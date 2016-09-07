package pos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import common.*;
import btm.swing.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author
 * @version 1.0
 */

public class PanelMain
    extends JPanel {
  // init constant to set hot key each screen in this panel
  public final int FLAG_SET_HOTKEY_SCR_MAIN = 1;
  public final int FLAG_SET_HOTKEY_SCR_ADMIN = 2;
  public final int FLAG_SET_HOTKEY_SCR_INV_MAN = 3;
  public final int FLAG_SET_HOTKEY_SCR_ADMIN_REPORT = 4;
  public final int FLAG_SET_HOTKEY_SCR_ADMIN_TRANS_MANAGEMENT = 5;
  public final int FLAG_SET_HOTKEY_SCR_INV_MANAGEMENT_TRANSFER = 6;
  public final int FLAG_SET_HOTKEY_SCR_INV_MANAGEMENT_RECEIPT = 7;
  public final int FLAG_SET_HOTKEY_SCR_INV_MANAGEMENT_DAMAGE_GOODS = 8;
  FrameMain frmMain;
  Utils ut = new Utils();
  DataAccessLayer DAL = new DataAccessLayer();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  ImageIcon logoImage;
  BJButton btnSaleExpress = new BJButton();
  BJButton btnAdmin = new BJButton();
  BJButton btnInvenManage = new BJButton();
  BJButton btnLogOff = new BJButton();
  BJButton btnReport = new BJButton();
  BJButton btnRegisterClose = new BJButton();
  BJButton btnTransManagement = new BJButton();
  BJButton btnVoidTrans = new BJButton();
  BJButton btnPaidIn = new BJButton();
  BJButton btnPaidOut = new BJButton();
  BJButton btnTransManagementBack = new BJButton();
  BJButton btnAdminBack = new BJButton();
  BJButton btnItemNetSale = new BJButton();
  BJButton btnTransNetSale = new BJButton();
  BJButton btnReportBack = new BJButton();
  BJButton btnInvStockCount = new BJButton();
  BJButton btnInvReceipt = new BJButton();
  BJButton btnReceiptItem = new BJButton();
  BJButton btnInvMagBack = new BJButton();
  BJButton btnTransferItem = new BJButton();
  BJButton btnTimer = new BJButton();
  BJButton btnRtnDmgGoodsItm = new BJButton();
  BJButton btnViewDmgGoodsItm = new BJButton();
  BJButton btnBackDmgGoodsItm = new BJButton();
  public int flagSetHotkey = FLAG_SET_HOTKEY_SCR_MAIN;
  BJButton btnViewTransfer = new BJButton();
  BJButton btnCustomer = new BJButton();
  BJButton btnReceipt = new BJButton();
  BJButton btnTransfer = new BJButton();
  BJButton btnRtnDmgGoods = new BJButton();
  BJButton btnTransferBack = new BJButton();
  BJButton btnReceiptBack = new BJButton(); //default

//Phuoc.nguyen
  BJButton btnExchangeRate = new BJButton();
  BJButton btnRunBatchJob = new BJButton();
//  BJButton btnExRateDone = new BJButton();
//  BJButton btnExRateAdd = new BJButton();
//  BJButton btnExRateDelete = new BJButton();
//  BJButton btnExRateCancel = new BJButton();

  public PanelMain(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      ;
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    registerKeyboardActions();
    logoImage = new ImageIcon("images//PosMain1.jpg");
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setIcon(logoImage);
    this.setLayout(borderLayout1);
    btnSaleExpress.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSaleExpress.setToolTipText(lang.getString("SaleExpress") + "(F1)");
    btnSaleExpress.setVerifyInputWhenFocusTarget(true);
    btnSaleExpress.setText(lang.getString("NewSales") + " (F1)");
    btnSaleExpress.addActionListener(new PanelMain_btnSaleExpress_actionAdapter(this));
    btnAdmin.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdmin.setToolTipText(lang.getString("Admin") + "(F2)");
    btnAdmin.setText(lang.getString("Admin") + "(F2)");
    btnAdmin.addActionListener(new PanelMain_btnAdmin_actionAdapter(this));
    btnInvenManage.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnInvenManage.setToolTipText(lang.getString("InvMgt") + "(F3)");
    btnInvenManage.setText("<html><center><b> "+ lang.getString("InvMgt") +" </b></center><&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
                           ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnInvenManage.addActionListener(new PanelMain_btnInvenManage_actionAdapter(this));
    btnLogOff.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnLogOff.setToolTipText(lang.getString("LogOff") + "(F10)");
    btnLogOff.setText("<html><center><b>"+lang.getString("LogOff")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F10</html>");
    btnLogOff.addActionListener(new PanelMain_btnLogOff_actionAdapter(this));
    btnReport.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnReport.setToolTipText(lang.getString("Report") +"(F1)");
    btnReport.setText(lang.getString("Report") +"(F1)");
    btnReport.addActionListener(new PanelMain_btnReport_actionAdapter(this));
    btnRegisterClose.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRegisterClose.setToolTipText(lang.getString("RegisterClosed") + "(F4)");
    btnRegisterClose.setText(lang.getString("RegisterClosed") + "(F4)");
    btnRegisterClose.addActionListener(new
                                       PanelMain_btnRegisterClose_actionAdapter(this));
    btnTransManagement.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnTransManagement.setToolTipText(lang.getString("TransactionManagement") + " (F2 )");
    btnTransManagement.setSelected(false);
    btnTransManagement.setText("<html><center><b>"+lang.getString("TransactionMan")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                               "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnTransManagement.addActionListener(new
        PanelMain_btnTransManagement_actionAdapter(this));
    btnVoidTrans.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnVoidTrans.setToolTipText(lang.getString("VoidTXN") + " (F1)");
    btnVoidTrans.setText("<html><center><b>"+lang.getString("VoidTXN")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                         "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnVoidTrans.addActionListener(new PanelMain_btnVoidTrans_actionAdapter(this));
    btnVoidTrans.setEnabled(false);
    btnPaidIn.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPaidIn.setToolTipText(lang.getString("PaidIn") + " (F2)");
    btnPaidIn.setText(lang.getString("PaidIn") + " (F2)");
    btnPaidIn.addActionListener(new PanelMain_btnPaidIn_actionAdapter(this));
    btnPaidOut.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPaidOut.setToolTipText(lang.getString("PaidOut") + " (F3)");
    btnPaidOut.setText(lang.getString("PaidOut") + " (F3)");
    btnPaidOut.addActionListener(new PanelMain_btnPaidOut_actionAdapter(this));
    btnPaidOut.addActionListener(new PanelMain_btnPaidOut_actionAdapter(this));

    btnTransManagementBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnTransManagementBack.setToolTipText(lang.getString("Back") + " (F12)");
    btnTransManagementBack.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnTransManagementBack.addActionListener(new
        PanelMain_btnTransManagementBack_actionAdapter(this));
    btnAdminBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdminBack.setToolTipText(lang.getString("Back") + "(F12 or ESC)");
    btnAdminBack.setText("<html><center><br><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                         "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</h" +
                         "tml>");
    btnAdminBack.addActionListener(new PanelMain_btnAdminBack_actionAdapter(this));
    btnItemNetSale.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnItemNetSale.setToolTipText(lang.getString("ItemNetSale") + "(F1)");
    btnItemNetSale.setText(lang.getString("ItemNetSale") + "(F1)");
    btnItemNetSale.addActionListener(new PanelMain_btnItemNetSale_actionAdapter(this));
    btnTransNetSale.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnTransNetSale.setToolTipText(lang.getString("TransNetSale") + "(F2)");
    btnTransNetSale.setText(lang.getString("TransNetSale") + "(F2)");
    btnTransNetSale.addActionListener(new
                                      PanelMain_btnTransNetSale_actionAdapter(this));
    btnReportBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnReportBack.setToolTipText(lang.getString("Back") + "(F12 or ESC)");
    btnReportBack.setText("<html><center><br><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                          "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                          "ml>");
    btnReportBack.addActionListener(new PanelMain_btnReportBack_actionAdapter(this));
    btnInvStockCount.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnInvStockCount.setToolTipText(lang.getString("StockCount") + "(F1)");
    btnInvStockCount.setText(lang.getString("StockCount") + "(F1)");
    btnInvStockCount.addActionListener(new
                                       PanelMain_btnInvStockCount_actionAdapter(this));
    btnInvReceipt.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnInvReceipt.setToolTipText(lang.getString("InvReceipt") + "(F1)");
    btnInvReceipt.setText("<html><center><b>"+lang.getString("InvReceipt")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
                          ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                          "p;F1</html>");
    btnInvReceipt.addActionListener(new PanelMain_btnInvReceipt_actionAdapter(this));
    btnReceiptItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnReceiptItem.setToolTipText(lang.getString("ReceiptItem") + "(F2)");
    btnReceiptItem.setText("<html><center><b>"+lang.getString("ReceiptItem")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                           "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
                           "sp;F2</html>");
    btnReceiptItem.addActionListener(new PanelMain_btnReceiptItem_actionAdapter(this));
    btnInvMagBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnInvMagBack.setToolTipText(lang.getString("Back")  +  "(F12 or ESC)");
    btnInvMagBack.setText("<html><center><br><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                          "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                          "ml>");
    btnInvMagBack.addActionListener(new PanelMain_btnInvMagBack_actionAdapter(this));
    btnTimer.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnTimer.setToolTipText(lang.getString("Timer") + "(F4)");
    btnTimer.setSelected(false);
    btnTimer.setText("<html><center><b>"+lang.getString("Timer")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
                     ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</" +
                     "html>");
    btnTimer.addActionListener(new PanelMain_btnTimer_actionAdapter(this));
    btnTransferItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnTransferItem.setToolTipText(lang.getString("TransferItem") + "(F1)");
    btnTransferItem.setText("<html><center><b>"+lang.getString("TransferItem")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
                            "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                            "bsp;F1</html>");
    btnTransferItem.addActionListener(new
                                      PanelMain_btnTransferItem_actionAdapter(this));
    btnViewTransfer.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnViewTransfer.setToolTipText(lang.getString("ViewTransfer") + "(F2)");
    btnViewTransfer.setActionCommand("Sale Express(F1)");
    btnViewTransfer.setText("<html><center><b>"+lang.getString("ViewTransfer")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                            "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                            "nbsp;F2</html>");
    btnViewTransfer.addActionListener(new
                                      PanelMain_btnViewTransfer_actionAdapter(this));
    btnViewTransfer.addActionListener(new
                                      PanelMain_btnViewTransfer_actionAdapter(this));
    /*btnCustomer.setText("<html><center><b>"+lang.getString("Customer")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
                        "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3" +
                        "</html>");*/
    //btnCustomer.addActionListener(new PanelMain_btnCustomer_actionAdapter(this));
    //btnCustomer.addActionListener(new PanelMain_btnCustomer_actionAdapter(this));
    //btnCustomer.setSelected(false);
    //btnCustomer.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    //btnCustomer.setToolTipText(lang.getString("Customer") + "(F3)");
    //btnCustomer.addActionListener(new PanelMain_btnCustomer_actionAdapter(this));
    btnReceipt.setToolTipText(lang.getString("Receipt") + " (F2)");
    btnReceipt.setText(lang.getString("Receipt") + " (F2)");
    btnReceipt.addActionListener(new PanelMain_btnReceipt_actionAdapter(this));
    btnTransfer.setToolTipText(lang.getString("Transfer") + " (F3)");
    btnTransfer.setText("<html><center><b>"+lang.getString("Transfer")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                        "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
                        "3</html>");
    btnTransfer.addActionListener(new PanelMain_btnTransfer_actionAdapter(this));
    btnTransferBack.setToolTipText(lang.getString("Back")+ " (F12)");
    btnTransferBack.setText("<html><center><br><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</h" +
                            "tml>");
    btnTransferBack.addActionListener(new
                                      PanelMain_btnTransferBack_actionAdapter(this));
    btnReceiptBack.setToolTipText(lang.getString("Back")+ " (F12)");
    btnReceiptBack.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnReceiptBack.addActionListener(new PanelMain_btnReceiptBack_actionAdapter(this));
    btnExchangeRate.addActionListener(new
                                      PanelMain_btnExchangeRate_actionAdapter(this));
    btnRunBatchJob.setToolTipText(lang.getString("RunBatchJob") + "(F5)");
    btnRunBatchJob.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRunBatchJob.setSelected(false);
    btnRunBatchJob.setText("<html><center><b>"+lang.getString("RunBatchJob")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                           "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
                           "sp;F5</html>");
    btnRtnDmgGoods.setText(lang.getString("DamageGoods") + " (F4)");
    btnRtnDmgGoods.addActionListener(new PanelMain_btnRtnDmgGoods_actionAdapter(this));
    btnRtnDmgGoodsItm.setToolTipText(lang.getString("ReturnDamageGoods") + " (F1)");
    btnRtnDmgGoodsItm.setText(
        "<html><b>"+lang.getString("ReturnDamageGoods")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnRtnDmgGoodsItm.addActionListener(new
        PanelMain_btnRtnDmgGoodsItm_actionAdapter(this));
    btnViewDmgGoodsItm.setToolTipText(lang.getString("ViewDmgGoods") + " (F2)");
    btnViewDmgGoodsItm.setText(
        "<html><b>"+lang.getString("ViewDmgGoods")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&n" +
        "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnViewDmgGoodsItm.addActionListener(new
        PanelMain_btnViewDmgGoodsItm_actionAdapter(this));
    btnBackDmgGoodsItm.setText("<html><center><br><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                               "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</h" +
                               "tml>");
    btnBackDmgGoodsItm.addActionListener(new
        PanelMain_btnBackDmgGoodsItm_actionAdapter(this));
    this.add(jLabel1, BorderLayout.NORTH);

    //Phuoc.nguyen
    btnExchangeRate.setText(lang.getString("ExchangeRate") + "(F3)");
    btnExchangeRate.setToolTipText(lang.getString("ExchangeRate") + "(F3)");
    btnExchangeRate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));

//    btnExRateDone.setText("Done (F1)");
//    btnExRateDone.setToolTipText("Done (F1)");
//    btnExRateDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//
//    btnExRateAdd.setText("Add (F2)");
//    btnExRateAdd.setToolTipText("Add (F2)");
//    btnExRateAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//
//    btnExRateDelete.setText("Delete (F3)");
//    btnExRateDelete.setToolTipText("Delete (F3)");
//    btnExRateDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//
//    btnExRateCancel.setText("Cancel (F4)");
//    btnExRateCancel.setToolTipText("Cancel (F4)");
//    btnExRateCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRunBatchJob.addActionListener(new PanelMain_btnRunBatchJob_actionAdapter(this));

  }

  //show button in Frame Main
  void showButton() {
    frmMain.showButton(btnSaleExpress);
    frmMain.showButton(btnAdmin);
//    frmMain.showButton(btnInvenManage);
    frmMain.showButton(btnExchangeRate);
    frmMain.showButton(btnRegisterClose);
    frmMain.showButton(btnLogOff);
  }

  //show button in Frame Main
  void showAdminButton() {
    frmMain.showButton(btnReport);
//    frmMain.showButton(btnRegisterClose);
    frmMain.showButton(btnTransManagement);
    //frmMain.showButton(btnCustomer);
//    frmMain.showButton(btnTimer);
    frmMain.showButton(btnRunBatchJob);
    frmMain.showButton(btnAdminBack);
    if(DataAccessLayer.getEmpPosID1().equals("0000000001007")||DataAccessLayer.getEmpPosID1().equals("0000000002004")||DataAccessLayer.getEmpPosID1().equals("0000000003")){
      btnRunBatchJob.setEnabled(true);
    }
    else{
      btnRunBatchJob.setEnabled(false);
    }


  }

  //show button in Transaction Management Button
  void showTransManagementButton() {
    frmMain.showButton(btnVoidTrans);
    frmMain.showButton(btnPaidIn);
    frmMain.showButton(btnPaidOut);
    frmMain.showButton(btnTransManagementBack);
  }

  //show button in Frame Main
  void showReportButton() {
    frmMain.showButton(btnItemNetSale);
    frmMain.showButton(btnTransNetSale);
    frmMain.showButton(btnReportBack);
  }

  //show button in Frame Main
  void showInvManageButton() {
    frmMain.showButton(btnInvStockCount);
    /*frmMain.showButton(btnReceipt);
         frmMain.showButton(btnTransfer);
         frmMain.showButton(btnRtnDmgGoods);*/
    frmMain.showButton(btnInvMagBack);
  }

  //show button Receipt Button
  void showReceiptButton() {
    frmMain.showButton(btnInvReceipt);
    frmMain.showButton(btnReceiptItem);
    frmMain.showButton(btnReceiptBack);
  }

  //show button Transfer Button
  void showTransferButton() {
    frmMain.showButton(btnTransferItem);
    frmMain.showButton(btnViewTransfer);
    frmMain.showButton(btnTransferBack);
  }

  //show return damage goods button
  void showDmgGoodsBtn() {
    frmMain.showButton(btnRtnDmgGoodsItm);
    frmMain.showButton(btnViewDmgGoodsItm);
    frmMain.showButton(btnBackDmgGoodsItm);
  }

  void btnSaleExpress_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.showScreen(Constant.SCR_NEW_SALE);
  }

  void btnAdmin_actionPerformed(ActionEvent e) {
    frmMain.removeButton();
    frmMain.setTitle(lang.getString("TT013"));
    showAdminButton();
    //set flag hotkey for sreen Admin
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_ADMIN;
  }

  void btnInvenManage_actionPerformed(ActionEvent e) {
    frmMain.removeButton();
    frmMain.setTitle(lang.getString("TT017"));
    showInvManageButton();
    //set flag for screen Inv Manage
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MAN;
  }

  void btnLogOff_actionPerformed(ActionEvent e) {
//    frmMain.dispose();
//    new AppPos();
    frmMain.removeButton();
    frmMain.showScreen(Constant.SCR_LOG_OFF);
    frmMain.setTitle(lang.getString("TT018"));
    frmMain.pnlLogOff.showButton();
    //Nguyen Pham Begin
    frmMain.pnlLogOff.txtOperatorID.requestFocus(true);
    //Nguyen Pham End
    //set flag for screen Main
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_MAIN;
  }

  void btnReport_actionPerformed(ActionEvent e) {
//Comment this on 11-11-2008
//    DialogConfirmUser dlgConfirm = new DialogConfirmUser(frmMain,lang.getString("TT018"),true,frmMain);
//    dlgConfirm.setLocation(210,170);
//    dlgConfirm.setVisible(true);
//    if (dlgConfirm.done) {
//      frmMain.removeButton();
//      frmMain.setTitle(lang.getString("TT019"));
//      showReportButton();
//
//      //set flag for screen Report
//      flagSetHotkey = FLAG_SET_HOTKEY_SCR_ADMIN_REPORT;
//    }

    frmMain.removeButton();
    frmMain.setTitle(lang.getString("TT019"));
    showReportButton();

    //set flag for screen Report
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_ADMIN_REPORT;

  }

  void btnRegisterClose_actionPerformed(ActionEvent e) {
//    DialogConfirmUser dlgConfirm = new DialogConfirmUser(frmMain,"JPOS - Confirm User",true,frmMain);
//    dlgConfirm.setLocation(210,170);
//    dlgConfirm.setVisible(true);
//    if (dlgConfirm.done) {
        frmMain.setTitle(lang.getString("TT020"));
        frmMain.showScreen(Constant.SCR_REGISTER_CLOSE);
        frmMain.pnlRegisterClose.txtAmountCashVND.requestFocus(true);
        frmMain.setEditTextInput(false);
//    }
  }

  void btnTransManagement_actionPerformed(ActionEvent e) {
    frmMain.removeButton();
    frmMain.setTitle(lang.getString("TT041"));
    showTransManagementButton();
    //set flag for screen Trans Management
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_ADMIN_TRANS_MANAGEMENT;
  }

  void btnAdminBack_actionPerformed(ActionEvent e) {
    frmMain.removeButton();
    frmMain.setTitle(lang.getString("TT013"));
    showButton();
    //set flag for screen Main
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_MAIN;
  }

  void btnTransNetSale_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT022"));
    frmMain.showScreen(Constant.SCR_NET_SALES_BY_TRANS);
  }

  void btnReportBack_actionPerformed(ActionEvent e) {
    frmMain.removeButton();
    frmMain.setTitle(lang.getString("TT013"));
    showAdminButton();
    //set flag hotkey for sreen Admin
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_ADMIN;

  }

  void btnInvReceipt_actionPerformed(ActionEvent e) {
    frmMain.setInputLabel(lang.getString("MS021_EnterTranDate"));
    frmMain.setTitle(lang.getString("TT023"));
    frmMain.showScreen(Constant.SCR_INVENTORY_RECEIPT);
  }

  void btnReceiptItem_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT024"));
    frmMain.showScreen(Constant.SCR_RECEIPT_ITEM);
    frmMain.pnlReceiptItem.initScreen();
  }

  void btnInvMagBack_actionPerformed(ActionEvent e) {
    frmMain.removeButton();
    frmMain.setTitle(lang.getString("TT013"));
    showButton();
    //set flag for screen Main
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_MAIN;
  }

  void btnTimer_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT025"));
    frmMain.showScreen(Constant.SCR_SCHEDULE_TIMER);
  }

  void btnInvStockCount_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT026"));
    frmMain.setInputLabel(lang.getString("MS059_InsertStock"));
    frmMain.showScreen(Constant.SCR_STOCK_COUNT);
  }

  void btnTransferItem_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT027"));
    frmMain.showScreen(Constant.SCR_TRANSFER_ITEM);
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
      switch (flagSetHotkey) {
        case FLAG_SET_HOTKEY_SCR_MAIN:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnSaleExpress.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnAdmin.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnExchangeRate.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F4) {
            btnRegisterClose.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F10 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnLogOff.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_ADMIN:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnReport.doClick();
            //}else if (identifier.intValue()==KeyEvent.VK_F2){
//            btnRegisterClose.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnTransManagement.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            //btnCustomer.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F4) {
            btnRunBatchJob.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnAdminBack.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_INV_MAN:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnInvStockCount.doClick();
            /*}else if (identifier.intValue()==KeyEvent.VK_F2){
              btnReceipt.doClick();
                       }else if (identifier.intValue()==KeyEvent.VK_F3){
              btnTransfer.doClick();
                       }else if (identifier.intValue()==KeyEvent.VK_F4){
              btnRtnDmgGoods.doClick();*/
//          }else if (identifier.intValue()==KeyEvent.VK_F5){
//            btnViewTransfer.doClick();
//          }else if (identifier.intValue()==KeyEvent.VK_F6){
//            btnViewTransfer.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnInvMagBack.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_ADMIN_REPORT:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnItemNetSale.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnTransNetSale.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnReportBack.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_ADMIN_TRANS_MANAGEMENT:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnVoidTrans.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnPaidIn.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnPaidOut.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnReportBack.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_INV_MANAGEMENT_TRANSFER:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnTransferItem.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnViewTransfer.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnTransferBack.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_INV_MANAGEMENT_RECEIPT:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnInvReceipt.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnReceiptItem.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnReceiptBack.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_INV_MANAGEMENT_DAMAGE_GOODS:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnRtnDmgGoodsItm.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnViewDmgGoodsItm.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnBackDmgGoodsItm.doClick();
          }
          break;

      }

    }
  }

  void refreshDataFromViewTransferPanel() {
    frmMain.pnlViewTransferTrans.txtCreaterName.setText("");
    frmMain.pnlViewTransferTrans.txtDesStoreID.setText("");
    frmMain.pnlViewTransferTrans.txtReceiverName.setText("");
    frmMain.pnlViewTransferTrans.txtSrcStoreID.setText("");
    frmMain.pnlViewTransferTrans.txtTransferDate.setText("");
    frmMain.pnlViewTransferTrans.txtTransferID.setText("");
  }

  void btnViewTransfer_actionPerformed(ActionEvent e) {
    refreshDataFromViewTransferPanel();
    frmMain.setTitle(lang.getString("TT028"));
    frmMain.showScreen(Constant.SCR_VIEW_TRANSFER_TRANS);
  }

  /*void btnCustomer_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT029"));
    frmMain.showScreen(Constant.SCR_CUSTOMER_SETUP);
    frmMain.setTitle(lang.getString("TT029"));
  }*/

  void btnVoidTrans_actionPerformed(ActionEvent e) {
    DialogConfirmUser dlgConfirm = new DialogConfirmUser(frmMain,lang.getString("TT018"),true,frmMain);
    dlgConfirm.setLocation(210,170);
    dlgConfirm.setVisible(true);
    if (dlgConfirm.done) {
      frmMain.setTitle(lang.getString("TT031"));
      frmMain.showScreen(Constant.SCR_VOID_TRANSACTION);
      frmMain.removeButton();
      frmMain.pnlVoidTransaction.showButton();
    }
  }

  void btnTransManagementBack_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.removeButton();
    showAdminButton();
  }

  void btnPaidIn_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT032"));
    frmMain.removeButton();
    frmMain.showScreen(Constant.SCR_PAID_IN);
    frmMain.pnlPaidIn.showButton();
    frmMain.pnlPaidIn.initScreen();
  }

  void btnPaidOut_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT033"));
    frmMain.removeButton();
    frmMain.showScreen(Constant.SCR_PAID_OUT);
    frmMain.pnlPaidOut.showButton();
    frmMain.pnlPaidOut.initScreen();

  }

  void btnReceipt_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT024"));
    frmMain.removeButton();
    frmMain.pnlMain.showReceiptButton();
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGEMENT_RECEIPT;
  }

  void btnTransfer_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT027"));
    frmMain.removeButton();
    frmMain.pnlMain.showTransferButton();
    //set flag for screen Trans Management
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGEMENT_TRANSFER;
  }

  void btnReceiptBack_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT017"));
    frmMain.removeButton();
    frmMain.pnlMain.showInvManageButton();

    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MAN;
  }

  void btnTransferBack_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT017"));
    frmMain.removeButton();
    frmMain.pnlMain.showInvManageButton();

    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MAN;
  }

  void btnItemNetSale_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT035"));
    frmMain.showScreen(Constant.SCR_NET_SALES_BY_ITEM);
  }

  void btnExchangeRate_actionPerformed(ActionEvent e) {
    DialogConfirmUser dlgConfirm = new DialogConfirmUser(frmMain,lang.getString("TT018"),true,frmMain);
    dlgConfirm.setLocation(210,170);
    dlgConfirm.setVisible(true);
    if (dlgConfirm.done) {
      frmMain.setTitle(lang.getString("TT015"));
      frmMain.showScreen(Constant.SCR_EXCHANGE_RATE);
      frmMain.pnlExchangeRate.initScreen();
    }
  }

  void btnRunBatchJob_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT036"));
    frmMain.showScreen(Constant.SCR_RUN_POS_BATCH_JOB);
    frmMain.setTitle(lang.getString("TT037"));
  }

  void btnRtnDmgGoods_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT038"));
    frmMain.removeButton();
    frmMain.pnlMain.showDmgGoodsBtn();
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGEMENT_DAMAGE_GOODS;
  }

  void btnRtnDmgGoodsItm_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT039"));
    frmMain.showScreen(Constant.SCR_RETURN_DAMAGE_GOODS_ITEM);
