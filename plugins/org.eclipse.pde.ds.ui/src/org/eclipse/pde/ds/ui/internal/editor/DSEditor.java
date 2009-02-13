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
 * $Id: DSEditor.java,v 1.1 2009/02/13 13:26:26 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.pde.ds.scr.Component;
import org.eclipse.pde.ds.scr.provider.ScrItemProviderAdapterFactory;
import org.eclipse.pde.emfforms.editor.AbstractEmfFormPage;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PartInitException;

/**
 * The FormEditor defining the pages allowing to edit a {@link Component}
 */
public class DSEditor extends EmfFormEditor<Component> {

	public DSEditor() {
		super();
	}

	@Override
	public String getPartName() {
		return getEditorInput().getName() + " (experimental)"; //$NON-NLS-1$
	}

	@Override
	protected AdapterFactory getSpecificAdapterFactory() {
		return new ScrItemProviderAdapterFactory();
	}

	@Override
	protected List<AbstractEmfFormPage> getPagesToAdd()
			throws PartInitException {
		List<AbstractEmfFormPage> pages = new ArrayList<AbstractEmfFormPage>(1);

		pages.add(new OverviewPage(this));

		return pages;
	}

	@Override
	protected List<Image> getPagesImages() {
		ArrayList<Image> list = new ArrayList<Image>(1);

		list.add(null);

		return list;
	}

	@Override
	protected VALIDATE_ON_SAVE validateOnSave() {
		return VALIDATE_ON_SAVE.VALIDATE_AND_WARN;
	}

	@Override
	public boolean isUsingSharedClipboard() {
		return true;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		super.doSave(monitor);
	}
}
