package sim;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import common.*;
import common.ExceptionHandle;
import common.Utils;

import common.Constant;
import java.awt.event.*;
import java.sql.*;
import java.sql.Statement;
import javax.swing.event.*;
import java.util.*;
import btm.attr.*;
import btm.attr.*;
import btm.swing.*;
import java.beans.*;
import javax.swing.border.*;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Hierachy Setup -> Prod Hiechy</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truongx
 * @version 1.0
 */

public class PanelPrdHierSetup extends JPanel {
  DataAccessLayer DAL;
  Utils ut = new Utils();
  JOptionPane jOpt = new JOptionPane();

  //assign for Prod Hier Type
  int lenCode=4;// if change you must change getMinTypeId_HierType() to make sure use len code=3 in getMinTypeId_HierType()
  int currentLevel=0;
  ObjHierType prdHierType=new ObjHierType();
  Vector vPrdHierType=new Vector();
  //assign for Prod Hier Type
  ObjHier prdHier=new ObjHier();
  Vector vPrdHierAll=new Vector();//all
  Vector vPrdHierActive=new Vector();
  //assign for treeview
  boolean value=false;
  String inValidChildID = "";
  String typeId_EOH = null;
  String currParentId = null;
  String currParrentTypeName = null;
  String currChildId = null;
  String currTypeId = null;
  String currTypeName = null;
  String currNodeName = null;
  String currParentName = null;
  boolean currLeaf = false;
  boolean currEOH = false;
  String currSubTypeName = null;
  String currSubTypeId = null;
//Right
  boolean add = false;
  boolean delete = false;

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel pnlPrdHierSetup = new JPanel();
  BJButton btnPrdHierCancel = new BJButton();
  BJButton btnPrdHierDel = new BJButton();
  BJButton btnPrdHierDone = new BJButton();
  BJButton btnPrdHierAdd = new BJButton();
  FlowLayout flowLayout4 = new FlowLayout();

  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  JPanel jPanel1 = new JPanel();
  JLabel jLabel4 = new JLabel();
  JPanel jPanel3 = new JPanel();
  JTextField txtName = new JTextField();
  JSplitPane jSplitPane1 = new JSplitPane();

  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JScrollPane jScrollTreeView = new JScrollPane();
  BorderLayout borderLayout3 = new BorderLayout();
  BNode rootTree ;
  BTreeModel pTreeModel;
  BJTree tree;
  Border border1;
  TitledBorder titledBorder1;
  JPanel jPanel4 = new JPanel();
  JLabel lblAddress = new JLabel();
  BorderLayout borderLayout4 = new BorderLayout();
  JLabel lblTypeId = new JLabel();
  JLabel lblParentId = new JLabel();
  JTextField txtParentName = new JTextField();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JTextField txtDesc = new JTextField();
  DefaultTreeCellRenderer treeRenderer = new DefaultTreeCellRenderer();
  BJButton btnRename = new BJButton();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelPrdHierSetup(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      this.cardLayout = crdLayout;
      this.parent = parent;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(6,6,6,6);
    titledBorder1 = new TitledBorder("");
    registerKeyboardActions();
//    selectChildNameNode=null;
    rootTree =new BNode( new BNodeDetail(Constant.rootId,Constant.rootId,Constant.rootId,Constant.root, Constant.root));
    pTreeModel = new BTreeModel(rootTree);
    tree=new BJTree(pTreeModel);

    pnlPrdHierSetup.setBackground(new Color(121, 152, 198));
    pnlPrdHierSetup.setMinimumSize(new Dimension(489, 51));
    pnlPrdHierSetup.setPreferredSize(new Dimension(10, 50));
    pnlPrdHierSetup.setLayout(flowLayout4);
    this.setLayout(borderLayout1);
    btnPrdHierCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrdHierCancel.setToolTipText(lang.getString("Cancel")+"(F12)");
    btnPrdHierCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnPrdHierCancel.addActionListener(new PanelPrdHierSetup_btnPrdHierCancel_actionAdapter(this));
    btnPrdHierDel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrdHierDel.setNextFocusableComponent(btnPrdHierCancel);
    btnPrdHierDel.setToolTipText(lang.getString("Delete")+"(F8)");
    btnPrdHierDel.setText(lang.getString("Delete")+"(F8)");
    btnPrdHierDel.addActionListener(new PanelPrdHierSetup_btnPrdHierDel_actionAdapter(this));
    btnPrdHierDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrdHierDone.setNextFocusableComponent(btnPrdHierAdd);
    btnPrdHierDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnPrdHierDone.setText(lang.getString("Done")+"(F1)");
    btnPrdHierDone.addActionListener(new PanelPrdHierSetup_btnPrdHierDone_actionAdapter(this));
    btnPrdHierAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrdHierAdd.setNextFocusableComponent(btnPrdHierDel);
    btnPrdHierAdd.setToolTipText(lang.getString("Add")+"(F2)");
    btnPrdHierAdd.setText(lang.getString("Add")+"(F2)");
    btnPrdHierAdd.addActionListener(new PanelPrdHierSetup_btnPrdHierAdd_actionAdapter(this));
    jPanel1.setBackground(Color.white);
    jPanel1.setOpaque(true);
    jPanel1.setPreferredSize(new Dimension(57, 470));
    jPanel1.setLayout(borderLayout2);
    jLabel4.setBounds(new Rectangle(13, 83, 82, 22));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("Description")+":");
    jPanel3.setLayout(null);
    txtName.setNextFocusableComponent(txtDesc);
    txtName.setBounds(new Rectangle(94, 53, 248, 21));
        txtName.addKeyListener(new PanelPrdHierSetup_txtName_keyAdapter(this));
    jSplitPane1.setPreferredSize(new Dimension(57, 470));
    jSplitPane1.setLastDividerLocation(-1);
    jPanel2.setLayout(borderLayout3);
    tree.addTreeSelectionListener(new PanelPrdHierSetup_tree_treeSelectionAdapter(this));
    jPanel3.setOpaque(true);
    this.
        setNextFocusableComponent(tree);
    this.setPreferredSize(new Dimension(800, 600));
    tree.addPropertyChangeListener(new PanelPrdHierSetup_tree_propertyChangeAdapter(this));
    jPanel4.setLayout(borderLayout4);
    lblAddress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAddress.setBorder(BorderFactory.createEtchedBorder());
    lblAddress.setText(lang.getString("Root"));
    jPanel4.setPreferredSize(new Dimension(10, 10));
    lblTypeId.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTypeId.setText(lang.getString("TypeID"));
    lblTypeId.setBounds(new Rectangle(351, 53, 159, 23));
    lblParentId.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblParentId.setText(lang.getString("ParentID"));
    lblParentId.setBounds(new Rectangle(352, 22, 149, 23));
    //txtParentName.setFont(lang.getString("FontName_Label"), 1, 12);
    txtParentName.setEnabled(false);
    txtParentName.setText(lang.getString("ParentName"));
    txtParentName.setBounds(new Rectangle(95, 23, 248, 21));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setText(lang.getString("Parent")+":");
    jLabel5.setBounds(new Rectangle(15, 19, 81, 26));
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setText(lang.getString("Name")+":");
    jLabel6.setBounds(new Rectangle(14, 52, 82, 24));
    txtDesc.setNextFocusableComponent(btnPrdHierDone);
    txtDesc.setText("");
    txtDesc.setBounds(new Rectangle(94, 82, 247, 24));
    tree.setNextFocusableComponent(txtName);
    tree.setToolTipText(lang.getString("ProdHierarchy"));
    tree.addKeyListener(new PanelPrdHierSetup_tree_keyAdapter(this));
    tree.addMouseMotionListener(new PanelPrdHierSetup_tree_mouseMotionAdapter(this));
    btnRename.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnRename.setText(lang.getString("Rename")+"(F4)");
    btnRename.addActionListener(new PanelPrdHierSetup_btnRename_actionAdapter(this));
    pnlPrdHierSetup.add(btnPrdHierDone, null);
    pnlPrdHierSetup.add(btnPrdHierAdd, null);
    pnlPrdHierSetup.add(btnRename);
    pnlPrdHierSetup.add(btnPrdHierDel, null);
    pnlPrdHierSetup.add(btnPrdHierCancel, null);
    this.add(pnlPrdHierSetup, BorderLayout.NORTH);
    this.add(jPanel1,  BorderLayout.SOUTH);
    this.add(jPanel4, BorderLayout.CENTER);
    jPanel1.add(jSplitPane1,  BorderLayout.CENTER);
    jPanel3.add(txtParentName, null);
    jPanel3.add(txtName, null);
    jPanel3.add(jLabel6, null);
    jPanel3.add(jLabel5, null);
    jPanel3.add(lblTypeId, null);
    jPanel3.add(lblParentId, null);
    jPanel3.add(txtDesc, null);
    jPanel3.add(jLabel4, null);
    jSplitPane1.add(jPanel2, JSplitPane.LEFT);
    jPanel2.add(jScrollTreeView, BorderLayout.CENTER);
    jScrollTreeView.getViewport().add(tree, null);
    jSplitPane1.add(jPanel3, JSplitPane.RIGHT);

