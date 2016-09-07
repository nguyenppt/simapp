package sim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import btm.attr.*;
import btm.swing.*;
import java.util.*;
import common.*;
import btm.business.EmployeeBusObj.*;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Admin -> Employee -> Employee Modify</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Vo Ha Thanh Huy
 * @version 1.0
 */

public class PanelAdminEmployeeModify extends JPanel {
  //define for panel
  FrameMainSim frmMain;
  Statement stmt = null;
  CardLayout cardLayout;
  JPanel parent;
  DefineEncrypt enc=new DefineEncrypt();
  int rowTableSelect;
  Employee emp =new Employee();
  btm.business.EmployeeBusObj empBusObj = new btm.business.EmployeeBusObj();
  Utils ut = new Utils();

  DataAccessLayer DAL;
  btm.business.EmployeeBusObj epl = new btm.business.EmployeeBusObj();


  BorderLayout borderLayout1 = new BorderLayout();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  JScrollPane jScrollPane1 = new JScrollPane();
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPanel11 = new JPanel();
  BJButton btnCancel = new BJButton();
  BJButton btnAdd = new BJButton();
  JPanel jPanel15 = new JPanel();
  JLabel lblBirthday = new JLabel();
  JLabel lblHomeNo = new JLabel();
  JTextField txtAddress = new JTextField();
  JLabel lblCity = new JLabel();
  JLabel lblMobileNo = new JLabel();
  JTextField txtEmail = new JTextField();
  JLabel lblConfirmPassword = new JLabel();
  JLabel lblWorkNo = new JLabel();
  JLabel lblEmail = new JLabel();
  JLabel lblEmployeeID = new JLabel();
  JTextField txtHomeNo = new JTextField();
  JTextField txtMobileNo = new JTextField();
  JLabel jLabel117 = new JLabel();
  JTextField txtCountry = new JTextField();
  JTextField txtFirstName = new JTextField();
  JLabel jLabel113 = new JLabel();
  JLabel jLabel111 = new JLabel();
  JLabel lblEmployeeCode = new JLabel();
  JLabel lblCountry = new JLabel();
  JTextField txtCity = new JTextField();
  JTextField txtLastName = new JTextField();
  JLabel lblPassword = new JLabel();
  JLabel jLabel112 = new JLabel();
  JTextField txtBirthday = new JTextField();
  JLabel lblStore = new JLabel();
  JPanel jPanel1 = new JPanel();
  JButton btnDate = new JButton();
  JTextField txtEmployeeCode = new JTextField();
  JLabel jLabel118 = new JLabel();
  JLabel lblAddress = new JLabel();
  JLabel jLabel110 = new JLabel();
  JLabel lblLastName = new JLabel();
  JTextField txtWorkNo = new JTextField();
  JTextField txtEmployeeID = new JTextField();
  JLabel lblFirstName = new JLabel();
  JButton btnSearchStore = new JButton();
  JTextField txtStore = new JTextField();
  JLabel jLabel115 = new JLabel();
  JPasswordField txtPassword = new JPasswordField();
  JPasswordField txtConfirmPassword = new JPasswordField();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

