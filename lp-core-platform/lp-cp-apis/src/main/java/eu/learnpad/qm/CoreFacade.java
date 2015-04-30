package eu.learnpad.qm;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.qm.rest.PublishQuestionnaire;

@Component
public abstract class CoreFacade implements XWikiRestComponent, PublishQuestionnaire{

}
