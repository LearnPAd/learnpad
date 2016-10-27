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
 *  $Id: ActionCleanOntology.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */
package gate.creole.ontology.impl.sesame;

import gate.gui.MainFrame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author johann
 */
class ActionCleanOntology
    extends AbstractAction {

  private AbstractOntologyImplSesame ontology;

  // TODO: add logger to constructor
  public ActionCleanOntology(String label, AbstractOntologyImplSesame aThis) {
    super(label);
    ontology = aThis;
  }

  public void actionPerformed(ActionEvent ae) {
    Runnable runableAction = new Runnable() {

      public void run() {
        // confirm: are you sure
        int returnValue =
            JOptionPane.showConfirmDialog(MainFrame.getInstance(),
            new JLabel("Are you sure you want to remove all data from the Ontology?"),
            "Confirm deleting ontology data",
            JOptionPane.YES_NO_OPTION);
        if (returnValue == JOptionPane.YES_OPTION) {
          try {
            MainFrame.lockGUI("Removing ontology data ... ");
            ontology.cleanOntology();
          } catch (Exception ex) {
            // TODO: log error to log
            //ex.printStackTrace(Out.getPrintWriter());
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
