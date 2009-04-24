package org.eclipse.pde.ds.ui.internal.editor;

import org.eclipse.pde.emfforms.editor.DefaultEmfFormEditorConfig;

public class DSEditorConfig extends DefaultEmfFormEditorConfig {
	@Override
	public boolean isUsingSharedClipboard() {
		return true;
	}

	@Override
	public VALIDATE_ON_SAVE getValidateOnSave() {
		return VALIDATE_ON_SAVE.VALIDATE_AND_WARN;
	}
}
