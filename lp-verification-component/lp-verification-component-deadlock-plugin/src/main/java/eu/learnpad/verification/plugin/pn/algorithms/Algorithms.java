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
import eu.learnpad.verification.plugin.pn.PetriNet.PL;
import eu.learnpad.verification.plugin.pn.PetriNet.TR;

public class Algorithms {

    public static PetriNet generateReducedNet(PetriNet pn) throws Exception{
        /* Applica regole piu complesse che generano una petri net minima (potrebbe renderne pi� difficile la lettura)
         Regole:
         - T1->P->T2 = posso togliere p ed unire le T (copiare tutti i next di T2 in T1 e i previous di T2 in T1 e rimuovere T2) solo se p ha un solo next ed un solo previous e (T2 ha un solo previous o T1 ha un solo next): questa modalita riduce di piu gli stati ma in questo caso bisogna anche controllare che non esista una connessione tra un previous di t2 e t1. Se esiste non si puo applicare perche andrebbe a eliminare deadlock: Es: in questo caso non si puo ridurre: p0->t1->p->t2->p1, p0->t2
         - P1->T->P2 = posso togliere T ed unire le P (copiare tutti i previous di P1 in P2 e i next di P1 in P2 e rimuovere P1) solo se T ha un solo next ed un solo previous e (P1 ha un solo next o P2 ha almeno un next): questa modalita riduce di piu gli stati ma in questo caso bisogna anche controllare che non esista una connessione tra un next di P1 ed un next di P2. Se gia esiste non si puo applicare perche andrebbe ad eliminare deadlock: Es: in questo caso non si puo ridurre: p0->t0->p1->t1, p0->t1
         - per ogni T con un prev e un next controllare se ne esiste un altra con stesso num di prev e next e che abbia lo stesso prev e next. Se c'e' toglierla.
         - per ogni P con un prev e un next controllare se ne esiste un altro con stesso num di prev e next e che abbia lo stesso prev e next. Se c'e' toglierlo.
         poi ripetere
         */
        
        if(pn.isEmpty())
            throw new Exception("ERROR: The provided petri net is empty");
        
        PetriNet pnReduced = pn.clonePN();
        while(true){
            boolean noMoreReductions = true;
            
            while(true){
                boolean noMoreReductionsTPT = true;
                for(PL place:pnReduced.getPlaceList_safe()){
                    if(place.previousList.size()==1 && place.nextList.size()==1 && (place.nextList.get(0).previousList.size()==1 || place.previousList.get(0).nextList.size()==1)){
                        TR t1 = place.previousList.get(0);
                        TR t2 = place.nextList.get(0);
                        
                        boolean canBeApplied = true;
                        for(PL t2PrevPlace: t2.previousList)
                            if(!t2PrevPlace.equals(place))
                                if(pnReduced.existConnection(t2PrevPlace, t1))
                                    canBeApplied=false;
                        if(!canBeApplied)
                            continue;
                        
                        for(PL t2NextPlace: t2.nextList){
                            if(!pnReduced.existConnection(t1, t2NextPlace)){
                                pnReduced.connect(t1, t2NextPlace);
                                //System.out.println("Connect " + t1.name + " with " + t2NextPlace.name);
                            }
                            t2NextPlace.numToken += place.numToken;
                        }
                        
                        for(PL t2PrevPlace: t2.previousList)
                            if(!t2PrevPlace.equals(place))
                                if(!pnReduced.existConnection(t2PrevPlace, t1)){
                                    pnReduced.connect(t2PrevPlace, t1);
                                    //System.out.println("Connect " + t2PrevPlace.name + " with " + t1.name);
                                }
                        
                        pnReduced.delPlace(place);
                        pnReduced.delTransition(t2);
                        //System.out.println("Removed " + place.name + " merged " + t2.name + " with " + t1.name);
                        noMoreReductionsTPT = false;
                        noMoreReductions = false;
                    }
                }
                if(noMoreReductionsTPT)
                    break;
            }
            
            
            while(true){
                boolean noMoreReductionsPTP = true;
                for(TR transition:pnReduced.getTransitionList_safe()){
                    if(transition.previousList.size()==1 && transition.nextList.size()==1 && (transition.previousList.get(0).nextList.size()==1 || transition.nextList.get(0).nextList.size()>0)){
                        PL p1 = transition.previousList.get(0);
                        PL p2 = transition.nextList.get(0);
                        
                        boolean canBeApplied = true;
                        for(TR p2NextT: p2.nextList)
                            if(!p2NextT.equals(transition))
                                if(pnReduced.existConnection(p2, p2NextT))
                                    canBeApplied=false;
                        if(!canBeApplied)
                            continue;
                        
                        for(TR p1PreviousTransition: p1.previousList)
                            if(!pnReduced.existConnection(p1PreviousTransition, p2)){
                                pnReduced.connect(p1PreviousTransition, p2);
                                //System.out.println("Connect " + p1PreviousTransition.name + " with " + p2.name);
                            }
                        
                        for(TR p1NextT: p1.nextList)
                            if(!p1NextT.equals(transition))
                                if(!pnReduced.existConnection(p2, p1NextT)){
                                    pnReduced.connect(p2, p1NextT);
                                    //System.out.println("Connect " + p2.name + " with " + p1NextT.name);
                                }
                                
                        p2.numToken += p1.numToken;
                        pnReduced.delTransition(transition);
                        pnReduced.delPlace(p1);
                        //System.out.println("Removed " + transition.name + " merged " + p1.name + " with " + p2.name);
                        noMoreReductionsPTP = false;
                        noMoreReductions = false;
                    }
                }
                if(noMoreReductionsPTP)
                    break;
            }
            
            while(true){
                boolean noMoreReductionsPTPE = true;
                ArrayList<TR> trList = pnReduced.getTransitionList_safe();
                for(int i=0;i<trList.size();i++){
                    if(trList.get(i).previousList.size()==1 && trList.get(i).nextList.size()==1){
                        for(int j=i+1;j<trList.size();j++){                            
                            if(trList.get(j).previousList.size()==1 
                                && trList.get(j).nextList.size()==1
                                && trList.get(i).previousList.get(0).equals(trList.get(j).previousList.get(0))
                                && trList.get(i).nextList.get(0).equals(trList.get(j).nextList.get(0))){
                                
                                pnReduced.delTransition(trList.get(j));
                                //System.out.println("Removed " + trList.get(j));
                                noMoreReductionsPTPE = false;
                                noMoreReductions = false;
                            }
                        }
                    }
                }
                if(noMoreReductionsPTPE)
                    break;
            }
            
            
            while(true){
                boolean noMoreReductionsTPTE = true;
                ArrayList<PL> plList = pnReduced.getPlaceList_safe();
                for(int i=0;i<plList.size();i++){
                    if(plList.get(i).previousList.size()==1 && plList.get(i).nextList.size()==1){
                        for(int j=i+1;j<plList.size();j++){                            
                            if(plList.get(j).previousList.size()==1 
                                && plList.get(j).nextList.size()==1
                                && plList.get(i).previousList.get(0).equals(plList.get(j).previousList.get(0))
                                && plList.get(i).nextList.get(0).equals(plList.get(j).nextList.get(0))){
                                
                                pnReduced.delPlace(plList.get(j));
                                //System.out.println("Removed " + plList.get(j));
                                noMoreReductionsTPTE = false;
                                noMoreReductions = false;
                            }
                        }
                    }
                }
                if(noMoreReductionsTPTE)
                    break;
            }
            
            
            if(noMoreReductions)
                break;
        }
        
        pnReduced.finalizeModel();
        return pnReduced;
    }
    
