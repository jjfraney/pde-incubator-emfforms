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
 * $Id: IEmfFormPage.java,v 1.2 2009/04/24 12:16:08 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.IFormPage;

public interface IEmfFormPage extends IFormPage {

	void createContents(Composite parent);

	void bind(DataBindingContext bindingContext);

	/**
	 * If the page contains a viewer of special interest, this method must return it.
	 * The viewer will then automatically be used as a source for the Outline, the Properties View, etc. 
	 * @return a viewer of special interest, or <code>null</code> if no viewer.
	 */
	Viewer getViewer();

	/**
	 * In order to benefit from an automatic grid layout management, this method can be used to indicate the 
	 * desired number of columns for the actual page  
	 * @return a positive number of desired columns 
	 */
	int getNumColumns();

}