/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import org.apache.jena.riot.Lang;
import org.junit.BeforeClass;

/**
 *
 * @author sandro.emmenegger
 */
public class AbstractUnitTest {
    
    protected static final String MODELSET_ID = APP.CONF.getString("testdata.modelset.version");
    protected static final String MODEL_ID = APP.CONF.getString("testdata.model.id", "mod.1234");
    protected static final String ARTIFACT_ID = APP.CONF.getString("testdata.artifact.id", "obj.1234");
    protected static final String TEST_USER = "b.barnes@learnpad.eu";
    
    /**
     * Remove all transformed files after testrun;
     * 
     * @throws eu.learnpad.ontology.recommender.RecommenderException
     */
    @BeforeClass
    public static void cleanUpDataFile() throws RecommenderException {
        File dataFile = new File(APP.CONF.getString("working.directory"), APP.CONF.getString("execution.data.path.relative"));
        if(dataFile.exists() && dataFile.length() > 0){
            OntModel dataToRemove = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
            dataToRemove.setNsPrefix("xwiki", APP.NS.XWIKI.toString());
            dataToRemove.setNsPrefix("exec", APP.NS.EXEC.toString());
            dataToRemove.read(dataFile.toURI().toString(), Lang.TTL.getName());
            dataFile.delete();
            OntModel model = FileOntAO.getInstance().getModelWithExecutionData(MODELSET_ID);
            model.remove(dataToRemove);
        }
    }    

    protected static void cleanUp(Path rootPath) throws IOException {

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

    protected List<Individual> getInstancesWithProperty(String ontClassName, String propertyName, RDFNode value) throws RecommenderException{
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(MODELSET_ID);
        OntClass ontClass = model.getOntClass(ontClassName);
        OntProperty ontProperty = model.getOntProperty(propertyName);
        return OntUtil.getInstancesWithProperty(model, ontClass, ontProperty, value);
    }
    
    protected Individual getTestUser() throws RecommenderException{
        Literal userIdValue = FileOntAO.getInstance().getModelWithExecutionData(MODELSET_ID).createTypedLiteral(TEST_USER);
        List<Individual> persons = getInstancesWithProperty(APP.NS.OMM + "Performer", APP.NS.EMO + "performerHasEmailAddress", userIdValue);
        if(!persons.isEmpty()){
            return persons.get(0);
        }
        return null;
    }

    protected OntModel latestModel() throws RecommenderException {
        String latestModelSetVersion = SimpleModelTransformator.getInstance().getLatestModelSetId();
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(latestModelSetVersion);
        return model;
    }
}
