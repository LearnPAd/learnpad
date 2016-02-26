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

import javax.xml.bind.JAXBContext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.mt.BridgeInterface;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */
public class XwikiBridgeInterfaceRestResource implements BridgeInterface
{

    private String HOSTNAME = "localhost";

    private int PORT = 8083;

    private String URL;

    public XwikiBridgeInterfaceRestResource()
    {
        this("localhost", 8083);
    }

    public XwikiBridgeInterfaceRestResource(String bridgeInterfaceHostname, int bridgeInterfaceHostPort)
    {
        // This constructor could change in the future
        this.updateConfiguration(bridgeInterfaceHostname, bridgeInterfaceHostPort);
    }

    public void updateConfiguration(String bridgeInterfaceHostname, int bridgeInterfaceHostPort)
    {
        this.HOSTNAME = bridgeInterfaceHostname;
        this.PORT = bridgeInterfaceHostPort;
        this.URL = "http://" + this.HOSTNAME + ":" + this.PORT + "/rest";
    }

    @Override
    public InputStream transform(String type, InputStream model) throws LpRestException
    {
        HttpClient httpClient = RestResource.getAnonymousClient();
        String uri = String.format("%s/learnpad/mt/bridge/transform", RestResource.MT_REST_URI);
        PostMethod postMethod = new PostMethod(uri);
        postMethod.addRequestHeader("Content-Type", "application/octet-stream");
        postMethod.addRequestHeader("Accept", "application/octet-stream");
        NameValuePair[] queryString = new NameValuePair[1];
        queryString[0] = new NameValuePair("type", type);
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
            String message =
                String.format("Model Transformer component failed to return result of transformation [type: %s]", type);
            throw new LpRestExceptionXWikiImpl(message, e);
        }
    }
}
