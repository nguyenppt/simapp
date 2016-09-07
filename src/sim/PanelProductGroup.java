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
import java.awt.Font;
import java.awt.BorderLayout;

/**
 * <p>Title      : Product Group </p>
 * <p>Description: makes an order and send it’s print to supplier </p>
 * <p>Description: Main -> Inv Mgmt -> Product Group </p>
 * <p>Copyright  : Copyright (c) 2006  </p>
 * <p>Company    : BTM </p>
 * @author       : TrungNT
 * @version 1.0
 */
public class PanelProductGroup extends JPanel {
  public static final String STATUS_ACTIVE   = "Active";
  public static final String STATUS_CANCEL   = "Cancel";
  public static final String TYPE_PICK_LIST   = "Pick List";
  public static final String TYPE_PO   = "PO";
  public static final String TYPE_STOCK_COUNT   = "Stock Count";

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  BorderLayout borderLayout1 = new BorderLayout();
  FrameMainSim frmMain;
  ProdGroupBusObj prodGroupObj=new ProdGroupBusObj();
  CardLayout cardLayout;
  JPanel parent;

  Statement stmt = null;
  DataAccessLayer DAL = new DataAccessLayer();
  Utils ut = new Utils();
  Vector vSql = new Vector();
  Vector vInfo = new Vector();

  //=============Define Constant for Table Column
  public static final int ITEM_ID   = 0;
  public static final int UPC       = 1;
  public static final int ITEM_NAME = 2;
  public static final int CHILD_NAME = 3;

//  public static final int VAT    = 3;


  JPanel pnlHeader = new JPanel();
//  BJButton btnModify = new BJButton();
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnClear = new BJButton();
  BJButton btnAdd = new BJButton();
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
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
//  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
//  JTextField txtReceiptDate = new JTextField();
  JTextField txtCreatedDate = new JTextField();
//  JButton btnReceiptDate = new JButton();
  JTextField txtPOID = new JTextField();
//  JButton btnOrderDate = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel pnlRightLabel = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JPanel pnlRightInfo = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  JLabel lblDesc = new JLabel();
  JScrollPane scrPanelTable = new JScrollPane();
  SortableTableModel dmPurchaseOrder = new SortableTableModel();
  BJTable tblPurchaseOrder = new BJTable(dmPurchaseOrder,true);
  JPanel jPanel1 = new JPanel();
  String poID ="";
  FlowLayout flowLayout4 = new FlowLayout();
  JTextArea txtDesc = new JTextArea();
  BJButton btnSearch = new BJButton();
  JComboBox cboStatus = new JComboBox();
  BJComboBox cboType = new BJComboBox();
  BJComboBox cboSupplier = new BJComboBox();
  JButton btnSupplier = new JButton();
  FlowLayout flowLayout5 = new FlowLayout();
//  BJComboBox cboStore = new BJComboBox();
//  JButton btnStore = new JButton();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JLabel jLabel10 = new JLabel();
  JTextField txtUPC = new JTextField();
  JButton btnUPC = new JButton();
//  JPanel jPanel2 = new JPanel();
//  JPanel jPanel3 = new JPanel();
  JPanel pnlDesc = new JPanel();
  FlowLayout flowLayout3 = new FlowLayout();
  //  JLabel jLabel11 = new JLabel();
//  JTextField txtQty = new JTextField();

