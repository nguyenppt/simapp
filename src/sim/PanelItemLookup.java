package sim;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import btm.attr.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author TrungNT
 * @version 1.0 ;
 */

public class PanelItemLookup extends JPanel {
  FrameMainSim frmMain;
  DataAccessLayer DAL;
//  Statement stmt= null;
  Utils ut=new Utils();
  String idNode="";//id of prod hier node
  String itemId="";//id of item

  int parentScreen=0;

  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel pnlButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel pnlMain = new BJPanel();
  BJButton btnReset = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnDone = new BJButton();
  BJButton btnCancel = new BJButton();
  FlowLayout flowLayout1 = new FlowLayout();
  BJPanel pnlResult = new BJPanel(Constant.PANEL_TYPE_DEFAULT);
  BJPanel pnlCriteria = new BJPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  BJPanel jPanel1 = new BJPanel();
  BJPanel jPanel2 = new BJPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable tbllItemLookup = new BJTable(dm,true);
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane jScrollPane2 = new JScrollPane();
  BorderLayout borderLayout4 = new BorderLayout();
//  DefaultMutableTreeNode top =  new DefaultMutableTreeNode("Root");
//  BJTree treProdHier = new BJTree("BTM_IM_PROD_HIR",getData(),top);
  BNode rootTree ;
  BTreeModel pTreeModel;
  BJTree treProdHier ;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  BJLabel lblItemCode = new BJLabel();
  BJLabel lblItemDesc = new BJLabel();
  BJLabel lblSupplierCode = new BJLabel();
  BJLabel lblSupplierName = new BJLabel();
  BJLabel lblSearchLimit = new BJLabel();
  JTextField txtItemCode = new JTextField();
  JTextField txtItemDesc = new JTextField();
  JTextField txtSupplier = new JTextField();
  JTextField txtSupplieName = new JTextField();
  JTextField txtSearchLimit = new JTextField();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  BJPanel jPanel3 = new BJPanel();
  BJPanel jPanel4 = new BJPanel();
  JTextField txtArtNo = new JTextField();
  BJLabel lblSupplierCode1 = new BJLabel();
  JTextField txtCreatedDate = new JTextField();
  BJLabel lblSupplierName1 = new BJLabel();
  JButton jButton1 = new JButton();
  BJLabel lblSupplierName2 = new BJLabel();
  JComboBox cboStatus = new JComboBox();




