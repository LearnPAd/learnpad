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
package eu.learnpad.gm.rest.certif;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/gamification")
public interface IEditCountStorage {

	/**
	 * Add some edits to the edit count of a given user
	 *
	 * @param userId
	 * @param nbEdits
	 */
	@GET
	@Path("/editscounts/{userid:.*}")
	public void addToEditCount(@PathParam("userid") String userId,
			Integer nbEdits);

	/**
	 * @param userId
	 * @return the current total number of edits of a user
	 */
	@GET
	@Path("/editscounts/{userid:.*}")
	public Integer getEditCount(@PathParam("userid") String userId);
}
