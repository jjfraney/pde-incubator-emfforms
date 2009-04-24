package org.eclipse.pde.ds.ui.internal.editor.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.pde.ds.ui.internal.editor.wizard.AddPropertiesWizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;

public class newPropertiesAction extends Action {

	private IWorkbenchPage workbenchPage;

	public newPropertiesAction(IWorkbenchPage workbenchPage) {
		super();
		this.workbenchPage = workbenchPage;
	}

	@Override
	public void run() {
		AddPropertiesWizard wizard = new AddPropertiesWizard(workbenchPage);

		Shell shell = workbenchPage.getWorkbenchWindow().getShell();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();

	}

}
