package pos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import common.DataAccessLayer;
import common.*;
import java.sql.*;
import common.*;
import common.Utils;
import oracle.jdbc.*;
import btm.attr.*;
import java.util.*;
import btm.swing.*;
import java.io.*;
import java.sql.Date;
import javax.swing.border.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Anh
 * @version 1.0
 */

public class FrameLogIn extends Frame {
//  String posVers=" - Ver 1.11";//20-Jul
//  String posVers=" - Ver 1.12";//28-06: set pass for Void, Return, Report, Even, Regis Close; Search TXN by item, Total
//  String posVers=" - Ver 1.14";//28-06: Line by Line
//  String posVers=" - Ver 1.15";//28-06: Line by Line
//  String posVers=" - Ver 1.16";//28-06: Line by Line
//  String posVers=" - Ver 1.18";//28-06: Line by Line
//  String posVers=" - Ver 1.20";//10-8: Cash Panel
//  String posVers=" - Ver 1.21";//10-8: Input Customer
//  String posVers=" - Ver 1.22";//add transID to search TXN
//  String posVers=" - Ver 1.23";//add transID to search TXN
//  String posVers=" - Ver 1.24";//vietnamese
//  String posVers=" - Ver 1.25";//add permission to delete item
//  String posVers=" - Ver 1.26";//save transaction
//  String posVers=" - Ver 1.30";//HOme customer => discount 4%
//  String posVers=" - Ver 1.31";//check systemdate
//  String posVers=" - Ver 1.32";//update time for Close Out
//  String posVers=" - Ver 1.33";//update time for Close Out
//  String posVers=" - Ver 1.34";//chang pass
//  String posVers=" - Ver 1.35";//fix bug promo mix match: ( buy 2 get 1 and A=B=C=D=5, only promo on A)
//  String posVers=" - Ver 1.36";//fix bug in Return Item (round 2 digits instead 3 digit )
//  String posVers=" - Ver 1.37";//fix bug error re-print if customer type= 1
//  String posVers=" - Ver 1.38";// apply Gift Promo
//  String posVers=" - Ver 1.39";// fix bug :random must more efficence
//  String posVers=" - Ver 1.40";// print Unilever revenue
//  String posVers=" - Ver 1.41";// print Unilever revenue
//  String posVers=" - Ver 1.42";// //Remove_Uni_CheckCust
//  String posVers=" - Ver 1.43";// //put Uni_CheckCust again ok
//  String posVers=" - Ver 1.44";// print customer revenue
//  String posVers=" - Ver 1.45";    // add batchjob CustRevenue
//  String posVers=" - Ver 1.46";    // remove comment
//  String posVers=" - Ver 1.50";    // enable deletor= deletor1, deletor2, deletor3...
//  String posVers=" - Ver 1.51";    // check admin user if role_id=1
//  String posVers=" - Ver 2.01";    // POS for Franchise
//  String posVers=" - Ver 3.01";    // POS for Vietnamese
//  String posVers=" - Ver 3.2";    // remove Admin for Report, disable Xoa GD, Add quantity for Report,
//  String posVers=" - Ver 3.03"; //Enable Report for yesterday only
//  String posVers=" - Ver 3.04"; // disable "XemGD" if day=today
  //3.05; 3.06: no
//  String posVers=" - Ver 3.07"; // add sale_id, account viewer, disable input quantity
//  String posVers=" - Ver 3.08"; // add sale_id, account viewer, disable input quantity
//  String posVers=" - Ver 3.09"; // return on other store
//  String posVers=" - Ver 3.10"; // emp 13 digits
  String posVers=" - Ver 3.11"; // disable Luu Giao dich

  Utils Util = new Utils();
  boolean date_flag  = true; //date_flag is true: login date same to system date; date_flag is false: login date difference to system date
  DataAccessLayer DAL = SystemClass.objDAL;
  ResourceBundle lang ;
  JTextField txtstore = new JTextField();
  JTextField txtoperator = new JTextField();
  JTextField txtdrawerfund = new JTextField();
  BJButton btnCancel = new BJButton();
  BJButton btnOK = new BJButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JPasswordField txtpassword = new JPasswordField();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel2 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel3 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  GridLayout gridLayout1 = new GridLayout();
  BJPanel jPanel4 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel5 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel6 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel7 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BorderLayout borderLayout1 = new BorderLayout();
  TitledBorder titledBorder1;
  Utils ut = new Utils();
  JLabel txtstorename = new JLabel();
  ////////////////// TUAN ANH

  //////////

