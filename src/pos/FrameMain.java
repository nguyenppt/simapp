package pos;

import btm.attr.*;
import btm.swing.*;
import common.Utils;
import common.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.Timer;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @version 1.0.
 */

public class FrameMain extends JFrame {
  private static final int NUM_CHAR_SEARCH =3 ;

  DataAccessLayer DAL =new DataAccessLayer();
  Transaction trans;//=new Transaction();
  Utils ut=new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  Timer timer = new Timer();

  int flagScreen=0;//determine screen is working on : New SSAle, Add Item, Even Exchange, Total
  int frmMainCount = 1;

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel pnlScreen = new JPanel();
  JPanel pnlButton = new JPanel();
  JPanel pnlMoneySymbol = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlCenter = new JPanel();
  JPanel pnlStatus = new JPanel();
  JPanel pnlInputItem = new JPanel();
  JLabel lblMode = new JLabel();
  JLabel lblStore = new JLabel();
  JLabel lblCashier = new JLabel();
  JLabel lblRegister = new JLabel();
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout2 = new GridLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel pnlItemCode = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JTextField txtMainInput = new JTextField();
  JLabel lblMainInput = new JLabel();
  GridLayout gridLayout3 = new GridLayout();
  PanelMain pnlMain;
  PanelAddItem pnlAddItem;
  PanelEvenExchange pnlEvenExchange;
  PanelNetSaleByItem pnlNetSalesByItem;
  PanelNetSalesByTrans pnlNetSalesByTrans;
  PanelViewTrans pnlViewTrans;
  PanelNewSale pnlNewSale;
  PanelPayment pnlPayment;
  PanelPrintReciept pnlPrintReciept;
  PanelReturnItem pnlReturnItem;
  PanelReturnNotFoundItem pnlReturnNotFoundItem;
  PanelVoidTransaction pnlVoidTransaction;
  PanelPaidIn pnlPaidIn;
  PanelPaidOut pnlPaidOut;
  PanelDetailTrans pnlDetailTrans;
  PanelViewTransaction pnlViewTransaction;
  PanelDetailTransaction pnlDetailTransaction;
  PanelRegisterClosedOut pnlRegisterClose;
  PanelInventoryReceipt pnlInventoryReceipt;
  PanelViewReceiptItem pnlViewReceiptItem;
  PanelScheduleTimer pnlScheduleTimer;
  PanelReceiptItem pnlReceiptItem;
  PanelCustomerSetup pnlCustomerSetup;
  PanelRunPosBatchJobs pnlRunPosBatchJob;
  PanelCustomerModify pnlCustomerModify;
  PanelPrintNetSaleReport pnlPrintNetSaleReport;

  PanelStockCount pnlStockCount;

  PanelTransferItem pnlTransferItem;
  PanelViewTransferTransaction pnlViewTransferTrans;
  PanelLogOff pnlLogOff;

//  PanelDamageGoods pnlDamageGoods;
//  PanelViewDmgGoods pnlViewDmgGoos;
//  PanelModifyDmgGoods pnlModifyDmgGoods;
  PanelExchangeRate pnlExchangeRate;
  PanelExRateView pnlExRateView;
  CardLayout cardLayout1 = new CardLayout();

  JPanel jPanel2 = new JPanel();