    jPanel4.add(lblAddress, BorderLayout.CENTER);
    jSplitPane1.setDividerLocation(300);
    setEnableAddButton(false);
    setEnableDelButton(false);
  }
  public void applyPermission() {
      EmpRight er = new EmpRight();
      er.initData(DAL, DAL.getEmpID());
      er.setData(DAL.getEmpID(), Constant.SCRS_PRD_HIER_TYPE_SETUP);
      add = er.getAdd();
      delete = er.getDelete();
    }

//---------------show data and show tree view
  public void showData() {

    assignHier();
    typeId_EOH = getEndTypeHier();
    showTree();
  }

  private void showTree() {
    tree.showHierTree(vPrdHierActive, rootTree);
    tree.updateUI();
//    tree.setSelectionPathByNameNode(rootTree, Constant.rootId);
  }
  private void assignHier() {
    vPrdHierAll = tree.initHierAll(frmMain.getDAL(), "BTM_IM_PROD_HIR");
    vPrdHierActive = tree.initHierActive(vPrdHierAll);
    vPrdHierType = tree.initHierType(frmMain.getDAL(), "BTM_IM_PROD_HIR_TYPE");
  }

    public void showVecHier(Vector vHier) {
      int i=0;
      ObjHier oHier=new ObjHier();
      String id1=null;
      while (i < vHier.size()) {
        oHier = (ObjHier) vHier.get(i);
        id1=oHier.getParentID().toString().trim();
//        System.out.println(id1);
        i += 1;
      }
    }

    //if PrdHierType not input any record then Hier not run
    public boolean checkPrdHierType() {
      if (vPrdHierType.size()<=0) return false;
      else return true;
    }

//not use now: TrungNT 13-06-2006
    private boolean checkDone() {
      Vector vListHierRoot = new Vector();
      Vector vListHierCond = new Vector();
      ObjHier oHier=new ObjHier();
      int i = 0;
      String parentId1 = null;
      String childId1 = null;
      String typeId1 = null;
      String parentId2 = null;
      String childId2 = null;
      vListHierRoot = getVecHier(vPrdHierActive, Constant.rootId); //select root
      while (i < vListHierRoot.size()) {
        oHier = (ObjHier) vListHierRoot.get(i);
        parentId1 = oHier.getParentID().toString().trim();
        childId1 = oHier.getChildID().toString().trim();
        typeId1 = oHier.getTypeID().toString().trim();
//        System.out.println(i + ". Parent Id : " + parentId1 + " - Child Id : " + childId1 + " - Type   Id : " + typeId1);
        vListHierCond = getVecHier(vPrdHierActive, childId1);
        if (vListHierCond.size()>0){
          if (!checkFullHier(vPrdHierActive, vListHierCond, typeId_EOH)) {
//            System.out.println("Stop");
            this.value = false;
            return false;
          }
        }else{
          this.value = false;
//          System.out.println("Stop at: " + childId1);
          this.inValidChildID = childId1;
          return false;
        }
        i +=1;
      }
      return true;
    }
    //check input hier, if full hier then return true, else false
    //parem source; v Condition, eohType
    //for ex : vListHier, vListCond, typeId of Hier Type
