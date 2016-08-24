/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.cw.internal.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.model.reference.EntityReference;
import org.xwiki.observation.EventListener;
import org.xwiki.observation.event.Event;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.cw.internal.utils.LPCodeLabels;

/**
 * @author gulyx
 * 
 * This code is based on this tutorial : http://extensions.xwiki.org/xwiki/bin/view/Extension/Observation+Module+Local
 **/

@Component
@Named("cw-simple-event-listener")
public abstract class SimpleEventListener implements EventListener, Initializable{

	/**
	 * Hint of the component.
	 */
	public static final String ROLEHINT = "cw-simple-event-listener";

	/**
	 * The key under which the last encountered error is stored in the current
	 * execution context.
	 */
	protected static final String LEARNPADERROR_KEY = "simplelistener.learnpad.error";
			
	protected String name;	
	protected List<Event> event;
	
	@Inject
	@Named("eu.learnpad.cw.internal.CWXwikiBridge")
	protected UICWBridge cwBridge;

	@Inject
	protected  Logger logger;
	
	public SimpleEventListener() {
		this.name = SimpleEventListener.ROLEHINT;
		this.event = new ArrayList<Event>();
	}
		
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Event> getEvents() {
		return this.event;
	}

}
