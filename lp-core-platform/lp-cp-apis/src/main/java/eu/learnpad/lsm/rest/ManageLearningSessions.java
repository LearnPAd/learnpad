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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;

public interface ManageLearningSessions {

	/** 
	 * @param learneremail
	 *            the email of the learner to be subscribed to a questionnaire
	 * @param questionnaireId
	 *            the id of a questionnaire
	 * @throws LpRestException
	 */
	@POST
	@Path("/subscribelearner")
	void subscribeLearnerToQuestionnaire(@QueryParam("learneremail") String learnerEmail, @QueryParam("questionnaireid") String questionnaireId) throws LpRestException;

	/** 
	 * @param learneremail
	 *            the email of the learner to be revoked form a questionnaire
	 * @param questionnaireId
	 *            the id of a questionnaire
	 * @throws LpRestException
	 */
	@POST
	@Path("/getquestionnairestat")
	void revokeLearnerFromQuestionnaire(@QueryParam("learneremail") String learnerEmail, @QueryParam("questionnaireid") String questionnaireId) throws LpRestException;
	
	/** 
	 * @param questionnaireId
	 *            the id of a questionnaire
	 * @return the list of learner's email subscribed to a questionnaire
	 * @throws LpRestException
	 */
	@GET
	@Path("/{questionnaireid}/list")
	String [] listLearnersFromQuestionnaire(@PathParam("questionnaireid") String questionnaireId) throws LpRestException;
	
	/** 
	 * @param learneremail
	 *            the email of the learner
	 * @return the list of questionnaires a learner has been subscribed 
	 * @throws LpRestException
	 */
	@GET
	@Path("/list")
	String [] listQuestionnaireFromLearner(@QueryParam("learneremail") String learnerEmail) throws LpRestException;
}
