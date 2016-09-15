package eu.learnpad.simulator.mon.manager;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.storage.DBController;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public abstract class LearnerAssessmentManager extends Thread {

	public abstract Document setBPModel(String xmlMessagePayload) throws ParserConfigurationException, SAXException, IOException;
	public abstract ComplexEventRuleActionListDocument elaborateModel(String xmlMessagePayload, Vector<Learner> vector, String sessionID, String bpmnID);
	public abstract DBController getDBController();
	public abstract Vector<Path> setAllAbsoluteSessionScores(Vector<Path> theGeneratedPath);
	public abstract void computeAndPropagateScores(List<String> learnersID, String idPath, String idBPMN);
}
