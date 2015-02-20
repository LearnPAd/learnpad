package eu.learnpad.simulator;

import java.util.ArrayList;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

import eu.learnpad.simulator.IProcessEventReceiver.IProcessEventReceiverProvider;
import eu.learnpad.simulator.IProcessManager.IProcessManagerProvider;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;
import eu.learnpad.simulator.uihandler.formhandler.activiti2jsonform.ActivitiToJsonFormFormHandler;
import eu.learnpad.simulator.uihandler.webserver.UIHandlerWebImpl;

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
		uiHandler = new UIHandlerWebImpl(webserverPort,
				new ArrayList<String>(), this,
				new ActivitiToJsonFormFormHandler(
						processEngine.getFormService()));
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
