package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import btm.attr.*;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Customer Setup -> Customer Modify</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelCustomerModify extends JPanel {
  DataAccessLayer DAL;
  FrameMainSim frmMain;
  Statement stmt = null;
  CustomerBusObj cusBusObj = new CustomerBusObj();
  Utils ut = new Utils();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  //
  JPanel jPanel9 = new JPanel();
  //
  //
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel11 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  JTable table = new JTable(dm){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  GridLayout gridLayout2 = new GridLayout();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jPanel15 = new JPanel();
  BJButton btnCancel = new BJButton();
  BJButton btnAdd = new BJButton();
  //
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  JPanel jPanel2 = new JPanel();
  JTextField txtCustomerID = new JTextField();
  JTextField txtEmail = new JTextField();
  JPanel jPanel13 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JLabel lblName2 = new JLabel();
  JTextField txtHomeNo = new JTextField();
  JLabel jLabel9 = new JLabel();
  JPanel jPanel12 = new JPanel();
  JTextField txtFax2 = new JTextField();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel18 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JPanel jPanel7 = new JPanel();
  FlowLayout flowLayout3 = new FlowLayout();
  JTextField txtName = new JTextField();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel14 = new JPanel();
  FlowLayout flowLayout4 = new FlowLayout();
  JComboBox cboCustomerType = new JComboBox();
  JTextField txtFax1 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel lblName3 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JPanel jPanel4 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout8 = new BorderLayout();
  JTextField txtAbbrName = new JTextField();
  JTextField txtMobileNo = new JTextField();
  JPanel jPanel110 = new JPanel();
  JScrollPane jScrollPane2 = new JScrollPane();
  JLabel lblName1 = new JLabel();
  JTextField txtCountry = new JTextField();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel8 = new JPanel();
  JLabel jLabel11 = new JLabel();
  JTextField txtCity = new JTextField();
  JTextField txtCounty = new JTextField();
  JLabel jLabel7 = new JLabel();
  BorderLayout borderLayout3 = new BorderLayout();
  JTextField txtAddress2 = new JTextField();
  JTextField txtContactName = new JTextField();
  BorderLayout borderLayout5 = new BorderLayout();
  JTextField txtAddress1 = new JTextField();
  JLabel jLabel110 = new JLabel();
  JLabel jLabel15 = new JLabel();
  BorderLayout borderLayout9 = new BorderLayout();
  JTextField txtWorkNo = new JTextField();
  JPanel jPanel20 = new JPanel();
  JLabel jLabel10 = new JLabel();
  JTextArea txtComment = new JTextArea();
  JTextArea jTextArea1 = new JTextArea();
  JLabel jLabel21 = new JLabel();
  JLabel jLabel19 = new JLabel();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout6 = new FlowLayout();
  JPanel jPanel5 = new JPanel();
  JLabel jLabel6 = new JLabel();
  JButton btnDate = new JButton();
  JTextField txtBirthday = new JTextField();
  JLabel jLabel14 = new JLabel();
  BJComboBox cboStore = new BJComboBox();
  BJComboBox cboStore1 = new BJComboBox();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelCustomerModify(FrameMainSim frmMain) {
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
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout6);
//
//
//
//
    jPanel9.setPreferredSize(new Dimension(700, 230));
    jPanel9.setLayout(borderLayout4);
    jPanel11.setPreferredSize(new Dimension(800, 222));
    jPanel11.setLayout(gridLayout2);
    jScrollPane1.setPreferredSize(new Dimension(800, 230));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    jPanel15.setBackground(new Color(121, 152, 198));
    jPanel15.setPreferredSize(new Dimension(800, 50));
//    btnCancel.setPreferredSize(new Dimension(80, 35));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F3)");
    btnCancel.setSelectedIcon(null);
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2<" +
    "/html>");
    btnCancel.addActionListener(new PanelCustomerModify_btnCancel_actionAdapter(this));
//    btnAdd.setPreferredSize(new Dimension(80, 35));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setToolTipText(lang.getString("Modify")+" (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Modify")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1<" +
    "/html>");
    btnAdd.addActionListener(new PanelCustomerModify_btnAdd_actionAdapter(this));
