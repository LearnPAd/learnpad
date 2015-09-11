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
package eu.learnpad.core.impl.me;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.XWikiRestUtils;
import eu.learnpad.cw.rest.data.Feedbacks;
import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.me.Controller;
import eu.learnpad.mv.rest.data.MVResults;

@Component
@Named("eu.learnpad.core.impl.me.XwikiController")
@Path("/learnpad/me")
public class XwikiController extends Controller implements XWikiRestComponent {

	private Map<String, MVResults> mvResults;

	@Inject
	Logger logger;

	public XwikiController() {
		this(false);
	}

	public XwikiController(boolean isBridgeInterfaceLocal) {
		if (isBridgeInterfaceLocal)
			this.bridge = new XwikiBridgeInterface();
		else
			this.bridge = new XwikiBridgeInterfaceRestResource();

		this.mvResults = new HashMap<String, MVResults>();
	}

	@Override
	public void putModelSet(String modelSetId, String type, byte[] modelSetFile)
			throws LpRestExceptionImpl {
		if (XWikiRestUtils.isPage(RestResource.CORE_REPOSITORY_WIKI,
				RestResource.CORE_REPOSITORY_SPACE, modelSetId) == false) {
			XWikiRestUtils.createEmptyPage(RestResource.CORE_REPOSITORY_WIKI,
					RestResource.CORE_REPOSITORY_SPACE, modelSetId);
		}
		String attachmentName = String.format("%s.%s", modelSetId, type);
		XWikiRestUtils.putAttachment(RestResource.CORE_REPOSITORY_WIKI,
				RestResource.CORE_REPOSITORY_SPACE, modelSetId, attachmentName,
				modelSetFile);
	}

	@Override
	public String startModelSetVerification(String modelSetId, String type,
			String verification) throws LpRestExceptionImpl {
		String verificationProcessId = UUID.randomUUID().toString();
		mvResults.put(verificationProcessId, new MVResults(modelSetId, type));
		return verificationProcessId;
	}

	@Override
	public MVResults checkModelSetVerification(String verificationProcessId)
			throws LpRestExceptionImpl {
		MVResults result = mvResults.get(verificationProcessId);
		if (result != null) {
			MVResults toReturn = new MVResults(result);
			if (result.getStatus().equals("inprogress")) {
				mvResults.get(verificationProcessId).terminate();
			} else {
				mvResults.remove(verificationProcessId);
			}
			if (toReturn.getStatus().equals("finished")) {
				// Notify CW about a new model set imported
				HttpClient httpClient = RestResource.getClient();
				String uri = String.format(
						"%s/learnpad/cw/bridge/modelsetimported/%s",
						RestResource.REST_URI, toReturn.getModelSetId());
				PutMethod putMethod = new PutMethod(uri);
				putMethod.addRequestHeader("Accept", "application/xml");
				NameValuePair[] queryString = new NameValuePair[1];
				queryString[0] = new NameValuePair("type", toReturn.getType());
				putMethod.setQueryString(queryString);
				try {
					httpClient.executeMethod(putMethod);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return toReturn;
		} else {
			return null;
		}
	}

	@Override
	public Feedbacks getFeedbacks(String modelSetId) throws LpRestExceptionImpl {
		// Now send the package's path to the importer for XWiki
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/cw/bridge/%s/feedbacks",
				RestResource.REST_URI, modelSetId);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream feedbacksStream = null;
		try {
			feedbacksStream = getMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Feedbacks feedbacks = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(Feedbacks.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			feedbacks = (Feedbacks) unmarshaller.unmarshal(feedbacksStream);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedbacks;
	}
}
