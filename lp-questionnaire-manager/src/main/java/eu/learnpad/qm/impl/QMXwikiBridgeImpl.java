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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.core.impl.qm.XwikiBridge;
import eu.learnpad.core.impl.qm.XwikiCoreFacadeRestResource;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.qm.component.QuestionnaireManager;

/**
*
* @author gulyx
*/

@Component
@Singleton
@Named("eu.learnpad.qm.impl.QMXwikiBridgeImpl")
@Path("/learnpad/qm/bridge")
public class QMXwikiBridgeImpl  extends XwikiBridge implements Initializable {
	
	// Currently this class has been implemented with the 
	// only idea to support the development of the core platform
	private QuestionnaireManager qm;
	
	@Override
	public void initialize() throws InitializationException {
		this.corefacade = new XwikiCoreFacadeRestResource();
		
		this.qm = QuestionnaireManager.getInstance();		
//		this.qm = QuestionnaireManager.getInstance("put here some file");
			
		this.printSomething("Initialization Completed");
	}

	@Override
	public void importModelSet(String modelSetId, String type,
			byte[] modelContent) throws LpRestExceptionXWikiImpl {
		// TODO This is stub code that have been implemented in order to 
		// make easy the development of the core-platform
		this.qm.storeModelID(modelSetId);
	}

	@Override
	public String createQuestionnaire() throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		
		String questionnareID = "this-is-foo";
		this.printSomething("invoked createQuestionnaire()");

		return questionnareID;
	}

	@Override
	public void addQuestionToQuestionnaire(String creationProcessId,
			String question, String expectedAnswer) throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		this.printSomething("invoked addQuestionToQuestionnaire("+creationProcessId+","+question+","+expectedAnswer+")");
		
	}

	@Override
	public void finalizeQuestionnaire(String creationProcessId, String type)
			throws LpRestExceptionXWikiImpl {
		// TODO Auto-generated method stub
		this.printSomething("invoked finalizeQuestionnaire("+creationProcessId+","+type+")");
		
	}

	@Override
	public String generateQuestionnaires(String modelSetId, String type,
			byte[] configurationFile) throws LpRestExceptionXWikiImpl {
		// TODO This is stub code that have been implemented in order to 
		// make easy the development of the core-platform
		
//	TODO : the REST invocation on this method is not working properly
//	I commented the actual body in order to have a better understanding
		String genProcessID = "thisIsFoo";
//		try {
//			genProcessID = this.qm.startGeneration(modelSetId);
//		} catch (Exception e) {
//			throw new LpRestExceptionXWikiImpl(e);
//		}
		
		return genProcessID;
	}

	@Override
	public String getGenerationStatus(String generationProcessId)
			throws LpRestExceptionXWikiImpl {
		// TODO This is stub code that have been implemented in order to 
		// make easy the development of the core-platform

		String currentStatus = this.qm.getGenerationStatus(generationProcessId).name();
		
		return currentStatus;
	}

	private void printSomething(String data){
		try {
			PrintWriter w = new PrintWriter(new FileWriter("/tmp/"+this.getClass().getName()+".log",true));
			w.println("[TEST MSG] : " + data);
			w.flush();
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
