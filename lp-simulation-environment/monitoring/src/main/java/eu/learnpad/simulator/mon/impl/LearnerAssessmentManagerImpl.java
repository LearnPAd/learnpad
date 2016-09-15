package eu.learnpad.simulator.mon.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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
import eu.learnpad.simulator.mon.storage.H2Controller;
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
	private float absoluteBPScore;
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
				
				Vector<Activity[]> theUnfoldedBPMN = bpmnExplorer.getUnfoldedBPMN(theBPMN);
				
				Bpmn newBpmn = new Bpmn(bpmnID,now,0, 0, theUnfoldedBPMN.size());

				//rules for coverage
				Vector<Path> theGeneratedPath = crossRulesGenerator.generatePathsRules(
																	crossRulesGenerator.generateAllPaths(theUnfoldedBPMN, newBpmn.getId()));
				
				theGeneratedPath = setAllAbsoluteSessionScores(theGeneratedPath);
				
				this.rulesLists = crossRulesGenerator.instantiateRulesSetForUsersInvolved(
						databaseController.savePathsForBPMN(theGeneratedPath),usersInvolved, sessionID);
				
				newBpmn.setAbsoluteBpScore(ComputeLearnerScore.absoluteBP(theGeneratedPath));
				
				absoluteBPScore = newBpmn.getAbsoluteBpScore();
				
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
	public void computeAndPropagateScores(List<String> learnersID, String idPath, String idBPMN) {
		
		int pathsCardinality = databaseController.getBPMNPathsCardinality(idBPMN);
		Date now = new Date();
		
		for(int i = 0; i<learnersID.size(); i++) {
			databaseController.setLearnerSessionScore(
					learnersID.get(i), idPath, idBPMN, 
					ScoreTemporaryStorage.getTemporaryLearnerSessionScore(learnersID.get(i)),
					new java.sql.Date(now.getTime()));
			
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
			
			scoresToShow.put(ScoreType.ABSOLUTE_BP_SCORE, absoluteBPScore);
			scoresToShow.put(ScoreType.ABSOLUTE_GLOBAL_SCORE, learnerAbsoluteGlobalScore);
			scoresToShow.put(ScoreType.ABSOLUTE_SESSION_SCORE, absoluteSessionScore);
			scoresToShow.put(ScoreType.BP_COVERAGE, learnerCoverage);
			scoresToShow.put(ScoreType.BP_SCORE, learnerBPScore);
			scoresToShow.put(ScoreType.GLOBAL_SCORE, learnerGlobalScore);
			scoresToShow.put(ScoreType.RELATIVE_BP_SCORE, learnerRelativeBPScore);
			scoresToShow.put(ScoreType.RELATIVE_GLOBAL_SCORE, learnerRelativeGlobalScore);
			scoresToShow.put(ScoreType.SESSION_SCORE, ScoreTemporaryStorage.getTemporaryLearnerSessionScore(learnersID.get(i)).floatValue());
			
			sendScoresToSim(scoresToShow,learnersID.get(i));
			
			DebugMessages.print(TimeStamp.getCurrentTime(),  this.getClass().getName(),  "Sending score to the platform ");
			sendScoreUpdateEventToCP(
					generateScoreEvent(scoresToShow, ScoreTemporaryStorage.getLastScoreUpdateEventSeen(), learnersID.get(i)));
			DebugMessages.ok();
		}
		
		
	}
	
	private void sendScoresToSim(HashMap<ScoreType, Float> scoresToShow, String user) {
		DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Sending scores to the simulator");
		ResponseDispatcher.sendScoresEvaluation(scoresToShow, "simulator", "scoresUpdateResponses", user);
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


	
	
	public static void main(String[] args)
	{
	 		Properties asd = new Properties();
	 		asd.setProperty("DB_DRIVER", "org.h2.Driver");
	 		asd.setProperty("DB_CONNECTION", "jdbc:h2:./data/glimpse");
	 		asd.setProperty("DB_USER", "");
	 		asd.setProperty("DB_PASSWORD", "");
	 
	 		H2Controller c2 = new H2Controller(asd);
	 		c2.connectToDB();
	 		
	 		LearnerAssessmentManager test = new LearnerAssessmentManagerImpl(c2);
	 
	 		
	 		List<String> ciccio = new ArrayList<>();
	 		ciccio.add("1");
	 		
	 		test.computeAndPropagateScores(ciccio, "a23748293649", "a23748293649-1");
	 		
	 }

}