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
 * $Id: ComponentComposite.java,v 1.1 2009/02/13 13:26:27 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class ComponentComposite extends Composite {

	private Text _textName;

	private Text _textImplementation;

	private Text _textActivate;

	private Text _textDeactivate;

	/**
	 * @param parent
	 * @param style
	 */
	public ComponentComposite(Composite parent, int style) {
		super(parent, style);
		createContents();
		hookListeners();
	}

	private void hookListeners() {
	}

	private void createContents() {
		_textName = PDEFormToolkit.createLabelAndText(
				Messages.ComponentComposite_Name, this);
		_textImplementation = PDEFormToolkit.createLabelAndText(
				Messages.ComponentComposite_Implementation, this);
		_textActivate = PDEFormToolkit.createLabelAndText(
				Messages.ComponentComposite_Activate, this);
		_textDeactivate = PDEFormToolkit.createLabelAndText(
				Messages.ComponentComposite_Deactivate, this);
	}

	public Text getTextName() {
		return _textName;
	}

	public Text getTextImplementation() {
		return _textImplementation;
	}

	public Text getTextActivate() {
		return _textActivate;
	}

	public Text getTextDeactivate() {
		return _textDeactivate;
	}
}
