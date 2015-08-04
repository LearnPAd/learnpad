/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author sandro.emmenegger
 */
public class SimpleModelTransformatorTest {

    public SimpleModelTransformatorTest() {
    }

    /**
     * Test of transform method, of class SimpleModelTransformator.
     * @throws java.io.IOException
     */
    @Test
    public void testTransform() throws IOException {

        String modelSetId = "modelset-222";
        String testModelsFilePath = "/models/Models4Transformation-18032015.xml";
        
        File outputFile = transform(testModelsFilePath, modelSetId);
        assertNotNull(outputFile);
        assertTrue(outputFile.exists());
        assertTrue(outputFile.length() > 0);
        
        //same again
         File outputFile2 = transform(testModelsFilePath, modelSetId);
         assertEquals(outputFile.getPath(), outputFile2.getPath());
        
         modelSetId = "modelset-224";
         outputFile2 = transform(testModelsFilePath, modelSetId);
         assertFalse(outputFile.getPath().equals(outputFile2.getPath()));
        
    }

    private File transform(String testModelsFilePath, String modelSetId) throws IOException {
        InputStream in = getClass().getResourceAsStream(testModelsFilePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int next = in.read();
        while (next > -1) {
            bos.write(next);
            next = in.read();
        }
        bos.flush();
        byte[] testModelFile = bos.toByteArray();
        SimpleModelTransformator t = new SimpleModelTransformator("ADOxx");
        File outputFile = t.transform(modelSetId, testModelFile);
        return outputFile;
    }

}
