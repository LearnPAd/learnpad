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
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.security.SecureRandom;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.junit.Assert;
import org.junit.Test;

import eu.learnpad.or.rest.data.kbprocessing.KBProcessId;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatus;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatus.Info;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatusType;

/**
 * 
 * @author gulyx
 */
public class KBProcessingOrAPITest {

	private SecureRandom random;

	public KBProcessingOrAPITest() {
		this.random = new SecureRandom();
	}

	@Test
	public void KBProcessIdFormatTest() {
		JAXBContext jc;
		try {
			Writer fw = new StringWriter();

			jc = JAXBContext.newInstance(KBProcessId.class);
			KBProcessId id = new KBProcessId();
			id.setId(String.valueOf(this.random.nextInt()));

			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(id, fw);

			fw.close();

			InputStream is = new ByteArrayInputStream(fw.toString().getBytes());
			Unmarshaller unmashaller = jc.createUnmarshaller();
			KBProcessId zag = (KBProcessId) unmashaller.unmarshal(is);

			boolean passed = id.getId().equals(zag.getId());

			Assert.assertTrue(passed);
		} catch (JAXBException | IOException e) {
			Assert.fail();
		}
	}

	@Test
	public void KBProcessingStatusMarshallUnmarshallCycleTest() {
		try {
			String statusString = this.getXMLKBProcessingStatus();
			InputStream is = new ByteArrayInputStream(statusString.getBytes());
			JAXBContext jc = JAXBContext.newInstance(KBProcessingStatus.class);
			jc.createUnmarshaller().unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	private String getXMLKBProcessingStatus() throws JAXBException {
		Writer sw = new StringWriter();
		KBProcessingStatus rec = this.generateKBProcessingStatus();
		JAXBContext jc = JAXBContext.newInstance(KBProcessingStatus.class);

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(rec, sw);

		return sw.toString();
	}

	private KBProcessingStatus generateKBProcessingStatus() {		
		KBProcessingStatusType statusValue = this.generateStatusType();
		Info info = this.generateInfo();		
		
		KBProcessingStatus status = new KBProcessingStatus();		
		status.setStatus(statusValue);
		status.setResults(info);

		return status;
	}

	private KBProcessingStatusType generateStatusType() {		
		int enumSize = KBProcessingStatusType.values().length;
		int selectdValue = this.random.nextInt(enumSize);
		
		return KBProcessingStatusType.values()[selectdValue];
	}

	private Info generateInfo() {
		Info info = new Info();
		
		int maxInfo = 6;
		int numberOfInfo = this.random.nextInt(maxInfo) ;
		
		String infoContent;
		String infoContentTagName;
		
		for (int i = 0; i < numberOfInfo ; i++){
			infoContent = String.valueOf(this.random.nextInt());			
			infoContentTagName = "content-"+i;
			
			JAXBElement<String> jaxbElement =
					  new JAXBElement(new QName(infoContentTagName), 
					    String.class, infoContent);
			
			info.getAny().add(jaxbElement);
		}
		
		return info;
	}

	public static void main(String args[]) {
		KBProcessingOrAPITest me = new KBProcessingOrAPITest();
		try {
			String xml = me.getXMLKBProcessingStatus();
			System.out.println(xml);			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}