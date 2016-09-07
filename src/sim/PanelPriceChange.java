package sim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import common.*;
import btm.attr.*;
import btm.swing.*;
import java.util.*;
import btm.swing.*;
import java.text.DateFormat;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.ResourceBundle;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Price Change</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: BTM International Software</p>
 * @author Phuong Nguyen
 * @version 1.0
 */
public class PanelPriceChange extends JPanel {
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_SIZE = 2;
  private static final int COL_STORE_ID = 3;
  private static final int COL_STORE_NAME = 4;
  private static final int COL_NEW_PRICE = 5; //
  private static final int COL_EFFECTIVE_DATE = 6;
  private static final int COL_CLOSE_DATE = 7;

  //Column Order of UnitCost History
  private static final int COL_HIS_STORE = 0; //
  private static final int COL_HIS_OLD_PRICE = 1; //
  private static final int COL_HIS_NEW_PRICE = 2;
  private static final int COL_HIS_EFFECT_DATE = 3;
  private static final int COL_HIS_CLOSED_DATE = 4;
  private static final int COL_HIS_STATUS = 5;

  private static final int ROW_NUM = 4;
  DataAccessLayer DAL;
  Statement stmt = null;
  Utils ut = new Utils();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  String childLocationID = "";
  Vector vSqlIns = new Vector();
  Vector vSqlDel = new Vector();
  Vector vSqlUpd = new Vector();
  double oldPrice = 0;
  //define for panel
  FrameMainSim frmMain;
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel jPnlHeader = new BJPanel(Constant.PANEL_BLUE_HEADER);
  JPanel jPnlInfo = new JPanel();
  JPanel jPnlTbl = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPnlInfoTop = new JPanel();
  JPanel jPnlInfoCenter = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPnlInfoTopRight = new JPanel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JLabel lblStoreID = new JLabel();
  JLabel lblItemUpc = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea txtDescription = new JTextArea();
  JButton btnItemUpc = new JButton();
  JTextField txtItemUpc = new JTextField();
  JLabel lblClosedDate = new JLabel();
  JLabel lblEffDate = new JLabel();
  JLabel lblNewPrice = new JLabel();
  JButton btnClosedDate = new JButton();
  JTextField txtClosedDate = new JTextField();
  JButton btnEffDate = new JButton();
  JTextField txtEffDate = new JTextField();
  JTextField txtNewPrice = new JTextField();
  JScrollPane jScrollPane2 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true){
    public boolean isCellEditable(int row,int col){
      return false;
    }
    public Class getColumnClass(int col){
      switch(col){
        case COL_NEW_PRICE: return Long.class;
        //case 2: return Long.class;
        default: return Object.class;
      }
    }
  };
  SortableTableModel dmHis = new SortableTableModel();
  BJTable jTblHistory = new BJTable(dmHis,false){
    public boolean isCellEditable(int row,int col){
      return false;
    }
    public Class getColumnClass(int col){
      switch(col){
        case COL_HIS_OLD_PRICE: return Long.class;
        //case 2: return Long.class;
        case COL_HIS_NEW_PRICE: return Long.class;
        //case 2: return Long.class;
        default: return Object.class;
      }
    }
  };

  BJButton btnCancel = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  String attr1 = "";
  String attr2 = "";
  BorderLayout borderLayout6 = new BorderLayout();
  JLabel lblOldPrice = new JLabel();
  JTextField txtOldPrice = new JTextField();
  JLabel lblUnitCost = new JLabel();
  JTextField txtUnitCost = new JTextField();
  JPanel jPnlInfoTopLeft = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JLabel lblChangeDate = new JLabel();
  JTextField txtChangeDate = new JTextField();
  JButton btnChangeDate = new JButton();
  JLabel lblDescription = new JLabel();
  Border border1 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border2 = new TitledBorder(border1, "Price History");
  JScrollPane jScrollPane3 = new JScrollPane();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  BJComboBox cboStore = new BJComboBox();
  public PanelPriceChange(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setPreferredSize(new Dimension(800, 600));
    this.setLayout(borderLayout1);
    jPnlHeader.setBackground(new Color(121, 152, 198));
    jPnlHeader.setAlignmentY((float) 0.5);
    jPnlHeader.setPreferredSize(new Dimension(800, 50));
    jPnlInfo.setBackground(SystemColor.control);
    jPnlInfo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    jPnlInfo.setPreferredSize(new Dimension(800, 335));
    jPnlInfo.setLayout(borderLayout2);
    jPnlTbl.setBackground(SystemColor.control);
    jPnlTbl.setPreferredSize(new Dimension(800, 200));
    jPnlTbl.setLayout(borderLayout6);
    jPnlInfoTop.setBackground(SystemColor.control);
    jPnlInfoTop.setPreferredSize(new Dimension(800, 125));
    jPnlInfoTop.setLayout(borderLayout3);
    jPnlInfoCenter.setBackground(SystemColor.control);
    jPnlInfoCenter.setPreferredSize(new Dimension(800, 25));
    jPnlInfoCenter.setLayout(null);
    jPanel7.setPreferredSize(new Dimension(150, 125));
    jPanel7.setLayout(null);
    jPanel9.setPreferredSize(new Dimension(265, 125));
    jPanel9.setLayout(null);
    jPnlInfoTopRight.setPreferredSize(new Dimension(400, 125));
    jPnlInfoTopRight.setLayout(borderLayout4);
    jPanel10.setMinimumSize(new Dimension(200, 35));
    jPanel10.setPreferredSize(new Dimension(250, 125));
    jPanel10.setLayout(null);
    jPanel11.setPreferredSize(new Dimension(135, 125));
    jPanel11.setLayout(null);
    lblStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStoreID.setPreferredSize(new Dimension(90, 20));
    lblStoreID.setText(lang.getString("AppliedToStore")+":");
    lblStoreID.setBounds(new Rectangle(15, 13, 104, 22));
    lblItemUpc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemUpc.setPreferredSize(new Dimension(90, 20));
    lblItemUpc.setText(lang.getString("UPC")+":");
    lblItemUpc.setBounds(new Rectangle(41, 13, 90, 20));
    jScrollPane1.setPreferredSize(new Dimension(600, 15));
    jScrollPane1.setBounds(new Rectangle(178, 3, 535, 22));
    txtDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDescription.setText("");
    txtDescription.setTabSize(8);
    btnItemUpc.setBounds(new Rectangle(183, 13, 30, 22));
    btnItemUpc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnItemUpc.setPreferredSize(new Dimension(30, 20));
    btnItemUpc.setText("...");
    btnItemUpc.addActionListener(new PanelPriceChange_btnItemID_actionAdapter(this));
    txtItemUpc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemUpc.setPreferredSize(new Dimension(150, 20));
    txtItemUpc.setText("");
    txtItemUpc.setBounds(new Rectangle(28, 13, 150, 22));
    txtItemUpc.addFocusListener(new PanelPriceChange_txtItemUpc_focusAdapter(this));
    txtItemUpc.addKeyListener(new PanelPriceChange_txtItemUpc_keyAdapter(this));
    lblClosedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblClosedDate.setPreferredSize(new Dimension(90, 20));
    lblClosedDate.setText(lang.getString("ClosedDate")+":");
    lblClosedDate.setBounds(new Rectangle(15, 93, 90, 23));
    lblEffDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEffDate.setPreferredSize(new Dimension(90, 20));
    lblEffDate.setText(lang.getString("EffectDate")+":");
    lblEffDate.setBounds(new Rectangle(15, 66, 90, 23));
    lblNewPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblNewPrice.setPreferredSize(new Dimension(90, 20));
    lblNewPrice.setText(lang.getString("NewPrice")+":");
    lblNewPrice.setBounds(new Rectangle(41, 93, 90, 20));
    btnClosedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClosedDate.setPreferredSize(new Dimension(30, 20));
    btnClosedDate.setText("...");
    btnClosedDate.setBounds(new Rectangle(148, 93, 30, 23));
    btnClosedDate.setEnabled(false);
    btnClosedDate.addActionListener(new PanelPriceChange_btnClosedDate_actionAdapter(this));
    btnEffDate.setBounds(new Rectangle(148, 66, 30, 23));
    btnEffDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnEffDate.setPreferredSize(new Dimension(30, 20));
    btnEffDate.setText("...");
    btnEffDate.addActionListener(new PanelPriceChange_btnEffDate_actionAdapter(this));
    txtEffDate.setBackground(Color.white);
    txtEffDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEffDate.setPreferredSize(new Dimension(160, 20));
    txtEffDate.setEditable(false);
    txtEffDate.setText("");
    txtEffDate.setBounds(new Rectangle(1, 66, 144, 23));
    txtNewPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtNewPrice.setPreferredSize(new Dimension(195, 20));
    txtNewPrice.setText("");
    txtNewPrice.setBounds(new Rectangle(28, 93, 186, 23));
    txtNewPrice.addFocusListener(new PanelPriceChange_txtNewPrice_focusAdapter(this));
    txtNewPrice.addKeyListener(new PanelPriceChange_txtNewPrice_keyAdapter(this));
    txtClosedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtClosedDate.setPreferredSize(new Dimension(160, 20));
    txtClosedDate.setEditable(false);
    txtClosedDate.setBackground(Color.white);
    txtClosedDate.setText("7/7/7777");
    txtClosedDate.setBounds(new Rectangle(1, 93, 144, 23));
    jScrollPane2.setPreferredSize(new Dimension(800, 240));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+"(F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelPriceChange_btnCancel_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setToolTipText(lang.getString("Add")+"(F2)");
    btnAdd.setText(lang.getString("Add")+"(F2)");
    btnAdd.addActionListener(new PanelPriceChange_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnDone.setText(lang.getString("Done")+"(F1)");
    btnDone.addActionListener(new PanelPriceChange_btnDone_actionAdapter(this));
    lblOldPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblOldPrice.setPreferredSize(new Dimension(90, 20));
    lblOldPrice.setText(lang.getString("SellingPrice")+":");
    lblOldPrice.setBounds(new Rectangle(41, 66, 90, 20));
    txtOldPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtOldPrice.setPreferredSize(new Dimension(195, 20));
    txtOldPrice.setEditable(false);
    txtOldPrice.setText("");
    txtOldPrice.setBounds(new Rectangle(28, 66, 185, 23));
    txtOldPrice.addKeyListener(new PanelPriceChange_txtUnitCost_keyAdapter(this));
    lblUnitCost.setText(lang.getString("UnitCost")+":");
    lblUnitCost.setBounds(new Rectangle(41, 39, 90, 20));
    lblUnitCost.setPreferredSize(new Dimension(90, 20));
    lblUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUnitCost.setText("");
    txtUnitCost.setBounds(new Rectangle(28, 39, 185, 22));
    txtUnitCost.setPreferredSize(new Dimension(185, 20));
    txtUnitCost.setEditable(false);
    txtUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    table.addKeyListener(new PanelPriceChange_table_keyAdapter(this));
    jPnlInfoTopLeft.setPreferredSize(new Dimension(400, 125));
    jPnlInfoTopLeft.setLayout(borderLayout5);
    lblChangeDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblChangeDate.setText(lang.getString("ChangeDate")+":");
    lblChangeDate.setBounds(new Rectangle(15, 39, 104, 22));
    txtChangeDate.setEnabled(true);
    txtChangeDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtChangeDate.setEditable(false);
    txtChangeDate.setText(ut.getSystemDate(1));
    txtChangeDate.setBounds(new Rectangle(1, 39, 144, 22));
    btnChangeDate.setBounds(new Rectangle(148, 39, 30, 22));
    btnChangeDate.setEnabled(false);
    btnChangeDate.setText("...");
    lblDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDescription.setPreferredSize(new Dimension(90, 20));
    lblDescription.setText(lang.getString("Description")+":");
    lblDescription.setBounds(new Rectangle(41, 3, 90, 20));
    jScrollPane3.setPreferredSize(new Dimension(600, 20));
    jScrollPane3.setBounds(new Rectangle(58, 0, 654, 124));
    jPanel1.setLayout(borderLayout7);
    jPanel2.setLayout(null);
    jTblHistory.setRowHeight(23);
    jPanel1.setPreferredSize(new Dimension(800, 165));
    jPanel2.setPreferredSize(new Dimension(610, 20));
    cboStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboStore.setBounds(new Rectangle(1, 13, 177, 22));
    cboStore.addActionListener(new PanelPriceChange_cboStore_actionAdapter(this));
    jPanel10.add(txtUnitCost, null);
    jPanel10.add(txtOldPrice);
    jPanel10.add(txtNewPrice);
    jPanel10.add(txtItemUpc, null);
    jPanel10.add(btnItemUpc, null);
    jPnlInfoTopLeft.add(jPanel7, java.awt.BorderLayout.WEST);
    jPnlHeader.add(btnDone, null);
    jPnlHeader.add(btnAdd, null);
    jPnlHeader.add(btnCancel, null);
    this.add(jPnlInfo,  BorderLayout.CENTER);
    jPanel7.add(lblOldPrice);
    jPanel7.add(lblItemUpc, null);
    jPanel7.add(lblUnitCost, null);
    jPanel7.add(lblNewPrice);
    jPanel11.add(lblStoreID);
    jPanel11.add(lblEffDate, null);
    jPanel11.add(lblClosedDate, null);
    jPanel11.add(lblChangeDate);
    jPnlInfoTop.add(jPnlInfoTopLeft, java.awt.BorderLayout.WEST);
    this.add(jPnlTbl, BorderLayout.SOUTH);
    jPnlTbl.add(jScrollPane2, java.awt.BorderLayout.EAST);
    jScrollPane2.getViewport().add(table, null);
    jPnlInfoCenter.add(jScrollPane1);
    jPnlInfoCenter.add(lblDescription);
    jPanel1.add(jPnlInfoTop, java.awt.BorderLayout.NORTH);
    jScrollPane1.getViewport().add(txtDescription);

    jPanel9.add(txtClosedDate, null);
    jPanel9.add(txtEffDate, null);
    jPanel9.add(btnEffDate, null);
    jPanel9.add(btnClosedDate, null);
    jPanel9.add(txtChangeDate);
    jPanel9.add(btnChangeDate);
    jPanel9.add(cboStore);
    jPnlInfoTopRight.add(jPanel11, java.awt.BorderLayout.WEST);
    jPnlInfoTop.add(jPnlInfoTopRight, java.awt.BorderLayout.CENTER);
    jPnlInfoTopLeft.add(jPanel10, java.awt.BorderLayout.CENTER);
    jPnlInfoTopRight.add(jPanel9, java.awt.BorderLayout.CENTER);
    jPanel1.add(jPnlInfoCenter, java.awt.BorderLayout.CENTER);
    jPnlInfo.add(jPanel2, java.awt.BorderLayout.CENTER);
    jPanel2.add(jScrollPane3, null);
    jScrollPane3.getViewport().add(jTblHistory);
    this.add(jPnlHeader, java.awt.BorderLayout.NORTH);
    jPnlInfo.add(jPanel1, java.awt.BorderLayout.NORTH);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    dm.addColumn(lang.getString("ItemID"));
    dm.addColumn(lang.getString("UPC"));
    dm.addColumn(lang.getString("Size"));
    dm.addColumn(lang.getString("StoreID"));
    dm.addColumn(lang.getString("StoreName"));
    dm.addColumn(lang.getString("NewPrice"));
    dm.addColumn(lang.getString("EffectDate"));
    dm.addColumn(lang.getString("ClosedDate"));
    table.setRowHeight(30);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);
    cboStore.setData2(frmMain.storeBusObj.getData(DAL));
    frmMain.storeBusObj.getData(DAL).getStatement().close();
  }


