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
import javax.swing.event.*;
import java.util.*;
import btm.attr.*;
import btm.attr.*;
import btm.swing.*;
import java.beans.*;
import javax.swing.border.*;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Hierachy Setup -> Prod Hiechy Type</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */

public class PanelPrdHierTypeSetup extends JPanel {
  DataAccessLayer DAL;
  Utils ut = new Utils();
  JOptionPane jOpt = new JOptionPane();

  //assign for Prod Hier Type
  ObjHierType prdHierType=new ObjHierType();
  Vector vListPrdHierType=new Vector();
  //assign for treeview
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

  //right
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
  JTextField txtParentName = new JTextField();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JTextField txtDesc = new JTextField();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelPrdHierTypeSetup(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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
    btnPrdHierCancel.addActionListener(new PanelPrdHierTypeSetup_btnPrdHierCancel_actionAdapter(this));
    btnPrdHierDel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrdHierDel.setNextFocusableComponent(btnPrdHierCancel);
    btnPrdHierDel.setToolTipText(lang.getString("Delete")+"(F8)");
    btnPrdHierDel.setText(lang.getString("Delete")+"(F8)");
    btnPrdHierDel.addActionListener(new PanelPrdHierTypeSetup_btnPrdHierDel_actionAdapter(this));
    btnPrdHierDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrdHierDone.setNextFocusableComponent(btnPrdHierAdd);
    btnPrdHierDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnPrdHierDone.setText(lang.getString("Done")+"(F1)");
    btnPrdHierDone.addActionListener(new PanelPrdHierTypeSetup_btnPrdHierDone_actionAdapter(this));
    btnPrdHierAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrdHierAdd.setNextFocusableComponent(btnPrdHierDel);
    btnPrdHierAdd.setToolTipText(lang.getString("Add")+"(F2)");
    btnPrdHierAdd.setText(lang.getString("Add")+"(F2)");
    btnPrdHierAdd.addActionListener(new PanelPrdHierTypeSetup_btnPrdHierAdd_actionAdapter(this));
    jPanel1.setBackground(Color.white);
    jPanel1.setOpaque(true);
    jPanel1.setPreferredSize(new Dimension(57, 470));
    jPanel1.setLayout(borderLayout2);
    jLabel4.setBounds(new Rectangle(13, 84, 82, 22));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("Description")+":");
    jPanel3.setLayout(null);
    txtName.setNextFocusableComponent(txtDesc);
    txtName.setBounds(new Rectangle(94, 53, 248, 24));
        txtName.addKeyListener(new PanelPrdHierTypeSetup_txtName_keyAdapter(this));
    jSplitPane1.setPreferredSize(new Dimension(57, 470));
    jSplitPane1.setLastDividerLocation(-1);
    jPanel2.setLayout(borderLayout3);
    tree.addTreeSelectionListener(new PanelPrdHierTypeSetup_tree_treeSelectionAdapter(this));
    jPanel3.setOpaque(true);
    this.setNextFocusableComponent(tree);
    this.setPreferredSize(new Dimension(800, 600));
    tree.addPropertyChangeListener(new PanelPrdHierTypeSetup_tree_propertyChangeAdapter(this));
    jPanel4.setLayout(borderLayout4);
    lblAddress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAddress.setBorder(BorderFactory.createEtchedBorder());
    lblAddress.setText(lang.getString("Root"));
    jPanel4.setPreferredSize(new Dimension(10, 10));
    txtParentName.setEnabled(false);
    txtParentName.setText(lang.getString("ParentName"));
    txtParentName.setBounds(new Rectangle(95, 21, 248, 25));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setText(lang.getString("Parent")+":");
    jLabel5.setBounds(new Rectangle(15, 19, 81, 26));
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setText(lang.getString("Name")+":");
    jLabel6.setBounds(new Rectangle(14, 52, 82, 24));
    txtDesc.setNextFocusableComponent(btnPrdHierDone);
    txtDesc.setText("");
    txtDesc.setBounds(new Rectangle(93, 83, 249, 24));
    tree.setNextFocusableComponent(txtName);
    tree.addKeyListener(new PanelPrdHierTypeSetup_tree_keyAdapter(this));
    pnlPrdHierSetup.add(btnPrdHierDone, null);
    pnlPrdHierSetup.add(btnPrdHierAdd, null);
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
    jPanel3.add(txtDesc, null);
    jPanel3.add(jLabel4, null);
    jSplitPane1.add(jPanel2, JSplitPane.LEFT);
    jPanel2.add(jScrollTreeView, BorderLayout.CENTER);
    jScrollTreeView.getViewport().add(tree, null);
    jSplitPane1.add(jPanel3, JSplitPane.RIGHT);

