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
package eu.learnpad.qm.tests.learnpad;

import org.junit.Assert;
import org.junit.Test;

import eu.learnpad.core.impl.qm.XwikiCoreFacadeRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.qm.tests.AbstractUnitTest;

public class XwikiCoreFacadeRestResourceTest extends AbstractUnitTest{

	private XwikiCoreFacadeRestResource cf;
	
	@Test
	public void generationCompletedTest(){
		this.cf = new XwikiCoreFacadeRestResource();
		try {
			cf.generationCompleted("this-is-foo");
		} catch (LpRestException e) {
			Assert.assertFalse(e.getMessage(),true);
		}
		Assert.assertTrue(true);
	}
		
	@Test
	public void publishTest(){
		String contents = "this is the foo content of a foo file";
		byte[] questionnairesFileContent = contents.getBytes();
		
		this.cf = new XwikiCoreFacadeRestResource();

		try {
			System.out.println("########################################################");
			System.out.println("########################################################");
			cf.publish("this-is-again-foo", "mothia-out" , questionnairesFileContent);
			System.out.println("********************************************************");
			System.out.println("********************************************************");
		} catch (LpRestException e) {
			Assert.assertFalse(e.getMessage(),true);
		}
		Assert.assertTrue(true);
	}


}