  //flag to set modify
//  boolean FLAG_MODIFY = false;
//  JLabel jLabel9 = new JLabel();
//  JLabel jLabel12 = new JLabel();
  public PanelProductGroup(FrameMainSim frmMain) {
        try {
          this.frmMain = frmMain;
          this.DAL = frmMain.getDAL();
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public PanelProductGroup(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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
    public void initScreen(){
      poID = prodGroupObj.getProdGroupIdCanInsertPOS(DAL);
      txtPOID.setText(poID);
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
    btnCancel.addActionListener(new PanelProductGroup_btnCancel_actionAdapter(this));

    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+"(F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnDelete.addActionListener(new PanelProductGroup_btnDelete_actionAdapter(this));

    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear")+"(F7)");
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnClear.addActionListener(new PanelProductGroup_btnClear_actionAdapter(this));

    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+"(F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                   "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelProductGroup_btnAdd_actionAdapter(this));

    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelProductGroup_btnDone_actionAdapter(this));
    pnlCenter.setLayout(borderLayout2);
    pnlTable.setPreferredSize(new Dimension(10, 100));
    pnlTable.setLayout(borderLayout4);
    pnlInfo.setPreferredSize(new Dimension(10, 180));
    pnlInfo.setLayout(borderLayout3);
    pnlLeft.setPreferredSize(new Dimension(450, 30));
    pnlLeft.setLayout(borderLayout5);
    pnlRight.setPreferredSize(new Dimension(500, 30));
    pnlRight.setLayout(borderLayout6);
    pnlLeftLabel.setPreferredSize(new Dimension(140, 10));
    pnlLeftLabel.setLayout(flowLayout5);
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel2.setPreferredSize(new Dimension(90, 22));
    jLabel2.setText(lang.getString("SupplierName")+":");
    jLabel3.setPreferredSize(new Dimension(90, 22));
    jLabel3.setText(lang.getString("CreatedDate")+":");
//    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    jLabel4.setPreferredSize(new Dimension(90, 22));
//    jLabel4.setText("To Store:");
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel5.setMaximumSize(new Dimension(66, 23));
    jLabel5.setPreferredSize(new Dimension(90, 22));
    jLabel5.setText(lang.getString("PG_ID")+":");
    pnlLeftInfo.setPreferredSize(new Dimension(250, 10));
    pnlLeftInfo.setLayout(flowLayout1);
//    txtReceiptDate.setBackground(Color.white);
//    txtReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    txtReceiptDate.setPreferredSize(new Dimension(140, 22));
    txtCreatedDate.setBackground(Color.white);
    txtCreatedDate.setEnabled(true);
    txtCreatedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreatedDate.setPreferredSize(new Dimension(175, 22));
    txtCreatedDate.setEditable(false);
    txtCreatedDate.setText( ut.getSystemDate(1));
//    btnReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    btnReceiptDate.setMinimumSize(new Dimension(30, 23));
//    btnReceiptDate.setPreferredSize(new Dimension(30, 23));
//    btnReceiptDate.setText("jButton1");
//    btnReceiptDate.addActionListener(new
//                                 PanelProductGroup_btnReceiptDate_actionAdapter(this));
//    btnOrderDate.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
//    btnOrderDate.setPreferredSize(new Dimension(30, 23));
//    btnOrderDate.setText("jButton1");
//    btnOrderDate.addActionListener(new
//        PanelProductGroup_btnOrderDate_actionAdapter(this));
    txtPOID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPOID.setPreferredSize(new Dimension(175, 22));
    txtPOID.setDisabledTextColor(SystemColor.info);
    txtPOID.setEditable(false);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    pnlRightLabel.setPreferredSize(new Dimension(100, 10));
    pnlRightLabel.setLayout(flowLayout2);
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel6.setOpaque(false);
    jLabel6.setPreferredSize(new Dimension(80, 22));
    jLabel6.setRequestFocusEnabled(true);
    jLabel6.setText(lang.getString("Status")+":");
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel8.setPreferredSize(new Dimension(80, 22));
    jLabel8.setText(lang.getString("Type")+":");
    pnlRightInfo.setPreferredSize(new Dimension(100, 10));
    pnlRightInfo.setLayout(flowLayout4);
    lblDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDesc.setMaximumSize(new Dimension(110, 16));
    lblDesc.setMinimumSize(new Dimension(110, 16));
    lblDesc.setPreferredSize(new Dimension(135, 20));
    lblDesc.setHorizontalAlignment(SwingConstants.LEADING);
    lblDesc.setText("              "+lang.getString("Description")+":");
    jPanel1.setPreferredSize(new Dimension(50, 10));
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    showButton();
    tblPurchaseOrder.addMouseListener(new
                                  PanelProductGroup_tblPurchaseOrder_mouseAdapter(this));
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDesc.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDesc.setMinimumSize(new Dimension(635, 15));
    txtDesc.setPreferredSize(new Dimension(590, 38));
    txtDesc.setLineWrap(true);
    txtDesc.setWrapStyleWord(true);

    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search")+"(F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnSearch.addActionListener(new PanelProductGroup_btnSearch_actionAdapter(this));
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setPreferredSize(new Dimension(180, 22));
    cboStatus.setPopupVisible(false);
    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboType.setPreferredSize(new Dimension(180, 22));
    cboSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSupplier.setPreferredSize(new Dimension(144, 22));
    cboType.addItemListener(new PanelProductGroup_cboType_itemAdapter(this));

    btnSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSupplier.setPreferredSize(new Dimension(30, 25));
    btnSupplier.setText("...");
    btnSupplier.addActionListener(new PanelProductGroup_btnSupplier_actionAdapter(this));
    flowLayout5.setAlignment(FlowLayout.RIGHT);
//    cboStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    cboStore.setPreferredSize(new Dimension(140, 22));
//    btnStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnStore.setPreferredSize(new Dimension(30, 23));
//    btnStore.setText("..");
//    btnStore.addActionListener(new PanelProductGroup_btnStore_actionAdapter(this));
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel10.setPreferredSize(new Dimension(80, 22));
    jLabel10.setToolTipText("");
    jLabel10.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel10.setText(lang.getString("UPC")+":");
    jLabel10.setVerticalAlignment(SwingConstants.CENTER);
    txtUPC.setBackground(Color.white);
    txtUPC.setEnabled(true);
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUPC.setPreferredSize(new Dimension(145, 22));
    txtUPC.setRequestFocusEnabled(true);
    txtUPC.setDisabledTextColor(new Color(236, 233, 216));
    txtUPC.setText("");
    txtUPC.addKeyListener(new PanelProductGroup_txtUPC_keyAdapter(this));
    btnUPC.setEnabled(true);
    btnUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnUPC.setPreferredSize(new Dimension(30, 22));
    btnUPC.setText("...");
    btnUPC.addActionListener(new PanelProductGroup_btnUPC_actionAdapter(this));
    pnlDesc.setPreferredSize(new Dimension(742, 80));
    pnlDesc.setLayout(flowLayout3);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    pnlRightInfo.add(cboType, null);
//    pnlRightInfo.add(cboSupplier, null);
//    pnlRightInfo.add(btnSupplier, null);
    pnlRightInfo.add(cboStatus, null);
    pnlRightInfo.add(txtUPC);
    pnlRightInfo.add(btnUPC);
    this.add(pnlCenter, java.awt.BorderLayout.CENTER);
    pnlCenter.add(pnlTable, java.awt.BorderLayout.CENTER);
    pnlCenter.add(pnlInfo, java.awt.BorderLayout.NORTH);
    pnlInfo.add(pnlLeft, java.awt.BorderLayout.WEST);
    pnlLeftLabel.add(jLabel5);
//    pnlLeftLabel.add(jLabel4);
    pnlLeftLabel.add(jLabel3);
    pnlLeftLabel.add(jLabel2);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightLabel, java.awt.BorderLayout.WEST);
    pnlRightLabel.add(jLabel8);
    pnlRightLabel.add(jLabel6);
    pnlRightLabel.add(jLabel10);
    pnlLeftInfo.add(txtPOID);
//    pnlLeftInfo.add(jPanel2);
    //    pnlLeftInfo.add(cboStore, null);
//    pnlLeftInfo.add(btnStore, null);
    pnlLeftInfo.add(txtCreatedDate);
    pnlLeftInfo.add(cboSupplier);
    pnlLeftInfo.add(btnSupplier);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightInfo, java.awt.BorderLayout.CENTER);
    pnlLeft.add(pnlLeftInfo, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlRight, java.awt.BorderLayout.CENTER);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.NORTH);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.CENTER);
    pnlRight.add(jPanel1, java.awt.BorderLayout.EAST);
    scrPanelTable.getViewport().add(tblPurchaseOrder,null);
    this.add(pnlHeader, java.awt.BorderLayout.NORTH);
    pnlInfo.add(pnlDesc, java.awt.BorderLayout.SOUTH);
    pnlDesc.add(lblDesc);
    pnlDesc.add(txtDesc);
    registerKeyboardActions();
    initInfoForCombo();
    initTable();
