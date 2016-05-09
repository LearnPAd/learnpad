/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.execution;


import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.OntAO;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.State;
import eu.learnpad.or.rest.data.States;

/**
 *
 * @author sandro.emmenegger
 */
public class ExecutionStates {
    
    private static final ExecutionStates instance = new ExecutionStates();
    
    public static ExecutionStates getInstance() {
        return instance;
    }
	
    public ExecutionStates() {}
    
    public States getStatesOfLatestAddedModelSet(String userId){
        String latestAddeModelSetId = SimpleModelTransformator.getInstance().getLatestModelSetId();
        if(latestAddeModelSetId == null || latestAddeModelSetId.isEmpty()){
            return new States();
        }
        return getStates(userId, latestAddeModelSetId);
    }

    public States getStates(String userId, String modelSetId) {

        String queryString = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "prefix bpmn: <http://ikm-group.ch/archiMEO/BPMN#>\n"
                + "prefix emo: <http://ikm-group.ch/archiMEO/emo#>\n"
                + "prefix lpd: <http://learnpad.eu#>\n"
                + "prefix omm: <http://ikm-group.ch/archiMEO/omm#>"
                + "SELECT ?processLabel ?executedProcess ?majorModelSetVersion ?flowElement ?flowElementLabel ?executedFlowElement ?startTime\n"
                + "WHERE {\n"
                + "    ?process a bpmn:Process .\n"
                + "    ?process emo:belongsToVersion ?version .\n"
                + "    ?process rdfs:label ?processLabel .\n"
                + "    ?version emo:majorModelSetVersion ?majorModelSetVersion .\n"
                + "    ?executedProcess a ?process .\n"
                + "    ?flowElementClass (rdfs:subClassOf)* bpmn:FlowElement .\n"
                + "    ?flowElement a ?flowElementClass .\n"
                + "    ?flowElement rdfs:label ?flowElementLabel .\n"
                + "    ?executedFlowElement a ?flowElement .\n"
                + "    ?executedFlowElement lpd:executedFlowElementBelongsToExecutedProcess ?executedProcess .\n"
                + "    ?executedFlowElement lpd:executedFlowElementStartTime ?startTime .\n"
                + "    FILTER NOT EXISTS {\n"
                + "        ?executedFlowElement lpd:executedFlowElementEndingTime ?endingTime .\n"
                + "    } .\n"
                + "    FILTER EXISTS {\n"
                + "        OPTIONAL {\n"
                + "            ?flowElement emo:swimlaneRepresentsOrganisationalUnit ?organisationalUnit .\n"
                + "            ?subOrganisationalUnit omm:organisationalUnitIsSubordinatedToOrganisationalUnit ?organisationalUnit .\n"
                + "        } .\n"
                + "        OPTIONAL {\n"
                + "            ?flowElement emo:poolRepresentsOrganisationalUnit ?organisationalUnit .\n"
                + "            ?subOrganisationalUnit omm:organisationalUnitIsSubordinatedToOrganisationalUnit ?organisationalUnit .\n"
                + "        } .\n"
                + "        ?role lpd:roleIsCastedByOrgUnit ?subOrganisationalUnit .\n"
                + "        ?performer omm:performerHasRole ?role .\n"
                + "        ?performer rdfs:label \"Barnaby Barnes\" .\n"
                + "    } .\n"
                + "}";

        Query query = QueryFactory.create(queryString);
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(modelSetId);
//        model.write(System.out, "Turtle");
        QueryExecution qexec = null;
        States states = new States();
        try {
            qexec = QueryExecutionFactory.create(query, model);
            ResultSet results = qexec.execSelect();
            for (; results.hasNext();) {
                QuerySolution soln = results.nextSolution();
                Literal processLabel = soln.getLiteral("processLabel");
                Resource executedProcessInstance = soln.getResource("executedProcess");
                Resource flowElement = soln.getResource("flowElement");
                Literal flowElementLabel = soln.getLiteral("flowElementLabel");
                Resource executedFlowElement = soln.getResource("executedFlowElement");
                Literal startTime = soln.getLiteral("startTime");
                
                State state = new State();
                state.setModelSetId(modelSetId);
                state.setProcessInstanceId(executedProcessInstance.getLocalName());
                state.setProcessName(processLabel.getString());
                state.setArtefactId(flowElement.getLocalName());
                state.setArtefactName(flowElementLabel.getString());
                XSDDateTime startTimeXsd = (XSDDateTime) startTime.getValue();
                state.setStartTime(startTimeXsd.asCalendar().getTime());
                states.addState(state);
            }
        } finally {
            if (qexec != null) {
                qexec.close();
            }
        }

        return states;
    }
}
