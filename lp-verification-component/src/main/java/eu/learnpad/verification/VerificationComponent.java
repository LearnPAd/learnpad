package eu.learnpad.verification;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import eu.learnpad.verification.plugin.PluginManager;
import eu.learnpad.verification.plugin.interfaces.Plugin;
import eu.learnpad.verification.utils.IOUtils;
import eu.learnpad.verification.utils.ConfigManager;
import eu.learnpad.verification.utils.Utils;


public class VerificationComponent {

	private static ArrayList<String> verificationRunningList = new ArrayList<String>();
	private static HashMap<String, String> loadedModelList = new HashMap<String, String>();

	private static LPGetModel _lpGetModel = null;
	private static LPNotify _lpNotify = null;
	public interface LPGetModel{ public String getModel(String modelId) throws Exception; }
	public interface LPNotify{ public String notifyVerificationEnd(String verificationId) throws Exception; }
	
	public static String[] getSupportedVerifications() throws Exception{
		String pluginsFolderPath = new ConfigManager().getElement("pluginsFolderPath");
		HashMap<String, Plugin> verificationMap = PluginManager.getAvailableVerifications(pluginsFolderPath);
		String[] ret = new String[verificationMap.keySet().size()];
		verificationMap.keySet().toArray(ret);
		return ret;
	}
	
	public static String loadModel(String model){
		String mid = java.util.UUID.randomUUID() + "";
		loadedModelList.put(mid, model);
		return mid;
	}
	
	public static String startVerification(final String modelId, final String verificationType) throws Exception{
		
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
	
	public static String getVerificationStatus(String verificationId) throws Exception{
		if(verificationRunningList.contains(verificationId))
			return "IN PROGRESS";
		
		String resultsFolderPath = new ConfigManager().getElement("resultsFolderPath");
		resultsFolderPath = checkResultsFolder(resultsFolderPath);
		
		File resultsFolderPathFile = new File(resultsFolderPath);
		if(!resultsFolderPathFile.exists())
			resultsFolderPathFile.mkdirs();
		for(File resultFile: resultsFolderPathFile.listFiles()) //TODO: aggiungere filtro su nome del file
			if(resultFile.getName().equals(verificationId))
				return "COMPLETED";
		
		return "NEVER STARTED";
	}
	
	public static String getVerificationResult(String verificationId) throws Exception{
		/*
		 <VerificationResult>
		 	<VerificationType>...</VerificationType>
		 	<VerificationID>...</VerificationID>
		 	<ModelID>...</ModelID>
		 	<Time> ...UTC Time...</Time>
		 	<Results>
		 		...plugin output...
		 	</Results>
		 </VerificationResult>
		 */
		
		String status = getVerificationStatus(verificationId);
		if(!status.equals("COMPLETED"))
			throw new Exception("ERROR: The status of the verification with id " + verificationId + " is " + status);
		
		String resultsFolderPath = new ConfigManager().getElement("resultsFolderPath");
		resultsFolderPath = checkResultsFolder(resultsFolderPath);
		
		File resultsFolderPathFile = new File(resultsFolderPath);
		for(File resultFile: resultsFolderPathFile.listFiles()) //TODO: aggiungere filtro su nome del file
			if(resultFile.getName().equals(verificationId))
				return new String(IOUtils.readFile(resultFile));
		throw new Exception("ERROR: Can not retrive results of the verification with id " + verificationId);
	}
	
	public static void setGetModelFunctionFromLP(LPGetModel lpGetModel){
		_lpGetModel = lpGetModel;
	}
	
	public static void setNotifyVerificationEndFunctionToLP(LPNotify lpNotify){
		_lpNotify = lpNotify;
	}
	
	private static void verificationThread(String vid, String modelId, String verificationType){
		try{
			String pluginsFolderPath = new ConfigManager().getElement("pluginsFolderPath");
			String resultsFolderPath = new ConfigManager().getElement("resultsFolderPath");
			resultsFolderPath = checkResultsFolder(resultsFolderPath);
			File resultsFolderPathFile = new File(resultsFolderPath);
			if(!resultsFolderPathFile.exists())
				resultsFolderPathFile.mkdirs();
			
			HashMap<String, Plugin> verificationMap = PluginManager.getAvailableVerifications(pluginsFolderPath);
			Plugin verificationEngine = verificationMap.get(verificationType);
			if(verificationEngine == null)
				throw new Exception("ERROR: Impossible to find a plugin for the verification type: " + verificationType);
			
			String model = getModel(modelId);
    		String result = verificationEngine.performVerification(model, verificationType);
    		String resultXml = "<VerificationResult><VerificationType>"+verificationType+"</VerificationType><VerificationID>"+vid+"</VerificationID><ModelID>"+modelId+"</ModelID><Time>"+Utils.getUTCTime()+"</Time><Results>"+result+"</Results></VerificationResult>";
    		
            IOUtils.writeFile(resultXml.getBytes(), resultsFolderPath + File.separator + vid, false);
            notifyVerificationEnd(vid);
		}catch(Exception ex){ex.printStackTrace();/*TODO: write log*/}
		
		verificationRunningList.remove(vid);
	}
	
	private static void notifyVerificationEnd(String verificationId) throws Exception{
		if(_lpNotify!=null)
			_lpNotify.notifyVerificationEnd(verificationId);
	}
	
	private static String getModel(String modelId) throws Exception{
		String model = loadedModelList.get(modelId);
		if(model==null) {
			if(_lpGetModel==null)
				throw new Exception("ERROR: lpGetModel not defined. Please use the setGetModelFunctionFromLP function first");
			model = _lpGetModel.getModel(modelId);
			if(model==null)
				throw new Exception("ERROR: can not retrive model with id " + modelId);
		}
		return model;
	}
	
	private static String checkResultsFolder(String folder) throws Exception{
		if(folder == null || folder.equals("")){
			String folderPath = VerificationComponent.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			if(new File(folderPath).isDirectory())
				folderPath = folderPath.substring(0, folderPath.length()-1);
			folder = folderPath.substring(0, folderPath.lastIndexOf("/")+1) + "LPVerificationComponentResults/";
		}
		return folder;
	}
	
	/*
	public static void main(String[] args) {
		try{
			String model = new String(IOUtils.readFile("D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_adoxx_1.xml"));
			String mid = VerificationComponent.loadModel(model);
			String vid = VerificationComponent.startVerification(mid, VerificationComponent.getSupportedVerifications()[0]);
			System.out.println(VerificationComponent.getVerificationStatus(vid));
			try {
				Thread.sleep(1000*3);
			} catch (InterruptedException e) {}
			System.out.println(getVerificationStatus(vid));
			System.out.println(getVerificationResult(vid));
		}catch(Exception ex){ex.printStackTrace();}
	}
	*/
}
