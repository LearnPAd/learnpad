/*
 *  OntologyTreePanel.java
 *
 *  Niraj Aswani, 12/March/07
 *
 *  $Id: OntologyTreePanel.html,v 1.0 2007/03/12 16:13:01 niraj Exp $
 */
package gate.creole.ontology.ocat;

import gate.Annotation;
import gate.AnnotationSet;
import gate.creole.ontology.AnnotationProperty;
import gate.creole.ontology.DatatypeProperty;
import gate.creole.ontology.Literal;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.ObjectProperty;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.RDFProperty;
import gate.gui.docview.AnnotationSetsView;
import gate.gui.docview.TextualDocumentView;
import gate.swing.ColorGenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.TreeSelectionModel;

import com.ontotext.gate.vr.ClassNode;
import com.ontotext.gate.vr.IFolder;
import com.ontotext.gate.vr.OntoTreeModel;

/**
 * This class provides a GUI frame for the OCAT tool, where one of the
 * components is the OntologyTree, the other one is Ontology Options and so on.
 * 
 * @author niraj
 */
@SuppressWarnings("deprecation")
public class OntologyTreePanel extends JPanel {

  /**
   * Serial version ID
   */
  private static final long serialVersionUID = 3618419328190592304L;

  /** Instance of JTree used to store information about ontology classes */
  protected JTree currentOntologyTree;

  /** The current currentOntologyTreeModel */
  private OntoTreeModel currentOntologyTreeModel;

  /** ToolBars that displays the different options */
  private JToolBar leftToolBar;

  /** OntologyViewerOptions instance */
  protected OntologyViewerOptions ontologyViewerOptions;

  /**
   * Stores all the various ontology2OntoTreeModels for different ontologies
   */
  protected HashMap<Ontology, OntoTreeModel> ontology2OntoTreeModels;

  /** Stores various color schemes for different ontology classes */
  protected HashMap<Ontology, HashMap<String, Color>> ontology2ColorSchemesMap;

  /** Current ontologyColorScheme */
  protected HashMap<String, Color> currentOResource2ColorMap;

  /** This stores Class selection map for each individual loaded ontology */
  protected HashMap<Ontology, HashMap<String, Boolean>> ontology2OResourceSelectionMap;

  /** Class Selection map for the current ontology */
  protected HashMap<String, Boolean> currentOResource2IsSelectedMap;

  /** This stores Class selection map for each individual loaded ontology */
  protected HashMap<Ontology, Set<RDFProperty>> ontology2PropertiesMap;

  /** Class Selection map for the current ontology */
  protected Set<RDFProperty> currentProperties;

  /**
   * This stores instances and the classes that instance belongs to
   */
  protected HashMap<Ontology, HashMap<String, Set<OClass>>> ontology2PropValuesAndInstances2ClassesMap;

  /**
   * instances of the ontology and their classes
   */
  protected HashMap<String, Set<OClass>> currentPropValuesAndInstances2ClassesMap;

  /** Central Textual Document View */
  private TextualDocumentView textView;

  /**
   * Current Annotation Map that stores the annotation in arraylist for each
   * concept
   */
  protected HashMap<String, ArrayList<Annotation>> currentOResourceName2AnnotationsListMap;

  /** Instance of colorGenerator */
  private ColorGenerator colorGenerator;

  /** Current Ontology */
  private Ontology currentOntology;

  /**
   * OntologyTreeListener that listens to the selection of ontology classes
   */
  protected OntologyTreeListener ontoTreeListener;

  /** Instance of ontology Viewer */
  protected OntologyViewer ontoViewer;

  /**
   * Indicates whether the annotation window is being shown or not
   */
  protected boolean showingAnnotationWindow = false;

