/**
 * LearnPAd - Verification Component
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

package eu.learnpad.verification.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import eu.learnpad.verification.plugin.interfaces.Plugin;
import eu.learnpad.verification.utils.Utils;

public class PluginManager {

    private static String defaultPluginFolder = "VerificationComponentPlugins/";
    
    private static String checkFolder(String folder) throws Exception{
        if(folder == null || folder.isEmpty()){
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
            if(!pluginFile.getPath().endsWith(".jar"))
                continue;
            try{
                ClassLoader pluginCL = URLClassLoader.newInstance(new URL[] { pluginFile.toURI().toURL() });
                JarFile jar = new JarFile(pluginFile);
                Manifest manifest = jar.getManifest();
                jar.close();
                String className = manifest.getMainAttributes().getValue(manifestEntry);
                Plugin plugin = (Plugin) pluginCL.loadClass(className).newInstance();
                if(plugin==null)
                    throw new Exception("Plugin class loader create null plugin");
                puginsInterfaceList.add(plugin);
            }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
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
            for(String ver: verList){
                if(ver.equals("ALL"))
                    throw new Exception("A plugin can not provide a verification of type: ALL");
                if(!ret.containsKey(ver))
                    ret.put(ver, plugin);
            }
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
