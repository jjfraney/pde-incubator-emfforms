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
 * $Id: XmlSourcePage.java,v 1.1 2009/08/19 14:54:21 bcabe Exp $
 */
package org.eclipse.pde.emfforms.internal.editor;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.*;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;

@SuppressWarnings("restriction")
public class XmlSourcePage extends AbstractSourcePage {

	public XmlSourcePage(EmfFormEditor<?> editor) {
		super(editor);
	}

	@Override
	public SourceViewer createSourceViewer(Composite parent) {
		StructuredTextViewer sourceViewer = new org.eclipse.wst.sse.ui.internal.StructuredTextViewer(parent, new VerticalRuler(VERTICAL_RULER_WIDTH), null, false, SWT.V_SCROLL | SWT.WRAP);

		sourceViewer.getTextWidget().setFont(JFaceResources.getFont("org.eclipse.wst.sse.ui.textfont")); //$NON-NLS-1$
		IStructuredDocument document = StructuredModelManager.getModelManager().createStructuredDocumentFor("org.eclipse.core.runtime.xml"); //$NON-NLS-1$

		SourceViewerConfiguration sourceViewerConfiguration = new StructuredTextViewerConfigurationXML() {
			@Override
			public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
				return super.getContentAssistant(sourceViewer);
			}
		};

		// sourceViewerConfiguration.getContentFormatter(_sourceStructuredTextViewer).format(document, new Region(0, document.getLength()));
		sourceViewer.configure(sourceViewerConfiguration);
		sourceViewer.setDocument(document);
		sourceViewer.setEditable(false);

		return sourceViewer;
	}

}
