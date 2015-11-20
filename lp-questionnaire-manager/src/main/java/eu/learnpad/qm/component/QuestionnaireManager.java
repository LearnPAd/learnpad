package eu.learnpad.qm.component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xwiki.component.phase.InitializationException;

public class QuestionnaireManager {

    private static QuestionnaireManager instance = null;
    
	private final String defaultConfiguratioFilename = "";
	private BufferedReader configurationReader;

	private Collection<String> importedModelID;
	private Map<String, QuestionnaireGenerationStatus> generationStatusMap;
	private static int fooIndex = 0;
	
    private QuestionnaireManager (String configurationFilename){
		try {			
			if (configurationFilename == null)
				configurationFilename = this.defaultConfiguratioFilename;
				this.configurationReader = new BufferedReader(new FileReader(configurationFilename));
				
				this.importedModelID = new Vector<String>();
				this.generationStatusMap = new HashMap<String,QuestionnaireGenerationStatus>();
		} catch (IOException e) {
			new InitializationException(e.getMessage(), e);
		}
    }


    private static synchronized QuestionnaireManager instantiate(String configurationFilename){
    	if (instance == null){
    		instance = new QuestionnaireManager(configurationFilename);
    	}
    	return instance;
    }

    public static QuestionnaireManager getInstance(){
    	return QuestionnaireManager.instantiate(null);
    }

    public static QuestionnaireManager getInstance(String configurationFilename){
    	return QuestionnaireManager.instantiate(configurationFilename);
    }

    public void storeModelID(String modelSetID){
    	if (!this.importedModelID.contains(modelSetID))
    		this.importedModelID.add(modelSetID);
    }
    
    public String startGeneration(String modelSetID) throws Exception{
    	if (!this.importedModelID.contains(modelSetID)){
    		throw new Exception("[ERROR:] " + modelSetID +" has not been imported yet");
    	}
		String genProcessID = "genProcessID:" + fooIndex + ":" + modelSetID.trim().toLowerCase();
		fooIndex++;
		this.generationStatusMap.put(genProcessID, QuestionnaireGenerationStatus.InProgress);
		return genProcessID;
    }
    
    public QuestionnaireGenerationStatus getGenerationStatus(String genProcessID){
    	QuestionnaireGenerationStatus currentStatus = this.generationStatusMap.get(genProcessID);
    	
    	switch (currentStatus) {
    	case InProgress:
    		this.generationStatusMap.put(genProcessID, QuestionnaireGenerationStatus.Completed);
    		break;
    	case Completed:
    		break;
		default:
    		currentStatus = QuestionnaireGenerationStatus.NotAvailable;
			break;
		}
    	
    	return currentStatus;
    }
}



