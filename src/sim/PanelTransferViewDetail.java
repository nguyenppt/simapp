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
/**
 * <p>Title: </p>
 * <p>Description: Transfer > View > Search > dblclick </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelTransferViewDetail extends JPanel{
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_NAME = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_QUANTITY = 4; //
  private static final int COL_STATUS = 5; //


  boolean bFlag = false;
  boolean b = true;
  DataAccessLayer DAL;
  ResourceBundle lang;

  FrameMainSim frmMain;
  Vector vSql = new Vector();
  Utils ut = new Utils();
//  int recordCount = 0;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel6 = new JPanel();
  BJButton btnPrint = new BJButton();
  BJButton btnCancel = new BJButton();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JTextField txtTransferDate = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField txtTransferID = new JTextField();
  JPanel jPanel4 = new JPanel();
  JLabel jLabel1 = new JLabel();
//  JTextField txtFromStoreID = new JTextField();
  JLabel jLabel3 = new JLabel();
  JPanel jPanel10 = new JPanel();
  JLabel jLabel2 = new JLabel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JTextField txtCreaterName = new JTextField();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JPanel jPanel8 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JLabel jLabel6 = new JLabel();
  JTextField txtReceiverName = new JTextField();
  JPanel jPanel9 = new JPanel();
  JLabel jLabel5 = new JLabel();
  String itemDesc = "";
  String size = "";
  String artNo = "";
  double qty = 0;

//  SortableTableModel dm = new SortableTableModel();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 2: return Long.class;
        default: return Object.class;
      }
    }
  };
//  BJTable table = new BJTable(dm,true);
  BJTable table = new BJTable(dm){
    public void tableChanged(TableModelEvent e) {
      super.tableChanged(e);
      repaint();
    }
  };

  JScrollPane jScrollPane1 = new JScrollPane();
//  JTextField txtToStoreID = new JTextField();
  JPanel jPanel7 = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jPanel12 = new JPanel();
  JPanel jPanel13 = new JPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  BorderLayout borderLayout8 = new BorderLayout();
  JPanel jPanel14 = new JPanel();
  JPanel jPanel15 = new JPanel();
  JLabel lblItemID = new JLabel();
  JLabel lblStatus = new JLabel();
  JTextField txtUPC = new JTextField();
  JButton btnSearchItem = new JButton();
  BJComboBox cboStatus = new BJComboBox();
  JPanel jPanel16 = new JPanel();
  JPanel jPanel17 = new JPanel();
  JPanel jPanel18 = new JPanel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JTextField txtQuantity = new JTextField();
  BJComboBox cboReason = new BJComboBox();
  BJButton btnAdd = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnDone = new BJButton();
  BJComboBox cboDesStoreID = new BJComboBox();
  BJComboBox cboSourceStoreID = new BJComboBox();


  public PanelTransferViewDetail(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    DAL.getConnfigParameter();
    lang=DAL.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

    registerKeyboardActions();
    jPanel6.setBackground(new Color(121, 152, 198));
    jPanel6.setPreferredSize(new Dimension(800, 50));
    this.setLayout(borderLayout1);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    //Button Cancel
    btnCancel.setToolTipText(lang.getString("Back")+" (F12)");
    btnCancel.setActionCommand("btnCancel");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelTransferViewDetail_btnCancel_actionAdapter(this));
    //Button Print
    btnPrint.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPrint.setToolTipText(lang.getString("Print")+" (F9)");
    btnPrint.setActionCommand("btnPrint");
    btnPrint.setText("<html><center><b>"+lang.getString("Print")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F9</h" +
                      "tml>");
    btnPrint.addActionListener(new PanelTransferViewDetail_btnPrint_actionAdapter(this));

    jPanel1.setMinimumSize(new Dimension(442, 80));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout2);
    txtTransferDate.setText("");
    txtTransferDate.setPreferredSize(new Dimension(160, 20));
    txtTransferDate.setEditable(false);
    txtTransferDate.setEnabled(true);
    txtTransferDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("ToStore") );
    jLabel4.setPreferredSize(new Dimension(120, 20));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtTransferID.setText("");
    txtTransferID.setPreferredSize(new Dimension(160, 20));
    txtTransferID.setEditable(false);
    txtTransferID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel4.setPreferredSize(new Dimension(200, 100));
    jLabel1.setText(lang.getString("Receiver") );
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtFromStoreID.setText("");
//    txtFromStoreID.setPreferredSize(new Dimension(160, 20));
//    txtFromStoreID.setEditable(false);
//    txtFromStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setText(lang.getString("TransferDate") );
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel10.setPreferredSize(new Dimension(130, 100));
    jPanel10.setMinimumSize(new Dimension(38, 31));
    jPanel10.setBackground(SystemColor.control);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText(lang.getString("CreaterName")+":");
    jPanel2.setBackground(SystemColor.control);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setMinimumSize(new Dimension(442, 90));
    jPanel2.setPreferredSize(new Dimension(800, 168));
    jPanel2.setLayout(borderLayout3);
    txtCreaterName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreaterName.setPreferredSize(new Dimension(160, 20));
    txtCreaterName.setEditable(false);
    txtCreaterName.setText("");
    jPanel5.setPreferredSize(new Dimension(300, 100));
    jPanel5.setLayout(borderLayout5);
    jPanel11.setBackground(SystemColor.control);
    jPanel11.setPreferredSize(new Dimension(170, 100));
    jPanel8.setBackground(SystemColor.control);
    jPanel8.setPreferredSize(new Dimension(130, 100));
    jPanel3.setBackground(SystemColor.control);
    jPanel3.setPreferredSize(new Dimension(300, 100));
    jPanel3.setLayout(borderLayout4);
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setPreferredSize(new Dimension(120, 20));
    jLabel6.setText(lang.getString("TransferId")+":");
    txtReceiverName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtReceiverName.setPreferredSize(new Dimension(160, 20));
    txtReceiverName.setEditable(false);
    txtReceiverName.setText("");
    jPanel9.setBackground(SystemColor.control);
    jPanel9.setPreferredSize(new Dimension(170, 100));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setPreferredSize(new Dimension(120, 20));
    jLabel5.setText(lang.getString("FromStore")+":");
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(780, 350));
    jScrollPane1.setToolTipText("");
//    txtToStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtToStoreID.setPreferredSize(new Dimension(160, 20));
//    txtToStoreID.setEditable(false);
//    txtToStoreID.setText("");
    table.addMouseListener(new PanelTransferViewDetail_table_mouseAdapter(this));
    table.addKeyListener(new PanelTransferViewDetail_table_keyAdapter(this));
    jPanel7.setLayout(borderLayout6);
    jPanel7.setPreferredSize(new Dimension(800, 50));
    jPanel7.setRequestFocusEnabled(true);
    jPanel12.setPreferredSize(new Dimension(300, 50));
    jPanel12.setLayout(borderLayout7);
    jPanel13.setPreferredSize(new Dimension(500, 50));
    jPanel13.setLayout(borderLayout8);
    jPanel14.setPreferredSize(new Dimension(130, 50));
    jPanel15.setPreferredSize(new Dimension(170, 50));
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setPreferredSize(new Dimension(120, 20));
    lblItemID.setText(lang.getString("UPC")+":");

    lblStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStatus.setPreferredSize(new Dimension(120, 20));
    lblStatus.setText(lang.getString("Status")+":");

    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel7.setText(lang.getString("ItemID")+":");
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUPC.setPreferredSize(new Dimension(125, 20));
    txtUPC.setText("");
    txtUPC.setSelectionStart(11);
    //Yen.dinh 14-6-2006
    txtUPC.addFocusListener(new PanelTransferViewDetail_txtUPC_focusAdapter(this));
    txtUPC.addKeyListener(new PanelTransferViewDetail_txtUPC_keyAdapter(this));
    btnSearchItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearchItem.setPreferredSize(new Dimension(33, 22));
    btnSearchItem.setText("...");
    btnSearchItem.addActionListener(new PanelTransferViewDetail_btnSearchItem_actionAdapter(this));
    cboStatus.setPreferredSize(new Dimension(160, 20));
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel16.setPreferredSize(new Dimension(130, 50));
    jPanel17.setPreferredSize(new Dimension(170, 50));
    jPanel18.setPreferredSize(new Dimension(200, 50));
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel7.setPreferredSize(new Dimension(120, 20));
    jLabel7.setText(lang.getString("Reason")+":");
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel8.setPreferredSize(new Dimension(120, 20));
    jLabel8.setText(lang.getString("Quantity")+":");
    txtQuantity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtQuantity.setPreferredSize(new Dimension(160, 20));
    txtQuantity.setText("");
    txtQuantity.addKeyListener(new PanelTransferViewDetail_txtQuantity_keyAdapter(this));
    txtQuantity.addFocusListener(new PanelTransferViewDetail_txtQuantity_focusAdapter(this));
    cboReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboReason.setPreferredSize(new Dimension(160, 20));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setText(lang.getString("Done")+" (F1)");
    btnDone.addActionListener(new PanelTransferViewDetail_btnDone_actionAdapter(this));
    btnAdd.setText(lang.getString("Add")+" (F2)");
    btnDelete.setText(lang.getString("Delete")+" (F8)");
    btnAdd.addActionListener(new PanelTransferViewDetail_btnAdd_actionAdapter(this));
    cboSourceStoreID.setEnabled(false);
    cboSourceStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSourceStoreID.setPreferredSize(new Dimension(160, 20));
    cboDesStoreID.setEnabled(false);
    cboDesStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboDesStoreID.setPreferredSize(new Dimension(160, 20));
    btnDelete.addActionListener(new
                                PanelTransferViewDetail_btnDelete_actionAdapter(this));
    this.add(jPanel6, BorderLayout.NORTH);
    jPanel6.add(btnDone, null);
    jPanel6.add(btnAdd, null);
    jPanel6.add(btnDelete, null);
    jPanel6.add(btnPrint, null);
    jPanel6.add(btnCancel, null);
    this.add(jPanel1,  BorderLayout.CENTER);
    jPanel8.add(jLabel6, null);
    jPanel8.add(jLabel5, null);
    jPanel8.add(jLabel4, null);
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.add(table, null);
    jPanel2.add(jPanel7, BorderLayout.NORTH);
    jPanel7.add(jPanel12,  BorderLayout.WEST);
    jPanel12.add(jPanel14,  BorderLayout.WEST);
    jPanel14.add(lblItemID, null);
    jPanel14.add(lblStatus, null);
    jPanel12.add(jPanel15,  BorderLayout.CENTER);
    jPanel15.add(txtUPC, null);
    jPanel15.add(btnSearchItem, null);
    jPanel15.add(cboStatus, null);
    jPanel7.add(jPanel13,  BorderLayout.CENTER);
    jPanel13.add(jPanel16,  BorderLayout.WEST);
    jPanel16.add(jLabel8, null);
    jPanel16.add(jLabel7, null);
    jPanel13.add(jPanel17,  BorderLayout.CENTER);
    jPanel17.add(txtQuantity, null);
    jPanel17.add(cboReason, null);
    jPanel13.add(jPanel18,  BorderLayout.EAST);
    jPanel2.add(jPanel4,  BorderLayout.EAST);
    jPanel3.add(jPanel9, BorderLayout.CENTER);
    jPanel3.add(jPanel8, BorderLayout.WEST);
    jPanel9.add(txtTransferID, null);
//    jPanel9.add(txtFromStoreID, null);
    jPanel9.add(cboSourceStoreID,null);
//    jPanel9.add(txtToStoreID, null);
    jPanel9.add(cboDesStoreID,null);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel2.add(jPanel3, BorderLayout.WEST);
    jPanel10.add(jLabel3, null);
    jPanel10.add(jLabel2, null);
    jPanel10.add(jLabel1, null);
    jPanel5.add(jPanel11, BorderLayout.CENTER);
    jPanel5.add(jPanel10, BorderLayout.WEST);
    jPanel11.add(txtTransferDate, null);
    jPanel11.add(txtCreaterName, null);
    jPanel11.add(txtReceiverName, null);
    jPanel1.add(jPanel2,  BorderLayout.NORTH);
    jScrollPane1.setViewportView(table);
    //View Table
    String[] columnNames = new String[]{lang.getString("ItemID"),lang.getString("ItemName"),lang.getString("Quantity")};
    dm.setDataVector(new Object[][]{},columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn(lang.getString("ItemID")).setPreferredWidth(150);
    table.getColumn(lang.getString("ItemName")).setPreferredWidth(350);
    table.getColumn(lang.getString("Quantity")).setPreferredWidth(150);

    cboStatus.addItem("WorkSheet");
    cboStatus.addItem("Approved");
    cboStatus.addItem("Completed");
    cboStatus.addItem("Cancelled");
    cboReason.setEditable(false);
  }

  public void setcboStatus(String TFId){
    String sqlStatus = "Select Decode(STATUS,'W','WorkSheet','A','Approved','C','Completed','Cancelled') STATUS From BTM_IM_TRANSFER Where TRANSFER_ID ='" + TFId + "'";
    Statement stmt = null;
    ResultSet rsS = null;
    String StrSta ="";
    try{
      stmt = DAL.getConnection().createStatement();
      rsS = DAL.executeQueries(sqlStatus,stmt);
      if(rsS.next()){
        StrSta = rsS.getString("STATUS");
      }
    }catch(Exception e){}
//System.out.println("sadgdsg:" + StrSta);
    cboStatus.setSelectedItem(StrSta);
    if(cboStatus.getSelectedIndex()>0){
      btnDone.setEnabled(false);
      btnAdd.setEnabled(false);
      table.setEnabled(false);
      b=false;
      cboStatus.setEnabled(false);
    }else{
      cboStatus.setEnabled(true);
      b=true;
      table.setEnabled(true);
      table.setCellEditable(4);
      btnAdd.setEnabled(true);
      btnDone.setEnabled(true);
    }

  }
  void showData(){
    String sql = "select store_id, name from btm_im_store order by name";
    ResultSet rs = null;
    try{
      rs = DAL.executeScrollQueries(sql);
      cboSourceStoreID.setData1(rs);
      rs.beforeFirst();
      cboDesStoreID.setData1(rs);
      rs.getStatement().close();
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    while (table.getRowCount() > 0) {
      dm.removeRow(0);
    }
    resetData();
    txtUPC.setEditable(true);
    btnSearchItem.setEnabled(true);
    txtQuantity.setEditable(true);
//    cboReason.setEnabled(true);
    btnDone.setVisible(true);
    btnAdd.setVisible(true);

    frmMain.showScreen(Constant.SCRS_TRANSFER_VIEW);
    frmMain.pnlMainSim.showInventManageButton();
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2 && b){
      txtUPC.setText(table.getValueAt(table.getSelectedRow(),COL_ART_NO).toString().trim());
      txtUPC.requestFocus(true);
    }
  }

  void btnPrint_actionPerformed(ActionEvent e) {
    //Export to PDF file (transfer form)
    printReceipt(txtTransferID.getText(),cboSourceStoreID.getSelectedItem().toString()
                 ,cboDesStoreID.getSelectedItem().toString(),
                 txtCreaterName.getText(),txtReceiverName.getText(),txtTransferDate.getText(),table,DAL);
   //print PDF file

/*   try {
     String[] arg={"./file/wtf" + txtTransferID.getText() + ".pdf"};
     PrintPDF.main(arg);
   }
   catch (Exception ex) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(ex);
   }*/

