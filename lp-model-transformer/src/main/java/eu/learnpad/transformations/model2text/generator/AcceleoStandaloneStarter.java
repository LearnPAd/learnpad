package eu.learnpad.transformations.model2text.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.eclipse.acceleo.common.IAcceleoConstants;
import org.eclipse.acceleo.model.mtl.MtlPackage;
import org.eclipse.acceleo.model.mtl.resource.EMtlResourceFactoryImpl;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;
import eu.learnpad.transformations.model2text.main.Generate;

/**
 * Class for the execution of Acceleo transformation in a standalone environment.
 * 
 * @author Basciani Francesco
 * @version 1.0
 */
public class AcceleoStandaloneStarter
{
    private Path modelXWikiAcceleoTmpPath = Paths.get("/tmp/learnpad/mt/model.xwiki.acceleo-tmp.xmi");

    public AcceleoStandaloneStarter()
    {
    }

    /**
     * Method that actually performs the Acceleo transformation.
     * 
     * @param modelPath The path of the model to be transformed.
     * @param resultFolderPath The path of the folder in which the result of the transformation will be located.
     */
    public void execute(InputStream model, Path resultFolderPath)
    {
        try {
            Files.copy(model, modelXWikiAcceleoTmpPath);

            URI modelURI = URI.createFileURI(modelXWikiAcceleoTmpPath.toString());
            List<String> arguments = new ArrayList<String>();
            Generate generator;
            
            generator = new Generate(modelURI, resultFolderPath.toFile(), arguments);
            generator.doGenerate(new BasicMonitor());
            
            // Have to unregister them because if not, they stay loaded and are messing up with ATL module
            // They are automatically loaded by Acceleo in the Generate class
            EPackage.Registry.INSTANCE.remove(XwikiPackage.eNS_URI);
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().remove("*");

            Files.delete(modelXWikiAcceleoTmpPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * The method is used to register globally the Ecore Resource Factory to the ".ecore" extension
     */
    public void registerResourceFactories()
    {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(IAcceleoConstants.EMTL_FILE_EXTENSION,
            new EMtlResourceFactoryImpl());
    }

    /**
     * The method is used to register globally packages.
     */
    public void registerPackages()
    {
        String metamodelPath = "resources/metamodels/xwiki/XWIKI.ecore";
        URI metamodelURI = URI.createFileURI(metamodelPath);

        // register globally the Ecore Resource Factory to the ".ecore" extension
        // weird that we need to do this, but well...
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();
        // enable extended metadata
        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(metamodelURI, true);
        EObject eObject = r.getContents().get(0);
        if (eObject instanceof EPackage) {
            EPackage p = (EPackage) eObject;
            EPackage.Registry.INSTANCE.put(p.getNsURI(), p);
        }

    }

    /**
     * The method is used to register globally libraries.
     */
    public void registerLibraries()
    {
        CodeSource acceleoModel = MtlPackage.class.getProtectionDomain().getCodeSource();
        if (acceleoModel != null) {
            String libraryLocation = acceleoModel.getLocation().toString();
            if (libraryLocation.endsWith(".jar")) {
                libraryLocation = "jar:" + libraryLocation + '!';
            }

            URIConverter.URI_MAP.put(URI.createURI("http://www.eclipse.org/acceleo/mtl/3.0/mtlstdlib.ecore"),
                URI.createURI(libraryLocation + "/model/mtlstdlib.ecore"));
            URIConverter.URI_MAP.put(URI.createURI("http://www.eclipse.org/acceleo/mtl/3.0/mtlnonstdlib.ecore"),
                URI.createURI(libraryLocation + "/model/mtlnonstdlib.ecore"));
        } else {
            System.err.println("Coudln't retrieve location of plugin 'org.eclipse.acceleo.model'.");
        }
    }
}
