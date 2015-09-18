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
package eu.learnpad.lsm.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;

public interface ExportStatistics {

	/** 
	 * @param learnerEmail
	 *            the email of the learner
	 * @return a doc reporting the statistics on the questionnaires completed by the given learner
	 * @throws LpRestException
	 */
	@GET
	@Path("/getlearnerstat")
	byte [] exportStatisticsByLearner(@QueryParam("learneremail") String learnerEmail) throws LpRestException;

	/** 
	 * @param questionnaireId
	 *            the id of a questionnaire
	 * @return a doc reporting the statistics on the learners completed the given questionnaire
	 * @throws LpRestException
	 */
	@GET
	@Path("/getquestionnairestat")
	byte [] exportStatisticsByQuestionnaire(@QueryParam("questionnaireid") String questionnaireId) throws LpRestException;
}
