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
 * <p>Description: Main -> Main -> Employee</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Vo Ha Thanh Huy
 * @version 1.0
 */

public class PanelAdminEmployee extends JPanel {
  //define for panel
  FrameMainSim frmMain;
  CardLayout cardLayout;
  int rowTableSelect;
  Statement stmt = null;
  DefineEncrypt enc = new DefineEncrypt();
  Employee emp = new Employee();
  JPanel parent;
  int rowNum;
  boolean flagSetHotKeyForAdd = true;
  Utils ut = new Utils();
  DataAccessLayer DAL;
  btm.business.EmployeeBusObj empBusObj = new btm.business.EmployeeBusObj();

  //Define Data on Panel
  Vector vSql = new Vector();
  Vector vArrayObject = new Vector();
  //define for panel
  BorderLayout borderLayout1 = new BorderLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  BJButton btnDelete = new BJButton();
  BJButton btnCancel = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  JPanel jPanel15 = new JPanel();
  BJButton btnClear = new BJButton();
  BJButton btnModify = new BJButton();
  JPanel jPanel1 = new JPanel();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm, true) {
    public boolean isCellEditable(int row, int col) {
      return false;
    }
  };
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,
      Color.lightGray, Color.white, null, null);
  JScrollPane jScrollPane1 = new JScrollPane();
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPanel11 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  FlowLayout flowLayout5 = new FlowLayout();
  JLabel lblEmployeeID = new JLabel();
  JLabel lblEmployeeCode = new JLabel();
  JLabel lblFirstName = new JLabel();
  JTextField txtEmployeeID = new JTextField();
  JTextField txtEmployeeCode = new JTextField();
  JTextField txtFirstName = new JTextField();
  JTextField txtLastName = new JTextField();
  JLabel lblLastName = new JLabel();
  JLabel jLabel110 = new JLabel();
  JLabel jLabel111 = new JLabel();
  JLabel jLabel112 = new JLabel();
  JLabel lblStore = new JLabel();
  JLabel lblBirthday = new JLabel();
  JTextField txtBirthday = new JTextField();
  JLabel lblAddress = new JLabel();
  JTextField txtAddress = new JTextField();
  JLabel jLabel113 = new JLabel();
  JLabel lblHomeNo = new JLabel();
  JTextField txtHomeNo = new JTextField();
  JTextField txtWorkNo = new JTextField();
  JTextField txtMobileNo = new JTextField();
  JLabel lblWorkNo = new JLabel();
  JLabel lblMobileNo = new JLabel();
  JLabel lblEmail = new JLabel();
  JTextField txtEmail = new JTextField();
  JLabel lblCity = new JLabel();
  JTextField txtCity = new JTextField();
  JLabel lblCountry = new JLabel();
  JTextField txtCountry = new JTextField();
  JLabel lblPassword = new JLabel();
  JLabel lblConfirmPassword = new JLabel();
  JLabel jLabel115 = new JLabel();
  JLabel jLabel116 = new JLabel();
  JLabel jLabel117 = new JLabel();
  JLabel jLabel118 = new JLabel();
  JButton btnDate = new JButton();
  JButton btnSearchStore = new JButton();
  JTextField txtStore = new JTextField();
  JLabel jLabel119 = new JLabel();
  JPasswordField txtPassword = new JPasswordField();
  JPasswordField txtConfirmPassword = new JPasswordField();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international
  //create panel
  /**
   * @author 		Vo Ha Thanh Huy
   * @param 		FrameMainSim frmMain, CardLayout crdLayout, JPanel parent
   * @return		no
   */
  public PanelAdminEmployee(FrameMainSim frmMain, CardLayout crdLayout,
                            JPanel parent) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      this.cardLayout = crdLayout;
      this.parent = parent;
      jbInit();
    }
    catch (Exception ex) {
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
    jPanel15.setLayout(flowLayout3);
    //DAL.getConnfigParameter();

    jPanel15.setPreferredSize(new Dimension(750, 50));
    jPanel15.setMinimumSize(new Dimension(610, 51));
    jPanel15.setBackground(new Color(121, 152, 198));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</h" +
        "tml>");
    btnDone.addActionListener(new PanelAdminEmployee_btnDone_actionAdapter(this));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setVisible(true);
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</h" +
        "tml>");
    btnAdd.addActionListener(new PanelAdminEmployee_btnAdd_actionAdapter(this));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</h" +
        "tml>");
    btnSearch.addActionListener(new PanelAdminEmployee_btnSearch_actionAdapter(this));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelAdminEmployee_btnCancel_actionAdapter(this));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setBounds(new Rectangle(541, 4, 120, 37));