  /** Constructor */
  public OntologyTreePanel(OntologyViewer ontoViewer) {
    this.ontoViewer = ontoViewer;
    this.textView = ontoViewer.documentTextualDocumentView;
    this.ontologyViewerOptions = new OntologyViewerOptions(this);
    ontology2ColorSchemesMap = new HashMap<Ontology, HashMap<String, Color>>();
    ontology2PropValuesAndInstances2ClassesMap =
      new HashMap<Ontology, HashMap<String, Set<OClass>>>();
    ontology2OntoTreeModels = new HashMap<Ontology, OntoTreeModel>();
    currentOResource2ColorMap = new HashMap<String, Color>();
    ontology2OResourceSelectionMap =
      new HashMap<Ontology, HashMap<String, Boolean>>();
    currentOResource2IsSelectedMap = new HashMap<String, Boolean>();
    ontology2PropertiesMap = new HashMap<Ontology, Set<RDFProperty>>();
    colorGenerator = new ColorGenerator();
    initGUI();
  }

  /**
   * This method finds out the ClassNode node in the ontology Tree for given
   * class
   * 
   * @param classValue
   * @return
   */
  public ClassNode getFirstNode(String classValue) {
    // lets first convert this classValue into the className
    int index = classValue.lastIndexOf("#");
    if(index < 0) index = classValue.lastIndexOf("/");
    if(index < 0) index = classValue.lastIndexOf(":");
    if(index >= 0) {
      classValue = classValue.substring(index + 1, classValue.length());
    }

    ClassNode currentNode = (ClassNode)currentOntologyTree.getModel().getRoot();
    return getFirstClassNode(currentNode, classValue);
  }

  /**
   * Internal recursive method to find out the Node for given class Value under
   * the heirarchy of given node
   * 
   * @param node
   * @param classValue
   * @return
   */
  private ClassNode getFirstClassNode(ClassNode node, String classValue) {
    if(node.toString().intern() == classValue.intern()) { return node; }

    Iterator children = node.getChildren();
    while(children.hasNext()) {
      ClassNode tempNode = (ClassNode)children.next();
      ClassNode returnedNode = getFirstClassNode(tempNode, classValue);
      if(returnedNode != null) { return returnedNode; }
    }
    return null;
  }

  /**
   * This method finds out the ClassNode node in the ontology Tree for given
   * class
   * 
   * @param classValue
   * @return
   */
  public List<ClassNode> getNode(String classValue) {
    // lets first convert this classValue into the className
    int index = classValue.lastIndexOf("#");
    if(index < 0) index = classValue.lastIndexOf("/");
    if(index < 0) index = classValue.lastIndexOf(":");
    if(index >= 0) {
      classValue = classValue.substring(index + 1, classValue.length());
    }

    ClassNode currentNode = (ClassNode)currentOntologyTree.getModel().getRoot();
    return getClassNode(currentNode, classValue);
  }

  /**
   * Internal recursive method to find out the Node for given class Value under
   * the heirarchy of given node
   * 
   * @param node
   * @param classValue
   * @return
   */
  private List<ClassNode> getClassNode(ClassNode node, String classValue) {
    List<ClassNode> cNodes = new ArrayList<ClassNode>();
    if(node.toString().equalsIgnoreCase(classValue)) {
      cNodes.add(node);
      return cNodes;
    }

    Iterator children = node.getChildren();
    while(children.hasNext()) {
      ClassNode tempNode = (ClassNode)children.next();
      List<ClassNode> returnedNodes = getClassNode(tempNode, classValue);
      if(returnedNodes != null) {
        cNodes.addAll(returnedNodes);
      }
    }

    return cNodes;
  }

  /** Deletes the Annotations from the document */
  public void deleteAnnotation(Annotation annot) {
    // and now removing from the actual document
    AnnotationSet set = ontoViewer.getDocument().getAnnotations();
    if(!(set.remove(annot))) {
      Map annotSetMap = ontoViewer.getDocument().getNamedAnnotationSets();
      if(annotSetMap != null) {
        java.util.List<String> setNames =
          new ArrayList<String>(annotSetMap.keySet());
        Collections.sort(setNames);
        Iterator<String> setsIter = setNames.iterator();
        while(setsIter.hasNext()) {
          set = ontoViewer.getDocument().getAnnotations(setsIter.next());
          if(set.remove(annot)) { return; }
        }
      }
    }
  }

  /** Returns the current ontology */
  public Ontology getCurrentOntology() {
    return currentOntology;
  }

  /** Returns the instance of highlighter */
  public javax.swing.text.Highlighter getHighlighter() {
    return ((JTextArea)((JScrollPane)textView.getGUI()).getViewport().getView())
      .getHighlighter();
  }

