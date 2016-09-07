package sim;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Statement;
import javax.swing.tree.*;
import javax.swing.event.*;
import btm.attr.*;
import java.util.*;
import btm.business.*;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Lookup -> Item Lookup</p>
 * <p>Description: Main -> Merch Mgmt -> Item Setup -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DialogItemLookup extends JDialog {
  //Variable
  public int FLAG_SCR=0;
  public boolean FLAG_DONE=false;
  public int FLAG_AMOUNT;
  public Vector vItemID=new Vector();
  //Variable
  FrameMainSim frmMain;
  Statement stmt = null;
  DataAccessLayer DAL;
  Utils ut=new Utils();
  BJCheckBox check = new BJCheckBox();
  String idNode="";//id of prod hier node
  String itemId="";//id of item

  int parentScreen=0;

  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel pnlButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel pnlMain = new BJPanel();
  BJButton btnReset = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnDone = new BJButton();
  BJButton btnDelete = new BJButton();
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
  boolean done = false;
  JTextField txtCreatedDate = new JTextField();
  BJLabel lblSupplierName1 = new BJLabel();
  JButton jButton1 = new JButton();
  ItemBusObject itemBusObject;
  BJLabel lblSupplierName2 = new BJLabel();
  JComboBox cboStatus = new JComboBox();
  JToggleButton btnSelectAll = new JToggleButton();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international
  public DialogItemLookup(Frame frame, String title, boolean modal, FrameMainSim frmMain,ItemBusObject itemBusObject) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      this.itemBusObject = itemBusObject;
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogItemLookup() {
    this(null, "", false,null,null);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    this.getContentPane().setLayout(borderLayout1);
    this.setSize(new Dimension(800,600));
    this.addWindowListener(new DialogItemLookup_this_windowAdapter(this));
    btnReset.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnReset.setToolTipText(lang.getString("Clear")+" (F7)");
    btnReset.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</" +
    "html>");
    btnReset.addActionListener(new DialogItemLookup_btnReset_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText(lang.getString("Search")+"(F3)");
    btnSearch.addActionListener(new DialogItemLookup_btnSearch_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8<" +
    "/html>");
    btnDelete.addActionListener(new DialogItemLookup_btnDelete_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText(lang.getString("Done")+"(F1)");
    btnDone.addActionListener(new DialogItemLookup_btnDone_actionAdapter(this));
    pnlButton.setLayout(flowLayout1);
    pnlMain.setLayout(borderLayout2);
    pnlCriteria.setMinimumSize(new Dimension(10, 10));
    pnlCriteria.setPreferredSize(new Dimension(10, 265));
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
    lblSupplierCode.setBounds(new Rectangle(16, 90, 115, 15));
    lblSupplierName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSupplierName.setText(lang.getString("SupplierName")+":");
    lblSupplierName.setBounds(new Rectangle(16, 118, 115, 15));
    lblSearchLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSearchLimit.setText(lang.getString("SearchLimit")+":");
    lblSearchLimit.setBounds(new Rectangle(16, 203, 115, 15));
    txtItemCode.setText("");
    txtItemCode.setBounds(new Rectangle(136, 8, 183, 21));
    txtItemCode.addKeyListener(new DialogItemLookup_txtItemCode_keyAdapter(this));
    txtItemCode.addFocusListener(new DialogItemLookup_txtItemCode_focusAdapter(this));
    txtItemDesc.setSelectionStart(11);
    txtItemDesc.setText("");
    txtItemDesc.setBounds(new Rectangle(136, 35, 183, 21));
    txtItemDesc.addKeyListener(new DialogItemLookup_txtItemDesc_keyAdapter(this));
    txtItemDesc.addFocusListener(new DialogItemLookup_txtItemDesc_focusAdapter(this));
    txtSupplier.setText("");
    txtSupplier.setBounds(new Rectangle(136, 90, 183, 21));
    txtSupplier.addKeyListener(new DialogItemLookup_txtSupplier_keyAdapter(this));
    txtSupplier.addFocusListener(new DialogItemLookup_txtSupplier_focusAdapter(this));
    txtSupplieName.setText("");
    txtSupplieName.setBounds(new Rectangle(136, 118, 183, 21));
    txtSupplieName.addKeyListener(new DialogItemLookup_txtSupplieName_keyAdapter(this));
    txtSupplieName.addFocusListener(new DialogItemLookup_txtSupplieName_focusAdapter(this));
    txtSearchLimit.setText(DAL.getSearchLimit());
    txtSearchLimit.setBounds(new Rectangle(136, 203, 32, 21));
    txtSearchLimit.addFocusListener(new DialogItemLookup_txtSearchLimit_focusAdapter(this));
    txtSearchLimit.addKeyListener(new DialogItemLookup_txtSearchLimit_keyAdapter(this));
    jPanel3.setPreferredSize(new Dimension(10, 20));
    jPanel4.setPreferredSize(new Dimension(10, 20));
    jScrollPane2.getViewport().setBackground(new Color(121, 152, 198));

    rootTree =new BNode( new BNodeDetail("1","1","1",Constant.root, Constant.root));
    pTreeModel = new BTreeModel(rootTree);
    treProdHier=new BJTree(pTreeModel);

    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new DialogItemLookup_btnCancel_actionAdapter(this));
    tbllItemLookup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtArtNo.setBounds(new Rectangle(136, 63, 183, 21));
    txtArtNo.addKeyListener(new DialogItemLookup_txtArtNo_keyAdapter(this));
    txtArtNo.addFocusListener(new DialogItemLookup_txtArtNo_focusAdapter(this));
    txtArtNo.setText("");
    lblSupplierCode1.setBounds(new Rectangle(16, 61, 115, 15));
    lblSupplierCode1.setText(lang.getString("UPC")+":");
    lblSupplierCode1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    tbllItemLookup.addMouseListener(new DialogItemLookup_tbllItemLookup_mouseAdapter(this));
    txtCreatedDate.setBounds(new Rectangle(136, 146, 139, 21));
    txtCreatedDate.setEditable(false);
    txtCreatedDate.setText("");
    lblSupplierName1.setBounds(new Rectangle(16, 146, 115, 15));
    lblSupplierName1.setText(lang.getString("CreatedDate")+":");
    lblSupplierName1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jButton1.setBounds(new Rectangle(278, 146, 43, 22));
    jButton1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jButton1.setText("...");
    jButton1.addKeyListener(new DialogItemLookup_jButton1_keyAdapter(this));
    jButton1.addActionListener(new DialogItemLookup_jButton1_actionAdapter(this));
    pnlResult.setPreferredSize(new Dimension(454, 340));
    lblSupplierName2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSupplierName2.setText(lang.getString("Status")+":");
    lblSupplierName2.setBounds(new Rectangle(16, 175, 115, 15));
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setBounds(new Rectangle(136, 175, 183, 21));
    cboStatus.addKeyListener(new DialogItemLookup_cboStatus_keyAdapter(this));
    cboStatus.addItem("None");
    cboStatus.addItem("Work Sheet");
    cboStatus.addItem("Approve");
    tbllItemLookup.addKeyListener(new DialogItemLookup_tbllItemLookup_keyAdapter(this));
    jScrollPane1.setFont(new java.awt.Font(lang.getString("FontName_Label"),1,11));
    jScrollPane1.setPreferredSize(new Dimension(454, 340));
    btnSelectAll.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    btnSelectAll.setText(lang.getString("SelectAll"));
    btnSelectAll.setBounds(new Rectangle(14, 233, 127, 26));
    btnSelectAll.addActionListener(new DialogItemLookup_btnSelectAll_actionAdapter(this));
    this.getContentPane().add(pnlButton,  BorderLayout.NORTH);
    this.getContentPane().add(pnlMain, BorderLayout.CENTER);
    pnlButton.add(btnDone, null);
    pnlButton.add(btnSearch, null);
    pnlButton.add(btnReset, null);
    pnlButton.add(btnDelete,null);
    pnlButton.add(btnCancel, null);
    pnlMain.add(pnlCriteria, BorderLayout.NORTH);
    pnlMain.add(pnlResult, BorderLayout.CENTER);
    pnlResult.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tbllItemLookup, null);
    pnlCriteria.add(jPanel1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 264, 199));
    jPanel1.add(txtItemCode, null);
    jPanel1.add(lblItemCode, null);
    jPanel1.add(txtItemDesc, null);
    jPanel1.add(lblItemDesc, null);
    jPanel1.add(txtArtNo, null);
    jPanel1.add(lblSupplierCode1, null);
    jPanel1.add(txtSupplier, null);
    jPanel1.add(lblSupplierCode, null);
    jPanel1.add(txtSupplieName, null);
    jPanel1.add(lblSupplierName, null);
    jPanel1.add(txtCreatedDate, null);
    jPanel1.add(lblSupplierName1, null);
    pnlCriteria.add(jPanel2,   new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
      ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 1), 90, 124));
    jPanel2.add(jScrollPane2,  BorderLayout.CENTER);
    jPanel2.add(jPanel3, BorderLayout.SOUTH);
    jPanel2.add(jPanel4, BorderLayout.NORTH);
    jScrollPane2.getViewport().add(treProdHier, null);
    jPanel1.add(jButton1, null);
    jPanel1.add(lblSearchLimit, null);
    jPanel1.add(lblSupplierName2, null);
    jPanel1.add(cboStatus, null);
    jPanel1.add(txtSearchLimit, null);
    jPanel1.add(btnSelectAll, null);
