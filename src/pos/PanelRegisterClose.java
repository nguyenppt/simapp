//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//






















































package pos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import common.*;
import javax.swing.border.*;
import btm.swing.*;
import java.sql.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @version 1.0
 */
//not use
public class PanelRegisterClose extends JPanel {
  //const in BTM_POS_TENDER_TYPE table
  private static final int CREDIT_TENDER =1 ;
  private static final int COUPON_TENDER =2 ;
  private static final int CASH_TENDER =3 ;
  private static final int FOREIGN_CASH_TENDER =4 ;

  private static final int OK_BTN =221;
  DataAccessLayer DAL = null;
  FrameMain frmMain;
  Statement stmt = null;
  int flagButton=OK_BTN;
  Utils ut=new Utils();

  float cash=0;
  float foreignCash=0;
  float couponCash=0;
  float creditCash=0;
  float paidOut=0;
//  float totalDrawer=0;

  BJPanel pnlUp = new BJPanel();
  BJPanel pnlDown = new BJPanel();
  GridLayout gridLayout1 = new GridLayout();
  BJButton btnVerifyDeposit = new BJButton();
  BJButton btnSave = new BJButton();
  BJButton btnBack = new BJButton();
  BJPanel pnlCreditCard = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel pnlCoupon = new BJPanel(Constant.PANEL_TYPE_HEADER);
  GridLayout gridLayout2 = new GridLayout();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  TitledBorder titledBorder2;
  BJPanel pnlDeposit = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel pnlDrawFun = new BJPanel(Constant.PANEL_TYPE_HEADER);
  GridLayout gridLayout3 = new GridLayout();
  Border border3;
  TitledBorder titledBorder3;
  Border border4;
  TitledBorder titledBorder4;
  Border border5;
  TitledBorder titledBorder5;
  BJPanel jPanel7 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel8 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel9 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel10 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel11 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  GridLayout gridLayout4 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JTextField txtVisaCard = new JTextField();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JTextField txtMasterCard = new JTextField();
  JTextField txtAmericanCard = new JTextField();
  JTextField txtDiscoverCard = new JTextField();
  JTextField tstJcbCard = new JTextField();
  BJPanel jPanel12 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel13 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel14 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel15 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  GridLayout gridLayout5 = new GridLayout();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JTextField txtCash = new JTextField();
  JTextField txtForeignCash = new JTextField();
  JTextField txtCoupon = new JTextField();
  JTextField txtDrawFun = new JTextField();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  FlowLayout flowLayout5 = new FlowLayout();
  FlowLayout flowLayout6 = new FlowLayout();
  FlowLayout flowLayout7 = new FlowLayout();
  FlowLayout flowLayout8 = new FlowLayout();

