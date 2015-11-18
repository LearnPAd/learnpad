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

import eu.learnpad.exception.LpRestException;
import eu.learnpad.verification.utils.Utils;
import eu.learnpad.verificationComponent.utils.ConfigManager;
import eu.learnpad.verificationComponent.utils.NETUtils;


public class PlatformFacadeImpl implements eu.learnpad.mv.CoreFacade {

    @Override
    public byte[] getModel(String modelSetId, String type) throws LpRestException {
        String url = "";
        try{
            url = new ConfigManager().getElement("LPHOSTNAME");
        }catch(Exception ex){Utils.log(ex);return new byte[0];}
        
        url = "http://" + url + "/learnpad/mv/getmodel/"+modelSetId+"?type="+type;
        byte[] ret = new byte[0];
        try{
            ret = NETUtils.sendHTTPGET(url, null, false, false);
        }catch(Exception ex){Utils.log(ex);}
        
        return ret;
    }

    @Override
    public void notifyVerification(String verificationProcessId) throws LpRestException {
        String url = "";
        try{
            url = new ConfigManager().getElement("LPHOSTNAME");
        }catch(Exception ex){Utils.log(ex);return;}
        
        url = "http://" + url + "/learnpad/mv/notifyverification/"+verificationProcessId;
        
        try{
            NETUtils.sendHTTP(url, "PUT", null, null, false, false);
        }catch(Exception ex){Utils.log(ex);}
    }
}
