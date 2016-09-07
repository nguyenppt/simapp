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

import com.lowagie.text.PageSize;
import btm.business.ItemMasterBusObj;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Transfer -> Store To Store </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelTransfer extends JPanel {
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_NAME = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_QUANTITY = 4; //

  public static final String STATUS_WORKSHEET = "WorkSheet";
  public static final String STATUS_APPROVED = "Approved";
  public static final String STATUS_COMPLETED = "Completed";
  public static final String STATUS_CANCELLED = "Cancelled";

  ItemMasterBusObj bItemMaster ;
  DataAccessLayer DAL;
  ResourceBundle lang;
  FrameMainSim frmMain;
  Vector vSql = new Vector();
  Utils ut = new Utils();
  Statement stmt = null;
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
  BJComboBox cboDesStoreID = new BJComboBox();
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
      if (col == 3) {
        return Long.class;
      }
      return Object.class;
    }
  };
  BJButton btnDone = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDeleteItem = new BJButton();
  BJButton btnViewTransfer = new BJButton();
  BJButton btnStockCount = new BJButton();
  BJButton btnCancel = new BJButton();
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
  double qty = 0;
  JPanel jPanel4 = new JPanel();
  JPanel jPanel6 = new JPanel();
//  JLabel lblItemID = new JLabel();
//  JTextField txtItemID = new JTextField();
  JLabel lblQty = new JLabel();
  JLabel lblStatus = new JLabel();
  JTextField txtQty = new JTextField();
  BJComboBox cboStatus = new BJComboBox();
  JButton txtUPC = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout1 = new GridLayout();
  BJComboBox cboSourceStoreID = new BJComboBox();
  FlowLayout flowLayout2 = new FlowLayout();
  JLabel jLabel71 = new JLabel();
  JTextField txtArtNo = new JTextField();
//  JLabel jLabel7 = new JLabel();
//  JTextField txtSize = new JTextField();

  public PanelTransfer(FrameMainSim frmMain) {
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
    DAL.getConnfigParameter();
    lang=DAL.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

    bItemMaster = new ItemMasterBusObj();
    bItemMaster.setDataAccessLayer(DAL);
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    jPanel2.setBackground(SystemColor.control);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setPreferredSize(new Dimension(800, 160));
    jPanel2.setRequestFocusEnabled(true);
    jPanel2.setLayout(borderLayout2);
    this.setPreferredSize(new Dimension(800, 600));
    this.setRequestFocusEnabled(true);
    jPanel3.setBackground(SystemColor.control);
    jPanel3.setPreferredSize(new Dimension(800, 350));
    jPanel3.setRequestFocusEnabled(true);
    jPanel3.setLayout(gridLayout1);
    jPanel1.setBackground(SystemColor.control);
    jPanel1.setPreferredSize(new Dimension(350, 150));
    jPanel1.setLayout(borderLayout4);
    jPanel5.setPreferredSize(new Dimension(300, 150));
    jPanel5.setLayout(borderLayout5);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setText(lang.getString("Receiver")+":");
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText(lang.getString("CreaterName")+":");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setText(lang.getString("TransferDate")+":");
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setPreferredSize(new Dimension(120, 20));
    jLabel4.setText(lang.getString("ToStore")+":");
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setPreferredSize(new Dimension(120, 20));
    jLabel5.setText(lang.getString("FromStore")+":");
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setPreferredSize(new Dimension(120, 20));
    jLabel6.setText(lang.getString("TransferId")+":");
    txtReceiverName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtReceiverName.setNextFocusableComponent(txtArtNo);
    txtReceiverName.setPreferredSize(new Dimension(150, 20));
    txtReceiverName.setText("");
    txtReceiverName.addKeyListener(new PanelTransfer_txtReceiverName_keyAdapter(this));
    txtCreaterName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreaterName.setPreferredSize(new Dimension(150, 20));
    txtCreaterName.setEditable(false);
    txtCreaterName.setText("");
    txtCreaterName.addKeyListener(new PanelTransfer_txtCreaterName_keyAdapter(this));
    txtTransferDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtTransferDate.setPreferredSize(new Dimension(150, 20));
    txtTransferDate.setText("");
    cboDesStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboDesStoreID.setNextFocusableComponent(txtReceiverName);
    cboDesStoreID.setPreferredSize(new Dimension(150, 20));
    cboDesStoreID.addKeyListener(new PanelTransfer_cboDesStoreID_keyAdapter(this));
    txtTransferID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtTransferID.setPreferredSize(new Dimension(150, 20));
    txtTransferID.setText("");
    txtTransferID.setHorizontalAlignment(SwingConstants.LEADING);
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(780, 350));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setActionCommand("btnDone");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelTransfer_btnDone_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("FontName_Label")+"Add (F2)");
    btnAdd.setActionCommand("btnAdd");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelTransfer_btnAdd_actionAdapter(this));
    btnDeleteItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDeleteItem.setPreferredSize(new Dimension(120, 37));
    btnDeleteItem.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDeleteItem.setActionCommand("btnDeleteItem");
    btnDeleteItem.setText("<html><center><b>"+lang.getString("Delete")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnDeleteItem.addActionListener(new PanelTransfer_btnDeleteItem_actionAdapter(this));
    btnViewTransfer.setPreferredSize(new Dimension(120, 37));
    btnViewTransfer.setToolTipText("");
    btnViewTransfer.setActionCommand("<html><center><b>View Transfer </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;F5</html>");
    btnViewTransfer.setText("<html><center><b>"+lang.getString("View")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html>");
    btnViewTransfer.addActionListener(new PanelTransfer_btnViewTransfer_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Back")+" (F12)");
    btnCancel.setActionCommand("btnCancel");
    btnCancel.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelTransfer_btnCancel_actionAdapter(this));
    jPanel8.setBackground(SystemColor.control);
    jPanel8.setPreferredSize(new Dimension(130, 150));
    jPanel9.setBackground(SystemColor.control);
    jPanel9.setDebugGraphicsOptions(0);
    jPanel9.setOpaque(true);
    jPanel9.setPreferredSize(new Dimension(220, 150));
    jPanel9.setToolTipText("");
    jPanel9.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    jPanel10.setBackground(SystemColor.control);
    jPanel10.setMinimumSize(new Dimension(38, 31));
    jPanel10.setPreferredSize(new Dimension(130, 150));
    jPanel11.setBackground(SystemColor.control);
    jPanel11.setPreferredSize(new Dimension(220, 150));
    jPanel11.setLayout(flowLayout2);
    jPanel4.setPreferredSize(new Dimension(100, 10));
    jPanel6.setBackground(new Color(121, 152, 198));
    jPanel6.setPreferredSize(new Dimension(800, 50));
    lblQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblQty.setPreferredSize(new Dimension(120, 20));
    lblQty.setText(lang.getString("Quantity")+":");
    lblStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStatus.setPreferredSize(new Dimension(120, 20));
    lblStatus.setText(lang.getString("Status")+":");

    txtQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtQty.setPreferredSize(new Dimension(150, 20));
    txtQty.addFocusListener(new PanelTransfer_txtQty_focusAdapter(this));
    txtQty.addKeyListener(new PanelTransfer_txtQty_keyAdapter(this));
    cboStatus.setPreferredSize(new Dimension(150, 20));
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));


    txtUPC.setPreferredSize(new Dimension(40, 20));
    txtUPC.setText("...");
    txtUPC.setVerticalAlignment(SwingConstants.CENTER);
    txtUPC.addActionListener(new PanelTransfer_txtUPC_actionAdapter(this));
    table.addKeyListener(new PanelTransfer_table_keyAdapter(this));
    cboSourceStoreID.setPreferredSize(new Dimension(150, 20));
    cboSourceStoreID.setNextFocusableComponent(txtReceiverName);
    cboSourceStoreID.setEnabled(false);
    cboSourceStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnStockCount.setPreferredSize(new Dimension(120, 37));
    btnStockCount.setRequestFocusEnabled(true);
    btnStockCount.setToolTipText(lang.getString("StockCount")+" (F6)");
    btnStockCount.setActionCommand("btnStockCount");
    btnStockCount.setContentAreaFilled(true);
    btnStockCount.setSelected(false);
    btnStockCount.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnStockCount.setText("<html><center><b>"+lang.getString("StockCount")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F6</html>");
    btnStockCount.addActionListener(new PanelTransfer_btnStockCount_actionAdapter(this));
    flowLayout2.setAlignment(FlowLayout.LEFT);
    jLabel71.setText(lang.getString("UPC")+":");
    jLabel71.setPreferredSize(new Dimension(120, 20));
    jLabel71.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtArtNo.setHorizontalAlignment(SwingConstants.LEADING);
    txtArtNo.addKeyListener(new PanelTransfer_txtArtNo_keyAdapter(this));
    txtArtNo.setText("");
    txtArtNo.setPreferredSize(new Dimension(150, 20));
    txtArtNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    this.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jPanel1, BorderLayout.WEST);
    jPanel1.add(jPanel8, BorderLayout.WEST);
    jPanel8.add(jLabel6, null);
    jPanel8.add(jLabel5, null);
    jPanel8.add(jLabel4, null);
    jPanel8.add(jLabel71, null);
    jPanel1.add(jPanel9, BorderLayout.CENTER);
    jPanel9.add(txtTransferID, null);
    jPanel9.add(cboSourceStoreID, null);
    jPanel9.add(cboDesStoreID, null);
    jPanel9.add(txtArtNo,null);
    jPanel9.add(txtUPC, null);
