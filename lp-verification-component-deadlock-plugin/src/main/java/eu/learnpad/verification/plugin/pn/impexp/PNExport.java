package eu.learnpad.verification.plugin.pn.impexp;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import eu.learnpad.verification.plugin.pn.PetriNet;
import eu.learnpad.verification.plugin.pn.PetriNet.PL;
import eu.learnpad.verification.plugin.pn.PetriNet.PT;
import eu.learnpad.verification.plugin.pn.PetriNet.TP;
import eu.learnpad.verification.plugin.pn.PetriNet.TR;
import eu.learnpad.verification.plugin.utils.XMLUtils;

public class PNExport {
	
	public static String exportTo_PNML(PetriNet pn) throws Exception{
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		
		Document xmlDoc = XMLUtils.createNewDocument();
		
		Element pnmlEl = xmlDoc.createElement("pnml");
		xmlDoc.appendChild(pnmlEl);
		Element netEl = xmlDoc.createElement("net");
		pnmlEl.appendChild(netEl);
		netEl.setAttribute("type", "http://www.informatik.hu-berlin.de/top/pntd/ptNetb");
		netEl.setAttribute("id", "noID");
		
		for(PL place:pn.getPlaceList_safe()){
			Element placeEl = xmlDoc.createElement("place");
			netEl.appendChild(placeEl);
			placeEl.setAttribute("id", place.name);
			Element nameEl = xmlDoc.createElement("name");
			placeEl.appendChild(nameEl);
			Element textEl = xmlDoc.createElement("text");
			nameEl.appendChild(textEl);
			textEl.setTextContent(place.name);
			Element graph1El = xmlDoc.createElement("graphics");
			nameEl.appendChild(graph1El);
			Element offsetEl = xmlDoc.createElement("offset");
			graph1El.appendChild(offsetEl);
			offsetEl.setAttribute("x", (Float.valueOf(place.x))+"");
			offsetEl.setAttribute("y", (Float.valueOf(place.y)+Float.valueOf(place.h)+5)+"");
			Element graph2El = xmlDoc.createElement("graphics");
			placeEl.appendChild(graph2El);
			Element positionEl = xmlDoc.createElement("position");
			graph2El.appendChild(positionEl);
			positionEl.setAttribute("x", place.x);
			positionEl.setAttribute("y", place.y);
			Element dimensionEl = xmlDoc.createElement("dimension");
			graph2El.appendChild(dimensionEl);
			dimensionEl.setAttribute("x", place.w);
			dimensionEl.setAttribute("y", place.h);
			if(place.numToken>0){
				Element initialMarkingEl = xmlDoc.createElement("initialMarking");
				placeEl.appendChild(initialMarkingEl);
				Element text1El = xmlDoc.createElement("text");
				initialMarkingEl.appendChild(text1El);
				text1El.setTextContent(place.numToken+"");
			}
		}
		
		for(TR transition:pn.getTransitionList_safe()){
			Element transitionEl = xmlDoc.createElement("transition");
			netEl.appendChild(transitionEl);
			transitionEl.setAttribute("id", transition.name);
			Element nameEl = xmlDoc.createElement("name");
			transitionEl.appendChild(nameEl);
			Element textEl = xmlDoc.createElement("text");
			nameEl.appendChild(textEl);
			textEl.setTextContent(transition.name);
			Element graph1El = xmlDoc.createElement("graphics");
			nameEl.appendChild(graph1El);
			Element offsetEl = xmlDoc.createElement("offset");
			graph1El.appendChild(offsetEl);
			offsetEl.setAttribute("x", (Float.valueOf(transition.x))+"");
			offsetEl.setAttribute("y", (Float.valueOf(transition.y)+Float.valueOf(transition.h)+5)+"");
			Element graph2El = xmlDoc.createElement("graphics");
			transitionEl.appendChild(graph2El);
			Element positionEl = xmlDoc.createElement("position");
			graph2El.appendChild(positionEl);
			positionEl.setAttribute("x", transition.x);
			positionEl.setAttribute("y", transition.y);
			Element dimensionEl = xmlDoc.createElement("dimension");
			graph2El.appendChild(dimensionEl);
			dimensionEl.setAttribute("x", transition.w);
			dimensionEl.setAttribute("y", transition.h);
		}
		ArrayList<TP> connTP = pn.getConnectionTPList_safe();
		for(int i=0;i<connTP.size();i++){
			TP arc = connTP.get(i);
			Element arcEl = xmlDoc.createElement("arc");
			netEl.appendChild(arcEl);
			arcEl.setAttribute("id", "arc"+i);
			arcEl.setAttribute("source", arc.source.name);
			arcEl.setAttribute("target", arc.target.name);
			Element inscriptionEl = xmlDoc.createElement("inscription");
			arcEl.appendChild(inscriptionEl);
			Element textEl = xmlDoc.createElement("text");
			inscriptionEl.appendChild(textEl);
			textEl.setTextContent("1");
			Element graphEl = xmlDoc.createElement("graphics");
			arcEl.appendChild(graphEl);
		}
		
		ArrayList<PT> connPT = pn.getConnectionPTList_safe();
		for(int i=0;i<connPT.size();i++){
			PT arc = connPT.get(i);
			Element arcEl = xmlDoc.createElement("arc");
			netEl.appendChild(arcEl);
			arcEl.setAttribute("id", "arc"+i);
			arcEl.setAttribute("source", arc.source.name);
			arcEl.setAttribute("target", arc.target.name);
			Element inscriptionEl = xmlDoc.createElement("inscription");
			arcEl.appendChild(inscriptionEl);
			Element textEl = xmlDoc.createElement("text");
			inscriptionEl.appendChild(textEl);
			textEl.setTextContent("1");
			Element graphEl = xmlDoc.createElement("graphics");
			arcEl.appendChild(graphEl);
		}
		
		return XMLUtils.getStringFromXmlDoc(xmlDoc);
	}

	
	public static String exportTo_EldaricaP(PetriNet pn) throws Exception{
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		
		String ret = "net {\""+pn.name+"\"}\n";
		
		for(TR tr:pn.getTransitionList_safe()){
			ret += "\ntr " + tr.name + " [] ";
			for(PL pl:tr.previousList)
				ret += pl.name + " ";
			ret += "->";
			for(PL pl:tr.nextList)
				ret += " " + pl.name;
		}
		
		ret += "\n";
		for(PL pl:pn.getStartList_safe())
			ret += "\npl " + pl.name + " (" + pl.numToken + ")";
		
		return ret;
	}
	
