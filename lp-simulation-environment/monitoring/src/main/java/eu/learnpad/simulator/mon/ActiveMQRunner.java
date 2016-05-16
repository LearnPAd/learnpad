/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

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
