package eu.learnpad.cw.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;

public interface NotifyRecommendations
{
    @Path("/notify/{modelsetid}")
    @PUT
    public void notifyRecommendations(@QueryParam("simulationid") @DefaultValue("") String simulationid,
        @QueryParam("userid") @DefaultValue("") String userId, Recommendations rec) throws LpRestException;
}
