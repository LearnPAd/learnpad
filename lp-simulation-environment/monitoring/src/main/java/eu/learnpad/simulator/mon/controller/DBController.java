package eu.learnpad.simulator.mon.controller;

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
	public Vector<Float> getLearnerBPMNScores(int learnerID);
	public Vector<Float> getLearnerRelativeBPScores(int learnerID);
	
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
	public Learner getLearner(int idLearner);
	public boolean updateLearner(int idLearner, Learner theLearnerToUpdate);
		
	//Path methods
	public int savePath(Path thePath);
	public Path getPath(int thePathID);
	public boolean updatePath(int thePathId, Path thePathToUpdate);
		
	//Role methods
	public int saveRole(Role theRole);
	public Role getRole(int theRoleID);
	public boolean updateRole(int theRoleId, Role theRoleToUpdate);
	
	//Topic methods
	public int saveTopic(Topic theTopic);
	public Topic getTopic(int theTopicID);
	public boolean updateTopic(int theTopicId, Topic theTopicToUpdate);
	
	public Vector<Learner> getLearners(String[] learnersIDs);
	
	public Vector<Path> getPathsExecutedByLearner(int learnerID, String idBPMN);
	public Vector<Path> savePathsForBPMN(Vector<Path> vector);
	
	//SCORES METHODS//
	
	//path_learner table
	public float getLearnerSessionScore(int idLearner, int idPath, String idBpmn);
	public int setLearnerSessionScore(int idLearner, int idPath, String idBpmn, float sessionScore);
	
	//learner table
	public void setLearnerGlobalScore(int learnerID, float learnerGlobalScore);
	public float getLearnerGlobalScore(int learnerID);
	
	public void setLearnerRelativeGlobalScore(int learnerID, float learnerRelativeGlobalScore);
	public float getLearnerRelativeGlobalScore(int learnerID);
	
	public void setLearnerAbsoluteGlobalScore(int learnerID, float absoluteGlobalScore);
	public float setLearnerAbsoluteGlobalScore(int learnerID);
	
	//bpmn_learner table
	public float getLearnerBPScore(int idLearner, String idBPMN);
	public int setLearnerBPScore(int idLearner, String idBPMN, float BPScore);
	
	public float getLearnerRelativeBPScore(int idLearner, String idBPMN);
	public int setLearnerRelativeBPScore(int idLearner, String idBPMN, float relativeBPScore);
	
	public float getLearnerBPCoverage(int idLearner, String idBPMN);
	public int setLearnerBPCoverage(int idLearner, String idBPMN, float BPCoverage);
	public Vector<Float> getBPMNScoresExecutedByLearner(int learnerID);
	public Vector<Float> getMaxSessionScores(int parseInt, String idBPMN);
	public Vector<Float> getBPMNAbsoluteScoresExecutedByLearner(int learnerID);
	public void updateLearnerScores(int learnerID, float learnerGlobalScore,
			float learnerRelativeGlobalScore, float learnerAbsoluteGLobalScore);
	public void updateBpmnLearnerScores(int learnerID, String idBPMN,
			float learnerBPScore, float learnerRelativeBPScore, float learnerCoverage);
	public int getBPMNPathsCardinality(String idBPMN);
	
}