package eu.learnpad.simulator.mon.alerts;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class AbstractParser {

	 @SuppressWarnings("deprecation")
	protected String readTextFromFile(String filePath)
		{
			File file = new File(filePath);
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;
			StringBuilder strB = new StringBuilder();
					
			try	{
				fis = new FileInputStream(file);
			
				// Here BufferedInputStream is added for fast reading.
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);
					 
				while (dis.available() != 0) {
				  // this statement reads the line from the file and print it to
					 // the console.
		    	strB.append(dis.readLine());
		    	
		    	}
				// dispose all the resources after using them.
				fis.close();
				bis.close();
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return strB.toString();
		}
	
	protected String convertSpecialChars(String inputString) {
		String tmp = inputString.replace("\n", "").replace("\r", "");
		tmp = tmp.replaceAll("&lt;", "<");
		tmp = tmp.replaceAll("&gt;", ">");
//		tmp = tmp.trim();
		return tmp;
	}

	public abstract void process();		

}
