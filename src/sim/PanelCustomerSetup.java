package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import java.util.Vector;
import btm.attr.*;
import java.awt.Dimension;
import java.util.ResourceBundle;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Customer Setup</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Vo Ha Thanh Huy
 * @version 1.0
 */

public class PanelCustomerSetup extends JPanel {
  DataAccessLayer DAL;
  FrameMainSim frmMain;
  Statement stmt = null;
   boolean flagSetHotKeyForAdd = true;// true if Add screen, false if modify screen
  Customer cust=new Customer();
  int rowTableSelect=0;
  int rowNum = 5;
  CustomerBusObj cusBusObj = new CustomerBusObj();
  Utils ut = new Utils();
  BorderLayout borderLayout1 = new BorderLayout();
  Vector vSql = new Vector();
  Vector vArrayObject = new Vector();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel lblName2 = new JLabel();
  JLabel lblName1 = new JLabel();
  JLabel jLabel7 = new JLabel();
  //
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel13 = new JLabel();
  //
  JTextField txtMobileNo = new JTextField();
  JTextField txtWorkNo = new JTextField();
  JTextField txtHomeNo = new JTextField();
  JTextField txtAddress2 = new JTextField();
  JTextField txtAddress1 = new JTextField();
  JTextField txtAbbrName = new JTextField();
  JTextField txtName = new JTextField();
  //
  JTextField txtEmail = new JTextField();
  JTextField txtCity = new JTextField();
  JTextField txtCounty = new JTextField();
  JTextField txtCountry = new JTextField();
  JTextField txtFax1 = new JTextField();
  JTextField txtFax2 = new JTextField();
  BorderLayout borderLayout3 = new BorderLayout();
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
  BJTable table = new BJTable(dm,true){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPanel14 = new JPanel();
  JScrollPane jScrollPane2 = new JScrollPane();
  JPanel jPanel15 = new JPanel();
  BJButton btnCancel = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  BJButton btnClear = new BJButton();
  BJButton btnModify = new BJButton();
  //
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  BJButton btnDelete = new BJButton();
  JLabel jLabel16 = new JLabel();
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel jLabel18 = new JLabel();
  JTextField txtCustomerID = new JTextField();
  JComboBox cboCustomerType = new JComboBox();
  FlowLayout flowLayout2 = new FlowLayout();
  JLabel jLabel19 = new JLabel();
  JLabel jLabel20 = new JLabel();
  JTextField txtContactName = new JTextField();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  FlowLayout flowLayout5 = new FlowLayout();
  JPanel jPanel16 = new JPanel();
  JLabel jLabel110 = new JLabel();
  FlowLayout flowLayout6 = new FlowLayout();
  JLabel jLabel17 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JTextField txtBirthday = new JTextField();
  JButton btnDate = new JButton();
  JLabel jLabel14 = new JLabel();
  BJComboBox cboStore = new BJComboBox();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelCustomerSetup(FrameMainSim frmMain) {
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
    //Edit Item Customer Type
    cboCustomerType.addItem("Regular"); //cus_flag=0
    cboCustomerType.addItem("Home Employee");//cus_flag=1
    cboCustomerType.addItem("Whole Sale");//cus_flag=2
    cboCustomerType.addItem("Other");//cus_flag=3
//    cboCustomerType.addItem("Franchise");
//    cboCustomerType.addItem("Whole Sales");
//    cboCustomerType.addItem("Other");

    //
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setDebugGraphicsOptions(0);
    jPanel1.setMinimumSize(new Dimension(620, 51));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(null);
    jPanel2.setBackground(SystemColor.control);
    jPanel2.setBorder(null);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setOpaque(true);
    jPanel2.setPreferredSize(new Dimension(450, 200));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(350, 320));
    jPanel3.setLayout(borderLayout3);
    jPanel4.setDebugGraphicsOptions(0);
    jPanel4.setPreferredSize(new Dimension(140, 50));
    FlowLayout fl4=new FlowLayout(FlowLayout.LEFT);
    jPanel4.setLayout(fl4);
    jPanel6.setMaximumSize(new Dimension(32767, 32767));
    jPanel6.setPreferredSize(new Dimension(100,10));
    jPanel6.setLayout(flowLayout4);
    jPanel8.setPreferredSize(new Dimension(80, 5));
    jPanel7.setPreferredSize(new Dimension(250,10));
    FlowLayout fl7=new FlowLayout(FlowLayout.LEFT);
    jPanel7.setLayout(fl7);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(90, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel1.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel1.setText(lang.getString("WorkPhone")+":");
    jLabel2.setText(lang.getString("HomePhone")+":");
    jLabel2.setPreferredSize(new Dimension(110, 20));
    jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel2.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setText(lang.getString("Address2")+":");
    jLabel3.setPreferredSize(new Dimension(90, 20));
    jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel3.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("Address1")+":");
    jLabel4.setPreferredSize(new Dimension(90, 20));
    jLabel4.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel4.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblName2.setText(lang.getString("LastName")+":");
    lblName2.setPreferredSize(new Dimension(110, 20));
    lblName2.setHorizontalAlignment(SwingConstants.LEFT);
    lblName2.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblName2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblName1.setText(lang.getString("FirstName")+":");
    lblName1.setPreferredSize(new Dimension(110, 20));
    lblName1.setHorizontalAlignment(SwingConstants.LEFT);
    lblName1.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblName1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel7.setText(lang.getString("CellPhone")+":");
    jLabel7.setPreferredSize(new Dimension(90, 20));
    jLabel7.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel7.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//
    jLabel8.setText(lang.getString("Email")+":");
    jLabel8.setPreferredSize(new Dimension(90, 20));
    jLabel8.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel9.setText(lang.getString("City")+":");
    jLabel9.setPreferredSize(new Dimension(90, 20));
    jLabel9.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel10.setText(lang.getString("County")+":");
    jLabel10.setPreferredSize(new Dimension(90, 20));
    jLabel10.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel11.setText(lang.getString("Country")+":");
    jLabel11.setPreferredSize(new Dimension(90, 20));
    jLabel11.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel12.setText(lang.getString("Fax1")+":");
    jLabel12.setPreferredSize(new Dimension(90, 20));
    jLabel12.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel12.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel13.setText(lang.getString("Store")+":");
    jLabel13.setPreferredSize(new Dimension(90, 20));
    jLabel13.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//
    txtMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtMobileNo.setPreferredSize(new Dimension(200, 20));
    txtMobileNo.setSelectionStart(0);
    txtMobileNo.setText("");
    txtMobileNo.setBounds(new Rectangle(25, 231, 200, 20));
    txtMobileNo.addKeyListener(new PanelCustomerSetup_txtMobileNo_keyAdapter(this));
    txtWorkNo.setText("");
    txtWorkNo.setBounds(new Rectangle(25, 206, 200, 20));
    txtWorkNo.addKeyListener(new PanelCustomerSetup_txtWorkNo_keyAdapter(this));
    txtWorkNo.setSelectionStart(0);
    txtWorkNo.setPreferredSize(new Dimension(200, 20));
    txtWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtHomeNo.setText("");
    txtHomeNo.addActionListener(new PanelCustomerSetup_txtHomeNo_actionAdapter(this));
    txtHomeNo.addKeyListener(new PanelCustomerSetup_txtHomeNo_keyAdapter(this));
    txtHomeNo.setSelectionStart(0);
    txtHomeNo.setPreferredSize(new Dimension(200, 20));
    txtHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress2.setText("");
    txtAddress2.addActionListener(new PanelCustomerSetup_txtAddress2_actionAdapter(this));
    txtAddress2.addKeyListener(new PanelCustomerSetup_txtAddress2_keyAdapter(this));
    txtAddress2.setSelectionStart(0);
    txtAddress2.setPreferredSize(new Dimension(200, 20));
    txtAddress2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress1.setText("");
    txtAddress1.addActionListener(new PanelCustomerSetup_txtAddress1_actionAdapter(this));
    txtAddress1.addKeyListener(new PanelCustomerSetup_txtAddress1_keyAdapter(this));
    txtAddress1.setSelectionStart(0);
    txtAddress1.setPreferredSize(new Dimension(200, 20));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAbbrName.setText("");
    txtAbbrName.addKeyListener(new PanelCustomerSetup_txtAbbrName_keyAdapter(this));
    txtAbbrName.setSelectionStart(0);
    txtAbbrName.setPreferredSize(new Dimension(200, 20));
    txtAbbrName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtName.setText("");
    txtName.addKeyListener(new PanelCustomerSetup_txtName_keyAdapter(this));
    txtName.setSelectionStart(0);
    txtName.setPreferredSize(new Dimension(200, 20));
    txtName.setSelectedTextColor(Color.white);
    txtName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmail.setPreferredSize(new Dimension(200, 20));
    txtEmail.setSelectionStart(0);
    txtEmail.setText("");
    txtEmail.addKeyListener(new PanelCustomerSetup_txtEmail_keyAdapter(this));
    txtCity.setText("");
    txtCity.addKeyListener(new PanelCustomerSetup_txtCity_keyAdapter(this));
    txtCity.setSelectionStart(0);
    txtCity.setPreferredSize(new Dimension(200, 20));
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCounty.setText("");
    txtCounty.addKeyListener(new PanelCustomerSetup_txtCounty_keyAdapter(this));
    txtCounty.setSelectionStart(0);
    txtCounty.setPreferredSize(new Dimension(200, 20));
    txtCounty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCountry.setText("");
    txtCountry.addKeyListener(new PanelCustomerSetup_txtCountry_keyAdapter(this));
    txtCountry.setSelectionStart(0);
    txtCountry.setPreferredSize(new Dimension(200, 20));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtFax1.setText("");
    txtFax1.addKeyListener(new PanelCustomerSetup_txtFax1_keyAdapter(this));
    txtFax1.setSelectionStart(0);
    txtFax1.setPreferredSize(new Dimension(200, 20));
    txtFax1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtFax2.setText("");
    txtFax2.addKeyListener(new PanelCustomerSetup_txtFax2_keyAdapter(this));
    txtFax2.setSelectionStart(0);
    txtFax2.setPreferredSize(new Dimension(200, 20));
    txtFax2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//
    jPanel5.setPreferredSize(new Dimension(250, 10));
    FlowLayout fl5=new FlowLayout(FlowLayout.LEFT);
    jPanel5.setLayout(fl5);
    jPanel9.setPreferredSize(new Dimension(800, 273));
    jPanel9.setLayout(borderLayout4);
    jPanel10.setPreferredSize(new Dimension(800, 50));
    jPanel10.setLayout(borderLayout5);
    jPanel11.setPreferredSize(new Dimension(800, 218));
    jPanel11.setLayout(gridLayout2);
    jPanel12.setPreferredSize(new Dimension(119, 50));
    jPanel12.setLayout(null);
    jPanel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    jPanel13.setPreferredSize(new Dimension(650, 50));
    jPanel13.setLayout(flowLayout1);
    txtComment.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtComment.setMinimumSize(new Dimension(635, 15));
    txtComment.setPreferredSize(new Dimension(630, 15));
    txtComment.setText("");
    txtComment.setLineWrap(true);
    txtComment.setWrapStyleWord(true);
        txtComment.addKeyListener(new PanelCustomerSetup_txtComment_keyAdapter(this));
    jLabel15.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel15.setPreferredSize(new Dimension(110, 15));
    jLabel15.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel15.setText(lang.getString("Comment")+":");
    jLabel15.setBounds(new Rectangle(5, 25, 105, 15));
    jPanel14.setPreferredSize(new Dimension(800, 2));
    jScrollPane2.setPreferredSize(new Dimension(611, 45));
    jScrollPane2.setRequestFocusEnabled(true);
    jScrollPane1.setPreferredSize(new Dimension(800, 240));
    jPanel15.setBackground(new Color(121, 152, 198));
    jPanel15.setMinimumSize(new Dimension(610, 51));
    jPanel15.setPreferredSize(new Dimension(750, 50));
    jPanel15.setBounds(new Rectangle(-9, 0, 816, 50));
    jPanel15.setLayout(flowLayout3);
    btnCancel.setBounds(new Rectangle(541, 4, 120, 37));
    btnCancel.setPreferredSize(new Dimension(120, 37));

    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelCustomerSetup_btnCancel_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</h" +
    "tml>");
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setBounds(new Rectangle(664, 4, 130, 37));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear")+" (F7)");
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</h" +
    "tml>");
    btnClear.addActionListener(new PanelCustomerSetup_btnClear_actionAdapter(this));

    btnSearch.addActionListener(new PanelCustomerSetup_btnSearch_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</h" +
    "tml>");
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify")+" (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</h" +
    "tml>");
    btnModify.addActionListener(new PanelCustomerSetup_btnModify_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.addActionListener(new PanelCustomerSetup_btnAdd_actionAdapter(this));
    btnDone.setPreferredSize(new Dimension(120, 37));
//    btnDone.setPreferredSize(new Dimension(80, 35));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</h" +
    "tml>");
    btnDone.addActionListener(new PanelCustomerSetup_btnDone_actionAdapter(this));
    btnDelete.setPreferredSize(new Dimension(120, 37));
