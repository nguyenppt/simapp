package pos;

import btm.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import common.*;
import btm.attr.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.Timer;
import java.util.TimerTask;
import btm.business.*;
import java.util.Vector;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: mang hinh chinh -> Giao dich moi</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @version 1.0
 */


public class PanelNewSale extends JPanel {

    private static final String MIX_MATCH_ANY ="Any" ;
    private static final String MIX_MATCH_PACKAGE ="All" ;
    private static final String MIX_MATCH_LIST ="List" ;
    public static final String THRESHOLD_TYPE_UNIT = "Unit";
    public static final String THRESHOLD_TYPE_AMOUNT = "Amount";
    public static final String THRESHOLD_AMOUNT_LIST = "Amount_List";//amount of item in list
    public static final String THRESHOLD_TYPE_BOTH = "Unit_And_Amount";

    //================= Flag Button for New Sale
    private static final int NS_NEW_SALE_BTN =200 ;
    private static final int NS_EVEN_EXCHANGE_BTN =201 ;
    private static final int NS_QUANTITY_BTN=202;
    private static final int NS_RETURN_ITEM_BTN =203;
    private static final int NS_DISCOUNT_BTN = 204;
    private static final int NS_DELETE_BTN = 205;
    private static final int NS_ADD_ITEM_BTN =206;
    private static final int NS_MARKDOWN_BTN =207;
    private static final int NS_TOTAL_BTN =208;
    private static final int NS_SEARCH_ITEM_BTN =209 ;
    private static final int NS_CUSTOMER_BTN =2091 ;
    private static final int NS_SALE_EMP_BTN =2092 ;

    //Flag Button for Discount
    private static final int NS_DISCOUNT_PRICE_BTN =210;
    private static final int NS_DISCOUNT_USD_BTN =211;
    private static final int NS_DISCOUNT_PERCENT_BTN =212;
    private static final int NS_DISCOUNT_PERCENT_ALL_BTN =2122;
    //Flag Button for Markdown
    private static final int NS_MARKDOWN_PRICE_BTN =213;
    private static final int NS_MARKDOWN_USD_BTN =214;
    private static final int NS_MARKDOWN_PERCENT_BTN =215;
    //Column Order of Sale Item Table
    private static final int COL_ITEM_ID =0;//
    private static final int COL_ART_NO = 1;
    private static final int COL_ITEM_DESC = 2;//
    private static final int COL_DISTCOUNT_QTY = 3;//discount qty of item
    private static final int COL_QUANTITY =4;//
    private static final int COL_PRICE =5;
    private static final int COL_MARKDOWN =6;
    private static final int COL_MARKDOWN_PER =7;
    private static final int COL_AMOUNT =8;
    private static final int COL_VAT=9;

  private static final int NUM_CHAR_SEARCH =3 ;
//  public static final int DECIMAL_LENGTH =2 ;
  FrameMain frmMain;
  DataAccessLayer DAL;
  Vector vAttr1 = new Vector();
  Vector vAttr2 = new Vector();
  Vector vSaleItemTemp = new Vector(); //vector used to save temp transaction
  Vector vNonPromoItem= new Vector();// store all non promo item, get from BTM_POS_NOT_PROMO
// Flag for return;
  boolean promoAllItem=false;//true if apply promo for all item
  boolean flagSaveGet = false; // flag keep the exist of saved transaction; flag=false: not available transaction saved, true if else
  boolean discCust = false;
  boolean security=false;
  int flagReturn = Constant.SALES_ITEM;
  int flagButton=NS_NEW_SALE_BTN;//dertermine button on screen.
  Utils ut=new Utils();
  ResourceBundle lang = DAL.getFrameLabel(ut.sLanguague, ut.sCountry);
  Object[][] rowdata = new Object[0][0];
 String[] columnNames = new String[]{  lang.getString("ItemID"),
                                       lang.getString("UPC"),
                                       lang.getString("ItemName"),
                                       lang.getString("DistQty"),
                                       lang.getString("Amount") ,
                                       lang.getString("ItemPrice"),
                                       lang.getString("DistQty") + "$",
                                       lang.getString("DistQty") + "%",
                                       lang.getString("AmountDue"),
                                       lang.getString("VAT")};




  DefaultTableModel model = new DefaultTableModel(){
    public boolean isCellEditable(int rowIndex, int mColIndex) {
      return false;
    }
  };

  BorderLayout borderLayout1 = new BorderLayout();


  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jPanel1 = new JPanel();
  JTable tblSaleItem = new JTable(model);
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel lblTotal = new JLabel();
  JLabel lblSubTotal = new JLabel();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  BJButton btnEvenExchange = new BJButton();
  BJButton btnModdifyQuan = new BJButton();
  BJButton btnReturnItem = new BJButton();
  BJButton btnCustomer = new BJButton();
  BJButton btnDiscount = new BJButton();
  BJButton btnDeleteItem = new BJButton();
  BJButton btnAddItem = new BJButton();
  BJButton btnMarkdown = new BJButton();
  BJButton btnTotal = new BJButton();
  BJButton btnSaveTXN = new BJButton();
  BJButton btnGetTXN = new BJButton();
  BJButton btnCancelTXN = new BJButton();
  BJButton btnBack = new BJButton();
  BJButton btnDiscountPrice = new BJButton();
  BJButton btnDiscountAll = new BJButton();
  BJButton btnDiscountUSD = new BJButton();
  BJButton btnDiscountPercent = new BJButton();
  BJButton btnDiscountBack = new BJButton();
  BJButton btnMarkdownPrice = new BJButton();
  BJButton btnMarkdownUSD = new BJButton();
  BJButton btnMarkdownPercent = new BJButton();
  BJButton btnMarkdownBack = new BJButton();
  boolean flagDiscount = false;
  long custID=-1;
  long custIDTemp = -1;
  long saleID=-1;
  long saleIDTemp = -1;
  long custType = -1;
//  long custTypeTemp = -1;
  String custName = "";
  String empName = "";
//  String custNameSaved = "";
//  String phoneSaved ="";
  Customer  savedCust=new Customer();
  String attr1=" ";
  String attr2=" ";
  ///9-3-2006
  String sArtNo="";
//  boolean discountStatus = false;
  StripedTableCellRenderer stripedTable = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);

  public PanelNewSale(  FrameMain frmMain) {
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
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    jPanel1.setLayout(gridLayout1);
    jPanel1.setPreferredSize(new Dimension(289, 40));
    lblTotal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTotal.setForeground(SystemColor.windowBorder);
    lblTotal.setText(lang.getString("TotalItem") + ": 0");
    lblSubTotal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSubTotal.setForeground(SystemColor.windowBorder);
    lblSubTotal.setText(lang.getString("SubTotal")+": 0.00");
    jPanel3.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    jPanel2.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    jPanel3.setBackground(new Color(121, 152, 198));
    jPanel4.setBackground(new Color(121, 152, 198));
    jPanel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 13));
    jPanel4.setLayout(null);
    jPanel2.setBackground(new Color(121, 152, 198));
    jScrollPane1.getViewport().setBackground(Color.white);
    jScrollPane1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    tblSaleItem.setRowHeight(30);
    tblSaleItem.addKeyListener(new PanelNewSale_tblSaleItem_keyAdapter(this));

    btnEvenExchange.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnEvenExchange.setToolTipText(lang.getString("EvenExchange") + "  (F9)");
        btnEvenExchange.setText("<html><center><b>"+lang.getString("EvenExchange")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;F9</html>");
    btnModdifyQuan.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnModdifyQuan.setToolTipText(lang.getString("Quantity") +" (F1)");
    btnModdifyQuan.setActionCommand(lang.getString("Quantity") +" (F6)");
    btnModdifyQuan.setText("<html><center><b>"+lang.getString("Quantity")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "1</html>");
    btnModdifyQuan.addActionListener(new PanelNewSale_btnModdifyQuan_actionAdapter(this));
    btnModdifyQuan.setEnabled(false);
    btnReturnItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnReturnItem.setToolTipText(lang.getString("ReturnItem") + "  (F7)");
    btnReturnItem.setText("<html><center><b>"+lang.getString("Return")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7<" +
    "/html>");
    btnReturnItem.addActionListener(new PanelNewSale_btnReturnItem_actionAdapter(this));

    btnCustomer.setToolTipText(lang.getString("Customer"));
    btnCustomer.setSelectedIcon(null);
    btnCustomer.setText("<html><center><b>"+lang.getString("Customer")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4" +
    "</html>");
    btnCustomer.addActionListener(new PanelNewSale_btnCustomer_actionAdapter(this) );
    btnDiscount.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDiscount.setToolTipText(lang.getString("Discount") + " (F6)");
    btnDiscount.setText("<html><center><b>"+lang.getString("Discount")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "6</html>");
    btnDiscount.addActionListener(new PanelNewSale_btnDiscount_actionAdapter(this));
    btnDeleteItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDeleteItem.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDeleteItem.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;F8</html>");
    btnDeleteItem.addActionListener(new PanelNewSale_btnDeleteItem_actionAdapter(this));
    btnAddItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAddItem.setToolTipText(lang.getString("Add")+" (F2)");
    btnAddItem.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAddItem.addActionListener(new PanelNewSale_btnAddItem_actionAdapter(this));
    btnMarkdown.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnMarkdown.setToolTipText(lang.getString("ViewTXN")+" (F5)");
    btnMarkdown.setText("<html><center><b>"+lang.getString("ViewTXN")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html" +
    ">");
    btnMarkdown.addActionListener(new PanelNewSale_btnMarkdown_actionAdapter(this));
    btnTotal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnTotal.setToolTipText(lang.getString("TotalTXN") +"(F10)");
    btnTotal.setVerifyInputWhenFocusTarget(true);
    btnTotal.setText("<html><center><b>"+lang.getString("TotalTXN")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F10</html>");
    btnTotal.addActionListener(new PanelNewSale_btnTotal_actionAdapter(this));
    btnSaveTXN.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSaveTXN.setToolTipText(lang.getString("Save")+" (F2)");
    btnSaveTXN.setVerifyInputWhenFocusTarget(true);
    btnSaveTXN.setText("<html><center><b>"+lang.getString("Save")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html" +
    ">");
    btnSaveTXN.setEnabled(false); ///Disable Save TXN to memory
    btnSaveTXN.addActionListener(new PanelNewSale_btnSaveTXN_actionAdapter(this));
    btnGetTXN.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnGetTXN.setToolTipText(lang.getString("GetTXN")+"(F2)");
    btnGetTXN.setVerifyInputWhenFocusTarget(true);
    btnGetTXN.setText("<html><center><b>"+lang.getString("GetTXN")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html" +
    ">");
    btnGetTXN.setEnabled(false);///Disable Save TXN to memory
    btnGetTXN.addActionListener(new PanelNewSale_btnGetTXN_actionAdapter(this));
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnBack.setToolTipText(lang.getString("Back")+" (F12 or ESC)");
    btnBack.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnBack.addActionListener(new PanelNewSale_btnBack_actionAdapter(this));
    btnEvenExchange.addActionListener(new PanelNewSale_btnEvenExchange_actionAdapter(this));
    btnDiscountPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDiscountPrice.setToolTipText(lang.getString("DiscountPrice") +" (F2)");
    btnDiscountPrice.setText("<html><center><b>"+lang.getString("DiscountPrice") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;F2</html>");
    btnDiscountPrice.addActionListener(new PanelNewSale_btnDiscountPrice_actionAdapter(this));
    btnDiscountUSD.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDiscountUSD.setToolTipText(lang.getString("Discount")+" $(F3)");
    btnDiscountUSD.setText("<html><center><b>"+lang.getString("Discount")+" $</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnDiscountUSD.addActionListener(new PanelNewSale_btnDiscountUSD_actionAdapter(this));
    btnDiscountPercent.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDiscountPercent.setToolTipText(lang.getString("Discount")+" %(F4)");
    btnDiscountPercent.setText("<html><center><b>"+lang.getString("Discount")+" %</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "F4</html>");
    btnDiscountPercent.addActionListener(new PanelNewSale_btnDiscountPercent_actionAdapter(this));
    btnDiscountBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDiscountBack.setToolTipText(lang.getString("Back")+" (F12)");
    btnDiscountBack.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</h" +
    "tml>");
    btnDiscountBack.addActionListener(new PanelNewSale_btnDiscountBack_actionAdapter(this));

    btnMarkdownPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnMarkdownPrice.setText(lang.getString("MarkDownPrice"));
    btnMarkdownPrice.addActionListener(new PanelNewSale_btnMarkdownPrice_actionAdapter(this));
    btnMarkdownUSD.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnMarkdownUSD.setText(lang.getString("MarkDown") + " USD");
    btnMarkdownUSD.addActionListener(new PanelNewSale_btnMarkdownUSD_actionAdapter(this));
    btnMarkdownPercent.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnMarkdownPercent.setText(lang.getString("MarkDown") + " %");
    btnMarkdownPercent.addActionListener(new PanelNewSale_btnMarkdownPercent_actionAdapter(this));
    btnMarkdownBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnMarkdownBack.setText(lang.getString("Back"));
    btnMarkdownBack.addActionListener(new PanelNewSale_btnMarkdownBack_actionAdapter(this));
    btnCancelTXN.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancelTXN.setToolTipText(lang.getString("Cancel") +" (F11)");
    btnCancelTXN.setText("<html><center><b>"+lang.getString("Cancel") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F11</html>");
    btnCancelTXN.addActionListener(new PanelNewSale_btnCancelTXN_actionAdapter(this));
    btnDiscountAll.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDiscountAll.setText("<html><center><b>"+lang.getString("DiscountAllPer")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center> </b>&nbsp;&nbs" +
    "p;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDiscountAll.addActionListener(new PanelNewSale_btnDiscountAll_actionAdapter(this));
    this.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tblSaleItem, null);
    this.add(jPanel1,  BorderLayout.SOUTH);
    jPanel1.add(jPanel4, null);
    jPanel1.add(jPanel3, null);
    jPanel3.add(lblTotal, null);
    jPanel1.add(jPanel2, null);
    jPanel2.add(lblSubTotal, null);

    //set table model
    tblSaleItem.getTableHeader().setReorderingAllowed(false);
    model.setDataVector(rowdata, columnNames);
//    stripedTable.installInTable(tblSaleItem,Color.lightGray,Color.white,null,null);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    stripedTable.installInTable(tblSaleItem, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

    setTable();
    formatSubTotal();
    getNonPromoItem();
  }

  ////Get items which no apply promo for HOme staff
  void getNonPromoItem(){
    ResultSet rs;
    String sql = "select * from BTM_POS_NOT_PROMO where  store_id ="+ DAL.getStoreID() ;
//    System.out.println("------"+sql);
    rs = DAL.executeQueries(sql);
    try{
      while (rs.next()){
        vNonPromoItem.add(rs.getString("item_id"));
      }
      rs.getStatement().close();
    }catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }

  }
  void formatSubTotal(){
    NumberFormat formatter = new DecimalFormat("#,##0.00");
    String [] str = lblSubTotal.getText().split(":");
    String s = "";
    if (str[1].length()>6){
      s = formatter.format(ut.convertToDouble(str[1]));
    }else{
      s = formatter.format(Double.parseDouble(str[1]));
    }
    lblSubTotal.setText(lang.getString("SubTotal") + ":" + s);
  }

  void setTable()
  {

    tblSaleItem.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    tblSaleItem.setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    TableColumn column = new TableColumn();


    /*TableColumn column1 = new TableColumn();
    //column1 = tblSaleItem.getColumn(ut.ToUpperString(lang.getString("Amount")));
    column1 = tblSaleItem.getColumn()
    column1.setMaxWidth(40);
    column1.setMinWidth(40);*/

    tblSaleItem.getColumnModel().getColumn(COL_ITEM_ID).setMaxWidth(0);
    tblSaleItem.getColumnModel().getColumn(COL_ITEM_ID).setMinWidth(0);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_ITEM_ID).setMaxWidth(0);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_ITEM_ID).setMinWidth(0);

    tblSaleItem.getColumnModel().getColumn(COL_ITEM_DESC).setMaxWidth(200);
    tblSaleItem.getColumnModel().getColumn(COL_ITEM_DESC).setMinWidth(200);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_ITEM_DESC).setMaxWidth(200);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_ITEM_DESC).setMinWidth(200);


    tblSaleItem.getColumnModel().getColumn(COL_PRICE).setMaxWidth(75);
    tblSaleItem.getColumnModel().getColumn(COL_PRICE).setMinWidth(75);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_PRICE).setMaxWidth(75);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_PRICE).setMinWidth(75);

    tblSaleItem.getColumnModel().getColumn(COL_DISTCOUNT_QTY).setMaxWidth(0);
    tblSaleItem.getColumnModel().getColumn(COL_DISTCOUNT_QTY).setMinWidth(0);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_DISTCOUNT_QTY).setMaxWidth(0);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_DISTCOUNT_QTY).setMinWidth(0);

    tblSaleItem.getColumnModel().getColumn(COL_AMOUNT).setMaxWidth(100);
    tblSaleItem.getColumnModel().getColumn(COL_AMOUNT).setMinWidth(100);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_AMOUNT).setMaxWidth(100);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_AMOUNT).setMinWidth(100);

    tblSaleItem.getColumnModel().getColumn(COL_ART_NO).setMaxWidth(100);
    tblSaleItem.getColumnModel().getColumn(COL_ART_NO).setMinWidth(100);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_ART_NO).setMaxWidth(100);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_ART_NO).setMinWidth(100);

    tblSaleItem.getColumnModel().getColumn(COL_MARKDOWN_PER).setMaxWidth(50);
    tblSaleItem.getColumnModel().getColumn(COL_MARKDOWN_PER).setMinWidth(50);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_MARKDOWN_PER).setMaxWidth(50);
    tblSaleItem.getTableHeader().getColumnModel().getColumn(COL_MARKDOWN_PER).setMinWidth(50);






