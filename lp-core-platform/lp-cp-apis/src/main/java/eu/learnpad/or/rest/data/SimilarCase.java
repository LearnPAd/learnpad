/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandro.emmenegger
 */
@XmlRootElement
public class SimilarCase {

    private String id; //uri
    private String name;
    private Double similarityValue;

    //case characterisation
    private String applicantName;
    private String applicationCity;
    private String[] applicationZones;
    private String applicationType;
    private String applicationSubtype;
    private String applicationPublicAdministration;
    private String[] applicationSectors;
    private String[] applicationBusinessActivities;
    private String applicationDescription;
    private String[] applicationATECOCategories;
    
    //case content
    private String applicationDecision;
    private Experts experts;
    private LearningMaterials learningMaterials;

    public String getId() {
        return id;
    }

    @XmlElement
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public Double getSimilarityValue() {
        return similarityValue;
    }

    @XmlElement
    public void setSimilarityValue(Double similarityValue) {
        this.similarityValue = similarityValue;
    }

    public String getApplicantName() {
        return applicantName;
    }

    @XmlElement
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicationCity() {
        return applicationCity;
    }

    @XmlElement
    public void setApplicationCity(String applicationCity) {
        this.applicationCity = applicationCity;
    }

    public String[] getApplicationZones() {
        return applicationZones;
    }

    @XmlElement
    public void setApplicationZones(String[] applicationZones) {
        this.applicationZones = applicationZones;
    }

    public String getApplicationType() {
        return applicationType;
    }

    @XmlElement
    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getApplicationSubtype() {
        return applicationSubtype;
    }

    @XmlElement
    public void setApplicationSubtype(String applicationSubtype) {
        this.applicationSubtype = applicationSubtype;
    }

    public String getApplicationPublicAdministration() {
        return applicationPublicAdministration;
    }

    @XmlElement
    public void setApplicationPublicAdministration(String applicationPublicAdministration) {
        this.applicationPublicAdministration = applicationPublicAdministration;
    }

    public String[] getApplicationSectors() {
        return applicationSectors;
    }

    @XmlElement
    public void setApplicationSectors(String[] applicationSectors) {
        this.applicationSectors = applicationSectors;
    }

    public String[] getApplicationBusinessActivities() {
        return applicationBusinessActivities;
    }

    @XmlElement
    public void setApplicationBusinessActivities(String[] applicationBusinessActivities) {
        this.applicationBusinessActivities = applicationBusinessActivities;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    @XmlElement
    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String[] getApplicationATECOCategories() {
        return applicationATECOCategories;
    }

    @XmlElement
    public void setApplicationATECOCategories(String[] applicationATECOCategories) {
        this.applicationATECOCategories = applicationATECOCategories;
    }

    public String getApplicationDecision() {
        return applicationDecision;
    }

    @XmlElement
    public void setApplicationDecision(String applicationDecision) {
        this.applicationDecision = applicationDecision;
    }

    public Experts getExperts() {
        return experts;
    }

    @XmlElement
    public void setExperts(Experts experts) {
        this.experts = experts;
    }

    public LearningMaterials getLearningMaterials() {
        return learningMaterials;
    }

    @XmlElement
    public void setLearningMaterials(LearningMaterials learningMaterials) {
        this.learningMaterials = learningMaterials;
    }
    
    
}
