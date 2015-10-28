package eu.learnpad.simulator.mon.manager;

public class LearnerAssessmentManager {

	private String theBPMN;

	public LearnerAssessmentManager() {
		
	}

	public void setBPModel(String xmlMessagePayload) {

		this.theBPMN = xmlMessagePayload;
		
	}
}
