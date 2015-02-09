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
package eu.learnpad.cw.rest.internal.representations;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.cw.rest.internal.utils.LearnPadMediaType;

@Component("eu.learnpad.cw.rest.internal.representations.BusinessProcessReader")
@Provider
@Consumes(LearnPadMediaType.APPLICATION_VND_LEARNPAD_BPMN)
@Singleton
public class BusinessProcessReader implements MessageBodyReader<DocumentRoot>,
		XWikiRestComponent {

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return DocumentRoot.class.isAssignableFrom(type);
	}

	@Override
	public DocumentRoot readFrom(Class<DocumentRoot> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		Bpmn2ResourceFactoryImpl resourceFactory = new Bpmn2ResourceFactoryImpl();
		Resource resource = resourceFactory.createResource(URI.createURI(httpHeaders.getFirst("host")));
		resource.load(entityStream, Collections.EMPTY_MAP);
		
		DocumentRoot dr = (DocumentRoot) resource.getContents().get(0);
		return dr;
	}

}
