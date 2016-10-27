/**
 * Property.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package gate.creole.ontology.impl;

public class Property {

    private byte type;

    private java.lang.String uri;

    public Property() {
    }

    public Property(
           byte type,
           java.lang.String uri) {
           this.type = type;
           this.uri = uri;
    }


    /**
     * Gets the type value for this Property.
     * 
     * @return type
     */
    public byte getType() {
        return type;
    }


    /**
     * Sets the type value for this Property.
     * 
     * @param type
     */
    public void setType(byte type) {
        this.type = type;
    }


    /**
     * Gets the uri value for this Property.
     * 
     * @return uri
     */
    public java.lang.String getUri() {
        return uri;
    }


    /**
     * Sets the uri value for this Property.
     * 
     * @param uri
     */
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }
}
