package sim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import common.*;
import common.ExceptionHandle;
import common.Utils;

import btm.attr.*;
import btm.swing.*;
import java.util.*;
import javax.swing.event.*;
import btm.swing.*;
import javax.swing.tree.*;
import java.sql.Statement;

/**
 * <p>Title: Admin -> Role Access </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author
 * @version 1.0
 */

public class PanelAdminRoleAccess extends JPanel {
  DataAccessLayer DAL = new DataAccessLayer();;

  Utils ut = new Utils();
  Vector vAccessName = new Vector();
  EmpRight er = new EmpRight();//FrameLogIn.empRight;
  Statement stmt = null;
  //define for panel
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;
  JPanel pnlMain = new JPanel();

  TreePath selectedPathOld;

  BJButton btnAdminRoleAccessCancel = new BJButton();
  BJButton btnDone = new BJButton();

  DefaultTableModel dataGridRole = new DefaultTableModel();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  //create panel
  BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel pnlCenter = new JPanel();
  JPanel pnlStatus = new JPanel();

  //grid layout
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout2 = new GridLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel pnlText = new JPanel();
  JPanel pnlTable = new JPanel();
  BJPanel pnlButtonContent = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel3 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel4 = new BJPanel(Constant.PANEL_TYPE_HEADER);
//  JTable jTableData;
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  JOptionPane jOpt = new JOptionPane();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BJListBox jListRole = new BJListBox();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JCheckBox checkAdd = new JCheckBox();
  JCheckBox checkDelete = new JCheckBox();
  JCheckBox checkModify = new JCheckBox();
  JCheckBox checkSearch = new JCheckBox();
  JCheckBox checkReport = new JCheckBox();
  BorderLayout borderLayout5 = new BorderLayout();
  //tree
  BJScreenTree tree;
  BJScreenNode rootTree ;
  BJScreenModel pTreeModel;
  RoleAccessRight oRoleAccRight=new RoleAccessRight();
  Vector vAccRightAll = new Vector();
  Vector vRoleAccRightAll = new Vector();
  Vector vRoleID = new Vector();
  Vector  vSql = new Vector();
  String oldChooseChildId = null;
  String currParentId = null;
  String currChildId = null;
  String currNodeName = null;
  String empId = DAL.getEmpID();
  String oldRoleId = null;
  String scrID = "";
   boolean firstTime = true;
//  String accessRight = "";
  boolean currLeaf = false;
  String oldInAccRight="00000";
  JScrollPane scrollTree = new JScrollPane();
  JScrollPane jscrListRole = new JScrollPane();
  BorderLayout borderLayout6 = new BorderLayout();
  BorderLayout borderLayout7 = new BorderLayout();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international


  /** Create Panel for Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		FrameMainSim frm, CardLayout crdLayout, JPanel parent
   * @return		no
   */
  public PanelAdminRoleAccess(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      this.cardLayout = crdLayout;
      this.parent = parent;
      jbInit();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }//end method


