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
package eu.learnpad.me.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.mv.rest.data.VerificationId;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.rest.data.VerificationStatus;
import eu.learnpad.mv.rest.data.VerificationsAvailable;

public interface CheckModelSet {

	/**
	 * @param modelSetId
	 *            is the ID of the model set to check
	 * @param type
	 *            of file that you uploaded and that you want to check
	 *            {adoxx|md|lpzip}
	 * @param verification
	 *            is the kind of verification you want to realize (only
	 *            'deadlock' at the moment)
	 * @return a process verification ID that you can use to check the progress
	 *         of the verification
	 * @throws LpRestException
	 */
	// "/learnpad/me/checkmodelset/start/{modelsetid}"

	@PUT
	@Path("/checkmodelset/start/{modelsetid}")
	@Produces(MediaType.APPLICATION_XML)
	VerificationId startModelSetVerification(@PathParam("modelsetid") String modelSetId,
			@QueryParam("type") @DefaultValue("lpzip") String type,
			@QueryParam("verification") @DefaultValue("ALL") String verification) throws LpRestException;

	/**
	 * @param verificationProcessId
	 *            is the ID returned by startModelSetVerification
	 * @return the progression status
	 * @throws LpRestException
	 */
	// "/learnpad/me/checkmodelset/check/{verificationprocessid}"

	@GET
	@Path("/checkmodelset/check/{verificationprocessid}")
	@Produces(MediaType.APPLICATION_XML)
	VerificationStatus checkModelSetVerification(@PathParam("verificationprocessid") String verificationProcessId)
			throws LpRestException;

	/**
	 * @param verificationProcessId
	 *            is the ID returned by startModelSetVerification
	 * @return the results if finished
	 * @throws LpRestException
	 */
	// "/learnpad/me/checkmodelset/check/{verificationprocessid}"

	@GET
	@Path("/checkmodelset/results/{verificationprocessid}")
	@Produces(MediaType.APPLICATION_XML)
	VerificationResults getModelSetVerificationResults(@PathParam("verificationprocessid") String verificationProcessId)
			throws LpRestException;

	/**
	 * @return the list of all available verifications
	 * @throws LpRestException
	 */
	@GET
	@Path("/checkmodelset/availableverifications")
	@Produces(MediaType.APPLICATION_XML)
	VerificationsAvailable getAvailableVerifications() throws LpRestException;
}