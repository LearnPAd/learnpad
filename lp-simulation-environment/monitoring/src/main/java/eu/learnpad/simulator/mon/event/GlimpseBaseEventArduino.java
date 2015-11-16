package eu.learnpad.simulator.mon.event;

public class GlimpseBaseEventArduino<T> extends GlimpseBaseEventAbstract<T> {

	private static final long serialVersionUID = 8661064245254655648L;
	private String sensorName;
	
	public GlimpseBaseEventArduino(T data, String probeID, Long timeStamp, String eventName,
			boolean isException, String sensorName) {
		super(data, timeStamp, eventName, isException);
		this.sensorName = sensorName;
		
		}

	public String getSensorName() {
		return sensorName;
	}
	
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
}