//Not use now
    private boolean checkFullHier(Vector vListSource, Vector vHier, String eohType) {
      Vector vListHierCond = new Vector();
      ObjHier oHier=new ObjHier();
      int i = 0;
      String parentId1 = null;
      String childId1 = null;
      String typeId1 = null;
      String parentId2 = null;
      String childId2 = null;
      int count=0;
      while (i < vHier.size()) {
        oHier = (ObjHier) vHier.get(i);
        parentId1 = oHier.getParentID().toString().trim();
        childId1 = oHier.getChildID().toString().trim();
        typeId1 = oHier.getTypeID().toString().trim();
//        System.out.println(i + ". Parent Id : " + parentId1 + " - Child Id : " + childId1 + " - Type   Id : " + typeId1);
        i +=1;
        if (typeId1.equals(eohType)) {
//          System.out.println("Nhanh nay " + parentId1 + " good ");
          this.value = true;
          return this.value;
        }else {
            vListHierCond = getVecHier(vPrdHierActive, childId1); //select root
            if (vListHierCond.size()>0){
              checkFullHier(vPrdHierActive, vListHierCond, typeId_EOH);
            }else{
              this.value = false;
              this.inValidChildID = childId1;
              return this.value;
            }
            if (this.value){
              return this.value;
            }else{
              return this.value;
            }
          }//else

      }
      return this.value;
    }




    public Vector getVecHier(Vector vHier, String parentId) {
      int i=0;
      Vector vHierTmp = new Vector();
      ObjHier oHier=new ObjHier();
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

//----------------------------------------------------------

//---------------check Enable for all objects
  /** checkValue method - if value <> -1 then add and delete button will enable = true, else = false
   * @author 		Tuan.Truong
   * @param 		no
   * @return		true/false
   */
  private boolean checkEnableAdd(boolean leaf, boolean eoh){
    if (leaf && eoh) {
      return false;
    }else {
      return true;
    }
  }

  /** setEnableAddButton method - if value ==true add button will enable = true, else = false
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  private void setEnableAddButton(boolean value){
    if (value) {//true
      if (add) btnPrdHierAdd.setEnabled(true);
      else btnPrdHierAdd.setEnabled(false);
    }else {
      btnPrdHierAdd.setEnabled(false);
    }

  }

  /** setEnableText method - if value ==true add button will enable = true, else = false
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  private void setEnableText(boolean value){
    if (value == true) {
      this.txtDesc.setEnabled(true);
      this.txtName.setEnabled(true);
    }else {
      this.txtDesc.setEnabled(false);
      this.txtName.setEnabled(false);
    }
  }

  /** setEnableDelButton method - if value ==true add button will enable = true, else = false
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  private void setEnableDelButton(boolean value){
    if (value == true) {
     if (delete) btnPrdHierDel.setEnabled(true);
     else btnPrdHierDel.setEnabled(false);
   }else {
     btnPrdHierDel.setEnabled(false);
   }
  }
//-----------------------------------------------------------------

//----------get value from vector
  //get Name of Hier Type from parentId
  private String getName_HierType_ParentId(String parentId) {
    int i=0;
    String id=null;
    String name=null;
    if (vPrdHierType.size() <0) return Constant.root;
    while (i < vPrdHierType.size()) {
      prdHierType = (ObjHierType) vPrdHierType.get(i);
      id=prdHierType.getHigherTypeID().toString().trim();
      if (id.equalsIgnoreCase(parentId)) {
        name=prdHierType.getTypeName().toString().trim();
        return name;
      }
      i += 1;
    }
    if (name == null) name="X";
 return name;
 }//end of method


 private String getTypeId_HierType() {
   int i=0;
   String id=null;
   String typeId=null;
   if (vPrdHierType.size() <0) return Constant.root;
   while (i < vPrdHierType.size()) {
     prdHierType = (ObjHierType) vPrdHierType.get(i);
     id=prdHierType.getHigherTypeID().toString().trim();
     if (id.equalsIgnoreCase(Constant.rootId)) {
       typeId=prdHierType.getTypeID().toString().trim();
       return typeId;
     }
     i += 1;
   }
   return typeId;
 }//end of method

 private String getNameHierType_TypeId(String typeId) {
   int i=0;
   String id=null;
   String name=null;
   if (typeId.equalsIgnoreCase(Constant.rootId)) return Constant.root;
   if (vPrdHierType.size() <0) return Constant.root;
   while (i < vPrdHierType.size()) {
     prdHierType = (ObjHierType) vPrdHierType.get(i);
     id=prdHierType.getTypeID().toString().trim();
     if (id.equalsIgnoreCase(typeId)) {
       name=prdHierType.getTypeName().toString().trim();
       return name;
     }
     i += 1;
   }
   return name;
 }//end of method

 //get TypeId of Hier Type from parentId
 private String getTypeId_HierType_ParentId(String parentId) {
   int i=0;
   String id=null;
   String typeId=null;
   if (vPrdHierType.size() <0) return Constant.root;
   if (parentId.equalsIgnoreCase(Constant.rootId)) {
     typeId=getMinTypeId_HierType();
     return typeId;
   }
   while (i < vPrdHierType.size()) {
     prdHierType = (ObjHierType) vPrdHierType.get(i);
     id=prdHierType.getHigherTypeID().toString().trim();
     if (id.equalsIgnoreCase(parentId)) {
       typeId=prdHierType.getTypeID().toString().trim();
       return typeId;
     }
     i += 1;
   }
   return typeId;
 }//end of method


 //get Child Id of Hier from ParentId -> ChildId;
 private String getChildId_Hier_ParentId(String parentId) {
   int i=0;
   String id=null;
   String childId=null;
   if (parentId.equalsIgnoreCase(Constant.rootId)) return Constant.rootId;
   if (vPrdHierActive.size() <= 0 ) return Constant.rootId;
   while (i < vPrdHierActive.size()) {
     prdHier = (ObjHier) vPrdHierActive.get(i);
     id=prdHier.getParentID().toString().trim();
     if (id.equalsIgnoreCase(parentId)) {
       childId = prdHier.getChildID().toString().trim();
     }
     i += 1;
   }
   return childId;
 }//end of method

 //from TypeId of Hier from ChildId;
 private String getTypeId_Hier_ChildId(String childId) {
   int i=0;
   String id=null;
   String typeId=null;
   if (childId.equalsIgnoreCase(Constant.rootId)) return Constant.rootId;
   if (vPrdHierActive.size() <= 0 ) return Constant.rootId;
   while (i < vPrdHierActive.size()) {
     prdHier = (ObjHier) vPrdHierActive.get(i);
     id=prdHier.getChildID().toString().trim();
     if (id.equalsIgnoreCase(childId)) {
       typeId = prdHier.getTypeID().toString().trim();
     }
     i += 1;
   }
   return typeId;
 }//end of method

 //Get Max
 private String getMaxChildId() {
   int i = 0;
   int k1= 0;
   int kMax= 0;
   String id1 = null;
   String idMax=null;
   if (vPrdHierAll.size() <= 0 ) return Constant.rootId;
   prdHier = (ObjHier) vPrdHierAll.get(0);
   idMax = prdHier.getChildID().toString().trim();
   kMax = Integer.valueOf(idMax).intValue();
   while (i < vPrdHierAll.size()) {
     prdHier = (ObjHier) vPrdHierAll.get(i);
     id1 = prdHier.getChildID().toString().trim();
     k1 = Integer.valueOf(id1).intValue();
     if (k1>=kMax) kMax = k1;
     i += 1;
   }
   idMax = ut.getYeroCode(kMax,lenCode);
   return idMax;
}//end of method

 //Get Min TypeId from Hier Type
 private String getMinTypeId_HierType() {
   int i = 0;
   int k1= 0;
   int kMin= 0;
   String id1 = null;
   String idMin=null;
   if (vPrdHierType.size() <= 0 ) return Constant.rootId;
   prdHierType = (ObjHierType) vPrdHierType.get(0);
   idMin = prdHierType.getTypeID().toString().trim();
   kMin = Integer.valueOf(idMin).intValue();
   while (i < vPrdHierType.size()) {
     prdHierType = (ObjHierType) vPrdHierType.get(i);
     id1 = prdHierType.getTypeID().toString().trim();
     k1 = Integer.valueOf(id1).intValue();
     if (kMin>k1) kMin = k1;
     i += 1;
   }
   idMin = ut.getYeroCode(kMin,lenCode-1);
   return idMin;
 }//end of method

 /** addPrdHierSetup method - insert data into table
  * @author 		Tuan.Truong
  * @param 		no
  * @return		no
  */
 void addPrdHierSetup(String parentId, String childId, String typeId, String childName, String parentName, String desc) {

   String sysDay = ut.getSystemDate();
   String sSQL = "insert into BTM_IM_PROD_HIR (PARENT_ID, CHILD_ID, TYPE_ID, RECD_LOAD_DATE, RECD_LAST_UPD_DATE, RECD_CURR_FLAG, RECD_CLOSED_DATE,CHILD_NAME, PARENT_NAME, DESCRIPTION, PURCHASE_TYPE_ID) values ('"
                     + parentId + "','" + childId + "','" + typeId
                     + "',to_date('" + sysDay + "','yyyy-mm-dd'),to_date('" + sysDay + "','yyyy-mm-dd'),'1',to_date('7777-7-7','yyyy-mm-dd'),'"
                     + childName + "','" + parentName + "','" + desc + "','1')";
//   System.out.println(sSQL);
   try{
     Statement stmt = DAL.getConnection().createStatement();
     DAL.executeUpdate(sSQL,stmt);
     stmt.close();
   }catch(Exception e){

   }

 }//end of method

 private boolean checkBarCodeSeq(String childId) {

   String sSQL = "select SBCLASS_ID, BARCODE_SEQ from BTM_IM_BARCODE_SEQ where SBCLASS_ID = '" + childId+ "'";
   ResultSet rs = null;
   Statement stmt = null;
//   System.out.println(sSQL);
   try {
     stmt = DAL.getConnection().createStatement();
     rs = DAL.executeQueries(sSQL,stmt);
     if (rs.next()) {
       rs.close();
       stmt.close();
       return true;
     }
     rs.close();
     stmt.close();
   }catch (Exception e) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }

   return false;
 }
 /** addBarCodeSeq method - insert data into table
  * @author 		Tuan.Truong
  * @param 		no
  * @return		no
  */
 void addBarCodeSeq(String childId) {

   String sSQL = "insert into BTM_IM_BARCODE_SEQ (SBCLASS_ID, BARCODE_SEQ) values ('" + childId + "','0001')";
//   System.out.println(sSQL);
   try {
     Statement stmt = DAL.getConnection().createStatement();
     DAL.executeUpdate(sSQL,stmt);
     stmt.close();
   }catch (Exception e) {
     e.printStackTrace();
   }

 }//end of method

 //add data into table BTM_IM_MAP_DEPARTMENT
 void addMapDept(String childId) {

   String mapDept = getNewMapDept();
   String sSQL = "insert into BTM_IM_MAP_DEPARTMENT (DEPT_ID, MAPPING_NO) values ('" + childId + "','" + mapDept + "')";
//   System.out.println(sSQL);
   try {
     Statement stmt = DAL.getConnection().createStatement();
     DAL.executeUpdate(sSQL,stmt);
     stmt.close();
   }catch (Exception e) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }

   }

   private String getNewMapDept() {

     String newMapDept = null;
     String sSQL = "select DEPT_ID, MAPPING_NO from BTM_IM_MAP_DEPARTMENT order by MAPPING_NO desc";
     ResultSet rs = null;
     Statement stmt = null;
     try {
//       System.out.println(sSQL);
       stmt = DAL.getConnection().createStatement();
       rs = DAL.executeQueries(sSQL,stmt);
       if (!rs.isBeforeFirst() && !rs.isAfterLast()) return "01";
       rs.next();
       int k = Integer.parseInt(rs.getString("MAPPING_NO").toString())+1;
       newMapDept = ut.getYeroCode(k,2);
       rs.close();
       stmt.close();
     }catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }

     return newMapDept;
   }

   /*Desc: check whether Dept is available in table BTM_IM_MAP_DEPARTMENT or not
    @author: Phuong Nguyen
    @param:  childId - Dept need to check
    @return: true/false
   */
     private boolean checkMapDept(String childId) {

       String sSQL = "select DEPT_ID, MAPPING_NO from BTM_IM_MAP_DEPARTMENT where DEPT_ID = '" + childId+ "'";
       ResultSet rs = null;
       Statement stmt = null;
//       System.out.println(sSQL);
       try {
         stmt = DAL.getConnection().createStatement();
         rs = DAL.executeQueries(sSQL,stmt);
         if (rs.next())
           return true;
         rs.close();
         stmt.close();
       }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }
       return false;
     }

   //add data into table BTM_IM_MAP_CATEGORY
   void addMapCat(String childId) {
       String mapCat = getNewMapCat();
     String sSQL = "insert into BTM_IM_MAP_CATEGORY (CATEGORY_ID, MAPPING_NO) values ('" + childId + "','" + mapCat + "')";
//     System.out.println(sSQL);
     try {
       Statement stmt = DAL.getConnection().createStatement();
       DAL.executeUpdate(sSQL,stmt);
       stmt.close();
     }catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
     }

     private String getNewMapCat() {
       String newMapCat = null;
       String sSQL = "select CATEGORY_ID, MAPPING_NO from BTM_IM_MAP_CATEGORY order by MAPPING_NO desc";
       ResultSet rs = null;
       Statement stmt = null;
       try {
//         System.out.println(sSQL);
         stmt = DAL.getConnection().createStatement();
         rs = DAL.executeQueries(sSQL,stmt);
         if (!rs.isBeforeFirst() && !rs.isAfterLast()) return "001";
         rs.next();
         int k = Integer.parseInt(rs.getString("MAPPING_NO").toString())+1;
         newMapCat = ut.getYeroCode(k,3);
         rs.close();
         stmt.close();
       }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }

       return newMapCat;
     }

     /*Desc: check whether Category is available in table BTM_IM_MAP_CATEGORY or not
       @author: Phuong Nguyen
       @param:  childId - Category need to check
       @return: true/false
      */
    private boolean checkMapCat(String childId) {
      String sSQL = "select CATEGORY_ID, MAPPING_NO from BTM_IM_MAP_CATEGORY where CATEGORY_ID = '" + childId+ "'";
      ResultSet rs = null;
      Statement stmt = null;
//      System.out.println(sSQL);
      try {
        stmt = DAL.getConnection().createStatement();
        rs = DAL.executeQueries(sSQL,stmt);
        if (rs.next()) {
          stmt.close();
          return true;
        }
        rs.close();
        stmt.close();
      }catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      return false;
    }

     //add data into table BTM_IM_MAP_CATEGORY
     void addMapSubCat(String childId) {
       String mapSubCat = getNewMapSubCat();
       String sSQL = "insert into BTM_IM_MAP_SUBCATEGORY (SUBCATEGORY_ID, MAPPING_NO) values ('" + childId + "','" + mapSubCat + "')";
//       System.out.println(sSQL);
       try {
         Statement stmt = DAL.getConnection().createStatement();
         DAL.executeUpdate(sSQL,stmt);
         stmt.close();
       }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }
       }

       private String getNewMapSubCat() {
         String newMapSubCat = null;
         String sSQL = "select SUBCATEGORY_ID, MAPPING_NO from BTM_IM_MAP_SUBCATEGORY order by MAPPING_NO desc";
         ResultSet rs = null;
         Statement stmt = null;
         try {
//           System.out.println(sSQL);
           stmt = DAL.getConnection().createStatement();
           rs = DAL.executeQueries(sSQL,stmt);
           if (!rs.isBeforeFirst() && !rs.isAfterLast()) return "001";
           rs.next();
           int k = Integer.parseInt(rs.getString("MAPPING_NO").toString())+1;
           newMapSubCat = ut.getYeroCode(k,3);
           rs.close();
           stmt.close();
         }catch (Exception e) {
           ExceptionHandle eh = new ExceptionHandle();
           eh.ouputError(e);
         }

         return newMapSubCat;
       }

       /*Desc: check whether SubCategory is available in table BTM_IM_MAP_SUBCATEGORY or not
        @author: Phuong Nguyen
        @param:  childId - SubCat need to check
        @return: true/false
       */
         private boolean checkMapSubCat(String childId) {

           String sSQL = "select SUBCATEGORY_ID, MAPPING_NO from BTM_IM_MAP_SUBCATEGORY where SUBCATEGORY_ID = '" + childId+ "'";
           ResultSet rs = null;
           Statement stmt = null;
//           System.out.println(sSQL);
           try {
             stmt = DAL.getConnection().createStatement();
             rs = DAL.executeQueries(sSQL,stmt);
             if (rs.next())
               return true;
             rs.close();
             stmt.close();
           }catch (Exception e) {
             ExceptionHandle eh = new ExceptionHandle();
             eh.ouputError(e);
           }
           return false;
         }

  /** deleteBarCodeSeq method - delete barcode seq
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void deleteBarCodeSeq(String childId) {
//    setOpenConnection();
    if (childId ==null) return;

    String sSQL = "delete BTM_IM_BARCODE_SEQ where SBCLASS_ID='" + childId + "'";
//    System.out.println(sSQL);
    try {
      Statement stmt = DAL.getConnection().createStatement();
      DAL.executeQueries(sSQL,stmt);
      stmt.close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }//end of method

//delete Department in map table
  void deleteMapDept(String childId) {
    if (childId ==null) return;

    String sSQL = "delete BTM_IM_MAP_DEPARTMENT where DEPT_ID='" + childId + "'";
//    System.out.println(sSQL);
    try {
      Statement stmt = DAL.getConnection().createStatement();
      DAL.executeQueries(sSQL,stmt);
      stmt.close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

  }//end of method
  //delete Category in map table
  void deleteMapCat(String childId) {
    if (childId ==null) return;

    String sSQL = "delete BTM_IM_MAP_CATEGORY where CATEGORY_ID='" + childId + "'";
//    System.out.println(sSQL);
    try {
      Statement stmt = DAL.getConnection().createStatement();
      DAL.executeQueries(sSQL,stmt);
      stmt.close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

  }//end of method
//delete SupCategory in map table
  void deleteMapSubCat(String childId) {
    if (childId ==null) return;

    String sSQL = "delete BTM_IM_MAP_SUBCATEGORY where SUBCATEGORY_ID='" + childId + "'";
//    System.out.println(sSQL);
    try {
      Statement stmt = DAL.getConnection().createStatement();
      DAL.executeQueries(sSQL,stmt);
      stmt.close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

  }//end of method

  /** getNewParentId method - get new Parent Id
   * @author 		Tuan.Truong
   * @param 		None
   * @return		parentID
   */
   private String getNewParentId(String childId) {
//     System.out.println("new childId " + childId);
     if (childId==null) return Constant.rootId;
//     if (childId.equalsIgnoreCase(Constant.root)) return Constant.rootId;
//     if (childId.equalsIgnoreCase(Constant.rootId)) return Constant.startId;
     return childId;
   }//end method

  /** getNewChildID method - get new Child ID
   * @author 		Tuan.Truong
   * @param 		None
   * @return		ChildId
   */
   private String getNewChildId() {
     String childId=null;
     if (vPrdHierAll.size() <= 0 ) return Constant.startId;
     else {
       String id = getMaxChildId();
//       System.out.println("Max Child " + id);
       int k = Integer.valueOf(id).intValue()+1;
       childId =  ut.getYeroCode(k, lenCode);
     }
     return childId;
   }//end method get ChildID

   /** getNewTypeId method - get new Type Id
    * @author 		Tuan.Truong
    * @param 		None
    * @return		typeID
    */
    private String getNewTypeId(String typeId) {
      if (typeId==null) return null;
      return typeId;
    }//end method get typeId

     private String getNewParentName(String typeId, String parentName) {
       if (typeId==null) return null;
       if (typeId.equalsIgnoreCase(Constant.rootId)) return Constant.root;
       return parentName;
     }//end method get typeId

   public String getEndTypeHier(){
     String typeId=null;
     if (vPrdHierType.size()<=0) return Constant.rootId;
     prdHierType = (ObjHierType) vPrdHierType.get(vPrdHierType.size()-1);
     typeId=prdHierType.getTypeID();
     return typeId;
   }

   /** btnPrdHierCancel method
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   void btnPrdHierCancel_actionPerformed(ActionEvent e) {
//     if (!checkDone()){
//       ut.showMessage(frmMain,"Warning","Product Hierchy is not valid!");
//       tree.setSelectionPathByNameNode(rootTree,this.inValidChildID);
//       return;
//     }
     frmMain.showScreen(Constant.SCRS_MAIN_SIM);
     frmMain.setTitle(lang.getString("TT0105"));

   }


   /** tree click - when click on treeview
    * @author 		Tuan.Truong
    * @param 		String name
    * @return		select Name node
    */
  void tree_valueChanged(TreeSelectionEvent e) {
       boolean check;
       if (e.getPath() != null){
         if (vPrdHierActive.size() == 0) {//view
           currParentId = Constant.rootId;
           currChildId = Constant.rootId;
           currTypeId = tree.getTypeId();
           currTypeName = Constant.root;
           currParentName = Constant.rootId;
           currNodeName = tree.getNodeName();
           currSubTypeName = getName_HierType_ParentId(tree.getTypeId());
           currSubTypeId = getTypeId_HierType();
           currLeaf = false;
           currEOH = true;
         }else {
           if (tree.getSelectionPath() != null){
             TreePath tpath = e.getPath();
             Object[] path = new Object[tpath.getPathCount()];
             currentLevel=tpath.getPathCount();
             currParentId = tree.getParentId();
             currParrentTypeName = getNameHierType_TypeId(getTypeId_Hier_ChildId(
                 tree.getParentId()));
             currChildId = tree.getChildId();
             currTypeId = tree.getTypeId();
             currTypeName = getNameHierType_TypeId(tree.getTypeId());
             currParentName = tree.getParentName();
             currNodeName = tree.getNodeName();
             currSubTypeName = getName_HierType_ParentId(tree.getTypeId());
             currSubTypeId = getTypeId_HierType_ParentId(tree.getTypeId());
             currLeaf = tree.isLeaf();
             currEOH = tree.isEndOfHier(typeId_EOH);
           }
         }
/*
//================
           System.out.println("----------------------------------");
           System.out.println("1. Parent Id       : " + currParentId);
           System.out.println("2. Child Id        : " + currChildId);
           System.out.println("3. Type Id         : " + currTypeId);
           System.out.println("4. Parent Name     : " + currParentName);
           System.out.println("5. Node Name       : " + currNodeName);
           System.out.println("6. Parent Type Name: " + currParrentTypeName);
           System.out.println("7.        Type Name: " + currTypeName);
           System.out.println("8.    Sub Type Name: " + currSubTypeName);
           System.out.println("9.    Sub Type ID  : " + currSubTypeId);
           System.out.println("10. Leaf           : " + currLeaf);
           System.out.println("11. end of Hier    : " + currEOH);
//           System.out.println("Pa : " + (BNode)tree.getParent().getName());
//================
 */
         this.lblAddress.setText(" " + tree.getStringPath(e));
         this.lblParentId.setText("(" + currTypeName + ")");
         this.lblTypeId.setText("(" + currSubTypeName + ")");
         this.txtParentName.setText(currNodeName);
         check = checkEnableAdd(currLeaf, currEOH);
         setEnableAddButton(check);
         setEnableText(check);
         if (currLeaf && !currNodeName.equalsIgnoreCase(Constant.root)) { setEnableDelButton(true);
         } else
           setEnableDelButton(false);
       }

  }//end of event


  /** btnPrdHierDel Button - Delete node
   * @author 		Tuan.Truong
   * @param 		String name
   * @return		no
   */
  void btnPrdHierDel_actionPerformed(ActionEvent e) {
    String childId = currChildId;
    String parentId = currParentId;
    if (checkItemMaster(childId)) {
        ut.showMessage(frmMain,lang.getString("TT019"),lang.getString("MS1130_CanNotDeleteNodeBecauseContainingItem"));
        return;
    }
    int i = ut.showMessage(frmMain,lang.getString("TT018"),lang.getString("MS1131_SureDelete") + currNodeName + "' ?",DialogMessage.QUESTION_MESSAGE,DialogMessage.YES_NO_OPTION);
    if (i == DialogMessage.YES_VALUE){
          deletePrdHier(childId);
          deleteBarCodeSeq(childId);
          deleteMapDept(childId);
          deleteMapCat(childId);
          deleteMapSubCat(childId);
          assignHier();
          showTree();
          tree.setSelectionPathByNameNode(rootTree,parentId);
    }
  }//end delete


  /** deletePrdHier Method - Delete node from database
   * @author 		Tuan.Truong
   * @param 		String name
   * @return		no
   */
  void deletePrdHier(String childId) {

    String ngay = ut.getSystemDate();
    String sSQL = "update BTM_IM_PROD_HIR set RECD_CURR_FLAG='0', RECD_CLOSED_DATE = to_date('" + ngay + "','yyyy-mm-dd') where CHILD_ID ='" + childId + "'";
    try{
      Statement stmt = DAL.getConnection().createStatement();
//      System.out.println(sSQL);
      DAL.executeUpdate(sSQL,stmt);
      stmt.close();
    }catch (Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

  }

  private boolean checkItemMaster(String childId) {
    boolean chk=false;
    ResultSet rs = null;
    Statement stmt = null;

    String sSQL = "select ITEM_ID from BTM_IM_ITEM_MASTER where CHILD_ID='" + childId + "'";
    try {
//      System.out.println(sSQL);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sSQL,stmt);
      if (rs.next()) {
        chk=true;
      }else {
        chk=false;
      }
      rs.close();
      stmt.close();
    }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
    }

    return chk;
  }

    void txtName_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e,txtName,prdHier.LEN_DETAIL_NAME);
    }

    void txtDesc_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e,txtDesc,prdHier.LEN_DETAIL_DESC);
    }

  void tree_propertyChange(PropertyChangeEvent e) {

  }

  void btnPrdHierDone_actionPerformed(ActionEvent e) {
//    if (!checkDone()){
//      ut.showMessage(frmMain,"Warning","Product Hierchy is not valid!");
//      tree.setSelectionPathByNameNode(rootTree,this.inValidChildID);
//      return;
//    }
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0105"));


  }
  private void registerKeyboardActions() {
   /// set up the maps:
   InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
   ActionMap actionMap = getActionMap();

   // F1
   Integer key = new Integer(KeyEvent.VK_F1);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), key);
   actionMap.put(key, new KeyAction(key));

   // F2
   key = new Integer(KeyEvent.VK_F2);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), key);
   actionMap.put(key, new KeyAction(key));
   // F4
   key = new Integer(KeyEvent.VK_F4);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), key);
   actionMap.put(key, new KeyAction(key));

   // F8
   key = new Integer(KeyEvent.VK_F8);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), key);
   actionMap.put(key, new KeyAction(key));

   // F12
   key = new Integer(KeyEvent.VK_F12);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
   actionMap.put(key, new KeyAction(key));

   // ESCAPE
      key = new Integer(KeyEvent.VK_ESCAPE);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
      actionMap.put(key, new KeyAction(key));
 }
 class KeyAction extends AbstractAction {

      private Integer identifier = null;

      public KeyAction(Integer identifier) {
        this.identifier = identifier;
      }

      /**
       * Invoked when an action occurs.
       */
      public void actionPerformed(ActionEvent e) {

        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnPrdHierDone.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F2) {
          btnPrdHierAdd.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F4) {
          btnRename.doClick();
        }

        else if (identifier.intValue() == KeyEvent.VK_F8) {
          btnPrdHierDel.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnPrdHierCancel.doClick();
        }
      }
    }

    /** btnPrdHierAdd Button
     * @author 		Tuan.Truong
     * @param 		no
     * @return		no
     */
     void btnPrdHierAdd_actionPerformed(ActionEvent e) {
       if (tree.getTypeId() == null) {
         ut.showMessage(frmMain, lang.getString("TT019"), lang.getString("MS1132_ClickNodeTreeview"));
         return;
       }
       String name = this.txtName.getText().trim();
       String desc = this.txtDesc.getText().trim();
       //if Name field and desc have text something like Men's then will return Men''s
       name = ut.putCommaToString(name);
       desc = ut.putCommaToString(desc);
       if (checkName(name)) {
         String newParrentId = getNewParentId(currChildId);
         assignHier();//to avoid many people input the time
         String newChildId = getNewChildId();
         String newTypeId = getNewTypeId(currSubTypeId);
         String parrentName = getNewParentName(currTypeId,currNodeName);
         addPrdHierSetup(newParrentId, newChildId, newTypeId, name, parrentName, desc);
         //insert data into BTM_IM_MAP_DEPARTMENT
//         if (tree.getPosNode()==((currentLevel-2))) {
         if (tree.getPosNode()==2) {
           if (!checkMapDept(newChildId))
             addMapDept(newChildId);
         }

         //insert data into BTM_IM_MAP_CATEGORY
//         if (tree.getPosNode()==(currentLevel-1)) {
         if (tree.getPosNode()==3) {
           if (!checkMapCat(newChildId))
             addMapCat(newChildId);
         }
         //insert data into BTM_IM_MAP_SUBCATEGORY
//         if (tree.getPosNode()==(currentLevel)) {
         if (tree.getPosNode()==4) {
           if (!checkMapSubCat(newChildId))
             addMapSubCat(newChildId);
           if (!checkBarCodeSeq(newChildId)) addBarCodeSeq(newChildId);
         }
         this.txtName.setText(null);
         this.txtDesc.setText(null);
         assignHier();
         tree.showHierTree(vPrdHierActive, rootTree);
         tree.updateUI();
         tree.setSelectionPathByNameNode(rootTree, newChildId);
       }
     }
     private boolean checkName(String name) {
       if ((name.length()==0) || (name==null)) {
         ut.showMessage(frmMain,lang.getString("TT019"),lang.getString("MS1133_NameNotEmpty"));
         this.txtName.setFocusable(true);
         return false;
       }
       return true;
     }

  void tree_mouseMoved(MouseEvent e) {
    if (tree.getPathForLocation(e.getX(),e.getY()) != null){
      String lastPath = tree.getPathForLocation(e.getX(), e.getY()).
          getLastPathComponent().toString();
      if (lastPath.equalsIgnoreCase("")) {
        return;
      }
      tree.setSelectionPath(tree.getPathForLocation(e.getX(),e.getY()));
    }
  }

  void tree_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnPrdHierAdd.doClick();
               }
  }

  public void btnRename_actionPerformed(ActionEvent e) {
    tree.setEditable(true) ;
    tree.startEditingAtPath(tree.getSelectionPath()) ;

  }

  public void tree_keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      updateNameNode (tree.getChildId(), tree.getNodeName());
      tree.setEditable(false) ;

    }

  }
  //update name of node
  void updateNameNode(String id, String name){
    String sql="";
    try {
      DAL.setBeginTrans(DAL.getConnection());
      sql="Update BTM_IM_PROD_HIR set child_name= '" + name + "' where child_id= "+id;
      DAL.executeUpdate(sql);
      sql="Update BTM_IM_PROD_HIR set parent_name= '" + name + "' where parent_id= "+id;
      DAL.executeUpdate(sql);
      DAL.setEndTrans(DAL.getConnection());
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

  }
}//End of class

