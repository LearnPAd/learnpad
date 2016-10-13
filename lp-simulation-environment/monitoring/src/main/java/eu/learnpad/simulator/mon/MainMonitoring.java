package eu.learnpad.simulator.mon;
/*  GLIMPSE: A generic and flexible monitoring infrastructure.
  * For further information: http://labsewiki.isti.cnr.it/labse/tools/glimpse/public/main
  * 
  * Copyright (C) 2011  Software Engineering Laboratory - ISTI CNR - Pisa - Italy
  * 
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * 
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * 
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * 
*/

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.simulator.mon.buffer.EventsBuffer;
import eu.learnpad.simulator.mon.cep.ComplexEventProcessor;
import eu.learnpad.simulator.mon.event.GlimpseBaseEvent;
import eu.learnpad.simulator.mon.impl.ComplexEventProcessorImpl;
import eu.learnpad.simulator.mon.impl.EventsBufferImpl;
import eu.learnpad.simulator.mon.impl.LearnerAssessmentManagerImpl;
import eu.learnpad.simulator.mon.impl.RuleTemplateManager;
import eu.learnpad.simulator.mon.manager.GlimpseManager;
import eu.learnpad.simulator.mon.manager.LearnerAssessmentManager;
import eu.learnpad.simulator.mon.manager.RestNotifier;
import eu.learnpad.simulator.mon.services.ServiceLocatorFactory;
import eu.learnpad.simulator.mon.storage.DBController;
import eu.learnpad.simulator.mon.storage.H2Controller;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import eu.learnpad.simulator.mon.utils.MailNotification;
import eu.learnpad.simulator.mon.utils.Manager;
import eu.learnpad.simulator.mon.utils.SplashScreen;

/**
 * @author Antonello Calabr&ograve;
 * 
 * This class is the launcher of the glimpse infrastructure
 * It setup the environment. It is possible to configure
 * different engine for complex event recognition.
 * 
 * It also provide the setup of all the connection to the ESB
 */

public class MainMonitoring {

	private static Properties systemProps = new Properties();

	// start settings
	protected static String ENVIRONMENTPARAMETERSFILE;
	protected static String DROOLSPARAMETERFILE;
	protected static String MANAGERPARAMETERFILE;
	protected static String SOAPREQUESTFILE;
	protected static String DROOLSRULEREQUESTTEMPLATE1;
	protected static String DROOLSRULEREQUESTTEMPLATE2;
	protected static String DROOLSRULEREQUESTTEMPLATE3_1;
	protected static String DROOLSRULEREQUESTTEMPLATE3_2;
	protected static String BSMWSDLURIFILEPATH;
	protected static String REGEXPATTERNFILEPATH;
	protected static String MAILNOTIFICATIONSETTINGSFILEPATH;
	protected static String DATABASECONNECTIONSTRINGH2;
	protected static String DATABASECONNECTIONSTRINGMYSQL;
	public static String RESTNOTIFIERURLSTRING; 
	// end settings

	private static Properties environmentParameters;
	private static TopicConnectionFactory connFact;
	private static InitialContext initConn;


	/**
	 * This method reads parameters from text files
	 * Parameters are relative to environment, cep engine
	 * 
	 * @param systemSettings
	 * @return true if operations are completed correctly
	 */
	
	public static boolean initProps(String systemSettings) {
		try {
			systemProps = Manager.Read(systemSettings);

			ENVIRONMENTPARAMETERSFILE = 		systemProps.getProperty("ENVIRONMENTPARAMETERSFILE");
			DROOLSPARAMETERFILE = 				systemProps.getProperty("DROOLSPARAMETERFILE");
			MANAGERPARAMETERFILE = 				systemProps.getProperty("MANAGERPARAMETERFILE");
			SOAPREQUESTFILE = 					systemProps.getProperty("SOAPREQUESTFILE");
			DROOLSRULEREQUESTTEMPLATE1 = 		systemProps.getProperty("DROOLSRULEREQUESTTEMPLATE1");	
			DROOLSRULEREQUESTTEMPLATE2 = 		systemProps.getProperty("DROOLSRULEREQUESTTEMPLATE2");	
			DROOLSRULEREQUESTTEMPLATE3_1 = 		systemProps.getProperty("DROOLSRULEREQUESTTEMPLATE3_1");
			DROOLSRULEREQUESTTEMPLATE3_2 = 		systemProps.getProperty("DROOLSRULEREQUESTTEMPLATE3_2");
			BSMWSDLURIFILEPATH = 				systemProps.getProperty("BSMWSDLURIFILEPATH");		
			REGEXPATTERNFILEPATH = 				systemProps.getProperty("REGEXPATTERNFILEPATH");
			MAILNOTIFICATIONSETTINGSFILEPATH = 	systemProps.getProperty("MAILNOTIFICATIONPATH");
			DATABASECONNECTIONSTRINGH2 = 		systemProps.getProperty("DATABASECONNECTIONSTRINGH2");
			DATABASECONNECTIONSTRINGMYSQL = 	systemProps.getProperty("DATABASECONNECTIONSTRINGMYSQL");
			RESTNOTIFIERURLSTRING = 			systemProps.getProperty("RESTNOTIFIERURLSTRING");
			return true;
		} catch (Exception asd) {
			System.out.println("USAGE: java -jar MainMonitoring.jar \"systemSettings\"");
			return false;
		}
	}
	
