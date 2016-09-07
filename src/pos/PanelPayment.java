package pos;

import java.awt.geom.*;
import java.awt.print.PrinterJob;
import java.awt.print.*;

import btm.swing.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import btm.attr.*;
import javax.swing.table.*;

import common.*;
import java.sql.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.text.*;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.PrintPDF;
import com.lowagie.text.FontFactory;
import java.awt.BorderLayout;
import btm.business.GiftPromoType;


import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: (Error in Grid) </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author HIEUPHAM
 * @version 1.0
 */

public class PanelPayment
    extends JPanel {
  //====================================
  private String AmountTenderedNum = "0";
  private String ChangedDueNum = "0";
//================= Flag Button for Payment
  private static final int PM_CASH_BTN = 216;
  private static final int PM_CREDIT_BTN = 217;
  private static final int PM_COUPON_BTN = 218;
  private static final int PM_FOREIGN_CASH_BTN = 219;
  private static final int PM_CREDIT_NUMBER_BTN = 2191;
  private static final int PM_CREDIT_EXP_DATE_BTN = 2192;

  //Column Order of Payment Table
  private static final int PM_COL_TYPE = 0; //
  private static final int PM_COL_DETAIL = 1; //
  private static final int PM_COL_EXPIRE_DATE = 2; //
  private static final int PM_COL_APPROVAL = 3;
  private static final int PM_COL_AMOUNT = 4;

  //const in BTM_POS_TENDER_TYPE table
  private static final int CASH_TENDER = 1;
  private static final int COUPON_TENDER = 2;
  private static final int FOREIGN_CASH_TENDER = 3;
  private static final int CREDIT_VISA_TENDER = 4;
  private static final int CREDIT_MASTER_TENDER = 5;
  private static final int CREDIT_DISCOVER_TENDER = 6;
  private static final int CREDIT_AMERICAN_EXPRESS_TENDER = 7;

  //const in BTM_POS_TENDER_TYPE table
  private static final String CREDIT_PAYMENT = "Credit";
  private static final String COUPON_PAYMENT = "Coupon";
  private static final String CASH_PAYMENT = "Cash";
  private static final String FOREIGN_CASH_PAYMENT = "Foreign cash";
  //Gift Promo
  public boolean winnerTrans=false;//determine if this trans is win a gift
  public String giftName="";//name of gift
  String curDir = System.getProperty("user.dir");

  //List Unilever item
  Vector vUnileverItem = new Vector(); // list of gift
//    private static final String CURENNCY_DEFAULT = "";

  FrameMain frmMain;
  DataAccessLayer DAL;
  int flagButton = PM_CASH_BTN; //dertermine button on screen.
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  boolean flagPayment = false; //true when the custormer finish payment
  float paidIn = 0;
  //Khoi tao bien <vSaleItem> de chua cac mat hang da mua cua nguoi dung
  Vector vSaleItem;
  //Khoi tao bien <trans> de chua thong tin chung cua Trans_ID
  Transaction trans;
  //
  Vector vGiftPromoType;
  //Khoi tao bien flagLoaiMuaBan xac dinh : nguoi dung mua bang(Cash or Credit card)
  int tenderType = CASH_TENDER;
  String currencyID = "";
  String currencyID1 = "";
  String mainInput = "";
  String mainForeignInput = "";
  String creditCardNum = ""; //char
  String creditCardExpire = ""; //date
  String trans_datetime = "";

  double addCash=0; //add cash
  boolean  flagCash = false;

  //Khoi tao bien <model> chua du lieu cho bang table
 /* String[] columnNames = new String[] {
      "Payment Type",
      "Payment Detail",
      "Expiration Date",
      "Approval",
      "Amount Tendered"};*/

   String[] columnNames = new String[] {
       lang.getString("PaymentType"),
       lang.getString("PaymentDetail"),
       lang.getString("ExpirationDate"),
       lang.getString("Approval"),
       lang.getString("AmountTendered")};

  Object[][] rowdata = new Object[0][5];
  SortableTableModel model = new SortableTableModel() {
    public boolean isCellEditable(int rowIndex, int mColIndex) {
      return false;
    }
  };

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel pnlNorth = new JPanel();
  JPanel pnlCenter = new JPanel();
  JPanel pnlSouth = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout2 = new GridLayout();
  //Khoi tao table lay du lieu tu bien <model>
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JLabel lblRetail = new JLabel();
  JLabel lblMarkDis = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel lblTotal = new JLabel();
  JPanel jPanel9 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JPanel jPanel12 = new JPanel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  FlowLayout flowLayout4 = new FlowLayout();
  FlowLayout flowLayout5 = new FlowLayout();
  FlowLayout flowLayout6 = new FlowLayout();
  FlowLayout flowLayout7 = new FlowLayout();
  FlowLayout flowLayout8 = new FlowLayout();
  FlowLayout flowLayout9 = new FlowLayout();
  FlowLayout flowLayout10 = new FlowLayout();
  FlowLayout flowLayout11 = new FlowLayout();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JLabel lblAmountTendered = new JLabel();
  JLabel lblAmountDue = new JLabel();
  FlowLayout flowLayout3 = new FlowLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  BJTable tblPayment = new BJTable(model, true);
  BorderLayout borderLayout2 = new BorderLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;
  FlowLayout flowLayout2 = new FlowLayout();
  BJButton btnCreditCard = new BJButton();
  BJButton btnCash = new BJButton();
  BJButton btnPostTXN = new BJButton();
  BJButton btnDelPayment = new BJButton();
//  BJButton btnPrintReceipt = new BJButton();
  BJButton btnBack = new BJButton();
  BJButton btnCoupon = new BJButton();
  BJButton btnForeignCash = new BJButton();
//  BJButton jButton1 = new BJButton();
//  BJButton jButton2 = new BJButton();
  BJButton btnFixCharge = new BJButton();
  BJButton btnEnter = new BJButton();
  BJButton btn200 = new BJButton();
  BJButton btn500 = new BJButton();
  BJButton btn1000 = new BJButton();
  BJButton btn2000 = new BJButton();
  BJButton btn5000 = new BJButton();
  BJButton btn10000 = new BJButton();
  BJButton btn20000 = new BJButton();
  BJButton btn50000 = new BJButton();
  BJButton btn100000 = new BJButton();
  BJButton btn500000 = new BJButton();
  public PanelPayment(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      currencyID = DAL.getCurrencyID();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {

    registerKeyboardActions();
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    this.setLayout(borderLayout1);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    pnlNorth.setLayout(gridLayout2);
    pnlCenter.setLayout(borderLayout2);
    lblRetail.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRetail.setPreferredSize(new Dimension(100, 16));
    lblRetail.setHorizontalAlignment(SwingConstants.RIGHT);
    lblRetail.setText("jLabel3");
    lblMarkDis.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblMarkDis.setPreferredSize(new Dimension(100, 16));
    lblMarkDis.setHorizontalAlignment(SwingConstants.RIGHT);
    lblMarkDis.setText("jLabel6");
    lblTotal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTotal.setPreferredSize(new Dimension(100, 16));
    lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
    lblTotal.setText("jLabel8");
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setText(lang.getString("Retail") );
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel9.setText(lang.getString("MarkDown") + " / " + lang.getString("Discount"));
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel11.setText(lang.getString("Total"));
    jPanel10.setLayout(flowLayout4);
    jPanel12.setLayout(flowLayout5);
    jPanel11.setLayout(flowLayout6);
    jPanel9.setLayout(flowLayout7);
    jPanel7.setLayout(flowLayout8);
    jPanel8.setLayout(flowLayout9);
    jPanel6.setLayout(flowLayout10);
    jPanel5.setLayout(flowLayout11);
    flowLayout11.setAlignment(FlowLayout.RIGHT);
    flowLayout10.setAlignment(FlowLayout.RIGHT);
    flowLayout9.setAlignment(FlowLayout.RIGHT);
    flowLayout8.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    flowLayout5.setAlignment(FlowLayout.LEFT);
    flowLayout6.setAlignment(FlowLayout.LEFT);
    flowLayout7.setAlignment(FlowLayout.LEFT);
    this.setPreferredSize(new Dimension(114, 131));
    pnlSouth.setPreferredSize(new Dimension(114, 60));
    pnlSouth.setRequestFocusEnabled(true);
    lblAmountTendered.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAmountTendered.setDoubleBuffered(false);
    lblAmountTendered.setText(lang.getString("AmountTendered1") + " : 0.00");
    lblAmountDue.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    lblAmountDue.setDoubleBuffered(false);
    lblAmountDue.setPreferredSize(new Dimension(200, 15));
    lblAmountDue.setHorizontalAlignment(SwingConstants.RIGHT);
    lblAmountDue.setText(lang.getString("AmountDue") + " : 0.00");
    jPanel1.setLayout(flowLayout2);
    jPanel2.setLayout(flowLayout3);
    flowLayout3.setAlignment(FlowLayout.RIGHT);
    jPanel1.setBackground(Color.orange);
    jPanel1.setPreferredSize(new Dimension(127, 30));
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel7.setText("");
    jPanel10.setBackground(Color.white);
    jPanel12.setBackground(Color.white);
    jPanel11.setBackground(Color.white);
    jPanel9.setBackground(Color.white);
    jPanel3.setBackground(Color.white);
    jPanel7.setBackground(Color.white);
    jPanel8.setBackground(Color.white);
    jPanel6.setBackground(Color.white);
    jPanel5.setBackground(Color.white);
    jScrollPane1.getViewport().setBackground(Color.white);
    tblPayment.setBackground(new Color(204, 204, 254));
    tblPayment.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 14));
    tblPayment.setRowHeight(30);
    tblPayment.addKeyListener(new PanelPayment_tblPayment_keyAdapter(this));
    pnlNorth.setBorder(titledBorder3);
    flowLayout2.setAlignment(FlowLayout.RIGHT);

    btnCash.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCash.setToolTipText(lang.getString("Cash") + " (F1)");
    btnCash.setText(lang.getString("Cash") + " (F1)");
    btnCash.addActionListener(new PanelPayment_btnCash_actionAdapter(this));
    btnForeignCash.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnForeignCash.setToolTipText(lang.getString("ForeignCash") + " (F2)");
    btnForeignCash.setText("<html><center><b>"+lang.getString("ForeignCash")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                           "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
                           "sp;F2</html>");
    btnForeignCash.addActionListener(new
                                     PanelPayment_btnForeignCash_actionAdapter(this));
    btnCoupon.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCoupon.setToolTipText(lang.getString("Coupon") + " (F3)");
    btnCoupon.setText(lang.getString("Coupon") + " (F3)");
    btnCoupon.addActionListener(new PanelPayment_btnCoupon_actionAdapter(this));
    btnCreditCard.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCreditCard.setToolTipText(lang.getString("CreditCard")+ " (F4)");
    btnCreditCard.setText(lang.getString("CreditCard")+ " (F4)");
    btnCreditCard.addActionListener(new
                                    PanelPayment_btnCreditCard_actionAdapter(this));
    btnPostTXN.setText("<html><center><b>"+lang.getString("PostTXN")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                       "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
                       "5</html>");
    btnPostTXN.setEnabled(false);
    btnPostTXN.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPostTXN.setToolTipText(lang.getString("PostTXN") + "(F5)");
    btnPostTXN.addActionListener(new PanelPayment_btnPostTXN_actionAdapter(this));
    btnDelPayment.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDelPayment.setToolTipText(lang.getString("DeletePayment")+" (F6)");
    btnDelPayment.setText(lang.getString("DeletePayment")+" (F6)");
    btnDelPayment.addActionListener(new
                                    PanelPayment_btnDelPayment_actionAdapter(this));
//    btnPrintReceipt.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//    btnPrintReceipt.setToolTipText(lang.getString("PrintReceipt") + " (F7)");
//    btnPrintReceipt.setText(lang.getString("PrintReceipt") + " (F7)");
//    btnPrintReceipt.addActionListener(new
//        PanelPayment_btnPrintReceipt_actionAdapter(this));
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnBack.setToolTipText(lang.getString("Back") + " (F12)");
    btnBack.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnBack.addActionListener(new PanelPayment_btnBack_actionAdapter(this));
//    jButton1.setText("<html><center><br><b>" + DAL.getFirstAutoNum() + "</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
//                     "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F10</ht" +
//                     "ml>");

//    jButton1.addActionListener(new PanelPayment_jButton1_actionAdapter(this));
//    jButton2.setText("<html><center><br><b>" + DAL.getSectAutoNum() + "</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
//                     "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F11</ht" +
//                     "ml>");
//    jButton2.addActionListener(new PanelPayment_jButton2_actionAdapter(this));
    btnFixCharge.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnFixCharge.setText("<html><b><p>"+lang.getString("FixGrandTotal")+"</p></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "<font color='#006699'><b><i>F1</i></b></font></html>");
    btnFixCharge.addActionListener(new PanelPayment_btnFixCharge_actionAdapter(this));
    //    btnFixCharge.setPreferredSize(new Dimension(63,39));
    btn200.setText("<html><div align='center'><b>200</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F2</i></b></font></div></html>");
    btn200.addActionListener(new PanelPayment_btn200_actionAdapter(this));
    //    btn200.setPreferredSize(new Dimension(63,39));
    btn500.setText("<html><div align='center'><b>500</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F3</i></b></font></div></html>");
    btn500.addActionListener(new PanelPayment_btn500_actionAdapter(this));
    //    btn500.setPreferredSize(new Dimension(63,39));
    btn1000.setText("<html><div align='center'><b>1,000</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F4</i></b></font></div></html>");
    btn1000.addActionListener(new PanelPayment_btn1000_actionAdapter(this));
    //    btn1000.setPreferredSize(new Dimension(63,39));
    btn2000.setText("<html><div align='center'><b>2,000</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F5</i></b></font></div></html>");
//    btn2000.setPreferredSize(new Dimension(63,39));
    btn2000.addActionListener(new PanelPayment_btn2000_actionAdapter(this));
    btn5000.setText("<html><div align='center'><b>5,000</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F6</i></b></font></div></html>");
//    btn5000.setPreferredSize(new Dimension(63,39));
    btn5000.addActionListener(new PanelPayment_btn5000_actionAdapter(this));
    btn10000.setText("<html><div align='center'><b>10,000</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F7</i></b></font></div></html>");
//    btn10000.setPreferredSize(new Dimension(63,39));
    btn10000.addActionListener(new PanelPayment_btn10000_actionAdapter(this));
    btn20000.setText("<html><div align='center'><b>20,000</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F8</i></b></font></div></html>");
//    btn20000.setPreferredSize(new Dimension(63,39));
    btn20000.addActionListener(new PanelPayment_btn20000_actionAdapter(this));
    btn50000.setText("<html><div align='center'><b>50,000</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F9</i></b></font></div></html>");
//    btn50000.setPreferredSize(new Dimension(63,39));
    btn50000.addActionListener(new PanelPayment_btn50000_actionAdapter(this));
    btn100000.setText("<html><div align='center'><b>100,000</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F10</i></b></font></div></html>");
//    btn100000.setPreferredSize(new Dimension(63,39));
    btn100000.addActionListener(new PanelPayment_btn100000_actionAdapter(this));
    btn500000.setText("<html><div align='center'><b>500,000</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#006699'><b><i>F11</i></b></font></div></html>");
//    btn500000.setPreferredSize(new Dimension(63,39));
    btn500000.addActionListener(new PanelPayment_btn500000_actionAdapter(this));
    btnEnter.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnEnter.setText("<html><center><b>"+lang.getString("Skip")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnEnter.addActionListener(new PanelPayment_btnEnter_actionAdapter(this));
    //    btnEnter.setPreferredSize(new Dimension(63,39));

//    jButton2.addActionListener(new PanelPayment_jButton2_actionAdapter(this));

    this.add(pnlNorth, BorderLayout.NORTH);
    pnlNorth.add(jPanel4, null);
    jPanel4.add(jPanel10, null);
    jPanel10.add(jLabel5, null);
    jPanel4.add(jPanel12, null);
    jPanel12.add(jLabel9, null);
    jPanel4.add(jPanel11, null);
    jPanel11.add(jLabel10, null);
    jPanel4.add(jPanel9, null);
    jPanel9.add(jLabel11, null);
    pnlNorth.add(jPanel3, null);
    jPanel3.add(jPanel7, null);
    jPanel7.add(lblRetail, null);
    jPanel3.add(jPanel8, null);
    jPanel8.add(lblMarkDis, null);
    jPanel3.add(jPanel6, null);
    jPanel6.add(jLabel7, null);
    jPanel3.add(jPanel5, null);
    jPanel5.add(lblTotal, null);
    this.add(pnlCenter, BorderLayout.CENTER);
    pnlCenter.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tblPayment, null);
    this.add(pnlSouth, BorderLayout.SOUTH);
    pnlSouth.add(jPanel1, null);
    jPanel1.add(lblAmountTendered, null);
    pnlSouth.add(jPanel2, null);
    jPanel2.add(lblAmountDue, null);

    jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.PAGE_AXIS));
    jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.PAGE_AXIS));
    pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.PAGE_AXIS));

    //Khoi tao table
    model.setDataVector(rowdata, columnNames);
    tblPayment.getTableHeader().setReorderingAllowed(false);
    tblPayment.getTableHeader().setResizingAllowed(false);
    tblPayment.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    formatAmountDue();

