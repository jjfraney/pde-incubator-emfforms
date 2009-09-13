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
 * $Id: RichTooltipHyperlinkAdapter.java,v 1.1 2009/09/13 20:32:58 bcabe Exp $
 */
package org.eclipse.pde.emfforms.internal.validation;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormText;

public class RichTooltipHyperlinkAdapter extends HyperlinkAdapter {

	private static final String INFO = "info";
	private static final String WARNING = "warning";
	private static final String ERROR = "error";
	private static final String OTHER_ERRORS = "Other errors";
	private IManagedForm _form;
	private Shell _shell;

	public RichTooltipHyperlinkAdapter(IManagedForm form) {
		_form = form;
	}

	public void linkActivated(HyperlinkEvent e) {
		if (_shell != null) {
			_shell.dispose();
			_shell = null;
			return;
		}

		String title = e.getLabel();
		// String details = title;
		Object href = e.getHref();
		Point hl = ((Control) e.widget).toDisplay(0, 0);
		hl.x += 10;
		hl.y += 20;
		_shell = new Shell(_form.getForm().getShell(), SWT.ON_TOP | SWT.TOOL);
		_shell.setImage(getImage(_form.getForm().getMessageType()));
		_shell.setText(title);
		GridLayoutFactory.fillDefaults().margins(10, 10).applyTo(_shell);
		FormText text = _form.getToolkit().createFormText(_shell, true);
		Label l = new Label(text, SWT.SEPARATOR | SWT.HORIZONTAL);
		text.setControl("sep", l);

		GridDataFactory.fillDefaults().applyTo(text);
		if (href instanceof IMessage[]) {
			Map<String, List<IMessage>> messagesMap = mapMessages((IMessage[]) href, text);
			configureFormText(_form.getForm().getForm(), text);
			text.setText(createFormTextContent(messagesMap), true, false);
		}
		_shell.setLocation(hl);
		_form.getToolkit().adapt(_shell);
		_shell.pack();
		_shell.open();
	}

	private void configureFormText(final Form form, FormText text) {
		text.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				String is = (String) e.getHref();
				try {
					int hashCode = Integer.parseInt(is);
					IMessage[] messages = form.getChildrenMessages();
					IMessage message = null;
					for (IMessage m : messages) {
						if (hashCode == m.hashCode()) {
							message = m;
							break;
						}
					}
					_shell.dispose();
					_shell = null;
					Control c = message.getControl();
					if (c != null) {
						// try to find the formpage to activate it...
						// find CTabItem
						CTabItem item = findCTabItem(c);
						item.getParent().setSelection(item);
						c.setFocus();
					}
				} catch (NumberFormatException ex) {
				}
			}

		});
		text.setImage(ERROR, getImage(IMessageProvider.ERROR));
		text.setImage(WARNING, getImage(IMessageProvider.WARNING));
		text.setImage(INFO, getImage(IMessageProvider.INFORMATION));
	}

	private String createFormTextContent(Map<String, List<IMessage>> messagesMap) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		pw.println("<form>");
		for (Entry<String, List<IMessage>> entry : messagesMap.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				if (OTHER_ERRORS.equals(entry.getKey())) {
					pw.println("<p><control href=\"sep\" fill=\"true\"/></p>");
				}

				pw.println("<li style=\"image\" value=\"" + entry.getKey() + "\"><b>" + entry.getKey() + "</b></li>");
				for (IMessage message : entry.getValue()) {
					pw.print("<li vspace=\"false\" style=\"image\" bindent=\"10\" indent=\"28\" value=\"");
					switch (message.getMessageType()) {
						case IMessageProvider.ERROR :
							pw.print(ERROR);
							break;
						case IMessageProvider.WARNING :
							pw.print(WARNING);
							break;
						case IMessageProvider.INFORMATION :
							pw.print(INFO);
							break;
					}
					pw.print("\"> <a href=\"");
					pw.print(message.hashCode() + "");
					pw.print("\">");
					if (message.getPrefix() != null)
						pw.print(message.getPrefix());
					pw.print(message.getMessage());
					pw.println("</a></li>");
				}
			}
		}
		pw.println("</form>");
		pw.flush();
		//	System.out.println(sw.toString());
		return sw.toString();
	}

	private Map<String, List<IMessage>> mapMessages(IMessage[] messages, FormText text) {
		// find the first message which is associated to a control
		// and then find its CTabFolder...
		List<String> pagesNames = new ArrayList<String>();
		for (IMessage msg : messages) {
			if (msg.getControl() != null) {
				CTabItem item = findCTabItem(msg.getControl());
				for (CTabItem it : item.getParent().getItems()) {
					pagesNames.add(it.getText());
					if (it.getImage() != null) {
						text.setImage(it.getText(), it.getImage());
					}
				}
			}
		}

		Map<String, List<IMessage>> res = new LinkedHashMap<String, List<IMessage>>();
		for (String s : pagesNames) {
			res.put(s, new ArrayList<IMessage>());
		}
		res.put(OTHER_ERRORS, new ArrayList<IMessage>());
		for (IMessage msg : messages) {
			Form container = findFormContainer(msg.getControl());
			String key = OTHER_ERRORS;
			if (container != null)
				key = container.getText();

			res.get(key).add(msg);

		}
		return res;
	}

	private Image getImage(int type) {
		switch (type) {
			case IMessageProvider.ERROR :
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
			case IMessageProvider.WARNING :
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
			case IMessageProvider.INFORMATION :
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		}
		return null;
	}

	private static Form findFormContainer(Control control) {
		if (control == null)
			return null;
		if (control instanceof Form)
			return (Form) control;

		return findFormContainer(control.getParent());
	}

	private static CTabItem findCTabItem(Control c) {
		Form container = findFormContainer(c);
		Composite c1 = container.getParent();
		CTabFolder c2 = (CTabFolder) c1.getParent();

		for (CTabItem item : c2.getItems()) {
			if (item.getControl() == c1) {
				return item;
			}
		}
		return null;

	}
}