//    btnDelete.setPreferredSize(new Dimension(80, 35));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</h" +
    "tml>");
    btnDelete.addActionListener(new PanelCustomerSetup_btnDelete_actionAdapter(this));
    table.addMouseListener(new PanelCustomerSetup_table_mouseAdapter(this));
    jLabel16.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel16.setForeground(Color.red);
    jLabel16.setText("(*)");
    jLabel18.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel18.setForeground(Color.black);
    jLabel18.setPreferredSize(new Dimension(110, 20));
    jLabel18.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel18.setText(lang.getString("CustomerID")+":");
    txtCustomerID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCustomerID.setPreferredSize(new Dimension(200, 20));
    txtCustomerID.setCaretPosition(0);
    txtCustomerID.setEditable(false);
    cboCustomerType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboCustomerType.setPreferredSize(new Dimension(200, 20));
    cboCustomerType.addKeyListener(new PanelCustomerSetup_cboCustomerType_keyAdapter(this));
    cboCustomerType.addItemListener(new PanelCustomerSetup_cboCustomerType_actionAdapter(this));
    jLabel19.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel19.setPreferredSize(new Dimension(110, 20));
    jLabel19.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel19.setText(lang.getString("CustomerType")+":");
    jLabel20.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel20.setPreferredSize(new Dimension(110, 20));
    jLabel20.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel20.setText(lang.getString("ContactName")+":");
    txtContactName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtContactName.setForeground(Color.black);
    txtContactName.setPreferredSize(new Dimension(200, 20));
    txtContactName.setText("");
    txtContactName.addKeyListener(new PanelCustomerSetup_txtContactName_keyAdapter(this));
    jPanel16.setOpaque(true);
    jPanel16.setPreferredSize(new Dimension(5, 45));
    jLabel110.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel110.setForeground(Color.red);
    jLabel110.setText("(*)");
    jLabel17.setText("(*)");
    jLabel17.setForeground(Color.red);
    jLabel17.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setPreferredSize(new Dimension(90, 20));
    jLabel5.setText(lang.getString("Birthday")+":");
    txtBirthday.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtBirthday.setPreferredSize(new Dimension(170, 20));
    txtBirthday.setText("");
    txtBirthday.addKeyListener(new PanelCustomerSetup_txtBirthday_keyAdapter(this));
    btnDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    btnDate.setPreferredSize(new Dimension(25, 21));
    btnDate.setText("...");
    btnDate.addActionListener(new PanelCustomerSetup_btnDate_actionAdapter(this));
    table.addKeyListener(new PanelCustomerSetup_table_keyAdapter(this));
    jLabel14.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel14.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel14.setPreferredSize(new Dimension(90, 20));
    jLabel14.setText(lang.getString("Fax2")+":");
    cboStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    cboStore.setMinimumSize(new Dimension(100, 21));
    cboStore.setPreferredSize(new Dimension(160, 21));
    this.add(jPanel1, BorderLayout.NORTH);
    jPanel15.add(btnDone, null);
    jPanel15.add(btnAdd, null);
    jPanel15.add(btnSearch, null);
    jPanel15.add(btnModify,null);

    jPanel15.add(btnClear, null);
    jPanel15.add(btnDelete, null);

    jPanel15.add(btnCancel, null);
    jPanel1.add(jPanel15, null);
    this.add(jPanel2, BorderLayout.WEST);
    jPanel2.add(jPanel4, BorderLayout.WEST);
    jPanel4.add(jLabel18, null);
    jPanel4.add(jLabel19, null);
    jPanel4.add(lblName1, null);
    jPanel4.add(lblName2, null);
    jPanel4.add(jLabel20, null);
    jPanel4.add(jLabel2, null);
    jPanel4.add(jLabel7, null);
    jPanel4.add(jLabel1,null);
    jPanel4.add(jLabel5, null);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel2.add(jPanel8,BorderLayout.EAST);
    jPanel5.add(txtCustomerID, null);
    jPanel5.add(cboCustomerType, null);
    jPanel5.add(txtName, null);
    jPanel5.add(jLabel110, null);
    jPanel5.add(txtAbbrName, null);
    jPanel5.add(jLabel16, null);
    jPanel5.add(txtContactName, null);
    jPanel5.add(txtHomeNo, null);
    jPanel5.add(txtMobileNo, null);
    jPanel5.add(txtWorkNo, null);
    jPanel5.add(txtBirthday, null);
    jPanel5.add(btnDate, null);
    this.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jPanel6, BorderLayout.WEST);
    jPanel6.add(jLabel8, null);
    jPanel6.add(jLabel4, null);
    jPanel6.add(jLabel3, null);
    jPanel6.add(jLabel9, null);
    jPanel6.add(jLabel10, null);
    jPanel6.add(jLabel11, null);
    jPanel6.add(jLabel12, null);
    jPanel6.add(jLabel14, null);
    jPanel6.add(jLabel13, null);
    jPanel3.add(jPanel7, BorderLayout.CENTER);
    jPanel7.add(txtEmail,null);
    jPanel7.add(txtAddress1,null);
    jPanel7.add(jLabel17, null);
    jPanel7.add(txtAddress2,null);
    jPanel7.add(txtCity, null);
    jPanel7.add(txtCounty,null);
    jPanel7.add(txtCountry, null);
    jPanel7.add(txtFax1,null);
    jPanel7.add(txtFax2,null);
    jPanel7.add(cboStore, null);
    this.add(jPanel9,BorderLayout.SOUTH);
    jPanel9.add(jPanel11, BorderLayout.SOUTH);
    jPanel11.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
