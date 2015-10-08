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
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;

// <host>/learnpad/or/execution/{modelsetid}?executionId=id,userid=id,threadid=id,pageid=id,artifactid=id
@Path("/execution/{modelsetid}")
public interface AddExecutionState {

	/**
	 * Sets the state of users flow during a process execution. The
	 * <code>threadId</code> is used to support parallel flows. Each time a
	 * parallel flow (due to a gateway) occurs, a sub - thread id is created.
	 * For ex. the starting thread is "1" and is split of in another flow, then
	 * this flows thread number would be "1.1", and so on.
	 *
	 * Reentering the same task(page) is recognized based on a automatically
	 * increased sequence number for each state change.
	 *
	 * @param modelSetId
	 *            is the uniq ID of the model set
	 * @param executionId
	 *            unique Id for each new process execution
	 * @param userId
	 *            the users unique id
	 * @param threadId
	 *            starts with 1 and changes in case of a fork
	 * @param pageId
	 *            the wiki page id
	 * @param artifactId
	 *            is the ID of the artifact in the model (event, gateway, unit,
	 *            etc.)
	 * @throws LpRestException
	 */
	@GET
	void addExecutionState(@PathParam("modelsetid") String modelSetId,
			@QueryParam("executionid") String executionId,
			@QueryParam("userid") String userId,
			@QueryParam("threadid") String threadId,
			@QueryParam("pageid") String pageId,
			@QueryParam("artifactid") String artifactId) throws LpRestException;
}
