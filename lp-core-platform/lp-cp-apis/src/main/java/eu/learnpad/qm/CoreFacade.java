package eu.learnpad.qm;

import javax.ws.rs.Path;

import eu.learnpad.qm.rest.NotificationsFromBridge;
import eu.learnpad.qm.rest.PublishQuestionnaire;

@Path("/learnpad/qm/corefacade")
public interface CoreFacade extends PublishQuestionnaire, NotificationsFromBridge{

}
