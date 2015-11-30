package eu.learnpad.simulator.processmanager.gamification;

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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class TaskScorer {

	/**
	 * task score is calculated using the following formula:
	 * <p>
	 * task_score = success * (1/nb_attempts +
	 * expected_response_time/observed_response_time)
	 *
	 * @param success
	 * @param nbAttempts
	 * @param expectedResponseTime
	 * @param observedResponseTime
	 * @return
	 */
	public static double calculateScore(boolean success, int nbAttempts,
			long expectedResponseTime, long observedResponseTime) {

		if (nbAttempts == 0 || !success) {
			return 0;
		} else if (observedResponseTime == 0) {
			throw new RuntimeException(
					"Observed response time of 0 should be impossible!");
		} else {
			return 1 / nbAttempts + expectedResponseTime / observedResponseTime;
		}

	}

	private Date startingTime;
	private long expectedResponseTime;

	private Map<String, Integer> usersFailedAttempts = new HashMap<String, Integer>();

	public TaskScorer(Date startingTime, long expectedResponseTime) {
		super();
		this.startingTime = startingTime;
		this.expectedResponseTime = expectedResponseTime;
	}

	public void addUserAttemptFail(String userId) {
		if (!usersFailedAttempts.containsKey(userId)) {
			usersFailedAttempts.put(userId, 1);
		} else {
			usersFailedAttempts
					.put(userId, usersFailedAttempts.get(userId) + 1);
		}
	}

	public double userComplete(String userId) {
		Date completionTime = new Date();
		long observedResponseTime = completionTime.getTime()
				- startingTime.getTime();

		int nbAttempts = 1;
		if (usersFailedAttempts.containsKey(userId)) {
			nbAttempts += usersFailedAttempts.get(userId);
		}

		return TaskScorer.calculateScore(true, nbAttempts,
				expectedResponseTime, observedResponseTime);

	}

	public int getNbAttemps(String userId) {
		if (usersFailedAttempts.containsKey(userId)) {
			return usersFailedAttempts.get(userId);
		} else {
			return 0;
		}

	}
}
