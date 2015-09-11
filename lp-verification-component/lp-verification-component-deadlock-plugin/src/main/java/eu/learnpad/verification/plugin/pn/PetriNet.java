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

package eu.learnpad.verification.plugin.pn;

import java.util.ArrayList;
import java.util.HashMap;

public class PetriNet implements java.io.Serializable{

    private static final long serialVersionUID = 3426953988681102210L;

    public class PL implements java.io.Serializable{
        private static final long serialVersionUID = 5172052386953796272L;
        public String name = "";
        public String desciption = "";
        public int numToken = 0;
        public boolean excludeFromDeadlockCheck = false;
        public String x="0",y="0",w="40.0",h="40.0";
        public ArrayList<TR> previousList = new ArrayList<TR>();
        public ArrayList<TR> nextList = new ArrayList<TR>();
        public HashMap<String, String> additionalInfoList = new HashMap<String, String>();
        public PL(String name){
            this.name = name.replaceAll("(\\W|_)+", "");
        }
        @SuppressWarnings("unchecked")
        public ArrayList<TR> getPreviousList_safe(){ return (ArrayList<TR>) previousList.clone(); }
        @SuppressWarnings("unchecked")
        public ArrayList<TR> getNextList_safe(){ return (ArrayList<TR>) nextList.clone(); }
        public void addInfo(String name, String value){ additionalInfoList.put(name, value); }
    }
    public class TR implements java.io.Serializable{
        private static final long serialVersionUID = 6525281606058042758L;
        public String name = "";
        public String desciption = "";
        public String x="0",y="0",w="40.0",h="40.0";
        public ArrayList<PL> previousList = new ArrayList<PL>();
        public ArrayList<PL> nextList = new ArrayList<PL>();
        public HashMap<String, String> additionalInfoList = new HashMap<String, String>();
        public TR(String name){
            this.name = name.replaceAll("(\\W|_)+", "");
        }
        @SuppressWarnings("unchecked")
        public ArrayList<PL> getPreviousList_safe(){ return (ArrayList<PL>) previousList.clone(); }
        @SuppressWarnings("unchecked")
        public ArrayList<PL> getNextList_safe(){ return (ArrayList<PL>) nextList.clone(); }
        public void addInfo(String name, String value){ additionalInfoList.put(name, value); }
    }
    public class TP implements java.io.Serializable{
        private static final long serialVersionUID = 5691467102614286878L;
        public TR source;
        public PL target;
        public int weight=1;
        public HashMap<String, String> additionalInfoList = new HashMap<String, String>();
        public TP(TR transition, PL place){
            this.source = transition;
            this.target = place;
        }
        public void addInfo(String name, String value){ additionalInfoList.put(name, value); }
    }
    public class PT implements java.io.Serializable{
        private static final long serialVersionUID = -33230958480957514L;
        public PL source;
        public TR target;
        public int weight=1;
        public HashMap<String, String> additionalInfoList = new HashMap<String, String>();
        public PT(PL place, TR transition){
            this.source = place;
            this.target = transition;
        }
        public void addInfo(String name, String value){ additionalInfoList.put(name, value); }
    }
    
    public String name;
    private ArrayList<PL> placeList = new ArrayList<PL>();
    private ArrayList<TR> transitionList = new ArrayList<TR>();
    private ArrayList<PL> startList = new ArrayList<PL>();
    private ArrayList<PL> endList = new ArrayList<PL>();
    private ArrayList<PT> connectionPTList = new ArrayList<PT>();
    private ArrayList<TP> connectionTPList = new ArrayList<TP>();
    
    public PetriNet(String name){
        this.name = name;
    }
    
    public void resetNet(){
        placeList.clear();
        transitionList.clear();
        startList.clear();
        endList.clear();
        connectionPTList.clear();
        connectionTPList.clear();
    }
    
