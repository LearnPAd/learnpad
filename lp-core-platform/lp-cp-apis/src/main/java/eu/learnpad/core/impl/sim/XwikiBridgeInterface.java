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
package eu.learnpad.core.impl.sim;

import java.io.InputStream;
import java.util.Collection;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.sim.BridgeInterface;
import eu.learnpad.sim.rest.data.ProcessData;
import eu.learnpad.sim.rest.data.ProcessInstanceData;

@Component
public class XwikiBridgeInterface implements XWikiRestComponent, BridgeInterface{

	@Override
	public Collection<String> getProcessDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> addProcessDefinition(
			String processDefinitionFileURL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessData getProcessInfos(String processArtifactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getProcessInstances() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addProcessInstance(ProcessInstanceData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessInstanceData getProcessInstanceInfos(
			String processInstanceArtifactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getProcessInstanceResults(
			String processinstanceartifactid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getUserResults(String userartifactid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getProcessResults(String processartifactid) {
		// TODO Auto-generated method stub
		return null;
	}

}
