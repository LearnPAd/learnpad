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

package eu.learnpad.verification;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import eu.learnpad.verification.plugin.PluginManager;
import eu.learnpad.verification.plugin.interfaces.Plugin;
import eu.learnpad.verification.utils.IOUtils;
import eu.learnpad.verification.utils.ConfigManager;
import eu.learnpad.verification.utils.Utils;
import eu.learnpad.verification.utils.XMLUtils;

/**
 * This class expose all the API you may need in order to verify a model
 * 
 */
public class VerificationComponent {

    private static ArrayList<String> verificationRunningList = new ArrayList<String>();
    private static HashMap<String, String[]> loadedModelList = new HashMap<String, String[]>();

    private static CustomGetModel _customGetModel = null;
    private static CustomNotify _customNotify = null;
    
    /**
     * This interface have to be implemented if you want to use your own method of model retrieval from a given model id.
     */
    public interface CustomGetModel{ public String[] getModels(String modelSetId) throws Exception; }
    /**
     * This interface have to be implemented if you want to be notified about the end of a verification identified by the given verification id.
     */
    public interface CustomNotify{ public void notifyVerificationEnd(String verificationId) throws Exception; }
    
    /**
     * This method return an array of string identifying all the available verification types provided by all the plugins
     * @return String[] array of string identifying all the available verification types
     */
    public static String[] getSupportedVerifications() throws Exception{
        String[] ret = _getSupportedVerifications();
        System.gc();
        return ret;
    }
    private static String[] _getSupportedVerifications() throws Exception{
        String pluginsFolderPath = new ConfigManager().getElement("pluginsFolderPath");
        HashMap<String, Plugin> verificationMap = PluginManager.getAvailableVerifications(pluginsFolderPath);
        String[] ret = new String[verificationMap.keySet().size()];
        verificationMap.keySet().toArray(ret);
        return ret;
    }
    /**
     * This method load a model in the component and return an id specific for the model that have to be used for the verification
     * @param model The model to load. It accepts any kind of model formats.
     * @return String an unique id associated to the model
     */
    public static String loadModel(String model){
        String mid = java.util.UUID.randomUUID() + "";
        loadedModelList.put(mid, new String[]{model});
        return mid;
    }
    
    /**
     * This method load a model in the component and return an id specific for the modelSet that have to be used for the verification
     * @param modelSet The modelSet to load. It accepts any kind of model formats.
     * @return String an unique id associated to the modelSet
     */
    public static String loadModel(String[] modelSet){
        String mid = java.util.UUID.randomUUID() + "";
        loadedModelList.put(mid, modelSet);
        return mid;
    }
    
    /**
     * This method start a specific verification of a model.
     * @param modelId The model id associated to the model to verify. The model is first searched on the loaded models. If it is not found and a custom getModel function have been provided (through the method setGetModelFunctionFromLP) it will be searched on the custom method.
     * @param verificationType The type of verification to perform (is one of the type returned by the function getSupportedVerifications )
     * @return String An unique id associated to the verification
     */
    public static String startVerification(final String modelId, final String verificationType){
        
        final String vid = java.util.UUID.randomUUID() + "";
        verificationRunningList.add(vid);
        
        Thread verificationThread = new Thread() {
            public void run() {
                verificationThread(vid, modelId, verificationType);
            };
        };
        verificationThread.start();
        
        return vid;
    }
    
    /**
     * This method perform a specific verification of a model in a synchronous way, waiting for its termination
     * @param modelId The model id associated to the model to verify. The model is first searched on the loaded models. If it is not found and a custom getModel function have been provided (through the method setGetModelFunctionFromLP) it will be searched on the custom method.
     * @param verificationType The type of verification to perform (is one of the type returned by the function getSupportedVerifications )
     * @return String An unique id associated to the verification
     */
    public static String startSyncVerification(String modelId, String verificationType){
        String vid = java.util.UUID.randomUUID() + "";
        verificationRunningList.add(vid);
        verificationThread(vid, modelId, verificationType);
        return vid;
    }
    
    /**
     * This method return the status of a model verification
     * @param verificationId The verification id as returned by the startVerification method
     * @return String The status of the verification. It can be: "IN PROGRESS", "COMPLETED" or "NEVER STARTED"
     */
    public static String getVerificationStatus(String verificationId) throws Exception{
        if(verificationRunningList.contains(verificationId))
            return "IN PROGRESS";
        
        String resultsFolderPath = new ConfigManager().getElement("resultsFolderPath");
        resultsFolderPath = checkResultsFolder(resultsFolderPath);
        
        File resultsFolderPathFile = new File(resultsFolderPath);
        if(!resultsFolderPathFile.exists())
            resultsFolderPathFile.mkdirs();
        for(File resultFile: resultsFolderPathFile.listFiles())
            if(resultFile.getName().equals(verificationId))
                return "COMPLETED";
        
        return "NEVER STARTED";
    }
    
