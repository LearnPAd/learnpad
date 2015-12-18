package eu.learnpad.qm.component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xwiki.component.phase.InitializationException;

import eu.learnpad.qm.exception.QMModelNotImportedException;
import eu.learnpad.qm.impl.QMXwikiBridgeImpl;

public class QuestionnaireManager {

    private static QuestionnaireManager instance = null;
    
	private final String defaultConfiguratioFilename = "/tmp/foo";
	private BufferedReader configurationReader;

	private Collection<String> importedModelID;
	private Map<String, QuestionnaireGenerationStatus> generationStatusMap;
	private static int fooIndex = 0;
	
    private QuestionnaireManager (String configurationFilename) throws IOException {
// *******************************************
// this part is not completed yet
    	if (configurationFilename == null) {
			configurationFilename = this.defaultConfiguratioFilename;
			new FileOutputStream(this.defaultConfiguratioFilename, true).close();
		}
		this.configurationReader = new BufferedReader(new FileReader(configurationFilename));
// *******************************************
		
		this.importedModelID = new Vector<String>();
		this.generationStatusMap = new HashMap<String, QuestionnaireGenerationStatus>();
    }


    private static synchronized QuestionnaireManager instantiate(String configurationFilename) throws IOException{
    	if (instance == null){
    		instance = new QuestionnaireManager(configurationFilename);
    	}
    	return instance;
    }

    public static QuestionnaireManager getInstance() throws InitializationException{
    	try {
			return QuestionnaireManager.instantiate(null);
		} catch (IOException e) {
			throw new InitializationException(e.getMessage(), e);
		}
    }

    public static QuestionnaireManager getInstance(String configurationFilename) throws InitializationException{
		try {
			return QuestionnaireManager.instantiate(configurationFilename);
		} catch (IOException e) {
			throw new InitializationException(e.getMessage(), e);
		}
    }

    public void storeModelID(String modelSetID){    	
    	if (!this.importedModelID.contains(modelSetID))
    		this.importedModelID.add(modelSetID);

    	this.printSomething("[IMPORTED] " + modelSetID);
    }
    
    public String startGeneration(String modelSetID) throws QMModelNotImportedException{
    	this.printSomething("[STARTING] " + modelSetID);

    	if (!this.importedModelID.contains(modelSetID)){
        	String message = "[ERROR:] " + modelSetID +" has not been imported yet";
    		
        	this.printSomething(message);
    		
    		throw new QMModelNotImportedException(message);
    	}
		String genProcessID = "genProcessID:" + fooIndex + ":" + modelSetID.trim().toLowerCase();
		fooIndex++;
		this.generationStatusMap.put(genProcessID, QuestionnaireGenerationStatus.InProgress);
		return genProcessID;
    }
    
    public QuestionnaireGenerationStatus getGenerationStatus(String genProcessID){
//    	The second time a given genProcessID is checked, its status is
//    	changed to completed (if it exists in the map)
    	QuestionnaireGenerationStatus currentStatus = this.generationStatusMap.get(genProcessID);
    	
    	if (currentStatus == null){
    		currentStatus = QuestionnaireGenerationStatus.NotAvailable;    		
    	}

    	this.printSomething("[CURRENT STATUS] " + genProcessID + " ---> " + currentStatus);

    	switch (currentStatus) {
    		case InProgress:
    			currentStatus = QuestionnaireGenerationStatus.Completed;
    			this.generationStatusMap.put(genProcessID, currentStatus);
    			break;
    		case Completed:
    			break;
    		default:
    			currentStatus = QuestionnaireGenerationStatus.NotAvailable;
    			break;
    		}

    	this.printSomething("[UPDATED STATUS] " + genProcessID + " ---> " + currentStatus);
    	
    	return currentStatus;
    }
    
	private void printSomething(String data){
		try {
			PrintWriter w = new PrintWriter(new FileWriter("/tmp/"+this.getClass().getName()+".log",true));
			w.println("[TEST MSG] : " + data);
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	    
	
	public static void main(String[] args) {
		QuestionnaireManager me;
		try {
			me = QuestionnaireManager.getInstance();
			me.storeModelID("modelSetID");
			String genProcessID = me.startGeneration("modelSetID");
			me.getGenerationStatus(genProcessID);
			me.getGenerationStatus("fooGenProcessID");
			me.getGenerationStatus(genProcessID);
		} catch (InitializationException e) {
			e.printStackTrace();
		} catch (QMModelNotImportedException e){
			e.printStackTrace();			
		}
	}
}



