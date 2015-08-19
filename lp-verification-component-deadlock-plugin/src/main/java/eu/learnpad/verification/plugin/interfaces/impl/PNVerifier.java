/**
 * LearnPAd - Verification Component - Deadlock Check Plugin
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

package eu.learnpad.verification.plugin.interfaces.impl;

import eu.learnpad.verification.plugin.bpmn.verifier.Engine;
import eu.learnpad.verification.plugin.interfaces.Plugin;
import eu.learnpad.verification.plugin.utils.Utils;

public class PNVerifier implements Plugin {

	@Override
	public String[] getVerificationTypeProvided() {
		return new String[]{"SINGLE DEADLOCK", "ALL DEADLOCKS"};
	}

	@Override
	public String performVerification(String adoxxBPModel, String type) {

		String ret = "";
		try{
			Engine engine = new Engine();
			if(type.equals("SINGLE DEADLOCK")){
				ret = engine.verifyDeadlockAdoxxBPMN(adoxxBPModel);
			} else if(type.equals("ALL DEADLOCKS")){
				ret = engine.verifyAllDeadlocksAdoxxBPMN(adoxxBPModel);
			} else 
				throw new Exception("ERROR: Verification type " + type + " not supported.");
		}catch(Exception ex){
			ex.printStackTrace();
			Utils.log(ex);
			ret = "<Result><Status>ERROR</Status><Description>"+ex.getMessage()+"</Description></Result>";
		}
		return ret;
	}
}