//    cboType.addItemListener(new PanelProductGroup_cboType_itemAdapter(this));
  }
  private void initInfoForCombo(){
    cboSupplier.setData1(getSupplierData());
    cboSupplier.setSelectedIndex(0);
    cboType.removeAllItems();
//    cboType.addItem("Pick List");
    cboType.addItem("PO");
    cboType.addItem("Stock Count");
    cboType.setSelectedIndex(0);
//    cboSupplier.setEnabled(false);
//    btnSupplier.setEnabled(false);
    try{
      stmt.close();
    }catch(Exception ex){}
    cboStatus.removeAllItems();
    cboStatus.addItem("Active");
    cboStatus.addItem("Cancel");
    cboStatus.setSelectedIndex(0); //default : Work Sheet
  }
  ResultSet getStoreData(){
    ResultSet rs = null;
    String sqlStr = "Select Store_ID, Name From BTM_IM_STORE";
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sqlStr,stmt);
    }
    catch(Exception ex){}
    return rs;
  }
  ResultSet getSupplierData(){
    ResultSet rs = null;
    String sqlStr = "Select Supp_ID, Supp_Name From BTM_IM_SUPPLIER order by supp_name asc";
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                          TYPE_SCROLL_INSENSITIVE,
                                          ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sqlStr,stmt);
    }
    catch(Exception ex){}
    return rs;
  }
//  ResultSet getPaymentData(){
//    ResultSet rs = null;
//    String sqlStr = "Select Payment_Method_ID, Payment_Method_Desc From BTM_IM_PAYMENT_METHOD";
//    try{
//      stmt = DAL.getConnection().createStatement(ResultSet.
//                                          TYPE_SCROLL_INSENSITIVE,
//                                          ResultSet.CONCUR_READ_ONLY);
//      rs = DAL.executeQueries(sqlStr,stmt);
//    }
//    catch(Exception ex){}
//    return rs;
//  }
  private void initTable() {
      //define for table
      String[] columnNames = new String[] {
          lang.getString("ItemID"), lang.getString("UPC"), lang.getString("ItemName"), lang.getString("Category")};
      dmPurchaseOrder.setDataVector(new Object[][] {}, columnNames);
      tblPurchaseOrder.setColumnWidth(UPC, 110);
      tblPurchaseOrder.setColumnWidth(ITEM_NAME, 150);
      tblPurchaseOrder.setColumnWidth(CHILD_NAME, 30);
      tblPurchaseOrder.setColor(Color.lightGray, Color.white);
 }
 public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_ITEM_SEASON_SETUP);
    btnAdd.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    //  btnAdminRoleReport.setEnabled(er.getReport());
  }

  void showButton() {
    this.pnlHeader.removeAll();
    this.pnlHeader.updateUI();
    pnlHeader.add(btnDone, null);
    pnlHeader.add(btnAdd, null);
    pnlHeader.add(btnSearch, null);
    pnlHeader.add(btnClear, null);
    pnlHeader.add(btnDelete, null);
    pnlHeader.add(btnCancel, null);
  }

  public void btnCancel_actionPerformed(ActionEvent e) {
     returnPreviousScreen();
  }

