package eu.learnpad.transformations.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Class used for the pre-processing of XML files from modeling applications of third parties.
	These files may be sub-standard XML for this request is a phase alignment to make these XML compliant.
 * @author Basciani Francesco
 * @version 1.0
 */
public class Alignment {
	
	private String tmpModelFolder = "/tmp/learnpad/mt";
	private String tmpFileFromInputStream = "fileFromInputStream.xml";
	private boolean deleteFile = true;
	
	public static String ADOXX_XMIHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Ado:ADOXMLType xmi:version=\"2.0\"  xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:Ado=\"http://www.ado.org\" xsi:schemaLocation=\"http://www.ado.org /Adoxx2XWiki/models/Ado.ecore\">\n";
	
	
	
	public boolean sanitizerForADOXX(InputStream modelInputStream, OutputStream out) throws Exception {
		
		boolean result = false;
		
		Path modelPath = Paths.get(String.format("%s/%s", tmpModelFolder, tmpFileFromInputStream));
		Files.deleteIfExists(modelPath);
		Files.copy(modelInputStream, modelPath);
		String fileFromInputStreamPath = modelPath.toString();
		
		File fileToSanitize = sanitizerForADOXX(fileFromInputStreamPath);
		
		//TODO check also if the outputstream was written correctly
		if(fileToSanitize.exists()){
			result = true;
		}
		
		//Write to the OutputStream provided as input
		Files.copy(fileToSanitize.toPath(), out);
		
		if(deleteFile){
			File fileToDelete = new File(tmpModelFolder + tmpFileFromInputStream);
			fileToDelete.delete();
			System.out.println("Tmp file ("+tmpModelFolder + tmpFileFromInputStream+") from input stream deleted!");
		}
		
		if(result){
			System.out.println("Alignment with ADOXX done!");
		}
		
		return result;
	}
	
	public boolean sanitizerForMagicDraw(InputStream modelInputStream, OutputStream out) throws Exception {
		
		boolean result = false;

        Path modelPath = Paths.get(String.format("%s/%s", tmpModelFolder, tmpFileFromInputStream));
        Files.copy(modelInputStream, modelPath);
        String fileFromInputStreamPath = modelPath.toString();
		
		File fileToSanitize = sanitizerForMagicDraw(fileFromInputStreamPath);
		
		//TODO check also if the outputstream was written correctly
		if(fileToSanitize.exists()){
			result = true;
		}
		
		//Write to the OutputStream provided as input
        Files.copy(fileToSanitize.toPath(), out);
		
		if(deleteFile){
			File fileToDelete = new File(tmpModelFolder + tmpFileFromInputStream);
			fileToDelete.delete();
			System.out.println("Tmp file ("+tmpModelFolder + tmpFileFromInputStream+") from input stream deleted!");
		}
		
		if(result){
			System.out.println("Alignment with Magic Draw done!");
		}
		
		return result;
	}
	
	
	
	//TODO
	private File sanitizerForMagicDraw(String modelInputPath){
		
		return null;
		
	}
	
