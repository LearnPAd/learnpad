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

package eu.learnpad.verification.plugin.pn.impexp;

import java.util.ArrayList;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eu.learnpad.verification.plugin.pn.PetriNet;
import eu.learnpad.verification.plugin.pn.PetriNet.PL;
import eu.learnpad.verification.plugin.pn.PetriNet.PT;
import eu.learnpad.verification.plugin.pn.PetriNet.TR;
import eu.learnpad.verification.plugin.pn.impexp.PNMapping.GeneratedElements;
import eu.learnpad.verification.plugin.utils.Utils;
import eu.learnpad.verification.plugin.utils.XMLUtils;

public class PNImport {
    
    public static boolean isOMGBPMN2(Document model){
        try{
            String queryRoot = "/*[namespace-uri()='http://www.omg.org/spec/BPMN/20100524/MODEL' and local-name()='definitions']";
            Node bpmnRootNode =  (Node) XMLUtils.execXPath(model.getDocumentElement(), queryRoot, XPathConstants.NODE);
            if(bpmnRootNode!=null)
                return true;
        }catch(Exception e){}
        return false;
    }
    
    public static boolean isADOXX(Document model){
        try{
            String queryRoot = "/*[local-name()='ADOXML' or local-name()='adoxml']";
            Node bpmnRootNode =  (Node) XMLUtils.execXPath(model.getDocumentElement(), queryRoot, XPathConstants.NODE);
            if(bpmnRootNode!=null)
                return true;
        }catch(Exception e){}
        return false;
    }
    
