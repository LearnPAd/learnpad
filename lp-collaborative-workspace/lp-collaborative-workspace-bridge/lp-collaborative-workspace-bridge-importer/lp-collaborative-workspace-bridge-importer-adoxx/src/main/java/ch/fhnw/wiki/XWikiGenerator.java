package ch.fhnw.wiki;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import ch.fhnw.support.AppZip;
import ch.fhnw.support.Packager;
import ch.fhnw.support.SpaceWebHome;
import ch.fhnw.usergroup.UserCreator;

public class XWikiGenerator {


	/**
	 * 
	 * @param directoryBPMN
	 * @param xwikiUser
	 * @param spaceName
	 * @return
	 * @throws Exception
	 */
	public static String xWikiGenerator(JFileChooser directoryBPMN, String xwikiUser, String spaceName) throws Exception{

		String[] arrayName = null;


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

		Document doc = docBuilder.parse(directoryBPMN.getSelectedFile());

		//Element adoXml = doc.getDocumentElement();
		//NodeList list = adoXml.getChildNodes();

		//Element model = (Element) doc.getElementsByTagName("ADOXML").item(0).getChildNodes().item(1).getChildNodes().item(1);

		NodeList models = doc.getElementsByTagName("MODEL");
		int arrayModelsLength = models.getLength();

		File createDirectory = new File(directoryBPMN.getCurrentDirectory().toString() + "\\xml\\" + spaceName);
		createDirectory.mkdirs();

		NodeList instances;
		NodeList connector;
		Element model;
		Element workingEnvironment;

		for(int t=0; t<arrayModelsLength; t++){

			model =(Element) models.item(t);
			workingEnvironment = (Element) models.item(t).getChildNodes();

			instances = workingEnvironment.getElementsByTagName("INSTANCE");
			connector = workingEnvironment.getElementsByTagName("CONNECTOR");

			// CORRECT BPMN FILE CONTROL
			if(model.getAttributes().getNamedItem("modeltype").toString().equals("modeltype=\"Working environment model\"")){

				System.out.println("USERS:");
				UserCreator.userCreator(workingEnvironment, directoryBPMN);

			}
			else if(model.getAttributes().getNamedItem("modeltype").toString().equals("modeltype=\"Document model\"")){

				System.out.println("DOCUMENTS:");
				System.out.println(Documents.DocumentGenerator(instances, spaceName, xwikiUser, directoryBPMN, createDirectory, doc, docFactory));

			}
			else if(model.getAttributes().getNamedItem("modeltype").toString().equals("modeltype=\"Business process diagram (BPMN 2.0)\"")){

				System.out.println("BPMN:\n");
				arrayName = BPMN.BPMNGenerator(instances, spaceName, xwikiUser, directoryBPMN, createDirectory, doc, docFactory);
				//PANELS GENERATOR
				//NodeList connector = instances.getElementsByTagName("CONNECTOR");
				System.out.println(Panels.PanelsGenerator(connector, directoryBPMN, xwikiUser) );


			}
			else if(model.getAttributes().getNamedItem("modeltype").toString().equals("modeltype=\"CMMN model\"")){

				System.out.println("CMMN:\n" + CMMN.CMMNGenerator() );

			}
			else if(model.getAttributes().getNamedItem("modeltype").toString().equals("modeltype=\"Sentry model\"")){

				System.out.println( "SENTRY MODEL:" );
				System.out.println("\n");
			}
			
			
		}

		//LAST STEP BEFORE THE PACKAGING OF THE XWIKI ARCHIVE
		Packager.packageCreator(arrayName, directoryBPMN, spaceName);
		SpaceWebHome.spaceCreator(directoryBPMN, spaceName);
		return AppZip.appZip(directoryBPMN);
	}
}
