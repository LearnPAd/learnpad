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
 *  $Id: GUISaveOptionsPane.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */
package gate.creole.ontology.impl.sesame;

import gate.creole.ontology.OConstants;
import gate.creole.ontology.OConstants.OntologyFormat;
import gate.gui.MainFrame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class for creating and showing the save ontology dialog.
 * This class creates the dialog pane for specifying the file name,
 * format, and other relevant options for saving an ontology to a file.
 *
 * @author Johann Petrak
 */
public class GUISaveOptionsPane extends JPanel {

  static final long serialVersionUID = 1L;

  public GUISaveOptionsPane() {
    super(new GridBagLayout());
    init();
  }
  private JComboBox formatChooser;
  private JTextField saveFileField;
  private JCheckBox includeImports;
  private File theFile;

  private void init() {

    // Columns for the gridbag layout:
    //  4 for the label
    //  6 for the input/selection
    //  1 for the button

    // First row -------------------------

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 0;
    constraints.gridwidth = 4;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.NONE;
    constraints.insets = new Insets(0, 0, 0, 0);
    this.add(new JLabel("Save to file:"), constraints);

    saveFileField = new JTextField("", 20);
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 0;
    constraints.gridwidth = 6;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 10, 0, 10);
    this.add(saveFileField, constraints);

    JButton btnChooseFile = new JButton("Select");
    btnChooseFile.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent ae) {
        JFileChooser fileChooser = MainFrame.getFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Specify file to save the ontology to");
        fileChooser.setSelectedFiles(null);
        int res = fileChooser.showDialog(null, "Choose");
        if (res == JFileChooser.APPROVE_OPTION) {
          saveFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
      }
    });

    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.NORTHWEST;
    this.add(btnChooseFile, constraints);
    //btnChooseFile.setBorderPainted(false);
    //btnChooseFile.setContentAreaFilled(false);


    // Second row, file format ------------------------

    formatChooser = new JComboBox(OntologyFormat.values());
    formatChooser.setToolTipText("Select the format to use for the file");
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 1;
    constraints.gridwidth = 4;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.NONE;
    constraints.insets = new Insets(0, 0, 0, 0);
    this.add(new JLabel("Annotation Sets:"), constraints);

    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 1;
    constraints.gridwidth = 6;
    constraints.fill = GridBagConstraints.NORTHWEST;
    constraints.insets = new Insets(0, 10, 0, 10);
    this.add(formatChooser, constraints);

    // Third row, include imports ------------------------

    includeImports =
        new JCheckBox("");
    includeImports.setSelected(false);
    includeImports.setToolTipText("Check to include data that was included as imports");

    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 2;
    constraints.gridwidth = 4;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.NONE;
    constraints.insets = new Insets(0, 0, 0, 0);
    this.add(new JLabel("Include imports:"), constraints);

    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 2;
    constraints.gridwidth = 6;
    constraints.fill = GridBagConstraints.NORTHWEST;
    constraints.insets = new Insets(0, 10, 0, 10);
    this.add(includeImports, constraints);




  }

  public boolean showOptionDialog() {
    MainFrame instance = MainFrame.getInstance();
    theFile = null;
    while (true) {
      int returnValue =
          JOptionPane.showOptionDialog(instance, this,
          "Save Ontology As...", JOptionPane.PLAIN_MESSAGE,
          JOptionPane.OK_CANCEL_OPTION, MainFrame.getIcon("empty"),
          new String[]{"OK", "Cancel"}, "OK");
      if (returnValue == JOptionPane.OK_OPTION) {
        String name = saveFileField.getText();
        if ( // name.isEmpty() -- does not work with java 1.5
             name.length() == 0
           ) {
          JOptionPane.showMessageDialog(instance,
              new JLabel("Please specify a file for saving the ontology"),
              "Error",
              JOptionPane.ERROR_MESSAGE);
          continue;
        } else {
          theFile = new File(name);
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Get the file specified by the user.
   * @return a File object representing the file chosen by the user to save
   * the ontology to.
   */
  public File getFile() {
    return theFile;
  }

  /**
   * Get the ontology format specified by the user
   * @return a OntologyFormat for the file to be saved
   */
  public OConstants.OntologyFormat getFormat() {
    int selected = formatChooser.getSelectedIndex();
    return OntologyFormat.values()[selected];
  }

  /**
   * Get the flag indicating if imported ontologies should be included
   * in the saved file.
   * @return a boolean indicating if imported ontologies should be saved too
   */
  public boolean getIncludeImports() {
    return includeImports.isSelected();
  }
}
