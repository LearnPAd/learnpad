/*
 *  CheckRenderer.java
 *
 *  Niraj Aswani, 12/March/07
 *
 *  $Id: CheckRenderer.html,v 1.0 2007/03/12 16:13:01 niraj Exp $
 */
package gate.creole.ontology.ocat;

import gate.creole.ontology.OClass;
import gate.creole.ontology.OInstance;
import gate.gui.MainFrame;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;

import com.ontotext.gate.vr.ClassNode;
import com.ontotext.gate.vr.IFolder;

/**
 * Description: This class provides the renderer for the Ontology Tree Nodes.
 * 
 * @author Niraj Aswani
 * @version 1.0
 */
public class CheckRenderer extends JPanel implements TreeCellRenderer {

  /**
   * Serial Version ID
   */
  private static final long serialVersionUID = 3257004371551204912L;

  /**
   * Allows user to select/deselect class in the ontology Tree
   */
  private JCheckBox check;

  /**
   * Class label is shown using this label
   */
  private JLabel label;

  /**
   * ICon label
   */
  private JLabel iconLabel;

  /**
   * Label Panel
   */
  private JPanel iconPanel, labelPanel;

  /**
   * The instance of ontologyTreePanel
   */
  private OntologyTreePanel ontologyTreePanel;

  /**
   * Constructor
   * 
   * @param owner
   */
  public CheckRenderer(OntologyTreePanel owner) {
    this.ontologyTreePanel = owner;
    check = new JCheckBox();
    check.setBackground(Color.white);
    label = new JLabel();
    iconLabel = new JLabel();

    iconPanel = new JPanel(new BorderLayout(5, 10));
    ((BorderLayout)iconPanel.getLayout()).setHgap(0);
    iconPanel.setOpaque(true);
    iconPanel.add(check, BorderLayout.WEST);
    iconPanel.add(iconLabel, BorderLayout.EAST);

    labelPanel = new JPanel(new BorderLayout(5, 10));
    ((BorderLayout)labelPanel.getLayout()).setHgap(0);
    // labelPanel.setOpaque(true);
    labelPanel.add(label);

    setLayout(new BorderLayout(5, 10));
    ((BorderLayout)getLayout()).setHgap(1);
    add(iconPanel, BorderLayout.WEST);
    add(labelPanel, BorderLayout.EAST);
  }

  /**
   * Renderer method
   */
  public Component getTreeCellRendererComponent(JTree tree, Object value,
    boolean isSelected, boolean expanded, boolean leaf, int row,
    boolean hasFocus) {

    Object userObject = value;
    if(!(userObject instanceof IFolder)) {
      label.setBackground(Color.white);
      return this;
    }

    javax.swing.Icon icon = null;
    ClassNode node = (ClassNode)userObject;
    String conceptName = node.toString();

    if(row == 0) {
      // this is the ontology name
      check.setVisible(false);
      iconLabel.setVisible(false);
      label.setText(conceptName);
      labelPanel.setBackground(Color.white);
      iconPanel.setBackground(Color.WHITE);
      return this;
    }
    else {
      check.setVisible(true);
      iconLabel.setVisible(true);
    }

    Boolean bValue =
      ontologyTreePanel.currentOResource2IsSelectedMap.get(conceptName);
    if(bValue == null) {
      bValue = new Boolean(false);
      ontologyTreePanel.currentOResource2IsSelectedMap.put(conceptName, bValue);
    }

    // if node should be selected
    boolean selected = bValue.booleanValue();

    check.setSelected(selected);
    if(node.getSource() instanceof OClass) {
      iconLabel.setIcon(MainFrame.getIcon("ontology-class"));
    }
    else if(node.getSource() instanceof OInstance) {
      iconLabel.setIcon(MainFrame.getIcon("ontology-instance"));
    }
    else {
      iconLabel.setIcon(null);
    }

    label.setText(conceptName);
    label.setFont(tree.getFont());

    // We assign the automatically generated random colors to the
    // concept,
    // but randomly generation of colors for different classes takes
    // place
    // only once when that ontology is loaded for the first time
    if(ontologyTreePanel.currentOResource2ColorMap.containsKey(conceptName)) {
      Color color =
        (Color)ontologyTreePanel.currentOResource2ColorMap.get(conceptName);
      labelPanel.setBackground(color);
      iconPanel.setBackground(Color.WHITE);
    }
    if(!ontologyTreePanel.ontologyViewerOptions.shouldShow(conceptName)) {
      check.setEnabled(false);
      label.setEnabled(false);
    }
    else {
      check.setEnabled(true);
      label.setEnabled(true);
    }
    return this;
  }
}
