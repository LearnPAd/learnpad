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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Feedback", propOrder = { "modelSetId", "modelId",
		"artifactId", "content" })
@XmlRootElement(name = "feedback")
public class Feedback {
	// TODO: class to store verification results; to define

	public String getModelSetId() {
		return this.modelSetId;
	}

	public String getModelId() {
		return this.modelId;
	}

	public String getArtifactId() {
		return this.artifactId;
	}

	public String getContent() {
		return this.content;
	}

	@XmlAttribute(name = "modelsetid", required = true)
	protected String modelSetId;

	@XmlAttribute(name = "modelid")
	protected String modelId;

	@XmlAttribute(name = "artifactid")
	protected String artifactId;

	@XmlValue
	protected String content;

	public Feedback() {
	}

	public Feedback(String modelSetId, String modelId, String artifactId,
			String content) {
		this.modelSetId = modelSetId;
		this.modelId = modelId;
		this.artifactId = artifactId;
		this.content = content;
	}
}
