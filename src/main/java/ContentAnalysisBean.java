

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
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
	private List<Annotation> Annot;
	private String quality;
	private String measure;
	private String Reccomandation;
	private String id;
	private List<Object> Contents;

	public ContentAnalysisBean(){

		
		

		System.out.println(id);
	}

	

	@PostConstruct
	   public void init(){
		Annot = new ArrayList<Annotation>();
		Annotation a = new Annotation(1,"correctness",3,4,"f2");
		Annot.add(a);
	   }



	

	

	public List<Object> getContents() {
		return Contents;
	}



	public void setContents(List<Object> contens) {
		Contents = contens;
	}



	public List<Annotation> getAnnot() {
		return Annot;
	}



	public void setAnnot(List<Annotation> annot) {
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
		FacesContext context = FacesContext.getCurrentInstance();


		id =  (String) context.getApplication().evaluateExpressionGet(context, "#{ContentBean.restid}", String.class);
		Client client = ClientBuilder.newClient();
		if(id==null){
			id="1";
		}
		WebTarget target = client.target("http://localhost:8080").path("contentanalysis/learnpad/ca/validatecollaborativecontent/"+id+"/status");
		String status =  target.request().get(String.class);
		System.out.println("Status: "+status);
		return status;
	}

	public void actionDownloadAnalysis(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();

		id =  (String) context.getApplication().evaluateExpressionGet(context, "#{ContentBean.restid}", String.class);

		//		id =  (String) context.getELContext().getELResolver().getValue(context.getELContext(),String.class , "rest");
		Client client = ClientBuilder.newClient();
		if(id==null){
			id="1";
		}
		WebTarget target = client.target("http://localhost:8080").path("contentanalysis/learnpad/ca/validatecollaborativecontent/"+id);
		Response annotatecontent =  target.request().get();

		Collection<AnnotatedCollaborativeContentAnalysis> res =	annotatecontent.readEntity(new GenericType<Collection<AnnotatedCollaborativeContentAnalysis>>() {});

		AnnotatedCollaborativeContentAnalysis prma = res.iterator().next();
		this.setContents(prma.getCollaborativeContent().getContent().getContent());
		this.setAnnot(prma.getAnnotations());
		this.setContent(prma.getCollaborativeContent().getContent().toString());
		this.setId(prma.getId().toString());
		this.setTitle(prma.getCollaborativeContent().getTitle());
		this.setMeasure(prma.getOverallQualityMeasure());
		this.setQuality(prma.getOverallQuality());
		this.setReccomandation(prma.getOverallRecommendations());
		
		System.out.println("Status: "+res);
		
	}


}
