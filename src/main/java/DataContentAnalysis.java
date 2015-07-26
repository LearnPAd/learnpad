import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

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
		String color;
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
		return color;
	}

	private void createData(){
		Content c = acca.getCollaborativeContent().getContent();
		List<Annotation> lannot =  acca.getAnnotations();

		int inode=0;
		Node temp= null;
		String tempString = null;
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
					temp = (Node) obj;
				}
				if(inode==1){
					inode=0;
					listdata.add(search(temp, (Node) obj, lannot, tempString));
				}else{
					inode++;
				}


			}
		}


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
