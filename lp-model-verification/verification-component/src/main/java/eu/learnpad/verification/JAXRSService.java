/**
 * LearnPAd - Verification Component
 * 
 *  Copyright (C) 2015 Unicam
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Damiano Falcioni - Unicam <damiano.falcioni@gmail.com>
 */

package eu.learnpad.verification;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import eu.learnpad.verification.utils.ConfigManager;
import eu.learnpad.verification.utils.Utils;

@Path("/VerificationComponent")
public class JAXRSService {
    @GET
    @Path("/getSupportedVerifications")
    @Produces(MediaType.TEXT_PLAIN)
    public static String getSupportedVerifications(){
        String ret = "";
        try{
            String[] verificationList = VerificationComponent.getSupportedVerifications();
            for(String verification:verificationList)
                ret += verification + "\n";
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    @PUT
    @Path("/loadModel")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public static String loadModel(byte[] model){
        return VerificationComponent.loadModel(new String(model));
    }
    
    @GET
    @Path("/startVerification")
    @Produces(MediaType.TEXT_PLAIN)
    public static String startVerification(@QueryParam("modelId") String modelId, @QueryParam("verificationType") String verificationType){
        String ret = "";
        try{
            ret = VerificationComponent.startVerification(modelId, verificationType);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    @GET
    @Path("/startSyncVerification")
    @Produces(MediaType.TEXT_PLAIN)
    public static synchronized String startSyncVerification(@QueryParam("modelId") String modelId, @QueryParam("verificationType") String verificationType){
        String ret = "";
        try{
            ret = VerificationComponent.startSyncVerification(modelId, verificationType);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    @GET
    @Path("/getVerificationStatus")
    @Produces(MediaType.TEXT_PLAIN)
    public static String getVerificationStatus(@QueryParam("verificationId") String verificationId){
        String ret = "";
        try{
            ret = VerificationComponent.getVerificationStatus(verificationId);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    @GET
    @Path("/getVerificationResult")
    @Produces(MediaType.TEXT_PLAIN)
    public static String getVerificationResult(@QueryParam("verificationId") String verificationId){
        String ret = "";
        try{
            ret = VerificationComponent.getVerificationResult(verificationId);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    public static void main(String[] args) {
        try{
            String port = "";
            try{
                port = new ConfigManager().getElement("PORT");
            }catch(Exception ex){Utils.log(ex);port="9998";}
            
            URI baseUri = UriBuilder.fromUri("http://127.0.0.1/rest").port(Integer.parseInt(port)).build();
            final ResourceConfig resourceConfig = new ResourceConfig(JAXRSService.class);
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri,resourceConfig, false);
            server.start();
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
    }
}
