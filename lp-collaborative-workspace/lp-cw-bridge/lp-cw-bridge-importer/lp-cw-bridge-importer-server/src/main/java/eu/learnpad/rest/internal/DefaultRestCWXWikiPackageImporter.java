package eu.learnpad.rest.internal;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;
import org.xwiki.rest.XWikiRestException;

import eu.learnpad.rest.RestCWXWikiPackageImporter;

@Component
@Named("eu.learnpad.rest.internal.DefaultRestCWXWikiPackageImporter")
public class DefaultRestCWXWikiPackageImporter implements RestCWXWikiPackageImporter, XWikiRestComponent {
	@Inject
	@Named("org.xwiki.rest.internal.resources.pages.PageResourceImpl")
	private XWikiRestComponent pageResource;

	private void putPage(File indexFile, String spaceName, String pageName)
			throws XWikiRestException, JAXBException {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials("Admin",
				"admin");
		httpClient.getState().setCredentials(
				new AuthScope("localhost", 8080, AuthScope.ANY_REALM),
				defaultcreds);

		PutMethod putMethod = new PutMethod(
				"http://localhost:8080/xwiki/rest/wikis/xwiki/spaces/"
						+ spaceName + "/pages/" + pageName);
		putMethod.addRequestHeader("Accept", "application/xml");
		putMethod.addRequestHeader("Accept-Ranges", "bytes");
		RequestEntity fileRequestEntity = new FileRequestEntity(indexFile,
				"application/xml");
		putMethod.setRequestEntity(fileRequestEntity);
		try {
			httpClient.executeMethod(putMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void postObject(File objectFile, String spaceName, String pageName,
			String className) throws XWikiRestException {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials("Admin",
				"admin");
		httpClient.getState().setCredentials(
				new AuthScope("localhost", 8080, AuthScope.ANY_REALM),
				defaultcreds);

		PostMethod postMethod = new PostMethod(
				"http://localhost:8080/xwiki/rest/wikis/xwiki/spaces/"
						+ spaceName + "/pages/" + pageName + "/objects");
		postMethod.addRequestHeader("Accept", "application/xml");
		postMethod.addRequestHeader("Accept-Ranges", "bytes");
		RequestEntity fileRequestEntity = new FileRequestEntity(objectFile,
				"application/xml");
		postMethod.setRequestEntity(fileRequestEntity);
		try {
			httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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