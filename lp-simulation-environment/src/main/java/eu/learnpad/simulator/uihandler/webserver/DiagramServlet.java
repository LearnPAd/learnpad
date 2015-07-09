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

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.learnpad.simulator.IProcessManager;

/**
 * This Servlet serves images of process and process instance diagrams.
 *
 * For process instance diagrams, a given task id must be passed to be
 * highlighted
 *
 * Expected request format:
 * <ul>
 * <li>for a process: \<basepath\>/process/processid</li>
 * <li>for a process instance: \<basepath\>/processinstance/processid/taskid</li>
 * </ul>
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class DiagramServlet extends HttpServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	final IProcessManager.IProcessManagerProvider processManagerProvider;

	/**
	 * @param dispatcher
	 * @param task
	 */
	public DiagramServlet(
			IProcessManager.IProcessManagerProvider processManagerProvider) {
		super();
		this.processManagerProvider = processManagerProvider;

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String[] path = request.getPathInfo().substring(1).split("/");

		if (!(path.length == 2 && path[0].equals("process") || (path.length == 3 && path[0]
				.equals("processinstance")))) {
			// incorrect call to the API, return error code
			response.sendError(400);
		} else {

			String processId = path[1];

			InputStream content;
			if (path[0].equals("processinstance")) {
				String taskId = path[2];
				content = this.processManagerProvider.processManager()
						.getCurrentTaskDiagram(processId, taskId);
			} else {
				content = this.processManagerProvider.processManager()
						.getProcessDiagram(processId);
			}

			if (content == null) {
				response.sendError(404);
			} else {
				response.setContentType("image/png");
				response.setStatus(HttpServletResponse.SC_OK);

				ServletOutputStream sos = response.getOutputStream();

				byte[] b = new byte[2048];
				int length;

				while ((length = content.read(b)) != -1) {
					sos.write(b, 0, length);
				}

				content.close();

				sos.flush();
				sos.close();
			}
		}
	}
}