/*
    TableColumn column2 = new TableColumn();
    column2 = tblSaleItem.getColumn(ut.ToUpperString(lang.getString("ItemPrice")));
    column2.setMaxWidth(75);
    column2.setMinWidth(75);

    TableColumn column3 = new TableColumn();
    column3 = tblSaleItem.getColumn( ut.ToUpperString(lang.getString("DistQty")) + "$");
    column3.setMaxWidth(75);
    column3.setMinWidth(75);

    TableColumn column4 = new TableColumn();
    column4 = tblSaleItem.getColumn( ut.ToUpperString(lang.getString("AmountDue")));
    column4.setMaxWidth(100);
    column4.setMinWidth(100);

    TableColumn column5 = new TableColumn();
    column5 = tblSaleItem.getColumn(ut.ToUpperString(lang.getString(ut.ToUpperString(lang.getString("UPC")))));
    column5.setMaxWidth(100);
    column5.setMinWidth(100);
    //store discount quantity
    TableColumn column6 = new TableColumn();
    column6 = tblSaleItem.getColumn(ut.ToUpperString(lang.getString("DistQty")));
    column6.setMaxWidth(0);
    column6.setMinWidth(0);
    column6.setPreferredWidth(0);

    TableColumn column7 = new TableColumn();
    column6 = tblSaleItem.getColumn(ut.ToUpperString(lang.getString("DistQty")) + "%");
    column6.setMaxWidth(50);
    column6.setMinWidth(50);*/
  }
  //set quantity and recalculate markdow , amount
  void setQuantity(String newQuantity){
    double quantity=Double.parseDouble(newQuantity);
    double oldQuantity=0;
    double price=0;
    double unitMarkdown=0;//for 1 unit of item
    double markdown=0;//total markdown of item
    double amountDue=0;
    int row=0;


    row=tblSaleItem.getSelectedRow();
    oldQuantity=Double.parseDouble((tblSaleItem.getValueAt(row,COL_QUANTITY).toString()));
    price=ut.convertToDouble(tblSaleItem.getValueAt(row,COL_PRICE).toString());
    if(row!=-1){
      unitMarkdown=ut.convertToDouble(tblSaleItem.getValueAt(row,COL_MARKDOWN).toString())/oldQuantity;
      markdown=quantity*unitMarkdown;
      amountDue=quantity*(price-unitMarkdown);
      //round off
      markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
      amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);
      //update table
      updateCalculate(row,quantity,markdown,amountDue);
    }
    setStatusLabel();
  }
  //calculate markdown/discount (the same)
  void setDiscountMarkdown(String input,int flag){
    double quantity=0;
    double newPrice=0;
    double price=0;//
    double unitMarkdown=0;//for 1 unit of item
    double markdown=0;//total markdown of item
    double amountDue=0;
    int row=0;

    row=tblSaleItem.getSelectedRow();
    quantity=Double.parseDouble((tblSaleItem.getValueAt(row,COL_QUANTITY).toString()));
    price=ut.convertToDouble((tblSaleItem.getValueAt(row,COL_PRICE).toString()));
    if(row!=-1){
        if(!isVaidate(price,Double.parseDouble(input),flag,quantity)) return;
        //Discount all quantity
        if (flag == NS_DISCOUNT_BTN || flag == NS_DISCOUNT_PRICE_BTN ||flag == NS_MARKDOWN_BTN ||flag == NS_MARKDOWN_PRICE_BTN) {
//          newPrice = Double.parseDouble(input);
//          unitMarkdown = price - newPrice;
//          markdown=quantity * unitMarkdown;
//          amountDue=quantity*(price-unitMarkdown);
          newPrice = Double.parseDouble(input)/quantity;
          unitMarkdown = price - newPrice;
          markdown=quantity * unitMarkdown;
          amountDue=quantity*(price-unitMarkdown);

          //round off
          amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);
          markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
        }
        //Discount all quantity
        else if (flag == NS_DISCOUNT_USD_BTN ||flag == NS_MARKDOWN_USD_BTN) {
//          unitMarkdown = Double.parseDouble(input);
//          newPrice = price - unitMarkdown;
//          markdown=quantity * unitMarkdown;
//          amountDue=quantity*(price-unitMarkdown);

          unitMarkdown = Double.parseDouble(input)/quantity;
          newPrice = price - unitMarkdown;
          markdown=quantity * unitMarkdown;
          amountDue=quantity*(price-unitMarkdown);

          //round off
          amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);
          markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
        }
        else if (flag == NS_DISCOUNT_PERCENT_BTN || flag == NS_MARKDOWN_PERCENT_BTN) {
          unitMarkdown = Double.parseDouble(input) * price / 100;
          newPrice = price - unitMarkdown;
          markdown=quantity * unitMarkdown;
          amountDue=quantity*(price-unitMarkdown);
          //round off
          amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);
          markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
        }
        //update table
        updateCalculate(row,quantity,markdown,amountDue);
    }
    setStatusLabel();
  }
  //discount all item by percent
  void setDiscountMarkdownAll(String input,int flag){
    double quantity=0;
    double newPrice=0;
    double price=0;//
    double unitMarkdown=0;//for 1 unit of item
    double markdown=0;//total markdown of item
    double amountDue=0;

    for (int i=0; i<tblSaleItem.getRowCount(); i++){
      quantity = Double.parseDouble( (tblSaleItem.getValueAt(i, COL_QUANTITY).
                                    toString()));
      price = ut.convertToDouble( (tblSaleItem.getValueAt(i, COL_PRICE).
                                   toString()));
      if (!isVaidate(price, Double.parseDouble(input), flag,quantity)){
        return;
      }
      unitMarkdown = Double.parseDouble(input) * price / 100;
      newPrice = price - unitMarkdown;
      markdown = quantity * unitMarkdown;
      amountDue = quantity * (price - unitMarkdown);
      //round off
      amountDue = ut.getRound(amountDue, Constant.PM_DECIMAL_LENGTH);
      markdown = ut.getRound(markdown, Constant.PM_DECIMAL_LENGTH);

      //update table
      updateCalculate(i, quantity, markdown, amountDue);
    }
    setStatusLabel();
  }
  //set color(
  void setColor(){
//    stripedTable.installInTable(tblSaleItem, Color.lightGray, Color.white, null, null);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    stripedTable.installInTable(tblSaleItem, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

  }
  //check
  boolean isVaidate(double price, double inputMarkdown,int flag,double qty){
    boolean isValid=true;

    if (flag == NS_DISCOUNT_PRICE_BTN||flag == NS_MARKDOWN_PRICE_BTN) {
      if(qty*price<inputMarkdown){
        ut.showMessage(frmMain,lang.getString("TT001"),  lang.getString("MS062_NewPrice"));
        isValid=false;
      }
    }
    else if (flag == NS_DISCOUNT_USD_BTN ||flag == NS_MARKDOWN_USD_BTN) {
      if(qty*price<inputMarkdown){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS063_Markdown"));
        isValid=false;
      }
    }
    else if (flag == NS_DISCOUNT_PERCENT_BTN || flag == NS_MARKDOWN_PERCENT_BTN) {
      if(inputMarkdown>100){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS064_MarkdownPer"));
        isValid=false;
      }

    }

    return isValid;
  }
  //update caculate at currrent row
  void updateCalculate(int row, double quantity, double markdown, double amountDue){
    double temp = 0;
    if((amountDue + markdown)!=0){
      temp = (markdown/ (amountDue + markdown));
    }
    tblSaleItem.setValueAt(String.valueOf(quantity), row, COL_QUANTITY);
    tblSaleItem.setValueAt(ut.formatNumber("" + markdown), row, COL_MARKDOWN);
    tblSaleItem.setValueAt(ut.formatNumber("" + ut.getRound ( temp * 100,2 ) ), row, COL_MARKDOWN_PER);
    tblSaleItem.setValueAt(ut.formatNumber("" + amountDue), row, COL_AMOUNT);
  }
  //update calculate at currrent row
  void updateCalculate(int row, double quantity,double discountQty, double markdown, double amountDue){

    tblSaleItem.setValueAt(String.valueOf(quantity), row, COL_QUANTITY);
    tblSaleItem.setValueAt(String.valueOf(discountQty), row, COL_DISTCOUNT_QTY);
    tblSaleItem.setValueAt(ut.formatNumber("" + markdown), row, COL_MARKDOWN);
    tblSaleItem.setValueAt(ut.formatNumber("" + ut.getRound ( (markdown/ (amountDue + markdown)) * 100,2 ) ), row, COL_MARKDOWN_PER);
    tblSaleItem.setValueAt(ut.formatNumber("" + amountDue), row, COL_AMOUNT);
  }

  //remove row
  void removeRow(){
    if (flagReturn == Constant.RETURN_ITEM){
      while (tblSaleItem.getRowCount()>0){
        model.removeRow(0);
      }
    }
  }
//add row
  void addItemRow(Object[] item){
    String itemId="";
    boolean isExist=false;
    double quantity = 0;
    int row=0;

    double price=0;
    double unitMarkdown=0;//for 1 unit of item
    double markdown=0;//total markdown of item
    double amountDue=0;

//    for (int i = 0; i < tblSaleItem.getRowCount(); i++) {
//      itemId = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
//      quantity = Double.parseDouble(tblSaleItem.getValueAt(i,COL_QUANTITY).toString());
//      if (itemId.equals(item[0].toString()) && quantity>0) {
//        isExist = true;
//        row = i;
//        break;
//      }
//    }
    //if item does not exist
    if (!isExist) {
      model.addRow(item);

    }
    //if item exist, increase add new quantity into available quantity
    else {
      //available qty
      quantity = Double.parseDouble( (tblSaleItem.getValueAt(row, COL_QUANTITY).
                                    toString()));
      price = ut.convertToDouble( tblSaleItem.getValueAt(row, COL_PRICE).
                                   toString());
      markdown = ut.convertToDouble(tblSaleItem.getValueAt(row,
          COL_MARKDOWN).toString());
      unitMarkdown = markdown / quantity;
      //new quantity
      quantity = quantity + Double.parseDouble(item[4].toString());
      markdown = quantity * unitMarkdown;
      amountDue = quantity * (price - unitMarkdown);
      //update table
      updateCalculate(row, quantity, markdown, amountDue);
    }

    //set focus for last row
    if(tblSaleItem.getRowCount()>0){
      tblSaleItem.setRowSelectionInterval(tblSaleItem.getRowCount()-1, tblSaleItem.getRowCount()-1);
    }

    //show current row
    Rectangle bounds = tblSaleItem.getCellRect(tblSaleItem.getRowCount()-1,0,true);
    jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

    setStatusLabel();
    setColor();
  }
  //delete row
  void deleteItemRow(){
    if(tblSaleItem.getSelectedRow()!=-1){
      if (flagReturn == Constant.RETURN_ITEM){
        double qty = Double.parseDouble(tblSaleItem.getValueAt(tblSaleItem.
            getSelectedRow(), COL_QUANTITY).toString());
        if (qty < 0){
          frmMain.pnlReturnItem.vSql.remove(tblSaleItem.getSelectedRow());
          frmMain.pnlReturnItem.vSql1.remove(tblSaleItem.getSelectedRow());
        }
      }
      model.removeRow(tblSaleItem.getSelectedRow());
      if(tblSaleItem.getRowCount()>0){
        tblSaleItem.setRowSelectionInterval(0, 0);
      }else{
        flagReturn = Constant.SALES_ITEM;
      }
    }
    setStatusLabel();
    setColor();
  }
  //
  void setStatusLabel(){
    double subTotal=0;
    double totalItem=0;
    if(tblSaleItem.getRowCount()>0){
      for (int i = 0; i < tblSaleItem.getRowCount(); i++) {
        totalItem+=Double.parseDouble((tblSaleItem.getValueAt(i,COL_QUANTITY).toString()));
        subTotal+=ut.convertToDouble(tblSaleItem.getValueAt(i,COL_AMOUNT).toString());
      }
      lblTotal.setText(lang.getString("TotalItem") + ": " + String.valueOf(totalItem));
      lblSubTotal.setText(lang.getString("SubTotal") + ": " + ut.formatNumber("" + subTotal));
    }else{
      lblTotal.setText(lang.getString("TotalItem") + ": 0");
      lblSubTotal.setText(lang.getString("SubTotal") + ": 0");
    }
  }
  boolean isExistItem(){
    if(tblSaleItem.getRowCount()>0){
      return true;
    }else{
      return false;
    }
  }
  //get all sale item in transaction
  Vector getSaleItem(){
    Vector vSaleItem=new Vector();
    Item item=  new Item();
    String itemId="";
    String artNo="";
//    int quantity = 0;
    double quantity = 0;
    double discQty=0;
    double price = 0;
    String desc="";
    double markdown=0;
    double markdownPer=0;
    double amountDue=0;
    double VAT = 0;
    if (custID == 0){
      custID = -1;
    }
    if (flagReturn == Constant.RETURN_ITEM){
      for (int i = 0; i < tblSaleItem.getRowCount(); i++) {
//        double qty = Double.parseDouble(tblSaleItem.getValueAt(i, COL_QUANTITY).toString());
          itemId = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
          artNo = tblSaleItem.getValueAt(i,COL_ART_NO).toString();
          desc = tblSaleItem.getValueAt(i, COL_ITEM_DESC).toString();
          price = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_PRICE).toString());
          quantity = Double.parseDouble(tblSaleItem.getValueAt(i, COL_QUANTITY).toString());
//          if(!tblSaleItem.getValueAt(i,COL_DISTCOUNT_QTY).toString().equals("")){
//            discQty = Double.parseDouble(tblSaleItem.getValueAt(i,COL_DISTCOUNT_QTY).toString());
//          }
          markdown = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_MARKDOWN).toString());
//          if(!tblSaleItem.getValueAt(i,COL_MARKDOWN_PER).toString().equals("")){
//            markdownPer = ut.convertToDouble(tblSaleItem.getValueAt(i,COL_MARKDOWN_PER).toString());
//          }
          amountDue = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_AMOUNT).toString());
          VAT = 0;//ut.convertToDouble(tblSaleItem.getValueAt(i, COL_VAT).toString());
          //BTMProd
          item = new Item(itemId,desc, price, quantity, markdown,"", 0, amountDue,custID,attr1,attr2, VAT,saleID);
//          item = new Item(itemId, desc, price, quantityR, markdown, "", 0,
//                          amountDue,custID);
//          System.out.println("Quantity: " + quantity + " + Amount Due: " + amountDue);
          vSaleItem.add(item);
      }
    }else{
      for (int i = 0; i < tblSaleItem.getRowCount(); i++) {
        itemId = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
        artNo = tblSaleItem.getValueAt(i,COL_ART_NO).toString();
        desc = tblSaleItem.getValueAt(i, COL_ITEM_DESC).toString();
        price = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_PRICE).
                                   toString());
        quantity = Double.parseDouble(tblSaleItem.getValueAt(i, COL_QUANTITY).
                                    toString());
//        discQty = Double.parseDouble(tblSaleItem.getValueAt(i,COL_DISTCOUNT_QTY).toString());
        markdown = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_MARKDOWN).
                                      toString());
//        markdownPer = ut.convertToDouble(tblSaleItem.getValueAt(i,COL_MARKDOWN_PER).toString());
        amountDue = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_AMOUNT).
                                       toString());
        attr1 = vAttr1.elementAt(i).toString();
        attr2 = vAttr2.elementAt(i).toString();
        VAT = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_VAT).toString());
        //BTMProd
        item = new Item(itemId,desc, price, quantity,markdown, "", 0,
                        amountDue,custID,attr1,attr2, VAT,saleID);
        vSaleItem.add(item);
      }
    }
    return vSaleItem;
  }
  //vector get all return Item
