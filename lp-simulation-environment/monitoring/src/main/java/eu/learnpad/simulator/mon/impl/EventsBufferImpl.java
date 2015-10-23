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
package eu.learnpad.simulator.mon.impl;

import java.util.ArrayList;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.simulator.mon.buffer.EventsBuffer;
import eu.learnpad.simulator.mon.event.GlimpseBaseEvent;
import eu.learnpad.simulator.mon.utils.DebugMessages;


public class EventsBufferImpl <T> implements EventsBuffer<T> {

	public ArrayList<GlimpseBaseEvent<T>> myBuffer = new ArrayList<GlimpseBaseEvent<T>>(); 
	
	public void add(GlimpseBaseEvent<T> evt)
	{
		myBuffer.add(evt);
		DebugMessages.println(TimeStamp.getCurrentTime(),this.getClass().getSimpleName(),"New element add to the buffer. New buffer size = " + myBuffer.size());
	}
	
	public void remove(GlimpseBaseEvent<T> evt)
	{
		myBuffer.remove(evt);
	}
	
	public GlimpseBaseEvent<T> getElementAt(int index)
	{
		return myBuffer.get(index);
	}
	
	public void removeElementAt(int index)
	{
		myBuffer.remove(index);
	}
	
	public void clear()
	{
		myBuffer.clear();
	}
	
	public void setDimension(int newSize)
	{
		//
	}
	
	public int getSize()
	{
		return myBuffer.size();
	}
}
