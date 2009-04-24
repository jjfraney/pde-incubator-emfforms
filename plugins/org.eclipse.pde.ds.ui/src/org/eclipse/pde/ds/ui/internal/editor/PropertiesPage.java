package org.eclipse.pde.ds.ui.internal.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.pde.ds.scr.Component;
import org.eclipse.pde.ds.scr.Properties;
import org.eclipse.pde.ds.scr.Property;
import org.eclipse.pde.ds.scr.ScrFactory;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.action.newPropertiesAction;
import org.eclipse.pde.ds.ui.internal.editor.masterdetail.PropertiesMasterDetail;
import org.eclipse.pde.emfforms.editor.AbstractEmfFormPage;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;

public class PropertiesPage extends AbstractEmfFormPage {

	//MasterDetail
	private PropertiesMasterDetail _propertiesMasterDetail;

	public final static String ID = "ds.propertie"; //$NON-NLS-1$

	public PropertiesPage(FormEditor editor) {
		super(editor, 1, true);
	}

	public void bind(DataBindingContext bindingContext) {
		final EditingDomain editingDomain = ((DSEditor) getEditor()).getEditingDomain();

		Component c = ((DSEditor) getEditor()).getCurrentEObject();

		_propertiesMasterDetail.setComponentAndEditingDomain(c, editingDomain, getEditorSite(), bindingContext);
		// the following has to be done after setting the editing domain
		_propertiesMasterDetail.registerContextMenu();

		_propertiesMasterDetail.getAddButtonProperty().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection) _propertiesMasterDetail.getViewer().getSelection()).getFirstElement();
				int idx = CommandParameter.NO_INDEX;
				if (sel != null) {
					if (sel instanceof WrapperItemProvider) {
						WrapperItemProvider wip = (WrapperItemProvider) sel;
						idx = ((Component) getObservedValue().getValue()).getAllProperties().indexOf(wip.getValue());
					}
				}

				Property p = ScrFactory.eINSTANCE.createProperty();
				p.setName("property" + System.currentTimeMillis()); //$NON-NLS-1$
				Entry entryP = FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTY, p);
				Command command = AddCommand.create(editingDomain, getObservedValue().getValue(), null, entryP, idx);
				editingDomain.getCommandStack().execute(command);

				_propertiesMasterDetail.getViewer().setSelection(new StructuredSelection(p), true);
			}
		});

		_propertiesMasterDetail.getAddButtonProperties().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Properties p = ScrFactory.eINSTANCE.createProperties();
				p.setEntry("properties" + System.currentTimeMillis()); //$NON-NLS-1$
				Command command = AddCommand.create(editingDomain, getObservedValue().getValue(), null, FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTIES, p), 0);
				editingDomain.getCommandStack().execute(command);
			}
		});

		_propertiesMasterDetail.getWizardAddButton().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				newPropertiesAction action = new newPropertiesAction(getEditor().getEditorSite().getPage());
				action.run();
			}
		});

		_propertiesMasterDetail.getRemoveButton().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection) _propertiesMasterDetail.getViewer().getSelection()).getFirstElement();
				if (sel != null) {
					Command c = DeleteCommand.create(editingDomain, sel);
					editingDomain.getCommandStack().execute(c);
				}
			}
		});

	}

	public void createContents(Composite parent) {
		createDataMasterDetailSection(parent);
	}

	private void createDataMasterDetailSection(Composite parent) {
		_propertiesMasterDetail = new PropertiesMasterDetail();
		_propertiesMasterDetail.createContent(this.getManagedForm());
		// it is bad to manipulate editor here, but to manage Cut/Copy/Paste,
		// the editor shall add a listener the viewer, and this is a way for him
		// to know that viewer exists.
		((DSEditor) getEditor()).addViewerToListenTo(_propertiesMasterDetail.getViewer());
	}

	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			// force the selection, to avoid a bug on the ContextMenu (on tab
			// changed, display menu was unconsistent)
			IStructuredSelection selection = (IStructuredSelection) _propertiesMasterDetail.getViewer().getSelection();
			_propertiesMasterDetail.getViewer().setSelection(selection);
			_propertiesMasterDetail.getViewer().refresh();
		}
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getPartName() {
		return Messages.PropertyPage_Title;
	}

	@Override
	public Viewer getViewer() {
		return _propertiesMasterDetail.getViewer();
	}

	private IObservableValue getObservedValue() {
		return ((DSEditor) getEditor()).getInputObservable();
	}

}
