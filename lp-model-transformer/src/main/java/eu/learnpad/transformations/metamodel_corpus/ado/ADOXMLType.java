/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ADOXML Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getATTRIBUTEPROFILES <em>ATTRIBUTEPROFILES</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getMODELS <em>MODELS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getAPPLICATIONMODELS <em>APPLICATIONMODELS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getMODELGROUPS <em>MODELGROUPS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getAdoversion <em>Adoversion</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getDatabase <em>Database</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getDate <em>Date</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getTime <em>Time</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getUsername <em>Username</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType()
 * @model extendedMetaData="name='ADOXML_._type' kind='elementOnly'"
 * @generated
 */
public interface ADOXMLType extends EObject {
	/**
	 * Returns the value of the '<em><b>ATTRIBUTEPROFILES</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ATTRIBUTEPROFILES</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATTRIBUTEPROFILES</em>' containment reference.
	 * @see #setATTRIBUTEPROFILES(ATTRIBUTEPROFILESType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_ATTRIBUTEPROFILES()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ATTRIBUTEPROFILES' namespace='##targetNamespace'"
	 * @generated
	 */
	ATTRIBUTEPROFILESType getATTRIBUTEPROFILES();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getATTRIBUTEPROFILES <em>ATTRIBUTEPROFILES</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ATTRIBUTEPROFILES</em>' containment reference.
	 * @see #getATTRIBUTEPROFILES()
	 * @generated
	 */
	void setATTRIBUTEPROFILES(ATTRIBUTEPROFILESType value);

	/**
	 * Returns the value of the '<em><b>MODELS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELS</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELS</em>' containment reference.
	 * @see #setMODELS(MODELSType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_MODELS()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='MODELS' namespace='##targetNamespace'"
	 * @generated
	 */
	MODELSType getMODELS();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getMODELS <em>MODELS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MODELS</em>' containment reference.
	 * @see #getMODELS()
	 * @generated
	 */
	void setMODELS(MODELSType value);

	/**
	 * Returns the value of the '<em><b>APPLICATIONMODELS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>APPLICATIONMODELS</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>APPLICATIONMODELS</em>' containment reference.
	 * @see #setAPPLICATIONMODELS(APPLICATIONMODELSType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_APPLICATIONMODELS()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='APPLICATIONMODELS' namespace='##targetNamespace'"
	 * @generated
	 */
	APPLICATIONMODELSType getAPPLICATIONMODELS();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getAPPLICATIONMODELS <em>APPLICATIONMODELS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>APPLICATIONMODELS</em>' containment reference.
	 * @see #getAPPLICATIONMODELS()
	 * @generated
	 */
	void setAPPLICATIONMODELS(APPLICATIONMODELSType value);

	/**
	 * Returns the value of the '<em><b>MODELGROUPS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELGROUPS</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELGROUPS</em>' containment reference.
	 * @see #setMODELGROUPS(MODELGROUPSType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_MODELGROUPS()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='MODELGROUPS' namespace='##targetNamespace'"
	 * @generated
	 */
	MODELGROUPSType getMODELGROUPS();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getMODELGROUPS <em>MODELGROUPS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MODELGROUPS</em>' containment reference.
	 * @see #getMODELGROUPS()
	 * @generated
	 */
	void setMODELGROUPS(MODELGROUPSType value);

	/**
	 * Returns the value of the '<em><b>Adoversion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adoversion</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adoversion</em>' attribute.
	 * @see #setAdoversion(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_Adoversion()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='adoversion'"
	 * @generated
	 */
	String getAdoversion();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getAdoversion <em>Adoversion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adoversion</em>' attribute.
	 * @see #getAdoversion()
	 * @generated
	 */
	void setAdoversion(String value);

	/**
	 * Returns the value of the '<em><b>Database</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' attribute.
	 * @see #setDatabase(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_Database()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='database'"
	 * @generated
	 */
	String getDatabase();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getDatabase <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' attribute.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(String value);

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_Date()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='date'"
	 * @generated
	 */
	String getDate();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(String value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_Time()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='time'"
	 * @generated
	 */
	String getTime();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(String value);

	/**
	 * Returns the value of the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Username</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Username</em>' attribute.
	 * @see #setUsername(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_Username()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='username'"
	 * @generated
	 */
	String getUsername();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getUsername <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Username</em>' attribute.
	 * @see #getUsername()
	 * @generated
	 */
	void setUsername(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getADOXMLType_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // ADOXMLType
