package eu.learnpad.cw.impl.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import eu.learnpad.or.rest.data.Recommendations;

public abstract class RecommendationsStore {

	private static RecommendationsStore recStore = null;
	
	protected RecommendationsStore() { 
	}

	public synchronized static RecommendationsStore createRecommendationsStore(){
		if (recStore == null)
			recStore = new RecommendationsStoreMultipleMaps();
//			recStore = new RecommendationsStoreWithPairs();
		return recStore;
	} 
	
	
	public abstract void reset();

	/*
	 * It returns an instance if the pair (userID,simID)
	 * links to a Recommendations, null otherwise.
	 *
	 * @param userID
	 * @param simID simulation session ID
	 * @returns Recommendations or null  
	 */	
	public abstract Recommendations get(String userID, String simID);

	/*
	 * It returns a map of Recommendations by SimID if the userID
	 * links to instances of Recommendations, null otherwise.
	 *
	 * @param userID
	 * @returns Recommendations Map by SimID or null  
	 */	
	public abstract Map<String,Recommendations> get(String userID);
	
	/*	
 * Stores the specified Recommendations with the specified pair (userID,simID)
 * Any previously data stored with the same pair is replaced.
 * In this case, the old instance of Recommendations is returned.
 * If there were no previous values stored with the pair, null is returned 
 * 
 * @param userID
 * @param simID simulation session ID
 * @param rec
 * @returns old Recommendations or null  
 */
	public abstract Recommendations put(String userID, String simID, Recommendations rec);
	
	/*
	 * Delete the Recommendations for a pair (userID,simID) if it exists.
	 * Returns the value previously associated the pair, or null. 
	 *
	 * @param userID
	 * @param simID simulation session ID
	 * @returns deleted Recommendations or null  
	 */
	public abstract Recommendations delete(String simID, String userID);

	/*
	 * Delete all the Recommendations for a given userID if it exists.
	 * Returns the a map of Recommendations previously associated the pair, or null. 
	 *
	 * @param userID
	 * @param rec
	 * @returns deleted map of Recommendations by SimID or null  
	 */
	public abstract Map<String,Recommendations> delete(String userID);	
}