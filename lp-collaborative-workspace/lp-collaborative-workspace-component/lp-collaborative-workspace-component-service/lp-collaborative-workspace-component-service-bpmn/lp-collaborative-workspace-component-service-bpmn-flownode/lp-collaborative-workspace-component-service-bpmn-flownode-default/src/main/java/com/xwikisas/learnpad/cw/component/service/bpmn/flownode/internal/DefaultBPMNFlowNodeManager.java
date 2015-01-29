package com.xwikisas.learnpad.cw.component.service.bpmn.flownode.internal;

import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;

import com.xwikisas.learnpad.cw.component.service.bpmn.flownode.BPMNFlowNodeManager;

@Component
@Singleton
public class DefaultBPMNFlowNodeManager implements BPMNFlowNodeManager {

	@Override
	public String getName(String id) {
		// TODO Auto-generated method stub
		return "NameOfFlowNode";
	}

}
