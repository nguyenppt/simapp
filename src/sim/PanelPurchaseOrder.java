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
import javax.swing.table.TableColumn;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.Color;

/**
 * <p>Title      : Purchase Order </p>
 * <p>Description: makes an order and send it’s print to supplier </p>
 * <p>Description:  Main -> Inv Mgmt -> Puchar Order</p>
 * <p>Copyright  : Copyright (c) 2006  </p>
 * <p>Company    : BTM </p>
 * @author       : Vinh Le
 * @version 1.0
 */
public class PanelPurchaseOrder extends JPanel {

    //=============Define Constant for Table Column
    public static final int ITEM_ID = 0;
    public static final int UPC = 1;
    public static final int ITEM_NAME = 2;
    public static final int STANDARD_UOM = 3;
    public static final int PACK_SIZE = 4;
    public static final int QTY = 5;
    public static final int UNIT_COST = 6;
    public static final int CUR_BR = 7;
    public static final int CUR_FR = 8;
    public static final int numOfPrintingColumn = 7;

    boolean IS_ENTER = true;//first time press Enter
    Statement stmt = null;
    //DataAccessLayer DAL = new DataAccessLayer();
    Utils ut = new Utils();
    DataSourceVector dsPurchaseOrder;
    InitTableModel dmPurchaseOrder = new InitTableModel();
    String strUPC = "0000000000000";
    String poID = "";
    Vector vSql = new Vector();
    Vector vInfo = new Vector();
    boolean FLAG_MODIFY = false;
    CardLayout cardLayout;
    JPanel parent;

    BJPanel bJPanelPO = new BJPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    BJPanel bJPanelButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
    BJPanel bJPanelContent = new BJPanel();
    BJPanel bJPanelInfor = new BJPanel();
    BorderLayout borderLayout2 = new BorderLayout();
    FlowLayout flowLayout1 = new FlowLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    BJPanel bJPanelGrid = new BJPanel();
    BJButton btnCancel = new BJButton();
    BJButton btnDelete = new BJButton();
    BJButton btnClear = new BJButton();
    BJButton btnSearch = new BJButton();
    BJButton btnModify = new BJButton();
    BJButton btnDone = new BJButton();
    BJButton btnAdd = new BJButton();
    BJPanel bJPanel1 = new BJPanel();
    BJPanel bJPanel2 = new BJPanel();
    BJPanel bJPanel3 = new BJPanel();
    BJPanel bJPanel4 = new BJPanel();
    BJPanel bJPanel5 = new BJPanel();
    BJPanel bJPanel6 = new BJPanel();
    BJLabel lblOrderID = new BJLabel();
    FlowLayout flowLayout2 = new FlowLayout();
    BJTextField txtPOID = new BJTextField();
    BJLabel bJLabel1 = new BJLabel();
    BJLabel lblSupp = new BJLabel();
    BJComboBox cboSupplier = new BJComboBox();
    JButton btnSupplier = new JButton();
    BJComboBox cboPayment = new BJComboBox();
    BJLabel lblPayment = new BJLabel();
    JButton btnStore = new JButton();
    BJComboBox cboStore = new BJComboBox();
    BJLabel lblStore = new BJLabel();
    BJLabel bJLabel4 = new BJLabel();
    FlowLayout flowLayout3 = new FlowLayout();
    BJComboBox cboStatus = new BJComboBox();
    FlowLayout flowLayout4 = new FlowLayout();
    BJLabel bJLabel2 = new BJLabel();
    BJLabel lblStatus = new BJLabel();
    JButton btnOrderDate = new JButton();
    BJTextField txtOrderDate = new BJTextField();
    BJLabel lblOderDate = new BJLabel();
    FlowLayout flowLayout5 = new FlowLayout();
    BJLabel bJLabel3 = new BJLabel();
    BJLabel lblTerm = new BJLabel();
    BJLabel lblReceiptDate = new BJLabel();
    JButton btnReceiptDate = new JButton();
    BJTextField txtTerm = new BJTextField();
    BJTextField txtReceiptDate = new BJTextField();
    BJLabel lblTotalAmount = new BJLabel();
    BJLabel lblTotalAmountL = new BJLabel();
    BJLabel bJLabel7 = new BJLabel();
    BJLabel lblReceive = new BJLabel();
    BJComboBox cboReceive = new BJComboBox();
    FlowLayout flowLayout6 = new FlowLayout();
    FlowLayout flowLayout7 = new FlowLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    BJLabel lblDescription = new BJLabel();
    BJTextArea txtDesc = new BJTextArea();
    BJPanel bJPanel7 = new BJPanel();
    FlowLayout flowLayout8 = new FlowLayout();
    BJPanel bJPanel8 = new BJPanel();
    BJPanel bJPanel9 = new BJPanel();
    FlowLayout flowLayout9 = new FlowLayout();
    FlowLayout flowLayout10 = new FlowLayout();
    BJLabel lblUPC = new BJLabel();
    BJLabel bJLabel6 = new BJLabel();
    BJLabel lblTotalL = new BJLabel();
    BJLabel lblTotal = new BJLabel();
    BJTextField txtUPC = new BJTextField();
    JButton btnUPC = new JButton();
    JButton btnProdGroup = new JButton();
    BJLabel lblProdGroup = new BJLabel();
    BJTextField txtProdGroup = new BJTextField();
    BorderLayout borderLayout4 = new BorderLayout();
    JScrollPane jScrollPane2 = new JScrollPane();
    BJTable tblPurchaseOrder = new BJTable(dmPurchaseOrder);
    FlowLayout flowLayout11 = new FlowLayout();