  FlowLayout flowLayout2 = new FlowLayout();
//==========
  public int flagEvenExchangeConstant;
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JButton btnDate = new JButton();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JLabel lblCustomer = new JLabel();
  GridLayout gridLayout4 = new GridLayout();
  JLabel lblPhone = new JLabel();
  JLabel lblName = new JLabel();
  JLabel lblTimer = new JLabel();
  JPanel jPanel12 = new JPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  JPanel jPanel13 = new JPanel();
  JPanel jPanel15 = new JPanel();
  BJComboBox cboCreditCard = new BJComboBox();
  JLabel jLabel1 = new JLabel();
  Border border1 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border2 = new TitledBorder(border1, lang.getString("Customer"));
  FlowLayout flowLayout1 = new FlowLayout();
  public FrameMain(Transaction trans) {
      DAL.getOracleConnect();
      try {
        this.trans=trans;
        jbInit();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  private void jbInit() throws Exception {
    border2 = new TitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.
        RAISED, new Color(59, 112, 164), Color.lightGray), lang.getString("Customer"));
    setLableInVisible();
    setButtonInVisible();
    setComboInVisible();
    this.getContentPane().setLayout(borderLayout1);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.setEnabled(true);
    this.setLocale(java.util.Locale.getDefault());
    this.setResizable(false);
    this.setSize(800,600);
    this.setState(Frame.NORMAL);
    this.addKeyListener(new FrameMain_this_keyAdapter(this));
//    this.addWindowListener(new FrameMain_this_windowAdapter(this));
    flagEvenExchangeConstant = Constant.EVEN_EXCHANGE_TRANS;
    // Declare these panels
    pnlMain=new PanelMain(this);
    pnlAddItem=new PanelAddItem(this);
    pnlEvenExchange=new PanelEvenExchange(this);
    pnlNetSalesByItem = new PanelNetSaleByItem(this);
    pnlNetSalesByTrans = new PanelNetSalesByTrans(this);
    pnlViewTrans = new PanelViewTrans(this);
    pnlNewSale=new PanelNewSale(this);
    pnlExchangeRate = new PanelExchangeRate(this);
    pnlExRateView = new PanelExRateView(this);
    pnlPayment=new PanelPayment(this);
    pnlStockCount=new PanelStockCount(this);
    pnlPrintReciept=new PanelPrintReciept(this);
    pnlReturnItem=new PanelReturnItem(this);
    pnlReturnNotFoundItem =new PanelReturnNotFoundItem(this);
    pnlVoidTransaction = new PanelVoidTransaction(this);
    pnlPaidIn = new PanelPaidIn(this);
    pnlPaidOut = new PanelPaidOut(this);
    pnlDetailTrans = new PanelDetailTrans(this);
    pnlViewTransaction = new PanelViewTransaction(this);
    pnlDetailTransaction = new PanelDetailTransaction(this);
    pnlRegisterClose=new PanelRegisterClosedOut(this);
    pnlInventoryReceipt=new PanelInventoryReceipt(this);
    pnlViewReceiptItem=new PanelViewReceiptItem(this);
    pnlReceiptItem=new PanelReceiptItem(this);

    pnlScheduleTimer= new PanelScheduleTimer(this);

    pnlCustomerSetup = new PanelCustomerSetup(this);
    pnlRunPosBatchJob = new PanelRunPosBatchJobs(this);
    pnlCustomerModify = new PanelCustomerModify(this);
//    pnlPrintNetSaleReport = new PanelPrintNetSaleReport(this);
    pnlTransferItem = new PanelTransferItem(this);
    pnlViewTransferTrans = new PanelViewTransferTransaction(this);
//    pnlDamageGoods = new PanelDamageGoods(this);
//    pnlViewDmgGoos = new PanelViewDmgGoods(this);
//    pnlModifyDmgGoods = new PanelModifyDmgGoods(this);
    pnlLogOff = new PanelLogOff(this);
    pnlScreen.setBackground(new Color(55, 194, 255));
    pnlScreen.setLayout(borderLayout2);
    pnlButton.setBackground(Color.gray);
    pnlButton.setMinimumSize(new Dimension(395, 35));
    pnlButton.setPreferredSize(new Dimension(140, 35));
//    pnlMoneySymbol.setMinimumSize(new Dimension(395, 35));
//    pnlMoneySymbol.setPreferredSize(new Dimension(140, 35));
//    pnlMoneySymbol.setBackground(Color.gray);
    pnlButton.setLayout(flowLayout2);
//    pnlMoneySymbol.setLayout(flowLayout2);
    pnlCenter.setBackground(Color.lightGray);
    pnlCenter.setLayout(cardLayout1);
    lblMode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblMode.setBorder(BorderFactory.createEtchedBorder());
    lblMode.setToolTipText("");
    lblMode.setHorizontalAlignment(SwingConstants.CENTER);
    lblMode.setText("<html>" + lang.getString("Status") + ":<FONT color=\"yellow\">"+lang.getString("Online")+"</FONT></html>");
    lblStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStore.setBorder(BorderFactory.createEtchedBorder());
    lblStore.setHorizontalAlignment(SwingConstants.CENTER);
    lblStore.setText(lang.getString("StoreID") + ": " + DAL.getStoreID());
    lblCashier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCashier.setBorder(BorderFactory.createEtchedBorder());
    lblCashier.setDebugGraphicsOptions(0);
    lblCashier.setHorizontalAlignment(SwingConstants.CENTER);
    lblCashier.setText(lang.getString("Cashier") + ": " + DAL.getEmpPOSID());
    lblRegister.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRegister.setBorder(BorderFactory.createEtchedBorder());
    lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
    lblRegister.setText(lang.getString("Register") + ": " + DAL.getregisterID());
    pnlStatus.setLayout(gridLayout1);
    pnlInputItem.setLayout(borderLayout3);

    pnlItemCode.setLayout(new BoxLayout(pnlItemCode, BoxLayout.PAGE_AXIS));

    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setAlignmentY((float) 0.5);
    jPanel1.setPreferredSize(new Dimension(20, 10));
    jPanel3.setBackground(new Color(121, 152, 198));
    jPanel3.setPreferredSize(new Dimension(300, 10));
    jPanel3.setLayout(borderLayout4);
    pnlInputItem.setBackground(new Color(121, 152, 198));
    pnlInputItem.setAlignmentX((float) 0.5);
    pnlInputItem.setPreferredSize(new Dimension(600, 67));
    lblMainInput.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblMainInput.setForeground(new Color(0, 0, 55));
    lblMainInput.setPreferredSize(new Dimension(34, 20));
    lblMainInput.setText(lang.getString("MS017_EnterItem"));
    jPanel4.setLayout(gridLayout3);
    txtMainInput.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 18));
    txtMainInput.setPreferredSize(new Dimension(20, 27));
    txtMainInput.setText("");
    txtMainInput.addKeyListener(new FrameMain_txtMainInput_keyAdapter(this));
    jPanel2.setBackground(new Color(121, 152, 198));
    jPanel2.setPreferredSize(new Dimension(10, 10));
    jPanel4.setBackground(new Color(121, 152, 198));
    jPanel4.setPreferredSize(new Dimension(34, 27));
    pnlStatus.setBackground(new Color(121, 152, 198));
    pnlAddItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    pnlAddItem.setForeground(new Color(0, 0, 100));
    pnlItemCode.setBackground(new Color(121, 152, 198));
    pnlItemCode.setPreferredSize(new Dimension(280, 58));
    jPanel5.setBackground(new Color(121, 152, 198));
    jPanel5.setPreferredSize(new Dimension(30, 10));
    jPanel5.setLayout(borderLayout5);
    jPanel6.setBackground(new Color(121, 152, 198));
    jPanel6.setBorder(null);
    jPanel6.setPreferredSize(new Dimension(186, 57));
    jPanel6.setLayout(borderLayout6);
    btnDate.setBackground(new Color(121, 152, 198));
    btnDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    btnDate.setMinimumSize(new Dimension(47, 27));
    btnDate.setText("...");
    btnDate.addActionListener(new FrameMain_btnDate_actionAdapter(this));
    jPanel8.setBackground(new Color(121, 152, 198));
    jPanel8.setPreferredSize(new Dimension(10, 27));
    jPanel7.setBackground(new Color(121, 152, 198));
    lblCustomer.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCustomer.setPreferredSize(new Dimension(60, 18));
    lblCustomer.setText(lang.getString("Customer"));
    jPanel9.setLayout(gridLayout4);
    jPanel9.setBackground(new Color(121, 152, 198));
    jPanel9.setPreferredSize(new Dimension(34, 18));
    jPanel10.setBackground(new Color(121, 152, 198));
    jPanel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 11));
    jPanel10.setBorder(border2);
    jPanel10.setPreferredSize(new Dimension(186, 50));
    jPanel10.setLayout(flowLayout1);
    lblPhone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPhone.setPreferredSize(new Dimension(175, 13));
    lblPhone.setText(lang.getString("CustName") + ": ");
    lblName.setBackground(new Color(121, 152, 198));
    lblName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblName.setPreferredSize(new Dimension(175, 13));
    lblName.setText(lang.getString("EmpName") +": ");
    lblTimer.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTimer.setBorder(BorderFactory.createEtchedBorder());
    lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
    lblTimer.setText(lang.getString("Timer"));
    pnlMain.setForeground(Color.red);
    pnlMain.setDoubleBuffered(true);
    pnlReturnItem.setForeground(Color.red);
    jPanel12.setBackground(new Color(121, 152, 198));
    jPanel12.setPreferredSize(new Dimension(90, 57));
    jPanel12.setLayout(borderLayout7);
    jPanel15.setBackground(new Color(121, 152, 198));
    jPanel15.setPreferredSize(new Dimension(10, 27));
    jPanel13.setBackground(new Color(121, 152, 198));
    jPanel13.setPreferredSize(new Dimension(10, 27));
    cboCreditCard.setMinimumSize(new Dimension(26, 27));
    cboCreditCard.setPreferredSize(new Dimension(85, 27));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(80, 20));
    jLabel1.setToolTipText("");
    jLabel1.setText(lang.getString("CCType") +": ");
    this.getContentPane().add(pnlScreen, BorderLayout.CENTER);
    this.getContentPane().add(pnlButton, BorderLayout.EAST);