//    btnCancel.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setVisible(true);
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</h" +
        "tml>");
    btnDelete.addActionListener(new PanelAdminEmployee_btnDelete_actionAdapter(this));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setPreferredSize(new Dimension(120, 37));
    this.setLayout(borderLayout1);
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear")+" (F7)");
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</h" +
        "tml>");
    btnClear.addActionListener(new PanelAdminEmployee_btnClear_actionAdapter(this));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify")+" (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</h" +
        "tml>");
    btnModify.addActionListener(new PanelAdminEmployee_btnModify_actionAdapter(this));
    btnModify.addMouseListener(new PanelAdminEmployee_btnModify_mouseAdapter(this));
    btnModify.setVisible(false);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    jScrollPane1.setPreferredSize(new Dimension(800, 210));
    jPanel11.setPreferredSize(new Dimension(800, 257));
    jPanel11.setLayout(gridLayout2);
    jPanel1.setMinimumSize(new Dimension(800, 160));
    jPanel1.setPreferredSize(new Dimension(800, 80));
    jPanel1.setLayout(null);
    lblEmployeeID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmployeeID.setPreferredSize(new Dimension(150, 21));
    lblEmployeeID.setText(lang.getString("EmployeeID")+":");
    lblEmployeeID.setBounds(new Rectangle(7, 10, 124, 15));
    lblEmployeeCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmployeeCode.setPreferredSize(new Dimension(150, 21));
    lblEmployeeCode.setText(lang.getString("EmployeeCode")+":");
    lblEmployeeCode.setBounds(new Rectangle(7, 37, 123, 15));
    lblFirstName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFirstName.setOpaque(false);
    lblFirstName.setPreferredSize(new Dimension(150, 21));
    lblFirstName.setText(lang.getString("FirstName")+":");
    lblFirstName.setBounds(new Rectangle(7, 64, 126, 15));
    txtEmployeeID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmployeeID.setPreferredSize(new Dimension(6, 22));
    txtEmployeeID.setEditable(false);
    txtEmployeeID.setText("");
    txtEmployeeID.setBounds(new Rectangle(125, 7, 216, 21));
    txtEmployeeCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmployeeCode.setNextFocusableComponent(txtFirstName);
    txtEmployeeCode.setPreferredSize(new Dimension(6, 21));
    txtEmployeeCode.setText("");
    txtEmployeeCode.setBounds(new Rectangle(125, 33, 216, 21));
    txtEmployeeCode.addKeyListener(new
        PanelAdminEmployee_txtEmployeeCode_keyAdapter(this));
    txtFirstName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtFirstName.setNextFocusableComponent(txtLastName);
    txtFirstName.setText("");
    txtFirstName.setBounds(new Rectangle(125, 60, 216, 21));
    txtFirstName.addKeyListener(new PanelAdminEmployee_txtFirstName_keyAdapter(this));
    txtLastName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtLastName.setNextFocusableComponent(lblHomeNo);
    txtLastName.setText("");
    txtLastName.setBounds(new Rectangle(125, 87, 216, 21));
    txtLastName.addKeyListener(new PanelAdminEmployee_txtLastName_keyAdapter(this));
    lblLastName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblLastName.setText(lang.getString("LastName")+":");
    lblLastName.setBounds(new Rectangle(7, 91, 89, 15));
    lblStore.setText("jLabel1");
    jLabel110.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel110.setForeground(Color.red);
    jLabel110.setText("(*)");
    jLabel110.setBounds(new Rectangle(349, 33, 13, 16));
    jLabel111.setText("(*)");
    jLabel111.setBounds(new Rectangle(349, 61, 13, 16));
    jLabel111.setForeground(Color.red);
    jLabel111.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel112.setText("(*)");
    jLabel112.setBounds(new Rectangle(349, 87, 13, 16));
    jLabel112.setForeground(Color.red);
    jLabel112.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStore.setText(lang.getString("Store")+":");
    lblStore.setBounds(new Rectangle(394, 10, 93, 15));
    lblBirthday.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblBirthday.setText(lang.getString("Birthday")+":");
    lblBirthday.setBounds(new Rectangle(393, 37, 86, 15));
    txtBirthday.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtBirthday.setNextFocusableComponent(txtAddress);
    txtBirthday.setText("");
    txtBirthday.setBounds(new Rectangle(506, 34, 214, 21));
    txtBirthday.addKeyListener(new PanelAdminEmployee_txtBirthday_keyAdapter(this));
    lblAddress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAddress.setText(lang.getString("Address")+":");
    lblAddress.setBounds(new Rectangle(394, 61, 71, 21));
    txtAddress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress.setNextFocusableComponent(txtCity);
    txtAddress.setText("");
    txtAddress.setBounds(new Rectangle(506, 61, 214, 21));
    txtAddress.addKeyListener(new PanelAdminEmployee_txtAddress_keyAdapter(this));
    jLabel113.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel113.setForeground(Color.red);
    jLabel113.setBounds(new Rectangle(728, 63, 13, 16));
    jLabel113.setText("(*)");
    lblHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblHomeNo.setText(lang.getString("HomePhone")+":");
    lblHomeNo.setBounds(new Rectangle(7, 118, 91, 15));
    txtHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtHomeNo.setNextFocusableComponent(lblWorkNo);
    txtHomeNo.setText("");
    txtHomeNo.setBounds(new Rectangle(125, 114, 216, 21));
    txtHomeNo.addKeyListener(new PanelAdminEmployee_txtHomeNo_keyAdapter(this));
    txtWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtWorkNo.setNextFocusableComponent(lblMobileNo);
    txtWorkNo.setText("");
    txtWorkNo.setBounds(new Rectangle(125, 141, 216, 21));
    txtWorkNo.addKeyListener(new PanelAdminEmployee_txtWorkNo_keyAdapter(this));
    txtMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtMobileNo.setNextFocusableComponent(txtPassword);
    txtMobileNo.setText("");
    txtMobileNo.setBounds(new Rectangle(125, 168, 216, 21));
    txtMobileNo.addKeyListener(new PanelAdminEmployee_txtMobileNo_keyAdapter(this));
    lblWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblWorkNo.setText(lang.getString("WorkPhone")+":");
    lblWorkNo.setBounds(new Rectangle(7, 144, 96, 15));
    lblMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblMobileNo.setText(lang.getString("CellPhone")+":");
    lblMobileNo.setBounds(new Rectangle(7, 171, 104, 15));
    lblEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmail.setText(lang.getString("Email")+":");
    lblEmail.setBounds(new Rectangle(394, 144, 80, 15));
    txtEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmail.setNextFocusableComponent(txtEmployeeCode);
    txtEmail.setOpaque(true);
    txtEmail.setText("");
    txtEmail.setBounds(new Rectangle(506, 141, 214, 21));
    txtEmail.addKeyListener(new PanelAdminEmployee_txtEmail_keyAdapter(this));
    lblCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCity.setText(lang.getString("City")+":");
    lblCity.setBounds(new Rectangle(394, 93, 94, 15));
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCity.setNextFocusableComponent(txtCountry);
    txtCity.setText("");
    txtCity.setBounds(new Rectangle(506, 87, 214, 21));
    txtCity.addKeyListener(new PanelAdminEmployee_txtCity_keyAdapter(this));
    lblCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCountry.setText(lang.getString("Country")+":");
    lblCountry.setBounds(new Rectangle(394, 118, 74, 15));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCountry.setNextFocusableComponent(txtEmail);
    txtCountry.setText("");
    txtCountry.setBounds(new Rectangle(506, 114, 214, 21));
    txtCountry.addKeyListener(new PanelAdminEmployee_txtCountry_keyAdapter(this));
    lblPassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPassword.setText(lang.getString("Password")+":");
    lblPassword.setBounds(new Rectangle(7, 198, 81, 15));

    lblConfirmPassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblConfirmPassword.setText(lang.getString("EnterConfirmPassword")+":");
    lblConfirmPassword.setBounds(new Rectangle(7, 225, 119, 15));
    jLabel115.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel115.setForeground(Color.red);
    jLabel115.setBounds(new Rectangle(348, 268, 13, 16));
    jLabel115.setText("(*)");
    jLabel116.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel116.setForeground(Color.red);
    jLabel116.setBounds(new Rectangle(730, 274, 13, 16));
    jLabel116.setText("(*)");
    jLabel117.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel117.setForeground(Color.red);
    jLabel117.setBounds(new Rectangle(349, 197, 13, 16));
    jLabel117.setText("(*)");
    jLabel118.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel118.setForeground(Color.red);
    jLabel118.setBounds(new Rectangle(349, 224, 13, 16));
    jLabel118.setText("(*)");
    btnDate.setBounds(new Rectangle(723, 33, 23, 23));
    btnDate.setPreferredSize(new Dimension(43, 25));
    btnDate.setText("...");
    btnDate.addActionListener(new PanelAdminEmployee_btnDate_actionAdapter(this));
    btnSearchStore.setText("...");
    btnSearchStore.addActionListener(new
        PanelAdminEmployee_btnSearchStore_actionAdapter(this));
    btnSearchStore.setBounds(new Rectangle(724, 6, 23, 22));
    txtStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtStore.setNextFocusableComponent(txtBirthday);
    txtStore.setText("");
    txtStore.setBounds(new Rectangle(506, 7, 214, 21));
    txtStore.addKeyListener(new PanelAdminEmployee_txtStore_keyAdapter(this));
    jLabel119.setText("(*)");
    jLabel119.setBounds(new Rectangle(756, 9, 13, 16));
    jLabel119.setForeground(Color.red);
    jLabel119.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPassword.setNextFocusableComponent(txtConfirmPassword);
    txtPassword.setText("");
    txtPassword.setBounds(new Rectangle(125, 195, 216, 21));
    txtPassword.addKeyListener(new PanelAdminEmployee_txtPassword_keyAdapter(this));
    txtConfirmPassword.setNextFocusableComponent(txtStore);
    txtConfirmPassword.setBounds(new Rectangle(125, 222, 216, 21));
    txtConfirmPassword.addKeyListener(new
        PanelAdminEmployee_txtConfirmPassword_keyAdapter(this));
    table.addMouseListener(new PanelAdminEmployee_table_mouseAdapter(this));
    table.addKeyListener(new PanelAdminEmployee_table_keyAdapter(this));
    jPanel15.add(btnDone, null);
    jPanel15.add(btnAdd, null);
    jPanel15.add(btnModify, null);
    jPanel15.add(btnSearch, null);
    jPanel15.add(btnClear, null);
    jPanel15.add(btnDelete, null);
    jPanel15.add(btnCancel, null);
    this.add(jPanel1, BorderLayout.CENTER);
    this.add(jPanel11, BorderLayout.SOUTH);
    jPanel11.add(jScrollPane1, null);
    jPanel1.add(txtEmployeeID, null);
    jPanel1.add(txtEmployeeCode, null);
    jPanel1.add(txtFirstName, null);
    this.add(jPanel15, BorderLayout.NORTH);
    jScrollPane1.add(table, null);
    jPanel1.add(jLabel110, null);
    jPanel1.add(jLabel111, null);
    jPanel1.add(txtStore, null);
    jPanel1.add(btnSearchStore, null);
    jPanel1.add(jLabel119, null);
    jPanel1.add(lblStore, null);
    jPanel1.add(txtLastName, null);
    jPanel1.add(jLabel112, null);
    jPanel1.add(txtAddress, null);
    jPanel1.add(txtCity, null);
    jPanel1.add(txtCountry, null);
    jPanel1.add(lblCity, null);
    jPanel1.add(lblCountry, null);
    jPanel1.add(lblAddress, null);
    jPanel1.add(txtMobileNo, null);
    jPanel1.add(txtEmail, null);
    jPanel1.add(lblEmail, null);
    jPanel1.add(txtPassword, null);
    jPanel1.add(jLabel117, null);
    jPanel1.add(jLabel118, null);
    jPanel1.add(txtConfirmPassword, null);
    jPanel1.add(lblEmployeeID, null);
    jPanel1.add(lblMobileNo, null);
    jPanel1.add(lblPassword, null);
    jPanel1.add(lblConfirmPassword, null);
    jPanel1.add(lblLastName, null);
    jPanel1.add(lblFirstName, null);
    jPanel1.add(lblEmployeeCode, null);
    jPanel1.add(txtHomeNo, null);
    jPanel1.add(lblHomeNo, null);
    jPanel1.add(txtWorkNo, null);
    jPanel1.add(lblWorkNo, null);
    jPanel1.add(jLabel113, null);
    jPanel1.add(txtBirthday, null);
    jPanel1.add(lblBirthday, null);
    jPanel1.add(btnDate, null);
    jScrollPane1.setViewportView(table);
    //View Table
    /*String[] columnNames = new String[] {
        "Employee ID", "Employee Code", "Name", "Store ID", "Home Phone",
        "Work Phone"};
    dm.setDataVector(new Object[][] {}
                     , columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn("EmployeeID").setPreferredWidth(150);
    table.getColumn("Employee Code").setPreferredWidth(150);
    table.getColumn("Name").setPreferredWidth(300);
    table.getColumn("Store ID").setPreferredWidth(150);
    table.getColumn("Home Phone").setPreferredWidth(150);
    table.getColumn("Work Phone").setPreferredWidth(150); */


    String[] columnNames = new String[] {
        lang.getString("EmployeeID"), lang.getString("EmployeeCode"), lang.getString("Name"), lang.getString("StoreID"), lang.getString("HomePhone"),
        lang.getString("WorkPhone")};
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




    //Set Employee ID
    txtEmployeeID.setText(empBusObj.getEmployeeIdCanInsert(DAL));
    //Visible btnModify
    btnModify.setVisible(false);
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
  };
  void initScreen() {
    txtEmployeeID.setText(empBusObj.getEmployeeIdCanInsert(DAL));
  }

  //apply employee's permission for this screen
  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_SUPPLIER_SETUP);
    btnAdd.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnModify.setEnabled(er.getModify());
    btnSearch.setEnabled(er.getSearch());
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    DialogAdminEmployeeSearch dlgAdminSearch = new DialogAdminEmployeeSearch(
        frmMain, lang.getString("TT0019"), true, frmMain,
        frmMain.employeeBusObject);
    dlgAdminSearch.txtEmployeeID.requestFocus(true);
    dlgAdminSearch.setVisible(true);
    if (dlgAdminSearch.done) {
      String empID = dlgAdminSearch.getData();
      frmMain.pnlAdminEmployeeModify.showData(empID, DAL);
      frmMain.pnlAdminEmployeeModify.txtEmployeeCode.requestFocus(true);
      frmMain.showScreen(Constant.SCRS_ADMIN_EMPLOYEE_MODIFY);

    }
  }

  void btnSearchStore_actionPerformed(ActionEvent e) {
    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,
        lang.getString("TT0004"), true, frmMain, frmMain.storeBusObj);
    dlgStoreSearch.setVisible(true);
    if (dlgStoreSearch.done) {
      String storeID = dlgStoreSearch.getData();
      frmMain.pnlAdminEmployee.txtStore.setText(storeID);
      frmMain.pnlAdminEmployee.txtBirthday.requestFocus(true);
      frmMain.showScreen(Constant.SCRS_ADMIN_EMPLOYEE);
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    //Visible btnModify
    btnModify.setVisible(false);
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
    flagSetHotKeyForAdd = true;
    //Show Screen
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0001"));
    frmMain.pnlMainSim.showAdminButton();
  }

  void btnClear_actionPerformed(ActionEvent e) {
    //Set Employee ID
    if (table.getRowCount() == 0) {
      txtEmployeeID.setText(empBusObj.getEmployeeIdCanInsert(DAL));
    }
    else {
      String strTempID = table.getValueAt(table.getRowCount() - 1, 0).toString();
      strTempID = strTempID.substring(1, strTempID.length());
      int iTemp = Integer.parseInt(strTempID) + 1;
      txtEmployeeID.setText(DAL.getHostID() + ut.getYeroCode(iTemp, 9));
    }

    txtEmployeeCode.setText("");
    txtFirstName.setText("");
    txtLastName.setText("");
    txtStore.setText("");
    txtBirthday.setText("");
    txtAddress.setText("");
    txtHomeNo.setText("");
    txtWorkNo.setText("");
    txtMobileNo.setText("");
    txtEmail.setText("");
    txtCity.setText("");
    txtCountry.setText("");
    txtPassword.setText("");
    txtConfirmPassword.setText("");
    //Visible btnModify
    btnModify.setVisible(false);
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
    flagSetHotKeyForAdd = true;
  }

  void btnAdd_actionPerformed(ActionEvent e) {
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

    sPass = ut.putCommaToString(sPass);
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
        checkEmployeeCodeInTable(sEmpID, sEmpCode)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1061_EmployeeCodeNotInvalid"));
      txtEmployeeCode.requestFocus(true);
      return;
    }

    if (!empBusObj.checkStore(sStoreID, DAL)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1040_StoreIDNotInvalid"));
      txtStore.requestFocus(true);
      return;
    }

    //Save object SQL
    Employee em = new Employee(txtEmployeeID.getText(), sEmpCode, sStoreID,
                               sFirstName, sLastName,
                               "", sPass, sAddress1, "",
                               sHomeNo, sWorkNo, sMobileNo,
                               sEmail, sCity, sCountry, sCounty,
                               sStartDate, "7/7/7777",
                               sBirthday);

    vArrayObject.addElement(em);
    //Add Table
    String[] rowData = new String[] {
        txtEmployeeID.getText(), txtEmployeeCode.getText(),
        sFirstName + " " + sLastName, sStoreID, sHomeNo, sWorkNo
    };
    dm.addRow(rowData);
