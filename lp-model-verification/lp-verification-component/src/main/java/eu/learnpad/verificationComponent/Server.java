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

    public static void main(String[] args) {
        try{
            String port = "";
            try{
                port = new ConfigManager().getElement("PORT");
            }catch(Exception ex){Utils.log(ex);port="9998";}
            
            BridgeImpl.initialize();
            URI baseUri = UriBuilder.fromUri("http://127.0.0.1/rest").port(Integer.parseInt(port)).build();
            ResourceConfig resourceConfig = new ResourceConfig(BridgeImpl.class);
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri,resourceConfig, false);
            server.start();
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
    }
}
