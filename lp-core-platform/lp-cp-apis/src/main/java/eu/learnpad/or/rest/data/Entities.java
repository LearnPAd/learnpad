/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the root class encapsulating the result of a semantic text analyis looking for  
 * named entites (ex. persons) in an HTML text. 
 * It contains a field <analyzedContent>...</analyzedContent> which holds the 
 * processed HTML but with new SPAN elements around text phrases representing a named entity. 
 * (Ex. arround the name "Sally Shugar", a person/user in the system).
 * The SPAN element has an attribute "data-recommendation" pointing to the entity (Entity element) containg more details.
 * The list of entites (Entity elements) are also provide within this root element.
 * Each entity declares a field <id>...</id> which the SPAN element can refere to.
 * 
 * Ex. of content to analyze:
 * 
 * <html>
 *  <body>
 *      The activity <i>Organize Conference</i> specified by Sally Shugar should be <b>splitted</b> into 2 activities.
 *  </body>
 * </html>
 * 
 * Ex. of result after analyzes:
 * 
 * 
 * <entities>
 * <analyzedContent>The activity &lt;i&gt;Organize Conference&lt;/i&gt; specified by &lt;span data-recommendation=&quot;ea570b35-35b8-48f6-a25c-b6753da11b7a&quot;&gt;Sally Shugar&lt;/span&gt; should be &lt;b&gt;splitted&lt;/b&gt; into 2 activities.</analyzedContent>
 * <entity>
 *      <contextArtifactId>transfer:obj.35315</contextArtifactId>
 *      <id>ea570b35-35b8-48f6-a25c-b6753da11b7a</id>
 *       <person>
 *          <email>sally.shugar@learnpad.eu</email>
 *          <name>Sally Shugar</name>
 *          <officeAddress>Yellow drive 244b, East Juhee, Malta</officeAddress>
 *          <organisationalUnit>
 *              <name>SUAP Office</name>
 *              <uri>transfer:obj.122121</uri>
 *          </organisationalUnit>
 *          <phoneNumber>+234 23223 123</phoneNumber>
 *          <role>Responsible SUAP Officer</role>
 *          <skypeId>learnpad_sally</skypeId>
 *          <uri>transfer:obj.34872</uri>
 *      </person>
 *      <relatedObjects>
 *          <relatedObjects>
 *              <description>This self study book with learning material is the definitve guide to manage a team in public administration.</description>
 *              <documentUrl>http://learnpad.eu/material/PublicAdministrationABC.pdf</documentUrl>
 *              <mimeType>application/pdf</mimeType>
 *              <name>Management ABC for public administrations</name>
 *              <relationType>sameAuthor</relationType>
 *              <uri>transfer:obj.21321</uri>
 *          </relatedObjects>
 *          <relatedObjects>
 *              <description>A set of best practices with many hints for organizing a service conference.</description>
 *              <documentUrl>http://learnpad.eu/material/BestPracticesServiceConferenceOrganisatoin.pdf</documentUrl>
 *              <mimeType>application/pdf</mimeType>
 *              <name>Best practices for organizing a service conference</name>
 *              <uri>transfer:obj.21322</uri>
 *          </relatedObjects>
 *      </relatedObjects>
 *      <type>eo:Person</type>
 *  </entity>
 * </entities>
 * 
 * 
 * @author sandro.emmenegger
 */
@XmlRootElement
public class Entities {
    
    private List<Entity> entities = null;
    
    private String analyzedContent;

    public List<Entity> getEntities() {
        return entities;
    }

    @XmlElement(name="entity")
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public String getAnalyzedContent() {
        return analyzedContent;
    }

    public void setAnalyzedContent(String analyzedContent) {
        this.analyzedContent = analyzedContent;
    }

}