  //create panel
  /**
   * @author 		Vo Ha Thanh Huy
   * @param 		FrameMainSim frmMain, CardLayout crdLayout, JPanel parent
   * @return		no
   */
  public PanelAdminEmployeeModify(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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
   * @author 		Vo Ha Thanh Huy
   * @param 		no
   * @return		no
   */
  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    //DAL.getConnfigParameter();

    jScrollPane1.setPreferredSize(new Dimension(800, 222));
    jPanel11.setPreferredSize(new Dimension(800, 254));
    jPanel11.setLayout(gridLayout2);
//  btnCancel.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setSelectedIcon(null);
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelAdminEmployeeModify_btnCancel_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setToolTipText(lang.getString("Modify")+" (F4)");
    btnAdd.setText("<html><center><b>"+lang.getString("Modify")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4<" +
    "/html>");
    btnAdd.addActionListener(new PanelAdminEmployeeModify_btnAdd_actionAdapter(this));
    jPanel15.setBackground(new Color(121, 152, 198));
    jPanel15.setPreferredSize(new Dimension(800, 50));
    lblBirthday.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblBirthday.setText(lang.getString("Birthday")+":");
    lblBirthday.setBounds(new Rectangle(411, 35, 86, 15));
    lblHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblHomeNo.setText(lang.getString("HomePhone")+":");
    lblHomeNo.setBounds(new Rectangle(8, 117, 91, 15));
    txtAddress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress.setNextFocusableComponent(txtCity);
    txtAddress.setText("");
    txtAddress.setBounds(new Rectangle(530, 61, 213, 21));
    txtAddress.addKeyListener(new PanelAdminEmployeeModify_txtAddress_keyAdapter(this));
    lblCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCity.setText(lang.getString("City")+":");
    lblCity.setBounds(new Rectangle(411, 94, 97, 15));
    lblMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblMobileNo.setText(lang.getString("CellPhone")+":");
    lblMobileNo.setBounds(new Rectangle(8, 171, 83, 15));
    txtEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmail.setMinimumSize(new Dimension(6, 22));
    txtEmail.setNextFocusableComponent(txtEmployeeCode);
    txtEmail.setText("");
    txtEmail.setBounds(new Rectangle(530, 143, 213, 21));
    txtEmail.addKeyListener(new PanelAdminEmployeeModify_txtEmail_keyAdapter(this));
    lblConfirmPassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblConfirmPassword.setText(lang.getString("EnterConfirmPassword")+":");
    lblConfirmPassword.setBounds(new Rectangle(8, 227, 127, 15));
    lblWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblWorkNo.setText(lang.getString("WorkPhone")+":");
    lblWorkNo.setBounds(new Rectangle(8, 146, 96, 15));
    lblEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmail.setText("Email:");
    lblEmail.setBounds(new Rectangle(411, 146, 80, 15));
    lblEmployeeID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmployeeID.setPreferredSize(new Dimension(150, 21));
    lblEmployeeID.setText(lang.getString("EmployeeID")+":");
    lblEmployeeID.setBounds(new Rectangle(8, 9, 99, 15));
    txtHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtHomeNo.setNextFocusableComponent(txtWorkNo);
    txtHomeNo.setText("");
    txtHomeNo.setBounds(new Rectangle(124, 115, 216, 21));
    txtHomeNo.addKeyListener(new PanelAdminEmployeeModify_txtHomeNo_keyAdapter(this));
    txtMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtMobileNo.setNextFocusableComponent(txtPassword);
    txtMobileNo.setText("");
    txtMobileNo.setBounds(new Rectangle(124, 170, 216, 21));
    txtMobileNo.addKeyListener(new PanelAdminEmployeeModify_txtMobileNo_keyAdapter(this));
    jLabel117.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel117.setForeground(Color.red);
    jLabel117.setBounds(new Rectangle(349, 199, 13, 16));
    jLabel117.setText("(*)");
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCountry.setNextFocusableComponent(txtEmail);
    txtCountry.setText("");
    txtCountry.setBounds(new Rectangle(530, 116, 213, 21));
    txtCountry.addKeyListener(new PanelAdminEmployeeModify_txtCountry_keyAdapter(this));
    txtFirstName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtFirstName.setNextFocusableComponent(txtLastName);
    txtFirstName.setText("");
    txtFirstName.setBounds(new Rectangle(124, 61, 216, 21));
    txtFirstName.addKeyListener(new PanelAdminEmployeeModify_txtFirstName_keyAdapter(this));
    jLabel113.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel113.setForeground(Color.red);
    jLabel113.setBounds(new Rectangle(755, 63, 13, 16));
    jLabel113.setText("(*)");
    jLabel111.setText("(*)");
    jLabel111.setBounds(new Rectangle(349, 61, 13, 16));
    jLabel111.setForeground(Color.red);
    jLabel111.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmployeeCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmployeeCode.setPreferredSize(new Dimension(150, 21));
    lblEmployeeCode.setText(lang.getString("EmployeeCode"));
    lblEmployeeCode.setBounds(new Rectangle(8, 35, 123, 15));
    lblCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCountry.setText(lang.getString("Country")+":");
    lblCountry.setBounds(new Rectangle(411, 120, 74, 15));
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCity.setNextFocusableComponent(txtCountry);
    txtCity.setText("");
    txtCity.setBounds(new Rectangle(530, 88, 213, 21));
    txtCity.addKeyListener(new PanelAdminEmployeeModify_txtCity_keyAdapter(this));
    txtLastName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtLastName.setNextFocusableComponent(lblHomeNo);
    txtLastName.setText("");
    txtLastName.setBounds(new Rectangle(124, 88, 216, 21));
    txtLastName.addKeyListener(new PanelAdminEmployeeModify_txtLastName_keyAdapter(this));
    lblPassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPassword.setText(lang.getString("Password")+":");
    lblPassword.setBounds(new Rectangle(8, 200, 81, 15));
    jLabel112.setText("(*)");
    jLabel112.setBounds(new Rectangle(349, 88, 13, 16));
    jLabel112.setForeground(Color.red);
    jLabel112.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtBirthday.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtBirthday.setNextFocusableComponent(txtAddress);
    txtBirthday.setText("");
    txtBirthday.setBounds(new Rectangle(530, 33, 213, 21));
    txtBirthday.addKeyListener(new PanelAdminEmployeeModify_txtBirthday_keyAdapter(this));
    lblStore.setText("jLabel1");
    lblStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStore.setText(lang.getString("Store")+":");
    lblStore.setBounds(new Rectangle(411, 9, 81, 15));
    jPanel1.setMinimumSize(new Dimension(800, 160));
    jPanel1.setPreferredSize(new Dimension(800, 80));
    jPanel1.setLayout(null);
    btnDate.setBounds(new Rectangle(750, 32, 22, 22));
    btnDate.setPreferredSize(new Dimension(30, 30));
    btnDate.setText("...");
    btnDate.addActionListener(new PanelAdminEmployeeModify_btnDate_actionAdapter(this));
    txtEmployeeCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmployeeCode.setNextFocusableComponent(txtFirstName);
    txtEmployeeCode.setPreferredSize(new Dimension(6, 21));
    txtEmployeeCode.setText("");
    txtEmployeeCode.setBounds(new Rectangle(124, 33, 216, 21));
    txtEmployeeCode.addKeyListener(new PanelAdminEmployeeModify_txtEmployeeCode_keyAdapter(this));
    jLabel118.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel118.setForeground(Color.red);
    jLabel118.setBounds(new Rectangle(349, 226, 13, 16));
    jLabel118.setText("(*)");
    lblAddress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAddress.setText(lang.getString("Address")+":");
    lblAddress.setBounds(new Rectangle(411, 61, 71, 21));
    jLabel110.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel110.setForeground(Color.red);
    jLabel110.setText("(*)");
    jLabel110.setBounds(new Rectangle(349, 33, 13, 16));
    lblLastName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblLastName.setText(lang.getString("LastName")+":");
    lblLastName.setBounds(new Rectangle(8, 90, 89, 15));
    txtWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtWorkNo.setNextFocusableComponent(txtMobileNo);
    txtWorkNo.setText("");
    txtWorkNo.setBounds(new Rectangle(124, 142, 216, 21));
    txtWorkNo.addKeyListener(new PanelAdminEmployeeModify_txtWorkNo_keyAdapter(this));
    txtEmployeeID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmployeeID.setPreferredSize(new Dimension(6, 22));
    txtEmployeeID.setEditable(false);
    txtEmployeeID.setText("");
    txtEmployeeID.setBounds(new Rectangle(124, 6, 216, 21));
    lblFirstName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFirstName.setOpaque(false);
    lblFirstName.setPreferredSize(new Dimension(150, 21));
    lblFirstName.setText(lang.getString("FirstName")+":");
    lblFirstName.setBounds(new Rectangle(8, 62, 126, 15));
    btnSearchStore.setText("...");
    btnSearchStore.addActionListener(new PanelAdminEmployeeModify_btnSearchStore_actionAdapter(this));
    btnSearchStore.setBounds(new Rectangle(750, 6, 23, 21));
    btnSearchStore.setPreferredSize(new Dimension(30, 30));
    txtStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtStore.setNextFocusableComponent(txtBirthday);
    txtStore.setText("");
    txtStore.setBounds(new Rectangle(530, 6, 213, 21));
    txtStore.addKeyListener(new PanelAdminEmployeeModify_txtStore_keyAdapter(this));
    jLabel115.setBounds(new Rectangle(780, 8, 13, 16));
    jLabel115.setText("(*)");
    jLabel115.setForeground(Color.red);
    jLabel115.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPassword.setNextFocusableComponent(txtConfirmPassword);
    txtPassword.setText("");
    txtPassword.setBounds(new Rectangle(124, 197, 216, 21));
    txtPassword.addKeyListener(new PanelAdminEmployeeModify_txtPassword_keyAdapter(this));
    txtConfirmPassword.setNextFocusableComponent(txtStore);
    txtConfirmPassword.setBounds(new Rectangle(124, 224, 216, 21));
    txtConfirmPassword.addKeyListener(new PanelAdminEmployeeModify_txtConfirmPassword_keyAdapter(this));
    table.addKeyListener(new PanelAdminEmployeeModify_table_keyAdapter(this));
    this.add(jPanel11,  BorderLayout.SOUTH);
    jPanel11.add(jScrollPane1, null);
    this.add(jPanel15, BorderLayout.NORTH);
    jPanel15.add(btnAdd, null);
    jPanel15.add(btnCancel, null);
    this.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(txtEmployeeCode, null);
    jPanel1.add(txtFirstName, null);
    jPanel1.add(jLabel110, null);
    jPanel1.add(jLabel111, null);
    jPanel1.add(txtLastName, null);
    jPanel1.add(jLabel112, null);
    jPanel1.add(txtHomeNo, null);
    jPanel1.add(txtWorkNo, null);
    jPanel1.add(txtMobileNo, null);
    jPanel1.add(txtConfirmPassword, null);
    jPanel1.add(txtPassword, null);
    jPanel1.add(jLabel117, null);
    jPanel1.add(jLabel118, null);
    jPanel1.add(lblConfirmPassword, null);
    jPanel1.add(lblPassword, null);
    jPanel1.add(lblMobileNo, null);
    jPanel1.add(lblWorkNo, null);
    jPanel1.add(lblHomeNo, null);
    jPanel1.add(lblLastName, null);
    jPanel1.add(lblFirstName, null);
    jPanel1.add(lblEmployeeCode, null);
    jPanel1.add(txtEmployeeID, null);
    jPanel1.add(lblStore, null);
    jPanel1.add(txtStore, null);
    jPanel1.add(lblEmployeeID, null);
    jPanel1.add(txtBirthday, null);
    jPanel1.add(lblBirthday, null);
    jPanel1.add(txtAddress, null);
    jPanel1.add(lblAddress, null);
    jPanel1.add(lblCity, null);
    jPanel1.add(txtCity, null);
    jPanel1.add(txtCountry, null);
    jPanel1.add(lblCountry, null);
    jPanel1.add(lblEmail, null);
    jPanel1.add(txtEmail, null);
    jPanel1.add(btnDate, null);
    jPanel1.add(jLabel113, null);
    jPanel1.add(btnSearchStore, null);
    jPanel1.add(jLabel115, null);
    jScrollPane1.add(table, null);
    jScrollPane1.setViewportView(table);
    //View Table
    String[] columnNames = new String[] {
         lang.getString("EmployeeID"), lang.getString("EmployeeCode"), lang.getString("Name"), lang.getString("StoreID"),lang.getString("HomePhone"),lang.getString("WorkPhone")};
     dm.setDataVector(new Object[][] {}
                      , columnNames);
     table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
     table.getTableHeader().setReorderingAllowed(false);
     table.getColumn(lang.getString("EmployeeID")).setPreferredWidth(150);
     table.getColumn(lang.getString("EmployeeCode")).setPreferredWidth(150);
     table.getColumn(lang.getString("Name")).setPreferredWidth(300);
     table.getColumn(lang.getString("StoreID")).setPreferredWidth(150);
     table.getColumn(lang.getString("HomePhone")).setPreferredWidth(150);
     table.getColumn(lang.getString("WorkPhone")).setPreferredWidth(150);
  };

