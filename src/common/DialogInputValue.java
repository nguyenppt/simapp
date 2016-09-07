package common;




import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Font;
import common.*;
import java.util.*;

/**
 * <p>Title: Check Account</p>
 * <p>Description: alow to access functions importance (admin only) </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM</p>
 * @author :
 * @version 1.0
 */

public class DialogInputValue extends JDialog {
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  JPanel panel1 = new JPanel();
  JTextField txtValue = new JTextField();
  Frame frmMain;
//  Utils Util = new Utils();
  public int value = 0;
  JLabel lblAccount = new JLabel();
  String observerID = "";

  public DialogInputValue(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogInputValue() {
    this(null, "", false);
  }
  public DialogInputValue(Frame frame, String title, boolean modal, Frame frmMain) {
   super(frame, title, modal);
   try {
     this.frmMain = frmMain;
//     this.DAL = frmMain.getDAL();
     jbInit();
   }
   catch(Exception ex) {
     ex.printStackTrace();
   }
  }

  private void jbInit() throws Exception {
    panel1.setLayout(null);
    panel1.setBackground(new Color(121, 152, 198));
    panel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    panel1.setForeground(Color.black);
    panel1.setDebugGraphicsOptions(0);
    txtValue.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 13));
    txtValue.setPreferredSize(new Dimension(6, 21));
    txtValue.setText("2");
    txtValue.setBounds(new Rectangle(11, 34, 200, 31));
    txtValue.addKeyListener(new DialogInputValue_txtValue_keyAdapter(this));
    lblAccount.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblAccount.setText(lang.getString("InputValue") + ":");
    lblAccount.setBounds(new Rectangle(11, 7, 109, 22));
    getContentPane().add(panel1);
    panel1.add(lblAccount);
    panel1.add(txtValue, null);
    this.setSize(new Dimension(225, 100));
    this.setResizable(false);
  }
  void  Input() {
         Utils ut=new Utils();
        if(ut.isIntString(txtValue.getText().trim())){
          value =Integer.parseInt( txtValue.getText().trim());
        }else{
          value=2;
        }
        this.dispose();

  }

    void Escape() {
    value = -1;
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
      Input();
    }else if (e.getKeyCode() == e.VK_ESCAPE){
      Escape();
    }
  }

  void txtValue_keyPressed(KeyEvent e) {
    DoEvent(e);
  }

}

class DialogInputValue_txtValue_keyAdapter extends java.awt.event.KeyAdapter {
  DialogInputValue adaptee;

  DialogInputValue_txtValue_keyAdapter(DialogInputValue adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtValue_keyPressed(e);
  }
}