    public PanelPurchaseOrder() {
        try {
            jbInit();
            showButton(true);
            setPropertyTable();
            setlang();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public PanelPurchaseOrder(FrameMainSim frmMain, CardLayout crdLayout,
                              JPanel parent) {
        try {
            this.cardLayout = crdLayout;
            this.parent = parent;
            jbInit();
            showButton(true);
            setPropertyTable();
            setlang();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        registerKeyboardActions();
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(borderLayout2);
        bJPanelPO.setLayout(borderLayout1);
        bJPanelButton.setLayout(flowLayout1);
        bJPanelContent.setLayout(borderLayout3);
        btnCancel.setPreferredSize(new Dimension(120, 37));
        btnCancel.setText("<html><center><b>Cancel</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                          "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
        btnCancel.addActionListener(new
                                    PanelPurchaseOrder_btnCancel_actionAdapter(this));
        btnDelete.setPreferredSize(new Dimension(120, 37));
        btnDelete.setText("<html><center><b>Delete</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                          "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
        btnDelete.addActionListener(new
                                    PanelPurchaseOrder_btnDelete_actionAdapter(this));
        btnClear.setPreferredSize(new Dimension(120, 37));
        btnClear.setText("<html><center><b>Clear</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                         "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
        btnClear.addActionListener(new
                                   PanelPurchaseOrder_btnClear_actionAdapter(this));
        btnSearch.setPreferredSize(new Dimension(120, 37));
        btnSearch.setText("<html><center><b>Search</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                          "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
        btnSearch.addActionListener(new
                                    PanelPurchaseOrder_btnSearch_actionAdapter(this));
        btnModify.setPreferredSize(new Dimension(120, 37));
        btnModify.setText("<html><center><b>Modify</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                          "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
        btnModify.addActionListener(new
                                    PanelPurchaseOrder_btnModify_actionAdapter(this));
        btnDone.setPreferredSize(new Dimension(120, 37));
        btnDone.setText("<html><center><b>Done</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
        btnDone.addActionListener(new PanelPurchaseOrder_btnDone_actionAdapter(this));
        btnAdd.setPreferredSize(new Dimension(120, 37));
        btnAdd.setText("<html><center><b>Add</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                       "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
        btnAdd.addActionListener(new PanelPurchaseOrder_btnAdd_actionAdapter(this));
        bJPanelInfor.setPreferredSize(new Dimension(10, 280));
        bJPanelInfor.setLayout(flowLayout11);
        bJPanel7.setLayout(flowLayout8);
        bJPanel7.setBorder(BorderFactory.createEtchedBorder());
        bJPanel7.setPreferredSize(new Dimension(790, 70));
        bJPanel5.setPreferredSize(new Dimension(770, 24));
        bJPanel5.setLayout(flowLayout2);
        bJPanel4.setPreferredSize(new Dimension(770, 24));
        bJPanel4.setLayout(flowLayout3);
        bJPanel3.setPreferredSize(new Dimension(770, 24));
        bJPanel3.setLayout(flowLayout4);
        bJPanel2.setPreferredSize(new Dimension(770, 24));
        bJPanel2.setLayout(flowLayout5);
        bJPanel1.setPreferredSize(new Dimension(770, 24));
        bJPanel1.setLayout(flowLayout6);
        bJPanel6.setPreferredSize(new Dimension(770, 46));
        bJPanel6.setLayout(flowLayout7);
        lblOrderID.setPreferredSize(new Dimension(150, 15));
        lblOrderID.setText("Oder ID:");
        flowLayout2.setAlignment(FlowLayout.LEFT);
        flowLayout2.setHgap(2);
        flowLayout2.setVgap(2);
        txtPOID.setEnabled(false);
        txtPOID.setPreferredSize(new Dimension(200, 20));
        bJLabel1.setPreferredSize(new Dimension(50, 15));
        bJLabel1.setToolTipText("");
        lblSupp.setPreferredSize(new Dimension(150, 15));
        lblSupp.setText("Supplier:");
        btnSupplier.setPreferredSize(new Dimension(23, 23));
        btnSupplier.setText("...");
        btnSupplier.addActionListener(new
            PanelPurchaseOrder_btnSupplier_actionAdapter(this));
        cboSupplier.setPreferredSize(new Dimension(176, 22));
        cboSupplier.addItemListener(new
                                    PanelPurchaseOrder_cboSupplier_itemAdapter(this));
        lblPayment.setPreferredSize(new Dimension(150, 15));
        lblPayment.setText("Payment:");
        btnStore.setPreferredSize(new Dimension(23, 23));
        btnStore.setText("...");
        btnStore.addActionListener(new
                                   PanelPurchaseOrder_btnStore_actionAdapter(this));
        lblStore.setPreferredSize(new Dimension(150, 15));
        lblStore.setText("To store:");
        flowLayout3.setAlignment(FlowLayout.LEFT);
        flowLayout3.setHgap(2);
        flowLayout3.setVgap(2);
        cboStore.setPreferredSize(new Dimension(176, 22));
        bJLabel4.setPreferredSize(new Dimension(50, 15));
        cboPayment.setPreferredSize(new Dimension(200, 22));
        flowLayout4.setAlignment(FlowLayout.LEFT);
        flowLayout4.setHgap(2);
        flowLayout4.setVgap(2);
        cboStatus.setPreferredSize(new Dimension(200, 22));
        bJLabel2.setForeground(Color.red);
        bJLabel2.setPreferredSize(new Dimension(50, 15));
        bJLabel2.setToolTipText("");
        bJLabel2.setText("(*)");
        lblStatus.setPreferredSize(new Dimension(150, 15));
        lblStatus.setText("Status:");
        btnOrderDate.setPreferredSize(new Dimension(23, 23));
        btnOrderDate.setText("...");
        btnOrderDate.addActionListener(new
            PanelPurchaseOrder_btnOrderDate_actionAdapter(this));
        lblOderDate.setPreferredSize(new Dimension(150, 15));
        lblOderDate.setText("Oder date:");
        txtOrderDate.setPreferredSize(new Dimension(176, 20));
        flowLayout5.setAlignment(FlowLayout.LEFT);
        flowLayout5.setHgap(2);
        flowLayout5.setVgap(2);
        bJLabel3.setForeground(Color.red);
        bJLabel3.setPreferredSize(new Dimension(50, 15));
        bJLabel3.setText("(*)");
        lblTerm.setPreferredSize(new Dimension(150, 15));
        lblTerm.setText("Condition:");
        lblReceiptDate.setPreferredSize(new Dimension(150, 15));
        lblReceiptDate.setText("Receipt date:");
        btnReceiptDate.setPreferredSize(new Dimension(23, 23));
        btnReceiptDate.setText("...");
        btnReceiptDate.addActionListener(new
            PanelPurchaseOrder_btnReceiptDate_actionAdapter(this));
        txtReceiptDate.setPreferredSize(new Dimension(176, 20));
        txtTerm.setPreferredSize(new Dimension(200, 20));
        txtTerm.addKeyListener(new PanelPurchaseOrder_txtTerm_keyAdapter(this));
        lblTotalAmount.setText("0.00");
        lblTotalAmountL.setPreferredSize(new Dimension(150, 15));
        lblTotalAmountL.setToolTipText("");
        lblTotalAmountL.setText("Total amount");
        bJLabel7.setPreferredSize(new Dimension(50, 15));
        lblReceive.setPreferredSize(new Dimension(150, 15));
        lblReceive.setText("Receive:");
        cboReceive.setPreferredSize(new Dimension(200, 22));
        flowLayout6.setAlignment(FlowLayout.LEFT);
        flowLayout6.setHgap(2);
        flowLayout6.setVgap(2);
        flowLayout7.setAlignment(FlowLayout.LEFT);
        flowLayout7.setHgap(2);
        flowLayout7.setVgap(2);
        jScrollPane1.setPreferredSize(new Dimension(600, 44));
        lblDescription.setPreferredSize(new Dimension(150, 15));
        lblDescription.setText("Description:");
        txtDesc.setPreferredSize(new Dimension(500, 44));
        bJPanel8.setPreferredSize(new Dimension(770, 24));
        bJPanel8.setLayout(flowLayout9);
        bJPanel9.setPreferredSize(new Dimension(770, 24));
        bJPanel9.setLayout(flowLayout10);
        flowLayout9.setAlignment(FlowLayout.LEFT);
        flowLayout9.setHgap(2);
        flowLayout9.setVgap(2);
        flowLayout10.setAlignment(FlowLayout.LEFT);
        flowLayout10.setHgap(2);
        flowLayout10.setVgap(2);
        lblUPC.setPreferredSize(new Dimension(150, 15));
        lblUPC.setText("UPC:");
        lblTotalL.setPreferredSize(new Dimension(150, 15));
        lblTotalL.setText("Total Qty:");
        lblTotal.setPreferredSize(new Dimension(200, 15));
        lblTotal.setText("0.00");
        btnUPC.setPreferredSize(new Dimension(23, 23));
        btnUPC.setText("...");
        btnUPC.addActionListener(new PanelPurchaseOrder_btnUPC_actionAdapter(this));
        txtUPC.setPreferredSize(new Dimension(176, 20));
        txtUPC.addKeyListener(new PanelPurchaseOrder_txtUPC_keyAdapter(this));
        bJLabel6.setPreferredSize(new Dimension(50, 15));
        btnProdGroup.setPreferredSize(new Dimension(23, 23));
        btnProdGroup.setText("...");
        btnProdGroup.addActionListener(new
            PanelPurchaseOrder_btnProdGroup_actionAdapter(this));
        lblProdGroup.setPreferredSize(new Dimension(150, 15));
        lblProdGroup.setText("Product group:");
        txtProdGroup.setPreferredSize(new Dimension(176, 20));
        bJPanelGrid.setLayout(borderLayout4);
        tblPurchaseOrder.addKeyListener(new
            PanelPurchaseOrder_tblPurchaseOrder_keyAdapter(this));
        tblPurchaseOrder.addMouseListener(new
            PanelPurchaseOrder_tblPurchaseOrder_mouseAdapter(this));
        this.add(bJPanelPO, java.awt.BorderLayout.CENTER);
        bJPanelPO.add(bJPanelButton, java.awt.BorderLayout.NORTH);
        bJPanelButton.add(btnDone);
        bJPanelButton.add(btnAdd);
        bJPanelButton.add(btnModify);
        bJPanelButton.add(btnSearch);
        bJPanelButton.add(btnClear);
        bJPanelButton.add(btnDelete);
        bJPanelButton.add(btnCancel);
        bJPanelPO.add(bJPanelContent, java.awt.BorderLayout.CENTER);
        bJPanelInfor.add(bJPanel5);
        bJPanel5.add(lblOrderID);
        bJPanel5.add(txtPOID);
        bJPanel5.add(bJLabel1);
        bJPanel5.add(lblSupp);
        bJPanel5.add(cboSupplier);
        bJPanel5.add(btnSupplier);
        bJPanelInfor.add(bJPanel4);
        bJPanel4.add(lblStore);
        bJPanel4.add(cboStore);
        bJPanel4.add(btnStore);
        bJPanel4.add(bJLabel4);
        bJPanel4.add(lblPayment);
        bJPanel4.add(cboPayment);
        bJPanelInfor.add(bJPanel3);
        bJPanel3.add(lblOderDate);
        bJPanel3.add(txtOrderDate);
        bJPanel3.add(btnOrderDate);
        bJPanel3.add(bJLabel2);
        bJPanel3.add(lblStatus);
        bJPanel3.add(cboStatus);
        bJPanelInfor.add(bJPanel2);
        bJPanel2.add(lblReceiptDate);
        bJPanel2.add(txtReceiptDate);
        bJPanel2.add(btnReceiptDate);
        bJPanel2.add(bJLabel3);
        bJPanel2.add(lblTerm);
        bJPanel2.add(txtTerm);
        bJPanelInfor.add(bJPanel1);
        bJPanel1.add(lblReceive);
        bJPanel1.add(cboReceive);
        bJPanel1.add(bJLabel7);
        bJPanel1.add(lblTotalL);
        bJPanel1.add(lblTotal);
        bJPanelInfor.add(bJPanel6);
        bJPanelInfor.add(bJPanel7);
        bJPanel6.add(lblDescription);
        bJPanel6.add(jScrollPane1);
        bJPanel8.add(lblUPC);
        bJPanel8.add(txtUPC);
        bJPanel8.add(btnUPC);
        bJPanel8.add(bJLabel6);
        bJPanel8.add(lblTotalAmountL);
        bJPanel8.add(lblTotalAmount);
        bJPanel9.add(lblProdGroup);
        bJPanel9.add(txtProdGroup);
        bJPanel9.add(btnProdGroup);
        bJPanel7.add(bJPanel8);
        bJPanel7.add(bJPanel9);
        jScrollPane1.getViewport().add(txtDesc);
        bJPanelContent.add(bJPanelGrid, java.awt.BorderLayout.NORTH);
        bJPanelGrid.add(jScrollPane2, java.awt.BorderLayout.CENTER);
        jScrollPane2.getViewport().add(tblPurchaseOrder);
        bJPanelContent.add(bJPanelInfor, java.awt.BorderLayout.NORTH);
        bJPanelContent.add(bJPanelGrid, java.awt.BorderLayout.CENTER);
    }

    public void initScreen() {
        poID = ut.getddMMyyhhmmssID();
        poID += ut.getCheckDigitEAN13(poID);
        txtPOID.setText(poID);
        initInfoForCombo();
    }

    private void initInfoForCombo(){
    cboStore.setData1(getStoreData());
    cboPayment.setData1(getPaymentData());
    cboSupplier.setData1(getSupplierData());
    cboSupplier.setSelectedIndex(0);
    try{
      stmt.close();
    }catch(Exception ex){}
    cboStatus.removeAllItems();
    cboStatus.addItem("Approve");
    cboStatus.addItem("Work Sheet");
    cboStatus.setSelectedIndex(1); //default : Work Sheet
    cboReceive.removeAllItems();
    cboReceive.addItem("Backroom");
//    cboReceive.addItem("Frontroom");
    cboReceive.setEnabled(false);
  }

  /**
   *
   * @return DataSourceVector
   */
   private DataSourceVector getStoreData() {
      DataSourceVector ds;
      ResultSet rs = null;
      String sqlStr = "Select Store_ID, Name From BTM_IM_STORE";
      try {
          return ds = new DataSourceVector(SystemClass.objDAL.getConnection(),
                                           sqlStr);
      }
      catch (Exception ex) {}
      return null;
  }

    private DataSourceVector getSupplierData() {
        DataSourceVector ds;
        String sqlStr =
            "Select Supp_ID, Supp_Name From BTM_IM_SUPPLIER order by supp_name asc";
        try {
            return ds = new DataSourceVector(SystemClass.objDAL.getConnection(), sqlStr);
        }
        catch (Exception ex) {}
        return null;
    }

    private DataSourceVector getPaymentData() {
        DataSourceVector ds;
        String sqlStr =
            "Select Payment_Method_ID, Payment_Method_Desc From BTM_IM_PAYMENT_METHOD";
        try {
            return ds = new DataSourceVector(SystemClass.objDAL.getConnection(), sqlStr);
        }
        catch (Exception ex) {}
        return null;
    }

    private void setPropertyTable() {
        //define for table
        String[] columnNames = new String[] {
            SystemClass.lang.getString("ItemID"),
            SystemClass.lang.getString("UPC"),
            SystemClass.lang.getString("ItemName"),
            SystemClass.lang.getString("UOM"),
            SystemClass.lang.getString("Packing"),
            SystemClass.lang.getString("Quantity"),
            SystemClass.lang.getString("UnitCost"),
            SystemClass.lang.getString("SOH")}; //"BackR","FrontR"};
        dmPurchaseOrder.setHeader(columnNames);
        tblPurchaseOrder.setColumnWidth(UPC, 110);
        tblPurchaseOrder.setColumnWidth(ITEM_NAME, 180);
        tblPurchaseOrder.setColumnWidth(STANDARD_UOM, 50);
        tblPurchaseOrder.setColumnWidth(PACK_SIZE, 50);
        tblPurchaseOrder.setColumnWidth(QTY, 60);
        tblPurchaseOrder.setColumnWidth(UNIT_COST, 60);
        tblPurchaseOrder.setColumnWidth(CUR_BR, 30);
        tblPurchaseOrder.setColor(Color.lightGray, Color.white);
        tblPurchaseOrder.setCellEditable(QTY);

        tblPurchaseOrder.getColumnModel().getColumn(ITEM_ID).setMaxWidth(0);
        tblPurchaseOrder.getColumnModel().getColumn(ITEM_ID).setMinWidth(0);
        tblPurchaseOrder.getColumnModel().getColumn(ITEM_ID).setPreferredWidth(0);
    }

    public void applyPermission() {
        EmpRight er = new EmpRight();
        er.initData(SystemClass.objDAL, SystemClass.objDAL.getEmpID());
        er.setData(SystemClass.objDAL.getEmpID(), Constant.SCRS_PURCHASE_ORDER);
        btnAdd.setEnabled(er.getAdd());
        btnSearch.setEnabled(er.getAdd());
        btnDelete.setEnabled(er.getDelete());
        btnModify.setEnabled(er.getModify());

        //  btnAdminRoleReport.setEnabled(er.getReport());
        if (!er.getAdd()) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT002"),
                           SystemClass.lang.getString("MS1085_CanNotUseScreen"));

        }
    }

    private void showButton(boolean flagSetButton) {
        btnAdd.setVisible(flagSetButton);
        btnModify.setVisible(!flagSetButton);
    }

    private void addItemIntoGrid() {
        String orderDate = txtOrderDate.getText().trim();
        String receiptDate = txtReceiptDate.getText().trim();
        String upc = txtUPC.getText().trim();

        if(upc.trim().length() == 0){
            txtUPC.requestFocus();
            return;
        }

        //check selected Store
        if (cboStore.getSelectedIndex() == 0) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString(
                "MS1165_PickUpStoreWantPlaceThisOrder"));
            cboStore.requestFocus(true);
            return;
        }
        if (!ut.checkDate(orderDate, "/") || orderDate.equals("")) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString("MS1012_OrderDateMustBeDateType"));
            txtOrderDate.requestFocus();
            txtOrderDate.setSelectionStart(0);
            txtOrderDate.setSelectionEnd(txtOrderDate.getText().length());
            return;
        }
        if (ut.compareDate(ut.getSystemDateStandard(), orderDate)) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString(
                               "MS1174_OrderDateGreaterOrEqualCurrentDate"));
            txtOrderDate.requestFocus();
            txtOrderDate.setSelectionStart(0);
            txtOrderDate.setSelectionEnd(txtOrderDate.getText().length());
            return;
        }
        if (!ut.checkDate(receiptDate, "/") || receiptDate.equals("")) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString("MS1013_ReceiptDateMustBeDateType"));
            txtReceiptDate.requestFocus();
            txtReceiptDate.setSelectionStart(0);
            txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
            return;
        }
        if (ut.compareDate(orderDate, receiptDate)) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString(
                               "MS1172_ReceiptDateGreaterOrEqualOrderDate"));
            txtReceiptDate.requestFocus();
            txtReceiptDate.setSelectionStart(0);
            txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
            return;
        }

        if (cboSupplier.getSelectedItem().toString().equalsIgnoreCase("None")) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString("MS1149_SupplierNotNoneValue"));
            cboSupplier.requestFocus(true);
            return;
        }

        if (cboReceive.getSelectedItem().toString().equalsIgnoreCase("Backroom")) {
            //Backroom
            if (upc.equals("")) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1007_UPCIsNotNull"));
                txtUPC.requestFocus();
                txtUPC.setSelectionStart(0);
                txtUPC.setSelectionEnd(txtUPC.getText().length());
                return;
            }
            if (!checkItemOrder(upc)) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1151_UPC_NotExistSupplier")
                               + cboSupplier.getSelectedItem().toString() +
                               SystemClass.lang.getString("MS1152_UPC_ItemInvalid"));
                txtUPC.requestFocus();
                txtUPC.setSelectionStart(0);
                txtUPC.setSelectionEnd(txtUPC.getText().length());
                return;
            }
            if (checkExistOnGrid(upc)) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1173_UPC_ExistedPurchaseOrder"));
                txtUPC.requestFocus();
                txtUPC.setSelectionStart(0);
                txtUPC.setSelectionEnd(txtUPC.getText().length());
                return;
            }
            addItemToGrid(upc);
        }
        else { //Frontroom
            if (txtProdGroup.getText().trim().equals("")) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1171_PGID_NotNullEnterPGID"));
                txtProdGroup.requestFocus();
                txtProdGroup.setSelectionStart(0);
                txtProdGroup.setSelectionEnd(txtProdGroup.getText().length());
                return;
            }
            if (!checkPGSupplier(txtProdGroup.getText(),
                                 cboSupplier.getSelectedData())) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString(
                                   "MS1169_ProductGroupNotCreateForSupplier")
                               + cboSupplier.getSelectedItem().toString() +
                               SystemClass.lang.getString("MS1170_ProductGroupInvalid"));
                txtProdGroup.requestFocus();
                txtProdGroup.setSelectionStart(0);
                txtProdGroup.setSelectionEnd(txtProdGroup.getText().length());
                return;
            }
            setDataForFrontroom(txtProdGroup.getText());
        }
        setObjectEnable(false);
    }

    private boolean checkPGSupplier(String pgID, String suppID) {
        ResultSet rs = null;
        String sqlStr =
            "Select PROD_GROUP_ID From BTM_IM_PROD_GROUP Where Supp_ID='"
            + suppID + "' and PROD_GROUP_ID='" + pgID + "'";
        try {
            //      System.out.println(sqlStr);
            stmt = SystemClass.objDAL.getConnection().createStatement();
            rs = SystemClass.objDAL.executeQueries(sqlStr, stmt);
            if (rs.next()) {
                stmt.close();
                return true;
            }
            stmt.close();
        }
        catch (Exception ex) {}
        return false;
    }

    private void addItemToGrid(String upc) {
        strUPC = upc;
        String strSQL = " Select mas.Item_Id,mas.Art_No UPC,mas.Item_Name,mas.Standard_Uom, mas.Pack_Size, ";
        strSQL += "1 Qty, un.new_Unit_Cost unit_cost, back_room_qty, front_room_qty ";
        strSQL += "From BTM_IM_ITEM_MASTER mas left outer join BTM_IM_ITEM_LOC_SOH soh on (soh.item_id = mas.item_id and ";
        strSQL += "soh.STORE_ID ="  +  cboStore.getSelectedData() + " ),  ";
        strSQL += "BTM_IM_UNIT_COST_HIST un ";
        strSQL += "Where mas.Item_Id=un.ITEM_ID and ";
        strSQL += "un.STORE_ID = " + cboStore.getSelectedData() + " and un.STATUS=1 and ";
        strSQL += "mas.Status='A' and mas.Art_No = '" + strUPC + "' ";
        try{
            if (dsPurchaseOrder == null) {
                String strSQLTemp = "";
                strSQLTemp = "Select '' Item_Id, '' UPC, '' Item_Name, '' Standard_Uom, ";
                strSQLTemp += " '' Pack_Size, '' Qty, '' unit_cost, ";
                strSQLTemp += " '' back_room_qty, '' front_room_qty ";
                strSQLTemp += " From dual where 1 = -1 ";
                dsPurchaseOrder = new DataSourceVector(SystemClass.objDAL.getConnection(),strSQLTemp);
                dsPurchaseOrder.setColumnsAreNum( new int[]{QTY});
            }
            if(strUPC!=null && strUPC.trim().length()>0){
                if (checkDupplicateUPC(strUPC) == -1) {
                    ResultSet rsItem = null;
                    Statement stmt = SystemClass.objDAL.getConnection().
                        createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    rsItem = stmt.executeQuery(strSQL);
                    int dem = 0;
                    if (rsItem != null) {
                        rsItem.beforeFirst();
                        while (rsItem.next()) {
                            Vector cols = new Vector();
                            for (int col = 1;
                                 col <= rsItem.getMetaData().getColumnCount();
                                 col++) {
                                if(rsItem.getObject(col)!=null && rsItem.getObject(col).toString().trim().length()>0){
                                    cols.add(rsItem.getObject(col));
                                }else{
                                    cols.add(new String(" "));
                                }

                            }
                            dsPurchaseOrder.addNewRow(cols);
                            dem++;
                        }
                    }
                    if (dem == 0) {
                        ut.showMessage(SystemClass.frmMainSim,
                                       SystemClass.lang.getString("TT001"),
                                       SystemClass.lang.getString(
                            "MS319_ItemNotExist"));
                    }
                    stmt.close();
                    dmPurchaseOrder.setDataSourceVector(dsPurchaseOrder);
                    setPropertyTable();

                }else{//in case item is existed
                    ut.showMessage(SystemClass.frmMainSim,
                                   SystemClass.lang.getString("TT001"),
                                   SystemClass.lang.getString("MS319_ItemNotExist"));
                }
            }
        }catch(Exception ex){
            ut.showMessage(SystemClass.frmMainSim,
               SystemClass.lang.getString("TT001"),
               ex.getMessage());
        }
        txtUPC.setText("");
    }

    /**
     * check duplicate UPC
     * @return int
     */
    private int checkDupplicateUPC(String upc) {
        int index = -1;
        try {
            if (dsPurchaseOrder != null) {
                int sizeofData = dsPurchaseOrder.getNumberOfRows();
                for (int row = 0; row < sizeofData; row++) {
                    if (dsPurchaseOrder.getValueAt(row,
                                           UPC).toString().equals(new
                        String(upc.trim()))) {
                        return row;
                    }
                }
            }
        }
        catch (Exception ex) {
            ut.showMessage(SystemClass.frmMainSim,
                           SystemClass.lang.getString("TT001"), ex.getMessage());
        }
        return index;
    }


    private void setObjectEnable(boolean flag) {
        cboStore.setEnabled(flag);
        btnStore.setEnabled(flag);
        cboSupplier.setEnabled(flag);
        btnSupplier.setEnabled(flag);
    }

    private void resetData() {
        txtPOID.setText(poID);
        cboStore.setSelectedIndex(0);
        cboSupplier.setSelectedIndex(0);
        cboStatus.setSelectedIndex(1); //default: Work Sheet
        txtOrderDate.setText("");
        txtReceiptDate.setText("");
        txtTerm.setText("");
        txtDesc.setText("");
        txtUPC.setText("");
        lblTotal.setText("0.00");
        lblTotalAmount.setText("0.00");
        setObjectEnable(true);
        if(dsPurchaseOrder != null){
            dsPurchaseOrder.getData().clear();
            dmPurchaseOrder.setDataSourceVector(dsPurchaseOrder);
        }
    }

    private boolean checkItemOrder(String upc) {
        ResultSet rs = null;
        String sqlStr =
            "Select mas.Art_No From BTM_IM_ITEM_MASTER mas, BTM_IM_ITEM_LOC_SUPPLIER sup "
            +
            " Where mas.Item_ID = sup.Item_ID and mas.Status='A' and sup.Supp_ID ='"
            + cboSupplier.getSelectedData() + "' and mas.Art_No='" + upc + "'";
        try {
            stmt = SystemClass.objDAL.getConnection().createStatement();
            rs = SystemClass.objDAL.executeQueries(sqlStr, stmt);
            if (rs.next()) {
                stmt.close();
                return true;
            }
            stmt.close();
        }
        catch (Exception ex) {}
        return false;
    }

    private boolean checkExistOnGrid(String upc) {
        for(int i=0;i < dmPurchaseOrder.getRowCount();i++){
          if(dmPurchaseOrder.getValueAt(i,1).toString().equalsIgnoreCase(upc.trim()))
            return true;
        }
    return false;
    }

    private void setSQLsForVector() {
        try{
            String strSql, itemID, qty, unitCost;
            String poID, storeID, orderDate, receiptDate,
                suppID, paymentID, status, term, desc, receiveType;
            poID = txtPOID.getText().trim();
            storeID = cboStore.getSelectedData();
            orderDate = txtOrderDate.getText();
            receiptDate = txtReceiptDate.getText();
            suppID = cboSupplier.getSelectedData();
            paymentID = cboPayment.getSelectedData();
            term = txtTerm.getText();
            desc = txtDesc.getText();
            status = cboStatus.getSelectedItem().toString().substring(0, 1);
            receiveType = cboReceive.getSelectedItem().toString().substring(0,
                1);
            String createDate = ut.getSystemDate(1);
            String createBy = SystemClass.objDAL.getEmpID();
            String currID = SystemClass.objDAL.getCurrencyID();

            strSql = "insert into BTM_IM_INV_ORDER (ORDER_ID, STORE_ID, STORE_ROLE, SOURCE_ID, SOURCE_ROLE, SUPP_ID, "
                + "STATUS, NOT_BEFORE_DATE, NOT_AFTER_DATE, EARLIEST_SHIP_DATE, LASTEST_SHIP_DATE, CREATED_DATE, "
                + "ORDERED_DATE, RECEIVED_DATE, COMPLETED_DATE, CLOSED_DATE, PAYMENT_METHOD, TERMS, CURR_ID, "
                + "CREATED_BY,COMMENT_DESC,FR_BR_FLAG) values ('" + poID +
                "','" +
                storeID + "','D','" + storeID + "','D','"
                + suppID + "','" + status +
                "','7/Jul/7777','7/Jul/7777','7/Jul/7777','7/Jul/7777'," +
                "to_date('"
                + createDate + "','DD/MM/YYYY'),to_date('" + orderDate +
                "','DD/MM/YYYY'),to_date('" + receiptDate
                + "','DD/MM/YYYY'),'7/Jul/7777','7/Jul/7777','" + paymentID +
                "','" +
                term + "','" + currID + "','"
                + createBy + "','" + desc + "','" + receiveType + "')";
            vSql.addElement(strSql);

            int rowCout = 0;
            rowCout = dsPurchaseOrder.getNumberOfRows();
            for (int i = 0; i < rowCout; i++) {
                itemID = dsPurchaseOrder.getValueAt(i, ITEM_ID).toString();
                qty = dsPurchaseOrder.getValueAt(i, QTY).toString();
                unitCost = dsPurchaseOrder.getValueAt(i, UNIT_COST).toString();
                strSql = "insert into BTM_IM_INV_ORDER_ITEM (ORDER_ID, ITEM_ID, QTY_EXPECTED, QTY_RECEIVED, UNIT_COST) values ('"
                    + poID + "','" + itemID + "'," + qty + ",0," + unitCost +
                    ")";
                vSql.addElement(strSql);
            }
        }catch(Exception ex){
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"), ex.getMessage());
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
            "Select VAT from BTM_IM_ITEM_LOC_SUPPLIER where ITEM_ID = '" +
            ItemID + "'";

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

    private void printReceipt(String PPOID, String sOrderDay, String sReceiptDay,
                      String StoreID, String StoreName, String SupplierID,
                      BJTable tbl,
                      DataAccessLayer DAL) {
        String strHeader[][] = {
            {
            "So DDH:" + PPOID}
        };
        String strTableTitle[] = {
            "Ma Vach", "Ten Mat Hang", "DVT", "VAT", "SL Dat", "Gia Mua",
            "Tong Tien"
        };
        String strTableAlign[] = {
            "CENTER", "LEFT", "CENTER", "CENTER", "CENTER", "RIGHT", "RIGHT"};
        int iTableSkew[] = {
            15, 36, 7, 7, 7, 11, 17}; //15 sum = 100%
        String strFormat[] = {
            "String", "String", "String", "String", "String", "double",
            "double"
        };
        String OrderFormLeft[] = {
            DAL.getCompanyName(),
            DAL.getCompanyAdr(),
            "Tel:" + DAL.getCompanyTel(), "Fax:" + DAL.getCompanyFax(),
            "VAT:" + DAL.getCompanyVAT(), "Ngay dat hang:" + sOrderDay,
            "Ngay giao hang:" + sReceiptDay,
//       "D/C giao hang:" + DAL.getAddrDelivery(),
            "D/C giao hang:" +
            SystemClass.frmMainSim.storeBusObj.getStoreAddress1(StoreID, DAL),
            "D/C hoa don:" + DAL.getAddrBill()
        };
        String strTemp[] = getSupllierInfo(SupplierID, DAL);
        String OrderFormRight[] = {
            "Ten NCC: " + strTemp[0], "Ma NCC: " + strTemp[1],
            "Adr: " + strTemp[2],
            "VAT: " + strTemp[3], "Tel: " + strTemp[4] + "  Fax: " + strTemp[5]
        };
        String OrderFormTitle[] = {
            "DON DAT HANG", "Store " + StoreName};
        String OrderFormInfoTop[] = {
            "Vui long kiem tra thong tin san pham tren don hang truoc khi tien hanh giao hang cho cong ty chung toi:",
            "*** LUU Y: 1-GIAO HANG THEO DIA CHI GIAO HANG GHI TREN DON DAT HANG.",
            "           2-VUI LONG GIAO DUNG SO LUONG THEO DON DAT HANG. CHUNG TOI SE KHONG NHAN BAT KY SO LUONG PHAT SINH NAO."};
        String OrderFormInfoBottom[] = {
            ""};
        int OrderFormInfoSkew[] = {
            40, 20, 40};
        int[] iTotal = {
            6};
        PrintReceipt pp = new PrintReceipt();
        pp.setFileName("./file/" + PPOID + ".pdf");

        String strTableItem[][] = new String[tbl.getRowCount()][
            numOfPrintingColumn]; //omit column BR,FR
        int i;
        double iTongTienVAT = 0;
        double iTongTienSauVAT = 0;
        //Insert data of table to matrix
        for (i = 0; i < tbl.getRowCount(); i++) {
            if (tbl.getValueAt(i, UPC) != null) {
                strTableItem[i][0] = tbl.getValueAt(i, UPC).toString();
            }
            else {
                strTableItem[i][0] = "";
            }
            //Item name
            if (tbl.getValueAt(i, ITEM_NAME) != null) {
                strTableItem[i][1] = tbl.getValueAt(i, ITEM_NAME).toString();
            }
            else {
                strTableItem[i][1] = "";
            }
            //Standard UOM
            if (tbl.getValueAt(i, STANDARD_UOM) != null) {
                strTableItem[i][2] = tbl.getValueAt(i, STANDARD_UOM).toString();
            }
            else {
                strTableItem[i][2] = "";
            }
            //VAT
            strTableItem[i][3] = getVAT(tbl.getValueAt(i, ITEM_ID).toString(),
                                        DAL) + "%";
            //Qty
            if (tbl.getValueAt(i, QTY) != null) {
                strTableItem[i][4] = tbl.getValueAt(i, QTY).toString();
            }
            else {
                strTableItem[i][4] = "";
            }
            //Unit cost
            if (tbl.getValueAt(i, UNIT_COST) != null) {
                strTableItem[i][5] = tbl.getValueAt(i, UNIT_COST).toString();
            }
            else {
                strTableItem[i][5] = "";
            }
            //Total price
            double dTotal = 0;
            if (!strTableItem[i][4].equals("") &&
                !tbl.getValueAt(i, UNIT_COST).equals("")) {
                dTotal = Double.parseDouble(tbl.getValueAt(i, UNIT_COST).
                                            toString()) *
                    Double.parseDouble(tbl.getValueAt(i, QTY).toString());
                strTableItem[i][6] = "" + dTotal;

                iTongTienVAT += dTotal *
                    Double.parseDouble(getVAT(tbl.getValueAt(i, ITEM_ID).
                                              toString(), DAL)) / 100;
                iTongTienSauVAT += dTotal +
                    dTotal *
                    Double.parseDouble(getVAT(tbl.getValueAt(i, ITEM_ID).
                                              toString(), DAL)) / 100;
            }
        }
        //Add VAT
        pp.setVATLabelFirst("VAT");
        pp.setVATTextFirst(iTongTienVAT + "");
        pp.setVATLabelSecond("Tong So Tien Sau (VAT)");
        pp.setVATTextSecond(iTongTienSauVAT + "");
        //Add table item
        pp.setBarcode(PPOID);
        pp.setHeader(strHeader);
        pp.setPageString("Trang:", "/");
        pp.setTableItem(strTableItem);
        pp.setOrderFormLeft(OrderFormLeft);
        pp.setOrderFormRight(OrderFormRight);
        pp.setOrderFormTitle(OrderFormTitle);
        pp.setOrderFormInfoTop(OrderFormInfoTop);
        pp.setOrderFormInfoBottom(OrderFormInfoBottom);
        //Add Table

        pp.setTableItem(strTableItem);
        pp.setTableTitle(strTableTitle);

        //Add format for document
        pp.setTotalName("Tong So Tien");
        pp.setLeftSign("Nguoi dat hang");
        pp.setRightSign("Quan ly kho");

        pp.setOrderFormBorderTable(new Color(0, 0, 0));
        pp.setFormatColumn(strFormat);
        pp.setRowsTotal(iTotal);
        pp.setIsViewTotal(true);
        pp.setOrderFormInfoSkew(OrderFormInfoSkew);
        pp.setTableSkew(iTableSkew);
        pp.setTableAlign(strTableAlign);

        //Add info
        pp.setShowVAT(true);
        pp.setPrintOrderForm(true);
        pp.setShowDialog(true);

        pp.setDeleteFileAfterPrint(true);
        pp.setMainFrame(SystemClass.frmMainSim);
        pp.print();
    }

    private boolean checkQtyNull() {
        for (int i = 0; i < tblPurchaseOrder.getRowCount(); i++) {
            if (tblPurchaseOrder.getValueAt(i, QTY).equals("")) {
                return true;
            }
        }
        return false;
    }

    private void returnPreviousScreen(){
      resetData();
      vSql.removeAllElements();
      showButton(true);
      FLAG_MODIFY = false;
      txtUPC.setEnabled(true);
      btnUPC.setEnabled(true);
      SystemClass.frmMainSim.showScreen(Constant.SCRS_MAIN_SIM);
      SystemClass.frmMainSim.setTitle(SystemClass.lang.getString("TT0002"));
      SystemClass.frmMainSim.pnlMainSim.btnItemLookup.requestFocus(true);
 }

    private void updateValueFromSelecledItemInTable() {
        int row = tblPurchaseOrder.getSelectedRow();
        if (row >= 0) {
            txtUPC.setText(tblPurchaseOrder.getValueAt(row, UPC).toString());
            showButton(false); //show Modify; hide Add
            FLAG_MODIFY = true; // allow hit F4 to modify
            txtUPC.setEnabled(false);
            btnUPC.setEnabled(false);
        }
    }

    private boolean checkExistOnGridModify(String promoName) {
        int rowCount = 0;
        rowCount = dsPurchaseOrder.getNumberOfRows();
        for (int i = 0; i < rowCount; i++) {
            if (i != tblPurchaseOrder.getSelectedRow() &&
                dmPurchaseOrder.getValueAt(i,
                UPC).toString().equals(promoName.trim()))
                return true;
        }
        return false;
    }

    private String getPaymentID(String suppID) {
        String paymentID = "";
        if(suppID!=null && suppID!=""){
            ResultSet rs = null;
            String sqlStr =
                "Select Payment_Method_ID From BTM_IM_SUPPLIER Where Supp_ID='" +
                suppID + "'";
            try {
                stmt = SystemClass.objDAL.getConnection().createStatement();
                rs = SystemClass.objDAL.executeQueries(sqlStr, stmt);
                if (rs.next()) {
                    paymentID = rs.getString("PAYMENT_METHOD_ID");
                }
                stmt.close();
                rs.close();
            }
            catch (Exception ex) {}
        }
        return paymentID;
    }

    private void updateTotal() {
        double total = 0;
        double totalAmount = 0;
        try {
            if (dsPurchaseOrder != null) {
                int rows = dsPurchaseOrder.getNumberOfRows();
                for (int i = 0; i < rows; i++) {
                    total +=
                        Double.parseDouble(dsPurchaseOrder.getValueAt(i, QTY).
                                           toString());
                    totalAmount +=
                        Double.parseDouble(dsPurchaseOrder.getValueAt(i, UNIT_COST).
                                           toString()) * Double.parseDouble(dsPurchaseOrder.getValueAt(i, QTY).
                                           toString());
                }
            }
        }
        catch (Exception ex) {
            ut.showMessage(SystemClass.frmMainSim,
                           SystemClass.lang.getString("TT001"), ex.getMessage());
        }
        lblTotal.setText(ut.formatNumberTo2DigitsDecimal(total));
        lblTotalAmount.setText(ut.formatNumberTo2DigitsDecimal(totalAmount));
    }

    private void insertItemToGrid(String upc) {
        addItemToGrid(upc);
    }

    public void setData(Vector vUPC) {
        for (int i = 0; i < vUPC.size(); i++) {
            if (!checkExistOnGrid(vUPC.get(i).toString().trim())) {
                insertItemToGrid(vUPC.get(i).toString().trim());
            }
        }
        if (cboStore.isEnabled() == true) {
            setObjectEnable(false);
        }
        updateTotal();
        txtUPC.setText("");
    }

    private void setDataForFrontroom(String pgID) {
        String strSql = " Select mas.Item_Id,mas.Art_No UPC,mas.Item_Name,mas.Standard_Uom,mas.Pack_Size,1 Qty,un.New_Unit_Cost unit_cost,BACK_ROOM_QTY, FRONT_ROOM_QTY "
            + "    From BTM_IM_PROD_GROUP_ITEM pg,BTM_IM_ITEM_MASTER mas left outer join BTM_IM_ITEM_LOC_SOH soh on (soh.item_id=mas.item_id and soh.STORE_ID=" +
            cboStore.getSelectedData() + "),  "
            + "		 BTM_IM_UNIT_COST_HIST un "
            + "    where pg.ITEM_ID= mas.ITEM_ID and mas.Item_Id=un.ITEM_ID  "
            + " and un.STORE_ID= '" + cboStore.getSelectedData() + "'"
            + "	and un.status = 1 and mas.Status='A'  "
            + "	 and PROD_GROUP_ID = '" + pgID + "' order by pg.seq ";

        try {
            if (dsPurchaseOrder == null) {
                String strSQLTemp = "";
                strSQLTemp =
                    "Select '' Item_Id, '' UPC, '' Item_Name, '' Standard_Uom, ";
                strSQLTemp += " '' Pack_Size, '' Qty, '' unit_cost, ";
                strSQLTemp += " '' back_room_qty, '' front_room_qty ";
                strSQLTemp += " From dual where 1 = -1 ";
                dsPurchaseOrder = new DataSourceVector(SystemClass.objDAL.
                    getConnection(), strSQLTemp);
                dsPurchaseOrder.setColumnsAreNum(new int[] {QTY});
            }
            if (strUPC != null && strUPC.trim().length() > 0) {
                if (checkDupplicateUPC(strUPC) > -1) {
                    ResultSet rsItem = null;
                    Statement stmt = SystemClass.objDAL.getConnection().
                        createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    rsItem = stmt.executeQuery(strSql);
                    int dem = 0;
                    if (rsItem != null) {
                        rsItem.beforeFirst();
                        while (rsItem.next()) {
                            Vector cols = new Vector();
                            for (int col = 1;
                                 col <= rsItem.getMetaData().getColumnCount();
                                 col++) {
                                cols.add(rsItem.getObject(col));
                            }
                            dsPurchaseOrder.addNewRow(cols);
                            dem++;
                        }
                    }
                    if (dem == 0) {
                        ut.showMessage(SystemClass.frmMainSim,
                                       SystemClass.lang.getString("TT001"),
                                       SystemClass.lang.getString(
                                           "MS319_ItemNotExist"));
                    }
                    stmt.close();
                    dmPurchaseOrder.setDataSourceVector(dsPurchaseOrder);
                    setPropertyTable();

                }
                else { //in case item is existed
                    ut.showMessage(SystemClass.frmMainSim,
                                   SystemClass.lang.getString("TT001"),
                                   SystemClass.lang.getString("MS319_ItemNotExist"));
                }
            }
        }
        catch (Exception ex) {
            ut.showMessage(SystemClass.frmMainSim,
                           SystemClass.lang.getString("TT001"),
                           ex.getMessage());
        }

    }

    private void setlang(){
        btnModify.setText("<html><center><b>"+SystemClass.lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
        btnCancel.setText("<html><center><b>"+SystemClass.lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
        btnDelete.setText("<html><center><b>"+SystemClass.lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
        btnClear.setText("<html><center><b>"+SystemClass.lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
        btnAdd.setText("<html><center><b>"+SystemClass.lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                   "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
        btnDone.setText("<html><center><b>"+SystemClass.lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
        lblDescription.setText(SystemClass.lang.getString("Description")+":");
        lblOderDate.setText(SystemClass.lang.getString("OrderDate")+":");
        lblOrderID.setText(SystemClass.lang.getString("PO_ID")+":");
        lblPayment.setText(SystemClass.lang.getString("Payment")+":");
        lblProdGroup.setText(SystemClass.lang.getString("ProGroup")+":");
        lblReceiptDate.setText(SystemClass.lang.getString("ReceiptDate")+":");
        lblReceive.setText(SystemClass.lang.getString("Receive")+":");
        lblStatus.setText(SystemClass.lang.getString("Status")+":");
        lblStore.setText(SystemClass.lang.getString("ToStore")+":");
        lblSupp.setText(SystemClass.lang.getString("SupplierName")+":");
        lblTerm.setText(SystemClass.lang.getString("Term")+":");
        lblTotalL.setText(SystemClass.lang.getString("TotalQty")+":");
        lblTotalAmountL.setText(SystemClass.lang.getString("TotalAmount")+":");
        lblUPC.setText(SystemClass.lang.getString("UPC")+":");
    }

    /**
     * Function process key press
     */
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

    /**
     *
     * <p>Title: </p>
     * <p>Description: Class process key press</p>
     * <p>Copyright: Copyright (c) 2005</p>
     * <p>Company: </p>
     * @author not attributable
     * @version 1.0
     */
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
            else if (identifier.intValue() == KeyEvent.VK_F4) {
                btnModify.doClick();
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


    //Events///////////////////////////////////////////
    public void btnDone_actionPerformed(ActionEvent e) {
        String orderDate = txtOrderDate.getText().trim();
        String receiptDate = txtReceiptDate.getText().trim();
        if (tblPurchaseOrder.getRowCount() > 0) {
            if (!ut.checkDate(orderDate, "/") || orderDate.equals("")) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1012_OrderDateMustBeDateType"));
                txtOrderDate.requestFocus();
                txtOrderDate.setSelectionStart(0);
                txtOrderDate.setSelectionEnd(txtOrderDate.getText().length());
                return;
            }
            if (ut.compareDate(ut.getSystemDateStandard(), orderDate)) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1174_OrderDateGreaterOrEqualCurrentDate"));
                txtOrderDate.requestFocus();
                txtOrderDate.setSelectionStart(0);
                txtOrderDate.setSelectionEnd(txtOrderDate.getText().length());
                return;
            }
            if (!ut.checkDate(receiptDate, "/") || receiptDate.equals("")) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1013_ReceiptDateMustBeDateType"));
                txtReceiptDate.requestFocus();
                txtReceiptDate.setSelectionStart(0);
                txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
                return;
            }
            if (ut.compareDate(orderDate, receiptDate)) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1172_ReceiptDateGreaterOrEqualOrderDate"));
                txtReceiptDate.requestFocus();
                txtReceiptDate.setSelectionStart(0);
                txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
                return;
            }
            if (checkQtyNull()) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1167_QuantityNotNULL"));
                tblPurchaseOrder.requestFocus(true);
                return;
            }
            setSQLsForVector();
            if (vSql.isEmpty() == false) {
                try {
                    stmt = SystemClass.objDAL.getConnection().createStatement(ResultSet.
                        TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                    SystemClass.objDAL.executeBatchQuery(vSql, stmt);

                    if (cboStatus.getSelectedItem().toString().equalsIgnoreCase(
                        "Approve")) { //print PO if Status is "Approve"
                        printReceipt(txtPOID.getText(), txtOrderDate.getText(),
                                     txtReceiptDate.getText(),
                                     cboStore.getSelectedData().toString(),
                                     cboStore.getSelectedItem().toString(),
                                     cboSupplier.getSelectedData().toString(),
                                     tblPurchaseOrder, SystemClass.objDAL);
                    }

                    stmt.close();
                }
                catch (Exception ex) {}
                ;
            }
        }
        returnPreviousScreen();

    }

    public void btnAdd_actionPerformed(ActionEvent e) {
        addItemIntoGrid();
    }

    public void btnModify_actionPerformed(ActionEvent e) {
        double qty = 1;
        int row = tblPurchaseOrder.getSelectedRow();
    //      if(txtQty.getText().trim().equals("")){
    //        ut.showMessage(frmMain, lang.getString("TT001"),
    //                       "The Quantity can not null." );
    //        txtQty.requestFocus();
    //        return;
    //      }

    //      double qty = Double.parseDouble(txtQty.getText());
    //      if(qty <= 0){
    //        ut.showMessage(frmMain, lang.getString("TT001"),
    //                       "The Quantity can not less than or equal 0." );
    //        txtQty.requestFocus();
    //        txtQty.setSelectionStart(0);
    //        txtQty.setSelectionEnd(txtQty.getText().length());
    //        return;
    //      }
        tblPurchaseOrder.setValueAt(ut.formatNumberTo2DigitsDecimal(qty), row, QTY);
        updateTotal();
        txtUPC.setText("");
        if (cboReceive.getSelectedItem().toString().equalsIgnoreCase("Backroom")) {
    //        txtUPC.setEnabled(true);
    //        btnUPC.setEnabled(true);
        }
        showButton(true); //show Add, hide Modify
        FLAG_MODIFY = false; // not allow to modify
        tblPurchaseOrder.requestFocus(true);
    }

    public void btnSearch_actionPerformed(ActionEvent e) {
        PurchaseOrderBusObj poBusObject = new PurchaseOrderBusObj();
        DialogPOSearch dlgPOSearch = new DialogPOSearch(SystemClass.frmMainSim,
            SystemClass.lang.getString("TT0103"), true, SystemClass.frmMainSim, poBusObject);
        dlgPOSearch.setVisible(true);
        if (dlgPOSearch.done) {
            SystemClass.frmMainSim.showScreen(Constant.SCRS_PURCHASE_ORDER_MODIFY);
        }
        else {
            SystemClass.frmMainSim.showScreen(Constant.SCRS_PURCHASE_ORDER);
        }
    }

    public void btnClear_actionPerformed(ActionEvent e) {
        resetData();
        showButton(true);
    }

    public void btnDelete_actionPerformed(ActionEvent e) {
        int row = tblPurchaseOrder.getSelectedRow();
        if (row >= 0) {
            int choose = ut.showMessage(SystemClass.frmMainSim,
                                        SystemClass.lang.getString("TT023"),
                                        SystemClass.lang.getString(
                "MS1076_DeleteUPC") +
                                        tblPurchaseOrder.getValueAt(row, UPC).
                                        toString() + "' ?", 3,
                                        DialogMessage.YES_NO_OPTION);
            if (choose == DialogMessage.YES_VALUE) {
                strUPC = tblPurchaseOrder.getValueAt(row, UPC).toString();
                dsPurchaseOrder.removeRowAt(checkDupplicateUPC(tblPurchaseOrder.getValueAt(row, UPC).toString()));
                dmPurchaseOrder.setDataSourceVector(dsPurchaseOrder);
                updateTotal();
                showButton(true);
                if (tblPurchaseOrder.getRowCount() == 0) {
                    setObjectEnable(true);
                }
            }
        }
        else {
            if (tblPurchaseOrder.getRowCount() > 0) {
                ut.showMessage(SystemClass.frmMainSim,
                               SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString(
                    "MS1077_ChooseUPCWantToDelete"));
                return;
            }
        }
    }

    public void btnCancel_actionPerformed(ActionEvent e) {
        returnPreviousScreen();
    }

    public void btnOrderDate_actionPerformed(ActionEvent actionEvent) {
        int posX, posY, posXFrame, posYFrame;
        posXFrame = SystemClass.frmMainSim.getX();
        posYFrame = SystemClass.frmMainSim.getY();
        posX = this.btnOrderDate.getY() + posYFrame + 90;
        posY = this.btnReceiptDate.getY() + posYFrame + 90;
        TimeDlg startdate = new TimeDlg(null);
        startdate.setTitle(SystemClass.lang.getString("ChooseOrderDate"));
        startdate.setResizable(false);
        startdate.setLocation(posX, posY);
        startdate.setVisible(true);
        if (startdate.isOKPressed()) {
            java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
                "dd/MM/yyyy");
            String date = fd.format(startdate.getDate());
            this.txtOrderDate.setText(date);
        }
    }

    public void btnReceiptDate_actionPerformed(ActionEvent actionEvent) {
        int posX, posY, posXFrame, posYFrame;
        posXFrame = SystemClass.frmMainSim.getX();
        posYFrame = SystemClass.frmMainSim.getY();
        posX = this.btnOrderDate.getY() + posYFrame + 90;
        posY = this.btnReceiptDate.getY() + posYFrame + 90;
        TimeDlg startdate = new TimeDlg(null);
        startdate.setTitle(SystemClass.lang.getString("ChooseReceiptDate"));
        startdate.setResizable(false);
        startdate.setLocation(posX, posY);
        startdate.setVisible(true);
        if (startdate.isOKPressed()) {
            java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
                "dd/MM/yyyy");
            String date = fd.format(startdate.getDate());
            this.txtReceiptDate.setText(date);
        }

    }

    public void btnSupplier_actionPerformed(ActionEvent actionEvent) {
        SupplierBusObj suppBusObj = new SupplierBusObj();
        DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(SystemClass.frmMainSim,SystemClass.lang.getString("TT0003"),true,SystemClass.frmMainSim,suppBusObj);
        dlgSupplierSearch.setVisible(true);
        if (dlgSupplierSearch.done){
          cboSupplier.setSelectedItem(dlgSupplierSearch.getSuppName());
          String payment_id = getPaymentID(cboSupplier.getSelectedData());
          cboPayment.setSelectedData(payment_id);
       }
    }

    public void btnStore_actionPerformed(ActionEvent actionEvent) {
        DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(SystemClass.
            frmMainSim,
            SystemClass.lang.getString("TT0004"), true, SystemClass.frmMainSim,
            SystemClass.frmMainSim.storeBusObj);
        dlgStoreSearch.setVisible(true);
        if (dlgStoreSearch.done) {
            cboStore.setSelectedData(dlgStoreSearch.getData());
        }
    }

    public void btnUPC_actionPerformed(ActionEvent actionEvent) {
        if (cboStore.getSelectedIndex() == 0) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString(
                "MS1165_PickUpStoreWantPlaceThisOrder"));
            cboStore.requestFocus(true);
            return;
        }
        if (cboSupplier.getSelectedItem().toString().equalsIgnoreCase("None")) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString("MS1149_SupplierNotNoneValue"));
            cboSupplier.requestFocus(true);
            return;
        }

        btm.business.ItemBusObject itemBusObject = new btm.business.
            ItemBusObject();
        DialogItemLookup dlgItemLookup = new DialogItemLookup(SystemClass.frmMainSim,
            SystemClass.lang.getString("TT0005"), true, SystemClass.frmMainSim, itemBusObject);
        dlgItemLookup.txtSupplier.setText(cboSupplier.getSelectedData());
        dlgItemLookup.txtSupplier.setEnabled(false);
        dlgItemLookup.txtSupplieName.setText(cboSupplier.getSelectedItem().
                                             toString());
        dlgItemLookup.txtSupplieName.setEnabled(false);
        dlgItemLookup.cboStatus.setSelectedItem("Approve");
        dlgItemLookup.cboStatus.setEnabled(false);
        dlgItemLookup.setVisible(true);
        if (dlgItemLookup.done) {
            if (dlgItemLookup.getUPCList().isEmpty()) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString(
                    "MS1079_ItemNotSelectedOrNotApproved"));
                return;
            }
            setData(dlgItemLookup.getUPCList());
        }

    }

    public void txtUPC_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e, txtUPC,PurchaseOrderBusObj.LEN_UPC_ID);
        ut.checkNumber(e);
    }

    public void cboSupplier_itemStateChanged(ItemEvent e) {
        String payment_id = getPaymentID(cboSupplier.getSelectedData());
        cboPayment.setSelectedData(payment_id);
    }

    public void txtTerm_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e, txtTerm,15);
    }

    public void btnProdGroup_actionPerformed(ActionEvent actionEvent) {
        if (cboStore.getSelectedIndex() == 0) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString("MS1165_PickUpStoreWantPlaceThisOrder"));
            cboStore.requestFocus(true);
            return;
        }
        if (cboSupplier.getSelectedItem().toString().equalsIgnoreCase("None")) {
            ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString("MS1149_SupplierNotNoneValue"));
            cboSupplier.requestFocus(true);
            return;
        }

        btm.business.ProdGroupBusObj prdGrpBusObject = new btm.business.
            ProdGroupBusObj();
        DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(
            SystemClass.frmMainSim, SystemClass.lang.getString("TT0006"), true, SystemClass.frmMainSim, prdGrpBusObject);
        dlgProdGroupSearch.txtSupplier.setText(cboSupplier.getSelectedItem().
                                               toString());
        dlgProdGroupSearch.txtSupplier.setEnabled(false);
        dlgProdGroupSearch.btnSupplier.setEnabled(false);
        dlgProdGroupSearch.cboType.setSelectedItem("PO");
        dlgProdGroupSearch.cboType.setEnabled(false);
        SystemClass.frmMainSim.pnlProdGroupDetail.SCREEN_FLAG = SystemClass.frmMainSim.pnlProdGroupDetail.
            PO_SETUP;
        dlgProdGroupSearch.setVisible(true);
        if (dlgProdGroupSearch.done) {
            if (dlgProdGroupSearch.getPGID().equals("")) {
                ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                               SystemClass.lang.getString("MS1079_ItemNotSelectedOrNotApproved"));
                return;
            }
            SystemClass.frmMainSim.pnlProdGroupDetail.setDataDetail(dlgProdGroupSearch.getPGID());
            SystemClass.frmMainSim.showScreen(Constant.SCRS_PROD_GROUP_DETAIL);
        }
        else {
            SystemClass.frmMainSim.showScreen(Constant.SCRS_PURCHASE_ORDER);
        }

    }

    public void tblPurchaseOrder_keyPressed(KeyEvent e) {

    }

    public void tblPurchaseOrder_mouseClicked(MouseEvent e) {
        if (dsPurchaseOrder != null) {
            txtUPC.setText("" +
                           tblPurchaseOrder.getValueAt(tblPurchaseOrder.
                getSelectedRow(), UPC).toString());

        }

    }

    public void tblPurchaseOrder_keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_DOWN || e.getKeyCode() == e.VK_UP) {
            tblPurchaseOrder_mouseClicked(null);
        }
        updateTotal();
    }
}