//  Vector getReturnItem(){
//    return frmMain.pnlReturnItem.getReturnItem();
//  }
  //go to New Sale
  void goBackNewSale(){
    frmMain.setInputLabel(lang.getString("MS017_EnterItem"));
    frmMain.setInputText("");
    flagButton=NS_NEW_SALE_BTN;
    frmMain.removeButton();
    showNewSaleButton();


    if (DAL.getFranchiseCust().length() == 13) {
      String cust = DAL.getFranchiseCust();

      String sql = "select cust_id, first_name, last_name,   address1, address2,  home_no, work_no, mobile_no, email,   city, county, country, fax1, fax2, cust_comment, to_char(created_date,'dd/mm/yyyy') created_date, cust_flag,first_name ||' ' || last_name as fullname   "
          + " from BTM_POS_CUSTOMER where  cust_id =" + cust;
      ResultSet rsCust = DAL.executeQueries(sql);
      try {
        if (rsCust.next()) {
          custID = Long.parseLong(rsCust.getString("cust_id"));
          custName = rsCust.getString("fullname");
          custType = Long.parseLong(rsCust.getString("cust_flag"));
          frmMain.lblName.setText(lang.getString("Name")+": " + custName);
          frmMain.lblPhone.setText(lang.getString("Phone")+": " + rsCust.getString("work_no"));

        }
        else {
          ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS065_NoCusFound"));
        }
        rsCust.getStatement().close();
      }
      catch (Exception ex) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      }
    }
  }
  //show button in Frame Main
  void showNewSaleButton(){
    if(!flagSaveGet){
      frmMain.removeButton();
      frmMain.showButton(btnModdifyQuan);
      frmMain.showButton(btnDiscount);
      frmMain.showButton(btnCustomer);
//    frmMain.showButton(btnAddItem);
      frmMain.showButton(btnDeleteItem);
      frmMain.showButton(btnMarkdown);
      frmMain.showButton(btnReturnItem);
      frmMain.showButton(btnEvenExchange);
      frmMain.showButton(btnTotal);
      frmMain.showButton(btnSaveTXN);
      frmMain.showButton(btnCancelTXN);
      frmMain.showButton(btnBack);
    }else{
      frmMain.removeButton();
      frmMain.showButton(btnModdifyQuan);
      frmMain.showButton(btnDiscount);
      frmMain.showButton(btnCustomer);
      frmMain.showButton(btnDeleteItem);
      frmMain.showButton(btnMarkdown);
      frmMain.showButton(btnReturnItem);
      frmMain.showButton(btnEvenExchange);
      frmMain.showButton(btnTotal);
      frmMain.showButton(btnGetTXN);
      frmMain.showButton(btnCancelTXN);
      frmMain.showButton(btnBack);
    }
  }
  //show button in Frame Main
//  void showGetSavedSaleButton(){
//    frmMain.removeButton();
//    frmMain.showButton(btnModdifyQuan);
//    frmMain.showButton(btnDiscount);
//    frmMain.showButton(btnCustomer);
//    frmMain.showButton(btnDeleteItem);
//    frmMain.showButton(btnMarkdown);
//    frmMain.showButton(btnReturnItem);
//    frmMain.showButton(btnEvenExchange);
//    frmMain.showButton(btnTotal);
//    frmMain.showButton(btnGetTXN);
//    frmMain.showButton(btnCancelTXN);
//    frmMain.showButton(btnBack);
//  }
  //show button in Frame Main
  void showDiscountButton(){
    frmMain.showButton(btnDiscountAll);
    frmMain.showButton(btnDiscountPrice);
    frmMain.showButton(btnDiscountUSD);
    frmMain.showButton(btnDiscountPercent);
    frmMain.showButton(btnDiscountBack);
  }
    //show button in Frame Main
  void showMarkdownButton(){
    frmMain.showButton(btnMarkdownPrice);
    frmMain.showButton(btnMarkdownUSD);
    frmMain.showButton(btnMarkdownPercent);
    frmMain.showButton(btnMarkdownBack);
  }
  void deleteNewSale(){
    while(model.getRowCount() >0){
      model.removeRow(0);
    }
  }

//TrungNT 070706
  //Check quantity of item when input the table of sale
//  boolean checkQuantity(String item_id, String store_id, int quantity) {
//    boolean bFlag = true;
//    String sqlQuery = "";
////    setOpenConnection();
//    Statement stmt = null;
//    ResultSet rs = null;
//    sqlQuery = "select STOCK_ON_HAND from BTM_POS_ITEM_LOC_SOH " +
//        "where ITEM_ID='" + item_id + "' and STORE_ID='" + store_id + "'";
//    String strQuantity="";
//    System.out.println("HUY:"+sqlQuery);
//    try {
//      stmt = DAL.getConnection().createStatement();
//      rs = DAL.executeQueries(sqlQuery, stmt);
//      if (rs.next()) {
//        strQuantity = rs.getString("STOCK_ON_HAND");
//        System.out.println("AAA : " + strQuantity);
//        if (Integer.parseInt(strQuantity)<quantity){
//          bFlag = false;
//        }
//      }
//      rs.close();
//      stmt.close();
//    }
//    catch (Exception ex) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(ex);
//    }
//
//    return bFlag;
//  }


  public int countDot(String sNode) {
    char[] aNode = sNode.toCharArray();
    int i, iNum = 0;
    for (i = 0; i < aNode.length; i++) {
      if (aNode[i] == '.') {
        iNum++;
      }
    }
    return iNum;
  }


  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput=frmMain.getInputText();
//    DataAccessLayer DAL =frmMain.getDAL();
    String sqlQuery="";
    Item item=new Item();
//    System.out.println(mainInput.indexOf("-"));

    if (mainInput.equalsIgnoreCase("S") && flagButton!=NS_CUSTOMER_BTN && flagButton!=NS_SEARCH_ITEM_BTN) {
      flagButton=NS_SEARCH_ITEM_BTN;
      frmMain.setInputLabel(lang.getString("MS067_EnterKeyWord"));
      frmMain.setInputText("");
      return;
    }
    if (mainInput.equalsIgnoreCase("C") && flagButton!=NS_CUSTOMER_BTN && flagButton!=NS_SEARCH_ITEM_BTN) {
      flagButton=NS_CUSTOMER_BTN;
      frmMain.setInputLabel(lang.getString("CustomerID"));
      frmMain.setInputText("");
      return;
    }
    if (mainInput.equalsIgnoreCase("E") && flagButton!=NS_CUSTOMER_BTN && flagButton!=NS_SEARCH_ITEM_BTN) {
      flagButton=NS_SALE_EMP_BTN;
      frmMain.setInputLabel(lang.getString("EmployeeID"));
      frmMain.setInputText("");
      return;
    }



    //New function

    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            //Home
            String userInfo="";//store weight of fruit
            double qty=1.0;//default value assigned to quantity

            if (mainInput.indexOf("-")!=-1){
              ut.showMessage(frmMain,lang.getString("TT001"),  lang.getString("MS068_InputGreater0"));
              frmMain.setInputText("");
              return;
            }

            //split item id and user info
            if(mainInput.length()>Constant.LENGHT_UPC_INPUT){
              userInfo=mainInput.substring(Constant.LENGHT_UPC_INPUT);
              mainInput=mainInput.substring(0,Constant.LENGHT_UPC_INPUT);
//              System.out.println("id: "+mainInput+ " info:  "+userInfo);
              qty = Double.parseDouble(userInfo.substring(0,3)) + Double.parseDouble(userInfo.substring(3)) / 1000;
//              System.out.println(qty);

            }

            //search item and add that item to table
            if (flagButton == NS_NEW_SALE_BTN) {
              //Change EAN 8,EAN 12 to EAN 13
              if (mainInput.length() == 4) {//BTMProduct
                mainInput="20000000" +mainInput;
                mainInput += ut.getCheckDigitEAN13(mainInput);

              }else  if (mainInput.length() < 13) {
                int len = mainInput.length();
                for (int i = 0; i < Constant.LENGHT_UPC_INPUT - len; i++) {
                  mainInput = "0" + mainInput;
                  frmMain.setInputText(mainInput);
                }

              }
              //Fix check digit in case wrong check digit from supplier
//              if(!checkBarCodeStandard(mainInput)){
//                mainInput=mainInput.substring(0,12)+ String.valueOf(ut.getCheckDigitEAN13(mainInput.substring(0,12)));
//              }
//
              //check item code
              if (!ut.isLongIntInput(frmMain, mainInput)) {
                frmMain.setInputText("");
                return;
              }
              Statement stmt = null;
              ResultSet rs=null;
              sqlQuery="Select * from BTM_POS_ITEM_PRICE where ART_NO='" +mainInput+"' and STORE_ID = " + DAL.getStoreID() ;
//              System.out.println(sqlQuery);
//              rs=DAL.executeQueries(sqlQuery);
              try {
                stmt = DAL.getConnection().createStatement();
                rs = DAL.executeQueries(sqlQuery,stmt);
                if (rs.next()) {
                  attr1 = rs.getString("attr1_name");
                  attr2 = rs.getString("attr2_name");
                  sArtNo = rs.getString("ART_NO");
//                  System.out.println(attr1);
//                  System.exit(0);
                  if (attr1 == null) attr1 = " ";
                  if (attr2 == null) attr2 = " ";
                  vAttr1.add(attr1);
                  vAttr2.add(attr2);
                  if (DAL.getFranchiseCust().length() == 13) {
                    item = new Item(rs.getString("ITEM_ID"),rs.getString("ITEM_DESC"),rs.getDouble("UNIT_COST")*(1+rs.getDouble("VAT")/100),qty,0,"",0,rs.getDouble("UNIT_PRICE"),0,attr1,attr2, rs.getDouble("VAT"),0);

                  }else{
                    item = new Item(rs.getString("ITEM_ID"),rs.getString("ITEM_DESC"),rs.getDouble("UNIT_PRICE"),qty,0,"",0,rs.getDouble("UNIT_PRICE"),0,attr1,attr2, rs.getDouble("VAT"),0);
                  }
                    //add to New Sale table
                    addItemRow(new Object[] {item.getId(),sArtNo,
                                          item.getDescription(),"0",
                                          String.valueOf(item.getQuantity()), //noted: type of quantity is double, not mean quantity for return purpose
                                          ut.formatNumber("" + item.getunitPrice()),
                                          ut.formatNumber("0"),"0.00",
                                          ut.formatNumber("" + item.getunitPrice()*item.getQuantity()),//amount due = price*qty
                                          ut.formatNumber("" + item.getVAT())
                               });

                }else{
                  ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS069_NoItemFound"));
                }
                rs.close();
                stmt.close();
              }catch (Exception ex) {
                ex.printStackTrace();
              }
            }
            //modify quantity
            else if (flagButton == NS_QUANTITY_BTN) {
              //check quantity

              if (!ut.isFloatInput(frmMain, mainInput)) {
                ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS070_QtyGreater0"));

                return;
              }
              if (Double.parseDouble( mainInput)<=0) {
                ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS070_QtyGreater0"));
                return;
              }
              //check cashier
              String s="";
              if(mainInput.equals("1")){
                s = "\r\n";
                s += "============================================================"+"\r\n";
                s +=lang.getString("Date") + ": "+ut.getSystemDateTime()  + " ====> "+ lang.getString("Empployee")+DAL.getEmpPosID1()+ lang.getString("SetQuantity") + "=1"+ "\r\n";
                s += "============================================================" + "\r\n";

                s += tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(),
                                           COL_ITEM_ID).toString() + ";" +
                    tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(),
                                           COL_ART_NO).toString() + ";" +
                    tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(),
                                           COL_ITEM_DESC).toString() + ";" +
                    tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(),
                                           COL_QUANTITY).toString() + ";" +
                    tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(),
                                           COL_PRICE).toString() + ";" +
                    tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(),
                                           COL_AMOUNT).toString();
                ut.writeToTextFile(DAL.getErrorFile(), s);
              }
              //end check cashier

              setQuantity(mainInput);
              goBackNewSale();

            }
            //modify discount
            else if (flagButton == NS_DISCOUNT_BTN ||flagButton == NS_DISCOUNT_PRICE_BTN ||flagButton == NS_DISCOUNT_USD_BTN ||flagButton == NS_DISCOUNT_PERCENT_BTN ||flagButton == NS_DISCOUNT_PERCENT_ALL_BTN) {
              //check discoutn
              if (!ut.isIntInput(frmMain, mainInput)) {
                frmMain.setInputText("");
                return;
              }

                double inputValue = Double.parseDouble(mainInput);
                int row = tblSaleItem.getSelectedRow();
                double inputFloatValue = Double.parseDouble(mainInput);
                double unitPrice = ut.convertToDouble(tblSaleItem.getValueAt(row, COL_PRICE).
                                                      toString());
                double amountDue = ut.convertToDouble(tblSaleItem.getValueAt(row, COL_AMOUNT).
                                                      toString());
                double quantity=Double.parseDouble((tblSaleItem.getValueAt(row,COL_QUANTITY).toString()));

                //check input
                if (inputValue < 1 || inputFloatValue >= unitPrice) {
                  if (flagButton == NS_DISCOUNT_PERCENT_BTN) {
                    if (inputValue > 100 || inputValue < 0) {
                      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS071_DiscountGreate0Less100"));
                      return;
                    }
                  }
                  else if (flagButton == NS_DISCOUNT_USD_BTN) {
                    if (inputValue > amountDue || inputValue < 0) {
                      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS071_DiscountGreate0Less") + " " +
                                     amountDue + " ("+lang.getString("AmountDue")+")");
                      return;
                    }
                  }
                  else {
                    if (inputValue < 0) {
                      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS037_PriceGreater0"));
                      return;
                    }
                    else if (inputValue > unitPrice*quantity) {
                      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS073_PriceLower") +
                                     " " +
                                     unitPrice + " ("+lang.getString("UnitPrice")+")");
                      return;
                    }
                  }
                }

                if (!ut.isFloatInput(frmMain, mainInput)) {
                  frmMain.setInputText("");
                  return;
                }
                if(flagButton==NS_DISCOUNT_PERCENT_ALL_BTN){
                  setDiscountMarkdownAll(mainInput, flagButton);
                }else{
                  setDiscountMarkdown(mainInput, flagButton);
                }
                goBackNewSale();
            }
            //modify markdown
            else if (flagButton == NS_MARKDOWN_BTN ||flagButton == NS_MARKDOWN_PRICE_BTN ||flagButton == NS_MARKDOWN_USD_BTN ||flagButton == NS_MARKDOWN_PERCENT_BTN) {
              //check discoutn
              if (!ut.isFloatInput(frmMain, mainInput)) {
                return;
              }
              setDiscountMarkdown(mainInput,flagButton);
              goBackNewSale();
            //search item
            }
            else if (flagButton == NS_CUSTOMER_BTN) {
              //check id
              if (!ut.isLongIntInput(frmMain, mainInput)) {
                frmMain.setInputText("");
                return;
              }

              ResultSet rsCust;
              if (DAL.getFranchiseCust().length() == 13) {
                mainInput=DAL.getFranchiseCust();
              }

              String sql = "select cust_id, first_name, last_name,   address1, address2,  home_no, work_no, mobile_no, email,   city, county, country, fax1, fax2, cust_comment, to_char(created_date,'dd/mm/yyyy') created_date, cust_flag,first_name ||' ' || last_name as fullname   "
                  +" from BTM_POS_CUSTOMER where  cust_id ="+mainInput;
              rsCust = DAL.executeQueries(sql);
              try{
                if(rsCust.next()){
                  custID = Long.parseLong(rsCust.getString("cust_id"));
                  custName = rsCust.getString("fullname");
                  custType = Long.parseLong(rsCust.getString("cust_flag"));
                  frmMain.lblPhone.setText(lang.getString("CustName")+": "+custName);
//                  frmMain.lblPhone.setText(lang.getString("Phone")+": "+rsCust.getString("work_no"));

                }
                else {
                  ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS065_NoCusFound"));
                }
                rsCust.getStatement().close();
              }catch (Exception ex) {
                ExceptionHandle eh = new ExceptionHandle();
                eh.ouputError(ex);
              }
              goBackNewSale();
            }
            else if (flagButton == NS_SALE_EMP_BTN) {
              //check id
              if (!ut.isLongIntInput(frmMain, mainInput)) {
                frmMain.setInputText("");
                return;
              }

              ResultSet rsSale;

              String sql = "select emp_id , first_name ||' ' || last_name as fullname  "
                  +" from BTM_POS_EMPLOYEE where  emp_id ="+mainInput;
              rsSale = DAL.executeQueries(sql);
              try{
                if(rsSale.next()){
                  saleID = Long.parseLong(rsSale.getString("emp_id"));
                  empName = rsSale.getString("fullname");
                  frmMain.lblName.setText(lang.getString("EmpName")+": "+empName);
                }
                else {
                  ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS066_NoEmpFound"));
                }
                rsSale.getStatement().close();
              }catch (Exception ex) {
                ExceptionHandle eh = new ExceptionHandle();
                eh.ouputError(ex);
              }
              goBackNewSale();
            }

            else if (flagButton == NS_SEARCH_ITEM_BTN) {
              //check input
              if (mainInput.length()<NUM_CHAR_SEARCH){
                ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS074_RequireThreeLetters"));
                return;
              }
              Statement stmt = null;
              ResultSet rs=null;
              DialogSearchResultItem dlgSearchResultItem = new DialogSearchResultItem(frmMain,
                  lang.getString("TT043"), true);
              //add seach result item
              sqlQuery="Select * from BTM_POS_ITEM_PRICE where UPPER(ITEM_DESC) LIKE UPPER('%" +mainInput+"%')";
//              System.out.println(sqlQuery);
              try {
                stmt = DAL.getConnection().createStatement();
                rs = DAL.executeQueries(sqlQuery,stmt);
                while (rs.next()) {
                  attr1 = rs.getString("attr1_name");
                  attr2 = rs.getString("attr2_name");
                  sArtNo= rs.getString("ART_NO");
                  if (attr1 == null) attr1 = " ";
                  if (attr2 == null) attr2 = " ";
                  vAttr1.add(attr1);
                  vAttr2.add(attr2);
                  if (DAL.getFranchiseCust().length() == 13) {
                    item = new Item(rs.getString("Item_id"),rs.getString("ITEM_DESC"),rs.getDouble("UNIT_COST")*(1+rs.getDouble("VAT")/100),1,0,"",0,rs.getDouble("UNIT_PRICE"),0,rs.getString("ART_NO"),attr1,attr2);

                  }else{
                    item = new Item(rs.getString("Item_id"),rs.getString("ITEM_DESC"),rs.getDouble("UNIT_PRICE"),1,0,"",0,rs.getDouble("UNIT_PRICE"),0,rs.getString("ART_NO"),attr1,attr2);

                  }
                  //add to Search table
                  dlgSearchResultItem.addItemRow(new Object[] {item.getArtNo(),
                                                 item.getDescription(),
                                                 String.valueOf(item.getunitPrice())});
                }
              }catch (Exception ex) {
                ExceptionHandle eh = new ExceptionHandle();
                eh.ouputError(ex);
              }
              if (dlgSearchResultItem.isExistItem()) {
                dlgSearchResultItem.setVisible(true);
                if (dlgSearchResultItem.isOKButton()) {
                  item = dlgSearchResultItem.getItem();
//===========get art_no
                  ResultSet rs5;
                  String sql = "select * from BTM_POS_ITEM_PRICE where Art_No="+item.getArtNo();
                  rs5 = DAL.executeQueries(sql);
                  try{
                    if (rs5.next()) {
//                      System.out.println("oooooooooooookkkkkkk");
                      item.setId(rs5.getString("Item_id"));
                    }
                    rs5.getStatement().close();
                  }catch (Exception ex) {
                    ExceptionHandle eh = new ExceptionHandle();
                    eh.ouputError(ex);
                  }
                  //add to New Sale table
                  addItemRow(new Object[] {item.getId(),item.getArtNo(),
                             item.getDescription(),"0",
                             String.valueOf(item.getQuantity()),
                             ut.formatNumber("" + item.getunitPrice()),
                             ut.formatNumber("0"),"0.00",
                             ut.formatNumber("" + item.getunitPrice()),
                             ut.formatNumber("" + item.getVAT())
                  });
                }
              }else{
                ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS069_NoItemFound"));
                dlgSearchResultItem.dispose();
              }
              try{
                rs.close();
                stmt.close();
              }catch(Exception ex){
                ex.printStackTrace();
              }
              goBackNewSale();
            }
            frmMain.setInputText("");
            flagDiscount = false;
            //format Sub Total
            formatSubTotal();
          }//end ENTER key
        }

        void btnEvenExchange_actionPerformed(ActionEvent e) {
          //if return item while Even exchange=> error
          if(tblSaleItem.getRowCount()>0 ){
            ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS075_CompleteTrans"));
            return;
          }
          //using Dlg Confirm User
//          DialogConfirmUser dlgConfirm = new DialogConfirmUser(frmMain,"JPOS - Confirm User",true,frmMain);
//          dlgConfirm.setLocation(210,170);
//          dlgConfirm.setVisible(true);
//          if (dlgConfirm.done) {
//            frmMain.setTitle("JPOS - EVEN EXCHANGE");
//            frmMain.showScreen(Constant.SCR_EVEN_EXCHANGE);
//            frmMain.pnlEvenExchange.initScreen();
//          }
          //using Dlg Checking Account
          //check manager
          DialogCheckAccount dlgCheckAccount = new DialogCheckAccount(frmMain,
              lang.getString("TT044"), true, frmMain);
          dlgCheckAccount.setLocation(210, 170);
          dlgCheckAccount.setVisible(true);
          if (dlgCheckAccount.done) {
            String s = "";
            s = "\r\n";
            s += "============================================================" +
                "\r\n";
            s += lang.getString("Date") +  ": " + ut.getSystemDateTime() + " ====> " + lang.getString("Cashier") + ":" +
                DAL.getEmpPosID1() + " =====>  " + lang.getString("Observer")+ ": " +
                dlgCheckAccount.getObserverID() + "=====> " + ut.ToUpperString(lang.getString("EvenExchange")) + "\r\n";
            s += "============================================================" +
                "\r\n";
            ut.writeToTextFile(DAL.getErrorFile(), s);
            //end check cashier
            frmMain.setTitle(lang.getString("TT045"));
            frmMain.showScreen(Constant.SCR_EVEN_EXCHANGE);
            frmMain.pnlEvenExchange.initScreen();
          }
          else {
            String s = "";
            s = "\r\n";
            s +=
                "============================================================" +
                "\r\n";
            s += lang.getString("Date") + ": " + ut.getSystemDateTime() + " ====> "+lang.getString("Cashier") + ":"+
                DAL.getEmpPosID1() + " =====>  " + lang.getString("InputKeys") + ":" +
                dlgCheckAccount.txtpassword.getText() +
                "=====>" + ut.ToUpperString(lang.getString("EvenExchange")) + " " + ut.ToUpperString(lang.getString("Unsuccessful"))  + "\r\n";
            s +=
                "============================================================" +
                "\r\n";
            ut.writeToTextFile(DAL.getErrorFile(), s);
            frmMain.txtMainInput.requestFocus(true);
          }

        }

        void btnModdifyQuan_actionPerformed(ActionEvent e) {
          if(isExistItem()){
            if (tblSaleItem.getSelectedRow()!= -1){
              double qty = Double.parseDouble(tblSaleItem.getValueAt(tblSaleItem.
                  getSelectedRow(), COL_QUANTITY).toString());
              if (qty > 0) {
                frmMain.setInputLabel(lang.getString("MS076_EnterQty"));
                flagButton = NS_QUANTITY_BTN;
                frmMain.setEditTextInput(true);
              }
            }
          }

        }

        void btnReturnItem_actionPerformed(ActionEvent e) {
//          frmMain.setTitle(lang.getString("TT0461"));
//          frmMain.showScreen(Constant.SCR_RETURN_NOTFOUND_ITEM);

            DialogCheckAccount dlgCheckAccount = new DialogCheckAccount(frmMain,lang.getString("TT044"),true,frmMain);
            dlgCheckAccount.setLocation(210,170);
            dlgCheckAccount.setVisible(true);
            if (dlgCheckAccount.done) {
                DialogCheckTransaction dlgCheckTransaction =new DialogCheckTransaction(frmMain,lang.getString("TT059"),true,frmMain);
                dlgCheckTransaction.setLocation(210,170);
                dlgCheckTransaction.setVisible(true);
//                System.out.println("============== "+ dlgCheckTransaction.done);

                String s="";
                s = "\r\n";
                s += "============================================================"+"\r\n";
                s +=lang.getString("Date") + ": "+ut.getSystemDateTime() + " ====>"+ lang.getString("Cashier") + ": " + DAL.getEmpPosID1() + " =====>  "+ lang.getString("Observer") + ":" + dlgCheckAccount.getObserverID() + "=====> "+ ut.ToUpperString(lang.getString("ReturnGoods")) +"\r\n";
                s += "============================================================" + "\r\n";
                ut.writeToTextFile(DAL.getErrorFile(), s);

                //check data input
                if (!ut.isDoubleString(dlgCheckTransaction.getTransId())|| dlgCheckTransaction.getTransId().length()<17) {
                  ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS041_TranIDIsNotNum"));
                  frmMain.txtMainInput.setText("");
                  frmMain.txtMainInput.requestFocus(true);
                  return;
                }

                //end check cashier
                if (dlgCheckTransaction.done) {
                  frmMain.setTitle(lang.getString("TT046"));
                  frmMain.showScreen(Constant.SCR_RETURN_ITEM);
                  frmMain.pnlReturnItem.setTransId(dlgCheckTransaction.getTransId());
                }else{
                  frmMain.setTitle(lang.getString("TT046"));
                  frmMain.showScreen(Constant.SCR_RETURN_NOTFOUND_ITEM);
                  frmMain.pnlReturnNotFoundItem.setTransId(dlgCheckTransaction.getTransId());
                }
            }
            else{
              String s = "";
              s = "\r\n";
              s +=
                  "============================================================" +
                  "\r\n";
              s += lang.getString("Date") + ": " + ut.getSystemDateTime() + " ====> " + lang.getString("Cashier") + ":" +
                  DAL.getEmpPosID1() + " =====>  " + lang.getString("InputKeys") + ":" +
                  dlgCheckAccount.txtpassword.getText() +
                  "=====> "+ut.ToUpperString(lang.getString("ReturnGoodsUncessful")) + ut.ToUpperString(lang.getString("ReturnGoods")) +" "+ ut.ToUpperString(lang.getString("Unsuccessful")) + "\r\n";
              s +=
                  "============================================================" +
                  "\r\n";
              ut.writeToTextFile(DAL.getErrorFile(), s);
              frmMain.txtMainInput.requestFocus(true);
            }

      }

        void btnDiscount_actionPerformed(ActionEvent e) {
          //check manager
              DialogCheckAccount dlgCheckAccount = new DialogCheckAccount(frmMain,lang.getString("TT044"),true,frmMain);//lang.getString("TT044")
              dlgCheckAccount.setLocation(210,170);
              dlgCheckAccount.setVisible(true);
              if (dlgCheckAccount.done) {
                   //check cashier
                  String s="";
                  s = "\r\n";
                  s += "============================================================"+"\r\n";
                  s +=lang.getString("Date")+": "+ut.getSystemDateTime() + " ====>"+lang.getString("Cashier")+": "+DAL.getEmpPosID1() + " =====> "+lang.getString("Observer")+": " + dlgCheckAccount.getObserverID() + "=====> " + lang.getString("Discount")+ "\r\n";
                  s += "============================================================" + "\r\n";
                  ut.writeToTextFile(DAL.getErrorFile(),s);
                  //end check cashier
                  if(isExistItem()){
                    if (tblSaleItem.getSelectedRow()!= -1){
                      double qty = Double.parseDouble(tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(),COL_QUANTITY).toString());
                      if (qty > 0){
                        frmMain.setInputLabel(lang.getString("MS077_EnterDis"));
                        frmMain.txtMainInput.requestFocus(true);
                        flagButton=NS_DISCOUNT_PERCENT_BTN;
                        //show discount button
                        frmMain.removeButton();
                        showDiscountButton();
                        flagDiscount = true;
                      }
                    }
                  }
                }
        }

        void btnDeleteItem_actionPerformed(ActionEvent e) {
//check manager
          DialogCheckAccount dlgCheckAccount = new DialogCheckAccount(frmMain,lang.getString("TT044"),true,frmMain);
          dlgCheckAccount.setLocation(210,170);
          dlgCheckAccount.setVisible(true);
          if (dlgCheckAccount.done) {
            if(isExistItem()){
              //check cashier
              String s="";
              s = "\r\n";
              s += "============================================================"+"\r\n";
              s +=lang.getString("Date") + ": "+ut.getSystemDateTime() + " ====>"+lang.getString("Cashier")+": "+DAL.getEmpPosID1() + " =====> "+lang.getString("Observer")+": " + dlgCheckAccount.getObserverID() + "=====> "+ ut.ToUpperString(lang.getString("Delete")) +"\r\n";
              s += "============================================================" + "\r\n";

              s+=tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(), COL_ITEM_ID).toString()  +";"+
                  tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(), COL_ART_NO).toString()+";"+
                  tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(), COL_ITEM_DESC).toString()  +";"+
                  tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(), COL_QUANTITY).toString() +";"+
                  tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(), COL_PRICE).toString() +";"+
                  tblSaleItem.getValueAt(tblSaleItem.getSelectedRow(), COL_AMOUNT).toString()  ;
              ut.writeToTextFile(DAL.getErrorFile(),s);
              //end check cashier
              deleteItemRow();
            }
          }
          frmMain.setInputLabel(lang.getString("MS017_EnterItem"));
          flagButton=NS_NEW_SALE_BTN;
          frmMain.txtMainInput.requestFocus(true);
        }

  void btnAddItem_actionPerformed(ActionEvent e) {
    frmMain.pnlAddItem.setParentScreen(Constant.SCR_NEW_SALE);
    frmMain.setTitle(lang.getString("TT047"));
    frmMain.showScreen(Constant.SCR_ADD_ITEM);
  }
