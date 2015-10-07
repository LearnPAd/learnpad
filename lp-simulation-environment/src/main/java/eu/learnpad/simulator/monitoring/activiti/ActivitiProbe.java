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

import eu.learnpad.simulator.utils.BPMNExplorerRepository;

/**
 * Monitor Activiti in order to notify Glimpse server of various process-related
 * events
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiProbe extends GlimpseAbstractProbe implements
ActivitiEventListener {

	private final BPMNExplorerRepository explorerRepo;

	/**
	 * @param settings
	 */
	public ActivitiProbe(BPMNExplorerRepository explorerRepo) {
		super(Manager.createProbeSettingsPropertiesObject(
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory",
				"tcp://atlantis.isti.cnr.it:61616", "system", "manager",
				"TopicCF", "jms.probeTopic", false, "probeName", "probeTopic"));

		this.explorerRepo = explorerRepo;
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

		ActivitiEntityEventImpl entity;
		ProcessInstance p;

		switch (event.getType()) {

		case ENTITY_CREATED:
			// for process instances creation
			entity = (ActivitiEntityEventImpl) event;
			if (entity.getEntity() instanceof ProcessInstance) {
				p = (ProcessInstance) entity.getEntity();

				String subprocessId;
				if (p.getId().equals(p.getProcessInstanceId())) {
					// main process
					subprocessId = null;
				} else {
					// subprocess
					subprocessId = p.getActivityId();
				}

				monitoringEvent = new GlimpseBaseEventBPMN<String>(null,
						p.getProcessDefinitionKey(),
						System.currentTimeMillis(), "PROCESS_CREATED", false,
						"", event.getProcessInstanceId(), null, null, null,
						subprocessId, null);
			}
			break;

		case ENTITY_DELETED:
			// for subprocess instances deletion
			entity = (ActivitiEntityEventImpl) event;
			if (entity.getEntity() instanceof ProcessInstance) {
				p = (ProcessInstance) entity.getEntity();

				String subprocessId;
				if (p.getId().equals(p.getProcessInstanceId())) {
					// main process
					subprocessId = null;
				} else {
					// subprocess
					subprocessId = p.getActivityId();
				}

				monitoringEvent = new GlimpseBaseEventBPMN<String>(null,
						p.getProcessDefinitionKey(),
						System.currentTimeMillis(), "PROCESS_COMPLETED", false,
						"", event.getProcessInstanceId(), null, null, null,
						subprocessId, null);
			}
			break;

		case TASK_CREATED:
		case TASK_COMPLETED:
			entity = (ActivitiEntityEventImpl) event;
			Task t = (Task) entity.getEntity();

			String subprocessId = explorerRepo.getExplorer(
					t.getProcessDefinitionId()).getSubprocess(
							t.getTaskDefinitionKey());

			monitoringEvent = new GlimpseBaseEventBPMN<String>("Activity_"
					+ System.currentTimeMillis(), event.getProcessInstanceId(),
					System.currentTimeMillis(), event.getType().toString(),
					false, "", event.getProcessInstanceId(), t.getAssignee(),
					null, t.getTaskDefinitionKey(), subprocessId, null);
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
