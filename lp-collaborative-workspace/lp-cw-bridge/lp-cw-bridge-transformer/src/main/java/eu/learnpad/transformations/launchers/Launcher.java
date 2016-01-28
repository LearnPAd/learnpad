package eu.learnpad.transformations.launchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;

import eu.learnpad.transformations.model2text.generator.AcceleoStandaloneStarter;
import eu.learnpad.transformations.preprocessing.Alignment;

public class Launcher {
	
//	private String tmpAcceleoModelFolder = "result/";
//	private final String ADOXX_TYPE = "ADOXX";
//	private final String MAGIC_DRAW_TYPE = "MD";
//	private String tmpModelFolder = "tmp/";
//	private String ADOXX2XWIKI_ATL_TRANSFORMATION = "resources/transformation/ado2xwiki.atl";
//	private String MAGICDRAW2XWIKI_ATL_TRANSFORMATION = "";
	
	
	/**
	 * Alignment Launcher: starting from an XML file as InputStream it create a valid XMI model file 
	 * directly into the OutputStream provided as input. 
	 * @param model
	 * @param type
	 * @param out
	 * @return boolean
	 * @throws Exception
	 */
	public boolean align(InputStream model, String type, OutputStream out) throws Exception{
		AlignmentLauncher aL = new AlignmentLauncher();
		return aL.align(model, type, out);
	}
	
	/**
	 * Execution of the ATL Transformation with a pre-processing with alignment.
	 * This method take an XML file as InputStream and after a pre-precessing phase execute the transformation with the resulting XMI model file.
	 * @param model
	 * @param out
	 * @param type
	 * @return boolean
	 * @throws Exception
	 */
	public boolean transform(InputStream model, OutputStream out, String type) throws Exception{
		ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
		return atlTL.transform(model, type, out);
	}
	
	/**
	 * Acceleo Transformation Launcher (MODEL2CODE Transformation).
	 * @param model
	 * @return Path
	 */
	public Path write(InputStream model){
		AcceleoTransformationLauncher acceleoTL = new AcceleoTransformationLauncher();
		return acceleoTL.write(model);
	}
	
