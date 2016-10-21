/*
 *  OntologyViewer.java
 *
 *  Niraj Aswani, 12/March/07
 *
 *  $Id: OntologyViewer.html,v 1.0 2007/03/12 16:13:01 niraj Exp $
 */
package gate.creole.ontology.ocat;

import gate.gui.docview.AbstractDocumentView;
import javax.swing.*;

import com.ontotext.gate.vr.ClassNode;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import gate.*;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.OResource;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.OntologyModificationListener;
import gate.creole.ontology.RDFProperty;
import gate.event.*;
import gate.gui.docview.*;

/**
 * In this class we implement the basic structure of the ontology viewer Which
 * includes the total ontologies that has been loaded in the system option for
 * the new ontology, if someone wishes to load it into the system the ontology
 * viewer (which displays all the conetps) Ontology instance viewer and the
 * instance attribute viewer
 * 
 * @author niraj
 */
public class OntologyViewer extends AbstractDocumentView implements
                                                        CreoleListener,
                                                        AnnotationSetListener,
                                                        AnnotationListener,
                                                        OntologyModificationListener {
  /**
   * Serial version ID
   */
  private static final long serialVersionUID = 3977303230621759543L;

  /** Main panel which holds all different components */
  private JPanel mainPanel;

  /** This stores all the available instances of Ontologies in GATE */
  private ArrayList<Ontology> ontologies;

  /** ComboBox used to display the ontology instances */
  private JComboBox ontologyCB;

  /** Panel to display ComboBox */
  private JPanel ontologyCBPanel;

  /**
   * Instance of JTabbedPane to show the ontology Viewer and the
   * OntologyViewerOptions
   */
  private JTabbedPane tabbedPane;

  /** Instance of OntologyTreePanel */
  private OntologyTreePanel ontologyTreePanel;

  /**
   * AnnotationMap which stores all the relavant annotations (i.e. annotation
   * with class feature) in the following way className --> ArrayList (which
   * contains all the annotations) </p>
   */
  private HashMap<String, ArrayList<Annotation>> className2AnnotationList =
    new HashMap<String, ArrayList<Annotation>>();

  /** TextualDocument View instance */
  protected TextualDocumentView documentTextualDocumentView;

  /**
   * This is where the actual document text is being displayed.
   */
  protected JTextArea documentTextArea;

  /**
   * This object provides a functionality to add/remove/change the annotations
   */
  protected AnnotationAction annotationAction;

  /**
   * Each Annotation Set is given a unique ID, which is used later in the
   * annotationsID2ASID map.
   */
  protected HashMap<Integer, String> asID2ASName =
    new HashMap<Integer, String>();

  /**
   * The highest Annotation Set ID
   */
  private int highestASID = 0;

  /**
   * We store the ID of each annotation and the ID of annotation set the
   * annotation belongs to.
   */
  private HashMap<Integer, Integer> annotationsID2ASID =
    new HashMap<Integer, Integer>();

  /**
   * Given an annotation, this method tells which selectedAnnotationSetName it
   * belongs to.
   * 
   * @param ann
   * @return the name of the annotation set.
   */
  public String getAnnotationSet(Annotation ann) {
    Integer setId = annotationsID2ASID.get(ann.getId());
    return asID2ASName.get(setId);
  }

  /** Initialises the GUI */
  public void initGUI() {
    // get a pointer to the textual view used for highlights
    Iterator centralViewsIter = owner.getCentralViews().iterator();
    while(documentTextualDocumentView == null && centralViewsIter.hasNext()) {
      DocumentView aView = (DocumentView)centralViewsIter.next();
      if(aView instanceof TextualDocumentView)
        documentTextualDocumentView = (TextualDocumentView)aView;
    }
    documentTextArea =
      (JTextArea)((JScrollPane)documentTextualDocumentView.getGUI())
        .getViewport().getView();

    // lets create the OntologyTree Component
    ontologyTreePanel = new OntologyTreePanel(this);

    // first lets find out all the annotations
    // which have ontology and a class feature in it
    document.getAnnotations().addAnnotationSetListener(this);
    initLocalData();

    // first creating ontologyCB GUI
    ontologyCB = new JComboBox();
    ontologyCBPanel = new JPanel();
    ontologyCBPanel.setLayout(new FlowLayout());
    ontologyCBPanel.add(ontologyCB);

    // then lets create main panel design
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(ontologyCBPanel, BorderLayout.NORTH);
    mainPanel.add(ontologyTreePanel.getGUI(), BorderLayout.CENTER);
    mainPanel.setBackground(Color.white);
    int width =
      ontologyCB.getWidth() > ontologyTreePanel.getWidth() ? ontologyCB
        .getWidth() : ontologyTreePanel.getWidth();
    mainPanel.setMaximumSize(new Dimension(width, mainPanel.getHeight()));
    tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Ontology Tree(s)", mainPanel);
    tabbedPane.addTab("Options", ontologyTreePanel.ontologyViewerOptions
      .getGUI());

    // load ontologies from the Gate environment
    loadOntologies();
    // add various listeners, such as annotationAction,
    // selectionChangeAction and so on.
    addListeners();
    if(ontologies != null && ontologies.size() > 0) {
      ontologyTreePanel.showOntologyInOntologyTreeGUI(ontologies.get(0),
        className2AnnotationList);
    }
    else {
      ontologyTreePanel.showEmptyOntologyTree();
    }
  }

  /** ReIntializes all Maps used for local uses */
  protected void initLocalData() {
    className2AnnotationList.clear();
    asID2ASName.clear();
    HashMap<String, ArrayList<Annotation>> temp =
      getAnnotationsWithClassOrInstanceFeature(document.getAnnotations(), null);
    if(temp != null) {
      className2AnnotationList.putAll(temp);
    }

    Map<String, AnnotationSet> annotSetMap = document.getNamedAnnotationSets();
    if(annotSetMap != null) {
      java.util.List<String> setNames =
        new ArrayList<String>(annotSetMap.keySet());
      Collections.sort(setNames);
      Iterator<String> setsIter = setNames.iterator();
      while(setsIter.hasNext()) {
        temp =
          getAnnotationsWithClassOrInstanceFeature(document
            .getAnnotations(setsIter.next()), null);
        if(temp != null) {
          Iterator<String> keyIter = temp.keySet().iterator();
          while(keyIter.hasNext()) {
            String key = keyIter.next();
            if(className2AnnotationList.containsKey(key)
              && temp.get(key) != null) {
              className2AnnotationList.get(key).addAll(temp.get(key));
              continue;
            }
            else {
              className2AnnotationList.put(key, temp.get(key));
            }
          }
        }
      }
    }
  }

  /**
   * This method is called by respective selectedAnnotationSetName from where an
   * annotation is removed
   */
  public void annotationRemoved(AnnotationSetEvent ase) {
    if(ontologyTreePanel.getCurrentOntology() == null) return;

    Annotation currentAnnot = ase.getAnnotation();
    currentAnnot.removeAnnotationListener(this);

    // checks if it was of our interest
    FeatureMap map = currentAnnot.getFeatures();
    String ontoClassName =
      ontologyTreePanel.ontologyViewerOptions.getSelectedClassURIFeatureName();
    String ontoInstanceName =
      ontologyTreePanel.ontologyViewerOptions
        .getSelectedInstanceURIFeatureName();
    if(map.containsKey(ontoClassName) || map.containsKey(ontoInstanceName)) {
      // yes this is of our interest we need to remove this from our
      // className2AnnotationList if it exists
      Set<String> keySet =
        ontologyTreePanel.currentOResourceName2AnnotationsListMap.keySet();
      Iterator<String> iter = keySet.iterator();
      String className = "";
      ArrayList<Annotation> annots = null;
      outer: while(iter.hasNext()) {
        className = iter.next();
        annots = className2AnnotationList.get(className);
        if(annots != null) {
          for(int i = 0; i < annots.size(); i++) {
            if(currentAnnot.getId().equals(annots.get(i).getId())) {
              annots.remove(currentAnnot);
              break outer;
            }
          }
        }
      }

      ontologyTreePanel.currentOResourceName2AnnotationsListMap.put(className,
        annots);
      // remove this from annotationsID2ASID
      annotationsID2ASID.remove(currentAnnot.getId());
      // and finally we need to highlight annotations
      ontologyTreePanel.ontoTreeListener.refreshHighlights();
    }
  }

  public void annotationUpdated(AnnotationEvent ae) {
    if(ontologyTreePanel.getCurrentOntology() == null) return;

    Annotation currentAnnot = (Annotation)ae.getSource();
    // what we need to do is to add this to className2AnnotationList if
    // it is relavant to our stuff
    FeatureMap map = currentAnnot.getFeatures();
    String ontoClassName =
      ontologyTreePanel.ontologyViewerOptions.getSelectedClassURIFeatureName();
    String ontoInstanceName =
      ontologyTreePanel.ontologyViewerOptions
        .getSelectedInstanceURIFeatureName();
    String aName = null;
    String aValue = "";

    if(map.containsKey(ontoClassName)) {
      aName = (String)map.get(ontoClassName);
    }
    else if(map.containsKey(ontoInstanceName)) {
      aName = (String)map.get(ontoInstanceName);
    }
    else {
      return;
    }

    if(aName == null) return;

    int index = aName.lastIndexOf("#");
    if(index < 0) index = aName.lastIndexOf("/");
    if(index < 0) index = aName.lastIndexOf(":");
    if(index >= 0) {
      aValue = aName.substring(index + 1, aName.length());
    }
    else {
      aValue = aName;
    }

    if(className2AnnotationList.containsKey(aValue)) {
      ArrayList<Annotation> annotList = className2AnnotationList.get(aValue);
      if(!annotList.contains(currentAnnot)) {
        annotList.add(currentAnnot);
        className2AnnotationList.put(aValue, annotList);
      }
    }
    else {
      ArrayList<Annotation> annotList = new ArrayList<Annotation>();
      annotList.add(currentAnnot);
      className2AnnotationList.put(aValue, annotList);
    }

    AnnotationSet set = null;
    // find out the selectedAnnotationSetName that contains this
    // annotation
    if(document.getAnnotations().get(currentAnnot.getId()) != null) {
      set = document.getAnnotations();
    }
    else {
      Collection sets = document.getNamedAnnotationSets().values();
      Iterator iter = sets.iterator();
      while(iter.hasNext()) {
        AnnotationSet set1 = (AnnotationSet)iter.next();
        if(set1.get(currentAnnot.getId()) != null) {
          set = set1;
          break;
        }
      }
    }

    Integer setId = null;
    if(asID2ASName.values() != null && asID2ASName.values().contains(set)) {
      Iterator<Integer> iter = asID2ASName.keySet().iterator();
      while(iter.hasNext()) {
        Integer tempId = iter.next();
        String setString = asID2ASName.get(tempId);
        if(setString.equals(set.getName())) {
          setId = tempId;
          break;
        }
      }
    }
    else {
      setId = new Integer(highestASID);
      highestASID++;
      asID2ASName.put(setId, set.getName());
    }
    annotationsID2ASID.put(currentAnnot.getId(), setId);
    // done entry

    // we have added the annotation to the annotation Map
    // so we would call the
    ontologyTreePanel.showOntologyInOntologyTreeGUI(ontologyTreePanel
      .getCurrentOntology(), className2AnnotationList);
    ontologyTreePanel.ontoTreeListener.refreshHighlights();
  }

  /**
   * This method is invoked whenever a new annotation is added
   */
  public void annotationAdded(AnnotationSetEvent ase) {
    if(ontologyTreePanel.getCurrentOntology() == null) return;

    Annotation currentAnnot = ase.getAnnotation();
    currentAnnot.addAnnotationListener(this);

    // what we need to do is to add this to className2AnnotationList if
    // it is relavant to our stuff
    FeatureMap map = currentAnnot.getFeatures();
    String ontoClassName =
      ontologyTreePanel.ontologyViewerOptions.getSelectedClassURIFeatureName();
    String ontoInstanceName =
      ontologyTreePanel.ontologyViewerOptions
        .getSelectedInstanceURIFeatureName();

    String aName = (String)map.get(ontoClassName);
    String aValue = "";

    if(map.containsKey(ontoClassName)) {
      aName = (String)map.get(ontoClassName);
    }
    else if(map.containsKey(ontoInstanceName)) {
      aName = (String)map.get(ontoInstanceName);
    }
    else {
      return;
    }

    if(aName == null) return;

    int index = aName.lastIndexOf("#");
    if(index < 0) index = aName.lastIndexOf("/");
    if(index < 0) index = aName.lastIndexOf(":");
    if(index >= 0) {
      aValue = aName.substring(index + 1, aName.length());
    }
    else {
      aValue = aName;
    }

    if(className2AnnotationList.containsKey(aValue)) {
      ArrayList<Annotation> annotList = className2AnnotationList.get(aValue);
      annotList.add(currentAnnot);
      className2AnnotationList.put(aValue, annotList);
    }
    else {
      ArrayList<Annotation> annotList = new ArrayList<Annotation>();
      annotList.add(currentAnnot);
      className2AnnotationList.put(aValue, annotList);
    }

    // make entry in annotationsID2ASID
    AnnotationSet set = (AnnotationSet)ase.getSource();
    Integer setId = null;
    if(asID2ASName.values() != null && asID2ASName.values().contains(set)) {
      Iterator<Integer> iter = asID2ASName.keySet().iterator();
      while(iter.hasNext()) {
        Integer tempId = iter.next();
        String setString = asID2ASName.get(tempId);
        if(setString.equals(set.getName())) {
          setId = tempId;
          break;
        }
      }
    }
    else {
      setId = new Integer(highestASID);
      highestASID++;
      asID2ASName.put(setId, set.getName());
    }
    annotationsID2ASID.put(currentAnnot.getId(), setId);

    // we have added the annotation to the annotation Map
    // so we would call the
    ontologyTreePanel.showOntologyInOntologyTreeGUI(ontologyTreePanel
      .getCurrentOntology(), className2AnnotationList);
    ontologyTreePanel.ontoTreeListener.refreshHighlights();
  }

  /**
   * This method iterates through annotations and find out the ones with
   * ontology and class features and returns the map<String,
   * ArrayList<Annotation>>
   */
  private HashMap<String, ArrayList<Annotation>> getAnnotationsWithClassOrInstanceFeature(
    AnnotationSet set, String aClassName) {
    Integer setId = null;
    if(asID2ASName.values() != null
      && asID2ASName.values().contains(set.getName())) {
      Iterator<Integer> iter = asID2ASName.keySet().iterator();
      while(iter.hasNext()) {
        Integer tempId = iter.next();
        String setString = asID2ASName.get(tempId);
        if(setString.equals(set.getName())) {
          setId = tempId;
          break;
        }
      }
    }
    else {
      setId = new Integer(highestASID);
      highestASID++;
      asID2ASName.put(setId, set.getName());
    }

    HashMap<String, ArrayList<Annotation>> subMap =
      new HashMap<String, ArrayList<Annotation>>();
    // now lets find out all the annotations which have ontology as a
    // feature
    Iterator<Annotation> setIter = set.iterator();
    String ontoClassName =
      ontologyTreePanel.ontologyViewerOptions.getSelectedClassURIFeatureName();
    String ontoInstanceName =
      ontologyTreePanel.ontologyViewerOptions
        .getSelectedInstanceURIFeatureName();
    while(setIter.hasNext()) {
      Annotation currentAnnot = setIter.next();
      currentAnnot.removeAnnotationListener(this);
      currentAnnot.addAnnotationListener(this);
      FeatureMap map = currentAnnot.getFeatures();
      String aName = null;
      String aValue = "";

      if(map.containsKey(ontoClassName)) {
        aName = (String)map.get(ontoClassName);
      }
      else if(map.containsKey(ontoInstanceName)) {
        aName = (String)map.get(ontoInstanceName);
      }
      else {
        continue;
      }

      int index = aName.lastIndexOf("#");
      if(index < 0) index = aName.lastIndexOf("/");
      if(index < 0) index = aName.lastIndexOf(":");
      if(index >= 0) {
        aValue = aName.substring(index + 1, aName.length());
      }
      else {
        aValue = aName;
      }

      if(aClassName == null || aValue.equals(aClassName)) {
        if(subMap.containsKey(aValue)) {
          ArrayList<Annotation> annotList = subMap.get(aValue);
          annotList.add(currentAnnot);
          subMap.put(aValue, annotList);
        }
        else {
          ArrayList<Annotation> annotList = new ArrayList<Annotation>();
          annotList.add(currentAnnot);
          subMap.put(aValue, annotList);
        }
      }
      annotationsID2ASID.put(currentAnnot.getId(), setId);
    }
    return subMap;
  }

  public void registerHooks() {
    documentTextArea.addMouseListener(annotationAction);
    documentTextArea.addMouseMotionListener(annotationAction);
    if(ontologyTreePanel != null) {
      if(ontologyTreePanel.ontoTreeListener != null) {
        ontologyTreePanel.ontoTreeListener.refreshHighlights();
      }
    }
  }

  public void unregisterHooks() {
    documentTextArea.removeMouseListener(annotationAction);
    documentTextArea.removeMouseMotionListener(annotationAction);
    if(ontologyTreePanel != null) {
      if(ontologyTreePanel.ontoTreeListener != null) {
        ontologyTreePanel.ontoTreeListener.removeHighlights();
      }
    }
  }

  public int getType() {
    return VERTICAL;
  }

  /**
   * gets the main tabbed pane that will be displayed as the OCAT component
   */
  public Component getGUI() {
    return tabbedPane;
  }

  /**
   * Adds various listeners to the different components
   */
  private void addListeners() {
    Gate.getCreoleRegister().addCreoleListener(this);
    ontologyCB.addActionListener(new OntologySelectionChangeAction());
    annotationAction = new AnnotationAction(ontologyTreePanel);
  }

  /**
   * Releases all resources and listeners
   */
  public void cleanup() {
    Gate.getCreoleRegister().removeCreoleListener(this);
    // and we need to remove the annotationSetListener as well
    AnnotationSet set = document.getAnnotations();
    set.removeAnnotationSetListener(this);
    Map annotSetMap = document.getNamedAnnotationSets();
    if(annotSetMap != null) {
      java.util.List<String> setNames =
        new ArrayList<String>(annotSetMap.keySet());
      Collections.sort(setNames);
      Iterator<String> setsIter = setNames.iterator();
      while(setsIter.hasNext()) {
        set = document.getAnnotations(setsIter.next());
        set.removeAnnotationSetListener(this);
      }
    }
  }

  /**
   * Searches within the GATE system to find out all the loaded ontologies
   */
  private void loadOntologies() {
    if(ontologies == null) {
      ontologies = new ArrayList<Ontology>();
      java.util.List lrs = gate.Gate.getCreoleRegister().getPublicLrInstances();
      Iterator iter1 = lrs.iterator();
      while(iter1.hasNext()) {
        gate.LanguageResource lr = (LanguageResource)iter1.next();
        if(!(lr instanceof Ontology)) continue;
        ((Ontology)lr).addOntologyModificationListener(this);
        ontologies.add((Ontology)lr);
        ontologyCB.addItem(((Ontology)lr).getName());
      }
    }
  }

  /**
   * Refresh the comboBox.. in case new ontology is loaded or one is removed
   * 
   * @param ontology
   *          this could be either newly added ontology or that removed one
   * @param removed
   *          if true instance is removed otherwise added
   */
  private void refreshOntologyCB(Ontology ontology, boolean removed) {
    if(removed) {
      // first we need to see if there exists any instance in the system
      // that has the same id, if so, we don't want to remove this
      int index = ontologies.indexOf(ontology);
      if(index >= 0) {
        ontologies.remove(ontology);
        ontologyCB.removeItemAt(index);
        ontologyCB.invalidate();
        boolean wasCurrentlySelected = false;
        if(ontologyTreePanel.getCurrentOntology() == ontology)
          wasCurrentlySelected = true;

        ontologyTreePanel.removeOntologyTreeModel(ontology,
          wasCurrentlySelected);
        if(wasCurrentlySelected) if(ontologyCB.getItemCount() > 0) {
          ontologyCB.setSelectedIndex(0);
        }
      }
    }
    else {
      ontologies.add(ontology);
      ontologyCB.addItem(ontology.getName());
      if(ontologyCB.getItemCount() == 1) {
        ontologyTreePanel.currentOntologyTree.setVisible(true);
        ontologyCB.setSelectedIndex(0);
      }
    }
  }

  /**
   * Description: an internal OntologySelectionChangeAction used to handle the
   * ontology selection changes
   * 
   * @author Niraj Aswani
   * @version 1.0
   */
  private class OntologySelectionChangeAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      /** gets executed when user changes his/her selection of ontology */
      if(e.getSource() == ontologyCB) {
        int index = ontologyCB.getSelectedIndex();
        if(index >= 0) {
          ontologyTreePanel.showOntologyInOntologyTreeGUI(
            ontologies.get(index), className2AnnotationList);
        }
        else {
          ontologyTreePanel.showEmptyOntologyTree();
        }
        ontologyTreePanel.ontoTreeListener.refreshHighlights();
      }
    }
  }

  // ***************************
  /** CreoleListener methods */
  // ****************************
  /**
   * Gets executed whenever new resource is loaded in the GATE system
   * 
   * @param creoleEvent
   */
  public void resourceLoaded(CreoleEvent creoleEvent) {
    Resource rs = creoleEvent.getResource();
    if(rs instanceof Ontology) {
      ((Ontology)rs).addOntologyModificationListener(this);
      refreshOntologyCB((Ontology)rs, false);
    }
  }

  /**
   * Gets executed whenever existing resource is unloaded in the GATE system
   * 
   * @param creoleEvent
   */
  public void resourceUnloaded(CreoleEvent creoleEvent) {
    Resource rs = creoleEvent.getResource();
    if(rs instanceof Ontology) {
      // we are interested
      ((Ontology)rs).removeOntologyModificationListener(this);
      refreshOntologyCB((Ontology)rs, true);
    }
  }

  public void datastoreOpened(CreoleEvent creoleEvent) {
  }

  public void datastoreCreated(CreoleEvent creoleEvent) {
  }

  public void datastoreClosed(CreoleEvent creoleEvent) {
  }

  /**
   * Gets executed whenever resource name is changed
   * 
   * @param resource
   * @param string
   * @param string2
   */
  public void resourceRenamed(Resource resource, String string, String string2) {
    if(resource instanceof Ontology) {
      int index = ontologies.indexOf((Ontology)resource);
      ontologyCB.remove(index);
      ontologyCB.insertItemAt(((Ontology)resource).getName(), index);
      ontologyCB.invalidate();
    }
  }

  public void resourcesRemoved(Ontology ontology, String[] deletedResources) {
    boolean shouldSelectAgain = false;
    int index = ontologies.indexOf(ontology);
    if(index < 0) return;

    if(ontologyCB.getSelectedIndex() == index) {
      shouldSelectAgain = true;
    }
    HashMap<String, Boolean> selectionMap =
      ontologyTreePanel.ontology2OResourceSelectionMap.get(ontology);

    refreshOntologyCB(ontology, true);
    refreshOntologyCB(ontology, false);
    if(shouldSelectAgain) ontologyCB.setSelectedIndex(index);
    HashMap<String, Boolean> newMap =
      ontologyTreePanel.ontology2OResourceSelectionMap.get(ontology);
    for(String key : selectionMap.keySet()) {
      Boolean val = selectionMap.get(key);
      if(newMap.containsKey(key)) {
        newMap.put(key, val);
      }
    }

    documentTextArea.requestFocus();
  }

  public void resourceRelationChanged(Ontology ontology, OResource resource1,
    OResource resouce2, int eventType) {
    this.ontologyModified(ontology, resource1, eventType);
  }

  public void resourcePropertyValueChanged(Ontology ontology,
    OResource resource, RDFProperty property, Object value, int eventType) {
    this.ontologyModified(ontology, resource, eventType);
  }

  public void ontologyModified(Ontology ontology, OResource resource,
    int eventType) {
    if(eventType == OConstants.SUB_CLASS_ADDED_EVENT
      || eventType == OConstants.SUB_CLASS_REMOVED_EVENT) {
      ontologyReset(ontology);
    }
  }

  public void ontologyReset(Ontology ontology) {
    boolean shouldSelectAgain = false;
    int index = ontologies.indexOf(ontology);
    if(index < 0) return;

    initLocalData();
    if(ontologyCB.getSelectedIndex() == index) {
      shouldSelectAgain = true;
    }

    // lets traverse through the ontology and find out the classes which
    // are selected
    HashMap<String, Boolean> selectionMap =
      ontologyTreePanel.ontology2OResourceSelectionMap.get(ontology);
    refreshOntologyCB(ontology, true);
    refreshOntologyCB(ontology, false);
    if(shouldSelectAgain) ontologyCB.setSelectedIndex(index);
    HashMap<String, Boolean> newMap =
      ontologyTreePanel.ontology2OResourceSelectionMap.get(ontology);
    for(String key : selectionMap.keySet()) {
      Boolean val = selectionMap.get(key);
      if(newMap.containsKey(key)) {
        newMap.put(key, val);
      }
    }

    documentTextArea.requestFocus();

  }

  public void resourceAdded(Ontology ontology, OResource resource) {
    boolean shouldSelectAgain = false;
    int index = ontologies.indexOf(ontology);
    if(index < 0) return;
    if(ontologyCB.getSelectedIndex() == index) {
      shouldSelectAgain = true;
    }

    if(resource instanceof OInstance) {
      OInstance inst1 = (OInstance)resource;
      Set<OClass> oClasses = inst1.getOClasses(OConstants.Closure.DIRECT_CLOSURE);
      // for each class find out its class nodes
      for(OClass aClass : oClasses) {
        List<ClassNode> cnodes = ontologyTreePanel.getNode(aClass.getName());
        if(cnodes.isEmpty()) {
          continue;
        }

        for(ClassNode anode : cnodes) {
          ClassNode instNode = new ClassNode(inst1);
          anode.addSubNode(instNode);
          // here we need to set a color for this new instance
          ontologyTreePanel.setColorScheme(instNode,
            (HashMap<String, Color>)ontologyTreePanel.ontology2ColorSchemesMap
              .get(ontology));
          ontologyTreePanel
            .setOntoTreeClassSelection(
              instNode,
              (HashMap<String, Boolean>)ontologyTreePanel.ontology2OResourceSelectionMap
                .get(ontology));
        }
      }

      HashMap<String, Set<OClass>> map =
        ontologyTreePanel.ontology2PropValuesAndInstances2ClassesMap
          .get(ontology);
      Set<RDFProperty> rdfProps =
        ontologyTreePanel.ontology2PropertiesMap.get(ontology);
      ontologyTreePanel
        .updatePVnInst2ClassesMap(inst1, rdfProps, oClasses, map);
    }
    else {

      // lets traverse through the ontology and find out the classes
      // which
      // are selected
      HashMap<String, Boolean> selectionMap =
        ontologyTreePanel.ontology2OResourceSelectionMap.get(ontology);
      refreshOntologyCB(ontology, true);
      refreshOntologyCB(ontology, false);
      if(shouldSelectAgain) ontologyCB.setSelectedIndex(index);
      HashMap<String, Boolean> newMap =
        ontologyTreePanel.ontology2OResourceSelectionMap.get(ontology);
      for(String key : selectionMap.keySet()) {
        Boolean val = selectionMap.get(key);
        if(newMap.containsKey(key)) {
          newMap.put(key, val);
        }
      }
    }

    if(shouldSelectAgain) ontologyCB.setSelectedIndex(index);
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        ontologyTreePanel.currentOntologyTree.updateUI();
        documentTextArea.requestFocus();
      }
    });
  }

  /**
   * A method that returns the instance of ontology viewer options.
   * 
   * @return
   */
  public OntologyViewerOptions getOntologyViewerOptions() {
    /*
     * Do not delete this method, as it is used by Annotator GUI
     */

    return ontologyTreePanel.ontologyViewerOptions;
  }

}
