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
import eu.learnpad.or.rest.data.Recommendations;

// <host>/learnpad/or/{modelsetid}/recommendation?artifactid=userid=id,type={role|context|expert|resource|...}
@Path("/{modelsetid}/recommendation")
public interface AskRecommendation {
	/**
	 * 
	 * The file returned could be XML or JSON (below is shown as XML to explain
	 * the structure) The different types of recommendations are based on the
	 * Ölten's workshop discussions
	 * http://wiki.learnpad.eu/LearnPAdWiki/bin/view
	 * /WP5/MinutesOltenWorkshop#HContextpanel
	 * <p>
	 * A specific format for each kind of recommendation.  For example, the following one is about experts.
	 * <code>
	 * <experts>
	 *   <expert>
	 *     <id>useridfromthemodel</id>
	 *     <user>Jean Simard</user>
	 *     <email>jean.simard@...</email>
	 *     <phone>2132321</phone>
	 *   </expert>
	 * </experts>
	 * </code>
	 * <p>
	 * The different type of recommendations could be:
	 * <ul>
	 * <li>role: about the role concerned by the current artifact (what people?,
	 * what organisation unit?)
	 * <li>context: mainly in execution mode, what are the contextual
	 * information from previous steps that could be needed here
	 * <li>expert: recommend some other people that may help on the current
	 * artifact
	 * <li>resource: other document that can complete the information on the
	 * current artifact
	 * </ul>
	 * 
	 * @param modelSetId
	 *            is the uniq ID of the model set
	 * @param artifactId
	 *            is the ID of the artifact in the model (event, gateway, unit,
	 *            etc.)
	 * @return is the list of recommendations (see above for the format)
	 * @throws LpRestException
	 */
	@GET
	Recommendations askRecommendation(@PathParam("modelsetid") String modelSetId,
			@QueryParam("artifactid") String artifactId,
			@QueryParam("userid") String userId, @QueryParam("type") String type) throws LpRestException;
}
