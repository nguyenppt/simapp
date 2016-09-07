package pos;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import java.util.Vector;
import btm.attr.*;
import javax.swing.event.*;
import java.io.*;
import sun.net.ftp.*;
import sun.net.*;
import java.util.StringTokenizer;
import java.util.*;
import java.sql.Date;

/**
 * <p>Title:Main - Qly GD - Chay Batch Job </p>
 * <p>Description: Batch job auto run from top, you must run export batch job, then FTP ,
 *  and run import batch job , export file name is BatchName_StoreIDSystemdate (Should modify on grid after)</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Vinh.Le
 * @version 1.0
 */

public class PanelRunPosBatchJobs extends JPanel {
  public final int NUMBER_IMPORT_POS_FILE = 3;
  DataAccessLayer DAL;
  FrameMain frmMain;
  boolean flag =  false;
  String customerId1 = "";
  String customerId2 = "";
  Statement stmt = null;
  String arrBatchName[] = new String[]{"PosExpPaidInOut","PosExpStoreFund",
      "PosExpSaleItemLoc","PosExpSaleReturnItemLoc","PosSumDailyTrans","PosExpSOH",
      "PosExpSumDailyTrans","PosImpCustomerRevenue","PosImpEmployee","PosImpCustomer","PosImpExchangeRate",
      "PosImpItemLoc","PosImpMixMatchPromo","PosImpThresholdPromo"};

  CustomerBusObj cusBusObj = new CustomerBusObj();
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  Vector vSql = new Vector();
  boolean flag_Customer_ID = false;
  JPanel jPanel9 = new JPanel();

  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel11 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  GridLayout gridLayout2 = new GridLayout();
  BorderLayout borderLayout6 = new BorderLayout();
  BJButton btnCancel = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnRun = new BJButton();
  BJButton btnFtp = new BJButton();

  //
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  int rowsNum = 5;
  JPanel pnlTop = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  JButton btnToDate = new JButton();
  JTextField txtToDate = new JTextField();
  JLabel lblToDate = new JLabel();
  JButton btnFromDate = new JButton();
  JTextField txtFromDate = new JTextField();
  JLabel lblFromDate = new JLabel();

  public PanelRunPosBatchJobs(FrameMain frmMain) {
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
    this.setPreferredSize(new Dimension(800,600));
    this.setLayout(borderLayout1);
    jPanel9.setPreferredSize(new Dimension(800, 430));
    jPanel9.setLayout(borderLayout4);
    jPanel11.setPreferredSize(new Dimension(800, 430));
    jPanel11.setLayout(gridLayout2);
//    txtComment.setPreferredSize(new Dimension(0, 0));
    jScrollPane1.setPreferredSize(new Dimension(1000, 250));
    jScrollPane1.setVerifyInputWhenFocusTarget(true);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
  //  btnCancel.setPreferredSize(new Dimension(80, 35));
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F12)");
    btnCancel.setText("<html><center><br><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                          "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                          "ml>");
    btnCancel.addActionListener(new PanelRunPosBatchJobs_btnCancel_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSearch.setToolTipText(lang.getString("Search") + " (F1)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1<" +
    "/html>");
    btnSearch.addActionListener(new PanelRunPosBatchJobs_btnSearch_actionAdapter(this));
    btnRun.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRun.setToolTipText(lang.getString("Run")+" (F2)");
    btnRun.setText("<html><center><b>"+lang.getString("Run")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</ht" +
    "ml>");
    btnRun.addActionListener(new PanelRunPosBatchJobs_btnRun_actionAdapter(this));
    pnlTop.setLayout(flowLayout1);
    btnToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnToDate.setText("...");
    btnToDate.addActionListener(new PanelRunPosBatchJobs_btnToDate_actionAdapter(this));
    txtToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtToDate.setPreferredSize(new Dimension(100, 21));
    txtToDate.setText(ut.getDD_MM_YYYY(ut.addDate( java.sql.Date.valueOf( ut.getSystemDate()),-1)));
    lblToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblToDate.setPreferredSize(new Dimension(80, 16));
    lblToDate.setHorizontalAlignment(SwingConstants.RIGHT);
    lblToDate.setText(lang.getString("ToDate")+ ":");
    lblToDate.setVerticalAlignment(SwingConstants.CENTER);
    lblToDate.setVerticalTextPosition(SwingConstants.CENTER);
    txtFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtFromDate.setPreferredSize(new Dimension(100, 21));
    txtFromDate.setText(ut.getDD_MM_YYYY(ut.addDate( java.sql.Date.valueOf( ut.getSystemDate()),-1)));
    lblFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFromDate.setText(lang.getString("FromDate") + ":");
    btnFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnFromDate.setText("...");
    btnFromDate.addActionListener(new PanelRunPosBatchJobs_btnFromDate_actionAdapter(this));
    table.addKeyListener(new PanelRunPosBatchJobs_table_keyAdapter(this));
    btnFtp.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnFtp.setToolTipText("FTP (F3)");
    btnFtp.setText("<html><center><b>FTP </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</ht" +
    "ml>");

    btnFtp.addActionListener(new PanelRunPosBatchJobs_btnFtp_actionAdapter(this));
    this.add(jPanel9,BorderLayout.SOUTH);
    jPanel9.add(jPanel11, BorderLayout.SOUTH);
    jPanel11.add(jScrollPane1, null);
    this.add(pnlTop, BorderLayout.NORTH);
    jScrollPane1.getViewport().add(table, null);
    pnlTop.add(lblFromDate, null);
    pnlTop.add(txtFromDate, null);
    pnlTop.add(btnFromDate, null);
    pnlTop.add(lblToDate, null);
    pnlTop.add(txtToDate, null);
    pnlTop.add(btnToDate, null);
    table.setRowHeight(30);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    /*String[] columnNames = new String[]{"Batch Name", "Batch Status","Batch Date" };
    dm.setDataVector(new Object[][]{},columnNames);
    table.getColumn("Batch Name").setPreferredWidth(220);
    table.getColumn("Batch Status").setPreferredWidth(220);
    table.getColumn("Batch Date").setPreferredWidth(160);*/
    String[] columnNames = new String[]{lang.getString("BatchName"), lang.getString("Status"),lang.getString("Date") };
    dm.setDataVector(new Object[][]{},columnNames);
    table.getColumnModel().getColumn(0).setPreferredWidth(200);
    table.getColumnModel().getColumn(1).setPreferredWidth(200);
    table.getColumnModel().getColumn(2).setPreferredWidth(160);


    table.setRowHeight(30);
    table.setColor(Color.lightGray, Color.white);
    txtFromDate.setEditable(false); //avoid input wrong format date
    txtToDate.setEditable(false); //avoid input wrong format date
  }

