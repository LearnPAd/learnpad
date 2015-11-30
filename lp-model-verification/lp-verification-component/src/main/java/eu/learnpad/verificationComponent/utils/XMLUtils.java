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

package eu.learnpad.verificationComponent.utils;

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
        TransformerFactory tf = TransformerFactory.newInstance("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl", null);
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
    }
    
    public static Object execXPath(org.w3c.dom.Node node, String pattern, QName xPathConstantsType) throws Exception{
         return XPathFactory.newInstance().newXPath().compile(pattern).evaluate(node, xPathConstantsType);
    }
    
    
    public static void main(String[] args){
        try {
            String xmlString = "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\" /><Reference URI=\"#SignedXmlId\"><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\" /><DigestValue>yUorMVTzafbYCCYB/N/75QSVDXs=</DigestValue></Reference></SignedInfo><SignatureValue>QF1Tpec48xkgEAhjz5auZh4dnMsHXa9gW/8C6PBQVqG6j9gM0/RzI3FJ4pcR8FPFsPtvMVsXB9VsJKQqtTEHhdHVa8epE2LSKMcH+00eXHaWA0nqBFFvn691IY32JzjQBekb7BcUS0SwrpAY96ZuhtIeXlQsDbGdvg22sNZcfjs=</SignatureValue><KeyInfo><X509Data><X509Certificate>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</X509Certificate></X509Data></KeyInfo><Object Id=\"SignedXmlId\"><profile timestamp=\"Tue, 26 Mar 2013 16:28:38 GMT\" xmlns=\"\"><base><titolo>Mr</titolo><nome>DAMIANO</nome><cognome>FALCIONI</cognome><sesso>M</sesso><login>FLCDMN85D05E783N</login><password>**************</password><pin>**************</pin><codice_fiscale>FLCDMN85D05E783N</codice_fiscale><telefono>0733203966</telefono><localita_nascita>MACERATA</localita_nascita><provincia_nascita>MC</provincia_nascita><cap_nascita /><regione_nascita /><data_nascita>05/04/1985</data_nascita><nazione_nascita /><gruppo>CNS</gruppo><ruolo>UTENTE</ruolo><email>damiano.falcioni@unicam.it</email><email_certificata>damiano.falcioni@postaraffaello.it</email_certificata><telefono_ufficio /><fax_ufficio /><numero_cellulare /><indirizzo_residenza>Pass. del Bidollo</indirizzo_residenza><localita_residenza>CORRIDONIA</localita_residenza><provincia_residenza>MC</provincia_residenza><cap_residenza>62014</cap_residenza><regione_residenza /><nazione_residenza /><professione /><settore_azienda /><profilo_familiare /><tipo_autenticazione>CF</tipo_autenticazione></base></profile></Object></Signature>";
            Document xmlDoc = getXmlDocFromString(xmlString);
            System.out.println(getStringFromXmlDoc(xmlDoc));
            //String certPath = "D:\\Universita\\Cohesion\\COHESION DA VEDERE\\SERVLET TEST\\apache-tomcat-7.0.27\\webapps\\CohesionServlet2\\cohesion2.cer";
            //boolean res = verifySignature(xmlDoc, certPath);
            //System.out.println(res);
            
            //executeXSL(new File("C:\\Users\\mio\\Desktop\\OWL BPMN2\\bpmnTest.xml"), new File("C:\\Users\\mio\\Desktop\\OWL BPMN2\\xml2rdf3.xsl"), new File("C:\\Users\\mio\\Desktop\\OWL BPMN2\\finale.rdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
