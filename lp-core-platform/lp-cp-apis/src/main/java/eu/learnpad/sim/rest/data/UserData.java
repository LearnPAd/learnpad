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
package eu.learnpad.sim.rest.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class UserData {

	public String id;
	public String firstName;
	public String lastName;
	public String bio;
	public String pictureURL;
	public String profileURL;

	public UserData() {
	}

	@JsonCreator
	public UserData(@JsonProperty("id") String id,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("bio") String bio,
			@JsonProperty("pictureURL") String pictureURL,
			@JsonProperty("profileURL") String profileURL) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
		this.pictureURL = pictureURL;
	}

}
