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

package eu.learnpad.verificationComponent;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;

import org.w3c.dom.Document;

import eu.learnpad.mv.CoreFacade;
import eu.learnpad.mv.rest.data.StatusType;
import eu.learnpad.mv.rest.data.VerificationId;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.rest.data.VerificationStatus;
import eu.learnpad.mv.rest.data.VerificationsAvailable;
import eu.learnpad.verification.VerificationComponent;
import eu.learnpad.verification.utils.Utils;
import eu.learnpad.verification.utils.Utils.LogType;
import eu.learnpad.verificationComponent.utils.ModelUtils;
import eu.learnpad.verificationComponent.utils.XMLUtils;

@Path("")
public class BridgeImpl extends eu.learnpad.mv.Bridge {
    
    public BridgeImpl() {
        String lpModelTypeToDownload = "lpzip";
        this.corefacade = new PlatformFacadeImpl();
        VerificationComponent.setCustomNotifyVerificationEndFunction(customNotifyFactory(this.corefacade));
        VerificationComponent.setCustomGetModelFunction(customGetModelFactory(lpModelTypeToDownload, this.corefacade));
    }
    
    private VerificationComponent.CustomGetModel customGetModelFactory(final String lpModelType, final CoreFacade corefacade){
        VerificationComponent.CustomGetModel myCustomGetModel = new VerificationComponent.CustomGetModel() {
                public String[] getModels(String modelId) throws Exception {
                    byte[] zippedModel = corefacade.getModel(modelId, lpModelType);
                    return ModelUtils.processModel(zippedModel);
                }
            };
        return myCustomGetModel;
    }
    
