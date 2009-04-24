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
 * $Id: PropertiesComposite2.java,v 1.2 2009/04/24 12:16:23 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class PropertiesComposite2 extends Composite {

	private Text _textName;

	public PropertiesComposite2(Composite parent, int style) {
		super(parent, style);
		_textName = PDEFormToolkit.createLabelAndText(Messages.PropertiesComposite_Name, this);
	}

	public Text get_textName() {
		return _textName;
	}

	public void set_textName(Text textName) {
		_textName = textName;
	}

}
