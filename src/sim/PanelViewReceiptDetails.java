package sim;

/**
 * <p>Title: </p>
 * <p>Description: Receive > From Supplier> View> Click row </p>
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
import javax.swing.border.*;
import btm.business.*;
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
import java.util.ResourceBundle;
//call from Receipt From Supplier
public class PanelViewReceiptDetails extends JPanel {
  ResourceBundle lang;
  //Declare Data Access Layer
  DataAccessLayer DAL;
  //Declare Utils
  Utils ut = new Utils();
  //Declare Frame Main
  FrameMainSim frmMain;
  //Declare a batch update
  Statement stmt = null;
  int recordCount = 0;
  Vector vInfo = new Vector();//Vector save array info of Receipt Item
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
        case 4: return Long.class;
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
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
  JLabel lblStoreID = new JLabel();
  JLabel lblReceiptDate = new JLabel();
  BJButton btnCancel = new BJButton();
  BJButton btnPrint = new BJButton();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  GridLayout gridLayout1 = new GridLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel lblReceiptID = new JLabel();
  JTextField txtReceiptID = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  JTextField txtReceiptDate = new JTextField();
  JLabel lblDescription = new JLabel();
  JTextArea txtDescription = new JTextArea();
  TitledBorder titledBorder1;
  JLabel lblISupplier = new JLabel();
  JTextField txtSupplier = new JTextField();
  boolean flagSetHotKeyForAdd = true;
  JTextField txtStore = new JTextField();
  JPanel jPanel11 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jPanel12 = new JPanel();
  JPanel jPanel13 = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jPanel14 = new JPanel();
  JPanel jPanel15 = new JPanel();
  JPanel jPanel16 = new JPanel();
  JLabel jLabel1 = new JLabel();
  FlowLayout flowLayout5 = new FlowLayout();
  JTextField txtItemID = new JTextField();
  JPanel jPanel17 = new JPanel();
  JPanel jPanel18 = new JPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField txtQuantity = new JTextField();
  BJComboBox cboReason = new BJComboBox();
  FlowLayout flowLayout6 = new FlowLayout();
  BJButton btnDone = new BJButton();
  BJButton btnAdd = new BJButton();
  FlowLayout flowLayout7 = new FlowLayout();
  JButton jButton1 = new JButton();
  String itemName = "";
  JLabel jLabel4 = new JLabel();
  BJComboBox cboSeason = new BJComboBox();
  JLabel jLabel5 = new JLabel();
  JTextField txtReceiptTo = new JTextField();

  public PanelViewReceiptDetails(FrameMainSim frmMain) {
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
    jPanel3.setPreferredSize(new Dimension(800, 370));
    jPanel3.setLayout(gridLayout1);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(760, 370));
    jPanel4.setPreferredSize(new Dimension(400, 150));
    jPanel4.setLayout(borderLayout3);
    jPanel5.setMinimumSize(new Dimension(300, 150));
    jPanel5.setLayout(borderLayout4);
    jPanel6.setPreferredSize(new Dimension(120, 150));
    jPanel6.setLayout(flowLayout1);
    jPanel7.setPreferredSize(new Dimension(180, 150));
    jPanel7.setLayout(flowLayout4);
    jPanel10.setPreferredSize(new Dimension(50, 150));
    jPanel8.setPreferredSize(new Dimension(100, 150));
    jPanel8.setLayout(flowLayout2);
    jPanel9.setPreferredSize(new Dimension(180, 150));
    jPanel9.setLayout(flowLayout3);
    lblStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStoreID.setPreferredSize(new Dimension(90, 20));
    lblStoreID.setText(lang.getString("StoreID"));
    lblReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReceiptDate.setPreferredSize(new Dimension(90, 20));
    lblReceiptDate.setText(lang.getString("ReceiptDate"));
    btnCancel.setMaximumSize(new Dimension(2147483647, 41));
    btnCancel.setMinimumSize(new Dimension(79, 41));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Back")+" (F12)");
    btnCancel.setVerifyInputWhenFocusTarget(true);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelViewReceiptDetails_btnCancel_actionAdapter(this));
    btnPrint.setPreferredSize(new Dimension(120, 37));
    btnPrint.setToolTipText(lang.getString("Print")+" (F3)");
    btnPrint.setText("<html><center><b>"+lang.getString("Print")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnPrint.addActionListener(new PanelViewReceiptDetails_btnPrint_actionAdapter(this));
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    lblReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReceiptID.setPreferredSize(new Dimension(90, 20));
    lblReceiptID.setText(lang.getString("ReceiptID") );
    txtReceiptID.setBackground(SystemColor.control);
    txtReceiptID.setEnabled(false);
    txtReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtReceiptID.setPreferredSize(new Dimension(225, 20));
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    txtReceiptDate.setBackground(SystemColor.control);
    txtReceiptDate.setEnabled(false);
    txtReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtReceiptDate.setMinimumSize(new Dimension(6, 21));
    txtReceiptDate.setPreferredSize(new Dimension(225, 20));
    txtReceiptDate.setToolTipText("");
    txtReceiptDate.setText("");
//        txtReceiptDate.setText(ut.getSystemDate2());
    lblDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblDescription.setPreferredSize(new Dimension(90, 20));
    lblDescription.setText(lang.getString("Description"));
    txtDescription.setBackground(SystemColor.control);
    txtDescription.setEnabled(false);
    txtDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDescription.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDescription.setDebugGraphicsOptions(0);
    txtDescription.setPreferredSize(new Dimension(225, 20));
    lblISupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblISupplier.setPreferredSize(new Dimension(90, 20));
    lblISupplier.setText(lang.getString("SupplierName"));
    txtSupplier.setBackground(SystemColor.control);
    txtSupplier.setEnabled(false);
    txtSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtSupplier.setPreferredSize(new Dimension(225, 20));
    txtSupplier.setText("");
    txtStore.setText("");
    txtStore.setPreferredSize(new Dimension(225, 20));
    txtStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtStore.setEnabled(false);
    txtStore.setBackground(SystemColor.control);
    table.addKeyListener(new PanelViewReceiptDetails_table_keyAdapter(this));
    jPanel11.setPreferredSize(new Dimension(800, 50));
    jPanel11.setLayout(borderLayout5);
    jPanel12.setPreferredSize(new Dimension(400, 50));
    jPanel12.setLayout(borderLayout6);
    jPanel13.setPreferredSize(new Dimension(400, 50));
    jPanel13.setLayout(borderLayout7);
    jPanel14.setPreferredSize(new Dimension(120, 50));
    jPanel14.setLayout(flowLayout5);
    jPanel15.setPreferredSize(new Dimension(180, 50));
    jPanel15.setLayout(flowLayout7);
    jPanel16.setPreferredSize(new Dimension(50, 50));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(90, 20));
    jLabel1.setText(lang.getString("UPC"));
    flowLayout5.setAlignment(FlowLayout.RIGHT);
    txtItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemID.setPreferredSize(new Dimension(185, 20));
    txtItemID.setText("");
    txtItemID.addKeyListener(new PanelViewReceiptDetails_txtItemID_keyAdapter(this));
    jPanel17.setPreferredSize(new Dimension(100, 50));
    jPanel18.setPreferredSize(new Dimension(180, 50));
    jPanel18.setLayout(flowLayout6);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(90, 20));
    jLabel2.setText(lang.getString("Reason"));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(90, 20));
    jLabel3.setText(lang.getString("Quantity") );
    txtQuantity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtQuantity.setPreferredSize(new Dimension(225, 20));
    txtQuantity.setText("");
    txtQuantity.addKeyListener(new PanelViewReceiptDetails_txtQuantity_keyAdapter(this));
    cboReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboReason.setPreferredSize(new Dimension(225, 20));
    flowLayout6.setAlignment(FlowLayout.LEFT);
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.setToolTipText("Print(F1)");
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setText("<html><center><b> "+lang.getString("Done") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelViewReceiptDetails_btnDone_actionAdapter(this));
    btnDone.setToolTipText("Print(F1)");
    btnDone.setActionCommand("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+ "(F2)");
    btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setText("<html><center><b>" +lang.getString("Add") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelViewReceiptDetails_btnAdd_actionAdapter(this));
    flowLayout7.setAlignment(FlowLayout.LEFT);
    jButton1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jButton1.setMinimumSize(new Dimension(43, 25));
    jButton1.setPreferredSize(new Dimension(35, 22));
    jButton1.setText("...");
    jButton1.addActionListener(new PanelViewReceiptDetails_jButton1_actionAdapter(this));
    jLabel4.setText(lang.getString("SeasonName"));
    jLabel4.setPreferredSize(new Dimension(90, 20));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSeason.setPreferredSize(new Dimension(225, 20));
    cboSeason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    table.addMouseListener(new PanelViewReceiptDetails_table_mouseAdapter(this));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setPreferredSize(new Dimension(90, 20));
    jLabel5.setText(lang.getString("ReceiptTo"));
    txtReceiptTo.setBackground(SystemColor.control);
    txtReceiptTo.setEnabled(false);
    txtReceiptTo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtReceiptTo.setPreferredSize(new Dimension(225, 20));
    txtReceiptTo.setText("");
    jPanel7.add(txtReceiptID, null);
    jPanel7.add(txtStore, null);
    jPanel7.add(txtSupplier, null);
    jPanel6.add(lblReceiptID, null);
    jPanel6.add(lblStoreID, null);
    jPanel6.add(lblISupplier, null);
    this.add(jPanel1,  BorderLayout.NORTH);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel4,  BorderLayout.WEST);
    jPanel4.add(jPanel6,  BorderLayout.WEST);
    jPanel4.add(jPanel7,  BorderLayout.CENTER);
    jPanel4.add(jPanel10,  BorderLayout.EAST);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel8,  BorderLayout.WEST);
    jPanel8.add(lblReceiptDate, null);
    jPanel8.add(jLabel5, null);
    jPanel8.add(lblDescription, null);
    jPanel5.add(jPanel9,  BorderLayout.CENTER);
    jPanel9.add(txtReceiptDate, null);
    jPanel9.add(txtReceiptTo, null);
    jPanel9.add(txtDescription, null);
    jPanel2.add(jPanel11, BorderLayout.NORTH);
    jPanel11.add(jPanel12,  BorderLayout.WEST);
    jPanel12.add(jPanel14,  BorderLayout.WEST);
    jPanel14.add(jLabel1, null);
    jPanel14.add(jLabel4, null);
    jPanel12.add(jPanel15,  BorderLayout.CENTER);
    jPanel15.add(txtItemID, null);
    jPanel15.add(jButton1, null);
    jPanel15.add(cboSeason, null);
    jPanel12.add(jPanel16,  BorderLayout.EAST);
    jPanel11.add(jPanel13,  BorderLayout.CENTER);
    jPanel13.add(jPanel17, BorderLayout.WEST);
    jPanel17.add(jLabel3, null);
    jPanel17.add(jLabel2, null);
    jPanel13.add(jPanel18, BorderLayout.CENTER);
    jPanel18.add(txtQuantity, null);
    jPanel18.add(cboReason, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    jPanel1.add(btnDone, null);
    jPanel1.add(btnAdd, null);
    jPanel1.add(btnPrint, null);
    jPanel1.add(btnCancel, null);
//    dm.addColumn("Item ID");
//    dm.addColumn("Item Name");
//    dm.addColumn("Quantity");
//    table.setRowHeight(30);
//    table.setColor(Color.lightGray, Color.white);
//    createConnection();

    String[] columnNames = new String[]{"Item ID","Item Name","Quantity"};
    dm.setDataVector(new Object[][]{},columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn("Item ID").setPreferredWidth(150);
    table.getColumn("Item Name").setPreferredWidth(300);
    table.getColumn("Quantity").setPreferredWidth(350);
    createConnection();
  }
  private void createConnection(){
    this.bItemMasterBusObj.setDataAccessLayer(DAL) ;
}

  String getReceiptID(){
    String receiptID= DAL.getStoreID() + ut.getSystemDateTime5();
    receiptID = receiptID.replaceAll(":"," ").replaceAll("-"," ").replaceAll(" ","");
    return receiptID;
  }
  //Enable edit  only today
  void initScreen(){
//    txtReceiptID.setText(getReceiptID());
    if(!txtReceiptDate.getText().equals("")){
      String receiptDate=txtReceiptDate.getText().substring(0,txtReceiptDate.getText().indexOf(" "));
      if (ut.compareDate(ut.getDD_MM_YYYY(ut.getSystemDate()),
                         ut.getDD_MM_YYYY2(receiptDate))) {
        btnAdd.setEnabled(false);
        btnDone.setEnabled(false);
      }else{
        btnAdd.setEnabled(true);
        btnDone.setEnabled(true);

      }
    }
//    txtStoreID.setText(getDataForComboboxStore());
  }
  ResultSet getDataForComboboxStore(Statement stmt){
      ResultSet rs = null;
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

  void showButton(boolean flagSetButton) {
    this.jPanel1.removeAll();
    this.jPanel1.updateUI();
    jPanel1.add(btnPrint, null);
    jPanel1.add(btnCancel, null);
  }

  void txtQuantity_focusLost(FocusEvent e) {

  }
  //Get data for combo box
  ResultSet getComboData(Statement stmt){
    ResultSet rs = null;
    try{
      String sql = "Select store_id, name from btm_im_store order by name";
      rs = DAL.executeScrollQueries(sql,stmt);
    }catch (Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  //Check exist
  boolean checkExist(String itemID, long storeID){
    ResultSet rs = null;
    try{
      String sql = "select * from btm_im_item_loc_soh where item_id ='" + itemID +
          "' and store_id =" + storeID;
      stmt = DAL.getConnection().createStatement(ResultSet.
                                               TYPE_SCROLL_INSENSITIVE,
                                               ResultSet.CONCUR_READ_ONLY);

      rs = DAL.executeQueries(sql,stmt);
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

  void txtReceiptDate_keyTyped(KeyEvent e) {
   // ut.limitLenjTextField(e,txtReceiptDate,StockOnHand.LEN_QTY);
  }


  void btnCancel_actionPerformed(ActionEvent e) {
    while (table.getRowCount() > 0) {
      dm.removeRow(0);
    }
    txtItemID.setEditable(true);
    jButton1.setEnabled(true);
    txtQuantity.setEditable(true);
    cboReason.setEnabled(true);
    btnDone.setVisible(true);
    btnAdd.setVisible(true);
    //show View Receipt screen
//    frmMain.showScreen(Constant.SCRS_VIEW_RECEIPT);
//    frmMain.pnlViewReceipt.txtReceiptDate.requestFocus(true);
    //show Dialog Receipt Search
    DialogReceiptSearch dlgReceiptSearch = new DialogReceiptSearch(frmMain,
        lang.getString("TT0102"), true, frmMain, frmMain.receiptBusObj);
    dlgReceiptSearch.setLocation(112, 85);
//    dlgReceiptSearch.updateScreen();
    dlgReceiptSearch.setVisible(true);
    if (dlgReceiptSearch.done) {
      frmMain.showScreen(Constant.SCRS_VIEW_RECEIPT_DETAILS);
    }
    else {
      frmMain.showScreen(Constant.SCRS_GOOD_RECEIPT);
    }
  }

//  void showData(String storeID, String suppID, String date) {
//    ResultSet rs = getDataDetail(storeID, suppID, date);
//    table.setData(getData(storeID, suppID, date));
//    try {
//      rs.beforeFirst();
//      if (rs.next()) {
//        txtReceiptID.setText(rs.getString("Receipt_ID"));
//        txtSupplier.setText(rs.getString("Supp_Name"));
//        txtStore.setText(storeID);
//        txtDescription.setText(rs.getString("DESCRIPTION"));
//        txtReceiptDate.setText(date);
//      }
//    }
//    catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//  }

//  ResultSet getDataDetail(String storeID, String suppID,String date) {
//      ResultSet rs = null;
//      String sqlStr = "Select r.Receipt_ID, ri.Item_ID, Supp_Name, Qty,DESCRIPTION From BTM_IM_RECEIPT r ,BTM_IM_RECEIPT_ITEM ri,BTM_IM_SUPPLIER s,BTM_IM_ITEM_MASTER m Where r.RECEIPT_ID = ri.RECEIPT_ID and ri.ITEM_ID = m.ITEM_ID and r.SUPP_ID = s.SUPP_ID and r.Store_ID=" + storeID + " and r.Supp_ID='" + suppID + "' and Receipt_Date=to_date('" + date + "','dd-MM-yyyy')";
//      System.out.println(sqlStr);
//      try {
//        rs = DAL.executeScrollQueries(sqlStr);
//      }
//      catch (Exception e) {
//        ExceptionHandle eh = new ExceptionHandle();
//        eh.ouputError(e);
//      }
//      return rs;
//
//    }
//
//  ResultSet getData(String storeID, String suppID,String date) {
//    ResultSet rs = null;
//    String sqlStr = "Select ri.Item_ID , Item_Name, Qty From BTM_IM_RECEIPT r ,BTM_IM_RECEIPT_ITEM ri,BTM_IM_SUPPLIER s,BTM_IM_ITEM_MASTER m Where r.RECEIPT_ID = ri.RECEIPT_ID and ri.ITEM_ID = m.ITEM_ID and r.SUPP_ID = s.SUPP_ID and r.Store_ID=" + storeID + " and r.Supp_ID='" + suppID + "' and Receipt_Date=to_date('" + date + "','dd-MM-yyyy')";
//    System.out.println(sqlStr);
//    try {
//      rs = DAL.executeScrollQueries(sqlStr);
//    }
//    catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//    return rs;
//
//  }



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
//    }
//  }


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

    // F12
    key = new Integer(KeyEvent.VK_F12);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
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
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }
      if (identifier.intValue() == KeyEvent.VK_F2) {
        btnAdd.doClick();
      }
      if (identifier.intValue() == KeyEvent.VK_F3) {
        btnPrint.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void btnPrint_actionPerformed(ActionEvent e) {
    //Export to PDF file (transfer form)
    String strDateTemp =txtReceiptDate.getText();
    int month1 = Integer.parseInt(strDateTemp.substring(0,strDateTemp.indexOf("/")));
    int day1 = Integer.parseInt(strDateTemp.substring(strDateTemp.indexOf("/")+1,strDateTemp.lastIndexOf("/")));
    int year1 = Integer.parseInt(strDateTemp.substring(strDateTemp.lastIndexOf("/")+1,strDateTemp.lastIndexOf("/")+5));

    printReceipt(txtReceiptID.getText(),txtStore.getText(),txtSupplier.getText(),String.valueOf(month1)+"/"+String.valueOf(day1)+"/"+String.valueOf(year1), table, DAL);
    //print PDF file

    /*try {
      String[] arg={"./file/grd" + txtReceiptID.getText() + ".pdf"};
      PrintPDF.main(arg);
    }
    catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }*/

    ////////////////////Tuan Anh
    /*if (e.getSource() instanceof JButton){
      PrinterJob printjob = PrinterJob.getPrinterJob();
      PanelPrintViewGRNSIM ppp;
      ppp = new PanelPrintViewGRNSIM(frmMain);
      printjob.setPrintable(ppp);
      if (printjob.printDialog()) {
        try {
          printjob.print();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }*/
