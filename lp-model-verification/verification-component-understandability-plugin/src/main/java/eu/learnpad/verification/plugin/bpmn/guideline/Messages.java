package eu.learnpad.verification.plugin.bpmn.guideline;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("en","US"));

	private static final ResourceBundle RESOURCE_BUNDLE_IT = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("it","IT"));

	
	private Messages() {
	}

	public static String getString(String key, Locale l) {
		try {
			if(l.equals(Locale.ITALIAN)){
				return RESOURCE_BUNDLE_IT.getString(key);
			}
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
