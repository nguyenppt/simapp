//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//




























package sim;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Vinh Le
 * @version 1.0
 */



import common.*;
import btm.attr.StockOnHand;
import btm.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.Vector;
import javax.swing.border.*;
import btm.business.*;
import btm.attr.*;
import java.util.ResourceBundle;

public class PanelViewReceipt extends JPanel {
  ResourceBundle lang;
  //Declare Data Access Layer
  DataAccessLayer DAL;
  //Declare Utils
  Utils ut = new Utils();
  //Declare Frame Main
  FrameMainSim frmMain;
  //Declare a batch update
  Statement stmt = null;
  ReceiptBusObj recBusObj = new ReceiptBusObj();
  String supplierID = "";
  Vector vArrayInfo = new Vector();//Vector save array info of Receipt Item
  ItemMasterBusObj  bItemMasterBusObj = new ItemMasterBusObj();
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
//  SortableTableModel dm = new SortableTableModel();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm,true){
    public void tableChanged(TableModelEvent e) {
      super.tableChanged(e);
      repaint();
    }
  };

  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JLabel lblReceiptDate = new JLabel();
  BJButton btnBack = new BJButton();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  GridLayout gridLayout1 = new GridLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  JTextField txtReceiptDate = new JTextField();
  TitledBorder titledBorder1;
  boolean flagSetHotKeyForAdd = true;
  JButton btnDate = new JButton();
  BJButton btnView = new BJButton();

  public PanelViewReceipt(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    DAL.getConnfigParameter();
    lang=DAL.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

    registerKeyboardActions();
    titledBorder1 = new TitledBorder("");
    this.setLayout(borderLayout1);
    jPanel1.setPreferredSize(new Dimension(800, 50));
    this.setPreferredSize(new Dimension(800, 600));
    jPanel2.setPreferredSize(new Dimension(800, 150));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(800, 440));
    jPanel3.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(760, 395));
    jPanel5.setMinimumSize(new Dimension(300, 150));
    jPanel5.setLayout(borderLayout4);
    jPanel8.setPreferredSize(new Dimension(300, 150));
    jPanel8.setLayout(flowLayout2);
    jPanel9.setPreferredSize(new Dimension(180, 150));
    jPanel9.setLayout(flowLayout3);
    lblReceiptDate.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblReceiptDate.setPreferredSize(new Dimension(90, 20));
    lblReceiptDate.setText(lang.getString("ReceiptDate"));
    btnBack.setPreferredSize(new Dimension(120, 37));
    btnBack.setToolTipText(lang.getString("Back")+" (F12)");
    btnBack.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnView.setPreferredSize(new Dimension(120, 37));
    btnView.setToolTipText(lang.getString("View")+" (F5)");
    btnView.setText("<html><center><b>"+lang.getString("View")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html>");

    btnBack.addActionListener(new PanelViewReceipt_btnBack_actionAdapter(this));
    table.addMouseListener(new PanelViewReceipt_table_mouseAdapter(this));
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    txtReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtReceiptDate.setMinimumSize(new Dimension(6, 21));
    txtReceiptDate.setPreferredSize(new Dimension(170, 20));
    txtReceiptDate.setToolTipText("");
    txtReceiptDate.setSelectionStart(10);
    txtReceiptDate.setText(ut.getSystemDate(1));
    btnDate.setText("...");
    btnDate.addActionListener(new PanelViewReceipt_btnDate_actionAdapter(this));
//    btnDate.addActionListener(new PanelTransferView_btnDate_actionAdapter(this));
//    btnDate.addActionListener(new PanelTransferView_btnDate_actionAdapter(this));
    table.addKeyListener(new PanelViewReceipt_table_keyAdapter(this));
    btnView.addActionListener(new PanelViewReceipt_btnView_actionAdapter(this));
    this.add(jPanel1,  BorderLayout.NORTH);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel8,  BorderLayout.WEST);
    jPanel8.add(lblReceiptDate, null);
    jPanel5.add(jPanel9,  BorderLayout.CENTER);
    jPanel9.add(txtReceiptDate, null);
    jPanel9.add(btnDate, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    jPanel1.add(btnView, null);
    jPanel1.add(btnBack, null);
    dm.addColumn("Receipt ID");
    dm.addColumn("Store Name");
    dm.addColumn("Supplier Name");
    dm.addColumn("Receipt To");
    table.setRowHeight(30);
    table.setColor(Color.lightGray, Color.white);
    createConnection();
    txtReceiptDate.addKeyListener(new PanelViewReceipt_txtReceiptDate_keyAdapter(this));
  }
  private void createConnection(){
    this.bItemMasterBusObj.setDataAccessLayer(DAL) ;
}

  void txtReceiptDate_keyTyped(KeyEvent e) {
   // ut.limitLenjTextField(e,txtReceiptDate,StockOnHand.LEN_QTY);
  }
   void btnBack_actionPerformed(ActionEvent e) {
     while(dm.getRowCount()>0){
       dm.removeRow(0);
     }
//     txtReceiptDate.setText(ut.getSystemDate2());
     frmMain.showScreen(Constant.SCRS_GOOD_RECEIPT);
     frmMain.pnlGoodsReceipt.cboSupplier.requestFocus(true);
     frmMain.pnlGoodsReceipt.initScreen();
   }

   void table_mouseClicked(MouseEvent e) {
     if (e.getClickCount() == 2) {
       viewReceiptDetail();
       frmMain.showScreen(Constant.SCRS_VIEW_RECEIPT_DETAILS);
       frmMain.pnlViewReceiptDetails.getComboData();
       frmMain.pnlViewReceiptDetails.txtItemID.requestFocus(true);
//       frmMain.pnlViewReceiptDetails.initScreen();
//       frmMain.pnlMainSim.showInventManageButton();
     }
   }
   void viewReceiptDetail(){
     ResultSet rs = null;
     try {
       stmt = DAL.getConnection().createStatement(ResultSet.
                                               TYPE_SCROLL_INSENSITIVE,
                                               ResultSet.CONCUR_READ_ONLY);

       rs= recBusObj.getDataDetail(String.valueOf(table.getValueAt(table.getSelectedRow(),0)),DAL,stmt);
       rs.next();
       if (!rs.isBeforeFirst() && !rs.isAfterLast()) {
         frmMain.pnlViewReceiptDetails.txtReceiptID.setText(rs.getString("Receipt_ID"));
         frmMain.pnlViewReceiptDetails.txtStore.setText(rs.getString("Store_ID"));
         frmMain.pnlViewReceiptDetails.txtSupplier.setText(rs.getString("SUPP_NAME"));
         frmMain.pnlViewReceiptDetails.txtReceiptDate.setText(rs.getString("RECEIPT_DATE"));
         frmMain.pnlViewReceiptDetails.txtReceiptTo.setText(table.getValueAt(table.getSelectedRow(),3).toString());
         frmMain.pnlViewReceiptDetails.txtDescription.setText(rs.getString("DESCRIPTION"));
       }
       frmMain.pnlViewReceiptDetails.dm.resetIndexes();
       frmMain.pnlViewReceiptDetails.table.removeAll();
       frmMain.pnlViewReceiptDetails.table.setData(recBusObj.getData(String.valueOf(table.getValueAt(table.getSelectedRow(),0)),DAL));
       frmMain.pnlViewReceiptDetails.table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
       frmMain.pnlViewReceiptDetails.table.getTableHeader().setReorderingAllowed(false);
       frmMain.pnlViewReceiptDetails.table.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 13));
       frmMain.pnlViewReceiptDetails.table.setHeaderName("Item ID",0);
       frmMain.pnlViewReceiptDetails.table.setHeaderName("UPC",1);
       frmMain.pnlViewReceiptDetails.table.setHeaderName("Item Name",2);
       frmMain.pnlViewReceiptDetails.table.setHeaderName("Size",3);
       frmMain.pnlViewReceiptDetails.table.setHeaderName("Quantity",4);
       frmMain.pnlViewReceiptDetails.table.setHeaderName("Reason",5);
       frmMain.pnlViewReceiptDetails.table.setHeaderName("Season",6);
