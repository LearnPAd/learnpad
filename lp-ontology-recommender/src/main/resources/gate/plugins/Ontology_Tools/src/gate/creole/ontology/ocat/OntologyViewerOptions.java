/*
 *  OntologyViewerOptions.java
 *
 *  Niraj Aswani, 12/March/07
 *
 *  $Id: OntologyViewerOptions.html,v 1.0 2007/03/12 16:13:01 niraj Exp $
 */
package gate.creole.ontology.ocat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import gate.*;
import java.util.*;
import gate.event.DocumentEvent;
import gate.event.DocumentListener;
import gate.gui.MainFrame;
import gate.util.BomStrippingInputStreamReader;
import gate.util.GateRuntimeException;

/**
 * Description: This class Provides options window to set the options for
 * Ontology Viewer
 *
 * @author Niraj Aswani
 * @version 1.0
 */
public class OntologyViewerOptions implements DocumentListener {

  private JScrollPane scroller;

  private JPanel optionPanel;

  /**
   * Indicates whether to select all subclasses when a super class is selected
   * or not.
   */
  private JCheckBox childFeatureCB;

  /**
   * Indicates whether to confirm the deletion of an annotation with user or
   * not.
   */
  private JCheckBox deleteConfirmationCB;

  /**
   * selected text as annotation property? user or not.
   */
  private JCheckBox selectedTextAsPropertyValue;

  private JTextField propertyName;

  /**
   * Show annonymous classes
   */
  private JCheckBox showAnonymousClassesCB;

  /**
   * Indicates whether to be case-sensitive or not when annotating text in the
   * add All option
   */
  private JCheckBox addAllFeatureCaseSensitiveCB;

  /**
   * Indicates whether to use the provided ontology class filter file or not. If
   * yes, it disables all the classes mentioned in the filter file from the ocat
   * tree.
   */
  private JRadioButton classesToHideRB;

  /**
   * Filter File URL
   */
  private URL classesToHideFileURL;

  /**
   * Text box to display the path of the selected filter file.
   */
  private JTextField classesToHideFilePathTF;

  /**
   * Button that allows selecting the filter file.
   */
  private JButton browseClassesToHideFileButton;

  /**
   * Button that allows saving the filter file.
   */
  private JButton saveClassesToHideFileButton;

  /**
   * Indicates whether to use the provided ontology class filter file or not. If
   * yes, it disables all the classes mentioned in the filter file from the ocat
   * tree.
   */
  private JRadioButton classesToShowRB;

  private JRadioButton disableFilteringRB;

  /**
   * Filter File URL
   */
  private URL classesToShowFileURL;

  /**
   * Text box to display the path of the selected filter file.
   */
  private JTextField classesToShowFilePathTF;

  /**
   * Button that allows selecting the filter file.
   */
  private JButton browseClassesToShowFileButton;

  /**
   * Button that allows saving the filter file.
   */
  private JButton saveClassesToShowFileButton;

  /**
   * Default AnnotationSEt or otherAnnotationSets
   */
  private JRadioButton otherAS, defaultAS;

  /**
   * All annotations are listed under this annotationSet comboBox
   */
  private JComboBox annotationSetsNamesCB;

  /**
   * Default AnnotationType, which is Mention and other available
   * annotationtypes
   */
  private JRadioButton otherAT, mentionAT;

  /**
   * Default classURI feature which is "class". User can specify other than this
   */
  private JRadioButton otherClassURIFeatureName, classURIFeatureName;

  /**
   * Default instanceURI feature which is "inst". User can specify other than
   * this
   */
  private JRadioButton otherInstanceURIFeatureName, instanceURIFeatureName;

  /** Class and Instance URI Text Fields */
  private JTextField otherClassURITF, otherInstanceURITF;

  /**
   * All available annotation types, with a capability of adding new, are listed
   * under this annotationTypesComboBox
   */
  private JComboBox annotationTypesCB;

  /**
   * Instance of the main ontologyTreePanel
   */
  private OntologyTreePanel ontologyTreePanel;

  /**
   * List of ontology classes to be filtered out.
   */
  protected HashSet<String> classesToHide;

  /**
   * List of ontology classes to be filtered out.
   */
  protected HashSet<String> classesToShow;

