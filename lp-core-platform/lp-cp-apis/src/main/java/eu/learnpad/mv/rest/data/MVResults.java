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
		"status",
		"deadlockPathList"})
@XmlRootElement(name = "modelverification")
public class MVResults {
	// TODO: class to store verification results; to define
	@XmlElement(name = "modelsetid", required = true)
	protected String modelSetId;
	@XmlElement(name = "status")
	protected String status;
	@XmlElement(name = "deadlockpath")
	protected List<String> deadlockPathList;

	public MVResults() {
		this.modelSetId = "";
		this.status = "";
		this.deadlockPathList = new ArrayList<String>();
	}

	public MVResults(String modelSetId) {
		this.modelSetId = modelSetId;
		this.status = "inprogress";
		this.deadlockPathList = new ArrayList<String>();
	}

	public MVResults(MVResults result) {
		this.modelSetId = result.modelSetId;
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
	
	public String getStatus() {
		return this.status;
	}
}
