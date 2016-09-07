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
 * <p>Title: Product Group Modify</p>
 * <p>Description: Main -> Inv Mgmt -> Product Group -> Product Group Modify </p>
 * <p>Copyright: Copyright (c) 2006  </p>
 * <p>Company: BTM </p>
 * @author TrungNT
 * @version 1.0
 */
public class PanelProductGroupModify
    extends JPanel {
  public static final String STATUS_ACTIVE   = "Active";
  public static final String STATUS_CANCEL   = "Cancel";
  public static final String TYPE_PICK_LIST   = "Pick List";
  public static final String TYPE_PO   = "PO";
  public static final String TYPE_STOCK_COUNT   = "Stock Count";

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
  public static final int CHILD_NAME = 3;
//  public static final int PACK_SIZE = 4;
//  public static final int QTY = 5;
//  public static final int UNIT_COST = 6;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  JPanel pnlHeader = new JPanel();
//  BJButton btnModify = new BJButton();
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
  JLabel jLabel3 = new JLabel();
//  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
//  JTextField txtReceiptDate = new JTextField();
  JTextField txtCreatedDate = new JTextField();
//  JButton btnReceiptDate = new JButton();
  JTextField txtPGID = new JTextField();
//  JButton btnCreatedDate = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel pnlRightLabel = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JPanel pnlRightInfo = new JPanel();
//  JTextField txtTerm = new JTextField();
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
//  BJButton btnPrint = new BJButton();
//  JLabel jLabel15 = new JLabel();
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
//  JLabel jLabel11 = new JLabel();
//  JTextField txtQty = new JTextField();
//  boolean FLAG_MODIFY = false;
//  String oldOrderDate = "";
  public PanelProductGroupModify() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public PanelProductGroupModify(FrameMainSim frmMain, CardLayout crdLayout,
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
    txtUPC.setEnabled(false);
    btnUPC.setEnabled(false);
//    txtQty.setEnabled(false);
    initInfoForCombo();
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setLayout(borderLayout1);
    pnlHeader.setBackground(new Color(121, 152, 198));
    pnlHeader.setPreferredSize(new Dimension(102, 45));
//    btnModify.setPreferredSize(new Dimension(120, 37));
//    btnModify.setToolTipText("Modify (F4)");
//    btnModify.setText("<html><center><b>Modify </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
//                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");

    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+"(F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                   "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelProductGroupModify_btnAdd_actionAdapter(this));

//    btnModify.addActionListener(new
//        PanelProductGroupModify_btnModify_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+"(F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
        "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new
        PanelProductGroupModify_btnCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+"(F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear")+"(F7)");
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                     "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnClear.addActionListener(new
                               PanelProductGroupModify_btnClear_actionAdapter(this));

    btnDelete.addActionListener(new
        PanelProductGroupModify_btnDelete_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new
                              PanelProductGroupModify_btnDone_actionAdapter(this));
    pnlCenter.setLayout(borderLayout2);
    pnlTable.setPreferredSize(new Dimension(10, 100));
    pnlTable.setLayout(borderLayout4);
    pnlInfo.setPreferredSize(new Dimension(10, 180));
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
    txtCreatedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtCreatedDate.setPreferredSize(new Dimension(175, 22));
    txtCreatedDate.setEditable(false);
//    btnReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    btnReceiptDate.setMinimumSize(new Dimension(30, 23));
//    btnReceiptDate.setPreferredSize(new Dimension(30, 23));
//    btnReceiptDate.setText("jButton1");
//    btnReceiptDate.addActionListener(new
//        PanelProductGroupModify_btnReceiptDate_actionAdapter(this));
//    btnCreatedDate.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
//    btnCreatedDate.setPreferredSize(new Dimension(30, 23));
//    btnCreatedDate.setText("jButton1");
//    btnCreatedDate.addActionListener(new
//        PanelProductGroupModify_btnCreatedDate_actionAdapter(this));
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
//    txtTerm.setBackground(Color.white);
//    txtTerm.setEnabled(true);
//    txtTerm.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    txtTerm.setPreferredSize(new Dimension(180, 20));
//    txtTerm.addKeyListener(new PanelProductGroupModify_txtTerm_keyAdapter(this));
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
        PanelProductGroupModify_tblProdGroup_mouseAdapter(this));
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDesc.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDesc.setMinimumSize(new Dimension(635, 15));
    txtDesc.setPreferredSize(new Dimension(590, 38));
    txtDesc.setLineWrap(true);
    txtDesc.setWrapStyleWord(true);
