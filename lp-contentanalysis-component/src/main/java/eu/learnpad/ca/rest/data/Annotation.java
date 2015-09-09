package eu.learnpad.ca.rest.data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public  class Annotation {

	@XmlAttribute(name = "id", required = true)
	protected Integer id;
	@XmlAttribute(name = "type", required = true)
	protected String type;
	@XmlAttribute(name = "StartNode", required = true)
	protected Integer startNode;
	@XmlAttribute(name = "EndNode", required = true)
	protected Integer endNode;
	@XmlAttribute(name = "recommendation", required = true)
	protected String recommendation;

	public Annotation(){
		
	}
	
	public Annotation(int id, String type, int snode, int enode, String recom){
		this.id=id;
		this.type=type;
		this.startNode=snode;
		this.endNode=enode;
		this.recommendation=recom;
	}
	
	/**
	 * get the value of id.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Integer }
	 *     
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set the value of id.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Integer }
	 *     
	 */
	public void setId(Integer value) {
		this.id = value;
	}

	/**
	 * get the value of type.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getType() {
		return type;
	}

	/**
	 * set the value of type.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setType(String value) {
		this.type = value;
	}

	/**
	 * get the value of startNode.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Integer }
	 *     
	 */
	public Integer getStartNode() {
		return startNode;
	}

	/**
	 * set the value of startNode.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Integer }
	 *     
	 */
	public void setStartNode(Integer value) {
		this.startNode = value;
	}

	/**
	 * get the value of endNode.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Integer }
	 *     
	 */
	public Integer getEndNode() {
		return endNode;
	}

	/**
	 * set the value of endNode.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Integer }
	 *     
	 */
	public void setEndNode(Integer value) {
		this.endNode = value;
	}

	/**
	 * get the value of recommendation.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getRecommendation() {
		return recommendation;
	}

	/**
	 * set the value of recommendation.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setRecommendation(String value) {
		this.recommendation = value;
	}

	@Override
	public String toString() {
		return "Annotation_id=" + id + "[type=" + type + ", startNode="
				+ startNode + ", endNode=" + endNode + ", recommendation="
				+ recommendation + "]\n";
	}
	
	

}