///////////////////Tuan Anh

  }
  void printReceipt(String strReceiptID, String strFromStoreRoom,
                      String strSupplier,String strDate,BJTable tbl, DataAccessLayer DAL) {
        String strHeader[][] = {
            {
            "STOREROOM#" + strFromStoreRoom
            }
        };
        String strTitle[] = {
            "GOODS RECEIPT ON DATE "+strDate};
        String strInfo[][] = {
            {
            "DATE : " + strDate,
            "SUPPLIER: " + strSupplier,
            "RECEIPT ID: " + strReceiptID}
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
            5, 20, 32, 9, 8, 7, 19};

        int[] iTotal = {
            5};

        //Add Item to receipt
        for (int i = 0; i < tbl.getRowCount(); i++) {
          //col 0
          int iNo = i + 1;
          strTableItem[i][0] = "" + iNo;

          //col 1
          if (tbl.getValueAt(i, 1) != null) {
            st = tbl.getValueAt(i, 1).toString();
          }
          else {
            st = " ";
          }
          strTableItem[i][1] = st;

          //col 2
          if (tbl.getValueAt(i, 2) != null) {
            st = tbl.getValueAt(i, 2).toString();
          }
          else {
            st = " ";
          }
          strTableItem[i][2] = st;

          //col 3
          strTableItem[i][3]=getItemColor(tbl.getValueAt(i, 0).toString(), DAL);

          //col 4
          strTableItem[i][4]=getItemSize(tbl.getValueAt(i, 0).toString(), DAL);

          //col 5
          if (tbl.getValueAt(i, 4) != null) {
            st = ""+Double.parseDouble(tbl.getValueAt(i, 4).toString());
          }
          else {
            st = "0";
          }
          strTableItem[i][5]=st;
          //col 6
          strTableItem[i][6]=getItemPrice(tbl.getValueAt(i, 0).toString(), DAL);
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

        pp.setIsGroupByArtNumber(false);
        pp.setTotalGroupName("Total by UPC");

        pp.print();
  }

  //Export to PDF file
  /* void createPdfFile(String strReceiptID, String strFromStoreRoom,
                      String strSupplier,String strDate,BJTable tbl, DataAccessLayer DAL) {
     Document document = new Document();
     document.setPageSize(PageSize.A4);
     document.setMargins(12,70,10,10);
     try {
       //Total page
       int iPage = tbl.getRowCount() / 30 + 1;
       int i, j;
       Utils ut = new Utils();
       PdfWriter writer = PdfWriter.getInstance(document,
                                                new FileOutputStream("./file/grd" +
           strReceiptID + ".pdf"));
       document.open();
       for (j = 1; j <= iPage; j++) {
         //-----------------------------------------Part 1-----------------------------------------//
         if (j!=1){
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
         Paragraph p2 = new Paragraph("GOOD RECEIPT ON DATE "+strDate+"\n\n",
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
         tableDesc.addCell(new Paragraph(new Chunk("SUPPLIER: " +
                                                   strSupplier,
                                                   FontFactory.getFont(
             FontFactory.
             HELVETICA, 10))));

         tableDesc.getDefaultCell().setHorizontalAlignment(Element.
             ALIGN_LEFT);
         tableDesc.getDefaultCell().setGrayFill(0f);
         tableDesc.getDefaultCell().setBorderColor(new Color(255, 255, 255));
         tableDesc.addCell(new Paragraph(new Chunk("RECEIPT ID: " +
                                                   strReceiptID,
                                                   FontFactory.getFont(
             FontFactory.
             HELVETICA, 10))));

         tableDesc.getDefaultCell().setColspan(3);
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
           if (tbl.getValueAt(l, 4) != null) {
             st = tbl.getValueAt(l, 4).toString();
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
             iTotalEnd += Integer.parseInt(tbl.getValueAt(i, 4).toString());
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
   public String getItemPrice(String strItemID, DataAccessLayer DAL) {
     String strInfo = "";
     ResultSet rs = null;
     try {
       rs = null;
       String sql = "select UNIT_COST from BTM_IM_ITEM_LOC_SUPPLIER " +
                   "where ITEM_ID = '"+strItemID+"' and STORE_ID='"+DAL.getStoreID()+"'";
       rs = DAL.executeQueries(sql);
       if (rs.next()) {
         if (rs.getString("Unit_Cost") != null) {
           strInfo = rs.getString("Unit_Cost");
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
   public String getItemColor(String strItemID, DataAccessLayer DAL) {
     String strInfo = "";
     ResultSet rs = null;
     try {
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
     }
     catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
 }
     return strInfo;
  }
  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnCancel.doClick();
               }

  }

  void jButton1_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0016"), true, frmMain, itemBusObject);
    dlgItemLookup.txtSupplieName.setEnabled(false);
    dlgItemLookup.txtSupplieName.setText(txtSupplier.getText());
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      txtItemID.setText(dlgItemLookup.getUPC());
      txtItemID.requestFocus(true);
    }
  }

  void txtQuantity_keyTyped(KeyEvent e) {
    /*ut.limitLenjTextField(e,txtQuantity,StockOnHand.LEN_QTY);
     if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
         (e.getKeyChar() != e.VK_BACK_SPACE))
       e.consume();*///Yen.Dinh 19-06-2006
    ut.checkNumberPositive(e,txtQuantity.getText());
  }
  void getSeason(){
    ResultSet rs = null;
    Statement stmt = null;
    String sql = "select season_id, season_cde from btm_im_Season order by season_id";

    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      cboSeason.setData(rs);
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

  }
  //get combo data
  void getComboData(){
    ResultSet rs = null;
    Statement stmt = null;
    String sql = "select reason_id, reason_desc from btm_im_reason where reason_id <> 1";

    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      cboReason.setData1(rs);
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

    getSeason();
    cboSeason.setSelectedItem(table.getValueAt(0,6));
    this.recordCount = table.getRowCount();
//    for (int i = 0; i<table.getRowCount(); i++){
//      if (!table.getValueAt(i,5).toString().equalsIgnoreCase("N/A")){
//        txtItemID.setEditable(false);
//        jButton1.setEnabled(false);
//        txtQuantity.setEditable(false);
//        cboReason.setEnabled(false);
//        btnDone1.setVisible(false);
//        btnAdd.setVisible(false);
//      }
//    }
  }
  int getSeqID(String receiptID){
    int result = 0;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select seq from BTM_IM_RECEIPT_ITEM where receipt_id='" + receiptID + "' order by seq desc";
//    System.out.println(sql);

    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        result = rs.getInt("seq");
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

    return result;
  }

  //get Item_ID
