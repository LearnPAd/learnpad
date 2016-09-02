/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.user.send;

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

import java.util.Map;

import eu.learnpad.sim.rest.event.ScoreType;
import eu.learnpad.simulator.uihandler.webserver.msg.user.IUserMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class SessionFinished implements IUserMsg {

	public String sessionid;
	public Map<String, String> tasknames;
	public Map<String, Integer> taskscores;
	public Map<String, Map<ScoreType, Float>> probescores;

	public SessionFinished(String sessionid, Map<String, String> tasknames,
			Map<String, Integer> taskscores, Map<String, Map<ScoreType, Float>> probescores) {
		super();
		this.sessionid = sessionid;
		this.tasknames = tasknames;
		this.taskscores = taskscores;
		this.probescores = probescores;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.uihandler.webserver.msg.user.UserMsg#getType()
	 */
	public TYPE getType() {
		return TYPE.SESSION_FINISHED;
	}

}