//   public void btnOrderDate_actionPerformed(ActionEvent e) {
//    int posX, posY, posXFrame, posYFrame;
//    posXFrame = this.frmMain.getX();
//    posYFrame = this.frmMain.getY();
////    posX = this.btnOrderDate.getY() + posYFrame + 90;
////    posY = this.btnReceiptDate.getY() + posYFrame + 90;
//    TimeDlg startdate = new TimeDlg(null);
//    startdate.setTitle("Choose Order Date");
//    startdate.setResizable(false);
////    startdate.setLocation(posX,posY);
//    startdate.setVisible(true);
//    if (startdate.isOKPressed()) {
//      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
//      String date = fd.format(startdate.getDate());
//      this.txtCreatedDate.setText(date);
//    }
//
//  }

//  public void btnReceiptDate_actionPerformed(ActionEvent e) {
//    int posX, posY, posXFrame, posYFrame;
//    posXFrame = this.frmMain.getX();
//    posYFrame = this.frmMain.getY();
//    posX = this.btnOrderDate.getY() + posYFrame + 90;
//    posY = this.btnReceiptDate.getY() + posYFrame + 90;
//    TimeDlg startdate = new TimeDlg(null);
//    startdate.setTitle("Choose Receipt Date");
//    startdate.setResizable(false);
//    startdate.setLocation(posX,posY);
//    startdate.setVisible(true);
//    if (startdate.isOKPressed()) {
//      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
//      String date = fd.format(startdate.getDate());
//      this.txtReceiptDate.setText(date);
//    }
//
//  }

  public void btnAdd_actionPerformed(ActionEvent e) {
//    String orderDate = txtCreatedDate.getText().trim();
//    String receiptDate = txtReceiptDate.getText().trim();
    String upc=txtUPC.getText().trim();

//      if (!ut.checkDate(orderDate, "/") ||  orderDate.equals("")){
//        ut.showMessage(frmMain,"Warning", "Order date must be a date type. It is not null");
//        txtCreatedDate.requestFocus();
//        txtCreatedDate.setSelectionStart(0);
//        txtCreatedDate.setSelectionEnd(txtCreatedDate.getText().length());
//        return;
//      }
//      if (ut.compareDate( ut.getSystemDateStandard(),orderDate)) {
//        ut.showMessage(frmMain, "Warning", "Order date must be greater than or equal current date.");
//        txtCreatedDate.requestFocus();
//        txtCreatedDate.setSelectionStart(0);
//        txtCreatedDate.setSelectionEnd(txtCreatedDate.getText().length());
//        return;
//      }
//      if(!ut.checkDate(receiptDate,"/") || receiptDate.equals("")){
//        ut.showMessage(frmMain,"Warning", "Input receipt date must be a date type. It is not null");
//        txtReceiptDate.requestFocus();
//        txtReceiptDate.setSelectionStart(0);
//        txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
//        return;
//      }
//      if (ut.compareDate(orderDate, receiptDate)) {
//        ut.showMessage(frmMain, "Warning", "Receipt date must be greater than or equal order date ");
//        txtReceiptDate.requestFocus();
//        txtReceiptDate.setSelectionStart(0);
//        txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
//        return;
//      }

//      if (cboType.getSelectedItem().toString().equalsIgnoreCase("None")) {
//        ut.showMessage(frmMain, "Warning", "Supplier is not None value.");
//        cboType.requestFocus(true);
//        return;
//      }
    if(upc.equalsIgnoreCase("")){
      ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1007_UPCIsNotNull"));
        txtUPC.requestFocus();
        txtUPC.setSelectionStart(0);
        txtUPC.setSelectionEnd(txtUPC.getText().length());
        return;
    }
    if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
      if (!checkItemOrder(upc)) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1151_UPC_NotExistSupplier")+"'"
                       + cboType.getSelectedItem().toString() +
                       "'. "+lang.getString("MS1152_UPC_ItemInvalid"));
        txtUPC.requestFocus();
        txtUPC.setSelectionStart(0);
        txtUPC.setSelectionEnd(txtUPC.getText().length());
        return;
      }
    }
    if(checkExistOnGrid(upc)){
      ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1106_UPC_Alreadyexisted"));
        txtUPC.requestFocus();
        txtUPC.setSelectionStart(0);
        txtUPC.setSelectionEnd(txtUPC.getText().length());
        return;
    }
    addItemToGrid(upc);
    setObjectEnable(false);
  }
  void addItemToGrid(String upc){
    ResultSet rs = null;
//    double qty=1;
//    if(!txtQty.getText().trim().equalsIgnoreCase("")){
//      qty = Double.parseDouble(txtQty.getText().trim());
//      if(qty <= 0){
//        ut.showMessage(frmMain, lang.getString("TT001"),
//                     "The Quantity can not less than or equal 0." );
//        txtQty.requestFocus();
//        txtQty.setSelectionStart(0);
//        txtQty.setSelectionEnd(txtQty.getText().length());
//        return;
//      }
//    }

      String strSql =" select * from (select mas.item_id,mas.art_no,mas.item_name,itemsup.SUPP_NAME,pri.attr2_name, pro.child_name,pri.new_price 	"
                                 +  " from BTM_IM_ITEM_MASTER mas, "
                                 +  "  (select mas.item_id,mas.child_id,hir.child_name  from BTM_IM_ITEM_MASTER mas, BTM_IM_PROD_HIR hir where mas.child_id = hir.child_id) pro , 		"
                                 +  "  (select distinct item_id,new_price,attr2_name  from BTM_IM_PRICE_HIST where status='1') pri,  		 "
                                 +  "   (select distinct isup.item_id,isup.supp_id,sup.supp_name from BTM_IM_ITEM_LOC_SUPPLIER isup,BTM_IM_SUPPLIER sup where isup.SUPP_ID=sup.SUPP_ID) itemsup 	"
                                 +  " where pri.ITEM_ID=mas.ITEM_ID and mas.item_id=itemsup.ITEM_ID and pro.item_id= mas.item_id  and mas.Art_No = '" + upc + "')"
                      +" where  rownum <=200";

//    String strSql = "Select mas.Item_Id,mas.Art_No UPC,mas.Item_Name,mas.Standard_Uom, mas.Pack_Size," + String.valueOf(qty) + " Qty,itmsup.Unit_Cost "
//              + "    From BTM_IM_ITEM_MASTER mas, "
//              + "        (select distinct isup.Item_Id,isup.Unit_Cost  "
//              + "         from BTM_IM_ITEM_LOC_SUPPLIER isup, BTM_IM_SUPPLIER sup "
//              + "	  where isup.SUPP_ID=sup.SUPP_ID and sup.SUPP_ID ='"+ cboType.getSelectedData() + "') itmsup "
//              + "    where mas.Item_Id=itmsup.ITEM_ID and mas.Status='A' and mas.Art_No = '" + upc + "'";
    try{
//        System.out.println(strSql);
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = DAL.executeScrollQueries(strSql,stmt);
        while(rs.next()){
          dmPurchaseOrder.addRow(new Object[] {rs.getString("ITEM_ID"),rs.getString("art_no"),
             rs.getString("ITEM_NAME"),rs.getString("child_name")});
        }
        rs.close();
        stmt.close();
    }catch(Exception ex){}
    txtUPC.setText("");
//    txtQty.setText("");
  }
  void setObjectEnable(boolean flag){
    if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
      cboSupplier.setEnabled(flag);
      btnSupplier.setEnabled(flag);
    }else{
      cboSupplier.setEnabled(false);
      btnSupplier.setEnabled(false);

    }
   cboType.setEnabled(flag);


  }
  private void resetData(){
    txtPOID.setText(poID);
//    cboStore.setSelectedIndex(0);
    cboType.setSelectedIndex(0);
    cboStatus.setSelectedIndex(0);//default: Work Sheet

//    txtCreatedDate.setText("");
//    txtReceiptDate.setText("");
    txtDesc.setText("");
    txtUPC.setText("");
//    txtQty.setText("");
    setObjectEnable(true);
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
  private boolean checkExistOnGrid(String upc){
    for(int i=0;i < dmPurchaseOrder.getRowCount();i++){
      if(dmPurchaseOrder.getValueAt(i,1).toString().equalsIgnoreCase(upc.trim()))
        return true;
    }
    return false;
  }
  private void setSQLsForVector(){
    String strSql,itemID,qty,unitCost;
    String poID,createdDate,term,desc,suppID="";
    String type="";
    String status="";
    poID = txtPOID.getText().trim();
//    storeID = cboStore.getSelectedData();
    createdDate = txtCreatedDate.getText();
//    receiptDate = txtReceiptDate.getText();
//    type = cboType.getSelectedData();
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

    desc =txtDesc.getText();
//    status = cboStatus.getSelectedItem().toString().substring(0,1);
    String createDate=ut.getSystemDate(1);
    String createBy=DAL.getEmpID();
    String currID = DAL.getCurrencyID();

    //If group for PO: insert supplier
    if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
      strSql = "insert into BTM_IM_PROD_GROUP (PROD_GROUP_ID, DESCRIPTION, TYPE, UOM, STATUS, CREATE_DATE, "
          + "LAST_UPDATE_DATE,SUPP_ID) values ('" + poID + "','" + desc + "','" +
          type + "','" + "UOM" + "','" + status + "',"
          + " to_date('" + createdDate + "','DD/MM/YYYY'),to_date('" + createdDate
          + "','DD/MM/YYYY'),'"+suppID+"')";
    }else{
      strSql = "insert into BTM_IM_PROD_GROUP (PROD_GROUP_ID, DESCRIPTION, TYPE, UOM, STATUS, CREATE_DATE, "
          + "LAST_UPDATE_DATE,SUPP_ID) values ('" + poID + "','" + desc + "','" +
          type + "','" + "UOM" + "','" + status + "',"
          + " to_date('" + createdDate + "','DD/MM/YYYY'),to_date('" + createdDate
          + "','DD/MM/YYYY'),'')";

    }
//    System.out.println(strSql);
    vSql.addElement(strSql);

    for(int i=0;i< tblPurchaseOrder.getRowCount();i++){
      itemID = tblPurchaseOrder.getValueAt(i,ITEM_ID).toString();
      strSql="insert into BTM_IM_PROD_GROUP_ITEM (PROD_GROUP_ID, ITEM_ID,SEQ) values ('"
          + poID + "','" + itemID + "',"+ i +")";
//      System.out.println(strSql);
      vSql.addElement(strSql);
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



  public void btnDone_actionPerformed(ActionEvent e) {
    String createdDate = txtCreatedDate.getText().trim();
//    String receiptDate = txtReceiptDate.getText().trim();
    if(tblPurchaseOrder.getRowCount()>0){
//      if (!ut.checkDate(orderDate, "/") ||  orderDate.equals("")){
//        ut.showMessage(frmMain,lang.getString("TT001"), "Order date must be a date type. It is not null");
//        txtCreatedDate.requestFocus();
//        txtCreatedDate.setSelectionStart(0);
//        txtCreatedDate.setSelectionEnd(txtCreatedDate.getText().length());
//        return;
//      }
//      if (ut.compareDate( ut.getSystemDateStandard(),orderDate)) {
//        ut.showMessage(frmMain, lang.getString("TT001"), "Order date must be greater than or equal current date.");
//        txtCreatedDate.requestFocus();
//        txtCreatedDate.setSelectionStart(0);
//        txtCreatedDate.setSelectionEnd(txtCreatedDate.getText().length());
//        return;
//      }
//      if(!ut.checkDate(receiptDate,"/") || receiptDate.equals("")){
//        ut.showMessage(frmMain,lang.getString("TT001"), "Input receipt date must be a date type. It is not null");
//        txtReceiptDate.requestFocus();
//        txtReceiptDate.setSelectionStart(0);
//        txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
//        return;
//      }
//      if (ut.compareDate(orderDate, receiptDate)) {
//        ut.showMessage(frmMain, lang.getString("TT001"), "Receipt date must be greater than or equal order date ");
//        txtReceiptDate.requestFocus();
//        txtReceiptDate.setSelectionStart(0);
//        txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
//        return;
//      }

      if(txtDesc.getText().trim().length()==0){
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1150_InputDescription"));
        return;
      }

       setSQLsForVector();
       if (vSql.isEmpty() == false) {
          try{
            stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);
            DAL.setBeginTrans(DAL.getConnection());
            DAL.executeBatchQuery(vSql, stmt);
            DAL.setEndTrans(DAL.getConnection());

            stmt.close();
          }
          catch(Exception ex){};
       }
    }
    returnPreviousScreen();
  }
  private void returnPreviousScreen(){
    resetData();
    while (dmPurchaseOrder.getRowCount() > 0) {
        dmPurchaseOrder.removeRow(0);
      }
    vSql.removeAllElements();
    showButton();
//    FLAG_MODIFY = false;
//    txtQty.setText("");
    txtUPC.setEnabled(true);
    btnUPC.setEnabled(true);
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0002"));
    frmMain.pnlMainSim.btnItemLookup.requestFocus(true);
 }
 void updateValueFromSelecledItemInTable(){
   int row=tblPurchaseOrder.getSelectedRow();
   if(row >= 0){
     txtUPC.setText(tblPurchaseOrder.getValueAt(row, UPC).toString());
//     txtQty.setText("");
     showButton();//show Modify; hide Add
//     FLAG_MODIFY = true;// allow hit F4 to modify
     txtUPC.setEnabled(false);
     btnUPC.setEnabled(false);
//     txtQty.requestFocus(true);
   }
  }
  public void btnDelete_actionPerformed(ActionEvent e) {
    int row= tblPurchaseOrder.getSelectedRow();
    if(row>=0){
      int choose = ut.showMessage(frmMain, lang.getString("TT020"),
                                  lang.getString("MS1076_DeleteUPC") + tblPurchaseOrder.getValueAt(row,UPC).toString() + "' ?", 3, DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.YES_VALUE) {
        dmPurchaseOrder.removeRow(row);
        showButton();
        if(tblPurchaseOrder.getRowCount()==0){
          setObjectEnable(true);
        }
      }
    }
    else{
      if(tblPurchaseOrder.getRowCount()>0){
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1077_ChooseUPCWantToDelete"));
        return;
      }
    }
  }

  public void tblPurchaseOrder_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
          updateValueFromSelecledItemInTable();
    }
  }

  public void btnModify_actionPerformed(ActionEvent e) {
  }
  boolean checkExistOnGridModify(String promoName){
    for (int i = 0; i < dmPurchaseOrder.getRowCount(); i++) {
      if (i != tblPurchaseOrder.getSelectedRow() &&
          dmPurchaseOrder.getValueAt(i, UPC).toString().equals(promoName.trim()))
        return true;
    }
    return false;
  }

  public void btnSearch_actionPerformed(ActionEvent e) {
     ProdGroupBusObj pgBusObject = new ProdGroupBusObj();
     DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(frmMain,lang.getString("TT0101"),true, frmMain,pgBusObject);
     dlgProdGroupSearch.setVisible(true);
     if(dlgProdGroupSearch.done){
       frmMain.showScreen(Constant.SCRS_PROD_GROUP_MODIFY);
     }else{
       frmMain.showScreen(Constant.SCRS_PROD_GROUP);
     }
   }

  public void btnClear_actionPerformed(ActionEvent e) {
//    if(cboType.isEnabled()){
        resetData();
//    }else{
//      txtUPC.setText("");
//      txtUPC.setEnabled(true);
//      btnUPC.setEnabled(true);
//      txtQty.setText("");
//    }
    showButton();
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
     else if (identifier.intValue() == KeyEvent.VK_F2) {
         btnAdd.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F8) {
       btnDelete.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F3) {
       btnSearch.doClick();
     }
//     else if (identifier.intValue() == KeyEvent.VK_F4) {
//         updateValueFromSelecledItemInTable();
//     }
     else if (identifier.intValue() == KeyEvent.VK_F7) {
       btnClear.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
       btnCancel.doClick();
     }
   }
 }

  void btnSupplier_actionPerformed(ActionEvent e) {
       SupplierBusObj suppBusObj = new SupplierBusObj();
       DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(frmMain,lang.getString("TT0013"),true,frmMain,suppBusObj);
       dlgSupplierSearch.setVisible(true);
       if (dlgSupplierSearch.done){
         cboSupplier.setSelectedItem(dlgSupplierSearch.getSuppName());
         cboType.setSelectedItem(dlgSupplierSearch.getSuppName());
//         String payment_id = getPaymentID(cboType.getSelectedData());
//         cboPayment.setSelectedData(payment_id);
       }
  }
