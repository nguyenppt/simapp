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
import java.util.Vector;
import java.util.ResourceBundle;
import javax.swing.border.*;
import btm.business.*;
import btm.attr.*;
import java.awt.print.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Chunk;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import java.io.FileOutputStream;
import com.lowagie.text.FontFactory;
import org.pdfbox.pdmodel.PDDocument;
import com.lowagie.text.PageSize;
import org.pdfbox.PrintPDF;
import java.awt.Dimension;

public class PanelGoodsReceipt extends JPanel {
  //   17/4/2006
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_NAME = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_QUANTITY = 4; //
  boolean fromPO=false;//flag : true if receipt base on Purchase Order

  //Declare Data Access Layer
  DataAccessLayer DAL;
  //Declare Utils
  Utils ut = new Utils();
  //Declare Frame Main
  FrameMainSim frmMain;
  //Declare a batch update
  Statement stmt = null;
  String supplierID = "";
  Vector vInfo = new Vector();//Vector save array info of Receipt Item
  Vector vHistSoh = new Vector();//save array soh of receipt items
  ItemMasterBusObj  bItemMasterBusObj = new ItemMasterBusObj();
  ReceiptBusObj receiptBusObj = new ReceiptBusObj();
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true);
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JLabel lblQty = new JLabel();
  JLabel lblItemID = new JLabel();
  JTextField txtQuantity = new JTextField();
  JLabel lblStoreID = new JLabel();
  JLabel lblReceiptDate = new JLabel();
  BJComboBox txtStoreID = new BJComboBox();
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnModify = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  GridLayout gridLayout1 = new GridLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel lblReceiptID = new JLabel();
  JTextField txtUPC = new JTextField();
  JTextField txtReceiptID = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  JButton btnItemSearch = new JButton();
  JButton btnStoreSearch = new JButton();
  JButton btnReceiptDateSearch = new JButton();
  JTextField txtReceiptDate = new JTextField();
  JLabel lblDescription = new JLabel();
  JTextArea txtDescription = new JTextArea();
  TitledBorder titledBorder1;
  JLabel lblItemName = new JLabel();
  BJComboBox cboStore = new BJComboBox();
  BJButton btnClear = new BJButton();
  BJButton btnViewReceipt = new BJButton();
  boolean showAdd = true;//true : show Add button, false : show Modify button
  BJComboBox cboSupplier = new BJComboBox();
  JButton btnSupplier = new JButton();
  String itemName = "";
  String articleNo = "";
  String sizeName = "";
  JLabel lblQty1 = new JLabel();
  BJComboBox cboSeason = new BJComboBox();
  JLabel lblQty2 = new JLabel();
  JLabel lblQty3 = new JLabel();
  JLabel lblQty4 = new JLabel();
  JButton btnOrderSearch = new JButton();
  boolean IS_ENTER = true;//first time press Enter

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelGoodsReceipt(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
      lblQty4.setText("");
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();
    titledBorder1 = new TitledBorder("");
    this.setLayout(borderLayout1);
    jPanel1.setPreferredSize(new Dimension(800, 50));
    this.setPreferredSize(new Dimension(800, 600));
    jPanel2.setPreferredSize(new Dimension(800, 150));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(800, 330));
    jPanel3.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(760, 305));
    jPanel4.setPreferredSize(new Dimension(400, 150));
    jPanel4.setLayout(borderLayout3);
    jPanel5.setMinimumSize(new Dimension(300, 150));
    jPanel5.setLayout(borderLayout4);
    jPanel6.setPreferredSize(new Dimension(120, 150));
    jPanel6.setLayout(flowLayout1);
    jPanel7.setPreferredSize(new Dimension(180, 150));
    jPanel7.setLayout(flowLayout4);
    jPanel10.setPreferredSize(new Dimension(50, 150));
    lblQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblQty.setPreferredSize(new Dimension(90, 20));
    lblQty.setText(lang.getString("Quantity")+":");
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setPreferredSize(new Dimension(90, 22));
    lblItemID.setHorizontalAlignment(SwingConstants.LEADING);
    lblItemID.setText(lang.getString("UPC")+":");
    txtQuantity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtQuantity.setPreferredSize(new Dimension(170, 20));
    txtQuantity.setText("");
    txtQuantity.addKeyListener(new PanelGoodsReceipt_txtQuantity_keyAdapter(this));
    txtQuantity.addFocusListener(new PanelGoodsReceipt_txtQuantity_focusAdapter(this));
    jPanel8.setPreferredSize(new Dimension(100, 180));
    jPanel8.setLayout(flowLayout2);
    jPanel9.setPreferredSize(new Dimension(180, 180));
    jPanel9.setLayout(flowLayout3);
    lblStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStoreID.setPreferredSize(new Dimension(90, 20));
    lblStoreID.setToolTipText("");
    lblStoreID.setText(lang.getString("StoreName")+":");
    lblReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReceiptDate.setPreferredSize(new Dimension(90, 20));
    lblReceiptDate.setText(lang.getString("ReceiptDate")+":");
    txtStoreID.setEnabled(true);
    txtStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtStoreID.setPreferredSize(new Dimension(170, 20));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelGoodsReceipt_btnCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnDelete.addActionListener(new PanelGoodsReceipt_btnDelete_actionAdapter(this));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify")+" (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnModify.addActionListener(new PanelGoodsReceipt_btnModify_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelGoodsReceipt_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelGoodsReceipt_btnDone_actionAdapter(this));
    table.addMouseListener(new PanelGoodsReceipt_table_mouseAdapter(this));
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    lblReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReceiptID.setPreferredSize(new Dimension(90, 22));
    lblReceiptID.setText(lang.getString("ReceiptID")+":");
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtUPC.setNextFocusableComponent(txtQuantity);
    txtUPC.setPreferredSize(new Dimension(170, 20));
    txtUPC.setText("");
    txtUPC.addFocusListener(new PanelGoodsReceipt_txtUPC_focusAdapter(this));
    txtUPC.addKeyListener(new PanelGoodsReceipt_txtUPC_keyAdapter(this));
    txtReceiptID.setBackground(SystemColor.control);
    txtReceiptID.setEnabled(false);
    txtReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtReceiptID.setPreferredSize(new Dimension(170, 20));
    txtReceiptID.setHorizontalAlignment(SwingConstants.LEADING);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    btnItemSearch.setPreferredSize(new Dimension(41, 23));
    btnItemSearch.setText("...");
    btnItemSearch.addFocusListener(new PanelGoodsReceipt_btnItemSearch_focusAdapter(this));
    btnItemSearch.addActionListener(new PanelGoodsReceipt_btnItemSearch_actionAdapter(this));
    txtReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtReceiptDate.setMinimumSize(new Dimension(6, 21));
    txtReceiptDate.setPreferredSize(new Dimension(170, 20));
    txtReceiptDate.setToolTipText("");
    txtReceiptDate.setEditable(false);
    txtReceiptDate.setText(ut.getSystemDate(1));
    btnStoreSearch.setEnabled(false);
    btnStoreSearch.setPreferredSize(new Dimension(41, 23));
    btnStoreSearch.setText("...");
    btnStoreSearch.addActionListener(new PanelGoodsReceipt_btnStoreSearch_actionAdapter(this));
    btnReceiptDateSearch.setEnabled(false);
    btnReceiptDateSearch.setPreferredSize(new Dimension(41, 23));
    btnReceiptDateSearch.setText("...");
    btnReceiptDateSearch.setEnabled(false);
    btnReceiptDateSearch.addActionListener(new PanelGoodsReceipt_btnReceiptDateSearch_actionAdapter(this));
    lblDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblDescription.setPreferredSize(new Dimension(90, 20));
    lblDescription.setText(lang.getString("Description")+":");
    txtDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDescription.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDescription.setDebugGraphicsOptions(0);
    txtDescription.setPreferredSize(new Dimension(230, 40));
    lblItemName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemName.setPreferredSize(new Dimension(90, 22));
    lblItemName.setText(lang.getString("SupplierName")+":");
    cboStore.setEnabled(false);
    cboStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    cboStore.setDoubleBuffered(false);
    cboStore.setMinimumSize(new Dimension(26, 21));
    cboStore.setPreferredSize(new Dimension(170, 22));
    cboStore.addActionListener(new PanelGoodsReceipt_cboStore_actionAdapter(this));
    cboStore.addMouseListener(new PanelGoodsReceipt_cboStore_mouseAdapter(this));
    cboStore.addFocusListener(new PanelGoodsReceipt_cboStore_focusAdapter(this));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear")+"(F7)");
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnClear.addActionListener(new PanelGoodsReceipt_btnClear_actionAdapter(this));
    btnViewReceipt.setText("<html><center><b>"+lang.getString("ViewReceipt")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html>");
    btnViewReceipt.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnViewReceipt.setToolTipText(lang.getString("ViewReceipt")+" (F5)");
    btnViewReceipt.setPreferredSize(new Dimension(120, 37));
    btnViewReceipt.addActionListener(new PanelGoodsReceipt_btnViewReceipt_actionAdapter(this));
    cboSupplier.setPreferredSize(new Dimension(170, 22));
    cboSupplier.addKeyListener(new PanelGoodsReceipt_cboSupplier_keyAdapter(this));
    cboSupplier.setMinimumSize(new Dimension(26, 21));
    cboSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    btnSupplier.setText("...");
    btnSupplier.addActionListener(new PanelGoodsReceipt_btnSupplier_actionAdapter(this));
    btnSupplier.setPreferredSize(new Dimension(41, 23));
    table.addKeyListener(new PanelGoodsReceipt_table_keyAdapter(this));
    lblQty1.setText(lang.getString("SeasonName")+":");
    lblQty1.setPreferredSize(new Dimension(90, 20));
    lblQty1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSeason.setPreferredSize(new Dimension(170, 22));
    cboSeason.setMinimumSize(new Dimension(26, 21));
    cboSeason.setDoubleBuffered(false);
    cboSeason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    cboSeason.setEnabled(true);
    lblQty2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblQty2.setPreferredSize(new Dimension(90, 20));
    lblQty2.setText("");
    lblQty3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblQty3.setPreferredSize(new Dimension(90, 20));
    lblQty3.setText(lang.getString("Total")+":");
    lblQty4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblQty4.setPreferredSize(new Dimension(90, 20));
    lblQty4.setText("");
    btnOrderSearch.setPreferredSize(new Dimension(41, 23));
    btnOrderSearch.setText("...");
    btnOrderSearch.addActionListener(new
        PanelGoodsReceipt_btnOrderSearch_actionAdapter(this));
    jPanel7.add(txtReceiptID, null);
    jPanel7.add(btnOrderSearch);
    jPanel7.add(cboStore, null);
    jPanel7.add(btnStoreSearch, null);
    jPanel7.add(cboSupplier, null);
    jPanel7.add(btnSupplier, null);
    jPanel7.add(txtUPC, null);
    jPanel7.add(btnItemSearch, null);
    jPanel7.add(txtQuantity);
    jPanel6.add(lblReceiptID, null);
    jPanel6.add(lblStoreID, null);
    jPanel6.add(lblItemName, null);
    this.add(jPanel1,  BorderLayout.NORTH);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel4,  BorderLayout.WEST);
    jPanel4.add(jPanel6,  BorderLayout.WEST);
    jPanel6.add(lblItemID, null);
    jPanel6.add(lblQty);
    jPanel4.add(jPanel7, BorderLayout.CENTER);
    jPanel4.add(jPanel10,  BorderLayout.EAST);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel8,  BorderLayout.WEST);
    jPanel8.add(lblReceiptDate, null);
    jPanel8.add(lblQty1, null);
    jPanel8.add(lblDescription, null);
    jPanel8.add(lblQty2, null);
    jPanel8.add(lblQty3, null);
    jPanel5.add(jPanel9,  BorderLayout.CENTER);
    jPanel9.add(txtReceiptDate, null);
    jPanel9.add(btnReceiptDateSearch, null);
    jPanel9.add(cboSeason, null);
    jPanel9.add(txtDescription, null);
    jPanel9.add(lblQty4, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    showButton(true);
    initScreen();
    initTable();
    createConnection();
  }
  private void initTable() {
    //define for table
    String[] columnNames = new String[] {
        lang.getString("ItemID"), lang.getString("UPC"), lang.getString("ItemName"), lang.getString("OrderedQty"), lang.getString("ReceivedQty")};
    dm.setDataVector(new Object[][] {}, columnNames);
    table.setColor(Color.lightGray, Color.white);
    table.setCellEditable(COL_QUANTITY);
}


  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_INVENTORY_RECEIPT);
    btnAdd.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnModify.setEnabled(er.getModify());
    btnViewReceipt.setEnabled(er.getReport());
    if(!er.getAdd()){
      ut.showMessage(frmMain, lang.getString("TT002"),
                     lang.getString("MS1085_CanNotUseScreen"));

    }

  }

  private void createConnection(){
    this.bItemMasterBusObj.setDataAccessLayer(DAL) ;
   }

  private String getReceiptID(){
    String receiptID= DAL.getStoreID()+ ut.getSystemDateTime5();
    receiptID = receiptID.replaceAll(":"," ").replaceAll("-"," ").replaceAll(" ","");
    return receiptID;
  }

  void initScreen(){
    lblQty4.setText("");
//    txtReceiptID.setText(getReceiptID()); 16 digits
    txtReceiptID.setText(ut.getDateTimeID()); // 12 digits
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                      TYPE_SCROLL_INSENSITIVE,
                                      ResultSet.CONCUR_READ_ONLY);
    }catch(Exception ex){};
    cboStore.setData(getDataForComboboxStore(stmt));
    cboStore.setSelectedData(DAL.getStoreID());
    cboStore.setEnabled(false); //only receipt to indicated store
    cboSeason.setData(getDataForComboboxSeason(stmt));
    cboSupplier.setData1(getDataForComboboxSupplier(stmt));
    cboSupplier.setSelectedIndex(0);
    try{
      stmt.close();
    }catch(Exception ex){};
  }
  private ResultSet getDataForComboboxSeason(Statement stmt){
    ResultSet rs = null;
    String sqlStr = "select season_id, season_cde from BTM_IM_SEASON order by season_id";
    try {
//      System.out.println(sqlStr);
         rs = DAL.executeScrollQueries(sqlStr,stmt);
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  private ResultSet getDataForComboboxStore(Statement stmt){
      ResultSet rs = null;
//      String sqlStr = "select Store_ID, Name from BTM_IM_STORE where store_type='D' order by name";
      String sqlStr = "select Store_ID, Name from BTM_IM_STORE order by name";

      try {
//        System.out.println(sqlStr);
           rs = DAL.executeScrollQueries(sqlStr,stmt);
      }catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      return rs;
  }
  private ResultSet getDataForComboboxSupplier(Statement stmt){
    ResultSet rs = null;
    String sqlStr = "select supp_id, supp_name from btm_im_supplier order by supp_name asc";
    try{
//      System.out.println(sqlStr);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs ;
  }
//show button
 //isAdd false if modify item
  void showButton(boolean isAdd) {
    this.jPanel1.removeAll();
    this.jPanel1.updateUI();
    jPanel1.add(btnDone, null);
    if(isAdd == true){
      jPanel1.add(btnAdd, null);
    }
    else{
      jPanel1.add(btnModify, null);
    }
    jPanel1.add(btnViewReceipt, null);
    jPanel1.add(btnClear, null);
    jPanel1.add(btnDelete, null);
    jPanel1.add(btnCancel, null);
  }

  void txtQuantity_focusLost(FocusEvent e) {
    if (txtQuantity.isEditable()) {
          if (!txtQuantity.getText().equalsIgnoreCase("")) {
            txtQuantity.setText("" + ut.formatNumber(txtQuantity.getText()));
          }
        }

  }
  void txtQuantity_focusGained(FocusEvent e) {
    if (!txtQuantity.getText().equalsIgnoreCase("")){
      txtQuantity.setText("" + ut.convertToDouble(txtQuantity.getText()));
    }
  }

  //Check exist
  boolean checkExist(String itemID, long storeID){
    ResultSet rs = null;
    try{
      String sql = "select * from btm_im_item_loc_soh where item_id ='" + itemID +
          "' and store_id =" + storeID;
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                           TYPE_SCROLL_INSENSITIVE,
                                           ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      if (rs.next()){
        stmt.close();
        return true;
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return false;
  }

  void txtQuantity_keyTyped(KeyEvent e) {
    ut.checkNumberPositive(e,txtQuantity.getText());
  }

  void txtReceiptDate_keyTyped(KeyEvent e) {
   // ut.limitLenjTextField(e,txtReceiptDate,StockOnHand.LEN_QTY);
  }
//add all items into vInfo
  void btnDone_actionPerformed(ActionEvent e) {
    if (table.getRowCount()>0){
      DAL.setBeginTrans(DAL.getConnection());
      insertGoodsReceiptNote();
      DAL.setEndTrans(DAL.getConnection());
    }
    //Export to PDF file (transfer form)
    if(table.getRowCount()>0){
      printReceipt(txtReceiptID.getText(), cboStore.getSelectedItem().toString(),
                   cboSupplier.getSelectedItem().toString(), table, DAL);
    }

    ///////////////////Tuan Anh
    while(table.getRowCount()>0){
      dm.removeRow(0);
    }
    vInfo.clear();
    vHistSoh.clear();
    resetDataInScreen();
    showButton(true);
    showAdd = true;
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showInventManageButton();
  }
  void printReceipt(String strReceiptID, String strFromStoreRoom,
                     String strSupplier, BJTable tbl, DataAccessLayer DAL) {
        String strHeader[][] = {
            {
            "STOREROOM#" + strFromStoreRoom
            }
        };
        String strTitle[] = {
            "GOODS RECEIPT NOTE"};
        String strInfo[][] = {
            {
            "DATE : " + ut.getSystemDate().substring(0, 10),
            "SUPPLIER: " + strSupplier,
            "RECEIPT ID: " + strReceiptID
            }
        };
        String strFormat[] = {
            "String","String","String","String","String","money","String"
        };

        String strTableTitle[] = {
            "No","UPC","Item Name","Color","Size","Qty","Price"
        };

        String strTableAlign[] = {
            "CENTER", "CENTER", "LEFT", "LEFT", "LEFT","RIGHT","RIGHT"};
        String strTableItem[][] = new String[tbl.getRowCount()][7];
        String st="";

//        int iTableSkew[] = {
//            5,8,34,13,10,10,20};
        int iTableSkew[] = {
            5, 20, 20, 21, 8, 7, 19};
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
          strTableItem[i][3]=getItemColor(tbl.getValueAt(i, COL_ITEM_ID).toString(), DAL);

          //col 4
          strTableItem[i][4]=getItemSize(tbl.getValueAt(i, COL_ITEM_ID).toString(), DAL);

          //col 5
          if (tbl.getValueAt(i, COL_QUANTITY) != null) {
            st = tbl.getValueAt(i, COL_QUANTITY).toString();
          }
          else {
            st = "0";
          }
          strTableItem[i][5]=st;
          //col 6
          strTableItem[i][6]=getItemPrice(tbl.getValueAt(i, COL_ITEM_ID).toString(), DAL);
        }

        PrintReceipt pp = new PrintReceipt();
        pp.setFileName(DAL.getAppHome()+"\\file\\" +
                         strReceiptID +
                         ".pdf");

        //pp.setFileName("./file/"+strReceiptID+".pdf");
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


  //get Item Price
  private String getItemPrice(String strItemID, DataAccessLayer DAL) {
    String strInfo = "";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select UNIT_COST from BTM_IM_ITEM_LOC_SUPPLIER " +
                   "where ITEM_ID = '"+strItemID+"' and STORE_ID='"+DAL.getStoreID()+"'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("UNIT_COST") != null) {
          strInfo = rs.getString("UNIT_COST");
        }
        else {
          strInfo =  "0";
        }
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return strInfo;
  }

  //get Color
  private String getItemColor(String strItemID, DataAccessLayer DAL) {
    String strInfo = "";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select item_id, NEW_PRICE, attr1_name, attr2_name " +
                   "from btm_im_price_hist " +
                   "where item_id ='"+strItemID+"' and STATUS ='1' ";
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
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return strInfo;
  }

  //get Size
  public String getItemSize(String strItemID, DataAccessLayer DAL) {
    String strInfo = "";
    ResultSet rs = null;
    try {
      rs = null;
      String sql =
          "select item_id, NEW_PRICE, attr1_name, attr2_name " +
          "from btm_im_price_hist " +
          "where item_id ='" + strItemID + "' and to_date('" + ut.getSystemDate() + "','" + ut.DATE_FORMAT + "') " +
          "between PRICE_EFF_DATE and PRICE_END_DATE Order by PRICE_EFF_DATE DESC";
//System.out.println(sql);
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
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return strInfo;
  }

  //get Type to receive 'B': Backroom or 'F':Frontroom
  String getReceiveType(String receiptID){
    ResultSet rs = null;
    String receiveType="";
    String sqlStr = "Select FR_BR_FLAG From BTM_IM_INV_ORDER Where ORDER_ID='" + receiptID + "'";
    try {
      rs = DAL.executeQueries(sqlStr);
      if (rs.next()) {
        receiveType = rs.getString("FR_BR_FLAG");
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return receiveType;
  }

  //insert Good receipt (receipt from Supplier) when click Done
  void insertGoodsReceiptNote() {
    String last_update_id = DAL.getEmpID(); //creater_id
    if (!vInfo.isEmpty()){
      for (int i = 0; i < table.getRowCount(); i++) {
        String receiptID = txtReceiptID.getText();
        String quantity = table.getValueAt(i, COL_QUANTITY).toString().trim();
        String itemID = table.getValueAt(i, COL_ITEM_ID).toString();
        String storeID = cboStore.getSelectedData();
        String receiveType = getReceiveType(receiptID);
        if(!checkExistInGrid(i)){
          //update SOH when click Done
          processForTableSOH(itemID, storeID, quantity, txtReceiptDate.getText(),
                           last_update_id,receiveType);
          //insert into BTM_IM_RECEIPT_ITEM
          insertReceiptItem(receiptID, itemID, quantity,cboSeason.getSelectedData(),i);
        }else{
          processSOHForDuplicateItemInGrid(itemID, storeID, quantity,receiveType);
          //update BTM_IM_RECEIPT_ITEM
          updateReceiptItem(receiptID, itemID, quantity);
        }
        //keep history: insert into  BTM_IM_INIT_SOH_HIST
//        insertInitSOHHist(itemID,storeID,quantity);
      }

      try {

//        stmt = DAL.getConnection().createStatement();
        if (!vInfo.isEmpty()){

          DAL.executeBatchQuery(vInfo);

        }
        if (!vHistSoh.isEmpty()){

          DAL.executeBatchQuery(vHistSoh);
        }
        //update PO after receipt
        DAL.executeUpdate("update BTM_IM_INV_ORDER set status='C' where order_id= "+txtReceiptID.getText().trim());

//        stmt.close();
      }
      catch(Exception ex){};
    }
  }

  public boolean checkExistInGrid(int row){
    boolean flag = false;
    for(int i = 0;i < row;i++){
       if (table.getValueAt(row,COL_ITEM_ID).equals(table.getValueAt(i,COL_ITEM_ID))){
         flag = true;
       }
    }
    return flag;
  }

  //update SOH when click Done
  void processForTableSOH(String itemID, String storeID, String quantity,
                          String receiptDate, String last_update_id,String receiveType) {
    ResultSet rs = null;
    String sqlStr = "Select * From BTM_IM_ITEM_LOC_SOH Where Item_ID = '" + itemID + "' and Store_ID=" + storeID;
//    System.out.println(sqlStr);
    ResultSet rsBack = null;
    float nQtyBack=0;
    String sqlQtyBack = "Select BACK_ROOM_QTY From  BTM_IM_ITEM_LOC_SOH Where Item_ID = '" + itemID + "' and Store_ID=" + storeID;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

      rs = DAL.executeScrollQueries(sqlStr,stmt);
      rsBack = DAL.executeQueries(sqlQtyBack);
      if(rsBack.next()){
        nQtyBack = rsBack.getFloat("BACK_ROOM_QTY");
      }
//       System.out.print(nQtyBack);
      if(receiveType.equalsIgnoreCase("B") || receiveType.trim().equals("")){ //receive to Backroom
        if (rs.next()) {
          sqlStr =
              "Update BTM_IM_ITEM_LOC_SOH set STOCK_ON_HAND = STOCK_ON_HAND + " +
              quantity +
              ", BACK_ROOM_QTY = " + nQtyBack + "+" + quantity +
              ",SOH_Update_DateTime = to_date('" + ut.getSystemDate() + "','" +
              ut.DATE_FORMAT +
              "') Where Item_ID = '" + itemID + "' and Store_ID=" + storeID;
        }

        else {
          sqlStr = "Insert Into BTM_IM_ITEM_LOC_SOH(Item_ID,Store_ID,Stock_On_Hand,Back_Room_Qty,Front_Room_Qty,SOH_Update_DateTime,Last_Update_id) values('"
              + itemID + "'," + storeID + "," + quantity + "," + quantity +
              ",0,to_date('" + receiptDate + "','"
              + ut.DATETIME_FORMAT2 + "'),'" + last_update_id + "')";
        }
      }else if(receiveType.equalsIgnoreCase("F")){ //receive to Frontroom
        if (rs.next()) {
          sqlStr =
              "Update BTM_IM_ITEM_LOC_SOH set STOCK_ON_HAND = STOCK_ON_HAND + " +
              quantity +
              ", Front_Room_Qty = Front_Room_Qty +" + quantity +
              ",SOH_Update_DateTime = to_date('" + ut.getSystemDate() + "','" +
              ut.DATE_FORMAT +
              "') Where Item_ID = '" + itemID + "' and Store_ID=" + storeID;
        }
        else {
          sqlStr = "Insert Into BTM_IM_ITEM_LOC_SOH(Item_ID,Store_ID,Stock_On_Hand,Back_Room_Qty,Front_Room_Qty,SOH_Update_DateTime,Last_Update_id) values('"
              + itemID + "'," + storeID + "," + quantity + ",0," + quantity +",to_date('" + receiptDate + "','"
              + ut.DATETIME_FORMAT2 + "'),'" + last_update_id + "')";
        }
      }
      rs.close();
      stmt.close();
//      System.out.println(sqlStr);
      vInfo.addElement(sqlStr);
    }
    catch(Exception ex){}
   }

   //update SOH with available item in grid
   void processSOHForDuplicateItemInGrid(String itemID, String storeID, String quantity,String receiveType) {
     ResultSet rs = null;
     String sqlStr = null;
       if(receiveType.equalsIgnoreCase("B") || receiveType.trim().equals("")){ //receive to Backroom
           sqlStr =
               "Update BTM_IM_ITEM_LOC_SOH set STOCK_ON_HAND = STOCK_ON_HAND + " +
               quantity +
               ", BACK_ROOM_QTY =  BACK_ROOM_QTY +" + quantity +
               ",SOH_Update_DateTime = to_date('" + ut.getSystemDate() + "','" +
               ut.DATE_FORMAT +
               "') Where Item_ID = '" + itemID + "' and Store_ID=" + storeID;
       }else if(receiveType.equalsIgnoreCase("F")){ //receive to Frontroom
           sqlStr =
               "Update BTM_IM_ITEM_LOC_SOH set STOCK_ON_HAND = STOCK_ON_HAND + " +
               quantity +
               ", Front_Room_Qty = Front_Room_Qty +" + quantity +
               ",SOH_Update_DateTime = to_date('" + ut.getSystemDate() + "','" +
               ut.DATE_FORMAT +
               "') Where Item_ID = '" + itemID + "' and Store_ID=" + storeID;
       }
//       System.out.println(sqlStr);
       vInfo.addElement(sqlStr);
    }



//keep history: insert into  BTM_IM_INIT_SOH_HIST
void insertInitSOHHist(String itemID,String storeID,String quantity){
  ResultSet rs = null;
  String sql="select * from BTM_IM_INIT_SOH_HIST where item_id= '" + itemID +"' and store_id ='"+storeID+"'";
  try {
    stmt = DAL.getConnection().createStatement(ResultSet.
                                              TYPE_SCROLL_INSENSITIVE,
                                              ResultSet.CONCUR_READ_ONLY);

    rs = DAL.executeScrollQueries(sql,stmt);
    if (!rs.next()){
      vHistSoh.addElement(
          "insert into BTM_IM_INIT_SOH_HIST (item_id, store_id, soh, soh_update_datetime) " +
          " values('" + itemID + "'," + storeID +
          " , " + quantity + ", to_date ('" +
          ut.getSystemDateTime1() + "','" + ut.DATETIME_FORMAT2 + "'))");
    }
    rs.close();
    stmt.close();
  }
  catch (Exception ex) {
    ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(ex);}
}
   //insert into BTM_IM_RECEIPT_ITEM
   void insertReceiptItem(String receiptID, String itemID, String quantity, String seasonID,int seq) {
     ResultSet rs = null;
     String sqlStr = "Insert Into BTM_IM_RECEIPT_ITEM values('" + receiptID +
         "','" + itemID + "'," + quantity + ",1,"+seq+",'" + seasonID + "')";
//     System.out.println(sqlStr);
     try {
       vInfo.addElement(sqlStr);
//       DAL.executeUpdate(sqlStr);
     }
     catch (Exception ex) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(ex);}
   }

   public void updateReceiptItem(String receiptID,String itemID,String quantity){
     ResultSet rs = null;
     String sqlStr = "Update BTM_IM_RECEIPT_ITEM set QTY=QTY + '"+quantity+"'"
         + " where RECEIPT_ID='"+receiptID+"' and ITEM_ID='"+itemID+"'";
//     System.out.println(sqlStr);
     try {
       vInfo.addElement(sqlStr);
     }
     catch (Exception ex) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(ex);}
   }

  void btnCancel_actionPerformed(ActionEvent e) {
    while(table.getRowCount()>0){
      dm.removeRow(0);
    }
    vInfo.removeAllElements();
    resetDataInScreen();
    showButton(true);
    showAdd = true;;


    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showReceive();
    frmMain.setTitle(lang.getString("TT0017"));
    frmMain.pnlMainSim.btnRecSupplier.requestFocus(true);

  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if (table.getSelectedRow() >= 0) {
      dm.removeRow(table.getSelectedRow());
      itemTotal();
      txtUPC.setText("");
      txtQuantity.setText("");
      showButton(true);
      showAdd = true;
      //remove master record if no item
      if(dm.getRowCount()==0){
        vInfo.remove(0);
      }

    }
    else{
      if(dm.getRowCount()>0)
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1092_SelectItemWantDelete"));
    }
  }

  void btnModify_actionPerformed(ActionEvent e) {
    int row = table.getSelectedRow();
    if(row >= 0){
      if (!checkData()) {
        return;
      }
      double quantity = ut.convertToDouble(txtQuantity.getText());
      table.setValueAt(String.valueOf(quantity), row, COL_QUANTITY);
      itemTotal();
      txtUPC.setText("");
      txtUPC.setEditable(true);
      txtQuantity.setText("");
      table.requestFocus(true);
      showButton(true);
      showAdd = true;
    }
  }

  void table_mouseClicked(MouseEvent e) {
//    if (e.getClickCount() == 2) {
//      int row = table.getSelectedRow();
//      if (row >= 0) {
//        showItemModify(row);
//      }
//    }
  }
 //show info of item to modify
 void showItemModify(int row){
   txtUPC.setText(table.getValueAt(row, COL_ART_NO).toString());
   txtUPC.setEditable(false);
//   txtQuantity.setText(table.getValueAt(row, COL_QUANTITY).toString());
   showButton(false);
   showAdd = false;
   txtQuantity.requestFocus(true);

 }

  void txtUPC_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtUPC,StockOnHand.LEN_ITEM_ID);
    if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
      (e.getKeyChar() != e.VK_BACK_SPACE) && (e.getKeyChar() != e.VK_ENTER))
    e.consume();
    else if (e.getKeyChar() == e.VK_ENTER){
      if (table.getRowCount()>0){
        if (this.txtUPC.getText().length() > 0) {
          updateValueInScreen(this.txtUPC.getText());
        }
      }else{
        if (this.txtUPC.getText().length() > 0) {
          updateValueInScreen1(this.txtUPC.getText());
          getSeason(this.txtUPC.getText());
        }
      }
//      sdgf
      if (!txtUPC.getText().equalsIgnoreCase("")){
        txtQuantity.requestFocus(true);
      }
    }
  }

  void txtUPC_focusLost(FocusEvent e) {

  }

  void updateValueInScreen(String itemCode){
    ResultSet rs = null;
    try {
      rs = (ResultSet)this.bItemMasterBusObj.selectInfoItem(itemCode,supplierID);
      if (rs.next()) {
        if (rs.getString("status").equalsIgnoreCase("W")){
          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1091_ItemIsWorkSheetCanNotReceipt"));
          btnItemSearch.requestFocus(true);
          return;
        }
        itemName = rs.getString("Item_Name");
        articleNo = rs.getString("Art_No");
        String sizeId = rs.getString("Attr2_Dtl_Id");
        if (sizeId == null || sizeId.trim().equals("")) {
          sizeName = "";
        }
        else {
          sizeName = getSizeName(sizeId);
        }

      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1086_ItemValueNotExistInStore"));
//      txtItemName.setText("");
        itemName = "";
        btnItemSearch.requestFocus(true);
        return;
      }
      rs.getStatement().close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    txtQuantity.requestFocus(true);
  }

  void updateValueInScreen1(String itemCode){
    ResultSet rs = null;
    try {

      rs = (ResultSet)this.bItemMasterBusObj.selectInfoItem1(itemCode);
      if (rs.next()) {
        if (rs.getString("status").equalsIgnoreCase("W")){
          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1091_ItemIsWorkSheetCanNotReceipt"));
          btnItemSearch.requestFocus(true);
          return;
        }
        itemName = rs.getString("Item_Name");
        articleNo = rs.getString("Art_No");
        String sizeId = rs.getString("Attr2_Dtl_Id");
        if (sizeId == null || sizeId.trim().equals("")) {
          sizeName = "";
        }
        else {
          sizeName = getSizeName(sizeId);
        }
        cboSupplier.setSelectedData(rs.getString("supp_id"));
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1086_ItemValueNotExistInStore"));
//      txtItemName.setText("");
        itemName = "";
        btnItemSearch.requestFocus(true);
        return;
      }
      rs.getStatement().close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    txtQuantity.requestFocus(true);
  }

  private String getSizeName(String sizeId){
    String name = "";
    ResultSet rs = null;
    String sqlStr = "Select Attr_Dtl_Desc From BTM_IM_ATTR_DTL Where Attr_dtl_id = '" + sizeId + "'";
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                               TYPE_SCROLL_INSENSITIVE,
                                               ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
      if(rs.next()){
        name = rs.getString("Attr_Dtl_Desc");
      }
      rs.close();
      stmt.close();
    }catch(Exception ex){};
    return name;
  }


  //Add only master record into vInfo
  void btnAdd_actionPerformed(ActionEvent e) {
    ResultSet rs = null;
    boolean bLag=true;//true if item is available( Approved )
    if (txtUPC.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1090_WrongUPCOrSize"));
      txtUPC.requestFocus(true);
      return;
    }
    if (!checkData()){
      return;
    }

    try {
      if (table.getRowCount() > 0) {
        if (this.txtUPC.getText().length() > 0) {
          updateValueInScreen(this.txtUPC.getText());
        }
      }
      else {
        if (this.txtUPC.getText().length() > 0) {
          updateValueInScreen1(this.txtUPC.getText());
          getSeason(this.txtUPC.getText());
        }
      }
      if (!txtUPC.getText().equalsIgnoreCase("")) {
        txtQuantity.requestFocus(true);
      }

      String supID = cboSupplier.getSelectedData();
      rs = (ResultSet)this.bItemMasterBusObj.selectInfoItem(this.txtUPC.
          getText(), supID);
      if (rs.next()) {
        if (rs.getString("status").equalsIgnoreCase("W")) {
          bLag = false;
        }
      }
      else {
        bLag = false;
      }
      rs.getStatement().close();
      if (bLag) {
        addDataIntoTable();
        itemTotal();
        if (table.flagSortable) {
          table.sortButton();
        }
      }
      //Add new

    }catch (Exception ex) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(ex);
   }

  }
  void itemTotal(){
    if (table.getRowCount()>0){
      double totalQty = 0;
      for(int i=0; i<table.getRowCount(); i++){
        totalQty = totalQty + ut.convertToDouble(table.getValueAt(i,COL_QUANTITY).toString());
      }
      lblQty4.setText("" + totalQty);
    }else{
      lblQty4.setText("");
    }
  }

  //return true if valid data
  boolean checkData(){

//    if(cboStore.getSelectedIndex()==0){
//      ut.showMessage(this.frmMain,lang.getString("TT013"),lang.getString("MS1089_StoreRequired"));
//      cboStore.requestFocus(true) ;
//      return false;
//    }
    if ( txtQuantity.getText().trim().length() == 0) {
        ut.showMessage(this.frmMain,lang.getString("TT013"),lang.getString("MS1088_InputQuantity"));
        txtQuantity.requestFocus(true) ;
        return false;
      }
    if (txtQuantity.getText().trim().length() > 12) {
       ut.showMessage(this.frmMain,lang.getString("TT013"),lang.getString("MS1088_InputQuantity"));
       txtQuantity.requestFocus(true) ;
       return false;
     }
     if ( txtUPC.getText().length() == 0) {
       ut.showMessage(this.frmMain,lang.getString("TT013"),lang.getString("MS1087_LenghOfItemCodeGreater0"));
       txtUPC.requestFocus(true) ;
       return false;
     }

     if (cboSupplier.getSelectedData().trim().length() == 0) {
       ut.showMessage(frmMain, lang.getString("TT001"),
                      lang.getString("MS1086_ItemValueNotExistInStore"));
       itemName = "";
       txtUPC.requestFocus(true);
       return false;
     }

    return true;
  }

//add data when click Add button
  void addDataIntoTable(){
    String itemID=bItemMasterBusObj.getIDfromUPC(txtUPC.getText().trim());
    supplierID = cboSupplier.getSelectedData();
    String suppName = cboSupplier.getSelectedItem().toString();
    double quantity = ut.convertToDouble(txtQuantity.getText());
    try {
      if (table.getRowCount()<1){
        cboStore.setEnabled(false);
        cboSupplier.setEnabled(false);
        cboSeason.setEnabled(false);
        txtDescription.setEditable(false);
        txtReceiptDate.setEditable(false);
        btnStoreSearch.setEnabled(false);
        btnSupplier.setEnabled(false);
        btnReceiptDateSearch.setEnabled(false);
//        System.out.println("Insert into btm_im_receipt values('" + txtReceiptID.getText() +
//                         "'," + Long.parseLong(cboStore.getSelectedData()) + ",'" +
//                         cboSupplier.getSelectedData() + "','" + DAL.getEmpID() +
//                         "',to_date('" + txtReceiptDate.getText() + "','" + ut.DATE_FORMAT2 + "'),'" +
//                         txtDescription.getText() + "')");
        vInfo.addElement("Insert into btm_im_receipt values('" + txtReceiptID.getText() +
                         "'," + Long.parseLong(cboStore.getSelectedData()) + ",'" +
                         cboSupplier.getSelectedData() + "','" + DAL.getEmpID() +
                         "',to_date('" + txtReceiptDate.getText() + "','" + ut.DATE_FORMAT2 + "'),'" +
                         txtDescription.getText() + "')");

      }
      //update quantity of item if exist
      if (updateItem(itemID,ut.convertToDouble(txtQuantity.getText()))){
        txtUPC.setText("");
        txtQuantity.setText("");
        txtUPC.requestFocus(true);
        return;
      }
      //insert  item if does not  exist
      dm.addRow(new Object[]{itemID,articleNo,itemName,sizeName,String.valueOf(quantity)});
      txtUPC.setText("");
      txtQuantity.setText("");
      txtUPC.requestFocus(true);
    //set focus for last row
      if (table.getRowCount()>0){
        table.setRowSelectionInterval(table.getRowCount()-1,table.getRowCount()-1);
      }
      //show current row
      Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
      jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

    }
    catch (Exception e) {
        DAL.setRollback(DAL.getConnection() ) ;
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }
  }
  //update item if exist: quantity = old quantity + input quantity
  boolean updateItem(String itemID, double qty) {
    for (int i = 0; i < dm.getRowCount(); i++) {
      String item = "" + table.getValueAt(i, COL_ITEM_ID);
      if (itemID.equalsIgnoreCase(item)) {
        double quantity = qty;
        quantity = quantity + ut.convertToDouble(table.getValueAt(i,COL_QUANTITY).toString());
        table.setValueAt(new Double(quantity), i, COL_QUANTITY);
        return true;
      }
    }
    return false;
  }

  private void resetDataInScreen() {
    supplierID = "";
    txtUPC.setText("");
    itemName = "";
    cboStore.setSelectedData(DAL.getStoreID());
    cboSupplier.setSelectedIndex(0);
    txtQuantity.setText("");
    txtDescription.setText("");
    txtReceiptDate.setText(ut.getSystemDate(1));
    txtReceiptID.setText(ut.getDateTimeID());
    cboStore.setEnabled(false);
    cboSupplier.setEnabled(true);
    cboSeason.setEnabled(true);
    txtDescription.setEditable(true);
    btnStoreSearch.setEnabled(true);
    btnSupplier.setEnabled(true);
//disable     btnReceiptDateSearch.setEnabled(true);

  }


  void btnReceiptDateSearch_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnReceiptDateSearch.getX() + posXFrame + 90;
    posY = this.btnReceiptDateSearch.getY() + posYFrame + 90;
    TimeDlg receiptdate = new TimeDlg(null);
    receiptdate.setTitle(lang.getString("ChooseDate"));
    receiptdate.setResizable(false);
    receiptdate.setLocation(posX, posY);
    receiptdate.setVisible(true);
    if (receiptdate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          ut.DATE_FORMAT1);
      String date = fd.format(receiptdate.getDate());
      this.txtReceiptDate.setText(date);
    }
  }

  void btnClear_actionPerformed(ActionEvent e) {
    txtUPC.setText("");
    txtUPC.setEditable(true);
    txtQuantity.setText("");
    showButton(true);
    showAdd = true;
  }

  void cboStore_focusLost(FocusEvent e) {

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
        if(showAdd == true){
          btnAdd.doClick();
        }
      }
      else if (identifier.intValue() == KeyEvent.VK_F4) {
        if(showAdd == false){
          btnModify.doClick();
        }else{
          int row = table.getSelectedRow();
          if (row >= 0) {
            showItemModify(row);
          }
        }

      }
      else if (identifier.intValue() == KeyEvent.VK_F5) {
        btnViewReceipt.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F7) {
        btnClear.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }

      else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void cboStore_actionPerformed(ActionEvent e) {
    if(cboStore.getSelectedIndex()>0){
      txtUPC.setText("");
//      txtItemName.setText("");
      itemName = "";
    }
  }

  void btnViewReceipt_actionPerformed(ActionEvent e) {
    //set info after
    while (table.getRowCount() > 0) {
      dm.removeRow(0);
    }
    vInfo.removeAllElements();
    resetDataInScreen();
    showButton(true);
    showAdd = true;

    ReceiptBusObj receiptBusObject = new ReceiptBusObj();
    DialogReceiptSearch dlgReceiptSearch = new DialogReceiptSearch(frmMain,lang.getString("TT0102"),true, frmMain,receiptBusObject);
    dlgReceiptSearch.setVisible(true);
    if(dlgReceiptSearch.done){
      frmMain.showScreen(Constant.SCRS_VIEW_RECEIPT_DETAILS);
    }else{
      frmMain.showScreen(Constant.SCRS_GOOD_RECEIPT);
     }
    //show View Receipt screen
//    frmMain.showScreen(Constant.SCRS_VIEW_RECEIPT);
//    frmMain.pnlViewReceipt.txtReceiptDate.requestFocus(true);
  }

  void btnStoreSearch_actionPerformed(ActionEvent e) {
    StoreBusObj store = new StoreBusObj();
    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,lang.getString("TT0004"),true,frmMain,store);
    dlgStoreSearch.setVisible(true);
    if (dlgStoreSearch.done){
      cboStore.setSelectedItem(dlgStoreSearch.getStoreName());
    }
  }

  void btnItemSearch_actionPerformed(ActionEvent e) {
      btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
      DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
          lang.getString("TT0016"), true, frmMain, itemBusObject);
      if (!cboSupplier.isEnabled()){
        dlgItemLookup.txtSupplieName.setText(cboSupplier.getSelectedItem().
                                             toString());
        dlgItemLookup.txtSupplieName.setEnabled(false);
      }
      dlgItemLookup.setVisible(true);
      if (dlgItemLookup.done) {
        txtUPC.setText(dlgItemLookup.getUPC());
//      txtItemName.setText(dlgItemLookup.getItemName());
        itemName = dlgItemLookup.getItemName();
        if (table.getRowCount()>0){
          if (this.txtUPC.getText().length() > 0) {
            updateValueInScreen(this.txtUPC.getText());
          }
        }else{
          if (this.txtUPC.getText().length() > 0) {
            updateValueInScreen1(this.txtUPC.getText());
            getSeason(this.txtUPC.getText());
          }
        }
        txtQuantity.requestFocus(true);
      }
  }
  void getSeason(String itemID){
    ResultSet rs = null;
    try{
      String sql = "select season_id from btm_im_item_season where item_id = '" + itemID + "' order by last_upd_datetime desc";
      rs = DAL.executeQueries(sql);
      if (rs.next()){
        cboSeason.setSelectedData(rs.getString("season_id"));
      }
      rs.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  void btnSupplier_actionPerformed(ActionEvent e) {
    SupplierBusObj suppBusObj = new SupplierBusObj();
    DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(frmMain,lang.getString("TT0013"),true,frmMain,suppBusObj);
    dlgSupplierSearch.setVisible(true);
    if (dlgSupplierSearch.done){
//      System.out.println(dlgSupplierSearch.getData());
      cboSupplier.setSelectedItem(dlgSupplierSearch.getSuppName());
    }

  }

  void cboSupplier_keyTyped(KeyEvent e) {
    if (e.getKeyChar() != KeyEvent.VK_ENTER){
//      cboSupplier.showPopup();

    }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      if (showAdd == true) {
        btnAdd.doClick();
      }
      else {
        btnModify.doClick();
      }
    }
    char key = e.getKeyChar();
    String sNode = table.getValueAt(table.getSelectedRow(), COL_QUANTITY).toString();
    if ( ( (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
          (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) || (e.getKeyCode() == KeyEvent.VK_DELETE) ||
          (key == '-' && sNode.equals("")) ||
          (key == '.' && ut.countDot(sNode) == 0 && !sNode.equals("")))
        && (table.getSelectedColumn() == COL_QUANTITY)) {
      if (IS_ENTER == true) {
        IS_ENTER = false; // modifying
        table.setValueAt("", table.getSelectedRow(),COL_QUANTITY);
      }
    }
    else {
      if (e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_UP &&
          e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT &&
          e.getKeyCode() != KeyEvent.VK_ENTER)
        e.consume();
    }
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      IS_ENTER = true;
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

  void btnItemSearch_focusLost(FocusEvent e) {
//      txtUPC.requestFocus(true);
  }

  public void btnOrderSearch_actionPerformed(ActionEvent e) {
    fromPO=true;

    PurchaseOrderBusObj poBusObject = new PurchaseOrderBusObj();
    DialogPOSearch dlgPOSearch = new DialogPOSearch(frmMain,lang.getString("TT0103"),true, frmMain,poBusObject);
    dlgPOSearch.cboStatus.setSelectedItem("Approve");
    dlgPOSearch.cboStatus.setEnabled(false);
    if (!cboSupplier.getSelectedItem().toString().equalsIgnoreCase("None")){
      dlgPOSearch.txtSupplier.setText(cboSupplier.getSelectedItem().toString());
      dlgPOSearch.txtSupplier.setEnabled(false);
    }
    dlgPOSearch.txtStore.setText(frmMain.storeBusObj.getStoreName(DAL,cboStore.getSelectedData()));
    dlgPOSearch.txtStore.setEnabled(false);
    dlgPOSearch.btnStore.setEnabled(false);
    dlgPOSearch.setVisible(true);

    if(!dlgPOSearch.getPoID().equals("")){
      if (!checkPO(dlgPOSearch.getPoID())) {
        txtReceiptID.setText(dlgPOSearch.getPoID());
        addDataFromPO(dlgPOSearch.getPoID());

      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1197_PO_receipted"));
      }
    }
  }



  /** Read data from selected PO into grid
   * @author 	Trung.Nguyen
   * @param 	orderID identify of order
   * @return	no
   */

  void addDataFromPO(String orderID){
    String sql="";
//    int total=0;//number of items in PO
    ResultSet rs1 ;
    ResultSet rs2;

    //reset component and vector
    txtUPC.setText("");
    txtQuantity.setText("");
    txtUPC.requestFocus(true);
    vHistSoh.clear();
    vInfo.clear();
    try {
      //show supplier
      sql = "select sup.SUPP_ID,sup.SUPP_NAME from BTM_IM_INV_ORDER odr,BTM_IM_SUPPLIER sup where odr.supp_id=sup.SUPP_ID and order_id= " +
          orderID;
//      System.out.println(sql);
      rs1 = DAL.executeQueries(sql);
      if (rs1.next()) {
        cboSupplier.setSelectedData(rs1.getString("SUPP_ID"));
        supplierID = cboSupplier.getSelectedData();
      }
      rs1.getStatement().close();

      //disable some component
      cboStore.setEnabled(false);
      cboSupplier.setEnabled(false);
      cboSeason.setEnabled(false);
      txtDescription.setEditable(false);
      txtReceiptDate.setEditable(false);
      btnStoreSearch.setEnabled(false);
      btnSupplier.setEnabled(false);
      btnReceiptDateSearch.setEnabled(false);

      //insert into master Vector
        sql="Insert into btm_im_receipt values('" + txtReceiptID.getText() +
                         "'," + Long.parseLong(cboStore.getSelectedData()) + ",'" +
                         cboSupplier.getSelectedData() + "','" + DAL.getEmpID() +
                         "',to_date('" + txtReceiptDate.getText() + "','" + ut.DATE_FORMAT2 + "'),'" +
                         txtDescription.getText() + "')";
        vInfo.addElement(sql);
//        System.out.println(sql);

      //insert items into grid
      sql = "select odr.item_id,odr.QTY_EXPECTED,odr.UNIT_COST,im.ART_NO,im.ITEM_NAME from BTM_IM_INV_ORDER_ITEM odr,BTM_IM_ITEM_MASTER im where odr.ITEM_ID=im.ITEM_ID and  order_ID = " + orderID +" order by upper(im.ITEM_NAME)";
//      System.out.println(sql);
      rs2 = DAL.executeQueries(sql);

      while (rs2.next()) {
        dm.addRow(new Object[]{rs2.getString("item_id"),rs2.getString("ART_NO"),rs2.getString("ITEM_NAME"),rs2.getString("QTY_EXPECTED"),"0"});

      }
      itemTotal();
      rs2.getStatement().close();

    //set focus for first row
      if (table.getRowCount()>0){
        table.setRowSelectionInterval(0,0);
      }
    }
    catch (Exception e) {
        DAL.setRollback(DAL.getConnection() ) ;
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }
  }
  //Check whether receipt PO or not
  //orderID  id of PO
  //return true if PO did receipt
  boolean checkPO(String orderID) {
    ResultSet rs = null;
    boolean isExist = false;
    String sql =
        "select * from BTM_IM_RECEIPT where receipt_id=" + orderID;
//    System.out.println(sql);
    try {

      rs = DAL.executeQueries(sql);

      if (rs.next()) {
        isExist = true;
      }
      rs.getStatement().close();

    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return isExist;
  }
  void updateReceiveQty(){
    double total=0;
    if (table.getSelectedRow() > 0 && (table.getValueAt(table.getSelectedRow()-1, COL_QUANTITY).equals("")
        || !ut.isDoubleString(table.getValueAt(table.getSelectedRow()-1, COL_QUANTITY).toString()))) {
      table.setValueAt("0", table.getSelectedRow()-1, COL_QUANTITY);
   }
   if (table.getRowCount() > 0 && (table.getValueAt(table.getRowCount()-1, COL_QUANTITY).equals("")
      || !ut.isDoubleString(table.getValueAt(table.getRowCount()-1, COL_QUANTITY).toString()))) {
      table.setValueAt("0", table.getRowCount()-1, COL_QUANTITY);
   }
   for(int i=0;i<table.getRowCount();i++){
        total += ut.convertToDouble(table.getValueAt(i, COL_QUANTITY).toString());
    }
    lblQty4.setText(ut.formatNumberTo2DigitsDecimal(total));
  }
  void table_keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      updateReceiveQty();
    }
  }

}

class PanelGoodsReceipt_btnOrderSearch_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelGoodsReceipt adaptee;
  PanelGoodsReceipt_btnOrderSearch_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnOrderSearch_actionPerformed(e);
  }
}

