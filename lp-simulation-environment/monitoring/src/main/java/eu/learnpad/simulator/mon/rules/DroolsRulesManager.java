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
package eu.learnpad.simulator.mon.rules;

import java.util.Collection;

import org.apache.commons.net.ntp.TimeStamp;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.io.ResourceFactory;
import org.w3c.dom.DOMException;

import eu.learnpad.simulator.mon.exceptions.IncorrectRuleFormatException;
import eu.learnpad.simulator.mon.exceptions.UnknownMethodCallRuleException;
import eu.learnpad.simulator.mon.exceptions.UnknownRuleException;
import eu.learnpad.simulator.mon.rules.RulesManager;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionType;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

public class DroolsRulesManager extends RulesManager {

	static KnowledgeBuilder kbuilder;
	static KnowledgeBase kbase;
	
	public DroolsRulesManager(Object knowledgeBuilder, Object knowledgeBase, Object knowledgeSession) {
		super(knowledgeBuilder, knowledgeBase, knowledgeSession);
		kbuilder = (KnowledgeBuilder) knowledgeBuilder;
		kbase = (KnowledgeBase) knowledgeBase;

		KnowledgeBuilderConfiguration config = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();     
		config.setProperty("drools.dialect.mvel.strict", "false");
		kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(config);
	}
	
	public Object[] vandaLoadRules(final ComplexEventRuleActionType rules) throws IncorrectRuleFormatException {
		
		String hugeRule = "import eu.learnpad.simulator.mon.event.GlimpseBaseEventBPMN;\n\t\t" +
				"import eu.learnpad.simulator.mon.manager.ResponseDispatcher;\n\t\t" +
				"import eu.learnpad.simulator.mon.manager.RestNotifier;\n\t\t" +
				"import eu.learnpad.simulator.mon.utils.NotifierUtils;\n\t\t" +
				"import eu.learnpad.simulator.mon.rules.DroolsRulesManager;\n\t\t" +
				"import eu.learnpad.sim.rest.event.AbstractEvent;\n\t\t" +
				"import eu.learnpad.sim.rest.event.EventType;\n\t\t" +
				"import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;\n\t\t" +
				"import eu.learnpad.sim.rest.event.impl.TaskEndEvent;\n\t\t" +
				"\t\tdeclare GlimpseBaseEventBPMN\n" +
				"\t\t\t@role( event )\n" +
				"\t\t\t@timestamp( timeStamp )\n" +
				"\t\tend\n\n";
		
		
		if (kbuilder == null) {
			kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		}
		
		final ComplexEventRuleType[] insertRules = rules.getInsertArray();
		for(int i = 0; i < insertRules.length; i++)
		{
			try {
				hugeRule+=(insertRules[i].getRuleBody());
				insertRule(hugeRule,"onlyOne");
			} catch (final DOMException e) {
				e.printStackTrace();
			} catch (UnknownMethodCallRuleException e) {
				e.printStackTrace();
			}
		}
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		DroolsRulesManager.getLoadedRulesInfo();
		
		return kbase.getKnowledgePackages().toArray();
}
	
	public Object[] loadRules(final ComplexEventRuleActionType rules) throws IncorrectRuleFormatException {
		
		if (kbuilder == null) {
			kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		}
		final ComplexEventRuleType[] insertRules = rules.getInsertArray();
		for(int i = 0; i < insertRules.length; i++)
		{
			try {
				insertRule(insertRules[i].getRuleBody(),insertRules[i].getRuleName());
			} catch (final DOMException e) {
				e.printStackTrace();
			} catch (UnknownMethodCallRuleException e) {
				e.printStackTrace();
			}
		}
		
		final ComplexEventRuleType[] deleteRules = rules.getDeleteArray();
		for(int i = 0; i < deleteRules.length; i++)
		{
			try {
				deleteRule(deleteRules[i].getRuleName());
			} catch (final DOMException e) {
				e.printStackTrace();
			} catch (final UnknownRuleException e) {
				e.printStackTrace();
			}
		}		
				
		final ComplexEventRuleType[] startRules = rules.getStartArray();
		for(int i = 0; i < startRules.length; i++)
		{
			try {
				startRule(startRules[i].getRuleName());
			} catch (final DOMException e) {
				e.printStackTrace();
			} catch (final UnknownRuleException e) {
				e.printStackTrace();
			}
		}	
		
		final ComplexEventRuleType[] stopRules = rules.getStopArray();
		for(int i = 0; i < stopRules.length; i++)
		{
			try {
				stopRule(stopRules[i].getRuleName());
			} catch (final DOMException e) {
				e.printStackTrace();
			} catch (final UnknownRuleException e) {
				e.printStackTrace();
			}
		}	
		
		final ComplexEventRuleType[] restartRules = rules.getRestartArray();
		for(int i = 0; i < restartRules.length; i++)
		{
			try {
				restartRule(restartRules[i].getRuleName());
			} catch (final DOMException e) {
				e.printStackTrace();
			} catch (final UnknownRuleException e) {
				e.printStackTrace();
			}
		}

		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		DroolsRulesManager.getLoadedRulesInfo();
		
		return kbase.getKnowledgePackages().toArray();
	}

