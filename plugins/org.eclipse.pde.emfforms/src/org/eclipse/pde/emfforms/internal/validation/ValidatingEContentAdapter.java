package org.eclipse.pde.emfforms.internal.validation;

import java.util.*;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessageManager;

public class ValidatingEContentAdapter extends EContentAdapter {
	private DataBindingContext _dataBindingContext;
	private EmfFormEditor<?> _formEditor;
	private IObservableValue _observedValue;
	private Diagnostician _diagnostician;

	private static final KeyMap keyMap = new KeyMap();

	public ValidatingEContentAdapter(IObservableValue observedValue, DataBindingContext dataBindingContext, EmfFormEditor<?> formEditor) {
		_formEditor = formEditor;
		_dataBindingContext = dataBindingContext;
		_observedValue = observedValue;

		// Subclass the default Diagnostician to customize the String
		// representation of each validated EObject
		_diagnostician = new Diagnostician() {
			@Override
			public String getObjectLabel(EObject eObject) {
				if (!eObject.eIsProxy()) {
					IItemLabelProvider itemLabelProvider = (IItemLabelProvider) _formEditor.getAdapterFactory().adapt(eObject, IItemLabelProvider.class);
					if (itemLabelProvider != null) {
						return itemLabelProvider.getText(eObject);
					}
				}

				return super.getObjectLabel(eObject);
			}
		};

	}

	@Override
	public void notifyChanged(Notification notification) {
		validate();
	}

	public void validate() {
		IMessageManager messageManager = _formEditor.getActivePageInstance().getManagedForm().getMessageManager();
		messageManager.removeAllMessages();
		messageManager.setAutoUpdate(false);

		Diagnostic diagnostics = _diagnostician.validate((EObject) _observedValue.getValue());

		for (Diagnostic diagnostic : diagnostics.getChildren()) {
			analyzeDiagnostic(diagnostic, messageManager);
		}

		messageManager.update();
	}

	private void analyzeDiagnostic(Diagnostic diagnostic, IMessageManager messageManager) {
		for (Object o : _dataBindingContext.getBindings()) {
			Binding binding = (Binding) o;
			if (binding.getModel() instanceof IEMFObservable && binding.getTarget() instanceof ISWTObservable) {
				checkBinding((IEMFObservable) binding.getModel(), (ISWTObservable) binding.getTarget(), diagnostic, messageManager);
			} else if (binding.getTarget() instanceof IEMFObservable && binding.getModel() instanceof ISWTObservable) {
				checkBinding((IEMFObservable) binding.getTarget(), (ISWTObservable) binding.getModel(), diagnostic, messageManager);
			} else {
				// add an error message anyways
				messageManager.addMessage(diagnostic, diagnostic.getMessage(), null, keyMap.getMessageProviderKey(diagnostic.getSeverity()));

			}
		}
	}

	private void checkBinding(IEMFObservable emfObservable, ISWTObservable swtObservable, Diagnostic diagnostic, IMessageManager messageManager) {
		List<?> diagnosticData = diagnostic.getData();
		if (diagnosticData.size() >= 2) {
			if (diagnosticData.get(0) == emfObservable.getObserved()) {
				if (diagnosticData.get(1) == emfObservable.getStructuralFeature()) {
					if (swtObservable.getWidget() instanceof Control) {
						Control control = (Control) swtObservable.getWidget();

						if (true || control.isVisible()) {
							messageManager.addMessage(swtObservable, diagnostic.getMessage(), null, keyMap.getMessageProviderKey(diagnostic.getSeverity()), control);
						} else
							messageManager.addMessage(swtObservable, diagnostic.getMessage(), null, keyMap.getMessageProviderKey(diagnostic.getSeverity()));
					}
				}
			}
		}
	}

	protected static class KeyMap {
		private Map<Integer, Integer> keymap = new HashMap<Integer, Integer>();

		protected KeyMap() {
			keymap.put(Integer.valueOf(IStatus.ERROR), Integer.valueOf(IMessageProvider.ERROR));
			keymap.put(Integer.valueOf(IStatus.INFO), Integer.valueOf(IMessageProvider.WARNING));
			keymap.put(Integer.valueOf(IStatus.INFO), Integer.valueOf(IMessageProvider.INFORMATION));
			keymap.put(Integer.valueOf(IStatus.OK), Integer.valueOf(IMessageProvider.NONE));
			keymap.put(Integer.valueOf(IStatus.CANCEL), Integer.valueOf(IMessageProvider.INFORMATION));
		}

		protected int getMessageProviderKey(int iStatusKey) {
			return keymap.get(Integer.valueOf(iStatusKey)).intValue();
		}
	}

}
