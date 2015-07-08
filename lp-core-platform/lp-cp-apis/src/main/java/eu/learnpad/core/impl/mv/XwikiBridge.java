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

import javax.inject.Named;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;

import eu.learnpad.mv.Bridge;

@Component
@Named("eu.learnpad.core.impl.mv.XwikiBridge")
@Path("/learnpad/mv")
public abstract class XwikiBridge extends Bridge{

	public XwikiBridge (){
		this(false);
	}

	public XwikiBridge (boolean isCoreFacadeLocal){
		if (isCoreFacadeLocal)
			this.corefacade = new XwikiCoreFacade();
		else
			this.corefacade = new XwikiCoreFacadeRestResource();
	}

}
