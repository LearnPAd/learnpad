package eu.learnpad.core.impl.sim;

import java.util.List;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.sim.CoreFacade;
import eu.learnpad.sim.rest.data.UserData;

@Component
public class XwikiCoreFacade implements XWikiRestComponent, CoreFacade{

	@Override
	public List<String> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData getUserData(String userartifactid) {
		// TODO Auto-generated method stub
		return null;
	}


}
