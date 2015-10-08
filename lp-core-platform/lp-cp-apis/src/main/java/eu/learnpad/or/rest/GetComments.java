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

import eu.learnpad.exception.LpRestException;

// <host>/learnpad/cp/{modelsetid}/{artifactid}/comments
@Path("/{modelsetid}/{artifactid}/comments")
public interface GetComments {
	/**
	 * <code>
	 * <comments>
	 *   <comment>
	 *     <modelIdAsInTheModelingEnvironment/>
	 *     <artifactIdAsInTheModelingEnvironment/>
	 *     <artifactwikiUrlId/>
	 *     <user/>
	 *     <date/>
	 *     <content/>
	 *   </comment>
	 *   <comment>
	 *     <modelIdAsInTheModelingEnvironment/>
	 *     <artifactIdAsInTheModelingEnvironment/>
	 *     <artifactwikiUrlId/>
	 *     <user/>
	 *     <date/>
	 *     <content/>
	 *   </comment>
	 * </comments>
	 * </code>
	 * 
	 * @param modelSetId
	 *            is the modelset on which we want comments
	 * @param artifactId
	 *            is the artifact (event, task, unit, etc.) on which we want;
	 *            optional, if not present, all the comments from the modelset
	 *            will be returned comments
	 * @return is a file (see above for the format)
	 * @throws LpRestException
	 */
	@GET
	byte[] getComments(@PathParam("modelsetid") String modelSetId,
			@PathParam("artifactId") String artifactId) throws LpRestException;
}