  /** Returns the associated color for the given class */
  public Color getHighlightColor(String classVal) {
    return (Color)currentOResource2ColorMap.get(classVal);
  }

  /** Initialize the GUI */
  private void initGUI() {
    currentOntologyTree = new JTree();

    ToolTipManager.sharedInstance().registerComponent(currentOntologyTree);
    currentOntologyTree.getSelectionModel().setSelectionMode(
      TreeSelectionModel.SINGLE_TREE_SELECTION);
    this.setLayout(new BorderLayout());
    this.add(new JScrollPane(currentOntologyTree), BorderLayout.CENTER);

    leftToolBar = new JToolBar(JToolBar.VERTICAL);
    leftToolBar.setFloatable(false);
    // this.add(leftToolBar, BorderLayout.WEST);

    ontoTreeListener = new OntologyTreeListener(this);
    currentOntologyTree.addMouseListener(ontoTreeListener);

    CheckRenderer cellRenderer = new CheckRenderer(this);
    currentOntologyTree.setCellRenderer(cellRenderer);
  }

  /** A method to show an empty ontology tree */
  public void showEmptyOntologyTree() {
    currentOntology = null;
    currentOntologyTreeModel = null;
    currentOResource2ColorMap = null;
    currentOResource2IsSelectedMap = null;
    currentProperties = null;
    currentOntologyTree.setVisible(false);
    ontoTreeListener.removeHighlights();
  }

  /**
   * This method is called to remove the stored ontology model and free up the
   * memory with other resources occupied by the removed ontology
   */
  public void removeOntologyTreeModel(Ontology ontology,
    boolean wasCurrentlySelected) {
    this.ontology2OntoTreeModels.remove(ontology);
    this.ontology2ColorSchemesMap.remove(ontology);
    this.ontology2OResourceSelectionMap.remove(ontology);
    this.ontology2PropValuesAndInstances2ClassesMap.remove(ontology);
    this.ontology2PropertiesMap.remove(ontology);
    if(ontology2OntoTreeModels == null || ontology2OntoTreeModels.size() == 0) {
      showEmptyOntologyTree();
    }
  }

  /**
   * This method is used to plot the ontology on the tree and generate/load the
   * respective data in the memory
   * 
   * @param ontology
   *          - the ontology to be ploted
   * @param currentOResourceName2AnnotationsListMap
   *          - the annotationMap which contains Key=concept(String)
   *          Value=annotations(ArrayList)
   */
  public void showOntologyInOntologyTreeGUI(Ontology ontology,
    HashMap<String, ArrayList<Annotation>> annotMap) {

    this.currentOResourceName2AnnotationsListMap = annotMap;
    if(currentOntology != null && currentOResource2ColorMap != null)
      ontology2ColorSchemesMap.put(currentOntology, currentOResource2ColorMap);

    if(currentOntology != null && currentOResource2IsSelectedMap != null)
      ontology2OResourceSelectionMap.put(currentOntology,
        currentOResource2IsSelectedMap);

    if(currentOntology != null && currentOntologyTreeModel != null
      && ontology2OntoTreeModels.containsKey(currentOntology))
      ontology2OntoTreeModels.put(currentOntology, currentOntologyTreeModel);

    if(currentOntology != null
      && currentPropValuesAndInstances2ClassesMap != null
      && ontology2OntoTreeModels.containsKey(currentOntology))
      ontology2PropValuesAndInstances2ClassesMap.put(currentOntology,
        currentPropValuesAndInstances2ClassesMap);

    if(currentOntology != null && currentProperties != null
      && ontology2OntoTreeModels.containsKey(currentOntology))
      ontology2PropertiesMap.put(currentOntology, currentProperties);

    currentOntology = ontology;
    ClassNode root = null;
    // lets create the new model for this new selected ontology
    if(ontology2OntoTreeModels != null
      && ontology2OntoTreeModels.containsKey(ontology)) {
      currentOntologyTreeModel = ontology2OntoTreeModels.get(ontology);
      currentOResource2ColorMap = ontology2ColorSchemesMap.get(ontology);
      currentOResource2IsSelectedMap =
        ontology2OResourceSelectionMap.get(ontology);
      currentPropValuesAndInstances2ClassesMap =
        ontology2PropValuesAndInstances2ClassesMap.get(ontology);
      currentProperties = ontology2PropertiesMap.get(ontology);
    }
    else {
      root =
        ClassNode.createRootNode(ontology, true, ontologyViewerOptions
          .showAnonymousClasses());
      HashMap<String, Color> newColorScheme = new HashMap<String, Color>();
      setColorScheme(root, newColorScheme);
      currentOResource2ColorMap = newColorScheme;
      ontology2ColorSchemesMap.put(ontology, newColorScheme);
      currentOntologyTreeModel = new OntoTreeModel(root);
      ontology2OntoTreeModels.put(ontology, currentOntologyTreeModel);
      HashMap<String, Boolean> newClassSelection =
        new HashMap<String, Boolean>();
      setOntoTreeClassSelection(root, newClassSelection);
      currentOResource2IsSelectedMap = newClassSelection;
      ontology2OResourceSelectionMap.put(ontology, newClassSelection);
      currentProperties = obtainProperties(ontology);
      ontology2PropertiesMap.put(ontology, currentProperties);
      currentPropValuesAndInstances2ClassesMap =
        obtainPVnInst2ClassesMap(ontology, currentProperties);
      ontology2PropValuesAndInstances2ClassesMap.put(ontology,
        currentPropValuesAndInstances2ClassesMap);
    }
    currentOntologyTree.setModel(currentOntologyTreeModel);
    // update the GUI part of the Tree
    currentOntologyTree.invalidate();
  }

