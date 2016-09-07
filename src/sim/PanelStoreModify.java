package sim;

import java.util.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;

import btm.swing.*;
import common.*;
import java.awt.event.*;
import btm.attr.*;
import java.sql.Statement;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Store Setup -> Modify</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelStoreModify extends JPanel {
  DataAccessLayer DAL;
  FrameMainSim frmMain;
  Statement stmt = null;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague,ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  Vector vSql = new Vector();
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
  BJButton btnAdd = new BJButton();
  //
  StripedTableCellRenderer stripedTable = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  DialogLocation dlgLocation ;
  int deleteRow = -1;
  FlowLayout flowLayout1 = new FlowLayout();
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
  JLabel lblEmail = new JLabel();
  JLabel lblFaxNo = new JLabel();
  JLabel lblPhoneNo = new JLabel();
  JLabel lblManagerName = new JLabel();
  JLabel lblCountry = new JLabel();
  JLabel lblCounty = new JLabel();
  JButton btnChildID = new JButton();
  JTextField txtChildID = new JTextField();
  JTextField txtChildName = new JTextField();
//  BJComboBox cboCurrCDE = new BJComboBox();
  JTextField txtEmail = new JTextField();
  JTextField txtFaxNo = new JTextField();
  JTextField txtPhoneNo = new JTextField();
  JTextField txtManagerName = new JTextField();
  JTextField txtCountry = new JTextField();
  JTextField txtCounty = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();

  public PanelStoreModify(FrameMainSim frmMain) {
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
    jPanel2.setBorder(null);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setOpaque(true);
    jPanel2.setPreferredSize(new Dimension(450, 220));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(350, 220));
    jPanel3.setLayout(borderLayout3);
    jPanel4.setPreferredSize(new Dimension(120, 10));
    jPanel6.setPreferredSize(new Dimension(120, 10));
    jPanel8.setPreferredSize(new Dimension(80,10));
    jPanel7.setPreferredSize(new Dimension(230, 10));
    jPanel5.setPreferredSize(new Dimension(250, 10));
    jPanel5.setLayout(flowLayout1);
    jScrollPane1.setPreferredSize(new Dimension(800, 280));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    jPanel15.setPreferredSize(new Dimension(700, 50));
        jPanel15.setLayout(flowLayout2);
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F2)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnCancel.addActionListener(new PanelStoreModify_btnCancel_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdd.setToolTipText(lang.getString("Modify")+" (F1)");
    btnAdd.setText("<html><center><b>"+lang.getString("Modify")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnAdd.addActionListener(new PanelStoreModify_btnAdd_actionAdapter(this));



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
    txtCity.addFocusListener(new PanelStoreModify_txtCity_focusAdapter(this));
    txtCity.addKeyListener(new PanelStoreModify_txtCity_keyAdapter(this));
    txtAddress2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress2.setPreferredSize(new Dimension(200, 20));
    txtAddress2.addKeyListener(new PanelStoreModify_txtAddress2_keyAdapter(this));
    txtAddress2.setText("");
    txtAddress2.addFocusListener(new PanelStoreModify_txtAddress2_focusAdapter(this));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress1.setPreferredSize(new Dimension(200, 20));
    txtAddress1.addKeyListener(new PanelStoreModify_txtAddress1_keyAdapter(this));
    txtAddress1.setText("");
    txtAddress1.addFocusListener(new PanelStoreModify_txtAddress1_focusAdapter(this));
    txtShortName1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtShortName1.setPreferredSize(new Dimension(200, 20));
    txtShortName1.addKeyListener(new PanelStoreModify_txtShortName1_keyAdapter(this));
    txtShortName1.setText("");
    txtShortName1.addFocusListener(new PanelStoreModify_txtShortName1_focusAdapter(this));
    txtName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtName.setPreferredSize(new Dimension(200, 20));
    txtName.addKeyListener(new PanelStoreModify_txtName_keyAdapter(this));
    txtName.setText("");
    txtName.addFocusListener(new PanelStoreModify_txtName_focusAdapter(this));
    txtStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtStoreID.setPreferredSize(new Dimension(200, 20));
    txtStoreID.setEditable(false);
    txtStoreID.setText("");
    cboType.setEnabled(false);
    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboType.setPreferredSize(new Dimension(200, 20));
    lblChildID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblChildID.setPreferredSize(new Dimension(110, 20));
    lblChildID.setText(lang.getString("ParentID")+":");
    lblEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmail.setPreferredSize(new Dimension(110, 20));
    lblEmail.setText(lang.getString("Email")+":");
    lblFaxNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFaxNo.setPreferredSize(new Dimension(110, 20));
    lblFaxNo.setText(lang.getString("Fax")+":");
    lblPhoneNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPhoneNo.setPreferredSize(new Dimension(110, 20));
    lblPhoneNo.setText(lang.getString("Phone")+":");
    lblManagerName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblManagerName.setPreferredSize(new Dimension(110, 20));
    lblManagerName.setText(lang.getString("ManagerName")+":");
    lblCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCountry.setPreferredSize(new Dimension(110, 20));
    lblCountry.setText(lang.getString("Country")+":");
    lblCounty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCounty.setPreferredSize(new Dimension(110, 20));
    lblCounty.setText(lang.getString("County")+":");
    btnChildID.setEnabled(false);
    btnChildID.setOpaque(true);
    btnChildID.setPreferredSize(new Dimension(40, 20));
    btnChildID.addActionListener(new PanelStoreModify_btnChildID_actionAdapter(this));
    btnChildID.setText("...");
    txtChildID.setPreferredSize(new Dimension(57, 21));
    txtChildID.setEditable(false);
    txtChildID.setText("");
    txtChildName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtChildName.setPreferredSize(new Dimension(155, 20));
    txtChildName.setEditable(false);
    txtChildName.setText("");
    txtEmail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEmail.setPreferredSize(new Dimension(200, 20));
    txtEmail.addKeyListener(new PanelStoreModify_txtEmail_keyAdapter(this));
    txtEmail.setText("");
    txtEmail.addFocusListener(new PanelStoreModify_txtEmail_focusAdapter(this));
    txtFaxNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtFaxNo.setPreferredSize(new Dimension(200, 20));
    txtFaxNo.addKeyListener(new PanelStoreModify_txtFaxNo_keyAdapter(this));
    txtFaxNo.setText("");
    txtFaxNo.addFocusListener(new PanelStoreModify_txtFaxNo_focusAdapter(this));
    txtPhoneNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPhoneNo.setPreferredSize(new Dimension(200, 20));
    txtPhoneNo.addKeyListener(new PanelStoreModify_txtPhoneNo_keyAdapter(this));
    txtPhoneNo.setText("");
    txtPhoneNo.addFocusListener(new PanelStoreModify_txtPhoneNo_focusAdapter(this));
    txtManagerName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtManagerName.setPreferredSize(new Dimension(200, 20));
    txtManagerName.addKeyListener(new PanelStoreModify_txtManagerName_keyAdapter(this));
    txtManagerName.setText("");
    txtManagerName.addFocusListener(new PanelStoreModify_txtManagerName_focusAdapter(this));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCountry.setPreferredSize(new Dimension(200, 20));
    txtCountry.addKeyListener(new PanelStoreModify_txtCountry_keyAdapter(this));
    txtCountry.setText("");
    txtCountry.addFocusListener(new PanelStoreModify_txtCountry_focusAdapter(this));
    txtCounty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCounty.setPreferredSize(new Dimension(200, 20));
    txtCounty.addKeyListener(new PanelStoreModify_txtCounty_keyAdapter(this));
    txtCounty.setText("");
    txtCounty.addFocusListener(new PanelStoreModify_txtCounty_focusAdapter(this));
    table.addKeyListener(new PanelStoreModify_table_keyAdapter(this));
    this.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel15, BorderLayout.CENTER);
    jPanel15.add(btnAdd, null);
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
    jPanel6.add(lblChildID, null);
    jPanel3.add(jPanel7, BorderLayout.CENTER);
    this.add(jScrollPane1, BorderLayout.SOUTH);
    jScrollPane1.getViewport().add(table, null);
    jPanel5.add(txtStoreID, null);
    jPanel5.add(cboType, null);
    jPanel5.add(txtName, null);
    jPanel5.add(txtShortName1, null);
    jPanel5.add(txtAddress1, null);
    jPanel5.add(txtAddress2, null);
    jPanel5.add(txtCity, null);
    jPanel7.add(txtCounty, null);
    jPanel7.add(txtCountry, null);
    jPanel7.add(txtManagerName, null);
    jPanel7.add(txtPhoneNo, null);
    jPanel7.add(txtFaxNo, null);
    jPanel7.add(txtEmail, null);
    jPanel7.add(txtChildName, null);
    jPanel7.add(txtChildID, null);
    jPanel7.add(btnChildID, null);
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

//    dlgLocation = new DialogLocation(frmMain,"", true,frmMain);
    txtChildID.setVisible(false);
    cboType.addItem("Store");
    cboType.addItem("Distribution");
  }


  void btnChildID_actionPerformed(ActionEvent e) {
    dlgLocation.flagDialog = Constant.STORE_SETUP;
    dlgLocation.setVisible(true) ;
    if (dlgLocation.isOKButton()) {
      txtChildName.setText(dlgLocation.getChildNameLocation());
      txtChildID.setText(dlgLocation.getChildIdLocation());
      dlgLocation.setVisible(false) ;
    }

  }
  //Show data
  void showData(long storeID){
    dlgLocation = new DialogLocation(frmMain,"", true,frmMain);
    while (dm.getRowCount()>0){
      dm.removeRow(0);
    }
    ResultSet rs = null;
    java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
           "dd/MM/yyyy");
    try{
      stmt = DAL.getConnection().createStatement();
      String sql = "select store_id, store_type, name, short_name1, " +
          " address_1, address_2, city, county, country, manager_name, remodel_date, " +
          " phone_no, fax_no, email, " +
          " s.child_id, child_name " +
          " from BTM_IM_STORE s, BTM_IM_ORG_HIR o " +
          " where s.child_id = o.child_id and store_id =" + storeID;
//      System.out.println(sql);
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        txtStoreID.setText("" + rs.getLong("store_id"));
        if (rs.getString("store_type").equalsIgnoreCase("D")){
          cboType.setSelectedIndex(1);
        }else{
          cboType.setSelectedIndex(0);
        }
        txtName.setText(rs.getString("name").trim());
        txtShortName1.setText(rs.getString("short_name1"));
        txtAddress1.setText(rs.getString("address_1"));
        txtAddress2.setText(rs.getString("address_2"));
        txtCity.setText(rs.getString("city"));
        txtCounty.setText(rs.getString("county"));
        txtCountry.setText(rs.getString("country"));
        txtManagerName.setText(rs.getString("manager_name"));
        txtPhoneNo.setText(rs.getString("phone_no"));
        txtFaxNo.setText(rs.getString("fax_no"));
        txtEmail.setText(rs.getString("email"));
        txtChildID.setText(rs.getString("child_id"));
        txtChildName.setText(rs.getString("child_name"));
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

  }

  void btnCancel_actionPerformed(ActionEvent e) {

//  frmMain.showScreen(Constant.SCRS_STORE_SEARCH);
//  frmMain.showScreen(Constant.SCRS_STORE_SETUP);
    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,lang.getString("TT0004"),true,frmMain,frmMain.storeBusObj);
    dlgStoreSearch.setText(frmMain.storeBusObj.getStoreID(), frmMain.storeBusObj.getStoreName(),
                           frmMain.storeBusObj.getAddress1(),
                           frmMain.storeBusObj.getCity(),
                           frmMain.storeBusObj.getCountry(), frmMain.storeBusObj.getManagerName(),
                           frmMain.storeBusObj.getPhoneNo());
    dlgStoreSearch.searchData(frmMain.pnlStoreSetup.rowNum);
    dlgStoreSearch.txtRowLimit.setText("" + frmMain.pnlStoreSetup.rowNum);
    dlgStoreSearch.setVisible(true);
    if (dlgStoreSearch.done == false){
      frmMain.showScreen(Constant.SCRS_STORE_SETUP);
    }else {
      showData(Long.parseLong(dlgStoreSearch.getData()));
    }

  }

  void btnAdd_actionPerformed(ActionEvent e) {
    long storeID = 0;
    if (!txtStoreID.getText().equals("")){
      if (ut.isLongIntString(txtStoreID.getText())){
        storeID = Long.parseLong(txtStoreID.getText());
        if (!vSql.isEmpty()){
          for (int i=0; i< dm.getRowCount(); i++){
            String storeIDOld = "" + table.getValueAt(i,0);
            if (storeID == Long.parseLong(storeIDOld)){
              ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS311_StoreIDExist"));
              txtStoreID.requestFocus(true);
              return;
            }
          }
        }
      }else{
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS310_StoreIDIsNum"));
        txtStoreID.requestFocus(true);
        return;
      }
    }else{
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1039_StoreIDNotNull"));
      txtStoreID.requestFocus(true);
      return;
    }
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
    String childID = txtChildID.getText().trim();
    String closedDate = "";
    if(txtName.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS305_StoreNameNotNull"));
      txtName.requestFocus(true);
      return;
    }
    if (managerName.equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS307_ManagerNameNotNull"));
      txtManagerName.requestFocus(true);
      return;
    }
    if (childID.equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS308_ChooseParentID"));
      btnChildID.requestFocus(true);
      return;
    }
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    String sql = "update BTM_IM_STORE set store_type ='" + type + "',name='" + name + "',short_name1='" + shortName1 +
        "',address_1='" + address1 +
        "',address_2='" + address2 + "',county='" + county + "',city='" + city +
        "',country='" + country + "',manager_name='" + managerName +
        "',phone_no='" + phoneNo + "',fax_no='" + faxNo + "',email='" + email +
        "',child_id='" + childID + "' where store_id=" + storeID;
//    System.out.println(sql);
    DAL.executeUpdate(sql,stmt);
    txtStoreID.setText("");
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
    txtChildID.setText("");
    txtChildName.setText("");
    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,lang.getString("TT0004"),true,frmMain,frmMain.storeBusObj);
    dlgStoreSearch.setText(frmMain.storeBusObj.getStoreID(), frmMain.storeBusObj.getStoreName(),
                           frmMain.storeBusObj.getAddress1(),
                           frmMain.storeBusObj.getCity(),
                           frmMain.storeBusObj.getCountry(), frmMain.storeBusObj.getManagerName(),
                           frmMain.storeBusObj.getPhoneNo());

    dlgStoreSearch.searchData(frmMain.pnlStoreSetup.rowNum);
    dlgStoreSearch.txtRowLimit.setText("" + frmMain.pnlStoreSetup.rowNum);
    dlgStoreSearch.setVisible(true);
    if (dlgStoreSearch.done == false){
      frmMain.showScreen(Constant.SCRS_STORE_SETUP);
    }else {
      storeID = Long.parseLong(dlgStoreSearch.getData());
      showData(storeID);
    }
    try {
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

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

  void txtStoreID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtStoreID,Store.LEN_STORE_ID);
  }

  void txtName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtName,Store.LEN_STORE_NAME);
    navigationComp(e, txtShortName1);
  }

  void txtShortName1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtShortName1,Store.LEN_STORE_SHORT_NAME1);
    navigationComp(e, txtAddress1);
  }

  void txtAddress1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAddress1,Store.LEN_STORE_ADDRESS_1);
    navigationComp(e, txtAddress2);
  }

  void txtAddress2_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAddress2,Store.LEN_STORE_ADDRESS_2);
    navigationComp(e, txtCity);
  }

  void txtCity_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCity,Store.LEN_STORE_CITY);
    navigationComp(e, txtCounty);
  }

  void txtCounty_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCounty,Store.LEN_STORE_COUNTY);
    navigationComp(e, txtCountry);
  }

  void txtCountry_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCountry,Store.LEN_STORE_COUNTRY);
    navigationComp(e, txtManagerName);
  }

  void txtManagerName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtManagerName,Store.LEN_STORE_MANAGER_NAME);
    navigationComp(e, txtPhoneNo);
  }

  void txtPhoneNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtPhoneNo,Store.LEN_STORE_PHONE_NO);
    ut.checkPhone(e);
    navigationComp(e, txtFaxNo);
  }

  void txtFaxNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtFaxNo,Store.LEN_STORE_FAX_NO);
    ut.checkPhone(e);
    navigationComp(e, txtEmail);
  }

  void txtEmail_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtEmail,Store.LEN_STORE_EMAIL);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      btnAdd.doClick();
    }else if(e.getKeyChar() == KeyEvent.VK_TAB){
      txtName.requestFocus(true);
      txtName.selectAll();
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
   // ENTER
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

        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnAdd.doClick();
        }

        else if (identifier.intValue() == KeyEvent.VK_F2 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
      }
    }

  void table_mousePressed(MouseEvent e) {
//    btnDone.requestFocus();
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

  void txtPhoneNo_focusGained(FocusEvent e) {
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
                 btnCancel.doClick();
               }
  }

}



