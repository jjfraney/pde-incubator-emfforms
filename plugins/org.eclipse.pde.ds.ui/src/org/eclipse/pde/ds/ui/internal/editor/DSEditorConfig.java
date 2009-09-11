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
 * $Id: DSEditorConfig.java,v 1.3 2009/09/11 22:00:14 bcabe Exp $
 */
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

	@Override
	public boolean isShowSourcePage() {
		return true;
	}
}