//    jPanel9.add(txtSize, null);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel5.add(jPanel10, BorderLayout.WEST);
    jPanel10.add(jLabel3, null);
    jPanel10.add(jLabel2, null);
    jPanel10.add(jLabel1, null);
    jPanel10.add(lblQty, null);
    jPanel10.add(lblStatus, null);
    jPanel5.add(jPanel11, BorderLayout.CENTER);
    jPanel11.add(txtTransferDate, null);
    jPanel11.add(txtCreaterName, null);
    jPanel11.add(txtReceiverName, null);
    jPanel11.add(txtQty, null);
    jPanel11.add(cboStatus, null);
    jPanel2.add(jPanel4,  BorderLayout.EAST);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    this.add(jPanel6, BorderLayout.NORTH);
    jPanel6.add(btnDone,null);
    jPanel6.add(btnAdd,null);
    jPanel6.add(btnViewTransfer, null);
    jPanel6.add(btnStockCount, null);
    jPanel6.add(btnDeleteItem,null);
    jPanel6.add(btnCancel,null);
    dm.addColumn(lang.getString("ItemID"));
    dm.addColumn(lang.getString("UPC"));
    dm.addColumn(lang.getString("ItemName"));
    dm.addColumn(lang.getString("Size"));
    dm.addColumn(lang.getString("Quantity"));
    setCboStatus(cboStatus);
  }

  public void setCboStatus(JComboBox cbo) {
   cbo.removeAllItems();
   cbo.addItem(STATUS_WORKSHEET);
   cbo.addItem(STATUS_APPROVED);
   cbo.addItem(STATUS_COMPLETED);
   cbo.addItem(STATUS_CANCELLED);
   cbo.setSelectedIndex(0); //default status: WorkSheet
 }

  public void applyPermission() {
   EmpRight er = new EmpRight();
   er.initData(DAL, DAL.getEmpID());
   er.setData(DAL.getEmpID(), Constant.SCRS_TRANSFER);
   btnAdd.setEnabled(er.getAdd());
   btnDeleteItem.setEnabled(er.getDelete());
   btnViewTransfer.setEnabled(er.getSearch());
   if(!er.getAdd()){
     ut.showMessage(frmMain, lang.getString("TT002"),
                    lang.getString("MS1085_CanNotUseScreen"));

   }

 }

  //Get store
  void getStoreID(){
    String sql = "select store_id, Name from btm_im_store order by upper(name)";

    try{
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      cboDesStoreID.setData(rs);
      rs.beforeFirst();
//      rs1 = DAL.executeScrollQueries(sql,stmt);
      cboSourceStoreID.setData(rs);
      rs.close();
//      rs1.close();
      stmt.close();
      if (cboSourceStoreID.getSelectedIndex() != -1){
        cboSourceStoreID.setSelectedData(DAL.getStoreID());
      }
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

  }
  //Get Employee name
  void getEmpName(){
    String empID = DAL.getEmpID();
    String sql = "select first_name, last_name from btm_im_employee where emp_id ='" + empID + "'";
    try{
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        txtCreaterName.setText(rs.getString("first_name").trim() + " " + rs.getString("last_name").trim());
        stmt.close();
        return;
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  //set Transfer ID
  private String getReceiptID(){
    String receiptID=DAL.getStoreID()+ ut.getSystemDateTime5();
    receiptID = receiptID.replaceAll(":"," ").replaceAll("-"," ").replaceAll(" ","");
    return receiptID;
  }
  //Check item
  boolean checkItem(String itemID, long storeID) {
    ResultSet rs = null;
    ResultSet rs1 = null;
    String sql =
        "select i.item_id, i.item_name, i.art_no, BACK_ROOM_QTY " +
        " from btm_im_item_master i, "  +
        " btm_im_item_loc_soh ils where i.item_id = ils.item_id and i.item_id='" +
        itemID + "' and store_id=" + storeID;
//    System.out.println(sql);
    try {
//      stmt = DAL.getConnection().createStatement(ResultSet.
//                                                TYPE_SCROLL_INSENSITIVE,
//                                                ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sql);
//      rs.beforeFirst();
      if (rs.next()) {
        this.itemDesc = rs.getString("item_name");
        this.qty = rs.getLong("BACK_ROOM_QTY");
        this.artNo = rs.getString("art_no");
        sql = "";
        sql = "select attr_dtl_desc from btm_im_attr_dtl a, btm_im_item_master i " +
            " where i.attr2_dtl_id = a.attr_dtl_id and item_id ='" + itemID +"'";
//        System.out.println(sql);
        rs1 = DAL.executeQueries(sql);
        if (rs1.next()){
          this.size = rs1.getString("attr_dtl_desc");
        }
        rs1.getStatement().close();
        rs.getStatement().close();
//        stmt.close();
        return true;
      }else{
        rs.getStatement().close();
      }
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return false;
  }

  //check item in table
  //true if exist item in table
  boolean checkItemTable(String itemID, double qty) {
    for (int i = 0; i < dm.getRowCount(); i++) {
      String item = "" + table.getValueAt(i, COL_ITEM_ID);
      if (itemID.equalsIgnoreCase(item)) {
        double quantity = qty;
        quantity = quantity + ut.convertToDouble(table.getValueAt(i, COL_QUANTITY).toString());

//        if (this.qty >= quantity){
//          table.setValueAt(new Double(quantity), i, COL_QUANTITY);
//          txtArtNo.setText("");
//          txtQty.setText("");
//          txtArtNo.requestFocus(true);
//        }else{
//          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2007_NotEnoughSOH") );
//          txtQty.setText("" + (this.qty - ut.convertToDouble("" + table.getValueAt(i, COL_QUANTITY))));
//          txtQty.requestFocus(true);
//        }

        if (this.qty < quantity){
          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2007_NotEnoughSOH") );
        }
          table.setValueAt(new Double(quantity), i, COL_QUANTITY);
          txtArtNo.setText("");
          txtQty.setText("");
          txtArtNo.requestFocus(true);
        return true;
      }
    }
    return false;
  }

  //set text
  void setText() {

//    setTransferID();
    getEmpName();
    getStoreID();
    txtTransferID.setText(getReceiptID());
    txtTransferID.setEditable(false);
    txtTransferDate.setText(ut.getSystemDate(1));
    txtTransferDate.setEditable(false);
    cboDesStoreID.requestFocus(true);
  }
  String getStoreName(String storeID){
    String storeName = "";
    ResultSet rs = null;
    String sqlStr = "Select Name From BTM_IM_STORE Where Store_ID=" + storeID;
    try{
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                     TYPE_SCROLL_INSENSITIVE,
                                                     ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
      if(rs.next()){
        storeName = rs.getString("NAME");
      }
      stmt.close();
      rs.close();
    }catch(Exception ex){};
    return storeName;
  }

  void btnAdd_actionPerformed(ActionEvent e) {
//    String transferID = "";
//    String fromStoreID = "";
//    String toStoreID = "";
//    String empID = "";
//    String receiverName = "";
//    String transferDate = "";
    String itemId = "";
    double qty = 0;
    if (txtReceiverName.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2008_InpuReceiverName"));
      txtReceiverName.requestFocus(true);
      return;
    }
    if (txtQty.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2009_InputQuantity"));
      txtQty.requestFocus(true);
      return;
    }
    itemId=bItemMaster.getIDfromUPC(txtArtNo.getText().trim());
    if (!checkItem(itemId,Long.parseLong(cboSourceStoreID.getSelectedData()))){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2001_UPCInvalid"));
      txtArtNo.setText("");
      txtArtNo.requestFocus(true);
      return;
    }
    if (!txtQty.getText().equalsIgnoreCase("")){
      if (ut.convertToDouble(txtQty.getText()) > this.qty) {
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS2007_NotEnoughSOH"));
//        txtQty.requestFocus(true);
//        return;
      }
    }
    if(cboSourceStoreID.getSelectedData().equalsIgnoreCase(cboDesStoreID.getSelectedData())){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2010_ChooseAnotherStore"));
      cboDesStoreID.requestFocus(true);
      return;
    }
//    transferID = txtTransferID.getText();

    qty = ut.convertToDouble(txtQty.getText());
    if (table.getRowCount() < 1){
//      vSql.clear();
//      fromStoreID = cboSourceStoreID.getSelectedData();
//      toStoreID = cboDesStoreID.getSelectedData();
//      empID = DAL.getEmpID();
//      receiverName = txtReceiverName.getText();
//      transferDate = txtTransferDate.getText();
//      System.out.println("111111111111 insert into btm_im_transfer values('" + transferID +
//                      "','" + fromStoreID + "','" + toStoreID + "','" + empID +
//                      "','" + receiverName + "',to_date('" + transferDate +
//                      "','dd/MM/yyyy'),'')");
//      vSql.addElement("insert into btm_im_transfer values('" + transferID +
//                      "','" + fromStoreID + "','" + toStoreID + "','" + empID +
//                      "','" + receiverName + "',to_date('" + transferDate +
//                      "','dd/MM/yyyy'),'')");
      cboDesStoreID.setEnabled(false);
      txtReceiverName.setEditable(false);
      cboSourceStoreID.setEnabled(false);
    }
    if (checkItemTable(itemId,qty)){
      return;
    }
    Object[] rowData = new Object[]{
        itemId,artNo, itemDesc,  size,new Double(qty)
    };
    dm.addRow(rowData);
    txtArtNo.setText("");
    txtQty.setText("");
    txtArtNo.requestFocus(true);
//    txtArtNo.selectAll();
//    txtSize.setText("");
//    txtItemID.requestFocus(true);
    //set focus for last row
    if(table.getRowCount()>0){
      table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
    }

    //show current row
    Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
    jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

  }
//  boolean checkItem(String itemID, String storeID){
//
//    return true;
//  }
  void btnDone_actionPerformed(ActionEvent e) {
    if (!cboStatus.getSelectedItem().toString().equalsIgnoreCase(STATUS_WORKSHEET) &&
        !cboStatus.getSelectedItem().toString().equalsIgnoreCase(STATUS_APPROVED)) {
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS2011_TransferStatusCanNotBe")+" '" + cboStatus.getSelectedItem().toString() + "'");
      cboStatus.requestFocus(true);
      return;
    }
    if (cboSourceStoreID.getSelectedItem().toString().equalsIgnoreCase(cboDesStoreID.getSelectedItem().toString())){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS2012_FromStoreDiffToStore") );
      cboDesStoreID.requestFocus(true);
      return;
    }

    ut.writeToTextFile("file\\error.txt","Hist TR  "+ ut.getSystemDateTime()+ "\r\n");

    String sql="";
    vSql.clear();
    if(cboStatus.getSelectedItem().toString().equalsIgnoreCase(STATUS_APPROVED)){
      sql = "Insert Into BTM_IM_TRANSFER Values('" + txtTransferID.getText() +
          "','" + cboSourceStoreID.getSelectedData() + "','" + cboDesStoreID.getSelectedData() +
          "','" + DAL.getEmpID() + "','" + txtReceiverName.getText() + "',to_date('" +
          txtTransferDate.getText() + "','dd/MM/yyyy'),'A')";
    }
    else{
      sql = "Insert Into BTM_IM_TRANSFER Values('" + txtTransferID.getText() +
          "','" + cboSourceStoreID.getSelectedData() + "','" + cboDesStoreID.getSelectedData() +
          "','" + DAL.getEmpID() + "','" + txtReceiverName.getText() + "',to_date('" +
          txtTransferDate.getText() + "','dd/MM/yyyy'),'W')";
    }
    vSql.addElement(sql);

    if (table.getRowCount()>0){
      for (int i = 0; i < table.getRowCount(); i++) {
        //insert into btm_im_transfer_item
        sql="Insert Into BTM_IM_TRANSFER_ITEM Values('" + txtTransferID.getText() +
            "','" + table.getValueAt(i, COL_ITEM_ID).toString() +
            "','" + ut.putCommaToString(table.getValueAt(i, COL_ITEM_NAME).toString()) +
            "'," + ut.convertToDouble(table.getValueAt(i, COL_QUANTITY).toString()) +
            ",1,"+i+")";

//        System.out.println(sql);
        vSql.addElement(sql);
        if(cboStatus.getSelectedItem().toString().equalsIgnoreCase(STATUS_APPROVED)){
          double nQty = ut.convertToDouble(table.getValueAt(i, COL_QUANTITY).toString()) ;

          sql= "Update BTM_IM_ITEM_LOC_SOH Set STOCK_ON_HAND = STOCK_ON_HAND - " + nQty +
              ",BACK_ROOM_QTY=BACK_ROOM_QTY - "+ nQty +", soh_update_datetime = to_date('" +
              ut.getSystemDate() + "','" + ut.DATE_FORMAT + "') " +
              " where item_id ='" + table.getValueAt(i, COL_ITEM_ID).toString() +
              "' and store_id =" + ut.convertToDouble(cboSourceStoreID.getSelectedData());

//          System.out.println(sql);
          vSql.addElement(sql);
        }
      }
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        DAL.setBeginTrans(DAL.getConnection());
        DAL.executeBatchQuery(vSql, stmt);
        DAL.setEndTrans(DAL.getConnection());
        stmt.close();
      }
      catch(Exception ex){};
    }
    vSql.clear();
    if (cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approved")){
      if (table.getRowCount() > 0) {
        printReceipt(txtTransferID.getText(),
                     cboSourceStoreID.getSelectedItem().toString(),
                     cboDesStoreID.getSelectedItem().toString(),
                     txtCreaterName.getText(), txtReceiverName.getText(),
                     table, DAL);
      }
    }
    vSql.clear();
    txtQty.setText("");
    txtArtNo.setText("");
    cboDesStoreID.setSelectedIndex(0);
    cboDesStoreID.setEnabled(true);
//disable    cboSourceStoreID.setEnabled(true);
    txtReceiverName.setText("");
    txtReceiverName.setEditable(true);
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showInventManageButton();
    while (table.getRowCount() > 0) {
      dm.removeRow(0);
    }
  }

  void printReceipt(String strTransferID, String strFromStoreRoom,
                    String strToStoreRoom, String strCreater,
                    String strReceiver, BJTable tbl, DataAccessLayer DAL) {
    String strHeader[][] = {
        {
        "STORE#" + strFromStoreRoom}
    };
    String strTitle[] = {
        "TRANSFER"};
    String strInfo[][] = {
        {
        "DATE : " + ut.getSystemDate().substring(0, 10),
        "CREATER: " + strCreater,
        "TRANS ID: " + strTransferID}, {
        "RECEIVER: " + strReceiver,
        "TO STORE: " + strToStoreRoom, " "}
    };
    String strFormat[] = {
        "int","String","String","String","String","money","double"
    };

    String strTableTitle[] = {
        "No","UPC","Item Name","Color","Size","Qty","Price"
    };

    String strTableAlign[] = {
        "CENTER", "CENTER", "LEFT", "LEFT", "LEFT","RIGHT","RIGHT"};
    String strTableItem[][] = new String[tbl.getRowCount()][7];
    String st="";

//    int iTableSkew[] = {
//        5,8,34,13,10,10,20};
    int iTableSkew[] = {
        4, 20, 32, 12, 8, 7, 19};
    int[] iTotal = {
        5};
    //Add Item to receipt
    for (int i = 0; i < tbl.getRowCount(); i++) {
      //col 0
      int iNo = i + 1;
      strTableItem[i][0] = "" + iNo;

      //col 1
      if (tbl.getValueAt(i, COL_ART_NO) != null) {
        st = tbl.getValueAt(i, COL_ART_NO).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][1] = st;

      //col 2
      if (tbl.getValueAt(i, COL_ITEM_NAME) != null) {
        st = tbl.getValueAt(i, COL_ITEM_NAME).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][2] = st;

      //col 3
      strTableItem[i][3] = getItemColor(tbl.getValueAt(i, COL_ITEM_ID).toString(), DAL);

      //col 4
      strTableItem[i][4] = getItemSize(tbl.getValueAt(i, COL_ITEM_ID).toString(), DAL);

      //col 5
      if (tbl.getValueAt(i, COL_QUANTITY) != null) {
        st = tbl.getValueAt(i, COL_QUANTITY).toString();
      }
      else {
        st = "0";
      }
      strTableItem[i][5] = st;

      //col 6
      strTableItem[i][6] = getItemPrice(tbl.getValueAt(i, COL_ITEM_ID).toString(), DAL);
    }

    PrintReceipt pp = new PrintReceipt();
    pp.setFileName(DAL.getAppHome()+"\\file\\" +
                         strTransferID +
                         ".pdf");

    //pp.setFileName("./file/"+strTransferID+".pdf");
    pp.setHeader(strHeader);
    pp.setTitle(strTitle);
    pp.setInfo(strInfo);

    //Add info
    pp.setTotalName("Total Qty");
    pp.setLeftSign("CREATER");
    pp.setRightSign("RECEIVER");
    pp.setColorBackgroundTotal(new Color(255,255,255));

    pp.setIsViewTotal(true);
    pp.setRowsTotal(iTotal);
    pp.setFormatColumn(strFormat);
    pp.setTableSkew(iTableSkew);
    pp.setTableAlign(strTableAlign);
    pp.setTableTitle(strTableTitle);
    pp.setTableItem(strTableItem);
    pp.setDeleteFileAfterPrint(true);

    pp.setGroupAt(1);
    pp.setIsGroupByArtNumber(false);
    pp.setTotalGroupName("Total by UPC");


    pp.print();
  }

  //Export to PDF file
/*  void createPdfFile(String strTransferID, String strFromStoreRoom,
                     String strToStoreRoom, String strCreater,
                     String strReceiver, BJTable tbl, DataAccessLayer DAL) {
    Document document = new Document();
    document.setPageSize(PageSize.A4);
    document.setMargins(12, 70, 10, 10);
    try {
      //Total page
      int iPage = tbl.getRowCount() / 30 + 1;
      int i, j;
      Utils ut = new Utils();
      PdfWriter writer = PdfWriter.getInstance(document,
                                               new FileOutputStream("./file/tf" +
          strTransferID + ".pdf"));
      document.open();
      for (j = 1; j <= iPage; j++) {
        //-----------------------------------------Part 1-----------------------------------------//
        if (j != 1) {
          document.newPage();
        }

        PdfPTable tableGroup = new PdfPTable(2);
        tableGroup.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableGroup.getDefaultCell().setGrayFill(0f);
        tableGroup.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableGroup.addCell(new Paragraph(new Chunk("STOREROOM#" +
            strFromStoreRoom,
            FontFactory.getFont(
                FontFactory.HELVETICA, 10))));
        tableGroup.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        tableGroup.getDefaultCell().setGrayFill(0f);
        tableGroup.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableGroup.addCell(new Paragraph(new Chunk("Page: " + j + " of " +
            iPage,
            FontFactory.getFont(
                FontFactory.HELVETICA, 10))));
        tableGroup.setTotalWidth(520);
        tableGroup.setLockedWidth(true);
        document.add(tableGroup);
        //-----------------------------------------Part 2-----------------------------------------//
        Paragraph p2 = new Paragraph("TRANSFER\n\n",
                                     FontFactory.getFont(FontFactory.
            HELVETICA, 12, Font.BOLD, new Color(0, 0, 0)));
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);
        //-----------------------------------------Part 3-----------------------------------------//
        PdfPTable tableDesc = new PdfPTable(3);
        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableDesc.addCell(new Paragraph(new Chunk("DATE : " +
                                                  ut.getSystemDate().substring(
            0,
            10),
                                                  FontFactory.getFont(
            FontFactory.
            HELVETICA, 10))));

        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableDesc.addCell(new Paragraph(new Chunk("CREATER: " +
                                                  strCreater,
                                                  FontFactory.getFont(
            FontFactory.
            HELVETICA, 10))));

        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableDesc.addCell(new Paragraph(new Chunk("TRAN ID: " +
                                                  strTransferID,
                                                  FontFactory.getFont(
            FontFactory.
            HELVETICA, 10))));

        tableDesc.getDefaultCell().setColspan(1);
        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableDesc.addCell(new Paragraph(new Chunk("TIME  : " +
                                                  ut.getSystemDateTime2().
                                                  substring(11, 19),
                                                  FontFactory.getFont(
            FontFactory.
            HELVETICA, 10))));

        tableDesc.getDefaultCell().setColspan(1);
        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableDesc.addCell(new Paragraph(new Chunk("RECEIVER: " +
                                                  strReceiver,
                                                  FontFactory.getFont(
            FontFactory.
            HELVETICA, 10))));

        tableDesc.getDefaultCell().setColspan(1);
        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableDesc.addCell(new Paragraph(new Chunk("TO STORE: " +
                                                  strToStoreRoom,
                                                  FontFactory.getFont(
            FontFactory.
            HELVETICA, 10))));

        tableDesc.setTotalWidth(520);
        tableDesc.setLockedWidth(true);
        document.add(tableDesc);
        //-----------------------------------------Part 4-----------------------------------------//
        Paragraph p3 = new Paragraph(" ",
                                     FontFactory.getFont(FontFactory.
            HELVETICA, 12, Font.BOLD, new Color(0, 0, 0)));
        p3.setAlignment(Element.ALIGN_CENTER);
        document.add(p3);
        //-----------------------------------------Part 5 1-----------------------------------------//
        float[] widths = {
            0.08f, 0.12f, 0.3f, 0.1f, 0.08f, 0.09f, 0.3f};
        PdfPTable table = new PdfPTable(widths);
        PdfPCell cell = new PdfPCell(new Paragraph("No",
            FontFactory.getFont(
                FontFactory.
                HELVETICA, 10, Font.BOLD, new Color(0, 0, 0))
                                     ));

        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Art #",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD, new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Item Name",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD, new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Color",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD, new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Size",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD, new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Qty",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD, new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Price",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD, new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        //-----------------------------------------Part 5 2-----------------------------------------//
        int iRemain = 0;
        if ( (tbl.getRowCount() - 30 * (j - 1)) > 30) {
          iRemain = 30;
        }
        else {
          iRemain = tbl.getRowCount() - 30 * (j - 1);
        }
        int endFor = 30 * (j - 1) + iRemain;
        int l = 0;
        String st = "";
        for (l = 30 * (j - 1); l < endFor; l++) {
          //col 1
          int iNo = l + 1;
          cell = new PdfPCell(new Paragraph(new Chunk(String.valueOf(iNo).
              toString(), FontFactory.getFont(FontFactory.HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 2
          if (tbl.getValueAt(l, 1) != null) {
            st = tbl.getValueAt(l, 1).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 3
          if (tbl.getValueAt(l, 2) != null) {
            st = tbl.getValueAt(l, 2).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 4
          cell = new PdfPCell(new Paragraph(new Chunk(getItemColor(tbl.
              getValueAt(l, 0).toString(), DAL),
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 5
          cell = new PdfPCell(new Paragraph(new Chunk(getItemSize(tbl.
              getValueAt(l, 0).toString(), DAL),
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));

          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 7
          if (tbl.getValueAt(l, 3) != null) {
            st = tbl.getValueAt(l, 3).toString();
          }
          else {
            st = "0";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);
          //col 6
          cell = new PdfPCell(new Paragraph(new Chunk(ut.
              formatNumber(getItemPrice(tbl.
                                        getValueAt(l, 0).toString(), DAL)),
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);
        }
        //-----------------------------------------Part 5 3-----------------------------------------//
        if (iPage == j) {
          //  table = new PdfPTable(8);
          int iTotalEnd = 0;
          for (i = 0; i < tbl.getRowCount(); i++) {
            iTotalEnd += Integer.parseInt(tbl.getValueAt(i, 3).toString());
          }

          cell = new PdfPCell(new Paragraph("Total Qty",
                                            FontFactory.getFont(FontFactory.
              HELVETICA, 10, Font.BOLD, new Color(0, 0, 0))
                              ));
          cell.setColspan(5);
          cell.setBorderColor(new Color(188, 188, 188));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cell);

          cell = new PdfPCell(new Paragraph(new Chunk(String.
              valueOf(iTotalEnd).toString(),
              FontFactory.getFont(FontFactory.HELVETICA, 10))));
          cell.setBorderColor(new Color(188, 188, 188));
          cell.setColspan(1);
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cell);

          cell = new PdfPCell(new Paragraph(new Chunk(" ",
              FontFactory.getFont(FontFactory.HELVETICA, 10))));
          cell.setBorderColor(new Color(188, 188, 188));
          cell.setColspan(1);
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cell);

          table.setTotalWidth(520);
          table.setLockedWidth(true);
          document.add(table);
        }
        else {
          table.setTotalWidth(520);
          table.setLockedWidth(true);
          document.add(table);
        }
        //-----------------------------------------Part 6-----------------------------------------//
        if (iRemain <= 30) {
          document.add(new Paragraph(" "));
          PdfPTable tableSpace = new PdfPTable(1);
          for (i = 0; i < (30 - iRemain) + 1; i++) {
            tableSpace.getDefaultCell().setVerticalAlignment(Element.
                ALIGN_MIDDLE);
            tableSpace.getDefaultCell().setGrayFill(0f);
            tableSpace.getDefaultCell().setBorderColor(new Color(255, 255,
                255));
            tableSpace.addCell(" ");
          }
          tableSpace.setTotalWidth(520);
          tableSpace.setLockedWidth(true);
          document.add(tableSpace);
        }
        //-----------------------------------------Part 7-----------------------------------------//
        document.add(new Paragraph(" "));
        PdfPTable tableSign = new PdfPTable(2);
        tableSign.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_CENTER);
        tableSign.getDefaultCell().setGrayFill(0f);
        tableSign.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableSign.addCell("CREATER");
        tableSign.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_CENTER);
        tableSign.getDefaultCell().setGrayFill(0f);
        tableSign.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableSign.addCell("RECEIVER");
        for (i = 0; i < 5; i++) {
          tableSign.addCell(" ");
          tableSign.getDefaultCell().setColspan(2);
          tableSign.setTotalWidth(520);
          tableSign.setLockedWidth(true);
        }
        document.add(tableSign);
      }

      document.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }*/
  //get Item Price
  public String getItemPrice(String strItemID,DataAccessLayer DAL){
    String strInfo="";
    ResultSet rs = null;
    try{
      rs = null;
      String sql =
            "select item_id, NEW_PRICE, attr1_name, attr2_name " +
            "from btm_im_price_hist " +
            "where item_id ='" + strItemID + "' and STATUS ='1' ";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("new_price")!=null){
          strInfo = rs.getString("new_price");
        }else{
          strInfo =  "0";
        }
      }
      rs.getStatement().close();
    }catch(Exception e){
    }
    return strInfo;
  }
  //get Color
  public String getItemColor(String strItemID,DataAccessLayer DAL){
    String strInfo="";
    ResultSet rs = null;
    try{
      rs = null;
      String sql =
            "select item_id, NEW_PRICE, attr1_name, attr2_name " +
            "from btm_im_price_hist " +
            "where item_id ='" + strItemID + "' and STATUS ='1' ";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("attr1_name") != null) {
          strInfo = rs.getString("attr1_name");
        }
        else {
          strInfo =  " ";
        }
      }
      rs.getStatement().close();
    }catch(Exception e){
    }
    return strInfo;
  }
  //get Size
  public String getItemSize(String strItemID,DataAccessLayer DAL){
    String strInfo="";
    ResultSet rs = null;
    try{
      rs = null;
      String sql =
           "select item_id, NEW_PRICE, attr1_name, attr2_name " +
           "from btm_im_price_hist " +
           "where item_id ='" + strItemID + "' and STATUS ='1' ";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("attr2_name") != null) {
          strInfo = rs.getString("attr2_name");
        }
        else {
          strInfo =  " ";
        }
      }
      rs.getStatement().close();
    }catch(Exception e){
    }
    return strInfo;
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
  void btnCancel_actionPerformed(ActionEvent e) {
    vSql.clear();
    while (table.getRowCount() > 0) {
      dm.removeRow(0);
    }
//    txtItemID.setText("");
    txtQty.setText("");
//    txtSize.setText("");
    txtArtNo.setText("");
    cboDesStoreID.setSelectedIndex(0);
    cboDesStoreID.setEnabled(true);
    //disable    cboSourceStoreID.setEnabled(true);
    txtReceiverName.setText("");
    txtReceiverName.setEditable(true);

//    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
//    frmMain.pnlMainSim.showInventManageButton();
//    frmMain.setTitle(lang.getString("TT014")+" - Inventory Management");
//    frmMain.pnlMainSim.btnTranStore.requestFocus(true);

    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showTransfer();
    frmMain.setTitle(lang.getString("TT0107"));
    frmMain.pnlMainSim.btnTranStore.requestFocus(true);

  }

  void btnDeleteItem_actionPerformed(ActionEvent e) {

    if (table.getSelectedRow() == -1) {
      return;
    }
    if (table.getRowCount() == 1) {
      dm.removeRow(0);
      return;
    }
    int[] i = table.getSelectedRows();
    for (int j = 0; j < i.length; j++) {
//      vSql.removeElementAt(i[0]);
      dm.removeRow(i[0]);
    }

  }
  void txtItemID_keyReleased(KeyEvent e) {
//    if(txtItemID.getText().equals("") || e.getKeyChar()== e.VK_ENTER ||
//       e.getKeyChar() == e.VK_BACK_SPACE || e.getKeyChar() == e.VK_DELETE)
//      return;
//    if(!ut.isLongIntString((txtItemID.getText().trim())))
//    {
//      ut.showMessage(frmMain,"Warning","The Item ID must be numeric");
//      txtItemID.requestFocus(true);
//      return;
//    }

  }

  void txtQty_keyReleased(KeyEvent e) {
//    if(txtQty.getText().equals("") || e.getKeyChar()== e.VK_ENTER ||
//       e.getKeyChar() == e.VK_BACK_SPACE || e.getKeyChar() == e.VK_DELETE)
//      return;
//    if(!ut.isLongIntString((txtQty.getText().trim())))
//    {
//      ut.showMessage(frmMain,"Warning","The Quantity must be numeric");
//      txtQty.requestFocus(true);
//      return;
//    }
  }

