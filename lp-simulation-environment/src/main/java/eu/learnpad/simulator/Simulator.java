package eu.learnpad.simulator;

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

import java.util.ArrayList;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

import eu.learnpad.simulator.IProcessEventReceiver.IProcessEventReceiverProvider;
import eu.learnpad.simulator.IProcessManager.IProcessManagerProvider;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;
import eu.learnpad.simulator.uihandler.formhandler.activiti2jsonform.ActivitiToJsonFormFormHandler;
import eu.learnpad.simulator.uihandler.webserver.UIHandlerWebImpl;
import eu.learnpad.simulator.uihandler.webserver.WebServer;

/**
 *
 */

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class Simulator implements IProcessManagerProvider,
		IProcessEventReceiverProvider {

	private final IProcessManager processManager;
	private final UIHandlerWebImpl uiHandler;
	private final ProcessEngine processEngine;

	public Simulator(String activitiConfigPath, int webserverPort)
			throws Exception {

		// launch activiti process engine
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(Main.class
						.getClassLoader().getResourceAsStream(
								activitiConfigPath));

		processEngine = config.buildProcessEngine();

		// create process manager
		processManager = new ActivitiProcessManager(processEngine, this);

		// create users ui handler
		uiHandler = new UIHandlerWebImpl(new WebServer(webserverPort, "ui",
				"tasks"), new ArrayList<String>(), this,
				new ActivitiToJsonFormFormHandler(processEngine
						.getFormService()));
	}

	public void stop() {
		processEngine.close();
		uiHandler.stop();
	}

	public IUserHandler userHandler() {
		return uiHandler;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * eu.learnpad.simulator.IProcessEventReceiver.IProcessEventReceiverProvider
	 * #processEventReceiver()
	 */
	public IProcessEventReceiver processEventReceiver() {
		return uiHandler;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see eu.learnpad.simulator.IUserEventReceiver.IUserEventReceiverProvider#
	 * userEventReceiver()
	 */
	public IProcessManager processManager() {
		return processManager;
	}

}