//    btnPrint.setPreferredSize(new Dimension(120, 37));
//    btnPrint.setToolTipText("Print (F3)");
//    btnPrint.setText("<html><center><b>Print </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
//                     ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
//    btnPrint.addActionListener(new
//                               PanelProductGroupModify_btnPrint_actionAdapter(this));
//    jLabel15.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    jLabel15.setPreferredSize(new Dimension(80, 22));
//    jLabel15.setText("Term:");
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setPreferredSize(new Dimension(176, 22));
    cboStatus.setPopupVisible(false);
    cboStatus.addItemListener(new
                              PanelProductGroupModify_cboStatus_itemAdapter(this));
    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboType.setPreferredSize(new Dimension(176, 22));
    cboSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSupplier.setPreferredSize(new Dimension(140, 22));
    btnSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSupplier.setPreferredSize(new Dimension(30, 25));
    btnSupplier.setText("...");
    btnSupplier.addActionListener(new
        PanelProductGroupModify_btnSupplier_actionAdapter(this));
    flowLayout5.setAlignment(FlowLayout.RIGHT);
//    cboStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    cboStore.setPreferredSize(new Dimension(140, 22));
//    btnStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnStore.setPreferredSize(new Dimension(30, 23));
//    btnStore.setText("..");
//    btnStore.addActionListener(new
//                               PanelProductGroupModify_btnStore_actionAdapter(this));
    pnlDesc.setPreferredSize(new Dimension(742, 80));
    flowLayout3.setAlignment(FlowLayout.LEFT);
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel10.setMaximumSize(new Dimension(40, 16));
    jLabel10.setMinimumSize(new Dimension(40, 16));
    jLabel10.setPreferredSize(new Dimension(80, 22));
    jLabel10.setToolTipText("");
    jLabel10.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel10.setText(lang.getString("UPC")+":");
    jLabel10.setVerticalAlignment(SwingConstants.CENTER);
    txtUPC.setBackground(Color.white);
    txtUPC.setEnabled(true);
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUPC.setPreferredSize(new Dimension(140, 22));
    txtUPC.setRequestFocusEnabled(true);
    txtUPC.setDisabledTextColor(new Color(236, 233, 216));
    txtUPC.setEditable(true);
    txtUPC.setText("");
    txtUPC.addKeyListener(new PanelProductGroupModify_txtUPC_keyAdapter(this));
    btnUPC.setEnabled(false);
    btnUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnUPC.setPreferredSize(new Dimension(30, 22));
    btnUPC.setText("...");
    btnUPC.addActionListener(new PanelProductGroupModify_btnUPC_actionAdapter(this));
//    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    jLabel11.setForeground(Color.black);
//    jLabel11.setOpaque(false);
//    jLabel11.setPreferredSize(new Dimension(222, 22));
//    jLabel11.setRequestFocusEnabled(true);
//    jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
//    jLabel11.setText("Qty:                     ");
//    txtQty.setBackground(Color.white);
//    txtQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtQty.setPreferredSize(new Dimension(80, 22));
//    txtQty.setEditable(true);
//    txtQty.setText("");
//    txtQty.addKeyListener(new PanelProductGroupModify_txtQty_keyAdapter(this));
    pnlRightInfo.add(cboType, null);
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
    pnlLeftLabel.add(jLabel8);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightLabel, java.awt.BorderLayout.WEST);
    pnlRightLabel.add(jLabel7);
    pnlRightLabel.add(jLabel6);
    pnlRightLabel.add(jLabel10);
    //    pnlRightLabel.add(jLabel15, null);
    pnlLeftInfo.add(txtPGID);
//    pnlLeftInfo.add(cboStore, null);
//    pnlLeftInfo.add(btnStore, null);
    pnlLeftInfo.add(txtCreatedDate);
