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
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalyses;
import eu.learnpad.exception.LpRestException;

public interface ContentAnalysisProxy {

	/**
	 * @param id
	 *            of the content
	 * @param language
	 *            of the analysis
	 * @param options
	 *            are the available options for the analysis
	 *            {simplicity,non_ambiguity,content_clarity,presentation_clarity
	 *            ,completeness,correctness}
	 * @param body
	 *            consists of 3 parts (respectively title, plain content and
	 *            html content) separated by '-*-*-' in an empty line
	 * @return the analysis ID returned by the component CA
	 * @throws LpRestException
	 */
	@POST
	@Path("/analyze")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	String startAnalysis(@QueryParam("id") String id, @QueryParam("language") @DefaultValue("english") String language,
			@QueryParam("option") List<String> options, InputStream body) throws LpRestException;

	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Path("/analyze/{analysisid}/status")
	String getStatus(@PathParam("analysisid") String analysisId) throws LpRestException;

	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Path("/analyze/{analysisid}/view")
	String getView(@PathParam("analysisid") String analysisId) throws LpRestException;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/analyze/{analysisid}")
	AnnotatedCollaborativeContentAnalyses getResults(@PathParam("analysisid") String analysisId) throws LpRestException;
}