//    frmMain.pnlDamageGoods.getCreatedName();
  }

  void btnBackDmgGoodsItm_actionPerformed(ActionEvent e) {
    frmMain.removeButton();
    frmMain.setTitle(lang.getString("TT017"));
    showInvManageButton();
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MAN;
  }

  void btnViewDmgGoodsItm_actionPerformed(ActionEvent e) {
    frmMain.removeButton();
    frmMain.setTitle(lang.getString("TT040"));
    frmMain.showScreen(Constant.SCR_VIEW_DAMAGE_GOODS_ITEM);
  }
}

class PanelMain_btnSaleExpress_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnSaleExpress_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSaleExpress_actionPerformed(e);
  }
}

class PanelMain_btnAdmin_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnAdmin_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdmin_actionPerformed(e);
  }
}

class PanelMain_btnInvenManage_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnInvenManage_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnInvenManage_actionPerformed(e);
  }
}

class PanelMain_btnLogOff_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnLogOff_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnLogOff_actionPerformed(e);
  }
}

class PanelMain_btnReport_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnReport_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReport_actionPerformed(e);
  }
}

class PanelMain_btnRegisterClose_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnRegisterClose_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnRegisterClose_actionPerformed(e);
  }
}

class PanelMain_btnTransManagement_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnTransManagement_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnTransManagement_actionPerformed(e);
  }
}