	public static String exportTo_EldaricaP_property_EndReachability(PetriNet pn) throws Exception{
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		
		String finalConfig = "";
		for(PL pl:pn.getEndList_safe())
			finalConfig += "\nfinal " + pl.name + " (1)";
		return finalConfig;
	}
	
	public static String exportTo_EldaricaP_property_DeadlockPresence(PetriNet pn) throws Exception{
		//C'è deadlock se tutte le transizioni non sono attivabili e c'è almeno un place non finale che contiene uno o piu token
		
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		
		String finalConfig = "finalConfiguration (";
		
		for(TR tr:pn.getTransitionList_safe()){
			finalConfig += "\n (";
			for(PL pl:tr.previousList)
				finalConfig += pl.name + " = 0 | ";
			finalConfig = finalConfig.substring(0, finalConfig.length()-2) + ")&";
		}
		
		finalConfig += "\n\n (";
		ArrayList<PL> endList = pn.getEndList_safe();
		for(PL pl:pn.getPlaceList_safe())
			if(!endList.contains(pl))
				if(!pl.excludeFromDeadlockCheck)
					finalConfig += pl.name + " > 0 | ";
		if(finalConfig.endsWith("| "))
			finalConfig = finalConfig.substring(0, finalConfig.length()-2) + ")\n)";
		else
			finalConfig = finalConfig.substring(0, finalConfig.length()-5) + ")";
		
		return finalConfig;
	}
	