  /**
   * Instead of a null value, we specify the defaultAnnotationSetName with some
   * strange string
   */
  public static final String DEFAULT_ANNOTATION_SET = "00#Default#00",
    DEFAULT_ANNOTATION_TYPE = "Mention",
    DEFAULT_CLASS_URI_FEATURE_NAME = "class",
    DEFAULT_INSTANCE_URI_FEATURE_NAME = "inst";

  /**
   * Currently selected annotationSetName
   */
  protected String selectedAnnotationSetName = DEFAULT_ANNOTATION_SET;

  /**
   * Currently selected annotation type
   */
  protected String selectedAnnotationType = DEFAULT_ANNOTATION_TYPE;

  private boolean readClassesToHideFile = false;
  private boolean readClassesToShowFile = false;

  /**
   * Constructor
   *
   * @param ontologyTreePanel
   */
  public OntologyViewerOptions(OntologyTreePanel ontoTree) {
    this.ontologyTreePanel = ontoTree;
    ontoTree.ontoViewer.getDocument().addDocumentListener(this);
    initGUI();
  }

  /**
   * Releases all resources
   */
  public void cleanup() {
    ontologyTreePanel.ontoViewer.getDocument().removeDocumentListener(this);
  }

  /** Returns the currently selected Annotation Set */
  public String getSelectedAnnotationSetName() {
    if(otherAS.isEnabled() && otherAS.isSelected()) {
      selectedAnnotationSetName =
        (String)annotationSetsNamesCB.getSelectedItem();
    }
    else if(defaultAS.isEnabled()) {
      selectedAnnotationSetName = DEFAULT_ANNOTATION_SET;
    }
    return selectedAnnotationSetName;
  }

  /**
   * The method disables the graphical selection of selectedAnnotationSetName
   * and will allow user to provide the annotationSetName explicitly
   *
   * @param annotationSetName
   */
  public void disableAnnotationSetSelection(String annotationSetName) {
    selectedAnnotationSetName = annotationSetName;
    // making sure the selectedAnnotationSetName exists, if not, it will
    // be created
    ontologyTreePanel.ontoViewer.getDocument().getAnnotations(
      selectedAnnotationSetName);

    otherAS.setEnabled(false);
    annotationSetsNamesCB.setEnabled(false);
    defaultAS.setEnabled(false);
  }

  /**
   * This will reenable the graphical support for selecting
   * annotationSetsNamesCB
   *
   * @param annotationSetName
   */
  public void enabledAnnotationSetSelection() {
    otherAS.setEnabled(true);
    annotationSetsNamesCB.setEnabled(true);
    defaultAS.setEnabled(true);
  }

  /** Returns the currently selected Annotation Type */
  public String getSelectedAnnotationType() {
    if(otherAT.isSelected()) {
      selectedAnnotationType = (String)annotationTypesCB.getSelectedItem();
    }
    else {
      selectedAnnotationType = DEFAULT_ANNOTATION_TYPE;
    }

    return selectedAnnotationType;
  }

