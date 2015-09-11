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

import eu.learnpad.verification.plugin.pn.PetriNet;
import eu.learnpad.verification.plugin.pn.PetriNet.PL;
import eu.learnpad.verification.plugin.pn.PetriNet.PT;
import eu.learnpad.verification.plugin.pn.PetriNet.TP;
import eu.learnpad.verification.plugin.pn.PetriNet.TR;
import eu.learnpad.verification.plugin.pn.impexp.PNMapping.MapElement.FromTo;
import eu.learnpad.verification.plugin.pn.impexp.PNMapping.MapElement.RelationElement;

public class PNMapping {
    
    static class MapElement{
        static class FromTo{ String from=""; String to=""; int placeToken=0;}
        static class RelationElement{ String relationType=""; String element="";}
        String[] typeList=new String[0];
        FromTo[] mappingFromToList=new FromTo[0];
        RelationElement[] inRelationElementList=new RelationElement[0];
        RelationElement[] outRelationElementList=new RelationElement[0];
        public String getInRelationElement(String relationType) throws Exception{
            for(RelationElement relationElement: inRelationElementList)
                if(relationElement.relationType.equals(relationType))
                    return relationElement.element;
            throw new Exception("ERROR: input relation of type " + relationType + " not defined for element of type " + getTypes());
        }
        public String getOutRelationElement(String relationType) throws Exception{
            for(RelationElement relationElement: outRelationElementList)
                if(relationElement.relationType.equals(relationType))
                    return relationElement.element;
            throw new Exception("ERROR: output relation of type " + relationType + " not defined for element of type " + getTypes());
        }
        public String getTypes(){
            String ret = "";
            for(String type:typeList)
                ret += type+"|";
            return ret;
        }
    }
    
    static class GeneratedElements{
        PL[] placeList=new PL[0];
        TR[] transitionList=new TR[0];
        PT[] ptList=new PT[0];
        TP[] tpList=new TP[0];
    }
    static class ProcessedElement{
        String elementId="";
        String elementType="";
        String elementDescription="";
        float x=0,y=0;
        public ProcessedElement(String elementId, String elementType, String elementDescription, float x, float y){
            this.elementId = elementId; this.elementType = elementType; this.elementDescription = elementDescription; this.x = x; this.y = y;
        } 
    }
    
    private ArrayList<MapElement> mapElementList = new ArrayList<PNMapping.MapElement>();
    private ArrayList<ProcessedElement> processedElementList = new ArrayList<PNMapping.ProcessedElement>();
    private PetriNet petriNet = new PetriNet("");
    
    public void resetProcessed(){
        processedElementList.clear();
        petriNet = new PetriNet("");
    }
    
    public MapElement getMapElement(String type) throws Exception{
        if(type==null || type.isEmpty())
            throw new Exception("ERROR: parameter type is empty");
        for(MapElement element:mapElementList)
            for(String typeEl: element.typeList)
                if(typeEl.equals(type))
                    return element;
        throw new Exception("ERROR: element " + type + " not defined.");
    }
    
    public String getFirstExistentMapElement(String[] typeList) throws Exception{
        if(typeList==null)
            throw new Exception("ERROR: parameter typeList is null");
        for(String type: typeList)
            if(existMapElement(type))
                return type;
        return null;
    }
    public boolean existMapElement(String[] typeList) throws Exception{
        if(typeList==null)
            throw new Exception("ERROR: parameter typeList is null");
        for(String type: typeList)
            if(existMapElement(type))
                return true;
        return false;
    }
    public boolean existMapElement(String type) throws Exception{
        if(type==null || type.isEmpty())
            throw new Exception("ERROR: parameter type is empty");
        for(MapElement element:mapElementList)
            for(String typeEl: element.typeList)
                if(typeEl.equals(type))
                    return true;
        return false;
    }
    
