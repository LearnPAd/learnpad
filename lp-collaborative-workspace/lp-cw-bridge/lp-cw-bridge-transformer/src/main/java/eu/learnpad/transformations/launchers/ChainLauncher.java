package eu.learnpad.transformations.launchers;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import eu.learnpad.transformations.model2model.ATLTransformation;
import eu.learnpad.transformations.model2text.generator.AcceleoStandaloneStarter;
import eu.learnpad.transformations.preprocessing.Alignment;



public class ChainLauncher {

		
	private String tmpModelFolder = "tmp/";
	
	/*
	 * The main method to execute all the transformations
	 */
	public void executeTransformation(String model_in, String model_params){
		
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

		String resultFolderPath = "result/xwiki/" + basenameInputModel; 
		
		try {
			
			/*
			 * *******************************************************
			 * MODEL2MODEL Transformation (ATL)
			 * *******************************************************
			 */
			myT = new ATLTransformation();
			System.out.println("Starting ATL Model2Model transformation...");
			myT.run(model_in, metamodel_in, model_params, metamodel_param, metamodel_out, modules, inTag, paramsTag, outTag, tmpModelPath);
			System.out.println("ATL Model2Model transformation done. Temporary XWIKI model named: "+tmpXwikiModelName+" created in /tmp folder.");
			
			
			/*
			 * *******************************************************
			 * MODEL2CODE Transformation (Acceleo)
			 * *******************************************************
			 */
			System.out.println("Starting Acceleo Model2Text transformation...");
			AcceleoStandaloneStarter ast = new AcceleoStandaloneStarter();
			ast.execute(tmpModelPath, resultFolderPath);
			System.out.println("Acceleo Model2Text done. You can find the result in the /result folder.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static void main(String[] args) throws Exception {

		
		String model_in = "resources/model/TitoloUnico.xml";
		String model_params = "JuriTest";
		
		
		
		System.out.println("*******STARTING THE OVERALL TRANSFORMATION*******");
		/*
		 * ADOXX XML file alignment
		 */
		Alignment al = new Alignment();
		String sanitazedFilePath = al.sanitizer(model_in);
		System.out.println("Alignment Done!");
		
		/*
		 * Start the execution of the overall transformation
		 */
		ChainLauncher mt = new ChainLauncher();
		mt.executeTransformation(sanitazedFilePath, model_params);
		
		System.out.println("*******FINISHED THE OVERALL TRANSFORMATION*******");
	}

}
