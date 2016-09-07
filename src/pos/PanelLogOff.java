package pos;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelLogOff extends JPanel {
  FrameMain frmMain;
  DataAccessLayer DAL;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JTextField txtOperatorID = new JTextField();
  JLabel lblOperatorID = new JLabel();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JLabel lblPassword = new JLabel();
  JPasswordField txtPassword = new JPasswordField();
  BJButton btnOK = new BJButton();
  TitledBorder titledBorder1;

  public PanelLogOff(FrameMain frmMain) {
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

//    this.setEnabled(true);
    titledBorder1 = new TitledBorder("");
    this.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    this.setPreferredSize(new Dimension(600, 600));
    this.setLayout(borderLayout1);
    jPanel1.setPreferredSize(new Dimension(600, 100));
    jPanel1.setLayout(gridLayout1);
    jPanel2.setBorder(BorderFactory.createEtchedBorder());
    jPanel2.setPreferredSize(new Dimension(600, 100));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(600, 285));
    jPanel4.setLayout(null);
    jPanel5.setPreferredSize(new Dimension(600, 50));
    jPanel5.setLayout(borderLayout3);
    jPanel6.setPreferredSize(new Dimension(600, 50));
    jPanel6.setLayout(borderLayout4);
    jPanel7.setPreferredSize(new Dimension(100, 50));
    jPanel9.setPreferredSize(new Dimension(100, 50));
    txtOperatorID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtOperatorID.setPreferredSize(new Dimension(200, 20));
    txtOperatorID.setText("");
    txtOperatorID.setBounds(new Rectangle(173, 13, 200, 25));
    txtOperatorID.addKeyListener(new PanelLogOff_txtOperatorID_keyAdapter(this));
    lblOperatorID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblOperatorID.setPreferredSize(new Dimension(110, 20));
    lblOperatorID.setText(lang.getString("Account") + ":");
    lblOperatorID.setBounds(new Rectangle(60, 14, 110, 20));
    jPanel10.setPreferredSize(new Dimension(100, 50));
    lblPassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPassword.setPreferredSize(new Dimension(110, 20));
    lblPassword.setHorizontalAlignment(SwingConstants.LEADING);
    lblPassword.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblPassword.setText(lang.getString("Password") + ":");
    lblPassword.setVerticalAlignment(SwingConstants.CENTER);
    lblPassword.setVerticalTextPosition(SwingConstants.CENTER);
    lblPassword.setBounds(new Rectangle(61, 0, 110, 20));
    txtPassword.setPreferredSize(new Dimension(200, 20));
    txtPassword.setBounds(new Rectangle(174, 0, 200, 27));
    txtPassword.addKeyListener(new PanelLogOff_txtPassword_keyAdapter(this));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnOK.setToolTipText("Enter");
    btnOK.setText("<html><center><b>"+lang.getString("OK")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
    btnOK.addKeyListener(new PanelLogOff_btnOK_keyAdapter(this));
    btnOK.addActionListener(new PanelLogOff_btnOK_actionAdapter(this));
    jPanel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    jPanel4.setBorder(BorderFactory.createEtchedBorder());
    jPanel11.setLayout(null);
    jPanel8.setLayout(null);
    this.add(jPanel1,  BorderLayout.NORTH);
    jPanel1.add(jPanel4, null);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel5,  BorderLayout.NORTH);
    jPanel2.add(jPanel6, BorderLayout.CENTER);
    jPanel5.add(jPanel7,  BorderLayout.WEST);
    jPanel8.add(lblOperatorID, null);
    jPanel8.add(txtOperatorID, null);
    jPanel5.add(jPanel9,  BorderLayout.EAST);
    jPanel5.add(jPanel8, BorderLayout.CENTER);
    jPanel6.add(jPanel10,  BorderLayout.WEST);
    jPanel6.add(jPanel11,  BorderLayout.CENTER);
    jPanel11.add(lblPassword, null);
    jPanel11.add(txtPassword, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    this.setVisible(true);
  }
  void showButton(){
    frmMain.showButton(btnOK);
  }
  void DoEvent(KeyEvent e)
 {
   if (e.getKeyCode() == e.VK_ENTER) {
     btnOK.doClick();
   }
 }

  void btnOK_actionPerformed(ActionEvent e) {
    DefineEncrypt enc=new DefineEncrypt();
    if (txtOperatorID.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS016_AccountNotFound"));
      txtOperatorID.requestFocus(true);
      return;
    }
    if (txtPassword.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS058_PassNotNull"));
      txtPassword.requestFocus(true);
      return;
    }
    ResultSet rs = null;
    Statement stmt = null;
    String sql = "select passwd,emp_id from BTM_POS_EMPLOYEE where EMP_CDE='" + txtOperatorID.getText() + "' and STORE_ID =" + DAL.getStoreID();
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        String sDecrypt = enc.decryptData(rs.getString("PASSWD"));
        if (txtPassword.getText().equalsIgnoreCase(sDecrypt)){
          frmMain.showScreen(Constant.SCR_MAIN);
          frmMain.setTitle(lang.getString("TT003"));
          frmMain.removeButton();
          frmMain.pnlMain.showButton();
          frmMain.txtMainInput.setVisible(true);
          DAL.setEmpPosID1(rs.getString("emp_id"));
          DAL.setEmpPOSID(txtOperatorID.getText());
          frmMain.lblCashier.setText(lang.getString("Cashier") + ": " + DAL.getEmpPOSID());
          txtPassword.setText("");
          txtOperatorID.setText("");
        }else{
          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS015_PassIncorrect"));
          txtPassword.requestFocus(true);
          rs.close();
          stmt.close();
          return;
        }
      }else{
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS016_AccountNotFound"));
        txtOperatorID.requestFocus(true);
        rs.close();
        stmt.close();
        return;
      }
      stmt.close();
      rs.close();
    }catch(Exception ex){
      ex.printStackTrace();
    }
 }


  void btnOK_keyPressed(KeyEvent e) {
           DoEvent(e);
    }

  void txtOperatorID_keyPressed(KeyEvent e) {
    DoEvent(e);
  }

  void txtPassword_keyPressed(KeyEvent e) {
    DoEvent(e);
  }
}

class PanelLogOff_btnOK_actionAdapter implements java.awt.event.ActionListener {
  PanelLogOff adaptee;

  PanelLogOff_btnOK_actionAdapter(PanelLogOff adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class PanelLogOff_btnOK_keyAdapter extends java.awt.event.KeyAdapter {
  PanelLogOff adaptee;

  PanelLogOff_btnOK_keyAdapter(PanelLogOff adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.btnOK_keyPressed(e);
  }
}

class PanelLogOff_txtOperatorID_keyAdapter extends java.awt.event.KeyAdapter {
  PanelLogOff adaptee;

  PanelLogOff_txtOperatorID_keyAdapter(PanelLogOff adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtOperatorID_keyPressed(e);
  }
}

class PanelLogOff_txtPassword_keyAdapter extends java.awt.event.KeyAdapter {
  PanelLogOff adaptee;

  PanelLogOff_txtPassword_keyAdapter(PanelLogOff adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtPassword_keyPressed(e);
  }
}