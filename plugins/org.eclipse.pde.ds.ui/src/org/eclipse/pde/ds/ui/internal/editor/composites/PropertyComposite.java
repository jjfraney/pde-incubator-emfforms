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
 * $Id: PropertyComposite.java,v 1.3 2009/05/29 23:52:32 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class PropertyComposite extends Composite {

	private Text _textName;

	private ComboViewer _comboType;

	private Text _textValue;

	public PropertyComposite(Composite parent, int style) {
		super(parent, style);
		createContents();
	}

	private void createContents() {
		_textName = PDEFormToolkit.createLabelAndText(Messages.PropertyComposite_Name, this);
		_comboType = PDEFormToolkit.createLabelAndComboViewer(Messages.PropertyComposite_Type, this);
		_textValue = PDEFormToolkit.createLabelAndText(Messages.PropertyComposite_Value, this);
	}

	public Text getTextName() {
		return _textName;
	}

	public ComboViewer getComboType() {
		return _comboType;
	}

	public void setTextName(Text textName) {
		_textName = textName;
	}

	public Text getTextValue() {
		return _textValue;
	}

	public void setTextValue(Text textValue) {
		_textValue = textValue;
	}

}