//    pnlLeftInfo.add(btnCreatedDate);
    pnlLeftInfo.add(cboSupplier);
    pnlLeftInfo.add(btnSupplier);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
//    pnlRightInfo.add(txtTerm);
    pnlRight.add(pnlRightInfo, java.awt.BorderLayout.CENTER);
    pnlLeft.add(pnlLeftInfo, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlRight, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlDesc, java.awt.BorderLayout.SOUTH);
    pnlDesc.add(jLabel1);
    pnlDesc.add(txtDesc);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.NORTH);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.CENTER);
    pnlRight.add(jPanel1, java.awt.BorderLayout.EAST);
    scrPanelTable.getViewport().add(tblProdGroup, null);
    this.add(pnlHeader, java.awt.BorderLayout.NORTH);
    registerKeyboardActions();
//    cboStatus.addItem("Approve");
//    cboStatus.addItem("Work Sheet");
//    cboStatus.addItem("Complete");
    initInfoForCombo();
    initTable();
  }

  void setDataToModify(String pgID) {
    initInfoForCombo();
    setDataToHeader(pgID);
    setDataToGrid(pgID);
    txtUPC.setText("");
//    txtQty.setText("");
    cboSupplier.setEnabled(false);
    btnSupplier.setEnabled(false);
    cboType.setEnabled(false);
    txtUPC.setEnabled(false);
//    txtQty.setEnabled(false);
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
//        cboStore.setSelectedData(rs.getString("STORE_ID"));
//        oldOrderDate = rs.getString("CREATE_DATE");
        txtCreatedDate.setText(rs.getString("CREATE_DATE"));
//        txtReceiptDate.setText(rs.getString("RECEIVED_DATE"));
        if(rs.getString("SUPP_ID")!=null && !rs.getString("SUPP_ID").equals("")){
          cboSupplier.setSelectedData(rs.getString("SUPP_ID"));
        }else{
          cboSupplier.setSelectedIndex(0);
        }
//        cboType.setSelectedData(rs.getString("PAYMENT_METHOD"));
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
//        else if (status.equalsIgnoreCase("C")) {
//          cboStatus.setSelectedItem("Complete");
//        }
//        txtTerm.setText(rs.getString("TERMS"));
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
//    String sqlStr = "Select inv.Item_Id, mas.Art_No UPC, mas.Item_Name ,mas.Standard_UOM, mas.Pack_Size, inv.Qty_Expected QTY, inv.Unit_Cost "
//        + " From BTM_IM_PROD_GROUP_ITEM inv, BTM_IM_ITEM_MASTER mas "
//        + " Where  inv.Item_Id = mas.Item_Id and PROD_GROUP_ID='" + pgID + "' order by upper(mas.Item_Name)";

    String sqlStr = " select mas.item_id,mas.art_no,mas.item_name,itemsup.SUPP_NAME, pro.child_name 	"
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

      while (rs.next()) {
        itemID = rs.getString("ITEM_ID");
        itemName = rs.getString("ITEM_NAME");
        upc = rs.getString("art_no");
        childName = rs.getString("child_name");
//        pSize = rs.getString("PACK_SIZE");
//        qty = rs.getString("QTY");
//        unitCost = rs.getString("UNIT_COST");
        dmProdGroup.addRow(new Object[] {itemID, upc, itemName, childName});
      }
      stmt.close();
      rs.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  private void initInfoForCombo() {
//    cboStore.setData(getStoreData());
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

  private void initTable() {
    //define for table
    String[] columnNames = new String[] {
        lang.getString("ItemID"), lang.getString("UPC"), lang.getString("ItemName"), lang.getString("Category")};
    dmProdGroup.setDataVector(new Object[][] {}
                                  , columnNames);
    tblProdGroup.setColumnWidth(UPC, 110);
    tblProdGroup.setColumnWidth(ITEM_NAME, 150);
    tblProdGroup.setColumnWidth(CHILD_NAME, 40);
//    tblProdGroup.setColumnWidth(PACK_SIZE, 40);
//    tblProdGroup.setColumnWidth(QTY, 60);
//    tblProdGroup.setColumnWidth(UNIT_COST, 60);
    tblProdGroup.setColor(Color.lightGray, Color.white);
  }

  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_ITEM_SEASON_SETUP);
    btnDelete.setEnabled(er.getDelete());
    btnAdd.setEnabled(er.getAdd());
//    btnModify.setEnabled(er.getModify());
    //  btnAdminRoleReport.setEnabled(er.getReport());
  }

  void showButton() {
    this.pnlHeader.removeAll();
    this.pnlHeader.updateUI();
    pnlHeader.add(btnDone, null);
//    if (FLAG_MODIFY == false) {
      pnlHeader.add(btnAdd, null);
//    }
//    else {
//      pnlHeader.add(btnModify, null);
//    }
//    pnlHeader.add(btnPrint, null);
    pnlHeader.add(btnClear, null);
    pnlHeader.add(btnDelete, null);
    pnlHeader.add(btnCancel, null);
  }

  public void btnCancel_actionPerformed(ActionEvent e) {
//    System.out.println("==============================GROUP");
    //clear data on grid
    while (dmProdGroup.getRowCount() > 0) {
      dmProdGroup.removeRow(0);
    }
    //show Dialog Promo Search
    DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(frmMain,
        lang.getString("TT0006"), true, frmMain, frmMain.pgBusObj);
    dlgProdGroupSearch.setLocation(112, 85);
    dlgProdGroupSearch.updateScreen();
    dlgProdGroupSearch.setVisible(true);
    if (dlgProdGroupSearch.done) {
      frmMain.showScreen(Constant.SCRS_PROD_GROUP_MODIFY);
    }
    else {
      frmMain.showScreen(Constant.SCRS_PROD_GROUP);
    }
//    FLAG_MODIFY = false;
  }

