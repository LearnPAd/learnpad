/**
 *
 */
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

import java.io.IOException;
import java.util.Arrays;

import eu.learnpad.simulator.uihandler.webserver.WebServer;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class Main {

	public static final String ACTIVITY_CONFIG_PATH = "activiti.cfg.xml";
	public static final String DEMO_PROCESS_FOLDER = "process";

	public static final int PORT = 8081;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		try {

			Simulator simulator = new Simulator(ACTIVITY_CONFIG_PATH, PORT);

			// add users
			for (String user : Arrays.asList("sally", "barnaby")) {
				simulator.userHandler().addUser(user);
			}

			// load process definitions
			simulator.processManager().addProjectDefinitions(
					DEMO_PROCESS_FOLDER + "/demo-suap-1.bpmn20.xml");

			System.out.println("---\n");
			System.out.println("Demo is ready and can be accessed at http://"
					+ WebServer.getIPAdress() + ":" + PORT);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("---\n");
			System.err
					.println("Sorry, cannot launch the demo (maybe it is already running?)");
			System.exit(-1);
		}
	}
}
