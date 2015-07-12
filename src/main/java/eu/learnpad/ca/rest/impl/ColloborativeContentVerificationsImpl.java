package eu.learnpad.ca.rest.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.ca.rest.ColloborativeContentVerifications;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Path("/learnpad/ca/validatecollaborativecontent")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class ColloborativeContentVerificationsImpl  {

	@Path("/test/{idAnnotatedCollaborativeContentAnalysis}")
	@GET
	public String getC(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)throws LpRestException{
		return contentID+"\n";
	}
	
	

	@Path("/{id}")
	@POST
	public String putValidateCollaborativeContent(@QueryParam("collaborativecontent") CollaborativeContentAnalysis contentFile)
				throws LpRestException{
					// TODO Auto-generated method stub
					return null;
				}
	
	@Path("/{edf}")
	@PUT
	public String updateValidateCollaborativeContent(@QueryParam("collaborativecontent") CollaborativeContentAnalysis contentFile)
				throws LpRestException{
					// TODO Auto-generated method stub
					return null;
				}

	@Path("/{idAnnotatedCollaborativeContentAnalysis:.*}")
	@GET
	public Collection<AnnotatedCollaborativeContentAnalysis> getCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
				// TODO Auto-generated method stub
		AnnotatedCollaborativeContentAnalysis asca = new AnnotatedCollaborativeContentAnalysis();
		asca.setId(1234);
		asca.setOverallQuality("overallquality");
		asca.setOverallQualityMeasure("OverallQualityMeasure");
		asca.setOverallRecommendations("Recommendation");
		asca.setType("all");
		CollaborativeContent sc = new CollaborativeContent();
		Content c = new Content();
		c.setContent("ciao");

		c.setContent(new Node(1234));

		sc.setContent(c);
		sc.setTitle("title");
		sc.setId("id");
		asca.setCollaborativeContent(sc);
		Annotation a = new Annotation();
		a.setId(44);
		a.setEndNode(1);
		a.setStartNode(0);
		a.setType("type");
		a.setRecommendation("rac");
		asca.setAnnotations(a);
		
		
		ArrayList<AnnotatedCollaborativeContentAnalysis> ar = new ArrayList<AnnotatedCollaborativeContentAnalysis>();
		ar.add(asca);
				return ar;
			}
	
	@Path("/{idAnnotatedCollaborativeContentAnalysis:.*}/status")
	@GET
	public String getStatusCollaborativeContentVerifications(@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID)
			throws LpRestException{
				// TODO Auto-generated method stub
				return null;
			}

}
