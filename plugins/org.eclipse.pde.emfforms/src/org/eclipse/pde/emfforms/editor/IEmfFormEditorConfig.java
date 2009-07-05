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
 * $Id: IEmfFormEditorConfig.java,v 1.2 2009/07/05 20:22:09 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import org.eclipse.swt.widgets.Display;

public interface IEmfFormEditorConfig {

	public enum VALIDATE_ON_SAVE {
		NO_VALIDATION, VALIDATE_AND_WARN, VALIDATE_AND_ABORT
	}

	public abstract VALIDATE_ON_SAVE getValidateOnSave();

	public abstract boolean isSaveAsAllowed();

	public abstract boolean isUsingSharedClipboard();

	public abstract boolean isShowOutlinePage();

	/**
	 * 
	 * @param display
	 * @return customized toolkit if not null, otherwise default toolkit from display
	 */
	public abstract PDEFormToolkit createPDEFormToolkit(Display display);

}