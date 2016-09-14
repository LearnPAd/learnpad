/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.wiki;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.ontology.util.ArgumentCheck;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Stores all kind of user actions on wiki platform in the ontology. Ex. Page
 * visits and mutations (add, modify, delete). Same for commments, attachments
 * and feedbacks.
 *
 *
 * @author sandro.emmenegger
 */
public class UserActionNotificationLog {

    private static final UserActionNotificationLog SINGLETON = new UserActionNotificationLog();

    private UserActionNotificationLog() {
    }

    public static UserActionNotificationLog getInstance() {
        return SINGLETON;
    }
    
    public static String createResourceId(String modelSetId, String modelId, String artifactId){
        StringBuilder resourceId = new StringBuilder(getStringValue(modelSetId, ""));
        resourceId.append(getStringValue(modelId, ""));
        resourceId.append(getStringValue(artifactId, ""));
        return resourceId.toString();
    }
    
    private static String getStringValue(String s, String defaultValue){
        if(s == null){
            return defaultValue;
        }
        return s;
    }

    /**
     * Create action history log for a wiki page or one of it's annotations
     * (commment, attachment, feedback)
     *
     * @param modelSetId
     * @param modelId
     * @param artifactId model object id linked to the resource
     * @param resourceId either the page id (URL) or the comment id
     * @param resourceType one of page, comment, attachment or feedback
     * to (ex. the page id a comment is referring to)
     * @param userId
     * @param timestamp
     * @param action
     * @return the log entry created in the ontology
     * @throws eu.learnpad.ontology.recommender.RecommenderException
     */
    public Individual logResourceNotification(String modelSetId,
            String modelId,
            String artifactId,
            String resourceId,
            ResourceType resourceType,
            String userId,
            Long timestamp,
            NotificationActionType action) throws RecommenderException {
        
        ArgumentCheck.notNull(modelSetId, "modelSetId in (logResourceNotification");
        ArgumentCheck.notNull(resourceId, "resourceId in (logResourceNotification");

        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(SimpleModelTransformator.getInstance().getLatestModelSetId());
        Individual logTargetInstance = null;    
        
        if (resourceType != null) {
            
            switch (resourceType) {
                case PAGE: {
                    logTargetInstance = getOrCreatePageInstance(model, createResourceId(modelSetId, modelId, artifactId));
                    break;
                }
                case COMMENT: {
                    OntClass annotationClass = model.createClass(APP.NS.XWIKI + "Comment");
                    logTargetInstance = getOrCreateAnnotation(model, createResourceId(modelSetId, modelId, artifactId), resourceId, annotationClass);
                    break;
                }
                case ATTACHMENT: {
                    OntClass annotationClass = model.createClass(APP.NS.XWIKI + "Attachment");
                    logTargetInstance = getOrCreateAnnotation(model, createResourceId(modelSetId, modelId, artifactId), resourceId, annotationClass);
                    break;
                }
                case FEEDBACK: {
                    OntClass annotationClass = model.createClass(APP.NS.XWIKI + "Feedback");
                    logTargetInstance = getOrCreateAnnotation(model, createResourceId(modelSetId, modelId, artifactId), resourceId, annotationClass);
                    break;
                }
            }

            if (logTargetInstance != null) {
                addActionHistory(model, logTargetInstance, userId, timestamp, action);

                Boolean notificationLogPersistenceEnabled = APP.CONF.getBoolean("execution.data.log.persistence.enabled", false);
                if(notificationLogPersistenceEnabled){
                    FileOntAO.getInstance().persistNotificationLogModel();
                }
            }
        }
        return logTargetInstance;
    }
    
    private Individual getOrCreatePageInstance(OntModel model, String pageURL) {

        OntClass pageClass = model.getOntClass(APP.NS.XWIKI + "Page");
        OntProperty pageUrlProperty = model.getOntProperty(APP.NS.XWIKI + "pageHasURL");
        Literal value = model.createTypedLiteral(pageURL);
        List<Individual> pageInstancs = OntUtil.getInstancesWithProperty(model, pageClass, pageUrlProperty, value);
        if (pageInstancs != null && !pageInstancs.isEmpty()) {
            return pageInstancs.get(0);
        }

        //create new instance
        Individual newPageInstance = pageClass.createIndividual(pageClass.getURI()+"_"+UUID.randomUUID());
        newPageInstance.addProperty(pageUrlProperty, value);

        return newPageInstance;
    }

    private void addActionHistory(OntModel model, Individual resource, String userId, Long timestamp, NotificationActionType action) {

        OntClass actionHistoryClass = model.getOntClass(APP.NS.XWIKI + "ActionHistory");
        Individual actionHistoryInstance = actionHistoryClass.createIndividual(APP.NS.EXEC + "ActionHistory_" + UUID.randomUUID());

        //actionAppliedToResource (xwiki:Page or xwiki:Annotation)
        OntProperty actionAppliedToResourceProp = model.getOntProperty(APP.NS.XWIKI + "actionAppliedToResource");
        actionHistoryInstance.addProperty(actionAppliedToResourceProp, resource);

        //user
        OntClass personClass = model.getOntClass(APP.NS.EO + "Person");
        OntProperty mailProp = model.getOntProperty(APP.NS.EMO + "performerHasEmailAddress");
        Literal userIdValue = model.createTypedLiteral(userId);
        List<Individual> persons = OntUtil.getInstancesWithProperty(model, personClass, mailProp, userIdValue);

        if(persons != null && !persons.isEmpty()){
            OntProperty userProp = model.createOntProperty(APP.NS.XWIKI + "user");
            actionHistoryInstance.addProperty(userProp, persons.get(0));
        }

        //actionTimestamp
        OntProperty timestampProp = model.getOntProperty(APP.NS.XWIKI + "actionTimestamp");
        Calendar timestampCalendar = Calendar.getInstance();
        timestampCalendar.setTimeInMillis(timestamp);
        Literal timestampValue = model.createTypedLiteral(timestampCalendar);
        actionHistoryInstance.addProperty(timestampProp, timestampValue);

        //actionType
        OntProperty actionProp = model.getOntProperty(APP.NS.XWIKI + "actionType");
        Literal actionValue = model.createTypedLiteral(action.toString().toLowerCase());
        actionHistoryInstance.addProperty(actionProp, actionValue);
    }

    private Individual getOrCreateAnnotation(OntModel model, String pageUrl, String resourceId, OntClass annotationClass) {
        Individual pageInstance = getOrCreatePageInstance(model, pageUrl);
        
        //lookup or create annotation
        OntProperty pageProperty = model.getOntProperty(APP.NS.XWIKI + "annotationIsMadeToPage");
        List<Individual> instances = OntUtil.getInstancesWithProperty(model, annotationClass, pageProperty, pageInstance);
        if (instances != null && !instances.isEmpty()) {
            return instances.get(0);
        }

        //create new instance
        Individual newInstance = annotationClass.createIndividual(APP.NS.EXEC +annotationClass.getLocalName()+"_" + UUID.randomUUID());
        newInstance.addProperty(pageProperty, pageInstance);
        
        return newInstance;
    }

}
