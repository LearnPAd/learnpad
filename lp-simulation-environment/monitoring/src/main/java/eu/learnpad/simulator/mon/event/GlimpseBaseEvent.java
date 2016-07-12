
/*
  * GLIMPSE: A generic and flexible monitoring infrastructure.
  * For further information: http://labsewiki.isti.cnr.it/labse/tools/glimpse/public/main
  * 
  * Copyright (C) 2011  Software Engineering Laboratory - ISTI CNR - Pisa - Italy
  * 
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * 
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * 
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * 
*/

package eu.learnpad.simulator.mon.event;

/**
 * The GlimpseBaseEvent <T> is the generic basic event that the monitoring infrastructure<br />
 * uses to infer more complex events. These object must be sent from a probe<br />
 * to GLIMPSE through the ESB.<br /><br />
 * 
 * The parameter of GlimpseBaseEvent(String, String, String, String, int, int, Long, String, boolean)<br />
 * are useful to provide information about the connector where the probe is instanciated.<br /><br />
 * Extending the GlimpseBaseEvent(String, String, String, String, int, int, Long, String, boolean) class<br />
 * it's possible to add or change variables and to manages it into the CEP using a well-formed Drools rule.<br />
 * 
 * @author Antonello Calabr&ograve;
 * @version 3.4
 * 
 * @param <T> The type of the data on the payload of the GlimpseBaseEvent, see method {@link #getData()} and {@link #setData(Object)}
 */

public interface GlimpseBaseEvent <T> {
	
	public T getEventData();
	public void setEventData(T t);
		
	public Long getTimeStamp();
	public void setTimeStamp(Long timeStamp);
	
	public String getEventName();
	public void setEventName(String eventName);
	
	public boolean isException();
	public void setException(boolean isException);
	
	public boolean isConsumed();
	public void setConsumed(boolean consumed);
}
