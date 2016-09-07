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
import java.awt.Dimension;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//nOT use now
public class PanelTransferModifyDamageGoods extends JPanel {
  boolean bFlag = false;
  DataAccessLayer DAL;
  ResourceBundle lang;
  FrameMainSim frmMain;
  Vector vSql = new Vector();
  Utils ut = new Utils();
  int recordCount = 0;
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
//  SortableTableModel dm = new SortableTableModel();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 2: return Long.class;
        default: return Object.class;
      }
    }
  };
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
  JTextField txtItemID = new JTextField();
  JButton btnSearchItem = new JButton();
  JPanel jPanel16 = new JPanel();
  JPanel jPanel17 = new JPanel();
  JPanel jPanel18 = new JPanel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JTextField txtQuantity = new JTextField();
  BJComboBox cboReason = new BJComboBox();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  BJComboBox cboDesStoreID = new BJComboBox();
  BJComboBox cboSourceStoreID = new BJComboBox();


  public PanelTransferModifyDamageGoods(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  void jbInit() throws Exception {
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
    btnCancel.addActionListener(new PanelTransferModifyDamageGoods_btnCancel_actionAdapter(this));
    //Button Print
    btnPrint.setToolTipText(lang.getString("Print") +" (F9)");
    btnPrint.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPrint.setActionCommand("btnPrint");
    btnPrint.setText(lang.getString("Print")+ " (F9)");
    btnPrint.addActionListener(new PanelTransferModifyDamageGoods_btnPrint_actionAdapter(this));

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
    jLabel1.setText(lang.getString("Receiver"));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtFromStoreID.setText("");
//    txtFromStoreID.setPreferredSize(new Dimension(160, 20));
//    txtFromStoreID.setEditable(false);
//    txtFromStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setText(lang.getString("TransferDate"));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel10.setPreferredSize(new Dimension(130, 100));
    jPanel10.setMinimumSize(new Dimension(38, 31));
    jPanel10.setBackground(SystemColor.control);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText(lang.getString("CreaterName"));
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
    jLabel6.setText(lang.getString("TransferId") );
    txtReceiverName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtReceiverName.setPreferredSize(new Dimension(160, 20));
    txtReceiverName.setEditable(false);
    txtReceiverName.setText("");
    jPanel9.setBackground(SystemColor.control);
    jPanel9.setPreferredSize(new Dimension(170, 100));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setPreferredSize(new Dimension(120, 20));
    jLabel5.setText(lang.getString("FromStore"));
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(780, 350));
    jScrollPane1.setToolTipText("");
//    txtToStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtToStoreID.setPreferredSize(new Dimension(160, 20));
//    txtToStoreID.setEditable(false);
//    txtToStoreID.setText("");
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
    lblItemID.setText(lang.getString("ItemID"));
    jLabel7.setText(lang.getString("ItemID"));
    txtItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemID.setPreferredSize(new Dimension(125, 20));
    txtItemID.setText("");
    btnSearchItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearchItem.setPreferredSize(new Dimension(33, 22));
    btnSearchItem.setText("...");
    btnSearchItem.addActionListener(new PanelTransferModifyDamageGoods_btnSearchItem_actionAdapter(this));
    jPanel16.setPreferredSize(new Dimension(130, 50));
    jPanel17.setPreferredSize(new Dimension(170, 50));
    jPanel18.setPreferredSize(new Dimension(200, 50));
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel7.setPreferredSize(new Dimension(120, 20));
    jLabel7.setText(lang.getString("Reason"));
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel8.setPreferredSize(new Dimension(120, 20));
    jLabel8.setText(lang.getString("Quantity"));
    txtQuantity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtQuantity.setPreferredSize(new Dimension(160, 20));
    txtQuantity.setText("");
    txtQuantity.addKeyListener(new PanelTransferModifyDamageGoods_txtQuantity_keyAdapter(this));
    txtQuantity.addFocusListener(new PanelTransferModifyDamageGoods_txtQuantity_focusAdapter(this));
    cboReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboReason.setPreferredSize(new Dimension(160, 20));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setText(lang.getString("Done")+" (F1)");
    //btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.addActionListener(new PanelTransferModifyDamageGoods_btnDone_actionAdapter(this));
    btnAdd.setText(lang.getString("Add")+" (F2)");
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.addActionListener(new PanelTransferModifyDamageGoods_btnAdd_actionAdapter(this));
    table.addKeyListener(new PanelTransferModifyDamageGoods_table_keyAdapter(this));
    table.addMouseListener(new PanelTransferModifyDamageGoods_table_mouseAdapter(this));
    cboSourceStoreID.setPreferredSize(new Dimension(160, 20));
    cboDesStoreID.setPreferredSize(new Dimension(160, 20));
    this.add(jPanel6, BorderLayout.NORTH);
    jPanel6.add(btnDone, null);
    jPanel6.add(btnAdd, null);
    jPanel6.add(btnPrint, null);
    jPanel6.add(btnCancel, null);
    this.add(jPanel1,  BorderLayout.CENTER);
    jPanel8.add(jLabel6, null);
    jPanel8.add(jLabel5, null);
    jPanel8.add(jLabel4, null);
    jPanel2.add(jPanel7, BorderLayout.NORTH);
    jPanel7.add(jPanel12,  BorderLayout.WEST);
    jPanel12.add(jPanel14,  BorderLayout.WEST);
    jPanel14.add(lblItemID, null);
    jPanel12.add(jPanel15,  BorderLayout.CENTER);
    jPanel15.add(txtItemID, null);
    jPanel15.add(btnSearchItem, null);
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
    jPanel1.add(jScrollPane1,  BorderLayout.SOUTH);
    jPanel1.add(jPanel2,  BorderLayout.NORTH);
    jScrollPane1.add(table, null);
    jScrollPane1.setViewportView(table);
    //View Table
    String[] columnNames = new String[]{lang.getString("ItemID"),lang.getString("ItemName"),lang.getString("Quantity")};
    dm.setDataVector(new Object[][]{},columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn(lang.getString("ItemID")).setPreferredWidth(150);
    table.getColumn(lang.getString("ItemName")).setPreferredWidth(350);
    table.getColumn(lang.getString("Quantity")).setPreferredWidth(150);
    cboDesStoreID.setEnabled(false);
    cboDesStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSourceStoreID.setEnabled(false);
    cboSourceStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

  }
  void showData(){
    System.out.println("tttttttttttttt");
    String sql = "select store_id, name from btm_im_store order by upper(name)";
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
    txtItemID.setEditable(true);
    btnSearchItem.setEnabled(true);
    txtQuantity.setEditable(true);
    cboReason.setEnabled(true);
    btnDone.setVisible(true);
    btnAdd.setVisible(true);

    frmMain.showScreen(Constant.SCRS_TRANSFER_VIEW_DAMAGE_GOODS);
//    frmMain.pnlMainSim.showInventManageButton();
    frmMain.pnlTransferViewDamageGoods.showData();
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
        strTitle[0] = "MODIFY DAMAGE GOODS ON DATE " + ut.getSystemDateStandard();
      }else{
        strTitle[0] = "DAMAGE GOODS ON DATE " + strDateTransfer;
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
      pp.setTotalGroupName("Total by Art#");

      pp.print();
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
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql, stmt);
      cboReason.setData1(rs);
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    this.recordCount = table.getRowCount();
//    for (int i = 0; i<table.getRowCount(); i++){
//      if (!table.getValueAt(i,5).toString().equalsIgnoreCase("N/A")){
//        txtItemID.setEditable(false);
//        btnSearchItem.setEnabled(false);
//        txtQuantity.setEditable(false);
//        cboReason.setEnabled(false);
//        btnDone.setVisible(false);
//        btnAdd.setVisible(false);
//      }
//    }
  }

  void txtQuantity_keyTyped(KeyEvent e) {
    /*ut.limitLenjTextField(e,txtQuantity,StockOnHand.LEN_QTY);
     if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
         (e.getKeyChar() != e.VK_BACK_SPACE))
       e.consume();*/
    ut.checkNumberPositive(e,txtQuantity.getText());//Yen.Dinh 19-06-2006
  }
  void txtQuantity_focusLost(FocusEvent e) {
   if (txtQuantity.isEditable()) {
         if (!txtQuantity.getText().equalsIgnoreCase("")) {
           txtQuantity.setText("" + ut.formatNumber(txtQuantity.getText()));
         }
       }

 }


  void btnSearchItem_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0016"), true, frmMain, itemBusObject);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      txtItemID.setText(dlgItemLookup.getItemID());
      txtItemID.requestFocus(true);
    }
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    if (txtQuantity.getText().trim()==null||txtQuantity.getText().equals("")){
    ut.showMessage(frmMain, lang.getString("TT001"),
                   lang.getString("MS353_EnterQuantityNumber"));
    txtItemID.requestFocus(true);

    return;
  }

    if (cboReason.getSelectedIndex() == 0){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS2021_ChooseReason"));
      cboReason.requestFocus(true);
      return;
    }
    String itemName="";
    String artNo="";
    String size="";
    String sql = "";
    double qty1 = -1;
    if (cboReason.getSelectedIndex() == 2){
      sql = "select im.item_id, im.item_name, art_no, attr_dtl_desc, qty from btm_im_item_master im, btm_im_attr_dtl ad, btm_im_transfer_dmg_goods r, btm_im_transfer_dmg_goods_item ri where im.ATTR2_DTL_ID = ad.ATTR_DTL_ID and im.status = 'A' and ri.item_id = im.item_id and ri.dmg_goods_id = r.dmg_goods_id and im.item_id ='"+txtItemID.getText().trim()+"' and ri.dmg_goods_id = '"+txtTransferID.getText()+"'";
    }else{
      sql = "select item_id, item_name, art_no, attr_dtl_desc, -1 qty from btm_im_item_master im, btm_im_attr_dtl ad where im.ATTR2_DTL_ID = ad.ATTR_DTL_ID and im.status = 'A' and item_id ='" +
          txtItemID.getText().trim() + "'";
    }
    ResultSet rs = null;
    Statement stmt = null;

    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        itemName = rs.getString("item_name");
        artNo = rs.getString("art_no");
        size = rs.getString("attr_dtl_desc");
        qty1 = rs.getDouble("qty");

        //set flag
        bFlag = true;
      }else {
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS350_ItemIDNotExist"));
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
      double quantity = getQuantity(txtItemID.getText(),cboReason.getSelectedItem().toString());
      if (ut.convertToDouble(txtQuantity.getText())+quantity > qty1){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS351_CanNotSubtract"));
        txtQuantity.requestFocus(true);
        return;
      }
    }
    if (!isExistItem(txtItemID.getText().trim(),cboReason.getSelectedItem().toString(),ut.convertToDouble(txtQuantity.getText()))){
      dm.addRow(new Object[] {
                txtItemID.getText().trim(), artNo, itemName, size,
                new Double(txtQuantity.getText()), cboReason.getSelectedItem()
      });
    }
    txtItemID.setText("");txtQuantity.setText("");cboReason.setSelectedIndex(0);
    txtItemID.requestFocus(true);
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
  int getSeqID(String receiptID){
    int result = 0;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select seq from BTM_IM_TRANSFER_DMG_GOODS_ITEM where dmg_goods_id ='" + receiptID + "' order by seq desc";
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
    String sql = "select receipt_id from btm_im_receipt_dmg_goods where receipt_id = '" + transferID + "'";
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
    Vector vSql = new Vector();
    int seqID = getSeqID(txtTransferID.getText());
    seqID ++;
    boolean flagUpdate = checkTransferID(txtTransferID.getText().trim());
    for (int i = this.recordCount; i<table.getRowCount(); i++){
      String reason = table.getValueAt(i,5).toString();
      String itemID = "";
      String itemName = "";
      double qty = 0;
      if (reason.equalsIgnoreCase("Adjustment-Add")){
        itemID = table.getValueAt(i,0).toString();
        qty = ut.convertToDouble(table.getValueAt(i,4).toString());
        itemName = table.getValueAt(i,2).toString();
        String sql = "insert into btm_im_transfer_dmg_goods_item values('" + txtTransferID.getText() +
            "','" + itemID + "','"+ itemName +"'," + qty + ",2,"+seqID+")";
//        System.out.println(sql);
        vSql.addElement(sql);
        sql = "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand - " + qty + " where item_id ='" + itemID + "' and store_id =" + cboSourceStoreID.getSelectedData().trim(); //txtFromStoreID.getText().trim();
//        System.out.println(sql);
        vSql.addElement(sql);
        if (flagUpdate){
          sql = "";
          sql = "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand + " + qty + " where item_id ='" + itemID + "' and store_id =" + cboDesStoreID.getSelectedData().trim(); //txtToStoreID.getText().trim();
//          System.out.println(sql);
          vSql.addElement(sql);
          sql = "";
          sql = "update BTM_IM_RECEIPT_DMG_GOODS_ITEM set qty = qty + " + qty + " where item_id ='" + itemID + "' and receipt_id =" + txtTransferID.getText();
//          System.out.println(sql);
          vSql.addElement(sql);
        }

      }else if (reason.equalsIgnoreCase("Adjustment-Sub")){
        itemID = table.getValueAt(i,0).toString();
        itemName = table.getValueAt(i,2).toString();
        qty = ut.convertToDouble(table.getValueAt(i,4).toString());
        String sql = "insert into btm_im_transfer_dmg_goods_item values('" + txtTransferID.getText() +
            "','" + itemID + "','"+ itemName +"'," + qty + ",3,"+seqID+")";
//        System.out.println(sql);
        vSql.addElement(sql);
        sql = "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand + " + qty + " where item_id ='" + itemID + "' and store_id =" + cboSourceStoreID.getSelectedData().trim(); //DAL.getStoreID();
//        System.out.println(sql);
        vSql.addElement(sql);
        if (flagUpdate){
          sql = "";
          sql = "update btm_im_item_loc_soh set stock_on_hand = stock_on_hand - " + qty + " where item_id ='" + itemID + "' and store_id =" + cboDesStoreID.getSelectedData().trim(); //txtToStoreID.getText().trim();
//          System.out.println(sql);
          vSql.addElement(sql);
          sql = "";
          sql = "update BTM_IM_RECEIPT_DMG_GOODS_ITEM set qty = qty - " + qty + " where item_id ='" + itemID + "' and receipt_id =" + txtTransferID.getText(); //txtToStoreID.getText().trim();
//          System.out.println(sql);
          vSql.addElement(sql);
        }
      }
    }
    if (!vSql.isEmpty()){
      DAL.setBeginTrans(DAL.getConnection());
      DAL.executeBatchQuery(vSql);
      DAL.setEndTrans(DAL.getConnection());

      //print receipt
      if (bFlag) {
        printReceipt(txtTransferID.getText(), cboSourceStoreID.getSelectedItem().toString(), //txtFromStoreID.getText(),
                     cboDesStoreID.getSelectedItem().toString(), //txtToStoreID.getText(),
                     txtCreaterName.getText(), txtReceiverName.getText(),
                     txtTransferDate.getText(), table, DAL);
        bFlag = false;
      }

    }
    resetData();
    frmMain.showScreen(Constant.SCRS_TRANSFER_VIEW_DAMAGE_GOODS);
    frmMain.pnlTransferViewDamageGoods.showData();

  }
  void resetData(){
    txtItemID.setText("");txtQuantity.setText("");cboReason.setSelectedIndex(0);
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
  }

  void btnPrint_actionPerformed(ActionEvent e) {
    //Export to PDF file (transfer form)
    printReceipt(txtTransferID.getText(), cboSourceStoreID.getSelectedItem().toString(), //txtFromStoreID.getText(),
                 cboDesStoreID.getSelectedItem().toString(), //txtToStoreID.getText(),
                 txtCreaterName.getText(), txtReceiverName.getText(),
                 txtTransferDate.getText(), table, DAL);
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      txtItemID.setText(table.getValueAt(table.getSelectedRow(),0).toString().trim());
      txtItemID.requestFocus(true);
    }
  }
}

