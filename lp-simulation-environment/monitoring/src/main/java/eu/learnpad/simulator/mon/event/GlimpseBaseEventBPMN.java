package eu.learnpad.simulator.mon.event;

import eu.learnpad.simulator.mon.event.GlimpseBaseEventGeneric;

public class GlimpseBaseEventBPMN<T> extends GlimpseBaseEventGeneric<String> {

	private static final long serialVersionUID = 1L;
	public String sessionID;
	public String assigneeID;
	public String roleID;	
	public String taskID;
	public String subProcessID;
	public String desideredCompletionTime;
	
	public GlimpseBaseEventBPMN(
			String data, String probeID, Long timeStamp,
			String eventName, boolean isException, String extraDataField, 
			String sessionID, String assigneeID, String roleID, String taskID,
			String subProcessID, String desideredCompletionTime) {
		
		super(data, probeID, timeStamp, eventName, isException, extraDataField);
		
		this.sessionID = sessionID;
		this.assigneeID = assigneeID;
		this.roleID = roleID;
		this.taskID = taskID;
		this.subProcessID = subProcessID;
		this.desideredCompletionTime = desideredCompletionTime;
	}
	
	public String getDesideredCompletionTime() {
		return this.desideredCompletionTime;
	}
	
	public void setDesideredCompletionTimeID(String desideredCompletionTime) {
		this.desideredCompletionTime = desideredCompletionTime;
	}
	
	public String getSessionID() {
		return this.sessionID;
	}
	
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	public String getAssigneeID() {
		return this.assigneeID;
	}
	
	public void setAssigneeID(String assigneeID) {
		this.assigneeID = assigneeID;
	}
	
	public String getTaskID() {
		return this.taskID;
	}
	
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}	
	public String getSubProcessID() {
		return this.subProcessID;
	}
	
	public void setSubProcessID(String subProcessID) {
		this.subProcessID = subProcessID;
	}	
	public String getRoleID() {
		return this.roleID;
	}
	
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
}