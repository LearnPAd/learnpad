package eu.learnpad.ca.rest.impl;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import eu.learnpad.ca.analysis.correctness.CorrectnessAnalysisTest;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;

public class StaticContentVerificationsImplTest extends JerseyTest{

	@Override
	protected Application configure() {
		return new ResourceConfig(StaticContentVerificationsImpl.class);
	}

	
	@Test
	public void checkStaticContentAnalysis() throws JAXBException {

		InputStream is = CorrectnessAnalysisTest.class.getClassLoader().getResourceAsStream("StaticContentXML.xml");
		assertNotNull(is);
		JAXBContext jaxbContexti = JAXBContext.newInstance(StaticContentAnalysis.class);

		Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
		StaticContentAnalysis StaticContentInput = (StaticContentAnalysis) jaxbUnmarshaller1.unmarshal(is);

		Entity<StaticContentAnalysis> entity = Entity.entity(StaticContentInput,MediaType.APPLICATION_XML);
		Response response =  target("/learnpad/ca/validatestaticcontent").request(MediaType.APPLICATION_XML).post(entity);

		String id = response.readEntity(String.class);

		assertNotNull(response);
		String status = "IN PROGRESS";
		while(!status.equals("OK")){
			status =  target("/learnpad/ca/validatestaticcontent/"+id+"/status").request().get(String.class);

			assertNotNull(status);

		}

		Response annotatecontent =  target("/learnpad/ca/validatestaticcontent/"+id).request().get();

		ArrayList<AnnotatedStaticContentAnalysis> res =	annotatecontent.readEntity(new GenericType<ArrayList<AnnotatedStaticContentAnalysis>>() {});
		assertNotNull(res);
		assertNotNull(annotatecontent);
	}

}
