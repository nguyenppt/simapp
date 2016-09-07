package sim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import common.*;
import btm.attr.*;
import java.util.*;
import btm.swing.*;
import btm.business.*;
import java.beans.*;
import java.sql.Statement;

/**
 * <p>Title: </p>
 * <p>Description: Admin -> Role </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */

public class PanelAdminRole extends JPanel {
  Utils ut = new Utils();
  Statement stmt = null;
  AdminBusObj adminBusObj = new AdminBusObj();
  int PrefixCodeLength = ut.PREFIX_CODE_LENGTH;
  //define for panel
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;
  String currentRoleID;
  DataAccessLayer DAL;
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

  JPanel pnlMain = new JPanel();
  Vector vSql = new Vector();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  boolean flagHotkey = true;
  BJButton btnPrdHierTypeDone = new BJButton();
  BJButton btnAdminRoleAdd = new BJButton();
  BJButton btnAdminRoleDel = new BJButton();
  BJButton btnAdminRoleSearch = new BJButton();
  BJButton btnAdminRoleCancel = new BJButton();

  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  //create panel
  BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel pnlCenter = new JPanel();
  JPanel pnlStatus = new JPanel();
  JLabel lblRoleDesc = new JLabel();
  JTextField txtRoleDesc = new JTextField();

