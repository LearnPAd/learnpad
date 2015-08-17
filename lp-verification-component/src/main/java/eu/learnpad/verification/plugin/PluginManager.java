package eu.learnpad.verification.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import eu.learnpad.verification.plugin.interfaces.Plugin;

public class PluginManager {

	private static String defaultPluginFolder = "LPVerificationComponentPlugins/";
	
	private static String checkFolder(String folder) throws Exception{
		if(folder == null || folder.equals("")){
			String folderPath = PluginManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			if(new File(folderPath).isDirectory())
				folderPath = folderPath.substring(0, folderPath.length()-1);
			folder = folderPath.substring(0, folderPath.lastIndexOf("/")+1) + defaultPluginFolder;
		}
		return folder;
	}
	
	public static Plugin[] getPlugins(String pluginsFolderPath) throws Exception{

		pluginsFolderPath = checkFolder(pluginsFolderPath);
		
		File pluginsPath = new File(pluginsFolderPath);
		if(!pluginsPath.exists())
			pluginsPath.mkdirs();
		
		String manifestEntry = "Verification-Class";
		ArrayList<Plugin> puginsInterfaceList = new ArrayList<Plugin>();
		
		
		for(File pluginFile: pluginsPath.listFiles()){
			ClassLoader pluginCL = URLClassLoader.newInstance(new URL[] { pluginFile.toURI().toURL() });
			try{
				JarFile jar = new JarFile(pluginFile);
				Manifest manifest = jar.getManifest();
				jar.close();
				String className = manifest.getMainAttributes().getValue(manifestEntry);
				Plugin plugin = (Plugin) pluginCL.loadClass(className).newInstance();
				puginsInterfaceList.add(plugin);
			}catch(Exception ex){ex.printStackTrace();/*TODO:implementare log*/}
		}
		
		Plugin[] ret = new Plugin[puginsInterfaceList.size()];
		puginsInterfaceList.toArray(ret);
		return ret;
	}
	
	public static HashMap<String, Plugin> getAvailableVerifications(String pluginsFolderPath) throws Exception{
		pluginsFolderPath = checkFolder(pluginsFolderPath);
		HashMap<String, Plugin> ret = new HashMap<String, Plugin>();
		Plugin[] pluginList = getPlugins(pluginsFolderPath);
		for(Plugin plugin: pluginList){
			String[] verList = plugin.getVerificationTypeProvided();
			for(String ver: verList)
				if(!ret.containsKey(ver))
					ret.put(ver, plugin);
		}
		return ret;
	}
	
	/*
	public static void main(String[] args) {
		try{
			String pluginsFolderPath = null;
			
			Plugin[] plugList = getPlugins(pluginsFolderPath);
			for(Plugin plugin : plugList)
				System.out.println(plugin.getVerificationTypeProvided()[0]);
		}catch(Exception ex){ex.printStackTrace();}
	}
	*/
}