//    str.installInTable(table, Color.lightGray, Color.white, null, null);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

    if (sBirthday.equals("")) {
      sBirthday = "1/1/1900";
    }
    String sEncrypt = enc.encryptData(sPass);
//    System.out.println("insert into BTM_IM_EMPLOYEE values('" +
//                       txtEmployeeID.getText() + "','" +
//                       txtEmployeeCode.getText() + "'," +
//                       sStoreID + ",'" + sFirstName + "','" + sLastName +
//                       "',null,'" + sEncrypt + "','" + sAddress1 + "',''," +
//                       "'" + sHomeNo + "','" + sWorkNo + "','" + sMobileNo +
//                       "','" + sEmail + "','" + sCity + "',''," +
//                       "'" + sCountry + "',to_date('" + sStartDate +
//                       "','DD-MM-YYYY')," +
//                       "to_date('" + sEndDate + "','DD-MM-YYYY'),to_date('" +
//                       sBirthday + "','DD-MM-YYYY'))");
    vSql.addElement("insert into BTM_IM_EMPLOYEE values('" +
                    txtEmployeeID.getText() + "','" + txtEmployeeCode.getText() +
                    "'," +
                    sStoreID + ",'" + sFirstName + "','" + sLastName +
                    "',null,'" +
                    sEncrypt + "','" + sAddress1 + "',''," +
                    "'" + sHomeNo + "','" + sWorkNo + "','" + sMobileNo + "','" +
                    sEmail + "','" + sCity + "',''," +
                    "'" + sCountry + "',to_date('" + sStartDate +
                    "','DD-MM-YYYY')," +
                    "to_date('" + sEndDate + "','DD-MM-YYYY'),to_date('" +
                    sBirthday + "','DD-MM-YYYY'))");
    //Set Employee ID
    //int id = Integer.parseInt(empBusObj.getEmployeeIdCanInsert(DAL)) +
    // table.getRowCount();
    //txtEmployeeID.setText(ut.getYeroCode(id, 10));
    if (table.getRowCount() == 0) {
      txtEmployeeID.setText(empBusObj.getEmployeeIdCanInsert(DAL));
    }
    else {
      /*String strTempID = table.getValueAt(table.getRowCount() - 1, 0).toString();
      strTempID = strTempID.substring(1, strTempID.length());
      int iTemp = Integer.parseInt(strTempID) + 1;
      txtEmployeeID.setText(DAL.getHostID() + ut.getYeroCode(iTemp, 9));*/
      long lTemp = Long.parseLong(empBusObj.getEmployeeIdCanInsert(DAL));
      lTemp+=table.getRowCount();
      txtEmployeeID.setText(""+lTemp);
    }

    txtEmployeeCode.setText("");
    txtFirstName.setText("");
    txtLastName.setText("");
    txtStore.setText("");
    txtBirthday.setText("");
    txtAddress.setText("");
    txtHomeNo.setText("");
    txtWorkNo.setText("");
    txtMobileNo.setText("");
    txtEmail.setText("");
    txtCity.setText("");
    txtCountry.setText("");
    txtPassword.setText("");
    txtConfirmPassword.setText("");
  }

  // True is had to have
  // False isn't had to have
  boolean checkEmployeeCodeInTable(String sEmpID, String sEmpCode) {
    boolean bFlag = false;
    int i, j;
    for (i = 0; i < table.getRowCount(); i++) {
      if (String.valueOf(table.getValueAt(i, 1)).equals(sEmpCode) &&
          !String.valueOf(table.getValueAt(i, 0)).equals(sEmpID)) {
        bFlag = true;
      }
    }
    return bFlag;
  }

  void btnDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    txtBirthday.setText("");
    posXFrame = frmMain.getX();
    posYFrame = frmMain.getY();
    posX = this.btnDate.getX() + posXFrame - 500;
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
        this.txtAddress.requestFocus(true);
      }
    }
  }

  void btnDelete_actionPerformed(ActionEvent e) {
    //Test value table
    if (table.getSelectedRow() == -1) {
      return;
    }
    if (vSql.isEmpty()) {
      return;
    }
    if (table.getRowCount() == 1) {
      dm.removeRow(0);
      vSql.removeAllElements();
      vArrayObject.removeAllElements();
      txtEmployeeID.setText(empBusObj.getEmployeeIdCanInsert(DAL));
      flagSetHotKeyForAdd = true;
      return;
    }
    //Remove value table
    int[] i = table.getSelectedRows();
    for (int j = 0; j < i.length; j++) {
      vSql.removeElementAt(i[0]);
      vArrayObject.removeElementAt(i[0]);
      dm.removeRow(i[0]);
    }
    //Updates table
    int lenghtTable = table.getRowCount();
    String sID = new String();
    if (i[0] == 0) {
      //Get CustomerID
      sID = empBusObj.getEmployeeIdCanInsert(DAL);
      //Update table
      table.setValueAt(sID, 0, 0);
      //Update vSQL
      String sql1 = new String();
      String sql2 = new String();
      String sql3 = new String();
      String sql = new String();
      sql = vSql.get(0).toString();
      sql1 = sql.substring(0, 36);
      sql2 = sql.substring(37, 46);
      sql3 = sql.substring(46, sql.length());
      sql = sql1 + sID + sql3;
//      System.out.println(sql);
      vSql.setElementAt(sql, 0);
      //Update vArrayObject
      Employee empTemp = (Employee) vArrayObject.get(0);
      empTemp.setEmpID(sID);
      vArrayObject.setElementAt(empTemp, 0);
    }
    for (int l = 1; l < lenghtTable; l++) {
      //Get CustomerID
      //sID = table.getValueAt(l - 1, 0).toString();
      //sID = ut.getYeroCode(Integer.parseInt(sID) + 1, emp.LEN_EMP_ID);
      sID = table.getValueAt(l - 1, 0).toString();
      sID = sID.substring(1, sID.length());
      sID = DAL.getHostID() +
          ut.getYeroCode(Integer.parseInt(sID) + 1, emp.LEN_EMP_ID - 1);

      //Update table
      table.setValueAt(sID, l, 0);
      //Update vSQL
      String sql1 = new String();
      String sql2 = new String();
      String sql3 = new String();
      String sql = new String();
      sql = vSql.get(l).toString();
      sql1 = sql.substring(0, 36);
      sql2 = sql.substring(37, 46);
      sql3 = sql.substring(46, sql.length());
      sql = sql1 + sID + sql3;
//      System.out.println(sql);
      vSql.setElementAt(sql, l);
      //Update vArrayObject
      Employee empTemp = (Employee) vArrayObject.get(l);
      empTemp.setEmpID(sID);
      vArrayObject.setElementAt(empTemp, l);
    }
    //Update CustomerID
    //int iTemp = 0;
    //int id = Integer.parseInt(table.getValueAt(table.getRowCount() - 1, 0).
    //                         toString());
    //txtEmployeeID.setText(ut.getYeroCode(id + 1, emp.LEN_EMP_ID));
    String strTempID = table.getValueAt(table.getRowCount() - 1, 0).toString();
    strTempID = strTempID.substring(1, strTempID.length());
    int iTemp = Integer.parseInt(strTempID) + 1;
    txtEmployeeID.setText(DAL.getHostID() + ut.getYeroCode(iTemp, 9));
    flagSetHotKeyForAdd = true;
  }

  void btnDone_actionPerformed(ActionEvent e) {
    try {
      if (!vSql.isEmpty()) {
//      System.out.println(vSql.size());
//      System.exit(0);
        try {
          stmt = DAL.getConnection().createStatement(ResultSet.
              TYPE_SCROLL_INSENSITIVE,
              ResultSet.CONCUR_READ_ONLY);
          DAL.setBeginTrans(DAL.getConnection());
          DAL.executeBatchQuery(vSql, stmt);
          DAL.setEndTrans(DAL.getConnection());
          stmt.close();
        }
        catch (Exception ex) {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(ex);}
        ;
        vSql.clear();
        vArrayObject.clear();
      }
      while (dm.getRowCount() > 0) {
        dm.removeRow(0);
      }

    }
    catch (Exception ed) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ed);
    }

    txtEmployeeID.setText(empBusObj.getEmployeeIdCanInsert(DAL));
