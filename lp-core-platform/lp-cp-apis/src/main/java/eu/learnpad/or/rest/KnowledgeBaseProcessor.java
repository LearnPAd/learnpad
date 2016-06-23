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
package eu.learnpad.or.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessId; 
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatus; 

public interface KnowledgeBaseProcessor {

    /**
     * Runs the inferencing with KPI rules to calculate individuals and 
     * organisational units KPI values.
     * 
     * @param modelSetId
     * @return
     * @throws LpRestException 
     */
	@GET
    @Path("/kb/{modelsetid}/calculatekpi")
	@Produces(MediaType.APPLICATION_XML)
	KBProcessId calculateKPI(@PathParam("modelsetid") String modelSetId) throws LpRestException;

	@GET
	@Path("/kb/getstatus")
	@Produces(MediaType.APPLICATION_XML)
	KBProcessingStatus getHandlingProcessStatus(@QueryParam("kbprocessprocessid") String kbProcessProcessId)
			throws LpRestException;
	
}
