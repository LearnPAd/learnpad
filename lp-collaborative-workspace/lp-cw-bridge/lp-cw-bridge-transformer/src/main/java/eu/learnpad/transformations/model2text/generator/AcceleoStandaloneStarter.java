package eu.learnpad.transformations.model2text.generator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.common.IAcceleoConstants;
import org.eclipse.acceleo.model.mtl.MtlPackage;
import org.eclipse.acceleo.model.mtl.resource.EMtlResourceFactoryImpl;
import org.eclipse.acceleo.parser.compiler.AcceleoCompiler;
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

import eu.learnpad.transformations.model2text.main.WebHome;


public class AcceleoStandaloneStarter{
	
	
	public AcceleoStandaloneStarter(){
		
	}
	
	   public void execute(String modelPath, String resultFolderPath) {
		   
//	     registerResourceFactories();
	     registerPackages();
//	     registerLibraries();
	    
         URI modelURI = URI.createFileURI(modelPath);
         File folder = new File(resultFolderPath);
         List<String> arguments = new ArrayList<String>();
         WebHome generator;
			try {
				generator = new WebHome(modelURI, folder, arguments);
				generator.doGenerate(new BasicMonitor());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	 
	   public void registerResourceFactories() {
	     Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	     Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(IAcceleoConstants.EMTL_FILE_EXTENSION, new EMtlResourceFactoryImpl());
	   }
	 
	   public void registerPackages() {
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
	       	    EPackage p = (EPackage)eObject;
//       	    rs.getPackageRegistry().put(p.getNsURI(), p);
	       	    EPackage.Registry.INSTANCE.put(p.getNsURI(), p);
	       	}
		   
	   }
	 
	   public void registerLibraries() {
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
