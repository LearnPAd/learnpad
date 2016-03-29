package eu.learnpad.or.rest.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "listofstringwrapper")
@XmlAccessorType (XmlAccessType.FIELD)
public class ListOfStringWrapper {

    @XmlElement(name = "itemlistofstring")
    private List<String> theList = null;
 
    public ListOfStringWrapper(){
    	this.theList = new ArrayList<String>();
    }
    
    public ListOfStringWrapper(String value){
    	this.theList = new ArrayList<String>();
    	this.theList.add(value);
    }

    public ListOfStringWrapper(List<String> theList){
    	this.theList = theList;
    }

    public List<String> getTheList() {
        return this.theList;
    }
 
    public void setTheList(List<String> theList) {
        this.theList = theList;
    }
}