  /** Create jbInit method - init value for frame
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void jbInit() throws Exception {
    //DAL.getConnfigParameter();

    jListRole.setBorder(BorderFactory.createLoweredBevelBorder());
    jListRole.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    this.setRequestFocusEnabled(true);
    this.setSize(800,600);
    this.addMouseListener(new PanelAdminRoleAccess_this_mouseAdapter(this));
   //tree
   rootTree =new BJScreenNode( new BJScreenNodeDetail(Constant.rootId,Constant.rootId,"Home"));
   pTreeModel = new BJScreenModel(rootTree);
   tree = new BJScreenTree(pTreeModel);

    //event for table
    pnlMain.setBackground(new Color(55, 194, 255));
    pnlMain.setLayout(borderLayout2);
//    jButton1.addActionListener(new FrameDetailTrans_jButton1_actionAdapter(this));
    pnlCenter.setMinimumSize(new Dimension(10, 10));
    pnlCenter.setPreferredSize(new Dimension(660, 60));
    pnlCenter.setLayout(flowLayout1);

    pnlText.setDebugGraphicsOptions(0);
    pnlText.setMaximumSize(new Dimension(2147483647, 2147483647));
    pnlText.setMinimumSize(new Dimension(10,10));
    pnlText.setPreferredSize(new Dimension(660, 25));
    pnlText.setRequestFocusEnabled(true);
    pnlText.setLayout(null);

    pnlTable.setDebugGraphicsOptions(0);
    pnlTable.setMinimumSize(new Dimension(10,10));
    pnlTable.setPreferredSize(new Dimension(800, 467));
    pnlTable.setLayout(borderLayout4);

//    jTableData.addMouseListener(new FrameDetailTrans_jTableData_mouseAdapter(this));

    //for status
    pnlStatus.setLayout(gridLayout1);
    pnlHeader.setLayout(borderLayout3);

    pnlButtonContent.setLayout(new BoxLayout(pnlButtonContent, BoxLayout.PAGE_AXIS));

    jPanel1.setPreferredSize(new Dimension(20, 10));
    jPanel3.setPreferredSize(new Dimension(50, 10));
    pnlHeader.setPreferredSize(new Dimension(102, 45));

    //for button of header
//    btnDone.setFont(new java.awt.Font("Dialog", 1, 12));
//    btnDone.setPreferredSize(new Dimension(63, 35));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText(lang.getString("Done")+" (F1)");
    btnDone.addActionListener(new PanelAdminRoleAccess_btnDone_actionAdapter(this));
//    btnAdminRoleAccessCancel.setFont(new java.awt.Font("Dialog", 1, 12));
//    btnAdminRoleAccessCancel.setPreferredSize(new Dimension(73, 35));
    btnAdminRoleAccessCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleAccessCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnAdminRoleAccessCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");

    btnAdminRoleAccessCancel.addActionListener(new PanelAdminRoleAccess_btnAdminRoleAccessCancel_actionAdapter(this));

    jPanel4.setLayout(flowLayout2);
    gridLayout1.setColumns(0);
    gridLayout1.setRows(1);
//    jTableData.setBorder(null);
//    jTableData.setRequestFocusEnabled(true);

    jPanel2.setLayout(borderLayout5);
    jPanel2.setBorder(BorderFactory.createEtchedBorder());
    jPanel2.setMaximumSize(new Dimension(29, 29));
    jPanel2.setMinimumSize(new Dimension(29, 29));
    jPanel2.setPreferredSize(new Dimension(100, 10));
    jPanel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel6.setBorder(BorderFactory.createEtchedBorder());
    jPanel6.setPreferredSize(new Dimension(210, 10));
    jPanel6.setLayout(null);
    jPanel5.setBorder(BorderFactory.createEtchedBorder());
    jPanel5.setDebugGraphicsOptions(0);
    jPanel5.setPreferredSize(new Dimension(285, 10));
    jPanel5.setLayout(borderLayout7);
//    jListAccessLevel.add(checkNewRoleID(),"Add");


    jListRole.setBounds(new Rectangle(4, 2, 277, 494));
    jListRole.addListSelectionListener(new PanelAdminRoleAccess_jListRole_listSelectionAdapter(this));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setText(lang.getString("Role"));
    jLabel1.setBounds(new Rectangle(10, 10, 109, 19));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setText(lang.getString("Screen"));
    jLabel2.setBounds(new Rectangle(290, 10, 214, 19));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setText(lang.getString("AccessRight"));
    jLabel3.setBounds(new Rectangle(548, 7, 251, 15));
    checkAdd.setEnabled(false);
    checkAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    checkAdd.setDoubleBuffered(false);
    checkAdd.setText(lang.getString("Add"));
    checkAdd.setBounds(new Rectangle(44, 30, 95, 25));
    checkDelete.setEnabled(false);
    checkDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    checkDelete.setText(lang.getString("Delete"));
    checkDelete.setBounds(new Rectangle(44, 70, 104, 25));
    checkModify.setEnabled(false);
    checkModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    checkModify.setText(lang.getString("Modify"));
    checkModify.setBounds(new Rectangle(44, 109, 97, 27));
    checkSearch.setEnabled(false);
    checkSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    checkSearch.setText(lang.getString("Search"));
    checkSearch.setBounds(new Rectangle(44, 151, 106, 22));
    checkReport.setEnabled(false);
    checkReport.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    checkReport.setText(lang.getString("Report"));
    checkReport.setBounds(new Rectangle(44, 187, 95, 32));
    tree.addTreeSelectionListener(new PanelAdminRoleAccess_tree_treeSelectionAdapter(this));
    scrollTree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollTree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollTree.setPreferredSize(new Dimension(54, 100));
    scrollTree.setToolTipText("");
    scrollTree.setVerifyInputWhenFocusTarget(true);
    tree.addKeyListener(new PanelAdminRoleAccess_tree_keyAdapter(this));
    pnlStatus.setPreferredSize(new Dimension(0, 0));
    jscrListRole.setBounds(new Rectangle(0, 0, 4, 4));
    this.add(pnlMain, BorderLayout.CENTER);
    pnlMain.add(pnlCenter, BorderLayout.CENTER);

    pnlCenter.add(pnlText,null);
    pnlText.add(jLabel1, null);
    pnlText.add(jLabel2, null);
    pnlText.add(jLabel3, null);

    //add all panel
    pnlMain.add(pnlStatus, BorderLayout.SOUTH);
    pnlMain.add(pnlHeader, BorderLayout.NORTH);
    pnlHeader.add(jPanel1, BorderLayout.WEST);
    pnlHeader.add(pnlButtonContent,  BorderLayout.CENTER);
    pnlButtonContent.add(jPanel4, null);
    pnlHeader.add(jPanel3,  BorderLayout.EAST);
    //add button into panl header
    jPanel4.add(btnDone, null);
    jPanel4.add(btnAdminRoleAccessCancel, null);

    pnlCenter.add(pnlTable, null);
    pnlTable.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(scrollTree,  BorderLayout.CENTER);

    pnlTable.add(jPanel5, BorderLayout.WEST);
    //jPanel5.add(jListRole, BorderLayout.NORTH);
    jPanel5.add(jscrListRole, BorderLayout.CENTER);
    jscrListRole.getViewport().add(jListRole, null);
    pnlTable.add(jPanel6, BorderLayout.EAST);


    jListRole.addKeyListener(new PanelAdminRoleAccess_jListRole_keyAdapter(this));
    jListRole.addMouseListener(new PanelAdminRoleAccess_jListRole_mouseAdapter(this));

    //call method
    jPanel6.add(checkAdd, null);
    jPanel6.add(checkDelete, null);
    jPanel6.add(checkModify, null);
    jPanel6.add(checkSearch, null);
    jPanel6.add(checkReport, null);
    scrollTree.getViewport().add(tree, null);
    resetCheck();
    }
    //apply employee's permission for this screen
    public void applyPermission() {
      EmpRight er = new EmpRight();
      er.initData(DAL, DAL.getEmpID());
      er.setData(DAL.getEmpID(), Constant.SCRS_SUPPLIER_SETUP);
      btnDone.setEnabled(er.getAdd());
    }


//---------------show data and show tree view
  public void showData() {
    vRoleAccRightAll = assignRoleAccRightAll(frmMain.getDAL());
    assignAccRight();
    showTree();
  }

  private void showTree() {

    tree.showScreenTree(vAccRightAll, rootTree);
    tree.updateUI();
   }
  private void assignAccRight() {

    try {
     stmt = DAL.getConnection().createStatement();
   }
   catch (Exception ex) {
     ex.printStackTrace();
   }

    vAccRightAll = tree.initAccRight(frmMain.getDAL());
    try {
        stmt.close();

      }
      catch (Exception ex) {
        ex.printStackTrace();
      }

  }


//---------process for tree
  private Vector assignRoleAccRightAll(DataAccessLayer DAL) {
    Vector vResultAccRight = new Vector();
    ResultSet rs = null;
    oRoleAccRight = null;
    Statement stmt = null;
    vResultAccRight.removeAllElements();
    String sSQL = "select ROLE_ID, SCR_ID, ACCESS_RIGHT from BTM_IM_ROLE_ACCESS_RIGHT order by ROLE_ID asc";
    try{
//      System.out.println(sSQL);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sSQL,stmt);
      if (!rs.isBeforeFirst() && !rs.isAfterLast()) return vResultAccRight;
      while (rs.next()) {
        oRoleAccRight=new RoleAccessRight(rs.getString("ROLE_ID"), rs.getString("SCR_ID"), rs.getString("ACCESS_RIGHT"));
        vResultAccRight.add(oRoleAccRight);
      }
      rs.close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    try{
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return vResultAccRight;
  }


  //get vector with condition : parentId
      public String getAccRightIn(Vector vRoleAccRight, String roleId, String accId) {
        if (roleId == null) return EmpRight.FULL_ACC_RIGHT;
        int i=0;
        String roleIdTmp=null;
        String accIdTmp = null;
        String accRight = EmpRight.NO_ACC_RIGHT;
        while (i < vRoleAccRight.size()) {
          oRoleAccRight = (RoleAccessRight) vRoleAccRight.get(i);
          roleIdTmp = oRoleAccRight.getRoleId().toString().trim();
          accIdTmp = oRoleAccRight.getAccId().toString().trim();
//          System.out.println(roleIdTmp + " : " + accIdTmp + " :" + accRight);
          if (roleIdTmp.equals(roleId) && accIdTmp.equals(accId)) {
            accRight = oRoleAccRight.getAccRight().toString().trim();
            return accRight;
          }
          i += 1;
        }//while
        if (roleId.equals("001") || roleId.equals("002")) return EmpRight.FULL_ACC_RIGHT;
        return accRight;
      }


  private void showInAccRight(String accRight) {
    int add = Integer.parseInt(accRight.substring(0,1));
    int delete = Integer.parseInt(accRight.substring(1,2));
    int modify = Integer.parseInt(accRight.substring(2,3));
    int search = Integer.parseInt(accRight.substring(3,4));
    int report = Integer.parseInt(accRight.substring(4,5));

    if (add == 1) this.checkAdd.setSelected(true);
    else this.checkAdd.setSelected(false);

    if (delete == 1) this.checkDelete.setSelected(true);
    else this.checkDelete.setSelected(false);

    if (modify == 1) this.checkModify.setSelected(true);
    else this.checkModify.setSelected(false);

    if (search == 1) this.checkSearch.setSelected(true);
    else this.checkSearch.setSelected(false);

    if (report == 1) this.checkReport.setSelected(true);
    else this.checkReport.setSelected(false);
  }
//--------------
  /** Cancel Button of Role Access Cancel
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnAdminRoleAccessCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
  }

  /** Get data on List
  * @author 		Loan.vo
  * @param 		no
  * @return		ResultSet
  */
  private ResultSet getDataListRole() {
    ResultSet rs = null;
    String sSQL = null;
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
      sSQL = "select ROLE_ID, ROLE_NAME from BTM_IM_ROLE where ROLE_ID <> 001 and ROLE_ID <> 002 order by ROLE_ID asc";
//      System.out.println(sSQL);
      rs = DAL.executeScrollQueries(sSQL,stmt);
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;

  }//end method



