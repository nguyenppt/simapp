package pos;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.JComponent.*;
import btm.attr.Customer;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Man hinh Chinh -> Quan Tri -> Khach hang -> Tim khach hang</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DialogCustomerSearch extends JDialog {
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel pnlMain = new JPanel();
  DataAccessLayer DAL;
  Utils ut = new Utils();
  BJCheckBox check = new BJCheckBox();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      if (col == 1){
        return Long.class;
      }
      return Object.class;
    }
  };
  BJTable table = new BJTable(dm,true);
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  BJButton btnDone = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnCancel = new BJButton();
  JTextField txtSearch = new JTextField();
  FrameMain frmMain;
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField txtCustName = new JTextField();
  JTextField txtAddress1 = new JTextField();
  JTextField txtRowsLimit = new JTextField();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel12 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  JPanel jPanel13 = new JPanel();

  ResourceBundle lang =DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);

  public boolean done = false;
//  public boolean flagHotKey =  true;
  FlowLayout flowLayout3 = new FlowLayout();
    Customer customer=new Customer();
  JLabel jLabel4 = new JLabel();
  JTextField txtCustID = new JTextField();

  public DialogCustomerSearch(Frame frame, String title, boolean modal, FrameMain frmMain) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogCustomerSearch() {
    this(null, "", false, null);
  }
  private void jbInit() throws Exception {
    pnlMain.setLayout(borderLayout1);
    //DAL.getConnfigParameter();

    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setMinimumSize(new Dimension(10, 10));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout2);
    jPanel2.setPreferredSize(new Dimension(800, 420));
    jPanel2.setLayout(gridLayout1);
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setPreferredSize(new Dimension(800, 410));
//    jPanel3.setPreferredSize(new Dimension(230, 50));
    jPanel5.setBackground(new Color(121, 152, 198));
    jPanel5.setPreferredSize(new Dimension(320, 50));
    jPanel5.setRequestFocusEnabled(true);
    jPanel5.setLayout(flowLayout3);
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
  //  btnSearch.setPreferredSize(new Dimension(80, 35));
    btnSearch.setText(lang.getString("Search")+" (F3)");
    btnSearch.addActionListener(new DialogCustomerSearch_btnSearch_actionAdapter(this));
    txtSearch.setPreferredSize(new Dimension(120, 20));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//    btnDone.setPreferredSize(new Dimension(80, 35));
    btnDone.setText(lang.getString("Done")+" (F1)");
    btnDone.addActionListener(new DialogCustomerSearch_btnDone_actionAdapter(this));
 //   btnModify.setPreferredSize(new Dimension(80, 35));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
 //   btnCancel.setPreferredSize(new Dimension(80, 35));
 btnCancel.setText("<html><center><br><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                       "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                       "ml>");
    btnCancel.addActionListener(new DialogCustomerSearch_btnCancel_actionAdapter(this));

    jPanel6.setPreferredSize(new Dimension(800, 130));
    jPanel6.setLayout(borderLayout3);
    jPanel9.setBackground(SystemColor.control);
    jPanel9.setPreferredSize(new Dimension(150, 10));
    jPanel7.setBackground(SystemColor.control);
    jPanel7.setPreferredSize(new Dimension(150, 10));
    jPanel8.setLayout(borderLayout4);
    jPanel11.setBackground(SystemColor.control);
    jPanel11.setPreferredSize(new Dimension(120, 10));
    jPanel11.setLayout(flowLayout2);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setText(lang.getString("CustomerName")+":");
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText(lang.getString("Address")+":");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setText(lang.getString("RowsLimit")+":");
    txtCustName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCustName.setPreferredSize(new Dimension(180, 20));
    txtCustName.setText("");
    txtCustName.addKeyListener(new DialogCustomerSearch_txtCustName_keyAdapter(this));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAddress1.setPreferredSize(new Dimension(180, 20));
    txtAddress1.setText("");
    txtAddress1.addKeyListener(new DialogCustomerSearch_txtAddress1_keyAdapter(this));
    txtRowsLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRowsLimit.setPreferredSize(new Dimension(46, 20));
    txtRowsLimit.setText("40");
    jPanel10.setBackground(SystemColor.control);
    jPanel10.setPreferredSize(new Dimension(200, 30));
    jPanel10.setLayout(flowLayout1);
    jPanel12.setBackground(SystemColor.control);
    jPanel12.setPreferredSize(new Dimension(180, 10));
    jPanel8.setPreferredSize(new Dimension(500, 30));
    jPanel13.setBackground(SystemColor.control);
    jPanel13.setPreferredSize(new Dimension(130, 20));
    table.addMouseListener(new DialogCustomerSearch_table_mouseAdapter(this));
    this.getContentPane().setBackground(SystemColor.control);
