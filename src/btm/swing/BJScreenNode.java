package btm.swing;


import java.util.Vector;
import java.math.*;
import common.*;
import java.math.*;
import javax.swing.*;
/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */


public class BJScreenNode  {
// Declare variable
  private String parentId;
  private String nodeId;
  private String name;
  BJScreenNode fatherNode;
  Vector children;
  Vector path;

  public BJScreenNode(BJScreenNodeDetail pDetail) {
    this.parentId= pDetail.getParentId();
    this.nodeId= pDetail.getNodeId();
    this.name= pDetail.getName();
    this.children = new Vector();
    this.path=new Vector();

  }
  public BJScreenNode() {

  }
  public static void linkNode(BJScreenNode pa,BJScreenNode[] kids) {
        int len = kids.length;
        BJScreenNode kid = null;
        for (int i = 0; i < len; i++) {
            kid = kids[i];
            pa.children.addElement(kid);
            kid.fatherNode = pa;

        }
    }

    /*****************************getter methods************************/
    public String toString() { return name; }
    public BJScreenNode getFather() { return fatherNode; }

    public int getChildCount() { return children.size();  }
    public BJScreenNode getChildAt(int i) {
        return (BJScreenNode)children.elementAt(i);
    }
    public int getIndexOfChild(BJScreenNode kid) {
        return children.indexOf(kid);
    }
    public BJScreenNode[] getPath(){
      return getPathToRoot(this,0);
    }
    ///////////////////////////////////////////////////////////////////////
    protected BJScreenNode[] getPathToRoot(BJScreenNode aNode, int depth) {
        BJScreenNode[]              retNodes;

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
            retNodes = getPathToRoot(aNode.getFather(), depth);
            retNodes[retNodes.length - depth] = aNode;
        }
        return retNodes;
    }
    ///////////////////////////////////////////////////////////
    public void insert(BJScreenNode newChild, int childIndex) {
            BJScreenNode oldParent = (BJScreenNode)newChild.getFather() ;

            if (oldParent != null) {
                oldParent.remove(newChild);
            }
            newChild.setParent(this);
            if (children == null) {
                children = new Vector();
            }
            children.insertElementAt(newChild, childIndex);
    }
    //////////////////////////////////////////
    public void remove(BJScreenNode aChild) {
        if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }

        if (!isNodeChild(aChild)) {
            throw new IllegalArgumentException("argument is not a child");
        }
        remove(getIndex(aChild));	// linear search
    }
    /////////////////////////////////
    public void removeAllChildren() {
        for (int i = getChildCount()-1; i >= 0; i--) {
            remove(i);
        }
    }
    ////////////////////////////////////
    public void remove(int childIndex) {
        BJScreenNode child = (BJScreenNode)getChildAt(childIndex);
        children.removeElementAt(childIndex);
        child.setParent(null);
    }
    /////////////////////////////////
    public void removeFromParent() {
        BJScreenNode parent = (BJScreenNode)getFather();
        if (parent != null) {
            parent.remove(this);
        }
    }
    ///////////////////////////////////////////////
    public void setParent(BJScreenNode newParent) {
        fatherNode = newParent;
    }
    ///////////////////////////////////////////
    public int getIndex(BJScreenNode aChild) {
        if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }

        if (!isNodeChild(aChild)) {
            return -1;
        }
        return children.indexOf(aChild);	// linear search
    }

    ////////////////////////////////////////////////
    public boolean isNodeChild(BJScreenNode aNode) {
        boolean retval;

        if (aNode == null) {
            retval = false;
        } else {
            if (getChildCount() == 0) {
                retval = false;
            } else {
                retval = (aNode.getFather() == this);
            }
        }
        return retval;
    }

    public boolean isLeaf() {
        return (getChildCount() == 0);
    }

    public String getParentId(){
        return parentId;
    }
    public void setParentId(String parentId){
        this.parentId= parentId;
    }

    public String getNodeId(){
        return nodeId;
    }
    public void setNodeId(String nodeId){
        this.nodeId= nodeId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }
    /******************************************************/
}
