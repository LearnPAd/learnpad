/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.qm.component;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xwiki.component.phase.InitializationException;

import eu.learnpad.qm.exception.QMModelNotImportedException;
import eu.learnpad.qm.impl.QMBridgeNotifier;

/**
*
* @author gulyx
*/
public class QuestionnaireManager implements QuestionnaireManagerNotifier {

    private static QuestionnaireManager instance = null;
    
	private final String defaultConfiguratioFilename = "/tmp/foo";
	private BufferedReader configurationReader;

	private Collection<String> importedModelID;
	private volatile Map<String, String> genProcessID2QuestionnaireFileMap;
	private volatile Map<String, QuestionnaireGenerationTask> activeGenerationTasks;
	
	private SecureRandom random;
	
	private QMBridgeNotifier bridgeNofitier;
	
    private QuestionnaireManager (QMBridgeNotifier bridgeNofitier, String configurationFilename) throws IOException {
// *******************************************
// this part is not completed yet
    	if (configurationFilename == null) {
			configurationFilename = this.defaultConfiguratioFilename;
			new FileOutputStream(this.defaultConfiguratioFilename, true).close();
		}
		this.configurationReader = new BufferedReader(new FileReader(configurationFilename));
// *******************************************
	
		this.bridgeNofitier = bridgeNofitier;
		
		this.importedModelID = new Vector<String>();
		this.activeGenerationTasks = Collections.synchronizedMap(new HashMap<String, QuestionnaireGenerationTask>());
		this.genProcessID2QuestionnaireFileMap = Collections.synchronizedMap(new HashMap<String,String>());
				
		this.random = new SecureRandom();
    }


	private String randomId() {
	    return new BigInteger(130, random).toString(32);
	}

    private static synchronized QuestionnaireManager instantiate(QMBridgeNotifier bridgeNofitier, String configurationFilename) throws IOException{
    	if (instance == null){
    		instance = new QuestionnaireManager(bridgeNofitier, configurationFilename);
    	}
    	return instance;
    }

    public static QuestionnaireManager getInstance(QMBridgeNotifier bridgeNofitier) throws InitializationException{
    	try {
			return QuestionnaireManager.instantiate(bridgeNofitier,null);
		} catch (IOException e) {
			throw new InitializationException(e.getMessage(), e);
		}
    }

    public static QuestionnaireManager getInstance(QMBridgeNotifier bridgeNofitier, String configurationFilename) throws InitializationException{
		try {
			return QuestionnaireManager.instantiate(bridgeNofitier,configurationFilename);
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
    	String genProcessID = this.startGeneration(modelSetID, this.configurationReader);
    	return genProcessID;
    }
    
    public String startGeneration(String modelSetID, BufferedReader configurationFile) throws QMModelNotImportedException{
    	this.printSomething("[STARTING] " + modelSetID);

    	if (!this.importedModelID.contains(modelSetID)){
        	String message = "[ERROR:] " + modelSetID +" has not been imported yet";
    		
        	this.printSomething(message);
    		
    		throw new QMModelNotImportedException(message);
    	}
    	
    	String fooIndex = this.randomId();
		String genProcessID = "genProcessID:" + modelSetID.trim().toLowerCase() + ":" + fooIndex;

//		The new questionnaire generation task is created, added into the map, and activated 
		QuestionnaireGenerationTask genTask = new QuestionnaireGenerationTask(genProcessID,this);
		this.activeGenerationTasks.put(genProcessID, genTask);
		new Thread(genTask).start();
		
		return genProcessID;
    }
    
    public QuestionnaireGenerationStatus getGenerationStatus(String genProcessID){    	
    	QuestionnaireGenerationStatus currentStatus = QuestionnaireGenerationStatus.NotAvailable;
    	
    	if (this.genProcessID2QuestionnaireFileMap.containsKey(genProcessID)){
    		currentStatus = QuestionnaireGenerationStatus.Completed;
    	}else{    	
    		QuestionnaireGenerationTask genTask = this.activeGenerationTasks.get(genProcessID);    	    	
    		if (genTask != null){
    			currentStatus = genTask.getGenerationStatus();
// If an existing genProcessID is checked, it is notified with a stimulus so that change its status.
// This method simulate a progression on the generation step.
// It must be removed when MOTHIA is integrated.
    			genTask.stimulusFromABackdoor();
    		}
    	}
    	
    	this.printSomething("[CURRENT STATUS] " + genProcessID + " ---> " + currentStatus);

    	return currentStatus;
    }
    
	@Override
	public void generationCompleted(String genProcessID, String genFilePath) {
		this.genProcessID2QuestionnaireFileMap.put(genProcessID, genFilePath);
		this.activeGenerationTasks.remove(genProcessID);

		if (this.bridgeNofitier != null)
			this.bridgeNofitier.generationCompleted(genProcessID, genFilePath);
		
		this.printSomething("[NOTIFICATION] " + genProcessID + " has been completed!" );
	}

	private synchronized void printSomething(String data){
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
			me = QuestionnaireManager.getInstance(null);
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



