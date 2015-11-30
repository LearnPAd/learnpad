package eu.learnpad.transformations.launchers;

import java.io.File;

import eu.learnpad.transformations.preprocessing.Alignment;

/**
 * Class to do the alignment phase. Starting from an XML file it create a valid XMI model file. 
 * @author Basciani Francesco
 *
 */
public class AlignmentLauncher {
	
	/**
	 * Alignment Launcher: starting from an XML file it create a valid XMI model file. 
	 * @param model_in The path of the model file to be tranformed.
	 */
	public void execute(String model_in) throws Exception{
		
		File f = new File(model_in);
		if(f.exists() && !f.isDirectory()) { 
			
			/*
			 * ADOXX XML file alignment
			 */
			System.out.println("*******STARTING ALIGNMENT*******");
			Alignment al = new Alignment();
			String sanitazedFilePath = al.sanitizerForADOXX(model_in);
			System.out.println("Aligned file: "+ sanitazedFilePath);
			System.out.println("*******ALIGNMENT DONE*******");
			
		}else{
			System.out.println("File "+model_in+" provided as input doesn't exist!");
		}
		
	}
	
	

	public static void main(String[] args) throws Exception {
		
		String model_in = "resources/model/titolo-unico.xml"; //For testing alignment
		
		AlignmentLauncher align = new AlignmentLauncher();
		align.execute(model_in);

	}

}
