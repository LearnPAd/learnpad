import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;

@ManagedBean(name="DataContentAnalysisbean")
@SessionScoped
public class DataContentAnalysis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6790649148160108414L;
	private AnnotatedCollaborativeContentAnalysis acca;
	private List<DataContent> listdata;
	private String color;
	private String xml;
	private String element = "ele";


	public DataContentAnalysis(){

		System.out.println("DataContentAnalysisbean");
		listdata = new ArrayList<DataContent>();
	}



	/*public DataContentAnalysis(AnnotatedCollaborativeContentAnalysis acca) {
		this.acca = acca;
		listdata = new ArrayList<DataContent>();
		createData();
	}*/



	public String getXml() {

		JAXBContext jaxbCtx;
		StringWriter sw = new StringWriter();
		if(acca!=null){
			try {
				jaxbCtx = javax.xml.bind.JAXBContext.newInstance(AnnotatedCollaborativeContentAnalysis.class);

				Marshaller marshaller = jaxbCtx.createMarshaller();
				marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
				marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshaller.marshal(acca, sw);


			} catch (JAXBException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sw.toString();
	}



	public void setXml(String xml) {
		this.xml = xml;
	}



	public String getElement() {
		return element;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public AnnotatedCollaborativeContentAnalysis getAcca() {
		return acca;
	}

	public void setAcca(AnnotatedCollaborativeContentAnalysis acca) {
		this.acca = acca;
		listdata = new ArrayList<DataContent>();
		createData();
		System.out.println(listdata);
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getColor(){
		color ="";
		if(acca!=null){
			switch (acca.getOverallQuality()) {
			case "VERY BAD":
				color ="#FF0000";
				break;

			case "BAD":
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

	private void createData(){
		Content c = acca.getCollaborativeContent().getContent();
		List<Annotation> lannot =  acca.getAnnotations();
		String content= exContent(c);
		Integer inode=0;
		//List<Node> temp= new ArrayList<Node>();
		DataContent prec = null;
		Collections.sort(lannot);
		for (Annotation ann : lannot) {
			Integer startoff = ann.getstartNode_Offset();
			Integer endoff = ann.getendNode_Offset();
			if(inode<startoff){
				String tok =  content.substring(inode,startoff.intValue());;
				listdata.add(new DataContent(tok, acca.getType()));
			}else{
				if(inode>startoff){
					if(prec!=null)
						prec.setRecommendation(prec.getRecommendation()+"\r\n"+ann.getRecommendation());
					continue;
				}
			}
			String token =  content.substring(startoff.intValue(), endoff.intValue());
			prec = new DataContent(token,ann.getRecommendation(),ann.getType());
			listdata.add(prec);
			inode = endoff;
		}
		/*String tempString = null;
		for (Object obj : c.getContent()) {
			if(obj instanceof String && inode==0 ){
				listdata.add(new DataContent(obj.toString(), acca.getType()));
				continue;
			}
			if(obj instanceof String && inode==1 ){
				tempString = obj.toString()+" ";
			}
			if(obj instanceof Node){
				if(inode==0){
					temp.add((Node) obj);
				}
				if(inode==1){
					DataContent s = search(temp.get(0), (Node) obj, lannot, tempString);
					if(s!=null){
						inode=0;
						listdata.add(s);
					}else{

					}
				}else{
					inode++;
				}


			}
		}*/


	}

	private String exContent(Content c) {
		String content = new String(); 
		for(Object obj : c.getContent()) {
			if(obj instanceof String){
				content+=obj.toString();
			}

		}
		return content;
	}



	private DataContent search(Node startn, Node end, List<Annotation> lannot, String element){
		for (Annotation annotation : lannot) {
			if(annotation.getStartNode().equals(startn.getId()) && annotation.getEndNode().equals(end.getId()) ){
				return new DataContent(element,annotation.getRecommendation(),annotation.getType());
			}
		}
		return null;
	}

	public List<DataContent> getListdata() {
		return listdata;
	}

	public void setListdata(List<DataContent> listdata) {
		this.listdata = listdata;
	}

	public String executeListener(){

		return "";
	}

	public void listener(ActionEvent event){
		this.setAcca((AnnotatedCollaborativeContentAnalysis) event.getComponent().getAttributes().get("valdata"));
		System.out.println(event);
	}

}
