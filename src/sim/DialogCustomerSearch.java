package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import btm.attr.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Customer Setup -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DialogCustomerSearch extends JDialog {
  BorderLayout borderLayout1 = new BorderLayout();
  DataAccessLayer DAL;
  Statement stmt = null;
  Utils ut = new Utils();
  int parentScreen=0;
  CustomerBusObj cusBusObj = new CustomerBusObj();

  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 0: return Long.class;
        case 3: return Date.class;
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm,true);
  BorderLayout borderLayout2 = new BorderLayout();
  BJPanel jPanel5 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJButton btnSearch = new BJButton();
  BJButton btnCancel = new BJButton();
  FrameMainSim frmMain;
  JPanel jPanel6 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout();
  int rowsNum = 5;
  public boolean done = false;
  JLabel jLabel2 = new JLabel();
  JLabel jLabel19 = new JLabel();
  JLabel jLabel20 = new JLabel();
  JLabel lblName2 = new JLabel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JPanel jPanel14 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel21 = new JLabel();
  JLabel lblName3 = new JLabel();
  JLabel lblName1 = new JLabel();
  JLabel jLabel110 = new JLabel();
  JLabel jLabel18 = new JLabel();
  JTextField txtHomeNo = new JTextField();
  JTextField txtAddress1 = new JTextField();
  JTextField txtContactName = new JTextField();
  JTextField txtAbbrName = new JTextField();
  JTextField txtName = new JTextField();
  JComboBox cboCustomerType = new JComboBox();
  JTextField txtCustomerID = new JTextField();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel1 = new JLabel();
  JTextField txtCity = new JTextField();
  JTextField txtCountry = new JTextField();
  JTextField txtMobileNo = new JTextField();
  JTextField txtWorkNo = new JTextField();
  JLabel lblRowLimit1 = new JLabel();
  JTextField txtRowLimit1 = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout5 = new FlowLayout();
  JLabel jLabel6 = new JLabel();
  JButton btnDate = new JButton();
  JTextField txtBirthday = new JTextField();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public DialogCustomerSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain,   CustomerBusObj cusBusObj) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      this.cusBusObj = cusBusObj;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogCustomerSearch() {
    this(null, "", false,null,null);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();
        //international
    this.getContentPane().setLayout(borderLayout1);
    this.setSize(new Dimension(800,600));
    this.addWindowListener(new DialogCustomerSearch_this_windowAdapter(this));
    this.addKeyListener(new DialogCustomerSearch_this_keyAdapter(this));
    jPanel1.setMinimumSize(new Dimension(10, 10));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout2);
    jPanel2.setMinimumSize(new Dimension(24, 15));
    jPanel2.setPreferredSize(new Dimension(800, 340));
    jPanel2.setLayout(gridLayout1);
    jScrollPane1.setMinimumSize(new Dimension(24, 20));
    jScrollPane1.setPreferredSize(new Dimension(800, 290));
//    jPanel3.setPreferredSize(new Dimension(230, 50));
    jPanel5.setMinimumSize(new Dimension(503, 35));
    jPanel5.setPreferredSize(new Dimension(750, 50));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setPreferredSize(new Dimension(130, 37));
//    btnSearch.setPreferredSize(new Dimension(80, 35));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3<" +
    "/html>");
    btnSearch.addActionListener(new DialogCustomerSearch_btnSearch_actionAdapter(this));
