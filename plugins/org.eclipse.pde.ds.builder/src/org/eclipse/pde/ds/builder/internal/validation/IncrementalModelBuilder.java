/**
 * Copyright (c) 2009 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anyware Technologies - initial API and implementation
 *
 * $Id: IncrementalModelBuilder.java,v 1.4 2009/07/05 17:12:48 bcabe Exp $
 */
package org.eclipse.pde.ds.builder.internal.validation;

import java.util.*;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.ibundle.IBundleModel;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.core.natures.PDE;
import org.osgi.service.component.ComponentConstants;

/**
 * An abstract class to subclass to launch background jobs on the modified model
 */
public abstract class IncrementalModelBuilder extends IncrementalProjectBuilder {
	protected class ModelFileDeltaVisitor implements IResourceDeltaVisitor {
		private Map<Resource, IResource> modifiedResources = new HashMap<Resource, IResource>();

		public ModelFileDeltaVisitor() {
			// Do nothing
		}

		public Map<Resource, IResource> getModifiedResources() {
			return modifiedResources;
		}

		/**
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse
		 *      .core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
			case IResourceDelta.REMOVED:
				// Do nothing
				break;
			case IResourceDelta.CHANGED:
				if (resource instanceof IContainer)
					return true;
				if (!"org.eclipse.pde.ds.content-type"
						.equals(((IFile) resource).getContentDescription()
								.getContentType().getId()))
					return false;
				// handle changed resource
				URI resourceURI = URI.createPlatformResourceURI(resource
						.getFullPath().toString(), true);
				Resource modelResource = new AdapterFactoryEditingDomain(
						new ComposedAdapterFactory(
								ComposedAdapterFactory.Descriptor.Registry.INSTANCE),
						new BasicCommandStack()).getResourceSet().getResource(
						resourceURI, true);
				if (modelResource != null && modelResource.isLoaded()
						&& !modifiedResources.containsKey(modelResource)) {
					modifiedResources.put(modelResource, resource);
				}
				break;
			}
			// return true to continue visiting children.
			return true;
		}
	}

	/**
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder#build(int,
	 *      java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		switch (kind) {
		case FULL_BUILD:
			fullBuild(monitor);
			break;
		case CLEAN_BUILD:
			clean(monitor);
			break;
		default:
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}

			break;
		}
		return null;
	}

	@Override
	protected void clean(IProgressMonitor monitor) throws CoreException {
		// Subclasses must override this method
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		// the visitor does the work.
		ModelFileDeltaVisitor visitor = new ModelFileDeltaVisitor();
		delta.accept(visitor);

		Map<Resource, IResource> modifiedResources = visitor
				.getModifiedResources();

		monitor.beginTask("Incremental Build", modifiedResources.size());

		for (Resource modelResource : modifiedResources.keySet()) {
			build(modelResource.getContents().get(0), modifiedResources
					.get(modelResource), false, new SubProgressMonitor(monitor,
					1));
		}

		monitor.done();

	}

	@SuppressWarnings("restriction")
	protected void fullBuild(IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		if (PDE.hasPluginNature(getProject())) {
			IPluginModelBase bundle = PluginRegistry.findModel(getProject());
			if (bundle instanceof IBundlePluginModelBase) {
				IBundleModel bundleModel = ((IBundlePluginModelBase) bundle)
						.getBundleModel();
				// XXX for some reason, if we don't call load() by hand, some headers are missing (???)
				bundleModel.load();
				String serviceComponents = bundleModel.getBundle().getHeader(
						ComponentConstants.SERVICE_COMPONENT);
				StringTokenizer tok = new StringTokenizer(serviceComponents,
						","); //$NON-NLS-1$
				// process all definition file
				while (tok.hasMoreElements()) {
					String definitionFile = tok.nextToken().trim();
					int ind = definitionFile.lastIndexOf('/');
					String path = ind != -1 ? definitionFile.substring(0, ind)
							: "/"; //$NON-NLS-1$
					// TODO we need to support pattern (path may be equal to
					// something like "/OSGI-INF/comp-*.xml"...)
					IFile componentFile = getProject().getFile(definitionFile);
					URI res = URI.createPlatformResourceURI(componentFile
							.getFullPath().toString(), true);
					Resource modelResource = new AdapterFactoryEditingDomain(
							new ComposedAdapterFactory(
									ComposedAdapterFactory.Descriptor.Registry.INSTANCE),
							new BasicCommandStack()).getResourceSet()
							.getResource(res, true);
					build(modelResource.getContents().get(0), componentFile,
							true, new SubProgressMonitor(monitor, 1));
				} // end while

			}

		}
	}

	protected abstract void build(EObject modelObject, IResource resource,
			boolean force, IProgressMonitor monitor) throws CoreException;
}
