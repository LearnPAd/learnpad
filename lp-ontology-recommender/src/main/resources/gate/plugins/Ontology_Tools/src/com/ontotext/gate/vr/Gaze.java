/* Gaze.java
 * borislav popov
 */
package com.ontotext.gate.vr;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import gate.creole.*;
import gate.creole.gazetteer.*;
import gate.creole.ontology.*;
import gate.util.*;
import gate.event.GateEvent;
import gate.gui.MainFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

/** Gaze is a Gazetteer VR capable of viewing and editing
 *  gazetteer lists, linear definitions (lists.def files),
 *  and mapping definitions (mappings between ontology classes and gazetteer lists).
 *  I.e. capable of visualizing and editing both linear and ontology-aware gazetteers. */
public class Gaze extends AbstractVisualResource
  implements GazetteerListener, OntologyModificationListener {

  /** size x when running from the tools menu */
  public final static int SIZE_X = 700;
  /** size y when running from the tools menu */
  public final static int SIZE_Y = 500;
  /** positin x when running from the tools menu */
  public final static int POSITION_X = 300;
  /** positin y when running from the tools menu */
  public final static int POSITION_Y = 200;

  /* Linear Definition Actions */
  /** Edit Linear Node */
  private final static int LDA_EDIT = 1;
  /** Insert Linear Node */
  private final static int LDA_INSERT = 2;
  /** Remove Linear Node */
  private final static int LDA_REMOVE = 3;

  /** the target to be displayed */
  private Gazetteer target = null;

  /** is the target resource ontology-aware gazetteer */
  private boolean isOntoGaz = false;

  /** the linear definition being displayed */
  private LinearDefinition linear = null;

  /** the linear node currently selected */
  private LinearNode linearNode = null;

  /** the gazetteer list currently selected */
  private GazetteerList gazList = null;

  /** Set of all lists, both in the linear definition and
   *  explicitly loaded ones.*/
  private Set listSet = null;

  /** the mapping definition being displayed */
  private MappingDefinition mapping = null;

  /** the mapping node currently selected */
  private MappingNode mappingNode = null;

  /** the ontology that is currently displayed */
  private Ontology ontology = null;

  /** map of ontologies vs trees */
  private Map<Ontology, JTree> ontologyTrees = new HashMap<Ontology, JTree>();

  /*manually added gui components */

  /**Linear Definition Popup menu */
  protected JPopupMenu linearPopup = new JPopupMenu();
  /**Linear Definition Edit Popup Item*/
  protected JMenuItem linearPopupEdit;
  /**Linear Definition Insert Popup Item*/
  protected JMenuItem linearPopupInsert;
  /**Linear Definition Remove Popup Item*/
  protected JMenuItem linearPopupRemove;

  /*automatically added gui components */
  protected JMenuBar mainMenu = new JMenuBar();
  protected JMenu fileMenu = new JMenu();
  protected JMenu viewMenu = new JMenu();
  protected JSplitPane baseSplit = new JSplitPane();
  protected JSplitPane mappingSplit = new JSplitPane();
  protected JSplitPane linearSplit = new JSplitPane();
  protected JPanel linearPanel = new JPanel();
  protected JPanel listPanel = new JPanel();
  protected JPanel mappingPanel = new JPanel();
  protected JPanel ontologyPanel = new JPanel();
  protected JLabel linearLabel = new JLabel();
  protected GridBagLayout gridBagLayout1 = new GridBagLayout();
  protected JScrollPane linearScroll = new JScrollPane();
  protected JToolBar linearBar = new JToolBar();
  protected JButton btnLinearLoad = new JButton();
  protected JList linearList = new JList();
  protected JButton btnLinearSave = new JButton();
  protected JButton btnLinearSaveAs = new JButton();
  protected JLabel listLabel = new JLabel();
  protected GridBagLayout gridBagLayout2 = new GridBagLayout();
  protected JToolBar listBar = new JToolBar();
  protected JButton btnListLoad = new JButton();
  protected JScrollPane listScroll = new JScrollPane();
  protected JButton btnListSave = new JButton();
  protected JButton btnListSaveAs = new JButton();
  protected GridBagLayout gridBagLayout3 = new GridBagLayout();
  protected JLabel mappingLabel = new JLabel();
  protected JToolBar mappingBar = new JToolBar();
  protected JButton btnMappingLoad = new JButton();
  protected JScrollPane mappingScroll = new JScrollPane();
  protected JList mappingList = new JList();
  protected JButton btnMappingSave = new JButton();
  protected JButton btnMappingSaveAs = new JButton();
  protected JLabel ontologyLabel = new JLabel();
  protected JToolBar ontologyBar = new JToolBar();
  protected JButton btnOntologyLoad = new JButton();
  protected JScrollPane ontologyScroll = new JScrollPane();
  protected GridBagLayout gridBagLayout4 = new GridBagLayout();
  protected JMenu menuHelp = new JMenu();
  protected JMenuItem menuAbout = new JMenuItem();
  protected GridBagLayout thisLayout = new GridBagLayout();
  protected JMenu menuLinear = new JMenu();
  protected JMenuItem menuLinearLoad = new JMenuItem();
  protected JMenuItem menuLinearSave = new JMenuItem();
  protected JMenuItem menuLinearSaveAs = new JMenuItem();
  protected JMenu menuList = new JMenu();
  protected JMenuItem menuListLoad = new JMenuItem();
  protected JMenuItem menuListSave = new JMenuItem();
  protected JMenuItem menuListSaveAs = new JMenuItem();
  protected JMenu menuMapping = new JMenu();
  protected JMenuItem menuMappingLoad = new JMenuItem();
  protected JMenuItem menuMappingSave = new JMenuItem();
  protected JMenuItem menuMappingSaveAs = new JMenuItem();
  protected JMenu menuOntology = new JMenu();
  protected JMenuItem menuOntologyLoad = new JMenuItem();
  protected JMenuItem menuRefresh = new JMenuItem();
  protected JTree oTree = new JTree();
  protected JTextArea listArea = new JTextArea();
  protected JButton btnMappingNew = new JButton();
  protected JButton btnLinearNew = new JButton();
  protected JButton btnListNew = new JButton();
  protected JMenuItem menuLinearNew = new JMenuItem();
  protected JMenuItem menuListNew = new JMenuItem();
  protected JMenuItem menuMappingNew = new JMenuItem();
  protected JButton btnListSaveAll = new JButton();
  protected JMenuItem menuListSaveAll = new JMenuItem();


  public Gaze() {
    try {

      jbInit();
      /* add menu bar*/
      mainMenu.setMinimumSize(new Dimension(0,20));
      mainMenu.setMaximumSize(new Dimension(0,20));
      mainMenu.setPreferredSize(new Dimension(0,20));
      this.add(mainMenu,   new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER,
            GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0 ));

      /* make the ontology tree invisible because not initialized yet*/
      oTree.setVisible(false);

      /* create and associate linear listeners... */
      createLinearListeneres();

      /*...and non linear (mapping, ontology) listeners */
      createNonLinearListeners();

      /* associate the load,save,saveas buttons with action */
      createLinearDefBtnListeners();
      createGazListBtnListeners();
      createMappingDefBtnListeners();

      /* create a new cell renderer for the linear definition list */
      linearList.setCellRenderer(new LinearCR());

      /* add modifications listener over the gazetteer list text area */
      listArea.getDocument().addDocumentListener(new GazListDL());

      /* create Linear Definition Popup menu */
      linearPopupEdit = new JMenuItem("edit");
      linearPopupInsert = new JMenuItem("insert");
      linearPopupRemove = new JMenuItem("remove");

      linearPopup.add(linearPopupEdit);
      linearPopup.add(linearPopupInsert);
      linearPopup.add(linearPopupRemove);

      /* add popup listener */
      linearList.addMouseListener(new LinearPopupListener());


      /* add popup menu items' listeners*/
      linearPopupEdit.addActionListener(new LinearPopupEditListener());
      linearPopupRemove.addActionListener(new LinearPopupRemoveListener());
      linearPopupInsert.addActionListener(new LinearPopupInsertListener());
    } catch(Exception e) {
      e.printStackTrace(gate.util.Err.getPrintWriter());
    }
  }

  /**
   * Called by the GUI when this viewer/editor has to initialise itself for a
   * specific object. this is an {@link gate.creole.AbstractVisualResource} overriden method.
   * @param targeta the object (be it a {@link gate.Resource},
   * {@link gate.DataStore} or whatever) this viewer has to display
   */
  public void setTarget(Object targeta) {

    /*check the parameter*/
    if (null == targeta) {
      throw new GateRuntimeException("should not set null target.");
    }
    if (! (targeta instanceof Gazetteer) ) {
      throw new GateRuntimeException(
        "the target should impelement \n"+
        "gate.creole.gazetteer.Gazetteer. \n"+
        "target => "+targeta.getClass());
    }

    target = (Gazetteer)targeta;

    target.addGazetteerListener(this);

    /** determine the type of the target */
    isOntoGaz = (target instanceof OntoGazetteer);

    /**disable the ontology and mapping areas */
    if (!isOntoGaz) {
      mappingSplit.setVisible(false);
      mappingList.setEnabled(false);
    }

    /* display linear definition */
    displayLinear(target);

    /* display mapping */
    if (isOntoGaz)
      displayMapping(target);

  } // setTarget(Object)


  public gate.Resource init() throws ResourceInstantiationException {
    return this;
  }

  /** updates the mapping list's ui */
  void updateMappingUI(){
    if ( null!=mappingList ) {
      mappingList.setListData(mapping.toArray());
      mappingList.updateUI();
    }
  } // updateMappingUI()

  /** Displays the specified list in the most right pane of Gaze
   * @param listName the name of the list
   */
  void displayList(String listName) {
    // find the gazetteer list by list name
    Object node = linear.getNodesByListNames().get(listName);
    GazetteerList newList = null;
    if ( node != null )  {
      newList = (GazetteerList)linear.getListsByNode().get(node);
      if ( null != newList ){

        //retrieve the possible editions of the gazetteer
        if (null!=listArea && null!=gazList) {
          gazList.setMode(gazList.STRING_MODE);
          boolean mdfd = gazList.isModified();
          gazList.updateContent(listArea.getText());
          gazList.setModified(mdfd);
        }

        //show the newly selected list
        gazList = newList;
        if ( null!= gazList) {
          gazList.setMode(gazList.STRING_MODE);
          boolean mdfd = gazList.isModified();
          listArea.setText(gazList.toString());
          gazList.setModified(mdfd);
        } else {
          listArea.setText("");
        }
      }  // != null
    } // != null

  } // displayList(String)

  /**Gets the lists
   * @return a list of all the gaz lists known to this VR*/
  java.util.List getLists() {
    return linear.getLists();
  }

  /**Gets all classes.
   * @return a list of all the classes from all the ontologies known to this VR*/
  java.util.List getClasses() {
    java.util.List<OClass> result = null;
    if ( null == ontology)
      result = new ArrayList<OClass>();
    else {
      result = new ArrayList<OClass>(ontology.getOClasses(false));
    }
    return result;
  }



  /** Displays linear definition
   *  @param g the gazetteer to take the definition from */
  private void displayLinear(Gazetteer g) {
    // get the linear definition
    linear = g.getLinearDefinition();
    // check the linear definition
    if (null == linear)
      throw new GateRuntimeException(
        "linear definition of a gazetteer should not be null.");

    listSet = new HashSet(linear.getLists());

    if (null == listSet)
      throw new GateRuntimeException(
        "The set of Gazetteer Lists should not be null.");

    // set the list data with the nodes of the gaz
    linearList.setListData(new Vector(linear.getNodes()));
  } // displayLinear()

  /** Displays mapping
   *  @param g the gazetteer to take the mapping from */
  private void displayMapping(Gazetteer g) {
    mapping = g.getMappingDefinition();
    if (null == mapping)
      throw new GateRuntimeException(
        "the mapping definition of an onto gazetteer should not be null");
    mappingList.setListData(mapping.toArray());

    /*Add all lists present in the mapping to the set of loaded lists*/
    listSet.addAll(mapping.getLists());

  }// displayMapping()

  /**Creates and associates listeners for the linear gui components*/
  private void createLinearListeneres() {

    /* add list selection listener to the linear definition list component*/
    linearList.addListSelectionListener(
      new ListSelectionListener () {
        public void valueChanged(ListSelectionEvent e) {
          if (linearList.getAnchorSelectionIndex() < linearList.getModel().getSize()) {
            Object obj = linearList.getModel().getElementAt(
              linearList.getAnchorSelectionIndex());
            if ( obj instanceof LinearNode ) {
              linearNode = (LinearNode) obj;

              //retrieve the possible editions of the gazetteer
              if (null!=listArea && null!=gazList) {
                gazList.setMode(gazList.STRING_MODE);
                boolean mdfd = gazList.isModified();
                gazList.updateContent(listArea.getText());
                gazList.setModified(mdfd);
              }

              //show the newly selected list
              gazList = (GazetteerList)linear.getListsByNode().get(linearNode);
              if ( null!= gazList) {
                gazList.setMode(gazList.STRING_MODE);
                boolean mdfd = gazList.isModified();
                listArea.setText(gazList.toString());
                gazList.setModified(mdfd);
              } else {
                listArea.setText("");
              }


            } // only if linear node
          } // size > 0
        } // valueChanged();
      } );

  } // createLinearListeneres()

  /**Creates and asssociates listeners for the
   * non linear (mapping,ontology) gui components   */
  private void createNonLinearListeners() {

    /* add list selection listener to the mapping definition list component*/
    mappingList.addListSelectionListener(
      new ListSelectionListener () {
        @SuppressWarnings("deprecation")
        public void valueChanged(ListSelectionEvent e) {
          if (0 < mappingList.getModel().getSize()) {
            Object obj = mappingList.getModel().getElementAt(
              mappingList.getAnchorSelectionIndex());
            if ( obj instanceof MappingNode ) {
              mappingNode = (MappingNode) obj;
              URL ourl;
              try {
                ourl = new URL(mappingNode.getOntologyID());
              } catch  (MalformedURLException x) {
                throw new GateRuntimeException("Malformed URL:"
                  +mappingNode.getOntologyID());
              }
              // get te ontology
              try {
                ontology = OntologyUtilities.getOntology(ourl);
                ontology.addOntologyModificationListener(Gaze.this);
              } catch (ResourceInstantiationException x) {
                x.printStackTrace(Err.getPrintWriter());
              }
              if (null == ontology)
                throw new GateRuntimeException("can not retrieve ontology by url.\n"
                  +"ontology is null.\n"
                  +"url = "+ourl);

              // remove the old tree from the scroll pane
              if (null != oTree)
                ontologyScroll.getViewport().remove(oTree);

              // check if there is already a tree for this ontology
              oTree = (JTree) ontologyTrees.get(ontology);

              if (null == oTree) {
                Map namesVsNodes = new HashMap();
                ClassNode root = ClassNode.createRootNode(ontology,mapping,namesVsNodes);
                OntoTreeModel model = new OntoTreeModel(root);
                MappingTreeView view = new MappingTreeView(model,mapping,Gaze.this);
                oTree = view;
                ontologyTrees.put(ontology,oTree);
              } // ontology tree has not been previously creted

              ontologyScroll.getViewport().add(oTree,null);
              oTree.setVisible(true);

              displayList(mappingNode.getList());
            } // only if mapping node
          } // size > 0
        } // valueChanged();
      } );

  } // createNonLinearListeners()


  /**Sets the listeners for the load,save and save as
   * buttons in the linear definition pane */
  private void createLinearDefBtnListeners() {
    /* add a create/new action listener */
    btnLinearNew.addActionListener(new LinearNewListener());
    menuLinearNew.addActionListener(new LinearNewListener());

    /* add load action listener for the linear definition */
    btnLinearLoad.addActionListener(new LinearLoadListener());
    menuLinearLoad.addActionListener(new LinearLoadListener());

    /* add save as action listener for the linear definition */
    btnLinearSaveAs.addActionListener(new LinearSaveAsListener());
    menuLinearSaveAs.addActionListener(new LinearSaveAsListener());


    /* add save action listener for the linear definition */
    btnLinearSave.addActionListener(new LinearSaveListener());
    menuLinearSave.addActionListener(new LinearSaveListener());

  } // createLinearDefBtnListeners()

  /**Sets the listeners for the load,save and save as
   * buttons in the gazetteer list pane */
  private void createGazListBtnListeners() {

    /* add new action listener */
    btnListNew.addActionListener(new ListNewListener());
    menuListNew.addActionListener(new ListNewListener());

    /* add load action listener */
    btnListLoad.addActionListener(new ListLoadListener());
    menuListLoad.addActionListener(new ListLoadListener());

    /* add save as action listener */
    btnListSaveAs.addActionListener(new ListSaveAsListener());
    menuListSaveAs.addActionListener(new ListSaveAsListener());

    /* add save action listener */
    btnListSave.addActionListener(new ListSaveListener());
    menuListSave.addActionListener(new ListSaveListener());

    /* add save all action listener */
    btnListSaveAll.addActionListener(new ListSaveAllListener());
    menuListSaveAll.addActionListener(new ListSaveAllListener());
  } // createGazListBtnListeners()

  /**Sets the listeners for the load,save and save as
   * buttons in the mapping pane */
  private void createMappingDefBtnListeners() {

    /* add create new action listener */
    btnMappingNew.addActionListener(new MappingNewListener());
    menuMappingNew.addActionListener(new MappingNewListener());

    /* add load action listener */
    btnMappingLoad.addActionListener(new MappingLoadListener());
    menuMappingLoad.addActionListener(new MappingLoadListener());

    /* add save as action listen*/
    btnMappingSaveAs.addActionListener(new MappingSaveAsListener());
    menuMappingSaveAs.addActionListener(new MappingSaveAsListener());


    /* add save action listener */
    btnMappingSave.addActionListener(new MappingSaveListener());
    menuMappingSave.addActionListener(new MappingSaveListener());

    /* add load ontology action listener*/
    btnOntologyLoad.addActionListener(new OntologyLoadListener());
    menuOntologyLoad.addActionListener(new OntologyLoadListener());

  } // createLinearDefBtnListeners()

  /**
   * Performs an action over the Linear Definition.
   * e.g. edit,insert,remove Linear Node.
   * @param action the action to be performed
   * @param index index of the place where this action took place(e.g. where to insert)
   * @param node the Linear Node to be used in the action
   */
  private void performLinearAction(int action, int index, LinearNode node ) {
    switch (action) {
      case LDA_EDIT : {
        LinearNode bkp = linear.get(index);
        linear.remove(index);
        int size = linear.size();
        linear.add(index,node);
        if (size == linear.size()) {
          JOptionPane.showMessageDialog(
            this,
            "The Linear Node can not be added to the Linear Definition \n"
            +"because a node with such a list already exists,\n"+
            "cannot be opened, or cannot be created if non-existant.\n"
            +"node : "+node,
            "Edit Linear Node Failure",
            JOptionPane.ERROR_MESSAGE);
          //rollback
          linear.add(index,bkp);
        }// if

        break;
      }
      case LDA_INSERT : {
        int size = linear.size();
        if (index < 0 ) index = 0;
        linear.add(index,node);
        if (size == linear.size()) {
          JOptionPane.showMessageDialog(
            this,
            "The Linear Node can not be added to the Linear Definition \n"
            +"because a node with such a list already exists,\n"+
            "cannot be opened, or cannot be created if non-existant.\n"
            +"node : "+node,
            "Insert Linear Node Failure",
            JOptionPane.ERROR_MESSAGE);
        }// if
        break;
      }
      case LDA_REMOVE : {
        linear.remove(index);
        break;
      }
    } // switch action
    linearList.setListData(linear.toArray());
  } // performLinearAction(int,LinearNode)

  /** Reinitializes the edited gazetteer */
  private void reinitializeGazetteer() {
    try {
      target.setListsURL(linear.getURL());
      if (isOntoGaz) {
        ((OntoGazetteer)target).setMappingURL(mapping.getURL());
        gate.Factory.deleteResource(((OntoGazetteer)target).getGazetteer());
      } // if onto gaz
      target = (Gazetteer)target.init();
      JOptionPane.showMessageDialog(this,
          "Gazetteer Reinitialized.",
          "Reinitialize Gazetteer",
          JOptionPane.INFORMATION_MESSAGE);
    } catch(ResourceInstantiationException x) {
      JOptionPane.showMessageDialog(this,
        "Gazetteer can not be reinitialized.\n"+
        "due to:"+x.getClass()+" "+x.getMessage(),
        "Gazsetteer Reinitialize Failure.",JOptionPane.ERROR_MESSAGE);
    }
  } // reinitializeGazetteer()

  /** Init of the gui components */
  private void jbInit() throws Exception {
    fileMenu.setToolTipText("");
    fileMenu.setText("File");
    viewMenu.setText("View");
    this.setPreferredSize(new Dimension(600, 300));
    this.setLayout(thisLayout);
    baseSplit.setPreferredSize(new Dimension(700, 450));
    mappingSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
    mappingSplit.setToolTipText("");
    linearSplit.setContinuousLayout(true);
    linearLabel.setAlignmentY((float) 0.0);
    linearLabel.setToolTipText("");
    linearLabel.setHorizontalAlignment(SwingConstants.CENTER);
    linearLabel.setText("Linear Definition");
    linearPanel.setLayout(gridBagLayout1);
    linearScroll.setPreferredSize(new Dimension(100, 50));
    btnLinearLoad.setBorder(BorderFactory.createEtchedBorder());
    btnLinearLoad.setToolTipText("Load a linear definition");
    btnLinearLoad.setFocusPainted(false);
    btnLinearLoad.setMargin(new Insets(2, 2, 2, 2));
    btnLinearLoad.setText("Load");
    btnLinearSave.setBorder(BorderFactory.createEtchedBorder());
    btnLinearSave.setToolTipText("Save the linear definition");
    btnLinearSave.setFocusPainted(false);
    btnLinearSave.setMargin(new Insets(2, 2, 2, 2));
    btnLinearSave.setText("Save");
    btnLinearSaveAs.setBorder(BorderFactory.createEtchedBorder());
    btnLinearSaveAs.setToolTipText("Save the linear definition changing the location");
    btnLinearSaveAs.setFocusPainted(false);
    btnLinearSaveAs.setMargin(new Insets(2, 0, 2, 0));
    btnLinearSaveAs.setText("Save as...");
    listLabel.setAlignmentY((float) 0.0);
    listLabel.setHorizontalAlignment(SwingConstants.CENTER);
    listLabel.setText("Gazetteer List");
    listPanel.setLayout(gridBagLayout2);
    btnListLoad.setBorder(BorderFactory.createEtchedBorder());
    btnListLoad.setToolTipText("Load a gazetteer list");
    btnListLoad.setFocusPainted(false);
    btnListLoad.setMargin(new Insets(2, 0, 2, 0));
    btnListLoad.setText("Load");
    listScroll.setAlignmentX((float) 0.0);
    listScroll.setAlignmentY((float) 0.0);
    btnListSave.setBorder(BorderFactory.createEtchedBorder());
    btnListSave.setToolTipText("Save the gazetteer list");
    btnListSave.setFocusPainted(false);
    btnListSave.setMargin(new Insets(2, 0, 2, 0));
    btnListSave.setText("Save");
    btnListSaveAs.setBorder(BorderFactory.createEtchedBorder());
    btnListSaveAs.setToolTipText("Save the gazetteer list to different location");
    btnListSaveAs.setFocusPainted(false);
    btnListSaveAs.setMargin(new Insets(2, 0, 2, 0));
    btnListSaveAs.setText("Save as...");
    listBar.setFloatable(false);
    mappingPanel.setLayout(gridBagLayout3);
    mappingLabel.setHorizontalAlignment(SwingConstants.CENTER);
    mappingLabel.setText("Mapping Definition");
    btnMappingLoad.setBorder(BorderFactory.createEtchedBorder());
    btnMappingLoad.setToolTipText("Load a mapping definition");
    btnMappingLoad.setFocusPainted(false);
    btnMappingLoad.setMargin(new Insets(2, 0, 2, 0));
    btnMappingLoad.setText("Load");
    btnMappingSave.setBorder(BorderFactory.createEtchedBorder());
    btnMappingSave.setToolTipText("Save mapping definition");
    btnMappingSave.setFocusPainted(false);
    btnMappingSave.setMargin(new Insets(2, 0, 2, 0));
    btnMappingSave.setText("Save");
    btnMappingSaveAs.setBorder(BorderFactory.createEtchedBorder());
    btnMappingSaveAs.setToolTipText("Save mapping definition to another location");
    btnMappingSaveAs.setFocusPainted(false);
    btnMappingSaveAs.setMargin(new Insets(2, 0, 2, 0));
    btnMappingSaveAs.setText("Save As...");
    ontologyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    ontologyLabel.setText("Ontology");
    btnOntologyLoad.setBorder(BorderFactory.createEtchedBorder());
    btnOntologyLoad.setToolTipText("Load an ontology");
    btnOntologyLoad.setFocusPainted(false);
    btnOntologyLoad.setMargin(new Insets(2, 2, 2, 2));
    btnOntologyLoad.setText("Load");
    ontologyPanel.setLayout(gridBagLayout4);
    mappingBar.setFloatable(false);
    ontologyBar.setFloatable(false);
    linearBar.setFloatable(false);
    menuHelp.setText("Help");
    menuAbout.setText("About");
    menuLinear.setText("Linear Definition");
    menuLinearLoad.setText("Load");
    menuLinearSave.setText("Save");
    menuLinearSaveAs.setText("Save as");
    menuList.setText("Gazetteer List");
    menuListLoad.setText("Load");
    menuListSave.setText("Save");
    menuListSaveAs.setText("Save as");
    menuMapping.setText("Mapping Definition");
    menuMappingLoad.setText("Load");
    menuMappingSave.setText("Save");
    menuMappingSaveAs.setText("Save as");
    menuOntology.setText("Ontology");
    menuOntologyLoad.setText("Load");
    menuRefresh.setText("Refresh");
    mainMenu.setBorder(BorderFactory.createEtchedBorder());
    oTree.setToolTipText("");
    btnMappingNew.setText("New");
    btnMappingNew.setMargin(new Insets(2, 0, 2, 0));
    btnMappingNew.setFocusPainted(false);
    btnMappingNew.setToolTipText("Create a New Mapping Definition");
    btnMappingNew.setBorder(BorderFactory.createEtchedBorder());
    btnLinearNew.setText("New");
    btnLinearNew.setMargin(new Insets(2, 2, 2, 2));
    btnLinearNew.setFocusPainted(false);
    btnLinearNew.setToolTipText("Create a New Linear Definition");
    btnLinearNew.setBorder(BorderFactory.createEtchedBorder());
    btnListNew.setText("New");
    btnListNew.setMargin(new Insets(2, 0, 2, 0));
    btnListNew.setFocusPainted(false);
    btnListNew.setToolTipText("Create a New Gazetteer List");
    btnListNew.setBorder(BorderFactory.createEtchedBorder());
    menuLinearNew.setText("New");
    menuListNew.setText("New");
    menuMappingNew.setText("New");
    btnListSaveAll.setText("Save All");
    btnListSaveAll.setMargin(new Insets(2, 0, 2, 0));
    btnListSaveAll.setFocusPainted(false);
    btnListSaveAll.setToolTipText("Save all modified gazetteer lists ");
    btnListSaveAll.setBorder(BorderFactory.createEtchedBorder());
    menuListSaveAll.setToolTipText("Save All Modified Gazetteer Lists");
    menuListSaveAll.setText("Save All");
    listBar.add(btnListNew, null);
    linearBar.add(btnLinearNew, null);
    mainMenu.add(fileMenu);
    mainMenu.add(viewMenu);
    mainMenu.add(menuHelp);
    this.add(baseSplit,   new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 48, 64));
    baseSplit.add(mappingSplit, JSplitPane.LEFT);
    baseSplit.add(linearSplit, JSplitPane.RIGHT);
    linearSplit.add(linearPanel, JSplitPane.TOP);
    linearSplit.add(listPanel, JSplitPane.BOTTOM);
    listPanel.add(listLabel,                           new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    mappingSplit.add(mappingPanel, JSplitPane.BOTTOM);
    mappingSplit.add(ontologyPanel, JSplitPane.TOP);
    ontologyPanel.add(ontologyLabel,    new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    ontologyPanel.add(ontologyBar,   new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    ontologyPanel.add(ontologyScroll,   new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    ontologyScroll.getViewport().add(oTree, null);
    ontologyBar.add(btnOntologyLoad, null);
    linearPanel.add(linearBar,      new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    linearBar.add(btnLinearLoad, null);
    linearBar.add(btnLinearSave, null);
    linearBar.add(btnLinearSaveAs, null);
    linearPanel.add(linearScroll,       new GridBagConstraints(0, 2, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    linearScroll.getViewport().add(linearList, null);
    linearPanel.add(linearLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    listPanel.add(listBar,         new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    listPanel.add(listScroll,       new GridBagConstraints(0, 2, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    listScroll.getViewport().add(listArea, null);
    listBar.add(btnListLoad, null);
    listBar.add(btnListSave, null);
    listBar.add(btnListSaveAs, null);
    listBar.add(btnListSaveAll, null);
    mappingPanel.add(mappingLabel,  new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    mappingPanel.add(mappingBar,   new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    mappingPanel.add(mappingScroll,  new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    mappingScroll.getViewport().add(mappingList, null);
    mappingBar.add(btnMappingNew, null);
    mappingBar.add(btnMappingLoad, null);
    mappingBar.add(btnMappingSave, null);
    mappingBar.add(btnMappingSaveAs, null);
    menuHelp.add(menuAbout);
    fileMenu.add(menuLinear);
    fileMenu.add(menuList);
    fileMenu.addSeparator();
    fileMenu.add(menuMapping);
    fileMenu.add(menuOntology);
    menuLinear.add(menuLinearNew);
    menuLinear.add(menuLinearLoad);
    menuLinear.add(menuLinearSave);
    menuLinear.add(menuLinearSaveAs);
    menuList.add(menuListNew);
    menuList.add(menuListLoad);
    menuList.add(menuListSave);
    menuList.add(menuListSaveAs);
    menuList.add(menuListSaveAll);
    menuMapping.add(menuMappingNew);
    menuMapping.add(menuMappingLoad);
    menuMapping.add(menuMappingSave);
    menuMapping.add(menuMappingSaveAs);
    menuOntology.add(menuOntologyLoad);
    viewMenu.add(menuRefresh);
    mappingSplit.setDividerLocation(200);
    linearSplit.setDividerLocation(230);
    baseSplit.setDividerLocation(300);
  } // jbInit()

/*---------implementation of GazetteerListener interface--------------*/
  public void processGazetteerEvent(GazetteerEvent e) {
    if ( e.REINIT == e.getType() ) {
      displayLinear((Gazetteer)e.getSource());
      if (isOntoGaz) {
        displayMapping((Gazetteer)e.getSource());
        ontologyTrees = new HashMap();
        oTree.setVisible(false);
      }
    } // reinit
  } // processGazetteerEvent(GazetteerEvent)
/*---------implementation of GazetteerListener interface--------------*/

/*->->->---implementation of OntologyModificationListener interface--------------*/
  public void processGateEvent(GateEvent e) {
  }

  public void resourceAdded(Ontology ontology, OResource resource) {
    ontologyModified(ontology, null, -1);
  }
  
  public void resourcesRemoved(Ontology ontology, String[] resourcesURIs) {
    ontologyModified(ontology, null, -1);
  }
  
  public void ontologyReset(Ontology ontology) {
      ontologyModified(ontology, null, -1);
  }
  
  public void resourceRelationChanged(Ontology ontology, OResource resource1, OResource resouce2, int eventType) {
    this.ontologyModified(ontology, resource1, eventType);
  }
  
  public void resourcePropertyValueChanged(Ontology ontology, OResource resource, RDFProperty property, Object value, int eventType) {
    this.ontologyModified(ontology, resource, eventType);
  }
  
  public void ontologyModified(Ontology ontology, OResource resource, int eventType) {
      JTree tree = ontologyTrees.get(ontology);
      if (tree!=null) {
        ontologyTrees.remove(ontology);
        Map<String, ClassNode> namesVsNodes = new HashMap<String, ClassNode>();
        ClassNode root = ClassNode.createRootNode(ontology,mapping,namesVsNodes);
        OntoTreeModel model = new OntoTreeModel(root);
        MappingTreeView view = new MappingTreeView(model,mapping,Gaze.this);

        /* synchronize the expansion of the old and new trees */
        synchronizeTreeExpansion(tree,view);

        if (ontology.equals(ontology)) {
          oTree = view;
          ontologyScroll.getViewport().add(oTree,null);
          oTree.setVisible(true);
        }
        ontologyTrees.put(ontology,oTree);
      }
  }

  /**
   * Synchronizes the expansion of the given trees.
   * @param orig the original tree
   * @param mirror the tree to mimic the expansion of the original
   */
  public static void synchronizeTreeExpansion(JTree orig, JTree mirror) {
    /*create a Set of expanded node names*/
    /*below will :
      iterate all nodes of the tree
      accumulate the path for each node as an arraylist
      check for each passed node whether the treepath is expanded
      and if expanded add it to the expanded list as a string.
    */
    Set expanded = new HashSet();
    TreeModel model =  orig.getModel();

    ArrayList remains = new ArrayList();
    ArrayList remainPaths = new ArrayList();

    remains.add(model.getRoot());
    ArrayList rootPath = new ArrayList();
    rootPath.add(model.getRoot());
    remainPaths.add(rootPath);

    while (remains.size() > 0 ) {
      Object node = remains.get(0);
      int cc = model.getChildCount(node);
      ArrayList parentPath = (ArrayList)remainPaths.get(0);
      for ( int c = 0 ; c < cc ; c++) {
        Object child = model.getChild(node,c);
        remains.add(child);
        ArrayList pp = new ArrayList(parentPath);
        pp.add(child);
        remainPaths.add(pp);
      }
      TreePath tp = new TreePath(parentPath.toArray());
      if (orig.isExpanded(tp)) {
        expanded.add(node.toString());
      }
      remains.remove(0);
      remainPaths.remove(0);
    }

    /*expand the mirror tree according to the expanded nodes set*/
    /*
      iterate all the nodes and keep their paths
      if a node is found as a string then expand it
    */

    remains = new ArrayList();
    remainPaths = new ArrayList();

    model = mirror.getModel();
    remains.add(model.getRoot());
    rootPath = new ArrayList();
    rootPath.add(model.getRoot());
    remainPaths.add(rootPath);

    while (remains.size() > 0 ) {
      Object node = remains.get(0);
      int cc = model.getChildCount(node);
      ArrayList parentPath = (ArrayList)remainPaths.get(0);
      for ( int c = 0 ; c < cc ; c++) {
        Object child = model.getChild(node,c);
        remains.add(child);
        ArrayList pp = new ArrayList(parentPath);
        pp.add(child);
        remainPaths.add(pp);
      }

      if (expanded.contains(node.toString()) ) {
        TreePath tp = new TreePath(parentPath.toArray());
        mirror.expandPath(tp);
      }
      remains.remove(0);
      remainPaths.remove(0);
    } // while nodes remain

  } // synchronizeTreeExpansion(JTree,JTree)
  
  
/*-<-<-<---implementation of OntologyModificationListener interface--------------*/


  /* --- inner classes ----*/

  /** Creates a list cell renderer for the
   *  Linear Definition list. It should make
   *  distinct the modifed gazetteer lists and still
   *  look like the default one.*/
  class LinearCR extends DefaultListCellRenderer {

    private static final long serialVersionUID = 3690752878255943737L;

    public LinearCR() {
      super();
    }

    public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus)
    {
      super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
      GazetteerList gl = (GazetteerList)linear.getListsByNode().get(value);
      if ( null!= gl && gl.isModified()) {
        setBackground(list.getBackground());
        setForeground(Color.red);
      } // is modified
      return this;
    }// getListCellRendererComponent()
  } // class linearCR

  /** Gazetteer List Document Listener is used to monitor the
   * gaz list changes and alter the isModified flag of the current list.*/
  class GazListDL implements DocumentListener {

    public void changedUpdate(DocumentEvent ev) {
        gazList.setModified(true);
      }

      public void insertUpdate(DocumentEvent ev) {
        gazList.setModified(true);
      }

      public void removeUpdate(DocumentEvent ev) {
        gazList.setModified(true);
      }
  } // class GazListDL

  /** Reacts on all New Linear Definition actions performed either
   *  through the menu, wither through the new buton. */
  class LinearNewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = MainFrame.getFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

          int result = chooser.showDialog(Gaze.this, "New");
          if ( result == JFileChooser.APPROVE_OPTION ) {
            File selected = chooser.getSelectedFile();
            try {
              if (!selected.createNewFile()){
                JOptionPane.showMessageDialog(
                  Gaze.this,
                  "Cannot Create Linear Definition\n"+
                  selected.getAbsolutePath(),
                  "Linear Definition Create Failure",
                  JOptionPane.ERROR_MESSAGE
                );
              } // if
              URL lurl = new URL("file:///"+selected.getAbsolutePath());
              linear = new LinearDefinition();
              linear.setURL(lurl);
              linear.setEncoding(target.getEncoding());
              linear.load();

              // get the new list set
              listSet = new HashSet(linear.getLists());

              if (null == listSet)
                throw new GateRuntimeException(
                  "The set of Gazetteer Lists should not be null.");

              // set the list data with the nodes of the gaz
              linearList.setListData(new Vector(linear.getNodes()));


              JOptionPane.showMessageDialog(
                Gaze.this,
                "New Linear Definition created successfully \n"+
                selected.getAbsolutePath(),
                "Create New Linear Definition Successful",
                JOptionPane.INFORMATION_MESSAGE
              );

            } catch (ResourceInstantiationException x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load linear definition (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\nResourceInstantiationException:"+x.getMessage()
                ,"Linear Definition Load Failure",
                JOptionPane.ERROR_MESSAGE);
            } catch (Exception x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load linear definition (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\n"+x.getClass()+":"+x.getMessage()
                ,"Linear Definition Load Failure",
                JOptionPane.ERROR_MESSAGE);
            }
          } // approve
        } // actionPerformed(ActionEvent)
    } // class LinearLoadListener

  /** Reacts on all Load Linear Definition actions performed either
   *  through the menu, wither through the load buton. */
  class LinearLoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = MainFrame.getFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

          int result = chooser.showOpenDialog(Gaze.this);
          if ( result == JFileChooser.APPROVE_OPTION ) {
            File selected = chooser.getSelectedFile();
            try {
              URL lurl = new URL("file:///"+selected.getAbsolutePath());
              linear = new LinearDefinition();
              linear.setURL(lurl);
              linear.setEncoding(target.getEncoding());
              linear.load();

              // get the new list set
              listSet = new HashSet(linear.getLists());

              if (null == listSet)
                throw new GateRuntimeException(
                  "The set of Gazetteer Lists should not be null.");

              // set the list data with the nodes of the gaz
              linearList.setListData(new Vector(linear.getNodes()));


              reinitializeGazetteer();
            } catch (ResourceInstantiationException x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load linear definition (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\nResourceInstantiationException:"+x.getMessage()
                ,"Linear Definition Load Failure",
                JOptionPane.ERROR_MESSAGE);
            } catch (Exception x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load linear definition (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\n"+x.getClass()+":"+x.getMessage()
                ,"Linear Definition Load Failure",
                JOptionPane.ERROR_MESSAGE);
            }
          } // approve
        } // actionPerformed(ActionEvent)
    } // class LinearLoadListener

    /** Reacts on all Save As Linear Definition actions. */
    class LinearSaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          if ( null == linear ) {
            JOptionPane.showMessageDialog(
              Gaze.this,"The linear definition is null and cannot be saved.",
              "Linear Definition Save As Failure.",JOptionPane.ERROR_MESSAGE);
          } else {
            JFileChooser chooser = MainFrame.getFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            int result = chooser.showSaveDialog(Gaze.this);
            if ( result == JFileChooser.APPROVE_OPTION ) {
              File selected = chooser.getSelectedFile();
              URL lurl;
              try {
                lurl = new URL("file:///"+selected.getAbsolutePath());
                linear.setURL(lurl);
                linear.store();
                JOptionPane.showMessageDialog(
                  Gaze.this,
                  "Linear Definition saved sucessfuly.\n"
                  +lurl,
                  "Linear Definition Save As",
                  JOptionPane.PLAIN_MESSAGE);
                reinitializeGazetteer();
              } catch (MalformedURLException x) {
                JOptionPane.showMessageDialog(Gaze.this,"Cannot save linear definition.\n"
                  +"Due to "+x.getClass()+":"+x.getMessage(),
                  "Linear Definition Save As failure",JOptionPane.ERROR_MESSAGE);
              } catch (ResourceInstantiationException x) {
                JOptionPane.showMessageDialog(
                  Gaze.this,
                  "Unable to save the linear defintion.\n"
                  +"Due to : "+x.getClass()+":"+x.getMessage(),
                  "Linear Definition Save failure.",
                  JOptionPane.ERROR_MESSAGE);
              } // catch
            } // approved
          } // else
        }
    }// class LinearSaveListener

  /** Reacts on all Linear Definition  Save As events */
  class LinearSaveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if ( null == linear ) {
        JOptionPane.showMessageDialog(
          Gaze.this,"The Linear Definition is null and cannot be saved.",
          "Linear Definition Save failure.",JOptionPane.ERROR_MESSAGE);
      } else {

        try {
          linear.store();
          JOptionPane.showMessageDialog(
            Gaze.this,
            "Linear Definition saved sucessfuly.\n"+
            linear.getURL(),
            "Linear Definition Save",
            JOptionPane.PLAIN_MESSAGE);

          reinitializeGazetteer();

        } catch (ResourceInstantiationException x) {
          JOptionPane.showMessageDialog(
            Gaze.this,
            "Unable to save the Linear Definition.\n"
            +"Due to : "+x.getClass()+":"+x.getMessage(),
            "Linear Definition Save failure.",
            JOptionPane.ERROR_MESSAGE);
        } // catch
      } // else
    } // actionPerformed(ActionEvent)
  } // class LinearSaveListener

  /**Reacts on all Create New Gaz List Events */
  class ListNewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = MainFrame.getFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

          int result = chooser.showDialog(Gaze.this, "New");
          if ( result == JFileChooser.APPROVE_OPTION ) {
            File selected = chooser.getSelectedFile();
            try {
              if (!selected.createNewFile()){
                JOptionPane.showMessageDialog(
                  Gaze.this,
                  "Cannot Create Gazetteer List.\n"+
                  selected.getAbsolutePath(),
                  "Gazetteer List Create Failure",
                  JOptionPane.ERROR_MESSAGE
                );
              } // if
              URL lurl = new URL("file:///"+selected.getAbsolutePath());
              gazList = new GazetteerList();
              gazList.setURL(lurl);
              gazList.load();
              gazList.setMode(gazList.STRING_MODE);
              // set the list data with the nodes of the gaz
              listArea.setText(gazList.toString());
              gazList.setModified(false);

              String lName = gazList.getURL().getFile();
              int slash = lName.lastIndexOf('/');
              lName = lName.substring(slash+1);
              listSet.add(lName);

            } catch (ResourceInstantiationException x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load Gazetteer List (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\nResourceInstantiationException:"+x.getMessage()
                ,"Gazetteer List Load Failure",
                JOptionPane.ERROR_MESSAGE);
            } catch (Exception x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load Gazetteer List (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\n"+x.getClass()+":"+x.getMessage()
                ,"Gazetteer List Load Failure",
                JOptionPane.ERROR_MESSAGE);
            }
          } // approve
        } // actionPerformed(ActionEvent)
    } // class ListNewListener


  /**Reacts on all Gaz List Load Events */
  class ListLoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = MainFrame.getFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

          int result = chooser.showOpenDialog(Gaze.this);
          if ( result == JFileChooser.APPROVE_OPTION ) {
            File selected = chooser.getSelectedFile();
            try {
              URL lurl = new URL("file:///"+selected.getAbsolutePath());
              gazList = new GazetteerList();
              gazList.setURL(lurl);
              gazList.load();
              gazList.setMode(gazList.STRING_MODE);

              listArea.setText(gazList.toString());

              gazList.setModified(false);

              String lName = gazList.getURL().getFile();
              int slash = lName.lastIndexOf('/');
              lName = lName.substring(slash+1);
              listSet.add(lName);

            } catch (ResourceInstantiationException x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load Gazetteer List (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\nResourceInstantiationException:"+x.getMessage()
                ,"Gazetteer List Load Failure",
                JOptionPane.ERROR_MESSAGE);
            } catch (Exception x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load Gazetteer List (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\n"+x.getClass()+":"+x.getMessage()
                ,"Gazetteer List Load Failure",
                JOptionPane.ERROR_MESSAGE);
            }
          } // approve
        } // actionPerformed(ActionEvent)
    } // class ListLoadListener

    /** Gazetteer list Save As action listener */
    class ListSaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          if ( null == gazList ) {
            JOptionPane.showMessageDialog(
              Gaze.this,"The Gazetteer List is null and cannot be saved.",
              "Gazetteer List Save failure.",JOptionPane.ERROR_MESSAGE);
          } else {
            JFileChooser chooser = MainFrame.getFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            int result = chooser.showSaveDialog(Gaze.this);
            if ( result == JFileChooser.APPROVE_OPTION ) {
              File selected = chooser.getSelectedFile();
              URL lurl;
              try {
                lurl = new URL("file:///"+selected.getAbsolutePath());
                gazList.setURL(lurl);
                gazList.updateContent(listArea.getText());
                gazList.setMode(gazList.LIST_MODE);
                gazList.store();
                JOptionPane.showMessageDialog(
                  Gaze.this,
                  "Gazetteer List saved sucessfuly.\n"
                  +lurl,
                  "Gazetteer List Save As",
                  JOptionPane.PLAIN_MESSAGE);

                reinitializeGazetteer();

              } catch (MalformedURLException x) {
                JOptionPane.showMessageDialog(Gaze.this,"Cannot save Gazetteer List.\n"
                  +"Due to "+x.getClass()+":"+x.getMessage(),
                  "Gazetteer List Save As failure",JOptionPane.ERROR_MESSAGE);
              } catch (ResourceInstantiationException x) {
                JOptionPane.showMessageDialog(
                  Gaze.this,
                  "Unable to save the Gazetteer List.\n"
                  +"Due to : "+x.getClass()+":"+x.getMessage(),
                  "Gazetteer List save failure.",
                  JOptionPane.ERROR_MESSAGE);
              } // catch
            } // approved
          } // else
        }
    }//class ListSaveAsListener

    /** Gaz List Save Action Listener */
    class ListSaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          if ( null == gazList ) {
            JOptionPane.showMessageDialog(
              Gaze.this,"The Gazetteer List is null and cannot be saved.",
              "Gazetteer List Save failure.",JOptionPane.ERROR_MESSAGE);
          } else {

            try {
              gazList.updateContent(listArea.getText());
              gazList.setMode(gazList.LIST_MODE);
              gazList.store();
              JOptionPane.showMessageDialog(
                Gaze.this,
                "Gazetteer List saved sucessfully.\n"
                +gazList.getURL(),
                "Gazetteer List Save",
                JOptionPane.PLAIN_MESSAGE);

              reinitializeGazetteer();

            } catch (ResourceInstantiationException x) {
              JOptionPane.showMessageDialog(
                Gaze.this,
                "Unable to save the Gazetteer List.\n"
                +"Due to : "+x.getClass()+":"+x.getMessage(),
                "Gazetteer List Save failure.",
                JOptionPane.ERROR_MESSAGE);
            } // catch
          } // else
        }
    }//gaz list save action listener

    /** Gaz List Save All Action Listener */
    class ListSaveAllListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
              if (null!=gazList && null!=listArea) {
                boolean mdf = gazList.isModified();
                gazList.updateContent(listArea.getText());
                gazList.setMode(gazList.LIST_MODE);
                gazList.setModified(mdf);
              }

              GazetteerList gl = null ;
              StringBuffer allListsStr = new StringBuffer();
              boolean totalSuccess = true;
              boolean totalFailure = true;
              boolean anythingHappened = false;

              LinearNode node = null;

              for ( int i = 0 ; i < linear.size() ; i++ ) {
                node = (LinearNode)linear.get(i);
                gl = (GazetteerList)linear.getListsByNode().get(node);
                try {
                  if (gl.isModified()) {
                    anythingHappened = true;
                    gl.setMode(gl.LIST_MODE);
                    gl.store();
                    allListsStr.append("\nSAVED : "+
                      node.getList());
                    totalFailure = false;
                  }
                } catch (ResourceInstantiationException x ) {
                  allListsStr.append("\nFAILED : ");
                  allListsStr.append(node.getList());
                  totalSuccess = false;
                }
              }//for

              String msg = null;
              if (!anythingHappened) {
                msg = "There were no modified Gazetteer Lists to be saved.\n";
              } else {
                if (totalFailure) {
                  msg = "Not even one modified Gazetteer List was saved.\n";
                } else {
                  if (totalSuccess) {
                    msg = "All Modified Gazetteer Lists saved sucessfuly.\n";
                  } else {
                    msg = "Some of the Modified Gazetteer Lists were saved sucessfuly.\n";
                  } // else
                } // else
              } //else
              JOptionPane.showMessageDialog(
                Gaze.this,
                msg+allListsStr,
                "Gazetteer List Save All",
                JOptionPane.PLAIN_MESSAGE);

              reinitializeGazetteer();
        }
    }//gaz list save all action listener

  /** Listener for right click on the Linear Definition list */
  class LinearPopupListener extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      if(SwingUtilities.isRightMouseButton(e)){
        /* invoke popup*/
        linearPopup.show(linearList,e.getX(),e.getY());
      } // if right button
    } // mouse clicked
  } // class LinearPopupListener

  /**Listener for the Edit action of the LinearDefinition popup*/
  class LinearPopupEditListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      LinearNode lnode =
        (LinearNode) linearList.getSelectedValue();

      Vector lists = new Vector(listSet);
      Vector majors = new Vector(linear.getMajors());
      Vector minors = new Vector(linear.getMinors());
      Vector languages = new Vector(linear.getLanguages());

      Collections.sort(lists);
      Collections.sort(majors);
      Collections.sort(minors);
      Collections.sort(languages);

      LinearNodeInput dialog =
        new LinearNodeInput(
          LDA_EDIT,
          linearList.getSelectedIndex(),
          lists,
          majors,
          minors,
          languages,
          lnode.getList(),
          lnode.getMajorType(),
          lnode.getMinorType(),
          lnode.getLanguage());
      dialog.setTitle("Edit Linear Node");
      dialog.setLocationRelativeTo(linearLabel);
      dialog.setResizable(false);
      dialog.setVisible(true);
    } // actionPerformed
  } // class LinearPopupEditListener

  /**Listener for the Insert action of the LinearDefinition popup*/
  class LinearPopupInsertListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {

      Vector lists = new Vector(listSet);
      Vector majors = new Vector(linear.getMajors());
      Vector minors = new Vector(linear.getMinors());
      Vector languages = new Vector(linear.getLanguages());

      Collections.sort(lists);
      Collections.sort(majors);
      Collections.sort(minors);
      Collections.sort(languages);

      LinearNodeInput dialog =
        new LinearNodeInput(
          LDA_INSERT,
          linearList.getSelectedIndex(),
          lists,
          majors,
          minors,
          languages);

      dialog.setTitle("Insert Linear Node");
      dialog.setLocationRelativeTo(linearLabel);
      dialog.setResizable(false);
      dialog.setVisible(true);
    } // actionPerformed
  } // class LinearPopupInsertListener

  /**Listener for the Remove action of the LinearDefinition popup*/
  class LinearPopupRemoveListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      int [] indices = linearList.getSelectedIndices();
      for ( int i = (indices.length-1) ; i > -1 ; i-- ){
        linear.remove(indices[i]);
      } // for
      linearList.setListData(linear.toArray());
    }
  } // class LinearPopupRemoveListener

  /**A dialog for input of a LinearNode. */
  class LinearNodeInput extends JDialog {
    /** the action that has been performed */
    private int action = -1;
    /** the position at which the action has been performed */
    private int position = -1;

    protected JPanel jPanel1 = new JPanel();
    protected JLabel jLabel1 = new JLabel();
    protected JComboBox listCombo = new JComboBox();
    protected JLabel jLabel2 = new JLabel();
    protected JComboBox majorCombo = new JComboBox();
    protected JLabel jLabel3 = new JLabel();
    protected JComboBox minorCombo = new JComboBox();
    protected JLabel jLabel4 = new JLabel();
    protected JComboBox languagesCombo = new JComboBox();
    protected JLabel jLabel5 = new JLabel();
    protected JLabel jLabel6 = new JLabel();
    protected GridBagLayout gridBagLayout1 = new GridBagLayout();
    protected JButton btnOk = new JButton();
    protected JButton btnCancel = new JButton();

    /** default constructor
     * @param anAction one of a set of predefined actions
     * @param pos the position/index where the action occured
     *   */
    public LinearNodeInput(int anAction, int pos) {
      try {
        action = anAction;
        position = pos;
        jbInit();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    } // default constructor

    /** Construct with providing the combobox lists
     * @param anAction one of a set of predefined actions
     * @param pos the position/index where the action occured
     * @param lists the lists to be loaded in the lists combo box
     * @param majors the major types to be loaded in the major type combo box
     * @param minors the minor types to be loaded in the minor type combo box
     * @param languages the languages to be loaded in the languages combo box*/
    public LinearNodeInput(int anAction,int pos,Vector lists, Vector majors,
                            Vector minors, Vector languages)
    {
      try {
        action = anAction;
        position = pos;
        if (null!=lists)
          listCombo = new JComboBox(lists);

        if (null!=majors)
          majorCombo = new JComboBox(majors);

        if (null!=minors)
          minorCombo = new JComboBox(minors);

        if (null!=languages)
          languagesCombo = new JComboBox(languages);

        jbInit();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    } // constructor with combo lists

    /** Construct with providing the combobox lists and the current values of the members
     * @param anAction one of a set of predefined actions
     * @param pos the position/index where the action occured
     * @param lists the lists to be loaded in the lists combo box
     * @param majors the major types to be loaded in the major type combo box
     * @param minors the minor types to be loaded in the minor type combo box
     * @param languagesList the languages to be loaded in the languages combo box
     */
    public LinearNodeInput(int anAction,int pos,
                    Vector lists, Vector majors, Vector minors,
                    Vector languagesList,
                    String list,String major, String minor, String languages)
    {
      try {
        action = anAction;
        position = pos;
        if (null!=lists)
          listCombo = new JComboBox(lists);

        if (null!=majors)
          majorCombo = new JComboBox(majors);

        if (null!=minors)
          minorCombo = new JComboBox(minors);

        if (null!=languagesList)
          languagesCombo = new JComboBox(languagesList);

        if (null!=list)
          listCombo.setSelectedItem(list);


        if (null!=major)
          majorCombo.setSelectedItem(major);

        if (null!=minor)
          minorCombo.setSelectedItem(minor);
        else
          minorCombo.setSelectedItem("");

        if (null!=languages)
          languagesCombo.setSelectedItem(languages);
        else
          languagesCombo.setSelectedItem("");

        jbInit();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    } // constructor with combo lists

    private void jbInit() throws Exception {
      jLabel1.setAlignmentX((float) 0.5);
      jLabel1.setAlignmentY((float) 0.0);
      jLabel1.setText("Gazetteer List*");
      jPanel1.setLayout(gridBagLayout1);
      jLabel2.setText("Major Type*");
      jLabel3.setText("Minor Type");
      jLabel4.setText("Languages (comma-separated)");
      jLabel5.setText("Select, enter or alter the members of the Linear Node");
      jLabel6.setToolTipText("");
      jLabel6.setText("The members marked with \"*\" are mandatory.");
      btnOk.setText("OK");
      btnCancel.setText("Cancel");
      listCombo.setEditable(true);
      majorCombo.setEditable(true);
      minorCombo.setEditable(true);
      languagesCombo.setEditable(true);
      this.getContentPane().add(jPanel1,  BorderLayout.CENTER);
      jPanel1.add(jLabel5,           new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
              ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 10, 0), 22, 0));
      jPanel1.add(jLabel1,       new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0
              ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
      jPanel1.add(listCombo,         new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0
              ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 60), 0, 0));
      jPanel1.add(jLabel2,      new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0
              ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
      jPanel1.add(majorCombo,      new GridBagConstraints(0, 4, 2, 1, 1.0, 0.0
              ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 60), 0, 0));
      jPanel1.add(jLabel3,      new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0
              ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 60), 0, 0));
      jPanel1.add(minorCombo,      new GridBagConstraints(0, 6, 2, 1, 1.0, 0.0
              ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 60), 0, 0));
      jPanel1.add(jLabel4,       new GridBagConstraints(0, 7, 2, 1, 0.0, 0.0
              ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
      jPanel1.add(languagesCombo,      new GridBagConstraints(0, 8, 2, 1, 1.0, 0.0
              ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 20, 0, 60), 0, 0));
      jPanel1.add(jLabel6,        new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0
              ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 15, 0), 0, 0));
      jPanel1.add(btnOk,          new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0
              ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 150, 0, 0), 0, 0));
      jPanel1.add(btnCancel,         new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0
              ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 50), 0, 0));

      this.setSize(new Dimension(338, 318));

      createListeners();
    }

    /** Create the Action Listeners for the dialog*/
    private void createListeners() {
        btnOk.addActionListener( new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            LinearNode ln = new LinearNode(
                (String)listCombo.getSelectedItem(),
                (String)majorCombo.getSelectedItem(),
                (String)minorCombo.getSelectedItem(),
                (String)languagesCombo.getSelectedItem()
              );
            if ((0 == ln.getList().trim().length())
              ||
              (0 == ln.getMajorType().trim().length())) {
              JOptionPane.showMessageDialog(Gaze.this,
              "This is not a valid Linear Node.\n"+
              "List and Major Type are mandatory\n"+
              "List : "+ln.getList()+
              "\nMajor Type : "+ln.getMajorType(),
              "Invalid Linear Node",JOptionPane.ERROR_MESSAGE );
            } else {
              performLinearAction(action,position,ln);
            }
            dispose();
          }
        });

        btnCancel.addActionListener( new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            dispose();   }  });

          addKeyListener(new KeyListener(){

          public void keyTyped(KeyEvent kev){}

          public void keyReleased(KeyEvent kev) {
            if (kev.VK_ENTER == kev.getKeyCode()) {
              LinearNode ln = new LinearNode(
                  (String)listCombo.getSelectedItem(),
                  (String)majorCombo.getSelectedItem(),
                  (String)minorCombo.getSelectedItem(),
                  (String)languagesCombo.getSelectedItem()
                );
              performLinearAction(action,position,ln);
              dispose();
            } // if enter
            else {
              if (kev.VK_ESCAPE == kev.getKeyCode()) {
                dispose();
              } // if escape
            } // else
          } // keyReleased()

          public void keyPressed(KeyEvent kev) {}

        }); // add esc enter key listener

    } // createListeners()

  }// class LinearNodeInput


  /** Reacts on all Create New Mapping actions performed either
   *  through the menu, either through the new buton. */
  class MappingNewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = MainFrame.getFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

          int result = chooser.showDialog(Gaze.this, "New");
          if ( result == JFileChooser.APPROVE_OPTION ) {
            File selected = chooser.getSelectedFile();
            try {
              if (!selected.createNewFile()){
                JOptionPane.showMessageDialog(
                  Gaze.this,
                  "Cannot Create Mapping Definition.\n"+
                  selected.getAbsolutePath(),
                  "Mapping Definition Create Failure",
                  JOptionPane.ERROR_MESSAGE
                );
              } // if
              URL lurl = new URL("file:///"+selected.getAbsolutePath());
              mapping = new MappingDefinition();
              mapping.setURL(lurl);
              mapping.load();

              // remove the old tree from the scroll pane
              if (null != oTree)
                ontologyScroll.getViewport().remove(oTree);

              oTree = new JTree();
              ontologyScroll.getViewport().add(oTree);
              oTree.setVisible(false);
              oTree.updateUI();

              // set the list data with the nodes of the gaz
              mappingList.setListData(mapping.toArray());


            } catch (ResourceInstantiationException x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load Mapping Definition (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\nResourceInstantiationException:"+x.getMessage()
                ,"Mapping Definition Load Failure",
                JOptionPane.ERROR_MESSAGE);
            } catch (Exception x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load linear definition (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\n"+x.getClass()+":"+x.getMessage()
                ,"Linear Definition Load Failure",
                JOptionPane.ERROR_MESSAGE);
            }
          } // approve
        } // actionPerformed(ActionEvent)
    } // class MappingNewListener


  /** Reacts on all Load Mapping actions performed either
   *  through the menu, wither through the load buton. */
  class MappingLoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = MainFrame.getFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

          int result = chooser.showOpenDialog(Gaze.this);
          if ( result == JFileChooser.APPROVE_OPTION ) {
            File selected = chooser.getSelectedFile();
            try {
              URL lurl = new URL("file:///"+selected.getAbsolutePath());
              mapping = new MappingDefinition();
              mapping.setURL(lurl);
              mapping.load();

              // set the list data with the nodes of the gaz
              mappingList.setListData(mapping.toArray());

              reinitializeGazetteer();
            } catch (ResourceInstantiationException x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load Mapping Definition (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\nResourceInstantiationException:"+x.getMessage()
                ,"Mapping Definition Load Failure",
                JOptionPane.ERROR_MESSAGE);
            } catch (Exception x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load linear definition (corrupted format).\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\n"+x.getClass()+":"+x.getMessage()
                ,"Linear Definition Load Failure",
                JOptionPane.ERROR_MESSAGE);
            }
          } // approve
        } // actionPerformed(ActionEvent)
    } // class MappingLoadListener

    /** Reacts on all Save As Mapping Definition actions. */
    class MappingSaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          if ( null == mapping ) {
            JOptionPane.showMessageDialog(
              Gaze.this,"The Mapping Definition is null and cannot be saved.",
              "Mapping Definition Save As Failure.",JOptionPane.ERROR_MESSAGE);
          } else {
            JFileChooser chooser = MainFrame.getFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            int result = chooser.showSaveDialog(Gaze.this);
            if ( result == JFileChooser.APPROVE_OPTION ) {
              File selected = chooser.getSelectedFile();
              URL lurl;
              try {
                lurl = new URL("file:///"+selected.getAbsolutePath());
                mapping.setURL(lurl);
                mapping.store();
                JOptionPane.showMessageDialog(
                  Gaze.this,
                  "Mapping Definition saved sucessfuly.\n"
                  +lurl,
                  "Mapping Definition Save As",
                  JOptionPane.PLAIN_MESSAGE);

                  reinitializeGazetteer();

              } catch (MalformedURLException x) {
                JOptionPane.showMessageDialog(Gaze.this,"Cannot save Mapping Definition.\n"
                  +"Due to "+x.getClass()+":"+x.getMessage(),
                  "Mapping Definition Save As Failure",JOptionPane.ERROR_MESSAGE);
              } catch (ResourceInstantiationException x) {
                JOptionPane.showMessageDialog(
                  Gaze.this,
                  "Unable to save the Mapping Defintion.\n"
                  +"Due to : "+x.getClass()+":"+x.getMessage(),
                  "Mapping Definition Save Failure.",
                  JOptionPane.ERROR_MESSAGE);
              } // catch
            } // approved
          } // else
        }
    }// class MappingSaveListener

  /** Reacts on all Mapping Definition Save As events */
  class MappingSaveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if ( null == mapping ) {
        JOptionPane.showMessageDialog(
          Gaze.this,"The Mapping Definition is null and cannot be saved.",
          "Mapping Definition Save failure.",JOptionPane.ERROR_MESSAGE);
      } else {

        try {
          mapping.store();
          JOptionPane.showMessageDialog(
            Gaze.this,
            "Mapping Definition saved sucessfuly.",
            "Mapping Definition Save",
            JOptionPane.PLAIN_MESSAGE);
          reinitializeGazetteer();
        } catch (ResourceInstantiationException x) {
          JOptionPane.showMessageDialog(
            Gaze.this,
            "Unable to save the Mapping Definition.\n"
            +"Due to : "+x.getClass()+":"+x.getMessage(),
            "Mapping Definition Save failure.",
            JOptionPane.ERROR_MESSAGE);
        } // catch
      } // else
    } // actionPerformed(ActionEvent)
  } // class MappingSaveListener

  /** Reacts on all Load Ontology actions performed either
   *  through the menu, wither through the load buton. */
  class OntologyLoadListener implements ActionListener {
    @SuppressWarnings("deprecation")    
    public void actionPerformed(ActionEvent e) {
          JFileChooser chooser = MainFrame.getFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

          int result = chooser.showOpenDialog(Gaze.this);
          if ( result == JFileChooser.APPROVE_OPTION ) {
            File selected = chooser.getSelectedFile();
            try {
              URL ourl = new URL("file:///"+selected.getAbsolutePath());
              try {
                ontology = OntologyUtilities.getOntology(ourl);
                ontology.addOntologyModificationListener(Gaze.this);
              } catch (ResourceInstantiationException x) {
                x.printStackTrace(Err.getPrintWriter());
              }
              if (null == ontology)
                throw new GateRuntimeException("can not Load ontology by url.\n"
                  +"ontology is null.\n"
                  +"url = "+ourl);

              // remove the old tree from the scroll pane
              if (null != oTree)
                ontologyScroll.getViewport().remove(oTree);

              // check if there is already a tree for this ontology
              oTree = (JTree) ontologyTrees.get(ontology);

              if (null == oTree) {
                Map namesVsNodes = new HashMap();
                ClassNode root = ClassNode.createRootNode(ontology,mapping,namesVsNodes);
                OntoTreeModel model = new OntoTreeModel(root);
                MappingTreeView view = new MappingTreeView(model,mapping,Gaze.this);
                oTree = view;
                ontologyTrees.put(ontology,oTree);
              } // ontology tree has not been previously creted

              ontologyScroll.getViewport().add(oTree,null);
              oTree.setVisible(true);


            } catch (Exception x) {
              JOptionPane.showMessageDialog(Gaze.this,
                "Unable to load Ontology.\n"
                +"file:///"+selected.getAbsolutePath()+"\n"
                +"Due to:\n"+x.getClass()+":"+x.getMessage()
                ,"Ontology Load Failure",
                JOptionPane.ERROR_MESSAGE);
            }
          } // approve
        } // actionPerformed(ActionEvent)
    } // class OntologyLoadListener

} // class Gaze