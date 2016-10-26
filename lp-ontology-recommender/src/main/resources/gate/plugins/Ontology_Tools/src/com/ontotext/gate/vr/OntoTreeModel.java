package com.ontotext.gate.vr;

import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import javax.swing.event.TreeModelEvent;
import java.util.Vector;

/**A Tree Model used for the tree view of an ontology.
 * @author Miroslav Goranov*/


public class OntoTreeModel implements TreeModel {

  private IFolder root;
  private Vector treeModelListeners = new Vector();

  public OntoTreeModel(IFolder root) {
      this.root = root;
  }

  public Object getRoot() {
    return root;
  }

  public Object getChild(Object parent, int index) {
      IFolder parentFolder=(IFolder)parent;
      IFolder child = parentFolder.getChild(index);
      return child;

  }

  public int getChildCount(Object parent) {
    IFolder parentFolder=(IFolder)parent;
    return parentFolder.getChildCount();
  }

  public boolean isLeaf(Object node) {
    boolean result = false;
    IFolder leaf = ( IFolder)node;
    if( leaf.getChildCount() == 0){
       result = true;
    }
    return result;
  }

  public void valueForPathChanged(TreePath path, Object newValue) {
  }

  public int getIndexOfChild(Object parent, Object child) {
      IFolder fold=(IFolder)parent;
      return fold.getIndexOfChild(child);
  }

  public void addTreeModelListener(TreeModelListener l) {
    treeModelListeners.add(l);
  }

  public void removeTreeModelListener(TreeModelListener l) {
      treeModelListeners.removeElement(l);
  }

  protected void fireTreeStructureChanged(IFolder oldRoot) {
        int len = treeModelListeners.size();
        TreeModelEvent e = new TreeModelEvent(this,
                                              new Object[] {oldRoot});
        for (int i = 0; i < len; i++) {
            ((TreeModelListener)treeModelListeners.elementAt(i)).
                    treeStructureChanged(e);
        }
    }
}