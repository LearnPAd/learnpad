package eu.learnpad.transformations.launchers;

import java.io.File;

import eu.learnpad.transformations.preprocessing.Alignment;

public class AlignmentLauncher {
	
	public void execute(String model_in) throws Exception{
		
		File f = new File(model_in);
		if(f.exists() && !f.isDirectory()) { 
			
			/*
			 * ADOXX XML file alignment
			 */
			System.out.println("*******STARTING ALIGNMENT*******");
			Alignment al = new Alignment();
			String sanitazedFilePath = al.sanitizer(model_in);
			System.out.println("Aligned file: "+ sanitazedFilePath);
			System.out.println("*******ALIGNMENT DONE*******");
			
		}else{
			System.out.println("File "+model_in+" provided as input doesn't exist!");
		}
		
	}
	
	

	public static void main(String[] args) throws Exception {
		
		String model_in = "resources/model/TitoloUnico.xml"; //For testing alignment
		
		
		AlignmentLauncher align = new AlignmentLauncher();
		align.execute(model_in);

	}

}