  public PanelItemLookup(FrameMainSim frmMain) {
    try {
      this.frmMain=frmMain;
      this.DAL = frmMain.DAL;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();

    this.setLayout(borderLayout1);
    btnReset.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnReset.setToolTipText(lang.getString("Clear")+" (F7)");
    btnReset.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</" +
    "html>");
    btnReset.addActionListener(new PanelItemLookup_btnReset_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText(lang.getString("Search")+" (F3)");
    btnSearch.addActionListener(new PanelItemLookup_btnSearch_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText(lang.getString("Done")+" (F1)");
    btnDone.addActionListener(new PanelItemLookup_btnDone_actionAdapter(this));
    pnlButton.setLayout(flowLayout1);
    pnlMain.setLayout(borderLayout2);
    pnlCriteria.setMinimumSize(new Dimension(10, 10));
    pnlCriteria.setPreferredSize(new Dimension(10, 250));
    pnlCriteria.setLayout(gridBagLayout1);
    jPanel1.setLayout(null);
    pnlResult.setLayout(borderLayout3);
    jPanel2.setLayout(borderLayout4);

    lblItemCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemCode.setText(lang.getString("ItemID")+":");
    lblItemCode.setBounds(new Rectangle(16, 8, 115, 15));
    lblItemDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemDesc.setText(lang.getString("ItemName")+":");
    lblItemDesc.setBounds(new Rectangle(16, 35, 115, 15));
    lblSupplierCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSupplierCode.setText(lang.getString("SupplierID")+":");
    lblSupplierCode.setBounds(new Rectangle(16, 88, 115, 15));
    lblSupplierName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSupplierName.setText(lang.getString("SupplierName")+":");
    lblSupplierName.setBounds(new Rectangle(16, 114, 115, 15));
    lblSearchLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSearchLimit.setText(lang.getString("SearchLimit")+":");
    lblSearchLimit.setBounds(new Rectangle(16, 198, 115, 15));
    txtItemCode.setText("");
    txtItemCode.setBounds(new Rectangle(136, 8, 183, 21));
    txtItemCode.addFocusListener(new PanelItemLookup_txtItemCode_focusAdapter(this));
        txtItemCode.addKeyListener(new PanelItemLookup_txtItemCode_keyAdapter(this));
    txtItemDesc.setSelectionStart(11);
    txtItemDesc.setText("");
    txtItemDesc.setBounds(new Rectangle(136, 35, 183, 21));
    txtItemDesc.addFocusListener(new PanelItemLookup_txtItemDesc_focusAdapter(this));
        txtItemDesc.addKeyListener(new PanelItemLookup_txtItemDesc_keyAdapter(this));
    txtSupplier.setText("");
    txtSupplier.setBounds(new Rectangle(136, 88, 183, 21));
    txtSupplier.addFocusListener(new PanelItemLookup_txtSupplier_focusAdapter(this));
        txtSupplier.addKeyListener(new PanelItemLookup_txtSupplier_keyAdapter(this));
    txtSupplieName.setText("");
    txtSupplieName.setBounds(new Rectangle(136, 114, 183, 21));
    txtSupplieName.addFocusListener(new PanelItemLookup_txtSupplieName_focusAdapter(this));
        txtSupplieName.addKeyListener(new PanelItemLookup_txtSupplieName_keyAdapter(this));
    txtSearchLimit.setText(DAL.getSearchLimit());
    txtSearchLimit.setBounds(new Rectangle(136, 198, 33, 21));
    txtSearchLimit.addFocusListener(new PanelItemLookup_txtSearchLimit_focusAdapter(this));
        txtSearchLimit.addKeyListener(new PanelItemLookup_txtSearchLimit_keyAdapter(this));
    jPanel3.setPreferredSize(new Dimension(10, 20));
    jPanel4.setPreferredSize(new Dimension(10, 20));
    jScrollPane2.getViewport().setBackground(new Color(121, 152, 198));

    rootTree =new BNode( new BNodeDetail("1","1","1",Constant.root, Constant.root));
    pTreeModel = new BTreeModel(rootTree);
    treProdHier=new BJTree(pTreeModel);

    tbllItemLookup.addMouseListener(new PanelItemLookup_tbllItemLookup_mouseAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelItemLookup_btnCancel_actionAdapter(this));
    tbllItemLookup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtArtNo.setBounds(new Rectangle(136, 61, 183, 21));
    txtArtNo.addKeyListener(new PanelItemLookup_txtArtNo_keyAdapter(this));
    txtArtNo.addFocusListener(new PanelItemLookup_txtArtNo_focusAdapter(this));
    txtArtNo.setText("");
    lblSupplierCode1.setBounds(new Rectangle(16, 61, 115, 15));
    lblSupplierCode1.setText(lang.getString("UPC")+":");
    lblSupplierCode1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreatedDate.setBounds(new Rectangle(136, 142, 144, 21));
    txtCreatedDate.setEditable(false);
    txtCreatedDate.setText("");
    lblSupplierName1.setBounds(new Rectangle(16, 142, 115, 15));
    lblSupplierName1.setText(lang.getString("CreatedDate")+":");
    lblSupplierName1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jButton1.setBounds(new Rectangle(282, 142, 36, 20));
    jButton1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jButton1.setText("...");
    jButton1.addActionListener(new PanelItemLookup_jButton1_actionAdapter(this));
    pnlResult.setPreferredSize(new Dimension(454, 354));
    jScrollPane1.setPreferredSize(new Dimension(454, 354));
    lblSupplierName2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSupplierName2.setText(lang.getString("Status")+":");
    lblSupplierName2.setBounds(new Rectangle(16, 170, 115, 15));
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setBounds(new Rectangle(136, 170, 183, 21));
    cboStatus.addKeyListener(new PanelItemLookup_cboStatus_keyAdapter(this));
    tbllItemLookup.addKeyListener(new PanelItemLookup_tbllItemLookup_keyAdapter(this));
    this.add(pnlButton,  BorderLayout.NORTH);
    this.add(pnlMain, BorderLayout.CENTER);
    pnlButton.add(btnDone, null);
    pnlButton.add(btnSearch, null);
    pnlButton.add(btnReset, null);
    pnlButton.add(btnCancel, null);
    pnlMain.add(pnlCriteria, BorderLayout.NORTH);
    pnlMain.add(pnlResult, BorderLayout.CENTER);
    pnlResult.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tbllItemLookup, null);
    pnlCriteria.add(jPanel1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 264, 199));
    jPanel1.add(txtItemCode, null);
    jPanel1.add(lblItemCode, null);
    jPanel1.add(lblItemDesc, null);
    jPanel1.add(txtItemDesc, null);
    jPanel1.add(txtArtNo, null);
    jPanel1.add(lblSupplierCode1, null);
    jPanel1.add(txtSupplier, null);
    jPanel1.add(lblSupplierCode, null);
    jPanel1.add(lblSupplierName, null);
    jPanel1.add(txtSupplieName, null);
    jPanel1.add(txtCreatedDate, null);
    jPanel1.add(lblSupplierName1, null);
    jPanel1.add(jButton1, null);
    jPanel1.add(lblSearchLimit, null);
    jPanel1.add(lblSupplierName2, null);
    jPanel1.add(cboStatus, null);
    jPanel1.add(txtSearchLimit, null);
    pnlCriteria.add(jPanel2,   new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
      ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 1), 90, 124));
    jPanel2.add(jScrollPane2,  BorderLayout.CENTER);
    jPanel2.add(jPanel3, BorderLayout.SOUTH);
    jPanel2.add(jPanel4, BorderLayout.NORTH);
    jScrollPane2.getViewport().add(treProdHier, null);
    tbllItemLookup.setAutoResizeMode(tbllItemLookup.AUTO_RESIZE_ALL_COLUMNS);    //show data
    tbllItemLookup.getTableHeader().setReorderingAllowed(false);
    tbllItemLookup.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
