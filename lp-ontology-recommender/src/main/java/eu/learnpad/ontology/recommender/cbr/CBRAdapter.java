/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender.cbr;

import ch.fhnw.cbr.service.CBRServices;
import ch.fhnw.cbr.service.data.CaseInstanceVO;
import ch.fhnw.cbr.service.data.CaseMatchVO;
import ch.fhnw.cbr.service.data.CaseViewVO;
import ch.fhnw.cbr.service.data.IndividualVO;
import ch.fhnw.cbr.service.data.LiteralPropertyValueVO;
import ch.fhnw.cbr.service.data.ObjectPropertyInstanceVO;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.util.ArgumentCheck;
import eu.learnpad.or.rest.data.ListOfStringWrapper;
import eu.learnpad.or.rest.data.SimilarCase;
import eu.learnpad.or.rest.data.SimilarCases;
import eu.learnpad.or.rest.data.SimulationData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandro.emmenegger
 */
public class CBRAdapter {

    public static final String OVERALL_PROCESS_VIEW = "http://learnpad.eu/cbr#OverallProcessView";
    public static final String NS_LEARNPAD = "http://learnpad.eu#";
    public static final String NS_EO = "http://ikm-group.ch/archiMEO/eo#";
    public static final String NS_DATA = "http://learnpad.eu/data#";

    // caches session data (case meta data and accumulated user entered data)
    private static final Map<String, Map<String, Object>> accumulatedSessionData = new HashMap<>();

    private static final CBRAdapter instance = new CBRAdapter();

    private static final Map<String, String> OBJECT_PROPERTY_MAP = new HashMap<String, String>();
    private static final Map<String, String> LITERAL_PROPERTY_MAP = new HashMap<String, String>();
    private static final Map<String, String> NAMESPACES = new HashMap<String, String>();

    static {
        OBJECT_PROPERTY_MAP.put("applicationCity", NS_LEARNPAD + "applicationIsLocatedInCity");
        OBJECT_PROPERTY_MAP.put("applicationZone", NS_LEARNPAD + "applicationAffectsZone");
        OBJECT_PROPERTY_MAP.put("applicationPublicAdministration", NS_LEARNPAD + "applicationIsManagedByPublicAdministration");
        OBJECT_PROPERTY_MAP.put("applicationSubType", NS_LEARNPAD + "ApplicationHasType");
        OBJECT_PROPERTY_MAP.put("applicationSector", NS_LEARNPAD + "applicationAffectsSector");
        OBJECT_PROPERTY_MAP.put("applicationBusinessActivity", NS_LEARNPAD + "applicationAffectsTargetBusinessActivity");
        OBJECT_PROPERTY_MAP.put("applicationATECOCategory", NS_LEARNPAD + "affectsTargetATECO");

        OBJECT_PROPERTY_MAP.put("involvedThirdParties", NS_LEARNPAD + "applicationRequiresOrganisationalUnits");

    }

    static {
        LITERAL_PROPERTY_MAP.put("applicationDescription", NS_LEARNPAD + "description");
        LITERAL_PROPERTY_MAP.put("decision", NS_LEARNPAD + "decision");
    }

    static {
        NAMESPACES.put("lpd:", NS_LEARNPAD);
    }

    private CBRAdapter() {
        ch.fhnw.cbr.persistence.OntAO.getInstance().setOntDataSource(new CBROntDataSource());
    }

    public static CBRAdapter getInstance() {
        return instance;
    }

    public synchronized CaseInstanceVO createOrUpdateSimulationSessionCase(String simulationId, SimulationData data) {
        ArgumentCheck.notNull(simulationId, "simulationId in (retrieveSimilarCases");

        if (!accumulatedSessionData.containsKey(simulationId)) {
            if (data != null && data.getSessionData() != null) {
                accumulatedSessionData.put(simulationId, data.getSessionData());
            } else {
                accumulatedSessionData.put(simulationId, new HashMap());
            }
        } else if (data != null && data.getSubmittedData() != null) {
            accumulatedSessionData.get(simulationId).putAll(data.getSubmittedData());
        }

        CBRServices service = CBRServices.getInstance();
        CaseViewVO caseViewVO = service.findCaseViewByUri(OVERALL_PROCESS_VIEW);

        CaseInstanceVO simulationCase = new CaseInstanceVO();
        simulationCase.setClassUri(simulationCaseUri());

        simulationCase.setUri(simulationCaseInstanceUri(simulationId));
        simulationCase.setLabel("Simulation Case " + simulationId);

        //Add properties of data map to case value object
        for (Map.Entry<String, Object> entry : accumulatedSessionData.get(simulationId).entrySet()) {
            String key = entry.getKey();

            Object value = entry.getValue();
            if (value instanceof Collection) {
                addProperties(simulationCase, key, (Collection) value);
            } else {
                List list = new ArrayList(Arrays.asList(value));
                addProperties(simulationCase, key, list);
            }
        }

        simulationCase = service.createOrUpdateCase(caseViewVO, simulationCase);

        return simulationCase;
    }

