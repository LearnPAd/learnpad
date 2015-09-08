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
package eu.learnpad.qm.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;

public interface GenerateQuestionnaires {
//	@GET
//	byte[] getQuestionnaires(@PathParam("modelid") String modelId,
//			@QueryParam("type") String type, byte[] bpmnFile)
//			throws LpRestException;
//	

	/** modelSet is supposed to the loaded already into the component
	 * @param modelSetId
	 * @param type
	 * @param configurationFile : if null, default configurations will be used (may be a java class in the future)
	 * @return a generation process id
	 * @throws LpRestException
	 */
	@GET
	@Path("/generate/{modelsetid}")
	String generateQuestionnaires(@PathParam("modelsetid") String modelSetId,
			@QueryParam("type")@DefaultValue("mothia-out") String type, byte[] configurationFile) throws LpRestException;
	
	@Path("/generate/{generationprocessid}/status")
	@GET
	String getGenerationStatus(@PathParam("generationprocessid") String generationProcessId)
			throws LpRestException;
	
}