  public PanelRegisterClose(FrameMain frmMain) {
    try {
      this.frmMain=frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
//        System.out.println("N15");
    registerKeyboardActions();
    border1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Deposit");
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Credit card");
    border2 = BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151));
    titledBorder2 = new TitledBorder(border2,"Coupon");
    border3 = BorderFactory.createEmptyBorder();
    titledBorder3 = new TitledBorder(border3,"Deposit");
    border4 = BorderFactory.createEmptyBorder();
    titledBorder4 = new TitledBorder(border4,"Draw fun");
    border5 = BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151));
    titledBorder5 = new TitledBorder(border5,"Drawer fun");
    this.setLayout(gridLayout1);
    pnlUp.setBackground(SystemColor.control);
    pnlUp.setLayout(gridLayout2);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(0);
    btnVerifyDeposit.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnVerifyDeposit.setToolTipText("Verify Deposit(F1)");
    btnVerifyDeposit.setText("Verify Deposit(F1)");
    btnVerifyDeposit.addActionListener(new PanelRegisterClose_btnVerifyDeposit_actionAdapter(this));
    btnSave.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnSave.setToolTipText("Save");
    btnSave.setText("Save");
    btnSave.addActionListener(new PanelRegisterClose_btnSave_actionAdapter(this));
    btnBack.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnBack.setToolTipText("Back(F2)");
    btnBack.setText("Back(F2)");
    btnBack.addActionListener(new PanelRegisterClose_btnBack_actionAdapter(this));
    pnlCreditCard.setBackground(SystemColor.control);
    pnlCreditCard.setFont(new java.awt.Font("Dialog", 0, 11));
    pnlCreditCard.setForeground(SystemColor.activeCaption);
    pnlCreditCard.setBorder(titledBorder1);
    pnlCreditCard.setLayout(gridLayout4);
    pnlCoupon.setBackground(SystemColor.control);
    pnlCoupon.setBorder(titledBorder2);
    pnlDown.setLayout(gridLayout3);
    pnlDeposit.setBackground(SystemColor.control);
    pnlDeposit.setBorder(border1);
    pnlDeposit.setDebugGraphicsOptions(0);
    pnlDeposit.setLayout(gridLayout5);
    pnlDrawFun.setBackground(SystemColor.control);
    pnlDrawFun.setBorder(titledBorder5);
    gridLayout4.setColumns(1);
    gridLayout4.setRows(0);
    jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel1.setText("VISA: ");
    txtVisaCard.setBackground(Color.white);
    txtVisaCard.setPreferredSize(new Dimension(210, 21));
    txtVisaCard.setEditable(false);
    txtVisaCard.setText("");
    jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel2.setText("MASTER :");
    jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel3.setText("AMERICAN :");
    jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel4.setText("DISCOVER: ");
    jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel5.setText("JCB: ");
    txtMasterCard.setBackground(Color.white);
    txtMasterCard.setPreferredSize(new Dimension(210, 21));
    txtMasterCard.setEditable(false);
    txtMasterCard.setText("");
    txtAmericanCard.setBackground(Color.white);
    txtAmericanCard.setAlignmentY((float) 0.5);
    txtAmericanCard.setPreferredSize(new Dimension(210, 21));
    txtAmericanCard.setEditable(false);
    txtAmericanCard.setText("");
    txtDiscoverCard.setBackground(Color.white);
    txtDiscoverCard.setOpaque(true);
    txtDiscoverCard.setPreferredSize(new Dimension(210, 21));
    txtDiscoverCard.setRequestFocusEnabled(true);
    txtDiscoverCard.setEditable(false);
    txtDiscoverCard.setText("");
    tstJcbCard.setBackground(Color.white);
    tstJcbCard.setPreferredSize(new Dimension(210, 21));
    tstJcbCard.setEditable(false);
    tstJcbCard.setText("");
    gridLayout5.setColumns(1);
    gridLayout5.setRows(0);
    jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel6.setText("VND: ");
    jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel7.setText("USD: ");
    jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel8.setText("You have to enter your deposit.");
    txtCash.setBackground(Color.white);
    txtCash.setPreferredSize(new Dimension(210, 21));
    txtCash.setToolTipText("");
    txtCash.setEditable(false);
    txtCash.setText("");
    txtForeignCash.setBackground(Color.white);
    txtForeignCash.setPreferredSize(new Dimension(210, 21));
    txtForeignCash.setEditable(false);
    txtForeignCash.setText("");
    txtCoupon.setBackground(Color.white);
    txtCoupon.setPreferredSize(new Dimension(150, 21));
    txtCoupon.setEditable(false);
    txtCoupon.setSelectionStart(11);
    txtCoupon.setText("");
    jPanel7.setLayout(flowLayout1);
    jPanel8.setLayout(flowLayout2);
    jPanel9.setLayout(flowLayout3);
    jPanel10.setLayout(flowLayout4);
    jPanel11.setLayout(flowLayout5);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.RIGHT);
    flowLayout5.setAlignment(FlowLayout.RIGHT);
    jPanel12.setLayout(flowLayout6);
    jPanel13.setLayout(flowLayout7);
    jPanel14.setLayout(flowLayout8);
    flowLayout6.setAlignment(FlowLayout.RIGHT);
    flowLayout7.setAlignment(FlowLayout.RIGHT);
    txtDrawFun.setPreferredSize(new Dimension(150, 21));
    txtDrawFun.setText("");
    txtDrawFun.addKeyListener(new PanelRegisterClose_txtDrawFun_keyAdapter(this));
    jPanel7.setBackground(SystemColor.control);
    jPanel8.setBackground(SystemColor.control);
    jPanel9.setBackground(SystemColor.control);
    jPanel10.setBackground(SystemColor.control);
    jPanel11.setBackground(SystemColor.control);
    jPanel12.setBackground(SystemColor.control);
    jPanel13.setBackground(SystemColor.control);
    jPanel14.setBackground(SystemColor.control);
    jPanel15.setBackground(SystemColor.control);
    this.add(pnlUp, null);
    pnlUp.add(pnlCreditCard, null);
    pnlCreditCard.add(jPanel7, null);
    jPanel7.add(jLabel1, null);
    jPanel7.add(txtVisaCard, null);
    pnlCreditCard.add(jPanel8, null);
    jPanel8.add(jLabel2, null);
    jPanel8.add(txtMasterCard, null);
    pnlCreditCard.add(jPanel9, null);
    jPanel9.add(jLabel3, null);
    jPanel9.add(txtAmericanCard, null);
    pnlCreditCard.add(jPanel10, null);
    jPanel10.add(jLabel4, null);
    jPanel10.add(txtDiscoverCard, null);
    pnlCreditCard.add(jPanel11, null);
    jPanel11.add(jLabel5, null);
    jPanel11.add(tstJcbCard, null);
    pnlUp.add(pnlCoupon, null);
    pnlCoupon.add(txtCoupon, null);
    this.add(pnlDown, null);
    pnlDown.add(pnlDeposit, null);
    pnlDeposit.add(jPanel12, null);
    jPanel12.add(jLabel6, null);
    jPanel12.add(txtCash, null);
    pnlDeposit.add(jPanel13, null);
    jPanel13.add(jLabel7, null);
    jPanel13.add(txtForeignCash, null);
    pnlDeposit.add(jPanel14, null);
    jPanel14.add(jLabel8, null);
    pnlDeposit.add(jPanel15, null);
    pnlDown.add(pnlDrawFun, null);
    pnlDrawFun.add(txtDrawFun, null);