//    System.out.println(txtEmployeeID.getText());
    txtEmployeeCode.setText("");
    txtFirstName.setText("");
    txtLastName.setText("");
    txtStore.setText("");
    txtBirthday.setText("");
    txtAddress.setText("");
    txtHomeNo.setText("");
    txtWorkNo.setText("");
    txtMobileNo.setText("");
    txtEmail.setText("");
    txtCity.setText("");
    txtCountry.setText("");
    txtPassword.setText("");
    txtConfirmPassword.setText("");

    //Visible btnModify
    btnModify.setVisible(false);
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
    flagSetHotKeyForAdd = true;

    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showAdminButton();
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      rowTableSelect = table.getSelectedRow();
      Employee empTemp = (Employee) vArrayObject.get(table.getSelectedRow());
      txtEmployeeID.setText(empTemp.getEmpID());
      txtEmployeeCode.setText(empTemp.getEmpCode());
      txtFirstName.setText(empTemp.getFirstName());
      txtLastName.setText(empTemp.getLastName());
      txtStore.setText(empTemp.getStoreID());
      txtBirthday.setText(empTemp.getBirthday());
      txtAddress.setText(empTemp.getAddress1());
      txtHomeNo.setText(empTemp.getHomeNo());
      txtWorkNo.setText(empTemp.getWorkNo());
      txtMobileNo.setText(empTemp.getMobileNo());
      txtEmail.setText(empTemp.getEmail());
      txtCity.setText(empTemp.getCity());
      txtCountry.setText(empTemp.getCountry());
      txtPassword.setText(empTemp.getPassword());
      txtConfirmPassword.setText(empTemp.getPassword());
      //Update state button
      btnModify.setVisible(true);
      btnAdd.setVisible(false);
      flagSetHotKeyForAdd = false;
      txtEmployeeCode.requestFocus(true);
    }
  }

  void btnModify_mouseClicked(MouseEvent e) {

  }

  void btnModify_actionPerformed(ActionEvent e) {
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

    sPass = ut.putCommaToString(sPass);
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
                     lang.getString("MS1044_PasswordEnteredNotMatch") +
                     lang.getString("MS1045_TypeNewPasswordBothTextBoxes"));
      txtConfirmPassword.requestFocus(true);
      return;
    }

    if (empBusObj.checkLength(sEmpCode, 10)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1049_EmployeeCodeTooLong"));
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
                     lang.getString("MS1046_EmployeeCodeTooLong"));
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
      ut.showMessage(frmMain, lang.getString("TT001"), "Wrong birthday");
      txtBirthday.requestFocus(true);
      return;
    }

    if (empBusObj.checkEmployeeCode(sEmpID, sEmpCode, DAL) ||
        checkEmployeeCodeInTable(sEmpID, sEmpCode)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1061_EmployeeCodeNotInvalid"));
      txtEmployeeCode.requestFocus(true);
      return;
    }

    if (!empBusObj.checkStore(sStoreID, DAL)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1040_StoreIDNotInvalid"));
      txtStore.requestFocus(true);
      return;
    }

    //Save object SQL
    Employee em = new Employee(txtEmployeeID.getText(), sEmpCode, sStoreID,
                               sFirstName, sLastName,
                               "", sPass, sAddress1, "",
                               sHomeNo, sWorkNo, sMobileNo,
                               sEmail, sCity, sCountry, sCounty,
                               sStartDate, "7/7/7777",
                               sBirthday);

    vArrayObject.setElementAt(em, rowTableSelect);
