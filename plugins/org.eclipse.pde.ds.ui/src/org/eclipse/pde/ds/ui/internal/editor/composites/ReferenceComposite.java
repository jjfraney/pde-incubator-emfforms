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
 * $Id: ReferenceComposite.java,v 1.1 2009/07/05 20:22:53 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class ReferenceComposite extends Composite {

	private Text _textName;

	private Text _textInterface;

	private Text _textTarget;

	private Text _textBind;

	private Text _textUnbind;

	/**
	 * @param parent
	 * @param style
	 */
	public ReferenceComposite(Composite parent, int style) {
		super(parent, style);
		createContents();
		hookListeners();
	}

	private void hookListeners() {
	}

	private void createContents() {
		_textName = PDEFormToolkit.createLabelAndText("Name:", this);
		_textInterface = PDEFormToolkit.createLabelAndText("Interface", this);
		_textTarget = PDEFormToolkit.createLabelAndText("Target", this);
		_textBind = PDEFormToolkit.createLabelAndText("Bind", this);
		_textUnbind = PDEFormToolkit.createLabelAndText("Unbind", this);
	}

	public Text getTextName() {
		return _textName;
	}

	public Text getTextInterface() {
		return _textInterface;
	}

	public Text getTextTarget() {
		return _textTarget;
	}

	public Text getTextBind() {
		return _textBind;
	}

	public Text getTextUnbind() {
		return _textUnbind;
	}
}
