package btm.swing;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author
 * @version 1.0
 */


import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.Vector;
import javax.swing.*;


public class BJScreenModel implements TreeModel {

private Vector treeModelListeners = new Vector();
private BJScreenNode rootNode;

protected EventListenerList listenerList = new EventListenerList();
public BJScreenModel(BJScreenNode root) {
  rootNode=root;
}

//////////////// Fire events //////////////////////////////////////////////

  /**
   * The only event raised by this model is TreeStructureChanged with the
   * root as path, i.e. the whole tree has changed.
   */
  protected void fireTreeStructureChanged(BJScreenNode oldRoot) {
      int len = treeModelListeners.size();
      TreeModelEvent e = new TreeModelEvent(this,
                                            new Object[] {oldRoot});
      for (int i = 0; i < len; i++) {
          ((TreeModelListener)treeModelListeners.elementAt(i)).
                  treeStructureChanged(e);
      }
  }

  //////////////// TreeModel interface implementation ///////////////////////


  /**
   * Adds a listener for the TreeModelEvent posted after the tree changes.
   */
  public void addTreeModelListener(TreeModelListener l) {
      treeModelListeners.addElement(l);
  }

  /**
   * Returns the child of parent at index index in the parent's child array.
   */
  public Object getChild(Object parent, int index) {
      BJScreenNode p = (BJScreenNode)parent;

      return p.getChildAt(index);
  }

  /**
   * Returns the number of children of parent.
   */
  public int getChildCount(Object parent) {
      BJScreenNode p = (BJScreenNode)parent;

          int count = 0;
          if (p.getFather() != null) {
              count++;
          }

      return p.getChildCount();
  }

  /**
   * Returns the index of child in parent.
   */
  public int getIndexOfChild(Object parent, Object child) {
      BJScreenNode p = (BJScreenNode)parent;

      return p.getIndexOfChild((BJScreenNode)child);
  }

  /**
   * Returns the root of the tree.
   */
  public Object getRoot() {
      return rootNode;
  }

  /**
   * Returns true if node is a leaf.
   */
  public boolean isLeaf(Object node) {
      BJScreenNode p = (BJScreenNode)node;
      return p.getChildCount() == 0;
  }
/////////////////////////////////////////////////////
public void removeNodeFromParent(BJScreenNode node) {
      BJScreenNode         parent = (BJScreenNode)node.getFather();

      if(parent == null)
          throw new IllegalArgumentException("node does not have a parent.");

      int[]            childIndex = new int[1];
      Object[]         removedArray = new Object[1];

      childIndex[0] = parent.getIndex(node);
      parent.remove(childIndex[0]);
      removedArray[0] = node;
      nodesWereRemoved(parent, childIndex, removedArray);
}
///////////////////////////////////////////////////////////////////
public void nodesWereRemoved(BJScreenNode node, int[] childIndices,
                               Object[] removedChildren) {
      if(node != null && childIndices != null) {
          fireTreeNodesRemoved(this, getPathToRoot(node), childIndices,
                               removedChildren);
      }
}
/////////////////////////////////////////////////////////////////
protected void fireTreeNodesRemoved(Object source, Object[] path,
                                      int[] childIndices,
                                      Object[] children) {
      // Guaranteed to return a non-null array
      Object[] listeners = listenerList.getListenerList();
      TreeModelEvent e = null;
      // Process the listeners last to first, notifying
      // those that are interested in this event
      for (int i = listeners.length-2; i>=0; i-=2) {
          if (listeners[i]==TreeModelListener.class) {
              // Lazily create the event:
              if (e == null)
                  e = new TreeModelEvent(source, path,
                                         childIndices, children);
              ((TreeModelListener)listeners[i+1]).treeNodesRemoved(e);
          }
      }
  }


  /**
   * Removes a listener previously added with addTreeModelListener().
   */
  public void removeTreeModelListener(TreeModelListener l) {
      treeModelListeners.removeElement(l);
  }

  /**
   * Messaged when the user has altered the value for the item
   * identified by path to newValue.  Not used by this model.
   */
  public void valueForPathChanged(TreePath path, Object newValue) {
      if(newValue.toString().trim().length()==0)return;
      BJScreenNode cNode=(BJScreenNode)path.getLastPathComponent() ;
      if(newValue.toString().trim().length()<=60){
          cNode.setName(newValue.toString() ) ;
      }else{
          cNode.setName(newValue.toString().substring(0,60) ) ;
      }
      ////////////update channge node to database(table type_indexation)////////////
//      dataModuleParametres1.saveBJScreenNode(cNode) ;
  }


  public void insertNodeInto(BJScreenNode pa, BJScreenNode newChild, int index){
     pa.insert(newChild, index);

      int[]           newIndexs = new int[1];

      newIndexs[0] = index;
      nodesWereInserted(pa, newIndexs);
  }
  //////////////////////
  public void nodesWereInserted(BJScreenNode node, int[] childIndices) {
      if(listenerList != null && node != null && childIndices != null
         && childIndices.length > 0) {
          int               cCount = childIndices.length;
          Object[]          newChildren = new Object[cCount];

          for(int counter = 0; counter < cCount; counter++)
              newChildren[counter] = node.getChildAt(childIndices[counter]);
          fireTreeNodesInserted(this, getPathToRoot(node), childIndices,
                                newChildren);
      }
  }

  public BJScreenNode[] getPathToRoot(BJScreenNode aNode) {
      return getPathToRoot(aNode, 0);
  }
   protected BJScreenNode[] getPathToRoot(BJScreenNode aNode, int depth) {
      BJScreenNode[]              retNodes;
      // This method recurses, traversing towards the root in order
      // size the array. On the way back, it fills in the nodes,
      // starting from the root and working back to the original node.

      /* Check for null, in case someone passed in a null node, or
         they passed in an element that isn't rooted at root. */
      if(aNode == null) {
          if(depth == 0)
              return null;
          else
              retNodes = new BJScreenNode[depth];
      }
      else {
          depth++;
          if(aNode == rootNode)
              retNodes = new BJScreenNode[depth];
          else
              retNodes = getPathToRoot(aNode.getFather(), depth);
          retNodes[retNodes.length - depth] = aNode;
      }
      return retNodes;
  }

 public void reload() {
      reload(rootNode);
  }
 public void reload(BJScreenNode node) {
      if(node != null) {
          //fireTreeStructureChanged(this, getPathToRoot(node), null, null);
          fireTreeStructureChanged(node);
      }
  }

  //////////////////////
protected void fireTreeNodesInserted(Object source, Object[] path,
                                      int[] childIndices,
                                      Object[] children) {
      // Guaranteed to return a non-null array
      Object[] listeners = listenerList.getListenerList();
      TreeModelEvent e = null;
      // Process the listeners last to first, notifying
      // those that are interested in this event
      for (int i = listeners.length-2; i>=0; i-=2) {
          if (listeners[i]==TreeModelListener.class) {
              // Lazily create the event:
              if (e == null)
                  e = new TreeModelEvent(source, path,
                                         childIndices, children);
              ((TreeModelListener)listeners[i+1]).treeNodesInserted(e);
          }
      }
  }
public BJScreenModel() {
  try {
    jbInit();
  }
  catch(Exception e) {
    e.printStackTrace() ;

    JOptionPane.showMessageDialog(null, "in BJScreenModel it happens a mistake: "+ e.toString());
  }
}
private void jbInit() throws Exception {


}

}
