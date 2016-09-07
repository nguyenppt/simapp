//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//








































package pos;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
import common.*;
import btm.swing.*;
import btm.swing.BJTable.*;
import btm.attr.*;
import java.awt.event.*;
import java.util.Vector;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: BTM International Software 2005</p>
 * <p>Company: </p>
 * @author Loan.vo
 * @version 1.0
 */

public class PanelViewTransferTransaction extends JPanel {
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_DESC = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_QUANTITY = 4; //

  private static final int VIEWTRANSFER_ID = 232;
  private static final int SEARCHTRANSFER_DATE = 233;
  DataAccessLayer DAL;
  FrameMain frmMain;
  Statement stmt = null;
  int flagButton = VIEWTRANSFER_ID;
  int flagReturn = Constant.TI_VIEW_TRANSFER_TRANS;
  Utils ut = new Utils();

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
   JTextField txtDesStoreID = new JTextField();
   JTextField txtSrcStoreID = new JTextField();
   JTextField txtTransferID = new JTextField();
   JScrollPane jScrollPane1 = new JScrollPane();
   BJButton btnBack = new BJButton();
   BJButton btnDeleteItem = new BJButton();
   SortableTableModel dm = new SortableTableModel(){
     public Class getColumnClass(int col){
       if (col == COL_QUANTITY){
         return Long.class;
       }
       return Object.class;
     }
   };
   BJTable table = new BJTable(dm,true){
     public boolean isCellEditable(int row,int col){
       return false;
     }
     public void tableChanged(TableModelEvent e) {
       super.tableChanged(e);
       repaint();
     }
     public Class getColumnClass(int col){
       if (col == 2){
         return Long.class;
       }
       return Object.class;
     }
   };
  String transferID ;
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  String itemDesc = "";
  String oldTrans_id = "";
  GridLayout gridLayout1 = new GridLayout();

