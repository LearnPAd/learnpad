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
    private String email;
    private String phoneNumber;
    private String role;
    private String description;

    
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

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
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

    public String getDescription() {
        return description;
    }
    
    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }
    
}
