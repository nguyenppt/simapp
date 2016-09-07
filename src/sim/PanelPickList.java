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
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.table.TableColumn;
import java.awt.Insets;

/**
 * <p>Title      : Purchase Order </p>
 * <p>Description: makes an order and send it’s print to supplier </p>
 * <p>Description: Main -> Inv Mgmt -> Pick list</p>
 * <p>Copyright  : Copyright (c) 2006  </p>
 * <p>Company    : BTM </p>
 * @author       : Vinh Le
 * @version 1.0
 */
public class PanelPickList
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

  double qty = -1;
  //=============Define Constant for Table Column
  public static final int ITEM_ID = 0;
  public static final int UPC = 1;
  public static final int ITEM_NAME = 2;
  public static final int STANDARD_UOM = 3;
  public static final int REQUESTED_QTY = 4;
  public static final int ACTUAL_PICK_QTY = 5;
  public static final int SUBCATEGORY = 6;
  public static final int CURBACK = 7;
  public static final int CURFRONT = 8;

  public static final String STATUS_WORKSHEET = "WorkSheet";
  public static final String STATUS_APPROVED = "Approved";
  public static final String STATUS_COMPLETED = "Completed";
  public static final String STATUS_CANCELLED = "Cancelled";

  public static final String PL_TO_BACKROOM = "BackRoom";
  public static final String PL_TO_FRONTROOM = "FrontRoom";

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  JPanel pnlHeader = new JPanel();
  BJButton btnModify = new BJButton();//not use
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnClear = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  JPanel pnlCenter = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlInfo = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel pnlLeft = new JPanel();
  JPanel pnlRight = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel pnlLeftLabel = new JPanel();
  JPanel pnlLeftInfo = new JPanel();
  JLabel lblExpectedDate = new JLabel();
  JLabel lblCreateDate = new JLabel();
  JLabel lblPLID = new JLabel();
  JTextField txtExpectedDate = new JTextField();
  JTextField txtCreateDate = new JTextField();
  JButton btnExpectedDate = new JButton();
  JTextField txtPLID = new JTextField();
  JButton btnCreateDate = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel pnlRightLabel = new JPanel();
  JLabel lblStatus = new JLabel();
  JLabel lblPickTo = new JLabel();
  JLabel lblCreator = new JLabel();
  JPanel pnlRightInfo = new JPanel();
  JPanel pnlDesc = new JPanel();
  JScrollPane scrPanelTable = new JScrollPane();
  SortableTableModel dmPickList = new SortableTableModel();
  BJTable tblPickList = new BJTable(dmPickList, true);
  FlowLayout flowLayout3 = new FlowLayout();
  String picklistID = "";
  BJButton btnSearch = new BJButton();
  JComboBox cboStatus = new JComboBox();
//  BJComboBox cboType = new BJComboBox();
  JTextField txtCreator = new JTextField();
  JPanel jPanel2 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JLabel lblUPC = new JLabel();
  JTextField txtUPC = new JTextField();
  JButton btnUPC = new JButton(); //flag to set modify
  boolean FLAG_MODIFY = false;
  JLabel jLabel9 = new JLabel();
  JLabel jLabel12 = new JLabel();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel pnlComment = new JPanel();
  JLabel lblDesc = new JLabel();
  JTextArea txtDesc = new JTextArea();
  JButton btnAddGroup = new JButton();
  JPanel jPanel3 = new JPanel();
//  JLabel lblQty = new JLabel();
  JTextField txtProGroupID = new JTextField();
//  JTextField txtQty = new JTextField();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextField txtProGroupName = new JTextField();
  boolean IS_ENTER = true;//first time press Enter

  JLabel lblReceipt = new JLabel();
  JTextField txtReceiptID = new JTextField();
  JButton btnReceiptID = new JButton();
