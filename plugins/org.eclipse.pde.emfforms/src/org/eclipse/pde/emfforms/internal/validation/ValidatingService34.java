package org.eclipse.pde.emfforms.internal.validation;

import java.util.List;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.internal.databinding.observable.masterdetail.DetailObservableValue;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.pde.emfforms.editor.ValidatingService;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessageManager;

public class ValidatingService34 implements ValidatingService {

	public void analyzeDiagnostic(DataBindingContext dataBindingContext, Diagnostic diagnostic, IMessageManager messageManager) {
		boolean atLeastOneErroneousBinding = false;
		for (Object o : dataBindingContext.getBindings()) {
			Binding binding = (Binding) o;
			DetailObservableValue emfObservable = null;
			ISWTObservable swtObservable = null;
			if (binding.getModel() instanceof DetailObservableValue && binding.getTarget() instanceof ISWTObservable) {
				emfObservable = (DetailObservableValue) binding.getModel();
				swtObservable = (ISWTObservable) binding.getTarget();
			} else if (binding.getTarget() instanceof DetailObservableValue && binding.getModel() instanceof ISWTObservable) {
				swtObservable = (ISWTObservable) binding.getModel();
				emfObservable = (DetailObservableValue) binding.getTarget();
			}

			if (emfObservable != null && swtObservable != null)
				if (checkBindingFor34(emfObservable, swtObservable, diagnostic, messageManager))
					atLeastOneErroneousBinding = true;
		}
		if (!atLeastOneErroneousBinding) {
			// add an error message anyways
			messageManager.addMessage(diagnostic, diagnostic.getMessage(), null, keyMap.getMessageProviderKey(diagnostic.getSeverity()));
		}
	}

	private boolean checkBindingFor34(DetailObservableValue emfObservable, ISWTObservable swtObservable, Diagnostic diagnostic, IMessageManager messageManager) {
		List<?> diagnosticData = diagnostic.getData();
		if (diagnosticData.size() >= 2) {
			if (diagnosticData.get(0) == emfObservable.getObserved()) {
				if (diagnosticData.get(1) == emfObservable.getValueType()) {
					if (swtObservable.getWidget() instanceof Control) {
						Control control = (Control) swtObservable.getWidget();

						if (true || control.isVisible())
							messageManager.addMessage(swtObservable, diagnostic.getMessage(), null, keyMap.getMessageProviderKey(diagnostic.getSeverity()), control);
						else
							messageManager.addMessage(swtObservable, diagnostic.getMessage(), null, keyMap.getMessageProviderKey(diagnostic.getSeverity()));

						return true;
					}
				}
			}
		}
		return false;
	}

}
