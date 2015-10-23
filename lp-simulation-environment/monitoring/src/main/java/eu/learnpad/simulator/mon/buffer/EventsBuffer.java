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
package eu.learnpad.simulator.mon.buffer;

import eu.learnpad.simulator.mon.event.GlimpseBaseEvent;

/**
 * @author acalabro
 *
 * @param <T>
 */
public interface EventsBuffer <T>{
	
	public void add(GlimpseBaseEvent<T> evt);
	public void remove(GlimpseBaseEvent<T> evt);
	public GlimpseBaseEvent<T> getElementAt(int index);
	public void removeElementAt(int index);
	public void clear();
	public void setDimension(int newSize);
	public int getSize();	
}