//  JLabel lblPickToo = new JLabel();
  JComboBox cboPickListTo = new JComboBox();
  public PanelPickList(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public PanelPickList(FrameMainSim frmMain, CardLayout crdLayout,
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
    resetData();
  }

  private void jbInit() throws Exception {
    txtDesc.setMaximumSize(new Dimension(17, 17));
    txtDesc.setPreferredSize(new Dimension(0, 23));
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setLayout(borderLayout1);
    pnlHeader.setBackground(new Color(121, 152, 198));
    pnlHeader.setPreferredSize(new Dimension(800, 45));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify")+"(F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnModify.addActionListener(new PanelPickList_btnModify_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+"(F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
        "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new PanelPickList_btnCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+"(F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("StkCountFR")+" (F7)");
    btnClear.setText("<html><center><b>"+lang.getString("StkCountFR")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                     "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnClear.addActionListener(new PanelPickList_btnClear_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.addActionListener(new PanelPickList_btnDelete_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+"(F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                   "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelPickList_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelPickList_btnDone_actionAdapter(this));
    pnlCenter.setLayout(borderLayout2);
    pnlInfo.setPreferredSize(new Dimension(800, 210));
    pnlInfo.setLayout(borderLayout3);
    pnlLeft.setPreferredSize(new Dimension(400, 90));
    pnlLeft.setLayout(borderLayout5);
    pnlRight.setPreferredSize(new Dimension(400, 90));
    pnlRight.setLayout(borderLayout6);
    pnlLeftLabel.setPreferredSize(new Dimension(150, 90));
    pnlLeftLabel.setLayout(null);
    lblExpectedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblExpectedDate.setPreferredSize(new Dimension(90, 22));
    lblExpectedDate.setText(lang.getString("ExpectedPickDate")+":");
    lblExpectedDate.setBounds(new Rectangle(27, 60, 115, 22));
    lblCreateDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblCreateDate.setPreferredSize(new Dimension(90, 22));
    lblCreateDate.setText(lang.getString("CreatedDate")+":");
    lblCreateDate.setBounds(new Rectangle(27, 32, 90, 22));
    lblPLID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPLID.setMaximumSize(new Dimension(66, 23));
    lblPLID.setPreferredSize(new Dimension(90, 22));
    lblPLID.setText(lang.getString("PickListID")+":");
    lblPLID.setBounds(new Rectangle(42, 5, 90, 22));
    pnlLeftInfo.setPreferredSize(new Dimension(250, 90));
    pnlLeftInfo.setLayout(flowLayout1);
    txtExpectedDate.setBackground(Color.white);
    txtExpectedDate.setEnabled(true);
    txtExpectedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtExpectedDate.setPreferredSize(new Dimension(140, 22));
    txtExpectedDate.setBounds(new Rectangle(5, 60, 140, 22));
    txtCreateDate.setBackground(Color.white);
//  txtCreateDate.setEnabled(true);
    txtCreateDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreateDate.setPreferredSize(new Dimension(140, 22));
    txtCreateDate.setEditable(false);
//  txtCreateDate.setEditable(false);
    txtCreateDate.setBounds(new Rectangle(5, 32, 140, 22));
    btnExpectedDate.setBounds(new Rectangle(150, 60, 30, 23));
    btnExpectedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnExpectedDate.setMinimumSize(new Dimension(30, 23));
    btnExpectedDate.setPreferredSize(new Dimension(30, 23));
    btnExpectedDate.setText("...");
    btnExpectedDate.addActionListener(new
        PanelPickList_btnExpectedDate_actionAdapter(this));
    btnCreateDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnCreateDate.setPreferredSize(new Dimension(30, 23));
    btnCreateDate.setText("...");
    btnCreateDate.setBounds(new Rectangle(150, 32, 30, 23));
    btnCreateDate.setEnabled(false);
    btnCreateDate.addActionListener(new
                                    PanelPickList_btnCreateDate_actionAdapter(this));
    txtPLID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPLID.setPreferredSize(new Dimension(176, 22));
    txtPLID.setDisabledTextColor(SystemColor.info);
    txtPLID.setEditable(false);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    pnlRightLabel.setPreferredSize(new Dimension(150, 90));
    pnlRightLabel.setLayout(null);
    lblStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStatus.setOpaque(false);
    lblStatus.setPreferredSize(new Dimension(80, 22));
    lblStatus.setRequestFocusEnabled(true);
    lblStatus.setText(lang.getString("Status")+":");
    lblStatus.setBounds(new Rectangle(42, 59, 80, 22));
    lblPickTo.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPickTo.setPreferredSize(new Dimension(80, 22));
    lblPickTo.setText(lang.getString("PickListTo")+":");
    lblPickTo.setBounds(new Rectangle(42, 32, 80, 22));
    lblCreator.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblCreator.setPreferredSize(new Dimension(80, 22));
    lblCreator.setText(lang.getString("Creator")+":");
    lblCreator.setBounds(new Rectangle(27, 5, 80, 22));
    pnlRightInfo.setPreferredSize(new Dimension(250, 90));
    pnlRightInfo.setLayout(null);
    pnlDesc.setLayout(flowLayout3);
    showButton(true);
    tblPickList.addMouseListener(new
                                 PanelPickList_tblPickList_mouseAdapter(this));
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search")+"(F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnSearch.addActionListener(new PanelPickList_btnSearch_actionAdapter(this));
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setPreferredSize(new Dimension(180, 22));
    cboStatus.setPopupVisible(false);
//    cboType.setEnabled(true);
//    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    cboType.setPreferredSize(new Dimension(180, 22));
    txtCreator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreator.setPreferredSize(new Dimension(175, 22));
    txtCreator.setEditable(false);
    txtCreator.setBounds(new Rectangle(5, 5, 175, 22));
    pnlDesc.setPreferredSize(new Dimension(800, 102));
    jPanel2.setBorder(titledBorder2);
    jPanel2.setMinimumSize(new Dimension(22, 22));
    jPanel2.setPreferredSize(new Dimension(780, 56));
    jPanel2.setLayout(null);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    lblUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblUPC.setPreferredSize(new Dimension(124, 22));
    lblUPC.setToolTipText("");
    lblUPC.setHorizontalAlignment(SwingConstants.LEFT);
    lblUPC.setText(lang.getString("UPC")+":");
    lblUPC.setVerticalAlignment(SwingConstants.CENTER);
    lblUPC.setBounds(new Rectangle(37, 4, 80, 23));
    txtUPC.setBackground(Color.white);
    txtUPC.setEnabled(true);
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUPC.setPreferredSize(new Dimension(140, 22));
    txtUPC.setRequestFocusEnabled(true);
    txtUPC.setDisabledTextColor(new Color(236, 233, 216));
    txtUPC.setText("");
    txtUPC.setBounds(new Rectangle(150, 4, 147, 22));
    txtUPC.addKeyListener(new PanelPickList_txtUPC_keyAdapter(this));
    btnUPC.setBounds(new Rectangle(300, 4, 30, 22));
    btnUPC.setEnabled(true);
    btnUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnUPC.setPreferredSize(new Dimension(30, 22));
    btnUPC.setText("...");
    btnUPC.addActionListener(new PanelPickList_btnUPC_actionAdapter(this));
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel9.setForeground(Color.red);
    jLabel9.setText("(*)");
    jLabel9.setBounds(new Rectangle(185, 35, 13, 16));
    jLabel12.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel12.setForeground(Color.red);
    jLabel12.setText("(*)");
    jLabel12.setBounds(new Rectangle(185, 63, 13, 16));
    this.setPreferredSize(new Dimension(800, 600));
    pnlCenter.setPreferredSize(new Dimension(800, 555));
    pnlComment.setPreferredSize(new Dimension(800, 35));
    pnlComment.setLayout(null);
    lblDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDesc.setText(lang.getString("Description")+":");
    lblDesc.setBounds(new Rectangle(35, 2, 80, 22));
    txtDesc.setText("");
    btnAddGroup.setBounds(new Rectangle(32, 29, 113, 23));
    btnAddGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnAddGroup.setHorizontalAlignment(SwingConstants.LEFT);
    btnAddGroup.setHorizontalTextPosition(SwingConstants.LEFT);
    btnAddGroup.setMargin(new Insets(1, 1, 2, 14));
    btnAddGroup.setText(lang.getString("AddGroup"));
    btnAddGroup.addActionListener(new PanelPickList_btnAddGroup_actionAdapter(this));
    jPanel3.setBorder(titledBorder2);
    jPanel3.setPreferredSize(new Dimension(260, 55));
    jPanel3.setLayout(null);
//    lblQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    lblQty.setText("Quantity:");
//    lblQty.setBounds(new Rectangle(12, 21, 73, 22));
    txtProGroupID.setEnabled(false);
    txtProGroupID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtProGroupID.setEditable(false);
    txtProGroupID.setText("");
    txtProGroupID.setBounds(new Rectangle(150, 29, 97, 23));
//    txtQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    txtQty.setToolTipText("");
//    txtQty.setText("");
//    txtQty.setBounds(new Rectangle(87, 21, 113, 22));
//    txtQty.addKeyListener(new PanelPickList_txtQty_keyAdapter(this));
    jScrollPane2.setPreferredSize(new Dimension(160, 45));
    jScrollPane2.setBounds(new Rectangle(150, 0, 575, 38));
    scrPanelTable.setPreferredSize(new Dimension(800, 350));
    txtProGroupName.setEnabled(false);
    txtProGroupName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtProGroupName.setEditable(false);
    txtProGroupName.setBounds(new Rectangle(249, 29, 155, 23));
    txtDesc.addKeyListener(new PanelPickList_txtDesc_keyAdapter(this));
    lblReceipt.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblReceipt.setText(lang.getString("OriginReceiptID")+":");
    lblReceipt.setBounds(new Rectangle(421, 4, 112, 23));
    txtReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtReceiptID.setText("");
    txtReceiptID.setBounds(new Rectangle(550, 4, 140, 22));
    btnReceiptID.setBounds(new Rectangle(695, 4, 30, 22));
    btnReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnReceiptID.setText("...");
    btnReceiptID.addActionListener(new PanelPickList_btnReceiptID_actionAdapter(this));
//    lblPickToo.setBounds(new Rectangle(42, 85, 88, 22));
//    lblPickToo.setText("PickList To:");
//    lblPickToo.setRequestFocusEnabled(true);
//    lblPickToo.setPreferredSize(new Dimension(80, 22));
//    lblPickToo.setOpaque(false);
//    lblPickToo.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboPickListTo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboPickListTo.setPreferredSize(new Dimension(180, 22));
    this.add(pnlCenter, java.awt.BorderLayout.CENTER);
    pnlLeftLabel.add(lblPLID);
    pnlLeftLabel.add(lblPickTo);
//    pnlLeftLabel.add(lblPickToo);
    pnlLeftLabel.add(lblStatus);

    // left info panel
    pnlLeftInfo.add(txtPLID);
    pnlLeftInfo.add(cboPickListTo, null);
//    pnlLeftInfo.add(cboType, null);
    pnlLeftInfo.add(cboStatus, null);


// right label info
    pnlRightLabel.add(lblCreator);
    pnlRightLabel.add(lblCreateDate);
    pnlRightLabel.add(lblExpectedDate);
    // right info panel
    pnlRightInfo.add(txtCreator, null);
    pnlRightInfo.add(txtCreateDate);
    pnlRightInfo.add(btnCreateDate);
    pnlRightInfo.add(jLabel9, null);
    pnlRightInfo.add(txtExpectedDate);
    pnlRightInfo.add(btnExpectedDate);
    pnlRightInfo.add(jLabel12, null);
    pnlInfo.add(pnlLeft, java.awt.BorderLayout.WEST);
    pnlInfo.add(pnlRight, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlDesc, java.awt.BorderLayout.SOUTH);
    pnlDesc.add(pnlComment);
    pnlComment.add(jScrollPane2);
    jScrollPane2.getViewport().add(txtDesc);
    pnlComment.add(lblDesc);
    pnlDesc.add(jPanel2, null);
//    pnlDesc.add(jPanel3);
//    jPanel3.add(lblQty);
//    jPanel3.add(txtQty);
    pnlCenter.add(scrPanelTable, java.awt.BorderLayout.CENTER);
    scrPanelTable.getViewport().add(tblPickList);
    pnlCenter.add(pnlInfo, java.awt.BorderLayout.NORTH);
    jPanel2.add(lblUPC, null);
    jPanel2.add(txtUPC, null);
    jPanel2.add(btnUPC, null);
    jPanel2.add(txtProGroupID);
    jPanel2.add(lblReceipt);
    jPanel2.add(txtReceiptID);
    jPanel2.add(btnReceiptID);
    jPanel2.add(txtProGroupName);
    jPanel2.add(btnAddGroup);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlLeft.add(pnlLeftInfo, java.awt.BorderLayout.EAST);
    pnlRight.add(pnlRightLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightInfo, java.awt.BorderLayout.CENTER);
    this.add(pnlHeader, java.awt.BorderLayout.NORTH);
    registerKeyboardActions();
    initTable();
    resetData();
    tblPickList.addKeyListener(new PanelPickList_tblPickList_keyAdapter(this));
  }

  public void setCboStatus(JComboBox cbo) {
    cbo.removeAllItems();
    cbo.addItem(STATUS_WORKSHEET);
    cbo.addItem(STATUS_APPROVED);
    cbo.addItem(STATUS_COMPLETED);
    cbo.addItem(STATUS_CANCELLED);
    cbo.setSelectedIndex(0); //default status: WorkSheet
  }
  public void setCboPickListTo(JComboBox cbo) {
    cbo.removeAllItems();
    cbo.addItem(PL_TO_BACKROOM);
    cbo.addItem(PL_TO_FRONTROOM);
    cbo.setSelectedIndex(1); //default PL:to FrontRoom
  }


  public void setLblPickFrom(JComboBox cbo) {
   cbo.removeAllItems();
   cbo.addItem(STATUS_WORKSHEET);
   cbo.addItem(STATUS_APPROVED);
   cbo.addItem(STATUS_COMPLETED);
   cbo.addItem(STATUS_CANCELLED);
   cbo.setSelectedIndex(0); //default status: WorkSheet
 }


//  private void setCboType(JComboBox cbo) {
//    cbo.removeAllItems();
//    cbo.addItem("Freeform");
////    cbo.addItem("Within Day");
////    cbo.addItem("End Of Day");
//    cbo.setSelectedIndex(0); //default status: Freeform
//  }

  private void initTable() {

    //define for table
    String[] columnNames = new String[] {
        "ITEM ID", "UPC", "Item Name", "UOM ", "Request Qty", "Pick Qty",
        "SubCategory", "BackR", "FrontR"};
    dmPickList.setDataVector(new Object[][] {}
                             , columnNames);
    tblPickList.setColumnWidth(ITEM_ID, 0);
    tblPickList.setColumnWidth(UPC, 100);
    tblPickList.setColumnWidth(ITEM_NAME, 180);
    tblPickList.setColumnWidth(STANDARD_UOM, 20);
    tblPickList.setColumnWidth(REQUESTED_QTY, 80);
    tblPickList.setColumnWidth(ACTUAL_PICK_QTY, 60);
    tblPickList.setColumnWidth(SUBCATEGORY, 80);
    tblPickList.setColumnWidth(CURBACK, 50);
    tblPickList.setColumnWidth(CURFRONT, 50);
    tblPickList.setColor(Color.lightGray, Color.white);
    tblPickList.setCellEditable(REQUESTED_QTY);

    TableColumn column = new TableColumn();
    column = tblPickList.getColumn("ITEM ID");
    column.setMaxWidth(0);
    column.setMinWidth(0);
    column.setPreferredWidth(0);

  }

  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_PICK_LIST);
    btnAdd.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnModify.setEnabled(er.getModify());
    //  btnAdminRoleReport.setEnabled(er.getReport());
    if(!er.getAdd()){
      ut.showMessage(frmMain, lang.getString("TT002"),
                     lang.getString("MS1085_CanNotUseScreen"));

    }

  }

  void showButton(boolean flagSetButton) {
    this.pnlHeader.removeAll();
    this.pnlHeader.updateUI();
    pnlHeader.add(btnDone, null);
//    if (flagSetButton == true)
      pnlHeader.add(btnAdd, null);
//    else
//      pnlHeader.add(btnModify, null);
    pnlHeader.add(btnSearch, null);
    pnlHeader.add(btnClear, null);
    pnlHeader.add(btnDelete, null);
    pnlHeader.add(btnCancel, null);
  }

  public void btnCancel_actionPerformed(ActionEvent e) {
    returnPreviousScreen();
  }

  public void btnCreateDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnCreateDate.getY() + posYFrame + 90;
    posY = this.btnExpectedDate.getY() + posYFrame + 90;
    TimeDlg startdate = new TimeDlg(null);
    startdate.setTitle(lang.getString("ChooseDate"));
    startdate.setResizable(false);
    startdate.setLocation(posX, posY);
    startdate.setVisible(true);
    if (startdate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(startdate.getDate());
      this.txtCreateDate.setText(date);
    }

  }

  public void btnExpectedDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnCreateDate.getY() + posYFrame + 90;
    posY = this.btnExpectedDate.getY() + posYFrame + 90;
    TimeDlg startdate = new TimeDlg(null);
    startdate.setTitle(lang.getString("ChooseDate"));
    startdate.setResizable(false);
    startdate.setLocation(posX, posY);
    startdate.setVisible(true);
    if (startdate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(startdate.getDate());
      this.txtExpectedDate.setText(date);
    }
  }

  public void btnAdd_actionPerformed(ActionEvent e) {
    ResultSet rs = null;
//checking valid quantity
    if (!checkExistOnGrid(txtUPC.getText().trim())) {
//      if (txtQty.getText().trim().equalsIgnoreCase("")) {
        insertItemToGrid(txtUPC.getText().trim(), 1);
        txtUPC.setText("");
    }
    if(!txtReceiptID.getText().equalsIgnoreCase("")){
      if(checkValidReceipt(txtReceiptID.getText().trim())){
        addUPCFromReceipt(txtReceiptID.getText().trim());
        txtReceiptID.setText("");
      }
    }
  }

  private void resetData() {
    picklistID = ut.getddMMyyhhmmssID();
    txtPLID.setText(picklistID);
//    setCboType(cboType);
    setCboStatus(cboStatus);
    setCboPickListTo(cboPickListTo);
    txtCreator.setText(DAL.getEmpCode());
    txtCreateDate.setText(ut.getSystemDate(1));
    txtExpectedDate.setText(ut.getSystemDate(1));
    txtUPC.setEditable(true);
    txtUPC.setText("");
    btnAddGroup.setEnabled(true);
    txtProGroupID.setText("");
    txtProGroupName.setText("");
//    txtQty.setText("");
    txtReceiptID.setText("");
    btnReceiptID.setEnabled(true);
    txtDesc.setText("");
    while (dmPickList.getRowCount() > 0) {
      dmPickList.removeRow(0);
    }
    vSql.removeAllElements();
  }

  private boolean checkExistOnGrid(String upc) {
    for (int i = 0; i < dmPickList.getRowCount(); i++) {
      if (dmPickList.getValueAt(i, UPC).toString().equalsIgnoreCase(upc.trim()))
        return true;
    }
    return false;
  }

  private void setSQLsForVector() {
    String SQL = " insert into BTM_IM_PICK_LIST"
        + " values ('" + txtPLID.getText() + "','" + DAL.getStoreID() + "','"
        + cboStatus.getSelectedItem().toString().substring(0, 1) + "','" +
        cboPickListTo.getSelectedItem().toString().substring(0, 1)
        + "','" + DAL.getEmpID() + "', to_date('" + txtCreateDate.getText() +
        "','dd/mm/yyyy'),"
        + " to_date('" + txtExpectedDate.getText() + "','dd/mm/yyyy'),'','"
        + txtDesc.getText() + "','" + txtProGroupID.getText() + "')";
//    System.out.println(SQL);
    vSql.addElement(SQL);
    for (int i = 0; i < dmPickList.getRowCount(); i++) {
      if(checkItemExistGrid(i)){
          SQL = " update BTM_IM_PICK_LIST_ITEM "
             + "set QTY_EXPECTED = QTY_EXPECTED + '"+dmPickList.getValueAt(i,REQUESTED_QTY)+"' "
             + "where PICK_LIST_ID='"+txtPLID.getText()+"' "
             + "and ITEM_ID='"+dmPickList.getValueAt(i,ITEM_ID)+"' ";
        }else{
          SQL = " insert into BTM_IM_PICK_LIST_ITEM"
              + " values ('" + txtPLID.getText() + "','" +
              dmPickList.getValueAt(i, ITEM_ID) + "'"
              + ",'" + dmPickList.getValueAt(i, REQUESTED_QTY) + "','',"
              + "'" + dmPickList.getValueAt(i, STANDARD_UOM) + "','')";
        }
//        System.out.println(SQL);
        vSql.addElement(SQL);
    }
  }

  public boolean checkItemExistGrid(int row){
    boolean flag = false;
    for(int i = 0;i < row;i++){
      if (dmPickList.getValueAt(row,ITEM_ID).equals(dmPickList.getValueAt(i,ITEM_ID))){
        flag = true;
      }
    }
    return flag;
  }
  void printReceipt(String PpicklistID, String sOrderDay, String sReceiptDay,
                    String StoreID, String StoreName, String SupplierID,
                    BJTable tbl,
                    DataAccessLayer DAL) {
//   String strHeader[][] = {
//       {
//       "So DDH:" + PpicklistID}
//   };
//   String strTableTitle[] = {
//       "Ma Vach", "Ten Mat Hang", "DVT", "VAT", "SL Dat", "Gia Mua",
//       "Tong Tien"
//   };
//   String strTableAlign[] = {
//       "CENTER", "LEFT", "CENTER","CENTER", "CENTER", "RIGHT", "RIGHT"};
//   int iTableSkew[] = {
//       15, 36, 7, 7, 7, 11, 17}; //15 sum = 100%
//   String strFormat[] = {
//       "String", "String", "String", "String", "String", "double", "double"
//   };
//   String OrderFormLeft[] = {
//       DAL.getCompanyName(),
//       DAL.getCompanyAdr(),
//       "Tel:" + DAL.getCompanyTel(), "Fax:" + DAL.getCompanyFax(),
//       "VAT:" + DAL.getCompanyVAT(), "Ngay dat hang:" + sOrderDay,
//       "Ngay giao hang:" + sReceiptDay,
//       "Adr:" + getStoreAdr(StoreID, DAL)
//   };
//   String strTemp[] = getSupllierInfo(SupplierID, DAL);
//   String OrderFormRight[] = {
//       "Ten NNC:" + strTemp[0], "MCC:" + strTemp[1], "Adr:" + strTemp[2],
//       "VAT:" + strTemp[3], "Tell:" + strTemp[4] + "  Fax:" + strTemp[5]
//   };
//   String OrderFormTitle[] = {
//       "DON DAT HANG", "Store " + StoreName};
//   String OrderFormInfoTop[] = {
//       "Xin Quy cong ty vui long dien day du thong tin ve ma san pham cua cong ty truoc khi tien hanh giao hang cho cong ty chung toi:"};
//   String OrderFormInfoBottom[] = {
//       ""};
//   int OrderFormInfoSkew[] = {
//       40, 20, 40};
//   int[] iTotal = {
//       6};
//   PrintReceipt pp = new PrintReceipt();
//   pp.setFileName("./file/" + PpicklistID + ".pdf");
//
//   //String strTableItem[][] = new String[strTableAlign.length][1];
//   String strTableItem[][] = new String[tbl.getRowCount()][tbl.getColumnCount()];
//   int i;
//   double iTongTienVAT=0;
//   double iTongTienSauVAT=0;
//   //Insert data of table to matrix
//   for (i=0;i<tbl.getRowCount();i++){
////     //Ma SP
////     if (tbl.getValueAt(i,ITEM_ID)!=null){
////       strTableItem[i][ITEM_ID] = tbl.getValueAt(i,ITEM_ID).toString();
////     }else{
////       strTableItem[i][ITEM_ID] = "";
////     }
//     //UPC
//     if (tbl.getValueAt(i,UPC)!=null){
//       strTableItem[i][0] = tbl.getValueAt(i,UPC).toString();
//     }else{
//       strTableItem[i][0] = "";
//     }
//     //Item name
//     if (tbl.getValueAt(i,ITEM_NAME)!=null){
//       strTableItem[i][1] = tbl.getValueAt(i,ITEM_NAME).toString();
//     }else{
//       strTableItem[i][1] = "";
//     }
//     //Standard UOM
//     if (tbl.getValueAt(i,STANDARD_UOM)!=null){
//       strTableItem[i][2] = tbl.getValueAt(i,STANDARD_UOM).toString();
//     }else{
//       strTableItem[i][2] = "";
//     }
//     //VAT
//     strTableItem[i][3] = getVAT(tbl.getValueAt(i,ITEM_ID).toString(),DAL)+"%";
//     //Qty
//     if (tbl.getValueAt(i,QTY)!=null){
//       strTableItem[i][4] = tbl.getValueAt(i,QTY).toString();
//     }else{
//       strTableItem[i][4] = "";
//     }
//     //Unit cost
//     if (tbl.getValueAt(i,UNIT_COST)!=null){
//       strTableItem[i][5] = tbl.getValueAt(i,UNIT_COST).toString();
//     }else{
//       strTableItem[i][5] = "";
//     }
//     //Total price
//     double dTotal = 0;
//     if (!strTableItem[i][4].equals("") && !tbl.getValueAt(i,UNIT_COST).equals("")){
//       dTotal = Double.parseDouble(tbl.getValueAt(i, UNIT_COST).toString()) *
//           Double.parseDouble(tbl.getValueAt(i, QTY).toString());
//       strTableItem[i][6] =""+dTotal;
//
//       iTongTienVAT += dTotal*Double.parseDouble(getVAT(tbl.getValueAt(i,ITEM_ID).toString(),DAL))/100;
//       iTongTienSauVAT += dTotal+dTotal*Double.parseDouble(getVAT(tbl.getValueAt(i,ITEM_ID).toString(),DAL))/100;
//     }
//   }
//   //Add VAT
//   pp.setVATLabelFirst("VAT");
//   pp.setVATTextFirst(iTongTienVAT+"");
//   pp.setVATLabelSecond("Tong So Tien Sau (VAT)");
//   pp.setVATTextSecond(iTongTienSauVAT+"");
//   //Add table item
//   pp.setHeader(strHeader);
//   pp.setPageString("Trang:", "/");
//   pp.setTableItem(strTableItem);
//   pp.setOrderFormLeft(OrderFormLeft);
//   pp.setOrderFormRight(OrderFormRight);
//   pp.setOrderFormTitle(OrderFormTitle);
//   pp.setOrderFormInfoTop(OrderFormInfoTop);
//   pp.setOrderFormInfoBottom(OrderFormInfoBottom);
//   //Add Table
//
//   pp.setTableItem(strTableItem);
//   pp.setTableTitle(strTableTitle);
//
//   //Add format for document
//   pp.setTotalName("Tong So Tien");
//   pp.setLeftSign("Nguoi dat hang");
//   pp.setRightSign("Quan ly kho");
//
//   pp.setOrderFormBorderTable(new Color(0, 0, 0));
//   pp.setFormatColumn(strFormat);
//   pp.setRowsTotal(iTotal);
//   pp.setIsViewTotal(true);
//   pp.setOrderFormInfoSkew(OrderFormInfoSkew);
//   pp.setTableSkew(iTableSkew);
//   pp.setTableAlign(strTableAlign);
//
//   //Add info
//   pp.setShowVAT(true);
//   pp.setPrintOrderForm(true);
//   pp.setShowDialog(true);
//
//   pp.setDeleteFileAfterPrint(true);
//
//   pp.print();
  }

  public void btnDone_actionPerformed(ActionEvent e) {
    String title="";
    String createDate = txtCreateDate.getText().trim();
    String expectedDate = txtExpectedDate.getText().trim();

    if (tblPickList.getRowCount() > 0) {
      if (!ut.checkDate(expectedDate, "/") || expectedDate.equals("")) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1135_InputExpectedPickDateDateType"));
        txtExpectedDate.setText("");
        btnExpectedDate.requestFocus(true);
        return;
      }
      if (ut.compareDate(createDate, expectedDate)) {
        ut.showMessage(frmMain, lang.getString("TT001"),
            lang.getString("MS1136_ExpectedPickDateGreaterOrEqualCreateDate"));
        txtExpectedDate.setText("");
        btnExpectedDate.requestFocus(true);
        return;
      }
      if (!cboStatus.getSelectedItem().toString().equalsIgnoreCase(
          STATUS_WORKSHEET)
          &&
          !cboStatus.getSelectedItem().toString().equalsIgnoreCase(STATUS_APPROVED)) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1137_PickListStatus") +
                       cboStatus.getSelectedItem().toString() + "'");
        return;
      }
      setSQLsForVector();
      if (!vSql.isEmpty()) {
        try {
          stmt = DAL.getConnection().createStatement(ResultSet.
              TYPE_SCROLL_INSENSITIVE,
              ResultSet.CONCUR_READ_ONLY);
          DAL.executeBatchQuery(vSql, stmt);
          stmt.close();
        }
        catch (Exception ex) {}
        ;
      }
      if (cboStatus.getSelectedItem().toString().equalsIgnoreCase(
          STATUS_APPROVED)) {
        //Print receipt
        if(cboPickListTo.getSelectedItem().toString().equals(PL_TO_FRONTROOM)){
          title=  "PICK LIST DELIVERY NOTE";
        }else{
          title=  "PICK LIST DELIVERY NOTE (TO BACKROOM)";
        }
          PrintReceipt(txtPLID.getText(),title, DAL.getStoreName(),
                       txtProGroupName.getText(), txtCreator.getText(),
                       ut.getSystemDateTime().substring(11, 19),
                       txtCreateDate.getText(), txtExpectedDate.getText(),
                       tblPickList, DAL, STATUS_APPROVED);

      }
    }
    returnPreviousScreen();
  }

  void PrintReceipt(String strPickListID,String strTitleHeader, String strFromStoreRoom,
                     String strProdGrp,String strCreator,String strTime,String strDate, String strExpDate,
                     BJTable tbl, DataAccessLayer DAL,String sStatus) {

      String strHeader[][] = {
          {
          "PICK LIST # " + strPickListID
      }
      };
      String strTitle[] = {
          strTitleHeader};
      String strInfo[][];
      String strFormat[];
      String strTableTitle[];
      String strTableAlign[];
      String strTableItem[][];
      String st = "";

//        int iTableSkew[] = {
//            5,8,34,13,10,10,20};

      int iTableSkew[];
      int[] iTotal = {
          4};

     strTableItem = new String[tbl.getRowCount()][6];
     strFormat  = new String[]{
              "String", "String", "String", "String", "money","String"
      };
     strTableTitle = new String[]{
          "No", "UPC", "Item Name", "UOM", "RequestQty","DeliveryQty"
      };
      strTableAlign = new String[]{
          "CENTER", "CENTER", "LEFT", "CENTER", "RIGHT","RIGHT"};
     iTableSkew = new int[]{5,15,40,10,15,15};
     strInfo = new String[][]{
         {
         "Create Date: " + strDate, //+ " " + strTime,
         "Store: " + strFromStoreRoom}
         ,{
         "Expect Date: " + strExpDate,
         "P/L Creator: " + strCreator}
         , {
         "Delivery Date: ",
         "Prod. Group: " + strProdGrp
         }
     };

      //Add Item to receipt
      for (int i = 0; i < tbl.getRowCount(); i++) {
        //col 0
        int iNo = i + 1;
        strTableItem[i][0] = "" + iNo;

        //col 1  -UPC
        if (tbl.getValueAt(i, UPC) != null) {
          st = tbl.getValueAt(i, UPC).toString();
        }
        else {
          st = " ";
        }
        strTableItem[i][1] = st;
        //col 2  - Item Name
        if (tbl.getValueAt(i, ITEM_NAME) != null) {
          st = tbl.getValueAt(i, ITEM_NAME).toString();
        }
        else {
          st = " ";
        }
        strTableItem[i][2] = st;
        //col 3  - UOM
        if (tbl.getValueAt(i, STANDARD_UOM) != null) {
          st = tbl.getValueAt(i, STANDARD_UOM).toString();
        }
        else {
          st = " ";
        }
        strTableItem[i][3] = st;
        //col 4  - QTY

          if (tbl.getValueAt(i, REQUESTED_QTY) != null) {
            st = tbl.getValueAt(i, REQUESTED_QTY).toString();
          }
          else {
            st = "0";
          }
        strTableItem[i][4] = st;
        //col 5
        strTableItem[i][5] = "";
      }
      PrintReceipt pp = new PrintReceipt();
      pp.setFileName(DAL.getAppHome() + "\\file\\" +
                     strPickListID +
                     ".pdf");

      //pp.setFileName("./file/"+strReceiptID+".pdf");
      pp.setHeader(strHeader);
      pp.setTitle(strTitle);
      pp.setInfo(strInfo);

      //Add info
      pp.setIsMagin(false);
      pp.setTotalName("Total Qty");
      pp.setLeftSign("DELIVER");
      pp.setRightSign("RECEIVER");
      pp.setColorBackgroundTotal(new Color(255, 255, 255));

      pp.setIsViewTotal(true);
      pp.setRowsTotal(iTotal);
      pp.setFormatColumn(strFormat);
      pp.setTableSkew(iTableSkew);
      pp.setTableAlign(strTableAlign);
      pp.setTableTitle(strTableTitle);
      pp.setTableItem(strTableItem);
      pp.setDeleteFileAfterPrint(true);

      pp.setIsGroupByArtNumber(false);
      pp.setTotalGroupName("");

      pp.print();
   }

  private void returnPreviousScreen() {
    resetData();
    showButton(true);
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0002"));
    frmMain.pnlMainSim.btnItemLookup.requestFocus(true);
  }

  void updateValueFromSelecledItemInTable() {
    int row = tblPickList.getSelectedRow();
    if (row >= 0) {
      txtUPC.setText(tblPickList.getValueAt(row, UPC).toString());
      showButton(false); //show Modify; hide Add
      FLAG_MODIFY = true; // allow hit F4 to modify
      txtUPC.setEnabled(false);
      btnUPC.setEnabled(false);
      btnAddGroup.setEnabled(false);
//      txtQty.requestFocus(true);
    }
  }

  public void btnDelete_actionPerformed(ActionEvent e) {
    int row = tblPickList.getSelectedRow();
    if (tblPickList.getSelectedRow() == -1) {
      return;
    }
    if (row >= 0) {
      int choose = ut.showMessage(frmMain, lang.getString("TT001"),
                                  lang.getString("MS1076_DeleteUPC") +
                                  tblPickList.getValueAt(row, UPC).toString() +
                                  "' ?", 3, DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.YES_VALUE) {
        dmPickList.removeRow(row);
        showButton(true);
      }
    }
    else {
      if (tblPickList.getRowCount() > 0) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1077_ChooseUPCWantToDelete"));
        return;
      }
    }
  }

  public void tblPickList_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
