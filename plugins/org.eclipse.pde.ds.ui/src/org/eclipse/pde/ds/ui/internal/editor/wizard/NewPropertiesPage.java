package org.eclipse.pde.ds.ui.internal.editor.wizard;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.ds.scr.Properties;
import org.eclipse.pde.ds.scr.ScrFactory;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.PropertiesComposite2;
import org.eclipse.pde.emfforms.databinding.EMFValidatingUpdateValueStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class NewPropertiesPage extends WizardPage {

	private PropertiesComposite2 propertiesComposite;
	private Properties p;

	protected NewPropertiesPage(String pageName) {
		super(pageName);
		setPageComplete(false);

	}

	public void createControl(Composite parent) {

		propertiesComposite = new PropertiesComposite2(parent, SWT.NULL);

		EditingDomain ed = ((AddPropertiesWizard) getWizard()).getEditingDomain();

		p = ScrFactory.eINSTANCE.createProperties();
		setControl(propertiesComposite);

		//binding
		DataBindingContext bindingContext = new DataBindingContext();

		IObservableValue iov = new WritableValue();
		iov.setValue(p);
		//Name
		bindingContext.bindValue(SWTObservables.observeText(propertiesComposite.get_textName(), SWT.FocusOut), EMFEditObservables.observeDetailValue(Realm.getDefault(), ed, iov, ScrPackage.eINSTANCE.getProperties_Entry()), new EMFValidatingUpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (!((String) value).trim().isEmpty())
					setPageComplete(true);
				return super.convert(value);
			}
		}, null);

	}

	public void createProperties() {

		EditingDomain ed = ((AddPropertiesWizard) getWizard()).getEditingDomain();
		IObservableValue ov = ((AddPropertiesWizard) getWizard()).getObservedValue();

		Entry entryP = FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTIES, p);
		Command c = AddCommand.create(ed, ov.getValue(), null, entryP, CommandParameter.NO_INDEX);
		ed.getCommandStack().execute(c);

	}

	@Override
	public IWizardPage getNextPage() {
		return null;
	}

}