//    System.out.println("//======================== Gift Promo Code ===================//");
    vUnileverItem=getUnileverItem("100059");
//    System.out.println("//======================== Unilever Item: show revenue  ===================//");
    vGiftPromoType=getGiftPromoData();

  }

  void formatAmountDue() {
//  String [] str = lblAmountDue.getText().split(":");
//  String s = ut.formatNumber(str[1]);
//  lblAmountDue.setText("Changed Due : " + s);
  }

  /*
   * HAM GAN GIA TRI CHO BIEN <vStaleItem>
   * @author 			Hieu.Pham
   *@param  :Vector vSaleItem => chua mang doi tuong ve thong tin cac mon hang
   *@return :void
   */
  void setSaleItem(Vector vSaleItem) {
    this.vSaleItem = vSaleItem;
    sumValueItems();
    String strTotal = lblTotal.getText();
    double dTotalTemp = ut.convertToDouble(strTotal);
    if (dTotalTemp < 0) {
      cashPayment(0);
      btnDelPayment.setVisible(false);
    }
    else {
      btnDelPayment.setVisible(true);
    }
  }

  /** getSalesItem()
   * @author Nghi Doan
   * @return a vector
   */
  Vector getSalesItem() {
    return vSaleItem;
  }

  /*
   * HAM GAN GIA TRI CHO BIEN <vStaleItem>
   * @author 			Hieu.Pham
   *@param  :Vector vTrans => chua thong tin chung cua Tranactions
   *@return :void
   */
  void setTransaction(Transaction trans) {
    this.trans = trans;
  }

  /*
   * Calculate total of payment
   * @author 			Hieu.Pham
   *@param  :
   *@return :
   */
  void sumValueItems() {
    float fRetail, fMarDis, fTotal; //, fAmountTendered, fAmountDue;
    fRetail = 0;
    fMarDis = 0;
    fTotal = 0;
    //fAmountTendered = 0;
    //fAmountDue = 0;

    Item item;
    for (int i = 0; i < vSaleItem.size(); i++) {
      item = (Item) vSaleItem.elementAt(i);
//      if (frmMain.pnlNewSale.flagReturn == Constant.RETURN_ITEM) {
      fRetail += item.getQuantity() * item.getunitPrice();
//      }
//      else {
//        fRetail += item.getQuantity() * item.getunitPrice();
//      }
      fMarDis += item.getMarkdownAmount();
      fTotal += item.getAmountDue();
    }
    lblRetail.setText(ut.formatNumber("" + fRetail));
    lblMarkDis.setText(ut.formatNumber("" + fMarDis));
    lblTotal.setText(ut.formatNumber("" + fTotal));
    lblAmountDue.setText(lang.getString("ChangedDue") + ":" + ut.formatNumber("" + fTotal));
  }

  /*Calculate when payment=COUPON
   *@author 			Hieu.Pham
   *@param  : inputCash input
   *@return : calculate and update label
   *       false :  payment is not finished!
   *       true: payment is finished
   */
    boolean creditPayment(double inputCash) {
    double changeDue = 0; //So tien phai tra lai
    double fTotal = 0; //So tien phai tra khi khach mua hang
    double prevousInput = 0; //
    boolean isExistRow = false;
    int updateRow = 0;
    double oldCash = 0;

    //get total
    fTotal = ut.convertToDouble(lblTotal.getText());
    for (int i = 0; i < tblPayment.getRowCount(); i++) {
      prevousInput +=
          ut.convertToDouble(tblPayment.getValueAt(i, PM_COL_AMOUNT).toString());
      if (tblPayment.getValueAt(i,
                                PM_COL_TYPE).toString().equalsIgnoreCase(
          CREDIT_PAYMENT)) {
        isExistRow = true;
        updateRow = i;
        oldCash = ut.convertToDouble(tblPayment.getValueAt(i, PM_COL_AMOUNT).
                                     toString());
      }
    }
//    System.out.println("amount " + prevousInput);
    //show amount and Change due label
    inputCash = prevousInput + inputCash;
    changeDue = ut.convertToDouble(lblTotal.getText()) - inputCash;
    if (changeDue < 0) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS092_AmountTender"));
      return false;
    }
    //update payment table
    if (isExistRow) {
      double cash = inputCash + oldCash;
      tblPayment.setValueAt(ut.formatNumber("" + cash), updateRow,
                            PM_COL_AMOUNT);
    }
    else {
      model.addRow(new Object[] {CREDIT_PAYMENT,
                   frmMain.cboCreditCard.getSelectedItem()
                   , "", lang.getString("PostPending"),
                   ut.formatNumber("" + (inputCash - prevousInput))});
    }

