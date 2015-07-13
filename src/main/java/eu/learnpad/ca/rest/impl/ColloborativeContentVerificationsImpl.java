package eu.learnpad.ca.rest.impl;

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


	private static Map<Integer,CollaborativeContentAnalysis> map = new HashMap<Integer, CollaborativeContentAnalysis>();
	private Integer id =0;



	@Path("/test/{idAnnotatedCollaborativeContentAnalysis}")
	@GET
	public String getC(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)throws LpRestException{
		return contentID+"\n";
	}



	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String putValidateCollaborativeContent(@FormParam("collaborativecontent") CollaborativeContentAnalysis contentFile)
			throws LpRestException{
		id++;
		map.put(id, contentFile);
		return id.toString();

	}



	@Path("/{idAnnotatedCollaborativeContentAnalysis:.*}")
	@GET
	public Collection<AnnotatedCollaborativeContentAnalysis> getCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
		if(map.containsKey(Integer.valueOf(contentID))){
			CollaborativeContentAnalysis caa = map.get(Integer.valueOf(contentID));

			CorrectnessAnalysis corrana = new CorrectnessAnalysis( new BritishEnglish());
			AnnotatedCollaborativeContentAnalysis acca = corrana.check(caa);



			ArrayList<AnnotatedCollaborativeContentAnalysis> ar = new ArrayList<AnnotatedCollaborativeContentAnalysis>();
			ar.add(acca);
			return ar;
		}else
			return null;
	}

	@Path("/{idAnnotatedCollaborativeContentAnalysis:.*}/status")
	@GET
	public String getStatusCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
		// TODO Auto-generated method stub
		return contentID;
	}

}
