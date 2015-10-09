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

public class CoverabilityGraph {

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
        public ArrayList<Node> previousNodeList = new ArrayList<Node>(1);
        public Node(int[] markList) throws Exception{
            if(markList == null) throw new Exception("ERROR: markList can not be null");
            this.markList = markList;
        }
    }
    private Node rootNode = null;
    private ArrayList<Node> nodeList = new ArrayList<Node>();
    private ArrayList<Node> finalNodeList = new ArrayList<Node>();
    
    private PetriNet petrinet = null;
    
    public CoverabilityGraph(PetriNet pn){
        petrinet = pn;
    }
    
    private void reset(){
        rootNode = null;
        nodeList.clear();
        finalNodeList.clear();
    }
    
    public void generate() throws Exception{
        reset();
        Stack nodeToProcess = new Stack();
        int[] initialMark = petrinet.getCurrentMark();
        rootNode = new Node(initialMark);
        nodeToProcess.push(rootNode);
        
        Node currentNode = null;
        while((currentNode = nodeToProcess.pop()) != null){
            
            currentNode = addUniqueNode(currentNode);
            
            if(checkIfEqualToAPreviousNode(currentNode)){
                finalNodeList.add(currentNode);
                continue;
            }
            
            ArrayList<TR> transitionEnabledList = petrinet.getEnabledTransitions(currentNode.markList);
            if(transitionEnabledList.isEmpty())
                finalNodeList.add(currentNode);
            
            for(TR transitionEnabled:transitionEnabledList){
                petrinet.setMark(currentNode.markList);
                petrinet.fireTransition(transitionEnabled);
                int[] markAfterTransitionFire = petrinet.getCurrentMark();
                petrinet.setMark(currentNode.markList);
                Node nextNode = new Node(markAfterTransitionFire);
                nextNode.previousNodeList.add(currentNode);
                
                Node previousNodeToAccelerate = null;
                if((previousNodeToAccelerate = checkPreviousNodeToAccelerate(nextNode))!=null)
                    accelerateNodeMarks(previousNodeToAccelerate.markList, nextNode.markList);
                                
                nodeToProcess.push(nextNode);
            }
        }
        petrinet.setMark(initialMark);
    }

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
        Stack previousNodeStack = new Stack();
        for(Node prev: currentNode.previousNodeList)
            previousNodeStack.push(prev);
        Node previousNode = null;
        while((previousNode=previousNodeStack.pop())!=null){
            int compareRes = compareNodeMarks(previousNode.markList, currentNode.markList);
            if(compareRes == 0)
                return true;
            for(Node prev: previousNode.previousNodeList)
                previousNodeStack.push(prev);
        }
        return false;
    }
    
    private Node checkPreviousNodeToAccelerate(Node currentNode) throws Exception{
        Stack previousNodeStack = new Stack();
        for(Node prev: currentNode.previousNodeList)
            previousNodeStack.push(prev);
        Node previousNode = null;
        while((previousNode=previousNodeStack.pop())!=null){
            int compareRes = compareNodeMarks(previousNode.markList, currentNode.markList);
            if(compareRes > 0)
                return previousNode;
            for(Node prev: previousNode.previousNodeList)
                previousNodeStack.push(prev);
        }
        return null;
    }
    
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
    
    private Node addUniqueNode(Node nodeToCheck){
        for(Node node:nodeList){
            int i=0;
            for(i=0;i<node.markList.length;i++)
                if(node.markList[i]!=nodeToCheck.markList[i])
                    break;
            if(i==node.markList.length){
                node.previousNodeList.addAll(nodeToCheck.previousNodeList);
                return node;
            }
        }
        nodeList.add(nodeToCheck);
        return nodeToCheck;
    }
    
    /*
    public static void main(String[] args) {
        
        try {
            String bpmnUrl = "D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_1.bpmn";
            //String bpmnUrl = "D:\\TOOLS\\eclipse\\workspace\\PN\\testModels\\test_1.bpmn";
            PetriNet pn = PNImport.generateFromBPMN(XMLUtils.getXmlDocFromURI(bpmnUrl));
            
            CoverabilityGraph cg = new CoverabilityGraph(pn);
            cg.generate();
            System.out.println(cg.nodeList.size());
            System.out.println(cg.finalNodeList.size());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
