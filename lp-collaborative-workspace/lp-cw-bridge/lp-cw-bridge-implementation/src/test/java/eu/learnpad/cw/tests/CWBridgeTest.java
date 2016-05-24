package eu.learnpad.cw.tests;

import java.security.SecureRandom;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.test.mockito.MockitoComponentMockingRule;

import eu.learnpad.cw.internal.CWXwikiBridge;
import eu.learnpad.cw.internal.RecommendationWebsocketServer;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;

/**
 *
 * @author gulyx
 */
public class CWBridgeTest {
	@Rule
	public final MockitoComponentMockingRule<CWXwikiBridge> mocker = new MockitoComponentMockingRule(
			CWXwikiBridge.class, eu.learnpad.cw.UICWBridge.class);
	// public final MockitoComponentMockingRule<CWXwikiBridge> mocker = new
	// MockitoComponentMockingRule(CWXwikiBridge.class,eu.learnpad.cw.UICWBridge.class,new
	// ArrayList(Arrays.asList(javax.servlet.http.HttpServletRequest,org.xwiki.query.QueryManager.class,
	// eu.learnpad.core.rest.RestResource.class,org.xwiki.configuration.ConfigurationSource.class,
	// org.xwiki.query.QueryFilter.class,
	// org.xwiki.model.reference.DocumentReferenceResolver.class)));
	// public final MockitoComponentMockingRule<CWXwikiBridge> mocker = new
	// MockitoComponentMockingRule(CWXwikiBridge.class,org.xwiki.rest.XWikiRestComponent.class);

	@Rule
	public final MockitoComponentMockingRule<RecommendationWebsocketServer> mockerRecWebSocketServer = new MockitoComponentMockingRule(
			RecommendationWebsocketServer.class);

	private CWXwikiBridge bridge;
	private SecureRandom random;

	public CWBridgeTest() {
		this.random = new SecureRandom();
	}

	@Test
	public void testRecommendationWebsocketServer() throws ComponentLookupException{
		bridge = mocker.getComponentUnderTest();

		RecommendationWebsocketServer recServer = mockerRecWebSocketServer.getComponentUnderTest();			
	}
	
	@Test
	@Ignore
	public void testInsertNotifiedRecommandations()
			throws ComponentLookupException, InitializationException, LpRestException {
		bridge = mocker.getComponentUnderTest();

		int maxTentatives = this.random.nextInt(4) + 1;
		long baseValue = this.random.nextLong() / 2;

		Recommendations rec = new Recommendations();
		String modelSetId = "mod." + String.valueOf(this.random.nextInt());
		String userId = "dummyUser";

		// TODO: getNotifiedRecommendations doesn't exists anymore
		// int numberOfRecsStored = 0;
		// Map<String, Recommendations> notifiedRecsMaps =
		// bridge.getNotifiedRecommendations(userId);
		// if (notifiedRecsMaps != null){
		// numberOfRecsStored = notifiedRecsMaps.size();
		// }

		// bridge.initialize();

		for (int i = 0; i < maxTentatives; i++) {
			baseValue += 1;
			// All the recs belongs to a different simulation session
			String simulationid = String.valueOf(baseValue);
			bridge.notifyRecommendations(modelSetId, simulationid, userId, rec);
		}

		// TODO: getNotifiedRecommendations doesn't exists anymore
		// notifiedRecsMaps = bridge.getNotifiedRecommendations(userId);
		// int currentNumberOfRecsStored = 0;
		// if (notifiedRecsMaps != null){
		// currentNumberOfRecsStored = notifiedRecsMaps.size();
		// }
		//
		// All the recs must be preserved
		// Assert.assertTrue( currentNumberOfRecsStored == (maxTentatives +
		// numberOfRecsStored) );
	}

	@Test
	@Ignore
	public void testBannedSimIdInNotifiedReccomandations()
			throws ComponentLookupException, InitializationException, LpRestException {
		bridge = mocker.getComponentUnderTest();

		int maxTentatives = this.random.nextInt(4) + 1;
		// All the recs belongs to a the same simulation session
		String simulationid = String.valueOf(this.random.nextLong());

		Recommendations rec = new Recommendations();
		String modelSetId = "mod." + String.valueOf(this.random.nextInt());
		String userId = "dummyUser";

		// TODO: getNotifiedRecommendations doesn't exists anymore
		// int numberOfRecsStored = 0;
		// Map<String, Recommendations> notifiedRecsMaps =
		// bridge.getNotifiedRecommendations(userId);
		// if (notifiedRecsMaps != null){
		// numberOfRecsStored = notifiedRecsMaps.size();
		// }

		// bridge.initialize();

		bridge.notifyRecommendations(modelSetId, simulationid, userId, rec);
		// Now the rec is deleted and the simulationid become banned
		bridge.deleteRecommendations(modelSetId, simulationid, userId);

		for (int i = 0; i < maxTentatives; i++) {
			// Since the simulationid is banned, so no more recs for this
			// simulation session are accepted
			// Here there is the assumption that this loop last less than
			// CWXwikiBridge.BANNING_PERIOD_IN_MILLI_SEC
			bridge.notifyRecommendations(modelSetId, simulationid, userId, rec);
		}

		// TODO: getNotifiedRecommendations doesn't exists anymore
		// notifiedRecsMaps = bridge.getNotifiedRecommendations(userId);
		// int currentNumberOfRecsStored = 0;
		// if (notifiedRecsMaps != null){
		// currentNumberOfRecsStored = notifiedRecsMaps.size();
		// }
		//
		// Assert.assertTrue( currentNumberOfRecsStored == numberOfRecsStored );
	}

}
