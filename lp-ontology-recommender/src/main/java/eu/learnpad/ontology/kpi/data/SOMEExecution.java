/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.data;

import com.hp.hpl.jena.graph.NodeFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.algebra.Table;
import com.hp.hpl.jena.sparql.algebra.TableFactory;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.engine.binding.Binding;
import com.hp.hpl.jena.sparql.engine.binding.BindingHashMap;
import com.hp.hpl.jena.sparql.syntax.Element;
import com.hp.hpl.jena.sparql.syntax.ElementData;
import com.hp.hpl.jena.sparql.syntax.ElementGroup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.topbraid.spin.arq.ARQFactory;
import org.topbraid.spin.inference.SPINInferences;
import org.topbraid.spin.system.SPINModuleRegistry;

/**
 *
 * @author andreas.martin
 */
public class SOMEExecution {

    public static Model exec(Model model, Table table, String query) throws IOException {
        Query currentQuery = QueryFactory.create(query);
        return exec(model, table, currentQuery);
    }

    public static Table getTable(List<List<String>> dataTable) {

        Table table = TableFactory.create();
        Integer rowNumber = 0;
        List<Var> vars = new ArrayList<>();

        for (List<String> rowData : dataTable) {
            if (rowNumber == 0) {
                for (String data : rowData) {
                    vars.add(Var.alloc(data));
                }
                table = TableFactory.create(vars);
            } else {
                table.addBinding(toBinding(rowData, vars));
            }
            rowNumber++;
        }
        return table;
    }

    public static Binding toBinding(List<String> row, List<Var> vars) {
        BindingHashMap result = new BindingHashMap();
        for (int i = 0; i < vars.size(); i++) {
            result.add(vars.get(i), NodeFactory.createLiteral(row.get(i)));
        }
        return result;
    }

    public static Model exec(Model model, final Table table, Query query) throws IOException {
        OntModel inferencedModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ElementData tableElementData = new ElementData() {
            @Override
            public Table getTable() {
                return table;
            }
        };
        for (Var var : table.getVars()) {
            tableElementData.add(var);
        }
        ElementGroup elementGroup = new ElementGroup();
        elementGroup.addElement(tableElementData);
        if (query.getQueryPattern() instanceof ElementGroup) {
            for (Element element : ((ElementGroup) query.getQueryPattern()).getElements()) {
                elementGroup.addElement(element);
            }
        } else {
            elementGroup.addElement(query.getQueryPattern());
        }
        query.setQueryPattern(elementGroup);

//        QueryExecution ex = QueryExecutionFactory.create(query, model);
        QueryExecution ex = ARQFactory.get().createQueryExecution(query, model);
        if (query.isConstructType()) {
            ex.execConstruct(inferencedModel);
        } else {
            inferencedModel.add(ex.execSelect().getResourceModel());
        }
        return inferencedModel;
    }

    public static Model inferenceRulesAndAdd(Model model) {
        model.add(inferenceRules(model));
        return model;
    }

    public static Model inferenceRules(Model model) {
        // Initialize system functions and templates
        SPINModuleRegistry.get().init();

        // Load domain model with imports
        OntModel newModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, model);

        // Register any new functions defined
        SPINModuleRegistry.get().registerAll(newModel, null);

        // Create and add Model for inferred triples
        Model newTriples = ModelFactory.createDefaultModel();
        newModel.addSubModel(newTriples);
        SPINInferences.run(newModel, newTriples, null, null, false, null);
        return newTriples;
    }

}
