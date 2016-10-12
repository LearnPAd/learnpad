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
		
		Activity obj27830 = new Activity("Assess Application", "Assess Application", "obj.27830", kpiExample, 1.0f);
		Activity obj27812 = new Activity("Send Communication of non-admissible instance", "Send Communication of non-admissible instance", "obj.27812", kpiExample, 1.0f);
		Activity obj27782 = new Activity("Check Application", "Check Application", "obj.27782", kpiExample, 1.0f);
		Activity obj29013 = new Activity("Request Amendment", "Request Amendment", "obj.29013", kpiExample, 1.0f);
		Activity obj27833 = new Activity("Check Amendment", "Check Amendment", "obj.27833", kpiExample, 1.0f);	
		Activity obj27788 = new Activity("Send Authorization Document", "Send Authorization Document", "obj.27788", kpiExample, 1.0f);
		Activity obj27839 = new Activity("Manage Inhibition", "Manage Inhibition", "obj.27839", kpiExample, 1.0f);
		Activity obj27987 = new Activity("Activate Service Conference", "Activate Service Conference", "obj.27987", kpiExample, 1.0f);
		Activity obj27921 = new Activity("Send Instance to Third Party", "Send Instance to Third Party", "obj.27921", kpiExample, 1.0f);
		Activity obj27990 = new Activity("Receive Confirmation", "Receive Confirmation", "obj.27990", kpiExample, 1.0f);
		
		//titolo unico
		if (idBPMN.compareTo("mod.21093") == 0) {
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27812});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj29013, obj27833, obj27788});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj29013, obj27833, obj27839});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27987, obj29013, obj27833, obj27839});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27987, obj29013, obj27833, obj27788});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27987, obj27990, obj29013, obj27833, obj27788});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27987, obj27990, obj29013, obj27833, obj27839});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27990, obj27788});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27990, obj27839});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27987, obj27990, obj27788});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27987, obj27990, obj27839});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27990, obj27987, obj27788});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27990, obj27987, obj27839});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27990, obj27987, obj29013, obj27833, obj27788});
		lastExploredBPMN.add(new Activity[]	{obj27830, obj27782, obj27921, obj27990, obj27987, obj29013, obj27833, obj27839});
		}
		
		//conferenza servizi
		if (idBPMN.compareTo("mod.21262") == 0) {
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