//       frmMain.pnlViewReceiptDetails.table.setColumnWidth();
       frmMain.pnlViewReceiptDetails.table.setHeader(new java.awt.Font("Dialog", 1, 15));
      // ut.changeDataTypetoLong(4,frmMain.pnlViewReceiptDetails.dm);//Yen.Dinh 19-06-2006
       stmt.close();
     }
     catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
   }
   String getSupplierName(String suppID) {
     String suppName = "";
     ResultSet rs = null;
     try {
       String sql = "select SUPP_NAME from BTM_IM_SUPPLIER Where Supp_ID='" +
           suppID + "'";
       stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

       rs = DAL.executeScrollQueries(sql,stmt);
       if (rs.next()) {
         suppName = rs.getString("SUPP_NAME");
       }
       rs.close();
       stmt.close();
     }
     catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
     return suppName;
   }

   String getSupplierID(String itemID, String storeID) {
    String suppID = "";
    ResultSet rs = null;
    try {
      String sql = "select distinct SUPP_ID from BTM_IM_ITEM_LOC_SUPPLIER Where Item_ID='" + itemID + "' and Store_ID=" + storeID;
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      if (rs.next()) {
        suppID = rs.getString("SUPP_ID");
      }
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return suppID;
  }

//  void btnReceiptDateSearch_actionPerformed(ActionEvent e) {
//    int posX, posY, posXFrame, posYFrame;
//    posXFrame = this.frmMain.getX();
//    posYFrame = this.frmMain.getY();
//    posX = this.btnReceiptDateSearch.getX() + posXFrame + 90;
//    posY = this.btnReceiptDateSearch.getY() + posYFrame + 90;
//    TimeDlg receiptdate = new TimeDlg(null);
//    receiptdate.setTitle("Choose Receipt Date");
//    receiptdate.setSize(300, 250);
//    receiptdate.setResizable(false);
//    receiptdate.setLocation(posX, posY);
//    receiptdate.setVisible(true);
//    if (receiptdate.isOKPressed()) {
//      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
//          "dd-MM-yyyy");
//      String date = fd.format(receiptdate.getDate());
//      this.txtReceiptDate.setText(date);
//      showData(date);
//    }
//  }
  void showData(String date){
    dm.resetIndexes();
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
      setArrayInfo(getArrayInfo(date,stmt));
      table.setData(getData(date, stmt));
      stmt.close();
    }
    catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    table.setAutoResizeMode(table.
                            AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font("Dialog",
        1, 13));
    table.setHeaderName("Receipt ID", 0);
    table.setHeaderName("Store Name", 1);
    table.setHeaderName("Supplier Name", 2);
    table.setHeaderName("Receipt To", 3);
    table.setHeader(new java.awt.Font("Dialog", 1, 15));
    ut.changeDataTypetoLong(0, dm);
  }
  ResultSet getData(String date, Statement stmt){
    ResultSet rs = null;
    String sqlStr = "Select r.Receipt_ID, st.Name , su.Supp_Name, decode(io.FR_BR_FLAG,'B','Backroom','Frontroom') FR_BR_FLAG " +
        " From BTM_IM_RECEIPT r, BTM_IM_STORE st, BTM_IM_SUPPLIER su, BTM_IM_INV_ORDER io " +
        " Where r.Store_ID = st.Store_Id and r.Supp_ID = su.Supp_Id and r.RECEIPT_ID = io.ORDER_ID " +
        " and Receipt_Date = to_date('" + date + "','dd-MM-yyyy')";
//    System.out.println(sqlStr);
    try {

      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  ResultSet getArrayInfo(String date,Statement stmt){
    ResultSet rs = null;
    String sqlStr = "Select Store_ID, Supp_ID From BTM_IM_RECEIPT Where Receipt_Date = to_date('" +
          date + "','dd-MM-yyyy')";
//    System.out.println(sqlStr);
    try {
         rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  void setArrayInfo(ResultSet rs){
        try{
          vArrayInfo.removeAllElements();
          while(rs.next()){
            String []arrInfo = new String[]{rs.getString("Store_ID"),rs.getString("Supp_ID")};
            vArrayInfo.add(arrInfo);
          }
        }
        catch(Exception e){
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(e);
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
      if (identifier.intValue() == KeyEvent.VK_F5 ||
          identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnView.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12) {
        btnBack.doClick();
      }

    }
  }

  void txtReceiptDate_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        if (ut.checkDate(txtReceiptDate.getText(), "/") || ut.checkDate(txtReceiptDate.getText(), "-")) {
//          showData(txtReceiptDate.getText());
           outInterface();
        }
        else {
          ut.showMessage(frmMain, "Warning", "Input must be a date type");
          while(dm.getRowCount()>0){
            dm.removeRow(0);
          }
        }
      }
    }

    void btnDate_actionPerformed(ActionEvent e) {
      int posX, posY, posXFrame, posYFrame;

      posXFrame = this.getX();
      posYFrame = this.getY();
      posX = this.btnDate.getX() + posXFrame + 150;
      posY = this.btnDate.getY() + posYFrame + 150;
      TimeDlg endDate = new TimeDlg(null);
      endDate.setTitle(lang.getString("ChooseEndDate"));
      endDate.setResizable(false);
      endDate.setLocation(posX, posY);
      endDate.setVisible(true);

      if (endDate.isOKPressed()) {
        java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
            "dd/MM/yyyy");
        String date = fd.format(endDate.getDate());
        this.txtReceiptDate.setText(date);
        this.txtReceiptDate.requestFocus(true);
        outInterface();
      }

    }

    void outInterface() {
      if (txtReceiptDate.getText().equals("")){
        return;
      }
      if (ut.checkDate(txtReceiptDate.getText(), "/") == false) {
        ut.showMessage(frmMain, "Warning!",
                       "Wrong date.Format of date is dd/mm/yyyy.");
        btnDate.requestFocus(true);
        return;
      }
      dm.resetIndexes();
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

        table.setData(getData(txtReceiptDate.getText(), stmt));
        stmt.close();
      }
      catch(Exception e){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
      table.getTableHeader().setReorderingAllowed(false);
      table.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 13));
      table.setHeaderName("Receipt ID", 0);
      table.setHeaderName("Store Name", 1);
      table.setHeaderName("Supplier Name", 2);
      table.setHeaderName("Recept To", 3);
      table.setHeader(new java.awt.Font("Dialog", 1, 15));
