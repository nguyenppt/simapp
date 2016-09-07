package pos;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelRegisterClosedOut extends JPanel {
  DataAccessLayer DAL;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  FrameMain frmMain;
  boolean flagSetHotkey = true;
  Statement stmt = null;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BJPanel jPanel7 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel8 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;
  TitledBorder titledBorder4;
  JTextField txtAmountD = new JTextField();
  JTextField txtSystemD = new JTextField();
  JLabel lblDiscoverer = new JLabel();
  JTextField txtAmountVM = new JTextField();
  JTextField txtSystemVM = new JTextField();
  JLabel lblVMCard = new JLabel();
  JTextField txtAmountAE = new JTextField();
  JTextField txtSystemAE = new JTextField();
  JLabel lblAmericanExpress = new JLabel();
  BorderLayout borderLayout3 = new BorderLayout();
  JTextField txtAmountCheck = new JTextField();
  JTextField txtSystemCheck = new JTextField();
  JLabel lblCheck = new JLabel();
  JTextField txtAmountCashEUR = new JTextField();
  JTextField txtSystemCashEUR = new JTextField();
  JLabel lblCashEUR = new JLabel();
  JTextField txtAmountCashUSD = new JTextField();
  JTextField txtSystemCashUSD = new JTextField();
  JLabel lblCashUSD = new JLabel();
  JTextField txtAmountCashVND = new JTextField();//user input
  JTextField txtSystemCashVND = new JTextField();//system auto calculate
  JLabel lblCashVND = new JLabel();
  BorderLayout borderLayout4 = new BorderLayout();
  BJPanel jPanel9 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel10 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JTextField txtRecommendDeposit = new JTextField();
  JLabel lblRecommendDeposit = new JLabel();
  JTextField txtDifference = new JTextField();
  JLabel lblDifference = new JLabel();
  JTextField txtAmountPlusDF = new JTextField();
  JLabel lblAmountPlusDF = new JLabel();
  JTextField txtSystemPlusDF = new JTextField();
  JLabel lblSystemPlusDF = new JLabel();
  JTextField txtCurrentDawFund = new JTextField();
  JLabel lblCurrentDrawFund = new JLabel();
  JComboBox cboExchangeRate = new JComboBox();
  BJButton btnVerifiedDeposit = new BJButton();
  BJButton btnClearTotal = new BJButton();
  BJButton btnCancel = new BJButton();
  BJButton btnChecking = new BJButton();
  double exchangeRateUSD = 1;
  double exchangeRateEUR = 1;
  double exchangeRateVND = 1;
  double CashVN=0;
  double CashUSD=0;
  double CashEUR=0;
  double amountTotal=0;
  double difference=0;
  double totalDeposit = 0;
  double totalSales = 0;

  public PanelRegisterClosedOut(FrameMain frmMain) {
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
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),lang.getString("CreditCard"));
    titledBorder1.setTitleFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),lang.getString("Cash"));
    titledBorder2.setTitleFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),lang.getString("CurrentStepDetermineDeposit"),TitledBorder.CENTER,TitledBorder.CENTER);
    titledBorder3.setTitleFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),lang.getString("TodayExchangeRate"),TitledBorder.CENTER,TitledBorder.CENTER);
    titledBorder4.setTitleFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    this.setPreferredSize(new Dimension(600, 600));
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setPreferredSize(new Dimension(600, 50));
    jPanel2.setPreferredSize(new Dimension(600, 370));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setBackground(new Color(121, 152, 198));
    jPanel3.setPreferredSize(new Dimension(600, 130));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(110, 20));
    jLabel1.setText(lang.getString("Amount"));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(110, 20));
    jLabel2.setText(lang.getString("SystemTotal"));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(110, 20));
    jLabel3.setText(lang.getString("MediaType"));
    jPanel4.setBackground(new Color(121, 152, 198));
    jPanel4.setPreferredSize(new Dimension(230, 50));
    jPanel5.setPreferredSize(new Dimension(330, 370));
    jPanel5.setLayout(borderLayout3);
    jPanel6.setPreferredSize(new Dimension(270, 370));
    jPanel6.setLayout(borderLayout4);
    jPanel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel8.setBorder(titledBorder1);
    jPanel8.setPreferredSize(new Dimension(330, 145));
    jPanel7.setBorder(titledBorder2);
    jPanel7.setPreferredSize(new Dimension(330, 225));
    txtAmountD.setEnabled(true);
    txtAmountD.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAmountD.setPreferredSize(new Dimension(110, 25));
    txtAmountD.setEditable(false);
    txtSystemD.setBackground(new Color(212, 208, 200));
    txtSystemD.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSystemD.setPreferredSize(new Dimension(110, 25));
    txtSystemD.setEditable(false);
    txtSystemD.setText("");
    lblDiscoverer.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblDiscoverer.setPreferredSize(new Dimension(110, 25));
    lblDiscoverer.setText(lang.getString("Discoverer") +":");
    txtAmountVM.setBackground(new Color(212, 208, 200));
    txtAmountVM.setEnabled(true);
    txtAmountVM.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAmountVM.setDoubleBuffered(false);
    txtAmountVM.setPreferredSize(new Dimension(110, 25));
    txtAmountVM.setEditable(false);
    txtAmountVM.setText("");
    txtSystemVM.setBackground(new Color(212, 208, 200));
    txtSystemVM.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSystemVM.setPreferredSize(new Dimension(110, 25));
    txtSystemVM.setEditable(false);
    txtSystemVM.setText("");
    lblVMCard.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblVMCard.setPreferredSize(new Dimension(110, 25));
    lblVMCard.setText("Visa/Master Card:");
    txtAmountAE.setEnabled(true);
    txtAmountAE.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAmountAE.setPreferredSize(new Dimension(110, 25));
    txtAmountAE.setEditable(false);
    txtAmountAE.setText("");
    txtSystemAE.setBackground(new Color(212, 208, 200));
    txtSystemAE.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSystemAE.setPreferredSize(new Dimension(110, 25));
    txtSystemAE.setEditable(false);
    txtSystemAE.setText("");
    lblAmericanExpress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAmericanExpress.setPreferredSize(new Dimension(110, 25));
    lblAmericanExpress.setText(lang.getString("AmericanExpress") + ":");
    txtAmountCheck.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAmountCheck.setPreferredSize(new Dimension(110, 25));
    txtAmountCheck.setEditable(false);
    txtAmountCheck.setText("");
    txtSystemCheck.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSystemCheck.setPreferredSize(new Dimension(110, 25));
    txtSystemCheck.setEditable(false);
    txtSystemCheck.setText("");
    lblCheck.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCheck.setPreferredSize(new Dimension(110, 25));
    lblCheck.setText(lang.getString("Cheque")+":");
    txtAmountCashEUR.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAmountCashEUR.setNextFocusableComponent(txtRecommendDeposit);
    txtAmountCashEUR.setPreferredSize(new Dimension(110, 25));
    txtAmountCashEUR.setText("");
    txtAmountCashEUR.addKeyListener(new PanelRegisterClosedOut_txtAmountCashEUR_keyAdapter(this));
    txtAmountCashEUR.addFocusListener(new PanelRegisterClosedOut_txtAmountCashEUR_focusAdapter(this));
    txtSystemCashEUR.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSystemCashEUR.setPreferredSize(new Dimension(110, 25));
    txtSystemCashEUR.setEditable(false);
    txtSystemCashEUR.setText("");
    lblCashEUR.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCashEUR.setPreferredSize(new Dimension(110, 25));
    lblCashEUR.setText(lang.getString("Cash") + " (EUR):");
    txtSystemCashUSD.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSystemCashUSD.setPreferredSize(new Dimension(110, 25));
    txtSystemCashUSD.setEditable(false);
    txtSystemCashUSD.setText("");
    lblCashUSD.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCashUSD.setPreferredSize(new Dimension(110, 25));
    lblCashUSD.setText(lang.getString("Cash") + " (USD):");
    txtAmountCashVND.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAmountCashVND.setNextFocusableComponent(txtAmountCashUSD);
    txtAmountCashVND.setPreferredSize(new Dimension(110, 25));
    txtAmountCashVND.setText("");
    txtAmountCashVND.addKeyListener(new PanelRegisterClosedOut_txtAmountCashVND_keyAdapter(this));
    txtAmountCashVND.addFocusListener(new PanelRegisterClosedOut_txtAmountCashVND_focusAdapter(this));
    txtSystemCashVND.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSystemCashVND.setPreferredSize(new Dimension(110, 25));
    txtSystemCashVND.setEditable(false);
    txtSystemCashVND.setText("");
    lblCashVND.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCashVND.setPreferredSize(new Dimension(110, 25));
    lblCashVND.setText(lang.getString("Cash") + " (VND):");
    txtAmountCashUSD.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAmountCashUSD.setNextFocusableComponent(txtAmountCashEUR);
    txtAmountCashUSD.setPreferredSize(new Dimension(110, 25));
    txtAmountCashUSD.setText("");
    txtAmountCashUSD.addKeyListener(new PanelRegisterClosedOut_txtAmountCashUSD_keyAdapter(this));
    txtAmountCashUSD.addFocusListener(new PanelRegisterClosedOut_txtAmountCashUSD_focusAdapter(this));
    jPanel9.setBorder(titledBorder3);
    jPanel9.setPreferredSize(new Dimension(270, 270));
    jPanel10.setBorder(titledBorder4);
    jPanel10.setPreferredSize(new Dimension(270, 100));
    txtRecommendDeposit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtRecommendDeposit.setPreferredSize(new Dimension(110, 25));
    txtRecommendDeposit.setText("");
    txtRecommendDeposit.addFocusListener(new PanelRegisterClosedOut_txtRecommendDeposit_focusAdapter(this));
    txtRecommendDeposit.addKeyListener(new PanelRegisterClosedOut_txtRecommendDeposit_keyAdapter(this));
    lblRecommendDeposit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRecommendDeposit.setPreferredSize(new Dimension(135, 25));
    lblRecommendDeposit.setText(lang.getString("RecommendDeposit") + ":");
    txtDifference.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDifference.setPreferredSize(new Dimension(110, 25));
    txtDifference.setEditable(false);
    txtDifference.setText("");
    lblDifference.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblDifference.setPreferredSize(new Dimension(135, 25));
    lblDifference.setText(lang.getString("Difference") + ":");
    txtAmountPlusDF.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAmountPlusDF.setPreferredSize(new Dimension(110, 25));
    txtAmountPlusDF.setEditable(false);
    txtAmountPlusDF.setText("");
    lblAmountPlusDF.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAmountPlusDF.setPreferredSize(new Dimension(135, 25));
    lblAmountPlusDF.setToolTipText("");
    lblAmountPlusDF.setText(lang.getString("Fact") + ":");
    txtSystemPlusDF.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSystemPlusDF.setPreferredSize(new Dimension(110, 25));
    txtSystemPlusDF.setEditable(false);
    txtSystemPlusDF.setText("");
    lblSystemPlusDF.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSystemPlusDF.setPreferredSize(new Dimension(135, 25));
    lblSystemPlusDF.setVerifyInputWhenFocusTarget(true);
    lblSystemPlusDF.setText(lang.getString("System") + ":");
    txtCurrentDawFund.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCurrentDawFund.setPreferredSize(new Dimension(110, 25));
    txtCurrentDawFund.setEditable(false);
    txtCurrentDawFund.setText("");
    lblCurrentDrawFund.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCurrentDrawFund.setPreferredSize(new Dimension(135, 25));
    lblCurrentDrawFund.setText(lang.getString("CurrentDrawFund") + ":");
    cboExchangeRate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboExchangeRate.setPreferredSize(new Dimension(200, 25));
    btnVerifiedDeposit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnVerifiedDeposit.setText(lang.getString("VerifiedDeposit") + " (F1)");
    btnVerifiedDeposit.addActionListener(new PanelRegisterClosedOut_btnVerifiedDeposit_actionAdapter(this));
    btnVerifiedDeposit.addHierarchyBoundsListener(new PanelRegisterClosedOut_btnVerifiedDeposit_hierarchyBoundsAdapter(this));
    btnClearTotal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnClearTotal.setText(lang.getString("ClearTotal") + " (F7)");
    btnClearTotal.addActionListener(new PanelRegisterClosedOut_btnClearTotal_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setText("<html><center><br><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                       "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                       "ml>");
    btnCancel.addActionListener(new PanelRegisterClosedOut_btnCancel_actionAdapter(this));
    btnChecking.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnChecking.setText("<html><center><b>"+lang.getString("CheckCloseOut") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F9</html>");
    btnChecking.addActionListener(new PanelRegisterClosedOut_btnChecking_actionAdapter(this));
    this.add(jPanel1,  BorderLayout.NORTH);
    jPanel1.add(jLabel3, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jPanel4, null);
    this.add(jPanel2,  BorderLayout.CENTER);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel5.add(jPanel8,  BorderLayout.NORTH);
    jPanel8.add(lblAmericanExpress, null);
    jPanel8.add(txtSystemAE, null);
    jPanel8.add(txtAmountAE, null);
    jPanel8.add(lblVMCard, null);
    jPanel8.add(txtSystemVM, null);
    jPanel8.add(txtAmountVM, null);
    jPanel8.add(lblDiscoverer, null);
    jPanel8.add(txtSystemD, null);
    jPanel8.add(txtAmountD, null);
    jPanel5.add(jPanel7, BorderLayout.CENTER);
    jPanel7.add(lblCashVND, null);
    jPanel7.add(txtSystemCashVND, null);
    jPanel7.add(txtAmountCashVND, null);
    jPanel7.add(lblCashUSD, null);
    jPanel7.add(txtSystemCashUSD, null);
    jPanel7.add(txtAmountCashUSD, null);
    jPanel7.add(lblCashEUR, null);
    jPanel7.add(txtSystemCashEUR, null);
    jPanel7.add(txtAmountCashEUR, null);
    jPanel7.add(lblCheck, null);
    jPanel7.add(txtSystemCheck, null);
    jPanel7.add(txtAmountCheck, null);
    jPanel2.add(jPanel6,  BorderLayout.EAST);
    jPanel6.add(jPanel9, BorderLayout.CENTER);
    jPanel9.add(lblCurrentDrawFund, null);
    jPanel9.add(txtCurrentDawFund, null);
    jPanel9.add(lblSystemPlusDF, null);
    jPanel9.add(txtSystemPlusDF, null);
    jPanel9.add(lblAmountPlusDF, null);
    jPanel9.add(txtAmountPlusDF, null);
    jPanel9.add(lblDifference, null);
    jPanel9.add(txtDifference, null);
    jPanel9.add(lblRecommendDeposit, null);
    jPanel9.add(txtRecommendDeposit, null);
    jPanel6.add(jPanel10,  BorderLayout.SOUTH);
    jPanel10.add(cboExchangeRate, null);
  }

  //getDataCash
  ResultSet getDataCredit(String tender_desc,Statement stmt){
    ResultSet rs = null;
    String sql = "";
    if (tender_desc.equalsIgnoreCase("Master / Visa")){
      sql="select sum(paid_in) - sum(paid_out) credit_card " +
        " from btm_pos_paid_in_out p, btm_pos_tender_type t " +
        " where p.payment_type = t.TENDER_ID " +
        " and (upper(t.TENDER_DESC) = upper('Master') or upper(t.TENDER_DESC) = upper('Visa')) and in_out_flag=0" +
        " order by trunc(trans_date)";

    }else{
      sql = "select sum(paid_in) - sum(paid_out) credit_card " +
          " from btm_pos_paid_in_out p, btm_pos_tender_type t " +
          " where p.payment_type = t.TENDER_ID " +
          " and upper(t.TENDER_DESC) = upper('" + tender_desc + "') and in_out_flag=0" +
          " order by trunc(trans_date)" ;
    }
//    System.out.println(sql);
    try{
      rs = DAL.executeQueries(sql,stmt);
    }
    catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  //getExchangeRate
  ResultSet getExchangeRate(Statement stmt){
    ResultSet rs = null;
    String sql = "select curr_id,exchange_rate from btm_pos_exchange_rate where (CURR_ID = 'USD' and WORKDAY = (select max ( WORKDAY ) from btm_pos_exchange_rate where CURR_ID = 'USD')) or (CURR_ID = 'EUR' and WORKDAY = (select max ( WORKDAY ) from btm_pos_exchange_rate where CURR_ID = 'EUR')) order by WORKDAY ASC";
//    System.out.println(sql);
    try{

      rs = DAL.executeScrollQueries(sql,stmt);
    }
    catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
}
    return rs;
  }
  //show credit card
  void showCreditCard(){
    try{
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = getDataCredit("American Express",stmt);
      if (rs.next()){
        totalSales = totalSales + rs.getDouble("credit_card");
        txtSystemAE.setText(ut.formatNumber("" + rs.getDouble("credit_card")));
        txtAmountAE.setText(ut.formatNumber("" + rs.getDouble("credit_card")));
      }else{
        txtSystemAE.setText(ut.formatNumber(""));
        txtAmountAE.setText(ut.formatNumber(""));
      }
      rs = null;
//      stmt = DAL.getConnection().createStatement();
      rs = getDataCredit("Master / Visa",stmt);
      if (rs.next()){
        totalSales = totalSales + rs.getDouble("credit_card");
        txtSystemVM.setText(ut.formatNumber("" + rs.getDouble("credit_card")));
        txtAmountVM.setText(ut.formatNumber("" + rs.getDouble("credit_card")));
      }else{
        txtSystemVM.setText(ut.formatNumber(""));
        txtAmountVM.setText(ut.formatNumber(""));
      }
      rs=null;
//      stmt = DAL.getConnection().createStatement();
      rs = getDataCredit("Discover",stmt);
      if (rs.next()){
        totalSales = totalSales + rs.getDouble("credit_card");
        txtSystemD.setText(ut.formatNumber("" + rs.getDouble("credit_card")));
        txtAmountD.setText(ut.formatNumber("" + rs.getDouble("credit_card")));
      }else{
        txtSystemD.setText(ut.formatNumber(""));
        txtAmountD.setText(ut.formatNumber(""));
      }
      stmt.close();
    }catch (Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  //show exchange rate
  void showExchangeRate(){
    try{
      //show exchange rate for USD
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = getExchangeRate(stmt);
      rs.beforeFirst();
      while (rs.next()){
        if (rs.getString("curr_id").equalsIgnoreCase("USD")){
          exchangeRateUSD = rs.getDouble("exchange_rate");
        }else if (rs.getString("curr_id").equalsIgnoreCase("VND")){
          exchangeRateVND = rs.getDouble("exchange_rate");
        }else if (rs.getString("curr_id").equalsIgnoreCase("EUR")){
          exchangeRateEUR = rs.getDouble("exchange_rate");
        }
      }
      stmt.close();
    }catch (Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    cboExchangeRate.removeAllItems();
    if (DAL.getCurrencyID().equalsIgnoreCase("VND")) {
      cboExchangeRate.addItem("1 (USD) = " + exchangeRateUSD + " (VND)");
      cboExchangeRate.addItem("1 (EUR) = " + exchangeRateEUR + " (VND)");
    }else{ //set value in the case Currency is difference to VND
     cboExchangeRate.addItem("Default Currency is VND");
    }
  }
  //calculate cash
 double calculateCash(String Currency){
      ResultSet rs = null;
      Statement stmt=null;
      String SQL = "";
      double PaidIn=0;
      double PaidOut=0;
      double Cash=0;
      try{
        SQL = "  select sum(paid_in) CASH_IN  "
            + "  from btm_pos_paid_in_out  "
            + "  where in_out_flag = 0 and EMP_ID='"+DAL.getEmpPosID1()+"' "
            + "  and PAID_IN_CURR_ID='"+Currency+"' and PAYMENT_TYPE='1' ";
//        System.out.println(SQL);
        stmt=DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs = DAL.executeQueries(SQL,stmt);
        if (rs.next()){
          PaidIn = rs.getDouble("CASH_IN");
        }
        SQL = "  select sum(paid_out) CASH_OUT  "
            + "  from btm_pos_paid_in_out  "
            + "  where in_out_flag = 0 and EMP_ID='"+DAL.getEmpPosID1()+"' "
            + "  and PAID_OUT_CURR_ID='"+Currency+"' and PAYMENT_TYPE='1' ";
//        System.out.println(SQL);
        rs = DAL.executeQueries(SQL);
        if (rs.next()){
          PaidOut = rs.getDouble("CASH_OUT");
        }
        Cash = PaidIn - PaidOut;
        stmt.close();
        rs.close();
      }
      catch(Exception e){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
       return Cash;
  }

  //show current draw fund
  void showCurrentDrawFund(){
    double systemTotal = 0;
    try{
      ResultSet rs = null;
      String sql =" select draw_fund from btm_pos_store_fund"
                + " where STORE_ID = '"+DAL.getStoreID().trim()+"'"
                + " and total_fund = 0 and CASHIER_ID ='" + DAL.getEmpPosID1() + "'"
                + " order by workday desc";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
//        totalDeposit = totalDeposit + rs.getDouble("draw_fund");
//        totalSales = totalSales + rs.getDouble("draw_fund");
        txtCurrentDawFund.setText(ut.formatNumber("" + rs.getDouble("draw_fund")));
        systemTotal =
          ut.convertToDouble(txtSystemAE.getText()) + //American Express Credit Card
          ut.convertToDouble(txtSystemVM.getText()) + //Visa/Master Credit Card
          ut.convertToDouble(txtSystemD.getText()) + //Discoverer Credit Card
          ut.convertToDouble(txtSystemCashVND.getText())*exchangeRateVND +
          ut.convertToDouble(txtSystemCashUSD.getText())*exchangeRateUSD +
          ut.convertToDouble(txtSystemCashEUR.getText())*exchangeRateEUR +
          ut.convertToDouble(txtSystemCheck.getText()) + //cheque if it is available for using
          ut.convertToDouble(txtCurrentDawFund.getText());
        txtSystemPlusDF.setText(ut.formatNumber("" + systemTotal));
      }else{
        frmMain.dispose();
        AppPos appPos = new AppPos();
      }
      stmt.close();
      rs.close();
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  //show data into System Total
  void showData(){
    showExchangeRate();
    showCreditCard();

    CashVN = calculateCash(DAL.getCurrencyID());
    txtSystemCashVND.setText(ut.formatNumber("" + CashVN));
    CashUSD = calculateCash("USD");
    txtSystemCashUSD.setText(ut.formatNumber("" + CashUSD));
    CashEUR = calculateCash("EUR");
    txtSystemCashEUR.setText(ut.formatNumber("" + CashEUR));

    txtAmountCashVND.setText(ut.formatNumber(txtAmountCashVND.getText()));
    txtAmountCashUSD.setText(ut.formatNumber(txtAmountCashUSD.getText()));
    txtAmountCashEUR.setText(ut.formatNumber(txtAmountCashEUR.getText()));

    //Set default value 0 for cheque
    txtSystemCheck.setText(ut.formatNumber(""));
    txtAmountCheck.setText(ut.formatNumber(""));

    txtAmountCashVND.selectAll();
    txtAmountCashVND.requestFocus(true);

    showCurrentDrawFund();
    addValueIntoAmt();
    setUnhideText(false);
    btnChecking.setText("<html><center><b>"+lang.getString("CheckCloseOut")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F9</html>");
  }
  void setUnhideText(boolean flag){
    setEnabledText(flag);
    if(!flag){
      setColorText(txtSystemCashVND.getBackground());
    }
  }
  void setEnabledText(boolean flag){
    txtSystemCashVND.setEnabled(flag);
    txtSystemCashUSD.setEnabled(flag);
    txtSystemCashEUR.setEnabled(flag);
    txtSystemPlusDF.setEnabled(flag);
    txtDifference.setEnabled(flag);
  }
  void setColorText(Color clr){
    txtSystemCashVND.setDisabledTextColor(clr);
    txtSystemCashUSD.setDisabledTextColor(clr);
    txtSystemCashEUR.setDisabledTextColor(clr);
    txtSystemPlusDF.setDisabledTextColor(clr);
    txtDifference.setDisabledTextColor(clr);
  }
  //show verified button
  void showButtonVerified(){
    frmMain.showButton(btnVerifiedDeposit);
    frmMain.showButton(btnClearTotal);
    frmMain.showButton(btnCancel);
//    frmMain.showButton(btnChecking);
  }
  //Clear
  void clearTotal(){
    txtAmountCashEUR.setText(ut.formatNumber(""));
    txtAmountCashUSD.setText(ut.formatNumber(""));
    txtAmountCashVND.setText(ut.formatNumber(""));
    txtAmountPlusDF.setText("" + ut.convertToDouble(txtCurrentDawFund.getText()));
    txtDifference.setText("");
    txtRecommendDeposit.setText("");
  }

  void addValueIntoAmt(){

    amountTotal =
        ut.convertToDouble(txtAmountAE.getText()) +
        ut.convertToDouble(txtAmountD.getText()) +
        ut.convertToDouble(txtAmountVM.getText()) +
        ut.convertToDouble(txtCurrentDawFund.getText())  +
        ut.convertToDouble(txtAmountCashVND.getText())*exchangeRateVND +
        ut.convertToDouble(txtAmountCashUSD.getText())*exchangeRateUSD +
        ut.convertToDouble(txtAmountCashEUR.getText())*exchangeRateEUR +
        ut.convertToDouble(txtAmountCheck.getText());

//difference will be showed negative if amountTotal < SystemCaculate
    difference = amountTotal - ut.convertToDouble(txtSystemPlusDF.getText());

    txtAmountPlusDF.setText(ut.formatNumber("" + amountTotal));
    txtDifference.setText(ut.formatNumber("" + difference));
//      recommendDeposit = amountTotal - ut.convertToDouble(txtCurrentDawFund.getText());
//      txtRecommendDeposit.setText(ut.formatNumber("" + recommendDeposit));

  }
  void eliminateValueInAmt(double value){
    amountTotal=amountTotal-value;
    //difference will be showed negative if amountTotal < SystemCaculate
    difference = amountTotal - ut.convertToDouble(txtSystemPlusDF.getText());

    txtAmountPlusDF.setText(ut.formatNumber("" + amountTotal));
    txtDifference.setText(ut.formatNumber("" + difference));

  }

  void txtAmountCashVND_focusLost(FocusEvent e) {
    if (txtAmountCashVND.getText().equalsIgnoreCase("")){
      txtAmountCashVND.setText(ut.formatNumber(""));
    }
    if (txtAmountCashVND.getText().equalsIgnoreCase("-")){
      txtAmountCashVND.setText(ut.formatNumber(""));
      return;
    }
      addValueIntoAmt();
  }

  void txtAmountCashUSD_focusLost(FocusEvent e) {
    if (txtAmountCashUSD.getText().equalsIgnoreCase("")){
      txtAmountCashUSD.setText(ut.formatNumber(""));
    }
    if (txtAmountCashUSD.getText().equalsIgnoreCase("-")){
      txtAmountCashUSD.setText(ut.formatNumber(""));
      return;
    }
    addValueIntoAmt();
  }

  void txtAmountCashEUR_focusLost(FocusEvent e) {
    if (txtAmountCashEUR.getText().equalsIgnoreCase("")){
      txtAmountCashEUR.setText(ut.formatNumber(""));
    }
    if (txtAmountCashEUR.getText().equalsIgnoreCase("-")){
      txtAmountCashEUR.setText(ut.formatNumber(""));
      return;
    }
    addValueIntoAmt();
  }

  void btnClearTotal_actionPerformed(ActionEvent e) {
    clearTotal();
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    clearTotal();
//    frmMain.pnlMain.showAdminButton();
//    frmMain.setTitle("JPOS - ADMIN");
//    frmMain.showScreen(Constant.SCR_MAIN);
//    frmMain.removeButton();
//    frmMain.pnlMain.showAdminButton();
    frmMain.setTitle(lang.getString("TT003"));
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.pnlMain.requestFocusInWindow();
   //set flag for screen Main
    flagSetHotkey = true;
  }

  void btnChecking_actionPerformed(ActionEvent e){
    if(btnChecking.getText().equals("<html><center><b>"+lang.getString("CheckCloseOut") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F9</html>")){
      DialogConfirmUser dlgConfirm = new DialogConfirmUser(frmMain,lang.getString("TT018"), true, frmMain);
      dlgConfirm.setLocation(210, 170);
      dlgConfirm.setVisible(true);
      if (dlgConfirm.done) {
        setUnhideText(true);
        btnChecking.setText(lang.getString("Enabled")+" (F9)");
      }
    } else {
      setUnhideText(false);
      btnChecking.setText("<html><center><b>"+lang.getString("CheckCloseOut") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F9</html>");
    }
  }
  void txtAmountCashVND_keyReleased(KeyEvent e) {
//    if(txtAmountCashVND.getText().equals("") || e.getKeyChar()== e.VK_ENTER)
//      return;
//    String sTemp = txtAmountCashVND.getText();
//    if (!sTemp.equals("-")){
//      if (!ut.isDoubleString( (txtAmountCashVND.getText()))) {
//        ut.showMessage(frmMain, "Warning",
//                       "The Amount Cash (VND) must be numeric");
//        txtAmountCashVND.requestFocus(true);
//        return;
//      }
//    }
  }

  void txtAmountCashUSD_keyReleased(KeyEvent e) {
//    if(txtAmountCashUSD.getText().equals("") || e.getKeyChar()== e.VK_ENTER)
//      return;
//    if(!ut.isDoubleString((txtAmountCashUSD.getText())))
//    {
//      ut.showMessage(frmMain,"Warning","The Amount Cash (USD) must be numeric");
//      txtAmountCashUSD.requestFocus(true);
//      return;
//    }

  }

  void txtAmountCashEUR_keyReleased(KeyEvent e) {
//    if(txtAmountCashEUR.getText().equals("") || e.getKeyChar()== e.VK_ENTER)
//      return;
//    if(!ut.isDoubleString((txtAmountCashEUR.getText())))
//    {
//      ut.showMessage(frmMain,"Warning","The Amount Cash (EUR) must be numeric");
//      txtAmountCashEUR.requestFocus(true);
//      return;
//    }
  }

  void txtRecommendDeposit_keyReleased(KeyEvent e) {
//    if (txtRecommendDeposit.getText().equals("") ||
//        e.getKeyChar() == e.VK_ENTER)
//      return;
//    if (!txtRecommendDeposit.getText().equalsIgnoreCase("-")){
//      if (!ut.isDoubleString( (txtRecommendDeposit.getText()))) {
//        ut.showMessage(frmMain, "Warning",
//                       "The Recommend Deposit must be numeric");
//        txtRecommendDeposit.requestFocus(true);
//        return;
//      }
//    }
  }

  void btnVerifiedDeposit_ancestorMoved(HierarchyEvent e) {
  }

  void btnVerifiedDeposit_actionPerformed(ActionEvent e) {
    //----------------------------
    if (txtRecommendDeposit.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS097_DepositNotNull"));
      txtRecommendDeposit.requestFocus(true);
      return;
    }
    char cTemp[] = txtRecommendDeposit.getText().toCharArray();
    if (cTemp[0]=='-'){
     ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS098_DepositGreater0"));
     txtRecommendDeposit.requestFocus(true);
     return;
   }
    if (ut.convertToDouble(txtAmountPlusDF.getText()) < ut.convertToDouble(txtRecommendDeposit.getText())){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS099_NotEnoughCash"));
      txtRecommendDeposit.requestFocus(true);
      return;
    }

    String diff = txtDifference.getText();
    if (diff.equalsIgnoreCase("")){
      diff = "0";
    }
    try{
      String sql = "update btm_pos_store_fund set total_fund = '" + ut.convertToDouble(txtSystemPlusDF.getText()) +"'"
         + ", total_deposit=" + ut.convertToDouble(txtRecommendDeposit.getText())
         + ", total_diff=" + ut.convertToDouble(diff)
         + ", WORKDAY= to_Date('" + ut.getServerDateTime(DAL).substring(0,19) + "','" + ut.DATETIME_FORMAT_SQL+"')"
         + " where STORE_ID = '"+DAL.getStoreID()+ "'"
         + " and total_fund = 0 and CASHIER_ID ='" + DAL.getEmpPosID1() + "'"
         + " and WORKDAY = (select max(WORKDAY) from BTM_POS_STORE_FUND "
         + " where STORE_ID = '"+DAL.getStoreID()+ "' and total_fund = 0 "
         + " and CASHIER_ID ='" + DAL.getEmpPosID1() + "')";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      DAL.executeUpdate(sql,stmt);
      sql = "";
      sql = " update btm_pos_paid_in_out  set in_out_flag = 1 "
          + " where in_out_flag = 0 and EMP_ID = '"+DAL.getEmpPosID1()+"'";

//      System.out.println(sql);
      DAL.executeUpdate(sql,stmt);
      frmMain.dispose();
      stmt.close();
      AppPos appPos = new AppPos();
    }
    catch(Exception ex){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }
  }

  void txtAmountCashVND_focusGained(FocusEvent e) {
    double cashVND=0;
    if (!txtAmountCashVND.getText().equalsIgnoreCase("")){
      txtAmountCashVND.selectAll();
    }else{
      txtAmountCashVND.setText(ut.formatNumber(""));
    }
    cashVND=ut.convertToDouble(txtAmountCashVND.getText())*exchangeRateVND;
    eliminateValueInAmt(cashVND);
  }

  void txtAmountCashUSD_focusGained(FocusEvent e) {
    double cashUSD=0;
    if (!txtAmountCashUSD.getText().equalsIgnoreCase("")){
      txtAmountCashUSD.selectAll();
    }else{
      txtAmountCashUSD.setText(ut.formatNumber(""));
    }
    cashUSD=ut.convertToDouble(txtAmountCashUSD.getText())*exchangeRateUSD;
    eliminateValueInAmt(cashUSD);
  }

  void txtAmountCashEUR_focusGained(FocusEvent e) {
    double cashEUR=0;
    if (!txtAmountCashEUR.getText().equalsIgnoreCase("")){
      txtAmountCashEUR.selectAll();
    }else{
      txtAmountCashEUR.setText(ut.formatNumber(""));
    }
    cashEUR=ut.convertToDouble(txtAmountCashEUR.getText())*exchangeRateEUR;
    eliminateValueInAmt(cashEUR);
  }

  void txtRecommendDeposit_focusGained(FocusEvent e) {
    addValueIntoAmt();
    if (!txtRecommendDeposit.getText().equalsIgnoreCase("")){
      txtRecommendDeposit.setText("" +
                                  ut.convertToDouble(txtRecommendDeposit.getText()));
    }
  }

  void txtRecommendDeposit_focusLost(FocusEvent e) {
    if(txtRecommendDeposit.getText().equalsIgnoreCase("-")){
      txtRecommendDeposit.setText("");
      return;
    }
    if (!txtRecommendDeposit.getText().equalsIgnoreCase("")){
      double i = Double.parseDouble(txtRecommendDeposit.getText());
      if (i < 0) {
        txtRecommendDeposit.setText("");
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS098_DepositGreater0"));
        txtRecommendDeposit.requestFocus(true);
        return;
      }
      txtRecommendDeposit.setText(ut.formatNumber(txtRecommendDeposit.getText()));
    }
  }
  private void registerKeyboardActions() {
   /// set up the maps:
   InputMap inputMap =getInputMap(WHEN_IN_FOCUSED_WINDOW);
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

   // F7
   key = new Integer(KeyEvent.VK_F7);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), key);
   actionMap.put(key, new KeyAction(key));

   // F9
   key = new Integer(KeyEvent.VK_F9);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), key);
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
//       if( flagSetHotkey == true ){

         if (identifier.intValue() == KeyEvent.VK_F1) {
           btnVerifiedDeposit.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F7) {
           btnClearTotal.doClick();
         }
         else if (identifier.intValue()==KeyEvent.VK_F9){
//           btnChecking.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F12 ||
                  identifier.intValue() == KeyEvent.VK_ESCAPE) {
           btnCancel.doClick();
         }
//       }
   }
}

  void txtAmountCashVND_keyTyped(KeyEvent e) {
    ut.checkNumberUpdate(e,txtAmountCashVND.getText());
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtAmountCashUSD.requestFocus(true);
    }
  }

  void txtAmountCashUSD_keyTyped(KeyEvent e) {
    ut.checkNumberUpdate(e,txtAmountCashUSD.getText());
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtAmountCashEUR.requestFocus(true);
    }
  }

  void txtAmountCashEUR_keyTyped(KeyEvent e) {
    ut.checkNumberUpdate(e,txtAmountCashEUR.getText());
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtRecommendDeposit.requestFocus(true);
    }
  }

  void txtRecommendDeposit_keyTyped(KeyEvent e) {
//      if (!txtRecommendDeposit.getText().equals("-")){
//        ut.checkNumberUpdate(e, txtRecommendDeposit.getText());
//      }
//  //  }
    ut.checkNumberUpdate(e, txtRecommendDeposit.getText());
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
      btnVerifiedDeposit.requestFocus(true);
    }
  }
}

