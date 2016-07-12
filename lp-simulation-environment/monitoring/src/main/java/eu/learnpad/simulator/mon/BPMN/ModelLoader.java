package eu.learnpad.simulator.mon.BPMN;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class ModelLoader {
	
	public static Document READMODEL(String modelURI) {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom = null;
		try {
			
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			dom = docBuilder.parse(modelURI);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return dom;
	}
}
