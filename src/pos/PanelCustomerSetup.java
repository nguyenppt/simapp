/*Modified by Nghia Le*/
package pos;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import java.util.Vector;
import btm.attr.*;
import javax.swing.event.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Man hinh Chinh -> Quan Tri -> Khach hang (Error on Grid)</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelCustomerSetup
    extends JPanel {
  //Column Order of Sale Item Table
  private static final int COL_CUMSTOMER_ID = 0; //
  private static final int COL_CUSTOMER_NAME = 1;
  private static final int COL_ADDRESS_1 = 2; //
  private static final int COL_CREATED_DATE = 3;

  DataAccessLayer DAL;
  FrameMain frmMain;
  String customerId1 = "";
  String customerId2 = "";
  Statement stmt = null;
  CustomerBusObj cusBusObj = new CustomerBusObj();
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  Vector vSql = new Vector();
  boolean flag_Customer_ID = false;
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabeltype = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  //
  JPanel jPanel7 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  //
  JTextField txtMobileNo = new JTextField();
  JTextField txtWorkNo = new JTextField();
  JTextField txtHomeNo = new JTextField();
  JTextField txtAddress2 = new JTextField();
  JTextField txtAddress1 = new JTextField();
  JTextField txtAbbrName = new JTextField();
  JTextField txtName = new JTextField();

  BJComboBox comType = new BJComboBox();

  //
  JTextField txtEmail = new JTextField();
  JTextField txtCity = new JTextField();
  JTextField txtCounty = new JTextField();
  JTextField txtCountry = new JTextField();
  JTextField txtFax1 = new JTextField();
  JTextField txtFax2 = new JTextField();
  JTextField txtCreatedDate = new JTextField();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jPanel12 = new JPanel();
  JPanel jPanel13 = new JPanel();
  JTextArea txtComment = new JTextArea();
  JLabel jLabel15 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm, true) {
    public boolean isCellEditable(int row, int col) {
      return false;
    }
  };
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPanel14 = new JPanel();
  JScrollPane jScrollPane2 = new JScrollPane();
  BorderLayout borderLayout6 = new BorderLayout();
  BJButton btnCancel = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();

  //
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,
      Color.lightGray, Color.white, null, null);
  BJButton btnDelete = new BJButton();
  int rowsNum = 5;
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel1 = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JTextField txtBirthDay = new JTextField();
  JLabel jLabel18 = new JLabel();
  JButton jButton1 = new JButton();
  JLabel jLabel19 = new JLabel();

  public PanelCustomerSetup(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
//        System.out.println("N6");
    registerKeyboardActions();
    this.setPreferredSize(new Dimension(800, 600));
    this.setLayout(borderLayout1);
    jPanel2.setBorder(null);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setOpaque(true);
    jPanel2.setPreferredSize(new Dimension(330, 330));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setDebugGraphicsOptions(0);
    jPanel3.setPreferredSize(new Dimension(270, 330));
    jPanel3.setLayout(borderLayout3);
    jPanel4.setPreferredSize(new Dimension(120, 330));
    jPanel4.setLayout(flowLayout2);
    jPanel7.setLayout(flowLayout1);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(110, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel1.setText(lang.getString("WorkPhone") + ":");
    jLabel2.setText(lang.getString("HomePhone") + ":");
    jLabel2.setPreferredSize(new Dimension(110, 20));
    jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    jLabeltype.setText("Choose customer type :");
//    jLabeltype.setPreferredSize(new Dimension(110, 20));
//    jLabeltype.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    jLabel3.setText(lang.getString("Address2") + ":");
    jLabel3.setPreferredSize(new Dimension(110, 20));
    jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("Address1") + ":");
    jLabel4.setPreferredSize(new Dimension(110, 20));
    jLabel4.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setText(lang.getString("AbbreviationName") + ":");
    jLabel5.setPreferredSize(new Dimension(110, 20));
    jLabel5.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabeltype.setText(lang.getString("CustomerType") + ":");
    jLabeltype.setPreferredSize(new Dimension(110, 20));
    jLabeltype.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    jLabel6.setText(lang.getString("CustomerName") + ":");
    jLabel6.setPreferredSize(new Dimension(110, 20));
    jLabel6.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel7.setText(lang.getString("CellPhone") + ":");
    jLabel7.setVerticalAlignment(SwingConstants.CENTER);
    jLabel7.setVerticalTextPosition(SwingConstants.CENTER);
    jLabel7.setPreferredSize(new Dimension(90, 20));
    jLabel7.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel7.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//
    jLabel8.setText(lang.getString("Email") + ":");
    jLabel8.setPreferredSize(new Dimension(90, 20));
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel9.setText(lang.getString("City") + ":");
    jLabel9.setPreferredSize(new Dimension(90, 20));
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel10.setText(lang.getString("County") + ":");
    jLabel10.setPreferredSize(new Dimension(90, 20));
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel11.setText(lang.getString("Country") + ":");
    jLabel11.setPreferredSize(new Dimension(90, 20));
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel12.setText(lang.getString("Fax") + " 1:");
    jLabel12.setPreferredSize(new Dimension(90, 20));
    jLabel12.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel13.setText(lang.getString("Fax") + " 2:");
    jLabel13.setPreferredSize(new Dimension(90, 20));
    jLabel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel14.setText(lang.getString("CreatedDate") + ":");
    jLabel14.setPreferredSize(new Dimension(90, 20));
    jLabel14.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//
    txtMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtMobileNo.setPreferredSize(new Dimension(150, 20));
    txtMobileNo.setSelectionStart(0);
    txtMobileNo.setText("");
    txtMobileNo.addKeyListener(new PanelCustomerSetup_txtMobileNo_keyAdapter(this));
    txtWorkNo.setText("");
    txtWorkNo.setBounds(new Rectangle(30, 182, 150, 20));
    txtWorkNo.addKeyListener(new PanelCustomerSetup_txtWorkNo_keyAdapter(this));
    txtWorkNo.setSelectionStart(0);
    txtWorkNo.setPreferredSize(new Dimension(150, 20));
    txtWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtHomeNo.setText("");
    txtHomeNo.setBounds(new Rectangle(30, 157, 150, 20));
    txtHomeNo.addKeyListener(new PanelCustomerSetup_txtHomeNo_keyAdapter(this));
    txtHomeNo.setSelectionStart(0);
    txtHomeNo.setPreferredSize(new Dimension(150, 20));
    txtHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAddress2.setText("");
    txtAddress2.setBounds(new Rectangle(30, 132, 150, 20));
    txtAddress2.addKeyListener(new PanelCustomerSetup_txtAddress2_keyAdapter(this));
    txtAddress2.setSelectionStart(0);
    txtAddress2.setPreferredSize(new Dimension(150, 20));
    txtAddress2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAddress1.setText("");
    txtAddress1.setBounds(new Rectangle(30, 107, 150, 20));
    txtAddress1.addKeyListener(new PanelCustomerSetup_txtAddress1_keyAdapter(this));
    txtAddress1.setSelectionStart(0);
    txtAddress1.setPreferredSize(new Dimension(150, 20));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAbbrName.setText("");
    txtAbbrName.setBounds(new Rectangle(30, 55, 150, 20));
    txtAbbrName.addKeyListener(new PanelCustomerSetup_txtAbbrName_keyAdapter(this));
    txtAbbrName.setSelectionStart(0);
    txtAbbrName.setPreferredSize(new Dimension(150, 20));
    txtAbbrName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtName.setText("");
    txtName.setBounds(new Rectangle(30, 30, 150, 20));
    txtName.addKeyListener(new PanelCustomerSetup_txtName_keyAdapter(this));
    txtName.setSelectionStart(0);
    txtName.setPreferredSize(new Dimension(150, 20));
    txtName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
//
    txtEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtEmail.setPreferredSize(new Dimension(150, 20));
    txtEmail.setSelectionStart(0);
    txtEmail.setText("");
    txtEmail.addKeyListener(new PanelCustomerSetup_txtEmail_keyAdapter(this));
    txtCity.setText("");
    txtCity.addKeyListener(new PanelCustomerSetup_txtCity_keyAdapter(this));
    txtCity.setSelectionStart(0);
    txtCity.setPreferredSize(new Dimension(150, 20));
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCounty.setText("");
    txtCounty.addKeyListener(new PanelCustomerSetup_txtCounty_keyAdapter(this));
    txtCounty.setSelectionStart(0);
    txtCounty.setPreferredSize(new Dimension(150, 20));
    txtCounty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCountry.setText("");
    txtCountry.addKeyListener(new PanelCustomerSetup_txtCountry_keyAdapter(this));
    txtCountry.setSelectionStart(0);
    txtCountry.setPreferredSize(new Dimension(150, 20));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtFax1.setText("");
    txtFax1.addKeyListener(new PanelCustomerSetup_txtFax1_keyAdapter(this));
    txtFax1.setSelectionStart(0);
    txtFax1.setPreferredSize(new Dimension(150, 20));
    txtFax1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtFax2.setText("");
    txtFax2.addKeyListener(new PanelCustomerSetup_txtFax2_keyAdapter(this));
    txtFax2.setSelectionStart(0);
    txtFax2.setPreferredSize(new Dimension(150, 20));
    txtFax2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCreatedDate.setText("");
    txtCreatedDate.setSelectionStart(0);
    txtCreatedDate.setPreferredSize(new Dimension(150, 20));
    txtCreatedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCreatedDate.setText(ut.getSystemDate(1));
    txtCreatedDate.setEditable(false);

    comType.setPreferredSize(new Dimension(150, 20));
    comType.setBounds(new Rectangle(30, 5, 150, 20));
    comType.addItemListener(new PanelCustomerSetup_comType_itemAdapter(this));

//
    jPanel5.setPreferredSize(new Dimension(180, 330));
    jPanel5.setLayout(null);
    jPanel9.setPreferredSize(new Dimension(800, 270));
    jPanel9.setLayout(borderLayout4);
    jPanel10.setPreferredSize(new Dimension(800, 50));
    jPanel10.setLayout(borderLayout5);
    jPanel11.setPreferredSize(new Dimension(800, 200));
    jPanel11.setLayout(gridLayout2);
    jPanel12.setPreferredSize(new Dimension(110, 50));
    jPanel12.setLayout(null);
    jPanel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    jPanel13.setPreferredSize(new Dimension(400, 50));
    jPanel13.setLayout(null);
    txtComment.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtComment.setMaximumSize(new Dimension(0, 0));
    txtComment.setMinimumSize(new Dimension(0, 0));
//    txtComment.setPreferredSize(new Dimension(0, 0));
    txtComment.setText("");
    txtComment.setLineWrap(true);
    txtComment.setWrapStyleWord(true);
    txtComment.addKeyListener(new PanelCustomerSetup_txtComment_keyAdapter(this));
    jLabel15.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    jLabel15.setPreferredSize(new Dimension(90, 15));
    jLabel15.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel15.setText(lang.getString("Comment") + ":");
    jLabel15.setBounds(new Rectangle(11, 14, 90, 15));
    jPanel14.setPreferredSize(new Dimension(800, 20));
    jScrollPane2.setPreferredSize(new Dimension(450, 45));
    jScrollPane2.setBounds(new Rectangle(40, 5, 469, 45));
    jScrollPane1.setPreferredSize(new Dimension(1000, 250));
    jScrollPane1.setVerifyInputWhenFocusTarget(true);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    //  btnCancel.setPreferredSize(new Dimension(80, 35));
    btnCancel.setToolTipText("");
    btnCancel.setText("<html><center><br><b>"+lang.getString("Cancel") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                          "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                          "ml>");
    btnCancel.addAncestorListener(new
                                  PanelCustomerSetup_btnCancel_ancestorAdapter(this));
    btnCancel.addActionListener(new PanelCustomerSetup_btnCancel_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    //   btnSearch.setPreferredSize(new Dimension(80, 35));
    btnSearch.setText(lang.getString("Search") + " (F3)");
    btnSearch.addActionListener(new PanelCustomerSetup_btnSearch_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    //  btnAdd.setPreferredSize(new Dimension(80, 35));
    btnAdd.setText(lang.getString("Add") + " (F2)");
    btnAdd.addActionListener(new PanelCustomerSetup_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    //   btnDone.setPreferredSize(new Dimension(80, 35));
    btnDone.setText(lang.getString("Done") + " (F1)");
    btnDone.addActionListener(new PanelCustomerSetup_btnDone_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    //  btnDelete.setPreferredSize(new Dimension(80, 35));
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8<" +
                      "/html>");
    btnDelete.addActionListener(new PanelCustomerSetup_btnDelete_actionAdapter(this));
    jPanel7.setPreferredSize(new Dimension(100, 330));
    jPanel7.setRequestFocusEnabled(true);
    jPanel1.setPreferredSize(new Dimension(170, 330));
    jLabel16.setBackground(SystemColor.control);
    jLabel16.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel16.setForeground(Color.red);
    jLabel16.setText("(*)");
    jLabel16.setBounds(new Rectangle(182, 27, 21, 23));
    jLabel17.setBounds(new Rectangle(183, 104, 21, 23));
    jLabel17.setText("(*)");
    jLabel17.setForeground(Color.red);
    jLabel17.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel17.setBackground(SystemColor.control);
    txtBirthDay.setBackground(Color.white);
    txtBirthDay.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtBirthDay.setPreferredSize(new Dimension(150, 20));
    txtBirthDay.setEditable(false);
    txtBirthDay.setSelectionStart(0);
    txtBirthDay.setBounds(new Rectangle(30, 81, 121, 20));
    txtBirthDay.setText("");
    jLabel18.setText(lang.getString("Birthday") + ":");
    jLabel18.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel18.setPreferredSize(new Dimension(110, 20));
    jLabel18.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jButton1.setBounds(new Rectangle(153, 81, 26, 20));
    jButton1.setMinimumSize(new Dimension(73, 25));
    jButton1.setPreferredSize(new Dimension(26, 20));
    jButton1.setText("jButton1");
    jButton1.addActionListener(new PanelCustomerSetup_jButton1_actionAdapter(this));
    table.addKeyListener(new PanelCustomerSetup_table_keyAdapter(this));
    jLabel19.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel19.setForeground(Color.red);
    jLabel19.setText("(*)");
    jLabel19.setBounds(new Rectangle(182, 55, 18, 19));
    this.add(jPanel2, BorderLayout.WEST);
    jPanel2.add(jPanel4, BorderLayout.WEST);
    jPanel4.add(jLabeltype, null);
    jPanel4.add(jLabel6, null);
    jPanel4.add(jLabel5, null);
    jPanel4.add(jLabel18, null);
    jPanel4.add(jLabel4, null);
    jPanel4.add(jLabel3, null);
    jPanel4.add(jLabel2, null);
    jPanel4.add(jLabel1, null);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel5.add(comType, null);
    jPanel5.add(txtName, null);
    jPanel5.add(txtAbbrName, null);
    jPanel5.add(jLabel16, null);
    jPanel5.add(txtAddress2, null);
    jPanel5.add(txtHomeNo, null);
    jPanel5.add(txtWorkNo, null);
    jPanel5.add(jLabel17, null);
    jPanel5.add(txtAddress1, null);
    jPanel5.add(txtBirthDay, null);
    this.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jPanel7, BorderLayout.WEST);
    jPanel7.add(jLabel7, null);
    jPanel7.add(jLabel8, null);
    jPanel7.add(jLabel9, null);
    jPanel7.add(jLabel10, null);
    jPanel7.add(jLabel11, null);
    jPanel7.add(jLabel12, null);
    jPanel7.add(jLabel13, null);
    jPanel7.add(jLabel14, null);
    jPanel3.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(txtMobileNo, null);
    jPanel1.add(txtEmail, null);
    jPanel1.add(txtCity, null);
    jPanel1.add(txtCounty, null);
    jPanel1.add(txtCountry, null);
    jPanel1.add(txtFax1, null);
    jPanel1.add(txtFax2, null);
    jPanel1.add(txtCreatedDate, null);
    this.add(jPanel9, BorderLayout.SOUTH);
    jPanel9.add(jPanel11, BorderLayout.SOUTH);
    jPanel11.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    table.setRowHeight(30);
//    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    jPanel9.add(jPanel10, BorderLayout.NORTH);
    jPanel10.add(jPanel12, BorderLayout.WEST);
    jPanel12.add(jLabel15, null);
    jPanel10.add(jPanel13, BorderLayout.CENTER);
    jPanel13.add(jScrollPane2, null);
    jScrollPane2.getViewport().add(txtComment, null);
    jPanel9.add(jPanel14, BorderLayout.CENTER);
    jPanel5.add(jButton1, null);
    jPanel5.add(jLabel19, null);
    String[] columnNames = new String[] {
        lang.getString("ItemID"),lang.getString("ItemName"),lang.getString("Address1"),lang.getString("CreatedDate")};
    /*
     String[] columnNames = new String[] {
        "ID","Name", "Address 1", "Created Date"};
     */
    dm.setDataVector(new Object[][] {}
                     , columnNames);
    comType.addItem("Regular"); //cus_flag=0
    comType.addItem("Home Employee");//cus_flag=1
    comType.addItem("Whole Sale");//cus_flag=2
    comType.addItem("Other");//cus_flag=3

//    comType.addItem("Franchise");
//    comType.addItem("Whole Sales");
//    comType.addItem("Others");
    table.getColumnModel().getColumn(0).setPreferredWidth(150);
    table.getColumnModel().getColumn(1).setPreferredWidth(150);
    table.getColumnModel().getColumn(2).setPreferredWidth(200);
    table.getColumnModel().getColumn(3).setPreferredWidth(150);
//    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//    table.getTableHeader().setReorderingAllowed(false);

  }

  void ComboData() {
    if (comType.getSelectedIndex() == 0) {
      jLabel5.setText(lang.getString("FirstName") + ":");
      jLabel6.setText(lang.getString("LastName") + ":");
      jButton1.setEnabled(true);
      txtBirthDay.setText("");
    }
    else {
      jLabel5.setText(lang.getString("AbbreviationName") + ":");
      jLabel6.setText(lang.getString("CustomerName") + ":");
      jButton1.setEnabled(false);
      txtBirthDay.setText("");
    }
  }

  //show button
  void showButton() {
    frmMain.showButton(btnDone);
    frmMain.showButton(btnAdd);
    frmMain.showButton(btnDelete);
    frmMain.showButton(btnSearch);
    frmMain.showButton(btnCancel);
  }

  private String createCustomerId() {
    String customerId = "1";
    if (comType.getSelectedIndex() == 0) {
      customerId = "2";
    }
    String sysDate = ut.getSystemShortDate().replaceAll("/", "");
    customerId += DAL.getStoreID() + sysDate + "001";
    return customerId;
  }

  public void getCustomerId() {
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    String sqlStr = "";
    long cusID = 0;
    try {
//        sqlStr = "Select max(CUST_ID) CUSTID From BTM_POS_CUSTOMER Where Cust_Flag='1' and Created_Date=to_date(SYSDATE,'dd-MM-yy')";
      sqlStr =
          "Select max(CUST_ID) CUSTID From BTM_POS_CUSTOMER Where Cust_Flag='1' ";
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs1 = DAL.executeScrollQueries(sqlStr, stmt);
      if (rs1.next()) {
        if (rs1.getString("CUSTID") != null) {
          cusID = Long.parseLong(rs1.getString("CUSTID").toString());
          customerId1 = String.valueOf(cusID);
        }
      }
      rs1.close();
//        sqlStr = "Select max(CUST_ID) CUSTID From BTM_POS_CUSTOMER Where Cust_Flag='0' and Created_Date=to_date(SYSDATE,'dd-MM-yy')";
      sqlStr =
          "Select max(CUST_ID) CUSTID From BTM_POS_CUSTOMER Where Cust_Flag='0' ";
      rs2 = DAL.executeScrollQueries(sqlStr, stmt);
      if (rs2.next()) {
        if (rs2.getString("CUSTID") != null) {
          cusID = Long.parseLong(rs2.getString("CUSTID").toString());
          customerId2 = String.valueOf(cusID);
        }
      }
      stmt.close();
      rs2.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    String firstName = txtAbbrName.getText().trim();
    String lastName = txtName.getText().trim();
    String address1 = txtAddress1.getText().trim();
    String address2 = txtAddress2.getText().trim();
    String homeNo = txtHomeNo.getText().trim();
    String workNo = txtWorkNo.getText().trim();
    String mobileNo = txtMobileNo.getText().trim();
    String email = txtEmail.getText().trim();
    String city = txtCity.getText().trim();
    String county = txtCounty.getText().trim();
    String country = txtCountry.getText().trim();
    String fax1 = txtFax1.getText().trim();
    String fax2 = txtFax2.getText().trim();
    String custComment = txtComment.getText().trim();
    String createdDate = txtCreatedDate.getText().trim();
    String birthDay = txtBirthDay.getText().trim();
    if (lastName.equals("")) {
      if(comType.getSelectedItem().toString().equalsIgnoreCase("Regular")){
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS109_LastNameNotNull"));
      }else{
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS110_CusNameNotNull"));
      }
      txtName.requestFocus(true);
      return;
    }
    if (firstName.equals("")) {
      if(comType.getSelectedItem().toString().equalsIgnoreCase("Regular")){
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS111_FirstNameNotNull"));
      }else{
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS112_AbbreviationNameNotNull"));
      }
      txtName.requestFocus(true);
      return;
    }
    if (address1.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS113_AddressNotNull"));
      txtAddress1.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(lastName, 120)) {
      if(comType.getSelectedItem().toString().equalsIgnoreCase("Regular")){
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS114_LastNameTooLong"));
      }else{
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS115_CustomerNameTooLong"));
      }
      txtName.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(firstName, 120)) {
      if(comType.getSelectedItem().toString().equalsIgnoreCase("Regular")){
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS116_FirstNameTooLong"));
      }else{
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS117_AbbreviationNameTooLong"));
      }
      txtAbbrName.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(address1, 150)) {
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS118_AddressTooLong"));
      txtAddress1.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(address2, 150)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS119_AddressTooLong"));
      txtAddress2.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(homeNo, 15)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS120_HomePhoneTooLong"));
      txtHomeNo.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(workNo, 15)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS121_WorkPhoneTooLong"));
      txtWorkNo.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(mobileNo, 15)) {
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS122_CellPhoneTooLong"));
      txtMobileNo.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(email, 125)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS123_EmailTooLong"));
      txtEmail.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(city, 50)) {
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS124_CityTooLong"));
      txtCity.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(county, 50)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS125_CountyTooLong"));
      txtCounty.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(country, 50)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS126_CountryTooLong"));
      txtCountry.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(fax1, 15)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS127_FaxTooLong"));
      txtFax1.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(fax2, 15)) {
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS116_FirstNameTooLong"));
      txtFax2.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(custComment, 250)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS128_CommentTooLong"));
      txtComment.requestFocus(true);
      return;
    }

    if (ut.checkPhoneNumber(homeNo) == false) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS129_WrongHomePhone"));
      txtHomeNo.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(workNo) == false) {
      ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS130_WrongWorkPhone"));
      txtWorkNo.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(mobileNo) == false) {
      ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS131_WrongCellPhone"));
      txtMobileNo.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(fax1) == false) {
      ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS132_WrongFax"));
      txtFax1.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(fax2) == false) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS133_WrongFax"));
      txtFax2.requestFocus(true);
      return;
    }
    if (birthDay.equalsIgnoreCase("")) {
      birthDay = "01/01/1900";
    }
    String customerId = "";
    /*if (comType.getSelectedIndex() == 1) {
      if (customerId1.trim().equals("")) {
          customerId1 = createCustomerId();
      }
      else {
        customerId1 = String.valueOf(Long.parseLong(customerId1) + 1);
      }
      customerId = customerId1;
         }
         else {
      if (customerId2.trim().equals("")) {
        customerId2 = createCustomerId();
      }
      else {
        customerId2 = String.valueOf(Long.parseLong(customerId2) + 1);
      }
      customerId = customerId2;
         }*/
    if (table.getRowCount() == 0) {
      customerId = cusBusObj.getCustomerIdCanInsertPOS(DAL);
    }
    else {
      //long lID = Long.parseLong(cusBusObj.getCustomerIdCanInsertPOS(DAL));
      long lID = Long.parseLong(""+table.getValueAt(table.getRowCount()-1,0));
      lID += 1;
      customerId = "" + lID;
    }

//    System.out.println(customerId);
    vSql.addElement("insert into BTM_POS_CUSTOMER values('" + customerId +
                    "','" +
                    firstName +
                    "','" + lastName + "','" + address1 + "','" + address2 +
                    "','" + homeNo + "','" + workNo + "','" + mobileNo + "','" +
                    fax1 + "','" + fax2 + "','" +
                    custComment + "','" + email + "','" + city + "','" + county +
                    "','" + country + "',to_date('" +
                    createdDate + "','DD-MM-YYYY'),'" +
                    comType.getSelectedIndex() + "', to_date('" + birthDay +
                    "','DD/MM/YYYY')," + DAL.getStoreID() + ")");

    String[] rowData = new String[] {
        customerId,lastName, address1, createdDate};
    dm.addRow(rowData);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255, 255, 230, f1);
    str.installInTable(table, Color.getHSBColor(f1[0], f1[1], f1[2]),
                       Color.black, null, null);
    txtName.setText("");
    txtAbbrName.setText("");
    txtAddress1.setText("");
    txtAddress2.setText("");
    txtHomeNo.setText("");
    txtWorkNo.setText("");
    txtMobileNo.setText("");
    txtEmail.setText("");
    txtCity.setText("");
    txtCounty.setText("");
    txtCountry.setText("");
    txtFax1.setText("");
    txtFax2.setText("");
    txtBirthDay.setText("");
    txtComment.setText("");

    //set focus for last row
    if(table.getRowCount()>0){
      table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
    }

    //show current row
    Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
    jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

  }

  void btnDone_actionPerformed(ActionEvent e) {
    if (vSql.isEmpty() == false) {
      try {
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
        DAL.executeBatchQuery(vSql, stmt);
        stmt.close();
      }
      catch (Exception ex) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      }
    }
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    vSql.clear();
    txtName.setText("");
    txtAbbrName.setText("");
    txtAddress1.setText("");
    txtAddress2.setText("");
    txtHomeNo.setText("");
    txtWorkNo.setText("");
    txtMobileNo.setText("");
    txtEmail.setText("");
    txtCity.setText("");
    txtCounty.setText("");
    txtCountry.setText("");
    txtFax1.setText("");
    txtFax2.setText("");
    txtComment.setText("");
    frmMain.setTitle(lang.getString("TT016"));
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showAdminButton();
  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if (table.getSelectedRow() == -1) {
      return;
    }
    if (vSql.isEmpty()) {
      return;
    }
    if (table.getRowCount() == 1) {
      dm.removeRow(0);
      vSql.removeAllElements();
      return;
    }

    int[] i = table.getSelectedRows();
    for (int j = 0; j < i.length; j++) {
      vSql.removeElementAt(i[0]);
      dm.removeRow(i[0]);
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    vSql.clear();
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    txtName.setText("");
    txtAbbrName.setText("");
    txtAddress1.setText("");
    txtAddress2.setText("");
    txtHomeNo.setText("");
    txtWorkNo.setText("");
    txtMobileNo.setText("");
    txtEmail.setText("");
    txtCity.setText("");
    txtCounty.setText("");
    txtCountry.setText("");
    txtFax1.setText("");
    txtFax2.setText("");
    txtComment.setText("");
    frmMain.setTitle(lang.getString("TT0005"));
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showAdminButton();
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    DialogCustomerSearch search = new DialogCustomerSearch(frmMain,
        lang.getString("TT007"), true, frmMain);
    search.setVisible(true);
    if (search.done) {
      String custID = search.getData();
//      System.out.println(custID);
      frmMain.pnlCustomerModify.custID = custID;
      frmMain.pnlCustomerModify.showData();
//      System.out.println("Done");
      frmMain.setTitle(lang.getString("TT007"));
      frmMain.showScreen(Constant.SCR_CUSTOMER_MODIFY);
//      System.out.println("System.out.println(custID);");
    }
  }

  void txtName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtName, Customer.LEN_CUST_NAME);
  }

  void txtAbbrName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtAbbrName, Customer.LEN_CUST_ABB_NAME);
  }

  void txtAddress1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtAddress1, Customer.LEN_CUST_ADDRESS);
  }

  void txtAddress2_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtAddress2, Customer.LEN_CUST_ADDRESS);
  }

  void txtHomeNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtHomeNo, Customer.LEN_CUST_PHONE);
  }

  void txtWorkNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtWorkNo, Customer.LEN_CUST_PHONE);
  }

  void txtMobileNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtWorkNo, Customer.LEN_CUST_PHONE);
  }

  void txtEmail_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtEmail, Customer.LEN_CUST_EMAIL);
  }

  void txtCity_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtCity, Customer.LEN_CUST_CITY);
  }

  void txtCounty_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtCounty, Customer.LEN_CUST_COUNTY);
  }

  void txtCountry_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtCountry, Customer.LEN_CUST_COUNTRY);
  }

  void txtFax1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtFax1, Customer.LEN_CUST_FAX);
  }

  void txtFax2_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtFax2, Customer.LEN_CUST_FAX);
  }

  void txtComment_keyTyped(KeyEvent e) {
    ut.limitLenjTextArea(e, txtComment, Customer.LEN_CUST_COMMENT);
  }

  void comType_itemStateChanged(ItemEvent e) {
    ComboData();
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

    // ESCAPE
    key = new Integer(KeyEvent.VK_ESCAPE);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
    actionMap.put(key, new KeyAction(key));
  }

  class KeyAction
      extends AbstractAction {

    private Integer identifier = null;

    public KeyAction(Integer identifier) {
      this.identifier = identifier;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {

      //  if(flag == true){//4 nut
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2) {
        btnAdd.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {
        btnSearch.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  private void catchHotKey(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      btnAdd.doClick();
    }
  }

  void btnCancel_ancestorMoved(AncestorEvent e) {

  }

  void jButton1_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    ;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.jButton1.getX() + posXFrame + 90;
    posY = this.jButton1.getY() + posYFrame + 90;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseBirthday"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);
    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtBirthDay.setText(date);
    }
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
}

class PanelCustomerSetup_btnAdd_actionAdapter
    implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnAdd_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnDone_actionAdapter
    implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnDone_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnDelete_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnCancel_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnSearch_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelCustomerSetup_txtName_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtName_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtName_keyTyped(e);
  }
}

