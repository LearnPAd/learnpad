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

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import eu.learnpad.verification.utils.Utils;
import eu.learnpad.verificationComponent.utils.ConfigManager;

public class Server {
    private HttpServer server = null;
    
    public Server(){
        this("http://127.0.0.1/rest");
    }
    
    public Server(String endpoint){
        int port = getPort();
        URI baseUri = null;
        if(endpoint.split(":").length==3)
            baseUri = UriBuilder.fromUri(endpoint).build();
        else
            baseUri = UriBuilder.fromUri(endpoint).port(port).build();
        ResourceConfig resourceConfig = new ResourceConfig(BridgeImpl.class);
        server = GrizzlyHttpServerFactory.createHttpServer(baseUri,resourceConfig, false);
    }
    
    private int getPort(){
        int port = 9998;
        try{
            port = Integer.parseInt(new ConfigManager().getElement("PORT"));
        }catch(Exception ex){Utils.log(ex);}
        return port;
    }
    
    public void start() throws Exception{
        server.start();
    }
    
    public static void main(String[] args) {
        try{
            Server server = new Server();
            server.start();
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
    }
}
