package eu.learnpad.verification.plugin.bpmn.verifier;

import java.io.File;
import java.util.ArrayList;

import eu.learnpad.verification.plugin.pn.PetriNet;
import eu.learnpad.verification.plugin.pn.PetriNet.PL;
import eu.learnpad.verification.plugin.pn.impexp.PNExport;
import eu.learnpad.verification.plugin.pn.impexp.PNImport;
import eu.learnpad.verification.plugin.pn.modelcheckers.LOLA;
import eu.learnpad.verification.plugin.utils.IOUtils;
import eu.learnpad.verification.plugin.utils.XMLUtils;

public class Engine {

	private String modelCheckerExePath = "";
	
	public Engine() throws Exception{
		initializeLOLAEngine();
	}
	private void initializeLOLAEngine() throws Exception{
		boolean isWindowsOS = System.getProperty("os.name").toLowerCase().contains("windows");
		String tmpFolder = System.getProperty("java.io.tmpdir");
		if(isWindowsOS){
			if(!new File(tmpFolder + "lola-2.0-cygwin.exe").exists()){
				IOUtils.writeFile(IOUtils.toByteArray(this.getClass().getResourceAsStream("exeLola/lola-2.0-cygwin.exe")), tmpFolder+"lola-2.0-cygwin.exe", false);
				IOUtils.writeFile(IOUtils.toByteArray(this.getClass().getResourceAsStream("exeLola/cyggcc_s-1.dll")), tmpFolder+"cyggcc_s-1.dll", false);
				IOUtils.writeFile(IOUtils.toByteArray(this.getClass().getResourceAsStream("exeLola/cygstdc++-6.dll")), tmpFolder+"cygstdc++-6.dll", false);
				IOUtils.writeFile(IOUtils.toByteArray(this.getClass().getResourceAsStream("exeLola/cygwin1.dll")), tmpFolder+"cygwin1.dll", false);
			}
		} else {
			if(!new File(tmpFolder + "lola").exists()){
				IOUtils.writeFile(IOUtils.toByteArray(this.getClass().getResourceAsStream("exeLola/lola")), tmpFolder+"lola", false);
			}
		}
		
		modelCheckerExePath = tmpFolder + ((isWindowsOS)?"lola-2.0-cygwin.exe":"lola");
	}
	
	public String verifyDeadlockBPMN(String bpmnModel) throws Exception{
		PetriNet pn = PNImport.generateFromBPMN(XMLUtils.getXmlDocFromString(bpmnModel));
		return verifyDeadlock(pn);
	}
	
	public String verifyDeadlockAdoxxBPMN(String adoxxBPModel) throws Exception{
		PetriNet[] pnList = PNImport.generateFromAdoxxBPMN(XMLUtils.getXmlDocFromString(adoxxBPModel));
		if(pnList.length==0)
			throw new Exception("ERROR: No BPMN2.0 model provided.");
		String ret = "";
		for(PetriNet pn: pnList)
			ret += verifyDeadlock(pn);
		return ret;
	}
	
	private String verifyDeadlock(PetriNet pn) throws Exception{
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		
		String modelToVerify = PNExport.exportTo_LOLA(pn);
		String propertyToVerify = PNExport.exportTo_LOLA_property_DeadlockPresence(pn);
		
		ArrayList<String[]> counterExampleTraceList = new ArrayList<String[]>();
		String out = LOLA.sync_getVerificationOutput(modelCheckerExePath, modelToVerify, propertyToVerify);
		
		boolean resultOK = LOLA.isPropertyVerified(out);
		if(resultOK)
			counterExampleTraceList.add(LOLA.getCounterExample(out, pn));
		
		return formatResult(counterExampleTraceList, pn);
	}
	
	public String verifyAllDeadlocksBPMN(String bpmnModel) throws Exception{
		PetriNet pn = PNImport.generateFromBPMN(XMLUtils.getXmlDocFromString(bpmnModel));
		return verifyAllDeadlocks(pn);
	}
	
