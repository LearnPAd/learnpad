package eu.learnpad.verification.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtils {
	
	public static Document getXmlDocFromString(String xml) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		builder.setEntityResolver(new EntityResolver() {
	            @Override
	            public InputSource resolveEntity(String publicId, String systemId)
	                    throws SAXException, IOException {
	                return new InputSource(new StringReader(""));
	            }
	        });
		return builder.parse(new ByteArrayInputStream(xml.getBytes()));
	}
	
	public static Document getXmlDocFromURI(String xmlFile) throws Exception{
		if(xmlFile.startsWith("http"))
			return getXmlDocFromURI(new URL(xmlFile).openStream());
		else
			return getXmlDocFromURI(new FileInputStream(new File(xmlFile)));
	}
	
	public static Document getXmlDocFromURI(InputStream is) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		builder.setEntityResolver(new EntityResolver() {
	            @Override
	            public InputSource resolveEntity(String publicId, String systemId)
	                    throws SAXException, IOException {
	                return new InputSource(new StringReader(""));
	            }
	        });
		return builder.parse(is);
	}
	
	public static String getStringFromXmlDoc(org.w3c.dom.Node node) throws Exception{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(node), new StreamResult(writer));
		return writer.getBuffer().toString().replaceAll("\n|\r", "");
    }
	
	public static Document createNewDocument() throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		//dbf.setIgnoringElementContentWhitespace(true);
		return dbf.newDocumentBuilder().newDocument();
	}
	
	public static String escapeXMLField(String field){
		if(field.contains("&")){
			int index = 0;
			do{
				index = field.indexOf("&", index);
				if(index != -1 && !field.substring(index).startsWith("&amp;"))
					field = field.substring(0, index) + "&amp;" + field.substring(index + 1, field.length());
				if(index != -1)
					index++;
			}while(index!=-1);
		}
		field = field.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
		return field;
	}
	
	public static Object execXPath(org.w3c.dom.Node node, String pattern, QName xPathConstantsType) throws Exception{
 		return XPathFactory.newInstance().newXPath().compile(pattern).evaluate(node, xPathConstantsType);
	}
}
