package eu.learnpad.simulator.mon.coverage;

import java.io.Serializable;
import java.util.HashMap;

public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String path_id;
	private String taskArtifactID;
	private HashMap<String, Float> expectedKpi; 
	private float weight;

	
	public Activity(String name, String path_id , String taskArtifactID, HashMap<String, Float> expectedKpi, float weight) {
		this.name = name;
		this.path_id = path_id;
		this.taskArtifactID = taskArtifactID;
		this.expectedKpi = expectedKpi;
		this.weight = weight;
	}

	public String getTaskArtifactID() {
		return taskArtifactID;
	}

	public void setTaskArtifactID(String taskArtifactID) {
		this.taskArtifactID = taskArtifactID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath_id() {
		return path_id;
	}
	public void setPath_id(String path_id) {
		this.path_id = path_id;
	}
	public HashMap<String, Float> getExpectedKpi() {
		return expectedKpi;
	}
	public void setExpectedKpi(HashMap<String, Float> expectedKpi) {
		this.expectedKpi = expectedKpi;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
}
