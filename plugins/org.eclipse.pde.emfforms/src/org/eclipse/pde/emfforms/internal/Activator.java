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
 * $Id: Activator.java,v 1.2 2009/07/17 14:33:55 bcabe Exp $
 */
package org.eclipse.pde.emfforms.internal;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.pde.emfforms"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void log(IStatus status) {
		if (status != null)
			getDefault().getLog().log(status);
	}

	public static void log(Throwable e) {
		if (e instanceof InvocationTargetException)
			e = ((InvocationTargetException) e).getTargetException();
		IStatus status = null;
		if (e instanceof CoreException) {
			status = ((CoreException) e).getStatus();
		} else if (e.getMessage() != null) {
			status = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, e.getMessage(), e);
		}
		log(status);
	}

	public static void logErrorMessage(String message) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, message, null));
	}

	public static void logException(Throwable e) {
		logException(e, null);
	}

	public static void logException(Throwable e, String message) {
		if (e instanceof InvocationTargetException) {
			e = ((InvocationTargetException) e).getTargetException();
		}
		IStatus status = null;
		if (e instanceof CoreException)
			status = ((CoreException) e).getStatus();
		else {
			if (message == null)
				message = e.getMessage();
			if (message == null)
				message = e.toString();
			status = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message, e);
		}
		log(status);
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String pluginId, String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, path);
	}

	/**
	 * Add an image to the image registry
	 * 
	 * @param registry
	 *            the registry to use
	 * @param imgPath
	 *            the path of the image to add
	 */
	public static void addImageToRegistry(ImageRegistry registry, String imgPath) {
		ImageDescriptor descriptor = getImageDescriptor(getDefault().getBundle().getSymbolicName(), imgPath);
		addImageToRegistry(registry, imgPath, descriptor);
	}

	/**
	 * Register in the given ImageRegistry the ImageDescriptor using the Image's
	 * path as key.
	 * 
	 * @param registry
	 *            ImageRegistry
	 * @param imgPath
	 *            String
	 * @param imgDesc
	 *            ImageDescriptor
	 */
	public static void addImageToRegistry(ImageRegistry registry, String imgPath, ImageDescriptor imgDesc) {
		ImageRegistry imgRegistry = registry;
		if (imgRegistry == null) {
			imgRegistry = getDefault().getImageRegistry();
		}

		imgRegistry.put(imgPath, imgDesc);
	}

	/**
	 * Get an image from the local ImageRegistry. If the given Image's path is
	 * not already registered, do it.
	 * 
	 * @param imagePath
	 *            String, path and key identifying the image in the
	 *            ImageRegistry
	 * 
	 * @return Image or null if nothing corresponds to the given key
	 */
	public static Image getImage(String imagePath) {
		Image result = getDefault().getImageRegistry().get(imagePath);

		if (result == null) {
			addImageToRegistry(getDefault().getImageRegistry(), imagePath);
			result = getDefault().getImageRegistry().get(imagePath);
		}

		return result;
	}

	/**
	 * Get an ImageDescriptor from the local ImageRegistry. If the given Image's
	 * path is not already registered, do it.
	 * 
	 * @param imagePath
	 *            String, path and key identifying the ImageDescriptor in the
	 *            ImageRegistry
	 * 
	 * @return ImageDescriptor or null if nothing corresponds to the given key
	 */
	public static ImageDescriptor getImageDescriptor(String imagePath) {
		ImageDescriptor result = getDefault().getImageRegistry().getDescriptor(imagePath);

		if (result == null) {
			addImageToRegistry(getDefault().getImageRegistry(), imagePath);
			result = getDefault().getImageRegistry().getDescriptor(imagePath);
		}

		return result;
	}

}