//  void txtItemID_keyTyped(KeyEvent e) {
//    ut.limitLenjTextField(e,txtItemID,25);
//    char key = e.getKeyChar();
//    if (ut.isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE){
//      return;
//    }else if (key == KeyEvent.VK_ENTER){
//      if (!checkItem(txtItemID.getText(),Long.parseLong(cboSourceStoreID.getSelectedData()))){
//        ut.showMessage(frmMain,"Warning","Item id does not exist in database or It isn't receipted! Please choose again");
//        txtItemID.setText("");
//        txtItemID.requestFocus(true);
//        return;
//      }
//      txtQty.requestFocus(true);
//      return;
//    }else{
//      e.consume();
//    }
//
//  }

  void txtQty_keyTyped(KeyEvent e) {
    /*ut.limitLenjTextField(e,txtQty,18);
    char key = e.getKeyChar();
    if (ut.isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE){
      return;
    }else if (key == KeyEvent.VK_ENTER){
      if (txtItemID.getText().trim().equalsIgnoreCase("")){
        if (txtArtNo.getText().equalsIgnoreCase("")) {
          ut.showMessage(frmMain, "Warning",
                         "You have to insert art no and size or scan item id");
          txtArtNo.requestFocus(true);
          return;
        }
        String size = "";
        if (txtSize.getText().equalsIgnoreCase("")) {
          size = "";
        }
         else {
          size = txtSize.getText().trim();
        }
        txtItemID.setText(getItemID(txtArtNo.getText().trim(), size));
      }
      checkItem(txtItemID.getText(),Long.parseLong(cboSourceStoreID.getSelectedData()));
      if (this.qty != 0){
        if (!txtQty.getText().equalsIgnoreCase("")){
          if (Long.parseLong(txtQty.getText()) > this.qty) {
            ut.showMessage(frmMain, "Warning",
                           "There is not enough quantity in stock on hand");
            txtQty.requestFocus(true);
            return;
          //auto add when click enter
          }else{
            btnAdd.doClick();
            txtArtNo.requestFocus(true);
          }
        }
      }else{
        ut.showMessage(frmMain, "Warning", "This item didn't have quantity in your stock, please insert quantity for it!");
        txtArtNo.requestFocus(true);
        txtArtNo.selectAll();
        txtSize.setText("");
        txtItemID.setText("");
        return;
      }
    }else{
      e.consume();
    }*///Yen

  ut.checkNumberPositive(e, txtQty.getText());
  if (e.getKeyChar() == KeyEvent.VK_ENTER){
    btnAdd.doClick();
  }
  }

  void txtItemID_focusLost(FocusEvent e) {
//    if (txtItemID.getText().equalsIgnoreCase("")){
//      return;
//    }
  }

  void txtQty_focusLost(FocusEvent e) {
    if (txtQty.isEditable()) {
          if (!txtQty.getText().equalsIgnoreCase("")) {
            txtQty.setText("" + ut.formatNumber(txtQty.getText()));
          }
        }

  }

  void btnViewTransfer_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_TRANSFER_VIEW);
    frmMain.pnlMainSim.showInventManageButton();
    frmMain.pnlTransferView.showData();
    frmMain.pnlTransferView.txtDate.setText(ut.getSystemDate(1));
    frmMain.pnlTransferView.txtDate.requestFocus(true);

  }

  void txtUPC_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0016"),true,frmMain,itemBusObject);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done){
      txtArtNo.setText(dlgItemLookup.getUPC());
      txtArtNo.requestFocus(true);
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
       if (identifier.intValue() == KeyEvent.VK_F1) {
         btnDone.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F2) {
         btnAdd.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F8) {
         btnDeleteItem.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F5) {
         btnViewTransfer.doClick();
       }
       else if (identifier.intValue()==KeyEvent.VK_F6){
         btnStockCount.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
         btnCancel.doClick();
       }
     }
   }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnAdd.doClick();
               }
  }

  void btnStockCount_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_STOCK_COUNT);
