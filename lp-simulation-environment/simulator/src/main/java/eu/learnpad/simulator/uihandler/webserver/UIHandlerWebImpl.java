/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver;

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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.servlet.ServletHolder;

import eu.learnpad.sim.rest.data.UserData;
import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.IUserHandler;
import eu.learnpad.simulator.monitoring.event.impl.ProcessEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.ProcessStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SessionScoreUpdateSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskFailedSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskStartSimEvent;
import eu.learnpad.simulator.uihandler.IFormHandler;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class UIHandlerWebImpl implements IUserHandler, IProcessEventReceiver {

	private final IProcessManager.IProcessManagerProvider userEventReceiverProvider;
	private final IFormHandler formHandler;
	private final WebServer webserver;
	private final Map<String, ServletHolder> usersMap = Collections
			.synchronizedMap(new HashMap<String, ServletHolder>());
	private final Map<String, ServletHolder> tasksMap = Collections
			.synchronizedMap(new HashMap<String, ServletHolder>());

	private final Map<String, UserData> usersInfos = Collections
			.synchronizedMap(new HashMap<String, UserData>());

	/**
	 * @param webserver
	 * @param users
	 * @param taskService
	 * @throws Exception
	 */
	public UIHandlerWebImpl(WebServer webserver, Collection<String> users,
			IProcessManager.IProcessManagerProvider userEventReceiverProvider,
			IFormHandler formHandler) {
		super();
		this.userEventReceiverProvider = userEventReceiverProvider;
		this.formHandler = formHandler;

		// launch task webserver
		this.webserver = webserver;

		// create process ui servlet
		webserver.addServlet(
				new UIProcessServlet(
						userEventReceiverProvider.processManager(), this,
						formHandler), "process");

		// create diagram servlet
		webserver.addServlet(new DiagramServlet(userEventReceiverProvider),
				"diagram");

		// instanciate users UI
		for (String user : users) {
			usersMap.put(user, this.webserver.addUIServlet(new UIServlet(user,
					userEventReceiverProvider.processManager()), user));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IUIHandler#addUser(java.lang.String)
	 */
	public void addUser(UserData user) {
		if (!usersMap.containsKey(user.id)) {
			usersMap.put(user.id, webserver.addUIServlet(new UIServlet(user.id,
					userEventReceiverProvider.processManager()), user.id));
			usersInfos.put(user.id, user);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IUIHandler#removeUser(java.lang.String)
	 */
	public void removeUser(String userId) {
		if (usersMap.containsKey(userId)) {
			webserver.removeServletHolder(usersMap.get(userId));
			usersMap.remove(userId);
			usersInfos.remove(userId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IUIHandler#getUsers()
	 */
	public List<String> getUsers() {
		return new ArrayList<>(usersMap.keySet());
	}

	@Override
	public UserData getUserData(String userId) {
		return usersInfos.get(userId);
	}

	/**
	 * stop the webserver
	 */
	public void stop() {
		webserver.stop();
	}

	@Override
	public void receiveSimulationStartEvent(SimulationStartSimEvent event) {
		for (String userId : event.involvedusers) {
			if (usersMap.containsKey(userId)) {
				((UIServlet) usersMap.get(userId).getServletInstance())
						.startSession(event);
			}
		}
	}

	@Override
	public void receiveSimulationEndEvent(SimulationEndSimEvent event) {
		for (String userId : event.involvedusers) {
			if (usersMap.containsKey(userId)) {
				((UIServlet) usersMap.get(userId).getServletInstance())
						.completeSession(event.simulationsessionid, event.probeScores);
			}
		}
	}

	@Override
	public void receiveProcessStartEvent(ProcessStartSimEvent event) {
		// nothing to do
	}

	@Override
	public void receiveProcessEndEvent(ProcessEndSimEvent event) {
		// nothing to do
	}

	@Override
	public void receiveTaskStartEvent(TaskStartSimEvent event) {
		tasksMap.put(event.task.id, webserver.addTaskServlet(new TaskServlet(
				userEventReceiverProvider.processManager(), event.task,
				formHandler), event.task.id));

		// note: it is important to signal new tasks to users *after* having
		// created the corresponding servlet otherwise the user may try to
		// connect to the task before it is available
		for (String userId : event.involvedusers) {
			if (usersMap.containsKey(userId)) {
				((UIServlet) usersMap.get(userId).getServletInstance())
						.addTask(event.simulationsessionid, event.task.id);
			}
		}
	}

	@Override
	public void receiveTaskFailedEvent(TaskFailedSimEvent event) {
		// nothing to do
	}

	@Override
	public void receiveTaskEndEvent(TaskEndSimEvent event) {
		for (String userId : event.involvedusers) {
			if (usersMap.containsKey(userId)) {
				((UIServlet) usersMap.get(userId).getServletInstance())
						.removeTask(event.task.id);
			}
		}

		// remove task ui from webserver
		webserver.removeServletHolder(tasksMap.get(event.task.id));

		tasksMap.remove(event.task.id);

	}

	@Override
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateSimEvent event) {
		// nothing to do
	}

}