//    btnDelete.setPreferredSize(new Dimension(80, 35));
//    btnModify.setPreferredSize(new Dimension(80, 35));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(130, 37));
//    btnCancel.setPreferredSize(new Dimension(80, 35));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new DialogCustomerSearch_btnCancel_actionAdapter(this));

    jPanel6.setPreferredSize(new Dimension(800, 230));
    jPanel6.setLayout(borderLayout3);
    table.addMouseListener(new DialogCustomerSearch_table_mouseAdapter(this));
    jLabel2.setText(lang.getString("HomePhone")+":");
    jLabel2.setPreferredSize(new Dimension(110, 20));
    jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel2.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel19.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel19.setPreferredSize(new Dimension(110, 20));
    jLabel19.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel19.setText(lang.getString("CustomerType")+":");
    jLabel20.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel20.setPreferredSize(new Dimension(110, 20));
    jLabel20.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel20.setText(lang.getString("ContactName")+":");
    lblName2.setText(lang.getString("AbbreviationName")+":");
    lblName2.setPreferredSize(new Dimension(110, 20));
    lblName2.setHorizontalAlignment(SwingConstants.LEFT);
    lblName2.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblName2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel3.setPreferredSize(new Dimension(380, 200));
    jPanel3.setLayout(borderLayout5);
    jPanel4.setLayout(borderLayout4);
    jPanel4.setMinimumSize(new Dimension(0, 0));
    jPanel4.setPreferredSize(new Dimension(380, 10));
    jPanel7.setPreferredSize(new Dimension(120, 10));
    jPanel7.setToolTipText("");
    jPanel7.setLayout(flowLayout1);
    jPanel10.setPreferredSize(new Dimension(250, 10));
    jPanel10.setLayout(flowLayout5);
    jPanel11.setPreferredSize(new Dimension(250, 10));
    FlowLayout low1=new FlowLayout(FlowLayout.LEFT);
    jPanel11.setLayout(low1);
    jPanel14.setPreferredSize(new Dimension(80, 10));
    jPanel9.setLayout(flowLayout3);
    jPanel9.setPreferredSize(new Dimension(100, 10));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel3.setPreferredSize(new Dimension(90, 20));
    jLabel3.setText(lang.getString("HomePhone")+":");
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setOpaque(false);
    jLabel5.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel5.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel5.setPreferredSize(new Dimension(110, 20));
    jLabel5.setText(lang.getString("Address1")+":");
    jLabel21.setText(lang.getString("ContactName")+":");
    jLabel21.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel21.setPreferredSize(new Dimension(110, 20));
    jLabel21.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblName3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblName3.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblName3.setHorizontalAlignment(SwingConstants.LEFT);
    lblName3.setPreferredSize(new Dimension(110, 20));
    lblName3.setText(lang.getString("LastName")+":");
    lblName1.setText(lang.getString("FirstName")+":");
    lblName1.setPreferredSize(new Dimension(110, 20));
    lblName1.setHorizontalAlignment(SwingConstants.LEFT);
    lblName1.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblName1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel110.setText(lang.getString("CustomerType")+":");
    jLabel110.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel110.setPreferredSize(new Dimension(110, 20));
    jLabel110.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel18.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel18.setForeground(Color.black);
    jLabel18.setPreferredSize(new Dimension(110, 20));
    jLabel18.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel18.setText(lang.getString("CustomerID")+":");
    txtHomeNo.setText("");
    txtHomeNo.setBounds(new Rectangle(25, 156, 200, 20));
    txtHomeNo.addKeyListener(new DialogCustomerSearch_txtHomeNo_keyAdapter(this));
    txtHomeNo.setSelectionStart(0);
    txtHomeNo.setPreferredSize(new Dimension(200, 20));
    txtHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtAddress1.setText("");
    txtAddress1.addKeyListener(new DialogCustomerSearch_txtAddress1_keyAdapter(this));
    txtAddress1.setSelectionStart(0);
    txtAddress1.setPreferredSize(new Dimension(200, 20));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtContactName.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtContactName.setForeground(Color.black);
    txtContactName.setPreferredSize(new Dimension(200, 20));
    txtContactName.setText("");
    txtContactName.addKeyListener(new DialogCustomerSearch_txtContactName_keyAdapter(this));
    txtAbbrName.setText("");
    txtAbbrName.addKeyListener(new DialogCustomerSearch_txtAbbrName_keyAdapter(this));
    txtAbbrName.setSelectionStart(0);
    txtAbbrName.setPreferredSize(new Dimension(200, 20));
    txtAbbrName.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtName.setText("");
    txtName.addKeyListener(new DialogCustomerSearch_txtName_keyAdapter(this));
    txtName.setSelectionStart(0);
    txtName.setPreferredSize(new Dimension(200, 20));
    txtName.setSelectedTextColor(Color.white);
    txtName.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtCustomerID.setEnabled(true);
    txtCustomerID.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtCustomerID.setPreferredSize(new Dimension(200, 20));
    txtCustomerID.setCaretPosition(0);
    txtCustomerID.setEditable(true);
    txtCustomerID.setText("");
    txtCustomerID.addKeyListener(new DialogCustomerSearch_txtCustomerID_keyAdapter(this));
    cboCustomerType.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    cboCustomerType.setPreferredSize(new Dimension(200, 20));
    cboCustomerType.addKeyListener(new DialogCustomerSearch_cboCustomerType_keyAdapter(this));
    cboCustomerType.addItemListener(new DialogCustomerSearch_cboCustomerType_itemAdapter(this));
    jLabel9.setText(lang.getString("City")+":");
    jLabel9.setPreferredSize(new Dimension(90, 20));
    jLabel9.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel11.setText(lang.getString("Country")+":");
    jLabel11.setPreferredSize(new Dimension(90, 20));
    jLabel11.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel11.setMaximumSize(new Dimension(53, 20));
    jLabel7.setText(lang.getString("CellPhone")+":");
    jLabel7.setPreferredSize(new Dimension(90, 20));
    jLabel7.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel7.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(90, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel1.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel1.setText(lang.getString("WorkPhone")+":");
    txtCity.setText("");
    txtCity.addKeyListener(new DialogCustomerSearch_txtCity_keyAdapter(this));
    txtCity.setSelectionStart(0);
    txtCity.setPreferredSize(new Dimension(200, 20));
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtCountry.setText("");
    txtCountry.addKeyListener(new DialogCustomerSearch_txtCountry_keyAdapter(this));
    txtCountry.setSelectionStart(0);
    txtCountry.setPreferredSize(new Dimension(200, 20));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtMobileNo.setPreferredSize(new Dimension(200, 20));
    txtMobileNo.setSelectionStart(0);
    txtMobileNo.setText("");
    txtMobileNo.addKeyListener(new DialogCustomerSearch_txtMobileNo_keyAdapter(this));
    txtWorkNo.setText("");
    txtWorkNo.addKeyListener(new DialogCustomerSearch_txtWorkNo_keyAdapter(this));
    txtWorkNo.setSelectionStart(0);
    txtWorkNo.setPreferredSize(new Dimension(200, 20));
    txtWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRowLimit1.setText(lang.getString("RowsLimit")+":");
    lblRowLimit1.setPreferredSize(new Dimension(90, 20));
    lblRowLimit1.setHorizontalAlignment(SwingConstants.LEFT);
    lblRowLimit1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRowLimit1.setMaximumSize(new Dimension(93, 15));
    lblRowLimit1.setMinimumSize(new Dimension(93, 20));
    txtRowLimit1.setText(DAL.getSearchLimit());
    txtRowLimit1.addKeyListener(new DialogCustomerSearch_txtRowLimit1_keyAdapter(this));
    txtRowLimit1.setPreferredSize(new Dimension(50, 20));
    txtRowLimit1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setText(lang.getString("Birthday")+":");
    jLabel6.setPreferredSize(new Dimension(110, 20));
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDate.setPreferredSize(new Dimension(25, 22));
    btnDate.setText("...");
    btnDate.addActionListener(new DialogCustomerSearch_btnDate_actionAdapter(this));
    txtBirthday.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtBirthday.setPreferredSize(new Dimension(170, 20));
    txtBirthday.setText("");
    txtBirthday.addKeyListener(new DialogCustomerSearch_txtBirthday_keyAdapter(this));
    table.addKeyListener(new DialogCustomerSearch_table_keyAdapter(this));
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel5,BorderLayout.CENTER);
    jPanel5.add(btnSearch,null);
    jPanel5.add(btnCancel,null);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    this.getContentPane().add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(jPanel3,  BorderLayout.WEST);
    jPanel3.add(jPanel7,  BorderLayout.CENTER);
    jPanel7.add(jLabel18, null);
    jPanel7.add(jLabel110, null);
    jPanel7.add(lblName1, null);
    jPanel7.add(lblName3, null);
    jPanel7.add(jLabel21, null);
    jPanel7.add(jLabel5, null);
    jPanel7.add(jLabel6, null);
    jPanel3.add(jPanel10,  BorderLayout.EAST);
    jPanel10.add(txtCustomerID, null);
    jPanel10.add(cboCustomerType, null);
    jPanel10.add(txtName, null);
    jPanel10.add(txtAbbrName, null);
    jPanel10.add(txtContactName, null);
    jPanel10.add(txtAddress1, null);
    jPanel10.add(txtBirthday, null);
    jPanel10.add(btnDate, null);
    jPanel6.add(jPanel4,  BorderLayout.EAST);
    jPanel4.add(jPanel9,  BorderLayout.CENTER);
    jPanel9.add(jLabel3, null);
    jPanel9.add(jLabel1, null);
    jPanel9.add(jLabel7, null);
    jPanel9.add(jLabel11, null);
    jPanel9.add(jLabel9, null);
    jPanel9.add(lblRowLimit1, null);
    jPanel4.add(jPanel11,  BorderLayout.EAST);
    jPanel11.add(txtHomeNo, null);
    jPanel11.add(txtWorkNo, null);
    jPanel11.add(txtMobileNo, null);
    jPanel11.add(txtCountry, null);
    jPanel11.add(txtCity, null);
    jPanel11.add(txtRowLimit1, null);
    jPanel6.add(jPanel14,  BorderLayout.CENTER);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);
