package eu.learnpad.core.impl.or.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.ecs.html.S;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import eu.learnpad.core.impl.or.XwikiBridgeInterfaceRestResource;
import eu.learnpad.core.impl.tests.AbstractUnitTest;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import eu.learnpad.or.rest.data.SimulationScoresMap;
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

		Map<ScoreType, Float> scores = this.generateSimScoreValues();

		SimulationScoresMap simScoreMap = new SimulationScoresMap();
		simScoreMap.setScoreMap(scores);
		
		try {
			this.bridge.updateSimulationScore(modelSetId, simulationSessionId, processArtifactId, timestamp, userId, simScoreMap);
		} catch (LpRestException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getCause());
			Assert.assertFalse(true);
		}

		Assert.assertFalse(false);
	}

	private Map<ScoreType, Float> generateSimScoreValues() {
		Map<ScoreType, Float> scores = new HashMap<ScoreType, Float>();

		// int rnd = this.getRandomGenerator().nextInt(ScoreType.values().length);
		// for (int i = 0; i < rnd ; i++) {
		// ScoreType scoretype = ScoreType.values()[this.getRandomGenerator().nextInt(ScoreType.values().length)];
		// Float value = this.getRandomGenerator().nextFloat();
		// scores.put(scoretype, value);
		// }

		scores.put(ScoreType.ABSOLUTE_GLOBAL_SCORE, 100f);
		scores.put(ScoreType.ABSOLUTE_SESSION_SCORE, 100f);
		scores.put(ScoreType.ABSOLUTE_BP_SCORE, 100f);
		scores.put(ScoreType.GLOBAL_SCORE, 50f);
		scores.put(ScoreType.SESSION_SCORE, 25f);
		scores.put(ScoreType.BP_SCORE, 20f);
		scores.put(ScoreType.BP_COVERAGE, 10f);

		return scores;
	}


	public static void main (String[] args){
		XwikiBridgeInterfaceRestResourceTest me = new XwikiBridgeInterfaceRestResourceTest();		
//		me.calculateKPITest();
//		me.getHandlingProcessStatusTest();
//		me.resourceNotificationTest();
		me.updateSimulationScoreTest();
	}
}