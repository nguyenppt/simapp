//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//





























package pos;

import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import btm.swing.*;
import common.*;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Anh
 * @version 1.0
 */

public class PanelInventoryReceipt extends JPanel {
    private static final int OK_BTN =223;

  FrameMain frmMain;
  int flagButton=OK_BTN;
  Utils ut=new Utils();
  DataAccessLayer DAL;
  SortableTableModel dm = new SortableTableModel(){
     public Class getColumnClass(int col){
       switch(col){
         case 0: return String.class;
         case 1: return String.class;
         case 2: return String.class;
         case 3: return String.class;
         case 4: return Long.class;
         case 5: return Long.class;
         default: return Object.class;
       }
     }
     public boolean isCellEditable(int row, int col){
       return false;
     }
   };
   int rowCount = 0;

   BJButton btnOK = new BJButton();
   BJButton btnCancel = new BJButton();

  BorderLayout borderLayout1 = new BorderLayout();
  BJButton btnNewDate = new BJButton();
  BJButton btnPrint = new BJButton();
  BJButton jButton2 = new BJButton();
  BJButton btnBack = new BJButton();
  JPanel pnlNorth = new JPanel();
  JPanel pnlCenter = new JPanel();
  JLabel jLabel1 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  BJTable jTable1;// = new JTable();
  BorderLayout borderLayout2 = new BorderLayout();

