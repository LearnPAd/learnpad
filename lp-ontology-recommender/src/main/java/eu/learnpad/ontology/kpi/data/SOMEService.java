/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.data;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.topbraid.spin.system.SPINModuleRegistry;

/**
 * KPI excel data loading based on the projects ontology and corresponding excel testfile.
 * 
 * @author andreas.martin
 */
public class SOMEService {
    
    private static final Logger LOGGER = Logger.getLogger(SOMEService.class.getName());

    private final ExcelParser ep;
    private final String excelFile;

    public SOMEService(File excelFile) {
        ep = new ExcelParser(excelFile);
        this.excelFile = excelFile.getAbsolutePath();
    }

    /**
     * Runs SPARQL query defined in the Excel file to construct values in the 
     * model (ontology). 
     * 
     * @param model
     * @return Model with instances created based on the excel data records.
     * @throws IOException
     * @throws InvalidFormatException 
     */
    public Model getModel(Model model) throws IOException, InvalidFormatException {
        SPINModuleRegistry.get().registerAll(model, null);
        String querySP = ep.getSPARQLQuery();
        if (querySP != null) {
            List<List<String>> dataTable = ep.getDataTable();
            if (!dataTable.isEmpty()) {
                return SOMEExecution.exec(model, SOMEExecution.getTable(dataTable), querySP);
            } else {
                LOGGER.log(Level.WARNING, "{0} contains no DATA", excelFile);
            }
        } else {
            LOGGER.log(Level.WARNING, "{0} contains no SPARQL query", excelFile);
        }
        return null;
    }
    
    public Model getModel() throws IOException, InvalidFormatException {
        return getModel(ModelFactory.createDefaultModel());
    }
}
