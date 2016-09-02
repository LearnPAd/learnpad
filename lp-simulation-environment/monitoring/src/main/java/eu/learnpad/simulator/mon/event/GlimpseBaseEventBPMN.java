package eu.learnpad.simulator.mon.event;

import eu.learnpad.sim.rest.event.AbstractEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.TaskEndEvent;

public class GlimpseBaseEventBPMN<T> extends GlimpseBaseEventGeneric<String> {

	private static final long serialVersionUID = 1L;
	public AbstractEvent event;

	public GlimpseBaseEventBPMN(String data, String probeID, Long timeStamp,
			String eventName, boolean isException, String extraDataField,
			AbstractEvent event) {

		super(data, probeID, timeStamp, eventName, isException, extraDataField);

		this.event = event;
	}

	public AbstractEvent getEvent() {
		return this.event;
	}
	
	public String getUserID() {
		return ((SessionScoreUpdateEvent) this.event).user;
	}
	
	public TaskEndEvent getTaskEndEvent() {
		return (TaskEndEvent) this.event;
	}

	public SessionScoreUpdateEvent getSessionScoreUpdateEvent() {
		return (SessionScoreUpdateEvent) this.event;
	}
	
	public void setEvent(AbstractEvent event) {
		this.event = event;
	}
}