//    table.setRowHeight(30);
//    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    jPanel9.add(jPanel10, BorderLayout.NORTH);
    jPanel10.add(jPanel12, BorderLayout.WEST);
    jPanel12.add(jLabel15, null);
    jPanel10.add(jPanel13, BorderLayout.CENTER);
    jPanel13.add(jScrollPane2, null);
    jPanel13.add(jPanel16, null);
    jScrollPane2.getViewport().add(txtComment,null);
    jPanel9.add(jPanel14, BorderLayout.CENTER);
    String[] columnNames = new String[]{lang.getString("CustomerID"),lang.getString("Name"),lang.getString("ContactName"),lang.getString("WorkPhone")};
    dm.setDataVector(new Object[][]{},columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn(lang.getString("CustomerID")).setPreferredWidth(150);
    table.getColumn(lang.getString("Name")).setPreferredWidth(300);
    table.getColumn(lang.getString("ContactName")).setPreferredWidth(350);
    table.getColumn(lang.getString("WorkPhone")).setPreferredWidth(150);
    //Visible btnModify
    btnModify.setVisible(false);
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
    resetButtonLabel();

    //Add store to combo box
    initData();
  }
  void initData(){
    String sql="";
    ResultSet rs = null;
    try{
      sql = "select STORE_ID,NAME from BTM_IM_STORE order by upper(name)";
      rs = DAL.executeScrollQueries(sql);
      cboStore.setData(rs);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  //reset button label
 void resetButtonLabel(){
//   btnSearch.setText("<html><center><b>Search</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
// "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
//    btnClear.setText("<html><center><b>Clear</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
// "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html>");
//    btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
// "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F6</h" +
// "tml>");
  flagSetHotKeyForAdd = true;
 }

  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_CUSTOMER_SETUP);
    btnAdd.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnModify.setEnabled(er.getModify());
    btnSearch.setEnabled(er.getSearch());
    //Edit txtCustomerID
    txtCustomerID.setText(cusBusObj.getCustomerIdCanInsert(DAL));
  }