//      changeDue=ut.getRound(changeDue,Constant.PM_DECIMAL_LENGTH);
    AmountTenderedNum = ut.formatNumber("" + inputCash);
    ChangedDueNum = ut.formatNumber("" + changeDue);
    lblAmountTendered.setText(lang.getString("AmountTendered")+":" +
                              ut.formatNumber("" + inputCash));
    if (inputCash >= fTotal) {
      lblAmountDue.setText(lang.getString("ChangedDue") + ":"  + ut.formatNumber("" + changeDue));
      //update payment table
      model.addRow(new Object[] {CASH_PAYMENT, "", "", "",
                   ut.formatNumber("" + changeDue)});
      flagPayment = true;
    }
    else {
      lblAmountDue.setText(lang.getString("ChangedDue") + ":" + ut.formatNumber("" + changeDue));
      flagPayment = false;
    }
    tenderType = Integer.parseInt(frmMain.cboCreditCard.getSelectedData());
    frmMain.setComboInVisible();
    return true;
  }

  /*Calculate when payment=CASH
   *@author 			Hieu.Pham
   *@param  : inputCash input
   *@return : calculate and update label
   *       true: payment is finished
   */
  void cashPayment(double inputCash) {
    double changeDue = 0; //So tien phai tra lai
    double fTotal = 0; //So tien phai tra khi khach mua hang
    double prevousInput = 0; //
    boolean isExistRow = false;
    int updateRow = 0;
    double oldCash = 0;
    //get total
    fTotal = ut.convertToDouble(lblTotal.getText());
    for (int i = 0; i < tblPayment.getRowCount(); i++) {
      prevousInput +=
          ut.convertToDouble(tblPayment.getValueAt(i, PM_COL_AMOUNT).toString());
      if (tblPayment.getValueAt(i,
                                PM_COL_TYPE).toString().equalsIgnoreCase(
          CASH_PAYMENT)) {
        isExistRow = true;
        updateRow = i;
        oldCash = ut.convertToDouble(tblPayment.getValueAt(i, PM_COL_AMOUNT).
                                     toString());
      }
    }
//    System.out.println("amount " + prevousInput);

    //update payment table
    if (isExistRow) {
      double cash = inputCash + oldCash;
      tblPayment.setValueAt(ut.formatNumber("" + cash), updateRow,
                            PM_COL_AMOUNT);
    }
    else {
      model.addRow(new Object[] {CASH_PAYMENT, "", "", "",
                   ut.formatNumber("" + inputCash)});
    }
    isExistRow = false;

    //show amount and Change due label
    inputCash = prevousInput + inputCash;
    if (frmMain.pnlNewSale.flagReturn == Constant.RETURN_ITEM) {
      double total = ut.convertToDouble(lblTotal.getText());
      if (total < 0) {
        changeDue = total + inputCash;
      }
      else {
        changeDue = total - inputCash;
      }
    }
    else {
      changeDue = ut.convertToDouble(lblTotal.getText()) - inputCash;
    }
    changeDue = ut.getRound(changeDue, Constant.PM_DECIMAL_LENGTH);

    AmountTenderedNum = ut.formatNumber("" + inputCash);
    ChangedDueNum = ut.formatNumber("" + changeDue);

    lblAmountTendered.setText(lang.getString("AmountTendered")+": " +
                              ut.formatNumber("" + inputCash));
    if (inputCash >= fTotal) {
      lblAmountDue.setText(lang.getString("ChangedDue") + ": " + ut.formatNumber("" + changeDue));
      //update payment table
      model.addRow(new Object[] {CASH_PAYMENT, "", "", "",
                   ut.formatNumber("" + changeDue)});
      flagPayment = true;
    }
    else {
      lblAmountDue.setText(lang.getString("ChangedDue") +": " + ut.formatNumber("" + changeDue));
      flagPayment = false;
    }
  }

  /*Calculate when payment=COUPON
   *@author 			Hieu.Pham
   *@param  : inputCash input
   *@return : calculate and update label
   *       false :  payment is not finished!
   *       true: payment is finished
   */
  void couponPayment(double inputCash) {
    double changeDue = 0; //So tien phai tra lai
    double fTotal = 0; //So tien phai tra khi khach mua hang
    double prevousInput = 0; //
    boolean isExistRow = false;
    int updateRow = 0;
    double oldCash = 0;

    //get total
    fTotal = ut.convertToDouble(lblTotal.getText());
    for (int i = 0; i < tblPayment.getRowCount(); i++) {
      prevousInput +=
          ut.convertToDouble(tblPayment.getValueAt(i, PM_COL_AMOUNT).toString());
      if (tblPayment.getValueAt(i,
                                PM_COL_TYPE).toString().equalsIgnoreCase(
          COUPON_PAYMENT)) {
        isExistRow = true;
        updateRow = i;
        oldCash = ut.convertToDouble(tblPayment.getValueAt(i, PM_COL_AMOUNT).
                                     toString());
      }

    }
//    System.out.println("amount " + prevousInput);
    //Nghi: Coupon Payment need to allow enter value greater then Total Due Amount
//      if( inputCash>(Float.parseFloat(lblTotal.getText())-prevousInput) ){
//         JOptionPane.showMessageDialog(this, "Coupon can not be greater than the amount due. ","Message", 1);
//         return ;
//      }
    //End Nghi

    //update payment table
    if (isExistRow) {
      double cash = inputCash + oldCash;
      tblPayment.setValueAt(ut.formatNumber("" + cash), updateRow,
                            PM_COL_AMOUNT);
    }
    else {
      model.addRow(new Object[] {COUPON_PAYMENT, "", "", "",
                   ut.formatNumber("" + inputCash)});
    }

    //show amount and Change due label
    inputCash = prevousInput + inputCash;
    changeDue = ut.convertToDouble(lblTotal.getText()) - inputCash;
    changeDue = ut.getRound(changeDue, Constant.PM_DECIMAL_LENGTH);

    AmountTenderedNum = ut.formatNumber("" + inputCash);
    ChangedDueNum = ut.formatNumber("" + changeDue);

    lblAmountTendered.setText(lang.getString("AmountTendered") + ":" +
                              ut.formatNumber("" + inputCash));
    if (inputCash >= fTotal) {
      lblAmountDue.setText(lang.getString("ChangedDue") +": " + ut.formatNumber("" + changeDue));
      //update payment table
      model.addRow(new Object[] {CASH_PAYMENT, "", "", "",
                   ut.formatNumber("" + changeDue)});
      flagPayment = true;
    }
    else {
      lblAmountDue.setText(lang.getString("ChangedDue") +": " + ut.formatNumber("" + changeDue));
      flagPayment = false;
    }
  }

  //return true : payment is finished
  void foreignCashPayment(double inputCash) {
    double changeDue = 0; //So tien phai tra lai
    double fTotal = 0; //So tien phai tra khi khach mua hang
    double foreignInputCash = inputCash; //take foreign cash
    String paymentDetail = "";
    double prevousInput = 0; //
    boolean isExistRow = false;
    int updateRow = 0;
    double oldCash = 0;

    //get total
    fTotal = ut.convertToDouble(lblTotal.getText());
    for (int i = 0; i < tblPayment.getRowCount(); i++) {
      prevousInput +=
          ut.convertToDouble(tblPayment.getValueAt(i, PM_COL_AMOUNT).toString());
      if (tblPayment.getValueAt(i,
                                PM_COL_TYPE).toString().equalsIgnoreCase(
          FOREIGN_CASH_PAYMENT)) {
        isExistRow = true;
        updateRow = i;
        oldCash = ut.convertToDouble(tblPayment.getValueAt(i, PM_COL_AMOUNT).
                                     toString());
      }
    }
//    System.out.println("amount " + prevousInput);

    inputCash = inputCash * getExchangeRate(currencyID);
    paymentDetail = ut.formatNumber("" + foreignInputCash) + "(" + currencyID +
        "), 1 (" + currencyID + ")=" + (getExchangeRate(currencyID)) +
        "(" + DAL.getCurrencyID() + ")";
//    System.out.println(paymentDetail);
    //update payment table
    if (isExistRow) {
      double cash = inputCash + oldCash;
      tblPayment.setValueAt(ut.formatNumber("" + cash), updateRow,
                            PM_COL_AMOUNT);
    }
    else {
      model.addRow(new Object[] {FOREIGN_CASH_PAYMENT, paymentDetail, "", "",
                   ut.formatNumber("" + inputCash)});
    }

    //show amount and Change due label
    inputCash = prevousInput + inputCash;
    changeDue = ut.convertToDouble(lblTotal.getText()) - inputCash;
    changeDue = ut.getRound(changeDue, Constant.PM_DECIMAL_LENGTH);

    AmountTenderedNum = ut.formatNumber("" + inputCash);
    ChangedDueNum = ut.formatNumber("" + changeDue);

    lblAmountTendered.setText(lang.getString("AmountTendered") + ":" +
                              ut.formatNumber("" + inputCash));
    if (inputCash >= fTotal) {
      lblAmountDue.setText(lang.getString("ChangedDue") +":" + ut.formatNumber("" + changeDue));
      //update payment table
      model.addRow(new Object[] {CASH_PAYMENT, "", "", "",
                   ut.formatNumber("" + changeDue)});
      flagPayment = true;
    }
    else {
      lblAmountDue.setText(lang.getString("ChangedDue") +":" + ut.formatNumber("" + changeDue));
      flagPayment = false;
    }

  }

  /*
   * HAM DELETE PAYMENT
   * @author 			Hieu.Pham
   *@param  :
   *@return :
   */
  void deletePayment() {
    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
    resetPayment();
  }

  //reset label
  void resetPayment() {
    lblAmountTendered.setText(lang.getString("AmountTendered") + ": 0.00");
    lblAmountDue.setText(lang.getString("ChangedDue") +": " + ut.formatNumber(lblTotal.getText()));
    flagButton = PM_CASH_BTN;
    flagPayment = false;
    btnPostTXN.setEnabled(false);
    frmMain.setEditTextInput(false);
    btnCash.requestFocus(true);
  }

  /*
   * Calculate amount of each item
   *@author : Vinh.Le
   *@param  : itemID,itemQuantity
   *@return : amount of item
   */
  double getAmountItem(String itemID, int itemQty, double markDown) {
//      DataAccessLayer DAL =frmMain.getDAL();
    Statement stmt = null;
    double amount = 0;

    ResultSet rs = null;

    String sqlStr = "Select * From BTM_POS_ITEM_PRICE Where ITEM_ID='" +
        itemID + "' and STORE_ID=" + DAL.getStoreID();
//    System.out.println(sqlStr);
//      rs = DAL.executeQueries(sqlStr);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr, stmt);
      if (rs.next()) {
        if (DAL.getFranchiseCust().length() == 13) {
          amount = rs.getDouble("UNIT_COST")*(1+rs.getDouble("VAT")/100) * itemQty - markDown;
        }else{
            amount = rs.getDouble("UNIT_PRICE") * itemQty - markDown;
        }
      }
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return amount;
  }

  //Nghi doan
  double getAmountItem(String itemID, double itemQty, double markDown) {
//      DataAccessLayer DAL =frmMain.getDAL();
    double amount = 0;
    Statement stmt = null;
    ResultSet rs = null;

    String sqlStr = "Select * From BTM_POS_ITEM_PRICE Where ITEM_ID='" +
        itemID + "' and STORE_ID=" + DAL.getStoreID();
//    System.out.println(sqlStr);
//      rs = DAL.executeQueries(sqlStr);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr, stmt);
      if (rs.next()) {
        if (DAL.getFranchiseCust().length() == 13) {
          amount = rs.getDouble("UNIT_COST")*(1+rs.getDouble("VAT")/100) * itemQty - markDown;
        }else{

            amount = rs.getDouble("UNIT_PRICE") * itemQty - markDown;
        }
      }
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return amount;
  }

  // End Nghi
  void saveData() {
    DAL.setBeginTrans(DAL.getConnection());
//      DataAccessLayer DAL = frmMain.getDAL();
    if (frmMain.pnlNewSale.flagReturn == Constant.RETURN_ITEM) {
//        System.out.println("Saving return items ...");
      try {
        Statement stmtReturn = null;
        stmtReturn = DAL.getConnection().createStatement();
        //return item from the same store
        if(frmMain.pnlReturnItem.vSql.size()>0){
          DAL.executeBatchQuery(frmMain.pnlReturnItem.vSql, stmtReturn);
          DAL.executeBatchQuery(frmMain.pnlReturnItem.vSql1, stmtReturn);
          frmMain.pnlReturnItem.vSql.clear();
          frmMain.pnlReturnItem.vSql1.clear();
        }

        //return from other store
        if(frmMain.pnlReturnNotFoundItem.vSql.size()>0){
          DAL.executeBatchQuery(frmMain.pnlReturnNotFoundItem.vSql, stmtReturn);
          DAL.executeBatchQuery(frmMain.pnlReturnNotFoundItem.vSql1, stmtReturn);
          frmMain.pnlReturnNotFoundItem.vSql.clear();
          frmMain.pnlReturnNotFoundItem.vSql1.clear();
        }

//        System.out.println("Successful to save return items");
        stmtReturn.close();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    String sSQL = new String();
    Statement stmt = null;
    ResultSet rs = null;
    float stockOnHand = 0;
    float changeDue = 0;
    double amountItem = 0;
    try {
      Item item;
//        Transaction trans = (Transaction) vTrans.elementAt(0);
      trans.setAmount( (float) ut.convertToDouble(lblTotal.getText()));
      Utils uTemp = new Utils();
      trans.setTrans_Date(java.sql.Date.valueOf(uTemp.getSystemDate()));
      trans.setTender_ID(tenderType);
      trans.setTrans_Type_ID(Constant.TRANS_TYPE_SALE);
      if (creditCardExpire.equalsIgnoreCase("")) {
        creditCardExpire = "1/1/1970";
      }
      int ii = 0;
      for (int i = 0; i < vSaleItem.size(); i++) {
        item = (Item) vSaleItem.elementAt(i);
//        System.out.println(item.getQuantity());
        //Nguyen Pham Begin
        trans_datetime = ut.getSystemDateTime1();
        //Nguyen Pham End
        ii++;
        //If return and buy new items => input new items to sale tables
        if (frmMain.pnlNewSale.flagReturn == Constant.RETURN_ITEM) {
          if (item.getQuantity() > 0) {
            //calculate amount of each item
            amountItem = getAmountItem(item.getId(), item.getQuantity(),
                                       item.getMarkdownAmount());
            //Nguyen Pham Begin
            Statement stmtUpd = null;
            sSQL =
                "INSERT INTO BTM_POS_DETAIL_TRANS VALUES( " + ii + "," +
                trans.getTrans_ID() + "," + trans.getTender_ID() + ",'" +
//                  trans.getEmpl_ID() + "'," +
                DAL.getEmpPosID1() + "'," +
                trans.getPromo_ID() + ",'" + item.getId() + "'," +
                trans.getStore_ID() + "," +
                item.getCustID() + "," + trans.getRegister_ID() + "," +
                String.valueOf(amountItem) +
                "," +
                item.getQuantity() + ",to_date('" + trans_datetime + "','" +
                ut.DATETIME_FORMAT2 + "')," +
                trans.getDisc_Amt() + ",'" +
                trans.getTrans_Type_ID() + "'," + item.getMarkdownAmount() +
                ",'" +
                item.getMarkdownReason() +
                "'," + item.getunitPrice() +
                ",'" + creditCardNum +
                "',to_Date('7/7/7777','dd/MM/yyyy'),0,0,'',to_Date('" +
                creditCardExpire + "','dd/MM/yyyy'),"+item.getSaleID()+")";
            //Nguyen Pham End
            try {
              stmtUpd = DAL.getConnection().createStatement();
//              System.out.println(sSQL);
              DAL.executeUpdate(sSQL, stmtUpd);
              stmtUpd.close();
            }
            catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
        else {
          //calculate amount of each item
          amountItem = getAmountItem(item.getId(), item.getQuantity(),
                                     item.getMarkdownAmount());
          //Nguyen Pham Begin
          Statement stmtUpd = null;
          sSQL =
              "INSERT INTO BTM_POS_DETAIL_TRANS VALUES( " + ii + "," +
              trans.getTrans_ID() + "," + trans.getTender_ID() + ",'" +
//                trans.getEmpl_ID() + "'," +
              DAL.getEmpPosID1() + "'," +
              trans.getPromo_ID() + ",'" + item.getId() + "'," +
              trans.getStore_ID() + "," +
              item.getCustID() + "," + trans.getRegister_ID() + "," +
              String.valueOf(amountItem) +
              "," +
              item.getQuantity() + ",to_date('" + trans_datetime + "','" +
              ut.DATETIME_FORMAT2 + "')," +
              trans.getDisc_Amt() + ",'" +
              trans.getTrans_Type_ID() + "'," +
              item.getMarkdownAmount() / item.getQuantity() + ",'" +
              item.getMarkdownReason() +
              "'," + item.getunitPrice() +
              ",'" + creditCardNum +
              "',to_Date('7/7/7777','dd/MM/yyyy'),0,0,'',to_Date('" +
              creditCardExpire + "','dd/MM/yyyy'),"+item.getSaleID()+")";
          //Nguyen Pham End
          try {
            stmt = DAL.getConnection().createStatement();
//            System.out.println("******* " + sSQL);
            DAL.executeUpdate(sSQL, stmt);
            stmt.close();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      float paidOut1 = 0;
      int tenderType1 = 0;
      int row = 0;
      String tT1 = "";

      for (int i = 0; i < tblPayment.getRowCount() - 1; i++) {
        tenderType = getTenderType(i,
                                   tblPayment.getValueAt(i, PM_COL_DETAIL).
                                   toString());
        if (tenderType == 3 && !mainForeignInput.equalsIgnoreCase("")) {
          paidIn = Long.parseLong(mainForeignInput);
        }
        else {
          if (currencyID.equalsIgnoreCase(DAL.getCurrencyID())) {
            paidIn = (float) ut.convertToDouble(tblPayment.getValueAt(i,
                PM_COL_AMOUNT).toString());
          }
        }
        float paidOut = Math.abs( (float) ut.convertToDouble(tblPayment.
            getValueAt(tblPayment.getRowCount() - 1, PM_COL_AMOUNT).toString()));
        //insert into BTM_POS_PAID_IN_OUT
        if (tblPayment.getRowCount() > 2) {
          tT1 = tblPayment.getValueAt(tblPayment.getRowCount() - 2,
                                      PM_COL_DETAIL).toString();
          row = tblPayment.getRowCount() - 2;
          if (tT1.equalsIgnoreCase(CREDIT_PAYMENT)) {
            tT1 = tblPayment.getValueAt(tblPayment.getRowCount() - 3,
                                        PM_COL_DETAIL).toString();
            row = tblPayment.getRowCount() - 3;
          }
        }
        tenderType1 = getTenderType(row, tT1);
        if (tenderType == CREDIT_VISA_TENDER ||
            tenderType == CREDIT_MASTER_TENDER ||
            tenderType == CREDIT_DISCOVER_TENDER ||
            tenderType == CREDIT_AMERICAN_EXPRESS_TENDER) {
          //Nguyen Pham Begin
          sSQL = "INSERT INTO BTM_POS_PAID_IN_OUT VALUES( " + trans.getTrans_ID() +
              ",0," + paidIn + "," + paidOut1 + ",'" + DAL.getCurrencyID() +
              "','" + DAL.getCurrencyID() + "'," +
              "to_date('" + ut.getSystemDateTime1() + "','" +
              ut.DATETIME_FORMAT2 +
              "')," + tenderType + ",'" + creditCardNum + "',to_Date('" +
              creditCardExpire + "','dd/MM/yyyy'),'',0,'" + DAL.getEmpPosID1() +
              "')";
          //Nguyen Pham End
        }
        else {
          //Nguyen Pham Begin
          if (tenderType == 3) {
            if (tenderType1 == tenderType) {
              sSQL = "INSERT INTO BTM_POS_PAID_IN_OUT VALUES( " +
                  trans.getTrans_ID() +
                  ",0," + paidIn + "," + paidOut + ",'" + currencyID1 + "','" +
                  DAL.getCurrencyID() + "'," +
                  "to_date('" + ut.getSystemDateTime1() + "','" +
                  ut.DATETIME_FORMAT2 +
                  "'), " + tenderType +
                  ",'',to_date('7777/7/7','yyyy/MM/dd'),'',0,'" +
                  DAL.getEmpPosID1() + "')";
            }
            else {
              sSQL = "INSERT INTO BTM_POS_PAID_IN_OUT VALUES( " +
                  trans.getTrans_ID() +
                  ",0," + paidIn + "," + paidOut1 + ",'" + currencyID1 + "','" +
                  DAL.getCurrencyID() + "'," +
                  "to_date('" + ut.getSystemDateTime1() + "','" +
                  ut.DATETIME_FORMAT2 +
                  "'), " + tenderType +
                  ",'',to_date('7777/7/7','yyyy/MM/dd'),'',0,'" +
                  DAL.getEmpPosID1() + "')";
            }
          }
          else {
            if (tenderType1 == tenderType) {
              sSQL = "INSERT INTO BTM_POS_PAID_IN_OUT VALUES( " +
                  trans.getTrans_ID() +
                  ",0," + paidIn + "," + paidOut + ",'" + currencyID + "','" +
                  DAL.getCurrencyID() + "'," +
                  "to_date('" + ut.getSystemDateTime1() + "','" +
                  ut.DATETIME_FORMAT2 +
                  "'), " + tenderType +
                  ",'',to_date('7777/7/7','yyyy/MM/dd'),'',0,'" +
                  DAL.getEmpPosID1() + "')";
            }
            else {
              sSQL = "INSERT INTO BTM_POS_PAID_IN_OUT VALUES( " +
                  trans.getTrans_ID() +
                  ",0," + paidIn + "," + paidOut1 + ",'" + currencyID + "','" +
                  DAL.getCurrencyID() + "'," +
                  "to_date('" + ut.getSystemDateTime1() + "','" +
                  ut.DATETIME_FORMAT2 +
                  "'), " + tenderType +
                  ",'',to_date('7777/7/7','yyyy/MM/dd'),'',0,'" +
                  DAL.getEmpPosID1() + "')";
            }
          }
          //Nguyen Pham End
        }
        Statement stmtUpd = null;
        try {
          stmtUpd = DAL.getConnection().createStatement();
//          System.out.println(sSQL);
          DAL.executeUpdate(sSQL, stmtUpd);
          stmtUpd.close();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
//          currencyID = CURENNCY_DEFAULT;

      }

    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
     DAL.setEndTrans(DAL.getConnection());
  }

  //get tender type of current row
  private int getTenderType(int row, String detail) {
    int tenderType = 0;
    String paymentType = tblPayment.getValueAt(row, PM_COL_TYPE).toString();
    if (paymentType.equals(CASH_PAYMENT)) {
      tenderType = CASH_TENDER;
    }
    else if (paymentType.equals(FOREIGN_CASH_PAYMENT)) {
      tenderType = FOREIGN_CASH_TENDER;
    }
    else if (paymentType.equalsIgnoreCase(CREDIT_PAYMENT)) {
      if (detail.equalsIgnoreCase("Visa")) {
        tenderType = CREDIT_VISA_TENDER;
      }
      else if (detail.equalsIgnoreCase("Master")) {
        tenderType = CREDIT_MASTER_TENDER;
      }
      else if (detail.equalsIgnoreCase("American Express")) {
        tenderType = CREDIT_AMERICAN_EXPRESS_TENDER;
      }
      else if (detail.equalsIgnoreCase("Discover")) {
        tenderType = CREDIT_DISCOVER_TENDER;
      }
    }
    else if (paymentType.equals(COUPON_PAYMENT)) {
      tenderType = COUPON_TENDER;
    }
    return tenderType;
  }

  //get exchange rate from BTM_POS_EXCHANGE_RATE
  private float getExchangeRate(String currencyID) {
    float exchangeRate = 0;
//      DataAccessLayer DAL = frmMain.getDAL();
    String sSQL = new String();
    ResultSet rs = null;
    Statement stmt = null;

    sSQL = "select * from BTM_POS_EXCHANGE_RATE where CURR_ID='" + currencyID +
        "' order by WORKDAY DESC";
//    System.out.println(sSQL);
//      rs=DAL.executeQueries(sSQL);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sSQL, stmt);
      if (rs.next()) {
        exchangeRate = rs.getFloat("EXCHANGE_RATE");
      }
      rs.close();
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return exchangeRate;
  }

  //show button in Frame Main
  void showButton() {
    frmMain.showButton(btnCash);
    frmMain.showButton(btnForeignCash);
    btnForeignCash.setEnabled(false);
    frmMain.showButton(btnCoupon);
    btnCoupon.setEnabled(false);
    frmMain.showButton(btnCreditCard);
    btnCreditCard.setEnabled(false);
    frmMain.showButton(btnPostTXN);
    frmMain.showButton(btnDelPayment);
//    frmMain.showButton(btnPrintReceipt);
    frmMain.showButton(btnBack);
//    frmMain.showButton(jButton1);
//    frmMain.showButton(jButton2);
  }

  void showPnlCash(){
    frmMain.showButton(btnFixCharge);
    frmMain.showButton(btn200);
    frmMain.showButton(btn500);
    frmMain.showButton(btn1000);
    frmMain.showButton(btn2000);
    frmMain.showButton(btn5000);
    frmMain.showButton(btn10000);
    frmMain.showButton(btn20000);
    frmMain.showButton(btn50000);
    frmMain.showButton(btn100000);
    frmMain.showButton(btn500000);
    frmMain.showButton(btnEnter);
  }
  boolean isValidAmount() {
    if (!ut.isFloatString(mainInput)) {
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS093_PameyNum"));
      return false;
    }
    return true;

  }

  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    if (e.getKeyCode() != KeyEvent.VK_F5 && e.getKeyCode() != KeyEvent.VK_F1 &&
        e.getKeyCode() != KeyEvent.VK_F2
        && e.getKeyCode() != KeyEvent.VK_F3 && e.getKeyCode() != KeyEvent.VK_F4 &&
        e.getKeyCode() != KeyEvent.VK_F6 && e.getKeyCode() != KeyEvent.VK_F7) {
      mainInput = frmMain.getInputText();
//      System.out.println("AAA: " + mainInput);
      Utils ut = new Utils();
      if (flagPayment)
        return;
      if(mainInput.equals("")) return;
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        if (Double.parseDouble(frmMain.txtMainInput.getText())>99999999){
             ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS094_MoneyMax"));
             frmMain.txtMainInput.setText("");
             return;
           }


        //Hieu
        if (flagButton == PM_CASH_BTN) {
          if (!isValidAmount())
            return;
          cashPayment(Double.parseDouble(mainInput));
        }
        else if (flagButton == PM_FOREIGN_CASH_BTN) {
          if (!isValidAmount())
            return;
          paidIn = Float.parseFloat(mainInput);
          foreignCashPayment(Double.parseDouble(mainInput));
          mainForeignInput = mainInput;
          frmMain.setInputLabel(lang.getString("Amount"));
          currencyID = DAL.getCurrencyID();
          flagButton = PM_CASH_BTN;
        }

        else if (flagButton == PM_CREDIT_EXP_DATE_BTN) {
          if (!isValidAmount())
            return;
          if (frmMain.cboCreditCard.getSelectedIndex() == 0) {
            ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS095_TypeCredit"));
            frmMain.cboCreditCard.requestFocus(true);
            return;
          }
          if (creditPayment(Double.parseDouble(mainInput))) {
            frmMain.cboCreditCard.setSelectedIndex(0);
            flagButton = PM_CASH_BTN;
          }
        }
        else if (flagButton == PM_COUPON_BTN) {
          if (!isValidAmount())
            return;
          couponPayment(Double.parseDouble(mainInput));
          flagButton = PM_CASH_BTN;
        }
        addCash=0;
        frmMain.removeButton();
        showButton();
        flagCash = false;
        //customer finish payment
        if (flagPayment) {
          btnPostTXN.setEnabled(true);
          frmMain.setEditTextInput(false);
//          System.out.println("Print1: " + lblAmountDue.getText());
          btnPostTXN.requestFocus(true);
        }
        else {
          btnPostTXN.setEnabled(false);
          frmMain.setEditTextInput(false);
          btnCash.requestFocus(true);
        }
        //format Amount Due
        formatAmountDue();
      }
      else {
        if (flagButton == PM_CREDIT_BTN) {
          ut.limitLenjTextField(e, frmMain.txtMainInput, 15);
        }

      } //enter
    }
  }

  void btnCreditCard_actionPerformed(ActionEvent e) {
    frmMain.setComboVisible();
    flagButton = PM_CREDIT_EXP_DATE_BTN; ;
    tenderType = CREDIT_VISA_TENDER;
    currencyID = DAL.getCurrencyID();
    frmMain.setInputLabel(lang.getString("MS096_EnterAmount"));
    frmMain.setEditTextInput(true);
  }

  void btnCash_actionPerformed(ActionEvent e) {
    flagButton = PM_CASH_BTN;
    tenderType = CASH_TENDER;
    currencyID = DAL.getCurrencyID();
    frmMain.setInputLabel(lang.getString("Amount"));
    frmMain.setEditTextInput(true);
    frmMain.setComboInVisible();
    frmMain.removeButton();
    showPnlCash();
    flagCash=true;
  }

  void btnCoupon_actionPerformed(ActionEvent e) {
    flagButton = PM_COUPON_BTN;
    tenderType = COUPON_TENDER;
    currencyID = DAL.getCurrencyID();
    frmMain.setInputLabel(lang.getString("Amount"));
    frmMain.setEditTextInput(true);
    frmMain.setComboInVisible();
  }

  void btnForeignCash_actionPerformed(ActionEvent e) {
    frmMain.setComboInVisible();
    flagButton = PM_FOREIGN_CASH_BTN;
    tenderType = FOREIGN_CASH_TENDER;

    DialogChooseCurrencyCode dlgCurrency = new DialogChooseCurrencyCode(frmMain,
        lang.getString("TT052"), true, frmMain.getDAL(), frmMain);
    dlgCurrency.setVisible(true);
    currencyID = dlgCurrency.getCurrencyCode();
    currencyID1 = dlgCurrency.getCurrencyCode();
    frmMain.setInputLabel(lang.getString("EnterAmountIn")+" " + currencyID);
    frmMain.setEditTextInput(true);

  }

  void btnPostTXN_actionPerformed(ActionEvent e) {
    saveData();
    //check if winner trans
    if(vGiftPromoType.size()>0){
      winnerTrans = applyPromoGift(String.valueOf(trans.getTrans_ID()),
                                   trans.getCust_ID(),
                                   String.valueOf(trans.getAmount()),
                                   vGiftPromoType);
    }
    PanelPrintPage ppp;
    PanelPrintReciept panreceipt;
    if (e.getSource()instanceof JButton) {
      PrinterJob printjob = PrinterJob.getPrinterJob();
      ppp = new PanelPrintPage(frmMain);
      printjob.setPrintable(ppp);
      try {
          int i, j, l, m;
          PrintReceipt pp = new PrintReceipt();
          String sTitle = DAL.getStoreName() + " - ";
          String Header[] = {
              DAL.getStoreID(), DAL.getStoreAddress(),
              "Ngay: " + ut.getSystemDate().substring(0, 10),
              " " + ut.getSystemDateTime2().substring(11, 19),
              "NV: " + trans.getEmpl_ID()
          };
          String CustInfo= getCustInfo(trans.getCust_ID())              ;

          if(winnerTrans){
            CustInfo=CustInfo+ "\r\n"+"BILL TRUNG THUONG: "+giftName;
          }

          //Unilever Item: show revenue
          String uniRevenue="";
          uniRevenue = getUniRevenue();
          if(!getUniRevenue().equals("")){
            CustInfo=CustInfo+ "\r\n"+"TONG TIEN UNI : "+uniRevenue;
          }

          String tableAlign[] = {"LEFT", "CENTER", "RIGHT", "LEFT", "RIGHT"};
          //String Item[][] = {{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"}};
          int length = vSaleItem.size() * 2;
          int iSkew[] = { 35, 17, 21, 27};
          Item item = new Item();
          double TotalPrice = 0;
          long lSubTotal = 0;
          long lDiscount = 0;
          long lTotal = 0;
          double Quantity = 0;
          long lTotalQuantity = 0;

          String sItem[][] = new String[length][5];
          //Convert the vector Item to array string Item
          j = 0;//j=0: set item number (1,2,3...)  and name to sItem(0,i)
          l = 0;
          for (i = 0; i < vSaleItem.size(); i++) {
            item = (Item) vSaleItem.elementAt(i); //Get item
            Quantity = item.getQuantity();
            TotalPrice = Quantity * item.getunitPrice();
            sItem[j][0] = "" + (int) (l + 1) + ". ";
            sItem[j][1] = item.getDescription(); //Name
            if (item.getMarkdownAmount() > 0) {
              double getAmt = item.getMarkdownAmount();
              double dDiscount = item.getMarkdownAmount() / TotalPrice * 100;
              sItem[j][2] = "-" + ut.getRound(dDiscount, 2) + "%"; //Discount

              sItem[j][3] = "";

              sItem[j][4] = "-" + item.getMarkdownAmount(); //Markdown
            }
            else {
              sItem[j][2] = "";

              sItem[j][3] = "";

              sItem[j][4] = "";
            }
            j++;//j=1: set barcode,quantity, unit price, VAT, total to sItem(1,i)

            sItem[j][0] = getArtNo(item.getId(), DAL);
            sItem[j][1] = "" + item.getQuantity();

            sItem[j][2] = "" + item.getunitPrice();
            TotalPrice = Quantity * item.getunitPrice();

            lSubTotal += TotalPrice;
            lDiscount += (long) item.getMarkdownAmount();
            lTotal += item.getAmountDue();
            lTotalQuantity += Quantity;

            sItem[j][3] = "VAT" + item.getVAT() + "%";

            sItem[j][4] = "" + TotalPrice;



            j++;
            l++;
          }
          String sEnding[] = {
              "" + lSubTotal, "" + lDiscount, "" + lTotal, "" + lTotalQuantity};
          pp.setHeaderReceipt(Header);
          pp.setCustInfo(CustInfo);
          pp.setFileName(DAL.getAppHome() + "\\file\\" +
                         String.valueOf(trans.getTrans_ID()).toString() +
                         ".pdf");
          pp.setTypeReceipt(2); //Type of receipt is TNX

          //Set Text
          String sAMT = lblAmountTendered.getText();
          String sCHDue = lblAmountDue.getText();

          sAMT = sAMT.substring(sAMT.indexOf(": ") + 2, sAMT.length() - 1);
          sCHDue = sCHDue.substring(sCHDue.indexOf(": ") + 2,
                                    sCHDue.length() - 1);

          pp.setIsShowAmount(true);
          pp.setAmountTendered(sAMT);
          pp.setChangedDue(sCHDue);

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

          pp.setIDTNX(String.valueOf(trans.getTrans_ID()).toString());

          pp.setDeleteFileAfterPrint(true);

          pp.print();
//        }
      }
      catch (Exception e1) {
        e1.printStackTrace();
      }
      frmMain.txtMainInput.requestFocus(true);
    }
    //Message winner trans
    if(winnerTrans){
      ut.showMessage(frmMain, lang.getString("TT001"),"BILL TRUNG THUONG: "+giftName);
      playSound(curDir+"\\file\\speaker.wav");
//      System.out.println("================================== "+curDir+"\\file\\speaker.wav");



    }

    //reset transaction

    currencyID = DAL.getCurrencyID();
    paidIn = 0;
    creditCardNum = "";
    creditCardExpire = "";
    //BTM_POS_DETAIL_TRANS(SEQ_NO,TRANS_ID,TENDER_ID,EMP_ID,PROMO_ID,ITEM_ID,STORE_ID,CUST_ID,REGISTER_ID,AMOUNT,UNIT_QTY,TRANS_DATE,DISC_AMT,TRANS_TYPE_ID,MARKDOWN,MKDN_DISC_REASON,ORG_UNIT_PRICE)
    tenderType = CASH_TENDER;
    frmMain.lblName.setText(lang.getString("Name") + ": ");
    frmMain.lblPhone.setText(lang.getString("Phone") + ": ");
    frmMain.pnlNewSale.custID = -1;
    frmMain.pnlNewSale.flagReturn = Constant.SALES_ITEM;
    deletePayment();
    frmMain.pnlNewSale.deleteNewSale();
    frmMain.pnlNewSale.lblTotal.setText(lang.getString("TotalItem") + ": 0");
    frmMain.pnlNewSale.lblSubTotal.setText(lang.getString("SubTotal") + ": 0");
    frmMain.pnlNewSale.custID=-1;
    frmMain.pnlNewSale.custType=-1;
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.showScreen(Constant.SCR_NEW_SALE);
  }

  //Get Customer Info
  String getCustInfo(String custID) {
    String custInfo="";
    ResultSet rs = null;
    try {
      rs = null;
      if (!custID.equals("-1")) { //is not No Name
        String sql =
            "select FIRST_NAME,LAST_NAME,total from BTM_POS_CUSTOMER where CUST_ID='" +
            custID + "'";
        rs = DAL.executeQueries(sql);
        if (rs.next()) {
//          custName = rs.getString("FIRST_NAME");
          custInfo ="K/hang:"+ rs.getString("LAST_NAME")+ "\r\n"; //+ "Doanh so:"+rs.getString("total");
        }
        rs.getStatement().close();
      }
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return custInfo;
  }

  //Get Cust Type
  String getItemName(String sID) {
    String iName = " ";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select ITEM_DESC from BTM_POS_ITEM_PRICE where ITEM_ID = '" +
          sID + "'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("ITEM_DESC") != null) {
          iName = rs.getString("ITEM_DESC");
        }
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return iName;
  }

  //Get Cust Type
  String getCustType(String sCustID) {
    String iType = "";
    ResultSet rs = null;
    try {
      rs = null;
      if (DAL.getFranchiseCust().length() == 13) {
        sCustID = DAL.getFranchiseCust();
      }

      String sql = "select CUST_FLAG from BTM_POS_CUSTOMER where CUST_ID='" +
          sCustID + "'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("CUST_FLAG") != null) {
          iType = rs.getString("CUST_FLAG");
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

  void btnDelPayment_actionPerformed(ActionEvent e) {
    deletePayment();
    frmMain.setComboInVisible();
    frmMain.setInputLabel(lang.getString("Amount"));
  }

  //get Store Name
  public String getStoreName(String strStoreID, DataAccessLayer DAL) {
    String strInfo = "";
    ResultSet rs = null;
    try {
      rs = null;
      String sql = "select NAME from BTM_POS_STORE where STORE_ID='" +
          strStoreID + "'";
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        if (rs.getString("NAME") != null) {
          strInfo = rs.getString("NAME");
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

//  void btnPrintReceipt_actionPerformed(ActionEvent e) {
//    /*PanelPrintReciept PanPrint = new PanelPrintReciept(frmMain);
//         PanPrint.setTransaction(trans);
//         PanPrint.setSaleItem(vSaleItem);*/
//    trans.setTrans_Date(java.sql.Date.valueOf(ut.getSystemDate()));
//    if (e.getSource()instanceof JButton) {
//      try {
//        int i, j, l, m;
//        PrintReceipt pp = new PrintReceipt();
//        String sTitle = DAL.getStoreName() + " - ";
//        String Header[] = {
//            DAL.getStoreID(), DAL.getStoreAddress(),
//            "Date#" + ut.getSystemDate().substring(0, 10),
//            "Op#" + trans.getEmpl_ID(),
//            "Time#" + ut.getSystemDateTime2().
//            substring(11, 19)};
//        String Align[] = {
//            "LEFT", "CENTER", "LEFT", "RIGHT"};
//        //String Item[][] = {{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"},{"1.","AAAAAAAAAAAAA","123456","XXXX"},{"0843160620186","100","315000","1234565465"}};
//        int length = vSaleItem.size() * 2;
//        int iSkew[] = {
//            35, 19, 19, 27};
//        Item item = new Item();
//        double TotalPrice = 0;
//        long lSubTotal = 0;
//        long lDiscount = 0;
//        long lTotal = 0;
//        double Quantity = 0;
//
//        String sItem[][] = new String[length][4];
//        //Convert the vector Item to array string Item
//        j = 0;
//        l = 0;
//        for (i = 0; i < vSaleItem.size(); i++) {
//          item = (Item) vSaleItem.elementAt(i); //Get item
//          Quantity = item.getQuantity();
//          TotalPrice = Quantity * item.getunitPrice();
//          sItem[j][0] = "" + (int) (l + 1) + ". ";
//          sItem[j][1] = item.getDescription(); //Name
//          if (item.getMarkdownAmount() > 0) {
//            double getAmt = item.getMarkdownAmount();
//            double dDiscount = item.getMarkdownAmount() / TotalPrice * 100;
//            sItem[j][2] = "-" + ut.getRound(dDiscount, 2) + "%"; //Discount
//
//            sItem[j][3] = "-" + item.getMarkdownAmount(); //Markdown
//          }
//          else {
//            sItem[j][2] = "";
//
//            sItem[j][3] = "";
//          }
//          j++;
//
//          sItem[j][0] = getArtNo(item.getId(), DAL);
////            if (frmMain.pnlNewSale.flagReturn == Constant.RETURN_ITEM) {
////              sItem[j][1] = "" + item.getQuantity();
////              lQuantity = (long) item.getQuantity();
////            }
////            else {
//          sItem[j][1] = "" + item.getQuantity();
//          Quantity = item.getQuantity();
////            }
//
//          sItem[j][2] = "" + item.getunitPrice();
//          TotalPrice = Quantity * item.getunitPrice();
//
//          lSubTotal += TotalPrice;
//          lDiscount += (long) item.getMarkdownAmount();
//          lTotal += item.getAmountDue();
//
//          sItem[j][3] = "" + TotalPrice;
//          j++;
//          l++;
//        }
//        String sEnding[] = {
//            "" + lSubTotal, "" + lDiscount, "" + lTotal};
//        pp.setHeaderReceipt(Header);
//        pp.setFileName(DAL.getAppHome() + "\\file\\" +
//                       String.valueOf(trans.getTrans_ID()).toString() +
//                       ".pdf");
//        pp.setTypeReceipt(2); //Type of receipt is TNX
//
//        //Set Text
//        String sAMT = lblAmountTendered.getText();
//        String sCHDue = lblAmountDue.getText();
//
//        sAMT = sAMT.substring(sAMT.indexOf(": ") + 2, sAMT.length() - 1);
//        sCHDue = sCHDue.substring(sCHDue.indexOf(": ") + 2, sCHDue.length() - 1);
//
//        pp.setIsShowAmount(true);
//        pp.setAmountTendered("0.00");
//        pp.setChangedDue("0.00");
//
//        pp.setAcrobatPath(DAL.getAcrobatFile());
//        pp.setDelayBeforePrint(DAL.getDelayBeforePrint());
//        pp.setDelayAfterPrint(DAL.getDelayAfterPrint());
//
//        pp.setTurnOffAcrobat(DAL.getTurnOffAcrobat());
//        pp.setShowDialog(false);
//        pp.setIsPrintBarcode(true);
//        pp.setTableAlignTNX(Align);
//        pp.setItemTNX(sItem);
//        pp.setTotalTNX(sEnding);
//        pp.setTableTNXSkew(iSkew);
//        pp.setTitleTNX(sTitle);
//
//        pp.setIDTNX(String.valueOf(trans.getTrans_ID()).toString());
//
//        pp.setDeleteFileAfterPrint(true);
//
//        pp.print();
//
//
//      }
//      catch (Exception e1) {
//        e1.printStackTrace();
//      }
//
//
//    }
//    frmMain.setComboInVisible();
//  }



  void btnBack_actionPerformed(ActionEvent e) {
    deletePayment();
//    frmMain.pnlNewSale.resetSaleItem();
    frmMain.setComboInVisible();
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.showScreen(Constant.SCR_NEW_SALE);
    flagCash=false;
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
    // DELETE
    key = new Integer (KeyEvent.VK_DELETE);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0),key);
    actionMap.put(key,new KeyAction(key));
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
      if (flagCash==true) {
        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnFixCharge.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F2) {
          btn200.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F3) {
          btn500.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F4) {
          btn1000.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F5) {
          btn2000.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F6) {
          btn5000.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F7) {
          btn10000.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F8) {
          btn20000.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F9) {
          btn50000.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F10) {
          btn100000.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F11) {
          btn500000.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F12) {
          btnEnter.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnBack.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_DELETE) {
          btnDelPayment.doClick();
        }
      }
      else{
        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnCash.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F2) {
          btnForeignCash.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F3) {
          btnCoupon.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F4) {
          btnCreditCard.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F5) {
          btnPostTXN.doClick();
          frmMain.txtMainInput.setText("");
          frmMain.txtMainInput.requestFocus(true);
        }
        else if (identifier.intValue() == KeyEvent.VK_F6
                 || identifier.intValue()==KeyEvent.VK_DELETE) {
          btnDelPayment.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F7) {
//          btnPrintReceipt.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F8) {
        }
        else if (identifier.intValue() == KeyEvent.VK_F9) {
        }
        else if (identifier.intValue() == KeyEvent.VK_F10) {
        }
        else if (identifier.intValue() == KeyEvent.VK_F11) {
        }
        else if (identifier.intValue() == KeyEvent.VK_F12
                 || identifier.intValue()==KeyEvent.VK_ESCAPE) {
          btnBack.doClick();
        }
     }
   }
  }

//
//=======
  void btnCancelTransaction_actionPerformed(ActionEvent e) {
    currencyID = DAL.getCurrencyID();
    paidIn = 0;
    creditCardNum = "";
    creditCardExpire = "";
    //BTM_POS_DETAIL_TRANS(SEQ_NO,TRANS_ID,TENDER_ID,EMP_ID,PROMO_ID,ITEM_ID,STORE_ID,CUST_ID,REGISTER_ID,AMOUNT,UNIT_QTY,TRANS_DATE,DISC_AMT,TRANS_TYPE_ID,MARKDOWN,MKDN_DISC_REASON,ORG_UNIT_PRICE)
    tenderType = CASH_TENDER;
    frmMain.lblName.setText(lang.getString("Name") + ": ");
    frmMain.lblPhone.setText(lang.getString("Phone") + ": ");
    frmMain.pnlNewSale.custID = -1;
    frmMain.pnlNewSale.flagReturn = Constant.SALES_ITEM;
    deletePayment();
    frmMain.pnlNewSale.deleteNewSale();
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.showScreen(Constant.SCR_NEW_SALE);
    frmMain.pnlNewSale.lblTotal.setText(lang.getString("TotalItem")+": 0");
    frmMain.pnlNewSale.lblSubTotal.setText(lang.getString("SubTotal") + ": 0");
  }

  void tblPayment_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      btnCoupon.doClick();
    }
  }

//  public void jButton1_actionPerformed(ActionEvent e) {
//    frmMain.txtMainInput.setText(frmMain.txtMainInput.getText() +
//                                 DAL.getFirstAutoNum());
//  }
//
//  public void jButton2_actionPerformed(ActionEvent e) {
//    frmMain.txtMainInput.setText(frmMain.txtMainInput.getText() +
//                                 DAL.getSectAutoNum());
//  }

  public void btnFixCharge_actionPerformed(ActionEvent e) {
    addCash=0;
    frmMain.txtMainInput.getText().equals("");
    double GrandTotal = ut.convertToDouble(lblTotal.getText());
    frmMain.txtMainInput.setText(""+GrandTotal);
  }

  public void btnEnter_actionPerformed(ActionEvent e) {
    frmMain.removeButton();
    showButton();
    flagCash=false;
    frmMain.setEditTextInput(false);
    if(flagPayment){
      btnPostTXN.requestFocus(true);
    }else{
      btnCash.requestFocus(true);
    }
  }

  public void btn200_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("200");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }

  public void btn500_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("500");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }

  public void btn1000_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("1000");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }
  public void btn2000_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("2000");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }
  public void btn5000_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("5000");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }
  public void btn10000_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("10000");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }
  public void btn20000_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("20000");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }
  public void btn50000_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("50000");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }
  public void btn100000_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("100000");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }
  public void btn500000_actionPerformed(ActionEvent e) {
    if(frmMain.txtMainInput.getText().equals("")){
      addCash=0;
    }else{
      if(ut.isDoubleString(frmMain.txtMainInput.getText())){
        addCash=ut.convertToDouble(frmMain.txtMainInput.getText());
      }
    }
    addCash=addCash+ut.convertToDouble("500000");
    frmMain.txtMainInput.setText(""+addCash);
    frmMain.txtMainInput.requestFocus(true);
  }

  //======================== Unilever Item: show revenue  ===================//
 String getUniRevenue(){
   String total="";
   float fTotal; //, fAmountTendered, fAmountDue;
   fTotal = 0;

   Item item;
   for (int i = 0; i < vSaleItem.size(); i++) {
     item = (Item) vSaleItem.elementAt(i);
     if(vUnileverItem.contains(String.valueOf(item.getId()))){
       fTotal += item.getAmountDue();
     }
   }
   if(fTotal>0){
    total= ut.doubleToString(fTotal);
   }
   return total;
 }
 //get all Unilever item
  Vector getUnileverItem(String suppID){
    Vector v=new Vector();
    String sql = "";
    ResultSet rs = null;

    try{
      sql =  "select * from BTM_POS_ITEM_PRICE where SUPP_ID ='"+suppID+"'";

//    System.out.println(sql);
      rs = DAL.executeQueries(sql);
      while (rs.next()){
        v.add(rs.getString("ITEM_ID"));
      }

      rs.getStatement().close();

    }catch(Exception e){
      e.printStackTrace();
    }
    return v;
  }

  //======================== Gift Promo Code ===================//
  //get all info of all Gift Promo type
  Vector getGiftPromoData(){
    Vector vGiftPromoType =new Vector();//public, store all GiftPromo Type

    GiftPromoType giftPromoType=new GiftPromoType();

   String giftPromoID = "";
   String minRevenue = "";
   String maxRevenue = "";
   String totalTrans = "";
   String totalGift = "";
   Vector vGiftList = new Vector(); // list of gift
   Vector vTransPos = new Vector(); // trans that win
   String status = "";
   String startDate = "";
    String endDate = "";
    //get BTM_POS_GIFT_PROMO_TYPE

    String sql = "";
    ResultSet rs = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    try{
      sql = "select * from BTM_POS_GIFT_PROMO_TYPE";
//    System.out.println(sql);
      rs = DAL.executeQueries(sql);
      while (rs.next()){
        //reset vector: must new
        vGiftList = new Vector();
        vTransPos = new Vector();

         giftPromoID = rs.getString("GIFT_PROMO_TYPE_ID");
         minRevenue = rs.getString("MIN_REVENUE");
         maxRevenue = rs.getString("MAX_REVENUE");
         totalTrans = rs.getString("TOTAL_TRANS");
         totalGift = rs.getString("TOTAL_GIFT");
         startDate = rs.getString("START_DATE");
         endDate = rs.getString("END_DATE");
         status = rs.getString("STATUS");
         //get type of gift for this Gift Promo
         sql = "select * from BTM_POS_GIFT_TOTAL where GIFT_PROMO_TYPE_ID= " +
             giftPromoID;
//       System.out.println(sql);
         rs1 = DAL.executeQueries(sql);
         while (rs1.next()) {
           vGiftList.add(rs1.getString("GIFT_TYPE_ID"));
         }
         rs1.getStatement().close();

         //get list of trans gift
         sql = "select * from BTM_POS_GIFT_TRANS where GIFT_PROMO_TYPE_ID= " +
             giftPromoID;
//       System.out.println(sql);
         rs2 = DAL.executeQueries(sql);
         while (rs2.next()) {
           vTransPos.add(rs2.getString("TRANS_POSITION"));
         }
         rs2.getStatement().close();

//      System.out.println(giftPromoID +" "+minRevenue+" "+maxRevenue+" "+totalTrans+" "+totalGift+" "+startDate+" "+endDate+" "+status);
        giftPromoType =new GiftPromoType(giftPromoID,minRevenue,maxRevenue,totalTrans,totalGift,vGiftList,vTransPos,startDate,endDate,status);
        vGiftPromoType.add(giftPromoType);
      }
      rs.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return vGiftPromoType;
  }

  // Gift promo not aply for HOme Emp
  //check after save current TXN
  //return true if trans is winner (position of trans in Winner list)

  boolean applyPromoGift(String transID,String custID,String amount, Vector vGiftPromoType){
    boolean win=false;
    int posPromoGift=-1;//position of Promo gift type in Vector
    int numTrans=0;//number trans has been saved (sum all trans have the same Promo Gift type with current trans
    String giftID="";
    GiftPromoType giftPromoType =new GiftPromoType(); //keep Promo Gift type
    String startDate ="";
    String endDate ="";
    String currDate="";


    for (int i = 0; i < vGiftPromoType.size() ; i++) {
      giftPromoType=(GiftPromoType)vGiftPromoType.elementAt(i);
      if (Double.parseDouble(amount)>=Double.parseDouble(giftPromoType.getMinRevenue()) && Double.parseDouble(amount)<Double.parseDouble(giftPromoType.getMaxRevenue()) ){
         posPromoGift=i;
         break;
      }
    }
    //check
    startDate = giftPromoType.getStartDate().substring(0,10).replace('/', '-');
    startDate = ut.getDD_MM_YYYY(startDate);
    endDate = giftPromoType.getEndDate().substring(0,10).replace('/', '-');
    endDate =  ut.getDD_MM_YYYY(endDate);
    currDate =ut.getSystemDateStandard();
    //exit if Gift Promo is out of date
    if(ut.compareDate(startDate,currDate )|| ut.compareDate(currDate,endDate ) ){
//      System.out.println("========================================= Gift Promo : Out of date");
      return false;
    }
    // exit if Gift Promo is disbable
    if(!giftPromoType.getStatus().equals("1")) {
//      System.out.println("========================================= Gift Promo:  disable");
      return false;
    }
    //exit if amount out of range
    if (posPromoGift ==-1) return false;
    //exit if customer is disable Gift Promo (HOme emp)

    //Remove_Uni_CheckCust
    if (!checkCustomerType(custID)){
//      System.out.println("========================================= Customer:  disable");
      return false;
    }

    //count trans of this Gift Promo type, include current Trans
    numTrans=countTrans(giftPromoType.getMinRevenue(),giftPromoType.getMaxRevenue(),giftPromoType.getStartDate().substring(0,10),giftPromoType.getEndDate().substring(0,10));

    //check if this trans is winner
//    System.out.println("============================current trans num : "+numTrans);
    if(giftPromoType.getVTransPos().contains(String.valueOf(numTrans))){
      giftID=getRandomGift(giftPromoType.getGiftPromoID());
      if (giftID.equals("")){
        return false;
      }
//      System.out.println("gift: "+ giftID);
      updatePromoGift(transID,custID,ut.getSystemDate(),giftPromoType.getGiftPromoID(),giftID);
      win=true;
    }

    return win;
  }
  //check customer type
  //return true if customer is enable (permit apply Gift Promo )
  boolean checkCustomerType(String custID){
    boolean enable=true;
    String sql = "";
    ResultSet rs = null;

    try{
      sql =  "select * from BTM_POS_CUSTOMER c, BTM_POS_CUSTOMER_TYPE t where c.CUST_FLAG =t.CUST_TYPE_ID and c.CUST_ID='"+custID+"'";

//    System.out.println(sql);
      rs = DAL.executeQueries(sql);
      if(rs.next()){
        enable =false;
      }
      rs.getStatement().close();

    }catch(Exception e){
      e.printStackTrace();
    }
    return enable;
  }

//update gift qty and history
  void updatePromoGift(String transID,String custID,String updateDate,String giftPromoTypeID, String giftTypeID){
    String sql = "";


    try{
      //update gift qty
      DAL.setBeginTrans(DAL.getConnection());

      sql =  "Update BTM_POS_GIFT_TOTAL set LEFT_QTY =LEFT_QTY-1 where GIFT_PROMO_TYPE_ID ="+giftPromoTypeID+" and GIFT_TYPE_ID="+giftTypeID;
//      System.out.println(sql);
      DAL.executeQueries(sql);

      //update history
      sql = "INSERT INTO BTM_POS_GIFT_PROMO_HIST VALUES('" + transID +
          "','"+  custID + "','" + giftTypeID+ "',to_date('" + trans_datetime + "','" +ut.DATETIME_FORMAT2 + "'))";
//      System.out.println(sql);

      DAL.executeQueries(sql);

      DAL.setEndTrans(DAL.getConnection());
    }catch(Exception e){
      e.printStackTrace();
    }


  }

  //rand.nextInt(n)+1    => random from 1..n
//get gift: random from available gift (qty>0)
//return "" if gift is out of stock

  String getRandomGift(String giftPromoTypeID){

    String giftID="";
    String sql = "";
    ResultSet rs = null;
    ResultSet rs1 = null;
    Vector vGiftID=new Vector();
    Vector vGiftQty=new Vector();
    Random rand = new Random();

    int totalQty=0;
    int begin=0;
    int end =0;
    int ranNum=0;

    try{
      sql =  "select * from BTM_POS_GIFT_TOTAL where GIFT_PROMO_TYPE_ID ="+giftPromoTypeID+" and LEFT_QTY>0";

//  System.out.println(sql);
      rs = DAL.executeQueries(sql);
      while (rs.next()){
        vGiftID.add(String.valueOf(rs.getInt("GIFT_TYPE_ID")));
        vGiftQty.add(String.valueOf(rs.getInt("LEFT_QTY")));
        totalQty=totalQty+rs.getInt("LEFT_QTY");
//      System.out.println("total: "+totalQty);
      }
      //random gift
      if (vGiftID.size()>0){
        //random base on total left qty
        ranNum = rand.nextInt(totalQty)+1 ;
        for (int i = 0; i < vGiftQty.size(); i++) {

           begin=end+1;
           end = end + Integer.parseInt(vGiftQty.elementAt(i).toString());
//System.out.println(begin+ "   to "+end);
           if(begin<=ranNum && ranNum <=end){
             giftID = vGiftID.elementAt(i).toString();
             break;
           }
        }
//      giftID = vGiftID.elementAt(rand.nextInt(vGiftID.size())).toString();
      }
      //get gift Name
      if(!giftID.equals("")){
        sql = "Select * from BTM_POS_GIFT_TYPE where GIFT_TYPE_ID= " + giftID;
        rs1 = DAL.executeQueries(sql);
        if (rs1.next()) {
          giftName = rs1.getString("GIFT_NAME");
//          System.out.println("====================="+giftName);
        }
        rs1.getStatement().close();
      }

      rs.getStatement().close();

    }catch(Exception e){
      e.printStackTrace();
    }

    return giftID;

}


//return number of Trans
// minRevenue
// maxRevenue
  int countTrans (String minRevenue, String maxRevenue,String starDate,String endDate){
    int count=0;
    String sql = "";
    ResultSet rs = null;

    try{
      sql =  "   select count(*) numtrans from BTM_POS_PAID_IN_OUT "
  + "  where  trunc(trans_date) >=to_date('"+starDate+"', 'yyyy-MM-dd') "
  + "  and trunc(trans_date) <=to_date('"+endDate+"', 'yyyy-MM-dd')  "
  + "  and (PAID_IN-PAID_OUT) >= "+minRevenue+" and (PAID_IN-PAID_OUT) < "+maxRevenue;

//    System.out.println(sql);
      rs = DAL.executeQueries(sql);
      if (rs.next()){
        count = rs.getInt("numtrans");
      }
      rs.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }

    return count;
  }
  void playSound(String soundFile){
    try {
            // From file
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(soundFile));

            // From URL
//          stream = AudioSystem.getAudioInputStream(new URL("http://hostname/audiofile"));

            // At present, ALAW and ULAW encodings must be converted
            // to PCM_SIGNED before it can be played
            AudioFormat format = stream.getFormat();
            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                format = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        format.getSampleSizeInBits()*2,
                        format.getChannels(),
                        format.getFrameSize()*2,
                        format.getFrameRate(),
                        true);        // big endian
                stream = AudioSystem.getAudioInputStream(format, stream);
            }

            // Create line
            SourceDataLine.Info info = new DataLine.Info(
                SourceDataLine.class, stream.getFormat(),
                ((int)stream.getFrameLength()*format.getFrameSize()));
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(stream.getFormat());
            line.start();

            // Continuously read and play chunks of audio
            int numRead = 0;
            byte[] buf = new byte[line.getBufferSize()];
            while ((numRead = stream.read(buf, 0, buf.length)) >= 0) {
                int offset = 0;
                while (offset < numRead) {
                    offset += line.write(buf, offset, numRead-offset);
                }
            }
            line.drain();
            line.stop();

        } catch (IOException e) {
        } catch (LineUnavailableException e) {
        } catch (UnsupportedAudioFileException e) {
        }
  }
    //======================== End Gift Promo Code ===================//
}
class PanelPayment_btn500000_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn500000_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn500000_actionPerformed(e);
  }
}

