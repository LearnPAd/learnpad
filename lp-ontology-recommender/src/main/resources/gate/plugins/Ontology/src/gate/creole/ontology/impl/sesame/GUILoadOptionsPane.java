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
 *  $Id: GUILoadOptionsPane.java 15334 2012-02-07 13:57:47Z ian_roberts $ 
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
 * Class for creating and showing the load ontology dialog.
 * This class creates the dialog pane for specifying the file name,
 * format, and other relevant options for laoding an ontology from a file.
 *
 * @author Johann Petrak
 */
public class GUILoadOptionsPane extends JPanel {

  static final long serialVersionUID = 1L;
  
  public GUILoadOptionsPane() {
    super(new GridBagLayout());
    init();
  }
  private JComboBox formatChooser;
  private JTextField loadFileField;
  private JTextField baseURIField;
  private JCheckBox asImport;
  private JCheckBox loadImports;
  private JTextField importMappingsFileField;
  private File theFile;
  private File theMappingsFile;

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
    this.add(new JLabel("Load from file:"), constraints);

    loadFileField = new JTextField("", 20);
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 0;
    constraints.gridwidth = 6;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(0, 10, 0, 10);
    this.add(loadFileField, constraints);

    JButton btnChooseFile = new JButton("Select");
    btnChooseFile.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent ae) {
        JFileChooser fileChooser = MainFrame.getFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        fileChooser.setDialogTitle("Specify file to load/import the ontology from");
        fileChooser.setSelectedFiles(null);
        int res = fileChooser.showDialog(null, "Choose");
        if (res == JFileChooser.APPROVE_OPTION) {
          loadFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
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


    // Second row ------------------------

    formatChooser = new JComboBox(OntologyFormat.values());
    formatChooser.setToolTipText("Select the format of the file");
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 1;
    constraints.gridwidth = 4;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.NONE;
    constraints.insets = new Insets(0, 0, 0, 0);
    this.add(new JLabel("File format:"), constraints);

    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 1;
    constraints.gridwidth = 6;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.NORTHWEST;
    constraints.insets = new Insets(5, 10, 10, 10);
    this.add(formatChooser, constraints);

    // Third row: baseURI ------------------------
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 2;
    constraints.gridwidth = 4;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.NONE;
    constraints.insets = new Insets(0, 0, 0, 0);
    this.add(new JLabel("Base URI:"), constraints);

    baseURIField = new JTextField("", 20);
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 2;
    constraints.gridwidth = 6;
    constraints.fill = GridBagConstraints.NORTHWEST;
    constraints.insets = new Insets(0, 10, 0, 10);
    this.add(baseURIField, constraints);
    // TODO: check if handling of default works correctly here!
    //baseURIField.setText("");

    // Fourth row: asImport checkbox ------------------------
    asImport =
        new JCheckBox("");
    asImport.setSelected(false);
    asImport.setToolTipText("Check to load this file as an import");
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 3;
    constraints.gridwidth = 4;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(0, 0, 0, 0);
    this.add(new JLabel("Load as import:"), constraints);

    baseURIField = new JTextField("", 20);
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 3;
    constraints.gridwidth = 6;
    constraints.fill = GridBagConstraints.NORTHWEST;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(0, 10, 0, 10);
    this.add(asImport, constraints);

    // Fifth row: asImport checkbox ------------------------
    loadImports =
        new JCheckBox("");
    loadImports.setSelected(false);
    loadImports.setToolTipText("Check to also load imports specified in the ontology");
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 4;
    constraints.gridwidth = 4;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(0, 0, 0, 0);
    this.add(new JLabel("Automatically load imports:"), constraints);

    baseURIField = new JTextField("", 20);
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 4;
    constraints.gridwidth = 6;
    constraints.fill = GridBagConstraints.NORTHWEST;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(0, 10, 0, 10);
    this.add(loadImports, constraints);
    loadImports.setSelected(true);

    // Fifth row: import mappings file ------------------------
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 5;
    constraints.gridwidth = 4;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.NONE;
    constraints.insets = new Insets(0, 0, 0, 0);
    this.add(new JLabel("File containing import mappings:"), constraints);

    JButton btnChooseMappingsFile = new JButton("Select");
    btnChooseMappingsFile.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent ae) {
        JFileChooser fileChooser = MainFrame.getFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        fileChooser.setDialogTitle("Specify file containing import mappings");
        fileChooser.setSelectedFiles(null);
        int res = fileChooser.showDialog(null, "Choose");
        if (res == JFileChooser.APPROVE_OPTION) {
          importMappingsFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
      }
    });


    importMappingsFileField = new JTextField("", 20);
    importMappingsFileField.setToolTipText("Select a file that contains import mappigns (optional)");
    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 5;
    constraints.gridwidth = 6;
    constraints.fill = GridBagConstraints.NORTHWEST;
    constraints.insets = new Insets(0, 10, 0, 10);
    this.add(importMappingsFileField, constraints);

    constraints = new GridBagConstraints();
    constraints.gridx = GridBagConstraints.RELATIVE;
    constraints.gridy = 5;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.NORTHWEST;
    this.add(btnChooseMappingsFile, constraints);


  }

  /**
   * Show the options pane as a OptionDialog, if the user chooses OK,
   * verify that the file name for loading is not empty and corresponds
   * to an existing file.
   * 
   * @return
   */
  public boolean showOptionDialog() {
    MainFrame instance = MainFrame.getInstance();
    theFile = null;
    while (true) {
      int returnValue =
          JOptionPane.showOptionDialog(instance, this,
          "Load/Import Ontology", JOptionPane.PLAIN_MESSAGE,
          JOptionPane.OK_CANCEL_OPTION, MainFrame.getIcon("empty"),
          new String[]{"OK", "Cancel"}, "OK");
      if (returnValue == JOptionPane.OK_OPTION) {
        String name = loadFileField.getText();
        if ( // name.isEmpty()  -- does not work in java 1.5
             name.length() == 0
           ) {
          JOptionPane.showMessageDialog(instance,
              new JLabel("Please specify a file for loading/importing the ontology"),
              "Error",
              JOptionPane.ERROR_MESSAGE);
          continue;

        }
        theFile = new File(name);
        if (!theFile.exists()) {
          JOptionPane.showMessageDialog(instance,
              new JLabel("The ontology file " + theFile.getAbsolutePath() + " was not found"),
              "Error",
              JOptionPane.ERROR_MESSAGE);
          continue;
        }
        if ( // !importMappingsFileField.getText().isEmpty()  -- does not work in Java 1.5
             importMappingsFileField.getText().length() != 0
           ) {
          theMappingsFile = new File(importMappingsFileField.getText());
          if (!theMappingsFile.exists()) {
            JOptionPane.showMessageDialog(instance,
                new JLabel("The mappings file " + theMappingsFile.getAbsoluteFile() + " was not found"),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            continue;
          }
        } else {
          theMappingsFile = null;
        }
        return true;
      }
      return false;

    }
  }

  /**
   * Return the file specified by the user
   * @return the File objects corresponding to the file chosen by the user
   */
  public File getFile() {
    return theFile;
  }

  /**
   * Return the file format chosen by the user
   * @return the OntologyFormat chosen by the user
   */
  public OConstants.OntologyFormat getFormat() {
    int selected = formatChooser.getSelectedIndex();
    return OntologyFormat.values()[selected];
  }

  /**
   * Return the basURI specified by the user.
   * @return a String that contains the baseURI specified by the user or
   * null if no baseURI was specified.
   */
  public String getBaseURI() {
    return baseURIField.getText();
  }

  /**
   * Return the import mappings file specified by the user.
   * @return a File object that corresponds to the mappings file chosen
   * by the user or null if no file was chosen.
   */
  public File getMappingsFile() {
    return theMappingsFile;
  }

  /**
   * Return a flag that indicates if the loaded file should be loaded as
   * an imported ontology.
   * @return a boolean indicating if the file should be loaded as an imported
   * ontology.
   */
  public boolean getAsImport() {
    return asImport.isSelected();
  }

  /**
   * Return a flag that indicates if imports specified in the loaded ontology
   * should automatically be loaded too.
   * @return a boolean indicating if imports should be resolved
   */
  public boolean getLoadImports() {
    return loadImports.isSelected();
  }

}
