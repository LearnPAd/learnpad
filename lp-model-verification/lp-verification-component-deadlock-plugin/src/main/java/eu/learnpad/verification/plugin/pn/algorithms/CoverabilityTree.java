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

package eu.learnpad.verification.plugin.pn.algorithms;

import java.util.ArrayList;

import eu.learnpad.verification.plugin.pn.PetriNet;
import eu.learnpad.verification.plugin.pn.PetriNet.TR;

public class CoverabilityTree {

    private class Stack{
        private ArrayList<Node> stack = new ArrayList<Node>();
        public void push(Node node){
            stack.add(node);
        }
        public Node pop(){
            if(stack.isEmpty())
                return null;
            Node ret = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return ret;
        }
    }
    
    private class Node{
        public int[] markList = null;
        //public ArrayList<Node> nextList = new ArrayList<Node>();
        public Node previousNode = null;
        //public Node referenceNode = null;
        //public TR transitionFired = null;
        //public boolean isActive = true;
        //boolean noTransitionsEnables = false;
        public Node(int[] markList) throws Exception{
            if(markList == null) throw new Exception("ERROR: markList can not be null");
            this.markList = markList;
        }
    }
    private Node rootNode = null;
    private ArrayList<int[]> marksList = new ArrayList<int[]>(500);
    //private ArrayList<Node> nodeList = new ArrayList<Node>();
    private ArrayList<Node> finalNodeList = new ArrayList<Node>(500);
    
    private PetriNet petrinet = null;
    
    public CoverabilityTree(PetriNet pn){
        petrinet = pn;
    }
    
    /*
    private void reset(){
        rootNode = null;
        //nodeList.clear();
        finalNodeList.clear();
        marksList.clear();
    }
    */
    
    public void generate() throws Exception{
        //reset();
        Stack nodeToProcess = new Stack();
        int[] initialMark = petrinet.getCurrentMark();
        marksList.add(initialMark);
        rootNode = new Node(initialMark);
        nodeToProcess.push(rootNode);
        
        Node currentNode = null;
        while((currentNode = nodeToProcess.pop()) != null){
            //nodeList.add(currentNode);
            
            if(checkIfEqualToAPreviousNode(currentNode)){
                //finalNodeList.add(currentNode);
                //currentNode.noTransitionsEnables = false;
                addUniqueFinalNode(currentNode);
                continue;
            }
            
            ArrayList<TR> transitionEnabledList = petrinet.getEnabledTransitions(currentNode.markList);
            if(transitionEnabledList.isEmpty()){
                //finalNodeList.add(currentNode);
                //currentNode.noTransitionsEnables = true;
                addUniqueFinalNode(currentNode);
            }
            
            for(TR transitionEnabled:transitionEnabledList){
                petrinet.setMark(currentNode.markList);
                petrinet.fireTransition(transitionEnabled);
                int[] markAfterTransitionFire = petrinet.getCurrentMark();
                petrinet.setMark(currentNode.markList);
                Node nextNode = new Node(markAfterTransitionFire);
                nextNode.previousNode = currentNode;
                //nextNode.transitionFired = transitionEnabled;
                
                Node previousNodeToAccelerate = null;
                if((previousNodeToAccelerate = checkPreviousNodeToAccelerate(nextNode))!=null)
                    accelerateNodeMarks(previousNodeToAccelerate.markList, nextNode.markList);
                
                nextNode.markList = getUniqueMarks(nextNode.markList);
                
                nodeToProcess.push(nextNode);
            }
        }
        petrinet.setMark(initialMark);
    }
    /*
    public ArrayList<int[]> getDistinctFinalMarkList(){
         ArrayList<int[]> ret = new ArrayList<int[]>();
         for(Node finalNode:finalNodeList)
             if(!ret.contains(finalNode.markList))
                 ret.add(finalNode.markList);
         return ret;
    }
    */
    private int compareNodeMarks(int[] previousMark, int[] currentMark) throws Exception{
        if(previousMark.length != currentMark.length)
            throw new Exception("ERROR: The mark size have to be the same");
        int ret = 0; 
        for(int i=0;i<currentMark.length;i++){
            if(currentMark[i]!=-1 && (currentMark[i]<previousMark[i] || previousMark[i]==-1))
                return -1;
            if(currentMark[i]!=previousMark[i])
                ret = 1;
        }
        return ret;
    }
    
