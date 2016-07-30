/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.data;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.recommender.RecommenderException;
import java.io.File;
import java.io.IOException;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

/**
 * Basic excel data loading tests with test ontology.
 * 
 * @author andreas.martin
 */
public class SOMETest extends AbstractUnitTest{
   
    @Test
    public void excelTest () throws IOException, InvalidFormatException
    {
        Model model = new SOMEService(new File("src/test/resources/SOME/SOMETest.xlsx")).getModel();
        RDFDataMgr.write(System.out, model, RDFFormat.TURTLE_BLOCKS);
    }
    
    @Test
    public void excelExistingModelTest () throws IOException, InvalidFormatException, RecommenderException
    {
        Model model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, "src/test/resources/SOME/ExampleOntology.ttl");
        
        Model initModel = ModelFactory.createDefaultModel();
        RDFDataMgr.read(initModel, "src/test/resources/SOME/ExampleOntology.ttl");
        Model resultModel = new SOMEService(new File("src/test/resources/SOME/SOMETestSPIN.xlsx")).getModel(model);
        RDFDataMgr.write(System.out, resultModel.difference(initModel), RDFFormat.TURTLE_BLOCKS);
    }
}