//      updateValueFromSelecledItemInTable();
    }
  }

  public void btnModify_actionPerformed(ActionEvent e) {
//    int row = tblPickList.getSelectedRow();
//    if (!checkValidQty()) {
//      return;
//    }
//    if (txtQty.getText().trim().equalsIgnoreCase("")) {
//      ut.showMessage(frmMain, lang.getString("TT001"),
//          "Input the new requested quantity that you want to modify.");
//      txtQty.requestFocus();
//      return;
//    }
//    tblPickList.setValueAt(String.valueOf(Double.parseDouble(txtQty.getText())),
//                           row, REQUESTED_QTY);
//    txtUPC.setText("");
//    txtQty.setText("");
//    txtUPC.setEnabled(true);
//    btnUPC.setEnabled(true);
//    btnAddGroup.setEnabled(true);
//    showButton(true); //show Add, hide Modify
//    FLAG_MODIFY = false; // don't allow to modify
//    tblPickList.requestFocus(true);
  }

  boolean checkValidQty() {
//    if (!txtQty.getText().trim().equalsIgnoreCase("")) {
//      qty = Double.parseDouble(txtQty.getText());
//      if (qty <= 0) {
//        ut.showMessage(frmMain, lang.getString("TT001"),
//                       "The Quantity can not less than or equal 0.");
//        txtQty.requestFocus();
//        txtQty.setSelectionStart(0);
//        txtQty.setSelectionEnd(txtQty.getText().length());
//        return false;
//      }
//    }
    return true;
  }

  public void btnSearch_actionPerformed(ActionEvent e) {
    PickListBusObj plBusObject = new PickListBusObj();
    DialogPickListSearch dlgPickListSearch = new DialogPickListSearch(frmMain,
        "JSIM - Pick List Search", true, frmMain, plBusObject);
    dlgPickListSearch.setVisible(true);
    if (dlgPickListSearch.done) {
      frmMain.showScreen(Constant.SCRS_PICK_LIST_MODIFY);
    }
    else {
      frmMain.showScreen(Constant.SCRS_PICK_LIST);
    }
  }

  public void btnClear_actionPerformed(ActionEvent e) {
//    resetData();
    frmMain.showScreen(Constant.SCRS_STOCK_COUNT_FRONT_ROOM);
    frmMain.pnlStockCountFrontRoom.initScreen();
    frmMain.pnlStockCountFrontRoom.cboStoreID.setSelectedData(DAL.getStoreID());
    frmMain.pnlStockCountFrontRoom.cboStoreID.requestFocus(true);
    frmMain.pnlStockCountFrontRoom.applyPermission();

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
        btnAdd.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {
        btnSearch.doClick();
      }
//      else if (identifier.intValue() == KeyEvent.VK_F4) {
//        if (FLAG_MODIFY == true) {
//          btnModify.doClick();
//        }
//        else {
//          updateValueFromSelecledItemInTable();
//        }
//      }
      else if (identifier.intValue() == KeyEvent.VK_F7) {
        btnClear.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void btnUPC_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        "JSIM - ITEM SEARCH", true, frmMain, itemBusObject);
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.cboStatus.setEditable(false);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      if (dlgItemLookup.getUPCList().isEmpty()) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1079_ItemNotSelectedOrNotApproved"));
        return;
      }
      setData(dlgItemLookup.getUPCList());
      txtUPC.setText("");
    }
  }

  void insertItemToGrid(String upc, double qty) {
    ResultSet rs = null;
    String SQL = " SELECT MAS.ITEM_ID,ART_NO,"
        + "ITEM_NAME,STANDARD_UOM,CHILD_NAME,"
        + String.valueOf(qty) + " REQUESTED_QTY,'' PICK_QTY,"
        + "BACK_ROOM_QTY,FRONT_ROOM_QTY "
        + "FROM BTM_IM_ITEM_MASTER MAS LEFT OUTER JOIN "
        + "	 BTM_IM_ITEM_LOC_SOH SOH ON (MAS.ITEM_ID=SOH.ITEM_ID AND SOH.STORE_ID='"+DAL.getStoreID()+"'),"
        + "	 BTM_IM_PROD_HIR HIR "
        + "WHERE MAS.CHILD_ID=HIR.CHILD_ID "
        + "AND  MAS.STATUS = 'A' "
        + "AND  MAS.ART_NO='" + upc + "' ";
    try {
//      System.out.println(SQL);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(SQL, stmt);
      while (rs.next()) {
        dmPickList.addRow(new Object[] {rs.getString("ITEM_ID"),
                          rs.getString("ART_NO"),
                          rs.getString("ITEM_NAME"),
                          rs.getString("STANDARD_UOM"),
                          rs.getString("REQUESTED_QTY"),
                          rs.getString("PICK_QTY"),
                          rs.getString("CHILD_NAME"),
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
        insertItemToGrid(vUPC.get(i).toString().trim(), 1);
      }
    }
  }

  void txtQty_keyTyped(KeyEvent e) {
//    char key = e.getKeyChar();
//    ut.limitLenjTextField(e, txtQty, 12);
//    ut.checkNumberUpdate(e, txtQty.getText());
  }

  void txtUPC_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtUPC, Constant.LENGHT_UPC_INPUT);
    ut.checkNumber(e);
  }

  public void btnAddGroup_actionPerformed(ActionEvent e) {
    btm.business.ProdGroupBusObj prdGrpBusObject = new btm.business.
        ProdGroupBusObj();
    DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(
        frmMain, lang.getString("TT0006"), true, frmMain, prdGrpBusObject);
    dlgProdGroupSearch.cboType.setSelectedItem("P");
    dlgProdGroupSearch.cboType.setEnabled(false);
    frmMain.pnlProdGroupDetail.SCREEN_FLAG = frmMain.pnlProdGroupDetail.
        PL_SETUP;
    dlgProdGroupSearch.setVisible(true);
    if (dlgProdGroupSearch.done) {
      if (dlgProdGroupSearch.getPGID().equalsIgnoreCase("")) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1079_ItemNotSelectedOrNotApproved"));
        return;
      }
      frmMain.pnlProdGroupDetail.setDataDetail(dlgProdGroupSearch.getPGID());
      frmMain.showScreen(Constant.SCRS_PROD_GROUP_DETAIL);
    }
    else {
      frmMain.showScreen(Constant.SCRS_PICK_LIST);
    }
  }

  public void txtDesc_keyTyped(KeyEvent e) {

  }

  void tblPickList_keyPressed(KeyEvent e) {
    char key = e.getKeyChar();
    String sNode ="";
    if(!tblPickList.getValueAt(tblPickList.getSelectedRow(), REQUESTED_QTY).equals("")){
      sNode = tblPickList.getValueAt(tblPickList.getSelectedRow(), REQUESTED_QTY).toString();
    }

    if ( ( (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
          (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) || (e.getKeyCode() == KeyEvent.VK_DELETE) ||
          (key == '-' && sNode.equals("")) ||
          (key == '.' && ut.countDot(sNode) == 0 && !sNode.equals("")))
        && (tblPickList.getSelectedColumn() == REQUESTED_QTY)) {
      if (IS_ENTER == true) {
        IS_ENTER = false; // modifying
        tblPickList.setValueAt("", tblPickList.getSelectedRow(),REQUESTED_QTY);
      }
    }
    else {
      if (e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_UP &&
          e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT &&
          e.getKeyCode() != KeyEvent.VK_ENTER)
        e.consume();
    }
//    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    if (e.getKeyCode() == KeyEvent.VK_DOWN && e.getKeyCode() == KeyEvent.VK_UP &&
        e.getKeyCode() == KeyEvent.VK_LEFT && e.getKeyCode() == KeyEvent.VK_RIGHT &&
          e.getKeyCode() == KeyEvent.VK_ENTER){
      IS_ENTER = true;
    }

  }
