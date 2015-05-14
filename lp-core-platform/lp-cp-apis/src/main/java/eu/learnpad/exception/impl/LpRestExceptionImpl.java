package eu.learnpad.exception.impl;

import eu.learnpad.exception.LpRestException;

import org.xwiki.rest.XWikiRestException;

public class LpRestExceptionImpl extends LpRestException {

    /**
     * Default constructor.
     * @throws XWikiException 
     */
    public LpRestExceptionImpl() throws XWikiRestException
    {
        throw new XWikiRestException();
    }

    /**
     * @param message the exception message.
     */
    public LpRestExceptionImpl(String message) throws XWikiRestException
    {
        throw new XWikiRestException(message);
    }

    /**
     * @param message the exception message.
     * @param cause the exception cause.
     */
    public LpRestExceptionImpl(String message, Throwable cause) throws XWikiRestException
    {
        throw new XWikiRestException(message, cause);
    }

    /**
     * @param cause the exception cause.
     */
    public LpRestExceptionImpl(Throwable cause) throws XWikiRestException
    {
        throw new XWikiRestException(cause);
    }
	
	
}
