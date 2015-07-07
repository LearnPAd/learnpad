/**
 *
 */
package eu.learnpad.simulator.api.impl;

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

import javax.servlet.ServletContextEvent;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;

import eu.learnpad.simulator.Simulator;

/**
 * Helper class to instantiate REST API servlet
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class SimulatorBridgeServletHolder extends ServletHolder {

	public SimulatorBridgeServletHolder(final Simulator simulator,
			ServletContextHandler context, String path) {
		super(new HttpServletDispatcher());

		context.addEventListener(new ResteasyBootstrap() {

			@Override
			public void contextInitialized(ServletContextEvent event) {
				super.contextInitialized(event);
				this.deployment.getDispatcher().getDefaultContextObjects()
						.put(Simulator.class, simulator);
			}

		});

		this.setInitParameter("javax.ws.rs.Application",
				SimulatorBridgeApplication.class.getName());
		this.setInitParameter("resteasy.servlet.mapping.prefix", path);
	}

}
