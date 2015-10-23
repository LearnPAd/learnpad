package eu.learnpad.simulator.mon.impl;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.simulator.mon.event.GlimpseBaseEventAbstract;
import eu.learnpad.simulator.mon.storage.StorageManager;

public class StorageManagerImpl extends StorageManager {

	@Override
	public StorageManager getStorageManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(String dbAddress, String dbName, String dbType,
			String loginName, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeMessage(GlimpseBaseEventAbstract<?> anEventToStore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieveMessages(TimeStamp from, TimeStamp to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieveMessages(String sessionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieveMessages(String userID, TimeStamp from, TimeStamp to) {
		// TODO Auto-generated method stub
		
	}
}
