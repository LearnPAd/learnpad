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
package eu.learnpad.core.impl.qm;

import javax.inject.Named;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.qm.Bridge;
import eu.learnpad.qm.CoreFacade;

@Component
@Named("eu.learnpad.core.impl.qm.XwikiBridge")
@Path("/learnpad/qm/bridge")
public abstract class XwikiBridge extends Bridge implements XWikiRestComponent{

	public XwikiBridge (){
		this.corefacade = null;
	}

	public XwikiBridge (CoreFacade cf){
		this.updateCoreFacade(cf);
	}

	public XwikiBridge (String coreFacadeHostname,
			int coreFacadeHostPort){
		this.corefacade = new XwikiCoreFacadeRestResource(coreFacadeHostname, coreFacadeHostPort);
	}
	
    public synchronized void updateCoreFacade (CoreFacade cf){
		this.corefacade = cf;    	
    }

}
