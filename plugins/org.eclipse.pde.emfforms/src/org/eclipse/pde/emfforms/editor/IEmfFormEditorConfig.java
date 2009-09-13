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
 * $Id: IEmfFormEditorConfig.java,v 1.6 2009/09/13 18:18:43 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;

public interface IEmfFormEditorConfig<E extends EmfFormEditor<O>, O extends EObject> {

	public enum VALIDATE_ON_SAVE {
		NO_VALIDATION, VALIDATE_AND_WARN, VALIDATE_AND_ABORT
	}

	E getEditor();

	/**
	 * Return a member of {@link VALIDATE_ON_SAVE}, in order to say if a
	 * validation must be executed before saving resource
	 * 
	 * <ul>
	 * <li>
	 * {@link VALIDATE_ON_SAVE#NO_VALIDATION} no validation executed before
	 * saving resource</li>
	 * <li>{@link VALIDATE_ON_SAVE#VALIDATE_AND_WARN} a validation is executed,
	 * and only warn the user if the validation is not OK</li>
	 * <li>
	 * {@link VALIDATE_ON_SAVE#VALIDATE_AND_ABORT} a validation is executed, and
	 * the resource cannot be saved</li>
	 * </ul>
	 * 
	 * @return {@link VALIDATE_ON_SAVE}
	 */
	VALIDATE_ON_SAVE getValidateOnSave();

	boolean isSaveAsAllowed();

	/**
	 * @return <code>true</code> if the associated editor must use a shared clipboard,
	 *         which will allow copy/paste actions between different instance of
	 *         the same editor.
	 */
	boolean isUsingSharedClipboard();

	boolean isShowOutlinePage();

	boolean isShowSourcePage();

	/**
	 * 
	 * @param display
	 * @return customized toolkit if not null, otherwise default toolkit from display
	 */
	PDEFormToolkit createPDEFormToolkit(Display display);

	Object getOutlineInput(O suggestedInput);

}