package eu.learnpad.ca.rest.impl;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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

import org.languagetool.Language;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;
import org.languagetool.language.Italian;

import eu.learnpad.ca.analysis.AnalysisInterface;
import eu.learnpad.ca.analysis.correctness.CorrectnessAnalysis;
import eu.learnpad.ca.analysis.simplicity.Simplicity;
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


	private static Map<Integer,List<AnalysisInterface>> map = new HashMap<Integer,List<AnalysisInterface>>();
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
		if(contentFile!=null){
			id++;
			Language lang = null;
			if(contentFile.getLanguage().toLowerCase().equals("english")){
				lang = new  BritishEnglish();
			}else{
				if(contentFile.getLanguage().toLowerCase().equals("italian")){
					lang = new Italian();
				}else
					if(contentFile.getLanguage().toLowerCase().equals("english uk")){
						lang = new BritishEnglish();
					}else
						if(contentFile.getLanguage().toLowerCase().equals("english us")){
							lang = new AmericanEnglish();
						}else
							lang = new BritishEnglish();
			}
			if(contentFile.getQualityCriteria().isCorrectness()){

				CorrectnessAnalysis threadcorre = new CorrectnessAnalysis(lang, contentFile);
				threadcorre.start();
				putAndCreate(id, threadcorre);

			}
			if(contentFile.getQualityCriteria().isSimplicity()){

				Simplicity threadsimply = new Simplicity (contentFile, lang);
				threadsimply.start();
				putAndCreate(id, threadsimply);

			}
			return id.toString();
		}else
			return "Null Element send";


	}

	private void putAndCreate(int id, AnalysisInterface ai){
		if(!map.containsKey(id)){
			List<AnalysisInterface> lai = new ArrayList<AnalysisInterface>();
			lai.add(ai);
			map.put(id, lai);
		}else{
			List<AnalysisInterface> lai = map.get(id);
			lai.add(ai);
		}
	}

	@Path("/{idAnnotatedCollaborativeContentAnalysis:\\d+}")
	@GET
	public Collection<AnnotatedCollaborativeContentAnalysis> getCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
		if(map.containsKey(Integer.valueOf(contentID))){
			ArrayList<AnnotatedCollaborativeContentAnalysis> ar = new ArrayList<AnnotatedCollaborativeContentAnalysis>();
			List<AnalysisInterface> listanalysisInterface = map.get(Integer.valueOf(contentID));

			for(AnalysisInterface analysisInterface :listanalysisInterface){
				AnnotatedCollaborativeContentAnalysis annotatedCollaborativeContent = analysisInterface.getAnnotatedCollaborativeContentAnalysis();

				annotatedCollaborativeContent.setId(Integer.valueOf(contentID));
				ar.add(annotatedCollaborativeContent);
			}



			return ar;
		}else
			return null;
	}

	@Path("/{idAnnotatedCollaborativeContentAnalysis:\\d+}/status")
	@GET
	public String getStatusCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
		if(map.containsKey(Integer.valueOf(contentID))){
			List<AnalysisInterface> listanalysisInterface  = map.get(Integer.valueOf(contentID));
			for(AnalysisInterface analysisInterface :listanalysisInterface){
				if(analysisInterface.getStatus()!="OK"){
					return "IN PROGRESS";
				}
			}
			return "OK";
		}
		return "ERROR";
	}

}
