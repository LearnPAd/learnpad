package eu.learnpad.simulator.processmanager.activiti.workarounds.msg;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Helper class to transform a BPMN file to a process supported by Activiti.
 *
 * Notably, the following tasks are not supported by Activiti, and are replaced
 * by a {@link JavaMsgTask}-backed Service task.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class BPMNTransformer {

	public static InputStream transform(InputStream input) throws SAXException,
			IOException, XPathExpressionException,
			ParserConfigurationException, TransformerConfigurationException,
			TransformerException, TransformerFactoryConfigurationError {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document doc = builder.parse(input);

		XPath xPath = XPathFactory.newInstance().newXPath();

		// replace sendTasks
		XPathExpression sendTaskexpr = xPath
				.compile("//*[local-name()='sendTask']");

		NodeList nodes = (NodeList) (sendTaskexpr.evaluate(doc,
				XPathConstants.NODESET));

		for (int i = 0; i < nodes.getLength(); i++) {
			Node oldNode = nodes.item(i);

			String prefix = oldNode.getNodeName().replace(
					oldNode.getLocalName(), "");
			String id = oldNode.getAttributes().getNamedItem("id")
					.getTextContent();
			String name = oldNode.getAttributes().getNamedItem("name")
					.getTextContent();
			String message = oldNode.getAttributes().getNamedItem("messageRef")
					.getTextContent();

			replaceNode(builder, doc, oldNode,
					createFragmentString(prefix, id, name, message), prefix);
		}

		// replace intermediate throw events
		XPathExpression throwEventexpr = xPath
				.compile("//*[local-name()='intermediateThrowEvent']");

		XPathExpression messageEventDefexpr = xPath
				.compile("//*[local-name()='messageEventDefinition']");

		nodes = (NodeList) (throwEventexpr
				.evaluate(doc, XPathConstants.NODESET));

		for (int i = 0; i < nodes.getLength(); i++) {
			Node oldNode = nodes.item(i);
			Node msgEvent = (Node) (messageEventDefexpr.evaluate(oldNode,
					XPathConstants.NODE));

			String prefix = oldNode.getNodeName().replace(
					oldNode.getLocalName(), "");

			String id = oldNode.getAttributes().getNamedItem("id")
					.getTextContent();

			String name = "";
			if (oldNode.getAttributes().getNamedItem("name") != null) {
				name = oldNode.getAttributes().getNamedItem("name")
						.getTextContent();
			}

			String messageId = msgEvent.getAttributes()
					.getNamedItem("messageRef").getTextContent();
			XPathExpression messageExpr = xPath
					.compile("//*[local-name()=\"message\" and @id=\""
							+ messageId + "\"]");

			System.out.println(((Node) (messageExpr.evaluate(doc,
					XPathConstants.NODE))));

			String message = ((Node) (messageExpr.evaluate(doc,
					XPathConstants.NODE))).getAttributes().getNamedItem("name")
					.getTextContent();

			replaceNode(builder, doc, oldNode,
					createFragmentString(prefix, id, name, message), prefix);
		}

		// convert back to inputstream and return
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(doc);
		Result outputTarget = new StreamResult(outputStream);
		TransformerFactory.newInstance().newTransformer()
				.transform(xmlSource, outputTarget);
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	private static final String createFragmentString(String prefix, String id,
			String name, String message) {
		return "<"
				+ prefix
				+ "serviceTask xmlns:activiti=\"http://activiti.org/bpmn\" id=\""
				+ id
				+ "\" name=\""
				+ name
				+ "\" activiti:class=\"eu.learnpad.simulator.processmanager.activiti.workarounds.msg.JavaMsgTask\""
				+ "><"
				+ prefix
				+ "extensionElements><activiti:field name=\"msg\" stringValue=\""
				+ message + "\"/></" + prefix + "extensionElements></" + prefix
				+ "serviceTask>";
	}

	private static void replaceNode(DocumentBuilder builder, Document doc,
			Node node, String xmlString, String prefix) throws SAXException,
			IOException {

		// due to the fact that the fragment may contain a prefix, we need to
		// "encapsulate" it in a parent node defining the prefix
		String fragment = "<root xmlns:"
				+ prefix.substring(0, prefix.length() - 1) + "=" + "\""
				+ node.getNamespaceURI() + "\">" + xmlString + "</root>";

		Document fragmentDoc = builder.parse(new ByteArrayInputStream(fragment
				.getBytes()));
		Node newNode = fragmentDoc.getFirstChild().getFirstChild();

		// TODO: OK I do not know why, but if I do not read the attributes
		// of the newly created node before "adopting" it, the values are
		// completely garbled.
		// If you now how to do this properly, please fix!
		newNode.getAttributes().getNamedItem("id").toString();
		newNode.getAttributes().getNamedItem("name").toString();
		newNode.getAttributes().getNamedItem("activiti:class").toString();
		newNode.getFirstChild().getFirstChild().getAttributes()
				.getNamedItem("name").toString();
		newNode.getFirstChild().getFirstChild().getAttributes()
				.getNamedItem("stringValue").toString();

		// replace in document
		doc.adoptNode(newNode);
		node.getParentNode().replaceChild(newNode, node);
	}

	public static void main(String[] args) throws XPathExpressionException,
			TransformerConfigurationException, MalformedURLException,
			SAXException, IOException, ParserConfigurationException,
			TransformerException, TransformerFactoryConfigurationError {
		String processDefinitionFilePath = "/home/jorquera/Documents/LearnPAd/integration/learnpad-tom/lp-simulation-environment/simulator/src/main/resources/process/collaboration_msg_with_events.bpmn20.xml";

		InputStream in = transform(new FileInputStream(new File(
				processDefinitionFilePath)));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			out.append(line);
		}
		System.out.println(out.toString()); // Prints the string content read
		// from input stream
		reader.close();
	}
}
