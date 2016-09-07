package pos;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import java.sql.*;
import common.*;
import java.awt.event.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: Mang hinh chinh -> Quan tri -> Quan ly Giao dich -> Tien nhan</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelPaidIn extends JPanel {
  FrameMain frmMain;
  DataAccessLayer DAL;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  JPanel jPanel1 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  BJButton btnOK = new BJButton();
  BJButton btnCancel = new BJButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel12 = new JPanel();
  JPanel jPanel13 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextField jTextArea1 = new JTextField();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel10 = new JPanel();
  BJComboBox cboReason = new BJComboBox();
  JPanel jPanel11 = new JPanel();
  JLabel lblReason = new JLabel();
  JLabel jLabel1 = new JLabel();
  JLabel lblPaymentType = new JLabel();
  BJComboBox cboPaymentType = new BJComboBox();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField1 = new JTextField();
  FlowLayout flowLayout3 = new FlowLayout();
  JPanel jPanel7 = new JPanel();
  JLabel jLabel3 = new JLabel();
  BorderLayout borderLayout5 = new BorderLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();

  public PanelPaidIn(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();
    jPanel1.setPreferredSize(new Dimension(600, 100));
    jPanel1.setLayout(borderLayout1);
    this.setPreferredSize(new Dimension(600, 600));
    this.setLayout(flowLayout1);
    jPanel3.setPreferredSize(new Dimension(600, 500));
    jPanel3.setLayout(borderLayout2);
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOK.setText(lang.getString("OK") + " (F1)");
    btnOK.addActionListener(new PanelPaidIn_btnOK_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setText("<html><center><br><b>"+ lang.getString("Cancel") +"</b></br></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                          "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                          "ml>");
    btnCancel.addActionListener(new PanelPaidIn_btnCancel_actionAdapter(this));
    jPanel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    jPanel2.setPreferredSize(new Dimension(10, 20));
    jPanel4.setMinimumSize(new Dimension(192, 10));
    jPanel4.setOpaque(true);
    jPanel4.setPreferredSize(new Dimension(380, 50));
    jPanel4.setLayout(borderLayout5);
    jPanel6.setPreferredSize(new Dimension(20, 10));
    jPanel5.setPreferredSize(new Dimension(20, 500));
    jPanel8.setPreferredSize(new Dimension(580, 100));
    jPanel8.setLayout(flowLayout2);
    jTextField1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jTextField1.setPreferredSize(new Dimension(200, 20));
    jTextField1.setSelectionStart(0);
    jTextField1.setText("");
    jTextField1.addKeyListener(new PanelPaidIn_jTextField1_keyAdapter(this));
    jPanel9.setLayout(borderLayout4);
    jPanel9.setOpaque(true);
    jPanel9.setPreferredSize(new Dimension(580, 150));
    jPanel13.setMinimumSize(new Dimension(10, 10));
    jPanel13.setPreferredSize(new Dimension(580, 22));
    jPanel13.setLayout(gridLayout1);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(160, 20));
    jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel2.setText("   " + lang.getString("Explanation") + ":");
    jPanel12.setPreferredSize(new Dimension(580, 100));
    jPanel12.setLayout(flowLayout4);
    jScrollPane1.setPreferredSize(new Dimension(500, 110));
    jTextArea1.setPreferredSize(new Dimension(500, 100));
    jTextArea1.setToolTipText("");
    jTextArea1.setText("");
//    jTextArea1.setLineWrap(true);
    cboReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboReason.setPreferredSize(new Dimension(200, 20));
    lblReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReason.setPreferredSize(new Dimension(160, 20));
    lblReason.setHorizontalAlignment(SwingConstants.LEFT);
    lblReason.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblReason.setText(lang.getString("SelectReasonForPaidIn") + ":");
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(160, 20));
    jLabel1.setText(lang.getString("CollectAmount") + ":");
    lblPaymentType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPaymentType.setPreferredSize(new Dimension(160, 20));
    lblPaymentType.setText(lang.getString("PaymentType") + ":" );
    jPanel11.setPreferredSize(new Dimension(180, 60));
    cboPaymentType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboPaymentType.setPreferredSize(new Dimension(200, 20));
    jTextField1.setText("");
    jTextField1.setSelectionStart(0);
    jTextField1.setPreferredSize(new Dimension(200, 20));
    jTextField1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel10.setPreferredSize(new Dimension(150, 60));
    jPanel10.setLayout(flowLayout3);
    jTextField5.setText("");
    jTextField5.setSelectionStart(0);
    jTextField5.setPreferredSize(new Dimension(200, 20));
    jTextField5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jTextField1.setPreferredSize(new Dimension(200, 20));
    jTextField1.setText("");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(25, 20));
    jLabel3.setText(DAL.getCurrencyID());
    jLabel3.setBounds(new Rectangle(13, 30, 25, 20));
    jPanel7.setLayout(null);
    jPanel7.setOpaque(true);
    jPanel7.setPreferredSize(new Dimension(200, 50));
    flowLayout4.setAlignment(FlowLayout.LEFT);
    this.add(jPanel1, null);
    jPanel1.add(jPanel2,  BorderLayout.NORTH);
    jPanel1.add(jPanel4, BorderLayout.CENTER);
    jPanel4.add(jPanel7, BorderLayout.EAST);
    jPanel7.add(jLabel3, null);
    jPanel4.add(jPanel11,  BorderLayout.WEST);
    jPanel11.add(lblReason, null);
    jPanel11.add(jLabel1, null);
    jPanel11.add(lblPaymentType, null);
    jPanel4.add(jPanel10,  BorderLayout.CENTER);
    jPanel10.add(cboReason, null);
    jPanel10.add(jTextField1, null);
    jPanel10.add(cboPaymentType, null);
    jPanel1.add(jPanel6,  BorderLayout.WEST);
    this.add(jPanel3, null);
    jPanel3.add(jPanel5,  BorderLayout.WEST);
    jPanel3.add(jPanel8,  BorderLayout.CENTER);
    jPanel8.add(jPanel9, null);
    jPanel9.add(jPanel12, BorderLayout.CENTER);
    jPanel12.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
    jPanel9.add(jPanel13, BorderLayout.NORTH);
    jPanel13.add(jLabel2, null);

  }
  //initialize screen
  void initScreen(){
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      cboReason.setData(getComboData(stmt));
      cboPaymentType.setData(getComboData1(stmt));
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  //get combo data
  ResultSet getComboData(Statement stmt){
    ResultSet rs = null;
    try{
//      System.out.println("select reason_id, reason_desc from btm_pos_reason where reason_type='I'");
      rs = DAL.executeScrollQueries(
          "select reason_id, reason_desc from btm_pos_reason where reason_type='I'",stmt);
    }catch(Exception e){
      e.printStackTrace();
    }
    return rs;
  }
  //get combo data
  ResultSet getComboData1(Statement stmt){
    ResultSet rs = null;
    try{

      rs = DAL.executeScrollQueries("select tender_id, tender_desc from btm_pos_tender_type where lower(tender_type)=lower('Credit') or lower(tender_type)=lower('Cash')",stmt);
    }catch(Exception e){
      e.printStackTrace();
    }
    return rs;
  }

  //show button
  void showButton(){
    frmMain.showButton(btnOK);
    frmMain.showButton(btnCancel);
  }

  void jTextField1_keyReleased(KeyEvent e) {
    char key = e.getKeyChar();
    if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_BACK_SPACE) return;
    if (key < '0' || key > '9'){
      if (key != ',' && key != '.'){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS089_CollectAmountNumberic"));
        return;
      }
    }
  }

  void btnOK_actionPerformed(ActionEvent e) {
    if(jTextArea1.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS089_CollectAmountNumberic"));
      jTextArea1.requestFocus(true);
      return;
    }
    if (jTextField1.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS091_CollectedAmountNotNull"));
      jTextField1.requestFocus(true);
      return;
    }

//    String sql = "Insert into btm_pos_paid_in_out values('-888'," +
//        cboReason.getSelectedData() + "," + Double.parseDouble(jTextField1.getText()) +
//        ",0,'"+DAL.getCurrencyID()+"',''," +
//        "to_date('" + ut.getSystemDate() + "','" + ut.DATE_FORMAT +
//        "'),"+ Integer.parseInt(cboPaymentType.getSelectedData()) +",'','','" + jTextArea1.g ketText() + "')";
    //Nguyen Pham Begin
    Statement stmt = null;
    String sql = "Insert into btm_pos_paid_in_out values('-888'," +
        cboReason.getSelectedData() + "," + Double.parseDouble(jTextField1.getText()) +
        ",0,'"+DAL.getCurrencyID()+"','"+DAL.getCurrencyID()+"'," +
        "to_date('" + ut.getSystemDateTime1() + "','" + ut.DATETIME_FORMAT2 +
        "'),"+ Integer.parseInt(cboPaymentType.getSelectedData()) +",'',to_date('7777-7-7','yyyy-MM-dd'),'" + jTextArea1.getText() + "',0,'" +DAL.getEmpPosID1()+ "')";

    //Nguyen Pham End
    try{
      stmt = DAL.getConnection().createStatement();
//      System.out.println(sql);
      DAL.executeUpdate(sql,stmt);
      stmt.close();
    }catch(Exception ex){
      ex.printStackTrace();
    }
    jTextArea1.setText("");
    jTextField1.setText("");
    frmMain.setTitle(lang.getString("TT051"));
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showTransManagementButton();
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    jTextArea1.setText("");
    jTextField1.setText("");
    frmMain.setTitle(lang.getString("TT051"));
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showTransManagementButton();
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

        if (identifier.intValue() == KeyEvent.VK_F1) {
            btnOK.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue()==KeyEvent.VK_ESCAPE) {
            btnCancel.doClick();
          }
        }
   }

  void jTextField1_keyTyped(KeyEvent e) {
    if (!jTextField1.getText().equals("-")) {
      ut.checkNumberUpdate(e, jTextField1.getText());
    }
  }
}

class PanelPaidIn_jTextField1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPaidIn adaptee;

  PanelPaidIn_jTextField1_keyAdapter(PanelPaidIn adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.jTextField1_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jTextField1_keyTyped(e);
  }
}

class PanelPaidIn_btnOK_actionAdapter implements java.awt.event.ActionListener {
  PanelPaidIn adaptee;

  PanelPaidIn_btnOK_actionAdapter(PanelPaidIn adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class PanelPaidIn_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelPaidIn adaptee;

  PanelPaidIn_btnCancel_actionAdapter(PanelPaidIn adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}
