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
package eu.learnpad.or.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

import eu.learnpad.or.rest.data.SimulationData;

/**
*
* @author gulyx
*/
public class OrAPITest {

	@Test
	public void SimulationDataFormatTest() {
		JAXBContext jc;
		try {
			Writer fw = new StringWriter();
			
			jc = JAXBContext.newInstance(SimulationData.class);
			SimulationData sim = new SimulationData();
			sim.getSessionData().put("ses-key1", "ses-entry1");
			sim.getSessionData().put("ses-key2", "ses-entry2");

			sim.getSubmittedData().put("sub-key1", "sub-entry1");
			sim.getSubmittedData().put("sub-key2", "sub-entry2");

			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(sim, fw);
			
			fw.close();
			
			InputStream is = new ByteArrayInputStream(fw.toString().getBytes());
			Unmarshaller unmashaller = jc.createUnmarshaller();
			SimulationData zag = (SimulationData) unmashaller.unmarshal(is);
			
			boolean sesPassed = (zag.getSessionData().get("ses-key1").equals("ses-entry1")) && (zag.getSessionData().get("ses-key2").equals("ses-entry2"));
			boolean subPassed = (zag.getSubmittedData().get("sub-key1").equals("sub-entry1")) && (zag.getSubmittedData().get("sub-key2").equals("sub-entry2"));
			
			Assert.assertTrue(sesPassed && subPassed);
		} catch (JAXBException | IOException e) {
			Assert.fail();
		}
	}

	
}
