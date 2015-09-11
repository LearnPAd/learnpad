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

package eu.learnpad.verification.plugin.bpmn.verifier;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;

import eu.learnpad.verification.plugin.pn.PetriNet;
import eu.learnpad.verification.plugin.pn.PetriNet.PL;
import eu.learnpad.verification.plugin.pn.impexp.PNExport;
import eu.learnpad.verification.plugin.pn.impexp.PNImport;
import eu.learnpad.verification.plugin.pn.modelcheckers.LOLA;
import eu.learnpad.verification.plugin.utils.IOUtils;
import eu.learnpad.verification.plugin.utils.Utils;
import eu.learnpad.verification.plugin.utils.XMLUtils;

public class Engine {

    private String modelCheckerExePath = "";
    
    public Engine() throws Exception{
        initializeLOLAEngine();
    }
    
    private void initializeLOLAEngine() throws Exception{
        boolean isWindowsOS = System.getProperty("os.name").toLowerCase().contains("windows");
        String tmpFolder = System.getProperty("java.io.tmpdir")+"/";
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
                new File(tmpFolder + "lola").setExecutable(true);
            }
        }
        
        modelCheckerExePath = tmpFolder + ((isWindowsOS)?"lola-2.0-cygwin.exe":"lola");
    }
    
    public String verifyDeadlock(String model, boolean checkAllDeadlock) throws Exception{
        PetriNet[] pnList = generatePN(model);
        String ret = "";
        for(PetriNet pn: pnList)
            if(checkAllDeadlock)
                ret += verifyAllDeadlocks(pn);
            else
                ret += verifySingleDeadlock(pn);
        
        return ret;
    }
    
    public String verifyUnboundedness(String model, boolean checkAllUnboundedness) throws Exception{
        PetriNet[] pnList = generatePN(model);
        String ret = "";
        for(PetriNet pn: pnList)
            if(checkAllUnboundedness)
                ret += verifyAllUnboundedness(pn);
            else
                ret += verifyEndUnboundedness(pn);
        return ret;
    }
    
    private PetriNet[] generatePN(String model) throws Exception{
        PetriNet[] pnList = new PetriNet[0];
        Document xmlModel = XMLUtils.getXmlDocFromString(model);
        
        if(PNImport.isOMGBPMN2(xmlModel))
            pnList = new PetriNet[]{PNImport.generateFromBPMN(xmlModel)};
        else if(PNImport.isADOXX(xmlModel)) {
            PetriNet[] pnList1 = PNImport.generateFromAdoxxBPMN(xmlModel);
            PetriNet[] pnList2 = PNImport.generateFromAdoxxPetriNet(xmlModel);
            pnList = Utils.concatenate(pnList1, pnList2);
        } else
            throw new Exception("ERROR: The model file format can not be recognized.");
        
        if(pnList.length==0)
            throw new Exception("ERROR: No BPMN2.0 model provided.");
        
        return pnList;
    }

    private String verifySingleDeadlock(PetriNet pn) throws Exception{
        if(pn.isEmpty())
            throw new Exception("ERROR: The provided petri net is empty");
        
        String modelToVerify = PNExport.exportTo_LOLA(pn);
        String propertyToVerify = PNExport.exportTo_LOLA_property_DeadlockPresence(pn);
        
        ArrayList<String[]> counterExampleTraceList = new ArrayList<String[]>();
        String out = LOLA.sync_getVerificationOutput(modelCheckerExePath, modelToVerify, propertyToVerify);
        
        if(LOLA.isPropertyVerified(out))
            counterExampleTraceList.add(LOLA.getCounterExample(out, pn));
        
        return formatResult(counterExampleTraceList, pn, "Deadlock");
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
            String[] lastCounterExampleObjList = counterExample[counterExample.length-1].split(" ");
            for(String lastCounterExampleObj: lastCounterExampleObjList)
                if(!endPLList.contains(pn.getPlace(lastCounterExampleObj)))
                    pn.getPlace(lastCounterExampleObj).excludeFromDeadlockCheck=true;
        }
        
        return formatResult(counterExampleTraceList, pn, "Deadlock");
    }
    
    private String verifyEndUnboundedness(PetriNet pn) throws Exception{
        if(pn.isEmpty())
            throw new Exception("ERROR: The provided petri net is empty");
        
        String modelToVerify = PNExport.exportTo_LOLA(pn);
        String propertyToVerify = PNExport.exportTo_LOLA_property_EndUnboundednessPresence(pn);
        
        ArrayList<String[]> counterExampleTraceList = new ArrayList<String[]>();
        String out = LOLA.sync_getVerificationOutput(modelCheckerExePath, modelToVerify, propertyToVerify);
        
        if(LOLA.isPropertyVerified(out))
            counterExampleTraceList.add(LOLA.getCounterExample(out, pn));
        
        return formatResult(counterExampleTraceList, pn, "Unboundedness");
    }
    
    private String verifyAllUnboundedness(PetriNet pn) throws Exception{
        if(pn.isEmpty())
            throw new Exception("ERROR: The provided petri net is empty");
        
        String modelToVerify = PNExport.exportTo_LOLA(pn);
        String[] propertyToVerifyList = PNExport.exportTo_LOLA_property_AllUnboundednessPresence(pn);
        
        ArrayList<String[]> counterExampleTraceList = new ArrayList<String[]>();
        for(String propertyToVerify:propertyToVerifyList){
            String out = LOLA.sync_getVerificationOutput(modelCheckerExePath, modelToVerify, propertyToVerify);
            if(LOLA.isPropertyVerified(out))
                counterExampleTraceList.add(LOLA.getCounterExample(out, pn));
        }
        return formatResult(counterExampleTraceList, pn, "Unboundedness");
    }
    
    private String formatResult(ArrayList<String[]> counterExampleTraceList, PetriNet pn, String verificationName) throws Exception{
        /*
         <Result>
             <PNName>..petri net name..</PNName>
             <Status>..OK or KO..</Status>
             <Description>..detailed description of the result..</Description>
             <CounterExampleTrace>
                 <Step num="1">
                     <ObjectID>..bpmn object id..</ObjectID>
                     <ObjectID>..bpmn object id..</ObjectID>
                     <ObjectID>..bpmn object id..</ObjectID>
                 </Step>
                 <Step num="2">
                     <ObjectID>..bpmn object id..</ObjectID>
                     <ObjectID>..bpmn object id..</ObjectID>
                 </Step>
                 ...
             </CounterExampleTrace>
             <CounterExampleTrace>
                 <Step num="1">
                     <ObjectID>..bpmn object id..</ObjectID>
                 </Step>
                 ...
             </CounterExampleTrace>
             ...
         </Result>
         */
        
        String status = (counterExampleTraceList.size()==0)?"OK":"KO";
        String description = (counterExampleTraceList.size()==0)?verificationName+" not found!":verificationName+" found!";
        String ret = "<Result><PNName>"+pn.name+"</PNName><Status>"+status+"</Status><Description>"+description+"</Description>";
        for(String[] counterExampleTrace: counterExampleTraceList){
            ret += "<CounterExampleTrace>";
            
            for(int i=0; i<counterExampleTrace.length;i++){
                ret += "<Step num=\""+i+"\">";
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
            //Document t = XMLUtils.getXmlDocFromString("<test a=\"Analisi &amp; dell Istanza\"></test>");
            //String a = (String)XMLUtils.execXPath(t.getDocumentElement(), "/test/@a", XPathConstants.STRING);
            //a = XMLUtils.escapeXPathField(a);
            //Node b = (Node)XMLUtils.execXPath(t.getDocumentElement(), "//*[@a="+a+"]", XPathConstants.NODE);
            
            String bpmnUrl = "D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_3.bpmn";
            String bpmnModel = new String(IOUtils.readFile(bpmnUrl));
            
            Engine engine = new Engine();
            //System.out.println(engine.verifyDeadlock(bpmnModel));
            System.out.println(engine.verifyDeadlock(bpmnModel, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
