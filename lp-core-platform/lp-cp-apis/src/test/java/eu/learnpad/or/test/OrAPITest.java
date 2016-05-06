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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.LearningMaterial;
import eu.learnpad.or.rest.data.LearningMaterials;
import eu.learnpad.or.rest.data.ListOfStringWrapper;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.SimilarCase;
import eu.learnpad.or.rest.data.SimilarCases;
import eu.learnpad.or.rest.data.SimulationData;

/**
 * 
 * @author gulyx
 */
public class OrAPITest {

	private SecureRandom random;

	public OrAPITest() {
		this.random = new SecureRandom();
	}

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

			boolean sesPassed = (zag.getSessionData().get("ses-key1").equals("ses-entry1"))
					&& (zag.getSessionData().get("ses-key2").equals("ses-entry2"));
			boolean subPassed = (zag.getSubmittedData().get("sub-key1").equals("sub-entry1"))
					&& (zag.getSubmittedData().get("sub-key2").equals("sub-entry2"));

			Assert.assertTrue(sesPassed && subPassed);
		} catch (JAXBException | IOException e) {
			Assert.fail();
		}
	}

	@Test
	public void RecommendationsMarshallUnmarshallCycleTest() {
		try {
			String recString = this.getXMLRecommendations();
			InputStream is = new ByteArrayInputStream(recString.getBytes());
			JAXBContext jc = JAXBContext.newInstance(Recommendations.class);
			jc.createUnmarshaller().unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertTrue(true);

	}

	public String getXMLRecommendations() throws JAXBException {
		Writer sw = new StringWriter();
		Recommendations rec = this.generateRecommendations();
		JAXBContext jc = JAXBContext.newInstance(Recommendations.class);

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(rec, sw);

		return sw.toString();
	}

	private Recommendations generateRecommendations() {
		Experts experts = this.generateExperts();
		LearningMaterials learningMaterials = this.generateLearningMaterials();
		SimilarCases similarCases = this.generateSimilarCases();

		Recommendations rec = new Recommendations();
		rec.setExperts(experts);
		rec.setLearningMaterials(learningMaterials);
		rec.setSimilarCases(similarCases);

		return rec;
	}

	private SimilarCases generateSimilarCases() {
		List<SimilarCase> scList = new ArrayList<SimilarCase>();

		int maxItems = this.random.nextInt(4);

		for (int i = 0; i < maxItems; i++) {
			SimilarCase sc = new SimilarCase();
			sc.setId("FooID" + i);
			sc.setName("FooName" + i);
			sc.setSimilarityValue("fooSimilarityValue" + i);

			sc.setExperts(this.generateExperts());
			sc.setLearningMaterials(this.generateLearningMaterials());
			sc.setData(this.generateDataMap());

			scList.add(sc);
		}

		SimilarCases scs = new SimilarCases();
		scs.setSimilarCases(scList);
		return scs;
	}

	private LearningMaterials generateLearningMaterials() {
		List<LearningMaterial> lmList = new ArrayList<LearningMaterial>();

		int maxItems = this.random.nextInt(4);

		for (int i = 0; i < maxItems; i++) {
			LearningMaterial lm = new LearningMaterial();
			lm.setId("FooID" + i);
			lm.setName("FooName" + i);
			lm.setMimeType("fooMimeType" + i);
			lm.setComment("FooComment" + i);
			lm.setQueryDescription("FooQueryDescription" + i);
			lm.setUrl("http://foo" + i + ".nowhere.org");
			lm.setDescription("none" + i);

			lmList.add(lm);
		}

		LearningMaterials lms = new LearningMaterials();
		lms.setLearningMaterials(lmList);
		return lms;
	}

	private Experts generateExperts() {
		List<BusinessActor> businessActors = new ArrayList<BusinessActor>();

		int maxItems = this.random.nextInt(4);

		for (int i = 0; i < maxItems; i++) {
			BusinessActor ba = new BusinessActor();
			ba.setName("FooName" + i);
			ba.setEmail("foo" + i + "@nowhere.org");
			ba.setPhoneNumber("+00 " + i);
			ba.setRole("FooRole" + i);
			ba.setUri("http://foo" + i + ".nowhere.org");
			ba.setDescription("none" + i);

			businessActors.add(ba);
		}

		Experts experts = new Experts();
		experts.setBusinessActors(businessActors);
		return experts;
	}

	private Map<String, ListOfStringWrapper> generateDataMap() {
		Map<String, ListOfStringWrapper> dataMap = new HashMap<String, ListOfStringWrapper>();

		int maxItems = this.random.nextInt(4);

		for (int i = 0; i < maxItems; i++) {
			ArrayList<String> l = new ArrayList<String>();
			l.add("first fooValue" + i);
			l.add("second fooValue" + i);
			ListOfStringWrapper list = new ListOfStringWrapper();
			list.setTheList(l);
			dataMap.put("fooKey" + i, list);
		}

		return dataMap;
	}

	public static void main(String args[]) {
		OrAPITest me = new OrAPITest();
		try {
			String xml = me.getXMLRecommendations();
			System.out.println(xml);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}