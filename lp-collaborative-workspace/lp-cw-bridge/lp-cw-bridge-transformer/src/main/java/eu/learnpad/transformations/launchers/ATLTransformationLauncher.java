package eu.learnpad.transformations.launchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;

import eu.learnpad.transformations.model2model.ATLTransformation;
import eu.learnpad.transformations.preprocessing.Alignment;

public class ATLTransformationLauncher {
	
	private String tmpModelFolder = "tmp/";
	private final String ADOXX_TYPE = "ADOXX";
	private final String MAGIC_DRAW_TYPE = "MD";
	private String ADOXX2XWIKI_ATL_TRANSFORMATION = "resources/transformation/ado2xwiki.atl";
	private String MAGICDRAW2XWIKI_ATL_TRANSFORMATION = "";
	
	
	
	
	/**
	 * Execution of the ATL Transformation with a pre-processing with alignment.
	 * This method take an XML file as InputStream and after a pre-precessing phase execute the transformation with the resulting XMI model file.
	 * @param model The InputStream of the model file to be transformed.
	 * @throws Exception
	 */
	public OutputStream  transform(InputStream model, String type) throws Exception{
		
		File sanitazedFilePath = null;
		Alignment al = new Alignment();
		
		String metamodel_in = "";
		String metamodel_out = "";
		String modules = "";
		String inTag = "";
		String outTag = "";
		
		switch (type) {
		case ADOXX_TYPE:
			System.out.println("*******STARTING ADOXX ALIGNMENT*******");
			sanitazedFilePath = al.sanitizerForADOXX(model);
			System.out.println("*******ALIGNMENT ADOXX DONE*******");
			
			metamodel_in 	= "resources/metamodels/adoxx/ado.ecore";
			metamodel_out 	= "resources/metamodels/xwiki/XWIKI.ecore";
			modules 		= ADOXX2XWIKI_ATL_TRANSFORMATION;
			inTag 			= "ADOXX";
			outTag 			= "XWIKI";
			
			break;
		case MAGIC_DRAW_TYPE:
			System.out.println("*******STARTING MAGICDRAW ALIGNMENT*******");
			sanitazedFilePath = al.sanitizerForMagicDraw(model);
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
		
				
		
		
		
		
		
		String basenameInputModel = FilenameUtils.getBaseName(sanitazedFilePath.getAbsolutePath());
		
		String tmpXwikiModelName = basenameInputModel + ".xmi";
		
		String tmpModelPath = tmpModelFolder + tmpXwikiModelName; //	tmp/xwiki_output_model.xmi

		ATLTransformation myT = new ATLTransformation();
		System.out.println("Starting ATL Model2Model transformation...");
		myT.run(sanitazedFilePath.getAbsolutePath(), metamodel_in, metamodel_out, modules, inTag, outTag, tmpModelPath);
		System.out.println("ATL Model2Model transformation done. Temporary XWIKI model named: "+tmpXwikiModelName+" created in /tmp folder.");
		
		
		
		File resultFile = new File(tmpModelPath);
		if(resultFile.exists()){
			System.out.println("File Exists!");
		}
		
		return null;
		
	}
	
	
	
	
	
	
//	/**
//	 * ATL Transformation Launcher (MODEL2MODEL Transformation).
//	 * @param model_in The path of the model file to be tranformed.
//	 * @throws IOException
//	 */
//	public void execute(String model_in) throws IOException{
//		/*
//		 * *******************************************************
//		 * MODEL2MODEL Transformation (ATL)
//		 * *******************************************************
//		 */
//		
//		File f = new File(model_in);
//		if(f.exists() && !f.isDirectory()) { 
//			
//			ATLTransformation myT = null;
//			
//			String metamodel_in 	= "resources/metamodels/adoxx/ado.ecore";
//			String metamodel_out 	= "resources/metamodels/xwiki/XWIKI.ecore";
//			String modules 			= "resources/transformation/ado2xwiki.atl";
//			String inTag 			= "ADOXX";
//			String outTag 			= "XWIKI";
//			
//			String basenameInputModel = FilenameUtils.getBaseName(model_in);
//			
//			String tmpXwikiModelName = basenameInputModel + ".xmi";
//			
//			String tmpModelPath = tmpModelFolder + tmpXwikiModelName; //	tmp/xwiki_output_model.xmi
//
//			myT = new ATLTransformation();
//			System.out.println("Starting ATL Model2Model transformation...");
//			myT.run(model_in, metamodel_in, metamodel_out, modules, inTag, outTag, tmpModelPath);
//			System.out.println("ATL Model2Model transformation done. Temporary XWIKI model named: "+tmpXwikiModelName+" created in /tmp folder.");
//				
//		    
//		}else{
//			System.out.println("File "+model_in+" provided as input doesn't exist!");
//		}
//		
//		
//	}
//	
//	/**
//	 * Execution of the ATL Transformation with a pre-processing with alignement.
//	 * This method take an XML file instead of XMI and after a pre-precessing phase execute the transformation with the resulting XMI model file.
//	 * @param model_in The path of the model file to be tranformed.
//	 * @throws Exception
//	 */
//	public void executeWithAlignment(String model_in) throws Exception{
//		
//		File f = new File(model_in);
//		if(f.exists() && !f.isDirectory()) { 
//			
//			/*
//			 * ADOXX XML file alignment
//			 */
//			System.out.println("*******STARTING ALIGNMENT*******");
//			Alignment al = new Alignment();
//			String sanitazedFilePath = al.sanitizerForADOXX(model_in);
//			System.out.println("*******ALIGNMENT DONE*******");
//			
//			
//			ATLTransformation myT = null;
//			
//			String metamodel_in 	= "resources/metamodels/adoxx/ado.ecore";
//			String metamodel_out 	= "resources/metamodels/xwiki/XWIKI.ecore";
//			String modules 			= "resources/transformation/ado2xwiki.atl";
//			String inTag 			= "ADOXX";
//			String outTag 			= "XWIKI";
//			
//			String basenameInputModel = FilenameUtils.getBaseName(sanitazedFilePath);
//			
//			String tmpXwikiModelName = basenameInputModel + ".xmi";
//			
//			String tmpModelPath = tmpModelFolder + tmpXwikiModelName; //	tmp/xwiki_output_model.xmi
//
//			myT = new ATLTransformation();
//			System.out.println("Starting ATL Model2Model transformation...");
//			myT.run(sanitazedFilePath, metamodel_in, metamodel_out, modules, inTag, outTag, tmpModelPath);
//			System.out.println("ATL Model2Model transformation done. Temporary XWIKI model named: "+tmpXwikiModelName+" created in /tmp folder.");
//				
//		}else{
//			System.out.println("File "+model_in+" provided as input doesn't exist!");
//		}
//			
//		
//		
//	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		String model_in = "resources/model/ado4f16a6bb-9318-4908-84a7-c2d135253dc9.xml";
		String type = "ADOXX";
//		String type = "MD";
		
		
		FileInputStream fis = new FileInputStream(model_in);
		
		
		ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
		System.out.println("*******STARTING THE ATL TRANSFORMATION*******");
		atlTL.transform(fis, type);
		System.out.println("*******FINISHED THE ATL TRANSFORMATION*******");

	}
	
	
}