  //grid layout
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout2 = new GridLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel pnlText = new JPanel();
  JPanel pnlTable = new JPanel();
  BJPanel pnlButtonContent = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel4 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JLabel lblInputItem = new JLabel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  JScrollPane jScrollDataGrid = new JScrollPane();
  JScrollPane jScrollPane1 = new JScrollPane();
  JOptionPane jOpt = new JOptionPane();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JScrollPane jScrollPane2 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true);
  JLabel lblRoleID = new JLabel();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  JTextField txtRoleID = new JTextField();
  BJButton btnAdminRoleClear = new BJButton();
  GridLayout gridLayout3 = new GridLayout();
  BJButton btnAdminRoleModify = new BJButton();
  JPanel jPanel3 = new JPanel();

  /** Create Panel for Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		FrameMainSim frm, CardLayout crdLayout, JPanel parent
   * @return		no
   */
  public PanelAdminRole(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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


  /** Create jbInit method - init value for frame
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    this.setLayout(borderLayout1);
    this.setSize(800,600);

    pnlMain.setBackground(new Color(55, 194, 255));
    pnlMain.setLayout(borderLayout2);
    pnlCenter.setMinimumSize(new Dimension(10, 10));
    pnlCenter.setPreferredSize(new Dimension(660, 60));
    pnlCenter.setLayout(borderLayout4);

    pnlText.setMinimumSize(new Dimension(10,10));
    pnlText.setPreferredSize(new Dimension(660, 100));
    pnlText.setRequestFocusEnabled(true);
    pnlText.setLayout(new BorderLayout());

    pnlTable.setMinimumSize(new Dimension(10,10));
    pnlTable.setPreferredSize(new Dimension(660, 370));
    pnlTable.setLayout(borderLayout5);

    lblRoleDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRoleDesc.setPreferredSize(new Dimension(80, 21));
    lblRoleDesc.setText(lang.getString("RoleName")+":");
    txtRoleDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRoleDesc.setPreferredSize(new Dimension(240, 21));
    txtRoleDesc.setRequestFocusEnabled(true);
    txtRoleDesc.addKeyListener(new PanelAdminRole_txtRoleDesc_keyAdapter(this));
    jPanel6.setMinimumSize(new Dimension(38, 10));
    jPanel6.setPreferredSize(new Dimension(60, 10));
    jPanel6.setLayout(flowLayout3);
    jPanel2.setLayout(flowLayout1);
    btnAdminRoleAdd.addActionListener(new PanelAdminRole_btnAdminRoleAdd_actionAdapter(this));
    flowLayout2.setAlignment(FlowLayout.LEFT);

    btnAdminRoleDel.addActionListener(new PanelAdminRole_btnAdminRoleDel_actionAdapter(this));
    btnPrdHierTypeDone.addActionListener(new PanelAdminRole_btnPrdHierTypeDone_actionAdapter(this));


    //for status
    pnlStatus.setLayout(gridLayout1);
    pnlHeader.setLayout(borderLayout3);

    pnlButtonContent.setLayout(gridLayout3);

    jPanel1.setPreferredSize(new Dimension(20, 10));
    pnlHeader.setPreferredSize(new Dimension(102, 45));

    //for button of header
    btnPrdHierTypeDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrdHierTypeDone.setPreferredSize(new Dimension(120, 37));
    btnPrdHierTypeDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnPrdHierTypeDone.setText("<html><center><b>"+lang.getString("Done")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnAdminRoleAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleAdd.setPreferredSize(new Dimension(120, 37));
    btnAdminRoleAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdminRoleAdd.setText("<html><center><b>"+lang.getString("Add")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdminRoleDel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleDel.setPreferredSize(new Dimension(120, 37));
    btnAdminRoleDel.setToolTipText(lang.getString("Delete")+" (F8)");
    btnAdminRoleDel.setText("<html><center><b>"+lang.getString("Delete")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnAdminRoleSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleSearch.setPreferredSize(new Dimension(120, 37));
    btnAdminRoleSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnAdminRoleSearch.setText("<html><center><b>"+lang.getString("Search")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnAdminRoleSearch.addActionListener(new PanelAdminRole_btnAdminRoleSearch_actionAdapter(this));
    btnAdminRoleCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleCancel.setPreferredSize(new Dimension(120, 37));
    btnAdminRoleCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnAdminRoleCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");

    btnAdminRoleCancel.addActionListener(new PanelAdminRole_btnAdminRoleCancel_actionAdapter(this));

    jPanel4.setLayout(flowLayout2);
    gridLayout1.setColumns(0);
    gridLayout1.setRows(1);

    jPanel2.setPreferredSize(new Dimension(120, 10));
    jPanel5.setPreferredSize(new Dimension(400, 10));
    table.addMouseListener(new PanelAdminRole_table_mouseAdapter(this));
    lblRoleID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRoleID.setPreferredSize(new Dimension(80, 21));
    lblRoleID.setText(lang.getString("RoleID")+":");
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    txtRoleID.setBackground(SystemColor.control);
    txtRoleID.setEnabled(false);
    txtRoleID.setPreferredSize(new Dimension(120, 21));
    txtRoleID.setEditable(true);
    btnAdminRoleClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnAdminRoleClear.addActionListener(new PanelAdminRole_btnAdminRoleClear_actionAdapter(this));
    btnAdminRoleClear.setPreferredSize(new Dimension(120, 37));
    btnAdminRoleClear.setToolTipText(lang.getString("Clear")+" (F7)");
    jPanel4.setMinimumSize(new Dimension(730, 51));
    jPanel4.setPreferredSize(new Dimension(755, 47));
    btnAdminRoleModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdminRoleModify.addActionListener(new PanelAdminRole_btnAdminRoleModify_actionAdapter(this));
    btnAdminRoleModify.setToolTipText(lang.getString("Modify")+" (F2)");
    btnAdminRoleModify.setPreferredSize(new Dimension(120, 37));
    jPanel3.setPreferredSize(new Dimension(10, 5));
    table.addKeyListener(new PanelAdminRole_table_keyAdapter(this));
    jPanel6.add(txtRoleID, null);
    pnlMain.add(pnlCenter, BorderLayout.CENTER);

    pnlCenter.add(pnlText, BorderLayout.NORTH);
    pnlText.add(jPanel2, BorderLayout.WEST);
    jPanel2.add(lblRoleID, null);
    jPanel2.add(lblRoleDesc, null);
    pnlText.add(jPanel5, BorderLayout.EAST);
    pnlText.add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(txtRoleDesc, null);
    pnlText.add(jPanel3, BorderLayout.NORTH);

    //add all panel
    pnlMain.add(pnlStatus, BorderLayout.SOUTH);
    pnlMain.add(pnlHeader, BorderLayout.NORTH);
    pnlHeader.add(jPanel1, BorderLayout.WEST);
    pnlHeader.add(pnlButtonContent,  BorderLayout.CENTER);
    pnlButtonContent.add(jPanel4, null);
    //add button into panl header
    showButton(true);
    pnlCenter.add(pnlTable, BorderLayout.CENTER);
    pnlTable.add(jScrollPane2,  BorderLayout.CENTER);
    jScrollPane2.getViewport().add(table, null);
    this.add(pnlMain, java.awt.BorderLayout.CENTER);

    //define for table
    String[] columnNames = new String[]{lang.getString("RoleID"), lang.getString("RoleName")};
    dm.setDataVector(new Object[][]{},columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    table.getColumn(lang.getString("RoleID")).setPreferredWidth(120);
    table.getColumn(lang.getString("RoleName")).setPreferredWidth(300);
    table.setRowHeight(30);
  }
  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_ADMIN_ROLE);
    btnAdminRoleAdd.setEnabled(er.getAdd());
    btnAdminRoleDel.setEnabled(er.getDelete());
    btnAdminRoleModify.setEnabled(er.getModify());
    btnAdminRoleSearch.setEnabled(er.getSearch());
  }
  void loadNewRoleID(){
    ResultSet rs = null;
    String roleIDs= DAL.getHostID() + "001";
    try {
      String sqlStr = "select ROLE_ID from BTM_IM_ROLE Where Role_Id not in ('001','002') order by ROLE_ID desc";
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);

      rs = DAL.executeScrollQueries(sqlStr, stmt);
      if(rs.next()){
        int iRole = Integer.parseInt(rs.getString("ROLE_ID")) + 1;
        roleIDs = "000" + String.valueOf(iRole);
        roleIDs = roleIDs.substring(roleIDs.length()-4);
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    txtRoleID.setText(roleIDs);
  }
  void showButton(boolean flag){
    jPanel4.removeAll();
    jPanel4.updateUI();
    jPanel4.add(btnPrdHierTypeDone, null);
    if(flag == true){
      jPanel4.add(btnAdminRoleAdd, null);
    }
    else{
      jPanel4.add(btnAdminRoleModify, null);
    }
    jPanel4.add(btnAdminRoleSearch, null);
    jPanel4.add(btnAdminRoleClear, null);
    jPanel4.add(btnAdminRoleDel, null);
    jPanel4.add(btnAdminRoleCancel, null);
  }

  /** checkName method - if value ==true add button will enable = true, else = false
     * @author 		Tuan.Truong
     * @param 		no
     * @return		no
     */
    private boolean checkLenName(String name) {
      if (name.length()==0)  {
        return true;
      }else {
        return false;
      }
    }
  /** Cancel Button of Admin Role
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnAdminRoleCancel_actionPerformed(ActionEvent e) {
    vSql.clear();
    while (dm.getRowCount()>0) {
      dm.removeRow(0);
    }
    txtRoleDesc.setText("");
    flagHotkey = true;
    showButton(true);
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
  }

  void btnAdminRoleAdd_actionPerformed(ActionEvent e) {
    String roleID = txtRoleID.getText();
    String roleName = txtRoleDesc.getText();

    if (roleName.trim().length()==0) {
      ut.showMessage(frmMain, lang.getString("TT012"), lang.getString("MS1063_RoleNameNotNull"));
      txtRoleDesc.requestFocus(true);
      return;
    }
    if(checkExist(roleName)){
      ut.showMessage(frmMain, lang.getString("TT012"), lang.getString("MS1062_RoleNameExisted"));
      txtRoleDesc.requestFocus(true);
      return;
    }
    if (checkExistOnGrid(roleName)) {
      ut.showMessage(frmMain, lang.getString("TT012"), lang.getString("MS1062_RoleNameExisted"));
      txtRoleDesc.requestFocus(true);
      return;
    }

    vSql.addElement("insert into BTM_IM_ROLE (ROLE_ID, ROLE_NAME) values ('" + roleID +"','" + roleName + "')");
    String[] rowData = new String[] {
                           roleID, roleName};
    dm.addRow(rowData);
//    str.installInTable(table, Color.lightGray, Color.white, null, null);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

    txtRoleID.setText(getNewRoleID(roleID));
    txtRoleDesc.setText("");
    txtRoleDesc.requestFocus(true);
  }
 private boolean checkExist(String roleName){
    ResultSet rs = null;
    String sqlStr = "Select Role_Name From BTM_IM_ROLE Where Role_Name='" + roleName + "'";
    try{
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr,stmt);
      if(rs.next()){
        stmt.close();
        return true;
      }
      stmt.close();
    }
    catch(Exception ex){}
    return false;
  }
  private boolean  checkExistOnGrid(String roleName){
    for(int i=0;i < dm.getRowCount();i++){
      if(dm.getValueAt(i,1).toString().equals(roleName.trim()))
        return true;
    }
    return false;
  }

  private boolean checkExistOnGridModify(String roleName) {
    for (int i = 0; i < dm.getRowCount(); i++) {
      if (i != table.getSelectedRow() &&
          dm.getValueAt(i, 1).toString().equals(roleName.trim()))
        return true;
    }
    return false;
  }

   /** Delete data from DataGrid
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   void deleteRowDataGrid(int roleID, int row) {
//     System.out.println("delete from BTM_IM_ROLE where ROLE_ID = " + roleID);
     try{
       stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);

       DAL.executeUpdate("delete from BTM_IM_ROLE where ROLE_ID = " + roleID);
       stmt.close();
     }
     catch(Exception ex){};
   }

   /** Insert data method
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   void addRole(String roleID, String roleDesc, String whichApp) {
//     System.out.println("insert into BTM_IM_ROLE (ROLE_ID, ROLE_NAME) values ('" + roleID +"','" + roleDesc + "')");
     try{
       stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);

       DAL.executeUpdate(
           "insert into BTM_IM_ROLE (ROLE_ID, ROLE_NAME) values ('" + roleID +
           "','" + roleDesc + "')");
        stmt.close();
     }catch(Exception ex){};
   }

    /** check new role ID - if right then return true, else false
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   private boolean checkNewRoleID() {
     boolean check=false;
     return check;
   }

   /** Create new RoleID
    * @author 		Vinh.Le
    * @param 		oldRoleId
    * @return		newRoleID
    */
   private String getNewRoleID(String oldRoleID) {
     int newRoleID = Integer.parseInt(oldRoleID);
     if (newRoleID < 9999)
       newRoleID++;
     String sRoleID = "000" + String.valueOf(newRoleID);
     sRoleID = sRoleID.substring(sRoleID.length() - 4);
     return sRoleID;
   } //end method get Parent

  /** Delete Button
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnAdminRoleDel_actionPerformed(ActionEvent e) {
    int row= table.getSelectedRow();
    if(row>=0){
      int choose = ut.showMessage(frmMain, lang.getString("TT012"),
                                  lang.getString("MS1066_WantDeleteRole") + table.getValueAt(table.getSelectedRow(),1).toString() + "' ?", 3, DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.YES_VALUE) {
        vSql.remove(row);
        dm.removeRow(row);
        if (flagHotkey == false) {
          txtRoleID.setText(currentRoleID);
        }
        txtRoleDesc.setText("");
        txtRoleDesc.requestFocus(true);
        flagHotkey = true;
        showButton(true);
      }
    }
    else{
      if(table.getRowCount()>0){
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1065_ChooseRoleWantDelete"));
        return;
      }
    }
  }

  void btnPrdHierTypeDone_actionPerformed(ActionEvent e) {
    if(table.getRowCount()>0){
      int choose = ut.showMessage(frmMain, lang.getString("TT012"),
                                  lang.getString("MS1064_SaveChangesRole"), 3, 1);
      if (choose == DialogMessage.YES_VALUE) { //save
        if (vSql.isEmpty() == false) {
          try{
            stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);
            DAL.executeBatchQuery(vSql, stmt);
            stmt.close();
          }
          catch(Exception ex){};
        }
      }
      else if (choose == DialogMessage.CANCEL_VALUE) {
        return;
      }
    }
    vSql.clear();
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    vSql.removeAllElements();
    flagHotkey = true;
    showButton(true);
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
  }


  void txtRoleDesc_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,this.txtRoleDesc,Role.LEN_ROLE_NAME);
  }

  void table_mouseClicked(MouseEvent e) {
    if(e.getClickCount()==2){
      int row = table.getSelectedRow();
      if(row>=0){
        currentRoleID = txtRoleID.getText();// save current Role ID
        txtRoleID.setText(table.getValueAt(row,0).toString());
        txtRoleDesc.setText(table.getValueAt(row,1).toString());
        txtRoleDesc.requestFocus(true);
        flagHotkey = false;
        showButton(false);
      }
    }
  }

  void btnAdminRoleSearch_actionPerformed(ActionEvent e) {

    if(flagHotkey == false){
      txtRoleID.setText(currentRoleID);
    }
    flagHotkey = true;
    showButton(true);

    //show Dialog Search
    DialogAminRoleSearch dlgAdminRoleSearch = new DialogAminRoleSearch(frmMain,
        lang.getString("TT0096"), true, frmMain,frmMain.iSeasonBusObj);
    dlgAdminRoleSearch.setLocation(112,85);
    dlgAdminRoleSearch.setVisible(true);
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
         btnPrdHierTypeDone.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F2) {
         if(flagHotkey == true){
           btnAdminRoleAdd.doClick();
         }else{
           btnAdminRoleModify.doClick();
         }
       }
       else if (identifier.intValue() == KeyEvent.VK_F8) {
         btnAdminRoleDel.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F3) {
         btnAdminRoleSearch.doClick();
       }

       else if (identifier.intValue() == KeyEvent.VK_F7) {
         btnAdminRoleClear.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
         btnAdminRoleCancel.doClick();
       }
     }
   }

  void btnAdminRoleClear_actionPerformed(ActionEvent e) {
    txtRoleDesc.setText("");
    if(flagHotkey == false){
      txtRoleID.setText(currentRoleID);
    }
    flagHotkey = true;
    showButton(true);
  }

  void btnAdminRoleModify_actionPerformed(ActionEvent e) {
    String roleID = txtRoleID.getText();
    String roleName = txtRoleDesc.getText();

   if (roleName.trim().length()==0) {
     ut.showMessage(frmMain, lang.getString("TT012"), lang.getString("MS1063_RoleNameNotNull"));
     txtRoleDesc.requestFocus(true);
     return;
   }
   if(checkExist(roleName)){
     ut.showMessage(frmMain, lang.getString("TT012"), lang.getString("MS1062_RoleNameExisted"));
     txtRoleDesc.requestFocus(true);
     return;
   }
   if (checkExistOnGridModify(roleName)) {
     ut.showMessage(frmMain, lang.getString("TT012"), lang.getString("MS1062_RoleNameExisted"));
     txtRoleDesc.requestFocus(true);
     return;
   }
   table.setValueAt(txtRoleDesc.getText(),table.getSelectedRow(),1);
    if(flagHotkey == false){
      txtRoleID.setText(currentRoleID);
    }
    txtRoleDesc.setText("");
    flagHotkey = true;
    showButton(true);
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      if (flagHotkey == true) {
        btnAdminRoleAdd.doClick();
      }
      else {
        btnAdminRoleModify.doClick();
      }

               }

  }

}//end class



class PanelAdminRole_btnAdminRoleCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRole adaptee;

  PanelAdminRole_btnAdminRoleCancel_actionAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleCancel_actionPerformed(e);
  }
}

class PanelAdminRole_btnAdminRoleAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRole adaptee;

  PanelAdminRole_btnAdminRoleAdd_actionAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleAdd_actionPerformed(e);
  }
}

class PanelAdminRole_btnAdminRoleDel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRole adaptee;

  PanelAdminRole_btnAdminRoleDel_actionAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleDel_actionPerformed(e);
  }
}

class PanelAdminRole_btnPrdHierTypeDone_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRole adaptee;

  PanelAdminRole_btnPrdHierTypeDone_actionAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierTypeDone_actionPerformed(e);
  }
}

class PanelAdminRole_txtRoleDesc_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminRole adaptee;

  PanelAdminRole_txtRoleDesc_keyAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRoleDesc_keyTyped(e);
  }
}

class PanelAdminRole_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminRole adaptee;

  PanelAdminRole_table_mouseAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelAdminRole_btnAdminRoleSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRole adaptee;

  PanelAdminRole_btnAdminRoleSearch_actionAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleSearch_actionPerformed(e);
  }
}

class PanelAdminRole_btnAdminRoleClear_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRole adaptee;

  PanelAdminRole_btnAdminRoleClear_actionAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleClear_actionPerformed(e);
  }
}

class PanelAdminRole_btnAdminRoleModify_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRole adaptee;

  PanelAdminRole_btnAdminRoleModify_actionAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleModify_actionPerformed(e);
  }
}

class PanelAdminRole_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminRole adaptee;

  PanelAdminRole_table_keyAdapter(PanelAdminRole adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
