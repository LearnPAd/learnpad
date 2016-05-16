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
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.core.impl.qm.XwikiBridge;
import eu.learnpad.core.impl.qm.XwikiCoreFacadeRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.qm.component.QuestionnaireManager;

/**
*
* @author gulyx
*/

@Component
@Singleton
@Named("eu.learnpad.qm.impl.QMXwikiBridgeImpl")
@Path("/learnpad/qm/bridge")
public class QMXwikiBridgeImpl extends XwikiBridge implements Initializable, QMBridgeNotifier  {
	
	// Currently this class has been implemented with the 
	// only idea to support the development of the core platform
	private QuestionnaireManager qm;
	
	@Override
	public void initialize() throws InitializationException {
		this.printSomething("Initialization Started ... ");

		this.corefacade = new XwikiCoreFacadeRestResource();
		
		this.qm = QuestionnaireManager.getInstance(this);		
//		this.qm = QuestionnaireManager.getInstance("put here some file");
			
		this.printSomething("... Initialization Completed");
	}

	@Override
	public void importModelSet(String modelSetId, ModelSetType type,
			InputStream modelContent) throws LpRestExceptionXWikiImpl {
		// TODO This is stub code that have been implemented in order to 
		// make easy the development of the core-platform
		this.qm.storeModelID(modelSetId);
	}

	@Override
	public String createQuestionnaire() throws LpRestExceptionXWikiImpl {
		// TODO This method is supposed to be used for the interactive
		// set-up of a questionnaire, so it does not concern the
		// integration with MOTHIA
		
		String questionnareID = "this-is-foo";
		this.printSomething("invoked createQuestionnaire()");

		return questionnareID;
	}

	@Override
	public void addQuestionToQuestionnaire(String creationProcessId,
			String question, String expectedAnswer) throws LpRestExceptionXWikiImpl {
		// TODO This method is supposed to be used for the interactive
		// set-up of a questionnaire, so it does not concern the
		// integration with MOTHIA

		this.printSomething("invoked addQuestionToQuestionnaire("+creationProcessId+","+question+","+expectedAnswer+")");		
	}

	@Override
	public void finalizeQuestionnaire(String creationProcessId, String type)
			throws LpRestExceptionXWikiImpl {
		// TODO This method is supposed to be used for the interactive
		// set-up of a questionnaire, so it does not concern the
		// integration with MOTHIA

		this.printSomething("invoked finalizeQuestionnaire("+creationProcessId+","+type+")");		
	}

	@Override
	public String generateQuestionnaires(String modelSetId, String type,
			byte[] configurationFile) throws LpRestExceptionXWikiImpl {
		// TODO This is stub code that have been implemented in order to 
		// make easy the development of the core-platform
		
		
		// TODO configuration files are not supported yet
		String genProcessID = this.generateQuestionnaires(modelSetId, type);			
		
		return genProcessID;
	}

	@Override
	public String generateQuestionnaires(String modelSetId, String type) throws LpRestExceptionXWikiImpl {
		// TODO This is stub code that have been implemented in order to 
		// make easy the development of the core-platform
		
		String genProcessID = "thisIsFoo";		
		try {
			genProcessID = this.qm.startGeneration(modelSetId);
		} catch (Exception e) {
			throw new LpRestExceptionXWikiImpl(e);
		}
		
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

	@Override
	public void generationCompleted(String genProcessID, String genFilePath) {
		byte[] questionnairesFile;
		try {
			questionnairesFile = Files.readAllBytes(Paths.get(genFilePath));
			this.corefacade.generationCompleted(genProcessID);			
			this.corefacade.publish(genProcessID, "mothia-out", questionnairesFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LpRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
