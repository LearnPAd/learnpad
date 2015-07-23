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

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.learnpad.gm.rest.certif.data.CertificateDefinitionData;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/gamification")
public interface ICertificateDefinitionStorage {

	/**
	 * Add a new certificate definition
	 *
	 * @return the id attributed to this certificate definition
	 *
	 */
	@POST
	@Path("/certificatesdefinitions")
	public String storeCertificateDefinition(CertificateDefinitionData data);

	/**
	 *
	 * @return the certificate definitions
	 */
	@GET
	@Path("/certificatesdefinitions/")
	public Set<CertificateDefinitionData> getCertificateDefinitions();

	/**
	 * The information associated with a given certificate definition
	 *
	 * @param certificateId
	 *            the ID associated with the certificate definition
	 * @return the data associated with this definition
	 */
	@GET
	@Path("/sessionscores/{certificateid:.*}")
	public CertificateDefinitionData getCertificate(
			@PathParam("certificateid") String certificateId);

}
