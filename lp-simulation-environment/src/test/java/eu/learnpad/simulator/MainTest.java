/**
 *
 */
package eu.learnpad.simulator;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import eu.learnpad.simulator.Main;
import eu.learnpad.simulator.Simulator;

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
				simulator.uiHandler().addUser(user);
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
