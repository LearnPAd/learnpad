package eu.learnpad.simulator.mon;

import java.net.URI;

import javax.jms.JMSException;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;

import eu.learnpad.simulator.mon.utils.TesterJMSConnection;

public class ActiveMQRunner implements Runnable {

	private String hostWhereToRunInstance;
	private BrokerService broker;
	private TransportConnector connector;
	
	private TesterJMSConnection tester;
	
	public ActiveMQRunner(String hostWhereToRunInstance) {
		this.hostWhereToRunInstance = hostWhereToRunInstance;		
		this.tester = new TesterJMSConnection(this.hostWhereToRunInstance);
	}
	
	public synchronized boolean isBrokerStarted() {
		if ((this.broker == null) || (!this.broker.isStarted())) {
			return false;
		}
				
		try {
		    this.tester.testConnection();
		} catch (JMSException e) {
			return false;
		}
		
		return true;
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
