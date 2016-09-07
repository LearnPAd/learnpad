/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import eu.learnpad.ontology.AbstractUnitTest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
public class QueryMapTest extends AbstractUnitTest {
    
    public QueryMapTest() {
    }

    /**
     * Test of getQuery method, of class QueryMap.
     */
    @Test
    public void testGetQuery() {
        RecommenderQuery query = QueryMap.getQuery("expertsWithSameRole");
        assertNotNull(query);
        assertNotNull(query.getQueryString());
        assertTrue(query.getQueryString().length() > 0);
    }
    
}
