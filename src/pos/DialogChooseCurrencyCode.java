package pos;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import common.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @version 1.0
 */

public class DialogChooseCurrencyCode extends JDialog {
  DataAccessLayer DAL;
  FrameMain frmMain;
  boolean isOK=false;
  String sql="";
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel2 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel3 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel4 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel5 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JComboBox cboCurrencyCode = new JComboBox();
  JLabel jLabel1 = new JLabel();
  BJButton btnOK = new BJButton();
  BJButton btnCancel = new BJButton();
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  public DialogChooseCurrencyCode(Frame frame, String title, boolean modal,DataAccessLayer DAL,FrameMain frmMain) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      this.DAL=DAL;

      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogChooseCurrencyCode(DataAccessLayer DAL) {
    this(null, "", false,DAL,null);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    //DAL.getConnfigParameter();

    jPanel5.setPreferredSize(new Dimension(10, 10));
    jPanel2.setLayout(null);
    this.setTitle(lang.getString("TT008"));
    this.addKeyListener(new DialogChooseCurrencyCode_this_keyAdapter(this));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setText(lang.getString("MS027_ChooseCur") + ":");
    jLabel1.setBounds(new Rectangle(51, 25, 180, 18));
    btnOK.setBounds(new Rectangle(38, 84, 130, 37));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnOK.setText(lang.getString("OK"));
    btnOK.addKeyListener(new DialogChooseCurrencyCode_btnOK_keyAdapter(this));
    btnOK.addActionListener(new DialogChooseCurrencyCode_btnOK_actionAdapter(this));
    btnCancel.setBounds(new Rectangle(170, 84, 130, 37));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setText(lang.getString("Cancel"));
    btnCancel.addHierarchyBoundsListener(new DialogChooseCurrencyCode_btnCancel_hierarchyBoundsAdapter(this));
    btnCancel.addActionListener(new DialogChooseCurrencyCode_btnCancel_actionAdapter(this));
    jPanel2.setDebugGraphicsOptions(0);
    cboCurrencyCode.setBounds(new Rectangle(236, 24, 48, 21));
    cboCurrencyCode.addKeyListener(new DialogChooseCurrencyCode_cboCurrencyCode_keyAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jPanel1, BorderLayout.WEST);
    panel1.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(btnOK, null);
    jPanel2.add(btnCancel, null);
    jPanel2.add(cboCurrencyCode, null);
    jPanel2.add(jLabel1, null);
    panel1.add(jPanel3, BorderLayout.SOUTH);
    panel1.add(jPanel4, BorderLayout.EAST);
    panel1.add(jPanel5, BorderLayout.NORTH);
    this.setSize(new Dimension(354, 183));
    //set center screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
    (screenSize.height - frameSize.height) / 2);

    showData();
  }
  void showData(){
    Statement stmt = null;
    sql = "Select * from BTM_POS_CURRENCY_CDE where curr_id <> '"+ DAL.getCurrencyID() +"'";
//    System.out.println(sql);
    ResultSet rs = null;
//    rs = DAL.executeQueries(sql);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      while (rs.next()) {
        cboCurrencyCode.addItem(rs.getString("CURR_ID"));
      }
      rs.close();
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  String getCurrencyCode(){
    return cboCurrencyCode.getSelectedItem().toString();

  }
  boolean isButtonOK(){
    return isOK;

  }

  void DoEvent(KeyEvent e)
   {
     if (e.getKeyCode() == e.VK_ENTER){
       btnOK.doClick();
     }else if (e.getKeyCode() == e.VK_ESCAPE){
       btnCancel.doClick();
     }
   }

  void btnOK_actionPerformed(ActionEvent e) {
    isOK=true;
    this.dispose();
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void btnOK_keyPressed(KeyEvent e) {
    DoEvent(e);
  }

  void btnCancel_ancestorResized(HierarchyEvent e) {

//    DoEvent(e);
  }

  void this_keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE)
      btnCancel.doClick();
  }

  void cboCurrencyCode_keyPressed(KeyEvent e) {
    DoEvent(e);
  }


}

class DialogChooseCurrencyCode_btnOK_actionAdapter implements java.awt.event.ActionListener {
  DialogChooseCurrencyCode adaptee;

  DialogChooseCurrencyCode_btnOK_actionAdapter(DialogChooseCurrencyCode adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class DialogChooseCurrencyCode_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogChooseCurrencyCode adaptee;

  DialogChooseCurrencyCode_btnCancel_actionAdapter(DialogChooseCurrencyCode adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogChooseCurrencyCode_btnOK_keyAdapter extends java.awt.event.KeyAdapter {
  DialogChooseCurrencyCode adaptee;

  DialogChooseCurrencyCode_btnOK_keyAdapter(DialogChooseCurrencyCode adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.btnOK_keyPressed(e);
  }
}

class DialogChooseCurrencyCode_btnCancel_hierarchyBoundsAdapter extends java.awt.event.HierarchyBoundsAdapter {
  DialogChooseCurrencyCode adaptee;

  DialogChooseCurrencyCode_btnCancel_hierarchyBoundsAdapter(DialogChooseCurrencyCode adaptee) {
    this.adaptee = adaptee;
  }
  public void ancestorResized(HierarchyEvent e) {
    adaptee.btnCancel_ancestorResized(e);
  }
}

class DialogChooseCurrencyCode_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogChooseCurrencyCode adaptee;

  DialogChooseCurrencyCode_this_keyAdapter(DialogChooseCurrencyCode adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogChooseCurrencyCode_cboCurrencyCode_keyAdapter extends java.awt.event.KeyAdapter {
  DialogChooseCurrencyCode adaptee;

  DialogChooseCurrencyCode_cboCurrencyCode_keyAdapter(DialogChooseCurrencyCode adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboCurrencyCode_keyPressed(e);
  }
}