public String getItemID(String upc){
  ResultSet rs = null;
  Statement stmt = null;
  if(upc.equalsIgnoreCase("")){
    return "";
  }
  String itemID="-1";
  try {
    String sql ="Select ITEM_ID From BTM_IM_ITEM_MASTER Where ART_NO='" + upc + "'";
//        System.out.println(sql);
        stmt = DAL.getConnection().createStatement();
        rs = DAL.executeQueries(sql, stmt);
        if (rs.next()) {
          itemID = rs.getString("ITEM_ID");
        }
  }catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
  }
  try {
        stmt.close();
      }
  catch (Exception e) {
       e.printStackTrace();
  }
  return itemID;
 }




  void btnAdd_actionPerformed(ActionEvent e) {
    if (txtQuantity.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS354_InsertNewQuantity"));
      txtQuantity.requestFocus(true);
      return;
    }
    if (cboReason.getSelectedIndex() == 0){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2021_ChooseReason"));
      cboReason.requestFocus(true);
      return;
    }
    String itemID="";
    String itemName="";
    String artNo="";
    String size="";
    String sql = "";
    double qty1 = -1;
    if (cboReason.getSelectedIndex() == 2){
      sql = "select im.item_id, item_name, art_no, attr_dtl_desc, stock_on_hand qty "
          + " from btm_im_item_master im, btm_im_attr_dtl ad, btm_im_receipt r, "
          + " btm_im_receipt_item ri, btm_im_item_loc_soh ils where im.ATTR2_DTL_ID = ad.ATTR_DTL_ID "
          + " and ils.store_id = r.store_id and ils.item_id = im.item_id and im.status = 'A' "
          + " and ri.item_id = im.item_id and ri.receipt_id = r.receipt_id and im.ART_NO ='"
          + txtItemID.getText().trim() + "' and ri.receipt_id = '"+txtReceiptID.getText()+"'";
    }else{
      sql = "select item_id, item_name, art_no, attr_dtl_desc, -1 qty from btm_im_item_master im, btm_im_attr_dtl ad where im.ATTR2_DTL_ID = ad.ATTR_DTL_ID and im.status = 'A' and ART_NO ='" +
          txtItemID.getText().trim() + "'";
    }
    ResultSet rs = null;
    Statement stmt = null;

    try{
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        itemID= rs.getString("item_id");
        itemName = rs.getString("item_name");
        artNo = rs.getString("art_no");
        size = rs.getString("attr_dtl_desc");
        qty1 = rs.getDouble("qty");
      }else {
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2014_ItemNotSelected"));
        txtItemID.requestFocus(true);
        rs.close();
        stmt.close();

        return;
      }
      rs.close();
      stmt.close();
    }catch(Exception ex){
      ex.printStackTrace();
    }

    if (qty1 != -1){
      double quantity = getQuantity(itemID,cboReason.getSelectedItem().toString());
      if (ut.convertToDouble(txtQuantity.getText())+quantity > qty1){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2019_CannotSubtractCauseQuantityNotValid"));
        txtQuantity.requestFocus(true);
        return;
      }
    }
    if (!isExistItem(itemID,cboReason.getSelectedItem().toString(),ut.convertToDouble(txtQuantity.getText()))){
        dm.addRow(new Object[] {
                  itemID, artNo, itemName, size,
                  new Double(txtQuantity.getText()), cboReason.getSelectedItem()});
        if(table.getRowCount()>0){
          table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
        }
    }
    for (int i = 0; i<table.getRowCount(); i++){
      table.setValueAt(cboSeason.getSelectedItem(),i,6);
    }
    txtItemID.setText("");txtQuantity.setText("");cboReason.setSelectedIndex(0);
    txtItemID.requestFocus(true);
  }
  String getReceiveType(){
    String receiveType = "";
    ResultSet rs = null;
    String sqlStr= "Select FR_BR_FLAG From BTM_IM_INV_ORDER Where ORDER_ID='" + txtReceiptID.getText().trim() + "'";
//    System.out.println(sqlStr);
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr,stmt);
      if (rs.next()){
        receiveType = rs.getString("FR_BR_FLAG");
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return receiveType;
  }
  void btnDone_actionPerformed(ActionEvent e) {
    Vector vSql = new Vector();
    int seqID = getSeqID(txtReceiptID.getText());
    String receiveType =  getReceiveType(); //'B':Backroom , 'F':Frontroom
    seqID ++;
    for (int i = this.recordCount; i<table.getRowCount(); i++){
      String reason = table.getValueAt(i,5).toString();
      String itemID = "";
      double qty = 0;
      if (reason.equalsIgnoreCase("Adjustment-Add")){
        itemID = table.getValueAt(i,0).toString();
        qty = ut.convertToDouble(table.getValueAt(i,4).toString());
        String sql = "insert into btm_im_receipt_item values('" + txtReceiptID.getText() +
            "','" + itemID + "'," + qty + ",2,"+seqID+",'" + cboSeason.getSelectedData() + "')";
//        System.out.println(sql);
        vSql.addElement(sql);
        if (checkExist(itemID,Long.parseLong(txtStore.getText()))){
           if(receiveType.equalsIgnoreCase("B")){
             sql =
                 "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand + " +
                 qty + ", back_room_qty = back_room_qty + " +  qty +
                 " where item_id ='" + itemID + "' and store_id =" +
                 txtStore.getText();
           }else{ //Frontroom
             sql =
                 "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand + " +
                 qty + ", front_room_qty = front_room_qty + " +  qty +
                 " where item_id ='" + itemID + "' and store_id =" +
                 txtStore.getText();
           }
        }else{
          if(receiveType.equalsIgnoreCase("B")){
            sql =
                "insert into btm_im_item_loc_soh (item_id, store_id, stock_on_hand, back_room_qty, front_room_qty, soh_update_datetime, last_update_id) " +
                " values('" + itemID + "'," + ut.convertToDouble(txtStore.getText()) +  "," + qty + "," + qty +
                ",0, to_date('" + ut.getSystemDate() + "','" + ut.DATE_FORMAT + "'), '" + DAL.getEmpID() +  "')";
          }else{ //Frontroom
            sql =
                "insert into btm_im_item_loc_soh (item_id, store_id, stock_on_hand, back_room_qty, front_room_qty, soh_update_datetime, last_update_id) " +
                " values('" + itemID + "'," + ut.convertToDouble(txtStore.getText()) +  "," + qty + ",0," + qty +
                ", to_date('" + ut.getSystemDate() + "','" + ut.DATE_FORMAT + "'), '" + DAL.getEmpID() +  "')";
          }

        }
//        System.out.println(sql);
        vSql.addElement(sql);
      }else if (reason.equalsIgnoreCase("Adjustment-Sub")){
        itemID = table.getValueAt(i,0).toString();
        qty = ut.convertToDouble(table.getValueAt(i,4).toString());
        String sql = "insert into btm_im_receipt_item values('" + txtReceiptID.getText() +
            "','" + itemID + "'," + qty + ",3,"+seqID+",'" + cboSeason.getSelectedData() + "')";
//        System.out.println(sql);
        vSql.addElement(sql);
        if(receiveType.equalsIgnoreCase("B")){
          sql =
              "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand - " +
              qty + ",back_room_qty = back_room_qty - " + qty + " where item_id ='" + itemID + "' and store_id =" +
              txtStore.getText();
        }else{
          sql =
              "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand - " +
              qty + ",front_room_qty = front_room_qty - " + qty + " where item_id ='" + itemID + "' and store_id =" +
              txtStore.getText();
        }
//        System.out.println(sql);
        vSql.addElement(sql);
      }
    }
    if (!vSql.isEmpty()){
      DAL.setBeginTrans(DAL.getConnection());
      DAL.executeBatchQuery(vSql);
      DAL.setEndTrans(DAL.getConnection());
    }
//    DAL.executeUpdate("Update btm_im_receipt_item set season_id = '" + cboSeason.getSelectedData() + "' where receipt_id = '" + txtReceiptID.getText() + "'");
    resetData();
//    frmMain.showScreen(Constant.SCRS_VIEW_RECEIPT);
//    frmMain.pnlViewReceipt.txtReceiptDate.requestFocus(true);

    //show Dialog Receipt Search
    DialogReceiptSearch dlgReceiptSearch = new DialogReceiptSearch(frmMain,
        lang.getString("TT0102"), true, frmMain, frmMain.receiptBusObj);
    dlgReceiptSearch.setLocation(112, 85);
//    dlgReceiptSearch.updateScreen();
    dlgReceiptSearch.setVisible(true);
    if (dlgReceiptSearch.done) {
      frmMain.showScreen(Constant.SCRS_VIEW_RECEIPT_DETAILS);
    }
    else {
      frmMain.showScreen(Constant.SCRS_GOOD_RECEIPT);
    }
  }
  double getQuantity(String itemID,String status){
    double result = 0;
    for (int i = this.recordCount; i<table.getRowCount(); i++){
      String itemID1 = table.getValueAt(i,0).toString();
      double qty = ut.convertToDouble(table.getValueAt(i,4).toString());
      String status1 = table.getValueAt(i,5).toString();
      if (status.equalsIgnoreCase(status1)){
        if (itemID.equalsIgnoreCase(itemID1)){
          result = result + qty;
        }
      }
    }
    return result;
  }
  boolean isExistItem(String itemID, String status, double qty){
    for (int i = this.recordCount; i<table.getRowCount(); i++){
      String itemID1 = table.getValueAt(i, 0).toString();
      double qty1 = ut.convertToDouble(table.getValueAt(i, 4).toString());
      String status1 = table.getValueAt(i, 5).toString();
      if (status.equalsIgnoreCase(status1)) {
        if (itemID.equalsIgnoreCase(itemID1)) {
          qty = qty + qty1;
          table.setValueAt(new Double(qty),i,4);
          return true;
        }
      }
    }
    return false;
  }
  void resetData(){
    txtItemID.setText("");txtQuantity.setText("");cboReason.setSelectedIndex(0);
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount()==2){
      txtItemID.setText(table.getValueAt(table.getSelectedRow(),1).toString());
      txtQuantity.requestFocus(true);
      return;
    }
  }

  void txtItemID_keyTyped(KeyEvent e) {
    ut.checkNumber(e);
  }
}

