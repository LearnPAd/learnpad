package eu.learnpad.verification.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ConfigManager {
	
	public String defaultConfigFile = "config.txt";
	private java.util.HashMap<String,String> elementList = null;
	

	public ConfigManager() throws Exception {
		elementList = new HashMap<String, String>();

		BufferedReader input = new BufferedReader(new InputStreamReader(ConfigManager.class.getResourceAsStream(defaultConfigFile)));
		String strLine;
		while ((strLine = input.readLine()) != null)
			if(strLine.contains("=") && !strLine.startsWith("//") && !strLine.startsWith("'") && !strLine.startsWith("#"))
				elementList.put(strLine.substring(0, strLine.indexOf("=")), strLine.substring(strLine.indexOf("=") + 1, strLine.length()));
		input.close();
	}
	
	public String getElement(String element){
		if(elementList.containsKey(element))
			return elementList.get(element);
		return "";
	}
}