//select item that will be changed Unit Cost
  //click button select Item_Id
  void btnItemID_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0095"), true, frmMain,itemBusObject);
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.cboStatus.setEditable(false);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done){
      txtItemUpc.setText(dlgItemLookup.getUPC());
      txtUnitCost.setText(ut.formatNumber(""+getUnitCost(dlgItemLookup.getItemID(),cboStore.getSelectedData())));
      double oldPrice = getNewPrice(dlgItemLookup.getItemID(),cboStore.getSelectedData());
      txtOldPrice.setText(ut.formatNumber("" + oldPrice));
      txtNewPrice.setText("");
      showHistory(ROW_NUM,getItemId(dlgItemLookup.getUPC()),cboStore.getSelectedData());
    }
  }
  //or enter Art_no into textbox
  void txtItemUpc_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtItemUpc,Constant.LENGHT_UPC_INPUT);
    ut.checkNumber(e);
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
      txtNewPrice.requestFocus(true);
    }
  }

  //after enter Art_no into textbox
  void txtItemUpc_focusLost(FocusEvent e) {
    txtNewPrice.setText("");
    if (txtItemUpc.getText().equalsIgnoreCase("") || !checkItemExist(txtItemUpc.getText())){
      txtUnitCost.setText("");
      txtOldPrice.setText("");
      while (dmHis.getRowCount()>0){
        dmHis.removeRow(0);
      }
      return;
    }

    String itemId = getItemId(txtItemUpc.getText());
    txtUnitCost.setText(ut.formatNumber("" + getUnitCost(itemId,cboStore.getSelectedData())));
    txtOldPrice.setText(ut.formatNumber("" + getNewPrice(itemId,cboStore.getSelectedData())));
    showHistory(ROW_NUM,getItemId(txtItemUpc.getText()),cboStore.getSelectedData());
  }

  // get Selling from BTM_IM_PRICE_HIST
  double getNewPrice(String itemId,String storeId){
    double oldPrice = 0;
//    Statement stmt = null;
    ResultSet rs = null;
    String sql = "Select a.NEW_PRICE From BTM_IM_PRICE_HIST a Where a.ITEM_ID ='"
        + itemId + "'and a.PRICE_EFF_DATE = (Select MAX(b.PRICE_EFF_DATE) From " +
        "BTM_IM_PRICE_HIST b where b.ITEM_ID= a.ITEM_ID and b.STATUS='1' and a.STORE_ID=b.STORE_ID)";
    try {
      if(!storeId.equals("")){
        sql = sql + " and a.STORE_ID='"+storeId+"'";
      }
//      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        oldPrice = rs.getDouble("NEW_PRICE");
      }
      rs.getStatement().close();
//      stmt.close();
    }catch (Exception ex) {
      ex.printStackTrace();
    }
    return oldPrice;
  }

  // get UnitCost from BTM_IM_UNIT_COST_HIST
  double getUnitCost(String itemId,String storeId){
    double unitCost = 0;
//    Statement stmt = null;
    ResultSet rs = null;
    String sql = "Select a.NEW_UNIT_COST From BTM_IM_UNIT_COST_HIST a Where a.ITEM_ID ='"
        + itemId + "'and a.UNIT_COST_EFF_DATE = (Select MAX(b.UNIT_COST_EFF_DATE) From " +
        "BTM_IM_UNIT_COST_HIST b where b.ITEM_ID= a.ITEM_ID and b.STATUS='1' and a.STORE_ID=b.STORE_ID)";
    try {
      if(!storeId.equals("")){
        sql=sql + " and a.STORE_ID='"+storeId+"'";
      }
//      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        unitCost = rs.getDouble("NEW_UNIT_COST");
      }
      rs.getStatement().close();
//      stmt.close();
    }catch (Exception ex) {
      ex.printStackTrace();
    }
    return unitCost;
  }

  //get Item_Id from BTM_IM_ITEM_MASTER
  //parameter Art_No : String
  String getItemId(String upc){
    String itemId = "";
    ResultSet rs = null;
//    Statement stmt = null;
    String sql ="Select ITEM_ID From BTM_IM_ITEM_MASTER Where ART_NO='" + upc + "'";
    try {
//      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        itemId = rs.getString("ITEM_ID");
      }
      rs.getStatement().close();
//      stmt.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return itemId;
  }

  //check the UPC exist
  boolean checkItemExist(String upc) {
    Statement stmt = null;
    ResultSet rs = null;
    String strSql = "Select Art_No From BTM_IM_ITEM_MASTER Where Art_No='" + upc + "' and STATUS ='A'";
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeScrollQueries(strSql, stmt);
      if (rs.next()) {
        return true;
      }
      rs.getStatement().close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

//enter new Unit Cost into textbox
  //check New_Unit_Cost is numberic
  void txtNewPrice_keyTyped(KeyEvent e) {
    ut.checkNumberPositive(e, txtNewPrice.getText());
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
     btnEffDate.requestFocus(true);
    }
  }

  void txtNewPrice_keyReleased(KeyEvent e) {
    ut.getYeroCode(1, 1);
    char key = e.getKeyChar();
    if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_BACK_SPACE)
      return;
    else if (key < '0' || key > '9') {
      if (key != ',' && key != '.') {
        return;
      }
    }
  }

  //after enter New_Unit_Cost into textbox
  void txtNewPrice_focusLost(FocusEvent e) {
    if (!txtNewPrice.getText().equalsIgnoreCase("")){
      txtNewPrice.setText(ut.formatNumber(txtNewPrice.getText()));
    }
  }

  //re_enter New_Unit_Cost into textbox
  void txtNewPrice_focusGained(FocusEvent e) {
    if (!txtNewPrice.getText().equalsIgnoreCase("")){
      txtNewPrice.setText("" + ut.convertToDouble(txtNewPrice.getText()));
    }
  }

