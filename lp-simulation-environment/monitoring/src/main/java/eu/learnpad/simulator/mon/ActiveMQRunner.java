package eu.learnpad.simulator.mon;

import java.net.URI;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;

public class ActiveMQRunner extends Thread {

	private String hostWhereToRunInstance;
	private BrokerService broker;
	private TransportConnector connector;
	
	public ActiveMQRunner(String hostWhereToRunInstance) {
		this.hostWhereToRunInstance = hostWhereToRunInstance;
	}
	
	public boolean isBrokerStarted() {
		if (this.broker == null) {
			return false;
		}
		return this.broker.isStarted();
	}
	
	public void run() {
		broker = new BrokerService();
		connector = new TransportConnector();
		
		try {
			connector.setUri(new URI(hostWhereToRunInstance));
			broker.addConnector(connector);
			broker.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