/*  String getCustomerIdCanInsert(){
   String id = new String();
    try {
      ResultSet rs = DAL.executeScrollQueries("Select CUST_ID from BTM_IM_CUSTOMER Order by CUST_ID DESC");
      rs.beforeFirst();
      if (!rs.next()){
        return "0000000001";
      }
      long iTemp = rs.getLong("CUST_ID")+1;
      //Add yero
      String st =null;
      int pos1=0;
      int pos2=0;
      st=ut.addYeroCode(10) + iTemp;
      pos1=st.length()- 10;
      pos2=st.length();
      if (pos1 < 0) {
        st = String.valueOf(iTemp).toString();
        return st;
      }
      st=st.substring(pos1, pos2);
      id=st;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }

   return id;
  }*/



void btnAdd_actionPerformed(ActionEvent e) {
    String firstName = txtName.getText().trim();
    String lastName = txtAbbrName.getText().trim();
    String contactName = txtContactName.getText().trim();
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
    String sTemp=new String();
    String sBirthday = txtBirthday.getText().trim();

    if (firstName.equals("")){
      sTemp=lblName1.getText();
      sTemp=sTemp.substring(0,(sTemp.length()-1));
      ut.showMessage(frmMain,lang.getString("TT001") ,sTemp + lang.getString("MS1178_NotNull"));
      txtName.requestFocus(true);
      return;
    }

    if (lastName.equals("")){
       sTemp=lblName2.getText();
       sTemp=sTemp.substring(0,(sTemp.length()-1));
       ut.showMessage(frmMain,lang.getString("TT001"),sTemp + lang.getString("MS1178_NotNull"));
       txtAbbrName.requestFocus(true);
       return;
    }

    if (address1.equals("")){
       ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1041_AddressNotNull"));
       txtAddress1.requestFocus(true);
       return;
    }

    if (cusBusObj.checkLength(firstName,120)){
      String stName1=new String();
      stName1 = lblName1.getText();
      stName1=stName1.replaceAll(":"," ");
      ut.showMessage(frmMain,lang.getString("TT001"),stName1+lang.getString("MS1177_TooLong"));
      txtName.requestFocus(true);
      return;
    }

    if (cusBusObj.checkLength(lastName,120)){
      String stName2=new String();
      stName2 = lblName2.getText();
      stName2=stName2.replaceAll(":"," ");
      ut.showMessage(frmMain,lang.getString("TT001"),stName2+lang.getString("MS1177_TooLong"));
      txtAbbrName.requestFocus(true);
      return;
    }

    if (cusBusObj.checkLength(contactName,120)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1177_TooLong"));
      txtAddress2.requestFocus(true);
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
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1070_CountyTooLong"));
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
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1072_Fax2TooLong"));
      txtFax2.requestFocus(true);
      return;
    }

    if (cusBusObj.checkLength(custComment,250)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1073_CommentTooLong"));
      txtComment.requestFocus(true);
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

    if (!ut.checkDate(sBirthday, "/") && !sBirthday.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1060_WrongBirthday"));
      txtBirthday.requestFocus(true);
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
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1075_WrongFax2PhoneNumber"));
      txtFax2.requestFocus(true);
      return;
    }
//    //Reset Vector
//    if (table.getRowCount()==0){
//      vSql.removeAllElements();
//      txtCustomerID.setText(cusBusObj.getCustomerIdCanInsert(DAL));
//    }

    //Save object SQL
    Customer cs=new Customer(txtCustomerID.getText(),String.valueOf(cboCustomerType.getSelectedIndex()),firstName,
                             lastName,contactName,address1,address2,homeNo,workNo,mobileNo,fax1,fax2,email,
                             country,county,city,custComment, sBirthday,cboStore.getSelectedData().trim());
    vArrayObject.addElement(cs);
    //Add Table
    String strName= "";
    if (cboCustomerType.getSelectedIndex() == 0){
      strName = firstName + " " + lastName;
    }else{

      strName = firstName;
    }
    String[] rowData = new String[] {
      txtCustomerID.getText() , strName , contactName, workNo
    };
    dm.addRow(rowData);
//    str.installInTable(table, Color.lightGray, Color.white, null, null);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);
    //set focus for last row
     if(table.getRowCount()>0){
       table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
     }

     //show current row
     Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
     jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

    //Test Null
    if (lastName.equals("")){
      lastName = "null";
    }else{
      lastName = "'" + lastName + "'";
    }

    if (contactName.equals("")){
      contactName = "null";
    }else{
      contactName = "'" + contactName + "'";
    }

    if (address2.equals("")){
      address2 = "null";
    }else{
      address2 = "'" + address2 + "'";
    }

    if (homeNo.equals("")){
      homeNo = "null";
    }else{
      homeNo = "'" + homeNo + "'";
    }

    if (workNo.equals("")){
      workNo = "null";
    }else{
      workNo = "'" + workNo + "'";
    }

    if (mobileNo.equals("")){
      mobileNo = "null";
    }else{
      mobileNo = "'" + mobileNo + "'";
    }

    if (email.equals("")){
      email = "null";
    }else{
      email = "'" + email + "'";
    }

    if (city.equals("")){
      city = "null";
    }else{
      city = "'" + city + "'";
    }

    if (county.equals("")){
      county = "null";
    }else{
      county = "'" + county  + "'";
    }

    if (country.equals("")){
      country = "null";
    }else{
      country = "'" + country  + "'";
    }

    if (fax1.equals("")){
      fax1 = "null";
    }else{
      fax1 = "'" + fax1 +"'";
    }

    if (fax2.equals("")){
       fax2 = "null";
    }else{
      fax2 = "'" + fax2 + "'";
    }

    if (custComment.equals("")){
       custComment = "null";
    }else{
      custComment = "'" + custComment + "'";
    }

    if (sBirthday.equals("")) {
      sBirthday = "1/1/1900";
    }

    vSql.addElement("insert into BTM_IM_CUSTOMER values('" +
                    txtCustomerID.getText() +  "','" +
                    firstName +
                    "'," + lastName + "," + contactName + ",'" + address1 +
                    "'," + address2 +
                    "," + homeNo +
                    "," + workNo + "," + mobileNo + "," + fax1 + "," + fax2 +
                    "," + custComment + "," + email + "," +
                    city +
                    "," + county + "," + country + ",to_date('" +
                    createdDate +
                    "','DD-MM-YYYY'),'" + cboCustomerType.getSelectedIndex() +
                    "',to_date('" + sBirthday + "','DD-MM-YYYY')," + cboStore.getSelectedData().trim() +",0)");
//    System.out.println(vSql);
    if (table.getRowCount() == 0) {
      txtCustomerID.setText(cusBusObj.getCustomerIdCanInsert(DAL));
    }
    else {
      long lID = Long.parseLong(cusBusObj.getCustomerIdCanInsert(DAL))/10;
      lID += table.getRowCount();
      txtCustomerID.setText("" + lID + ut.getCheckDigitEAN13(String.valueOf(lID)));
    }


    //Reset Text
    txtAbbrName.setText("");
    txtAddress1.setText("");
    txtAddress2.setText("");
    txtCity.setText("");
    txtComment.setText("");
    txtContactName.setText("");
    txtCountry.setText("");
    txtCounty.setText("");
    txtEmail.setText("");
    txtFax1.setText("");
    txtFax2.setText("");
    txtHomeNo.setText("");
    txtMobileNo.setText("");
    txtName.setText("");
    txtWorkNo.setText("");
    txtBirthday.setText("");
    cboCustomerType.setSelectedIndex(0);
    cboStore.setSelectedIndex(0);
    lblName1.setText(lang.getString("FirstName")+":");
    lblName2.setText(lang.getString("LastName")+":");
    txtName.requestFocus(true);
  }

  void btnDone_actionPerformed(ActionEvent e) {
    if (!txtName.getText() .equals("") && table.getRowCount()==0) {
            int i = ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1179_AddDoneSaveData"),
                               DialogMessage.QUESTION_MESSAGE,
                               DialogMessage.YES_NO_OPTION);
            if(i==DialogMessage.YES_VALUE){
              return;
            }
    }

    if (vSql.isEmpty() == false){
      try{
        DAL.setBeginTrans(DAL.getConnection());
         stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        DAL.executeBatchQuery(vSql, stmt);
        DAL.setEndTrans(DAL.getConnection());
        stmt.close();
      }
      catch(Exception ex){}
      vSql.clear();
      vArrayObject.clear();
      flagSetHotKeyForAdd = true;
    }
    while (dm.getRowCount()>0){
      dm.removeRow(0);
    }
    txtAbbrName.setText("");
    txtAddress1.setText("");
    txtAddress2.setText("");
    txtCity.setText("");
    txtComment.setText("");
    txtContactName.setText("");
    txtCountry.setText("");
    txtCounty.setText("");
    txtEmail.setText("");
    txtFax1.setText("");
    txtFax2.setText("");
    txtHomeNo.setText("");
    txtMobileNo.setText("");
    txtName.setText("");
    txtWorkNo.setText("");
    txtBirthday.setText("");
    cboCustomerType.setSelectedIndex(0);
    cboStore.setSelectedIndex(0);
    lblName1.setText(lang.getString("FirstName")+":");
    lblName2.setText(lang.getString("LastName")+":");
    txtCustomerID.setText(cusBusObj.getCustomerIdCanInsert(DAL));
    btnModify.setVisible(false);
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showMerchanSystemButton();
  }

  void btnClear_actionPerformed(ActionEvent e){
    txtAbbrName.setText("");
    txtAddress1.setText("");
    txtAddress2.setText("");
    txtCity.setText("");
    txtComment.setText("");
    txtContactName.setText("");
    txtCountry.setText("");
    txtCounty.setText("");
    txtEmail.setText("");
    txtFax1.setText("");
    txtFax2.setText("");
    txtHomeNo.setText("");
    txtMobileNo.setText("");
    txtName.setText("");
    txtWorkNo.setText("");
    txtBirthday.setText("");
    cboCustomerType.setSelectedIndex(0);
    cboStore.setSelectedIndex(0);
    lblName1.setText(lang.getString("FirstName")+":");
    lblName2.setText(lang.getString("LastName")+":");
    btnModify.setVisible(false);
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
    flagSetHotKeyForAdd = true;
    if (table.getRowCount()==0){
      txtCustomerID.setText(cusBusObj.getCustomerIdCanInsert(DAL));
    }else{
      long lID = Long.parseLong(cusBusObj.getCustomerIdCanInsert(DAL))/10;
      lID += table.getRowCount();
      txtCustomerID.setText("" + lID + ut.getCheckDigitEAN13(String.valueOf(lID)));
    }
  }
  void btnDelete_actionPerformed(ActionEvent e) {
    //Test value table
    if (table.getSelectedRow() == -1){
      return;
    }
    if (vSql.isEmpty()){
      return;
    }
    if (table.getRowCount()==1){
      dm.removeRow(0);
      vSql.removeAllElements();
      vArrayObject.removeAllElements();
      txtCustomerID.setText(cusBusObj.getCustomerIdCanInsert(DAL));
      flagSetHotKeyForAdd = true;
      return;
    }

    //Remove value table
    long lID = 0;
    int[] i = table.getSelectedRows();
    for (int j=0; j<i.length; j++){
      vSql.removeElementAt(i[0]);
      vArrayObject.removeElementAt(i[0]);
      dm.removeRow(i[0]);
    }
    //Updates table
    int lenghtTable = table.getRowCount();
    String sID= new String();
    if (i[0]==0){
      //Get CustomerID
      sID = cusBusObj.getCustomerIdCanInsert(DAL);
      //Update table
      table.setValueAt(sID,0,0);
      //Update vSQL
      String sql1=new String();
      String sql2=new String();
      String sql3=new String();
      String sql=new String();
      sql= vSql.get(0).toString();
      sql1 = sql.substring(0,36);
      sql2= sql.substring(36,49);
      sql3 =sql.substring(49,sql.length());
      sql = sql1+sID+sql3;
      vSql.setElementAt(sql,0);
      //Update vArrayObject
      Customer cs = (Customer)vArrayObject.get(0);
      cs.setCustID(sID);
      vArrayObject.setElementAt(cs,0);
    }
    for (int l=1;l<lenghtTable;l++){
      //Get CustomerID
      lID = Long.parseLong(table.getValueAt(l-1,0).toString().substring(0,12));
      lID += 1;
      sID="" + lID + ut.getCheckDigitEAN13(String.valueOf(lID));
      //Update table
      table.setValueAt(sID,l,0);
      //Update vSQL
      String sql1=new String();
      String sql2=new String();
      String sql3=new String();
      String sql=new String();
      sql= vSql.get(l).toString();
      sql1 = sql.substring(0,36);
      sql2= sql.substring(36,49);
      sql3 =sql.substring(49,sql.length());
      sql = sql1+sID+sql3;
      vSql.setElementAt(sql,l);
      //Update vArrayObject
      Customer cs = (Customer)vArrayObject.get(l);
      cs.setCustID(sID);
      vArrayObject.setElementAt(cs,l);
    }
    lID = Long.parseLong(cusBusObj.getCustomerIdCanInsert(DAL))/10;
    lID += table.getRowCount();
    txtCustomerID.setText("" + lID + ut.getCheckDigitEAN13(String.valueOf(lID)));

    flagSetHotKeyForAdd = true;
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    //Cancel form
    txtAbbrName.setText("");
    txtAddress1.setText("");
    txtAddress2.setText("");
    txtCity.setText("");
    txtComment.setText("");
    txtContactName.setText("");
    txtCountry.setText("");
    txtCounty.setText("");
    txtEmail.setText("");
    txtFax1.setText("");
    txtFax2.setText("");
    txtHomeNo.setText("");
    txtMobileNo.setText("");
    txtName.setText("");
    txtWorkNo.setText("");
    txtBirthday.setText("");
    cboCustomerType.setSelectedIndex(0);
    cboStore.setSelectedIndex(0);
    lblName1.setText(lang.getString("CustomerName")+":");
    lblName2.setText(lang.getString("AbbreviationName")+":");
    txtCustomerID.setText(cusBusObj.getCustomerIdCanInsert(DAL));
    //Cancel Vector
    vSql.clear();
    vArrayObject.clear();
    flagSetHotKeyForAdd = true;
    while (dm.getRowCount()>0){
      dm.removeRow(0);
    }
    //Update state Cancel
    btnModify.setVisible(false);
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
    //Update
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0001"));
    frmMain.pnlMainSim.showMerchanSystemButton();
  }

  void btnSearch_actionPerformed(ActionEvent e) {
     DialogCustomerSearch dlgCustomerSearch = new DialogCustomerSearch(frmMain,lang.getString("TT0097"),true,frmMain,frmMain.customerBusObject);
     dlgCustomerSearch.setVisible(true);
    if (dlgCustomerSearch.done){
       String custID = dlgCustomerSearch.getData();
       frmMain.pnlCustomerModify.showData(custID);//,DAL);
       frmMain.pnlCustomerModify.txtName.requestFocus(true);
       frmMain.showScreen(Constant.SCRS_CUSTOMER_MODIFY);
     }
     else{
       txtName.requestFocus(true);
     }
  }

    void txtName_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtName,Customer.LEN_CUST_NAME);
      navigationComp(e,txtAbbrName);
    }

    void navigationComp(KeyEvent e, JTextField txt) {
      if (e.getKeyChar() == KeyEvent.VK_ENTER ||
          e.getKeyChar() == KeyEvent.VK_TAB) {
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
      navigationComp(e,txtCity);
    }

    void txtHomeNo_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtHomeNo,Customer.LEN_CUST_PHONE);
      if (ut.checkPhoneNumber(String.valueOf(e.getKeyChar())) == false){
        e.consume();
      }
      navigationComp(e,txtMobileNo);
    }

    void txtWorkNo_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtWorkNo,Customer.LEN_CUST_PHONE);
      if (ut.checkPhoneNumber(String.valueOf(e.getKeyChar())) == false){
       e.consume();
      }
      navigationComp(e,txtBirthday);
    }

    void txtMobileNo_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtMobileNo,Customer.LEN_CUST_PHONE);
      if (ut.checkPhoneNumber(String.valueOf(e.getKeyChar())) == false){
        e.consume();
      }
      navigationComp(e,txtWorkNo);
    }

    void txtEmail_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtEmail,Customer.LEN_CUST_EMAIL);
      navigationComp(e,txtAddress1);
    }

    void txtCity_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtCity,Customer.LEN_CUST_CITY);
      navigationComp(e,txtCounty);
    }

    void txtCounty_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtCounty,Customer.LEN_CUST_COUNTY);
      navigationComp(e,txtCountry);
    }

    void txtCountry_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtCountry,Customer.LEN_CUST_COUNTRY);
      navigationComp(e,txtFax1);
    }

    void txtFax1_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtFax1,Customer.LEN_CUST_FAX);
      if (ut.checkPhoneNumber(String.valueOf(e.getKeyChar())) == false){
       e.consume();
      }
      navigationComp(e,txtFax2);
    }

    void txtFax2_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtFax2,Customer.LEN_CUST_FAX);
      if (ut.checkPhoneNumber(String.valueOf(e.getKeyChar())) == false){
       e.consume();
      }
      navigationComp(e,txtComment);
    }
    void navigationComp(KeyEvent e, JTextArea txtArea){
      if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
        txtArea.requestFocus(true);
        txtArea.selectAll();
      }
    }
    void txtComment_keyTyped(KeyEvent e) {
      ut.limitLenjTextArea(e,txtComment,Customer.LEN_CUST_COMMENT);
      if(e.getKeyChar()==KeyEvent.VK_ENTER){
        btnAdd.doClick();
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

  void table_mousePressed(MouseEvent e) {
//    btnDone.requestFocus();
  }

  void cboCustomerType_actionPerformed(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED){
      String itemLabel =  e.getItem().toString();
//      if (itemLabel == "Regular"){
        lblName1.setText(lang.getString("FirstName")+":");
        lblName2.setText(lang.getString("LastName")+":");
//      }else{
//        lblName1.setText("Customer Name:");
//        lblName2.setText("Abbreviation Name:");
//      }
    }
  }

  void txtHomeNo_actionPerformed(ActionEvent e) {

  }

  void txtAddress1_actionPerformed(ActionEvent e) {
  }

  void txtAddress2_actionPerformed(ActionEvent e) {
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      rowTableSelect = table.getSelectedRow();
      Customer cs= (Customer)vArrayObject.get(table.getSelectedRow());
      txtCustomerID.setText(cs.getCustID());
      cboCustomerType.setSelectedIndex(Integer.parseInt(cs.getCustType()));
      cboStore.setSelectedData(cs.getStoreID());
      txtName.setText(cs.getFirstName());
      txtAbbrName.setText(cs.getLastName());
      txtContactName.setText(cs.getContactName());
      txtAddress1.setText(cs.getAddress1());
      txtAddress2.setText(cs.getAddress2());
      txtHomeNo.setText(cs.getHomePhone());
      txtMobileNo.setText(cs.getCellPhone());
      txtWorkNo.setText(cs.getWorkPhone());
      txtFax1.setText(cs.getFax1());
      txtFax2.setText(cs.getFax2());
      txtCity.setText(cs.getCity());
      txtCountry.setText(cs.getCountry());
      txtCounty.setText(cs.getCounty());
      txtEmail.setText(cs.getEmail());
      txtComment.setText(cs.getComment());
      txtBirthday.setText(cs.getBirthday());
      btnAdd.setVisible(false);
      btnDelete.setVisible(false);
      btnModify.setVisible(true);
      flagSetHotKeyForAdd = false;
//      System.out.println("flarhotkey...." + flagSetHotKeyForAdd);
      txtAbbrName.requestFocus(true);

    }
  }
  void setInterfaceEditCustomer(){
  }

  void btnModify_actionPerformed(ActionEvent e) {
    //Variable
    String sql=new String();
    String firstName = txtName.getText().trim();
    String lastName = txtAbbrName.getText().trim();
    String contactName = txtContactName.getText().trim();
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
    String sBirthday = txtBirthday.getText().trim();

    String strName = "";
    if (cboCustomerType.getSelectedItem().toString().equals("Franchise")) {
      strName = firstName;
    }
    else {
      strName = firstName + " " + lastName;
    }

    //Update table setup customer
    table.setValueAt(txtCustomerID.getText(),rowTableSelect,0);
    table.setValueAt(strName,rowTableSelect,1);
    table.setValueAt(txtContactName.getText(),rowTableSelect,2);
    table.setValueAt(txtWorkNo.getText(),rowTableSelect,3);
    //Update state of button
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
    btnModify.setVisible(false);
    //Update vSql
    if (firstName.equals("")){
      ut.showMessage(frmMain,lang.getString("TT001") ,lang.getString("MS1180_CustomerNameNotNull"));
      txtName.requestFocus(true);
      return;
    }

    if (address1.equals("")){
       ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS306_Address1NotNull"));
       txtAddress1.requestFocus(true);
       return;
    }

    if (cusBusObj.checkLength(firstName,120)){
      String stName1=new String();
      stName1 = lblName1.getText();
      stName1=stName1.replaceAll(":"," ");
      ut.showMessage(frmMain,lang.getString("TT001"),stName1+lang.getString("MS1177_TooLong"));
      txtName.requestFocus(true);
      return;
    }

    if (cusBusObj.checkLength(lastName,120)){
      String stName2=new String();
      stName2 = lblName2.getText();
      stName2=stName2.replaceAll(":"," ");
      ut.showMessage(frmMain,lang.getString("TT001"),stName2+lang.getString("MS1177_TooLong"));
      txtAbbrName.requestFocus(true);
      return;
    }

    if (cusBusObj.checkLength(contactName,120)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1181_ContactNameTooLong"));
      txtAddress2.requestFocus(true);
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
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1070_CountyTooLong"));
      txtCounty.requestFocus(true);
      return;
    }

    if (cusBusObj.checkLength(country,50)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1070_CountyTooLong"));
      txtCountry.requestFocus(true);
      return;
    }

    if (cusBusObj.checkLength(fax1,15)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1071_Fax1TooLong"));
      txtFax1.requestFocus(true);
      return;
    }

    if (cusBusObj.checkLength(fax2,15)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1072_Fax2TooLong"));
      txtFax2.requestFocus(true);
      return;
    }

    if (cusBusObj.checkLength(custComment,250)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1073_CommentTooLong"));
      txtComment.requestFocus(true);
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
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1075_WrongFax2PhoneNumber"));
      txtFax2.requestFocus(true);
      return;
    }
    if (!ut.checkDate(sBirthday, "/") && !sBirthday.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1060_WrongBirthday"));
      txtBirthday.requestFocus(true);
      return;
    }

    //Save object SQL
    Customer cs=new Customer(txtCustomerID.getText(),String.valueOf(cboCustomerType.getSelectedIndex()),firstName,
                             lastName,contactName,address1,address2,homeNo,workNo,mobileNo,fax1,fax2,email,
                             country,county,city,custComment,sBirthday,cboStore.getSelectedData().trim());
    vArrayObject.setElementAt(cs,rowTableSelect);
    //Test Null
    if (lastName.equals("")){
      lastName = "null";
    }else{
      lastName = "'" + lastName + "'";
    }

    if (contactName.equals("")){
      contactName = "null";
    }else{
      contactName = "'" + contactName + "'";
    }

    if (address2.equals("")){
      address2 = "null";
    }else{
      address2 = "'" + address2 + "'";
    }

    if (homeNo.equals("")){
      homeNo = "null";
    }else{
      homeNo = "'" + homeNo + "'";
    }

    if (workNo.equals("")){
      workNo = "null";
    }else{
      workNo = "'" + workNo + "'";
    }

    if (mobileNo.equals("")){
      mobileNo = "null";
    }else{
      mobileNo = "'" + mobileNo + "'";
    }

    if (email.equals("")){
      email = "null";
    }else{
      email = "'" + email + "'";
    }

    if (city.equals("")){
      city = "null";
    }else{
      city = "'" + city + "'";
    }

    if (county.equals("")){
      county = "null";
    }else{
      county = "'" + county  + "'";
    }

    if (country.equals("")){
      country = "null";
    }else{
      country = "'" + country  + "'";
    }

    if (fax1.equals("")){
      fax1 = "null";
    }else{
      fax1 = "'" + fax1 +"'";
    }

    if (fax2.equals("")){
       fax2 = "null";
    }else{
      fax2 = "'" + fax2 + "'";
    }

    if (custComment.equals("")){
       custComment = "null";
    }else{
      custComment = "'" + custComment + "'";
    }

    if (sBirthday.equals("")) {
      sBirthday = "1/1/1900";
    }

    sql = "insert into BTM_IM_CUSTOMER values('" +
                    txtCustomerID.getText() + "','" +
                    firstName +
                    "'," + lastName + "," + contactName + ",'" + address1 +
                    "'," + address2 +
                    "," + homeNo +
                    "," + workNo + "," + mobileNo + "," + fax1 + "," + fax2 +
                    "," + custComment + "," + email + "," +
                    city +
                    "," + county + "," + country + ",to_date('" +
                    createdDate +
                    "','DD-MM-YYYY'),'" + cboCustomerType.getSelectedIndex() +
                    "',to_date('" + sBirthday + "','DD-MM-YYYY')," + cboStore.getSelectedData().trim()+")"
;
    vSql.setElementAt(sql,rowTableSelect);
    //Update state of form setup customer
    txtAbbrName.setText("");
    txtAddress1.setText("");
    txtAddress2.setText("");
    txtCity.setText("");
    txtComment.setText("");
    txtContactName.setText("");
    txtCountry.setText("");
    txtCounty.setText("");
    txtEmail.setText("");
    txtFax1.setText("");
    txtFax2.setText("");
    txtHomeNo.setText("");
    txtMobileNo.setText("");
    txtName.setText("");
    txtWorkNo.setText("");
    txtBirthday.setText("");
    cboCustomerType.setSelectedIndex(0);
    cboStore.setSelectedIndex(0);
    lblName1.setText(lang.getString("CustomerName")+":");
    lblName2.setText(lang.getString("AbbreviationName")+":");
    resetButtonLabel();
   if (table.getRowCount()==0){
      txtCustomerID.setText(cusBusObj.getCustomerIdCanInsert(DAL));
    }else{
      long lID = Long.parseLong(cusBusObj.getCustomerIdCanInsert(DAL))/10;
      lID += table.getRowCount();
      txtCustomerID.setText("" + lID + ut.getCheckDigitEAN13(String.valueOf(lID)));
    }
    flagSetHotKeyForAdd = true;
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
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1060_WrongBirthday"));
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

  void cboCustomerType_keyTyped(KeyEvent e) {
    navigationComp(e,txtName);
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

class PanelCustomerSetup_btnClear_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnClear_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnAdd_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnDone_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnDelete_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnCancel_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnSearch_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelCustomerSetup_txtName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtName_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtName_keyTyped(e);
    }
}