//  String getPaymentID(String suppID){
//    String paymentID = "";
//    ResultSet rs = null;
//    String sqlStr = "Select Payment_Method_ID From BTM_IM_SUPPLIER Where Supp_ID='" + suppID + "'";
//    try{
//      stmt = DAL.getConnection().createStatement();
//      rs = DAL.executeQueries(sqlStr,stmt);
//      if(rs.next()){
//        paymentID = rs.getString("PAYMENT_METHOD_ID");
//      }
//      stmt.close();
//      rs.close();
//    }
//    catch(Exception ex){}
//    return paymentID;
//  }

//  void btnStore_actionPerformed(ActionEvent e) {
//       DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,
//           lang.getString("TT014")+" - STORE SEARCH", true, frmMain, frmMain.storeBusObj);
//       dlgStoreSearch.setVisible(true);
//       if (dlgStoreSearch.done) {
//         cboStore.setSelectedData(dlgStoreSearch.getData());
//       }
//  }

  void btnUPC_actionPerformed(ActionEvent e) {
      if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
        if (cboSupplier.getSelectedItem().toString().equalsIgnoreCase("None")) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1149_SupplierNotNoneValue"));
          cboType.requestFocus(true);
          return;
        }
      }
       btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
       DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0005"),true,frmMain,itemBusObject);
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
       if (dlgItemLookup.done){
          if (dlgItemLookup.getUPCList().isEmpty()){
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1079_ItemNotSelectedOrNotApproved"));
            return;
          }
          setData(dlgItemLookup.getUPCList());
          setObjectEnable(false);
          txtUPC.setText("");
//          txtQty.setText("");
       }
  }
  void insertItemToGrid(String upc){
    String strSql =" select * from (select mas.item_id,mas.art_no,mas.item_name,itemsup.SUPP_NAME,pri.attr2_name, pro.child_name,pri.new_price 	"
                               +  " from BTM_IM_ITEM_MASTER mas, "
                               +  "  (select mas.item_id,mas.child_id,hir.child_name  from BTM_IM_ITEM_MASTER mas, BTM_IM_PROD_HIR hir where mas.child_id = hir.child_id) pro , 		"
                               +  "  (select distinct item_id,new_price,attr2_name  from BTM_IM_PRICE_HIST where status='1') pri,  		 "
                               +  "   (select distinct isup.item_id,isup.supp_id,sup.supp_name from BTM_IM_ITEM_LOC_SUPPLIER isup,BTM_IM_SUPPLIER sup where isup.SUPP_ID=sup.SUPP_ID) itemsup 	"
                               +  " where pri.ITEM_ID=mas.ITEM_ID and mas.item_id=itemsup.ITEM_ID and pro.item_id= mas.item_id  and mas.Art_No = '" + upc + "')"
                    +" where  rownum <=200";
        ResultSet rs = null;
        try {
//          System.out.println(strSql);
          stmt = DAL.getConnection().createStatement(ResultSet.
              TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
          rs = DAL.executeScrollQueries(strSql, stmt);
          if (rs.next()) {
            dmPurchaseOrder.addRow(new Object[] {rs.getString("ITEM_ID"),
                                   rs.getString("art_no"),
                                   rs.getString("ITEM_NAME"),
                                   rs.getString("child_name")});
          }
          rs.close();
          stmt.close();
        }
        catch (Exception ex) {}

  }
  void setData(Vector vUPC){
    for(int i=0; i< vUPC.size();i++){
      if(!checkExistOnGrid(vUPC.get(i).toString().trim())){
        insertItemToGrid(vUPC.get(i).toString().trim());
      }
    }
 }

//  void txtQty_keyTyped(KeyEvent e) {
//    char key = e.getKeyChar();
//    ut.checkNumberUpdate(e,txtQty.getText());
//  }

  void txtUPC_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtUPC,PurchaseOrderBusObj.LEN_UPC_ID);
    ut.checkNumber(e);
  }

  void cboType_itemStateChanged(ItemEvent e) {
    if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
      cboSupplier.setEnabled(true);
      btnSupplier.setEnabled(true);
    }else{
      cboSupplier.setSelectedIndex(0);
      cboSupplier.setEnabled(false);
      btnSupplier.setEnabled(false);
    }

