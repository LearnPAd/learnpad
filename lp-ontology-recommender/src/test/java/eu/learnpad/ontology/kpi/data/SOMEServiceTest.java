/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.data;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.persistence.FileOntAO;
import java.io.File;
import java.nio.file.Path;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test data loading from excel and applying rule to calculate KPI's on the projects ontology.
 * 
 * @author sandro.emmenegger
 */
public class SOMEServiceTest extends AbstractUnitTest {
    
    private static Path testFilePath;

    public SOMEServiceTest() {
    }

    @Test
    public void testGetModel() throws Exception {

        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(MODELSET_ID);
        File testExcel = new File("src/main/resources/testdata/kpi/IndividualsPerformanceKPIs_20160630.xlsx");
        assertTrue(testExcel.exists());
        SOMEService service = new SOMEService(testExcel);
        Model insertedKPIValuesModel = service.getModel(model);
        
        model.write(System.out, "TTL");     
        
        assertNotNull(insertedKPIValuesModel);
        
        assertFalse(insertedKPIValuesModel.isEmpty());
        assertTrue(insertedKPIValuesModel.listStatements().hasNext());

    }

}
