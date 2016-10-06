/*
 * GLIMPSE: A generic and flexible monitoring infrastructure.
 * For further information: http://labsewiki.isti.cnr.it/labse/tools/glimpse/public/main
 * 
 * Copyright (C) 2011  Software Engineering Laboratory - ISTI CNR - Pisa - Italy
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
*/
package eu.learnpad.simulator.mon.manager;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionType;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.net.ntp.TimeStamp;
import org.apache.xmlbeans.XmlException;
import org.drools.definition.rule.Rule;
import org.drools.definitions.impl.*;

import eu.learnpad.simulator.mon.consumer.ConsumerProfile;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.exceptions.IncorrectRuleFormatException;
import eu.learnpad.simulator.mon.rules.RulesManager;
import eu.learnpad.simulator.mon.storage.ScoreTemporaryStorage;
import eu.learnpad.simulator.mon.utils.DebugMessages;

public class GlimpseManager extends Thread implements MessageListener {

	private TopicConnection connection;
	private TopicSession publishSession;
	private TopicPublisher tPub;
	private Topic connectionTopic;
	private TopicSession subscribeSession;
	private TopicSubscriber tSub;
	private String serviceTopic;
	private String answerTopic;
	private RulesManager rulesManagerOne;
	private LearnerAssessmentManager learnerAssessmentManager;
	private ResponseDispatcher responder;

	public static HashMap<Object, ConsumerProfile> requestMap = new HashMap<Object, ConsumerProfile>();

	/**
	 * @param settings
	 * @param connectionFact
	 * @param initConn
	 * @param rulesManager
	 */
	public GlimpseManager(Properties settings, TopicConnectionFactory connectionFact, InitialContext initConn,
			RulesManager rulesManagerOne, LearnerAssessmentManager lam) {
		serviceTopic = settings.getProperty("serviceTopic");
		this.rulesManagerOne = rulesManagerOne;
		this.learnerAssessmentManager = lam;
		setupConnection(connectionFact, initConn);
		
	}

