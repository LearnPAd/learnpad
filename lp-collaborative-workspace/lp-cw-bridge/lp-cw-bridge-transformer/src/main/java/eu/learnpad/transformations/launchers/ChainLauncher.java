package eu.learnpad.transformations.launchers;

import java.io.FileInputStream;
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
	
	//TODO eliminare. NON mi serve. il path lo devo prendere dall'output dell'ATL T.
	private String tmpInputStreamFilePath = "tmp/acceleoInputStreamFile.xmi";
		
	private String tmpModelFolder = "tmp/";
	
	
	/**
	 * Execute the chain of transformation composed by: ATL Transformation (MODEL2MODEL Transformation) and 
	 * Acceleo Transformation (MODEL2TEXT Transformation).
	 * @param model_in The path of the model file to be tranformed.
	 * @throws Exception 
	 * @throws IOException
	 */
	public Path executeChain(InputStream model, String type) throws Exception{
		
		ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
		OutputStream atlResult = atlTL.transform(model, type);
		
		
		FileInputStream fis = new FileInputStream(tmpInputStreamFilePath);
		
		AcceleoTransformationLauncher acceleoTL = new AcceleoTransformationLauncher();
		Path acceleoResultPath = acceleoTL.write(fis);
		
		return acceleoResultPath;
		
	}
	
//	/**
//	 * Execute the chain of transformation composed by: ATL Transformation (MODEL2MODEL Transformation) and 
//	 * Acceleo Transformation (MODEL2TEXT Transformation).
//	 * @param model_in The path of the model file to be tranformed.
//	 * @throws IOException
//	 */
//	public void executeTransformation(String model_in){
//		
//		ATLTransformation myT = null;
//		
//		String metamodel_in 	= "resources/metamodels/adoxx/ado.ecore";
//		String metamodel_out 	= "resources/metamodels/xwiki/XWIKI.ecore";
//		String modules 			= "resources/transformation/ado2xwiki.atl";
//		String inTag 			= "ADOXX";
//		String outTag 			= "XWIKI";
//		
//		String basenameInputModel = FilenameUtils.getBaseName(model_in);
//		
//		String tmpXwikiModelName = basenameInputModel + ".xmi";
//		
//		String tmpModelPath = tmpModelFolder + tmpXwikiModelName; //	tmp/xwiki_output_model.xmi
//
//		String resultFolderPath = "result/";
//		
//		try {
//			
//			/*
//			 * *******************************************************
//			 * MODEL2MODEL Transformation (ATL)
//			 * *******************************************************
//			 */
//			myT = new ATLTransformation();
//			System.out.println("Starting ATL Model2Model transformation...");
//			myT.run(model_in, metamodel_in, metamodel_out, modules, inTag, outTag, tmpModelPath);
//			System.out.println("ATL Model2Model transformation done. Temporary XWIKI model named: "+tmpXwikiModelName+" created in /tmp folder.");
//			
//			
//			/*
//			 * *******************************************************
//			 * MODEL2CODE Transformation (Acceleo)
//			 * *******************************************************
//			 */
//			System.out.println("Starting Acceleo Model2Text transformation...");
//			AcceleoStandaloneStarter ast = new AcceleoStandaloneStarter();
//			ast.execute(tmpModelPath, resultFolderPath);
//			System.out.println("Acceleo Model2Text done. You can find the result in the /result folder.");
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}


	public static void main(String[] args) throws Exception {
		
		String model_in = "resources/model/ado4f16a6bb-9318-4908-84a7-c2d135253dc9.xml";
		String type = "ADOXX";
		FileInputStream fis = new FileInputStream(model_in);
		
		
		
		System.out.println("*******STARTING THE OVERALL TRANSFORMATION*******");
		ChainLauncher mt = new ChainLauncher();
		Path resultPath = mt.executeChain(fis, type);
		System.out.println("*******FINISHED THE OVERALL TRANSFORMATION*******");
	}

}