  //show button
  void showButton(){
    frmMain.showButton(btnSearch);
    frmMain.showButton(btnRun);
    frmMain.showButton(btnFtp);
    frmMain.showButton(btnCancel);
  }
  //init screen
  public void initScreen(){
    txtFromDate.setText(ut.getDD_MM_YYYY(ut.addDate( java.sql.Date.valueOf( ut.getSystemDate()),-1)));
    txtToDate.setText(ut.getDD_MM_YYYY(ut.addDate( java.sql.Date.valueOf( ut.getSystemDate()),-1)));
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
  }
  private void executeJarFile(String fileName) {
    try {
      Process p = Runtime.getRuntime().exec(fileName);
//      System.out.println("Runing...........");
      p.waitFor();//wait till process p is finished
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }



  private boolean checkBatchJobComplete(String batchName, String date){
  ResultSet rs = null;
  String sqlStr = "Select Batch_Status From BTM_POS_BATCH_MAINTENANCE Where Batch_Name='" + batchName + "' and Batch_Date=to_date('" + date + "','dd-MM-yyyy')";
  try{
    DAL.executeUpdate("Commit");
//    stmt = DAL.getConnection().createStatement(ResultSet.
//                                               TYPE_SCROLL_INSENSITIVE,
//                                               ResultSet.CONCUR_READ_ONLY);
//    rs = DAL.executeScrollQueries(sqlStr,stmt);
//    System.out.println(sqlStr);
    rs = DAL.executeQueries(sqlStr);
    if(rs.next()){
      if(rs.getString("Batch_Status").trim().equals("Complete")){
        rs.getStatement().close();
        return true; //batch job execute complete
      }
    }
    rs.getStatement().close();
  }
  catch(Exception e){
    ExceptionHandle eh = new ExceptionHandle();
    eh.ouputError(e);
}
  return false;//not complete
}
//Check file if need (for import batch job)
private boolean checkFile(String batchName) {
  String message = "";
  boolean found=true;

  if (batchName.equals(Constant.POS_EMPLOYEE)) {
    found= ut.isExistFile( Constant.POS_EMPLOYEE_FILE ,Constant.IMPORT_PATH);
    message = lang.getString("MS100_RunAfterSimEm");
  }
  else if (batchName.equals(Constant.POS_EXCHANGE_RATE)) {
    found= ut.isExistFile(Constant.POS_EXCHANGE_RATE_FILE,Constant.IMPORT_PATH);
    message = lang.getString("MS101_RunAfterSimExchangeRatem");
  }
  else if (batchName.equals(Constant.POS_ITEM_PRICE)) {
    found= ut.isExistFile(Constant.POS_ITEM_PRICE_FILE,Constant.IMPORT_PATH);
    message = lang.getString("MS102_RunAfterSimItemPrice");
  }
  if (!found) {
    ut.showMessage(frmMain, lang.getString("TT001"), message +" "+ lang.getString("MS103_RunFTP"));
  }
  return found;
}


  void btnRun_actionPerformed(ActionEvent e) {
    int row = table.getSelectedRow();
    String selectedDate="";

    if (row == 0) {
      selectedDate= table.getValueAt(row, 2).toString();
      while(table.getRowCount()>0 && table.getValueAt(row, 2).toString().equals(selectedDate) ){
        selectedDate= table.getValueAt(row, 2).toString();
        try {
          //Unix: ./Pos_BatchJobs/
          //Windows: \\Pos_BatchJobs\\
          String fileName = ".\\Pos_BatchJobs\\" +
              table.getValueAt(row, 0).toString() + ".bat " +
              ut.getYYYY_MM_DD(table.getValueAt(row, 2).toString());
//          System.out.println("File executing: " + fileName);
          if (!checkFile(table.getValueAt(row, 0).toString().trim()))
            return;
//          System.out.println("Begin Run...........");
          executeJarFile(fileName);

          if(ut.batchjobPosComplete(table.getValueAt(row, 0).toString(), ut.STATUS_COMPLETE,ut.getYYYY_MM_DD(table.getValueAt(row, 2).toString()), DAL)){
            ut.showMessage(frmMain, lang.getString("TT053"),
                         "Batch Job '" + table.getValueAt(row, 0).toString() +
                         "' " + lang.getString("MS104_RunSuccefully"));

          }else{
            ut.showMessage(frmMain, lang.getString("TT001"),
                         "SOS: Batch Job '" + table.getValueAt(row, 0).toString() +
                         "' " + lang.getString("Failed"));

          }

          dm.removeRow(row);
//          System.out.println("");
//          System.out.println("Completed!");

        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
        ;
      }
    }
    else {
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS105_ChooseBatchToRun"));
      return;
}

  }

  void btnCancel_actionPerformed(ActionEvent e) {
     while (dm.getRowCount()>0){
      dm.removeRow(0);
    }
    frmMain.setTitle(lang.getString("TT016"));
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showAdminButton();
  }
  //convert "dd/MM/yyyy" --> "yyyy-MM-dd"
  private String convertDate(String date){
    String arrDate[] = date.split("/");
    date = arrDate[2] + "-" + arrDate[1] + "-" + arrDate[0];
    return date;
  }

  //convert "dd/MM/yyyy" --> "yyyy-MM-dd"
  private String convertDateVN(String date) {
    String arrDate[] = date.split("-");
    date = arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0];
    return date;
  }

  private ResultSet getAllData(String fromDate,String toDate,Statement stmt) {
     ResultSet rs = null;
     String sqlStr = "Select Batch_Name,Batch_Status, Batch_Date From BTM_POS_BATCH_MAINTENANCE Where Batch_Date between to_date('" + fromDate + "','dd-MM-yyyy') and to_date('"
         + toDate + "','dd-MM-yyyy')";
     try{
//       System.out.println(sqlStr);
       rs = DAL.executeScrollQueries(sqlStr,stmt);
     }
     catch(Exception ex){};
     return rs;
   }
   private boolean checkBatchJobExecute(ResultSet rs,String batchName, String date){
   String batchDate="";
   try{
     rs.beforeFirst();
     while (rs.next()){
       batchDate = rs.getDate("Batch_Date").toString();
       if(batchName.equals(rs.getString("Batch_Name").toString()) && date.equals(batchDate) && rs.getString("Batch_Status").toString().equals("Complete")){
          return false;//executed
       }
     }
   }
   catch(Exception ex){};
   return true;//not yet execute-->add to table
 }

  void btnSearch_actionPerformed(ActionEvent e) {
   String fromDate = txtFromDate.getText().trim();
   String toDate = txtToDate.getText().trim();
   if (!ut.checkDate(fromDate, "/")) {
     ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS106_FDateIsDate"));
     txtFromDate.requestFocus(true);
     return;
   }
   if (!ut.checkDate(toDate, "/")) {
     ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS107_TDateIsDate"));
     txtToDate.requestFocus(true);
     return;
   }
   while(dm.getRowCount()>0){
     dm.removeRow(0);
   }
   try{
     stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
     ResultSet rs = getAllData(fromDate,toDate,stmt);
     String date = convertDate(fromDate);
     Date starDate = Date.valueOf(date);
     Date endDate = Date.valueOf(convertDate(toDate));
     while (starDate.compareTo(endDate) <= 0) { //compare date date <= todate
       for (int i = 0; i < arrBatchName.length; i++) {
         if (checkBatchJobExecute(rs, arrBatchName[i], date)) { //if not yet execute
           //add to table
           String row[] = new String[] {
               arrBatchName[i], "Not Complete", convertDateVN(date)};
           dm.addRow(row);
         }
       }
       date = ut.addDate(starDate, 1);
       starDate = Date.valueOf(date);
     }
     stmt.close();
   }catch(Exception ex){};
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

   // F3
   key = new Integer(KeyEvent.VK_F3);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), key);
   actionMap.put(key, new KeyAction(key));
   // F4
   key = new Integer(KeyEvent.VK_F4);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), key);
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
       btnSearch.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F2) {
       btnRun.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F3) {
       btnFtp.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F12 ||
              identifier.intValue() == KeyEvent.VK_ESCAPE) {
       btnCancel.doClick();
     }
   }
   }


  void btnToDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
   posXFrame = this.frmMain.getX();
   posYFrame = this.frmMain.getY();
   posX = this.btnToDate.getX() + posXFrame - 300;
   posY = this.btnToDate.getY() + posYFrame + 75;
   TimeDlg toDate = new TimeDlg(null);
   toDate.setTitle(lang.getString("ToDate"));
   toDate.setResizable(false);
   toDate.setLocation(posX, posY);
   toDate.setVisible(true);
   if (toDate.isOKPressed()) {
     java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
         ut.DATE_FORMAT1);
     String date = fd.format(toDate.getDate());
     this.txtToDate.setText(date);
   }
  }

  void btnFromDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnFromDate.getX() + posXFrame;
    posY = this.btnFromDate.getY() + posYFrame + 75;
    TimeDlg fromDate = new TimeDlg(null);
    fromDate.setTitle(lang.getString("FromDate"));
    fromDate.setResizable(false);
    fromDate.setLocation(posX, posY);
    fromDate.setVisible(true);
    if (fromDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          ut.DATE_FORMAT1);
      String date = fd.format(fromDate.getDate());
      this.txtFromDate.setText(date);
    }

  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                btnRun.doClick();
              }
  }
  //FTP: get files from SIm to POS and put files from POS to SIM
  void sendReceive(){

    String remotePath="Batch\\Export\\" + DAL.getStoreID();
    String localPath="Batch\\Import\\";
    String filename="";
    File dir;
    String[] children;//list of  files at local

    CustomFtpClient ftpClient = new CustomFtpClient();
    try {
      //1---------- connect Ftp
//System.out.println(DAL.getStoreID()+" "+DAL.getFtpServer()+" "+DAL.getFtpUser()+" "+DAL.getFtpPassword());
      ftpClient.openServer(DAL.getFtpServer());
      ftpClient.login(DAL.getFtpUser(), DAL.getFtpPassword());
      ftpClient.cd(remotePath);
      ftpClient.binary();

      //2---------- copy from SIM Export to POS Export
      StringBuffer pathList = new StringBuffer();
      InputStream is = ftpClient.list();
      int c;

      //read all file and directory at remote
      while ( (c = is.read()) != -1) {
        String s = (new Character( (char) c)).toString();
        pathList.append(s);
      }

      StringTokenizer st = new StringTokenizer(pathList.toString(),"\r\n");
      //get file from SIM Export
      while (st.hasMoreTokens()){
         filename=st.nextToken().substring(55);
         if(filename.indexOf(".txt")!=-1){
           is = ftpClient.get(filename);
           File file_out = new File(localPath + filename);
           FileOutputStream os = new
               FileOutputStream(file_out);
           byte[] bytes = new byte[1024];

           while ( (c = is.read(bytes)) != -1) {
             os.write(bytes, 0, c);
           }
           is.close();
           os.close();
           //3--------- delete SIM Export
           ftpClient.delete(filename);
         }
      }

      //4--------- copy POS Import to SIM Backup
      remotePath="..\\..\\..\\Batch\\Backup\\Export";
      localPath="Batch\\Import\\";

      ftpClient.cd(remotePath);

      //get list of file
      dir = new File(localPath);
      children = dir.list();
      if (children != null) {
        for (int i = 0; i < children.length; i++) {
          // Get filename of file or directory
          filename = children[i];
          TelnetOutputStream os = ftpClient.put(filename);
          File file_in = new File(localPath + filename);
          is = new FileInputStream(file_in);
          byte[] bytes = new byte[1024];

          while ( (c = is.read(bytes)) != -1) {
            os.write(bytes, 0, c);
          }
          is.close();
          os.close();
        }
      }

      //5--------- copy from POS Export to SIM Import
      remotePath="..\\..\\..\\Batch\\Import";
      localPath="Batch\\Export\\";
      ftpClient.cd(remotePath);

      //get list of file
      dir = new File(localPath);
      children = dir.list();
      if (children != null) {
        for (int i = 0; i < children.length; i++) {
          // Get filename of file or directory
          filename = children[i];
          TelnetOutputStream os = ftpClient.put(filename);
          File file_in = new File(filename);
          is = new FileInputStream(localPath + file_in);
          byte[] bytes = new byte[1024];
          while ( (c = is.read(bytes)) != -1) {
            os.write(bytes, 0, c);
          }
          is.close();
          os.close();
        }
      }

      //---------- close ftp
      ftpClient.closeServer();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  //Delete method of Ftp
  public class CustomFtpClient extends FtpClient {

    public CustomFtpClient(String host) throws IOException {
      super(host);
    }

    public CustomFtpClient(String host, int port) throws IOException {
      super(host, port);
    }

    public CustomFtpClient() {
      super();
    }

    public void delete(String fileName) throws IOException {
      issueCommand("DELE " + fileName);
    }
  }

  //FTP
  void btnFtp_actionPerformed(ActionEvent e) {

//    if(countFile(".\\Batch\\Import") >0){
//      ut.showMessage(frmMain, "Notify",
//                     "You must run import batch job, some files exist in Batch\\Import!!!.");
//      return;
//    }

//    System.out.println("Ftp is runing...........");
    sendReceive();
    try {
      String fileName = ".\\file\\move.bat" ;
      //String[] commands = new String[]{" move","Batch\\export\\*.txt", "Batch\\Backup\\Export"};
      Process p = Runtime.getRuntime().exec(fileName);
      p.waitFor();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
//    System.out.println("Ftp is finished");
    ut.showMessage(frmMain, lang.getString("TT053"),lang.getString("MS108_FTPFinish"));

  }
  /** Count files in the path
   * @author 	Trung.Nguyen
   * @param 	path the path of file
   * @return	number of file in the path
   */

  public int  countFile(String path){
    int count=0;
    String filename;
    File dir = new File(path);

    String[] children = dir.list();
    if (children == null) {
      count = 0;
    }else {
      count = children.length;
    }
    return count;
  }

}
class PanelRunPosBatchJobs_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelRunPosBatchJobs adaptee;

  PanelRunPosBatchJobs_btnCancel_actionAdapter(PanelRunPosBatchJobs adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelRunPosBatchJobs_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelRunPosBatchJobs adaptee;

  PanelRunPosBatchJobs_btnSearch_actionAdapter(PanelRunPosBatchJobs adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}
class PanelRunPosBatchJobs_btnRun_actionAdapter implements java.awt.event.ActionListener {
  PanelRunPosBatchJobs adaptee;

  PanelRunPosBatchJobs_btnRun_actionAdapter(PanelRunPosBatchJobs adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnRun_actionPerformed(e);
  }
}


class PanelRunPosBatchJobs_btnFromDate_actionAdapter implements java.awt.event.ActionListener {
  PanelRunPosBatchJobs adaptee;

  PanelRunPosBatchJobs_btnFromDate_actionAdapter(PanelRunPosBatchJobs adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnFromDate_actionPerformed(e);
  }
}

class PanelRunPosBatchJobs_btnToDate_actionAdapter implements java.awt.event.ActionListener {
  PanelRunPosBatchJobs adaptee;

  PanelRunPosBatchJobs_btnToDate_actionAdapter(PanelRunPosBatchJobs adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnToDate_actionPerformed(e);
  }
}

class PanelRunPosBatchJobs_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelRunPosBatchJobs adaptee;

  PanelRunPosBatchJobs_table_keyAdapter(PanelRunPosBatchJobs adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class PanelRunPosBatchJobs_btnFtp_actionAdapter implements java.awt.event.ActionListener {
  PanelRunPosBatchJobs adaptee;

  PanelRunPosBatchJobs_btnFtp_actionAdapter(PanelRunPosBatchJobs adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnFtp_actionPerformed(e);
  }
}