    public void addMapping(String mappingFormula) throws Exception{
        /*
         *  Formula Syntax: 
         *  Formula = MapRules ";" InputRelations ";" OutputRelations
         *  MapRules = elementTypeList ":" RuleList
         *  InputRelations = "in :" relationList
         *  OutputRelations = "out :" relationList
         *  elementTypeList = ElementTypeName [ "|" elementTypeList ]
         *  ElementTypeName: the type name of the object you want to map
         *  RuleList = Rule [ "," RuleList ]
         *  Rule = from ">" to
         *  from: name of the PN element you want to create/use for connection with the "to" element; this name have to start with "p" in order to refer to a place or with "t" in order to refer to a transition
         *  to: name of the PN element you want to create/use for connection with the "from" element; this name have to start with "p" in order to refer to a place or with "t" in order to refer to a transition
         *  relationList = relation [ "," relationList]
         *  relation = RelationType "=" PNElementName
         *  RelationType: name of the relation type you want to map
         *  PNElementName: name of the PN element defined in the from/to you want to use for the relation
         *  
         *  es: 
         *  Start: p(1)>t ; in:message=p ; out: sequence=t
         *  Task|Intermediate: p>t ; in: sequence=p, message=t ; out: sequence=t, message=t
         *  End: p ; in: sequence=p ; out:
         *  msgEnd: p>t,t>p1 ; in: sequence=p ; out: message=t
         * */
        
        if(mappingFormula==null)
            throw new Exception("ERROR: Formula is null");
        
        MapElement element = new MapElement();
        mappingFormula = mappingFormula.replaceAll("( |-)+", "");
        
        String[] formulaSentenceList = mappingFormula.split(";");
        
        if(formulaSentenceList.length!=3)
            throw new Exception("ERROR: Formula Syntax Error; Expected 3 \";\" in the formula");
        
        element.typeList = formulaSentenceList[0].split(":")[0].split("\\|");
        
        String[] ruleList = new String[0];
        if(formulaSentenceList[0].split(":").length>1)
            ruleList = formulaSentenceList[0].split(":")[1].split(",");
        
        ArrayList<FromTo> fromToListA = new ArrayList<PNMapping.MapElement.FromTo>();
        for(String rule:ruleList){
            FromTo fromTo = new FromTo();
            fromTo.from = rule.split(">")[0];
            if(fromTo.from.contains("(")){
                fromTo.placeToken = Integer.parseInt(fromTo.from.substring(fromTo.from.indexOf("(")+1, fromTo.from.indexOf(")")));
                fromTo.from = fromTo.from.split("\\(")[0];
            }
            if(rule.split(">").length>1){
                fromTo.to = rule.split(">")[1];
                if(fromTo.to.contains("(")){
                    fromTo.placeToken = Integer.parseInt(fromTo.to.substring(fromTo.to.indexOf("(")+1, fromTo.to.indexOf(")")));
                    fromTo.to = fromTo.to.split("\\(")[0];
                }
            }
            fromToListA.add(fromTo);
        }
        element.mappingFromToList = new FromTo[fromToListA.size()];
        fromToListA.toArray(element.mappingFromToList);
        
        
        ArrayList<RelationElement> inRelationElementListA = new ArrayList<PNMapping.MapElement.RelationElement>();
        String[] inRelElRowList = new String[0];
        if(formulaSentenceList[1].split(":").length>1)
            inRelElRowList = formulaSentenceList[1].split(":")[1].split(",");
        for(String relElRow: inRelElRowList){
            RelationElement relationElement = new RelationElement();
            if(relElRow.split("=").length!=2)
                throw new Exception("ERROR: Formula Syntax Error; Expected 1 \"=\" in the formula: " + relElRow);
            relationElement.relationType = relElRow.split("=")[0];
            relationElement.element = relElRow.split("=")[1];
            inRelationElementListA.add(relationElement);
        }
        element.inRelationElementList = new RelationElement[inRelationElementListA.size()];
        inRelationElementListA.toArray(element.inRelationElementList);
        
        ArrayList<RelationElement> outRelationElementListA = new ArrayList<PNMapping.MapElement.RelationElement>();
        String[] outRelElRowList = new String[0];
        if(formulaSentenceList[2].split(":").length>1)
            outRelElRowList = formulaSentenceList[2].split(":")[1].split(",");
        for(String relElRow: outRelElRowList){
            RelationElement relationElement = new RelationElement();
            if(relElRow.split("=").length!=2)
                throw new Exception("ERROR: Formula Syntax Error; Expected 1 \"=\" in the formula: " + relElRow);
            relationElement.relationType = relElRow.split("=")[0];
            relationElement.element = relElRow.split("=")[1];
            outRelationElementListA.add(relationElement);
        }
        element.outRelationElementList = new RelationElement[outRelationElementListA.size()];
        outRelationElementListA.toArray(element.outRelationElementList);
        
        addMapping(element);
    }
    
    public void addMapping(MapElement element) throws Exception{
        if(element==null)
            throw new Exception("ERROR: parameter element is null");
        if(existMapElement(element.typeList))
            throw new Exception("ERROR: Type " + getFirstExistentMapElement(element.typeList) + " defined more than once.");
        mapElementList.add(element);
    }
    
    public GeneratedElements processElement(String elementId, String elementType, String elementDescription, float x, float y) throws Exception{
        if(elementId==null || elementId.isEmpty() || elementType==null || elementType.isEmpty())
            throw new Exception("ERROR: incorrect data provided for processing element:\nelementId: "+elementId+"\nelementType: "+elementType+"\nelementDescription: "+elementDescription);
        return processElement(new ProcessedElement(elementId, elementType, elementDescription, x, y));
    }
    
