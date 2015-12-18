package eu.learnpad.ca.analysis.localizzation;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.languagetool.Language;

public class Messages {
	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle("localizzation/"+BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key, Language lang) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