//    cboCustomerType.addItem("Regular");
//    cboCustomerType.addItem("Franchise");
//    cboCustomerType.addItem("Whole Sales");
//    cboCustomerType.addItem("Other");
    cboCustomerType.addItem("Regular"); //cus_flag=0
    cboCustomerType.addItem("Home Employee");//cus_flag=1
    cboCustomerType.addItem("Whole Sale");//cus_flag=2
    cboCustomerType.addItem("Other");//cus_flag=3

    txtCustomerID.requestFocus(true);
  }

  // Search Data
  void searchData(int rowsNum) {

  }
  public void setFocus(){
     txtCustomerID.requestFocus(true);
     this.setVisible(true);
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      done = true;
      setDataIntoCustBusObj();
      this.dispose();
    }
  }
  //return data
  String getData(){
    String result = "";
    result = "" + table.getValueAt(table.getSelectedRow(),0);
    return result;
  }
  void btnCancel_actionPerformed(ActionEvent e) {
    done = false;
    this.dispose();
  }

  //set data into CustBusObj
  void setDataIntoCustBusObj() {
    cusBusObj.setValueSearch(txtCustomerID.getText(),
                             cboCustomerType.getSelectedItem().toString(),
                             txtName.getText(),
                             txtAbbrName.getText(), txtContactName.getText(),
                             txtAddress1.getText(),
                             txtHomeNo.getText(), txtWorkNo.getText(),
                             txtMobileNo.getText(), txtCountry.getText(),
                             txtCity.getText(), txtRowLimit1.getText(),
                             txtBirthday.getText());
  }

  //set text
  void txtName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtName,Customer.LEN_CUST_NAME);
    navigationComp(e,txtAbbrName);
  }

  void txtAbbrName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAbbrName,Customer.LEN_CUST_ABB_NAME);
    navigationComp(e,txtContactName);
  }
  void txtContactName_keyTyped(KeyEvent e){
    ut.limitLenjTextField(e,txtContactName,Customer.LEN_CUST_NAME);
  }
  void txtAddress1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAddress1,Customer.LEN_CUST_ADDRESS);
    navigationComp(e,txtBirthday);
  }

  void txtHomeNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtHomeNo,Customer.LEN_CUST_PHONE);
    ut.checkPhone(e);
    navigationComp(e,txtWorkNo);
  }

  void txtWorkNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtWorkNo,Customer.LEN_CUST_PHONE);
    ut.checkPhone(e);
    navigationComp(e,txtMobileNo);
  }

  void txtMobileNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtMobileNo,Customer.LEN_CUST_PHONE);
    ut.checkPhone(e);
    navigationComp(e,txtCountry);
  }

  void txtCity_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCity,Customer.LEN_CUST_CITY);
    navigationComp(e,txtRowLimit1);
  }

  void txtCountry_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCountry,Customer.LEN_CUST_COUNTRY);
    navigationComp(e,txtCity);
  }

  void txtCustomerID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCustomerID,13);
    if (ut.checkPhoneNumber(String.valueOf(e.getKeyChar())) == false){
       e.consume();
    }
    navigationComp(e,cboCustomerType);
  }

  void navigationComp(KeyEvent e, JComboBox cbo) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      cbo.requestFocus(true);
    }
  }

  void navigationComp(KeyEvent e, JTextField txt){
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      txt.requestFocus(true);
      txt.selectAll();
    }
  }

  void txtRowLimit1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtRowLimit1,5);
    ut.checkNumber(e);
    if(e.getKeyChar()==KeyEvent.VK_ENTER){
      btnSearch.doClick();
    }
  }
  public void setValue(String sCustId,String sCustType,String sFirstName,String sLastName,String sContactName,
                       String sAddress1,String sHomePhone,String sWorkPhone,String sCellPhone,
                       String sCountry,String sCity,String sRow,String sBirthday){
    txtCustomerID.setText(sCustId);
    cboCustomerType.setSelectedItem(sCustType);
    txtName.setText(sFirstName);
    txtAbbrName.setText(sLastName);
    txtContactName.setText(sContactName);
    txtAddress1.setText(sAddress1);
    txtHomeNo.setText(sHomePhone);
    txtWorkNo.setText(sWorkPhone);
    txtMobileNo.setText(sCellPhone);
    txtCountry.setText(sCountry);
    txtCity.setText(sCity);
    txtRowLimit1.setText(sRow);
    txtBirthday.setText(sBirthday);
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    if (txtRowLimit1.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS031_RowsIsNum"));
      return;
    }

    rowsNum = Integer.parseInt(txtRowLimit1.getText());
//    if (rowsNum > 50){
//      ut.showMessage(frmMain,lang.getString("TT001"), "The number of rows is too long! it must be less than or equal 50");
//      return;
//    }
    frmMain.pnlCustomerSetup.rowNum = rowsNum;
    searchDataCustomer(txtRowLimit1.getText());
  }
  // Search Data
  void searchDataCustomer(String rowsNum) {
    dm.resetIndexes();
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      table.setData(cusBusObj.getDataSearch(txtCustomerID.getText(),cboCustomerType.getSelectedIndex(),txtName.getText(),
                                        txtAbbrName.getText(),txtContactName.getText(),txtAddress1.getText(),
                                        txtHomeNo.getText(),txtWorkNo.getText(),txtMobileNo.getText(),
                                        txtCountry.getText(),txtCity.getText(),DAL,rowsNum,txtBirthday.getText(),stmt));

     stmt.close();
    }
    catch(Exception ex){};

    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 13));
    table.setHeaderName(lang.getString("CustomerID"), 0);
    table.setHeaderName(lang.getString("CustomerName"), 1);