//    this.setFont(new java.awt.Font("SansSerif", 0, 12));
    pnlMain.setBackground(Color.lightGray);
//    table.setFont(new java.awt.Font("SansSerif", 0, 11));
    jLabel4.setText(lang.getString("CustomerID")+":");
    jLabel4.setPreferredSize(new Dimension(120, 20));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    //txtCustID.addKeyListener(new DialogCustomerSearch_txtCustID_keyAdapter(this));
    txtCustID.setText("");
    txtCustID.setPreferredSize(new Dimension(180, 20));
    txtCustID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));

    pnlMain.add(jPanel1, BorderLayout.NORTH);
//    jPanel1.add(jPanel3, BorderLayout.WEST);
//    jPanel3.add(txtSearch, null);
//    jPanel3.add(btnSearch, null);
    jPanel1.add(jPanel5, BorderLayout.CENTER);
    jPanel5.add(btnDone,null);
    jPanel5.add(btnSearch,null);
    jPanel5.add(btnCancel,null);
    pnlMain.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jScrollPane1, null);
    pnlMain.add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(jPanel9, BorderLayout.WEST);
    jPanel6.add(jPanel8, BorderLayout.CENTER);
    jPanel8.add(jPanel10, BorderLayout.CENTER);
    jPanel10.add(txtCustID, null);
    jPanel10.add(txtCustName, null);
    jPanel10.add(txtAddress1, null);
    jPanel10.add(txtRowsLimit, null);
    jPanel10.add(jPanel13, null);
    jPanel8.add(jPanel11,  BorderLayout.WEST);
    jPanel11.add(jLabel4, null);
    jPanel11.add(jLabel1, null);
    jPanel11.add(jLabel2, null);
    jPanel11.add(jLabel3, null);
    jPanel8.add(jPanel12,  BorderLayout.EAST);
    jPanel6.add(jPanel7,  BorderLayout.EAST);
    jScrollPane1.getViewport().add(table, null);
//    pnlMain.setPreferredSize(new Dimension(800,600));
    getContentPane().add(pnlMain,null);
    this.setSize(new Dimension(800,600));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);
    registerKeyboardActions();
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  }
  void btnSearch_actionPerformed(ActionEvent e) {
    if (txtRowsLimit.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS031_RowsIsNum"));
      return;
    }
    frmMain.pnlCustomerSetup.rowsNum = Integer.parseInt(txtRowsLimit.getText());
    if (frmMain.pnlCustomerSetup.rowsNum >= 50){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS032_RowsTooLong"));
      return;
    }
    dm.resetIndexes();
    searchData(frmMain.pnlCustomerSetup.rowsNum);
    if (table.getRowCount()>0){
      table.setRowSelectionInterval(0,0);
    }
  }
  // Search Data
  void searchData(int rowsNum){
//    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
//    table.setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    while (dm.getRowCount()>0){
      dm.removeRow(0);
    }
    dm.resetIndexes();
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    }catch(Exception ex){
      ex.printStackTrace();
    }