class PanelViewReceiptDetails_txtQuantity_focusAdapter
    extends java.awt.event.FocusAdapter {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_txtQuantity_focusAdapter(PanelViewReceiptDetails
      adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.txtQuantity_focusLost(e);
  }
}

//class PanelViewReceiptDetails_txtQuantity_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelViewReceiptDetails adaptee;
//
//  PanelViewReceiptDetails_txtQuantity_keyAdapter(PanelViewReceiptDetails adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtQuantity_keyTyped(e);
//  }
//}

class PanelViewReceiptDetails_btnPrint_actionAdapter
    implements java.awt.event.ActionListener {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_btnPrint_actionAdapter(PanelViewReceiptDetails adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrint_actionPerformed(e);
  }
}

class PanelViewReceiptDetails_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_btnCancel_actionAdapter(PanelViewReceiptDetails
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelViewReceiptDetails_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_table_keyAdapter(PanelViewReceiptDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class PanelViewReceiptDetails_jButton1_actionAdapter implements java.awt.event.ActionListener {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_jButton1_actionAdapter(PanelViewReceiptDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class PanelViewReceiptDetails_txtQuantity_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_txtQuantity_keyAdapter(PanelViewReceiptDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtQuantity_keyTyped(e);
  }
}

class PanelViewReceiptDetails_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_btnAdd_actionAdapter(PanelViewReceiptDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelViewReceiptDetails_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_btnDone_actionAdapter(PanelViewReceiptDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelViewReceiptDetails_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_table_mouseAdapter(PanelViewReceiptDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelViewReceiptDetails_txtItemID_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewReceiptDetails adaptee;

  PanelViewReceiptDetails_txtItemID_keyAdapter(PanelViewReceiptDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemID_keyTyped(e);
  }
}
