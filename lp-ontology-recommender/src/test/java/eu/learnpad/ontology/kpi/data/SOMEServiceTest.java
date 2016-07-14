/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.data;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author sandro.emmenegger
 */
public class SOMEServiceTest extends AbstractUnitTest {
    
    private static Path testFilePath;

    public SOMEServiceTest() {
    }

    @Before
    public void writeTestFileToWorkingFolder() throws IOException {
        File baseWorkingFolder = new File(APP.CONF.getString("working.directory"));
        assertTrue(baseWorkingFolder.exists());

        File kpiDataFolder = new File(baseWorkingFolder, APP.CONF.getString("kpi.dashboard.data.folder.relative"));
        if (!kpiDataFolder.exists()) {
            kpiDataFolder.mkdirs();
        }
        
        String filename = "IndividualsPerformanceKPIs_20160630.xlsx";
        testFilePath = Paths.get(kpiDataFolder.toString(), filename);

        Files.copy(Paths.get("src/main/resources/testdata/kpi/"+filename), testFilePath, StandardCopyOption.REPLACE_EXISTING);

    }

    @Test
    public void testGetModel() throws Exception {

        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(MODELSET_ID);
        SOMEService service = new SOMEService(testFilePath.toFile());
        Model insertedKPIValuesModel = service.getModel(model);
        assertNotNull(insertedKPIValuesModel);
        
        assertFalse(insertedKPIValuesModel.isEmpty());
        assertTrue(insertedKPIValuesModel.listStatements().hasNext());
        
//        insertedKPIValuesModel.write(System.out, "TTL");

    }

}