    public SimilarCases retrieveSimilarCases(String modelSetId, String artifactId, String userId, String simulationSessionId) {
        ArgumentCheck.notNull(simulationSessionId, "simulationSessionId in (retrieveSimilarCases)");

        CBRServices service = CBRServices.getInstance();
        CaseViewVO caseViewVO = service.findCaseViewByUri(OVERALL_PROCESS_VIEW);
        CaseInstanceVO sessionCaseInstance = null;
        try {
            sessionCaseInstance = service.getCaseInstance(simulationCaseInstanceUri(simulationSessionId), caseViewVO);
        } catch (Exception ex) {
            Logger.getLogger(CBRAdapter.class.getName()).log(Level.SEVERE, "Error when fetching simulation session case instance for simulationId: " + String.valueOf(simulationSessionId), ex);
        }
//        if(sessionCaseInstance == null){
//            sessionCaseInstance = createOrUpdateSimulationSessionCase(simulationSessionId, null);
//        }
        List<SimilarCase> similarCasesList = new ArrayList<SimilarCase>();
// The meaning of this if statement is that if some problem occourred with the CBRService
// we assume that there are no similar cases to return         
        if (sessionCaseInstance != null) {
            List<CaseMatchVO> matchingCases = service.retreiveCases(caseViewVO,
                    sessionCaseInstance);

            Collections.sort(matchingCases, new Comparator<CaseMatchVO>() {
                @Override
                public int compare(CaseMatchVO c1, CaseMatchVO c2) {
                    return Double.compare(c2.getSimilarity(),
                            c1.getSimilarity());
                }
            });

            for (int i = 0; i < 4 && matchingCases.size() > i; i++) {

                CaseMatchVO matchingCase = matchingCases.get(i);
                if ((simulationCaseInstanceUri(simulationSessionId))
                        .equals(matchingCase.getCaseUri())) {
                    continue;
                }

                SimilarCase similarCase = new SimilarCase();
                similarCase.setName(matchingCase.getCaseName());
                String similarityValuePercentage = String.format("%1.2f",
                        (matchingCase.getSimilarity() * 100)) + "%";
                similarCase.setSimilarityValue(similarityValuePercentage);
                similarCase.setData(new HashMap<String, ListOfStringWrapper>());

                CaseInstanceVO caseInstanceVO = matchingCase
                        .getCaseInstanceVO();
                if (caseInstanceVO != null) {
                    if (caseInstanceVO.getObjectProperties() != null) {
                        for (ObjectPropertyInstanceVO objectPropertyInstanceVO : caseInstanceVO.getObjectProperties()) {
                            String propertyName = getObjectPropertyName(objectPropertyInstanceVO.getTypeUri());
                            List<IndividualVO> refInstances = objectPropertyInstanceVO.getRangeClassInstances();
                            if (refInstances != null && !refInstances.isEmpty()) {
                                List<String> refInstancesLabels = new ArrayList<String>();
                                for (IndividualVO refInstance : refInstances) {
                                    refInstancesLabels.add(refInstance.getLabel());
                                }
                                ListOfStringWrapper wrapper = new ListOfStringWrapper(refInstancesLabels);
                                similarCase.getData().put(propertyName, wrapper);
                            }
                        }
                    }
                    if (caseInstanceVO.getLiteralProperties() != null) {
                        for (LiteralPropertyValueVO litProp : caseInstanceVO.getLiteralProperties()) {
                            String propertyName = getLiteralPropertyName(replaceNamespaceByPrefix(litProp.getUri()));
                            ListOfStringWrapper wrapper = new ListOfStringWrapper(litProp.getValue());
                            similarCase.getData().put(propertyName, wrapper);
                        }
                    }
                }

                similarCasesList.add(similarCase);
            }
        }
        SimilarCases similarCases = new SimilarCases();
        similarCases.setSimilarCases(similarCasesList);

        return similarCases;
    }
    
