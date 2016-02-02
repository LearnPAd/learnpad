/**
 * LearnPAd - Verification Component - Deadlock Check Plugin
 * 
 *  Copyright (C) 2015 Unicam
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Damiano Falcioni - Unicam <damiano.falcioni@gmail.com>
 */

package eu.learnpad.verification.plugin.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
  /*  public static Document getXmlDocFromURI(String xmlFile) throws Exception{
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
    
    public static String escapeXPathField(String field) {
        Matcher matcher = Pattern.compile("['\"]").matcher(field);
        StringBuilder buffer = new StringBuilder("concat(");
        int start = 0;
        while (matcher.find()) {
            buffer.append("'").append(field.substring(start, matcher.start())).append("',");
            buffer.append("'".equals(matcher.group()) ? "\"'\"," : "'\"',");
            start = matcher.end();
        }
        if (start == 0)
            return "'" + field + "'";
        return buffer.append("'").append(field.substring(start)).append("'").append(")").toString();
    }*/
    
    public static Object execXPath(org.w3c.dom.Node node, String pattern, QName xPathConstantsType) throws Exception{
         return XPathFactory.newInstance().newXPath().compile(pattern).evaluate(node, xPathConstantsType);
    }
}
