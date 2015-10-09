package eu.learnpad.ca.rest.data.stat;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import eu.learnpad.ca.rest.data.QualityCriteria;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"language",
    "staticContent",
    "qualityCriteria"
})
@XmlRootElement(name = "StaticContentAnalysis")
public class StaticContentAnalysis {

	@XmlAttribute(name = "language", required = true)
	protected String language;
    @XmlElement(name = "StaticContent", required = true)
    protected StaticContent staticContent;
    @XmlElement(name = "QualityCriteria", required = true)
    protected QualityCriteria qualityCriteria;
    
    /**
     * Get the value of language.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the value of language.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }
    
    /**
     * Get the value of staticContent.
     * 
     * @return
     *     possible object is
     *     {@link CollaborativeContent }
     *     
     */
    public StaticContent getStaticContent() {
        return staticContent;
    }

    /**
     * Set the value of staticContent.
     * 
     * @param value
     *     allowed object is
     *     {@link CollaborativeContent }
     *     
     */
    public void setStaticContent(StaticContent value) {
        this.staticContent = value;
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
		return "StaticContentAnalysis [staticContent="
				+ staticContent + ", qualityCriteria=" + qualityCriteria
				+ "]";
	}

    


}
