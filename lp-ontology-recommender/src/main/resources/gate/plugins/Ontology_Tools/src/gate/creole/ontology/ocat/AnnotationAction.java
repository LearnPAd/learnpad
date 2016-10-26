/*
 *  AnnotationAction.java
 *
 *  Niraj Aswani, 12/March/07
 *
 *  $Id: AnnotationAction.html,v 1.0 2007/03/12 16:13:01 niraj Exp $
 */
package gate.creole.ontology.ocat;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;

/**
 * This class provides the GUI implementation for creating/changing/deleting
 * annotations from the text. It uses OntologyTreePanel to display the list of
 * available classes in the ontology.
 * 
 * @author niraj
 */
public class AnnotationAction extends MouseInputAdapter {

  /**
   * Reference to the main OntologyTreePanel object
   */
  private OntologyTreePanel ontologyTreePanel;

  /**
   * Timer object
   */
  private javax.swing.Timer annotationWindowTimer;

  /**
   * How long we should wait before showing a new annotation/change annotation
   * window.
   */
  private final int DELAY = 500;

  /**
   * Action that is performed when user decides to create a new annotation.
   */
  private AnnotationEditor annotationEditor;

  /**
   * Constructor
   * 
   * @param ontologyTreePanel
   *          the instance this instance uses to obtain the information about
   *          ontology
   */
  public AnnotationAction(OntologyTreePanel ontoTreePanel) {
    this.ontologyTreePanel = ontoTreePanel;
    annotationEditor = new AnnotationEditor(ontoTreePanel);
    annotationWindowTimer = new javax.swing.Timer(DELAY, annotationEditor);
    annotationWindowTimer.setRepeats(false);
  }

  /**
   * Grabs the current location of mouse pointers
   * 
   * @param e
   */
  public void mousePressed(MouseEvent e) {
    // if mouse is pressed anywhere, we simply hide all the windows
    hideAllWindows();
  }

  /**
   * This method to hide all the popup windows
   */
  public void hideAllWindows() {
    if(ontologyTreePanel.showingAnnotationWindow) {
      ontologyTreePanel.showingAnnotationWindow = false;
      annotationEditor.hideWindow();
    }
  }

  /**
   * Invoked when Mouse hovers over the document
   * 
   * @param e
   */
  public void mouseMoved(MouseEvent e) {
    if(ontologyTreePanel.currentOntologyTree == null
      || ontologyTreePanel.showingAnnotationWindow) return;
    // mouse is moved so simply activate the timer
    annotationEditor
      .setTextLocation(ontologyTreePanel.ontoViewer.documentTextArea
        .viewToModel(e.getPoint()));
    annotationWindowTimer.restart();
    annotationEditor.setMousePoint(e.getPoint());
  }

  /**
   * Invoked when mouse is dragged
   */
  public void mouseDragged(MouseEvent e) {
    mouseMoved(e);
  }
}