  /** Initialize the GUI */
  private void initGUI() {
    classesToHide = new HashSet<String>();
    classesToShow = new HashSet<String>();

    optionPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.NONE;
    c.gridx = 0;
    c.anchor = GridBagConstraints.FIRST_LINE_START;

    childFeatureCB = new JCheckBox("Disable child feature");
    selectedTextAsPropertyValue =
      new JCheckBox("Selected Text As Property Value?");
    propertyName = new JTextField(
            "http://www.w3.org/2000/01/rdf-schema#label", 15);
    OntologyViewerOptionsActions ovoa = new OntologyViewerOptionsActions();

    deleteConfirmationCB = new JCheckBox("Enable confirm deletion");
    showAnonymousClassesCB = new JCheckBox("Show Anonymous classes");
    addAllFeatureCaseSensitiveCB =
      new JCheckBox("Case sensitive \"Annotate All\" feature");
    classesToHideRB = new JRadioButton("Classes to ommit");
    classesToHideFilePathTF = new JTextField(15);
    browseClassesToHideFileButton = new JButton("Browse");
    saveClassesToHideFileButton = new JButton("Save");
    disableFilteringRB = new JRadioButton("Disable filtering");
    classesToShowRB = new JRadioButton("Classes to show");
    classesToShowFilePathTF = new JTextField(15);
    browseClassesToShowFileButton = new JButton("Browse");
    saveClassesToShowFileButton = new JButton("Save");
    annotationSetsNamesCB = new JComboBox();
    annotationTypesCB = new JComboBox();
    defaultAS = new JRadioButton();
    otherAS = new JRadioButton();
    mentionAT = new JRadioButton();
    otherAT = new JRadioButton();
    classURIFeatureName = new JRadioButton();
    otherClassURIFeatureName = new JRadioButton();
    otherClassURITF = new JTextField(12);
    instanceURIFeatureName = new JRadioButton();
    otherInstanceURIFeatureName = new JRadioButton();
    otherInstanceURITF = new JTextField(12);

    showAnonymousClassesCB.setSelected(false);
    addAllFeatureCaseSensitiveCB.setSelected(true);

    JPanel classesToOmmitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    classesToOmmitPanel.add(new JLabel("    File:"));
    classesToOmmitPanel.add(classesToHideFilePathTF);
    classesToOmmitPanel.add(browseClassesToHideFileButton);
    classesToOmmitPanel.add(saveClassesToHideFileButton);

    JPanel classesToShowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    classesToShowPanel.add(new JLabel("    File:"));
    classesToShowPanel.add(classesToShowFilePathTF);
    classesToShowPanel.add(browseClassesToShowFileButton);
    classesToShowPanel.add(saveClassesToShowFileButton);

    ButtonGroup bg8 = new ButtonGroup();
    bg8.add(classesToShowRB);
    bg8.add(classesToHideRB);
    bg8.add(disableFilteringRB);
    disableFilteringRB.setSelected(true);

    // lets find out all the annotations
    Document document = ontologyTreePanel.ontoViewer.getDocument();

    Map annotSetMap = document.getNamedAnnotationSets();
    if(annotSetMap != null) {
      java.util.List setNames = new ArrayList(annotSetMap.keySet());
      if(setNames != null) {
        Collections.sort(setNames);
        Iterator setsIter = setNames.iterator();
        while(setsIter.hasNext()) {
          String setName = (String)setsIter.next();
          annotationSetsNamesCB.addItem(setName);
          ontologyTreePanel.ontoViewer.getDocument().getAnnotations(setName)
            .addAnnotationSetListener(ontologyTreePanel.ontoViewer);
        }
      }
    }
    annotationSetsNamesCB.setEnabled(false);
    annotationSetsNamesCB.setEditable(true);


    Set types = document.getAnnotations().getAllTypes();
    if(types != null) {
      Iterator iter = types.iterator();
      while(iter.hasNext()) {
        annotationTypesCB.addItem((String)iter.next());
      }
    }

    annotationTypesCB.setEnabled(false);
    annotationTypesCB.setEditable(true);
    defaultAS.setSelected(true);
    mentionAT.setSelected(true);
    classURIFeatureName.setSelected(true);
    instanceURIFeatureName.setSelected(true);


    ButtonGroup group = new ButtonGroup();
    group.add(defaultAS);
    group.add(otherAS);

    ButtonGroup group1 = new ButtonGroup();
    group1.add(mentionAT);
    group1.add(otherAT);

    ButtonGroup group2 = new ButtonGroup();
    group2.add(classURIFeatureName);
    group2.add(otherClassURIFeatureName);

    ButtonGroup group3 = new ButtonGroup();
    group3.add(instanceURIFeatureName);
    group3.add(otherInstanceURIFeatureName);

    showAnonymousClassesCB.addActionListener(ovoa);
    classesToHideRB.addActionListener(ovoa);
    browseClassesToHideFileButton.addActionListener(ovoa);
    saveClassesToHideFileButton.addActionListener(ovoa);
    classesToShowRB.addActionListener(ovoa);
    browseClassesToShowFileButton.addActionListener(ovoa);
    saveClassesToShowFileButton.addActionListener(ovoa);
    annotationSetsNamesCB.addActionListener(ovoa);
    annotationTypesCB.addActionListener(ovoa);
    defaultAS.addActionListener(ovoa);
    otherAS.addActionListener(ovoa);
    mentionAT.addActionListener(ovoa);
    otherAT.addActionListener(ovoa);
    classURIFeatureName.addActionListener(ovoa);
    otherClassURIFeatureName.addActionListener(ovoa);
    otherClassURITF.addActionListener(ovoa);
    instanceURIFeatureName.addActionListener(ovoa);
    otherInstanceURITF.addActionListener(ovoa);
    otherInstanceURIFeatureName.addActionListener(ovoa);

    c.gridwidth = 5;
    c.gridy = 0; optionPanel.add(showAnonymousClassesCB, c);
    c.gridy = 1; optionPanel.add(childFeatureCB, c);
    c.gridy = 2; optionPanel.add(deleteConfirmationCB, c);
    c.gridy = 3; optionPanel.add(addAllFeatureCaseSensitiveCB, c);
    c.gridy = 4; optionPanel.add(disableFilteringRB, c);
    c.gridy = 5; optionPanel.add(classesToHideRB, c);
    c.gridy = 6; optionPanel.add(classesToOmmitPanel, c);
    c.gridy = 7; optionPanel.add(classesToShowRB, c);
    c.gridy = 8; optionPanel.add(classesToShowPanel, c);
    c.gridy = 9; optionPanel.add(selectedTextAsPropertyValue, c);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 10;
    optionPanel.add(new JLabel("Annotation property: "), c);
    c.gridx = 1;
    c.gridwidth = 4;
    optionPanel.add(propertyName, c);
    c.gridwidth = 1;
    c.gridy = 11;
    c.gridx = 0;
    optionPanel.add(new JLabel("Annotation set: "), c);
    c.gridx = 1;
    optionPanel.add(defaultAS, c);
    c.gridx = 2;
    optionPanel.add(new JLabel("Default"), c);
    c.gridx = 3;
    optionPanel.add(otherAS, c);
    c.gridx = 4;
    optionPanel.add(annotationSetsNamesCB, c);
    c.gridy = 12;
    c.gridx = 0;
    optionPanel.add(new JLabel("Annotation type: "), c);
    c.gridx = 1;
    optionPanel.add(mentionAT, c);
    c.gridx = 2;
    optionPanel.add(new JLabel("Mention"), c);
    c.gridx = 3;
    optionPanel.add(otherAT, c);
    c.gridx = 4;
    optionPanel.add(annotationTypesCB, c);


    c.gridy = 13;
    c.gridx = 0;
    optionPanel.add(new JLabel("Class URI feature name: "), c);
    c.gridx = 1;
    optionPanel.add(classURIFeatureName, c);
    c.gridx = 2;
    optionPanel.add(new JLabel(DEFAULT_CLASS_URI_FEATURE_NAME), c);
    c.gridx = 3;
    optionPanel.add(otherClassURIFeatureName, c);
    c.gridx = 4;
    optionPanel.add(otherClassURITF, c);

    c.gridy = 14;
    c.gridx = 0;
    optionPanel.add(new JLabel("Instance URI feature name: "), c);
    c.gridx = 1;
    optionPanel.add(instanceURIFeatureName, c);
    c.gridx = 2;
    optionPanel.add(new JLabel(DEFAULT_INSTANCE_URI_FEATURE_NAME), c);
    c.gridx = 3;
    optionPanel.add(otherInstanceURIFeatureName, c);
    c.gridx = 4;
    optionPanel.add(otherInstanceURITF, c);

    c.fill = GridBagConstraints.BOTH;
    c.weighty = 1.0;
    c.gridwidth = 5;
    c.gridx = 0;
    c.gridy = 15; optionPanel.add(Box.createVerticalGlue(), c);

    scroller = new JScrollPane(optionPanel);
  }

