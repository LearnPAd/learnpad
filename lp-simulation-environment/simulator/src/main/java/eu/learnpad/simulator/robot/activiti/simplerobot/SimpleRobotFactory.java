/**
 *
 */
package eu.learnpad.simulator.robot.activiti.simplerobot;

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

import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;

import eu.learnpad.simulator.robot.IRobot;
import eu.learnpad.simulator.robot.IRobotFactory;
import eu.learnpad.simulator.uihandler.formhandler.AbstractFormHandler;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class SimpleRobotFactory implements
		IRobotFactory<Map<String, Object>, Map<String, Object>> {

	private final RepositoryService repositoryService;
	private final TaskService taskService;
	private final AbstractFormHandler formHandler;

	public SimpleRobotFactory(RepositoryService repositoryService,
			TaskService taskService, AbstractFormHandler formHandler) {
		super();
		this.repositoryService = repositoryService;
		this.taskService = taskService;
		this.formHandler = formHandler;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see eu.learnpad.simulator.robot.IRobotFactory#createRobot()
	 */
	@Override
	public IRobot<Map<String, Object>, Map<String, Object>> createRobot() {
		return new SimpleRobot(repositoryService, taskService, formHandler);
	}

}
