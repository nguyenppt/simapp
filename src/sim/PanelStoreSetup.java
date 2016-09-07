package sim;

import java.util.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;

import btm.swing.*;
import common.*;
import java.awt.event.*;
import btm.attr.*;
import java.beans.*;
import java.io.*;
import java.sql.Statement;

/**
 * <p>Title: </p>
 * <p>Description:Main-> Merch Mgmt->Store Setup</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelStoreSetup extends JPanel {
  DataAccessLayer DAL;
  FrameMainSim frmMain;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
//  Vector vSql = new Vector();
  Vector vinsrtStore=new Vector(); //contain insert into BTM_IM_STORE
  Vector vinsrtPrHst=new Vector(); //contain insert into BTM_IM_PRICE_HIST
  Vector vinsrUCHst =new Vector(); //contain insert into BTM_IM_UNIT_COST_HIST
  Vector vinsrtLocSup=new Vector();//contain insert into BTM_IM_LOC_SUP
  Vector vModify = new Vector();
  int  vPosition = 0;
  boolean flagSetHotKeyForAdd = true;
  Statement stmt = null;
//  Statement stmt1 = null;
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  //
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  //
  //
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true){
    public Class getColumnClass(int col){
      switch(col){
        case 0: return Long.class;
        default: return Object.class;
      }
    }
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  BorderLayout borderLayout6 = new BorderLayout();
  BJPanel jPanel15 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJButton btnCancel = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnClear = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnModify = new BJButton();
  BJButton btnDone = new BJButton();
  //
  StripedTableCellRenderer stripedTable = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  BJButton btnDelete = new BJButton();
  DialogLocation dlgLocation ;
  int deleteRow = -1;
  int rowNum = 5;
  JLabel lblCity = new JLabel();
  JLabel lblAddress2 = new JLabel();
  JLabel lblAddress1 = new JLabel();
  JLabel lblShortName1 = new JLabel();
  JLabel lblName = new JLabel();
  JLabel lblType = new JLabel();
  JLabel lblStoreID = new JLabel();
  JTextField txtCity = new JTextField();
  JTextField txtAddress2 = new JTextField();
  JTextField txtAddress1 = new JTextField();
  JTextField txtShortName1 = new JTextField();
  JTextField txtName = new JTextField();
  JComboBox cboType = new JComboBox();
  JTextField txtStoreID = new JTextField();
  JLabel lblChildID = new JLabel();
  JLabel lblCurrCDE = new JLabel();
  JLabel lblEmail = new JLabel();
  JLabel lblFaxNo = new JLabel();
  JLabel lblPhoneNo = new JLabel();
  JLabel lblManagerName = new JLabel();
  JLabel lblCountry = new JLabel();
  JLabel lblCounty = new JLabel();
  JButton btnChildID = new JButton();
  JTextField txtChildID = new JTextField();
  JTextField txtChildName = new JTextField();
  BJComboBox cboCurrCDE = new BJComboBox();
  JTextField txtEmail = new JTextField();
  JTextField txtFaxNo = new JTextField();
  JTextField txtPhoneNo = new JTextField();
  JTextField txtManagerName = new JTextField();
  JTextField txtCountry = new JTextField();
  JTextField txtCounty = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JPanel jPanel9 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JPanel jPanel12 = new JPanel();
  JLabel jLabel4 = new JLabel();
  long storeID = 0;
//  long distributionID = 0;

  public PanelStoreSetup(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setPreferredSize(new Dimension(800,600));
    this.addMouseListener(new PanelStoreSetup_this_mouseAdapter(this));
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout6);
    jPanel2.setBorder(null);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setOpaque(true);
    jPanel2.setPreferredSize(new Dimension(430, 220));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(350, 220));
    jPanel3.setLayout(borderLayout3);
    jPanel4.setPreferredSize(new Dimension(120, 10));
    jPanel6.setPreferredSize(new Dimension(120, 10));
    jPanel8.setPreferredSize(new Dimension(60, 10));
    jPanel7.setPreferredSize(new Dimension(220, 10));
    jPanel5.setPreferredSize(new Dimension(250, 10));
    jPanel5.setLayout(null);
    jScrollPane1.setPreferredSize(new Dimension(800, 280));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setPreferredSize(new Dimension(120,37));
    jPanel15.setPreferredSize(new Dimension(700, 50));
    jPanel15.setLayout(flowLayout2);
    btnCancel.setToolTipText(lang.getString("Cancel") + "Cancel (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelStoreSetup_btnCancel_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSearch.setToolTipText(lang.getString("Search")+ " (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</h" +
    "tml>");

    btnSearch.addActionListener(new PanelStoreSetup_btnSearch_actionAdapter(this));
    btnSearch.setPreferredSize(new Dimension(120,37));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</h" +
     "tml>");

    btnAdd.addActionListener(new PanelStoreSetup_btnAdd_actionAdapter(this));
    btnAdd.setPreferredSize(new Dimension(120,37));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnModify.setToolTipText(lang.getString("Modify")+" (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</h" +
     "tml>");
    btnModify.addActionListener(new PanelStoreSetup_btnModify_actionAdapter(this));
    btnModify.setPreferredSize(new Dimension(120,37));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</h" +
    "tml>");

    btnDone.addActionListener(new PanelStoreSetup_btnDone_actionAdapter(this));
    btnDone.setPreferredSize(new Dimension(120,37));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</h" +
   "tml>");

    btnDelete.addActionListener(new PanelStoreSetup_btnDelete_actionAdapter(this));
    btnDelete.setPreferredSize(new Dimension(120,37));

    lblCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCity.setPreferredSize(new Dimension(110, 20));
    lblCity.setText(lang.getString("City")+":");
    lblAddress2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAddress2.setPreferredSize(new Dimension(110, 20));
    lblAddress2.setText(lang.getString("Address2")+":");
    lblAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAddress1.setPreferredSize(new Dimension(110, 20));
    lblAddress1.setText(lang.getString("Address1")+":");
    lblShortName1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblShortName1.setPreferredSize(new Dimension(110, 20));
    lblShortName1.setText(lang.getString("ShortName1")+":");
    lblName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblName.setPreferredSize(new Dimension(110, 20));
    lblName.setText(lang.getString("Name")+":");
    lblType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblType.setPreferredSize(new Dimension(110, 20));
    lblType.setText(lang.getString("Type")+":");
    lblStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStoreID.setPreferredSize(new Dimension(110, 20));
    lblStoreID.setText(lang.getString("StoreID")+":");
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCity.setPreferredSize(new Dimension(200, 20));
    txtCity.setBounds(new Rectangle(25, 156, 200, 20));
    txtCity.addFocusListener(new PanelStoreSetup_txtCity_focusAdapter(this));
    txtCity.addKeyListener(new PanelStoreSetup_txtCity_keyAdapter(this));
    txtAddress2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress2.setPreferredSize(new Dimension(200, 20));
    txtAddress2.setText("");
    txtAddress2.setBounds(new Rectangle(25, 131, 200, 20));
    txtAddress2.addFocusListener(new PanelStoreSetup_txtAddress2_focusAdapter(this));
    txtAddress2.addKeyListener(new PanelStoreSetup_txtAddress2_keyAdapter(this));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress1.setPreferredSize(new Dimension(200, 20));
    txtAddress1.setText("");
    txtAddress1.setBounds(new Rectangle(25, 106, 200, 20));
    txtAddress1.addFocusListener(new PanelStoreSetup_txtAddress1_focusAdapter(this));
    txtAddress1.addKeyListener(new PanelStoreSetup_txtAddress1_keyAdapter(this));
    txtShortName1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtShortName1.setPreferredSize(new Dimension(200, 20));
    txtShortName1.setText("");
    txtShortName1.setBounds(new Rectangle(25, 80, 200, 20));
    txtShortName1.addFocusListener(new PanelStoreSetup_txtShortName1_focusAdapter(this));
    txtShortName1.addKeyListener(new PanelStoreSetup_txtShortName1_keyAdapter(this));
    txtName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtName.setPreferredSize(new Dimension(200, 20));
    txtName.setText("");
    txtName.setBounds(new Rectangle(25, 55, 200, 20));
    txtName.addFocusListener(new PanelStoreSetup_txtName_focusAdapter(this));
    txtName.addKeyListener(new PanelStoreSetup_txtName_keyAdapter(this));
    txtStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtStoreID.setPreferredSize(new Dimension(200, 20));
    txtStoreID.setText("");
    txtStoreID.setBounds(new Rectangle(25, 5, 200, 20));
    txtStoreID.addFocusListener(new PanelStoreSetup_txtStoreID_focusAdapter(this));
    txtStoreID.addKeyListener(new PanelStoreSetup_txtStoreID_keyAdapter(this));
    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboType.setNextFocusableComponent(null);
    cboType.setPreferredSize(new Dimension(200, 20));
    cboType.setBounds(new Rectangle(25, 30, 200, 20));
    cboType.addKeyListener(new PanelStoreSetup_cboType_keyAdapter(this));
    cboType.addActionListener(new PanelStoreSetup_cboType_actionAdapter(this));
    cboType.addPropertyChangeListener(new PanelStoreSetup_cboType_propertyChangeAdapter(this));
    lblChildID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblChildID.setPreferredSize(new Dimension(110, 20));
    lblChildID.setText(lang.getString("ParentID") + ":");
    lblCurrCDE.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCurrCDE.setPreferredSize(new Dimension(110, 20));
    lblCurrCDE.setText(lang.getString("Currency") + ":");
    lblEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmail.setPreferredSize(new Dimension(110, 20));
    lblEmail.setText(lang.getString("Email") + ":");
    lblFaxNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFaxNo.setPreferredSize(new Dimension(110, 20));
    lblFaxNo.setText(lang.getString("Fax") + ":");
    lblPhoneNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPhoneNo.setPreferredSize(new Dimension(110, 20));
    lblPhoneNo.setText(lang.getString("Phone") + ":");
    lblManagerName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblManagerName.setPreferredSize(new Dimension(110, 20));
    lblManagerName.setText(lang.getString("ManagerName") + ":");
    lblCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCountry.setPreferredSize(new Dimension(110, 20));
    lblCountry.setText(lang.getString("Country") + ":");
    lblCounty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCounty.setPreferredSize(new Dimension(110, 20));
    lblCounty.setText(lang.getString("County") + ":");
    btnChildID.setOpaque(true);
    btnChildID.setPreferredSize(new Dimension(40, 20));
    btnChildID.setText("...");
    btnChildID.addActionListener(new PanelStoreSetup_btnChildID_actionAdapter(this));
    txtChildID.setPreferredSize(new Dimension(57, 21));
    txtChildID.setEditable(false);
    txtChildID.setText("");
    txtChildName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtChildName.setPreferredSize(new Dimension(155, 20));
    txtChildName.setEditable(false);
    txtChildName.setEnabled(false);
    txtChildName.setText("");
    txtEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmail.setPreferredSize(new Dimension(200, 20));
    txtEmail.setText("");
    txtEmail.addFocusListener(new PanelStoreSetup_txtEmail_focusAdapter(this));
    txtEmail.addKeyListener(new PanelStoreSetup_txtEmail_keyAdapter(this));
    txtFaxNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtFaxNo.setPreferredSize(new Dimension(200, 20));
    txtFaxNo.setText("");
    txtFaxNo.addFocusListener(new PanelStoreSetup_txtFaxNo_focusAdapter(this));
    txtFaxNo.addKeyListener(new PanelStoreSetup_txtFaxNo_keyAdapter(this));
    txtPhoneNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPhoneNo.setPreferredSize(new Dimension(200, 20));
    txtPhoneNo.setText("");
    txtPhoneNo.addFocusListener(new PanelStoreSetup_txtPhoneNo_focusAdapter(this));
    txtPhoneNo.addActionListener(new PanelStoreSetup_txtPhoneNo_actionAdapter(this));
    txtPhoneNo.addKeyListener(new PanelStoreSetup_txtPhoneNo_keyAdapter(this));
    txtManagerName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtManagerName.setPreferredSize(new Dimension(200, 20));
    txtManagerName.setText("");
    txtManagerName.addFocusListener(new PanelStoreSetup_txtManagerName_focusAdapter(this));
    txtManagerName.addKeyListener(new PanelStoreSetup_txtManagerName_keyAdapter(this));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCountry.setPreferredSize(new Dimension(200, 20));
    txtCountry.setText("");
    txtCountry.addFocusListener(new PanelStoreSetup_txtCountry_focusAdapter(this));
    txtCountry.addKeyListener(new PanelStoreSetup_txtCountry_keyAdapter(this));
    txtCounty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCounty.setPreferredSize(new Dimension(200, 20));
    txtCounty.setText("");
    txtCounty.addFocusListener(new PanelStoreSetup_txtCounty_focusAdapter(this));
    txtCounty.addKeyListener(new PanelStoreSetup_txtCounty_keyAdapter(this));
    cboCurrCDE.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboCurrCDE.setPreferredSize(new Dimension(200, 20));
    cboCurrCDE.addKeyListener(new PanelStoreSetup_cboCurrCDE_keyAdapter(this));
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnClear.addActionListener(new PanelStoreSetup_btnClear_actionAdapter(this));
//    btnClear.setText("Clear (F5)");
    btnClear.setPreferredSize(new Dimension(120,37));

    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    jLabel1.setBackground(Color.red);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setForeground(Color.red);
    jLabel1.setText("(*)");
    jLabel1.setBounds(new Rectangle(228, 5, 16, 20));
    jLabel2.setBounds(new Rectangle(228, 55, 16, 20));
    jLabel2.setText("(*)");
    jLabel2.setForeground(Color.red);
    jLabel2.setPreferredSize(new Dimension(13, 16));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setBackground(Color.red);
    jLabel3.setBackground(Color.red);
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setForeground(Color.red);
    jLabel3.setText("(*)");
    jLabel3.setBounds(new Rectangle(228, 104, 16, 20));
    jPanel9.setPreferredSize(new Dimension(30, 10));
    jPanel12.setPreferredSize(new Dimension(30, 20));
    jPanel11.setPreferredSize(new Dimension(30, 15));
    jPanel10.setPreferredSize(new Dimension(30, 20));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setForeground(Color.red);
    jLabel4.setPreferredSize(new Dimension(25, 20));
    jLabel4.setText("(*)");
    table.addKeyListener(new PanelStoreSetup_table_keyAdapter(this));
    this.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel15, BorderLayout.CENTER);
    jPanel15.add(btnDone, null);
    jPanel15.add(btnAdd, null);
    jPanel15.add(btnSearch, null);
    jPanel15.add(btnModify, null);

    jPanel15.add(btnClear, null);
    jPanel15.add(btnDelete, null);

    jPanel15.add(btnCancel, null);
    this.add(jPanel2, BorderLayout.WEST);
    jPanel2.add(jPanel4, BorderLayout.WEST);
    jPanel4.add(lblStoreID, null);
    jPanel4.add(lblType, null);
    jPanel4.add(lblName, null);
    jPanel4.add(lblShortName1, null);
    jPanel4.add(lblAddress1, null);
    jPanel4.add(lblAddress2, null);
    jPanel4.add(lblCity, null);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel2.add(jPanel8,BorderLayout.EAST);
    this.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jPanel6, BorderLayout.WEST);
    jPanel6.add(lblCounty, null);
    jPanel6.add(lblCountry, null);
    jPanel6.add(lblManagerName, null);
    jPanel6.add(lblPhoneNo, null);
    jPanel6.add(lblFaxNo, null);
    jPanel6.add(lblEmail, null);
    jPanel6.add(lblCurrCDE, null);
    jPanel6.add(lblChildID, null);
    jPanel3.add(jPanel7, BorderLayout.CENTER);
    this.add(jScrollPane1, BorderLayout.SOUTH);
    table.addMouseListener(new PanelStoreSetup_table_mouseAdapter(this));
    jScrollPane1.getViewport().add(table, null);
    jPanel5.add(txtStoreID, null);
    jPanel5.add(cboType, null);
    jPanel5.add(txtName, null);
    jPanel5.add(txtShortName1, null);
    jPanel5.add(jLabel2, null);
    jPanel5.add(jLabel1, null);
    jPanel5.add(txtCity, null);
    jPanel5.add(txtAddress2, null);
    jPanel5.add(txtAddress1, null);
    jPanel5.add(jLabel3, null);
    jPanel7.add(txtCounty, null);
    jPanel7.add(txtCountry, null);
    jPanel7.add(txtManagerName, null);
    jPanel7.add(txtPhoneNo, null);
    jPanel7.add(txtFaxNo, null);
    jPanel7.add(txtEmail, null);
    jPanel7.add(cboCurrCDE, null);
    jPanel7.add(txtChildName, null);
    jPanel7.add(txtChildID, null);
    jPanel7.add(btnChildID, null);
    jPanel3.add(jPanel9,  BorderLayout.EAST);
    jPanel9.add(jPanel12, null);
    jPanel9.add(jPanel11, null);
    jPanel9.add(jPanel10, null);
    jPanel10.add(jLabel4, null);
    String[] columnNames = new String[]{
         lang.getString("StoreID"),
         lang.getString("Name"),
         lang.getString("ManagerName"),
         lang.getString("Phone"),
         lang.getString("Fax")
    };
    dm.setDataVector(new Object[][]{},columnNames);
    table.setColumnWidth(0,100);
    table.setColumnWidth(1,200);
    table.setColumnWidth(2,200);
    table.setColumnWidth(3,150);
    table.setColumnWidth(4,150);

    txtChildID.setVisible(false);
    cboType.addItem("Store");
    cboType.addItem("Distribution");
    txtStoreID.setText("" + storeID);
    txtStoreID.setEditable(false);
    btnModify.setVisible(false);
    resetButtonLabel();
  }
  void initScreen(){

    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    cboCurrCDE.setData(frmMain.storeBusObj.getCurrency(DAL,stmt));
    try {
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    getStoreID();
  }


  //apply employee's permission for this screen
  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_STORE_SETUP);
    btnAdd.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnModify.setEnabled(er.getModify());
    btnSearch.setEnabled(er.getSearch());


  }

  //Void get Store ID
  void getStoreID(){

    ResultSet rs = null;
    try {
     stmt = DAL.getConnection().createStatement();
//     stmt1 = DAL.getConnection().createStatement();
   }
   catch (Exception ex) {
     ex.printStackTrace();
   }

    try{
      String sql = "";

      sql = "select store_id from btm_im_store order by store_id desc";
//      System.out.println(sql);
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        this.storeID = rs.getLong("store_id");
        this.storeID = this.storeID + 1;
      }else {
        this.storeID = Integer.parseInt(DAL.getHostID() + "100");
      }
      rs.close();
//      sql = "Select store_id from btm_im_store where store_type='D' order by store_id desc";
//      System.out.println(sql);
//      rs = DAL.executeQueries(sql,stmt);
//      if (rs.next()){
//        this.distributionID = rs.getLong("store_id");
//        this.distributionID = this.distributionID + 1;
//      }else {
//        this.distributionID = Integer.parseInt(DAL.getHostID() + "200");
//      }
      txtStoreID.setText("" + storeID);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    try {
       stmt.close();
//       stmt1.close();
     }
     catch (Exception ex) {
       ex.printStackTrace();
     }


  }
  void btnChildID_actionPerformed(ActionEvent e) {
    if (txtChildID.getText().equalsIgnoreCase("")){
      dlgLocation = new DialogLocation(frmMain, "", true, frmMain);
      dlgLocation.flagDialog = Constant.STORE_SETUP;
      dlgLocation.setVisible(true);
      if (dlgLocation.isOKButton()) {
        txtChildName.setText(dlgLocation.getChildNameLocation());
        txtChildID.setText(dlgLocation.getChildIdLocation());
        dlgLocation.setVisible(false);
      }
    }else{
      btnAdd.doClick();
    }
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    String sql="";
    if (txtStoreID.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1039_StoreIDNotNull"));
      txtStoreID.requestFocus(true);
      return;
    }
    long storeID1 = Long.parseLong(txtStoreID.getText());
    String type="";
    if (cboType.getSelectedItem().toString().equalsIgnoreCase("Distribution")){
      type = "D"; //D: Distribution
    }else{
      type = "S"; //S: Store
    }

    String name = txtName.getText().trim();
    String shortName1 = txtShortName1.getText().trim();
    String address1 = txtAddress1.getText().trim();
    String address2 = txtAddress2.getText().trim();
    String city = txtCity.getText().trim();
    String county = txtCounty.getText().trim();
    String country = txtCountry.getText().trim();
    String managerName = txtManagerName.getText().trim();
    String phoneNo = txtPhoneNo.getText().trim();
    String faxNo = txtFaxNo.getText().trim();
    String email = txtEmail.getText().trim();
    String currID = cboCurrCDE.getSelectedData();
    String childID = txtChildID.getText().trim();
    if(txtName.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1176_StoreNameNotNull"));
      txtName.requestFocus(true);
      return;
    }
    if (txtAddress1.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1041_AddressNotNull"));
      txtAddress1.requestFocus(true);
      return;
    }
    if (managerName.equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS307_ManagerNameNotNull"));
      txtManagerName.requestFocus(true);
      return;
    }
//    if (!ut.checkPhoneNumber(phoneNo)){
//      ut.showMessage(frmMain,"Warning","Wrong Phone number");
//      txtPhoneNo.requestFocus();
//      return;
//    }
//    if (!ut.checkPhoneNumber(faxNo)){
//      ut.showMessage(frmMain,"Warning","Wrong Fax number");
//      txtFaxNo.requestFocus(true);
//      return;
//    }
    if (childID.equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS308_ChooseParentID"));
      btnChildID.requestFocus(true);
      return;
    }
//    if (checkExisting(storeID)){
//      ut.showMessage(frmMain,"Warning", "This store id is existing! Please choose another store id");
//      txtStoreID.requestFocus(true);
//      return;
//    }
    sql = "Insert into BTM_IM_STORE(store_id, store_type, name, short_name1, " +
        " address_1, address_2, county, city, country, manager_name, phone_no, " +
        " fax_no, email, curr_cde, created_date, closed_date, child_id) " +
        " values (" + storeID1 + ",'" + type + "','" + name + "','" +
        shortName1 +
        "','" + address1 + "','" + address2 + "','" + county + "','" + city +
        "','" + country + "','" + managerName + "','" + phoneNo + "','" + faxNo +
        "','" + email + "','" + currID +
        "',to_date('" + ut.getSystemDate(1) + "','DD/MM/YYYY')," +
        "to_date('7/7/7777','DD/MM/YYYY'),'" + childID + "')";

    vinsrtStore.addElement(sql);

//    System.out.println(sql);

    //Insert all item into BTM_IM_PRICE_HIST
    //PRICE_EFF_DATE can not today b/c conflic when add new store today

    sql = " insert into BTM_IM_PRICE_HIST "
        + " select ITEM_ID," + storeID1 + " STORE_ID,TRANS_TYPE,PRICE_EFF_DATE,PRICE_END_DATE,NEW_PRICE,      "
        + " OLD_PRICE,STATUS,PROMO_ID,DESCRIPTION,REASON,STORE_TYPE,UNIT_COST,SELLING_UNIT_RETAIL,"
        + " UNIT_RETAIL,SELLING_UOM,LAST_UPD_DATE,0 BATCH_SEQ ,ATTR1_NAME,ATTR2_NAME, AVE_COST"
        + " from BTM_IM_PRICE_HIST where status='1' and STORE_ID = (select store_id from BTM_IM_PRICE_HIST  where rownum<=1)";

   //setup price for new store
    vinsrtPrHst.addElement(sql);
//    System.out.println(sql);

    //Insert all item into BTM_IM_UNIT_COST_HIST

    sql = " insert into BTM_IM_UNIT_COST_HIST "
        + " select ITEM_ID," + storeID1 + " STORE_ID,TRANS_TYPE,UNIT_COST_EFF_DATE,UNIT_COST_END_DATE,NEW_UNIT_COST,      "
        + " OLD_UNIT_COST,STATUS,PROMO_ID,DESCRIPTION,REASON,STORE_TYPE,PRICE,SELLING_UNIT_RETAIL,"
        + " UNIT_RETAIL,SELLING_UOM,LAST_UPD_DATE,0 BATCH_SEQ ,ATTR1_NAME,ATTR2_NAME, AVE_COST"
        + " from BTM_IM_UNIT_COST_HIST where status='1' and STORE_ID = (select store_id from BTM_IM_PRICE_HIST  where rownum<=1)";

   //setup price for new store
    vinsrUCHst.addElement(sql);
//    System.out.println(sql);

    //Insert all item into BTM_IM_ITEM_LOC_SUPPLIER
    sql = " insert into BTM_IM_ITEM_LOC_SUPPLIER "
        + " select ITEM_ID,SUPP_ID," + storeID1 + " STORE_ID,CASE_SIZE,UNIT_COST,PRICE_CHANGE, AVG_COST, VAT "
        + " from BTM_IM_ITEM_LOC_SUPPLIER where STORE_ID = (select store_id from BTM_IM_ITEM_LOC_SUPPLIER  where rownum<=1)";

   //setup price for new store
    vinsrtLocSup.addElement(sql);
//    System.out.println(sql);


    Object[] rowData = new Object[] {
        new Long(storeID1), name, managerName, phoneNo, faxNo
    };
    dm.addRow(rowData);
    //set focus for last row
     if(table.getRowCount()>0){
       table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
     }

     //show current row
     Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
     jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

    Vector vData = new Vector();
    vData.addElement(new Long(storeID1));
    vData.addElement(type);
    vData.addElement(name);
    vData.addElement(shortName1);
    vData.addElement(address1);
    vData.addElement(address2);
    vData.addElement(city);
    vData.addElement(county);
    vData.addElement(country);
    vData.addElement(managerName);
    vData.addElement(phoneNo);
    vData.addElement(faxNo);
    vData.addElement(email);
    vData.addElement(currID);
    vData.addElement(txtChildName.getText());
    vData.addElement(childID);
    vModify.addElement(vData);
//    if (type.equalsIgnoreCase("S")){
      this.storeID = storeID1 + 1;
//    }else if(type.equalsIgnoreCase("D")){
//      this.distributionID = storeID1 + 1;
//    }
//    stripedTable.installInTable(table, Color.lightGray, Color.white,Color.white, Color.black);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    stripedTable.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

    resetData();
    txtStoreID.setText(String.valueOf(Integer.parseInt(table.getValueAt(table.getRowCount()-1,0).toString())+1));
  }
  //Check existing
  boolean checkExisting(long storeID){
    try{
      stmt = DAL.getConnection().createStatement();
      ResultSet rs = null;
//      System.out.println("Select * from BTM_IM_STORE where store_id = " + storeID);
      rs = DAL.executeQueries("Select * from BTM_IM_STORE where store_id = " + storeID,stmt);
      if (rs.next()){
        stmt.close();
        return true;
      }
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    try {
       stmt.close();

     }
     catch (Exception ex) {
       ex.printStackTrace();
     }

    return false;
  }
  //reset button label
  void resetButtonLabel(){
   flagSetHotKeyForAdd = true;
  }
  //reset data
  void resetData(){
//    txtStoreID.setText("");
    cboType.setSelectedIndex(0);
    txtName.setText("");
    txtShortName1.setText("");
    txtAddress1.setText("");
    txtAddress2.setText("");
    txtCity.setText("");
    txtCounty.setText("");
    txtCountry.setText("");
    txtManagerName.setText("");
    txtPhoneNo.setText("");
    txtFaxNo.setText("");
    txtEmail.setText("");
    cboCurrCDE.setSelectedIndex(0);
    txtChildID.setText("");
    txtChildName.setText("");
//    setButton(true,false);

  }
  //set button
  void setButton(boolean flag, boolean flag1){
    btnAdd.setVisible(flag);
    btnDelete.setVisible(flag);
    btnModify.setVisible(flag1);
  }
  void btnDone_actionPerformed(ActionEvent e) {
    if (!txtName.getText().equals("") && table.getRowCount()==0) {
            int i = ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS302_HelpSave"),
                               DialogMessage.QUESTION_MESSAGE,
                               DialogMessage.YES_NO_OPTION);
            if(i==DialogMessage.YES_VALUE){
              return;
            }
    }
//   for(int i=0;i<table.getRowCount();i++){
//     vSql.addElement(vinsrtStore.elementAt(i));
//     vSql.addElement(vinsrtPrHst.elementAt(i));
//     vSql.addElement(vinsrtLocSup.elementAt(i));
//   }
    try {
      stmt = DAL.getConnection().createStatement();
      if (!vinsrtStore.isEmpty() && !vinsrtPrHst.isEmpty() && !vinsrUCHst.isEmpty() && !vinsrtLocSup.isEmpty()) {
        DAL.setBeginTrans(DAL.getConnection());
        DAL.executeBatchQuery(vinsrtStore, stmt);
        DAL.executeBatchQuery(vinsrtPrHst, stmt);
        DAL.executeBatchQuery(vinsrUCHst, stmt);
        DAL.executeBatchQuery(vinsrtLocSup, stmt);
        DAL.setEndTrans(DAL.getConnection());
    }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    while (dm.getRowCount() > 0) {
      String storeID = table.getValueAt(0, 0).toString();
      File file = new File("./Batch/Export/" + storeID + "/");
      if (!file.isDirectory()) {
        file.mkdir();
      }
      file = null;
      File file1 = new File("./Batch/Backup/Export/" + storeID + "/");
      if (!file1.isDirectory()) {
        file1.mkdir();
      }
      file1 = null;
      dm.removeRow(0);
    }
//    vSql.clear();
    vinsrtStore.clear();
    vinsrtPrHst.clear();
    vinsrUCHst.clear();
    vinsrtLocSup.clear();
    vModify.clear();
    flagSetHotKeyForAdd = true;
    resetData();
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showMerchanSystemButton();
    try {
      stmt.close();

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }


  void btnDelete_actionPerformed(ActionEvent e) {
    if (table.getSelectedRow() == -1){
      return;
    }
    if (vinsrtStore.isEmpty()){
      return;
    }
    if (table.getRowCount()==1){
      dm.removeRow(0);
      vinsrtStore.removeAllElements();
      vinsrtPrHst.removeAllElements();
      vinsrUCHst.removeAllElements();
      vinsrtLocSup.removeAllElements();
      vModify.removeAllElements();
      flagSetHotKeyForAdd = true;
      return;
    }
    int[] i = table.getSelectedRows();
    for (int j=0; j<i.length; j++){
      vinsrtStore.removeElementAt(i[0]);
      vinsrtPrHst.removeElementAt(i[0]);
      vinsrUCHst.removeElementAt(i[0]);
      vinsrtLocSup.removeElementAt(i[0]);
      vModify.removeElementAt(i[0]);
      dm.removeRow(i[0]);
    }
    flagSetHotKeyForAdd = true;
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    while (dm.getRowCount()>0){
      dm.removeRow(0);
    }
//    vSql.clear();
    vinsrtStore.clear();
    vinsrtPrHst.clear();
    vinsrUCHst.clear();
    vinsrtLocSup.clear();
    vModify.clear();
    resetData();
    flagSetHotKeyForAdd = true;
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showMerchanSystemButton();
    frmMain.setTitle(lang.getString("TT024"));

  }

  void btnSearch_actionPerformed(ActionEvent e) {
//    frmMain.pnlStoreSearch.setParentScreen(Constant.SCRS_STORE_SETUP);
//    frmMain.pnlStoreSearch.resetData();
//    frmMain.showScreen(Constant.SCRS_STORE_SEARCH);
    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,lang.getString("TT024"),true,frmMain,frmMain.storeBusObj);
    dlgStoreSearch.txtStoreID.requestFocus(true);
    dlgStoreSearch.setVisible(true);
    if (dlgStoreSearch.done){
      String storeID = dlgStoreSearch.getData();
      frmMain.showStoreModify(Long.parseLong(storeID));
      frmMain.showScreen(Constant.SCRS_STORE_MODIFY);
      frmMain.pnlStoreModify.txtName.requestFocus(true);
    }
  }

    void txtStoreID_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtStoreID,Store.LEN_STORE_ID);
      ut.checkNumber(e);
    }


    void txtShortName1_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtShortName1,Store.LEN_STORE_SHORT_NAME1);
      navigationComp(e,txtAddress1);
    }

    void txtAddress1_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtAddress1,Store.LEN_STORE_ADDRESS_1);
      navigationComp(e,txtAddress2);
    }

    void txtAddress2_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtAddress2,Store.LEN_STORE_ADDRESS_2);
      navigationComp(e,txtCity);
    }

    void txtCity_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtCity,Store.LEN_STORE_CITY);
      navigationComp(e,txtCounty);
    }

    void txtCounty_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtCounty,Store.LEN_STORE_COUNTY);
      navigationComp(e,txtCountry);
    }

    void txtCountry_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtCountry,Store.LEN_STORE_COUNTRY);
      navigationComp(e,txtManagerName);
    }

    void txtManagerName_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtManagerName,Store.LEN_STORE_MANAGER_NAME);
      navigationComp(e,txtPhoneNo);
    }

    void txtPhoneNo_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtPhoneNo,Store.LEN_STORE_PHONE_NO);
      navigationComp(e,txtFaxNo);
      ut.checkPhone(e);
    }

    void txtFaxNo_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtFaxNo,Store.LEN_STORE_FAX_NO);
      ut.checkPhone(e);
      navigationComp(e,txtEmail);
    }

    void txtEmail_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtEmail,Store.LEN_STORE_EMAIL);
      navigationComp(e,cboCurrCDE);
    }

    void txtChildID_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtChildID,Store.LEN_STORE_CHILD_ID);
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
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            if (flagSetHotKeyForAdd == true) {
              btnAdd.doClick();
            }
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
              btnSearch.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F4) {
            if (flagSetHotKeyForAdd == false) {
              btnModify.doClick();
            }
          }
          else if (identifier.intValue() == KeyEvent.VK_F5) {

          }
          else if (identifier.intValue() == KeyEvent.VK_F7) {
              btnClear.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F8) {
            if (flagSetHotKeyForAdd == true) {
              btnDelete.doClick();
            }
          }

          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
              btnCancel.doClick();
          }

        }
      }

//  void table_mousePressed(MouseEvent e) {
//    btnDone.requestFocus();
//  }

  void this_mousePressed(MouseEvent e) {

  }

  void txtName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtName,Store.LEN_STORE_NAME);
    navigationComp(e,txtShortName1);
  }

  void btnClear_actionPerformed(ActionEvent e) {
    resetData();
    flagSetHotKeyForAdd = false;
    }

  void txtStoreID_focusLost(FocusEvent e) {

//    long storeID = 0;
//    if (!txtStoreID.getText().equals("")){
//      if (ut.isLongIntString(txtStoreID.getText())){
//        storeID = Long.parseLong(txtStoreID.getText());
//        if (!vSql.isEmpty()){
//          for (int i=0; i< dm.getRowCount(); i++){
//            String storeIDOld = "" + table.getValueAt(i,0);
//            if (storeID == Long.parseLong(storeIDOld)){
//              ut.showMessage(frmMain,"Warning", "This Store ID has been existed! Please choose another Store ID");
//              txtStoreID.requestFocus(true);
//              return;
//            }
//          }
//        }
//      }else{
//        ut.showMessage(frmMain,"Warning","Store id must be numeric");
//        txtStoreID.requestFocus(true);
//        return;
//      }
//    }else{
//      return;
//    }
//    if (checkExisting(storeID)){
//      ut.showMessage(frmMain,"Warning", "This store id is existing! Please choose another store id");
//      txtStoreID.requestFocus(true);
//      return;
//    }
//    cboType.requestFocus(true);
  }

  void cboType_propertyChange(PropertyChangeEvent e) {
//    if (cboType.getSelectedIndex() == 0){
//      txtStoreID.setText("" + storeID);
//    }else if(cboType.getSelectedIndex() == 1){
//      txtStoreID.setText("" + distributionID);
//    }
  }

  void cboType_actionPerformed(ActionEvent e) {
//    if (cboType.getSelectedIndex() == 0){
//      txtStoreID.setText("" + storeID);
//    }else if(cboType.getSelectedIndex() == 1){
//      txtStoreID.setText("" + distributionID);
//    }

  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2 ){
      Vector vData = null;
      this.vPosition = table.getSelectedRow();
      vData = (Vector)vModify.elementAt(this.vPosition);
//      System.out.println(vData.elementAt(0).toString());
      if (vData.elementAt(1).toString().equalsIgnoreCase("S")){
        cboType.setSelectedIndex(0);
      }else{
        cboType.setSelectedIndex(1);
      }
      txtName.setText(vData.elementAt(2).toString());
      txtShortName1.setText(vData.elementAt(3).toString());
      txtAddress1.setText(vData.elementAt(4).toString());
      txtAddress2.setText(vData.elementAt(5).toString());
      txtCity.setText(vData.elementAt(6).toString());
      txtCounty.setText(vData.elementAt(7).toString());
      txtCountry.setText(vData.elementAt(8).toString());
      txtManagerName.setText(vData.elementAt(9).toString());
      txtPhoneNo.setText(vData.elementAt(10).toString());
      txtFaxNo.setText(vData.elementAt(11).toString());
      txtEmail.setText(vData.elementAt(12).toString());
      cboCurrCDE.setSelectedItem(vData.elementAt(13).toString());
      txtChildName.setText(vData.elementAt(14).toString());
      txtChildID.setText(vData.elementAt(15).toString());
      setButton(false,true);
      flagSetHotKeyForAdd = false;
//      System.out.println("flarhotkey...." + flagSetHotKeyForAdd);
//      btnSearch.setText("<html><center><b>Search</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
//      btnClear.setText("<html><center><b>Clear</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
//      btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</h" +
//   "tml>");
      txtName.requestFocus(true);
      txtStoreID.setText(vData.elementAt(0).toString());
//      System.out.println("1: " + txtStoreID.getText());
       }
  }

  void btnModify_actionPerformed(ActionEvent e) {
    long storeID1 = Long.parseLong(txtStoreID.getText());
    String type="";
    if (cboType.getSelectedItem().toString().equalsIgnoreCase("Distribution")){
      type = "D"; //D: Distribution
    }else{
      type = "S"; //S: Store
    }

    String name = txtName.getText().trim();
    String shortName1 = txtShortName1.getText().trim();
    String address1 = txtAddress1.getText().trim();
    String address2 = txtAddress2.getText().trim();
    String city = txtCity.getText().trim();
    String county = txtCounty.getText().trim();
    String country = txtCountry.getText().trim();
    String managerName = txtManagerName.getText().trim();
    String phoneNo = txtPhoneNo.getText().trim();
    String faxNo = txtFaxNo.getText().trim();
    String email = txtEmail.getText().trim();
    String currID = cboCurrCDE.getSelectedData();
    String childID = txtChildID.getText().trim();
    if(txtName.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS305_StoreNameNotNull"));
      txtName.requestFocus(true);
      return;
    }
    if (txtAddress1.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS306_Address1NotNull"));
      txtAddress1.requestFocus(true);
      return;
    }
    if (managerName.equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS307_ManagerNameNotNull"));
      txtManagerName.requestFocus(true);
      return;
    }
//    if (!ut.checkPhoneNumber(phoneNo)){
//      ut.showMessage(frmMain,"Warning","Wrong Phone number");
//      txtPhoneNo.requestFocus();
//      return;
//    }
//    if (!ut.checkPhoneNumber(faxNo)){
//      ut.showMessage(frmMain,"Warning","Wrong Fax number");
//      txtFaxNo.requestFocus(true);
//      return;
//    }
    if (childID.equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS308_ChooseParentID"));
      btnChildID.requestFocus(true);
      return;
    }
//    if (checkExisting(storeID)){
//      ut.showMessage(frmMain,"Warning", "This store id is existing! Please choose another store id");
//      txtStoreID.requestFocus(true);
//      return;
//    }
//    System.out.println("Insert into BTM_IM_STORE(store_id, store_type, name, short_name1, " +
//        " address_1, address_2, county, city, country, manager_name, phone_no, " +
//        " fax_no, email, curr_cde, created_date, closed_date, child_id) " +
//        " values (" + storeID1 + ",'" + type + "','" + name + "','" + shortName1 +
//        "','" + address1 + "','" + address2 + "','" + county + "','" + city +
//        "','" + country + "','" + managerName + "','" + phoneNo + "','" + faxNo +
//        "','" + email + "','" + currID +
//        "',to_date('" + ut.getSystemDate(1) + "','DD/MM/YYYY')," +
//        "to_date('7/7/7777','DD/MM/YYYY'),'" + childID + "')");
    vinsrtStore.setElementAt("Insert into BTM_IM_STORE(store_id, store_type, name, short_name1, " +
        " address_1, address_2, county, city, country, manager_name, phone_no, " +
        " fax_no, email, curr_cde, created_date, closed_date, child_id) " +
        " values (" + storeID1 + ",'" + type + "','" + name + "','" + shortName1 +
        "','" + address1 + "','" + address2 + "','" + county + "','" + city +
        "','" + country + "','" + managerName + "','" + phoneNo + "','" + faxNo +
        "','" + email + "','" + currID +
        "',to_date('" + ut.getSystemDate(1) + "','DD/MM/YYYY')," +
        "to_date('7/7/7777','DD/MM/YYYY'),'" + childID + "')",this.vPosition);
    Vector vData = new Vector();
    vData.addElement(new Long(storeID1));
    vData.addElement(type);
    vData.addElement(name);
    vData.addElement(shortName1);
    vData.addElement(address1);
    vData.addElement(address2);
    vData.addElement(city);
    vData.addElement(county);
    vData.addElement(country);
    vData.addElement(managerName);
    vData.addElement(phoneNo);
    vData.addElement(faxNo);
    vData.addElement(email);
    vData.addElement(currID);
    vData.addElement(txtChildName.getText());
    vData.addElement(childID);
    vModify.setElementAt(vData, this.vPosition);
    Object[] rowData = new Object[] {
        new Long(storeID1), name, managerName, phoneNo, faxNo
    };

    table.setValueAt(new Long(storeID1),this.vPosition,0);
    table.setValueAt(name,this.vPosition,1);
    table.setValueAt(managerName, this.vPosition, 2);
    table.setValueAt(phoneNo, this.vPosition, 3);
    table.setValueAt(faxNo, this.vPosition, 4);
    resetData();
    setButton(true,false);
    txtStoreID.setText(String.valueOf(Integer.parseInt(table.getValueAt(table.getRowCount()-1,0).toString())+1));
    flagSetHotKeyForAdd = true;
  }
  void navigationComp(KeyEvent e, JTextField txt){
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      txt.requestFocus(true);
      txt.selectAll();
    }
  }
  void navigationComp(KeyEvent e, JButton btn){
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      btn.requestFocus(true);
    }
  }

  void navigationComp(KeyEvent e, JComboBox cbo){
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      cbo.requestFocus(true);
    }
  }
  void navigationComp(KeyEvent e, JTextArea txtArea){
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      txtArea.requestFocus(true);
      txtArea.selectAll();
    }
  }
  void cboType_keyTyped(KeyEvent e) {
    navigationComp(e,txtName);
  }

  void cboCurrCDE_keyTyped(KeyEvent e) {
    navigationComp(e,btnChildID);
  }

  void txtName_focusGained(FocusEvent e) {
    txtName.selectAll();
  }

  void txtShortName1_focusGained(FocusEvent e) {
    txtShortName1.selectAll();
  }

  void txtAddress1_focusGained(FocusEvent e) {
    txtAddress1.selectAll();
  }

  void txtAddress2_focusGained(FocusEvent e) {
    txtAddress2.selectAll();
  }

  void txtCity_focusGained(FocusEvent e) {
    txtCity.selectAll();
  }

  void txtCounty_focusGained(FocusEvent e) {
    txtCounty.selectAll();
  }

  void txtCountry_focusGained(FocusEvent e) {
    txtCountry.selectAll();
  }

  void txtManagerName_focusGained(FocusEvent e) {
    txtManagerName.selectAll();
  }

  void txtPhoneNo_actionPerformed(ActionEvent e) {
    txtPhoneNo.selectAll();
  }

  void txtFaxNo_focusGained(FocusEvent e) {
    txtFaxNo.selectAll();
  }

  void txtEmail_focusGained(FocusEvent e) {
    txtEmail.selectAll();
  }

  void txtName_focusLost(FocusEvent e) {
    txtName.select(0,0);
  }

  void txtShortName1_focusLost(FocusEvent e) {
    txtShortName1.select(0,0);
  }

  void txtAddress1_focusLost(FocusEvent e) {
    txtAddress1.select(0,0);
  }

  void txtAddress2_focusLost(FocusEvent e) {
    txtAddress2.select(0,0);
  }

  void txtCity_focusLost(FocusEvent e) {
    txtCity.select(0,0);
  }

  void txtCounty_focusLost(FocusEvent e) {
    txtCounty.select(0,0);
  }

  void txtCountry_focusLost(FocusEvent e) {
    txtCountry.select(0,0);
  }

  void txtManagerName_focusLost(FocusEvent e) {
    txtManagerName.select(0,0);
  }

  void txtPhoneNo_focusGained(FocusEvent e) {
    txtPhoneNo.selectAll();
  }

  void txtPhoneNo_focusLost(FocusEvent e) {
    txtPhoneNo.select(0,0);
  }

  void txtFaxNo_focusLost(FocusEvent e) {
    txtFaxNo.select(0,0);
  }

  void txtEmail_focusLost(FocusEvent e) {
    txtEmail.select(0,0);
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      if (flagSetHotKeyForAdd == true) {
                 btnAdd.doClick();
               }
               else {
                 btnModify.doClick();

               }
             }
  }

}

class PanelStoreSetup_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_btnAdd_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelStoreSetup_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_btnDone_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

//class PanelStoreSetup_btnCreatedDate_actionAdapter implements java.awt.event.ActionListener {
//  PanelStoreSetup adaptee;
//
//  PanelStoreSetup_btnCreatedDate_actionAdapter(PanelStoreSetup adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnCreatedDate_actionPerformed(e);
//  }
//}

//class PanelStoreSetup_btnClosedDate_actionAdapter implements java.awt.event.ActionListener {
//  PanelStoreSetup adaptee;
//
//  PanelStoreSetup_btnClosedDate_actionAdapter(PanelStoreSetup adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnClosedDate_actionPerformed(e);
//  }
//}

class PanelStoreSetup_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_btnDelete_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelStoreSetup_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_btnCancel_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelStoreSetup_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_btnSearch_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelStoreSetup_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_table_mouseAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelStoreSetup_this_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_this_mouseAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.this_mousePressed(e);
  }
}

class PanelStoreSetup_btnChildID_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_btnChildID_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnChildID_actionPerformed(e);
  }
}

class PanelStoreSetup_txtStoreID_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtStoreID_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtStoreID_keyTyped(e);
  }
}

class PanelStoreSetup_txtName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtName_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtName_keyTyped(e);
  }
}

class PanelStoreSetup_txtShortName1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtShortName1_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtShortName1_keyTyped(e);
  }
}

class PanelStoreSetup_txtAddress1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtAddress1_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress1_keyTyped(e);
  }
}

class PanelStoreSetup_txtAddress2_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtAddress2_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress2_keyTyped(e);
  }
}

class PanelStoreSetup_txtCity_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtCity_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCity_keyTyped(e);
  }
}

class PanelStoreSetup_txtCounty_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtCounty_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCounty_keyTyped(e);
  }
}

class PanelStoreSetup_txtCountry_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtCountry_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCountry_keyTyped(e);
  }
}

class PanelStoreSetup_txtManagerName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtManagerName_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtManagerName_keyTyped(e);
  }
}

class PanelStoreSetup_txtPhoneNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtPhoneNo_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPhoneNo_keyTyped(e);
  }
}

class PanelStoreSetup_txtFaxNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtFaxNo_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtFaxNo_keyTyped(e);
  }
}

class PanelStoreSetup_txtEmail_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtEmail_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEmail_keyTyped(e);
  }
}

class PanelStoreSetup_btnClear_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_btnClear_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelStoreSetup_txtStoreID_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtStoreID_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtStoreID_focusLost(e);
  }
}

class PanelStoreSetup_cboType_propertyChangeAdapter implements java.beans.PropertyChangeListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_cboType_propertyChangeAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void propertyChange(PropertyChangeEvent e) {
    adaptee.cboType_propertyChange(e);
  }
}

class PanelStoreSetup_cboType_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_cboType_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboType_actionPerformed(e);
  }
}

class PanelStoreSetup_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_btnModify_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelStoreSetup_cboType_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_cboType_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboType_keyTyped(e);
  }
}

class PanelStoreSetup_cboCurrCDE_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_cboCurrCDE_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboCurrCDE_keyTyped(e);
  }
}

class PanelStoreSetup_txtName_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtName_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtName_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtName_focusLost(e);
  }
}

class PanelStoreSetup_txtShortName1_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtShortName1_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtShortName1_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtShortName1_focusLost(e);
  }
}

class PanelStoreSetup_txtAddress1_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtAddress1_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtAddress1_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtAddress1_focusLost(e);
  }
}

class PanelStoreSetup_txtAddress2_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtAddress2_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtAddress2_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtAddress2_focusLost(e);
  }
}

class PanelStoreSetup_txtCity_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtCity_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtCity_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtCity_focusLost(e);
  }
}

class PanelStoreSetup_txtCounty_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtCounty_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtCounty_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtCounty_focusLost(e);
  }
}

class PanelStoreSetup_txtCountry_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtCountry_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtCountry_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtCountry_focusLost(e);
  }
}

class PanelStoreSetup_txtManagerName_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtManagerName_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtManagerName_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtManagerName_focusLost(e);
  }
}

class PanelStoreSetup_txtPhoneNo_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtPhoneNo_actionAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtPhoneNo_actionPerformed(e);
  }
}

class PanelStoreSetup_txtFaxNo_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtFaxNo_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtFaxNo_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtFaxNo_focusLost(e);
  }
}

class PanelStoreSetup_txtEmail_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtEmail_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtEmail_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtEmail_focusLost(e);
  }
}

class PanelStoreSetup_txtPhoneNo_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_txtPhoneNo_focusAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtPhoneNo_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtPhoneNo_focusLost(e);
  }
}

class PanelStoreSetup_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreSetup adaptee;

  PanelStoreSetup_table_keyAdapter(PanelStoreSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