class PanelMain_btnAdminBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnAdminBack_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminBack_actionPerformed(e);
  }
}

class PanelMain_btnItemNetSale_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnItemNetSale_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnItemNetSale_actionPerformed(e);
  }
}

class PanelMain_btnTransNetSale_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnTransNetSale_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnTransNetSale_actionPerformed(e);
  }
}

class PanelMain_btnReportBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnReportBack_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReportBack_actionPerformed(e);
  }
}

class PanelMain_btnInvStockCount_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnInvStockCount_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnInvStockCount_actionPerformed(e);
  }
}

class PanelMain_btnInvReceipt_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnInvReceipt_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnInvReceipt_actionPerformed(e);
  }
}

class PanelMain_btnReceiptItem_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnReceiptItem_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceiptItem_actionPerformed(e);
  }
}

class PanelMain_btnInvMagBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnInvMagBack_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnInvMagBack_actionPerformed(e);
  }
}

class PanelMain_btnTimer_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnTimer_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnTimer_actionPerformed(e);
  }
}

class PanelMain_btnTransferItem_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnTransferItem_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnTransferItem_actionPerformed(e);
  }
}

class PanelMain_btnViewTransfer_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnViewTransfer_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnViewTransfer_actionPerformed(e);
  }
}

class PanelMain_btnVoidTrans_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnVoidTrans_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnVoidTrans_actionPerformed(e);
  }
}