//    //Add Table
//    String[] rowData = new String[] {
//        txtEmployeeID.getText(), txtEmployeeCode.getText(),
//        sFirstName + " " + sLastName, sStoreID, sEmail, sWorkNo
//    };
//    dm.setValueAt();
//    dm.setRowCount(rowData);
//    str.installInTable(table, Color.lightGray, Color.white, null, null);
    //Update Table
    dm.setValueAt(txtEmployeeID.getText(), rowTableSelect, 0);
    dm.setValueAt(txtEmployeeCode.getText(), rowTableSelect, 1);
    dm.setValueAt(sFirstName + " " + sLastName, rowTableSelect, 2);
    dm.setValueAt(sStoreID, rowTableSelect, 3);
    dm.setValueAt(sHomeNo, rowTableSelect, 4);
    dm.setValueAt(sWorkNo, rowTableSelect, 5);
    //Insert vSQL
    if (sBirthday.equals("")) {
      sBirthday = "1/1/1900";
    }
//    System.out.println("insert into BTM_IM_EMPLOYEE values('" +
//                       txtEmployeeID.getText() + "','" +
//                       txtEmployeeCode.getText() + "'," +
//                       sStoreID + ",'" + sFirstName + "','" + sLastName +
//                       "',null,'" + sPass + "','" + sAddress1 + "',''," +
//                       "'" + sHomeNo + "','" + sWorkNo + "','" + sMobileNo +
//                       "','" + sEmail + "','" + sCity + "',''," +
//                       "'" + sCountry + "',to_date('" + sStartDate +
//                       "','DD-MM-YYYY')," +
//                       "to_date('" + sEndDate + "','DD-MM-YYYY'),to_date('" +
//                       sBirthday + "','DD-MM-YYYY'))");
    vSql.setElementAt("insert into BTM_IM_EMPLOYEE values('" +
                      txtEmployeeID.getText() + "','" + txtEmployeeCode.getText() +
                      "'," +
                      sStoreID + ",'" + sFirstName + "','" + sLastName +
                      "',null,'" +
                      sPass + "','" + sAddress1 + "',''," +
                      "'" + sHomeNo + "','" + sWorkNo + "','" + sMobileNo +
                      "','" +
                      sEmail + "','" + sCity + "',''," +
                      "'" + sCountry + "',to_date('" + sStartDate +
                      "','DD-MM-YYYY')," +
                      "to_date('" + sEndDate + "','DD-MM-YYYY'),to_date('" +
                      sBirthday + "','DD-MM-YYYY'))", rowTableSelect);
    //Set Employee ID
    if (table.getRowCount() == 0) {
      txtEmployeeID.setText(empBusObj.getEmployeeIdCanInsert(DAL));
    }
    else {
      String strTempID = table.getValueAt(table.getRowCount() - 1, 0).toString();
      strTempID = strTempID.substring(1, strTempID.length());
      int iTemp = Integer.parseInt(strTempID) + 1;
      txtEmployeeID.setText(DAL.getHostID() + ut.getYeroCode(iTemp, 9));
    }

    txtEmployeeCode.setText("");
    txtFirstName.setText("");
    txtLastName.setText("");
    txtStore.setText("");
    txtBirthday.setText("");
    txtAddress.setText("");
    txtHomeNo.setText("");
    txtWorkNo.setText("");
    txtMobileNo.setText("");
    txtEmail.setText("");
    txtCity.setText("");
    txtCountry.setText("");
    txtPassword.setText("");
    txtConfirmPassword.setText("");
    //Visible btnModify
    btnModify.setVisible(false);
    btnAdd.setVisible(true);
    btnDelete.setVisible(true);
    flagSetHotKeyForAdd = true;
  }

  void txtEmployeeCode_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtEmployeeCode, Employee.LEN_EMP_CDE);
    ut.checkCode(e);
    navigationComp(e, txtFirstName);
  }

  void navigationComp(KeyEvent e, JTextField txt) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      txt.requestFocus(true);
      txt.selectAll();
    }
  }

  void txtFirstName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtFirstName, Employee.LEN_EMP_FIRST_NAME);
    navigationComp(e, txtLastName);
  }

  void txtLastName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtLastName, Employee.LEN_EMP_LAST_NAME);
    navigationComp(e, txtHomeNo);
  }

  void txtStore_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtStore, Employee.LEN_EMP_STORE_ID);
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      btnSearchStore.requestFocus(true);
    }
  }

  void txtAddress_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtAddress, Employee.LEN_EMP_ADDRESS);
    navigationComp(e, txtCity);
  }

  void txtHomeNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtHomeNo, Employee.LEN_EMP_PHONE);
    ut.checkPhone(e);
    navigationComp(e, txtWorkNo);
  }

  void txtMobileNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtMobileNo, Employee.LEN_EMP_PHONE);
    ut.checkPhone(e);
    navigationComp(e, txtPassword);
  }

  void txtWorkNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtWorkNo, Employee.LEN_EMP_PHONE);
    ut.checkPhone(e);
    navigationComp(e, txtMobileNo);
  }

  void txtEmail_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtEmail, Employee.LEN_EMP_EMAIL);
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
      btnAdd.doClick();
    }
  }

  void txtCity_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtCity, Employee.LEN_EMP_COUNTRY);
    navigationComp(e, txtCountry);
  }

  void txtPassword_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtPassword, Employee.LEN_EMP_PASSWORD);
    navigationComp(e, txtConfirmPassword);
  }

  void txtConfirmPassword_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtConfirmPassword, Employee.LEN_EMP_PASSWORD);
    navigationComp(e, txtStore);
  }

  void txtCountry_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtCountry, Employee.LEN_EMP_COUNTRY);
    navigationComp(e, txtEmail);
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
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2) {
        if (flagSetHotKeyForAdd == true) {
          btnAdd.doClick();
        }
        else {
          btnModify.doClick();
        }
      }
      else if (identifier.intValue() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {
        btnSearch.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F7) {
        btnClear.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
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
      if (flagSetHotKeyForAdd == true) {
        btnAdd.doClick();
      }
      else {
        btnModify.doClick();
      }

    }
  }
}