//    jScrollPane2.setVisible(false);
    tbllItemLookup.setAutoResizeMode(tbllItemLookup.AUTO_RESIZE_ALL_COLUMNS);    //show data
    tbllItemLookup.getTableHeader().setReorderingAllowed(false);
    tbllItemLookup.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    showData();
//    String[] columnNames = new String[]{"Item Code", "Item Name","Supplier Name","Parent ID","Parent Name"};
//    dm.setDataVector(new Object[][]{},columnNames);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);
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
    idNode="001";
    treProdHier.setVisible(true);
    treProdHier.setSelectionPathByNameNode(rootTree,idNode);
  }
  //Set editable textbox
  void setEditablTextField(boolean flag){
    txtItemCode.setEditable(flag);
    txtItemDesc.setEditable(flag);
    txtArtNo.setEditable(flag);
    txtSupplier.setEditable(flag);
    txtSupplieName.setEditable(flag);
    cboStatus.setEnabled(flag);
  }
  //Set enable treeview
  void btnReset_actionPerformed(ActionEvent e) {
    txtItemCode.setText("");
    txtItemDesc.setText("");
    txtArtNo.setText("");
    txtSupplier.setText("");
    txtSupplieName.setText("");
    txtSearchLimit.setText(DAL.getSearchLimit());
    txtCreatedDate.setText("");
    cboStatus.setSelectedIndex(0);
    setEditablTextField(true);
    while (tbllItemLookup.getRowCount()>0){
      dm.removeRow(0);
    }
    txtItemCode.requestFocus(true);
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    if (txtSearchLimit.getText().equalsIgnoreCase("")){
      txtSearchLimit.setText(DAL.getSearchLimit());
    }
    searchData();
  }
  void searchData(){
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "";
//    if (txtItemCode.isEditable()&& !treProdHier.isEnabled()){
      sql = "";
      sql = "select mas.item_id,mas.art_no,mas.item_name,itemsup.SUPP_NAME,pri.attr2_name, pro.child_name,pri.new_price "
          + "	from BTM_IM_ITEM_MASTER mas, (select mas.item_id,mas.child_id,hir.child_name "
          + "                            from BTM_IM_ITEM_MASTER mas, BTM_IM_PROD_HIR hir "
          + "                                     where mas.child_id = hir.child_id) pro , "
          + "		 (select distinct item_id,new_price,attr2_name  "
          + "		  from BTM_IM_PRICE_HIST "
          + "		  where status='1' and store_id = '"+DAL.getStoreID()+"') pri,  "
          + "		 (select distinct isup.item_id,isup.supp_id,sup.supp_name  "
          + "		  from BTM_IM_ITEM_LOC_SUPPLIER isup, BTM_IM_SUPPLIER sup "
          + "		  where isup.SUPP_ID=sup.SUPP_ID) itemsup "
          + "	where pri.ITEM_ID=mas.ITEM_ID and mas.item_id=itemsup.ITEM_ID and pro.item_id= mas.item_id ";
      if (!txtItemCode.getText().equalsIgnoreCase("")) {
        sql = sql + " and mas.item_id like '%" +
            ut.putCommaToString(txtItemCode.getText().trim()) + "%' ";
      }
      if (!txtItemDesc.getText().equalsIgnoreCase("")) {
        sql = sql + " and lower(mas.item_name) like lower('%" +
            ut.putCommaToString(txtItemDesc.getText().trim()) + "%') ";
      }
      if (!txtArtNo.getText().equalsIgnoreCase("")){
//        if (txtArtNo.getText().length() == 6){
          sql = sql + " and mas.art_no  like '%" + ut.putCommaToString(txtArtNo.getText()) + "%'" ;
//        }else{
//          sql = sql + " and lower(mas.art_no) like lower('%" +
//              txtArtNo.getText().trim() + "%') ";
//        }
      }
      if (!txtSupplier.getText().equalsIgnoreCase("")) {
//        if (txtSupplier.getText().length() == 6){
          sql = sql + " and itemsup.supp_id like '%" + ut.putCommaToString(txtSupplier.getText()) + "%'";
//        }else{
//          sql = sql + " and lower(itemsup.supp_id) like lower('%" +
//              txtSupplier.getText().trim() + "%') ";
//        }
      }
      if (!txtSupplieName.getText().equalsIgnoreCase("")) {
        sql = sql + " and lower(itemsup.supp_name) like lower('%" +
            ut.putCommaToString(txtSupplieName.getText().trim()) + "%') ";
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
      if (treProdHier.isLeaf()){
        sql = sql + " and pro.child_id='"+ treProdHier.getChildId() +"'";
      }
//    }
    if (!sql.equalsIgnoreCase("")){
      sql = "select * from (" + sql + " ) where  rownum <=" +
          Integer.parseInt(txtSearchLimit.getText());
      System.out.println(sql);
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = DAL.executeScrollQueries(sql,stmt);
      }catch(Exception ex){

      }
      dm.resetIndexes();
      tbllItemLookup.setData(rs,check,1);
      if (tbllItemLookup.getRowCount() > 0) {
        tbllItemLookup.setRowSelectionInterval(0, 0);
        treProdHier.setSelectionPathByNameNode(rootTree,tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(),6).toString());
      }
      tbllItemLookup.setColumnWidth(0,10);
      tbllItemLookup.setColumnWidth(2,70);
      tbllItemLookup.setColumnWidth(4,100);
      tbllItemLookup.setColumnWidth(7,50);
      tbllItemLookup.setHeaderName(lang.getString("ItemID"), 1);
      tbllItemLookup.setHeaderName(lang.getString("UPC"),2);
      tbllItemLookup.setHeaderName(lang.getString("ItemName"), 3);
      tbllItemLookup.setHeaderName(lang.getString("SupplierName"), 4);
      tbllItemLookup.setHeaderName(lang.getString("Size"), 5);
      tbllItemLookup.setHeaderName(lang.getString("SubCategory"), 6);
      tbllItemLookup.setHeaderName(lang.getString("Price"),7);
      try{
        rs.close();
        stmt.close();
      }catch(Exception exx){
        exx.printStackTrace();
      }
    }
    txtItemCode.requestFocus(true);
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

  void txtSupplieName_focusGained(FocusEvent e) {
    setEditablTextField(true);
    txtSupplieName.selectAll();
  }
  String getItemID(){
    String result = "";
    result = tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(),1).toString();
    return result;
  }
  String getUPC(){
    String result = "";
    result = tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(),2).toString();
    return result;
  }
  Vector getUPCList(){
    Vector vUPC = new Vector();
    for(int i=0;i<tbllItemLookup.getRowCount();i++){
      BJCheckBox check = (BJCheckBox) tbllItemLookup.getValueAt(i, 0);
      if (check.isSelected()) {
           vUPC.addElement(tbllItemLookup.getValueAt(i,2));
      }
    }
    if (vUPC.isEmpty()){
      vUPC.addElement(tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(),2));
    }
    return vUPC;
  }
  String getUnitPrice(){
    String result = "";
    result = tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(),7).toString();
    return result;
  }
  String getItemName(){
    String result = "";
    result = tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(),3).toString();
    return result;
  }
  //get name of sub category
  String getSubCategoryName(){
    String result = "";
    String subCategory = treProdHier.getChildId();

    ResultSet rs = null;
    try{
      String sql = "select child_name from btm_im_prod_hir where child_id='" + subCategory + "'";
      rs = DAL.executeQueries(sql);
      if (rs.next()){
        result = rs.getString("child_name");
      }
      rs.getStatement().close();

    }catch(Exception e){
      e.printStackTrace();
    }

    return result;
  }
  String getProductGroup(){
    String result="";
    String prodGroup = treProdHier.getParentId(treProdHier.getParentId(treProdHier.getParentId()));
    result = prodGroup;
    return result;
  }
  void tbllItemLookup_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 1){
      treProdHier.setSelectionPathByNameNode(rootTree,tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(),6).toString());
    }
    if (e.getClickCount() == 2){
      if (FLAG_SCR==0){
        done = true;
        setData();
        this.dispose();
      }else{
        FLAG_DONE = true;
        vItemID.addElement(tbllItemLookup.getValueAt(tbllItemLookup.getSelectedRow(),1).toString());
        this.dispose();
      }
    }
  }
  void getUPCCheck(){

  }
  void btnDone_actionPerformed(ActionEvent e) {
    if (FLAG_SCR==0){
      for (int j = 0; j < tbllItemLookup.getRowCount(); j++) {
        BJCheckBox check = (BJCheckBox) tbllItemLookup.getValueAt(j, 0);
        if (check.isSelected()) {
          done = true;
          setData();
          this.dispose();
        }
      }

    }else{
      FLAG_DONE=true;
      for (int j = 0; j < tbllItemLookup.getRowCount(); j++) {
       //Get Item ID
        BJCheckBox check = (BJCheckBox) tbllItemLookup.getValueAt(j, 0);
        if (check.isSelected()) {
          vItemID.addElement(check.getValue());
        }
      }
      this.dispose();
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    if (FLAG_SCR==0){
      done = false;
      this.dispose();
    }else{
      this.dispose();
    }
  }

  private void registerKeyboardActions() {
                  /// set up the maps:
                  InputMap inputMap = new InputMap();
                  inputMap.setParent(pnlButton.getInputMap(JComponent.
                                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
                  ActionMap actionMap = pnlButton.getActionMap();

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

                 pnlButton.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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
                    else if (identifier.intValue() == KeyEvent.VK_F3) {
                      btnSearch.doClick();
                    }
                    else if (identifier.intValue() == KeyEvent.VK_F8){
                      btnDelete.doClick();
                    }
                    else if (identifier.intValue() == KeyEvent.VK_F7) {
                      btnReset.doClick();
                    }
                    else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                      btnCancel.doClick();
                          }
                  }
          }



  void btnDelete_actionPerformed(ActionEvent e) {
    if (tbllItemLookup.getRowCount()>0){
      int i = 1;
      if (checkTable(tbllItemLookup)) {
        i = ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1009_Sure"),
                           DialogMessage.QUESTION_MESSAGE,
                           DialogMessage.YES_NO_OPTION);
      }
      if (i == DialogMessage.YES_VALUE) {
        String mess = "";
        for (int j = 0; j < tbllItemLookup.getRowCount(); j++) {
          //Xoa du lieu trong Database
          BJCheckBox check = (BJCheckBox) tbllItemLookup.getValueAt(j, 0);
          if (check.isSelected()) {
            if (checkExist(check.getValue())) {

//              Statement stmt = null;
//          System.out.println("Delete from btm_pos_customer where cust_id ='" + check.getValue() + "'");
              try {
                stmt = DAL.getConnection().createStatement();
                Vector vSql = new Vector();
                String sql = "delete from btm_im_price_hist where item_id ='" +
                    check.getValue() + "'";
//                System.out.println(sql);
                vSql.addElement(sql);
                sql="";
                sql = "delete from btm_im_unit_cost_hist where item_id ='" +
                    check.getValue() + "'";
//                System.out.println(sql);
                vSql.addElement(sql);
                sql = "";
                sql =  "delete from btm_im_item_loc_supplier where item_id ='" +
                    check.getValue() + "'";
//                System.out.println(sql);
                vSql.addElement(sql);
                sql = "";
                sql = "delete from btm_im_item_season where item_id ='" +
                    check.getValue() + "'";
//                System.out.println(sql);
                vSql.addElement(sql);
                sql = "";
                sql = "delete from btm_im_item_loc_soh where item_id = '" + check.getValue() + "'";
//                System.out.println(sql);
                vSql.addElement(sql);
                sql = "";
                sql = "delete from btm_im_item_master where item_id ='" +
                    check.getValue() + "'";
//                System.out.println(sql);
                vSql.addElement(sql);
                DAL.setBeginTrans(DAL.getConnection());
                DAL.executeBatchQuery(vSql,stmt);
                DAL.setEndTrans(DAL.getConnection());

                stmt.close();
              }
              catch (Exception ex) {
                ex.printStackTrace();
              }

            }else {
              if (mess.equalsIgnoreCase("")){
                mess = "Items : " + check.getValue();
              }else{
                mess = mess + "; " + check.getValue();
              }
            }
          }
        }
        if (!mess.equalsIgnoreCase("")){
          ut.showMessage(frmMain,lang.getString("TT007"), mess + lang.getString("MS1010_CanNotDelete"));
        }
      }
      searchData();
    }
  }
  void setData(){
    itemBusObject.setData(txtItemCode.getText(),txtItemDesc.getText(),txtArtNo.getText(),
                          txtSupplier.getText(),txtSupplieName.getText(),txtCreatedDate.getText(), cboStatus.getSelectedIndex(), txtSearchLimit.getText());
  }
  void getData(String itemID, String itemName, String artNo, String supplierID,
               String supplierName, String createdDate, int status, String searchLimit){
    if (itemID.equalsIgnoreCase("") && itemName.equalsIgnoreCase("") && artNo.equalsIgnoreCase("")
        && supplierID.equalsIgnoreCase("") && supplierName.equalsIgnoreCase("")
        && createdDate.equalsIgnoreCase("") && searchLimit.equalsIgnoreCase("")){
      cboStatus.setSelectedIndex(status);
      txtSearchLimit.setText(DAL.getSearchLimit());
      return;
    }else{
      txtItemCode.setText(itemID);
      txtItemDesc.setText(itemName);
      txtArtNo.setText(artNo);
      txtSupplier.setText(supplierID);
      txtSupplieName.setText(supplierName);
      txtCreatedDate.setText(createdDate);
      cboStatus.setSelectedIndex(status);
      txtSearchLimit.setText(searchLimit);
      searchData();
    }

  }
  boolean checkTable(BJTable table1){
    for (int i = 0; i<tbllItemLookup.getRowCount(); i++){
      BJCheckBox check = (BJCheckBox) table1.getValueAt(i,0);
      if (check.isSelected()){
        return true;
      }
    }
    return false;
  }

  boolean checkExist(String itemID){

    Statement stmt = null;
    ResultSet rs = null;
    try{
      stmt = DAL.getConnection().createStatement();
      String sql = "select item_id from BTM_IM_ITEM_LOC_SOH where item_id ='" + itemID + "'";
//      System.out.println(sql);
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        stmt.close();
        return false;
      }
      stmt.close();

    }catch(Exception e){
      e.printStackTrace();
    }
    return true;
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

  void txtArtNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtArtNo,ItemMaster.LEN_ITEM_ID);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtSupplier.requestFocus(true);
    }
  }
  void catchKeyCode(KeyEvent e){
    if (e.getKeyCode() == KeyEvent.VK_F3){
      btnSearch.doClick();
    }else if(e.getKeyCode() == KeyEvent.VK_F1){
      btnDone.doClick();
    }else if(e.getKeyCode() == KeyEvent.VK_F8){
      btnDelete.doClick();
    }else if(e.getKeyCode() == KeyEvent.VK_F7){
      btnReset.doClick();
    }else if(e.getKeyCode() == KeyEvent.VK_F12 || e.getKeyCode() == KeyEvent.VK_ESCAPE){
      btnCancel.doClick();
    }

  }
  void txtArtNo_keyPressed(KeyEvent e) {
//System.out.println("Press: " + e.getKeyCode());
    catchKeyCode(e);
  }

  void txtItemCode_keyPressed(KeyEvent e) {
    catchKeyCode(e);
  }

  void txtItemDesc_keyPressed(KeyEvent e) {
    catchKeyCode(e);
  }
  void txtSupplier_keyPressed(KeyEvent e) {
    catchKeyCode(e);
  }
  void txtSupplieName_keyPressed(KeyEvent e) {
    catchKeyCode(e);
  }
  void jButton1_keyPressed(KeyEvent e) {
    catchKeyCode(e);
  }
  void cboStatus_keyPressed(KeyEvent e) {
    catchKeyCode(e);
  }
  void txtSearchLimit_keyPressed(KeyEvent e) {
    catchKeyCode(e);
  }
  void tbllItemLookup_keyPressed(KeyEvent e) {
    catchKeyCode(e);
  }
  void txtItemCode_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtItemCode,ItemMaster.LEN_ITEM_ID);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtItemDesc.requestFocus(true);
    }
  }

  void txtItemDesc_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtArtNo.requestFocus(true);
    }
  }

  void txtSupplier_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtSupplieName.requestFocus(true);
    }
  }

  void txtSupplieName_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      jButton1.requestFocus(true);
    }
  }

  void jButton1_keyTyped(KeyEvent e) {
//    if (e.getKeyChar() == KeyEvent.VK_ENTER){
//      cboStatus.requestFocus(true);
//    }
  }

  void cboStatus_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtSearchLimit.requestFocus(true);
    }
  }

  void this_windowOpened(WindowEvent e) {
    txtArtNo.requestFocus(true);
  }

  void txtSearchLimit_focusGained(FocusEvent e) {
    txtSearchLimit.selectAll();
  }

  void txtSearchLimit_focusLost(FocusEvent e) {
    txtSearchLimit.select(0,0);
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

  void txtSearchLimit_keyTyped(KeyEvent e) {
    ut.checkNumber(e);
  }

  void btnSelectAll_actionPerformed(ActionEvent e) {
    int rows = tbllItemLookup.getRowCount();
    if(rows>0){
      for (int i = 0; i < rows; i++) {
        JCheckBox chkTemp = (BJCheckBox) tbllItemLookup.getValueAt(i, 0);
        chkTemp.setSelected(btnSelectAll.isSelected());
        tbllItemLookup.setValueAt(chkTemp, i, 0);
      }
      if(btnSelectAll.isSelected()){
        btnSelectAll.setLabel(lang.getString("ClearAll"));
      }else{
        btnSelectAll.setLabel(lang.getString("SelectAll"));
      }
    }else{
      btnSelectAll.setSelected(false);
    }
  }
}