//    jPanel3.setti

    //=====================
//    showData();
  }

  //show button in Frame Main
  void showButton() {
    frmMain.showButton(btnVerifyDeposit);
//    frmMain.showButton(btnSave);
    frmMain.showButton(btnBack);
  }
  void showData(){
    String sSQL=new String();
    ResultSet rs=null;
    String transID="";
    //reset var
    cash=0;
    foreignCash=0;
    couponCash=0;
    creditCash=0;
    paidOut=0;

    sSQL = "select * from BTM_POS_PAID_IN_OUT where trunc(TRANS_DATE) = to_Date('" + ut.getSystemDate()+ "','yyyy-MM-dd') order by trans_ID";
//    System.out.println(sSQL);


    try{
      stmt = DAL.getConnection().createStatement();
      rs=DAL.executeQueries(sSQL,stmt);
      while(rs.next()){
        if(rs.getInt("PAYMENT_TYPE")==CASH_TENDER){
          cash+=rs.getFloat("PAID_IN");
        }else if(rs.getInt("PAYMENT_TYPE")==FOREIGN_CASH_TENDER){
          foreignCash+=rs.getFloat("PAID_IN");
        }else if(rs.getInt("PAYMENT_TYPE")==COUPON_TENDER){
          couponCash+=rs.getFloat("PAID_IN");
        }else if(rs.getInt("PAYMENT_TYPE")==CREDIT_TENDER){
          creditCash+=rs.getFloat("PAID_IN");
        }
        if(!transID.equals(rs.getString("TRANS_ID"))){
          paidOut += rs.getFloat("PAID_OUT");
        }
        transID=rs.getString("TRANS_ID");
      }
      stmt.close();
//      System.out.println("paidOut "+paidOut);
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    //set text
    txtCash.setText(String.valueOf(cash));
    txtForeignCash.setText(String.valueOf(foreignCash/ut.getExchangeRate(DAL,"USD")));
    txtCoupon.setText(String.valueOf(couponCash));
    txtVisaCard.setText(String.valueOf(creditCash));

  }
  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {

  }
  void btnVerifyDeposit_actionPerformed(ActionEvent e) {
    String sSQL="";
    ResultSet rs=null;
    float paidIn=0;
    float drawIn=0;
    float drawOut=0;
    float payment=0;

    if (!ut.isFloatString(txtDrawFun.getText().trim())) {
        ut.showMessage(frmMain,"Warning", "The drawer must be number!");
        txtDrawFun.setSelectionStart(0);
        txtDrawFun.setSelectionEnd(txtDrawFun.getText().length());
        txtDrawFun.requestFocus();
        return;
    }
    if(Float.parseFloat(txtDrawFun.getText()) <= 0){
       ut.showMessage(frmMain,"Warning", "The drawer must be greater than 0!");
       txtDrawFun.setSelectionStart(0);
       txtDrawFun.setSelectionEnd(txtDrawFun.getText().length());
       txtDrawFun.requestFocus();
        return;
    }
    paidIn=cash+foreignCash +couponCash+creditCash;
    payment=paidIn-paidOut;
    drawOut=Float.parseFloat(txtDrawFun.getText().trim());

    sSQL = "SELECT DRAW_FUND,TOTAL_FUND FROM BTM_POS_STORE_FUND WHERE WORKDAY=to_Date('" + ut.getSystemDate() + "','yyyy-MM-dd')";
//    System.out.println(sSQL);
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                          ResultSet.CONCUR_UPDATABLE);
      rs=DAL.executeQueries(sSQL,stmt);
      rs.next();
      drawIn=rs.getFloat("DRAW_FUND");

      if(drawOut <(drawIn+payment) ){
        ut.showMessage(frmMain,"Warning", "The drawer less than amount!");
        return;
      }else{
        ut.showMessage(frmMain,"Warning",  "Verify deposit is ok!");
        rs.updateFloat("TOTAL_FUND", drawOut);
        rs.updateRow();
        //back
        frmMain.dispose();
        new AppPos();
      }
      stmt.close();
    }
    catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);

    }
  }

  void btnSave_actionPerformed(ActionEvent e) {
//need save ??
  }

  void btnBack_actionPerformed(ActionEvent e) {
    frmMain.setTitle("JPOS - MAIN");
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showAdminButton();
   // PanelMain.flagSetHotkey = PanelMain.FLAG_SET_HOTKEY_SCR_ADMIN;
  }

  void txtDrawFun_keyTyped(KeyEvent e) {
if (( txtDrawFun.getText().length() == Constant.LENGHT_DRAWER_INPUT) && (e.getKeyChar() != e.VK_BACK_SPACE) && (txtDrawFun.getSelectionStart() == txtDrawFun.getSelectionEnd()) ) {
      e.consume();
    }
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
           if (identifier.intValue()==KeyEvent.VK_F1){
            btnVerifyDeposit.doClick();
          }else if (identifier.intValue()==KeyEvent.VK_F2 || identifier.intValue()==KeyEvent.VK_ESCAPE){
            btnBack.doClick();
          }
      }
}
}




class PanelRegisterClose_btnVerifyDeposit_actionAdapter implements java.awt.event.ActionListener {
  PanelRegisterClose adaptee;

  PanelRegisterClose_btnVerifyDeposit_actionAdapter(PanelRegisterClose adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnVerifyDeposit_actionPerformed(e);
  }
}

class PanelRegisterClose_btnSave_actionAdapter implements java.awt.event.ActionListener {
  PanelRegisterClose adaptee;

  PanelRegisterClose_btnSave_actionAdapter(PanelRegisterClose adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSave_actionPerformed(e);
  }
}

class PanelRegisterClose_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelRegisterClose adaptee;

  PanelRegisterClose_btnBack_actionAdapter(PanelRegisterClose adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelRegisterClose_txtDrawFun_keyAdapter extends java.awt.event.KeyAdapter {
  PanelRegisterClose adaptee;

  PanelRegisterClose_txtDrawFun_keyAdapter(PanelRegisterClose adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtDrawFun_keyTyped(e);
  }
}