//    table.setHeaderName("First Name",2);
//      table.setHeaderName("First Name",3);
//    table.setColor(Color.lightGray, Color.white);

    table.setData(getData(txtCustID.getText(),txtCustName.getText(), txtAddress1.getText(), rowsNum ,DAL,stmt),check,1);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));

    table.setColumnWidth(0,30);
    table.setColumnWidth(1,80);
    table.setColumnWidth(2,200);
    table.setColumnWidth(3,100);
    table.setColumnWidth(4,200);
    table.setColumnWidth(5,200);
    table.setColumnWidth(6,100);
    table.setColumnWidth(7,100);
    table.setColumnWidth(8,100);
    table.setColumnWidth(9,150);
    table.setColumnWidth(10,100);
    table.setColumnWidth(11,100);
    table.setColumnWidth(12,100);
    table.setColumnWidth(13,100);
    table.setColumnWidth(14,100);
    table.setColumnWidth(15,300);
    table.setColumnWidth(16,150);
    table.setHeaderName(lang.getString("CustomerID") , 1);
    table.setHeaderName(lang.getString("FirstName") , 2);
    table.setHeaderName(lang.getString("LastName") , 3);
    table.setHeaderName(lang.getString("Address1") , 4);
    table.setHeaderName(lang.getString("Address2") , 5);
    table.setHeaderName(lang.getString("HomePhone") , 6);
    table.setHeaderName(lang.getString("WorkPhone") , 7);
    table.setHeaderName(lang.getString("CellPhone") , 8);
    table.setHeaderName(lang.getString("Email") , 9);
    table.setHeaderName(lang.getString("City") , 10);
    table.setHeaderName(lang.getString("County") , 11);
    table.setHeaderName(lang.getString("Country") , 12);
    table.setHeaderName(lang.getString("Fax") + "1" , 13);
    table.setHeaderName(lang.getString("Fax") + "2" , 14);
    table.setHeaderName(lang.getString("Comment") , 15);
    table.setHeaderName(lang.getString("CreatedDate") , 16);
    table.setHeaderName(lang.getString("CustFlag") , 17);
    table.setHeaderName(lang.getString("FullName") , 18);

    /*table.setHeaderName("Ma KH" , 1);
    table.setHeaderName("Ten KH", 2);
    table.setHeaderName("Ho", 3);
    table.setHeaderName("Dia chi 1", 4);
    table.setHeaderName("Dia chi 2", 5);
    table.setHeaderName("DT Nha", 6);
    table.setHeaderName("DT Cty", 7);
    table.setHeaderName("So DD", 8);
    table.setHeaderName("Email", 9);
    table.setHeaderName("TPho/Tinh", 10);
    table.setHeaderName("Quan/Huyen", 11);
    table.setHeaderName("Quoc gia", 12);
    table.setHeaderName("Fax 1", 13);
    table.setHeaderName("Fax 2", 14);
    table.setHeaderName("Ghi chu", 15);
    table.setHeaderName("Ngay tao", 16);
    table.setHeaderName("Trang thai", 17);
    table.setHeaderName("Ten Ho", 18);*/

