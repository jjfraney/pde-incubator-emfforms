package org.eclipse.pde.ds.ui.internal.editor.wizard;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class TypeSelectionPage extends WizardPage implements Listener {

	private ComboViewer type;

	public TypeSelectionPage() {
		super("Type Selection");
		setTitle("Titre ici");
		setDescription("description ici");
		setPageComplete(false);
	}

	public void createControl(Composite parent) {

		Composite pageComposite = new Composite(parent, SWT.NULL);
		pageComposite.setLayout(new FillLayout());

		final String[] types = {"Property", "Properties"};
		type = PDEFormToolkit.createLabelAndComboViewer("Choose type :", pageComposite);
		type.getCombo().setItems(types);
		type.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection().isEmpty()) {
					setErrorMessage("Select a type");
					setPageComplete(false);
				}
				setErrorMessage(null);
				setPageComplete(true);
			}
		});

		setControl(pageComposite);
	}

	public ComboViewer getType() {
		return type;
	}

	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}

	public void handleEvent(Event event) {
		if (event.widget == getType().getCombo()) {
			canFlipToNextPage();
			getWizard().getContainer().updateButtons();
		}
	}

	@Override
	public IWizardPage getNextPage() {
		int sel = type.getCombo().getSelectionIndex();
		if (sel == 0)
			return ((AddPropertiesWizard) getWizard()).getPage("Create Property");
		if (sel == 1)
			return ((AddPropertiesWizard) getWizard()).getPage("Create Properties");
		return null;
	}
}
