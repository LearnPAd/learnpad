package eu.learnpad.ca.rest.impl;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import eu.learnpad.ca.analysis.correctness.CorrectnessAnalysisTest;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;






public class ColloborativeContentVerificationsImplTest extends JerseyTest{

	@Override
	protected Application configure() {
		return new ResourceConfig(ColloborativeContentVerificationsImpl.class);
	}

	@Test
	public void checkCollaborativeContentAnalysis() throws JAXBException {

		InputStream is = CorrectnessAnalysisTest.class.getClassLoader().getResourceAsStream("CollaborativeContentXML.xml");
		assertNotNull(is);
		JAXBContext jaxbContexti = JAXBContext.newInstance(CollaborativeContentAnalysis.class);

		Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
		CollaborativeContentAnalysis collaborativeContentInput = (CollaborativeContentAnalysis) jaxbUnmarshaller1.unmarshal(is);

		Entity<CollaborativeContentAnalysis> entity = Entity.entity(collaborativeContentInput,MediaType.APPLICATION_XML);
		Response response =  target("/learnpad/ca/validatecollaborativecontent").request(MediaType.APPLICATION_XML).post(entity);

		String id = response.readEntity(String.class);

		assertNotNull(response);
		String status = "IN PROGRESS";
		while(!status.equals("OK")){
			status =  target("/learnpad/ca/validatecollaborativecontent/"+id+"/status").request().get(String.class);

			assertNotNull(status);

		}

		Response annotatecontent =  target("/learnpad/ca/validatecollaborativecontent/"+id).request().get();

		ArrayList<AnnotatedCollaborativeContentAnalysis> res =	annotatecontent.readEntity(new GenericType<ArrayList<AnnotatedCollaborativeContentAnalysis>>() {});
		assertNotNull(res);
		assertNotNull(annotatecontent);
	}

	

}
