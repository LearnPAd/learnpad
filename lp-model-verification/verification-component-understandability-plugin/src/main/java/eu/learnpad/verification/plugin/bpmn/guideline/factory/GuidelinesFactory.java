package eu.learnpad.verification.plugin.bpmn.guideline.factory;

import java.util.ArrayList;
import java.util.Collection;









import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.bpmn2.Definitions;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.ActivityDescription;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.ApplyHierarchicalStructure;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.MinimizeGatewayHeterogeneity;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.MinimizeModelSize;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.ModelLoops;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.appearence.LinearMessageFlows;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.appearence.LinearSequenceFlows;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.appearence.ProcessOrientation;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingANDGateways;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingActivities;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingConvergingGateways;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingDataObject;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingEvents;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingLanes;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingMessageEvent;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingPools;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingStartandEndEvents;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LabelingXORGateway;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling.LoopMarkerAnnotation;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.BalanceGateways;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.ConsistentUsageEndEvents;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.ConsistentUsageLanes;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.ConsistentUsagePools;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.ConsistentUsageStartEvents;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.ExplicitStartEndEvents;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.RestrictUsageTerminateEndEvent;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.SplitAndJoinFlows;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.UsageDefaultFlows;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.UsageInclusiveORGateways;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.UsageMeaningfulGateways;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage.explicitGateways;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "verificationType",
		"definitionID",
		"status",
		"description",
		"guidelines"
})
@XmlRootElement(name = "UnderstandabilityResult")
public class GuidelinesFactory {

    @XmlElement(name = "VerificationType", required = true)
    private String verificationType;
	@XmlElement(name = "DefinitionID", required = true)
	private String definitionID;
	@XmlElement(name = "Status", required = true)
	private String status;
	@XmlElement(name = "Description", required = true)
	private String description;
	@XmlTransient
	private Definitions diagram;
	@XmlElementWrapper(name = "Guidelines", required = true)
	@XmlElement(name = "Guideline", required = true)
	private Collection<abstractGuideline> guidelines;

	@XmlTransient
	protected BlockingQueue<Runnable> threadPool;
	@XmlTransient
	private ExecutorService threadPoolExecutor;
	@XmlTransient
	private long lStartTime; 
	
	GuidelinesFactory(){

	}

	public GuidelinesFactory(Definitions graph){
		diagram = graph;
		guidelines = new ArrayList<abstractGuideline>();
		setDefinitionID(diagram.getId());
		//** General
		guidelines.add(new MinimizeModelSize(diagram));
		guidelines.add(new ApplyHierarchicalStructure(diagram));
		guidelines.add(new ModelLoops(diagram));
		guidelines.add(new ActivityDescription(diagram));
		guidelines.add(new MinimizeGatewayHeterogeneity(diagram));
		//** Notation Usage
		guidelines.add(new ConsistentUsagePools(diagram));
		guidelines.add(new ConsistentUsageLanes(diagram));
		guidelines.add(new ExplicitStartEndEvents(diagram));
		guidelines.add(new ConsistentUsageStartEvents(diagram));
		guidelines.add(new ConsistentUsageEndEvents(diagram));
		guidelines.add(new RestrictUsageTerminateEndEvent(diagram));
		guidelines.add(new explicitGateways(diagram));
		guidelines.add(new SplitAndJoinFlows(diagram));	
		guidelines.add(new BalanceGateways(diagram));	
		guidelines.add(new UsageMeaningfulGateways(diagram));
		guidelines.add(new UsageInclusiveORGateways(diagram));
		guidelines.add(new UsageDefaultFlows(diagram));
		//** Labeling 
		guidelines.add(new LabelingPools(diagram));
		guidelines.add(new LabelingLanes(diagram));
		guidelines.add(new LabelingActivities(diagram));
		guidelines.add(new LabelingEvents(diagram));
		guidelines.add(new LabelingStartandEndEvents(diagram));
		guidelines.add(new LabelingMessageEvent(diagram));
		guidelines.add(new LabelingXORGateway(diagram));
		guidelines.add(new LabelingANDGateways(diagram));
		guidelines.add(new LabelingConvergingGateways(diagram));
		guidelines.add(new LabelingDataObject(diagram));
		guidelines.add(new LoopMarkerAnnotation(diagram));
		//** Appearence 
		guidelines.add(new LinearSequenceFlows(diagram));
		guidelines.add(new LinearMessageFlows(diagram));
		guidelines.add(new ProcessOrientation(diagram));
		//guidelines.add(new (diagram));
		//guidelines.add(new (diagram));
		//guidelines.add(new (diagram));
		threadPool = new LinkedBlockingQueue<Runnable>();
		/*
		setProcessID(explicitSEevent.getProcessID());*/	
	}

	public void StartSequential(){
		for (abstractGuideline abstractGuideline : guidelines) {
			abstractGuideline.Start();
		}
		setStatus();
	}
	
	public void StartThreadPool(){
		long keepAliveTime =5000;
		
		 threadPoolExecutor =
		        new ThreadPoolExecutor(
		               8,
		                10,
		                keepAliveTime,
		                TimeUnit.MILLISECONDS,
		                threadPool
		                );
		for (abstractGuideline abstractGuideline : guidelines) {
			threadPoolExecutor.execute(abstractGuideline);
		}
		lStartTime = System.currentTimeMillis();
		threadPoolExecutor.shutdown();
		
	}
	
	public boolean getStatusThreadPool(){
		boolean res = threadPoolExecutor.isTerminated();
		if(res){
			setStatus();
			long lEndTime = System.currentTimeMillis();
			long difference = lEndTime - lStartTime;

			System.out.println("Guidelines Elapsed milliseconds: " + difference);
		}
		return res;
	}

	public Collection<abstractGuideline> getGuidelines(){
		return guidelines;
	}

	public String getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(String verificationType) {
        this.verificationType = verificationType;
    }
	
	public String getDefinitionID() {
		return definitionID;
	}

	public void setDefinitionID(String DefinitionID) {
		this.definitionID = DefinitionID;
	}

	public String getStatus(){
		return status;
	}

	private void setStatus() {
		status = "OK";
		description = "Well done, no errors found!";
		for (abstractGuideline abstractGuideline : guidelines) {
			if(!abstractGuideline.getStatus()){
				status = "KO";
				description = "Please follow these guidelines:";
				break;
			}
		}
	}

	@Override
	public String toString() {
		String ret = "GuideLineFactory: \n\r";
		int index=0;
		for(abstractGuideline bp: guidelines){
			ret+=++index+") ";
			ret+=bp.toString();
			ret+="\n\r-------------------------------------\n\r"; 
		}
		return ret;
	}

}
