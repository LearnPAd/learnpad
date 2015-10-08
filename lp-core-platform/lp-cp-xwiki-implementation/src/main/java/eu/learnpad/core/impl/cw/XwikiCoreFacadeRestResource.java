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
package eu.learnpad.core.impl.cw;

import java.io.IOException;
import java.util.Collection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.cw.CoreFacade;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.rest.data.UserData;

/*
 * The methods inherited form the CoreFacade in this
 * class should be implemented as a REST invocation
 * toward the CoreFacade binded at the provided URL
 */
public class XwikiCoreFacadeRestResource extends RestResource implements
		CoreFacade {
	
	eu.learnpad.sim.BridgeInterface sim;

	public XwikiCoreFacadeRestResource() {
		this("localhost", 8080);
	}

	public XwikiCoreFacadeRestResource(String coreFacadeHostname,
			int coreFacadeHostPort) {
		// This constructor could change in the future
		this.updateConfiguration(coreFacadeHostname, coreFacadeHostPort);
		this.sim = new eu.learnpad.core.impl.sim.XwikiBridgeInterfaceRestResource();
	}

	public void updateConfiguration(String coreFacadeHostname,
			int coreFacadeHostPort) {
		// This constructor has to be fixed, since it requires changes on the
		// class
		// eu.learnpad.core.rest.RestResource

	}

	@Override
	public void commentNotification(String modelSetId, String commentId,
			String action) throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void resourceNotification(String modelSetId, String resourceId,
			String artifactIds, String action) throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getModel(String modelSetId, String type)
			throws LpRestException {
		// Now send the package's path to the importer for XWiki
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/getmodel/%s",
				RestResource.REST_URI, modelSetId);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type);
		getMethod.setQueryString(queryString);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] model = null;
		try {
			model = IOUtils.toByteArray(getMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public String startSimulation(String modelId, String currentUser,
			Collection<UserData> potentialUsers) throws LpRestException {
		return this.sim.addProcessInstance(modelId, potentialUsers, currentUser);
	}
}