    public PetriNet clonePN() throws Exception{
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream(512);
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(bos);
        out.writeObject(this);
        byte[] bytes = bos.toByteArray();
        out.close();
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(bytes));
        PetriNet readObject = (PetriNet) ois.readObject();
        ois.close();
        return readObject;
    }
    
    public void importPN(PetriNet petriNet) throws Exception{
        petriNet = petriNet.clonePN();
        placeList.addAll(petriNet.placeList);
        transitionList.addAll(petriNet.transitionList);
        startList.addAll(petriNet.startList);
        endList.addAll(petriNet.endList);
        connectionPTList.addAll(petriNet.connectionPTList);
        connectionTPList.addAll(petriNet.connectionTPList);
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList<PL> getPlaceList_safe(){ return (ArrayList<PL>) placeList.clone(); }
    @SuppressWarnings("unchecked")
    public ArrayList<TR> getTransitionList_safe(){ return (ArrayList<TR>) transitionList.clone(); }
    @SuppressWarnings("unchecked")
    public ArrayList<PL> getStartList_safe(){ return (ArrayList<PL>) startList.clone(); }
    @SuppressWarnings("unchecked")
    public ArrayList<PL> getEndList_safe(){ return (ArrayList<PL>) endList.clone(); }
    @SuppressWarnings("unchecked")
    public ArrayList<PT> getConnectionPTList_safe(){ return (ArrayList<PT>) connectionPTList.clone(); }
    @SuppressWarnings("unchecked")
    public ArrayList<TP> getConnectionTPList_safe(){ return (ArrayList<TP>) connectionTPList.clone(); }
    
    public boolean isEmpty(){
        return placeList.isEmpty() && transitionList.isEmpty();
    }
    
    public PL addPlace(String name) throws Exception{
        return addPlace(name, 0);
    }

    public PL addPlace(String name, int numToken) throws Exception{
        if(name == null || name.isEmpty())
            throw new Exception("ERROR: Name is empty or null");
        for(PL place:placeList)
            if(place.name.equals(name))
                throw new Exception("ERROR: A place with name " + name + " already exist");
        PL ret = new PL(name);
        ret.numToken = numToken;
        placeList.add(ret);
        //if(numToken>0)
        //    startList.add(ret);
        return ret;
    }
    
    public void delPlace(String name) throws Exception{
        delPlace(getPlace(name));
    }
    public void delPlace(PL place) throws Exception{
        placeList.remove(place);
        endList.remove(place);
        startList.remove(place);
        
        for(TR tr0:place.getPreviousList_safe())
            delConnection(tr0, place);
        for(TR tr0:place.getNextList_safe())
            delConnection(place, tr0);
    }
    
    public  ArrayList<TR> getEnabledTransitions() throws Exception{
        int[] initialMark = new int[placeList.size()];
        for(int i=0; i<placeList.size();i++)
            initialMark[i] = placeList.get(i).numToken;
        return getEnabledTransitions(initialMark);
    }
    public ArrayList<TR> getEnabledTransitions(int[] startingMarkList) throws Exception{

        if(startingMarkList == null)
            throw new Exception("ERROR: starting mark can not be null");
        if(startingMarkList.length != placeList.size())
            throw new Exception("ERROR: you have to provide a mark for each place");
        ArrayList<TR> enabledList = new ArrayList<TR>();
        for(TR transition: transitionList){
            boolean isEnabled=true;
            for(PL place:transition.previousList)
                if(startingMarkList[placeList.indexOf(place)]==0)
                    isEnabled = false;
            if(isEnabled)
                enabledList.add(transition);
        }
            
        return enabledList;
    }
    
    public void fireTransition(TR transition) throws Exception{
        if(!getEnabledTransitions().contains(transition))
            throw new Exception("ERROR: transition " + transition.name + " can not be fired");
        
        for(PL place: transition.previousList)
            if(place.numToken>0)
                place.numToken--;
        for(PL place: transition.nextList)
            if(place.numToken>=0)
                place.numToken++;
    }
    
    public int[] getCurrentMark(){
        int[] mark = new int[placeList.size()];
        for(int i=0; i<placeList.size();i++)
            mark[i] = placeList.get(i).numToken;
        return mark;
    }
    
    public void setMark(int[] newMark) throws Exception{
        if(newMark == null)
            throw new Exception("ERROR: starting mark can not be null");
        if(newMark.length != placeList.size())
            throw new Exception("ERROR: you have to provide a mark for each place");
        for(int i=0; i<placeList.size();i++)
            placeList.get(i).numToken = newMark[i];
    }
    
    public TR addTransition(String name) throws Exception{
        if(name == null || name.isEmpty())
            throw new Exception("ERROR: Name is empty or null");
        for(TR transition:transitionList)
            if(transition.name.equals(name))
                throw new Exception("ERROR: A transition with name " + name + " already exist");
        TR ret = new TR(name);
        transitionList.add(ret);
        return ret;
    }
    
    public void delTransition(String name) throws Exception{
        delTransition(getTransition(name));
    }
    public void delTransition(TR transition) throws Exception{
        transitionList.remove(transition);
        for(PL pl0:transition.getPreviousList_safe())
            delConnection(pl0, transition);
        for(PL pl0:transition.getNextList_safe())
            delConnection(transition, pl0);
    }
    
    public PT connect(PL place, TR transition) throws Exception{
        PT conn = null;
        try {
            conn = getConnection(place, transition);
        } catch (Exception e) {}
        if(conn != null) {
            //conn.weight += 1;
            //return conn;
            throw new Exception("ERROR: A connection already exist between " + transition.name + " and " + place.name);
        }
        place.nextList.add(transition);
        transition.previousList.add(place);
        conn = new PT(place, transition);
        connectionPTList.add(conn);
        return conn;
    }
    public TP connect(TR transition, PL place) throws Exception{
        TP conn = null;
        try {
            conn = getConnection(transition, place);
        } catch (Exception e) {}
        if(conn != null) {
            //conn.weight += 1;
            //return conn;
            throw new Exception("ERROR: A connection already exist between " + transition.name + " and " + place.name);
        }
        transition.nextList.add(place);
        place.previousList.add(transition);
        conn = new TP(transition, place);
        connectionTPList.add(conn);
        return conn;
    }
    
    public boolean existConnection(PL place, TR transition){
        for(TR trNext: place.nextList)
            if(transition.equals(trNext))
                return true;
        return false;
    }
    
    public boolean existConnection(TR transition, PL place){
        for(PL plNext: transition.nextList)
            if(place.equals(plNext))
                return true;
        return false;
    }
    
    public void delConnection(PL place, TR transition) throws Exception{
        PT conn = getConnection(place, transition);
        connectionPTList.remove(conn);
        place.nextList.remove(transition);
        transition.previousList.remove(place);
        
    }
    public void delConnection(TR transition, PL place) throws Exception{
        TP conn = getConnection(transition, place);
        connectionTPList.remove(conn);
        transition.nextList.remove(place);
        place.previousList.remove(transition);
    }
    
    public PL getPlace(String name) throws Exception{
        if(name == null || name.isEmpty())
            throw new Exception("ERROR: Name is empty or null");
        name = name.replaceAll("(\\W|_)+", "");
        for(PL place:placeList)
            if(place.name.equals(name))
                return place;
        throw new Exception("ERROR: Can not find a Place with name " + name);
    }
    
    public TR getTransition(String name) throws Exception{
        if(name == null || name.isEmpty())
            throw new Exception("ERROR: Name is empty or null");
        name = name.replaceAll("(\\W|_)+", "");
        for(TR transition:transitionList)
            if(transition.name.equals(name))
                return transition;
        throw new Exception("ERROR: Can not find a Transition with name " + name);
    }
    
    public boolean existPlace(String name) throws Exception{
        if(name == null || name.isEmpty())
            throw new Exception("ERROR: Name is empty or null");
        name = name.replaceAll("(\\W|_)+", "");
        for(PL place:placeList)
            if(place.name.equals(name))
                return true;
        return false;
    }
    
    public boolean existTransition(String name) throws Exception{
        if(name == null || name.isEmpty())
            throw new Exception("ERROR: Name is empty or null");
        name = name.replaceAll("(\\W|_)+", "");
        for(TR transition:transitionList)
            if(transition.name.equals(name))
                return true;
        return false;
    }
    
    public TP getConnection(TR transition, PL place) throws Exception{
        for(TP tp:connectionTPList)
            if(tp.source == transition && tp.target == place)
                return tp;
        throw new Exception("ERROR: Can not find a connection between " + transition.name + " and " + place.name);
    }
    public PT getConnection(PL place, TR transition) throws Exception{
        for(PT pt:connectionPTList)
            if(pt.source == place && pt.target == transition)
                return pt;
        throw new Exception("ERROR: Can not find a connection between " + place.name + " and " + transition.name);
    }
    
    public void finalizeModel(){
        updateEndList();
        updateStartList();
    }

    private void updateEndList(){
        ArrayList<PL> newEndList = new ArrayList<PetriNet.PL>();
        for(PL place:placeList)
            if(place.nextList.isEmpty())
                newEndList.add(place);
        this.endList = newEndList;
    }
    
    private void updateStartList(){
        ArrayList<PL> newStartList = new ArrayList<PetriNet.PL>();
        for(PL place:placeList)
            if(place.numToken>0)
                newStartList.add(place);
        this.startList = newStartList;
    }
    
    public void updateStartListCheckingFlow(){
        ArrayList<PL> newStartList = new ArrayList<PetriNet.PL>();
        for(PL place:placeList)
            if(place.numToken>0)
                newStartList.add(place);
            else
                if(place.previousList.isEmpty()){
                    place.numToken = 1;
                    newStartList.add(place);
                }
        this.startList = newStartList;
    }
    /*
    public static void main(String[] args) {
        try {
            PetriNet pn = new PetriNet("test");
            
            PL p0 = pn.addPlace("p0", 1);
            TR t0 = pn.addTransition("t0");
            PL p1 = pn.addPlace("p1");
            TR t1 = pn.addTransition("t1");
            PL p2 = pn.addPlace("p2");
            pn.connect(p0, t0);
            pn.connect(p0, t1);
            pn.connect(t0, p1);
            pn.connect(p1, t1);
            pn.connect(t1, p2);
            //pn.delTransition(t0);
            //pn.delPlace(p1);
            pn.finalizeModel();
            
            System.out.println(eu.learnpad.verification.pn.impexp.PNExport.exportTo_EldaricaP(pn));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
