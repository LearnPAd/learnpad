package ch.fhnw.wiki;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class StartEvent {


	/**
	 *  Search for the start event in the list of all the objects of the BPMN diagram
	 * @param instances the list of all the objects in the process
	 * @return The name of the Start Event
	 */
	public static String startEventSearcher(Document doc){


		NodeList instances = doc.getElementsByTagName("CONNECTOR");

		int arrayLength = instances.getLength();

		Element from;
		String connectorName;
		for (int i=0; i<arrayLength; i++){


			//get the info from the other file
			Element connector = (Element) instances.item(i);
			connectorName = connector.getAttributes().getNamedItem("class").toString();

			if (connectorName.equals("class=\"Subsequent\""))
			{
				//the element n°1 of the NodeList is the link to the next object of the process
				from = (Element) connector.getChildNodes().item(1);

				if (from.getAttributes().getNamedItem("class").toString().replaceFirst("class=\"", "").replaceAll("\"", "").equals("Start Event")){

					//return the name of the start event
					return from.getAttributes().getNamedItem("instance").toString().replaceFirst("instance=\"", "").replaceAll("\"", "");
				}
			}	
		}
		return "";
	}
	
	
	public static String startEventGenerator(JFileChooser directory, String xwikiUser, Document doc, String spaceName){

		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setValidating(false);

			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			//resolver for DOCTYPE ADOXML SYSTEM
			docBuilder.setEntityResolver(new EntityResolver()
			{
				public InputSource resolveEntity(String publicId, String systemId)
						throws SAXException, IOException
						{
					return new InputSource(new StringReader(""));
						}
			});


			// DIRECTORY CREATOR 
			File createDirectory = new File(directory.getCurrentDirectory().toString() + "\\xml\\" + spaceName);


			// generating the new xml page file for xwiki
			DocumentBuilderFactory docFactoryFinale = DocumentBuilderFactory.newInstance();
			docFactory.setValidating(false);
			DocumentBuilder docBuilderFinale = docFactoryFinale.newDocumentBuilder();

			Document docFinale = docBuilderFinale.newDocument();

			Element xwikidoc = docFinale.createElement("xwikidoc");
			xwikidoc.setAttribute("version", "1.1");
			docFinale.appendChild(xwikidoc);

			Element web = docFinale.createElement("web");
			web.setTextContent(spaceName);
			xwikidoc.appendChild(web);

			Element name = docFinale.createElement("name");
			name.setTextContent(directory.getSelectedFile().getName().replaceAll(".xml", ""));
			xwikidoc.appendChild(name);

			Element language = docFinale.createElement("language");
			xwikidoc.appendChild(language);

			Element defaultLanguage = docFinale.createElement("defaultLanguage");
			defaultLanguage.setTextContent("en");
			xwikidoc.appendChild(defaultLanguage);

			Element translation = docFinale.createElement("translation");
			translation.setTextContent("0");
			xwikidoc.appendChild(translation);

			//TODO chance to insert the user based on an existing user of xwiki
			Element creator = docFinale.createElement("creator");
			creator.setTextContent("xwiki:XWiki.Admin");
			xwikidoc.appendChild(creator);

			Element creationDate = docFinale.createElement("creationDate");
			creationDate.setTextContent("139682179000");
			xwikidoc.appendChild(creationDate);

			// connection to a generic file which explain the process
			Element parent = docFinale.createElement("parent");
			parent.setTextContent(spaceName + "." + directory.getSelectedFile().getName().replaceAll(".xml", ""));
			xwikidoc.appendChild(parent);

			// insert the user based on an existing user of xwiki
			Element author = docFinale.createElement("author");
			author.setTextContent("xwiki:XWiki." + xwikiUser);
			xwikidoc.appendChild(author);

			Element customClass = docFinale.createElement("customClass");
			xwikidoc.appendChild(customClass);

			//ADMIN USER INSERTED BY THE USER
			Element contentAuthor = docFinale.createElement("contentAuthor");
			contentAuthor.setTextContent("xwiki:XWiki." + xwikiUser);
			xwikidoc.appendChild(contentAuthor);

			Element date = docFinale.createElement("date");
			date.setTextContent("139705438000");
			xwikidoc.appendChild(date);

			Element contentUpdateDate = docFinale.createElement("contentUpdateDate");
			contentUpdateDate.setTextContent("139705438000");
			xwikidoc.appendChild(contentUpdateDate);

			Element version = docFinale.createElement("version");
			version.setTextContent("1.1");
			xwikidoc.appendChild(version);

			// web page title
			Element title = docFinale.createElement("title");
			title.setTextContent(directory.getSelectedFile().getName().replaceAll(".xml", ""));
			xwikidoc.appendChild(title);

			Element defaultTemplate = docFinale.createElement("defaultTemplate");
			xwikidoc.appendChild(defaultTemplate);

			//Element validationScript = docFinale.createElement("validationScript");
			//	xwikidoc.appendChild(validationScript);

			//TODO Analyze the comment structure
			Element comment2 = docFinale.createElement("comment");
			//
			xwikidoc.appendChild(comment2);

			Element minorEdit2 = docFinale.createElement("minorEdit");
			xwikidoc.appendChild(minorEdit2);

			Element syntaxId = docFinale.createElement("syntaxId");
			syntaxId.setTextContent("xwiki/2.1");
			xwikidoc.appendChild(syntaxId);

			Element hidden = docFinale.createElement("hidden");
			xwikidoc.appendChild(hidden);

			Element content = docFinale.createElement("content");
			xwikidoc.appendChild(content);

			// ATTACHMENT
			/*
			Element attachment = docFinale.createElement("attachment");
			xwikidoc.appendChild(attachment);

			Element fileName = docFinale.createElement("filename");
			fileName.setTextContent("image013.jpg");
			attachment.appendChild(fileName);

			Element fileAuthor = docFinale.createElement("author");
			fileAuthor.setTextContent(spaceName + ".Admin");
			attachment.appendChild(fileAuthor);

			Element fileDate = docFinale.createElement("date");
			fileDate.setTextContent("1396892513000");
			attachment.appendChild(fileDate);

			Element fileVersion = docFinale.createElement("version");
			fileVersion.setTextContent("1.1");
			attachment.appendChild(fileVersion);

			Element fileComment = docFinale.createElement("comment");
			attachment.appendChild(fileComment);

			Element fileContent = docFinale.createElement("content");
			fileContent.setTextContent("");
			attachment.appendChild(fileContent);

			Element fileSize = docFinale.createElement("filesize");
			fileSize.setTextContent("79279");
			attachment.appendChild(fileSize);
			 */

			//TODO DAFARE
			// INSERT OF THE CONTENT, OBJECT DESCRIPTION FROM THE ADOXX FILE
			//String contenuto = new String();
			String adoXmlFile = StartEvent.startEventSearcher(doc);

			content.setTextContent("**Generic Description of the Process:**\n\n\n"
					+ "__Link to the First Step:__" + "{{html wiki=true}}<br/><br/>"
					+ "\n<form action=\"" + adoXmlFile + "\">"+
					"\n<input type=\"submit\" value=\"First Activity\">" +
					"\n</form>"+
					"\n{{/html}}"
						//	+ "<button type=\"button\"><a href=\"" + adoXmlFile + "\">First Activity --></a></button>" 
					//+ contenuto
					);

			// Write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			docFinale.setXmlStandalone(true);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			DOMSource source = new DOMSource(docFinale);

			StreamResult result = new StreamResult(createDirectory + "\\"+ directory.getSelectedFile().getName().replaceAll(".xml", "") + ".xml");
			transformer.transform(source, result);
			return directory.getSelectedFile().getName().replaceAll(".xml", "");


		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		return directory.getSelectedFile().getName().replaceAll(".xml", "");
	}
	
}
