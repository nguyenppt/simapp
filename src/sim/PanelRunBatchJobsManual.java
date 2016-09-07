package sim;

import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import btm.swing.*;
import common.*;
import btm.attr.*;
import java.awt.print.*;
import java.util.*;
import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>Title: </p>
 * <p>Description: Batch job auto run from top, you must run export batch job, then FTP ,
 * and run import batch job , export file name is BatchName_StoreIDSystemdate </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author Vinh.Le
 * @version 1.0
 */

// if files exist in Export folder => must ftp
// else if files exist in Import folder => must run import
// if batch job does'nt complete or run times is not enough => run evry batch job
public class PanelRunBatchJobsManual
    extends JPanel {
  //define for panel
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;
//  boolean flag = false;
  java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
  String arrBatchName[] = new String[]{"SimExpEmployee","SimExpCustomer","SimExpExchangeRate",
      "SimExpItemLoc","SimExpMixMatchPromo","SimExpThresholdPromo","SimExpCustomerRevenue","SimImpSumDailyTrans","SimImpSalesItemLoc",
      "SimImpSaleReturnItemLoc","SimImpStoreFund","SimImpPaidInOut","SimImpSOH","SimImpInvItemLocDM",
      "SimImpInvItemLocDCom"};
  Statement stmt = null;

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm, true) {
    public Class getColumnClass(int col) {
      switch (col) {
          default:
          return Object.class;
      }
    }

    public boolean isCellEditable(int row, int col) {
      return false;
    }
  };

  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BJButton btnCancel = new BJButton();
  BJButton btnRun = new BJButton();
  JLabel lbTitle = new JLabel();
  DataAccessLayer DAL = new DataAccessLayer();
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  String path = "";
  JPanel jPanel5 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JLabel lbToDate = new JLabel();
  JTextField txtToDate = new JTextField();
  JButton btnSearchToDate = new JButton();
  BJButton btnSearch = new BJButton();
  JButton btnSearchFromDate = new JButton();
  JTextField txtFromDate = new JTextField();
  JLabel lblFromDate = new JLabel();

  public PanelRunBatchJobsManual() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public PanelRunBatchJobsManual(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public PanelRunBatchJobsManual(FrameMainSim frmMain, CardLayout crdLayout,
                             JPanel parent) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      this.cardLayout = crdLayout;
      this.parent = parent;
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout3);
    jPanel2.setLayout(borderLayout2);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setToolTipText(lang.getString("Cancel") +" (F3 or ESC)");
    btnCancel.setSelectedIcon(null);
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelRunBatchJobsManual_btnCancel_actionAdapter(this));
    btnRun.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRun.setToolTipText(lang.getString("RunBatchJobs") +" (F5)");
    btnRun.setText("<html><center><b>"+lang.getString("Run")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</ht" +
    "ml>");
    btnRun.addActionListener(new PanelRunBatchJobsManual_btnRun_actionAdapter(this));
    lbTitle.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
    lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lbTitle.setText("");
    jPanel4.setBackground(new Color(121, 152, 198));
    jPanel3.setBackground(Color.lightGray);
    jPanel3.setLayout(borderLayout4);
    lbToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lbToDate.setPreferredSize(new Dimension(80, 16));
    lbToDate.setHorizontalAlignment(SwingConstants.RIGHT);
    lbToDate.setText(lang.getString("ToDate") +" :");
    lbToDate.setVerticalAlignment(SwingConstants.CENTER);
    lbToDate.setVerticalTextPosition(SwingConstants.CENTER);
    txtToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtToDate.setPreferredSize(new Dimension(100, 21));
    txtToDate.setText(ut.getDD_MM_YYYY(ut.addDate( java.sql.Date.valueOf( ut.getSystemDate()),-1)));
    txtToDate.addKeyListener(new
        PanelRunBatchJobsManual_txtToDate_keyAdapter(this));
    btnSearchToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearchToDate.setPreferredSize(new Dimension(35, 22));
    btnSearchToDate.setText("...");
    btnSearchToDate.addActionListener(new
        PanelRunBatchJobsManual_btnSearchToDate_actionAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(454, 380));
    jPanel5.setPreferredSize(new Dimension(294, 40));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3<" +
    "/html>");
    btnSearch.addActionListener(new PanelRunBatchJobsManual_btnSearch_actionAdapter(this));
    btnSearch.setToolTipText(lang.getString("Search") +" (F3)");
    btnSearchFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearchFromDate.setPreferredSize(new Dimension(35, 22));
    btnSearchFromDate.setText("...");
    btnSearchFromDate.addActionListener(new PanelRunBatchJobsManual_btnSearchFromDate_actionAdapter(this));
    txtFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtFromDate.setPreferredSize(new Dimension(100, 21));
    txtFromDate.setText(ut.getDD_MM_YYYY(ut.addDate( java.sql.Date.valueOf( ut.getSystemDate()),-1)));
    lblFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFromDate.setText(lang.getString("FromDate") +": ");
    table.addKeyListener(new PanelRunBatchJobsManual_table_keyAdapter(this));
    jPanel4.add(btnSearch, null);
    this.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel4, BorderLayout.NORTH);
    jPanel4.add(btnRun, null);
    jPanel4.add(btnCancel, null);
    jPanel1.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jPanel5, BorderLayout.NORTH);
    jPanel5.add(lblFromDate, null);
    jPanel5.add(txtFromDate, null);
    jPanel5.add(btnSearchFromDate, null);
    jPanel5.add(lbToDate, null);
    jPanel5.add(txtToDate, null);
    jPanel5.add(btnSearchToDate, null);
    jPanel3.add(lbTitle, BorderLayout.CENTER);
    this.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(table, null);

    //define for table
    String[] columnNames = new String[] {
        lang.getString("BatchName"),
        lang.getString("Status"),
        lang.getString("Date")};
    dm.setDataVector(new Object[][] {}
                     , columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    table.getColumn(lang.getString("BatchName")).setMinWidth(120);
    table.getColumn(lang.getString("Status")).setPreferredWidth(120);
    table.getColumn(lang.getString("Date")).setPreferredWidth(250);
    table.setRowHeight(30);
    table.setColor(Color.lightGray, Color.white);
    txtFromDate.setEditable(false); //avoid input wrong format date
    txtToDate.setEditable(false); //avoid input wrong format date

  }

  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_PICK_LIST);
    btnRun.setEnabled(er.getReport());
  }


  void initScreen() {
//    txtFromDate.setText(ut.getSystemDate(1));
//    txtToDate.setText(ut.getSystemDate(1));
    txtFromDate.setText(ut.getDD_MM_YYYY(ut.addDate( java.sql.Date.valueOf( ut.getSystemDate()),-1)));
    txtToDate.setText(ut.getDD_MM_YYYY(ut.addDate( java.sql.Date.valueOf( ut.getSystemDate()),-1)));


    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
//    System.out.println(isExistFile("Employee_11002006-01-10.txt"));
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

   // F5
   key = new Integer(KeyEvent.VK_F5);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
   actionMap.put(key, new KeyAction(key));

   // F5
   key = new Integer(KeyEvent.VK_F5);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
   actionMap.put(key, new KeyAction(key));
   // F6
   key = new Integer(KeyEvent.VK_F6);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), key);
   actionMap.put(key, new KeyAction(key));
   // F7
   key = new Integer(KeyEvent.VK_F7);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), key);
   actionMap.put(key, new KeyAction(key));
   // F8
   key = new Integer(KeyEvent.VK_F8);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), key);
   actionMap.put(key, new KeyAction(key));
   // F9
   key = new Integer(KeyEvent.VK_F9);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), key);
   actionMap.put(key, new KeyAction(key));
   // F10
   key = new Integer(KeyEvent.VK_F10);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), key);
   actionMap.put(key, new KeyAction(key));
   // F11
   key = new Integer(KeyEvent.VK_F11);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), key);
   actionMap.put(key, new KeyAction(key));
   // F12
   key = new Integer(KeyEvent.VK_F12);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
   actionMap.put(key, new KeyAction(key));

   // ENTER
    key = new Integer(KeyEvent.VK_ENTER);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
    actionMap.put(key, new KeyAction(key));

   // Escape
   key = new Integer(KeyEvent.VK_ESCAPE);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
   actionMap.put(key, new KeyAction(key));
  }

  class KeyAction
      extends AbstractAction {

    private Integer identifier = null;

    public KeyAction(Integer identifier) {
      this.identifier = identifier;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
      if (identifier.intValue() == KeyEvent.VK_F3) {
        btnSearch.doClick();
      }else if (identifier.intValue() == KeyEvent.VK_F5) {
        btnRun.doClick();
      }else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void btnSearchToDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnSearchToDate.getX() + posXFrame - 300;
    posY = this.btnSearchToDate.getY() + posYFrame + 75;
    TimeDlg toDate = new TimeDlg(null);
    toDate.setTitle(lang.getString("ChooseEndDate"));
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


  void txtToDate_keyPressed(KeyEvent e) {

  }

  void btnSearch_actionPerformed(ActionEvent e) {
//    setOpenConnection();
    String fromDate = txtFromDate.getText().trim();
    String toDate = txtToDate.getText().trim();
    if (!ut.checkDate(fromDate, "/")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS327_FromDateMustDateType"));
      txtFromDate.requestFocus(true);
      return;
    }
    if (!ut.checkDate(toDate, "/")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS326_ToDateMustDateType"));
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
      dm.resetIndexes();
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
      table.sortButton();
      stmt.close();
    }catch(Exception ex){};
//    closeConnection();
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

  //get all batch job in DB between fromDate and toDate

  private ResultSet getAllData(String fromDate,String toDate,Statement stmt) {
    ResultSet rs = null;
    String sqlStr = "Select Batch_Name,Batch_Status, Batch_Date From BTM_IM_BATCH_MAINTENANCE Where Batch_Date between to_date('" + fromDate + "','dd-MM-yyyy') and to_date('"
        + toDate + "','dd-MM-yyyy')";
    try{
//      System.out.println(sqlStr);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch(Exception ex){};
    return rs;
  }

  void btnSearchFromDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnSearchToDate.getX() + posXFrame - 300;
    posY = this.btnSearchToDate.getY() + posYFrame + 75;
    TimeDlg fromDate = new TimeDlg(null);
    fromDate.setTitle(lang.getString("ChooseStartDate"));
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
  private boolean checkBatchJobComplete(String batchName, String date){
    ResultSet rs = null;
    String sqlStr = "Select Batch_Status From BTM_IM_BATCH_MAINTENANCE Where Batch_Name='" + batchName + "' and Batch_Date=to_date('" + date + "','dd-MM-yyyy')";
    try{
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
      if(rs.next()){
        if(rs.getString("Batch_Status").trim().equals("Complete")){
          stmt.close();
          rs.close();
          return true; //batch job execute complete
        }
      }
      stmt.close();
      rs.close();
    }
    catch(Exception ex){};
    return false;//not complete
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

  //Check file if need (for import batch job)
  private boolean checkFile(String batchName) {
    String message = "";
    boolean found=true;

    if (batchName.equals(Constant.SIM_SALES_ITEM_LOC)) {
      found= ut.isExistFile( Constant.SIM_SALES_ITEM_LOC_FILE ,Constant.IMPORT_PATH);
      message = "SimSalesItemLoc "+lang.getString("MS328_ModuleMustRunAfter")+" PosSalesItemLoc "+lang.getString("MS329_ModuleMustRunAfter");
    }
    else if (batchName.equals(Constant.SIM_SOH)) {
      found= ut.isExistFile(Constant.SIM_SOH_FILE,Constant.IMPORT_PATH);
      message = "SimSOH "+lang.getString("MS328_ModuleMustRunAfter")+" PosSOH "+lang.getString("MS329_ModuleMustRunAfter");
    }
    else if (batchName.equals(Constant.SIM_CUSTOMER)) {
      found= ut.isExistFile(Constant.SIM_CUSTOMER_FILE,Constant.IMPORT_PATH);
      message = "SimCustomer "+lang.getString("MS328_ModuleMustRunAfter")+" PosCustomer "+lang.getString("MS329_ModuleMustRunAfter");
    }
    else if (batchName.equals(Constant.SIM_SUM_DAILY_TRANS_BACK)) {
      found= ut.isExistFile(Constant.SIM_SUM_DAILY_TRANS_BACK_FILE,Constant.IMPORT_PATH);
      message =
          "SimSumDailyTransBackup "+lang.getString("MS328_ModuleMustRunAfter")+" PosSumDailyTransBackup "+lang.getString("MS329_ModuleMustRunAfter");
    }
    if (!found) {
      ut.showMessage(frmMain,  lang.getString("TT001") , message +" "+ lang.getString("MS330_RunFTP"));
    }
    return found;
  }
  //Auto run all batch jobs in selected date
  void btnRun_actionPerformed(ActionEvent e) {
    int row = table.getSelectedRow();
    String selectedDate="";
    if(row == 0){
      selectedDate= table.getValueAt(row, 2).toString();
      while(table.getRowCount()>0 && table.getValueAt(row, 2).toString().equals(selectedDate) ){
        selectedDate= table.getValueAt(row, 2).toString();
        try {
          //Unix: ./Sim_BatchJobs/
          //Win: .\\Sim_BatchJobs\\
          String fileName = ".\\Sim_BatchJobs\\" +
              table.getValueAt(row, 0).toString() + ".bat " +
              ut.getYYYY_MM_DD(table.getValueAt(row, 2).toString());
//          System.out.println("File executing: " + fileName);
          if (!checkFile(table.getValueAt(row, 0).toString().trim()))
            return;
//          System.out.println("Begin Run...........");
          executeJarFile(fileName);

          if(ut.batchjobSimComplete(table.getValueAt(row, 0).toString(), ut.STATUS_COMPLETE,ut.getYYYY_MM_DD(table.getValueAt(row, 2).toString()), DAL)){
            ut.showMessage(frmMain, lang.getString("TT0009"),
                         "Batch Job '" + table.getValueAt(row, 0).toString() +
                         "'  "+lang.getString("MS331_RunSuccessFull"));

          }else{
            ut.showMessage(frmMain, lang.getString("TT001"),
                         lang.getString("TT002")+": Batch Job '" + table.getValueAt(row, 0).toString() +
                         lang.getString("Failed"));

          }

          dm.removeRow(row);
//          System.out.println("");
//          System.out.println("Completed!");

        }
        catch (Exception ex) {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(ex);}
        ;
      }
    }
    else{
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS332_ChooseBatchToRun"));
      return;
    }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnRun.doClick();
               }

  }

}

class PanelRunBatchJobsManual_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelRunBatchJobsManual adaptee;

  PanelRunBatchJobsManual_btnCancel_actionAdapter(PanelRunBatchJobsManual adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelRunBatchJobsManual_btnRun_actionAdapter
    implements java.awt.event.ActionListener {
  PanelRunBatchJobsManual adaptee;

  PanelRunBatchJobsManual_btnRun_actionAdapter(PanelRunBatchJobsManual adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnRun_actionPerformed(e);
  }
}

class PanelRunBatchJobsManual_btnSearchToDate_actionAdapter
    implements java.awt.event.ActionListener {
  PanelRunBatchJobsManual adaptee;

  PanelRunBatchJobsManual_btnSearchToDate_actionAdapter(PanelRunBatchJobsManual adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchToDate_actionPerformed(e);
  }
}

class PanelRunBatchJobsManual_txtToDate_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelRunBatchJobsManual adaptee;

  PanelRunBatchJobsManual_txtToDate_keyAdapter(PanelRunBatchJobsManual adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.txtToDate_keyPressed(e);
  }
}

class PanelRunBatchJobsManual_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelRunBatchJobsManual adaptee;

  PanelRunBatchJobsManual_btnSearch_actionAdapter(PanelRunBatchJobsManual adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelRunBatchJobsManual_btnSearchFromDate_actionAdapter implements java.awt.event.ActionListener {
  PanelRunBatchJobsManual adaptee;

  PanelRunBatchJobsManual_btnSearchFromDate_actionAdapter(PanelRunBatchJobsManual adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchFromDate_actionPerformed(e);
  }
}

class PanelRunBatchJobsManual_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelRunBatchJobsManual adaptee;

  PanelRunBatchJobsManual_table_keyAdapter(PanelRunBatchJobsManual adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