//    showData();
//    String[] columnNames = new String[]{"Item ID", "Item Name", "Supplier Name","Parent ID","Parent Name"};
//    dm.setDataVector(new Object[][]{},columnNames);
    treProdHier.setEnabled(false);
  }
  public void applyPermission() {
     EmpRight er = new EmpRight();
     er.initData(DAL, DAL.getEmpID());
     er.setData(DAL.getEmpID(), Constant.SCRS_ITEM_LOOKUP);
     btnDone.setEnabled(er.getAdd());
     btnSearch.setEnabled(er.getSearch());
   }

  //show data
  void showData(){
      //built tree prod hier
      Vector vPrdHierAll = new Vector();
      vPrdHierAll = treProdHier.initHierAll(frmMain.getDAL(), "BTM_IM_PROD_HIR");
      Vector vPrdHierActive = new Vector();
      vPrdHierActive = treProdHier.initHierActive(vPrdHierAll);
      treProdHier.showHierTree(vPrdHierActive, rootTree);
      treProdHier.updateUI();
      //reset idNode
      idNode="";
      cboStatus.removeAllItems();
      cboStatus.addItem("None");
      cboStatus.addItem("Work Sheet");
      cboStatus.addItem("Approve");
  }
  //check data
  boolean isValidate(){
     if(!txtItemCode.getText().trim().equals("")){
         if (!ut.isLongIntInput(frmMain,txtItemCode.getText().trim(),lang.getString("MS1094_ItemCodeNumeric"))) {
             txtItemCode.requestFocus();
             return false;
         }
     }
     if(!txtSupplier.getText().trim().equals("")){
        if (!ut.isLongIntInput(frmMain,txtSupplier.getText().trim(),lang.getString("MS1095_SupplierCodeNumeric"))) {
            txtSupplier.requestFocus();
            return false;
        }
     }
     if(!txtSearchLimit.getText().trim().equals("")){
         if (!ut.isLongIntInput(frmMain,txtSearchLimit.getText().trim(),lang.getString("MS1096_SearchLimitNumeric"))) {
             txtSearchLimit.requestFocus();
            return false;
         }
     }
     return true;
  }
  void btnReset_actionPerformed(ActionEvent e) {
      txtItemCode.setText("");
      txtItemDesc.setText("");
      txtArtNo.setText("");
      txtSupplier.setText("");
      txtSupplieName.setText("");
      txtCreatedDate.setText("");
      txtSearchLimit.setText(DAL.getSearchLimit());
      setEditablTextField(true);
      while (tbllItemLookup.getRowCount()>0){
        dm.removeRow(0);
      }
      txtItemCode.requestFocus(true);
  }
  //Set editable textbox
  void setEditablTextField(boolean flag){
    txtItemCode.setEditable(flag);
    txtItemDesc.setEditable(flag);
    txtArtNo.setEditable(flag);
    txtSupplier.setEditable(flag);
    txtSupplieName.setEditable(flag);
  }

  void btnDone_actionPerformed(ActionEvent e) {
      frmMain.showScreen(parentScreen);
      if (parentScreen==Constant.SCRS_PRICE_CHANGE) {
          if (tbllItemLookup.getRowCount() > 0) {
//              frmMain.pnlPriceChange.setSelectItem(tbllItemLookup.getValueAt(
//                      tbllItemLookup.getSelectedRow(), 0).toString());
          }
      } else if (parentScreen==Constant.SCRS_ITEM_SETUP) {
          if (tbllItemLookup.getRowCount() > 0) {
            frmMain.pnlItemSetup.flagSearchItem = true;
            frmMain.pnlItemSetup.setSelectItem(tbllItemLookup.getValueAt(
                      tbllItemLookup.getSelectedRow(), 0).toString());
          }
          frmMain.pnlItemSetup.applyPermission();

      }
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    if (txtSearchLimit.getText().equalsIgnoreCase("")){
      txtSearchLimit.setText(DAL.getSearchLimit());
    }
    searchData();
  }

  void searchData(){
//    Statement stmt = null;
    ResultSet rs = null;
    String sql = "";
    if (txtItemCode.isEditable()){
      sql = "";
      sql = " select mas.item_id,mas.art_no,mas.item_name,itemsup.SUPP_NAME,pri.attr2_name, mas.child_id,pri.new_price "
          + " from BTM_IM_ITEM_MASTER mas,  "
          + "		 (select distinct item_id,new_price,attr2_name  "
          + "		  from BTM_IM_PRICE_HIST "
          + "		  where status='1'and store_id='"+DAL.getStoreID()+"') pri,  "
          + "		 (select distinct isup.item_id,isup.supp_id,sup.supp_name  "
          + "		  from BTM_IM_ITEM_LOC_SUPPLIER isup, BTM_IM_SUPPLIER sup "
          + "		  where isup.SUPP_ID=sup.SUPP_ID) itemsup "
          + " where pri.ITEM_ID=mas.ITEM_ID and mas.item_id=itemsup.ITEM_ID ";

      if (!txtItemCode.getText().equalsIgnoreCase("")) {
        sql = sql + " and lower(mas.item_id) like lower('%" +
            txtItemCode.getText().trim() + "%') ";
      }
      if (!txtItemDesc.getText().equalsIgnoreCase("")) {
        sql = sql + " and lower(mas.item_name) like lower('%" +
            txtItemDesc.getText().trim() + "%') ";
      }
      if (!txtArtNo.getText().equalsIgnoreCase("")){
        if (txtArtNo.getText().length() == 6){
          sql = sql + " and mas.art_no = '" + txtArtNo.getText() + "'" ;
        }else{
          sql = sql + " and lower(mas.art_no) like lower('%" +
              txtArtNo.getText().trim() + "%') ";
        }
      }
      if (!txtSupplier.getText().equalsIgnoreCase("")) {
        if (txtSupplier.getText().length() == 6){
          sql = sql + " and itemsup.supp_id = '" + txtSupplier.getText() + "'";
        }else{
          sql = sql + " and lower(itemsup.supp_id) like lower('%" +
              txtSupplier.getText().trim() + "%') ";
        }
      }
      if (!txtSupplieName.getText().equalsIgnoreCase("")) {
        sql = sql + " and lower(itemsup.supp_name) like lower('%" +
            txtSupplieName.getText().trim() + "%') ";
      }
      if (!txtCreatedDate.getText().equalsIgnoreCase("")){
        sql = sql + " and mas.created_date = to_date('" + txtCreatedDate.getText() + "','dd/mm/yyyy')";
      }
      if (cboStatus.getSelectedIndex() != 0){
        if (cboStatus.getSelectedIndex() == 1){
          sql = sql + " and mas.status = 'W'";
        }else if (cboStatus.getSelectedIndex() == 2){
          sql = sql + " and mas.status = 'A'";
        }
      }
    }
//      else if(treProdHier.isEnabled()){
//
//      if (treProdHier.isLeaf()){
//        sql = "";
//        sql =
//            "select distinct i.ITEM_ID ,i.ITEM_NAME, s.SUPP_NAME ,h.CHILD_ID,h.CHILD_NAME " +
//            " from BTM_IM_ITEM_MASTER i ,BTM_IM_ITEM_LOC_SUPPLIER isu ,BTM_IM_SUPPLIER s,BTM_IM_PROD_HIR h,BTM_IM_PRICE_HIST iph " +
//            " where i.ITEM_ID=isu.ITEM_ID and isu.SUPP_ID=s.SUPP_ID and i.CHILD_ID=h.CHILD_ID " +
//            " and i.ITEM_ID=iph.ITEM_ID and isu.STORE_ID=iph.STORE_ID and iph.PRICE_END_DATE=to_date('7/7/7777','DD/MM/YYYY')" +
//            " and h.child_id ='" + treProdHier.getChildId() + "'";
//      }
//    }
    if (!sql.equalsIgnoreCase("")){
      sql = "select * from (" + sql + " ) where  rownum <=" +
          Integer.parseInt(txtSearchLimit.getText());
//      System.out.println(sql);
      try{
        rs = DAL.executeScrollQueries(sql);
      }catch(Exception ex){

      }
      dm.resetIndexes();
      tbllItemLookup.setData(rs);
      if (tbllItemLookup.getRowCount() > 0) {
        tbllItemLookup.setRowSelectionInterval(0, 0);
      }
      tbllItemLookup.setHeaderName(lang.getString("ItemCode"), 0);
      tbllItemLookup.setHeaderName(lang.getString("UPC"),1);
      tbllItemLookup.setHeaderName(lang.getString("ItemName"), 2);
      tbllItemLookup.setHeaderName(lang.getString("SupplierName"), 3);
      tbllItemLookup.setHeaderName(lang.getString("Size"), 4);
      tbllItemLookup.setHeaderName(lang.getString("ParentID"), 5);
      tbllItemLookup.setHeaderName(lang.getString("NewPrice"),6);
      try{
        rs.getStatement().close();
//        stmt.close();
      }catch(Exception exx){

      }

    }

  }

  //reset Search Data
  void resetData(){
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
  }

  // set parent panel for come back
  void setParentScreen(int parentScreen){
    this.parentScreen=parentScreen;
  }
  //show detail of selected item
  void tbllItemLookup_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 1){
      treProdHier.setSelectionPathByNameNode(rootTree,tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(),5).toString());
    }
    if (e.getClickCount() == 2) {
      frmMain.pnlItemLookupDetail.setItemId(tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(), 0).toString());
      frmMain.pnlItemLookupDetail.setProdIDNode(tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(), 3).toString());
      frmMain.pnlItemLookupDetail.showData();
      frmMain.showScreen(Constant.SCRS_ITEM_LOOKUP_DETAIL);
      frmMain.pnlItemLookupDetail.btnDone.requestFocus(true);
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
      frmMain.showScreen(parentScreen);
      frmMain.pnlMainSim.showLookup();
  }

    void txtItemCode_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtItemCode,ItemMaster.LEN_ITEM_ID);
      if (e.getKeyChar() == KeyEvent.VK_ENTER){
        txtItemDesc.requestFocus(true);
      }
    }

    void txtItemDesc_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtItemDesc,ItemMaster.LEN_ITEM_DESC);
      if (e.getKeyChar() == KeyEvent.VK_ENTER){
        txtArtNo.requestFocus(true);
      }
    }

    void txtSupplier_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtSupplier,Supplier.LEN_SUP_ID);
      if (e.getKeyChar() == KeyEvent.VK_ENTER){
        txtSupplieName.requestFocus(true);
      }
    }

    void txtSupplieName_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtSupplieName,Supplier.LEN_SUP_NAME);
      if (e.getKeyChar() == KeyEvent.VK_ENTER){
        jButton1.requestFocus(true);
      }
    }

    void txtSearchLimit_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e,txtSupplieName,Constant.LEN_LIMIT_SEARCH);
        ut.checkNumber(e);
        if (e.getKeyChar() == KeyEvent.VK_ENTER){
          btnSearch.doClick();
          txtItemCode.requestFocus(true);
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
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnSearch.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F7) {
            btnReset.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnCancel.doClick();
          }
        }
      }

  void txtItemCode_focusGained(FocusEvent e) {
    setEditablTextField(true);
    txtItemCode.selectAll();
  }

  void txtItemDesc_focusGained(FocusEvent e) {
    setEditablTextField(true);
    txtItemDesc.selectAll();
  }

  void txtArtNo_focusGained(FocusEvent e) {
    setEditablTextField(true);
    txtArtNo.selectAll();
  }

  void txtSupplier_focusGained(FocusEvent e) {
    setEditablTextField(true);
    txtSupplier.selectAll();

  }

  void jButton1_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.jButton1.getX() + posXFrame + 150;
    posY = this.jButton1.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtCreatedDate.setText(date);
    }
    cboStatus.requestFocus(true);

  }

  void cboStatus_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtSearchLimit.requestFocus(true);
    }else{
      cboStatus.showPopup();
    }
  }

  void txtArtNo_keyTyped(KeyEvent e) {
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtSupplier.requestFocus(true);
    }
  }

  void txtSearchLimit_focusGained(FocusEvent e) {
    txtSearchLimit.selectAll();
  }

  void txtItemCode_focusLost(FocusEvent e) {
    txtItemCode.select(0,0);
  }

  void txtItemDesc_focusLost(FocusEvent e) {
    txtItemDesc.select(0,0);
  }

  void txtArtNo_focusLost(FocusEvent e) {
    txtArtNo.select(0,0);
  }

  void txtSupplier_focusLost(FocusEvent e) {
    txtSupplier.select(0,0);
  }

  void txtSupplieName_focusLost(FocusEvent e) {
    txtSupplieName.select(0,0);
  }

  void txtSupplieName_focusGained(FocusEvent e) {
    txtSupplieName.selectAll();
  }

  void txtSearchLimit_focusLost(FocusEvent e) {
    txtSupplieName.select(0,0);
  }

  void tbllItemLookup_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnSearch.doClick();
               }
  }
}

