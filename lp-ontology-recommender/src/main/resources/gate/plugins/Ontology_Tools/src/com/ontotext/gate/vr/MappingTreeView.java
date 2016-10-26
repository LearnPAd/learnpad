package com.ontotext.gate.vr;

import gate.creole.gazetteer.MappingDefinition;
import gate.creole.gazetteer.MappingNode;
import gate.creole.ontology.OClass;
import gate.gui.MainFrame;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;



/**
 * Mapping Tree View extends {@link javax.swing.JTree}
 * in order to represent the mapping information.
 * To be used with Gaze.
 * borislav popov 18/04/2002 */
public class MappingTreeView extends JTree {

    private static final long serialVersionUID = 3257568420999410744L;

  /**the name of the default gazetteer icon*/
  private final static String GAZ_ICON = "lr";

  /**Mapping Node Edit Action */
  private final static int EDIT_ACTION = 1;
  /**Mapping Node Insert Action */
  private final static int INSERT_ACTION = 2;
  /**Mapping Node Remove Action */
  private final static int REMOVE_ACTION = 3;

  /** reference to the mapping definition represented by this view */
  private MappingDefinition mapping = null;

  /** reference to the Gaze VR */
  private Gaze gaze = null;

  /**mapping popup menu */
  private JPopupMenu pupMenu = new JPopupMenu();

  /**insert popup action*/
  private JMenuItem insertPuP = new JMenuItem("insert mapping");
  /**remove popup action*/
  private JMenuItem removePuP = new JMenuItem("remove mapping");

  /**@param model the tree model
   * @param mappingDef the mapping definition
   * @param gazeVR gaze (gazetteer editor) visual resource */
  public MappingTreeView(OntoTreeModel model,
        MappingDefinition mappingDef, Gaze gazeVR) {

    super(model);

    if (null == mappingDef)
      throw new NullPointerException(
        "Mapping Def cannot be null on contructing MappingTreeView");

    if (null == gazeVR)
      throw new NullPointerException(
        "Gazetteer Editor - Gaze VR - cannot be null on contructing MappingTreeView");

    mapping = mappingDef;
    gaze = gazeVR;

    init();

  }// constructor

  /** Initialization */
  private void init() {
    getSelectionModel().setSelectionMode(
      TreeSelectionModel.SINGLE_TREE_SELECTION);
    DefaultTreeCellRenderer renderer = new MappingTreeCR();
    renderer.setLeafIcon(renderer.getDefaultClosedIcon());
    this.setCellRenderer(renderer);

    // listeners
    addMouseListener(new MyMouseAdapter());

    removePuP.addActionListener(new RemoveAL());
    insertPuP.addActionListener(new InsertAL());

    pupMenu.add(insertPuP);
    pupMenu.add(removePuP);

  } // init

  /** Mapping Tree Cell Renderer distinguishes nodes, originating from ontology classes from
   *  nodes, originating from gazetteer lists. */
  class MappingTreeCR extends DefaultTreeCellRenderer {

     private static final long serialVersionUID = 3546924666926085169L;

    /**
     * Sets the value of the current tree cell to <code>value</code>.
     * If <code>selected</code> is true, the cell will be drawn as if
     * selected. If <code>expanded</code> is true the node is currently
     * expanded and if <code>leaf</code> is true the node represets a
     * leaf anf if <code>hasFocus</code> is true the node currently has
     * focus. <code>tree</code> is the JTree the receiver is being
     * configured for.
     * Returns the Component that the renderer uses to draw the value.
     *
     * @return	Component that the renderer uses to draw the value.
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value,
				   boolean selected, boolean expanded,
				   boolean leaf, int row, boolean hasFocus) {
      super.getTreeCellRendererComponent(
        tree, value, selected, expanded, leaf, row, hasFocus);

      if ( value instanceof ClassNode ) {
        ClassNode cn = (ClassNode) value;
        Object source = cn.getSource();
        if ( source instanceof MappingNode ) {
          setIcon(MainFrame.getIcon(GAZ_ICON));
        } // if gaz list
      } // if node
      return this;
    } // getTreeCellRendererComponent()

  } // class MappingTreeCR

  /**The mouse adapter listens to the entire mouse activity and invokes a popup menu if
   * right click. */
  class MyMouseAdapter extends MouseAdapter{

      public MyMouseAdapter(){
      }

      public void mouseClicked(MouseEvent e){
          TreePath path=MappingTreeView.this.getSelectionPath();
          
          ClassNode node =null;
          if (SwingUtilities.isLeftMouseButton(e)) {
            if (2 == e.getClickCount()) {
              if( path != null){
                node = (ClassNode) path.getLastPathComponent();
                if ( node.getSource() instanceof MappingNode ) {
                  MappingNode mn = (MappingNode)node.getSource();
                  gaze.displayList(mn.getList());
                }
              } // if !=null
            } // double click
          }  // left

          if(SwingUtilities.isRightMouseButton(e)){
            if( path != null){
              node = (ClassNode) path.getLastPathComponent();
              if ( node.getSource() instanceof MappingNode ) {
                removePuP.setEnabled(true);
                insertPuP.setEnabled(false);
              } else {
                removePuP.setEnabled(false);
                insertPuP.setEnabled(true);
              }
              pupMenu.show(MappingTreeView.this,e.getX(),e.getY());
            }
          }
      }
  } //class MyMouseAdapter


  /*Action Listener of the remove pop up menu item */
  class RemoveAL implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      ClassNode node = (ClassNode)MappingTreeView.this.getLastSelectedPathComponent();
      Object source = node.getSource();
      if (source instanceof MappingNode) {
        TreePath pp = MappingTreeView.this.getAnchorSelectionPath().getParentPath();
        if (null!=pp) {
          ClassNode pNode = (ClassNode)pp.getLastPathComponent();
          Vector<ClassNode> kids = pNode.children();
          kids.remove(node);
          pNode.setChildren(kids);
          mapping.remove(source);
          MappingTreeView.this.updateUI();
          gaze.updateMappingUI();
        } // pp ! null
      }// if map node
    } // actionPerformed()
  } // class RemoveAL


  /*Action Listener of the insert pop up menu item */
  class InsertAL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      ClassNode node = (ClassNode)MappingTreeView.this.getLastSelectedPathComponent();
      Object source = node.getSource();
      if (source instanceof OClass) {
        List lists = gaze.getLists();
        Collections.sort(lists);

        Object result = JOptionPane.showInputDialog(MappingTreeView.this,
                          "Map selected ontology class to a gazetteer list:",
                          "Insert Mapping Node",
                          JOptionPane.PLAIN_MESSAGE,
                          null,lists.toArray(),null);
        if (null != result) {
          OClass oc = (OClass) source;
          MappingNode mn = new MappingNode((String)result,
              oc.getOntology().getURL().toString(),
              node.toString());
          mapping.add(mn);
          ClassNode cn = new ClassNode(mn);
          Vector<ClassNode> kids = node.children();
          kids.add(cn);
          MappingTreeView.this.updateUI();
          gaze.updateMappingUI();
        } // null!=result
      }// if map node
    } // actionPerformed()
  } // class InsertAL


} //MappingTreeView