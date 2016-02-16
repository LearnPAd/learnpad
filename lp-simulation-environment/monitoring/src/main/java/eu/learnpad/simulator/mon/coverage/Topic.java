package eu.learnpad.simulator.mon.coverage;

public class Topic {

	private int id;
	private String topicName;

	public Topic(int id, String topicName) {
		this.id = id;
		this.topicName = topicName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
}
