/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.transformation;

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

import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;
import javax.xml.transform.Source;
import javax.xml.transform.URIResolver;
import net.sf.saxon.s9api.XdmAtomicValue;

/**
 * Simple XSLT based model transformator.
 *
 * @author sandro.emmenegger
 */
public final class SimpleModelTransformator {

    private static final SimpleModelTransformator instance = new SimpleModelTransformator();
    private String latestModelSetId;

    private SimpleModelTransformator() {
        System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
        loadTestmodel();
    }

    private void loadTestmodel() {
        //For testing purposes a test modelset is loaded !
        InputStream testModelSetFile = new ByteArrayInputStream(FileOntAO.getInstance().getModelSetFile(APP.CONF.getString("testdata.model.file.path")));
        transform(APP.CONF.getString("testdata.modelset.version"), testModelSetFile, ModelSetType.ADOXX);
    }

    public static SimpleModelTransformator getInstance() {
        return instance;
    }

    public File transform(String modelSetId, InputStream model, ModelSetType type) {

        TransformerFactory tFactory = TransformerFactory.newInstance();
        tFactory.setURIResolver(new XsltURIResolver());
        File latestOutFile = null;

        try {
            InputStream xsltIn = getClass().getResourceAsStream(APP.CONF.getString("import.transformation.xslt.path") + type.name().toLowerCase() + "2ontology.xsl");
            Transformer transformer = tFactory.newTransformer(new StreamSource(xsltIn));
            transformer.setParameter("modelSetVersion", new XdmAtomicValue(modelSetId));

            File previousVersionOfOutFile = getPreviousVersionOfOutputFile(modelSetId);
            latestOutFile = createNewVersionOfOutputFile(modelSetId);
            Result result = new StreamResult(latestOutFile);
            transformer.transform(new StreamSource(model), result);

            //Compare previous file version with new generated version and remove the new version if no changes are recognized.
//            if (filesEqual(previousVersionOfOutFile, latestOutFile)) {
//                File parent = latestOutFile.getParentFile();
//                latestOutFile.delete();
//                parent.delete();
//                latestOutFile = previousVersionOfOutFile;
//            }
            latestModelSetId = modelSetId;

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SimpleModelTransformator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException | IOException ex) {
            Logger.getLogger(SimpleModelTransformator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return latestOutFile;
    }

    public String getLatestModelSetId() {
        return latestModelSetId;
    }

    public void setLatestModelSetId(String id) {
        latestModelSetId = id;
    }

    public File getLatestVersionFile(String modelSetId) {
        Path modelSetFolderPath = getModelSetFolderPath(modelSetId);
        Integer latestVersionNumber = getLatestVersionNumber(modelSetFolderPath);
        if (!modelSetFolderPath.toFile().exists() || latestVersionNumber == null) {
            return null;
        }
        String filename = modelSetId + APP.CONF.getString("ontology.learnpad.model.instances.filetype");
        Path latestVersionFile = modelSetFolderPath.resolve(Paths.get(latestVersionNumber.toString(), filename));
        if (!latestVersionFile.toFile().exists()) {
            return null;
        }
        return latestVersionFile.toFile();
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
    private File createNewVersionOfOutputFile(String modelSetId) throws IOException {

        Path modelSetFolderPath = getModelSetFolderPath(modelSetId);
        if (!modelSetFolderPath.toFile().exists()) {
            Files.createDirectories(modelSetFolderPath);
        }

        Integer latestVersionNumber = getLatestVersionNumber(modelSetFolderPath);
        latestVersionNumber = latestVersionNumber == null ? 1 : latestVersionNumber++;
        Path versionPath = Paths.get(modelSetFolderPath.toString(), latestVersionNumber.toString());
        Files.createDirectories(versionPath);
        String filename = modelSetId + APP.CONF.getString("ontology.learnpad.model.instances.filetype");
        File outputFile = new File(versionPath.toString(), filename);

        return outputFile;

    }

    public Path getModelSetFolderPath(String modelSetId) {
        Path relativeModelSetPath = Paths.get(APP.CONF.getString("ontology.learnpad.model.instances"), modelSetId);
        Path modelSetFolderPath;
        Path workingDirectory = getWorkingDirectory(APP.CONF.getString("working.directory"), System.getProperty("user.dir"));
        if (workingDirectory != null) {
            modelSetFolderPath = workingDirectory.resolve(relativeModelSetPath);
        } else {
            modelSetFolderPath = relativeModelSetPath;
        }
        return modelSetFolderPath;
    }

    private Path getWorkingDirectory(String... directoryPaths) {
        for (String directoryPathString : directoryPaths) {
            if (directoryPathString != null) {
                Path directoryPath = Paths.get(directoryPathString);
                File workingDirectory = directoryPath.toFile();
                if (workingDirectory.exists() && workingDirectory.canWrite()) {
                    return directoryPath;
                }
            }
        }
        return null;
    }

    /**
     * Searchs the highest version number in a set of number folders expected in
     * a given parent folder.
     *
     * @param modelSetFolder
     * @return
     * @throws NumberFormatException
     */
    private Integer getLatestVersionNumber(Path modelSetFolderPath) throws NumberFormatException {
        if (!modelSetFolderPath.toFile().exists()) {
            return null;
        }
        Integer latestVersionNumber = null;
        for (File versionDir : modelSetFolderPath.toFile().listFiles()) {
            Integer v = Integer.valueOf(versionDir.getName());
            if (latestVersionNumber == null || latestVersionNumber < v) {
                latestVersionNumber = v;
            }
        }
        return latestVersionNumber;
    }

    /**
     * Search for previous version of generated/transformed ontology instances
     * file of a given model set.
     *
     * @param modelSetId
     * @return
     * @throws IOException
     */
    private File getPreviousVersionOfOutputFile(String modelSetId) throws IOException {
        Path modelSetPath = getModelSetFolderPath(modelSetId);
        if (!Files.isDirectory(modelSetPath)) {
            return null;
        }

        Integer latestVersionNumber = getLatestVersionNumber(modelSetPath);
        if (latestVersionNumber == null || latestVersionNumber < 2) {
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
        if (previousVersionOfOutFile == null || latestOutFile == null) {
            return false;
        }
        byte[] f1 = Files.readAllBytes(previousVersionOfOutFile.toPath());
        byte[] f2 = Files.readAllBytes(latestOutFile.toPath());
        return Arrays.equals(f1, f2);
    }

}

class XsltURIResolver implements URIResolver {

    @Override
    public Source resolve(String href, String base) throws TransformerException {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(href);
            return new StreamSource(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