  /**
   * Returns the panel for ontoOption Panel
   *
   * @return
   */
  public Component getGUI() {
    return scroller;
  }

  /**
   * Inner class that implements the actions for various options
   *
   * @author Niraj Aswani
   * @version 1.0
   */
  private class OntologyViewerOptionsActions extends AbstractAction {

    /**
     * Serial version ID
     */
    private static final long serialVersionUID = 3906926759864643636L;

    public void actionPerformed(ActionEvent ae) {

      if(ae.getSource() == otherAS) {
        annotationSetsNamesCB.setEnabled(true);
        if(annotationSetsNamesCB.getSelectedItem() == null
          && annotationSetsNamesCB.getItemCount() > 0) {
          annotationSetsNamesCB.setSelectedIndex(0);
          return;
        }
        else if(annotationSetsNamesCB.getItemCount() == 0) {
          ontologyTreePanel.ontoTreeListener.removeHighlights();
          return;
        }
        else {
          annotationSetsNamesCB.setSelectedIndex(annotationSetsNamesCB
            .getSelectedIndex());
          return;
        }
      }
      else if(ae.getSource() == classURIFeatureName
        || ae.getSource() == otherClassURIFeatureName
        || ae.getSource() == otherClassURITF
        || ae.getSource() == instanceURIFeatureName
        || ae.getSource() == otherInstanceURIFeatureName
        || ae.getSource() == otherInstanceURITF
        || ae.getSource() == showAnonymousClassesCB) {
        ontologyTreePanel.ontoViewer.ontologyReset(ontologyTreePanel
          .getCurrentOntology());
      }
      else if(ae.getSource() == annotationSetsNamesCB) {

        // see if user has entered a new Item
        String item = (String)annotationSetsNamesCB.getSelectedItem();

        // we need to change the annotationTypesCBcomp values as well
        annotationTypesCB.removeAllItems();
        Set types =
          ontologyTreePanel.ontoViewer.getDocument().getAnnotations(
            (String)item).getAllTypes();
        if(types != null) {
          Iterator iter = types.iterator();
          while(iter.hasNext()) {
            annotationTypesCB.addItem((String)iter.next());
          }
        }

        annotationTypesCB.updateUI();
        if(mentionAT.isSelected()) {
          ontologyTreePanel.ontoTreeListener.refreshHighlights();
        }
        else {
          if(annotationTypesCB.getSelectedItem() == null
            && annotationTypesCB.getItemCount() > 0) {
            annotationTypesCB.setSelectedIndex(0);
            return;
          }
          else if(annotationTypesCB.getItemCount() == 0) {
            ontologyTreePanel.ontoTreeListener.removeHighlights();
            return;
          }
          else {
            annotationTypesCB.setSelectedIndex(annotationTypesCB
              .getSelectedIndex());
            return;
          }
        }
      }
      else if(ae.getSource() == defaultAS) {

        annotationSetsNamesCB.setEnabled(false);

        // we need to change the annotationTypesCB values as well
        annotationTypesCB.removeAllItems();
        Set types =
          ontologyTreePanel.ontoViewer.getDocument().getAnnotations()
            .getAllTypes();

        if(types != null) {
          Iterator iter = types.iterator();
          while(iter.hasNext()) {
            annotationTypesCB.addItem((String)iter.next());
          }
        }
        annotationTypesCB.updateUI();

        if(mentionAT.isSelected()) {
          ontologyTreePanel.ontoTreeListener.refreshHighlights();
        }
        else {
          if(annotationTypesCB.getSelectedItem() == null
            && annotationTypesCB.getItemCount() > 0) {
            annotationTypesCB.setSelectedIndex(0);
            return;
          }
          else if(annotationTypesCB.getItemCount() == 0) {
            ontologyTreePanel.ontoTreeListener.removeHighlights();
            return;
          }
          else {
            annotationTypesCB.setSelectedIndex(annotationTypesCB
              .getSelectedIndex());
            return;
          }
        }
      }
      else if(ae.getSource() == otherAT) {

        annotationTypesCB.setEnabled(otherAT.isSelected());
        if(annotationTypesCB.getSelectedItem() == null
          && annotationTypesCB.getItemCount() > 0) {
          annotationTypesCB.setSelectedIndex(0);
          return;
        }
        else if(annotationTypesCB.getItemCount() == 0) {
          ontologyTreePanel.ontoTreeListener.removeHighlights();
          return;
        }
        else {
          annotationTypesCB.setSelectedIndex(annotationTypesCB
            .getSelectedIndex());
          return;
        }

      }
      else if(ae.getSource() == mentionAT) {

        annotationTypesCB.setEnabled(false);
        ontologyTreePanel.ontoTreeListener.refreshHighlights();
        return;

      }
      else if(ae.getSource() == annotationTypesCB) {

        // see if user has entered a new Item
        String item = (String)annotationTypesCB.getSelectedItem();
        if(item == null) {
          if(annotationTypesCB.getItemCount() > 0) {
            annotationTypesCB.setSelectedIndex(0);
            return;
          }
          return;
        }

        for(int i = 0; i < annotationTypesCB.getItemCount(); i++) {
          if(item.equals((String)annotationTypesCB.getItemAt(i))) {
            annotationTypesCB.setSelectedIndex(i);
            ontologyTreePanel.ontoTreeListener.refreshHighlights();
            return;
          }
        }

        // here means a new item is added
        annotationTypesCB.addItem(item);
        // annotationTypesCB.setSelectedItem(item);
        ontologyTreePanel.ontoTreeListener.refreshHighlights();
        return;
      }
      else if(ae.getSource() == browseClassesToHideFileButton) {
        // open the file dialogue
        JFileChooser fileChooser = MainFrame.getFileChooser();
        int answer = fileChooser.showOpenDialog(MainFrame.getInstance());
        if(answer == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          if(selectedFile == null) {
            return;
          }
          else {
            try {
              String newURL = selectedFile.toURI().toURL().toString();
              if(!newURL.equalsIgnoreCase(classesToHideFilePathTF.getText()
                .trim())) {
                readClassesToHideFile = true;
              }
              else {
                readClassesToHideFile = false;
              }

              classesToHideFilePathTF.setText(newURL);
              classesToHideFileURL = selectedFile.toURI().toURL();
              if(isClassesToHideFilterOn()) {
                updateClassesToHide();
              }
            }
            catch(MalformedURLException me) {
              JOptionPane.showMessageDialog(MainFrame.getInstance(),
                "Invalid URL");
              return;
            }
          }
        }
      }
      else if(ae.getSource() == saveClassesToHideFileButton) {
        // open the file dialogue
        JFileChooser fileChooser = MainFrame.getFileChooser();
        int answer = fileChooser.showSaveDialog(MainFrame.getInstance());
        if(answer == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          if(selectedFile == null) {
            return;
          }
          else {
            try {

              BufferedWriter bw =
                new BufferedWriter(new FileWriter(selectedFile));
              for(String s : classesToHide) {
                bw.write(s);
                bw.newLine();
              }
              bw.flush();
              bw.close();
            }
            catch(IOException ioe) {
              JOptionPane.showMessageDialog(MainFrame.getInstance(), ioe
                .getMessage());
              throw new GateRuntimeException(ioe);
            }
          }
        }

      }
      else if(ae.getSource() == classesToHideRB) {
        updateClassesToHide();
      }
      else if(ae.getSource() == browseClassesToShowFileButton) {
        // open the file dialogue
        JFileChooser fileChooser = MainFrame.getFileChooser();
        int answer = fileChooser.showOpenDialog(MainFrame.getInstance());
        if(answer == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          if(selectedFile == null) {
            return;
          }
          else {
            try {
              String newURL = selectedFile.toURI().toURL().toString();
              if(!newURL.equalsIgnoreCase(classesToShowFilePathTF.getText()
                .trim())) {
                readClassesToShowFile = true;
              }
              else {
                readClassesToShowFile = false;
              }

              classesToShowFilePathTF.setText(newURL);
              classesToShowFileURL = selectedFile.toURI().toURL();
              if(isClassesToShowFilterOn()) {
                updateClassesToShow();
              }
            }
            catch(MalformedURLException me) {
              JOptionPane.showMessageDialog(MainFrame.getInstance(),
                "Invalid URL");
              return;
            }
          }
        }
      }
      else if(ae.getSource() == saveClassesToShowFileButton) {
        // open the file dialogue
        JFileChooser fileChooser = MainFrame.getFileChooser();
        int answer = fileChooser.showSaveDialog(MainFrame.getInstance());
        if(answer == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          if(selectedFile == null) {
            return;
          }
          else {
            try {

              BufferedWriter bw =
                new BufferedWriter(new FileWriter(selectedFile));
              for(String s : classesToShow) {
                bw.write(s);
                bw.newLine();
              }
              bw.flush();
              bw.close();
            }
            catch(IOException ioe) {
              JOptionPane.showMessageDialog(MainFrame.getInstance(), ioe
                .getMessage());
              throw new GateRuntimeException(ioe);
            }
          }
        }

      }
      else if(ae.getSource() == classesToShowRB) {
        updateClassesToShow();
      }
    }
  }

