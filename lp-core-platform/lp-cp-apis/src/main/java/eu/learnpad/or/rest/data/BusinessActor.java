/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author sandro.emmenegger
 */
public class BusinessActor {
    
    private String uri;
    private String name;
    private String firstname;
    private String lastname;
    private String email;
    private String skypeId;
    private String phoneNumber;
    private String officeAddress;
    private OrganisationalUnit organisationalUnit;    
    private String role;
    private String description;
    private Boolean bookmark;
    
    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    @XmlElement
    public void setUri(String uri) {
        this.uri = uri;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    @XmlElement
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @XmlElement
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkypeId() {
        return skypeId;
    }

    @XmlElement
    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @XmlElement
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    @XmlElement
    public void setRole(String role) {
        this.role = role;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    @XmlElement
    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public OrganisationalUnit getOrganisationalUnit() {
        return organisationalUnit;
    }

    @XmlElement
    public void setOrganisationalUnit(OrganisationalUnit organisationalUnit) {
        this.organisationalUnit = organisationalUnit;
    }

    public String getDescription() {
        return description;
    }
    
    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getBookmark() {
        return bookmark;
    }

    @XmlElement
    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }    
}
