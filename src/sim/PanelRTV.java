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
 * <p>Description: Main -> Inv Mgmt -> Transfer -> RTV </p>
 * <p>Copyright  : Copyright (c) 2006  </p>
 * <p>Company    : BTM </p>
 * @author       : Vinh Le
 * @modify by    : nqphuocit
 * @version 1.0
 */
public class PanelRTV
    extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  Statement stmt = null;
  DataAccessLayer DAL = new DataAccessLayer();
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  Vector vSql = new Vector();
  Vector vInfo = new Vector();

  double qty = -1;
  //=============Define Constant for Table Column
  public static final int ITEM_ID = 0;
  public static final int UPC = 1;
  public static final int ITEM_NAME = 2;
  public static final int STANDARD_UOM = 3;
  public static final int REQUESTED_QTY = 4;
  public static final int ACTUAL_RTV_QTY = 5;
  public static final int SUBCATEGORY = 6;
  public static final int CURBACK = 7;
//  public static final int CURFRONT = 8;
  public static final int UNIT_COST = 8;

  public static final String STATUS_WORKSHEET = "WorkSheet";
  public static final String STATUS_APPROVED = "Approved";
  public static final String STATUS_COMPLETED = "Completed";
//  public static final String STATUS_CANCELLED = "Cancelled";

  JPanel pnlHeader = new JPanel();
  BJButton btnModify = new BJButton();//not use
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
//  BJButton btnClear = new BJButton();
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
  JTextField txtRTVID = new JTextField();
  JButton btnCreateDate = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel pnlRightLabel = new JPanel();
  JLabel lblStatus = new JLabel();
  JLabel lblType = new JLabel();
  JLabel lblCreator = new JLabel();
  JPanel pnlRightInfo = new JPanel();
  JPanel pnlDesc = new JPanel();
  JScrollPane scrPanelTable = new JScrollPane();
  SortableTableModel dmRTV = new SortableTableModel();
  BJTable tblRTV = new BJTable(dmRTV, true);
  FlowLayout flowLayout3 = new FlowLayout();
  String returnToVendID = "";
  BJButton btnSearch = new BJButton();
  JComboBox cboStatus = new JComboBox();
  BJComboBox cboType = new BJComboBox();
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
  JPanel jPanel3 = new JPanel();
