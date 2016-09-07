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

/**
 * <p>Title: </p>
 * <p>Description: Main -> Amin -> Role</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */

public class PanelAdminAccessLevel extends JPanel {
  Utils ut = new Utils();
  AdminBusObj adminBusObj = new AdminBusObj();
  int PrefixCodeLength = ut.PREFIX_CODE_LENGTH;
  //define for panel
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;
  DataAccessLayer DAL;
  JPanel pnlMain = new JPanel();
  Vector vSql = new Vector();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  boolean theFirstAdd=false;
  int accessId;
  BJButton btnPrdHierTypeDone = new BJButton();
  BJButton btnAdminAccessLevelAdd = new BJButton();
  BJButton btnAdminAccessLevelDel = new BJButton();
  BJButton btnAdminAccessLevelSearch = new BJButton();
  BJButton btnAdminAccessLevelCancel = new BJButton();

  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  //create panel
  BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel pnlCenter = new JPanel();
  JPanel pnlStatus = new JPanel();
  JLabel lblRoleDesc = new JLabel();
  JTextField txtAccessName = new JTextField();

  //grid layout
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout2 = new GridLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel pnlText = new JPanel();
  JPanel pnlTable = new JPanel();
  BJPanel pnlButtonContent = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel3 = new BJPanel(Constant.PANEL_TYPE_HEADER);
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
  JTable table = new JTable(dm);
  JLabel jLabel1 = new JLabel();
  JTextField txtAccessCode = new JTextField();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

