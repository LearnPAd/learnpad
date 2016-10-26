/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.query;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QuerySolutionMap;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.recommender.QueryMap;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.ontology.recommender.RecommenderQuery;
import eu.learnpad.or.rest.data.RelatedObject;
import eu.learnpad.or.rest.data.RelatedObjects;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sandro.emmenegger
 */
public class OntRetrieval {

    public RelatedObjects getDocumentsOfAuthor(String modelSetId, Individual author) throws RecommenderException {
        RelatedObjects documents = new RelatedObjects();
        List<RelatedObject> relatedObjectList = new ArrayList<>();
        RecommenderQuery queryObj = QueryMap.getQuery("documentsOrWikiPagesOfSameAuthor");
        Query query = QueryFactory.create(queryObj.getQueryString());
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(modelSetId);
        QueryExecution qexec = null;
        QuerySolutionMap initialBinding = new QuerySolutionMap();
        if (author != null) {
            initialBinding.add("author", author);

            try {
                qexec = QueryExecutionFactory.create(query, model, initialBinding);
                ResultSet results = qexec.execSelect();
                for (; results.hasNext();) {
                    QuerySolution soln = results.nextSolution();
                    RelatedObject relatedObject = new RelatedObject();
                    //Selection: ?documentURI ?documentLocationURL ?documentMimeType ?documentLabel ?documentComment ?documentDescription ?lastUpdated
                    relatedObject.setRelationType("sameCreator");
                    relatedObject.setUri(getLiteralString(soln, "documentURI"));
                    relatedObject.setDocumentUrl(getLiteralString(soln, "documentLocationURL"));
                    relatedObject.setMimeType(getLiteralString(soln, "documentMimeType"));
                    relatedObject.setName(getLiteralString(soln, "documentLabel"));
                    relatedObject.setComment(getLiteralString(soln, "documentComment"));
                    relatedObject.setDescription(getLiteralString(soln, "documentDescription"));
                    relatedObjectList.add(relatedObject);
                }
            } finally {
                if (qexec != null) {
                    qexec.close();
                }
            }

        }

        documents.setRelatedObjects(relatedObjectList);
        return documents;
    }

    private String getLiteralString(QuerySolution soln, String varName) {
        Literal lit = soln.getLiteral(varName);
        if (lit != null) {
            return lit.getString();
        }
        return null;
    }

}
