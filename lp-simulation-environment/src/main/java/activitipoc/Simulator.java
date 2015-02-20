package activitipoc;

import java.util.ArrayList;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

import activitipoc.formhandler.activiti2jsonform.ActivitiToJsonFormFormHandler;
import activitipoc.processmanager.activiti.ActivitiProcessManager;
import activitipoc.uihandler.webserver.UIHandlerWebImpl;

/**
 *
 */

/**
 * @author jorquera
 *
 */
public class Simulator implements IUIHandler.IUIHandlerProvider,
IProcessManager.IProcessManagerProvider {

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
		processManager = new ActivitiProcessManager(processEngine);

		// create users ui handler
		uiHandler = new UIHandlerWebImpl(webserverPort,
				new ArrayList<String>(), processManager,
				new ActivitiToJsonFormFormHandler(
						processEngine.getFormService()));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IProcessManager.IProcessManagerProvider#processManager()
	 */
	public IProcessManager processManager() {
		return processManager;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IUIHandler.IUIHandlerProvider#uiHandler()
	 */
	public IUIHandler uiHandler() {
		return uiHandler;
	}

	public void stop() {
		processEngine.close();
		uiHandler.stop();
	}
}
