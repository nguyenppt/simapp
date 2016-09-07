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


public class BNode  {
// Declare variable
  private String parentID;
  private String nodeID;
  private String typeID;
  private String name;
  private String parentName;
  BNode fatherNode;
  Vector children;
  Vector path;

  public BNode(BNodeDetail pDetail) {
    this.parentID= pDetail.getParentID();
    this.nodeID= pDetail.getNodeID();
    this.typeID= pDetail.getTypeID();
    this.name= pDetail.getName();
    this.parentName = pDetail.getParentName();

    this.children = new Vector();
    this.path=new Vector();

  }
  public BNode() {

  }
  public static void linkNode(BNode pa,BNode[] kids) {
        int len = kids.length;
        BNode kid = null;
        for (int i = 0; i < len; i++) {
            kid = kids[i];
            pa.children.addElement(kid);
            kid.fatherNode = pa;

        }
    }

    /*****************************getter methods************************/
    public String toString() { return name; }
    public BNode getFather() { return fatherNode; }

    public int getChildCount() { return children.size();  }
    public BNode getChildAt(int i) {
        return (BNode)children.elementAt(i);
    }
    public int getIndexOfChild(BNode kid) {
        return children.indexOf(kid);
    }
    public BNode[] getPath(){
      return getPathToRoot(this,0);
    }
    ///////////////////////////////////////////////////////////////////////
    protected BNode[] getPathToRoot(BNode aNode, int depth) {
        BNode[]              retNodes;

        /* Check for null, in case someone passed in a null node, or
           they passed in an element that isn't rooted at root. */
        if(aNode == null) {
            if(depth == 0)
                return null;
            else
                retNodes = new BNode[depth];
        }
        else {
            depth++;
            retNodes = getPathToRoot(aNode.getFather(), depth);
            retNodes[retNodes.length - depth] = aNode;
        }
        return retNodes;
    }
    ///////////////////////////////////////////////////////////
    public void insert(BNode newChild, int childIndex) {
            BNode oldParent = (BNode)newChild.getFather() ;

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
    public void remove(BNode aChild) {
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
        BNode child = (BNode)getChildAt(childIndex);
        children.removeElementAt(childIndex);
        child.setParent(null);
    }
    /////////////////////////////////
    public void removeFromParent() {
        BNode parent = (BNode)getFather();
        if (parent != null) {
            parent.remove(this);
        }
    }
    ///////////////////////////////////////////////
    public void setParent(BNode newParent) {
        fatherNode = newParent;
    }
    ///////////////////////////////////////////
    public int getIndex(BNode aChild) {
        if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }

        if (!isNodeChild(aChild)) {
            return -1;
        }
        return children.indexOf(aChild);	// linear search
    }

    ////////////////////////////////////////////////
    public boolean isNodeChild(BNode aNode) {
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

    public String getParentID(){
        return parentID;
    }
    public void setParentID(String parentID){
        this.parentID= parentID;
    }

    public String getNodeID(){
        return nodeID;
    }
    public void setNodeID(String nodeID){
        this.nodeID= nodeID;
    }

    public String getTypeID(){
        return typeID;
    }
    public void setTypeID(String typeID){
        this.typeID= typeID;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }

    public String getParentName(){
        return parentName;
    }
    public void setParentName(String parentName){
        this.parentName= parentName;
    }

    /******************************************************/

}