    public static PetriNet generateReducedNet0(PetriNet pn) throws Exception{
        /* Applica regole piu leggere che non creano una petrinet minima
         Regole:
         - T1->P->T2 = posso togliere p ed unire le T (copiare tutti i next di T2 in T1 e rimuovere T2) solo se p ha un solo next ed un solo previous e T2 ha un solo previous
         - P1->T->P2 = posso togliere T ed unire le P (copiare tutti i previous di P1 in P2 e rimuovere P1) solo se T ha un solo next ed un solo previous e P1 ha un solo next
         - per ogni T con un prev e un next controllare se ne esiste un altra con stesso num di prev e next e che abbia lo stesso prev e next. Se c'e' toglierla.
         - per ogni P con un prev e un next controllare se ne esiste un altro con stesso num di prev e next e che abbia lo stesso prev e next. Se c'e' toglierlo.
         poi ripetere
         */
        
        if(pn.isEmpty())
            throw new Exception("ERROR: The provided petri net is empty");
        
        PetriNet pnReduced = pn.clonePN();
        while(true){
            boolean noMoreReductions = true;
            
            
            while(true){
                boolean noMoreReductionsTPT = true;
                for(PL place:pnReduced.getPlaceList_safe()){
                    if(place.previousList.size()==1 && place.nextList.size()==1 && place.nextList.get(0).previousList.size()==1){
                        TR t1 = place.previousList.get(0);
                        TR t2 = place.nextList.get(0);
                        
                        for(PL t2NextPlace: t2.nextList){
                            if(!pnReduced.existConnection(t1, t2NextPlace)){
                                pnReduced.connect(t1, t2NextPlace);
                                //System.out.println("Connect " + t1.name + " with " + t2NextPlace.name);
                            }
                            t2NextPlace.numToken += place.numToken;
                        }
                        
                        pnReduced.delPlace(place);
                        pnReduced.delTransition(t2);
                        //System.out.println("Removed " + place.name + " merged " + t2.name + " with " + t1.name);
                        noMoreReductionsTPT = false;
                        noMoreReductions = false;
                    }
                }
                if(noMoreReductionsTPT)
                    break;
            }
            

            while(true){
                boolean noMoreReductionsPTP = true;
                for(TR transition:pnReduced.getTransitionList_safe()){
                    if(transition.previousList.size()==1 && transition.nextList.size()==1 && (transition.previousList.get(0).nextList.size()==1)){
                        PL p1 = transition.previousList.get(0);
                        PL p2 = transition.nextList.get(0);
                        for(TR p1PreviousTransition: p1.previousList)
                            if(!pnReduced.existConnection(p1PreviousTransition, p2)){
                                pnReduced.connect(p1PreviousTransition, p2);
                                //System.out.println("Connect " + p1PreviousTransition.name + " with " + p2.name);
                            }
                        p2.numToken += p1.numToken;
                        pnReduced.delTransition(transition);
                        pnReduced.delPlace(p1);
                        //System.out.println("Removed " + transition.name + " merged " + p1.name + " with " + p2.name);
                        noMoreReductionsPTP = false;
                        noMoreReductions = false;
                    }
                }
                if(noMoreReductionsPTP)
                    break;
            }
            
            
            while(true){
                boolean noMoreReductionsPTPE = true;
                ArrayList<TR> trList = pnReduced.getTransitionList_safe();
                for(int i=0;i<trList.size();i++){
                    if(trList.get(i).previousList.size()==1 && trList.get(i).nextList.size()==1){
                        for(int j=i+1;j<trList.size();j++){                            
                            if(trList.get(j).previousList.size()==1 
                                && trList.get(j).nextList.size()==1
                                && trList.get(i).previousList.get(0).equals(trList.get(j).previousList.get(0))
                                && trList.get(i).nextList.get(0).equals(trList.get(j).nextList.get(0))){
                                
                                pnReduced.delTransition(trList.get(j));
                                //System.out.println("Removed " + trList.get(j));
                                noMoreReductionsPTPE = false;
                                noMoreReductions = false;
                            }
                        }
                    }
                }
                if(noMoreReductionsPTPE)
                    break;
            }
            
            
            while(true){
                boolean noMoreReductionsTPTE = true;
                ArrayList<PL> plList = pnReduced.getPlaceList_safe();
                for(int i=0;i<plList.size();i++){
                    if(plList.get(i).previousList.size()==1 && plList.get(i).nextList.size()==1){
                        for(int j=i+1;j<plList.size();j++){                            
                            if(plList.get(j).previousList.size()==1 
                                && plList.get(j).nextList.size()==1
                                && plList.get(i).previousList.get(0).equals(plList.get(j).previousList.get(0))
                                && plList.get(i).nextList.get(0).equals(plList.get(j).nextList.get(0))){
                                
                                pnReduced.delPlace(plList.get(j));
                                //System.out.println("Removed " + plList.get(j));
                                noMoreReductionsTPTE = false;
                                noMoreReductions = false;
                            }
                        }
                    }
                }
                if(noMoreReductionsTPTE)
                    break;
            }
            
            
            if(noMoreReductions)
                break;
        }
        
        pnReduced.finalizeModel();
        return pnReduced;
    }
    
    
    
    public static PetriNet generateUnfoldedNet(PetriNet pn) throws Exception{
        //TODO
        if(pn.isEmpty())
            throw new Exception("ERROR: The provided petri net is empty");
        
        throw new Exception("TO BE IMPLEMENTED");
    }
    
    
    /*
    public static void main(String[] args) {
        try {
            String bpmnUrl = "D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_9.bpmn";
            PetriNet pn = PNImport.generateFromBPMN(XMLUtils.getXmlDocFromURI(bpmnUrl));
            PetriNet pnReduced = Algorithms.generateReducedNet(pn);
            System.out.println(PNExport.exportTo_PNML(pn));
            System.out.println(PNExport.exportTo_PNML(pnReduced));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
