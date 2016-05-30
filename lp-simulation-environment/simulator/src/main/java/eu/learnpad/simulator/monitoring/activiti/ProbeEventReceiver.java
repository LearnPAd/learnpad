/**
 *
 */
package eu.learnpad.simulator.monitoring.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.naming.NamingException;

import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.ProcessStartEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.SimulationEndEvent;
import eu.learnpad.sim.rest.event.impl.SimulationStartEvent;
import eu.learnpad.sim.rest.event.impl.TaskEndEvent;
import eu.learnpad.sim.rest.event.impl.TaskFailedEvent;
import eu.learnpad.sim.rest.event.impl.TaskStartEvent;
import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.mon.event.GlimpseBaseEvent;
import eu.learnpad.simulator.mon.event.GlimpseBaseEventBPMN;
import eu.learnpad.simulator.mon.probe.GlimpseAbstractProbe;
import eu.learnpad.simulator.mon.utils.Manager;
import eu.learnpad.simulator.monitoring.event.impl.ProcessEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.ProcessStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SessionScoreUpdateSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskFailedSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskStartSimEvent;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;
import eu.learnpad.simulator.utils.SimulatorProperties;

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

/**
 * Monitor simulation sessions in order to notify Glimpse server of various
 * events
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ProbeEventReceiver extends GlimpseAbstractProbe implements
		IProcessEventReceiver {

	private final IProcessManager manager;

	/**
	 * @param settings
	 */
	public ProbeEventReceiver(IProcessManager manager) {
		super(Manager.createProbeSettingsPropertiesObject(
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory",
				SimulatorProperties.props
						.getProperty(SimulatorProperties.PROP_GLIMPSE_SERVER),
				"system", "manager", "TopicCF", "jms.probeTopic", false,
				"probeName", "probeTopic"));

		this.manager = manager;
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
				"Activity_" + event.timestamp,
				event.simulationsessionid,
				event.timestamp,
				event.getType().toString(),
				false,
				"",
				new SimulationStartEvent(
						event.timestamp,
						event.simulationsessionid,
						new ArrayList<String>(event.involvedusers),
						manager.getModelSetIdFromSessionId(event.simulationsessionid),
//						manager.getSimulationSessionParametersData(event.simulationsessionid)));
						getCleanSessionParameters(event.simulationsessionid)));

		send(monitoringEvent);

	}

	@Override
	public void receiveSimulationEndEvent(SimulationEndSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp,
				event.simulationsessionid,
				event.timestamp,
				event.getType().toString(),
				false,
				"",
				new SimulationEndEvent(
						event.timestamp,
						event.simulationsessionid,
						new ArrayList<String>(event.involvedusers),
						manager.getModelSetIdFromSessionId(event.simulationsessionid),
//						manager.getSimulationSessionParametersData(event.simulationsessionid)));
						getCleanSessionParameters(event.simulationsessionid)));

		send(monitoringEvent);

	}

	@Override
	public void receiveProcessStartEvent(ProcessStartSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp,
				event.simulationsessionid,
				event.timestamp,
				event.getType().toString(),
				false,
				"",
				new ProcessStartEvent(
						event.timestamp,
						event.simulationsessionid,
						new ArrayList<String>(event.involvedusers),
						manager.getModelSetIdFromSessionId(event.simulationsessionid),
//						manager.getSimulationSessionParametersData(event.simulationsessionid),
						getCleanSessionParameters(event.simulationsessionid),
						event.processInstance.getProcessartifactkey()));

		send(monitoringEvent);

	}

	@Override
	public void receiveProcessEndEvent(ProcessEndSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp,
				event.simulationsessionid,
				event.timestamp,
				event.getType().toString(),
				false,
				"",
				new ProcessEndEvent(
						event.timestamp,
						event.simulationsessionid,
						new ArrayList<String>(event.involvedusers),
						manager.getModelSetIdFromSessionId(event.simulationsessionid),
						getCleanSessionParameters(event.simulationsessionid),
						event.processInstance.getProcessartifactkey()));
		send(monitoringEvent);

	}

	@Override
	public void receiveTaskStartEvent(TaskStartSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp,
				event.simulationsessionid,
				event.timestamp,
				event.getType().toString(),
				false,
				event.task.subprocessKey,
				new TaskStartEvent(
						event.timestamp,
						event.simulationsessionid,
						new ArrayList<String>(event.involvedusers),
						manager.getModelSetIdFromSessionId(event.simulationsessionid),
						getCleanSessionParameters(event.simulationsessionid),
						manager.getProcessInstanceInfos(event.task.processId).getProcessartifactkey(),
						event.task.key, new ArrayList<String>(
								event.involvedusers)));

		send(monitoringEvent);

	}

	@Override
	public void receiveTaskEndEvent(TaskEndSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp,
				event.simulationsessionid,
				event.timestamp,
				event.getType().toString(),
				false,
				event.task.subprocessKey,
				new TaskEndEvent(
						event.timestamp,
						event.simulationsessionid,
						new ArrayList<String>(event.involvedusers),
						manager.getModelSetIdFromSessionId(event.simulationsessionid),
						getCleanSessionParameters(event.simulationsessionid),
						manager.getProcessInstanceInfos(event.task.processId).getProcessartifactkey(),
						event.task.key, new ArrayList<String>(
								event.involvedusers), event.completingUser,
						event.submittedData));

		send(monitoringEvent);

	}

	@Override
	public void receiveTaskFailedEvent(TaskFailedSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp,
				event.simulationsessionid,
				event.timestamp,
				event.getType().toString(),
				false,
				event.task.subprocessKey,
				new TaskFailedEvent(
						event.timestamp,
						event.simulationsessionid,
						new ArrayList<String>(event.involvedusers),
						manager.getModelSetIdFromSessionId(event.simulationsessionid),
						getCleanSessionParameters(event.simulationsessionid),
						manager.getProcessInstanceInfos(event.task.processId).getProcessartifactkey(),
						event.task.key, new ArrayList<String>(
								event.involvedusers), event.completingUser,
								event.submittedData));

		send(monitoringEvent);

	}

	@Override
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateSimEvent event) {
		GlimpseBaseEventBPMN<String> monitoringEvent = new GlimpseBaseEventBPMN<String>(
				"Activity_" + event.timestamp,
				event.simulationsessionid,
				event.timestamp,
				event.getType().toString(),
				false,
				"",
				new SessionScoreUpdateEvent(
						event.timestamp,
						event.simulationsessionid,
						new ArrayList<String>(event.involvedusers),
						manager.getModelSetIdFromSessionId(event.simulationsessionid),
						getCleanSessionParameters(event.simulationsessionid),
						manager.getProcessInstanceInfos(event.processid).getProcessartifactkey(),
						event.user, event.sessionscore));

		send(monitoringEvent);

	}

	/**
	 * Remove useless internal additional data from the parameters associated
	 * with a sim. session
	 *
	 * @param simSessionId
	 * @return the cleaned map
	 */
	private Map<String, Object> getCleanSessionParameters(String simSessionId) {
		Map<String, Object> res = new HashMap<>(
				manager.getSimulationSessionParametersData(simSessionId));
		res.remove(ActivitiProcessManager.SIMULATION_ID_KEY);
		res.remove("case");
		res.remove("applicantName");
		return res;
	}

}
