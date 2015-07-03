package eu.learnpad.ca.rest.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class QualityCriteria {

    @XmlAttribute(name = "simplicity")
    protected Boolean simplicity;
    @XmlAttribute(name = "non_ambiguity")
    protected Boolean nonAmbiguity;
    @XmlAttribute(name = "content_clarity")
    protected Boolean contentClarity;
    @XmlAttribute(name = "presentation_clarity")
    protected Boolean presentationClarity;
    @XmlAttribute(name = "completeness")
    protected Boolean completeness;
    @XmlAttribute(name = "correctness")
    protected Boolean correctness;

    /**
     * Get the value of simplicity.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSimplicity() {
        return simplicity;
    }

    /**
     * Set the value of simplicity.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSimplicity(Boolean value) {
        this.simplicity = value;
    }

    /**
     * Get the value of nonAmbiguity.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonAmbiguity() {
        return nonAmbiguity;
    }

    /**
     * Set the value of nonAmbiguity.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonAmbiguity(Boolean value) {
        this.nonAmbiguity = value;
    }

    /**
     * Get the value of contentClarity.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContentClarity() {
        return contentClarity;
    }

    /**
     * Set the value of contentClarity.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContentClarity(Boolean value) {
        this.contentClarity = value;
    }

    /**
     * Get the value of presentationClarity.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPresentationClarity() {
        return presentationClarity;
    }

    /**
     * Set the value of presentationClarity.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPresentationClarity(Boolean value) {
        this.presentationClarity = value;
    }

    /**
     * Get the value of completeness.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCompleteness() {
        return completeness;
    }

    /**
     * Set the value of completeness.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCompleteness(Boolean value) {
        this.completeness = value;
    }

    /**
     * Get the value of correctness.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCorrectness() {
        return correctness;
    }

    /**
     * Set the value of correctness.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCorrectness(Boolean value) {
        this.correctness = value;
    }

	@Override
	public String toString() {
		return "QualityCriteria [simplicity=" + simplicity + ", nonAmbiguity="
				+ nonAmbiguity + ", contentClarity=" + contentClarity
				+ ", presentationClarity=" + presentationClarity
				+ ", completeness=" + completeness + ", correctness="
				+ correctness + "]";
	}
    
    

}
