package sim;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import common.DataAccessLayer;
import java.sql.*;
import common.*;
import common.DataAccessLayer;
import common.Utils;
import oracle.jdbc.*;
import btm.attr.*;
import java.util.*;
import btm.swing.*;
import java.io.*;
import java.sql.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Anh
 * @version 1.0
 */

public class FrameLogIn extends Frame {

//  String simVer=" - Ver 1.10";//20-Jul
//  String simVer=" - Ver 1.12";//20-Jul
//  public String simVer=" - Ver 1.13";//20-Jul
//    public String simVer=" - Ver 1.14";//20-Jul
//    public String simVer=" - Ver 1.15";//20-Jul  only disable Transfer and Receipt if DCOM hang
//    public String simVer=" - Ver 1.18";//add Package size
//    public String simVer=" - Ver 1.20";//Add Back room Front room, PO for Fornt Room
//    public String simVer=" - Ver 1.21";//input qty faster for PO, PL, Receipt
//    public String simVer=" - Ver 1.22";//input qty faster for PO, PL, Receipt
//    public String simVer=" - Ver 1.23";//input qty faster for PO, PL, Receipt
//    public String simVer=" - Ver 1.24";//Add UPCS from Receipt for creating PL
//  public String simVer=" - Ver 1.25";//Redesign View/Search Receipt Screen for more filter conditions (using dialog)
//      public String simVer = " - Ver 1.26"; //modify printing Pick List
//      public String simVer = " - Ver 1.27"; //Unit Cost,Stock coun FR ,BR: performance, Price change: logic
//      public String simVer = " - Ver 1.28"; //error when Modify Item
//      public String simVer = " - Ver 1.29"; //error when Add UPC in PO, Enalbe add size for Peace
//      public String simVer = " - Ver 1.30 "; //modify price change /cost change layout
//      public String simVer = " - Ver 1.31 "; //stock count screen
//        public String simVer = " - Ver 1.32 "; //new store setup - duplicate data into unit_cost_hist table
//        public String simVer = " - Ver 1.33 "; //get address delivery from address of selected cbo Store
//        public String simVer = " - Ver 1.34 "; //set seq for product group
//        public String simVer = " - Ver 1.35 "; //birthday's customer
//        public String simVer = " - Ver 1.36 "; //Promotion: multi store
//  public String simVer = " - Ver 1.37 "; //fix bug receipt transfer from store
//  public String simVer = " - Ver 1.38 "; //add scroll in Employee Role
//  public String simVer = " - Ver 1.39 "; //fix bug transfer
//  public String simVer = " - Ver 1.40 "; //fix bug transfer

//  public String simVer = " - Ver 1.40 "; //fix bug transfer
//  public String simVer = " - Ver 1.41 "; //increase the size of UPC column printing//check system date with server
//  public String simVer = " - Ver 1.42 "; //fix convert to double qty for Receipt from Store
 // public String simVer = " - Ver 1.43 "; //fix modify promotion
//  public String simVer = " - Ver 1.45 "; //stock count: add host
// public String simVer = " - Ver 1.48 "; //additional function: transfer FR to BR
// public String simVer = " - Ver 1.49 "; //Damage Goods + RTV

// public String simVer = " - Ver 2.01 "; //Remove BR and FR
// public String simVer = " - Ver 2.03 "; //fix bug: show PO and RTV frontroom
// public String simVer = " - Ver 2.04 "; //fix bug: status for transfer (5/5)
// public String simVer = " - Ver 2.05 "; //fix bug: enable supplier in ProductGroup, modify customer type
// public String simVer = " - Ver 2.06 "; //fix bug: Transfer (Out of fArray), add View Receipt from Transfer and add Seq for Receitp from Store
// public String simVer = " - Ver 2.07 "; // Update Customer Total, add new column Total for Customer
// public String simVer = " - Ver 2.08 "; // add batchjob CustRevenue
//  public String simVer = " - Ver 2.09 "; // PO with barcode print
//    public String simVer = " - Ver 2.10 "; // PO with barcode print
//        public String simVer = " - Ver 2.11 "; // PO with barcode print FIX BUG: LOSS 2 LINES

 // add message to PO print, paging for PO, PanelDMGModify:copy grip data to clipboard
// public String simVer = " - Ver 2.12 ";

