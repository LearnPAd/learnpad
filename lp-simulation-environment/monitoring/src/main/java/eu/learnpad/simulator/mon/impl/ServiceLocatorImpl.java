package eu.learnpad.simulator.mon.impl;

/**
 * @author Antonello Calabr&ograve;
 * @version 3.3
 * 
 */


import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;
import javax.xml.messaging.URLEndpoint;

import org.apache.commons.net.ntp.TimeStamp;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import eu.learnpad.simulator.mon.alerts.NodeInformationsParser;
import eu.learnpad.simulator.mon.alerts.SLAAlertParser;
import eu.learnpad.simulator.mon.cep.ComplexEventProcessor;
import eu.learnpad.simulator.mon.services.HashMapManager;
import eu.learnpad.simulator.mon.services.ServiceLocator;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import eu.learnpad.simulator.mon.utils.Manager;

public class ServiceLocatorImpl extends ServiceLocator {

	public static String localSoapRequestFilePath;
	public static ServiceRegistryImpl dataSetForCollectedInformation;
	public static RuleTemplateManager localRuleTemplateManager;
	public static String localBsmWsdlUriFilePath;
	public static String localRegexPatternFilePath;
	
	public static ServiceLocatorImpl instance = null;
	public static HashMapManager theHashMapManager;
	public static ComplexEventProcessor anEngine = null;
	
	public Properties regexPatternProperties;
	
	public static synchronized ServiceLocatorImpl getSingleton() {
        if (instance == null) 
            instance = new ServiceLocatorImpl(anEngine,
            		localSoapRequestFilePath,
            		RuleTemplateManager.getSingleton(),
            		localBsmWsdlUriFilePath,
            		localRegexPatternFilePath);
        return instance;
    }
	
	public ServiceLocatorImpl(ComplexEventProcessor engine,
			String soapRequestFilePath,
			RuleTemplateManager ruleTemplateManager,
			String bsmWsdlUriFilePath,
			String regexPatternFilePath) {
		
		DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Starting ServiceLocator component ");

		ServiceLocatorImpl.instance = this;
		ServiceLocatorImpl.anEngine = engine;
		ServiceLocatorImpl.localSoapRequestFilePath = soapRequestFilePath;
		ServiceLocatorImpl.localRuleTemplateManager = ruleTemplateManager;
		ServiceLocatorImpl.localBsmWsdlUriFilePath = bsmWsdlUriFilePath;
		ServiceLocatorImpl.localRegexPatternFilePath = regexPatternFilePath;
	
		theHashMapManager = new HashMapManager();
		regexPatternProperties = Manager.Read(regexPatternFilePath);		
		DebugMessages.ok();

	}
	
