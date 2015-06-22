package eu.learnpad.ca.rest.data.collaborative;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import eu.learnpad.ca.rest.data.QualityCriteria;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "collaborativeContent",
    "qualityCriteria"
})
@XmlRootElement(name = "CollaborativeContentAnalysis")
public class CollaborativeContentAnalysis {

    @XmlElement(name = "CollaborativeContent", required = true)
    protected CollaborativeContent collaborativeContent;
    @XmlElement(name = "QualityCriteria", required = true)
    protected QualityCriteria qualityCriteria;

    /**
     * Get the value of collaborativeContent.
     * 
     * @return
     *     possible object is
     *     {@link CollaborativeContent }
     *     
     */
    public CollaborativeContent getCollaborativeContent() {
        return collaborativeContent;
    }

    /**
     * Set the value of collaborativeContent.
     * 
     * @param value
     *     allowed object is
     *     {@link CollaborativeContent }
     *     
     */
    public void setCollaborativeContent(CollaborativeContent value) {
        this.collaborativeContent = value;
    }

    /**
     * Get the value of qualityCriteria.
     * 
     * @return
     *     possible object is
     *     {@link QualityCriteria }
     *     
     */
    public QualityCriteria getQualityCriteria() {
        return qualityCriteria;
    }

    /**
     * Set the value of qualityCriteria.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityCriteria }
     *     
     */
    public void setQualityCriteria(QualityCriteria value) {
        this.qualityCriteria = value;
    }

	@Override
	public String toString() {
		return "CollaborativeContentAnalysis [collaborativeContent="
				+ collaborativeContent + ", qualityCriteria=" + qualityCriteria
				+ "]";
	}

    


}