  public boolean isClassesToHideFilterOn() {
    return classesToHideRB.isSelected();
  }

  public String getSelectedClassURIFeatureName() {
    if(classURIFeatureName.isSelected()) {
      return DEFAULT_CLASS_URI_FEATURE_NAME;
    }
    else {
      return otherClassURITF.getText().trim();
    }
  }

  public String getSelectedInstanceURIFeatureName() {
    if(instanceURIFeatureName.isSelected()) {
      return DEFAULT_INSTANCE_URI_FEATURE_NAME;
    }
    else {
      return otherInstanceURITF.getText().trim();
    }
  }

  public boolean isClassesToShowFilterOn() {
    return classesToShowRB.isSelected();
  }

  private void updateClassesToHide() {
    try {
      if(classesToHideFileURL == null || !readClassesToHideFile) return;
      classesToHide.clear();
      BufferedReader br =
        new BomStrippingInputStreamReader(classesToHideFileURL
          .openStream());
      String line = br.readLine();
      while(line != null) {
        classesToHide.add(line.trim());
        line = br.readLine();
      }
      br.close();
      ontologyTreePanel.ontoTreeListener.refreshHighlights();
      return;
    }
    catch(IOException ioe) {
      throw new GateRuntimeException(ioe);
    }
  }

  private void updateClassesToShow() {
    try {
      if(classesToShowFileURL == null || !readClassesToShowFile) return;
      classesToShow.clear();
      BufferedReader br =
        new BomStrippingInputStreamReader(classesToShowFileURL
          .openStream());
      String line = br.readLine();
      while(line != null) {
        classesToShow.add(line.trim());
        line = br.readLine();
      }
      br.close();
      ontologyTreePanel.ontoTreeListener.refreshHighlights();
      return;
    }
    catch(IOException ioe) {
      throw new GateRuntimeException(ioe);
    }
  }