class PanelStoreModify_btnChildID_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreModify adaptee;

  PanelStoreModify_btnChildID_actionAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnChildID_actionPerformed(e);
  }
}

class PanelStoreModify_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreModify adaptee;

  PanelStoreModify_btnCancel_actionAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelStoreModify_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelStoreModify adaptee;

  PanelStoreModify_btnAdd_actionAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelStoreModify_txtStoreID_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtStoreID_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtStoreID_keyTyped(e);
    }
}

class PanelStoreModify_txtName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtName_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtName_keyTyped(e);
    }
}

class PanelStoreModify_txtShortName1_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtShortName1_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtShortName1_keyTyped(e);
    }
}

class PanelStoreModify_txtAddress1_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtAddress1_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtAddress1_keyTyped(e);
    }
}

class PanelStoreModify_txtAddress2_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtAddress2_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtAddress2_keyTyped(e);
    }
}

class PanelStoreModify_txtCity_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtCity_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtCity_keyTyped(e);
    }
}

class PanelStoreModify_txtCounty_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtCounty_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtCounty_keyTyped(e);
    }
}

class PanelStoreModify_txtCountry_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtCountry_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtCountry_keyTyped(e);
    }
}

class PanelStoreModify_txtManagerName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtManagerName_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtManagerName_keyTyped(e);
    }
}

