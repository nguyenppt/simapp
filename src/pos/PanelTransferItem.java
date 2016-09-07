//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//


















































package pos;

import java.awt.geom.*;
import java.awt.print.PrinterJob;
import java.awt.print.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
import common.*;
import btm.swing.*;
import btm.attr.*;
import java.awt.event.*;
import java.util.Vector;
import java.beans.*;
import java.sql.Statement;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//not use now
public class PanelTransferItem extends JPanel {
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_DESC = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_QUANTITY = 4; //

  DataAccessLayer DAL;
  Statement stmt = null;
  FrameMain frmMain;
  Utils ut = new Utils();
  int flagButton = Constant.TI_TRANSFER_ITEM;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JTextField txtReceiverName = new JTextField();
  JTextField txtCreaterName = new JTextField();
  JTextField txtTransferDate = new JTextField();
  JTextField txtSrcStoreID = new JTextField();
  JTextField txtTransferID = new JTextField();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true) {
    public boolean isCellEditable(int row, int col) {
      return false;
    }

    public void tableChanged(TableModelEvent e) {
      super.tableChanged(e);
      repaint();
    }

    public Class getColumnClass(int col) {
      if (col == COL_QUANTITY) {
        return Long.class;
      }
      return Object.class;
    }
  };
  BJButton btnDone = new BJButton();
  BJButton btnQuantity = new BJButton();
  BJButton btnDeleteItem = new BJButton();
  BJButton btnCancel = new BJButton();
  String transferID;
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  String itemDesc = "";
  long stockOnHand = 0;
  BorderLayout borderLayout3 = new BorderLayout();
  BJComboBox cboDesStoreID = new BJComboBox();

  public PanelTransferItem(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      setText();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
//    System.out.println("N22");
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    jPanel2.setBackground(SystemColor.control);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setPreferredSize(new Dimension(600, 100));
    jPanel2.setLayout(borderLayout2);
    this.setEnabled(true);
    this.setPreferredSize(new Dimension(600, 800));
    this.setRequestFocusEnabled(true);
    jPanel3.setBackground(SystemColor.control);
    jPanel3.setDebugGraphicsOptions(0);
    jPanel3.setPreferredSize(new Dimension(600, 400));
    jPanel3.setLayout(borderLayout3);
    jPanel1.setBackground(SystemColor.control);
    jPanel1.setPreferredSize(new Dimension(300, 100));
    jPanel1.setLayout(borderLayout4);
    jPanel5.setPreferredSize(new Dimension(300, 100));
    jPanel5.setLayout(borderLayout5);
    jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setText("Receiver Name:");
    jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText("Creater Name:");
    jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setText("Transfer Date:");
    jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel4.setPreferredSize(new Dimension(120, 20));
    jLabel4.setText("To Store ID:");
    jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel5.setPreferredSize(new Dimension(120, 20));
    jLabel5.setText("From Store ID");
    jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel6.setPreferredSize(new Dimension(120, 20));
    jLabel6.setText("Transfer ID:");
    txtReceiverName.setFont(new java.awt.Font("Dialog", 1, 11));
    txtReceiverName.setPreferredSize(new Dimension(150, 20));
    txtReceiverName.setText("");
    txtReceiverName.addFocusListener(new PanelTransferItem_txtReceiverName_focusAdapter(this));
    txtReceiverName.addKeyListener(new
                                   PanelTransferItem_txtReceiverName_keyAdapter(this));
    txtCreaterName.setFont(new java.awt.Font("Dialog", 1, 11));
    txtCreaterName.setNextFocusableComponent(txtReceiverName);
    txtCreaterName.setPreferredSize(new Dimension(150, 20));
    txtCreaterName.setText("");
    txtCreaterName.addKeyListener(new
                                  PanelTransferItem_txtCreaterName_keyAdapter(this));
    txtTransferDate.setFont(new java.awt.Font("Dialog", 1, 11));
    txtTransferDate.setPreferredSize(new Dimension(150, 20));
    txtTransferDate.setText("");
    txtSrcStoreID.setFont(new java.awt.Font("Dialog", 1, 11));
    txtSrcStoreID.setPreferredSize(new Dimension(150, 20));
    txtSrcStoreID.setText("");
    txtSrcStoreID.addKeyListener(new PanelTransferItem_txtSrcStoreID_keyAdapter(this));
    txtTransferID.setFont(new java.awt.Font("Dialog", 1, 11));
    txtTransferID.setPreferredSize(new Dimension(150, 20));
    txtTransferID.setText("");
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(600, 395));
    btnDone.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnDone.setToolTipText("Done (F1)");
    btnDone.setActionCommand("btnDone");
    btnDone.setText("Done (F1)");
    btnDone.addActionListener(new PanelTransferItem_btnDone_actionAdapter(this));