//  JLabel lblQty = new JLabel();
//  JTextField txtQty = new JTextField();
  JScrollPane jScrollPane2 = new JScrollPane();
  boolean IS_ENTER = true;//first time press Enter

  BJComboBox cboFromStore = new BJComboBox();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton btnSupp = new JButton();
  BJComboBox cboSupp = new BJComboBox();
  public PanelRTV(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public PanelRTV(FrameMainSim frmMain, CardLayout crdLayout,
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
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setLayout(borderLayout1);
    pnlHeader.setBackground(new Color(121, 152, 198));
    pnlHeader.setPreferredSize(new Dimension(800, 45));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify") + " (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnModify.addActionListener(new PanelRTV_btnModify_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
        "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new PanelRTV_btnCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete") + " (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
//    btnClear.setPreferredSize(new Dimension(120, 37));
//    btnClear.setToolTipText("Stk Count FR (F7)");
//    btnClear.setText("<html><center><b>Stk Count FR </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
//                     "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
//    btnClear.addActionListener(new PanelRTV_btnClear_actionAdapter(this));

    btnDelete.addActionListener(new PanelRTV_btnDelete_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add") + " (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                   "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelRTV_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done") + " (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelRTV_btnDone_actionAdapter(this));
    pnlCenter.setLayout(borderLayout2);
    pnlInfo.setPreferredSize(new Dimension(800, 195));
    pnlInfo.setLayout(borderLayout3);
    pnlLeft.setOpaque(true);
    pnlLeft.setPreferredSize(new Dimension(400, 122));
    pnlLeft.setLayout(borderLayout5);
    pnlRight.setPreferredSize(new Dimension(400, 90));
    pnlRight.setLayout(borderLayout6);
    pnlLeftLabel.setPreferredSize(new Dimension(150, 100));
    pnlLeftLabel.setLayout(null);
    lblExpectedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblExpectedDate.setPreferredSize(new Dimension(90, 22));
    lblExpectedDate.setText(lang.getString("ExpectedRTVDate") + ":");
    lblExpectedDate.setBounds(new Rectangle(27, 60, 115, 22));
    lblCreateDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblCreateDate.setPreferredSize(new Dimension(90, 22));
    lblCreateDate.setText(lang.getString("CreateDate") + ":");
    lblCreateDate.setBounds(new Rectangle(27, 32, 90, 22));
    lblPLID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPLID.setMaximumSize(new Dimension(66, 23));
    lblPLID.setPreferredSize(new Dimension(90, 22));
    lblPLID.setText(lang.getString("RTV_ID") + ":");
    lblPLID.setBounds(new Rectangle(42, 5, 90, 22));
    pnlLeftInfo.setPreferredSize(new Dimension(250, 120));
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
    btnExpectedDate.setEnabled(false);
    btnExpectedDate.addActionListener(new
        PanelRTV_btnExpectedDate_actionAdapter(this));
    btnCreateDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnCreateDate.setPreferredSize(new Dimension(30, 23));
    btnCreateDate.setText("...");
    btnCreateDate.setBounds(new Rectangle(150, 32, 30, 23));
    btnCreateDate.setEnabled(false);
    btnCreateDate.addActionListener(new
                                    PanelRTV_btnCreateDate_actionAdapter(this));
    txtRTVID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtRTVID.setPreferredSize(new Dimension(176, 22));
    txtRTVID.setDisabledTextColor(SystemColor.info);
    txtRTVID.setEditable(false);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    pnlRightLabel.setPreferredSize(new Dimension(150, 90));
    pnlRightLabel.setLayout(null);
    lblStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStatus.setOpaque(false);
    lblStatus.setPreferredSize(new Dimension(80, 22));
    lblStatus.setRequestFocusEnabled(true);
    lblStatus.setText(lang.getString("Status") + ":");
    lblStatus.setBounds(new Rectangle(28, 86, 80, 22));
    lblType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblType.setPreferredSize(new Dimension(80, 22));
    lblType.setText(lang.getString("RTV_From") + ":");
    lblType.setBounds(new Rectangle(42, 85, 94, 22));
    lblCreator.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblCreator.setPreferredSize(new Dimension(80, 22));
    lblCreator.setText(lang.getString("Creator") + ":");
    lblCreator.setBounds(new Rectangle(27, 5, 80, 22));
    pnlRightInfo.setPreferredSize(new Dimension(250, 90));
    pnlRightInfo.setLayout(null);
    pnlDesc.setLayout(flowLayout3);
    showButton(true);
    tblRTV.addMouseListener(new
                                 PanelRTV_tblRTV_mouseAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search") + " (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnSearch.addActionListener(new PanelRTV_btnSearch_actionAdapter(this));
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setPreferredSize(new Dimension(175, 22));
    cboStatus.setPopupVisible(false);
    cboType.setEnabled(false);
    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboType.setPreferredSize(new Dimension(180, 22));
    txtCreator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreator.setPreferredSize(new Dimension(175, 22));
    txtCreator.setEditable(false);
    txtCreator.setBounds(new Rectangle(5, 5, 175, 22));
    pnlDesc.setPreferredSize(new Dimension(800, 88));
    jPanel2.setBorder(titledBorder2);
    jPanel2.setMinimumSize(new Dimension(22, 22));
    jPanel2.setPreferredSize(new Dimension(780, 35));
    jPanel2.setRequestFocusEnabled(false);
    jPanel2.setLayout(null);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    lblUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblUPC.setPreferredSize(new Dimension(124, 22));
    lblUPC.setToolTipText("");
    lblUPC.setHorizontalAlignment(SwingConstants.LEFT);
    lblUPC.setText(lang.getString("UPC") + ":");
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
    txtUPC.addKeyListener(new PanelRTV_txtUPC_keyAdapter(this));
    btnUPC.setBounds(new Rectangle(300, 4, 30, 22));
    btnUPC.setEnabled(true);
    btnUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnUPC.setPreferredSize(new Dimension(30, 22));
    btnUPC.setText("...");
    btnUPC.addActionListener(new PanelRTV_btnUPC_actionAdapter(this));
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
    pnlComment.setPreferredSize(new Dimension(800, 40));
    pnlComment.setLayout(null);
    lblDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDesc.setText(lang.getString("Description") + ":");
    lblDesc.setBounds(new Rectangle(39, 0, 80, 22));
    txtDesc.setText("");
    jPanel3.setBorder(titledBorder2);
    jPanel3.setPreferredSize(new Dimension(260, 55));
    jPanel3.setLayout(null);
//    lblQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    lblQty.setText("Quantity:");
//    lblQty.setBounds(new Rectangle(12, 21, 73, 22));
//    txtQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    txtQty.setToolTipText("");
//    txtQty.setText("");
//    txtQty.setBounds(new Rectangle(87, 21, 113, 22));
//    txtQty.addKeyListener(new PanelRTV_txtQty_keyAdapter(this));
    jScrollPane2.setPreferredSize(new Dimension(160, 22));
    jScrollPane2.setBounds(new Rectangle(150, 0, 575, 38));
    scrPanelTable.setPreferredSize(new Dimension(800, 350));
    txtDesc.addKeyListener(new PanelRTV_txtDesc_keyAdapter(this));
    cboFromStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboFromStore.setPreferredSize(new Dimension(180, 22));
    cboFromStore.setEnabled(false); //disable
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setText(lang.getString("FromStore") + ":");
    jLabel1.setBounds(new Rectangle(43, 27, 70, 28));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(80, 22));
    jLabel2.setText(lang.getString("ToSupplier") + ":");
    jLabel2.setBounds(new Rectangle(42, 60, 111, 23));
    btnSupp.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSupp.setPreferredSize(new Dimension(30, 22));
    btnSupp.setText("...");
    btnSupp.addActionListener(new PanelRTV_btnSupp_actionAdapter(this));
    cboSupp.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSupp.setPreferredSize(new Dimension(145, 22));
    this.add(pnlCenter, java.awt.BorderLayout.CENTER);
    pnlLeftLabel.add(lblPLID);
    pnlLeftLabel.add(jLabel1, null);
    pnlLeftLabel.add(lblType);
    pnlLeftLabel.add(jLabel2, null);
    // left info panel
    pnlLeftInfo.add(txtRTVID);
    pnlLeftInfo.add(cboFromStore, null);
    pnlLeftInfo.add(cboSupp, null);
    pnlLeftInfo.add(btnSupp, null);
    pnlLeftInfo.add(cboType, null);

// right label info
    pnlRightLabel.add(lblCreator);
    pnlRightLabel.add(lblCreateDate);
    pnlRightLabel.add(lblExpectedDate);
    pnlRightLabel.add(lblStatus, null);
    // right info panel
    pnlRightInfo.add(txtCreator, null);
    pnlRightInfo.add(txtCreateDate);
    pnlRightInfo.add(btnCreateDate);
    pnlRightInfo.add(jLabel9, null);
    pnlRightInfo.add(txtExpectedDate);
    pnlRightInfo.add(btnExpectedDate);
    pnlRightInfo.add(jLabel12, null);
    pnlRightInfo.add(cboStatus, null);
    pnlInfo.add(pnlLeft, java.awt.BorderLayout.WEST);
    pnlInfo.add(pnlRight, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlDesc, java.awt.BorderLayout.SOUTH);
    pnlDesc.add(pnlComment);
    pnlComment.add(jScrollPane2);
    pnlComment.add(lblDesc);
    jScrollPane2.getViewport().add(txtDesc);
    pnlDesc.add(jPanel2, null);
//    pnlDesc.add(jPanel3);
//    jPanel3.add(lblQty);
//    jPanel3.add(txtQty);
    pnlCenter.add(scrPanelTable, java.awt.BorderLayout.CENTER);
    scrPanelTable.getViewport().add(tblRTV);
    pnlCenter.add(pnlInfo, java.awt.BorderLayout.NORTH);
    jPanel2.add(lblUPC, null);
    jPanel2.add(txtUPC, null);
    jPanel2.add(btnUPC, null);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlLeft.add(pnlLeftInfo, java.awt.BorderLayout.EAST);
    pnlRight.add(pnlRightLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightInfo, java.awt.BorderLayout.CENTER);
    this.add(pnlHeader, java.awt.BorderLayout.NORTH);
    registerKeyboardActions();
    initTable();
    resetData();
    tblRTV.addKeyListener(new PanelRTV_tblRTV_keyAdapter(this));
    cboStatus.setBounds(new Rectangle(4, 87, 178, 22));
  }

  public void setCboStatus(JComboBox cbo) {
    cbo.removeAllItems();
    cbo.addItem(STATUS_WORKSHEET);
    cbo.addItem(STATUS_APPROVED);
    cbo.addItem(STATUS_COMPLETED);
//    cbo.addItem(STATUS_CANCELLED);
    cbo.setSelectedIndex(0); //default status: WorkSheet
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



  private void setCboType(JComboBox cbo) {
    cbo.removeAllItems();
    cbo.addItem("BackRoom");
//    cbo.addItem("FrontRoom");
//    cbo.addItem("End Of Day");
    cbo.setSelectedIndex(0); //default status: BackRoom
  }

  private void initTable() {

    //define for table
    String[] columnNames = new String[] {


        lang.getString("ItemID"),
        lang.getString("UPC"),
        lang.getString("ItemName"),
        lang.getString("UOM"),
        lang.getString("RequestQty"),
        lang.getString("RTVQty"),
        lang.getString("SubCategory"),
        lang.getString("CurrentSOH"),
        lang.getString("UnitCost")};


//        "BackR", "FrontR", "Unit Cost"};
    dmRTV.setDataVector(new Object[][] {}
                             , columnNames);
    tblRTV.setColumnWidth(UPC, 100);
    tblRTV.setColumnWidth(ITEM_NAME, 180);
    tblRTV.setColumnWidth(STANDARD_UOM, 20);
    tblRTV.setColumnWidth(REQUESTED_QTY, 80);
    tblRTV.setColumnWidth(ACTUAL_RTV_QTY, 60);
    tblRTV.setColumnWidth(SUBCATEGORY, 80);
    tblRTV.setColumnWidth(CURBACK, 50);
//    tblRTV.setColumnWidth(CURFRONT, 50);
    tblRTV.setColor(Color.lightGray, Color.white);
    tblRTV.setCellEditable(REQUESTED_QTY);

    TableColumn column1 = new TableColumn();
    column1 = tblRTV.getColumn(lang.getString("ItemID"));
    column1.setMaxWidth(0);
    column1.setMinWidth(0);
    column1.setPreferredWidth(0);

    TableColumn column2 = new TableColumn();
    column2 = tblRTV.getColumn(lang.getString("UnitCost"));
    column2.setMaxWidth(0);
    column2.setMinWidth(0);
    column2.setPreferredWidth(0);

//hide the FrontR column
//    TableColumn column3 = new TableColumn();
//    column3 = tblRTV.getColumn("FrontR");
//    column3.setMaxWidth(0);
//    column3.setMinWidth(0);
//    column3.setPreferredWidth(0);

  }

  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_RTV);
    btnAdd.setEnabled(er.getAdd());
    btnSearch.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnModify.setEnabled(er.getModify());
    //  btnAdminRoleReport.setEnabled(er.getReport());
    if(!er.getAdd()){
      ut.showMessage(frmMain, lang.getString("TT002"),lang.getString("MS317_YouPermission"));

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
//    pnlHeader.add(btnClear, null);
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
    startdate.setTitle(lang.getString("ChooseStartDate"));
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
    if(cboFromStore.getSelectedItem().toString().equalsIgnoreCase("None")){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS333_PickUpStore"));
      cboFromStore.requestFocus(true);
      return;
   }
   if(cboSupp.getSelectedItem().toString().equalsIgnoreCase("None")){
     ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS334_SuppNotNull"));
     cboSupp.requestFocus(true);
     return;
   }
   if(txtUPC.getText().trim().equals("")){
     ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS335_UPCReturn"));
     txtUPC.requestFocus(true);
     return;
   }
   if (!checkItemInVendor(txtUPC.getText().trim())) {
     ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS336_ItemNotExist") + " '" + cboSupp.getSelectedItem().toString() + "'");
     txtUPC.requestFocus(true);
     return;
   }
   if (!checkExistOnGrid(txtUPC.getText().trim())) {
     insertItemToGrid(txtUPC.getText().trim(), 1);
     txtUPC.setText("");
     if(dmRTV.getRowCount()>0){
//       cboType.setEnabled(false);
       //disable       cboFromStore.setEnabled(false);
       cboSupp.setEnabled(false);
       btnSupp.setEnabled(false);
     }
   }
 }

  private boolean checkItemInVendor(String upc){
    ResultSet rs = null;
    String sqlStr =
        "Select mas.Art_No From BTM_IM_ITEM_MASTER mas, BTM_IM_ITEM_LOC_SUPPLIER sup "
    +   "Where mas.Item_ID = sup.Item_ID and sup.Supp_ID='" + cboSupp.getSelectedData() + "' "
    +   "and sup.Store_Id = " + cboFromStore.getSelectedData() + " "
    +   "and mas.Art_No ='" + upc + "'";
    try {
//      System.out.println(sqlStr);
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
  private void resetData() {
    returnToVendID = ut.getddMMyyhhmmssID();
    txtRTVID.setText(returnToVendID);
    setCboType(cboType);
    cboType.setSelectedIndex(0);
    cboType.setEnabled(false);
    setCboStatus(cboStatus);
    cboStatus.setSelectedIndex(0);
    cboFromStore.setData1(getStoreData());
    cboFromStore.setSelectedData(DAL.getStoreID());
    cboSupp.setData1(getSupplierData());
    txtCreator.setText(DAL.getEmpCode());
    txtCreateDate.setText(ut.getSystemDate(1));
    txtExpectedDate.setText(ut.getSystemDate(1));
    txtUPC.setEditable(true);
    txtUPC.setText("");
    txtDesc.setText("");
    //disable    cboFromStore.setEnabled(true);
    cboSupp.setEnabled(true);
    btnSupp.setEnabled(true);
//    cboType.setEnabled(true);
    while (dmRTV.getRowCount() > 0) {
      dmRTV.removeRow(0);
    }
    vSql.removeAllElements();
  }

  private boolean checkExistOnGrid(String upc) {
    for (int i = 0; i < dmRTV.getRowCount(); i++) {
      if (dmRTV.getValueAt(i, UPC).toString().equalsIgnoreCase(upc.trim()))
        return true;
    }
    return false;
  }

  private void setSQLsForVector() {
    String type = "B";
//    if(cboType.getSelectedIndex()==1){
//      type = "F";
//    }
    String SQL = " insert into BTM_IM_RTV"
        + " values ('" + txtRTVID.getText() + "','" + cboSupp.getSelectedData() + "'," + DAL.getStoreID() + ",'"
        + cboStatus.getSelectedItem().toString().substring(0, 1) + "','" + type
        + "','" + DAL.getEmpID() + "', to_date('" + txtCreateDate.getText() +
        "','dd/mm/yyyy'),"
        + " to_date('" + txtExpectedDate.getText() + "','dd/mm/yyyy'),'"
        + txtDesc.getText() + "','')";
//    System.out.println(SQL);
    vSql.addElement(SQL);
    for (int i = 0; i < dmRTV.getRowCount(); i++) {
      SQL = " insert into BTM_IM_RTV_DETAIL"
              + " values ('" + txtRTVID.getText() + "',"+i+",'" +
              dmRTV.getValueAt(i, ITEM_ID) + "'"
              + ",'" + dmRTV.getValueAt(i, REQUESTED_QTY) + "','','',"
              + dmRTV.getValueAt(i, UNIT_COST) + ",'')";
//          System.out.println(SQL);
          vSql.addElement(SQL);
    }
  }

  public boolean checkItemExistGrid(int row){
    boolean flag = false;
    for(int i = 0;i < row;i++){
      if (dmRTV.getValueAt(row,UPC).equals(dmRTV.getValueAt(i,UPC))){
        flag = true;
      }
    }
    return flag;
  }


  public void btnDone_actionPerformed(ActionEvent e) {
    String createDate = txtCreateDate.getText().trim();
    String expectedDate = txtExpectedDate.getText().trim();

    if (tblRTV.getRowCount() > 0) {
      if (!ut.checkDate(expectedDate, "/") || expectedDate.equals("")) {
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS343_ExpectedRVTDate"));
        txtExpectedDate.setText("");
        btnExpectedDate.requestFocus(true);
        return;
      }
      if (ut.compareDate(createDate, expectedDate)) {
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS344_ExpectedRVTGreaterCreate"));
        txtExpectedDate.setText("");
        btnExpectedDate.requestFocus(true);
        return;
      }
      if (!cboStatus.getSelectedItem().toString().equalsIgnoreCase(STATUS_WORKSHEET) &&
          !cboStatus.getSelectedItem().toString().equalsIgnoreCase(STATUS_APPROVED)) {
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS345_RTVStatus") + " '" + cboStatus.getSelectedItem().toString() + "'");
        return;
      }
      setSQLsForVector();
      if (!vSql.isEmpty()) {
        try {
          stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
          DAL.executeBatchQuery(vSql, stmt);
          stmt.close();
        }
        catch (Exception ex) {}
        ;
      }
      if (cboStatus.getSelectedItem().toString().equalsIgnoreCase(STATUS_APPROVED)) {
        String strHeader = "PHIEU TRA HANG" ;
//        if (cboType.getSelectedIndex() == 1){
//          strHeader = strHeader + " (TU FRONT ROOM)";
//        }
//        else{
//          strHeader = strHeader + " (TU BACK ROOM)";
//        }
        //Print receipt
        PrintReceipt(txtRTVID.getText(),strHeader,cboFromStore.getSelectedItem().toString(),
                     "", txtCreator.getText(),
                     ut.getSystemDateTime().substring(11, 19),
                     txtCreateDate.getText(), txtExpectedDate.getText(),
                     tblRTV, DAL,STATUS_APPROVED);
      }
    }
    returnPreviousScreen();
  }

  void PrintReceipt(String strRtvID,String strTitleHeader, String strFromStoreRoom,
                     String strProdGrp,String strCreator,String strTime,String strDate, String strExpDate,
                     BJTable tbl, DataAccessLayer DAL,String sStatus) {

      String strHeader[][] = {
          { "RTV # " + strRtvID }
      };
      String strTitle[] = { strTitleHeader };
      String strInfo[][];
      String strFormat[];
      String strTableTitle[];
      String strTableAlign[];
      String strTableItem[][];
      String st = "";
      String s =cboSupp.getSelectedItem().toString();

//        int iTableSkew[] = {
//            5,8,34,13,10,10,20};

      int iTableSkew[];
      int[] iTotal = {
         6};

     strTableItem = new String[tbl.getRowCount()][7];
     strFormat  = new String[]{
              "int", "String", "String", "String", "money", "money", "money"
      };
     strTableTitle = new String[]{
          "Stt", "Ma Vach", "Ten Mat Hang", "DVT", "SL", "Gia Mua" ,"Tong Tien"
      };
      strTableAlign = new String[]{
          "CENTER", "CENTER", "LEFT", "CENTER", "RIGHT" ,"RIGHT", "RIGHT"};
     iTableSkew = new int[]{5,15,35,7,8,15,15};
     strInfo = new String[][]{
         {
         "Ngay lap phieu: " + strDate, //+ " " + strTime,
         "Cua hang: " + strFromStoreRoom}
         ,{
         "Ngay du dinh tra: " + strExpDate,
         "Nguoi lap phieu: " + strCreator}
         , {
         "Ngay tra: ",
         "Nha cung cap: " + s
         }
     };
     ResultSet rsStore = null;
     String sqlStore = "Select STORE_ID From BTM_IM_STORE Where NAME = '" +  strFromStoreRoom +"'";
     int nStoreId = 0;

     try{
       stmt = DAL.getConnection().createStatement();
       rsStore = DAL.executeQueries(sqlStore, stmt);
      if (rsStore.next()) {
        nStoreId = rsStore.getInt("STORE_ID");
      }
      stmt.close();

     }catch (Exception ex) {}
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

        //col 5 - Gia mua -  Unit Cost
        String strBarcode = tbl.getValueAt(i, UPC).toString();
        String sqlGetUCost = "Select NEW_UNIT_COST From BTM_IM_UNIT_COST_HIST c, BTM_IM_ITEM_MASTER m " +
            "Where c.ITEM_ID=m.ITEM_ID And m.ART_NO='" + strBarcode + "' And c.STORE_ID="+nStoreId+"And c.STATUS='1'";
        ResultSet rsUCost = null;
        float nUnitCost = 0;
        try{
          stmt = DAL.getConnection().createStatement();
          rsUCost = DAL.executeQueries(sqlGetUCost, stmt);
          if (rsUCost.next()) {
            nUnitCost = rsUCost.getFloat("NEW_UNIT_COST");
          }
          stmt.close();
        }catch (Exception ex) {}

        strTableItem[i][5] = "" + nUnitCost;

        //col 6- tong tien - total
        float nTotal =0;
        float nQty = Float.parseFloat(tbl.getValueAt(i, REQUESTED_QTY).toString());
        nTotal = nQty * nUnitCost ;
        strTableItem[i][6] = "" + nTotal;

      }
      PrintReceipt pp = new PrintReceipt();
      pp.setFileName(DAL.getAppHome() + "\\file\\" +
                     strRtvID +
                     ".pdf");

      //pp.setFileName("./file/"+strReceiptID+".pdf");
      pp.setHeader(strHeader);
      pp.setTitle(strTitle);
      pp.setInfo(strInfo);

      //Add info
      pp.setIsMagin(false);
      pp.setTotalName("Tong So Tien");
      pp.setLeftSign("Ben giao");
      pp.setRightSign("Ben nhan");
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
    int row = tblRTV.getSelectedRow();
    if (row >= 0) {
      txtUPC.setText(tblRTV.getValueAt(row, UPC).toString());
      showButton(false); //show Modify; hide Add
      FLAG_MODIFY = true; // allow hit F4 to modify
      txtUPC.setEnabled(false);
      btnUPC.setEnabled(false);
//      btnAddGroup.setEnabled(false);
//      txtQty.requestFocus(true);
    }
  }

  public void btnDelete_actionPerformed(ActionEvent e) {
    int row = tblRTV.getSelectedRow();
    if (tblRTV.getSelectedRow() == -1) {
      return;
    }
    if (row >= 0) {
      int choose = ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1076_DeleteUPC") +
                                  " '" +
                                  tblRTV.getValueAt(row, UPC).toString() +
                                  "' ?", 3, DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.YES_VALUE) {
        dmRTV.removeRow(row);
        showButton(true);
        if(dmRTV.getRowCount()==0){
          //disable         cboFromStore.setEnabled(true);
         cboSupp.setEnabled(true);
         btnSupp.setEnabled(true);
//         cboType.setEnabled(true);
       }
      }
    }
    else {
      if (tblRTV.getRowCount() > 0) {
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS1077_ChooseUPCWantToDelete"));
        return;
      }
    }
  }

  public void tblRTV_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
//      updateValueFromSelecledItemInTable();
    }
  }

  public void btnModify_actionPerformed(ActionEvent e) {
//    int row = tblRTV.getSelectedRow();
//    if (!checkValidQty()) {
//      return;
//    }
//    if (txtQty.getText().trim().equalsIgnoreCase("")) {
//      ut.showMessage(frmMain, "Warning",
//          "Input the new requested quantity that you want to modify.");
//      txtQty.requestFocus();
//      return;
//    }
//    tblRTV.setValueAt(String.valueOf(Double.parseDouble(txtQty.getText())),
//                           row, REQUESTED_QTY);
//    txtUPC.setText("");
//    txtQty.setText("");
//    txtUPC.setEnabled(true);
//    btnUPC.setEnabled(true);
//    btnAddGroup.setEnabled(true);
//    showButton(true); //show Add, hide Modify
//    FLAG_MODIFY = false; // don't allow to modify
//    tblRTV.requestFocus(true);
  }

  boolean checkValidQty() {
//    if (!txtQty.getText().trim().equalsIgnoreCase("")) {
//      qty = Double.parseDouble(txtQty.getText());
//      if (qty <= 0) {
//        ut.showMessage(frmMain, "Warning",
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
    DialogRTVSearch dlgRTVSearch = new DialogRTVSearch(frmMain,
        lang.getString("TT0011"), true, frmMain, plBusObject);
    dlgRTVSearch.setVisible(true);
    if (dlgRTVSearch.done) {
      frmMain.showScreen(Constant.SCRS_RTV_MODIFY);
    }
    else {
      frmMain.showScreen(Constant.SCRS_RTV);
    }
  }

//  public void btnClear_actionPerformed(ActionEvent e) {
////    resetData();
//    frmMain.showScreen(Constant.SCRS_STOCK_COUNT_FRONT_ROOM);
//    frmMain.pnlStockCountFrontRoom.initScreen();
//    frmMain.pnlStockCountFrontRoom.cboStoreID.setSelectedData(DAL.getStoreID());
//    frmMain.pnlStockCountFrontRoom.cboStoreID.requestFocus(true);
//
//  }

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
//        btnClear.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void btnUPC_actionPerformed(ActionEvent e) {
    if(cboFromStore.getSelectedItem().toString().equalsIgnoreCase("None")){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS333_PickUpStore"));
        cboFromStore.requestFocus(true);
      return;
    }
    if(cboSupp.getSelectedItem().toString().equalsIgnoreCase("None")){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS334_SuppNotNull"));
        cboSupp.requestFocus(true);
      return;
    }
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0005"), true, frmMain, itemBusObject);
    dlgItemLookup.txtSupplier.setText(cboSupp.getSelectedData());
    dlgItemLookup.txtSupplier.setEnabled(false);
    dlgItemLookup.txtSupplieName.setText(cboSupp.getSelectedItem().toString());
    dlgItemLookup.txtSupplieName.setEnabled(false);
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.cboStatus.setEnabled(false);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      if (dlgItemLookup.getUPCList().isEmpty()) {
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS1079_ItemNotSelectedOrNotApproved"));
        return;
      }
      setData(dlgItemLookup.getUPCList());
      txtUPC.setText("");
      if(dmRTV.getRowCount()>0){
        //disable          cboFromStore.setEnabled(false);
          cboSupp.setEnabled(false);
          btnSupp.setEnabled(false);
//          cboType.setEnabled(false);
        }
    }
  }

  void insertItemToGrid(String upc, double qty) {
    ResultSet rs = null;
    String SQL = " SELECT MAS.ITEM_ID,ART_NO,"
        + "ITEM_NAME,STANDARD_UOM,CHILD_NAME,"
        + String.valueOf(qty) + " REQUESTED_QTY,'' RETURNED_QTY,"
        + "BACK_ROOM_QTY,FRONT_ROOM_QTY, NEW_UNIT_COST UNIT_COST "
        + "FROM BTM_IM_ITEM_MASTER MAS LEFT OUTER JOIN "
        + "	 BTM_IM_ITEM_LOC_SOH SOH ON (MAS.ITEM_ID=SOH.ITEM_ID AND SOH.STORE_ID='"+cboFromStore.getSelectedData()+"'),"
        + "	 BTM_IM_PROD_HIR HIR, "
        + "      BTM_IM_UNIT_COST_HIST UNHIS "
        + "WHERE MAS.CHILD_ID=HIR.CHILD_ID "
        + "AND  MAS.STATUS = 'A' "
        + "AND  MAS.ITEM_ID = UNHIS.ITEM_ID "
        + "AND  UNHIS.STORE_ID = " + cboFromStore.getSelectedData() + " "
        + "AND  UNHIS.STATUS = 1 "
        + "AND  MAS.ART_NO='" + upc + "' ";
    try {
//      System.out.println(SQL);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(SQL, stmt);
      while (rs.next()) {
        dmRTV.addRow(new Object[] {rs.getString("ITEM_ID"),
                          rs.getString("ART_NO"),
                          rs.getString("ITEM_NAME"),
                          rs.getString("STANDARD_UOM"),
                          rs.getString("REQUESTED_QTY"),
                          rs.getString("RETURNED_QTY"),
                          rs.getString("CHILD_NAME"),
                          rs.getString("BACK_ROOM_QTY"),
//                          rs.getString("FRONT_ROOM_QTY"),
                          rs.getString("UNIT_COST")});
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
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS321_ItemNotSelect"));
        return;
      }
      frmMain.pnlProdGroupDetail.setDataDetail(dlgProdGroupSearch.getPGID());
      frmMain.showScreen(Constant.SCRS_PROD_GROUP_DETAIL);
    }
    else {
      frmMain.showScreen(Constant.SCRS_RTV);
    }
  }

  public void txtDesc_keyTyped(KeyEvent e) {

  }

  void tblRTV_keyPressed(KeyEvent e) {
    char key = e.getKeyChar();
    String sNode = tblRTV.getValueAt(tblRTV.getSelectedRow(), REQUESTED_QTY).toString();
    if ( ( (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
          (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) || (e.getKeyCode() == KeyEvent.VK_DELETE) ||
          (key == '-' && sNode.equals("")) ||
          (key == '.' && ut.countDot(sNode) == 0 && !sNode.equals("")))
        && (tblRTV.getSelectedColumn() == REQUESTED_QTY)) {
      if (IS_ENTER == true) {
        IS_ENTER = false; // modifying
        tblRTV.setValueAt("", tblRTV.getSelectedRow(),REQUESTED_QTY);
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
    }
}
//  void updateRequestQty(){
//    if (tblRTV.getSelectedRow() > 0 && (tblRTV.getValueAt(tblRTV.getSelectedRow()-1, REQUESTED_QTY).equals("")
//       || tblRTV.getValueAt(tblRTV.getSelectedRow()-1, REQUESTED_QTY).toString().equals("0")
//       || !ut.isDoubleString(tblRTV.getValueAt(tblRTV.getSelectedRow()-1, REQUESTED_QTY).toString()))) {
//       tblRTV.setValueAt("1", tblRTV.getSelectedRow()-1, REQUESTED_QTY);
//    }
//    if (tblRTV.getRowCount() > 0 && (tblRTV.getValueAt(tblRTV.getRowCount()-1, REQUESTED_QTY).equals("")
//       || tblRTV.getValueAt(tblRTV.getRowCount()-1, REQUESTED_QTY).toString().equals("0")
//       || !ut.isDoubleString(tblRTV.getValueAt(tblRTV.getRowCount()-1, REQUESTED_QTY).toString()))) {
//       tblRTV.setValueAt("1", tblRTV.getRowCount()-1, REQUESTED_QTY);
//    }
//  }
  void updateRequestQty(int row){
    if(row >=0 && row < tblRTV.getRowCount()){
      if (tblRTV.getValueAt(row, REQUESTED_QTY).equals("")
         || tblRTV.getValueAt(row, REQUESTED_QTY).toString().equals("0")
         || !ut.isDoubleString(tblRTV.getValueAt(row, REQUESTED_QTY).toString())) {
         tblRTV.setValueAt("1", row, REQUESTED_QTY);
      }
    }
  }
  void tblRTV_keyReleased(KeyEvent e) {
    if (e.getKeyCode()==KeyEvent.VK_ENTER) {
      if(tblRTV.getSelectedRow()<tblRTV.getRowCount()){
        updateRequestQty(tblRTV.getSelectedRow() - 1);
      }
      if(tblRTV.getSelectedRow()==0){
        updateRequestQty(tblRTV.getRowCount()-1);
      }
    }
    if (e.getKeyCode()==KeyEvent.VK_DOWN) {
      if(tblRTV.getSelectedRow()<tblRTV.getRowCount()){
        updateRequestQty(tblRTV.getSelectedRow() - 1);
      }else{
        updateRequestQty(tblRTV.getSelectedRow());
      }
    }
    if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT) {
      updateRequestQty(tblRTV.getSelectedRow());
    }
    if (e.getKeyCode()==KeyEvent.VK_UP) {
      if(tblRTV.getSelectedRow()<0){
        updateRequestQty(tblRTV.getSelectedRow());
      }else{
        updateRequestQty(tblRTV.getSelectedRow()+1);
      }
    }
  }

  public void btnReceiptID_actionPerformed(ActionEvent e) {
    ReceiptBusObj receiptBusObject = new ReceiptBusObj();
    DialogReceiptSearch dlgReceiptSearch = new DialogReceiptSearch(frmMain,lang.getString("TT0012"),true, frmMain,receiptBusObject);
    dlgReceiptSearch.cboDestination.setSelectedItem("BackRoom");
    dlgReceiptSearch.setVisible(true);

    if(!dlgReceiptSearch.getReceiptID().equals("")){
      if (checkValidReceipt(dlgReceiptSearch.getReceiptID())) {
        addUPCFromReceipt(dlgReceiptSearch.getReceiptID());
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS341_SelectReceipt"));
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
    + " 		'1' REQUESTED_QTY,'' RETURNED_QTY,HI.CHILD_NAME, 	    "
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
        dmRTV.addRow(new Object[] {rs.getString("ITEM_ID"),
                          rs.getString("ART_NO"),
                          rs.getString("ITEM_NAME"),
                          rs.getString("STANDARD_UOM"),
                          rs.getString("REQUESTED_QTY"),
                          rs.getString("RETURNED_QTY"),
                          rs.getString("CHILD_NAME"),
                          rs.getString("BACK_ROOM_QTY")//,
//                          rs.getString("FRONT_ROOM_QTY")
             });
      }
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }
  }

  void btnSupp_actionPerformed(ActionEvent e) {
    SupplierBusObj suppBusObj = new SupplierBusObj();
     DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(frmMain,lang.getString("TT0013"),true,frmMain,suppBusObj);
     dlgSupplierSearch.setVisible(true);
     if (dlgSupplierSearch.done){
       cboSupp.setSelectedItem(dlgSupplierSearch.getSuppName());
     }
  }
}

