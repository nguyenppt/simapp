package sim;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.util.*;
import btm.attr.*;
import btm.business.*;
import javax.swing.border.*;
import java.awt.Dimension;

/**
 * <p>Title: Product Group Details</p>
 * <p>Description: List Item detail of Product Group </p>
 * <p>Description: Main -> Inv Mgmt -> Product Group -> Product Group Detail</p>
 * <p>Copyright: Copyright (c) 2006  </p>
 * <p>Company: BTM </p>
 * @author Vinh Le
 * @version 1.0
 */
public class PanelProductGroupDetails
    extends JPanel {
  public static final String STATUS_ACTIVE   = "Active";
  public static final String STATUS_CANCEL   = "Cancel";
  public static final String TYPE_PICK_LIST  = "Pick List";
  public static final String TYPE_PO         = "PO";
  public static final String TYPE_STOCK_COUNT = "Stock Count";

  BorderLayout borderLayout1 = new BorderLayout();
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  Statement stmt = null;
  DataAccessLayer DAL = new DataAccessLayer();
  Utils ut = new Utils();
  Vector vSql = new Vector();
  Vector vInfo = new Vector();

  //=============Define Constant for Table Column
  public static final int CHECK = 0;
  public static final int ITEM_ID = 1;
  public static final int UPC = 2;
  public static final int ITEM_NAME = 3;
  public static final int CHILD_NAME = 4;

  //=============Define Constant for Screens call this screen
  public static final int PO_SETUP  = 0;
  public static final int PO_MODIFY = 1;
  //for Pick List
  public static final int PL_SETUP  = 2;
  public static final int PL_MODIFY = 3;
  //for Stock Coutn BR
  public static final int STOCK_COUNT_BR  = 4;
  //for Stock Coutn FR
  public static final int STOCK_COUNT_FR  = 5;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  //===public variable: current screen call this screen
  public int SCREEN_FLAG = 0; //default is PO_SETUP screen.
  JPanel pnlHeader = new JPanel();
  BJButton btnCancel = new BJButton();
  BJButton btnDone = new BJButton();
  JPanel pnlCenter = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlTable = new JPanel();
  JPanel pnlInfo = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel pnlLeft = new JPanel();
  JPanel pnlRight = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel pnlLeftLabel = new JPanel();
  JPanel pnlLeftInfo = new JPanel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JTextField txtCreatedDate = new JTextField();
  JTextField txtPGID = new JTextField();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel pnlRightLabel = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JPanel pnlRightInfo = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  JPanel pnlDesc = new JPanel();
  JLabel jLabel1 = new JLabel();
  JScrollPane scrPanelTable = new JScrollPane();
  SortableTableModel dmProdGroup = new SortableTableModel();
  BJTable tblProdGroup = new BJTable(dmProdGroup, true);
  FlowLayout flowLayout3 = new FlowLayout();
  JPanel jPanel1 = new JPanel();
  String pgID = "";
  FlowLayout flowLayout4 = new FlowLayout();
  JTextArea txtDesc = new JTextArea();
  JComboBox cboStatus = new JComboBox();
  BJComboBox cboType = new BJComboBox();
  BJComboBox cboSupplier = new BJComboBox();
  JButton btnSupplier = new JButton();
  FlowLayout flowLayout5 = new FlowLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  BJCheckBox check = new BJCheckBox();
  JToggleButton btnSelectAll = new JToggleButton();
  public PanelProductGroupDetails() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public PanelProductGroupDetails(FrameMainSim frmMain, CardLayout crdLayout,
                                  JPanel parent) {
    try {
      this.frmMain = frmMain;
      this.cardLayout = crdLayout;
      this.parent = parent;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void initScreen() {
    pgID = ut.getDateTimeID();
    txtPGID.setText(pgID);
    initInfoForCombo();
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setLayout(borderLayout1);
    pnlHeader.setBackground(new Color(121, 152, 198));
    pnlHeader.setPreferredSize(new Dimension(102, 45));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+"(F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
        "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new
        PanelProductGroupDetails_btnCancel_actionAdapter(this));
        btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new
                              PanelProductGroupDetails_btnDone_actionAdapter(this));
    pnlCenter.setLayout(borderLayout2);
    pnlTable.setPreferredSize(new Dimension(10, 100));
    pnlTable.setLayout(borderLayout4);
    pnlInfo.setPreferredSize(new Dimension(10, 165));
    pnlInfo.setLayout(borderLayout3);
    pnlLeft.setPreferredSize(new Dimension(400, 10));
    pnlLeft.setLayout(borderLayout5);
    pnlRight.setPreferredSize(new Dimension(500, 10));
    pnlRight.setLayout(borderLayout6);
    pnlLeftLabel.setPreferredSize(new Dimension(140, 10));
    pnlLeftLabel.setLayout(flowLayout5);
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel3.setPreferredSize(new Dimension(90, 22));
    jLabel3.setText(lang.getString("CreatedDate")+":");
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel5.setMaximumSize(new Dimension(66, 23));
    jLabel5.setPreferredSize(new Dimension(90, 22));
    jLabel5.setText(lang.getString("PG_ID")+":");
    pnlLeftInfo.setPreferredSize(new Dimension(250, 10));
    pnlLeftInfo.setLayout(flowLayout1);
    txtCreatedDate.setBackground(Color.white);
    txtCreatedDate.setEnabled(true);
    txtCreatedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtCreatedDate.setPreferredSize(new Dimension(175, 22));
    txtCreatedDate.setEditable(false);
    txtPGID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPGID.setPreferredSize(new Dimension(175, 22));
    txtPGID.setDisabledTextColor(SystemColor.info);
    txtPGID.setEditable(false);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    pnlRightLabel.setPreferredSize(new Dimension(150, 10));
    pnlRightLabel.setLayout(flowLayout2);
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel6.setOpaque(false);
    jLabel6.setPreferredSize(new Dimension(80, 22));
    jLabel6.setRequestFocusEnabled(true);
    jLabel6.setText(lang.getString("Status")+":");
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel7.setPreferredSize(new Dimension(80, 22));
    jLabel7.setText(lang.getString("Type")+":");
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel8.setMaximumSize(new Dimension(65, 16));
    jLabel8.setMinimumSize(new Dimension(65, 16));
    jLabel8.setPreferredSize(new Dimension(90, 22));
    jLabel8.setText(lang.getString("SupplierName")+":");
    pnlRightInfo.setPreferredSize(new Dimension(100, 10));
    pnlRightInfo.setLayout(flowLayout4);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel1.setPreferredSize(new Dimension(135, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel1.setText("              "+lang.getString("Description")+":");
    pnlDesc.setLayout(flowLayout3);
    jPanel1.setPreferredSize(new Dimension(50, 10));
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    showButton();
    tblProdGroup.addMouseListener(new
    PanelProductGroupDetails_tblProdGroup_mouseAdapter(this));
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDesc.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDesc.setMinimumSize(new Dimension(635, 15));
    txtDesc.setPreferredSize(new Dimension(588, 38));
    txtDesc.setEditable(false);
    txtDesc.setLineWrap(true);
    txtDesc.setWrapStyleWord(true);
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setPreferredSize(new Dimension(176, 22));
    cboStatus.setPopupVisible(false);
    cboStatus.addItemListener(new PanelProductGroupDetails_cboStatus_itemAdapter(this));
    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboType.setPreferredSize(new Dimension(176, 22));
    cboSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSupplier.setPreferredSize(new Dimension(140, 22));
    btnSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSupplier.setPreferredSize(new Dimension(30, 25));
    btnSupplier.setText("...");
    btnSupplier.addActionListener(new
    PanelProductGroupDetails_btnSupplier_actionAdapter(this));
    flowLayout5.setAlignment(FlowLayout.RIGHT);
    pnlDesc.setPreferredSize(new Dimension(742, 80));
    flowLayout3.setAlignment(FlowLayout.LEFT);
    btnSelectAll.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSelectAll.setPreferredSize(new Dimension(90, 25));
    btnSelectAll.setText(lang.getString("SelectAll"));
    btnSelectAll.addActionListener(new PanelProductGroupDetails_btnSelectAll_actionAdapter(this));
    pnlRightInfo.add(cboType, null);
    pnlRightInfo.add(cboStatus, null);
    this.add(pnlCenter, java.awt.BorderLayout.CENTER);
    pnlCenter.add(pnlTable, java.awt.BorderLayout.CENTER);
    pnlCenter.add(pnlInfo, java.awt.BorderLayout.NORTH);
    pnlInfo.add(pnlLeft, java.awt.BorderLayout.WEST);
    pnlLeftLabel.add(jLabel5);
    pnlLeftLabel.add(jLabel3);
    pnlLeftLabel.add(jLabel8);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightLabel, java.awt.BorderLayout.WEST);
    pnlRightLabel.add(jLabel7);
    pnlRightLabel.add(jLabel6);
    pnlLeftInfo.add(txtPGID);
    pnlLeftInfo.add(txtCreatedDate);
    pnlLeftInfo.add(cboSupplier);
    pnlLeftInfo.add(btnSupplier);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightInfo, java.awt.BorderLayout.CENTER);
    pnlLeft.add(pnlLeftInfo, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlRight, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlDesc, java.awt.BorderLayout.SOUTH);
    pnlDesc.add(jLabel1);
    pnlDesc.add(txtDesc);
    pnlDesc.add(btnSelectAll, null);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.NORTH);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.CENTER);
    pnlRight.add(jPanel1, java.awt.BorderLayout.EAST);
    scrPanelTable.getViewport().add(tblProdGroup, null);
    this.add(pnlHeader, java.awt.BorderLayout.NORTH);
    registerKeyboardActions();
    initInfoForCombo();
  }

  void setDataDetail(String pgID) {
    initInfoForCombo();
    setDataToHeader(pgID);
    setDataToGrid(pgID);
    cboSupplier.setEnabled(false);
    btnSupplier.setEnabled(false);
    cboType.setEnabled(false);
    showButton();
  }

  void setDataToHeader(String pgID) {
    ResultSet rs = null;
    String sqlStr = "Select PROD_GROUP_ID,DESCRIPTION,TYPE,STATUS, to_char(CREATE_DATE,'DD/MM/YYYY') CREATE_DATE,  Supp_Id "
        + " From BTM_IM_PROD_GROUP Where PROD_GROUP_ID='" + pgID + "'";
    try {
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr, stmt);
      while (rs.next()) {
        txtPGID.setText(pgID);
        txtCreatedDate.setText(rs.getString("CREATE_DATE"));
        cboSupplier.setSelectedData(rs.getString("SUPP_ID"));
        String type = rs.getString("TYPE");
        if (type.equalsIgnoreCase("P")) {
          cboType.setSelectedItem(TYPE_PICK_LIST);
        }
        else if (type.equalsIgnoreCase("O")) {
          cboType.setSelectedItem(TYPE_PO);
        }
        else if (type.equalsIgnoreCase("S")) {
          cboType.setSelectedItem(TYPE_STOCK_COUNT);
        }

        String status = rs.getString("STATUS");
        if (status.equalsIgnoreCase("A")) {
          cboStatus.setSelectedItem(STATUS_ACTIVE);
        }
        else if (status.equalsIgnoreCase("C")) {
          cboStatus.setSelectedItem(STATUS_CANCEL);
        }
        txtDesc.setText(rs.getString("DESCRIPTION"));
      }
      stmt.close();
      rs.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void setDataToGrid(String pgID) {
    ResultSet rs = null;
    String itemID, itemName, upc, childName, pSize, qty, unitCost;
    String sqlStr = " select mas.item_id,mas.art_no UPC,mas.item_name, pro.child_name 	"
    +  "  from BTM_IM_ITEM_MASTER mas, "
    +  "   (select mas.item_id,mas.child_id,hir.child_name  from BTM_IM_ITEM_MASTER mas, BTM_IM_PROD_HIR hir where mas.child_id = hir.child_id) pro , 		"
    +  "    (select distinct isup.item_id,isup.supp_id,sup.supp_name from BTM_IM_ITEM_LOC_SUPPLIER isup,BTM_IM_SUPPLIER sup where isup.SUPP_ID=sup.SUPP_ID) itemsup,"
    +  "    (select item_id,seq from BTM_IM_PROD_GROUP_ITEM where PROD_GROUP_ID ='" + pgID + "' ) gro 	"
    +  "  where  mas.item_id=itemsup.ITEM_ID and mas.item_id = pro.item_id and mas.item_id=gro.item_id order by gro.seq";
    try {
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr, stmt);

      //clear data on grid
      while (dmProdGroup.getRowCount() > 0) {
        dmProdGroup.removeRow(0);
      }
      tblProdGroup.setData(rs,check,1);
      tblProdGroup.setColumnWidth(CHECK, 10);
      tblProdGroup.setColumnWidth(UPC, 110);
      tblProdGroup.setColumnWidth(ITEM_NAME, 200);
      tblProdGroup.setColumnWidth(CHILD_NAME, 100);
      tblProdGroup.setHeaderName(lang.getString("ItemID"), ITEM_ID);
      tblProdGroup.setHeaderName(lang.getString("UPC"),UPC);
      tblProdGroup.setHeaderName(lang.getString("ItemName"), ITEM_NAME);
      tblProdGroup.setHeaderName(lang.getString("Category"), CHILD_NAME);
      tblProdGroup.setColor(Color.lightGray, Color.white);
      stmt.close();
      rs.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  private void initInfoForCombo() {
    cboSupplier.setData1(getSupplierData());
    cboType.setData(getPaymentData());
    cboType.removeAllItems();
//    cboType.addItem("Pick List");
    cboType.addItem("PO");
    cboType.addItem("Stock Count");
    cboType.setSelectedIndex(0);
    cboSupplier.setEnabled(false);
    try{
      stmt.close();
    }catch(Exception ex){}
    cboStatus.removeAllItems();
    cboStatus.addItem("Active");
    cboStatus.addItem("Cancel");
    cboStatus.setSelectedIndex(0); //default : Work Sheet
  }

  ResultSet getStoreData() {
    ResultSet rs = null;
    String sqlStr = "Select Store_ID, Name From BTM_IM_STORE";
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sqlStr, stmt);
    }
    catch (Exception ex) {}
    return rs;
  }

  ResultSet getSupplierData() {
    ResultSet rs = null;
    String sqlStr = "Select Supp_ID, Supp_Name From BTM_IM_SUPPLIER order by supp_name asc";
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sqlStr, stmt);
    }
    catch (Exception ex) {}
    return rs;
  }

  ResultSet getPaymentData() {
    ResultSet rs = null;
    String sqlStr =
        "Select Payment_Method_ID, Payment_Method_Desc From BTM_IM_PAYMENT_METHOD";
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sqlStr, stmt);
    }
    catch (Exception ex) {}
    return rs;
  }

  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_ITEM_SEASON_SETUP);
  }

  void showButton() {
    this.pnlHeader.removeAll();
    this.pnlHeader.updateUI();
    pnlHeader.add(btnDone, null);
    pnlHeader.add(btnCancel, null);
    btnSelectAll.setSelected(false);
    btnSelectAll.setText(lang.getString("SelectAll"));
  }

  public void btnCancel_actionPerformed(ActionEvent e) {
    //clear data on grid
    while (dmProdGroup.getRowCount() > 0) {
      dmProdGroup.removeRow(0);
    }
    //show Dialog Product Group Search
    DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(
        frmMain,
        lang.getString("TT0006"), true, frmMain, frmMain.pgBusObj);
    dlgProdGroupSearch.setLocation(112, 85);
    dlgProdGroupSearch.updateScreen();
    dlgProdGroupSearch.setVisible(true);
    if (dlgProdGroupSearch.done) {
      frmMain.pnlProdGroupDetail.setDataDetail(dlgProdGroupSearch.getPGID());
      frmMain.showScreen(Constant.SCRS_PROD_GROUP_DETAIL);
    }
    else {
      if (SCREEN_FLAG == PO_SETUP) {
        frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER);
      }
      else if (SCREEN_FLAG == PO_MODIFY) {
        frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER_MODIFY);
      }
      else if (SCREEN_FLAG == PL_SETUP) {
        frmMain.showScreen(Constant.SCRS_PICK_LIST);
      }
      else if (SCREEN_FLAG == PL_MODIFY) {
        frmMain.showScreen(Constant.SCRS_PICK_LIST_MODIFY);
      }
    }
  }
  private void resetData() {
    cboStatus.setSelectedIndex(1);
    txtCreatedDate.setText("");
    txtDesc.setText("");
    showButton();
  }

