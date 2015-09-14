package eu.learnpad.ca.rest;

/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */



import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.exception.LpRestException;

//@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_XML)
@Path("/learnpad/ca/validatecollaborativecontent")
public interface ColloborativeContentVerifications {
	
	
	@Path("/test/{idAnnotatedCollaborativeContentAnalysis}")
	@GET
	public String getC(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException;

	
	@Path("/")
	@POST
	String putValidateCollaborativeContent(@FormParam("collaborativecontent") CollaborativeContentAnalysis contentFile)
				throws LpRestException;
	
	/*@Path("/")
	@PUT
	String updateValidateCollaborativeContent(@FormParam("collaborativecontent") CollaborativeContentAnalysis contentFile)
				throws LpRestException;
*/
	@Path("/{idAnnotatedCollaborativeContentAnalysis:.*}")
	@GET
	Collection<AnnotatedCollaborativeContentAnalysis> getCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException;
	
	@Path("/{idAnnotatedCollaborativeContentAnalysis:.*}/status")
	@GET
	String getStatusCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException;
}
/**
*@startuml
*
LPCorePlatform -> ContentAnalysis:putValidateCollaborativeContent(contentXML) HTTP Req @POST /learnpad/ca/validatecollaborativecontent 
activate ContentAnalysis #FFBBBB 

ContentAnalysis-->LPCorePlatform: Http Response OK (idAnnotatedCollaborativeContentAnalysis)

ContentAnalysis->ContentAnalysis: Perform verifications

LPCorePlatform -> ContentAnalysis: getStatusCollaborativeContentVerifications(idAnnotatedCollaborativeContentAnalysis) @GET /learnpad/ca/collaborativecontentverifications/{idAnnotatedCollaborativeContentAnalysis}/status

ContentAnalysis-->LPCorePlatform: Http Response OK (Status:Processing)



LPCorePlatform -> ContentAnalysis: getStatusCollaborativeContentVerifications(idAnnotatedCollaborativeContentAnalysis) @GET /learnpad/ca/collaborativecontentverifications/{idAnnotatedCollaborativeContentAnalysis}/status


ContentAnalysis-->LPCorePlatform: Http Response OK (Status:OK)

deactivate ContentAnalysis #FFBBBB 

LPCorePlatform -> ContentAnalysis: getCollaborativeContentVerifications(idAnnotatedCollaborativeContentAnalysis) @GET  /learnpad/ca/collaborativecontentverifications/{idAnnotatedCollaborativeContentAnalysis}

ContentAnalysis-->LPCorePlatform: Http Response OK (Collection<annotatedCollaborativeContentAnalysis>)



*@enduml
**/