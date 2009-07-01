package org.eclipse.pde.ds.builder.internal.validation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

/**
 * A set of helper methods for EMF
 */
public final class EMFHelper {
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
}
