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
package eu.learnpad.rest.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.inject.Named;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;
import org.xwiki.rest.XWikiRestException;

import eu.learnpad.rest.RestCWNotifyModel;

@Component
@Named("eu.learnpad.rest.internal.DefaultRestCWNotifyModel")
public class DefaultRestCWNotifyModel implements XWikiRestComponent,
		RestCWNotifyModel {

	@Override
	public void postNotifyModel(String modelId, String type)
			throws XWikiRestException {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials("Admin",
				"admin");
		httpClient.getState().setCredentials(
				new AuthScope("localhost", 8080, AuthScope.ANY_REALM),
				defaultcreds);

		GetMethod getMethod = new GetMethod(
				"http://localhost:8080/xwiki/rest/learnpad/model/" + modelId
						+ "/" + type);
		getMethod.addRequestHeader("Accept", "application/xml");
		getMethod.addRequestHeader("Accept-Ranges", "bytes");
		try {
			httpClient.executeMethod(getMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream modelStream = getMethod.getResponseBodyAsStream();
			String packagePath = generateXWikiPackage(modelStream, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String generateXWikiPackage(InputStream modelStream, String type) {
		UUID uuid = UUID.randomUUID();
		String stylesheetFileName = "/stylesheet/" + type + "2xwiki.xsl";
		InputStream stylesheetStream = DefaultRestCWNotifyModel.class
				.getResourceAsStream(stylesheetFileName);
		File packageFolder = new File("/tmp/learnpad/" + uuid);
		packageFolder.mkdirs();

		Source modelSource = new StreamSource(modelStream);
		Source stylesheetSource = new StreamSource(stylesheetStream);
		File rdfFile = new File(packageFolder.getPath() + "/ontology.rdf");
		Result result = new StreamResult(rdfFile);

		// create an instance of TransformerFactory
		TransformerFactory transFact = TransformerFactory.newInstance();

		try {
			javax.xml.transform.Transformer trans = transFact
					.newTransformer(stylesheetSource);
			trans.setParameter("packageFolder", packageFolder.getPath());
			trans.transform(modelSource, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * String modelFileName = "model." + type; File modelFile = new
		 * File(packageFolder.getPath() + "/" + modelFileName);
		 * FileUtils.copyInputStreamToFile(modelStream, modelFile);
		 */
		return packageFolder.getPath();
	}
}