class PanelRegisterClosedOut_txtAmountCashVND_focusAdapter extends java.awt.event.FocusAdapter {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_txtAmountCashVND_focusAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtAmountCashVND_focusLost(e);
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtAmountCashVND_focusGained(e);
  }
}

class PanelRegisterClosedOut_txtAmountCashUSD_focusAdapter extends java.awt.event.FocusAdapter {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_txtAmountCashUSD_focusAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtAmountCashUSD_focusLost(e);
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtAmountCashUSD_focusGained(e);
  }
}

class PanelRegisterClosedOut_txtAmountCashEUR_focusAdapter extends java.awt.event.FocusAdapter {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_txtAmountCashEUR_focusAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtAmountCashEUR_focusLost(e);
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtAmountCashEUR_focusGained(e);
  }
}

class PanelRegisterClosedOut_btnClearTotal_actionAdapter implements java.awt.event.ActionListener {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_btnClearTotal_actionAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClearTotal_actionPerformed(e);
  }
}

class PanelRegisterClosedOut_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_btnCancel_actionAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelRegisterClosedOut_btnChecking_actionAdapter implements java.awt.event.ActionListener {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_btnChecking_actionAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnChecking_actionPerformed(e);
  }
}
class PanelRegisterClosedOut_txtAmountCashVND_keyAdapter extends java.awt.event.KeyAdapter {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_txtAmountCashVND_keyAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtAmountCashVND_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAmountCashVND_keyTyped(e);
  }
}