class PanelTransferModifyDamageGoods_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferModifyDamageGoods adaptee;

  PanelTransferModifyDamageGoods_btnCancel_actionAdapter(PanelTransferModifyDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelTransferModifyDamageGoods_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferModifyDamageGoods adaptee;

  PanelTransferModifyDamageGoods_table_keyAdapter(PanelTransferModifyDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class PanelTransferModifyDamageGoods_txtQuantity_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferModifyDamageGoods adaptee;

  PanelTransferModifyDamageGoods_txtQuantity_keyAdapter(PanelTransferModifyDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtQuantity_keyTyped(e);
  }
}

class PanelTransferModifyDamageGoods_btnSearchItem_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferModifyDamageGoods adaptee;

  PanelTransferModifyDamageGoods_btnSearchItem_actionAdapter(PanelTransferModifyDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchItem_actionPerformed(e);
  }
}

class PanelTransferModifyDamageGoods_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferModifyDamageGoods adaptee;

  PanelTransferModifyDamageGoods_btnAdd_actionAdapter(PanelTransferModifyDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelTransferModifyDamageGoods_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferModifyDamageGoods adaptee;

  PanelTransferModifyDamageGoods_btnDone_actionAdapter(PanelTransferModifyDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelTransferModifyDamageGoods_btnPrint_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferModifyDamageGoods adaptee;

  PanelTransferModifyDamageGoods_btnPrint_actionAdapter(PanelTransferModifyDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrint_actionPerformed(e);
  }
}

class PanelTransferModifyDamageGoods_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelTransferModifyDamageGoods adaptee;

  PanelTransferModifyDamageGoods_table_mouseAdapter(PanelTransferModifyDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}
//Yen.Dinh 19-06-2006
class PanelTransferModifyDamageGoods_txtQuantity_focusAdapter extends java.awt.event.FocusAdapter {
  PanelTransferModifyDamageGoods adaptee;

  PanelTransferModifyDamageGoods_txtQuantity_focusAdapter(PanelTransferModifyDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtQuantity_focusLost(e);
  }

}

