//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//






















































package pos;

import java.awt.*;
import java.awt.event.*;
import common.*;
import javax.swing.*;
import btm.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//not use now
public class PanelViewReceiptItem extends JPanel {
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
        case 2: return Long.class;
        case 3: return Long.class;
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
  BJButton jButton2 = new BJButton();
  BJButton btnBack = new BJButton();
  JPanel pnlNorth = new JPanel();
  JPanel pnlCenter = new JPanel();
  JLabel jLabel1 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  BJTable jTable1;// = new JTable();
  BorderLayout borderLayout2 = new BorderLayout();

  public PanelViewReceiptItem(FrameMain frmMain) {
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
//        System.out.println("N24");
    this.setLayout(new FlowLayout());

    dm.addColumn("Receipt ID");
    dm.addColumn("Art No");
    dm.addColumn("Size");
    dm.addColumn("Quantity");
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
    btnBack.setText("Back(F2)");
    btnBack.addActionListener(new PanelViewReceiptItem_btnBack_actionAdapter(this));
    jButton2.setFont(new java.awt.Font("SansSerif", 0, 12));
    jButton2.setText("jButton2");
    btnNewDate.setText("New date");
    this.setLayout(borderLayout1);
    jLabel1.setFont(new java.awt.Font("SansSerif", 1, 13));
    jLabel1.setText("Inventory Receipt ");
    pnlCenter.setLayout(borderLayout2);
    pnlNorth.setBackground(new Color(121, 152, 198));
    pnlNorth.setFont(new java.awt.Font("Dialog", 1, 12));
    pnlNorth.setPreferredSize(new Dimension(650, 28));
    this.setPreferredSize(new Dimension(650, 432));
    this.setRequestFocusEnabled(true);
    pnlCenter.setPreferredSize(new Dimension(650, 404));
    jScrollPane1.setPreferredSize(new Dimension(650, 395));
    btnNewDate.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnNewDate.setText("New Date(F1)");
    btnOK.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnCancel.setFont(new java.awt.Font("SansSerif", 0, 12));
    this.add(pnlNorth,  BorderLayout.NORTH);
    this.add(pnlCenter, BorderLayout.CENTER);
    pnlCenter.add(jScrollPane1, BorderLayout.EAST);
    jScrollPane1.getViewport().add(jTable1, null);
    pnlNorth.add(jLabel1, null);
    jTable1.setColumnWidth(0,50);
    jTable1.setColumnWidth(1,250);
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
      jTable1.setColumnWidth(0,50);
      jTable1.setColumnWidth(1,250);
      jTable1.setHeaderName("Receipt ID",0);
      jTable1.setHeaderName("Art No",1);
      jTable1.setHeaderName("Size",2);
      jTable1.setHeaderName("Quantity",3);
      //ut.changeDataTypetoLong(3,dm); //Yen.Dinh 19-06-2006
      ut.changeDataTypetoLong(0,dm);

      if (dm.getRowCount()>0){
        jLabel1.setText("Inventory Receipt Date: " + ut.getSystemDate(1));
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  private ResultSet getData(String date, Statement stmt){
    ResultSet rs = null;
    try{
//       String sql= "select BTM_POS_RECEIPT_ITEM.item_id,BTM_POS_RECEIPT_ITEM.unit_price, total_qty,BTM_POS_ITEM_PRICE.item_desc from BTM_POS_RECEIPT_ITEM,BTM_POS_ITEM_PRICE where BTM_POS_RECEIPT_ITEM.item_id =BTM_POS_ITEM_PRICE.item_id and  BTM_POS_RECEIPT_ITEM.receipt_date= to_date('" + date + "', 'DD/MM/YYYY')";
      String sql="select a.receipt_id,b.art_no,b.attr2_name, total_qty" +
          " from BTM_POS_RECEIPT_ITEM a,BTM_POS_ITEM_PRICE b " +
          " where a.item_id =b.item_id and " +
          " a.receipt_date= to_date('" + date + "', 'DD/MM/YYYY') " +
          " order by a.receipt_id";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  void resetData(){
    frmMain.txtMainInput.requestFocus();
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
    jLabel1.setText("Inventory Receipt ");
  }
  void showButton(){
    frmMain.showButton(btnBack);
  }

  void btnBack_actionPerformed(ActionEvent e) {
    removeRow();
    frmMain.setTitle("JPOS - RECEIPT ITEM");
    frmMain.showScreen(Constant.SCR_RECEIPT_ITEM);
    frmMain.pnlReceiptItem.initScreen();

  }
  void removeRow(){
      while (jTable1.getRowCount()>0){
        dm.removeRow(0);
      }
  }

}

class PanelViewReceiptItem_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelViewReceiptItem adaptee;

  PanelViewReceiptItem_btnBack_actionAdapter(PanelViewReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}
