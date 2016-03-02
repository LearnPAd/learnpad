package eu.learnpad.ca.rest.impl;

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
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;
import org.languagetool.language.Italian;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.analysis.completeness.Completeness;
import eu.learnpad.ca.analysis.contentclarity.ContentClarity;
import eu.learnpad.ca.analysis.correctness.CorrectnessAnalysis;
import eu.learnpad.ca.analysis.non_ambiguity.NonAmbiguity;
import eu.learnpad.ca.analysis.presentation.PresentationClarity;
import eu.learnpad.ca.analysis.simplicity.Simplicity;
import eu.learnpad.ca.gate.GateThread;
import eu.learnpad.ca.rest.StaticContentVerifications;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalyses;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Path("/learnpad/ca/bridge/validatestaticcontent")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class StaticContentVerificationsImpl implements StaticContentVerifications {

	private static Map<Integer,List<AbstractAnalysisClass>> map = new HashMap<Integer,List<AbstractAnalysisClass>>();
	private static Integer id =0;
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(StaticContentVerificationsImpl.class);

	@Path("/")
	@POST
	public String putValidateStaticContent(StaticContentAnalysis contentFile)
			throws LpRestException {
		try{
			String content = contentFile.getStaticContent().getContentplain();
			if(content!=null && content.length()>0){
				GateThread gateu = new GateThread(content,contentFile.getQualityCriteria());
				gateu.start();
				if(contentFile.getQualityCriteria().isCorrectness()){
					id++;
					Language lang = null;
					if(contentFile.getLanguage()=="english"){
						lang = new BritishEnglish();
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

						Simplicity threadEL = new Simplicity(contentFile, lang,gateu);
						threadEL.start();
						putAndCreate(id, threadEL);

					}
					if(contentFile.getQualityCriteria().isNonAmbiguity()){

						NonAmbiguity threadNonAmbiguity = new NonAmbiguity (contentFile, lang, gateu);
						threadNonAmbiguity.start();
						putAndCreate(id, threadNonAmbiguity);

					}
					if(contentFile.getQualityCriteria().isContentClarity()){

						ContentClarity threadContentClarity = new ContentClarity (contentFile, lang, gateu);
						threadContentClarity.start();
						putAndCreate(id, threadContentClarity);

					}
					if(contentFile.getQualityCriteria().isCompleteness()){

						Completeness threadCompleteness = new Completeness (contentFile, lang);
						threadCompleteness.start();
						putAndCreate(id, threadCompleteness);

					}
					if(contentFile.getQualityCriteria().isPresentationClarity()){

						PresentationClarity threadPresentation = new PresentationClarity (contentFile, lang);
						threadPresentation.start();
						putAndCreate(id, threadPresentation);

					}
					return id.toString();
				}else{
					log.error("No Content send: "+content);
					return "No Content send";
				}
			}else{
				log.error("Error "+"Null Element send");
				return "Null Element send";
			}

		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return "FATAL ERROR";
		}
	}

	private void putAndCreate(int id, AbstractAnalysisClass ai){
		if(!map.containsKey(id)){
			List<AbstractAnalysisClass> lai = new ArrayList<AbstractAnalysisClass>();
			lai.add(ai);
			map.put(id, lai);
		}else{
			List<AbstractAnalysisClass> lai = map.get(id);
			lai.add(ai);
		}
	}


	@Path("/{idAnnotatedStaticContentAnalysis:\\d+}")
	@GET
	public AnnotatedStaticContentAnalyses getStaticContentVerifications(
			@PathParam("idAnnotatedStaticContentAnalysis") String contentID) throws LpRestException {
		try{
			if(map.containsKey(Integer.valueOf(contentID))){
				AnnotatedStaticContentAnalyses ar = new AnnotatedStaticContentAnalyses();
				List<AbstractAnalysisClass> listanalysisInterface = map.get(Integer.valueOf(contentID));

				for(AbstractAnalysisClass analysisInterface :listanalysisInterface){
					AnnotatedStaticContentAnalysis annotatedStaticContent = analysisInterface.getAnnotatedStaticContentAnalysis();
					if(annotatedStaticContent!=null){
						annotatedStaticContent.setId(Integer.valueOf(contentID));
						ar.setAnnotateStaticContentAnalysis(annotatedStaticContent);
					}
				}

				return ar;
			}else{
				log.error("Element not found: "+contentID+" map:"+map.keySet().toString());
				return null;
			}
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return null;
		}
	}

	@Path("/{idAnnotatedStaticContentAnalysis:\\d+}/status")
	@GET
	public String getStatusStaticContentVerifications(@PathParam("idAnnotatedStaticContentAnalysis") String contentID)
			throws LpRestException {
		try{
			if(map.containsKey(Integer.valueOf(contentID))){
				List<AbstractAnalysisClass> listanalysisInterface  = map.get(Integer.valueOf(contentID));
				Integer progress = getProgress(listanalysisInterface);
				if(progress>99)
					return "OK";
				else
					return "InProgess_"+progress+"%";

			}
			log.error("Element not found: "+contentID+" map:"+map.keySet().toString());
			return "ERROR";


		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return "FATAL ERROR";
		}
	}

	private Integer getProgress(List<AbstractAnalysisClass> listanalysisInterface){
		int size = listanalysisInterface.size();
		int i = 0;
		for(AbstractAnalysisClass analysisInterface :listanalysisInterface){
			if(analysisInterface.getStatus().equals("OK") ){
				i++;
			}
		}
		Integer p = (i*100/size);
		return p;

	}
}
