/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Space</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getWiki <em>Wiki</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getName <em>Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getHome <em>Home</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getXwikiRelativeUrl <em>Xwiki Relative Url</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getXwikiAbsoluteUrl <em>Xwiki Absolute Url</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getSpace()
 * @model extendedMetaData="name='Space' kind='elementOnly'"
 * @generated
 */
public interface Space extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getSpace_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Wiki</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wiki</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wiki</em>' attribute.
	 * @see #setWiki(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getSpace_Wiki()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='wiki' namespace='##targetNamespace'"
	 * @generated
	 */
	String getWiki();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getWiki <em>Wiki</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wiki</em>' attribute.
	 * @see #getWiki()
	 * @generated
	 */
	void setWiki(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getSpace_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Home</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Home</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Home</em>' attribute.
	 * @see #setHome(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getSpace_Home()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='home' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHome();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getHome <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Home</em>' attribute.
	 * @see #getHome()
	 * @generated
	 */
	void setHome(String value);

	/**
	 * Returns the value of the '<em><b>Xwiki Relative Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xwiki Relative Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xwiki Relative Url</em>' attribute.
	 * @see #setXwikiRelativeUrl(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getSpace_XwikiRelativeUrl()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='xwikiRelativeUrl' namespace='##targetNamespace'"
	 * @generated
	 */
	String getXwikiRelativeUrl();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getXwikiRelativeUrl <em>Xwiki Relative Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xwiki Relative Url</em>' attribute.
	 * @see #getXwikiRelativeUrl()
	 * @generated
	 */
	void setXwikiRelativeUrl(String value);

	/**
	 * Returns the value of the '<em><b>Xwiki Absolute Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xwiki Absolute Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xwiki Absolute Url</em>' attribute.
	 * @see #setXwikiAbsoluteUrl(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getSpace_XwikiAbsoluteUrl()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='xwikiAbsoluteUrl' namespace='##targetNamespace'"
	 * @generated
	 */
	String getXwikiAbsoluteUrl();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getXwikiAbsoluteUrl <em>Xwiki Absolute Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xwiki Absolute Url</em>' attribute.
	 * @see #getXwikiAbsoluteUrl()
	 * @generated
	 */
	void setXwikiAbsoluteUrl(String value);

} // Space
