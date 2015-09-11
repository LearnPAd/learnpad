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

package eu.learnpad.verification.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ConfigManager {
    
    public String defaultConfigFile = "config.txt";
    private java.util.HashMap<String,String> elementList = null;
    

    public ConfigManager() throws Exception {
        elementList = new HashMap<String, String>();

        BufferedReader input = new BufferedReader(new InputStreamReader(ConfigManager.class.getResourceAsStream(defaultConfigFile)));
        String strLine;
        while ((strLine = input.readLine()) != null)
            if(strLine.contains("=") && !strLine.startsWith("//") && !strLine.startsWith("'") && !strLine.startsWith("#"))
                elementList.put(strLine.substring(0, strLine.indexOf("=")), strLine.substring(strLine.indexOf("=") + 1, strLine.length()));
        input.close();
    }
    
    public String getElement(String element){
        if(elementList.containsKey(element))
            return elementList.get(element);
        return "";
    }
}
