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
package eu.learnpad.qm.impl;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.restlet.representation.InputRepresentation;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.core.impl.qm.XwikiBridge;
import eu.learnpad.core.impl.qm.XwikiCoreFacadeRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;

/**
*
* @author gulyx
*/

@Component
@Singleton
@Named("eu.learnpad.qm.impl.QMXwikiBridgeImpl")
@Path("/learnpad/qm/bridge")
public class QMXwikiBridgeImpl  extends XwikiBridge implements Initializable {

	@Override
	public void initialize() throws InitializationException {
		this.corefacade = new XwikiCoreFacadeRestResource();
	}

	@Override
	public void importModelSet(String modelSetId, String type,
			byte[] modelContent) throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String createQuestionnaire() throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addQuestionToQuestionnaire(String creationProcessId,
			String question, String expectedAnswer) throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalizeQuestionnaire(String creationProcessId, String type)
			throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String generateQuestionnaires(String modelSetId, String type,
			byte[] configurationFile) throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGenerationStatus(String generationProcessId)
			throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		return null;
	}

}
