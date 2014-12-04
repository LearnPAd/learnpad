package ch.fhnw.usergroup;

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

public class GroupCreator {

	public static void groupCreator(NodeList workingEnvironment, JFileChooser directoryCompany, String[] arrayFrom, String[] arrayTo) throws Exception {


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

		//Document docCompany = docBuilderCompany.parse(directoryCompany.getSelectedFile());


		//NodeList instancesCompany = docCompany.getElementsByTagName("INSTANCE");

		//int arrayLength = instancesCompany.getLength();
		//String[] arrayName = new String[arrayLength+1];

		int arrayLength = arrayTo.length;

		//String oldName;
		//String nomeCorretto;
		//String type;

		//String role;
		//String roleName;


		for(int i=0; i<arrayLength;i++){


			//if(arrayTo[i].equals(null)){
			//get the info from the other file
			//			Node attributes = instancesCompany.item(i);				
			////////////////////////////////////////////////		
			//			Element eElement = (Element) attributes;

			//			role = attributes.getAttributes().getNamedItem("class").toString().replaceFirst("class=\"", "").replaceAll("\"", "");

			//			System.out.println(attributes.getAttributes().getNamedItem("class").toString().replaceFirst("class=\"", "").replaceAll("\"", "")
			//					+ " " + attributes.getAttributes().getNamedItem("name").toString().replaceFirst("name=\"", "").replaceAll("\"", ""));

			//			roleName = attributes.getAttributes().getNamedItem("name").toString().replaceFirst("name=\"", "").replaceAll("\"", "");

			//if(role.equals("Role")){





			//				NodeList elementi = eElement.getElementsByTagName("ATTRIBUTE");
			////////////////////////////////////////////////
			//Node attributes = instances.item(i);

			//				type = attributes.getAttributes().getNamedItem("class").toString();
			//				type = type.replaceFirst("class=\"", "").replaceAll("\"", "");


			//				oldName = attributes.getAttributes().getNamedItem("name").toString().replaceFirst("name=\"", "").replaceAll("\"", "");
			//TODO it miss the *
			//				nomeCorretto = oldName.replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");
			//				arrayName[i] = nomeCorretto;



			// Get the root element

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
			web.setTextContent("XWiki");
			xwikidoc.appendChild(web);

			Element name = newDocument.createElement("name");
			name.setTextContent(arrayTo[i]);
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
			creator.setTextContent("XWiki.Admin");
			xwikidoc.appendChild(creator);

			Element creationDate = newDocument.createElement("creationDate");
			creationDate.setTextContent("1401899176000");
			xwikidoc.appendChild(creationDate);

			Element parent = newDocument.createElement("parent");
			parent.setTextContent("XWiki.XWikiGroups");
			xwikidoc.appendChild(parent);

			Element author = newDocument.createElement("author");
			author.setTextContent("XWiki.Admin");
			xwikidoc.appendChild(author);

			//		Element customClass = newDocument.createElement("customClass");
			//		xwikidoc.appendChild(customClass);

			Element contentAuthor = newDocument.createElement("contentAuthor");
			contentAuthor.setTextContent("XWiki.Admin");
			xwikidoc.appendChild(contentAuthor);

			Element date = newDocument.createElement("date");
			date.setTextContent("1401899176000");
			xwikidoc.appendChild(date);

			Element contentUpdateDate = newDocument.createElement("contentUpdateDate");
			contentUpdateDate.setTextContent("1401899176000");
			xwikidoc.appendChild(contentUpdateDate);

			Element version = newDocument.createElement("version");
			version.setTextContent("6.1");
			xwikidoc.appendChild(version);

			Element title = newDocument.createElement("title");
			xwikidoc.appendChild(title);

			//	Element defaultTemplate = newDocument.createElement("defaultTemplate");
			//		xwikidoc.appendChild(defaultTemplate);

			///	Element validationScript = newDocument.createElement("validationScript");
			///	xwikidoc.appendChild(validationScript);

			Element comment = newDocument.createElement("comment");
			//comment.setTextContent("Updated WatchList");
			xwikidoc.appendChild(comment);

			Element minorEdit = newDocument.createElement("minorEdit");
			minorEdit.setTextContent("false");
			xwikidoc.appendChild(minorEdit);

			Element syntaxId = newDocument.createElement("syntaxId");
			syntaxId.setTextContent("xwiki/2.0");
			xwikidoc.appendChild(syntaxId);

			Element hidden = newDocument.createElement("hidden");
			hidden.setTextContent("false");
			xwikidoc.appendChild(hidden);

			Element content = newDocument.createElement("content");
			content.setTextContent("{{include document=\"XWiki.XWikiUserSheet\"/}}");
			xwikidoc.appendChild(content);

			Element object = newDocument.createElement("object");



			Element nameObject = newDocument.createElement("name");
			nameObject.setTextContent("XWiki." + arrayTo[i]);
			object.appendChild(nameObject);

			Element number = newDocument.createElement("number");
			number.setTextContent("0");
			object.appendChild(number);

			Element className = newDocument.createElement("className");
			className.setTextContent("XWiki.XWikiGroups");
			object.appendChild(className);

			Element guid = newDocument.createElement("guid");
			guid.setTextContent("d2304211-9748-4287-a5ca-5db6114bbadd");
			object.appendChild(guid);

			Element classObject = newDocument.createElement("class");

			Element nameClass = newDocument.createElement("name");
			nameClass.setTextContent("XWiki.XWikiGroups");
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
			/*
				Element automaticwatch = newDocument.createElement("automaticwatch");
				////////////////

				Element cache = newDocument.createElement("cache");
				cache.setTextContent("0");
				automaticwatch.appendChild(cache);

				Element disabled = newDocument.createElement("disabled");
				disabled.setTextContent("0");
				automaticwatch.appendChild(disabled);

				Element displayType = newDocument.createElement("displayType");
				displayType.setTextContent("select");
				automaticwatch.appendChild(displayType);

				Element multiSelect = newDocument.createElement("multiSelect");
				multiSelect.setTextContent("0");
				automaticwatch.appendChild(multiSelect);

				Element nameWatch = newDocument.createElement("name");
				nameWatch.setTextContent("automaticwatch");
				automaticwatch.appendChild(nameWatch);

				Element numberWatch = newDocument.createElement("number");
				numberWatch.setTextContent("6");
				automaticwatch.appendChild(numberWatch);

				Element prettyName = newDocument.createElement("prettyName");
				prettyName.setTextContent("Automatic watching");
				automaticwatch.appendChild(prettyName);

				Element relationalStorage = newDocument.createElement("relationalStorage");
				relationalStorage.setTextContent("0");
				automaticwatch.appendChild(relationalStorage);

				Element separator = newDocument.createElement("separator");
				separator.setTextContent(" ");
				automaticwatch.appendChild(separator);

				Element separators = newDocument.createElement("separators");
				separators.setTextContent(" ,|");
				automaticwatch.appendChild(separators);

				Element size = newDocument.createElement("size");
				size.setTextContent("1");
				automaticwatch.appendChild(size);

				Element unmodifiable = newDocument.createElement("unmodifiable");
				unmodifiable.setTextContent("0");
				automaticwatch.appendChild(unmodifiable);

				Element values = newDocument.createElement("values");
				values.setTextContent("default|NONE|ALL|MAJOR|NEW");
				automaticwatch.appendChild(values);

				Element classType = newDocument.createElement("classType");
				classType.setTextContent("com.xpn.xwiki.objects.classes.StaticListClass");
				automaticwatch.appendChild(classType);

				classObject.appendChild(automaticwatch);

			 */


			/*
				Element documents = newDocument.createElement("member");

				Element disabledDoc = newDocument.createElement("disabled");
				disabledDoc.setTextContent("0");
				documents.appendChild(disabledDoc);

				Element nameDoc = newDocument.createElement("name");
				nameDoc.setTextContent("documents");
				documents.appendChild(nameDoc);

				Element numberDoc = newDocument.createElement("number");
				numberDoc.setTextContent("4");
				documents.appendChild(numberDoc);

				Element prettyNameDoc = newDocument.createElement("prettyName");
				prettyNameDoc.setTextContent("Document list");
				documents.appendChild(prettyNameDoc);

				Element rowsDoc = newDocument.createElement("rows");
				rowsDoc.setTextContent("5");
				documents.appendChild(rowsDoc);

				Element sizeDoc = newDocument.createElement("size");
				sizeDoc.setTextContent("80");
				documents.appendChild(sizeDoc);

				Element unmodifiableDoc = newDocument.createElement("unmodifiable");
				unmodifiableDoc.setTextContent("0");
				documents.appendChild(unmodifiableDoc);

				Element classTypeDoc = newDocument.createElement("classType");
				classTypeDoc.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				documents.appendChild(classTypeDoc);

				classObject.appendChild(documents);
			 */


			Element memberO = newDocument.createElement("member");

			Element disabledDoc = newDocument.createElement("disabled");
			disabledDoc.setTextContent("0");
			memberO.appendChild(disabledDoc);

			Element nameDoc = newDocument.createElement("name");
			nameDoc.setTextContent("member");
			memberO.appendChild(nameDoc);

			Element numberDoc = newDocument.createElement("number");
			numberDoc.setTextContent("1");
			memberO.appendChild(numberDoc);

			Element prettyNameDoc = newDocument.createElement("prettyName");
			prettyNameDoc.setTextContent("Member");
			memberO.appendChild(prettyNameDoc);

			Element sizeDoc = newDocument.createElement("size");
			sizeDoc.setTextContent("30");
			memberO.appendChild(sizeDoc);

			Element unmodifiableDoc = newDocument.createElement("unmodifiable");
			unmodifiableDoc.setTextContent("0");
			memberO.appendChild(unmodifiableDoc);

			Element classTypeDoc = newDocument.createElement("classType");
			classTypeDoc.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
			memberO.appendChild(classTypeDoc);

			classObject.appendChild(memberO);

			/*
				Element interval = newDocument.createElement("interval");

				Element cacheInt = newDocument.createElement("cache");
				cacheInt.setTextContent("0");
				interval.appendChild(cacheInt);

				Element disabledInt = newDocument.createElement("disabled");
				disabledInt.setTextContent("0");
				interval.appendChild(disabledInt);

				Element displayTypeInt = newDocument.createElement("displayType");
				displayTypeInt.setTextContent("select");
				interval.appendChild(displayTypeInt);

				Element multiSelectInt = newDocument.createElement("multiSelect");
				multiSelectInt.setTextContent("0");
				interval.appendChild(multiSelectInt);

				Element nameInt = newDocument.createElement("name");
				nameInt.setTextContent("interval");
				interval.appendChild(nameInt);

				Element numberInt = newDocument.createElement("number");
				numberInt.setTextContent("1");
				interval.appendChild(numberInt);

				Element prettyNameInt = newDocument.createElement("prettyName");
				prettyNameInt.setTextContent("Email notifications interval");
				interval.appendChild(prettyNameInt);

				Element relationalStorageInt = newDocument.createElement("relationalStorage");
				relationalStorageInt.setTextContent("0");
				interval.appendChild(relationalStorageInt);

				Element separatorInt = newDocument.createElement("separator");
				separatorInt.setTextContent(" ");
				interval.appendChild(separatorInt);

				Element separatorsInt = newDocument.createElement("separators");
				separatorsInt.setTextContent(" ,|");
				interval.appendChild(separatorsInt);

				Element sizeInt = newDocument.createElement("size");
				sizeInt.setTextContent("1");
				interval.appendChild(sizeInt);

				Element unmodifiableInt = newDocument.createElement("unmodifiable");
				unmodifiableInt.setTextContent("0");
				interval.appendChild(unmodifiableInt);

				Element valuesInt = newDocument.createElement("values");
				valuesInt.setTextContent("Scheduler.WatchListDailyNotifier|Scheduler.WatchListHourlyNotifier|Scheduler.WatchListWeeklyNotifier");
				interval.appendChild(valuesInt);

				Element classTypeInt = newDocument.createElement("classType");
				classTypeInt.setTextContent("com.xpn.xwiki.objects.classes.StaticListClass");
				interval.appendChild(classTypeInt);

				classObject.appendChild(interval);

			 */

			/*
				Element spaces = newDocument.createElement("spaces");

				Element disabledSpa = newDocument.createElement("disabled");
				disabledSpa.setTextContent("0");
				spaces.appendChild(disabledSpa);

				Element nameSpa = newDocument.createElement("name");
				nameSpa.setTextContent("spaces");
				spaces.appendChild(nameSpa);

				Element numberSpa = newDocument.createElement("number");
				numberSpa.setTextContent("3");
				spaces.appendChild(numberSpa);

				Element prettyNameSpa = newDocument.createElement("prettyName");
				prettyNameSpa.setTextContent("Space list");
				spaces.appendChild(prettyNameSpa);

				Element rowsSpa = newDocument.createElement("rows");
				rowsSpa.setTextContent("5");
				spaces.appendChild(rowsSpa);

				Element sizeSpa = newDocument.createElement("size");
				sizeSpa.setTextContent("80");
				spaces.appendChild(sizeSpa);

				Element unmodifiableSpa = newDocument.createElement("unmodifiable");
				unmodifiableSpa.setTextContent("0");
				spaces.appendChild(unmodifiableSpa);

				Element classTypeSpa = newDocument.createElement("classType");
				classTypeSpa.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				spaces.appendChild(classTypeSpa);

				classObject.appendChild(spaces);
			 */

			object.appendChild(classObject);

			/*
				Element users = newDocument.createElement("users");

				Element disabledUsers = newDocument.createElement("disabled");
				disabledUsers.setTextContent("0");
				users.appendChild(disabledUsers);

				Element nameUsers2 = newDocument.createElement("name");
				nameUsers2.setTextContent("users");
				users.appendChild(nameUsers2);

				Element numberUsers = newDocument.createElement("number");
				numberUsers.setTextContent("5");
				users.appendChild(numberUsers);

				Element prettyNameUsers = newDocument.createElement("prettyName");
				prettyNameUsers.setTextContent("User list");
				users.appendChild(prettyNameUsers);

				Element rowsUsers = newDocument.createElement("rows");
				rowsUsers.setTextContent("5");
				users.appendChild(rowsUsers);

				Element sizeUsers = newDocument.createElement("size");
				sizeUsers.setTextContent("80");
				users.appendChild(sizeUsers);

				Element unmodifiableUsers = newDocument.createElement("unmodifiable");
				unmodifiableUsers.setTextContent("0");
				users.appendChild(unmodifiableUsers);

				Element classTypeUsers = newDocument.createElement("classType");
				classTypeUsers.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				users.appendChild(classTypeUsers);

				classObject.appendChild(users);
			 */


			Element wikis = newDocument.createElement("wikis");

			Element disabledWiki = newDocument.createElement("disabled");
			disabledWiki.setTextContent("0");
			wikis.appendChild(disabledWiki);

			Element nameWiki = newDocument.createElement("name");
			nameWiki.setTextContent("wikis");
			wikis.appendChild(nameWiki);

			Element numberWiki = newDocument.createElement("number");
			numberWiki.setTextContent("2");
			wikis.appendChild(numberWiki);

			Element prettyWiki = newDocument.createElement("prettyName");
			prettyWiki.setTextContent("Wiki list");
			wikis.appendChild(prettyWiki);

			Element rowsWiki = newDocument.createElement("rows");
			rowsWiki.setTextContent("5");
			wikis.appendChild(rowsWiki);

			Element sizeWiki = newDocument.createElement("size");
			sizeWiki.setTextContent("80");
			wikis.appendChild(sizeWiki);

			Element unmodifiableWiki = newDocument.createElement("unmodifiable");
			unmodifiableWiki.setTextContent("0");
			wikis.appendChild(unmodifiableWiki);

			Element classTypeWiki = newDocument.createElement("classType");
			classTypeWiki.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
			wikis.appendChild(classTypeWiki);

			///////////classObject.appendChild(wikis);


			object.appendChild(classObject);

			xwikidoc.appendChild(object);

			Element property = newDocument.createElement("property");

			Element memberProperty = newDocument.createElement("member");
			//memberProperty.setTextContent("");
			property.appendChild(memberProperty);

			object.appendChild(property);

			xwikidoc.appendChild(object);


			//////////////////////////////
			int userNumber =1;
			for(int k=0; k<arrayLength;k++){

				//System.out.println("i: " + i + " arrayFrom[i] =" + arrayFrom[i] + " arrayTo[i] =" + arrayTo[i] + "\nk: " + k + " arrayFrom[k] =" + arrayFrom[k] + " arrayTo[k] =" + arrayTo[k]);

				if(arrayTo[k].equals(arrayTo[i])){
					Element object2 = newDocument.createElement("object");

					Element nameObj = newDocument.createElement("name");
					nameObj.setTextContent("XWiki." + arrayTo[k]);
					object2.appendChild(nameObj);

					Element numberObj = newDocument.createElement("number");
					numberObj.setTextContent(userNumber + "");
					object2.appendChild(numberObj);

					userNumber++;

					Element classNameObj = newDocument.createElement("className");
					classNameObj.setTextContent("XWiki.XWikiGroups");
					object2.appendChild(classNameObj);

					Element guidObj = newDocument.createElement("guid");
					guidObj.setTextContent("1d986218-665a-4653-b4f6-e7b856991e3b");
					object2.appendChild(guidObj);

					Element classObj = newDocument.createElement("class");


					Element nameClass2 = newDocument.createElement("name");
					nameClass2.setTextContent("XWiki.XWikiGroups");
					classObj.appendChild(nameClass2);

					Element customClassObj = newDocument.createElement("customClass");
					classObj.appendChild(customClassObj);

					Element customMappingObj = newDocument.createElement("customMapping");
					classObj.appendChild(customMappingObj);

					Element defaultViewSheetObj = newDocument.createElement("defaultViewSheet");
					classObj.appendChild(defaultViewSheetObj);

					Element defaultEditSheetObj = newDocument.createElement("defaultEditSheet");
					classObj.appendChild(defaultEditSheetObj);

					Element defaultWebObj = newDocument.createElement("defaultWeb");
					classObj.appendChild(defaultWebObj);

					Element nameFieldObj = newDocument.createElement("nameField");
					classObj.appendChild(nameFieldObj);

					Element validationScriptObj = newDocument.createElement("validationScript");
					classObj.appendChild(validationScriptObj);

					Element memberObj = newDocument.createElement("member");

					Element disabledMemb = newDocument.createElement("disabled");
					disabledMemb.setTextContent("0");
					memberObj.appendChild(disabledMemb);

					Element nameMemb = newDocument.createElement("name");
					nameMemb.setTextContent("member");
					memberObj.appendChild(nameMemb);

					Element numberMemb = newDocument.createElement("number");
					numberMemb.setTextContent("1");
					memberObj.appendChild(numberMemb);

					Element prettyNameMemb = newDocument.createElement("prettyName");
					prettyNameMemb.setTextContent("Member");
					memberObj.appendChild(prettyNameMemb);

					Element sizeMemb = newDocument.createElement("size");
					sizeMemb.setTextContent("30");
					memberObj.appendChild(sizeMemb);

					Element unmodifiableMemb = newDocument.createElement("unmodifiable");
					unmodifiableMemb.setTextContent("0");
					memberObj.appendChild(unmodifiableMemb);

					Element classTypeMemb = newDocument.createElement("classType");
					classTypeMemb.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
					memberObj.appendChild(classTypeMemb);				

					classObj.appendChild(memberObj);

					object2.appendChild(classObj);


					Element propertyObj = newDocument.createElement("property");

					Element memberProperty2 = newDocument.createElement("member");
					memberProperty2.setTextContent("XWiki." + arrayFrom[k]);
					propertyObj.appendChild(memberProperty2);

					object2.appendChild(propertyObj);

					xwikidoc.appendChild(object2);

				}
			}
			/*

				Element object3 = newDocument.createElement("object");


				Element nameObj3 = newDocument.createElement("name");
				nameObj3.setTextContent("XWiki.Document Reviewer");
				object3.appendChild(nameObj3);

				Element numberObj3 = newDocument.createElement("number");
				numberObj3.setTextContent("1");
				object3.appendChild(numberObj3);

				Element classNameObj3 = newDocument.createElement("className");
				classNameObj3.setTextContent("XWiki.XWikiGroups");
				object3.appendChild(classNameObj3);

				Element guidObj3 = newDocument.createElement("guid");
				guidObj3.setTextContent("521793dd-e071-4d6e-bc68-779d4d315beb");
				object3.appendChild(guidObj3);

				Element classObj2 = newDocument.createElement("class");

				Element nameCla = newDocument.createElement("name");
				nameCla.setTextContent("XWiki.XWikiGroups");
				classObj2.appendChild(nameCla);

				Element customClass = newDocument.createElement("customClass");
				classObj2.appendChild(customClass);

				Element customMappingCla = newDocument.createElement("customMapping");
				classObj2.appendChild(customMappingCla);

				Element defaultViewSheetCla = newDocument.createElement("defaultViewSheet");
				classObj2.appendChild(defaultViewSheetCla);

				Element defaultEditSheetCla = newDocument.createElement("defaultEditSheet");
				classObj2.appendChild(defaultEditSheetCla);

				Element defaultWebCla = newDocument.createElement("defaultWeb");
				classObj2.appendChild(defaultWebCla);

				Element nameFieldCla = newDocument.createElement("nameField");
				classObj2.appendChild(nameFieldCla);

				Element validationScriptCla = newDocument.createElement("validationScript");
				classObj2.appendChild(validationScriptCla);


				Element memberObj2 = newDocument.createElement("member");

				Element disabledObj2 = newDocument.createElement("disabled");
				disabledObj2.setTextContent("0");
				memberObj2.appendChild(disabledObj2);

				Element nameObj2 = newDocument.createElement("name");
				nameObj2.setTextContent("member");
				memberObj2.appendChild(nameObj2);

				Element numberObj2 = newDocument.createElement("number");
				numberObj2.setTextContent("1");
				memberObj2.appendChild(numberObj2);

				Element prettyNameObj2 = newDocument.createElement("prettyName");
				prettyNameObj2.setTextContent("Member");
				memberObj2.appendChild(prettyNameObj2);

				Element sizeObj2 = newDocument.createElement("size");
				sizeObj2.setTextContent("30");
				memberObj2.appendChild(sizeObj2);

				Element unmodifiableObj2 = newDocument.createElement("unmodifiable");
				unmodifiableObj2.setTextContent("0");
				memberObj2.appendChild(unmodifiableObj2);

				Element classTypeObj2 = newDocument.createElement("classType");
				classTypeObj2.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				memberObj2.appendChild(classTypeObj2);			


				classObj2.appendChild(memberObj2);

				object3.appendChild(classObj2);


				Element memberProperty3 = newDocument.createElement("property");

				Element member = newDocument.createElement("member");
				member.setTextContent("XWiki." + "daniele");
				memberProperty3.appendChild(member);

				object3.appendChild(memberProperty3);





				xwikidoc.appendChild(object3);
			 */

			///////////////////////////	

			//}	







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
			File dir = new File(directoryCompany.getCurrentDirectory().toString() + "\\xml\\xwiki\\");
			dir.mkdir();
			StreamResult result = new StreamResult(new File(directoryCompany.getCurrentDirectory().toString() + "\\xml\\xwiki\\"+ arrayTo[i] + ".xml"));
			transformer.transform(source, result);

			//}
		}

		//prettyPrint(newDocument, directory, "user");
		//}else{
		//	System.out.println("Procedura gia effettuata");
		//}
	}
}
//}
