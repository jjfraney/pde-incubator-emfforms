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
 * $Id: OptionsComposite.java,v 1.1 2009/02/13 13:26:27 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.pde.ds.scr.ConfigurationPolicy;
import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.widgets.*;

public class OptionsComposite extends Composite {

	private Text _textFactory;

	private ComboViewer _comboviewerConfigurationPolicy;

	private Button _buttonEnabled;

	private Button _buttonImmediate;

	/**
	 * @param parent
	 * @param style
	 */
	public OptionsComposite(Composite parent, int style) {
		super(parent, style);
		createContents();
		hookListeners();
	}

	private void hookListeners() {
	}

	private void createContents() {
		_textFactory = PDEFormToolkit.createLabelAndText(
				Messages.OptionsComposite_Factory, this);
		_comboviewerConfigurationPolicy = PDEFormToolkit
				.createLabelAndComboViewer(
						Messages.OptionsComposite_ConfigurationPolicy, this);
		_comboviewerConfigurationPolicy.setInput(ConfigurationPolicy.values());
		_buttonEnabled = PDEFormToolkit.createCheckBoxAndLabel(
				Messages.OptionsComposite_Enabled, this);
		_buttonImmediate = PDEFormToolkit.createCheckBoxAndLabel(
				Messages.OptionsComposite_Immediate, this);
	}

	public Text getTextFactory() {
		return _textFactory;
	}

	public ComboViewer getComboViewerConfigurationPolicy() {
		return _comboviewerConfigurationPolicy;
	}

	public Button getButtonEnabled() {
		return _buttonEnabled;
	}

	public Button getButtonImmediate() {
		return _buttonImmediate;
	}
}