//    table.setHeaderName("Last Name", 2);
    table.setHeaderName(lang.getString("Address1"), 2);
    table.setHeaderName(lang.getString("Birthday"), 3);
    table.setHeaderName(lang.getString("HomePhone"), 4);
    table.setHeaderName(lang.getString("CellPhone"), 5);
    table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
    //ut.changeDataTypetoLong(0,dm);
  }

  void cboCustomerType_itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED){
         String itemLabel =  e.getItem().toString();
//         if (itemLabel =="Regular"){
           lblName1.setText(lang.getString("FirstName")+":");
           lblName3.setText(lang.getString("LastName")+":");
//         }else{
//           lblName1.setText("Customer Name:");
//           lblName3.setText("Abbreviation Name:");
//         }
       }
  }
  private void registerKeyboardActions() {
             /// set up the maps:

             InputMap inputMap = new InputMap();
             inputMap.setParent(jPanel5.getInputMap(JComponent.
                                                    WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
             ActionMap actionMap = jPanel5.getActionMap();


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

              jPanel5.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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

               if (identifier.intValue() == KeyEvent.VK_F3 || identifier.intValue() == KeyEvent.VK_ENTER) {
                 btnSearch.doClick();
               }
               else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                 btnCancel.doClick();
               }
             }
     }
     private void catchHotKey(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_F3) {
              btnSearch.doClick();
            }
            else if (e.getKeyCode() == KeyEvent.VK_F12 ||
                     e.getKeyCode() == KeyEvent.VK_ESCAPE) {
              btnCancel.doClick();
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
      this.txtBirthday.setText(date);
      this.txtHomeNo.requestFocus(true);
    }
  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtRowLimit1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtCity_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtCountry_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtMobileNo_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtWorkNo_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtHomeNo_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtBirthday_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtAddress1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtContactName_keyPressed(KeyEvent e) {
    catchHotKey(e);
    navigationComp(e,txtAddress1);
  }

  void txtAbbrName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void cboCustomerType_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtCustomerID_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void cboCustomerType_keyTyped(KeyEvent e) {
    navigationComp(e,txtName);
  }

  void txtBirthday_keyTyped(KeyEvent e) {
    if(e.getKeyChar()==KeyEvent.VK_ENTER){
      btnDate.requestFocus(true);
    }
  }

  void this_windowOpened(WindowEvent e) {
    txtCustomerID.requestFocus(true);
  }

}

class DialogCustomerSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_btnSearch_actionAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class DialogCustomerSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_table_mouseAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogCustomerSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_btnCancel_actionAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogCustomerSearch_txtHomeNo_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtHomeNo_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtHomeNo_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtHomeNo_keyPressed(e);
  }
}

class DialogCustomerSearch_txtName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtName_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtName_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtName_keyPressed(e);
  }
}

class DialogCustomerSearch_txtAbbrName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtAbbrName_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAbbrName_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtAbbrName_keyPressed(e);
  }
}

class DialogCustomerSearch_txtContactName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtContactName_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtContactName_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtContactName_keyPressed(e);
  }
}

class DialogCustomerSearch_txtAddress1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtAddress1_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress1_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtAddress1_keyPressed(e);
  }
}

class DialogCustomerSearch_txtWorkNo_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtWorkNo_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtWorkNo_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtWorkNo_keyPressed(e);
  }
}

class DialogCustomerSearch_txtMobileNo_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtMobileNo_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtMobileNo_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtMobileNo_keyPressed(e);
  }
}

class DialogCustomerSearch_txtCountry_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtCountry_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCountry_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtCountry_keyPressed(e);
  }
}

class DialogCustomerSearch_txtCity_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtCity_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCity_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtCity_keyPressed(e);
  }
}

class DialogCustomerSearch_txtCustomerID_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtCustomerID_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCustomerID_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtCustomerID_keyPressed(e);
  }
}

class DialogCustomerSearch_txtRowLimit1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtRowLimit1_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRowLimit1_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRowLimit1_keyPressed(e);
  }
}

class DialogCustomerSearch_cboCustomerType_itemAdapter implements java.awt.event.ItemListener {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_cboCustomerType_itemAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.cboCustomerType_itemStateChanged(e);
  }
}

class DialogCustomerSearch_btnDate_actionAdapter implements java.awt.event.ActionListener {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_btnDate_actionAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class DialogCustomerSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_this_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogCustomerSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_table_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogCustomerSearch_txtBirthday_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtBirthday_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtBirthday_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtBirthday_keyTyped(e);
  }
}

class DialogCustomerSearch_cboCustomerType_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_cboCustomerType_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboCustomerType_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboCustomerType_keyTyped(e);
  }
}

class DialogCustomerSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_this_windowAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}


