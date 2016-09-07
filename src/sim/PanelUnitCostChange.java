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

/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Cost Change</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: BTM International Software</p>
 * @author Nguyen Quang Phuoc
 * @version 1.0
 */
public class PanelUnitCostChange extends JPanel {
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_SIZE = 2;
  private static final int COL_STORE_ID = 3;
  private static final int COL_STORE_NAME = 4;
  private static final int COL_NEW_UNIT_COST = 5; //
  private static final int COL_EFFECTIVE_DATE = 6;
  private static final int COL_CLOSE_DATE = 7;

  //Column Order of UnitCost History
  private static final int COL_HIS_STORE = 0;
  private static final int COL_HIS_OLD_COST = 1; //
  private static final int COL_HIS_NEW_COST = 2;
  private static final int COL_HIS_EFFECT_DATE = 3;
  private static final int COL_HIS_CLOSED_DATE = 4;
  private static final int COL_HIS_STATUS = 5;

  private static final int ROW_NUM = 4;
  DataAccessLayer DAL;
  ResourceBundle lang;
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
  JLabel lblNewUnitCost = new JLabel();
  JButton btnClosedDate = new JButton();
  JTextField txtClosedDate = new JTextField();
  JButton btnEffDate = new JButton();
  JTextField txtEffDate = new JTextField();
  JTextField txtNewUnitCost = new JTextField();
  JScrollPane jScrollPane2 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true){
    public boolean isCellEditable(int row,int col){
      return false;
    }
    public Class getColumnClass(int col){
      switch(col){
        case COL_NEW_UNIT_COST: return Long.class;
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
        case COL_HIS_OLD_COST: return Long.class;
        //case 2: return Long.class;
        case COL_HIS_NEW_COST: return Long.class;
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
  JLabel lblOldUnitCost = new JLabel();
  JTextField txtOldUnitCost = new JTextField();
  JLabel lblPrice = new JLabel();
  JTextField txtPrice = new JTextField();
  JPanel jPnlInfoTopLeft = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JLabel lblChangeDate = new JLabel();
  JTextField txtChangeDate = new JTextField();
  JButton btnChangeDate = new JButton();
  JLabel lblDescription = new JLabel();
  Border border1 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border2 = new TitledBorder(border1, "Unit Cost History");
  JScrollPane jScrollPane3 = new JScrollPane();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  BJComboBox cboStore = new BJComboBox();
  public PanelUnitCostChange(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }


  void jbInit() throws Exception {
    DAL.getConnfigParameter();
    lang=DAL.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

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
    lblStoreID.setText(lang.getString("AppliedStore")+":" );
    lblStoreID.setBounds(new Rectangle(15, 13, 104, 22));
    lblItemUpc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemUpc.setPreferredSize(new Dimension(90, 20));
    lblItemUpc.setText(lang.getString("UPC"));
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
    btnItemUpc.addActionListener(new PanelUnitCostChange_btnItemID_actionAdapter(this));
    txtItemUpc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemUpc.setPreferredSize(new Dimension(150, 20));
    txtItemUpc.setText("");
    txtItemUpc.setBounds(new Rectangle(28, 13, 150, 22));
    txtItemUpc.addFocusListener(new PanelUnitCostChange_txtItemUpc_focusAdapter(this));
    txtItemUpc.addKeyListener(new PanelUnitCostChange_txtItemUpc_keyAdapter(this));
    lblClosedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblClosedDate.setPreferredSize(new Dimension(90, 20));
    lblClosedDate.setText(lang.getString("ClosedDate")+":" );
    lblClosedDate.setBounds(new Rectangle(15, 93, 90, 23));
    lblEffDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEffDate.setPreferredSize(new Dimension(90, 20));
    lblEffDate.setText(lang.getString("EffectDate")+":" );
    lblEffDate.setBounds(new Rectangle(15, 66, 90, 23));
    lblNewUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblNewUnitCost.setPreferredSize(new Dimension(90, 20));
    lblNewUnitCost.setText(lang.getString("NewUnitCost") );
    lblNewUnitCost.setBounds(new Rectangle(41, 93, 90, 20));
    btnClosedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClosedDate.setPreferredSize(new Dimension(30, 20));
    btnClosedDate.setText("...");
    btnClosedDate.setBounds(new Rectangle(148, 93, 30, 23));
    btnClosedDate.setEnabled(false);
    btnClosedDate.addActionListener(new PanelUnitCostChange_btnClosedDate_actionAdapter(this));
    btnEffDate.setBounds(new Rectangle(148, 66, 30, 23));
    btnEffDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnEffDate.setPreferredSize(new Dimension(30, 20));
    btnEffDate.setText("...");
    btnEffDate.addActionListener(new PanelUnitCostChange_btnEffDate_actionAdapter(this));
    txtEffDate.setBackground(Color.white);
    txtEffDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEffDate.setPreferredSize(new Dimension(160, 20));
    txtEffDate.setEditable(false);
    txtEffDate.setText("");
    txtEffDate.setBounds(new Rectangle(1, 66, 144, 23));
    txtNewUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtNewUnitCost.setPreferredSize(new Dimension(195, 20));
    txtNewUnitCost.setText("");
    txtNewUnitCost.setBounds(new Rectangle(28, 93, 186, 23));
    txtNewUnitCost.addFocusListener(new PanelUnitCostChange_txtNewUnitCost_focusAdapter(this));
    txtNewUnitCost.addKeyListener(new PanelUnitCostChange_txtNewUnitCost_keyAdapter(this));
    txtClosedDate.setPreferredSize(new Dimension(160, 20));
    txtClosedDate.setEditable(false);
    txtClosedDate.setBackground(Color.white);
    txtClosedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtClosedDate.setText("7/7/7777");
    txtClosedDate.setBounds(new Rectangle(1, 93, 144, 23));
    jScrollPane2.setPreferredSize(new Dimension(800, 240));
    btnCancel.setToolTipText(lang.getString("Cancel") +" (F12)");
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel") +" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelUnitCostChange_btnCancel_actionAdapter(this));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setText(lang.getString("Add")+" (F2)");
    btnAdd.addActionListener(new PanelUnitCostChange_btnAdd_actionAdapter(this));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setText(lang.getString("Done")+" (F1)");
    btnDone.addActionListener(new PanelUnitCostChange_btnDone_actionAdapter(this));
    lblOldUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblOldUnitCost.setPreferredSize(new Dimension(90, 20));
    lblOldUnitCost.setText(lang.getString("OldUnitCost")+":" );
    lblOldUnitCost.setBounds(new Rectangle(41, 66, 90, 20));
    txtOldUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtOldUnitCost.setPreferredSize(new Dimension(195, 20));
    txtOldUnitCost.setEditable(false);
    txtOldUnitCost.setText("");
    txtOldUnitCost.setBounds(new Rectangle(28, 66, 185, 23));
    txtOldUnitCost.addKeyListener(new PanelUnitCostChange_txtUnitCost_keyAdapter(this));
    lblPrice.setText(lang.getString("SellingPrice")+":" );
    lblPrice.setBounds(new Rectangle(41, 39, 90, 20));
    lblPrice.setPreferredSize(new Dimension(90, 20));
    lblPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPrice.setText("");
    txtPrice.setBounds(new Rectangle(28, 39, 185, 22));
    txtPrice.setPreferredSize(new Dimension(185, 20));
    txtPrice.setEditable(false);
    txtPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    table.addKeyListener(new PanelUnitCostChange_table_keyAdapter(this));
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
    lblDescription.setText(lang.getString("Description") +":");
    lblDescription.setBounds(new Rectangle(41, 3, 90, 20));
    jScrollPane3.setPreferredSize(new Dimension(600, 60));
    jScrollPane3.setBounds(new Rectangle(100, 5, 654, 124));
    jPanel1.setLayout(borderLayout7);
    jPanel2.setLayout(null);
    jTblHistory.setRowHeight(23);
    jPanel1.setPreferredSize(new Dimension(800, 165));
    jPanel2.setPreferredSize(new Dimension(610, 20));
    cboStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboStore.setBounds(new Rectangle(1, 13, 177, 22));
    cboStore.addActionListener(new PanelUnitCostChange_cboStore_actionAdapter(this));
    jPanel10.add(txtPrice, null);
    jPanel10.add(txtOldUnitCost);
    jPanel10.add(txtNewUnitCost);
    jPanel10.add(txtItemUpc, null);
    jPanel10.add(btnItemUpc, null);
    jPnlInfoTopLeft.add(jPanel7, java.awt.BorderLayout.WEST);
    jPnlHeader.add(btnDone, null);
    jPnlHeader.add(btnAdd, null);
    jPnlHeader.add(btnCancel, null);
    this.add(jPnlInfo,  BorderLayout.CENTER);
    jPanel7.add(lblOldUnitCost);
    jPanel7.add(lblItemUpc, null);
    jPanel7.add(lblPrice, null);
    jPanel7.add(lblNewUnitCost);
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
    jPanel2.add(jScrollPane3, null);
    jScrollPane3.getViewport().add(jTblHistory);
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
    this.add(jPnlHeader, java.awt.BorderLayout.NORTH);
    jPnlInfo.add(jPanel1, java.awt.BorderLayout.NORTH);
    jPnlInfo.add(jPanel2, java.awt.BorderLayout.CENTER);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    dm.addColumn(lang.getString("ItemID"));
    dm.addColumn(lang.getString("UPC"));
    dm.addColumn(lang.getString("Size"));
    dm.addColumn(lang.getString("StoreID"));
    dm.addColumn(lang.getString("StoreName"));
    dm.addColumn(lang.getString("NewUnitCost"));
    dm.addColumn(lang.getString("EffectDate"));
    dm.addColumn(lang.getString("ClosedDate"));
    table.setRowHeight(30);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);
    cboStore.setData2(frmMain.storeBusObj.getData(DAL));
  }


//select item that will be changed Unit Cost
  //click button select Item_Id
  void btnItemID_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0016"), true, frmMain,itemBusObject);
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.cboStatus.setEditable(false);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done){
      txtItemUpc.setText(dlgItemLookup.getUPC());
      txtPrice.setText(dlgItemLookup.getUnitPrice());
      double unitCost = getUnitCost(dlgItemLookup.getItemID(),cboStore.getSelectedData());
      txtOldUnitCost.setText(ut.formatNumber("" + unitCost));
      txtNewUnitCost.setText("");
      showHistory(ROW_NUM,getItemId(dlgItemLookup.getUPC()),cboStore.getSelectedData());
    }
  }

  //or enter Art_no into textbox
  void txtItemUpc_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtItemUpc,Constant.LENGHT_UPC_INPUT);
    ut.checkNumber(e);
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
      txtNewUnitCost.requestFocus(true);
    }
  }

  //after enter Art_no into textbox
  void txtItemUpc_focusLost(FocusEvent e) {
    txtNewUnitCost.setText("");
    if (txtItemUpc.getText().equalsIgnoreCase("") || !checkItemExist(txtItemUpc.getText())){
      txtPrice.setText("");
      txtOldUnitCost.setText("");
      while (dmHis.getRowCount()>0){
        dmHis.removeRow(0);
      }
//      ut.showMessage(frmMain,"Warning","Wrong UPC or Item is not in Approve Status");
      return;
    }

    String itemId = getItemId(txtItemUpc.getText());
    txtPrice.setText(ut.formatNumber("" + getPrice(itemId,cboStore.getSelectedData())));
    txtOldUnitCost.setText(ut.formatNumber("" + getUnitCost(itemId,cboStore.getSelectedData())));
    showHistory(ROW_NUM,getItemId(txtItemUpc.getText()),cboStore.getSelectedData());
  }

  // get New Unit Cost from BTM_IM_UNIT_COST_HIST
  double getUnitCost(String itemId,String storeId){
    double unitCost = 0;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "Select a.NEW_UNIT_COST From BTM_IM_UNIT_COST_HIST a Where a.ITEM_ID ='"
        + itemId + "'and a.UNIT_COST_EFF_DATE = (Select MAX(b.UNIT_COST_EFF_DATE) From " +
        "BTM_IM_UNIT_COST_HIST b where b.ITEM_ID= a.ITEM_ID and b.STATUS='1' and a.STORE_ID=b.STORE_ID)";
    try {
      if(!storeId.equals("")){
        sql=sql + " and a.STORE_ID='"+storeId+"'";
      }
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        unitCost = rs.getDouble("NEW_UNIT_COST");
      }
      rs.close();
      stmt.close();
    }catch (Exception ex) {
      ex.printStackTrace();
    }
    return unitCost;
  }

  // get New Price from BTM_IM_PRICE_HIST
  double getPrice(String itemId,String storeId){
    double price = 0;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "Select a.NEW_PRICE From BTM_IM_PRICE_HIST a Where a.ITEM_ID ='"
        + itemId + "'and a.PRICE_EFF_DATE = (Select MAX(b.PRICE_EFF_DATE) From " +
        "BTM_IM_PRICE_HIST b where b.ITEM_ID= a.ITEM_ID and b.STATUS='1' and a.STORE_ID=b.STORE_ID)";
    try {
      if(!storeId.equals("")){
        sql=sql + " and a.STORE_ID='"+storeId+"'";
      }
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        price = rs.getDouble("NEW_PRICE");
      }
      rs.close();
      stmt.close();
    }catch (Exception ex) {
      ex.printStackTrace();
    }
    return price;
  }

  //get Item_Id from BTM_IM_ITEM_MASTER
  //parameter Art_No : String
  String getItemId(String upc){
    String itemId = "";
    ResultSet rs = null;
    Statement stmt = null;
    String sql ="Select ITEM_ID From BTM_IM_ITEM_MASTER Where ART_NO='" + upc + "'";
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        itemId = rs.getString("ITEM_ID");
      }
      rs.close();
      stmt.close();
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
      rs.close();
      stmt.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