//  public void btnCreatedDate_actionPerformed(ActionEvent e) {
//    int posX, posY, posXFrame, posYFrame;
//    posXFrame = this.frmMain.getX();
//    posYFrame = this.frmMain.getY();
//    posX = this.btnCreatedDate.getY() + posYFrame + 90;
//    posY = this.btnCreatedDate.getY() + posYFrame + 90;
//    TimeDlg startdate = new TimeDlg(null);
//    startdate.setTitle("Choose Created Date");
//    startdate.setResizable(false);
//    startdate.setLocation(posX, posY);
//    startdate.setVisible(true);
//    if (startdate.isOKPressed()) {
//      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
//          "dd/MM/yyyy");
//      String date = fd.format(startdate.getDate());
//      this.txtCreatedDate.setText(date);
//    }
//
//  }

//  public void btnReceiptDate_actionPerformed(ActionEvent e) {
//    int posX, posY, posXFrame, posYFrame;
//    posXFrame = this.frmMain.getX();
//    posYFrame = this.frmMain.getY();
//    posX = this.btnCreatedDate.getY() + posYFrame + 90;
//    posY = this.btnReceiptDate.getY() + posYFrame + 90;
//    TimeDlg startdate = new TimeDlg(null);
//    startdate.setTitle("Choose Receipt Date");
//    startdate.setResizable(false);
//    startdate.setLocation(posX, posY);
//    startdate.setVisible(true);
//    if (startdate.isOKPressed()) {
//      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
//          "dd/MM/yyyy");
//      String date = fd.format(startdate.getDate());
//      this.txtReceiptDate.setText(date);
//    }
//
//  }

  private void resetData() {
//    cboStore.setSelectedIndex(0);
    cboStatus.setSelectedIndex(0);
//    txtCreatedDate.setText("");
//    txtReceiptDate.setText("");
//    txtTerm.setText("");
    txtDesc.setText("");
    txtUPC.setText("");
//    txtQty.setText("");
//    FLAG_MODIFY = false; // not allow hit F4 to modify
    txtUPC.setEnabled(true);
    btnUPC.setEnabled(true);
    showButton();
  }