//    pnlButton.add(pnlAdmin,  "pnlAdmin");
    pnlScreen.add(pnlCenter, BorderLayout.CENTER);
    pnlScreen.add(pnlStatus, BorderLayout.SOUTH);
    pnlScreen.add(pnlInputItem, BorderLayout.NORTH);
    pnlInputItem.add(jPanel1, BorderLayout.WEST);
    pnlInputItem.add(pnlItemCode,  BorderLayout.CENTER);
    pnlItemCode.add(jPanel4, null);
    jPanel4.add(lblMainInput, null);
    pnlItemCode.add(txtMainInput, null);
    pnlItemCode.add(jPanel2, null);
    pnlInputItem.add(jPanel3,  BorderLayout.EAST);
    jPanel3.add(jPanel5,  BorderLayout.WEST);
    jPanel5.add(btnDate, BorderLayout.CENTER);
    jPanel5.add(jPanel7, BorderLayout.SOUTH);
    jPanel5.add(jPanel8, BorderLayout.NORTH);
    jPanel3.add(jPanel6,  BorderLayout.EAST);
    jPanel3.add(jPanel12,  BorderLayout.CENTER);
    jPanel12.add(jPanel13, BorderLayout.CENTER);
    jPanel13.add(cboCreditCard, null);
    jPanel12.add(jPanel15, BorderLayout.NORTH);
    jPanel15.add(jLabel1, null);

    jPanel6.add(jPanel10, BorderLayout.CENTER);
    //    jPanel6.add(jPanel9, BorderLayout.NORTH);
