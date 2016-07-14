/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.data;

import com.hp.hpl.jena.rdf.model.Model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author andreas.martin
 */
public class SOME {

    public static void main(String... args) throws IOException, InvalidFormatException {
        List<String> files = new ArrayList<>();
        File dir = new File(System.getProperty("user.dir"));
        File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.getName().contains(".xlsx")) {
                    files.add(file.getName());
                }
            }
        }

        for (String file : files) {
            Model model = new SOMEService(new File(dir + "\\" + file)).getModel();
            if (model != null) {
                StringWriter out = new StringWriter();
                RDFDataMgr.write(out, model, RDFFormat.TURTLE_BLOCKS);
                System.out.println(out);
                file = file.replace(".xlsx", ".ttl");
                try (FileWriter writer = new FileWriter(dir + "\\" + file)) {
                    writer.write(out.toString());
                    writer.flush();
                }
            }
        }
    }

}
