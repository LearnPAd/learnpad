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
package eu.learnpad.core.impl.dash;

import java.io.InputStream;

import javax.inject.Named;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.configuration.LearnpadPropertiesConfigurationSource;
import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.dash.BridgeInterface;
import eu.learnpad.dash.rest.data.KPIValuesFormat;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */
@Component
@Named("dash")
public class XwikiBridgeInterfaceRestResource extends DefaultRestResource
		implements BridgeInterface, Initializable {

	@Override
	public void initialize() throws InitializationException {
		this.restPrefix = ((LearnpadPropertiesConfigurationSource) this.configurationSource).getRestPrefix("DASH");
	}

	@Override
	public void importModelSet(String modelSetId, ModelSetType type,
			InputStream modelContent) throws LpRestException {
		// TODO Auto-generated method stub
	}

	@Override
	public void importModelSet(String modelSetId, KPIValuesFormat format,
			InputStream cockpitContent) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

}