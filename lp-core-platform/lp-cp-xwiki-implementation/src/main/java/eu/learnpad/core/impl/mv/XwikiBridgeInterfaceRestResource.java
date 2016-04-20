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
package eu.learnpad.core.impl.mv;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Named;
import javax.xml.bind.JAXBContext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.configuration.LearnpadPropertiesConfigurationSource;
import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.mv.BridgeInterface;
import eu.learnpad.mv.rest.data.VerificationId;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.rest.data.VerificationStatus;
import eu.learnpad.mv.rest.data.VerificationsAvailable;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */
@Component
@Named("mv")
public class XwikiBridgeInterfaceRestResource extends DefaultRestResource implements BridgeInterface, Initializable {

	@Override
	public void initialize() throws InitializationException {
		this.restPrefix = ((LearnpadPropertiesConfigurationSource) this.configurationSource).getRestPrefix("MV");
	}

	@Override
	public VerificationsAvailable getAvailableVerifications() throws LpRestException {
		HttpClient httpClient = this.getHttpClient();
		String uri = String.format("%s/learnpad/mv/bridge/getavailableverifications", this.restPrefix);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		VerificationsAvailable verificationsAvailable = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(VerificationsAvailable.class);
			InputStream retIs = getMethod.getResponseBodyAsStream();
			verificationsAvailable = (VerificationsAvailable) jaxbContext.createUnmarshaller().unmarshal(retIs);
		} catch (Exception e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		return verificationsAvailable;
	}

	@Override
	public VerificationId startVerification(String modelSetId, String verificationType) throws LpRestException {
		HttpClient httpClient = this.getHttpClient();
		String uri = String.format("%s/learnpad/mv/bridge/startverification", this.restPrefix);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[2];
		queryString[0] = new NameValuePair("modelsetid", modelSetId);
		queryString[1] = new NameValuePair("verificationtype", verificationType);

		getMethod.setQueryString(queryString);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		VerificationId verificationId = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(VerificationId.class);
			InputStream retIs = getMethod.getResponseBodyAsStream();
			verificationId = (VerificationId) jaxbContext.createUnmarshaller().unmarshal(retIs);
		} catch (Exception e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		return verificationId;
	}

	@Override
	public VerificationStatus getVerificationStatus(String verificationProcessId) throws LpRestException {
		HttpClient httpClient = this.getHttpClient();
		String uri = String.format("%s/learnpad/mv/bridge/getverificationstatus", this.restPrefix);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("verificationprocessid", verificationProcessId);
		getMethod.setQueryString(queryString);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		VerificationStatus verificationStatus = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(VerificationStatus.class);
			InputStream retIs = getMethod.getResponseBodyAsStream();
			verificationStatus = (VerificationStatus) jaxbContext.createUnmarshaller().unmarshal(retIs);
		} catch (Exception e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		return verificationStatus;
	}

	@Override
	public VerificationResults getVerificationResult(String verificationProcessId) throws LpRestException {

		HttpClient httpClient = this.getHttpClient();
		String uri = String.format("%s/learnpad/mv/bridge/getverificationresult", this.restPrefix);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("verificationprocessid", verificationProcessId);
		getMethod.setQueryString(queryString);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
		VerificationResults verificationResults = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(VerificationResults.class);
			InputStream retIs = getMethod.getResponseBodyAsStream();
			verificationResults = (VerificationResults) jaxbContext.createUnmarshaller().unmarshal(retIs);
		} catch (Exception e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		return verificationResults;
	}

	private HttpClient getHttpClient() {
		return new HttpClient();
	}
}