  void btnAdd_actionPerformed(ActionEvent e) {
    //Update data
    String sEmpID = txtEmployeeID.getText().trim();
    String sEmpCode = txtEmployeeCode.getText().trim();
    String sStoreID = txtStore.getText().trim();
    String sFirstName = txtFirstName.getText().trim();
    String sLastName = txtLastName.getText().trim();
    String sSSN = "0";
    String sPass = txtPassword.getText().trim();
    String sConPass = txtConfirmPassword.getText().trim();
    String sAddress1 = txtAddress.getText().trim();
    String sAddress2 = "";
    String sHomeNo = txtHomeNo.getText().trim();
    String sWorkNo = txtWorkNo.getText().trim();
    String sMobileNo = txtMobileNo.getText().trim();
    String sEmail = txtEmail.getText().trim();
    String sCity = txtCity.getText().trim();
    String sCountry = txtCountry.getText().trim();
    String sCounty = "";
    String sStartDate = ut.getSystemDate(1);
    String sEndDate = "7/7/7777";
    String sBirthday = txtBirthday.getText().trim();
    String sSQL = "";
    //Check datda
    if (sEmpCode.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1036_EmployeeCodeNotNull"));
      txtEmployeeCode.requestFocus(true);
      return;
    }

    if (sFirstName.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1037_FirstNameNotNull"));
      txtFirstName.requestFocus(true);
      return;
    }

    if (sLastName.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1038_LastNameNotNull"));
      txtLastName.requestFocus(true);
      return;
    }

    if (sStoreID.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1039_StoreIDNotNull"));
      txtStore.requestFocus(true);
      return;
    }
    if (!empBusObj.checkStore(sStoreID, DAL)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1040_StoreIDNotInvalid"));
      txtStore.requestFocus(true);
      try{
        stmt.close();
      }catch(Exception ex){};
      return;
    }

    if (sAddress1.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1041_AddressNotNull"));
      txtAddress.requestFocus(true);
      return;
    }

    if (sPass.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1042_PasswordNotNull"));
      txtPassword.requestFocus(true);
      return;
    }

    if (sConPass.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1043_ConfirmPasswordNotNull"));
      txtConfirmPassword.requestFocus(true);
      return;
    }

    if (!sPass.equals(sConPass)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1044_PasswordEnteredNotMatch") +". "+
                     lang.getString("MS1045_TypeNewPasswordBothTextBoxes"));
      txtConfirmPassword.requestFocus(true);
      return;
    }

