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
 * $Id: DSEditorConfig.java,v 1.4 2009/09/13 18:04:02 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor;

import org.eclipse.pde.ds.scr.Component;
import org.eclipse.pde.emfforms.editor.DefaultEmfFormEditorConfig;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;

public class DSEditorConfig extends DefaultEmfFormEditorConfig<Component> {

	public DSEditorConfig(EmfFormEditor<Component> editor) {
		super(editor);
	}

	@Override
	public boolean isUsingSharedClipboard() {
		return true;
	}

	@Override
	public VALIDATE_ON_SAVE getValidateOnSave() {
		return VALIDATE_ON_SAVE.VALIDATE_AND_WARN;
	}

	@Override
	public boolean isShowSourcePage() {
		return true;
	}
}
