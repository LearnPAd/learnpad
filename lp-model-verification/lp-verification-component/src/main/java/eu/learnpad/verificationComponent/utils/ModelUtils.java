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

package eu.learnpad.verificationComponent.utils;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ModelUtils {
    
    public static String[] processModel(byte[] model) throws Exception{
        ArrayList<String> retA = new ArrayList<String>();
        
        if(isZipFile(model)){
            HashMap<String, byte[]> zipContentList = IOUtils.unZip(model);
            for(String fileName:zipContentList.keySet()){
                byte[] zipEntry = zipContentList.get(fileName);
                if(isOMGBPMN2(zipEntry))
                    retA.add(new String(zipEntry, "UTF-8"));
            }
            if(retA.isEmpty()){
                for(String fileName:zipContentList.keySet()){
                    byte[] zipEntry = zipContentList.get(fileName);
                    if(isADOXX(zipEntry))
                        retA.addAll(extractOMGBPMNModelsFromADOXX(new String(zipEntry, "UTF-8")));
                }
            }
        }
        
        if(isADOXX(model))
            retA.addAll(extractOMGBPMNModelsFromADOXX(new String(model, "UTF-8")));
        
        if(isOMGBPMN2(model))
            retA.add(new String(model, "UTF-8"));
 
        if(retA.isEmpty())
            throw new Exception("Cannot find any know models");
        
        String[] ret = new String[retA.size()];
        retA.toArray(ret);
        return ret;
    }
    
    private static boolean isZipFile(byte[] modelB){
        if(modelB[0]=='P' && modelB[1]=='K')
            return true;
        return false;
    }
    
    private static boolean isOMGBPMN2(byte[] modelB){
        try{
            Document model = XMLUtils.getXmlDocFromString(new String(modelB, "UTF-8"));
            String queryRoot = "/*[namespace-uri()='http://www.omg.org/spec/BPMN/20100524/MODEL' and local-name()='definitions']";
            Node bpmnRootNode =  (Node) XMLUtils.execXPath(model.getDocumentElement(), queryRoot, XPathConstants.NODE);
            if(bpmnRootNode!=null)
                return true;
        }catch(Exception e){}
        return false;
    }
    
    private static boolean isADOXX(byte[] modelB){
        try{
            String adoxxModel = new String(modelB, "UTF-8");
            adoxxModel = adoxxModel.replace("<!DOCTYPE ADOXML SYSTEM \"adoxml31.dtd\">", "")
                    .replace("<?xml version=\"1.1\" encoding=\"UTF-8\"?>\n", "")
                    .replace(" xmlns=\"@boc-eu.com/boc-is/adonis.model.document;1\"", "")
                    .replace(" xsi:schemaLocation=\"@boc-eu.com/boc-is/adonis.model.document;1 adoxmlmodel.xsd\"", "");

            Document model = XMLUtils.getXmlDocFromString(adoxxModel);
            String queryRoot = "/*[local-name()='ADOXML' or local-name()='adoxml']";
            Node bpmnRootNode =  (Node) XMLUtils.execXPath(model.getDocumentElement(), queryRoot, XPathConstants.NODE);
            if(bpmnRootNode!=null)
                return true;
        }catch(Exception e){}
        return false;
    }
    
    private static ArrayList<String> extractOMGBPMNModelsFromADOXX(String adoxxModel) throws Exception{
        ArrayList<String> ret = new ArrayList<String>();
                
        HashMap<String,String> adoxxModelList = extractBPMNModelsFromADOXX(adoxxModel);
        for(String adoxxModelLightId:adoxxModelList.keySet())
            ret.add(callWSConversion(adoxxModelLightId, adoxxModelList.get(adoxxModelLightId)));
        return ret;
    }
    
    private static HashMap<String,String> extractBPMNModelsFromADOXX(String adoxxModel) throws Exception{
        HashMap<String,String> ret = new HashMap<String,String>();
        
        adoxxModel = adoxxModel.replace("<!DOCTYPE ADOXML SYSTEM \"adoxml31.dtd\">", "")
                                .replace("<?xml version=\"1.1\" encoding=\"UTF-8\"?>\n", "")
                                .replace(" xmlns=\"@boc-eu.com/boc-is/adonis.model.document;1\"", "")
                                .replace(" xsi:schemaLocation=\"@boc-eu.com/boc-is/adonis.model.document;1 adoxmlmodel.xsd\"", "");

        if(adoxxModel.contains("ADOXML"))
            adoxxModel = adoxxModel.replace("<ADOXML", "<adoxml").replace("</ADOXML", "</adoxml")
                                    .replace("<MODELS", "<models").replace("</MODELS", "</models")
                                    //APPLICATIONMODELS ?
                                    .replace("<ATTRPROFDIR", "<attrprofdir").replace("</ATTRPROFDIR", "</attrprofdir")
                                    .replace("<ATTRIBUTEPROFILES", "<attributeprofiles").replace("</ATTRIBUTEPROFILES", "</attributeprofiles")
                                    .replace("<ATTRIBUTEPROFILE", "<attributeprofile").replace("</ATTRIBUTEPROFILE", "</attributeprofile")
                                    .replace("<MODELGROUPS", "<modelgroups").replace("</MODELGROUPS", "</modelgroups")
                                    .replace("<MODELGROUP", "<modelgroup").replace("</MODELGROUP", "</modelgroup")
                                    .replace("<MODELREFERENCE", "<modelreference").replace("</MODELREFERENCE", "</modelreference")
                                    .replace("<MODELATTRIBUTES", "<modelattributes").replace("</MODELATTRIBUTES", "</modelattributes")
                                    .replace("<MODEL id=\"mod.", "<model id=\"").replace("</MODEL", "</model")
                                    .replace("<ATTRIBUTE", "<attribute").replace("</ATTRIBUTE", "</attribute")
                                    .replace("<RECORD", "<record").replace("</RECORD", "</record")
                                    .replace("<INSTANCE", "<instance").replace("</INSTANCE", "</instance")
                                    .replace("<INTERREF", "<interref").replace("</INTERREF", "</interref")
                                    .replace("<IREF", "<iref").replace("</IREF", "</iref")
                                    .replace("<CONNECTOR", "<connector").replace("</CONNECTOR", "</connector")
                                    .replace("<FROM", "<from").replace("</FROM", "</from")
                                    .replace("<TO", "<to").replace("</TO", "</to")
                                    .replace("<ROW", "<row").replace("</ROW", "</row");
        
        Document adoxxXml = XMLUtils.getXmlDocFromString(adoxxModel);
        
        NodeList bpmnModelElList =  (NodeList) XMLUtils.execXPath(adoxxXml.getDocumentElement(), "//*[@modeltype='Business process diagram (BPMN 2.0)']", XPathConstants.NODESET);
        
        for(int modelIndex=0;modelIndex<bpmnModelElList.getLength();modelIndex++) {
            String modelId = bpmnModelElList.item(modelIndex).getAttributes().getNamedItem("id").getNodeValue();
            
            Document lightAdoxxXml = XMLUtils.getXmlDocFromString(adoxxModel);
            NodeList nodesToRemove =  (NodeList) XMLUtils.execXPath(lightAdoxxXml.getDocumentElement(), "//*[local-name()='model' or local-name()='MODEL'][@id!="+XMLUtils.escapeXPathField(modelId)+"]", XPathConstants.NODESET);
            for(int i=0;i<nodesToRemove.getLength();i++)
                nodesToRemove.item(i).getParentNode().removeChild(nodesToRemove.item(i));
            String model = XMLUtils.getStringFromXmlDoc(lightAdoxxXml);
            ret.put(modelId, model);
        }
        
        return ret;
    }
    
    private static String callWSConversion(String adoxxModelId, String adoxxModel) throws Exception{

        String envelope = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.bpmn.adows.adoxx.org/\"><soapenv:Header/><soapenv:Body><ser:transformADOXML2BPMN><inputADOxxXML><![CDATA["+adoxxModel+"]]></inputADOxxXML><modelid>"+adoxxModelId+"</modelid></ser:transformADOXML2BPMN></soapenv:Body></soapenv:Envelope>";
        HashMap<String,String> htmlHeaderList = new HashMap<String,String>();
        htmlHeaderList.put("Content-Type", "text/xml; charset=UTF-8");
        htmlHeaderList.put("SOAPAction", "urn:TransformADOXML2BPMN");
        
        byte[] data = NETUtils.sendHTTPPOST("https://www.adoxx.org/ADOxxORGBPMNExport/services/transformbpmn", envelope, htmlHeaderList, false, false);
        String dataS = new String(data, "UTF-8");
        Document dataX = XMLUtils.getXmlDocFromString(dataS);
        if(dataX.getElementsByTagName("faultstring").getLength()!=0)
            throw new Exception("Error contacting the Web Service: "+dataX.getElementsByTagName("faultstring").item(0).getTextContent());
        
        String bpmnModel = dataX.getElementsByTagName("outputBPMN").item(0).getTextContent();
        if(bpmnModel.isEmpty())
            throw new Exception("Error: the ws returned an empty bpmn for the model "+adoxxModelId + ":\n"+adoxxModel);
        //Utils.log("BPMN MODEL:\n"+bpmnModel, LogType.INFO);
        return bpmnModel;
    }
}