	public static String exportTo_PROMELA(PetriNet pn) throws Exception{
		
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		/* Mapping Suggested by SPIN
 #define Place	byte    // assume < 256 tokens per place                             
 Place p1, p2, p3;
 Place p4, p5, p6;
 #define inp1(x)		(x>0) -> x=x-1
 #define inp2(x,y)	(x>0&&y>0) -> x = x-1; y=y-1
 #define out1(x)		x=x+1
 #define out2(x,y)	x=x+1; y=y+1
 init
 {	p1 = 1; p4 = 1;	//initial mark
 	do
	:: atomic { inp1(p1)	-> out1(p2) } //t1
	:: atomic { inp2(p2,p4)	-> out1(p3) } //t2
	:: atomic { inp1(p3)	-> out2(p1,p4) }
	:: atomic { inp1(p4)	-> out1(p5) }
	:: atomic { inp2(p1,p5) -> out1(p6) }
	:: atomic { inp1(p6)	-> out2(p4,p1) }
 	od
 }
		 * 
		 * 
		 * */
		throw new Exception("TO BE IMPLEMENTED");
	}
	
	//Cunf & PEP
	public static String exportTo_ll_net(PetriNet pn) throws Exception{
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		
		throw new Exception("TO BE IMPLEMENTED");
	}
	
	public static String exportTo_LOLA(PetriNet pn) throws Exception{
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		
		String ret = "PLACE ";
		for(PL place:pn.getPlaceList_safe())
			ret += place.name + ", ";
		ret = ret.substring(0, ret.length()-2) + ";";
		ret += "\nMARKING ";
		for(PL place:pn.getStartList_safe())
			ret += place.name + ": " + place.numToken + ", ";
		ret = ret.substring(0, ret.length()-2) + ";";
		for(TR transition:pn.getTransitionList_safe()){
			ret += "\nTRANSITION " + transition.name;
			ret += " CONSUME ";
			for(PL pl:transition.getPreviousList_safe())
				ret += pl.name + ": 1, ";
			ret = ret.substring(0, ret.length()-2) + ";";
			ret += " PRODUCE ";
			for(PL pl:transition.getNextList_safe())
				ret += pl.name + ": 1, ";
			ret = ret.substring(0, ret.length()-2) + ";";
		}

		return ret;
	}
	
	
	public static String exportTo_LOLA_property_DeadlockPresence(PetriNet pn) throws Exception{
		// C'è deadlock se tutte le transizioni non sono attivabili (DEADLOCK) e c'è almeno un place non finale che contiene uno o piu token
		
		if(pn.isEmpty())
			throw new Exception("ERROR: The provided petri net is empty");
		
		String finalConfig = "EF (DEADLOCK AND ( ";
		ArrayList<PL> endList = pn.getEndList_safe();
		for(PL pl:pn.getPlaceList_safe())
			if(!endList.contains(pl))
				if(!pl.excludeFromDeadlockCheck)
					finalConfig += pl.name + " > 0 OR ";
		if(finalConfig.endsWith("OR "))
			finalConfig = finalConfig.substring(0, finalConfig.length()-3) + "))";
		else
			finalConfig = "EF DEADLOCK";
		return finalConfig;
	}
	
	/*
	public static void main(String[] args) {
		try {
			String bpmnUrl = "D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test_90.bpmn";
			PetriNet pn = PNImport.generateFromBPMN(XMLUtils.getXmlDocFromURI(bpmnUrl));
			//System.out.println(exportTo_EldaricaP(pn));
			//System.out.println(exportTo_EldaricaP_property_DeadlockPresence(pn));
			//System.out.println(exportTo_EldaricaP_propertyEndReachability(pn));
			System.out.println(exportTo_PNML(pn));
			//System.out.println(exportTo_LOLA(pn));
			//System.out.println(exportTo_LOLA_property_DeadlockPresence(pn));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
