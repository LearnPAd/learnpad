/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Types of resources in wiki. 
 * 
 * @author sandro.emmenegger
 */
@XmlType
@XmlEnum
public enum ResourceType {
    PAGE, COMMENT, ATTACHEMENT, FEEDBACK
}
