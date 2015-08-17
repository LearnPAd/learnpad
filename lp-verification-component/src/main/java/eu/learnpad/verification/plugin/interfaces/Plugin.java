package eu.learnpad.verification.plugin.interfaces;

public interface Plugin {
	public String[] getVerificationTypeProvided();
	public String performVerification(String model, String Type); //FIXME: valutare come passare il modello e in che formato ritornare il risultato.
}
