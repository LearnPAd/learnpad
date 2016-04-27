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

// <host>/learnpad/cw/contentverification/{modelsetid}?artifactid=123&resourceid=123&result={will be a Java class but waiting for a PR first}
public interface ContentVerified {

	/**
	 * @param modelSetId
	 *            is the ID of the model set that is concerned
	 * @param artifactId
	 *            is an ID of artifacts from the model
	 * @param resourceId
	 *            is an ID for the resource that has been verified
	 * @param result
	 *            contain the response about the verification for the specified
	 *            content
	 * @throws LpRestException
	 */
	@Path("/contentverification/{modelsetid}")
	@PUT
	void contentVerified(@PathParam("modelsetid") String modelSetId, @QueryParam("artifactid") String artifactId,
			@QueryParam("resourceid") String resourceId, @QueryParam("result") String result) throws LpRestException;
}