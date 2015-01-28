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
package com.xwikisas.learnpad.cw.component.service.bpmn.flownode.script;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.context.Execution;
import org.xwiki.script.service.ScriptService;

import com.xwikisas.learnpad.cw.component.service.bpmn.flownode.BPMNFlowNodeManager;

@Component
@Named("learnpad.bpmn.flownode")
@Singleton
public class BPMNFlowNodeManagerScriptService implements ScriptService {
	/**
	 * The key under which the last encountered error is stored in the current
	 * execution context.
	 */
	private static final String LEARNPADBPMNFLOWNODEERROR_KEY = "scriptservice.learnpad.bpmn.flownode.error";

	@Inject
	private BPMNFlowNodeManager bpmn;

	/**
	 * Provides access to the current context.
	 */
	@Inject
	private Execution execution;

	/**
	 * Get the error generated while performing the previously called action.
	 *
	 * @return an eventual exception or {@code null} if no exception was thrown
	 */
	public Exception getLastError() {
		return (Exception) this.execution.getContext().getProperty(
				LEARNPADBPMNFLOWNODEERROR_KEY);
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
		this.execution.getContext().setProperty(LEARNPADBPMNFLOWNODEERROR_KEY, e);
	}
}
