package eu.learnpad.simulator.processmanager.activiti.workarounds.msg;

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

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.el.FixedValue;

import eu.learnpad.simulator.Main;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;

/**
 *
 * This class implements an Activiti Task Service delegate that handle message
 * send to other processes (something that Activiti does explicitly not handle).
 * <p>
 * Its goal is to replace the following activities that are unsupported by
 * Activiti:
 * <ul>
 * <li>intermediateThrowEvent</li>
 * <li>sendTask</li>
 * </ul>
 *
 * @see http://activiti.org/userguide/index.html#bpmnJavaServiceTask
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class JavaMsgTask implements JavaDelegate {

	// will be injected by Activiti
	public FixedValue msg;

	@Override
	public void execute(final DelegateExecution execution) {

		// This object should only be used with the Activiti process manager
		// (as it is an activiti-specific workaround)
		// if this cast fails, then there is something extremely *wrong*
		((ActivitiProcessManager) Main.simulator.processManager())
				.receiveTaskMessage(new MessageInfoData(execution
						.getProcessInstanceId(), msg.getExpressionText()));

	}
}
