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
import javax.swing.border.*;
import javax.swing.event.*;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Admin -> Employee Role</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Vinh.Le
 * @version 1.0
 */

public class PanelAdminGrantRole extends JPanel {
  Utils ut = new Utils();
  //define for panel
  FrameMainSim frmMain;
  Statement stmt = null;
  CardLayout cardLayout;
  JPanel parent;
  String empID;
  DataAccessLayer DAL;
  JPanel pnlMain = new JPanel();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  Vector  vSql = new Vector();

  BJButton btnDone = new BJButton();
  BJButton btnCancel = new BJButton();

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
  JPanel pnlTable = new JPanel();
  BJPanel pnlButtonContent = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel4 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JLabel lblInputItem = new JLabel();
  FlowLayout flowLayout2 = new FlowLayout();
  JScrollPane jScrollDataGrid = new JScrollPane();
  JScrollPane jScrollPane1 = new JScrollPane();
  JOptionPane jOpt = new JOptionPane();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JScrollPane jScrollPane2 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true);
  JPanel pnlEmployee = new JPanel();
  JPanel pnlRole = new JPanel();
  JPanel pnlTitle = new JPanel();
  JPanel pnlRoleList = new JPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  JPanel pnlButton = new JPanel();
  JPanel pnlTileGrant = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JButton btnUp = new JButton();
  JButton btnDown = new JButton();
  JLabel jLabel1 = new JLabel();
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout8 = new BorderLayout();
  BJListBox listEmployee = new BJListBox();
  BorderLayout borderLayout9 = new BorderLayout();
  BorderLayout borderLayout10 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout11 = new BorderLayout();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  BorderLayout borderLayout12 = new BorderLayout();
  BorderLayout borderLayout13 = new BorderLayout();
  BJListBox listRole = new BJListBox();
  JScrollPane jscrListEmp = new JScrollPane();
  JScrollPane jscrListRole = new JScrollPane();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international


  public PanelAdminGrantRole(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    this.setSize(new Dimension(822, 600));
   // DAL.getConnfigParameter();


    pnlMain.setBackground(new Color(55, 194, 255));
    pnlMain.setLayout(borderLayout2);
    pnlCenter.setMinimumSize(new Dimension(10, 10));
    pnlCenter.setPreferredSize(new Dimension(660, 60));
    pnlCenter.setLayout(borderLayout4);


    pnlTable.setMinimumSize(new Dimension(10,10));
    pnlTable.setPreferredSize(new Dimension(660, 357));
    pnlTable.setLayout(borderLayout5);
     //for status
    pnlStatus.setLayout(gridLayout1);
    pnlHeader.setLayout(borderLayout3);

    pnlButtonContent.setLayout(new BoxLayout(pnlButtonContent, BoxLayout.PAGE_AXIS));

    pnlHeader.setPreferredSize(new Dimension(102, 45));

    //for button of header
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</h" +
    "tml>");
    btnDone.addActionListener(new PanelAdminGrantRole_btnDone_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Done")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelAdminGrantRole_btnCancel_actionAdapter(this));

    jPanel4.setLayout(flowLayout2);
    gridLayout1.setColumns(0);
    gridLayout1.setRows(1);

    lblInputItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    pnlEmployee.setPreferredSize(new Dimension(350, 10));
    pnlEmployee.setLayout(borderLayout9);
    pnlEmployee.setBorder(BorderFactory.createEtchedBorder());
    pnlEmployee.setOpaque(true);
    pnlRole.setPreferredSize(new Dimension(10, 250));
    pnlRole.setLayout(borderLayout7);
    pnlRoleList.setBackground(SystemColor.control);
    pnlRoleList.setBorder(BorderFactory.createEtchedBorder());
    pnlRoleList.setLayout(borderLayout10);
    pnlTitle.setPreferredSize(new Dimension(10, 40));
    pnlTitle.setLayout(borderLayout6);
    pnlTileGrant.setPreferredSize(new Dimension(80, 10));
    pnlTileGrant.setLayout(borderLayout8);
    btnUp.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnUp.setText("^");
    btnUp.addActionListener(new PanelAdminGrantRole_btnUp_actionAdapter(this));
    btnDown.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDown.setText("v");
    btnDown.addActionListener(new PanelAdminGrantRole_btnDown_actionAdapter(this));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(42, 30));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText(lang.getString("Granted")+":");
    pnlButton.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.CENTER);
    listEmployee.setBorder(BorderFactory.createLoweredBevelBorder());
    listEmployee.addMouseListener(new PanelAdminGrantRole_listEmployee_mouseAdapter(this));
    jPanel1.setPreferredSize(new Dimension(10, 40));
    jPanel1.setLayout(borderLayout11);
    jPanel3.setOpaque(true);
    jPanel3.setPreferredSize(new Dimension(360, 10));
    jPanel3.setLayout(borderLayout13);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(100, 24));
    jLabel2.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel2.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel2.setText(lang.getString("Employee")+":");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(25, 24));
    jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel3.setText(lang.getString("Role")+":");
    jPanel2.setLayout(borderLayout12);
    listRole.setBorder(BorderFactory.createLoweredBevelBorder());
    table.addMouseListener(new PanelAdminGrantRole_table_mouseAdapter(this));
    table.addKeyListener(new PanelAdminGrantRole_table_keyAdapter(this));
    this.add(pnlMain, BorderLayout.CENTER);
    pnlMain.add(pnlCenter, BorderLayout.CENTER);

    //add all panel
    pnlMain.add(pnlStatus, BorderLayout.SOUTH);
    pnlMain.add(pnlHeader, BorderLayout.NORTH);
    pnlHeader.add(pnlButtonContent,  BorderLayout.CENTER);
    pnlButtonContent.add(jPanel4, null);

    //add button into panl header
    jPanel4.add(btnDone, null);
    jPanel4.add(btnCancel, null);

    pnlCenter.add(pnlTable, BorderLayout.CENTER);
    pnlTable.add(jScrollPane2,  BorderLayout.CENTER);
    pnlTable.add(pnlRole, BorderLayout.NORTH);
    pnlRole.add(pnlRoleList,  BorderLayout.CENTER);
    //pnlRoleList.add(listRole,  BorderLayout.CENTER);
    pnlRoleList.add(jscrListRole,  BorderLayout.CENTER);
    jscrListRole.getViewport().add(listRole, null);
    pnlRole.add(pnlTitle,  BorderLayout.SOUTH);
    pnlCenter.add(pnlEmployee, BorderLayout.WEST);
    pnlCenter.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel3, BorderLayout.WEST);
    jPanel3.add(jLabel2,  BorderLayout.SOUTH);
    jPanel1.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jLabel3,  BorderLayout.SOUTH);
    jScrollPane2.getViewport().add(table, null);
    pnlTitle.add(pnlTileGrant,  BorderLayout.WEST);
    pnlTileGrant.add(jLabel1,  BorderLayout.SOUTH);
    pnlTitle.add(pnlButton, BorderLayout.CENTER);
    pnlButton.add(btnDown, null);
    pnlButton.add(btnUp, null);
    pnlEmployee.add(jscrListEmp, BorderLayout.CENTER);
    jscrListEmp.getViewport().add(listEmployee, null);

   //define for table
    String[] columnNames = new String[]{lang.getString("RoleID"), lang.getString("RoleName")};
    dm.setDataVector(new Object[][]{},columnNames);
    listRole.addMouseListener(new PanelAdminGrantRole_listRole_mouseAdapter(this));
  }

  //apply employee's permission for this screen
  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_SUPPLIER_SETUP);
    btnDone.setEnabled(er.getAdd());
  }


  private void setTableModel(){
   table.setColumnWidth(0,70);
   table.setColumnWidth(1,150);
   table.setHeaderName(lang.getString("RoleID"),0);
   table.setHeaderName(lang.getString("RoleName"),1);
   table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
   table.getTableHeader().setReorderingAllowed(false);
   table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
   table.setRowHeight(30);
  }



  public void initScreen(){

    listEmployee.removeAll();
    vSql.clear();
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                       TYPE_SCROLL_INSENSITIVE,
                                       ResultSet.CONCUR_READ_ONLY);
      listEmployee.setData(getDataListEmployee(stmt));
      stmt.close();
    }
    catch(Exception ex){};
    if (listEmployee.getRowCount() > 0) {
      listEmployee.setSelectedIndex(0);
      empID = listEmployee.getSelectedData();
      showData();
      setTableModel();
    }
  }

  private ResultSet getDataListEmployee(Statement stmt) {
    ResultSet rs = null;
    try {
      //0000000001,0000000002 : empployee admin(BTM , Adidas)
      String sqlStr = "select EMP_ID, LAST_NAME || ' ' || FIRST_NAME AS EMP_NAME from BTM_IM_EMPLOYEE  where  EMP_ID not in ('0000000001007','0000000002004') order by EMP_ID asc";
//      System.out.println(sqlStr);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method

//  private ResultSet getDataListRole(Statement stmt) {
//    ResultSet rs = null;
//    String sqlStr = "select ROLE_ID, ROLE_NAME from BTM_IM_ROLE order by ROLE_ID asc";
//    try {
//      System.out.println(sqlStr);
//      rs = DAL.executeScrollQueries(sqlStr,stmt);
//    }
//    catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//    return rs;
//  } //end method

  void btnCancel_actionPerformed(ActionEvent e) {
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    vSql.clear();
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
  }
  private boolean  compareWithTable(String roleID){
    for(int i=0;i< table.getRowCount();i++){
      if(roleID.equals(dm.getValueAt(i,0).toString()))
        return true;//exist-->not different
    }
    return false;//not exist -->different
  }

  private boolean checkDifferentWithTable() {
    ResultSet rs = null;
    String sqlStr = "Select Role_ID From BTM_IM_EMPLOYEE_RIGHT Where EMP_ID='" +
        empID + "'";
    try {
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                       TYPE_SCROLL_INSENSITIVE,
                                       ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
      while (rs.next()) {
        //if not exist-->different
        if (!compareWithTable(rs.getString("Role_ID").toString())){
          rs.close();
          stmt.close();
          return true;
        }
      }
      rs.close();
      stmt.close();
    }
    catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);}
    return false; //not exist
  }

  private boolean checkDifferentWithDB() {
    for(int i=0;i<table.getRowCount(); i++){
      //if this role not belong to this emp-->different
      if(!checkExistInDB(dm.getValueAt(i,0).toString())){
        return true;
      }
    }
    return false;// not different with DB
  }

  private boolean checkExistInDB(String roleID) {
    ResultSet rs = null;
    String sqlStr = "Select * From BTM_IM_EMPLOYEE_RIGHT Where EMP_ID='" + empID + "' and ROLE_ID='" + roleID + "'";
    try{
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                     TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
      if(rs.next()){
        rs.close();
        stmt.close();
        return true;//exist
      }
      rs.close();
      stmt.close();
    }
    catch(SQLException ex){}
    return false;//not exist
  }

  void btnDone_actionPerformed(ActionEvent e) {
    int pr = 1;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                             TYPE_SCROLL_INSENSITIVE,
                                             ResultSet.CONCUR_READ_ONLY);
      pr = processEmployeeRole(stmt);
      stmt.close();
    }
    catch(Exception ex){};
    if(pr!=3){
      while (dm.getRowCount() > 0) {
        dm.removeRow(0);
      }
      vSql.removeAllElements();
      frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    }
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
        btnDone.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  private void addRole() {
    if(listRole.getSelectedIndex()>=0){
      String roleID = listRole.getSelectedData();
      String roleName =  listRole.getSelectedValue().toString();
      vSql.addElement("Delete BTM_IM_EMPLOYEE_RIGHT Where Emp_ID='" + empID +
                      "' and Role_ID='" + roleID + "'");
      vSql.addElement("Insert Into BTM_IM_EMPLOYEE_RIGHT values ('" + empID +
                      "','" + roleID + "')");
      String[] rowData = new String[] {
          roleID, roleName};
      dm.addRow(rowData);
//      str.installInTable(table, Color.lightGray, Color.white, null, null);
      float[] f1 = new float[3];
      Color.RGBtoHSB(255,255,230,f1);
      str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

      //refresh on list
      String sRoleID = getStringRoleID();
      listRole.removeAll();
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
        listRole.setData(getDataListRoleForEmployee(empID, sRoleID, stmt));
        stmt.close();
      }
      catch(Exception ex){};
    }
  }

  void btnDown_actionPerformed(ActionEvent e) {
    addRole();
  }

  void btnUp_actionPerformed(ActionEvent e) {
    removeRole();
  }

  void listRole_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      addRole();
    }
  }

  private boolean checkExistOnGrid(String roleName) {
    for (int i = 0; i < dm.getRowCount(); i++) {
      if (dm.getValueAt(i, 1).toString().equals(roleName.trim()))
        return true;
    }
    return false;
  }

  private void showData(){
    //clear on table
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      //show roles of selected employee on table
      table.setData(getRolesForEmployee(empID, stmt));
      //show roles of selected employee on list
      String sRoleID = getStringRoleID();
      listRole.removeAll();

      listRole.setData(getDataListRoleForEmployee(empID, sRoleID, stmt));
      stmt.close();
    }
    catch(Exception ex){};
  }
  private String getStringRoleID(){
    String s="";
    for(int i=0 ; i < dm.getRowCount();i++){
      s+="'" + dm.getValueAt(i,0).toString() + "',";
    }
    if(s.trim().length()>0)
      s = s.substring(0,s.length()-1);
     return s;
  }

  private ResultSet getDataListRoleForEmployee(String empID,String sRoleID,Statement stmt) {
    ResultSet rs = null;
    String condition = "'001','002'";//
    String sqlStr = "select ROLE_ID, ROLE_NAME from BTM_IM_ROLE";
    if(sRoleID.trim().length()>0)
      condition+= "," + sRoleID;
    sqlStr+=" Where Role_ID not in(" + condition + ") order by ROLE_ID asc";
    try {
//      System.out.println(sqlStr);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  private ResultSet getRolesForEmployee(String empID,Statement stmt) {
    ResultSet rs = null;
    String sqlStr = "select er.ROLE_ID,ROLE_NAME from btm_im_employee_right er,btm_im_role r where er.ROLE_ID=r.ROLE_ID and emp_id='" + empID + "'";
    try{
//      System.out.println(sqlStr);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch(Exception ex){
    }
    return rs;
  }

  private void removeRole() {
    int row = table.getSelectedRow();
    if (row >= 0) {
      String roleID = dm.getValueAt(row, 0).toString();
      vSql.addElement("Delete BTM_IM_EMPLOYEE_RIGHT Where Emp_ID='" + empID +
                      "' and Role_ID='" + roleID + "'");
      dm.removeRow(row);
      //refresh on list
      String sRoleID = getStringRoleID();
      listRole.removeAll();
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
        listRole.setData(getDataListRoleForEmployee(empID, sRoleID,stmt));
        stmt.close();
      }
      catch(Exception ex){};
    }

  }
  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      removeRole();
    }
  }

  private int processEmployeeRole(Statement stmt) {
    if (checkDifferentWithDB() || checkDifferentWithTable()) { //different with DB -->update
      int choose = ut.showMessage(frmMain, lang.getString("TT012"),
                                  lang.getString("MS1066_DoYouWantSave"), 3, 1);
      if (choose == DialogMessage.YES_VALUE) { //save
        if (vSql.isEmpty() == false) {
          DAL.executeBatchQuery(vSql,stmt);
          return 1;//YES
        }
      }
      else if (choose == DialogMessage.CANCEL_VALUE) {
        return 3;//Cancel
      }
    }
    return 2;//NO

  }

  void listEmployee_mouseClicked(MouseEvent e) {

    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      processEmployeeRole(stmt);
      stmt.close();
    }
    catch(Exception ex){};
    empID = listEmployee.getSelectedData();
    showData();
    setTableModel();
    vSql.clear();
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnCancel.doClick();
               }
  }
} //end class

