package eu.learnpad.ca.rest.data.stat;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import eu.learnpad.ca.rest.data.Annotation;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AnnotatedStaticContentAnalyses")
public class AnnotatedStaticContentAnalyses {
	
	@XmlElement(required = true)
    protected List<AnnotatedStaticContentAnalysis> AnnotatedStaticContentAnalyses;

	
	
    /**
     * Gets the value of the annotateStaticContentAnalysis property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotatedStaticContentAnalyses.AnnotateStaticContentAnalysis }
     *     
     */
    public List<AnnotatedStaticContentAnalysis> getAnnotateStaticContentAnalysis() {
    	if(AnnotatedStaticContentAnalyses==null){
    		AnnotatedStaticContentAnalyses = new ArrayList<AnnotatedStaticContentAnalysis>();
    	}
        return AnnotatedStaticContentAnalyses;
    }

  
    public void setAnnotateStaticContentAnalysis(AnnotatedStaticContentAnalysis value) {
    	if(AnnotatedStaticContentAnalyses==null){
    		AnnotatedStaticContentAnalyses = new ArrayList<AnnotatedStaticContentAnalysis>();
    	}
        this.AnnotatedStaticContentAnalyses.add(value);
    }
    
    

}