class PanelPayment_btn100000_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn100000_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn100000_actionPerformed(e);
  }
}

class PanelPayment_btn50000_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn50000_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn50000_actionPerformed(e);
  }
}

class PanelPayment_btn20000_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn20000_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn20000_actionPerformed(e);
  }
}

class PanelPayment_btn10000_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn10000_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn10000_actionPerformed(e);
  }
}

class PanelPayment_btn5000_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn5000_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn5000_actionPerformed(e);
  }
}

class PanelPayment_btn2000_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn2000_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn2000_actionPerformed(e);
  }
}

class PanelPayment_btn1000_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn1000_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn1000_actionPerformed(e);
  }
}

class PanelPayment_btn500_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn500_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn500_actionPerformed(e);
  }
}

class PanelPayment_btn200_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btn200_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btn200_actionPerformed(e);
  }
}

class PanelPayment_btnEnter_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btnEnter_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnEnter_actionPerformed(e);
  }
}

class PanelPayment_btnFixCharge_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPayment adaptee;
  PanelPayment_btnFixCharge_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnFixCharge_actionPerformed(e);
  }
}

//class PanelPayment_jButton1_actionAdapter
//    implements java.awt.event.ActionListener {
//  private PanelPayment adaptee;
//  PanelPayment_jButton1_actionAdapter(PanelPayment adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.jButton1_actionPerformed(e);
//  }
//}

