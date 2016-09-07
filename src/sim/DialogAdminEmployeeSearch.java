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
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Amin -> Admin Employee -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class DialogAdminEmployeeSearch extends JDialog {
  BorderLayout borderLayout1 = new BorderLayout();
  DataAccessLayer DAL;
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international
  Utils ut = new Utils();
  Statement stmt = null;
  int parentScreen=0;
  btm.business.EmployeeBusObj empBusObj = new btm.business.EmployeeBusObj();


  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 0: return Long.class;
        default: return Object.class;
      }
    }
  };
  FrameMainSim frmMain;
  int rowsNum = 5;
  public boolean done = false;
  BJButton btnCancel = new BJButton();
  BJButton btnSearch = new BJButton();
  BJPanel jPanel5 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJTable table = new BJTable(dm,true){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  JScrollPane jScrollPane1 = new JScrollPane();
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPanel11 = new JPanel();
  JLabel lblBirthday = new JLabel();
  JLabel lblHomeNo = new JLabel();
  JTextField txtAddress = new JTextField();
  JLabel lblCity = new JLabel();
  JLabel lblMobileNo = new JLabel();
  JTextField txtEmail = new JTextField();
  JLabel lblWorkNo = new JLabel();
  JLabel lblEmail = new JLabel();
  JLabel lblEmployeeID = new JLabel();
  JTextField txtHomeNo = new JTextField();
  JTextField txtMobileNo = new JTextField();
  JTextField txtCountry = new JTextField();
  JTextField txtFirstName = new JTextField();
  JLabel lblEmployeeCode = new JLabel();
  JLabel lblCountry = new JLabel();
  JTextField txtCity = new JTextField();
  JTextField txtLastName = new JTextField();
  JTextField txtBirthday = new JTextField();
  JLabel lblStore = new JLabel();
  JPanel jPanel1 = new JPanel();
  JButton btnDate = new JButton();
  JTextField txtEmployeeCode = new JTextField();
  JLabel lblAddress = new JLabel();
  JLabel lblLastName = new JLabel();
  JTextField txtWorkNo = new JTextField();
  JTextField txtEmployeeID = new JTextField();
  JLabel lblFirstName = new JLabel();
  JButton btnSearchStore = new JButton();
  JTextField txtStore = new JTextField();
  JTextField txtRowLimit1 = new JTextField();
  JLabel lblRowLimit1 = new JLabel();



  public DialogAdminEmployeeSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain,EmployeeBusObj empBusObj) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      this.empBusObj = empBusObj;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  public void setValueSearch(String sEmpID, String sEmpCode, String sStoreID,
                          String sFirstName, String sLastName,
                          String sSSN, String sPass,
                          String sAddress1, String sAddress2,
                          String sHomeNo, String sWorkNo, String sMobileNo,
                          String sEmail, String sCity, String sCountry,
                          String sCounty, String sTartDate, String sEndDate,
                          String sBirthday){
    txtEmployeeID.setText(sEmpID);
    txtEmployeeCode.setText(sEmpCode);
    txtFirstName.setText(sFirstName);
    txtLastName.setText(sLastName);
    txtStore.setText(sStoreID);
    txtBirthday.setText(sBirthday);
    txtAddress.setText(sAddress1);
    txtHomeNo.setText(sHomeNo);
    txtWorkNo.setText(sWorkNo);
    txtMobileNo.setText(sMobileNo);
    txtEmail.setText(sEmail);
    txtCity.setText(sCity);
    txtCountry.setText(sCountry);
  }
  public DialogAdminEmployeeSearch() {
    this(null, "", false,null,null);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    jPanel5.setPreferredSize(new Dimension(750, 50));
    jPanel5.setMinimumSize(new Dimension(503, 35));
    btnSearch.setText("<html><center><b>"+ lang.getString("Search")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3<" +
    "/html>");
    btnSearch.addFocusListener(new DialogAdminEmployeeSearch_btnSearch_focusAdapter(this));
    btnSearch.addActionListener(new DialogAdminEmployeeSearch_btnSearch_actionAdapter(this));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setPreferredSize(new Dimension(130, 37));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new DialogAdminEmployeeSearch_btnCancel_actionAdapter(this));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setPreferredSize(new Dimension(130, 37));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    registerKeyboardActions();
    this.getContentPane().setLayout(borderLayout1);
    this.setSize(new Dimension(800,600));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);
    this.addWindowListener(new DialogAdminEmployeeSearch_this_windowAdapter(this));
    this.addKeyListener(new DialogAdminEmployeeSearch_this_keyAdapter(this));
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.addKeyListener(new DialogAdminEmployeeSearch_table_keyAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(800, 242));
    jPanel11.setPreferredSize(new Dimension(800, 326));
    jPanel11.setLayout(gridLayout2);
    lblBirthday.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblBirthday.setText(lang.getString("Birthday")+": ");
    lblBirthday.setBounds(new Rectangle(381, 36, 86, 15));
    lblHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblHomeNo.setText(lang.getString("HomePhone")+": ");
    lblHomeNo.setBounds(new Rectangle(7, 119, 91, 15));
    txtAddress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress.setNextFocusableComponent(txtCity);
    txtAddress.setText("");
    txtAddress.setBounds(new Rectangle(500, 61, 214, 21));
    txtAddress.addKeyListener(new DialogAdminEmployeeSearch_txtAddress_keyAdapter(this));
    lblCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCity.setText(lang.getString("City")+": ");
    lblCity.setBounds(new Rectangle(381, 93, 110, 15));
    lblMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblMobileNo.setText(lang.getString("CellPhone")+ ": ");
    lblMobileNo.setBounds(new Rectangle(7, 172, 90, 15));
    txtEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmail.setNextFocusableComponent(txtRowLimit1);
    txtEmail.setText("");
    txtEmail.setBounds(new Rectangle(500, 142, 214, 21));
    txtEmail.addKeyListener(new DialogAdminEmployeeSearch_txtEmail_keyAdapter(this));
    lblWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblWorkNo.setText(lang.getString("Phone")+ ": ");
    lblWorkNo.setBounds(new Rectangle(7, 146, 100, 15));
    lblEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmail.setText(lang.getString("Email")+ ":");
    lblEmail.setBounds(new Rectangle(381, 144, 80, 15));
    lblEmployeeID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmployeeID.setPreferredSize(new Dimension(150, 21));
    lblEmployeeID.setText(lang.getString("AccountCode")+ ":");
    lblEmployeeID.setBounds(new Rectangle(7, 10, 96, 15));
    txtHomeNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtHomeNo.setNextFocusableComponent(txtWorkNo);
    txtHomeNo.setText("");
    txtHomeNo.setBounds(new Rectangle(123, 115, 214, 21));
    txtHomeNo.addKeyListener(new DialogAdminEmployeeSearch_txtHomeNo_keyAdapter(this));
    txtMobileNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtMobileNo.setNextFocusableComponent(txtStore);
    txtMobileNo.setText("");
    txtMobileNo.setBounds(new Rectangle(123, 169, 214, 21));
    txtMobileNo.addKeyListener(new DialogAdminEmployeeSearch_txtMobileNo_keyAdapter(this));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCountry.setNextFocusableComponent(txtEmail);
    txtCountry.setText("");
    txtCountry.setBounds(new Rectangle(500, 115, 214, 21));
    txtCountry.addKeyListener(new DialogAdminEmployeeSearch_txtCountry_keyAdapter(this));
    txtFirstName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtFirstName.setNextFocusableComponent(txtLastName);
    txtFirstName.setText("");
    txtFirstName.setBounds(new Rectangle(123, 60, 214, 21));
    txtFirstName.addKeyListener(new DialogAdminEmployeeSearch_txtFirstName_keyAdapter(this));
    lblEmployeeCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmployeeCode.setPreferredSize(new Dimension(150, 21));
    lblEmployeeCode.setText(lang.getString("Account")+":");
    lblEmployeeCode.setBounds(new Rectangle(7, 35, 123, 15));
    lblCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCountry.setText(lang.getString("Country")+":");
    lblCountry.setBounds(new Rectangle(381, 118, 90, 15));
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCity.setNextFocusableComponent(txtCountry);
    txtCity.setText("");
    txtCity.setBounds(new Rectangle(500, 88, 215, 21));
    txtCity.addKeyListener(new DialogAdminEmployeeSearch_txtCity_keyAdapter(this));
    txtLastName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtLastName.setNextFocusableComponent(txtHomeNo);
    txtLastName.setText("");
    txtLastName.setBounds(new Rectangle(123, 88, 214, 21));
    txtLastName.addKeyListener(new DialogAdminEmployeeSearch_txtLastName_keyAdapter(this));
    txtBirthday.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtBirthday.setNextFocusableComponent(txtAddress);
    txtBirthday.setText("");
    txtBirthday.setBounds(new Rectangle(500, 34, 214, 21));
    txtBirthday.addKeyListener(new DialogAdminEmployeeSearch_txtBirthday_keyAdapter(this));
    lblStore.setText("jLabel1");
    lblStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStore.setText(lang.getString("Store")+":");
    lblStore.setBounds(new Rectangle(381, 10, 60, 15));
    jPanel1.setMinimumSize(new Dimension(800, 160));
    jPanel1.setPreferredSize(new Dimension(800, 80));
    jPanel1.setLayout(null);
    btnDate.setBounds(new Rectangle(720, 33, 30, 22));
    btnDate.setText("...");
    btnDate.addActionListener(new DialogAdminEmployeeSearch_btnDate_actionAdapter(this));
    txtEmployeeCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmployeeCode.setMinimumSize(new Dimension(6, 22));
    txtEmployeeCode.setNextFocusableComponent(txtFirstName);
    txtEmployeeCode.setPreferredSize(new Dimension(6, 21));
    txtEmployeeCode.setText("");
    txtEmployeeCode.setBounds(new Rectangle(123, 33, 214, 21));
    txtEmployeeCode.addKeyListener(new DialogAdminEmployeeSearch_txtEmployeeCode_keyAdapter(this));
    lblAddress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAddress.setText(lang.getString("Address")+": ");
    lblAddress.setBounds(new Rectangle(381, 61, 71, 21));
    lblLastName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblLastName.setText(lang.getString("LastName")+":");
    lblLastName.setBounds(new Rectangle(7, 91, 89, 15));
    txtWorkNo.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12));
    txtWorkNo.setNextFocusableComponent(txtMobileNo);
    txtWorkNo.setText("");
    txtWorkNo.setBounds(new Rectangle(123, 142, 214, 21));
    txtWorkNo.addKeyListener(new DialogAdminEmployeeSearch_txtWorkNo_keyAdapter(this));
    txtEmployeeID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmployeeID.setNextFocusableComponent(lblEmployeeCode);
    txtEmployeeID.setPreferredSize(new Dimension(6, 22));
    txtEmployeeID.setEditable(true);
    txtEmployeeID.setText("");
    txtEmployeeID.setBounds(new Rectangle(123, 6, 214, 21));
    txtEmployeeID.addKeyListener(new DialogAdminEmployeeSearch_txtEmployeeID_keyAdapter(this));
    lblFirstName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFirstName.setOpaque(false);
    lblFirstName.setPreferredSize(new Dimension(150, 21));
    lblFirstName.setText(lang.getString("FirstName")+":");
    lblFirstName.setBounds(new Rectangle(7, 62, 126, 15));
    btnSearchStore.setText("...");
    btnSearchStore.addActionListener(new DialogAdminEmployeeSearch_btnSearchStore_actionAdapter(this));
    btnSearchStore.setBounds(new Rectangle(721, 7, 21, 21));
    txtStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtStore.setNextFocusableComponent(txtBirthday);
    txtStore.setText("");
    txtStore.setBounds(new Rectangle(500, 6, 214, 21));
    txtStore.addActionListener(new DialogAdminEmployeeSearch_txtStore_actionAdapter(this));
    txtStore.addKeyListener(new DialogAdminEmployeeSearch_txtStore_keyAdapter(this));
    table.addMouseListener(new DialogAdminEmployeeSearch_table_mouseAdapter(this));
    txtRowLimit1.setText("5");
    txtRowLimit1.setBounds(new Rectangle(500, 169, 50, 20));
    txtRowLimit1.addKeyListener(new DialogAdminEmployeeSearch_txtRowLimit1_keyAdapter(this));
    txtRowLimit1.setPreferredSize(new Dimension(50, 20));
    txtRowLimit1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtRowLimit1.setNextFocusableComponent(txtEmployeeID);
    lblRowLimit1.setText(lang.getString("RowsLimit")+":");
    lblRowLimit1.setBounds(new Rectangle(381, 169, 106, 20));
    lblRowLimit1.setPreferredSize(new Dimension(90, 20));
    lblRowLimit1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel5.add(btnSearch, null);
    jPanel5.add(btnCancel, null);
    this.getContentPane().add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(txtEmployeeID, null);
    jPanel1.add(txtEmployeeCode, null);
    jPanel1.add(txtFirstName, null);
    jPanel1.add(txtAddress, null);
    jPanel1.add(txtCountry, null);
    jPanel1.add(btnSearchStore, null);
    jPanel1.add(btnDate, null);
    jPanel1.add(txtBirthday, null);
    jPanel1.add(txtLastName, null);
    jPanel1.add(txtEmail, null);
    jPanel1.add(txtHomeNo, null);
    jPanel1.add(txtWorkNo, null);
    jPanel1.add(lblEmployeeID, null);
    jPanel1.add(lblWorkNo, null);
    jPanel1.add(lblHomeNo, null);
    jPanel1.add(lblLastName, null);
    jPanel1.add(lblFirstName, null);
    jPanel1.add(lblEmployeeCode, null);
    jPanel1.add(lblStore, null);
    jPanel1.add(lblEmail, null);
    jPanel1.add(lblCountry, null);
    jPanel1.add(lblCity, null);
    jPanel1.add(lblAddress, null);
    jPanel1.add(lblBirthday, null);
    jPanel1.add(txtMobileNo, null);
    jPanel1.add(txtRowLimit1, null);
    jPanel1.add(lblRowLimit1, null);
    jPanel1.add(lblMobileNo, null);
    jPanel1.add(txtStore, null);
    jPanel1.add(txtCity, null);
    this.getContentPane().add(jPanel11,  BorderLayout.SOUTH);
    jPanel11.add(jScrollPane1, null);
    jScrollPane1.add(table, null);
    this.getContentPane().add(jPanel5, BorderLayout.NORTH);
    jScrollPane1.setViewportView(table);
    //View Table