	public void setupConnection(TopicConnectionFactory connectionFact, InitialContext initConn) {
		try {
			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Creating connection object ");
			connection = connectionFact.createTopicConnection();
			DebugMessages.ok();

			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Creating public session object ");
			publishSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			DebugMessages.ok();

			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Creating subscribe object");
			subscribeSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			DebugMessages.ok();

			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Setting up destination topic ");
			connectionTopic = (Topic) initConn.lookup(serviceTopic);
			tPub = publishSession.createPublisher(connectionTopic);
			DebugMessages.ok();

			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Setting up reading topic ");
			tSub = subscribeSession.createSubscriber(connectionTopic, "DESTINATION = 'monitor'", true);
			tSub.setMessageListener(this);
			DebugMessages.ok();

			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Creating response dispatcher ");
			responder = new ResponseDispatcher(initConn, connectionFact, requestMap, learnerAssessmentManager);
			DebugMessages.ok();

		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void onMessage(Message messagePayload) {

		TextMessage msg = null;
		try {
			msg = (TextMessage) messagePayload;
			DebugMessages.line();
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"receive " + msg.getText());
			DebugMessages.line();
			String xmlMessagePayload = msg.getText();
			String sender = msg.getStringProperty("SENDER");
			ComplexEventRuleActionListDocument ruleDoc;

			// check if the paylod of the message is a bpmn to be used for path
			// extraction and rules generation
			// or if the xml is already a rule to inject into the engine
			if (xmlMessagePayload.contains("http://www.omg.org/spec/BPMN/20100524/MODEL")) {
				DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
						"The message sent seems to contain a BPMN - Forwarding it to the LearnPAd Assessment Manager");
				
				@SuppressWarnings("unchecked")
				List<String> learnersIDs = (List<String>) msg.getObjectProperty("USERSINVOLVEDID");	
				String bpmnID = msg.getObjectProperty("BPMNID").toString();
				String sessionID = msg.getObjectProperty("SESSIONID").toString();	
				
				Vector<Learner> learnersInvolved = learnerAssessmentManager.getDBController().getOrSetLearners(learnersIDs); 
				
				ScoreTemporaryStorage sessionScoreBuffer = new ScoreTemporaryStorage(learnersInvolved, sessionID);
				
				ruleDoc = learnerAssessmentManager.elaborateModel(xmlMessagePayload, learnersInvolved, sessionID, bpmnID);

			} else {
				ruleDoc = ComplexEventRuleActionListDocument.Factory.parse(xmlMessagePayload);
			}
			ComplexEventRuleActionType rules = ruleDoc.getComplexEventRuleActionList();

			// the topic where the listener will give analysis results
			answerTopic = "answerTopic" + "#" + this.getName() + "#" + System.nanoTime();

			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Create answerTopic");
			connectionTopic = publishSession.createTopic(answerTopic);
			// tPub = publishSession.createPublisher(connectionTopic);
			DebugMessages.ok();

			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
					"Setting up ComplexEventProcessor with new rule.");
			try {
				Object[] loadedKnowledgePackage = rulesManagerOne.loadRules(rules);
				// inserisco la coppia chiave valore dove la chiave è il KnowledgePackage
				// caricato, generato da DroolsRulesManager con la loadRules
				// e il valore è l'enabler che l'ha inviata
				// (il KnowledgePackage array dovrebbe avere sempre dimensione 1
				// essendo creato ad ogni loadrules)
				for (int i = 0; i < loadedKnowledgePackage.length; i++) {
					KnowledgePackageImp singleKnowlPack = (KnowledgePackageImp) loadedKnowledgePackage[i];
					Rule[] singleRuleContainer = new Rule[singleKnowlPack.getRules().size()];
					singleRuleContainer = singleKnowlPack.getRules().toArray(singleRuleContainer);

					for (int j = 0; j < singleRuleContainer.length; j++) {
						requestMap.put(singleRuleContainer[j].getName(), new ConsumerProfile(sender, answerTopic));
					}
				}
				DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
						"KnowledgeBase packages loaded: " + rulesManagerOne.getLoadedKnowledgePackageCardinality());
				DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
						"Communicate the answerTopic to the requester");
				sendMessage(createMessage("AnswerTopic == " + answerTopic, sender));
				DebugMessages.ok();
			} catch (IncorrectRuleFormatException e) {
				sendMessage(createMessage("PROVIDED RULE CONTAINS ERRORS", sender));
			}
			
//TODO:////////TESTTTTTTTTTTTTTTTTT///////////////////////////
//			ResponseDispatcher.saveAndNotifyLearnersScore("1-2-6", "a23748293649", 5, 300.0f);
			
		} catch (NullPointerException asd) {
			try {
				sendMessage(createMessage("PROVIDED RULE IS NULL, PLEASE PROVIDE A VALID RULE",
						msg.getStringProperty("SENDER")));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} catch (XmlException e) {
			try {
				sendMessage(createMessage("PROVIDED XML CONTAINS ERRORS", msg.getStringProperty("SENDER")));
			} catch (JMSException e1) {
				e1.printStackTrace();
			}
		} catch (JMSException ee) {
			ee.printStackTrace();
		}
		DebugMessages.line();
	}

	public void run() {
		DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Starting connection ");
		try {
			connection.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		DebugMessages.ok();
		DebugMessages.line();
		System.out.println(this.getClass().getSimpleName() + ": is now ready to accept incoming requests ");
		DebugMessages.line();
	}

	private TextMessage createMessage(String msg, String sender) {
		try {
			TextMessage sendMessage = publishSession.createTextMessage();
			sendMessage.setText(msg);
			sendMessage.setStringProperty("DESTINATION", sender);
			return sendMessage;
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void sendMessage(TextMessage msg) {
		try {
			if (msg != null) {
				// System.out.println(this.getClass().getSimpleName() + ": send
				// " + msg.getText());
				// DebugMessages.line();
				tPub.publish(msg);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