   /** Bin data on List
  * @author 		Tuan.Truong
  * @param 		no
  * @return		no
  */
 void binDataListRole() {

   jListRole.setData(getDataListRole());
   jListRole.setSelectedIndex(0);
   oldRoleId = this.jListRole.getSelectedData();
   try {
     stmt.close();
   }
   catch (Exception ex) {
     ex.printStackTrace();
   }

 } //end of get Data

//   void addRole(String roleID, String roleDesc, String whichApp) {
//     System.out.println("insert into BTM_IM_ROLE (ROLE_ID, ROLE_NAME, WHICH_APP) values ('" + roleID +"','" + roleDesc + "','?')");
//     DAL.executeUpdate("insert into BTM_IM_ROLE (ROLE_ID, ROLE_NAME, WHICH_APP) values ('" + roleID +"','" + roleDesc + "','?')");//?=whichApp
//   }

    void jButtonNext_actionPerformed(ActionEvent e) {

   }

//   private void removeItemRoleAccess(String roleID, int accessID) {
//     deleteItemRoleAccess(roleID, accessID);
//   }
//   private void deleteItemRoleAccess(String roleID, int accessID) {
//     ResultSet rs=null;
//     System.out.println("delete BTM_IM_ROLE_ACCESS_RIGHT where ROLE_ID = '" + roleID + "' and ACCESS_ID = " + accessID);
//     rs = DAL.executeQueries("delete BTM_IM_ROLE_ACCESS_RIGHT where ROLE_ID = '" + roleID + "' and ACCESS_ID = " + accessID);
//     try {
//     }catch (Exception e) {
//       ExceptionHandle eh = new ExceptionHandle();
//       eh.ouputError(e);
//     }
//   }//end of method

