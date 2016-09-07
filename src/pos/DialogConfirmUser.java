package pos;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

/**
 * <p>Title: Confirm User</p>
 * <p>Description: alow to access functions importance (admin only) </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM</p>
 * @author : Vinh Le
 * @version 1.0
 */

public class DialogConfirmUser extends JDialog {
  JPanel panel1 = new JPanel();
  JLabel jLabel4 = new JLabel();
  JTextField txtoperator = new JTextField();
  JLabel jLabel2 = new JLabel();
  JPasswordField txtpassword = new JPasswordField();
  BJButton btnOK = new BJButton();
  BJButton btnCancel = new BJButton();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel1 = new JLabel();
  FrameMain frmMain;
  DataAccessLayer DAL;
  Utils ut = new Utils();

  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  public boolean done = false;

  public DialogConfirmUser(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogConfirmUser() {
    this(null, "", false);
  }
  public DialogConfirmUser(Frame frame, String title, boolean modal, FrameMain frmMain) {
   super(frame, title, modal);
   try {
     this.frmMain = frmMain;
     this.DAL = frmMain.getDAL();

     jbInit();
   }
   catch(Exception ex) {
     ex.printStackTrace();
   }
  }

  private void jbInit() throws Exception {

    panel1.setLayout(null);
    DAL.getConnfigParameter();
    lang = DAL.getFrameLabel(ut.sLanguague, ut.sCountry);
    panel1.setBackground(new Color(121, 152, 198));
    panel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    panel1.setForeground(Color.black);
    panel1.setDebugGraphicsOptions(0);
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("Account") + ":");
    jLabel4.setBounds(new Rectangle(40, 88, 104, 15));
    txtoperator.setPreferredSize(new Dimension(6, 21));
    txtoperator.setText("");
    txtoperator.setBounds(new Rectangle(128, 86, 196, 21));
    txtoperator.addKeyListener(new DialogConfirmUser_txtoperator_keyAdapter(this));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setText(lang.getString("Password") + ":");
    jLabel2.setBounds(new Rectangle(40, 118, 109, 15));
    txtpassword.setPreferredSize(new Dimension(6, 21));
    txtpassword.setText("");
    txtpassword.setBounds(new Rectangle(129, 114, 195, 21));
    txtpassword.addKeyListener(new DialogConfirmUser_txtpassword_keyAdapter(this));
    btnOK.setBounds(new Rectangle(95, 166, 94, 37));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnOK.setPreferredSize(new Dimension(90, 37));
    btnOK.setToolTipText("Enter");
    btnOK.setText(lang.getString("OK"));
    btnOK.addActionListener(new DialogConfirmUser_btnOK_actionAdapter(this));
    btnCancel.setBounds(new Rectangle(194, 166, 95, 37));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(90, 37));
    btnCancel.setToolTipText("ESC");
    btnCancel.setText(lang.getString("Cancel"));
    btnCancel.addActionListener(new DialogConfirmUser_btnCancel_actionAdapter(this));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 17));
    jLabel5.setForeground(Color.orange);
    jLabel5.setText(lang.getString("PointOfSale"));
    jLabel5.setBounds(new Rectangle(128, 15, 131, 23));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 3, 12));
    jLabel1.setForeground(Color.blue);
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText(lang.getString("MS028_EnterAccount") + ":");
    jLabel1.setBounds(new Rectangle(62, 45, 275, 22));
    getContentPane().add(panel1);
    panel1.add(jLabel1, null);
    panel1.add(jLabel4, null);
    panel1.add(jLabel2, null);
    panel1.add(txtoperator, null);
    panel1.add(txtpassword, null);
    panel1.add(btnCancel, null);
    panel1.add(btnOK, null);
    panel1.add(jLabel5, null);
    btnOK.requestFocus();
    this.setSize(new Dimension(380, 260));
    this.setResizable(false);
  }
  void btnOK_actionPerformed(ActionEvent e) {
    DefineEncrypt enc=new DefineEncrypt();

    ResultSet rs = null;
    String empId = txtoperator.getText().trim();
    String pass = txtpassword.getText().trim();
    Connection conn = null;
    String StoreID = DAL.getStoreID();
    String registerID = DAL.getregisterID();

// init Oracle conection

    try {

      if (txtoperator.getText().trim().equals("")) {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS029_AccNotNull"));
        txtoperator.requestFocus(true);
        return;
      }
      rs = DAL.executeQueries(
          "select PASSWD, EMP_ID,ROLE_ID from BTM_POS_EMPLOYEE where  EMP_CDE='" +
          ut.putCommaToString(empId) + "' and STORE_ID =" + DAL.getStoreID());
      if (rs.next()) {
        if(!rs.getString("ROLE_ID").equalsIgnoreCase("1")){
          ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS030_NotPermission"));
          txtoperator.setSelectionStart(0);
          txtoperator.setSelectionEnd(txtoperator.getText().length());
          txtpassword.setText("");
          txtoperator.requestFocus(true);
          return;
      }
        String sDecrypt = enc.decryptData(rs.getString("PASSWD"));
        if (pass.equals(sDecrypt)) {
          done = true;
          this.dispose();
        }
        else {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS015_PassIncorrect"));
          txtpassword.setText("");
          txtpassword.requestFocus(true);
          return;
        }
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS016_AccountNotFound"));
        txtoperator.setSelectionStart(0);
        txtoperator.setSelectionEnd(txtoperator.getText().length());
        txtpassword.setText("");
        txtoperator.requestFocus(true);
        return;
      }
      rs.getStatement().close();
    } // end try
    catch (Exception e1) {
      e1.printStackTrace();
    }

  }

  void btnCancel_actionPerformed(ActionEvent e) {
    done = false;
    this.dispose();
  }

  void txtoperator_keyPressed(KeyEvent e) {
    DoEvent(e);
  }
  void DoEvent(KeyEvent e)
  {
    if (e.getKeyCode() == e.VK_ENTER){
      btnOK.doClick();
    }else if (e.getKeyCode() == e.VK_ESCAPE){
      btnCancel.doClick();
    }
  }

  void txtpassword_keyPressed(KeyEvent e) {
    DoEvent(e);
  }

}

class DialogConfirmUser_btnOK_actionAdapter implements java.awt.event.ActionListener {
  DialogConfirmUser adaptee;

  DialogConfirmUser_btnOK_actionAdapter(DialogConfirmUser adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class DialogConfirmUser_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogConfirmUser adaptee;

  DialogConfirmUser_btnCancel_actionAdapter(DialogConfirmUser adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogConfirmUser_txtoperator_keyAdapter extends java.awt.event.KeyAdapter {
  DialogConfirmUser adaptee;

  DialogConfirmUser_txtoperator_keyAdapter(DialogConfirmUser adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtoperator_keyPressed(e);
  }
}

class DialogConfirmUser_txtpassword_keyAdapter extends java.awt.event.KeyAdapter {
  DialogConfirmUser adaptee;

  DialogConfirmUser_txtpassword_keyAdapter(DialogConfirmUser adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtpassword_keyPressed(e);
  }
}