    /**
     * Generate a PetriNet from the provided OMG BPMN2 Standard.
     * Supported BPMN2 elements are: startEvent, endEvent, task, userTask, serviceTask, manualTask, businessRuleTask, receiveTask, sendTask, scriptTask, choreographyTask, intermediateCatchEvent, intermediateThrowEvent, adHocSubProcess, subProcess, transaction, callActivity, choreographyTask, subChoreography, callChoreography, standardLoopCharacteristics, boundaryEvent, parallelGateway, exclusiveGateway, eventBasedGateway, inclusiveGateway, complexGateway, sequenceFlow, messageFlow, participant.
     * The function provide a personalized mapping for every element.
     * @param bpmnXml The BPMN2 model
     * @return The PetriNet
     * @throws Exception
     */
    public static PetriNet generateFromBPMN(Document bpmnXml) throws Exception{
        if(!isOMGBPMN2(bpmnXml))
            throw new Exception("ERROR: The provided model is not a valid OMG standard BPMN2.0 model");
        
        PNMapping pnm = new PNMapping();
        
        pnm.addMapping("task : p>t ; in:sequence=p, message=t ; out:sequence=t, message=t, bound=p");
        pnm.addMapping("start: p(1)>t ; in: ; out:sequence=t");
        pnm.addMapping("startMsg: p>t ; in: message=p ; out:sequence=t");
        pnm.addMapping("end|terminateEventDefinition|errorEventDefinition: p ; in: sequence=p ; out: ");
        pnm.addMapping("endMsg|terminateEventDefinitionMsg|errorEventDefinitionMsg: p>t,t>p1 ; in: sequence=p ; out: message=t");
        pnm.addMapping("loop: p>t0,t0>p0,p0>t,p0>t1,t1>p ; in:sequence=p, message=t0 ; out:sequence=t, message=t0, bound=p");
        pnm.addMapping("xor|inclusiveComplexG|eventG : p ; in:sequence=p ; out:sequence=p");
        pnm.addMapping("and : t ; in:sequence=t ; out:sequence=t");
        pnm.addMapping("bound : t ; in:bound=t ; out:sequence=t");
        
        String startQuery = "//*[local-name()='startEvent']";
        String endQuery = "//*[local-name()='endEvent']";
        //String endSpecialQuery = "./*[local-name()='terminateEventDefinition']|./*[local-name()='errorEventDefinition']";
        String anyTaskQuery = "//*[local-name()='task']|//*[local-name()='userTask']|//*[local-name()='serviceTask']|//*[local-name()='manualTask']|//*[local-name()='businessRuleTask']|//*[local-name()='receiveTask']|//*[local-name()='sendTask']|//*[local-name()='scriptTask']|//*[local-name()='choreographyTask']|//*[local-name()='intermediateCatchEvent']|//*[local-name()='intermediateThrowEvent']|//*[local-name()='adHocSubProcess']|//*[local-name()='subProcess']|//*[local-name()='transaction']|//*[local-name()='callActivity']|//*[local-name()='choreographyTask']|//*[local-name()='subChoreography']|//*[local-name()='callChoreography']";
        //String anyTaskLoopQuery = "./*[local-name()='standardLoopCharacteristics']|./*[local-name()='multiInstanceLoopCharacteristics']";
        String anyTaskLoopQuery = "./*[local-name()='standardLoopCharacteristics']";
        String boundaryTaskQuery = "//*[local-name()='boundaryEvent']";
        //String andQuery = "//*[local-name()='inclusiveGateway']|//*[local-name()='eventBasedGateway']|//*[local-name()='complexGateway']|//*[local-name()='parallelGateway']";
        String andQuery = "//*[local-name()='parallelGateway']";
        String xorQuery = "//*[local-name()='exclusiveGateway']";
        String eventGatewayQuery = "//*[local-name()='eventBasedGateway']";
        String inclusiveComplexGatewayQuery = "//*[local-name()='inclusiveGateway']|//*[local-name()='complexGateway']";
        
        String sequenceFlowQuery = "//*[local-name()='sequenceFlow']";
        String messageFlowQuery = "//*[local-name()='messageFlow']";
        String sequenceFlowExcludeFromToElemList = "participant"; //Se ci sono sequence flow da/verso una pool allora non li considero
        String messageFlowExcludeFromToElemList = "participant"; //Se ci sono messaggi da/verso una pool allora non li considero in quanto per la verifica si assumerebbe che il messaggio sia inviato/ricevuto sempre perci� � come se non ci fosse; se si toglie, vanno mappati a parte un place con un token ed una transizione per ogni messaggio
        
        NodeList startEventNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), startQuery, XPathConstants.NODESET);
        for(int i=0;i<startEventNodeList.getLength();i++){
            String id = startEventNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            NodeList messegesToStartNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), "//*[local-name()='messageFlow' and (@sourceRef="+XMLUtils.escapeXPathField(id)+" or @targetRef="+XMLUtils.escapeXPathField(id)+")]", XPathConstants.NODESET);
            String elemType = "start";
            if(messegesToStartNodeList.getLength()>0)
                elemType += "Msg";
            float[] xy = getBPMNElementCoordinatesXY(bpmnXml, id);
            
            GeneratedElements ge = pnm.processElement(id, elemType, id, xy[0], xy[1]);
            
            String poolId = getBPMNElementPool(bpmnXml, id);
            for(PL place:ge.placeList)
                place.addInfo("poolId", poolId);
            for(TR transition:ge.transitionList)
                transition.addInfo("poolId", poolId);
        }
        
        NodeList endEventNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), endQuery, XPathConstants.NODESET);
        for(int i=0;i<endEventNodeList.getLength();i++){
            String id = endEventNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            NodeList messegesFromEndNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), "//*[local-name()='messageFlow' and (@sourceRef="+XMLUtils.escapeXPathField(id)+" or @targetRef="+XMLUtils.escapeXPathField(id)+")]", XPathConstants.NODESET);
            String elemType = "end";
            if(messegesFromEndNodeList.getLength()>0)
                elemType += "Msg";
            float[] xy = getBPMNElementCoordinatesXY(bpmnXml, id);
            GeneratedElements ge = pnm.processElement(id, elemType, id, xy[0], xy[1]);
            
            String poolId = getBPMNElementPool(bpmnXml, id);
            for(PL place:ge.placeList)
                place.addInfo("poolId", poolId);
            for(TR transition:ge.transitionList)
                transition.addInfo("poolId", poolId);
        }
        
        NodeList anyTaskNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), anyTaskQuery, XPathConstants.NODESET);
        for(int i=0;i<anyTaskNodeList.getLength();i++){
            String id = anyTaskNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            String elemType = "task";
            NodeList taskLoopDefinitionNodeList =  (NodeList) XMLUtils.execXPath(anyTaskNodeList.item(i), anyTaskLoopQuery, XPathConstants.NODESET);
            if(taskLoopDefinitionNodeList.getLength()>0)
                elemType = "loop";
            float[] xy = getBPMNElementCoordinatesXY(bpmnXml, id);
            GeneratedElements ge = pnm.processElement(id, elemType, id, xy[0], xy[1]);
            
            if(anyTaskNodeList.item(i).getAttributes().getNamedItem("calledElement")!=null){
                String calledElement = anyTaskNodeList.item(i).getAttributes().getNamedItem("calledElement").getNodeValue();
                for(PL place: ge.placeList)
                    place.addInfo("calledElement", calledElement);
                for(TR transition: ge.transitionList)
                    transition.addInfo("calledElement", calledElement);
            }
            
            String poolId = getBPMNElementPool(bpmnXml, id);
            for(PL place:ge.placeList)
                place.addInfo("poolId", poolId);
            for(TR transition:ge.transitionList)
                transition.addInfo("poolId", poolId);
        }
        
        NodeList andNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), andQuery, XPathConstants.NODESET);
        for(int i=0;i<andNodeList.getLength();i++){
            String id = andNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            float[] xy = getBPMNElementCoordinatesXY(bpmnXml, id);
            GeneratedElements ge = pnm.processElement(id, "and", id, xy[0], xy[1]);
            
            String poolId = getBPMNElementPool(bpmnXml, id);
            for(PL place:ge.placeList)
                place.addInfo("poolId", poolId);
            for(TR transition:ge.transitionList)
                transition.addInfo("poolId", poolId);
        }
        
        NodeList xorNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), xorQuery, XPathConstants.NODESET);
        for(int i=0;i<xorNodeList.getLength();i++){
            String id = xorNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            float[] xy = getBPMNElementCoordinatesXY(bpmnXml, id);
            GeneratedElements ge = pnm.processElement(id, "xor", id, xy[0], xy[1]);
            
            String poolId = getBPMNElementPool(bpmnXml, id);
            for(PL place:ge.placeList)
                place.addInfo("poolId", poolId);
            for(TR transition:ge.transitionList)
                transition.addInfo("poolId", poolId);
        }
        
        NodeList eventGNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), eventGatewayQuery, XPathConstants.NODESET);
        for(int i=0;i<eventGNodeList.getLength();i++){
            String id = eventGNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            float[] xy = getBPMNElementCoordinatesXY(bpmnXml, id);
            GeneratedElements ge = pnm.processElement(id, "eventG", id, xy[0], xy[1]);
            
            String poolId = getBPMNElementPool(bpmnXml, id);
            for(PL place:ge.placeList)
                place.addInfo("poolId", poolId);
            for(TR transition:ge.transitionList)
                transition.addInfo("poolId", poolId);
        }
        
        NodeList inclusiveComplexGNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), inclusiveComplexGatewayQuery, XPathConstants.NODESET);
        for(int i=0;i<inclusiveComplexGNodeList.getLength();i++){
            String id = inclusiveComplexGNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            String defaultSequenceFlowId = "";
            if(inclusiveComplexGNodeList.item(i).getAttributes().getNamedItem("default")!=null)
                defaultSequenceFlowId = inclusiveComplexGNodeList.item(i).getAttributes().getNamedItem("default").getNodeValue();
            float[] xy = getBPMNElementCoordinatesXY(bpmnXml, id);
            GeneratedElements ge = pnm.processElement(id, "inclusiveComplexG", id, xy[0], xy[1]);
            if(ge.placeList.length==1 && ge.placeList[0]!=null)
                ge.placeList[0].addInfo("defaultSequenceFlowId", defaultSequenceFlowId);
            
            String poolId = getBPMNElementPool(bpmnXml, id);
            for(PL place:ge.placeList)
                place.addInfo("poolId", poolId);
            for(TR transition:ge.transitionList)
                transition.addInfo("poolId", poolId);
        }
        
        NodeList boundaryTaskNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), boundaryTaskQuery, XPathConstants.NODESET);
        for(int i=0;i<boundaryTaskNodeList.getLength();i++){
            String id = boundaryTaskNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            float[] xy = getBPMNElementCoordinatesXY(bpmnXml, id);
            pnm.processElement(id, "bound", id, xy[0], xy[1]);
            String sourceId = boundaryTaskNodeList.item(i).getAttributes().getNamedItem("attachedToRef").getNodeValue();
            if(sourceId.isEmpty())
                throw new Exception("ERROR: Attribute 'attachedToRef' not defined for the object " + id);
            GeneratedElements ge = pnm.processRelation(id, "bound", sourceId, id);    
            
            String poolId = getBPMNElementPool(bpmnXml, id);
            for(PL place:ge.placeList)
                place.addInfo("poolId", poolId);
            for(TR transition:ge.transitionList)
                transition.addInfo("poolId", poolId);
        }
        
        NodeList sequenceFlowNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), sequenceFlowQuery, XPathConstants.NODESET);
        for(int i=0;i<sequenceFlowNodeList.getLength();i++){
            String id = sequenceFlowNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            String sourceId = sequenceFlowNodeList.item(i).getAttributes().getNamedItem("sourceRef").getNodeValue();
            String targetId = sequenceFlowNodeList.item(i).getAttributes().getNamedItem("targetRef").getNodeValue();    
            String sourceType =  ((Node) XMLUtils.execXPath(bpmnXml.getDocumentElement(), "//*[@id="+XMLUtils.escapeXPathField(sourceId)+"]", XPathConstants.NODE)).getLocalName();
            String targetType =  ((Node) XMLUtils.execXPath(bpmnXml.getDocumentElement(), "//*[@id="+XMLUtils.escapeXPathField(targetId)+"]", XPathConstants.NODE)).getLocalName();
            
            if(sourceId.isEmpty()) throw new Exception("ERROR: Attribute 'sourceRef' missing for the relation " + id);
            if(targetId.isEmpty()) throw new Exception("ERROR: Attribute 'targetRef' missing for the relation " + id);
            if(sourceType.isEmpty()) throw new Exception("ERROR: Can not identify the element " + sourceId);
            if(targetType.isEmpty()) throw new Exception("ERROR: Can not identify the element " + targetId);
            
            if(sequenceFlowExcludeFromToElemList.contains(sourceType) || sequenceFlowExcludeFromToElemList.contains(targetType))
                continue;
            
            GeneratedElements ge = pnm.processRelation(id, "sequence", sourceId, targetId);
            
            String poolId = getBPMNElementPool(bpmnXml, id);
            for(PL place:ge.placeList)
                place.addInfo("poolId", poolId);
            for(TR transition:ge.transitionList)
                transition.addInfo("poolId", poolId);
        }
        
        NodeList messageFlowNodeList =  (NodeList) XMLUtils.execXPath(bpmnXml.getDocumentElement(), messageFlowQuery, XPathConstants.NODESET);
        for(int i=0;i<messageFlowNodeList.getLength();i++){
            String id = messageFlowNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            String sourceId = messageFlowNodeList.item(i).getAttributes().getNamedItem("sourceRef").getNodeValue();
            String targetId = messageFlowNodeList.item(i).getAttributes().getNamedItem("targetRef").getNodeValue();
            String sourceType =  ((Node) XMLUtils.execXPath(bpmnXml.getDocumentElement(), "//*[@id="+XMLUtils.escapeXPathField(sourceId)+"]", XPathConstants.NODE)).getLocalName();
            String targetType =  ((Node) XMLUtils.execXPath(bpmnXml.getDocumentElement(), "//*[@id="+XMLUtils.escapeXPathField(targetId)+"]", XPathConstants.NODE)).getLocalName();

            if(sourceId.isEmpty()) throw new Exception("ERROR: Attribute 'sourceRef' missing for the relation " + id);
            if(targetId.isEmpty()) throw new Exception("ERROR: Attribute 'targetRef' missing for the relation " + id);
            if(sourceType.isEmpty()) throw new Exception("ERROR: Can not identify the element " + sourceId);
            if(targetType.isEmpty()) throw new Exception("ERROR: Can not identify the element " + targetId);
            
            if(messageFlowExcludeFromToElemList.contains(sourceType) || messageFlowExcludeFromToElemList.contains(targetType))
                continue;
            
            GeneratedElements genEl = pnm.processRelation(id, "message", sourceId, targetId);
            if(genEl.placeList.length>0)
                genEl.placeList[0].excludeFromDeadlockCheck = true;
            
        }
        
        PetriNet pn = pnm.generatePN("PNBPMN");
        postProcessEventGateways(pn, "eventG", "message");
        postProcessInclusiveComplexGateways(pn, "inclusiveComplexG");
        pn.updateStartListCheckingFlow(); //richiamando questa funzione sistemo i bpmn fatti male che iniziano senza uno startevent necessario quando un processo inizia ad es da un intermediate event che parte da un segnale o da un altro processo
        return pn;
    }
    

    private static float[] getBPMNElementCoordinatesXY(Document bpmnXml, String elementId) throws Exception{
        String x = (String) XMLUtils.execXPath(bpmnXml.getDocumentElement(), "//*[local-name()='BPMNShape' and @bpmnElement=" + XMLUtils.escapeXPathField(elementId) + "]/*[local-name()='Bounds']/@x", XPathConstants.STRING);
        String y = (String) XMLUtils.execXPath(bpmnXml.getDocumentElement(), "//*[local-name()='BPMNShape' and @bpmnElement=" + XMLUtils.escapeXPathField(elementId) + "]/*[local-name()='Bounds']/@y", XPathConstants.STRING);
        if(x.isEmpty()) x = "0";
        if(y.isEmpty()) y = "0";
        return new float[]{Float.valueOf(x), Float.valueOf(y)};
    }
    
    private static String getBPMNElementPool(Document bpmnXml, String elementId) throws Exception{
        return (String) XMLUtils.execXPath(bpmnXml.getDocumentElement(), "//*[local-name()='process'][.//*[@id=" + XMLUtils.escapeXPathField(elementId) + "]]/@id", XPathConstants.STRING);
    }
    
        
    public static PetriNet[] generateFromAdoxxBPMN(Document adoxxXml) throws Exception{
        
        if(!isADOXX(adoxxXml))
            throw new Exception("ERROR: The provided model is not a valid ADOXX model");
        
        PNMapping pnm = new PNMapping();
        
        pnm.addMapping("task : p>t ; in:sequence=p, message=t ; out:sequence=t, message=t, bound=p");
        pnm.addMapping("start: p(1)>t ; in: ; out:sequence=t");
        pnm.addMapping("startMsg: p>t ; in: message=p ; out:sequence=t");
        pnm.addMapping("end|terminateEventDefinition|errorEventDefinition: p ; in: sequence=p ; out: ");
        pnm.addMapping("endMsg|terminateEventDefinitionMsg|errorEventDefinitionMsg: p>t,t>p1 ; in: sequence=p ; out: message=t");
        pnm.addMapping("loop: p>t0,t0>p0,p0>t,p0>t1,t1>p ; in:sequence=p, message=t0 ; out:sequence=t, message=t0, bound=p");
        pnm.addMapping("xor|inclusiveComplexG|eventG : p ; in:sequence=p ; out:sequence=p");
        pnm.addMapping("and : t ; in:sequence=t ; out:sequence=t");
        pnm.addMapping("bound : t ; in:bound=t ; out:sequence=t");
        
        String startQuery = ".//*[@id!='' and @class='Start Event']";
        String endQuery = ".//*[@id!='' and @class='End Event']";
        String anyTaskQuery = ".//*[@id!='' and @class='Task']|.//*[@id!='' and @class='Intermediate Event (sequence flow)']|.//*[@id!='' and @class='Sub-Process']";
        String anyTaskLoopQuery = "./*[@name='Loop type' and text()!='Not specified']";
        String boundaryTaskQuery = ".//*[@id!='' and @class='Intermediate Event (boundary)']";
        String andQuery = ".//*[@id!='' and @class='Non-exclusive Gateway']|.//*[@id!='' and @class='Non-exclusive Gateway (converging)']";
        String xorQuery = ".//*[@id!='' and @class='Exclusive Gateway']";
        
        String eventGatewayQuery = ".//*[@name='Type']";
        String inclusiveComplexGatewayQuery = ".//*[@name='Gateway type']";
        String sequenceFlowQuery = ".//*[@id!='' and @class='Subsequent']";
        String messageFlowQuery = ".//*[@id!='' and @class='Message Flow']";
        String sequenceFlowExcludeFromToElemList = ""; //Se ci sono sequence flow da/verso una pool allora non li considero 
        String messageFlowExcludeFromToElemList = "Pool (collapsed) Lane"; //Se ci sono messaggi da/verso una pool allora non li considero in quanto per la verifica si assumerebbe che il messaggio sia inviato/ricevuto sempre perci� � come se non ci fosse; se si toglie, vanno mappati a parte un place con un token ed una transizione per ogni messaggio
        
        NodeList bpmnModelElList =  (NodeList) XMLUtils.execXPath(adoxxXml.getDocumentElement(), "//*[@modeltype='Business process diagram (BPMN 2.0)']", XPathConstants.NODESET);
        PetriNet[] ret = new PetriNet[bpmnModelElList.getLength()];
        
        for(int modelIndex=0;modelIndex<bpmnModelElList.getLength();modelIndex++) {
            
            pnm.resetProcessed();
            Node bpmnModelEl = bpmnModelElList.item(modelIndex);
            String modelId = bpmnModelEl.getAttributes().getNamedItem("id").getNodeValue();
            
            
            NodeList startEventNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, startQuery, XPathConstants.NODESET);
            for(int i=0;i<startEventNodeList.getLength();i++){
                String id = startEventNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                String name = startEventNodeList.item(i).getAttributes().getNamedItem("name").getNodeValue();
                NodeList messegesToStartNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, "//*[@id!='' and @class='Message Flow' and (.//@instance="+XMLUtils.escapeXPathField(name)+")]", XPathConstants.NODESET);
                String elemType = "start";
                if(messegesToStartNodeList.getLength()>0)
                    elemType += "Msg";
                float[] xy = getAdoxxElementCoordinatesXY(adoxxXml, id);
                GeneratedElements ge = pnm.processElement(id, elemType, id, xy[0], xy[1]);
                
                String poolId = getAdoxxElementPool(adoxxXml, id);
                for(PL place:ge.placeList)
                    place.addInfo("poolId", poolId);
                for(TR transition:ge.transitionList)
                    transition.addInfo("poolId", poolId);
            }
            
            NodeList endEventNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, endQuery, XPathConstants.NODESET);
            for(int i=0;i<endEventNodeList.getLength();i++){
                String id = endEventNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                String name = endEventNodeList.item(i).getAttributes().getNamedItem("name").getNodeValue();
                NodeList messegesFromEndNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, "//*[@id!='' and @class='Message Flow' and (.//@instance="+XMLUtils.escapeXPathField(name)+")]", XPathConstants.NODESET);
                String elemType = "end";
                if(messegesFromEndNodeList.getLength()>0)
                    elemType += "Msg";
                float[] xy = getAdoxxElementCoordinatesXY(adoxxXml, id);
                GeneratedElements ge = pnm.processElement(id, elemType, id, xy[0], xy[1]);
                
                String poolId = getAdoxxElementPool(adoxxXml, id);
                for(PL place:ge.placeList)
                    place.addInfo("poolId", poolId);
                for(TR transition:ge.transitionList)
                    transition.addInfo("poolId", poolId);
            }
            
            NodeList anyTaskNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, anyTaskQuery, XPathConstants.NODESET);
            for(int i=0;i<anyTaskNodeList.getLength();i++){
                String id = anyTaskNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                String elemType = "task";
                NodeList taskLoopDefinitionNodeList =  (NodeList) XMLUtils.execXPath(anyTaskNodeList.item(i), anyTaskLoopQuery, XPathConstants.NODESET);
                if(taskLoopDefinitionNodeList.getLength()>0)
                    elemType = "loop";
                float[] xy = getAdoxxElementCoordinatesXY(adoxxXml, id);
                GeneratedElements ge = pnm.processElement(id, elemType, id, xy[0], xy[1]);
                
                String poolId = getAdoxxElementPool(adoxxXml, id);
                for(PL place:ge.placeList)
                    place.addInfo("poolId", poolId);
                for(TR transition:ge.transitionList)
                    transition.addInfo("poolId", poolId);
                
            }
            
            NodeList andNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, andQuery, XPathConstants.NODESET);
            for(int i=0;i<andNodeList.getLength();i++){
                String id = andNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                String name = andNodeList.item(i).getAttributes().getNamedItem("name").getNodeValue();
                
                float[] xy = getAdoxxElementCoordinatesXY(adoxxXml, id);
                
                String gatewayType =  (String) XMLUtils.execXPath(andNodeList.item(i), inclusiveComplexGatewayQuery, XPathConstants.STRING);
                GeneratedElements ge = null;
                if(gatewayType.equals("Inclusive") || gatewayType.equals("Complex")){                    
                    String defaultSequenceFlowId =  (String) XMLUtils.execXPath(bpmnModelEl, "//*[local-name()='CONNECTOR' or local-name()='connector'][./*[local-name()='FROM' or local-name()='from']/@instance="+XMLUtils.escapeXPathField(name)+" and ./*[local-name()='ATTRIBUTE' or local-name()='attribute'][@name='Default']='Yes']/@id", XPathConstants.STRING);
                    ge = pnm.processElement(id, "inclusiveComplexG", id, xy[0], xy[1]);
                    if(ge.placeList.length==1 && ge.placeList[0]!=null)
                        ge.placeList[0].addInfo("defaultSequenceFlowId", defaultSequenceFlowId);
                }else
                    ge = pnm.processElement(id, "and", id, xy[0], xy[1]);
                
                String poolId = getAdoxxElementPool(adoxxXml, id);
                for(PL place:ge.placeList)
                    place.addInfo("poolId", poolId);
                for(TR transition:ge.transitionList)
                    transition.addInfo("poolId", poolId);
            }
            
            NodeList xorNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, xorQuery, XPathConstants.NODESET);
            for(int i=0;i<xorNodeList.getLength();i++){
                String id = xorNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                float[] xy = getAdoxxElementCoordinatesXY(adoxxXml, id);
                
                String gatewayType =  (String) XMLUtils.execXPath(xorNodeList.item(i), eventGatewayQuery, XPathConstants.STRING);
                GeneratedElements ge = null;
                if(gatewayType.toLowerCase().contains("event"))
                    ge = pnm.processElement(id, "eventG", id, xy[0], xy[1]);
                else
                    ge = pnm.processElement(id, "xor", id, xy[0], xy[1]);
                
                String poolId = getAdoxxElementPool(adoxxXml, id);
                for(PL place:ge.placeList)
                    place.addInfo("poolId", poolId);
                for(TR transition:ge.transitionList)
                    transition.addInfo("poolId", poolId);
            }
            
            NodeList boundaryTaskNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, boundaryTaskQuery, XPathConstants.NODESET);
            for(int i=0;i<boundaryTaskNodeList.getLength();i++){
                String id = boundaryTaskNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                float[] xy = getAdoxxElementCoordinatesXY(adoxxXml, id);
                GeneratedElements ge = pnm.processElement(id, "bound", id, xy[0], xy[1]);
                
                String poolId = getAdoxxElementPool(adoxxXml, id);
                for(PL place:ge.placeList)
                    place.addInfo("poolId", poolId);
                for(TR transition:ge.transitionList)
                    transition.addInfo("poolId", poolId);
                
                String sourceName = (String) XMLUtils.execXPath(boundaryTaskNodeList.item(i), "./*[@name='Attached to']//@tobjname", XPathConstants.STRING);
                if(sourceName.isEmpty()) throw new Exception("ERROR: Attribute 'Attached to' not defined for the object " + id);
                String sourceId = (String) XMLUtils.execXPath(bpmnModelEl, "//*[@id!='' and @name="+XMLUtils.escapeXPathField(sourceName)+"]/@id", XPathConstants.STRING);
                if(sourceId.isEmpty()) throw new Exception("ERROR: Can not find the object with name " + sourceName);
                GeneratedElements ge1 = pnm.processRelation(id, "bound", sourceId, id);  
                
                for(PL place:ge1.placeList)
                    place.addInfo("poolId", poolId);
                for(TR transition:ge1.transitionList)
                    transition.addInfo("poolId", poolId);
            }
            
            NodeList sequenceFlowNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, sequenceFlowQuery, XPathConstants.NODESET);
            for(int i=0;i<sequenceFlowNodeList.getLength();i++){
                String id = sequenceFlowNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                
                String sourceName = (String) XMLUtils.execXPath(sequenceFlowNodeList.item(i), "./*[local-name()='FROM' or local-name()='from']/@instance", XPathConstants.STRING);
                String sourceType = (String) XMLUtils.execXPath(sequenceFlowNodeList.item(i), "./*[local-name()='FROM' or local-name()='from']/@class", XPathConstants.STRING);
                String targetName = (String) XMLUtils.execXPath(sequenceFlowNodeList.item(i), "./*[local-name()='TO' or local-name()='to']/@instance", XPathConstants.STRING);
                String targetType = (String) XMLUtils.execXPath(sequenceFlowNodeList.item(i), "./*[local-name()='TO' or local-name()='to']/@class", XPathConstants.STRING);
                String sourceId = (String) XMLUtils.execXPath(bpmnModelEl, "//*[@id!='' and @name="+XMLUtils.escapeXPathField(sourceName)+"]/@id", XPathConstants.STRING);
                String targetId = (String) XMLUtils.execXPath(bpmnModelEl, "//*[@id!='' and @name="+XMLUtils.escapeXPathField(targetName)+"]/@id", XPathConstants.STRING);

                if(sourceId.isEmpty()) throw new Exception("ERROR: Can not identify the element with name " + sourceName + " for the relation " + id);
                if(targetId.isEmpty()) throw new Exception("ERROR: Can not identify the element with name " + targetName + " for the relation " + id);
                
                if(sequenceFlowExcludeFromToElemList.contains(sourceType) || sequenceFlowExcludeFromToElemList.contains(targetType))
                    continue;
                
                pnm.processRelation(id, "sequence", sourceId, targetId);
            }
            
            NodeList messageFlowNodeList =  (NodeList) XMLUtils.execXPath(bpmnModelEl, messageFlowQuery, XPathConstants.NODESET);
            for(int i=0;i<messageFlowNodeList.getLength();i++){
                String id = messageFlowNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                
                String sourceName = (String) XMLUtils.execXPath(messageFlowNodeList.item(i), "./*[local-name()='FROM' or local-name()='from']/@instance", XPathConstants.STRING);
                String sourceType = (String) XMLUtils.execXPath(messageFlowNodeList.item(i), "./*[local-name()='FROM' or local-name()='from']/@class", XPathConstants.STRING);
                String targetName = (String) XMLUtils.execXPath(messageFlowNodeList.item(i), "./*[local-name()='TO' or local-name()='to']/@instance", XPathConstants.STRING);
                String targetType = (String) XMLUtils.execXPath(messageFlowNodeList.item(i), "./*[local-name()='TO' or local-name()='to']/@class", XPathConstants.STRING);
                String sourceId = (String) XMLUtils.execXPath(bpmnModelEl, "//*[@id!='' and @name="+XMLUtils.escapeXPathField(sourceName)+"]/@id", XPathConstants.STRING);
                String targetId = (String) XMLUtils.execXPath(bpmnModelEl, "//*[@id!='' and @name="+XMLUtils.escapeXPathField(targetName)+"]/@id", XPathConstants.STRING);

                if(sourceId.isEmpty()) throw new Exception("ERROR: Can not identify the element with name " + sourceName + " for the relation " + id);
                if(targetId.isEmpty()) throw new Exception("ERROR: Can not identify the element with name " + targetName + " for the relation " + id);
                
                if(messageFlowExcludeFromToElemList.contains(sourceType) || messageFlowExcludeFromToElemList.contains(targetType))
                    continue;
                
                GeneratedElements genEl = pnm.processRelation(id, "message", sourceId, targetId);
                if(genEl.placeList.length>0)
                    genEl.placeList[0].excludeFromDeadlockCheck = true;
                
            }
        
            ret[modelIndex] = pnm.generatePN(modelId);
            postProcessEventGateways(ret[modelIndex], "eventG", "message");
            postProcessInclusiveComplexGateways(ret[modelIndex], "inclusiveComplexG");
            ret[modelIndex].updateStartListCheckingFlow(); //richiamando questa funzione sistemo i bpmn fatti male che iniziano senza uno startevent necessario quando un processo inizia ad es da un intermediate event che parte da un segnale o da un altro processo
        }
        
        return ret;
    }
    
    private static float[] getAdoxxElementCoordinatesXY(Document adoxxXml, String elementId) throws Exception{
        String position = (String) XMLUtils.execXPath(adoxxXml.getDocumentElement(), "//*[@id=" + XMLUtils.escapeXPathField(elementId) + "]/*[@name='Position']", XPathConstants.STRING);
        String x = position.substring(position.indexOf("x:")+2);
        x = x.substring(0, x.indexOf("cm"));
        String y = position.substring(position.indexOf("y:")+2);
        y = y.substring(0, y.indexOf("cm"));
        return new float[]{Float.valueOf(x)*29, Float.valueOf(y)*29};
    }
    
    private static String getAdoxxElementPool(Document adoxxXml, String elementId) throws Exception{
        String elementName = (String) XMLUtils.execXPath(adoxxXml.getDocumentElement(), "//*[@id=" + XMLUtils.escapeXPathField(elementId) + "]/@name", XPathConstants.STRING);
        String elementPoolName = (String) XMLUtils.execXPath(adoxxXml.getDocumentElement(), "//*[@class='Is inside'][(./*[local-name()='TO' or local-name()='to']/@class='Pool') or (./*[local-name()='TO' or local-name()='to']/@class='Pool (collapsed)')][./*[local-name()='FROM' or local-name()='from']/@instance=" + XMLUtils.escapeXPathField(elementName) + "]/*[local-name()='TO' or local-name()='to']/@instance", XPathConstants.STRING);
        String elementPoolId = (String) XMLUtils.execXPath(adoxxXml.getDocumentElement(), "//*[@name=" + XMLUtils.escapeXPathField(elementPoolName) + "]/@id", XPathConstants.STRING);
        return elementPoolId;
    }

    /*
    Per ogni event gateway vengono controllati tutti i task collegati in outgoing. Se questi task hanno uno o piu messaggi in input, questi vengono staccati dalla transition del task e riattaccati alla transition del gateway. In questo modo il gateway sceglie il task da far partire sulla base del messaggio arrivato.
    Es: p0(è eventG)>t0,p0(è eventG)>t1,t0>p1,t1>p2,p1>t2,p3>t2(è messaggio) diventa p0(è eventG)>t0,p0(è eventG)>t1,t0>p1,t1>p2,p1>t2,p3>t0(è messaggio su eventG)
    */
   private static void postProcessEventGateways(PetriNet pn, String mappingNameForEventGateway, String mappingNameForMessageRelations) throws Exception{
       for(PL place:pn.getPlaceList_safe()){
          if(!mappingNameForEventGateway.equals(place.additionalInfoList.get("elementType")))
              continue;
          for(TR nextTransition:place.getNextList_safe()) {
              if(nextTransition.nextList.size()==1)
                  if(nextTransition.nextList.get(0).nextList.size()==1){
                      TR transitionToCheck = nextTransition.nextList.get(0).nextList.get(0);
                      for(PL prevOfTransitionToCheck:transitionToCheck.getPreviousList_safe())
                          if(mappingNameForMessageRelations.equals(pn.getConnection(prevOfTransitionToCheck, transitionToCheck).additionalInfoList.get("relationType"))){
                              pn.delConnection(prevOfTransitionToCheck, transitionToCheck);
                              PT conn = pn.connect(prevOfTransitionToCheck, nextTransition);
                              conn.addInfo("relationType", mappingNameForMessageRelations);
                          }
                  }
          }
      }
   }
   
   /*
    Per ogni inclusive o complex gateway aggiungo transizioni in uscita in modo da prevedere tutte le possibili combinazioni di partenza di output flow.
    */
   private static void postProcessInclusiveComplexGateways(PetriNet pn, String mappingNameForInclusiveComplexGateway) throws Exception{
       for(PL place:pn.getPlaceList_safe()){
           if(!mappingNameForInclusiveComplexGateway.equals(place.additionalInfoList.get("elementType")))
               continue;
           String defaultSequenceFlowId = place.additionalInfoList.get("defaultSequenceFlowId");
           if(defaultSequenceFlowId==null)
               defaultSequenceFlowId = "";

           ArrayList<TR> nextTransitionList = place.getNextList_safe();
           
           if(!defaultSequenceFlowId.isEmpty()){
               for(TR nextTransition:place.nextList)
                   if(defaultSequenceFlowId.equals(pn.getConnection(place, nextTransition).additionalInfoList.get("relationId")))
                       nextTransitionList.remove(nextTransition);
           }
           int[][] binMap = Utils.generateBinaryMatrix(nextTransitionList.size());
           for(int iRow=0;iRow<binMap.length;iRow++){
               int sum=0;
               for(int iColumn=0;iColumn<binMap[iRow].length;iColumn++)
                   sum+=binMap[iRow][iColumn];
               if(sum<=1)
                   continue;
               
               TR newTr = pn.addTransition("t"+iRow+place.name);
               newTr.description = place.name;
               newTr.x = place.x;
               newTr.y = (Float.valueOf(place.y) + Float.valueOf(place.h)*(iRow+1) + 20) + "";
               pn.connect(place, newTr);
               
               for(int iColumn=0;iColumn<binMap[iRow].length;iColumn++){
                   if(binMap[iRow][iColumn]==0)
                       continue;
                   for(PL nextPlace:nextTransitionList.get(iColumn).nextList)
                       pn.connect(newTr, nextPlace);
               }
           }
       }
    }
    
    
    public static PetriNet[] generateFromAdoxxPetriNet(Document adoxxXml) throws Exception{
        
        if(!isADOXX(adoxxXml))
            throw new Exception("ERROR: The provided model is not a valid ADOXX model");
        
        PNMapping pnm = new PNMapping();
        pnm.addMapping("p : p ; in:connection=p ; out:connection=p");
        pnm.addMapping("t : t ; in:connection=t ; out:connection=t");        
        
        String placeQuery = ".//*[@id!='' and @class='Place']";
        String transitionQuery = ".//*[@id!='' and @class='Transition']";
        String relationQuery = ".//*[@id!='' and (@class='P2TRelation' or @class='T2PRelation')]";
        
        NodeList pnModelElList =  (NodeList) XMLUtils.execXPath(adoxxXml.getDocumentElement(), "//*[@modeltype='Petri Net']", XPathConstants.NODESET);
        PetriNet[] ret = new PetriNet[pnModelElList.getLength()];
        
        for(int modelIndex=0;modelIndex<pnModelElList.getLength();modelIndex++) {
            
            pnm.resetProcessed();
            Node pnModelEl = pnModelElList.item(modelIndex);
            String modelId = pnModelEl.getAttributes().getNamedItem("id").getNodeValue();
            
            
            NodeList placeNodeList =  (NodeList) XMLUtils.execXPath(pnModelEl, placeQuery, XPathConstants.NODESET);
            for(int i=0;i<placeNodeList.getLength();i++){
                String id = placeNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                float[] xy = getAdoxxElementCoordinatesXY(adoxxXml, id);
                String nTokenS = (String) XMLUtils.execXPath(placeNodeList.item(i), "./*[@name='NumOfTokens']", XPathConstants.STRING);
                int nToken = 0;
                try{
                    nToken = Integer.parseInt(nTokenS);
                }catch(Exception ex){}
                GeneratedElements genList = pnm.processElement(id, "p", id, xy[0], xy[1]);
                
                if(genList.placeList.length>0)
                    genList.placeList[0].numToken = nToken;
            }
            
            NodeList transitionNodeList =  (NodeList) XMLUtils.execXPath(pnModelEl, transitionQuery, XPathConstants.NODESET);
            for(int i=0;i<transitionNodeList.getLength();i++){
                String id = transitionNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                float[] xy = getAdoxxElementCoordinatesXY(adoxxXml, id);
                pnm.processElement(id, "t", id, xy[0], xy[1]);
            }
            
            NodeList relationNodeList =  (NodeList) XMLUtils.execXPath(pnModelEl, relationQuery, XPathConstants.NODESET);
            for(int i=0;i<relationNodeList.getLength();i++){
                String id = relationNodeList.item(i).getAttributes().getNamedItem("id").getNodeValue();
                
                String sourceName = (String) XMLUtils.execXPath(relationNodeList.item(i), "./*[local-name()='FROM' or local-name()='from']/@instance", XPathConstants.STRING);
                String targetName = (String) XMLUtils.execXPath(relationNodeList.item(i), "./*[local-name()='TO' or local-name()='to']/@instance", XPathConstants.STRING);
                String sourceId = (String) XMLUtils.execXPath(pnModelEl, "//*[@id!='' and @name="+XMLUtils.escapeXPathField(sourceName)+"]/@id", XPathConstants.STRING);
                String targetId = (String) XMLUtils.execXPath(pnModelEl, "//*[@id!='' and @name="+XMLUtils.escapeXPathField(targetName)+"]/@id", XPathConstants.STRING);

                String weight = (String) XMLUtils.execXPath(relationNodeList.item(i), "./*[@name='Weight']", XPathConstants.STRING);
                int weightN = 1;
                try{
                    weightN = Integer.parseInt(weight);
                }catch(Exception ex){}
                if(sourceId.isEmpty()) throw new Exception("ERROR: Can not identify the element with name " + sourceName + " for the relation " + id);
                if(targetId.isEmpty()) throw new Exception("ERROR: Can not identify the element with name " + targetName + " for the relation " + id);
                
                GeneratedElements elList = pnm.processRelation(id, "connection", sourceId, targetId);
                if(elList.ptList.length>0)
                    elList.ptList[0].weight = weightN;
                if(elList.tpList.length>0)
                    elList.tpList[0].weight = weightN;
            }
        
            ret[modelIndex] = pnm.generatePN(modelId);
        }
        
        return ret;
    }
    
    /*
    public static void main(String[] args) {
        try {
            String modelUrl = "D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_adoxx_02.xml";
            //PetriNet[] pnList = new PetriNet[]{generateFromBPMN(XMLUtils.getXmlDocFromURI(modelUrl))};
            PetriNet[] pnList = generateFromAdoxxBPMN(XMLUtils.getXmlDocFromURI(modelUrl));
            //PetriNet[] pnList = generateFromAdoxxPetriNet(XMLUtils.getXmlDocFromURI(modelUrl));
            //System.out.println(PNExport.exportToEldaricaP(pn));
            //System.out.println(PNExport.exportToEldaricaP_propertyDeadlockPresence(pn));
            //System.out.println(PNExport.exportToEldaricaP_propertyEndReachability(pn));
            for(PetriNet pn: pnList)
                System.out.println(PNExport.exportTo_PNML(pn));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
