package eu.learnpad.ca.rest.data.stat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import eu.learnpad.ca.rest.data.Content;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "title",
    "content"
})
public class StaticContent {

    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Content", required = true)
    protected Content content;
    @XmlAttribute(name = "id")
    protected String id;

    public StaticContent(){
    	
    }
    
    
    public StaticContent(String id, String title) {
		this.id = id;
		this.title = title;
	}


	/**
     * Get the value of title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Get the value of content.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Content getContent() {
        return content;
    }

    /**
     * Set the value of content.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(Content value) {
        this.content = value;
    }

    /**
     * Get the value of id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

	@Override
	public String toString() {
		return "StaticContent"+ ", id=" + id + " [title=" + title + ", content=" + content
				+ "]";
	}
    
    

}