//display transaction (not use for mark down now)
  void btnMarkdown_actionPerformed(ActionEvent e) {
      frmMain.setInputLabel(lang.getString("MS021_EnterTranDate"));
      frmMain.setTitle(lang.getString("TT011"));
      frmMain.showScreen(Constant.SCR_VIEW_TRANSACTION);
      frmMain.setInputText(ut.getSystemDateStandard());
  }

  void btnTotal_actionPerformed(ActionEvent e) {

    String upcVissan="2000000501062";
    for (int i = 0; i < tblSaleItem.getRowCount(); i++) {
      if (upcVissan.equals(tblSaleItem.getValueAt(i, COL_ART_NO).toString())) {
        if(Double.parseDouble(tblSaleItem.getValueAt(i, COL_QUANTITY).toString())==1){
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS078_InputVissanQty"));
          return;

        }
      }
    }


    //check and apply promotion
    resetSaleItem();
    //Sum sale item
    sumSaleItem();

    applyMixMatchPromo();
    applyThresholdPromo();
//    getInitialSale();
    applyDisCustType();

//check if cust is franchise, must input cust id
//    if(DAL.getFranchiseStore().equals("true")&& String.valueOf( frmMain.pnlNewSale.custID ).equals("-1")){
//      ut.showMessage(frmMain, setText(lang.getString("TT001"),
//                           "Please enter customer.");
//      return;
//    }

    //If return Item=> Check money if enough
    double subTotal=0;
    if(tblSaleItem.getRowCount()>0){
      for (int i = 0; i < tblSaleItem.getRowCount(); i++) {
        subTotal+=ut.convertToDouble(tblSaleItem.getValueAt(i,COL_AMOUNT).toString());
      }
      if(subTotal<0 && ( calcDrawFun()< (subTotal*(-1)) ) ){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS079_paidInMoney") + calcDrawFun()+" VND");
        return;
      }
    }

    String transID="";
    String today="";
    double amount=0;
    String trans_Type_ID="1";
    double disc_Amt=0;

    Transaction trans;
    DataAccessLayer DAL ;
    String StforeID ="";
    if(isExistItem()){
      //get info of transaction NGhi
      today = ut.getSystemDateTime1();
      today = today.substring(0,2) + today.substring(3, 5) + today.substring(8,10)
              +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);
//yymmddhhmmss
//      today = today.substring(8,10) + today.substring(3, 5) + today.substring(0,2)
//              +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);

      trans= frmMain.getTransaction();
      transID = ut.doubleToString(trans.getStore_ID()) +ut.doubleToString(trans.getRegister_ID())+today;
      trans.setTrans_ID(Long.parseLong(transID));
      trans.setCust_ID(Long.toString(custID));
      trans.setSale_ID(Long.toString(saleID));

      frmMain.pnlPayment.setSaleItem(getSaleItem());

      frmMain.pnlPayment.setTransaction(trans);

      //cal Amount Due
      double amountDue = getAmountTender(frmMain.pnlPayment.lblAmountTendered.getText());
      frmMain.pnlPayment.lblAmountDue.setText(lang.getString("AmountDue1") + ": " + ut.formatNumber("" + amountDue));
//      System.out.print("Amount Due: " + amountDue);
      frmMain.setTitle(lang.getString("TT048"));
      frmMain.showScreen(Constant.SCR_PAYMENT);
      if (amountDue <= 0){
        amountDue = amountDue*(-1);
//        frmMain.setInputText("0.00");
        frmMain.txtMainInput.setEditable(false);
        frmMain.pnlPayment.mainInput = "0";
        frmMain.removeButton();
        frmMain.pnlPayment.showButton();
        frmMain.pnlPayment.btnPostTXN.setEnabled(true);
        frmMain.pnlPayment.btnPostTXN.requestFocus(true);
        frmMain.pnlPayment.flagCash=false;
      }
    }
  }

  //  apply total discount for customer range
      void applyDisCustType(){
        ResultSet rs = null;
        String discType ="";
        double discValue = 0;
        double oldMarkdown = 0;
        double newMarkdown = 0;
        double totalMarkdown = 0;
        double newAmountDue =0;
        String SQL = "SELECT DISCOUNT_TYPE,DISCOUNT_VALUE FROM BTM_POS_CUSTOMER_TYPE WHERE CUST_TYPE_ID='"+custType+"'";
        try{
          rs = DAL.executeQueries(SQL);
          if(rs.next()){
            discType = rs.getString("DISCOUNT_TYPE");
            discValue = Double.parseDouble(rs.getString("DISCOUNT_VALUE"));
          }
        }catch(Exception ex){
          ExceptionHandle eh=new ExceptionHandle();
          eh.ouputError(ex);
        }
//discount Total Percentage on bill
        if (discType.equalsIgnoreCase("Total_Percentage")){
          for(int i=0;i<tblSaleItem.getRowCount();i++){
            if(!vNonPromoItem.contains(tblSaleItem.getValueAt(i, COL_ITEM_ID).toString())){
              oldMarkdown = ut.convertToDouble(tblSaleItem.getValueAt(i,COL_MARKDOWN).toString());
              newMarkdown = ut.convertToDouble(tblSaleItem.getValueAt(i,COL_AMOUNT).toString())-(ut.convertToDouble(tblSaleItem.getValueAt(i,COL_AMOUNT).toString())*(100-discValue)/100);
              totalMarkdown = oldMarkdown + newMarkdown;
              newAmountDue = (Double.parseDouble(tblSaleItem.getValueAt(i,COL_QUANTITY).toString())*ut.convertToDouble(tblSaleItem.getValueAt(i,COL_PRICE).toString()))-totalMarkdown;
              updateCalculate(i,Double.parseDouble(tblSaleItem.getValueAt(i,COL_QUANTITY).toString()),totalMarkdown,newAmountDue);
            }
          }
          discCust = true;
        }
        else{

        }
      }

  void btnSaveTXN_actionPerformed(ActionEvent e) {
    String itemId="";
    String artNo = "";
    String desc ="";
    double price =0;
    double quantity = 0;
    double discQty = 0;
    double markdown = 0;
    double markdownPer =0;
    double amountDue=0;
    String attr1="";
    String attr2="";
    Item itemT=  new Item();
    if(vSaleItemTemp!=null){
      vSaleItemTemp.clear();
    }
    if(isExistItem()){
      flagSaveGet = true;
      showNewSaleButton();
      custIDTemp = custID;
      saleIDTemp = saleID;

//      custTypeTemp = custType;
//      custTypeTemp = -1;
//      vSaleItemTemp = getSaleItem();
      for (int i = 0; i < tblSaleItem.getRowCount(); i++) {
        itemId = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
        artNo = tblSaleItem.getValueAt(i,COL_ART_NO).toString();
        desc = tblSaleItem.getValueAt(i, COL_ITEM_DESC).toString();
        price = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_PRICE).
                                   toString());
        quantity = Double.parseDouble(tblSaleItem.getValueAt(i, COL_QUANTITY).
                                    toString());
        if(tblSaleItem.getValueAt(i,COL_DISTCOUNT_QTY).toString().equals("null")){
          discQty = Double.parseDouble(tblSaleItem.getValueAt(i,COL_DISTCOUNT_QTY).toString());
        }
        markdown = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_MARKDOWN).
                                      toString());
        if(tblSaleItem.getValueAt(i,COL_MARKDOWN_PER).toString().equals("null")){
          markdownPer = ut.convertToDouble(tblSaleItem.getValueAt(i,COL_MARKDOWN_PER).toString());
        }
        amountDue = ut.convertToDouble(tblSaleItem.getValueAt(i, COL_AMOUNT).
                                       toString());
        attr1 = "";//vAttr1.elementAt(i).toString();
        attr2 = "";//vAttr2.elementAt(i).toString();
        itemT = new Item(itemId,artNo, desc, price, quantity,discQty, markdown,markdownPer, "", 0,
                        amountDue,custIDTemp,attr1,attr2,saleIDTemp);
        vSaleItemTemp.add(itemT);
      }
      while (model.getRowCount() > 0) {
        model.removeRow(0);
      }
