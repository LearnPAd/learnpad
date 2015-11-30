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
package eu.learnpad.mv.rest.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MVResults", propOrder = {
		"modelSetId",
		"type",
		"status",
		"deadlockPathList" })
@XmlRootElement(name = "modelverification")
public class MVResults {
	// TODO: class to store verification results; to define
	@XmlElement(name = "modelsetid", required = true)
	protected String modelSetId;
	@XmlElement(name = "type", required = true)
	protected String type;
	@XmlElement(name = "status")
	protected String status;
	@XmlElement(name = "deadlockpath")
	protected List<String> deadlockPathList;

	public MVResults() {
		this.modelSetId = "";
		this.type = "lpzip";
		this.status = "";
		this.deadlockPathList = new ArrayList<String>();
	}

	public MVResults(String modelSetId, String type) {
		this.modelSetId = modelSetId;
		this.type = type;
		this.status = "inprogress";
		this.deadlockPathList = new ArrayList<String>();
	}

	public MVResults(MVResults result) {
		this.modelSetId = result.modelSetId;
		this.type = result.type;
		this.deadlockPathList = new ArrayList<String>(result.deadlockPathList);
		this.status = result.status;
	}

	public void terminate() {
		this.status = "finished";
		this.deadlockPathList.clear();
		this.deadlockPathList.add("path/to/first/deadlock");
		this.deadlockPathList.add("path/to/second/deadlock");
	}

	public String getModelSetId() {
		return this.modelSetId;
	}

	public String getType() {
		return this.type;
	}

	public String getStatus() {
		return this.status;
	}
}