  /**
   * This method returns the properties available in the ontology
   * 
   * @param ontology
   * @return
   */
  private Set<RDFProperty> obtainProperties(Ontology ontology) {
    Set<RDFProperty> toReturn = new HashSet<RDFProperty>();
    // lets add all properties
    Set<RDFProperty> props = ontology.getPropertyDefinitions();
    Iterator<RDFProperty> iter = props.iterator();
    while(iter.hasNext()) {
      final RDFProperty p = iter.next();
      if(p instanceof AnnotationProperty || p instanceof DatatypeProperty
        || p instanceof ObjectProperty) {
        toReturn.add(p);
        continue;
      }
    }
    return toReturn;
  }

  /**
   * This method iterates through each instance of the ontology and obtains its
   * all set properties. For each set property, obtains its value and add it to
   * the returning map as a key. A set of direct classes of the instance then
   * becomes the value for this key.
   * 
   * @param ontology
   * @return
   */
  private HashMap<String, Set<OClass>> obtainPVnInst2ClassesMap(
    Ontology ontology, Set<RDFProperty> propertySet) {
    HashMap<String, Set<OClass>> map = new HashMap<String, Set<OClass>>();

    Set<OInstance> instances = ontology.getOInstances();
    Iterator<OInstance> instIter = instances.iterator();
    while(instIter.hasNext()) {
      OInstance anInst = instIter.next();
      Set<OClass> classes = anInst.getOClasses(OConstants.Closure.DIRECT_CLOSURE);
      updatePVnInst2ClassesMap(anInst, propertySet, classes, map);
    }
    return map;
  }

