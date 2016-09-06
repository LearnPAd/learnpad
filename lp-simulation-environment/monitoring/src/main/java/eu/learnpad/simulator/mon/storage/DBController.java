package eu.learnpad.simulator.mon.storage;

import java.util.List;
import java.util.Vector;

import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Bpmn;
import eu.learnpad.simulator.mon.coverage.Category;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.coverage.Role;
import eu.learnpad.simulator.mon.coverage.Topic;

public interface DBController {
	
	public boolean connectToDB();
	public boolean disconnectFromDB();
	
	//global and utils methods
	public boolean checkIfBPHasBeenAlreadyExtracted(String idBPMN);
	public Vector<Path> getBPMNPaths(String idBPMN);
	//public ComplexEventRuleActionListDocument getRulesListForASpecificBPMN(String bpmnIDFromXML);
		
	public Activity[] getAllDistinctActivityOFaBPMN(Bpmn theBpmn);
	public Vector<Float> getLearnerBPMNScores(String learnerID);
	public Vector<Float> getLearnerRelativeBPScores(String learnerID);
	
	//BPMN methods
	public int saveBPMN(Bpmn theBPMN);
	public Bpmn getBPMN(int theBPMNid, String learnpad_bpmn_id);
	public boolean updateBpmn(int theBPMNid, Bpmn theBpmnToUpdate);
	
	//Category methods
	public int saveCategory(Category theCategory);
	public Category getCategory(int theCategoryID);
	public boolean updateCategory(int theCategoryid, Category theCategoryToUpdate);
	
	//Learner methods
	public int saveLearnerProfile(Learner theLearner);
	public Learner getLearner(String idLearner);
	public boolean updateLearner(String idLearner, Learner theLearnerToUpdate);
		
	//Path methods
	public int savePath(Path thePath);
	public Path getPath(String thePathID);
	public boolean updatePath(String thePathId, Path thePathToUpdate);
		
	//Role methods
	public int saveRole(Role theRole);
	public Role getRole(int theRoleID);
	public boolean updateRole(int theRoleId, Role theRoleToUpdate);
	
	//Topic methods
	public int saveTopic(Topic theTopic);
	public Topic getTopic(int theTopicID);
	public boolean updateTopic(int theTopicId, Topic theTopicToUpdate);
	
	public Vector<Learner> getOrSetLearners(List<String> learnersIDs);
	
	public Vector<Path> getPathsExecutedByLearner(String learnerID, String idBPMN);
	public Vector<Path> savePathsForBPMN(Vector<Path> vector);
	
	//SCORES METHODS//
	
	//path_learner table
	public float getLearnerSessionScore(String idLearner, String idPath, String idBpmn);
	public int setLearnerSessionScore(String idLearner, String idPath, String idBpmn, float sessionScore, java.sql.Date scoreUpdatingDate);
	
	//learner table
	public void setLearnerGlobalScore(String learnerID, float learnerGlobalScore);
	public float getLearnerGlobalScore(String learnerID);
	
	public void setLearnerRelativeGlobalScore(String learnerID, float learnerRelativeGlobalScore);
	public float getLearnerRelativeGlobalScore(String learnerID);
	
	public void setLearnerAbsoluteGlobalScore(String learnerID, float absoluteGlobalScore);
	public float setLearnerAbsoluteGlobalScore(String learnerID);
	
	//bpmn_learner table
	public float getLearnerBPScore(String idLearner, String idBPMN);
	public int setLearnerBPScore(String idLearner, String idBPMN, float BPScore);
	
	public float getLearnerRelativeBPScore(String idLearner, String idBPMN);
	public int setLearnerRelativeBPScore(String idLearner, String idBPMN, float relativeBPScore);
	
	public float getLearnerBPCoverage(String idLearner, String idBPMN);
	public int setLearnerBPCoverage(String idLearner, String idBPMN, float BPCoverage);
	public Vector<Float> getBPMNScoresExecutedByLearner(String learnerID);
	public Vector<Float> getMaxSessionScores(String learnerID, String idBPMN);
	public Vector<Float> getBPMNAbsoluteScoresExecutedByLearner(String learnerID);
	public void updateLearnerScores(String learnerID, float learnerGlobalScore,
			float learnerRelativeGlobalScore, float learnerAbsoluteGLobalScore);
	public void updateBpmnLearnerScores(String learnerID, String idBPMN,
			float learnerBPScore, float learnerRelativeBPScore, float learnerCoverage);
	public int getBPMNPathsCardinality(String idBPMN);
	
}