//    String[] columnNames = new String[] {
//        "Employee ID", "Employee Code", "Name", "Store ID", "Email",
//        "Work Phone"};
//    dm.setDataVector(new Object[][] {}
//                     , columnNames);
//    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
//    table.getTableHeader().setReorderingAllowed(false);
//    table.getColumn("Employee ID").setPreferredWidth(150);
//    table.getColumn("Employee Code").setPreferredWidth(150);
//    table.getColumn("Name").setPreferredWidth(300);
//    table.getColumn("Store ID").setPreferredWidth(150);
//    table.getColumn("Email").setPreferredWidth(150);
//    table.getColumn("Work Phone").setPreferredWidth(150);
    if(btnSearch.isFocusOwner()){
      txtEmployeeID.requestFocus(true);
    }
  }

  // Search Data
  void searchData(int rowsNum) {

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

    jPanel5.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMap);
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

      if (identifier.intValue() == KeyEvent.VK_F3 ||
          identifier.intValue() == KeyEvent.VK_ENTER) {
        btnSearch.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
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
  void btnCancel_actionPerformed(ActionEvent e) {
    done = false;
    this.dispose();
  }

  void btnSearchStore_actionPerformed(ActionEvent e) {
    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,
        lang.getString("TT0004"), true, frmMain, frmMain.storeBusObj);
    dlgStoreSearch.setVisible(true);
    if (dlgStoreSearch.done) {
      String storeID = dlgStoreSearch.getData();
      this.txtStore.setText(storeID);
      this.txtBirthday.requestFocus(true);
    }
  }

  //return data
  String getData(){
    String result = "";
    result = "" + table.getValueAt(table.getSelectedRow(),0);
    return result;
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

    if (endDate.isOKPressed()){
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtBirthday.setText(date);
      this.txtAddress.requestFocus(true);
    }
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      done = true;
      setDataIntoEmployeeBusObj();
      this.dispose();
    }
  }

  void setDataIntoEmployeeBusObj(){
    String sEmpID = txtEmployeeID.getText().trim();
    String sEmpCode = txtEmployeeCode.getText().trim();
    String sStoreID = txtStore.getText().trim();
    String sFirstName = txtFirstName.getText().trim();
    String sLastName = txtLastName.getText().trim();
    String sSSN = "";
    String sPass = "";
    String sConPass = "";
    String sAddress1 = txtAddress.getText().trim();
    String sAddress2 = "";
    String sHomeNo = txtHomeNo.getText().trim();
    String sWorkNo = txtWorkNo.getText().trim();
    String sMobileNo = txtMobileNo.getText().trim();
    String sEmail = txtEmail.getText().trim();
    String sCity = txtCity.getText().trim();
    String sCountry = txtCountry.getText().trim();
    String sCounty = "";
    String sStartDate = "";
    String sEndDate = "";
    String sBirthday = txtBirthday.getText().trim();

    empBusObj.setEmployee(txtEmployeeID.getText(), sEmpCode,
                                          sStoreID,
                                          sFirstName, sLastName,
                                          "", sPass, sAddress1, "",
                                          sHomeNo, sWorkNo, sMobileNo,
                                          sEmail, sCity, sCountry, sCounty,
                                          sStartDate, "",
                                          sBirthday);
    empBusObj.setRow(txtRowLimit1.getText());
  }

  void txtEmployeeID_keyTyped(KeyEvent e) {
    ut.checkCode(e);
    navigationComp(e,txtEmployeeCode);
  }
  void navigationComp(KeyEvent e, JTextField txt){
      if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
        txt.requestFocus(true);
        txt.selectAll();
      }
    }

  void txtEmployeeCode_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtEmployeeCode,Employee.LEN_EMP_CDE);
    ut.checkCode(e);
    navigationComp(e,txtFirstName);
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
    if(e.getKeyChar()==KeyEvent.VK_ENTER || e.getKeyChar()==KeyEvent.VK_TAB){
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
  }

  void txtMobileNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtMobileNo,Employee.LEN_EMP_PHONE);
    ut.checkPhone(e);
    navigationComp(e,txtStore);
  }

  void txtWorkNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtWorkNo,Employee.LEN_EMP_PHONE);
    ut.checkPhone(e);
    navigationComp(e,txtMobileNo);
  }

  void txtEmail_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtEmail,Employee.LEN_EMP_EMAIL);
    navigationComp(e,txtRowLimit1);
  }

  void txtCity_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCity,Employee.LEN_EMP_COUNTRY);
    navigationComp(e,txtCountry);
  }

  void txtCountry_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCountry,Employee.LEN_EMP_COUNTRY);
    navigationComp(e,txtEmail);
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    if (txtRowLimit1.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1001_EnterNumberOfRow"));
      return;
    }

    rowsNum = Integer.parseInt(txtRowLimit1.getText());