class PanelRTV_btnReceiptID_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnReceiptID_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceiptID_actionPerformed(e);
  }
}

class PanelRTV_txtDesc_keyAdapter
    extends KeyAdapter {
  private PanelRTV adaptee;
  PanelRTV_txtDesc_keyAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtDesc_keyTyped(e);
  }
}

class PanelRTV_btnAddGroup_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnAddGroup_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAddGroup_actionPerformed(e);
  }
}

class PanelRTV_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnSearch_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

//class PanelRTV_btnClear_actionAdapter
//    implements java.awt.event.ActionListener {
//  private PanelRTV adaptee;
//  PanelRTV_btnClear_actionAdapter(PanelRTV adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnClear_actionPerformed(e);
//  }
//}

class PanelRTV_tblRTV_mouseAdapter
    extends MouseAdapter {
  private PanelRTV adaptee;
  PanelRTV_tblRTV_mouseAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblRTV_mouseClicked(e);
  }
}

class PanelRTV_btnModify_actionAdapter
    implements ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnModify_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelRTV_btnDelete_actionAdapter
    implements ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnDelete_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelRTV_btnDone_actionAdapter
    implements ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnDone_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelRTV_btnAdd_actionAdapter
    implements ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnAdd_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelRTV_btnExpectedDate_actionAdapter
    implements ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnExpectedDate_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnExpectedDate_actionPerformed(e);
  }
}