//    btnQuantity.setEnabled(false);
    btnQuantity.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnQuantity.setToolTipText("Quantity (F2)");
    btnQuantity.setActionCommand("btnModify");
    btnQuantity.setText("Quantity (F2)");
    btnQuantity.addActionListener(new PanelTransferItem_btnModify_actionAdapter(this));
    btnDeleteItem.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnDeleteItem.setToolTipText("Delete Item (F3)");
    btnDeleteItem.setActionCommand("btnDeleteItem");
    btnDeleteItem.setText("Delete Item (F3)");
    btnDeleteItem.addActionListener(new
        PanelTransferItem_btnDeleteItem_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnCancel.setToolTipText("Back (F4)");
    btnCancel.setActionCommand("btnCancel");
    btnCancel.setText("<html><center><b>Back </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</h" +
    "tml>");
    btnCancel.addActionListener(new PanelTransferItem_btnCancel_actionAdapter(this));
    jPanel8.setBackground(SystemColor.control);
    jPanel8.setPreferredSize(new Dimension(130, 100));
    jPanel9.setBackground(SystemColor.control);
    jPanel9.setPreferredSize(new Dimension(170, 100));
    jPanel10.setBackground(SystemColor.control);
    jPanel10.setMinimumSize(new Dimension(38, 31));
    jPanel10.setPreferredSize(new Dimension(130, 100));
    jPanel11.setBackground(SystemColor.control);
    jPanel11.setPreferredSize(new Dimension(170, 100));
    table.addKeyListener(new PanelTransferItem_table_keyAdapter(this));
    cboDesStoreID.setFont(new java.awt.Font("Dialog", 1, 11));
    cboDesStoreID.setPreferredSize(new Dimension(150, 20));
    this.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jPanel1, BorderLayout.WEST);
    jPanel1.add(jPanel8, BorderLayout.WEST);
    jPanel8.add(jLabel6, null);
    jPanel8.add(jLabel5, null);
    jPanel8.add(jLabel4, null);
    jPanel1.add(jPanel9, BorderLayout.CENTER);
    jPanel9.add(txtTransferID, null);
    jPanel9.add(txtSrcStoreID, null);
    jPanel9.add(cboDesStoreID, null);
    jPanel2.add(jPanel5, BorderLayout.EAST);
    jPanel5.add(jPanel10, BorderLayout.WEST);
    jPanel10.add(jLabel3, null);
    jPanel10.add(jLabel2, null);
    jPanel10.add(jLabel1, null);
    jPanel5.add(jPanel11, BorderLayout.CENTER);
    jPanel11.add(txtTransferDate, null);
    jPanel11.add(txtCreaterName, null);
    jPanel11.add(txtReceiverName, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(table, null);
    dm.addColumn("Item ID");
    dm.addColumn("Art No");
    dm.addColumn("Item Name");
    dm.addColumn("Size");
    dm.addColumn("Quantity");
//    table.getColumn("Item Description").setPreferredWidth(300);
//    table.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 13));
//    table.getTableHeader().setReorderingAllowed(false);
//    table.setRowHeight(30);
//    table.setFont(new java.awt.Font("Dialong", 1, 13));
    getComboData();
  }
  //get Size
  String getSize(String sItemID){
    String strTemp="";
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select ATTR2_NAME from BTM_POS_ITEM_PRICE where ITEM_ID = '"+sItemID+"'";
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      if (rs.next()){
        strTemp = rs.getString("ATTR2_NAME");
      }
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return strTemp;
  }

  //get Atr No
  String getArtNo(String sItemID){
    String strTemp="";
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select ART_NO from BTM_POS_ITEM_PRICE where ITEM_ID = '"+sItemID+"'";
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      if (rs.next()){
        strTemp = rs.getString("ART_NO");
      }
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return strTemp;
  }

  //get Data
  void getComboData(){
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select store_id, name from btm_pos_store ";
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      cboDesStoreID.setData1(rs);
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  //set Transfer ID
  void setTransferID() {
    String today = ut.getSystemDateTime();
//    today = today.substring(2, 4) + today.substring(5, 7) +
//        today.substring(8, 10)
//        + today.substring(11, 13) + today.substring(14, 16) +
//        today.substring(17);
    today = today.substring(0,2) + today.substring(3, 5) + today.substring(8,10)
            +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);

    Transaction trans;
    trans = frmMain.getTransaction();
    this.transferID = ut.doubleToString(trans.getStore_ID()) + today;
  }

  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {

    String mainInput = frmMain.getInputText();
    Item item = new Item();
    ResultSet rs = null;
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (flagButton == Constant.TI_TRANSFER_ITEM) {
        if (mainInput.equalsIgnoreCase("s")) {
          flagButton = Constant.TI_SEARCH_ITEM;
          frmMain.setInputLabel(" Enter key words from the item description.");
          frmMain.setInputText("");
          return;
        }
        if (mainInput.equals("")) {
          ut.showMessage(frmMain, "Warning",
                         "You must insert or scan item code before hit Enter");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        if (!checkItem(mainInput)) {
          ut.showMessage(frmMain, "Warning",
                         "Can not find this item, please check it again");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        if (!checkItemTable(mainInput)) {
          dm.addRow(new Object[] {mainInput, getArtNo(mainInput),itemDesc,getSize(mainInput), new Long(1)});
        }
        frmMain.setInputText("");
        table.setRowSelectionInterval(0, COL_ITEM_ID);
        if (txtCreaterName.isEditable() && !txtCreaterName.getText().equalsIgnoreCase("")
            && !txtReceiverName.getText().equalsIgnoreCase("") && cboDesStoreID.getSelectedIndex() != 0){
          if (table.getRowCount() > 0) {
            txtCreaterName.setEditable(false);
            txtReceiverName.setEditable(false);
            cboDesStoreID.setEnabled(false);
          }
        }
        return;
      }
      if (flagButton == Constant.TI_MODIFY_QUANTITY) {
        if (mainInput.equals("")) {
          ut.showMessage(frmMain, "Warning", "You must insert the quantity");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        if (!ut.isLongIntString(mainInput)) {
          ut.showMessage(frmMain, "Warning", "Wrong number");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        if (Long.parseLong(mainInput) < 1) {
          ut.showMessage(frmMain, "Warning", "Quantity can not be less than 1");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        if (table.getSelectedRow() == -1) {
          ut.showMessage(frmMain, "Warning",
                         "You must choose the item to modify quantity");
          return;
        }
        if (!checkItem(table.getValueAt(table.getSelectedRow(),COL_ITEM_ID).toString())) {
          ut.showMessage(frmMain, "Warning",
                         "Can not find this item, please check it again");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        if (Long.parseLong(mainInput) > this.stockOnHand){
          ut.showMessage(frmMain, "Warning","You have only " + this.stockOnHand + " items");
          return;
        }
        table.setValueAt(mainInput, table.getSelectedRow(), COL_QUANTITY);
        frmMain.setInputText("");
        flagButton = Constant.TI_TRANSFER_ITEM;
        frmMain.setInputLabel(
            "Insert or scan Item to transfer, Press s to search");
        table.requestFocus(true);
      }
      if (flagButton == Constant.TI_SEARCH_ITEM) {
        if (mainInput.length() < 3) {
          ut.showMessage(frmMain, "Warning",
                         "The systems required at least three lettes to search");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        DialogSearchResultItem dlgSearchResultItem = new DialogSearchResultItem(
            frmMain,
            "", true);
        //add seach result item
        String sqlQuery =
            "Select ip.* from BTM_POS_ITEM_PRICE ip, btm_pos_item_loc_soh isl where ip.item_id = isl.item_id and UPPER(ip.ITEM_DESC) LIKE UPPER('%" +
            mainInput + "%')";
//        System.out.println(sqlQuery);
        try {
          stmt = DAL.getConnection().createStatement();
          rs = DAL.executeQueries(sqlQuery,stmt);
          while (rs.next()) {
            item = new Item(rs.getString("ITEM_ID"), rs.getString("ITEM_DESC"),
                            rs.getDouble("UNIT_PRICE"), 1, 0, "", 0,
                            rs.getDouble("UNIT_PRICE"),0);
            dlgSearchResultItem.addItemRow(new Object[] {item.getId(),
                                           item.getDescription(),
                                           String.valueOf(item.getunitPrice())});
          }
          stmt.close();
        }
        catch (Exception ex) {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(ex);
        }
        if (dlgSearchResultItem.isExistItem()) {
          dlgSearchResultItem.setVisible(true);
          if (dlgSearchResultItem.isOKButton()) {
            item = dlgSearchResultItem.getItem();
            if (!checkItem(item.getId())){
              ut.showMessage(frmMain, "Warning",
                             "Can not find this item, please check it again");
            }else if (!checkItemTable(item.getId())) {
              dm.addRow(new Object[] {item.getId(),getArtNo(item.getId()), item.getDescription(),getSize(item.getId()),
                        new Long(1)});
            }
          }
        }
        else {
          ut.showMessage(frmMain,"Warning",
              "No items found matching your description");
          dlgSearchResultItem.dispose();
        }
        flagButton = Constant.TI_TRANSFER_ITEM;
        frmMain.setInputLabel(
            "Insert or scan Item to transfer, Press s to search");
        frmMain.setInputText("");

      }
    }
    table.setColor(Color.lightGray,Color.white);

  }

  //Check item
  boolean checkItem(String itemID) {
    ResultSet rs = null;
    String sql =
        "select ip.item_id, item_desc, stock_on_hand from btm_pos_item_price ip, btm_pos_item_loc_soh ils " +
        " where ip.item_id = ils.item_id and ip.item_id='" +
        itemID + "'";
//    System.out.println(sql);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()) {
        itemDesc = rs.getString("item_desc");
        stockOnHand = rs.getLong("stock_on_hand");
        stmt.close();
        return true;
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return false;
  }

  //check item in table
  boolean checkItemTable(String itemID) {
    for (int i = 0; i < dm.getRowCount(); i++) {
      String item = "" + table.getValueAt(i, COL_ITEM_ID);
      if (itemID.equalsIgnoreCase(item)) {
        long quantity = Long.parseLong("" + table.getValueAt(i, COL_QUANTITY)) + 1;
        if (quantity > this.stockOnHand){
          ut.showMessage(frmMain,"Warning!","You have only " + this.stockOnHand + " items");
        }else{
          table.setValueAt(new Long(quantity), i, COL_QUANTITY);
        }
        return true;
      }
    }
    return false;
  }

  void btnQuantity_actionPerformed(ActionEvent e) {
    String mainInput = frmMain.getInputText();
    if (flagButton != Constant.TI_MODIFY_QUANTITY) {
      flagButton = Constant.TI_MODIFY_QUANTITY;
      frmMain.setInputLabel(
          "Insert the quantity to modify quantity of the specific item");
      frmMain.setInputText("");
      frmMain.txtMainInput.requestFocus(true);
      return;
    }
    if (flagButton == Constant.TI_MODIFY_QUANTITY) {
      if (mainInput.equals("")) {
        ut.showMessage(frmMain, "Warning", "You must insert the quantity");
        frmMain.txtMainInput.requestFocus(true);
        return;
      }
      if (!ut.isLongIntString(mainInput)) {
        ut.showMessage(frmMain, "Warning", "Wrong number");
        frmMain.txtMainInput.requestFocus(true);
        return;
      }
      if (Long.parseLong(mainInput) < 1) {
        ut.showMessage(frmMain, "Warning", "Quantity can not be less than 1");
        frmMain.txtMainInput.requestFocus(true);
        return;
      }
      if (table.getSelectedRow() == -1) {
        ut.showMessage(frmMain, "Warning",
                       "You must choose the item to modify quantity");
        return;
      }
      table.setValueAt(mainInput, table.getSelectedRow(), COL_QUANTITY);
      frmMain.setInputText("");
      flagButton = Constant.TI_TRANSFER_ITEM;
      frmMain.setInputLabel(
          "Insert or scan Item to transfer, Press s to search");
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    txtCreaterName.setText("");
    cboDesStoreID.setSelectedIndex(0);
    txtReceiverName.setText("");
    txtCreaterName.setEditable(true);
    cboDesStoreID.setEnabled(true);
    txtReceiverName.setEditable(true);
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    flagButton = Constant.TI_TRANSFER_ITEM;
    frmMain.setTitle("JPOS - TRANSFER ITEM");
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showTransferButton();
  }

  void btnDone_actionPerformed(ActionEvent e) {
    Vector vSql = new Vector();
    if (txtCreaterName.getText().equals("")) {
      ut.showMessage(frmMain, "Warning", "Creater Name is not null");
      txtCreaterName.requestFocus(true);
      return;
    }
    if (txtReceiverName.getText().equals("")) {
      ut.showMessage(frmMain, "Warning", "Receiver Name is not null");
      txtReceiverName.requestFocus(true);
      return;
    }
    for (int i = 0; i < dm.getRowCount(); i++) {
      vSql.addElement("Insert into BTM_POS_TRANSFER_ITEM values('" +
                      txtTransferID.getText() +
                      "'," + Long.parseLong(DAL.getStoreID()) +
                      "," + Long.parseLong(cboDesStoreID.getSelectedData()) +
                      ",to_date('" + txtTransferDate.getText() +
                      "','DD-MM-YYYY'),'" +
                      txtCreaterName.getText() + "','" +
                      txtReceiverName.getText() +
                      "','" +  table.getValueAt(i, COL_ITEM_ID).toString() +
                      "'," + Long.parseLong("" + table.getValueAt(i, COL_QUANTITY).toString()) +
                      ",0," +
                      "to_date('7777-7-7','YYYY-MM-DD'))");
    }
//    System.out.println("vsql:"+vSql);
    if (!vSql.isEmpty()) {
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        DAL.executeBatchQuery(vSql,stmt);
        stmt.close();
      }
      catch(Exception ex){};
    }
   /////////////// Tuan Anh Dang
   PanelPrintTransfer pptransfer = new PanelPrintTransfer(frmMain);
    if (e.getSource() instanceof JButton) {
      PageFormat pf = new PageFormat();
      Paper paper = new Paper();
      paper.setImageableArea(0, 0, 250, 841);
      pf.setPaper(paper);
      PrinterJob printJob = PrinterJob.getPrinterJob();
      printJob.setPrintable(pptransfer,pf);

   if (printJob.printDialog()) {
       try {
           printJob.print();
       } catch (Exception ex) {
           ex.printStackTrace();
       }
   }
   }
   //////////////////end print DNTA

   txtCreaterName.setText("");
   cboDesStoreID.setSelectedIndex(0);
   txtReceiverName.setText("");
   txtCreaterName.setEditable(true);
   cboDesStoreID.setEnabled(true);
   txtReceiverName.setEditable(true);
   while (dm.getRowCount() > 0) {
     dm.removeRow(0);
   }
   flagButton = Constant.TI_TRANSFER_ITEM;
   frmMain.setTitle("JPOS - TRANSFER ITEM");
   frmMain.showScreen(Constant.SCR_MAIN);
   frmMain.removeButton();
   frmMain.pnlMain.showTransferButton();
  }

  //void show button
  void showButton() {
    frmMain.showButton(btnDone);
    frmMain.showButton(btnQuantity);
    frmMain.showButton(btnDeleteItem);
    frmMain.showButton(btnCancel);
  }

  void btnDeleteItem_actionPerformed(ActionEvent e) {
    if (table.getSelectedRow() != -1) {
      if (table.getRowCount() == 1) {
        dm.removeRow(0);
        return;
      }
      int[] i = table.getSelectedRows();
      for (int j = 0; j < i.length; j++) {
        dm.removeRow(i[0]);
      }
    }
  }

  //set text
  void setText() {
    setTransferID();
    txtTransferID.setText(this.transferID);
    txtTransferID.setEditable(false);
    txtTransferDate.setText(ut.getSystemDate(1));
    txtTransferDate.setEditable(false);
    txtSrcStoreID.setText("" + frmMain.getStoreName(DAL));
    txtSrcStoreID.setEditable(false);
  }

  void txtSrcStoreID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtSrcStoreID, 6);
  }

  void txtCreaterName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtCreaterName, 50);
  }

  void txtReceiverName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtReceiverName, 50);
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
    // ESCAPE
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

      //  if(flag == true){//4 Button
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }
     else if (identifier.intValue() == KeyEvent.VK_F2) {
        btnQuantity.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {
        btnDeleteItem.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F4 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void txtReceiverName_focusLost(FocusEvent e) {
    frmMain.txtMainInput.requestFocus(true);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
  private void catchHotKey(KeyEvent e) {
               if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnQuantity.doClick();
               }
             }
 }

class PanelTransferItem_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferItem adaptee;

  PanelTransferItem_btnModify_actionAdapter(PanelTransferItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnQuantity_actionPerformed(e);
  }
}