    if (empBusObj.checkLength(sEmpCode, 10)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1046_EmployeeCodeTooLong"));
      txtEmployeeCode.requestFocus(true);
      return;
    }

    if (empBusObj.checkLength(sStoreID, 6)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1047_StoreIDTooLong"));
      txtStore.requestFocus(true);
      return;
    }
    if (empBusObj.checkLength(sFirstName, 120)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1048_FirstNameTooLong"));
      txtFirstName.requestFocus(true);
      return;
    }
    if (empBusObj.checkLength(sLastName, 120)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1049_EmployeeCodeTooLong"));
      txtLastName.requestFocus(true);
      return;
    }
    if (empBusObj.checkLength(sAddress1, 150)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                    lang.getString("MS1050_AddressTooLong"));
      txtAddress.requestFocus(true);
      return;
    }
    if (empBusObj.checkLength(sHomeNo, 15)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1051_HomePhoneTooLong"));
      txtHomeNo.requestFocus(true);
      return;
    }
    if (empBusObj.checkLength(sWorkNo, 15)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                    lang.getString("MS1052_WorkPhoneTooLong"));
      txtWorkNo.requestFocus(true);
      return;
    }
    if (empBusObj.checkLength(sMobileNo, 15)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1053_MobilePhoneTooLong"));
      txtMobileNo.requestFocus(true);
      return;
    }
    if (empBusObj.checkLength(sEmail, 125)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1054_EmailTooLong"));
      txtEmail.requestFocus(true);
      return;
    }
    if (empBusObj.checkLength(sCity, 50)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1055_CityTooLong"));
      txtCity.requestFocus(true);
      return;
    }
    if (empBusObj.checkLength(sCountry, 50)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1056_CountryTooLong"));
      txtCountry.requestFocus(true);
      return;
    }
    if (ut.checkPhoneNumber(sHomeNo) == false) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1057_WrongHomePhoneNumber"));
      txtHomeNo.requestFocus(true);
      return;
    }

    if (ut.checkPhoneNumber(sWorkNo) == false) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1058_WrongWorkPhoneNumber"));
      txtWorkNo.requestFocus(true);
      return;
    }

    if (ut.checkPhoneNumber(sMobileNo) == false) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1059_WrongCellPhoneNumber"));
      txtMobileNo.requestFocus(true);
      return;
    }
    if (!ut.checkDate(sBirthday, "/") && !sBirthday.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1060_WrongBirthday"));
      txtBirthday.requestFocus(true);
      return;
    }
     if (empBusObj.checkEmployeeCode(sEmpID, sEmpCode, DAL) ||
        checkEmployeeCodeInTable(sEmpCode)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1061_EmployeeCodeNotInvalid"));
      txtEmployeeCode.requestFocus(true);
      return;
    }

    if (!empBusObj.checkStore(sStoreID, DAL)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1040_StoreIDNotInvalid"));
      txtStore.requestFocus(true);
      return;
    }

    if (sBirthday.equals("")) {
      sBirthday = "1/1/1900";
    }
    String sEncrypt = enc.encryptData(sPass);
    sSQL = "Update BTM_IM_EMPLOYEE set EMP_ID = '" + txtEmployeeID.getText() +
        "', EMP_CDE = '" + sEmpCode + "', STORE_ID = '" + sStoreID +
        "', FIRST_NAME = '" + sFirstName + "', LAST_NAME = '" + sLastName +
        "', SSN = '" + sSSN + "', PASSWD = '" + sEncrypt + "', ADDRESS1 = '" + sAddress1 +
        "', ADDRESS2 = '" + sAddress2 + "', HOME_NO = '" + sHomeNo + "', WORK_NO = '" + sWorkNo +
        "', MOBILE_NO = '" + sMobileNo + "', EMAIL = '" + sEmail + "', CITY = '" + sCity +
        "', COUNTY = '" +  sCounty + "', COUNTRY = '" + sCountry + "',START_DATE = to_date('" + ut.getSystemDate(1) + "','DD-MM-YYYY'), BIRTHDAY = to_date('" + sEndDate + "','DD-MM-YYYY')" +
        " where EMP_ID ='" + txtEmployeeID.getText() + "'";