class PanelPurchaseOrder_tblPurchaseOrder_keyAdapter
    extends KeyAdapter {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_tblPurchaseOrder_keyAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void keyPressed(KeyEvent keyEvent) {
        adaptee.tblPurchaseOrder_keyPressed(keyEvent);
    }

    public void keyReleased(KeyEvent e) {
        adaptee.tblPurchaseOrder_keyReleased(e);
    }
}

class PanelPurchaseOrder_tblPurchaseOrder_mouseAdapter
    extends MouseAdapter {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_tblPurchaseOrder_mouseAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.tblPurchaseOrder_mouseClicked(e);
    }
}

class PanelPurchaseOrder_btnProdGroup_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnProdGroup_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.btnProdGroup_actionPerformed(actionEvent);
    }
}

class PanelPurchaseOrder_txtTerm_keyAdapter
    extends KeyAdapter {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_txtTerm_keyAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent keyEvent) {
        adaptee.txtTerm_keyTyped(keyEvent);
    }
}

class PanelPurchaseOrder_cboSupplier_itemAdapter
    implements ItemListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_cboSupplier_itemAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent e) {
        adaptee.cboSupplier_itemStateChanged(e);
    }
}

class PanelPurchaseOrder_txtUPC_keyAdapter
    extends KeyAdapter {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_txtUPC_keyAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent keyEvent) {
        adaptee.txtUPC_keyTyped(keyEvent);
    }
}

