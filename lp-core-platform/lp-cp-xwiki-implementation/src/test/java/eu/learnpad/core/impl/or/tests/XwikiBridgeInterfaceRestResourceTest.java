package eu.learnpad.core.impl.or.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import eu.learnpad.core.impl.or.XwikiBridgeInterfaceRestResource;
import eu.learnpad.core.impl.tests.AbstractUnitTest;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;

public class XwikiBridgeInterfaceRestResourceTest extends AbstractUnitTest {
	private XwikiBridgeInterfaceRestResource bridge;
	
	public XwikiBridgeInterfaceRestResourceTest(){
		this.bridge = new XwikiBridgeInterfaceRestResource();
	}
		
	@Test
	@Ignore
	public void calculateKPITest() {
		boolean exceptionRaised = false;
		
		String modelSetId = this.randomId(); 
		try {
			this.bridge.calculateKPI(modelSetId);
		} catch (LpRestException e) {
			exceptionRaised = true;
		}
		Assert.assertFalse(exceptionRaised);
	}

	@Test
	@Ignore
	public void getHandlingProcessStatusTest(){
		boolean exceptionRaised = false;
		
		String kbProcessProcessId = this.randomId(); 
		try {
			this.bridge.getHandlingProcessStatus(kbProcessProcessId);
		} catch (LpRestException e) {
			exceptionRaised = true;
		}
		Assert.assertFalse(exceptionRaised);
	}
	
	@Test
	@Ignore
	public void resourceNotificationTest(){
		boolean exceptionRaised = false;
		
		try {
			Long timestamp = new Long(System.currentTimeMillis());
			String resourceId = "fooResourceId";
			String referringToResourceId = "foo";
			String modelId = "fooModelId";
			String artifactId = "fooArtifactId";
			String userId = "bbarnes@learnpad.eu";
			NotificationActionType action = NotificationActionType.ADDED;
			ResourceType resourceType = ResourceType.PAGE;
			String modelSetId = "fooModelSetId";
			this.bridge.resourceNotification(modelSetId, modelId, artifactId, resourceId, resourceType, referringToResourceId, userId, timestamp, action);
		} catch (LpRestException e) {
			exceptionRaised = true;
		}
		Assert.assertFalse(exceptionRaised);
	}

	public static void main (String[] args){
		XwikiBridgeInterfaceRestResourceTest me = new XwikiBridgeInterfaceRestResourceTest();		
//		me.calculateKPITest();
//		me.getHandlingProcessStatusTest();
		me.resourceNotificationTest();
	}
}