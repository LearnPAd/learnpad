/**
 *
 */
package activitipoc;

import java.io.IOException;
import java.util.Arrays;

import activitipoc.uihandler.webserver.WebServer;

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
			for (String user : Arrays.asList("sarah", "tom")) {
				simulator.uiHandler().addUser(user);
			}

			// load process definitions
			simulator.processManager().addProjectDefinitions(
					DEMO_PROCESS_FOLDER + "/VacationRequest.bpmn20.xml");
			simulator.processManager().addProjectDefinitions(
					DEMO_PROCESS_FOLDER + "/demo.bpmn20.xml");

			System.out.println("---\n");
			System.out.println("Demo is ready and can be accessed at http://"
					+ WebServer.getIPAdress() + ":" + PORT);

		} catch (Exception e) {
			System.err.println("---\n");
			System.err
			.println("Sorry, cannot launch the demo (maybe it is already running?)");
			System.exit(-1);
		}
	}
}
