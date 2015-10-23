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
package eu.learnpad.simulator.mon.cep;

import javax.jms.Message;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;

import eu.learnpad.simulator.mon.exceptions.UnknownMethodCallRuleException;
import eu.learnpad.simulator.mon.rules.RulesManager;

public abstract class ComplexEventProcessor extends Thread {
	public RulesManager cepRuleManager;
	public abstract RulesManager getRuleManager();
	
	public abstract void init(TopicConnectionFactory connectionFact, InitialContext initConn);
	public abstract void onMessage(Message arg0) throws UnknownMethodCallRuleException;	
	public abstract void setMetric();
}