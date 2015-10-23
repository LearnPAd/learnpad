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
package eu.learnpad.simulator.mon.consumer;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.xmlbeans.XmlException;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;


/**
 * Implementing this interface to create
 * a communication with the monitoring Enabler.<br /><br />
 * To correctly use the monitoring, the connection parameters
 * must be configured using the following methods:<br />
 * {@link #initConnection(Properties, boolean)}<br />
 * {@link #createConnection(InitialContext, Properties, boolean)}<br /><br />
 * <br />
 * How the Monitoring Enabler works:<br />
 *  -> To request an evaluation to the monitoring enabler, a client<br />
 *  must be able to send a well formed request: a JMS Message which payload<br />
 *  is an xml containing a set of actions expressed using the <br />
 *  ComplexEventRule.xsd schema.<br />
 *  To aid on the creation of the XML, you can use the class ComplexEventRuleActionListDocument.<br />
 *  Possible XML actions are: Insert Delete Start Stop Restart.<br /><br />
 *  When a well structured message (see exampleRule.xml in the APITestProject example) is received from the monitoring infrastructure<br />
 *  the monitoring will provide a responseMessage (TextMessage) containing the name<br />
 *  of the private topic where all the messages related to the requested evaluation will be sent.<br />
 *  When the enabler receives this message, should subscribes the new channel.<br />
 *  For this action, it can be use the method {@link #connectToTheResponseChannel(TopicConnection, String, boolean)}<br /><br />
 *  
 * ************************************************************************<br />
 * **************An usage example is available here {@link TestClass}**************<br />
 * ************************************************************************<br /><br />
 * 
 * 	@see #initConnection(Properties, boolean) initConnection method set InitialContext <br />
 * 	@see #createConnection(InitialContext, Properties, boolean) set TopicConnectionFactory <br /> <br />
 * 
 * @author Antonello Calabr&ograve;
 * @version 0.2
 * 
 */
public interface MonitorConsumer {

	/**
	 * @param publishSession the TopicSession where to send the message
	 * @param debug debug value
	 * @return a TextMessage that can be sent to the monitoring infrastructure<br />
	 * to obtain the Status of the monitoring enabler<br />
	 * that is defined using the enum {@link Status}
	 */
	TextMessage getMonitorStatusMessage(TopicSession publishSession, boolean debug);
	
	/**
	 * The returned Properties object can be used to 
	 * simply setup connection using it with the following methods:<br />
	 * <br />
	 * InitialContext initConnection(Properties settings)<br />
	 * TopicConnectionFactory createConnectionFactory(InitialContext initConn, Properties settings)
	 * 
	 * @param javaNamingFactoryInitial eg: org.apache.activemq.jndi.ActiveMQInitialContextFactory
	 * @param javaNamingProviderUrl eg: tcp://atlantis.isti.cnr.it:61616
	 * @param javaNamingSecurityPrincipal eg: system
	 * @param javaNamingSecurityCredential eg: manager
	 * @param connectionFactoryNames eg: TopicCF
	 * @param topicServiceTopic eg: jms.serviceTopic
 	 * @param debug debug value
	 * @return a Properties object that can be used to setup the connection
	 */
	Properties createSettingsPropertiesObject(String javaNamingFactoryInitial,
			String javaNamingProviderUrl,
			String javaNamingSecurityPrincipal,
			String javaNamingSecurityCredential,
			String connectionFactoryNames,
			String topicServiceTopic, boolean debug);
	
	/**
	 * @param settings the environment JMS settings, @see {@link #createSettingsPropertiesObject(String, String, String, String, String, String, boolean)} method
	 * @param debug debug value
	 * @return the JMS InitialContext
	 * @throws NamingException
	 */
	InitialContext initConnection(Properties settings, boolean debug) throws NamingException;
	
	/**
	 * @param initConn 
	 * @param settings
	 * @param debug debug value
	 * @return the topicConnection to communicate with the monitoring infrastructure
	 * @throws NamingException
	 */
	TopicConnection createConnection(InitialContext initConn, Properties settings, boolean debug) throws NamingException, JMSException;