 // modify showing history of price change and cost change - sort end_date by desc
// public String simVer = " - Ver 2.13 ";
// public String simVer = " - Ver 2.14 ";// show number of print in PO
// public String simVer = " - Ver 2.15 ";// fix :Missing comma err on Transfer
// public String simVer = " - Ver 2.16 ";// add Status on View Transfer
// public String simVer = " - Ver 2.17 ";// fix :Missing comma err on Receipt From Store
// public String simVer = " - Ver 3.01 ";// fix :update employee to POS  after chang pass
//  public String simVer = " - Ver 3.02 ";// fix : search Receipt from Supp with No PO
//    public String simVer = " - Ver 3.03 ";// fix : add item for PO error for first store in Combobox
//    public String simVer = " - Ver 3.04 ";// fix : enable transfer qty > soh
//    public String simVer = " - Ver 3.05 ";// fix dupp in Transfer, disable TransDamage
//        public String simVer = " - Ver 3.06 ";// disbale From Store, Transfer date in PO, Transfer
//        public String simVer = " - Ver 3.07 ";// DMG good: set rights
         public String simVer = " - Ver 3.08 ";// Emp to 13

  Utils ut = new Utils();
  DataAccessLayer DAL = new DataAccessLayer();
  public static EmpRight empRight= new EmpRight();
  JTextField txtoperator = new JTextField();
  BJButton btnCancel = new BJButton();
  BJButton btnOK = new BJButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JPasswordField txtpassword = new JPasswordField();
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
  BJButton btnChangePassword = new BJButton();
  ResourceBundle lang;
  ////////////////// TUAN ANH

  //////////

  public FrameLogIn() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    DAL.getConnfigParameter();
    lang=DAL.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international
    txtoperator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    txtoperator.setPreferredSize(new Dimension(6, 21));
    txtoperator.setText("");
    txtoperator.setBounds(new Rectangle(159, 13, 196, 21));
    txtoperator.addKeyListener(new FrameLogIn_txtoperator_keyAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    btnCancel.setToolTipText(lang.getString("Cancel"));
    btnCancel.setText(lang.getString("Cancel"));
    btnCancel.addKeyListener(new FrameLogIn_btnCancel_keyAdapter(this));
    btnCancel.addActionListener(new FrameLogIn_btnCancel_actionAdapter(this));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnOK.setToolTipText(lang.getString("OK"));
    btnOK.setText(lang.getString("OK"));
    btnOK.addKeyListener(new FrameLogIn_btnOK_keyAdapter(this));
    btnOK.requestFocus();
    btnOK.addActionListener(new FrameLogIn_btnOK_actionAdapter(this));
    this.setBackground(new Color(121, 152, 198));
    this.setResizable(false);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setText(lang.getString("Password")+":");
    jLabel2.setBounds(new Rectangle(41, 9, 98, 15));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("AccountCode")+":");
    jLabel4.setBounds(new Rectangle(40, 16, 110, 15));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 17));
    jLabel5.setForeground(Color.orange);
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel5.setText(lang.getString("SIM"));
    txtpassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    txtpassword.setPreferredSize(new Dimension(6, 21));
    txtpassword.setText("");
    txtpassword.setBounds(new Rectangle(160, 6, 195, 21));
    txtpassword.addKeyListener(new FrameLogIn_txtpassword_keyAdapter(this));
    txtpassword.addActionListener(new FrameLogIn_txtpassword_actionAdapter(this));
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
    btnChangePassword.setText(lang.getString("ChangePass"));
    btnChangePassword.addActionListener(new FrameLogIn_btnChangePassword_actionAdapter(this));
    btnChangePassword.setToolTipText(lang.getString("ChangePass"));
    btnChangePassword.setBounds(new Rectangle(161, 0, 195, 18));
    btnChangePassword.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    this.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel2, null);
    jPanel2.add(jLabel5, null);
    jPanel1.add(jPanel6, null);
    jPanel6.add(jLabel4, null);
    jPanel6.add(txtoperator, null);
    jPanel1.add(jPanel5, null);
    jPanel5.add(txtpassword, null);
    jPanel5.add(jLabel2, null);
    jPanel1.add(jPanel4, null);
    jPanel4.add(btnChangePassword, null);
    jPanel1.add(jPanel3, null);
    this.add(jPanel7, BorderLayout.SOUTH);
    jPanel7.add(btnOK, null);
    jPanel7.add(btnCancel, null);
    this.add(jPanel8, BorderLayout.NORTH);
    this.setSize(new Dimension(407, 291));
    this.setTitle(lang.getString("TT0113")+simVer);
    this.setIconImage(new ImageIcon("images/logo1.gif").getImage());
    checkFile();
    txtoperator.requestFocus();
    System.out.println(lang.getString("TT014") +simVer);
  }
  //check file and folder