  /** Create Panel for Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		FrameMainSim frm, CardLayout crdLayout, JPanel parent
   * @return		no
   */
  public PanelAdminAccessLevel(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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
    this.setLayout(borderLayout1);
    this.setSize(800,600);
    //DAL.getConnfigParameter();

    pnlMain.setBackground(new Color(55, 194, 255));
    pnlMain.setLayout(borderLayout2);
//    jButton1.addActionListener(new FrameDetailTrans_jButton1_actionAdapter(this));
    pnlCenter.setBackground(UIManager.getColor("control"));
    pnlCenter.setMinimumSize(new Dimension(10, 10));
    pnlCenter.setPreferredSize(new Dimension(660, 60));
    pnlCenter.setLayout(borderLayout4);

    pnlText.setBackground(SystemColor.control);
    pnlText.setMinimumSize(new Dimension(10,10));
    pnlText.setPreferredSize(new Dimension(660, 100));
    pnlText.setRequestFocusEnabled(true);
    pnlText.setLayout(new BorderLayout());

    pnlTable.setBackground(SystemColor.control);
    pnlTable.setMinimumSize(new Dimension(10,10));
    pnlTable.setPreferredSize(new Dimension(660, 357));
    pnlTable.setLayout(borderLayout5);

    lblRoleDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRoleDesc.setPreferredSize(new Dimension(100, 21));
    lblRoleDesc.setText(lang.getString("AccessName")+":");
    lblRoleDesc.setBounds(new Rectangle(14, 55, 100, 21));
    txtAccessName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAccessName.setPreferredSize(new Dimension(240, 21));
    txtAccessName.setBounds(new Rectangle(29, 56, 240, 22));
    txtAccessName.addKeyListener(new PanelAdminAccessLevel_txtAccessName_keyAdapter(this));
    jPanel6.setBackground(SystemColor.control);
    jPanel6.setMinimumSize(new Dimension(38, 10));
    jPanel6.setPreferredSize(new Dimension(60, 10));
    jPanel6.setLayout(null);
    jPanel2.setBackground(SystemColor.control);
    jPanel2.setLayout(null);
    jPanel5.setBackground(SystemColor.control);
//    table.addMouseListener(new FrameDetailTrans_table_mouseAdapter(this));
    btnAdminAccessLevelAdd.addActionListener(new PanelAdminAccessLevel_btnAdminAccessLevelAdd_actionAdapter(this));
    flowLayout2.setAlignment(FlowLayout.LEFT);

    btnAdminAccessLevelDel.addActionListener(new PanelAdminAccessLevel_btnAdminAccessLevelDel_actionAdapter(this));
    btnPrdHierTypeDone.addActionListener(new PanelAdminAccessLevel_btnPrdHierTypeDone_actionAdapter(this));


    //for status
    pnlStatus.setLayout(gridLayout1);
    pnlHeader.setLayout(borderLayout3);

    pnlButtonContent.setLayout(new BoxLayout(pnlButtonContent, BoxLayout.PAGE_AXIS));

    jPanel1.setPreferredSize(new Dimension(20, 10));
    jPanel3.setPreferredSize(new Dimension(50, 10));
    pnlHeader.setPreferredSize(new Dimension(102, 45));

    //for button of header
    btnPrdHierTypeDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrdHierTypeDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnPrdHierTypeDone.setText(lang.getString("Done")+" (F1)");
    btnAdminAccessLevelAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminAccessLevelAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdminAccessLevelAdd.setText(lang.getString("Add")+" (F2)");
    btnAdminAccessLevelDel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminAccessLevelDel.setToolTipText(lang.getString("Delete")+" (F3)");
    btnAdminAccessLevelDel.setText(lang.getString("Delete")+" (F3)");
    btnAdminAccessLevelSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminAccessLevelSearch.setToolTipText(lang.getString("Search")+" (F4)");
    btnAdminAccessLevelSearch.setText(lang.getString("Search")+" (F4)");
    btnAdminAccessLevelSearch.addActionListener(new PanelAdminAccessLevel_btnAdminAccessLevelSearch_actionAdapter(this));
    btnAdminAccessLevelCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminAccessLevelCancel.setToolTipText(lang.getString("Cancel")+" (F5)");
    btnAdminAccessLevelCancel.setText(lang.getString("Cancel")+" (F5)");
    btnAdminAccessLevelCancel.addActionListener(new PanelAdminAccessLevel_btnAdminAccessLevelCancel_actionAdapter(this));

    jPanel4.setLayout(flowLayout2);
    gridLayout1.setColumns(0);
    gridLayout1.setRows(1);

    jPanel2.setPreferredSize(new Dimension(120, 10));
    jPanel5.setPreferredSize(new Dimension(290, 10));
    table.addMouseListener(new PanelAdminAccessLevel_table_mouseAdapter(this));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setText(lang.getString("AccessCode")+":");
    jLabel1.setBounds(new Rectangle(14, 19, 95, 25));
    txtAccessCode.setText("");
    txtAccessCode.setBounds(new Rectangle(29, 22, 240, 24));
    table.addKeyListener(new PanelAdminAccessLevel_table_keyAdapter(this));
    this.add(pnlMain, BorderLayout.CENTER);
    pnlMain.add(pnlCenter, BorderLayout.CENTER);

    pnlCenter.add(pnlText, BorderLayout.NORTH);
    pnlText.add(jPanel2, BorderLayout.WEST);
    jPanel2.add(lblRoleDesc, null);
    jPanel2.add(jLabel1, null);
    pnlText.add(jPanel5, BorderLayout.EAST);
    pnlText.add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(txtAccessName, null);
    jPanel6.add(txtAccessCode, null);

    //add all panel
    pnlMain.add(pnlStatus, BorderLayout.SOUTH);
    pnlMain.add(pnlHeader, BorderLayout.NORTH);
    pnlHeader.add(jPanel1, BorderLayout.WEST);
    pnlHeader.add(pnlButtonContent,  BorderLayout.CENTER);
    pnlButtonContent.add(jPanel4, null);
    pnlHeader.add(jPanel3,  BorderLayout.EAST);
    //add button into panl header
    jPanel4.add(btnPrdHierTypeDone, null);
    jPanel4.add(btnAdminAccessLevelAdd, null);
    jPanel4.add(btnAdminAccessLevelDel, null);
    jPanel4.add(btnAdminAccessLevelSearch, null);
    jPanel4.add(btnAdminAccessLevelCancel, null);


    pnlCenter.add(pnlTable, BorderLayout.CENTER);
    pnlTable.add(jScrollPane2,  BorderLayout.CENTER);
    jScrollPane2.getViewport().add(table, null);

   //define for table
    String[] columnNames = new String[]{lang.getString("AccessId"), lang.getString("AccessCode"), lang.getString("AccessName")};
    dm.setDataVector(new Object[][]{},columnNames);
    table.getColumn(lang.getString("AccessId")).setPreferredWidth(120);
    table.getColumn(lang.getString("AccessCode")).setPreferredWidth(120);
    table.getColumn(lang.getString("AccessName")).setPreferredWidth(300);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    table.setRowHeight(30);
    setEnableAddButton(false);
    setEnableDelButton(false);
    theFirstAdd=true;
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

    /** setEnableAddButton method - if value ==true add button will enable = true, else = false
      * @author 		Tuan.Truong
      * @param 		no
      * @return		no
      */
     private void setEnableAddButton(boolean value){
       if (value == true) {
         this.btnAdminAccessLevelAdd.setEnabled(true);
       }else {
         this.btnAdminAccessLevelAdd.setEnabled(false);
       }
     }

     /** setEnableDelButton method - if value ==true add button will enable = true, else = false
      * @author 		Tuan.Truong
      * @param 		no
      * @return		no
      */
     private void setEnableDelButton(boolean value){
       if (value == true) {
         this.btnAdminAccessLevelDel.setEnabled(true);
       }else {
         this.btnAdminAccessLevelDel.setEnabled(false);
       }
     }

  /** Cancel Button of Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnAdminAccessLevelCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
  }

  private String checkCode() {
    if (this.txtAccessCode.getText().toString().trim().length()==0) {
      ut.showMessage(frmMain,lang.getString("TT011"),lang.getString("MS1033_AccessCodeNotNull"));
      this.txtAccessCode.requestFocus(true);
      return null;
    }else {
      return this.txtAccessCode.getText().trim();
    }
  }

  private String checkName() {
    if (this.txtAccessName.getText().toString().trim().length()==0) {
      ut.showMessage(frmMain,lang.getString("TT011"),lang.getString("MS1034_AccessNameNotNull"));
      this.txtAccessName.requestFocus(true);
      return null;
    }else {
      return this.txtAccessName.getText().trim();
    }
  }

  /** Add Button
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnAdminAccessLevelAdd_actionPerformed(ActionEvent e) {
    String accessCode = checkCode();
    String accessName = checkName();
    if (accessCode ==null) return;
    if (accessName ==null) return;
    //add into element
    if (theFirstAdd==true) {
      accessId = adminBusObj.getNewAccessID(DAL);
      theFirstAdd=false;
    }else {
//      accessId = adminBusObj.addNewRoleId(accessId, DAL);
      theFirstAdd=false;
    }
    String sSQL = "insert into BTM_IM_ACCESS_RIGHT (ACCESS_ID, ACCESS_CODE, ACCESS_NAME) values ('" + accessId +"','" + accessCode + "','" + accessName + ")";
//    System.out.println(sSQL);
    vSql.addElement(sSQL);
    String[] rowData = new String[] {
                           "1", accessCode, accessName
    };
    dm.addRow(rowData);
//    str.installInTable(table, Color.lightGray, Color.white, null, null);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

    this.txtAccessName.setText(null);
  }

   /** Delete data from DataGrid
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   void deleteRowDataGrid(int roleID, int row) {
//     System.out.println("delete from BTM_IM_ROLE where ROLE_ID = " + roleID);
     DAL.executeUpdate("delete from BTM_IM_ROLE where ROLE_ID = " + roleID);
   }

   /** Insert data method
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   void addRole(String roleID, String roleDesc, String whichApp) {
//     System.out.println("insert into BTM_IM_ROLE (ROLE_ID, ROLE_NAME) values ('" + roleID +"','" + roleDesc + "')");
     DAL.executeUpdate("insert into BTM_IM_ROLE (ROLE_ID, ROLE_NAME) values ('" + roleID +"','" + roleDesc + "')");
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
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   private String getNewRoleID(String name) {
     if (name==Constant.root) return Constant.startId;
     ResultSet rs = null;
     String sSQL = null;
     String st=null;
     sSQL = "select ROLE_ID from BTM_IM_ROLE order by ROLE_ID desc";
//     System.out.println(sSQL);
     rs = DAL.executeQueries(sSQL);
     try {
       rs.next();
       int k=Integer.parseInt(rs.getString("ROLE_ID").trim())+1;
       st=ut.addYeroCode(PrefixCodeLength) + k;
       st=st.substring(st.length()-PrefixCodeLength-1,st.length());
       rs.getStatement().close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
     }

     return st;
   }//end method get Parent

  /** Delete Button
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnAdminAccessLevelDel_actionPerformed(ActionEvent e) {
    String s= table.getValueAt(table.getSelectedRow(),0).toString();
    int roleID = Integer.parseInt(s);
    int row= table.getSelectedRow();
    int choose = ut.showMessage(frmMain,lang.getString("TT012"),lang.getString("MS1035_DeleteThisRole"),3,1);
    if (choose==DialogMessage.YES_VALUE) {
       dm.removeRow(table.getSelectedRow());
     }
  }

  void btnPrdHierTypeDone_actionPerformed(ActionEvent e) {


//    String t = "11111";
//    empRight = new EmpRight(t);

//    int choose = ut.showMessage(frmMain,"Admin Role","Do you want to save ?",3,1);
//    if (choose==DialogMessage.YES_VALUE) {//save
//      if (vSql.isEmpty() == false){
//          DAL.executeBatchQuery(vSql);
//        }
//        while (dm.getRowCount()>0){
//          dm.removeRow(0);
//        }
//        vSql.removeAllElements();
//        frmMain.showScreen(Constant.SCRS_MAIN_SIM);
//    }else if (choose ==DialogMessage.NO_VALUE) {
//      while (dm.getRowCount()>0){
//        dm.removeRow(0);
//      }
//      vSql.removeAllElements();
//      frmMain.showScreen(Constant.SCRS_MAIN_SIM);
//    }
  }

  void txtAccessName_keyReleased(KeyEvent e) {
    if (checkLenName(this.txtAccessName.getText().toString()) ) {
      setEnableAddButton(false);
    }else {
      setEnableAddButton(true);
    }
  }

  void txtAccessName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,this.txtAccessName,Role.LEN_ROLE_NAME);
  }

  void table_mouseClicked(MouseEvent e) {
    String id = table.getValueAt(table.getSelectedRow(),0).toString();
    if (id.length()!=0) {
      setEnableDelButton(true);
    }else {
      setEnableDelButton(false);
    }
  }

  void btnAdminAccessLevelSearch_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_ADMIN_ROLE_SEARCH);
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
         btnPrdHierTypeDone.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F2) {
         btnAdminAccessLevelAdd.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F3) {
         btnAdminAccessLevelDel.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F4) {
         btnAdminAccessLevelSearch.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F5 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
         btnAdminAccessLevelCancel.doClick();
       }
     }
   }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      btnAdminAccessLevelAdd.doClick();
           }
  }

}//end class



class PanelAdminAccessLevel_btnAdminAccessLevelCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminAccessLevel adaptee;

  PanelAdminAccessLevel_btnAdminAccessLevelCancel_actionAdapter(PanelAdminAccessLevel adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminAccessLevelCancel_actionPerformed(e);
  }
}

class PanelAdminAccessLevel_btnAdminAccessLevelAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminAccessLevel adaptee;

  PanelAdminAccessLevel_btnAdminAccessLevelAdd_actionAdapter(PanelAdminAccessLevel adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminAccessLevelAdd_actionPerformed(e);
  }
}

class PanelAdminAccessLevel_btnAdminAccessLevelDel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminAccessLevel adaptee;

  PanelAdminAccessLevel_btnAdminAccessLevelDel_actionAdapter(PanelAdminAccessLevel adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminAccessLevelDel_actionPerformed(e);
  }
}

class PanelAdminAccessLevel_btnPrdHierTypeDone_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminAccessLevel adaptee;

  PanelAdminAccessLevel_btnPrdHierTypeDone_actionAdapter(PanelAdminAccessLevel adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierTypeDone_actionPerformed(e);
  }
}

class PanelAdminAccessLevel_txtAccessName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminAccessLevel adaptee;

  PanelAdminAccessLevel_txtAccessName_keyAdapter(PanelAdminAccessLevel adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtAccessName_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAccessName_keyTyped(e);
  }
}

class PanelAdminAccessLevel_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminAccessLevel adaptee;

  PanelAdminAccessLevel_table_mouseAdapter(PanelAdminAccessLevel adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelAdminAccessLevel_btnAdminAccessLevelSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminAccessLevel adaptee;

  PanelAdminAccessLevel_btnAdminAccessLevelSearch_actionAdapter(PanelAdminAccessLevel adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminAccessLevelSearch_actionPerformed(e);
  }
}

class PanelAdminAccessLevel_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminAccessLevel adaptee;

  PanelAdminAccessLevel_table_keyAdapter(PanelAdminAccessLevel adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
