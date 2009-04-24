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
 * $Id: PropertiesComposite.java,v 1.2 2009/04/24 11:52:12 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.pde.emfforms.editor.PDEFormToolkit.Pair;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class PropertiesComposite extends Composite {

	private Pair<TreeViewer, Pair<Button, Button>> _propertiesViewer;

	/**
	 * @param parent
	 * @param style
	 */
	public PropertiesComposite(Composite parent, int style) {
		super(parent, style);
		createContents();
		hookListeners();
	}

	private void createContents() {
		PropertiesComposite composite = this;
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(composite);

		Composite browseComposite = new Composite(composite, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(browseComposite);

		TreeViewer listViewer = new TreeViewer(browseComposite, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).span(1, 2).applyTo(listViewer.getControl());

		Button addButton = new Button(browseComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(addButton);
		addButton.setText("Add..."); //$NON-NLS-1$

		Button removeButton = new Button(browseComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(removeButton);
		removeButton.setText("Remove"); //$NON-NLS-1$

		GridDataFactory.fillDefaults().grab(true, false).applyTo(browseComposite);
		_propertiesViewer = new Pair<TreeViewer, Pair<Button, Button>>(listViewer, new Pair<Button, Button>(addButton, removeButton));
	}

	private void hookListeners() {
	}

	public Pair<TreeViewer, Pair<Button, Button>> getPropertiesViewer() {
		return _propertiesViewer;
	}
}
