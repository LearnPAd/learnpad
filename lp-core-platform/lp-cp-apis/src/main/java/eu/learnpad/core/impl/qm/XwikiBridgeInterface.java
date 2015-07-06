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
package eu.learnpad.core.impl.qm;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.qm.BridgeInterface;

@Component
public class XwikiBridgeInterface implements XWikiRestComponent, BridgeInterface{

	@Override
	public void importModelSet(String modelSetId, String type,
			byte[] modelContent) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String generateQuestionnaires(String modelSetId, String type,
			byte[] configurationFile) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGenerationStatus(String generationProcessId)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createQuestionnaire() throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addQuestionToQuestionnaire(String creationProcessId,
			String question, String expectedAnswer) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalizeQuestionnaire(String creationProcessId, String type)
			throws LpRestException {
		// TODO Auto-generated method stub
		
	}

}