////////////////////Tuan Anh
//    if (e.getSource() instanceof JButton) {
//      PrinterJob printjob = PrinterJob.getPrinterJob();
//      PanelPrintViewTransferSIM ppp;
//      ppp = new PanelPrintViewTransferSIM(frmMain);
//      printjob.setPrintable(ppp);
//      if (printjob.printDialog()) {
//        try {
//          printjob.print();
//        }
//        catch (Exception ex) {
//          ex.printStackTrace();
//        }
//      }
//    }
    ///////////////////Tuan Anh
  }
  void printReceipt(String strTransferID, String strFromStoreRoom,
                     String strToStoreRoom, String strCreater,
                     String strReceiver, String strDateTransfer,BJTable tbl, DataAccessLayer DAL) {
      String strHeader[][] = {
          {
          "STOREROOM#" + strFromStoreRoom," "
          }//,
//          {
//          "ADDRESS#"+DAL.getStoreName()," "
//          }
      };

      String strTitle[] = new String[1];
      if (bFlag){
        strTitle[0] = "MODIFY TRANSFER ON DATE " + ut.getSystemDateStandard();
      }else{
        strTitle[0] = "TRANSFER ON DATE " + strDateTransfer;
      }

      String strInfo[][] = {
          {
          "DATE : " + strDateTransfer,
          "CREATER: " + strCreater,
          "TRAN ID: " + strTransferID}, {
          "RECEIVER: " + strReceiver,
          "TO STORE: " + strToStoreRoom," "}
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

//      int iTableSkew[] = {
//          5,8,34,13,10,10,20};
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
        strTableItem[i][3] = getItemColor(tbl.getValueAt(i, 0).toString(), DAL);

        //col 4
        strTableItem[i][4] = getItemSize(tbl.getValueAt(i, 0).toString(), DAL);

        //col 5
        if (tbl.getValueAt(i, 4) != null) {
          st = tbl.getValueAt(i, 4).toString();
          if (tbl.getValueAt(i, 5).equals("Adjustment-Sub")){
            st="-"+st;
          }
        }
        else {
          st = "0";
        }

        strTableItem[i][5] = st;

        //col 6
        strTableItem[i][6] = getItemPrice(tbl.getValueAt(i, 0).toString(), DAL);
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
  void createPdfFile(String strTransferID, String strFromStoreRoom,
                     String strToStoreRoom, String strCreater,
                     String strReceiver, String strDateTransfer,BJTable tbl, DataAccessLayer DAL) {
    Document document = new Document();
    document.setPageSize(PageSize.A4);
    document.setMargins(12,70,10,10);
    try {
      //Total page
      int iPage = tbl.getRowCount() / 30 + 1;
      int i, j;
      Utils ut = new Utils();
      PdfWriter writer = PdfWriter.getInstance(document,
                                               new FileOutputStream("./file/wtf" +
          strTransferID + ".pdf"));
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

        tableGroup.getDefaultCell().setColspan(2);
        tableGroup.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableGroup.getDefaultCell().setGrayFill(0f);
        tableGroup.getDefaultCell().setBorderColor(new Color(255, 255, 255));
        tableGroup.addCell(new Paragraph(new Chunk("ADDRESS#"+DAL.getStoreName(),
            FontFactory.getFont(
                FontFactory.HELVETICA, 10))));


        tableGroup.setTotalWidth(520);
        tableGroup.setLockedWidth(true);
        document.add(tableGroup);
        //-----------------------------------------Part 2-----------------------------------------//
        Paragraph p2 = new Paragraph("TRANSFER ON DATE "+strDateTransfer+"\n\n",
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

          //col 6
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

          //col 7
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

  }

  //get Item Price
  public String getItemPrice(String strItemID, DataAccessLayer DAL) {
    String strInfo = "";
    ResultSet rs = null;
    try {
      String sql =
           "select item_id, NEW_PRICE, attr1_name, attr2_name " +
           "from btm_im_price_hist " +
           "where item_id ='" + strItemID + "' and STATUS ='1' ";

      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("new_price") != null) {
          strInfo = rs.getString("new_price");
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
          strInfo = " ";
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
      else if (identifier.intValue() == KeyEvent.VK_F2) {
        btnAdd.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {

      }
      else if (identifier.intValue() == KeyEvent.VK_F9) {
        btnPrint.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      btnCancel.doClick();
    }
  }




  //get combo data
  void getComboData() {
    ResultSet rs = null;
    Statement stmt = null;
    String sql =
        "select reason_id, reason_desc from btm_im_reason where reason_id <> 1";

    try {
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql, stmt);
      cboReason.setData1(rs);
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

//    this.recordCount = table.getRowCount();
//    for (int i = 0; i<table.getRowCount(); i++){
//      if (!table.getValueAt(i,5).toString().equalsIgnoreCase("N/A")){
//        txtUPC.setEditable(false);
//        btnSearchItem.setEnabled(false);
//        txtQuantity.setEditable(false);
//        cboReason.setEnabled(false);
//        btnDone.setVisible(false);
//        btnAdd.setVisible(false);
//      }
//    }
  }
  //Check item
boolean checkItem(String upc, long storeID) {
  ResultSet rs = null;
  ResultSet rs1 = null;
  String sql =
      "select i.item_id, i.item_name, i.art_no, BACK_ROOM_QTY " +
      " from btm_im_item_master i, "  +
      " btm_im_item_loc_soh ils where i.item_id = ils.item_id and i.ART_NO='" +
      upc + "' and store_id=" + storeID;
//  System.out.println(sql);
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
          " where i.attr2_dtl_id = a.attr_dtl_id and i.art_no ='" + upc +"'";
//      System.out.println(sql);
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

  void txtUPC_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtUPC,25);
    char key = e.getKeyChar();
    if (ut.isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE){
      return;
    }else if (key == KeyEvent.VK_ENTER){
      if (!checkItem(txtUPC.getText().trim(),Long.parseLong(cboSourceStoreID.getSelectedData()))){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2014_ItemNotSelected"));
        txtUPC.setText("");
        txtUPC.requestFocus(true);
        return;
      }
      txtQuantity.requestFocus(true);
      return;
    }else{
      e.consume();
    }

  }
  void txtQuantity_focusLost(FocusEvent e) {
      if (txtQuantity.isEditable()) {
            if (!txtQuantity.getText().equalsIgnoreCase("")) {
              txtQuantity.setText("" + ut.formatNumber(txtQuantity.getText()));
            }
          }

    }


  void txtQuantity_keyTyped(KeyEvent e) {
    /*ut.limitLenjTextField(e,txtQuantity,StockOnHand.LEN_QTY);
     if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
         (e.getKeyChar() != e.VK_BACK_SPACE))
       e.consume();*///Yen
    ut.checkNumberPositive(e,txtQuantity.getText());
  }

  void btnSearchItem_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0016"), true, frmMain, itemBusObject);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      txtUPC.setText(dlgItemLookup.getUPC());
      txtUPC.requestFocus(true);
    }
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    if (!checkItemID(txtUPC.getText().trim(),(String)cboSourceStoreID.getSelectedData())){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS2014_ItemNotSelected"));
      txtUPC.requestFocus(true);
      return;
    }

    if (txtQuantity.getText().trim()==null||txtQuantity.getText().equals("")){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS2009_InputQuantity"));
      txtUPC.requestFocus(true);
      return;
    }
//    if (cboReason.getSelectedIndex() == 0){
//      ut.showMessage(frmMain,"Warning","Please choose a reason");
//      cboReason.requestFocus(true);
//      return;
//    }
    String itemName="";
    String itemID="";
    String artNo="";
    String size="";
    String sql = "";
    double qty1 = -1;
    if (cboReason.getSelectedIndex() == 2){
      sql = "select im.item_id, im.item_name, art_no, attr_dtl_desc, qty from btm_im_item_master im, btm_im_attr_dtl ad, btm_im_transfer r, btm_im_transfer_item ri where im.ATTR2_DTL_ID = ad.ATTR_DTL_ID and im.status = 'A' and ri.item_id = im.item_id and ri.transfer_id = r.transfer_id and im.ART_NO ='"+txtUPC.getText().trim()+"' and ri.transfer_id = '"+txtTransferID.getText().trim()+"'";
    }else{
      sql = "select item_id, item_name, art_no, attr_dtl_desc, -1 qty from btm_im_item_master im, btm_im_attr_dtl ad where im.ATTR2_DTL_ID = ad.ATTR_DTL_ID and im.status = 'A' and im.ART_NO ='" +
          txtUPC.getText().trim() + "'";
    }
    ResultSet rs = null;
    Statement stmt = null;

    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        itemID= rs.getString("item_id");
        itemName = rs.getString("item_name");
        artNo = rs.getString("art_no");
        size = rs.getString("attr_dtl_desc");
        qty1 = rs.getDouble("qty");

        //set flag
        bFlag = true;
      }else {
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2014_ItemNotSelected"));
        txtUPC.requestFocus(true);
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
      double quantity = getQuantity(txtUPC.getText().trim(),cboReason.getSelectedItem().toString());
      if (ut.convertToDouble(txtQuantity.getText())+quantity > qty1){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2019_CannotSubtractCauseQuantityNotValid"));
        txtQuantity.requestFocus(true);
        return;
      }
    }
    if (!isExistItem(txtUPC.getText().trim(),cboReason.getSelectedItem().toString(),ut.convertToDouble(txtQuantity.getText()))){
      dm.addRow(new Object[] {
                itemID, artNo, itemName, size,
                new Double(txtQuantity.getText()), cboReason.getSelectedItem()
      });
    }
    txtUPC.setText("");txtQuantity.setText("");cboReason.setSelectedIndex(0);
    txtUPC.requestFocus(true);
  }
  boolean checkItemID(String upc,String storeID){

   String sql = "select * from BTM_IM_ITEM_LOC_SOH where  STORE_ID =  '"+storeID+
    "' and item_id= (select item_id from BTM_IM_ITEM_MASTER where ART_NO='" + upc + "')";

//   System.out.print(sql);
   ResultSet rs = null;
   try{
     rs = DAL.executeQueries(sql);
     if (rs.next()){
       rs.getStatement().close();
       return true;
     }
     rs.getStatement().close();
   }catch(Exception ex){
     ex.printStackTrace();
   }
   return false;
 }

  double getQuantity(String upc,String status){
    double result = 0;
    for (int i = 0; i<table.getRowCount(); i++){
      String upc1 = table.getValueAt(i,COL_ART_NO).toString();
      double qty = ut.convertToDouble(table.getValueAt(i,COL_QUANTITY).toString());
      String status1 = table.getValueAt(i,COL_STATUS).toString();
      if (status.equalsIgnoreCase(status1)){
        if (upc.equalsIgnoreCase(upc1)){
          result = result + qty;
        }
      }
    }
    return result;
  }
  boolean isExistItem(String upc, String status, double qty){
    for (int i = 0; i<table.getRowCount(); i++){
      String upc1 = table.getValueAt(i, COL_ART_NO).toString();
      double qty1 = ut.convertToDouble(table.getValueAt(i, COL_QUANTITY).toString());
      String status1 = table.getValueAt(i, COL_STATUS).toString();
//      if (status.equalsIgnoreCase(status1)) {
        if (upc.equalsIgnoreCase(upc1)) {
//          qty = qty + qty1;
          table.setValueAt(new Double(qty),i,COL_QUANTITY);
          return true;
//        }
      }
    }
    return false;
  }
  int getSeqID(String receiptID){
    int result = 0;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select seq from BTM_IM_TRANSFER_ITEM where transfer_id='" + receiptID + "' order by seq desc";
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
  boolean checkTransferID(String transferID){
    String sql = "select receipt_id from btm_im_receipt_f_tsf where receipt_id = '" + transferID + "'";
    ResultSet rs = null;
    try{
      rs = DAL.executeQueries(sql);
      if (rs.next()){
        rs.getStatement().close();
        return true;
      }
      rs.getStatement().close();
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return false;
  }
  void btnDone_actionPerformed(ActionEvent e) {
//
//    cboStatus.addItem("Completed");
//    cboStatus.addItem("Cancelled

    if (!cboStatus.getSelectedItem().toString().equalsIgnoreCase("WorkSheet") &&
        !cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approved")) {
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS2011_TransferStatusCanNotBe") + cboStatus.getSelectedItem().toString() + "'");
      cboStatus.requestFocus(true);
      return;
    }

    ut.writeToTextFile("file\\error.txt","Hist TR D  "+ ut.getSystemDateTime()+ "\r\n");
    Vector vSql = new Vector();
    int seqID = getSeqID(txtTransferID.getText());
    seqID ++;
    boolean flagUpdate = checkTransferID(txtTransferID.getText().trim());
    if(cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approved")){
      String sql = "Update BTM_IM_TRANSFER set STATUS = 'A' Where TRANSFER_ID='" + txtTransferID.getText() + "'";
      try{
        DAL.executeUpdate(sql);
      }catch(Exception ex){}
    }

    String strSqlDel = "Delete BTM_IM_TRANSFER_ITEM where TRANSFER_ID = '" + txtTransferID.getText() + "'";
    try{
      DAL.executeUpdate(strSqlDel);
    }catch(Exception ex){}

    for (int i =0; i<table.getRowCount(); i++){
//      String reason = table.getValueAt(i,COL_STATUS).toString();
      String itemID = "";
      String itemName = "";
      double qty = 0;
//      if (reason.equalsIgnoreCase("Adjustment-Add")){
        itemID = table.getValueAt(i,COL_ITEM_ID).toString();
        qty = ut.convertToDouble(table.getValueAt(i,COL_QUANTITY).toString());
        itemName = table.getValueAt(i,COL_ITEM_NAME).toString();
        String sql = "insert into btm_im_transfer_item values('" + txtTransferID.getText() +
            "','" + itemID + "','"+ut.putCommaToString(itemName) +"'," + qty + ",1,"+i+")";
//        System.out.println(sql);
        vSql.addElement(sql);
        if(cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approved")){
          sql = "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand - " + qty + ",BACK_ROOM_QTY=BACK_ROOM_QTY - " + qty + " where item_id ='" + itemID + "' and store_id =" + cboSourceStoreID.getSelectedData().trim();
//          System.out.println(sql);
          vSql.addElement(sql);
          //update destination store SOH (already receipted)
          if (flagUpdate) {
//            sql = "";
//            sql = "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand + " + qty + ",BACK_ROOM_QTY=BACK_ROOM_QTY + " + qty + " where item_id ='" + itemID + "' and store_id =" + cboDesStoreID.getSelectedData().trim();
//            System.out.println(sql);
//            vSql.addElement(sql);
//            sql = "";
            sql = "update BTM_IM_RECEIPT_F_TSF_ITEM set qty = qty + " + qty + " where item_id ='" + itemID + "' and RECEIPT_ID =" + txtTransferID.getText();
//            System.out.println(sql);
            vSql.addElement(sql);
          }
        }
//      }
//      else if (reason.equalsIgnoreCase("Adjustment-Sub")){
//        itemID = table.getValueAt(i, COL_ITEM_ID).toString();
//        itemName = table.getValueAt(i, COL_ITEM_NAME).toString();
//        qty = ut.convertToDouble(table.getValueAt(i, COL_QUANTITY).toString());
//        String sql = "insert into btm_im_transfer_item values('" + txtTransferID.getText() +
//            "','" + itemID + "','" + itemName + "'," + qty + ",3," + i + ")";
//        System.out.println(sql);
//        vSql.addElement(sql);
//        if (cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approved")) {
//          sql = "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand + " + qty + ",BACK_ROOM_QTY=BACK_ROOM_QTY + " + qty + " where item_id ='" + itemID + "' and store_id =" + cboSourceStoreID.getSelectedData().trim();
//          System.out.println(sql);
//          vSql.addElement(sql);
//          //update destination store SOH (already receipted)
//
//          if (flagUpdate) {
//            sql = "";
//            sql = "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand - " + qty + ",BACK_ROOM_QTY=BACK_ROOM_QTY - " + qty + " where item_id ='" + itemID + "' and store_id =" + cboDesStoreID.getSelectedData().trim();
//            System.out.println(sql);
//            vSql.addElement(sql);
//            sql = "";
//            sql = "update BTM_IM_RECEIPT_F_TSF_ITEM set qty = qty - " + qty + " where item_id ='" + itemID + "' and RECEIPT_ID =" + txtTransferID.getText();
//            System.out.println(sql);
//            vSql.addElement(sql);
//          }
//        }
//      }
    }
    if (!vSql.isEmpty()){
      DAL.setBeginTrans(DAL.getConnection());
      DAL.executeBatchQuery(vSql);
      DAL.setEndTrans(DAL.getConnection());

      //print receipt
      if (cboStatus.getSelectedItem().toString().equalsIgnoreCase("Approved")){
        printReceipt(txtTransferID.getText(),cboSourceStoreID.getSelectedItem().toString()
                     ,cboDesStoreID.getSelectedItem().toString(),
                 txtCreaterName.getText(),txtReceiverName.getText(),txtTransferDate.getText(),table,DAL);
        bFlag=false;
      }
    }
    resetData();
    frmMain.showScreen(Constant.SCRS_TRANSFER_VIEW);
    frmMain.pnlMainSim.showInventManageButton();

  }
  void resetData(){
    txtUPC.setText("");txtQuantity.setText("");cboReason.setSelectedIndex(0);
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
  }
  //Yen.Dinh 14-6-2006
 void txtUPC_keyReleased(KeyEvent e) {};
 void txtUPC_focusLost(FocusEvent e) {};
  public void btnDelete_actionPerformed(ActionEvent e) {
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

}

class PanelTransferViewDetail_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelTransferViewDetail adaptee;
  PanelTransferViewDetail_btnDelete_actionAdapter(PanelTransferViewDetail
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelTransferViewDetail_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_btnCancel_actionAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelTransferViewDetail_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_table_mouseAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelTransferViewDetail_btnPrint_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_btnPrint_actionAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrint_actionPerformed(e);
  }
}

class PanelTransferViewDetail_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_table_keyAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
//Yen.dinh 14-6-2006

class PanelTransferViewDetail_txtUPC_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_txtUPC_keyAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtUPC_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }
}
class PanelTransferViewDetail_txtUPC_focusAdapter extends java.awt.event.FocusAdapter {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_txtUPC_focusAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtUPC_focusLost(e);
  }
}

//------
class PanelTransferViewDetail_txtQuantity_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_txtQuantity_keyAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtQuantity_keyTyped(e);
  }
}

class PanelTransferViewDetail_btnSearchItem_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_btnSearchItem_actionAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchItem_actionPerformed(e);
  }
}
//Yen.Dinh 14-06-2006
class PanelTransferViewDetail_txtQuantity_focusAdapter extends java.awt.event.FocusAdapter {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_txtQuantity_focusAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtQuantity_focusLost(e);
  }
}


class PanelTransferViewDetail_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_btnAdd_actionAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelTransferViewDetail_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferViewDetail adaptee;

  PanelTransferViewDetail_btnDone_actionAdapter(PanelTransferViewDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}
