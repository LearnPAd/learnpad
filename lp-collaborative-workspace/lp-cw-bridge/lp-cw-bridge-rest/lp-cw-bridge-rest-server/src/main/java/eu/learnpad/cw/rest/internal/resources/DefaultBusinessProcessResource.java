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
package eu.learnpad.cw.rest.internal.resources;

import org.eclipse.bpmn2.DocumentRoot;
import org.xwiki.component.annotation.Component;

import eu.learnpad.cw.rest.CollaborativeWorkspaceRestException;
import eu.learnpad.cw.rest.resources.BusinessProcessResource;

@Component("eu.learnpad.cw.rest.internal.resources.DefaultBusinessProcessResource")
public class DefaultBusinessProcessResource implements BusinessProcessResource {

	@Override
	public DocumentRoot getBusinessProcess(String bpId)
			throws CollaborativeWorkspaceRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putBusinessProcess(String bpId, DocumentRoot documentRoot)
			throws CollaborativeWorkspaceRestException {
		System.out.println("[INFO] putBusinessProcess");
	}

	@Override
	public void deleteBusinessProcess(String bpId)
			throws CollaborativeWorkspaceRestException {
		// TODO Auto-generated method stub
	}
}