//  private boolean checkExist(String promoName) {
//    ResultSet rs = null;
//    String sqlStr = "Select UPC From BTM_IM_PROMO_HEAD Where UPC='" + promoName +
//        "'";
//    try {
//      stmt = DAL.getConnection().createStatement();
//      rs = DAL.executeQueries(sqlStr, stmt);
//      if (rs.next()) {
//        stmt.close();
//        return true;
//      }
//      stmt.close();
//    }
//    catch (Exception ex) {}
//    return false;
//  }

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
//    storeID = cboStore.getSelectedData();
//    orderDate = txtCreatedDate.getText();
//    receiptDate = txtReceiptDate.getText();
//    term = txtTerm.getText();
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
//      qty = tblProdGroup.getValueAt(i, QTY).toString();
//      unitCost = tblProdGroup.getValueAt(i, UNIT_COST).toString();
      strSql = "insert into BTM_IM_PROD_GROUP_ITEM (PROD_GROUP_ID, ITEM_ID,SEQ) values ('"
          + pgID + "','" + itemID + "',"+ i +")";
//      System.out.println(strSql);
      vSql.addElement(strSql);
    }
  }

  public void btnDone_actionPerformed(ActionEvent e) {
    String orderDate = txtCreatedDate.getText().trim();
//    String receiptDate = txtReceiptDate.getText().trim();
//    if (!ut.checkDate(orderDate, "/") || orderDate.equals("")) {
//      ut.showMessage(frmMain, "Warning",
//                     "Order date must be a date type. It is not null");
//      txtCreatedDate.requestFocus();
//      txtCreatedDate.setSelectionStart(0);
//      txtCreatedDate.setSelectionEnd(txtCreatedDate.getText().length());
//      return;
//    }
//    if (ut.compareDate(oldOrderDate, orderDate)) {
//      ut.showMessage(frmMain, "Warning",
//                     "Order date must be greater than or equal old order date.");
//      txtCreatedDate.requestFocus();
//      txtCreatedDate.setSelectionStart(0);
//      txtCreatedDate.setSelectionEnd(txtCreatedDate.getText().length());
//      return;
//    }
//    if (!ut.checkDate(receiptDate, "/") || receiptDate.equals("")) {
//      ut.showMessage(frmMain, "Warning",
//                     "Input receipt date must be a date type. It is not null");
//      txtReceiptDate.requestFocus();
//      txtReceiptDate.setSelectionStart(0);
//      txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
//      return;
//    }
//    if (ut.compareDate(orderDate, receiptDate)) {
//      ut.showMessage(frmMain, "Warning",
//                     "Receipt date must be greater than or equal order date ");
//      txtReceiptDate.requestFocus();
//      txtReceiptDate.setSelectionStart(0);
//      txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
//      return;
//    }
    if (tblProdGroup.getRowCount() > 0) {
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
    returnPreviousScreen();
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
//    String[] arrInfo = new String[] {
//        txtDesc.getText(), txtCreatedDate.getText(),
//        "", ""};
//    dlgProdGroupSearch.updateRowOnTable(arrInfo);
    dlgProdGroupSearch.setVisible(true);
//    FLAG_MODIFY = false;
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
      txtUPC.setText(tblProdGroup.getValueAt(row, UPC).toString());
//      txtQty.setText("");
//      FLAG_MODIFY = true; // allow hit F4 to modify
      txtUPC.setEnabled(false);
      btnUPC.setEnabled(false);
//      txtQty.requestFocus(true);
      showButton();
    }
  }

  public void btnDelete_actionPerformed(ActionEvent e) {
    int row = tblProdGroup.getSelectedRow();
    if (row >= 0) {
      int choose = ut.showMessage(frmMain, lang.getString("TT020"),
                                  lang.getString("MS1076_DeleteUPC") +
                                  tblProdGroup.getValueAt(row, UPC).
                                  toString() + "' ?", 3,
                                  DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.YES_VALUE) {
        dmProdGroup.removeRow(row);
      }
    }
    else {
      if (tblProdGroup.getRowCount() > 0) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1077_ChooseUPCWantToDelete"));
        return;
      }
    }
  }

  public void tblProdGroup_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      if (btnDone.isEnabled()) {
        updateValueFromSelecledItemInTable();
//        txtQty.setEnabled(true);
//        FLAG_MODIFY = true;
        showButton();
      }
    }
  }

