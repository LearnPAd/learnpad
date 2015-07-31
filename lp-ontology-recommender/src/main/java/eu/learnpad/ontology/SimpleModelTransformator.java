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

    private String type;

    public SimpleModelTransformator(String type) {
        System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
    }

    public File transform(String modelSetId, byte[] model) {

        TransformerFactory tFactory = TransformerFactory.newInstance();
        File outputFile = null;

        try {
            InputStream xsltIn = getClass().getResourceAsStream(APP.CONF.getString("import.transformation.xslt.path")+"adoxx2ontology.xsl");
            Transformer transformer = tFactory.newTransformer(new StreamSource(xsltIn));
            outputFile = createOutputFile(modelSetId);
            Result result = new StreamResult(outputFile);

            transformer.transform(new StreamSource(new ByteArrayInputStream(model)), result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SimpleModelTransformator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException | IOException ex) {
            Logger.getLogger(SimpleModelTransformator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outputFile;
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
    public File createOutputFile(String modelSetId) throws IOException {

        String modelSetFolderPath = APP.CONF.getString("ontology.learnpad.model.instances") + "/" + modelSetId;
        File modelSetFolder = new File(modelSetFolderPath);
        if (!modelSetFolder.exists()) {
            Files.createDirectories(modelSetFolder.toPath());
        }

        Integer latestVersionNumber = 0;
        for (File versionDir : modelSetFolder.listFiles()) {
            Integer v = Integer.valueOf(versionDir.getName());
            if (latestVersionNumber < v) {
                latestVersionNumber = v;
            }
        }
        latestVersionNumber++;
        Path versionPath = Paths.get(modelSetFolder.getPath(), latestVersionNumber.toString());
        Files.createDirectories(versionPath);
        String filename = modelSetId+".ttl";
        File outputFile = new File(versionPath.toString(), filename);

        return outputFile;

    }

}
