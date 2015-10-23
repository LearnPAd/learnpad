/**
 *
 */
package eu.learnpad.simulator.robot.activiti.markovrobot;

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
import java.util.Map.Entry;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Simple data structure to come around the fact that java still does not have
 * tuples
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class MarkovData {

	final String taskId;
	final Map<String, Object> properties;

	public MarkovData(String taskId, Map<String, Object> inputData) {
		super();
		this.taskId = taskId;
		this.properties = inputData;
	}

	@Override
	public String toString() {

		String textProperties = "{";
		for (Entry<String, Object> e : properties.entrySet()) {
			textProperties = textProperties + e.getKey() + " -> "
					+ e.getValue() + ";";
		}
		textProperties = textProperties + "}";

		return "(" + taskId + "," + properties + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MarkovData))
			return false;
		if (obj == this)
			return true;

		MarkovData rhs = (MarkovData) obj;
		return new EqualsBuilder().append(taskId, rhs.taskId)
				.append(properties, rhs.properties).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(taskId).append(properties)
				.toHashCode();
	}

}
