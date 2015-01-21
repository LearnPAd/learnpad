package ch.fhnw.support;

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
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SpaceWebHome {



	public static void spaceCreator( JFileChooser directoryBPMN, String spaceName) throws Exception {


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

		//real package creator
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();


		Document newDocument = db.newDocument();


		Element xwikidoc = newDocument.createElement("xwikidoc");
		xwikidoc.setAttribute("version", "1.1");
		newDocument.appendChild(xwikidoc);

		Element web = newDocument.createElement("web");
		web.setTextContent(spaceName);
		xwikidoc.appendChild(web);


		Element name = newDocument.createElement("name");
		name.setTextContent("WebHome");
		xwikidoc.appendChild(name);

		Element language = newDocument.createElement("language");
		xwikidoc.appendChild(language);

		Element defaultLanguage = newDocument.createElement("defaultLanguage");
		xwikidoc.appendChild(defaultLanguage);

		Element translation = newDocument.createElement("translation");
		translation.setTextContent("0");
		xwikidoc.appendChild(translation);

		Element creator = newDocument.createElement("creator");
		creator.setTextContent("XWiki." + "Admin");
		xwikidoc.appendChild(creator);

		Element creationDate = newDocument.createElement("creationDate");
		creationDate.setTextContent("1414691917000");
		xwikidoc.appendChild(creationDate);

		Element parent = newDocument.createElement("parent");
		parent.setTextContent("xwiki:Main.WebHome");
		xwikidoc.appendChild(parent);

		Element author = newDocument.createElement("author");
		author.setTextContent("XWiki." + "Admin");
		xwikidoc.appendChild(author);

		Element contentAuthor = newDocument.createElement("contentAuthor");
		contentAuthor.setTextContent("XWiki." + "Admin");
		xwikidoc.appendChild(contentAuthor);

		Element date = newDocument.createElement("date");
		date.setTextContent("1414691931000");
		xwikidoc.appendChild(date);

		Element contentUpdateDate = newDocument.createElement("contentUpdateDate");
		contentUpdateDate.setTextContent("1414691917000");
		xwikidoc.appendChild(contentUpdateDate);

		Element version = newDocument.createElement("version");
		version.setTextContent("1.1");
		xwikidoc.appendChild(version);

		Element title = newDocument.createElement("title");
		title.setTextContent(spaceName);
		xwikidoc.appendChild(title);

		Element updateWatchlist = newDocument.createElement("comment");
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
		content.setTextContent("{{dashboard/}}");
		xwikidoc.appendChild(content);

		Element object = newDocument.createElement("object");
		////////////////////////////////////////xwikidoc.appendChild(object);

		Element nameObject = newDocument.createElement("name");
		nameObject.setTextContent(spaceName + ".WebHome");
		object.appendChild(nameObject);

		Element number = newDocument.createElement("number");
		number.setTextContent("0");
		object.appendChild(number);

		Element className = newDocument.createElement("className");
		className.setTextContent("XWiki.DocumentSheetBinding");
		object.appendChild(className);

		Element guid = newDocument.createElement("guid");
		guid.setTextContent("5fe8932f-b904-4468-ab00-a37f5289996a");
		object.appendChild(guid);

		Element classObject = newDocument.createElement("class");

		///////////////////object.appendChild(classObject);

		Element nameClass = newDocument.createElement("name");
		nameClass.setTextContent("XWiki.DocumentSheetBinding");
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


		Element sheet = newDocument.createElement("sheet");
		////////////////

		Element customDisplay = newDocument.createElement("customDisplay");
		sheet.appendChild(customDisplay);

		Element disabled = newDocument.createElement("disabled");
		disabled.setTextContent("0");
		sheet.appendChild(disabled);

		Element nameWatch = newDocument.createElement("name");
		nameWatch.setTextContent("sheet");
		sheet.appendChild(nameWatch);

		Element numberWatch = newDocument.createElement("number");
		numberWatch.setTextContent("1");
		sheet.appendChild(numberWatch);

		Element picker = newDocument.createElement("picker");
		picker.setTextContent("0");
		sheet.appendChild(picker);

		Element prettyName = newDocument.createElement("prettyName");
		prettyName.setTextContent("Sheet");
		sheet.appendChild(prettyName);

		Element size = newDocument.createElement("size");
		size.setTextContent("30");
		sheet.appendChild(size);

		Element unmodifiable = newDocument.createElement("unmodifiable");
		unmodifiable.setTextContent("0");
		sheet.appendChild(unmodifiable);

		Element validationMessage = newDocument.createElement("validationMessage");
		sheet.appendChild(validationMessage);

		Element validationRegExp = newDocument.createElement("validationRegExp");
		sheet.appendChild(validationRegExp);

		Element classType = newDocument.createElement("classType");
		classType.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
		sheet.appendChild(classType);

		classObject.appendChild(sheet);

		object.appendChild(classObject);


		Element property = newDocument.createElement("property");

		Element sheetProperty = newDocument.createElement("sheet");
		property.appendChild(sheetProperty);

		object.appendChild(property);

		xwikidoc.appendChild(object);



		Element object2 = newDocument.createElement("object");
		////////////////////////////////////////xwikidoc.appendChild(object);

		Element nameObject2 = newDocument.createElement("name");
		nameObject2.setTextContent(spaceName + ".WebHome");
		object2.appendChild(nameObject2);

		Element number2 = newDocument.createElement("number");
		number2.setTextContent("0");
		object2.appendChild(number2);

		Element className2 = newDocument.createElement("className");
		className2.setTextContent("XWiki.GadgetClass");
		object2.appendChild(className2);

		Element guid2 = newDocument.createElement("guid");
		guid2.setTextContent("22e4cb73-b4a7-40cd-bf4c-9b2fef3b2d40");
		object2.appendChild(guid2);

		Element classObject2 = newDocument.createElement("class");

		///////////////////object.appendChild(classObject);

		Element nameClass2 = newDocument.createElement("name");
		nameClass2.setTextContent("XWiki.GadgetClass");
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


		Element contentObj = newDocument.createElement("content");
		////////////////

		Element disabled2 = newDocument.createElement("disabled");
		disabled2.setTextContent("0");
		contentObj.appendChild(disabled2);

		Element editor = newDocument.createElement("editor");
		editor.setTextContent("---");
		contentObj.appendChild(editor);

		Element nameAllow = newDocument.createElement("name");
		nameAllow.setTextContent("content");
		contentObj.appendChild(nameAllow);

		Element numberAllow = newDocument.createElement("number");
		numberAllow.setTextContent("2");
		contentObj.appendChild(numberAllow);

		Element pickerObj = newDocument.createElement("picker");
		pickerObj.setTextContent("0");
		contentObj.appendChild(pickerObj);

		Element prettyName2 = newDocument.createElement("prettyName");
		prettyName2.setTextContent("content");
		contentObj.appendChild(prettyName2);

		Element rowsObj = newDocument.createElement("rows");
		rowsObj.setTextContent("5");
		contentObj.appendChild(rowsObj);

		Element sizeObj = newDocument.createElement("size");
		sizeObj.setTextContent("40");
		contentObj.appendChild(sizeObj);

		Element unmodifiableObj = newDocument.createElement("unmodifiable");
		unmodifiableObj.setTextContent("0");
		contentObj.appendChild(unmodifiableObj);

		Element validationMessageObj = newDocument.createElement("validationMessage");
		contentObj.appendChild(validationMessageObj);

		Element validationRegExpObj = newDocument.createElement("validationRegExp");
		contentObj.appendChild(validationRegExpObj);

		Element classType2 = newDocument.createElement("classType");
		classType2.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
		contentObj.appendChild(classType2);

		classObject2.appendChild(contentObj);

		Element position = newDocument.createElement("position");
		/////////////xwikidoc.appendChild(documents);

		Element disabledPosition = newDocument.createElement("disabled");
		disabledPosition.setTextContent("0");
		position.appendChild(disabledPosition);

		Element nameGroups = newDocument.createElement("name");
		nameGroups.setTextContent("position");
		position.appendChild(nameGroups);

		Element numberGroups = newDocument.createElement("number");
		numberGroups.setTextContent("3");
		position.appendChild(numberGroups);

		Element pickerPosition = newDocument.createElement("picker");
		pickerPosition.setTextContent("0");
		position.appendChild(pickerPosition);

		Element prettyNamePosition = newDocument.createElement("prettyName");
		prettyNamePosition.setTextContent("position");
		position.appendChild(prettyNamePosition);

		Element sizePosition = newDocument.createElement("size");
		sizePosition.setTextContent("30");
		position.appendChild(sizePosition);

		Element unmodifiable3 = newDocument.createElement("unmodifiable");
		unmodifiable3.setTextContent("0");
		position.appendChild(unmodifiable3);

		Element validationMessagePosition = newDocument.createElement("validationMessage");
		position.appendChild(validationMessagePosition);

		Element validationRegExpPosition = newDocument.createElement("validationRegExp");
		position.appendChild(validationRegExpPosition);

		Element classType3 = newDocument.createElement("classType");
		classType3.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
		position.appendChild(classType3);

		classObject2.appendChild(position);


		Element title2 = newDocument.createElement("title");

		Element disabledTitle = newDocument.createElement("disabled");
		disabledTitle.setTextContent("0");
		title2.appendChild(disabledTitle);

		Element nameTitle = newDocument.createElement("name");
		nameTitle.setTextContent("title");
		title2.appendChild(nameTitle);

		Element numberTitle = newDocument.createElement("number");
		numberTitle.setTextContent("1");
		title2.appendChild(numberTitle);

		Element pickerTitle = newDocument.createElement("picker");
		pickerTitle.setTextContent("0");
		title2.appendChild(pickerTitle);

		Element prettyNameTitle = newDocument.createElement("prettyName");
		prettyNameTitle.setTextContent("title");
		title2.appendChild(prettyNameTitle);

		Element sizeTitle = newDocument.createElement("size");
		sizeTitle.setTextContent("30");
		title2.appendChild(sizeTitle);

		Element unmodifiableTitle = newDocument.createElement("unmodifiable");
		unmodifiableTitle.setTextContent("0");
		title2.appendChild(unmodifiableTitle);

		Element validationMessageTitle = newDocument.createElement("validationMessage");
		title2.appendChild(validationMessageTitle);

		Element validationRegExpTitle = newDocument.createElement("validationRegExp");
		title2.appendChild(validationRegExpTitle);

		Element classTypeTitle = newDocument.createElement("classType");
		classTypeTitle.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
		title2.appendChild(classTypeTitle);

		classObject2.appendChild(title2);

		object2.appendChild(classObject2);

		Element property2 = newDocument.createElement("property");

		Element contentProperty = newDocument.createElement("content");
		contentProperty.setTextContent("{{velocity}}" +
				"\n#if ($doc.space == \"Main\")" +
				"\n  {{spaces /}}" +
				"\n#else" +
				"\n  {{spaceindex space=\"$doc.space\" /}}" +
				"\n#end" +
				"\n{{/velocity}}\n");
		property2.appendChild(contentProperty);

		object2.appendChild(property2);

		Element property3 = newDocument.createElement("property");

		Element positionProperty = newDocument.createElement("position");
		positionProperty.setTextContent("1,1");
		property3.appendChild(positionProperty);

		object2.appendChild(property3);

		Element property4 = newDocument.createElement("property");

		Element usersProperty = newDocument.createElement("title");
		usersProperty.setTextContent("#if ($doc.space == 'Main')$services.localization.render('platform.dashboard.wiki.spaces')#{else}$services.localization.render('platform.dashboard.space.documents', [$doc.space])#end");
		property4.appendChild(usersProperty);

		object2.appendChild(property4);

		xwikidoc.appendChild(object2);




		Element object3 = newDocument.createElement("object");
		////////////////////////////////////////xwikidoc.appendChild(object);

		Element nameObject3 = newDocument.createElement("name");
		nameObject3.setTextContent(spaceName + ".WebHome");
		object3.appendChild(nameObject3);

		Element number3 = newDocument.createElement("number");
		number3.setTextContent("1");
		object3.appendChild(number3);

		Element className3 = newDocument.createElement("className");
		className3.setTextContent("XWiki.GadgetClass");
		object3.appendChild(className3);

		Element guid3 = newDocument.createElement("guid");
		guid3.setTextContent("b614f7fd-46f1-4932-8b1f-1fab1e21d3ef");
		object3.appendChild(guid3);

		Element classObject3 = newDocument.createElement("class");

		///////////////////object.appendChild(classObject);

		Element nameClass3 = newDocument.createElement("name");
		nameClass3.setTextContent("XWiki.GadgetClass");
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


		Element contentClass = newDocument.createElement("content");
		////////////////

		Element disabled3 = newDocument.createElement("disabled");
		disabled3.setTextContent("0");
		contentClass.appendChild(disabled3);

		Element editor3 = newDocument.createElement("editor");
		editor3.setTextContent("---");
		contentClass.appendChild(editor3);

		Element name3 = newDocument.createElement("name");
		name3.setTextContent("content");
		contentClass.appendChild(name3);

		Element numberClass = newDocument.createElement("number");
		numberClass.setTextContent("2");
		contentClass.appendChild(numberClass);

		Element pickerClass = newDocument.createElement("picker");
		pickerClass.setTextContent("0");
		contentClass.appendChild(pickerClass);

		Element prettyName3 = newDocument.createElement("prettyName");
		prettyName3.setTextContent("content");
		contentClass.appendChild(prettyName3);

		Element rows3 = newDocument.createElement("rows");
		rows3.setTextContent("5");
		contentClass.appendChild(rows3);

		Element size3 = newDocument.createElement("size");
		size3.setTextContent("40");
		contentClass.appendChild(size3);

		Element unmodifiable4 = newDocument.createElement("unmodifiable");
		unmodifiable4.setTextContent("0");
		contentClass.appendChild(unmodifiable4);

		Element validationMessageClass = newDocument.createElement("validationMessage");
		contentClass.appendChild(validationMessageClass);

		Element validationRegExpClass = newDocument.createElement("validationRegExp");
		contentClass.appendChild(validationRegExpClass);

		Element classType4 = newDocument.createElement("classType");
		classType4.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
		contentClass.appendChild(classType4);

		classObject3.appendChild(contentClass);

		Element position2 = newDocument.createElement("position");
		/////////////xwikidoc.appendChild(documents);

		Element disabledPos = newDocument.createElement("disabled");
		disabledPos.setTextContent("0");
		position2.appendChild(disabledPos);

		Element namePos = newDocument.createElement("name");
		namePos.setTextContent("position");
		position2.appendChild(namePos);

		Element numberPos = newDocument.createElement("number");
		numberPos.setTextContent("3");
		position2.appendChild(numberPos);

		Element pickerPos = newDocument.createElement("picker");
		pickerPos.setTextContent("0");
		position2.appendChild(pickerPos);

		Element prettyNamePos = newDocument.createElement("prettyName");
		prettyNamePos.setTextContent("position");
		position2.appendChild(prettyNamePos);

		Element sizePos = newDocument.createElement("size");
		sizePos.setTextContent("30");
		position2.appendChild(sizePos);

		Element unmodifiablePos = newDocument.createElement("unmodifiable");
		unmodifiablePos.setTextContent("0");
		position2.appendChild(unmodifiablePos);

		Element validationMessagePos = newDocument.createElement("validationMessage");
		position2.appendChild(validationMessagePos);

		Element validationRegExpPos = newDocument.createElement("validationRegExp");
		position2.appendChild(validationRegExpPos);

		Element classType5 = newDocument.createElement("classType");
		classType5.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
		position2.appendChild(classType5);

		classObject3.appendChild(position2);


		Element title3 = newDocument.createElement("title");
		/////////////xwikidoc.appendChild(documents);

		Element disabledTitle3 = newDocument.createElement("disabled");
		disabledTitle3.setTextContent("0");
		title3.appendChild(disabledTitle3);

		Element nameTitle3 = newDocument.createElement("name");
		nameTitle3.setTextContent("title");
		title3.appendChild(nameTitle3);

		Element numberTitle3 = newDocument.createElement("number");
		numberTitle3.setTextContent("1");
		title3.appendChild(numberTitle3);

		Element pickerTitle3 = newDocument.createElement("picker");
		pickerTitle3.setTextContent("0");
		title3.appendChild(pickerTitle3);

		Element prettyNameTitle3 = newDocument.createElement("prettyName");
		prettyNameTitle3.setTextContent("title");
		title3.appendChild(prettyNameTitle3);

		Element sizeTitle3 = newDocument.createElement("size");
		sizeTitle3.setTextContent("30");
		title3.appendChild(sizeTitle3);

		Element unmodifiableTitle3 = newDocument.createElement("unmodifiable");
		unmodifiableTitle3.setTextContent("0");
		title3.appendChild(unmodifiableTitle3);

		Element validationMessageTitle3 = newDocument.createElement("validationMessage");
		title3.appendChild(validationMessageTitle3);

		Element validationRegExpTitle3 = newDocument.createElement("validationRegExp");
		title3.appendChild(validationRegExpTitle3);

		Element classTypeTitle3 = newDocument.createElement("classType");
		classTypeTitle3.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
		title3.appendChild(classTypeTitle3);

		classObject3.appendChild(title3);

		object3.appendChild(classObject3);

		Element property5 = newDocument.createElement("property");

		Element contentProperty2 = newDocument.createElement("content");
		contentProperty2.setTextContent("{{velocity}}" +
				"\n#set($tagSpace = $NULL)" +
				"\n#if ($doc.space != \"Main\")" +
				"\n  #set($tagSpace = $doc.space)" +
				"\n#end" +
				"\n" +
				"\n{{tagcloud  space=\"$!tagSpace\" /}}" +
				"\n{{/velocity}}\n");
		property5.appendChild(contentProperty2);

		object3.appendChild(property5);

		Element property6 = newDocument.createElement("property");


		Element positionProperty2 = newDocument.createElement("position");
		positionProperty2.setTextContent("1,2");
		property6.appendChild(positionProperty2);

		object3.appendChild(property6);

		Element property7 = newDocument.createElement("property");

		Element titleProperty = newDocument.createElement("title");
		titleProperty.setTextContent("$services.localization.render('platform.dashboard.wiki.tagcloud')");
		property7.appendChild(titleProperty);

		object3.appendChild(property7);

		// chiusura del file
		xwikidoc.appendChild(object3);


		Element object4 = newDocument.createElement("object");
		////////////////////////////////////////xwikidoc.appendChild(object);

		Element nameObject4 = newDocument.createElement("name");
		nameObject4.setTextContent(spaceName + ".WebHome");
		object4.appendChild(nameObject4);

		Element number4 = newDocument.createElement("number");
		number4.setTextContent("2");
		object4.appendChild(number4);

		Element className4 = newDocument.createElement("className");
		className4.setTextContent("XWiki.GadgetClass");
		object4.appendChild(className4);

		Element guid4 = newDocument.createElement("guid");
		guid4.setTextContent("65802a40-15b9-4a9f-b750-30e9b1a9363b");
		object4.appendChild(guid4);

		Element classObject4 = newDocument.createElement("class");

		///////////////////object.appendChild(classObject);

		Element nameClass4 = newDocument.createElement("name");
		nameClass4.setTextContent("XWiki.GadgetClass");
		classObject4.appendChild(nameClass4);

		Element customClassObject4 = newDocument.createElement("customClass");
		classObject4.appendChild(customClassObject4);

		Element customMapping4 = newDocument.createElement("customMapping");
		classObject4.appendChild(customMapping4);

		Element defaultViewSheet4 = newDocument.createElement("defaultViewSheet");
		classObject4.appendChild(defaultViewSheet4);

		Element defaultEditSheet4 = newDocument.createElement("defaultEditSheet");
		classObject4.appendChild(defaultEditSheet4);

		Element defaultWeb4 = newDocument.createElement("defaultWeb");
		classObject4.appendChild(defaultWeb4);

		Element nameField4 = newDocument.createElement("nameField");
		classObject4.appendChild(nameField4);

		Element validationScriptObject4 = newDocument.createElement("validationScript");
		classObject4.appendChild(validationScriptObject4);


		Element contentClass2 = newDocument.createElement("content");
		////////////////

		Element disabled4 = newDocument.createElement("disabled");
		disabled4.setTextContent("0");
		contentClass2.appendChild(disabled4);

		Element editor4 = newDocument.createElement("editor");
		editor4.setTextContent("---");
		contentClass2.appendChild(editor4);

		Element name4 = newDocument.createElement("name");
		name4.setTextContent("content");
		contentClass2.appendChild(name4);

		Element numberClass2 = newDocument.createElement("number");
		numberClass2.setTextContent("2");
		contentClass2.appendChild(numberClass2);

		Element pickerClass2 = newDocument.createElement("picker");
		pickerClass2.setTextContent("0");
		contentClass2.appendChild(pickerClass2);

		Element prettyName4 = newDocument.createElement("prettyName");
		prettyName4.setTextContent("content");
		contentClass2.appendChild(prettyName4);

		Element rows4 = newDocument.createElement("rows");
		rows4.setTextContent("5");
		contentClass2.appendChild(rows4);

		Element size4 = newDocument.createElement("size");
		size4.setTextContent("40");
		contentClass2.appendChild(size4);

		Element unmodifiable5 = newDocument.createElement("unmodifiable");
		unmodifiable5.setTextContent("0");
		contentClass2.appendChild(unmodifiable5);

		Element validationMessageClass2 = newDocument.createElement("validationMessage");
		contentClass2.appendChild(validationMessageClass2);

		Element validationRegExpClass2 = newDocument.createElement("validationRegExp");
		contentClass2.appendChild(validationRegExpClass2);

		Element classType6 = newDocument.createElement("classType");
		classType6.setTextContent("com.xpn.xwiki.objects.classes.TextAreaClass");
		contentClass2.appendChild(classType6);

		classObject4.appendChild(contentClass2);

		Element position3 = newDocument.createElement("position");
		/////////////xwikidoc.appendChild(documents);

		Element disabledPos2 = newDocument.createElement("disabled");
		disabledPos2.setTextContent("0");
		position3.appendChild(disabledPos2);

		Element namePos2 = newDocument.createElement("name");
		namePos2.setTextContent("position");
		position3.appendChild(namePos2);

		Element numberPos2 = newDocument.createElement("number");
		numberPos2.setTextContent("3");
		position3.appendChild(numberPos2);

		Element pickerPos2 = newDocument.createElement("picker");
		pickerPos2.setTextContent("0");
		position3.appendChild(pickerPos2);

		Element prettyNamePos2 = newDocument.createElement("prettyName");
		prettyNamePos2.setTextContent("position");
		position3.appendChild(prettyNamePos2);

		Element sizePos2 = newDocument.createElement("size");
		sizePos2.setTextContent("30");
		position3.appendChild(sizePos2);

		Element unmodifiablePos2 = newDocument.createElement("unmodifiable");
		unmodifiablePos2.setTextContent("0");
		position3.appendChild(unmodifiablePos2);

		Element validationMessagePos2 = newDocument.createElement("validationMessage");
		position3.appendChild(validationMessagePos2);

		Element validationRegExpPos2 = newDocument.createElement("validationRegExp");
		position3.appendChild(validationRegExpPos2);

		Element classType7 = newDocument.createElement("classType");
		classType7.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
		position3.appendChild(classType7);

		classObject4.appendChild(position3);


		Element title4 = newDocument.createElement("title");
		/////////////xwikidoc.appendChild(documents);

		Element disabledTitle4 = newDocument.createElement("disabled");
		disabledTitle4.setTextContent("0");
		title4.appendChild(disabledTitle4);

		Element nameTitle4 = newDocument.createElement("name");
		nameTitle4.setTextContent("title");
		title4.appendChild(nameTitle4);

		Element numberTitle4 = newDocument.createElement("number");
		numberTitle4.setTextContent("1");
		title4.appendChild(numberTitle4);

		Element pickerTitle4 = newDocument.createElement("picker");
		pickerTitle4.setTextContent("0");
		title4.appendChild(pickerTitle4);

		Element prettyNameTitle4 = newDocument.createElement("prettyName");
		prettyNameTitle4.setTextContent("title");
		title4.appendChild(prettyNameTitle4);

		Element sizeTitle4 = newDocument.createElement("size");
		sizeTitle4.setTextContent("30");
		title4.appendChild(sizeTitle4);

		Element unmodifiableTitle4 = newDocument.createElement("unmodifiable");
		unmodifiableTitle4.setTextContent("0");
		title4.appendChild(unmodifiableTitle4);

		Element validationMessageTitle4 = newDocument.createElement("validationMessage");
		title4.appendChild(validationMessageTitle4);

		Element validationRegExpTitle4 = newDocument.createElement("validationRegExp");
		title4.appendChild(validationRegExpTitle4);

		Element classTypeTitle4 = newDocument.createElement("classType");
		classTypeTitle4.setTextContent("com.xpn.xwiki.objects.classes.StringClass");
		title4.appendChild(classTypeTitle4);

		classObject4.appendChild(title4);

		object4.appendChild(classObject4);

		Element property8 = newDocument.createElement("property");

		Element contentProperty6 = newDocument.createElement("content");
		contentProperty6.setTextContent("{{velocity}}" +
				"\n#set($aSpace = $NULL)" + 
				"\n#if ($doc.space != \"Main\")" +
				"\n  #set ($aSpace = $doc.space)" +
				"\n#end" +
				"\n" +
				"\n{{activity spaces=\"$!aSpace\" /}}" +
				"\n{{/velocity}}\n");
		property8.appendChild(contentProperty6);

		object4.appendChild(property8);


		Element property9 = newDocument.createElement("property");

		Element positionProperty3 = newDocument.createElement("position");
		positionProperty3.setTextContent("2,1");
		property9.appendChild(positionProperty3);

		object4.appendChild(property9);


		Element property10 = newDocument.createElement("property");

		Element titleProperty2 = newDocument.createElement("title");
		titleProperty2.setTextContent("#if ($doc.space != 'Main')$services.localization.render('platform.dashboard.space.activity', [$doc.space])#{else}$services.localization.render('platform.dashboard.wiki.activity')#end");
		property10.appendChild(titleProperty2);

		object4.appendChild(property10);

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
		File dir = new File(directoryBPMN.getCurrentDirectory().toString() + "\\xml\\xwiki\\");
		dir.mkdir();
		StreamResult result = new StreamResult(new File(directoryBPMN.getCurrentDirectory().toString() + "\\xml\\" + spaceName + "\\WebHome.xml"));
		transformer.transform(source, result);

		System.out.println("\nSPACE HOMEPAGE GENERATED\n");

	}
}