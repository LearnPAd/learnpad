package eu.learnpad.cw.tests;

import java.security.SecureRandom;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.cw.CWXwikiBridge;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;


/**
*
* @author gulyx
*/
public class CWBridgeTest {
	private CWXwikiBridge bridge;
	private SecureRandom random;
	
	public CWBridgeTest() {
		this.random = new SecureRandom();
	}
		
	@Test
	public void testInsertNotifiedReccomandations(){	
		bridge = new CWXwikiBridge();
		
		int maxTentatives = this.random.nextInt(4);
		long baseValue = this.random.nextLong()/2;
		
		Recommendations rec = new Recommendations();
		String modelSetId = "mod."+String.valueOf(this.random.nextInt());
		String userId = "dummyUser";
		
		try {
			bridge.initialize();
			
			for (int i=0; i<maxTentatives; i++){
				baseValue += 1;
// All the recs belongs to a different simulation session			
				String simulationid = String.valueOf(baseValue);
				bridge.notifyRecommendations(modelSetId, simulationid, userId, rec);				
			}
			Map<String,Recommendations> notifiedRecsMaps = bridge.getNotifiedRecommendations(userId);
// All the recs must be preserved			
			Assert.assertTrue( notifiedRecsMaps.size() == maxTentatives );
			
		} catch (InitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LpRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertFalse(true);		
	}
	
	@Test
	public void testBannedSimIdInNotifiedReccomandations(){	
		bridge = new CWXwikiBridge();
				
		int maxTentatives = this.random.nextInt(4)+1;
// All the recs belongs to a the same simulation session			
		String simulationid = String.valueOf(this.random.nextLong());
		
		Recommendations rec = new Recommendations();
		String modelSetId = "mod."+String.valueOf(this.random.nextInt());
		String userId = "dummyUser";
		
		try {
			bridge.initialize();
			
			bridge.notifyRecommendations(modelSetId, simulationid, userId, rec);				
// Now the rec is deleted and the simulationid become banned			
			bridge.deleteRecommendations(modelSetId, simulationid, userId);
			
			for (int i=0; i<maxTentatives; i++){
// Since the simulationid is banned, so no more recs for this simulation session are accepted
				bridge.notifyRecommendations(modelSetId, simulationid, userId, rec);				
			}
			
			Map<String,Recommendations> notifiedRecsMaps = bridge.getNotifiedRecommendations(userId);
			Assert.assertTrue( notifiedRecsMaps.isEmpty() );
			
		} catch (InitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LpRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertFalse(true);		
	}
	
}
