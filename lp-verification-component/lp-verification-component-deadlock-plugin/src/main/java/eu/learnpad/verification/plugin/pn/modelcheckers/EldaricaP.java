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
import java.io.PrintStream;
import java.util.ArrayList;

import eu.learnpad.verification.plugin.utils.IOUtils;

public class EldaricaP {
    
    public static String sync_getVerificationOutput(String eldaricaJarPath, String modelToVerify, String propertyToVerify) throws Exception{
        final int timeoutInMinutes = 5;
        modelToVerify = modelToVerify + "\n\n" + propertyToVerify;
        String filePath = System.getProperty("java.io.tmpdir") + java.util.UUID.randomUUID() + ".net";
        IOUtils.writeFile(modelToVerify.getBytes(), filePath, false);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        final Process proc = Runtime.getRuntime().exec("java -cp " + eldaricaJarPath + " lazabs.horn.abstractions.PetriMain " + filePath);
        IOUtils.inheritIO(proc.getInputStream(), ps);
        IOUtils.inheritIO(proc.getErrorStream(), ps);
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000*60*timeoutInMinutes);
                    proc.destroy();
                } catch (InterruptedException e) {}
            };
        };
        timerThread.start();
        proc.waitFor();
        timerThread.interrupt();
        new File(filePath).delete();
        return baos.toString();
    }
    
    public static boolean isPropertyVerified(String eldaricaOutput){
        if(eldaricaOutput.isEmpty())
            return false;
        
        if(eldaricaOutput.contains("UNREACHABLE"))
            return false;
        else
            return true;
    }
    
    public static String[] getCounterExample(String eldaricaOutput) throws Exception{
        if(!isPropertyVerified(eldaricaOutput))
            throw new Exception("ERROR: The property is not verified so a counter example can not exist.");
        
        if(eldaricaOutput.isEmpty())
            return new String[0];
        
        String newLineChar = "\r\n";
        if(!eldaricaOutput.contains("\r\n"))
            newLineChar = "\n";
        //String[] controlGroupList = eldaricaPOutput.substring(eldaricaPOutput.indexOf("Computing control groups ...\r\n")+"Computing control groups ...\r\n".length(), eldaricaPOutput.indexOf("\r\nData places:")).replaceAll("\r\n", ", ").split(", ");
        String[] dataPlaceList = eldaricaOutput.substring(eldaricaOutput.indexOf("Data places:"+newLineChar)+("Data places:"+newLineChar).length(), eldaricaOutput.indexOf(newLineChar+newLineChar+"Encoding")).replaceAll(newLineChar, ", ").split(", ");
        String[] traceRowList = eldaricaOutput.substring(eldaricaOutput.indexOf("REACHABLE"+newLineChar)+("REACHABLE"+newLineChar).length()).split(newLineChar);
        
        ArrayList<String> traceList = new ArrayList<String>();
        for(String traceRow:traceRowList){
            if(!traceRow.contains("inv_"))
                continue;
            String elems = traceRow.substring(traceRow.indexOf("inv_")+"inv_".length(), traceRow.indexOf("(")).replaceAll("_", " ");
            String[] dataPlaceElemList = traceRow.substring(traceRow.indexOf("(")+"(".length(), traceRow.indexOf(")")).split(", ");
            for(int i=0;i<dataPlaceElemList.length;i++)
                if(!dataPlaceElemList[i].equals("0"))
                    elems += " " + dataPlaceList[i];
            traceList.add(elems);
        }
        
        String[] ret = new String[traceList.size()];
        for(int i=0;i<traceList.size();i++)
            ret[i] = traceList.get(traceList.size()-i-1);
        
        return ret;
    }
    
    /*
    public static void main(String[] args) {
        try{
            String bpmnUrl = "D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_9.bpmn";
            PetriNet pn = PNImport.generateFromBPMN(XMLUtils.getXmlDocFromURI(bpmnUrl));
            //pn.getPlace("pTask1").excludeFromDeadlockCheck=true;
            //pn.getPlace("pSequenceFlow17").excludeFromDeadlockCheck=true;
            
            
            String modelToVerify = PNExport.exportTo_EldaricaP(pn);
            String propertyToVerify = PNExport.exportTo_EldaricaP_property_DeadlockPresence(pn);
            //String propertyToVerify = PNImportExport.exportToEldaricaP_propertyEndReachability(pn);
            //System.out.println(modelToVerify);
            System.out.println(propertyToVerify);
            
            String out = EldaricaP.sync_getVerificationOutput("D:\\LAVORO\\PROGETTI\\PNToolkit\\verifier\\lazabs.jar", modelToVerify, propertyToVerify);
            //System.out.println(out);
            System.out.println("Is property verified? " + EldaricaP.isPropertyVerified(out));
            if(!EldaricaP.isPropertyVerified(out))
                return;
            String[] traceList = EldaricaP.getCounterExample(out);
            for(String trace:traceList)
                System.out.println(trace);
        }catch(Exception ex){ex.printStackTrace();}
    }
    */
}
