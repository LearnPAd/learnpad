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
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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

@Path("/learnpad/dash/bridge/")
@Consumes(MediaType.APPLICATION_XML)
public class DASHBridgeImpl extends Bridge {

	private static Logger log = Logger.getLogger(DASHBridgeImpl.class);
	private static String url = "http://localhost/it-has-not-been-set";

	public DASHBridgeImpl() {
		// TODO the following line is just a placeholder. It should be fixed.
		this.corefacade = new DASHCoreFacadeRestResource();
		
		org.apache.log4j.BasicConfigurator.configure();
		log.info(this.getClass().getName() + " instantiated!");
	}

	@Override
	@Path("/modelsetimported/{modelsetid}")
	@PUT
	public void modelSetImported(@PathParam("modelsetid") String modelSetId,
			@QueryParam("type") @DefaultValue("ADOXX") ModelSetType type)
			throws LpRestException {
		log.info("ModelSet " + modelSetId + " (" + type + ") has been imported");
	}

	@Override
	@PUT
	@Path("/loadkpivalues/{modelsetid}")
	@Consumes(MediaType.APPLICATION_XML)
	public void loadKPIValues(
			@PathParam("modelsetid") String modelSetId,
			@QueryParam("format") @DefaultValue("ADOXXCockpit") KPIValuesFormat format,
			@QueryParam("businessactor") String businessActorId,
			InputStream cockpitContent) throws LpRestException {
		// taken from :
		// http://web.archive.org/web/20140531042945/https://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
		Scanner scanner = new Scanner(cockpitContent).useDelimiter("\\A");
		String cockpitContentAsAString = scanner.hasNext() ? scanner.next()
				: "-- no contents for the cockpit --";
		log.info("Loaded KPI Values as " + format + " for ModelSet "
				+ modelSetId + "\n" + cockpitContentAsAString);
	}

	@Override
	@Path("/view/{modelsetid}")
	@GET
	public String getKPIValuesView(@PathParam("modelsetid") String modelSetId,
			@QueryParam("businessactor") String businessActorId) throws LpRestException {
		log.info("Requeste URL for : ModelSet " + modelSetId + "; businessActorId " + businessActorId );

		return url;
	}

	public static void setCockpitURL(String url) {
		DASHBridgeImpl.url = url; 
	}

}
