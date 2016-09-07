package pos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import common.DataAccessLayer;
import common.ExceptionHandle;
import common.*;
import btm.swing.*;
import java.text.*;
import com.lowagie.text.FontFactory;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: (Error on Grid)</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelDetailTransaction
    extends JPanel {
  private static final int OK_BTN = 223;
  //13_3_2006
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_DESC = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_QUANTITY = 4; //
  private static final int COL_PRICE = 5;
  private static final int COL_MARKDOWN = 6;
  private static final int COL_UNIT_PRICE_MARKDOWN = 7;

  FrameMain frmMain;
  int flagButton = OK_BTN;
  Utils ut = new Utils();
  DataAccessLayer DAL;
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);


  BorderLayout borderLayout1 = new BorderLayout();
  SortableTableModel dm = new SortableTableModel() {
    public Class getColumnClass(int col) {
      switch (col) {
        case COL_QUANTITY:
          return Double.class;
        case COL_PRICE:
          return Double.class;
        case COL_MARKDOWN:
          return Double.class;
        case COL_UNIT_PRICE_MARKDOWN:
          return Double.class;
        default:
          return Object.class;
      }
    }
  };
  JLabel lblTransID = new JLabel(lang.getString("TransactionNo") +":");
  JLabel lblDate = new JLabel(lang.getString("Date") + ":");
  JLabel lblType = new JLabel(lang.getString("Type") + ":");
  JLabel lblStoreTrans = new JLabel(lang.getString("StoreID")+ ":");
  JLabel lblOperator = new JLabel(lang.getString("CreaterName") +":");
  JTextField txtTransID = new JTextField();
  JTextField txtDate = new JTextField();
  JTextField txtType = new JTextField();
  JTextField txtStoreTrans = new JTextField();
  JTextField txtOperator = new JTextField();
  JPanel jPanel1 = new JPanel();
  JPanel pnlText = new JPanel();
  JPanel pnlTable = new JPanel();
  JPanel pnlItemCode = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout3 = new GridLayout();
  BJTable jTable1;
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BJButton btnCancel = new BJButton();
  BJButton btnPrint = new BJButton();

  public PanelDetailTransaction(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {

    registerKeyboardActions();
    this.setLayout(new FlowLayout());
    jTable1 = new BJTable(dm, true) {
      public boolean isCellEditable(int row, int col) {
        return false;
      }
    };

    pnlText.setBackground(Color.lightGray);
    pnlText.setMinimumSize(new Dimension(10, 10));
    pnlText.setPreferredSize(new Dimension(660, 160));
    pnlText.setRequestFocusEnabled(true);
    pnlText.setLayout(new BorderLayout());

    pnlTable.setBackground(SystemColor.control);
    pnlTable.setMinimumSize(new Dimension(10, 10));
    pnlTable.setPreferredSize(new Dimension(660, 357));
    pnlTable.setLayout(new FlowLayout());

    lblTransID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTransID.setPreferredSize(new Dimension(100, 21));
    lblDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblDate.setPreferredSize(new Dimension(100, 21));
    lblType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblType.setPreferredSize(new Dimension(100, 21));
    lblStoreTrans.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStoreTrans.setPreferredSize(new Dimension(100, 21));
    lblOperator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblOperator.setPreferredSize(new Dimension(100, 21));
    txtTransID.setBackground(SystemColor.control);
    txtTransID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtTransID.setPreferredSize(new Dimension(240, 21));
    txtTransID.setEditable(false);
    txtDate.setBackground(SystemColor.control);
    txtDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtDate.setPreferredSize(new Dimension(240, 21));
    txtDate.setEditable(false);
    txtType.setBackground(SystemColor.control);
    txtType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtType.setPreferredSize(new Dimension(240, 21));
    txtType.setEditable(false);
    txtStoreTrans.setBackground(SystemColor.control);
    txtStoreTrans.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtStoreTrans.setPreferredSize(new Dimension(240, 21));
    txtStoreTrans.setEditable(false);
    txtOperator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtOperator.setPreferredSize(new Dimension(240, 21));
    txtOperator.setEditable(false);
    txtOperator.setBackground(SystemColor.control);
    jPanel6.setBackground(SystemColor.control);
    jPanel6.setPreferredSize(new Dimension(60, 31));
    jPanel2.setBackground(SystemColor.control);
    jPanel5.setBackground(SystemColor.control);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setText("<html><center><b>" +lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new
                                PanelDetailTransaction_btnCancel_actionAdapter(this));
//    jTable1.setPreferredSize(new Dimension(0, 0));
    this.setBackground(SystemColor.control);
    btnPrint.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPrint.setText(lang.getString("Print") + " (F9)");
    btnPrint.addActionListener(new PanelDetailTransaction_btnPrint_actionAdapter(this));
    jPanel2.add(lblTransID, null);
    jPanel2.add(lblDate, null);
    jPanel2.add(lblType, null);
    jPanel2.add(lblStoreTrans, null);
    jPanel2.add(lblOperator, null);

    jPanel6.add(txtTransID, null);
    jPanel6.add(txtDate, null);
    jPanel6.add(txtType, null);
    jPanel6.add(txtStoreTrans, null);
    jPanel6.add(txtOperator, null);

//    jTable1.setBorder(null);
//    jTable1.setRequestFocusEnabled(true);

    jPanel2.setPreferredSize(new Dimension(120, 10));
    jPanel5.setPreferredSize(new Dimension(290, 10));

//    jTable1.setRowHeight(30);
//    jTable1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));

    JScrollPane jsp = new JScrollPane(jTable1);
    jsp.setPreferredSize(new Dimension(650, 307));
    jsp.setViewportView(jTable1);
    jsp.getViewport().setBackground(SystemColor.control);
    pnlTable.add(jsp, null);
    pnlText.add(jPanel2, BorderLayout.WEST);
    pnlText.add(jPanel5, BorderLayout.EAST);
    pnlText.add(jPanel6, BorderLayout.CENTER);

    this.add(pnlText, null);
    this.add(pnlTable, null);

  }

  /* get new markdown of item to show on table
   * @author Vinh.Le
   * @param : tran_id, item_id, old_markdown and new_Qty
   * @return : double markdown
   */
  double getNewMarkDown(String tran_id, String item_id, double old_Markdown,
                        long new_Qty) {
    double newMarkDown = old_Markdown;
    Statement stmt = null;
    ResultSet rs = null;
    //get quantity returned of item
    String sqlStr =
        "Select RETURN_QTY From BTM_POS_RETURN_ITEM Where TRANS_ID='" + tran_id +
        "' and ITEM_ID='" + item_id + "'";
//    System.out.println(sqlStr);
//    rs = DAL.executeQueries(sqlStr);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr, stmt);
      if (rs.next()) {
        //calculate new markdown
        newMarkDown = (old_Markdown / (rs.getInt("RETURN_QTY") + new_Qty)) *
            new_Qty;
      }
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return newMarkDown;
  }

  //Get and show data out from database
  public void showData(String trans_id) {
    int i = 0;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      String sql =
          "select trunc(trans_date) trans_date, trans_id, trans_desc, store_id, emp_id " +
          " from BTM_POS_DETAIL_TRANS d, BTM_POS_TRANS_TYPE t " +
          " where d.trans_type_id = t.trans_type_id and trans_id ='" + trans_id +
          "'  group by trunc(trans_date), trans_id, trans_desc, store_id, emp_id";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        txtTransID.setText("" + rs.getLong("trans_id"));
        txtDate.setText("" + rs.getDate("trans_date"));
        txtType.setText(rs.getString("trans_desc"));
        txtStoreTrans.setText("" + rs.getLong("store_id"));
        txtOperator.setText(rs.getString("emp_id"));
      }else{
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS042_TranNotExist"));
        updateScreen();
        stmt.close();
        return;
      }
      stmt.close();
      sql =
          " select d.item_id, art_no,item_desc,attr2_name, unit_qty, unit_price, markdown*unit_qty, amount " +
          " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i " +
          " where d.item_id = i.item_id and trans_id = '" + trans_id + "'";

//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql, stmt);
      dm.resetIndexes();
      jTable1.setData(rs);

      frmMain.txtMainInput.setText("");
      jTable1.setHeaderName(lang.getString("ItemID") , COL_ITEM_ID);
      jTable1.setHeaderName(lang.getString("ArtNo"), COL_ART_NO);
      jTable1.setHeaderName(lang.getString("ItemName"), COL_ITEM_DESC);
      jTable1.setHeaderName(lang.getString("Size"), COL_SIZE);
      jTable1.setHeaderName(lang.getString("Quantity"), COL_QUANTITY);
      jTable1.setHeaderName(lang.getString("UnitPrice"), COL_PRICE);
      jTable1.setHeaderName(lang.getString("MarkDown"), COL_MARKDOWN);
      jTable1.setHeaderName(lang.getString("Total"), COL_UNIT_PRICE_MARKDOWN);

      frmMain.txtMainInput.requestFocus(true);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    try {
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  void updateScreen(){
    txtTransID.setText("");
    txtDate.setText("");
    txtType.setText("");
    txtStoreTrans.setText("");
    txtOperator.setText("");
    while (jTable1.getRowCount() > 0) {
      dm.removeRow(0);
    }
    frmMain.txtMainInput.setText("");
    frmMain.txtMainInput.requestFocus(true);
  }

  //show button in Frame Main
  void showButton() {
    frmMain.showButton(btnPrint);
    frmMain.showButton(btnCancel);

  }

  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput = frmMain.getInputText();
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (mainInput.equals("")) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS040_EnterTransactionID"));
          updateScreen();
          return;

        }
        if (!ut.isDoubleString(mainInput)) {
          ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS041_TranIDIsNotNum"));
          updateScreen();
          return;
        }
      if (!mainInput.equals("")) {
        showData(mainInput);
      }
    } //enter

  }

  private String formatNumberTo2DigitsDecimal(Double d) {
    NumberFormat formatter = new DecimalFormat("#,##0.00");
    String s = formatter.format(d);
    return s;
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    while (jTable1.getRowCount() > 0) {
      dm.removeRow(0);
    }
    txtTransID.setText("");
    txtDate.setText("");
    txtType.setText("");
    txtStoreTrans.setText("");
    txtOperator.setText("");
    frmMain.setTitle(lang.getString("TT011"));
    frmMain.setInputLabel(lang.getString("MS021_EnterTranDate"));
    frmMain.showScreen(Constant.SCR_VIEW_TRANSACTION);
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
    // F9
     key = new Integer(KeyEvent.VK_F9);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), key);
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

      if (identifier.intValue() == KeyEvent.VK_F9) {
        btnPrint.doClick();
      }else  if (identifier.intValue() == KeyEvent.VK_F12 ||
          identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }


    }

  }

  void btnPrint_actionPerformed(ActionEvent e) {
    try {

      //print for Franchise ( A4 paper), not use now
//      if (getCustType(txtTransID.getText(), DAL).equals("1")) {
      if (false) {
        String strHeader[][] = {
            {
            getStoreName(DAL.getStoreID(), DAL) + " to " +
            getFranName(getCustID(txtTransID.getText(), DAL), DAL)
        }
        };
        String strTitle[] = {
            "DELIVERY ORDER"};
        String strInfo[][] = {
            {
            "DATE : " + ut.getSystemDate().substring(0, 10),
            "FRANCHISE: " +
            getFranName(getCustID(txtTransID.getText(), DAL), DAL),
            "TRANS ID: " + txtTransID.getText()}
        };

        String strFormat[] = {
            "String", "String", "String", "String", "String", "money",
            "double",
            "string", "double"
        };

        String strTableTitle[] = {
            "No", "Art#", "Item Name", "Color", "Size", "Qty", "Price",
            "Discount",
            "Item Total"
        };

        String strTableAlign[] = {
            "CENTER", "CENTER", "LEFT", "LEFT", "LEFT", "RIGHT", "RIGHT",
            "RIGHT",
            "RIGHT"};
        String strTableItem[][] = new String[jTable1.getRowCount()][9];
        String st = "";

        int iTableSkew[] = {
            4, 7, 27, 15, 6, 10, 11, 10, 14};
        int[] iTotal = {
            5, 8};

        int i, j, l;
        long lDiscount = 0;
        //Add Item to receipt
        for (i = 0; i < jTable1.getRowCount(); i++) {
          //col 0
          int iNo = i + 1;
          strTableItem[i][0] = "" + iNo;

          //col art no
          if (jTable1.getValueAt(i, 1) != null) {
            st = jTable1.getValueAt(i, 1).toString();
          }
          else {
            st = " ";
          }
          strTableItem[i][1] = st;

          //col item name
          if (jTable1.getValueAt(i, 2) != null) {
            st = jTable1.getValueAt(i, 2).toString();
          }
          else {
            st = " ";
          }
          strTableItem[i][2] = st;

          //col color
          strTableItem[i][3] = getItemColor(jTable1.getValueAt(i, 0).toString(), DAL);;

          //col size
          if (jTable1.getValueAt(i, 3) != null) {
            st = jTable1.getValueAt(i, 3).toString();
          }
          else {
            st = " ";
          }
          strTableItem[i][4] = st;

          //col qty
          if (jTable1.getValueAt(i, 4) != null) {
            st = jTable1.getValueAt(i, 4).toString();
          }
          else {
            st = " ";
          }
          strTableItem[i][5] = st;

          //col unit price
          if (jTable1.getValueAt(i, 5) != null) {
            st = jTable1.getValueAt(i, 5).toString();
            //st = st.substring(0,st.indexOf("."));
            st = ""+ut.convertToDouble(st);
            st = st.substring(0,st.indexOf("."));
          }
          else {
            st = " ";
          }

          strTableItem[i][6] = st;

          //col 7
          String sMac = jTable1.getValueAt(i, 6).toString();
          sMac = ""+ut.convertToDouble(sMac);
          String sQuantity = jTable1.getValueAt(i, 4).toString();
          String sUnit = jTable1.getValueAt(i, 5).toString();
          sUnit =""+ut.convertToDouble(sUnit);

          //err divide by 0: if jTable1.getValueAt(i, 4).toString() = 0.xx
//          lDiscount = (long) Double.parseDouble(sMac) * 100 /
//              ((long)Double.parseDouble(jTable1.getValueAt(i, 4).toString()) *(long) Double.parseDouble(sUnit));

          lDiscount = (long) ( Double.parseDouble(sMac) * 100 /
              (Double.parseDouble(jTable1.getValueAt(i, 4).toString()) * Double.parseDouble(sUnit)));

          strTableItem[i][7] = "" + lDiscount + "%";

          //col 8
          if (jTable1.getValueAt(i, 7) != null) {
            st = jTable1.getValueAt(i, 7).toString();
            //st =st.substring(0,st.indexOf("."));
            st = ""+ut.convertToDouble(st);
            st = st.substring(0,st.indexOf("."));
          }
          else {
            st = " ";
          }
          strTableItem[i][8] = st;
        }

        PrintReceipt pp = new PrintReceipt();
        pp.setFileName(DAL.getAppHome()+"\\file\\" +
                         txtTransID.getText() +
                         ".pdf");

        //pp.setFileName("./file/" + txtTransID.getText() + ".pdf");
        pp.setHeader(strHeader);
        pp.setTitle(strTitle);
        pp.setInfo(strInfo);

        //Add info
        pp.setTotalName("Grand Total");
        pp.setLeftSign("FRANCHISE");
        pp.setRightSign("CREATER");
        //Set Font for table
        pp.setColorBackgroundTotal(new Color(255, 255, 255));

        pp.setFontInTable(FontFactory.getFont(FontFactory.HELVETICA, 9));
        pp.setTotalRowInPage(61);

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
      else {

        int i, j, l;
        PrintReceipt pp = new PrintReceipt();
        String sTitle = DAL.getStoreName() + " - ";
        String Header[] = {
            DAL.getStoreID(), DAL.getStoreAddress(),

        "Ngay#"+ut.getSystemDate().substring(0, 10), "NV#" +getEmpCODE(txtTransID.getText(), DAL),
                      "Gio#"+ut.getSystemDateTime2().
                      substring(11, 19)};
        String CustInfo= getCustInfo(getCustID(txtTransID.getText(),DAL));

        String giftName = checkWinnerTrans(txtTransID.getText());
        if(!giftName.equals("")){
          CustInfo=CustInfo+ "\r\n"+"BILL TRUNG THUONG: "+giftName;
        }

        String tableAlign[] = {"LEFT", "CENTER", "RIGHT", "LEFT", "RIGHT"};
        //String Item[][] = {{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"}};
        int length = jTable1.getRowCount() * 2;
        String sItem[][] = new String[length][5];
        int iSkew[] = {
              35, 19, 19, 27};
        //Item item = new Item();
        double lTotalPrice = 0;
        double lSubTotal = 0;
        double lDiscount = 0;
        double lTotal = 0;
        double lQuantity = 0;
        String  st="";
        //Convert the vector Item to array string Item
        j = 0; //j=0: set item number (1,2,3...)  and name to sItem(0,i)
        l = 0;
        for (i = 0; i < jTable1.getRowCount(); i++) {
          //item = (Item) vSaleItem.elementAt(i); //Get item
          sItem[j][0] = "" + (int) (l + 1) + ". ";
          //Name
          if (jTable1.getValueAt(i, 2) != null) {
            st = jTable1.getValueAt(i, 2).toString();
          }
          else {
            st = " ";
          }
          sItem[j][1] = st; //Name

          if (!jTable1.getValueAt(i, 6).equals("0.00")) {
            String sMarkdown=jTable1.getValueAt(i, 6).toString();
            sMarkdown = sMarkdown.substring(0,sMarkdown.indexOf("."));
            sMarkdown = sMarkdown.replaceAll(",","");

            String sPrice = jTable1.getValueAt(i, 7).toString();
            sPrice = sPrice.substring(0,sPrice.indexOf("."));
            sPrice = sPrice.replaceAll(",","");

            double dDiscount = Double.parseDouble(sMarkdown) / (Double.parseDouble(sMarkdown)+Double.parseDouble(sPrice)) * 100;
            sItem[j][2] = "-"+dDiscount+"%";
            sItem[j][3] = "-"+sMarkdown;
			sItem[j][4] ="";
          }else{
            sItem[j][2] = "";
            sItem[j][3] = "";
			sItem[j][4] ="";
          }
          j++;//j=1: set barcode,quantity, unit price, VAT, total to sItem(1,i)
          //Barcode
          if (jTable1.getValueAt(i, 0) != null) {
            st = jTable1.getValueAt(i, 1).toString();;
          }
          else {
            st = " ";
          }
          sItem[j][0] = st;

          //col qty
          if (jTable1.getValueAt(i, 4) != null) {
            st = jTable1.getValueAt(i, 4).toString();
          }
          else {
            st = " ";
          }
         sItem[j][1] = st;

          //Unit price
          if (jTable1.getValueAt(i, 5) != null) {
            st = jTable1.getValueAt(i, 5).toString();
            st = ""+ut.convertToDouble(st);
            st = st.substring(0,st.indexOf("."));
          }
          else {
            st = " ";
          }
          sItem[j][2] = st;

          lTotalPrice = Double.parseDouble(sItem[j][1]) *
              Double.parseDouble(st);

          lSubTotal += lTotalPrice;

          //Discount
          if (jTable1.getValueAt(i, 6) != null) {
            st = jTable1.getValueAt(i, 6).toString();
            st = ""+ut.convertToDouble(st);
            st = st.substring(0,st.indexOf("."));
          }
          else {
            st = " ";
          }

          lDiscount += Double.parseDouble(st);

		  //VAT: null
		  sItem[j][3] = "" ;
          //Amount
          if (jTable1.getValueAt(i, 7) != null) {
            st = jTable1.getValueAt(i, 7).toString();
            st = ""+ut.convertToDouble(st);
            st = st.substring(0,st.indexOf("."));
          }
          else {
            st = " ";
          }

          lTotal += Double.parseDouble(st);

          sItem[j][4] = "" + lTotalPrice;
          j++;
          l++;
        }
        String sEnding[] = {
            "" + lSubTotal, "" + lDiscount, "" + lTotal};
        pp.setHeaderReceipt(Header);
        pp.setCustInfo(CustInfo);
        pp.setFileName(DAL.getAppHome()+"\\file\\" +
                         String.valueOf(txtTransID.getText()).toString() +
                         ".pdf");

        /*pp.setFileName("./file/" +
                       String.valueOf(txtTransID.getText()).toString() +
                       ".pdf");*/
        pp.setTypeReceipt(2); //Type of receipt is TNX

        pp.setAcrobatPath(DAL.getAcrobatFile());
        pp.setDelayBeforePrint(DAL.getDelayBeforePrint());
        pp.setDelayAfterPrint(DAL.getDelayAfterPrint());

        pp.setTurnOffAcrobat(DAL.getTurnOffAcrobat());
        pp.setShowDialog(false);
        pp.setIsPrintBarcode(true);
        pp.setTableAlignTNX(tableAlign);
        pp.setItemTNX(sItem);
        pp.setTotalTNX(sEnding);
        pp.setTableTNXSkew(iSkew);
        pp.setTitleTNX(sTitle);

        pp.setIDTNX(String.valueOf(txtTransID.getText()).toString());

        pp.setDeleteFileAfterPrint(true);

        pp.print();
      }
    }
    catch (Exception e1) {
      e1.printStackTrace();
    }
  }
 /* String convertNum(String sNum){
    String sTemp = sNum.replace(',','0');
    long lTemp = Long.parseLong(sTemp);
    lTemp = lTemp/10;
    return ""+lTemp;
  }*/
  //Get Cust Type
  String getEmpCODE(String sTransID, DataAccessLayer DAL){
   String iType="";
   ResultSet rs = null;
   try {
     rs = null;
     String sql = "select EMP_CDE from BTM_POS_EMPLOYEE where EMP_ID = (select EMP_ID from BTM_POS_DETAIL_TRANS where TRANS_ID = '"+sTransID+"' and rownum <=1)";
     rs = DAL.executeQueries(sql);
     if (rs.next()) {
       if (rs.getString("EMP_CDE") != null) {
         iType=rs.getString("EMP_CDE");
       }
     }
     rs.getStatement().close();
   }
   catch (Exception e) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }

   return iType;
 }

  String getCustID(String sTransID, DataAccessLayer DAL){
    String iType="";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "SELECT CUST_ID FROM BTM_POS_DETAIL_TRANS where TRANS_ID = '"+sTransID+"'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("CUST_ID") != null) {
          iType=rs.getString("CUST_ID");
        }
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return iType;
  }

  String getCustInfo(String sCustID){
    String sCustName="";
    ResultSet rs = null;
    try {
      rs = null;
      if (DAL.getFranchiseCust().length() == 13) {
        sCustID = DAL.getFranchiseCust();
      }
      String sql = "SELECT LAST_NAME,total FROM BTM_POS_CUSTOMER where CUST_ID = '"+sCustID+"'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("LAST_NAME") != null) {
          sCustName="K/hang:" +rs.getString("LAST_NAME")+ "\r\n"+ "Doanh so:"+rs.getString("total");
        }
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return sCustName;
  }
  //Get Cust Type
  String getCustType(String sTransID, DataAccessLayer DAL){
    String iType="";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select CUST_FLAG from BTM_POS_CUSTOMER where CUST_ID = (SELECT CUST_ID FROM BTM_POS_DETAIL_TRANS where TRANS_ID = '"+sTransID+"' and rownum <= 1)";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("CUST_FLAG") != null) {
          iType=rs.getString("CUST_FLAG");
        }
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return iType;
  }

  //Get Fran Name
  String getFranName(String sCustID, DataAccessLayer DAL) {
    String iType = "";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select FIRST_NAME from BTM_POS_CUSTOMER where CUST_ID='" +
          sCustID + "'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("FIRST_NAME") != null) {
          iType = rs.getString("FIRST_NAME");
        }
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return iType;
  }
