package eu.learnpad.transformations.launchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import eu.learnpad.transformations.model2model.ATLTransformation;
import eu.learnpad.transformations.preprocessing.Alignment;

public class ATLTransformationLauncher {
	
	private final String ADOXX_TYPE = "ADOXX";
	private final String MAGIC_DRAW_TYPE = "MD";
	private String ADOXX2XWIKI_ATL_TRANSFORMATION = "transformation/ado2xwiki.atl";
	private String MAGICDRAW2XWIKI_ATL_TRANSFORMATION = "";
	private String modelFile = "/tmp/learnpad/mt/model.xmi";
	private boolean deleteFile = false;
	
	/**
	 * Execution of the ATL Transformation with a pre-processing with alignment.
	 * This method take an XML file as InputStream and after a pre-precessing phase execute the transformation with the resulting XMI model file.
	 * @param model The InputStream of the model file to be transformed.
	 * @throws Exception
	 */
	public boolean transform(InputStream modelInputStream, String type, OutputStream out) throws Exception {
		boolean result = false;
		
		String metamodel_in = "";
		String metamodel_out = "";
		List<InputStream> modules = new ArrayList<InputStream>();
		String inTag = "";
		String outTag = "";
		
		InputStream atlStream;
		switch (type) {
		case ADOXX_TYPE:
			metamodel_in 	= "metamodels/adoxx/ado.ecore";
			metamodel_out 	= "metamodels/xwiki/XWIKI.ecore";
			atlStream = this.getClass().getClassLoader().getResourceAsStream(ADOXX2XWIKI_ATL_TRANSFORMATION);
			modules.add(atlStream);
			inTag 			= "ADOXX";
			outTag 			= "XWIKI";
			
			break;
		case MAGIC_DRAW_TYPE:
			metamodel_in 	= "";
			metamodel_out 	= "";
            atlStream = this.getClass().getClassLoader().getResourceAsStream(MAGICDRAW2XWIKI_ATL_TRANSFORMATION);
            modules.add(atlStream);
			inTag 			= "";
			outTag 			= "";
			
			break;
		default:
			System.out.println("Type not allowed!");
			break;
		}
			//if the sanitizer succeded
			
			ATLTransformation myT = new ATLTransformation();
			System.out.println("Starting ATL Model2Model transformation...");
			InputStream learnpadMetamodelInputStream = this.getClass().getClassLoader().getResourceAsStream(metamodel_in);
			InputStream xwikiMetamodelInputStream = this.getClass().getClassLoader().getResourceAsStream(metamodel_out);
			InputStream modelStream = Files.newInputStream(Paths.get(modelFile));
			myT.run(modelInputStream, learnpadMetamodelInputStream, xwikiMetamodelInputStream, modules, inTag, outTag, out);
			learnpadMetamodelInputStream.close();
			xwikiMetamodelInputStream.close();
			modelStream.close();
			for (InputStream module : modules) {
			    module.close();
			}
			result = true;
			System.out.println("ATL Model2Model transformation done.");

			if(deleteFile){
				File fileToDelete = new File(modelFile);
				fileToDelete.delete();
				System.out.println("Temp file "+modelFile+" deleted!");
			}	
			
		
		return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		
//		String model_in = "resources/model/ado4f16a6bb-9318-4908-84a7-c2d135253dc9.xml";
		String model_in = "resources/model/titolo-unico.xml";
		String file_out = "/tmp/testTransformationOutputStream.xmi";
		String type = "ADOXX";
//		String type = "MD";
		
		
		FileInputStream fis = new FileInputStream(model_in);
		OutputStream out = new FileOutputStream(file_out);	
		
		ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
		System.out.println("*******STARTING THE ATL TRANSFORMATION*******");
		atlTL.transform(fis, type, out);
		System.out.println("*******FINISHED THE ATL TRANSFORMATION*******");

	}
	
	
}
