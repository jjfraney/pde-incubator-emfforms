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
 * $Id: Messages.java,v 1.1 2009/02/12 22:20:33 bcabe Exp $
 */
package org.eclipse.pde.emfforms.internal.editor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.pde.emfforms.editor.messages"; //$NON-NLS-1$

	public static String EmfFormEditor_DiaDialog_InvalidModel_Title;

	public static String EmfFormEditor_InitError;

	public static String EmfFormEditor_SaveError;

	public static String EmfFormEditor_SaveError_Exception;

	public static String EmfFormEditor_SaveError_Msg;

	public static String EmfFormEditor_SaveError_Title;

	public static String EmfFormEditor_ValidationError_Msg;

	public static String EmfFormEditor_ValidationWarn_Msg;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
