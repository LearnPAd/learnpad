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
import eu.learnpad.or.rest.data.SimilarCases;

public interface GetSimilarCases {

    /**
     * Search for similar cases with the given case descriptions. 
     * Each parameter belonging to the application is optional.
     *
     * @param modelSetId is the uniq ID of the model set
     * @param artifactId is the ID of the artifact in the model (event, gateway, unit, etc.)
     * @param userId the users unique
     * @param applicantName name of the applicant
     * @param applicationCity location of the object the application belongs to
     * @param applicationZone spatial zone(s) affected by the application
     * @param applicationType type of application
     * @param applicationPublicAdministration responsible public administration
     * @param applicationSector business sector(s) affected by application
     * @param applicationBusinessActivity business activity or activities affected by application
     * @param applicationDescription short freetext description of application
     * @param applicationATECOCategory standard category code the application object belongs to
     * @return list of similar cases with assigned content.
     *
     * @throws LpRestException
     */
    // <host>/learnpad/or/similarcases?artifactName=A name,artifactDescription=Some description,artifactType={event-start|gateway-exclusive|task|event-end|...},modelType={bpmn|cmmn|...},freeDescription=Some text,existingArtifactId=id,existingArtifactStructureDepth=2
    @Path("/similarcases")
    @GET
    SimilarCases retrieveSimilarCases(
            @QueryParam("modelsetId") String modelSetId,
            @QueryParam("artifactid") String artifactId,
            @QueryParam("userid") String userId,
            @QueryParam("applicantName") String applicantName,
            @QueryParam("applicationCity") String applicationCity,
            @QueryParam("applicationZone") String applicationZone,
            @QueryParam("applicationType") String applicationType,
            @QueryParam("applicationPublicAdministration") String applicationPublicAdministration,
            @QueryParam("applicationSector") String applicationSector,
            @QueryParam("applicationBusinessActivity") String applicationBusinessActivity,
            @QueryParam("applicationDescription") String applicationDescription,
            @QueryParam("applicationATECOCategory") String applicationATECOCategory)
            throws LpRestException;
}
