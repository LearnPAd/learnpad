package eu.learnpad.simulator.mon.services;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPMessage;

/**
 * @author Antonello Calabr&ograve;
 * @version 3.3
 * 
 */
public abstract class ServiceLocator extends Thread {

	protected abstract SOAPConnection createConnectionToService();
	protected abstract String readSoapRequest(String SoapRequestXMLFilePath);
	protected abstract SOAPMessage messageCreation(String soapMessageStringFile);
	protected abstract SOAPMessage messageSendingAndGetResponse(SOAPConnection soapConnection, SOAPMessage soapMessage, String serviceWsdl);
	
	public abstract String getMachineIPLocally(String serviceName, String serviceType, String serviceRole); //check if the mapping between service and machine has been already done
	public abstract String getMachineIPQueryingDSB(String serviceName, String serviceType, String serviceRole); //if local check fails, this method will starts a procedure to query the dsb to get machine name
}
