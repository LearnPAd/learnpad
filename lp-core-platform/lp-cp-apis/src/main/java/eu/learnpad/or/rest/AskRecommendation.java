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

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;

public interface AskRecommendation {

	/**
	 *
	 *
	 * @param modelSetId
	 *            is the uniq ID of the model set
	 * @param artifactId
	 *            is the ID of the artifact in the model (event, gateway, unit,
	 *            etc.)
	 * @param userId
	 * @param simulationSessionId
	 *            unique id of a simulation session instance. This is an
	 *            optional parameter, and it is meaningful only recommendations
	 *            are requested in conjunction with a simulation session. If it
	 *            is specified, the method lookups case description (case
	 *            characterization) of the case assigned to the simulation id
	 *            and search for similar cases based on the comparison of the
	 *            case characterizations. In case it is not specified, the
	 *            parameter assumes the agreed default value (i.e. "--none--"),
	 *            and it must be ignored.
	 * 
	 * @return is the list of recommendations, similar cases must be expected
	 *         only if the parameter {@code simulationSessionId} has been
	 *         specified and it is different from the default value.
	 * @throws LpRestException
	 */
	// <host>/learnpad/or/bridge/{modelsetid}/recommendation?artifactid=idArtifact&userid=idUser&simulationsessionid=idSimSess
	@Path("/{modelsetid}/recommendation")
	@GET
	Recommendations askRecommendation(@PathParam("modelsetid") String modelSetId,
			@QueryParam("artifactid") String artifactId, @QueryParam("userid") String userId,
			@QueryParam("simulationsessionid") @DefaultValue("--none--") String simulationSessionId)
			throws LpRestException;
}