//    btnDone.setPreferredSize(new Dimension(80, 35));
    table.addMouseListener(new PanelCustomerModify_table_mouseAdapter(this));
    jPanel2.setLayout(null);
    txtCustomerID.setText("");
    txtCustomerID.setEditable(false);
    txtCustomerID.setCaretPosition(0);
    txtCustomerID.setPreferredSize(new Dimension(200, 20));
    txtCustomerID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCustomerID.setEnabled(true);
    txtEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmail.setPreferredSize(new Dimension(200, 20));
    txtEmail.setSelectionStart(0);
    txtEmail.setText("");
    txtEmail.addKeyListener(new PanelCustomerModify_txtEmail_keyAdapter(this));
    FlowLayout fl13=new FlowLayout(FlowLayout.LEFT);
    jPanel13.setLayout(fl13);
    jPanel13.setPreferredSize(new Dimension(650, 30));
    jPanel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    jPanel10.setPreferredSize(new Dimension(250, 10));
    FlowLayout fl10=new FlowLayout(FlowLayout.LEFT);
    jPanel10.setLayout(fl10);
    lblName3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblName3.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblName3.setHorizontalAlignment(SwingConstants.LEFT);
    lblName3.setPreferredSize(new Dimension(110, 20));
    lblName3.setText(lang.getString("AbbreName")+":");
    txtHomeNo.setText("");
    txtHomeNo.addKeyListener(new PanelCustomerModify_txtHomeNo_keyAdapter(this));
    txtHomeNo.setSelectionStart(0);
    txtHomeNo.setPreferredSize(new Dimension(200, 20));
    txtHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel9.setText(lang.getString("Store")+":");
    jLabel9.setPreferredSize(new Dimension(90, 20));
    jLabel9.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel12.setPreferredSize(new Dimension(10, 10));
    jPanel12.setPreferredSize(new Dimension(120, 10));
    jPanel12.setLayout(null);
    txtFax2.setText("");
    txtFax2.addKeyListener(new PanelCustomerModify_txtFax2_keyAdapter(this));
    txtFax2.setSelectionStart(0);
    txtFax2.setPreferredSize(new Dimension(200, 20));
    txtFax2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel16.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel16.setForeground(Color.red);
    jLabel16.setText("(*)");
    jLabel18.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel18.setForeground(Color.black);
    jLabel18.setPreferredSize(new Dimension(110, 20));
    jLabel18.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel18.setText(lang.getString("CustomerID")+":");
    jLabel17.setText("(*)");
    jLabel17.setForeground(Color.red);
    jLabel17.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel7.setPreferredSize(new Dimension(150, 10));
    jPanel7.setToolTipText("");
    jPanel7.setLayout(flowLayout1);
    txtName.setText("");
    txtName.addKeyListener(new PanelCustomerModify_txtName_keyAdapter(this));
    txtName.setSelectionStart(0);
    txtName.setPreferredSize(new Dimension(200, 20));
    txtName.setSelectedTextColor(Color.white);
    txtName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel6.setPreferredSize(new Dimension(800, 250));
    jPanel6.setBounds(new Rectangle(0, 0, 800, 310));
    jPanel6.setLayout(borderLayout3);
    jPanel14.setPreferredSize(new Dimension(80, 10));
    cboCustomerType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboCustomerType.setPreferredSize(new Dimension(200, 20));
    cboCustomerType.addItemListener(new PanelCustomerModify_cboCustomerType_itemAdapter(this));
    txtFax1.setText("");
    txtFax1.addKeyListener(new PanelCustomerModify_txtFax1_keyAdapter(this));
    txtFax1.setSelectionStart(0);
    txtFax1.setPreferredSize(new Dimension(200, 20));
    txtFax1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel5.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel5.setPreferredSize(new Dimension(90, 20));
    jLabel5.setText(lang.getString("Address1")+":");
    jLabel8.setText(lang.getString("Email")+":");
    jLabel8.setPreferredSize(new Dimension(90, 20));
    jLabel8.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel4.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel4.setPreferredSize(new Dimension(90, 20));
    jLabel4.setText(lang.getString("Address2")+":");
    jLabel12.setText(lang.getString("Fax1")+":");
    jLabel12.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel12.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel12.setPreferredSize(new Dimension(90, 20));
    jPanel4.setLayout(borderLayout8);
    jPanel4.setMinimumSize(new Dimension(0, 0));
    jPanel4.setPreferredSize(new Dimension(380, 10));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(110, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel1.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel1.setText(lang.getString("WorkPhone")+":");
    jLabel13.setText(lang.getString("Fax2")+":");
    jLabel13.setPreferredSize(new Dimension(90, 20));
    jLabel13.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel3.setPreferredSize(new Dimension(110, 20));
    jLabel3.setText(lang.getString("HomePhone")+":");
    jPanel3.setPreferredSize(new Dimension(380, 230));
    jPanel3.setLayout(borderLayout5);
    txtAbbrName.setText("");
    txtAbbrName.addKeyListener(new PanelCustomerModify_txtAbbrName_keyAdapter(this));
    txtAbbrName.setSelectionStart(0);
    txtAbbrName.setPreferredSize(new Dimension(200, 20));
    txtAbbrName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtMobileNo.setPreferredSize(new Dimension(200, 20));
    txtMobileNo.setSelectionStart(0);
    txtMobileNo.setText("");
    txtMobileNo.addKeyListener(new PanelCustomerModify_txtMobileNo_keyAdapter(this));
    jPanel110.setPreferredSize(new Dimension(250, 10));
    FlowLayout fl11=new FlowLayout(FlowLayout.LEFT);
    jPanel110.setLayout(fl11);
    jScrollPane2.setPreferredSize(new Dimension(620, 45));
    jScrollPane2.setRequestFocusEnabled(true);
    lblName1.setText(lang.getString("CustomerName")+":");
    lblName1.setPreferredSize(new Dimension(110, 20));
    lblName1.setHorizontalAlignment(SwingConstants.LEFT);
    lblName1.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblName1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCountry.setText("");
    txtCountry.addKeyListener(new PanelCustomerModify_txtCountry_keyAdapter(this));
    txtCountry.setSelectionStart(0);
    txtCountry.setPreferredSize(new Dimension(200, 20));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel8.setOpaque(true);
    jPanel8.setPreferredSize(new Dimension(650, 80));
    jPanel8.setLayout(borderLayout9);
    jLabel11.setText(lang.getString("Country")+":");
    jLabel11.setPreferredSize(new Dimension(90, 20));
    jLabel11.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCity.setText("");
    txtCity.addKeyListener(new PanelCustomerModify_txtCity_keyAdapter(this));
    txtCity.setSelectionStart(0);
    txtCity.setPreferredSize(new Dimension(200, 20));
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCounty.setText("");
    txtCounty.addKeyListener(new PanelCustomerModify_txtCounty_keyAdapter(this));
    txtCounty.setSelectionStart(0);
    txtCounty.setPreferredSize(new Dimension(200, 20));
    txtCounty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel7.setText(lang.getString("CellPhone")+":");
    jLabel7.setPreferredSize(new Dimension(110, 20));
    jLabel7.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel7.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress2.setText("");
    txtAddress2.addKeyListener(new PanelCustomerModify_txtAddress2_keyAdapter(this));
    txtAddress2.setSelectionStart(0);
    txtAddress2.setPreferredSize(new Dimension(200, 20));
    txtAddress2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtContactName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtContactName.setForeground(Color.black);
    txtContactName.setPreferredSize(new Dimension(200, 20));
    txtContactName.setText("");
    txtContactName.addKeyListener(new PanelCustomerModify_txtContactName_keyAdapter(this));
    txtAddress1.setText("");
    txtAddress1.addKeyListener(new PanelCustomerModify_txtAddress1_keyAdapter(this));
    txtAddress1.setSelectionStart(0);
    txtAddress1.setPreferredSize(new Dimension(200, 20));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel110.setText(lang.getString("CustomerType")+":");
    jLabel110.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel110.setPreferredSize(new Dimension(110, 20));
    jLabel110.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel15.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel15.setPreferredSize(new Dimension(110, 15));
    jLabel15.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel15.setText(lang.getString("Comment")+":");
    jLabel15.setBounds(new Rectangle(12, 25, 110, 15));
    txtWorkNo.setText("");
    txtWorkNo.addKeyListener(new PanelCustomerModify_txtWorkNo_keyAdapter(this));
    txtWorkNo.setSelectionStart(0);
    txtWorkNo.setPreferredSize(new Dimension(200, 20));
    txtWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel20.setLayout(flowLayout3);
    jPanel20.setPreferredSize(new Dimension(100, 10));
    jLabel10.setText(lang.getString("County")+":");
    jLabel10.setPreferredSize(new Dimension(90, 20));
    jLabel10.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtComment.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtComment.addKeyListener(new PanelCustomerModify_txtComment_keyAdapter(this));
    jLabel21.setText(lang.getString("ContactName")+":");
    jLabel21.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel21.setPreferredSize(new Dimension(110, 20));
    jLabel21.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel19.setText("(*)");
    jLabel19.setForeground(Color.red);
    jLabel19.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel5.setMinimumSize(new Dimension(15, 45));
    jPanel5.setPreferredSize(new Dimension(5, 45));
    jLabel6.setText(lang.getString("Birthday")+":");
    jLabel6.setPreferredSize(new Dimension(110, 20));
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDate.setOpaque(true);
    btnDate.setPreferredSize(new Dimension(25, 22));
    btnDate.setText("...");
    btnDate.addActionListener(new PanelCustomerModify_btnDate_actionAdapter(this));
    txtBirthday.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtBirthday.setPreferredSize(new Dimension(170, 20));
    txtBirthday.setText("");
    txtBirthday.addKeyListener(new PanelCustomerModify_txtBirthday_keyAdapter(this));
    table.addKeyListener(new PanelCustomerModify_table_keyAdapter(this));
    jLabel14.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel14.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel14.setPreferredSize(new Dimension(90, 20));
    jLabel14.setText(lang.getString("City")+":");
    cboStore1.setMinimumSize(new Dimension(100, 21));
    cboStore1.setPreferredSize(new Dimension(100, 21));
    cboStore1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    cboStore1.setMinimumSize(new Dimension(100, 21));
    cboStore1.setPreferredSize(new Dimension(160, 21));
    this.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel15, BorderLayout.WEST);
    jPanel15.add(btnAdd, null);
    jPanel15.add(btnCancel, null);
    this.add(jPanel9,BorderLayout.SOUTH);
    jPanel9.add(jPanel11, BorderLayout.SOUTH);
    jPanel11.add(jScrollPane1, null);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel6, null);
    jPanel7.add(jLabel18, null);
    jPanel7.add(jLabel110, null);
    jPanel7.add(lblName1, null);
    jPanel7.add(lblName3, null);
    jPanel7.add(jLabel21, null);
    jPanel7.add(jLabel3, null);
    jPanel7.add(jLabel7, null);
    jPanel7.add(jLabel1, null);
    jPanel7.add(jLabel6, null);
    jPanel3.add(jPanel10, BorderLayout.EAST);
    jPanel3.add(jPanel7, BorderLayout.CENTER);
    jPanel10.add(txtCustomerID, null);
    jPanel10.add(cboCustomerType, null);
    jPanel10.add(txtName, null);
    jPanel10.add(jLabel16, null);
    jPanel10.add(txtAbbrName, null);
    jPanel10.add(jLabel19, null);
    jPanel10.add(txtContactName, null);
    jPanel10.add(txtHomeNo, null);
    jPanel10.add(txtMobileNo, null);
    jPanel10.add(txtWorkNo, null);
    jPanel10.add(txtBirthday, null);
    jPanel10.add(btnDate, null);
    jPanel6.add(jPanel4, BorderLayout.EAST);
    jPanel6.add(jPanel3, BorderLayout.WEST);
    jPanel20.add(jLabel8, null);
    jPanel20.add(jLabel5, null);
    jPanel20.add(jLabel4, null);
    jPanel20.add(jLabel12, null);
    jPanel20.add(jLabel13, null);
    jPanel20.add(jLabel11, null);
    jPanel20.add(jLabel10, null);
    jPanel20.add(jLabel14, null);
    jPanel20.add(jLabel9, null);
    jPanel4.add(jPanel110, BorderLayout.EAST);
    jPanel4.add(jPanel20, BorderLayout.CENTER);
    jPanel110.add(txtEmail, null);
    jPanel110.add(txtAddress1, null);
    jPanel110.add(jLabel17, null);
    jPanel110.add(txtAddress2, null);
    jPanel110.add(txtFax1, null);
    jPanel110.add(txtFax2, null);
    jPanel110.add(txtCountry, null);
    jPanel110.add(txtCounty, null);
    jPanel110.add(txtCity, null);
    jPanel110.add(cboStore1, null);
    jPanel110.add(cboStore1, null);
    jPanel6.add(jPanel8, BorderLayout.SOUTH);
    jPanel8.add(jPanel12, BorderLayout.WEST);
    jPanel12.add(jLabel15, null);
    jPanel8.add(jPanel13, BorderLayout.CENTER);
    jPanel13.add(jPanel5, null);
    jPanel13.add(jScrollPane2, null);
    jScrollPane2.getViewport().add(txtComment, null);
    jPanel6.add(jPanel14, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(table, null);
    table.setRowHeight(30);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
   String[] columnNames = new String[]{lang.getString("CustomerID"),lang.getString("Name"),lang.getString("ContactName"),lang.getString("WorkPhone")};
    dm.setDataVector(new Object[][]{},columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn(lang.getString("CustomerID")).setPreferredWidth(150);
    table.getColumn(lang.getString("Name")).setPreferredWidth(300);
    table.getColumn(lang.getString("ContactName")).setPreferredWidth(350);
    table.getColumn(lang.getString("WorkPhone")).setPreferredWidth(150);

//    cboCustomerType.addItem("Regular");
//    cboCustomerType.addItem("Franchise");
//    cboCustomerType.addItem("Whole Sales");
//    cboCustomerType.addItem("Other");
    cboCustomerType.addItem("Regular"); //cus_flag=0
    cboCustomerType.addItem("Home Employee");//cus_flag=1
    cboCustomerType.addItem("Whole Sale");//cus_flag=2
    cboCustomerType.addItem("Other");//cus_flag=3

    initData();
  }
  void initData(){
    String sql="";
    ResultSet rs = null;
    try{
      sql = "select STORE_ID,NAME from BTM_IM_STORE order by upper(name) ";
      rs = DAL.executeScrollQueries(sql);
      cboStore1.setData(rs);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  //Show data
  void showData(String custID,DataAccessLayer DAL){
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
    ResultSet rs = null;
    try{
      String sql = "select CUST_ID, FIRST_NAME, LAST_NAME, CONTACT_NAME, ADDRESS1, ADDRESS2, HOME_NO, " +
          "WORK_NO, MOBILE_NO, FAX1, FAX2, CUST_COMMENT, EMAIL, CITY, COUNTY, COUNTRY, CUST_FLAG,STORE_ID " +
          "from BTM_IM_CUSTOMER where CUST_ID = '" + custID + "'";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql);
      rs.beforeFirst();
      if (rs.next()){
        txtCustomerID.setText(rs.getString("CUST_ID"));
        txtName.setText(rs.getString("FIRST_NAME"));
        txtAbbrName.setText(rs.getString("LAST_NAME"));
        txtContactName.setText(rs.getString("CONTACT_NAME"));
        txtAddress1.setText(rs.getString("ADDRESS1"));
        txtAddress2.setText(rs.getString("ADDRESS2"));
        txtHomeNo.setText(rs.getString("HOME_NO"));
        txtWorkNo.setText(rs.getString("WORK_NO"));
        txtMobileNo.setText(rs.getString("MOBILE_NO"));
        txtFax1.setText(rs.getString("FAX1"));
        txtFax2.setText(rs.getString("FAX2"));
        txtComment.setText(rs.getString("CUST_COMMENT"));
        txtEmail.setText(rs.getString("EMAIL"));
        txtCity.setText(rs.getString("CITY"));
        txtCounty.setText(rs.getString("COUNTY"));
        txtCountry.setText(rs.getString("COUNTRY"));
        cboCustomerType.setSelectedIndex(rs.getInt("CUST_FLAG"));
        cboStore1.setSelectedData(rs.getString("STORE_ID"));
        String itemLabel = cboCustomerType.getSelectedItem().toString();

//        if (itemLabel == "Regular") {
          lblName1.setText(lang.getString("FirstName")+":");
          lblName2.setText(lang.getString("LastName")+":");
//        }else{
//          lblName1.setText("Customer Name:");
//          lblName2.setText("Abbreviation Name:");
//        }
      }
      rs.getStatement().close();

    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    String firstName = txtName.getText().trim();
    String lastName = txtAbbrName.getText().trim();
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
    String createdDate = ut.getSystemDate(1);
    String contactName = txtContactName.getText().trim();
    String sTemp = new String();
    String sBirthday = txtBirthday.getText();
    String custFlag = "" + cboCustomerType.getSelectedIndex();

    if (firstName.equals("")){
      sTemp=lblName1.getText();
      sTemp=sTemp.substring(0,(sTemp.length()-1));
      ut.showMessage(frmMain,lang.getString("TT001") ,sTemp + lang.getString("MS1195_NotNull"));
      txtName.requestFocus(true);
      return;
    }

    if (lastName.equals("")){
       sTemp=lblName2.getText();
       sTemp=sTemp.substring(0,(sTemp.length()-1));
       ut.showMessage(frmMain,lang.getString("TT001"),sTemp + lang.getString("MS1195_NotNull"));
       txtAbbrName.requestFocus(true);
       return;
    }

    if (address1.equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1041_AddressNotNull"));
      txtAddress1.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(firstName,120)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1068_CustomerNameTooLong"));
      txtName.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(lastName,120)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1069_AbbreviationNameTooLong"));
      txtAbbrName.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(address1,150)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1050_AddressTooLong"));
      txtAddress1.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(address2,150)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1050_AddressTooLong"));
      txtAddress2.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(homeNo,15)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1051_HomePhoneTooLong"));
      txtHomeNo.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(workNo,15)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1052_WorkPhoneTooLong"));
      txtWorkNo.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(mobileNo,15)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1053_MobilePhoneTooLong"));
      txtMobileNo.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(email,125)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1054_EmailTooLong"));
      txtEmail.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(city,50)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1055_CityTooLong"));
      txtCity.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(county,50)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1056_CountyTooLong"));
      txtCounty.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(country,50)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1056_CountryTooLong"));
      txtCountry.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(fax1,15)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1071_Fax1TooLong"));
      txtFax1.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(fax2,15)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1048_FirstNameTooLong"));
      txtFax2.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(custComment,250)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1073_CommentTooLong"));
      txtComment.requestFocus(true);
      return;
    }

    if (!ut.checkDate(sBirthday, "/") && !sBirthday.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1060_WrongBirthday"));
      txtBirthday.requestFocus(true);
      return;
    }

    if (ut.checkPhoneNumber(homeNo) == false){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1057_WrongHomePhoneNumber"));
      txtHomeNo.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(workNo) == false){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1058_WrongWorkPhoneNumber"));
      txtWorkNo.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(mobileNo) == false){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1059_WrongCellPhoneNumber"));
      txtMobileNo.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(fax1) == false){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1074_WrongFax1Number"));
      txtFax1.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(fax2) == false){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1075_WrongFax2Number"));
      txtFax2.requestFocus(true);
      return;
    }

    if (sBirthday.equals("")) {
      sBirthday = "1/1/1900";
    }

    String sql = "Update BTM_IM_CUSTOMER set first_name ='" + firstName +
        "', last_name='" + lastName + "',contact_name = '" + contactName+ "',address1 ='" + address1 +
        "', address2 ='" + address2 + "', home_no ='" + homeNo +
        "', work_no ='" + workNo + "', mobile_no ='" + mobileNo +
        "', email ='" + email + "', city ='" + city + "', county ='" + county +
        "', country ='" + country + "', fax1 ='" + fax1 + "', fax2 ='" + fax2 +
        "', cust_comment ='" + custComment + "',created_date = to_date('" + ut.getSystemDate() + "','yyyy-MM-dd'), birthday = to_date('" + sBirthday + "','DD/MM/YY'),cust_flag='" + custFlag + "',store_id="+cboStore1.getSelectedData().trim()+" where cust_id = '" + txtCustomerID.getText() + "'";
//    System.out.println(sql);
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      DAL.executeUpdate(sql);
      stmt.close();
    }catch(Exception ex){};
    while (dm.getRowCount()>0){
      dm.removeRow(0);
    }
    //Hien thi dialog search
    DialogCustomerSearch dlgCustomerSearch = new DialogCustomerSearch(frmMain,lang.getString("TT0097"),true,frmMain,frmMain.customerBusObject);
    dlgCustomerSearch.setValue(frmMain.customerBusObject.getCustID().toString(),frmMain.customerBusObject.getCustType().toString(),
                               frmMain.customerBusObject.getFirstName().toString(),frmMain.customerBusObject.getLastName().toString(),
                               frmMain.customerBusObject.getContactName().toString(),frmMain.customerBusObject.getAddress1(),
                               frmMain.customerBusObject.getHomePhone().toString(),frmMain.customerBusObject.getWorkPhone(),
                               frmMain.customerBusObject.getCellPhone().toString(),frmMain.customerBusObject.getCountry().toString(),
                               frmMain.customerBusObject.getCity().toString(),frmMain.customerBusObject.getRow().toString(),frmMain.customerBusObject.getBirthday().toString());
    dlgCustomerSearch.searchDataCustomer(String.valueOf(frmMain.customerBusObject.getRow().toString()));
    dlgCustomerSearch.txtRowLimit1.setText("" + frmMain.pnlCustomerSetup.rowNum);
    dlgCustomerSearch.setVisible(true);
     if (dlgCustomerSearch.done == false){
       frmMain.showScreen(Constant.SCRS_CUSTOMER_SETUP);
     }else {
       showData(dlgCustomerSearch.getData());
       txtName.requestFocus(true);
     }
  }

  void btnDone_actionPerformed(ActionEvent e) {
    while (dm.getRowCount()>0){
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
    txtBirthday.setText("");
    frmMain.showCustomerSearch();
    frmMain.showScreen(Constant.SCRS_CUSTOMER_SEARCH);
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    DialogCustomerSearch dlgCustomerSearch = new DialogCustomerSearch(frmMain,lang.getString("TT0097"),true,frmMain,frmMain.customerBusObject);
    dlgCustomerSearch.setValue(frmMain.customerBusObject.getCustID().toString(),frmMain.customerBusObject.getCustType().toString(),
                               frmMain.customerBusObject.getFirstName().toString(),frmMain.customerBusObject.getLastName().toString(),
                               frmMain.customerBusObject.getContactName().toString(),frmMain.customerBusObject.getAddress1(),
                               frmMain.customerBusObject.getHomePhone().toString(),frmMain.customerBusObject.getWorkPhone(),
                               frmMain.customerBusObject.getCellPhone().toString(),frmMain.customerBusObject.getCountry().toString(),
                               frmMain.customerBusObject.getCity().toString(),frmMain.customerBusObject.getRow().toString(),frmMain.customerBusObject.getBirthday().toString());
   dlgCustomerSearch.searchDataCustomer(String.valueOf(frmMain.customerBusObject.getRow().toString()));
   dlgCustomerSearch.txtRowLimit1.setText("" + frmMain.pnlCustomerSetup.rowNum);
   dlgCustomerSearch.setVisible(true);
    if (dlgCustomerSearch.done == false){
      frmMain.showScreen(Constant.SCRS_CUSTOMER_SETUP);
    }else {
      showData(dlgCustomerSearch.getData());
      txtName.requestFocus(true);
    }
  }
  void showData(String custID){
    while (dm.getRowCount()>0){
     dm.removeRow(0);
    }
    ResultSet rs = null;
    java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");

    try{
      String sql = "select CUST_ID, FIRST_NAME, LAST_NAME, CONTACT_NAME, ADDRESS1, ADDRESS2, HOME_NO, " +
          "WORK_NO, MOBILE_NO, FAX1, FAX2, CUST_COMMENT, EMAIL, CITY, COUNTY, COUNTRY, CUST_FLAG, BIRTHDAY,STORE_ID " +
          "from BTM_IM_CUSTOMER where CUST_ID = '" + custID + "'";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql);
      rs.beforeFirst();
      if (rs.next()){
        txtCustomerID.setText(rs.getString("CUST_ID"));
        txtName.setText(rs.getString("FIRST_NAME"));
        txtAbbrName.setText(rs.getString("LAST_NAME"));
        txtContactName.setText(rs.getString("CONTACT_NAME"));
        txtAddress1.setText(rs.getString("ADDRESS1"));
        txtAddress2.setText(rs.getString("ADDRESS2"));
        txtHomeNo.setText(rs.getString("HOME_NO"));
        txtWorkNo.setText(rs.getString("WORK_NO"));
        txtMobileNo.setText(rs.getString("MOBILE_NO"));
        txtFax1.setText(rs.getString("FAX1"));
        txtFax2.setText(rs.getString("FAX2"));
        txtComment.setText(rs.getString("CUST_COMMENT"));
        txtEmail.setText(rs.getString("EMAIL"));
        txtCity.setText(rs.getString("CITY"));
        txtCounty.setText(rs.getString("COUNTY"));
        txtCountry.setText(rs.getString("COUNTRY"));
        cboCustomerType.setSelectedIndex(rs.getInt("CUST_FLAG"));
        cboStore1.setSelectedData(rs.getString("STORE_ID"));
        txtBirthday.setText("" + fd.format(rs.getDate("BIRTHDAY")));

        String itemLabel = cboCustomerType.getSelectedItem().toString();
//        if (itemLabel == "Franchise") {
//          lblName1.setText("Customer Name:");
//          lblName2.setText("Abbreviation Name:");
//        }
        if (itemLabel == "Regular") {
          lblName1.setText(lang.getString("FirstName")+":");
          lblName2.setText(lang.getString("LastName")+":");
        }
      }
    }catch(Exception e){
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
  }


  void txtName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtName,Customer.LEN_CUST_NAME);
    navigationComp(e,txtAbbrName);
  }
  void navigationComp(KeyEvent e, JTextField txt){
      if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
        txt.requestFocus(true);
        txt.selectAll();
      }
    }

  void txtAbbrName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAbbrName,Customer.LEN_CUST_ABB_NAME);
    navigationComp(e,txtContactName);
  }
  void txtContactName_keyTyped(KeyEvent e){
    ut.limitLenjTextField(e,txtContactName,Customer.LEN_CUST_NAME);
    navigationComp(e,txtHomeNo);
  }
  void txtAddress1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAddress1,Customer.LEN_CUST_ADDRESS);
    navigationComp(e,txtAddress2);
  }

  void txtAddress2_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAddress2,Customer.LEN_CUST_ADDRESS);
    navigationComp(e,txtFax1);
  }

  void txtHomeNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtHomeNo,Customer.LEN_CUST_PHONE);
    ut.checkPhone(e);
    navigationComp(e,txtMobileNo);
  }

  void txtWorkNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtWorkNo,Customer.LEN_CUST_PHONE);
    ut.checkPhone(e);
    navigationComp(e,txtBirthday);
  }

  void txtMobileNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtMobileNo,Customer.LEN_CUST_PHONE);
    ut.checkPhone(e);
    navigationComp(e,txtWorkNo);
  }

  void txtEmail_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtEmail,Customer.LEN_CUST_EMAIL);
    navigationComp(e,txtAddress1);
  }

  void txtCity_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCity,Customer.LEN_CUST_CITY);
    navigationComp(e,txtComment);
  }

  void txtCounty_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCounty,Customer.LEN_CUST_COUNTY);
    navigationComp(e,txtCity);
  }

  void txtCountry_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCountry,Customer.LEN_CUST_COUNTRY);
    navigationComp(e,txtCounty);
  }
  void navigationComp(KeyEvent e, JTextArea txtArea){
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      txtArea.requestFocus(true);
      txtArea.selectAll();
    }
  }


  void txtFax1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtFax1,Customer.LEN_CUST_FAX);
    ut.checkPhone(e);
    navigationComp(e,txtFax2);
  }

  void txtFax2_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtFax2,Customer.LEN_CUST_FAX);
    ut.checkPhone(e);
    navigationComp(e,txtCountry);
  }

  void txtComment_keyTyped(KeyEvent e) {
    ut.limitLenjTextArea(e,txtComment,Customer.LEN_CUST_COMMENT);
    if(e.getKeyChar()==KeyEvent.VK_ENTER){
      btnAdd.doClick();
    }
  }

  void txtRowLimit1_keyTyped(KeyEvent e) {
    if (ut.checkPhoneNumber(String.valueOf(e.getKeyChar())) == false){
      e.consume();
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
   // ESCAPE
   key = new Integer(KeyEvent.VK_ENTER);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
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
      if (identifier.intValue() == KeyEvent.VK_F1 ) {
        btnAdd.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F2 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
      }
    }

  void table_mousePressed(MouseEvent e) {
//    table.requestFocus();
  }

  void cboCustomerType_itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED){
         String itemLabel =  e.getItem().toString();
//         if (!itemLabel.equalsIgnoreCase("Regular")){
//           lblName1.setText("Customer Name:");
//           lblName3.setText("Abbreviation Name:");
//         }
//         if (itemLabel == "Regular"){
           lblName1.setText(lang.getString("CustomerName")+":");
           lblName3.setText(lang.getString("LastName")+":");
//         }
       }
  }

  void btnDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnDate.getX() + posXFrame + 90;
    posY = this.btnDate.getY() + posYFrame + 90;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseDateBirth"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      String sNow = ut.getSystemDate(1);
      if (ut.compareDate(date, sNow)) {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1060_WrongBirthday")
);
        txtBirthday.requestFocus(true);
        return;
      }
      else {
        this.txtBirthday.setText(date);
        this.txtEmail.requestFocus(true);
      }
    }
  }

  void txtBirthday_keyTyped(KeyEvent e) {
    if(e.getKeyChar()==KeyEvent.VK_ENTER){
      btnDate.requestFocus(true);
    }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnCancel.doClick();
               }
  }

}

