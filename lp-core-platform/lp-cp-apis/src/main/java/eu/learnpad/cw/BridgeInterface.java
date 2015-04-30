package eu.learnpad.cw;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.cw.rest.ModelImported;

@Component
public abstract class BridgeInterface implements XWikiRestComponent, ModelImported{

}