class PanelItemLookup_btnReset_actionAdapter implements java.awt.event.ActionListener {
  PanelItemLookup adaptee;

  PanelItemLookup_btnReset_actionAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnReset_actionPerformed(e);
  }
}

class PanelItemLookup_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelItemLookup adaptee;

  PanelItemLookup_btnDone_actionAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelItemLookup_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelItemLookup adaptee;

  PanelItemLookup_btnSearch_actionAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}


class PanelItemLookup_tbllItemLookup_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_tbllItemLookup_mouseAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.tbllItemLookup_mouseClicked(e);
  }
}

class PanelItemLookup_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelItemLookup adaptee;

  PanelItemLookup_btnCancel_actionAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelItemLookup_txtItemCode_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemLookup adaptee;

    PanelItemLookup_txtItemCode_keyAdapter(PanelItemLookup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtItemCode_keyTyped(e);
    }
}

class PanelItemLookup_txtItemDesc_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemLookup adaptee;

    PanelItemLookup_txtItemDesc_keyAdapter(PanelItemLookup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtItemDesc_keyTyped(e);
    }
}

class PanelItemLookup_txtSupplier_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemLookup adaptee;

    PanelItemLookup_txtSupplier_keyAdapter(PanelItemLookup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtSupplier_keyTyped(e);
    }
}