    private String getObjectPropertyName(String propertyUri) {
        for (Map.Entry<String, String> entry : OBJECT_PROPERTY_MAP.entrySet()) {
            if (entry.getValue().equals(propertyUri)) {
                return entry.getKey();
            }
        }
        if ((NS_LEARNPAD + "applicationIsSubmittedByApplicant").equals(propertyUri)) {
            return "applicantName";
        }
        return propertyUri;
    }

    private String getLiteralPropertyName(String propertyUri) {
        LITERAL_PROPERTY_MAP.entrySet();
        for (Map.Entry entry : LITERAL_PROPERTY_MAP.entrySet()) {
            if (entry.getValue().equals(propertyUri)) {
                return entry.getKey().toString();
            }
        }
        return propertyUri;
    }

    private void addProperties(CaseInstanceVO simulationCase, String key, Collection values) {
        if (OBJECT_PROPERTY_MAP.containsKey(key)) {
            List<ObjectPropertyInstanceVO> objectProperties = simulationCase.getObjectProperties();
            if (objectProperties == null) {
                objectProperties = new ArrayList<ObjectPropertyInstanceVO>();
            }

            List<IndividualVO> rangeInstances = new ArrayList<>();
            for (Object v : values) {
                String value = replaceNamespacePrefix(v);
                IndividualVO rangeInstance = new IndividualVO();
                rangeInstance.setUri(value);
                rangeInstances.add(rangeInstance);
            }
            try {
                ObjectPropertyInstanceVO property = new ObjectPropertyInstanceVO();
                property.setTypeUri(OBJECT_PROPERTY_MAP.get(key));
                property.setRangeClassInstances(rangeInstances);
                objectProperties.add(property);
                simulationCase.setObjectProperties(objectProperties);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Error er) {
                er.printStackTrace();
            }
        }
        if (LITERAL_PROPERTY_MAP.containsKey(key)) {
            List<LiteralPropertyValueVO> literalProperties = simulationCase.getLiteralProperties();
            if (literalProperties == null) {
                literalProperties = new ArrayList<LiteralPropertyValueVO>();
            }

            for (Object v : values) {
                String value = v == null ? "" : v.toString();
                LiteralPropertyValueVO literalPropertyValueVO = new LiteralPropertyValueVO();
                literalPropertyValueVO.setUri(LITERAL_PROPERTY_MAP.get(key));
                literalPropertyValueVO.setValue(value);
                literalProperties.add(literalPropertyValueVO);
            }
            simulationCase.setLiteralProperties(literalProperties);
        }

        //Create person out of applicant name
        if ("applicantName".equals(key) && !values.isEmpty()) {
            IndividualVO rangeInstance = new IndividualVO();
            rangeInstance.setClassUri(NS_EO + "Person");
            String personName = values.iterator().next().toString();
            rangeInstance.setLabel(personName);
            personName = personName.replace(" ", "_");
            rangeInstance.setUri(NS_DATA + personName);

            ObjectPropertyInstanceVO property = new ObjectPropertyInstanceVO();
            property.setUri(NS_LEARNPAD + "applicationIsSubmittedByApplicant");

            List rangeInstanceList = new ArrayList(Arrays.asList(rangeInstance));
            property.setRangeClassInstances(rangeInstanceList);

            List propertyList = new ArrayList(Arrays.asList(property));
            simulationCase.setObjectProperties(propertyList);
        }
    }

    private String replaceNamespacePrefix(Object value) {
        String retValue = value instanceof String ? value.toString() : "";
        for (String ns : NAMESPACES.keySet()) {
            if (retValue.startsWith(ns)) {
                retValue = NAMESPACES.get(ns) + retValue.substring(ns.length());
                return retValue;
            }
        }
        return retValue;
    }

    private String replaceNamespaceByPrefix(String uri) {
        for (Map.Entry<String, String> namspaceMapping : NAMESPACES.entrySet()) {
            String fullNamespace = namspaceMapping.getValue();
            if (uri.startsWith(fullNamespace)) {
                uri = namspaceMapping.getKey() + uri.substring(fullNamespace.length());
                return uri;
            }
        }
        return uri;
    }
    
    private String simulationCaseUri(){
        return APP.CONF.getString("ontology.simulation.case.class.uri");
    }
    
    private String simulationCaseInstanceUri(String simulationSessionId){
        return simulationCaseUri()+simulationSessionId;
    }

}