   void jListRole_keyPressed(KeyEvent e) {
     if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnAdminRoleAccessCancel.doClick();
               }
     if (e.getKeyCode() == KeyEvent.VK_ENTER){

     }//end if
   }//end method


  void jButtonBack_actionPerformed(ActionEvent e) {
  }

  void btnDone_actionPerformed(ActionEvent e) {
    String roleId = this.jListRole.getSelectedData();
    boolean add = this.checkAdd.isSelected();
    boolean delete = this.checkDelete.isSelected();
    boolean modify = this.checkModify.isSelected();
    boolean search = this.checkSearch.isSelected();
    boolean report = this.checkReport.isSelected();

    String outAccRight = convertAccRight(add, delete, modify, search, report);
    boolean checkRoleScreen = checkExistRoleSreen(roleId,oldChooseChildId);
    if (oldChooseChildId.equals("-1")){
      frmMain.showScreen(Constant.SCRS_MAIN_SIM);
      return;
    }
    if (checkRoleScreen) { //!null -> update RoleAccRight
      updateData(roleId, oldChooseChildId, outAccRight);
    }
    else { //else insert new
      insertData(roleId, oldChooseChildId, outAccRight);
    }
    vRoleAccRightAll = assignRoleAccRightAll(frmMain.getDAL());
    oldInAccRight = outAccRight;
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
 }///end method

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
        btnDone.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnAdminRoleAccessCancel.doClick();
      }
    }
  }

   /** Get Inset info into Role Access RIght method
* @author 		Loan.vo
* @param 		no
* @return		no
*/
   private void insertData(String roleID, String scrID,String accessRight) {
     String  sql = "Insert into BTM_IM_ROLE_ACCESS_RIGHT (ROLE_ID,SCR_ID,ACCESS_RIGHT) VALUES ('" + roleID + "','" + scrID + "','" + accessRight + "')";
     try {
       stmt = DAL.getConnection().createStatement();
//       System.out.println(sql);
       DAL.executeUpdate(sql);
    }
    catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(ex);
    }
    try {
       stmt.close();
     }
     catch (Exception ex) {
       ex.printStackTrace();
     }

   }

   private void updateData(String roleID, String scrID, String accessRight) {
     String sql =
         "update BTM_IM_ROLE_ACCESS_RIGHT set ACCESS_RIGHT = '" + accessRight + "' where ROLE_ID='" +
         roleID + "' and SCR_ID='" + scrID + "'";
     try {
       stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//       System.out.println(sql);
       DAL.executeUpdate(sql);
     }
     catch (Exception ex) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(ex);
     }
     try {
       stmt.close();
     }
     catch (Exception ex) {
       ex.printStackTrace();
    }

   }

   /** tree_valueChanged
  * @author 		Loan.vo
  * @param 		no
  * @return		no
  */

  void tree_valueChanged(TreeSelectionEvent e) {
    if (this.jListRole.getSelectedIndex() < 0){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1194_MustChooseRole"),DialogMessage.WARNING_MESSAGE,DialogMessage.OK_OPTION);
      return;
    }
    if (e.getPath() != null) {
      if (vAccRightAll.size() == 0) { //view
        currParentId = Constant.rootId;
        currChildId = Constant.rootId;
        currNodeName = tree.getNodeName();
        currLeaf = false;
      }
      else {
        if (tree.getSelectionPath() != null) {
          TreePath tpath = e.getPath();
          Object[] path = new Object[tpath.getPathCount()];
          currParentId = tree.getParentId();
          currChildId = tree.getChildId();
          currNodeName = tree.getNodeName();
          currLeaf = tree.isLeaf();
        }
      }

      String roleId = this.jListRole.getSelectedData();
      boolean add = this.checkAdd.isSelected();
      boolean delete = this.checkDelete.isSelected();
      boolean modify = this.checkModify.isSelected();
      boolean search = this.checkSearch.isSelected();
      boolean report = this.checkReport.isSelected();
      if (currLeaf) {
        checkAdd.setEnabled(true);
        checkDelete.setEnabled(true);
        checkModify.setEnabled(true);
        checkDelete.setEnabled(true);
        checkSearch.setEnabled(true);
        checkReport.setEnabled(true);
      }
        else {
        oldInAccRight = EmpRight.NO_ACC_RIGHT;
        resetCheck();
      }

        String outAccRight = convertAccRight(add, delete, modify, search, report);
      if (firstTime == false) {
        if (oldInAccRight.equals(outAccRight)) {
//          System.out.println("==");
        }
        else {
//          System.out.println("<>");
             int result = ut.showMessage(frmMain, lang.getString("TT001"),
                                         lang.getString("MS1066_DoYouWantSave"),
                                         DialogMessage.WARNING_MESSAGE,
                                         DialogMessage.YES_NO_OPTION);
             if (result == DialogMessage.YES_VALUE) {
               boolean checkRoleScreen = checkExistRoleSreen(roleId,oldChooseChildId);
                 if (checkRoleScreen) { //!null -> update RoleAccRight
                   updateData(roleId, oldChooseChildId, outAccRight);
                 }
                 else { //else insert new
                   insertData(roleId, oldChooseChildId, outAccRight);
                 }
                vRoleAccRightAll = assignRoleAccRightAll(frmMain.getDAL());
             }
           }
      }
      else firstTime = false;
           String inAccRight = getAccRightIn(vRoleAccRightAll, roleId, currChildId);
           if (currLeaf) {
             showInAccRight(inAccRight); //showInAccRight(empId, roleId, currChildId);
           }
           else {
             resetCheck();
           }
           if (inAccRight == null)
             oldInAccRight = EmpRight.NO_ACC_RIGHT;
        else
          oldInAccRight = inAccRight;
//        System.out.println("current AccRight : " + inAccRight +
//                           " - OldAccRight : " + outAccRight);
//        System.out.println("currChildId : " + currChildId +
//                           " - Old choose Child : " + oldChooseChildId);

        oldChooseChildId = currChildId;
    }
    jListRole.setSelectedIndex(-1);

  }