	public String verifyAllDeadlocksAdoxxBPMN(String adoxxBPModel) throws Exception{
		PetriNet[] pnList = PNImport.generateFromAdoxxBPMN(XMLUtils.getXmlDocFromString(adoxxBPModel));
		if(pnList.length==0)
			throw new Exception("ERROR: No BPMN2.0 model provided.");
		String ret = "";
		for(PetriNet pn: pnList)
			ret += verifyAllDeadlocks(pn);
		return ret;
	}
	
	private String verifyAllDeadlocks(PetriNet pn) throws Exception{
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		
		ArrayList<PL> endPLList = pn.getEndList_safe();
		
		String modelToVerify = PNExport.exportTo_LOLA(pn);
		
		ArrayList<String[]> counterExampleTraceList = new ArrayList<String[]>();
		
		while(true){
			String propertyToVerify = PNExport.exportTo_LOLA_property_DeadlockPresence(pn);
			String out = LOLA.sync_getVerificationOutput(modelCheckerExePath, modelToVerify, propertyToVerify);
			boolean deadlockPresent = LOLA.isPropertyVerified(out);
			if(!deadlockPresent)
				break;
			String[] counterExample = LOLA.getCounterExample(out, pn);
			counterExampleTraceList.add(counterExample);
			String[] counterExampleObjList = counterExample[counterExample.length-1].split(" ");
			for(String counterExampleObj: counterExampleObjList)
				if(!endPLList.contains(pn.getPlace(counterExampleObj)))
					pn.getPlace(counterExampleObj).excludeFromDeadlockCheck=true;
		}
		
		return formatResult(counterExampleTraceList, pn);
	}
	
	private String formatResult(ArrayList<String[]> counterExampleTraceList, PetriNet pn) throws Exception{
		/*
	 	<Result>
	 		<PNName></PNName>
	 		<Status></Status>
	 		<Description></Description>
	 		<CounterExampleTrace>
	 			<Step num=1>
	 				<ObjectID></ObjectID>
	 				<ObjectID></ObjectID>
	 				<ObjectID></ObjectID>
	 			</Step>
	 			<Step num=2>
	 				<ObjectID></ObjectID>
	 				<ObjectID></ObjectID>
	 			</Step>
	 		</CounterExampleTrace>
	 		<CounterExampleTrace></CounterExampleTrace>
	 	</Result>
		 */
		
		
		String status = (counterExampleTraceList.size()==0)?"OK":"KO";
		String description = (counterExampleTraceList.size()==0)?"No deadlock found!":"The model has deadlock!";
		String ret = "<Result><PNName>"+pn.name+"</PNName><Status>"+status+"</Status><Description>"+description+"</Description>";
		for(String[] counterExampleTrace: counterExampleTraceList){
			ret += "<CounterExampleTrace>";
			
			for(int i=0; i<counterExampleTrace.length;i++){
				ret += "<Step num="+i+">";
				String[] objList = counterExampleTrace[i].split(" ");
				ArrayList<String> objProcessed = new ArrayList<String>();
				for(String obj: objList){
					if(pn.getPlace(obj)==null)
						throw new Exception("ERROR: place " + obj + " not found");
					String objDesc = pn.getPlace(obj).desciption;
					if(objProcessed.contains(objDesc))
						continue;
					objProcessed.add(objDesc);
					ret += "<ObjectID>"+objDesc+"</ObjectID>";
				}
				ret += "</Step>";
			}
			ret += "</CounterExampleTrace>";
		}
		ret += "</Result>";
			
		return ret;
	}
	
	/*
	public static void main(String[] args) {	
		try {
			String bpmnUrl = "D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_adoxx_2.xml";
			String bpmnModel = new String(IOUtils.readFile(bpmnUrl));
			
			Engine engine = new Engine();
			//System.out.println(engine.verifyDeadlock(bpmnModel));
			System.out.println(engine.verifyAllDeadlocksAdoxxBPMN(bpmnModel));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
