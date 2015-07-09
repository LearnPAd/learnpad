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

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import javax.inject.Named;
import javax.ws.rs.Path;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xwiki.component.annotation.Component;

import eu.learnpad.cw.Bridge;
import eu.learnpad.exception.LpRestException;

@Component
@Named("eu.learnpad.core.impl.cw.XwikiBridge")
@Path("/learnpad/cw")
public class XwikiBridge extends Bridge{

	public XwikiBridge (){
		this(false);
	}

	public XwikiBridge (boolean isCoreFacadeLocal){
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
	private String buildXWikiPackage(InputStream modelStream, String type) {
		UUID uuid = UUID.randomUUID();
		String stylesheetFileName = "/stylesheet/" + type + "2xwiki.xsl";
		InputStream stylesheetStream = XwikiBridge.class
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
			trans.transform(modelSource, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return packageFolder.getPath() + "/xwiki";
	}

	@Override
	public void modelSetImported(String modelSetId, String type) throws LpRestException {
		this.corefacade.getModel(modelSetId, type);		
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

}
