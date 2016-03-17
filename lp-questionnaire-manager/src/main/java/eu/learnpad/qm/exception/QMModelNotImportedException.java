package eu.learnpad.qm.exception;

public class QMModelNotImportedException extends QMExeption {

	public QMModelNotImportedException()
    {
		super();
    }

    /**
     * @param message the exception message.
     */
    public QMModelNotImportedException(String message)
    {
    	super(message);
    }

    /**
     * @param message the exception message.
     * @param cause the exception cause.
     */
    public QMModelNotImportedException(String message, Throwable cause)
    {
    	super(message, cause);
    }
}
