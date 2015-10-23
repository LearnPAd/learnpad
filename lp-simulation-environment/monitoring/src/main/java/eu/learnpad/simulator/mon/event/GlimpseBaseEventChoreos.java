package eu.learnpad.simulator.mon.event;

public class GlimpseBaseEventChoreos<T> extends GlimpseBaseEventAbstract<T> {

	private static final long serialVersionUID = 534588849735258143L;
	private String choreographySource;
	private String serviceSource;
	private String machineIP;

	public GlimpseBaseEventChoreos(T data, Long timeStamp,
			String eventName, boolean isException, String choreographySource, String serviceSource, String machineIP) {
		super(data, timeStamp, eventName, isException);
		
		this.choreographySource = choreographySource;
		this.serviceSource = serviceSource;
		this.machineIP = machineIP;
	}

	public String getMachineIP() {
		return machineIP;
	}

	public void setMachineIP(String machineIP) {
		this.machineIP = machineIP;
	}

	public String getChoreographySource() {
		return choreographySource;
	}

	public void setChoreographySource(String choreographySource) {
		this.choreographySource = choreographySource;
	}

	public String getServiceSource() {
		return serviceSource;
	}

	public void setServiceSource(String serviceSource) {
		this.serviceSource = serviceSource;
	}
}
