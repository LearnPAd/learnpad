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

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class MainTest {

	@Test
	public void testCorrectBoot() {

		try {
			Simulator simulator = new Simulator(Main.ACTIVITY_CONFIG_PATH,
					Main.PORT);

			// add users
			for (String user : Arrays.asList("sarah", "tom")) {
				simulator.userHandler().addUser(user);
			}

			// load process definitions
			simulator.processManager().addProjectDefinitions(
					Main.DEMO_PROCESS_FOLDER + "/VacationRequest.bpmn20.xml");
			simulator.processManager().addProjectDefinitions(
					Main.DEMO_PROCESS_FOLDER + "/demo.bpmn20.xml");

			simulator.stop();

		} catch (Exception e) {
			System.err.println(e);
			fail("got exception during setup");
		}
	}
}
