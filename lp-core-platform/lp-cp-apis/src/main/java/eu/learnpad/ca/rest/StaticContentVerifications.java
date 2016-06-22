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
package eu.learnpad.ca.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalyses;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Path("/validatestaticcontent")
public interface StaticContentVerifications {

	@Path("/")
	@POST
	String putValidateStaticContent(StaticContentAnalysis contentFile) throws LpRestException;

	@Path("/{idAnnotatedStaticContentAnalysis:.*}")
	@GET
	AnnotatedStaticContentAnalyses getStaticContentVerifications(
			@PathParam("idAnnotatedStaticContentAnalysis") String contentID) throws LpRestException;

	@Path("/{idAnnotatedStaticContentAnalysis:.*}/status")
	@GET
	String getStatusStaticContentVerifications(@PathParam("idAnnotatedStaticContentAnalysis") String contentID)
			throws LpRestException;

	/**
	 *
	 * @param contentID
	 *            the id of contents that have been processed 
	 * @return the URL were is possible to access the default view (e.g. html) about of the AnnotatedCollaborativeContentAnalyses
	 * 		   processed for the given contentID
	 */
	@Path("/{idAnnotatedStaticContentAnalysis:.*}/view")
	@GET
	String getStaticContentVerificationsView(
			@PathParam("idAnnotatedStaticContentAnalysis") String contentID) throws LpRestException;

}