//      custNameSaved = frmMain.lblName.getText();
//      phoneSaved = frmMain.lblPhone.getText();
      savedCust.setFirstName(frmMain.lblName.getText());
      savedCust.setWorkPhone(frmMain.lblPhone.getText());
      savedCust.setCustType(String.valueOf( custType));
      frmMain.lblName.setText(lang.getString("Name")+":");
      frmMain.lblPhone.setText(lang.getString("Phone")+":");

      //reset
      custID = -1;
      saleID = -1;
      custType =-1; //reset customer type

    }
    setStatusLabel();
  }

  void btnGetTXN_actionPerformed(ActionEvent e){
    flagSaveGet = false;
    while(model.getRowCount()>0){
      model.removeRow(0);
    }
    if(vSaleItemTemp!=null){
      int option = ut.showMessage(frmMain,lang.getString("TT049"),lang.getString("MS080_ConfirmGetOldTrans"),DialogMessage.QUESTION_MESSAGE,DialogMessage.YES_NO_OPTION);
      if(option == DialogMessage.YES_VALUE){
        Item item;
        for(int i = 0;i<vSaleItemTemp.size();i++){
          item = (Item) vSaleItemTemp.elementAt(i);
          addItemRow(new Object[] {item.getId(),item.getArtNo(),
                     item.getDescription(), String.valueOf(item.getDiscountQuantity()),
                     String.valueOf(item.getQuantity()), //noted: type of quantity is double, not mean quantity for return purpose
                     ut.formatNumber("" + item.getunitPrice()),
                     ut.formatNumber("0"),
                     ut.formatNumber("0"),
                     ut.formatNumber("" + item.getunitPrice()*item.getQuantity())}); //amount due = price*qty
        }
        frmMain.lblName.setText(savedCust.getFirstName());
        frmMain.lblPhone.setText(savedCust.getWorkPhone());
        custType= Long.parseLong(savedCust.getCustType());

      }
    }
        showNewSaleButton();
  }

