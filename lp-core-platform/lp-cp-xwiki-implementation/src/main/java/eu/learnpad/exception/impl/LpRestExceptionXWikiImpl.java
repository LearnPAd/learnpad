package eu.learnpad.exception.impl;

import eu.learnpad.exception.LpRestException;

import org.xwiki.rest.XWikiRestException;

public final class LpRestExceptionXWikiImpl extends LpRestException {

	boolean isXWikiRestException;
	
    /**
     * Default constructor.
     * @throws XWikiException 
     */
    public LpRestExceptionXWikiImpl()
    {
        this.isXWikiRestException=false;
    }

    /**
     * @param message the exception message.
     */
    public LpRestExceptionXWikiImpl(String message)
    {
    	super(message);
        this.isXWikiRestException=false;
    }

    /**
     * @param message the exception message.
     * @param cause the exception cause.
     */
    public LpRestExceptionXWikiImpl(String message, Throwable cause)
    {
    	super(message, cause);
        this.isXWikiRestException=false;
    }

    /**
     * @param cause the exception cause.
     */
    public LpRestExceptionXWikiImpl(Throwable cause)
    {
    	super(cause);
        this.isXWikiRestException=false;
    }
	
    /**
     * @param cause the exception cause.
     */
    public LpRestExceptionXWikiImpl(XWikiRestException xWikiRestExeption)
    {
    	super(xWikiRestExeption);
        this.isXWikiRestException=true;
    }
    
    /**
     * It extracts back an LpRestExceptionXWikiImpl caused by an XWikiRestException
     * into the original XWikiRestException.
     * 
     * @param e the exception cause.
     * @return the original XWikiRestException
     * @throws  RuntimeException if the extraction fails
     */
    public static XWikiRestException extractXWikiRestException (LpRestException e) throws RuntimeException{
    	if (e instanceof LpRestExceptionXWikiImpl) {
    		LpRestExceptionXWikiImpl e1 = (LpRestExceptionXWikiImpl) e;
    		if (e1.isXWikiRestException)
    	    	return (XWikiRestException) e1.getCause();    				
		}
		throw new RuntimeException("Cannot extract LpRestException into XWikiRestException");
    }
    
}