    private VerificationComponent.CustomNotify customNotifyFactory(final CoreFacade corefacade){
        VerificationComponent.CustomNotify myCustomNotify = new VerificationComponent.CustomNotify() {
                public void notifyVerificationEnd(String verificationId) throws Exception {
                    corefacade.notifyVerification(verificationId);
                }
            };
        return myCustomNotify;
    }
    
    
    @GET
    @Path("/learnpad/mv/bridge/getavailableverifications")
    @Produces(MediaType.APPLICATION_XML)
    public VerificationsAvailable getAvailableVerifications(){
        try{
            String[] verificationList = VerificationComponent.getSupportedVerifications();
            VerificationsAvailable ret = new VerificationsAvailable();
            List<String> va = ret.getVerificationAvailable();
            for(String verification:verificationList)
                va.add(verification);
            
            return ret;
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return null;
    }
    
    @GET
    @Path("/learnpad/mv/bridge/startverification")
    @Produces(MediaType.APPLICATION_XML)
    public VerificationId startVerification(@QueryParam("modelsetid") String modelSetId, @QueryParam("verificationtype") String verificationType){
        VerificationId vid = null;
        try{
            String ret = VerificationComponent.startVerification(modelSetId, verificationType);
            vid = new VerificationId();
            vid.setId(ret);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return vid;
    }
    
    @GET
    @Path("/learnpad/mv/bridge/getverificationstatus")
    @Produces(MediaType.APPLICATION_XML)
    public VerificationStatus getVerificationStatus(@QueryParam("verificationprocessid") String verificationProcessId){
        try{
            String ret = VerificationComponent.getVerificationStatus(verificationProcessId);
            VerificationStatus vs = new VerificationStatus();
            vs.setStatus(StatusType.fromValue(ret));
            return vs;
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return null;
    }
    
    @GET
    @Path("/learnpad/mv/bridge/getverificationresult")
    @Produces(MediaType.APPLICATION_XML)
    public VerificationResults getVerificationResult(@QueryParam("verificationprocessid") String verificationProcessId){
        VerificationResults ret = null;
        try{
            String res = VerificationComponent.getVerificationResult(verificationProcessId);
            Document resX = XMLUtils.getXmlDocFromString(res);
            JAXBContext jaxbContext = JAXBContext.newInstance(VerificationResults.class);
            
            ret = (VerificationResults)jaxbContext.createUnmarshaller().unmarshal(resX);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    //TEST METHODS: set the LP platform address to http://127.0.0.1:9998/rest in the config file in order to use them
    @GET
    @Path("/learnpad/mv/corefacade/getmodel/{modelsetid}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getModelTEST(@PathParam("modelsetid") String modelSetId, @QueryParam("type") String type){
        try{
            //return eu.learnpad.verificationComponent.utils.IOUtils.readFile("D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\TitoloUnico21.10.15-rev2.xml");
            String modelB64 = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxicG1uMjpkZWZpbml0aW9ucyB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIiB4bWxuczpicG1uMj0iaHR0cDovL3d3dy5vbWcub3JnL3NwZWMvQlBNTi8yMDEwMDUyNC9NT0RFTCIgeG1sbnM6YnBtbmRpPSJodHRwOi8vd3d3Lm9tZy5vcmcvc3BlYy9CUE1OLzIwMTAwNTI0L0RJIiB4bWxuczpkYz0iaHR0cDovL3d3dy5vbWcub3JnL3NwZWMvREQvMjAxMDA1MjQvREMiIHhtbG5zOmRpPSJodHRwOi8vd3d3Lm9tZy5vcmcvc3BlYy9ERC8yMDEwMDUyNC9ESSIgeG1sbnM6ZXh0PSJodHRwOi8vb3JnLmVjbGlwc2UuYnBtbjIvZXh0IiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIGlkPSJEZWZpbml0aW9uc18xIiBleHBvcnRlcj0ib3JnLmVjbGlwc2UuYnBtbjIubW9kZWxlci5jb3JlIiBleHBvcnRlclZlcnNpb249IjEuMi4xLjIwMTUwNzA4MTUwNyIgdGFyZ2V0TmFtZXNwYWNlPSJodHRwOi8vb3JnLmVjbGlwc2UuYnBtbjIvZGVmYXVsdC9wcm9jZXNzIj4NCiAgPGJwbW4yOnByb2Nlc3MgaWQ9InRlc3RfMTIiIG5hbWU9IkRlZmF1bHQgUHJvY2VzcyIgaXNFeGVjdXRhYmxlPSJmYWxzZSI+DQogICAgPGJwbW4yOnN0YXJ0RXZlbnQgaWQ9IlN0YXJ0RXZlbnRfMSIgbmFtZT0iU3RhcnQgRXZlbnQgMSI+DQogICAgICA8YnBtbjI6b3V0Z29pbmc+U2VxdWVuY2VGbG93XzE8L2JwbW4yOm91dGdvaW5nPg0KICAgIDwvYnBtbjI6c3RhcnRFdmVudD4NCiAgICA8YnBtbjI6ZW5kRXZlbnQgaWQ9IkVuZEV2ZW50XzEiIG5hbWU9IkVuZCBFdmVudCAxIj4NCiAgICAgIDxicG1uMjppbmNvbWluZz5TZXF1ZW5jZUZsb3dfNTwvYnBtbjI6aW5jb21pbmc+DQogICAgPC9icG1uMjplbmRFdmVudD4NCiAgICA8YnBtbjI6dGFzayBpZD0iVGFza18xIiBuYW1lPSJUYXNrIDEiPg0KICAgICAgPGJwbW4yOmluY29taW5nPlNlcXVlbmNlRmxvd18xPC9icG1uMjppbmNvbWluZz4NCiAgICAgIDxicG1uMjpvdXRnb2luZz5TZXF1ZW5jZUZsb3dfNTwvYnBtbjI6b3V0Z29pbmc+DQogICAgPC9icG1uMjp0YXNrPg0KICAgIDxicG1uMjpzZXF1ZW5jZUZsb3cgaWQ9IlNlcXVlbmNlRmxvd18xIiBzb3VyY2VSZWY9IlN0YXJ0RXZlbnRfMSIgdGFyZ2V0UmVmPSJUYXNrXzEiLz4NCiAgICA8YnBtbjI6c2VxdWVuY2VGbG93IGlkPSJTZXF1ZW5jZUZsb3dfNSIgc291cmNlUmVmPSJUYXNrXzEiIHRhcmdldFJlZj0iRW5kRXZlbnRfMSIvPg0KICA8L2JwbW4yOnByb2Nlc3M+DQogIDxicG1uZGk6QlBNTkRpYWdyYW0gaWQ9IkJQTU5EaWFncmFtXzEiIG5hbWU9IkRlZmF1bHQgUHJvY2VzcyBEaWFncmFtIj4NCiAgICA8YnBtbmRpOkJQTU5QbGFuZSBpZD0iQlBNTlBsYW5lXzEiIGJwbW5FbGVtZW50PSJ0ZXN0XzEyIj4NCiAgICAgIDxicG1uZGk6QlBNTlNoYXBlIGlkPSJCUE1OU2hhcGVfMSIgYnBtbkVsZW1lbnQ9IlN0YXJ0RXZlbnRfMSI+DQogICAgICAgIDxkYzpCb3VuZHMgaGVpZ2h0PSIzNi4wIiB3aWR0aD0iMzYuMCIgeD0iMTAwLjAiIHk9IjEwMC4wIi8+DQogICAgICAgIDxicG1uZGk6QlBNTkxhYmVsIGlkPSJCUE1OTGFiZWxfMSIgbGFiZWxTdHlsZT0iQlBNTkxhYmVsU3R5bGVfMSI+DQogICAgICAgICAgPGRjOkJvdW5kcyBoZWlnaHQ9IjE1LjAiIHdpZHRoPSI2OC4wIiB4PSI4NC4wIiB5PSIxMzYuMCIvPg0KICAgICAgICA8L2JwbW5kaTpCUE1OTGFiZWw+DQogICAgICA8L2JwbW5kaTpCUE1OU2hhcGU+DQogICAgICA8YnBtbmRpOkJQTU5TaGFwZSBpZD0iQlBNTlNoYXBlXzIiIGJwbW5FbGVtZW50PSJFbmRFdmVudF8xIj4NCiAgICAgICAgPGRjOkJvdW5kcyBoZWlnaHQ9IjM2LjAiIHdpZHRoPSIzNi4wIiB4PSI1MDAuMCIgeT0iMTAwLjAiLz4NCiAgICAgICAgPGJwbW5kaTpCUE1OTGFiZWwgaWQ9IkJQTU5MYWJlbF8yIiBsYWJlbFN0eWxlPSJCUE1OTGFiZWxTdHlsZV8xIj4NCiAgICAgICAgICA8ZGM6Qm91bmRzIGhlaWdodD0iMTUuMCIgd2lkdGg9IjY1LjAiIHg9IjQ4Ni4wIiB5PSIxMzYuMCIvPg0KICAgICAgICA8L2JwbW5kaTpCUE1OTGFiZWw+DQogICAgICA8L2JwbW5kaTpCUE1OU2hhcGU+DQogICAgICA8YnBtbmRpOkJQTU5TaGFwZSBpZD0iQlBNTlNoYXBlX1Rhc2tfMSIgYnBtbkVsZW1lbnQ9IlRhc2tfMSI+DQogICAgICAgIDxkYzpCb3VuZHMgaGVpZ2h0PSI1MC4wIiB3aWR0aD0iMTEwLjAiIHg9IjI1MC4wIiB5PSI5My4wIi8+DQogICAgICAgIDxicG1uZGk6QlBNTkxhYmVsIGlkPSJCUE1OTGFiZWxfOCIgbGFiZWxTdHlsZT0iQlBNTkxhYmVsU3R5bGVfMSI+DQogICAgICAgICAgPGRjOkJvdW5kcyBoZWlnaHQ9IjE1LjAiIHdpZHRoPSIzNy4wIiB4PSIyODYuMCIgeT0iMTEwLjAiLz4NCiAgICAgICAgPC9icG1uZGk6QlBNTkxhYmVsPg0KICAgICAgPC9icG1uZGk6QlBNTlNoYXBlPg0KICAgICAgPGJwbW5kaTpCUE1ORWRnZSBpZD0iQlBNTkVkZ2VfU2VxdWVuY2VGbG93XzEiIGJwbW5FbGVtZW50PSJTZXF1ZW5jZUZsb3dfMSIgc291cmNlRWxlbWVudD0iQlBNTlNoYXBlXzEiIHRhcmdldEVsZW1lbnQ9IkJQTU5TaGFwZV9UYXNrXzEiPg0KICAgICAgICA8ZGk6d2F5cG9pbnQgeHNpOnR5cGU9ImRjOlBvaW50IiB4PSIxMzYuMCIgeT0iMTE4LjAiLz4NCiAgICAgICAgPGRpOndheXBvaW50IHhzaTp0eXBlPSJkYzpQb2ludCIgeD0iMTg4LjAiIHk9IjExOC4wIi8+DQogICAgICAgIDxkaTp3YXlwb2ludCB4c2k6dHlwZT0iZGM6UG9pbnQiIHg9IjI1MC4wIiB5PSIxMTguMCIvPg0KICAgICAgICA8YnBtbmRpOkJQTU5MYWJlbCBpZD0iQlBNTkxhYmVsXzkiIGxhYmVsU3R5bGU9IkJQTU5MYWJlbFN0eWxlXzEiLz4NCiAgICAgIDwvYnBtbmRpOkJQTU5FZGdlPg0KICAgICAgPGJwbW5kaTpCUE1ORWRnZSBpZD0iQlBNTkVkZ2VfU2VxdWVuY2VGbG93XzUiIGJwbW5FbGVtZW50PSJTZXF1ZW5jZUZsb3dfNSIgc291cmNlRWxlbWVudD0iQlBNTlNoYXBlX1Rhc2tfMSIgdGFyZ2V0RWxlbWVudD0iQlBNTlNoYXBlXzIiPg0KICAgICAgICA8ZGk6d2F5cG9pbnQgeHNpOnR5cGU9ImRjOlBvaW50IiB4PSIzNjAuMCIgeT0iMTE4LjAiLz4NCiAgICAgICAgPGRpOndheXBvaW50IHhzaTp0eXBlPSJkYzpQb2ludCIgeD0iNDMwLjAiIHk9IjExOC4wIi8+DQogICAgICAgIDxkaTp3YXlwb2ludCB4c2k6dHlwZT0iZGM6UG9pbnQiIHg9IjUwMC4wIiB5PSIxMTguMCIvPg0KICAgICAgICA8YnBtbmRpOkJQTU5MYWJlbCBpZD0iQlBNTkxhYmVsXzEwIiBsYWJlbFN0eWxlPSJCUE1OTGFiZWxTdHlsZV8xIi8+DQogICAgICA8L2JwbW5kaTpCUE1ORWRnZT4NCiAgICA8L2JwbW5kaTpCUE1OUGxhbmU+DQogICAgPGJwbW5kaTpCUE1OTGFiZWxTdHlsZSBpZD0iQlBNTkxhYmVsU3R5bGVfMSI+DQogICAgICA8ZGM6Rm9udCBuYW1lPSJhcmlhbCIgc2l6ZT0iOS4wIi8+DQogICAgPC9icG1uZGk6QlBNTkxhYmVsU3R5bGU+DQogIDwvYnBtbmRpOkJQTU5EaWFncmFtPg0KPC9icG1uMjpkZWZpbml0aW9ucz4=";
            return javax.xml.bind.DatatypeConverter.parseBase64Binary(modelB64);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return new byte[0];
    }
    
    @PUT
    @Path("/learnpad/mv/corefacade/notifyverification/{verificationprocessid}")
    public void notifyVerificationTEST(@PathParam("verificationprocessid") String verificationProcessId){
        System.out.println("Verification completed: "+verificationProcessId);
        Utils.log("Verification completed: "+verificationProcessId, LogType.INFO);
    }
    
    @PUT
    @Path("/learnpad/mv/bridge/loadmodel")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public static String loadModel(byte[] model){
        try{
            return VerificationComponent.loadModel(ModelUtils.processModel(model));
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return "";
    }
    
    @GET
    @Path("/learnpad/mv/bridge/startsyncverification")
    @Produces(MediaType.TEXT_PLAIN)
    public String startSyncVerification(@QueryParam("modelsetid") String modelSetId, @QueryParam("verificationtype") String verificationType){
        String ret = "";
        try{
            ret = VerificationComponent.startSyncVerification(modelSetId, verificationType);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
}
