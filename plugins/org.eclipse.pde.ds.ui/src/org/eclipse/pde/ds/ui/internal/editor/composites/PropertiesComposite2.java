package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class PropertiesComposite2 extends Composite {

	private Text _textName;

	public PropertiesComposite2(Composite parent, int style) {
		super(parent, style);
		_textName = PDEFormToolkit.createLabelAndText(Messages.ComponentComposite_Name, this);
	}

	public Text get_textName() {
		return _textName;
	}

	public void set_textName(Text textName) {
		_textName = textName;
	}

}