    /**
     * This method return the result of a completed verification
     * @param verificationId The verification id as returned by the startVerification method
     * @return String The result of the verification. It is an xml in the following format:
     * <pre>
     * {@code
 <VerificationResults>
   <VerificationType>...</VerificationType>
   <VerificationID>...</VerificationID>
   <ModelID>...</ModelID>
   <FinalResult>..OK o KO..</FinalResult>
   <Time> ...UTC Time...</Time>
   <Results>
     ...plugin output...
   </Results>
 </VerificationResults>
         }
     * </pre>
     */
    public static String getVerificationResult(String verificationId) throws Exception{
        
        String status = getVerificationStatus(verificationId);
        if(!status.equals("COMPLETED"))
            throw new Exception("ERROR: The status of the verification with id " + verificationId + " is " + status);
        
        System.gc();
        
        String resultsFolderPath = new ConfigManager().getElement("resultsFolderPath");
        resultsFolderPath = checkResultsFolder(resultsFolderPath);
        
        File resultsFolderPathFile = new File(resultsFolderPath);
        for(File resultFile: resultsFolderPathFile.listFiles())
            if(resultFile.getName().equals(verificationId))
                return new String(IOUtils.readFile(resultFile));
        throw new Exception("ERROR: Can not retrive results of the verification with id " + verificationId);
    }
    
    /**
     * This method set the custom class defined to retrieve a model from a given id
     * @param customGetModel The custom class implementing the CustomGetModel interface
     */
    public static void setCustomGetModelFunction(CustomGetModel customGetModel){
        _customGetModel = customGetModel;
    }
    
    /**
     * This method set the custom class defined to notify the end of a verification
     * @param customNotify The custom class implementing the CustomNotify interface
     */
    public static void setCustomNotifyVerificationEndFunction(CustomNotify customNotify){
        _customNotify = customNotify;
    }
    
    private static void verificationThread(String vid, String modelId, String verificationType){
        _verificationThread(vid, modelId, verificationType);
        System.gc();
    }

    private static void _verificationThread(String vid, String modelId, String verificationType){
        try{
            String pluginsFolderPath = new ConfigManager().getElement("pluginsFolderPath");
            String resultsFolderPath = new ConfigManager().getElement("resultsFolderPath");
            resultsFolderPath = checkResultsFolder(resultsFolderPath);
            File resultsFolderPathFile = new File(resultsFolderPath);
            if(!resultsFolderPathFile.exists())
                resultsFolderPathFile.mkdirs();
            
            HashMap<String, Plugin> verificationMap = PluginManager.getAvailableVerifications(pluginsFolderPath);
            String[] modelList = getModels(modelId);
            String result = "";
            
            if(verificationType.equals("ALL")){
                for(String model: modelList)
                    for(String pluginVerificationType:verificationMap.keySet())
                        result += verificationMap.get(pluginVerificationType).performVerification(model, pluginVerificationType);
            } else {
                Plugin verificationEngine = verificationMap.get(verificationType);
                if(verificationEngine == null)
                    throw new Exception("ERROR: Impossible to find a plugin for the verification type: " + verificationType);
                for(String model: modelList)
                    result += verificationEngine.performVerification(model, verificationType);
            }
            
            verificationMap = null;
            if(result.isEmpty())
                result = "<ErrorResult><Status>ERROR</Status><Description>The "+verificationType+" verificator returned an empty response</Description></ErrorResult>";
            String finalResult = "OK";
            if(result.contains("<Status>ERROR</Status>") || result.contains("<Status>KO</Status>"))
                finalResult = "KO";
            String resultXml = "<VerificationResults><VerificationType>"+verificationType+"</VerificationType><VerificationID>"+vid+"</VerificationID><ModelID>"+modelId+"</ModelID><FinalResult>"+finalResult+"</FinalResult><Time>"+Utils.getUTCTime()+"</Time><Results>"+result+"</Results></VerificationResults>";
            try{
                XMLUtils.getXmlDocFromString(resultXml);
            }catch(Exception e){ throw new Exception("ERROR: The result is not a valid XML:\n"+resultXml);}
            IOUtils.writeFile(resultXml.getBytes(), resultsFolderPath + File.separator + vid, false);
            notifyVerificationEnd(vid);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        verificationRunningList.remove(vid);
    }
    
    private static void notifyVerificationEnd(String verificationId) throws Exception{
        if(_customNotify!=null)
            _customNotify.notifyVerificationEnd(verificationId);
    }
    
    private static String[] getModels(String modelId) throws Exception{
        String[] model = loadedModelList.get(modelId);
        if(model==null || model.length==0) {
            if(_customGetModel==null)
                throw new Exception("ERROR: can not retrive model with id " + modelId + "; customGetModel not defined. Please use the setCustomGetModelFunction function first");
            model = _customGetModel.getModels(modelId);
            if(model==null || model.length==0)
                throw new Exception("ERROR: can not retrive model with id " + modelId);
        }
        return model;
    }
    
    private static String checkResultsFolder(String folder) throws Exception{
        if(folder == null || folder.isEmpty()){
            String folderPath = VerificationComponent.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if(new File(folderPath).isDirectory())
                folderPath = folderPath.substring(0, folderPath.length()-1);
            folder = folderPath.substring(0, folderPath.lastIndexOf("/")+1) + "VerificationComponentResults/";
        }
        return folder;
    }
    
    /*
    public static void main(String[] args) {
        try{
            System.out.println(VerificationComponent.getSupportedVerifications()[1]);
            String model = new String(IOUtils.readFile("D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_3.bpmn"));
            //String model = new String(IOUtils.readFile("D:\\TOOLS\\curlData.xml"));
            String mid = VerificationComponent.loadModel(model);
            String vid = VerificationComponent.startSyncVerification(mid, VerificationComponent.getSupportedVerifications()[1]);
            System.out.println(VerificationComponent.getVerificationStatus(vid));
            try {
                Thread.sleep(1000*3);
            } catch (InterruptedException e) {}
            System.out.println(VerificationComponent.getVerificationStatus(vid));
            System.out.println(VerificationComponent.getVerificationResult(vid));
        }catch(Exception ex){ex.printStackTrace();}
    }
    */
}
