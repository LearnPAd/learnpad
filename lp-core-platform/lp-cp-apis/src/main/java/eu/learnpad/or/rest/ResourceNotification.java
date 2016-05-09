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

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;

public interface ResourceNotification {

	/**
	 * @param modelSetId
	 *            is the ID of the model set that is concerned
	 * @param resourceId
	 *            is the ID that designate the resource, ex. a wiki page, a 
         *            comment for a page, an attachement or a feedback
         * @param resourceType
         *            type of resource (wiki page, comment, etc.)      
         * @param referringToResourceId
         *            resource id of referred resource (ex. the page id a comment belongs to)
         * @param modelArtifactIds
	 *            is a list of ID to other artifacts from the model, 
         *            linked to this resource
         * @param userId
         *            unique user identifier for the system
         * @param timestamp
         *            timestamp of action on platform
         * @param action
	 *            will precise the kind of notification (visited, added, modified, deleted)
	 * @throws LpRestException
	 */
	// <host>/learnpad/or/resourcenotification/{modelsetid}?resourceid=id,linkedto=(id1,id2,id3),action={added|modified|deleted}
	@POST
	@Path("/{modelsetid}/resourcenotification")
	void resourceNotification(@PathParam("modelsetid") String modelSetId,
			@QueryParam("resourceid") String resourceId,
                        @QueryParam("resourcetype") ResourceType resourceType,
                        @QueryParam("referringtoresourceid") String referringToResourceId,
                        @QueryParam("modelartifactids") String[] modelArtifactIds,
			@QueryParam("userid") String userId, 
                        @QueryParam("timestamp") Long timestamp, 
                        @QueryParam("action") NotificationActionType action) throws LpRestException;
}