//  private boolean checkExistInDatabase(String roleID, String scrID,
//                                       String accessRight) {
//    ResultSet rs = null;
//    String sqlStr = "Select * From BTM_IM_ROLE_ACCESS_RIGHT Where Role_ID='" + roleID + "' and Scr_ID='" + scrID + "' and Access_Right='" + accessRight +"'";
//    try{
//      rs = DAL.executeQueries(sqlStr);
//      if(rs.next())
//        return true;
//    }
//    catch(Exception ex ){}
//    return false; //not exist
//  }
//reset check
  public void resetCheck(){
      checkAdd.setSelected(false);
      checkDelete.setSelected(false);
      checkModify.setSelected(false);
      checkSearch.setSelected(false);
      checkReport.setSelected(false);

      checkAdd.setEnabled(false);
      checkDelete.setEnabled(false);
      checkModify.setEnabled(false);
      checkDelete.setEnabled(false);
      checkSearch.setEnabled(false);
      checkReport.setEnabled(false);
      firstTime = true;
  }

  private boolean checkExistRoleSreen(String roleID, String scrID) {
    ResultSet rs = null;
    String sqlStr = "Select * From BTM_IM_ROLE_ACCESS_RIGHT Where Role_ID='" +
        roleID + "' and Scr_ID='" + scrID + "'";
    try {
      stmt = DAL.getConnection().createStatement();
//      System.out.println(sqlStr);
      rs = DAL.executeQueries(sqlStr,stmt);
      if (rs.next()) {
        stmt.close();
        return true;
      }
    }
    catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);}
    try {
       stmt.close();
     }
     catch (Exception ex) {
       ex.printStackTrace();
     }
    return false; //not exist
  }

  private String convertAccRight(boolean add, boolean delete, boolean modify, boolean search, boolean report) {
    String result = "";
    if (add) result += "1";
    else result += "0";
    if (delete) result += "1";
    else result += "0";
    if (modify) result += "1";
    else result += "0";
    if (search) result += "1";
    else result += "0";
    if (report) result += "1";
    else result += "0";
    return result;
  }
  /** jListRole_valueChanged
* @author 		Loan.vo
* @param 		no
* @return		no
*/

  void jListRole_valueChanged(ListSelectionEvent e) {
    }

  void this_mouseClicked(MouseEvent e) {

  }
  /** jListRole_mouseclick
 * @author 		Loan.vo
 * @param 		no
 * @return		no
 */

  void jListRole_mouseClicked(MouseEvent e) {

    String roleId = this.jListRole.getSelectedData();
//   System.out.println("Role ID :" + roleId);

    boolean add = this.checkAdd.isSelected();
    boolean delete = this.checkDelete.isSelected();
    boolean modify = this.checkModify.isSelected();
    boolean search = this.checkSearch.isSelected();
    boolean report = this.checkReport.isSelected();
    String outAccRight = convertAccRight(add, delete, modify, search, report);
      if (oldInAccRight.equals(outAccRight)) {
//          System.out.println("==");
        }
        else {
//          System.out.println("<>");
           if (firstTime == false) {
             int result = ut.showMessage(frmMain, lang.getString("TT001"),
                                         lang.getString("MS1066_DoYouWantSave"),
                                         DialogMessage.WARNING_MESSAGE,
                                         DialogMessage.YES_NO_OPTION);
             if (result == DialogMessage.YES_VALUE) {
               boolean checkRoleScreen = checkExistRoleSreen(oldRoleId,
                   oldChooseChildId);
                 if (checkRoleScreen) { //!null -> update RoleAccRight
                   updateData(oldRoleId, oldChooseChildId, outAccRight);
                 }
                 else { //else insert new
                   insertData(oldRoleId, oldChooseChildId, outAccRight);
                 }
                   vRoleAccRightAll = assignRoleAccRightAll(frmMain.getDAL());
             }
           }//if firstTime
           else firstTime = false;
        }

          String inAccRight = getAccRightIn(vRoleAccRightAll, roleId, currChildId);
        if (currLeaf) {
          showInAccRight(inAccRight);//showInAccRight(empId, roleId, currChildId);
        }
        else resetCheck();
        if (inAccRight == null)
           oldInAccRight = EmpRight.NO_ACC_RIGHT;
        else
          oldInAccRight = inAccRight;
        oldChooseChildId = currChildId;
        oldRoleId = roleId;
  }

  void tree_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnAdminRoleAccessCancel.doClick();
               }
  }
}//end class

