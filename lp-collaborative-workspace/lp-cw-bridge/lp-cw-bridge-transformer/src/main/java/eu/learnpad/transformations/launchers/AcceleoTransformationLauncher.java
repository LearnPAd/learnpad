package eu.learnpad.transformations.launchers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

import eu.learnpad.transformations.model2text.generator.AcceleoStandaloneStarter;



public class AcceleoTransformationLauncher {
	
	private String tmpModelFolder = "result/";
	
	
	
	
	/**
	 * Acceleo Transformation Launcher (MODEL2CODE Transformation).
	 * @param model The InputStream of the model file to be transformed.
	 */
	public Path write(InputStream model){

//		String basenameInputModel = FilenameUtils.getBaseName(model.);
		String resultFolderPath = tmpModelFolder + "test"; 

		
		System.out.println("Starting Acceleo Model2Text transformation...");
		AcceleoStandaloneStarter ast = new AcceleoStandaloneStarter();
		ast.execute(model, resultFolderPath);
		System.out.println("Acceleo Model2Text done. You can find the result in the /result folder.");
		
		return null;
	}
	
	
	

	public static void main(String[] args) throws FileNotFoundException {
		
		/*
		 * Acceleo take as input a model (XMI file)
		 */
		String model_in = "resources/model/titolo-unico.xwiki.xmi";
		FileInputStream fis = new FileInputStream(model_in);
		
		
		
		System.out.println("*******STARTING THE ACCELEO TRANSFORMATION*******");
		AcceleoTransformationLauncher acceleoTL = new AcceleoTransformationLauncher();
		acceleoTL.write(fis);
		System.out.println("*******FINISHED THE ACCELEO TRANSFORMATION*******");
		
		
	}

}
