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

public class BPMN {

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
	public static String[] BPMNGenerator(NodeList instances, String spaceName, String xwikiUser, JFileChooser directoryBPMN, File createDirectory, Document doc, DocumentBuilderFactory docFactory) throws Exception{

		String[] arrayName = null;
		try {
			// create xwiki pages for every file cool !
			String oldName;
			String nameClean;
			String type;

			int arrayLength = instances.getLength();
			arrayName = new String[arrayLength+1];


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


				if(type.equals("Lane")){
					//lane are roles so that every lane will be translated into a Group into the class usergroup.Group 

					//UserGroupCreator.groupCreator(directoryBPMN, listModel, oldName);
					System.out.println((i+1) + ": Name: " + nameClean + "\n    Type: " + type);


				}else if(type.equals("Pool")){
					//pool are not generated into wiki page as there is no need in the current situation
					System.out.println((i+1) + ": Name: " + nameClean + "\n    Type: " + type);



				}else if(type.equals("Data Object")){
					//data object are not generated here but in their class that is called Documents.java
					System.out.println((i+1) + ": Name: " + nameClean + "\n    Type: " + type);

				}else {


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
					//comment.setTextContent("deny delete right for XWiki.XWikiGuest");
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

					String currentLane = "";
					for (int h=0; h<lunghezza; h++){

						Element elementConnector =(Element) connector.item(h);

						from = (Element) elementConnector.getChildNodes().item(1);
						to = (Element) elementConnector.getChildNodes().item(3);

						if(oldName.equals(from.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "")) &&
								elementConnector.getAttributes().getNamedItem("class").toString().equals("class=\"Is inside\"") ){

							currentLane = to.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "");
							comment.setTextContent("deny delete right for XWiki." + currentLane);
						}

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
							//	guidObj.setTextContent("ea383b6e-4b93-49e7-9c1e-849cfc7d11c5");
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

							Element groupsP2 = docFinale.createElement("groups");
							groupsP2.setTextContent("XWiki." + laneName);
							propertyG2.appendChild(groupsP2);

							object2.appendChild(propertyG2);

							Element propertyL2 = docFinale.createElement("property");

							Element levelsP2 = docFinale.createElement("levels");
							levelsP2.setTextContent("view,comment,edit,delete");
							propertyL2.appendChild(levelsP2);

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
					//	guidObj.setTextContent("ea383b6e-4b93-49e7-9c1e-849cfc7d11c5");
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


					//deny rights 
					Element objectEnd = docFinale.createElement("object");

					Element nameObjEnd = docFinale.createElement("name");
					nameObjEnd.setTextContent(spaceName + "." + nameClean);
					objectEnd.appendChild(nameObjEnd);

					Element numberObjEnd = docFinale.createElement("number");
					numberObjEnd.setTextContent((r+2) + "");
					objectEnd.appendChild(numberObjEnd);

					Element classNameObjEnd = docFinale.createElement("className");
					classNameObjEnd.setTextContent("XWiki.XWikiRights");
					objectEnd.appendChild(classNameObjEnd);

					Element guidObjEnd = docFinale.createElement("guid");
					//	guidObj.setTextContent("ea383b6e-4b93-49e7-9c1e-849cfc7d11c5");
					objectEnd.appendChild(guidObjEnd);

					//
					Element classObjEnd = docFinale.createElement("class");

					Element nameClassEnd = docFinale.createElement("name");
					nameClassEnd.setTextContent("XWiki.XWikiRights");
					classObjEnd.appendChild(nameClassEnd);

					Element customClassClassEnd = docFinale.createElement("customClass");
					classObjEnd.appendChild(customClassClassEnd);

					Element customMappingClassEnd = docFinale.createElement("customMapping");
					classObjEnd.appendChild(customMappingClassEnd);

					Element defaultViewSheetClassEnd = docFinale.createElement("defaultViewSheet");
					classObjEnd.appendChild(defaultViewSheetClassEnd);

					Element defaultEditSheetClassEnd = docFinale.createElement("defaultEditSheet");
					classObjEnd.appendChild(defaultEditSheetClassEnd);

					Element defaultWebClassEnd = docFinale.createElement("defaultWeb");
					classObjEnd.appendChild(defaultWebClassEnd);

					Element nameFieldClassEnd = docFinale.createElement("nameField");
					classObjEnd.appendChild(nameFieldClassEnd);

					Element validationScriptClassEnd = docFinale.createElement("validationScript");
					classObjEnd.appendChild(validationScriptClassEnd);

					Element allowClassEnd = docFinale.createElement("allow");

					Element defaultValueAEnd = docFinale.createElement("defaultValue");
					defaultValueAEnd.setTextContent("1");
					allowClassEnd.appendChild(defaultValueAEnd);

					Element disabledAEnd = docFinale.createElement("disabled");
					disabledAEnd.setTextContent("0");
					allowClassEnd.appendChild(disabledAEnd);

					Element displayFormTypeAEnd = docFinale.createElement("displayFormType");
					displayFormTypeAEnd.setTextContent("select");
					allowClassEnd.appendChild(displayFormTypeAEnd);

					Element displayTypeAEnd = docFinale.createElement("displayType");
					displayTypeAEnd.setTextContent("allow");
					allowClassEnd.appendChild(displayTypeAEnd);

					Element nameAEnd = docFinale.createElement("name");
					nameAEnd.setTextContent("allow");
					allowClassEnd.appendChild(nameAEnd);

					Element numberAEnd = docFinale.createElement("number");
					numberAEnd.setTextContent("4");
					allowClassEnd.appendChild(numberAEnd);

					Element prettyNameAEnd = docFinale.createElement("prettyName");
					prettyNameAEnd.setTextContent("Allow/Deny");
					allowClassEnd.appendChild(prettyNameAEnd);

					Element unmodifiableAEnd = docFinale.createElement("unmodifiable");
					unmodifiableAEnd.setTextContent("0");
					allowClassEnd.appendChild(unmodifiableAEnd);

					Element classTypeAEnd = docFinale.createElement("classType");
					classTypeAEnd.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
					allowClassEnd.appendChild(classTypeAEnd);

					classObjEnd.appendChild(allowClassEnd);

					Element groupsEnd = docFinale.createElement("groups");

					Element cacheGEnd = docFinale.createElement("cache");
					cacheGEnd.setTextContent("0");
					groupsEnd.appendChild(cacheGEnd);

					Element disabledGEnd = docFinale.createElement("disabled");
					disabledGEnd.setTextContent("0");
					groupsEnd.appendChild(disabledGEnd);

					Element displayTypeGEnd = docFinale.createElement("displayType");
					displayTypeGEnd.setTextContent("input");
					groupsEnd.appendChild(displayTypeGEnd);

					Element multiSelectGEnd = docFinale.createElement("multiSelect");
					multiSelectGEnd.setTextContent("1");
					groupsEnd.appendChild(multiSelectGEnd);

					Element nameGEnd = docFinale.createElement("name");
					nameGEnd.setTextContent("groups");
					groupsEnd.appendChild(nameGEnd);

					Element numberGEnd = docFinale.createElement("number");
					numberGEnd.setTextContent("1");
					groupsEnd.appendChild(numberGEnd);

					Element pickerGEnd = docFinale.createElement("picker");
					pickerGEnd.setTextContent("1");
					groupsEnd.appendChild(pickerGEnd);

					Element prettyNameGEnd = docFinale.createElement("prettyName");
					prettyNameGEnd.setTextContent("Groups");
					groupsEnd.appendChild(prettyNameGEnd);

					Element relationalStorageGEnd = docFinale.createElement("relationalStorage");
					relationalStorageGEnd.setTextContent("0");
					groupsEnd.appendChild(relationalStorageGEnd);

					Element separatorGEnd = docFinale.createElement("separator");
					separatorGEnd.setTextContent(" ");
					groupsEnd.appendChild(separatorGEnd);

					Element sizeGEnd = docFinale.createElement("size");
					sizeGEnd.setTextContent("5");
					groupsEnd.appendChild(sizeGEnd);

					Element unmodifiableGEnd = docFinale.createElement("unmodifiable");
					unmodifiableGEnd.setTextContent("0");
					groupsEnd.appendChild(unmodifiableGEnd);

					Element classTypeGEnd = docFinale.createElement("classType");
					classTypeGEnd.setTextContent("com.xpn.xwiki.objects.classes.GroupsClass");
					groupsEnd.appendChild(classTypeGEnd);

					classObjEnd.appendChild(groupsEnd);

					Element levelsEnd = docFinale.createElement("levels");

					Element cacheLEnd = docFinale.createElement("cache");
					cacheLEnd.setTextContent("0");
					levelsEnd.appendChild(cacheLEnd);

					Element disabledLEnd = docFinale.createElement("disabled");
					disabledLEnd.setTextContent("0");
					levelsEnd.appendChild(disabledLEnd);

					Element displayTypeLEnd = docFinale.createElement("displayType");
					displayTypeLEnd.setTextContent("select");
					levelsEnd.appendChild(displayTypeLEnd);

					Element multiSelectLEnd = docFinale.createElement("multiSelect");
					multiSelectLEnd.setTextContent("1");
					levelsEnd.appendChild(multiSelectLEnd);

					Element nameLEnd = docFinale.createElement("name");
					nameLEnd.setTextContent("levels");
					levelsEnd.appendChild(nameLEnd);

					Element numberLEnd = docFinale.createElement("number");
					numberLEnd.setTextContent("2");
					levelsEnd.appendChild(numberLEnd);

					Element prettyNameLEnd = docFinale.createElement("prettyName");
					prettyNameLEnd.setTextContent("Levels");
					levelsEnd.appendChild(prettyNameLEnd);

					Element relationalStorageLEnd = docFinale.createElement("relationalStorage");
					relationalStorageLEnd.setTextContent("0");
					levelsEnd.appendChild(relationalStorageLEnd);

					Element separatorLEnd = docFinale.createElement("separator");
					separatorLEnd.setTextContent(" ");
					levelsEnd.appendChild(separatorLEnd);

					Element sizeLEnd = docFinale.createElement("size");
					sizeLEnd.setTextContent("3");
					levelsEnd.appendChild(sizeLEnd);

					Element unmodifiableLEnd = docFinale.createElement("unmodifiable");
					unmodifiableLEnd.setTextContent("0");
					levelsEnd.appendChild(unmodifiableLEnd);

					Element classTypeLEnd = docFinale.createElement("classType");
					classTypeLEnd.setTextContent("com.xpn.xwiki.objects.classes.LevelsClass");
					levelsEnd.appendChild(classTypeLEnd);

					classObjEnd.appendChild(levelsEnd);

					Element usersEnd = docFinale.createElement("users");

					Element cacheUEnd = docFinale.createElement("cache");
					cacheUEnd.setTextContent("0");
					usersEnd.appendChild(cacheUEnd);

					Element disabledUEnd = docFinale.createElement("disabled");
					disabledUEnd.setTextContent("0");
					usersEnd.appendChild(disabledUEnd);

					Element displayTypeUEnd = docFinale.createElement("displayType");
					displayTypeUEnd.setTextContent("input");
					usersEnd.appendChild(displayTypeUEnd);

					Element multiSelectUEnd = docFinale.createElement("multiSelect");
					multiSelectUEnd.setTextContent("1");
					usersEnd.appendChild(multiSelectUEnd);

					Element nameUEnd = docFinale.createElement("name");
					nameUEnd.setTextContent("users");
					usersEnd.appendChild(nameUEnd);

					Element numberUEnd = docFinale.createElement("number");
					numberUEnd.setTextContent("3");
					usersEnd.appendChild(numberUEnd);

					Element pickerUEnd = docFinale.createElement("picker");
					pickerUEnd.setTextContent("1");
					usersEnd.appendChild(pickerUEnd);

					Element prettyNameUEnd = docFinale.createElement("prettyName");
					prettyNameUEnd.setTextContent("Users");
					usersEnd.appendChild(prettyNameUEnd);

					Element relationalStorageUEnd = docFinale.createElement("relationalStorage");
					relationalStorageUEnd.setTextContent("0");
					usersEnd.appendChild(relationalStorageUEnd);

					Element separatorUEnd = docFinale.createElement("separator");
					separatorUEnd.setTextContent(" ");
					usersEnd.appendChild(separatorUEnd);

					Element sizeUEnd = docFinale.createElement("size");
					sizeUEnd.setTextContent("5");
					usersEnd.appendChild(sizeUEnd);

					Element unmodifiableUEnd = docFinale.createElement("unmodifiable");
					unmodifiableUEnd.setTextContent("0");
					usersEnd.appendChild(unmodifiableUEnd);

					Element classTypeUEnd = docFinale.createElement("classType");
					classTypeUEnd.setTextContent("com.xpn.xwiki.objects.classes.UsersClass");
					usersEnd.appendChild(classTypeUEnd);

					classObjEnd.appendChild(usersEnd);

					objectEnd.appendChild(classObjEnd);


					Element propertyAEnd = docFinale.createElement("property");

					Element allowPEnd = docFinale.createElement("allow");
					allowPEnd.setTextContent("0");
					propertyAEnd.appendChild(allowPEnd);

					objectEnd.appendChild(propertyAEnd);

					Element propertyG2End = docFinale.createElement("property");

					Element groupsP2End = docFinale.createElement("groups");
					groupsP2End.setTextContent("XWiki." + currentLane);
					propertyG2End.appendChild(groupsP2End);

					objectEnd.appendChild(propertyG2End);

					Element propertyL2End = docFinale.createElement("property");

					Element levelsP2End = docFinale.createElement("levels");
					levelsP2End.setTextContent("edit,delete");
					propertyL2End.appendChild(levelsP2End);

					objectEnd.appendChild(propertyL2End);

					xwikidoc.appendChild(objectEnd);






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
					String testContent = "{{velocity}}\n{{html wiki=true}}";
					//NodeList connector = doc.getElementsByTagName("CONNECTOR");

					for (int j =0; j<elementi.getLength();j++){
						if(elementi.item(j).getAttributes().getNamedItem("name").toString().equals("name=\"Description\"")){
							// insert adoxx description
							testContent = testContent + "**Description:** " +  elementi.item(j).getTextContent() + "<br/><br/>";

							//}else if(elementi.item(j).getAttributes().getNamedItem("name").toString().equals("name=\"Comment\"")){
							// inserts adoxx comments
							//contenuto = contenuto + "\n\n**Comment:** " + elementi.item(j).getTextContent() + "<br/><br/>";
						}else if(elementi.item(j).getAttributes().getNamedItem("name").toString().equals("name=\"Open questions\"")){
							testContent = testContent + "\n\n**Open Questions:** " + elementi.item(j).getTextContent() + "<br/><br/>";
						}

						/**
						 * At the end of the loop we have to check if there are some links to the next activity of the process
						 * INSERT OF THE LINKS TO THE NEXT ACTIVITY OF THE PROCESS
						 */ 
						if (j==elementi.getLength()-1){
							if(instances.item(i).getAttributes().getNamedItem("class").toString().equals("class=\"Pool\"") || instances.item(i).getAttributes().getNamedItem("class").toString().equals("class=\"Lane\"") || instances.item(i).getAttributes().getNamedItem("class").toString().equals("class=\"Data Object\"")){
								content.setTextContent(testContent);
							}else if(instances.item(i).getAttributes().getNamedItem("class").toString().equals("class=\"End Event\"")){
								content.setTextContent(testContent + "<br/>=//**__Process Completed!__**// =" );
							}else if(!instances.item(i).getAttributes().getNamedItem("class").toString().equals("class=\"End Event\"")){
								content.setTextContent(testContent + "<br/>__Link to the Next Activity:__<br/><br/>"  + Connector.nextLinkCreator(connector, oldName, spaceName) + "{{/velocity}}" );
							}else{
								content.setTextContent(testContent);
							}
						}
					}

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
			}
			// insert at the bottom of the array the name of the file for starting the process
			arrayName[(arrayName.length)-1] = StartEvent.startEventGenerator(directoryBPMN, xwikiUser, doc, spaceName);

			System.out.println("\nReading of the ADOxx file complete!\nTotal number of instances: " + arrayLength +  "\nDone!\n");


			// Packager generator
			return arrayName;

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		return arrayName;
	}
}
