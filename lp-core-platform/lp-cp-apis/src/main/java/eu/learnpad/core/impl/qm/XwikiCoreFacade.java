package eu.learnpad.core.impl.qm;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.qm.CoreFacade;

@Component
public class XwikiCoreFacade implements XWikiRestComponent, CoreFacade{

	@Override
	public void publish(String questionnairesId, String type,
			byte[] questionnairesFile) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void genrationCompleted(String questionnairesId)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		
	}


}
