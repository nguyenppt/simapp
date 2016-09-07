package btm.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.sql.*;
import common.DataAccessLayer;
import common.Constant;
import common.ExceptionHandle;
import btm.swing.*;
import btm.attr.*;
import java.util.*;
import java.util.StringTokenizer;
import java.sql.Statement;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class BJScreenTree  extends JTree{
  DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

  AccessRight oAccRight=new AccessRight();
  public Vector vAccRightAll=new Vector();
  Statement stmt = null;

  public BJScreenTree(BJScreenModel treeModel) {

    super(treeModel);
    getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
    this.setCellRenderer(renderer);
  }

  //screen
  public Vector initAccRight(DataAccessLayer DAL) {
    Vector vResultAccRight = new Vector();
    vResultAccRight = assignAccRightAll(DAL);
    vAccRightAll = vResultAccRight;
    return vResultAccRight;
  }

   public  void showScreenTree(Vector vAccRight, BJScreenNode rootTree){
     Vector vAccRightRoot = new Vector();
     vAccRightRoot = getVecAccRight(vAccRight, Constant.rootId);//select root
     rootTree.removeAllChildren();
     buildScreenTree(vAccRightRoot, rootTree);
     this.updateUI();

  }

  public void buildScreenTree(Vector vAccRight, BJScreenNode root){
    BJScreenNode  childNode ;
    BJScreenNodeDetail accDetail;
    Vector vAccRightCond = new Vector();
    String parentId = null;
    String childId = null;
    String name = null;
    int i=0;
    while (i < vAccRight.size()) {
        oAccRight = (AccessRight) vAccRight.get(i);
        parentId= oAccRight.getParentId().toString().trim();
        childId = oAccRight.getChildId().toString().trim();
        name = oAccRight.getName().toString().trim();
        accDetail=new BJScreenNodeDetail(parentId,childId,name);
        childNode= new BJScreenNode(accDetail);
        BJScreenNode.linkNode(root,new BJScreenNode[]{childNode});
        vAccRightCond = getVecAccRight(vAccRightAll, childId);
        buildScreenTree(vAccRightCond, childNode);
        i +=1;
      }
    }

   //=========================================================
    public Vector assignAccRightAll(DataAccessLayer DAL) {
      Vector vResultAccRight = new Vector();
      ResultSet rs = null;
      oAccRight = null;
      try {
        stmt = DAL.getConnection().createStatement();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
      vResultAccRight.removeAllElements();
      String sSQL = "select PARENT_SCR_ID, SCR_ID, SCR_NAME from BTM_IM_ACCESS_RIGHT order by SCR_ID asc";
//      System.out.println(sSQL);
      rs = DAL.executeQueries(sSQL,stmt);
      try {
        if (!rs.isBeforeFirst() && !rs.isAfterLast()) return vResultAccRight;
        while (rs.next()) {
          oAccRight=new AccessRight(rs.getString("PARENT_SCR_ID"), rs.getString("SCR_ID"), rs.getString("SCR_NAME"));
          vResultAccRight.add(oAccRight);
        }
        rs.getStatement().close();
      }catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      return vResultAccRight;
    }

    //get vector with condition : parentId
     public Vector getVecAccRight(Vector vAccRight, String parentId) {
       int i=0;
       Vector vAccRightTmp = new Vector();
       String id=null;
       while (i < vAccRight.size()) {
         oAccRight = (AccessRight) vAccRight.get(i);
         id=oAccRight.getParentId().toString().trim();
          if (id.equalsIgnoreCase(parentId)) {
            vAccRightTmp.add(oAccRight);
          }
          i += 1;
       }
       return vAccRightTmp;
     }

   //   get parenId with condition childId
      public String getParentId(String childId) {
        int i=0;
        String id=null;
        String parentId=null;
        if (vAccRightAll.size() <0) return Constant.root;
        while (i < vAccRightAll.size()) {
          oAccRight = (AccessRight) vAccRightAll.get(i);
          id=oAccRight.getChildId().toString().trim();
           if (id.equalsIgnoreCase(childId)) {
             parentId = oAccRight.getParentId().toString().trim();
             return parentId;
           }
          i += 1;
        }
        return parentId;
      }//end of method


  //=========================================================
  public String getStringPath(TreeSelectionEvent e){
     String st="";
     String name=null;
     TreePath tpath = e.getPath();
     Object[] path = new Object[tpath.getPathCount()];
     path = tpath.getPath();
     int i=0;
     while (i<path.length) {
       name=(path[i]).toString().trim();
       if (i!=path.length-1) {
           st += name + "\\";
       }else {st += name;}
       i=i+1;
     }
     return st;
   }//end method


   public int getPosNode() {
     TreePath path = this.getSelectionPath();
     return path.getPathCount();
   }


   public int getChildCount() {
     BJScreenNode currentNode;
     TreePath path = this.getSelectionPath();
     currentNode = (BJScreenNode) path.getLastPathComponent();
     return currentNode.getChildCount();
   }


  /** getParentId method
   * @author 		Tuan.Truong
   * @param 		None
   * @return		String ParentId
   */
  public String getParentId() {
      BJScreenNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BJScreenNode) path.getLastPathComponent();

      return currentNode.getParentId();
  }

  /** getChildId method
   * @author 		Tuan.Truong
   * @param 		None
   * @return		String ChildId
   */
  public String getChildId() {
      BJScreenNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BJScreenNode) path.getLastPathComponent();

      return currentNode.getNodeId();
  }

  /** getNodeName method
   * @author 		Tuan.Truong
   * @param 		TreeSelectionEvent e
   * @return		String NodeName
   */
  public String getNodeName() {
      BJScreenNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BJScreenNode) path.getLastPathComponent();

      return currentNode.getName();
  }//end method

  /** getNodeLeaf method
   * @author 		Tuan.Truong
   * @param 		TreeSelectionEvent e
   * @return		Boolean NodeLeaf
   */
  public boolean isLeaf() {
      BJScreenNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BJScreenNode) path.getLastPathComponent();

      return currentNode.isLeaf();
  }//end method
  /** Set focus on selected node by ID
  * @param 	root root of tree
  * @param 	nameNode name of  node which focus on
  * @return	no
  */
  public void setSelectionPathByNameNode(BJScreenNode root,String idNode){
    BJScreenNode node=null;
    if(root.getNodeId().equals(idNode)) this.setSelectionPath(new TreePath(root.getPath()));
    for (int i = 0; i < root.getChildCount(); i++) {
        if(root.getChildAt(i).getNodeId().equals(idNode)){
          node= (BJScreenNode)root.getChildAt(i);
          this.setSelectionPath(new TreePath(node.getPath()));
          return;
        }
        if(root.getChildAt(i).getChildCount()>0){
          setSelectionPathByNameNode((BJScreenNode)root.getChildAt(i),idNode);
        }
    }
  }
}