//    frmMain.pnlStockCountBackRoom.initScreen();
//    frmMain.pnlStockCountBackRoom.cboStoreID.setSelectedData(DAL.getStoreID());
    frmMain.pnlStockCountBackRoom.applyPermission();
  }

  void txtCreaterName_keyTyped(KeyEvent e) {
  }

  void txtReceiverName_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
//      btnAdd.doClick();
      txtArtNo.requestFocus(true);

    }

  }

  void txtArtNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtArtNo,ItemMaster.LEN_ITEM_ARTNO);
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtQty.requestFocus(true);
    }
  }

  void txtSize_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtQty.requestFocus(true);
    }
  }
  String getItemID(String artNo, String size){
    String result = "";
    String sql = "";
    ResultSet rs = null;
    try{
      sql = "select distinct im.item_id from btm_im_item_master im, btm_im_attr_dtl ad " +
          " where im.attr2_dtl_id = ad.attr_dtl_id and im.status <> 'D' and im.art_no = '" + artNo + "'";
      if (!size.equalsIgnoreCase("")){
        sql = sql + " and lower(ad.attr_dtl_desc) = lower('" + size + "')";
      }else{
        sql = sql + " and ad.attr_dtl_desc is null";
      }
//      System.out.println(sql);
      rs = DAL.executeQueries(sql);
      if (rs.next()){
        result = rs.getString("item_id");
      }
      rs.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return result;
  }

  void cboDesStoreID_keyTyped(KeyEvent e) {
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
      txtReceiverName.requestFocus(true);
    }
  }

}