  public FrameLogIn() {
    try {
      DAL.getConnfigParameter();
      lang = DataAccessLayer.getFrameLabel(Util.sLanguague, Util.sCountry);
      jbInit();

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    DataAccessLayer DAL = new DataAccessLayer();
//    DAL.getOracleConnect();
    DAL.getConnfigParameter();
    lang = DAL.getFrameLabel(Util.sLanguague, Util.sCountry);
    titledBorder1 = new TitledBorder("");
    this.setLayout(borderLayout1);
    txtstore.setBackground(new Color(236, 233, 216));
    txtstore.setEnabled(true);
    txtstore.setDoubleBuffered(false);
    txtstore.setEditable(false);
    txtstore.setText("");
    txtstore.setBounds(new Rectangle(156, 2, 195, 21));
    txtstore.addActionListener(new FrameLogIn_txtstore_actionAdapter(this));
    txtstore.addMouseListener(new FrameLogIn_txtstore_mouseAdapter(this));
    txtstore.addKeyListener(new FrameLogIn_txtstore_keyAdapter(this));
    txtstore.addActionListener(new FrameLogIn_txtstore_actionAdapter(this));
    txtoperator.setPreferredSize(new Dimension(6, 21));
    txtoperator.setText("");
    txtoperator.setBounds(new Rectangle(156, 20, 196, 21));
    txtoperator.addFocusListener(new FrameLogIn_txtoperator_focusAdapter(this));
    txtoperator.addKeyListener(new FrameLogIn_txtoperator_keyAdapter(this));
    txtdrawerfund.setText("");
    txtdrawerfund.setBounds(new Rectangle(156, 6, 195, 21));
    txtdrawerfund.addKeyListener(new FrameLogIn_txtdrawerfund_keyAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText("ESC");
    btnCancel.setText(lang.getString("Cancel"));
    btnCancel.addKeyListener(new FrameLogIn_btnCancel_keyAdapter(this));
    btnCancel.addActionListener(new FrameLogIn_btnCancel_actionAdapter(this));
    btnCancel.addActionListener(new FrameLogIn_btnCancel_actionAdapter(this));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnOK.setToolTipText("Enter");
    btnOK.setText(lang.getString("OK"));
    btnOK.addKeyListener(new FrameLogIn_btnOK_keyAdapter(this));
    btnOK.addActionListener(new FrameLogIn_btnOK_actionAdapter(this));
    btnOK.requestFocus();
  //  btnOK.addActionListener(new FrameLogIn_btnOK_actionAdapter(this));
    this.setBackground(new Color(121, 152, 198));
    this.setResizable(false);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setText(lang.getString("StoreID") + ":");
    jLabel1.setBounds(new Rectangle(42, 5, 95, 15));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setText(lang.getString("Password") +  ":");
    jLabel2.setBounds(new Rectangle(43, 15, 109, 15));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setText(lang.getString("DrawerFund") + ":");
    jLabel3.setBounds(new Rectangle(42, 9, 114, 15));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("Account") + ":");
    jLabel4.setBounds(new Rectangle(41, 22, 104, 15));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 17));
    jLabel5.setForeground(Color.orange);
    jLabel5.setText(Util.ToUpperString(lang.getString("PointOfSale")));
    jLabel5.setBounds(new Rectangle(155, 3, 131, 23));
    txtpassword.setPreferredSize(new Dimension(6, 21));
    txtpassword.setText("");//Test
    txtpassword.setBounds(new Rectangle(156, 12, 195, 21));
    txtpassword.addKeyListener(new FrameLogIn_txtpassword_keyAdapter(this));
    txtpassword.addActionListener(new FrameLogIn_txtpassword_actionAdapter(this));
    jPanel1.setLayout(gridLayout1);
    gridLayout1.setColumns(0);
    gridLayout1.setRows(5);
    jPanel6.setLayout(null);
    jPanel5.setLayout(null);
    jPanel4.setLayout(null);
    jPanel3.setLayout(null);
    jPanel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    jPanel2.setLayout(null);
    jPanel1.setBorder(null);
    txtstorename.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
    txtstorename.setForeground(Color.red);
    txtstorename.setRequestFocusEnabled(true);
    txtstorename.setBounds(new Rectangle(155, 19, 211, 30));
    this.add(jPanel1, BorderLayout.CENTER);
    jPanel2.add(jLabel5, null);
    jPanel2.add(txtstorename, null);
    jPanel1.add(jPanel2, null);
    jPanel1.add(jPanel6, null);
    jPanel6.add(txtoperator, null);
    jPanel6.add(jLabel4, null);
    jPanel1.add(jPanel5, null);
    jPanel5.add(txtpassword, null);
    jPanel5.add(jLabel2, null);
    jPanel1.add(jPanel4, null);
    jPanel4.add(jLabel3, null);
    jPanel4.add(txtdrawerfund, null);
    jPanel1.add(jPanel3, null);
    jPanel3.add(jLabel1, null);
    jPanel3.add(txtstore, null);
    this.add(jPanel7, BorderLayout.SOUTH);
    jPanel7.add(btnOK, null);
    jPanel7.add(btnCancel, null);
    this.setSize(new Dimension(407, 291));
    this.setTitle("JPOS:" + lang.getString("TT007") +posVers);
    this.setIconImage(new ImageIcon("images/logo1.gif").getImage());
    ///
    checkFile();
    showData();
    System.out.println("POS "+posVers);
  }
  //check file and folder
  void checkFile() {
    if(!ut.isExistFile("./Config.txt")){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS001_NotFoundConfig"));
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
    }else if(!ut.isExistFile("./Pos_BatchJobs")){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS005_NotFoundPBFolder"));
      return;
    }

    String[] outCalcEngFiles = ut.filterFile("./Batch/Import/","",".txt");
    //FTP is finished, need Import (4 files must be in Import\\)
    if(outCalcEngFiles.length == 4){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS006_RunImport"));
    //FTP is not finished, need FTP, then Import
    }else if(outCalcEngFiles.length > 0 && outCalcEngFiles.length < 4){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS007_RunFTP"));
    }

    //FTP is not finished, need FTP, then Import
    outCalcEngFiles = ut.filterFile("./Batch/Export/","",".txt");
    if(outCalcEngFiles.length > 0){
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS007_RunFTP"));
    }

  }
  void showData() {
//    ResultSet rs2 = null;
    // test connection to the database
    if (!DAL.testOracleConnect()) {

      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS008_ConnectFail"));
      return;
    }
    DAL.getOracleConnect();
    if (DAL.getFranchiseCust().length() == 13) {
      ut.showMessage(this, lang.getString("TT002"), lang.getString("MS009_PosFranchise"));
    }

//    System.out.println("store name : "+DAL.getStoreName());
    txtstore.setText(DAL.getStoreID());
    txtstorename.setText(DAL.getStoreName());
    //Nghi
/*    try {
      rs2 = DAL.executeQueries(
          "select workday,draw_fund from BTM_POS_STORE_FUND where workday=to_date('" +
          Util.getSystemDate() + "','yyyy-MM-dd')");
      if (rs2.next()) {
        if (rs2.getDate("workday").toString().equals(Util.getSystemDate())) {
          txtdrawerfund.setText(rs2.getString("draw_fund"));
          txtdrawerfund.setEditable(false);
          return;
        }
      }

    }
    catch (Exception ex) {

    }*/

  }

  boolean isValidate() {
    if (txtoperator.getText().trim().equals("")) {
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS010_ForgotAccount"));
      txtoperator.setText("");
      return false;
    }
    if (txtdrawerfund.getText().trim().equals("")) {
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS011_ForgotDrawerFund"));
      txtdrawerfund.setText("");
      return false;
    }
    if (Util.isIntString(txtdrawerfund.getText().trim()) == false) {
      ut.showMessage(this,lang.getString("TT001"), lang.getString("MS012_DrawerFundIsNum"));
      txtdrawerfund.setText("");
      return false;

    }else{
      if(Integer.parseInt(txtdrawerfund.getText().trim())==0){
        ut.showMessage(this,lang.getString("TT001"), lang.getString("MS013_DrawerFundGreater0"));
        txtdrawerfund.setText("");
        return false;
      }

    }
    return true;

  }

  void btnCancel_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  void btnOK_actionPerformed(ActionEvent e) {
    DefineEncrypt enc=new DefineEncrypt();

    ResultSet rs = null;
    ResultSet rs1 = null;
    String sql="";

    String empId = txtoperator.getText().trim();
    String pass = txtpassword.getText().trim();
    String drawIn = txtdrawerfund.getText().trim();
    Connection conn = null;
    String StoreID = DAL.getStoreID();
    String registerID = DAL.getregisterID();

// init Oracle conection

    try {
      //check system date with server date
      if(!ut.checkSystemDate(DAL)){
        ut.showMessage(this,lang.getString("TT001"), lang.getString("MS014_ChangeDate")+" " +ut.getServerDateTime(DAL)+"!");
        return;
      }

      if (!isValidate())
        return;
      rs = DAL.executeQueries(
          "select EMP_ID, PASSWD from BTM_POS_EMPLOYEE where EMP_CDE='" + ut.putCommaToString(empId) + "' and STORE_ID =" + DAL.getStoreID());
      if (rs.next()) {
        String sDecrypt = enc.decryptData(rs.getString("PASSWD"));
        if (pass.equals(sDecrypt)) {

          Transaction trans = new Transaction(0,
                                    Integer.parseInt(DAL.getStoreID()),
                                    empId, "",
                                    Integer.parseInt(DAL.getregisterID()),"");
          DAL.setEmpPOSID(txtoperator.getText());
          DAL.setEmpPosID1(rs.getString("emp_id"));
          FrameMain frmMain = new FrameMain(trans);

          if (txtdrawerfund.isEditable()) {
//                        System.out.println("===================== Ins Ver");
            rs1 = DAL.executeQueries("INSERT INTO BTM_POS_STORE_FUND VALUES( " +
                                     Integer.parseInt(DAL.getStoreID()) + "," +
                                     Integer.parseInt(DAL.getregisterID()) +
                                     ",'" + rs.getString("emp_id") + "'," + ut.putCommaToString(drawIn) +
                                     ", 0,'"+ DAL.getCurrencyID() +"' ,to_Date('" +
                                     ut.getSystemDateTime1() + "','" + ut.DATETIME_FORMAT2+"'),0,0,'"+posVers+"',1)");
//            System.out.println(rs1);
            rs1.getStatement().close();

            btnOK.requestFocus(true);
            frmMain.pnlMain.btnAdmin.requestFocus(true);

          }else{
            sql="update BTM_POS_STORE_FUND set seq=seq+1,VERSION='"+posVers+"' where STORE_ID='"+DAL.getStoreID()+"' and REGISTER_ID ='"+DAL.getregisterID()+"' and CASHIER_ID ='"+rs.getString("emp_id")+"' and trunc(workday)=to_date('" + ut.getSystemDate() + "','yyyy-MM-dd')";
//            System.out.println(sql);
            DAL.executeQueries(sql);
//            System.out.println("===================== Update Ver");

          }
          if(date_flag && !txtdrawerfund.isEditable()){
            btnOK.requestFocus(true);
            frmMain.pnlMain.btnAdmin.requestFocus(true);
          }
          if(!date_flag && !txtdrawerfund.isEditable()){
//            Transaction trans = new Transaction(0,
//                                              rs.getInt("store_id"),
//                                              rs.getString("cashier_id"), "",
//                                              rs.getInt("register_id")
//                                              );
//          DAL.setEmpPOSID(rs.getString("cashier_id"));
//          FrameMain frmMain = new FrameMain(trans);
//          frmMain.setVisible(true);
          frmMain.showScreen(Constant.SCR_REGISTER_CLOSE);
          frmMain.pnlRegisterClose.txtAmountCashVND.requestFocus(true);
          frmMain.pnlRegisterClose.btnCancel.setVisible(false);
          }
//new Transaction();

          //appear center
//          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//          Dimension frameSize = frmMain.getSize();
//          if (frameSize.height > screenSize.height) {
//            frameSize.height = screenSize.height;
//          }
//          if (frameSize.width > screenSize.width) {
//            frameSize.width = screenSize.width;
//          }
//          frmMain.setLocation( (screenSize.width - frameSize.width) / 2,
//                              (screenSize.height - frameSize.height) / 2);
          frmMain.setVisible(true);
          this.dispose();
        }
        else {
          ut.showMessage(this,lang.getString("TT001"), lang.getString("MS015_PassIncorrect"));
          txtpassword.setText("");
          return;
        }
      }
      else {
        ut.showMessage(this,lang.getString("TT001"),  lang.getString("MS016_AccountNotFound"));
        txtoperator.setText("");
        txtpassword.setText("");
//       txtdrawerfund.setText("");
        return;
      }
      rs.getStatement().close();
    } // end try
    catch (Exception e1) {
      e1.printStackTrace();
    }

  }

  void txtpassword_actionPerformed(ActionEvent e) {

  }

  void txtstore_actionPerformed(ActionEvent e) {

  }

  class FrameLogIn_btnCancel_actionAdapter
      implements java.awt.event.ActionListener {
    FrameLogIn adaptee;

    FrameLogIn_btnCancel_actionAdapter(FrameLogIn adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.btnCancel_actionPerformed(e);
    }
  }

  class FrameLogIn_btnOK_actionAdapter
      implements java.awt.event.ActionListener {
    FrameLogIn adaptee;

    FrameLogIn_btnOK_actionAdapter(FrameLogIn adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.btnOK_actionPerformed(e);
    }
  }

  class FrameLogIn_txtpassword_actionAdapter
      implements java.awt.event.ActionListener {
    FrameLogIn adaptee;

    FrameLogIn_txtpassword_actionAdapter(FrameLogIn adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.txtpassword_actionPerformed(e);
    }
  }

  class FrameLogIn_txtstore_actionAdapter
      implements java.awt.event.ActionListener {
    FrameLogIn adaptee;

    FrameLogIn_txtstore_actionAdapter(FrameLogIn adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.txtstore_actionPerformed(e);
    }
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

  void txtdrawerfund_keyPressed(KeyEvent e) {
    DoEvent(e);
  }

  void txtstore_keyPressed(KeyEvent e) {
    DoEvent(e);
  }



  void btnOK_keyPressed(KeyEvent e) {
    DoEvent(e);
  }

  void btnCancel_keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE)
      btnCancel.doClick();
  }

  void txtoperator_focusLost(FocusEvent e) {
    ResultSet rsEMP = null;
          ResultSet rsSTORE_FUND = null;
          String empID = txtoperator.getText().trim();
          txtdrawerfund.setEditable(true);
          txtdrawerfund.setText("");
          if(empID.equals(""))      return;

          String sqlStr = "";
          String sqlEMP = "Select * From BTM_POS_EMPLOYEE Where EMP_CDE = '" + ut.putCommaToString(empID) + "' and STORE_ID =" + DAL.getStoreID().trim();
          if (!DAL.testOracleConnect()) {
            ut.showMessage(this,lang.getString("TT001"),  lang.getString("MS008_ConnectFail"));
             return;
          }
         try{
//           System.out.println(sqlEMP);
           rsEMP = DAL.executeQueries (sqlEMP);
           if(rsEMP.next()){
             sqlStr = "Select WORKDAY, DRAW_FUND, CASHIER_ID, TOTAL_FUND"
                    + " From BTM_POS_STORE_FUND Where" // WORKDAY=to_date('" +
//                      Util.getSystemDate() + "','yyyy-MM-dd') and
                    + " STORE_ID ='" + DAL.getStoreID().trim() +"'"
                    + " and CASHIER_ID ='" + rsEMP.getString("emp_id") +"'"
                    + " and TOTAL_FUND = 0 order by WORKDAY desc";
//             System.out.println(sqlStr);
             rsSTORE_FUND = DAL.executeQueries(sqlStr);
             if (rsSTORE_FUND.next()) { // da co Store_fund --> load data len
//               if (rsSTORE_FUND.getDouble("total_fund")<1){
                 txtdrawerfund.setText(rsSTORE_FUND.getString("DRAW_FUND"));
                 txtdrawerfund.setEditable(false);
                 if(!rsSTORE_FUND.getString("WORKDAY").substring(0,10).equals(Util.getSystemDate())){
                   date_flag = false;
                 }
//               }
             }
             rsSTORE_FUND.getStatement().close();
           }
           rsEMP.getStatement().close();
         }
         catch(Exception ex)
         {
           ex.printStackTrace();
         }
  }

  void txtdrawerfund_keyTyped(KeyEvent e) {
    ut.checkNumber(e);
    ut.limitLenjTextField(e,txtdrawerfund,20);
  }

  void txtoperator_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtoperator,20);
  }

  void txtpassword_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtpassword,20);
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

class FrameLogIn_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  FrameLogIn adaptee;

  FrameLogIn_btnCancel_actionAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
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

class FrameLogIn_txtdrawerfund_keyAdapter extends java.awt.event.KeyAdapter {
  FrameLogIn adaptee;

  FrameLogIn_txtdrawerfund_keyAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtdrawerfund_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtdrawerfund_keyTyped(e);
  }
}

class FrameLogIn_txtstore_keyAdapter extends java.awt.event.KeyAdapter {
  FrameLogIn adaptee;

  FrameLogIn_txtstore_keyAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtstore_keyPressed(e);
  }
}

class FrameLogIn_txtstore_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameLogIn adaptee;

  FrameLogIn_txtstore_mouseAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
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

class FrameLogIn_txtoperator_focusAdapter extends java.awt.event.FocusAdapter {
  FrameLogIn adaptee;

  FrameLogIn_txtoperator_focusAdapter(FrameLogIn adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtoperator_focusLost(e);
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


