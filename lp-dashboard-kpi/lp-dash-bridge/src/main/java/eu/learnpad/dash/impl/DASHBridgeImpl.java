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
import eu.learnpad.dash.config.APP;
import eu.learnpad.dash.config.PropertyUtil;
import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 *
 * @author gulyx
 */
@Path("/learnpad/dash/bridge/")
@Consumes(MediaType.APPLICATION_XML)
public class DASHBridgeImpl extends Bridge {

    private static Logger log = Logger.getLogger(DASHBridgeImpl.class);
    private static String url = "http://localhost/it-has-not-been-set/";
    
    private String configFileLocationLabel = "config.file.location";
    private PropertyUtil conf;
    
    
    public DASHBridgeImpl() {
    	
    	String confFileName = System.getProperty(configFileLocationLabel);
    	if (confFileName == null){
    		this.conf=new PropertyUtil();
    	}else{
    		this.conf=new PropertyUtil(confFileName);
    	}
    	
        // TODO the following line is just a placeholder. It should be fixed.
        this.corefacade = new DASHCoreFacadeRestResource();

        org.apache.log4j.BasicConfigurator.configure();
        log.info(this.getClass().getName() + " instantiated!");
        
//        this.url = APP.CONF.getString("lp-dash.cockpit.url", this.url);
        this.url = this.conf.getProperty("lp-dash.cockpit.url", this.url);
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
        log.info("Started to load dashboard KPI values for business actor with id: " + businessActorId );
        
        File cockpitXmlFile = getKpiDashboardFile(businessActorId);
        if (cockpitXmlFile != null) {
            try {
                Files.copy(cockpitContent, cockpitXmlFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
            	String message = "Cannot create dashboard kpi file from inputstream during loadKPIValues method call."; 
                log.error(message, ex);
    			throw new LpRestExceptionXWikiImpl(message + ex.getMessage(), ex);
            }
        }
    }

    @Override
    @Path("/view/{modelsetid}")
    @GET
    public String getKPIValuesView(@PathParam("modelsetid") String modelSetId,
            @QueryParam("businessactor") String businessActorId) throws LpRestException {
        log.info("Request dashboard URL for : ModelSet " + modelSetId + "; businessActorId " + businessActorId);
        
        return url+"?businessActorId="+businessActorId;
    }

    public static void setCockpitURL(String url) {
        DASHBridgeImpl.url = url;
    }

    private File getKpiDashboardFile(String businessActorId) {
        File kpiDashboardWorkingFolder;
        File cockpitXmlFile = null;
        try {
            File baseWorkingDirectory = this.conf.getWorkingDirectory();
            if(baseWorkingDirectory == null){
                return null;
            }
            String folder = this.conf.getProperty("lp-dash.kpi.dashboard.data.folder.relative"); 
            java.nio.file.Path kpiDashboardWorkingFolderPath = Paths.get(baseWorkingDirectory.toString(), folder);
            kpiDashboardWorkingFolder = kpiDashboardWorkingFolderPath.toFile();
            if (!kpiDashboardWorkingFolder.exists()) {
                kpiDashboardWorkingFolder.mkdirs();
            }
            cockpitXmlFile = new File(kpiDashboardWorkingFolder, businessActorId + "_cockpit.xml");
            if (!cockpitXmlFile.exists()) {
                cockpitXmlFile.createNewFile();
            }

        } catch (Exception ex) {
            log.warn("Cannot create KPI dashboard file for business actor: " + businessActorId, ex);
        }
        return cockpitXmlFile;
    }

}