class PanelPrdHierSetup_btnRename_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPrdHierSetup adaptee;
  PanelPrdHierSetup_btnRename_actionAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnRename_actionPerformed(e);
  }
}

class PanelPrdHierSetup_btnPrdHierCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierSetup adaptee;

  PanelPrdHierSetup_btnPrdHierCancel_actionAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierCancel_actionPerformed(e);
  }
}

class PanelPrdHierSetup_treProDetail_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  PanelPrdHierSetup adaptee;

  PanelPrdHierSetup_treProDetail_mouseMotionAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }
}

class PanelPrdHierSetup_tree_treeSelectionAdapter implements javax.swing.event.TreeSelectionListener {
  PanelPrdHierSetup adaptee;

  PanelPrdHierSetup_tree_treeSelectionAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(TreeSelectionEvent e) {
    adaptee.tree_valueChanged(e);
  }
}

class PanelPrdHierSetup_btnPrdHierDel_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierSetup adaptee;

  PanelPrdHierSetup_btnPrdHierDel_actionAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierDel_actionPerformed(e);
  }
}

class PanelPrdHierSetup_txtName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelPrdHierSetup adaptee;

    PanelPrdHierSetup_txtName_keyAdapter(PanelPrdHierSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtName_keyTyped(e);
    }
}

