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
package eu.learnpad.core.impl.qm;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Named;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.qm.BridgeInterface;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */
@Component
@Named("qm")
public class XwikiBridgeInterfaceRestResource extends DefaultRestResource
		implements BridgeInterface, Initializable {

	@Override
	public void initialize() throws InitializationException {
		// TODO this initialization has to be fixed similarly to the implementation for the other components. see there.
		this.restPrefix = "";
	}

	@Override
	public void importModelSet(String modelSetId, ModelSetType type,
			InputStream modelContent) throws LpRestExceptionXWikiImpl {
		// Notify QM about a new model set imported
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/qm/bridge/importmodel/%s",
				DefaultRestResource.REST_URI, modelSetId);
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type.toString());
		putMethod.setQueryString(queryString);

		RequestEntity requestEntity = new InputStreamRequestEntity(modelContent);
		putMethod.setRequestEntity(requestEntity);

		try {
			httpClient.executeMethod(putMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

	}

	private String localGenerateQuestionnaires(String modelSetId, String type,
			byte[] configurationFile) throws LpRestExceptionXWikiImpl {
		// Ask the QM to generate new questionnaire for a given model set
		// that has been already imported
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/qm/bridge/generate/%s",
				DefaultRestResource.REST_URI, modelSetId);
		PostMethod postMethod = new PostMethod(uri);

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type);
		postMethod.setQueryString(queryString);

		RequestEntity requestEntity = null;
		if (configurationFile != null) {
			postMethod.addRequestHeader("Content-Type",
					"application/octet-stream");
			requestEntity = new ByteArrayRequestEntity(configurationFile);
		}
		postMethod.setRequestEntity(requestEntity);

		String genProcessID = null;
		try {
			httpClient.executeMethod(postMethod);
			genProcessID = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
		return genProcessID;
	}

	@Override
	public String generateQuestionnaires(String modelSetId, String type,
			byte[] configurationFile) throws LpRestExceptionXWikiImpl {
		String genProcessID = this.localGenerateQuestionnaires(modelSetId,
				type, configurationFile);
		return genProcessID;
	}

	@Override
	public String generateQuestionnaires(String modelSetId, String type)
			throws LpRestExceptionXWikiImpl {
		String genProcessID = this.localGenerateQuestionnaires(modelSetId,
				type, null);
		return genProcessID;
	}

	@Override
	public String getGenerationStatus(String generationProcessId)
			throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createQuestionnaire() throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addQuestionToQuestionnaire(String creationProcessId,
			String question, String expectedAnswer)
			throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
	}

	@Override
	public void finalizeQuestionnaire(String creationProcessId, String type)
			throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
	}
}