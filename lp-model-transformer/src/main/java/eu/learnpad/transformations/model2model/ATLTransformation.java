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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
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
import org.eclipse.m2m.atl.engine.compiler.AtlStandaloneCompiler;
import org.eclipse.m2m.atl.engine.compiler.atl2006.Atl2006Compiler;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;

/**
 * Class used to execute the ATL Transformation
 * 
 * @author Francesco Basciani, Jean Simard
 * @version 1.0
 */
public class ATLTransformation
{
    private Map<String, Object> options;

    /**
     * The constructor initialize some options of the transformation. Here there is the registration of the Metamodel
     * Ecore.
     * 
     * @throws IOException
     */
    public ATLTransformation() throws IOException
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
     * @param lpModelStream Path of the file representing the Ecore model (in .XMI) to be transformed.
     * @param lpMetamodelStream Path of the Ecore Metamodel in which the model in input is conform to.
     * @param xwikiMetamodelStream The Path of the file for the Ecore Metamodel that the resulting model have to be
     *            conform.
     * @param atlPaths The path of the effective ATL transformation (the .ATL file).
     * @param inTag The tag of the input Metamodel
     * @param outTag The tag of the input Parameter Metamodel
     * @param out The path of the Ecore model file resulting from the transformation.
     * @return
     */
    public IModel run(InputStream lpModelStream, InputStream lpMetamodelStream, InputStream xwikiMetamodelStream,
        List<InputStream> atlStreams, String inTag, String outTag, OutputStream out)
    {
        ILauncher launcher = new EMFVMLauncher();
        ModelFactory modelFactory = new EMFModelFactory();
        IInjector injector = new EMFInjector();
        IExtractor extractor = new EMFExtractor();

        try {
            IReferenceModel lpMetamodel = modelFactory.newReferenceModel();
            injector.inject(lpMetamodel, lpMetamodelStream, this.options);
            IReferenceModel xwikiMetamodel = modelFactory.newReferenceModel();
            injector.inject(xwikiMetamodel, xwikiMetamodelStream, this.options);

            IModel lpModel = modelFactory.newModel(lpMetamodel);
            injector.inject(lpModel, lpModelStream, this.options);
            IModel xwikiModel = modelFactory.newModel(xwikiMetamodel);

            launcher.initialize(this.options);
            launcher.addInModel(lpModel, "IN", inTag);
            launcher.addOutModel(xwikiModel, "OUT", outTag);
            Object[] asm = (Object[]) this.compile(atlStreams).toArray();
            launcher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), this.options, asm);

            extractor.extract(xwikiModel, out, null);

            return xwikiModel;
        } catch (ATLCoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private List<InputStream> compile(List<InputStream> atlStreams)
    {
        List<InputStream> asmStreams = new ArrayList<InputStream>();
        for (InputStream atlStream : atlStreams) {
            InputStream asmStream = this.compile(atlStream);
            if (asmStream != null) {
                asmStreams.add(asmStream);
            }
        }
        return asmStreams;
    }

    public InputStream compile(InputStream atlStream)
    {
        AtlStandaloneCompiler compiler = new Atl2006Compiler();
        String asmFilename = String.format("%s.asm", UUID.randomUUID().toString());
        java.nio.file.Path asmPath = Paths.get("/tmp/learnpad/mt", asmFilename);
        compiler.compile(atlStream, asmPath.toString());
        try {
            return Files.newInputStream(asmPath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