class PanelPrdHierSetup_tree_propertyChangeAdapter implements java.beans.PropertyChangeListener {
  PanelPrdHierSetup adaptee;

  PanelPrdHierSetup_tree_propertyChangeAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void propertyChange(PropertyChangeEvent e) {
    adaptee.tree_propertyChange(e);
  }
}

class PanelPrdHierSetup_btnPrdHierDone_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierSetup adaptee;

  PanelPrdHierSetup_btnPrdHierDone_actionAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierDone_actionPerformed(e);
  }
}

class PanelPrdHierSetup_btnPrdHierAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierSetup adaptee;

  PanelPrdHierSetup_btnPrdHierAdd_actionAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierAdd_actionPerformed(e);
  }
}

class PanelPrdHierSetup_tree_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  PanelPrdHierSetup adaptee;

  PanelPrdHierSetup_tree_mouseMotionAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseMoved(MouseEvent e) {
    adaptee.tree_mouseMoved(e);
  }
}

class PanelPrdHierSetup_tree_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPrdHierSetup adaptee;

  PanelPrdHierSetup_tree_keyAdapter(PanelPrdHierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tree_keyPressed(e);
  }

  public void keyReleased(KeyEvent e) {
    adaptee.tree_keyReleased(e);
  }
}