class PanelCustomerModify_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerModify adaptee;

  PanelCustomerModify_btnAdd_actionAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelCustomerModify_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerModify adaptee;

  PanelCustomerModify_btnCancel_actionAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelCustomerModify_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_table_mouseAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelCustomerModify_cboCustomerType_itemAdapter implements java.awt.event.ItemListener {
  PanelCustomerModify adaptee;

  PanelCustomerModify_cboCustomerType_itemAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.cboCustomerType_itemStateChanged(e);
  }
}

class PanelCustomerModify_txtName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtName_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtName_keyTyped(e);
  }
}

class PanelCustomerModify_txtAbbrName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtAbbrName_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAbbrName_keyTyped(e);
  }
}

class PanelCustomerModify_txtContactName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtContactName_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtContactName_keyTyped(e);
  }
}

class PanelCustomerModify_txtAddress1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtAddress1_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress1_keyTyped(e);
  }
}

class PanelCustomerModify_txtAddress2_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtAddress2_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress2_keyTyped(e);
  }
}

class PanelCustomerModify_txtHomeNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtHomeNo_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtHomeNo_keyTyped(e);
  }
}

class PanelCustomerModify_txtComment_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtComment_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtComment_keyTyped(e);
  }
}

class PanelCustomerModify_txtWorkNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtWorkNo_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtWorkNo_keyTyped(e);
  }
}

class PanelCustomerModify_txtMobileNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtMobileNo_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtMobileNo_keyTyped(e);
  }
}

class PanelCustomerModify_txtFax1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtFax1_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtFax1_keyTyped(e);
  }
}

class PanelCustomerModify_txtFax2_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtFax2_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtFax2_keyTyped(e);
  }
}

class PanelCustomerModify_txtEmail_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtEmail_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEmail_keyTyped(e);
  }
}

class PanelCustomerModify_txtCountry_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtCountry_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCountry_keyTyped(e);
  }
}

class PanelCustomerModify_txtCounty_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtCounty_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCounty_keyTyped(e);
  }
}

class PanelCustomerModify_txtCity_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtCity_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCity_keyTyped(e);
  }
}

class PanelCustomerModify_btnDate_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerModify adaptee;

  PanelCustomerModify_btnDate_actionAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class PanelCustomerModify_txtBirthday_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_txtBirthday_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtBirthday_keyTyped(e);
  }
}

class PanelCustomerModify_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerModify adaptee;

  PanelCustomerModify_table_keyAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

