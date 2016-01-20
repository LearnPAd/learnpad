package eu.learnpad.transformations.launchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import eu.learnpad.transformations.preprocessing.Alignment;

/**
 * Class to do the alignment phase. Starting from an XML file it create a valid XMI model file. 
 * @author Basciani Francesco
 *
 */
public class AlignmentLauncher {
	
	private final String ADOXX_TYPE = "ADOXX";
	private final String MAGIC_DRAW_TYPE = "MD";
	
	
	/**
	 * Alignment Launcher: starting from an XML file it create a valid XMI model file. 
	 * @param model_in The path of the model file to be transformed.
	 */
//	public void execute(String model_in) throws Exception{
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
//			System.out.println("Aligned file: "+ sanitazedFilePath);
//			System.out.println("*******ALIGNMENT DONE*******");
//			
//		}else{
//			System.out.println("File "+model_in+" provided as input doesn't exist!");
//		}
//		
//	}
	
	
	public OutputStream  align(InputStream model, String type) throws Exception{
		
		Alignment al = new Alignment();
		File result = null;
		
		switch (type) {
		case ADOXX_TYPE:
			result = al.sanitizerForADOXX(model);
			break;
		case MAGIC_DRAW_TYPE:
			result = al.sanitizerForMagicDraw(model);
			break;
		default:
			System.out.println("Type not allowed!");
			break;
		}
		
		return null;
		
	}
	
	

	public static void main(String[] args) throws Exception {
		
		String model_in = "resources/model/ado4f16a6bb-9318-4908-84a7-c2d135253dc9.xml";
		String type = "ADOXX";
//		String type = "MD";
		
		AlignmentLauncher align = new AlignmentLauncher();
//		align.execute(model_in);
		
		FileInputStream fis = new FileInputStream(model_in);
		align.align(fis, type);

	}

}