/*
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    txtoperator.setFont(new java.awt.Font("SansSerif", 0, 12));
    txtoperator.setPreferredSize(new Dimension(6, 21));
    txtoperator.setText("");
    txtoperator.setBounds(new Rectangle(159, 13, 196, 21));
    txtoperator.addKeyListener(new FrameLogIn_txtoperator_keyAdapter(this));
    btnCancel.setFont(new java.awt.Font("SansSerif", 1, 13));
    btnCancel.setToolTipText("Esc");
    btnCancel.setText("Cancel");
    btnCancel.addKeyListener(new FrameLogIn_btnCancel_keyAdapter(this));
    btnCancel.addActionListener(new FrameLogIn_btnCancel_actionAdapter(this));
    btnOK.setFont(new java.awt.Font("SansSerif", 1, 12));
    btnOK.setToolTipText("Enter");
    btnOK.setText("OK");
    btnOK.addKeyListener(new FrameLogIn_btnOK_keyAdapter(this));
    btnOK.requestFocus();
    btnOK.addActionListener(new FrameLogIn_btnOK_actionAdapter(this));
    this.setBackground(new Color(121, 152, 198));
    this.setResizable(false);
    jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12));

    jLabel2.setText("Enter Password:");
    jLabel2.setBounds(new Rectangle(41, 9, 98, 15));
    jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel4.setText("Enter Username");
    jLabel4.setBounds(new Rectangle(40, 16, 110, 15));
    jLabel5.setFont(new java.awt.Font("SansSerif", 1, 17));
    jLabel5.setForeground(Color.orange);
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel5.setText("STORE INVENTORY MANAGEMENT");
    txtpassword.setFont(new java.awt.Font("SansSerif", 0, 12));
    txtpassword.setPreferredSize(new Dimension(6, 21));
    txtpassword.setText("");
    txtpassword.setBounds(new Rectangle(160, 6, 195, 21));
    txtpassword.addKeyListener(new FrameLogIn_txtpassword_keyAdapter(this));
    txtpassword.addActionListener(new FrameLogIn_txtpassword_actionAdapter(this));
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
    btnChangePassword.setText("Change Password");
    btnChangePassword.addActionListener(new FrameLogIn_btnChangePassword_actionAdapter(this));
    btnChangePassword.setToolTipText("Enter");
    btnChangePassword.setBounds(new Rectangle(161, 0, 195, 18));
    btnChangePassword.setFont(new java.awt.Font("SansSerif", 1, 12));
    this.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel2, null);
    jPanel2.add(jLabel5, null);
    jPanel1.add(jPanel6, null);
    jPanel6.add(jLabel4, null);
    jPanel6.add(txtoperator, null);
    jPanel1.add(jPanel5, null);
    jPanel5.add(txtpassword, null);
    jPanel5.add(jLabel2, null);
    jPanel1.add(jPanel4, null);
    jPanel4.add(btnChangePassword, null);
    jPanel1.add(jPanel3, null);
    this.add(jPanel7, BorderLayout.SOUTH);
    jPanel7.add(btnOK, null);
    jPanel7.add(btnCancel, null);
    this.add(jPanel8, BorderLayout.NORTH);
    this.setSize(new Dimension(407, 291));
    this.setTitle("JSIM:Login "+simVer);
    this.setIconImage(new ImageIcon("images/logo1.gif").getImage());
    checkFile();
    txtoperator.requestFocus();
    System.out.println("SIM " +simVer);
  }
  //check file and folder

*/







  void checkFile() {
    if(!ut.isExistFile("./Config.txt")){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS002"));
      return;
    }else if(!ut.isExistFile("./file")){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS002_NotFoundFileFolder"));
      return;

    }else if(!ut.isExistFile("./images")){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS003_NotFoundImageFolder"));
      return;

    }else if(!ut.isExistFile("./Batch") || !ut.isExistFile("./Batch/Export") ||!ut.isExistFile("./Batch/Import")){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS004_NotFoundBIEFolder"));
      return;
    }else if(!ut.isExistFile("./Sim_BatchJobs")){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS1126_NotFoundSBFolder"));
      return;
    }


///

//    String[] outCalcEngFiles = ut.filterFile(".\\Batch\\Import\\","",".txt");
//    //FTP is finished, need Import
//    if(outCalcEngFiles.length == 5){
//      ut.showMessage(this,lang.getString("Msg_Warning"), "You must run IMPORT batch job ");
//    //FTP is not finished, need FTP, then Import
//    }else if(outCalcEngFiles.length > 0 && outCalcEngFiles.length < 5){
//      ut.showMessage(this,lang.getString("Msg_Warning"), "You must run FTP at POS, then run IMPORT batch job");
//    }
//
//    //FTP is not finished, need FTP, then Import
//    outCalcEngFiles = ut.filterFile(".\\Batch\\Export\\","",".txt");
//    if(outCalcEngFiles.length > 0){
//      ut.showMessage(this,lang.getString("TT001"), "You must run FTP at POS, then run IMPORT batch job");
//    }



  }

  //showData ();
  void showData (){
     ResultSet rs2=null;
 // test connection to the database
   if(!DAL.testOracleConnect()){
        JOptionPane.showMessageDialog(this, lang.getString("MS008_ConnectFail"),lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
        return;
     }}

  boolean isValidate(){
      if (txtoperator.getText().trim().equals("")){
         JOptionPane.showMessageDialog(this, lang.getString("MS010_ForgotAccount"),lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
         return false;
       }
       if (txtpassword.getText().trim().equals("")){
        JOptionPane.showMessageDialog(this, lang.getString("MS1127_ForgotPassword"),lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
        return false;
      }

       return true;

  }
  void btnCancel_actionPerformed(ActionEvent e) {
    System.exit(0);
  }
  void btnOK_actionPerformed(ActionEvent e) {
    DefineEncrypt enc=new DefineEncrypt();
    ResultSet rs=null;
    ResultSet rs1=null;
    String empCode=txtoperator.getText().trim();
    String pass=txtpassword.getText().trim();
    String empId = null;
    Connection conn = null;
//    String StoreID = DAL.getStoreID();
//    String registerID = DAL.getregisterID();
// init Oracle conection
  try {
    if(!isValidate()) return;
    DAL.getOracleConnect();

    //check system date with server date
    //Nho sua lai
    if(!ut.checkSystemDate(DAL)){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS014_ChangeDate")+ut.getServerDateTime(DAL)+"!");
      return;
    }
    //Run Inventory Management only if SIM_IMP_INV_ITEM_LOC_DCOM is completed
    Date today = Date.valueOf(ut.getSystemDate());
    if(!ut.batchjobSimComplete(ut.SIM_IMP_INV_ITEM_LOC_DCOM, ut.STATUS_COMPLETE,ut.addDate(today,-1), DAL)){
      ut.showMessage(this,lang.getString("TT001"), lang.getString( "MS1000_RunBatchJob"));
    }

//    System.out.println("select * from BTM_IM_EMPLOYEE where EMP_CDE='" +ut.putCommaToString( empCode) +"'");
    rs=DAL.executeQueries("select * from BTM_IM_EMPLOYEE where EMP_CDE='" + ut.putCommaToString(empCode)+"'");
    if (rs.next()){
       String sDecrypt = enc.decryptData(rs.getString("PASSWD"));
//      String sDecrypt = rs.getString("passwd");
       if (pass.equals(sDecrypt)  ){
//new Transaction();
//            Transaction trans=new Transaction(0,Integer.parseInt(DAL.getStoreID()),empId,"",Integer.parseInt(DAL.getregisterID()));
            empId = rs.getString("EMP_ID").toString().trim();
            DAL.setEmpID(empId);
            DAL.setEmpCode(txtoperator.getText().trim());
            FrameMainSim frmMain   = new FrameMainSim();
//
            //appear center
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = frmMain.getSize();
            if (frameSize.height > screenSize.height) {
              frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
              frameSize.width = screenSize.width;
            }
            frmMain.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
            frmMain.setVisible(true);
            //assign emp right
            empRight.initData(DAL, empId);

            updateLoginHist();
            this.dispose();
       }
       else {
         JOptionPane.showMessageDialog(this, lang.getString("MS015_PassIncorrect"),lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
         txtpassword.setText("");
         return;
       }
    }
    else{
       JOptionPane.showMessageDialog(this, lang.getString("MS999_OperatorIDNotExist"),lang.getString("TT001"), JOptionPane.INFORMATION_MESSAGE);
       txtoperator.setText("");
       txtpassword.setText("");
       return;
     }
     rs.getStatement().close();
 }// end try
  catch (Exception e1) {
       e1.printStackTrace();
       }
  }
 void updateLoginHist(){
   ResultSet rs = null;
   String sql="";


   sql= "select * from BTM_IM_LOGIN_HIST where store_id="+ DAL.getStoreID() +" and host_id="+DAL.getHostID() +" and emp_id="+ DAL.getEmpID() +" and trunc(workday)=to_date('" + ut.getSystemDate() + "','yyyy-MM-dd')";
   rs = DAL.executeQueries(sql);
   try {
     //update
     if (rs.next()) {
       sql = "update BTM_IM_LOGIN_HIST set seq=seq+1,VERSION='" + simVer +
           "' where STORE_ID='" + DAL.getStoreID() + "' and host_id ='" +
           DAL.getHostID() + "' and emp_id ='" + DAL.getEmpID() +
           "' and trunc(workday)=to_date('" + ut.getSystemDate() +
           "','yyyy-MM-dd')";
//       System.out.println(sql);
       DAL.executeUpdate(sql);
       //insert

     }
     else {
       sql = " INSERT INTO BTM_IM_LOGIN_HIST values('" + DAL.getStoreID() +
           "','" + DAL.getHostID() + "','" + DAL.getEmpID() + "',to_Date('" +
           ut.getSystemDateTime1() + "','" + ut.DATETIME_FORMAT2 + "'),'" +
           simVer + "',1)";
//       System.out.println(sql);
       DAL.executeUpdate(sql);
       //insert
     }
     rs.getStatement().close();
   } // end try
   catch (Exception e1) {
     e1.printStackTrace();
   }

 }
  public EmpRight getEmpRight(EmpRight er) {
    return er;
  }

  void txtpassword_actionPerformed(ActionEvent e) {

  }

  void txtstore_actionPerformed(ActionEvent e) {

  }
  void txtoperator_keyPressed(KeyEvent e) {
//    System.out.println(e.getKeyCode());

    DoEvent(e);
  }

  void txtpassword_keyPressed(KeyEvent e) {
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

  void btnOK_keyPressed(KeyEvent e) {
    DoEvent(e);
  }

  void btnCancel_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE )
      btnCancel.doClick();
  }

  void txtoperator_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtoperator,20);
  }

  void txtpassword_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtpassword,20);
  }

  void btnChangePassword_actionPerformed(ActionEvent e) {
    FrameChangePassword frame = new FrameChangePassword();
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
    this.dispose();
  }
}

