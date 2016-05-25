/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.persistence.util;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * File based implementation of a ontology persistence access object.
 *
 * @author sandro.emmenegger
 */
public class OntUtil{

    /**
     * Returns all instances of the given OntClass including instances of
     * subclasses.
     *
     * @param model
     * @param rootClass
     * @return
     */
    public static List<Individual> getInstances(OntModel model, OntClass rootClass) {
        List<Individual> retInstances = new ArrayList<>();
        if (rootClass.getURI() == null) {
            return retInstances;
        }

        String queryString
                = "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT ?instance WHERE {\n"
                + "   ?class rdfs:subClassOf* ?rootClass. \n"
                + "   ?instance rdf:type ?class .\n"
                + "}\n";

        ParameterizedSparqlString queryStr = new ParameterizedSparqlString(queryString);
        queryStr.setParam("rootClass", rootClass);
        Query query = QueryFactory.create(queryStr.toString());
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet resSet = qexec.execSelect();

        while (resSet.hasNext()) {
            QuerySolution querySolution = resSet.next();
            Resource res = querySolution.getResource("instance");

            if (res.getURI() != null) {
                Individual instance = model.getIndividual(res.getURI());
                retInstances.add(instance);
            }
        }

        return retInstances;
    }
    
/**
     * Returns all instances of the given OntClass including instances of
     * subclasses with the property value set as defined.
     *
     * @param model
     * @param rootClass
     * @param property
     * @param value
     * @return
     */
    public static List<Individual> getInstancesWithProperty(OntModel model, OntClass rootClass, Property property, RDFNode value) {
        List<Individual> retInstances = new ArrayList<>();
        if (rootClass.getURI() == null) {
            return retInstances;
        }

        String queryString
                = "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT ?instance WHERE {\n"
                + "   ?class rdfs:subClassOf* ?rootClass. \n"
                + "   ?instance rdf:type ?class .\n"
                + "   ?instance ?property ?propertyValue .\n"
                + "}\n";

        ParameterizedSparqlString queryStr = new ParameterizedSparqlString(queryString);
        queryStr.setParam("rootClass", rootClass);
        queryStr.setParam("property", property);
        queryStr.setParam("propertyValue", value);
        Query query = QueryFactory.create(queryStr.toString());
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet resSet = qexec.execSelect();

        while (resSet.hasNext()) {
            QuerySolution querySolution = resSet.next();
            Resource res = querySolution.getResource("instance");

            if (res.getURI() != null) {
                Individual instance = model.getIndividual(res.getURI());
                retInstances.add(instance);
            }
        }

        return retInstances;
    }    

    /**
     * Convenient method returns a literal property's content if set, otherwise
     * the default value will be returned.
     *
     * @param model
     * @param individualURI
     * @param propertyURI
     * @param defaultValue
     * @return
     */
    public static String getLiteralPropertyString(OntModel model, String individualURI, String propertyURI, String defaultValue) {
        if (propertyURI == null || "".equals(propertyURI.trim())) {
            return defaultValue;
        }
        Individual individual = model.getIndividual(individualURI);
        if (individual == null) {
            return defaultValue;
        }
        Property property = model.getProperty(propertyURI);
        if (property == null) {
            return defaultValue;
        }
        RDFNode value = individual.getPropertyValue(property);
        if (value == null) {
            return defaultValue;
        }
        return value.asLiteral().getString();
    }
}
