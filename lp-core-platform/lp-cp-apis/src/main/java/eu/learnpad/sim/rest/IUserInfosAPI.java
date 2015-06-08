/**
 *
 */
package eu.learnpad.sim.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.learnpad.sim.rest.data.UserData;

/**
 * @author Tom Jorquera - Linagora
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IUserInfosAPI {

	@GET
	@Path("/learnpad/sim/users")
	public List<String> getUsers();

	@GET
	@Path("/learnpad/sim/users/{userid:.*}")
	public UserData getUserData(@PathParam("userid") String userId);
}
