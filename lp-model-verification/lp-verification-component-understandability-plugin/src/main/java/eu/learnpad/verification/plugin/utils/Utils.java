/**
 * LearnPAd - Verification Component - Deadlock Check Plugin
 * 
 *  Copyright (C) 2015 Unicam
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Damiano Falcioni - Unicam <damiano.falcioni@gmail.com>
 */

package eu.learnpad.verification.plugin.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
	public static String getUTCTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date());
	}
	
	public static Date stringToDate(String dateTime) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.parse(dateTime);
	}
	

	
	public enum LogType{
		INFO, ERROR, DEBUG;
	}
	public static void log(Error e){
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		log(sw.toString(), LogType.ERROR);
	}
	public static void log(Exception e){
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		log(sw.toString(), LogType.ERROR);
	}
	public static void log(String message, LogType logType){
		try{
			String folderPath = Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			if(new File(folderPath).isDirectory())
				folderPath = folderPath.substring(0, folderPath.length()-1);
			String logFile = folderPath.substring(0, folderPath.lastIndexOf("/")+1) + "verification.log";
			//System.err.println("INFO: updated log file " + logFile);
			String callerClassName = new Exception().getStackTrace()[1].getClassName();
			IOUtils.writeFile(("\n"+logType.toString()+" "+getUTCTime()+" "+callerClassName+" \n"+message).getBytes(), logFile, true);
		}catch(Exception ex){ex.printStackTrace();}
	}
}
