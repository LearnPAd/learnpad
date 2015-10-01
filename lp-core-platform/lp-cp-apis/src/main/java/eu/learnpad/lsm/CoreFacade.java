package eu.learnpad.lsm;

import javax.ws.rs.Path;

import eu.learnpad.lsm.rest.NotificationsFromBridge;
import eu.learnpad.lsm.rest.SendInvitations;

@Path("/learnpad/lsm/corefacade")
public interface CoreFacade extends NotificationsFromBridge, SendInvitations{

}