//get Store Name
  public String getStoreName(String strStoreID, DataAccessLayer DAL) {
    String strInfo = "";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select NAME from BTM_POS_STORE where STORE_ID='"+strStoreID+"'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("NAME") != null) {
          strInfo = rs.getString("NAME");
        }else {
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

 //get Item Color
  public String getItemColor(String strItemID, DataAccessLayer DAL) {
    String strInfo = "";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select ATTR1_NAME from BTM_POS_ITEM_PRICE " +
          "where ITEM_ID = '" + strItemID + "' and STORE_ID='" + DAL.getStoreID() +
          "'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("ATTR1_NAME") != null) {
          strInfo = rs.getString("ATTR1_NAME");
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

  //get Item Size
  public String getItemSize(String strItemID, DataAccessLayer DAL) {
    String strInfo = "";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select ATTR2_NAME from BTM_POS_ITEM_PRICE " +
          "where ITEM_ID = '" + strItemID + "' and STORE_ID='" + DAL.getStoreID() +
          "'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("ATTR2_NAME") != null) {
          strInfo = rs.getString("ATTR2_NAME");
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

  //get Art number
  public String getArtNo(String strItemID, DataAccessLayer DAL) {
    String strInfo = "";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select ART_NO from BTM_POS_ITEM_PRICE " +
          "where ITEM_ID = '" + strItemID + "' and STORE_ID='" + DAL.getStoreID() +
          "'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("ART_NO") != null) {
          strInfo = rs.getString("ART_NO");
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

  //======================== Gift Promo Code ===================//
  //check if this trans was winner trans
  //return gift name if trans ws winner trans
  String checkWinnerTrans (String transID){
    String giftName="";
    String sql = "";
    ResultSet rs = null;

    try{
      sql =  " select  g.gift_name from BTM_POS_GIFT_PROMO_HIST p,BTM_POS_GIFT_TYPE g where p.GIFT_TYPE_ID=g.GIFT_TYPE_ID and trans_ID='"+transID +"'";
//    System.out.println(sql);
      rs = DAL.executeQueries(sql);
      if (rs.next()){
        giftName=rs.getString("gift_name");
      }
      rs.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }

    return giftName;
  }

}

class PanelDetailTransaction_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelDetailTransaction adaptee;

  PanelDetailTransaction_btnCancel_actionAdapter(PanelDetailTransaction adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelDetailTransaction_btnPrint_actionAdapter implements java.awt.event.ActionListener {
  PanelDetailTransaction adaptee;

  PanelDetailTransaction_btnPrint_actionAdapter(PanelDetailTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrint_actionPerformed(e);
  }
}
