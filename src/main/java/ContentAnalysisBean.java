

import java.io.Serializable;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import eu.learnpad.ca.rest.data.Annotation;
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
	private Annotation Annot;
	private String quality;
	private String measure;
	private String Reccomandation;
	private String id;


	public ContentAnalysisBean(){

		FacesContext context = FacesContext.getCurrentInstance();


		id =  (String) context.getExternalContext().getRequestMap().get("rest");




		System.out.println(id);
	}



	public Annotation getAnnot() {
		return Annot;
	}



	public void setAnnot(Annotation annot) {
		Annot = annot;
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


	public String executeListener(){
		Client client = ClientBuilder.newClient();
		if(id==null)id="1";
		WebTarget target = client.target("http://localhost:8080").path("contentanalysis/learnpad/ca/validatecollaborativecontent/"+id+"/status");
		String status =  target.request().get(String.class);
		System.out.println("Status: "+status);
		return status;
	}
	
	public void actionDownloadAnalysis(ActionEvent event){
		Client client = ClientBuilder.newClient();
		if(id==null)id="1";
		WebTarget target = client.target("http://localhost:8080").path("contentanalysis/learnpad/ca/validatecollaborativecontent/"+id);
		Response annotatecontent =  target.request().get();

		Collection<AnnotatedCollaborativeContentAnalysis> res =	annotatecontent.readEntity(new GenericType<Collection<AnnotatedCollaborativeContentAnalysis>>() {});
	
		System.out.println("Status: "+res);
	}


}
