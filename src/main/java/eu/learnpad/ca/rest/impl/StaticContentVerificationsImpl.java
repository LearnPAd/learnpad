package eu.learnpad.ca.rest.impl;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.languagetool.Language;
import org.languagetool.language.BritishEnglish;
import org.languagetool.language.Italian;

import eu.learnpad.ca.analysis.AnalysisInterface;
import eu.learnpad.ca.analysis.correctness.CorrectnessAnalysis;
import eu.learnpad.ca.analysis.simplicity.Simplicity;
import eu.learnpad.ca.rest.StaticContentVerifications;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Path("/learnpad/ca/validatestaticcontent")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class StaticContentVerificationsImpl implements StaticContentVerifications {

	private static Map<Integer,List<AnalysisInterface>> map = new HashMap<Integer,List<AnalysisInterface>>();
	private static Integer id =0;


	@Path("/")
	@POST
	public String putValidateStaticContent(StaticContentAnalysis contentFile)
			throws LpRestException {
		if(contentFile.getQualityCriteria().isCorrectness()){
			id++;
			Language lang = null;
			if(contentFile.getLanguage()=="english"){
				lang = new BritishEnglish();
			}else{
				if(contentFile.getLanguage()=="italian"){
					lang = new Italian();
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


	@Path("/{idAnnotatedStaticContentAnalysis:\\d+}")
	@GET
	public Collection<AnnotatedStaticContentAnalysis> getStaticContentVerifications(
			@PathParam("idAnnotatedStaticContentAnalysis") String contentID) throws LpRestException {
		if(map.containsKey(Integer.valueOf(contentID))){
			ArrayList<AnnotatedStaticContentAnalysis> ar = new ArrayList<AnnotatedStaticContentAnalysis>();
			List<AnalysisInterface> listanalysisInterface = map.get(Integer.valueOf(contentID));

			for(AnalysisInterface analysisInterface :listanalysisInterface){
				AnnotatedStaticContentAnalysis annotatedStaticContent = analysisInterface.getAnnotatedStaticContentAnalysis();

				annotatedStaticContent.setId(Integer.valueOf(contentID));
				ar.add(annotatedStaticContent);
			}

			return ar;
		}else
			return null;
	}

	@Path("/{idAnnotatedStaticContentAnalysis:\\d+}/status")
	@GET
	public String getStatusStaticContentVerifications(@PathParam("idAnnotatedStaticContentAnalysis") String contentID)
			throws LpRestException {
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
