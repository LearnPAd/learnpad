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
		
//		    producer.close();
//		    consumer.close();
//		    session.close();
//		    conn.close();
	}
	
}