class FrameLogIn_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  FrameLogIn adaptee;

  FrameLogIn_btnCancel_actionAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class FrameLogIn_btnOK_actionAdapter implements java.awt.event.ActionListener {
  FrameLogIn adaptee;

  FrameLogIn_btnOK_actionAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class FrameLogIn_txtpassword_actionAdapter implements java.awt.event.ActionListener {
  FrameLogIn adaptee;

  FrameLogIn_txtpassword_actionAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtpassword_actionPerformed(e);
  }
}

class FrameLogIn_txtstore_actionAdapter implements java.awt.event.ActionListener {
  FrameLogIn adaptee;

  FrameLogIn_txtstore_actionAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtstore_actionPerformed(e);
  }
}

class FrameLogIn_txtpassword_keyAdapter extends java.awt.event.KeyAdapter {
  FrameLogIn adaptee;

  FrameLogIn_txtpassword_keyAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtpassword_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtpassword_keyTyped(e);
  }
}

class FrameLogIn_txtoperator_keyAdapter extends java.awt.event.KeyAdapter {
  FrameLogIn adaptee;

  FrameLogIn_txtoperator_keyAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtoperator_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtoperator_keyTyped(e);
  }
}

class FrameLogIn_btnOK_keyAdapter extends java.awt.event.KeyAdapter {
  FrameLogIn adaptee;

  FrameLogIn_btnOK_keyAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.btnOK_keyPressed(e);
  }
}

class FrameLogIn_btnCancel_keyAdapter extends java.awt.event.KeyAdapter {
  FrameLogIn adaptee;

  FrameLogIn_btnCancel_keyAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.btnCancel_keyPressed(e);
  }
}

class FrameLogIn_btnChangePassword_actionAdapter implements java.awt.event.ActionListener {
  FrameLogIn adaptee;

  FrameLogIn_btnChangePassword_actionAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnChangePassword_actionPerformed(e);
  }
}