class PanelAdminRoleAccessAccess_btnAdminRoleAccessCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRoleAccess adaptee;

  PanelAdminRoleAccessAccess_btnAdminRoleAccessCancel_actionAdapter(PanelAdminRoleAccess adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleAccessCancel_actionPerformed(e);
  }
}

class PanelAdminRoleAccess_btnAdminRoleAccessCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRoleAccess adaptee;

  PanelAdminRoleAccess_btnAdminRoleAccessCancel_actionAdapter(PanelAdminRoleAccess adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleAccessCancel_actionPerformed(e);
  }
}

class PanelAdminRoleAccess_jListRole_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminRoleAccess adaptee;

  PanelAdminRoleAccess_jListRole_keyAdapter(PanelAdminRoleAccess adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jListRole_keyPressed(e);
  }
}

class PanelAdminRoleAccess_jListRole_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminRoleAccess adaptee;

  PanelAdminRoleAccess_jListRole_mouseAdapter(PanelAdminRoleAccess adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jListRole_mouseClicked(e);
  }
}

class PanelAdminRoleAccess_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRoleAccess adaptee;

  PanelAdminRoleAccess_btnDone_actionAdapter(PanelAdminRoleAccess adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelAdminRoleAccess_jListRole_listSelectionAdapter implements javax.swing.event.ListSelectionListener {
  PanelAdminRoleAccess adaptee;

  PanelAdminRoleAccess_jListRole_listSelectionAdapter(PanelAdminRoleAccess adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(ListSelectionEvent e) {
    adaptee.jListRole_valueChanged(e);
  }
}

class PanelAdminRoleAccess_tree_treeSelectionAdapter implements javax.swing.event.TreeSelectionListener {
  PanelAdminRoleAccess adaptee;

  PanelAdminRoleAccess_tree_treeSelectionAdapter(PanelAdminRoleAccess adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(TreeSelectionEvent e) {
    adaptee.tree_valueChanged(e);
  }
}

class PanelAdminRoleAccess_this_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminRoleAccess adaptee;

  PanelAdminRoleAccess_this_mouseAdapter(PanelAdminRoleAccess adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.this_mouseClicked(e);
  }
}

class PanelAdminRoleAccess_tree_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminRoleAccess adaptee;

  PanelAdminRoleAccess_tree_keyAdapter(PanelAdminRoleAccess adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tree_keyPressed(e);
  }
}
