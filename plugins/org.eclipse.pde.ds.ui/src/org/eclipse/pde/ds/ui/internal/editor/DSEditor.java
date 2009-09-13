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
 * $Id: DSEditor.java,v 1.12 2009/09/13 18:04:02 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.resources.*;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.pde.ds.scr.Component;
import org.eclipse.pde.ds.scr.provider.ScrItemProviderAdapterFactory;
import org.eclipse.pde.ds.ui.internal.Activator;
import org.eclipse.pde.emfforms.editor.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PartInitException;

/**
 * The FormEditor defining the pages allowing to edit a {@link Component}
 */
public class DSEditor extends EmfFormEditor<Component> implements IResourceChangeListener {

	public static String ID = "ds.DSEditor";

	@Override
	protected DefaultEmfFormEditorConfig getFormEditorConfig() {
		return new DSEditorConfig(this);
	}

	@Override
	public String getPartName() {
		return getEditorInput().getName() + " (experimental)"; //$NON-NLS-1$
	}

	@Override
	public void createModel() {
		super.createModel();

		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	@Override
	protected List<AbstractEmfFormPage> getPagesToAdd() throws PartInitException {
		List<AbstractEmfFormPage> pages = new ArrayList<AbstractEmfFormPage>(1);

		pages.add(new OverviewPage(this));
		pages.add(new PropertiesPage(this));
		pages.add(new ServicesPage(this));
		try {
			if (false)
				pages.add(new SourcePage(this));
		} catch (NoClassDefFoundError ex) {
			Activator.logErrorMessage("WTP SSE not available... No source page at the moment"); //$NON-NLS-1$
		}

		return pages;
	}

	@Override
	protected List<Image> getPagesImages() {
		ArrayList<Image> list = new ArrayList<Image>(1);
		list.add(null);
		return list;
	}

	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}

	@Override
	protected AdapterFactory getSpecificAdapterFactory() {
		return new ScrItemProviderAdapterFactory();
	}

	@Override
	public String getID() {
		return ID;
	}
}
