package eu.learnpad.core.impl.or;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.or.CoreFacade;

@Component
public class XwikiCoreFacade implements XWikiRestComponent, CoreFacade{

	@Override
	public byte[] getComments(String modelSetId, String artifactId)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}


}