class PanelCustomerSetup_txtAbbrName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtAbbrName_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtAbbrName_keyTyped(e);
    }
}
class PanelCustomerSetup_txtContactName_keyAdapter extends java.awt.event.KeyAdapter {
      PanelCustomerSetup adaptee;

      PanelCustomerSetup_txtContactName_keyAdapter(PanelCustomerSetup adaptee) {
          this.adaptee = adaptee;
      }
      public void keyTyped(KeyEvent e) {
          adaptee.txtContactName_keyTyped(e);
      }
  }

class PanelCustomerSetup_txtAddress1_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtAddress1_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtAddress1_keyTyped(e);
    }
}

class PanelCustomerSetup_txtAddress2_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtAddress2_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtAddress2_keyTyped(e);
    }
}

class PanelCustomerSetup_txtHomeNo_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtHomeNo_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtHomeNo_keyTyped(e);
    }
}

class PanelCustomerSetup_txtEmail_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtEmail_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtEmail_keyTyped(e);
    }
}

class PanelCustomerSetup_txtCity_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtCity_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtCity_keyTyped(e);
    }
}

class PanelCustomerSetup_txtCounty_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtCounty_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtCounty_keyTyped(e);
    }
}

class PanelCustomerSetup_txtCountry_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtCountry_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtCountry_keyTyped(e);
    }
}


