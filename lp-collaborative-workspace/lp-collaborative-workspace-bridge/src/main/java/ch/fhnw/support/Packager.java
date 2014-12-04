package ch.fhnw.support;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class Packager {

	/**
	 * 
	 * @param array
	 * @param directory
	 * @throws Exception
	 */
	public static void packageCreator( String[] array , JFileChooser directory, String wikiName) throws Exception  {


		//real package creator
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.newDocument();
		
		//get package tag
		Element rootPackage = doc.createElement("package");
		doc.appendChild(rootPackage);

		Node infos = doc.createElement("infos");

		//empty self closing nodes
		Node name = doc.createElement("name");
		infos.appendChild(name);

		Node description = doc.createElement("description");
		infos.appendChild(description);

		Node licence = doc.createElement("licence");
		infos.appendChild(licence);

		Node author = doc.createElement("author");
		infos.appendChild(author);

		Node version = doc.createElement("version");
		infos.appendChild(version);

		Node backupPack = doc.createElement("backupPack");
		backupPack.setTextContent("0");
		infos.appendChild(backupPack);

		Node preserveVersion = doc.createElement("preserveVersion");
		preserveVersion.setTextContent("1");
		infos.appendChild(preserveVersion);

		//final append
		rootPackage.appendChild(infos);


		Node files = doc.createElement("files");
		rootPackage.appendChild(files);
		for(int i =0; i<array.length; i++){
			Element file =doc.createElement("file");
			file.setAttribute("defaultAction", "0");
			file.setAttribute("language", "");

			///////////////////// INSERT LIST OF FILES FOR XWIKI ///////////////////
			file.setTextContent(wikiName + "."+ array[i]);
			files.appendChild(file);
		}	

		prettyPrint(doc, directory, "package");

	}

	public static final void prettyPrint(Document xml, JFileChooser directory, String fileName) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		xml.setXmlStandalone(true);
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		Writer out = new StringWriter();
		tf.transform(new DOMSource(xml), new StreamResult(out));

		/// print the xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(xml);
		StreamResult result = new StreamResult(new File(directory.getCurrentDirectory().toString() + "\\xml\\"+ fileName + ".xml"));
		transformer.transform(source, result);
	}

}