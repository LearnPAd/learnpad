package eu.learnpad.me;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.me.rest.ContentVerified;

@Component
public abstract class BridgeInterface implements XWikiRestComponent, ContentVerified {

}
