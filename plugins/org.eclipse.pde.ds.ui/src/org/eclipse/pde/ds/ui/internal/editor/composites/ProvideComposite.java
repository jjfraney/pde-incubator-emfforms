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
 * $Id: ProvideComposite.java,v 1.1 2009/07/05 20:22:53 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class ProvideComposite extends Composite {

	private Text _textInterface;

	/**
	 * @param parent
	 * @param style
	 */
	public ProvideComposite(Composite parent, int style) {
		super(parent, style);
		createContents();
		hookListeners();
	}

	private void hookListeners() {
	}

	private void createContents() {
		_textInterface = PDEFormToolkit.createLabelAndText("Interface", this);
	}

	public Text getTextInterface() {
		return _textInterface;
	}
}
