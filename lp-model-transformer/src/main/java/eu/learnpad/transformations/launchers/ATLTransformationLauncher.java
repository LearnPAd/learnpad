package eu.learnpad.transformations.launchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import eu.learnpad.transformations.model2model.ATLTransformation;
import eu.learnpad.transformations.model2model.ATLTransformation2;
import eu.learnpad.transformations.preprocessing.Alignment;

public class ATLTransformationLauncher {
	
	private final String ADOXX_TYPE = "ADOXX";
	private final String MAGIC_DRAW_TYPE = "MD";
	private String ADOXX2XWIKI_ATL_TRANSFORMATION = "transformation/ado2xwiki.atl";
	private String TMP_ADOXX_ECORE = "/tmp/learnpad/mt/adoxx.ecore";
    private String TMP_XWIKI_ECORE = "/tmp/learnpad/mt/xwiki.ecore";
    private String TMP_ATL_TRANSFORMATION = "/tmp/learnpad/mt/transformation.atl";
	private String MAGICDRAW2XWIKI_ATL_TRANSFORMATION = "";
	private String modelFile = "/tmp/learnpad/mt/model.xmi";
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
		List<InputStream> modules = new ArrayList<InputStream>();
		String inTag = "";
		String outTag = "";

		OutputStream outputAlignementFile = new FileOutputStream(modelFile);
		
		InputStream atlStream;
		switch (type) {
		case ADOXX_TYPE:
			System.out.println("*******STARTING ADOXX ALIGNMENT*******");
			sanitizerResult = al.sanitizerForADOXX(modelInputStream, outputAlignementFile);
			outputAlignementFile.close();
			System.out.println("*******ALIGNMENT ADOXX DONE*******");
			
			metamodel_in 	= "metamodels/adoxx/ado.ecore";
			metamodel_out 	= "metamodels/xwiki/XWIKI.ecore";
			atlStream = this.getClass().getClassLoader().getResourceAsStream(ADOXX2XWIKI_ATL_TRANSFORMATION);
			modules.add(atlStream);
			inTag 			= "ADOXX";
			outTag 			= "XWIKI";
			
			break;
		case MAGIC_DRAW_TYPE:
			System.out.println("*******STARTING MAGICDRAW ALIGNMENT*******");
			sanitizerResult = al.sanitizerForMagicDraw(modelInputStream, outputAlignementFile);
            outputAlignementFile.close();
			System.out.println("*******ALIGNMENT MAGICDRAW DONE*******");
			
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
				
		if(sanitizerResult){
			//if the sanitizer succeded
			
			ATLTransformation2 myT = new ATLTransformation2();
			System.out.println("Starting ATL Model2Model transformation...");
			InputStream learnpadMetamodelInputStream = this.getClass().getClassLoader().getResourceAsStream(metamodel_in);
			InputStream xwikiMetamodelInputStream = this.getClass().getClassLoader().getResourceAsStream(metamodel_out);
			OutputStream learnpadMetamodelOutputStream = new FileOutputStream(TMP_ADOXX_ECORE);
			OutputStream xwikiMetamodelOutputStream = new FileOutputStream(TMP_XWIKI_ECORE);
			IOUtils.copy(learnpadMetamodelInputStream, learnpadMetamodelOutputStream);
			IOUtils.copy(xwikiMetamodelInputStream, xwikiMetamodelOutputStream);
			learnpadMetamodelInputStream.close();
			learnpadMetamodelOutputStream.close();
			xwikiMetamodelInputStream.close();
			xwikiMetamodelOutputStream.close();
			myT.run(modelFile, TMP_ADOXX_ECORE, TMP_XWIKI_ECORE, modules, inTag, outTag, out);
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
