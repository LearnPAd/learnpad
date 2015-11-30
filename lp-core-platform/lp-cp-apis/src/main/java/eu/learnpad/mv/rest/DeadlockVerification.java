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
package eu.learnpad.mv.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.mv.rest.data.MVResults;

public interface DeadlockVerification {
	/**
	 * @param modelSetId
	 * @param type
	 * @param modelContent
	 * @return a verification process id
	 * @throws LpRestException
	 */
	@PUT
	@Path("/startdeadlockverification/{modelsetid}")
	String startDeadlockVerification(@PathParam("modelsetid") String modelSetId,
			@QueryParam("type")@DefaultValue("lpzip") String type, byte[] modelContent) throws LpRestException;
	
	@GET
	@Path("/getdeadlockverificationstatus/{verificationprocessid}")
	@Consumes(MediaType.APPLICATION_JSON)
	MVResults getDeadlockVerificationStatus(@PathParam("verificationprocessid") String verificationProcessId) throws LpRestException;
}