class PanelCustomerSetup_txtComment_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerSetup adaptee;

    PanelCustomerSetup_txtComment_keyAdapter(PanelCustomerSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtComment_keyTyped(e);
    }
}

class PanelCustomerSetup_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_table_mouseAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelCustomerSetup_cboCustomerType_actionAdapter implements java.awt.event.ItemListener  {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_cboCustomerType_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.cboCustomerType_actionPerformed(e);
  }
}

class PanelCustomerSetup_txtHomeNo_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtHomeNo_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtHomeNo_actionPerformed(e);
  }
}

class PanelCustomerSetup_txtAddress1_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtAddress1_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtAddress1_actionPerformed(e);
  }
}

class PanelCustomerSetup_txtAddress2_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtAddress2_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtAddress2_actionPerformed(e);
  }
}

class PanelCustomerSetup_txtMobileNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtMobileNo_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtMobileNo_keyTyped(e);
  }
}

class PanelCustomerSetup_txtFax1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtFax1_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtFax1_keyTyped(e);
  }
}

class PanelCustomerSetup_txtFax2_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtFax2_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtFax2_keyTyped(e);
  }
}

class PanelCustomerSetup_txtWorkNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtWorkNo_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtWorkNo_keyTyped(e);
  }
}

class PanelCustomerSetup_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnModify_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelCustomerSetup_btnDate_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_btnDate_actionAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class PanelCustomerSetup_txtBirthday_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_txtBirthday_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtBirthday_keyTyped(e);
  }
}

class PanelCustomerSetup_cboCustomerType_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_cboCustomerType_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboCustomerType_keyTyped(e);
  }
}

class PanelCustomerSetup_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerSetup adaptee;

  PanelCustomerSetup_table_keyAdapter(PanelCustomerSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}


