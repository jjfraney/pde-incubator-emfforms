package org.eclipse.pde.ds.builder.internal.validation;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.pde.ds.builder.internal.Activator;

public class ScrBuilder extends IncrementalModelBuilder {
	public static final String ID = "org.eclipse.pde.ds.builder.scrBuilder";

	public ScrBuilder() {
	}

	protected void build(EObject modelObject, IResource resource,
			boolean force, IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		monitor.beginTask("Model Validation", 4);

		monitor.subTask("Validation");

		Diagnostic diagnostic = validate(modelObject);

		monitor.worked(3);

		if (resource instanceof IContainer) {
			MarkerHelper.cleanMarkers((IContainer) resource);
		} else if (resource instanceof IFile) {
			MarkerHelper.cleanMarkers((IFile) resource);
		}

		MarkerHelper.createMarkers(diagnostic, new SubProgressMonitor(monitor,
				1));

		monitor.done();
	}

	private Diagnostic validate(final EObject modelObject) {
		TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE
				.createEditingDomain();
		final AdapterFactory adapterFactory = domain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain) domain)
				.getAdapterFactory()
				: null;

		try {
			return (Diagnostic) domain
					.runExclusive(new RunnableWithResult.Impl<Diagnostic>() {
						public void run() {

							Diagnostic diagnostic = new Diagnostician() {

								@Override
								public String getObjectLabel(EObject eObject) {
									if (adapterFactory != null
											&& !eObject.eIsProxy()) {
										IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory
												.adapt(
														eObject,
														IItemLabelProvider.class);
										if (itemLabelProvider != null) {
											return itemLabelProvider
													.getText(eObject);
										}
									}

									return super.getObjectLabel(eObject);
								}
							}.validate(modelObject);
							setResult(diagnostic);
						}
					});
		} catch (InterruptedException ie) {
			// Log and return the exception in a diagnostic
			Activator.log(ie);
			return new BasicDiagnostic(Diagnostic.ERROR, "ModelChecker", 0, ie
					.getMessage(), new Object[] { modelObject });
		}
	}

	@Override
	protected String getContentType() {
		return "org.eclipse.pde.ds.content-type";
	}
}