  /**
   * Use this method to switch on and off the filter.
   *
   * @param onOff
   */
  public void setClassesToHideOn(boolean onOff) {
    if(classesToHideRB.isSelected() != onOff) {
      classesToHideRB.setSelected(onOff);
    }
    updateClassesToHide();
  }

  /**
   * Use this method to switch on and off the filter.
   *
   * @param onOff
   */
  public void setClassesToShowOn(boolean onOff) {
    if(classesToShowRB.isSelected() != onOff) {
      classesToShowRB.setSelected(onOff);
    }
    updateClassesToShow();
  }

  /** Returns if Child Feature is set to ON/OFF */
  public boolean isChildFeatureDisabled() {
    return childFeatureCB.isSelected();
  }

  /** Returns if Child Feature is set to ON/OFF */
  public boolean getDeleteConfirmation() {
    return deleteConfirmationCB.isSelected();
  }

  /**
   * Returns if case sensitive option is set to ON/OFF
   *
   * @return
   */
  public boolean isAddAllOptionCaseSensitive() {
    return addAllFeatureCaseSensitiveCB.isSelected();
  }

  public void addToClassesToHide(HashSet<String> list) {
    if(classesToHide == null) classesToHide = new HashSet<String>();
    classesToHide.addAll(list);
    ontologyTreePanel.ontoTreeListener.refreshHighlights();
  }

