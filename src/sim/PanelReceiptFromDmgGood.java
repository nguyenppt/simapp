
package sim;

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
import java.awt.geom.*;
import java.awt.print.PrinterJob;
import java.awt.print.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Chunk;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.Element;
import com.lowagie.text.Document;
import java.io.FileOutputStream;
import com.lowagie.text.Paragraph;
import com.lowagie.text.FontFactory;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.PrintPDF;
import java.util.*;
import com.lowagie.text.PageSize;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Receive -> From Supplier</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelReceiptFromDmgGood extends JPanel {
  String sToStore="";
  String sFromStore="";
  int iToStore=0;
  int iFromStore=0;

  DataAccessLayer DAL;
  FrameMainSim frmMain;
  Vector vSql = new Vector();
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  Statement stmt = null;
  int flagButton = Constant.TI_TRANSFER_ITEM;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JTextField txtTransferDate = new JTextField();
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
      if (col == 4) {
        return Long.class;
      }
      return Object.class;
    }
  };
  BJButton btnDone = new BJButton();
  BJButton btnBack = new BJButton();
//  String transferID;
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  String itemDesc = "";
  String size = "";
  String artNo = "";
  long qty = 0;
  JPanel jPanel4 = new JPanel();
  JPanel jPanel6 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout1 = new GridLayout();
  BJComboBox cboTransferID = new BJComboBox();
  FlowLayout flowLayout2 = new FlowLayout();
  BJComboBox cboFromStoreID = new BJComboBox();
  BJComboBox cboToStoreID = new BJComboBox();
  JButton jButton1 = new JButton();

  public PanelReceiptFromDmgGood(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
//      setText();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    jPanel2.setBackground(SystemColor.control);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setPreferredSize(new Dimension(800, 100));
    jPanel2.setRequestFocusEnabled(true);
    jPanel2.setLayout(borderLayout2);
    this.setPreferredSize(new Dimension(800, 600));
    this.setRequestFocusEnabled(true);
    jPanel3.setBackground(SystemColor.control);
    jPanel3.setPreferredSize(new Dimension(800, 360));
    jPanel3.setRequestFocusEnabled(true);
    jPanel3.setLayout(gridLayout1);
    jPanel1.setBackground(SystemColor.control);
    jPanel1.setPreferredSize(new Dimension(350, 100));
    jPanel1.setLayout(borderLayout4);
    jPanel5.setPreferredSize(new Dimension(300, 100));
    jPanel5.setLayout(borderLayout5);
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setText(lang.getString("ReceiptDate") + ":");
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setPreferredSize(new Dimension(120, 20));
    jLabel4.setText(lang.getString("ToStore") + ":");
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setPreferredSize(new Dimension(120, 20));
    jLabel5.setText(lang.getString("FromStore") + ":");
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setPreferredSize(new Dimension(120, 20));
    jLabel6.setText(lang.getString("TransferId") + ":");
    txtTransferDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtTransferDate.setPreferredSize(new Dimension(150, 20));
    txtTransferDate.setText("");
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(780, 375));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done") + " (F1)");
    btnDone.setActionCommand("btnDone");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelReceiptFromDmgGood_btnDone_actionAdapter(this));
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnBack.setPreferredSize(new Dimension(120, 37));
    btnBack.setToolTipText(lang.getString("Back") + " (F12)");
    btnBack.setActionCommand("btnBack");
    btnBack.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnBack.addActionListener(new PanelReceiptFromDmgGood_btnBack_actionAdapter(this));
    jPanel8.setBackground(SystemColor.control);
    jPanel8.setPreferredSize(new Dimension(130, 100));
    jPanel9.setBackground(SystemColor.control);
    jPanel9.setDebugGraphicsOptions(0);
    jPanel9.setOpaque(true);
    jPanel9.setPreferredSize(new Dimension(220, 100));
    jPanel9.setToolTipText("");
    jPanel9.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    jPanel10.setBackground(SystemColor.control);
    jPanel10.setMinimumSize(new Dimension(38, 31));
    jPanel10.setPreferredSize(new Dimension(130, 100));
    jPanel11.setBackground(SystemColor.control);
    jPanel11.setPreferredSize(new Dimension(220, 100));
    jPanel11.setLayout(flowLayout2);
    jPanel4.setPreferredSize(new Dimension(100, 10));
    jPanel6.setBackground(new Color(121, 152, 198));
    jPanel6.setPreferredSize(new Dimension(800, 50));
    cboTransferID.setPreferredSize(new Dimension(150, 20));
    cboTransferID.addActionListener(new PanelReceiptFromDmgGood_cboTransferID_actionAdapter(this));
    cboTransferID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    flowLayout2.setAlignment(FlowLayout.LEFT);
    cboFromStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboFromStoreID.setPreferredSize(new Dimension(150, 20));
    cboToStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboToStoreID.setPreferredSize(new Dimension(150, 20));
    jButton1.setPreferredSize(new Dimension(120, 20));
    jButton1.setText(lang.getString("TransferId") + "");
    jButton1.addActionListener(new PanelReceiptFromDmgGood_jButton1_actionAdapter(this));
    this.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jPanel1, BorderLayout.WEST);
    jPanel1.add(jPanel8, BorderLayout.WEST);
    jPanel8.add(jLabel5, null);
    jPanel8.add(jLabel4, null);
    jPanel1.add(jPanel9, BorderLayout.CENTER);
    jPanel9.add(cboFromStoreID, null);
    jPanel9.add(cboToStoreID, null);
    jPanel9.add(jButton1, null);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel5.add(jPanel10, BorderLayout.WEST);
    jPanel10.add(jLabel6, null);
    jPanel10.add(jLabel3, null);
    jPanel5.add(jPanel11, BorderLayout.CENTER);
    jPanel11.add(cboTransferID, null);
    jPanel11.add(txtTransferDate, null);
    jPanel2.add(jPanel4,  BorderLayout.EAST);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    this.add(jPanel6, BorderLayout.NORTH);
    jPanel6.add(btnDone,null);
    jPanel6.add(btnBack,null);
    dm.addColumn(lang.getString("ItemID"));
    dm.addColumn(lang.getString("UPC"));
    dm.addColumn(lang.getString("ItemName"));
    dm.addColumn(lang.getString("Size"));
    dm.addColumn(lang.getString("Quantity"));
  }

  public void applyPermission() {
     EmpRight er = new EmpRight();
     er.initData(DAL, DAL.getEmpID());
     er.setData(DAL.getEmpID(), Constant.SCRS_RECEIPT_DAMAGE_GOODS);
     btnDone.setEnabled(er.getAdd());

     //  btnAdminRoleReport.setEnabled(er.getReport());
     if(!er.getAdd()){
       ut.showMessage(frmMain, lang.getString("TT002"),lang.getString("MS317_YouPermission"));

     }

   }

  void initData(){
    String sql="";
    ResultSet rs = null;
    try{
      sql = "Select store_id, name from btm_im_store where STORE_TYPE = 'S' order by upper(name)";
      rs = DAL.executeScrollQueries(sql);
      cboFromStoreID.setData1(rs);

      sql = "Select store_id, name from btm_im_store where STORE_TYPE = 'D' order by upper(name)";
      rs = DAL.executeScrollQueries(sql);
      cboToStoreID.setData1(rs);
      rs.getStatement().close();
      txtTransferDate.setText(ut.getSystemDate(1));
      txtTransferDate.setEditable(false);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
    if (cboFromStoreID.getSelectedIndex() != 0 && cboToStoreID.getSelectedIndex() != 0){
      String sql = " select DMG_GOODS_ID,DMG_GOODS_ID from BTM_IM_TRANSFER_DMG_GOODS " +
          " where FROM_STORE_ID = " + cboFromStoreID.getSelectedData().trim() + "  and TO_STORE_ID = " + cboToStoreID.getSelectedData().trim() +
          " and DMG_GOODS_ID not in (select  BTM_IM_RECEIPT_DMG_GOODS.RECEIPT_ID from BTM_IM_RECEIPT_DMG_GOODS where FROM_STORE_ID = " + cboFromStoreID.getSelectedData().trim() + " and TO_STORE_ID = " + cboToStoreID.getSelectedData().trim() +") " ;
      ResultSet rs = null;
//      System.out.println(sql);
      try{
        //keep To Store
        sToStore = cboToStoreID.getSelectedData().trim();
        sFromStore = cboFromStoreID.getSelectedData().trim();
        iToStore = cboToStoreID.getSelectedIndex();
        iFromStore = cboFromStoreID.getSelectedIndex();
        table.removeAll();
        //........................................
        rs = DAL.executeScrollQueries(sql);
        cboTransferID.setData1(rs);
        rs.getStatement().close();
      }catch(Exception ex){
        ex.printStackTrace();
      }
    }
  }

  void cboTransferID_actionPerformed(ActionEvent e) {
    if(cboTransferID.getSelectedIndex() != 0){
      String sql = "";
      ResultSet rs = null;
      sql = " select gi.ITEM_ID,im.ART_NO, gi.ITEM_NAME, ad.ATTR_DTL_DESC, "
      + "sum(case when reason_id = 1 or reason_id = 2 then qty else 0 end) -  "
      + "sum(case when reason_id = 3 then qty else 0 end) qty  "
      + "from BTM_IM_TRANSFER_DMG_GOODS_ITEM gi,btm_im_item_master im, btm_im_attr_dtl ad  "
      + "where gi.item_id = im.item_id and im.ATTR2_DTL_ID = ad.ATTR_DTL_ID  "
      + "and DMG_GOODS_ID = '" + cboTransferID.getSelectedData().trim() + "' "
      + "group by gi.item_id, im.art_no, gi.item_name, ad.attr_dtl_desc  ";
//      sql = " select gi.ITEM_ID,im.ART_NO, gi.ITEM_NAME, ad.ATTR_DTL_DESC,QTY " +
//            " from BTM_IM_TRANSFER_DMG_GOODS_ITEM gi,btm_im_item_master im, btm_im_attr_dtl ad " +
//            " where gi.item_id = im.item_id and im.ATTR2_DTL_ID = ad.ATTR_DTL_ID " +
//            " and DMG_GOODS_ID = '" + cboTransferID.getSelectedData().trim() + "' ";

//      System.out.println(sql);
      try{
        rs = DAL.executeScrollQueries(sql);
        dm.resetIndexes();
        table.setData(rs);
        table.setHeaderName(lang.getString("ItemID"),0);
        table.setHeaderName(lang.getString("UPC"),1);
        table.setHeaderName(lang.getString("ItemName"),2);
        table.setHeaderName(lang.getString("Size"),3);
        table.setHeaderName(lang.getString("Quantity"),4);
        //ut.changeDataTypetoLong(4,dm); //Yen.Dinh 19-06-2006
        rs.getStatement().close();
      }catch(Exception ex){
        ex.printStackTrace();
      }
    }
  }

  boolean checkItemIDInSOH(String sItemID,String sStoreID,DataAccessLayer DAL){
    boolean bFlag = false;
    ResultSet rs = null;
    try {
      String sql = "select ITEM_ID from BTM_IM_ITEM_LOC_SOH where ITEM_ID = '"+sItemID+"' and STORE_ID = "+sStoreID;
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
          bFlag =true;
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return bFlag;
  }

  void btnDone_actionPerformed(ActionEvent e) {
    if (table.getRowCount()>0){
      if (cboTransferID.getSelectedIndex()==0){
        cboFromStoreID.setSelectedIndex(0);
        cboToStoreID.setSelectedIndex(0);

        while (dm.getRowCount()>0)
          dm.removeRow(0);
        ut.showMessage(frmMain,lang.getString("TT001") ,lang.getString("MS352_ChooseTranfer"));
        return;
      }

      String sql = "";
      Vector vSql = new Vector();

      sql = "insert into BTM_IM_RECEIPT_DMG_GOODS values('" +
          cboTransferID.getSelectedData().trim() +
          "'," + sFromStore + "," +
          sToStore +
          ",'" + DAL.getEmpID() + "',to_date('" + ut.getSystemDate() + "','" +
          ut.DATE_FORMAT + "'))";
      vSql.addElement(sql);

      for(int i = 0; i<table.getRowCount(); i++){
        sql = "insert into BTM_IM_RECEIPT_DMG_GOODS_ITEM(receipt_id, item_id, item_name, qty) " +
            " values ('" + cboTransferID.getSelectedData().trim() + "','" + table.getValueAt(i,0).toString().trim() +
            "','" + table.getValueAt(i,2).toString().trim() + "'," + table.getValueAt(i,4).toString().trim() + ")";
        vSql.addElement(sql);

        if (checkItemIDInSOH(table.getValueAt(i,0).toString(),sToStore,DAL)){//True
          sql =
              "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand + " +
              table.getValueAt(i, 4).toString().trim() +
              ",soh_update_datetime = to_date('" + ut.getSystemDate() + "','" + ut.DATE_FORMAT + "'), last_update_id ='" + DAL.getEmpID() +
              "' where item_id ='" + table.getValueAt(i, 0).toString().trim() +
              "' and store_id = " + sToStore;

        }else{//False
          sql = "insert into btm_im_item_loc_soh(item_id,store_id,stock_on_hand,soh_update_datetime,last_update_id) values('" +
              table.getValueAt(i, 0).toString().trim() + "'," +
              sToStore +
              "," + table.getValueAt(i, 4).toString().trim() + ",to_date('" +
              ut.getSystemDate() +
              "','" + ut.DATE_FORMAT + "'),'" + DAL.getEmpID() + "')";
        }
        vSql.addElement(sql);
      }

      if (!vSql.isEmpty()){
        DAL.setBeginTrans(DAL.getConnection());
        DAL.executeBatchQuery(vSql);
        DAL.setEndTrans(DAL.getConnection());
        vSql.clear();
        while(table.getRowCount()>0){
          dm.removeRow(0);
        }
        cboTransferID.setSelectedIndex(0);
        if (iFromStore != 0 && iToStore != 0){
          String sqll = " select DMG_GOODS_ID,DMG_GOODS_ID from BTM_IM_TRANSFER_DMG_GOODS " +
         " where FROM_STORE_ID = " + sFromStore + "  and TO_STORE_ID = " + sToStore +
         " and DMG_GOODS_ID not in (select  BTM_IM_RECEIPT_DMG_GOODS.RECEIPT_ID from BTM_IM_RECEIPT_DMG_GOODS where FROM_STORE_ID = " + sFromStore + " and TO_STORE_ID = " + sToStore +") " ;

          ResultSet rs = null;
//          System.out.println(sqll);
          try{
            rs = DAL.executeScrollQueries(sqll);
            cboTransferID.setData1(rs);
            rs.getStatement().close();
          }catch(Exception ex){
            ex.printStackTrace();
          }
        }
      }
    }
  }

  void btnBack_actionPerformed(ActionEvent e) {
    vSql.clear();
    while(table.getRowCount()>0){
      dm.removeRow(0);
    }

    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showReceive();
    frmMain.setTitle(lang.getString("TT0017"));
    frmMain.pnlMainSim.btnRecSupplier.requestFocus(true);


  }
  //check item exist
  boolean checkExist(String itemID, String storeId) {
    //set open connection to make sure we do not get ResultSet Error

    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select item_id from btm_im_item_loc_soh where item_id = '" +
        itemID +
        "' and store_id = " + Long.parseLong(storeId);
//    System.out.println(sql);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        stmt.close();

        return true;
      }
      stmt.close();
      //the same meaning with set open connection. These method have to stay together

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return false;
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
       else if (identifier.intValue() == KeyEvent.VK_F12) {
         btnBack.doClick();
       }
     }
   }

}

class PanelReceiptFromDmgGood_jButton1_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptFromDmgGood adaptee;

  PanelReceiptFromDmgGood_jButton1_actionAdapter(PanelReceiptFromDmgGood adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class PanelReceiptFromDmgGood_cboTransferID_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptFromDmgGood adaptee;

  PanelReceiptFromDmgGood_cboTransferID_actionAdapter(PanelReceiptFromDmgGood adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboTransferID_actionPerformed(e);
  }
}

class PanelReceiptFromDmgGood_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptFromDmgGood adaptee;

  PanelReceiptFromDmgGood_btnDone_actionAdapter(PanelReceiptFromDmgGood adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelReceiptFromDmgGood_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptFromDmgGood adaptee;

  PanelReceiptFromDmgGood_btnBack_actionAdapter(PanelReceiptFromDmgGood adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}
