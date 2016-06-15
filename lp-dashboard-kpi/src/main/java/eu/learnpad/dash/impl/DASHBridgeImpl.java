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
package eu.learnpad.dash.impl;

import java.io.InputStream;
import java.io.StringReader;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import eu.learnpad.dash.Bridge;
import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.me.rest.data.ModelSetType;


/**
*
* @author gulyx
*/

@Path("/learnpad/ca/bridge/")
@Consumes(MediaType.APPLICATION_XML)
public class DASHBridgeImpl extends Bridge {

	private static Logger log = Logger.getLogger(DASHBridgeImpl.class);

	
	public DASHBridgeImpl(){
// TODO the following line is just a placeholder. It should be fixed.
		this.corefacade = new DASHCoreFacadeRestResource();
		
		log.info(this.getClass().getName() + " instantiated!");
	}
	
	@Override
	public void modelSetImported(String modelSetId, ModelSetType type)
			throws LpRestException {
		log.info("ModelSet " + modelSetId+" ("+type+") has been imported");
	}
	
	@Override
	public void loadKPIValues(String modelSetId, KPIValuesFormat format,
			InputStream cockpitContent) throws LpRestException {
		
		// taken from : http://web.archive.org/web/20140531042945/https://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
		Scanner scanner = new Scanner(cockpitContent).useDelimiter("\\A");
		String cockpitContentAsAString = scanner.hasNext() ? scanner.next() : "-- no contents for the cockpit --";
		log.info("Loaded KPI Values as " + format + " for ModelSet " + modelSetId + "\n" + cockpitContentAsAString);
	}

}
