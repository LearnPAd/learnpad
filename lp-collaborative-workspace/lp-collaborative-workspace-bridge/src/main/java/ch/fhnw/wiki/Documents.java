package ch.fhnw.wiki;

import java.io.File;
import java.util.ArrayList;
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

public class Documents {

	/**
	 * 
	 * @param instances
	 * @param spaceName
	 * @param xwikiUser
	 * @param directoryBPMN
	 * @param createDirectory
	 * @param doc
	 * @param docFactory
	 * @return
	 * @throws Exception
	 */
	public static String DocumentGenerator(NodeList instances, String spaceName, String xwikiUser, JFileChooser directoryBPMN, File createDirectory, Document doc, DocumentBuilderFactory docFactory) throws Exception{
		
		int arrayLength=0;
		
		try {
			// create xwiki pages for every file cool !
			String oldName;
			String nameClean;
			String type;

			arrayLength = instances.getLength();
			String[] arrayName = new String[arrayLength+1];

			for (int i=0; i<arrayLength; i++){


				//get the info from the other file			
				////////////////////////////////////////////////		
				Element attributes = (Element) instances.item(i);
				NodeList elementi = attributes.getElementsByTagName("ATTRIBUTE");
				////////////////////////////////////////////////
				//Node attributes = instances.item(i);

				type = attributes.getAttributes().getNamedItem("class").toString();
				type = type.replaceFirst("class=\"", "").replaceAll("\"", "");


				oldName = attributes.getAttributes().getNamedItem("name").toString().replaceAll("name=\"", "").replaceAll("\"","");
				//TODO it miss the *
				nameClean = oldName.replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");
				arrayName[i] = nameClean;



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
				name.setTextContent(nameClean);
				xwikidoc.appendChild(name);

				Element language = docFinale.createElement("language");
				xwikidoc.appendChild(language);

				Element defaultLanguage = docFinale.createElement("defaultLanguage");
				defaultLanguage.setTextContent("en");
				xwikidoc.appendChild(defaultLanguage);

				Element translation = docFinale.createElement("translation");
				translation.setTextContent("0");
				xwikidoc.appendChild(translation);

				//THE ADMIN NAME SELECTED BY THE USER
				Element creator = docFinale.createElement("creator");
				creator.setTextContent("XWiki." + xwikiUser);
				xwikidoc.appendChild(creator);

				Element creationDate = docFinale.createElement("creationDate");
				creationDate.setTextContent("139682179000");
				xwikidoc.appendChild(creationDate);

				// connection to a generic file which explain the process
				Element parent = docFinale.createElement("parent");
				parent.setTextContent(spaceName + "." + directoryBPMN.getSelectedFile().getName().replaceAll(".xml", ""));
				xwikidoc.appendChild(parent);

				// insert the user based on an existing user of xwiki
				Element author = docFinale.createElement("author");
				author.setTextContent("XWiki." + xwikiUser);
				xwikidoc.appendChild(author);

				//Element customClass = docFinale.createElement("customClass");
				//xwikidoc.appendChild(customClass);

				Element contentAuthor = docFinale.createElement("contentAuthor");
				contentAuthor.setTextContent("XWiki." + xwikiUser);
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
				if(instances.item(i).getAttributes().getNamedItem("class").toString().equals("class=\"Exclusive Gateway\"")){
					title.setTextContent(oldName);
				}else{
					title.setTextContent(nameClean);
				}
				xwikidoc.appendChild(title);

				Element comment = docFinale.createElement("comment");
				comment.setTextContent("deny delete right for XWiki.XWikiGuest");
				xwikidoc.appendChild(comment);

				//Element validationScript = docFinale.createElement("validationScript");
				//xwikidoc.appendChild(validationScript);

				//TODO Analyze the comment structure
				//Element comment = docFinale.createElement("comment");
				//
				//xwikidoc.appendChild(comment);

				Element minorEdit = docFinale.createElement("minorEdit");
				minorEdit.setTextContent("true");
				xwikidoc.appendChild(minorEdit);

				Element syntaxId = docFinale.createElement("syntaxId");
				syntaxId.setTextContent("xwiki/2.1");
				xwikidoc.appendChild(syntaxId);

				Element hidden = docFinale.createElement("hidden");
				hidden.setTextContent("false");
				xwikidoc.appendChild(hidden);

				Element content = docFinale.createElement("content");
				xwikidoc.appendChild(content);


				//USER RIGHTS MANAGEMENT

				NodeList connector = doc.getElementsByTagName("CONNECTOR");
				int lunghezza = connector.getLength();

				Element to;
				Element from;
				String laneName = "";
				int r =0;
				ArrayList<String> lanes = new ArrayList<String>();


				for(int h=0; h<lunghezza; h++){

					Element elementConnector =(Element) connector.item(h);

					from = (Element) elementConnector.getChildNodes().item(1);
					to = (Element) elementConnector.getChildNodes().item(3);


					if(elementConnector.getAttributes().getNamedItem("class").toString().equals("class=\"Is inside\"") 
							&& to.getAttributes().getNamedItem("class").toString().equals("class=\"Lane\"")
							&& from.getAttributes().getNamedItem("instance").toString().equals("instance=\"" + oldName + "\"") 
							&& !lanes.contains(to.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", ""))
							)
					{
						r++;

						//							System.out.println("IS INSIDE: " + elementConnector.getAttributes().getNamedItem("class").toString().equals("class=\"Is inside\"") + "LANE: " + to.getAttributes().getNamedItem("class").toString().equals("class=\"Lane\"") + "NAME: " + from.getAttributes().getNamedItem("instance").toString().equals("instance=\"" + oldName + "\"") + " " + oldName );


						laneName=to.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "");
						lanes.add(laneName);


						Element object = docFinale.createElement("object");

						Element nameObj = docFinale.createElement("name");
						nameObj.setTextContent(spaceName + "." + nameClean);
						object.appendChild(nameObj);

						Element numberObj = docFinale.createElement("number");
						numberObj.setTextContent(r + "");
						object.appendChild(numberObj);

						Element classNameObj = docFinale.createElement("className");
						classNameObj.setTextContent("XWiki.XWikiRights");
						object.appendChild(classNameObj);

						Element guidObj = docFinale.createElement("guid");
					//	guidObj.setTextContent("ea383b6e-4b93-49e7-9c1e-849cfc7d11c5");
						object.appendChild(guidObj);

						//
						Element classObj = docFinale.createElement("class");

						Element nameClass = docFinale.createElement("name");
						nameClass.setTextContent("XWiki.XWikiRights");
						classObj.appendChild(nameClass);

						Element customClassClass = docFinale.createElement("customClass");
						classObj.appendChild(customClassClass);

						Element customMappingClass = docFinale.createElement("customMapping");
						classObj.appendChild(customMappingClass);

						Element defaultViewSheetClass = docFinale.createElement("defaultViewSheet");
						classObj.appendChild(defaultViewSheetClass);

						Element defaultEditSheetClass = docFinale.createElement("defaultEditSheet");
						classObj.appendChild(defaultEditSheetClass);

						Element defaultWebClass = docFinale.createElement("defaultWeb");
						classObj.appendChild(defaultWebClass);

						Element nameFieldClass = docFinale.createElement("nameField");
						classObj.appendChild(nameFieldClass);

						Element validationScriptClass = docFinale.createElement("validationScript");
						classObj.appendChild(validationScriptClass);

						Element allowClass = docFinale.createElement("allow");

						Element defaultValueA = docFinale.createElement("defaultValue");
						defaultValueA.setTextContent("1");
						allowClass.appendChild(defaultValueA);

						Element disabledA = docFinale.createElement("disabled");
						disabledA.setTextContent("0");
						allowClass.appendChild(disabledA);

						Element displayFormTypeA = docFinale.createElement("displayFormType");
						displayFormTypeA.setTextContent("select");
						allowClass.appendChild(displayFormTypeA);

						Element displayTypeA = docFinale.createElement("displayType");
						displayTypeA.setTextContent("allow");
						allowClass.appendChild(displayTypeA);

						Element nameA = docFinale.createElement("name");
						nameA.setTextContent("allow");
						allowClass.appendChild(nameA);

						Element numberA = docFinale.createElement("number");
						numberA.setTextContent("4");
						allowClass.appendChild(numberA);

						Element prettyNameA = docFinale.createElement("prettyName");
						prettyNameA.setTextContent("Allow/Deny");
						allowClass.appendChild(prettyNameA);

						Element unmodifiableA = docFinale.createElement("unmodifiable");
						unmodifiableA.setTextContent("0");
						allowClass.appendChild(unmodifiableA);

						Element classTypeA = docFinale.createElement("classType");
						classTypeA.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
						allowClass.appendChild(classTypeA);

						classObj.appendChild(allowClass);

						Element groups = docFinale.createElement("groups");

						Element cacheG = docFinale.createElement("cache");
						cacheG.setTextContent("0");
						groups.appendChild(cacheG);

						Element disabledG = docFinale.createElement("disabled");
						disabledG.setTextContent("0");
						groups.appendChild(disabledG);

						Element displayTypeG = docFinale.createElement("displayType");
						displayTypeG.setTextContent("input");
						groups.appendChild(displayTypeG);

						Element multiSelectG = docFinale.createElement("multiSelect");
						multiSelectG.setTextContent("1");
						groups.appendChild(multiSelectG);

						Element nameG = docFinale.createElement("name");
						nameG.setTextContent("groups");
						groups.appendChild(nameG);

						Element numberG = docFinale.createElement("number");
						numberG.setTextContent("1");
						groups.appendChild(numberG);

						Element pickerG = docFinale.createElement("picker");
						pickerG.setTextContent("1");
						groups.appendChild(pickerG);

						Element prettyNameG = docFinale.createElement("prettyName");
						prettyNameG.setTextContent("Groups");
						groups.appendChild(prettyNameG);

						Element relationalStorageG = docFinale.createElement("relationalStorage");
						relationalStorageG.setTextContent("0");
						groups.appendChild(relationalStorageG);

						Element separatorG = docFinale.createElement("separator");
						separatorG.setTextContent(" ");
						groups.appendChild(separatorG);

						Element sizeG = docFinale.createElement("size");
						sizeG.setTextContent("5");
						groups.appendChild(sizeG);

						Element unmodifiableG = docFinale.createElement("unmodifiable");
						unmodifiableG.setTextContent("0");
						groups.appendChild(unmodifiableG);

						Element classTypeG = docFinale.createElement("classType");
						classTypeG.setTextContent("com.xpn.xwiki.objects.classes.GroupsClass");
						groups.appendChild(classTypeG);

						classObj.appendChild(groups);

						Element levels = docFinale.createElement("levels");

						Element cacheL = docFinale.createElement("cache");
						cacheL.setTextContent("0");
						levels.appendChild(cacheL);

						Element disabledL = docFinale.createElement("disabled");
						disabledL.setTextContent("0");
						levels.appendChild(disabledL);

						Element displayTypeL = docFinale.createElement("displayType");
						displayTypeL.setTextContent("select");
						levels.appendChild(displayTypeL);

						Element multiSelectL = docFinale.createElement("multiSelect");
						multiSelectL.setTextContent("1");
						levels.appendChild(multiSelectL);

						Element nameL = docFinale.createElement("name");
						nameL.setTextContent("levels");
						levels.appendChild(nameL);

						Element numberL = docFinale.createElement("number");
						numberL.setTextContent("2");
						levels.appendChild(numberL);

						Element prettyNameL = docFinale.createElement("prettyName");
						prettyNameL.setTextContent("Levels");
						levels.appendChild(prettyNameL);

						Element relationalStorageL = docFinale.createElement("relationalStorage");
						relationalStorageL.setTextContent("0");
						levels.appendChild(relationalStorageL);

						Element separatorL = docFinale.createElement("separator");
						separatorL.setTextContent(" ");
						levels.appendChild(separatorL);

						Element sizeL = docFinale.createElement("size");
						sizeL.setTextContent("3");
						levels.appendChild(sizeL);

						Element unmodifiableL = docFinale.createElement("unmodifiable");
						unmodifiableL.setTextContent("0");
						levels.appendChild(unmodifiableL);

						Element classTypeL = docFinale.createElement("classType");
						classTypeL.setTextContent("com.xpn.xwiki.objects.classes.LevelsClass");
						levels.appendChild(classTypeL);

						classObj.appendChild(levels);

						Element users = docFinale.createElement("users");

						Element cacheU = docFinale.createElement("cache");
						cacheU.setTextContent("0");
						users.appendChild(cacheU);

						Element disabledU = docFinale.createElement("disabled");
						disabledU.setTextContent("0");
						users.appendChild(disabledU);

						Element displayTypeU = docFinale.createElement("displayType");
						displayTypeU.setTextContent("input");
						users.appendChild(displayTypeU);

						Element multiSelectU = docFinale.createElement("multiSelect");
						multiSelectU.setTextContent("1");
						users.appendChild(multiSelectU);

						Element nameU = docFinale.createElement("name");
						nameU.setTextContent("users");
						users.appendChild(nameU);

						Element numberU = docFinale.createElement("number");
						numberU.setTextContent("3");
						users.appendChild(numberU);

						Element pickerU = docFinale.createElement("picker");
						pickerU.setTextContent("1");
						users.appendChild(pickerU);

						Element prettyNameU = docFinale.createElement("prettyName");
						prettyNameU.setTextContent("Users");
						users.appendChild(prettyNameU);

						Element relationalStorageU = docFinale.createElement("relationalStorage");
						relationalStorageU.setTextContent("0");
						users.appendChild(relationalStorageU);

						Element separatorU = docFinale.createElement("separator");
						separatorU.setTextContent(" ");
						users.appendChild(separatorU);

						Element sizeU = docFinale.createElement("size");
						sizeU.setTextContent("5");
						users.appendChild(sizeU);

						Element unmodifiableU = docFinale.createElement("unmodifiable");
						unmodifiableU.setTextContent("0");
						users.appendChild(unmodifiableU);

						Element classTypeU = docFinale.createElement("classType");
						classTypeU.setTextContent("com.xpn.xwiki.objects.classes.UsersClass");
						users.appendChild(classTypeU);

						classObj.appendChild(users);

						object.appendChild(classObj);


						Element propertyA = docFinale.createElement("property");

						Element allowP = docFinale.createElement("allow");
						allowP.setTextContent("1");
						propertyA.appendChild(allowP);

						object.appendChild(propertyA);

						Element propertyG = docFinale.createElement("property");

						Element groupsP = docFinale.createElement("groups");
						groupsP.setTextContent("XWiki." + laneName);
						propertyG.appendChild(groupsP);

						object.appendChild(propertyG);

						Element propertyL = docFinale.createElement("property");

						Element levelsP = docFinale.createElement("levels");
						levelsP.setTextContent("view,comment");
						propertyL.appendChild(levelsP);

						object.appendChild(propertyL);

						xwikidoc.appendChild(object);


					}
				}
				for (int h=0; h<lunghezza; h++){

					Element elementConnector =(Element) connector.item(h);

					from = (Element) elementConnector.getChildNodes().item(1);
					to = (Element) elementConnector.getChildNodes().item(3);

					if(elementConnector.getAttributes().getNamedItem("class").toString().equals("class=\"Is inside\"") 
							&& to.getAttributes().getNamedItem("class").toString().equals("class=\"Lane\"")
							&& !from.getAttributes().getNamedItem("instance").toString().equals("instance=\"" + oldName + "\"") 
							&& !lanes.contains(to.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", ""))

							){

						r++;

						laneName=to.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "");
						lanes.add(laneName);

						Element object2 = docFinale.createElement("object");

						Element nameObj = docFinale.createElement("name");
						nameObj.setTextContent(spaceName + "." + nameClean);
						object2.appendChild(nameObj);

						Element numberObj = docFinale.createElement("number");
						numberObj.setTextContent(r + "");
						object2.appendChild(numberObj);

						Element classNameObj = docFinale.createElement("className");
						classNameObj.setTextContent("XWiki.XWikiRights");
						object2.appendChild(classNameObj);

						Element guidObj = docFinale.createElement("guid");
				//		guidObj.setTextContent("ea383b6e-4b93-49e7-9c1e-849cfc7d11c5");
						object2.appendChild(guidObj);

						//
						Element classObj = docFinale.createElement("class");

						Element nameClass = docFinale.createElement("name");
						nameClass.setTextContent("XWiki.XWikiRights");
						classObj.appendChild(nameClass);

						Element customClassClass = docFinale.createElement("customClass");
						classObj.appendChild(customClassClass);

						Element customMappingClass = docFinale.createElement("customMapping");
						classObj.appendChild(customMappingClass);

						Element defaultViewSheetClass = docFinale.createElement("defaultViewSheet");
						classObj.appendChild(defaultViewSheetClass);

						Element defaultEditSheetClass = docFinale.createElement("defaultEditSheet");
						classObj.appendChild(defaultEditSheetClass);

						Element defaultWebClass = docFinale.createElement("defaultWeb");
						classObj.appendChild(defaultWebClass);

						Element nameFieldClass = docFinale.createElement("nameField");
						classObj.appendChild(nameFieldClass);

						Element validationScriptClass = docFinale.createElement("validationScript");
						classObj.appendChild(validationScriptClass);

						Element allowClass = docFinale.createElement("allow");

						Element defaultValueA = docFinale.createElement("defaultValue");
						defaultValueA.setTextContent("1");
						allowClass.appendChild(defaultValueA);

						Element disabledA = docFinale.createElement("disabled");
						disabledA.setTextContent("0");
						allowClass.appendChild(disabledA);

						Element displayFormTypeA = docFinale.createElement("displayFormType");
						displayFormTypeA.setTextContent("select");
						allowClass.appendChild(displayFormTypeA);

						Element displayTypeA = docFinale.createElement("displayType");
						displayTypeA.setTextContent("allow");
						allowClass.appendChild(displayTypeA);

						Element nameA = docFinale.createElement("name");
						nameA.setTextContent("allow");
						allowClass.appendChild(nameA);

						Element numberA = docFinale.createElement("number");
						numberA.setTextContent("4");
						allowClass.appendChild(numberA);

						Element prettyNameA = docFinale.createElement("prettyName");
						prettyNameA.setTextContent("Allow/Deny");
						allowClass.appendChild(prettyNameA);

						Element unmodifiableA = docFinale.createElement("unmodifiable");
						unmodifiableA.setTextContent("0");
						allowClass.appendChild(unmodifiableA);

						Element classTypeA = docFinale.createElement("classType");
						classTypeA.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
						allowClass.appendChild(classTypeA);

						classObj.appendChild(allowClass);

						Element groups = docFinale.createElement("groups");

						Element cacheG = docFinale.createElement("cache");
						cacheG.setTextContent("0");
						groups.appendChild(cacheG);

						Element disabledG = docFinale.createElement("disabled");
						disabledG.setTextContent("0");
						groups.appendChild(disabledG);

						Element displayTypeG = docFinale.createElement("displayType");
						displayTypeG.setTextContent("input");
						groups.appendChild(displayTypeG);

						Element multiSelectG = docFinale.createElement("multiSelect");
						multiSelectG.setTextContent("1");
						groups.appendChild(multiSelectG);

						Element nameG = docFinale.createElement("name");
						nameG.setTextContent("groups");
						groups.appendChild(nameG);

						Element numberG = docFinale.createElement("number");
						numberG.setTextContent("1");
						groups.appendChild(numberG);

						Element pickerG = docFinale.createElement("picker");
						pickerG.setTextContent("1");
						groups.appendChild(pickerG);

						Element prettyNameG = docFinale.createElement("prettyName");
						prettyNameG.setTextContent("Groups");
						groups.appendChild(prettyNameG);

						Element relationalStorageG = docFinale.createElement("relationalStorage");
						relationalStorageG.setTextContent("0");
						groups.appendChild(relationalStorageG);

						Element separatorG = docFinale.createElement("separator");
						separatorG.setTextContent(" ");
						groups.appendChild(separatorG);

						Element sizeG = docFinale.createElement("size");
						sizeG.setTextContent("5");
						groups.appendChild(sizeG);

						Element unmodifiableG = docFinale.createElement("unmodifiable");
						unmodifiableG.setTextContent("0");
						groups.appendChild(unmodifiableG);

						Element classTypeG = docFinale.createElement("classType");
						classTypeG.setTextContent("com.xpn.xwiki.objects.classes.GroupsClass");
						groups.appendChild(classTypeG);

						classObj.appendChild(groups);

						Element levels = docFinale.createElement("levels");

						Element cacheL = docFinale.createElement("cache");
						cacheL.setTextContent("0");
						levels.appendChild(cacheL);

						Element disabledL = docFinale.createElement("disabled");
						disabledL.setTextContent("0");
						levels.appendChild(disabledL);

						Element displayTypeL = docFinale.createElement("displayType");
						displayTypeL.setTextContent("select");
						levels.appendChild(displayTypeL);

						Element multiSelectL = docFinale.createElement("multiSelect");
						multiSelectL.setTextContent("1");
						levels.appendChild(multiSelectL);

						Element nameL = docFinale.createElement("name");
						nameL.setTextContent("levels");
						levels.appendChild(nameL);

						Element numberL = docFinale.createElement("number");
						numberL.setTextContent("2");
						levels.appendChild(numberL);

						Element prettyNameL = docFinale.createElement("prettyName");
						prettyNameL.setTextContent("Levels");
						levels.appendChild(prettyNameL);

						Element relationalStorageL = docFinale.createElement("relationalStorage");
						relationalStorageL.setTextContent("0");
						levels.appendChild(relationalStorageL);

						Element separatorL = docFinale.createElement("separator");
						separatorL.setTextContent(" ");
						levels.appendChild(separatorL);

						Element sizeL = docFinale.createElement("size");
						sizeL.setTextContent("3");
						levels.appendChild(sizeL);

						Element unmodifiableL = docFinale.createElement("unmodifiable");
						unmodifiableL.setTextContent("0");
						levels.appendChild(unmodifiableL);

						Element classTypeL = docFinale.createElement("classType");
						classTypeL.setTextContent("com.xpn.xwiki.objects.classes.LevelsClass");
						levels.appendChild(classTypeL);

						classObj.appendChild(levels);

						Element users = docFinale.createElement("users");

						Element cacheU = docFinale.createElement("cache");
						cacheU.setTextContent("0");
						users.appendChild(cacheU);

						Element disabledU = docFinale.createElement("disabled");
						disabledU.setTextContent("0");
						users.appendChild(disabledU);

						Element displayTypeU = docFinale.createElement("displayType");
						displayTypeU.setTextContent("input");
						users.appendChild(displayTypeU);

						Element multiSelectU = docFinale.createElement("multiSelect");
						multiSelectU.setTextContent("1");
						users.appendChild(multiSelectU);

						Element nameU = docFinale.createElement("name");
						nameU.setTextContent("users");
						users.appendChild(nameU);

						Element numberU = docFinale.createElement("number");
						numberU.setTextContent("3");
						users.appendChild(numberU);

						Element pickerU = docFinale.createElement("picker");
						pickerU.setTextContent("1");
						users.appendChild(pickerU);

						Element prettyNameU = docFinale.createElement("prettyName");
						prettyNameU.setTextContent("Users");
						users.appendChild(prettyNameU);

						Element relationalStorageU = docFinale.createElement("relationalStorage");
						relationalStorageU.setTextContent("0");
						users.appendChild(relationalStorageU);

						Element separatorU = docFinale.createElement("separator");
						separatorU.setTextContent(" ");
						users.appendChild(separatorU);

						Element sizeU = docFinale.createElement("size");
						sizeU.setTextContent("5");
						users.appendChild(sizeU);

						Element unmodifiableU = docFinale.createElement("unmodifiable");
						unmodifiableU.setTextContent("0");
						users.appendChild(unmodifiableU);

						Element classTypeU = docFinale.createElement("classType");
						classTypeU.setTextContent("com.xpn.xwiki.objects.classes.UsersClass");
						users.appendChild(classTypeU);

						classObj.appendChild(users);

						object2.appendChild(classObj);


						Element propertyA = docFinale.createElement("property");

						Element allowP = docFinale.createElement("allow");
						allowP.setTextContent("0");
						propertyA.appendChild(allowP);

						object2.appendChild(propertyA);

						Element propertyG2 = docFinale.createElement("property");

						Element levelsP2 = docFinale.createElement("levels");
						levelsP2.setTextContent("view,comment,edit,delete");
						propertyG2.appendChild(levelsP2);

						object2.appendChild(propertyG2);

						Element propertyL2 = docFinale.createElement("property");

						Element groupsP2 = docFinale.createElement("groups");
						groupsP2.setTextContent("XWiki." + laneName);
						propertyL2.appendChild(groupsP2);

						object2.appendChild(propertyL2);

						xwikidoc.appendChild(object2);

					}
				}



				// USER RIGHTS FOR XWIKI GUESTS (GENERIC XWIKI GROUP)

				r++;

				Element object3 = docFinale.createElement("object");

				Element nameObj = docFinale.createElement("name");
				nameObj.setTextContent(spaceName + "." + nameClean);
				object3.appendChild(nameObj);

				Element numberObj = docFinale.createElement("number");
				numberObj.setTextContent((r+1) + "");
				object3.appendChild(numberObj);

				Element classNameObj = docFinale.createElement("className");
				classNameObj.setTextContent("XWiki.XWikiRights");
				object3.appendChild(classNameObj);

				Element guidObj = docFinale.createElement("guid");
		//		guidObj.setTextContent("ea383b6e-4b93-49e7-9c1e-849cfc7d11c5");
				object3.appendChild(guidObj);

				//
				Element classObj = docFinale.createElement("class");

				Element nameClass = docFinale.createElement("name");
				nameClass.setTextContent("XWiki.XWikiRights");
				classObj.appendChild(nameClass);

				Element customClassClass = docFinale.createElement("customClass");
				classObj.appendChild(customClassClass);

				Element customMappingClass = docFinale.createElement("customMapping");
				classObj.appendChild(customMappingClass);

				Element defaultViewSheetClass = docFinale.createElement("defaultViewSheet");
				classObj.appendChild(defaultViewSheetClass);

				Element defaultEditSheetClass = docFinale.createElement("defaultEditSheet");
				classObj.appendChild(defaultEditSheetClass);

				Element defaultWebClass = docFinale.createElement("defaultWeb");
				classObj.appendChild(defaultWebClass);

				Element nameFieldClass = docFinale.createElement("nameField");
				classObj.appendChild(nameFieldClass);

				Element validationScriptClass = docFinale.createElement("validationScript");
				classObj.appendChild(validationScriptClass);

				Element allowClass = docFinale.createElement("allow");

				Element defaultValueA = docFinale.createElement("defaultValue");
				defaultValueA.setTextContent("1");
				allowClass.appendChild(defaultValueA);

				Element disabledA = docFinale.createElement("disabled");
				disabledA.setTextContent("0");
				allowClass.appendChild(disabledA);

				Element displayFormTypeA = docFinale.createElement("displayFormType");
				displayFormTypeA.setTextContent("select");
				allowClass.appendChild(displayFormTypeA);

				Element displayTypeA = docFinale.createElement("displayType");
				displayTypeA.setTextContent("allow");
				allowClass.appendChild(displayTypeA);

				Element nameA = docFinale.createElement("name");
				nameA.setTextContent("allow");
				allowClass.appendChild(nameA);

				Element numberA = docFinale.createElement("number");
				numberA.setTextContent("4");
				allowClass.appendChild(numberA);

				Element prettyNameA = docFinale.createElement("prettyName");
				prettyNameA.setTextContent("Allow/Deny");
				allowClass.appendChild(prettyNameA);

				Element unmodifiableA = docFinale.createElement("unmodifiable");
				unmodifiableA.setTextContent("0");
				allowClass.appendChild(unmodifiableA);

				Element classTypeA = docFinale.createElement("classType");
				classTypeA.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
				allowClass.appendChild(classTypeA);

				classObj.appendChild(allowClass);

				Element groups = docFinale.createElement("groups");

				Element cacheG = docFinale.createElement("cache");
				cacheG.setTextContent("0");
				groups.appendChild(cacheG);

				Element disabledG = docFinale.createElement("disabled");
				disabledG.setTextContent("0");
				groups.appendChild(disabledG);

				Element displayTypeG = docFinale.createElement("displayType");
				displayTypeG.setTextContent("input");
				groups.appendChild(displayTypeG);

				Element multiSelectG = docFinale.createElement("multiSelect");
				multiSelectG.setTextContent("1");
				groups.appendChild(multiSelectG);

				Element nameG = docFinale.createElement("name");
				nameG.setTextContent("groups");
				groups.appendChild(nameG);

				Element numberG = docFinale.createElement("number");
				numberG.setTextContent("1");
				groups.appendChild(numberG);

				Element pickerG = docFinale.createElement("picker");
				pickerG.setTextContent("1");
				groups.appendChild(pickerG);

				Element prettyNameG = docFinale.createElement("prettyName");
				prettyNameG.setTextContent("Groups");
				groups.appendChild(prettyNameG);

				Element relationalStorageG = docFinale.createElement("relationalStorage");
				relationalStorageG.setTextContent("0");
				groups.appendChild(relationalStorageG);

				Element separatorG = docFinale.createElement("separator");
				separatorG.setTextContent(" ");
				groups.appendChild(separatorG);

				Element sizeG = docFinale.createElement("size");
				sizeG.setTextContent("5");
				groups.appendChild(sizeG);

				Element unmodifiableG = docFinale.createElement("unmodifiable");
				unmodifiableG.setTextContent("0");
				groups.appendChild(unmodifiableG);

				Element classTypeG = docFinale.createElement("classType");
				classTypeG.setTextContent("com.xpn.xwiki.objects.classes.GroupsClass");
				groups.appendChild(classTypeG);

				classObj.appendChild(groups);

				Element levels = docFinale.createElement("levels");

				Element cacheL = docFinale.createElement("cache");
				cacheL.setTextContent("0");
				levels.appendChild(cacheL);

				Element disabledL = docFinale.createElement("disabled");
				disabledL.setTextContent("0");
				levels.appendChild(disabledL);

				Element displayTypeL = docFinale.createElement("displayType");
				displayTypeL.setTextContent("select");
				levels.appendChild(displayTypeL);

				Element multiSelectL = docFinale.createElement("multiSelect");
				multiSelectL.setTextContent("1");
				levels.appendChild(multiSelectL);

				Element nameL = docFinale.createElement("name");
				nameL.setTextContent("levels");
				levels.appendChild(nameL);

				Element numberL = docFinale.createElement("number");
				numberL.setTextContent("2");
				levels.appendChild(numberL);

				Element prettyNameL = docFinale.createElement("prettyName");
				prettyNameL.setTextContent("Levels");
				levels.appendChild(prettyNameL);

				Element relationalStorageL = docFinale.createElement("relationalStorage");
				relationalStorageL.setTextContent("0");
				levels.appendChild(relationalStorageL);

				Element separatorL = docFinale.createElement("separator");
				separatorL.setTextContent(" ");
				levels.appendChild(separatorL);

				Element sizeL = docFinale.createElement("size");
				sizeL.setTextContent("3");
				levels.appendChild(sizeL);

				Element unmodifiableL = docFinale.createElement("unmodifiable");
				unmodifiableL.setTextContent("0");
				levels.appendChild(unmodifiableL);

				Element classTypeL = docFinale.createElement("classType");
				classTypeL.setTextContent("com.xpn.xwiki.objects.classes.LevelsClass");
				levels.appendChild(classTypeL);

				classObj.appendChild(levels);

				Element users = docFinale.createElement("users");

				Element cacheU = docFinale.createElement("cache");
				cacheU.setTextContent("0");
				users.appendChild(cacheU);

				Element disabledU = docFinale.createElement("disabled");
				disabledU.setTextContent("0");
				users.appendChild(disabledU);

				Element displayTypeU = docFinale.createElement("displayType");
				displayTypeU.setTextContent("input");
				users.appendChild(displayTypeU);

				Element multiSelectU = docFinale.createElement("multiSelect");
				multiSelectU.setTextContent("1");
				users.appendChild(multiSelectU);

				Element nameU = docFinale.createElement("name");
				nameU.setTextContent("users");
				users.appendChild(nameU);

				Element numberU = docFinale.createElement("number");
				numberU.setTextContent("3");
				users.appendChild(numberU);

				Element pickerU = docFinale.createElement("picker");
				pickerU.setTextContent("1");
				users.appendChild(pickerU);

				Element prettyNameU = docFinale.createElement("prettyName");
				prettyNameU.setTextContent("Users");
				users.appendChild(prettyNameU);

				Element relationalStorageU = docFinale.createElement("relationalStorage");
				relationalStorageU.setTextContent("0");
				users.appendChild(relationalStorageU);

				Element separatorU = docFinale.createElement("separator");
				separatorU.setTextContent(" ");
				users.appendChild(separatorU);

				Element sizeU = docFinale.createElement("size");
				sizeU.setTextContent("5");
				users.appendChild(sizeU);

				Element unmodifiableU = docFinale.createElement("unmodifiable");
				unmodifiableU.setTextContent("0");
				users.appendChild(unmodifiableU);

				Element classTypeU = docFinale.createElement("classType");
				classTypeU.setTextContent("com.xpn.xwiki.objects.classes.UsersClass");
				users.appendChild(classTypeU);

				classObj.appendChild(users);

				object3.appendChild(classObj);
				

				Element propertyA3 = docFinale.createElement("property");

				Element allowP3 = docFinale.createElement("allow");
				allowP3.setTextContent("0");
				propertyA3.appendChild(allowP3);

				object3.appendChild(propertyA3);

				Element propertyG3 = docFinale.createElement("property");

				Element levelsP3 = docFinale.createElement("levels");
				levelsP3.setTextContent("view,comment,edit,delete");
				propertyG3.appendChild(levelsP3);

				object3.appendChild(propertyG3);

				Element propertyL3 = docFinale.createElement("property");

				Element usersP3 = docFinale.createElement("users");
				usersP3.setTextContent("XWiki.XWikiGuest");
				propertyL3.appendChild(usersP3);

				object3.appendChild(propertyL3);

				xwikidoc.appendChild(object3);


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

				// INSERT OF THE CONTENT, OBJECT DESCRIPTION FROM THE ADOXX FILE
				String testContent = "";
				//NodeList connector = doc.getElementsByTagName("CONNECTOR");

				for (int j =0; j<elementi.getLength();j++){
					if(elementi.item(j).getAttributes().getNamedItem("name").toString().equals("name=\"Description\"")){
						// insert adoxx description
						testContent = testContent + "**Description:**\n\n" +  elementi.item(j).getTextContent() + "\n\n";


					}else if ( elementi.item(j).getAttributes().getNamedItem("name").toString().equals("name=\"Referenced document\"")){
						testContent  = testContent +  "**Related Document:**\n\n" + elementi.item(j).getTextContent().toString().replaceAll("\\\\\"", "").replaceAll("\"", "").replaceAll("ITEM <automatically> param:", "").replaceAll("ITEM Winword param:", "")
								.replaceAll("ITEM Powerpnt param:","").replaceAll("ITEM Excel param:", "").replaceAll("\\\\\\\\", "\\\\");
					}

				}

				content.setTextContent(testContent);

				System.out.println((i+1) + ": Name: " + nameClean + "\n    Type: " + type);


				// Write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				docFinale.setXmlStandalone(true);
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				DOMSource source = new DOMSource(docFinale);

				StreamResult result = new StreamResult(createDirectory + "\\"+ nameClean + ".xml");
				transformer.transform(source, result);

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		return "Documents correctly generated: " + arrayLength + "\n";

	}	
}
