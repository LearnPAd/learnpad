package eu.learnpad.simulator.mon.impl;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionType;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.simulator.mon.exceptions.IncorrectRuleFormatException;
import eu.learnpad.simulator.mon.rules.RulesManager;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import eu.learnpad.simulator.mon.utils.Manager;

public class RuleTemplateManager {

	public static String localDroolsRequestTemplatesFilePathOne;
	public static String localDroolsRequestTemplatesFilePathTwo;
	public static String localDroolsRequestTemplatesFilePathThree_1;
	public static String localDroolsRequestTemplatesFilePathThree_2;
	public static RuleTemplateManager instance = null;
	public static RulesManager rulesManager;
	private String finalString;
	private int startReplace;
	
	public RuleTemplateManager(String droolsRequestTemplatesFilePathOne, String droolsRequestTemplatesFilePathTwo, String droolsRequestTemplatesFilePathThree_1, String droolsRequestTemplatesFilePathThree_2) {
		
		RuleTemplateManager.localDroolsRequestTemplatesFilePathOne = droolsRequestTemplatesFilePathOne;
		RuleTemplateManager.localDroolsRequestTemplatesFilePathTwo = droolsRequestTemplatesFilePathTwo;
		RuleTemplateManager.localDroolsRequestTemplatesFilePathThree_1 = droolsRequestTemplatesFilePathThree_1;
		RuleTemplateManager.localDroolsRequestTemplatesFilePathThree_2 = droolsRequestTemplatesFilePathThree_2;
	}
	
	public static synchronized RuleTemplateManager getSingleton() {
        if (instance == null) 
            instance = new RuleTemplateManager(localDroolsRequestTemplatesFilePathOne,localDroolsRequestTemplatesFilePathTwo, localDroolsRequestTemplatesFilePathThree_1, localDroolsRequestTemplatesFilePathThree_2);
        return instance;
    }

