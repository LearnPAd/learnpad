package eu.learnpad.transformations.model2text.services;

import java.io.File;

public class ModelServices {
	public String getModelSetId() {
		File f = new File("tmp/xwiki_output_model.xmi");
		String modelName = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\")+1); 
		modelName = modelName.substring(0, modelName.length()-4);
		return modelName;
	}
}
