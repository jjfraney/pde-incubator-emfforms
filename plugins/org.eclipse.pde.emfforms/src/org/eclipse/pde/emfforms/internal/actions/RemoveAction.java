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
 * $Id: RemoveAction.java,v 1.2 2009/08/20 17:22:09 bcabe Exp $
 */
package org.eclipse.pde.emfforms.internal.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.pde.emfforms.editor.EmfMasterDetailBlock;
import org.eclipse.pde.emfforms.editor.actions.AbstractRemoveAction;

public class RemoveAction extends AbstractRemoveAction {

	public RemoveAction(EmfMasterDetailBlock masterDetail) {
		super(masterDetail);
	}

	protected void removeObject(EObject sel, EditingDomain editingDomain) {
		Command c = RemoveCommand.create(editingDomain, sel);
		editingDomain.getCommandStack().execute(c);
	}

}
