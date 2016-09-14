package eu.learnpad.core.impl.or.tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import eu.learnpad.core.impl.or.XwikiBridgeInterfaceRestResource;
import eu.learnpad.core.impl.tests.AbstractUnitTest;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import eu.learnpad.sim.rest.event.ScoreType;

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
			String modelId = "fooModelId";
			String artifactId = "fooArtifactId";
			String userId = "b.barnes@learnpad.eu";
			NotificationActionType action = NotificationActionType.ADDED;
			ResourceType resourceType = ResourceType.PAGE;
			String modelSetId = "fooModelSetId";
			this.bridge.resourceNotification(modelSetId, modelId, artifactId, resourceId, resourceType, userId, timestamp, action);
		} catch (LpRestException e) {
			exceptionRaised = true;
		}
		Assert.assertFalse(exceptionRaised);
	}
	
	@Test
	@Ignore	
	public void updateSimulationScoreTest(){
		String modelSetId = "modelsetid";
		String simulationSessionId = "simulationsessionid";
		String processArtifactId = "processartifactid";

		Long timestamp = System.currentTimeMillis();

		String userId = "b.barnes@learnpad.eu";

		Map<String, Object> simulationSessionData = new HashMap<String, Object>();
		for (int i = this.getRandomGenerator().nextInt(10); i >= 0; i--) {
			simulationSessionData.put("data" + i, "value" + i);
		}

		for (ScoreType scoreType : ScoreType.values()) {
			Float scoreValue = this.getRandomGenerator().nextFloat();

			try {
				this.bridge.updateSimulationScore(modelSetId, simulationSessionId, processArtifactId, timestamp, userId, scoreType, scoreValue);
			} catch (LpRestException e) {
				System.err.println(e.getMessage());
				Assert.assertFalse(true);
			}
		}

		Assert.assertFalse(false);
	}


	public static void main (String[] args){
		XwikiBridgeInterfaceRestResourceTest me = new XwikiBridgeInterfaceRestResourceTest();		
//		me.calculateKPITest();
//		me.getHandlingProcessStatusTest();
//		me.resourceNotificationTest();
		me.updateSimulationScoreTest();
	}
}