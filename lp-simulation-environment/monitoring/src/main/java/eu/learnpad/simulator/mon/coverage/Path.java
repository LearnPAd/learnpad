package eu.learnpad.simulator.mon.coverage;

public class Path {

	private int id;
	private String idBpmn;
	private float absoluteSessionScore;
	private String pathRule;
	private Activity[] theActivities;
	
	public Path() {
	}
	
	public Path(int id, String idBpmn) {
		this.id = id;
		this.idBpmn = idBpmn;
		this.absoluteSessionScore = 0;
		this.pathRule = "";
	}

	public Path(int id, String idBpmn, String pathRule) {
		this.id = id;
		this.idBpmn = idBpmn;
		this.absoluteSessionScore = 0;
		this.pathRule = pathRule;
	}
	
	public Path(int id, String idBpmn, float absoluteSessionScore) {
		this.id = id;
		this.idBpmn = idBpmn;
		this.absoluteSessionScore = absoluteSessionScore;
		this.pathRule = "";
	}

	public Path(int id, String idBpmn, float absoluteSessionScore, String pathRule) {
		this.id = id;
		this.idBpmn = idBpmn;
		this.absoluteSessionScore = absoluteSessionScore;
		this.pathRule = pathRule;
	}
	
	public Path(int id, String idBpmn, float absoluteSessionScore, String pathRule, Activity[] theActivities) {
		this.id = id;
		this.idBpmn = idBpmn;
		this.absoluteSessionScore = absoluteSessionScore;
		this.pathRule = pathRule;
		this.theActivities = theActivities;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdBpmn() {
		return idBpmn;
	}
	public void setIdBpmn(String idBpmn) {
		this.idBpmn = idBpmn;
	}
	public float getAbsoluteSessionScore() {
		return absoluteSessionScore;
	}
	public void setAbsoluteSessionScore(float absoluteSessionScore) {
		this.absoluteSessionScore = absoluteSessionScore;
	}
	public String getPathRule() {
		return pathRule;
	}
	public void setPathRule(String pathRule) {
		this.pathRule = pathRule;
	}
	public Activity[] getActivities() {
		return theActivities;
	}
	public void setActivities(Activity[] theActivities) {
		this.theActivities = theActivities;
	}
}
