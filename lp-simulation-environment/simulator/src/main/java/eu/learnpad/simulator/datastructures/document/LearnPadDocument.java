package eu.learnpad.simulator.datastructures.document;

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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This data structure represents a document used in a process. Among others, a
 * document contains several document fields. These fields are used to generate
 * forms to be filled by the users.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class LearnPadDocument {

	@JsonProperty("id")
	public final String id;
	@JsonProperty("name")
	public final String name;
	@JsonProperty("desc")
	public final String desc;
	@JsonProperty("fields")
	public final Collection<LearnPadDocumentField> fields;

	public LearnPadDocument(String id, String name, String desc,
			Collection<LearnPadDocumentField> fields) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.fields = fields;
	}

}
