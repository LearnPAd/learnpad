package eu.learnpad.transformations.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Few simple utils to read DOM. This is originally from the Jakarta Commons Modeler.
 * @author Basciani Francesco
 * @version 1.0
 */
public class Utils {
	
	public static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try 
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }
	
	
	public static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
         
        return null;
    }

	/**
	 * Starting from a Reader element (the XML) this method return a Document (an XML parsable Document).
	 * @param  is  It represent the input stream of the XML.
	 * @return Document 
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document readXml(Reader is) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		
		dbf.setValidating(false);
		dbf.setIgnoringComments(false);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setNamespaceAware(true);
		// dbf.setCoalescing(true);
		// dbf.setExpandEntityReferences(true);

		DocumentBuilder db = null;
		db = dbf.newDocumentBuilder();
		db.setEntityResolver(new NullResolver());

		// db.setErrorHandler( new MyErrorHandler());
		InputSource ips = new InputSource(is);
		return db.parse(ips);
	}
	
	
	
	/**
	 * This method remove the Nth Line of a file.
	 * It takes the String file path and the number of the row to be deleted.
	 * It modify directly the original file.
	 * @param f File path of the file
	 * @param toRemove The number of the row to be deleted
	 * @throws IOException
	 */
	public void removeNthLine(String f, int toRemove) throws IOException {
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
	
	
	
	/**
	 * This method insert a string representing a line of a file in the file at the line number provided as input.
	 * It use a temporary file to do this job that will be deleted at the end.
	 * It modify directly the original file.
	 * @param inputPathFile The path of the file in which the new lines have to be inserted
	 * @param lineno The number of the lines in which the new line have to be inserted
	 * @param lineToBeInserted The effective String that represent the line that have to be inserted
	 * @throws Exception
	 */
	public void insertStringInFile(String inputPathFile, int lineno, String lineToBeInserted) throws Exception {
		
			File inFile = new File(inputPathFile);
		    // temp file
		    File outFile = new File("/tmp/temp_file.tmp");
		     
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
	
	
	
	/**
	 * This method replace a String (toReplace) to another String (withReplace) found into the file (not in a String).
	 * It modify directly the original file.
	 * @param filePath The path file to be replace.
	 * @param toReplace The string to find into the file that have to be replaced.
	 * @param withReplace The string to replace in the place of the one found in the file.
	 * @throws IOException
	 */
	public void replaceIntoAFile(String filePath, String toReplace, String withReplace) throws IOException{
		Path path = Paths.get(filePath);
		Charset charset = StandardCharsets.UTF_8;
	
		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll(toReplace, withReplace);
		Files.write(path, content.getBytes(charset));
	}
	
	
	/**
	 * This method escape the HTML entities, used to write special caracters into HTML web pages.
	 * This is used to avoid porblems with XML tags escapes like "<", ">" and so on.
	 * @param string
	 * @return A string with no HTML entities tag.
	 */
	public String escapeHtml(String string) {
	    String escapedTxt = "";
	    char tmp = ' ';
	    for(int i = 0; i < string.length(); i++) {
	        tmp = string.charAt(i);
	        switch (tmp) {
	            case '<':
	                escapedTxt += "&lt;";
	                break;
	            case '>':
	                escapedTxt += "&gt;";
	                break;
	            case '&':
	                escapedTxt += "&amp;";
	                break;
	            case '"':
	                escapedTxt += "&quot;";
	                break;
	            case '\'':
	                escapedTxt += "&apos;";
	                break;
	            default:
	                escapedTxt += tmp;
	        }
	    }
	    return escapedTxt;
	}
	
	
	
	
	/**
	 * Convert an InputStream to String.
	 * @param is
	 * @return String
	 */
	public String convertInputStreamToString(InputStream is){
		
		String result = null;
		
		StringWriter writer = new StringWriter();
		try {
			IOUtils.copy(is, writer, "utf8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = writer.toString();
		
		return result;
	}
	
	
	
	
	public void writeInputStreamToFile(InputStream inputStream, File file){
		
		OutputStream outputStream = null;

		try {
			// write the inputStream to a FileOutputStream
			outputStream = new FileOutputStream(file);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			System.out.println("Done!");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
	
}




class NullResolver implements EntityResolver {
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		return new InputSource(new StringReader(""));
	}
}
