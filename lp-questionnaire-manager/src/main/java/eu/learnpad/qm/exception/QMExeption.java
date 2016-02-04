package eu.learnpad.qm.exception;

public class QMExeption extends Exception {

	public QMExeption()
    {
		super();
    }

    /**
     * @param message the exception message.
     */
    public QMExeption(String message)
    {
    	super(message);
    }

    /**
     * @param message the exception message.
     * @param cause the exception cause.
     */
    public QMExeption(String message, Throwable cause)
    {
    	super(message, cause);
    }


}
