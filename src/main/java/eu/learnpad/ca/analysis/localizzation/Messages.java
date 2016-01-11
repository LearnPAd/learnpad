package eu.learnpad.ca.analysis.localizzation;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.languagetool.Language;
import org.languagetool.language.Italian;

public class Messages {
	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle("localizzation/"+BUNDLE_NAME, new Locale("en"));
	private static final ResourceBundle RESOURCE_BUNDLE_IT = ResourceBundle
			.getBundle("localizzation/"+BUNDLE_NAME, new Locale("it"));

	private Messages() {
	}

	public static String getString(String key, Language lang) {
		try {
			if(lang instanceof Italian){
				String res = RESOURCE_BUNDLE_IT.getString(key);	
				return res;
			}
			String res = RESOURCE_BUNDLE.getString(key);
			return res;
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
