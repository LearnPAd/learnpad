package eu.learnpad.core.impl.qm;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.qm.CoreFacade;

@Component
public class XwikiCoreFacade implements XWikiRestComponent, CoreFacade{

	@Override
	public void putQuestionnaire(String modelId, String type, byte[] bpmnFile)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		
	}

}
