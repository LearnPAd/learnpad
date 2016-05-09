/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.notification;

import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;

/**
 * Stores all kind of user actions on wiki platform in the ontology. Ex. Page
 * visits and mutations (add, modify, delete). Same for commments, attachements
 * and feedbacks.
 *
 *
 * @author sandro.emmenegger
 */
public class NotificationLog {

    private static final NotificationLog SINGLETON = new NotificationLog();

    private NotificationLog() {
    }

    public NotificationLog getInstance() {
        return SINGLETON;
    }

    public void logResourceNotification(String modelSetId, 
                                   String resourceId, 
                                   ResourceType resourceType, 
                                   String referringToResourceId, 
                                   String[] modelArtifactIds, 
                                   String userId, 
                                   Long timestamp, 
                                   NotificationActionType action) {
        
        if(resourceType != null){
            
        }
        

    }

}
