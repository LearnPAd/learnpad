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

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Patch", propOrder = { "id", "type", "modelId", "artefact" })
@XmlRootElement(name = "patch")
public class Patch {
	@XmlAttribute(name = "id")
	protected UUID id;

	@XmlAttribute(name = "type", required = true)
	protected PatchType type;

	@XmlAttribute(name = "modelid", required = true)
	protected String modelId;

	@XmlElement(name = "artefact", required = true)
	protected Artefact artefact;

	public Patch() {
		this.id = UUID.randomUUID();
	}

	public Patch(PatchType type, String modelid, Artefact artifact) {
		this.id = UUID.randomUUID();
		this.type = type;
		this.modelId = modelid;
		this.artefact = artifact;
	}
}