//enter new Unit Cost into textbox
  //check New_Unit_Cost is numberic
  void txtNewUnitCost_keyTyped(KeyEvent e) {
    ut.checkNumberPositive(e, txtNewUnitCost.getText());
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
     btnEffDate.requestFocus(true);
    }
  }

  void txtNewUnitCost_keyReleased(KeyEvent e) {
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
  void txtNewUnitCost_focusLost(FocusEvent e) {
    if (!txtNewUnitCost.getText().equalsIgnoreCase("")){
      txtNewUnitCost.setText(ut.formatNumber(txtNewUnitCost.getText()));
    }
  }

  //re_enter New_Unit_Cost into textbox
  void txtNewUnitCost_focusGained(FocusEvent e) {
    if (!txtNewUnitCost.getText().equalsIgnoreCase("")){
      txtNewUnitCost.setText("" + ut.convertToDouble(txtNewUnitCost.getText()));
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
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2001_UPCInvalid"));
      btnItemUpc.requestFocus(true);
//      txtItemUpc.requestFocus(true);
      return;
    }
    if (txtNewUnitCost.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2002_UnitCostNotNull"),0,JOptionPane.INFORMATION_MESSAGE);
      txtNewUnitCost.requestFocus(true);
      return;
    }
    if (!ut.isDoubleString("" + ut.convertToDouble(txtNewUnitCost.getText().trim()))){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2003_UnitCostNumeric"));
      txtNewUnitCost.requestFocus(true);
      return;
    }

    if (txtEffDate.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2004_ChooseEffectiveDate"));
      btnEffDate.requestFocus();
      return;
    }
    if (!ut.compareDate1(txtEffDate.getText(),txtChangeDate.getText())){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2005_EffectiveDateGreaterThanCurrenDate"));
      btnEffDate.requestFocus(true);
      return;
    }
    if (!ut.compareDate(txtClosedDate.getText(),txtEffDate.getText())){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2006_ClosedDateGreaterThanEffectiveDate"));
      btnClosedDate.requestFocus(true);
      return;
    }
    Object[] rowData;
    String itemId = getItemId(txtItemUpc.getText());
    String strId = cboStore.getSelectedData();
    String strName = frmMain.storeBusObj.getStoreName(DAL,cboStore.getSelectedData());
    String itmSize = getItemSize(itemId);

    //the case of all store
        if(strId.equals("")){
          String SQL = "SELECT * FROM BTM_IM_STORE";
          ResultSet rs = null;
          try {
            rs = DAL.executeScrollQueries(SQL);
            String strIdTemp = "";
            String strNameTemp = "";
            while (rs.next()) {
              strIdTemp = rs.getString("STORE_ID");
              strNameTemp = rs.getString("NAME");
              checkExistTable(itemId, strIdTemp, txtEffDate.getText());
              rowData = new Object[] {
                  itemId, txtItemUpc.getText(), itmSize, strIdTemp,
                  strNameTemp, txtNewUnitCost.getText(), txtEffDate.getText(),
                  txtClosedDate.getText()};
              dm.addRow(rowData);
              addIntoVector(itemId, strIdTemp, txtEffDate.getText(),
                            txtClosedDate.getText(),
                            ut.convertToDouble(txtNewUnitCost.getText()),
                            ut.convertToDouble(txtOldUnitCost.getText()),
                            ut.convertToDouble(txtPrice.getText()),
                            txtChangeDate.getText());
            }
          }catch(Exception ex){
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(ex);
          }
        }
        //case if change the price of item for certain store
        else{
          checkExistTable(itemId, strId, txtEffDate.getText());
          rowData = new Object[] {
              itemId, txtItemUpc.getText(), itmSize, strId,
              strName, txtNewUnitCost.getText(), txtEffDate.getText(),
              txtClosedDate.getText()};
          dm.addRow(rowData);
          addIntoVector(itemId, strId, txtEffDate.getText(),
                        txtClosedDate.getText(),
                        ut.convertToDouble(txtNewUnitCost.getText()),
                        ut.convertToDouble(txtOldUnitCost.getText()),
                        ut.convertToDouble(txtPrice.getText()),
                        txtChangeDate.getText());
        }
    resetData();
  }

  void addIntoVector(String itemId,String strId, String effDate,String endDate,double newCost, double oldCost,double unitPrice, String Date){
    String sqlIns="";
    String sqlUpd="";
    String sqlDel="";
    String status ="0";
    String affUCdate = cvtDate(txtEffDate.getText()).trim();
    String sysdate = cvtDate(txtChangeDate.getText()).trim();

    if (affUCdate.equals(sysdate)){
      status ="1";
      sqlUpd = "Update BTM_IM_UNIT_COST_HIST set STATUS = '0', UNIT_COST_END_DATE=to_date('" + effDate +
          "','" + ut.DATE_FORMAT1 + "'), LAST_UPD_DATE = to_date('"+Date+"','dd/MM/yyyy')"+
          " Where ITEM_ID='" + itemId + "' and STORE_ID='" + strId +"' and STATUS = '1'";
      vSqlUpd.addElement(sqlUpd);
    }

    sqlIns = "Insert into BTM_IM_UNIT_COST_HIST(ITEM_ID,STORE_ID,UNIT_COST_EFF_DATE,UNIT_COST_END_DATE,"+
        "NEW_UNIT_COST,OLD_UNIT_COST,STATUS,PRICE,LAST_UPD_DATE,BATCH_SEQ) Values('" + itemId + "'," + strId + ",to_date('" +
        effDate + "','" +  ut.DATE_FORMAT1 + "'),to_date('" + endDate +
        "','" +  ut.DATE_FORMAT1 + "')," + newCost + "," +
        oldCost + ",'" + status + "'," + unitPrice + ",to_date('"+Date+"','dd/MM/yyyy'),0)";

    sqlDel = "Delete BTM_IM_UNIT_COST_HIST Where ITEM_ID='" + itemId + "' and STORE_ID=" +
        strId + " and UNIT_COST_EFF_DATE = to_date('" + effDate + "','" + ut.DATE_FORMAT1 + "')";

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
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return size;
  }

  //Reset data
  void resetData() {
    txtItemUpc.setText("");
    txtOldUnitCost.setText("");
    txtNewUnitCost.setText("");
    txtEffDate.setText("");
    txtPrice.setText("");
    txtChangeDate.setText(ut.getSystemDate(1));
    txtClosedDate.setText("7/7/7777");
    btnClosedDate.setEnabled(false);
    txtDescription.setText("");
    cboStore.setSelectedIndex(0);
    childLocationID = "";
    while(dmHis.getRowCount()>0){
      dmHis.removeRow(0);
    }
  }
//show Unit Cost History
  void showHistory(int rowsNum,String itemId,String storeId) {
      while (dmHis.getRowCount()>0){
        dmHis.removeRow(0);
      }
       dmHis.resetIndexes();
       ResultSet rs = null;
       String SQL = null;
       if(storeId.equals("")){
         SQL = "select NAME,OLD_UNIT_COST, NEW_UNIT_COST,to_char(UNIT_COST_EFF_DATE,'dd/mm/yyyy') EFF_DATE,"
             + "to_char(UNIT_COST_END_DATE,'dd/mm/yyyy') END_DATE, M_STATUS from ("
             + "select NAME, OLD_UNIT_COST, NEW_UNIT_COST, to_date(UNIT_COST_EFF_DATE,'dd/mm/yyyy') UNIT_COST_EFF_DATE,"
             + " to_date(UNIT_COST_END_DATE,'dd/mm/yyyy') UNIT_COST_END_DATE, decode(STATUS,1,'In Use','N/A') M_STATUS "
             + "from BTM_IM_UNIT_COST_HIST a, BTM_IM_STORE b where item_id='" +
             itemId + "' and a.STORE_ID=b.STORE_ID "
             + " order by STATUS desc, UNIT_COST_END_DATE desc)"
             + " where rownum <= " + rowsNum;
       }else{
         SQL = "select NAME,OLD_UNIT_COST, NEW_UNIT_COST,to_char(UNIT_COST_EFF_DATE,'dd/mm/yyyy') EFF_DATE,"
             + "to_char(UNIT_COST_END_DATE,'dd/mm/yyyy') END_DATE, M_STATUS from ("
             + " select NAME, OLD_UNIT_COST, NEW_UNIT_COST, to_date(UNIT_COST_EFF_DATE,'dd/mm/yyyy') UNIT_COST_EFF_DATE,"
             + " to_date(UNIT_COST_END_DATE,'dd/mm/yyyy') UNIT_COST_END_DATE, decode(STATUS,1,'In Use','N/A') M_STATUS "
             + "from BTM_IM_UNIT_COST_HIST a, BTM_IM_STORE b where item_id='"
             + itemId + "' and a.STORE_ID=b.STORE_ID and b.STORE_ID='"
             + storeId + "' order by STATUS desc, UNIT_COST_END_DATE desc) "
             + "where rownum <= " + rowsNum;
       }
       try{
            stmt = DAL.getConnection().createStatement(ResultSet.
                                        TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);
//            System.out.println(SQL);
            rs = DAL.executeQueries(SQL,stmt);
            jTblHistory.setData(rs);
            stmt.close();
       }catch(Exception ex){
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(ex);
       };
       jTblHistory.getTableHeader().setReorderingAllowed(false);
       jTblHistory.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
       jTblHistory.setHeaderName(lang.getString("Store") , COL_HIS_STORE);
       jTblHistory.setHeaderName(lang.getString("OldUnitCost") , COL_HIS_OLD_COST);
       jTblHistory.setHeaderName(lang.getString("NewUnitCost") , COL_HIS_NEW_COST);
       jTblHistory.setHeaderName(lang.getString("EffectDate") , COL_HIS_EFFECT_DATE);
       jTblHistory.setHeaderName(lang.getString("ClosedDate") , COL_HIS_CLOSED_DATE);
       jTblHistory.setHeaderName(lang.getString("Status"), COL_HIS_STATUS);
//       jTblHistory.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 0, 13));
    }

//click button Done
  void btnDone_actionPerformed(ActionEvent e) {
    ut.writeToTextFile("file\\error.txt","Hist UC  "+ ut.getSystemDateTime()+ "\r\n");
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
   er.setData(DAL.getEmpID(), Constant.SCRS_UNIT_COST_CHANGE);
   btnAdd.setEnabled(er.getAdd());
   if(!er.getAdd()){
     ut.showMessage(frmMain, lang.getString("TT002"),
                    lang.getString("MS1085_CanNotUseScreen"));

   }

 } //Check exist
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
    if (txtItemUpc.getText().equalsIgnoreCase("") || !checkItemExist(txtItemUpc.getText())){
      txtPrice.setText("");
      txtOldUnitCost.setText("");
      while (dmHis.getRowCount()>0){
        dmHis.removeRow(0);
      }
      return;
    }
    String itemId = getItemId(txtItemUpc.getText());
    txtPrice.setText(ut.formatNumber("" + getPrice(itemId,cboStore.getSelectedData())));
    txtOldUnitCost.setText(ut.formatNumber("" + getUnitCost(itemId,cboStore.getSelectedData())));
    showHistory(ROW_NUM,getItemId(txtItemUpc.getText()),cboStore.getSelectedData());
  }
}
class PanelUnitCostChange_cboStore_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelUnitCostChange adaptee;
  PanelUnitCostChange_cboStore_actionAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cboStore_actionPerformed(e);
  }
}

