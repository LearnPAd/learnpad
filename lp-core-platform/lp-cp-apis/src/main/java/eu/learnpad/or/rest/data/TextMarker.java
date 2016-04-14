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
public class TextMarker {
    
    private int startPosition;
    private int lenght;

    public int getStartPosition() {
        return startPosition;
    }

    @XmlElement
    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getLenght() {
        return lenght;
    }

    @XmlElement
    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
    
}