class PanelAdminEmployee_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnSearch_actionAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelAdminEmployee_btnSearchStore_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnSearchStore_actionAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchStore_actionPerformed(e);
  }
}

class PanelAdminEmployee_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnCancel_actionAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelAdminEmployee_btnClear_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnClear_actionAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelAdminEmployee_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnAdd_actionAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelAdminEmployee_btnDate_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnDate_actionAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class PanelAdminEmployee_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnDelete_actionAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelAdminEmployee_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnDone_actionAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelAdminEmployee_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_table_mouseAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelAdminEmployee_btnModify_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnModify_mouseAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.btnModify_mouseClicked(e);
  }
}

class PanelAdminEmployee_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_btnModify_actionAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelAdminEmployee_txtEmployeeCode_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtEmployeeCode_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEmployeeCode_keyTyped(e);
  }
}

class PanelAdminEmployee_txtFirstName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtFirstName_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtFirstName_keyTyped(e);
  }
}

class PanelAdminEmployee_txtLastName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtLastName_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtLastName_keyTyped(e);
  }
}

class PanelAdminEmployee_txtStore_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtStore_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtStore_keyTyped(e);
  }
}

class PanelAdminEmployee_txtAddress_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtAddress_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress_keyTyped(e);
  }
}

class PanelAdminEmployee_txtHomeNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtHomeNo_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtHomeNo_keyTyped(e);
  }
}

class PanelAdminEmployee_txtMobileNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtMobileNo_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtMobileNo_keyTyped(e);
  }
}

class PanelAdminEmployee_txtWorkNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtWorkNo_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtWorkNo_keyTyped(e);
  }
}

class PanelAdminEmployee_txtEmail_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtEmail_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEmail_keyTyped(e);
  }
}

class PanelAdminEmployee_txtCity_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtCity_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCity_keyTyped(e);
  }
}

class PanelAdminEmployee_txtPassword_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtPassword_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPassword_keyTyped(e);
  }
}

class PanelAdminEmployee_txtConfirmPassword_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtConfirmPassword_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtConfirmPassword_keyTyped(e);
  }
}

class PanelAdminEmployee_txtCountry_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtCountry_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCountry_keyTyped(e);
  }
}

class PanelAdminEmployee_txtBirthday_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_txtBirthday_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtBirthday_keyTyped(e);
  }
}

class PanelAdminEmployee_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminEmployee adaptee;

  PanelAdminEmployee_table_keyAdapter(PanelAdminEmployee adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
