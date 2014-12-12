package ch.fhnw.wiki;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
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
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Panels {

	/**
	 * 
	 * @param connector
	 * @param directoryBPMN
	 * @param xwikiUser
	 * @return
	 * @throws Exception
	 */
	public static String PanelsGenerator(NodeList connector, JFileChooser directoryBPMN, String xwikiUser) throws Exception{


		DocumentBuilderFactory docFactoryCompany = DocumentBuilderFactory.newInstance();
		docFactoryCompany.setValidating(false);

		DocumentBuilder docBuilderCompany = docFactoryCompany.newDocumentBuilder();

		//resolver for DOCTYPE ADOXML SYSTEM
		docBuilderCompany.setEntityResolver(new EntityResolver()
		{
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException
					{
				return new InputSource(new StringReader(""));
					}
		});



		int arrayLength = connector.getLength();

		String documentName;
		String taskName;

		Element from;
		Element to;



		String contentFunction ="{{velocity}}"+
				"\n{{html wiki=\"true\"}}" +
				"\n#panelheader('RESOURCES FOR YOUR CONTEXT')";
		for(int i=0; i<arrayLength;i++){

			//i++;

			//get the info from the other file
			Element attributes = (Element) connector.item(i);



			from = (Element) attributes.getChildNodes().item(1);
			to = (Element) attributes.getChildNodes().item(3);

			documentName = to.getAttributes().getNamedItem("instance").toString().replaceFirst("instance=\"", "").replaceAll("\"", "");
			taskName = from.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "");

			if(attributes.getAttributes().getNamedItem("class").toString().equals("class=\"Data Association\"")){

				contentFunction = contentFunction +  
						"\n#if($doc == \"IMPORTED." + taskName + "\")" +
						"OUT:<br/>" +
						"\n<a href=\"" + documentName + "\"><span class=\"application-img\">image:http://www.iliumsoft.com/support/admin/images/gateway/shared-images/ico-pdf-export.png </span> " +
						"<span class=\"application-label\">" + documentName + "</a><br/>" +
						"\n#end" +
						"\n#if($doc == \"IMPORTED." + documentName + "\")" +
						"IN:<br/>" + 
						"\n<a href=\"" + taskName + "\"><span class=\"application-img\">image:http://www.iliumsoft.com/support/admin/images/gateway/shared-images/ico-pdf-export.png </span> " +
						"<span class=\"application-label\">" + taskName + "</a><br/>" +
						"\n#end" ;


			}
		}

		contentFunction = contentFunction + 
				"\n" +
				"\n#panelfooter()" +
				"\n" +
				"\n{{/html}}" +
				"\n{{/velocity}}";
		/*
		"{{velocity}}"+
		"\n{{html wiki=\"true\"}}" +
		"\n#panelheader('DOCUMENTS NEEDED')" + 
		"\n#if($doc == \"IMPORTED." + documentDescription + "\")" +
		"\n<a href=\"" + documentDescription + "\"><span class=\"application-img\">image:http://www.iliumsoft.com/support/admin/images/gateway/shared-images/ico-pdf-export.png </span> <span class=\"application-label\">" + documentDescription + "</a>" +
		"\n#else" + 
		"\nNESSUN DOCUMENTO COLLEGATO" +
		"\n#end" +
		"\n" +
		"\n#panelfooter()" +
		"\n" +
		"\n{{/html}}" +
		"\n{{/velocity}}"
		 */


		//real package creator
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		//String filePath = "c:\\xml\\package.xml";
		//File packageFile = new File(filePath);
		//	packageFile.createNewFile();


		Document newDocument = db.newDocument();
		//db.parse(new FileInputStream(new File(filePath)));

		//get package tag
		//Element rootPackage = newDocument.createElement("package");
		//newDocument.appendChild(rootPackage);

		//if (rootPackage.getChildNodes().getLength()==1){
		//System.out.println(rootPackage.getChildNodes().getLength());

		Element xwikidoc = newDocument.createElement("xwikidoc");
		xwikidoc.setAttribute("version", "1.1");
		newDocument.appendChild(xwikidoc);

		Element web = newDocument.createElement("web");
		web.setTextContent("Panels");
		xwikidoc.appendChild(web);


		Element name = newDocument.createElement("name");
		name.setTextContent("Context Resources");
		xwikidoc.appendChild(name);

		Element language = newDocument.createElement("language");
		xwikidoc.appendChild(language);

		Element defaultLanguage = newDocument.createElement("defaultLanguage");
		defaultLanguage.setTextContent("en");
		xwikidoc.appendChild(defaultLanguage);

		Element translation = newDocument.createElement("translation");
		translation.setTextContent("0");
		xwikidoc.appendChild(translation);

		Element creator = newDocument.createElement("creator");
		creator.setTextContent("XWiki." + xwikiUser);
		xwikidoc.appendChild(creator);

		Element creationDate = newDocument.createElement("creationDate");
		creationDate.setTextContent("1401899176000");
		xwikidoc.appendChild(creationDate);

		Element parent = newDocument.createElement("parent");
		parent.setTextContent("Panels.WebHome");
		xwikidoc.appendChild(parent);

		Element author = newDocument.createElement("author");
		author.setTextContent("XWiki." + xwikiUser);
		xwikidoc.appendChild(author);

		Element contentAuthor = newDocument.createElement("contentAuthor");
		contentAuthor.setTextContent("XWiki." + xwikiUser);
		xwikidoc.appendChild(contentAuthor);

		Element date = newDocument.createElement("date");
		date.setTextContent("1401899176000");
		xwikidoc.appendChild(date);

		Element contentUpdateDate = newDocument.createElement("contentUpdateDate");
		contentUpdateDate.setTextContent("1401899176000");
		xwikidoc.appendChild(contentUpdateDate);

		Element version = newDocument.createElement("version");
		version.setTextContent("1.1");
		xwikidoc.appendChild(version);

		Element title = newDocument.createElement("title");
		xwikidoc.appendChild(title);

		Element updateWatchlist = newDocument.createElement("comment");
		//updateWatchlist.setTextContent("Updated WatchList");
		xwikidoc.appendChild(updateWatchlist);

		Element minorEdit = newDocument.createElement("minorEdit");
		minorEdit.setTextContent("false");
		xwikidoc.appendChild(minorEdit);

		Element syntaxId = newDocument.createElement("syntaxId");
		syntaxId.setTextContent("xwiki/2.1");
		xwikidoc.appendChild(syntaxId);

		Element hidden = newDocument.createElement("hidden");
		hidden.setTextContent("false");
		xwikidoc.appendChild(hidden);

		Element content = newDocument.createElement("content");
		//content.setTextContent("{{include document=\"XWiki.XWikiUserSheet\"/}}");
		xwikidoc.appendChild(content);

		Element object = newDocument.createElement("object");
		////////////////////////////////////////xwikidoc.appendChild(object);

		Element nameObject = newDocument.createElement("name");
		nameObject.setTextContent("Panels." + "Context Resources");
		object.appendChild(nameObject);

		Element number = newDocument.createElement("number");
		number.setTextContent("0");
		object.appendChild(number);

		Element className = newDocument.createElement("className");
		className.setTextContent("Panels.PanelClass");
		object.appendChild(className);

		Element guid = newDocument.createElement("guid");
	//	guid.setTextContent("132f9db7-d133-4a1e-837b-0a4506931b32");
		object.appendChild(guid);

		Element classObject = newDocument.createElement("class");

		///////////////////object.appendChild(classObject);

		Element nameClass = newDocument.createElement("name");
		nameClass.setTextContent("Panels.PanelClass");
		classObject.appendChild(nameClass);

		Element customClassObject = newDocument.createElement("customClass");
		classObject.appendChild(customClassObject);

		Element customMapping = newDocument.createElement("customMapping");
		classObject.appendChild(customMapping);

		Element defaultViewSheet = newDocument.createElement("defaultViewSheet");
		classObject.appendChild(defaultViewSheet);

		Element defaultEditSheet = newDocument.createElement("defaultEditSheet");
		classObject.appendChild(defaultEditSheet);

		Element defaultWeb = newDocument.createElement("defaultWeb");
		classObject.appendChild(defaultWeb);

		Element nameField = newDocument.createElement("nameField");
		classObject.appendChild(nameField);

		Element validationScriptObject = newDocument.createElement("validationScript");
		classObject.appendChild(validationScriptObject);


		Element category = newDocument.createElement("category");
		////////////////

		Element cache = newDocument.createElement("cache");
		cache.setTextContent("0");
		category.appendChild(cache);

		Element disabled = newDocument.createElement("disabled");
		disabled.setTextContent("0");
		category.appendChild(disabled);

		Element displayType = newDocument.createElement("displayType");
		displayType.setTextContent("select");
		category.appendChild(displayType);

		Element multiSelect = newDocument.createElement("multiSelect");
		multiSelect.setTextContent("0");
		category.appendChild(multiSelect);

		Element nameWatch = newDocument.createElement("name");
		nameWatch.setTextContent("category");
		category.appendChild(nameWatch);

		Element numberWatch = newDocument.createElement("number");
		numberWatch.setTextContent("5");
		category.appendChild(numberWatch);

		Element prettyName = newDocument.createElement("prettyName");
		prettyName.setTextContent("Category");
		category.appendChild(prettyName);

		Element relationalStorage = newDocument.createElement("relationalStorage");
		relationalStorage.setTextContent("0");
		category.appendChild(relationalStorage);

		Element separator = newDocument.createElement("separator");
		separator.setTextContent(" ");
		category.appendChild(separator);

		Element separators = newDocument.createElement("separators");
		separators.setTextContent(" ,|");
		category.appendChild(separators);

		Element size = newDocument.createElement("size");
		size.setTextContent("1");
		category.appendChild(size);

		Element unmodifiable = newDocument.createElement("unmodifiable");
		unmodifiable.setTextContent("0");
		category.appendChild(unmodifiable);

		Element values = newDocument.createElement("values");
		values.setTextContent("Information|Navigation|Tools|Administration|Other");
		category.appendChild(values);

		Element classType = newDocument.createElement("classType");
		classType.setTextContent("com.xpn.xwiki.objects.classes.StaticListClass");
		category.appendChild(classType);

		classObject.appendChild(category);

		Element content1 = newDocument.createElement("content");
		/////////////

		Element disabledDocuments = newDocument.createElement("disabled");
		disabledDocuments.setTextContent("0");
		content1.appendChild(disabledDocuments);

		Element editor = newDocument.createElement("editor");
		editor.setTextContent("Text");
		content1.appendChild(editor);

		Element contentName = newDocument.createElement("name");
		contentName.setTextContent("content");
		content1.appendChild(contentName);

		Element numberDocuments = newDocument.createElement("number");
		numberDocuments.setTextContent("4");
		content1.appendChild(numberDocuments);

		Element prettyNameDocuments = newDocument.createElement("prettyName");
		prettyNameDocuments.setTextContent("Content");
		content1.appendChild(prettyNameDocuments);

		Element rows = newDocument.createElement("rows");
		rows.setTextContent("25");
		content1.appendChild(rows);

		Element sizeDocument = newDocument.createElement("size");
		sizeDocument.setTextContent("120");
		content1.appendChild(sizeDocument);

		Element unmodifiableDocument = newDocument.createElement("unmodifiable");
		unmodifiableDocument.setTextContent("0");
		content1.appendChild(unmodifiableDocument);

		Element classTypeDocument = newDocument.createElement("classType");
		classTypeDocument.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
		content1.appendChild(classTypeDocument);

		classObject.appendChild(content1);

		Element description = newDocument.createElement("description");

		Element disabledDescription = newDocument.createElement("disabled");
		disabledDescription.setTextContent("0");
		description.appendChild(disabledDescription);

		Element editorDescription = newDocument.createElement("editor");
		editorDescription.setTextContent("Text");
		description.appendChild(editorDescription);

		Element nameDescription = newDocument.createElement("name");
		nameDescription.setTextContent("description");
		description.appendChild(nameDescription);

		Element numberDescription = newDocument.createElement("number");
		numberDescription.setTextContent("3");
		description.appendChild(numberDescription);

		Element prettyNameDescription = newDocument.createElement("prettyName");
		prettyNameDescription.setTextContent("Description");
		description.appendChild(prettyNameDescription);

		Element rowsDescription = newDocument.createElement("rows");
		rowsDescription.setTextContent("5");
		description.appendChild(rowsDescription);

		Element sizeDescription = newDocument.createElement("size");
		sizeDescription.setTextContent("40");
		description.appendChild(sizeDescription);

		Element unmodifiableDescription = newDocument.createElement("unmodifiable");
		unmodifiableDescription.setTextContent("0");
		description.appendChild(unmodifiableDescription);

		Element classTypeInterval = newDocument.createElement("classType");
		classTypeInterval.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
		description.appendChild(classTypeInterval);

		classObject.appendChild(description);

		Element name2 = newDocument.createElement("name");

		Element disabledName = newDocument.createElement("disabled");
		disabledName.setTextContent("0");
		name2.appendChild(disabledName);

		Element nameName = newDocument.createElement("name");
		nameName.setTextContent("name");
		name2.appendChild(nameName);

		Element numberName = newDocument.createElement("number");
		numberName.setTextContent("1");
		name2.appendChild(numberName);

		Element prettyNameName = newDocument.createElement("prettyName");
		prettyNameName.setTextContent("Name");
		name2.appendChild(prettyNameName);

		Element sizeName = newDocument.createElement("size");
		sizeName.setTextContent("40");
		name2.appendChild(sizeName);

		Element unmodifiableName = newDocument.createElement("unmodifiable");
		unmodifiableName.setTextContent("0");
		name2.appendChild(unmodifiableName);

		Element classTypeName = newDocument.createElement("classType");
		classTypeName.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
		name2.appendChild(classTypeName);

		classObject.appendChild(name2);

		Element type = newDocument.createElement("type");

		Element cacheType = newDocument.createElement("cache");
		cacheType.setTextContent("0");
		type.appendChild(cacheType);

		Element disabledType = newDocument.createElement("disabled");
		disabledType.setTextContent("0");
		type.appendChild(disabledType);

		Element displayTypeType = newDocument.createElement("displayType");
		displayTypeType.setTextContent("select");
		type.appendChild(displayTypeType);

		Element multiSelectType = newDocument.createElement("multiSelect");
		multiSelectType.setTextContent("0");
		type.appendChild(multiSelectType);

		Element nameType = newDocument.createElement("name");
		nameType.setTextContent("type");
		type.appendChild(nameType);

		Element numberType = newDocument.createElement("number");
		numberType.setTextContent("2");
		type.appendChild(numberType);

		Element prettyNameUsers = newDocument.createElement("prettyName");
		prettyNameUsers.setTextContent("Panel type");
		type.appendChild(prettyNameUsers);

		Element relationalStorageType = newDocument.createElement("relationalStorage");
		relationalStorageType.setTextContent("0");
		type.appendChild(relationalStorageType);

		Element separatorType = newDocument.createElement("separator");
		separatorType.setTextContent(" ");
		type.appendChild(separatorType);

		Element separatorsType = newDocument.createElement("separators");
		separatorsType.setTextContent(" ,|");
		type.appendChild(separatorsType);

		Element sizeType = newDocument.createElement("size");
		sizeType.setTextContent("1");
		type.appendChild(sizeType);

		Element unmodifiableType = newDocument.createElement("unmodifiable");
		unmodifiableType.setTextContent("0");
		type.appendChild(unmodifiableType);

		Element valuesType = newDocument.createElement("values");
		valuesType.setTextContent("view|edit");
		type.appendChild(valuesType);

		Element classTypeType = newDocument.createElement("classType");
		classTypeType.setTextContent("com.xpn.xwiki.objects.classes.StaticListClass");
		type.appendChild(classTypeType);

		classObject.appendChild(type);

		object.appendChild(classObject);


		Element property = newDocument.createElement("property");

		Element categoryProperty = newDocument.createElement("category");
		categoryProperty.setTextContent("Information");
		property.appendChild(categoryProperty);

		object.appendChild(property);

		Element property2 = newDocument.createElement("property");

		Element contentProperty = newDocument.createElement("content");
		contentProperty.setTextContent(contentFunction);
		property2.appendChild(contentProperty);

		object.appendChild(property2);


		Element property3 = newDocument.createElement("property");

		Element descriptionProperty = newDocument.createElement("description");
		descriptionProperty.setTextContent("Resources needed for the LearnPAd System");
		property3.appendChild(descriptionProperty);

		object.appendChild(property3);

		Element property4 = newDocument.createElement("property");

		Element nameProperty = newDocument.createElement("name");
		nameProperty.setTextContent("Context Resources");
		property4.appendChild(nameProperty);

		object.appendChild(property4);

		Element property5 = newDocument.createElement("property");

		Element typeProperty = newDocument.createElement("type");
		typeProperty.setTextContent("view");
		property5.appendChild(typeProperty);

		object.appendChild(property5);


		// chiusura del file
		xwikidoc.appendChild(object);


		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		newDocument.setXmlStandalone(true);
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		Writer out = new StringWriter();
		tf.transform(new DOMSource(newDocument), new StreamResult(out));

		//System.out.println(out.toString());

		/// print the xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(newDocument);
		File dir = new File(directoryBPMN.getCurrentDirectory().toString() + "\\xml\\panels\\");
		dir.mkdir();
		StreamResult result = new StreamResult(new File(directoryBPMN.getCurrentDirectory().toString() + "\\xml\\panels\\"+ "Context Resources" + ".xml"));
		transformer.transform(source, result);


		//}



		return "\nResource Panel has been generated. Please access the Administration interface to activate it.\n";
	}

}