//    String payment_id = getPaymentID(cboType.getSelectedData());
  }

  public void txtUPC_keyPressed(KeyEvent e) {
    if(e.getKeyCode()==e.VK_ENTER){
      btnAdd.doClick();
    }
  }

  //  void txtTerm_keyTyped(KeyEvent e) {
//    ut.limitLenjTextField(e, txtTerm,15);
//  }



}

class PanelProductGroup_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelProductGroup adaptee;
  PanelProductGroup_btnSearch_actionAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelProductGroup_btnClear_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelProductGroup adaptee;
  PanelProductGroup_btnClear_actionAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelProductGroup_tblPurchaseOrder_mouseAdapter
    extends MouseAdapter {
  private PanelProductGroup adaptee;
  PanelProductGroup_tblPurchaseOrder_mouseAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblPurchaseOrder_mouseClicked(e);
  }
}

class PanelProductGroup_btnModify_actionAdapter
    implements ActionListener {
  private PanelProductGroup adaptee;
  PanelProductGroup_btnModify_actionAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelProductGroup_btnDelete_actionAdapter
    implements ActionListener {
  private PanelProductGroup adaptee;
  PanelProductGroup_btnDelete_actionAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelProductGroup_btnDone_actionAdapter
    implements ActionListener {
  private PanelProductGroup adaptee;
  PanelProductGroup_btnDone_actionAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelProductGroup_btnAdd_actionAdapter
    implements ActionListener {
  private PanelProductGroup adaptee;
  PanelProductGroup_btnAdd_actionAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

//class PanelProductGroup_btnReceiptDate_actionAdapter
//    implements ActionListener {
//  private PanelProductGroup adaptee;
//  PanelProductGroup_btnReceiptDate_actionAdapter(PanelProductGroup adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnReceiptDate_actionPerformed(e);
//  }
//}

//class PanelProductGroup_btnOrderDate_actionAdapter
//    implements ActionListener {
//  private PanelProductGroup adaptee;
//  PanelProductGroup_btnOrderDate_actionAdapter(PanelProductGroup adaptee) {
//    this.adaptee = adaptee;
//  }
//
////  public void actionPerformed(ActionEvent e) {
////    adaptee.btnOrderDate_actionPerformed(e);
////  }
//}


class PanelProductGroup_btnCancel_actionAdapter
    implements ActionListener {
  private PanelProductGroup adaptee;
  PanelProductGroup_btnCancel_actionAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelProductGroup_btnSupplier_actionAdapter implements java.awt.event.ActionListener {
  PanelProductGroup adaptee;

  PanelProductGroup_btnSupplier_actionAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplier_actionPerformed(e);
  }
}

//class PanelProductGroup_btnStore_actionAdapter implements java.awt.event.ActionListener {
//  PanelProductGroup adaptee;
//
//  PanelProductGroup_btnStore_actionAdapter(PanelProductGroup adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnStore_actionPerformed(e);
//  }
//}

class PanelProductGroup_btnUPC_actionAdapter implements java.awt.event.ActionListener {
  PanelProductGroup adaptee;

  PanelProductGroup_btnUPC_actionAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnUPC_actionPerformed(e);
  }
}

//class PanelProductGroup_txtQty_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelProductGroup adaptee;
//
//  PanelProductGroup_txtQty_keyAdapter(PanelProductGroup adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtQty_keyTyped(e);
//  }
//}

class PanelProductGroup_txtUPC_keyAdapter extends java.awt.event.KeyAdapter {
  PanelProductGroup adaptee;

  PanelProductGroup_txtUPC_keyAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }

  public void keyPressed(KeyEvent e) {
    adaptee.txtUPC_keyPressed(e);
  }
}


class PanelProductGroup_cboType_itemAdapter implements java.awt.event.ItemListener {
  PanelProductGroup adaptee;

  PanelProductGroup_cboType_itemAdapter(PanelProductGroup adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {

    adaptee.cboType_itemStateChanged(e);
  }
}