//    jPanel9.add(lblCustomer, null);
    jPanel10.add(lblName, null);
    jPanel10.add(lblPhone, null);
    pnlStatus.add(lblMode, null);
    pnlStatus.add(lblStore, null);
    pnlStatus.add(lblCashier, null);
    pnlStatus.add(lblRegister, null);
    pnlStatus.add(lblTimer, null);
    this.setTitle(lang.getString("TT003"));

    pnlCenter.add(pnlAddItem, "pnlAddItem");
    pnlCenter.add(pnlReturnItem, "pnlReturnItem");
    pnlCenter.add(pnlReturnNotFoundItem, "pnlReturnNotFoundItem");
    pnlCenter.add(pnlVoidTransaction, "pnlVoidTransaction");
    pnlCenter.add(pnlPaidIn, "pnlPaidIn");
    pnlCenter.add(pnlPaidOut, "pnlPaidOut");
    pnlCenter.add(pnlEvenExchange, "pnlEvenExchange");
    pnlCenter.add(pnlNetSalesByItem, "pnlNetSalesByItem");
    pnlCenter.add(pnlNetSalesByTrans,"pnlNetSalesByTrans");
    pnlCenter.add(pnlViewTrans, "pnlViewTrans");
    pnlCenter.add(pnlNewSale, "pnlNewSale");
    pnlCenter.add(pnlExchangeRate, "pnlExchangeRate");
    pnlCenter.add(pnlExRateView, "pnlExRateView");
    pnlCenter.add(pnlPayment, "pnlPayment");
    pnlCenter.add(pnlStockCount, "pnlStockCount");
    pnlCenter.add(pnlDetailTrans, "pnlDetailTrans");
    pnlCenter.add(pnlViewTransaction, "pnlViewTransaction");
    pnlCenter.add(pnlDetailTransaction, "pnlDetailTransaction");
    pnlCenter.add(pnlRegisterClose, "pnlRegisterClose");
    pnlCenter.add(pnlInventoryReceipt, "pnlInventoryReceipt");
    pnlCenter.add(pnlViewReceiptItem,"pnlViewReceiptItem");
    pnlCenter.add(pnlReceiptItem, "pnlReceiptItem");

    pnlCenter.add(pnlScheduleTimer, "pnlScheduleTimer");

    pnlCenter.add(pnlCustomerSetup, "pnlCustomerSetup");
    pnlCenter.add(pnlRunPosBatchJob, "pnlRunPosBatchJob");
    pnlCenter.add(pnlCustomerModify, "pnlCustomerModify");
    pnlCenter.add(pnlTransferItem, "pnlTransferItem");
    pnlCenter.add(pnlViewTransferTrans, "pnlViewTransferTrans");
//    pnlCenter.add(pnlDamageGoods, "pnlDamageGoods");
//    pnlCenter.add(pnlViewDmgGoos, "pnlViewDmgGoos");
//    pnlCenter.add(pnlModifyDmgGoods, "pnlModifyDmgGoods");
    pnlCenter.add(pnlLogOff, "pnlLogOff");
    pnlCenter.add(pnlMain, "pnlMain");

    ResultSet rs=null;
    rs=getComboData();
    cboCreditCard.setData1(rs);
    rs.getStatement().close();
    this.setIconImage(new ImageIcon("images\\logo1.gif").getImage());

    showScreen(Constant.SCR_MAIN);
//<<<<<<< FrameMain.java
//    showScreen(Constant.NS_INVENTORY_RECEIPT_SCR);
//    showScreen(Constant.NS_NEW_SALE_SCR);
//    showScreen(Constant.NS_REGISTER_CLOSE_SCR);

//        showScreen(Constant.NS_RECEIPT_ITEM_SCR);
    //establish connection, only this connection is used in POS
//    setTransaction();//for testing
//    getTransID();//must recalculate
//=======

    //set Timer
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        // Task here
        lblTimer.setText(ut.getSystemDateTime0());
      }
    }

    , 10, 1000);

