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
package eu.learnpad.core.impl.mv;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.mv.Controller;

@Component
@Named("eu.learnpad.core.impl.mv.XwikiController")
@Path("/learnpad/mv")
public class XwikiController extends Controller implements XWikiRestComponent {

	@Inject
	Logger logger;

	public XwikiController() {
		this(false);
	}

	public XwikiController(boolean isBridgeInterfaceLocal) {
		if (isBridgeInterfaceLocal)
			this.bridge = new XwikiBridgeInterface();
		else
			this.bridge = new XwikiBridgeInterfaceRestResource();
	}

	@Override
	public byte[] getModel(String modelSetId, String type)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyVerification(String verificationProcessId)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		
	}

}