class PanelRTV_btnCreateDate_actionAdapter
    implements ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnCreateDate_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCreateDate_actionPerformed(e);
  }
}

class PanelRTV_btnCancel_actionAdapter
    implements ActionListener {
  private PanelRTV adaptee;
  PanelRTV_btnCancel_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelRTV_btnUPC_actionAdapter
    implements java.awt.event.ActionListener {
  PanelRTV adaptee;

  PanelRTV_btnUPC_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnUPC_actionPerformed(e);
  }
}

class PanelRTV_txtUPC_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelRTV adaptee;

  PanelRTV_txtUPC_keyAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }
}

class PanelRTV_txtQty_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelRTV adaptee;

  PanelRTV_txtQty_keyAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtQty_keyTyped(e);
  }
}

class PanelRTV_tblRTV_keyAdapter extends java.awt.event.KeyAdapter {
  PanelRTV adaptee;

  PanelRTV_tblRTV_keyAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tblRTV_keyPressed(e);
  }
  public void keyReleased(KeyEvent e) {
    adaptee.tblRTV_keyReleased(e);
  }
}

class PanelRTV_btnSupp_actionAdapter implements java.awt.event.ActionListener {
  PanelRTV adaptee;

  PanelRTV_btnSupp_actionAdapter(PanelRTV adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupp_actionPerformed(e);
  }
}


