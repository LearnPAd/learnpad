package eu.learnpad.ca.rest.impl;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.languagetool.language.BritishEnglish;

import eu.learnpad.ca.correctness.CorrectnessAnalysis;
import eu.learnpad.ca.rest.StaticContentVerifications;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Path("/learnpad/ca/validatestaticcontent")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class StaticContentVerificationsImpl implements StaticContentVerifications {
	
	private static Map<Integer,CorrectnessAnalysis> map = new HashMap<Integer, CorrectnessAnalysis>();
	private static Integer id =0;


	@Path("/")
	@POST
	public String putValidateStaticContent(StaticContentAnalysis contentFile)
			throws LpRestException {
		id++;
		CorrectnessAnalysis threadcorre = new CorrectnessAnalysis(new BritishEnglish(), contentFile);
		threadcorre.start();
		map.put(id, threadcorre);
		return id.toString();
	}

	@Path("/{idAnnotatedStaticContentAnalysis:\\d+}")
	@GET
	public Collection<AnnotatedStaticContentAnalysis> getStaticContentVerifications(
			@PathParam("idAnnotatedStaticContentAnalysis") String contentID) throws LpRestException {
		if(map.containsKey(Integer.valueOf(contentID))){
			CorrectnessAnalysis correctnessAnalysis = map.get(Integer.valueOf(contentID));

			
			AnnotatedStaticContentAnalysis annotatedStaticContent = correctnessAnalysis.getAnnotatedStaticContentAnalysis();

			annotatedStaticContent.setId(Integer.valueOf(contentID));

			ArrayList<AnnotatedStaticContentAnalysis> ar = new ArrayList<AnnotatedStaticContentAnalysis>();
			ar.add(annotatedStaticContent);
			return ar;
		}else
			return null;
	}

	@Path("/{idAnnotatedStaticContentAnalysis:\\d+}/status")
	@GET
	public String getStatusStaticContentVerifications(@PathParam("idAnnotatedStaticContentAnalysis") String contentID)
			throws LpRestException {
		if(map.containsKey(Integer.valueOf(contentID))){
			CorrectnessAnalysis correctnessAnalysis = map.get(Integer.valueOf(contentID));
			return correctnessAnalysis.getStatus();
		}
		return "ERROR";
	}

}
