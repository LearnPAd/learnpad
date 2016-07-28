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
package eu.learnpad.cw.rest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;

//<host>/learnpad/cw/resourcenotification/{modelsetid}?modelid=123&artifactid=123&resourceid=123&action={visited|added|deleted|modified}&type={page|comment|attachment|feedback}&useid=bkjsbdsa
public interface ResourceNotification {

	/**
	 * @param modelSetId
	 *            is the ID of the model set that is concerned
	 * @param modelId
	 *            is the ID of the model that is concerned. In case the resource
	 *            targets the whole modelset, this param will be null/unset
	 * @param artifactId
	 *            is the ID of the artifact from the model, linked to this
	 *            resource. In case the resource targets the whole model, this
	 *            param will be null/unset
	 * @param resourceId
	 *            is the ID that designate the resource
	 * @param type
	 *            is the type of the resource (See
	 *            {@link eu.learnpad.or.rest.data.ResourceType})
	 * @param action
	 *            will precise the kind of notification (view, added, deleted,
	 *            modified. {@link See
	 *            eu.learnpad.or.rest.data.NotificationActionType})
	 * @param userId
	 *            is the ID of the user
	 * @throws LpRestException
	 * 
	 */
	@Path("/resourcenotification/{modelsetid}")
	@PUT
	void resourceNotification(@PathParam("modelsetid") String modelSetId,
			@QueryParam("modelid") String modelId,
			@QueryParam("artifactid") String artifactId,
			@QueryParam("resourceid") String resourceId,
			@QueryParam("resourcetype") ResourceType type,
			@QueryParam("action") NotificationActionType action,
			@QueryParam("userid") String userId) throws LpRestException;
}