class DialogItemLookup_btnReset_actionAdapter implements java.awt.event.ActionListener {
  DialogItemLookup adaptee;

  DialogItemLookup_btnReset_actionAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnReset_actionPerformed(e);
  }
}

class DialogItemLookup_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  DialogItemLookup adaptee;

  DialogItemLookup_btnSearch_actionAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}


class DialogItemLookup_txtItemCode_focusAdapter extends java.awt.event.FocusAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtItemCode_focusAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtItemCode_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemCode_focusLost(e);
  }
}

class DialogItemLookup_txtItemDesc_focusAdapter extends java.awt.event.FocusAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtItemDesc_focusAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtItemDesc_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemDesc_focusLost(e);
  }
}

class DialogItemLookup_txtArtNo_focusAdapter extends java.awt.event.FocusAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtArtNo_focusAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtArtNo_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtArtNo_focusLost(e);
  }
}

class DialogItemLookup_txtSupplier_focusAdapter extends java.awt.event.FocusAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtSupplier_focusAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtSupplier_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtSupplier_focusLost(e);
  }
}

class DialogItemLookup_txtSupplieName_focusAdapter extends java.awt.event.FocusAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtSupplieName_focusAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtSupplieName_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtSupplieName_focusLost(e);
  }
}

class DialogItemLookup_tbllItemLookup_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_tbllItemLookup_mouseAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.tbllItemLookup_mouseClicked(e);
  }
}

