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
package eu.learnpad.core.impl.ca;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpHeaders;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.ca.BridgeInterface;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalyses;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalyses;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.configuration.LearnpadPropertiesConfigurationSource;
import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */
@Component
@Named("ca")
public class XwikiBridgeInterfaceRestResource extends DefaultRestResource
		implements BridgeInterface, Initializable {

	@Override
	public void initialize() throws InitializationException {
		this.restPrefix = ((LearnpadPropertiesConfigurationSource) this.configurationSource)
				.getRestPrefix("CA");
	}

	@Override
	public String putValidateCollaborativeContent(
			CollaborativeContentAnalysis contentFile) throws LpRestException {
		HttpClient httpClient = this.getAnonymousClient();
		String uri = String.format(
				"%s/learnpad/ca/bridge/validatecollaborativecontent",
				this.restPrefix);
		PostMethod postMethod = new PostMethod(uri);

		postMethod.addRequestHeader(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_XML);
		try {
			StringWriter contentWriter = new StringWriter();
			JAXBContext context = JAXBContext
					.newInstance(CollaborativeContentAnalysis.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(contentFile, contentWriter);

			RequestEntity entity = new StringRequestEntity(
					contentWriter.toString(), MediaType.APPLICATION_XML, null);
			postMethod.setRequestEntity(entity);
		} catch (JAXBException | UnsupportedEncodingException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}

		try {
			httpClient.executeMethod(postMethod);
			return postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

	@Override
	public AnnotatedCollaborativeContentAnalyses getCollaborativeContentVerifications(
			String contentID) throws LpRestException {
		HttpClient httpClient = this.getAnonymousClient();
		String uri = String.format(
				"%s/learnpad/ca/bridge/validatecollaborativecontent/%s",
				this.restPrefix, contentID);
		GetMethod getMethod = new GetMethod(uri);

		try {
			httpClient.executeMethod(getMethod);

			AnnotatedCollaborativeContentAnalyses analysis = null;

			JAXBContext jaxbContext = JAXBContext
					.newInstance(AnnotatedCollaborativeContentAnalyses.class);
			InputStream retIs = getMethod.getResponseBodyAsStream();
			analysis = (AnnotatedCollaborativeContentAnalyses) jaxbContext
					.createUnmarshaller().unmarshal(retIs);
			return analysis;
		} catch (Exception e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public String getStatusCollaborativeContentVerifications(String contentID)
			throws LpRestException {

		HttpClient httpClient = this.getAnonymousClient();
		String uri = String.format(
				"%s/learnpad/ca/bridge/validatecollaborativecontent/%s/status",
				this.restPrefix, contentID);
		GetMethod getMethod = new GetMethod(uri);

		try {
			httpClient.executeMethod(getMethod);
			return getMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

	@Override
	public String getCollaborativeContentVerificationsView(String contentID)
			throws LpRestException {

		HttpClient httpClient = this.getAnonymousClient();
		String uri = String.format(
				"%s/learnpad/ca/bridge/validatecollaborativecontent/%s/view",
				this.restPrefix, contentID);
		GetMethod getMethod = new GetMethod(uri);

		try {
			httpClient.executeMethod(getMethod);
			return getMethod.getResponseBodyAsString(); 
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

	@Override
	public String putValidateStaticContent(StaticContentAnalysis contentFile)
			throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnnotatedStaticContentAnalyses getStaticContentVerifications(
			String contentID) throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStatusStaticContentVerifications(String contentID)
			throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStaticContentVerificationsView(String contentID)
			throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

}