//>>>>>>> 1.27
  }

  //for Testing only
  void setTransaction(){
    trans=new Transaction(0,0,1,1,1,"1",new Date(System.currentTimeMillis()),"1",0,"1",0,1,"1");
  }
  //get cbo data
  ResultSet getComboData(){
    ResultSet rs = null;
    try{
      String sql="select tender_id, tender_desc from btm_pos_tender_type where lower(tender_type)=lower('Credit')";
      rs = DAL.executeScrollQueries(sql);
    }catch (Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  // get store name
  String getStoreName(DataAccessLayer DAL){
    String sql = "select name from btm_pos_store where store_id ='" + DAL.getStoreID() + "'";
    String storeName="";
    ResultSet rs = null;
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        storeName = rs.getString("name");
      }
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
      return storeName;
  }

    //process input text
  void txtMainInput_keyPressed(KeyEvent e) {

        if(flagScreen==Constant.SCR_NEW_SALE){
          pnlNewSale.txtMainInput_keyPressed(e);
        }else if(flagScreen==Constant.SCR_EVEN_EXCHANGE){
          pnlEvenExchange.txtMainInput_keyPressed(e);
        }else if(flagScreen==Constant.SCR_RETURN_ITEM){
          pnlReturnItem.txtMainInput_keyPressed(e);
        }else if(flagScreen==Constant.SCR_RETURN_NOTFOUND_ITEM){
          pnlReturnNotFoundItem.txtMainInput_keyPressed(e);
        }else if(flagScreen==Constant.SCR_VOID_TRANSACTION){
          pnlVoidTransaction.txtMainInput_keyPressed(e);
        }else if(flagScreen==Constant.SCR_ADD_ITEM){
          pnlAddItem.txtMainInput_keyPressed(e);
        }else if(flagScreen==Constant.SCR_PAYMENT){
          pnlPayment.txtMainInput_keyPressed(e);
        }else if(flagScreen==Constant.SCR_VIEW_TRANSACTION){
          pnlViewTransaction.txtMainInput_keyPressed(e);
        }else if(flagScreen==Constant.SCR_VIEW_TRANS){
          pnlViewTrans.txtMainInput_keyPressed(e);
        }else if(flagScreen==Constant.SCR_REGISTER_CLOSE){
//          pnlRegisterClose.txtMainInput_keyPressed(e);
        }else if(flagScreen == Constant.SCR_DETAIL_TRANSACTION){
          pnlDetailTransaction.txtMainInput_keyPressed(e);
        }else if(flagScreen == Constant.SCR_RECEIPT_ITEM){
          pnlReceiptItem.txtMainInput_keyPressed(e);
        }else if(flagScreen == Constant.SCR_INVENTORY_RECEIPT){
          pnlInventoryReceipt.txtMainInput_keyPressed(e);
        }else if(flagScreen == Constant.SCR_VIEW_RECEIPT){
          pnlViewReceiptItem.txtMainInput_keyPressed(e);
        }else if(flagScreen == Constant.SCR_NET_SALES_BY_ITEM){
          pnlNetSalesByItem.txtMainInput_keyPressed(e);
        }else if(flagScreen == Constant.SCR_NET_SALES_BY_TRANS){
          pnlNetSalesByTrans.txtMainInput_keyPressed(e);
        }else if(flagScreen == Constant.SCR_TRANSFER_ITEM){
          pnlTransferItem.txtMainInput_keyPressed(e);
        }else if(flagScreen == Constant.SCR_VIEW_TRANSFER_TRANS){
          pnlViewTransferTrans.txtMainInput_keyPressed(e);
//        }else if(flagScreen == Constant.SCR_RETURN_DAMAGE_GOODS_ITEM){
//          pnlDamageGoods.txtMainInput_keyPressed(e);
//        }else if(flagScreen == Constant.SCR_VIEW_DAMAGE_GOODS_ITEM){
//          pnlViewDmgGoos.txtMainInput_keyPressed(e);
//        }else if(flagScreen == Constant.SCR_MODIFY_DAMAGE_GOODS_ITEM){
//          pnlModifyDmgGoods.txtMainInput_keyPressed(e);
        }else if(flagScreen == Constant.SCR_STOCK_COUNT){
          pnlStockCount.txtMainInput_keyPressed(e);
        }
  }
  //limit input charater
  void txtMainInput_keyTyped(KeyEvent e) {

    if((flagScreen==Constant.SCR_VIEW_TRANS || flagScreen==Constant.SCR_VIEW_TRANSACTION) && ( txtMainInput.getText().length() == Constant.LENGHT_MAIN_INPUT_DATE) && (e.getKeyChar() != e.VK_BACK_SPACE) && (txtMainInput.getSelectionStart() == txtMainInput.getSelectionEnd()) ){
       e.consume();
    }

    if (( txtMainInput.getText().length() == Constant.LENGHT_MAIN_INPUT) && (e.getKeyChar() != e.VK_BACK_SPACE) && (txtMainInput.getSelectionStart() == txtMainInput.getSelectionEnd()) ) {
      e.consume();
    } else if (txtMainInput.getText().length() > Constant.LENGHT_MAIN_INPUT) {
      String strTemp = txtMainInput.getText() ;
      txtMainInput.setText(strTemp.substring(0,Constant.LENGHT_MAIN_INPUT));
    }
    if(flagScreen==Constant.SCR_RETURN_ITEM || flagScreen == Constant.SCR_DETAIL_TRANSACTION){
          ut.limitLenjTextField(e,txtMainInput,Constant.LENGHT_MAIN_INPUT_TRANS);
    }
  }

  //set flag, no need to reshow screen
  public void setFlagScreen(int flagScreen){
    this.flagScreen=flagScreen;
  }
  //setLableVisible
  void setLableVisible(){
//    lblCustomer.setVisible(true);
    jPanel10.setVisible(true);
    lblName.setVisible(true);
    lblPhone.setVisible(true);
  }
  //setLableInvisible
  void setLableInVisible(){
//    lblCustomer.setVisible(false);
    jPanel10.setVisible(false);
    lblName.setVisible(false);
    lblPhone.setVisible(false);
  }
  //setButtonVisible
  void setButtonVisible(){
    btnDate.setVisible(true);
  }
  //setButtonInVisible
  void setButtonInVisible(){
    btnDate.setVisible(false);
  }
  //setComboVisible
  void setComboVisible(){
    jLabel1.setVisible(true);
    cboCreditCard.setVisible(true);
  }
  void setComboInVisible(){
    jLabel1.setVisible(false);
    cboCreditCard.setVisible(false);
  }
  //end set
  //show screen when click button
  //flagScreen: screen u want to goto
  public void showScreen(int flagScreen){

      this.flagScreen = flagScreen;
      //remove old button
      removeButton();
      if (flagScreen == Constant.SCR_MAIN) {
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlMain");
          pnlMain.showButton();
          lblMainInput.setText(lang.getString("SelectOptions"));
          setEditTextInput(false);
      }
      else if (flagScreen == Constant.SCR_NEW_SALE) {
          setLableVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlNewSale");
          pnlNewSale.goBackNewSale();
          setEditTextInput(true);
      }
      else if (flagScreen == Constant.SCR_EXCHANGE_RATE) {
          cardLayout1.show(pnlCenter, "pnlExchangeRate");
          pnlExchangeRate.showExRateButton();
      }
      else if(flagScreen == Constant.SCR_EXCHANGE_RATE_VIEW){
          cardLayout1.show(pnlCenter, "pnlExRateView");
          pnlExRateView.showExRateViewButton();
//          pnlExRateView.showDataTable();
      }
      else if (flagScreen == Constant.SCR_EVEN_EXCHANGE) {
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlEvenExchange");
          pnlEvenExchange.showButton();
          if (flagEvenExchangeConstant == Constant.EVEN_EXCHANGE_TRANS){
            lblMainInput.setText(lang.getString("MS018_EnterTran"));
          }else{
            lblMainInput.setText(lang.getString("MS019_EnterItemNew"));
          }
          setEditTextInput(true);
        }else if (flagScreen == Constant.SCR_ADD_ITEM) {
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlAddItem");
          pnlAddItem.showAddItemButton();
          lblMainInput.setText(lang.getString("AddItem"));
          setEditTextInput(false);
        }else if (flagScreen == Constant.SCR_RETURN_ITEM) {
          setLableVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlReturnItem");
          pnlReturnItem.showButton();
          lblMainInput.setText(lang.getString("MS020_EnterTranRe"));
          setEditTextInput(true);
        }else if (flagScreen == Constant.SCR_RETURN_NOTFOUND_ITEM) {
          setLableVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlReturnNotFoundItem");
          pnlReturnNotFoundItem.showButton();
          lblMainInput.setText(lang.getString("MS023_EnterItem"));
          setEditTextInput(true);

        }else if (flagScreen == Constant.SCR_VOID_TRANSACTION){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter,"pnlVoidTransaction");
          lblMainInput.setText(lang.getString("MS020_EnterTranRe"));
          setEditTextInput(true);
        }else if(flagScreen == Constant.SCR_PAID_IN){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter,"pnlPaidIn");
          lblMainInput.setText(lang.getString("PaidIn"));
          setEditTextInput(false);
        }else if(flagScreen == Constant.SCR_PAID_OUT){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter,"pnlPaidOut");
          lblMainInput.setText(lang.getString("PaidOut"));
          setEditTextInput(false);
        }else if (flagScreen == Constant.SCR_PAYMENT) {
          setLableVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlPayment");
//          pnlPayment.showButton();
          pnlPayment.showPnlCash();
          pnlPayment.flagCash=true;
          lblMainInput.setText(lang.getString("Amount"));
          setEditTextInput(true);
        }else if (flagScreen == Constant.SCR_VIEW_TRANS) {
          setLableInVisible();
          setButtonVisible();
          cardLayout1.show(pnlCenter, "pnlViewTrans");
          pnlViewTrans.showButton();
          lblMainInput.setText(lang.getString("MS021_EnterTranDate"));
          setEditTextInput(true);
        }else if (flagScreen == Constant.SCR_DETAIL_TRANS) {
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlDetailTrans");
          pnlDetailTrans.showButton();
          setEditTextInput(true);
        }else if (flagScreen == Constant.SCR_DETAIL_TRANSACTION){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlDetailTransaction");
          pnlDetailTransaction.showButton();
          setInputLabel(lang.getString("MS022_InserTran"));
          setEditTextInput(true);
        }else if (flagScreen == Constant.SCR_VIEW_TRANSACTION){
          setLableInVisible();
          setButtonVisible();
          cardLayout1.show(pnlCenter, "pnlViewTransaction");
          pnlViewTransaction.showButton();
          setEditTextInput(true);
        }else if(flagScreen==Constant.SCR_REGISTER_CLOSE){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlRegisterClose");
          removeButton();
          pnlRegisterClose.showButtonVerified();
          pnlRegisterClose.showData();
          setEditTextInput(false);
        }else if(flagScreen==Constant.SCR_RECEIPT_ITEM){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlReceiptItem");
          pnlReceiptItem.showButton();
          lblMainInput.setText(lang.getString("MS023_EnterItem")+ ": ");//TrungNT
          setEditTextInput(true);
        }else if(flagScreen==Constant.SCR_INVENTORY_RECEIPT){
          setLableInVisible();
          setButtonVisible();
          cardLayout1.show(pnlCenter, "pnlInventoryReceipt");
          pnlInventoryReceipt.showButton();
          setEditTextInput(true);
        }else if(flagScreen==Constant.SCR_VIEW_RECEIPT){
          setLableInVisible();
          setButtonVisible();
          cardLayout1.show(pnlCenter, "pnlViewReceiptItem");
          pnlViewReceiptItem.showButton();
          setEditTextInput(true);
        }else if(flagScreen == Constant.SCR_NET_SALES_BY_ITEM){
          setLableInVisible();
          setButtonVisible();
          cardLayout1.show(pnlCenter, "pnlNetSalesByItem");
          pnlNetSalesByItem.showButton();
          lblMainInput.setText(lang.getString("MS021_EnterTranDate"));
          setEditTextInput(true);
        }else if(flagScreen == Constant.SCR_NET_SALES_BY_TRANS){
          setLableInVisible();
          setButtonVisible();
          cardLayout1.show(pnlCenter, "pnlNetSalesByTrans");
          pnlNetSalesByTrans.showButton();
          lblMainInput.setText(lang.getString("MS021_EnterTranDate"));
          setEditTextInput(true);

        }else if(flagScreen == Constant.SCR_SCHEDULE_TIMER){
          setLableInVisible();
          setButtonInVisible();
                  cardLayout1.show(pnlCenter, "pnlScheduleTimer");
                  pnlScheduleTimer.showButton();
                  lblMainInput.setText(lang.getString("MS024_EnterHours"));
                  setEditTextInput(true);

        }else if(flagScreen == Constant.SCR_CUSTOMER_SETUP){
          pnlCustomerSetup.getCustomerId();
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlCustomerSetup");
          pnlCustomerSetup.showButton();
          lblMainInput.setText("");
          setEditTextInput(false);
        }else if(flagScreen == Constant.SCR_RUN_POS_BATCH_JOB){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlRunPosBatchJob");
          pnlRunPosBatchJob.showButton();
          pnlRunPosBatchJob.initScreen();
          setEditTextInput(false);
        }else if(flagScreen == Constant.SCR_CUSTOMER_MODIFY){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlCustomerModify");
          pnlCustomerModify.showButton();
          lblMainInput.setText("");
          setEditTextInput(false);

        }else if(flagScreen == Constant.SCR_STOCK_COUNT){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter, "pnlStockCount");
          pnlStockCount.showButton();
          setEditTextInput(true);

        }else if(flagScreen == Constant.SCR_TRANSFER_ITEM){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter,"pnlTransferItem");
          pnlTransferItem.showButton();
          lblMainInput.setText(lang.getString("MS026_EnterTranfer") + ": ");
          setEditTextInput(true);
          pnlTransferItem.setText();
          pnlTransferItem.flagButton = Constant.TI_TRANSFER_ITEM;

