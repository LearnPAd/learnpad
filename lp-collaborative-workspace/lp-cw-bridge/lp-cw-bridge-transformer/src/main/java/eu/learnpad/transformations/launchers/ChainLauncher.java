package eu.learnpad.transformations.launchers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;


/**
 * Class that execute the chain of transformation composed by: ATL Transformation (MODEL2MODEL Transformation) and 
 * Acceleo Transformation (MODEL2TEXT Transformation).
 * @author Basciani Francesco
 * @version 1.0
 *
 */
public class ChainLauncher {
	
	private String tmpAlignmentOutputXMIFile = "tmp/tmpAlignmentOutputXMIFile.xmi";		
	
	
	/**
	 * Execute the chain of transformation composed by: ATL Transformation (MODEL2MODEL Transformation) and 
	 * Acceleo Transformation (MODEL2TEXT Transformation).
	 * @param model_in The path of the model file to be transformed.
	 * @throws Exception 
	 * @throws IOException
	 */
	public Path executeChain(InputStream model, String type, OutputStream out) throws Exception{
		
		Path acceleoResultPath = null;
		
		boolean atlResult = false;
		ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
		atlResult = atlTL.transform(model, type, out);
		
		if(atlResult){
			//If the transformation succeded
			
			//Take the result of ATL Transformation as File in order to pass it to the Acceleo T.
			FileInputStream fis = new FileInputStream(tmpAlignmentOutputXMIFile);
			AcceleoTransformationLauncher acceleoTL = new AcceleoTransformationLauncher();
			acceleoResultPath = acceleoTL.write(fis);
		}else{
			System.out.println("ATL Transformation failed!");
		}
		
		
		return acceleoResultPath;
		
	}
	


	public static void main(String[] args) throws Exception {
		
//		String model_in = "resources/model/ado4f16a6bb-9318-4908-84a7-c2d135253dc9.xml";
		String model_in = "resources/model/titolo-unico.xml";
		String file_out = "tmp/testTransformationOutputStream.xmi";
		String type = "ADOXX";
//		String type = "MD";
		
		FileInputStream fis = new FileInputStream(model_in);
		OutputStream out = new FileOutputStream(file_out);	
		
		
		System.out.println("*******STARTING THE OVERALL TRANSFORMATION*******");
		ChainLauncher mt = new ChainLauncher();
		Path resultPath = mt.executeChain(fis, type, out);
		System.out.println("*******FINISHED THE OVERALL TRANSFORMATION*******");
	}

}