class PanelAdminGrantRole_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminGrantRole adaptee;

  PanelAdminGrantRole_btnCancel_actionAdapter(PanelAdminGrantRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelAdminGrantRole_btnDown_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminGrantRole adaptee;

  PanelAdminGrantRole_btnDown_actionAdapter(PanelAdminGrantRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDown_actionPerformed(e);
  }
}

class PanelAdminGrantRole_btnUp_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminGrantRole adaptee;

  PanelAdminGrantRole_btnUp_actionAdapter(PanelAdminGrantRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnUp_actionPerformed(e);
  }
}

class PanelAdminGrantRole_listRole_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminGrantRole adaptee;

  PanelAdminGrantRole_listRole_mouseAdapter(PanelAdminGrantRole adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.listRole_mouseClicked(e);
  }
}

class PanelAdminGrantRole_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminGrantRole adaptee;

  PanelAdminGrantRole_table_mouseAdapter(PanelAdminGrantRole adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelAdminGrantRole_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminGrantRole adaptee;

  PanelAdminGrantRole_btnDone_actionAdapter(PanelAdminGrantRole adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelAdminGrantRole_listEmployee_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminGrantRole adaptee;

  PanelAdminGrantRole_listEmployee_mouseAdapter(PanelAdminGrantRole adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.listEmployee_mouseClicked(e);
  }
}

class PanelAdminGrantRole_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminGrantRole adaptee;

  PanelAdminGrantRole_table_keyAdapter(PanelAdminGrantRole adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

