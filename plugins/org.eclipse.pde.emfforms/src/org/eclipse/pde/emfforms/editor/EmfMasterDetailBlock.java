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
 * $Id: EmfMasterDetailBlock.java,v 1.3 2009/07/07 09:36:40 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.*;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public abstract class EmfMasterDetailBlock extends MasterDetailsBlock implements IDetailsPageProvider, IMenuListener {

	private String title;
	private TreeViewer treeViewer;
	private Button addButton;
	private Button removeButton;
	protected EmfFormEditor<?> parentEditor;

	public EmfMasterDetailBlock(EmfFormEditor<?> editor, String title) {
		this.title = title;
		this.parentEditor = editor;
	}

	@Override
	protected void createMasterPart(final IManagedForm managedForm_, Composite parent) {
		FormToolkit toolkit = parentEditor.getToolkit();

		Section section = toolkit.createSection(parent, Section.DESCRIPTION | Section.TITLE_BAR);
		section.setText(title);
		section.setDescription("Edit " + title); //$NON-NLS-1$
		section.marginWidth = 5;
		section.setLayout(new FillLayout());
		section.marginHeight = 5;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(client);

		Composite browseComposite = new Composite(client, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(browseComposite);

		FilteredTree ft = new FilteredTree(browseComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL, new PatternFilter(), true);
		treeViewer = ft.getViewer();

		GridDataFactory.fillDefaults().grab(true, false).span(1, 2).applyTo(treeViewer.getControl());

		Composite buttonComposite = new Composite(browseComposite, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(buttonComposite);

		addButton = new Button(buttonComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(addButton);
		addButton.setText("Add..."); //$NON-NLS-1$

		removeButton = new Button(buttonComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(removeButton);
		removeButton.setText("Remove..."); //$NON-NLS-1$

		GridDataFactory.fillDefaults().grab(true, false).applyTo(buttonComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(browseComposite);

		treeViewer.setContentProvider(new AdapterFactoryContentProvider(parentEditor.getAdapterFactory()));
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(parentEditor.getAdapterFactory()));
		treeViewer.addFilter(getTreeFilter());

		final SectionPart spart = new SectionPart(section);
		managedForm_.addPart(spart);

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm_.fireSelectionChanged(spart, event.getSelection());
			}
		});

		treeViewer.addOpenListener(new IOpenListener() {
			public void open(OpenEvent event) {
				detailsPart.setFocus();
			}
		});

		createContextMenuFor(treeViewer);

		GridDataFactory.fillDefaults().grab(true, true).applyTo(treeViewer.getControl());

		section.setClient(client);
	}

	/**
	 * Return a ViewerFilter to apply on the treeViewer
	 * 
	 * @return a ViewerFilter to apply on the treeViewer
	 */
	protected abstract ViewerFilter getTreeFilter();

	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.setPageProvider(this);
	}

	public Object getPageKey(Object object) {
		return AdapterFactoryEditingDomain.unwrap(object);
	}

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	public Button getAddButton() {
		return addButton;
	}

	public Button getRemoveButton() {
		return removeButton;
	}

	protected void createContextMenuFor(StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator("additions"));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		IEditorActionBarContributor actionBarContributor = parentEditor.getEditorSite().getActionBarContributor();
		if (actionBarContributor != null && actionBarContributor instanceof EmfActionBarContributor)
			((EmfActionBarContributor) actionBarContributor).setFilter(getContextMenuFilter());
		parentEditor.getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));
	}

	protected abstract IFilter getContextMenuFilter();

	public void menuAboutToShow(IMenuManager manager) {
		if (parentEditor.getEditorSite().getActionBarContributor() != null)
			((IMenuListener) parentEditor.getEditorSite().getActionBarContributor()).menuAboutToShow(manager);
	}

}
