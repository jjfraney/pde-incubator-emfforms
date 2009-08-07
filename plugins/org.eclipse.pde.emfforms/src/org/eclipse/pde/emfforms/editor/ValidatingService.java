package org.eclipse.pde.emfforms.editor;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.ui.forms.IMessageManager;

public interface ValidatingService {

	public static final KeyMap keyMap = new KeyMap();

	static class KeyMap {
		private Map<Integer, Integer> keymap = new HashMap<Integer, Integer>();

		protected KeyMap() {
			keymap.put(Integer.valueOf(IStatus.ERROR), Integer.valueOf(IMessageProvider.ERROR));
			keymap.put(Integer.valueOf(IStatus.INFO), Integer.valueOf(IMessageProvider.WARNING));
			keymap.put(Integer.valueOf(IStatus.INFO), Integer.valueOf(IMessageProvider.INFORMATION));
			keymap.put(Integer.valueOf(IStatus.OK), Integer.valueOf(IMessageProvider.NONE));
			keymap.put(Integer.valueOf(IStatus.CANCEL), Integer.valueOf(IMessageProvider.INFORMATION));
		}

		public int getMessageProviderKey(int iStatusKey) {
			return keymap.get(Integer.valueOf(iStatusKey)).intValue();
		}
	}

	void analyzeDiagnostic(DataBindingContext dataBindingContext, Diagnostic diagnostic, IMessageManager messageManager);
}
