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

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.me.rest.data.ModelSetType;

public interface Transformer {

	/**
	 * Ask for a transformation of the model into an XWiki package
	 * 
	 * @param type
	 *            is the kind of model (Adoxx, MagicDraw or Learn PAd)
	 * @param model
	 *            is the XML stream of the model
	 * @return an XFF xwiki package
	 * @throws LpRestException
	 *             when any error is raised during the process
	 */
	// <host>/learnpad/cw/corefacade/transform?type={ADOXX|MD}
	@POST
	@Path("/transform")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	InputStream transform(@QueryParam("type") @DefaultValue("ADOXX") ModelSetType type, InputStream model)
			throws LpRestException;
}