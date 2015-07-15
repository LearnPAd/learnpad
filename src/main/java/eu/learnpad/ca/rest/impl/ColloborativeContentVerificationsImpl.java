package eu.learnpad.ca.rest.impl;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.languagetool.language.BritishEnglish;

import eu.learnpad.ca.correctness.CorrectnessAnalysis;
import eu.learnpad.ca.rest.ColloborativeContentVerifications;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Path("/learnpad/ca/validatecollaborativecontent")
//@Consumes("text/plain")//{"text/plain", MediaType.APPLICATION_XML})
@Produces(MediaType.APPLICATION_XML)
public class ColloborativeContentVerificationsImpl implements ColloborativeContentVerifications {


	private static Map<Integer,CorrectnessAnalysis> map = new HashMap<Integer, CorrectnessAnalysis>();
	private static Integer id =0;



	@Path("/test/{idAnnotatedCollaborativeContentAnalysis}")
	@GET
	public String getC(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)throws LpRestException{
		return contentID+"\n";
	}



	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String putValidateCollaborativeContent(CollaborativeContentAnalysis contentFile)
			throws LpRestException{
		id++;
		CorrectnessAnalysis threadcorre = new CorrectnessAnalysis(new BritishEnglish(), contentFile);
		threadcorre.start();
		map.put(id, threadcorre);
		return id.toString();

	}



	@Path("/{idAnnotatedCollaborativeContentAnalysis:\\d+}")
	@GET
	public Collection<AnnotatedCollaborativeContentAnalysis> getCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
		if(map.containsKey(Integer.valueOf(contentID))){
			CorrectnessAnalysis correctnessAnalysis = map.get(Integer.valueOf(contentID));

			
			AnnotatedCollaborativeContentAnalysis annotatedCollaborativeContent = correctnessAnalysis.getAnnotatedCollaborativeContentAnalysis();

			 annotatedCollaborativeContent.setId(Integer.valueOf(contentID));

			ArrayList<AnnotatedCollaborativeContentAnalysis> ar = new ArrayList<AnnotatedCollaborativeContentAnalysis>();
			ar.add(annotatedCollaborativeContent);
			return ar;
		}else
			return null;
	}

	@Path("/{idAnnotatedCollaborativeContentAnalysis:\\d+}/status")
	@GET
	public String getStatusCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
		if(map.containsKey(Integer.valueOf(contentID))){
			CorrectnessAnalysis correctnessAnalysis = map.get(Integer.valueOf(contentID));
			return correctnessAnalysis.getStatus();
			
		}
		return "ERROR";
	}

}
