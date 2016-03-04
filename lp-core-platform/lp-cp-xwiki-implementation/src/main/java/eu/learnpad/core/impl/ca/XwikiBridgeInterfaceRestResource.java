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

import eu.learnpad.ca.BridgeInterface;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalyses;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalyses;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */

public class XwikiBridgeInterfaceRestResource extends RestResource implements BridgeInterface
{

    public XwikiBridgeInterfaceRestResource()
    {
        this("localhost", 8080);
    }

    public XwikiBridgeInterfaceRestResource(String coreFacadeHostname, int coreFacadeHostPort)
    {
        // This constructor could change in the future
        this.updateConfiguration(coreFacadeHostname, coreFacadeHostPort);
    }

    public void updateConfiguration(String coreFacadeHostname, int coreFacadeHostPort)
    {
        // This constructor has to be fixed, since it requires changes on the class
        // eu.learnpad.core.rest.RestResource

    }

    @Override
    public String putValidateCollaborativeContent(CollaborativeContentAnalysis contentFile) throws LpRestException
    {
        HttpClient httpClient = RestResource.getAnonymousClient();
        String uri = String.format("%s/learnpad/ca/bridge/validatecollaborativecontent", RestResource.CA_REST_URI);
        PostMethod postMethod = new PostMethod(uri);

        postMethod.addRequestHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        try {
            StringWriter contentWriter = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(CollaborativeContentAnalysis.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(contentFile, contentWriter);
            
            RequestEntity entity = new StringRequestEntity(contentWriter.toString(), MediaType.APPLICATION_XML, null);
            postMethod.setRequestEntity(entity);
        } catch (JAXBException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            httpClient.executeMethod(postMethod);
            return postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AnnotatedCollaborativeContentAnalyses getCollaborativeContentVerifications(String contentID)
        throws LpRestException
    {
        HttpClient httpClient = RestResource.getAnonymousClient();
        String uri = String.format("%s/learnpad/ca/bridge/validatecollaborativecontent/%s", RestResource.CA_REST_URI, contentID);
        GetMethod getMethod = new GetMethod(uri);
        
        try {
            httpClient.executeMethod(getMethod);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        AnnotatedCollaborativeContentAnalyses analysis = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AnnotatedCollaborativeContentAnalyses.class);
            InputStream retIs = getMethod.getResponseBodyAsStream();
            analysis = (AnnotatedCollaborativeContentAnalyses)jaxbContext.createUnmarshaller().unmarshal(retIs);
            return analysis;
        } catch (Exception e) {
            throw new LpRestExceptionXWikiImpl(e.getMessage(),e);
        }
    }

    @Override
    public String getStatusCollaborativeContentVerifications(String contentID) throws LpRestException
    {

        HttpClient httpClient = RestResource.getAnonymousClient();
        String uri = String.format("%s/learnpad/ca/bridge/validatecollaborativecontent/%s/status", RestResource.CA_REST_URI, contentID);
        GetMethod getMethod = new GetMethod(uri);
        
        try {
            httpClient.executeMethod(getMethod);
            return getMethod.getResponseBodyAsString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String putValidateStaticContent(StaticContentAnalysis contentFile) throws LpRestException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AnnotatedStaticContentAnalyses getStaticContentVerifications(String contentID)
        throws LpRestException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getStatusStaticContentVerifications(String contentID) throws LpRestException
    {
        // TODO Auto-generated method stub
        return null;
    }

}