class PanelRegisterClosedOut_txtAmountCashUSD_keyAdapter extends java.awt.event.KeyAdapter {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_txtAmountCashUSD_keyAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtAmountCashUSD_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAmountCashUSD_keyTyped(e);
  }
}

class PanelRegisterClosedOut_txtAmountCashEUR_keyAdapter extends java.awt.event.KeyAdapter {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_txtAmountCashEUR_keyAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtAmountCashEUR_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAmountCashEUR_keyTyped(e);
  }
}

class PanelRegisterClosedOut_txtRecommendDeposit_keyAdapter extends java.awt.event.KeyAdapter {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_txtRecommendDeposit_keyAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtRecommendDeposit_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRecommendDeposit_keyTyped(e);
  }
}

class PanelRegisterClosedOut_btnVerifiedDeposit_hierarchyBoundsAdapter extends java.awt.event.HierarchyBoundsAdapter {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_btnVerifiedDeposit_hierarchyBoundsAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void ancestorMoved(HierarchyEvent e) {
    adaptee.btnVerifiedDeposit_ancestorMoved(e);
  }
}

class PanelRegisterClosedOut_btnVerifiedDeposit_actionAdapter implements java.awt.event.ActionListener {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_btnVerifiedDeposit_actionAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnVerifiedDeposit_actionPerformed(e);
  }
}

class PanelRegisterClosedOut_txtRecommendDeposit_focusAdapter extends java.awt.event.FocusAdapter {
  PanelRegisterClosedOut adaptee;

  PanelRegisterClosedOut_txtRecommendDeposit_focusAdapter(PanelRegisterClosedOut adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtRecommendDeposit_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtRecommendDeposit_focusLost(e);
  }
}

