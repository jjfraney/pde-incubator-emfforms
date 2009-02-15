package org.eclipse.pde.ds.ui.internal.editor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.pde.ds.ui.internal.editor.messages"; //$NON-NLS-1$

	public static String ComponentComposite_Activate;

	public static String ComponentComposite_Deactivate;

	public static String ComponentComposite_Implementation;
	public static String ComponentComposite_Name;

	public static String OverviewPage_Component_Section;
	public static String OverviewPage_Component_Section_desc;

	public static String OverviewPage_Options_Section;
	public static String OverviewPage_Options_Section_desc;

	public static String OverviewPage_Properties_Section;
	public static String OverviewPage_Properties_Section_desc;

	public static String OverviewPage_Title;

	public static String OptionsComposite_Factory;
	public static String OptionsComposite_ConfigurationPolicy;
	public static String OptionsComposite_Enabled;
	public static String OptionsComposite_Immediate;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
