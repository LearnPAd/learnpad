/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology;

import com.hp.hpl.jena.rdf.model.Model;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author sandro.emmenegger
 */
public class AbstractUnitTest {
    
    protected static final String MODELSET_ID = APP.CONF.getString("testdata.modelset.version");
    protected static final String TEST_USER = "barnaby.barnes@learnpad.com";
    
    /**
     * Remove all transformed files after testrun;
     * 
     * @throws IOException 
     */
    //@AfterClass
    public static void after() throws IOException {
        Path path = SimpleModelTransformator.getInstance().getModelSetFolderPath(MODELSET_ID);
        cleanUp(path.getParent());
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

}
