package eu.learnpad.transformations.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	
	public static String XMIHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Ado:ADOXMLType xmi:version=\"2.0\"  xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:Ado=\"http://www.ado.org\" xsi:schemaLocation=\"http://www.ado.org /Adoxx2XWiki/models/Ado.ecore\">\n";
//	public String XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE ADOXML SYSTEM \"adoxml31.dtd\"><ADOXML version=\"3.1\" date=\"31.07.2015\" time=\"17:29\" database=\"lpad\" username=\"Admin\" adoversion=\"Version 1.0 4.0\">";
	
	
	public String sanitizer(String modelInputPath) throws Exception{
		String XmlString;
		
		String resultFilePath = "tmp/model_aligned.xmi";

		BufferedReader br;
		String line;
		StringBuilder sb = new StringBuilder();
		

		//Delete first 3 rows
		int nRowToDelete = 2;
		while(nRowToDelete >= 0){
			removeNthLine(modelInputPath, nRowToDelete);
			nRowToDelete--;
		}

		int rowToInsert  = 1; //top of the file
		insertStringInFile(modelInputPath, rowToInsert, XMIHeader.trim());
		replaceIntoAFile(modelInputPath);
		
		
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
			
//			XmlString = XmlString.replace("</ADOXML>", "</Ado:ADOXMLType>").trim();
			
			
			
			//Simple replace of the header declaration
//			XmlString = updateHeader(XmlString);
			
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
	
	
	private void insertStringInFile(String inputPathFile, int lineno, String lineToBeInserted) throws Exception {
		
			File inFile = new File(inputPathFile);
		    // temp file
		    File outFile = new File("tmp/temp_file.tmp");
		     
		    // input
		    FileInputStream fis  = new FileInputStream(inFile);
		    BufferedReader in = new BufferedReader
		        (new InputStreamReader(fis));

		    // output         
		    FileOutputStream fos = new FileOutputStream(outFile);
		    PrintWriter out = new PrintWriter(fos);

		    String thisLine = "";
		    int i =1;
		    while ((thisLine = in.readLine()) != null) {
		      if(i == lineno) out.println(lineToBeInserted);
		      out.println(thisLine);
		      i++;
		      }
		   out.flush();
		   out.close();
		   in.close();
		    
		   inFile.delete();
		   outFile.renameTo(inFile);
		 }
	
	
	
	private void replaceIntoAFile(String filePath) throws IOException{
		Path path = Paths.get(filePath);
		Charset charset = StandardCharsets.UTF_8;
	
		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll("</ADOXML>", "</Ado:ADOXMLType>");
		Files.write(path, content.getBytes(charset));
	}
	
	
	private void removeNthLine(String f, int toRemove) throws IOException {
	    RandomAccessFile raf = new RandomAccessFile(f, "rw");

	    // Leave the n first lines unchanged.
	    for (int i = 0; i < toRemove; i++){
	    	raf.readLine();
	    }

	    // Shift remaining lines upwards.
	    long writePos = raf.getFilePointer();
	    raf.readLine();
	    long readPos = raf.getFilePointer();

	    byte[] buf = new byte[1024];
	    int n;
	    while (-1 != (n = raf.read(buf))) {
	        raf.seek(writePos);
	        raf.write(buf, 0, n);
	        readPos += n;
	        writePos += n;
	        raf.seek(readPos);
	    }

	    raf.setLength(writePos);
	    raf.close();
	}
	
	
	private List<String> getAllTagsName(String xmlString) throws ParserConfigurationException, SAXException, IOException{
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
	
	
//	private String getFileNameFromPath(String filePath){
//		
//		return FilenameUtils.getBaseName(filePath); //get the name of the file without extension
//		
////		Path p = Paths.get(filePath);
////		return p.getFileName().toString();
//	}

}
