package org.eclipse.pde.ds.ui.internal.handlers;

import org.eclipse.core.commands.*;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class AddScrBuilderHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public AddScrBuilderHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection sel = HandlerUtil.getCurrentSelection(event);
		if (!sel.isEmpty()) {
			if (sel instanceof IStructuredSelection) {
				IProject project = (IProject) ((IStructuredSelection) sel).getFirstElement();

				IProjectDescription projectDescription = null;
				try {
					projectDescription = project.getDescription();
				} catch (CoreException e) {
					// TODO handle exception ...
				}

				ICommand[] buildCommands = projectDescription.getBuildSpec();
				ICommand[] newBuildCommands = new ICommand[buildCommands.length + 1];
				System.arraycopy(buildCommands, 0, newBuildCommands, 0, buildCommands.length);
				ICommand buildCommand = projectDescription.newCommand();
				buildCommand.setBuilderName("org.eclipse.pde.ds.builder.scrBuilder"); //$NON-NLS-1$
				newBuildCommands[buildCommands.length] = buildCommand; //$NON-NLS-1$
				projectDescription.setBuildSpec(newBuildCommands);

				try {
					project.setDescription(projectDescription, IResource.FORCE, null);
					project.build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
				} catch (CoreException e) {
					// TODO handle exception ...
				}
			}
		}
		return null;
	}
}
