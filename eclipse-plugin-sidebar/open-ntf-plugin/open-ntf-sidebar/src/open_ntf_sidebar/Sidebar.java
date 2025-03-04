package open_ntf_sidebar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class Sidebar extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		setPartName("Sample Sidebar Plugin");

		parent.setLayout(new GridLayout(1, true));
		Browser browser = new Browser(parent, SWT.EDGE);

		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		browser.setUrl("https://www.google.com");
		browser.setJavascriptEnabled(true);
	}

	@Override
	public void setFocus() {
	}

}
