package eu.learnpad.verification.plugin.bpmn;

import eu.learnpad.verification.plugin.pn.PetriNet;
import eu.learnpad.verification.plugin.pn.PetriNet.PL;
import eu.learnpad.verification.plugin.pn.PetriNet.TR;

public class BPUtils {
    
    /*
     Ritorna tutti i places che abilitano l'oggetto BPIdObject (funzione dipendente dal mapping usato per BPMN)
     */
    public static String[] getPNIdsFromBPMNId(PetriNet pn, String bpIdObject){
        String[] pnIdObject = new String[0];
        for(PL place:pn.getPlaceList_safe()){
            if(place.description.equals(bpIdObject)){
                if(place.nextList.size()==1 && place.nextList.get(0).description.equals(bpIdObject)){
                    pnIdObject = new String[place.nextList.get(0).previousList.size()];
                    for(int i=0;i<place.nextList.get(0).previousList.size();i++)
                        pnIdObject[i] = place.nextList.get(0).previousList.get(i).name;
                }else{
                    pnIdObject = new String[]{place.name};
                }
                break;
            }
        }
        if(pnIdObject.length==0)
            for(TR transition:pn.getTransitionList_safe())
                if(transition.description.equals(bpIdObject)){
                    if(transition.previousList.size()!=0){
                        pnIdObject = new String[transition.previousList.size()];
                        for(int i=0;i<transition.previousList.size();i++)
                            pnIdObject[i] = transition.previousList.get(i).name;
                        break;
                    }
                }
        return pnIdObject;
    }
    
    public static boolean existBPMNObject(PetriNet pn, String bpIdObject){
        for(PL place:pn.getPlaceList_safe())
            if(place.description.equals(bpIdObject))
                return true;
        for(TR transition:pn.getTransitionList_safe())
            if(transition.description.equals(bpIdObject) && transition.previousList.size()!=0)
                return true;
        return false;
    }
}
