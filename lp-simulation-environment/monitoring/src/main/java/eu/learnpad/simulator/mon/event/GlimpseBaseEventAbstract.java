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

import java.io.Serializable;

import eu.learnpad.simulator.mon.event.GlimpseBaseEvent;

/**
 * 
 * This class is a possible implementation (extension) of the {@link GlimpseBaseEvent} class.<br /><br /> 
 * This implementation refer to a CONNECT project scenario.
 * 
 * @author Antonello Calabr&ograve;
 * @version 3.4
 */
public abstract class GlimpseBaseEventAbstract<T> implements GlimpseBaseEvent<T>, Serializable
{
	private static final long serialVersionUID = 431412878795449197L;
	protected T data;
	protected Long timeStamp;
	protected String eventName;
	protected boolean isException;
	private boolean consumed;

	public GlimpseBaseEventAbstract(T data, Long timeStamp,	String eventName, boolean isException) {
		
		this.data = data;
		this.timeStamp = timeStamp;
		this.eventName = eventName;
		this.isException = isException;
		this.consumed = false;
	}

	@Override
	public T getEventData() {
		return data;
	}

	@Override
	public void setEventData(T data) {
		this.data = data;
	}

	@Override
	public Long getTimeStamp() {
		return timeStamp;
	}

	@Override
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String getEventName() {
		return eventName;
	}

	@Override
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public boolean isException() {
		return isException;
	}

	@Override
	public void setException(boolean isException) {
		this.isException = isException;
	}

	@Override
	public boolean isConsumed() {
		return consumed;
	}

	@Override
	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}
}
