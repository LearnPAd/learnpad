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
package probe;

import java.net.UnknownHostException;
import java.util.Properties;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.sim.rest.event.AbstractEvent;
import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.ProcessStartEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.SimulationEndEvent;
import eu.learnpad.sim.rest.event.impl.SimulationStartEvent;
import eu.learnpad.sim.rest.event.impl.TaskEndEvent;
import eu.learnpad.sim.rest.event.impl.TaskFailedEvent;
import eu.learnpad.sim.rest.event.impl.TaskStartEvent;
import eu.learnpad.simulator.mon.event.GlimpseBaseEvent;
import eu.learnpad.simulator.mon.event.GlimpseBaseEventBPMN;
import eu.learnpad.simulator.mon.probe.GlimpseAbstractProbe;
import eu.learnpad.simulator.mon.probe.GlimpseProbe;
import eu.learnpad.simulator.mon.utils.Manager;
import eu.learnpad.simulator.mon.utils.DebugMessages;


/**
 * This class provides an example of how to send messages (events) to the Glimpse Monitoring Bus,
 * <br />there are three methods for sending event on which payload is <br /><br />
 * a String: {@link MyGlimpseProbe#generateAndSendExample_GlimpseBaseEvents_StringPayload(int)},<br />
 * an Object: {@link MyGlimpseProbe#generateAndSendExample_GlimpseBaseEvents_ObjectPayload(int)},<br />
 * an Exception: {@link MyGlimpseProbe#generateAndSendExample_GlimpseBaseEvents_ExceptionPayload(Exception)}<br /><br />
 * the sendEvent action behavior may be modified simply implementing the abstract method <br />
 * {@link GlimpseAbstractProbe#sendMessage(GlimpseBaseEvent, boolean)}.<br /<br />
 * In this class we used the classic sendEventMessage method provided by the abstract class {@link GlimpseAbstractProbe}<br />
 * <br />
 * You can directly implement your probe extending {@link GlimpseAbstractProbe} or directly implementing {@link GlimpseProbe}<br /><br />
 * @author Antonello Calabr&ograve;
 * @version 3.3.1
 *
 */

public class MyGlimpseProbe_SimMonLearnPAd extends GlimpseAbstractProbe {

	public MyGlimpseProbe_SimMonLearnPAd(Properties settings) {
		super(settings);
	}


	private void generateAndSendExample_GlimpseBaseEvents_StringPayload(String data, AbstractEvent event) throws UnknownHostException {
		DebugMessages.print(TimeStamp.getCurrentTime(),MyGlimpseProbe_SimMonLearnPAd.class.getName(),
				"Creating GlimpseBaseEvent_SimMonLearnPAd message");
		GlimpseBaseEventBPMN<String> message;
		DebugMessages.ok();
		DebugMessages.line();
		message = new GlimpseBaseEventBPMN<String>("eventForCP", "aProbe", System.currentTimeMillis(), data, false, "extraField", event);
		
		
		try {
			this.sendEventMessage(message, false);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	

	/*
	 * To override the sendMessage, you can use the following code
	 * 
	 * @Override
	 * public void sendMessage(GlimpseBaseEvent<?> event, boolean debug) {
	 * 	//YOUR SENDING IMPLEMENTATION
	 * }
	 * 
	 * */
	
	@Override
	public void sendMessage(GlimpseBaseEvent<?> event, boolean debug) {		
	}

	public static void main(String[] args) throws UnknownHostException {
		
		MyGlimpseProbe_SimMonLearnPAd aGenericProbe = new
				MyGlimpseProbe_SimMonLearnPAd(Manager.createProbeSettingsPropertiesObject(
								"org.apache.activemq.jndi.ActiveMQInitialContextFactory",
								"tcp://localhost:61616",
								"system",
								"manager",
								"TopicCF",
								"jms.probeTopic",
								false,
								"probeName",
								"probeTopic")
								);
		
		DebugMessages.println(TimeStamp.getCurrentTime(), MyGlimpseProbe_SimMonLearnPAd.class.getName(),"Sending a set of events:\nsimstart, processstart, taskstart, taskfailed, taskstart, taskend, processend, sessionscoreupdate, simendinfinite");	
		try {
			AbstractEvent event;
			event = new SimulationStartEvent();
			aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload("SIMULATION_START", event);
					
			Thread.sleep(2000);
			
			event = new ProcessStartEvent();
			aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload("PROCESS_START", event);
			
			Thread.sleep(2000);
			
			event = new TaskStartEvent();
			aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload("TASK_START", event);
			
			Thread.sleep(2000);
			
			event = new TaskFailedEvent();
			aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload("TASK_FAILED", event);
			
			Thread.sleep(2000);
			
			event = new TaskStartEvent();
			aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload("TASK_START", event);
			
			Thread.sleep(2000);
			
			event = new TaskEndEvent();
			aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload("TASK_END", event);
			
			Thread.sleep(2000);
			
			event = new ProcessEndEvent();
			aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload("PROCESS_END", event);
			
			Thread.sleep(2000);
			
			event = new SessionScoreUpdateEvent();
			aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload("SESSION_SCORE_UPDATE", event);
			
			Thread.sleep(2000);
			
			event = new SimulationEndEvent();
			aGenericProbe.generateAndSendExample_GlimpseBaseEvents_StringPayload("SIMULATION_END", event);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
