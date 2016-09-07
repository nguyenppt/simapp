package pos;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

/**
 * <p>Title: Check Account</p>
 * <p>Description: alow to access functions importance (admin only) </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM</p>
 * @author : Phuong Nguyen
 * @version 1.0
 */

public class DialogCheckAccount extends JDialog {
  JPanel panel1 = new JPanel();
  JPasswordField txtpassword = new JPasswordField();
  FrameMain frmMain;
  DataAccessLayer DAL;
  Utils ut = new Utils();
  String userFlag="";
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);

  public boolean done = false;
  JLabel lblAccount = new JLabel();
  String observerID = "";

  public DialogCheckAccount(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogCheckAccount() {
    this(null, "", false);
  }
  public DialogCheckAccount(Frame frame, String title, boolean modal, FrameMain frmMain) {
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
    txtpassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 13));
    txtpassword.setPreferredSize(new Dimension(6, 21));
    txtpassword.setText("");
    txtpassword.setBounds(new Rectangle(11, 34, 200, 31));
    txtpassword.addKeyListener(new DialogCheckAccount_txtpassword_keyAdapter(this));
    lblAccount.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblAccount.setText(lang.getString("AccountCode") + ":");
    lblAccount.setBounds(new Rectangle(11, 7, 127, 22));
    getContentPane().add(panel1);
    panel1.add(lblAccount);
    panel1.add(txtpassword, null);
    this.setSize(new Dimension(225, 100));
    this.setResizable(false);

  }
   public void setUserFlag(String userFlag) {
     this.userFlag=userFlag;
   }
  public void SignIn() {
    DefineEncrypt enc=new DefineEncrypt();

    ResultSet rs = null;
    String pass = txtpassword.getText().trim();
    Connection conn = null;
    String user = "";


    if (userFlag.equals(ut.VIEWER_ACCOUNT)){
      user=DAL.getViewerAccount();
    }else{
      user=DAL.getObserverAccount();
    }

// init Oracle conection

    try {
      rs = DAL.executeQueries(
          "select PASSWD, EMP_CDE ,EMP_ID from BTM_POS_EMPLOYEE where  EMP_CDE like '%"+user+"%'"
          + " and STORE_ID =" + DAL.getStoreID());
//      System.out.println("select PASSWD, EMP_CDE ,EMP_ID from BTM_POS_EMPLOYEE where  EMP_CDE= '"+DAL.getObserverAccount()+"'"
//          + " and STORE_ID =" + DAL.getStoreID());
      while (rs.next()) {
        String sDecrypt = enc.decryptData(rs.getString("PASSWD"));
        if (pass.equals(sDecrypt)) {
          done = true;
          observerID = rs.getString("EMP_ID");
          break;
        }
        else {
          observerID = "";
          done = false;
        }
      }
      this.dispose();
      rs.getStatement().close();
    } // end try
    catch (Exception e1) {
      e1.printStackTrace();
    }

  }

  public void Escape() {
    done = false;
    this.dispose();
  }

  public String getObserverID() {
    return observerID;
  }

  void txtoperator_keyPressed(KeyEvent e) {
    DoEvent(e);
  }
  void DoEvent(KeyEvent e)
  {
    if (e.getKeyCode() == e.VK_ENTER){
      SignIn();
    }else if (e.getKeyCode() == e.VK_ESCAPE){
      Escape();
    }
  }

  void txtpassword_keyPressed(KeyEvent e) {
    DoEvent(e);
  }

}

class DialogCheckAccount_txtpassword_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCheckAccount adaptee;

  DialogCheckAccount_txtpassword_keyAdapter(DialogCheckAccount adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtpassword_keyPressed(e);
  }
}