    jPanel4.add(lblAddress, BorderLayout.CENTER);
    jSplitPane1.setDividerLocation(300);

    applyPermission();
    setEnableAddButton(true);//bc show end of hier type
    setEnableDelButton(true);
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

//    assignPrdHierType_Vector();
    tree.initHierType(DAL,"BTM_IM_PROD_HIR_TYPE");
    vListPrdHierType = tree.vHierTypeAll;
    typeId_EOH = getMaxChildId();
    showTree();
  }

  private void showTree() {
    tree.showHierTypeTree(vListPrdHierType, rootTree);
    tree.updateUI();
    tree.setSelectionPathByNameNode(rootTree,typeId_EOH);//Constant.rootId-> before
  }
//------------------------------

  public boolean checkHierTypeSetup() {
    return DAL.getHierTypeSetup();
  }
//------assign value from database to vector
  /** assignPrdHierTypeData method - insert data into vector
   * @author 		Tuan.Truong
   * @param 		no
   * @return		vListPrdHierType vector
   */
  private void assignPrdHierType_Vector() {
     ResultSet rs = null;
     prdHierType = null;
     vListPrdHierType.removeAllElements();
     String sSQL = "select TYPE_ID, HIGHER_TYPE_ID, NAME, DESCRIPTION from BTM_IM_PROD_HIR_TYPE order by TYPE_ID asc";
//     System.out.println(sSQL);
     rs = DAL.executeQueries(sSQL);
     try {
       int i=0;
       while (rs.next()) {
          i +=1;
          prdHierType=new ObjHierType(rs.getString("TYPE_ID"),rs.getString("HIGHER_TYPE_ID"),rs.getString("NAME"),rs.getString("DESCRIPTION"));
          vListPrdHierType.add(prdHierType);
       }
     rs.getStatement().close();
     }catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
     }
   }

//----------------------------------------------------------

