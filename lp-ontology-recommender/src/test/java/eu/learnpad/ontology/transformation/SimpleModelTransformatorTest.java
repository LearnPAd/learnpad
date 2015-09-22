/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.transformation;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import javax.inject.Inject;
import org.jglue.cdiunit.CdiRunner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author sandro.emmenegger
 */
@RunWith(CdiRunner.class)
public class SimpleModelTransformatorTest extends AbstractUnitTest{
    
    @Inject
    SimpleModelTransformator transformator;

    private final Path transformedModelsRoot = Paths.get(APP.CONF.getString("ontology.learnpad.model.instances"));

    @Before
    public void before() throws IOException {
        cleanUp(transformedModelsRoot);
    }

    /**
     * Test of transform method, of class SimpleModelTransformator.
     *
     * @throws java.io.IOException
     */
    @Ignore
    @Test
    public void testTransform() throws IOException {

        
        String testModelsFilePath = "/models/Models4Transformation-18032015.xml";

        File outputFile = transform(testModelsFilePath, TEST_MODEL_SET_ID_1, ModellingEnvironmentType.ADOxx);
        assertNotNull(outputFile);
        assertTrue(outputFile.exists());
        assertTrue(outputFile.length() > 0);

        //same again
        File outputFile2 = transform(testModelsFilePath, TEST_MODEL_SET_ID_1, ModellingEnvironmentType.ADOxx);
        assertEquals(outputFile.getPath(), outputFile2.getPath());

        outputFile2 = transform(testModelsFilePath, TEST_MODEL_SET_ID_2, ModellingEnvironmentType.ADOxx);
        assertFalse(outputFile.getPath().equals(outputFile2.getPath()));

        File latestTransformation = transformator.getLatestVersionFile(TEST_MODEL_SET_ID_2);
        assertNotNull(latestTransformation);
        assertEquals(latestTransformation.getPath(), outputFile2.getPath());

    }
    
    @Test
    public void testTitoloUnico() throws IOException{
        String testModelsFilePath = "/models/TitoloUnicoV4/TitoloUnico_v4.xml";

        File outputFile = transform(testModelsFilePath, TEST_MODEL_SET_ID_TITOLO_UNICO, ModellingEnvironmentType.ADOxx);
        assertNotNull(outputFile);
        assertTrue(outputFile.exists());
        assertTrue(outputFile.length() > 0);
    }

    private File transform(String testModelsFilePath, String modelSetId, ModellingEnvironmentType type) throws IOException {
        InputStream in = getClass().getResourceAsStream(testModelsFilePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int next = in.read();
        while (next > -1) {
            bos.write(next);
            next = in.read();
        }
        bos.flush();
        byte[] testModelFile = bos.toByteArray();

        File outputFile = transformator.transform(modelSetId, testModelFile, type);
        return outputFile;
    }

    private void cleanUp(Path rootPath) throws IOException {

        if (Files.exists(rootPath)) {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.deleteIfExists(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.deleteIfExists(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

}
