
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
package eu.learnpad.ca.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalyses;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public interface StaticContentVerifications {

	
	@Path("/learnpad/ca/validatestaticcontent")
	@POST
	String putValidateStaticContent(StaticContentAnalysis contentFile)
				throws LpRestException;
	
	
	@Path("/learnpad/ca/staticcontentverifications/{idAnnotatedStaticContentAnalysis:.*}")
	@GET
	AnnotatedStaticContentAnalyses getStaticContentVerifications(@PathParam("idAnnotatedStaticContentAnalysis") String contentID)
			throws LpRestException;
	
	@Path("/learnpad/ca/staticcontentverifications/{idAnnotatedStaticContentAnalysis:.*}/status")
	@GET
	String getStatusStaticContentVerifications(@PathParam("idAnnotatedStaticContentAnalysis") String contentID)
			throws LpRestException;
}
/**
*@startuml
*
LPCorePlatform -> ContentAnalysis:putValidateStaticContent(contentXML) HTTP Req @POST /learnpad/ca/validatestaticcontent 
activate ContentAnalysis #FFBBBB 

ContentAnalysis-->LPCorePlatform: Http Response OK (idAnnotatedStaticContentAnalysis)

ContentAnalysis->ContentAnalysis: Perform verifications

LPCorePlatform -> ContentAnalysis: getStatusStaticContentVerifications(idAnnotatedStaticContentAnalysis) @GET /learnpad/ca/staticcontentverifications/{idAnnotatedStaticContentAnalysis}/status

ContentAnalysis-->LPCorePlatform: Http Response OK (Status:Processing)



LPCorePlatform -> ContentAnalysis: getStatusStaticContentVerifications(idAnnotatedStaticContentAnalysis) @GET /learnpad/ca/staticcontentverifications/{idAnnotatedStaticContentAnalysis}/status

ContentAnalysis-->LPCorePlatform: Http Response OK (Status:OK)

deactivate ContentAnalysis #FFBBBB 

LPCorePlatform -> ContentAnalysis: getStaticContentVerifications(idAnnotatedStaticContentAnalysis) @GET  /learnpad/ca/staticcontentverifications/{idAnnotatedStaticContentAnalysis}

ContentAnalysis-->LPCorePlatform: Http Response OK (Collection<AnnotatedStaticContentAnalysis>)



*@enduml
**/
