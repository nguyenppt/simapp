package sim;

import common.*;
import btm.attr.StockOnHand;
import btm.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
import javax.swing.border.*;
import btm.business.*;
import btm.attr.*;
import java.awt.print.*;
import javax.swing.event.*;
import java.util.ResourceBundle;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Receive -> Damage Goods</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
////not use
public class PanelDamageGoods extends JPanel {/*
  //Declare Data Access Layer
  DataAccessLayer DAL;
  //Declare Utils
  Utils ut = new Utils();
  //Declare Frame Main
  FrameMainSim frmMain;
  //Declare a batch update
  Statement stmt = null;
  String supplierID = "";
  Vector vInfo = new Vector();//Vector save array info of Receipt Item
  ItemMasterBusObj  bItemMasterBusObj = new ItemMasterBusObj();
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JLabel lblQty = new JLabel();
  JLabel lblItemID = new JLabel();
  JTextField txtQuantity = new JTextField();
  JLabel lblStoreID = new JLabel();
  JLabel lblReceiptDate = new JLabel();
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnModify = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  GridLayout gridLayout1 = new GridLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel lblReceiptID = new JLabel();
  JTextField txtItemID = new JTextField();
  JTextField txtReceiptID = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  JButton btnItemSearch = new JButton();
  JButton btnReceiptDateSearch = new JButton();
  JTextField txtReceiptDate = new JTextField();
  JLabel lblDescription = new JLabel();
  JTextArea txtDescription = new JTextArea();
  TitledBorder titledBorder1;
  JLabel lblItemName = new JLabel();
  BJComboBox cboFromStore = new BJComboBox();
  BJButton btnClear = new BJButton();
  BJButton btnViewReceipt = new BJButton();
  boolean flagSetHotKeyForAdd = true;
  BJComboBox cboToStore = new BJComboBox();
  String itemName = "";
  String articleNo = "";
  String sizeName = "";
  String empID = "";
  JTextField txtCreaterName = new JTextField();
  JLabel lblQty1 = new JLabel();
  JLabel lblQty2 = new JLabel();
  JComboBox cboStatus = new JComboBox();
  public PanelDamageGoods(FrameMainSim frmMain) {
    try {
        this.frmMain = frmMain;
        this.DAL = frmMain.getDAL();
        jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();
    titledBorder1 = new TitledBorder("");
    this.setLayout(borderLayout1);
    jPanel1.setPreferredSize(new Dimension(800, 50));
    this.setPreferredSize(new Dimension(800, 600));
    jPanel2.setPreferredSize(new Dimension(800, 150));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(800, 340));
    jPanel3.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(760, 335));
    jPanel4.setPreferredSize(new Dimension(400, 150));
    jPanel4.setLayout(borderLayout3);
    jPanel5.setMinimumSize(new Dimension(300, 150));
    jPanel5.setLayout(borderLayout4);
    jPanel6.setPreferredSize(new Dimension(120, 150));
    jPanel6.setLayout(flowLayout1);
    jPanel7.setPreferredSize(new Dimension(180, 150));
    jPanel7.setLayout(flowLayout4);
    jPanel10.setPreferredSize(new Dimension(50, 150));
    lblQty.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblQty.setPreferredSize(new Dimension(90, 20));
    lblQty.setText("Quantity : ");
    lblItemID.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblItemID.setPreferredSize(new Dimension(110, 20));
    lblItemID.setHorizontalAlignment(SwingConstants.LEADING);
    lblItemID.setText("Item ID : ");
    txtQuantity.setFont(new java.awt.Font("SansSerif", 1, 11));
    txtQuantity.setPreferredSize(new Dimension(170, 20));
    txtQuantity.setText("");
    jPanel8.setPreferredSize(new Dimension(100, 150));
    jPanel8.setLayout(flowLayout2);
    jPanel9.setPreferredSize(new Dimension(180, 150));
    jPanel9.setLayout(flowLayout3);
    lblStoreID.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblStoreID.setPreferredSize(new Dimension(110, 20));
    lblStoreID.setToolTipText("");
    lblStoreID.setText("From Store :");
    lblReceiptDate.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblReceiptDate.setPreferredSize(new Dimension(90, 20));
    lblReceiptDate.setText("Receipt Date : ");
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText("Back (F6)");
    btnCancel.setText("<html><center><b>Back </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F6</html>");
    btnCancel.addActionListener(new PanelDamageGoods_btnCancel_actionAdapter(this));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText("Delete (F3)");
    btnDelete.setText("<html><center><b>Delete </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnDelete.addActionListener(new PanelDamageGoods_btnDelete_actionAdapter(this));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText("Modify (F2)");
    btnModify.setText("<html><center><b>Modify </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText("Add (F2)");
    btnAdd.setText("<html><center><b>Add </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelDamageGoods_btnAdd_actionAdapter(this));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText("Done(F1)");
    btnDone.setText("<html><center><b>Done</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelDamageGoods_btnDone_actionAdapter(this));
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    lblReceiptID.setFont(new java.awt.Font("Dialog", 1, 12));
    lblReceiptID.setPreferredSize(new Dimension(110, 20));
    lblReceiptID.setText("Damage Goods ID:");
    txtItemID.setFont(new java.awt.Font("SansSerif", 1, 11));
    txtItemID.setNextFocusableComponent(txtQuantity);
    txtItemID.setPreferredSize(new Dimension(170, 20));
    txtItemID.setText("");
    txtItemID.addKeyListener(new PanelDamageGoods_txtItemID_keyAdapter(this));
    txtReceiptID.setBackground(SystemColor.control);
    txtReceiptID.setEnabled(false);
    txtReceiptID.setFont(new java.awt.Font("Dialog", 1, 11));
    txtReceiptID.setPreferredSize(new Dimension(170, 20));
    txtReceiptID.setHorizontalAlignment(SwingConstants.LEADING);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    btnItemSearch.setPreferredSize(new Dimension(41, 23));
    btnItemSearch.setText("...");
    btnItemSearch.addActionListener(new PanelDamageGoods_btnItemSearch_actionAdapter(this));
    txtReceiptDate.setFont(new java.awt.Font("SansSerif", 1, 11));
    txtReceiptDate.setMinimumSize(new Dimension(6, 21));
    txtReceiptDate.setPreferredSize(new Dimension(170, 20));
    txtReceiptDate.setToolTipText("");
    txtReceiptDate.setEditable(false);
    txtReceiptDate.setText(ut.getSystemDate(1));
    btnReceiptDateSearch.setEnabled(false);
    btnReceiptDateSearch.setPreferredSize(new Dimension(41, 23));
    btnReceiptDateSearch.setText("...");
    lblDescription.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblDescription.setPreferredSize(new Dimension(90, 20));
    lblDescription.setText("Description :");
    txtDescription.setFont(new java.awt.Font("SansSerif", 1, 12));
    txtDescription.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDescription.setDebugGraphicsOptions(0);
    txtDescription.setPreferredSize(new Dimension(230, 46));
    lblItemName.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblItemName.setPreferredSize(new Dimension(110, 20));
    lblItemName.setText("To Store :");
    cboFromStore.setEnabled(true);
    cboFromStore.setFont(new java.awt.Font("SansSerif", 1, 11));
    cboFromStore.setDoubleBuffered(false);
    cboFromStore.setMinimumSize(new Dimension(26, 21));
    cboFromStore.setPreferredSize(new Dimension(170, 22));
    cboFromStore.setEditable(false);
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText("Clear(F4)");
    btnClear.setText("<html><center><b>Clear</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnClear.addActionListener(new PanelDamageGoods_btnClear_actionAdapter(this));
    btnViewReceipt.setText("<html><center><b>View Receipt</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html>");
    btnViewReceipt.addActionListener(new PanelDamageGoods_btnViewReceipt_actionAdapter(this));
    btnViewReceipt.setToolTipText("View Receipt(F5)");
    btnViewReceipt.setPreferredSize(new Dimension(120, 37));
    cboToStore.setPreferredSize(new Dimension(170, 22));
    cboToStore.setMinimumSize(new Dimension(26, 21));
    cboToStore.setFont(new java.awt.Font("SansSerif", 1, 11));
    txtCreaterName.setText("");
    txtCreaterName.setPreferredSize(new Dimension(170, 20));
    txtCreaterName.setFont(new java.awt.Font("SansSerif", 1, 11));
    lblQty1.setText("Creater Name : ");
    lblQty1.setPreferredSize(new Dimension(90, 20));
    lblQty1.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblQty2.setText("Status :");
    lblQty2.setPreferredSize(new Dimension(90, 20));
    lblQty2.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblQty2.setMinimumSize(new Dimension(46, 16));
    cboStatus.setFont(new java.awt.Font("Dialog", 1, 12));
    cboStatus.setOpaque(true);
    cboStatus.setPreferredSize(new Dimension(170, 20));
    cboStatus.setSelectedIndex(-1);
    jPanel7.add(txtReceiptID, null);
    jPanel7.add(cboFromStore, null);
    jPanel7.add(cboToStore, null);
    jPanel7.add(txtItemID, null);
    jPanel7.add(btnItemSearch, null);
    jPanel6.add(lblReceiptID, null);
    jPanel6.add(lblStoreID, null);
    jPanel6.add(lblItemName, null);
    this.add(jPanel1,  BorderLayout.NORTH);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel4,  BorderLayout.WEST);
    jPanel4.add(jPanel6,  BorderLayout.WEST);
    jPanel6.add(lblItemID, null);
    jPanel4.add(jPanel7, BorderLayout.CENTER);
    jPanel4.add(jPanel10,  BorderLayout.EAST);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel8,  BorderLayout.WEST);
    jPanel8.add(lblReceiptDate, null);
    jPanel8.add(lblQty1, null);
    jPanel8.add(lblQty2, null);
    jPanel8.add(lblQty, null);
    jPanel8.add(lblDescription, null);
    jPanel5.add(jPanel9,  BorderLayout.CENTER);
    jPanel9.add(txtReceiptDate, null);
    jPanel9.add(btnReceiptDateSearch, null);
    jPanel9.add(txtCreaterName, null);
    jPanel9.add(cboStatus, null);
    jPanel9.add(txtQuantity, null);
    jPanel9.add(txtDescription, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    showButton(true);
    dm.addColumn("Item ID");
    dm.addColumn("Item Name");
    dm.addColumn("Article No");
    dm.addColumn("Size");
    dm.addColumn("Quantity");
    table.setRowHeight(30);
    table.setColor(Color.lightGray, Color.white);
    txtQuantity.addKeyListener(new PanelDamageGoods_txtQuantity_keyAdapter(this));
    cboStatus.removeAllItems();
    cboStatus.addItem("None");
    cboStatus.addItem("Damage Goods");
    cboStatus.addItem("Adjustment");
  }
  private String getReceiptID(){
    String receiptID=DAL.getStoreID()+ ut.getSystemDateTime5();
    receiptID = receiptID.replaceAll(":"," ").replaceAll("-"," ").replaceAll(" ","");
    return receiptID;
  }

  void showButton(boolean flagSetButton) {
    this.jPanel1.removeAll();
    this.jPanel1.updateUI();
    jPanel1.add(btnDone, null);
    if(flagSetButton == true){
      jPanel1.add(btnAdd, null);
    }
    else{
      jPanel1.add(btnModify, null);
    }
    jPanel1.add(btnDelete, null);
    jPanel1.add(btnClear, null);
    jPanel1.add(btnViewReceipt, null);
    jPanel1.add(btnCancel, null);
  }
  void getEmpName(){

    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select emp_id, concat(first_name, concat(' ',last_name)) emp_name from btm_im_employee where emp_cde='" + DAL.getEmpCode() + "'" ;
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        this.empID = rs.getString("emp_id");
        txtCreaterName.setText(rs.getString("emp_name"));
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

  }
  void getData(){
    getEmpName();
    txtReceiptID.setText(getReceiptID());
    cboStatus.setSelectedIndex(0);
    txtCreaterName.setEditable(false);
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "Select store_id, name from btm_im_store where store_id <> 1200 order by name";
    System.out.println(sql);

    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sql,stmt);
      cboFromStore.setData1(rs);
      rs = null;
      sql = "Select store_id, name from btm_im_store order by name";
      rs = DAL.executeQueries(sql,stmt);
      cboToStore.setData(rs);
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

  }
  //Check exist
  boolean checkExist(String itemID, long storeID){
    ResultSet rs = null;
    try{
      String sql = "select * from btm_im_item_loc_soh where item_id ='" + itemID +
          "' and store_id =" + storeID;
      System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                           TYPE_SCROLL_INSENSITIVE,
                                           ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      if (rs.next()){
        stmt.close();
        return true;
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return false;
  }





  void txtQuantity_keyTyped(KeyEvent e) {
    ut.checkNumber(e);
    ut.limitLenjTextField(e,txtQuantity,StockOnHand.LEN_QTY);
     if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
         (e.getKeyChar() != e.VK_BACK_SPACE))
       e.consume();
  }

  void txtItemID_keyTyped(KeyEvent e) {
    ut.checkNumber(e);
    ut.limitLenjTextField(e,txtItemID,StockOnHand.LEN_ITEM_ID);
    if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
      (e.getKeyChar() != e.VK_BACK_SPACE) && (e.getKeyChar() != e.VK_ENTER))
    e.consume();
    else if (e.getKeyChar() == e.VK_ENTER){
      if (!txtItemID.getText().equalsIgnoreCase("")){
        txtQuantity.requestFocus(true);
      }
    }
  }

  void btnItemSearch_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        "JSIM - ITEM LOOKUP", true, frmMain, itemBusObject);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      txtItemID.setText(dlgItemLookup.getItemID());
      itemName = dlgItemLookup.getItemName();
      txtItemID.requestFocus(true);
    }

  }
  //getArtnoandSize
  void getArtNoandSize(){

    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select distinct im.art_no, ph.ATTR2_NAME from btm_im_price_hist ph, btm_im_item_master im " +
        " where ph.item_id = im.item_id and im.item_id = '" + txtItemID.getText() + "'" ;
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        this.articleNo = rs.getString("art_no");
        this.sizeName = rs.getString("attr2_name");
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

  }
  void btnAdd_actionPerformed(ActionEvent e) {
    addDataIntoTable();
  }
  void addDataIntoTable(){
    if (!checkDataInScreen()){
      return;
    }
    if (cboStatus.getSelectedIndex() == 0){
      ut.showMessage(frmMain,"Warning","Please choose a status");
      cboStatus.requestFocus(true);
      return;
    }
    long quantity = Long.parseLong(txtQuantity.getText());
    try {
      if (table.getRowCount()<1){
        cboFromStore.setEnabled(false);
        cboToStore.setEnabled(false);
        cboStatus.setEnabled(false);
        txtDescription.setEditable(false);
        txtReceiptDate.setEditable(false);
        btnReceiptDateSearch.setEnabled(false);
      }
      if (checkItemTable(txtItemID.getText().trim(),Long.parseLong(txtQuantity.getText()))){
        return;
      }
      getArtNoandSize();
      dm.addRow(new Object[]{txtItemID.getText(),itemName,articleNo,sizeName,String.valueOf(quantity)});
      if (table.getRowCount()>0){
        table.setRowSelectionInterval(table.getRowCount() - 1,
                                      table.getRowCount() - 1);
      }
     //show current row
      Rectangle bounds = table.getCellRect(table.getRowCount() - 1, 0, true);
      jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

      txtItemID.setText("");
      txtQuantity.setText("");
      txtItemID.requestFocus(true);
    }
    catch (Exception e) {
        DAL.setRollback(DAL.getConnection() ) ;
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }
  }
  boolean checkDataInScreen(){

    if(cboFromStore.getSelectedIndex()==0){
      ut.showMessage(this.frmMain,"Invalid Input","Store is required.");
      cboFromStore.requestFocus(true) ;
      return false;
    }
    if(txtItemID.getText().equalsIgnoreCase("")){
      ut.showMessage(this.frmMain,"Invalid Input","Please input ItemID");
      txtItemID.requestFocus(true);
      return false;
    }
    long quantity = 0;
    if(!txtQuantity.getText().equals("")){
      quantity = Long.parseLong(txtQuantity.getText());
    }
    if(quantity < 1){
      ut.showMessage(frmMain,"Invalid Input","Quantity must be greater than 0");
      txtQuantity.requestFocus(true);
      return false;
    }
    return true;
  }

  //check item in table
  boolean checkItemTable(String itemID, long qty) {
    for (int i = 0; i < dm.getRowCount(); i++) {
      String item = "" + table.getValueAt(i, 0);
      if (itemID.equalsIgnoreCase(item)) {
        long quantity = qty;
        table.setValueAt(new Long(quantity), i, 4);
        table.setRowSelectionInterval(i,i);
    //show current row
        Rectangle bounds = table.getCellRect(i, 0, true);
        jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

        txtItemID.setText("");
        txtQuantity.setText("");
        txtItemID.requestFocus(true);
        return true;
      }
    }
    return false;
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    while(table.getRowCount()>0){
      dm.removeRow(0);
    }
    vInfo.removeAllElements();
    resetDataInScreen();
    showButton(true);
    flagSetHotKeyForAdd = true;;

//    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
//    frmMain.setTitle(lang.getString("TT014")+" - Inventory Manegement");
//    frmMain.pnlMainSim.showInventManageButton();

    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showReceive();
    frmMain.setTitle(lang.getString("TT014")+" - Receive");
    frmMain.pnlMainSim.btnRecSupplier.requestFocus(true);

  }

  private void resetDataInScreen() {
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
    supplierID = "";
    txtItemID.setText("");
    itemName = "";
    this.articleNo = "";
    this.sizeName = "";
    cboFromStore.setSelectedIndex(0);
    txtQuantity.setText("");
    txtDescription.setText("");
    txtReceiptDate.setText(ut.getSystemDate(1));
    txtReceiptID.setText(getReceiptID());
    cboFromStore.setEnabled(true);
    cboStatus.setEnabled(true);
    txtDescription.setEditable(true);
    btnReceiptDateSearch.setEnabled(true);
  }

  void btnClear_actionPerformed(ActionEvent e) {
    txtItemID.setText("");
    txtQuantity.setText("");
    txtItemID.requestFocus(true);
  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if (table.getSelectedRow() >= 0) {
      dm.removeRow(table.getSelectedRow());
      txtItemID.setText("");
      txtQuantity.setText("");
      flagSetHotKeyForAdd = true;
    }
    else{
      if(dm.getRowCount()>0)
        ut.showMessage(frmMain,"Warning!","Please select item which you want to delete.");
    }

  }

  void btnDone_actionPerformed(ActionEvent e) {
    Vector vSql = new Vector();
    if (table.getRowCount()>0){
      String status = "";
      if (cboStatus.getSelectedIndex() == 1){
        status = "D";
      }else {
        status = "A";
      }
      String sql = "insert into btm_im_receive_dmg_goods values ('" +
          txtReceiptID.getText() + "'," + cboFromStore.getSelectedData() +
          "," + cboToStore.getSelectedData() + ",'" + this.empID +
          "',to_date('" + txtReceiptDate.getText() + "','DD/MM/YYYY'),'','','"+status+"')";
      System.out.println(sql);
      vSql.addElement(sql);
      for (int i=0; i<table.getRowCount(); i++){
        String sql1 = "insert into btm_im_receive_dmg_goods_item values('" +
            txtReceiptID.getText() + "','" + table.getValueAt(i,0).toString() +
            "'," + Double.parseDouble(table.getValueAt(i,4).toString()) + ")";
        System.out.println(sql1);
        vSql.addElement(sql1);
      }
      if (!vSql.isEmpty()){

        Statement stmt = null;
        try{
          stmt = DAL.getConnection().createStatement();
          DAL.setBeginTrans(DAL.getConnection());
          DAL.executeBatchQuery(vSql,stmt);
          DAL.setEndTrans(DAL.getConnection());
          stmt.close();
        }catch(Exception ex){
          ex.printStackTrace();
        }
      }
    }
    resetDataInScreen();
  }

  void btnViewReceipt_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_VIEW_DAMAGE_GOODS);
    frmMain.pnlViewDamageGoods.txtReceiptDate.requestFocus(true);
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


     // Invoked when an action occurs.
     //
    public void actionPerformed(ActionEvent e) {
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2) {
        btnAdd.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {
        btnDelete.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F4) {
        btnClear.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F5) {
        btnViewReceipt.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F6 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }
*/
}

