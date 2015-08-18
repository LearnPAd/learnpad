package eu.learnpad.verification.plugin.interfaces.impl;

import eu.learnpad.verification.plugin.bpmn.verifier.Engine;
import eu.learnpad.verification.plugin.interfaces.Plugin;

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
			//FIXME: log
			ret = "<Result><Status>ERROR</Status><Description>"+ex.getMessage()+"</Description></Result>";
		}
		return ret;
	}
}
