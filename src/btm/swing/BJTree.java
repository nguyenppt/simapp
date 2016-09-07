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
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class BJTree  extends JTree{
  DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
  Vector vTreeData = new Vector();

  ObjHier oHier=new ObjHier();
  public Vector vHierAll=new Vector();
  public Vector vHierActive=new Vector();

  ObjHierType oHierType=new ObjHierType();
  public Vector vHierTypeAll=new Vector();

  public BJTree(BTreeModel treeModel) {

    super(treeModel);
    getSelectionModel().setSelectionMode(
        TreeSelectionModel.SINGLE_TREE_SELECTION);
    renderer.setIcon(new ImageIcon("images/sample.gif"));
    renderer.setLeafIcon(new ImageIcon("images/sample.gif"));//sim.FrameMainSim.class.getResource
    this.setCellRenderer(renderer);
  }

  public Vector initHierAll(DataAccessLayer DAL, String tableName) {
    Vector vResultHierAll = new Vector();
    vResultHierAll = assignHierAll(DAL, tableName);
    vHierAll = vResultHierAll;//assign
    return vResultHierAll;
  }

  public Vector initHierActive(Vector vHierAll) {
    Vector vResultHierActive = new Vector();
    vResultHierActive = getHierActive(vHierAll, "1");//1 : active, 0: delete
    vHierActive = vResultHierActive;//assign
    return vResultHierActive;
  }

  public Vector initHierType(DataAccessLayer DAL, String tableName) {
    Vector vResultHierType = new Vector();
    vResultHierType = assignHierTypeAll(DAL, tableName);
    vHierTypeAll = vResultHierType;//assign
    return vResultHierType;
  }

  /** Show tree which builds from DB
  * @param 	tableName table which get data from
  * @param 	rootTree root of Tree
  * @return	nothing
  */
  public  void showHierTree(Vector vHier, BNode rootTree){
    if (vHier.size()==0) return;
    Vector vListHierRoot = new Vector();
    vListHierRoot = getHier_ParentId(vHier, Constant.rootId);//select root
    rootTree.removeAllChildren();
    buildHierTree(vListHierRoot, rootTree);
    this.updateUI();
 }

 /** Show tree which builds from DB
 * @param 	tableName table which get data from
 * @param 	rs  ResultSet
 * @param 	rootTree root of Tree
 * @return	nothing
 */

 public void buildHierTree(Vector vHier,BNode root){
    BNode  childNode ;
    BNodeDetail hierDetail;
    Vector vListHierCond = new Vector();
    String parentId = null;
    String childId = null;
    String typeId = null;
    String childName = null;
    String parentName = null;
    int i = 0;
    while (i < vHier.size()) {
      oHier = (ObjHier) vHier.get(i);
      parentId = oHier.getParentID().toString().trim();
      childId = oHier.getChildID().toString().trim();
      typeId = oHier.getTypeID().toString().trim();
      childName = oHier.getChildName().toString().trim();
      parentName = oHier.getParentName().toString().trim();
//      System.out.println("parentId : " + parentId + " childId : " + childId + " typeId : " + typeId);
      hierDetail = new BNodeDetail(parentId, childId, typeId, childName,
                                   parentName);
      childNode = new BNode(hierDetail);
      BNode.linkNode(root, new BNode[] {childNode});
      vListHierCond = getHier_ParentId(vHierActive, childId);
      buildHierTree(vListHierCond, childNode);
      i += 1;
    }
   }

   public  void showHierTypeTree(Vector vHierType, BNode rootTree){
     Vector vListHierTypeRoot = new Vector();
     vListHierTypeRoot = getHierType_ParentId(vHierType, Constant.rootId);//select root
     rootTree.removeAllChildren();
     buildHierTypeTree(vListHierTypeRoot, rootTree);
     this.updateUI();

  }

  public void buildHierTypeTree(Vector vHierType, BNode root){
    BNode  childNode ;
    BNodeDetail hierDetail;
    Vector vHierTypeCond = new Vector();
    String parentId = null;
    String childId = null;
    String typeId = null;
    String childName = null;
    String parentName = null;
    int i=0;
    while (i < vHierType.size()) {
        oHierType = (ObjHierType) vHierType.get(i);
        parentId= oHierType.getHigherTypeID().toString().trim();
        childId = oHierType.getTypeID().toString().trim();//typeId
//       typeId = null;
        childName = oHierType.getTypeName().toString().trim();
//       parentName = mull;
        hierDetail=new BNodeDetail(parentId,childId,typeId,childName, parentName);
        childNode= new BNode(hierDetail);
        BNode.linkNode(root,new BNode[]{childNode});
        vHierTypeCond = getHierType_ParentId(vHierTypeAll, childId);
        buildHierTypeTree(vHierTypeCond, childNode);
        i +=1;
      }
    }

   //=========================================================
   public Vector assignHierAll(DataAccessLayer DAL, String tableName) {
     Vector vResultHier = new Vector();
      ResultSet rs = null;
      Statement stmt = null;
      oHier = null;
      vResultHier.removeAllElements();
      if (tableName == null) return vResultHier;
      String sSQL = "select PARENT_ID, CHILD_ID, TYPE_ID, PARENT_NAME, CHILD_NAME, RECD_CURR_FLAG from " + tableName + " order by upper(CHILD_NAME) asc";
      try {
//        System.out.println(sSQL);
        stmt = DAL.getConnection().createStatement();
        rs = DAL.executeQueries(sSQL,stmt);
        if (!rs.isBeforeFirst() && !rs.isAfterLast()) return vResultHier;
        while (rs.next()) {
          oHier=new ObjHier(rs.getString("PARENT_ID"),rs .getString("CHILD_ID"),rs.getString("TYPE_ID"), rs.getString("PARENT_NAME"), rs.getString("CHILD_NAME"), rs.getString("RECD_CURR_FLAG"));
          vResultHier.add(oHier);
        }
        rs.getStatement().close();
      }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
      }
      return vResultHier;
    }

    public Vector getHierActive(Vector vHier, String flagActive) {
         int i=0;
         Vector vHierTmp = new Vector();
         String flag = null;
         while (i < vHier.size()) {
           oHier = (ObjHier) vHier.get(i);
           flag=oHier.getFlagActive();
            if (flag.equals(flagActive)) {
              vHierTmp.add(oHier);
            }
            i += 1;
         }
         return vHierTmp;
       }


    public Vector getHier_ParentId(Vector vHier, String parentId) {
      int i=0;
      Vector vHierTmp = new Vector();
      String id=null;
      while (i < vHier.size()) {
        oHier = (ObjHier) vHier.get(i);
        id=oHier.getParentID().toString().trim();
         if (id.equalsIgnoreCase(parentId)) {
           vHierTmp.add(oHier);
         }
         i += 1;
      }
      return vHierTmp;
    }

    public Vector addHier(Vector vHier, String parentId, String childId, String typeId, String parentName,
                          String childName, String flagActive) {
      oHier=new ObjHier(parentId, childId, typeId, parentName, childName, flagActive);
      vHier.add(oHier);
      return vHier;
    }

    public void addNewNode(BNode parentNode, String parentId, String childId, String typeId, String parentName,
                          String childName) {
      BNode  childNode ;
      BNodeDetail hierDetail;
      hierDetail = new BNodeDetail(parentId, childId, typeId, childName,
                                   parentName);
      childNode = new BNode(hierDetail);
      BNode.linkNode(parentNode, new BNode[] {childNode});
    }

    public Vector assignHierTypeAll(DataAccessLayer DAL, String tableName) {
      Vector vResultHierType = new Vector();
      ResultSet rs = null;
      Statement stmt = null;
      oHierType = null;
      vResultHierType.removeAllElements();
      if (tableName == null) return vResultHierType;
      String sSQL = "select TYPE_ID, HIGHER_TYPE_ID, NAME, DESCRIPTION from " + tableName + " order by TYPE_ID asc";
      try {
        stmt = DAL.getConnection().createStatement();
//        System.out.println(sSQL);
        rs = DAL.executeQueries(sSQL,stmt);
        if (!rs.isBeforeFirst() && !rs.isAfterLast()) return vResultHierType;
        while (rs.next()) {
          oHierType=new ObjHierType(rs.getString("TYPE_ID"),rs.getString("HIGHER_TYPE_ID"),rs.getString("NAME"),rs.getString("DESCRIPTION"));
          vResultHierType.add(oHierType);
        }
        rs.getStatement().close();
      }catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      return vResultHierType;
    }

     public Vector getHierType_ParentId(Vector vHierType, String parentId) {
       int i=0;
       Vector vHierTypeTmp = new Vector();
       String id=null;
       while (i < vHierType.size()) {
         oHierType = (ObjHierType) vHierType.get(i);
         id=oHierType.getHigherTypeID().toString().trim();
          if (id.equalsIgnoreCase(parentId)) {
            vHierTypeTmp.add(oHierType);
          }
          i += 1;
       }
       return vHierTypeTmp;
     }


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
     BNode currentNode;
     TreePath path = this.getSelectionPath();
     currentNode = (BNode) path.getLastPathComponent();
     return currentNode.getChildCount();
   }

   public String getParentId(String childId) {
     int i=0;
     String id=null;
     String parentId=null;
     if (vHierAll.size() <0) return Constant.root;
     while (i < vHierAll.size()) {
       oHier = (ObjHier) vHierAll.get(i);
       id=oHier.getChildID().toString().trim();
        if (id.equalsIgnoreCase(childId)) {
          parentId = oHier.getParentID().toString().trim();
          return parentId;
        }
       i += 1;
     }
     return parentId;
   }//end of method

  /** getParentId method
   * @author 		Tuan.Truong
   * @param 		None
   * @return		String ParentId
   */
  public String getParentId() {
      BNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BNode) path.getLastPathComponent();

      return currentNode.getParentID();
  }

  /** getChildId method
   * @author 		Tuan.Truong
   * @param 		None
   * @return		String ChildId
   */
  public String getChildId() {
      BNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BNode) path.getLastPathComponent();

      return currentNode.getNodeID();
  }

  /** getTypeId method
   * @author 		Tuan.Truong
   * @param 		TreeSelectionEvent e
   * @return		String TypeId
   */
  public String getTypeId() {
      BNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BNode) path.getLastPathComponent();

      return currentNode.getTypeID();
  }

  /** getNodeName method
   * @author 		Tuan.Truong
   * @param 		TreeSelectionEvent e
   * @return		String NodeName
   */
  public String getNodeName() {
      BNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BNode) path.getLastPathComponent();

      return currentNode.getName();
  }//end method

  /** getParentName method
   * @author 		Tuan.Truong
   * @param 		None
   * @return		String NodeName
   */
  public String getParentName() {
      BNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BNode) path.getLastPathComponent();

      return currentNode.getParentName();
  }//end method

  /** getNodeLeaf method
   * @author 		Tuan.Truong
   * @param 		TreeSelectionEvent e
   * @return		Boolean NodeLeaf
   */
  public boolean isLeaf() {
      BNode currentNode;

      TreePath path = this.getSelectionPath();
      currentNode = (BNode) path.getLastPathComponent();

      return currentNode.isLeaf();
  }//end method

  /** getNodeLeaf method
   * @author 		Tuan.Truong
   * @param 		TreeSelectionEvent e
   * @return		Boolean NodeLeaf
   */
  public boolean isEndOfHier(String typeId) {
    if (typeId.equalsIgnoreCase(getTypeId().toString())) return true;
    else return false;
  }//end method

  public boolean isEndOfHierType(String childId) {
    if (childId.equalsIgnoreCase(getChildId().toString())) return true;
    else return false;
  }//end method

  /** Set focus on selected node by ID
  * @param 	root root of tree
  * @param 	nameNode name of  node which focus on
  * @return	no
  */
  public void setSelectionPathByNameNode(BNode root,String idNode){
    BNode node=null;
    if(root.getNodeID().equals(idNode)) this.setSelectionPath(new TreePath(root.getPath()));
    for (int i = 0; i < root.getChildCount(); i++) {
        if(root.getChildAt(i).getNodeID().equals(idNode)){
          node= (BNode)root.getChildAt(i);
          this.setSelectionPath(new TreePath(node.getPath()));
          return;
        }
        if(root.getChildAt(i).getChildCount()>0){
          setSelectionPathByNameNode((BNode)root.getChildAt(i),idNode);
        }
    }
  }

}
