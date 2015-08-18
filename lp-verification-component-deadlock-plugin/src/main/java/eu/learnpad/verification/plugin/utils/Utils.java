package eu.learnpad.verification.plugin.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
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
			
			String callerClassName = new Exception().getStackTrace()[1].getClassName();
			IOUtils.writeFile(("\n"+logType.toString()+" "+getUTCTime()+" "+callerClassName+" \n"+message).getBytes(), logFile, true);
		}catch(Exception ex){ex.printStackTrace();}
	}
}
