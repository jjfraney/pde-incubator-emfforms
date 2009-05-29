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
 * $Id: PropertiesComposite2.java,v 1.3 2009/05/29 23:52:32 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class PropertiesComposite2 extends Composite {

	private Text _textEntry;

	public PropertiesComposite2(Composite parent, int style) {
		super(parent, style);
		_textEntry = PDEFormToolkit.createLabelAndText(Messages.PropertiesComposite_Entry, this);
	}

	public Text getTextEntry() {
		return _textEntry;
	}

	public void setTextName(Text textName) {
		_textEntry = textName;
	}

}
