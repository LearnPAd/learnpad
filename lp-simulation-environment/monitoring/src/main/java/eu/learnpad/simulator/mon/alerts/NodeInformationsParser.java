package eu.learnpad.simulator.mon.alerts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodeInformationsParser extends AbstractParser{

	private String inputNodeInformations;
	private String processedNodeInformations;
	
	private String serviceName;
	private String machineName;

	private static final String SERVICENAME="serviceName";
	private static final String SERVICENAME_TAG_PATTERN_PRE = SERVICENAME+"[^>]*>.*";
	private static final String SERVICENAME_TAG_PATTERN_POST = "</[^>]*"+SERVICENAME+">";
	private static final String PROXY = "providerProxyEndpoint";
	private static final String PROXY_TAG_PATTERN = PROXY+".*?"+PROXY+">";	
//	private static final String PROXY_TAG_PATTERN = PROXY+">.*</[^>]*"+PROXY+">";	
	private static final String SENDER="senderInitialisation";
	private static final String SENDER_TAG_PATTERN = SENDER+">.*</[^>]*"+SENDER+">";
	private static final String EXTERNALADDRESS="externalProviderAddress";
	private static final String EXTERNALADDRESS_TAG_PATTERN = EXTERNALADDRESS+">.*</[^>]*"+EXTERNALADDRESS+">";
//	private static final String MACHINE_NAME = ".*(://)?.*"
	
	public NodeInformationsParser(){
		this("");
	}

	public NodeInformationsParser(String nodeInformations){
		this(nodeInformations,null);
	}

	public NodeInformationsParser(String nodeInformations, String serviceName){
		this.setServiceName(serviceName);
		this.setNodeInformations(nodeInformations);
	}
		

	public void process (){		
		if (this.serviceName != null){
			this.processedNodeInformations = this.convertSpecialChars(this.inputNodeInformations);
			String proxy = this.extractProxy();	
			if (proxy != null){
				String senderInitialisation = this.extractSenderInitialisation(proxy);
				if (senderInitialisation != null){
					this.extractMachineName(senderInitialisation);
				}
			}
		}
	}

	private String extractSenderInitialisation(String proxy) {
		 Pattern p = Pattern.compile(SENDER_TAG_PATTERN);
		 Matcher m = p.matcher(proxy);
		 if (m.find()){
			 return m.group();
		 }
		 return null;
	}

	private void extractMachineName(String senderInitialisation) {
		 Pattern p = Pattern.compile(EXTERNALADDRESS_TAG_PATTERN);
		 Matcher m = p.matcher(senderInitialisation);
		 if (m.find()){
			 String tmpMachineName = m.group(); 
			 tmpMachineName = tmpMachineName.replaceAll(EXTERNALADDRESS, "");
			 tmpMachineName = tmpMachineName.replaceAll(">", "").replaceAll("<.*", "");
			 tmpMachineName = tmpMachineName.replaceAll(".*//", "").replaceAll(":.*", "");
			 this.machineName = tmpMachineName;
		 }

		
	}

	private String extractProxy() {
		 Pattern proxyPattern = Pattern.compile(PROXY_TAG_PATTERN);
		 Matcher proxyMatcher = proxyPattern.matcher(this.processedNodeInformations);

		 String serviceNameRegEx = SERVICENAME_TAG_PATTERN_PRE+this.serviceName+SERVICENAME_TAG_PATTERN_POST;
		 Pattern servicePattern = Pattern.compile(serviceNameRegEx);
		 Matcher serviceMatcher;
		 String proxy ;
		 
		 boolean foundOne = proxyMatcher.find();
		 while (foundOne){
			 proxy = proxyMatcher.group();
			 serviceMatcher = servicePattern.matcher(proxy);
			 if (serviceMatcher.find()){
				 return proxy;
			 }else{
				 foundOne = proxyMatcher.find();
			 }
		 }
		return null;
	}

	public String getProcessedNodeInformations(){
		return this.processedNodeInformations;
	}

	public String getProcessedMachineName() {
		return this.machineName;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setNodeInformations(String nodeInformations){
		this.inputNodeInformations = nodeInformations;
	}
}