//Sum sale by item
  void sumSaleItem(){
    String item="";
    String tmpItem="";
    double quantity=0;
    double oldQuantity=0;
    double additionQty=0;
    double price=0;
    double unitMarkdown=0;//for 1 unit of item
    double oldMarkdown=0;//total markdown of item
    double additionMarkdown=0;//total markdown of item

    double markdown=0;//total markdown of item
    double amountDue=0;
    int j=0;

    for (int i = 0; i < model.getRowCount(); i++) {
      item = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
      //process if sale item in BuyItem
      j=i+1;
      while ( j < model.getRowCount()) {

        tmpItem = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
        if(tmpItem.equals(item)){
          oldQuantity=ut.convertToDouble((tblSaleItem.getValueAt(i,COL_QUANTITY).toString()));
          additionQty=ut.convertToDouble((tblSaleItem.getValueAt(j,COL_QUANTITY).toString()));
          quantity = oldQuantity+additionQty;
          price = ut.convertToDouble( tblSaleItem.getValueAt(i, COL_PRICE).
                                   toString());
          oldMarkdown=ut.convertToDouble((tblSaleItem.getValueAt(i,COL_MARKDOWN).toString()));
          additionMarkdown=ut.convertToDouble((tblSaleItem.getValueAt(j,COL_MARKDOWN).toString()));

          markdown=oldMarkdown + additionMarkdown;
          amountDue=quantity*price-markdown;
          //round off
          markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
          amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);
          //update table at first item
          updateCalculate(i,quantity,markdown,amountDue);

          model.removeRow(j);
          j--;

        }
        j++;
      }

    }
  }
  //  caculate current draw fun: the same code in PanelPaidOut, Void TXn
  long calcDrawFun(){
   String sql="";
   long currDrawFun=0;
   ResultSet rs=null;

   try{
     // beginning draw fun
      sql = "select draw_fund from btm_pos_store_fund where total_fund = 0 order by workday desc";
      rs = DAL.executeQueries(sql);
      if(rs.next()){
        currDrawFun = rs.getLong("draw_fund");
      }

      //get paid in-out
      sql = "select sum(paid_in) - sum(paid_out) total "+
        " from btm_pos_paid_in_out   "+
        " where trunc(trans_date) = to_date('"+ut.getSystemDate()+"', 'yyyy-MM-dd')  " +
        " order by trunc(trans_date)";

        rs = DAL.executeQueries(sql);
        if(rs.next()){
          currDrawFun = currDrawFun + rs.getLong("total");
        }

   }
   catch (Exception ex) {
     ex.printStackTrace();
   }

   return currDrawFun;

 }

  double getAmountTender(String s){
    double aDue = ut.convertToDouble(frmMain.pnlPayment.lblTotal.getText());
    String [] str = s.split(":");
    aDue -= ut.convertToDouble(str[1]);
    return aDue;
  }
  void btnBack_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT003"));
    frmMain.showScreen(Constant.SCR_MAIN);
  }

  void btnDiscountPrice_actionPerformed(ActionEvent e) {
    frmMain.txtMainInput.requestFocus(true);
    frmMain.setInputLabel(lang.getString("MS081_EnterNewPriceItem") );
    flagButton=NS_DISCOUNT_PRICE_BTN;
  }

  void btnDiscountUSD_actionPerformed(ActionEvent e) {
    frmMain.txtMainInput.requestFocus(true);
    frmMain.setInputLabel(lang.getString("MS082_EnterDiscountAmountNewItem"));
    flagButton=NS_DISCOUNT_USD_BTN;
  }

  void btnDiscountPercent_actionPerformed(ActionEvent e) {
    frmMain.txtMainInput.requestFocus(true);
    frmMain.setInputLabel(lang.getString("MS083_EnterDisPerItem"));
    flagButton=NS_DISCOUNT_PERCENT_BTN;
  }
  void btnDiscountAll_actionPerformed(ActionEvent e) {
    frmMain.txtMainInput.requestFocus(true);
    frmMain.setInputLabel(lang.getString(("MS084_EnterDisPerAll")));
    flagButton=NS_DISCOUNT_PERCENT_ALL_BTN;
  }

  void btnDiscountBack_actionPerformed(ActionEvent e) {
    frmMain.txtMainInput.requestFocus(true);
    goBackNewSale()  ;
    flagDiscount = false;
  }

  void btnMarkdownPrice_actionPerformed(ActionEvent e) {
    frmMain.setInputLabel(lang.getString("MS081_EnterNewPriceItem"));
    flagButton=NS_DISCOUNT_USD_BTN;
  }

  void btnMarkdownUSD_actionPerformed(ActionEvent e) {
    frmMain.setInputLabel(lang.getString("MS085_EnterMarkdown"));
    flagButton=NS_MARKDOWN_USD_BTN;
  }

  void btnMarkdownPercent_actionPerformed(ActionEvent e) {
    frmMain.setInputLabel(lang.getString("MS086_EntermarkDown"));
    flagButton=NS_MARKDOWN_PERCENT_BTN;
  }

  void btnMarkdownBack_actionPerformed(ActionEvent e) {
    goBackNewSale();
  }


  void btnCustomer_actionPerformed(ActionEvent e) {
    Customer  cust=new Customer();
    DialogCustomerSearch search = new DialogCustomerSearch(frmMain,lang.getString("TT050"),true,frmMain);
    search.setVisible(true);
    if (search.done){
      cust= search.getCustomer();
      custID = Long.parseLong(cust.getCustID());
      custType=Long.parseLong(cust.getCustType());
//      custName = cust.getFirstName()+ " "+cust.getLastName();
      custName = cust.getLastName(); //only show last name
      frmMain.lblName.setText(lang.getString("Name")+": "+custName);
      frmMain.lblPhone.setText(lang.getString("Phone")+": "+cust.getWorkPhone());
    }
  }

  void btnViewTransferTransaction_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT028"));
    frmMain.showScreen(Constant.SCR_VIEW_TRANSFER_TRANS);
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
     // ESCAPE
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
       if(flagDiscount==false){
         if (identifier.intValue() == KeyEvent.VK_F1) {
//           btnModdifyQuan.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F6) {
           btnDiscount.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F3) {
           flagButton=NS_SEARCH_ITEM_BTN;
           frmMain.setInputLabel(lang.getString("MS067_EnterKeyWord"));
           frmMain.setInputText("");
           return;

//           searchExpressItem();
//            resetSaleItem();
//           applyMixMatchPromo();
//           applyThresholdPromo();
         }
         else if (identifier.intValue() == KeyEvent.VK_F4) {
           btnCustomer.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F2) {
           ///Disable Save TXN to memory
//           if(!flagSaveGet){
//             btnSaveTXN.doClick();
//           }else{
//             btnGetTXN.doClick();
//           }
        }
         else if (identifier.intValue() == KeyEvent.VK_F8) {
           btnDeleteItem.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F5) {
           btnMarkdown.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F7) {
           btnReturnItem.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F9) {
           btnEvenExchange.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F10) {
           btnTotal.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F11) {
          btnCancelTXN.doClick();
        }
         else if (identifier.intValue() == KeyEvent.VK_F12 ) {
           btnBack.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_ESCAPE) {
           goBackNewSale();

         }

       }
       else{
         if (identifier.intValue() == KeyEvent.VK_F1) {
           btnDiscountAll.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F2) {
          btnDiscountPrice.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F3) {
          btnDiscountUSD.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F4) {
          btnDiscountPercent.doClick();
        }else if(identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnDiscountBack.doClick();
        }

       }
     }
  }


  void btnCancelTXN_actionPerformed(ActionEvent e) {
    int i = ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS087_ConfirmCancelTrans"),DialogMessage.WARNING_MESSAGE,DialogMessage.YES_NO_OPTION);
    if (i == DialogMessage.YES_VALUE){
      //check manager
          DialogCheckAccount dlgCheckAccount = new DialogCheckAccount(frmMain,lang.getString("TT044"),true,frmMain);
          dlgCheckAccount.setLocation(210,170);
          dlgCheckAccount.setVisible(true);
          if (dlgCheckAccount.done) {
            if(isExistItem()){
              //check cashier
              String s="";
              s = "\r\n";
              s += "============================================================"+"\r\n";
              s +="Date: "+ut.getSystemDateTime() + " ====> "+ lang.getString("Cashier") + ": " + DAL.getEmpPosID1() + " =====> " + lang.getString("Observer") + ": " + dlgCheckAccount.getObserverID() + "=====> " + ut.ToUpperString(lang.getString("CancelTXN"))  + ": "+ "\r\n";
              s += "============================================================" + "\r\n";
              for(int r=0; r<tblSaleItem.getRowCount(); r++){
                s +=
                    tblSaleItem.getValueAt(r, COL_ITEM_ID).toString() + ";" +
                    tblSaleItem.getValueAt(r,COL_ART_NO).toString() + ";" +
                    tblSaleItem.getValueAt(r,COL_ITEM_DESC).toString() + ";" +
                    tblSaleItem.getValueAt(r,COL_QUANTITY).toString() + ";" +
                    tblSaleItem.getValueAt(r,COL_PRICE).toString() + ";" +
                    tblSaleItem.getValueAt(r,COL_AMOUNT).toString();
                s += "\r\n";
              }
              ut.writeToTextFile(DAL.getErrorFile(),s);
              //end check cashier
              frmMain.lblName.setText(lang.getString("Name")+": ");
              frmMain.lblPhone.setText(lang.getString("Phone")+": ");
              custID = -1;
              saleID = -1;
              custType=-1;
              frmMain.pnlNewSale.lblTotal.setText(lang.getString("TotalItem") + ": 0");
              frmMain.pnlNewSale.lblSubTotal.setText(lang.getString("SubTotal")+": 0.00");
              deleteNewSale();
            }
          }
          else{
            String s="";
            s = "\r\n";
            s += "============================================================"+"\r\n";
              s +=lang.getString("Date") + ": "+ut.getSystemDateTime() + " ====> "+ lang.getString("Cashier")  + ":" +DAL.getEmpPosID1() + " =====>  " + lang.getString("InputKeys") + ": " + dlgCheckAccount.txtpassword.getText() + "=====> " + ut.ToUpperString(lang.getString("Delete")) + " " + ut.ToUpperString(lang.getString("Delete")) +" " + ut.ToUpperString(lang.getString("Unsuccessful"))+ "\r\n";
            s += "============================================================" + "\r\n";
            ut.writeToTextFile(DAL.getErrorFile(),s);
          }
        }
    frmMain.txtMainInput.requestFocus(true);

  }

  void tblSaleItem_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F6) {
             btnDiscount.doClick();
    }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      goBackNewSale();

    }
  }

  //================== ThresHold & MultiUnit Promo ===========================//
  //==========================================================================//


  /** applyThresholdPromo apply Threshold to sale items
   *  Put Threshold info into Threshold object
   * @author	Trung.Nguyen
   * return no
  **/

  void applyThresholdPromo(){

    ThresholdBusObj threshold=new ThresholdBusObj();
//    String listSaleItemID="";
    ResultSet rs=null;
    ResultSet rsBuy=null;
    ResultSet rsGet=null;
    String thresholdNo="";
    Vector vBuyItem = new Vector();
    Vector vGetItem = new Vector();
    Vector vBuyAmount = new Vector();
    Vector vGetAmount = new Vector();

    //Exit if no sale item
    if(model.getRowCount()==0){
      return;
    }

    //Get all Threshold promo which affect today

    String sql = "select distinct h.* from BTM_POS_PROMO_DETAIL h  where status=1 and h.THRESHOLD_NO <> 'N' and "
        + " trunc(START_TIME)<= to_date('" + ut.getSystemDate() +"','" + ut.DATE_FORMAT + "') and "
        + " to_date('" + ut.getSystemDate() +"','" + ut.DATE_FORMAT + "') <= trunc(END_TIME)  " ;
//    System.out.println(sql);
    rs = DAL.executeQueries(sql);

    try {
      //Process each Threshold Promo
      while(rs.next()){

        thresholdNo = rs.getString("THRESHOLD_NO");

        threshold.setTransType(rs.getString("PROMO_TRAN_TYPE"));
        threshold.setThresholdQty(rs.getString("THRESHOLD_QTY"));
        threshold.setThresholdAmt(rs.getString("THRESHOLD_AMT"));
        threshold.setDiscountType(rs.getString("DISCOUNT_TYPE"));
        threshold.setDiscountAmt(rs.getString("DISCOUNT_AMT"));
        threshold.setStatus(rs.getString("STATUS"));

        //put Threshold Buy Item into threshold obj
        sql =
            "select * from BTM_POS_PROMO_THRESHOLD_SKU where BUY_GET_FLAG=0 and THRESHOLD_NO = " +
            thresholdNo;
//        System.out.println(sql);
        rsBuy = DAL.executeQueries(sql);

        while (rsBuy.next()) {
          vBuyItem.add(rsBuy.getString("Item_ID"));
          vBuyAmount.add(rsBuy.getString("Qty"));
        }
        threshold.setBuyUPC(vBuyItem);
        threshold.setBuyAmount(vBuyAmount);

        //Get Threshold Get Item into threshold obj
        sql =
            "select * from BTM_POS_PROMO_THRESHOLD_SKU where BUY_GET_FLAG=1 and THRESHOLD_NO = " +
            thresholdNo;
//        System.out.println(sql);
        rsGet = DAL.executeQueries(sql);

        while (rsGet.next()) {
          vGetItem.add(rsGet.getString("Item_ID"));
          vGetAmount.add(rsGet.getString("Qty"));
        }
        threshold.setGetUPC(vGetItem);
        threshold.setGetAmount(vGetAmount);

        //process
        processThreshold(threshold);

        //reset vector for next MixMatch
        vBuyItem.clear();
        vBuyAmount.clear();
        vGetItem.clear();
        vGetAmount.clear();

        rsBuy.getStatement().close();
        rsGet.getStatement().close();
      }

    rs.getStatement().close();
    }catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }

  }

  /** processThreshold: process Threshold promo base on
   * @author	Trung.Nguyen
   * @param mixMatch Threshold obj
   * @param

   * return no
  **/

  void processThreshold(ThresholdBusObj threshold){
    String getItem = "";
    String item = "";
    int row=0;
    double quantity=0;
    double price=0;
    double markdown=0;//total markdown of item
    double amountDue=0;

    //THRESHOLD_PROMO type
    if (threshold.getTransType().equals(THRESHOLD_TYPE_AMOUNT)) {
      // Buy total peyment >= 100 000 => discount 10%
      if(threshold.getDiscountType().equals(Constant.GET_TYPE_PERCENT_OFF)){
        updateThresholdItem(threshold,Constant.GET_TYPE_PERCENT_OFF);

      // Not use  GET_TYPE_FIXED_UNIT_PRICE
      }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
              //-- no code here
              ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("NoNeedCode")+ " " +Constant.GET_TYPE_FIXED_UNIT_PRICE);
              return;

      // Not use  GET_TYPE_UNIT_PRICE_OFF
      }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
             //-- no code here
             ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("NoNeedCode")+ " " +Constant.GET_TYPE_UNIT_PRICE_OFF);
             return;

      //Not use  GET_TYPE_FIXED_TOTAL_PRICE
      }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_FIXED_TOTAL_PRICE)){
             //-- no code here
             ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("NoNeedCode")+ " " +Constant.GET_TYPE_FIXED_TOTAL_PRICE);
             return;

      // Buy total payment >= 100000 => discount 2000
      }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_TOTAL_PRICE_OFF)){
        //-- no code here
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("NoNeedCode")+ " " +Constant.GET_TYPE_TOTAL_PRICE_OFF);
        return;

      }
      promoAllItem=true;
    }
    else if (threshold.getTransType().equals(THRESHOLD_TYPE_UNIT)) {
      // Buy >=n X  => promo on 1 Y
      if(threshold.getBuyUPC().size()==1){
        //Loop sale item
        for (int i = 0; i < model.getRowCount(); i++) {
          item = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
          //process if sale item in BuyItem
          if (threshold.getBuyUPC().contains(item)) {
            // Buy  >= n items X => Get Y with markdown = m%
            if(threshold.getDiscountType().equals(Constant.GET_TYPE_PERCENT_OFF)){
              updateMultiUnitItem(threshold, i,Constant.GET_TYPE_PERCENT_OFF);
            // Buy  >= n items X => Get Y with price = m
            }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
              updateMultiUnitItem(threshold, i,Constant.GET_TYPE_FIXED_UNIT_PRICE);
             // Buy  >= n items X => Get Y with markdown = m
            }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
              updateMultiUnitItem(threshold, i,Constant.GET_TYPE_UNIT_PRICE_OFF);
              //Not use  GET_TYPE_FIXED_TOTAL_PRICE
            }
            else if (threshold.getDiscountType().equals(Constant.
                GET_TYPE_FIXED_TOTAL_PRICE)) {
              //-- no code here
              ut.showMessage(frmMain, lang.getString("TT001"),
                             lang.getString("NoNeedCode")+ " " +
                             Constant.GET_TYPE_FIXED_TOTAL_PRICE);
              return;
              // Buy total peyment >= 100000 => discount 2000
            }
            else if (threshold.getDiscountType().equals(Constant.
                GET_TYPE_TOTAL_PRICE_OFF)) {
              //-- no code here
              ut.showMessage(frmMain, lang.getString("TT001"),
                             lang.getString("NoNeedCode")+ " " + Constant.GET_TYPE_TOTAL_PRICE_OFF);
              return;
            }

          }
        }
      // Buy >=n in list (X,Z)   => promo on 1 Y
      }else  if(threshold.getBuyUPC().size()>1){
        // Buy  >= n items in list (X,Z) => Get Y with markdown = m%
        if(threshold.getDiscountType().equals(Constant.GET_TYPE_PERCENT_OFF)){
          updateMultiUnitListItem(threshold,Constant.GET_TYPE_PERCENT_OFF);

        // Buy  >= n items X => Get Y with price = m
        }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
          updateMultiUnitListItem(threshold,Constant.GET_TYPE_FIXED_UNIT_PRICE);
        // Buy  >= n items X => Get Y with markdown = m
        }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
           updateMultiUnitListItem(threshold,Constant.GET_TYPE_UNIT_PRICE_OFF);
        //Not use  GET_TYPE_FIXED_TOTAL_PRICE
        }
        else if (threshold.getDiscountType().equals(Constant.
             GET_TYPE_FIXED_TOTAL_PRICE)) {
           //-- no code here
           ut.showMessage(frmMain, lang.getString("TT001"),
                          lang.getString("NoNeedCode")+ " " + Constant.GET_TYPE_FIXED_TOTAL_PRICE);
           return;
           // Buy total peyment >= 100000 => discount 2000
         }
         else if (threshold.getDiscountType().equals(Constant.
             GET_TYPE_TOTAL_PRICE_OFF)) {
           //-- no code here
           ut.showMessage(frmMain, lang.getString("TT001"),
                          lang.getString("NoNeedCode")+ " " + Constant.GET_TYPE_TOTAL_PRICE_OFF);
           return;

         }
      }
    }
    //Buy >= amount of item in list => promo on n Y
    else if (threshold.getTransType().equals(THRESHOLD_AMOUNT_LIST)) {
      // Buy  >= Amount of item in list (X,Z) => Get n Y with markdown = m%
      if(threshold.getDiscountType().equals(Constant.GET_TYPE_PERCENT_OFF)){
        updateAmountListItem(threshold,Constant.GET_TYPE_PERCENT_OFF);
      // Buy  >= Amount of item in list (X,Z) => Get n Y with price = m
      }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
        updateAmountListItem(threshold,Constant.GET_TYPE_FIXED_UNIT_PRICE);
      // Buy  >= Amount of item in list (X,Z) => Get n Y with markdown = m
      }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
        updateAmountListItem(threshold,Constant.GET_TYPE_UNIT_PRICE_OFF);
      //Not use  GET_TYPE_FIXED_TOTAL_PRICE
      }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_FIXED_TOTAL_PRICE)){
              //-- no code here
              ut.showMessage(frmMain, lang.getString("TT001"),
                             lang.getString("NoNeedCode")+ " " + Constant.GET_TYPE_FIXED_TOTAL_PRICE);
              return;
        // Buy total peyment >= 100000 => discount 2000
      }else if(threshold.getDiscountType().equals(Constant.GET_TYPE_TOTAL_PRICE_OFF)){
              //-- no code here
              ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("NoNeedCode")+ " " +Constant.GET_TYPE_TOTAL_PRICE_OFF);
              return;
      }


    }else if (threshold.getTransType().equals(THRESHOLD_TYPE_BOTH)) {
      //Loop sale item
      for (int i = 0; i < model.getRowCount(); i++) {
        item = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
        //process if sale item in BuyItem
        if (threshold.getBuyUPC().contains(item)) {
          updateUnitAmountItem(threshold, i);
        }
      }

    } //end if type

  }

  /**updateUnitAmountItem : calculate GetItem markdown
   *                 base on GetType: Percent Off, Unit Price, Unit Price Off, Get Free, List Percent
   * @author	Trung.Nguyen
   * @param currentItem  buy item which is processing
   * @param currentRow  row of buy item which is processing
   * return no
  **/

   void updateUnitAmountItem(ThresholdBusObj threshold, int currentRow){

   }

  /**updateMultiUnitItem :  Buy >=n X  => promo on Y
   *                 base on GetType: Percent Off, Unit Price, Unit Price Off, Get Free, List Percent
   * @author	Trung.Nguyen
   * @param currentItem  buy item which is processing
   * @param currentRow  row of buy item which is processing
   * return no
  **/

   void updateMultiUnitItem(ThresholdBusObj threshold, int currentRow,String type){
     boolean found =false ; //true if found GetItem in sale item
     boolean isUpdate = true;//true if update is neccesary
     double buyAmt=0;
     double getAmt =0;
     String getItem = "";
     double disAmt =0;//discount %
     double quantity=0;//qty of Get item which sale
     double discountQty=0;//discount qty of sale item
     double availableQty=0;// available qty for discount (sale qty- discount qty)
     double price = 0; //price of Get item
     double unitMarkdown=0;//for 1 unit of Get item
     double markdown=0;//total markdown of Get item
     double oldMarkdown=0;//old total markdown of Get item
     double amountDue=0;//amount of row which apply promo
     int updateRow=0;// position of row which apply promo


           //test here
           double saleQty=0;//qty of Buy Item which sale
           //get update row of Get Item
           getItem = threshold.getGetUPC().elementAt(0).toString();
           for (int j = 0; j < model.getRowCount(); j++) {
             String item  = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
             if (getItem.equals(item)) {
               updateRow = j;
               found =true;
               break;//GetItem: only 1 item
             }
           }
           //exit if not found GetItem
           if(!found){
             return;
           }

           saleQty = Double.parseDouble( (tblSaleItem.getValueAt(currentRow, COL_QUANTITY).toString()));
           quantity = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_QUANTITY).toString()));
           //get current discount qty
           discountQty = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_DISTCOUNT_QTY).toString()));
           oldMarkdown = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_MARKDOWN).toString()));

           price = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_PRICE).toString()));
           //available qty = sale qty- discount qty
           availableQty = quantity - discountQty;

           //get n of X
           buyAmt=Double.parseDouble(threshold.getThresholdQty());
           //get k of Y (qty)
           getAmt=Double.parseDouble(threshold.getGetAmount().elementAt(0).toString());
           //get % discount m of Y
           disAmt=Double.parseDouble(threshold.getDiscountAmt().toString());
           if( saleQty >= buyAmt && availableQty > 0){
             // Buy  >= n items X => Get Y with markdown = m%
             if(type.equals(Constant.GET_TYPE_PERCENT_OFF)){
               unitMarkdown = disAmt * price / 100;
             // Buy  >= n items X => Get Y with price = m
             }else if(type.equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
               unitMarkdown =   price -disAmt;
              // Buy  >= n items X => Get Y with markdown = m
             }else if(type.equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
               unitMarkdown = disAmt ;
             }
             if (availableQty >= getAmt){ //apply for (getAmt) item Y
               markdown = getAmt * unitMarkdown;
               discountQty = discountQty + getAmt;
             }else{//apply for all item Y
               markdown = availableQty * unitMarkdown;
               discountQty = discountQty + availableQty;
             }
             markdown = markdown + oldMarkdown;
             amountDue = quantity * price - markdown;
           }else {
             isUpdate = false;
           }



     //round off
     markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
     amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);

     //update discount and AmountDue of sale
     if(isUpdate){
       updateCalculate(updateRow, quantity,discountQty, markdown, amountDue);

     }
     setStatusLabel();

   }

       /**updateAmountListItem : Buy >= amount of item in list => promo on n Y
        *
        * @author	Trung.Nguyen
        * @param currentItem  buy item which is processing
        * @param currentRow  row of buy item which is processing
        * return no
       **/

        void updateAmountListItem(ThresholdBusObj threshold,String type){
          boolean found =false ; //true if found GetItem in sale item
          boolean isUpdate = true;//true if update is neccesary
          double buyAmt=0;
          double getAmt =0;//discount %
          String getItem = "";
          double disAmt =0;//discount %
          double quantity=0;//qty of Get item which sale
          double discountQty=0;//discount qty of sale item
          double availableQty=0;// available qty for discount (sale qty- discount qty)
          double price = 0; //price of Get item
          double unitMarkdown=0;//for 1 unit of Get item
          double oldMarkdown=0;//old total markdown of Get item
          double markdown=0;//total markdown of Get item
          double amountDue=0;//amount of row which apply promo
          int updateRow=0;// position of row which apply promo

                int times = 0; //times to apply apromo for Y
                double saleAmt=0;//Amount of Item in List which sale
                //get update row of Get Item
                getItem = threshold.getGetUPC().elementAt(0).toString();
                for (int j = 0; j < model.getRowCount(); j++) {
                  String item  = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
                  if (getItem.equals(item)) {
                    updateRow = j;
                    found =true;
                    break;//GetItem: only 1 item
                  }
                }
                //exit if not found GetItem
                if(!found){
                  return;
                }
                //Amount item in list which sale
                for (int j = 0; j < model.getRowCount(); j++) {
                  String item  = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
                  if (threshold.getBuyUPC().contains(item)) {
                    quantity = Double.parseDouble( (tblSaleItem.getValueAt(j, COL_QUANTITY).toString()));
                    price = ut.convertToDouble( (tblSaleItem.getValueAt(j, COL_PRICE).toString()));
                    saleAmt = saleAmt + quantity*price;
                  }
                }
                quantity = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_QUANTITY).toString()));
                //get current discount qty
                discountQty = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_DISTCOUNT_QTY).toString()));
                oldMarkdown = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_MARKDOWN).toString()));

                price = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_PRICE).toString()));
                //available qty = sale qty- discount qty
                availableQty = quantity - discountQty;

                //get Amount of item in List
                buyAmt=Double.parseDouble(threshold.getThresholdAmt());
                //get k of Y (qty)
                getAmt=Double.parseDouble(threshold.getGetAmount().elementAt(0).toString());
                //get % discount m of Y
                disAmt=Double.parseDouble(threshold.getDiscountAmt().toString());
                times = ut.doubleStringToInt(String.valueOf(saleAmt / buyAmt));

                if( times>0 && availableQty > 0){
                  // Buy  >= Amount of item in list (X,Z) => Get n Y with markdown = m%
                  if(type.equals(Constant.GET_TYPE_PERCENT_OFF)){
                    unitMarkdown = disAmt * price / 100;
                  // Buy  >= Amount of item in list (X,Z) => Get n Y with price = m
                  }else if(type.equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
                    unitMarkdown = price -disAmt;
                  // Buy  >= Amount of item in list (X,Z) => Get n Y with markdown = m
                  }else if(type.equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
                    unitMarkdown = disAmt;
                  }
                  if (availableQty >= times*getAmt){ //apply for (times*getAmt) item Y
                    markdown = times*getAmt * unitMarkdown;
                    discountQty = discountQty + times*getAmt;
                  }else{//apply for all item Y
                    markdown = availableQty * unitMarkdown;
                    discountQty = discountQty + availableQty;
                  }
                  markdown = markdown + oldMarkdown;
                  amountDue = quantity * price - markdown;

                }else {
                  isUpdate = false;
                }

          //round off
          markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
          amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);

          //update discount and AmountDue of sale
          if(isUpdate){
             updateCalculate(updateRow, quantity,discountQty, markdown, amountDue);
          }
          setStatusLabel();

        }

   /**updateMultiUnitListItem :  Buy >=n in list (X,Z)   => promo on Y
    *                 base on GetType: Percent Off, Unit Price, Unit Price Off, Get Free, List Percent
    * @author	Trung.Nguyen
    * @param currentItem  buy item which is processing
    * @param currentRow  row of buy item which is processing
    * return no
   **/

    void updateMultiUnitListItem(ThresholdBusObj threshold,String type){
      boolean found =false ; //true if found GetItem in sale item
      boolean isUpdate = true;//true if update is neccesary
      double buyAmt=0;
      double getAmt =0;//discount %
      String getItem = "";
      double disAmt =0;//discount %
      double quantity=0;//qty of Get item which sale
      double discountQty=0;//discount qty of sale item
      double availableQty=0;// available qty for discount (sale qty- discount qty)
      double price = 0; //price of Get item
      double unitMarkdown=0;//for 1 unit of Get item
      double oldMarkdown=0;//old total markdown of Get item
      double markdown=0;//total markdown of Get item
      double amountDue=0;//amount of row which apply promo
      int updateRow=0;// position of row which apply promo



            double saleQty=0;//qty of Buy Item which sale
            //get update row of Get Item
            getItem = threshold.getGetUPC().elementAt(0).toString();
            for (int j = 0; j < model.getRowCount(); j++) {
              String item  = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
              if (getItem.equals(item)) {
                updateRow = j;
                found =true;
                break;//GetItem: only 1 item
              }
            }
            //exit if not found GetItem
            if(!found){
              return;
            }

            for (int j = 0; j < model.getRowCount(); j++) {
              String item  = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
              if (threshold.getBuyUPC().contains(item)) {
                saleQty = saleQty + Double.parseDouble( (tblSaleItem.getValueAt(j, COL_QUANTITY).toString()));
              }
            }
            quantity = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_QUANTITY).toString()));
            //get current discount qty
            discountQty = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_DISTCOUNT_QTY).toString()));
            oldMarkdown = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_MARKDOWN).toString()));

            price = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_PRICE).toString()));
            //available qty = sale qty- discount qty
            availableQty = quantity - discountQty;

            //get n of X
            buyAmt=Double.parseDouble(threshold.getThresholdQty());
            //get k of Y (qty)
            getAmt=Double.parseDouble(threshold.getGetAmount().elementAt(0).toString());
            //get % discount m of Y
            disAmt=Double.parseDouble(threshold.getDiscountAmt().toString());
            if( saleQty >= buyAmt && availableQty > 0){
              // Buy  >= n items in list (X,Z) => Get Y with markdown = m%
              if(type.equals(Constant.GET_TYPE_PERCENT_OFF)){
                unitMarkdown = disAmt * price / 100;

              // Buy  >= n items X => Get Y with price = m
              }else if(type.equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
                unitMarkdown =   price-disAmt;
              // Buy  >= n items X => Get Y with markdown = m
              }else if(type.equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
                  unitMarkdown = disAmt ;
              }

              if (availableQty >= getAmt){ //apply for (getAmt) item Y
                markdown = getAmt * unitMarkdown;
                discountQty = discountQty + getAmt;
              }else{//apply for all item Y
                markdown = availableQty * unitMarkdown;
                discountQty = discountQty + availableQty;
              }
              markdown = markdown + oldMarkdown;
              amountDue = quantity * price - markdown;
            }else {
              isUpdate = false;
            }




      //round off
      markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
      amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);

      //update discount and AmountDue of sale
      if(isUpdate){
         updateCalculate(updateRow, quantity,discountQty, markdown, amountDue);
      }
      setStatusLabel();

    }

  /**updateThresholdItem : calculate GetItem markdown
   *                 base on GetType: Percent Off
   * @author	Trung.Nguyen
   * @param currentItem  buy item which is processing
   * @param currentRow  row of buy item which is processing
   * return no
  **/

   void updateThresholdItem(ThresholdBusObj threshold,String type){
    boolean isUpdate = true;//true if update is neccesary
    double buyAmt=0;
    String getItem = "";
    double disAmt =0;//discount %
    double oldDisAmt =0;//last Amount discount
    double quantity=0;//qty of Get item which sale
    double price = 0; //price of item apply promo
    double unitMarkdown=0;//for 1 unit of Get item
    double markdown=0;//total markdown of Get item
    double amountDue=0;//amount of row which apply promo
    int updateRow=0;// position of row which apply promo


        // Buy total peyment >= 100 000 => discount 10%
        if(type.equals(Constant.GET_TYPE_PERCENT_OFF)){

          //update all sale item
          for (int j = 0; j < model.getRowCount(); j++) {
            quantity = Double.parseDouble( (tblSaleItem.getValueAt(j,
                COL_QUANTITY).toString()));
            price = ut.convertToDouble( (tblSaleItem.getValueAt(j,
                COL_PRICE).toString()));
            oldDisAmt= oldDisAmt + ut.convertToDouble( (tblSaleItem.getValueAt(j,
                COL_MARKDOWN).toString()));
            amountDue +=  quantity * price;
          }
          if(amountDue >= Double.parseDouble(threshold.getThresholdAmt())){
            disAmt = Double.parseDouble(threshold.getDiscountAmt());
            //if multi threshold => apply threshold with max disAmt
            if(oldDisAmt < (disAmt*amountDue)/100 ){
              for (int j = 0; j < model.getRowCount(); j++) {
                quantity = Double.parseDouble( (tblSaleItem.getValueAt(j,
                    COL_QUANTITY).toString()));
                price = ut.convertToDouble( (tblSaleItem.getValueAt(j,
                    COL_PRICE).
                                             toString()));
                unitMarkdown = price * disAmt / 100;
                markdown = quantity * unitMarkdown;
                amountDue = quantity * (price - unitMarkdown);

                updateCalculate(j, quantity, markdown, amountDue);
              }
            }
          }
          //no need update any more
          isUpdate=false;


        }

    //round off
    markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
    amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);

    //update discount and AmountDue of sale
    if(isUpdate){
      updateCalculate(updateRow, quantity, markdown, amountDue);
    }
    setStatusLabel();
  }

