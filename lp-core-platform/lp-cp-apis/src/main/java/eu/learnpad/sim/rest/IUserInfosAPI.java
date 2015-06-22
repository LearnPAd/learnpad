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
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IUserInfosAPI {

	@GET
	@Path("/")
	public List<String> getUsers();

	@GET
	@Path("/{userid:.*}")
	public UserData getUserData(@PathParam("userid") String userId);
}
