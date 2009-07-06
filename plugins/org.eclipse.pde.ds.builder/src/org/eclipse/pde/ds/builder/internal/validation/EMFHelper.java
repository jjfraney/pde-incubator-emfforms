package org.eclipse.pde.ds.builder.internal.validation;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * A set of helper methods for EMF
 */
public final class EMFHelper {
	private EMFHelper() {
	}

	private static final String PLATFORM_SCHEME = "platform"; //$NON-NLS-1$

	private static final String FILE_SCHEME = "file"; //$NON-NLS-1$

	private static final String RESOURCE_SEGMENT = "resource"; //$NON-NLS-1$

	public static IFile getIFile(URI uri) {
		IFile file = null;

		if (PLATFORM_SCHEME.equals(uri.scheme()) && uri.segmentCount() > 1
				&& RESOURCE_SEGMENT.equals(uri.segment(0))) {
			StringBuffer platformResourcePath = new StringBuffer();
			for (int j = 1, size = uri.segmentCount(); j < size; ++j) {
				platformResourcePath.append('/');
				platformResourcePath.append(URI.decode(uri.segment(j)));
			}

			file = ResourcesPlugin.getWorkspace().getRoot().getFile(
					new Path(platformResourcePath.toString()));
		} else if (FILE_SCHEME.equals(uri.scheme())) {
			StringBuffer fileResourcePath = new StringBuffer();
			for (int j = 1, size = uri.segmentCount(); j < size; ++j) {
				fileResourcePath.append('/');
				fileResourcePath.append(URI.decode(uri.segment(j)));
			}

			file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(
					new Path(fileResourcePath.toString()));
		}

		return file;
	}

	/**
	 * Return the IProject corresponding to the given Resource
	 * 
	 * @param resource
	 *            Resource
	 * 
	 * @return IProject
	 */
	public static IProject getIProject(Resource resource) {
		IProject result = null;
		// Using its URI
		org.eclipse.emf.common.util.URI uri = resource.getURI();
		if (resource.getResourceSet().getURIConverter().exists(uri, null)) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

			// Retrieve the corresponding Platform IResource
			IResource findMember = root.findMember(new Path(uri
					.toPlatformString(true)));

			// And finally returning the corresponding IProject
			result = findMember.getProject();
		}
		return result;
	}
}
