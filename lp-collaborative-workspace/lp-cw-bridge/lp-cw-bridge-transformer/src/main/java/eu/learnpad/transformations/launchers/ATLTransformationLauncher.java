package eu.learnpad.transformations.launchers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import eu.learnpad.transformations.model2model.ATLTransformation;
import eu.learnpad.transformations.preprocessing.Alignment;

public class ATLTransformationLauncher {
	
	
	private String tmpModelFolder = "tmp/";
	
	/*
	 * *******************************************************
	 * MODEL2MODEL Transformation (ATL)
	 * *******************************************************
	 */
	public void execute(String model_in, String model_params) throws IOException{
		
		File f = new File(model_in);
		if(f.exists() && !f.isDirectory()) { 
			
			ATLTransformation myT = null;
			
			String metamodel_in 	= "resources/metamodels/adoxx/ado.ecore";
			String metamodel_param 	= "resources/metamodels/Parameter/Parameter.ecore";
			String metamodel_out 	= "resources/metamodels/xwiki/XWIKI.ecore";
			String modules 			= "resources/transformation/ado2xwiki.atl";
			String inTag 			= "ADOXX";
			String outTag 			= "XWIKI";
			String paramsTag 		= "Parameter";
			
			String basenameInputModel = FilenameUtils.getBaseName(model_in);
			
			String tmpXwikiModelName = basenameInputModel + ".xmi";
			
			String tmpModelPath = tmpModelFolder + tmpXwikiModelName; //	tmp/xwiki_output_model.xmi

			myT = new ATLTransformation();
			System.out.println("Starting ATL Model2Model transformation...");
			myT.run(model_in, metamodel_in, model_params, metamodel_param, metamodel_out, modules, inTag, paramsTag, outTag, tmpModelPath);
			System.out.println("ATL Model2Model transformation done. Temporary XWIKI model named: "+tmpXwikiModelName+" created in /tmp folder.");
				
		    
		}else{
			System.out.println("File "+model_in+" provided as input doesn't exist!");
		}
		
		
	}
	
	
	public void executeWithAlignment(String model_in, String model_params) throws Exception{
		
		File f = new File(model_in);
		if(f.exists() && !f.isDirectory()) { 
			
			/*
			 * ADOXX XML file alignment
			 */
			System.out.println("*******STARTING ALIGNMENT*******");
			Alignment al = new Alignment();
			String sanitazedFilePath = al.sanitizer(model_in);
			System.out.println("*******ALIGNMENT DONE*******");
			
			
			
			
			ATLTransformation myT = null;
			
			String metamodel_in 	= "resources/metamodels/adoxx/ado.ecore";
			String metamodel_param 	= "resources/metamodels/Parameter/Parameter.ecore";
			String metamodel_out 	= "resources/metamodels/xwiki/XWIKI.ecore";
			String modules 			= "resources/transformation/ado2xwiki.atl";
			String inTag 			= "ADOXX";
			String outTag 			= "XWIKI";
			String paramsTag 		= "Parameter";
			
			String basenameInputModel = FilenameUtils.getBaseName(sanitazedFilePath);
			
			String tmpXwikiModelName = basenameInputModel + ".xmi";
			
			String tmpModelPath = tmpModelFolder + tmpXwikiModelName; //	tmp/xwiki_output_model.xmi

			myT = new ATLTransformation();
			System.out.println("Starting ATL Model2Model transformation...");
			myT.run(sanitazedFilePath, metamodel_in, model_params, metamodel_param, metamodel_out, modules, inTag, paramsTag, outTag, tmpModelPath);
			System.out.println("ATL Model2Model transformation done. Temporary XWIKI model named: "+tmpXwikiModelName+" created in /tmp folder.");
				
		}else{
			System.out.println("File "+model_in+" provided as input doesn't exist!");
		}
			
		
		
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		String model_in = "resources/model/TitoloUnico.xml"; //For testing alignment
//		String model_in = "resources/model/TitoloUnico.xmi"; // For testing without alignment
		//If alignment is needed remember to take the right xml as input
		boolean alignment = true;
		String model_params = "JuriTest";
		
		
		
		System.out.println("*******STARTING THE ATL TRANSFORMATION*******");
		
		/*
		 * Start the execution of the overall transformation
		 */
		ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
		if(alignment){
			atlTL.executeWithAlignment(model_in, model_params);
		}else{
			atlTL.execute(model_in, model_params);
		}
		
		System.out.println("*******FINISHED THE ATL TRANSFORMATION*******");

	}
	
	
}
