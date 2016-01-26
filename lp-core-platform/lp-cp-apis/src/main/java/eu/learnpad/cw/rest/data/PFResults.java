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
package eu.learnpad.cw.rest.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PFResults", propOrder = { "patches", "feedbacks" })
@XmlRootElement(name = "PFResults")
public class PFResults {
	@XmlElement(name = "patches", required = false)
	protected Patches patches;
	
	@XmlElement(name = "feedbacks", required = false)
	protected Feedbacks feedbacks;

	public PFResults() {
		this.patches = new Patches();
		this.feedbacks = new Feedbacks();
	}

	public void addPatch(Patch patch) {
		this.patches.add(patch);
	}

	public void addFeedback(Feedback feedback) {
		this.feedbacks.add(feedback);
	}
}