//  void updateRequestQty(){
//    if (tblPickList.getSelectedRow() > 0 && (tblPickList.getValueAt(tblPickList.getSelectedRow()-1, REQUESTED_QTY).equals("")
//       || tblPickList.getValueAt(tblPickList.getSelectedRow()-1, REQUESTED_QTY).toString().equals("0")
//       || !ut.isDoubleString(tblPickList.getValueAt(tblPickList.getSelectedRow()-1, REQUESTED_QTY).toString()))) {
//       tblPickList.setValueAt("1", tblPickList.getSelectedRow()-1, REQUESTED_QTY);
//    }
//    if (tblPickList.getRowCount() > 0 && (tblPickList.getValueAt(tblPickList.getRowCount()-1, REQUESTED_QTY).equals("")
//       || tblPickList.getValueAt(tblPickList.getRowCount()-1, REQUESTED_QTY).toString().equals("0")
//       || !ut.isDoubleString(tblPickList.getValueAt(tblPickList.getRowCount()-1, REQUESTED_QTY).toString()))) {
//       tblPickList.setValueAt("1", tblPickList.getRowCount()-1, REQUESTED_QTY);
//    }
//  }
  void updateRequestQty(int row){
    if(row >=0 && row < tblPickList.getRowCount()){
      if (tblPickList.getValueAt(row, REQUESTED_QTY).equals("")
         || tblPickList.getValueAt(row, REQUESTED_QTY).toString().equals("0")
         || !ut.isDoubleString(tblPickList.getValueAt(row, REQUESTED_QTY).toString())) {
         tblPickList.setValueAt("1", row, REQUESTED_QTY);
      }
    }
  }
  void tblPickList_keyReleased(KeyEvent e) {
    if (e.getKeyCode()==KeyEvent.VK_ENTER) {
      if(tblPickList.getSelectedRow()<tblPickList.getRowCount()){
        updateRequestQty(tblPickList.getSelectedRow() - 1);
      }
      if(tblPickList.getSelectedRow()==0){
        updateRequestQty(tblPickList.getRowCount()-1);
      }
    }
    if (e.getKeyCode()==KeyEvent.VK_DOWN) {
      if(tblPickList.getSelectedRow()<tblPickList.getRowCount()){
        updateRequestQty(tblPickList.getSelectedRow() - 1);
      }else{
        updateRequestQty(tblPickList.getSelectedRow());
      }
    }
    if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT) {
      updateRequestQty(tblPickList.getSelectedRow());
    }
    if (e.getKeyCode()==KeyEvent.VK_UP) {
      if(tblPickList.getSelectedRow()<0){
        updateRequestQty(tblPickList.getSelectedRow());
      }else{
        updateRequestQty(tblPickList.getSelectedRow()+1);
      }
    }
  }

  public void btnReceiptID_actionPerformed(ActionEvent e) {
    ReceiptBusObj receiptBusObject = new ReceiptBusObj();
    DialogReceiptSearch dlgReceiptSearch = new DialogReceiptSearch(frmMain,lang.getString("TT0102"),true, frmMain,receiptBusObject);
    dlgReceiptSearch.cboDestination.setSelectedItem("BackRoom");
    dlgReceiptSearch.setVisible(true);

    if(!dlgReceiptSearch.getReceiptID().equals("")){
      if (checkValidReceipt(dlgReceiptSearch.getReceiptID())) {
        addUPCFromReceipt(dlgReceiptSearch.getReceiptID());
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS2020_SelectedReceiveNotValid"));
      }
    }

  }
