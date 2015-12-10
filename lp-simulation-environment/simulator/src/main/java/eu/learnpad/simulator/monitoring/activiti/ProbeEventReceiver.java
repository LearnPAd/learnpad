/**
 *
 */
package eu.learnpad.simulator.monitoring.activiti;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import it.cnr.isti.labsedc.glimpse.event.GlimpseBaseEvent;
import it.cnr.isti.labsedc.glimpse.event.GlimpseBaseEventBPMN;
import it.cnr.isti.labsedc.glimpse.probe.GlimpseAbstractProbe;
import it.cnr.isti.labsedc.glimpse.utils.Manager;

import javax.jms.JMSException;
import javax.naming.NamingException;

import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.monitoring.event.impl.ProcessEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.ProcessStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SessionScoreUpdateSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskStartSimEvent;

/**
 * Monitor simulation sessions in order to notify Glimpse server of various
 * events
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ProbeEventReceiver extends GlimpseAbstractProbe implements
IProcessEventReceiver {

	/**
	 * @param settings
	 */
	public ProbeEventReceiver() {
		super(Manager.createProbeSettingsPropertiesObject(
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory",
				"tcp://atlantis.isti.cnr.it:61616", "system", "manager",
				"TopicCF", "jms.probeTopic", false, "probeName", "probeTopic"));
	}

	/**
	 * Send a notification to the monitor
	 *
	 * @param event
	 */
	protected void send(GlimpseBaseEventBPMN<String> event) {
		try {
			this.sendEventMessage(event, false);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.activiti.engine.delegate.event.ActivitiEventListener#isFailOnException
	 * ()
	 */
	public boolean isFailOnException() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.cnr.isti.labsedc.glimpse.probe.GlimpseAbstractProbe#sendMessage(it
	 * .cnr.isti.labsedc.glimpse.event.GlimpseBaseEvent, boolean)
	 */
	@Override
	public void sendMessage(GlimpseBaseEvent<?> event, boolean debug) {
		// nothing to do here(?)
	}

	@Override
	public void receiveSimulationStartEvent(SimulationStartSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				null, event.simulationsessionid, event.timestamp, event
				.getType().toString(), false, "",
				event.simulationsessionid, null, null, null, null, null);

		send(monitoringEvent);

	}

	@Override
	public void receiveSimulationEndEvent(SimulationEndSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				null, event.simulationsessionid, event.timestamp, event
				.getType().toString(), false, "",
				event.simulationsessionid, null, null, null, null, null);

		send(monitoringEvent);

	}

	@Override
	public void receiveProcessStartEvent(ProcessStartSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				null, event.simulationsessionid, event.timestamp, event
				.getType().toString(), false, "",
				event.simulationsessionid, null, null, null, null, null);

		send(monitoringEvent);

	}

	@Override
	public void receiveProcessEndEvent(ProcessEndSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				null, event.simulationsessionid, event.timestamp, event
						.getType().toString(), false, "",
				event.simulationsessionid, null, null, null, null, null);
		send(monitoringEvent);

	}

	@Override
	public void receiveTaskStartEvent(TaskStartSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp, event.simulationsessionid,
				System.currentTimeMillis(), event.getType().toString(), false,
				"", event.simulationsessionid, null, null, event.task.key,
				event.task.subprocessKey, null);
		send(monitoringEvent);

	}

	@Override
	public void receiveTaskEndEvent(TaskEndSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp, event.simulationsessionid,
				System.currentTimeMillis(), event.getType().toString(), false,
				"", event.simulationsessionid, event.completingUser, null,
				event.task.key, event.task.subprocessKey, null);
		send(monitoringEvent);

	}

	@Override
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp, event.simulationsessionid,
				System.currentTimeMillis(), event.getType().toString(), false,
				event.sessionscore.toString(), event.simulationsessionid,
				event.user, null, null, null, null);
		send(monitoringEvent);

	}

}
