package eu.learnpad.simulator.datastructures.document;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class LearnPadDocumentField {

	@JsonProperty("id")
	String id;
	@JsonProperty("name")
	String name;
	@JsonProperty("type")
	String type;
	@JsonProperty("desc")
	String desc;
	@JsonProperty("value")
	String value;

	public LearnPadDocumentField(String id, String name, String type,
			String desc, String value) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.desc = desc;
		this.value = value;
	}

}
