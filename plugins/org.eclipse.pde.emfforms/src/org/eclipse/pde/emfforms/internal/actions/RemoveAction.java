package org.eclipse.pde.emfforms.internal.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
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

public class RemoveAction extends Action {

	private EmfMasterDetailBlock masterDetail;

	public RemoveAction(EmfMasterDetailBlock masterDetail) {
		super();
		this.masterDetail = masterDetail;
		setText("Remove");
		setToolTipText("Remove selected element");
	}

	public void run() {
		TreeViewer treeViewer = masterDetail.getTreeViewer();

		if (!treeViewer.getSelection().isEmpty()) {

			int selIndex = treeViewer.getTree().indexOf(treeViewer.getTree().getSelection()[0]);

			EObject sel = (EObject) ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
			AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(masterDetail.getEditor().getAdapterFactory());

			// -- Confirmation Dialog
			String title = "Delete the current selection"; //$NON-NLS-1$
			String message = "Do you really want to delete '" + labelProvider.getText(sel) + "' ?"; //$NON-NLS-1$ //$NON-NLS-2$

			if (MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message)) {
				EditingDomain editingDomain = masterDetail.getEditor().getEditingDomain();
				// perform add method
				Command c = RemoveCommand.create(editingDomain, sel);
				editingDomain.getCommandStack().execute(c);

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

	@Override
	public ImageDescriptor getImageDescriptor() {
		return ImageDescriptor.createFromURL(Activator.getDefault().getBundle().getResource(IEmfFormsImages.REMOVE_TOOLBAR_BUTTON));
	}

}
