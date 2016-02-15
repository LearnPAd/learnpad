package eu.learnpad.transformations.launchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import eu.learnpad.transformations.model2model.ATLTransformation;
import eu.learnpad.transformations.preprocessing.Alignment;

public class ATLTransformationLauncher {
	
	private final String ADOXX_TYPE = "ADOXX";
	private final String MAGIC_DRAW_TYPE = "MD";
	private String ADOXX2XWIKI_ATL_TRANSFORMATION = "transformation/ado2xwiki.atl";
	private String MAGICDRAW2XWIKI_ATL_TRANSFORMATION = "";
	private String tmpAlignmentOutputXMIFile = "/tmp/tmpAlignmentOutputXMIFile.xmi";
	private boolean deleteFile = false;
	
	
	
	
	/**
	 * Execution of the ATL Transformation with a pre-processing with alignment.
	 * This method take an XML file as InputStream and after a pre-precessing phase execute the transformation with the resulting XMI model file.
	 * @param model The InputStream of the model file to be transformed.
	 * @throws Exception
	 */
	public boolean transform(InputStream modelInputStream, String type, OutputStream out) throws Exception{
		
		boolean result = false;
		
		boolean sanitizerResult = false;
		Alignment al = new Alignment();
		
		String metamodel_in = "";
		String metamodel_out = "";
		String modules = "";
		String inTag = "";
		String outTag = "";

		OutputStream outputAlignementFile = new FileOutputStream(tmpAlignmentOutputXMIFile);
		
		switch (type) {
		case ADOXX_TYPE:
			System.out.println("*******STARTING ADOXX ALIGNMENT*******");
			sanitizerResult = al.sanitizerForADOXX(modelInputStream, outputAlignementFile);
			System.out.println("*******ALIGNMENT ADOXX DONE*******");
			
			metamodel_in 	= "metamodels/adoxx/ado.ecore";
			metamodel_out 	= "metamodels/xwiki/XWIKI.ecore";
			modules 		= ADOXX2XWIKI_ATL_TRANSFORMATION;
			inTag 			= "ADOXX";
			outTag 			= "XWIKI";
			
			break;
		case MAGIC_DRAW_TYPE:
			System.out.println("*******STARTING MAGICDRAW ALIGNMENT*******");
			sanitizerResult = al.sanitizerForMagicDraw(modelInputStream, outputAlignementFile);
			System.out.println("*******ALIGNMENT MAGICDRAW DONE*******");
			
			metamodel_in 	= "";
			metamodel_out 	= "";
			modules 		= MAGICDRAW2XWIKI_ATL_TRANSFORMATION;
			inTag 			= "";
			outTag 			= "";
			
			break;
		default:
			System.out.println("Type not allowed!");
			break;
		}
				
		if(sanitizerResult){
			//if the sanitizer succeded
			
			ATLTransformation myT = new ATLTransformation();
			System.out.println("Starting ATL Model2Model transformation...");
			InputStream learnpadMetamodelInputStream = this.getClass().getClassLoader().getResourceAsStream(metamodel_in);
			InputStream xwikiMetamodelInputStream = this.getClass().getClassLoader().getResourceAsStream(metamodel_out);
			InputStream transformationInputStream = this.getClass().getClassLoader().getResourceAsStream(modules);
			OutputStream learnpadMetamodelOutputStream = new FileOutputStream("/tmp/adoxx.ecore");
			OutputStream xwikiMetamodelOutputStream = new FileOutputStream("/tmp/xwiki.ecore");
			OutputStream transformationOutputStream = new FileOutputStream("/tmp/transformation.atl");
			IOUtils.copy(learnpadMetamodelInputStream, learnpadMetamodelOutputStream);
			IOUtils.copy(xwikiMetamodelInputStream, xwikiMetamodelOutputStream);
			IOUtils.copy(transformationInputStream, transformationOutputStream);
			learnpadMetamodelInputStream.close();
			learnpadMetamodelOutputStream.close();
			transformationInputStream.close();
			xwikiMetamodelInputStream.close();
			xwikiMetamodelOutputStream.close();
			transformationOutputStream.close();
			myT.run(tmpAlignmentOutputXMIFile, "/tmp/adoxx.ecore", "/tmp/xwiki.ecore", "/tmp/transformation.atl", inTag, outTag, out);
			result = true;
			System.out.println("ATL Model2Model transformation done.");

			if(deleteFile){
				File fileToDelete = new File(tmpAlignmentOutputXMIFile);
				fileToDelete.delete();
				System.out.println("Temp file "+tmpAlignmentOutputXMIFile+" deleted!");
			}
			
			
			
		}else{
			System.out.println("Sanitizer failed!");
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
