package it.univaq.disim.transformation.test;

import it.univaq.disim.transformation.controller.ATLTransformation;

import java.io.IOException;


	public class MainTest {

		public static void main(String[] args) {
			
			ATLTransformation myT = null;
			
//			String model_in = "resources/model/ClassModel.xmi";
//			String metamodel_in = "resources/metamodels/ClassMM.ecore";
//			String metamodel_out =  "resources/metamodels/RelationalMM.ecore";
//			String modules =  "resources/transformation/Class2Relational.atl";
//			
//			String inTag = "Class";
//			String outTag = "Relational";

			
			String model_in = "resources/model/bp1_simpleModelByXSLT.xmi";
			String metamodel_in = "resources/metamodels/bpmn/MyModel.ecore";
			String metamodel_out =  "resources/metamodels/xwiki/XWIKI.ecore";
			String modules =  "resources/transformation/bpmn2xwiki.atl";
			
			String inTag = "BPMN2";
			String outTag = "XWIKI";
			
			String outPath = "resources/modello_output_xwiki.xmi";
			
			try {
				myT = new ATLTransformation();
				
				myT.run(model_in, metamodel_in, metamodel_out, modules, inTag, outTag, outPath);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

	}