//select Effective date
  void btnEffDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
     posXFrame = this.frmMain.getX();
     posYFrame = this.frmMain.getY();
     posX = this.btnEffDate.getX() + posXFrame+90;
     posY = this.btnEffDate.getY() + posYFrame+90;
     TimeDlg endDate = new TimeDlg(null);
     endDate.setTitle(lang.getString("ChooseEffectiveDate"));
     endDate.setResizable(false);
     endDate.setLocation(posX, posY);
     endDate.setVisible(true);
     if (endDate.isOKPressed()) {
       java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
       String date = fd.format(endDate.getDate());
       this.txtEffDate.setText(date);
     }
  }

//select closed date
  void btnClosedDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnEffDate.getX() + posXFrame + 90;
    posY = this.btnEffDate.getY() + posYFrame + 90;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseClosedDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);
    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtClosedDate.setText(date);
    }
  }

//click button ADD
  void btnAdd_actionPerformed(ActionEvent e) {
    if (txtItemUpc.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1007_UPCIsNotNull"));
      txtItemUpc.requestFocus(true);
      return;
    }
    if (!checkItemExist(txtItemUpc.getText().trim())){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1143_UPC_InvalidOrDoesNotExistWithApproveStatus"));
//      txtItemUpc.requestFocus(true);
      btnItemUpc.requestFocus(true);
      return;
    }
    if (txtNewPrice.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1144_NewPriceNotNull"));
      txtNewPrice.requestFocus(true);
      return;
    }
    if (!ut.isDoubleString("" + ut.convertToDouble(txtNewPrice.getText().trim()))){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1145_NewPriceMustNumeric"));
      txtNewPrice.requestFocus(true);
      return;
    }

    if (txtEffDate.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1146_ChooseEffectiveDate"));
      btnEffDate.requestFocus();
      return;
    }
    if (!ut.compareDate1(txtEffDate.getText(),txtChangeDate.getText())){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1147_EffectiveDateGreatCurrentDate"));
      btnEffDate.requestFocus(true);
      return;
    }
    if (!ut.compareDate(txtClosedDate.getText(),txtEffDate.getText())){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1148_ClosedDateGreatEffectiveDate"));
      btnClosedDate.requestFocus(true);
      return;
    }
    String itemId = getItemId(txtItemUpc.getText());
    String strId = cboStore.getSelectedData();
    String strName = frmMain.storeBusObj.getStoreName(DAL,cboStore.getSelectedData());
    String itmSize = getItemSize(itemId);
    Object[] rowData;
//the case of all store
    if(strId.equals("")){
      String SQL = "SELECT * FROM BTM_IM_STORE";
      ResultSet rs = null;
      try{
        rs = DAL.executeScrollQueries(SQL);
        String strIdTemp = "";
        String strNameTemp ="";
        while(rs.next()){
          strIdTemp = rs.getString("STORE_ID");
          strNameTemp = rs.getString("NAME");
          checkExistTable(itemId,strIdTemp,txtEffDate.getText());
          rowData = new Object[] {itemId,txtItemUpc.getText(),itmSize,strIdTemp,
              strNameTemp,txtNewPrice.getText(),txtEffDate.getText(),txtClosedDate.getText()};
          dm.addRow(rowData);
          addIntoVector(itemId,strIdTemp,txtEffDate.getText(),txtClosedDate.getText(),ut.convertToDouble(txtNewPrice.getText()),ut.convertToDouble(txtOldPrice.getText()),ut.convertToDouble(txtUnitCost.getText()),txtChangeDate.getText());
        }
        rs.getStatement().close();
      }catch(Exception ex){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      }
    }
//case if change the price of item for certain store
    else{
      checkExistTable(itemId,strId,txtEffDate.getText());
      rowData = new Object[] {itemId,txtItemUpc.getText(),itmSize,strId,
          strName,txtNewPrice.getText(),txtEffDate.getText(),txtClosedDate.getText()};
      dm.addRow(rowData);
      addIntoVector(itemId,strId,txtEffDate.getText(),txtClosedDate.getText(),ut.convertToDouble(txtNewPrice.getText()),ut.convertToDouble(txtOldPrice.getText()),ut.convertToDouble(txtUnitCost.getText()),txtChangeDate.getText());
    }
    resetData();
  }

  void addIntoVector(String itemId,String strId, String effDate,String endDate,double newPrice, double oldPrice,double unitCost, String Date){
    String sqlIns="";
    String sqlUpd="";
    String sqlDel="";

    String status ="0";
    String affUCdate = cvtDate(txtEffDate.getText()).trim();
    String sysdate = cvtDate(txtChangeDate.getText()).trim();

    if (affUCdate.equals(sysdate)){
      status ="1";
        sqlUpd =
            "Update BTM_IM_PRICE_HIST set STATUS = '0', PRICE_END_DATE=to_date('" +
            endDate + "','" + ut.DATE_FORMAT1 + "'), LAST_UPD_DATE = to_date('" +
            Date + "','dd/MM/yyyy')" +
            " Where ITEM_ID='" + itemId + "' and STATUS = '1'"+
            " and STORE_ID='" + strId + "' ";
      vSqlUpd.addElement(sqlUpd);
    }
    sqlIns = "Insert into BTM_IM_PRICE_HIST(ITEM_ID,STORE_ID,PRICE_EFF_DATE,PRICE_END_DATE,"+
        "NEW_PRICE,OLD_PRICE,STATUS,UNIT_COST,LAST_UPD_DATE,BATCH_SEQ) Values('" + itemId + "'," + strId + ",to_date('" +
        effDate + "','" +  ut.DATE_FORMAT1 + "'),to_date('" + endDate +
        "','" +  ut.DATE_FORMAT1 + "')," + newPrice + ","
        + oldPrice + ",'" + status + "'," + unitCost + ",to_date('"+Date+"','dd/MM/yyyy'),0)";
    sqlDel = "Delete BTM_IM_PRICE_HIST Where ITEM_ID='" + itemId + "' and STORE_ID=" +
        strId + " and PRICE_EFF_DATE = to_date('" + effDate + "','" + ut.DATE_FORMAT1 + "')";

    vSqlIns.addElement(sqlIns);
    vSqlDel.addElement(sqlDel);
  }

  //Covert to date type yyyy-mm-dd
  //date: dd/mm/yyyy
  String cvtDate(String date){
    String rlt = date;
    String year = "";
    String month = "";
    String day = "";
    day = String.valueOf(date.charAt(0))+String.valueOf(date.charAt(1));
    month = String.valueOf(date.charAt(3))+String.valueOf(date.charAt(4));
    for(int i=6; i<10;i++){
      year = year+String.valueOf(date.charAt(i));
    }
    rlt = year + "-"+ month + "-" + day;
    return rlt;
  }

  //check itemid and storeid in BJTable
  void checkExistTable(String itemId,String storeId, String effDate){
    for (int i = 0; i<table.getRowCount(); i++){
      if (table.getValueAt(i,COL_ITEM_ID).toString().equalsIgnoreCase(itemId)
          && table.getValueAt(i,COL_STORE_ID).toString().equalsIgnoreCase(storeId)
          && table.getValueAt(i,COL_EFFECTIVE_DATE).toString().equalsIgnoreCase(effDate)){
        vSqlIns.removeElementAt(i);
        vSqlDel.removeElementAt(i);
        dm.removeRow(i);
        return;
      }
    }
    return;
  }

  //get Item Size
  String getItemSize(String itemId){
    String size="";
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "Select ATTR2_DTL_ID From BTM_IM_ITEM_MASTER where ITEM_ID = '"+itemId+"'";
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeScrollQueries(sql,stmt);
      if (rs.next()){
        size = rs.getString("ATTR2_DTL_ID");
        if (size.equalsIgnoreCase("-1")){
          size=" ";
        }
      }
      rs.getStatement().close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return size;
  }

  //Reset data
  void resetData() {
    txtItemUpc.setText("");
    txtOldPrice.setText("");
    txtNewPrice.setText("");
    txtEffDate.setText("");
    txtUnitCost.setText("");
    txtChangeDate.setText(ut.getSystemDate(1));
    txtClosedDate.setText("7/7/7777");
    btnClosedDate.setEnabled(false);
    txtDescription.setText("");
    childLocationID = "";
    cboStore.setSelectedIndex(0);
    while(dmHis.getRowCount()>0){
      dmHis.removeRow(0);
    }
  }
//show Price History
  void showHistory(int rowsNum,String itemId,String storeId) {
      while (dmHis.getRowCount()>0){
        dmHis.removeRow(0);
      }
       dmHis.resetIndexes();
       ResultSet rs = null;
       String SQL = null;
       if(storeId.equals("")){
         SQL = "select NAME,OLD_PRICE, NEW_PRICE,to_char(PRICE_EFF_DATE,'dd/mm/yyyy') PRICE_EFF_DATE,"
             + "to_char(PRICE_END_DATE,'dd/mm/yyyy') PRICE_END_DATE, M_STATUS from ("
             + " select NAME, OLD_PRICE, NEW_PRICE, to_date(PRICE_EFF_DATE,'dd/mm/yyyy') PRICE_EFF_DATE,"
             + " to_date(PRICE_END_DATE,'dd/mm/yyyy') PRICE_END_DATE, decode(STATUS,1,'In Use','N/A') M_STATUS "
             + "from BTM_IM_PRICE_HIST a, BTM_IM_STORE b where item_id='"+itemId+"'"
             + " and a.STORE_ID=b.STORE_ID "
             + " order by STATUS desc,PRICE_END_DATE desc) "
             + " where rownum <= "+rowsNum;
       }else{
         SQL = "select NAME,OLD_PRICE, NEW_PRICE,to_char(PRICE_EFF_DATE,'dd/mm/yyyy') PRICE_EFF_DATE,"
             + "to_char(PRICE_END_DATE,'dd/mm/yyyy') PRICE_END_DATE, M_STATUS from ("
             + " select NAME, OLD_PRICE, NEW_PRICE, to_date(PRICE_EFF_DATE,'dd/mm/yyyy') PRICE_EFF_DATE,"
             + " to_date(PRICE_END_DATE,'dd/mm/yyyy') PRICE_END_DATE, decode(STATUS,1,'In Use','N/A') M_STATUS "
             + "from BTM_IM_PRICE_HIST a, BTM_IM_STORE b where item_id='"+itemId+"'"
             + " and a.STORE_ID = b.STORE_ID and a.store_id = '"+storeId+"'"
             + " order by STATUS desc, PRICE_END_DATE desc )"
             + " where rownum <= " +rowsNum;
       }
       try{
            stmt = DAL.getConnection().createStatement(ResultSet.
                                        TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);
//            System.out.println(SQL);
            rs = DAL.executeQueries(SQL,stmt);
            jTblHistory.setData(rs);
            rs.getStatement().close();
            stmt.close();
       }catch(Exception ex){
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(ex);
       };
       jTblHistory.getTableHeader().setReorderingAllowed(false);
       jTblHistory.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
       jTblHistory.setHeaderName(lang.getString("StoreID"), COL_HIS_STORE);
       jTblHistory.setHeaderName(lang.getString("OldPrice"), COL_HIS_OLD_PRICE);
       jTblHistory.setHeaderName(lang.getString("NewPrice"), COL_HIS_NEW_PRICE);
       jTblHistory.setHeaderName(lang.getString("EffectDate"), COL_HIS_EFFECT_DATE);
       jTblHistory.setHeaderName(lang.getString("ClosedDate"), COL_HIS_CLOSED_DATE);
       jTblHistory.setHeaderName(lang.getString("Status"), COL_HIS_STATUS);
    }