  public PanelViewTransferTransaction(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
       DAL = frmMain.getDAL();
       //setText();

      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
//        System.out.println("N26");
   registerKeyboardActions();
    this.setLayout(borderLayout1);
   jPanel2.setDebugGraphicsOptions(0);
   jPanel2.setPreferredSize(new Dimension(600, 100));
   jPanel2.setLayout(borderLayout2);
   this.setPreferredSize(new Dimension(600, 800));
   this.setRequestFocusEnabled(true);
    this.addMouseListener(new PanelViewTransferTransaction_this_mouseAdapter(this));
   jPanel3.setDebugGraphicsOptions(0);
    jPanel3.setPreferredSize(new Dimension(600, 400));
    jPanel3.setLayout(gridLayout1);
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
   txtReceiverName.setBackground(SystemColor.control);
   txtReceiverName.setText("");
   txtReceiverName.setEditable(false);
   txtCreaterName.setFont(new java.awt.Font("Dialog", 1, 11));
   txtCreaterName.setPreferredSize(new Dimension(150, 20));
   txtCreaterName.setBackground(SystemColor.control);
   txtCreaterName.setText("");
   txtCreaterName.setEditable(false);
   txtTransferDate.setFont(new java.awt.Font("Dialog", 1, 11));
   txtTransferDate.setPreferredSize(new Dimension(150, 20));
   txtTransferDate.setBackground(SystemColor.control);
   txtTransferDate.setText("");
   txtTransferDate.setEditable(false);
   txtDesStoreID.setBackground(SystemColor.control);
    txtDesStoreID.setFont(new java.awt.Font("Dialog", 1, 11));
   txtDesStoreID.setPreferredSize(new Dimension(150, 20));
   txtDesStoreID.setText("");
   txtDesStoreID.setEditable(false);
   txtSrcStoreID.setBackground(SystemColor.control);
    txtSrcStoreID.setFont(new java.awt.Font("Dialog", 1, 11));
   txtSrcStoreID.setPreferredSize(new Dimension(150, 20));
   txtSrcStoreID.setText("");
   txtSrcStoreID.setEditable(false);
   txtTransferID.setBackground(SystemColor.control);
    txtTransferID.setFont(new java.awt.Font("Dialog", 1, 11));
   txtTransferID.setPreferredSize(new Dimension(150, 20));
   txtTransferID.setText("");
   txtTransferID.setEditable(false);
   jScrollPane1.setPreferredSize(new Dimension(600, 395));
   btnBack.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnBack.setToolTipText("Back (F2 or Esc)");
    btnBack.setActionCommand("btnBack");
   btnBack.setText("<html><center><b>Back </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</h" +
    "tml>");
   btnBack.addActionListener(new PanelViewTransferTransaction_btnBack_actionAdapter(this));
   btnDeleteItem.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnDeleteItem.setToolTipText("Delete Item(F1)");
    btnDeleteItem.setActionCommand("btnDeleteItem");
   btnDeleteItem.setText("<html><center><b>Delete Item</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";F1</html>");
   btnDeleteItem.addActionListener(new PanelViewTransferTransaction_btnDeleteItem_actionAdapter(this));
   jPanel8.setPreferredSize(new Dimension(130, 100));
   jPanel9.setPreferredSize(new Dimension(170, 100));
   jPanel10.setMinimumSize(new Dimension(38, 31));
   jPanel10.setPreferredSize(new Dimension(130, 100));
   jPanel11.setPreferredSize(new Dimension(170, 100));
   table.addMouseListener(new PanelViewTransferTransaction_table_mouseAdapter(this));
    table.addKeyListener(new PanelViewTransferTransaction_table_keyAdapter(this));
    this.add(jPanel2,  BorderLayout.CENTER);
   jPanel2.add(jPanel1,  BorderLayout.WEST);
   jPanel1.add(jPanel8,  BorderLayout.WEST);
   jPanel8.add(jLabel6, null);
   jPanel8.add(jLabel5, null);
   jPanel8.add(jLabel4, null);
   jPanel1.add(jPanel9,  BorderLayout.CENTER);
   jPanel9.add(txtTransferID, null);
   jPanel9.add(txtSrcStoreID, null);
   jPanel9.add(txtDesStoreID, null);
   jPanel2.add(jPanel5,  BorderLayout.EAST);
   jPanel5.add(jPanel10,  BorderLayout.WEST);
   jPanel10.add(jLabel3, null);
   jPanel10.add(jLabel2, null);
   jPanel10.add(jLabel1, null);
   jPanel5.add(jPanel11,  BorderLayout.CENTER);
   jPanel11.add(txtTransferDate, null);
   jPanel11.add(txtCreaterName, null);
   jPanel11.add(txtReceiverName, null);
   this.add(jPanel3,  BorderLayout.SOUTH);
   jPanel3.add(jScrollPane1, null);
   jScrollPane1.getViewport().add(table, null);
   dm.addColumn("Item ID");
   dm.addColumn("Art No");
   dm.addColumn("Item Name");
   dm.addColumn("Size");
   dm.addColumn("Quantity");
   table.getColumn("Item Name").setPreferredWidth(300);
   table.getTableHeader().setFont(new java.awt.Font("Dialog",1,13));
   table.getTableHeader().setReorderingAllowed(false);
   table.setRowHeight(30);
   table.setFont(new java.awt.Font("Dialong",1,13));

  }

  void btnBack_actionPerformed(ActionEvent e) {
    removeRow();
    frmMain.setTitle("JPOS - TRANSFER ITEM");
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showTransferButton();
  }
  //show button in Frame Main
  void showButton(){
    frmMain.showButton(btnDeleteItem);
    frmMain.showButton(btnBack);
 }

//remove row
  void removeRow(){
      while (table.getRowCount()>0){
        dm.removeRow(0);
      }
  }

 //Get and show data out from database
 public void showData(String transfer_id){
//   String[] columnNames = new String[]{"Item ID","Item Description","Quantity"};
 int i=0;
 ResultSet rs = null;
 try{
   String sql = "select transfer_id,src_store_id,des_store_id,transfer_date,creater_name,receiver_name" +
         " from BTM_POS_TRANSFER_ITEM " +
         " where transfer_id ='" + transfer_id +
         "' group by transfer_id,src_store_id,des_store_id,transfer_date,creater_name,receiver_name";
//     System.out.println(sql);
     stmt = DAL.getConnection().createStatement();
     rs = DAL.executeQueries(sql,stmt);
     if (rs.next()){
       txtTransferID.setText("" + rs.getLong("transfer_id"));
       txtDesStoreID.setText("" + rs.getLong("des_store_id"));
       txtReceiverName.setText(rs.getString("receiver_name"));
       txtSrcStoreID.setText("" + rs.getLong("src_store_id"));
       txtCreaterName.setText(rs.getString("creater_name"));
       txtTransferDate.setText("" + rs.getDate("transfer_date"));
          sql = " select d.item_id,i.art_no, i.item_desc,i.ATTR2_NAME, d.qty" +
        " from BTM_POS_TRANSFER_ITEM d , BTM_POS_ITEM_PRICE i " +
         " where d.item_id = i.item_id and d.transfer_id = '" + transfer_id + "'";
//     System.out.println(sql);
     Statement stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     rs = DAL.executeScrollQueries(sql,stmt);

       table.setData(rs);
       stmt.close();
       table.setColumnWidth(COL_ITEM_ID,100);
       table.setColumnWidth(COL_ITEM_DESC,250);
       table.setHeaderName("Item ID",COL_ITEM_ID);
       table.setHeaderName("Art No",COL_ART_NO);
       table.setHeaderName("Item Name",COL_ITEM_DESC);
       table.setHeaderName("Size",COL_SIZE);
       table.setHeaderName("Quantity",COL_QUANTITY);
//       ut.changeDataTypetoLong(2,table);
//       sql = " select count(*) as num" +
//         " from BTM_POS_TRANSFER_ITEM d, BTM_POS_ITEM_PRICE i " +
//         " where d.item_id = i.item_id and d.transfer_id = '" + transfer_id + "'";
//     System.out.println(sql);
//    rs = DAL.executeQueries(sql);
//    if (rs.next()){
//      i = rs.getInt("num");
//    }
//    rs = null;
//   Object[][] data = new Object[i][3];
//   sql = " select d.item_id, i.item_desc, d.qty" +
//        " from BTM_POS_TRANSFER_ITEM d , BTM_POS_ITEM_PRICE i " +
//         " where d.item_id = i.item_id and d.transfer_id = '" + transfer_id + "'";
//     System.out.println(sql);
//   rs = DAL.executeQueries(sql);
//   i = 0;
//   while (rs.next()){
//     data[i][0] = rs.getString("item_id");
//     data[i][1] = rs.getString("item_desc");
//     data[i][2] = new Double(rs.getDouble("qty"));
//      i += 1;
//   }
//     dm.setDataVector(data,columnNames);
//    }
//    if (dm.getRowCount()>0){
//      table.setRowSelectionInterval(0,0);
    }
    stmt.close();

 }catch(Exception ex){
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(ex);
 }
 }
 boolean checkTransID(String transferID){
   ResultSet rs = null;
   try{
     String sql;
     sql = "Select transfer_id from BTM_POS_TRANSFER_ITEM where transfer_id = '" + transferID + "'";
//     System.out.println(sql);
     stmt = DAL.getConnection().createStatement();
     rs = DAL.executeQueries(sql,stmt);
     if (rs.next()){
       oldTrans_id = transferID;
       stmt.close();
     }else{
       stmt.close();
       return false;
     }
   }catch(Exception e){
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return true;
 }
 void txtMainInput_keyPressed(KeyEvent e) {
   String mainInput=frmMain.getInputText();
   if (e.getKeyCode() == KeyEvent.VK_ENTER) {
     if (mainInput.equalsIgnoreCase("s")){
       flagButton = SEARCHTRANSFER_DATE;
       frmMain.setInputText("");
       frmMain.setInputLabel("Enter transfer_date (DD/MM/YYYY) for searching");
       frmMain.setButtonVisible();
       return;
     }

     else if (flagButton == VIEWTRANSFER_ID){
       if (!checkTransID(mainInput)) {
         ut.showMessage(frmMain,"Warning", "Transfer id doesn't exist ! Please put it again");
         frmMain.txtMainInput.requestFocus();
         frmMain.txtMainInput.setSelectionStart(0);
         frmMain.txtMainInput.setSelectionEnd(frmMain.txtMainInput.getText().length());
         return;
       }
       else if (!mainInput.equals("")) {
         showData(mainInput);
         frmMain.txtMainInput.requestFocus();
         frmMain.txtMainInput.setSelectionStart(0);
         frmMain.txtMainInput.setSelectionEnd(frmMain.txtMainInput.getText().length());
         }
     }
     else if (flagButton == SEARCHTRANSFER_DATE){
        if (!ut.checkDate(mainInput, "/")){
          ut.showMessage(frmMain,"Warning", "Input must be a date type");
         frmMain.txtMainInput.requestFocus();
         frmMain.txtMainInput.setSelectionStart(0);
         frmMain.txtMainInput.setSelectionEnd(frmMain.txtMainInput.getText().length());

          return;
        }
//        DialogSearchTransfer_Id search = new DialogSearchTransfer_Id(frmMain,"JPOS - TRANSFER LOOKUP",true);
//        search.setData(mainInput);
//        if (search.done){
//          String transfer_id = search.getData();
//           showData(transfer_id);
//       }
       flagButton = VIEWTRANSFER_ID;
       frmMain.setButtonInVisible();
       frmMain.setInputLabel("Insert or scan Item to transfer, Press s to search");
       frmMain.setInputText("");

      }

   }//enter
 }
 //show search
 void showSearch(){
   DialogSearchTransfer_Id search = new DialogSearchTransfer_Id(frmMain,"JPOS - TRANSFER LOOKUP",true);
   search.setData(frmMain.txtMainInput.getText());
   if (search.done){
     String transfer_id = search.getData();
      dm.resetIndexes();
      showData(transfer_id);
      ut.changeDataTypetoLong(COL_QUANTITY,dm);
      flagButton = VIEWTRANSFER_ID;
      frmMain.setButtonInVisible();
      frmMain.setInputLabel("Insert or scan Item to transfer, Press s to search");
      frmMain.setInputText("");

  }
 }
 boolean isExistItem(){
//   System.out.println(table.getSelectedRow());
   if(table.getRowCount()>0){
     return true;
   }else{
     return false;
   }
 }
  void btnDeleteItem_actionPerformed(ActionEvent e) {
    String transferID = txtTransferID.getText();
     ResultSet rs = null;
      String sql;
    if(isExistItem()){
      sql = "delete from BTM_POS_TRANSFER_ITEM where transfer_id = '" + transferID + "' and item_id ='" +table.getValueAt(table.getSelectedRow(),COL_ITEM_ID) + "'";
        if (table.getSelectedRow() != -1){
           int k= ut.showMessage(frmMain,"Warning", "Do you want to delete item ?",DialogMessage.QUESTION_MESSAGE,DialogMessage.YES_NO_OPTION);
         if (table.getRowCount() == 1 && k == DialogMessage.YES_VALUE) {
           dm.removeRow(0);
//           System.out.println(sql);
           try{
             stmt = DAL.getConnection().createStatement();
             DAL.executeUpdate(sql, stmt);
             stmt.close();
           }catch(Exception ex){};
           return;
         }
         int[] i = table.getSelectedRows();
         if (k == 0) {
           for (int j = 0; j < i.length; j++) {
//             sql = "delete from BTM_POS_TRANSFER_ITEM where transfer_id = '" +
//                 transferID + "' and item_id ='" + table.getValueAt(0, 0) + "'";
             sql = "delete from BTM_POS_TRANSFER_ITEM where transfer_id = '" + transferID + "' and item_id ='" +table.getValueAt(table.getSelectedRow(),COL_ITEM_ID) + "'";
             dm.removeRow(i[0]);
//             System.out.println(sql);
             try{
               stmt = DAL.getConnection().createStatement();
               DAL.executeUpdate(sql,stmt);
               stmt.close();
             }catch(Exception ex){};
           }
         }
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
   // ESCAPE
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

   //  if(flag == true){//4 nut
       if (identifier.intValue() == KeyEvent.VK_F1 ) {
         btnDeleteItem.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F2 || identifier.intValue()==KeyEvent.VK_ESCAPE) {
         btnBack.doClick();
       }
      }
   }


    void table_mousePressed(MouseEvent e) {
    }

  void this_mousePressed(MouseEvent e) {

  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                btnBack.doClick();
              }
  }
  }

class PanelViewTransferTransaction_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelViewTransferTransaction adaptee;

  PanelViewTransferTransaction_btnBack_actionAdapter(PanelViewTransferTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelViewTransferTransaction_btnDeleteItem_actionAdapter implements java.awt.event.ActionListener {
  PanelViewTransferTransaction adaptee;

  PanelViewTransferTransaction_btnDeleteItem_actionAdapter(PanelViewTransferTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDeleteItem_actionPerformed(e);
  }
}

class PanelViewTransferTransaction_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelViewTransferTransaction adaptee;

  PanelViewTransferTransaction_table_mouseAdapter(PanelViewTransferTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelViewTransferTransaction_this_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelViewTransferTransaction adaptee;

  PanelViewTransferTransaction_this_mouseAdapter(PanelViewTransferTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.this_mousePressed(e);
  }
}

class PanelViewTransferTransaction_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewTransferTransaction adaptee;

  PanelViewTransferTransaction_table_keyAdapter(PanelViewTransferTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}




