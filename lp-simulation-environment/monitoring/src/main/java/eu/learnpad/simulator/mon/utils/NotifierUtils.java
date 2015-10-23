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
package eu.learnpad.simulator.mon.utils;

/**
 * @author Antonello Calabr&ograve;
 *
 *This class provide some basic functionalities to evaluate
 *pattern between simple/complex event captured by the cep
 *
 */
public class NotifierUtils {
	
	public static boolean strictlyFollows(int firstEventID, int secondEventID)
	{
		if (secondEventID - firstEventID == 1)
		{
			return true;
		}
		else
			return false;
	}
	
	public static long latency(long firstEvent, long secondEvent)
	{
		return secondEvent-firstEvent;
	}
	
	public static boolean strictlyFollows(int firstEventID, int secondEventID, int thirdEventID)
	{
		if ((secondEventID - firstEventID == 1) && (thirdEventID - secondEventID == 1))
		{
			
			return true;
		}
		else
			return false;
	}
	
	public static double evaluateProb(int eventToEvaluateOccurrencies, int totalEventsNumber)
	{
		if (eventToEvaluateOccurrencies != 0 && totalEventsNumber != 0)
		{
			return (eventToEvaluateOccurrencies*100) / totalEventsNumber;
		}
		else
			return 0;
	}
	
	public static double ratio (int one, int two)
	{
		if (one != 0 && two != 0)
		{
			return (double)one/two;
		}
		return 0;
	}
	
	public static String eventName (String eventName)
	{
		return eventName;
	}
}