//check wherether upc is on grid
  private boolean checkExistOnGrid(String upc) {
    for (int i = 0; i < dmProdGroup.getRowCount(); i++) {
      if (dmProdGroup.getValueAt(i,
                                     1).toString().equalsIgnoreCase(upc.
          trim()))
        return true;
    }
    return false;
  }

  private void setSQLsForVector() {
    String strSql, itemID, qty, unitCost;
    String pgID,   suppID="",  desc;
    String type="";
    String status="";

    pgID = txtPGID.getText().trim();
    desc = txtDesc.getText();
    if(cboType.getSelectedItem().toString().equals(TYPE_PICK_LIST)){
      type="P";
    }else if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
      type="O";
      suppID = cboSupplier.getSelectedData();
    }else if(cboType.getSelectedItem().toString().equals(TYPE_STOCK_COUNT)){
      type="S";
    }
    if(cboStatus.getSelectedItem().toString().equals(STATUS_ACTIVE)){
      status="A";
    }else if(cboStatus.getSelectedItem().toString().equals(STATUS_CANCEL)){
      status="C";
    }

    strSql = "Update BTM_IM_PROD_GROUP Set TYPE='" +  type  +
        "',SUPP_ID='" + suppID + "',STATUS='"
        + status + "',LAST_UPDATE_DATE=to_date('" + ut.getSystemDate() +
        "','yyyy-MM-dd'),DESCRIPTION='" + desc +
        "' Where PROD_GROUP_ID='" + pgID + "'";
