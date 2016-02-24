/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.transformations.model2model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.compiler.atl2006.Atl2006Compiler;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;

/**
 * Class used to execute the ATL Transformation
 * 
 * @author Jean Simard
 * @version 1.0
 */
public class ATLTransformation2
{

    // private IModel paramsModel;
    private IModel outModel;

    private Map<String, Object> options;

    /**
     * The constructor inizialize some options of the transformation. Here there is the registration of the Metamodel
     * Ecore.
     * 
     * @throws IOException
     */
    public ATLTransformation2() throws IOException
    {
        options = new HashMap<String, Object>();
        options.put("supportUML2Stereotypes", "false");
        options.put("printExecutionTime", "true");
        options.put("OPTION_CONTENT_TYPE", "false");
        options.put("allowInterModelReferences", "false");
        options.put("step", "false");
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    }

    /**
     * The function is the only one to be visible to the outside and is the one that initiates the whole process of
     * transformation.
     * 
     * @param lpModelPath Path of the file representing the Ecore model (in .XMI) to be transformed.
     * @param lpMetamodelPath Path of the Ecore Metamodel in which the model in input is conform to.
     * @param xwikiMetamodelPath The Path of the file for the Ecore Metamodel that the resulting model have to be
     *            conform.
     * @param atlPaths The path of the effective ATL transformation (the .ATL file).
     * @param inTag The tag of the input Metamodel
     * @param outTag The tag of the input Parameter Metamodel
     * @param out The path of the Ecore model file resulting from the transformation.
     * @return
     */
    public IModel run(String lpModelPath, String lpMetamodelPath, String xwikiMetamodelPath, String atlPaths,
        String inTag, String outTag, OutputStream out)
    {
        ILauncher launcher = new EMFVMLauncher();
        ModelFactory modelFactory = new EMFModelFactory();
        IInjector injector = new EMFInjector();
        IExtractor extractor = new EMFExtractor();

        try {
            IReferenceModel lpMetamodel = modelFactory.newReferenceModel();
            injector.inject(lpMetamodel, lpMetamodelPath);
            IReferenceModel xwikiMetamodel = modelFactory.newReferenceModel();
            injector.inject(xwikiMetamodel, xwikiMetamodelPath);

            IModel lpModel = modelFactory.newModel(lpMetamodel);
            injector.inject(lpModel, lpModelPath);
            IModel xwikiModel = modelFactory.newModel(xwikiMetamodel);

            launcher.initialize(this.options);
            launcher.addInModel(lpModel, "IN", inTag);
            launcher.addOutModel(xwikiModel, "OUT", outTag);
            launcher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), this.options,
                (Object[]) getModulesList(atlPaths));

            extractor.extract(xwikiModel, out, null);
            
            EMFModelFactory emfModelFactory = (EMFModelFactory) modelFactory;
            emfModelFactory.unload((EMFModel) lpModel);
            emfModelFactory.unload((EMFModel) xwikiModel);
            emfModelFactory.unload((EMFModel) lpMetamodel);
            emfModelFactory.unload((EMFModel) xwikiModel);
        } catch (ATLCoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return this.outModel;
    }

    /**
     * The function returns the modules that are present in the file processing. Each transform file (.atl) may contain
     * more modules within it (each has within the mapping that makes the transformation). Each of these modules to its
     * interior will have a variable IN and an OUT eg. Each of these modules is then saved in a separate file .asm.
     * 
     * @return InputStream[]
     * @throws IOException
     */
    private InputStream[] getModulesList(String atlPaths) throws IOException
    {
        InputStream[] modulesList = null;
        String[] moduleNames = atlPaths.split(",");
        modulesList = new InputStream[moduleNames.length];
        for (int i = 0; i < moduleNames.length; i++) {

            String asmModulePath =
                new Path(moduleNames[i].trim()).removeFileExtension().addFileExtension("asm").toString();
            System.out.println(asmModulePath);

            Atl2006Compiler compiler = new Atl2006Compiler();
            compiler.compile(new FileInputStream(new File(moduleNames[i])), asmModulePath);

            modulesList[i] = new FileInputStream(asmModulePath);
        }
        return modulesList;
    }

}
