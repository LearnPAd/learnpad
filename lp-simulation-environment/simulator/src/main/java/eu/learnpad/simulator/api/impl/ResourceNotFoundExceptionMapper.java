/**
 *
 */
package eu.learnpad.simulator.api.impl;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.LoggerFactory;

/**
 * Response to send when throwing a {@link NotFoundException}
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ResourceNotFoundExceptionMapper implements
		ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		LoggerFactory.getLogger(ResourceNotFoundExceptionMapper.class).trace(
				"Receive REST request for non-existant resource ({})",
				exception.getMessage());
		return Response.status(Status.NOT_FOUND).build();
	}

}
