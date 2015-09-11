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

package eu.learnpad.verification.plugin.interfaces;
/*
 * This interface should be implemented in order to create a valid plugin for the verification component
 */
public interface Plugin {
    /**
     * The method return a list of verification capabilities provided by the plugin
     * @return    an array of string identifying the verification type provided
     */
    public String[] getVerificationTypeProvided();
    
    /**
     * The method perform the verification specified over the provided model. The verification have to be done in a synchronous way. Asynchronous support is provided by the verification component that manage the plugins.
     * @param    model   The model to verify (currently the model passed is the full ADOxx LearnPAd xml)
     * @param    type    The type of verification to perform; one provided by the method getVerificationTypeProvided
     * @return   The verification result in XML format. The root element have to be <Result></Result> 
     */
    public String performVerification(String model, String type);
}