    private boolean checkIfEqualToAPreviousNode(Node currentNode) throws Exception{
        Node previousNode = currentNode.previousNode;
        while(previousNode!=null){
            int compareRes = compareNodeMarks(previousNode.markList, currentNode.markList);
            if(compareRes == 0)
                return true;
            previousNode = previousNode.previousNode;
        }
        return false;
    }
    
    private Node checkPreviousNodeToAccelerate(Node currentNode) throws Exception{
        Node previousNode = currentNode.previousNode;
        while(previousNode!=null){
            int compareRes = compareNodeMarks(previousNode.markList, currentNode.markList);
            if(compareRes > 0)
                return previousNode;
            previousNode = previousNode.previousNode;
        }
        return null;
    }
    /*
    private Node checkPreviousNodeToAccelerate2(Node currentNode) throws Exception{
        Node previousNode = currentNode.previousNode;
        Node ret = null;
        while(previousNode!=null){
            int compareRes = compareNodeMarks(previousNode.markList, currentNode.markList);
            if(compareRes > 0)
                ret = previousNode;
            previousNode = previousNode.previousNode;
        }
        return ret;
    }
    */
    private void accelerateNodeMarks(int[] previousMark, int[] currentMark) throws Exception{
        if(previousMark.length != currentMark.length)
            throw new Exception("ERROR: The mark size have to be the same");
        if(compareNodeMarks(previousMark, currentMark)<=0)
            throw new Exception("ERROR: Nothing to accelerate");
        
        for(int i=0;i<currentMark.length;i++){
            if(currentMark[i]!=-1 && (currentMark[i]>previousMark[i] && previousMark[i]!=-1))
                currentMark[i]=-1;
        }
    }
    
    private int[] getUniqueMarks(int[] markToCheck){
        for(int[] mark:marksList){
            int i=0;
            for(i=0;i<mark.length;i++)
                if(mark[i]!=markToCheck[i])
                    break;
            if(i==mark.length)
                return mark;
        }
        
        marksList.add(markToCheck);
        return markToCheck;
    }
    
    private void addUniqueFinalNode(Node node) throws Exception{
        //int i=0;
        for(Node finalNode: finalNodeList){
            if(finalNode.markList == node.markList)
                return;
            /*
            int compareRes = compareNodeMarks(node.markList, finalNode.markList);
            if(compareRes > 0)
                return;
            compareRes = compareNodeMarks(finalNode.markList, node.markList);
            if(compareRes > 0){
                finalNodeList.set(i, node);
                return;
            }
            */
            //i++;
        }
        finalNodeList.add(node);
    }
    /*
    public static void main(String[] args) {
        
        try {
            String bpmnUrl = "D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_1.bpmn";
            //String bpmnUrl = "D:\\TOOLS\\eclipse\\workspace\\PN\\testModels\\test_1.bpmn";
            PetriNet pn = PNImport.generateFromBPMN(XMLUtils.getXmlDocFromURI(bpmnUrl));
            
            CoverabilityTree ct = new CoverabilityTree(pn);
            ct.generate();
            //System.out.println(ct.nodeList.size());
            System.out.println(ct.marksList.size());
            System.out.println(ct.finalNodeList.size());
            
            for(PetriNet.PL p: pn.getPlaceList_safe())
                System.out.print(p.name + " ");
            System.out.println(" ");
            for(Node n: ct.finalNodeList){
                for(int i:n.markList)
                    System.out.print(i+" ");
                System.out.print(" " + n.noTransitionsEnables + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