	/**
	 * Execute the chain of transformation composed by: ATL Transformation (MODEL2MODEL Transformation) and 
	 * Acceleo Transformation (MODEL2TEXT Transformation).
	 * @param model
	 * @param type
	 * @param out
	 * @return Path
	 * @throws Exception
	 */
	public Path executeChain(InputStream model, String type, OutputStream out) throws Exception{
		ChainLauncher chainL = new ChainLauncher();
		return chainL.executeChain(model, type, out);
	}
	
	
//	/**
//	 * Alignment Launcher: starting from an XML file as InputStream it create a valid XMI model file 
//	 * directly into the OutputStream provided as input. 
//	 * @param model The InputStream of the model file to be transformed.
//	 */
//	public boolean align(InputStream model, String type, OutputStream out) throws Exception{
//		
//		Alignment al = new Alignment();
//		boolean result = false;
//		
//		switch (type) {
//		case ADOXX_TYPE:
//			result = al.sanitizerForADOXX(model, out);
//			break;
//		case MAGIC_DRAW_TYPE:
//			result = al.sanitizerForMagicDraw(model, out);
//			break;
//		default:
//			System.out.println("Type not allowed!");
//			break;
//		}
//		
//		return result;
//		
//	}
	
	
	
	
//	/**
//	 * Execution of the ATL Transformation with a pre-processing with alignment.
//	 * This method take an XML file as InputStream and after a pre-precessing phase execute the transformation with the resulting XMI model file.
//	 * @param model The InputStream of the model file to be transformed.
//	 * @throws Exception
//	 */
//	public boolean transform(InputStream model, OutputStream out, String type) throws Exception{
//		
////		File sanitazedFilePath = null;
//		boolean sanitizerResult = false;
//		Alignment al = new Alignment();
//		
//		String metamodel_in = "";
//		String metamodel_out = "";
//		String modules = "";
//		String inTag = "";
//		String outTag = "";
//		
//		switch (type) {
//		case ADOXX_TYPE:
//			System.out.println("*******STARTING ADOXX ALIGNMENT*******");
//			sanitizerResult = al.sanitizerForADOXX(model, out);
//			System.out.println("*******ALIGNMENT ADOXX DONE*******");
//			
//			metamodel_in 	= "resources/metamodels/adoxx/ado.ecore";
//			metamodel_out 	= "resources/metamodels/xwiki/XWIKI.ecore";
//			modules 		= ADOXX2XWIKI_ATL_TRANSFORMATION;
//			inTag 			= "ADOXX";
//			outTag 			= "XWIKI";
//			
//			break;
//		case MAGIC_DRAW_TYPE:
//			System.out.println("*******STARTING MAGICDRAW ALIGNMENT*******");
//			sanitizerResult = al.sanitizerForMagicDraw(model, out);
//			System.out.println("*******ALIGNMENT MAGICDRAW DONE*******");
//			
//			metamodel_in 	= "";
//			metamodel_out 	= "";
//			modules 		= MAGICDRAW2XWIKI_ATL_TRANSFORMATION;
//			inTag 			= "";
//			outTag 			= "";
//			
//			break;
//		default:
//			System.out.println("Type not allowed!");
//			break;
//		}
//		
//		if(sanitizerResult){
//			//if the sanitizer succeded
//			
//			
//			
//			
//			
//			
//			
//			
//			
//			
//		}else{
//			System.out.println("Sanitizer failed!");
//		}
//		
//		String basenameInputModel = FilenameUtils.getBaseName(sanitazedFilePath.getAbsolutePath());
//		String tmpXwikiModelName = basenameInputModel + ".xmi";
//		String tmpModelPath = tmpModelFolder + tmpXwikiModelName; //	tmp/xwiki_output_model.xmi
//
//		ATLTransformation myT = new ATLTransformation();
//		System.out.println("Starting ATL Model2Model transformation...");
//		myT.run(sanitazedFilePath.getAbsolutePath(), metamodel_in, metamodel_out, modules, inTag, outTag, tmpModelPath);
//		System.out.println("ATL Model2Model transformation done. Temporary XWIKI model named: "+tmpXwikiModelName+" created in /tmp folder.");
//		
//		File resultFile = new File(tmpModelPath);
//		if(resultFile.exists()){
//			System.out.println("File Exists!");
//		}
//		
//		return null;
//	}
//	
//	
	
	
	
//	/**
//	 * Acceleo Transformation Launcher (MODEL2CODE Transformation).
//	 * @param model The InputStream of the model file to be transformed.
//	 */
//	public boolean write(InputStream model, OutputStream out){
//
//		String resultFolderPath = tmpAcceleoModelFolder + "test"; 
//		
//		System.out.println("Starting Acceleo Model2Text transformation...");
//		AcceleoStandaloneStarter ast = new AcceleoStandaloneStarter();
//		ast.execute(model, resultFolderPath);
//		System.out.println("Acceleo Model2Text done. You can find the result in the /result folder.");
//		
//		return null;
//	}
//	
//	
	
	
	
	
//	/**
//	 * Execute the chain of transformation composed by: ATL Transformation (MODEL2MODEL Transformation) and 
//	 * Acceleo Transformation (MODEL2TEXT Transformation).
//	 * @param model_in The path of the model file to be transformed.
//	 * @throws Exception 
//	 * @throws IOException
//	 */
//	public boolean executeChain(InputStream model, String type, OutputStream out) throws Exception{
//		
//		/*
//		 * The producer streams data to an OutputStream while the consumer consumes it through its InputStream. 
//		 * They can be connected to each other by passing one end of the pipe to each class.
//		 */
//		int BUFFER = 2048;
//		 
//		PipedInputStream inputStreamPipe = new PipedInputStream(BUFFER);
//		PipedOutputStream outputStreamPipe = new PipedOutputStream(inputStreamPipe);
//		
//		
//		
//		
//		ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
//		outputStreamPipe = (PipedOutputStream) atlTL.transform(model, type);
////		OutputStream atlResult = atlTL.transform(model, type);
//		
//		
////		FileInputStream fis = new FileInputStream(tmpInputStreamFilePath);
//		
//		AcceleoTransformationLauncher acceleoTL = new AcceleoTransformationLauncher();
//		Path acceleoResultPath = acceleoTL.write(inputStreamPipe);
//		
//		return acceleoResultPath;
//		
//	}
	
	public static void main(String[] args) throws Exception {
		
		String model_in = "resources/model/ado4f16a6bb-9318-4908-84a7-c2d135253dc9.xml";
		String file_out = "tmp/testAlignmentOutputStream.xmi";
		String type = "ADOXX";
//		String type = "MD";
		
		// create a new output stream
        OutputStream out = new FileOutputStream(file_out);		

        
        Launcher launcher = new Launcher();
		FileInputStream fis = new FileInputStream(model_in);
		launcher.align(fis, type, out);
	
	}

}
