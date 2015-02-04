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
package eu.learnpad.cw.service.bpmn.script;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.context.Execution;
import org.xwiki.script.service.ScriptService;
import org.xwiki.script.service.ScriptServiceManager;

@Component
@Named("learnpad.bpmn")
@Singleton
public class BPMNManagerScriptService implements ScriptService {
	/**
	 * Hint of the component.
	 */
	public static final String ROLEHINT = "learnpad.bpmn";

	/**
	 * The key under which the last encountered error is stored in the current
	 * execution context.
	 */
	private static final String LEARNPADBPMNERROR_KEY = "scriptservice.learnpad.bpmn.error";

	@Inject
	private ScriptServiceManager scriptServiceManager;

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
				LEARNPADBPMNERROR_KEY);
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
		this.execution.getContext().setProperty(LEARNPADBPMNERROR_KEY, e);
	}
}
