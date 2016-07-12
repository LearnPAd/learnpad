package eu.learnpad.simulator.api.rest.corefacade;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.rest.data.UserData;
import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.ProcessStartEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.SimulationEndEvent;
import eu.learnpad.sim.rest.event.impl.SimulationStartEvent;
import eu.learnpad.sim.rest.event.impl.TaskEndEvent;
import eu.learnpad.sim.rest.event.impl.TaskFailedEvent;
import eu.learnpad.sim.rest.event.impl.TaskStartEvent;

/**
 * The purpose of this class is to test that the Simulation Rest Core facade API
 * works correctly. Notably, it is used to check that the data elements
 * transmitted via the API can be serialized and deserialized correctly.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class SimRestAPICoreFacadeTest {

	public static final int PORT = 54321;

	SimRestAPITestCoreFacadeServer server;
	TJWSEmbeddedJaxrsServer container;

	SimRestAPITestCoreFacadeInterface receiverClient;
	ResteasyClient client;

	@Before
	public void initTestServerAndClient() {
		// create server
		server = new SimRestAPITestCoreFacadeServer();

		container = new TJWSEmbeddedJaxrsServer();
		container.setPort(PORT);
		container.getDeployment().getResources().add(server);
		container.start();

		// create client
		client = new ResteasyClientBuilder().build();
		receiverClient = client.target("http://localhost:" + PORT).proxy(
				SimRestAPITestCoreFacadeInterface.class);
	}

	@After
	public void shutdownEngine() {
		client.close();
		container.stop();
	}

	@Test
	public void testSendEvents() {
		// verify that no event has been registered yet
		assertEquals(server.nbSimulationEndEvents, 0);
		assertEquals(server.nbSimulationStartEvents, 0);
		assertEquals(server.nbProcessEndEvents, 0);
		assertEquals(server.nbProcessStartEvents, 0);
		assertEquals(server.nbTaskEndEvents, 0);
		assertEquals(server.nbTaskFailedEvents, 0);
		assertEquals(server.nbTaskStartEvents, 0);
		assertEquals(server.nbSessionScoreUpdateEvents, 0);

		// send events
		try {
			receiverClient.notifySimulationEndEvent(new SimulationEndEvent(
					System.currentTimeMillis(), "1", Arrays.asList("test"),
					null, null));

			receiverClient
			.notifySimulationStartEvent(new SimulationStartEvent(
					System.currentTimeMillis(), "1", Arrays
					.asList("test"), null, null));

			receiverClient.notifyProcessEndEvent(new ProcessEndEvent(System
					.currentTimeMillis(), "1", Arrays.asList("test"), null,
					null, "1"));

			receiverClient.notifyProcessStartEvent(new ProcessStartEvent(
					System.currentTimeMillis(), "1", Arrays.asList("test"),
					null, null, "1"));

			receiverClient.notifyTaskEndEvent(new TaskEndEvent(System
					.currentTimeMillis(), "1", Arrays.asList("test"), null,
					null, "1", "1", Arrays.asList("test"), "test",
					new HashMap<String, Object>()));

			receiverClient.notifyTaskFailedEvent(new TaskFailedEvent(System
					.currentTimeMillis(), "1", Arrays.asList("test"), null,
					null, "1", "1", Arrays.asList("test"), "test",
					new HashMap<String, Object>()));

			receiverClient.notifyTaskStartEvent(new TaskStartEvent(System
					.currentTimeMillis(), "1", Arrays.asList("test"), null,
					null, "1", "1", Arrays.asList("test")));
			receiverClient
			.notifySessionScoreUpdateEvent(new SessionScoreUpdateEvent(
					System.currentTimeMillis(), "1", Arrays
					.asList("test"), null, null, "1", "1", 1L));

		} catch (LpRestException e) {
			e.printStackTrace();
			fail("Exception thrown");
		}

		// verify that all events has been registered
		assertEquals(server.nbSimulationEndEvents, 1);
		assertEquals(server.nbSimulationStartEvents, 1);
		assertEquals(server.nbProcessEndEvents, 1);
		assertEquals(server.nbProcessStartEvents, 1);
		assertEquals(server.nbTaskEndEvents, 1);
		assertEquals(server.nbTaskFailedEvents, 1);
		assertEquals(server.nbTaskStartEvents, 1);
		assertEquals(server.nbSessionScoreUpdateEvents, 1);
	}

	@Test
	public void testUserData() {
		// add a test user
		String userId = "someId";

		UserData inputUserData = new UserData(userId, "Test", "Tset",
				"some bio", "http://none", "http://none");
		server.adduser(inputUserData);

		// request the user via the Rest API
		UserData receivedUserData = receiverClient.getUserData(userId);

		// check that the two UserData are the same
		assertEquals(inputUserData.id, receivedUserData.id);
		assertEquals(inputUserData.firstName, receivedUserData.firstName);
		assertEquals(inputUserData.lastName, receivedUserData.lastName);
		assertEquals(inputUserData.bio, receivedUserData.bio);
		assertEquals(inputUserData.pictureURL, receivedUserData.pictureURL);
		assertEquals(inputUserData.profileURL, receivedUserData.profileURL);
	}

}