  public PanelInventoryReceipt(FrameMain frmMain) {
   DAL = frmMain.getDAL();
   try {
      this.frmMain=frmMain;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
//        System.out.println("N9");
    registerKeyboardActions();
    this.setLayout(new FlowLayout());

   dm.addColumn("Item ID");
   dm.addColumn("Art No");
   dm.addColumn("Art Name");
   dm.addColumn("Size");
   dm.addColumn("Item Price");
   dm.addColumn("Total Quantity");
   jTable1 = new BJTable(dm,true){
     public boolean isCellEditable(int row, int col){
       return false;
     }
   };
//   jTable1.setPreferredSize(new Dimension(650, 500));
//   jTable1.setRowHeight(30);
//   jTable1.setFont(new java.awt.Font("Dialog", 1, 15));
//   JTableHeader header = new JTableHeader();
//     header = jTable1.getTableHeader();
//     header.setFont(new java.awt.Font("Dialog", 1, 13));
//     jTable1.setTableHeader(header);
//     jTable1.addMouseListener(new PanelInventoryReceipt_jTable1_mouseAdapter(this));

//    TableColumn column = new TableColumn();
//    column = jTable1.getColumn("Date");
//    column.setMaxWidth(80);
//    column.setMinWidth(10);

    btnBack.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnBack.setText("Back(F3)");
    btnBack.addActionListener(new PanelInventoryReceipt_btnBack_actionAdapter(this));
    jButton2.setFont(new java.awt.Font("SansSerif", 0, 12));
    jButton2.setText("jButton2");
    btnNewDate.setText("New date");
    btnNewDate.addActionListener(new PanelInventoryReceipt_jButton1_actionAdapter(this));
    btnPrint.setText("Print");
//    btnPrint.addActionListener(new PanelInventoryReceipt_btnPrint_actionAdapter(this));
    this.setLayout(borderLayout1);
    jLabel1.setFont(new java.awt.Font("SansSerif", 1, 13));
    jLabel1.setText("Inventory Receipt ");
    pnlCenter.setLayout(borderLayout2);
    pnlNorth.setBackground(new Color(121, 152, 198));
    pnlNorth.setFont(new java.awt.Font("Dialog", 1, 12));
    pnlNorth.setPreferredSize(new Dimension(650, 28));
    btnOK.addActionListener(new PanelInventoryReceipt_btnOK_actionAdapter(this));
    this.setPreferredSize(new Dimension(650, 432));
    this.setRequestFocusEnabled(true);
    pnlCenter.setPreferredSize(new Dimension(650, 404));
    jScrollPane1.setPreferredSize(new Dimension(650, 395));
    btnNewDate.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnNewDate.setText("New Date (F1)");
    btnPrint.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnPrint.setText("Print (F2)");
    btnOK.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnCancel.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnCancel.addActionListener(new PanelInventoryReceipt_btnCancel_actionAdapter(this));
    jTable1.addKeyListener(new PanelInventoryReceipt_jTable1_keyAdapter(this));
    this.add(pnlNorth,  BorderLayout.NORTH);
    this.add(pnlCenter, BorderLayout.CENTER);
    pnlCenter.add(jScrollPane1, BorderLayout.EAST);
    jScrollPane1.getViewport().add(jTable1, null);
    pnlNorth.add(jLabel1, null);
//    jTable1.setColumnWidth(0,50);
//    jTable1.setColumnWidth(1,250);
  }
  private ResultSet getData(String date, Statement stmt){
     ResultSet rs = null;
     try{
//       String sql= "select BTM_POS_RECEIPT_ITEM.item_id,BTM_POS_RECEIPT_ITEM.unit_price, total_qty,BTM_POS_ITEM_PRICE.item_desc from BTM_POS_RECEIPT_ITEM,BTM_POS_ITEM_PRICE where BTM_POS_RECEIPT_ITEM.item_id =BTM_POS_ITEM_PRICE.item_id and  BTM_POS_RECEIPT_ITEM.receipt_date= to_date('" + date + "', 'DD/MM/YYYY')";
       //Nguyen Pham Begin
       String sql="select a.item_id,b.art_no,b.item_desc,b.attr2_name,a.unit_price, sum(total_qty) total_qty " +
           "from BTM_POS_RECEIPT_ITEM a,BTM_POS_ITEM_PRICE b " +
           " where a.item_id =b.item_id and " +
           " a.receipt_date= to_date('" + date + "', 'DD/MM/YYYY') " +
           " group by a.item_id,b.art_no,b.attr2_name,a.unit_price, b.item_desc";
       //Nguyen Pham End
//       System.out.println(sql);
       rs = DAL.executeScrollQueries(sql,stmt);
     }catch(Exception e){
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
     return rs;
   }
 //Process mainInput in FrameMain
//remove row
  void removeRow(){
      while (jTable1.getRowCount()>0){
        dm.removeRow(0);
      }
  }

   public void showData(String date){
      ResultSet rs = null;
      Statement stmt = null;
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs = getData(date,stmt);
        //reset dm
        while (dm.getRowCount()>0) {
          dm.removeRow(0);
        }
        dm.resetIndexes();
        jTable1.setData(rs);
//        jTable1.setColumnWidth(0,50);
//        jTable1.setColumnWidth(1,250);
        jTable1.setHeaderName("Item ID",0);
        jTable1.setHeaderName("Art No",1);
        jTable1.setHeaderName("Art Name",2);
        jTable1.setHeaderName("Size",3);
        jTable1.setHeaderName("Item Price",4);
        jTable1.setHeaderName("Total Quantity",5);
        ut.changeDataTypetoLong(4,dm);

        if (dm.getRowCount()>0){
          jLabel1.setText("Inventory Receipt Date: " + ut.getSystemDate(1));
        }
        rs.close();
        stmt.close();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
    void setTable(){
      JTableHeader header = new JTableHeader();
      header = jTable1.getTableHeader();
      header.setFont(new java.awt.Font("Dialog", 1, 13));
    }
    void jTable1_mousePressed(MouseEvent e) {
      if (e.getClickCount() == 2){
        String trans_id = jTable1.getValueAt(jTable1.getSelectedRow(),1).toString();
        frmMain.goToDetailTrans(trans_id);
        frmMain.setTitle("JPOS - DETAIL TRANSACTION");
        frmMain.showScreen(Constant.SCR_DETAIL_TRANS);
      }
    }

  //show button in Frame Main
  void showButton(){
    frmMain.showButton(btnNewDate);
//    frmMain.showButton(jButton2);
    frmMain.showButton(btnPrint);
    frmMain.showButton(btnBack);
  }
  void txtMainInput_keyPressed(KeyEvent e) {
     String mainInput=frmMain.getInputText();


     if (e.getKeyCode() == KeyEvent.VK_ENTER) {
           if (!mainInput.equals("")){
             if (ut.checkDate(mainInput,"/")){
               showData(mainInput);
               jLabel1.setText("Inventory Receipt date:\t"+mainInput);
             }else{
               ut.showMessage(frmMain,"Warning", "Input must be a date type");
               resetData();
               frmMain.txtMainInput.setSelectionStart(0);
               frmMain.txtMainInput.setSelectionEnd(mainInput.length());
             }
           }
           else{
             resetData();
           }

     }//enter

   }
   void resetData(){
     frmMain.txtMainInput.requestFocus();
         while(dm.getRowCount()>0){
           dm.removeRow(0);
         }
         jLabel1.setText("Inventory Receipt ");
  }


   void jButton1_actionPerformed(ActionEvent e) {
    frmMain.setInputText("");
    resetData();
   }

//Process mainInput in FrameMain


  void btnBack_actionPerformed(ActionEvent e) {
    removeRow();
    frmMain.setTitle("JPOS - RECEIPT ITEM");
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showReceiptButton();
  }

  void btnOK_actionPerformed(ActionEvent e) {
    frmMain.setInputText("");
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
    btnNewDate.doClick();
  }
  else if (identifier.intValue() == KeyEvent.VK_F2){
    btnPrint.doClick();
  }
  else if (identifier.intValue() == KeyEvent.VK_F3 ||
           identifier.intValue() == KeyEvent.VK_ESCAPE) {
    btnBack.doClick();
  }
}
}
  private void catchHotKey(KeyEvent e) {
              if (e.getKeyCode() == KeyEvent.VK_F2) {
                btnBack.doClick();
              }
   }

  void btnCancel_actionPerformed(ActionEvent e) {

  }

  void jTable1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

}
class PanelInventoryReceipt_jTable1_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelViewTrans adaptee;

  PanelInventoryReceipt_jTable1_mouseAdapter(PanelViewTrans adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.jTable1_mousePressed(e);
  }
}

class PanelInventoryReceipt_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelInventoryReceipt adaptee;

  PanelInventoryReceipt_btnBack_actionAdapter(PanelInventoryReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelInventoryReceipt_btnOK_actionAdapter implements java.awt.event.ActionListener {
  PanelInventoryReceipt adaptee;

  PanelInventoryReceipt_btnOK_actionAdapter(PanelInventoryReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class PanelInventoryReceipt_jButton1_actionAdapter implements java.awt.event.ActionListener {
  PanelInventoryReceipt adaptee;

  PanelInventoryReceipt_jButton1_actionAdapter(PanelInventoryReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class PanelInventoryReceipt_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelInventoryReceipt adaptee;

  PanelInventoryReceipt_btnCancel_actionAdapter(PanelInventoryReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelInventoryReceipt_jTable1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelInventoryReceipt adaptee;

  PanelInventoryReceipt_jTable1_keyAdapter(PanelInventoryReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTable1_keyPressed(e);
  }
}
