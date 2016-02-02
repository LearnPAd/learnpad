/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Syntaxes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes#getSyntax <em>Syntax</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getSyntaxes()
 * @model extendedMetaData="name='Syntaxes' kind='elementOnly'"
 * @generated
 */
public interface Syntaxes extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>Syntax</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Syntax</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Syntax</em>' attribute list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getSyntaxes_Syntax()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='syntax' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getSyntax();

} // Syntaxes
