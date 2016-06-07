package eu.learnpad.simulator.mon.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Bpmn;
import eu.learnpad.simulator.mon.coverage.Category;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.coverage.Role;
import eu.learnpad.simulator.mon.coverage.Topic;
import eu.learnpad.simulator.mon.utils.DebugMessages;

public class H2Controller implements DBController {
	 
	 private static Connection connection;
	 private Properties connectionProp;
	 
	public H2Controller(Properties databaseConnectionProperties) {
			connectionProp = databaseConnectionProperties;
		}
		
	    
	public Connection createDatabaseAndStructure() throws ClassNotFoundException, SQLException {
		DebugMessages.print(TimeStamp.getCurrentTime(),H2Controller.class.getSimpleName(),
		"Connection to db " + connectionProp.getProperty("DB_CONNECTION"));
		
		Connection dbConnection = null;
         Class.forName(connectionProp.getProperty("DB_DRIVER"));
         dbConnection = DriverManager.getConnection(
        		 connectionProp.getProperty("DB_CONNECTION"),
        		 connectionProp.getProperty("DB_USER"),
        		 connectionProp.getProperty("DB_PASSWORD"));
		 DebugMessages.ok(); 				
	     return dbConnection;
	    }
	
	@Override
	public boolean connectToDB() {
		
		try {
			connection = createDatabaseAndStructure();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			DebugMessages.println(TimeStamp.getCurrentTime(),
					H2Controller.class.getSimpleName(),
					"Could not connect to db " + connectionProp.getProperty("DB_CONNECTION"));
			return false;
		}
		return true;
	}

	@Override
	public boolean disconnectFromDB() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfBPHasBeenAlreadyExtracted(String idBPMN) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<Path> getBPMNPaths(String idBPMN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity[] getAllDistinctActivityOFaBPMN(Bpmn theBpmn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Float> getLearnerBPMNScores(int learnerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Float> getLearnerRelativeBPScores(int learnerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveBPMN(Bpmn theBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bpmn getBPMN(int theBPMNid, String learnpad_bpmn_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBpmn(int theBPMNid, Bpmn theBpmnToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveCategory(Category theCategory) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Category getCategory(int theCategoryID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCategory(int theCategoryid, Category theCategoryToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveLearnerProfile(Learner theLearner) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Learner getLearner(int idLearner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateLearner(int idLearner, Learner theLearnerToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int savePath(Path thePath) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Path getPath(int thePathID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePath(int thePathId, Path thePathToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveRole(Role theRole) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Role getRole(int theRoleID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRole(int theRoleId, Role theRoleToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveTopic(Topic theTopic) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Topic getTopic(int theTopicID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTopic(int theTopicId, Topic theTopicToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<Learner> getLearners(String[] learnersIDs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Path> getPathsExecutedByLearner(int learnerID, String idBPMN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Path> savePathsForBPMN(Vector<Path> vector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getLearnerSessionScore(int idLearner, int idPath, String idBpmn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerSessionScore(int idLearner, int idPath, String idBpmn, float sessionScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLearnerGlobalScore(int learnerID, float learnerGlobalScore) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getLearnerGlobalScore(int learnerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLearnerRelativeGlobalScore(int learnerID, float learnerRelativeGlobalScore) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getLearnerRelativeGlobalScore(int learnerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLearnerAbsoluteGlobalScore(int learnerID, float absoluteGlobalScore) {
		// TODO Auto-generated method stub

	}

	@Override
	public float setLearnerAbsoluteGlobalScore(int learnerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerBPScore(int idLearner, String idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerBPScore(int idLearner, String idBPMN, float BPScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerRelativeBPScore(int idLearner, String idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerRelativeBPScore(int idLearner, String idBPMN, float relativeBPScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerBPCoverage(int idLearner, String idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerBPCoverage(int idLearner, String idBPMN, float BPCoverage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<Float> getBPMNScoresExecutedByLearner(int learnerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Float> getMaxSessionScores(int parseInt, String idBPMN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Float> getBPMNAbsoluteScoresExecutedByLearner(int learnerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateLearnerScores(int learnerID, float learnerGlobalScore, float learnerRelativeGlobalScore,
			float learnerAbsoluteGLobalScore) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBpmnLearnerScores(int learnerID, String idBPMN, float learnerBPScore,
			float learnerRelativeBPScore, float learnerCoverage) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getBPMNPathsCardinality(String idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

}
