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
/**
 * <p>Title: Purchase Order Modify</p>
 * <p>Description: Main -> Inv Mgmt -> Puchar Order -> Modify </p>
 * <p>Copyright: Copyright (c) 2006  </p>
 * <p>Company: BTM </p>
 * @author Vinh Le
 * @version 1.0
 */
public class PanelPurchaseOrderModify
    extends JPanel {
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

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  JPanel pnlHeader = new JPanel();
  BJButton btnModify = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnClear = new BJButton();
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
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JTextField txtReceiptDate = new JTextField();
  JTextField txtOrderDate = new JTextField();
  JButton btnReceiptDate = new JButton();
  JTextField txtPOID = new JTextField();
  JButton btnOrderDate = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel pnlRightLabel = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JPanel pnlRightInfo = new JPanel();
  JTextField txtTerm = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();
  JPanel pnlDesc = new JPanel();
  JLabel jLabel1 = new JLabel();
  JScrollPane scrPanelTable = new JScrollPane();
  SortableTableModel dmPurchaseOrder = new SortableTableModel();
  BJTable tblPurchaseOrder = new BJTable(dmPurchaseOrder, true);
  FlowLayout flowLayout3 = new FlowLayout();
  JPanel jPanel1 = new JPanel();
  String poID = "";
  FlowLayout flowLayout4 = new FlowLayout();
  JTextArea txtDesc = new JTextArea();
  BJButton btnPrint = new BJButton();
  JLabel jLabel15 = new JLabel();
  JComboBox cboStatus = new JComboBox();
  BJComboBox cboPayment = new BJComboBox();
  BJComboBox cboSupplier = new BJComboBox();
  JButton btnSupplier = new JButton();
  FlowLayout flowLayout5 = new FlowLayout();
  BJComboBox cboStore = new BJComboBox();
  JButton btnStore = new JButton();
  JPanel jPanel2 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JLabel jLabel10 = new JLabel();
  FlowLayout flowLayout6 = new FlowLayout();
  JTextField txtUPC = new JTextField();
  JButton btnUPC = new JButton();
  JLabel jLabel11 = new JLabel();
  JTextField txtQty = new JTextField();
  boolean FLAG_MODIFY = false;
  String oldOrderDate = "";
  JLabel jLabel9 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JLabel lblTotalAmount = new JLabel();
  JLabel jLabel14 = new JLabel();
  JTextField txtProdGroup = new JTextField();
  JLabel jLabel16 = new JLabel();
  JComboBox cboReceive = new JComboBox();
  JLabel jLabel17 = new JLabel();
  JLabel lblTotal = new JLabel();
  JButton btnProdGroup = new JButton();
  boolean IS_ENTER = true;//first time press Enter
  public PanelPurchaseOrderModify() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public PanelPurchaseOrderModify(FrameMainSim frmMain, CardLayout crdLayout,
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
    poID = ut.getDateTimeID();
    txtPOID.setText(poID);
    txtUPC.setEnabled(false);
    btnUPC.setEnabled(false);
    txtQty.setEnabled(false);
    initInfoForCombo();
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setLayout(borderLayout1);
    pnlHeader.setBackground(new Color(121, 152, 198));
    pnlHeader.setPreferredSize(new Dimension(102, 45));

    //btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify")+" (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                   "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelPurchaseOrderModify_btnAdd_actionAdapter(this));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.addActionListener(new
        PanelPurchaseOrderModify_btnModify_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
        "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new
        PanelPurchaseOrderModify_btnCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear")+" (F7)");
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                     "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnClear.addActionListener(new
                               PanelPurchaseOrderModify_btnClear_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.addActionListener(new
        PanelPurchaseOrderModify_btnDelete_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new
                              PanelPurchaseOrderModify_btnDone_actionAdapter(this));
    pnlCenter.setLayout(borderLayout2);
    pnlTable.setPreferredSize(new Dimension(10, 100));
    pnlTable.setLayout(borderLayout4);
    pnlInfo.setPreferredSize(new Dimension(10, 260));
    pnlInfo.setLayout(borderLayout3);
    pnlLeft.setPreferredSize(new Dimension(400, 10));
    pnlLeft.setLayout(borderLayout5);
    pnlRight.setPreferredSize(new Dimension(500, 10));
    pnlRight.setLayout(borderLayout6);
    pnlLeftLabel.setPreferredSize(new Dimension(140, 10));
    pnlLeftLabel.setLayout(flowLayout5);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel2.setPreferredSize(new Dimension(90, 22));
    jLabel2.setText(lang.getString("ReceiptDate")+":");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel3.setPreferredSize(new Dimension(90, 22));
    jLabel3.setText(lang.getString("OrderDate")+":");
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel4.setPreferredSize(new Dimension(90, 22));
    jLabel4.setText(lang.getString("ToStore")+":");
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel5.setMaximumSize(new Dimension(66, 23));
    jLabel5.setPreferredSize(new Dimension(90, 22));
    jLabel5.setText(lang.getString("PO_ID")+":");
    pnlLeftInfo.setPreferredSize(new Dimension(250, 10));
    pnlLeftInfo.setLayout(flowLayout1);
    txtReceiptDate.setBackground(Color.white);
    txtReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtReceiptDate.setPreferredSize(new Dimension(140, 22));
    txtOrderDate.setBackground(Color.white);
    txtOrderDate.setEnabled(true);
    txtOrderDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtOrderDate.setPreferredSize(new Dimension(140, 22));
    txtOrderDate.setEditable(true);
    btnReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnReceiptDate.setMinimumSize(new Dimension(30, 23));
    btnReceiptDate.setPreferredSize(new Dimension(30, 23));
    btnReceiptDate.setText("jButton1");
    btnReceiptDate.addActionListener(new
        PanelPurchaseOrderModify_btnReceiptDate_actionAdapter(this));
    btnOrderDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnOrderDate.setPreferredSize(new Dimension(30, 23));
    btnOrderDate.setText("jButton1");
    btnOrderDate.addActionListener(new
        PanelPurchaseOrderModify_btnOrderDate_actionAdapter(this));
    txtPOID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPOID.setPreferredSize(new Dimension(175, 22));
    txtPOID.setDisabledTextColor(SystemColor.info);
    txtPOID.setEditable(false);
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
    jLabel7.setText(lang.getString("Payment")+":");
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel8.setPreferredSize(new Dimension(80, 22));
    jLabel8.setText(lang.getString("SupplierName")+":");
    pnlRightInfo.setPreferredSize(new Dimension(100, 10));
    pnlRightInfo.setLayout(flowLayout4);
    txtTerm.setBackground(Color.white);
    txtTerm.setEnabled(true);
    txtTerm.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtTerm.setPreferredSize(new Dimension(180, 20));
    txtTerm.addKeyListener(new PanelPurchaseOrderModify_txtTerm_keyAdapter(this));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel1.setPreferredSize(new Dimension(135, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel1.setText("              "+lang.getString("Description")+":");
    pnlDesc.setLayout(flowLayout3);
    jPanel1.setPreferredSize(new Dimension(50, 10));
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    showButton();
    tblPurchaseOrder.addMouseListener(new
        PanelPurchaseOrderModify_tblPurchaseOrder_mouseAdapter(this));
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDesc.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDesc.setMinimumSize(new Dimension(635, 15));
    txtDesc.setPreferredSize(new Dimension(590, 38));
    txtDesc.setLineWrap(true);
    txtDesc.setWrapStyleWord(true);
    btnPrint.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrint.setPreferredSize(new Dimension(120, 37));
    btnPrint.setToolTipText(lang.getString("Print")+" (F3)");
    btnPrint.setText("<html><center><b>"+lang.getString("Print")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
                     ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnPrint.addActionListener(new
                               PanelPurchaseOrderModify_btnPrint_actionAdapter(this));
    jLabel15.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel15.setPreferredSize(new Dimension(80, 22));
    jLabel15.setText(lang.getString("Term")+":");
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setPreferredSize(new Dimension(180, 22));
    cboStatus.setPopupVisible(false);
    cboStatus.addItemListener(new
                              PanelPurchaseOrderModify_cboStatus_itemAdapter(this));
    cboPayment.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboPayment.setPreferredSize(new Dimension(180, 22));
    cboSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSupplier.setPreferredSize(new Dimension(144, 22));
    btnSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSupplier.setPreferredSize(new Dimension(30, 25));
    btnSupplier.setText("...");
    btnSupplier.addActionListener(new
        PanelPurchaseOrderModify_btnSupplier_actionAdapter(this));
    flowLayout5.setAlignment(FlowLayout.RIGHT);
    cboStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStore.setPreferredSize(new Dimension(140, 22));
    btnStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnStore.setPreferredSize(new Dimension(30, 23));
    btnStore.setText("..");
    btnStore.addActionListener(new
                               PanelPurchaseOrderModify_btnStore_actionAdapter(this));
    pnlDesc.setPreferredSize(new Dimension(742, 120));
    jPanel2.setBorder(titledBorder2);
    jPanel2.setMinimumSize(new Dimension(22, 22));
    jPanel2.setPreferredSize(new Dimension(783, 68));
    jPanel2.setLayout(flowLayout6);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel10.setPreferredSize(new Dimension(124, 22));
    jLabel10.setToolTipText("");
    jLabel10.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel10.setText("          "+lang.getString("UPC")+":");
    jLabel10.setVerticalAlignment(SwingConstants.CENTER);
    flowLayout6.setAlignment(FlowLayout.LEFT);
    txtUPC.setBackground(Color.white);
    txtUPC.setEnabled(true);
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUPC.setPreferredSize(new Dimension(140, 22));
    txtUPC.setRequestFocusEnabled(true);
    txtUPC.setDisabledTextColor(new Color(236, 233, 216));
    txtUPC.setEditable(true);
    txtUPC.setText("");
    txtUPC.addKeyListener(new PanelPurchaseOrderModify_txtUPC_keyAdapter(this));
    btnUPC.setEnabled(false);
    btnUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnUPC.setPreferredSize(new Dimension(30, 22));
    btnUPC.setText("...");
    btnUPC.addActionListener(new PanelPurchaseOrderModify_btnUPC_actionAdapter(this));
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel11.setForeground(Color.black);
    jLabel11.setOpaque(false);
    jLabel11.setPreferredSize(new Dimension(222, 22));
    jLabel11.setRequestFocusEnabled(true);
    jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel11.setText("                 "+lang.getString("TotalAmount")+":");
    txtQty.setBackground(Color.white);
    txtQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtQty.setPreferredSize(new Dimension(80, 22));
    txtQty.setEditable(true);
    txtQty.setText("");
    txtQty.addKeyListener(new PanelPurchaseOrderModify_txtQty_keyAdapter(this));
    txtQty.setVisible(false);
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel9.setForeground(Color.red);
    jLabel9.setText("(*)");
    jLabel12.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel12.setForeground(Color.red);
    jLabel12.setText("(*)");
    lblTotalAmount.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTotalAmount.setPreferredSize(new Dimension(120, 22));
    lblTotalAmount.setText("0.00");
    jLabel14.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel14.setPreferredSize(new Dimension(124, 22));
    jLabel14.setText("          "+lang.getString("ProGroup")+":");
    txtProdGroup.setPreferredSize(new Dimension(140, 22));
    txtProdGroup.setText("");
    jLabel16.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel16.setPreferredSize(new Dimension(90, 22));
    jLabel16.setText(lang.getString("Receive")+":");
    cboReceive.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboReceive.setPreferredSize(new Dimension(140, 22));
    cboReceive.setEnabled(false);
    jLabel17.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel17.setPreferredSize(new Dimension(80, 22));
    jLabel17.setText(lang.getString("TotalQty")+":");
    lblTotal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTotal.setPreferredSize(new Dimension(120, 22));
    lblTotal.setText("0.00");
    btnProdGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnProdGroup.setPreferredSize(new Dimension(30, 22));
    btnProdGroup.setText("...");
    btnProdGroup.addActionListener(new PanelPurchaseOrderModify_btnProdGroup_actionAdapter(this));
    tblPurchaseOrder.addKeyListener(new PanelPurchaseOrderModify_tblPurchaseOrder_keyAdapter(this));
    pnlRightInfo.add(cboSupplier, null);
    pnlRightInfo.add(btnSupplier, null);
    pnlRightInfo.add(cboPayment, null);
    pnlRightInfo.add(cboStatus, null);
    this.add(pnlCenter, java.awt.BorderLayout.CENTER);
    pnlCenter.add(pnlTable, java.awt.BorderLayout.CENTER);
    pnlCenter.add(pnlInfo, java.awt.BorderLayout.NORTH);
    pnlInfo.add(pnlLeft, java.awt.BorderLayout.WEST);
    pnlLeftLabel.add(jLabel5);
    pnlLeftLabel.add(jLabel4);
    pnlLeftLabel.add(jLabel3);
    pnlLeftLabel.add(jLabel2);
    pnlLeftLabel.add(jLabel16, null);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightLabel, java.awt.BorderLayout.WEST);
    pnlRightLabel.add(jLabel8);
    pnlRightLabel.add(jLabel7);
    pnlRightLabel.add(jLabel6);
    pnlRightLabel.add(jLabel15, null);
    pnlLeftInfo.add(txtPOID);
    pnlLeftInfo.add(cboStore, null);
    pnlLeftInfo.add(btnStore, null);
    pnlLeftInfo.add(txtOrderDate);
    pnlLeftInfo.add(btnOrderDate);
    pnlLeftInfo.add(jLabel9, null);
    pnlLeftInfo.add(txtReceiptDate);
    pnlLeftInfo.add(btnReceiptDate);
    pnlLeftInfo.add(jLabel12, null);
    pnlLeftInfo.add(cboReceive, null);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlRightInfo.add(txtTerm);
    pnlRight.add(pnlRightInfo, java.awt.BorderLayout.CENTER);
    pnlLeft.add(pnlLeftInfo, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlRight, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlDesc, java.awt.BorderLayout.SOUTH);
    pnlDesc.add(jLabel1);
    pnlDesc.add(txtDesc);
    pnlDesc.add(jPanel2, null);
    jPanel2.add(jLabel10, null);
    jPanel2.add(txtUPC, null);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.NORTH);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.CENTER);
    pnlRight.add(jPanel1, java.awt.BorderLayout.EAST);
    scrPanelTable.getViewport().add(tblPurchaseOrder, null);
    this.add(pnlHeader, java.awt.BorderLayout.NORTH);
    jPanel2.add(btnUPC, null);
    jPanel2.add(jLabel11, null);
    jPanel2.add(txtQty, null);
    jPanel2.add(lblTotalAmount, null);
    jPanel2.add(jLabel14, null);
    jPanel2.add(txtProdGroup, null);
    jPanel2.add(btnProdGroup, null);
    pnlRightLabel.add(jLabel17, null);
    pnlRightInfo.add(lblTotal, null);
    registerKeyboardActions();
    cboStatus.addItem("Approve");
    cboStatus.addItem("Work Sheet");
    cboStatus.addItem("Complete");
    cboReceive.addItem("Backroom");
    cboReceive.addItem("Frontroom");
    cboReceive.setEnabled(false);
    initInfoForCombo();
    initTable();
    tblPurchaseOrder.addFocusListener(new PanelPurchaseOrderModify_tblPurchaseOrder_focusAdapter(this));
  }

  void setDataToModify(String poID) {
    initInfoForCombo();
    setDataToHeader(poID);
    setDataToGrid(poID);
    txtUPC.setText("");
    txtQty.setText("");
    cboStore.setEnabled(false);
    btnStore.setEnabled(false);
    cboSupplier.setEnabled(false);
    btnSupplier.setEnabled(false);
    cboPayment.setEnabled(false);
    cboReceive.setEnabled(false);
    showButton();
  }

  void setDataToHeader(String poID) {
    ResultSet rs = null;
    String sqlStr = "Select Store_Id, to_char(Ordered_Date,'DD/MM/YYYY') Ordered_Date, to_char(Received_Date,'DD/MM/YYYY') Received_Date, Supp_Id, Payment_Method, Status, Terms, Comment_Desc, Fr_Br_Flag "
        + " From BTM_IM_INV_ORDER Where Order_ID='" + poID + "'";
    try {
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr, stmt);
      while (rs.next()) {
        txtPOID.setText(poID);
        cboStore.setSelectedData(rs.getString("STORE_ID"));
        oldOrderDate = rs.getString("ORDERED_DATE");
        txtOrderDate.setText(oldOrderDate);
        txtReceiptDate.setText(rs.getString("RECEIVED_DATE"));
        cboSupplier.setSelectedData(rs.getString("SUPP_ID"));
        cboPayment.setSelectedData(rs.getString("PAYMENT_METHOD"));
        String status = rs.getString("STATUS");
        if (status.equalsIgnoreCase("A")) {
          cboStatus.setSelectedItem("Approve");
        }
        else if (status.equalsIgnoreCase("W")) {
          cboStatus.setSelectedItem("Work Sheet");
        }
        else if (status.equalsIgnoreCase("C")) {
          cboStatus.setSelectedItem("Complete");
        }
        String receive = rs.getString("FR_BR_FLAG").trim();
        if (receive.equalsIgnoreCase("B")) {
          cboReceive.setSelectedItem("Backroom");
          txtUPC.setEnabled(true);
          btnUPC.setEnabled(true);
          txtProdGroup.setEnabled(false);
          btnProdGroup.setEnabled(false);
        } else if (receive.equalsIgnoreCase("F")) {
          cboReceive.setSelectedItem("Frontroom");
          txtUPC.setEnabled(false);
          btnUPC.setEnabled(false);
          txtProdGroup.setEnabled(true);
          btnProdGroup.setEnabled(true);
        }

        txtTerm.setText(rs.getString("TERMS"));
        txtDesc.setText(rs.getString("COMMENT_DESC"));
      }
      stmt.close();
      rs.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void setDataToGrid(String poID) {
    ResultSet rs = null;
    String itemID, itemName, upc, uom, pSize, qty, unitCost,backR,frontR;
    String sqlStr = "Select inv.Item_Id, mas.Art_No UPC, mas.Item_Name ,mas.Standard_UOM, mas.Pack_Size, inv.Qty_Expected QTY, inv.Unit_Cost,BACK_ROOM_QTY,FRONT_ROOM_QTY "
        + " From BTM_IM_INV_ORDER_ITEM inv, BTM_IM_ITEM_MASTER mas left outer join BTM_IM_ITEM_LOC_SOH soh on (soh.item_id=mas.item_id and soh.STORE_ID = "+cboStore.getSelectedData()+")"
        + " Where  inv.Item_Id = mas.Item_Id and Order_ID='" + poID + "' order by upper(mas.Item_Name)";
    try {
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr, stmt);

      //clear data on grid
      while (dmPurchaseOrder.getRowCount() > 0) {
        dmPurchaseOrder.removeRow(0);
      }

      while (rs.next()) {
        itemID = rs.getString("ITEM_ID");
        itemName = rs.getString("ITEM_NAME");
        upc = rs.getString("UPC");
        uom = rs.getString("STANDARD_UOM");
        pSize = rs.getString("PACK_SIZE");
        qty = rs.getString("QTY");
        unitCost = rs.getString("UNIT_COST");
        backR=rs.getString("BACK_ROOM_QTY");
//        frontR = rs.getString("FRONT_ROOM_QTY");
        dmPurchaseOrder.addRow(new Object[] {itemID, upc, itemName, uom, pSize, qty, unitCost,backR});//,frontR});
      }
      updateTotal();
      stmt.close();
      rs.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  private void initInfoForCombo() {
    cboStore.setData(getStoreData());
    try {
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    cboSupplier.setData(getSupplierData());
    try {
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    cboPayment.setData(getPaymentData());
    try {
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
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

  private void initTable() {
    //define for table
    String[] columnNames = new String[] {
        lang.getString("ItemID"), lang.getString("UPC"), lang.getString("ItemName"), lang.getString("UOM"), lang.getString("Packing"), lang.getString("Quantity"), lang.getString("UnitCost"),lang.getString("SOH")};
    dmPurchaseOrder.setDataVector(new Object[][] {}
                                  , columnNames);
    tblPurchaseOrder.setColumnWidth(UPC, 110);
    tblPurchaseOrder.setColumnWidth(ITEM_NAME, 180);
    tblPurchaseOrder.setColumnWidth(STANDARD_UOM, 50);
    tblPurchaseOrder.setColumnWidth(PACK_SIZE, 50);
    tblPurchaseOrder.setColumnWidth(QTY, 60);
    tblPurchaseOrder.setColumnWidth(UNIT_COST, 60);
    tblPurchaseOrder.setColumnWidth(CUR_BR,40);
//    tblPurchaseOrder.setColumnWidth(CUR_FR,0);
    tblPurchaseOrder.setColor(Color.lightGray, Color.white);
    tblPurchaseOrder.setCellEditable(QTY);

    TableColumn column=new TableColumn();
    column=tblPurchaseOrder.getColumn(lang.getString("ItemID"));
    column.setMaxWidth(0);
    column.setMinWidth(0);
    column.setPreferredWidth(0);

//hide the FrontR column
//    TableColumn column1=new TableColumn();
//    column1=tblPurchaseOrder.getColumn("FrontR");
//    column1.setMaxWidth(0);
//    column1.setMinWidth(0);
//    column1.setPreferredWidth(0);

  }

  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_ITEM_SEASON_SETUP);
    btnDelete.setEnabled(er.getDelete());
    btnAdd.setEnabled(er.getAdd());
    btnModify.setEnabled(er.getModify());
  }

  void showButton() {
    this.pnlHeader.removeAll();
    this.pnlHeader.updateUI();
    pnlHeader.add(btnDone, null);
    if (FLAG_MODIFY == false) {
      pnlHeader.add(btnAdd, null);
    }
    else {
      pnlHeader.add(btnModify, null);
    }
    pnlHeader.add(btnPrint, null);
    pnlHeader.add(btnClear, null);
    pnlHeader.add(btnDelete, null);
    pnlHeader.add(btnCancel, null);
  }

  public void btnCancel_actionPerformed(ActionEvent e) {
    //clear data on grid
    while (dmPurchaseOrder.getRowCount() > 0) {
      dmPurchaseOrder.removeRow(0);
    }
    //show Dialog Promo Search
    DialogPOSearch dlgPOSearch = new DialogPOSearch(frmMain,
        lang.getString("TT0103"), true, frmMain, frmMain.poBusObj);
    dlgPOSearch.setLocation(112, 85);
    dlgPOSearch.updateScreen();
    dlgPOSearch.setVisible(true);
    if (dlgPOSearch.done) {
      frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER_MODIFY);
    }
    else {
      frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER);
    }
    FLAG_MODIFY = false;
  }

  public void btnOrderDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnOrderDate.getY() + posYFrame + 90;
    posY = this.btnReceiptDate.getY() + posYFrame + 90;
    TimeDlg startdate = new TimeDlg(null);
    startdate.setTitle(lang.getString("ChooseOrderDate"));
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

  public void btnReceiptDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnOrderDate.getY() + posYFrame + 90;
    posY = this.btnReceiptDate.getY() + posYFrame + 90;
    TimeDlg startdate = new TimeDlg(null);
    startdate.setTitle(lang.getString("ChooseReceiptDate"));
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

  private void printPO() {
    printReceipt(txtPOID.getText(), txtOrderDate.getText(),
                     txtReceiptDate.getText(), cboStore.getSelectedData().toString(),
                     cboStore.getSelectedItem().toString(),
                     cboSupplier.getSelectedData().toString(), tblPurchaseOrder,
                     DAL);
  }

  private void resetData() {
//    cboStore.setSelectedIndex(0);
    cboStatus.setSelectedIndex(1);
    txtOrderDate.setText("");
    txtReceiptDate.setText("");
    txtTerm.setText("");
    txtDesc.setText("");
    txtUPC.setText("");
    txtQty.setText("");
    FLAG_MODIFY = false; // not allow hit F4 to modify
    txtUPC.setEnabled(true);
    btnUPC.setEnabled(true);
    showButton();
  }

  private boolean checkExist(String promoName) {
    ResultSet rs = null;
    String sqlStr = "Select UPC From BTM_IM_PROMO_HEAD Where UPC='" + promoName +
        "'";
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr, stmt);
      if (rs.next()) {
        stmt.close();
        return true;
      }
      stmt.close();
    }
    catch (Exception ex) {}
    return false;
  }

  private boolean checkExistOnGrid(String promoName) {
    for (int i = 0; i < dmPurchaseOrder.getRowCount(); i++) {
      if (dmPurchaseOrder.getValueAt(i,
                                     1).toString().equalsIgnoreCase(promoName.
          trim()))
        return true;
    }
    return false;
  }

  private void setSQLsForVector() {
    String strSql, itemID, qty, unitCost;
    String poID, storeID, orderDate, receiptDate,
        suppID, paymentID, status, term, desc;
    poID = txtPOID.getText().trim();
    storeID = cboStore.getSelectedData();
    orderDate = txtOrderDate.getText();
    receiptDate = txtReceiptDate.getText();
    term = txtTerm.getText();
    desc = txtDesc.getText();
    status = cboStatus.getSelectedItem().toString().substring(0, 1);

    strSql = "Update BTM_IM_INV_ORDER Set STORE_ID='" + storeID +
        "',SOURCE_ID='" + storeID + "',STATUS='"
        + status + "',ORDERED_DATE=to_date('" + orderDate +
        "','DD/MM/YYYY'),RECEIVED_DATE=to_date('" + receiptDate
        + "','DD/MM/YYYY'),TERMS='" + term + "',COMMENT_DESC='" + desc +
        "' Where ORDER_ID='" + poID + "'";
//    System.out.println(strSql);
    vSql.addElement(strSql);
    strSql = "Delete BTM_IM_INV_ORDER_ITEM  where Order_ID='" + poID + "'";
//    System.out.println(strSql);
    vSql.addElement(strSql);

    for (int i = 0; i < tblPurchaseOrder.getRowCount(); i++) {
      itemID = tblPurchaseOrder.getValueAt(i, ITEM_ID).toString();
      qty = tblPurchaseOrder.getValueAt(i, QTY).toString();
      unitCost = tblPurchaseOrder.getValueAt(i, UNIT_COST).toString();
      strSql = "insert into BTM_IM_INV_ORDER_ITEM (ORDER_ID, ITEM_ID, QTY_EXPECTED, QTY_RECEIVED, UNIT_COST) values ('"
          + poID + "','" + itemID + "'," + qty + ",0," + unitCost + ")";
//      System.out.println(strSql);
      vSql.addElement(strSql);
    }
  }

  public void btnDone_actionPerformed(ActionEvent e) {
    String orderDate = txtOrderDate.getText().trim();
    String receiptDate = txtReceiptDate.getText().trim();
    if (!ut.checkDate(orderDate, "/") || orderDate.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1012_OrderDateMustBeDateType"));
      txtOrderDate.requestFocus();
      txtOrderDate.setSelectionStart(0);
      txtOrderDate.setSelectionEnd(txtOrderDate.getText().length());
      return;
    }
    if (ut.compareDate(oldOrderDate, orderDate)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1166_OrderDateGreaterOrEqualOldOrderDate"));
      txtOrderDate.requestFocus();
      txtOrderDate.setSelectionStart(0);
      txtOrderDate.setSelectionEnd(txtOrderDate.getText().length());
      return;
    }
    if (!ut.checkDate(receiptDate, "/") || receiptDate.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1013_ReceiptDateMustBeDateType"));
      txtReceiptDate.requestFocus();
      txtReceiptDate.setSelectionStart(0);
      txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
      return;
    }
    if (ut.compareDate(orderDate, receiptDate)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1014_OrderDateMustBeBeforeReceiptDate"));
      txtReceiptDate.requestFocus();
      txtReceiptDate.setSelectionStart(0);
      txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
      return;
    }
    if (checkQtyNull()) {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1167_QuantityNotNULL"));
        tblPurchaseOrder.requestFocus(true);
        return;
    }
    if (cboStatus.getSelectedItem().toString().equalsIgnoreCase("Complete")) {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1168_PO_CanNotChangeToComplete"));
        cboStatus.requestFocus(true);
        return;
    }
    if (tblPurchaseOrder.getRowCount() > 0) {
      setSQLsForVector();
      if (vSql.isEmpty() == false) {
        try {
          stmt = DAL.getConnection().createStatement(ResultSet.
              TYPE_SCROLL_INSENSITIVE,
              ResultSet.CONCUR_READ_ONLY);
          DAL.executeBatchQuery(vSql, stmt);
          stmt.close();
        }
        catch (Exception ex) {}
      }
    }
    if(cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approve")){
      printPO();
    }
    returnPreviousScreen();
  }

  private boolean checkQtyNull() {
    for (int i = 0; i < tblPurchaseOrder.getRowCount(); i++) {
      if (tblPurchaseOrder.getValueAt(i, QTY).toString().equalsIgnoreCase("")) {
        return true;
      }
    }
    return false;
  }

  private void returnPreviousScreen() {
    //clear data on grid
    while (dmPurchaseOrder.getRowCount() > 0) {
      dmPurchaseOrder.removeRow(0);
    }
    //show Dialog Promo Search
    DialogPOSearch dlgPOSearch = new DialogPOSearch(frmMain,
        lang.getString("TT0103"), true, frmMain, frmMain.poBusObj);
    dlgPOSearch.setLocation(112, 85);
    dlgPOSearch.updateScreen();
    String[] arrInfo = new String[] {
        txtDesc.getText(), txtOrderDate.getText(),
        txtReceiptDate.getText(), cboStore.getSelectedItem().toString()};
    dlgPOSearch.updateRowOnTable(arrInfo);
    dlgPOSearch.setVisible(true);
    FLAG_MODIFY = false;
    if (dlgPOSearch.done) {
      frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER_MODIFY);
    }
    else {
      frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER);
    }

  }

  void updateValueFromSelecledItemInTable() {
    int row = tblPurchaseOrder.getSelectedRow();
    if (row >= 0) {
      txtUPC.setText(tblPurchaseOrder.getValueAt(row, UPC).toString());
      txtQty.setText("");
      FLAG_MODIFY = true; // allow hit F4 to modify
      txtUPC.setEnabled(false);
      btnUPC.setEnabled(false);
      txtQty.requestFocus(true);
      showButton();
    }
  }

  public void btnDelete_actionPerformed(ActionEvent e) {
    int row = tblPurchaseOrder.getSelectedRow();
    if (row >= 0) {
      int choose = ut.showMessage(frmMain, lang.getString("TT023"),
                                  lang.getString("MS1076_DeleteUPC") +
                                  tblPurchaseOrder.getValueAt(row, UPC).
                                  toString() + "' ?", 3,
                                  DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.YES_VALUE) {
        dmPurchaseOrder.removeRow(row);
        updateTotal();
      }
    }
    else {
      if (tblPurchaseOrder.getRowCount() > 0) {
        ut.showMessage(frmMain, "Warning!",
                       lang.getString("MS1077_ChooseUPCWantToDelete"));
        return;
      }
    }
  }

  public void tblPurchaseOrder_mouseClicked(MouseEvent e) {
//    if (e.getClickCount() == 2) {
//      if (btnDone.isEnabled()) {
//        updateValueFromSelecledItemInTable();
//        txtQty.setEnabled(true);
//        FLAG_MODIFY = true;
//        showButton();
//      }
//    }
  }

  public void btnModify_actionPerformed(ActionEvent e) {
    int row = tblPurchaseOrder.getSelectedRow();
    if (row >= 0) {
      double qty = 1;
//      if (txtQty.getText().trim().equalsIgnoreCase("")) {
//        ut.showMessage(frmMain, lang.getString("TT001"),
//                       "The Quantity can not null.");
//        txtQty.requestFocus();
//        return;
//      }
//      qty = Double.parseDouble(txtQty.getText());
//      if (qty <= 0) {
//        ut.showMessage(frmMain, lang.getString("TT001"),
//                       "The Quantity can not less than or equal 0.");
//        txtQty.requestFocus();
//        txtQty.setSelectionStart(0);
//        txtQty.setSelectionEnd(txtQty.getText().length());
//        return;
//      }
      tblPurchaseOrder.setValueAt(String.valueOf(qty), row, QTY);
      updateTotal();
      txtUPC.setText("");
      txtQty.setText("");
      FLAG_MODIFY = false;
      showButton();
      if(cboReceive.getSelectedItem().toString().equalsIgnoreCase("Backroom")){
        txtUPC.setEnabled(true);
        btnUPC.setEnabled(true);
      }
      tblPurchaseOrder.requestFocus(true);
    }
  }

  boolean checkExistOnGridModify(String promoName) {
    for (int i = 0; i < dmPurchaseOrder.getRowCount(); i++) {
      if (i != tblPurchaseOrder.getSelectedRow() &&
          dmPurchaseOrder.getValueAt(i, UPC).toString().equals(promoName.trim()))
        return true;
    }
    return false;
  }

  public void btnClear_actionPerformed(ActionEvent e) {
    resetData();
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
      }
      else if (identifier.intValue() == KeyEvent.VK_F2) {
        if (FLAG_MODIFY == false) {
          btnAdd.doClick();
        }
      }
      else if (identifier.intValue() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {
        btnPrint.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F4) {
        if (FLAG_MODIFY == true) {
          btnModify.doClick();
        }
        else {
          updateValueFromSelecledItemInTable();
        }
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

  void btnSupplier_actionPerformed(ActionEvent e) {
    SupplierBusObj suppBusObj = new SupplierBusObj();
    DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(frmMain,
        lang.getString("TT0013"), true, frmMain, suppBusObj);
    dlgSupplierSearch.setVisible(true);
    if (dlgSupplierSearch.done) {
      cboSupplier.setSelectedItem(dlgSupplierSearch.getSuppName());
      String payment_id = getPaymentID(cboSupplier.getSelectedData());
      cboPayment.setSelectedData(payment_id);
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

  void btnStore_actionPerformed(ActionEvent e) {
    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,
        lang.getString("TT0004"), true, frmMain, frmMain.storeBusObj);
    dlgStoreSearch.setVisible(true);
    if (dlgStoreSearch.done) {
      cboStore.setSelectedData(dlgStoreSearch.getData());
    }
  }

  void btnUPC_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0005"), true, frmMain, itemBusObject);
    dlgItemLookup.txtSupplier.setText(cboSupplier.getSelectedData());
    dlgItemLookup.txtSupplier.setEnabled(false);
    dlgItemLookup.txtSupplieName.setText(cboSupplier.getSelectedItem().toString());
    dlgItemLookup.txtSupplieName.setEnabled(false);
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.cboStatus.setEnabled(false);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      if (dlgItemLookup.getUPCList().isEmpty()) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS2014_ItemNotSelected"));
        return;
      }
      setData(dlgItemLookup.getUPCList());
      updateTotal();
      txtUPC.setText("");
      txtQty.setText("");
    }
  }
  void updateTotal(){
    double total=0;
    double totalAmount=0;
    if (tblPurchaseOrder.getSelectedRow() > 0 && (tblPurchaseOrder.getValueAt(tblPurchaseOrder.getSelectedRow()-1, QTY).equals("")
       || tblPurchaseOrder.getValueAt(tblPurchaseOrder.getSelectedRow()-1, QTY).toString().equals("0")
       || !ut.isDoubleString(tblPurchaseOrder.getValueAt(tblPurchaseOrder.getSelectedRow()-1, QTY).toString()))) {
       tblPurchaseOrder.setValueAt("1", tblPurchaseOrder.getSelectedRow()-1, QTY);
    }
    if (tblPurchaseOrder.getRowCount() > 0 && (tblPurchaseOrder.getValueAt(tblPurchaseOrder.getRowCount()-1, QTY).equals("")
       || tblPurchaseOrder.getValueAt(tblPurchaseOrder.getRowCount()-1, QTY).toString().equals("0")
       || !ut.isDoubleString(tblPurchaseOrder.getValueAt(tblPurchaseOrder.getRowCount()-1, QTY).toString()))) {
       tblPurchaseOrder.setValueAt("1", tblPurchaseOrder.getRowCount()-1, QTY);
    }
    for(int i=0;i<tblPurchaseOrder.getRowCount();i++){
      if (!tblPurchaseOrder.getValueAt(i, QTY).equals("")) {
        total +=   ut.convertToDouble(tblPurchaseOrder.getValueAt(i, QTY).toString());
        totalAmount += ut.convertToDouble(tblPurchaseOrder.getValueAt(i, QTY).toString()) *
            ut.convertToDouble(tblPurchaseOrder.getValueAt(i, UNIT_COST).toString());
      }
    }
    lblTotal.setText(ut.formatNumberTo2DigitsDecimal(total));
    lblTotalAmount.setText(ut.formatNumberTo2DigitsDecimal(totalAmount));
  }


  void insertItemToGrid(String upc) {
    String strSql = "Select mas.Item_Id,mas.Art_No UPC,mas.Item_Name,mas.Standard_Uom,mas.Pack_Size, 1 Qty,un.New_Unit_Cost unit_cost,BACK_ROOM_QTY,FRONT_ROOM_QTY "
        + "    From BTM_IM_ITEM_MASTER mas left outer join BTM_IM_ITEM_LOC_SOH soh on (soh.item_id=mas.item_id and soh.STORE_ID = "+cboStore.getSelectedData()+"), "
        + " BTM_IM_UNIT_COST_HIST un "
        + "    where mas.Item_Id=un.ITEM_ID "
        + " and un.STORE_ID = '"+cboStore.getSelectedData()+"' and un.STATUS = 1 "
        + "and mas.Status='A' and mas.Art_No ='" +
        upc + "'";
    ResultSet rs = null;
    try {
//      System.out.println(strSql);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(strSql, stmt);
      if (rs.next()) {
        dmPurchaseOrder.addRow(new Object[] {rs.getString("ITEM_ID"),
                               rs.getString("UPC"),
                               rs.getString("ITEM_NAME"),
                               rs.getString("STANDARD_UOM"),
                               rs.getString("PACK_SIZE"),
                               rs.getString("QTY"),
                               rs.getString("UNIT_COST"),
                               rs.getString("BACK_ROOM_QTY"),
                               rs.getString("FRONT_ROOM_QTY")});
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
    updateTotal();
    txtUPC.setText("");
    txtQty.setText("");
  }

  void txtQty_keyTyped(KeyEvent e) {
    char key = e.getKeyChar();
    ut.checkNumberUpdate(e, txtQty.getText());
  }

  void txtUPC_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtUPC, PurchaseOrderBusObj.LEN_UPC_ID);
    ut.checkNumber(e);
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

  void printReceipt(String PPOID, String sOrderDay, String sReceiptDay,
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
        15, 36, 7, 7, 7, 11, 17}; //15
    String strFormat[] = {
        "String", "String", "String", "String", "String", "double", "double"
    };

    String OrderFormLeft[] = {
        DAL.getCompanyName(), DAL.getCompanyAdr(),
        "Tel:" + DAL.getCompanyTel(), "Fax:" + DAL.getCompanyFax(),
        "VAT:" + DAL.getCompanyVAT(), "Ngay dat hang:" + sOrderDay,
        "Ngay giao hang:" + sReceiptDay,
//        "D/C giao hang:" + DAL.getAddrDelivery(),
        "D/C giao hang:" + frmMain.storeBusObj.getStoreAddress1(StoreID,DAL),
        "D/C hoa don:" + DAL.getAddrBill()
    };
    String strTemp[] = getSupllierInfo(SupplierID, DAL);
    String OrderFormRight[] = {
        "Ten NNC:" + strTemp[0], "MCC:" + strTemp[1], "Adr:" + strTemp[2],
        "VAT:" + strTemp[3], "Tell:" + strTemp[4] + "  Fax:" + strTemp[5]
    };
    String OrderFormTitle[] = {
        "DON DAT HANG", "Store " + StoreName};
//    String OrderFormInfoTop[] = {
//        "Xin Quy cong ty vui long dien day du thong tin ve ma san pham cua cong ty truoc khi tien hanh giao hang cho cong ty chung toi:"};
    String OrderFormInfoTop[] = {
        "Vui long kiem tra thong tin san pham tren don hang truoc khi tien hanh giao hang cho cong ty chung toi:",
        "*** LUU Y: 1-GIAO HANG THEO DIA CHI GIAO HANG GHI TREN DON DAT HANG.",
        "           2-VUI LONG GIAO DUNG SO LUONG THEO DON DAT HANG. CHUNG TOI SE KHONG NHAN BAT KY SO LUONG PHAT SINH NAO"};

    String OrderFormInfoBottom[] = {
        ""};
    int OrderFormInfoSkew[] = {
        40, 20, 40};
    int[] iTotal = {
        6};
    PrintReceipt pp = new PrintReceipt();
    pp.setFileName("./file/" + PPOID + ".pdf");

    //String strTableItem[][] = new String[strTableAlign.length][1];
//    String strTableItem[][] = new String[tbl.getRowCount()][tbl.getColumnCount()];
    String strTableItem[][] = new String[tbl.getRowCount()][numOfPrintingColumn]; //omit BR and FR column
    int i;
    double iTongTienVAT=0;
    double iTongTienSauVAT=0;
    //Insert data of table to matrix
    for (i = 0; i < tbl.getRowCount(); i++) {
//      //Ma SP
//      if (tbl.getValueAt(i, ITEM_ID) != null) {
//        strTableItem[i][ITEM_ID] = tbl.getValueAt(i, ITEM_ID).toString();
//      }
//      else {
//        strTableItem[i][ITEM_ID] = "";
//      }
      //UPC
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
        strTableItem[i][2] = tbl.getValueAt(i, STANDARD_UOM).
            toString();
      }
      else {
        strTableItem[i][2] = "";
      }
      //VAT
     strTableItem[i][3] = getVAT(tbl.getValueAt(i,ITEM_ID).toString(),DAL)+"%";
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
        dTotal = Double.parseDouble(tbl.getValueAt(i, UNIT_COST).toString()) *
            Double.parseDouble(tbl.getValueAt(i, QTY).toString());
        strTableItem[i][6] = "" + dTotal;

       iTongTienVAT += dTotal*Double.parseDouble(getVAT(tbl.getValueAt(i,ITEM_ID).toString(),DAL))/100;
       iTongTienSauVAT += dTotal+dTotal*Double.parseDouble(getVAT(tbl.getValueAt(i,ITEM_ID).toString(),DAL))/100;
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
    pp.setMainFrame(frmMain);
    pp.print();

  }

  void btnPrint_actionPerformed(ActionEvent e) {
    printReceipt(txtPOID.getText(), txtOrderDate.getText(),
                 txtReceiptDate.getText(), cboStore.getSelectedData().toString(),
                 cboStore.getSelectedItem().toString(),
                 cboSupplier.getSelectedData().toString(), tblPurchaseOrder,
                 DAL);
  }

  void cboStatus_itemStateChanged(ItemEvent e) {
//    if (cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approve") ||
//        cboStatus.getSelectedItem().toString().equalsIgnoreCase("Complete")) {
//      btnPrint.setEnabled(true);
//    }
//    else {
//      btnPrint.setEnabled(false);
//    }
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
//    if(txtQty.getText().trim().equalsIgnoreCase("")){
//       ut.showMessage(frmMain, lang.getString("TT001"),
//                    "The Quantity can not null." );
//       txtQty.requestFocus();
//       return;
//    }
    double qty=1;
//    double qty = Double.parseDouble(txtQty.getText().trim());
//    if(qty <= 0){
//       ut.showMessage(frmMain, lang.getString("TT001"),
//                    "The Quantity can not less than or equal 0." );
//       txtQty.requestFocus();
//       txtQty.setSelectionStart(0);
//       txtQty.setSelectionEnd(txtQty.getText().length());
//       return;
//    }
    String strSql = "Select mas.Item_Id,mas.Art_No UPC,mas.Item_Name,mas.Standard_Uom,mas.Pack_Size, " + String.valueOf(qty) + " Qty,un.New_Unit_Cost unit_cost,BACK_ROOM_QTY,FRONT_ROOM_QTY "
              + "    From BTM_IM_ITEM_MASTER mas left outer join BTM_IM_ITEM_LOC_SOH soh on (soh.item_id=mas.item_id and soh.STORE_ID= "+cboStore.getSelectedData()+"), "
              + " BTM_IM_UNIT_COST_HIST un"
              + "    where mas.Item_Id=un.ITEM_ID and un.STORE_ID = '"+cboStore.getSelectedData()+"'"
              + " and un.STATUS =1 and mas.Status='A' and mas.Art_No = '" + upc + "'";
    try{
//        System.out.println(strSql);
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = DAL.executeScrollQueries(strSql,stmt);
        while(rs.next()){
          dmPurchaseOrder.addRow(new Object[] {rs.getString("ITEM_ID"),rs.getString("UPC"),
             rs.getString("ITEM_NAME"),rs.getString("STANDARD_UOM"),rs.getString("PACK_SIZE"),
             rs.getString("QTY"),rs.getString("UNIT_COST"),rs.getString("BACK_ROOM_QTY"),rs.getString("FRONT_ROOM_QTY")});
        }
        rs.close();
        stmt.close();
    }catch(Exception ex){}
    updateTotal();
    txtUPC.setText("");
    txtQty.setText("");
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    addItemIntoGrid();
  }
  void addItemIntoGrid(){
    String orderDate = txtOrderDate.getText().trim();
    String receiptDate = txtReceiptDate.getText().trim();
    String upc = txtUPC.getText().trim();

    if (!ut.checkDate(orderDate, "/") || orderDate.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1012_OrderDateMustBeDateType"));
      txtOrderDate.requestFocus();
      txtOrderDate.setSelectionStart(0);
      txtOrderDate.setSelectionEnd(txtOrderDate.getText().length());
      return;
    }
    if (ut.compareDate(oldOrderDate, orderDate)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1166_OrderDateGreaterOrEqualOldOrderDate"));
      txtOrderDate.requestFocus();
      txtOrderDate.setSelectionStart(0);
      txtOrderDate.setSelectionEnd(txtOrderDate.getText().length());
      return;
    }
    if (!ut.checkDate(receiptDate, "/") || receiptDate.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1013_ReceiptDateMustBeDateType"));
      txtReceiptDate.requestFocus();
      txtReceiptDate.setSelectionStart(0);
      txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
      return;
    }
    if (ut.compareDate(orderDate, receiptDate)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1172_ReceiptDateGreaterOrEqualOrderDate"));
      txtReceiptDate.requestFocus();
      txtReceiptDate.setSelectionStart(0);
      txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
      return;
    }

    if (upc.equalsIgnoreCase("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1007_UPCIsNotNull"));
      txtUPC.requestFocus();
      txtUPC.setSelectionStart(0);
      txtUPC.setSelectionEnd(txtUPC.getText().length());
      return;
    }
    if (cboReceive.getSelectedItem().toString().equalsIgnoreCase("Backroom")) {
      //Backroom
      if (!checkItemOrder(upc)) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1151_UPC_NotExistSupplier")
                       + cboSupplier.getSelectedItem().toString() +
                       lang.getString("MS1152_UPC_ItemInvalid"));
        txtUPC.requestFocus();
        txtUPC.setSelectionStart(0);
        txtUPC.setSelectionEnd(txtUPC.getText().length());
        return;
      }
      if (checkExistOnGrid(upc)) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1106_UPC_Alreadyexisted"));
        txtUPC.requestFocus();
        txtUPC.setSelectionStart(0);
        txtUPC.setSelectionEnd(txtUPC.getText().length());
        return;
      }
      addItemToGrid(upc);
    }
    else { //Frontroom
      if (txtProdGroup.getText().trim().equalsIgnoreCase("")) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1171_PGID_NotNullEnterPGID"));
        txtProdGroup.requestFocus();
        txtProdGroup.setSelectionStart(0);
        txtProdGroup.setSelectionEnd(txtProdGroup.getText().length());
        return;
      }
      if (!checkPGSupplier(txtProdGroup.getText(), cboSupplier.getSelectedData())) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1169_ProductGroupNotCreateForSupplier")
                       + cboSupplier.getSelectedItem().toString() +
                       lang.getString("MS1170_ProductGroupInvalid"));
        txtProdGroup.requestFocus();
        txtProdGroup.setSelectionStart(0);
        txtProdGroup.setSelectionEnd(txtProdGroup.getText().length());
        return;
      }
      setDataForFrontroom(txtProdGroup.getText());
    }
  }
  void setDataForFrontroom(String pgID){
    String strSql = "Select mas.Item_Id,mas.Art_No UPC,mas.Item_Name,mas.Standard_Uom,mas.Pack_Size,1 Qty,un.New_Unit_Cost unit_cost,BACK_ROOM_QTY,FRONT_ROOM_QTY"
            + "    From BTM_IM_PROD_GROUP_ITEM pg,BTM_IM_ITEM_MASTER mas left outer join BTM_IM_ITEM_LOC_SOH soh on (soh.item_id=mas.item_id and soh.STORE_ID ="+cboStore.getSelectedData()+"), "
            + " BTM_IM_UNIT_COST_HIST un"
            + "    where pg.ITEM_ID= mas.ITEM_ID and mas.Item_Id=un.ITEM_ID "
            + " and un.STORE_ID = '"+cboStore.getSelectedData()+"' and un.STATUS =1 and mas.Status='A' "
            + " and PROD_GROUP_ID = '" + pgID + "' order by pg.seq";
        ResultSet rs = null;
        try {
//          System.out.println(strSql);
          stmt = DAL.getConnection().createStatement(ResultSet.
              TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
          rs = DAL.executeScrollQueries(strSql, stmt);
          while (rs.next()) {
            if(!checkExistOnGrid(rs.getString("UPC").trim())){
              dmPurchaseOrder.addRow(new Object[] {rs.getString("ITEM_ID"),
                                     rs.getString("UPC"),
                                     rs.getString("ITEM_NAME"),
                                     rs.getString("STANDARD_UOM"),
                                     rs.getString("PACK_SIZE"),
                                     rs.getString("QTY"),
                                     rs.getString("UNIT_COST"),
                                     rs.getString("BACK_ROOM_QTY"),
                                     rs.getString("FRONT_ROOM_QTY")});
            }
          }
          rs.close();
          stmt.close();
        }
        catch (Exception ex) {}
  }

  boolean checkPGSupplier(String pgID,String suppID){
   ResultSet rs = null;
   String sqlStr =
       "Select PROD_GROUP_ID From BTM_IM_PROD_GROUP Where Supp_ID='"
       + suppID + "' and PROD_GROUP_ID='" + pgID + "'";
   try {
//     System.out.println(sqlStr);
     stmt = DAL.getConnection().createStatement();
     rs = DAL.executeQueries(sqlStr, stmt);
     if (rs.next()) {
       stmt.close();
       return true;
     }
     stmt.close();
   }
   catch (Exception ex) {}
   return false;
 }


  void txtTerm_keyTyped(KeyEvent e) {
     ut.limitLenjTextField(e, txtTerm,15);
  }

  void btnProdGroup_actionPerformed(ActionEvent e) {
     btm.business.ProdGroupBusObj prdGrpBusObject = new btm.business.ProdGroupBusObj();
     DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(frmMain,lang.getString("TT0006"),true,frmMain,prdGrpBusObject);
     dlgProdGroupSearch.txtSupplier.setText(cboSupplier.getSelectedItem().toString());
     dlgProdGroupSearch.txtSupplier.setEnabled(false);
     dlgProdGroupSearch.btnSupplier.setEnabled(false);
     dlgProdGroupSearch.cboType.setSelectedItem("PO");
     dlgProdGroupSearch.cboType.setEnabled(false);
     frmMain.pnlProdGroupDetail.SCREEN_FLAG = frmMain.pnlProdGroupDetail.PO_MODIFY;
     dlgProdGroupSearch.setVisible(true);
     if (dlgProdGroupSearch.done){
        if (dlgProdGroupSearch.getPGID().equalsIgnoreCase("")){
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1079_ItemNotSelectedOrNotApproved") );
          return;
        }
        frmMain.pnlProdGroupDetail.setDataDetail(dlgProdGroupSearch.getPGID());
        frmMain.showScreen(Constant.SCRS_PROD_GROUP_DETAIL);
     }else{
       frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER_MODIFY);
     }
  }

  void tblPurchaseOrder_keyPressed(KeyEvent e) {
    char key = e.getKeyChar();
    String sNode = tblPurchaseOrder.getValueAt(tblPurchaseOrder.getSelectedRow(), QTY).toString();
    if ( ( (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
          (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) || (e.getKeyCode() == KeyEvent.VK_DELETE) ||
          (key == '-' && sNode.equals("")) ||
          (key == '.' && ut.countDot(sNode) == 0 && !sNode.equals("")))
        && (tblPurchaseOrder.getSelectedColumn() == QTY)) {
      if (IS_ENTER == true) {
        IS_ENTER = false; // modifying
        tblPurchaseOrder.setValueAt("", tblPurchaseOrder.getSelectedRow(),QTY);
      }
    }
    else {
      if (e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_UP &&
          e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT &&
          e.getKeyCode() != KeyEvent.VK_ENTER)
        e.consume();
    }
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      IS_ENTER = true;
      if(sNode.equalsIgnoreCase("")){
        tblPurchaseOrder.setValueAt("1", tblPurchaseOrder.getSelectedRow(),QTY);
      }
      updateTotal();
    }
  }

  void tblPurchaseOrder_focusLost(FocusEvent e) {
    for(int i=0;i<tblPurchaseOrder.getRowCount();i++){
      if(tblPurchaseOrder.getValueAt(i,QTY).toString().equals(null)||tblPurchaseOrder.getValueAt(i,QTY).toString().equalsIgnoreCase("")){
        tblPurchaseOrder.setValueAt("1",i,QTY);
      }
    }
  }

  void tblPurchaseOrder_keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      updateTotal();
    }
  }
  public void txtUPC_keyPressed(KeyEvent e) {
    DoEvent(e);
  }
  void DoEvent(KeyEvent e){
    if(e.getKeyCode()==e.VK_ENTER){
      btnAdd.doClick();
    }
  }
}


