package eu.learnpad.verification.rest.impl;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.glassfish.jersey.server.ResourceConfig;
/*import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;

import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;*/
import org.junit.Test;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import eu.learnpad.verification.plugin.bpmn.guideline.factory.GuidelinesFactory;



public class UnderstandabilityRestImplTest extends JerseyTest {
	
	/*@Override
	protected TestContainerFactory getTestContainerFactory()  {
	    return new GrizzlyWebTestContainerFactory();
	}
	@Override
    protected DeploymentContext configureDeployment() {
		 forceSet(TestProperties.CONTAINER_PORT, "0");
		    return ServletDeploymentContext.forServlet(new ServletContainer(
		    		new ResourceConfig(UnderstandabilityRestImpl.class)))
		                                   .build();
         
    }*/
	
	@Override
	protected Application configure() {
		 enable(TestProperties.LOG_TRAFFIC);
		return new ResourceConfig(UnderstandabilityRestImpl.class);
	}

	@Test
	public void test() {
		try {
			URL is = UnderstandabilityRestImplTest.class.getClassLoader().getResource("annidategateway.bpmn");
			assertNotNull(is);
			String content = new String(Files.readAllBytes(Paths.get(is.toURI())));

			Entity<String> entity = Entity.entity(content,MediaType.TEXT_PLAIN);
			Response response =  target("/validatemodel/put/").request(MediaType.TEXT_PLAIN).post(entity);

			String id = response.readEntity(String.class);
			assertNotEquals(response.getStatus(), 404);
			assertNotNull(response);
			assertTrue(id!="");
			String status = "IN PROGRESS";
			while(!status.equals("OK")){
				status =  target("/validatemodel/"+id+"/status").request().get(String.class);

				assertNotNull(status);

			}

			Response annotatecontent =  target("/validatemodel/"+id).request().get();

			GuidelinesFactory res =	annotatecontent.readEntity(GuidelinesFactory.class);

			JAXBContext jaxbCtx;

			jaxbCtx = javax.xml.bind.JAXBContext.newInstance(GuidelinesFactory.class);

			Marshaller marshaller = jaxbCtx.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			//marshaller.marshal(annotatedCollaborativeContentAnalysis, System.out);

			OutputStream os = new FileOutputStream( "nosferatu.xml" );
			marshaller.marshal( res, os );
			assertNotNull(res);
			assertNotNull(annotatecontent);
		} catch (JAXBException | IOException | URISyntaxException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

}
