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
 * <p>Description: Mang hinh chinh -> Quan tri -> Quan ly Giao dich -> Tien tra</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelPaidOut extends JPanel {
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
  BJComboBox cboReason = new BJComboBox();
  JLabel lblReason = new JLabel();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel8 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel9 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel12 = new JPanel();
  JPanel jPanel13 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel14 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextField jTextArea1 = new JTextField();
  JPanel jPanel15 = new JPanel();
  JLabel jLabel3 = new JLabel();
  FlowLayout flowLayout2 = new FlowLayout();

  public PanelPaidOut(FrameMain frmMain) {
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
    jPanel1.setPreferredSize(new Dimension(600, 80));
    jPanel1.setLayout(borderLayout1);
    this.setPreferredSize(new Dimension(600, 600));
    this.setLayout(flowLayout1);
    jPanel3.setPreferredSize(new Dimension(600, 500));
    jPanel3.setLayout(borderLayout2);
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOK.setToolTipText(lang.getString("OK") + " (F1)");
    btnOK.setText(lang.getString("OK") + " (F1)");
    btnOK.addActionListener(new PanelPaidOut_btnOK_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F12)");
    btnCancel.setText("<html><center><br><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                          "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                          "ml>");
    btnCancel.addActionListener(new PanelPaidOut_btnCancel_actionAdapter(this));
    jPanel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    jPanel2.setPreferredSize(new Dimension(10, 20));
    cboReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboReason.setPreferredSize(new Dimension(200, 20));
    lblReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReason.setPreferredSize(new Dimension(160, 20));
    lblReason.setText(lang.getString("SelectReasonForPaidOut") + ":");
    jPanel4.setMinimumSize(new Dimension(345, 32));
    jPanel4.setPreferredSize(new Dimension(420, 20));
    jPanel6.setPreferredSize(new Dimension(20, 10));
    jPanel7.setPreferredSize(new Dimension(200, 10));
    jPanel7.setLayout(null);
    jPanel5.setPreferredSize(new Dimension(20, 500));
    jPanel8.setPreferredSize(new Dimension(580, 500));
    jPanel8.setLayout(borderLayout3);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(160, 20));
    jLabel1.setText(lang.getString("PaidOutAmount") + ":");
    jTextField1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jTextField1.setPreferredSize(new Dimension(200, 20));
    jTextField1.setSelectionStart(0);
    jTextField1.setText("");
    jTextField1.addKeyListener(new PanelPaidOut_jTextField1_keyAdapter(this));
    jPanel9.setLayout(borderLayout4);
    jPanel9.setPreferredSize(new Dimension(580, 450));
    jPanel13.setMinimumSize(new Dimension(10, 10));
    jPanel13.setPreferredSize(new Dimension(580, 20));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(200, 20));
    jLabel2.setText(lang.getString("DescribeTheItemsPurchased") + ":");
    jPanel14.setPreferredSize(new Dimension(360, 20));
    jPanel12.setPreferredSize(new Dimension(580, 430));
    jPanel12.setLayout(flowLayout2);
    jScrollPane1.setPreferredSize(new Dimension(500, 100));
    jPanel15.setPreferredSize(new Dimension(100, 10));
    jTextArea1.setToolTipText("");
    jTextArea1.setText("");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setIconTextGap(4);
    jLabel3.setText(DAL.getCurrencyID());
    jLabel3.setBounds(new Rectangle(0, 31, 24, 16));
    flowLayout2.setAlignment(FlowLayout.LEFT);
    this.add(jPanel1, null);
    jPanel1.add(jPanel2,  BorderLayout.NORTH);
    jPanel1.add(jPanel4, BorderLayout.CENTER);
    jPanel4.add(lblReason, null);
    jPanel4.add(cboReason, null);
    jPanel4.add(jLabel1, null);
    jPanel4.add(jTextField1, null);
    jPanel1.add(jPanel6,  BorderLayout.WEST);
    jPanel1.add(jPanel7,  BorderLayout.EAST);
    jPanel7.add(jLabel3, null);
    this.add(jPanel3, null);
    jPanel3.add(jPanel5,  BorderLayout.WEST);
    jPanel3.add(jPanel8,  BorderLayout.CENTER);
    jPanel8.add(jPanel9, BorderLayout.CENTER);
    jPanel9.add(jPanel12, BorderLayout.CENTER);
    jPanel12.add(jScrollPane1, null);
    jPanel12.add(jPanel15, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
    jPanel9.add(jPanel13, BorderLayout.NORTH);
    jPanel13.add(jLabel2, null);
    jPanel13.add(jPanel14, null);
  }
  //initialize Screen
  void initScreen(){
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      cboReason.setData(getComboData(stmt));
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  //get combo data
  ResultSet getComboData(Statement stmt){
    ResultSet rs = null;
    try{
      rs = DAL.executeScrollQueries(
          "select reason_id, reason_desc from btm_pos_reason where reason_type='O'",stmt);
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

  void btnOK_actionPerformed(ActionEvent e) {
    if(jTextArea1.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS090_ExplanationNotNull"));
      jTextArea1.requestFocus(true);
      return;
    }
    if (jTextField1.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS091_CollectedAmountNotNull"));
      jTextField1.requestFocus(true);
      return;
    }
    if(calcDrawFun()< Double.parseDouble(jTextField1.getText())){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS079_paidInMoney") +" "+calcDrawFun()+" VND");
      jTextField1.requestFocus(true);
      return;
    }
    Statement stmt = null;
    String sql = "Insert into btm_pos_paid_in_out values('-999'," +
        cboReason.getSelectedData() + ",0," + Double.parseDouble(jTextField1.getText()) +
        ",'"+DAL.getCurrencyID()+"','"+DAL.getCurrencyID()+"'," +
        "to_date('" + ut.getSystemDateTime1() + "','" + ut.DATETIME_FORMAT2 +
        "'),1,'',to_date('7777-7-7','yyyy-MM-dd'),'" + jTextArea1.getText() + "',0,'" +DAL.getEmpPosID1()+ "')";
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
  //  caculate current draw fun: the same code in PanelNewSale,VOid Txn
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
  void jTextField1_keyReleased(KeyEvent e) {
    char key = e.getKeyChar();
    if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_BACK_SPACE) return;
    if (key < '0' || key > '9'){
      if (key != ',' && key != '.'){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS089_CollectAmountNumberic"));
        return;
      }
    }
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

class PanelPaidOut_btnOK_actionAdapter implements java.awt.event.ActionListener {
  PanelPaidOut adaptee;

  PanelPaidOut_btnOK_actionAdapter(PanelPaidOut adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class PanelPaidOut_jTextField1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPaidOut adaptee;

  PanelPaidOut_jTextField1_keyAdapter(PanelPaidOut adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.jTextField1_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jTextField1_keyTyped(e);
  }
}

class PanelPaidOut_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelPaidOut adaptee;

  PanelPaidOut_btnCancel_actionAdapter(PanelPaidOut adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}
