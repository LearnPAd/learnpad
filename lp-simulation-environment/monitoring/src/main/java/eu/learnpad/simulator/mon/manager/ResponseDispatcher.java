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

import it.cnr.isti.labse.glimpse.xml.complexEventException.ComplexEventException;
import it.cnr.isti.labse.glimpse.xml.complexEventResponse.ComplexEventResponse;
import it.cnr.isti.labse.glimpse.xml.complexEventResponse.ComplexEventResponseListDocument;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.sim.rest.event.ScoreType;
import eu.learnpad.simulator.mon.consumer.ConsumerProfile;
import eu.learnpad.simulator.mon.utils.DebugMessages;

public class ResponseDispatcher {

	private static Topic connectionTopic;
	@SuppressWarnings("unused")
	private static InitialContext initConn;
	private static TopicSession publishSession;
	private static TopicPublisher tPub;
	private static HashMap<Object, ConsumerProfile> requestMap;
	private static TopicConnection connection;
	
	@SuppressWarnings("unused")
	private static TopicSession publicSession;
	private static LearnerAssessmentManager lam;
	
	public ResponseDispatcher(InitialContext initConn,
			TopicConnectionFactory connectionFact,
			HashMap<Object, ConsumerProfile> requestMap) {

		ResponseDispatcher.requestMap = requestMap;
		ResponseDispatcher.initConn = initConn;
		try {
			connection = connectionFact.createTopicConnection();
			publishSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}		
	}
	
	public ResponseDispatcher(InitialContext initConn,
			TopicConnectionFactory connectionFact,
			HashMap<Object, ConsumerProfile> requestMap, LearnerAssessmentManager learnerAssessmentManager) {

		ResponseDispatcher.requestMap = requestMap;
		ResponseDispatcher.initConn = initConn;
		ResponseDispatcher.lam = learnerAssessmentManager;
		try {
			connection = connectionFact.createTopicConnection();
			publishSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}		
	}

	public static void sendResponse(ComplexEventResponse response, String enablerName, String answerTopic)
	{
		try {
			publicSession = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
			connectionTopic = publishSession.createTopic(answerTopic);
			tPub = publishSession.createPublisher(connectionTopic);
			
			ObjectMessage sendMessage = publishSession.createObjectMessage();
			sendMessage.setObject((Serializable) response);
			sendMessage.setStringProperty("DESTINATION", enablerName);
			tPub.publish(sendMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendResponse(ComplexEventException exception, String enablerName, String answerTopic)
	{
		try {
			publicSession = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
			connectionTopic = publishSession.createTopic(answerTopic);
			tPub = publishSession.createPublisher(connectionTopic);
			
			ObjectMessage sendMessage = publishSession.createObjectMessage();
			sendMessage.setObject((Serializable) exception);
			sendMessage.setStringProperty("DESTINATION", enablerName);
			tPub.publish(sendMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void setPathCompletedAndPropagateScores(String learnersID, String idPath, String idBPMN)
	{
		ResponseDispatcher.lam.computeAndPropagateScores(new ArrayList<String>(Arrays.asList(learnersID.split(","))), idPath, idBPMN);
	}

	
	
	public static void sendResponse(Object object, String enablerName, String answerTopic)
	{
		try {
			publicSession = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
			connectionTopic = publishSession.createTopic(answerTopic);
			tPub = publishSession.createPublisher(connectionTopic);
			
			ObjectMessage sendMessage = publishSession.createObjectMessage();
			sendMessage.setObject((Serializable) object);
			sendMessage.setStringProperty("DESTINATION", enablerName);
			tPub.publish(sendMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendScoresEvaluation(HashMap<ScoreType, Float> scores, String destination, String channel, String userid)
	{
		try {
			publicSession = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
			connectionTopic = publishSession.createTopic(channel);
			tPub = publishSession.createPublisher(connectionTopic);
			
			ObjectMessage sendMessage = publishSession.createObjectMessage();
			sendMessage.setObject((Serializable) scores);
			sendMessage.setStringProperty("DESTINATION", destination);
			sendMessage.setStringProperty("USERID", userid);
			tPub.publish(sendMessage);
		} catch (JMSException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), ResponseDispatcher.class.getSimpleName(),  "Exception during sendScoresEvaluation method execution");
		}
	}
	
		
	public static void NotifyMeValue(String ruleMatched, String enablerName, String key, String value)
	{
		ConsumerProfile enablerMatched = (ConsumerProfile)requestMap.get(ruleMatched);
		
		ComplexEventResponseListDocument rsp;
		rsp = ComplexEventResponseListDocument.Factory.newInstance();
		ComplexEventResponse response = rsp.addNewComplexEventResponseList();
		response.setRuleName(ruleMatched);
		response.setResponseKey(key);
		response.setResponseValue(value);
		
		ResponseDispatcher.sendResponse(response, enablerName, enablerMatched.getAnswerTopic());
		DebugMessages.print(TimeStamp.getCurrentTime(), ResponseDispatcher.class.getSimpleName(),
				"ruleMatched: " + ruleMatched
				+ " - enablerName: " + enablerName
				+ " - evaluationResult: " + value.toString());
	}
	
	public static void NotifyMeObject(String ruleMatched, String enablerName, Object object)
	{
		ConsumerProfile enablerMatched = (ConsumerProfile)requestMap.get(ruleMatched);
		
		ResponseDispatcher.sendResponse(object, enablerName, enablerMatched.getAnswerTopic());
		DebugMessages.print(TimeStamp.getCurrentTime(), ResponseDispatcher.class.getSimpleName(),
				"ruleMatched: " + ruleMatched
				+ " - enablerName: " + enablerName
				+ " - object: " + object.toString());
	}
	
	public static void NotifyMeException(String ruleMatched,
			String enablerName, Exception exception)
	{
		ConsumerProfile enablerMatched = (ConsumerProfile)requestMap.get(ruleMatched);
		
		ComplexEventException exceptionRaised = ComplexEventException.Factory.newInstance();
		if (exception.getCause() == null) {
			exceptionRaised.setCauseClassName("null");
		} else {
			exceptionRaised.setCauseClassName(exception.getCause().getClass().getName());
		}
		exceptionRaised.setClassName(exception.getClass().getName());
		exceptionRaised.setLocalizedMessage(exception.getLocalizedMessage());
		exceptionRaised.setMessage(exception.getMessage());
		exceptionRaised.setStackTrace(exception.getStackTrace().toString());

		ResponseDispatcher.sendResponse(exceptionRaised, enablerName, enablerMatched.getAnswerTopic());
		
		DebugMessages.print(TimeStamp.getCurrentTime(), ResponseDispatcher.class.getSimpleName(),
				"ruleMatched: " + ruleMatched
				+ " - enablerName: " + enablerName
				+ " - evaluationResult: " + exceptionRaised.getClassName());
	}
	
	public static void LogViolation(String ruleMatched,
			String whoGenerateIt, String whatToLog)
	{
		DebugMessages.line();
		DebugMessages.line();
		DebugMessages.line();
		DebugMessages.println(TimeStamp.getCurrentTime(), ResponseDispatcher.class.getSimpleName(),
				"ruleMatched:\n" + ruleMatched
				+ "whoGeneratedIt: " + whoGenerateIt
				+ "\nwhat happens: " + whatToLog);
		DebugMessages.line();
		DebugMessages.line();
		DebugMessages.line();
	}
}
