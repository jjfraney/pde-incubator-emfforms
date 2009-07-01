package org.eclipse.pde.ds.builder.internal.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Helper class to manage markers : clean, creation...
 */
public final class MarkerHelper {

	public static final String VALIDATION_MARKER_TYPE = "org.eclipse.core.resources.problemmarker"; //$NON-NLS-1$

	public static void cleanMarkers(IContainer container) throws CoreException {
		if (container != null) {
			String markerType = MarkerHelper.VALIDATION_MARKER_TYPE;
			container.deleteMarkers(markerType, true, IResource.DEPTH_INFINITE);
		}
	}

	public static void cleanMarkers(IFile file) throws CoreException {
		if (file != null) {
			String markerType = MarkerHelper.VALIDATION_MARKER_TYPE;
			file.deleteMarkers(markerType, true, IResource.DEPTH_ZERO);
		}
	}

	private static void createMarker(Diagnostic diagnostic,
			Map<URI, IFile> visitedResources) throws CoreException {
		String markerType = MarkerHelper.VALIDATION_MARKER_TYPE;

		EObject target = (EObject) diagnostic.getData().get(0);
		Resource r = target.eResource();
		URI resourceUri = r.getURI();

		// Normalize the URI to something that we can deal with like file or
		// platform scheme
		resourceUri = r.getResourceSet().getURIConverter().normalize(
				resourceUri);

		IFile file = visitedResources.get(resourceUri);

		if (file == null) {
			file = EMFHelper.getIFile(resourceUri);

			if (file != null) {
				file.deleteMarkers(markerType, true, IResource.DEPTH_ZERO);
				visitedResources.put(resourceUri, file);
			}
		}

		if (file != null) {

			IMarker marker = file.createMarker(markerType);

			marker.setAttribute(IMarker.SOURCE_ID, diagnostic.getCode());
			marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(
					target).toString());

			switch (diagnostic.getSeverity()) {
			case IStatus.INFO:
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
				marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_LOW);
				break;
			case IStatus.WARNING:
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
				marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_NORMAL);
				break;
			case IStatus.ERROR:
			case IStatus.CANCEL:
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
				break;
			}

			marker.setAttribute(IMarker.MESSAGE, diagnostic.getMessage());
		}
	}

	public static void createMarkers(final Diagnostic validationDiagnostic,
			IProgressMonitor monitor) throws CoreException {
		if (validationDiagnostic.getSeverity() == Diagnostic.OK) {
			return;
		}
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor m) throws CoreException {
				final Map<URI, IFile> visitedResources = new HashMap<URI, IFile>();

				if (!validationDiagnostic.getChildren().isEmpty()) {
					m.beginTask("Create validation markers",
							validationDiagnostic.getChildren().size());
					for (Diagnostic diagnostic : validationDiagnostic
							.getChildren()) {
						List<?> data = diagnostic.getData();
						if (data != null && !data.isEmpty()
								&& data.get(0) instanceof EObject) {
							createMarker(diagnostic, visitedResources);
						}

						m.worked(1);
					}
				} else {
					createMarker(validationDiagnostic, visitedResources);
				}

				m.done();
			}
		};

		ResourcesPlugin.getWorkspace().run(runnable, monitor);
	}
}