	public static boolean init()
	{
		boolean successfullInit = false;
		
		try 
		{
			//the connection are initialized
			environmentParameters = Manager.Read(ENVIRONMENTPARAMETERSFILE);
			initConn = new InitialContext(environmentParameters);
			 
			DebugMessages.println(TimeStamp.getCurrentTime(), MainMonitoring.class.getSimpleName(), "Connection Parameters");
			DebugMessages.line();
			DebugMessages.println(TimeStamp.getCurrentTime(), MainMonitoring.class.getSimpleName(), 
									"java.naming.factory.initial " + environmentParameters.getProperty("java.naming.factory.initial"));
			DebugMessages.println(TimeStamp.getCurrentTime(), MainMonitoring.class.getSimpleName(), 
									"java.naming.provider.url " + environmentParameters.getProperty("java.naming.provider.url"));
			DebugMessages.println(TimeStamp.getCurrentTime(), MainMonitoring.class.getSimpleName(), 
									"java.naming.security.principal " + environmentParameters.getProperty("java.naming.security.principal"));
			DebugMessages.println(TimeStamp.getCurrentTime(), MainMonitoring.class.getSimpleName(), 
									"java.naming.security.credentials " + environmentParameters.getProperty("java.naming.security.credentials"));
			DebugMessages.println(TimeStamp.getCurrentTime(), MainMonitoring.class.getSimpleName(), 
									"connectionFactoryNames " + environmentParameters.getProperty("connectionFactoryNames"));
			DebugMessages.println(TimeStamp.getCurrentTime(), MainMonitoring.class.getSimpleName(), 
									"topic.serviceTopic " + environmentParameters.getProperty("topic.serviceTopic"));
			DebugMessages.println(TimeStamp.getCurrentTime(), MainMonitoring.class.getSimpleName(), 
									"topic.probeTopic " + environmentParameters.getProperty("topic.probeTopic"));
			DebugMessages.line();
			DebugMessages.print(TimeStamp.getCurrentTime(), MainMonitoring.class.getSimpleName(),"Setting up TopicConnectionFactory");
			connFact = (TopicConnectionFactory)initConn.lookup("TopicCF");
			DebugMessages.ok();
			DebugMessages.line();
			successfullInit = true;
		} catch (NamingException e) {
			e.printStackTrace();
			successfullInit = false;
		} catch (Exception e) {
			e.printStackTrace();
			successfullInit = false;
		}
		return successfullInit;
	}

	/**
	 * Read the properties and init the connections to the enterprise service bus
	 * 
	 * @param is the systemSettings file
	 */
	public static void main(String[] args) {
		try{
			FileOutputStream fos = new FileOutputStream("glimpseLog.log");
			PrintStream ps = new PrintStream(fos);
			System.setErr(ps);		
			
			if (MainMonitoring.initProps(args[0]) && MainMonitoring.init()) {
	
				System.out.println("Running ActiveMQ instance on " + environmentParameters.getProperty("java.naming.provider.url"));
				
				ActiveMQRunner anActiveMQInstance = new ActiveMQRunner(environmentParameters.getProperty("java.naming.provider.url"));
			    new Thread(anActiveMQInstance).start();
								
				while (!anActiveMQInstance.isBrokerStarted()) {
					Thread.sleep(1000);
				}
				
				System.out.println("ActiveMQ is running");
				System.out.println("Running GLIMPSE");
				SplashScreen.Show();
				System.out.println("Please wait until setup is done...");
				
				//the buffer where the events are stored to be analyzed, in this version
				//the buffer object is not used because Drools has it's own eventStream object
				EventsBuffer<GlimpseBaseEvent<?>> buffer = new EventsBufferImpl<GlimpseBaseEvent<?>>();

				//The complex event engine that will be used (in this case drools)
				ComplexEventProcessor engineOne = new ComplexEventProcessorImpl(
						Manager.Read(MANAGERPARAMETERFILE), buffer, connFact,
						initConn);

				engineOne.start();
				
				RestNotifier notifierEngine = new RestNotifier();
				notifierEngine.start();
				
				//using H2 database
				DBController databaseController = new H2Controller(Manager.Read(DATABASECONNECTIONSTRINGH2));
				
				//using MYSQL database
				//DBController databaseController = new MySqlController(Manager.Read(DATABASECONNECTIONSTRINGMYSQL));
				
				LearnerAssessmentManager lam = new LearnerAssessmentManagerImpl(databaseController);
				lam.start(); 
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				RuleTemplateManager templateManager = new RuleTemplateManager(
										DROOLSRULEREQUESTTEMPLATE1,
										DROOLSRULEREQUESTTEMPLATE2, 
										DROOLSRULEREQUESTTEMPLATE3_1,
										DROOLSRULEREQUESTTEMPLATE3_2);

				//the component in charge to locate services and load specific rules.
				ServiceLocatorFactory.getServiceLocatorParseViolationReceivedFromBSM(
										engineOne, templateManager, REGEXPATTERNFILEPATH).start();

				//start MailNotifier component
				MailNotification mailer = new MailNotification(
						Manager.Read(MAILNOTIFICATIONSETTINGSFILEPATH));
				mailer.start();
				//the manager of all the architecture
				GlimpseManager manager = new GlimpseManager(
						Manager.Read(MANAGERPARAMETERFILE), connFact, initConn,
						engineOne.getRuleManager(), lam);
				
				manager.start();
			}
		} catch (Exception e) {
			System.out.println("USAGE: java -jar MainMonitoring.jar \"systemSettings\"");			
		}
	}
	

	
}
