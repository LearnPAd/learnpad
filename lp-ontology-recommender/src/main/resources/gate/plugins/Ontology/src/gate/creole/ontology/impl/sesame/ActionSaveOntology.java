/*
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  Johann Petrak 2009-08-13
 *
 *  $Id: ActionSaveOntology.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */
package gate.creole.ontology.impl.sesame;

import gate.gui.MainFrame;
import gate.util.GateRuntimeException;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Class for carrying out the action of saving an ontology.
 * This class creates uses {@link #GUISaveOptionsPane} to show a dialog
 * for specifying the file to save to and other options and saves the
 * ontology using those options if the user does not choose to cancel.
 * 
 * @author Johann Petrak
 */
class ActionSaveOntology
    extends AbstractAction {

  static final long serialVersionUID = 1L;

  private AbstractOntologyImplSesame ontology;

  public ActionSaveOntology(String label, AbstractOntologyImplSesame aThis) {
    super(label);
    ontology = aThis;
  }

  public void actionPerformed(ActionEvent ae) {
    Runnable runableAction = new Runnable() {

      public void run() {
        GUISaveOptionsPane pane = new GUISaveOptionsPane();
        boolean ok = pane.showOptionDialog();
        if (ok) {
          try {
            MainFrame.lockGUI("Saving ontology ... ");
            ontology.writeOntologyData(pane.getFile(),
                pane.getFormat(), pane.getIncludeImports());
          } catch (Exception ex) {
            throw new GateRuntimeException(
                "Problem when saving ontology to file "+
                pane.getFile().getAbsolutePath(),ex);
            } finally {
            MainFrame.unlockGUI();
          }
        }
      }
    };
    Thread thread = new Thread(runableAction, "");
    thread.setPriority(Thread.MIN_PRIORITY);
    thread.start();
  }
}