//class PanelPayment_jButton2_actionAdapter
//    implements java.awt.event.ActionListener {
//  private PanelPayment adaptee;
//  PanelPayment_jButton2_actionAdapter(PanelPayment adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.jButton2_actionPerformed(e);
//  }
//}

//class KeyAction extends AbstractAction {
//
//   private Integer identifier = null;
//
//   public KeyAction(Integer identifier) {
//      this.identifier = identifier;
//   }
//
//   /**
//    * Invoked when an action occurs.
//    */
//   public void actionPerformed(ActionEvent e) {
//       if (identifier.intValue()==KeyEvent.VK_F1){
//           btnCreditCard.doClick();
//       }else if (identifier.intValue()==KeyEvent.VK_F2){
//           btnCoupon.doClick();
//       }else if (identifier.intValue()==KeyEvent.VK_F3){
//           btnCash.doClick();
//       }else if (identifier.intValue()==KeyEvent.VK_F4){
//           btnForeignCash.doClick();
//       }else if (identifier.intValue()==KeyEvent.VK_F5){
//           btnPostTXN.doClick();
//       }else if (identifier.intValue()==KeyEvent.VK_F6){
//           btnDelPayment.doClick();
//       }else if (identifier.intValue()==KeyEvent.VK_F7){
//           btnPrintReceipt.doClick();
//       }else if (identifier.intValue()==KeyEvent.VK_F8 || identifier.intValue() == KeyEvent.VK_ESCAPE){
//           btnBack.doClick();
//       }
//   }
//}