class DialogItemLookup_btnDone_actionAdapter implements java.awt.event.ActionListener {
  DialogItemLookup adaptee;

  DialogItemLookup_btnDone_actionAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class DialogItemLookup_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogItemLookup adaptee;

  DialogItemLookup_btnCancel_actionAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogItemLookup_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  DialogItemLookup adaptee;

  DialogItemLookup_btnDelete_actionAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class DialogItemLookup_jButton1_actionAdapter implements java.awt.event.ActionListener {
  DialogItemLookup adaptee;

  DialogItemLookup_jButton1_actionAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class DialogItemLookup_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtArtNo_keyAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtArtNo_keyPressed(e);
  }
}

class DialogItemLookup_txtItemCode_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtItemCode_keyAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtItemCode_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemCode_keyTyped(e);
  }
}

class DialogItemLookup_txtItemDesc_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtItemDesc_keyAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtItemDesc_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemDesc_keyTyped(e);
  }
}

class DialogItemLookup_txtSupplier_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtSupplier_keyAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtSupplier_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSupplier_keyTyped(e);
  }
}

class DialogItemLookup_txtSupplieName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtSupplieName_keyAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtSupplieName_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSupplieName_keyTyped(e);
  }
}

class DialogItemLookup_jButton1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_jButton1_keyAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jButton1_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jButton1_keyTyped(e);
  }
}

class DialogItemLookup_cboStatus_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_cboStatus_keyAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboStatus_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboStatus_keyTyped(e);
  }
}

class DialogItemLookup_txtSearchLimit_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtSearchLimit_keyAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtSearchLimit_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSearchLimit_keyTyped(e);
  }
}

class DialogItemLookup_tbllItemLookup_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_tbllItemLookup_keyAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tbllItemLookup_keyPressed(e);
  }
}

class DialogItemLookup_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_this_windowAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DialogItemLookup_txtSearchLimit_focusAdapter extends java.awt.event.FocusAdapter {
  DialogItemLookup adaptee;

  DialogItemLookup_txtSearchLimit_focusAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtSearchLimit_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtSearchLimit_focusLost(e);
  }
}

class DialogItemLookup_btnSelectAll_actionAdapter implements java.awt.event.ActionListener {
  DialogItemLookup adaptee;

  DialogItemLookup_btnSelectAll_actionAdapter(DialogItemLookup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSelectAll_actionPerformed(e);
  }
}

