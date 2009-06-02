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
 * $Id: EmfActionBarContributor.java,v 1.1 2009/06/02 09:06:04 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;

public class EmfActionBarContributor extends EditingDomainActionBarContributor implements ISelectionChangedListener {

	private MenuManager createChildMenuManager;
	private IEditorPart activeEditorPart;
	private ISelectionProvider selectionProvider;
	private Collection<IAction> createActions;

	protected IAction showPropertiesViewAction = new Action("Open properties") {
		@Override
		public void run() {
			try {
				getPage().showView("org.eclipse.ui.views.PropertySheet");
			} catch (PartInitException exception) {
				exception.printStackTrace();
			}
		}
	};

	private IFilter filter = new IFilter() {

		public boolean select(Object toTest) {
			return true;
		}

	};

	public EmfActionBarContributor() {
		super(ADDITIONS_LAST_STYLE);
		loadResourceAction = new LoadResourceAction();
		validateAction = new ValidateAction();
		controlAction = new ControlAction();
	}

	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		IMenuManager submenuManager = new MenuManager("", "libraryMenuID");
		menuManager.insertAfter("additions", submenuManager);
		submenuManager.add(new Separator("settings"));
		submenuManager.add(new Separator("actions"));
		submenuManager.add(new Separator("additions"));
		submenuManager.add(new Separator("additions-end"));

		// Prepare for CreateChild item addition or removal.
		createChildMenuManager = new MenuManager("New");
		submenuManager.insertBefore("additions", createChildMenuManager);

		// Force an update because Eclipse hides empty menus now.
		submenuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager menuManager) {
				menuManager.updateAll(true);
			}
		});
		addGlobalActions(submenuManager);
	}

	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);
		activeEditorPart = part;

		// Switch to the new selection provider.
		if (selectionProvider != null) {
			selectionProvider.removeSelectionChangedListener(this);
		}
		if (part == null) {
			selectionProvider = null;
		} else {
			selectionProvider = part.getSite().getSelectionProvider();
			selectionProvider.addSelectionChangedListener(this);

			// Fake a selection changed event to update the menus.
			if (selectionProvider.getSelection() != null) {
				selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
			}
		}
	}

	public void selectionChanged(SelectionChangedEvent event) {
		// Remove any menu items for old selection.
		if (createChildMenuManager != null) {
			depopulateManager(createChildMenuManager, createActions);
		}

		// Query the new selection for appropriate new child/sibling descriptors
		Collection<?> newChildDescriptors = null;
		Collection<?> newSiblingDescriptors = null;

		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
			Object object = ((IStructuredSelection) selection).getFirstElement();

			EditingDomain domain = ((IEditingDomainProvider) activeEditorPart).getEditingDomain();
			newChildDescriptors = domain.getNewChildDescriptors(object, null);
			newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
		}

		// Generate actions for selection; populate and redraw the menus.
		createActions = generateCreateActions(newChildDescriptors, newSiblingDescriptors, selection);

		if (createChildMenuManager != null) {
			populateManager(createChildMenuManager, createActions, null);
			createChildMenuManager.update(true);
		}

	}

	private void populateManager(MenuManager manager, Collection<? extends IAction> actions, String contributionID) {
		if (actions != null) {
			for (IAction action : actions) {
				if (contributionID != null) {
					manager.insertBefore(contributionID, action);
				} else {
					manager.add(action);
				}
			}
		}
	}

	protected Collection<IAction> generateCreateActions(Collection<?> childDescriptors, Collection<?> simblingDescriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (childDescriptors != null) {
			for (Object descriptor : childDescriptors) {
				if (this.filter.select(((CommandParameter) descriptor).value))
					actions.add(new CreateChildAction(activeEditorPart, selection, descriptor));
			}
		}
		if (simblingDescriptors != null) {
			for (Object descriptor : simblingDescriptors) {
				if (this.filter.select(((CommandParameter) descriptor).value))
					actions.add(new CreateSiblingAction(activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	protected void depopulateManager(IContributionManager manager, Collection<? extends IAction> actions) {
		if (actions != null) {
			IContributionItem[] items = manager.getItems();
			for (int i = 0; i < items.length; i++) {
				// Look into SubContributionItems
				IContributionItem contributionItem = items[i];
				while (contributionItem instanceof SubContributionItem) {
					contributionItem = ((SubContributionItem) contributionItem).getInnerItem();
				}

				// Delete the ActionContributionItems with matching action.
				if (contributionItem instanceof ActionContributionItem) {
					IAction action = ((ActionContributionItem) contributionItem).getAction();
					if (actions.contains(action)) {
						manager.remove(contributionItem);
					}
				}
			}
		}
	}

	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		super.menuAboutToShow(menuManager);
		MenuManager submenuManager = null;

		submenuManager = new MenuManager("New");
		populateManager(submenuManager, createActions, null);
		menuManager.insertBefore("edit", submenuManager);
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}
}
