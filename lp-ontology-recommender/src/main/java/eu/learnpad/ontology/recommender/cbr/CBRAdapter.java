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
import eu.learnpad.ontology.util.ArgumentCheck;
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

/**
 *
 * @author sandro.emmenegger
 */
public class CBRAdapter {

    public static final String SIMULATION_CASE_CLASS_URI = "http://learnpad.eu#SimulatedApplicationCase_";
    public static final String OVERALL_PROCESS_VIEW = "http://learnpad.eu/cbr#OverallProcessView";
    public static final String NS_LEARNPAD = "http://learnpad.eu#";
    public static final String NS_EO = "http://ikm-group.ch/archiMEO/eo#";
    public static final String NS_DATA = "http://learnpad.eu/data#";

    // caches session data (case meta data and accumulated user entered data)
    private static final Map<String, Map<String, Object>> accumulatedSessionData = new HashMap<>();

    private static final CBRAdapter instance = new CBRAdapter();

    private static final Map<String, String> OBJECT_PROPERTY_MAP = new HashMap();
    private static final Map<String, String> LITERAL_PROPERTY_MAP = new HashMap();
    private static final Map<String, String> NAMESPACES = new HashMap();

    static {
        OBJECT_PROPERTY_MAP.put("applicationCity", NS_LEARNPAD + "applicationIsLocatedInCity");
        OBJECT_PROPERTY_MAP.put("applicationZone", NS_LEARNPAD + "applicationAffectsZone");
        OBJECT_PROPERTY_MAP.put("applicationPublicAdministration", NS_LEARNPAD + "applicationIsManagedByPublicAdministration");
        OBJECT_PROPERTY_MAP.put("applicationSubType", NS_LEARNPAD + "ApplicationHasType");
        OBJECT_PROPERTY_MAP.put("applicationSector", NS_LEARNPAD + "applicationAffectsSector");
        OBJECT_PROPERTY_MAP.put("applicationBusinessActivity", NS_LEARNPAD + "applicationAffectsTargetBusinessActivity");
        OBJECT_PROPERTY_MAP.put("applicationATECOCategory", NS_LEARNPAD + "affectsTargetATECO");

        OBJECT_PROPERTY_MAP.put("organisationalUnit", NS_LEARNPAD + "applicationRequiresOrganisationalUnits");
        OBJECT_PROPERTY_MAP.put("decision", NS_LEARNPAD + "decision");
    }

    static {
        LITERAL_PROPERTY_MAP.put("applicationDescription", NS_LEARNPAD + "description");
    }

    static {
        NAMESPACES.put("lpd:", NS_LEARNPAD);
    }

    private CBRAdapter() {
    }

    public static CBRAdapter getInstance() {
        return instance;
    }

    public CaseInstanceVO createOrUpdateSimulationSessionCase(String simulationId, SimulationData data) {
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
        simulationCase.setClassUri(SIMULATION_CASE_CLASS_URI);

        simulationCase.setUri(SIMULATION_CASE_CLASS_URI + simulationId);
        simulationCase.setLabel("Simulation Case " + simulationId);

        //Add properties of data map to case value object
        for (Map.Entry<String, Object> entry : accumulatedSessionData.get(simulationId).entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Collection) {
                addProperties(simulationCase, key, (Collection) value);
            } else {
                addProperties(simulationCase, key, Arrays.asList(value));
            }
        }

        simulationCase = service.createOrUpdateCase(caseViewVO, simulationCase);

        return simulationCase;
    }

    public SimilarCases retrieveSimilarCases(String modelSetId, String artifactId, String userId, String simulationSessionId) {
        ArgumentCheck.notNull(simulationSessionId, "simulationSessionId in (retrieveSimilarCases)");
        
        CBRServices service = CBRServices.getInstance();
        CaseViewVO caseViewVO = service.findCaseViewByUri(OVERALL_PROCESS_VIEW);
        CaseInstanceVO sessionCaseInstance = service.getCaseInstance(SIMULATION_CASE_CLASS_URI + simulationSessionId, caseViewVO);
        List<CaseMatchVO> matchingCases = service.retreiveCases(caseViewVO, sessionCaseInstance);
        List<SimilarCase> similarCasesList = new ArrayList<>();

        Collections.sort(matchingCases, new Comparator<CaseMatchVO>() {
            @Override
            public int compare(CaseMatchVO c1, CaseMatchVO c2) {
                return Double.compare(c2.getSimilarity(), c1.getSimilarity());
            }
        });

        for (int i = 0; i < 4 && matchingCases.size() > i; i++) {

            CaseMatchVO matchingCase = matchingCases.get(i);
            if ((SIMULATION_CASE_CLASS_URI + simulationSessionId).equals(matchingCase.getCaseUri())) {
                continue;
            }

            SimilarCase similarCase = new SimilarCase();
            similarCase.setName(matchingCase.getCaseName());
            String similarityValuePercentage = String.format("%1.2f", (matchingCase.getSimilarity() * 100)) + "%";
            similarCase.setSimilarityValue(similarityValuePercentage);
            similarCase.setData(new HashMap<String, Object>());

            CaseInstanceVO caseInstanceVO = matchingCase.getCaseInstanceVO();
            if (caseInstanceVO != null) {
                if (caseInstanceVO.getObjectProperties() != null) {
                    for (ObjectPropertyInstanceVO objectPropertyInstanceVO : caseInstanceVO.getObjectProperties()) {
                        String propertyName = getObjectPropertyName(objectPropertyInstanceVO.getTypeUri());
                        List<IndividualVO> refInstances = objectPropertyInstanceVO.getRangeClassInstances();
                        if (refInstances != null && !refInstances.isEmpty()) {
                            List<String> refInstancesLabels = new ArrayList();
                            for (IndividualVO refInstance : refInstances) {
                                refInstancesLabels.add(refInstance.getLabel());
                            }
                            similarCase.getData().put(propertyName, refInstancesLabels);
                        }
                    }
                }
                if (caseInstanceVO.getLiteralProperties() != null) {
                    for (LiteralPropertyValueVO litProp : caseInstanceVO.getLiteralProperties()) {
                        String propertyName = getLiteralPropertyName(replaceNamespaceByPrefix(litProp.getUri()));
                        similarCase.getData().put(propertyName, litProp.getValue());
                    }
                }
            }

            similarCasesList.add(similarCase);
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
                objectProperties = new ArrayList<>();
            }

            List<IndividualVO> rangeInstances = new ArrayList<>();
            for (Object v : values) {
                String value = replaceNamespacePrefix(v);
                IndividualVO rangeInstance = new IndividualVO();
                rangeInstance.setUri(value);
                rangeInstances.add(rangeInstance);
            }
            ObjectPropertyInstanceVO property = new ObjectPropertyInstanceVO();
            property.setTypeUri(OBJECT_PROPERTY_MAP.get(key));
            property.setRangeClassInstances(rangeInstances);
            objectProperties.add(property);
            simulationCase.setObjectProperties(objectProperties);
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
            property.setRangeClassInstances(Arrays.asList(rangeInstance));
            simulationCase.setObjectProperties(Arrays.asList(property));
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

}
