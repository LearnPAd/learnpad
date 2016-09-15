package eu.learnpad.simulator.mon.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
	 
	 private static Connection conn;
	 private Properties connectionProp;
	 private PreparedStatement preparedStmt;
	 private ResultSet resultsSet;
	 
	public H2Controller(Properties databaseConnectionProperties) {
			connectionProp = databaseConnectionProperties;
			
	}
		    	
	@Override
	public boolean connectToDB() {
		try {
			DebugMessages.print(TimeStamp.getCurrentTime(),H2Controller.class.getSimpleName(),
					"Connection to db " + connectionProp.getProperty("DB_CONNECTION"));
			         Class.forName(connectionProp.getProperty("DB_DRIVER"));
			         conn = DriverManager.getConnection(
			        		 connectionProp.getProperty("DB_CONNECTION"),
			        		 connectionProp.getProperty("DB_USER"),
			        		 connectionProp.getProperty("DB_PASSWORD"));
					 DebugMessages.ok(); 
					 
		} catch (ClassNotFoundException | SQLException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(),
					H2Controller.class.getSimpleName(),
					"Could not connect to db " + connectionProp.getProperty("DB_CONNECTION") + "\n check if db is not already in used or locked by previous instance.");
			return false;
		}
		return true;
	}

	@Override
	public boolean disconnectFromDB() {
		return false;
	}
	
	@Override
	public Vector<Path> getBPMNPaths(String idBPMN) {
		String query = "select * from glimpse.path where id_bpmn = \'"+idBPMN+"';";
		Vector<Path> retrievedPath = new Vector<Path>();
		
		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery(); 
		            
			while ( resultsSet.next() ) {
				retrievedPath.add(new Path(resultsSet.getString("id_path"),
									resultsSet.getString("id_bpmn"),
									resultsSet.getFloat("absolute_session_score"),
									resultsSet.getString("path_rule")));
            }
            DebugMessages.println(
					TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),
					"BPMN paths loaded from DB");
		} catch (SQLException e) {
			System.err.println("Exception during getBPMNPaths ");
			System.err.println(e.getMessage());
		}
        return retrievedPath;
	}

	@Override
	public float getLearnerSessionScore(String idLearner, String idPath, String idBPMN) {
		return 0;
	}
	
	@Override
	public int setLearnerSessionScore(String idLearner, String idPath, String idBPMN, float sessionScore, java.sql.Date scoreUpdatingDate) {
	      String query = " insert into glimpse.path_learner (id_learner, id_path, id_bpmn, session_score, execution_date)"
	    	        + " values (?, ?, ?, ?, ?) ";
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, idLearner);
			preparedStmt.setString(2, idPath);
		    preparedStmt.setString(3,idBPMN);
		    preparedStmt.setFloat(4, sessionScore);
		    preparedStmt.setDate(5,scoreUpdatingDate);

		    // execute the prepared statement
		    preparedStmt.execute();
		} catch (SQLException e) {
			return 1;
		}  
		DebugMessages.println(
				TimeStamp.getCurrentTime(), 
				this.getClass().getSimpleName(),
				"PathLearner SessionScore Saved");
		return 0;
	}
	
	@Override
	public int saveBPMN(Bpmn theBPMN) {

	      String query = " insert into glimpse.bpmn (id_bpmn, extraction_date, id_category, absolute_bp_score, paths_cardinality)"
	    	        + " values (?, ?, ?, ?, ?) ";
	    	 
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, theBPMN.getId());
		    preparedStmt.setDate(2, new java.sql.Date(theBPMN.getExtractionDate().getTime()));
		    preparedStmt.setInt(3,theBPMN.getIdCategory());
		    preparedStmt.setFloat(4, theBPMN.getAbsoluteBpScore());
		    preparedStmt.setInt(5, theBPMN.getPathsCardinality());

		    // execute the prepared statement
		    preparedStmt.execute();
		} catch (SQLException e) {
			return 1;
		}  
		DebugMessages.println(
				TimeStamp.getCurrentTime(), 
				this.getClass().getSimpleName(),
				"BPMN Saved");
		return 0;
	}

	@Override
	public boolean updateBpmn(int theBPMNid, Bpmn theBpmnToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveCategory(Category theCategory) {
		return 0;
	}

	@Override
	public Category getCategory(int theCategoryID) {
		return null;
	}

	@Override
	public boolean updateCategory(int theCategoryid, Category theCategoryToUpdate) {
		return false;
	}

	@Override
	public int saveLearnerProfile(Learner theLearner) {
		String query = "insert into glimpse.learner"
				+ "(id_learner, id_role, global_score, relative_global_score, absolute_global_score)"
    	        + " values (?, ?, ?, ?, ?)";
    	 
	try {
		preparedStmt = conn.prepareStatement(query);
	    preparedStmt.setString(1, theLearner.getId());
	    preparedStmt.setInt(2, theLearner.getIdRole());
	    preparedStmt.setFloat(3,theLearner.getGlobalScore());
	    preparedStmt.setFloat(4,theLearner.getRelativeGlobalScore());
	    preparedStmt.setFloat(5,theLearner.getAbsolute_global_score());

	    // execute the prepared statement
	    preparedStmt.execute();
	} catch (SQLException e) {
		return 1;
	}  
	DebugMessages.println(
			TimeStamp.getCurrentTime(), 
			this.getClass().getSimpleName(),
			"Learner profile created and saved on database.");
	return 0;
	}

	@Override
	public Learner getLearner(String idLearner) {
		String query = "select * from glimpse.learner where id_learner = \'"+idLearner+"';";
		Learner theLearnerGathered = null;
		
		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery(); 
				theLearnerGathered =  new Learner(resultsSet.getString("id_learner"),
									resultsSet.getInt("id_role"),
									resultsSet.getFloat("global_score"),
									resultsSet.getFloat("relative_global_score"),
									resultsSet.getFloat("absolute_global_score"));
            DebugMessages.println(
					TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),
					"Learner gathered from DB");
		} catch (SQLException e) {
			System.err.println("Exception during getLearner ");
			System.err.println(e.getMessage());
		}
        return theLearnerGathered;
	}

	@Override
	public boolean updateLearner(String idLearner, Learner theLearnerToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int savePath(Path thePath) {
		 String query = " insert into glimpse.path (id_path, id_bpmn, absolute_session_score, path_rule)"
	    	        + " values (?, ?, ?, ?)";
	    	 
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1,thePath.getId());
		    preparedStmt.setString(2, thePath.getIdBpmn());
		    preparedStmt.setFloat(3,thePath.getAbsoluteSessionScore());
		    preparedStmt.setString(4, thePath.getPathRule());

		    // execute the prepared statement
		    preparedStmt.execute();
		} catch (SQLException e) {
			return 1;
		}  
		DebugMessages.println(
				TimeStamp.getCurrentTime(), 
				this.getClass().getSimpleName(),
				"Path Saved");
		return 0;
	}

	@Override
	public Path getPath(String thePathID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePath(String thePathId, Path thePathToUpdate) {
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
	public boolean checkIfBPHasBeenAlreadyExtracted(String idBPMN) {
		String query = "select * from glimpse.path where id_bpmn = \'"+idBPMN+"';";
			
		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery();
			if (resultsSet.first()) { 
				DebugMessages.println(
						TimeStamp.getCurrentTime(), 
						this.getClass().getSimpleName(),
						"The BPMN has been already extracted, loading values");
				return true; 
				} 				
			}	catch(SQLException asd) {
				System.err.println("Exception during checkIfBPHasBeenAlreadyExtracted ");
				return false;
			}
		return false;
	}

	@Override
	public Activity[] getAllDistinctActivityOFaBPMN(Bpmn theBpmn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bpmn getBPMN(int theBPMNid, String learnpad_bpmn_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getLearnerBPScore(String idLearner, String idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerRelativeBPScore(String idLearner, String idBPMN) {
		
		return 0;
	}

	@Override
	public int setLearnerBPScore(String idLearner, String idBPMN, float BPScore) {
		 String query = " insert into glimpse.bpmn_learner (id_learner, id_bpmn, bp_score, relative_bp_score, bp_coverage)"
	    	        + " values (?, ?, ?, ?. ?)";
	    	 
		try {
			preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString(1, idLearner);
		    preparedStmt.setString(2,idBPMN);
		    preparedStmt.setFloat(3, BPScore);
		    preparedStmt.setFloat(4, 0f);
		    preparedStmt.setFloat(5, 0f);

		    // execute the prepared statement
		    preparedStmt.execute();
		} catch (SQLException e) {
			return 1;
		}  
		DebugMessages.println(
				TimeStamp.getCurrentTime(), 
				this.getClass().getSimpleName(),
				"learnerBPScore Updated");
		return 0;
	}

	@Override
	public float getLearnerBPCoverage(String idLearner, String idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerBPCoverage(String idLearner, String idBPMN, float BPCoverage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<Learner> getOrSetLearners(List<String> learnersIDs) {
		Vector<Learner> learners = new Vector<Learner>();
		String query;
		Learner aLearner;
		try {
			for (int i = 0; i<learnersIDs.size(); i++) {
				query = "select * from glimpse.learner where id_learner = \'"+learnersIDs.get(i)+"';";
					
						preparedStmt = conn.prepareStatement(query);
						resultsSet = preparedStmt.executeQuery(); 
						
						if (resultsSet.first()) {
							learners.add(new Learner(
								resultsSet.getString("id_learner"),
								Integer.parseInt(resultsSet.getString("id_role")),
								Float.parseFloat(resultsSet.getString("global_score")),
								Float.parseFloat(resultsSet.getString("relative_global_score")),
								Float.parseFloat(resultsSet.getString("absolute_global_score"))));
							DebugMessages.println(TimeStamp.getCurrentTime(),this.getClass().getSimpleName(),"Learner found");
							}
						else {
							aLearner = new Learner(learnersIDs.get(i),0,0.0f,0.0f,0.0f);
							learners.add(aLearner);
							saveLearnerProfile(aLearner);
							}	 
						}
		} catch (SQLException e) {
			System.err.println("Exception during getLearners");
			System.err.println(e.getMessage());
		}
		return learners;	
	}

	@Override
	public Vector<Path> savePathsForBPMN(Vector<Path> vector) {

		for (int i = 0; i<vector.size(); i++) {
			savePath(vector.get(i));
		}
		return vector;
	}

	@Override
	public Vector<Path> getPathsExecutedByLearner(String learnerID, String idBPMN) {
		String query = "SELECT * FROM glimpse.path where id_path IN( "
				+ "SELECT distinct id_path FROM glimpse.path_learner where id_learner = \'" +
				learnerID +	"')";
		Vector<Path> retrievedPath = new Vector<Path>();
		
		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery(); 
			while ( resultsSet.next() ) {
				retrievedPath.add(new Path(resultsSet.getString("id_path"),
									resultsSet.getString("id_bpmn"),
									resultsSet.getFloat("absolute_session_score"),
									resultsSet.getString("path_rule")));
            }
            DebugMessages.println(
					TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),
					"Executed paths loaded from DB");
		} catch (SQLException e) {
			System.err.println("Exception during getPathsExecutedByLearner ");
			System.err.println(e.getMessage());
		}
        return retrievedPath;
	}

	@Override
	public void setLearnerGlobalScore(String learnerID, float learnerGlobalScore) {
		 String query = " update glimpse.learner set global_score = "+
				 			learnerGlobalScore + ";";
	    	 
		try {
			preparedStmt = conn.prepareStatement(query);
		    
		    // execute the prepared statement
		    preparedStmt.execute();
		} catch (SQLException e) {
			System.err.println("Exception during setLearnerGlobalScore ");
			System.err.println(e.getMessage());
		}  
		DebugMessages.println(
				TimeStamp.getCurrentTime(), 
				this.getClass().getSimpleName(),
				"GlobalScore Updated");
		
	}

	@Override
	public void setLearnerRelativeGlobalScore(String learnerID, float learnerRelativeGlobalScore) {
		 String query = " update glimpse.learner set relative_global_score = "+
				 learnerRelativeGlobalScore + ";";
	 
		try {
			preparedStmt = conn.prepareStatement(query);

			// execute the prepared statement
			preparedStmt.execute();
		} catch (SQLException e) {
		}
		DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
				"learnerRelativeGlobalScore Updated");
	}
	
	@Override
	public void setLearnerAbsoluteGlobalScore(String learnerID, float absoluteGlobalScore) {
		 String query = " update glimpse.learner set absolute_global_score = "+
				 absoluteGlobalScore + ";";
	 
		try {
			preparedStmt = conn.prepareStatement(query);

			// execute the prepared statement
			preparedStmt.execute();
		} catch (SQLException e) {
		}
		DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
				"absoluteGlobalScore Updated");
	}

	@Override
	public Vector<Float> getLearnerBPMNScores(String learnerID) {
		String query = "SELECT bp_score " + " FROM glimpse.bpmn_learner"
				+ " where id_learner = \'" + learnerID + "';";
		Vector<Float> retrievedScores = new Vector<Float>();
		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery();
			while (resultsSet.next()) {
				retrievedScores.add(resultsSet.getFloat("bp_score"));
				}
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "BPMN scores retrieved");
		} catch (SQLException e) {
			System.err.println("Exception during getLearnerBPMNScores");
			System.err.println(e.getMessage());
		}
		return retrievedScores;
	}

	@Override
	public int setLearnerRelativeBPScore(String idLearner, String idBPMN, float relativeBPScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerGlobalScore(String learnerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerRelativeGlobalScore(String learnerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float setLearnerAbsoluteGlobalScore(String learnerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<Float> getLearnerRelativeBPScores(String learnerID) {
		String query = "SELECT relative_bp_score "
				+ " FROM glimpse.bpmn_learner" 
				+ " where id_learner = '" + learnerID +
				"'";
		Vector<Float> retrievedScores = new Vector<Float>();
		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery(); 
			while ( resultsSet.next() ) {
					retrievedScores.add(resultsSet.getFloat("relative_bp_score"));
			}
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),"BPMN scores retrieved");
		} catch (SQLException e) {
			System.err.println("Exception during getLearnerBPMNScores");
			System.err.println(e.getMessage());
		}
		return retrievedScores;
	}

	@Override
	public Vector<Float> getBPMNScoresExecutedByLearner(String learnerID) {
		String query = "SELECT bpmn_learner.bp_score"
				+ " FROM glimpse.bpmn, glimpse.bpmn_learner" + " where bpmn_learner.id_learner = '" + learnerID + "'";

		Vector<Float> retrievedScores = new Vector<Float>();

		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery();

			while (resultsSet.next()) {
				retrievedScores.add(resultsSet.getFloat("bp_score"));
			}
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Extracted bpmn loaded from DB");
		} catch (SQLException e) {
			System.err.println("Exception during getBPMNExecutedByLearner");
			System.err.println(e.getMessage());
		}
		return retrievedScores;
	}

	@Override
	public Vector<Float> getMaxSessionScores(String learnerID, String idBPMN) {
		
		String query = "SELECT max(session_score)"
				+ " FROM glimpse.path_learner"
				+ " where id_learner = '"+ learnerID
				+ "' and EXISTS (select distinct id_path from glimpse.path_learner where id_bpmn = '" + idBPMN + "') group by id_path";

		Vector<Float> retrievedScores = new Vector<Float>();

		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery();

			while (resultsSet.next()) {
				retrievedScores.add(resultsSet.getFloat("MAX(session_score)"));
			}
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Selected session_score");
		} catch (SQLException e) {
			System.err.println("Exception during getMaxSessionScores");
			System.err.println(e.getMessage());
		}
		return retrievedScores;
	}

	@Override
	public Vector<Float> getBPMNAbsoluteScoresExecutedByLearner(String learnerID) {
		String query = "SELECT bpmn.absolute_bp_score"
				+ " FROM glimpse.bpmn, glimpse.bpmn_learner"
				+ " where bpmn_learner.id_learner = '" + learnerID + "'";

		Vector<Float> retrievedScores = new Vector<Float>();

		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery();
			if (resultsSet.wasNull() == false) {  
				while (resultsSet.next()) {
					retrievedScores.add(resultsSet.getFloat("absolute_bp_score"));
				}
			} else {
				retrievedScores.add(0f);
			}
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Selected absolute_bp_scores");
		} catch (SQLException e) {
			System.err.println("Exception during getBPMNAbsoluteScoresExecutedByLearner");
			System.err.println(e.getMessage());
		}
		return retrievedScores;
	}

	@Override
	public void updateLearnerScores(String learnerID, float learnerGlobalScore, 
			float learnerRelativeGlobalScore, float learnerAbsoluteGLobalScore) {
		String query;
		Learner aLearner;
		try {
			query = "select * from glimpse.learner where id_learner = \'"+learnerID+"';";
					preparedStmt = conn.prepareStatement(query);
					resultsSet = preparedStmt.executeQuery(); 
						
						if (resultsSet.first()) {
							
							query = "update glimpse.learner set global_score = "+
							learnerGlobalScore + ",  relative_global_score = "+
									learnerRelativeGlobalScore + ", absolute_global_score = "+
									 learnerAbsoluteGLobalScore + " where id_learner = \'"+
									learnerID + "';";
						 
							preparedStmt = conn.prepareStatement(query);

								// execute the prepared statement
							preparedStmt.execute();
							
							DebugMessages.println(
									TimeStamp.getCurrentTime(), 
									this.getClass().getSimpleName(),
									"Learner Scores updated");
						}
						else {
							aLearner = new Learner(learnerID,0,learnerGlobalScore,learnerRelativeGlobalScore,learnerAbsoluteGLobalScore);
							saveLearnerProfile(aLearner);
							}	 
		} catch (SQLException e) {
			System.err.println("Exception during updateLearnerScores");
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void updateBpmnLearnerScores(String learnerID, String idBPMN, float learnerBPScore,
			float learnerRelativeBPScore, float learnerCoverage) {
		String query;
		try {
			query = "select * from glimpse.bpmn_learner where id_learner = \'"+learnerID+"';";
					preparedStmt = conn.prepareStatement(query);
					resultsSet = preparedStmt.executeQuery(); 
						
						if (resultsSet.first()) {
							
							query = "update glimpse.bpmn_learner set bp_score = "+
							learnerBPScore + ",  relative_bp_score = "+
									learnerRelativeBPScore + ", bp_coverage = "+
									 learnerCoverage + " where id_learner = \'"+
									learnerID + "';";
						 
							preparedStmt = conn.prepareStatement(query);

								// execute the prepared statement
							preparedStmt.execute();
							DebugMessages.println(
									TimeStamp.getCurrentTime(), 
									this.getClass().getSimpleName(),
									"BPMN scores updated");
						}
						else {
							 query = " insert into glimpse.bpmn_learner (id_learner, id_bpmn, bp_score, relative_bp_score, bp_coverage)"
						    	        + " values (?, ?, ?, ?, ?)";
								preparedStmt = conn.prepareStatement(query);

							    preparedStmt.setString(1, learnerID);
							    preparedStmt.setString(2,idBPMN);
							    preparedStmt.setFloat(3, learnerBPScore);
							    preparedStmt.setFloat(4, learnerRelativeBPScore);
							    preparedStmt.setFloat(5, learnerCoverage);

							    // execute the prepared statement
							    preparedStmt.execute();
							}	 
		} catch (SQLException e) {
			System.err.println("Exception during updateBpmnLearnerScores");
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public int getBPMNPathsCardinality(String idBPMN) {
		String query = "SELECT COUNT(*) FROM glimpse.path where id_bpmn = \'"+idBPMN+"';";
		int result = 0;

		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery(); 
			
			while ( resultsSet.next() ) {
				result = resultsSet.getInt("COUNT(*)");
            }
            DebugMessages.println(
					TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),
					"Paths counted ");
		} catch (SQLException e) {
			System.err.println("Exception during getBPMNPathsCardinality ");
			System.err.println(e.getMessage());
		}
		return result;
	}
}