//click button Done
  void btnDone_actionPerformed(ActionEvent e) {
    ut.writeToTextFile("file\\error.txt","Hist PR  "+ ut.getSystemDateTime()+ "\r\n");
    if (!vSqlIns.isEmpty()) {
      DAL.setBeginTrans(DAL.getConnection());
      try {
        stmt = DAL.getConnection().createStatement();
        DAL.executeBatchQuery(vSqlDel, stmt);
        DAL.executeBatchQuery(vSqlUpd);
        DAL.executeBatchQuery(vSqlIns);
        stmt.close();
      }catch (Exception ex) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      }
      DAL.setEndTrans(DAL.getConnection());
      vSqlDel.clear();
      vSqlIns.clear();
      vSqlUpd.clear();
    }
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    resetData();
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
  }


  public void applyPermission() {
   EmpRight er = new EmpRight();
   er.initData(DAL, DAL.getEmpID());
   er.setData(DAL.getEmpID(), Constant.SCRS_PRICE_CHANGE);
   btnAdd.setEnabled(er.getAdd());
   if(!er.getAdd()){
     ut.showMessage(frmMain, lang.getString("TT002"),
                    lang.getString("MS1085_CanNotUseScreen"));

   }
 }

  void btnCancel_actionPerformed(ActionEvent e) {
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
    vSqlIns.clear();
    vSqlDel.clear();
    vSqlUpd.clear();
    resetData();
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0002"));

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
        else if (identifier.intValue() == KeyEvent.VK_F2) {
          btnAdd.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
      }
 }

  void txtUnitCost_keyTyped(KeyEvent e) {

  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
        btnAdd.doClick();
    }
  }

  public void cboStore_actionPerformed(ActionEvent e) {
//    txtNewPrice.setText("");
    if (txtItemUpc.getText().equalsIgnoreCase("") || !checkItemExist(txtItemUpc.getText())){
      txtUnitCost.setText("");
      txtOldPrice.setText("");
      while (dmHis.getRowCount()>0){
        dmHis.removeRow(0);
      }
      return;
    }
    String itemId = getItemId(txtItemUpc.getText());
    txtUnitCost.setText(ut.formatNumber("" + getUnitCost(itemId,cboStore.getSelectedData())));
    txtOldPrice.setText(ut.formatNumber("" + getNewPrice(itemId,cboStore.getSelectedData())));
    showHistory(ROW_NUM,getItemId(txtItemUpc.getText()),cboStore.getSelectedData());
  }
}

class PanelPriceChange_cboStore_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPriceChange adaptee;
  PanelPriceChange_cboStore_actionAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cboStore_actionPerformed(e);
  }
}

class PanelPriceChange_btnItemID_actionAdapter implements java.awt.event.ActionListener {
  PanelPriceChange adaptee;

  PanelPriceChange_btnItemID_actionAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnItemID_actionPerformed(e);
  }
}

class PanelPriceChange_txtNewPrice_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPriceChange adaptee;

  PanelPriceChange_txtNewPrice_keyAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtNewPrice_keyTyped(e);
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtNewPrice_keyReleased(e);
  }
}

class PanelPriceChange_btnEffDate_actionAdapter implements java.awt.event.ActionListener {
  PanelPriceChange adaptee;

  PanelPriceChange_btnEffDate_actionAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnEffDate_actionPerformed(e);
  }

}

class PanelPriceChange_btnClosedDate_actionAdapter implements java.awt.event.ActionListener {
  PanelPriceChange adaptee;

  PanelPriceChange_btnClosedDate_actionAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClosedDate_actionPerformed(e);
  }
}

class PanelPriceChange_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelPriceChange adaptee;

  PanelPriceChange_btnAdd_actionAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelPriceChange_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelPriceChange adaptee;

  PanelPriceChange_btnDone_actionAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelPriceChange_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelPriceChange adaptee;

  PanelPriceChange_btnCancel_actionAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelPriceChange_txtNewPrice_focusAdapter extends java.awt.event.FocusAdapter {
  PanelPriceChange adaptee;

  PanelPriceChange_txtNewPrice_focusAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtNewPrice_focusLost(e);
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtNewPrice_focusGained(e);
  }
}

class PanelPriceChange_txtUnitCost_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPriceChange adaptee;

  PanelPriceChange_txtUnitCost_keyAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtUnitCost_keyTyped(e);
  }
}

class PanelPriceChange_txtItemUpc_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPriceChange adaptee;

  PanelPriceChange_txtItemUpc_keyAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemUpc_keyTyped(e);
  }
}

class PanelPriceChange_txtItemUpc_focusAdapter extends java.awt.event.FocusAdapter {
  PanelPriceChange adaptee;

  PanelPriceChange_txtItemUpc_focusAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemUpc_focusLost(e);
  }
}