//        }else if(flagScreen == Constant.SCR_RETURN_DAMAGE_GOODS_ITEM){
//          setLableInVisible();
//          setButtonInVisible();
//          cardLayout1.show(pnlCenter,"pnlDamageGoods");
//          pnlDamageGoods.showButton();
//          lblMainInput.setText("Insert or Scan Item to return, Press 's' to search:");
//          setEditTextInput(true);
//          pnlDamageGoods.setText();
//          pnlDamageGoods.flagButton = Constant.RETURN_DAMAGE_GOODS_ITEM;
//        }else if(flagScreen == Constant.SCR_VIEW_DAMAGE_GOODS_ITEM){
//          setLableInVisible();
//          setButtonVisible();
//          cardLayout1.show(pnlCenter,"pnlViewDmgGoos");
//          pnlViewDmgGoos.showButton();
//          lblMainInput.setText("Insert date(DD/MM/YYYY) to view Damage Goods");
//          setEditTextInput(true);
//        }else if(flagScreen == Constant.SCR_MODIFY_DAMAGE_GOODS_ITEM){
//          setLableInVisible();
//          setButtonInVisible();
//          cardLayout1.show(pnlCenter, "pnlModifyDmgGoods");
//          pnlModifyDmgGoods.showButton();
//          lblMainInput.setText("Insert new quantity:");
//          setEditTextInput(true);
        }else if(flagScreen == Constant.SCR_VIEW_TRANSFER_TRANS){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter,"pnlViewTransferTrans");
          pnlViewTransferTrans.showButton();
          lblMainInput.setText(lang.getString("MS026_EnterTranfer"));
          setEditTextInput(true);
