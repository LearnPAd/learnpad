package eu.learnpad.simulator.mon.services;

import java.net.InetAddress;
import java.util.HashMap;

public class HashMapManager {

	public HashMap<String, String> theCommonMapTable;
	public InetAddress test;
	
	public HashMapManager() {
		
		theCommonMapTable = new HashMap<String, String>();
	}
	
	public String getMachine(String serviceName, String serviceType, String serviceRole) {
		return localSearchName(serviceName);
	}

	private String localSearchName(String serviceName) {
		return theCommonMapTable.get(serviceName);
	}
	
	public boolean insertLocalTable(int ruleInsertionID, String serviceName,
			String machineIP, String serviceType, String serviceRole) {
		
		theCommonMapTable.put(serviceName, machineIP);
		
		return true;
	}

	public boolean deleteLocalTable(int ruleInsertionID, String serviceName,
			InetAddress machineIP, String serviceType, String serviceRole) {
		return false;
	}

	public boolean updateLocalTable(int ruleInsertionID, String serviceName,
			InetAddress machineIP, String serviceType, String serviceRole) {
		return false;
	}
}