// check whether or not receipt is in status WORKSHEET
  boolean checkValidReceipt(String receiptID) {
    ResultSet rs = null;
    boolean isValid = false;
    String sql =
        "select * from BTM_IM_RECEIPT where receipt_id=" + receiptID;
//    System.out.println(sql);
    try {
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        isValid = true;
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return isValid;
}

  void addUPCFromReceipt(String receiptID){
    ResultSet rs = null;
    String SQL = "  select RI.ITEM_ID,MS.ART_NO,MS.ITEM_NAME,MS.STANDARD_UOM, 	    "
    + " 		'1' REQUESTED_QTY,'' PICK_QTY,HI.CHILD_NAME, 	    "
    + "		SO.BACK_ROOM_QTY,SO.FRONT_ROOM_QTY "
    + "FROM BTM_IM_RECEIPT_ITEM RI, 	  "
    + "	 BTM_IM_ITEM_MASTER MS LEFT OUTER JOIN BTM_IM_ITEM_LOC_SOH SO ON (MS.ITEM_ID = SO.ITEM_ID AND STORE_ID='"+DAL.getStoreID()+"'), 	  "
    + "	 BTM_IM_PROD_HIR HI  "
    + "WHERE RI.RECEIPT_ID = '"+receiptID+"'  "
    + "AND   MS.ITEM_ID = RI.ITEM_ID  "
    + "AND	  MS.CHILD_ID = HI.CHILD_ID order by RI.seq ";
    try {
//      System.out.println(SQL);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(SQL, stmt);
      while (rs.next()) {
        dmPickList.addRow(new Object[] {rs.getString("ITEM_ID"),
                          rs.getString("ART_NO"),
                          rs.getString("ITEM_NAME"),
                          rs.getString("STANDARD_UOM"),
                          rs.getString("REQUESTED_QTY"),
                          rs.getString("PICK_QTY"),
                          rs.getString("CHILD_NAME"),
                          rs.getString("BACK_ROOM_QTY"),
                          rs.getString("FRONT_ROOM_QTY")});
      }
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }
  }
}

