package eu.learnpad.simulator.mon.manager;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import eu.learnpad.simulator.mon.controller.DBController;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public abstract class LearnerAssessmentManager extends Thread {

	public abstract Document setBPModel(String xmlMessagePayload) throws ParserConfigurationException, SAXException, IOException;
	//public abstract ComplexEventRuleActionListDocument ExploreBPSavePathsGenerateAndSaveRules(Document dom);
	public abstract ComplexEventRuleActionListDocument elaborateModel(String xmlMessagePayload, Vector<Learner> vector, String sessionID, String bpmnID);

	public abstract DBController getDBController();
	public abstract void computeAndSaveScores(String learnersID, int idPath, String idBPMN, float sessionScore);
	public abstract Vector<Path> setAllAbsoluteSessionScores(Vector<Path> theGeneratedPath);
}
