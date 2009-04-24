package org.eclipse.pde.ds.ui.internal.editor.composites;

import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.emfforms.editor.PDEFormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class PropertyComposite extends Composite {

	private Text _textName;

	private Text _textValue;

	public PropertyComposite(Composite parent, int style) {
		super(parent, style);
		createContents();
	}

	private void createContents() {
		_textName = PDEFormToolkit.createLabelAndText(Messages.ComponentComposite_Name, this);
		_textValue = PDEFormToolkit.createLabelAndText(Messages.ComponentComposite_Value, this);
	}

	public Text getTextName() {
		return _textName;
	}

	public void setTextName(Text textName) {
		_textName = textName;
	}

	public Text getTextValue() {
		return _textValue;
	}

	public void setTextValue(Text textValue) {
		_textValue = textValue;
	}

}
