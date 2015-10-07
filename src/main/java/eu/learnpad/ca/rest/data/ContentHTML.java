package eu.learnpad.ca.rest.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public  class ContentHTML {


	@XmlMixed
	@XmlAnyElement
	protected List<Node> ContentHTML;

	/**
	 * get the value of Content
	 * @return List of Object
	 */
	
	/**
	 * get the value of Content
	 * @return List of Object
	 */

	public List<Node> getContent() {
		if (ContentHTML == null) {
			ContentHTML = new ArrayList<Node>();
		}
		return this.ContentHTML;
	}

	/***
	 * Set get the value of Content
	 * @param obj
	 */
	public void setContent(Element obj){
		if (ContentHTML == null) {
			ContentHTML = new ArrayList<Node>();
		}
		
			this.ContentHTML.add(obj);
		

	}

	@Override
	public String toString() {
		String string = "";
		for(Object obj: ContentHTML){
			if(obj instanceof Node){
				Node ele = (Node)obj;
				string+="[";
				string+=ele.getChildNodes().toString();
				string+=ele.getNodeName();
				string+=ele.getNodeValue();
				string+="]";
			}else
			string+=obj.toString();
		}
		return   string;
	}


	





}