//<<<<<<< FrameMain.java
//        }
//=======
        }else if( flagScreen == Constant.SCR_LOG_OFF){
          setLableInVisible();
          setButtonInVisible();
          cardLayout1.show(pnlCenter,"pnlLogOff");
          lblMainInput.setText("");
//          setEditTextInput(false);
          txtMainInput.setVisible(false);
        }

//>>>>>>> 1.42
    }

  //set enable edit input text
   public void setEditTextInput(boolean isEdit){
      txtMainInput.setEditable(isEdit);
      txtMainInput.setText("");
      txtMainInput.requestFocus(true);
  }
  // go to Detail transaction to view data and choose item for even exchange
  public void goToDetailTrans(String trans_id){
    this.setInputLabel("");
    setEditTextInput(false);
    pnlDetailTrans.showData(trans_id);
  }
  //go to Detail Transaction to view data
  public void goToDetailTransaction(String trans_id){
    pnlDetailTransaction.showData(trans_id);
  }

  // go back to Even Exchange to exchange data
  public void goBackEvenExchange(String item_id, String trans_id){
    pnlEvenExchange.addNewRow(item_id, trans_id);
  }
  //show button from other Panel
  public void showButton(JButton button){
    this.pnlButton.add(button, null);
  }
  //show button on Panel contains Money Symbols