class PanelStoreModify_txtPhoneNo_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtPhoneNo_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtPhoneNo_keyTyped(e);
    }
}

class PanelStoreModify_txtFaxNo_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtFaxNo_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtFaxNo_keyTyped(e);
    }
}

class PanelStoreModify_txtEmail_keyAdapter extends java.awt.event.KeyAdapter {
    PanelStoreModify adaptee;

    PanelStoreModify_txtEmail_keyAdapter(PanelStoreModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtEmail_keyTyped(e);
    }
}

class PanelStoreModify_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_table_mouseAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelStoreModify_txtName_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtName_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtName_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtName_focusLost(e);
  }
}

class PanelStoreModify_txtShortName1_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtShortName1_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtShortName1_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtShortName1_focusLost(e);
  }
}

class PanelStoreModify_txtAddress1_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtAddress1_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtAddress1_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtAddress1_focusLost(e);
  }
}

class PanelStoreModify_txtAddress2_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtAddress2_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtAddress2_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtAddress2_focusLost(e);
  }
}

class PanelStoreModify_txtCity_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtCity_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtCity_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtCity_focusLost(e);
  }
}

class PanelStoreModify_txtCounty_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtCounty_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtCounty_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtCounty_focusLost(e);
  }
}

class PanelStoreModify_txtCountry_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtCountry_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtCountry_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtCountry_focusLost(e);
  }
}

class PanelStoreModify_txtManagerName_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtManagerName_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtManagerName_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtManagerName_focusLost(e);
  }
}

class PanelStoreModify_txtPhoneNo_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtPhoneNo_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtPhoneNo_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtPhoneNo_focusLost(e);
  }
}

class PanelStoreModify_txtFaxNo_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtFaxNo_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtFaxNo_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtFaxNo_focusLost(e);
  }
}

class PanelStoreModify_txtEmail_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_txtEmail_focusAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtEmail_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtEmail_focusLost(e);
  }
}

class PanelStoreModify_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStoreModify adaptee;

  PanelStoreModify_table_keyAdapter(PanelStoreModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

