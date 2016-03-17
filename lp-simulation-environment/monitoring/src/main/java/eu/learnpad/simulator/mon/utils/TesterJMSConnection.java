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
package eu.learnpad.simulator.mon.utils;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class TesterJMSConnection implements MessageListener{

	ActiveMQConnectionFactory factory;
	TextMessage message;
	
	public TesterJMSConnection(String hostRunningTheInstance){
		this.factory = new ActiveMQConnectionFactory(hostRunningTheInstance);		
		this.message = new ActiveMQTextMessage();
	}
	
	
	@Override
	public void onMessage(Message msg) {
		System.err.println("The following test message has been received :" + msg.toString());
	}

	public void testConnection() throws JMSException {
		Connection conn = this.factory.createConnection();

		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createTopic("test");
		MessageProducer producer = session.createProducer(dest);
		MessageConsumer consumer = session.createConsumer(dest);
		consumer.setMessageListener(this); 
		conn.start();
		
		this.message.setText("This is a test message form : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		producer.send(this.message);
		
		producer.close();
		consumer.close();
		session.close();
		conn.close();
	}
	
}
