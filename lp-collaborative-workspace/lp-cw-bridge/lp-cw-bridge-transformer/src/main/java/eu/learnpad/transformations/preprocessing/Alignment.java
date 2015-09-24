package eu.learnpad.transformations.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Alignment {
	
	public String XMIHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Ado:ADOXMLType xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:Ado=\"http://www.ado.org\" xsi:schemaLocation=\"http://www.ado.org /Adoxx2XWiki/models/Ado.ecore\">";
	public String XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE ADOXML SYSTEM \"adoxml31.dtd\"><ADOXML version=\"3.1\" date=\"31.07.2015\" time=\"17:29\" database=\"lpad\" username=\"Admin\" adoversion=\"Version 1.0 4.0\">";
	
	
	public String sanitizer(String modelInputPath) throws ParserConfigurationException, SAXException, IOException{
		String XmlString;
		
		String resultFilePath = "tmp/model_aligned.xmi";

		BufferedReader br;
		String line;
		StringBuilder sb = new StringBuilder();
		
		
		
		File inputFile = new File(modelInputPath);
		
		
		
		if(inputFile.exists()){
			
			PrintWriter out = new PrintWriter(resultFilePath);
			
			System.out.println("The input file exist!");
			try {
				br = new BufferedReader(new FileReader(inputFile));
				
				while((line=br.readLine())!= null){
					sb.append(line.trim());
//					sb.append(System.getProperty("line.separator"));
				}
				
				br.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			XmlString=sb.toString();
			
			
			
			//Simple replace of the header declaration
			XmlString = updateHeader(XmlString);
			
//			System.out.println(XmlString);

			List<String> allTags = getAllTagsName(XmlString);
			
			for (String tagName : allTags) {
				XmlString = XmlString.replaceAll("<"+tagName+"", "<"+tagName.substring(0, 1).toLowerCase() + tagName.substring(1)+"");
				XmlString = XmlString.replaceAll("</"+tagName+">", "</"+tagName.substring(0, 1).toLowerCase() + tagName.substring(1)+">");
			}
			
			
			
			out.println(XmlString);
			out.close();

			//			//replace of all the tags in respect to the xmi
//			XmlString = updateBody(XmlString);
//			
//			String fileName = getFileNameFromPath(modelInputPath);
//			
//			return createXMIFile(XmlString, fileName);
		
			return resultFilePath;
		}else{
			System.out.println("The input file does not exist!");
			return null;
		}
			
		
	}
	
	
	private String updateHeader(String XmlString){
		XmlString = XmlString.replace(XMLHeader, XMIHeader).trim();
//		XmlString = XmlString.replace("<ADOXML", "<Ado:ADOXMLType").trim();
		/*
		 * Delete node:
		 * <ADOXML version="3.1" date="31.07.2015" time="17:29" database="lpad" username="Admin" adoversion="Version 1.0 4.0">
		 */
//		XmlString = XmlString.replace("<ADOXML (.*?)>", "");
		XmlString = XmlString.replace("</ADOXML>", "</Ado:ADOXMLType>").trim();
		return XmlString;
	}
	
	
	
//	private String updateBody(String XmlString){
//		//TODO
//	    return null;
//	}
	
	
	public List<String> getAllTagsName(String xmlString) throws ParserConfigurationException, SAXException, IOException{
		Set<String> setTest = new HashSet<String>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		  
	    DocumentBuilder builder;
	    builder = factory.newDocumentBuilder();
	    Document document = Utils.readXml(new StringReader(xmlString));
		
//		 DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//		 DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//		 Document document = docBuilder.parse(new File(filepath));

		 NodeList nodeList = document.getElementsByTagName("*");
		    for (int i = 0; i < nodeList.getLength(); i++) {
		        Node node = nodeList.item(i);
		        
		        setTest.add(node.getNodeName());
		        
//		        String output = node.getNodeName().substring(0, 1).toLowerCase() + node.getNodeName().substring(1);
		    }

		    List<String> tagList = new ArrayList<String>();
		    
		    for (String string : setTest) {
		    	if(!string.equals("Ado:ADOXMLType")){
		    		tagList.add(string);
		    	}
			}
		    
		    //List sorting
		    java.util.Collections.sort(tagList, new MyComparator());
		    
		    return tagList;
	}
	
	
//	private String createXMIFile(String xmlString, String fileName){
//		String xmiFilePath = "";
//		
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		  
//	    DocumentBuilder builder;
//	        try
//	        {
//	            builder = factory.newDocumentBuilder();
//	 
//	            Document document = Utils.readXml(new StringReader(xmlString));
//	            
//	            // Use String reader
////	            Document document = builder.parse( new InputSource(new StringReader( xmlString ) ) );
//	 
//	            TransformerFactory tranFactory = TransformerFactory.newInstance();
//	            Transformer aTransformer = tranFactory.newTransformer();
//	            Source src = new DOMSource( document );
//	            
//	            xmiFilePath = "tmp/" + fileName + ".xmi";
//	            File xmiFile = new File(xmiFilePath);
//	            if(xmiFile.exists()){
//					System.out.println("XMI file created!");
//				}else{
//					System.out.println("Error creating XMI file!");
//				}
//	            
//	            Result dest = new StreamResult( xmiFile );
//	            aTransformer.transform( src, dest );
//	        } catch (Exception e)
//	        {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        }
//	        
//		return xmiFilePath;
//	}
	
	private String getFileNameFromPath(String filePath){
		
		return FilenameUtils.getBaseName(filePath); //get the name of the file without extension
		
//		Path p = Paths.get(filePath);
//		return p.getFileName().toString();
	}

}