//    System.out.println(sSQL);
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                               TYPE_SCROLL_INSENSITIVE,
                                               ResultSet.CONCUR_READ_ONLY);
      DAL.executeUpdate(sSQL, stmt);
      stmt.close();
    }
    catch(Exception ex){};

    //Show dialog search
    DialogAdminEmployeeSearch dlgAdminSearch = new DialogAdminEmployeeSearch(
        frmMain, lang.getString("TT0019"), true, frmMain,
        frmMain.employeeBusObject);
    dlgAdminSearch.setValueSearch(frmMain.employeeBusObject.getEmpID(),
                                  frmMain.employeeBusObject.getEmpCode(),
                                  frmMain.employeeBusObject.getStoreID(),
                                  frmMain.employeeBusObject.getFirstName(),
                                  frmMain.employeeBusObject.getLastName(),
                                  frmMain.employeeBusObject.getSSN(),
                                  frmMain.employeeBusObject.getAddress2(),
                                  frmMain.employeeBusObject.getAddress1(),
                                  frmMain.employeeBusObject.getAddress2(),
                                  frmMain.employeeBusObject.getHomeNo(),
                                  frmMain.employeeBusObject.getWorkNo(),
                                  frmMain.employeeBusObject.getMobileNo(),
                                  frmMain.employeeBusObject.getEmail(),
                                  frmMain.employeeBusObject.getCity(),
                                  frmMain.employeeBusObject.getCountry(),
                                  frmMain.employeeBusObject.getCounty(),
                                  frmMain.employeeBusObject.getStartDate(),
                                  frmMain.employeeBusObject.getEndDate(),
                                  frmMain.employeeBusObject.getBirthday());
    dlgAdminSearch.txtRowLimit1.setText(frmMain.employeeBusObject.getRow());
    dlgAdminSearch.searchData(frmMain.employeeBusObject.getRow());
    dlgAdminSearch.setVisible(true);
    if (dlgAdminSearch.done == false) {
      frmMain.showScreen(Constant.SCRS_ADMIN_EMPLOYEE);
    }
    else {
      showData(dlgAdminSearch.getData(), DAL);
      txtEmployeeCode.requestFocus(true);
    }
  }

  boolean checkEmployeeCodeInTable(String sEmpCode) {
    boolean bFlag = false;
    int i, j;
    for (i = 0; i < table.getRowCount(); i++) {
      if (String.valueOf(table.getValueAt(i, 1)).equals(sEmpCode)) {
        bFlag = true;
      }
    }
    return bFlag;
  }

  void btnSearchStore_actionPerformed(ActionEvent e) {
    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,
        lang.getString("TT0004"), true, frmMain, frmMain.storeBusObj);
    dlgStoreSearch.setVisible(true);
    if (dlgStoreSearch.done) {
      String storeID = dlgStoreSearch.getData();
      frmMain.pnlAdminEmployeeModify.txtStore.setText(storeID);
      frmMain.pnlAdminEmployeeModify.txtBirthday.requestFocus(true);
      frmMain.showScreen(Constant.SCRS_ADMIN_EMPLOYEE_MODIFY);
    }
  }
  //Show data
  void showData(String empID,DataAccessLayer DAL){

    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                             TYPE_SCROLL_INSENSITIVE,
                                             ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = empBusObj.getDataEmployee(empID, DAL,stmt);
      rs.beforeFirst();
      if (rs.next()) {
         txtEmployeeID.setText(rs.getString("EMP_ID"));
         txtEmployeeCode.setText(rs.getString("EMP_CDE"));
         txtStore.setText(rs.getString("STORE_ID"));
         txtFirstName.setText(rs.getString("FIRST_NAME"));
         txtLastName.setText(rs.getString("LAST_NAME"));
         String sEncrypt = enc.decryptData(rs.getString("PASSWD"));
         txtPassword.setText(sEncrypt);
         txtConfirmPassword.setText(sEncrypt);
         txtAddress.setText(rs.getString("ADDRESS1"));
         txtHomeNo.setText(rs.getString("HOME_NO"));
         txtWorkNo.setText(rs.getString("WORK_NO"));
         txtMobileNo.setText(rs.getString("MOBILE_NO"));
         txtEmail.setText(rs.getString("EMAIL"));
         txtCity.setText(rs.getString("CITY"));
         txtCountry.setText(rs.getString("COUNTRY"));
         java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
         txtBirthday.setText("" + fd.format(rs.getDate("BIRTHDAY")));
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  void btnDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = frmMain.getX();
    posYFrame = frmMain.getY();
    posX = this.btnDate.getX() + posXFrame - 500;
    posY = this.btnDate.getY() + posYFrame + 90;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
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
        this.txtAddress.requestFocus(true);
      }
    }

  }

  void txtEmployeeCode_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtEmployeeCode,Employee.LEN_EMP_CDE);
     ut.checkCode(e);
     navigationComp(e,txtFirstName);
   }

   void navigationComp(KeyEvent e, JTextField txt) {
     if (e.getKeyChar() == KeyEvent.VK_ENTER ||
         e.getKeyChar() == KeyEvent.VK_TAB) {
       txt.requestFocus(true);
       txt.selectAll();
     }
   }


   void txtFirstName_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtFirstName,Employee.LEN_EMP_FIRST_NAME);
     navigationComp(e,txtLastName);
   }

   void txtLastName_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtLastName,Employee.LEN_EMP_LAST_NAME);
     navigationComp(e,txtHomeNo);
   }

   void txtStore_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtStore,Employee.LEN_EMP_STORE_ID);
     ut.checkNumber(e);
     if (e.getKeyChar() == KeyEvent.VK_ENTER ||
         e.getKeyChar() == KeyEvent.VK_TAB) {
       btnSearchStore.requestFocus(true);
     }
   }

   void txtAddress_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtAddress,Employee.LEN_EMP_ADDRESS);
     navigationComp(e,txtCity);
   }

   void txtHomeNo_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtHomeNo,Employee.LEN_EMP_PHONE);
     ut.checkPhone(e);
     navigationComp(e,txtWorkNo);
   }

   void txtMobileNo_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtMobileNo,Employee.LEN_EMP_PHONE);
     ut.checkPhone(e);
     navigationComp(e,txtPassword);
   }

   void txtWorkNo_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtWorkNo,Employee.LEN_EMP_PHONE);
     ut.checkPhone(e);
     navigationComp(e,txtMobileNo);
   }

   void txtEmail_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtEmail,Employee.LEN_EMP_EMAIL);
     if (e.getKeyChar() == KeyEvent.VK_ENTER ){
       btnAdd.doClick();
     }
   }

   void txtCity_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtCity,Employee.LEN_EMP_COUNTRY);
     navigationComp(e,txtCountry);
   }

   void txtPassword_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtPassword,Employee.LEN_EMP_PASSWORD);
     navigationComp(e,txtConfirmPassword);
   }

   void txtConfirmPassword_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtConfirmPassword,Employee.LEN_EMP_PASSWORD);
     navigationComp(e,txtStore);
   }

   void txtCountry_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e,txtCountry,Employee.LEN_EMP_COUNTRY);
     navigationComp(e,txtEmail);
   }

  void btnCancel_actionPerformed(ActionEvent e) {
    DialogAdminEmployeeSearch dlgAdminSearch = new DialogAdminEmployeeSearch(
        frmMain, lang.getString("TT0019"), true, frmMain,
        frmMain.employeeBusObject);
    dlgAdminSearch.setValueSearch(frmMain.employeeBusObject.getEmpID(),
                                  frmMain.employeeBusObject.getEmpCode(),
                                  frmMain.employeeBusObject.getStoreID(),
                                  frmMain.employeeBusObject.getFirstName(),
                                  frmMain.employeeBusObject.getLastName(),
                                  frmMain.employeeBusObject.getSSN(),
                                  frmMain.employeeBusObject.getAddress2(),
                                  frmMain.employeeBusObject.getAddress1(),
                                  frmMain.employeeBusObject.getAddress2(),
                                  frmMain.employeeBusObject.getHomeNo(),
                                  frmMain.employeeBusObject.getWorkNo(),
                                  frmMain.employeeBusObject.getMobileNo(),
                                  frmMain.employeeBusObject.getEmail(),
                                  frmMain.employeeBusObject.getCity(),
                                  frmMain.employeeBusObject.getCountry(),
                                  frmMain.employeeBusObject.getCounty(),
                                  frmMain.employeeBusObject.getStartDate(),
                                  frmMain.employeeBusObject.getEndDate(),
                                  frmMain.employeeBusObject.getBirthday());
    dlgAdminSearch.txtRowLimit1.setText(frmMain.employeeBusObject.getRow());
    dlgAdminSearch.searchData(frmMain.employeeBusObject.getRow());
    dlgAdminSearch.setVisible(true);
    if (dlgAdminSearch.done == false){
      frmMain.showScreen(Constant.SCRS_ADMIN_EMPLOYEE);
    }else{
      showData(dlgAdminSearch.getData(),DAL);
      txtEmployeeCode.requestFocus(true);
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
     if (identifier.intValue() == KeyEvent.VK_F1 ) {
       btnAdd.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
         btnCancel.doClick();
       }
     }
   }

  void txtBirthday_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      btnDate.requestFocus(true);
    }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnCancel.doClick();
               }
  }
}

class PanelAdminEmployeeModify_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_btnAdd_actionAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelAdminEmployeeModify_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_btnCancel_actionAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelAdminEmployeeModify_btnSearchStore_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_btnSearchStore_actionAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchStore_actionPerformed(e);
  }
}

class PanelAdminEmployeeModify_btnDate_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_btnDate_actionAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class PanelAdminEmployeeModify_txtEmployeeCode_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtEmployeeCode_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEmployeeCode_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtFirstName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtFirstName_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtFirstName_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtLastName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtLastName_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtLastName_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtStore_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtStore_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtStore_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtAddress_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtAddress_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtHomeNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtHomeNo_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtHomeNo_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtMobileNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtMobileNo_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtMobileNo_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtWorkNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtWorkNo_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtWorkNo_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtEmail_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtEmail_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEmail_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtCity_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtCity_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCity_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtCountry_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtCountry_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCountry_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtPassword_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtPassword_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPassword_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtConfirmPassword_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtConfirmPassword_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtConfirmPassword_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_txtBirthday_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_txtBirthday_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtBirthday_keyTyped(e);
  }
}

class PanelAdminEmployeeModify_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployeeModify adaptee;

  PanelAdminEmployeeModify_table_keyAdapter(PanelAdminEmployeeModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}



