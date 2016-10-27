/**
 * ResourceInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package gate.creole.ontology.impl;

public class ResourceInfo  implements java.io.Serializable {
    private byte classType;
    private java.lang.String uri;

    public ResourceInfo() {
    }

    public ResourceInfo(java.lang.String uri, byte classType) {
           this.classType = classType;
           this.uri = uri;
    }

    /**
     * Gets the uri value for this ResourceInfo.
     * 
     * @return uri
     */
    public java.lang.String getUri() {
        return uri;
    }


    /**
     * Sets the uri value for this ResourceInfo.
     * 
     * @param uri
     */
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }


    public byte getClassType() {
      return classType;
    }

    public void setClassType(byte classType) {
      this.classType = classType;
    }

}