//  public void showPnlMoneySymbol(){
//    this.pnlButton.add(pnlMoneySymbol);
//  }
  //show button on Panel contains Money Symbols
//  public void showMoneySymbol(JButton button){
//    this.pnlMoneySymbol.add(button, null);
//  }
  //remove button
  void removeButton(){
    this.pnlButton.removeAll();
    this.pnlButton.updateUI();
  }
  //get textinput from other Panel
  public String getInputText(){
      return this.txtMainInput.getText().trim();
  }
  //set textinput from other Panel
  public void setInputText(String s){
      this.txtMainInput.setText(s);
      this.txtMainInput.requestFocus(true);
  }

  public void setInputLabel(String s){
      this.lblMainInput.setText(s);
  }
  public Transaction getTransaction(){
      return trans;
  }
  public DataAccessLayer getDAL(){
      return DAL;
  }
  public void afterOpenPOS() {
      int numberOfMillisecondsInTheFuture = 1000; // 1 sec
      Date timeToRun = new Date(System.currentTimeMillis()+numberOfMillisecondsInTheFuture);
      Timer timer = new Timer();
     timer.schedule(new TimerTask() {
             public void run() {
                try {
                 Runtime.getRuntime().exec("C:\\WINDOWS\\notepad.exe");
             }
             catch (Exception ex) {
            ex.getMessage();
          }
             }}, timeToRun);
          }

  void btnDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;;
     posXFrame = this.getX();
     posYFrame = this.getY();
     posX = this.btnDate.getX() + posXFrame+90;
     posY = this.btnDate.getY() + posYFrame+90;
     TimeDlg endDate = new TimeDlg(null);
     endDate.setTitle(lang.getString("ChooseDate"));
     endDate.setResizable(false);
     endDate.setLocation(posX, posY);
     endDate.setVisible(true);

     if (endDate.isOKPressed()) {
       java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
           "dd/MM/yyyy");
       String date = fd.format(endDate.getDate());
       this.txtMainInput.setText(date);
       if (flagScreen == Constant.SCR_VIEW_TRANSACTION){
         pnlViewTransaction.showData(txtMainInput.getText());
       }else if(flagScreen == Constant.SCR_VIEW_TRANS){
         pnlViewTrans.showData(txtMainInput.getText());
       }else if(flagScreen == Constant.SCR_VIEW_TRANSFER_TRANS){
         pnlViewTransferTrans.showSearch();
       }else if(flagScreen == Constant.SCR_INVENTORY_RECEIPT){
         pnlInventoryReceipt.showData(txtMainInput.getText());
       }else if(flagScreen == Constant.SCR_NET_SALES_BY_ITEM){
         pnlNetSalesByItem.showData();
       }else if(flagScreen == Constant.SCR_NET_SALES_BY_TRANS){
         pnlNetSalesByTrans.showData();
//       }else if(flagScreen == Constant.SCR_VIEW_DAMAGE_GOODS_ITEM){
//         pnlViewDmgGoos.showData(txtMainInput.getText());
       }else if(flagScreen == Constant.SCR_VIEW_RECEIPT){
         pnlViewReceiptItem.showData(txtMainInput.getText());
       }
       this.txtMainInput.requestFocus(true);
     }
  }

  void this_keyPressed(KeyEvent e) {

  }




//  void this_windowOpened(WindowEvent e) {
//    afterOpenPOS();
//    int delay = 5000;   // delay for 5 sec.
//    int period = 1000;  // repeat every sec.
//    Timer timer = new Timer();
//
//    timer.scheduleAtFixedRate(new TimerTask() {
//      public void run() {
//        try {
//          Runtime.getRuntime().exec("C:\\WINDOWS\\explorer.exe");
//        } catch (Exception ex) {
//          ex.getMessage();
//        }
//        System.out.println("ok");
//                }
//            }, delay, period);
//
//  }

}


class FrameMain_txtMainInput_keyAdapter extends java.awt.event.KeyAdapter {
  FrameMain adaptee;

  FrameMain_txtMainInput_keyAdapter(FrameMain adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtMainInput_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtMainInput_keyTyped(e);
  }
}


class FrameMain_btnDate_actionAdapter implements java.awt.event.ActionListener {
  FrameMain adaptee;

  FrameMain_btnDate_actionAdapter(FrameMain adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class FrameMain_this_keyAdapter extends java.awt.event.KeyAdapter {
  FrameMain adaptee;

  FrameMain_this_keyAdapter(FrameMain adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}





