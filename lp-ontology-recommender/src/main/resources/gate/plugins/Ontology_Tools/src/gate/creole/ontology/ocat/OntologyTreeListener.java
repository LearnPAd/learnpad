/*
 *  OntologyTreeListener.java
 *
 *  Niraj Aswani, 12/March/07
 *
 *  $Id: OntologyTreeListener.html,v 1.0 2007/03/12 16:13:01 niraj Exp $
 */
package gate.creole.ontology.ocat;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.creole.ontology.AnnotationProperty;
import gate.creole.ontology.InvalidURIException;
import gate.creole.ontology.Literal;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.OResource;
import gate.creole.ontology.OURI;
import gate.creole.ontology.Ontology;
import gate.gui.MainFrame;
import gate.util.GateRuntimeException;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.tree.TreePath;

import com.ontotext.gate.vr.ClassNode;
import com.ontotext.gate.vr.IFolder;

/**
 * Description: This class listens events on tree selection
 * 
 * @author Niraj Aswani
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class OntologyTreeListener extends MouseAdapter {

  /**
   * Instance of OntologyTreePanel
   */
  private OntologyTreePanel ontologyTreePanel;

  /**
   * Highlighter that is used for highlighting
   */
  protected Highlighter highlighter;

  /**
   * This is where we specify the start/end offsets of the highlighted
   * annotations which is then used for quick lookup to see if the mouse is on
   * one of them
   */
  protected int[] annotationRange;

  /**
   * List of highlighted annotations
   */
  protected ArrayList<Annotation> highlightedAnnotations;

  /**
   * List of highlighted TAGS, which are used for removing highlights
   */
  private ArrayList<Object> highlightedTags;

  /** Constructor */
  public OntologyTreeListener(OntologyTreePanel owner) {
    this.ontologyTreePanel = owner;
    this.highlighter = owner.getHighlighter();
  }

  /**
   * This method is invoked whenever user clicks on one of the classes in the
   * ontology tree
   */
  public void mouseClicked(MouseEvent me) {
    // ok now find out the currently selected node
    int x = me.getX();
    int y = me.getY();
    JTree tree = ontologyTreePanel.currentOntologyTree;
    int row = tree.getRowForLocation(x, y);
    if(row == 0) {
      // do nothing
      return;
    }
    TreePath path = tree.getPathForRow(row);

    // let us expand it if the sibling feature is on
    if(path != null) {
      final ClassNode node = (ClassNode)path.getLastPathComponent();

      // ok let us see if this was a right click
      if(SwingUtilities.isRightMouseButton(me)) {
        // it is a right click
        final Color color =
          ontologyTreePanel.currentOResource2ColorMap.get(node.toString());
        final JPopupMenu popup = new JPopupMenu();
        JMenuItem cancel = new JMenuItem("Close");
        cancel.setToolTipText("Closes this popup");
        JMenuItem changeColor = new JMenuItem("Change Color");
        changeColor.setToolTipText("Changes Color");
        JMenuItem addToClassesToHide =
          new JMenuItem("Hide (Children : "
            + (!ontologyTreePanel.ontologyViewerOptions
              .isChildFeatureDisabled()) + ")");
        JMenuItem removeFromClassesToHide =
          new JMenuItem("Show (Children : "
            + (!ontologyTreePanel.ontologyViewerOptions
              .isChildFeatureDisabled()) + ")");
        JMenuItem addToClassesToShow =
          new JMenuItem("Show (Children : "
            + (!ontologyTreePanel.ontologyViewerOptions
              .isChildFeatureDisabled()) + ")");
        JMenuItem removeFromClassesToShow =
          new JMenuItem("Hide (Children : "
            + (!ontologyTreePanel.ontologyViewerOptions
              .isChildFeatureDisabled()) + ")");

        changeColor.setToolTipText("Adds to the Filter List");

        ToolTipManager.sharedInstance().registerComponent(cancel);
        ToolTipManager.sharedInstance().registerComponent(changeColor);
        ToolTipManager.sharedInstance().registerComponent(addToClassesToHide);

        popup.add(new JLabel(node.toString()));
        popup.addSeparator();
        popup.add(changeColor);
        if(ontologyTreePanel.ontologyViewerOptions.isClassesToHideFilterOn()) {
          if(!ontologyTreePanel.ontologyViewerOptions.classesToHide
            .contains(node.toString())) {
            popup.add(addToClassesToHide);
          }
          else {
            popup.add(removeFromClassesToHide);
          }
        }
        else if(ontologyTreePanel.ontologyViewerOptions
          .isClassesToShowFilterOn()) {
          if(!ontologyTreePanel.ontologyViewerOptions.classesToShow
            .contains(node.toString())) {
            popup.add(addToClassesToShow);
          }
          else {
            popup.add(removeFromClassesToShow);
          }
        }

        popup.add(cancel);
        popup.setOpaque(true);
        popup.setBackground(UIManager.getLookAndFeelDefaults().getColor(
          "ToolTip.background"));

        changeColor.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            Color col =
              JColorChooser.showDialog(ontologyTreePanel,
                "Select colour for \"" + node.toString() + "\"", color);
            if(col != null) {
              Color colAlpha =
                new Color(col.getRed(), col.getGreen(), col.getBlue(), 128);
              ontologyTreePanel.setColor(node.toString(), colAlpha);
              // so let us update our tree
              // and rehighlight
              ontologyTreePanel.repaint();
              ontologyTreePanel.ontoTreeListener.refreshHighlights();
              popup.setVisible(false);
            }
          }
        });

        addToClassesToHide.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            boolean disabled =
              ontologyTreePanel.ontologyViewerOptions.isChildFeatureDisabled();
            HashSet<String> classNames = new HashSet<String>();
            popup.setVisible(false);
            if(!disabled) {
              Ontology ontology = ontologyTreePanel.getCurrentOntology();
              OResource aClass =
                (OClass)ontology.getOResourceByName(node.toString());
              classNames.add(node.toString());
              if(aClass instanceof OClass) {
                Set<OClass> classes =
                  ((OClass)aClass).getSubClasses(OConstants.Closure.TRANSITIVE_CLOSURE);

                for(OClass ac : classes) {
                  classNames.add(ac.getName());
                }

                Set<OInstance> instances =
                  ontology.getOInstances((OClass)aClass,
                    OConstants.Closure.TRANSITIVE_CLOSURE);
                for(OInstance ai : instances) {
                  classNames.add(ai.getName());
                }
              }
            }
            else {
              classNames.add(node.toString());
            }
            ontologyTreePanel.ontologyViewerOptions
              .addToClassesToHide(classNames);
            return;
          }
        });

        removeFromClassesToHide.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            boolean disabled =
              ontologyTreePanel.ontologyViewerOptions.isChildFeatureDisabled();
            HashSet<String> classNames = new HashSet<String>();
            popup.setVisible(false);
            if(!disabled) {
              Ontology ontology = ontologyTreePanel.getCurrentOntology();
              OResource aClass =
                (OClass)ontology.getOResourceByName(node.toString());
              classNames.add(node.toString());
              if(aClass instanceof OClass) {
                Set<OClass> classes =
                  ((OClass)aClass).getSubClasses(OConstants.Closure.TRANSITIVE_CLOSURE);

                for(OClass ac : classes) {
                  classNames.add(ac.getName());
                }

                Set<OInstance> instances =
                  ontology.getOInstances((OClass)aClass,
                    OConstants.Closure.TRANSITIVE_CLOSURE);
                for(OInstance ai : instances) {
                  classNames.add(ai.getName());
                }
              }
            }
            else {
              classNames.add(node.toString());
            }
            ontologyTreePanel.ontologyViewerOptions
              .removeFromClassesToHide(classNames);
            return;
          }
        });

        addToClassesToShow.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            boolean disabled =
              ontologyTreePanel.ontologyViewerOptions.isChildFeatureDisabled();
            HashSet<String> classNames = new HashSet<String>();
            popup.setVisible(false);
            if(!disabled) {
              Ontology ontology = ontologyTreePanel.getCurrentOntology();
              OResource aClass =
                (OClass)ontology.getOResourceByName(node.toString());
              classNames.add(node.toString());
              if(aClass instanceof OClass) {
                Set<OClass> classes =
                  ((OClass)aClass).getSubClasses(OConstants.Closure.TRANSITIVE_CLOSURE);

                for(OClass ac : classes) {
                  classNames.add(ac.getName());
                }

                Set<OInstance> instances =
                  ontology.getOInstances((OClass)aClass,
                    OConstants.Closure.TRANSITIVE_CLOSURE);
                for(OInstance ai : instances) {
                  classNames.add(ai.getName());
                }
              }
            }
            else {
              classNames.add(node.toString());
            }
            ontologyTreePanel.ontologyViewerOptions
              .addToClassesToShow(classNames);
            return;
          }
        });

        removeFromClassesToShow.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            boolean disabled =
              ontologyTreePanel.ontologyViewerOptions.isChildFeatureDisabled();
            HashSet<String> classNames = new HashSet<String>();
            popup.setVisible(false);
            if(!disabled) {
              Ontology ontology = ontologyTreePanel.getCurrentOntology();
              OResource aClass =
                (OClass)ontology.getOResourceByName(node.toString());
              classNames.add(node.toString());
              if(aClass instanceof OClass) {
                Set<OClass> classes =
                  ((OClass)aClass).getSubClasses(OConstants.Closure.TRANSITIVE_CLOSURE);

                for(OClass ac : classes) {
                  classNames.add(ac.getName());
                }

                Set<OInstance> instances =
                  ontology.getOInstances((OClass)aClass,
                    OConstants.Closure.TRANSITIVE_CLOSURE);
                for(OInstance ai : instances) {
                  classNames.add(ai.getName());
                }
              }
            }
            else {
              classNames.add(node.toString());
            }
            ontologyTreePanel.ontologyViewerOptions
              .removeFromClassesToShow(classNames);
            return;
          }
        });

        cancel.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            popup.setVisible(false);
          }
        });
        popup.setVisible(true);
        popup.show(ontologyTreePanel, x, y);
        ontologyTreePanel.ontoViewer.documentTextArea.requestFocus();
        return;
      }

      if(!ontologyTreePanel.ontologyViewerOptions.shouldShow(node.toString())) { return; }

      String selectedText =
        ontologyTreePanel.ontoViewer.documentTextArea.getSelectedText();
      ontologyTreePanel.ontoViewer.annotationAction.hideAllWindows();

      if(selectedText != null && selectedText.length() > 0) {
        if(node.getSource() instanceof OClass) {
          addNewAnnotation(node, false, null, false, false);
        }
        else {
          addNewAnnotation(node, false, null, true, false);
        }
        ontologyTreePanel.ontoViewer.documentTextArea.requestFocus();
      }

      boolean isSelected =
        !ontologyTreePanel.currentOResource2IsSelectedMap.get(node.toString())
          .booleanValue();

      // now if the sibling feature is ON we need to reflect our changes
      // to its children as well
      if(!ontologyTreePanel.ontologyViewerOptions.isChildFeatureDisabled()) {
        // yes it is ON
        setChildrenSelection(node, isSelected);

        if(isSelected) {
          Object[] paths = path.getPath();
          expandChildren(paths, node);
        }
      }
      else {
        ontologyTreePanel.setSelected(node.toString(), isSelected);
      }
      ontologyTreePanel.currentOntologyTree.repaint();

      // so now we need to highlight all the stuff
      refreshHighlights();
      ontologyTreePanel.ontoViewer.documentTextArea.requestFocus();
    }
  }

  /**
   * This is to expand all the paths
   * 
   * @param paths
   *          => This should be the all ancestor in proper sequence including
   *          the current node at end for e.g. root, greatgrandfather,
   *          grandfather, father, currentnode
   * @param node
   *          => it is currentnode.. providing this would help interate through
   *          all this node's children and expand them into the ontotree
   */
  private void expandChildren(Object[] paths, IFolder node) {
    Object[] newPath = new Object[paths.length + 1];
    System.arraycopy(paths, 0, newPath, 0, paths.length);

    if(node.getChildCount() > 0) {
      Iterator iter = node.getChildren();
      while(iter.hasNext()) {
        IFolder node1 = (IFolder)iter.next();
        newPath[newPath.length - 1] = node1;
        expandChildren(newPath, node1);
      }
    }
    else {
      Object[] myArray = new Object[paths.length - 1];
      System.arraycopy(paths, 0, myArray, 0, paths.length - 1);
      TreePath temp = new TreePath(myArray);

      if(!ontologyTreePanel.currentOntologyTree.isVisible(temp)) {
        ontologyTreePanel.currentOntologyTree.expandPath(new TreePath(myArray));
      }
    }
  }

  /**
   * This method is to find out the path for this node from the root of the tree
   * 
   * @param node
   * @return
   */
  public TreePath getTreePath(IFolder node) {
    IFolder root =
      (IFolder)ontologyTreePanel.currentOntologyTree.getModel().getRoot();
    Object[] path = new Object[0];
    path = traverseThroughPath(root, node, path);
    return new TreePath(path);
  }

  /**
   * Internal method used to find out the tree path
   * 
   * @param currentNode
   * @param nodeToFind
   * @param path
   * @return
   */
  private Object[] traverseThroughPath(IFolder currentNode, IFolder nodeToFind,
    Object[] path) {
    if(currentNode.equals(nodeToFind)) { return path; }
    if(currentNode.getChildCount() > 0) {
      Object[] tempPath = new Object[path.length + 1];
      System.arraycopy(path, 0, tempPath, 0, path.length);
      tempPath[tempPath.length - 1] = currentNode;
      Iterator children = currentNode.getChildren();
      while(children.hasNext()) {
        IFolder node = (IFolder)children.next();
        Object[] returnedPath = traverseThroughPath(node, nodeToFind, tempPath);
        if(returnedPath != null) { return returnedPath; }
      }
    }
    return null;
  }

  /** Method to select the children node as well */
  void setChildrenSelection(IFolder node, boolean value) {
    ontologyTreePanel.setSelected(node.toString(), value);
    if(node.getChildCount() > 0) {
      Iterator iter = node.getChildren();
      while(iter.hasNext()) {
        setChildrenSelection((IFolder)iter.next(), value);
      }
    }
  }
  
  private void addPropertyValue(Ontology currentOntology, String propName, OResource resource, String value) {
    OURI propURI = null;
    try {
      try {
        propURI = currentOntology.createOURI(propName);
      }
      catch(InvalidURIException iue) {
        // not a full URI, try it as a name
        propURI = currentOntology.createOURIForName(propName);
      }
      
      AnnotationProperty prop =
        currentOntology.getAnnotationProperty(propURI);

      if(prop != null) {
        resource.addAnnotationPropertyValue(
          (AnnotationProperty)prop, new Literal(value));
      }
      else {
        // check that this URI isn't a class or instance
        if(currentOntology.containsOClass(propURI)) {
          JOptionPane.showMessageDialog(MainFrame.getInstance(), "URI "
                  + propURI + " already in use as a class");
        }
        else if(currentOntology.containsOInstance(propURI)) {
          JOptionPane.showMessageDialog(MainFrame.getInstance(), "URI "
                  + propURI + " already in use as an instance");              
        }
        else {
          AnnotationProperty aProp =
            ontologyTreePanel.getCurrentOntology().addAnnotationProperty(
              propURI);
          if(aProp == null) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "URI "
                    + propURI
                    + " already in use as a non-annotation property");                
          }
          else {
            resource.addAnnotationPropertyValue(aProp,
              new Literal(value));
          }
        }
      }
    }
    catch(InvalidURIException iue) {
      JOptionPane.showMessageDialog(MainFrame.getInstance(),
              "Cannot construct valid URI from property name "
              + propName);          
    }
    
  }

  /**
   * Method to add a new annotation
   * 
   * @param classValue
   * @param all
   */
  public ArrayList<Annotation> addNewAnnotation(ClassNode node, boolean all,
    FeatureMap map, boolean isClassFeature, boolean shouldCreateInstance) {

    ArrayList<Annotation> toReturn = new ArrayList<Annotation>();

    // lets find out the text offsets
    int start =
      ontologyTreePanel.ontoViewer.documentTextArea.getSelectionStart();
    int end = ontologyTreePanel.ontoViewer.documentTextArea.getSelectionEnd();

    ArrayList<Integer[]> offsets = new ArrayList<Integer[]>();
    // now if all is true then we need to find out all the occurances of
    // same text in the document
    if(all) {
      // so first find out the respective text
      String textToSearchIn =
        ontologyTreePanel.ontoViewer.getDocument().getContent().toString();

      if(!ontologyTreePanel.ontologyViewerOptions.isAddAllOptionCaseSensitive()) {
        textToSearchIn = textToSearchIn.toLowerCase();
      }

      String textToSearch = textToSearchIn.substring(start, end);

      int index = 0;
      while(index >= 0) {
        index = textToSearchIn.indexOf(textToSearch, index);
        if(index >= 0) {
          offsets.add(new Integer[]{new Integer(index),
            new Integer(index + textToSearch.length())});
          index = index + textToSearch.length();
        }
      }
    }
    else {
      offsets.add(new Integer[]{new Integer(start), new Integer(end)});
    }
    String selectedText =
      ontologyTreePanel.ontoViewer.documentTextArea.getSelectedText();
    //selectedText = selectedText.replaceAll(" ", "_");

    ontologyTreePanel.ontoViewer.documentTextArea.setSelectionStart(start);
    ontologyTreePanel.ontoViewer.documentTextArea.setSelectionEnd(start);

    // and so add it to the annotationSetName
    String annotationSet =
      ontologyTreePanel.ontologyViewerOptions.getSelectedAnnotationSetName();
    AnnotationSet set = null;
    if(annotationSet.equals(OntologyViewerOptions.DEFAULT_ANNOTATION_SET)) {
      set = ontologyTreePanel.ontoViewer.getDocument().getAnnotations();
    }
    else {
      set =
        ontologyTreePanel.ontoViewer.getDocument()
          .getAnnotations(annotationSet);
    }

    FeatureMap newMap = Factory.newFeatureMap();
    if(map != null) newMap.putAll(map);

    newMap.remove(ontologyTreePanel.ontologyViewerOptions
      .getSelectedClassURIFeatureName());
    newMap.remove(ontologyTreePanel.ontologyViewerOptions
      .getSelectedInstanceURIFeatureName());

    if(isClassFeature) {
      newMap.put(ontologyTreePanel.ontologyViewerOptions
        .getSelectedClassURIFeatureName(), ((OResource)node.getSource())
        .getURI().toString());

      // we need to check here if we need to add the selected value as a
      // property value
      String propName =
        ontologyTreePanel.ontologyViewerOptions.getPropertyName();
      Ontology currentOntology = ontologyTreePanel.getCurrentOntology();
      if(propName != null) {
        addPropertyValue(currentOntology, propName, ((OClass)node.getSource()), selectedText);
      }

    }
    else {
      gate.creole.ontology.URI uri = null;
      String classFeature = null;
      OInstance instance = null;

      if(shouldCreateInstance) {
        // generate instance URI
        String instanceName =
          ((OClass)node.getSource()).getName() + "_" + Gate.genSym();
        OResource aResource =
          ontologyTreePanel.getCurrentOntology().getOResourceByName(
            instanceName);
        classFeature = ((OClass)node.getSource()).getURI().toString();
        if(aResource == null) {
          uri =
              gate.creole.ontology.OntologyUtilities.createURI(ontologyTreePanel.getCurrentOntology(),
              instanceName, false);

          instance =
            ontologyTreePanel.getCurrentOntology().addOInstance(uri,
              (OClass)node.getSource());
        }
        else {
          int index = 1;
          while(true) {
            OResource tempResource =
              ontologyTreePanel.getCurrentOntology().getOResourceByName(
                instanceName + index);
            if(tempResource == null) {
              uri =
                  gate.creole.ontology.OntologyUtilities.createURI(ontologyTreePanel
                  .getCurrentOntology(), instanceName + index, false);

              instance =
                ontologyTreePanel.getCurrentOntology().addOInstance(uri,
                  (OClass)node.getSource());
              break;
            }
            index++;
          }
        }

      }
      else {
        OResource res = (OResource)node.getSource();
        if(res instanceof OInstance) {
          Set<OClass> classes =
            ((OInstance)res).getOClasses(OConstants.Closure.DIRECT_CLOSURE);
          if(classes.size() > 0) {
            classFeature = classes.iterator().next().getURI().toString();
          }
          instance = (OInstance)res;
        }
        uri = ((OResource)node.getSource()).getURI();

      }

      newMap.put(ontologyTreePanel.ontologyViewerOptions
        .getSelectedInstanceURIFeatureName(), uri.toString());
      if(classFeature != null) {
        newMap.put(ontologyTreePanel.ontologyViewerOptions
          .getSelectedClassURIFeatureName(), classFeature);

      }

      // we need to check here if we need to add the selected value as a
      // property value
      String propName =
        ontologyTreePanel.ontologyViewerOptions.getPropertyName();
      if(propName != null && instance != null) {
        Ontology currentOntology = ontologyTreePanel.getCurrentOntology();
        if(propName != null) {
          addPropertyValue(currentOntology, propName, instance, selectedText);
        }

      }
    }

    // to be compatible with KIM which has ontology feature without ending #
    String dns = ontologyTreePanel.getCurrentOntology().getDefaultNameSpace();
    newMap.put(gate.creole.ANNIEConstants.LOOKUP_ONTOLOGY_FEATURE_NAME, dns
      .substring(0, dns.length() - 1));

    for(int i = 0; i < offsets.size(); i++) {

      start = (((Integer[])offsets.get(i))[0]).intValue();
      end = (((Integer[])offsets.get(i))[1]).intValue();

      try {

        if(i == 0) {
          // if(!ontologyTreePanel.showingNewInstanceAnnotationWindow) {
          ontologyTreePanel.setSelected(node.toString(), true);
          // }
          // and finally we need to expand the path
          if(node != null) {
            TreePath path = getTreePath(node);
            ontologyTreePanel.currentOntologyTree.expandPath(path);
            ontologyTreePanel.currentOntologyTree.scrollPathToVisible(path);
          }
        }

        // and we need to add new annotation
        Integer id =
          set
            .add(new Long(start), new Long(end),
              ontologyTreePanel.ontologyViewerOptions
                .getSelectedAnnotationType(), newMap);
        toReturn.add(set.get(id));

      }
      catch(gate.util.InvalidOffsetException ioe) {
        throw new GateRuntimeException(ioe);
      }
    }
    return toReturn;
  }

  /**
   * Method to remove all highlights
   */
  public void removeHighlights() {
    annotationRange = new int[0];
    // everytime we hightlight first we remove all the highlights
    if(highlightedTags != null) {
      for(int i = 0; i < highlightedTags.size(); i++) {
        highlighter.removeHighlight(highlightedTags.get(i));
      }
    }
    highlightedAnnotations = new ArrayList<Annotation>();
    highlightedTags = new ArrayList<Object>();
  }

  /**
   * Method to highlight the annotations
   */
  public void refreshHighlights() {

    annotationRange = new int[0];
    // everytime we hightlight first we remove all the highlights
    if(highlightedTags != null) {
      for(int i = 0; i < highlightedTags.size(); i++) {
        highlighter.removeHighlight(highlightedTags.get(i));
      }
    }
    highlightedTags = new ArrayList<Object>();
    highlightedAnnotations = new ArrayList<Annotation>();

    HashMap<String, ArrayList<Annotation>> currentClassName2AnnotationsListMap =
      ontologyTreePanel.currentOResourceName2AnnotationsListMap;
    if(currentClassName2AnnotationsListMap == null) return;

    HashMap<String, Boolean> currentClass2IsSelectedMap =
      ontologyTreePanel.currentOResource2IsSelectedMap;

    // if there is no class selected we donot need to highlight anything
    if(currentClass2IsSelectedMap == null
      || currentClass2IsSelectedMap.isEmpty()) { return; }

    Iterator<String> iter = currentClass2IsSelectedMap.keySet().iterator();
    while(iter.hasNext()) {
      String className = iter.next();
      if(!ontologyTreePanel.ontologyViewerOptions.shouldShow(className)) {
        continue;
      }

      if(!currentClass2IsSelectedMap.get(className).booleanValue()) {
        continue;
      }

      String key = className;
      if(className.startsWith("[") && className.endsWith("]"))
        key = className.substring(1, className.length()-1);
      
      ArrayList<Annotation> annotationsList =
        currentClassName2AnnotationsListMap.get(key);
      
      

      if(annotationsList == null || annotationsList.isEmpty()) {
        continue;
      }
      else {
        // see which annotation types should only be listed
        String typeToMatch =
          ontologyTreePanel.ontologyViewerOptions.getSelectedAnnotationType();
        String setToMatch =
          ontologyTreePanel.ontologyViewerOptions
            .getSelectedAnnotationSetName();
        for(int j = 0; j < annotationsList.size(); j++) {
          Annotation ann = (Annotation)annotationsList.get(j);
          if(ann.getType().equals(typeToMatch)) {
            String set = ontologyTreePanel.ontoViewer.getAnnotationSet(ann);
            if(set == null
              && setToMatch
                .equals(OntologyViewerOptions.DEFAULT_ANNOTATION_SET)) {
              // do nothing
            }
            else if(set != null && set.equals(setToMatch)) {
              // so nothing
            }
            else {
              continue;
            }
          }
          else {
            continue;
          }

          try {
            Color color = ontologyTreePanel.getHighlightColor(className);
            Object tag =
              highlighter.addHighlight(ann.getStartNode().getOffset()
                .intValue(), ann.getEndNode().getOffset().intValue(),
                new DefaultHighlighter.DefaultHighlightPainter(color));
            highlightedAnnotations.add(ann);
            highlightedTags.add(tag);
          }
          catch(javax.swing.text.BadLocationException e) {
            throw new GateRuntimeException(e);
          }
        }
      }
    }

    // This is to make process faster.. instead of accessing each
    // annotation
    // and its offset, we create an array with its annotation offsets to
    // search
    // faster
    ArrayList<Annotation> highAnns = highlightedAnnotations;
    Collections.sort(highAnns, new gate.util.OffsetComparator());
    annotationRange = new int[highAnns.size() * 2];
    for(int i = 0, j = 0; j < highAnns.size(); i += 2, j++) {
      Annotation ann = (Annotation)highAnns.get(j);
      annotationRange[i] = ann.getStartNode().getOffset().intValue();
      annotationRange[i + 1] = ann.getEndNode().getOffset().intValue();
    }
  }
}
