package eu.learnpad.transformations.model2model;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.m2m.atl.common.ATLExecutionException;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.compiler.atl2006.Atl2006Compiler;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;


public class ATLTransformation {
	private IModel inModel;
	private IModel outModel;

	private IReferenceModel inmodelMetamodel;
	private IReferenceModel outmodelMetamodel;

	private String modules; //This is the file .atl of the transformation

	private String inTag;
	private String outTag;

	private Map<String, Object> options;

	public ATLTransformation() throws IOException {
		options = new HashMap<String, Object>();
		options.put("supportUML2Stereotypes", "false");
		options.put("printExecutionTime", "true");
		options.put("OPTION_CONTENT_TYPE", "false");
		options.put("allowInterModelReferences", "false");
		options.put("step", "false");
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

	}
	
	
	
	/**
	 * The function is the only one to be visible to the outside and is the one that initiates the whole process of transformation.
	 * @param String model_in, String metamodel_in, String metamodel_out, String modules, String inTag, String outTag, String outPath
	 * @return IModel
	 */
	public IModel run(String model_in, String metamodel_in, String metamodel_out, String modules, String inTag, String outTag, String outPath) {
		
		try {
			set(model_in, metamodel_in, metamodel_out, modules, inTag, outTag);
			//Execute transformation
			doTransformation(new NullProgressMonitor());
			//Save the model created in a file that has the name passed in input with outPath
			IExtractor extractor = new EMFExtractor();
			extractor.extract(outModel, outPath);
			
//			//We create an object file with the path entered by the user, or are we going to take the template you just created and saved
//			File myTemp = new File(outPath);
//			//Take the contents of the file (ie the model) to expose it as JSON
//			String myContent = FileUtils.readFileToString(myTemp);
			
			System.out.println("Transformation executed!");
			
			
		} catch (ATLCoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ATLExecutionException e) {
			e.printStackTrace();
		}
				
		
		return this.outModel;
	}
	

	/**
	 * The function takes all the parameters that are passed in input and puts them in the variables of the class types using their ATL.
	 * @param model_in
	 * @param metamodel_in
	 * @param metamodel_out
	 * @param modules
	 * @param inTag
	 * @param outTag
	 * @throws ATLCoreException
	 */
	private void set(String model_in, String metamodel_in, String metamodel_out, String modules, String inTag, String outTag) throws ATLCoreException {
			ModelFactory factory = new EMFModelFactory();
			IInjector injector = new EMFInjector();
			this.inmodelMetamodel = factory.newReferenceModel();
			injector.inject(this.inmodelMetamodel,  metamodel_in);
			this.outmodelMetamodel = factory.newReferenceModel();
			injector.inject(this.outmodelMetamodel,  metamodel_out);
			this.outModel = factory.newModel(this.outmodelMetamodel);
			this.inModel = factory.newModel(this.inmodelMetamodel);
			injector.inject(this.inModel,  model_in);
			this.modules = modules;
			this.inTag = inTag;
			this.outTag = outTag;
	}

	/**
	 * Get simple function that returns any options you set by the manufacturer at the time of the class definition
	 * @return Map<String, Object> options
	 */
	private Map<String, Object> getOptions() {
		return options;
	}

	/**
	 * The function performs the actual transformation using class variables already septate method set.
	 * @param monitor
	 * @return Object
	 * @throws ATLCoreException
	 * @throws IOException
	 * @throws ATLExecutionException
	 */
	private Object doTransformation(IProgressMonitor monitor)
			throws ATLCoreException, IOException, ATLExecutionException {
		ILauncher launcher = new EMFVMLauncher();
		List<InputStream> inputStreamsToClose = new ArrayList<InputStream>();
		Map<String, Object> launcherOptions = getOptions();
		launcher.initialize(launcherOptions);
		launcher.addInModel(this.inModel, "IN", this.inTag);
		launcher.addOutModel(this.outModel, "OUT", this.outTag);
		InputStream[] modulesStreams = getModulesList();
		inputStreamsToClose.addAll(Arrays.asList(modulesStreams));
		Object result = launcher.launch("run", monitor, launcherOptions,
				(Object[]) modulesStreams);
		for (InputStream inputStream : inputStreamsToClose) {
			inputStream.close();
		}
		return result;
	}

	/**
	 * The function returns the modules that are present in the file processing. Each transform file (.atl) may contain
	 * more modules within it (each has within the mapping that makes the transformation). Each of these modules to
	 * its interior will have a variable IN and an OUT eg. Each of these modules is then saved in a separate file .asm.
	 * @return InputStream[]
	 * @throws IOException
	 */
	private InputStream[] getModulesList() throws IOException  {
		InputStream[] modules = null;
		String[] moduleNames = this.modules.split(",");
		modules = new InputStream[moduleNames.length];
		for (int i = 0; i < moduleNames.length; i++) {
			
			String asmModulePath = new Path(moduleNames[i].trim()).removeFileExtension().addFileExtension("asm").toString();
			System.out.println(asmModulePath);

			Atl2006Compiler compiler = new Atl2006Compiler();
			compiler.compile(new FileInputStream(new File(moduleNames[i])), asmModulePath);
			
			modules[i] = new FileInputStream(asmModulePath);
		}
		return modules;
	}


}