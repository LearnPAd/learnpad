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
package eu.learnpad.cw.rest.internal;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;
import org.xwiki.rest.XWikiRestException;

import eu.learnpad.cw.rest.XWikiPackage;
import eu.learnpad.rest.utils.RestResource;

@Component
@Named("eu.learnpad.cw.rest.internal.DefaultXWikiPackage")
public class DefaultXWikiPackage extends RestResource implements XWikiPackage,
		XWikiRestComponent {

	@Inject
	Logger logger;

	private void putPage(File indexFile, String spaceName, String pageName)
			throws XWikiRestException, JAXBException {
		HttpClient httpClient = getClient();

		String uri = REST_URI + "/wikis/xwiki/spaces/" + spaceName + "/pages/"
				+ pageName;
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");
		putMethod.addRequestHeader("Accept-Ranges", "bytes");
		RequestEntity fileRequestEntity = new FileRequestEntity(indexFile,
				"application/xml");
		putMethod.setRequestEntity(fileRequestEntity);
		try {
			httpClient.executeMethod(putMethod);
		} catch (HttpException e) {
			logger.error("Unable to process the PUT request on page '"
					+ spaceName + "." + pageName + "'.", e);
			return;
		} catch (IOException e) {
			logger.error("Unable to PUT the page '" + spaceName + "."
					+ pageName + "'.", e);
			return;
		}
	}

	private void postObject(File objectFile, String spaceName, String pageName,
			String className) throws XWikiRestException {
		HttpClient httpClient = getClient();

		String uri = REST_URI + "/wikis/xwiki/spaces/" + spaceName + "/pages/"
				+ pageName + "/objects";
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Accept", "application/xml");
		postMethod.addRequestHeader("Accept-Ranges", "bytes");
		RequestEntity fileRequestEntity = new FileRequestEntity(objectFile,
				"application/xml");
		postMethod.setRequestEntity(fileRequestEntity);
		try {
			httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			logger.error("Unable to process the POST request on object '"
					+ spaceName + "." + pageName + "." + className + "'.", e);
			return;
		} catch (IOException e) {
			logger.error("Unable to POST the object '" + spaceName + "."
					+ pageName + "." + className + "'.", e);
			return;
		}
	}

	private void importObjects(File objectsFolder, String spaceName,
			String pageName) throws XWikiRestException {
		for (File objectSpaceFolder : objectsFolder.listFiles()) {
			if (objectSpaceFolder.isDirectory()) {
				for (File objectClassFolder : objectSpaceFolder.listFiles()) {
					if (objectClassFolder.isDirectory()) {
						for (File objectFile : objectClassFolder.listFiles()) {
							if (objectFile.isFile()) {
								String className = objectSpaceFolder.getName()
										+ "." + objectClassFolder.getName();
								postObject(objectFile, spaceName, pageName,
										className);
							}
						}
					}
				}
			}
		}
	}

	private void importPage(File pageFolder, String spaceName)
			throws XWikiRestException {
		String pageName = pageFolder.getName();
		if (!pageFolder.isDirectory()) {
			throw new XWikiRestException();
		}
		try {
			File indexFile = new File(pageFolder.getPath() + "/index.xml");
			if (indexFile.isFile()) {
				putPage(indexFile, spaceName, pageName);
			}
			File objectsFolder = new File(pageFolder.getPath() + "/objects");
			if (objectsFolder.isDirectory()) {
				importObjects(objectsFolder, spaceName, pageName);
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void importSpace(File spaceFolder) throws XWikiRestException {
		if (spaceFolder.isDirectory()) {
			for (File pageFolder : spaceFolder.listFiles()) {
				importPage(pageFolder, spaceFolder.getName());
			}
		}
	}

	public void putXWikiPackage(String path) throws XWikiRestException {
		File packageFolder = new File(path);
		if (packageFolder.isDirectory()) {
			for (File spaceFolder : packageFolder.listFiles()) {
				importSpace(spaceFolder);
			}
		}
	}
}