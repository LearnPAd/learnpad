package eu.learnpad.simulator.datastructures;

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

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class LearnPadTaskSubmissionResult {

	public static enum TaskSubmissionStatus {
		VALIDATED, REJECTED, ALREADY_COMPLETED, UNKOWN_TASK, UNKOWN_ERROR
	}

	public final TaskSubmissionStatus status;
	public final Integer sessionScore;

	public LearnPadTaskSubmissionResult(TaskSubmissionStatus status,
			Integer sessionScore) {
		super();
		this.status = status;
		this.sessionScore = sessionScore;
	}

	// helper methods to create submission results

	public static LearnPadTaskSubmissionResult validated(Integer sessionScore) {
		return new LearnPadTaskSubmissionResult(TaskSubmissionStatus.VALIDATED,
				sessionScore);
	}

	public static LearnPadTaskSubmissionResult rejected() {
		return new LearnPadTaskSubmissionResult(TaskSubmissionStatus.REJECTED,
				0);
	}

	public static LearnPadTaskSubmissionResult alreadyCompleted() {
		return new LearnPadTaskSubmissionResult(
				TaskSubmissionStatus.ALREADY_COMPLETED, 0);
	}

	public static LearnPadTaskSubmissionResult unknownTask() {
		return new LearnPadTaskSubmissionResult(
				TaskSubmissionStatus.UNKOWN_TASK, 0);
	}

	public static LearnPadTaskSubmissionResult unknownError() {
		return new LearnPadTaskSubmissionResult(
				TaskSubmissionStatus.UNKOWN_ERROR, 0);
	}

}