class PanelPurchaseOrderModify_btnPrint_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPurchaseOrderModify adaptee;
  PanelPurchaseOrderModify_btnPrint_actionAdapter(PanelPurchaseOrderModify
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrint_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_btnClear_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPurchaseOrderModify adaptee;
  PanelPurchaseOrderModify_btnClear_actionAdapter(PanelPurchaseOrderModify
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_tblPurchaseOrder_mouseAdapter
    extends MouseAdapter {
  private PanelPurchaseOrderModify adaptee;
  PanelPurchaseOrderModify_tblPurchaseOrder_mouseAdapter(
      PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblPurchaseOrder_mouseClicked(e);
  }
}

class PanelPurchaseOrderModify_btnModify_actionAdapter
    implements ActionListener {
  private PanelPurchaseOrderModify adaptee;
  PanelPurchaseOrderModify_btnModify_actionAdapter(PanelPurchaseOrderModify
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_btnDelete_actionAdapter
    implements ActionListener {
  private PanelPurchaseOrderModify adaptee;
  PanelPurchaseOrderModify_btnDelete_actionAdapter(PanelPurchaseOrderModify
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_btnDone_actionAdapter
    implements ActionListener {
  private PanelPurchaseOrderModify adaptee;
  PanelPurchaseOrderModify_btnDone_actionAdapter(PanelPurchaseOrderModify
                                                 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_btnReceiptDate_actionAdapter
    implements ActionListener {
  private PanelPurchaseOrderModify adaptee;
  PanelPurchaseOrderModify_btnReceiptDate_actionAdapter(
      PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceiptDate_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_btnOrderDate_actionAdapter
    implements ActionListener {
  private PanelPurchaseOrderModify adaptee;
  PanelPurchaseOrderModify_btnOrderDate_actionAdapter(PanelPurchaseOrderModify
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnOrderDate_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_btnCancel_actionAdapter
    implements ActionListener {
  private PanelPurchaseOrderModify adaptee;
  PanelPurchaseOrderModify_btnCancel_actionAdapter(PanelPurchaseOrderModify
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_btnSupplier_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_btnSupplier_actionAdapter(PanelPurchaseOrderModify
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplier_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_btnStore_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_btnStore_actionAdapter(PanelPurchaseOrderModify
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnStore_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_btnUPC_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_btnUPC_actionAdapter(PanelPurchaseOrderModify
                                                adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnUPC_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_txtQty_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_txtQty_keyAdapter(PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtQty_keyTyped(e);
  }
}

class PanelPurchaseOrderModify_txtUPC_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_txtUPC_keyAdapter(PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }

  public void keyReleased(KeyEvent e) {
  }

  public void keyPressed(KeyEvent e) {
    adaptee.txtUPC_keyPressed(e);
  }
}

class PanelPurchaseOrderModify_cboStatus_itemAdapter implements java.awt.event.ItemListener {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_cboStatus_itemAdapter(PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.cboStatus_itemStateChanged(e);
  }
}

class PanelPurchaseOrderModify_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_btnAdd_actionAdapter(PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_txtTerm_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_txtTerm_keyAdapter(PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtTerm_keyTyped(e);
  }
}

class PanelPurchaseOrderModify_btnProdGroup_actionAdapter implements java.awt.event.ActionListener {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_btnProdGroup_actionAdapter(PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnProdGroup_actionPerformed(e);
  }
}

class PanelPurchaseOrderModify_tblPurchaseOrder_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_tblPurchaseOrder_keyAdapter(PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tblPurchaseOrder_keyPressed(e);
  }
  public void keyReleased(KeyEvent e) {
    adaptee.tblPurchaseOrder_keyReleased(e);
  }
}

class PanelPurchaseOrderModify_tblPurchaseOrder_focusAdapter extends java.awt.event.FocusAdapter {
  PanelPurchaseOrderModify adaptee;

  PanelPurchaseOrderModify_tblPurchaseOrder_focusAdapter(PanelPurchaseOrderModify adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.tblPurchaseOrder_focusLost(e);
  }
}