	/**
	 * Send a ActionList of rules to the monitoring infrastructure requesting an evaluation.<br />
	 * If the request is accepted, the monitoring will answer with a responseChannel where to connect<br />
	 * The actionList parameter is a list of action to provide to the Monitoring<br />
	 * To create it, you can use ComplexEventRuleActionListDocument class.
	 * 
	 * @param connection
	 * @param initContext
	 * @param serviceChannel
	 * @param actionList
	 * @param debug debug value
	 * @throws JMSException
	 * @throws NamingException
	 */
	void sendActionListMessage(TopicConnection connection, InitialContext initContext, String serviceChannel, ComplexEventRuleActionListDocument actionList, boolean debug) throws JMSException, NamingException;

	/**
	 * Send a textMessage to the monitoring infrastructure,
	 * you can use it after setting up connection, see 
	 * {@link #createConnection(InitialContext, Properties, boolean)} method
	 * and {@link #initConnection(Properties, boolean)} method
	 * 
	 * @param connection
	 * @param initContext
	 * @param serviceChannel
	 * @param textToSend
	 * @param debug debug value
	 * @throws JMSException
	 * @throws NamingException
	 */
	void sendTextMessage(TopicConnection connection, InitialContext initContext, String serviceChannel, String textToSend, boolean debug) throws JMSException, NamingException;
	
	/**
	 * This class creates the Subscriber to receive answers from monitoring<br />
	 * must be used after the connection init and setup, see<br />
	 * {@link #createConnection(InitialContext, Properties, boolean)} method
	 * and {@link #initConnection(Properties, boolean)} method
	 * 
	 * @param connection the TopicConnection, can be created using<br />
	 * {@link #createConnection(InitialContext, Properties, boolean)} method
	 * @param initContext the JMS InitialContext, can be created using<br />
	 * {@link #initConnection(Properties, boolean)}  method
	 * 
	 * @param serviceChannel the channel where the monitoring is listening for requests<br />
	 * the default channel is serviceTopic
	 * @param debug debug value
	 * @return a topic Subscriber to receive answers from monitoring.
	 * @throws JMSException
	 * @throws NamingException
	 */
	TopicSubscriber createSubscriber(TopicConnection connection, InitialContext initContext, String serviceChannel, boolean debug) throws JMSException, NamingException;
		
	/**
	 * @param xmlRule a string that contains the xml of the rule to evaluate<br />
	 * the rule must be expressed using the ComplexEventRuleAction format,<br />
	 * for more information see ComplexEventRule documentation
	 * @param debug debug value
	 * @return a ComplexEventRuleActionListDocument object
	 * @throws XmlException
	 * @throws JMSException
	 */
	ComplexEventRuleActionListDocument createComplexEventRuleActionDocumentFromXMLString(String xmlRule, boolean debug) throws XmlException, JMSException;
	
	/**
	 * this method aid to switch channel after receiving a response from the<br />
	 * monitoring infrastructure. After sending a request, the monitor<br />
	 * will provide a new channel where to listen for response.
	 * Using this method is possible to obtain a new TopicSubscriber<br />
	 * for the new channel and set it on the MessageListener of the class
	 * tSub.setMessageListener(this);
	 * 
	 * @param connection the connection to the server
	 * @param responseChannel the channel where to connect to receive response
	 * @param debug debug value
	 * @return the new topic where to listen for response
	 * @throws JMSException
	 */
	TopicSubscriber connectToTheResponseChannel(TopicConnection connection, String responseChannel, boolean debug) throws JMSException;
	
	/**
	 * @param msg the message received from the monitoring<br />
	 * after sending a request
	 * @return a string containing the name of the topic where <br />
	 * to connect to fetch the evaluation results.
	 */
	String getAnswerTopicFromTextMessage(TextMessage msg) throws JMSException;
}