class PanelCustomerSetup_txtAbbrName_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtAbbrName_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtAbbrName_keyTyped(e);
  }
}

class PanelCustomerSetup_txtAddress1_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtAddress1_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress1_keyTyped(e);
  }
}

class PanelCustomerSetup_txtAddress2_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtAddress2_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress2_keyTyped(e);
  }
}

class PanelCustomerSetup_txtHomeNo_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtHomeNo_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtHomeNo_keyTyped(e);
  }
}

class PanelCustomerSetup_txtWorkNo_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtWorkNo_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtWorkNo_keyTyped(e);
  }
}

class PanelCustomerSetup_txtMobileNo_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtMobileNo_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtMobileNo_keyTyped(e);
  }
}

class PanelCustomerSetup_txtEmail_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtEmail_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtEmail_keyTyped(e);
  }
}

class PanelCustomerSetup_txtCity_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtCity_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtCity_keyTyped(e);
  }
}

class PanelCustomerSetup_txtCounty_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtCounty_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtCounty_keyTyped(e);
  }
}

class PanelCustomerSetup_txtCountry_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtCountry_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtCountry_keyTyped(e);
  }
}

class PanelCustomerSetup_txtFax1_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtFax1_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtFax1_keyTyped(e);
  }
}

class PanelCustomerSetup_txtFax2_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtFax2_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtFax2_keyTyped(e);
  }
}

class PanelCustomerSetup_txtComment_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtComment_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtComment_keyTyped(e);
  }
}

class PanelCustomerSetup_comType_itemAdapter
    implements java.awt.event.ItemListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_comType_itemAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.comType_itemStateChanged(e);
  }
}

class PanelCustomerSetup_btnCancel_ancestorAdapter
    implements javax.swing.event.AncestorListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnCancel_ancestorAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void ancestorAdded(AncestorEvent e) {
  }

  public void ancestorRemoved(AncestorEvent e) {
  }

  public void ancestorMoved(AncestorEvent e) {
    adaptee.btnCancel_ancestorMoved(e);
  }
}

class PanelCustomerSetup_jButton1_actionAdapter
    implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_jButton1_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class PanelCustomerSetup_table_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_table_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
