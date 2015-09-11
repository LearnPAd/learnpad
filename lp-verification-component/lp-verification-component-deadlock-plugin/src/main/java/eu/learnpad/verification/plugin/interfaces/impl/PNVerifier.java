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

import java.io.File;

import eu.learnpad.verification.plugin.bpmn.verifier.Engine;
import eu.learnpad.verification.plugin.interfaces.Plugin;
import eu.learnpad.verification.plugin.utils.IOUtils;
import eu.learnpad.verification.plugin.utils.Utils;
import eu.learnpad.verification.plugin.utils.Utils.LogType;
import eu.learnpad.verification.plugin.utils.XMLUtils;

public class PNVerifier implements Plugin {

    @Override
    public String[] getVerificationTypeProvided() {
        return new String[]{"ONE DEADLOCK", "ALL DEADLOCKS", "END PLACES UNBOUNDEDNESS", "ALL PLACES UNBOUNDEDNESS"};
    }

    @Override
    public String performVerification(String model, String type) {

        String ret = "";
        try{
            Engine engine = new Engine();
            if(type.equals("ONE DEADLOCK")){
                ret = engine.verifyDeadlock(model, false);
            } else if(type.equals("ALL DEADLOCKS")){
                ret = engine.verifyDeadlock(model, true);
            } else if(type.equals("END PLACES UNBOUNDEDNESS")){
                ret = engine.verifyUnboundedness(model, false);
            } else if(type.equals("ALL PLACES UNBOUNDEDNESS")){
                ret = engine.verifyUnboundedness(model, true);
            } else
                throw new Exception("ERROR: Verification type " + type + " not supported.");
        }catch(Exception ex){
            ex.printStackTrace();
            Utils.log(ex);
            Utils.log("\nModel involved in the exception:\n"+model, LogType.ERROR);
            ret = "<Result><Status>ERROR</Status><Description>"+ex.getMessage()+"</Description></Result>";
        }
        return ret;
    }
    
    public static void main(String[] args) {
        try{
            String fileName = PNVerifier.class.getName();
            String filePath = PNVerifier.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if(!new File(filePath).isDirectory())
                fileName = filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
            if(args.length==0) {
                System.out.println("Usage:\njava -jar " + fileName + " command\n\ncommand:\n\t--showAvailableVerifications\n\t--performVerification verificationType modelPath");
                return;
            }
            
            if(args[0].equals("--showAvailableVerifications")) {
                String[] retList = new PNVerifier().getVerificationTypeProvided();
                for(String ret:retList)
                    System.out.println(ret);
                return;
            }
            if(args[0].equals("--performVerification")) {
                if(args.length!=3){
                    System.out.println("ERROR: expected --performVerification verificationType modelPath");
                    return;
                }
                String verificationType = args[1];
                String modelPath = args[2];
                String model = new String(IOUtils.readFile(modelPath));
                String result = new PNVerifier().performVerification(model, verificationType);
                String resultXml = "<VerificationResult><VerificationType>"+verificationType+"</VerificationType><VerificationID></VerificationID><ModelID>"+modelPath+"</ModelID><Time>"+Utils.getUTCTime()+"</Time><Results>"+result+"</Results></VerificationResult>";
                try{
                    XMLUtils.getXmlDocFromString(resultXml);
                }catch(Exception e){
                    resultXml = "<VerificationResult><VerificationType>"+verificationType+"</VerificationType><VerificationID></VerificationID><ModelID>"+modelPath+"</ModelID><Time>"+Utils.getUTCTime()+"</Time><Results><Result><Status>ERROR</Status><Description>ERROR: The result is not a valid XML</Description></Result></Results></VerificationResult>";
                    Utils.log("ERROR: The result is not a valid XML:\n"+resultXml, LogType.ERROR);
                }
                
                System.out.println(resultXml);
                return;
            }
        }catch(Exception ex){Utils.log(ex);}
    }
    
}
