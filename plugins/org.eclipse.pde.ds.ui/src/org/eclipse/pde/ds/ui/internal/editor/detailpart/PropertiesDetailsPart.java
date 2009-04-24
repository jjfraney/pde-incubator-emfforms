package org.eclipse.pde.ds.ui.internal.editor.detailpart;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.PropertiesComposite2;
import org.eclipse.pde.emfforms.databinding.EMFValidatingUpdateValueStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class PropertiesDetailsPart implements IDetailsPage {

	private IManagedForm managedForm;
	private EditingDomain editingDomain;
	private DataBindingContext dataBindingContext;
	private IObservableValue currentProperties;
	private PropertiesComposite2 propertiesComposite;

	public PropertiesDetailsPart(IManagedForm managedForm, EditingDomain editingDomain, DataBindingContext dataBindingContext) {
		this.managedForm = managedForm;
		this.editingDomain = editingDomain;
		this.dataBindingContext = dataBindingContext;

		this.currentProperties = new WritableValue();
	}

	public void createContents(Composite parent) {
		GridLayoutFactory.fillDefaults().margins(0, 0).applyTo(parent);

		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Détails");
		section.marginWidth = 10;
		section.marginHeight = 5;

		propertiesComposite = new PropertiesComposite2(section, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(propertiesComposite);

		managedForm.getToolkit().adapt(propertiesComposite);

		toolkit.adapt(propertiesComposite);
		propertiesComposite.setParent(section);

		section.setClient(propertiesComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);

		bind(dataBindingContext);
	}

	private void bind(DataBindingContext bindingContext) {

		// Entry
		bindingContext.bindValue(SWTObservables.observeText(propertiesComposite.get_textName(), SWT.FocusOut), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, currentProperties, ScrPackage.eINSTANCE.getProperties_Entry()), new EMFValidatingUpdateValueStrategy(), null);
	}

	public void commit(boolean onSave) {
	}

	public void dispose() {

	}

	public void initialize(IManagedForm form) {

	}

	public boolean isDirty() {
		return false;
	}

	public boolean isStale() {
		return false;
	}

	public void refresh() {
	}

	public void setFocus() {
		propertiesComposite.get_textName().setFocus();
		propertiesComposite.get_textName().selectAll();
	}

	public boolean setFormInput(Object input) {
		return false;
	}

	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection sel = (IStructuredSelection) selection;
		if (sel.getFirstElement() instanceof WrapperItemProvider) {
			WrapperItemProvider wip = (WrapperItemProvider) sel.getFirstElement();
			Object o = wip.getEditableValue(sel.getFirstElement());
			currentProperties.setValue(o);
		}
	}

}