	public void run() {
		
		while (this.getState() == State.RUNNABLE) {
			try {
				Thread.sleep(90000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void triggeredCheck(String serviceName) {
		analyzeBSMResponse(
				messageSendingAndGetResponse(createConnectionToService(),
						messageCreation(
								localSoapRequestFilePath),
								localBsmWsdlUriFilePath), serviceName);
	}
	
	protected void analyzeBSMResponse(SOAPMessage messageFromBSM, String serviceName) {
		String finalProviderAddress = "";
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			messageFromBSM.writeTo(out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		}

		//this is the SOAP response in XML format
		String soapResponseRaw = new String(out.toByteArray());

		NodeInformationsParser ourParser = new NodeInformationsParser(soapResponseRaw,serviceName);
		ourParser.process();
		finalProviderAddress = ourParser.getProcessedMachineName();

		//add or update new data on commonmaptable
		if (theHashMapManager.getMachine(serviceName, null,null) == null) {
				theHashMapManager.theCommonMapTable.put(serviceName, finalProviderAddress);	
		}
		else {
			theHashMapManager.theCommonMapTable.remove(serviceName);
			theHashMapManager.theCommonMapTable.put(serviceName, finalProviderAddress);
		}
		
	}
			
	protected SOAPConnection createConnectionToService() {
		
		SOAPConnectionFactory connectionFactory;
		SOAPConnection soapConnection;
		
		try {
			connectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = connectionFactory.createConnection();            
            return soapConnection;
		
        } catch (SOAPException soape) {
        	DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getCanonicalName(), "error creating connection");
        	soape.printStackTrace();
        	return null;
        }
	}
	
	protected SOAPMessage messageCreation(String soapMessageStringFilePath) {
		
		 	SOAPMessage message = null;
			try {
				message = MessageFactory.newInstance().createMessage(); 
	        SOAPPart soapPart = message.getSOAPPart();  
	        soapPart.setContent(
	        		new StreamSource(
	        				new FileInputStream(soapMessageStringFilePath)));  
	        message.saveChanges();  
	        return message;  
			} catch (SOAPException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return message; 
	}
	
	protected SOAPMessage messageSendingAndGetResponse(SOAPConnection soapConnection, SOAPMessage soapMessage, String serviceWsdlFilePath) {
		SOAPMessage resp;
        URLEndpoint endpoint = new URLEndpoint (Manager.ReadTextFromFile(serviceWsdlFilePath));
        
        try {
			 resp = soapConnection.call(soapMessage, endpoint);
			 soapConnection.close();
			 return resp;
		}

        catch (SOAPException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getCanonicalName(), "error during messageSendingAndGetResponse");		
			e.printStackTrace();
			return null;
		}
	}
	
	protected String readSoapRequest(String SoapRequestXMLFilePath) {
		
		File SoapRequestXMLFile = new File(SoapRequestXMLFilePath);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(SoapRequestXMLFile);
			doc.getDocumentElement().normalize();
		} catch (SAXException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(),this.getClass().getCanonicalName(),"error during SoapRequestXMLFile reading - SaxException");
			return null;
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(),this.getClass().getCanonicalName(),"error during SoapRequestXMLFile reading - IOException");
			return null;
		} catch (ParserConfigurationException e1) {
			DebugMessages.println(TimeStamp.getCurrentTime(),this.getClass().getCanonicalName(),"error during SoapRequestXMLFile reading - ParserConfigurationException");
			return null;
		}
		return doc.getTextContent();
	}

	public static void GetMachineIP(String senderName, String filterService, String serviceRole, RuleTemplateEnum ruleTemplateType, String payload, Long timeStamp) {
		
		DebugMessages.println(TimeStamp.getCurrentTime(),ServiceLocatorImpl.class.getCanonicalName(), "getMachineIP method called");
		ServiceLocatorImpl theLocator = ServiceLocatorImpl.getSingleton();
		
		try{
			SLAAlertParser slaParser = new SLAAlertParser(payload);
			slaParser.process();
			String alertServiceName = slaParser.getProcessedServiceName();
			
			String machineIP = theLocator.getMachineIPLocally(alertServiceName, filterService, serviceRole);
			if (machineIP == null) {
				machineIP = theLocator.getMachineIPQueryingDSB(alertServiceName, filterService, serviceRole);
			}		
			
			//generate the new rule to monitor
			ComplexEventRuleActionListDocument newRule = localRuleTemplateManager
					.generateNewRuleToInjectInKnowledgeBase(machineIP, alertServiceName, ruleTemplateType, timeStamp, filterService);
			
			DebugMessages.println(TimeStamp.getCurrentTime(),
					ServiceLocatorImpl.class.getName(),
					newRule.getComplexEventRuleActionList().xmlText());
			
			//insert new rule into the knowledgeBase
			localRuleTemplateManager.insertRule(newRule,anEngine.getRuleManager());
		}
		catch(IndexOutOfBoundsException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(),ServiceLocatorImpl.class.getName(),"Not an SLA Alert");
		}	 
	}
	
	@Override
	public String getMachineIPLocally(String serviceName, String serviceType, String serviceRole) {
		return theHashMapManager.getMachine(serviceName,serviceType,serviceRole);
	}
	
	@Override
	public String getMachineIPQueryingDSB(String serviceName, String serviceType, String serviceRole) {
		DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Forced service map cache update");
		triggeredCheck(serviceName);
		DebugMessages.ok();
		return getMachineIPLocally(serviceName, serviceType, serviceRole);
	}
}