    public GeneratedElements processElement(ProcessedElement element) throws Exception{
        if(element==null)
            throw new Exception("ERROR: parameter element is null");
        GeneratedElements ret = new GeneratedElements();
        
        MapElement map = getMapElement(element.elementType);
        
        ArrayList<PL> retPLListA = new ArrayList<PetriNet.PL>();
        ArrayList<TR> retTRListA = new ArrayList<PetriNet.TR>();
        ArrayList<PT> retPTListA = new ArrayList<PetriNet.PT>();
        ArrayList<TP> retTPListA = new ArrayList<PetriNet.TP>();
        int index = 0;
        for(FromTo rule:map.mappingFromToList){

            String fromName = rule.from + element.elementId;
            String toName = "";
            if(!rule.to.isEmpty())
                toName = rule.to + element.elementId;
            
            if(fromName.toLowerCase().startsWith("p")){
                PL from = null;
                TR to = null;
                if(!petriNet.existPlace(fromName)){
                    from = petriNet.addPlace(fromName, rule.placeToken);
                    from.desciption = element.elementDescription;
                    from.x = element.x+"";
                    from.y = (element.y+(Float.valueOf(from.h)+20)*index) + "";
                    index++;
                }else
                    from = petriNet.getPlace(fromName);
                
                retPLListA.add(from);
                
                if(!rule.to.isEmpty()){
                    if(!petriNet.existTransition(toName)){
                        to = petriNet.addTransition(toName);
                        to.desciption = element.elementDescription;
                        to.x = element.x+"";
                        to.y = (element.y+(Float.valueOf(to.h)+20)*index) + "";
                        index++;
                    }else
                        to = petriNet.getTransition(toName);
                    retTRListA.add(to);
                    PT conn = petriNet.connect(from, to);
                    retPTListA.add(conn);
                }
            } else {
                TR from = null;
                PL to = null;
                if(!petriNet.existTransition(fromName)){
                    from = petriNet.addTransition(fromName);
                    from.desciption = element.elementDescription;
                    from.x = element.x+"";
                    from.y = (element.y+(Float.valueOf(from.h)+20)*index) + "";
                    index++;
                }else
                    from = petriNet.getTransition(fromName);
                retTRListA.add(from);
                
                if(!rule.to.isEmpty()){
                    if(!petriNet.existPlace(toName)){
                        to = petriNet.addPlace(toName, rule.placeToken);
                        to.desciption = element.elementDescription;
                        to.x = element.x+"";
                        to.y = (element.y+(Float.valueOf(to.h)+20)*index) + "";
                        index++;
                    }else
                        to = petriNet.getPlace(toName);
                    retPLListA.add(to);
                    TP conn = petriNet.connect(from, to);
                    retTPListA.add(conn);
                }
            }
        }
        ret.placeList = new PL[retPLListA.size()];
        retPLListA.toArray(ret.placeList);
        ret.transitionList = new TR[retTRListA.size()];
        retTRListA.toArray(ret.transitionList);
        ret.ptList = new PT[retPTListA.size()];
        retPTListA.toArray(ret.ptList);
        ret.tpList = new TP[retTPListA.size()];
        retTPListA.toArray(ret.tpList);
        processedElementList.add(element);
        return ret;
    }
    
    private ProcessedElement getProcessedElement(String elementId) throws Exception{
        if(elementId==null || elementId.isEmpty())
            throw new Exception("ERROR: parameter elementId is empty");
        for(ProcessedElement processedElement:processedElementList)
            if(processedElement.elementId.equals(elementId))
                return processedElement;
        throw new Exception("ERROR: element " + elementId + " is not been processed");
    }
    
