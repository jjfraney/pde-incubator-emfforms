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
 * $Id: SimpleSourcePage.java,v 1.1 2009/08/19 14:54:21 bcabe Exp $
 */
package org.eclipse.pde.emfforms.internal.editor;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;

public class SimpleSourcePage extends AbstractSourcePage {

	public SimpleSourcePage(EmfFormEditor<?> editor) {
		super(editor);
	}

	@Override
	public SourceViewer createSourceViewer(Composite parent) {
		SourceViewer sourceViewer = new SourceViewer(parent, new VerticalRuler(VERTICAL_RULER_WIDTH), SWT.V_SCROLL | SWT.WRAP);

		sourceViewer.getTextWidget().setFont(JFaceResources.getTextFont());

		sourceViewer.configure(new TextSourceViewerConfiguration());
		sourceViewer.setDocument(new Document());
		sourceViewer.setEditable(false);

		return sourceViewer;
	}

}
