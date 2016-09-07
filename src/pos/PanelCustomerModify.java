 /*Modified by Nghia Le */
package pos;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import btm.attr.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Man hinh chinh -> Khach Hang -> Tim -> (Error on Grid)</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelCustomerModify extends JPanel {
  DataAccessLayer DAL;
  FrameMain frmMain;
  CustomerBusObj cusBusObj = new CustomerBusObj();
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
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
  JLabel jLabel14 = new JLabel();
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
  JTextField txtCreatedDate = new JTextField();
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
  BorderLayout borderLayout6 = new BorderLayout();
  BJButton btnCancel = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  String custID = "";
  //
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);

  public PanelCustomerModify(FrameMain frmMain) {
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
//    System.out.println("N5");
    registerKeyboardActions();
    this.setPreferredSize(new Dimension(800,600));
    this.setLayout(borderLayout1);
    jPanel2.setBorder(null);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setOpaque(true);
    jPanel2.setPreferredSize(new Dimension(330, 200));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(270, 200));
    jPanel3.setLayout(borderLayout3);
    jPanel4.setBackground(SystemColor.control);
    jPanel4.setPreferredSize(new Dimension(120, 10));
    jPanel6.setBackground(SystemColor.control);
    jPanel6.setPreferredSize(new Dimension(100,10));
    jPanel8.setBackground(SystemColor.control);
    jPanel8.setPreferredSize(new Dimension(30, 10));
    jPanel7.setBackground(SystemColor.control);
    jPanel7.setPreferredSize(new Dimension(170, 10));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(110, 20));
    jLabel1.setText(lang.getString("WorkPhone") + ":");
    jLabel2.setText(lang.getString("HomePhone") + ":");
    jLabel2.setPreferredSize(new Dimension(110, 20));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setText(lang.getString("Address") + " 2:");
    jLabel3.setPreferredSize(new Dimension(110, 20));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("Address") + " 1:");
    jLabel4.setPreferredSize(new Dimension(110, 20));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setText(lang.getString("FirstName") + ":");
    jLabel5.setPreferredSize(new Dimension(110, 20));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setText("Last Name:");
    jLabel6.setPreferredSize(new Dimension(110, 20));
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel7.setText(lang.getString("CellPhone") + ":");
    jLabel7.setPreferredSize(new Dimension(110, 20));
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
    txtMobileNo.setRequestFocusEnabled(true);
    txtMobileNo.setSelectionStart(0);
    txtMobileNo.setText("");
        txtMobileNo.addKeyListener(new PanelCustomerModify_txtMobileNo_keyAdapter(this));
    txtWorkNo.setText("");
        txtWorkNo.addKeyListener(new PanelCustomerModify_txtWorkNo_keyAdapter(this));
    txtWorkNo.setSelectionStart(0);
    txtWorkNo.setPreferredSize(new Dimension(150, 20));
    txtWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtHomeNo.setText("");
        txtHomeNo.addKeyListener(new PanelCustomerModify_txtHomeNo_keyAdapter(this));
    txtHomeNo.setSelectionStart(0);
    txtHomeNo.setPreferredSize(new Dimension(150, 20));
    txtHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAddress2.setText("");
        txtAddress2.addKeyListener(new PanelCustomerModify_txtAddress2_keyAdapter(this));
    txtAddress2.setSelectionStart(0);
    txtAddress2.setPreferredSize(new Dimension(150, 20));
    txtAddress2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAddress1.setText("");
        txtAddress1.addKeyListener(new PanelCustomerModify_txtAddress1_keyAdapter(this));
    txtAddress1.setSelectionStart(0);
    txtAddress1.setPreferredSize(new Dimension(150, 20));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAbbrName.setText("");
        txtAbbrName.addKeyListener(new PanelCustomerModify_txtAbbrName_keyAdapter(this));
    txtAbbrName.setSelectionStart(0);
    txtAbbrName.setPreferredSize(new Dimension(150, 20));
    txtAbbrName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtName.setText("");
        txtName.addKeyListener(new PanelCustomerModify_txtName_keyAdapter(this));
    txtName.setSelectionStart(0);
    txtName.setPreferredSize(new Dimension(150, 20));
    txtName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
