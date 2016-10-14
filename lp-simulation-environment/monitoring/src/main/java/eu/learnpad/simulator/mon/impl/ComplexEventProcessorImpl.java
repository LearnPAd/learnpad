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
package eu.learnpad.simulator.mon.impl;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.net.ntp.TimeStamp;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

import eu.learnpad.simulator.mon.buffer.EventsBuffer;
import eu.learnpad.simulator.mon.cep.ComplexEventProcessor;
import eu.learnpad.simulator.mon.event.GlimpseBaseEvent;
import eu.learnpad.simulator.mon.event.GlimpseBaseEventBPMN;
import eu.learnpad.simulator.mon.exceptions.UnknownMethodCallRuleException;
import eu.learnpad.simulator.mon.rules.DroolsRulesManager;
import eu.learnpad.simulator.mon.rules.RulesManager;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import eu.learnpad.simulator.mon.utils.Manager;

public class ComplexEventProcessorImpl extends ComplexEventProcessor implements MessageListener {

	private String topic;
	private TopicConnection connection;
	private Topic connectionTopic;
	private TopicSession publishSession;
	private TopicSession subscribeSession;
	@SuppressWarnings("unused")
	private TopicPublisher tPub;
	private TopicSubscriber tSub;
	private KnowledgeBase knowledgeBase;
	private StatefulKnowledgeSession ksession;
	private WorkingMemoryEntryPoint eventStream;
	private KnowledgeBuilder knowledgeBuilder;
	
	public ComplexEventProcessorImpl(Properties settings, EventsBuffer<GlimpseBaseEvent<?>> buffer, TopicConnectionFactory connectionFact,
			InitialContext initConn) {
		this.topic = settings.getProperty("probeTopic");
		
		init(connectionFact,initConn);
	}

	public void init(TopicConnectionFactory connectionFact,
			InitialContext initConn) {
		try {
			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Creating connection object ");
			connection = connectionFact.createTopicConnection();
			DebugMessages.ok();

			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Creating public session object ");
			publishSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			DebugMessages.ok();
			
			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Creating subscribe object ");
			subscribeSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			DebugMessages.ok();
			
			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Setting up reading topic ");
			connectionTopic = (Topic) initConn.lookup(topic);
			tSub = subscribeSession.createSubscriber(connectionTopic, null,true);
			DebugMessages.ok();
			
			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Setting up destination topic ");
			connectionTopic = publishSession.createTopic(this.topic);
			tPub = publishSession.createPublisher(connectionTopic);
			DebugMessages.ok();	

			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Reading knowledge base ");

			knowledgeBase = createKnowledgeBase();
			ksession = knowledgeBase.newStatefulKnowledgeSession();
			ksession.setGlobal("EVENTS EntryPoint", eventStream);
			eventStream = ksession.getWorkingMemoryEntryPoint("DEFAULT");
			cepRuleManager = new DroolsRulesManager(knowledgeBuilder, knowledgeBase, ksession);
			DebugMessages.ok();
			
		} catch (JMSException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),e.getMessage());
		} catch (NamingException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),e.getMessage());
		}		
	}
	
	public void run()
	{
		DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Starting connection ");
		try {
			if (connection == null) {
				System.out.println("Unable to connect to ActiveMQ instance or connection parameters wrong.\nPlease check and restart GLIMPSE.");
				System.exit(0);
			}
			connection.start();
			DebugMessages.ok();
			tSub.setMessageListener(this);
			DebugMessages.line();
			//while (this.getState() == State.RUNNABLE) {
		        ksession.fireUntilHalt();
		        //} 
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(Message arg0) {
		
		ObjectMessage msg = (ObjectMessage) arg0;
		try {
			GlimpseBaseEvent<?> receivedEvent = (GlimpseBaseEvent<?>) msg.getObject();
			if (eventStream != null && receivedEvent != null) {
				try {
					eventStream.insert(receivedEvent);
					
						if (receivedEvent instanceof GlimpseBaseEventBPMN<?>) {
							DebugMessages.println(
								TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
								"receives:\n" +
								"eventData: " + receivedEvent.getEventData() + "\n" +
								"eventName: " + receivedEvent.getEventName() + "\n" +
								"timestamp: " + receivedEvent.getTimeStamp() + "\n" +
								"event: " + ((GlimpseBaseEventBPMN<?>) receivedEvent).getEvent()
								);	
						} else {
						DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),
								"receives:\n" +
								"eventData: " + receivedEvent.getEventData() + "\n" +
								"eventName: " + receivedEvent.getEventName() + "\n" +
								"timestamp: " + receivedEvent.getTimeStamp());
					}
					DebugMessages.line();
				} catch(org.drools.RuntimeDroolsException droolsCrashException) {
					DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), droolsCrashException.getMessage());
					new UnknownMethodCallRuleException();
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		catch(ClassCastException ex) {
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), ex.getMessage());
		}
	}
	
	private KnowledgeBase createKnowledgeBase() {
		try {				
			KnowledgeBaseConfiguration config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
			config.setOption(EventProcessingOption.STREAM);
			
			/* Using knowledge builder to create a knowledgePackage using provided resources (drl file)
			 * after the creation the knowledgePackage contained into the knowledge builder will be putted
			 * into the knowledgeBase using the method addKnowledgePackages(knowledgeBuilder.getKnowledgePackages())
			 */				

			knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			
			byte[] firstRuleToLoadByteArray = Manager.ReadTextFromFile(System.getProperty("user.dir")	+ "/configFiles/startupRule.drl").getBytes();
			Resource drlToLoad = ResourceFactory.newByteArrayResource(firstRuleToLoadByteArray);
			
			try {
				knowledgeBuilder.add(drlToLoad,ResourceType.DRL);
			} catch(Exception asd) { 
				System.out.println(asd.getMessage());
			}
			
			KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
			if (errors.size() > 0) {
				for (KnowledgeBuilderError error : errors) {
					System.err.println(error);
				}
				throw new IllegalArgumentException("Could not parse knowledge.");
			}
			knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(config);
			knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
			return knowledgeBase;
		}
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(),e.getMessage());
			return null;
		}
	}

	@Override
	public void setMetric() {		
	}

	public RulesManager getRuleManager() {
		return this.cepRuleManager;
	}
}