class PanelMain_btnTransManagementBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnTransManagementBack_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnTransManagementBack_actionPerformed(e);
  }
}

class PanelMain_btnPaidIn_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnPaidIn_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPaidIn_actionPerformed(e);
  }
}

class PanelMain_btnPaidOut_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnPaidOut_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPaidOut_actionPerformed(e);
  }
}

class PanelMain_btnReceipt_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnReceipt_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceipt_actionPerformed(e);
  }
}

class PanelMain_btnTransfer_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnTransfer_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnTransfer_actionPerformed(e);
  }
}

class PanelMain_btnReceiptBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnReceiptBack_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceiptBack_actionPerformed(e);
  }
}

class PanelMain_btnTransferBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnTransferBack_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnTransferBack_actionPerformed(e);
  }
}

class PanelMain_btnExchangeRate_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnExchangeRate_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnExchangeRate_actionPerformed(e);
  }
}

class PanelMain_btnRunBatchJob_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnRunBatchJob_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnRunBatchJob_actionPerformed(e);
  }
}

class PanelMain_btnCustomer_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnCustomer_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    //adaptee.btnCustomer_actionPerformed(e);
  }
}

class PanelMain_btnRtnDmgGoods_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnRtnDmgGoods_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnRtnDmgGoods_actionPerformed(e);
  }
}

class PanelMain_btnRtnDmgGoodsItm_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnRtnDmgGoodsItm_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnRtnDmgGoodsItm_actionPerformed(e);
  }
}

class PanelMain_btnBackDmgGoodsItm_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnBackDmgGoodsItm_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnBackDmgGoodsItm_actionPerformed(e);
  }
}

class PanelMain_btnViewDmgGoodsItm_actionAdapter
    implements java.awt.event.ActionListener {
  PanelMain adaptee;

  PanelMain_btnViewDmgGoodsItm_actionAdapter(PanelMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnViewDmgGoodsItm_actionPerformed(e);
  }
}