class PanelTransferItem_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferItem adaptee;

  PanelTransferItem_btnCancel_actionAdapter(PanelTransferItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelTransferItem_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferItem adaptee;

  PanelTransferItem_btnDone_actionAdapter(PanelTransferItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelTransferItem_btnDeleteItem_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferItem adaptee;

  PanelTransferItem_btnDeleteItem_actionAdapter(PanelTransferItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDeleteItem_actionPerformed(e);
  }
}

class PanelTransferItem_txtSrcStoreID_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferItem adaptee;

  PanelTransferItem_txtSrcStoreID_keyAdapter(PanelTransferItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSrcStoreID_keyTyped(e);
  }
}

class PanelTransferItem_txtCreaterName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferItem adaptee;

  PanelTransferItem_txtCreaterName_keyAdapter(PanelTransferItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCreaterName_keyTyped(e);
  }
}

class PanelTransferItem_txtReceiverName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferItem adaptee;

  PanelTransferItem_txtReceiverName_keyAdapter(PanelTransferItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtReceiverName_keyTyped(e);
  }
}

class PanelTransferItem_txtReceiverName_focusAdapter extends java.awt.event.FocusAdapter {
  PanelTransferItem adaptee;

  PanelTransferItem_txtReceiverName_focusAdapter(PanelTransferItem adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtReceiverName_focusLost(e);
  }
}

class PanelTransferItem_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferItem adaptee;

  PanelTransferItem_table_keyAdapter(PanelTransferItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}



