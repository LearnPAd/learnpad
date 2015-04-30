package eu.learnpad.me;

import eu.learnpad.me.rest.ImportModel;
import eu.learnpad.me.rest.RetrieveFeedbacks;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

@Component
public abstract class CoreFacade implements XWikiRestComponent, RetrieveFeedbacks, ImportModel{
	
}
