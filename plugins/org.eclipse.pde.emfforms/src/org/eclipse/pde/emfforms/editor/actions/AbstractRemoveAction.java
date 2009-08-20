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
 * $Id: AbstractRemoveAction.java,v 1.1 2009/08/20 17:22:09 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor.actions;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.pde.emfforms.editor.EmfMasterDetailBlock;
import org.eclipse.pde.emfforms.internal.Activator;
import org.eclipse.pde.emfforms.internal.editor.IEmfFormsImages;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractRemoveAction extends Action {

	private EmfMasterDetailBlock masterDetail;

	public AbstractRemoveAction(EmfMasterDetailBlock masterDetail) {
		super();
		this.masterDetail = masterDetail;
		setText("Remove");
		setToolTipText("Remove selected element");
	}

	public void run() {
		TreeViewer treeViewer = masterDetail.getTreeViewer();

		if (!treeViewer.getSelection().isEmpty()) {

			int selIndex = treeViewer.getTree().indexOf(treeViewer.getTree().getSelection()[0]);

			EObject sel = (EObject) AdapterFactoryEditingDomain.unwrap((((IStructuredSelection) treeViewer.getSelection()).getFirstElement()));
			AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(masterDetail.getEditor().getAdapterFactory());

			// -- Confirmation Dialog
			String title = "Delete the current selection"; //$NON-NLS-1$
			String message = "Do you really want to delete '" + labelProvider.getText(sel) + "' ?"; //$NON-NLS-1$ //$NON-NLS-2$

			if (MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message)) {
				EditingDomain editingDomain = masterDetail.getEditor().getEditingDomain();
				// perform remove method
				removeObject(sel, editingDomain);

				treeViewer.refresh();
				if (treeViewer.getTree().getItemCount() > 0) {

					// if we delete the last line, select the new last
					// line
					if (selIndex >= treeViewer.getTree().getItemCount())
						selIndex = selIndex - 1;

					treeViewer.getTree().setSelection(treeViewer.getTree().getItem(selIndex));
					treeViewer.getTree().setFocus();
				}
			}
		}
	}

	protected abstract void removeObject(EObject sel, EditingDomain editingDomain);

	@Override
	public ImageDescriptor getImageDescriptor() {
		return ImageDescriptor.createFromURL(Activator.getDefault().getBundle().getResource(IEmfFormsImages.REMOVE_TOOLBAR_BUTTON));
	}

}