//
    txtEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtEmail.setPreferredSize(new Dimension(150, 20));
    txtEmail.setSelectionStart(0);
    txtEmail.setText("");
    txtCity.setText("");
    txtCity.setSelectionStart(0);
    txtCity.setPreferredSize(new Dimension(150, 20));
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCounty.setText("");
    txtCounty.setSelectionStart(0);
    txtCounty.setPreferredSize(new Dimension(150, 20));
    txtCounty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCountry.setText("");
    txtCountry.setSelectionStart(0);
    txtCountry.setPreferredSize(new Dimension(150, 20));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtFax1.setText("");
    txtFax1.setSelectionStart(0);
    txtFax1.setPreferredSize(new Dimension(150, 20));
    txtFax1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtFax2.setText("");
    txtFax2.setSelectionStart(0);
    txtFax2.setPreferredSize(new Dimension(150, 20));
    txtFax2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCreatedDate.setText("");
    txtCreatedDate.setSelectionStart(0);
    txtCreatedDate.setPreferredSize(new Dimension(150, 20));
    txtCreatedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCreatedDate.setText(ut.getSystemDate());
    txtCreatedDate.setEditable(false);
//
    jPanel5.setBackground(SystemColor.control);
    jPanel5.setPreferredSize(new Dimension(180, 10));
    jPanel9.setPreferredSize(new Dimension(800, 300));
    jPanel9.setLayout(borderLayout4);
    jPanel10.setPreferredSize(new Dimension(800, 50));
    jPanel10.setLayout(borderLayout5);
    jPanel11.setPreferredSize(new Dimension(800, 220));
    jPanel11.setLayout(gridLayout2);
    jPanel12.setBackground(SystemColor.control);
    jPanel12.setPreferredSize(new Dimension(100, 50));
    jPanel13.setBackground(SystemColor.control);
    jPanel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    jPanel13.setPreferredSize(new Dimension(500, 50));
    jPanel13.setLayout(null);
    txtComment.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtComment.setMaximumSize(new Dimension(0, 0));
    txtComment.setMinimumSize(new Dimension(0, 0));
    txtComment.setPreferredSize(new Dimension(0, 0));
    txtComment.setText("");
    txtComment.setLineWrap(true);
    txtComment.setWrapStyleWord(true);
        txtComment.addKeyListener(new PanelCustomerModify_txtComment_keyAdapter(this));
    jLabel15.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    jLabel15.setPreferredSize(new Dimension(90, 15));
    jLabel15.setText(lang.getString("Comment") + ":");
    jPanel14.setBackground(SystemColor.control);
    jPanel14.setPreferredSize(new Dimension(800, 30));
    jScrollPane2.setPreferredSize(new Dimension(450, 45));
    jScrollPane2.setBounds(new Rectangle(35, 5, 483, 45));
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setPreferredSize(new Dimension(800, 250));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
 //   btnCancel.setPreferredSize(new Dimension(80, 35));
    btnCancel.setText("<html><center><br><b>"+lang.getString("Cancel") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                       "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                       "ml>");
    btnCancel.addActionListener(new PanelCustomerModify_btnCancel_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
 //   btnAdd.setPreferredSize(new Dimension(80, 35));
    btnAdd.setText(lang.getString("Modify") + " (F4)");
    btnAdd.addActionListener(new PanelCustomerModify_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
 //   btnDone.setPreferredSize(new Dimension(80, 35));
    btnDone.setText(lang.getString("Done") + " (F1)");
    btnDone.addActionListener(new PanelCustomerModify_btnDone_actionAdapter(this));
    table.addKeyListener(new PanelCustomerModify_table_keyAdapter(this));
    this.add(jPanel2, BorderLayout.WEST);
    jPanel2.add(jPanel4, BorderLayout.WEST);
    jPanel4.add(jLabel6, null);
    jPanel4.add(jLabel5, null);
    jPanel4.add(jLabel4, null);
    jPanel4.add(jLabel3, null);
    jPanel4.add(jLabel2, null);
    jPanel4.add(jLabel1, null);
    jPanel4.add(jLabel7, null);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel2.add(jPanel8,BorderLayout.EAST);
    jPanel5.add(txtName, null);
    jPanel5.add(txtAbbrName, null);
    jPanel5.add(txtAddress1, null);
    jPanel5.add(txtAddress2, null);
    jPanel5.add(txtHomeNo, null);
    jPanel5.add(txtWorkNo, null);
    jPanel5.add(txtMobileNo, null);
    this.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jPanel6, BorderLayout.WEST);
    jPanel6.add(jLabel8,null);
    jPanel6.add(jLabel9,null);
    jPanel6.add(jLabel10,null);
    jPanel6.add(jLabel11,null);
    jPanel6.add(jLabel12,null);
    jPanel6.add(jLabel13,null);
    jPanel6.add(jLabel14,null);
    jPanel3.add(jPanel7, BorderLayout.CENTER);
    jPanel7.add(txtEmail,null);
    jPanel7.add(txtCity,null);
    jPanel7.add(txtCounty,null);
    jPanel7.add(txtCountry,null);
    jPanel7.add(txtFax1,null);
    jPanel7.add(txtFax2,null);
    jPanel7.add(txtCreatedDate,null);
    this.add(jPanel9,BorderLayout.SOUTH);
    jPanel9.add(jPanel11, BorderLayout.SOUTH);
    jPanel11.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    table.setRowHeight(30);
//    table.setAutoResizeMode(BJTable.AUTO_RESIZE_OFF);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    jPanel9.add(jPanel10, BorderLayout.NORTH);
    jPanel10.add(jPanel12, BorderLayout.WEST);
    jPanel12.add(jLabel15, null);
    jPanel10.add(jPanel13, BorderLayout.CENTER);
    jPanel13.add(jScrollPane2, null);
    jScrollPane2.getViewport().add(txtComment,null);
    jPanel9.add(jPanel14, BorderLayout.CENTER);
    //String[] columnNames = new String[]{"ID","Name","Address 1","Created Date"};
    String[] columnNames = new String[]{lang.getString("ItemID"),lang.getString("ItemName"),lang.getString("Address1"),lang.getString("CreatedDate")};
    dm.setDataVector(new Object[][]{},columnNames);
    /*table.getColumn("Ma").setPreferredWidth(150);
    table.getColumn("Ten").setPreferredWidth(150);
    table.getColumn("Dia chi 1").setPreferredWidth(200);
    table.getColumn("Ngay tao").setPreferredWidth(150);*/
    table.getColumnModel().getColumn(0).setPreferredWidth(150);
    table.getColumnModel().getColumn(1).setPreferredWidth(150);
    table.getColumnModel().getColumn(2).setPreferredWidth(200);
    table.getColumnModel().getColumn(3).setPreferredWidth(150);
//    table.setAutoResizeMode(BJTable.AUTO_RESIZE_ALL_COLUMNS);
  }
  //Show data
  void showData(){
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
    ResultSet rs = null;
    try{
      String sql = "select * from BTM_POS_CUSTOMER where cust_id ='" + custID + "'";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql);
      if (rs.next()){
        txtName.setText(rs.getString("last_name"));
        txtAbbrName.setText(rs.getString("first_name"));
        txtAddress1.setText(rs.getString("address1"));
        txtAddress2.setText(rs.getString("address2"));
        txtHomeNo.setText(rs.getString("home_no"));
        txtWorkNo.setText(rs.getString("work_no"));
        txtMobileNo.setText(rs.getString("mobile_no"));
        txtEmail.setText(rs.getString("email"));
        txtCity.setText(rs.getString("city"));
        txtCounty.setText(rs.getString("county"));
        txtCountry.setText(rs.getString("country"));
        txtFax1.setText(rs.getString("fax1"));
        txtFax2.setText(rs.getString("fax2"));
        txtCreatedDate.setText("" + rs.getDate("created_date"));
        txtComment.setText(rs.getString("cust_comment"));
        if(rs.getString("cust_flag").equals("0")){
          jLabel6.setText(lang.getString("LastName") + ":");
          jLabel5.setText(lang.getString("FirstName") + ":");
         }
        else{
          jLabel6.setText(lang.getString("CustomerName") + ":");
          jLabel5.setText(lang.getString("AbbreviationName") + ":");
        }

      }
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  //show button
  void showButton(){
    frmMain.showButton(btnDone);
    frmMain.showButton(btnAdd);
    frmMain.showButton(btnCancel);
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
    if (lastName.equals("")){
      if(jLabel6.getText().equalsIgnoreCase (lang.getString("LastName") + ":")){
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS109_LastNameNotNull"));
      }else{
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS110_CusNameNotNull"));
      }
      txtName.requestFocus(true);
      return;
    }
    if (firstName.equals("")){
      if(jLabel6.getText().equalsIgnoreCase (lang.getString("LastName") + ":")){
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS111_FirstNameNotNull"));
      }else{
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS112_AbbreviationNameNotNull"));
      }
      txtName.requestFocus(true);
      return;
    }
    if (address1.equals("")){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS113_AddressNotNull"));
      txtAddress1.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(lastName,120)){
      if(jLabel6.getText().equalsIgnoreCase (lang.getString("LastName") + ":")){
        ut.showMessage(frmMain,  lang.getString("TT001"),lang.getString("MS114_LastNameTooLong"));
      }else{
        ut.showMessage(frmMain,  lang.getString("TT001"),lang.getString("MS115_CustomerNameTooLong"));
      }
      txtName.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(firstName,120)){
      if(jLabel6.getText().equalsIgnoreCase (lang.getString("LastName") + ":")){
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS116_FirstNameTooLong"));
      }else{
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS117_AbbreviationNameTooLong"));
      }
      txtAbbrName.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(address1,150)){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS118_AddressTooLong"));
      txtAddress1.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(address2,150)){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS119_AddressTooLong"));
      txtAddress2.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(homeNo,15)){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS120_HomePhoneTooLong"));
      txtHomeNo.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(workNo,15)){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS121_WorkPhoneTooLong"));
      txtWorkNo.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(mobileNo,15)){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS122_CellPhoneTooLong"));
      txtMobileNo.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(email,125)){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS123_EmailTooLong"));
      txtEmail.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(city,50)){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS124_CityTooLong"));
      txtCity.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(county,50)){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS125_CountyTooLong"));
      txtCounty.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(country,50)){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS126_CountryTooLong"));
      txtCountry.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(fax1,15)){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS127_FaxTooLong"));
      txtFax1.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(fax2,15)){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS116_FirstNameTooLong"));
      txtFax2.requestFocus(true);
      return;
    }
    if (cusBusObj.checkLength(custComment,250)){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS128_CommentTooLong"));
      txtComment.requestFocus(true);
      return;
    }

    if (ut.checkPhoneNumber(homeNo) == false){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS129_WrongHomePhone"));
      txtHomeNo.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(workNo) == false){
      ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS130_WrongWorkPhone"));
      txtWorkNo.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(mobileNo) == false){
      ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS131_WrongCellPhone"));
      txtMobileNo.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(fax1) == false){
      ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS132_WrongFax"));
      txtFax1.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(fax2) == false){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS133_WrongFax"));
      txtFax2.requestFocus(true);
      return;
    }
    String sql = "Update BTM_POS_CUSTOMER set first_name ='" + firstName +
        "', last_name='" + lastName + "',address1 ='" + address1 +
        "', address2 ='" + address2 + "', home_no ='" + homeNo +
        "', work_no ='" + workNo + "', mobile_no ='" + mobileNo +
        "',fax1 ='" + fax1 + "', email ='" + email + "', city ='" + city + "', county ='" + county +
        "', country ='" + country + "', cust_comment ='" + custComment + "', fax2 ='" + fax2 +
        "'  where cust_id = '" + custID + "'";
//    System.out.println(sql);
    DAL.executeUpdate(sql);
    while (dm.getRowCount()>0){
      dm.removeRow(0);
    }
    String[] rowData = new String[] {custID,firstName,address1,createdDate};
    dm.addRow(rowData);
    str.installInTable(table, Color.lightGray, Color.white, null, null);
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
    DialogCustomerSearch search = new DialogCustomerSearch(frmMain,lang.getString("TT054"),true,frmMain);
    search.searchData(frmMain.pnlCustomerSetup.rowsNum);
    if (search.table.getRowCount()>0){
      search.table.setRowSelectionInterval(0,0);
    }

    search.setVisible(true);

    if (search.done == false){
      frmMain.setTitle(lang.getString("TT056"));
      frmMain.showScreen(Constant.SCR_CUSTOMER_SETUP);
    }else {
      custID = search.getData();
      showData();
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    DialogCustomerSearch search = new DialogCustomerSearch(frmMain,lang.getString("TT054"),true,frmMain);
    search.searchData(frmMain.pnlCustomerSetup.rowsNum);
    search.setVisible(true);
    if (search.done == false){
      frmMain.setTitle(lang.getString("TT056"));
      frmMain.showScreen(Constant.SCR_CUSTOMER_SETUP);
    }else {
      custID = search.getData();
      showData();
    }

  }

  void txtName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtName,Customer.LEN_CUST_NAME);
  }

  void txtAbbrName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAbbrName,Customer.LEN_CUST_ABB_NAME);
  }

  void txtAddress1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAddress1,Customer.LEN_CUST_ADDRESS);
  }

  void txtAddress2_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAddress2,Customer.LEN_CUST_ADDRESS);
  }

  void txtHomeNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtHomeNo,Customer.LEN_CUST_PHONE);
  }

  void txtWorkNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtWorkNo,Customer.LEN_CUST_PHONE);
  }

  void txtMobileNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtWorkNo,Customer.LEN_CUST_PHONE);
  }

  void txtEmail_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtEmail,Customer.LEN_CUST_EMAIL);
  }

  void txtCity_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCity,Customer.LEN_CUST_CITY);
  }

  void txtCounty_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCounty,Customer.LEN_CUST_COUNTY);
  }

  void txtCountry_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCountry,Customer.LEN_CUST_COUNTRY);
  }

  void txtFax1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtFax1,Customer.LEN_CUST_FAX);
  }

  void txtFax2_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtFax2,Customer.LEN_CUST_FAX);
  }

  void txtComment_keyTyped(KeyEvent e) {
    ut.limitLenjTextArea(e,txtComment,Customer.LEN_CUST_COMMENT);
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
    // F12
    key = new Integer(KeyEvent.VK_F12);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
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

    //  if(flag == true){//4 nut
        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnDone.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F4) {
          btnAdd.doClick();
        }
        else if (identifier.intValue()==KeyEvent.VK_F12 || identifier.intValue()==KeyEvent.VK_ESCAPE){
          btnCancel.doClick();
        }
       }
    }
    private void catchHotKey(KeyEvent e) {
               if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnAdd.doClick();
               }
             }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
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

class PanelCustomerModify_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerModify adaptee;

  PanelCustomerModify_btnDone_actionAdapter(PanelCustomerModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
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

class PanelCustomerModify_txtComment_keyAdapter extends java.awt.event.KeyAdapter {
    PanelCustomerModify adaptee;

    PanelCustomerModify_txtComment_keyAdapter(PanelCustomerModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtComment_keyTyped(e);
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

