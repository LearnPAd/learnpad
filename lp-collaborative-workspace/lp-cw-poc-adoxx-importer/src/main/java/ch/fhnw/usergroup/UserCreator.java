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
				defaultLanguage.setTextContent("en");
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
				className.setTextContent("Dashboard.UserDashboardPreferencesClass");
				object.appendChild(className);

				Element guid = newDocument.createElement("guid");
				//guid.setTextContent("06c98e97-2c58-4929-853e-fbbfde460e53");
				object.appendChild(guid);

				Element classObject = newDocument.createElement("class");

				///////////////////object.appendChild(classObject);

				Element nameClass = newDocument.createElement("name");
				nameClass.setTextContent("Dashboard.UserDashboardPreferencesClass");
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


				Element displayOnMainPage = newDocument.createElement("displayOnMainPage");
				////////////////

				Element customDisplay = newDocument.createElement("customDisplay");
				displayOnMainPage.appendChild(customDisplay);

				Element defaultValue = newDocument.createElement("defaultValue");
				defaultValue.setTextContent("0");
				displayOnMainPage.appendChild(defaultValue);

				Element disabled = newDocument.createElement("disabled");
				disabled.setTextContent("0");
				displayOnMainPage.appendChild(disabled);

				Element displayFormType = newDocument.createElement("displayFormType");
				displayFormType.setTextContent("select");
				displayOnMainPage.appendChild(displayFormType);

				Element displayType = newDocument.createElement("displayType");
				displayOnMainPage.appendChild(displayType);

				Element nameWatch = newDocument.createElement("name");
				nameWatch.setTextContent("displayOnMainPage");
				displayOnMainPage.appendChild(nameWatch);

				Element numberWatch = newDocument.createElement("number");
				numberWatch.setTextContent("1");
				displayOnMainPage.appendChild(numberWatch);

				Element prettyName = newDocument.createElement("prettyName");
				prettyName.setTextContent("displayOnMainPage");
				displayOnMainPage.appendChild(prettyName);

				Element unmodifiable = newDocument.createElement("unmodifiable");
				unmodifiable.setTextContent("0");
				displayOnMainPage.appendChild(unmodifiable);

				Element validationMessage = newDocument.createElement("validationMessage");
				displayOnMainPage.appendChild(validationMessage);

				Element validationRegExp = newDocument.createElement("validationRegExp");
				displayOnMainPage.appendChild(validationRegExp);				

				Element classType = newDocument.createElement("classType");
				classType.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
				displayOnMainPage.appendChild(classType);

				classObject.appendChild(displayOnMainPage);

				object.appendChild(classObject);

				Element property = newDocument.createElement("property");

				Element displayProperty = newDocument.createElement("displayOnMainPage");
				displayProperty.setTextContent("0");
				property.appendChild(displayProperty);

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
				className2.setTextContent("XWiki.WatchListClass");
				object2.appendChild(className2);

				Element guid2 = newDocument.createElement("guid");
				//guid2.setTextContent("153168be-5d86-4f13-9e8b-f06ea1118ec7");
				object2.appendChild(guid2);

				Element classObject2 = newDocument.createElement("class");

				///////////////////object.appendChild(classObject);

				Element nameClass2 = newDocument.createElement("name");
				nameClass2.setTextContent("XWiki.WatchListClass");
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


				Element automaticwatch = newDocument.createElement("automaticwatch");
				////////////////

				Element cache = newDocument.createElement("cache");
				cache.setTextContent("0");
				automaticwatch.appendChild(cache);

				Element disabled2 = newDocument.createElement("disabled");
				disabled2.setTextContent("0");
				automaticwatch.appendChild(disabled2);

				Element displayType2 = newDocument.createElement("displayType");
				displayType2.setTextContent("select");
				automaticwatch.appendChild(displayType2);

				Element multiSelect = newDocument.createElement("multiSelect");
				multiSelect.setTextContent("0");
				automaticwatch.appendChild(multiSelect);


				Element nameAutomaticwatch = newDocument.createElement("name");
				nameAutomaticwatch.setTextContent("automaticwatch");
				automaticwatch.appendChild(nameAutomaticwatch);

				Element numberAutomaticwatch = newDocument.createElement("number");
				numberAutomaticwatch.setTextContent("6");
				automaticwatch.appendChild(numberAutomaticwatch);

				Element prettyName2 = newDocument.createElement("prettyName");
				prettyName2.setTextContent("Automatic watching");
				automaticwatch.appendChild(prettyName2);

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

				Element unmodifiable2 = newDocument.createElement("unmodifiable");
				unmodifiable2.setTextContent("0");
				automaticwatch.appendChild(unmodifiable2);

				Element values = newDocument.createElement("values");
				values.setTextContent("default|NONE|ALL|MAJOR|NEW");
				automaticwatch.appendChild(values);

				Element classType2 = newDocument.createElement("classType");
				classType2.setTextContent("com.xpn.xwiki.objects.classes.StaticListClass");
				automaticwatch.appendChild(classType2);

				classObject2.appendChild(automaticwatch);

				Element documents = newDocument.createElement("documents");

				Element disabledDocuments2 = newDocument.createElement("disabled");
				disabledDocuments2.setTextContent("0");
				documents.appendChild(disabledDocuments2);

				Element nameDocuments = newDocument.createElement("name");
				nameDocuments.setTextContent("documents");
				documents.appendChild(nameDocuments);

				Element numberDocuments = newDocument.createElement("number");
				numberDocuments.setTextContent("4");
				documents.appendChild(numberDocuments);

				Element prettyNameDocuments = newDocument.createElement("prettyName");
				prettyNameDocuments.setTextContent("Document list");
				documents.appendChild(prettyNameDocuments);


				Element rowsDocuments = newDocument.createElement("rows");
				rowsDocuments.setTextContent("5");
				documents.appendChild(rowsDocuments);

				Element sizeDocuments = newDocument.createElement("size");
				sizeDocuments.setTextContent("80");
				documents.appendChild(sizeDocuments);

				Element unmodifiable3 = newDocument.createElement("unmodifiable");
				unmodifiable3.setTextContent("0");
				documents.appendChild(unmodifiable3);

				Element classType3 = newDocument.createElement("classType");
				classType3.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				documents.appendChild(classType3);

				classObject2.appendChild(documents);


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

				classObject2.appendChild(interval);

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

				Element separatorSpaces = newDocument.createElement("size");
				separatorSpaces.setTextContent("80");
				spaces.appendChild(separatorSpaces);

				Element unmodifiableSpaces = newDocument.createElement("unmodifiable");
				unmodifiableSpaces.setTextContent("0");
				spaces.appendChild(unmodifiableSpaces);

				Element classTypeSpaces = newDocument.createElement("classType");
				classTypeSpaces.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				spaces.appendChild(classTypeSpaces);

				classObject2.appendChild(spaces);

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

				Element separatorUsers = newDocument.createElement("size");
				separatorUsers.setTextContent("80");
				users.appendChild(separatorUsers);

				Element unmodifiableUsers = newDocument.createElement("unmodifiable");
				unmodifiableUsers.setTextContent("0");
				users.appendChild(unmodifiableUsers);

				Element classTypeUsers = newDocument.createElement("classType");
				classTypeUsers.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				users.appendChild(classTypeUsers);

				classObject2.appendChild(users);

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

				Element separatorWikis = newDocument.createElement("size");
				separatorWikis.setTextContent("80");
				wikis.appendChild(separatorWikis);

				Element unmodifiableWikis = newDocument.createElement("unmodifiable");
				unmodifiableWikis.setTextContent("0");
				wikis.appendChild(unmodifiableWikis);

				Element classTypeWikis = newDocument.createElement("classType");
				classTypeWikis.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				wikis.appendChild(classTypeWikis);

				classObject2.appendChild(wikis);


				object2.appendChild(classObject2);

				Element property2 = newDocument.createElement("property");

				Element allowProperty = newDocument.createElement("documents");
				allowProperty.setTextContent("xwiki:XWiki." + employeeName);
				property2.appendChild(allowProperty);

				object2.appendChild(property2);

				xwikidoc.appendChild(object2);


				Element object3 = newDocument.createElement("object");

				Element nameObject3 = newDocument.createElement("name");
				nameObject3.setTextContent("XWiki." + employeeName);
				object3.appendChild(nameObject3);

				Element number3 = newDocument.createElement("number");
				number3.setTextContent("0");
				object3.appendChild(number3);

				Element className3 = newDocument.createElement("className");
				className3.setTextContent("XWiki.XWikiRights");
				object3.appendChild(className3);

				Element guid3 = newDocument.createElement("guid");
				//guid3.setTextContent("4da634b9-3be0-4d00-bc66-e491785b1ec4");
				object3.appendChild(guid3);

				Element classObject3 = newDocument.createElement("class");

				///////////////////object.appendChild(classObject);

				Element nameClass3 = newDocument.createElement("name");
				nameClass3.setTextContent("XWiki.XWikiRights");
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


				Element allow = newDocument.createElement("allow");
				////////////////

				Element defaultValueAllow = newDocument.createElement("defaultValue");
				defaultValueAllow.setTextContent("1");
				allow.appendChild(defaultValueAllow);

				Element disabledAllow = newDocument.createElement("disabled");
				disabledAllow.setTextContent("0");
				allow.appendChild(disabledAllow);

				Element displayFormTypeAllow = newDocument.createElement("displayFormType");
				displayFormTypeAllow.setTextContent("select");
				allow.appendChild(displayFormTypeAllow);

				Element displayTypeAllow = newDocument.createElement("displayType");
				displayTypeAllow.setTextContent("allow");
				allow.appendChild(displayTypeAllow);

				Element nameAllow = newDocument.createElement("name");
				nameAllow.setTextContent("allow");
				allow.appendChild(nameAllow);

				Element numberAllow = newDocument.createElement("number");
				numberAllow.setTextContent("4");
				allow.appendChild(numberAllow);

				Element prettyNameAllow = newDocument.createElement("prettyName");
				prettyNameAllow.setTextContent("Allow/Deny");
				allow.appendChild(prettyNameAllow);

				Element unmodifiableAllow = newDocument.createElement("unmodifiable");
				unmodifiableAllow.setTextContent("0");
				allow.appendChild(unmodifiableAllow);

				Element classTypeAllow = newDocument.createElement("classType");
				classTypeAllow.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
				allow.appendChild(classTypeAllow);

				classObject3.appendChild(allow);

				Element groups = newDocument.createElement("groups");
				/////////////xwikidoc.appendChild(documents);

				Element cacheGroups = newDocument.createElement("cache");
				cacheGroups.setTextContent("0");
				groups.appendChild(cacheGroups);

				Element disabledGroups = newDocument.createElement("disabled");
				disabledGroups.setTextContent("0");
				groups.appendChild(disabledGroups);

				Element displayTypeGroups = newDocument.createElement("displayType");
				displayTypeGroups.setTextContent("input");
				groups.appendChild(displayTypeGroups);

				Element multiSelectGroups = newDocument.createElement("multiSelect");
				multiSelectGroups.setTextContent("1");
				groups.appendChild(multiSelectGroups);

				Element nameGroups = newDocument.createElement("name");
				nameGroups.setTextContent("groups");
				groups.appendChild(nameGroups);

				Element numberGroups = newDocument.createElement("number");
				numberGroups.setTextContent("1");
				groups.appendChild(numberGroups);

				Element pickerGroups = newDocument.createElement("picker");
				pickerGroups.setTextContent("1");
				groups.appendChild(pickerGroups);

				Element prettyNameGroups = newDocument.createElement("prettyName");
				prettyNameGroups.setTextContent("Groups");
				groups.appendChild(prettyNameGroups);

				Element relationalStorageGroups = newDocument.createElement("relationalStorage");
				relationalStorageGroups.setTextContent("0");
				groups.appendChild(relationalStorageGroups);

				Element separatorGroups = newDocument.createElement("separator");
				separatorGroups.setTextContent(" ");
				groups.appendChild(separatorGroups);

				Element sizeGroups = newDocument.createElement("size");
				sizeGroups.setTextContent("5");
				groups.appendChild(sizeGroups);

				Element unmodifiableGroups = newDocument.createElement("unmodifiable");
				unmodifiableGroups.setTextContent("0");
				groups.appendChild(unmodifiableGroups);

				Element classTypeGroups = newDocument.createElement("classType");
				classTypeGroups.setTextContent("com.xpn.xwiki.objects.classes.GroupsClass");
				groups.appendChild(classTypeGroups);

				classObject3.appendChild(groups);


				Element levels = newDocument.createElement("levels");
				/////////////xwikidoc.appendChild(documents);

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

				classObject3.appendChild(levels);


				Element users2 = newDocument.createElement("users");
				/////////////xwikidoc.appendChild(documents);

				Element cacheUsers2 = newDocument.createElement("cache");
				cacheUsers2.setTextContent("0");
				users2.appendChild(cacheUsers2);

				Element disabledUsers2 = newDocument.createElement("disabled");
				disabledUsers2.setTextContent("0");
				users2.appendChild(disabledUsers2);

				Element displayTypeUsers2 = newDocument.createElement("displayType");
				displayTypeUsers2.setTextContent("input");
				users2.appendChild(displayTypeUsers2);

				Element multiSelectUsers2 = newDocument.createElement("multiSelect");
				multiSelectUsers2.setTextContent("1");
				users2.appendChild(multiSelectUsers2);

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

				Element relationalStorageUsers2 = newDocument.createElement("relationalStorage");
				relationalStorageUsers2.setTextContent("0");
				users2.appendChild(relationalStorageUsers2);

				Element separatorUsers2 = newDocument.createElement("separator");
				separatorUsers2.setTextContent(" ");
				users2.appendChild(separatorUsers2);

				Element sizeUsers2 = newDocument.createElement("size");
				sizeUsers2.setTextContent("5");
				users2.appendChild(sizeUsers2);

				Element unmodifiableUsers2 = newDocument.createElement("unmodifiable");
				unmodifiableUsers2.setTextContent("0");
				users2.appendChild(unmodifiableUsers2);

				Element classTypeUsers2 = newDocument.createElement("classType");
				classTypeUsers2.setTextContent("com.xpn.xwiki.objects.classes.UsersClass");
				users2.appendChild(classTypeUsers2);

				classObject3.appendChild(users2);

				object3.appendChild(classObject3);


				Element property3 = newDocument.createElement("property");

				Element allowProperty2 = newDocument.createElement("allow");
				allowProperty2.setTextContent("1");
				property3.appendChild(allowProperty2);

				object3.appendChild(property3);

				Element property4 = newDocument.createElement("property");

				Element levelsProperty = newDocument.createElement("levels");
				levelsProperty.setTextContent("edit");
				property4.appendChild(levelsProperty);

				object3.appendChild(property4);

				Element property5 = newDocument.createElement("property");

				Element usersProperty = newDocument.createElement("users");
				usersProperty.setTextContent("XWiki." + employeeName);
				property5.appendChild(usersProperty);

				object3.appendChild(property5);

				xwikidoc.appendChild(object3);


				Element object4 = newDocument.createElement("object");

				Element nameObject4 = newDocument.createElement("name");
				nameObject4.setTextContent("XWiki." + employeeName);
				object4.appendChild(nameObject4);

				Element number4 = newDocument.createElement("number");
				number4.setTextContent("0");
				object4.appendChild(number4);

				Element className4 = newDocument.createElement("className");
				className4.setTextContent("XWiki.XWikiUsers");
				object4.appendChild(className4);

				Element guid4 = newDocument.createElement("guid");
				//guid4.setTextContent("a3aca5eb-c28a-49c5-81b9-5146ea247a5b");
				object4.appendChild(guid4);

				Element classObject4 = newDocument.createElement("class");

				Element nameClass4 = newDocument.createElement("name");
				nameClass4.setTextContent("XWiki.XWikiUsers");
				classObject4.appendChild(nameClass4);				
				
				Element customClass = newDocument.createElement("customClass");
				classObject4.appendChild(customClass);				
				
				Element customMappingClass4 = newDocument.createElement("customMapping");
				classObject4.appendChild(customMappingClass4);				
				
				Element defaultViewSheetClass4 = newDocument.createElement("defaultViewSheet");
				classObject4.appendChild(defaultViewSheetClass4);				
				
				Element defaultEditSheetClass4 = newDocument.createElement("defaultEditSheet");
				classObject4.appendChild(defaultEditSheetClass4);				
				
				Element defaultWebClass4 = newDocument.createElement("defaultWeb");
				classObject4.appendChild(defaultWebClass4);				
				
				Element nameFieldClass4 = newDocument.createElement("nameField");
				classObject4.appendChild(nameFieldClass4);				
				
				Element validationScriptClass4 = newDocument.createElement("validationScript");
				classObject4.appendChild(validationScriptClass4);				
				
				
				Element accessibility = newDocument.createElement("accessibility");

				Element disabledAcc = newDocument.createElement("disabled");
				disabledAcc.setTextContent("0");
				accessibility.appendChild(disabledAcc);
				
				Element displayFormTypeAcc = newDocument.createElement("displayFormType");
				displayFormTypeAcc.setTextContent("select");
				accessibility.appendChild(displayFormTypeAcc);

				Element displayTypeAcc = newDocument.createElement("displayType");
				displayTypeAcc.setTextContent("yesno");
				accessibility.appendChild(displayTypeAcc);
				
				Element nameAcc = newDocument.createElement("name");
				nameAcc.setTextContent("accessibility");
				accessibility.appendChild(nameAcc);

				Element numberAcc = newDocument.createElement("number");
				numberAcc.setTextContent("16");
				accessibility.appendChild(numberAcc);

				Element prettyNameAcc = newDocument.createElement("prettyName");
				prettyNameAcc.setTextContent("Enable extra accessibility features");
				accessibility.appendChild(prettyNameAcc);

				Element unmodifiableAcc = newDocument.createElement("unmodifiable");
				unmodifiableAcc.setTextContent("0");
				accessibility.appendChild(unmodifiableAcc);

				Element classTypeAcc = newDocument.createElement("classType");
				classTypeAcc.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
				accessibility.appendChild(classTypeAcc);
				
				classObject4.appendChild(accessibility);
				
				
				Element active = newDocument.createElement("active");

				Element disabledActive = newDocument.createElement("disabled");
				disabledActive.setTextContent("0");
				active.appendChild(disabledActive);
				
				Element displayFormTypeActive = newDocument.createElement("displayFormType");
				displayFormTypeActive.setTextContent("select");
				active.appendChild(displayFormTypeActive);

				Element displayTypeActive = newDocument.createElement("displayType");
				displayTypeActive.setTextContent("active");
				active.appendChild(displayTypeActive);
				
				Element nameActive = newDocument.createElement("name");
				nameActive.setTextContent("active");
				active.appendChild(nameActive);

				Element numberActive = newDocument.createElement("number");
				numberActive.setTextContent("6");
				active.appendChild(numberActive);

				Element prettyNameActive = newDocument.createElement("prettyName");
				prettyNameActive.setTextContent("Active");
				active.appendChild(prettyNameActive);

				Element unmodifiableActive = newDocument.createElement("unmodifiable");
				unmodifiableActive.setTextContent("0");
				active.appendChild(unmodifiableActive);

				Element classTypeActive = newDocument.createElement("classType");
				classTypeActive.setTextContent("com.xpn.xwiki.objects.classes.BooleanClass");
				active.appendChild(classTypeActive);				
				
				classObject4.appendChild(active);

				Element address = newDocument.createElement("address");

				Element disabledAdd = newDocument.createElement("disabled");
				disabledAdd.setTextContent("0");
				address.appendChild(disabledAdd);

				Element nameAdd = newDocument.createElement("name");
				nameAdd.setTextContent("address");
				address.appendChild(nameAdd);

				Element numberAdd = newDocument.createElement("number");
				numberAdd.setTextContent("22");
				address.appendChild(numberAdd);

				Element prettyNameAdd = newDocument.createElement("prettyName");
				prettyNameAdd.setTextContent("Address");
				address.appendChild(prettyNameAdd);

				Element rowsAdd = newDocument.createElement("rows");
				rowsAdd.setTextContent("3");
				address.appendChild(rowsAdd);
				
				Element sizeAdd = newDocument.createElement("size");
				sizeAdd.setTextContent("40");
				address.appendChild(sizeAdd);

				Element unmodifiableAdd = newDocument.createElement("unmodifiable");
				unmodifiableAdd.setTextContent("0");
				address.appendChild(unmodifiableAdd);

				Element classTypeAdd = newDocument.createElement("classType");
				classTypeAdd.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
				address.appendChild(classTypeAdd);

				classObject4.appendChild(address);


				Element avatar = newDocument.createElement("avatar");

				Element disabled4 = newDocument.createElement("disabled");
				disabled4.setTextContent("0");
				avatar.appendChild(disabled4);

				Element nameAvatar = newDocument.createElement("name");
				nameAvatar.setTextContent("avatar");
				avatar.appendChild(nameAvatar);

				Element numberAvatar = newDocument.createElement("number");
				numberAvatar.setTextContent("20");
				avatar.appendChild(numberAvatar);

				Element prettyNameAvatar = newDocument.createElement("prettyName");
				prettyNameAvatar.setTextContent("Avatar");
				avatar.appendChild(prettyNameAvatar);

				Element sizeAvatar = newDocument.createElement("size");
				sizeAvatar.setTextContent("30");
				avatar.appendChild(sizeAvatar);

				Element unmodifiableAvatar = newDocument.createElement("unmodifiable");
				unmodifiableAvatar.setTextContent("0");
				avatar.appendChild(unmodifiableAvatar);

				Element classTypeAvatar = newDocument.createElement("classType");
				classTypeAvatar.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
				avatar.appendChild(classTypeAvatar);

				classObject4.appendChild(avatar);


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

				classObject4.appendChild(blog);


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

				classObject4.appendChild(blogfeed);

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

				classObject4.appendChild(comment2);

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

				classObject4.appendChild(company);

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

				classObject4.appendChild(default_language);

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

				classObject4.appendChild(displayHiddenDocuments);

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

				classObject4.appendChild(editor);

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
				prettyNameemail.setTextContent("e-Mail");
				email.appendChild(prettyNameemail);

				Element sizeemail = newDocument.createElement("size");
				sizeemail.setTextContent("30");
				email.appendChild(sizeemail);

				Element unmodifiableemail = newDocument.createElement("unmodifiable");
				unmodifiableemail.setTextContent("0");
				email.appendChild(unmodifiableemail);

				Element validationRegExp2 = newDocument.createElement("validationRegExp");
				validationRegExp2.setTextContent("/^(([^@\\s]+)@((?:[-a-zA-Z0-9]+\\.)+[a-zA-Z]{2,}))?$/");
				email.appendChild(validationRegExp2);

				Element classTypeemail = newDocument.createElement("classType");
				classTypeemail.setTextContent("com.xpn.xwiki.objects.classes.EmailClass");
				email.appendChild(classTypeemail);

				classObject4.appendChild(email);

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

				classObject4.appendChild(first_name);

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

				classObject4.appendChild(imaccount);

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

				classObject4.appendChild(imtype);

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

				classObject4.appendChild(last_name);

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

				classObject4.appendChild(password);

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

				classObject4.appendChild(phone);

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

				classObject4.appendChild(skin);

				Element timezone = newDocument.createElement("timezone");

				Element customDisplay2 = newDocument.createElement("customDisplay");
				customDisplay2.setTextContent("{{velocity}}"
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
				timezone.appendChild(customDisplay2);

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

				classObject4.appendChild(timezone);

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

				classObject4.appendChild(usertype);

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

				classObject4.appendChild(validkey);

				object4.appendChild(classObject4);



				Element propertyn = newDocument.createElement("property");

				Element activeProperty = newDocument.createElement("active");
				activeProperty.setTextContent("1");
				propertyn.appendChild(activeProperty);

				object4.appendChild(propertyn);

				Element property6 = newDocument.createElement("property");


				Element emailProperty = newDocument.createElement("email");
				emailProperty.setTextContent(emailEmployee);
				property6.appendChild(emailProperty);

				object4.appendChild(property6);

				Element property7 = newDocument.createElement("property");

				Element first_nameProperty = newDocument.createElement("first_name");
				first_nameProperty.setTextContent(employeeName);
				property7.appendChild(first_nameProperty);

				object4.appendChild(property7);


				Element property8 = newDocument.createElement("property");

				Element last_nameProperty = newDocument.createElement("last_name");
				last_nameProperty.setTextContent(employeeName);
				property8.appendChild(last_nameProperty);

				object4.appendChild(property8);


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

				object4.appendChild(property9);


				// chiusura del file
				xwikidoc.appendChild(object4);


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
		//	System.out.println("Procedura già effettuata");
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
