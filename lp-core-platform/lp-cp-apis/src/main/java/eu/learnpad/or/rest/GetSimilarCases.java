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
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;

// <host>/learnpad/or/similarcases?artifactName=A name,artifactDescription=Some description,artifactType={event-start|gateway-exclusive|task|event-end|...},modelType={bpmn|cmmn|...},freeDescription=Some text,existingArtifactId=id,existingArtifactStructureDepth=2
@Path("/similarcases")
public interface GetSimilarCases {

	/**
	 * Search for similar cases with the given case descriptions. Each parameter
	 * is optional.
	 * 
	 * !!This feature list and case description structure is a proposal and not
	 * final.
	 *
	 * @param artifactName
	 *            the name or title of an artifact
	 * @param artifactDescription
	 *            a free text description of a single artifact
	 * @param artifactType
	 *            might be further classify
	 * @param modelType
	 *            the model type, ex. business process (BPMN, ...)
	 * @param freeDescription
	 * @param existingArtifactId
	 *            allows to search for similar cases according the properties of
	 *            an existing or selected artifact.
	 * @param existingArtifactStructureDepth
	 *            considers connected artifacts of a selected artifact reference
	 *            by the parameter <code>existingArtifactId</code> until the
	 *            defined depth.
	 * @throws LpRestException
	 */
	@GET
	void addExecutionState(
			@QueryParam("artifactName") String artifactName,
			@QueryParam("artifactDescription") String artifactDescription,
			@QueryParam("artifactType") String artifactType,
			@QueryParam("modelType") String modelType,
			@QueryParam("freeDescription") String freeDescription,
			@QueryParam("existingArtifactId") String existingArtifactId,
			@QueryParam("existingArtifactStructureDepth") String existingArtifactStructureDepth)
			throws LpRestException;
}