class PanelPriceChange_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPriceChange adaptee;

  PanelPriceChange_table_keyAdapter(PanelPriceChange adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

//=============
//package sim;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.table.*;
//import java.sql.*;
//import common.*;
//import btm.attr.*;
//import btm.swing.*;
//import java.util.*;
//import javax.swing.event.*;
//import btm.swing.*;
//import java.text.DateFormat;
////import common.TimeDlg;
//
///**
// * <p>Title: </p>
// * <p>Description: </p>
// * <p>Copyright: Copyright (c) 2005</p>
// * <p>Company: BTM International Software</p>
// * @author Tuan Truong
// * @version 1.0
// */
//
//public class PanelPriceChange extends JPanel {
//  //Column Order of Sale Item Table
//  private static final int COL_ITEM_ID = 0; //
//  private static final int COL_ART_NO = 1;
//  private static final int COL_SIZE = 2;
//  private static final int COL_STORE_ID = 3;
//  private static final int COL_STORE_NAME = 4;
//  private static final int COL_NEW_PRICE = 5; //
//  private static final int COL_EFFECTIVE_DATE = 6;
//  private static final int COL_CLOSE_DATE = 7;
//
//  DataAccessLayer DAL;
//  Statement stmt = null;
//
//  Utils ut = new Utils();
//  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
//  btm.business.PriceHistBusObj prHist = new btm.business.PriceHistBusObj();
//  String childLocationID = "";
//  Vector vList = new Vector();
//  Vector vDataList = new Vector();
//  Vector vSql = new Vector();
//  Vector vSqlUpd = new Vector();
//  Vector vPriceHist = new Vector();
//  Vector vUnitCost = new Vector();
//  Vector vItemID = new Vector();
//  double oldPrice = 0;
//  //define for panel
//  FrameMainSim frmMain;
//  BorderLayout borderLayout1 = new BorderLayout();
//  BJPanel jPanel1 = new BJPanel(Constant.PANEL_BLUE_HEADER);
//  JPanel jPanel2 = new JPanel();
//  JPanel jPanel3 = new JPanel();
//  BorderLayout borderLayout2 = new BorderLayout();
//  JPanel jPanel4 = new JPanel();
//  JPanel jPanel5 = new JPanel();
//  JPanel jPanel6 = new JPanel();
//  BorderLayout borderLayout3 = new BorderLayout();
//  JPanel jPanel7 = new JPanel();
//  JPanel jPanel8 = new JPanel();
//  JPanel jPanel9 = new JPanel();
//  BorderLayout borderLayout4 = new BorderLayout();
//  JPanel jPanel10 = new JPanel();
//  JPanel jPanel11 = new JPanel();
//  JPanel jPanel12 = new JPanel();
//  BorderLayout borderLayout5 = new BorderLayout();
//  JPanel jPanel13 = new JPanel();
//  JPanel jPanel14 = new JPanel();
//  JLabel lblAllStore = new JLabel();
//  JLabel lblStoreID = new JLabel();
//  JLabel lblItemID = new JLabel();
//  JLabel lblDescription = new JLabel();
//  JScrollPane jScrollPane1 = new JScrollPane();
//  JTextArea txtDescription = new JTextArea();
//  JCheckBox chkAllStore = new JCheckBox();
//  JButton btnStoreID = new JButton();
//  JTextField txtStoreID = new JTextField();
//  JButton btnItemID = new JButton();
//  JTextField txtItemID = new JTextField();
//  JLabel lblClosedDate = new JLabel();
//  JLabel lblEffDate = new JLabel();
//  JLabel lblNewPrice = new JLabel();
//  JButton btnClosedDate = new JButton();
//  JTextField txtClosedDate = new JTextField();
//  JButton btnEffDate = new JButton();
//  JTextField txtEffDate = new JTextField();
//  JTextField txtNewPrice = new JTextField();
//  JScrollPane jScrollPane2 = new JScrollPane();
//  SortableTableModel dm = new SortableTableModel();
//  BJTable table = new BJTable(dm,true){
//    public boolean isCellEditable(int row,int col){
//      return false;
//    }
//    public Class getColumnClass(int col){
//      switch(col){
//        case COL_NEW_PRICE: return Long.class;
//        //case 2: return Long.class;
//        default: return Object.class;
//      }
//    }
//  };
//  BJButton btnCancel = new BJButton();
//  BJButton btnAdd = new BJButton();
//  BJButton btnDone = new BJButton();
//  String attr1 = "";
//  String attr2 = "";
//  BorderLayout borderLayout6 = new BorderLayout();
//  JLabel lblUnitCost = new JLabel();
//  JTextField txtUnitCost = new JTextField();
//  JLabel lblOldPrice = new JLabel();
//  JTextField txtOldPrice = new JTextField();
//
//
//  /** Create Panel for Product Hier Type Setup
//   * @author 		Tuan.Truong
//   * @param 		FrameMainSim frm, CardLayout crdLayout, JPanel parent
//   * @return		no
//   */
//  public PanelPriceChange(FrameMainSim frmMain) {
//    try {
//      this.frmMain = frmMain;
//      DAL = frmMain.getDAL();
//      jbInit();
//    }catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//  }//end method
//
//
//  /** Create jbInit method - init value for frame
//   * @author 		Nghi Doan
//   * @param 		no
//   * @return		no
//   */
//  void jbInit() throws Exception {
//    registerKeyboardActions();
//    this.setPreferredSize(new Dimension(800, 600));
//    this.setLayout(borderLayout1);
//    jPanel1.setBackground(new Color(121, 152, 198));
//    jPanel1.setAlignmentY((float) 0.5);
//    jPanel1.setPreferredSize(new Dimension(800, 50));
//    jPanel2.setBackground(SystemColor.control);
//    jPanel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
//    jPanel2.setPreferredSize(new Dimension(800, 250));
//    jPanel2.setLayout(borderLayout2);
//    jPanel3.setBackground(SystemColor.control);
//    jPanel3.setPreferredSize(new Dimension(800, 300));
//    jPanel3.setLayout(borderLayout6);
//    jPanel4.setBackground(SystemColor.control);
//    jPanel4.setPreferredSize(new Dimension(10, 250));
//    jPanel6.setBackground(SystemColor.control);
//    jPanel6.setPreferredSize(new Dimension(190, 250));
//    jPanel5.setBackground(SystemColor.control);
//    jPanel5.setPreferredSize(new Dimension(600, 250));
//    jPanel5.setLayout(borderLayout3);
//    jPanel7.setPreferredSize(new Dimension(100, 130));
//    jPanel9.setPreferredSize(new Dimension(200, 130));
//    jPanel8.setPreferredSize(new Dimension(300, 130));
//    jPanel8.setLayout(borderLayout4);
//    jPanel10.setPreferredSize(new Dimension(200, 130));
//    jPanel11.setPreferredSize(new Dimension(100, 130));
//    jPanel12.setLayout(borderLayout5);
//    jPanel12.setPreferredSize(new Dimension(600, 90));
//    jPanel13.setPreferredSize(new Dimension(100, 80));
//    jPanel14.setPreferredSize(new Dimension(500, 120));
//    lblAllStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    lblAllStore.setPreferredSize(new Dimension(90, 20));
//    lblAllStore.setText("                   ");
//    lblStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    lblStoreID.setPreferredSize(new Dimension(90, 20));
//    lblStoreID.setText("Store ID");
//    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    lblItemID.setPreferredSize(new Dimension(90, 20));
//    lblItemID.setText("UPC:");
//    lblDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    lblDescription.setPreferredSize(new Dimension(90, 20));
//    lblDescription.setText("Description");
//    jScrollPane1.setPreferredSize(new Dimension(490, 60));
//    txtDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtDescription.setText("");
//    txtDescription.setTabSize(8);
//    chkAllStore.setEnabled(false);
//    chkAllStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    chkAllStore.setPreferredSize(new Dimension(192, 20));
//    chkAllStore.setText("All Store");
//    chkAllStore.addActionListener(new PanelPriceChange_chkAllStore_actionAdapter(this));
//    txtStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtStoreID.setPreferredSize(new Dimension(150, 20));
//    txtStoreID.setEditable(false);
//    txtStoreID.setText("");
//    btnItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnItemID.setPreferredSize(new Dimension(30, 20));
//    btnItemID.setText("...");
//    btnItemID.addActionListener(new PanelPriceChange_btnItemID_actionAdapter(this));
//    txtItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtItemID.setPreferredSize(new Dimension(150, 20));
//    txtItemID.setText("");
//    txtItemID.addFocusListener(new PanelPriceChange_txtItemID_focusAdapter(this));
//    txtItemID.addKeyListener(new PanelPriceChange_txtItemID_keyAdapter(this));
//    btnStoreID.setEnabled(false);
//    btnStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnStoreID.setPreferredSize(new Dimension(30, 20));
//    btnStoreID.setText("...");
//    btnStoreID.addActionListener(new PanelPriceChange_btnStoreID_actionAdapter(this));
//    lblClosedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    lblClosedDate.setPreferredSize(new Dimension(90, 20));
//    lblClosedDate.setText("Closed Date:");
//    lblEffDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    lblEffDate.setPreferredSize(new Dimension(90, 20));
//    lblEffDate.setText("Effective Date:");
//    lblNewPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    lblNewPrice.setPreferredSize(new Dimension(90, 20));
//    lblNewPrice.setText("New Price:");
//    btnClosedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnClosedDate.setPreferredSize(new Dimension(30, 20));
//    btnClosedDate.setText("...");
//    btnClosedDate.setEnabled(false);
//    btnClosedDate.addActionListener(new PanelPriceChange_btnClosedDate_actionAdapter(this));
//    btnEffDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnEffDate.setPreferredSize(new Dimension(30, 20));
//    btnEffDate.setText("...");
//    btnEffDate.addActionListener(new PanelPriceChange_btnEffDate_actionAdapter(this));
//    txtEffDate.setBackground(Color.white);
//    txtEffDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtEffDate.setPreferredSize(new Dimension(160, 20));
//    txtEffDate.setEditable(false);
//    txtEffDate.setText("");
//    txtNewPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtNewPrice.setPreferredSize(new Dimension(195, 20));
//    txtNewPrice.setText("");
//    txtNewPrice.addFocusListener(new PanelPriceChange_txtNewPrice_focusAdapter(this));
//    txtNewPrice.addKeyListener(new PanelPriceChange_txtNewPrice_keyAdapter(this));
//    txtClosedDate.setPreferredSize(new Dimension(160, 20));
//    txtClosedDate.setEditable(false);
//    txtClosedDate.setBackground(Color.white);
//    txtClosedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtClosedDate.setText("7/7/7777");
//    jScrollPane2.setPreferredSize(new Dimension(760, 290));
//    btnCancel.setToolTipText("Cancel (F12)");
//    btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
//    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
//    btnCancel.addActionListener(new PanelPriceChange_btnCancel_actionAdapter(this));
//    btnAdd.setToolTipText("Add (F2)");
//    btnAdd.setText("Add (F2)");
//    btnAdd.addActionListener(new PanelPriceChange_btnAdd_actionAdapter(this));
//    btnDone.setToolTipText("Done (F1)");
//    btnDone.setText("Done (F1)");
//    btnDone.addActionListener(new PanelPriceChange_btnDone_actionAdapter(this));
//    lblUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    lblUnitCost.setPreferredSize(new Dimension(90, 20));
//    lblUnitCost.setText("Unit Cost:");
//    txtUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtUnitCost.setPreferredSize(new Dimension(195, 20));
//    txtUnitCost.setEditable(false);
//    txtUnitCost.setText("");
//    txtUnitCost.addKeyListener(new PanelPriceChange_txtUnitCost_keyAdapter(this));
//    lblOldPrice.setText("Old Price:");
//    lblOldPrice.setPreferredSize(new Dimension(90, 20));
//    lblOldPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtOldPrice.setText("");
//    txtOldPrice.setPreferredSize(new Dimension(185, 20));
//    txtOldPrice.setEditable(false);
//    txtOldPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    table.addKeyListener(new PanelPriceChange_table_keyAdapter(this));
//    jPanel9.add(txtUnitCost, null);
//    jPanel10.add(txtItemID, null);
//    jPanel10.add(btnItemID, null);
//    jPanel10.add(txtStoreID, null);
//    jPanel10.add(btnStoreID, null);
//    jPanel10.add(chkAllStore, null);
//    this.add(jPanel1,  BorderLayout.NORTH);
//    jPanel1.add(btnDone, null);
//    jPanel1.add(btnAdd, null);
//    jPanel1.add(btnCancel, null);
//    this.add(jPanel2,  BorderLayout.CENTER);
//    jPanel2.add(jPanel4,  BorderLayout.WEST);
//    jPanel2.add(jPanel5,  BorderLayout.CENTER);
//    jPanel5.add(jPanel7,  BorderLayout.WEST);
//    jPanel7.add(lblItemID, null);
//    jPanel7.add(lblStoreID, null);
//    jPanel7.add(lblAllStore, null);
//    jPanel7.add(lblOldPrice, null);
//    jPanel5.add(jPanel8,  BorderLayout.CENTER);
//    jPanel8.add(jPanel10, BorderLayout.CENTER);
//    jPanel8.add(jPanel11,  BorderLayout.EAST);
//    jPanel11.add(lblUnitCost, null);
//    jPanel11.add(lblNewPrice, null);
//    jPanel11.add(lblEffDate, null);
//    jPanel11.add(lblClosedDate, null);
//    jPanel5.add(jPanel9,  BorderLayout.EAST);
//    jPanel5.add(jPanel12, BorderLayout.SOUTH);
//    jPanel12.add(jPanel13,  BorderLayout.WEST);
//    jPanel12.add(jPanel14,  BorderLayout.CENTER);
//    this.add(jPanel3, BorderLayout.SOUTH);
//    jPanel13.add(lblDescription, null);
//    jPanel14.add(jScrollPane1, null);
//    jPanel2.add(jPanel6, BorderLayout.EAST);
//    jScrollPane1.getViewport().add(txtDescription, null);
//    jPanel9.add(txtNewPrice, null);
//    jPanel9.add(txtEffDate, null);
//    jPanel9.add(btnEffDate, null);
//    jPanel9.add(txtClosedDate, null);
//    jPanel9.add(btnClosedDate, null);
//    jPanel3.add(jScrollPane2, BorderLayout.CENTER);
//    jScrollPane2.getViewport().add(table, null);
//    jPanel10.add(txtOldPrice, null);
//    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
//    table.getTableHeader().setReorderingAllowed(false);
//    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
//    dm.addColumn("Item ID");
//    dm.addColumn("UPC");
//    dm.addColumn("Size");
//    dm.addColumn("Store ID");
//    dm.addColumn("Store Name");
//    dm.addColumn("New Price");
//    dm.addColumn("Effective Date");
//    dm.addColumn("Closed Date");
//    table.setRowHeight(30);
////    str.installInTable(table, Color.lightGray, Color.white, null, null);
//    float[] f1 = new float[3];
//    Color.RGBtoHSB(255,255,230,f1);
//    str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);
//
//    chkAllStore.setSelected(true);
//  }
//  public void applyPermission() {
//   EmpRight er = new EmpRight();
//   er.initData(DAL, DAL.getEmpID());
//   er.setData(DAL.getEmpID(), Constant.SCRS_PRICE_CHANGE);
//   btnAdd.setEnabled(er.getAdd());
// }
// //get item
// void getUnitCostHist(){
//   if(!vPriceHist.isEmpty()){
//     vPriceHist.clear();
//   }
//
//   Statement stmt = null;
//   ResultSet rs = null;
//   String sql = "";
//   try{
//     sql = "select iph.item_id, store_id, price_eff_date from btm_im_price_hist iph, btm_im_item_master im  " +
//         " where iph.item_id = im.item_id and im.status <> 'D' and  price_eff_date > to_date('" + ut.getSystemDate() + "','" + ut.DATE_FORMAT + "')";
//     System.out.println(sql);
////     System.out.println("System count: " + frmMain.frmMainCount);
//     System.out.println("Befor executing Query: " + ut.getSystemDateTime());
//     stmt = DAL.getConnection().createStatement();
//     rs = DAL.executeQueries(sql,stmt);
//     java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
//          "dd/MM/yyyy");
//     System.out.println("After executing query: " + ut.getSystemDateTime());
//     while (rs.next()){
//       String date = fd.format(rs.getDate("price_eff_date"));
//       Vector vData = new Vector();
//       vData.addElement(rs.getString("item_id"));
//       vData.addElement(new Long(rs.getLong("store_id")));
//       vData.addElement(date);
//       vPriceHist.addElement(vData);
//     }
////     rs = null;
//     rs.close();
//     stmt.close();
//     System.out.println("After all: " + ut.getSystemDateTime());
//   }catch(Exception e){
//
//   }
//
//   System.out.println("Show screen: " + ut.getSystemDateTime());
// }
//
//  //set item id
//  void setSelectItem(String itemID){
//    txtItemID.setText(itemID);
//  }
//  void btnItemID_actionPerformed(ActionEvent e) {
//    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
//    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT014")+" - ITEM LOOKUP", true, frmMain,itemBusObject);
//    dlgItemLookup.setVisible(true);
//    if (dlgItemLookup.done){
//      txtItemID.setText(dlgItemLookup.getUPC());
//      txtOldPrice.setText(dlgItemLookup.getUnitPrice());
//      setInfoForItem();
//    }
//  }
//  void btnStoreID_actionPerformed(ActionEvent e) {
//    chkAllStore.setSelected(false);
//    DialogStoreLocation dlgStoreLocation = new DialogStoreLocation(frmMain,"",true,frmMain);
//    dlgStoreLocation.setVisible(true);
//    if (dlgStoreLocation.isOK){
//      if (dlgStoreLocation.getList2().isEmpty()){
//        if (!dlgStoreLocation.getList1().isEmpty()){
//          lblStoreID.setText("Area:");
//          txtStoreID.setText(dlgStoreLocation.getChildNameLocation());
//          vList = dlgStoreLocation.getList1();
//          childLocationID = dlgStoreLocation.getChildIdLocation();
//        }
//      }else{
//        lblStoreID.setText("Store ID:");
//        String storeID = "";
//        String storeName = "";
//        vList = dlgStoreLocation.getList2();
//        vDataList = dlgStoreLocation.getDataList2();
//        for (int i = 0; i<= vList.lastIndexOf(vList.lastElement()); i ++){
//          if (storeID.equalsIgnoreCase("")){
//            storeID = "" + vList.elementAt(i);
//            storeName = "" + vDataList.elementAt(i);
//          }else {
//            storeID = storeID + ";" + vList.elementAt(i);
//            storeName = storeName + "; " + vDataList.elementAt(i);
//          }
//        }
//        txtStoreID.setText(storeName);
//      }
//    }
//    if (vList.isEmpty()){
//      chkAllStore.setSelected(true);
//    }
//  }
//
//  void txtNewPrice_keyTyped(KeyEvent e) {
////    ut.limitLenjTextField(e,txtNewPrice, 18);
//    ut.checkNumberPositive(e,txtNewPrice.getText());
//
//  }
//
//  void btnEffDate_actionPerformed(ActionEvent e) {
//    int posX, posY, posXFrame, posYFrame;;
//     posXFrame = this.frmMain.getX();
//     posYFrame = this.frmMain.getY();
//     posX = this.btnEffDate.getX() + posXFrame+90;
//     posY = this.btnEffDate.getY() + posYFrame+90;
//     TimeDlg endDate = new TimeDlg(null);
//     endDate.setTitle("Choose Effective Date");
//     endDate.setResizable(false);
//     endDate.setLocation(posX, posY);
//     endDate.setVisible(true);
//     if (endDate.isOKPressed()) {
//       java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
//           "dd/MM/yyyy");
//       String date = fd.format(endDate.getDate());
//       this.txtEffDate.setText(date);
//     }
//  }
//
//  void btnClosedDate_actionPerformed(ActionEvent e) {
//    int posX, posY, posXFrame, posYFrame;;
//     posXFrame = this.frmMain.getX();
//     posYFrame = this.frmMain.getY();
//     posX = this.btnEffDate.getX() + posXFrame+90;
//     posY = this.btnEffDate.getY() + posYFrame+90;
//     TimeDlg endDate = new TimeDlg(null);
//     endDate.setTitle("Choose Closed Date");
//     endDate.setResizable(false);
//     endDate.setLocation(posX, posY);
//     endDate.setVisible(true);
//     if (endDate.isOKPressed()) {
//       java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
//           "dd/MM/yyyy");
//       String date = fd.format(endDate.getDate());
//       this.txtClosedDate.setText(date);
//     }
//
//  }
//  String getItemCode(String upc){
//    ResultSet rs = null;
//    Statement stmt = null;
//    if(upc.equalsIgnoreCase("")){
//      return "";
//    }
//    String itemID="-1";
//        try {
//          String sql ="Select ITEM_ID From BTM_IM_ITEM_MASTER Where ART_NO='" + upc + "'";
//          System.out.println(sql);
//          stmt = DAL.getConnection().createStatement();
//          rs = DAL.executeQueries(sql, stmt);
//          if (rs.next()) {
//            itemID = rs.getString("ITEM_ID");
//          }
//        }
//        catch (Exception e) {
//          ExceptionHandle eh = new ExceptionHandle();
//          eh.ouputError(e);
//        }
//        try {
//          stmt.close();
//        }
//        catch (Exception e) {
//          e.printStackTrace();
//        }
//      return itemID;
//  }
//
//  double getNewPrice(String upc){
//    ResultSet rs = null;
//    double unit_cost=0;
//    String itemID = getItemCode(upc);
//    String strSql = "Select NEW_UNIT_COST From BTM_IM_UNIT_COST_HIST where ITEM_ID='" + itemID + "' and status = 1";
//    try {
//      rs =  DAL.executeQueries(strSql);
//      if(rs.next()){
//        unit_cost = rs.getDouble("UNIT_COST");
//      }
//      rs.close();
//    } // end try
//    catch (Exception e1) {
//      e1.printStackTrace();
//    }
//    return unit_cost;
//  }
//
//  void btnAdd_actionPerformed(ActionEvent e) {
//    if (!chkAllStore.isSelected() && vList.isEmpty()){
//      ut.showMessage(frmMain,lang.getString("TT001"), "You must choose store id ");
//      btnStoreID.requestFocus(true);
//      return;
//    }
//    //get Store list ID & Name
//    if (chkAllStore.isSelected()){
//      vList.clear();
//      vDataList.clear();
//      try{
//
//        ResultSet rs = null;
//        String sql = "select store_id, name from btm_im_store where closed_date = to_date('7777-7-7','yyyy-MM-dd')";
//        stmt = DAL.getConnection().createStatement();
//        rs = DAL.executeQueries(sql,stmt);
//        while (rs.next()){
//          vList.add(new Long(rs.getLong("store_id")));
//          vDataList.add(rs.getString("name"));
//        }
//        stmt.close();
//      }catch(Exception ex){}
//
//    }
//    if (txtItemID.getText().equalsIgnoreCase("")){
//      ut.showMessage(frmMain,lang.getString("TT001"),"UPC is not null");
//      txtItemID.requestFocus(true);
//      return;
//    }
//    if (!checkItemExist(txtItemID.getText().trim())){
//      ut.showMessage(frmMain,lang.getString("TT001"),"The UPC is invalid or does not exist");
//      txtItemID.requestFocus(true);
//      return;
//    }
//    if (vList.isEmpty()){
//      ut.showMessage(frmMain,"Warning!","There are no store in your choice, Please choose it again");
//      btnStoreID.requestFocus(true);
//      return;
//    }
//    if (txtNewPrice.getText().equalsIgnoreCase("")){
//      ut.showMessage(frmMain,lang.getString("TT001"),"New price is not null");
//      txtNewPrice.requestFocus(true);
//      return;
//    }
//    if (!ut.isDoubleString("" + ut.convertToDouble(txtNewPrice.getText().trim()))){
//      ut.showMessage(frmMain,lang.getString("TT001"),"New Price must be a numeric");
//      txtNewPrice.requestFocus(true);
//      return;
//    }
//    //check unit cost and unit price
//    double unit_price = ut.convertToDouble(txtNewPrice.getText());
////    double unit_cost = getNewPrice(txtItemID.getText().trim());
//    double unit_cost = ut.convertToDouble(txtUnitCost.getText());
//    if (unit_price <= unit_cost ){
//      int choose = ut.showMessage(frmMain, "Warning!",
//                                  "Unit Price is less than or equal Unit Cost. Do you want to setup this item?", 3, DialogMessage.YES_NO_OPTION);
//      if (choose == DialogMessage.NO_VALUE) {
//        txtNewPrice.requestFocus(true);
//        txtNewPrice.selectAll();
//        return;
//      }
//    }
//    if (unit_price >= 2*unit_cost ){
//      int choose = ut.showMessage(frmMain, "Warning!",
//                                  "Unit Price is greater than or equal 2*Unit Cost. Do you want to setup this item?", 3, DialogMessage.YES_NO_OPTION);
//      if (choose == DialogMessage.NO_VALUE) {
//        txtNewPrice.requestFocus(true);
//        txtNewPrice.selectAll();
//        return;
//      }
//    }
//
//    if (txtEffDate.getText().equalsIgnoreCase("")){
//      ut.showMessage(frmMain,lang.getString("TT001"),"You must choose the effective date");
//      btnEffDate.requestFocus();
//      return;
//    }
//    java.util.Date effDate = ut.convertStringToDate(txtEffDate.getText());
//    if (!ut.compareDate1(txtEffDate.getText(),ut.getSystemDate(1))){
//      ut.showMessage(frmMain,lang.getString("TT001"),"Effective Date must be greater than current date");
//      btnEffDate.requestFocus(true);
//      return;
//    }
//    if (!ut.compareDate(txtClosedDate.getText(),txtEffDate.getText())){
//      ut.showMessage(frmMain,lang.getString("TT001"),"Closed Date must be greater than effective date");
//      btnClosedDate.requestFocus(true);
//      return;
//    }
//    Object[] rowData;
//    String mess = "";
//    for (int j=0; j<vItemID.size();j++){
//      String itemID = vItemID.elementAt(j).toString();
//      for (int i = 0; i < vList.size(); i++) {
//        long storeID = Long.parseLong("" + vList.elementAt(i));
//        String storeName = vDataList.elementAt(i).toString();
//        if (checkEffDate(itemID, storeID, txtEffDate.getText())) {
//          checkExistTable(itemID, storeID);
//          if (checkExist(itemID, storeID)) {
//            String sqlUpd =  "Delete btm_im_price_hist where item_id='" +
//                itemID + "' and store_id = " +
//                storeID + " and price_eff_date = " +
//                " to_date('" + txtEffDate.getText() + "','dd/MM/yyyy')";
//            System.out.println(sqlUpd);
//            vSqlUpd.addElement(sqlUpd);
//          }
//          getItemAttr(itemID);
//          if (!txtOldPrice.getText().equalsIgnoreCase("")){
//            oldPrice = ut.convertToDouble(txtOldPrice.getText());
//          }
//          String sqlIns =
//              "Insert into btm_im_price_hist(item_id, store_id, price_eff_date, " +
//              " price_end_date, new_price, old_price, status, description,unit_cost,last_upd_date,attr1_name, attr2_name,batch_seq) values('" +
//              itemID + "'," + storeID +
//              ",to_date('" + txtEffDate.getText() + "','" + ut.DATE_FORMAT1 +
//              "'),to_date('" + txtClosedDate.getText() + "','" +
//              ut.DATE_FORMAT1 +
//              "')," + ut.convertToDouble(txtNewPrice.getText()) + "," +
//              oldPrice + ",0,'" +
//              txtDescription.getText() + "',"+unit_cost+",to_date('"+ut.getSystemDate(1)+"','dd/MM/yyyy'),'" + attr1 + "','" + attr2 + "',0)";
//          System.out.println(sqlIns);
//          vSql.addElement(sqlIns);
//          rowData = new Object[] {
//              itemID, getArtNo(itemID),getSize(itemID),new Long(storeID), storeName, txtNewPrice.getText(),
//              txtEffDate.getText(), txtClosedDate.getText()};
//          dm.addRow(rowData);
//
//          vUnitCost.addElement(txtUnitCost.getText().trim());
//        }
//        else {
//          String sqlIns = "update btm_im_price_hist set new_price = " +
//              ut.convertToDouble(txtNewPrice.getText()) +
//              " , last_upd_date = to_date('"+ut.getSystemDate(1)+"','dd/MM/yyyy') where price_eff_Date = to_date('"+txtEffDate.getText()+"','dd-MM-yyyy') and price_end_date = to_date('7/7/7777','DD/MM/YYYY') and item_id = '" + itemID +
//              "' and store_id = " + storeID;
//          System.out.println(sqlIns);
//          vSql.addElement(sqlIns);
//          rowData = new Object[] {
//              itemID,getArtNo(itemID),getSize(itemID), new Long(storeID), storeName, txtNewPrice.getText(),
//              txtEffDate.getText(), txtClosedDate.getText()};
//          dm.addRow(rowData);
//        }
//      }
//    }
//    resetData();
//  }
//  //get Size
//  String getSize(String sItemID){
//    String strTemp="";
//    Statement stmt = null;
//    ResultSet rs = null;
//    String sql = "select ATTR2_DTL_ID from BTM_IM_ITEM_MASTER where ITEM_ID = '"+sItemID+"'";
//    try{
//      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//      rs = DAL.executeScrollQueries(sql,stmt);
//      if (rs.next()){
//        strTemp = rs.getString("ATTR2_DTL_ID");
//        if (strTemp.equalsIgnoreCase("-1")){
//          strTemp=" ";
//        }
//      }
//      stmt.close();
//    }catch(Exception e){
//      e.printStackTrace();
//    }
//    return strTemp;
//  }
//
//  //check the UPC exist
//  boolean checkItemExist(String upc){
//    ResultSet rs = null;
//    String strSql =  "Select Art_No From BTM_IM_ITEM_MASTER Where Art_No = '" + upc + "'";
//    try{
//     stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//     rs = DAL.executeScrollQueries(strSql,stmt);
//     if (rs.next()){
//       return true;
//     }
//     stmt.close();
//   }catch(Exception e){
//     e.printStackTrace();
//   }
//    return false;
//  }
//
//  //get Atr No
//  String getArtNo(String sItemID){
//    String strTemp="";
//    Statement stmt = null;
//    ResultSet rs = null;
//    String sql = "select ART_NO from BTM_IM_ITEM_MASTER where ITEM_ID = '"+sItemID+"'";
//    try{
//      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//      rs = DAL.executeScrollQueries(sql,stmt);
//      if (rs.next()){
//        strTemp = rs.getString("ART_NO");
//      }
//      stmt.close();
//    }catch(Exception e){
//      e.printStackTrace();
//    }
//    return strTemp;
//  }
//
//  //check effective date
//  boolean checkEffDate(String itemID, long storeID, String effDate){
//    for (int i = 0; i < vPriceHist.size(); i++){
//      Vector vData = (Vector) vPriceHist.elementAt(i);
//      if (vData.elementAt(0).toString().equalsIgnoreCase(itemID)) {
//        if (Long.parseLong(vData.elementAt(1).toString()) == storeID) {
//          if (vData.elementAt(2).toString().equalsIgnoreCase(effDate)) {
//            return false;
//          }
//        }
//      }
//    }
//    return true;
//  }
//  //check itemid and storeid in BJTable
//  void checkExistTable(String itemID, long storeID){
//    for (int i = 0; i<table.getRowCount(); i++){
//      if (table.getValueAt(i,COL_ITEM_ID).toString().equalsIgnoreCase(itemID)){
//        if (Long.parseLong(table.getValueAt(i,COL_STORE_ID).toString())== storeID){
//          vSql.removeElementAt(i);
//          dm.removeRow(i);
//          return;
//        }
//      }
//    }
//    return;
//  }
//  //void getItemAttr(String itemID)
//  void getItemAttr(String itemID){
//    try{
//
//      Statement stmt = null;
//      ResultSet rs = null;
//      String sql =
//          "select attr_dtl_desc from btm_im_item_master i, btm_im_attr_dtl a " +
//          " where i.attr1_dtl_id = a.attr_dtl_id and i.item_id = '" + itemID + "'";
//      System.out.println(sql);
//      stmt = DAL.getConnection().createStatement();
//      rs = DAL.executeQueries(sql,stmt);
//      if (rs.next()) {
//        attr1 = rs.getString("attr_dtl_desc");
//      }
//      sql = "";
//      sql =
//          "select attr_dtl_desc from btm_im_item_master i, btm_im_attr_dtl a " +
//          " where i.attr2_dtl_id = a.attr_dtl_id and i.item_id = '" + itemID + "'";
//      rs = DAL.executeQueries(sql,stmt);
//      if (rs.next()){
//        attr2 = rs.getString("attr_dtl_desc");
//      }
//      rs.close();
//      stmt.close();
//
//    }catch(Exception e){
//
//    }
//  }
//  //update Unit Cost on table BTM_IM_Price_Hist
//  private void updateUnitCost(){
//    Vector vSqlUpdateUnitCost = new Vector();
//    vSqlUpdateUnitCost.clear();
//    if (!vUnitCost.isEmpty()){
//      for (int i = 0; i < table.getRowCount(); i++) {
//        double unitCost = Double.parseDouble(  vUnitCost.get(i).toString());
//        String itemId = table.getValueAt(i, COL_ITEM_ID).toString().trim();
//        String storeId = table.getValueAt(i, COL_STORE_ID).toString().trim();
//        vSqlUpdateUnitCost.addElement("Update BTM_IM_PRICE_HIST set Unit_Cost=" +
//                                      unitCost + " where Item_id='" + itemId +
//                                      "' and Store_id=" + storeId +
//                                      " and status='1'");
//      }
//    }
//    if(!vSqlUpdateUnitCost.isEmpty()){
//      try{
//        stmt = DAL.getConnection().createStatement(ResultSet.
//                                               TYPE_SCROLL_INSENSITIVE,
//                                               ResultSet.CONCUR_READ_ONLY);
//        DAL.executeBatchQuery(vSqlUpdateUnitCost,stmt);
//        stmt.close();
//      }
//      catch(Exception ex){};
//    }
//  }
//
//  //Check exist
//  boolean checkExist(String itemID, long storeID){
//    try{
//
//      Statement stmt = null;
//      ResultSet rs = null;
//      String sql = "Select item_id, new_price from btm_im_price_hist where item_id ='" +
//          itemID + "' and store_id =" + storeID + " and price_end_date=to_date('7/7/7777','dd/mm/yyyy')";
//
//      stmt=DAL.getConnection().createStatement();
//      rs = DAL.executeQueries(sql,stmt);
//      stmt.close();
//
//      System.out.println(sql);
//      stmt = DAL.getConnection().createStatement(ResultSet.
//                                               TYPE_SCROLL_INSENSITIVE,
//                                               ResultSet.CONCUR_READ_ONLY);
//
//      rs = DAL.executeScrollQueries(sql,stmt);
//
//      if (rs.next()){
//        oldPrice = rs.getDouble("new_price");
//        rs.close();
//        stmt.close();
//        return true;
//      }
//      rs.close();
//      stmt.close();
//
//    }catch(Exception e){
////      ExceptionHandle eh = new ExceptionHandle();
////      eh.ouputError(e);
//    }
//    oldPrice = 0;
//    return false;
//  }
//
//  void txtNewPrice_keyReleased(KeyEvent e) {
//    ut.getYeroCode(1,1);
//    char key = e.getKeyChar();
//    if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_BACK_SPACE) return;
//    else if (key < '0' || key > '9'){
//      if (key != ',' && key != '.'){
//        return;
//      }
//    }
//  }
//
//  void btnDone_actionPerformed(ActionEvent e) {
//    if(!vSql.isEmpty()){
//      DAL.setBeginTrans(DAL.getConnection());
//      try {
//        stmt = DAL.getConnection().createStatement();
//        if (!vSqlUpd.isEmpty()) {
//          DAL.executeBatchQuery(vSqlUpd,stmt);
//        }
//        DAL.executeBatchQuery(vSql);
//        stmt.close();
//              updatePriceHistTable();
//      }
//      catch (Exception ex) {
//        ExceptionHandle eh = new ExceptionHandle();
//        eh.ouputError(ex);
//      }
//       DAL.setEndTrans(DAL.getConnection());
//      vSqlUpd.clear();
//      vSql.clear();
//      vUnitCost.clear();
//    }
//    while(dm.getRowCount()>0){
//      dm.removeRow(0);
//    }
//    resetData();
//    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
//  }
//  //update table price hist
//  //set status=1 for rows that have affect date=today
//  void updatePriceHistTable(){
//    String sql="";
//    //reset status =0 for items that have to update status (effect date=today, end_date=7777-7-7)
//    sql="update btm_im_price_hist set status = '0',price_end_date = to_Date('"+ut.getSystemDate(1)+"','dd-MM-yyyy')"+
//        "where item_id in "+
//        " (select distinct item_id from btm_im_price_hist "+
//        " where price_eff_date  = to_date('" + ut.getSystemDate() +
//        "','" + ut.DATE_FORMAT + "') and price_end_date = to_date('7777-7-7','yyyy-MM-dd')) and status =1"+
//        " and price_eff_date  <>  to_Date('"+ut.getSystemDate(1)+"','dd-MM-yyyy')";
//    System.out.println(sql);
//    //set status =1 for items that have to update status (effect date=today,end_date=7777-7-7)
//    DAL.executeQueries(sql);
//    sql="update btm_im_price_hist set status = '1'"+
//                       " where price_eff_date  = to_date('" + ut.getSystemDate() +
//                       "','" + ut.DATE_FORMAT + "') and price_end_date = to_date('7777-7-7','yyyy-MM-dd')";
//
//    DAL.executeQueries(sql);
//
//  }
//
//  //Reset data
//  void resetData(){
//    txtItemID.setText("");
//    txtStoreID.setText("");
//    vList.clear();
//    vDataList.clear();
//    vItemID.clear();
//    txtUnitCost.setText("");
//    txtNewPrice.setText("");
//    txtEffDate.setText("");
//    txtOldPrice.setText("");
//    txtClosedDate.setText("7/7/7777");
//    btnClosedDate.setEnabled(false);
//    txtDescription.setText("");
//    childLocationID = "";
//  }
//  void btnCancel_actionPerformed(ActionEvent e) {
//    while(dm.getRowCount()>0){
//      dm.removeRow(0);
//    }
//    vSql.clear();
//    vSqlUpd.clear();
//    vUnitCost.clear();
//    resetData();
//    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
//    frmMain.setTitle(lang.getString("TT014")+" - Inventory Management");
//
//  }
//  private void registerKeyboardActions() {
//   /// set up the maps:
//   InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
//   ActionMap actionMap = getActionMap();
//
//   // F1
//  Integer key = new Integer(KeyEvent.VK_F1);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), key);
//  actionMap.put(key, new KeyAction(key));
//
//  // F2
//  key = new Integer(KeyEvent.VK_F2);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), key);
//  actionMap.put(key, new KeyAction(key));
//
//  // F3
//  key = new Integer(KeyEvent.VK_F3);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), key);
//  actionMap.put(key, new KeyAction(key));
//
//  // F4
//  key = new Integer(KeyEvent.VK_F4);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), key);
//  actionMap.put(key, new KeyAction(key));
//
//  // F5
//  key = new Integer(KeyEvent.VK_F5);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
//  actionMap.put(key, new KeyAction(key));
//
//  // F5
//  key = new Integer(KeyEvent.VK_F5);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
//  actionMap.put(key, new KeyAction(key));
//  // F6
//  key = new Integer(KeyEvent.VK_F6);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), key);
//  actionMap.put(key, new KeyAction(key));
//  // F7
//  key = new Integer(KeyEvent.VK_F7);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), key);
//  actionMap.put(key, new KeyAction(key));
//  // F8
//  key = new Integer(KeyEvent.VK_F8);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), key);
//  actionMap.put(key, new KeyAction(key));
//  // F9
//  key = new Integer(KeyEvent.VK_F9);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), key);
//  actionMap.put(key, new KeyAction(key));
//  // F10
//  key = new Integer(KeyEvent.VK_F10);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), key);
//  actionMap.put(key, new KeyAction(key));
//  // F11
//  key = new Integer(KeyEvent.VK_F11);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), key);
//  actionMap.put(key, new KeyAction(key));
//  // F12
//  key = new Integer(KeyEvent.VK_F12);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
//  actionMap.put(key, new KeyAction(key));
//
//  // ENTER
//   key = new Integer(KeyEvent.VK_ENTER);
//   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
//   actionMap.put(key, new KeyAction(key));
//
//  // Escape
//  key = new Integer(KeyEvent.VK_ESCAPE);
//  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
//  actionMap.put(key, new KeyAction(key));
// }
// class KeyAction extends AbstractAction {
//
//      private Integer identifier = null;
//
//      public KeyAction(Integer identifier) {
//        this.identifier = identifier;
//      }
//
//      /**
//       * Invoked when an action occurs.
//       */
//      public void actionPerformed(ActionEvent e) {
//        if (identifier.intValue() == KeyEvent.VK_F1) {
//          btnDone.doClick();
//        }
//        else if (identifier.intValue() == KeyEvent.VK_F2) {
//          btnAdd.doClick();
//        }
//        else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
//          btnCancel.doClick();
//        }
//      }
// }
//
//  void txtNewPrice_focusLost(FocusEvent e) {
//    if (!txtNewPrice.getText().equalsIgnoreCase("")){
//      txtNewPrice.setText(ut.formatNumber(txtNewPrice.getText()));
//    }
//  }
//
//  void txtNewPrice_focusGained(FocusEvent e) {
//    if (!txtNewPrice.getText().equalsIgnoreCase("")){
//      txtNewPrice.setText("" + ut.convertToDouble(txtNewPrice.getText()));
//    }
//  }
//
//  void chkAllStore_actionPerformed(ActionEvent e) {
//    if (chkAllStore.isSelected()){
//      txtStoreID.setText("");
//      vList.clear();
//      vDataList.clear();
//      childLocationID = "";
//    }
//  }
//
//
//  void txtUnitCost_keyTyped(KeyEvent e) {
//    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
//      txtUnitCost.requestFocus(true);
//      txtUnitCost.selectAll();
//      return;
//    }
//    ut.limitLenjTextField(e, txtUnitCost, ItemLocSupplier.LEN_UNIT_COST);
//    if (e.getKeyChar() != '.') {
//      if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
//          (e.getKeyChar() != e.VK_BACK_SPACE))
//        e.consume();
//    }
//    else if (txtUnitCost.getText().indexOf(".") >= 0 ||
//             txtUnitCost.getText().length() == 0)
//      e.consume();
//
//  }
//
//  void txtItemID_keyTyped(KeyEvent e) {
//    ut.limitLenjTextField(e,txtItemID,Constant.LENGHT_ITEM_ID_INPUT); // Vinh modify
//    ut.checkNumber(e);
//    if(e.getKeyChar() == KeyEvent.VK_ENTER){
//      txtNewPrice.requestFocus(true);
//    }
//  }
//
//  void txtItemID_focusLost(FocusEvent e) {
//    if (txtItemID.getText().equalsIgnoreCase("")){
//      return;
//    }
//    setInfoForItem();
//  }
//  void setInfoForItem(){
//    String sql = " select distinct i.item_id, ph.new_price, un.new_unit_cost  "
//    + "from btm_im_item_master i,  "
//    + "	 btm_im_price_hist ph, "
//    + "	 btm_im_unit_cost_hist un "
//    + "where i.item_id = ph.item_id  "
//    + "and i.status <> 'D'  "
//    + "and ph.status = 1  "
//    + "and i.art_no = '"+txtItemID.getText()+"' "
//    + "and i.item_id = un.item_id "
//    + "and un.status = 1  ";
//
//    Statement stmt = null;
//    ResultSet rs = null;
//
//    vItemID.clear();
//    try{
//      System.out.println(sql);
//      stmt = DAL.getConnection().createStatement();
//      rs = DAL.executeQueries(sql,stmt);
//      while (rs.next()){
//        vItemID.addElement(rs.getString("item_id"));
//        txtOldPrice.setText(ut.formatNumber("" + rs.getDouble("new_price")));
//        txtUnitCost.setText(ut.formatNumber(""+rs.getDouble("new_unit_cost")));
//      }
//      rs.close();
//      stmt.close();
//    }catch(Exception ex){
//      ex.printStackTrace();
//    }
//
//  }
//
//  void table_keyPressed(KeyEvent e) {
//    if (e.getKeyCode() == KeyEvent.VK_F2) {
//        btnAdd.doClick();
//    }
//  }
//
//}
//
//class PanelPriceChange_btnItemID_actionAdapter implements java.awt.event.ActionListener {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_btnItemID_actionAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnItemID_actionPerformed(e);
//  }
//}
//
//class PanelPriceChange_btnStoreID_actionAdapter implements java.awt.event.ActionListener {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_btnStoreID_actionAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnStoreID_actionPerformed(e);
//  }
//}
//
//class PanelPriceChange_txtNewPrice_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_txtNewPrice_keyAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtNewPrice_keyTyped(e);
//  }
//  public void keyReleased(KeyEvent e) {
//    adaptee.txtNewPrice_keyReleased(e);
//  }
//}
//
//class PanelPriceChange_btnEffDate_actionAdapter implements java.awt.event.ActionListener {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_btnEffDate_actionAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnEffDate_actionPerformed(e);
//  }
//
//}
//
//class PanelPriceChange_btnClosedDate_actionAdapter implements java.awt.event.ActionListener {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_btnClosedDate_actionAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnClosedDate_actionPerformed(e);
//  }
//}
//
//class PanelPriceChange_btnAdd_actionAdapter implements java.awt.event.ActionListener {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_btnAdd_actionAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnAdd_actionPerformed(e);
//  }
//}
//
//class PanelPriceChange_btnDone_actionAdapter implements java.awt.event.ActionListener {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_btnDone_actionAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnDone_actionPerformed(e);
//  }
//}
//
//class PanelPriceChange_btnCancel_actionAdapter implements java.awt.event.ActionListener {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_btnCancel_actionAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnCancel_actionPerformed(e);
//  }
//}
//
//class PanelPriceChange_txtNewPrice_focusAdapter extends java.awt.event.FocusAdapter {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_txtNewPrice_focusAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void focusLost(FocusEvent e) {
//    adaptee.txtNewPrice_focusLost(e);
//  }
//  public void focusGained(FocusEvent e) {
//    adaptee.txtNewPrice_focusGained(e);
//  }
//}
//
//class PanelPriceChange_chkAllStore_actionAdapter implements java.awt.event.ActionListener {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_chkAllStore_actionAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.chkAllStore_actionPerformed(e);
//  }
//}
//
//class PanelPriceChange_txtUnitCost_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_txtUnitCost_keyAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtUnitCost_keyTyped(e);
//  }
//}
//
//class PanelPriceChange_txtItemID_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_txtItemID_keyAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtItemID_keyTyped(e);
//  }
//}
//
//class PanelPriceChange_txtItemID_focusAdapter extends java.awt.event.FocusAdapter {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_txtItemID_focusAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void focusLost(FocusEvent e) {
//    adaptee.txtItemID_focusLost(e);
//  }
//}
//
//class PanelPriceChange_table_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelPriceChange adaptee;
//
//  PanelPriceChange_table_keyAdapter(PanelPriceChange adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyPressed(KeyEvent e) {
//    adaptee.table_keyPressed(e);
//  }
//}
//
