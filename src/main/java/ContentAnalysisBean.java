

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;


@ManagedBean(name="ContentAnalysisBean")
@SessionScoped
public class ContentAnalysisBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Title;
	private String Content;

	private String quality;
	private String measure;
	private String Reccomandation;
	private String id;
	private String status;
	private Collection<AnnotatedCollaborativeContentAnalysis> collectionannotatedcontent;


	public ContentAnalysisBean(){

		
		

		System.out.println(id);
	}

	

	@PostConstruct
	   public void init(){
		
		
	   }



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
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

	
	
	
	public void actionDownloadAnalysis(ActionEvent event){
		try {
            Thread.currentThread().sleep(2500);
        } catch(Exception e) {}
		FacesContext context = FacesContext.getCurrentInstance();


		//id =  (String) context.getApplication().evaluateExpressionGet(context, "#{ContentBean.restid}", String.class);
		Client client = ClientBuilder.newClient();
		if(id==null){
			id="1";
		}
		 
		WebTarget target = client.target("http://localhost:8080").path("lp-content-analysis/learnpad/ca/validatecollaborativecontent/"+id+"/status");
		String 	status ="";
		while (!status.equals("OK")) {
			
		
		status = target.request().get(String.class);
		
		this.setStatus(status);
		}
		System.out.println("Status: "+status);
		
		
		target = client.target("http://localhost:8080").path("lp-content-analysis/learnpad/ca/validatecollaborativecontent/"+id);
		Response annotatecontent =  target.request().get();

		this.setCollectionannotatedcontent(annotatecontent.readEntity(new GenericType<Collection<AnnotatedCollaborativeContentAnalysis>>() {}));

		
		
		
	}


}
