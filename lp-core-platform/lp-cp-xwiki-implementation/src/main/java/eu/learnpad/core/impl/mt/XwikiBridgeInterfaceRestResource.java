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
package eu.learnpad.core.impl.mt;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.http.HttpHeaders;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.configuration.LearnpadPropertiesConfigurationSource;
import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.mt.BridgeInterface;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */
@Component
@Named("mt")
public class XwikiBridgeInterfaceRestResource extends DefaultRestResource implements BridgeInterface, Initializable {
	@Override
	public void initialize() throws InitializationException {
		this.restPrefix = ((LearnpadPropertiesConfigurationSource) this.configurationSource).getRestPrefix("MT");
	}

	@Override
	public InputStream transform(ModelSetType type, InputStream model) throws LpRestException {
		HttpClient httpClient = this.getAnonymousClient();
		String uri = String.format("%s/learnpad/mt/bridge/transform", this.restPrefix);
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM);
		postMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_OCTET_STREAM);
		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type.toString());
		postMethod.setQueryString(queryString);

		RequestEntity modelEntity = new InputStreamRequestEntity(model);
		postMethod.setRequestEntity(modelEntity);
		try {
			httpClient.executeMethod(postMethod);
		} catch (IOException e) {
			String message = String.format("Error in sending POST to Model Transformer component [type: %s]", type);
			throw new LpRestExceptionXWikiImpl(message, e);
		}
		try {
			return postMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			String message = String
					.format("Model Transformer component failed to return result of transformation [type: %s]", type);
			throw new LpRestExceptionXWikiImpl(message, e);
		}
	}
}