//---------------check Enable for all objects
  /** checkValue method - if value <> -1 then add and delete button will enable = true, else = false
   * @author 		Tuan.Truong
   * @param 		no
   * @return		true/false
   */
  private boolean checkEnableAdd(boolean leaf){
    if (leaf) {
      return true;
    }else {
      return false;
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
    if (vListPrdHierType.size() <0) return Constant.root;
    while (i < vListPrdHierType.size()) {
      prdHierType = (ObjHierType) vListPrdHierType.get(i);
      id=prdHierType.getHigherTypeID().toString().trim();
//      System.out.println("ID : " + id);
      if (id.equalsIgnoreCase(parentId)) {
        name=prdHierType.getTypeName().toString().trim();
//        System.out.println("Name : " + name);
        return name;
      }
      i += 1;
    }
    if (name == null) name="X";
 return name;
 }//end of method

 //Get Min TypeId from Hier Type
 private String getMaxChildId() {
   int i = 0;
   int k1= 0;
   int kMax= 0;
   String id1 = null;
   String idMax=null;
   if (vListPrdHierType.size() <= 0 ) return Constant.rootId;
   prdHierType = (ObjHierType) vListPrdHierType.get(0);
   idMax = prdHierType.getTypeID().toString().trim();
   kMax = Integer.valueOf(idMax).intValue();
   while (i < vListPrdHierType.size()) {
     prdHierType = (ObjHierType) vListPrdHierType.get(i);
     id1 = prdHierType.getTypeID().toString().trim();
     k1 = Integer.valueOf(id1).intValue();
     if (kMax<k1) kMax = k1;
     i += 1;
   }
   idMax = ut.getYeroCode(kMax, ut.PREFIX_CODE_LENGTH);
   return idMax;
 }//end of method

 /** addPrdHierSetup method - insert data into table
  * @author 		Tuan.Truong
  * @param 		no
  * @return		no
  */
 void addPrdHierSetup(String typeId, String parentId, String childName, String desc) {
   String sysDay = ut.getSystemDate();
   String sSQL = "insert into BTM_IM_PROD_HIR_TYPE (TYPE_ID, HIGHER_TYPE_ID, NAME, DESCRIPTION) values ('"
                     + typeId + "','" + parentId + "','"
                     + childName + "','" + desc + "')";
//   System.out.println(sSQL);
   DAL.executeUpdate(sSQL);
 }//end of method

   /** getNewParentId method - get new Parent Id
   * @author 		Tuan.Truong
   * @param 		None
   * @return		parentID
   */
   private String getNewParentId(String childId) {
     if (childId==null) return Constant.rootId;
//     if (childId.equalsIgnoreCase(Constant.root)) return Constant.rootId;
//     if (childId.equalsIgnoreCase(Constant.rootId)) return Constant.startId;
     return childId;
   }//end method

    /** getNewTypeId method - get new Type Id
    * @author 		Tuan.Truong
    * @param 		None
    * @return		typeID
    */
    private String getNewChildId() {
      String childId=null;
      if (vListPrdHierType.size() <= 0 ) return Constant.startId;
      else {
        String id = getMaxChildId();
        int k = Integer.valueOf(id).intValue()+1;
        childId = ut.getYeroCode(k, ut.PREFIX_CODE_LENGTH);
      }
      return childId;
    }//end method get typeId

     private String getNewParentName(String typeId, String parentName) {
       if (typeId==null) return null;
       if (typeId.equalsIgnoreCase(Constant.rootId)) return Constant.root;
       return parentName;
     }//end method get typeId

   /** btnPrdHierCancel method
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   void btnPrdHierCancel_actionPerformed(ActionEvent e) {
     frmMain.showScreen(Constant.SCRS_MAIN_SIM);
//     frmMain.setTitle("JSIM - Hierarchy Setup");

   }


   /** tree click - when click on treeview
    * @author 		Tuan.Truong
    * @param 		String name
    * @return		select Name node
    */
  void tree_valueChanged(TreeSelectionEvent e) {
       boolean check;
       if (e.getPath() != null){
         if (vListPrdHierType.size() == 0) {
           currParentId = Constant.rootId;
           currChildId = Constant.rootId;
           currNodeName = Constant.root;
//           currParentName = Constant.rootId;
           currLeaf = true;
           currEOH = true;
         }else {
           if (tree.getSelectionPath() != null){
             currParentId = tree.getParentId();
             currChildId = tree.getChildId();
             currNodeName = tree.getNodeName();
             currLeaf = tree.isLeaf();
             currEOH = tree.isEndOfHierType(typeId_EOH);
           }
         }
//         System.out.println("1. Parent Id  : " + currParentId);
//         System.out.println("2. Child Id   : " + currChildId);
//         System.out.println("10. Leaf        : " + currLeaf);
//         System.out.println("11. end of Hier : " + currEOH);

         this.lblAddress.setText(" " + tree.getStringPath(e));
         this.txtParentName.setText(currNodeName);
         check = checkEnableAdd(currLeaf);
         setEnableAddButton(check);
         setEnableText(check);
         if (currLeaf && !currNodeName.equalsIgnoreCase(Constant.root)) { setEnableDelButton(true);
         } else setEnableDelButton(false);
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
    if (checkHier(childId)) {
        ut.showMessage(frmMain,lang.getString("TT019"),lang.getString("MS1142_DeleteNodeBecauseContainingProductHier"));
        return;
    }
    int i = ut.showMessage(frmMain,lang.getString("TT018"),lang.getString("MS1131_SureDelete") + currNodeName + "' ?",DialogMessage.QUESTION_MESSAGE,DialogMessage.YES_NO_OPTION);
    if (i == DialogMessage.YES_VALUE){
          deletePrdHier(childId);
          showData();
          tree.setSelectionPathByNameNode(rootTree,parentId);
    }
//    System.out.println(i);
  }//end delete


  /** deletePrdHier Method - Delete node from database
   * @author 		Tuan.Truong
   * @param 		String name
   * @return		no
   */
  void deletePrdHier(String childId) {
    String sSQL = "delete from BTM_IM_PROD_HIR_TYPE where TYPE_ID = '" + childId +"'";
//    System.out.println(sSQL);
    DAL.executeQueries(sSQL);
  }

  private boolean checkHier(String childId) {
    boolean chk=false;
    ResultSet rs = null;
    String sSQL = "select CHILD_ID from BTM_IM_PROD_HIR where TYPE_ID='" + childId + "' and RECD_CURR_FLAG !=0";
//    System.out.println(sSQL);
    rs = DAL.executeQueries(sSQL);
    try {
         if (rs.next()) {
           chk=true;
         }else {
           chk=false;
         }
         rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return chk;
  }

    void txtName_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e,txtName,prdHierType.LEN_TYPE_NAME);
    }

    void txtDesc_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e,txtDesc,prdHierType.LEN_TYPE_DESC);
    }

  void tree_propertyChange(PropertyChangeEvent e) {

  }

  void btnPrdHierDone_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);

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

  // F3
  key = new Integer(KeyEvent.VK_F3);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), key);
  actionMap.put(key, new KeyAction(key));

  // F4
  key = new Integer(KeyEvent.VK_F4);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), key);
  actionMap.put(key, new KeyAction(key));

  // F5
  key = new Integer(KeyEvent.VK_F5);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
  actionMap.put(key, new KeyAction(key));

  // F5
  key = new Integer(KeyEvent.VK_F5);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
  actionMap.put(key, new KeyAction(key));
  // F6
  key = new Integer(KeyEvent.VK_F6);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), key);
  actionMap.put(key, new KeyAction(key));
  // F7
  key = new Integer(KeyEvent.VK_F7);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), key);
  actionMap.put(key, new KeyAction(key));
  // F8
  key = new Integer(KeyEvent.VK_F8);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), key);
  actionMap.put(key, new KeyAction(key));
  // F9
  key = new Integer(KeyEvent.VK_F9);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), key);
  actionMap.put(key, new KeyAction(key));
  // F10
  key = new Integer(KeyEvent.VK_F10);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), key);
  actionMap.put(key, new KeyAction(key));
  // F11
  key = new Integer(KeyEvent.VK_F11);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), key);
  actionMap.put(key, new KeyAction(key));
  // F12
  key = new Integer(KeyEvent.VK_F12);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
  actionMap.put(key, new KeyAction(key));

  // ENTER
   key = new Integer(KeyEvent.VK_ENTER);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
   actionMap.put(key, new KeyAction(key));

  // Escape
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
       if (tree.getChildId() == null) {
         ut.showMessage(frmMain, lang.getString("TT019"), lang.getString("MS1132_ClickNodeTreeview"));
         return;
       }
       String name = this.txtName.getText().trim();
       String desc = this.txtDesc.getText().trim();
       //if Name fiel and desc have text something like Men's then will return Men''s
       name = ut.putCommaToString(name);
       desc = ut.putCommaToString(desc);
       if (checkName(name)) {
         String newParrentId = getNewParentId(currChildId);
         String newChildId = getNewChildId();
         addPrdHierSetup(newChildId, newParrentId, name, desc);
         this.txtName.setText(null);
         this.txtDesc.setText(null);
         assignPrdHierType_Vector();
         showTree();
         typeId_EOH = getMaxChildId();
         tree.setSelectionPathByNameNode(rootTree,newChildId);
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


  void tree_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnPrdHierAdd.doClick();
               }
  }
}//End of class