class PanelTransfer_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelTransfer adaptee;

  PanelTransfer_btnAdd_actionAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelTransfer_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelTransfer adaptee;

  PanelTransfer_btnDone_actionAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelTransfer_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelTransfer adaptee;

  PanelTransfer_btnCancel_actionAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelTransfer_btnDeleteItem_actionAdapter implements java.awt.event.ActionListener {
  PanelTransfer adaptee;

  PanelTransfer_btnDeleteItem_actionAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDeleteItem_actionPerformed(e);
  }
}

//class PanelTransfer_txtItemID_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelTransfer adaptee;
//
//  PanelTransfer_txtItemID_keyAdapter(PanelTransfer adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyReleased(KeyEvent e) {
//    adaptee.txtItemID_keyReleased(e);
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtItemID_keyTyped(e);
//  }
//}

class PanelTransfer_txtQty_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransfer adaptee;

  PanelTransfer_txtQty_keyAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtQty_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtQty_keyTyped(e);
  }
}

class PanelTransfer_txtItemID_focusAdapter extends java.awt.event.FocusAdapter {
  PanelTransfer adaptee;

  PanelTransfer_txtItemID_focusAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemID_focusLost(e);
  }
}

class PanelTransfer_txtQty_focusAdapter extends java.awt.event.FocusAdapter {
  PanelTransfer adaptee;

