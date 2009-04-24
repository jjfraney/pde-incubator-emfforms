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
 * $Id: SourcePage.java,v 1.2 2009/04/24 11:52:12 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.EventObject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.OverviewRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.pde.ds.scr.util.ScrResourceFactoryImpl;
import org.eclipse.pde.emfforms.editor.AbstractEmfFormPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;

public class SourcePage extends AbstractEmfFormPage {
	private SourceViewer _sourceStructuredTextViewer;

	public final static String ID = "ds.source"; //$NON-NLS-1$

	/**
	 * @param editor
	 */
	public SourcePage(FormEditor editor) {
		super(editor);
	}

	public void bind(DataBindingContext bindingContext) {
		final EditingDomain editingDomain = ((DSEditor) getEditor()).getEditingDomain();

		editingDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {
			public void commandStackChanged(EventObject event) {
				refreshSourceContent();
			}
		});

		// initialize content
		refreshSourceContent();
	}

	private void refreshSourceContent() {
		EObject obj = (EObject) getObservedValue().getValue();

		// Copies the root to avoid modifying it
		final StringWriter writer = new StringWriter();
		try {
			((XMLResource) obj.eResource()).save(writer, Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO log
		}
		final String result = writer.toString();
		writer.flush();
		if (_sourceStructuredTextViewer != null)
			_sourceStructuredTextViewer.getDocument().set(result);
	}

	public void createContents(Composite parent) {
		int VERTICAL_RULER_WIDTH = 12;

		GridLayout gl = new GridLayout(getNumColumns(), true);
		gl.verticalSpacing = 0;
		parent.setLayout(gl);
		ISharedTextColors sharedColors = EditorsPlugin.getDefault().getSharedTextColors();
		IOverviewRuler overviewRuler = new OverviewRuler(null, VERTICAL_RULER_WIDTH, sharedColors);

		String contentTypeID = "org.eclipse.pde.ds.content-type"; //$NON-NLS-1$
		_sourceStructuredTextViewer = new StructuredTextViewer(parent, new VerticalRuler(VERTICAL_RULER_WIDTH), null, false, SWT.V_SCROLL | SWT.WRAP);
		((StructuredTextViewer) _sourceStructuredTextViewer).getTextWidget().setFont(JFaceResources.getFont("org.eclipse.wst.sse.ui.textfont")); //$NON-NLS-1$
		IStructuredModel scratchModel = StructuredModelManager.getModelManager().createUnManagedStructuredModelFor(contentTypeID);
		IDocument document = scratchModel.getStructuredDocument();

		SourceViewerConfiguration sourceViewerConfiguration = new StructuredTextViewerConfigurationXML() {
			@Override
			public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
				return super.getContentAssistant(sourceViewer);
			}
		};
		_sourceStructuredTextViewer.configure(sourceViewerConfiguration);
		_sourceStructuredTextViewer.setDocument(document);
		//_sourceStructuredTextViewer.setEditable(false);

		_sourceStructuredTextViewer.addTextListener(new ITextListener() {
			public void textChanged(TextEvent event) {
				// FIXME this is ***VERY*** hackish
				reloadEObjectFromString(_sourceStructuredTextViewer.getDocument().get());
			}
		});
		GridDataFactory.fillDefaults().grab(true, true).applyTo(_sourceStructuredTextViewer.getControl());

	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getPartName() {
		return "Source";
	}

	@Override
	public Viewer getViewer() {
		return null;
	}

	private IObservableValue getObservedValue() {
		return ((DSEditor) getEditor()).getInputObservable();
	}

	private void reloadEObjectFromString(String string) {
		EObject obj = (EObject) getObservedValue().getValue();

		// Copies the root to avoid modifying it
		final ByteArrayInputStream reader = new ByteArrayInputStream(string.getBytes());
		try {
			Resource r = new ScrResourceFactoryImpl().createResource(URI.createURI("tmp"));
			r.unload();
			r.load(reader, Collections.EMPTY_MAP);
			getObservedValue().setValue(r.getContents().get(0));
		} catch (IOException e) {
			// TODO log
		}
	}

}
