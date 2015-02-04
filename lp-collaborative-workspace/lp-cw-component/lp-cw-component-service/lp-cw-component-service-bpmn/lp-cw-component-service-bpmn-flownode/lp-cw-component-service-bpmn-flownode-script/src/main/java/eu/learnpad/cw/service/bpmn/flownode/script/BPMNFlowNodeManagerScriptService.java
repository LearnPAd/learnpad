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
package eu.learnpad.cw.service.bpmn.flownode.script;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.context.Execution;
import org.xwiki.script.service.ScriptService;

import eu.learnpad.cw.service.bpmn.flownode.BPMNFlowNodeManager;
import eu.learnpad.cw.service.bpmn.flownode.BPMNFlowNodeManagerException;

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
	private BPMNFlowNodeManager bpmnFlowNodeManager;

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
		this.execution.getContext().setProperty(LEARNPADBPMNFLOWNODEERROR_KEY,
				e);
	}

	public String getCurrent() {
		try {
			return bpmnFlowNodeManager.getCurrent();
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String getName() {
		try {
			return bpmnFlowNodeManager.getName();
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String getName(String id) {
		try {
			return bpmnFlowNodeManager.getName(id);
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String getDocumentation() {
		try {
			return bpmnFlowNodeManager.getDocumentation();
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}
	
	public String getDocumentation(String id) {
		try {
			return bpmnFlowNodeManager.getDocumentation(id);
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String getURL() {
		try {
			return bpmnFlowNodeManager.getURL();
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String getURL(String id) {
		try {
			return bpmnFlowNodeManager.getURL(id);
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public List<String> getIncomings() {
		try {
			return bpmnFlowNodeManager.getIncomings();
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public List<String> getIncomings(String id) {
		try {
			return bpmnFlowNodeManager.getIncomings(id);
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public List<String> getOutgoings() {
		try {
			return bpmnFlowNodeManager.getOutgoings();
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public List<String> getOutgoings(String id) {
		try {
			return bpmnFlowNodeManager.getOutgoings(id);
		} catch (BPMNFlowNodeManagerException e) {
			this.setLastError(e);
			return null;
		}
	}
}