//      ut.changeDataTypetoLong(1, dm);
    }

  void table_keyPressed(KeyEvent e) {
  }

  void btnView_actionPerformed(ActionEvent e) {
    if (ut.checkDate(txtReceiptDate.getText(), "/") || ut.checkDate(txtReceiptDate.getText(), "-")) {
//          showData(txtReceiptDate.getText());
       outInterface();
    }
    else {
      ut.showMessage(frmMain, "Warning", "Input must be a date type");
      while(dm.getRowCount()>0){
        dm.removeRow(0);
      }
    }

  }
}

class PanelViewReceipt_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelViewReceipt adaptee;

  PanelViewReceipt_btnBack_actionAdapter(PanelViewReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelViewReceipt_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelViewReceipt adaptee;

  PanelViewReceipt_table_mouseAdapter(PanelViewReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelViewReceipt_txtReceiptDate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewReceipt adaptee;

  PanelViewReceipt_txtReceiptDate_keyAdapter(PanelViewReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtReceiptDate_keyPressed(e);
  }
}

//class PanelTransferView_btnDate_actionAdapter implements java.awt.event.ActionListener {
//  PanelViewReceipt adaptee;
//
//  PanelTransferView_btnDate_actionAdapter(PanelViewReceipt adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnDate_actionPerformed(e);
//  }
//}

class PanelViewReceipt_btnDate_actionAdapter implements java.awt.event.ActionListener {
  PanelViewReceipt adaptee;

  PanelViewReceipt_btnDate_actionAdapter(PanelViewReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class PanelViewReceipt_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewReceipt adaptee;

  PanelViewReceipt_table_keyAdapter(PanelViewReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class PanelViewReceipt_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelViewReceipt adaptee;

  PanelViewReceipt_btnView_actionAdapter(PanelViewReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}
