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

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.qm.BridgeInterface;
import eu.learnpad.qm.Controller;

/*
 * It is not clear yet who is responsible for the instantiation
 * of this class. From what I read from Jean it is supposed to be done
 * automatically when registering the Java component into the XWiki Platform.
 * Thus I do not know how may instances we may actually have. Thus, for the
 * moment I marked it as Singleton.
 *  
 */
@Component
@Singleton
public class XwikiController extends Controller implements XWikiRestComponent{

/*
 * Note that in this solution the Controllers do not interact
 * each-others, but each controller directly invokes the BridgesInterfaces
 * (from the other controllers) it needs. This is not actually what was
 * originally planned, thus in the future it may change.
 */
 
/* Not sure if this is the correct way to proceed.
 * I would like to decide in a configuration file
 * the implementation to bind, and not into the source
 * code. In fact, this second case implies to rebuild the
 * whole platform at each change.	
 */
	@Inject
	@Named("eu.learnpad.core.impl.lsm.XwikiBridgeInterfaceRestResource")
	private eu.learnpad.lsm.Bridge lsm;
	
	public XwikiController (){
		this.bridge = null;
	}

	public XwikiController (BridgeInterface bi){
		this.updateBridgeInterface(bi);
	}
	
	public XwikiController (String bridgeInterfaceHostname,
			int bridgeInterfaceHostPort){
		this.bridge = new XwikiBridgeInterfaceRestResource(bridgeInterfaceHostname, bridgeInterfaceHostPort);
	}
	
    public synchronized void updateBridgeInterface (BridgeInterface bi){
		this.bridge = bi;    
    }
	
	@Override
	public void publish(String questionnairesId, String type,
			byte[] questionnairesFile) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void genrationCompleted(String questionnairesId)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		
	}

}
