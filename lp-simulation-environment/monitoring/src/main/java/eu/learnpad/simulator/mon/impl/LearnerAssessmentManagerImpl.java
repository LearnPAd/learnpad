package eu.learnpad.simulator.mon.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.net.ntp.TimeStamp;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.rest.event.ScoreType;
import eu.learnpad.sim.rest.event.impl.ScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.simulator.mon.BPMN.PathExplorer;
import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Bpmn;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.impl.PathExplorerImpl;
import eu.learnpad.simulator.mon.impl.RulesPerPathGeneratorImpl;
import eu.learnpad.simulator.mon.manager.LearnerAssessmentManager;
import eu.learnpad.simulator.mon.manager.ResponseDispatcher;
import eu.learnpad.simulator.mon.manager.RestNotifier;
import eu.learnpad.simulator.mon.rules.generator.RulesPerPath;
import eu.learnpad.simulator.mon.storage.DBController;
import eu.learnpad.simulator.mon.storage.ScoreTemporaryStorage;
import eu.learnpad.simulator.mon.utils.ComputeLearnerScore;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public class LearnerAssessmentManagerImpl extends LearnerAssessmentManager {

	private Document theBPMN;
	private PathExplorer bpmnExplorer;
	private RulesPerPath crossRulesGenerator;
	private DBController databaseController;
	private ComplexEventRuleActionListDocument rulesLists;
	private float absoluteSessionScore; 

	public LearnerAssessmentManagerImpl(DBController databaseController) {
		
		//Creation of the BPMNExplorerEngine
		 bpmnExplorer = new PathExplorerImpl();
		 		
		//the instance of DB used
		this.databaseController = databaseController;
		
		//Creation of the PathCrossingRulesGenerator object
		crossRulesGenerator = new RulesPerPathGeneratorImpl();
		
	}
		
	public void run() {
		
		databaseController.connectToDB();
		databaseController.cleanDB();
	}
	
	@Override
	public DBController getDBController() {
		return this.databaseController;
	}
		
	@Override
	public Document setBPModel(String xmlMessagePayload) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		Document dom = docBuilder.parse(new InputSource(new StringReader(xmlMessagePayload)));
		this.theBPMN = dom;
		return dom;
	}

	@Override
	public ComplexEventRuleActionListDocument elaborateModel(String xmlMessagePayload, Vector<Learner> usersInvolved, String sessionID, String bpmnID) {
		
		try {
			theBPMN = setBPModel(xmlMessagePayload);
			
			if (!databaseController.checkIfBPHasBeenAlreadyExtracted(bpmnID)) {
				
				Date now = new Date();
				
				Vector<Activity[]> theUnfoldedBPMN = bpmnExplorer.getUnfoldedBPMN(theBPMN, bpmnID);
				
				Bpmn newBpmn = new Bpmn(bpmnID,now,0, 0, theUnfoldedBPMN.size());

				//rules for coverage
				Vector<Path> theGeneratedPath = crossRulesGenerator.generatePathsRules(
																	crossRulesGenerator.generateAllPaths(theUnfoldedBPMN, newBpmn.getId()));
				
				theGeneratedPath = setAllAbsoluteSessionScores(theGeneratedPath);
				
				this.rulesLists = crossRulesGenerator.instantiateRulesSetForUsersInvolved(
						databaseController.savePathsForBPMN(theGeneratedPath),usersInvolved, sessionID);
				
				newBpmn.setAbsoluteBpScore(ComputeLearnerScore.absoluteBP(theGeneratedPath));
				
				databaseController.saveBPMN(newBpmn);
			} else {
				this.rulesLists = crossRulesGenerator.instantiateRulesSetForUsersInvolved(
						databaseController.getBPMNPaths(bpmnID),
						usersInvolved, sessionID);
			}			
		} catch (ParserConfigurationException | SAXException | IOException e ) {
			e.printStackTrace();
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),e.getCause().toString());
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),"The message contains an INVALID BPMN");
		}		
		return rulesLists;
	}

	@Override
	public Vector<Path> setAllAbsoluteSessionScores(Vector<Path> theGeneratedPath) {

		for (int i =0; i< theGeneratedPath.size(); i++) {
			absoluteSessionScore = ComputeLearnerScore.absoluteSession(theGeneratedPath.get(i).getActivities());
			theGeneratedPath.get(i).setAbsoluteSessionScore(absoluteSessionScore);
		}
		
		return theGeneratedPath;
		
	}

	@Override
	public void setPathCompleted(List<String> learnersID, String idPath, String idBPMN) {
		Date now = new Date();

		DebugMessages.print(TimeStamp.getCurrentTime(),  this.getClass().getSimpleName(),  
				"Set path " + idPath + " for bpmn " + idBPMN + " completed ");
		DebugMessages.ok();
		
		for(int i = 0; i<learnersID.size(); i++) {
			databaseController.setLearnerSessionScore(
					learnersID.get(i), idPath, idBPMN, 
					ScoreTemporaryStorage.getTemporaryLearnerSessionScore(learnersID.get(i)),
					new java.sql.Date(now.getTime()));
		}
	}
	
	@Override
	public void computeAndPropagateScores(List<String> learnersID, String idBPMN, String simulationSessionID) {
		
		int pathsCardinality = databaseController.getBPMNPathsCardinality(idBPMN);
		
		for(int i = 0; i<learnersID.size(); i++) {
//			databaseController.setLearnerSessionScore(
//					learnersID.get(i), idPath, idBPMN, 
//					ScoreTemporaryStorage.getTemporaryLearnerSessionScore(learnersID.get(i)),
//					new java.sql.Date(now.getTime()));
			
			float learnerBPScore = ComputeLearnerScore.learnerBP(
					databaseController.getMaxSessionScores(learnersID.get(i), idBPMN));
			
			Vector<Path> pathsExecutedByLearner = databaseController.getPathsExecutedByLearner(learnersID.get(i), idBPMN); 
			
			//compute relativeBPScore
			float learnerRelativeBPScore = ComputeLearnerScore.learnerRelativeBP(pathsExecutedByLearner);

			//compute coverage percentage
			float learnerCoverage = ComputeLearnerScore.BPCoverage(
					pathsExecutedByLearner,pathsCardinality);

			databaseController.updateBpmnLearnerScores(learnersID.get(i), idBPMN, learnerBPScore, learnerRelativeBPScore, learnerCoverage);
			
			//compute globalScore
			float learnerGlobalScore = ComputeLearnerScore.learnerGlobal(
					databaseController.getLearnerBPMNScores(learnersID.get(i)));
		
			//compute relativeGlobalScore
			float learnerRelativeGlobalScore = ComputeLearnerScore.learnerRelativeGlobal(
					databaseController.getLearnerRelativeBPScores(learnersID.get(i)));
			
			
			//compute absoluteGlobalScore
			float learnerAbsoluteGlobalScore = ComputeLearnerScore.learnerAbsoluteGlobal(
					databaseController.getBPMNAbsoluteScoresExecutedByLearner(learnersID.get(i)));
			
			databaseController.updateLearnerScores(
					learnersID.get(i), learnerGlobalScore, learnerRelativeGlobalScore, learnerAbsoluteGlobalScore);

			HashMap<ScoreType, Float> scoresToShow = new HashMap<ScoreType, Float>();
			
			scoresToShow.put(ScoreType.ABSOLUTE_BP_SCORE, databaseController.getAbsoluteBPScore(idBPMN));
			scoresToShow.put(ScoreType.ABSOLUTE_GLOBAL_SCORE, learnerAbsoluteGlobalScore);
			scoresToShow.put(ScoreType.ABSOLUTE_SESSION_SCORE, databaseController.getLastPathAbsoluteSessionScoreExecutedByLearner(learnersID.get(i), idBPMN));
			scoresToShow.put(ScoreType.BP_COVERAGE, learnerCoverage);
			scoresToShow.put(ScoreType.BP_SCORE, learnerBPScore);
			scoresToShow.put(ScoreType.GLOBAL_SCORE, learnerGlobalScore);
			scoresToShow.put(ScoreType.RELATIVE_BP_SCORE, learnerRelativeBPScore);
			scoresToShow.put(ScoreType.RELATIVE_GLOBAL_SCORE, learnerRelativeGlobalScore);
			scoresToShow.put(ScoreType.SESSION_SCORE, ScoreTemporaryStorage.getTemporaryLearnerSessionScore(learnersID.get(i)).floatValue());
			
			DebugMessages.line();
			sendScoresToSim(scoresToShow,learnersID.get(i), simulationSessionID);
			DebugMessages.line();
			
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "SESSION_SCORE " + scoresToShow.get(ScoreType.SESSION_SCORE));
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "ABSOLUTE_SESSION_SCORE " + scoresToShow.get(ScoreType.ABSOLUTE_SESSION_SCORE));
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "BP_COVERAGE " + scoresToShow.get(ScoreType.BP_COVERAGE));
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "BP_SCORE " + scoresToShow.get(ScoreType.BP_SCORE));
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "RELATIVE_BP_SCORE " + scoresToShow.get(ScoreType.RELATIVE_BP_SCORE));
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "GLOBAL_SCORE " + scoresToShow.get(ScoreType.GLOBAL_SCORE));
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "ABSOLUTE_GLOBAL_SCORE " + scoresToShow.get(ScoreType.ABSOLUTE_GLOBAL_SCORE));
			DebugMessages.line();
			//DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "ABSOLUTE_BP_SCORE " + scoresToShow.get(ScoreType.ABSOLUTE_BP_SCORE));
			//DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "RELATIVE_GLOBAL_SCORE " + scoresToShow.get(ScoreType.RELATIVE_GLOBAL_SCORE));
			
			
			DebugMessages.print(TimeStamp.getCurrentTime(),  this.getClass().getSimpleName(),  "Sending score to the platform ");
			ScoreUpdateEvent theEventForTheCP = generateScoreEvent(scoresToShow, ScoreTemporaryStorage.getLastScoreUpdateEventSeenForUser(learnersID.get(i)), learnersID.get(i));
			sendScoreUpdateEventToCP(theEventForTheCP);
			DebugMessages.ok();
			
			DebugMessages.line();
			DebugMessages.println(TimeStamp.getCurrentTime(),  this.getClass().getSimpleName(), " ------ VALUES SENT TO THE CP ------");
			DebugMessages.println(TimeStamp.getCurrentTime(),  this.getClass().getSimpleName(), "simulationSessionID: " + theEventForTheCP.simulationsessionid );
			DebugMessages.println(TimeStamp.getCurrentTime(),  this.getClass().getSimpleName(), "involvedusers: " + theEventForTheCP.involvedusers );
			DebugMessages.println(TimeStamp.getCurrentTime(),  this.getClass().getSimpleName(), "simulationSessionData: " + theEventForTheCP.simulationSessionData );
			DebugMessages.line();
			
		}
	}
	
	private void sendScoresToSim(HashMap<ScoreType, Float> scoresToShow, String user, String simulationSessionID) {
		DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Sending scores to the simulator related to user: " + user);
		ResponseDispatcher.sendScoresEvaluation(scoresToShow, "simulator", "scoresUpdateResponses", user, simulationSessionID);
		DebugMessages.ok();	
}

	protected ScoreUpdateEvent generateScoreEvent(Map<ScoreType, Float> scores, SessionScoreUpdateEvent sessionScore, String learnersID) {
		return new ScoreUpdateEvent(System.currentTimeMillis(), sessionScore.simulationsessionid, sessionScore.involvedusers,
				sessionScore.modelsetid, sessionScore.simulationSessionData, sessionScore.processartifactid,
				learnersID, scores);
	}
	
	protected void sendScoreUpdateEventToCP(ScoreUpdateEvent event) {
		try {
			RestNotifier.getCoreFacade().notifyScoreUpdateEvent(event);
		} catch (LpRestException e) {
			e.printStackTrace();
		}
	}
}