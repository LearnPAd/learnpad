package eu.learnpad.transformations.launchers;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import eu.learnpad.transformations.model2text.generator.AcceleoStandaloneStarter;



public class AcceleoTransformationLauncher {
	
//	private String tmpModelFolder = "tmp/";
	
	
	/**
	 * Acceleo Transformation Launcher (MODEL2CODE Transformation).
	 * @param model_in The path of the model file to be tranformed.
	 */
	public void execute(String model_in){
		/*
		 * *******************************************************
		 * MODEL2CODE Transformation (Acceleo)
		 * *******************************************************
		 */
		File f = new File(model_in);
		if(f.exists() && !f.isDirectory()) { 
			
			String basenameInputModel = FilenameUtils.getBaseName(model_in);
			String resultFolderPath = "result/xwiki/" + basenameInputModel; 

			
			System.out.println("Starting Acceleo Model2Text transformation...");
			AcceleoStandaloneStarter ast = new AcceleoStandaloneStarter();
			ast.execute(model_in, resultFolderPath);
			System.out.println("Acceleo Model2Text done. You can find the result in the /result folder.");
		}else{
			System.out.println("File "+model_in+" provided as input doesn't exist!");
		}
	
	}
	
	
	
	

	public static void main(String[] args) {
		
		/*
		 * Acceleo take as input a model (XMI file)
		 */
		String model_in = "resources/model/titolo-unico.xwiki.xmi";
		
		System.out.println("*******STARTING THE ACCELEO TRANSFORMATION*******");
		AcceleoTransformationLauncher acceleoTL = new AcceleoTransformationLauncher();
		acceleoTL.execute(model_in);
		System.out.println("*******FINISHED THE ACCELEO TRANSFORMATION*******");
		
		
	}

}