class PanelItemLookup_txtSupplieName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemLookup adaptee;

    PanelItemLookup_txtSupplieName_keyAdapter(PanelItemLookup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtSupplieName_keyTyped(e);
    }
}

class PanelItemLookup_txtSearchLimit_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemLookup adaptee;

    PanelItemLookup_txtSearchLimit_keyAdapter(PanelItemLookup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtSearchLimit_keyTyped(e);
    }
}

class PanelItemLookup_txtItemCode_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_txtItemCode_focusAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtItemCode_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemCode_focusLost(e);
  }
}

class PanelItemLookup_txtItemDesc_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_txtItemDesc_focusAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtItemDesc_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemDesc_focusLost(e);
  }
}

class PanelItemLookup_txtArtNo_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_txtArtNo_focusAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtArtNo_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtArtNo_focusLost(e);
  }
}

class PanelItemLookup_txtSupplier_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_txtSupplier_focusAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtSupplier_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtSupplier_focusLost(e);
  }
}


class PanelItemLookup_jButton1_actionAdapter implements java.awt.event.ActionListener {
  PanelItemLookup adaptee;

  PanelItemLookup_jButton1_actionAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class PanelItemLookup_cboStatus_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_cboStatus_keyAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboStatus_keyTyped(e);
  }
}

class PanelItemLookup_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_txtArtNo_keyAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelItemLookup_txtSearchLimit_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_txtSearchLimit_focusAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtSearchLimit_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtSearchLimit_focusLost(e);
  }
}

class PanelItemLookup_txtSupplieName_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_txtSupplieName_focusAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtSupplieName_focusLost(e);
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtSupplieName_focusGained(e);
  }
}

class PanelItemLookup_tbllItemLookup_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemLookup adaptee;

  PanelItemLookup_tbllItemLookup_keyAdapter(PanelItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tbllItemLookup_keyPressed(e);
  }
}

