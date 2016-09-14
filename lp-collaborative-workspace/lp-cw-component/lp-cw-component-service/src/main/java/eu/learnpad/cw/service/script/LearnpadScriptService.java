/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.cw.service.script;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.context.Execution;
import org.xwiki.script.service.ScriptService;
import org.xwiki.script.service.ScriptServiceManager;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.cw.rest.data.ScoreRecordCollection;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.sim.rest.data.ProcessInstanceData;

@Component
@Named("learnpad")
@Singleton
public class LearnpadScriptService implements ScriptService, UICWBridge {

	/**
	 * Hint of the component.
	 */
	public static final String ROLEHINT = "learnpad";

	/**
	 * The key under which the last encountered error is stored in the current
	 * execution context.
	 */
	private static final String LEARNPADERROR_KEY = "scriptservice.learnpad.error";

	@Inject
	private ScriptServiceManager scriptServiceManager;

	@Inject
	@Named("eu.learnpad.cw.internal.CWXwikiBridge")
	private UICWBridge cwBridge;

	/**
	 * Provides access to the current context.
	 */
	@Inject
	private Execution execution;

	/**
	 * Get a sub script service related to wiki. (Note that we're voluntarily
	 * using an API name of "get" to make it extra easy to access Script
	 * Services from Velocity (since in Velocity writing
	 * <code>$services.wiki.name</code> is equivalent to writing
	 * <code>$services.wiki.get("name")</code>). It also makes it a short and
	 * easy API name for other scripting languages.
	 * 
	 * @param serviceName
	 *            id of the script service
	 * @return the service asked or null if none could be found
	 */
	public ScriptService get(String serviceName) {
		return scriptServiceManager.get(ROLEHINT + '.' + serviceName);
	}

	/**
	 * Get the error generated while performing the previously called action.
	 * 
	 * @return an eventual exception or {@code null} if no exception was thrown
	 */
	public Exception getLastError() {
		return (Exception) this.execution.getContext().getProperty(
				LEARNPADERROR_KEY);
	}

	/**
	 * Store a caught exception in the context, so that it can be later
	 * retrieved using {@link #getLastError()}.
	 * 
	 * @param e
	 *            the exception to store, can be {@code null} to clear the
	 *            previously stored exception
	 * @see #getLastError()
	 */
	private void setLastError(Exception e) {
		this.execution.getContext().setProperty(LEARNPADERROR_KEY, e);
	}

	public Recommendations getRecommendations(String modelSetId,
			String artifactId, String userId) {
		try {
			return this.cwBridge.getRecommendations(modelSetId, artifactId,
					userId);
		} catch (Exception e) {
			this.setLastError(e);
			return null;
		}
	}

	@Override
	public String getDashboardKpiDefaultViewer(String modelSetId, String userId) {
		String url = null;
		try {
			url = this.cwBridge
					.getDashboardKpiDefaultViewer(modelSetId, userId);
			URL u = new URL(url); // this would check for the protocol
			u.toURI(); // does the extra checking required for validation of URI
		} catch (LpRestException e) {
			this.setLastError(e);
		} catch (MalformedURLException | URISyntaxException e) {
			url = "";
			this.setLastError(e);
		}
		return url;
	}

	@Override
	public String startDashboardKpiCalculation(String modelSetId) {
		String kpiCalulationPID  = null;
		try {
			kpiCalulationPID = this.cwBridge
					.startDashboardKpiCalculation(modelSetId);
		} catch (LpRestException e) {
			kpiCalulationPID  = "";
			this.setLastError(e);
		}
		return kpiCalulationPID ;
	}

	public String startSimulation(String modelId, String currentUser,
			Collection<String> potentialUsers) {
		try {
			return this.cwBridge.startSimulation(modelId, currentUser,
					potentialUsers);
		} catch (LpRestException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String joinSimulation(String simulationId, String userId) {
		try {
			return this.cwBridge.joinSimulation(simulationId, userId);
		} catch (LpRestException e) {
			this.setLastError(e);
			return null;
		}
	}

	@Override
	public Collection<String> listSimulations() throws LpRestException {
		try {
			return this.cwBridge.listSimulations();
		} catch (LpRestException e) {
			this.setLastError(e);
			return null;
		}
	}

	@Override
	public ProcessInstanceData getSimulationInfo(String simulationId)
			throws LpRestException {
		try {
			ProcessInstanceData foo = this.cwBridge.getSimulationInfo(simulationId);
			return foo;
		} catch (LpRestException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String getRestPrefix(String component) {
		try {
			return this.cwBridge.getRestPrefix(component);
		} catch (LpRestException e) {
			this.setLastError(e);
			return null;
		}
	}

	public ScoreRecordCollection getScores(String userid, String modelid)
			throws LpRestException {
		try {
			return this.cwBridge.getScores(userid, modelid);
		} catch (LpRestException e) {
			this.setLastError(e);
			return null;
		}
	}

	@Override
	public void pageNotification(String modelSetId, String modelId,
			String artifactId, String resourceId, String action, String userId)
			throws LpRestException {
		try {
			this.cwBridge.pageNotification(modelSetId, modelId, artifactId, resourceId,
					action, userId);
		} catch (LpRestException e) {
			this.setLastError(e);
		}
	}

	@Override
	public void commentNotification(String modelSetId, String modelId,
			String artifactId, String resourceId, String action, String userId)
			throws LpRestException {
		try {
			this.cwBridge.commentNotification(modelSetId, modelId, artifactId, resourceId,
					action, userId);
		} catch (LpRestException e) {
			this.setLastError(e);
		}
	}

	@Override
	public void attachmentNotification(String modelSetId, String modelId,
			String artifactId, String resourceId, String action, String userId)
			throws LpRestException {
		try {
			this.cwBridge.attachmentNotification(modelSetId, modelId, artifactId, resourceId,
					action, userId);
		} catch (LpRestException e) {
			this.setLastError(e);
		}
	}

	@Override
	public void feedbackNotification(String modelSetId, String modelId,
			String artifactId, String resourceId, String action, String userId)
			throws LpRestException {
		try {
			this.cwBridge.feedbackNotification(modelSetId, modelId, artifactId, resourceId,
					action, userId);
		} catch (LpRestException e) {
			this.setLastError(e);
		}
	}

}