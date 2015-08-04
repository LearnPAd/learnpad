/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Simple XSLT based model transformator.
 *
 * @author sandro.emmenegger
 */
public class SimpleModelTransformator {

    private final String type;
    
    static{
        System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
    }
    
    public SimpleModelTransformator(String type) {
        this.type = type;
    }

    public File transform(String modelSetId, byte[] model) {

        TransformerFactory tFactory = TransformerFactory.newInstance();
        File latestOutFile = null;

        try {
            InputStream xsltIn = getClass().getResourceAsStream(APP.CONF.getString("import.transformation.xslt.path") + "adoxx2ontology.xsl");
            Transformer transformer = tFactory.newTransformer(new StreamSource(xsltIn));

            File previousVersionOfOutFile = getPreviousVersionOfOutputFile(modelSetId);
            latestOutFile = createNewVersionOfOutputFile(modelSetId);
            Result result = new StreamResult(latestOutFile);
            transformer.transform(new StreamSource(new ByteArrayInputStream(model)), result);

            //Compare previous file version with new generated version and remove the new version if no changes are recognized.
            if (filesEqual(previousVersionOfOutFile, latestOutFile)) {
                File parent = latestOutFile.getParentFile();
                latestOutFile.delete();
                parent.delete();
                latestOutFile = previousVersionOfOutFile;
            }

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SimpleModelTransformator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException | IOException ex) {
            Logger.getLogger(SimpleModelTransformator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return latestOutFile;
    }

    /**
     * Returns a new empty output file. Follows a folder structure with
     * incremental version numbers. Ex.
     * ..../<modelsetId>/<IncrementalNumber>/<modelsetId>.ttl
     *
     * @param modelSetId
     * @return
     * @throws java.io.IOException
     */
    public File createNewVersionOfOutputFile(String modelSetId) throws IOException {

        Path modelSetFolderPath = Paths.get(APP.CONF.getString("ontology.learnpad.model.instances"), modelSetId, this.type);
        File modelSetFolder = modelSetFolderPath.toFile();
        if (!modelSetFolder.exists()) {
            Files.createDirectories(modelSetFolder.toPath());
        }

        Integer latestVersionNumber = getLatestVersionNumber(modelSetFolder);
        latestVersionNumber = latestVersionNumber == null ? 1 : latestVersionNumber++;
        Path versionPath = Paths.get(modelSetFolder.getPath(), latestVersionNumber.toString());
        Files.createDirectories(versionPath);
        String filename = modelSetId + APP.CONF.getString("ontology.learnpad.model.instances.filetype");
        File outputFile = new File(versionPath.toString(), filename);

        return outputFile;

    }

    /**
     * Searchs the highest version number in a set of number folders expected in a given parent folder.
     * 
     * @param modelSetFolder
     * @return
     * @throws NumberFormatException 
     */
    private Integer getLatestVersionNumber(File modelSetFolder) throws NumberFormatException {
        Integer latestVersionNumber = null;
        for (File versionDir : modelSetFolder.listFiles()) {
            Integer v = Integer.valueOf(versionDir.getName());
            if (latestVersionNumber == null || latestVersionNumber < v) {
                latestVersionNumber = v;
            }
        }
        return latestVersionNumber;
    }

    /**
     * Search for previous version of generated/transformed ontology instances file 
     * of a given model set.
     * 
     * @param modelSetId
     * @return
     * @throws IOException 
     */
    private File getPreviousVersionOfOutputFile(String modelSetId) throws IOException {
        Path modelSetPath = Paths.get(APP.CONF.getString("ontology.learnpad.model.instances"), modelSetId, this.type);
        if (!Files.isDirectory(modelSetPath)) {
            return null;
        }

        Integer latestVersionNumber = getLatestVersionNumber(modelSetPath.toFile());
        if(latestVersionNumber == null || latestVersionNumber < 2){
            return null;
        }
        latestVersionNumber--;
        
        Path versionPath = Paths.get(modelSetPath.toString(), latestVersionNumber.toString());
        String filename = modelSetId + APP.CONF.getString("ontology.learnpad.model.instances.filetype");
        File previousVersionOutputFile = new File(versionPath.toString(), filename);
        return previousVersionOutputFile;
    }

    /**
     * Compare files content.
     * 
     * @param previousVersionOfOutFile
     * @param latestOutFile
     * @return 
     */
    private boolean filesEqual(File previousVersionOfOutFile, File latestOutFile) throws IOException {
        if(previousVersionOfOutFile == null || latestOutFile == null){
            return false;
        }
        byte[] f1 = Files.readAllBytes(previousVersionOfOutFile.toPath());
        byte[] f2 = Files.readAllBytes(latestOutFile.toPath());
        return Arrays.equals(f1, f2);
    }
    

}
