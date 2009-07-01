package org.eclipse.pde.ds.builder.internal.validation;

import org.eclipse.pde.core.plugin.IPluginModelBase;

import org.eclipse.pde.internal.core.PDECore;

import org.eclipse.pde.internal.core.PluginModelManager;

import org.eclipse.pde.internal.core.text.bundle.BundleModel;

import org.eclipse.core.runtime.URIUtil;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.natures.PDE;

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
				if(resource instanceof IProject)
					return true;
				// handle changed resource
				URI resourceURI = URI.createPlatformResourceURI(resource
						.getFullPath().toString(), true);
				Resource modelResource = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE),new BasicCommandStack())
						.getResourceSet().getResource(resourceURI, true);
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

	protected void fullBuild(IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		if (PDE.hasPluginNature(getProject())) {
			IFile manifestFile = getProject().getFile(ICoreConstants.BUNDLE_FILENAME_DESCRIPTOR);

			IPluginModelBase bundle = PDECore.getDefault().getModelManager().findModel(getProject());
			
			// TODO analyze every component referenced in the Service-Component header
			// build(project, getProject(), true, new SubProgressMonitor(monitor,1));
			URI res = URI.createPlatformResourceURI(getProject().getFile("component.xml").getFullPath().toString());
			Resource modelResource = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE),new BasicCommandStack())
			.getResourceSet().getResource(res, true);
			build(modelResource.getContents().get(0), getProject().getFile("component.xml"), true, new SubProgressMonitor(monitor,1));
			
			
		}
	}

	protected abstract void build(EObject modelObject, IResource resource,
			boolean force, IProgressMonitor monitor) throws CoreException;
}