//======================= Mix Match & Package Promo ==========================//
//============================================================================//

  /** applyMixMatchPromo apply Mix Match to sale items
   *  Put Mix Match info into Mix Match object
   * @author	Trung.Nguyen
   * return no
  **/

//Mix Match
  void applyMixMatchPromo(){

    MixMatchBusObj mixMatch=new MixMatchBusObj();
    String listSaleItemID="";
    ResultSet rs=null;
    ResultSet rsBuy=null;
    ResultSet rsGet=null;
    String mixMatchNo="";
    Vector vBuyItem = new Vector();
    Vector vGetItem = new Vector();
    Vector vBuyAmount = new Vector();
    Vector vGetAmount = new Vector();

    //Exit if no sale item
    if(model.getRowCount()==0){
      return;
    }
    //Get lis sale item
    for (int i = 0; i < model.getRowCount(); i++) {
      if(listSaleItemID.equals("")){
        listSaleItemID = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
      }else{
        listSaleItemID = listSaleItemID + "," +
            tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
      }
    }
//    System.out.println("lis sale: -------- "+ listSaleItemID);
    //Get all Mix match promo which have BuyItem in NewSale item and promo affect today

    String sql = "select distinct h.* from BTM_POS_PROMO_DETAIL h ,BTM_POS_PROMO_MIX_MATCH_BUY b where status=1 and "
        + " trunc(START_TIME)<= to_date('" + ut.getSystemDate() +"','" + ut.DATE_FORMAT + "') and "
        + " to_date('" + ut.getSystemDate() +"','" + ut.DATE_FORMAT + "') <= trunc(END_TIME) and "
        + " h.MIX_MATCH_NO <> 'N' and h.MIX_MATCH_NO = b.MIX_MATCH_NO and b.ITEM_ID in (" + listSaleItemID + ") " ;
//    System.out.println(sql);
    rs = DAL.executeQueries(sql);

    try {
      //Process each Mix Match Promo
      while(rs.next()){

        mixMatchNo = rs.getString("MIX_MATCH_NO");

        mixMatch.setTransType(rs.getString("PROMO_TRAN_TYPE"));
        mixMatch.setBuyType(rs.getString("BUY_TYPE"));
        mixMatch.setGetType(rs.getString("GET_TYPE"));
        mixMatch.setStatus(rs.getString("STATUS"));

        //put Mix Match Buy into mixMatch obj
        sql =
            "select * from BTM_POS_PROMO_MIX_MATCH_BUY where MIX_MATCH_NO = " +
            mixMatchNo;
//        System.out.println(sql);
        rsBuy = DAL.executeQueries(sql);

        while (rsBuy.next()) {
          vBuyItem.add(rsBuy.getString("Item_ID"));
          vBuyAmount.add(rsBuy.getString("Buy_Amt"));
        }
        mixMatch.setBuyUPC(vBuyItem);
        mixMatch.setBuyAmount(vBuyAmount);

        //Get Mix Match Get into mixMatch obj
        sql =
            "select * from BTM_POS_PROMO_MIX_MATCH_GET where MIX_MATCH_NO = " +
            mixMatchNo;
//        System.out.println(sql);
        rsGet = DAL.executeQueries(sql);

        while (rsGet.next()) {
          vGetItem.add(rsGet.getString("Item_ID"));
          vGetAmount.add(rsGet.getString("Get_Amt"));
        }
        mixMatch.setGetUPC(vGetItem);
        mixMatch.setGetAmount(vGetAmount);

        //process
        processMixMatch(mixMatch);

        //reset vector for next MixMatch
        vBuyItem.clear();
        vBuyAmount.clear();
        vGetItem.clear();
        vGetAmount.clear();

        rsBuy.getStatement().close();
        rsGet.getStatement().close();
      }

    rs.getStatement().close();
    }catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }

  }
  /** processMixMatch: process Mix Match promo base on
   * @author	Trung.Nguyen
   * @param mixMatch Mix Match obj
   * @param

   * return no
  **/
 //Mix Match
  void processMixMatch(MixMatchBusObj mixMatch){
    String getItem = "";
    String item = "";
    int row=0;
    double quantity=0;
    double price=0;
    double markdown=0;//total markdown of item
    double amountDue=0;

        //MIX_MATCH_PROMO type
        if (mixMatch.getTransType().equals(MIX_MATCH_ANY)) {
          //Loop sale item
          for (int i = 0; i < model.getRowCount(); i++) {
            item = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
            //process if sale item in BuyItem
            if (mixMatch.getBuyUPC().contains(item)) {
              // Buy n X Get Y with  markdown = m%
              if(mixMatch.getGetType().equals(Constant.GET_TYPE_PERCENT_OFF)){
                updateMixMatchItem(mixMatch, i,Constant.GET_TYPE_PERCENT_OFF);
              // Buy n X Get Y with price=m
              }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
                updateMixMatchItem(mixMatch, i,Constant.GET_TYPE_FIXED_UNIT_PRICE);
              // Buy n X Get 1 Y with markdown = m
              }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
                updateMixMatchItem(mixMatch, i,Constant.GET_TYPE_UNIT_PRICE_OFF);
                //Not use  GET_TYPE_FIXED_TOTAL_PRICE
              }
              else if (mixMatch.getGetType().equals(Constant.
                  GET_TYPE_FIXED_TOTAL_PRICE)) {
                //-- no code here
                ut.showMessage(frmMain, lang.getString("TT001"),
                               lang.getString("NoNeedCode")+ " " +  Constant.GET_TYPE_FIXED_TOTAL_PRICE);
                return;

                //Not use  GET_TYPE_TOTAL_PRICE_OFF
              }
              else if (mixMatch.getGetType().equals(Constant.
                  GET_TYPE_TOTAL_PRICE_OFF)) {
                //-- no code here
                ut.showMessage(frmMain, lang.getString("TT001"),
                               lang.getString("NoNeedCode")+ " " + Constant.GET_TYPE_TOTAL_PRICE_OFF);
                return;

                //Buy n X get m Y free
              }
              else if (mixMatch.getGetType().equals(Constant.GET_TYPE_GET_FREE)) {
                updateMixMatchFreeItem(mixMatch, i,Constant.GET_TYPE_GET_FREE);
              }

            }
          }
         //PACKAGE_PROMO type
        }else if (mixMatch.getTransType().equals(MIX_MATCH_PACKAGE)) {
          updatePackageItem(mixMatch,0);

         //MIX MATCH LIST type
        }else if (mixMatch.getTransType().equals(MIX_MATCH_LIST)) {
          double  buyAmt = Double.parseDouble(mixMatch.getBuyAmount().elementAt(0).toString());
          //buy items in list (X,Z) => apply promo to all buy item
          if(buyAmt ==1 ){
            //Loop sale item
            for (int i = 0; i < model.getRowCount(); i++) {
              item = tblSaleItem.getValueAt(i, COL_ITEM_ID).toString();
              //process if sale item in BuyItem
              if (mixMatch.getBuyUPC().contains(item)) {
                // Buy  item in list (X,Z )   markdown = m% to all
                if(mixMatch.getGetType().equals(Constant.GET_TYPE_PERCENT_OFF)){
                  updateMixMatchList1(mixMatch, i,Constant.GET_TYPE_PERCENT_OFF);
                 // Buy  item in list (X,Z )  price=m to all
                }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
                  updateMixMatchList1(mixMatch, i,Constant.GET_TYPE_FIXED_UNIT_PRICE);
                // Buy  item in list (X,Z )  markdown = m to all
                }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
                   updateMixMatchList1(mixMatch, i,Constant.GET_TYPE_UNIT_PRICE_OFF);
                //Not use  GET_TYPE_FIXED_TOTAL_PRICE
                }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_FIXED_TOTAL_PRICE)){
                           //-- no code here
                           ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("NoNeedCode")+ " " +Constant.GET_TYPE_FIXED_TOTAL_PRICE);
                           return;

                //Not use  GET_TYPE_TOTAL_PRICE_OFF
                }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_TOTAL_PRICE_OFF)){
                          //-- no code here
                          ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("NoNeedCode")+ " " +Constant.GET_TYPE_TOTAL_PRICE_OFF);
                          return;

                }


              }
            }
          // buy n items in list (X,Z) => apply promo to n Y  if Get Item exist,
          //                               else apply to n X (X has max qty  in list)

          }else if (buyAmt >1){
            // Buy n item in list (X,Z ) Get Y with  markdown = m%
            if(mixMatch.getGetType().equals(Constant.GET_TYPE_PERCENT_OFF)){
              updateMixMatchListN(mixMatch,Constant.GET_TYPE_PERCENT_OFF);
            // Buy n item in list (X,Z ) Get Y with price=m
           }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
             updateMixMatchListN(mixMatch,Constant.GET_TYPE_FIXED_UNIT_PRICE);
           // Buy n item in list (X,Z ) Get 1 Y with markdown = m
           }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
              updateMixMatchListN(mixMatch,Constant.GET_TYPE_UNIT_PRICE_OFF);

            //Not use  GET_TYPE_FIXED_TOTAL_PRICE
            }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_FIXED_TOTAL_PRICE)){
                      //-- no code here
                      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("NoNeedCode")+ " " +Constant.GET_TYPE_FIXED_TOTAL_PRICE);
                      return;

            //Not use  GET_TYPE_TOTAL_PRICE_OFF
          }else if(mixMatch.getGetType().equals(Constant.GET_TYPE_TOTAL_PRICE_OFF)){
                     //-- no code here
                     ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("NoNeedCode")+ " " +Constant.GET_TYPE_TOTAL_PRICE_OFF);
                     return;

              }

          }
        }//end if type

  }

  /**updatePackageItem : calculate GetItem markdown
   *                 base on GetType: Percent Off, Unit Price, Unit Price Off, Get Free, List Percent
   * @author	Trung.Nguyen
   * @param currentItem  buy item which is processing
   * @param currentRow  row of buy item which is processing
   * return no
  **/
 //Mix Match
   void updatePackageItem(MixMatchBusObj mixMatch, int currentRow){

   }

   /**updateMixMatchList1 : //buy items in list (X,Z) => apply promo to all buy item
    *                 base on GetType: Percent Off, Unit Price, Unit Price Off, Get Free, List Percent
    * @author	Trung.Nguyen
    * @param currentItem  buy item which is processing
    * @param currentRow  row of buy item which is processing
    * return no
   **/
  //Mix Match
    void updateMixMatchList1(MixMatchBusObj mixMatch, int currentRow,String type){
//      boolean found =false ; //true if found GetItem in sale item
     boolean isUpdate = true;//true if update is neccesary
//     double buyAmt=0;
     String getItem = "";
     double getAmt =0;//discount %
     double quantity=0;//qty of Get item which sale
     double discountQty=0;//discount qty of sale item
//     double availableQty=0;// available qty for discount (sale qty- discount qty)
     double price = 0; //price of Get item
     double unitMarkdown=0;//for 1 unit of Get item
     double markdown=0;//total markdown of Get item
//     double oldMarkdown=0;//old total markdown of Get item
     double amountDue=0;//amount of row which apply promo
     int updateRow=0;// position of row which apply promo


           //update at current row (BUY ITEM)
           updateRow = currentRow;

           quantity = Double.parseDouble( (tblSaleItem.getValueAt(updateRow,
               COL_QUANTITY).toString()));
           price = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow,
               COL_PRICE).toString()));

           getAmt = Double.parseDouble(mixMatch.getGetAmount().elementAt(0).
                                       toString());
           // Buy  item in list (X,Z ) Get Y with  markdown = m%
           if(type.equals(Constant.GET_TYPE_PERCENT_OFF)){
             unitMarkdown = price * getAmt / 100;
            // Buy  item in list (X,Z ) Get Y with price=m
           }else if(type.equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
             unitMarkdown = price - getAmt ;
           // Buy  item in list (X,Z ) Get 1 Y with markdown = m
           }else if(type.equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
              unitMarkdown =  getAmt ;
           }


           markdown = quantity * unitMarkdown;
           amountDue = quantity * (price - unitMarkdown);


     //round off
     markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
     amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);

     //update discount, Discount Qty and AmountDue of sale
     if(isUpdate){
       updateCalculate(updateRow, quantity,discountQty, markdown, amountDue);
     }
     setStatusLabel();

    }
    /**updateMixMatchList :  buy n items in list (X,Z) => apply promo to item Y if one Get Item , else if many Get Item apply to max qty item in list
     *                 base on GetType: Percent Off, Unit Price, Unit Price Off, Get Free, List Percent
     * @author	Trung.Nguyen
     * @param currentItem  buy item which is processing
     * @param currentRow  row of buy item which is processing
     * return no
    **/

    void updateMixMatchListN(MixMatchBusObj mixMatch,String type){
      boolean found =false ; //true if found GetItem in sale item
     boolean isUpdate = true;//true if update is neccesary
     double buyAmt=0;
     String getItem = "";
     double getAmt =0;//discount %
     double quantity=0;//qty of Get item which sale
     double discountQty=0;//discount qty of sale item
     double availableQty=0;// available qty for discount (sale qty- discount qty)
     double price = 0; //price of Get item
     double unitMarkdown=0;//for 1 unit of Get item
     double markdown=0;//total markdown of Get item
     double oldMarkdown=0;//old total markdown of Get item
     double amountDue=0;//amount of row which apply promo
     int updateRow=0;// position of row which apply promo

     int times = 0; //times to apply apromo for Y
     double saleQty = 0; //qty of Buy Item which sale

     Vector usedRow =new Vector(); // row  did use to apply promo
     int leftQty=0;//promo qty  must be apply (Get Item)


     //if only one GetItem => update  Get Item Y
     //if many GetItem => update to Get Item which maximum qty

     for (int j = 0; j < model.getRowCount(); j++) {
       String item = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
       if (mixMatch.getBuyUPC().contains(item)) {
         saleQty = saleQty +
             Double.parseDouble( (tblSaleItem.getValueAt(j, COL_QUANTITY).toString()));
       }
     }

//Loop only when apply promo on many Get Items
     do{
          quantity=0;
          for (int j = 0; j < model.getRowCount(); j++) {
            String item = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
            if(!usedRow.contains(String.valueOf(j))){
              if (mixMatch.getGetUPC().contains(item)) {
                if (quantity < Double.parseDouble( (tblSaleItem.getValueAt(j,
                    COL_QUANTITY).toString()))) {
                  quantity = Double.parseDouble( (tblSaleItem.getValueAt(j,
                      COL_QUANTITY).toString()));
                  updateRow = j;
                }
                found = true;
              }
            }
          }

           if (!found) {
             return;
           }


           //=================
           quantity = Double.parseDouble( (tblSaleItem.getValueAt(updateRow,
               COL_QUANTITY).toString()));
           //get current discount qty
           discountQty = Double.parseDouble( (tblSaleItem.getValueAt(updateRow,
               COL_DISTCOUNT_QTY).toString()));
           oldMarkdown = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow,
               COL_MARKDOWN).toString()));

           price = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_PRICE).
                                        toString()));
           //available qty = sale qty- discount qty
           availableQty = quantity - discountQty;

           //get n of (X,Z)
           buyAmt = Double.parseDouble(mixMatch.getBuyAmount().elementAt(0).toString());
           //get new price m of Y
           getAmt = Double.parseDouble(mixMatch.getGetAmount().elementAt(0).toString());


           //get left qty at first times
           if(usedRow.size()==0){
             times = ut.doubleStringToInt(String.valueOf(saleQty / buyAmt));
             leftQty = times;

           }else{//process left qty
             times=leftQty;
           }
           if (times > 0 && availableQty > 0) {
             // Buy n item in list (X,Z ) Get n Y with  markdown = m%
             if(type.equals(Constant.GET_TYPE_PERCENT_OFF)){
               unitMarkdown = getAmt * price / 100;
             // Buy n item in list (X,Z ) Get n Y with price=m
            }else if(type.equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
              unitMarkdown =  price-getAmt;
            // Buy n item in list (X,Z ) Get n Y with markdown = m
            }else if(type.equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
               unitMarkdown = getAmt ;
            }

             if (availableQty >= times) { //apply for 'times' item Y
               markdown = times * unitMarkdown;
               discountQty = discountQty + times;
             }
             else { //apply for all item Y
               markdown = availableQty * unitMarkdown;
               discountQty = discountQty + availableQty;
             }
             markdown = markdown + oldMarkdown;
             amountDue = quantity * price - markdown;
             //keep left qty must be promoted
             leftQty= leftQty- ut.doubleStringToInt(String.valueOf(discountQty));
             usedRow.addElement(String.valueOf(updateRow));
           }
           else {
             isUpdate = false;
           }
     //round off
     markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
     amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);

     //update discount, Discount Qty and AmountDue of sale
     if(isUpdate){
       updateCalculate(updateRow, quantity,discountQty, markdown, amountDue);
     }
   }while(leftQty>0 && (mixMatch.getGetUPC().size()!=1));


     setStatusLabel();

    }

  /**updateMixMatchItem : calculate GetItem markdown
   *                 base on GetType: Percent Off, Unit Price, Unit Price Off, Get Free, List Percent
   * @author	Trung.Nguyen
   * @param currentItem  buy item which is processing
   * @param currentRow  row of buy item which is processing
   * return no
  **/
 //Mix Match
   void updateMixMatchItem(MixMatchBusObj mixMatch, int currentRow,String type){
     boolean found =false ; //true if found GetItem in sale item
    boolean isUpdate = true;//true if update is neccesary
    double buyAmt=0;
    String getItem = "";
    String buyItem = "";
    double getAmt =0;//discount %
    double quantity=0;//qty of Get item which sale
    double discountQty=0;//discount qty of sale item
    double availableQty=0;// available qty for discount (sale qty- discount qty)
    double price = 0; //price of Get item
    double unitMarkdown=0;//for 1 unit of Get item
    double markdown=0;//total markdown of Get item
    double oldMarkdown=0;//old total markdown of Get item
    double amountDue=0;//amount of row which apply promo
    int updateRow=0;// position of row which apply promo


    buyItem = tblSaleItem.getValueAt(currentRow, COL_ITEM_ID).toString();

          int times=0; //times to apply apromo for Y
          double saleQty=0;//qty of Buy Item which sale
          //get update row of Get Item
          getItem = mixMatch.getGetUPC().elementAt(0).toString();
          for (int j = 0; j < model.getRowCount(); j++) {
            String item  = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
            if (getItem.equals(item)) {
              updateRow = j;
              found =true;
              break;//GetItem: only 1 item
            }
          }
          //exit if not found GetItem
          if(!found){
            return;
          }
          saleQty = Double.parseDouble( (tblSaleItem.getValueAt(currentRow, COL_QUANTITY).toString()));
          quantity = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_QUANTITY).toString()));
          //get current discount qty
          discountQty = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_DISTCOUNT_QTY).toString()));
          oldMarkdown = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_MARKDOWN).toString()));

          price = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_PRICE).toString()));
          //available qty = sale qty- discount qty
          availableQty = quantity - discountQty;

          //get n of X
          buyAmt=Double.parseDouble(mixMatch.getBuyAmount().elementAt(mixMatch.getBuyUPC().indexOf(buyItem) ).toString());
          //get % discount m of Y
          getAmt=Double.parseDouble(mixMatch.getGetAmount().elementAt(0).toString());
          times = ut.doubleStringToInt( String.valueOf( saleQty/buyAmt));
          if(times > 0 && availableQty > 0){
            // Buy n X Get Y with  markdown = m%
            if(type.equals(Constant.GET_TYPE_PERCENT_OFF)){
              unitMarkdown = getAmt * price / 100;
            // Buy n X Get Y with price=m
            }else if(type.equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
              unitMarkdown =   price -getAmt;
            // Buy n X Get 1 Y with markdown = m
            }else if(type.equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
              unitMarkdown = getAmt ;
            }
            if(availableQty >= times){ //apply for 'times' item Y
              markdown = times  * unitMarkdown;
              discountQty = discountQty + times;
            }else{//apply for all item Y
              markdown = availableQty * unitMarkdown;
              discountQty = discountQty + availableQty;
            }
            markdown = markdown + oldMarkdown;
            amountDue = quantity * price - markdown;

          }else{
            isUpdate=false;
          }




    //round off
    markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
    amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);

    //update discount, Discount Qty and AmountDue of sale
    if(isUpdate){
      updateCalculate(updateRow, quantity,discountQty, markdown, amountDue);
    }
    setStatusLabel();
  }

  /**updateMixMatchFreeItem : calculate GetItem markdown
   *                 base on GetType: Percent Off, Unit Price, Unit Price Off, Get Free, List Percent
   * @author	Trung.Nguyen
   * @param currentItem  buy item which is processing
   * @param currentRow  row of buy item which is processing
   * return no
  **/
 //Mix Match
   void updateMixMatchFreeItem(MixMatchBusObj mixMatch, int currentRow,String type){
     boolean found =false ; //true if found GetItem in sale item
    boolean isUpdate = true;//true if update is neccesary
    double buyAmt=0;
    String getItem = "";
    String buyItem = "";
    double getAmt =0;//discount %
    double quantity=0;//qty of Get item which sale
    double discountQty=0;//discount qty of sale item
    double availableQty=0;// available qty for discount (sale qty- discount qty)
    double price = 0; //price of Get item
    double unitMarkdown=0;//for 1 unit of Get item
    double markdown=0;//total markdown of Get item
    double oldMarkdown=0;//old total markdown of Get item
    double amountDue=0;//amount of row which apply promo
    int updateRow=0;// position of row which apply promo


    buyItem = tblSaleItem.getValueAt(currentRow, COL_ITEM_ID).toString();

          int times=0; //times to apply apromo for Y
          double saleQty=0;//qty of Buy Item which sale
          //get update row of Get Item
          getItem = mixMatch.getGetUPC().elementAt(0).toString();
          for (int j = 0; j < model.getRowCount(); j++) {
            String item  = tblSaleItem.getValueAt(j, COL_ITEM_ID).toString();
            if (getItem.equals(item)) {
              updateRow = j;
              found =true;
              break;//GetItem: only 1 item
            }
          }
          //exit if not found GetItem
          if(!found){
            return;
          }

          saleQty = Double.parseDouble( (tblSaleItem.getValueAt(currentRow, COL_QUANTITY).toString()));
          quantity = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_QUANTITY).toString()));
          //get current discount qty
          discountQty = Double.parseDouble( (tblSaleItem.getValueAt(updateRow, COL_DISTCOUNT_QTY).toString()));
          oldMarkdown = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_MARKDOWN).toString()));

          price = ut.convertToDouble( (tblSaleItem.getValueAt(updateRow, COL_PRICE).toString()));
          //available qty = sale qty- discount qty
          availableQty = quantity - discountQty;

          //get n of X
          buyAmt=Double.parseDouble(mixMatch.getBuyAmount().elementAt(mixMatch.getBuyUPC().indexOf(buyItem) ).toString());

          //get m of Y (qty)
          getAmt=Double.parseDouble(mixMatch.getGetAmount().elementAt(0).toString());
          times = ut.doubleStringToInt( String.valueOf( saleQty/buyAmt));
          //buy item = get item => availableQty
          if(buyItem.equals(getItem)){
             availableQty=  ut.doubleStringToInt (String.valueOf(saleQty/(buyAmt+getAmt))) *getAmt -discountQty;
          }

          if(times > 0 && availableQty > 0){
            unitMarkdown = price;
            if(availableQty >= times*getAmt){ //apply for (times*getAmt) item Y
              markdown = (times * getAmt) * unitMarkdown;
              discountQty = discountQty + times*getAmt;
            }else{//apply for all item Y
              markdown = availableQty * unitMarkdown;
              discountQty = discountQty + availableQty;
            }
            markdown = markdown + oldMarkdown;
            amountDue = quantity * price - markdown;

          }else{
            isUpdate=false;
          }

    //round off
    markdown=ut.getRound(markdown,Constant.PM_DECIMAL_LENGTH);
    amountDue=ut.getRound(amountDue,Constant.PM_DECIMAL_LENGTH);

    //update discount, Discount Qty and AmountDue of sale
    if(isUpdate){
      updateCalculate(updateRow, quantity,discountQty, markdown, amountDue);
    }
    setStatusLabel();
  }

  /**resetSaleItem : reset Get Item which sale : set discount=0, discount Qty =0
   *
   * @author	Trung.Nguyen
   * return no
  **/
 //
   void resetSaleItem(){
     double quantity=0;//qty of sale item
     double discountQty=0;//discount qty of sale item
     double price = 0; //price of item apply promo
     double markdown=0;//total markdown of item
     double amountDue=0;//amount of row which apply promo
     ResultSet rs1=null;
     ResultSet rs2=null;
     Vector vGetItem = new Vector();//list get item



     String sql = " select item_id from BTM_POS_PROMO_MIX_MATCH_GET where MIX_MATCH_NO in (select  h.MIX_MATCH_NO from BTM_POS_PROMO_DETAIL h  where status=1 and  trunc(START_TIME)<= to_date('" + ut.getSystemDate() +"','" + ut.DATE_FORMAT + "') and  to_date('" + ut.getSystemDate() +"','" + ut.DATE_FORMAT + "') <= trunc(END_TIME) and  h.MIX_MATCH_NO <> 'N') ";

//     System.out.println(sql);
     rs1 = DAL.executeQueries(sql);

     try {
       //Process each Threshold Promo
       while(rs1.next()){
         vGetItem.add(rs1.getString("item_id") );
//         System.out.println("--------- "+rs1.getString("item_id"));
       }
       rs1.getStatement().close();
     }catch (Exception ex) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(ex);
     }

      sql = " select item_id from BTM_POS_PROMO_THRESHOLD_SKU where BUY_GET_FLAG=1 and THRESHOLD_NO in (select  h.THRESHOLD_NO from BTM_POS_PROMO_DETAIL h  where status=1 and  trunc(START_TIME)<= to_date('" + ut.getSystemDate() +"','" + ut.DATE_FORMAT + "') and  to_date('" + ut.getSystemDate() +"','" + ut.DATE_FORMAT + "') <= trunc(END_TIME) and h.THRESHOLD_NO <> 'N')";