class PanelPrdHierTypeSetup_btnPrdHierCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierTypeSetup adaptee;

  PanelPrdHierTypeSetup_btnPrdHierCancel_actionAdapter(PanelPrdHierTypeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierCancel_actionPerformed(e);
  }
}

class PanelPrdHierTypeSetup_treProDetail_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  PanelPrdHierTypeSetup adaptee;

  PanelPrdHierTypeSetup_treProDetail_mouseMotionAdapter(PanelPrdHierTypeSetup adaptee) {
    this.adaptee = adaptee;
  }
}

class PanelPrdHierTypeSetup_tree_treeSelectionAdapter implements javax.swing.event.TreeSelectionListener {
  PanelPrdHierTypeSetup adaptee;

  PanelPrdHierTypeSetup_tree_treeSelectionAdapter(PanelPrdHierTypeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(TreeSelectionEvent e) {
    adaptee.tree_valueChanged(e);
  }
}

class PanelPrdHierTypeSetup_btnPrdHierDel_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierTypeSetup adaptee;

  PanelPrdHierTypeSetup_btnPrdHierDel_actionAdapter(PanelPrdHierTypeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierDel_actionPerformed(e);
  }
}

class PanelPrdHierTypeSetup_txtName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelPrdHierTypeSetup adaptee;

    PanelPrdHierTypeSetup_txtName_keyAdapter(PanelPrdHierTypeSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtName_keyTyped(e);
    }
}

class PanelPrdHierTypeSetup_tree_propertyChangeAdapter implements java.beans.PropertyChangeListener {
  PanelPrdHierTypeSetup adaptee;

  PanelPrdHierTypeSetup_tree_propertyChangeAdapter(PanelPrdHierTypeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void propertyChange(PropertyChangeEvent e) {
    adaptee.tree_propertyChange(e);
  }
}

class PanelPrdHierTypeSetup_btnPrdHierDone_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierTypeSetup adaptee;

  PanelPrdHierTypeSetup_btnPrdHierDone_actionAdapter(PanelPrdHierTypeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierDone_actionPerformed(e);
  }
}

class PanelPrdHierTypeSetup_btnPrdHierAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierTypeSetup adaptee;

  PanelPrdHierTypeSetup_btnPrdHierAdd_actionAdapter(PanelPrdHierTypeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierAdd_actionPerformed(e);
  }
}

class PanelPrdHierTypeSetup_tree_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPrdHierTypeSetup adaptee;

  PanelPrdHierTypeSetup_tree_keyAdapter(PanelPrdHierTypeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tree_keyPressed(e);
  }
}