class PanelGoodsReceipt_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnAdd_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelGoodsReceipt_txtQuantity_focusAdapter extends java.awt.event.FocusAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_txtQuantity_focusAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtQuantity_focusLost(e);
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtQuantity_focusGained(e);
  }

}

class PanelGoodsReceipt_txtQuantity_keyAdapter extends java.awt.event.KeyAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_txtQuantity_keyAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtQuantity_keyTyped(e);
  }
}

class PanelGoodsReceipt_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnDone_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelGoodsReceipt_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnCancel_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelGoodsReceipt_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnDelete_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelGoodsReceipt_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnModify_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelGoodsReceipt_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_table_mouseAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelGoodsReceipt_txtUPC_keyAdapter extends java.awt.event.KeyAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_txtUPC_keyAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }
}

class PanelGoodsReceipt_txtUPC_focusAdapter extends java.awt.event.FocusAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_txtUPC_focusAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtUPC_focusLost(e);
  }
}

class PanelGoodsReceipt_btnReceiptDateSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnReceiptDateSearch_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceiptDateSearch_actionPerformed(e);
  }
}

class PanelGoodsReceipt_btnClear_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnClear_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelGoodsReceipt_cboStore_focusAdapter extends java.awt.event.FocusAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_cboStore_focusAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.cboStore_focusLost(e);
  }
}

