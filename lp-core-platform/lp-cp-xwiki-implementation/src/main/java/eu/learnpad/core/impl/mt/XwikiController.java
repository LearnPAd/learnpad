/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.core.impl.mt;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.mt.BridgeInterface;
import eu.learnpad.mt.Controller;

@Component
@Singleton
@Named("eu.learnpad.core.impl.mt.XwikiController")
@Path("/learnpad/mt/corefacade")
public class XwikiController extends Controller implements XWikiRestComponent,
		Initializable {
	private boolean initialized = false;

	@Override
	public synchronized void initialize() throws InitializationException {
		if (!this.initialized) {
			this.bridge = new XwikiBridgeInterfaceRestResource();
			this.initialized = true;

		}
	}

	public synchronized void updateBridgeInterface(BridgeInterface bi) {
		this.bridge = bi;
	}
}
