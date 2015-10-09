package eu.learnpad.transformations.run;



import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eu.learnpad.transformations.model2model.ATLTransformation;
import eu.learnpad.transformations.model2text.generator.AcceleoStandaloneStarter;
import eu.learnpad.transformations.preprocessing.Alignment;


	public class MainTest {
		
		private String tmpModelFolder = "tmp/";
		private String tmpXwikiModelName = "xwiki_output_model.xmi";
		
		/*
		 * The main method to execute all the transformations
		 */
		public void executeTransformation(String model_in){
			
			ATLTransformation myT = null;
			
			String metamodel_in = 	"resources/metamodels/adoxx/ado.ecore";
			String metamodel_out =  "resources/metamodels/xwiki/XWIKI.ecore";
			String modules =  		"resources/transformation/ado2xwiki.atl";
			
			String inTag = 	"ADOXX";
			String outTag = "XWIKI";
			
//			String tmpXwikiModelName = "xwiki_output_model.xmi";
			String tmpModelPath = tmpModelFolder + tmpXwikiModelName;

			String resultFolderPath = "result";
			
			try {
				
				/*
				 * *******************************************************
				 * MODEL2MODEL Transformation (ATL)
				 * *******************************************************
				 */
				myT = new ATLTransformation();
				System.out.println("Starting ATL Model2Model transformation...");
				myT.run(model_in, metamodel_in, metamodel_out, modules, inTag, outTag, tmpModelPath);
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


		public String getModelSetId() {
			
			String tmpModelPath = tmpModelFolder + tmpXwikiModelName;
			
			File f = new File(tmpModelPath);
			String modelName = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\")+1); 
			modelName = modelName.substring(0, modelName.length()-4);
			return modelName;
		}
		
		
		
		
		public static void main(String[] args) throws Exception {
			/*
			 * We have to provide the XMI model to the system
			 */
			
//			String model_in = "resources/model/PATestCityApplication.xmi";
			String model_in = "resources/model/PATestCityApplication.xml";
			
			
			/*
			 * Metamodel alignment
			 */
			Alignment al = new Alignment();
			String sanitazedFilePath = al.sanitizer(model_in);
			System.out.println(sanitazedFilePath);
			
			System.out.println("Alignment Done!");
			
			/*
			 * Start the execution of the overall transformation
			 */
			MainTest mt = new MainTest();
			System.out.println("*******STARTING THE OVERALL TRANSFORMATION*******");
			mt.executeTransformation(sanitazedFilePath);
			System.out.println("*******FINISHED THE OVERALL TRANSFORMATION*******");
		}

	}