  PanelTransfer_txtQty_focusAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtQty_focusLost(e);
  }
}

class PanelTransfer_btnViewTransfer_actionAdapter implements java.awt.event.ActionListener {
  PanelTransfer adaptee;

  PanelTransfer_btnViewTransfer_actionAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnViewTransfer_actionPerformed(e);
  }
}

class PanelTransfer_txtUPC_actionAdapter implements java.awt.event.ActionListener {
  PanelTransfer adaptee;

  PanelTransfer_txtUPC_actionAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtUPC_actionPerformed(e);
  }
}

class PanelTransfer_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransfer adaptee;

  PanelTransfer_table_keyAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class PanelTransfer_btnStockCount_actionAdapter implements java.awt.event.ActionListener {
  PanelTransfer adaptee;

  PanelTransfer_btnStockCount_actionAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnStockCount_actionPerformed(e);
  }
}

class PanelTransfer_txtCreaterName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransfer adaptee;

  PanelTransfer_txtCreaterName_keyAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCreaterName_keyTyped(e);
  }
}

class PanelTransfer_txtReceiverName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransfer adaptee;

  PanelTransfer_txtReceiverName_keyAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtReceiverName_keyTyped(e);
  }
}

class PanelTransfer_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransfer adaptee;

  PanelTransfer_txtArtNo_keyAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelTransfer_txtSize_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransfer adaptee;

  PanelTransfer_txtSize_keyAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSize_keyTyped(e);
  }
}

class PanelTransfer_cboDesStoreID_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransfer adaptee;

  PanelTransfer_cboDesStoreID_keyAdapter(PanelTransfer adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboDesStoreID_keyTyped(e);
  }
}