class PanelUnitCostChange_btnItemID_actionAdapter implements java.awt.event.ActionListener {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_btnItemID_actionAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnItemID_actionPerformed(e);
  }
}

class PanelUnitCostChange_txtNewUnitCost_keyAdapter extends java.awt.event.KeyAdapter {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_txtNewUnitCost_keyAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtNewUnitCost_keyTyped(e);
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtNewUnitCost_keyReleased(e);
  }
}

class PanelUnitCostChange_btnEffDate_actionAdapter implements java.awt.event.ActionListener {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_btnEffDate_actionAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnEffDate_actionPerformed(e);
  }

}

class PanelUnitCostChange_btnClosedDate_actionAdapter implements java.awt.event.ActionListener {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_btnClosedDate_actionAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClosedDate_actionPerformed(e);
  }
}

class PanelUnitCostChange_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_btnAdd_actionAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelUnitCostChange_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_btnDone_actionAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelUnitCostChange_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_btnCancel_actionAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelUnitCostChange_txtNewUnitCost_focusAdapter extends java.awt.event.FocusAdapter {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_txtNewUnitCost_focusAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtNewUnitCost_focusLost(e);
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtNewUnitCost_focusGained(e);
  }
}

class PanelUnitCostChange_txtUnitCost_keyAdapter extends java.awt.event.KeyAdapter {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_txtUnitCost_keyAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtUnitCost_keyTyped(e);
  }
}

class PanelUnitCostChange_txtItemUpc_keyAdapter extends java.awt.event.KeyAdapter {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_txtItemUpc_keyAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemUpc_keyTyped(e);
  }
}

class PanelUnitCostChange_txtItemUpc_focusAdapter extends java.awt.event.FocusAdapter {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_txtItemUpc_focusAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemUpc_focusLost(e);
  }
}

class PanelUnitCostChange_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelUnitCostChange adaptee;

  PanelUnitCostChange_table_keyAdapter(PanelUnitCostChange adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