//class PanelDamageGoods_txtQuantity_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelDamageGoods adaptee;
//
//  PanelDamageGoods_txtQuantity_keyAdapter(PanelDamageGoods adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtQuantity_keyTyped(e);
//  }
//}
//
//class PanelDamageGoods_txtItemID_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelDamageGoods adaptee;
//
//  PanelDamageGoods_txtItemID_keyAdapter(PanelDamageGoods adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtItemID_keyTyped(e);
//  }
//}
//
//class PanelDamageGoods_btnItemSearch_actionAdapter implements java.awt.event.ActionListener {
//  PanelDamageGoods adaptee;
//
//  PanelDamageGoods_btnItemSearch_actionAdapter(PanelDamageGoods adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnItemSearch_actionPerformed(e);
//  }
//}
//
//class PanelDamageGoods_btnAdd_actionAdapter implements java.awt.event.ActionListener {
//  PanelDamageGoods adaptee;
//
//  PanelDamageGoods_btnAdd_actionAdapter(PanelDamageGoods adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnAdd_actionPerformed(e);
//  }
//}
//
//class PanelDamageGoods_btnCancel_actionAdapter implements java.awt.event.ActionListener {
//  PanelDamageGoods adaptee;
//
//  PanelDamageGoods_btnCancel_actionAdapter(PanelDamageGoods adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnCancel_actionPerformed(e);
//  }
//}
//
//class PanelDamageGoods_btnClear_actionAdapter implements java.awt.event.ActionListener {
//  PanelDamageGoods adaptee;
//
//  PanelDamageGoods_btnClear_actionAdapter(PanelDamageGoods adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnClear_actionPerformed(e);
//  }
//}
//
//class PanelDamageGoods_btnDelete_actionAdapter implements java.awt.event.ActionListener {
//  PanelDamageGoods adaptee;
//
//  PanelDamageGoods_btnDelete_actionAdapter(PanelDamageGoods adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnDelete_actionPerformed(e);
//  }
//}
//
//class PanelDamageGoods_btnDone_actionAdapter implements java.awt.event.ActionListener {
//  PanelDamageGoods adaptee;
//
//  PanelDamageGoods_btnDone_actionAdapter(PanelDamageGoods adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnDone_actionPerformed(e);
//  }
//}
//
//class PanelDamageGoods_btnViewReceipt_actionAdapter implements java.awt.event.ActionListener {
//  PanelDamageGoods adaptee;
//
//  PanelDamageGoods_btnViewReceipt_actionAdapter(PanelDamageGoods adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnViewReceipt_actionPerformed(e);
//  }
//}

