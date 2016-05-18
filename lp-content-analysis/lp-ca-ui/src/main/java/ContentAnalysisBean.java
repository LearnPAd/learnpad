

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalyses;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;


@ManagedBean(name="ContentAnalysisBean")
@SessionScoped
public class ContentAnalysisBean implements Serializable {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ContentAnalysisBean.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String port;

	private String Title;
	private String Content;

	private String quality;
	private String measure;
	private String Reccomandation;
	private String id;
	private String status;
	private Collection<AnnotatedCollaborativeContentAnalysis> collectionannotatedcontent;
	private String ip;


	public ContentAnalysisBean(){
		try{
		Properties prop = new Properties();
		// load a properties file
		prop.load(ContentAnalysisBean.class.getClassLoader().getResourceAsStream("config.properties")); //$NON-NLS-1$

			ip = prop.getProperty("ip_server");
			port = prop.getProperty("port_server");
		}catch(Exception e){
			ip="localhost";
			port = "8082";
		}

		log.trace(id);
		
		/*//String Name=request.getParameter("name");
		Collection<String> listc = getCollectionids();
		if(!listc.isEmpty()){
			for(String l :listc){
				this.id=l;
				log.trace(id);
			}
			actionDownloadAnalysis();
		}*/
	}



	@PostConstruct
	public void init(){
		log.trace(id);
	}



	public String getStatus() {
		return status;
	}

	public void setAllid(String ID){
		this.id = ID;
	}

	public String getAllid(){
		return this.id;
	}

	public Collection<String> getCollectionids(){
		try{
			Client client = ClientBuilder.newClient();

			WebTarget target = client.target("http://"+ip+":"+port).path("lp-content-analysis/learnpad/ca/bridge/validatecollaborativecontent/allid");

			Response allID =  target.request().get();
			String res = allID.readEntity(String.class);

			return Arrays.asList(res.split(";"));
		}catch(Exception e){
			log.error(e);
			return null;
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String color(String OverallQuality){
		String color ="";
		if(OverallQuality!=null){
			switch (OverallQuality) {
			case "VERY BAD":
				color ="#FF0000";
				break;

			case "BAD":
				color ="#FF9C07";
				break;

			case "NOT SO BAD":
				color ="#FFFF00";
				break;
			case "GOOD":
				color ="#00FF00";
				break;
			case "VERY GOOD":
				color ="#00FF00";
				break;
			case "EXCELLENT":
				color ="#00FF7F";
				break;


			default:
				color ="";
				break;
			}
		}
		return color;
	}


	public Collection<AnnotatedCollaborativeContentAnalysis> getCollectionannotatedcontent() {
		return collectionannotatedcontent;
	}



	public void setCollectionannotatedcontent(
			Collection<AnnotatedCollaborativeContentAnalysis> collectionannotatedcontent) {
		this.collectionannotatedcontent = collectionannotatedcontent;
	}




	public String getQuality() {
		return quality;
	}



	public void setQuality(String quality) {
		this.quality = quality;
	}



	public String getMeasure() {
		return measure;
	}



	public void setMeasure(String measure) {
		this.measure = measure;
	}



	public String getReccomandation() {
		return Reccomandation;
	}



	public void setReccomandation(String reccomandation) {
		Reccomandation = reccomandation;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}




	public void actionDownloadAnalysis(){
		try{
			log.trace(id);
			
			Client client = ClientBuilder.newClient();
			if(id==null){
				id="1";
			}

			WebTarget target = client.target("http://"+ip+":"+port).path("lp-content-analysis/learnpad/ca/bridge/validatecollaborativecontent/"+id+"/status");
			String 	status ="";
			while (!status.equals("OK")) {


				status = target.request().get(String.class);

				this.setStatus(status);
				if(status.equals("ERROR"))
					break;
			}
			log.trace("Status: "+status);

			if(status.equals("OK")){
				target = client.target("http://"+ip+":"+port).path("lp-content-analysis/learnpad/ca/bridge/validatecollaborativecontent/"+id);
				Response annotatecontent =  target.request().get();
				AnnotatedCollaborativeContentAnalyses res = annotatecontent.readEntity(new GenericType<AnnotatedCollaborativeContentAnalyses>() {});
				this.setCollectionannotatedcontent(res.getAnnotateCollaborativeContentAnalysis());

			}
		}catch(Exception e){
			log.error(e);
		}

	}


}