//     System.out.println(sql);
     rs2 = DAL.executeQueries(sql);

     try {
       //Process each Threshold Promo
       while(rs2.next()){
         vGetItem.add(rs2.getString("item_id") );
//         System.out.println("--------- "+rs2.getString("item_id"));
       }
       rs2.getStatement().close();
     }catch (Exception ex) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(ex);
     }

     //if promoAllItem=true :reset all item
     //else reset Get ITem only
     for (int j = 0; j < model.getRowCount(); j++) {
//System.out.println("----"+tblSaleItem.getValueAt(j, COL_ITEM_ID).toString());
       if(discCust || promoAllItem || vGetItem.contains(tblSaleItem.getValueAt(j, COL_ITEM_ID).toString())){
         quantity = Double.parseDouble( (tblSaleItem.getValueAt(j, COL_QUANTITY).
                                         toString()));
         //get current discount qty
         discountQty = 0;
         markdown = 0;
         price = ut.convertToDouble( (tblSaleItem.getValueAt(j, COL_PRICE).
                                      toString()));
         amountDue = quantity * price;
         //round off
         amountDue = ut.getRound(amountDue, Constant.PM_DECIMAL_LENGTH);

         updateCalculate(j, quantity, discountQty, markdown, amountDue);

//         System.out.println("-- "+promoAllItem);
       }
     }
     setStatusLabel();
     promoAllItem=false;
     discCust=false;
   }


}


class PanelNewSale_btnEvenExchange_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnEvenExchange_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnEvenExchange_actionPerformed(e);
  }
}

class PanelNewSale_btnModdifyQuan_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnModdifyQuan_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModdifyQuan_actionPerformed(e);
  }
}

class PanelNewSale_btnReturnItem_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnReturnItem_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnReturnItem_actionPerformed(e);
  }
}

class PanelNewSale_btnDiscount_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnDiscount_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDiscount_actionPerformed(e);
  }
}

class PanelNewSale_btnDeleteItem_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnDeleteItem_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDeleteItem_actionPerformed(e);
  }
}

class PanelNewSale_btnAddItem_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnAddItem_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAddItem_actionPerformed(e);
  }
}

class PanelNewSale_btnMarkdown_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnMarkdown_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnMarkdown_actionPerformed(e);
  }
}

class PanelNewSale_btnTotal_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnTotal_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnTotal_actionPerformed(e);
  }
}

class PanelNewSale_btnSaveTXN_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnSaveTXN_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSaveTXN_actionPerformed(e);
  }
}

class PanelNewSale_btnGetTXN_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnGetTXN_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnGetTXN_actionPerformed(e);
  }
}

class PanelNewSale_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnBack_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelNewSale_btnDiscountPrice_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnDiscountPrice_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDiscountPrice_actionPerformed(e);
  }
}

class PanelNewSale_btnDiscountUSD_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnDiscountUSD_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDiscountUSD_actionPerformed(e);
  }
}

class PanelNewSale_btnDiscountPercent_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnDiscountPercent_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDiscountPercent_actionPerformed(e);
  }
}

class PanelNewSale_btnDiscountBack_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnDiscountBack_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDiscountBack_actionPerformed(e);
  }
}

class PanelNewSale_btnMarkdownPrice_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnMarkdownPrice_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnMarkdownPrice_actionPerformed(e);
  }
}

class PanelNewSale_btnMarkdownUSD_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnMarkdownUSD_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnMarkdownUSD_actionPerformed(e);
  }
}

class PanelNewSale_btnMarkdownPercent_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnMarkdownPercent_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnMarkdownPercent_actionPerformed(e);
  }
}

class PanelNewSale_btnMarkdownBack_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnMarkdownBack_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnMarkdownBack_actionPerformed(e);
  }
}

class PanelNewSale_btnCustomer_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnCustomer_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCustomer_actionPerformed(e);
  }
}

class PanelNewSale_btnCancelTXN_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnCancelTXN_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancelTXN_actionPerformed(e);
  }
}

class PanelNewSale_tblSaleItem_keyAdapter extends java.awt.event.KeyAdapter {
  PanelNewSale adaptee;

  PanelNewSale_tblSaleItem_keyAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tblSaleItem_keyPressed(e);
  }
}

class PanelNewSale_btnDiscountAll_actionAdapter implements java.awt.event.ActionListener {
  PanelNewSale adaptee;

  PanelNewSale_btnDiscountAll_actionAdapter(PanelNewSale adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDiscountAll_actionPerformed(e);
  }
}
