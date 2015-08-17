package eu.learnpad.verification.plugin.interfaces;
/*
 * This interface should be implemented in order to create a valid plugin for the verification component
 */
public interface Plugin {
	/**
	 * The method return a list of verification capabilities provided by the plugin
	 * @return	an array of string identifying the verification type provided
	 */
	public String[] getVerificationTypeProvided();
	
	/**
	 * The method perform the verification specified over the provided model. The verification have to be done in a synchronous way. Asynchronous support is provided by the verification component that manage the plugins.
	 * @param	model	The model to verify (currently the model passed is the full ADOxx LearnPAd xml)
	 * @param	type	The type of verification to perform; one provided by the method getVerificationTypeProvided
	 * @return 	The verification result in XML format. The root element have to be <Result><Result/>
	 */
	public String performVerification(String model, String type);
}