	@Override
	public void insertRule(final String rule, final String ruleName) throws IncorrectRuleFormatException, UnknownMethodCallRuleException {
		try {
			Long now = System.currentTimeMillis();
		kbuilder.add(ResourceFactory.newByteArrayResource(rule.trim().getBytes()), ResourceType.DRL);
		DebugMessages.println(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Time Elapsed for loading one rule: " + (System.currentTimeMillis() - now));
		} catch (Exception droolsExceptionOnLoading) {
			DebugMessages.println(TimeStamp.getCurrentTime(),this.getClass().getCanonicalName(), droolsExceptionOnLoading.getCause() + "\n" +
					droolsExceptionOnLoading.getMessage());			
			throw new UnknownMethodCallRuleException();
		}

		if (kbuilder.getErrors().size() > 0)
			 throw new IncorrectRuleFormatException(kbuilder.getErrors()); 
	}

	@Override
	public void deleteRule(final String ruleName) throws UnknownRuleException {
		
		DebugMessages.println(TimeStamp.getCurrentTime(),this.getClass().getSimpleName(), "Listing rules loaded into the knowledgeBases");
		Collection<KnowledgePackage> ass = kbase.getKnowledgePackages();
		Object esd[] = ass.toArray();
		for (int i = 0; i<esd.length; i++) {
			
			KnowledgePackage kp = (KnowledgePackage)esd[i];
			Collection<Rule> rls = kp.getRules();
			Object r[] = rls.toArray();
			DebugMessages.println(TimeStamp.getCurrentTime(),this.getClass().getSimpleName(), "KnowledgeBase name: " + kp.getName());
			if (r.length > 0) {
				for (int j = 0; j<r.length;j++) {
					Rule gg = (Rule) r[j];
					if (gg.getName().compareTo(ruleName) == 0)
					{
						kp.getRules().remove(gg);
					}
				}
			}
		}
	}

	void startRule(final String ruleName) throws UnknownRuleException {
		
	}

	void stopRule(final String ruleName) throws UnknownRuleException {
		
	}

	void restartRule(final String ruleName) throws UnknownRuleException {
		
	}
	
	public int getLoadedKnowledgePackageCardinality() {
		return kbase.getKnowledgePackages().size();
	}
	
	public static void getLoadedRulesInfo()
	{
		DebugMessages.println(TimeStamp.getCurrentTime(),DroolsRulesManager.class.getSimpleName(), "Listing rules loaded into the knowledgeBases");
		Collection<KnowledgePackage> ass = kbase.getKnowledgePackages();
		Object esd[] = ass.toArray();
		for (int i = 0; i<esd.length; i++) {
			
			KnowledgePackage kp = (KnowledgePackage)esd[i];
			Collection<Rule> rls = kp.getRules();
			Object r[] = rls.toArray();
			DebugMessages.println(TimeStamp.getCurrentTime(),DroolsRulesManager.class.getSimpleName(), "KnowledgeBase name: " + kp.getName());
			if (r.length == 0) {
				DebugMessages.println(TimeStamp.getCurrentTime(),DroolsRulesManager.class.getSimpleName(), "--! No rules loaded into: " + kp.getName() + " knowledgeBase");
			}
			else
			{
				for (int j = 0; j<r.length;j++) {
					Rule gg = (Rule) r[j];
					DebugMessages.println(TimeStamp.getCurrentTime(),DroolsRulesManager.class.getSimpleName(), "RuleName: " + gg.getName());	
					}
			}
		}
	}	

	public static void unloadRule(String ruleName) {
		
		Collection<KnowledgePackage> ass = kbase.getKnowledgePackages();
		Object esd[] = ass.toArray();
		for (int i = 0; i<esd.length; i++) {
			
			KnowledgePackage kp = (KnowledgePackage)esd[i];
			Collection<Rule> rls = kp.getRules();
			Object r[] = rls.toArray();
			DebugMessages.println(TimeStamp.getCurrentTime(), DroolsRulesManager.class.getSimpleName(), "KnowledgeBase name: " + kp.getName());
			if (r.length > 0) {
				for (int j = 0; j<r.length;j++) {
					Rule gg = (Rule) r[j];
					if (gg.getName().compareTo(ruleName) == 0)
					{
						kbase.removeRule(kp.getName(),gg.getName());
					}
				}
			}
		}
		DroolsRulesManager.getLoadedRulesInfo();
	}
}
