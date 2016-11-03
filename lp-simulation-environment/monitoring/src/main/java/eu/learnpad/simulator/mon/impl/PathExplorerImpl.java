package eu.learnpad.simulator.mon.impl;

import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;

import eu.learnpad.simulator.mon.BPMN.PathExplorer;
import eu.learnpad.simulator.mon.coverage.Activity;

public class PathExplorerImpl implements PathExplorer {

	public Vector<Activity[]> lastExploredBPMN;
	
	@Override
	public Vector<Activity[]> getUnfoldedBPMN(Document theBusinessProcessToUnfold, String idBPMN) {
		
		//call the software provided by third parties
		
		lastExploredBPMN = new Vector<Activity[]>();
		
		HashMap<String, Float> kpiExample = new HashMap<String, Float>();
		kpiExample.put("kpiOne", 0.1f);
		kpiExample.put("kpiTwo", 0.2f);
		kpiExample.put("kpiThree", 0.3f);
		kpiExample.put("kpiFour", 0.4f);
		
		Activity obj27830 = new Activity("Assess Application", "Assess Application", "obj.27830", kpiExample, 3000.0f);
		Activity obj27812 = new Activity("Send Communication of non-admissible instance", "Send Communication of non-admissible instance", "obj.27812", kpiExample, 3000.0f);
		Activity obj27782 = new Activity("Check Application", "Check Application", "obj.27782", kpiExample, 3000.0f);
		Activity obj29013 = new Activity("Request Amendment", "Request Amendment", "obj.29013", kpiExample, 3000.0f);
		Activity obj27833 = new Activity("Check Amendment", "Check Amendment", "obj.27833", kpiExample, 3000.0f);	
		Activity obj27788 = new Activity("Send Authorization Document", "Send Authorization Document", "obj.27788", kpiExample, 3000.0f);
		Activity obj27839 = new Activity("Manage Inhibition", "Manage Inhibition", "obj.27839", kpiExample, 3000.0f);
		Activity obj27987 = new Activity("Activate Service Conference", "Activate Service Conference", "obj.27987", kpiExample, 3000.0f);
		Activity obj27921 = new Activity("Send Instance to Third Party", "Send Instance to Third Party", "obj.27921", kpiExample, 3000.0f);
		Activity obj27990 = new Activity("Receive Confirmation", "Receive Confirmation", "obj.27990", kpiExample, 3000.0f);

		Activity usertask1 = new Activity("Activate Service Conference", "Activate Service Conference", "usertask1", kpiExample, 3000.0f);
		Activity usertask16 = new Activity("Check Amendment", "Check Amendment", "usertask16", kpiExample, 3000.0f);	
		Activity usertask17 = new Activity("Richiedi Integrazione", "Richiedi Integrazione", "usertask17", kpiExample, 3000.0f);
		Activity usertask18 = new Activity("Fornisce Autorizzazioni", "Fornisce Autorizzazioni", "usertask18", kpiExample, 3000.0f);
		Activity usertask19 = new Activity("Fornisce Autorizzazioni Sotto Condizione", "Fornisce Autorizzazioni Sotto Condizione", "usertask19", kpiExample, 3000.0f);
		Activity usertask20 = new Activity("Fornisce Diniego (inibizione  a procedere)", "Fornisce Diniego (inibizione  a procedere)", "usertask20", kpiExample, 3000.0f);
		Activity usertask21 = new Activity("Prende la Decisione Finale", "Prende la Decisione Finale", "usertask21", kpiExample, 3000.0f);
		Activity usertask22 = new Activity("Fornisce Report Finale", "Final decision", "usertask22", kpiExample, 3000.0f);	

		Activity usertask2 = new Activity("Protocolla la documentazione ricevuta", "Protocolla la documentazione ricevuta", "usertask2", kpiExample, 3000.0f);
		Activity usertask3 = new Activity("Protocolla la documentazione ricevuta", "Protocolla la documentazione ricevuta", "usertask3", kpiExample, 3000.0f);	
		Activity usertask4 = new Activity("Controlla la Documentazione Fornita", "Controlla la Documentazione Fornita", "usertask4", kpiExample, 3000.0f);
		Activity usertask5 = new Activity("Controlla la Documentazione Fornita", "Controlla la Documentazione Fornita", "usertask5", kpiExample, 3000.0f);
		Activity usertask6 = new Activity("Elabora Richiesta Integrazioni", "Elabora Richiesta Integrazioni", "usertask6", kpiExample, 3000.0f);
		Activity usertask7 = new Activity("Elabora Richiesta Integrazioni", "Elabora Richiesta Integrazioni", "usertask7", kpiExample, 3000.0f);
		Activity usertask8 = new Activity("Prende una Decisione", "Prende una Decisione", "usertask8", kpiExample, 3000.0f);
		Activity usertask11 = new Activity("Prende una Decisione", "Prende una Decisione", "usertask11", kpiExample, 3000.0f);	
		Activity usertask14 = new Activity("Fornisci Opinione", "Fornisci Opinione", "usertask14", kpiExample, 3000.0f);	
		Activity usertask15 = new Activity("Fornisci Opinione", "Fornisci Opinione", "usertask15", kpiExample, 3000.0f);	
		
		
		//titolo unico
		if (idBPMN.compareTo("mod.21093") == 0) {
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27812});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27987, obj27990, obj27788});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27987, obj27990, obj27839});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27987, obj27990, obj29013, obj27833, obj27788});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27987, obj27990, obj29013, obj27833, obj27839});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27788});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27839});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27987, obj27990, obj27788});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27987, obj27990, obj27839});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27987, obj27990, obj29013, obj27833, obj27788});
			lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27987, obj27990, obj29013, obj27833, obj27839});
		}
		
		//conferenza servizi
		if (idBPMN.compareTo("mod.21262") == 0) {
			lastExploredBPMN.add(new Activity[]	{usertask1, usertask16, usertask17, usertask22});
			lastExploredBPMN.add(new Activity[]	{usertask1, usertask16, usertask18, usertask22});
			lastExploredBPMN.add(new Activity[]	{usertask1, usertask16, usertask19, usertask22});
			lastExploredBPMN.add(new Activity[]	{usertask1, usertask16, usertask20, usertask22});
			lastExploredBPMN.add(new Activity[]	{usertask1, usertask16, usertask21, usertask22});

			lastExploredBPMN.add(new Activity[]	{usertask2, usertask4});
			lastExploredBPMN.add(new Activity[]	{usertask2, usertask4, usertask6, usertask15});
			lastExploredBPMN.add(new Activity[]	{usertask2, usertask4, usertask8, usertask15});

			lastExploredBPMN.add(new Activity[]	{usertask3, usertask5});
			lastExploredBPMN.add(new Activity[]	{usertask3, usertask5, usertask7, usertask14});
			lastExploredBPMN.add(new Activity[]	{usertask3, usertask5, usertask11, usertask14});
		}
		
		//controlla pratica
		if (idBPMN.compareTo("mod.TBD") == 0) {
		}	
		
		return lastExploredBPMN;
	}

	@Override
	public void setUnfoldedBPMN(Vector<Activity[]> theUnfoldedBusinessProcess) {
		lastExploredBPMN = theUnfoldedBusinessProcess;
	}
}