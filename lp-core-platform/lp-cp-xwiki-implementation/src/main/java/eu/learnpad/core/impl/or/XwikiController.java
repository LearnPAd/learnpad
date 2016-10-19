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
package eu.learnpad.core.impl.or;

import java.io.InputStream;
import java.nio.file.Paths;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.Utils;
import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.me.rest.data.KPIsFormat;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.or.Controller;

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
@Named("eu.learnpad.core.impl.or.XwikiController")
@Path("/learnpad/or/corefacade")
public class XwikiController extends Controller implements XWikiRestComponent, Initializable {

	@Inject
	@Named("xwiki")
	private Utils utils;

	@Inject
	private ComponentManager componentManager;

	/*
	 * Note that in this solution the Controllers do not interact each-others,
	 * but each controller directly invokes the BridgesInterfaces (from the
	 * other controllers) it needs. This is not actually what was originally
	 * planned, thus in the future it may change. Also, not sure if this is the
	 * correct way to proceed. I would like to decide in a configuration file
	 * the implementation to bind, and not into the source code. In fact, this
	 * second case implies to rebuild the whole platform at each change.
	 */
	private eu.learnpad.cw.BridgeInterface cw;
	
	private eu.learnpad.dash.BridgeInterface dash;

	private eu.learnpad.sim.BridgeInterface sim;

	@Override
	public void initialize() throws InitializationException {
		try {
			this.bridge = this.componentManager.getInstance(RestResource.class, "or");

			this.cw = this.componentManager.getInstance(RestResource.class, "cw");
			this.dash = this.componentManager.getInstance(RestResource.class, "dash");
			this.sim = this.componentManager.getInstance(RestResource.class, "sim");
		} catch (ComponentLookupException e) {
			throw new InitializationException(e.getMessage(), e);
		}
	}

	@Override
	public byte[] getComments(String modelSetId, String artifactId) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getModel(String modelSetId, ModelSetType type) throws LpRestException {
		String attachmentName = String.format("%s.%s", modelSetId, type);
		String fileName = "adoxx_modelset.xml";
		java.nio.file.Path filePath = Paths.get(fileName);
		return utils.getFileInAttachment(DefaultRestResource.CORE_REPOSITORY_WIKI,
				DefaultRestResource.CORE_REPOSITORY_SPACE, modelSetId, attachmentName, filePath);
	}

	@Override
	public InputStream getExternalKPIs(String modelSetId, String kpisid, KPIsFormat type)
			throws LpRestException {
		String attachmentName = String.format("%s.%s", kpisid, type.toString().toLowerCase());
		return utils.getAttachment(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE, modelSetId, attachmentName);
	}

	@Override
	public void pushKPIValues(String modelSetId, KPIValuesFormat format,
			String businessActorId, InputStream cockpitContent)
			throws LpRestException {
		this.dash.loadKPIValues(modelSetId, format, businessActorId, cockpitContent);
	}

}