  public void removeFromClassesToHide(HashSet<String> list) {
    if(classesToHide == null) classesToHide = new HashSet<String>();
    classesToHide.removeAll(list);
    ontologyTreePanel.ontoTreeListener.refreshHighlights();
  }

  public void addToClassesToShow(HashSet<String> list) {
    if(classesToShow == null) classesToShow = new HashSet<String>();
    classesToShow.addAll(list);
    ontologyTreePanel.ontoTreeListener.refreshHighlights();
  }

  public void removeFromClassesToShow(HashSet<String> list) {
    if(classesToShow == null) classesToShow = new HashSet<String>();
    classesToShow.removeAll(list);
    ontologyTreePanel.ontoTreeListener.refreshHighlights();
  }

  // DocumentListener Methods
  public void annotationSetAdded(DocumentEvent de) {
    // we need to update our annotationSetsNamesCB List
    String getSelected = (String)annotationSetsNamesCB.getSelectedItem();
    annotationSetsNamesCB.addItem(de.getAnnotationSetName());
    ontologyTreePanel.ontoViewer.getDocument().getAnnotations(
      de.getAnnotationSetName()).addAnnotationSetListener(
      ontologyTreePanel.ontoViewer);
    ;
    annotationSetsNamesCB.setSelectedItem(getSelected);
  }

  public void contentEdited(DocumentEvent de) {
    // ignore
  }

  /**
   * This methods implements the actions when any selectedAnnotationSetName is
   * removed from
   *
   * @param de
   */
  public void annotationSetRemoved(DocumentEvent de) {
    // String getSelected = (String)annotationSetsNamesCB.getSelectedItem();
    annotationSetsNamesCB.removeItem(de.getAnnotationSetName());
    // Note: still removing the hook (listener) is remaining and we need
    // to
    // sort it out
  }

