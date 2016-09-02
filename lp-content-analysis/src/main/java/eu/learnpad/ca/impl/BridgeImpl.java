package eu.learnpad.ca.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import eu.learnpad.ca.Bridge;
import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.analysis.completeness.Completeness;
import eu.learnpad.ca.analysis.contentclarity.ContentClarity;
import eu.learnpad.ca.analysis.correctness.CorrectnessAnalysis;
import eu.learnpad.ca.analysis.non_ambiguity.NonAmbiguity;
import eu.learnpad.ca.analysis.presentation.PresentationClarity;
import eu.learnpad.ca.analysis.simplicity.Simplicity;
import eu.learnpad.ca.config.PropertyUtil;
import eu.learnpad.ca.gate.GateThread;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalyses;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalyses;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Path("/learnpad/ca/bridge/")
@Produces(MediaType.APPLICATION_XML)
public class BridgeImpl extends Bridge {

	private static Map<Integer,List<AbstractAnalysisClass>> map = new HashMap<Integer,List<AbstractAnalysisClass>>();
	private static Integer id =0;
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BridgeImpl.class);

    private final String configFileLocationLabel = "config.file.location";
    private PropertyUtil conf;

    private final String IP_CAUI_Label = "ip_ui_server";
    private final String PORT_CAUI_Label = "port_ui_server";
    private final String IP_CAUI_DEFAULT = "localhost";
    private final String PORT_CAUI_DEFAULT = "9092";
    
	public BridgeImpl() {
		String confFileName = System.getProperty(configFileLocationLabel);
		if (confFileName == null) {
			this.conf = new PropertyUtil();
		} else {
			this.conf = new PropertyUtil(confFileName);
		}
	}
	
	@Path("/validatecollaborativecontent")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String putValidateCollaborativeContent(CollaborativeContentAnalysis contentFile)
			throws LpRestException{
		try{
			if(contentFile!=null){
				String content = contentFile.getCollaborativeContent().getContentplain();
				if(content!=null && content.length()>0){
					GateThread gateu = new GateThread(content,contentFile.getQualityCriteria());
					gateu.start();
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

						/*JuridicalJargon threadsimply = new JuridicalJargon (contentFile, lang);
					threadsimply.start();
					putAndCreate(id, threadsimply);

					DifficultJargon threadDF = new DifficultJargon (contentFile, lang);
					threadDF.start();
					putAndCreate(id, threadDF);



					ExcessiveLength threadEL = new ExcessiveLength(contentFile, lang);
					threadEL.start();
					putAndCreate(id, threadEL);*/
						Simplicity threadEL = new Simplicity(contentFile, lang, gateu);
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
					if(contentFile.getQualityCriteria().isPresentationClarity()){

						PresentationClarity threadPresentation = new PresentationClarity (contentFile, lang);
						threadPresentation.start();
						putAndCreate(id, threadPresentation);

					}
					if(contentFile.getQualityCriteria().isCompleteness()){

						Completeness threadCompleteness = new Completeness (contentFile, lang);
						threadCompleteness.start();
						putAndCreate(id, threadCompleteness);

					}


					return id.toString();
				}else{
					log.error("No Content send: "+content);
					return "No Content send";
				}
			}else{
				log.error("Null Element send");
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

	@Path("/validatecollaborativecontent/{idAnnotatedCollaborativeContentAnalysis:\\d+}")
	@GET
	public AnnotatedCollaborativeContentAnalyses getCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
		try{
			if(map.containsKey(Integer.valueOf(contentID))){
				AnnotatedCollaborativeContentAnalyses ar = new AnnotatedCollaborativeContentAnalyses();
				List<AbstractAnalysisClass> listanalysisInterface = map.get(Integer.valueOf(contentID));

				for(AbstractAnalysisClass analysisInterface :listanalysisInterface){
					AnnotatedCollaborativeContentAnalysis annotatedCollaborativeContent = analysisInterface.getAnnotatedCollaborativeContentAnalysis();
					if(annotatedCollaborativeContent!=null){
						annotatedCollaborativeContent.setId(Integer.valueOf(contentID));
						ar.setAnnotateCollaborativeContentAnalysis(annotatedCollaborativeContent);
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

	@Path("/validatecollaborativecontent/{idAnnotatedCollaborativeContentAnalysis:\\d+}/status")
	@GET
	public String getStatusCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
		try{
			if(map.containsKey(Integer.valueOf(contentID))){
				List<AbstractAnalysisClass> listanalysisInterface  = map.get(Integer.valueOf(contentID));
				Integer progress = getProgress(listanalysisInterface);
				if(progress>99){
					clean(listanalysisInterface);
					return "OK";
				}
				else
					return "InProgress_"+progress+"%";
			}
			log.error("Element not found: "+contentID+" map:"+map.keySet().toString());
			return "ERROR";
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return "FATAL ERROR";
		}
	}

	@Path("/validatecollaborativecontent/{idAnnotatedCollaborativeContentAnalysis:\\d+}/view")
	@GET
	public String getCollaborativeContentVerificationsView(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException {
//		String ip,port="";
//		try{
//			Properties prop = new Properties();
//			// load a properties file
//			InputStream is = new FileInputStream("config.properties");
//			prop.load(is); //$NON-NLS-1$
//
//			ip = prop.getProperty("ip_ui_server");
//			port = prop.getProperty("port_ui_server");
//			}catch(Exception e){
//				ip="localhost";
//				port = "9092";
//			}
		String ip = this.conf.getProperty(IP_CAUI_Label, IP_CAUI_DEFAULT);
		String port = this.conf.getProperty(PORT_CAUI_Label, PORT_CAUI_DEFAULT);
		
		return "http://"+ip+":"+port+"/getanalysis.jsf?id="+contentID;
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
	
	private void clean(List<AbstractAnalysisClass> listanalysisInterface){
		for(AbstractAnalysisClass analysisInterface :listanalysisInterface){
			if(analysisInterface.getGate()!=null){
				analysisInterface.getGate().cleanup();
			}
		}
	}

	@Path("/validatecollaborativecontent/allid")
	@GET
	public String  getStatusCollaborativeContentVerifications()
			throws LpRestException{
		String result = new String();
		try{
			if(!map.isEmpty()){
				for(Integer key :map.keySet()){
					result+=key.toString()+";";
				}

				return result;

			}
			log.error("Element not found");
			return "ERROR";
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());

			return "FATAL ERROR";

		}
	}

	@Path("/validatestaticcontent")
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




	@Path("/validatestaticcontent/{idAnnotatedStaticContentAnalysis:\\d+}")
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

	@Path("/validatestaticcontent/{idAnnotatedStaticContentAnalysis:\\d+}/status")
	@GET
	public String getStatusStaticContentVerifications(@PathParam("idAnnotatedStaticContentAnalysis") String contentID)
			throws LpRestException {
		try{
			if(map.containsKey(Integer.valueOf(contentID))){
				List<AbstractAnalysisClass> listanalysisInterface  = map.get(Integer.valueOf(contentID));
				Integer progress = getProgress(listanalysisInterface);
				if(progress>99){
					clean(listanalysisInterface);
					return "OK";
				}
				else
					return "InProgress_"+progress+"%";

			}
			log.error("Element not found: "+contentID+" map:"+map.keySet().toString());
			return "ERROR";


		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return "FATAL ERROR";
		}
	}

	@Path("/validatestaticcontent/{idAnnotatedStaticContentAnalysis:\\d+}/view")
	@GET
	public String getStaticContentVerificationsView(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException {
//		String ip,port="";
//		try{
//			Properties prop = new Properties();
//			// load a properties file
//			InputStream is = new FileInputStream("config.properties");
//			prop.load(is); //$NON-NLS-1$
//
//				ip = prop.getProperty("ip_ui_server");
//				port = prop.getProperty("port_ui_server");
//			}catch(Exception e){
//				ip="localhost";
//				port = "9092";
//			}
		String ip = this.conf.getProperty(IP_CAUI_Label, IP_CAUI_DEFAULT);
		String port = this.conf.getProperty(PORT_CAUI_Label, PORT_CAUI_DEFAULT);
				
		return "http://"+ip+":"+port+"/getanalysis.jsf?id="+contentID;
	}

}
