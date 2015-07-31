/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
     */
    @Test
    public void testTransform() throws IOException {
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));

        InputStream in = getClass().getResourceAsStream("/models/Models4Transformation-18032015.xml");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int next = in.read();
        while (next > -1) {
            bos.write(next);
            next = in.read();
        }
        bos.flush();
        byte[] testModelFile = bos.toByteArray();
        SimpleModelTransformator t = new SimpleModelTransformator("ADOxx");
        t.transform("modelset-222", testModelFile);
    }

}