class PanelGoodsReceipt_cboStore_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_cboStore_mouseAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
}

class PanelGoodsReceipt_cboStore_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_cboStore_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboStore_actionPerformed(e);
  }
}

class PanelGoodsReceipt_btnViewReceipt_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnViewReceipt_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnViewReceipt_actionPerformed(e);
  }
}

class PanelGoodsReceipt_btnStoreSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnStoreSearch_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnStoreSearch_actionPerformed(e);
  }
}

class PanelGoodsReceipt_btnItemSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnItemSearch_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnItemSearch_actionPerformed(e);
  }
}

class PanelGoodsReceipt_btnSupplier_actionAdapter implements java.awt.event.ActionListener {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnSupplier_actionAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplier_actionPerformed(e);
  }
}

class PanelGoodsReceipt_cboSupplier_keyAdapter extends java.awt.event.KeyAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_cboSupplier_keyAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboSupplier_keyTyped(e);
  }
}

class PanelGoodsReceipt_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_table_keyAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
  public void keyReleased(KeyEvent e) {
    adaptee.table_keyReleased(e);
  }
}

class PanelGoodsReceipt_btnItemSearch_focusAdapter extends java.awt.event.FocusAdapter {
  PanelGoodsReceipt adaptee;

  PanelGoodsReceipt_btnItemSearch_focusAdapter(PanelGoodsReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.btnItemSearch_focusLost(e);
  }
}