//    if (rowsNum > 50){
//      ut.showMessage(frmMain,"Warning!", "The number of rows is too long! it must be less than or equal 50");
//      return;
//    }
    frmMain.pnlAdminEmployee.rowNum = rowsNum;
    searchData(txtRowLimit1.getText());
  }
 void searchData(String rowsNum) {
   String sEmpID = txtEmployeeID.getText().trim();
   String sEmpCode = txtEmployeeCode.getText().trim();
   String sStoreID = txtStore.getText().trim();
   String sFirstName = txtFirstName.getText().trim();
   String sLastName = txtLastName.getText().trim();
   String sSSN = "";
   String sPass = "";
   String sConPass = "";
   String sAddress1 = txtAddress.getText().trim();
   String sAddress2 = "";
   String sHomeNo = txtHomeNo.getText().trim();
   String sWorkNo = txtWorkNo.getText().trim();
   String sMobileNo = txtMobileNo.getText().trim();
   String sEmail = txtEmail.getText().trim();
   String sCity = txtCity.getText().trim();
   String sCountry = txtCountry.getText().trim();
   String sCounty = "";
   String sStartDate = "";
   String sEndDate = "";
   String sBirthday = txtBirthday.getText().trim();

   dm.resetIndexes();
   try{
     stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
     table.setData(empBusObj.getDataSearch(txtEmployeeID.getText(), sEmpCode,
                                           sStoreID,
                                           sFirstName, sLastName,
                                           "", sPass, sAddress1, "",
                                           sHomeNo, sWorkNo, sMobileNo,
                                           sEmail, sCity, sCountry, sCounty,
                                           sStartDate, "",
                                           sBirthday, txtRowLimit1.getText(),
                                           DAL, stmt));
     stmt.close();
   }
   catch(Exception ex){};
   table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
   table.getTableHeader().setReorderingAllowed(false);
   table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
   table.setHeaderName(lang.getString("EmployeeID"), 0);
   table.setHeaderName(lang.getString("Employee"), 1);
   table.setHeaderName(lang.getString("Name"), 2);
   table.setHeaderName(lang.getString("Store"), 3);
   table.setHeaderName(lang.getString("HomePhone"), 4);
   table.setHeaderName(lang.getString("Phone"), 5);
   table.setHeader(new java.awt.Font(lang.getString("FontName_Text"), 1, 15));
 }

  void txtRowLimit1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtRowLimit1,5);
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER ){
      btnSearch.doClick();
    }
  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtEmployeeID_keyPressed(KeyEvent e) {
    catchHotKey(e);
    navigationComp(e,txtEmployeeCode);
  }

  void txtEmployeeCode_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtFirstName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtLastName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtHomeNo_keyPressed(KeyEvent e) {
    catchHotKey(e);
    navigationComp(e,txtWorkNo);
  }

  void txtWorkNo_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtMobileNo_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtStore_actionPerformed(ActionEvent e) {
//    catchHotKey(e);
  }

  void txtBirthday_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtStore_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtAddress_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtCity_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtCountry_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtEmail_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtRowLimit1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtBirthday_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      btnDate.requestFocus(true);
    }
  }

  void btnSearch_focusGained(FocusEvent e) {

  }

  void this_windowOpened(WindowEvent e) {
    txtEmployeeID.requestFocus(true);
  }
}

class DialogAdminEmployeeSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_btnSearch_actionAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class DialogAdminEmployeeSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_btnCancel_actionAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogAdminEmployeeSearch_btnSearchStore_actionAdapter implements java.awt.event.ActionListener {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_btnSearchStore_actionAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchStore_actionPerformed(e);
  }
}

class DialogAdminEmployeeSearch_btnDate_actionAdapter implements java.awt.event.ActionListener {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_btnDate_actionAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class DialogAdminEmployeeSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_table_mouseAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogAdminEmployeeSearch_txtEmployeeID_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtEmployeeID_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEmployeeID_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtEmployeeID_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtEmployeeCode_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtEmployeeCode_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEmployeeCode_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtEmployeeCode_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtFirstName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtFirstName_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtFirstName_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtFirstName_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtLastName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtLastName_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtLastName_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtLastName_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtStore_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtStore_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtStore_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtStore_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtAddress_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtAddress_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtAddress_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtHomeNo_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtHomeNo_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtHomeNo_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtHomeNo_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtMobileNo_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtMobileNo_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtMobileNo_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtMobileNo_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtWorkNo_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtWorkNo_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtWorkNo_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtWorkNo_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtEmail_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtEmail_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEmail_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtEmail_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtCity_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtCity_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCity_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtCity_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtCountry_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtCountry_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCountry_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtCountry_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtRowLimit1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtRowLimit1_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRowLimit1_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRowLimit1_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_this_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_table_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogAdminEmployeeSearch_txtStore_actionAdapter implements java.awt.event.ActionListener {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtStore_actionAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtStore_actionPerformed(e);
  }
}

class DialogAdminEmployeeSearch_txtBirthday_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_txtBirthday_keyAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtBirthday_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtBirthday_keyTyped(e);
  }
}

class DialogAdminEmployeeSearch_btnSearch_focusAdapter extends java.awt.event.FocusAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_btnSearch_focusAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.btnSearch_focusGained(e);
  }
}

class DialogAdminEmployeeSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogAdminEmployeeSearch adaptee;

  DialogAdminEmployeeSearch_this_windowAdapter(DialogAdminEmployeeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
