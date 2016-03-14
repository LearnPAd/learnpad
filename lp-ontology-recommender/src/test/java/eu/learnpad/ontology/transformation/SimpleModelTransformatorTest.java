/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.transformation;

import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author sandro.emmenegger
 */
public class SimpleModelTransformatorTest extends AbstractUnitTest{
    
    /**
     * Test of transform method, of class SimpleModelTransformator.
     *
     * @throws java.io.IOException
     */
//    @Ignore
    @Test
    public void testTransform() throws IOException {

        byte[] modelSetFile1 = FileOntAO.getInstance().getModelSetFile(APP.CONF.getString("testdata.model.file.path.previous"));
        assertNotNull(modelSetFile1);
        assertTrue(modelSetFile1.length > 0);
        
        String modelSetId1 = APP.CONF.getString("testdata.modelset.version.previous");
        File transformedFile1 = SimpleModelTransformator.getInstance().transform(modelSetId1, new ByteArrayInputStream(modelSetFile1), ModelSetType.ADOXX);
        assertNotNull(transformedFile1);
        assertTrue(transformedFile1.exists());
        assertTrue(transformedFile1.length() > 0);        
        File latestTransformationFile = SimpleModelTransformator.getInstance().getLatestVersionFile(modelSetId1);
        assertNotNull(latestTransformationFile);
        assertEquals(latestTransformationFile.getPath(), transformedFile1.getPath());
        
        //same again
        transformedFile1 = SimpleModelTransformator.getInstance().transform(modelSetId1, new ByteArrayInputStream(modelSetFile1), ModelSetType.ADOXX);
        assertEquals("Expect no new folder resp. version since the modelset file has not changed.", transformedFile1.getPath(), transformedFile1.getPath());
                
                
        byte[] modelSetFile2 = FileOntAO.getInstance().getModelSetFile(APP.CONF.getString("testdata.model.file.path"));
        assertNotNull(modelSetFile2);
        assertTrue(modelSetFile2.length > 0);
        
        String modelSetId2 = APP.CONF.getString("testdata.modelset.version");
        File transformedFile2 = SimpleModelTransformator.getInstance().transform(modelSetId2, new ByteArrayInputStream(modelSetFile2), ModelSetType.ADOXX);
        assertNotNull(transformedFile2);
        assertTrue(transformedFile2.exists());
        assertTrue(transformedFile2.length() > 0);            
        
        latestTransformationFile = SimpleModelTransformator.getInstance().getLatestVersionFile(modelSetId2);
        assertNotNull(latestTransformationFile);
        assertEquals(latestTransformationFile.getPath(), transformedFile2.getPath());

    }
}