//    System.out.println(strSql);
    vSql.addElement(strSql);
    strSql = "Delete BTM_IM_PROD_GROUP_ITEM  where PROD_GROUP_ID='" + pgID + "'";
//    System.out.println(strSql);
    vSql.addElement(strSql);

    for (int i = 0; i < tblProdGroup.getRowCount(); i++) {
      itemID = tblProdGroup.getValueAt(i, ITEM_ID).toString();
      strSql = "insert into BTM_IM_PROD_GROUP_ITEM (PROD_GROUP_ID, ITEM_ID,SEQ) values ('"
          + pgID + "','" + itemID + "',"+ i +")";
//      System.out.println(strSql);
      vSql.addElement(strSql);
    }
  }
  Vector getUPCList(){
    Vector vUPC = new Vector();
    for(int i=0;i<tblProdGroup.getRowCount();i++){
      BJCheckBox check = (BJCheckBox) tblProdGroup.getValueAt(i, 0);
      if (check.isSelected()) {
           vUPC.addElement(tblProdGroup.getValueAt(i,2));
      }
    }
    return vUPC;
  }

  public void btnDone_actionPerformed(ActionEvent e) {
    Vector vUPC = getUPCList();
    if (vUPC.isEmpty()){
           ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1079_ItemNotSelectedOrNotApproved") );
           return;
    }
    if(SCREEN_FLAG == PO_SETUP){
      frmMain.pnlPurchaseOrder.setData(vUPC);
      frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER);
    }else if (SCREEN_FLAG == PO_MODIFY){
      frmMain.pnlPurchaseOrderModify.setData(vUPC);
      frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER_MODIFY);
    }else if (SCREEN_FLAG == PL_SETUP){
      frmMain.pnlPickList.setData(vUPC);
      frmMain.pnlPickList.txtProGroupID.setText(txtPGID.getText());
      frmMain.pnlPickList.txtProGroupName.setText(txtDesc.getText());
      frmMain.showScreen(Constant.SCRS_PICK_LIST);
    }else if (SCREEN_FLAG == PL_MODIFY){
      frmMain.pnlPickListModify.setData(vUPC);
      frmMain.pnlPickListModify.txtProGroupID.setText(txtPGID.getText());
      frmMain.pnlPickListModify.txtProGroupName.setText(txtDesc.getText());
      frmMain.showScreen(Constant.SCRS_PICK_LIST_MODIFY);
    }else if (SCREEN_FLAG == STOCK_COUNT_BR){
      frmMain.pnlStockCountBackRoom.setData(vUPC);
      frmMain.pnlStockCountBackRoom.txtPrdGroup.setText(txtDesc.getText());
      frmMain.showScreen(Constant.SCRS_STOCK_COUNT);
    }else if (SCREEN_FLAG == STOCK_COUNT_FR){
      frmMain.pnlStockCountFrontRoom.setData(vUPC);
      frmMain.pnlStockCountFrontRoom.txtPrdGroup.setText(txtDesc.getText());
      frmMain.showScreen(Constant.SCRS_STOCK_COUNT_FRONT_ROOM);
    }
  }

  private void returnPreviousScreen() {
    //clear data on grid
    while (dmProdGroup.getRowCount() > 0) {
      dmProdGroup.removeRow(0);
    }
    //show Dialog Promo Search
    DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(frmMain,
        lang.getString("TT0006"), true, frmMain, frmMain.pgBusObj);
    dlgProdGroupSearch.setLocation(112, 85);
    dlgProdGroupSearch.updateScreen();
    dlgProdGroupSearch.btnSearch.doClick();
    dlgProdGroupSearch.setVisible(true);
    if (dlgProdGroupSearch.done) {
      frmMain.showScreen(Constant.SCRS_PROD_GROUP_MODIFY);
    }
    else {
      frmMain.showScreen(Constant.SCRS_PROD_GROUP);
    }

  }

  void updateValueFromSelecledItemInTable() {
    int row = tblProdGroup.getSelectedRow();
    if (row >= 0) {
      showButton();
    }
  }
  public void tblProdGroup_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      if (btnDone.isEnabled()) {
        updateValueFromSelecledItemInTable();
        showButton();
      }
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
      } else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void btnSupplier_actionPerformed(ActionEvent e) {
    SupplierBusObj suppBusObj = new SupplierBusObj();
    DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(frmMain,
        lang.getString("TT0013"), true, frmMain, suppBusObj);
    dlgSupplierSearch.setVisible(true);
    if (dlgSupplierSearch.done) {
      cboSupplier.setSelectedItem(dlgSupplierSearch.getSuppName());
      String payment_id = getPaymentID(cboSupplier.getSelectedData());
      cboType.setSelectedData(payment_id);
    }
  }

  String getPaymentID(String suppID) {
    String paymentID = "";
    ResultSet rs = null;
    String sqlStr =
        "Select Payment_Method_ID From BTM_IM_SUPPLIER Where Supp_ID='" +
        suppID + "'";
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr, stmt);
      if (rs.next()) {
        paymentID = rs.getString("PAYMENT_METHOD_ID");
      }
      rs.getStatement().close();
    }
    catch (Exception ex) {}
    return paymentID;
  }

  void btnUPC_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0016"), true, frmMain, itemBusObject);
    //search by supplier if PO
    if(cboType.getSelectedItem().toString().equals(TYPE_PO)){

      dlgItemLookup.txtSupplier.setText(cboSupplier.getSelectedData());
      dlgItemLookup.txtSupplier.setEnabled(false);
      dlgItemLookup.txtSupplieName.setText(cboSupplier.getSelectedItem().
                                           toString());
      dlgItemLookup.txtSupplieName.setEnabled(false);
    }
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.cboStatus.setEnabled(false);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      if (dlgItemLookup.getUPCList().isEmpty()) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1079_ItemNotSelectedOrNotApproved"));
        return;
      }
      setData(dlgItemLookup.getUPCList());
    }
  }

  void insertItemToGrid(String upc) {
    String strSql  = " select mas.item_id,mas.art_no,mas.item_name,itemsup.SUPP_NAME, pro.child_name 	"
        + "  from (select * from BTM_IM_ITEM_MASTER where art_no='" + upc + "') mas, "
        + "   (select mas.item_id,mas.child_id,hir.child_name  from BTM_IM_ITEM_MASTER mas, BTM_IM_PROD_HIR hir where mas.child_id = hir.child_id) pro , 		"
        + "    (select distinct isup.item_id,isup.supp_id,sup.supp_name from BTM_IM_ITEM_LOC_SUPPLIER isup,BTM_IM_SUPPLIER sup where isup.SUPP_ID=sup.SUPP_ID) itemsup"
        + "  where  mas.item_id=itemsup.ITEM_ID and mas.item_id = pro.item_id ";

    ResultSet rs = null;
    try {
//      System.out.println(strSql);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(strSql, stmt);
      if (rs.next()) {
        dmProdGroup.addRow(new Object[] {rs.getString("ITEM_ID"),
                               rs.getString("art_no"),
                               rs.getString("ITEM_NAME"),
                               rs.getString("child_name")});
      }
      rs.close();
      stmt.close();
    }
    catch (Exception ex) {}

  }

  void setData(Vector vUPC) {
    for (int i = 0; i < vUPC.size(); i++) {
      if (!checkExistOnGrid(vUPC.get(i).toString().trim())) {
        insertItemToGrid(vUPC.get(i).toString().trim());
      }
    }
  }

  public String[] getSupllierInfo(String SupplierID, DataAccessLayer DAL) {
    String strTemp[] = new String[6];
    String sql =
        " select SUPP_NAME,SUPP_ID,ADDRESS1,VAT_NO,CONTACT_PHONE,CONTACT_FAX from BTM_IM_SUPPLIER where SUPP_ID = '" +
        SupplierID + "'";

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        if (rs.getString("SUPP_NAME") != null) {
          strTemp[0] = rs.getString("SUPP_NAME");
        }
        else {
          strTemp[0] = "";
        }
        if (rs.getString("SUPP_ID") != null) {
          strTemp[1] = rs.getString("SUPP_ID");
        }
        else {
          strTemp[1] = "";
        }
        if (rs.getString("ADDRESS1") != null) {
          strTemp[2] = rs.getString("ADDRESS1");
        }
        else {
          strTemp[2] = "";
        }
        if (rs.getString("VAT_NO") != null) {
          strTemp[3] = rs.getString("VAT_NO");
        }
        else {
          strTemp[3] = "";
        }
        if (rs.getString("CONTACT_PHONE") != null) {
          strTemp[4] = rs.getString("CONTACT_PHONE");
        }
        else {
          strTemp[4] = "";
        }
        if (rs.getString("CONTACT_FAX") != null) {
          strTemp[5] = rs.getString("CONTACT_FAX");
        }
        else {
          strTemp[5] = "";
        }
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return strTemp;
  }

  public String getStoreAdr(String StoreID, DataAccessLayer DAL) {
    String strTemp = "";
    String sql =
        "select ADDRESS_1 from BTM_IM_STORE where STORE_ID = " +
        StoreID;

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        if (rs.getString("ADDRESS_1") != null) {
          strTemp = rs.getString("ADDRESS_1");
        }
        else {
          strTemp = "";
        }
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return strTemp;
  }
  public String getVAT(String ItemID, DataAccessLayer DAL) {
    String strTemp = "0";
    String sql =
        "Select VAT from BTM_IM_ITEM_LOC_SUPPLIER where ITEM_ID = '" + ItemID + "'";

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        if (rs.getString("VAT") != null) {
          strTemp = rs.getString("VAT");
        }
        else {
          strTemp = "0";
        }
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return strTemp;
  }

  void cboStatus_itemStateChanged(ItemEvent e) {
    if (cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approve") ||
        cboStatus.getSelectedItem().toString().equalsIgnoreCase("Complete")) {
    }
    else {
    }
  }
  private boolean checkItemOrder(String upc){
    ResultSet rs = null;
    String sqlStr = "Select mas.Art_No From BTM_IM_ITEM_MASTER mas, BTM_IM_ITEM_LOC_SUPPLIER sup "
        + " Where mas.Item_ID = sup.Item_ID and mas.Status='A' and sup.Supp_ID ='"
        + cboSupplier.getSelectedData()+ "' and mas.Art_No='" + upc + "'";
    try{
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr,stmt);
      if(rs.next()){
        stmt.close();
        return true;
      }
      stmt.close();
    }
    catch(Exception ex){}
    return false;
  }
  void addItemToGrid(String upc){
    ResultSet rs = null;
    String strSql  = " select mas.item_id,mas.art_no,mas.item_name,itemsup.SUPP_NAME, pro.child_name 	"
        + "  from (select * from BTM_IM_ITEM_MASTER where art_no='" + upc + "') mas, "
        + "   (select mas.item_id,mas.child_id,hir.child_name  from BTM_IM_ITEM_MASTER mas, BTM_IM_PROD_HIR hir where mas.child_id = hir.child_id) pro , 		"
        + "    (select distinct isup.item_id,isup.supp_id,sup.supp_name from BTM_IM_ITEM_LOC_SUPPLIER isup,BTM_IM_SUPPLIER sup where isup.SUPP_ID=sup.SUPP_ID) itemsup"
        + "  where  mas.item_id=itemsup.ITEM_ID and mas.item_id = pro.item_id ";


    try{
//        System.out.println(strSql);
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = DAL.executeScrollQueries(strSql,stmt);
        while(rs.next()){
          dmProdGroup.addRow(new Object[] {rs.getString("ITEM_ID"),rs.getString("art_no"),
             rs.getString("ITEM_NAME"),rs.getString("child_name")});
        }
        rs.close();
        stmt.close();
    }catch(Exception ex){}
  }

  void btnSelectAll_actionPerformed(ActionEvent e) {
    int rows = tblProdGroup.getRowCount();
   if(rows>0){
     for (int i = 0; i < rows; i++) {
       JCheckBox chkTemp = (BJCheckBox) tblProdGroup.getValueAt(i, 0);
       chkTemp.setSelected(btnSelectAll.isSelected());
       tblProdGroup.setValueAt(chkTemp, i, 0);
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

class PanelProductGroupDetails_tblProdGroup_mouseAdapter
    extends MouseAdapter {
  private PanelProductGroupDetails adaptee;
  PanelProductGroupDetails_tblProdGroup_mouseAdapter(
      PanelProductGroupDetails adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblProdGroup_mouseClicked(e);
  }
}


class PanelProductGroupDetails_btnDone_actionAdapter
    implements ActionListener {
  private PanelProductGroupDetails adaptee;
  PanelProductGroupDetails_btnDone_actionAdapter(PanelProductGroupDetails
                                                 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelProductGroupDetails_btnCancel_actionAdapter
    implements ActionListener {
  private PanelProductGroupDetails adaptee;
  PanelProductGroupDetails_btnCancel_actionAdapter(PanelProductGroupDetails
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelProductGroupDetails_btnSupplier_actionAdapter
    implements java.awt.event.ActionListener {
  PanelProductGroupDetails adaptee;

  PanelProductGroupDetails_btnSupplier_actionAdapter(PanelProductGroupDetails
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplier_actionPerformed(e);
  }
}

class PanelProductGroupDetails_cboStatus_itemAdapter implements java.awt.event.ItemListener {
  PanelProductGroupDetails adaptee;

  PanelProductGroupDetails_cboStatus_itemAdapter(PanelProductGroupDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.cboStatus_itemStateChanged(e);
  }
}

class PanelProductGroupDetails_btnSelectAll_actionAdapter implements java.awt.event.ActionListener {
  PanelProductGroupDetails adaptee;

  PanelProductGroupDetails_btnSelectAll_actionAdapter(PanelProductGroupDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSelectAll_actionPerformed(e);
  }
}
