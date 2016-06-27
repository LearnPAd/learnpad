/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Entities;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Interface to trigger KPI calculations. 
 * 
 * @author sandro.emmenegger
 */
public interface KpiDashboard {
    
    /**
     * Runs the inferencing with KPI rules to calculate individuals and 
     * organisational units KPI values.
     * 
     * @param modelSetId
     * @return
     * @throws LpRestException 
     */
    @Path("/{modelsetid}/calculatekpi")
    @POST    
    Entities calculateKPI(@PathParam("modelsetid") String modelSetId) throws LpRestException;
}