//    if(DAL.getFranchiseStore().equals("true")){
//      table.setHeaderName("ABB NAME", 3);
//    }

    table.setRowHeight(30);
    //table.getTableHeader().setFont(new java.awt.Font("Arial",1,13));

    //table.getR
    ut.changeDataTypetoLong(1,dm);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);



    try{
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /** getData(String custName, String address1, int rowsLimit, DataAccessLayer DAL) - this method return the customer (type=1) from BTM_IM_CUSTOMER table
   * @author Nghi Doan
   * @param custName
   * @param address1
   * @param rowsLimit
   * @param DAL
   */
  public ResultSet getData(String custID,String custName, String address1, int rowsLimit, DataAccessLayer DAL, Statement stmt){
    ResultSet rs = null;
    try{

      String sql ="";
      //get only Franchise
//      if(DAL.getFranchiseStore().equals("true")){
////        sql ="select cust_id, first_name, last_name, address1, address2, " +
////          " home_no, work_no, mobile_no, email, " +
////          " city, county, country, fax1, fax2, cust_comment, to_char(created_date,'dd/mm/yyyy') created_date, cust_flag " +
////          " from BTM_POS_CUSTOMER where cust_flag = 1 and lower(first_name) like lower('%" + custName.trim() +
////          "%') and cust_id like '%" + custID + "%' and lower(address1) like lower('%" + address1 + "%') and rownum <=" + rowsLimit;
//      sql = " select * "
//      + "from (select cust_id, first_name, last_name, address1, address2,  "
//      + " home_no, work_no, mobile_no, email,  "
//      + " city, county, country, fax1, fax2, cust_comment, to_char(created_date,'dd/mm/yyyy') created_date, cust_flag, first_name ||' ' || last_name as fullname "
//      + " from BTM_POS_CUSTOMER where cust_flag <> 0 and cust_id<>'-1') "
//      + "where lower(fullname) like lower('%" + custName.trim()
//      + "%') and cust_id like '%" + custID + "%' and lower(address1) like lower('%" + address1 + "%') and rownum <= "+ rowsLimit;
//
//
//      //get other customer
//      }
//      else{

 //disable search customer
//        sql = " select * "
//        + "from (select cust_id, first_name, last_name,   "
//        + "address1, address2,  home_no, work_no, mobile_no, email,   "
//        + "city, county, country, fax1, fax2, cust_comment, to_char(created_date,'dd/mm/yyyy') created_date, cust_flag,first_name ||' ' || last_name as fullname   "
//        + "from BTM_POS_CUSTOMER )  "
//        + "where lower(fullname) like lower('%" + custName.trim() +"%') and cust_id like '%" + custID + "%'  "
//        + "and lower(address1) like lower('%" + address1 + "%') and cust_flag=1 and rownum <=" + rowsLimit;

    sql = " select * "
    + "from (select cust_id, first_name, last_name,   "
    + "address1, address2,  home_no, work_no, mobile_no, email,   "
    + "city, county, country, fax1, fax2, cust_comment, to_char(created_date,'dd/mm/yyyy') created_date, cust_flag,first_name ||' ' || last_name as fullname   "
    + "from BTM_POS_CUSTOMER )  "
    + "where lower(fullname) like lower('%" + custName.trim() +"%') and cust_id like '%" + custID + "%'  "
    + "and lower(address1) like lower('%" + address1 + "%') and rownum <=" + rowsLimit;

if (DAL.getFranchiseCust().length() == 13) {
  sql = " select * "
  + "from (select cust_id, first_name, last_name,   "
  + "address1, address2,  home_no, work_no, mobile_no, email,   "
  + "city, county, country, fax1, fax2, cust_comment, to_char(created_date,'dd/mm/yyyy') created_date, cust_flag,first_name ||' ' || last_name as fullname   "
  + "from BTM_POS_CUSTOMER )  "
  + "where  cust_id like '%" + DAL.getFranchiseCust() + "%'  ";

System.out.println(sql);
}

//      }

//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  void btnDone_actionPerformed(ActionEvent e) {
    if (table.getRowCount()>0){
      done = true;
    }
      this.dispose();


//========================Delee from DB
//    int i = 1;
//    if (checkTable(table)){
//      i = ut.showMessage(frmMain,"Warning", "Are you sure?", DialogMessage.QUESTION_MESSAGE,
//                                            DialogMessage.YES_NO_OPTION);
//    }
//    if (i==DialogMessage.YES_VALUE){
//      for (int j = 0; j < table.getRowCount(); j++) {
//        //Xoa du lieu trong Database
//        BJCheckBox check = (BJCheckBox) table.getValueAt(j, 0);
//        if (check.isSelected()) {
//
//          Statement stmt = null;
//          try{
//            stmt = DAL.getConnection().createStatement();
//            DAL.executeUpdate("Delete from btm_pos_customer where cust_id ='" +
//                              check.getValue() + "'",stmt);
//            stmt.close();
//          }catch(Exception ex){
//            ex.printStackTrace();
//          }
//        }
//      }
//    }
//    searchData(frmMain.pnlCustomerSetup.rowsNum);


  }
  boolean checkTable(BJTable table1){
    for (int i = 0; i<table.getRowCount(); i++){
      BJCheckBox check = (BJCheckBox) table1.getValueAt(i,0);
      if (check.isSelected()){
        return true;
      }
    }
    return false;
  }
  //return data
  String getData(){
    String result = "";
    result = "" + table.getValueAt(table.getSelectedRow(),1);
    return result;
  }
  //return Customer name
  String getCustName(){
    String result = "";
//    String custFlag = "" + table.getValueAt(table.getSelectedRow(),17);

    if(table.getRowCount()>0){
      //if franchise=> get first name only
//      if (DAL.getFranchiseStore().equalsIgnoreCase("true")) {
//        result = "Name: " + table.getValueAt(table.getSelectedRow(), 2);
//      }
//      else {
        result = lang.getString("Name") + ": " + table.getValueAt(table.getSelectedRow(), 2) + " " +
            table.getValueAt(table.getSelectedRow(), 3);
//      }
    }else{
      result= txtCustName.getText();
    }

    return result;
  }
  // Return Customer phone
  String getCustPhone(){
    String result = "";
    result = lang.getString("Phone") + ": " + table.getValueAt(table.getSelectedRow(), 7);
    return result;
  }


  // Get Customer object
  Customer getCustomer(){
    Customer cusomer = new Customer();
    String custID="";
    String firstName="";
    String lastName="";
    String fullName="";
    String custType="";
    String workPhone="";


    //get customer from table
    if(table.getRowCount()>0){
      custID = table.getValueAt(table.getSelectedRow(),1).toString();
      firstName = table.getValueAt(table.getSelectedRow(), 2).toString() ;
      lastName = table.getValueAt(table.getSelectedRow(), 3).toString();
      custType=table.getValueAt(table.getSelectedRow(),17).toString();
      fullName=table.getValueAt(table.getSelectedRow(),18).toString();
      if(table.getValueAt(table.getSelectedRow(), 7)!=null){
        workPhone = table.getValueAt(table.getSelectedRow(), 7).toString();
      }
    //get customer from textbox
    }
//    else{
//      ResultSet rs = null;
//      try{
//        String sql ="";
//        //get only Franchise
//        if(DAL.getFranchiseStore().equals("true")){
//          sql =
//          "select * from (select select cust_id, first_name, last_name,   "
//        + "address1, address2,  home_no, work_no, mobile_no, email,   "
//        + "city, county, country, fax1, fax2, cust_comment, to_char(created_date,'dd/mm/yyyy') created_date, cust_flag,first_name ||' ' || last_name as fullname   "
//        + " BTM_POS_CUSTOMER where cust_flag<>0) where lower(full_name) like lower('%" +
//              txtCustName.getText().trim() + "%') ";
//        //get other customer
//        }else{
//          sql =
//          "select * from (select select cust_id, first_name, last_name,   "
//        + "address1, address2,  home_no, work_no, mobile_no, email,   "
//        + "city, county, country, fax1, fax2, cust_comment, to_char(created_date,'dd/mm/yyyy') created_date, cust_flag,first_name ||' ' || last_name as fullname   "
//        + "BTM_POS_CUSTOMER where cust_flag = 0 and cust_flag='-1') where lower(full_name) like lower('%" +
//              txtCustName.getText().trim() + "%') ";
//
//        }
//        System.out.println("2222222222222 ne "+sql);
//        rs = DAL.executeQueries(sql);
//        if(rs.next()){
//          custID = rs.getString("cust_id");
//          firstName = rs.getString("first_name");
//          lastName = rs.getString("last_name");
//          fullName=rs.getString("full_name");
//          workPhone = rs.getString("work_no");
//        }
//      }catch(Exception e){
//        ExceptionHandle eh = new ExceptionHandle();
//        eh.ouputError(e);
//      }
//
//    }
    cusomer.setCustID(custID);
    cusomer.setFirstName(firstName);

//System.out.println("cc "+custType);

    cusomer.setLastName(lastName);
    cusomer.setCustType(custType);
    cusomer.setWorkPhone(workPhone);
    return cusomer;
  }

//  void btnModify_actionPerformed(ActionEvent e) {
//    if (table.getSelectedRow() != -1){
//      done = true;
//      this.dispose();
//    }
//  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      done = true;
      this.dispose();
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    done = false;
    this.dispose();
  }
  private void registerKeyboardActions() {
  // set up the maps:
  InputMap inputMap = new InputMap();
  inputMap.setParent(pnlMain.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
  ActionMap actionMap = pnlMain.getActionMap();
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
  // F12
   key = new Integer(KeyEvent.VK_F12);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
   actionMap.put(key, new KeyAction(key));

  // Escape
  key = new Integer(KeyEvent.VK_ESCAPE);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
  actionMap.put(key, new KeyAction(key));

  pnlMain.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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
      else if (identifier.intValue() == KeyEvent.VK_F3) {
        btnSearch.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }

}

  void table_mousePressed(MouseEvent e) {
    btnSearch.requestFocus();
  }

   private void catchHotKey(KeyEvent e) {
               if (e.getKeyCode() == KeyEvent.VK_F3) {
                 btnSearch.doClick();
               }
               else if (e.getKeyCode() == KeyEvent.VK_F12 || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                 btnCancel.doClick();
               }

             }

  void txtCustName_keyTyped(KeyEvent e) {
    /*if (e.getKeyChar() == KeyEvent.VK_ENTER && table.getRowCount()==0){
      ResultSet rs = null;
      try{
        String sql ="";
        //get only Franchise
        if(DAL.getFranchiseStore().equals("true")){
          sql =
              "select * from BTM_POS_CUSTOMER where cust_flag=1 and lower(first_name) like lower('%" +
              txtCustName.getText().trim() + "%') ";
        //get other customer
        }else{
          sql =
              "select * from BTM_POS_CUSTOMER where cust_flag <> 1 and lower(first_name) like lower('%" +
              txtCustName.getText().trim() + "%') ";

        }

        System.out.println(sql);
        rs = DAL.executeQueries(sql);
        if(rs.next()){
          done = true;
          this.dispose();

        }else{
          ut.showMessage(frmMain,"Warning", "No Customer found matching your description");
        }
      }catch(Exception ex){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      }


    }else if(e.getKeyChar() == KeyEvent.VK_ENTER && table.getRowCount()>0){
      //btnSearch.doClick();
    }*/
  if (e.getKeyChar() == KeyEvent.VK_ENTER){
    btnSearch.doClick();
  }

  }

  void txtAddress1_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      btnSearch.doClick();
    }

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

class DialogCustomerSearch_btnDone_actionAdapter implements java.awt.event.ActionListener {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_btnDone_actionAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

//class DialogCustomerSearch_btnModify_actionAdapter implements java.awt.event.ActionListener {
//  DialogCustomerSearch adaptee;
//
//  DialogCustomerSearch_btnModify_actionAdapter(DialogCustomerSearch adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnModify_actionPerformed(e);
//  }
//}

class DialogCustomerSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_table_mouseAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
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

class DialogCustomerSearch_txtCustName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCustomerSearch adaptee;

  DialogCustomerSearch_txtCustName_keyAdapter(DialogCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCustName_keyTyped(e);
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
}