class PanelPayment_btnCreditCard_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPayment adaptee;

  PanelPayment_btnCreditCard_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCreditCard_actionPerformed(e);
  }
}

class PanelPayment_btnCash_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPayment adaptee;

  PanelPayment_btnCash_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCash_actionPerformed(e);
  }
}

class PanelPayment_btnPostTXN_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPayment adaptee;

  PanelPayment_btnPostTXN_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPostTXN_actionPerformed(e);
  }
}

class PanelPayment_btnDelPayment_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPayment adaptee;

  PanelPayment_btnDelPayment_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelPayment_actionPerformed(e);
  }
}

//class PanelPayment_btnPrintReceipt_actionAdapter
//    implements java.awt.event.ActionListener {
//  PanelPayment adaptee;
//
//  PanelPayment_btnPrintReceipt_actionAdapter(PanelPayment adaptee) {
//    this.adaptee = adaptee;
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnPrintReceipt_actionPerformed(e);
//  }
//}

class PanelPayment_btnBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPayment adaptee;

  PanelPayment_btnBack_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelPayment_btnCoupon_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPayment adaptee;

  PanelPayment_btnCoupon_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCoupon_actionPerformed(e);
  }
}

class PanelPayment_btnForeignCash_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPayment adaptee;

  PanelPayment_btnForeignCash_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnForeignCash_actionPerformed(e);
  }
}

class PanelPayment_btnCancelTransaction_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPayment adaptee;

  PanelPayment_btnCancelTransaction_actionAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancelTransaction_actionPerformed(e);
  }
}

class PanelPayment_tblPayment_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPayment adaptee;

  PanelPayment_tblPayment_keyAdapter(PanelPayment adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.tblPayment_keyPressed(e);
  }
}
