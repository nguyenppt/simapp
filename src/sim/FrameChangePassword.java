package sim;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import common.DataAccessLayer;
import java.sql.*;
import common.*;
import common.Utils;
import oracle.jdbc.*;
import btm.attr.*;
import java.util.*;
import btm.swing.*;
import java.io.*;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: Login -> Change Pass</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Vo Ha Thanh Huy
 * @version
 */

public class FrameChangePassword
    extends Frame {
  //  String posVers=" - Ver 1.10";//20-Jul
//  String posVers=" - Ver 1.12";//20-Jul
  public String posVers = " "; //20-Jul

  Utils ut = new Utils();
  DataAccessLayer DAL = new DataAccessLayer();
  public static EmpRight empRight = new EmpRight();
  JTextField txtoperator = new JTextField();
  BJButton btnCancel = new BJButton();
  BJButton btnOK = new BJButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JPasswordField txtOldPassword = new JPasswordField();
  JPanel jPanel1 = new JPanel();
  BJPanel jPanel2 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel3 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  GridLayout gridLayout1 = new GridLayout();
  BJPanel jPanel4 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel5 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel6 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel7 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BorderLayout borderLayout1 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel8 = new JPanel();
  JLabel jLabel3 = new JLabel();
  JPasswordField txtNewPassword = new JPasswordField();
  BJPanel jPanel9 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JLabel jLabel6 = new JLabel();
  JPasswordField txtConfirmPassword = new JPasswordField();
  BJPanel jPanel10 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public FrameChangePassword() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    //DataAccessLayer DAL = new DataAccessLayer();
    //DAL.getConnfigParameter();
    DAL.getOracleConnect();
    //DAL.getConnfigParameter();

//    DAL.getOracleConnect();
    this.setLayout(borderLayout1);
    txtoperator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    txtoperator.setPreferredSize(new Dimension(6, 21));
    txtoperator.setText("");
    txtoperator.setBounds(new Rectangle(192, 13, 196, 21));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    btnCancel.setToolTipText(lang.getString("Cancel"));
    btnCancel.setText(lang.getString("Cancel"));
    btnCancel.addActionListener(new FrameChangePassword_btnCancel_actionAdapter(this));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnOK.setToolTipText(lang.getString("Enter"));
    btnOK.setText(lang.getString("OK"));
    btnOK.addActionListener(new FrameChangePassword_btnOK_actionAdapter(this));
    btnOK.requestFocus();
    this.setBackground(new Color(121, 152, 198));
    this.setResizable(false);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    jLabel2.setText(lang.getString("EnterOldPassword")+":");
    jLabel2.setBounds(new Rectangle(41, 9, 142, 15));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("EnterOperatorID")+":");
    jLabel4.setBounds(new Rectangle(40, 16, 143, 15));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 17));
    jLabel5.setForeground(Color.orange);
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel5.setText(lang.getString("SIM"));
    txtOldPassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    txtOldPassword.setPreferredSize(new Dimension(6, 21));
    txtOldPassword.setText("");
    txtOldPassword.setBounds(new Rectangle(192, 6, 195, 21));
    jPanel1.setLayout(gridLayout1);
    gridLayout1.setColumns(0);
    gridLayout1.setRows(5);
    jPanel6.setLayout(null);
    jPanel5.setLayout(null);
    jPanel4.setLayout(null);
    jPanel3.setLayout(null);
    jPanel2.setOpaque(true);
    jPanel2.setPreferredSize(new Dimension(320, 40));
    jPanel2.setLayout(flowLayout1);
    jPanel8.setBackground(new Color(121, 152, 198));
    jLabel3.setBounds(new Rectangle(41, 9, 140, 15));
    jLabel3.setText(lang.getString("EnterNewPassword")+":");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtNewPassword.setBounds(new Rectangle(191, 6, 195, 21));
    txtNewPassword.setText("");
    txtNewPassword.setPreferredSize(new Dimension(6, 21));
    txtNewPassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    jPanel9.setLayout(null);
    jPanel9.setBounds(new Rectangle(0, 0, 407, 46));
    jLabel6.setBounds(new Rectangle(41, 9, 143, 15));
    jLabel6.setText(lang.getString("EnterConfirmPassword")+":");
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtConfirmPassword.setBounds(new Rectangle(193, 6, 195, 21));
    txtConfirmPassword.setText("");
    txtConfirmPassword.setPreferredSize(new Dimension(6, 21));
    txtConfirmPassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    jPanel10.setLayout(null);
    jPanel10.setBounds(new Rectangle(0, 0, 407, 46));
    this.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel2, null);
    jPanel2.add(jLabel5, null);
    jPanel1.add(jPanel6, null);
    jPanel6.add(jLabel4, null);
    jPanel6.add(txtoperator, null);
    jPanel1.add(jPanel5, null);
    jPanel5.add(jLabel2, null);
    jPanel5.add(txtOldPassword, null);
    jPanel1.add(jPanel4, null);
    jPanel4.add(jPanel9, null);
    jPanel9.add(jLabel3, null);
    jPanel9.add(txtNewPassword, null);
    jPanel1.add(jPanel3, null);
    jPanel3.add(jPanel10, null);
    jPanel10.add(jLabel6, null);
    jPanel10.add(txtConfirmPassword, null);
    this.add(jPanel7, BorderLayout.SOUTH);
    jPanel7.add(btnOK, null);
    jPanel7.add(btnCancel, null);
    this.add(jPanel8, BorderLayout.NORTH);
    this.setSize(new Dimension(407, 291));
    this.setTitle(lang.getString("TT0018"));
    this.setIconImage(new ImageIcon("images/logo1.gif").getImage());
    txtoperator.requestFocus();
  }

  void showData() {
    ResultSet rs2 = null;
    if (!DAL.testOracleConnect()) {
      JOptionPane.showMessageDialog(this, lang.getString("MS008_ConnectFail"),
                                    lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
      return;
    }
  }

  boolean isValidate() {
    if (txtoperator.getText().trim().equals("")) {
      JOptionPane.showMessageDialog(this, lang.getString("MS1029_ForgotEnterOperatorID"),
                                    lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
      return false;
    }
    if (txtOldPassword.getText().trim().equals("")) {
      JOptionPane.showMessageDialog(this, lang.getString("MS1030_ForgotEnterOldPassword"),
                                    lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
      return false;
    }
    if (txtNewPassword.getText().trim().equals("")) {
      JOptionPane.showMessageDialog(this, lang.getString("MS1031_ForgotEnterNewPassword"),
                                    lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
      return false;
    }

    if (!txtNewPassword.getText().trim().equals(txtConfirmPassword.getText())) {
      JOptionPane.showMessageDialog(this,
          lang.getString("MS1032_NewPasswordMustMatchWithConfirmPassword"),
                                    lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
      return false;
    }
    return true;
  }
  boolean changePassword(String sUserName, String sPassword) {
    boolean bLag = true;
    DefineEncrypt enc = new DefineEncrypt();
    ResultSet rs = null;
    try {
      DAL.getOracleConnect();
      String sDecrypt = enc.encryptData(sPassword);
      DAL.executeQueries("update BTM_IM_EMPLOYEE set PASSWD = '"+sDecrypt+"' where EMP_CDE='" +
                              ut.putCommaToString(sUserName) + "'");
    }
    catch (Exception e1) {
      bLag=false;
      e1.printStackTrace();
    }
    return bLag;
  }
  //Check User Name and Password
  boolean checkAccount(String sUserName, String sPassword) {
    boolean bLag = false;
    DefineEncrypt enc = new DefineEncrypt();
    ResultSet rs = null;
    String empCode = sUserName.trim();
    String pass = sPassword.trim();

    try {
      DAL.getOracleConnect();
      rs = DAL.executeQueries("select * from BTM_IM_EMPLOYEE where EMP_CDE='" +
                              ut.putCommaToString(empCode) + "'");
      if (rs.next()) {
        String sDecrypt = enc.decryptData(rs.getString("PASSWD"));
        if (pass.equals(sDecrypt)) {
          return true;
        }
      }
      rs.getStatement().close();
    }
    catch (Exception e1) {
      e1.printStackTrace();
    }

    return bLag;
  }

  //Show Frame Login
  void showFrameLogin() {
    FrameLogIn frame = new FrameLogIn();
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation( (screenSize.width - frameSize.width) / 2,
                      (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    showFrameLogin();
    this.dispose();
  }

  void btnOK_actionPerformed(ActionEvent e) {
    if(!isValidate()) return;
    if (!checkAccount(txtoperator.getText(),txtOldPassword.getText())){
      JOptionPane.showMessageDialog(this, lang.getString("MS1028_OperatorIDAndPasswordNotExist"),
                                    lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
      return;
    }
     if (changePassword(txtoperator.getText(),txtNewPassword.getText())){
       JOptionPane.showMessageDialog(this, lang.getString("MS1026_AccountUpdatedSuccessful"),
                                     lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
       showFrameLogin();
       this.dispose();
     }else{
       JOptionPane.showMessageDialog(this, lang.getString("MS1027_AccountNotUpdated"),
                                     lang.getString("TT010"), JOptionPane.INFORMATION_MESSAGE);
     }
  }
}

class FrameChangePassword_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  FrameChangePassword adaptee;

  FrameChangePassword_btnCancel_actionAdapter(FrameChangePassword adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class FrameChangePassword_btnOK_actionAdapter implements java.awt.event.ActionListener {
  FrameChangePassword adaptee;

  FrameChangePassword_btnOK_actionAdapter(FrameChangePassword adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}