  /**
   * Gets the URL of the filter file being used.
   *
   * @return
   */
  public URL getClassesToHideFileURL() {
    return classesToHideFileURL;
  }

  /**
   * Sets the filter file to be used.
   *
   * @param classesToHideFileURL
   */
  public void setClassesToHideFileURL(URL filterFileURL) {
    this.classesToHideFileURL = filterFileURL;
    if(isClassesToHideFilterOn()) {
      updateClassesToHide();
    }
  }

  /**
   * Gets the URL of the filter file being used.
   *
   * @return
   */
  public URL getClassesToShowFileURL() {
    return classesToShowFileURL;
  }

  /**
   * Sets the filter file to be used.
   *
   * @param classesToHideFileURL
   */
  public void setClassesToShowFileURL(URL filterFileURL) {
    this.classesToShowFileURL = filterFileURL;
    if(isClassesToShowFilterOn()) {
      updateClassesToShow();
    }
  }

  /**
   * Gets a set of ontology classes disabled in the OCAT.
   *
   * @return
   */
  public HashSet<String> getClassesToHide() {
    return classesToHide;
  }

  /**
   * Gets a set of ontology classes disabled in the OCAT.
   *
   * @return
   */
  public HashSet<String> getClassesToShow() {
    return classesToShow;
  }

  public String getPropertyName() {
    return selectedTextAsPropertyValue.isSelected() ? propertyName.getText()
      .trim() : null;
  }

  /**
   * This method should be called to specify the ontology classes that should be
   * disabled from the ocat.
   *
   * @param classesToHide
   */
  public void setClassesToHide(HashSet<String> ontologyClassesToFilterOut) {
    // ok here we need to create a temporary file and add these classes
    // in it
    if(ontologyClassesToFilterOut == null) {
      ontologyClassesToFilterOut = new HashSet<String>();
    }

    try {
      File newFile = File.createTempFile("classesToHide", "tmp");
      BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
      for(String aClassName : ontologyClassesToFilterOut) {
        bw.write(aClassName);
        bw.newLine();
      }
      bw.flush();
      bw.close();
      readClassesToHideFile = true;
      classesToHideFilePathTF.setText(newFile.toURI().toURL().toString());
      classesToHideFileURL = newFile.toURI().toURL();
      setClassesToHideOn(true);
    }
    catch(IOException ioe) {
      throw new GateRuntimeException(
        "Not able to save the classes in a temporary file", ioe);
    }
  }

  /**
   * This method should be called to specify the ontology classes that should be
   * disabled from the ocat.
   *
   * @param classesToHide
   */
  public void setClassesToShow(HashSet<String> ontologyClassesToShow) {
    // ok here we need to create a temporary file and add these classes
    // in it
    if(ontologyClassesToShow == null) {
      ontologyClassesToShow = new HashSet<String>();
    }

    try {
      File newFile = File.createTempFile("classesToShow", "tmp");
      BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
      for(String aClassName : ontologyClassesToShow) {
        bw.write(aClassName);
        bw.newLine();
      }
      bw.flush();
      bw.close();
      readClassesToShowFile = true;
      classesToShowFilePathTF.setText(newFile.toURI().toURL().toString());
      classesToShowFileURL = newFile.toURI().toURL();
      setClassesToShowOn(true);
    }
    catch(IOException ioe) {
      throw new GateRuntimeException(
        "Not able to save the classes in a temporary file", ioe);
    }
  }

  /**
   * Disable Filtering.
   *
   * @param shouldDisable
   */
  public void disableFiltering(boolean shouldDisable) {
    if(disableFilteringRB.isSelected() != shouldDisable) {
      disableFilteringRB.setSelected(shouldDisable);
      if(shouldDisable) {
        ontologyTreePanel.ontoTreeListener.refreshHighlights();
      }
    }
  }

  public boolean showAnonymousClasses() {
    return showAnonymousClassesCB.isSelected();
  }

  public boolean shouldShow(String aResourceName) {
    if(disableFilteringRB.isSelected()) return true;

    if(classesToHideRB.isSelected() && classesToHide != null)
      return !classesToHide.contains(aResourceName);

    else if(classesToShow != null)
      return classesToShow.contains(aResourceName);

    return true;
  }

}
