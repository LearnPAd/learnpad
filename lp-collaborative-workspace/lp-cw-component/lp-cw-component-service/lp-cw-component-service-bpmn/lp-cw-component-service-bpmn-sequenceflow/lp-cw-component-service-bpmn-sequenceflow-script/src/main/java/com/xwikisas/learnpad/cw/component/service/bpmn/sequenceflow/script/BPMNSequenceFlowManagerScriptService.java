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
package eu.learnpad.cw.service.bpmn.sequenceflow.script;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.context.Execution;
import org.xwiki.script.service.ScriptService;

import eu.learnpad.cw.service.bpmn.sequenceflow.BPMNSequenceFlowManager;
import eu.learnpad.cw.service.bpmn.sequenceflow.BPMNSequenceFlowManagerException;

@Component
@Named("learnpad.bpmn.sequenceflow")
@Singleton
public class BPMNSequenceFlowManagerScriptService implements ScriptService {
	/**
	 * The key under which the last encountered error is stored in the current
	 * execution context.
	 */
	private static final String LEARNPADBPMNSEQUENCEFLOWERROR_KEY = "scriptservice.learnpad.bpmn.sequenceflow.error";

	@Inject
	private BPMNSequenceFlowManager bpmnSequenceFlowManager;

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
				LEARNPADBPMNSEQUENCEFLOWERROR_KEY);
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
		this.execution.getContext().setProperty(
				LEARNPADBPMNSEQUENCEFLOWERROR_KEY, e);
	}

	public String getCurrent() {
		try {
			return bpmnSequenceFlowManager.getCurrent();
		} catch (BPMNSequenceFlowManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String getName() {
		try {
			return bpmnSequenceFlowManager.getName();
		} catch (BPMNSequenceFlowManagerException e) {
			this.setLastError(e);
			return null;
		}
	}
	public String getName(String id) {
		try {
			return bpmnSequenceFlowManager.getName(id);
		} catch (BPMNSequenceFlowManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String getSourceRef() {
		try {
			return bpmnSequenceFlowManager.getSourceRef();
		} catch (BPMNSequenceFlowManagerException e) {
			this.setLastError(e);
			return null;
		}
	}
	public String getSourceRef(String id) {
		try {
			return bpmnSequenceFlowManager.getSourceRef(id);
		} catch (BPMNSequenceFlowManagerException e) {
			this.setLastError(e);
			return null;
		}
	}

	public String getTargetRef() {
		try {
			return bpmnSequenceFlowManager.getTargetRef();
		} catch (BPMNSequenceFlowManagerException e) {
			this.setLastError(e);
			return null;
		}
	}
	public String getTargetRef(String id) {
		try {
			return bpmnSequenceFlowManager.getTargetRef(id);
		} catch (BPMNSequenceFlowManagerException e) {
			this.setLastError(e);
			return null;
		}
	}
}