  /**
   * This method iterates through each instance of the ontology and obtains its
   * all set properties. For each set property, obtains its value and add it to
   * the returning map as a key. A set of direct classes of the instance then
   * becomes the value for this key.
   * 
   * @param ontology
   * @return
   */
  public void updatePVnInst2ClassesMap(OInstance anInst,
    Set<RDFProperty> propertySet, Set<OClass> classes,
    HashMap<String, Set<OClass>> map) {

    String anInstName = anInst.getName();
    if(map.containsKey(anInstName.toLowerCase())) {
      Set<OClass> availableClasses = map.get(anInstName.toLowerCase());
      availableClasses.addAll(classes);
    }
    else {
      map.put(anInstName.toLowerCase(), classes);
    }

    Iterator<RDFProperty> propertyIter = propertySet.iterator();
    while(propertyIter.hasNext()) {

      RDFProperty anRDFProp = propertyIter.next();
      Set<String> stringValues = new HashSet<String>();

      // here we check what type of property it is
      if(anRDFProp instanceof AnnotationProperty) {
        java.util.List<Literal> values =
          anInst.getAnnotationPropertyValues((AnnotationProperty)anRDFProp);
        for(int i = 0; i < values.size(); i++) {
          stringValues.add(values.get(i).getValue().toLowerCase());
        }

      }
      else if(anRDFProp instanceof DatatypeProperty) {
        java.util.List<Literal> values =
          anInst.getDatatypePropertyValues((DatatypeProperty)anRDFProp);
        for(int i = 0; i < values.size(); i++) {
          stringValues.add(values.get(i).getValue().toLowerCase());
        }

      }
      else if(anRDFProp instanceof ObjectProperty) {
        java.util.List<OInstance> values =
          anInst.getObjectPropertyValues((ObjectProperty)anRDFProp);
        for(int i = 0; i < values.size(); i++) {
          stringValues.add(values.get(i).toString().toLowerCase());
        }
      }

      if(stringValues.isEmpty()) continue;

      Iterator<String> stringValIter = stringValues.iterator();
      while(stringValIter.hasNext()) {
        String aValue = stringValIter.next();
        if(map.containsKey(aValue)) {
          Set<OClass> availableClasses = map.get(aValue);
          availableClasses.addAll(classes);
        }
        else {
          map.put(aValue, classes);
        }
      }
    }
  }

  /**
   * For every ontology it generates the colors only once at the begining which
   * should remain same throughout the programe
   * 
   * @param root
   *          - the root (top class) of the ontology
   * @param colorScheme
   *          - and the colorScheme hashmap Key=conceptName, Value:associated
   *          color map. if provided as a new fresh instance of hashmap with
   *          size zero, it parses through the whole ontology and generate the
   *          random color instances for all the classes and stores them in the
   *          provided colorScheme hashmap
   */
  public void setColorScheme(IFolder root, HashMap<String, Color> colorScheme) {
    if(!colorScheme.containsKey(root.toString())) {
      colorScheme.put(root.toString(),
        AnnotationSetsView.getColor("OntologyColours",root.toString()));
      Iterator children = root.getChildren();
      while(children.hasNext()) {
        setColorScheme((IFolder)children.next(), colorScheme);
      }
    }
  }

  /**
   * This is to initialise the classSelection as false to all the classes
   * 
   * @param root
   * @param classSelection
   */
  public void setOntoTreeClassSelection(IFolder root,
    HashMap<String, Boolean> classSelection) {
    if(!classSelection.containsKey(root.toString())) {
      classSelection.put(root.toString(), new Boolean(true));
      Iterator children = root.getChildren();
      while(children.hasNext()) {
        setOntoTreeClassSelection((IFolder)children.next(), classSelection);
      }
    }
  }

  /**
   * returns the currentOntologyTree Panel
   * 
   * @return
   */
  public Component getGUI() {
    return this;
  }

  /**
   * This method select/deselect the classes in the classSelectionMap
   * 
   * @param className
   * @param value
   */
  public void setSelected(String className, boolean value) {
    currentOResource2IsSelectedMap.put(className, new Boolean(value));
    ontology2OResourceSelectionMap.put(currentOntology,
      currentOResource2IsSelectedMap);
  }

  public void setColor(String className, Color col) {
    currentOResource2ColorMap.put(className, col);
    ontology2ColorSchemesMap.put(currentOntology, currentOResource2ColorMap);
  }

  public Set<String> getAllClassNames() {
    Set<String> toReturn = new HashSet<String>();
    for(String aResource : currentOResource2IsSelectedMap.keySet()) {
      if(currentOntology.getOResourceByName(aResource) instanceof OClass) {
        toReturn.add(aResource);
      }
    }
    return toReturn;
  }

  public Set<String> getAllInstanceNames() {
    Set<String> toReturn = new HashSet<String>();
    for(String aResource : currentOResource2IsSelectedMap.keySet()) {
      if(currentOntology.getOResourceByName(aResource) instanceof OInstance) {
        toReturn.add(aResource);
      }
    }
    return toReturn;
  }
}
