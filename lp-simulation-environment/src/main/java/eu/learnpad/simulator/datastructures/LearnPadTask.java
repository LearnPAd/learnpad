package eu.learnpad.simulator.datastructures;

import java.util.Collection;

import eu.learnpad.simulator.datastructures.document.LearnPadDocument;

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
 * This data structure holds the common information about a task
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class LearnPadTask {

	public final String processId;
	public final String id;
	public final String name;
	public final String desc;
	public final Collection<LearnPadDocument> documents;
	public final long startingTime;

	public LearnPadTask(String processId, String taskId, String taskName,
			String taskDesc, Collection<LearnPadDocument> documents,
			long startingTime) {
		super();
		this.processId = processId;
		this.id = taskId;
		this.name = taskName;
		this.desc = taskDesc;
		this.documents = documents;
		this.startingTime = startingTime;
	}

	@Override
	public String toString() {
		return "LPTask: {" + processId + ", " + id + ", " + name + "}";
	}
}
