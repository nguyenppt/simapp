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

public class DialogCheckTransaction extends JDialog {
  JPanel panel1 = new JPanel();
  JTextField txtTransId = new JTextField();
  FrameMain frmMain;
  DataAccessLayer DAL;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);

  public boolean done = false;
  JLabel lblAccount = new JLabel();

  public DialogCheckTransaction(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogCheckTransaction() {
    this(null, "", false);
  }
  public DialogCheckTransaction(Frame frame, String title, boolean modal, FrameMain frmMain) {
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
    txtTransId.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 13));
    txtTransId.setPreferredSize(new Dimension(6, 21));
    txtTransId.setText("");
    txtTransId.setBounds(new Rectangle(11, 34, 200, 31));
    txtTransId.addKeyListener(new DialogCheckTransaction_txtTransId_keyAdapter(this));
    lblAccount.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblAccount.setText("Ma giao dich" + ":");
    lblAccount.setBounds(new Rectangle(11, 7, 127, 22));
    getContentPane().add(panel1);
    panel1.add(lblAccount);
    panel1.add(txtTransId, null);
    this.setSize(new Dimension(225, 100));
    this.setResizable(false);

  }

  public void SignIn() {
    DefineEncrypt enc=new DefineEncrypt();

    ResultSet rs = null;
    String transactionID = txtTransId.getText().trim();
    Connection conn = null;
    String user = "";
    String sql="";



// init Oracle conection

    try {
      sql="select TRANS_ID from BTM_POS_DETAIL_TRANS where  TRANS_ID like '%"+transactionID+"%'"
          + " and STORE_ID =" + DAL.getStoreID();
//      System.out.println(sql);
      rs = DAL.executeQueries(sql);


      if (rs.next() && !transactionID.equals("")) {
          done = true;
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

  public String getTransId() {
    String transId="";
    transId =txtTransId.getText();
    return transId;
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

  void txtTransId_keyPressed(KeyEvent e) {
    DoEvent(e);
  }

}

class DialogCheckTransaction_txtTransId_keyAdapter extends java.awt.event.KeyAdapter {
  DialogCheckTransaction adaptee;

  DialogCheckTransaction_txtTransId_keyAdapter(DialogCheckTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtTransId_keyPressed(e);
  }
}