    public GeneratedElements processRelation(String relationId, String relationType, String elementFromId, String elementToId) throws Exception{
        if(relationId==null || relationId.isEmpty() || relationType==null || relationType.isEmpty() || elementFromId==null || elementFromId.isEmpty() || elementToId==null || elementToId.isEmpty())
            throw new Exception("ERROR: incorrect data provided for processing relation:\nrelationId: "+relationId+"\nrelationType: " +relationType+"\nelementFromId: "+elementFromId+"\nelementToId: "+elementToId);
        
        GeneratedElements ret = new GeneratedElements();
        ArrayList<PL> retPLListA = new ArrayList<PetriNet.PL>();
        ArrayList<TR> retTRListA = new ArrayList<PetriNet.TR>();
        ArrayList<PT> retPTListA = new ArrayList<PetriNet.PT>();
        ArrayList<TP> retTPListA = new ArrayList<PetriNet.TP>();
        
        MapElement mapFrom = getMapElement(getProcessedElement(elementFromId).elementType);
        MapElement mapTo = getMapElement(getProcessedElement(elementToId).elementType);
        
        String mapFromElementPrefix = mapFrom.getOutRelationElement(relationType);
        String mapToElementPrefix = mapTo.getInRelationElement(relationType);
        
        String mapFromElementName = mapFromElementPrefix + elementFromId;
        String mapToElementName = mapToElementPrefix + elementToId;
        
        if(mapFromElementName.toLowerCase().startsWith("p")){
            if(mapToElementName.toLowerCase().startsWith("p")){
                PL from = petriNet.getPlace(mapFromElementName);
                PL to = petriNet.getPlace(mapToElementName);
                TR intermediate = petriNet.addTransition("t" + relationId);
                PT conn1 = petriNet.connect(from, intermediate);
                TP conn2 = petriNet.connect(intermediate, to);
                intermediate.x = from.x;
                intermediate.y = (Float.valueOf(from.y) + Float.valueOf(from.h) + 20) + "";
                intermediate.desciption = from.desciption;
                retTRListA.add(intermediate);
                retPTListA.add(conn1);
                retTPListA.add(conn2);
            } else {
                PL from = petriNet.getPlace(mapFromElementName);
                TR to = petriNet.getTransition(mapToElementName);
                PT conn = petriNet.connect(from, to);
                retPTListA.add(conn);
            }
        } else {
            if(mapToElementName.toLowerCase().startsWith("p")){
                TR from = petriNet.getTransition(mapFromElementName);
                PL to = petriNet.getPlace(mapToElementName);
                TP conn = petriNet.connect(from, to);
                retTPListA.add(conn);
            } else {
                TR from = petriNet.getTransition(mapFromElementName);
                TR to = petriNet.getTransition(mapToElementName);
                PL intermediate = petriNet.addPlace("p" + relationId);
                TP conn1 = petriNet.connect(from, intermediate);
                PT conn2 = petriNet.connect(intermediate, to);
                intermediate.x = from.x;
                intermediate.y = (Float.valueOf(from.y) + Float.valueOf(from.h) + 20) + "";
                intermediate.desciption = from.desciption;
                retPLListA.add(intermediate);
                retTPListA.add(conn1);
                retPTListA.add(conn2);
            }
        }
        
        ret.placeList = new PL[retPLListA.size()];
        retPLListA.toArray(ret.placeList);
        ret.transitionList = new TR[retTRListA.size()];
        retTRListA.toArray(ret.transitionList);
        ret.ptList = new PT[retPTListA.size()];
        retPTListA.toArray(ret.ptList);
        ret.tpList = new TP[retTPListA.size()];
        retTPListA.toArray(ret.tpList);
        
        return ret;
    }
    
    public PetriNet generatePN(String name){
        petriNet.name = name;
        petriNet.finalizeModel();
        return petriNet;
    }
    
    /*
    public static void main(String[] args) {
        try {
            PNMapping pnm = new PNMapping();

            pnm.addMapping("task|intermediate: p>t ; in:sequence=p, message=t ; out:sequence=t, message=t");
            pnm.addMapping("start|: p(1)>t ; in:sequence=p, message=p ; out:sequence=t");
            pnm.addMapping("end: p>t,t>p1 ; in: sequence=p ; out: message=t");
            pnm.addMapping("loop: p>t0,t0>p0,p0>t,p0>t1,t1>p ; in:sequence=p, message=t0 ; out:sequence=t, message=t0");
            pnm.addMapping("xor: p ; in: sequence=p ; out: sequence=p");
            
            pnm.processElement("start0", "start", "", 0, 0);
            pnm.processElement("start1", "start", "", 0, 0);
            pnm.processElement("task0", "task", "", 0, 0);
            pnm.processElement("task1", "loop", "", 0, 0);
            pnm.processElement("end0", "end", "", 0, 0);
            pnm.processElement("end1", "end", "", 0, 0);
            
            pnm.processRelation("seq1", "sequence", "start0","task0");
            pnm.processRelation("seq2", "sequence", "start1", "task1");
            pnm.processRelation("seq3", "sequence", "task0", "end0");
            pnm.processRelation("seq4", "sequence", "task1", "end1");
            
            GeneratedElements ret = pnm.processRelation("msg1", "message", "task1", "task0");
            ret.placeList[0].excludeFromDeadlockCheck = true;
            
            PetriNet pn = pnm.generatePN("test");
            System.out.println(PNExport.exportTo_LOLA_property_DeadlockPresence(pn));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
