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
package eu.learnpad.qm.tests.component;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;

import org.xwiki.component.phase.InitializationException;

import eu.learnpad.qm.component.QuestionnaireGenerationStatus;
import eu.learnpad.qm.component.QuestionnaireManager;
import eu.learnpad.qm.exception.QMModelNotImportedException;
import eu.learnpad.qm.tests.AbstractUnitTest;

/**
 *
 * @author gulyx
 */
public class QMUnitTest extends AbstractUnitTest {
    
	private QuestionnaireManager qm;
	
	@Before
	public void configureEnvironment(){
		try {
			this.qm = QuestionnaireManager.getInstance();
		} catch (InitializationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testImportAndStartGeneration(){
		boolean isCompleted = false;
		int tentative = 0;

		String modelSetID = this.randomId();
		this.qm.storeModelID(modelSetID);
		
		String genProcessID;
		try {
			genProcessID = this.qm.startGeneration(modelSetID);	

			QuestionnaireGenerationStatus status;
			while ((!isCompleted) && (tentative < AbstractUnitTest.MAX_TENTATIVES)){
				tentative++;
				status = this.qm.getGenerationStatus(genProcessID);
				isCompleted = status == QuestionnaireGenerationStatus.Completed;		
			}	
		} catch (QMModelNotImportedException e) {
			AssertionError ae = new AssertionError(e);
			throw ae;
		}		
		
		assertTrue("The Import and Start Generation Test Failed",isCompleted);
	}
	
	@Test
	public void testStartGenerationForUnimportedModel(){
		boolean isProcessing = false;
		int tentative = 0;

		String modelSetID = this.randomId();

		String genProcessID;
		try {
			genProcessID = this.qm.startGeneration(modelSetID);	

			QuestionnaireGenerationStatus status;
			while ((!isProcessing) && (tentative < AbstractUnitTest.MAX_TENTATIVES)){
				tentative++;
				status = this.qm.getGenerationStatus(genProcessID);
				isProcessing = status != QuestionnaireGenerationStatus.NotAvailable;		
			}	
		} catch (QMModelNotImportedException e) {
			assertTrue(true);
		}		
		
		assertFalse("The Generation for an Unimported Model Wrongly Completed",isProcessing);		
		
	}
	

}