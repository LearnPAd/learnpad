package eu.learnpad.verification.plugin.interfaces.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Scanner;

import org.junit.Test;

import eu.learnpad.verification.plugin.bpmn.guideline.factory.GuidelinesFactoryTest;

public class BPMNUnderstandabilityTest {

    @Test
    public void test() {
        InputStream is = GuidelinesFactoryTest.class.getClassLoader().getResourceAsStream("annidategateway.bpmn");
        assertNotNull(is);
        BPMNUnderstandability understandability = new BPMNUnderstandability();
        String[]  type = understandability.getVerificationTypeProvided();
        String model= new Scanner(is).useDelimiter("\\A").next();
        String result = understandability.performVerification(model, type[0]);
        //System.out.println(result);
        assertNotNull(result);
    }

    @Test
    public void testError() {
        InputStream is = GuidelinesFactoryTest.class.getClassLoader().getResourceAsStream("error.bpmn");
        assertNotNull(is);
        BPMNUnderstandability understandability = new BPMNUnderstandability();
        String[]  type = understandability.getVerificationTypeProvided();
        String model= new Scanner(is).useDelimiter("\\A").next();
        String result = understandability.performVerification(model, type[0]);
        //System.out.println(result);
        assertNotNull(result);
    }
    
    @Test
    public void testError2() {
        InputStream is = GuidelinesFactoryTest.class.getClassLoader().getResourceAsStream("modelloTestO.bpmn");
        assertNotNull(is);
        BPMNUnderstandability understandability = new BPMNUnderstandability();
        String[]  type = understandability.getVerificationTypeProvided();
        String model= new Scanner(is).useDelimiter("\\A").next();
        String result = understandability.performVerification(model, type[0]);
        //System.out.println(result);
        assertNotNull(result);
    }
/*
    @Test
    public void mainTest() {
        try {
            InputStream is = GuidelinesFactoryTest.class.getClassLoader().getResourceAsStream("annidategateway.bpmn");
            assertNotNull(is);

            File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            BPMNUnderstandability understandability = new BPMNUnderstandability();
            String[] args = new String[1];
            args[0] = temp.getAbsolutePath();
            understandability.main(args);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
    }
*/
}

