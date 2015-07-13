package eu.learnpad.ca.rest.impl;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.learnpad.ca.rest.StaticContentVerifications;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Path("/learnpad/ca/validatestaticcontent")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class StaticContentVerificationsImpl implements StaticContentVerifications {

	@Path("/")
	@POST
	public String putValidateStaticContent(@FormParam("staticcontent") StaticContentAnalysis contentFile)
			throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Path("/{idAnnotatedStaticContentAnalysis:.*}")
	@GET
	public Collection<AnnotatedStaticContentAnalysis> getStaticContentVerifications(
			@PathParam("idAnnotatedStaticContentAnalysis") String contentID) throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Path("/{idAnnotatedStaticContentAnalysis:.*}/status")
	@GET
	public String getStatusStaticContentVerifications(@PathParam("idAnnotatedStaticContentAnalysis") String contentID)
			throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

}