//  public void btnModify_actionPerformed(ActionEvent e) {
//    int row = tblProdGroup.getSelectedRow();
//    if (row >= 0) {
//      if (txtQty.getText().trim().equalsIgnoreCase("")) {
//        ut.showMessage(frmMain, "Warning",
//                       "The Quantity can not null.");
//        txtQty.requestFocus();
//        return;
//      }
//      double qty = Double.parseDouble(txtQty.getText());
//      if (qty <= 0) {
//        ut.showMessage(frmMain, "Warning",
//                       "The Quantity can not less than or equal 0.");
//        txtQty.requestFocus();
//        txtQty.setSelectionStart(0);
//        txtQty.setSelectionEnd(txtQty.getText().length());
//        return;
//      }
//      tblProdGroup.setValueAt(String.valueOf(qty), row, QTY);
//      txtUPC.setText("");
//      txtQty.setText("");
////      FLAG_MODIFY = false;
//      showButton();
//      txtUPC.setEnabled(true);
//      btnUPC.setEnabled(true);
//      tblProdGroup.requestFocus(true);
//    }
//  }
  //check wherether upc is on grid
//  boolean checkExistOnGridModify(String upc) {
//    for (int i = 0; i < dmProdGroup.getRowCount(); i++) {
//      if (i != tblProdGroup.getSelectedRow() &&
//          dmProdGroup.getValueAt(i, UPC).toString().equals(upc.trim()))
//        return true;
//    }
//    return false;
//  }

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
//        if (FLAG_MODIFY == false) {
          btnAdd.doClick();
//        }
      }
      else if (identifier.intValue() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {
//        btnPrint.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F4) {
//        if (FLAG_MODIFY == true) {
//          btnModify.doClick();
//        }
//        else {
//          updateValueFromSelecledItemInTable();
//        }
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
      rs.close();
    }
    catch (Exception ex) {}
    return paymentID;
  }

//  void btnStore_actionPerformed(ActionEvent e) {
//    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,
//        lang.getString("TT014")+" - STORE SEARCH", true, frmMain, frmMain.storeBusObj);
//    dlgStoreSearch.setVisible(true);
//    if (dlgStoreSearch.done) {
//      cboStore.setSelectedData(dlgStoreSearch.getData());
//    }
//  }

  void btnUPC_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0005"), true, frmMain, itemBusObject);
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
      txtUPC.setText("");
