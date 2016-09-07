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
 * <p>Description: Admin -> Role -> Role Modify</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */

public class PanelAdminRoleModify extends JPanel {
  Utils ut = new Utils();
  Statement stmt = null;
  AdminBusObj adminBusObj = new AdminBusObj();
  int PrefixCodeLength = ut.PREFIX_CODE_LENGTH;
  //define for panel
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;
  DataAccessLayer DAL;

  JPanel pnlMain = new JPanel();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  boolean theFirstAdd=false;
  BJButton btnAdminRoleModify = new BJButton();
  BJButton btnAdminRoleCancel = new BJButton();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

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
  JTable table = new JTable(dm);
  JLabel jLabel1 = new JLabel();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  JTextField txtRoleID = new JTextField();

  /** Create Panel for Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		FrameMainSim frm, CardLayout crdLayout, JPanel parent
   * @return		no
   */
  public PanelAdminRoleModify(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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

    pnlMain.setBackground(new Color(55, 194, 255));
    pnlMain.setLayout(borderLayout2);
//    jButton1.addActionListener(new FrameDetailTrans_jButton1_actionAdapter(this));
    pnlCenter.setMinimumSize(new Dimension(10, 10));
    pnlCenter.setPreferredSize(new Dimension(660, 60));
    pnlCenter.setLayout(borderLayout4);

    pnlText.setMinimumSize(new Dimension(10,10));
    pnlText.setPreferredSize(new Dimension(660, 100));
    pnlText.setRequestFocusEnabled(true);
    pnlText.setLayout(new BorderLayout());

    pnlTable.setMinimumSize(new Dimension(10,10));
    pnlTable.setPreferredSize(new Dimension(660, 357));
    pnlTable.setLayout(borderLayout5);

    lblRoleDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRoleDesc.setPreferredSize(new Dimension(100, 21));
    lblRoleDesc.setText(lang.getString("RoleDescription")+":");
    txtRoleDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRoleDesc.setPreferredSize(new Dimension(240, 21));
    txtRoleDesc.addKeyListener(new PanelAdminRoleModify_txtRoleDesc_keyAdapter(this));
    jPanel6.setMinimumSize(new Dimension(38, 10));
    jPanel6.setPreferredSize(new Dimension(60, 10));
    jPanel6.setLayout(flowLayout3);
    jPanel2.setLayout(flowLayout1);
//    table.addMouseListener(new FrameDetailTrans_table_mouseAdapter(this));
    btnAdminRoleModify.addActionListener(new PanelAdminRoleModify_btnAdminRoleModify_actionAdapter(this));



    //for status
    pnlStatus.setLayout(gridLayout1);
    pnlHeader.setLayout(borderLayout3);

    pnlButtonContent.setLayout(new BoxLayout(pnlButtonContent, BoxLayout.PAGE_AXIS));

    pnlHeader.setPreferredSize(new Dimension(102, 45));

    //for button of header
    btnAdminRoleModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleModify.setToolTipText(lang.getString("Modify")+" (F1)");
    btnAdminRoleModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1<" +
    "/html>");
    btnAdminRoleCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleCancel.setToolTipText(lang.getString("Cancel")+" (F2)");
    btnAdminRoleCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2<" +
    "/html>");
    btnAdminRoleCancel.addActionListener(new PanelAdminRoleModify_btnAdminRoleCancel_actionAdapter(this));

    jPanel4.setLayout(flowLayout2);
    gridLayout1.setColumns(0);
    gridLayout1.setRows(1);

    jPanel2.setPreferredSize(new Dimension(150, 10));
    jPanel5.setPreferredSize(new Dimension(390, 10));
    lblInputItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(100, 21));
    jLabel1.setText(lang.getString("RoleID")+":");
    txtRoleID.setOpaque(true);
    txtRoleID.setPreferredSize(new Dimension(120, 21));
    txtRoleID.setToolTipText("");
    txtRoleID.setEditable(false);
    txtRoleID.setText("");
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    table.addKeyListener(new PanelAdminRoleModify_table_keyAdapter(this));
    this.add(pnlMain, BorderLayout.CENTER);
    pnlMain.add(pnlCenter, BorderLayout.CENTER);

    pnlCenter.add(pnlText, BorderLayout.NORTH);
    pnlText.add(jPanel2, BorderLayout.WEST);
    jPanel2.add(jLabel1, null);
    jPanel2.add(lblRoleDesc, null);
    pnlText.add(jPanel5, BorderLayout.EAST);
    pnlText.add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(txtRoleID, null);
    jPanel6.add(txtRoleDesc, null);

    //add all panel
    pnlMain.add(pnlStatus, BorderLayout.SOUTH);
    pnlMain.add(pnlHeader, BorderLayout.NORTH);
    pnlHeader.add(pnlButtonContent,  BorderLayout.CENTER);
    pnlButtonContent.add(jPanel4, null);
    //add button into panl header
    jPanel4.add(btnAdminRoleModify, null);
    jPanel4.add(btnAdminRoleCancel, null);


    pnlCenter.add(pnlTable, BorderLayout.CENTER);
    pnlTable.add(jScrollPane2,  BorderLayout.CENTER);
    jScrollPane2.getViewport().add(table, null);

   //define for table
    String[] columnNames = new String[]{lang.getString("RoleID"), lang.getString("RoleName")};
    dm.setDataVector(new Object[][]{},columnNames);
    table.getColumn(lang.getString("RoleID")).setPreferredWidth(120);
    table.getColumn(lang.getString("RoleName")).setPreferredWidth(300);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    table.setRowHeight(30);
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


  /** Cancel Button of Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnAdminRoleCancel_actionPerformed(ActionEvent e) {
    //show Dialog Search
    DialogAminRoleSearch dlgAdminRoleSearch = new DialogAminRoleSearch(frmMain,
        lang.getString("TT0096"), true, frmMain, frmMain.iSeasonBusObj);
    dlgAdminRoleSearch.setLocation(112, 85);
    dlgAdminRoleSearch.updateScreen();
    dlgAdminRoleSearch.setVisible(true);

  }
  private void returnDialogSearch(){
    //show Dialog Search
    DialogAminRoleSearch dlgAdminRoleSearch = new DialogAminRoleSearch(frmMain,
        lang.getString("TT0096"), true, frmMain, frmMain.iSeasonBusObj);
    dlgAdminRoleSearch.setLocation(112, 85);
    dlgAdminRoleSearch.updateScreen();
    dlgAdminRoleSearch.updateRowOnTable(txtRoleDesc.getText());
    dlgAdminRoleSearch.setVisible(true);
  }

  private String checkDesc() {
    if (this.txtRoleDesc.getText().toString().trim().length()==0) {
      ut.showMessage(frmMain,lang.getString("TT026"),lang.getString("MS1063_RoleNameNotNull"));
      this.txtRoleDesc.requestFocus(true);
      return null;
    }else {
      return this.txtRoleDesc.getText().trim();
    }
  }

  /** Add Button of Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnAdminRoleModify_actionPerformed(ActionEvent e) {

     String roleID = txtRoleID.getText();
    String roleName = txtRoleDesc.getText();

    if (roleName.trim().length()==0) {
      ut.showMessage(frmMain, lang.getString("TT026"), lang.getString("MS1063_RoleNameNotNull"));
      txtRoleDesc.requestFocus(true);
      return;
    }
    if(checkExist(roleID,roleName)){
      ut.showMessage(frmMain, lang.getString("TT026"),lang.getString("MS1062_RoleNameExisted"));
      txtRoleDesc.requestFocus(true);
      return;
    }

    String sqlStr = "update BTM_IM_ROLE set ROLE_NAME='" + roleName + "' where ROLE_ID = '" + roleID + "'";
//    System.out.println(sqlStr);
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);
      DAL.executeUpdate(sqlStr, stmt);
      stmt.close();
    }catch(Exception ex){};
//    str.installInTable(table, Color.lightGray, Color.white, null, null);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

    returnDialogSearch();
  }
  private boolean checkExist(String roleID ,String roleName){
     ResultSet rs = null;
     String sqlStr = "Select Role_Name From BTM_IM_ROLE Where Role_Name='" + roleName + "' and Role_ID <> '" + roleID + "'";
     try{
//       System.out.println(sqlStr);
       stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);

       rs = DAL.executeScrollQueries(sqlStr,stmt);
       if(rs.next()){
         stmt.close();
         return true;
       }
       stmt.close();
     }
     catch(Exception ex){}
     return false;
   }




  void txtRoleDesc_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,this.txtRoleDesc,Role.LEN_ROLE_NAME);
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
        btnAdminRoleModify.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnAdminRoleCancel.doClick();
      }
    }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnAdminRoleCancel.doClick();
              }

  }


}//end class



class PanelAdminRoleModify_btnAdminRoleCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRoleModify adaptee;

  PanelAdminRoleModify_btnAdminRoleCancel_actionAdapter(PanelAdminRoleModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleCancel_actionPerformed(e);
  }
}

class PanelAdminRoleModify_btnAdminRoleModify_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRoleModify adaptee;

  PanelAdminRoleModify_btnAdminRoleModify_actionAdapter(PanelAdminRoleModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleModify_actionPerformed(e);
  }
}

class PanelAdminRoleModify_txtRoleDesc_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminRoleModify adaptee;

  PanelAdminRoleModify_txtRoleDesc_keyAdapter(PanelAdminRoleModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRoleDesc_keyTyped(e);
  }
}

class PanelAdminRoleModify_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminRoleModify adaptee;

  PanelAdminRoleModify_table_keyAdapter(PanelAdminRoleModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