class PanelPickList_btnReceiptID_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnReceiptID_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceiptID_actionPerformed(e);
  }
}

class PanelPickList_txtDesc_keyAdapter
    extends KeyAdapter {
  private PanelPickList adaptee;
  PanelPickList_txtDesc_keyAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtDesc_keyTyped(e);
  }
}

class PanelPickList_btnAddGroup_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnAddGroup_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAddGroup_actionPerformed(e);
  }
}

class PanelPickList_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnSearch_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelPickList_btnClear_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnClear_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelPickList_tblPickList_mouseAdapter
    extends MouseAdapter {
  private PanelPickList adaptee;
  PanelPickList_tblPickList_mouseAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblPickList_mouseClicked(e);
  }
}

class PanelPickList_btnModify_actionAdapter
    implements ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnModify_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelPickList_btnDelete_actionAdapter
    implements ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnDelete_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelPickList_btnDone_actionAdapter
    implements ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnDone_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelPickList_btnAdd_actionAdapter
    implements ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnAdd_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelPickList_btnExpectedDate_actionAdapter
    implements ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnExpectedDate_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnExpectedDate_actionPerformed(e);
  }
}

class PanelPickList_btnCreateDate_actionAdapter
    implements ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnCreateDate_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCreateDate_actionPerformed(e);
  }
}

class PanelPickList_btnCancel_actionAdapter
    implements ActionListener {
  private PanelPickList adaptee;
  PanelPickList_btnCancel_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelPickList_btnUPC_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPickList adaptee;

  PanelPickList_btnUPC_actionAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnUPC_actionPerformed(e);
  }
}

class PanelPickList_txtUPC_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPickList adaptee;

  PanelPickList_txtUPC_keyAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }
}

class PanelPickList_txtQty_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPickList adaptee;

  PanelPickList_txtQty_keyAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtQty_keyTyped(e);
  }
}

class PanelPickList_tblPickList_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPickList adaptee;

  PanelPickList_tblPickList_keyAdapter(PanelPickList adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tblPickList_keyPressed(e);
  }
  public void keyReleased(KeyEvent e) {
    adaptee.tblPickList_keyReleased(e);
  }
}
