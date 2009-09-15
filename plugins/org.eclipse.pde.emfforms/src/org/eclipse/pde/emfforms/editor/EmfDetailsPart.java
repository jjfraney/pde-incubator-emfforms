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
 * $Id: EmfDetailsPart.java,v 1.6 2009/08/07 10:33:03 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import java.util.*;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.internal.databinding.observable.masterdetail.DetailObservableValue;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.databinding.internal.EMFObservableValueDecorator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.*;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class EmfDetailsPart implements IDetailsPage {

	private Composite _mainDetailComposite;
	private EmfFormEditor<?> _parentEditor;
	private IObservableValue _selectedObject;
	private List<Binding> _bindings = Collections.EMPTY_LIST;

	public EmfDetailsPart(EmfFormEditor<?> parentEditor) {
		_parentEditor = parentEditor;
		_selectedObject = new WritableValue();
	}

	final public void createContents(Composite parent) {

		GridLayoutFactory.fillDefaults().margins(5, 5).applyTo(parent);

		_mainDetailComposite = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(_mainDetailComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(_mainDetailComposite);

		createSpecificContent(_mainDetailComposite);

		getEditor().getToolkit().adapt(_mainDetailComposite);

		_bindings = bind(getEditor().getDataBindingContext());
	}

	public void addMessagesFromMarkers(List<Binding> bindings, Object unbound) {
		IMessageManager messageManager = getEditor().getActivePageInstance().getManagedForm().getMessageManager();

		for (IMarker marker : getEditor().getMarkers()) {
			EObject eObject = getEditor().getEObjectFrom(marker);
			if (eObject != null) {
				List<Binding> relatedBindings = findBindingsFor(eObject, bindings);

				String message = marker.getAttribute(IMarker.MESSAGE, null);
				int severity = mapToMessageSeverity(marker);

				// TODO: display more than one message per eobject for the unbound case
				if (eObject == unbound) {
					messageManager.addMessage(unbound, message, null, severity);
				}

				for (Binding binding : relatedBindings) {
					if (binding.getTarget() instanceof ISWTObservable) {
						ISWTObservable swtObservable = (ISWTObservable) binding.getTarget();
						if (swtObservable.getWidget() instanceof Control) {
							// TODO: display more than one message per eobject for bound case.
							messageManager.addMessage(getModelObserved(binding), message, null, severity, (Control) swtObservable.getWidget());
						}
					}

				}
			}
		}
	}

	private List<Binding> findBindingsFor(EObject eObject, List<Binding> bindings) {
		List<Binding> result = new ArrayList<Binding>();
		for (Binding binding : bindings) {
			Object observed = getModelObserved(binding);

			if (observed == eObject)
				result.add(binding);
		}
		return result;
	}

	private Object getModelObserved(Binding binding) {
		Object observed = null;
		if (binding.getModel() instanceof EMFObservableValueDecorator) {
			EMFObservableValueDecorator decorator = (EMFObservableValueDecorator) binding.getModel();
			observed = decorator.getObserved();
		} else if (binding.getModel() instanceof DetailObservableValue) {
			DetailObservableValue observable = (DetailObservableValue) binding.getModel();
			observed = observable.getObserved();
		}
		return observed;
	}

	private int mapToMessageSeverity(IMarker marker) {
		int severity = IMessageProvider.INFORMATION;
		switch (marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR)) {
			case IMarker.SEVERITY_ERROR :
				severity = IMessageProvider.ERROR;
				break;
			case IMarker.SEVERITY_WARNING :
				severity = IMessageProvider.WARNING;
				break;
		}
		return severity;
	}

	protected abstract void createSpecificContent(Composite parent);

	protected abstract List<Binding> bind(DataBindingContext dataBindingContext);

	public void commit(boolean onSave) {
		// nothing
	}

	public void dispose() {
		// nothing
	}

	public void initialize(IManagedForm form) {
		// nothing
	}

	public boolean isDirty() {
		// TODO propose a better impl??
		return false;
	}

	public boolean isStale() {
		// TODO propose a better impl??
		return false;
	}

	public void refresh() {
	}

	public void setFocus() {
		_mainDetailComposite.setFocus();
	}

	public boolean setFormInput(Object input) {
		return false;
	}

	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection sel = (IStructuredSelection) selection;

		if (!sel.isEmpty()) {
			Object unwrapped = AdapterFactoryEditingDomain.unwrap(sel.getFirstElement());
			getCurrentSelection().setValue(unwrapped);
			addMessagesFromMarkers(_bindings, unwrapped);
		}
	}

	protected EditingDomain getEditingDomain() {
		return getEditor().getEditingDomain();
	}

	protected FormToolkit getToolkit() {
		return getEditor().getToolkit();
	}

	public IObservableValue getCurrentSelection() {
		return _selectedObject;
	}

	public EmfFormEditor<?> getEditor() {
		return _parentEditor;
	}

}
