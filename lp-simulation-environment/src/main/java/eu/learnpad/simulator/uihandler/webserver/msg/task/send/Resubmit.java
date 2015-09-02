/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.task.send;

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

import java.util.Collection;

import eu.learnpad.simulator.datastructures.document.LearnPadDocument;
import eu.learnpad.simulator.uihandler.webserver.msg.task.ITaskMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class Resubmit implements ITaskMsg {

	public String name;
	public String description;
	public String processid;
	public String processname;
	public long startingtime;
	public String form;
	public Collection<LearnPadDocument> documents;

	// for game-related display
	public int nbattempts;
	public long expectedtime;

	public Resubmit(String name, String description, String processid,
			String processname, long startingtime, String form,
			Collection<LearnPadDocument> documents, int nbattempts,
			long expectedtime) {
		super();
		this.name = name;
		this.description = description;
		this.processid = processid;
		this.processname = processname;
		this.startingtime = startingtime;
		this.form = form;
		this.documents = documents;
		this.nbattempts = nbattempts;
		this.expectedtime = expectedtime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.uihandler.webserver.msg.task.ITaskMsg#getType()
	 */
	@Override
	public TYPE getType() {
		return TYPE.RESUBMIT;
	}

}
