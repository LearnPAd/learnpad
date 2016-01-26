package eu.learnpad.transformations.launchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import eu.learnpad.transformations.model2model.ATLTransformation;
import eu.learnpad.transformations.preprocessing.Alignment;
import eu.learnpad.transformations.preprocessing.Utils;

public class ATLTransformationLauncher {
	
	private final String ADOXX_TYPE = "ADOXX";
	private final String MAGIC_DRAW_TYPE = "MD";
	private String ADOXX2XWIKI_ATL_TRANSFORMATION = "resources/transformation/ado2xwiki.atl";
	private String MAGICDRAW2XWIKI_ATL_TRANSFORMATION = "";
	private String tmpAlignmentOutputXMIFile = "tmp/tmpAlignmentOutputXMIFile.xmi";
//	private String tmpModelInputFilePath = "tmp/tmpModelInputFile.xmi";
	private boolean deleteFile = false;
	
	
	
	
	/**
	 * Execution of the ATL Transformation with a pre-processing with alignment.
	 * This method take an XML file as InputStream and after a pre-precessing phase execute the transformation with the resulting XMI model file.
	 * @param model The InputStream of the model file to be transformed.
	 * @throws Exception
	 */
	public boolean transform(InputStream model, String type, OutputStream out) throws Exception{
		
		Utils utils = new Utils();
		
		
		
		
		boolean result = false;
		
		boolean sanitizerResult = false;
		Alignment al = new Alignment();
		
		String metamodel_in = "";
		String metamodel_out = "";
		String modules = "";
		String inTag = "";
		String outTag = "";
		
		
		//posso usare PipedOutpuStream dataPipe come InputStream ora
//		int BUFFER = 8192;
//		PipedInputStream convertPipe = new PipedInputStream(BUFFER);
//		PipedOutputStream dataPipe = new PipedOutputStream(convertPipe);
		
		
//		//temp output stream to use in sanitizer
//		String tmpSanitizerFilePath = tmpModelFolder + "tmpSanitizerOutputStream.xmi";
//		// create a new output stream
//        OutputStream midOutput = new FileOutputStream(tmpSanitizerFilePath);
        
		//Inizialize the outputstream with the tmp file path
		//This OutputStrem will be used to take the sanitize file.
		OutputStream outputAlignementFile = new FileOutputStream(tmpAlignmentOutputXMIFile);
		
		
		switch (type) {
		case ADOXX_TYPE:
			System.out.println("*******STARTING ADOXX ALIGNMENT*******");
			sanitizerResult = al.sanitizerForADOXX(model, outputAlignementFile);
			System.out.println("*******ALIGNMENT ADOXX DONE*******");
			
			metamodel_in 	= "resources/metamodels/adoxx/ado.ecore";
			metamodel_out 	= "resources/metamodels/xwiki/XWIKI.ecore";
			modules 		= ADOXX2XWIKI_ATL_TRANSFORMATION;
			inTag 			= "ADOXX";
			outTag 			= "XWIKI";
			
			break;
		case MAGIC_DRAW_TYPE:
			System.out.println("*******STARTING MAGICDRAW ALIGNMENT*******");
			sanitizerResult = al.sanitizerForMagicDraw(model, outputAlignementFile);
			System.out.println("*******ALIGNMENT MAGICDRAW DONE*******");
			
			metamodel_in 	= "";
			metamodel_out 	= "";
			modules 		= MAGICDRAW2XWIKI_ATL_TRANSFORMATION;
			inTag 			= "";
			outTag 			= "";
			
			break;
		default:
			System.out.println("Type not allowed!");
			break;
		}
		
				
		if(sanitizerResult){
			//if the sanitizer succeded
			
//			String basenameInputModel = FilenameUtils.getBaseName(sanitazedFilePath.getAbsolutePath());
//			String tmpXwikiModelName = basenameInputModel + ".xmi";
//			String tmpModelPath = tmpModelFolder + tmpXwikiModelName; //	tmp/xwiki_output_model.xmi
			
			
			
			
			//posso usare PipedOutpuStream dataPipe come InputStream ora
//			int BUFFER = 2048;
//			PipedInputStream convertPipe = new PipedInputStream(BUFFER);
//			PipedOutputStream dataPipe = new PipedOutputStream(convertPipe);
			
//			DataSource dataSource = new DataSource(dataPipe);
//			DataConsumer dataConsumer = new DataConsumer(convertPipe);
			
			
			//Create a file starting from the inputStream
//			File file = new File(tmpAlignmentOutputXMIFile);
//			utils.writeInputStreamToFile(model, file);
			
			
			
			
			//In the tmpAlignmentOutputXMIFile we have aligned file. We use this for the transformation
//			FileInputStream alignedFile = new FileInputStream(tmpAlignmentOutputXMIFile);
//			String model_in = utils.convertInputStreamToString(alignedFile);
			
			
			ATLTransformation myT = new ATLTransformation();
			System.out.println("Starting ATL Model2Model transformation...");
			myT.run(tmpAlignmentOutputXMIFile, metamodel_in, metamodel_out, modules, inTag, outTag, out);
			System.out.println("ATL Model2Model transformation done.");
			
			
			

			if(deleteFile){
				File fileToDelete = new File(tmpAlignmentOutputXMIFile);
				fileToDelete.delete();
				System.out.println("Temp file "+tmpAlignmentOutputXMIFile+" deleted!");
//				file.delete();
//				System.out.println("Temp file "+tmpAlignmentOutputXMIFile+" deleted!");
			}
			
			
		}else{
			System.out.println("Sanitizer failed!");
		}
		
		return result;
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
//		String model_in = "resources/model/ado4f16a6bb-9318-4908-84a7-c2d135253dc9.xml";
		String model_in = "resources/model/titolo-unico.xml";
		String file_out = "tmp/testTransformationOutputStream.xmi";
		String type = "ADOXX";
//		String type = "MD";
		
		
		FileInputStream fis = new FileInputStream(model_in);
		OutputStream out = new FileOutputStream(file_out);	
		
		ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
		System.out.println("*******STARTING THE ATL TRANSFORMATION*******");
		atlTL.transform(fis, type, out);
		System.out.println("*******FINISHED THE ATL TRANSFORMATION*******");

	}
	
	
}
