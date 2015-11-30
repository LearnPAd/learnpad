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
package eu.learnpad.simulator.mon.consumer;

public class ConsumerProfile {
	private String name;
	private String answerTopic;
	public ConsumerProfile(String name, String answerTopic)
	{
		this.name = name;
		this.answerTopic = answerTopic;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getAnswerTopic()
	{
		return this.answerTopic;
	}
		
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAnswerTopic(String answerTopic)
	{
		this.answerTopic = answerTopic;
	}
}
