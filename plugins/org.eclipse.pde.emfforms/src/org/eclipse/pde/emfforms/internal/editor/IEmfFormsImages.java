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
 * $Id: IEmfFormsImages.java,v 1.1 2009/07/17 14:33:54 bcabe Exp $
 */

package org.eclipse.pde.emfforms.internal.editor;

/**
 * Interface used to stock information about images used in the platform editor
 */
public interface IEmfFormsImages {
	public static final String ICONS_FOLDER = "icons/";

	public static final String DECORATORS_FOLDER = ICONS_FOLDER + "decorators/";

	public static final String ERROR_DECORATOR = DECORATORS_FOLDER + "error_ovr.gif";

	public static final String WARNING_DECORATOR = DECORATORS_FOLDER + "warning_co.gif";
}
