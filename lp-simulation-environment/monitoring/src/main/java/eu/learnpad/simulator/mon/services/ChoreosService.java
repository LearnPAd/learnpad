package eu.learnpad.simulator.mon.services;

import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;

import javax.xml.namespace.QName;

public class ChoreosService extends javax.xml.ws.Service {

	protected String idChoreography;
	protected String enactmentID;
	protected String role;
	protected HashMap<InetAddress,Object> dbase = new HashMap<InetAddress,Object>();
	
	
	public ChoreosService(URL wsdlDocumentLocation, QName serviceName) {
		super(wsdlDocumentLocation, serviceName);
	}
	
	public String getEnactmentID() {
		return enactmentID;
	}

	public void setEnactmentID(String enactmentID) {
		this.enactmentID = enactmentID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getIdChoreography() {
		return idChoreography;
	}

	public void setIdChoreography(String idChoreography) {
		this.idChoreography = idChoreography;
	}
}