class PanelPurchaseOrder_btnUPC_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnUPC_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.btnUPC_actionPerformed(actionEvent);
    }
}

class PanelPurchaseOrder_btnStore_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnStore_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.btnStore_actionPerformed(actionEvent);
    }
}

class PanelPurchaseOrder_btnSupplier_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnSupplier_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.btnSupplier_actionPerformed(actionEvent);
    }
}

class PanelPurchaseOrder_btnReceiptDate_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnReceiptDate_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.btnReceiptDate_actionPerformed(actionEvent);
    }
}

class PanelPurchaseOrder_btnOrderDate_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnOrderDate_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.btnOrderDate_actionPerformed(actionEvent);
    }
}

class PanelPurchaseOrder_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnCancel_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnCancel_actionPerformed(e);
    }
}

class PanelPurchaseOrder_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnDelete_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnDelete_actionPerformed(e);
    }
}

class PanelPurchaseOrder_btnClear_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnClear_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnClear_actionPerformed(e);
    }
}

class PanelPurchaseOrder_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnSearch_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnSearch_actionPerformed(e);
    }
}

class PanelPurchaseOrder_btnModify_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnModify_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnModify_actionPerformed(e);
    }
}

class PanelPurchaseOrder_btnAdd_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnAdd_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnAdd_actionPerformed(e);
    }
}

class PanelPurchaseOrder_btnDone_actionAdapter
    implements java.awt.event.ActionListener {
    private PanelPurchaseOrder adaptee;
    PanelPurchaseOrder_btnDone_actionAdapter(PanelPurchaseOrder adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnDone_actionPerformed(e);
    }
}
