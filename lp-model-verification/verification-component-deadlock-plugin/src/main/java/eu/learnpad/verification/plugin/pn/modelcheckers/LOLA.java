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

package eu.learnpad.verification.plugin.pn.modelcheckers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import eu.learnpad.verification.plugin.pn.PetriNet;
import eu.learnpad.verification.plugin.pn.PetriNet.PL;
import eu.learnpad.verification.plugin.utils.IOUtils;

public class LOLA {
    
    public static String sync_getVerificationOutput(String lolaBinPath, String modelToVerify, String propertyToVerify, boolean useCoverabilitySearch) throws Exception{
        final int timeoutInMinutes = 5;
        return sync_getVerificationOutput(lolaBinPath, modelToVerify, propertyToVerify, useCoverabilitySearch, timeoutInMinutes*60);
    }
    
    public static String sync_getVerificationOutput(String lolaBinPath, String modelToVerify, String propertyToVerify, boolean useCoverabilitySearch, final int timeoutInSeconds) throws Exception{
        
        int cores = Runtime.getRuntime().availableProcessors();
        String filePath = System.getProperty("java.io.tmpdir") + "/" + java.util.UUID.randomUUID() + ".lola";
        IOUtils.writeFile(modelToVerify.getBytes(), filePath, false);
        //IOUtils.writeFile(propertyToVerify.getBytes(), filePath+".ctl", false);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommandLine cmdLine = new CommandLine(lolaBinPath);
        //cmdLine.addArgument("--formula="+filePath+".ctl", false);
        cmdLine.addArgument("--formula="+propertyToVerify, false);
        cmdLine.addArgument("--threads=" + cores);
        if(useCoverabilitySearch)
            cmdLine.addArgument("--search=cover");
        cmdLine.addArgument("-p");
        //cmdLine.addArgument("--path=\""+filePath+".out\"", false);
        //cmdLine.addArgument("--state=\""+filePath+".out\"", false);
        cmdLine.addArgument(filePath, false);
        
        DefaultExecutor exec = new DefaultExecutor();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(1000*timeoutInSeconds);
        
        exec.setWatchdog(watchdog);
        exec.setStreamHandler(streamHandler);
        exec.setExitValues(new int[]{0,1,2,139,35584});
        int exitVal = exec.execute(cmdLine);
        
        String output = outputStream.toString();

        if(watchdog.killedProcess())
            throw new Exception("ERROR: Timeout occurred. LOLA has reached the execution time limit of "+timeoutInSeconds+" seconds, so it has been aborted.\nPartial Output:\n"+output);
        
        if(exitVal!=0 || output.equals("") || output.contains("aborting [#"))
            throw new Exception("ERROR: LOLA internal error\nExit code:"+exitVal+"\nExec: " +cmdLine.toString() + "\nOutput:\n" + output);
        new File(filePath).delete();
        return output;
    }

    public static boolean isPropertyVerified(String lolaOutput){
        if(lolaOutput==null || lolaOutput.isEmpty())
            return false;
        
        if(lolaOutput.contains("lola: result: yes"))
            return true;
        else
            return false;
    }
    
    public static String[] getCounterExample(String lolaOutput, PetriNet pn) throws Exception{
        if(!isPropertyVerified(lolaOutput))
            throw new Exception("ERROR: The property is not verified so a counter example can not exist.");
        
        if(lolaOutput==null || lolaOutput.isEmpty())
            return new String[0];
        
        String newLineChar = "\r\n";
        if(!lolaOutput.contains("\r\n"))
            newLineChar = "\n";
        
        ArrayList<String> transitionTraceList = new ArrayList<String>();
        String[] traceRowList = lolaOutput.split(newLineChar);
        for(String traceRow:traceRowList)
            if(!(traceRow.contains(":") || traceRow.startsWith("===") || traceRow.startsWith("lola: ") || traceRow.startsWith("NOSTATE")))
                transitionTraceList.add(traceRow);
        
        if(transitionTraceList.isEmpty())
            return new String[0];
        
        ArrayList<String> placeTraceList = new ArrayList<String>();
        PetriNet pnc = pn.clonePN();
        ArrayList<PL> placeList = pnc.getPlaceList_safe();
        
        if(pnc.getEnabledTransitions().contains(pnc.getTransition(transitionTraceList.get(transitionTraceList.size()-1)))){
            //rigirare l'output
            ArrayList<String> tmpList = new ArrayList<String>();
            for(int i=transitionTraceList.size()-1;i>=0;i--)
                tmpList.add(transitionTraceList.get(i));
            transitionTraceList = tmpList;
        }
        
        for(String transitionTrace:transitionTraceList){
            int[] currentMark = pnc.getCurrentMark();
            String places = "";
            for(int i=0;i<currentMark.length;i++)
                if(currentMark[i]!=0)
                    places += placeList.get(i).name+" ";
            placeTraceList.add(places);

            pnc.fireTransition(pnc.getTransition(transitionTrace));
        }
        {
            int[] currentMark = pnc.getCurrentMark();
            String places = "";
            for(int i=0;i<currentMark.length;i++)
                if(currentMark[i]!=0)
                    places += placeList.get(i).name+" ";
            placeTraceList.add(places);
        }
                
        String[] ret = new String[placeTraceList.size()];
        placeTraceList.toArray(ret);
        return ret;
    }
    
    /*
    public static void main(String[] args) {
        try{
            //FIXME: con test_1 e 7 lola esplode in stati! Fare piu test con il parametro --search=cover 
            String bpmnUrl = "D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_4.bpmn";
            PetriNet pn = PNImport.generateFromBPMN(XMLUtils.getXmlDocFromURI(bpmnUrl));
            //pn.getPlace("pTask1").excludeFromDeadlockCheck=true;
            //pn.getPlace("pSequenceFlow17").excludeFromDeadlockCheck=true;
            //pn.getPlace("pStartEvent1").excludeFromDeadlockCheck=true;
            
            //pn = it.unicam.cs.pn.algorithms.Algorithms.generateReducedNet(pn);
            
            String modelToVerify = PNExport.exportTo_LOLA(pn);
            //String propertyToVerify = PNExport.exportTo_LOLA_property_DeadlockPresence(pn);
            //String propertyToVerify = PNExport.exportTo_LOLA_property_StateReachable(pn, BPUtils.getPNIdsFromBPMNId(pn, "Task_1"), false);
            String propertyToVerify = PNExport.exportTo_LOLA_property_State2FollowState1(pn, BPUtils.getPNIdsFromBPMNId(pn, "StartEvent_2"), BPUtils.getPNIdsFromBPMNId(pn, "Task_2"), true);
            
            
            //se controllo AF invece di EF allora debbo stare attento perchè la ricerca va in timeout, ma in questi casi se va in timeout significa probabilmente che non è verificata in quanto se la verifica ci mette subito
            //propertyToVerify = "AG((pStartEvent1 > 0) -> AF(pEndEvent1 > 0 OR pEndEvent2 > 0))";
            //System.out.println(modelToVerify);
            System.out.println(propertyToVerify);
            
            String out = LOLA.sync_getVerificationOutput("D:\\LAVORO\\PROGETTI\\PNToolkit\\verifier\\lola-2.0-cygwin.exe", modelToVerify, propertyToVerify, false);
            System.out.println(out);
            System.out.println("Is property verified? " + LOLA.isPropertyVerified(out));
            if(!LOLA.isPropertyVerified(out))
                return;
            String[] traceList = LOLA.getCounterExample(out, pn);
            for(String trace:traceList)
                System.out.println(trace);
                
        }catch(Exception ex){ex.printStackTrace();}
    }
    */
}
