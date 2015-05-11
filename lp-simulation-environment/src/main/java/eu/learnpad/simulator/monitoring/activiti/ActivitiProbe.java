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

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiProbe extends GlimpseAbstractProbe implements
		ActivitiEventListener {

	/**
	 * @param settings
	 */
	public ActivitiProbe() {
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
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.
	 * activiti.engine.delegate.event.ActivitiEvent)
	 */
	public void onEvent(ActivitiEvent event) {

		GlimpseBaseEventBPMN<String> monitoringEvent = null;

		// System.out.println("event.getType() " + event.getType());
		// System.out.println("event.getProcessInstanceId() "
		// + event.getProcessInstanceId());
		// System.out.println("processInstanceId " + processInstanceId);

		ActivitiEntityEventImpl e;
		switch (event.getType()) {

		case ENTITY_CREATED:
			// for process instances creation
			e = (ActivitiEntityEventImpl) event;
			if (e.getEntity() instanceof ProcessInstance) {
				monitoringEvent = new GlimpseBaseEventBPMN<String>(null,
						event.getProcessInstanceId(),
						System.currentTimeMillis(), "PROCESS_CREATED", false,
						"", event.getProcessInstanceId(), null, null, null,
						null, null);
			}
			break;

		case PROCESS_COMPLETED:
			monitoringEvent = new GlimpseBaseEventBPMN<String>(null,
					event.getProcessInstanceId(), System.currentTimeMillis(),
					event.getType().toString(), false, "",
					event.getProcessInstanceId(), null, null, null, null, null);
			break;

		case TASK_CREATED:
		case TASK_COMPLETED:
			e = (ActivitiEntityEventImpl) event;
			Task t = (Task) e.getEntity();

			monitoringEvent = new GlimpseBaseEventBPMN<String>("Activity_"
					+ System.currentTimeMillis(), event.getProcessInstanceId(),
					System.currentTimeMillis(), event.getType().toString(),
					false, "", event.getProcessInstanceId(), t.getAssignee(),
					null, t.getTaskDefinitionKey(), null, null);
			break;

		default:
			// non-monitored event, ignore
			break;
		}

		if (monitoringEvent != null) {
			send(monitoringEvent);
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
	}

}