//      txtQty.setText("");
    }
  }

  void insertItemToGrid(String upc) {
//    String strSql = "Select mas.Item_Id,mas.Art_No UPC,mas.Item_Name,mas.Standard_Uom,mas.Pack_Size, 1 Qty,itmsup.Unit_Cost "
//        + "    From BTM_IM_ITEM_MASTER mas, "
//        + "        (select distinct isup.Item_Id,isup.Unit_Cost  "
//        +
//        "         from BTM_IM_ITEM_LOC_SUPPLIER isup, BTM_IM_SUPPLIER sup "
//        + "	  where isup.SUPP_ID=sup.SUPP_ID and sup.SUPP_ID ='" +
//        cboSupplier.getSelectedData() + "') itmsup "
//        +
//        "    where mas.Item_Id=itmsup.ITEM_ID and mas.Status='A' and mas.Art_No ='" +
//        upc + "'";
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

//  void txtQty_keyTyped(KeyEvent e) {
//    char key = e.getKeyChar();
//    ut.checkNumberUpdate(e, txtQty.getText());
//  }

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

//  void printReceipt(String PPOID, String sOrderDay, String sReceiptDay,
//                    String StoreID, String StoreName, String SupplierID,
//                    BJTable tbl,
//                    DataAccessLayer DAL) {}

//  void btnPrint_actionPerformed(ActionEvent e) {
//    printReceipt(txtPGID.getText(), txtCreatedDate.getText(),
//                 txtReceiptDate.getText(), cboStore.getSelectedData().toString(),
//                 cboStore.getSelectedItem().toString(),
//                 cboSupplier.getSelectedData().toString(), tblProdGroup,
//                 DAL);
//  }

  void cboStatus_itemStateChanged(ItemEvent e) {
    if (cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approve") ||
        cboStatus.getSelectedItem().toString().equalsIgnoreCase("Complete")) {
//      btnPrint.setEnabled(true);
    }
    else {
//      btnPrint.setEnabled(false);
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
//    if(txtQty.getText().trim().equalsIgnoreCase("")){
//       ut.showMessage(frmMain, "Warning",
//                    "The Quantity can not null." );
//       txtQty.requestFocus();
//       return;
//    }
//    double qty = Double.parseDouble(txtQty.getText().trim());
//    if(qty <= 0){
//       ut.showMessage(frmMain, "Warning",
//                    "The Quantity can not less than or equal 0." );
//       txtQty.requestFocus();
//       txtQty.setSelectionStart(0);
//       txtQty.setSelectionEnd(txtQty.getText().length());
//       return;
//    }
//    String strSql = "Select mas.Item_Id,mas.Art_No UPC,mas.Item_Name,mas.Standard_Uom,mas.Pack_Size, " + String.valueOf(qty) + " Qty,itmsup.Unit_Cost "
//              + "    From BTM_IM_ITEM_MASTER mas, "
//              + "        (select distinct isup.Item_Id,isup.Unit_Cost  "
//              + "         from BTM_IM_ITEM_LOC_SUPPLIER isup, BTM_IM_SUPPLIER sup "
//              + "	  where isup.SUPP_ID=sup.SUPP_ID and sup.SUPP_ID ='"+ cboSupplier.getSelectedData() + "') itmsup "
//              + "    where mas.Item_Id=itmsup.ITEM_ID and mas.Status='A' and mas.Art_No = '" + upc + "'";

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
    txtUPC.setText("");
//    txtQty.setText("");
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    String orderDate = txtCreatedDate.getText().trim();
//    String receiptDate = txtReceiptDate.getText().trim();
    String upc = txtUPC.getText().trim();

//    if (!ut.checkDate(orderDate, "/") || orderDate.equals("")) {
//      ut.showMessage(frmMain, "Warning",
//                     "Order date must be a date type. It is not null");
//      txtCreatedDate.requestFocus();
//      txtCreatedDate.setSelectionStart(0);
//      txtCreatedDate.setSelectionEnd(txtCreatedDate.getText().length());
//      return;
//    }
//    if (ut.compareDate(oldOrderDate, orderDate)) {
//      ut.showMessage(frmMain, "Warning",
//                     "Order date must be greater than or equal old order date.");
//      txtCreatedDate.requestFocus();
//      txtCreatedDate.setSelectionStart(0);
//      txtCreatedDate.setSelectionEnd(txtCreatedDate.getText().length());
//      return;
//    }
//    if (!ut.checkDate(receiptDate, "/") || receiptDate.equals("")) {
//      ut.showMessage(frmMain, "Warning",
//                     "Input receipt date must be a date type. It is not null");
//      txtReceiptDate.requestFocus();
//      txtReceiptDate.setSelectionStart(0);
//      txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
//      return;
//    }
//    if (ut.compareDate(orderDate, receiptDate)) {
//      ut.showMessage(frmMain, "Warning",
//                     "Receipt date must be greater than or equal order date ");
//      txtReceiptDate.requestFocus();
//      txtReceiptDate.setSelectionStart(0);
//      txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
//      return;
//    }

    if (upc.equalsIgnoreCase("")) {
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
                       lang.getString("MS1151_UPC_NotExistSupplier")
                       + cboSupplier.getSelectedItem().toString() +
                       lang.getString("MS1152_UPC_ItemInvalid"));
        txtUPC.requestFocus();
        txtUPC.setSelectionStart(0);
        txtUPC.setSelectionEnd(txtUPC.getText().length());
        return;
      }
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

//  void txtTerm_keyTyped(KeyEvent e) {
////     ut.limitLenjTextField(e, txtTerm,15);
//  }
}


//class PanelProductGroupModify_btnPrint_actionAdapter
//    implements java.awt.event.ActionListener {
//  private PanelProductGroupModify adaptee;
//  PanelProductGroupModify_btnPrint_actionAdapter(PanelProductGroupModify
//                                                  adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnPrint_actionPerformed(e);
//  }
//}

class PanelProductGroupModify_btnClear_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelProductGroupModify adaptee;
  PanelProductGroupModify_btnClear_actionAdapter(PanelProductGroupModify
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelProductGroupModify_tblProdGroup_mouseAdapter
    extends MouseAdapter {
  private PanelProductGroupModify adaptee;
  PanelProductGroupModify_tblProdGroup_mouseAdapter(
      PanelProductGroupModify adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblProdGroup_mouseClicked(e);
  }
}

//class PanelProductGroupModify_btnModify_actionAdapter
//    implements ActionListener {
//  private PanelProductGroupModify adaptee;
//  PanelProductGroupModify_btnModify_actionAdapter(PanelProductGroupModify
//      adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnModify_actionPerformed(e);
//  }
//}

class PanelProductGroupModify_btnDelete_actionAdapter
    implements ActionListener {
  private PanelProductGroupModify adaptee;
  PanelProductGroupModify_btnDelete_actionAdapter(PanelProductGroupModify
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelProductGroupModify_btnDone_actionAdapter
    implements ActionListener {
  private PanelProductGroupModify adaptee;
  PanelProductGroupModify_btnDone_actionAdapter(PanelProductGroupModify
                                                 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

//class PanelProductGroupModify_btnReceiptDate_actionAdapter
//    implements ActionListener {
//  private PanelProductGroupModify adaptee;
//  PanelProductGroupModify_btnReceiptDate_actionAdapter(
//      PanelProductGroupModify adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnReceiptDate_actionPerformed(e);
//  }
//}

//class PanelProductGroupModify_btnCreatedDate_actionAdapter
//    implements ActionListener {
//  private PanelProductGroupModify adaptee;
//  PanelProductGroupModify_btnCreatedDate_actionAdapter(PanelProductGroupModify
//      adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnCreatedDate_actionPerformed(e);
//  }
//}

class PanelProductGroupModify_btnCancel_actionAdapter
    implements ActionListener {
  private PanelProductGroupModify adaptee;
  PanelProductGroupModify_btnCancel_actionAdapter(PanelProductGroupModify
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelProductGroupModify_btnSupplier_actionAdapter
    implements java.awt.event.ActionListener {
  PanelProductGroupModify adaptee;

  PanelProductGroupModify_btnSupplier_actionAdapter(PanelProductGroupModify
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplier_actionPerformed(e);
  }
}

//class PanelProductGroupModify_btnStore_actionAdapter
//    implements java.awt.event.ActionListener {
//  PanelProductGroupModify adaptee;
//
//  PanelProductGroupModify_btnStore_actionAdapter(PanelProductGroupModify
//                                                  adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnStore_actionPerformed(e);
//  }
//}

class PanelProductGroupModify_btnUPC_actionAdapter
    implements java.awt.event.ActionListener {
  PanelProductGroupModify adaptee;

  PanelProductGroupModify_btnUPC_actionAdapter(PanelProductGroupModify
                                                adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnUPC_actionPerformed(e);
  }
}

//class PanelProductGroupModify_txtQty_keyAdapter
//    extends java.awt.event.KeyAdapter {
//  PanelProductGroupModify adaptee;
//
//  PanelProductGroupModify_txtQty_keyAdapter(PanelProductGroupModify adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtQty_keyTyped(e);
//  }
//}

class PanelProductGroupModify_txtUPC_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelProductGroupModify adaptee;

  PanelProductGroupModify_txtUPC_keyAdapter(PanelProductGroupModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }
}

class PanelProductGroupModify_cboStatus_itemAdapter implements java.awt.event.ItemListener {
  PanelProductGroupModify adaptee;

  PanelProductGroupModify_cboStatus_itemAdapter(PanelProductGroupModify adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.cboStatus_itemStateChanged(e);
  }
}

class PanelProductGroupModify_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelProductGroupModify adaptee;

  PanelProductGroupModify_btnAdd_actionAdapter(PanelProductGroupModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

//class PanelProductGroupModify_txtTerm_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelProductGroupModify adaptee;
//
//  PanelProductGroupModify_txtTerm_keyAdapter(PanelProductGroupModify adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtTerm_keyTyped(e);
//  }
//}
