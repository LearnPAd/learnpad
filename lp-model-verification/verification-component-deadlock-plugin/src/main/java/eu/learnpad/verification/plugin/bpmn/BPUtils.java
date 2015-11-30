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

package eu.learnpad.verification.plugin.bpmn;

import eu.learnpad.verification.plugin.pn.PetriNet;
import eu.learnpad.verification.plugin.pn.PetriNet.PL;
import eu.learnpad.verification.plugin.pn.PetriNet.TR;

public class BPUtils {
    
    /*
     Ritorna tutti i places che abilitano l'oggetto BPIdObject (funzione dipendente dal mapping usato per BPMN)
     
    public static String[] getPNIdsFromBPMNId2(PetriNet pn, String bpIdObject){
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
    */
    //Ritorna tutti i places che abilitano l'oggetto BPIdObject
    public static String[] getPNIdsFromBPMNId(PetriNet pn, String bpIdObject){
        String[] pnIdObject = new String[0];
        for(PL place:pn.getPlaceList_safe())
            if(place.description.equals(bpIdObject) && place.additionalInfoList.containsKey("isEntryPoint") && place.additionalInfoList.get("isEntryPoint").equals("true")){
                pnIdObject = new String[]{place.name};
                break;
            }

        if(pnIdObject.length==0)
            for(TR transition:pn.getTransitionList_safe())
                if(transition.description.equals(bpIdObject) && transition.previousList.size()!=0  && transition.additionalInfoList.containsKey("isEntryPoint") && transition.additionalInfoList.get("isEntryPoint").equals("true")){
                    pnIdObject = new String[transition.previousList.size()];
                    for(int i=0;i<transition.previousList.size();i++)
                        pnIdObject[i] = transition.previousList.get(i).name;
                    break;
                }
        return pnIdObject;
    }
    
    public static boolean existBPMNObject(PetriNet pn, String bpIdObject){
        for(PL place:pn.getPlaceList_safe())
            if(place.description.equals(bpIdObject) && place.additionalInfoList.containsKey("isEntryPoint") && place.additionalInfoList.get("isEntryPoint").equals("true"))
                return true;
        for(TR transition:pn.getTransitionList_safe())
            if(transition.description.equals(bpIdObject) && transition.previousList.size()!=0  && transition.additionalInfoList.containsKey("isEntryPoint") && transition.additionalInfoList.get("isEntryPoint").equals("true"))
                return true;
        return false;
    }
}
