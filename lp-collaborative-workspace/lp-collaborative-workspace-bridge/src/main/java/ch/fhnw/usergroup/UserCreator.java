package ch.fhnw.usergroup;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class UserCreator {


	public static void userCreator(Element workingEnvironment, JFileChooser directoryCompany) throws Exception {




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

		//		Element model = (Element) docCompany.getElementsByTagName("ADOXML").item(0).getChildNodes().item(1).getChildNodes().item(1);

		// CORRECT BPMN FILE CONTROL
		//		if(!model.getAttributes().getNamedItem("modeltype").toString().equals("modeltype=\"Working environment model\"")){
		//			System.out.println("WORKING ENVIRONMENT FILE IS NOT CORRECT");
		//TODO insert interrupt
		//			return "";
		//		}
		//		else if(model.getAttributes().getNamedItem("modeltype").toString().equals("modeltype=\"Working environment model\""))
		//		{

		NodeList instances = workingEnvironment.getElementsByTagName("INSTANCE");


		int arrayLength = instances.getLength();
		//String[] arrayName = new String[arrayLength+1];

		//String oldName;
		//String nomeCorretto;
		//String type;

		String employee;
		String employeeName;

		String emailEmployee="";

		for(int i=0; i<arrayLength;i++){

			//get the info from the other file
			Element attributes = (Element) instances.item(i);

			employee = attributes.getAttributes().getNamedItem("class").toString().replaceFirst("class=\"", "").replaceAll("\"", "");

			//	System.out.println(attributes.getAttributes().getNamedItem("class").toString().replaceFirst("class=\"", "").replaceAll("\"", "")
			//		+ " = " + attributes.getAttributes().getNamedItem("name").toString().replaceFirst("name=\"", "").replaceAll("\"", ""));

			//System.out.println("LUNGHEZZA ARRAY" + arrayLength);

			employeeName = attributes.getAttributes().getNamedItem("name").toString().replaceFirst("name=\"", "").replaceAll("\"", "");

			if(employee.equals("Performer")){

				NodeList attribute = attributes.getElementsByTagName("ATTRIBUTE");
				////////////////////////////////////////////////


				int arrayAttributeLength = attribute.getLength(); 

				for(int j=0;j<arrayAttributeLength;j++){
					Node eElementi = attribute.item(j);
					Element attributess = (Element) eElementi;

					if(attributess.getAttributes().getNamedItem("name").toString().equals("name=\"Description\"") ){
						emailEmployee=attributess.getTextContent().toString();
						//System.out.println("EMAILLLLLLLLLLLLLLLLLLLLLLLLL " + emailEmployee + " PORCA DI QUELLA TROIA");
					}
				}






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
				xwikidoc.setAttribute("versions", "1.1");
				newDocument.appendChild(xwikidoc);

				Element web = newDocument.createElement("web");
				web.setTextContent("XWiki");
				xwikidoc.appendChild(web);


				Element name = newDocument.createElement("name");
				name.setTextContent(employeeName);
				xwikidoc.appendChild(name);

				Element language = newDocument.createElement("language");
				xwikidoc.appendChild(language);

				Element defaultLanguage = newDocument.createElement("defaultLanguage");
				xwikidoc.appendChild(defaultLanguage);

				Element translation = newDocument.createElement("translation");
				translation.setTextContent("0");
				xwikidoc.appendChild(translation);

				Element creator = newDocument.createElement("creator");
				creator.setTextContent("XWiki." + employeeName);
				xwikidoc.appendChild(creator);

				Element creationDate = newDocument.createElement("creationDate");
				creationDate.setTextContent("1401899176000");
				xwikidoc.appendChild(creationDate);

				Element parent = newDocument.createElement("parent");
				parent.setTextContent("xwiki:Main.UserDirectory");
				xwikidoc.appendChild(parent);

				Element author = newDocument.createElement("author");
				author.setTextContent("XWiki." + employeeName);
				xwikidoc.appendChild(author);

				Element contentAuthor = newDocument.createElement("contentAuthor");
				contentAuthor.setTextContent("XWiki." + employeeName);
				xwikidoc.appendChild(contentAuthor);

				Element date = newDocument.createElement("date");
				date.setTextContent("1401899176000");
				xwikidoc.appendChild(date);

				Element contentUpdateDate = newDocument.createElement("contentUpdateDate");
				contentUpdateDate.setTextContent("1401899176000");
				xwikidoc.appendChild(contentUpdateDate);

				Element version = newDocument.createElement("version");
				version.setTextContent("1.3");
				xwikidoc.appendChild(version);

				Element title = newDocument.createElement("title");
				xwikidoc.appendChild(title);

				Element updateWatchlist = newDocument.createElement("comment");
				updateWatchlist.setTextContent("Updated WatchList");
				xwikidoc.appendChild(updateWatchlist);

				Element minorEdit = newDocument.createElement("minorEdit");
				minorEdit.setTextContent("true");
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
				////////////////////////////////////////xwikidoc.appendChild(object);

				Element nameObject = newDocument.createElement("name");
				nameObject.setTextContent("XWiki." + employeeName);
				object.appendChild(nameObject);

				Element number = newDocument.createElement("number");
				number.setTextContent("0");
				object.appendChild(number);

				Element className = newDocument.createElement("className");
				className.setTextContent("XWiki.WatchListClass");
				object.appendChild(className);

				Element guid = newDocument.createElement("guid");
				guid.setTextContent("4dc9a532-7f9d-45da-9b6b-4fb17e5502e8");
				object.appendChild(guid);

				Element classObject = newDocument.createElement("class");

				///////////////////object.appendChild(classObject);

				Element nameClass = newDocument.createElement("name");
				nameClass.setTextContent("XWiki.WatchListClass");
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

				Element documents = newDocument.createElement("documents");
				/////////////

				Element disabledDocuments = newDocument.createElement("disabled");
				disabledDocuments.setTextContent("0");
				documents.appendChild(disabledDocuments);

				Element nameDocuments = newDocument.createElement("name");
				nameDocuments.setTextContent("documents");
				documents.appendChild(nameDocuments);

				Element numberDocuments = newDocument.createElement("number");
				numberDocuments.setTextContent("4");
				documents.appendChild(numberDocuments);

				Element prettyNameDocuments = newDocument.createElement("prettyName");
				prettyNameDocuments.setTextContent("Document list");
				documents.appendChild(prettyNameDocuments);

				Element rows = newDocument.createElement("rows");
				rows.setTextContent("5");
				documents.appendChild(rows);

				Element sizeDocument = newDocument.createElement("size");
				sizeDocument.setTextContent("80");
				documents.appendChild(sizeDocument);

				Element unmodifiableDocument = newDocument.createElement("unmodifiable");
				unmodifiableDocument.setTextContent("0");
				documents.appendChild(unmodifiableDocument);

				Element classTypeDocument = newDocument.createElement("classType");
				classTypeDocument.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				documents.appendChild(classTypeDocument);

				classObject.appendChild(documents);

				Element interval = newDocument.createElement("interval");

				Element cacheInterval = newDocument.createElement("cache");
				cacheInterval.setTextContent("0");
				interval.appendChild(cacheInterval);

				Element disabledInterval = newDocument.createElement("disabled");
				disabledInterval.setTextContent("0");
				interval.appendChild(disabledInterval);

				Element displayTypeInterval = newDocument.createElement("displayType");
				displayTypeInterval.setTextContent("select");
				interval.appendChild(displayTypeInterval);

				Element multiSelectInterval = newDocument.createElement("multiSelect");
				multiSelectInterval.setTextContent("0");
				interval.appendChild(multiSelectInterval);

				Element nameInterval = newDocument.createElement("name");
				nameInterval.setTextContent("interval");
				interval.appendChild(nameInterval);

				Element numberInterval = newDocument.createElement("number");
				numberInterval.setTextContent("1");
				interval.appendChild(numberInterval);

				Element prettyNameInterval = newDocument.createElement("prettyName");
				prettyNameInterval.setTextContent("Email notifications interval");
				interval.appendChild(prettyNameInterval);

				Element relationalStorageInterval = newDocument.createElement("relationalStorage");
				relationalStorageInterval.setTextContent("0");
				interval.appendChild(relationalStorageInterval);

				Element separatorInterval = newDocument.createElement("separator");
				separatorInterval.setTextContent(" ");
				interval.appendChild(separatorInterval);

				Element separatorsInterval = newDocument.createElement("separators");
				separatorsInterval.setTextContent(" ,|");
				interval.appendChild(separatorsInterval);

				Element sizeInterval = newDocument.createElement("size");
				sizeInterval.setTextContent("1");
				interval.appendChild(sizeInterval);

				Element unmodifiableInterval = newDocument.createElement("unmodifiable");
				unmodifiableInterval.setTextContent("0");
				interval.appendChild(unmodifiableInterval);

				Element valuesInterval = newDocument.createElement("values");
				valuesInterval.setTextContent("Scheduler.WatchListDailyNotifier|Scheduler.WatchListHourlyNotifier|Scheduler.WatchListWeeklyNotifier");
				interval.appendChild(valuesInterval);

				Element classTypeInterval = newDocument.createElement("classType");
				classTypeInterval.setTextContent("com.xpn.xwiki.objects.classes.StaticListClass");
				interval.appendChild(classTypeInterval);

				classObject.appendChild(interval);

				Element spaces = newDocument.createElement("spaces");

				Element disabledSpaces = newDocument.createElement("disabled");
				disabledSpaces.setTextContent("0");
				spaces.appendChild(disabledSpaces);

				Element nameSpaces = newDocument.createElement("name");
				nameSpaces.setTextContent("spaces");
				spaces.appendChild(nameSpaces);

				Element numberSpaces = newDocument.createElement("number");
				numberSpaces.setTextContent("3");
				spaces.appendChild(numberSpaces);

				Element prettyNameSpaces = newDocument.createElement("prettyName");
				prettyNameSpaces.setTextContent("Space list");
				spaces.appendChild(prettyNameSpaces);

				Element rowsSpaces = newDocument.createElement("rows");
				rowsSpaces.setTextContent("5");
				spaces.appendChild(rowsSpaces);

				Element sizeSpaces = newDocument.createElement("size");
				sizeSpaces.setTextContent("80");
				spaces.appendChild(sizeSpaces);

				Element unmodifiableSpaces = newDocument.createElement("unmodifiable");
				unmodifiableSpaces.setTextContent("0");
				spaces.appendChild(unmodifiableSpaces);

				Element classTypeSpaces = newDocument.createElement("classType");
				classTypeSpaces.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				spaces.appendChild(classTypeSpaces);

				classObject.appendChild(spaces);

				Element users = newDocument.createElement("users");

				Element disabledUsers = newDocument.createElement("disabled");
				disabledUsers.setTextContent("0");
				users.appendChild(disabledUsers);

				Element nameUsers = newDocument.createElement("name");
				nameUsers.setTextContent("users");
				users.appendChild(nameUsers);

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

				Element wikis = newDocument.createElement("wikis");

				Element disabledWikis = newDocument.createElement("disabled");
				disabledWikis.setTextContent("0");
				wikis.appendChild(disabledWikis);

				Element nameWikis = newDocument.createElement("name");
				nameWikis.setTextContent("wikis");
				wikis.appendChild(nameWikis);

				Element numberWikis = newDocument.createElement("number");
				numberWikis.setTextContent("2");
				wikis.appendChild(numberWikis);

				Element prettyNameWikis = newDocument.createElement("prettyName");
				prettyNameWikis.setTextContent("Wiki list");
				wikis.appendChild(prettyNameWikis);

				Element rowsWikis = newDocument.createElement("rows");
				rowsWikis.setTextContent("5");
				wikis.appendChild(rowsWikis);

				Element sizeWikis = newDocument.createElement("size");
				sizeWikis.setTextContent("80");
				wikis.appendChild(sizeWikis);

				Element unmodifiableWikis = newDocument.createElement("unmodifiable");
				unmodifiableWikis.setTextContent("0");
				wikis.appendChild(unmodifiableWikis);

				Element classTypeWikis = newDocument.createElement("classType");
				classTypeWikis.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				wikis.appendChild(classTypeWikis);

				classObject.appendChild(wikis);

				object.appendChild(classObject);

				Element property = newDocument.createElement("property");

				Element documentsProperty = newDocument.createElement("documents");
				documentsProperty.setTextContent("xwiki:XWiki." + employeeName);
				property.appendChild(documentsProperty);

				object.appendChild(property);

				xwikidoc.appendChild(object);



				Element object2 = newDocument.createElement("object");
				////////////////////////////////////////xwikidoc.appendChild(object);

				Element nameObject2 = newDocument.createElement("name");
				nameObject2.setTextContent("XWiki." + employeeName);
				object2.appendChild(nameObject2);

				Element number2 = newDocument.createElement("number");
				number2.setTextContent("0");
				object2.appendChild(number2);

				Element className2 = newDocument.createElement("className");
				className2.setTextContent("XWiki.XWikiRights");
				object2.appendChild(className2);

				Element guid2 = newDocument.createElement("guid");
				guid2.setTextContent("cea8996b-f8b4-4e80-a49a-2f8d94bcf632");
				object2.appendChild(guid2);

				Element classObject2 = newDocument.createElement("class");

				///////////////////object.appendChild(classObject);

				Element nameClass2 = newDocument.createElement("name");
				nameClass2.setTextContent("XWiki.XWikiRights");
				classObject2.appendChild(nameClass2);

				Element customClassObject2 = newDocument.createElement("customClass");
				classObject2.appendChild(customClassObject2);

				Element customMapping2 = newDocument.createElement("customMapping");
				classObject2.appendChild(customMapping2);

				Element defaultViewSheet2 = newDocument.createElement("defaultViewSheet");
				classObject2.appendChild(defaultViewSheet2);

				Element defaultEditSheet2 = newDocument.createElement("defaultEditSheet");
				classObject2.appendChild(defaultEditSheet2);

				Element defaultWeb2 = newDocument.createElement("defaultWeb");
				classObject2.appendChild(defaultWeb2);

				Element nameField2 = newDocument.createElement("nameField");
				classObject2.appendChild(nameField2);

				Element validationScriptObject2 = newDocument.createElement("validationScript");
				classObject2.appendChild(validationScriptObject2);


				Element allow = newDocument.createElement("allow");
				////////////////

				Element defaultValue = newDocument.createElement("defaultValue");
				defaultValue.setTextContent("1");
				allow.appendChild(defaultValue);

				Element disabled2 = newDocument.createElement("disabled");
				disabled2.setTextContent("0");
				allow.appendChild(disabled2);

				Element displayFormType = newDocument.createElement("displayFormType");
				displayFormType.setTextContent("select");
				allow.appendChild(displayFormType);

				Element displayType2 = newDocument.createElement("displayType");
				displayType2.setTextContent("allow");
				allow.appendChild(displayType2);

				Element nameAllow = newDocument.createElement("name");
				nameAllow.setTextContent("allow");
				allow.appendChild(nameAllow);

				Element numberAllow = newDocument.createElement("number");
				numberAllow.setTextContent("4");
				allow.appendChild(numberAllow);

				Element prettyName2 = newDocument.createElement("prettyName");
				prettyName2.setTextContent("Allow/Deny");
				allow.appendChild(prettyName2);

				Element unmodifiable2 = newDocument.createElement("unmodifiable");
				unmodifiable2.setTextContent("0");
				allow.appendChild(unmodifiable2);

				Element classType2 = newDocument.createElement("classType");
				classType2.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
				allow.appendChild(classType2);

				classObject2.appendChild(allow);

				Element groups = newDocument.createElement("groups");
				/////////////xwikidoc.appendChild(documents);


				Element cache2 = newDocument.createElement("cache");
				cache2.setTextContent("0");
				groups.appendChild(cache2);

				Element disabledDocuments2 = newDocument.createElement("disabled");
				disabledDocuments2.setTextContent("0");
				groups.appendChild(disabledDocuments2);

				Element displayType3 = newDocument.createElement("displayType");
				displayType3.setTextContent("input");
				groups.appendChild(displayType3);

				Element multiSelect2 = newDocument.createElement("multiSelect");
				multiSelect2.setTextContent("1");
				groups.appendChild(multiSelect2);

				Element nameGroups = newDocument.createElement("name");
				nameGroups.setTextContent("groups");
				groups.appendChild(nameGroups);

				Element numberGroups = newDocument.createElement("number");
				numberGroups.setTextContent("1");
				groups.appendChild(numberGroups);

				Element picker = newDocument.createElement("picker");
				picker.setTextContent("1");
				groups.appendChild(picker);

				Element prettyNameGroups = newDocument.createElement("prettyName");
				prettyNameGroups.setTextContent("Groups");
				groups.appendChild(prettyNameGroups);

				Element relationalStorage2 = newDocument.createElement("relationalStorage");
				relationalStorage2.setTextContent("0");
				groups.appendChild(relationalStorage2);

				Element separatorGroups = newDocument.createElement("separator");
				separatorGroups.setTextContent(" ");
				groups.appendChild(separatorGroups);

				Element sizeGroups = newDocument.createElement("size");
				sizeGroups.setTextContent("5");
				groups.appendChild(sizeGroups);

				Element unmodifiable3 = newDocument.createElement("unmodifiable");
				unmodifiable3.setTextContent("0");
				groups.appendChild(unmodifiable3);

				Element classType3 = newDocument.createElement("classType");
				classType3.setTextContent("com.xpn.xwiki.objects.classes.GroupsClass");
				groups.appendChild(classType3);

				classObject2.appendChild(groups);


				Element levels = newDocument.createElement("levels");

				Element cacheLevels = newDocument.createElement("cache");
				cacheLevels.setTextContent("0");
				levels.appendChild(cacheLevels);

				Element disabledLevels = newDocument.createElement("disabled");
				disabledLevels.setTextContent("0");
				levels.appendChild(disabledLevels);

				Element displayTypeLevels = newDocument.createElement("displayType");
				displayTypeLevels.setTextContent("select");
				levels.appendChild(displayTypeLevels);

				Element multiSelectLevels = newDocument.createElement("multiSelect");
				multiSelectLevels.setTextContent("1");
				levels.appendChild(multiSelectLevels);

				Element nameLevels = newDocument.createElement("name");
				nameLevels.setTextContent("levels");
				levels.appendChild(nameLevels);

				Element numberLevels = newDocument.createElement("number");
				numberLevels.setTextContent("2");
				levels.appendChild(numberLevels);

				Element prettyNameLevels = newDocument.createElement("prettyName");
				prettyNameLevels.setTextContent("Levels");
				levels.appendChild(prettyNameLevels);

				Element relationalStorageLevels = newDocument.createElement("relationalStorage");
				relationalStorageLevels.setTextContent("0");
				levels.appendChild(relationalStorageLevels);

				Element separatorLevels = newDocument.createElement("separator");
				separatorLevels.setTextContent(" ");
				levels.appendChild(separatorLevels);

				Element sizeLevels = newDocument.createElement("size");
				sizeLevels.setTextContent("3");
				levels.appendChild(sizeLevels);

				Element unmodifiableLevels = newDocument.createElement("unmodifiable");
				unmodifiableLevels.setTextContent("0");
				levels.appendChild(unmodifiableLevels);

				Element classTypeLevels = newDocument.createElement("classType");
				classTypeLevels.setTextContent("com.xpn.xwiki.objects.classes.LevelsClass");
				levels.appendChild(classTypeLevels);

				classObject2.appendChild(levels);

				Element users2 = newDocument.createElement("users");

				Element cacheUsers = newDocument.createElement("cache");
				cacheUsers.setTextContent("0");
				users2.appendChild(cacheUsers);

				Element disabledUsers2 = newDocument.createElement("disabled");
				disabledUsers2.setTextContent("0");
				users2.appendChild(disabledUsers2);

				Element displayTypeUsers = newDocument.createElement("displayType");
				displayTypeUsers.setTextContent("input");
				users2.appendChild(displayTypeUsers);

				Element multiSelectUsers = newDocument.createElement("multiSelect");
				multiSelectUsers.setTextContent("1");
				users2.appendChild(multiSelectUsers);

				Element nameUsers2 = newDocument.createElement("name");
				nameUsers2.setTextContent("users");
				users2.appendChild(nameUsers2);

				Element numberUsers2 = newDocument.createElement("number");
				numberUsers2.setTextContent("3");
				users2.appendChild(numberUsers2);

				Element pickerUsers2 = newDocument.createElement("picker");
				pickerUsers2.setTextContent("1");
				users2.appendChild(pickerUsers2);

				Element prettyNameUsers2 = newDocument.createElement("prettyName");
				prettyNameUsers2.setTextContent("Users");
				users2.appendChild(prettyNameUsers2);

				Element relationalStorage3 = newDocument.createElement("relationalStorage");
				relationalStorage3.setTextContent("0");
				users2.appendChild(relationalStorage3);

				Element separatorUsers = newDocument.createElement("separator");
				separatorUsers.setTextContent(" ");
				users2.appendChild(separatorUsers);

				Element sizeUsers2 = newDocument.createElement("size");
				sizeUsers2.setTextContent("5");
				users2.appendChild(sizeUsers2);

				Element unmodifiableUsers2 = newDocument.createElement("unmodifiable");
				unmodifiableUsers2.setTextContent("0");
				users2.appendChild(unmodifiableUsers2);

				Element classTypeUsers2 = newDocument.createElement("classType");
				classTypeUsers2.setTextContent("com.xpn.xwiki.objects.classes.UsersClass");
				users2.appendChild(classTypeUsers2);

				classObject2.appendChild(users2);

				object2.appendChild(classObject2);

				Element property2 = newDocument.createElement("property");

				Element allowProperty = newDocument.createElement("allow");
				allowProperty.setTextContent("1");
				property2.appendChild(allowProperty);

				object2.appendChild(property2);

				Element property3 = newDocument.createElement("property");

				Element levelsProperty = newDocument.createElement("levels");
				levelsProperty.setTextContent("edit");
				property3.appendChild(levelsProperty);

				object2.appendChild(property3);

				Element property4 = newDocument.createElement("property");

				Element usersProperty = newDocument.createElement("users");
				usersProperty.setTextContent("XWiki." + employeeName);
				property4.appendChild(usersProperty);

				object2.appendChild(property4);

				xwikidoc.appendChild(object2);




				Element object3 = newDocument.createElement("object");
				////////////////////////////////////////xwikidoc.appendChild(object);

				Element nameObject3 = newDocument.createElement("name");
				nameObject3.setTextContent("XWiki." + employeeName);
				object3.appendChild(nameObject3);

				Element number3 = newDocument.createElement("number");
				number3.setTextContent("0");
				object3.appendChild(number3);

				Element className3 = newDocument.createElement("className");
				className3.setTextContent("XWiki.XWikiUsers");
				object3.appendChild(className3);

				Element guid3 = newDocument.createElement("guid");
				guid3.setTextContent("4da634b9-3be0-4d00-bc66-e491785b1ec4");
				object3.appendChild(guid3);

				Element classObject3 = newDocument.createElement("class");

				///////////////////object.appendChild(classObject);

				Element nameClass3 = newDocument.createElement("name");
				nameClass3.setTextContent("XWiki.XWikiUsers");
				classObject3.appendChild(nameClass3);

				Element customClassObject3 = newDocument.createElement("customClass");
				classObject3.appendChild(customClassObject3);

				Element customMapping3 = newDocument.createElement("customMapping");
				classObject3.appendChild(customMapping3);

				Element defaultViewSheet3 = newDocument.createElement("defaultViewSheet");
				classObject3.appendChild(defaultViewSheet3);

				Element defaultEditSheet3 = newDocument.createElement("defaultEditSheet");
				classObject3.appendChild(defaultEditSheet3);

				Element defaultWeb3 = newDocument.createElement("defaultWeb");
				classObject3.appendChild(defaultWeb3);

				Element nameField3 = newDocument.createElement("nameField");
				classObject3.appendChild(nameField3);

				Element validationScriptObject3 = newDocument.createElement("validationScript");
				classObject3.appendChild(validationScriptObject3);


				Element accessibility = newDocument.createElement("accessibility");
				////////////////

				Element disabled3 = newDocument.createElement("disabled");
				disabled3.setTextContent("0");
				accessibility.appendChild(disabled3);

				Element displayFormType2 = newDocument.createElement("displayFormType");
				displayFormType2.setTextContent("select");
				accessibility.appendChild(displayFormType2);

				Element displayType4 = newDocument.createElement("displayType");
				displayType4.setTextContent("yesno");
				accessibility.appendChild(displayType4);

				Element nameAccessibility = newDocument.createElement("name");
				nameAccessibility.setTextContent("accessibility");
				accessibility.appendChild(nameAccessibility);

				Element numberAccessibility = newDocument.createElement("number");
				numberAccessibility.setTextContent("16");
				accessibility.appendChild(numberAccessibility);

				Element prettyName3 = newDocument.createElement("prettyName");
				prettyName3.setTextContent("Enable extra accessibility features");
				accessibility.appendChild(prettyName3);

				Element unmodifiable4 = newDocument.createElement("unmodifiable");
				unmodifiable4.setTextContent("0");
				accessibility.appendChild(unmodifiable4);

				Element classType4 = newDocument.createElement("classType");
				classType4.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
				accessibility.appendChild(classType4);

				classObject3.appendChild(accessibility);

				Element active = newDocument.createElement("active");
				/////////////xwikidoc.appendChild(documents);

				Element disabledDocuments4 = newDocument.createElement("disabled");
				disabledDocuments4.setTextContent("0");
				active.appendChild(disabledDocuments4);

				Element displayFormType3 = newDocument.createElement("displayFormType");
				displayFormType3.setTextContent("select");
				active.appendChild(displayFormType3);

				Element displayType5 = newDocument.createElement("displayType");
				displayType5.setTextContent("active");
				active.appendChild(displayType5);

				Element nameActive = newDocument.createElement("name");
				nameActive.setTextContent("active");
				active.appendChild(nameActive);

				Element numberActive = newDocument.createElement("number");
				numberActive.setTextContent("6");
				active.appendChild(numberActive);

				Element prettyNameActive = newDocument.createElement("prettyName");
				prettyNameActive.setTextContent("Active");
				active.appendChild(prettyNameActive);

				Element unmodifiable5 = newDocument.createElement("unmodifiable");
				unmodifiable5.setTextContent("0");
				active.appendChild(unmodifiable5);

				Element classType5 = newDocument.createElement("classType");
				classType5.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
				active.appendChild(classType5);

				classObject3.appendChild(active);


				Element address = newDocument.createElement("address");

				Element disabledAddress = newDocument.createElement("disabled");
				disabledAddress.setTextContent("0");
				address.appendChild(disabledAddress);

				Element nameAddress = newDocument.createElement("name");
				nameAddress.setTextContent("address");
				address.appendChild(nameAddress);

				Element numberAddress = newDocument.createElement("number");
				numberAddress.setTextContent("22");
				address.appendChild(numberAddress);

				Element prettyNameAddress = newDocument.createElement("prettyName");
				prettyNameAddress.setTextContent("Address");
				address.appendChild(prettyNameAddress);

				Element rowsAddress = newDocument.createElement("rows");
				rowsAddress.setTextContent("3");
				address.appendChild(rowsAddress);

				Element sizeAddress = newDocument.createElement("size");
				sizeAddress.setTextContent("40");
				address.appendChild(sizeAddress);

				Element unmodifiableAddress = newDocument.createElement("unmodifiable");
				unmodifiableAddress.setTextContent("0");
				address.appendChild(unmodifiableAddress);

				Element classTypeAddress = newDocument.createElement("classType");
				classTypeAddress.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				address.appendChild(classTypeAddress);

				classObject3.appendChild(address);

				Element avatar = newDocument.createElement("avatar");

				Element disabled4 = newDocument.createElement("disabled");
				disabled4.setTextContent("0");
				avatar.appendChild(disabled4);

				Element name2 = newDocument.createElement("name");
				name2.setTextContent("avatar");
				avatar.appendChild(name2);

				Element number4 = newDocument.createElement("number");
				number4.setTextContent("20");
				avatar.appendChild(number4);

				Element prettyName4 = newDocument.createElement("prettyName");
				prettyName4.setTextContent("Avatar");
				avatar.appendChild(prettyName4);

				Element size2 = newDocument.createElement("size");
				size2.setTextContent("30");
				avatar.appendChild(size2);

				Element unmodifiable6 = newDocument.createElement("unmodifiable");
				unmodifiable6.setTextContent("0");
				avatar.appendChild(unmodifiable6);

				Element classType6 = newDocument.createElement("classType");
				classType6.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				avatar.appendChild(classType6);

				classObject3.appendChild(avatar);


				Element blog = newDocument.createElement("blog");

				Element disabledBlog = newDocument.createElement("disabled");
				disabledBlog.setTextContent("0");
				blog.appendChild(disabledBlog);

				Element nameBlog = newDocument.createElement("name");
				nameBlog.setTextContent("blog");
				blog.appendChild(nameBlog);

				Element numberBlog = newDocument.createElement("number");
				numberBlog.setTextContent("9");
				blog.appendChild(numberBlog);

				Element prettyNameBlog = newDocument.createElement("prettyName");
				prettyNameBlog.setTextContent("Blog");
				blog.appendChild(prettyNameBlog);

				Element sizeBlog = newDocument.createElement("size");
				sizeBlog.setTextContent("60");
				blog.appendChild(sizeBlog);

				Element unmodifiableBlog = newDocument.createElement("unmodifiable");
				unmodifiableBlog.setTextContent("0");
				blog.appendChild(unmodifiableBlog);

				Element classTypeBlog = newDocument.createElement("classType");
				classTypeBlog.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				blog.appendChild(classTypeBlog);

				classObject3.appendChild(blog);


				Element blogfeed = newDocument.createElement("blogfeed");

				Element disabledBlogfeed = newDocument.createElement("disabled");
				disabledBlogfeed.setTextContent("0");
				blogfeed.appendChild(disabledBlogfeed);


				Element nameBlogfeed = newDocument.createElement("name");
				nameBlogfeed.setTextContent("blogfeed");
				blogfeed.appendChild(nameBlogfeed);

				Element numberBlogfeed = newDocument.createElement("number");
				numberBlogfeed.setTextContent("10");
				blogfeed.appendChild(numberBlogfeed);

				Element prettyNameBlogfeed = newDocument.createElement("prettyName");
				prettyNameBlogfeed.setTextContent("Blog Feed");
				blogfeed.appendChild(prettyNameBlogfeed);

				Element sizeBlogfeed = newDocument.createElement("size");
				sizeBlogfeed.setTextContent("60");
				blogfeed.appendChild(sizeBlogfeed);

				Element unmodifiableBlogfeed = newDocument.createElement("unmodifiable");
				unmodifiableBlogfeed.setTextContent("0");
				blogfeed.appendChild(unmodifiableBlogfeed);

				Element classTypeBlogfeed = newDocument.createElement("classType");
				classTypeBlogfeed.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				blogfeed.appendChild(classTypeBlogfeed);

				classObject3.appendChild(blogfeed);

				Element comment2 = newDocument.createElement("comment");

				Element disabledComment2 = newDocument.createElement("disabled");
				disabledComment2.setTextContent("0");
				comment2.appendChild(disabledComment2);

				Element nameComment2 = newDocument.createElement("name");
				nameComment2.setTextContent("comment");
				comment2.appendChild(nameComment2);

				Element numberComment2 = newDocument.createElement("number");
				numberComment2.setTextContent("11");
				comment2.appendChild(numberComment2);

				Element prettyNameComment2 = newDocument.createElement("prettyName");
				prettyNameComment2.setTextContent("Comment");
				comment2.appendChild(prettyNameComment2);

				Element rowsComment2 = newDocument.createElement("rows");
				rowsComment2.setTextContent("5");
				comment2.appendChild(rowsComment2);

				Element sizeComment2 = newDocument.createElement("size");
				sizeComment2.setTextContent("40");
				comment2.appendChild(sizeComment2);

				Element unmodifiableComment2 = newDocument.createElement("unmodifiable");
				unmodifiableComment2.setTextContent("0");
				comment2.appendChild(unmodifiableComment2);

				Element classTypeComment2 = newDocument.createElement("classType");
				classTypeComment2.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				comment2.appendChild(classTypeComment2);

				classObject3.appendChild(comment2);

				Element company = newDocument.createElement("company");

				Element disabledcompany = newDocument.createElement("disabled");
				disabledcompany.setTextContent("0");
				company.appendChild(disabledcompany);

				Element namecompany = newDocument.createElement("name");
				namecompany.setTextContent("company");
				company.appendChild(namecompany);

				Element numbercompany = newDocument.createElement("number");
				numbercompany.setTextContent("8");
				company.appendChild(numbercompany);

				Element prettyNamecompany = newDocument.createElement("prettyName");
				prettyNamecompany.setTextContent("Company");
				company.appendChild(prettyNamecompany);

				Element sizecompany = newDocument.createElement("size");
				sizecompany.setTextContent("30");
				company.appendChild(sizecompany);

				Element unmodifiablecompany = newDocument.createElement("unmodifiable");
				unmodifiablecompany.setTextContent("0");
				company.appendChild(unmodifiablecompany);

				Element classTypecompany = newDocument.createElement("classType");
				classTypecompany.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				company.appendChild(classTypecompany);

				classObject3.appendChild(company);

				Element default_language = newDocument.createElement("default_language");

				Element disableddefault_language = newDocument.createElement("disabled");
				disableddefault_language.setTextContent("0");
				default_language.appendChild(disableddefault_language);

				Element namedefault_language = newDocument.createElement("name");
				namedefault_language.setTextContent("default_language");
				default_language.appendChild(namedefault_language);

				Element numberdefault_language = newDocument.createElement("number");
				numberdefault_language.setTextContent("7");
				default_language.appendChild(numberdefault_language);

				Element prettyNamedefault_language = newDocument.createElement("prettyName");
				prettyNamedefault_language.setTextContent("Default Language");
				default_language.appendChild(prettyNamedefault_language);

				Element sizedefault_language = newDocument.createElement("size");
				sizedefault_language.setTextContent("30");
				default_language.appendChild(sizedefault_language);

				Element unmodifiabledefault_language = newDocument.createElement("unmodifiable");
				unmodifiabledefault_language.setTextContent("0");
				default_language.appendChild(unmodifiabledefault_language);

				Element classTypedefault_language = newDocument.createElement("classType");
				classTypedefault_language.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				default_language.appendChild(classTypedefault_language);

				classObject3.appendChild(default_language);

				Element displayHiddenDocuments = newDocument.createElement("displayHiddenDocuments");

				Element disabledhidden = newDocument.createElement("disabled");
				disabledhidden.setTextContent("0");
				displayHiddenDocuments.appendChild(disabledhidden);

				Element displayFormTypehidden = newDocument.createElement("displayFormType");
				displayFormTypehidden.setTextContent("select");
				displayHiddenDocuments.appendChild(displayFormTypehidden);

				Element displayTypehidden = newDocument.createElement("displayType");
				displayTypehidden.setTextContent("yesno");
				displayHiddenDocuments.appendChild(displayTypehidden);

				Element namehidden = newDocument.createElement("name");
				namehidden.setTextContent("displayHiddenDocuments");
				displayHiddenDocuments.appendChild(namehidden);

				Element numberhidden = newDocument.createElement("number");
				numberhidden.setTextContent("17");
				displayHiddenDocuments.appendChild(numberhidden);

				Element prettyNamehidden = newDocument.createElement("prettyName");
				prettyNamehidden.setTextContent("Display Hidden Documents");
				displayHiddenDocuments.appendChild(prettyNamehidden);

				Element unmodifiablehidden = newDocument.createElement("unmodifiable");
				unmodifiablehidden.setTextContent("0");
				displayHiddenDocuments.appendChild(unmodifiablehidden);

				Element classTypehidden = newDocument.createElement("classType");
				classTypehidden.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
				displayHiddenDocuments.appendChild(classTypehidden);

				classObject3.appendChild(displayHiddenDocuments);

				Element editor = newDocument.createElement("editor");

				Element cacheeditor = newDocument.createElement("cache");
				cacheeditor.setTextContent("0");
				editor.appendChild(cacheeditor);

				Element disablededitor = newDocument.createElement("disabled");
				disablededitor.setTextContent("0");
				editor.appendChild(disablededitor);

				Element displayTypeeditor = newDocument.createElement("displayType");
				displayTypeeditor.setTextContent("select");
				editor.appendChild(displayTypeeditor);

				Element multiSelecteditor = newDocument.createElement("multiSelect");
				multiSelecteditor.setTextContent("0");
				editor.appendChild(multiSelecteditor);

				Element nameeditor = newDocument.createElement("name");
				nameeditor.setTextContent("editor");
				editor.appendChild(nameeditor);

				Element numbereditor = newDocument.createElement("number");
				numbereditor.setTextContent("14");
				editor.appendChild(numbereditor);

				Element prettyNameeditor = newDocument.createElement("prettyName");
				prettyNameeditor.setTextContent("Default Editor");
				editor.appendChild(prettyNameeditor);

				Element relationalStorageeditor = newDocument.createElement("relationalStorage");
				relationalStorageeditor.setTextContent("0");
				editor.appendChild(relationalStorageeditor);

				Element separatorditor = newDocument.createElement("separator");
				separatorditor.setTextContent(" ");
				editor.appendChild(separatorditor);

				Element separatorseditor = newDocument.createElement("separators");
				separatorseditor.setTextContent(" ,|");
				editor.appendChild(separatorseditor);

				Element sizeEditor = newDocument.createElement("size");
				sizeEditor.setTextContent("1");
				editor.appendChild(sizeEditor);

				Element unmodifiableEditor = newDocument.createElement("unmodifiable");
				unmodifiableEditor.setTextContent("0");
				editor.appendChild(unmodifiableEditor);

				Element valuesEditor = newDocument.createElement("values");
				valuesEditor.setTextContent("---|Text|Wysiwyg");
				editor.appendChild(valuesEditor);

				Element classTypeEditor = newDocument.createElement("classType");
				classTypeEditor.setTextContent("com.xpn.xwiki.objects.classes.StaticListClass");
				editor.appendChild(classTypeEditor);

				classObject3.appendChild(editor);

				Element email = newDocument.createElement("email");

				Element disabledemail = newDocument.createElement("disabled");
				disabledemail.setTextContent("0");
				email.appendChild(disabledemail);

				Element nameemail = newDocument.createElement("name");
				nameemail.setTextContent("email");
				email.appendChild(nameemail);

				Element numberemail = newDocument.createElement("number");
				numberemail.setTextContent("3");
				email.appendChild(numberemail);

				Element prettyNameemail = newDocument.createElement("prettyName");
				prettyNameemail.setTextContent("e-mail");
				email.appendChild(prettyNameemail);

				Element sizeemail = newDocument.createElement("size");
				sizeemail.setTextContent("30");
				email.appendChild(sizeemail);

				Element unmodifiableemail = newDocument.createElement("unmodifiable");
				unmodifiableemail.setTextContent("0");
				email.appendChild(unmodifiableemail);

				Element validationRegExp = newDocument.createElement("validationRegExp");
				///////////////////////validationRegExp.setTextContent("/^(([^@\s]+)@((?:[-a-zA-Z0-9]+\.)+[a-zA-Z]{2,}))?$/");
				email.appendChild(validationRegExp);

				Element classTypeemail = newDocument.createElement("classType");
				classTypeemail.setTextContent("com.xpn.xwiki.objects.classes.EmailClass");
				email.appendChild(classTypeemail);

				classObject3.appendChild(email);

				Element first_name = newDocument.createElement("first_name");

				Element disabledfirst_name = newDocument.createElement("disabled");
				disabledfirst_name.setTextContent("0");
				first_name.appendChild(disabledfirst_name);

				Element namefirst_name = newDocument.createElement("name");
				namefirst_name.setTextContent("first_name");
				first_name.appendChild(namefirst_name);

				Element numberfirst_name = newDocument.createElement("number");
				numberfirst_name.setTextContent("1");
				first_name.appendChild(numberfirst_name);

				Element prettyNamefirst_name= newDocument.createElement("prettyName");
				prettyNamefirst_name.setTextContent("First Name");
				first_name.appendChild(prettyNamefirst_name);

				Element sizefirst_name = newDocument.createElement("size");
				sizefirst_name.setTextContent("30");
				first_name.appendChild(sizefirst_name);

				Element unmodifiablefirst_name = newDocument.createElement("unmodifiable");
				unmodifiablefirst_name.setTextContent("0");
				first_name.appendChild(unmodifiablefirst_name);

				Element classTypefirst_name = newDocument.createElement("classType");
				classTypefirst_name.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				first_name.appendChild(classTypefirst_name);

				classObject3.appendChild(first_name);

				Element imaccount = newDocument.createElement("imaccount");

				Element disabledimaccount = newDocument.createElement("disabled");
				disabledimaccount.setTextContent("0");
				imaccount.appendChild(disabledimaccount);

				Element nameimaccount = newDocument.createElement("name");
				nameimaccount.setTextContent("imaccount");
				imaccount.appendChild(nameimaccount);

				Element numberimaccount = newDocument.createElement("number");
				numberimaccount.setTextContent("13");
				imaccount.appendChild(numberimaccount);

				Element prettyNameimaccount = newDocument.createElement("prettyName");
				prettyNameimaccount.setTextContent("imaccount");
				imaccount.appendChild(prettyNameimaccount);

				Element sizeimaccount = newDocument.createElement("size");
				sizeimaccount.setTextContent("30");
				imaccount.appendChild(sizeimaccount);

				Element unmodifiableimaccount = newDocument.createElement("unmodifiable");
				unmodifiableimaccount.setTextContent("0");
				imaccount.appendChild(unmodifiableimaccount);

				Element classTypeimaccount = newDocument.createElement("classType");
				classTypeimaccount.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				imaccount.appendChild(classTypeimaccount);

				classObject3.appendChild(imaccount);

				Element imtype = newDocument.createElement("imtype");

				Element cacheimtype = newDocument.createElement("cache");
				cacheimtype.setTextContent("0");
				imtype.appendChild(cacheimtype);

				Element disabledimtype = newDocument.createElement("disabled");
				disabledimtype.setTextContent("0");
				imtype.appendChild(disabledimtype);

				Element displayTypeimtype = newDocument.createElement("displayType");
				displayTypeimtype.setTextContent("select");
				imtype.appendChild(displayTypeimtype);

				Element multiSelectimtype = newDocument.createElement("multiSelect");
				multiSelectimtype.setTextContent("0");
				imtype.appendChild(multiSelectimtype);

				Element nameimtype = newDocument.createElement("name");
				nameimtype.setTextContent("imtype");
				imtype.appendChild(nameimtype);

				Element numberimtype = newDocument.createElement("number");
				numberimtype.setTextContent("12");
				imtype.appendChild(numberimtype);

				Element prettyNameimtype = newDocument.createElement("prettyName");
				prettyNameimtype.setTextContent("IM Type");
				imtype.appendChild(prettyNameimtype);

				Element relationalimtype = newDocument.createElement("relationalStorage");
				relationalimtype.setTextContent("0");
				imtype.appendChild(relationalimtype);

				Element separatorimtype = newDocument.createElement("separator");
				separatorimtype.setTextContent(" ");
				imtype.appendChild(separatorimtype);

				Element separatorsimtype = newDocument.createElement("separators");
				separatorsimtype.setTextContent(" ,|");
				imtype.appendChild(separatorsimtype);	

				Element sizeimtype = newDocument.createElement("size");
				sizeimtype.setTextContent("1");
				imtype.appendChild(sizeimtype);

				Element unmodifiableimtype = newDocument.createElement("unmodifiable");
				unmodifiableimtype.setTextContent("0");
				imtype.appendChild(unmodifiableimtype);

				Element valuesimtype = newDocument.createElement("values");
				valuesimtype.setTextContent("---|AIM|Yahoo|Jabber|MSN|Skype|ICQ");
				imtype.appendChild(valuesimtype);

				Element classTypeimtype = newDocument.createElement("classType");
				classTypeimtype.setTextContent("com.xpn.xwiki.objects.classes.StaticListClass");
				imtype.appendChild(classTypeimtype);

				classObject3.appendChild(imtype);

				Element last_name = newDocument.createElement("last_name");

				Element disabledlast_name = newDocument.createElement("disabled");
				disabledlast_name.setTextContent("0");
				last_name.appendChild(disabledlast_name);

				Element namelast_name = newDocument.createElement("name");
				namelast_name.setTextContent("last_name");
				last_name.appendChild(namelast_name);

				Element numberlast_name = newDocument.createElement("number");
				numberlast_name.setTextContent("2");
				last_name.appendChild(numberlast_name);

				Element prettyNamelast_name = newDocument.createElement("prettyName");
				prettyNamelast_name.setTextContent("Last Name");
				last_name.appendChild(prettyNamelast_name);

				Element sizelast_name = newDocument.createElement("size");
				sizelast_name.setTextContent("30");
				last_name.appendChild(sizelast_name);

				Element unmodifiablelast_name = newDocument.createElement("unmodifiable");
				unmodifiablelast_name.setTextContent("0");
				last_name.appendChild(unmodifiablelast_name);

				Element classTypelast_name = newDocument.createElement("classType");
				classTypelast_name.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				last_name.appendChild(classTypelast_name);

				classObject3.appendChild(last_name);

				Element password = newDocument.createElement("password");

				Element disabledpassword = newDocument.createElement("disabled");
				disabledpassword.setTextContent("0");
				password.appendChild(disabledpassword);

				Element namepassword = newDocument.createElement("name");
				namepassword.setTextContent("password");
				password.appendChild(namepassword);

				Element numberpassword = newDocument.createElement("number");
				numberpassword.setTextContent("4");
				password.appendChild(numberpassword);

				Element prettyNamepassword = newDocument.createElement("prettyName");
				prettyNamepassword.setTextContent("Password");
				password.appendChild(prettyNamepassword);

				Element sizepassword = newDocument.createElement("size");
				sizepassword.setTextContent("10");
				password.appendChild(sizepassword);

				Element unmodifiablepassword = newDocument.createElement("unmodifiable");
				unmodifiablepassword.setTextContent("0");
				password.appendChild(unmodifiablepassword);

				Element classTypepassword = newDocument.createElement("classType");
				classTypepassword.setTextContent("com.xpn.xwiki.objects.classes.PasswordClass");
				password.appendChild(classTypepassword);

				classObject3.appendChild(password);

				Element phone = newDocument.createElement("phone");

				Element disabledphone = newDocument.createElement("disabled");
				disabledphone.setTextContent("0");
				phone.appendChild(disabledphone);

				Element namephone = newDocument.createElement("name");
				namephone.setTextContent("phone");
				phone.appendChild(namephone);

				Element numberphone = newDocument.createElement("number");
				numberphone.setTextContent("21");
				phone.appendChild(numberphone);

				Element prettyNamephone = newDocument.createElement("prettyName");
				prettyNamephone.setTextContent("Phone");
				phone.appendChild(prettyNamephone);

				Element sizephone = newDocument.createElement("size");
				sizephone.setTextContent("30");
				phone.appendChild(sizephone);

				Element unmodifiablephone = newDocument.createElement("unmodifiable");
				unmodifiablephone.setTextContent("0");
				phone.appendChild(unmodifiablephone);

				Element classTypephone = newDocument.createElement("classType");
				classTypephone.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				phone.appendChild(classTypephone);

				classObject3.appendChild(phone);

				Element skin = newDocument.createElement("skin");

				Element disabledskin = newDocument.createElement("disabled");
				disabledskin.setTextContent("0");
				skin.appendChild(disabledskin);

				Element nameskin = newDocument.createElement("name");
				nameskin.setTextContent("skin");
				skin.appendChild(nameskin);

				Element numberskin = newDocument.createElement("number");
				numberskin.setTextContent("19");
				skin.appendChild(numberskin);

				Element prettyNameskin = newDocument.createElement("prettyName");
				prettyNameskin.setTextContent("skin");
				skin.appendChild(prettyNameskin);

				Element sizeskin = newDocument.createElement("size");
				sizeskin.setTextContent("30");
				skin.appendChild(sizeskin);

				Element unmodifiableskin = newDocument.createElement("unmodifiable");
				unmodifiableskin.setTextContent("0");
				skin.appendChild(unmodifiableskin);

				Element classTypeskin = newDocument.createElement("classType");
				classTypeskin.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				skin.appendChild(classTypeskin);

				classObject3.appendChild(skin);

				Element timezone = newDocument.createElement("timezone");

				Element customDisplay = newDocument.createElement("customDisplay");
				customDisplay.setTextContent("{{velocity}}"
						+"\n#if ($xcontext.action == 'inline' || $xcontext.action == 'edit')"
						+"\n{{html}}"
						+"\n#if($xwiki.jodatime)"
						+"\n&lt;select id='$prefix$name' name='$prefix$name'>"
						+"\n&lt;option value=\"\" #if($value == $tz)selected=\"selected\"#end>$services.localization.render('XWiki.XWikiPreferences_timezone_default')&lt;/option>"
						+"\n#foreach($tz in $xwiki.jodatime.getServerTimezone().getAvailableIDs())"
						+"\n&lt;option value=\"$tz\" #if($value == $tz)selected=\"selected\"#end>$tz&lt;/option>"
						+"\n#end"
						+"\n&lt;/select>"
						+"\n#else"
						+"\n&lt;input id='$prefix$name' name='$prefix$name' type=\"text\" value=\"$!value\"/>"
						+"\n#end"
						+"\n{{/html}}"
						+"\n#else"
						+"\n$!value"
						+"\n#end"
						+"\n{{/velocity}}\n" );		
				timezone.appendChild(customDisplay);

				Element disabledtimezone = newDocument.createElement("disabled");
				disabledtimezone.setTextContent("0");
				timezone.appendChild(disabledtimezone);

				Element nametimezone = newDocument.createElement("name");
				nametimezone.setTextContent("timezone");
				timezone.appendChild(nametimezone);

				Element numbertimezone = newDocument.createElement("number");
				numbertimezone.setTextContent("18");
				timezone.appendChild(numbertimezone);

				Element prettyNametimezone = newDocument.createElement("prettyName");
				prettyNametimezone.setTextContent("Timezone");
				timezone.appendChild(prettyNametimezone);

				Element sizetimezone = newDocument.createElement("size");
				sizetimezone.setTextContent("30");
				timezone.appendChild(sizetimezone);

				Element unmodifiabletimezone = newDocument.createElement("unmodifiable");
				unmodifiabletimezone.setTextContent("0");
				timezone.appendChild(unmodifiabletimezone);

				Element classTypetimezone = newDocument.createElement("classType");
				classTypetimezone.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				timezone.appendChild(classTypetimezone);

				classObject3.appendChild(timezone);

				Element usertype = newDocument.createElement("usertype");

				Element cacheusertype = newDocument.createElement("cache");
				cacheusertype.setTextContent("0");
				usertype.appendChild(cacheusertype);

				Element disabledusertype = newDocument.createElement("disabled");
				disabledusertype.setTextContent("0");
				usertype.appendChild(disabledusertype);

				Element usertypeype = newDocument.createElement("displayType");
				usertypeype.setTextContent("select");
				usertype.appendChild(usertypeype);

				Element multiSelectusertype = newDocument.createElement("multiSelect");
				multiSelectusertype.setTextContent("0");
				usertype.appendChild(multiSelectusertype);

				Element nameusertype = newDocument.createElement("name");
				nameusertype.setTextContent("usertype");
				usertype.appendChild(nameusertype);

				Element numberusertype = newDocument.createElement("number");
				numberusertype.setTextContent("15");
				usertype.appendChild(numberusertype);

				Element prettyNameusertype = newDocument.createElement("prettyName");
				prettyNameusertype.setTextContent("User type");
				usertype.appendChild(prettyNameusertype);

				Element relationalStorageusertype = newDocument.createElement("relationalStorage");
				relationalStorageusertype.setTextContent("0");
				usertype.appendChild(relationalStorageusertype);

				Element separatorusertype = newDocument.createElement("separator");
				separatorusertype.setTextContent(" ");
				usertype.appendChild(separatorusertype);

				Element separatorsusertype = newDocument.createElement("separators");
				separatorsusertype.setTextContent(" ,|");
				usertype.appendChild(separatorsusertype);

				Element sizeusertype = newDocument.createElement("size");
				sizeusertype.setTextContent("1");
				usertype.appendChild(sizeusertype);

				Element unmodifiableusertype = newDocument.createElement("unmodifiable");
				unmodifiableusertype.setTextContent("0");
				usertype.appendChild(unmodifiableusertype);

				Element valuesusertype = newDocument.createElement("values");
				valuesusertype.setTextContent("Simple|Advanced");
				usertype.appendChild(valuesusertype);

				Element classTypevaluesusertype = newDocument.createElement("classType");
				classTypevaluesusertype.setTextContent("com.xpn.xwiki.objects.classes.StaticListClass");
				usertype.appendChild(classTypevaluesusertype);

				classObject3.appendChild(usertype);

				Element validkey = newDocument.createElement("validkey");

				Element disabledvalidkey = newDocument.createElement("disabled");
				disabledvalidkey.setTextContent("0");
				validkey.appendChild(disabledvalidkey);

				Element namevalidkey = newDocument.createElement("name");
				namevalidkey.setTextContent("validkey");
				validkey.appendChild(namevalidkey);

				Element numbervalidkey = newDocument.createElement("number");
				numbervalidkey.setTextContent("5");
				validkey.appendChild(numbervalidkey);

				Element prettyNamevalidkey = newDocument.createElement("prettyName");
				prettyNamevalidkey.setTextContent("Validation Key");
				validkey.appendChild(prettyNamevalidkey);

				Element sizevalidkey = newDocument.createElement("size");
				sizevalidkey.setTextContent("10");
				validkey.appendChild(sizevalidkey);

				Element unmodifiablevalidkey = newDocument.createElement("unmodifiable");
				unmodifiablevalidkey.setTextContent("0");
				validkey.appendChild(unmodifiablevalidkey);

				Element classTypevalidkey = newDocument.createElement("classType");
				classTypevalidkey.setTextContent("com.xpn.xwiki.objects.classes.PasswordClass");
				validkey.appendChild(classTypevalidkey);

				classObject3.appendChild(validkey);

				object3.appendChild(classObject3);



				Element property5 = newDocument.createElement("property");

				Element activeProperty = newDocument.createElement("active");
				activeProperty.setTextContent("1");
				property5.appendChild(activeProperty);

				object3.appendChild(property5);

				Element property6 = newDocument.createElement("property");


				Element emailProperty = newDocument.createElement("email");
				emailProperty.setTextContent(emailEmployee);
				property6.appendChild(emailProperty);

				object3.appendChild(property6);

				Element property7 = newDocument.createElement("property");

				Element first_nameProperty = newDocument.createElement("first_name");
				first_nameProperty.setTextContent(employeeName);
				property7.appendChild(first_nameProperty);

				object3.appendChild(property7);


				Element property8 = newDocument.createElement("property");

				Element last_nameProperty = newDocument.createElement("last_name");
				last_nameProperty.setTextContent(employeeName);
				property8.appendChild(last_nameProperty);

				object3.appendChild(property8);


				/**
				 * PASSWORD ENCRYPTION FOR XWIKI
				 * SO GOOD
				 */

				MessageDigest md;
				String message = employeeName;
				String passwordEncrypted="";
				try {
					md= MessageDigest.getInstance("SHA-512");

					md.update(message.getBytes());
					byte[] mb = md.digest();
					String out = "";
					for (int j = 0; j < mb.length; j++) {
						byte temp = mb[j];
						String s = Integer.toHexString(new Byte(temp));
						while (s.length() < 2) {
							s = "0" + s;
						}
						s = s.substring(s.length() - 2);
						out += s;
						passwordEncrypted=out;
					}
					//System.out.println(out.length());
					//System.out.println("CRYPTO: " + out);

				} catch (NoSuchAlgorithmException e) {
					System.out.println("ERROR: " + e.getMessage());
				}



				Element property9 = newDocument.createElement("property");

				Element passwordProperty = newDocument.createElement("password");
				passwordProperty.setTextContent("hash:SHA-512:" + passwordEncrypted);
				property9.appendChild(passwordProperty);

				object3.appendChild(property9);


				// chiusura del file
				xwikidoc.appendChild(object3);


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
				StreamResult result = new StreamResult(new File(directoryCompany.getCurrentDirectory().toString() + "\\xml\\xwiki\\"+ employeeName + ".xml"));
				transformer.transform(source, result);


			}
		}


		NodeList connector = workingEnvironment.getElementsByTagName("CONNECTOR");

		Element from;
		Element to;

		int arrayConnectorLength = connector.getLength();

		String[] arrayFrom = new String[arrayConnectorLength];
		String[] arrayTo = new String[arrayConnectorLength];


		for(int q=0; q<arrayConnectorLength; q++){

			Element elementConnector = (Element) connector.item(q);

			from = (Element) elementConnector.getChildNodes().item(1);
			to = (Element) elementConnector.getChildNodes().item(3);

			arrayFrom[q]=from.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "");
			arrayTo[q]=to.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "");

			System.out.println("User \"" + arrayFrom[q] + "\" has beeen created linked to the \"" + arrayTo[q] + "\" group.");

		}

		GroupCreator.groupCreator(connector, directoryCompany, arrayFrom, arrayTo);
		System.out.println("\n");
		//		}

		//prettyPrint(newDocument, directory, "user");
		//}else{
		//	System.out.println("Procedura gia effettuata");
		//}


	}




	/**
	 * 
	 * @param directoryBPMN
	 * @param directoryCompany
	 * @return
	 */
	public static String chechkUserGroup(JFileChooser directoryBPMN, JFileChooser directoryCompany){



		System.out.println("The lanes and the Roles are identical");
		return "same";
	}

}
