package eu.learnpad.verification.rest.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import eu.learnpad.verification.plugin.bpmn.guideline.factory.GuidelinesFactory;
import eu.learnpad.verification.plugin.bpmn.reader.MyBPMN2ModelReader;
import eu.learnpad.verification.plugin.utils.XMLUtils;




@Path("validatemodel")
public class UnderstandabilityRestImpl {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UnderstandabilityRestImpl.class);

	
	private static Map<Integer,GuidelinesFactory> map = new HashMap<Integer,GuidelinesFactory>();
	private static Integer id =0;

	@Path("/put")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public String putModel(String modelxml){
		try{
			id++;
			if(!isOMGBPMN2(modelxml))
				return "";

			MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();

			GuidelinesFactory eg = new GuidelinesFactory(readerBPMN.readStringModel(modelxml));
			eg.setVerificationType("UNDERSTANDABILITY");
			eg.StartThreadPool();
			map.put(id, eg);
			return id.toString();
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return "FATAL ERROR";
		}
	}


	@Path("/{idmodel:\\d+}/status")
	@GET
	public String getStatusUnderstandabilityVerifications(@PathParam("idmodel") String modelID){
		try{
			if(map.containsKey(Integer.valueOf(modelID))){
				GuidelinesFactory ele = map.get(Integer.valueOf(modelID));
				if(ele.getStatusThreadPool()){
						return "OK";
				}else
					return "IN PROGRESS";
				
			}
		//	log.error("Element not found");
			return "ERROR";
		}catch(Exception e){
			//log.fatal("Fatal "+e.getMessage());
			return "FATAL ERROR";
		}
	}


	@Path("/{idmodel:\\d+}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public GuidelinesFactory getUnderstandabilityVerifications(@PathParam("idmodel") String modelID){
		try{
			if(map.containsKey(Integer.valueOf(modelID))){
				GuidelinesFactory ele = map.get(Integer.valueOf(modelID));
				if(ele.getStatusThreadPool()){
						return ele;
				}
				
			}
		//	log.error("Element not found");
			return null;
		}catch(Exception e){
			//log.fatal("Fatal "+e.getMessage());
			return null;
		}
	}

	private boolean isOMGBPMN2(String modelS){
		try{
			Document model = XMLUtils.getXmlDocFromString(modelS);
			String queryRoot = "/*[namespace-uri()='http://www.omg.org/spec/BPMN/20100524/MODEL' and local-name()='definitions']";
			Node bpmnRootNode =  (Node) XMLUtils.execXPath(model.getDocumentElement(), queryRoot, XPathConstants.NODE);
			if(bpmnRootNode!=null)
				return true;
		}catch(Exception e){
			log.error(e);
			log.error("\nModel involved in the exception:\n"+modelS);

		}
		return false;
	}
}