	/**
	 * This method take the path of the ADOXX XML file and, after a phase of sanitizing, return an XMI file that could be processed by an ATL Transformation.
	 * To sanitize the XML it create a copy of the original file in a temporary folder with the same name of the original one.
	 * It get as a String the content of the XML and replace the original Header of the XML with new one (in order to make as result a conform XML).
	 * Furthermore it parse with XML Java Parser the string representing the XML in order to take all the tags in it.
	 * Once we have all the tags the method order this list by length in decreasing way so we are able to replace, starting from the longest string, all the tags with the first letter lowercase and all the remaining letters in uppercase (e.g. <ATTRIBUTE> became <aTTRIBUTE>).
	 * The phase of ordering is important to avoid situation like: <ATTRIBUTE_TYPE> could became <ATTRIBUTE_tYPE> if the first replace was with the tag <TYPE> instead of <ATTRIBUTE_TYPE>.
	 * Optionally at the end the temporary file used for the processing could be delete or not.
	 * @param modelInputPath String path of the XML file to be sanitized.
	 * @return The String path of the new XMI file, ready to be processed by an ATL Transformation.
	 * @throws Exception
	 */
	private File sanitizerForADOXX(String modelInputPath) throws Exception{
		String XmlString;
		
		Utils utils = new Utils();
		
		String basenameInputModel = FilenameUtils.getBaseName(modelInputPath);
		
		System.out.println(basenameInputModel);

		String copyModelInputPath = tmpModelFolder + "copy_" + basenameInputModel + ".xmi";
		
		File src = new File(modelInputPath);
		File dst = new File(copyModelInputPath);
		
		Files.copy(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
		System.out.println("Temporary copy of the XML file provided as input was created: "+dst.getName()+"!");
		
		String resultFilePath = tmpModelFolder + basenameInputModel + ".xmi";

		BufferedReader br;
		String line;
		StringBuilder sb = new StringBuilder();
		

		//Delete first 3 rows
//		int nRowToDelete = 2;
		//Delete first 1 rows with value = 0
		int nRowToDelete = 0;
		while(nRowToDelete >= 0){
			utils.removeNthLine(copyModelInputPath, nRowToDelete);
			nRowToDelete--;
		}

		int rowToInsert  = 1; //top of the file
		utils.insertStringInFile(copyModelInputPath, rowToInsert, ADOXX_XMIHeader.trim());
		
		/*
		 * This replacement is important in order to maintain the XML conformance after the XML header replacement.
		 * Simply this method replace the last tag of the original XML file: </ADOXML> to </Ado:ADOXMLType>.
		 */
		utils.replaceIntoAFile(copyModelInputPath, "</ADOXML>", "</Ado:ADOXMLType>");
		
		
		File inputFile = new File(copyModelInputPath);
		
		
		if(inputFile.exists()){
			
			System.out.println("The input file exist!");
			try {
				br = new BufferedReader(new FileReader(inputFile));
				
				while((line=br.readLine())!= null){
					sb.append(line.trim() + "\n");
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
			
			List<String> allTags = getAllTagsName(XmlString);
			
			for (String tagName : allTags) {
				XmlString = XmlString.replaceAll("<"+tagName+"", "<"+tagName.substring(0, 1).toLowerCase() + tagName.substring(1)+"");
				XmlString = XmlString.replaceAll("</"+tagName+">", "</"+tagName.substring(0, 1).toLowerCase() + tagName.substring(1)+">");
			}
			
			String resultXmlString = getValueFromAdoATTRIBUTETag(XmlString);

			//CREATE RESULT FILE
			File resultFile = new File(resultFilePath);
			resultFile.createNewFile();
			PrintWriter out = new PrintWriter(resultFile);
			out.write(resultXmlString);
			out.close();
			
			//Delete temporary file
			if(deleteFile){
				if(dst.delete()){
					System.out.println("Temporary copy of the XML file provided as input was deleted: "+dst.getName()+"!");
				}else{
					System.out.println("Can't delete temporary copy of the XML file provided as input: "+dst.getName()+"!");
				}
				
			}
			
			return resultFile;
		}else{
			System.out.println("The input file does not exist!");
			return null;
		}
			
		
	}
	
	/**
	 * The function get text between tags <ATTRIBUTE> and </ATTRIBUTE> and put it into value attribute into the tag.
	 * It takes the String of the XML and using an XML Java Parser it navigate throw the file searching for the text between the two tags.
	 * It do is job directly to the file passed in input as String path.
	 * @param xmlString String representing the XML 
	 * @param outputFilePath String representing the path of the file to be modified 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws TransformerException 
	 */
	private String getValueFromAdoATTRIBUTETag(String xmlString) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		 Document document = Utils.readXml(new StringReader(xmlString));
	    
		NodeList nodeList = document.getElementsByTagName("aTTRIBUTE");
		
		Utils utils = new Utils();
		
		    for (int i = 0; i < nodeList.getLength(); i++) {
		    	Element el = (Element) nodeList.item(i);
		    	String attributeToInsert = utils.escapeHtml(el.getTextContent());
		    	el.setAttribute("value", attributeToInsert);
		    	el.setTextContent(""); //Put empty text where first there was text
		    }
		
		    String result = Utils.convertDocumentToString(document);
		   
		    return result;
		    
		    
//		 // write the content into xml file
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		DOMSource source = new DOMSource(document);
//		StreamResult result = new StreamResult(resultFile);
//		transformer.transform(source, result);
		    
	}
	
	/**
	 * This method get a list with all the names of the tags, ordered in decreasing way based on length of the strings, of the XML passed as String input throw an XML Java parser.
	 * @param xmlString
	 * @return A list of String representing all the tags name.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private List<String> getAllTagsName(String xmlString) throws ParserConfigurationException, SAXException, IOException{
		Set<String> setTest = new HashSet<String>();
		
	    Document document = Utils.readXml(new StringReader(xmlString));

	    /**
	     * With "*" we are saying that we are interested in all the tags (no one in particular)
	     */
		 NodeList nodeList = document.getElementsByTagName("*");
		    for (int i = 0; i < nodeList.getLength(); i++) {
		        Node node = nodeList.item(i);
		        
		        setTest.add(node.getNodeName());
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
	
}
