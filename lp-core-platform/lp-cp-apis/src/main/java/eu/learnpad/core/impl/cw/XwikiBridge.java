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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PutMethod;
import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.cw.Bridge;
import eu.learnpad.cw.rest.data.Feedbacks;
import eu.learnpad.exception.LpRestException;

@Component
@Named("eu.learnpad.core.impl.cw.XwikiBridge")
@Path("/learnpad/cw/bridge")
public class XwikiBridge extends Bridge implements XWikiRestComponent {

	@Inject
	private Logger logger;
	
	private XwikiController cwController;

	public XwikiBridge() {
		this(false);
	}

	public XwikiBridge(boolean isCoreFacadeLocal) {
		cwController = new XwikiController(true);
		if (isCoreFacadeLocal)
			this.corefacade = new XwikiCoreFacade();
		else
			this.corefacade = new XwikiCoreFacadeRestResource();
	}

	@Override
	public byte[] getComments(String modelSetId, String artifactId)
			throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getResource(String modelSetId, String resourceId,
			String linkedTo, String action) throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	private String buildXWikiPackage(String modelSetId, InputStream modelStream, String type) {
		UUID uuid = UUID.randomUUID();
		String stylesheetFileName = "/stylesheet/" + type + "2xwiki.xsl";
		InputStream stylesheetStream = getClass().getClassLoader()
				.getResourceAsStream(stylesheetFileName);
		File packageFolder = new File("/tmp/learnpad/" + uuid);
		packageFolder.mkdirs();

		Source modelSource = new StreamSource(modelStream);
		Source stylesheetSource = new StreamSource(stylesheetStream);
		File rdfFile = new File(packageFolder.getPath() + "/ontology.rdf");
		Result result = new StreamResult(rdfFile);

		// create an instance of TransformerFactory
		TransformerFactory transFact = new net.sf.saxon.TransformerFactoryImpl();

		try {
			Transformer trans = transFact.newTransformer(stylesheetSource);
			trans.setParameter("packageFolder", packageFolder.getPath());
			trans.setParameter("modelSetId", modelSetId);
			trans.transform(modelSource, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return packageFolder.getPath() + "/xwiki";
	}

	@Override
	public void modelSetImported(String modelSetId, String type)
			throws LpRestException {
		// Get the model file from Core Platform
		InputStream modelStream = new ByteArrayInputStream(
				this.cwController.getModel(modelSetId, type));
		
		// Make the XSL transformation and get the package's path
		String packagePath = buildXWikiPackage(modelSetId, modelStream, type);
		
		// Now send the package's path to the importer for XWiki
		HttpClient httpClient = RestResource.getClient();

		String uri = RestResource.REST_URI + "/learnpad/cw/package";
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");
		putMethod.addRequestHeader("Accept-Ranges", "bytes");
		
		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("path", packagePath);
		putMethod.setQueryString(queryString);
		
		try {
			httpClient.executeMethod(putMethod);
		} catch (HttpException e) {
			logger.error(
					"Unable to process the PUT request for XWiki package '"
							+ packagePath
							+ "' onto the Collaborative Workspace.", e);
		} catch (IOException e) {
			logger.error("Unable to PUT XWiki package '" + packagePath
					+ "' to the Collaborative Workspace.", e);
		}
	}

	@Override
	public void contentVerified(String modelSetId, String artifactId,
			String resourceId, String result) throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modelVerified(String modelSetId, String result)
			throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public Feedbacks getFeedbacks(String modelSetId) throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

}