	public String setBody(String machineIP, String serviceName, 
							RuleTemplateEnum templateType, long timeStamp,
							String filterService) {
		String ruleSelected;
		switch(templateType) {
	      case EVENTAAFTEREVENTB: {
	    	  ruleSelected = Manager.ReadTextFromFile(localDroolsRequestTemplatesFilePathOne);
	    	  ruleSelected.replaceAll("$$MACHINEIP$$", machineIP);
	    	  ruleSelected.replaceAll("$$SERVICENAME$$", serviceName);
	      }
	        break;
	     
	      case EVENTABETWEENEVENTB: {
	    	  ruleSelected = Manager.ReadTextFromFile(localDroolsRequestTemplatesFilePathOne);
	    	  ruleSelected.replaceAll("$$MACHINEIP$$", machineIP);
	    	  ruleSelected.replaceAll("$$SERVICENAME$$", serviceName);
	      }
	       break;
	       
	      case NOEVENTFROMINFRASTRUCTURE: {
	    	  ruleSelected = Manager.ReadTextFromFile(localDroolsRequestTemplatesFilePathTwo);
	    	  startReplace = ruleSelected.indexOf("MACHINE_IP");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  machineIP +
		    		  ruleSelected.substring(startReplace+10,ruleSelected.length());
		      
		      ruleSelected = finalString;
		      
		      startReplace = ruleSelected.indexOf("MACHINE_IP");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  machineIP +
		    		  ruleSelected.substring(startReplace+10,ruleSelected.length());
		      startReplace = finalString.indexOf("SERVICE_NAME");
		      ruleSelected = finalString.substring(0, startReplace) +
		    		  serviceName 
		    		  + finalString.substring(startReplace+12,finalString.length());
		      
		      startReplace = ruleSelected.indexOf("SERVICE_NAME");
		      ruleSelected = ruleSelected.substring(0, startReplace) +
		    		  serviceName 
		    		  + ruleSelected.substring(startReplace+12,ruleSelected.length());
		      
		      startReplace = ruleSelected.indexOf("_TIMESTAMP_");
		      ruleSelected = ruleSelected.substring(0,startReplace) + String.valueOf(timeStamp) +
		    		  ruleSelected.substring(startReplace+11,ruleSelected.length());
		      System.out.println(ruleSelected);

	      }
	       break;
	      
	      case INFRASTRUCTUREVIOLATION: {
	    	  ruleSelected = Manager.ReadTextFromFile(localDroolsRequestTemplatesFilePathOne);
		      startReplace = ruleSelected.indexOf("MACHINE_IP");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  machineIP +
		    		  ruleSelected.substring(startReplace+10,ruleSelected.length());
		      
		      ruleSelected = finalString;
		      
		      startReplace = ruleSelected.indexOf("MACHINE_IP");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  machineIP +
		    		  ruleSelected.substring(startReplace+10,ruleSelected.length());
		      
		      startReplace = finalString.indexOf("SERVICE_NAME");
		      ruleSelected = finalString.substring(0, startReplace) +
		    		  serviceName 
		    		  + finalString.substring(startReplace+12,finalString.length());

		      startReplace = ruleSelected.indexOf("SERVICE_NAME");
		      ruleSelected = ruleSelected.substring(0, startReplace) +
		    		  serviceName 
		    		  + ruleSelected.substring(startReplace+12,ruleSelected.length());
		      
		      startReplace = ruleSelected.indexOf("_TIMESTAMP_");
		      ruleSelected = ruleSelected.substring(0,startReplace) + String.valueOf(timeStamp) +
		    		  ruleSelected.substring(startReplace+11,ruleSelected.length());
		      System.out.println(ruleSelected);

	    	  }
	    	 break;
	    	 
	      case CHECKSERVICESLAFAILURE3TIMES_FIRSTPART: { 
	    	  //must use FilterService the new rule
	    	  ruleSelected = Manager.ReadTextFromFile(localDroolsRequestTemplatesFilePathThree_1);
		      
	    	  startReplace = ruleSelected.indexOf("SERVICE_NAME");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  serviceName +
		    		  ruleSelected.substring(startReplace+12,ruleSelected.length());
		      
		      ruleSelected = finalString;
		      
		      startReplace = ruleSelected.indexOf("_SERVICE_FILTER_");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  filterService +
		    		  ruleSelected.substring(
		    				  startReplace+16,ruleSelected.length());
		      ruleSelected = finalString;
		      
		      startReplace = ruleSelected.indexOf("_SERVICE_FILTER_");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  filterService 
		    		  + ruleSelected.substring(startReplace+16,ruleSelected.length());
		      ruleSelected = finalString;

		      startReplace = ruleSelected.indexOf("_SERVICE_FILTER_");
		      if (startReplace != -1) {
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  filterService 
		    		  + ruleSelected.substring(startReplace+16,ruleSelected.length());
		      ruleSelected = finalString;
		      }
		      startReplace = ruleSelected.indexOf("SERVICE_NAME");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  serviceName 
		    		  + ruleSelected.substring(startReplace+12,ruleSelected.length());
		      ruleSelected = finalString;
		      System.out.println(ruleSelected);

	    	 }
	    	 break;
	    	 
	      case CHECKSERVICESLAFAILURE3TIMES_SECONDPART: {
	    	  	
	    	  ruleSelected = Manager.ReadTextFromFile(localDroolsRequestTemplatesFilePathThree_2);
		      
	    	  startReplace = ruleSelected.indexOf("SERVICE_NAME");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  serviceName +
		    		  ruleSelected.substring(startReplace+12,ruleSelected.length());
		      
		      ruleSelected = finalString;
		      
//		      startReplace = ruleSelected.indexOf("MACHINE_IP");
//		      finalString = ruleSelected.substring(0, startReplace) +
//		    		  machineIP +
//		    		  ruleSelected.substring(
//		    				  startReplace+10,ruleSelected.length());
//		      ruleSelected = finalString;
		      
		      startReplace = ruleSelected.indexOf("SERVICE_NAME");
		      finalString = ruleSelected.substring(0, startReplace) +
		    		  serviceName 
		    		  + ruleSelected.substring(startReplace+12,ruleSelected.length());
		      ruleSelected = finalString;
		      System.out.println(ruleSelected);
	      }
	      break;
	    	 
	      default:
	    	  ruleSelected = "";
	    	  break;
	    }
		return ruleSelected;
	}
	
	
	public ComplexEventRuleActionListDocument generateNewRuleToInjectInKnowledgeBase(
			String machineIP, String serviceName,
			RuleTemplateEnum ruleTemplateType, 
			Long timeStamp, String filterService) {
		
		ComplexEventRuleActionListDocument ruleDoc;			
		ruleDoc = ComplexEventRuleActionListDocument.Factory.newInstance();
		ComplexEventRuleActionType ruleActions = ruleDoc.addNewComplexEventRuleActionList();
		ComplexEventRuleType ruleType = ruleActions.addNewInsert();
		ruleType.setRuleName(machineIP);
		ruleType.setRuleType("drools");
		
		ruleType.setRuleBody(
				setBody(
						machineIP, serviceName, 
										ruleTemplateType, timeStamp, filterService));
		return ruleDoc;
	}

	public int insertRule(ComplexEventRuleActionListDocument newRuleToInsert, RulesManager engine) {
		try {
			rulesManager = engine;
			DebugMessages.println(TimeStamp.getCurrentTime(), ServiceLocatorImpl.class.getCanonicalName(), "Updating knowledgeBase " +
			rulesManager.getLoadedKnowledgePackageCardinality());

			rulesManager.loadRules(newRuleToInsert.getComplexEventRuleActionList());
			DebugMessages.println(TimeStamp.getCurrentTime(), ServiceLocatorImpl.class.getCanonicalName(), "KnowledgeBase updated");
		} catch (IncorrectRuleFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int unloadRule(int ruleInsertionID) {
		return 0;
	}
}
