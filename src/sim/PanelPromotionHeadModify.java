package sim;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.util.*;
import btm.attr.*;
import java.awt.Dimension;

/**
 * <p>Title: Promotion Head Modify </p>
 *
 * <p>Description: Main -> Merch Mgmt -> Promo Head -> Modify </p>
 *
 * <p>Copyright: Copyright (c) 2006  </p>
 *
 * <p>Company: BTM </p>
 *
 * @author Vinh Le
 * @version 1.0
 */

//Running promo: Could modify promo with End_date=yesterday (close promo immediately)
//Delete Promo: only delete before import to POS

public class PanelPromotionHeadModify extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  Statement stmt = null;
  DataAccessLayer DAL = new DataAccessLayer();
  Utils ut = new Utils();
  Vector vSql = new Vector();
  Vector vInfo = new Vector();

  //=============Define Constant for Table Column
  public static final int PROMO_ID   = 0;
  public static final int PROMO_NAME = 1;
  public static final int START_DATE = 2;
  public static final int END_DATE   = 3;
  public static final int START_TIME = 4;
  public static final int END_TIME   = 5;
  public static final int STORE      = 6;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  JPanel pnlHeader = new JPanel();
  BJButton btnModify = new BJButton();
  BJButton btnCancel = new BJButton();
  JPanel pnlCenter = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlTable = new JPanel();
  JPanel pnlInfo = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel pnlLeft = new JPanel();
  JPanel pnlRight = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel pnlLeftLabel = new JPanel();
  JPanel pnlLeftInfo = new JPanel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JTextField txtEndDate = new JTextField();
  JTextField txtStartDate = new JTextField();
  JTextField txtPromoName = new JTextField();
  JButton btnEndDate = new JButton();
  JTextField txtPromoID = new JTextField();
  JButton btnStartDate = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel pnlRightLabel = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JPanel pnlRightInfo = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  JPanel pnlDesc = new JPanel();
  JLabel jLabel1 = new JLabel();
  JScrollPane scrPanelTable = new JScrollPane();
  SortableTableModel dmPromoHead = new SortableTableModel();
  BJTable tblPromoHead = new BJTable(dmPromoHead,true);
  FlowLayout flowLayout3 = new FlowLayout();
  JPanel jPanel1 = new JPanel();
  JLabel lblhide = new JLabel();
  String promoID ="";
  JComboBox cboEndMinute = new JComboBox();
  JComboBox cboEndHour = new JComboBox();
  JComboBox cboStartMinute = new JComboBox();
  JComboBox cboStartHour = new JComboBox();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  FlowLayout flowLayout4 = new FlowLayout();
  JTextArea txtDesc = new JTextArea();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel6 = new JLabel();

  String oldStartDate = "";

  JLabel jlblStore = new JLabel();
  JComboBox cboStore = new JComboBox();
  public PanelPromotionHeadModify() {
    try {
      jbInit();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
  public PanelPromotionHeadModify(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
    try {
      this.frmMain = frmMain;
      this.cardLayout = crdLayout;
      this.parent = parent;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void initTimeCombo() {
    int i;
    String strTemp = "";
    for (i = 0; i < 24; i++) {
      if (i < 10) {
        strTemp = "0" + String.valueOf(i);
      }
      else {
        strTemp = String.valueOf(i);
      }
      cboStartHour.addItem(strTemp);
      cboEndHour.addItem(strTemp);
    }
    for (i = 0; i < 60; i += 5) {
      if (i < 10) {
        strTemp = "0" + String.valueOf(i);
      }
      else {
        strTemp = String.valueOf(i);
      }
      cboStartMinute.addItem(strTemp);
      cboEndMinute.addItem(strTemp);
    }
  }

  public void initComboStore(){
    cboStore.addItem("ALL");
    ResultSet rs=null;
    Statement stmt = null;
    String sql = "Select NAME From BTM_IM_STORE" ;
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      while(rs.next()){
        cboStore.addItem(rs.getString("NAME"));
      }
      rs.close();
      stmt.close();
//      cboStore.setVisible(false);
    }catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    pnlHeader.setBackground(new Color(121, 152, 198));
    pnlHeader.setPreferredSize(new Dimension(102, 45));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify")+" (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnModify.addActionListener(new PanelPromotionHeadModify_btnModify_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new PanelPromotionHeadModify_btnCancel_actionAdapter(this));

    pnlCenter.setLayout(borderLayout2);
    pnlTable.setPreferredSize(new Dimension(10, 100));
    pnlTable.setLayout(borderLayout4);
    pnlInfo.setPreferredSize(new Dimension(10, 160));
    pnlInfo.setLayout(borderLayout3);
    pnlLeft.setPreferredSize(new Dimension(400, 10));
    pnlLeft.setLayout(borderLayout5);
    pnlRight.setPreferredSize(new Dimension(500, 10));
    pnlRight.setLayout(borderLayout6);
    pnlLeftLabel.setPreferredSize(new Dimension(140, 10));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel2.setPreferredSize(new Dimension(100, 22));
    jLabel2.setText(lang.getString("EndDate")+":");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel3.setPreferredSize(new Dimension(100, 22));
    jLabel3.setText(lang.getString("StartDate")+":");
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel4.setPreferredSize(new Dimension(100, 22));
    jLabel4.setText(lang.getString("PromoName")+":");
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel5.setMaximumSize(new Dimension(66, 23));
    jLabel5.setPreferredSize(new Dimension(100, 22));
    jLabel5.setText(lang.getString("PromoID")+":");
    pnlLeftInfo.setPreferredSize(new Dimension(250, 10));
    pnlLeftInfo.setLayout(flowLayout1);
    txtEndDate.setBackground(Color.white);
    txtEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtEndDate.setPreferredSize(new Dimension(140, 22));
    txtEndDate.setEditable(false);
    txtStartDate.setBackground(Color.white);
    txtStartDate.setEnabled(true);
    txtStartDate.setEditable(false);
    txtStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtStartDate.setPreferredSize(new Dimension(140, 22));
    txtStartDate.setEditable(true);
    txtPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPromoName.setPreferredSize(new Dimension(175, 22));
    btnEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnEndDate.setMinimumSize(new Dimension(30, 23));
    btnEndDate.setPreferredSize(new Dimension(30, 23));
    btnEndDate.setText("jButton1");
    btnEndDate.addActionListener(new
                                 PanelPromotionHeadModify_btnEndDate_actionAdapter(this));
    btnStartDate.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
    btnStartDate.setPreferredSize(new Dimension(30, 23));
    btnStartDate.setText("jButton1");
    btnStartDate.addActionListener(new
        PanelPromotionHeadModify_btnStartDate_actionAdapter(this));
    txtPromoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPromoID.setPreferredSize(new Dimension(175, 22));
    txtPromoID.setDisabledTextColor(SystemColor.info);
    txtPromoID.setEditable(false);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    pnlRightLabel.setPreferredSize(new Dimension(150, 10));
    pnlRightLabel.setLayout(flowLayout2);
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel7.setPreferredSize(new Dimension(80, 22));
    jLabel7.setText(lang.getString("EndTime")+":");

    jlblStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jlblStore.setPreferredSize(new Dimension(80, 22));
    jlblStore.setText(lang.getString("Store")+":");

    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel8.setPreferredSize(new Dimension(80, 22));
    jLabel8.setText(lang.getString("StartTime")+":");
    pnlRightInfo.setPreferredSize(new Dimension(100, 10));
    pnlRightInfo.setLayout(flowLayout4);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel1.setPreferredSize(new Dimension(96, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText(lang.getString("Description")+":");
    pnlDesc.setLayout(flowLayout3);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    jPanel1.setPreferredSize(new Dimension(50, 10));
    lblhide.setPreferredSize(new Dimension(34, 10));
    cboStartHour.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboStartHour.setPreferredSize(new Dimension(52, 22));
    cboStartMinute.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboStartMinute.setPreferredSize(new Dimension(52, 22));
    cboEndHour.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboEndHour.setPreferredSize(new Dimension(52, 22));
    cboEndMinute.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboEndMinute.setPreferredSize(new Dimension(52, 22));

    cboStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboStore.setPreferredSize(new Dimension(130, 22));

    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel9.setMinimumSize(new Dimension(24, 16));
    jLabel9.setPreferredSize(new Dimension(24, 16));
    jLabel9.setText(lang.getString("mm"));
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel10.setPreferredSize(new Dimension(16, 16));
    jLabel10.setText(lang.getString("hh"));
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel11.setPreferredSize(new Dimension(24, 16));
    jLabel11.setText(lang.getString("mm"));
    jLabel12.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel12.setPreferredSize(new Dimension(16, 16));
    jLabel12.setText(lang.getString("hh"));
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    showButton(true);
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDesc.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDesc.setMinimumSize(new Dimension(635, 15));
    txtDesc.setPreferredSize(new Dimension(594, 38));
    txtDesc.setLineWrap(true);
    txtDesc.setWrapStyleWord(true);
    jLabel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel13.setForeground(Color.red);
    jLabel13.setText("(*)");
    jLabel14.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel14.setForeground(Color.red);
    jLabel14.setText("(*)");
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setForeground(Color.red);
    jLabel6.setRequestFocusEnabled(true);
    jLabel6.setText("(*)");
    this.setRequestFocusEnabled(true);
    this.add(pnlCenter, java.awt.BorderLayout.CENTER);
    pnlCenter.add(pnlTable, java.awt.BorderLayout.CENTER);
    pnlCenter.add(pnlInfo, java.awt.BorderLayout.NORTH);
    pnlInfo.add(pnlLeft, java.awt.BorderLayout.WEST);
    pnlLeftLabel.add(jLabel5);
    pnlLeftLabel.add(jLabel4);
    pnlLeftLabel.add(jLabel3);
    pnlLeftLabel.add(jLabel2);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightLabel, java.awt.BorderLayout.WEST);
    pnlRightLabel.add(jLabel8);
    pnlRightLabel.add(jLabel7);

    pnlRightLabel.add(jlblStore);

    pnlLeftInfo.add(txtPromoID);
    pnlLeftInfo.add(txtPromoName);
    pnlLeftInfo.add(jLabel13, null);
    pnlLeftInfo.add(txtStartDate);
    pnlLeftInfo.add(btnStartDate);
    pnlLeftInfo.add(jLabel14, null);
    pnlLeftInfo.add(txtEndDate);
    pnlLeftInfo.add(btnEndDate);
    pnlLeftInfo.add(jLabel6, null);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlRightInfo.add(cboStartHour);
    pnlRightInfo.add(jLabel12);
    pnlRightInfo.add(cboStartMinute);
    pnlRightInfo.add(jLabel11);
    pnlRightInfo.add(cboEndHour);
    pnlRightInfo.add(jLabel10);
    pnlRightInfo.add(cboEndMinute);
    pnlRightInfo.add(jLabel9);
    pnlRightInfo.add(cboStore);
    pnlRight.add(pnlRightInfo, java.awt.BorderLayout.CENTER);
    pnlLeft.add(pnlLeftInfo, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlRight, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlDesc, java.awt.BorderLayout.SOUTH);
    pnlDesc.add(jLabel1);
    pnlDesc.add(lblhide);
    pnlDesc.add(txtDesc);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.NORTH);
    pnlTable.add(scrPanelTable, java.awt.BorderLayout.CENTER);
    pnlRight.add(jPanel1, java.awt.BorderLayout.EAST);
    scrPanelTable.getViewport().add(tblPromoHead,null);
    this.add(pnlHeader, java.awt.BorderLayout.NORTH);
    registerKeyboardActions();
    initTable();
    initTimeCombo();
    initComboStore();
  }
  void initTable() {
      //define for table
      String[] columnNames = new String[] {
          lang.getString("PromoID"), lang.getString("PromoName"), lang.getString("StartDate"), lang.getString("EndDate"), lang.getString("StartTime"), lang.getString("EndTime"),lang.getString("Store")};
      dmPromoHead.setDataVector(new Object[][] {}, columnNames);
      tblPromoHead.setColumnWidth(PROMO_NAME, 150);
      tblPromoHead.setColumnWidth(START_DATE, 100);
      tblPromoHead.setColumnWidth(END_DATE, 100);
      tblPromoHead.setColumnWidth(START_TIME, 80);
      tblPromoHead.setColumnWidth(END_TIME, 80);
      tblPromoHead.setColumnWidth(STORE, 80);
      tblPromoHead.setColor(Color.lightGray, Color.white);
 }
  void showButton(boolean flagSetButton) {
    this.pnlHeader.removeAll();
    this.pnlHeader.updateUI();
    pnlHeader.add(btnModify, null);
    pnlHeader.add(btnCancel, null);
  }

  boolean isAllStore(String promoID){
    String sql = "Select count(*) From BTM_IM_PROMO_STORE where PROMO_ID ='" + promoID + "'";
    ResultSet rs=null;
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if(rs.next()){
        int nStr = rs.getInt(1);
        if(nStr>1){
          stmt.close();
          rs.close();
          return true;
        }
      }
    }catch(Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
     return false;
  }

  void setComboStore(String promoID){
    if(isAllStore(promoID)){
      cboStore.setSelectedItem("ALL");
    }else{
      ResultSet rs=null;
      Statement stmt = null;
      String sql=  "Select s.NAME From BTM_IM_STORE s, BTM_IM_PROMO_STORE p " +
          "Where p.STORE_ID = s.STORE_ID And p.PROMO_ID = '" + promoID + "'" ;
      try{
        stmt = DAL.getConnection().createStatement();
        rs = DAL.executeQueries(sql, stmt);
        if(rs.next()){
          cboStore.setSelectedItem(rs.getString("NAME"));
        }
        stmt.close();
        rs.close();
        cboStore.setEnabled(false);
      }catch(Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
    }
  }
  //Covert to date type yyyy-mm-dd
  //date: dd/mm/yyyy
  String cvtDate(String date){
    String rlt = date;
    String year = "";
    String month = "";
    String day = "";
    day = String.valueOf(date.charAt(0))+String.valueOf(date.charAt(1));
    month = String.valueOf(date.charAt(3))+String.valueOf(date.charAt(4));
    for(int i=6; i<10;i++){
      year = year+String.valueOf(date.charAt(i));
    }
    rlt = year + month + day;
    return rlt;
  }

  void setDataToModify(String promoID){
    ResultSet rs = null;
    String sqlStr = "Select to_char(Promo_Id) Promo_Id, Promo_Name, Description, "
       + " to_char(Start_Date,'DD/MM/YYYY') Start_Date, to_char(End_Date,'DD/MM/YYYY') End_Date, to_char(Extract_Date,'DD/MM/YYYY') Extract_Date, "
       + " to_char(Start_Time,'HH24:MI') Start_Time, to_char(End_Time,'HH24:MI') End_Time "
       + " From BTM_IM_PROMO_HEAD Where Promo_ID='" + promoID + "'";
   try{
//       System.out.println(sqlStr);
       stmt = DAL.getConnection().createStatement(ResultSet.
                                       TYPE_SCROLL_INSENSITIVE,
                                       ResultSet.CONCUR_READ_ONLY);
       rs = DAL.executeScrollQueries(sqlStr,stmt);
       while (rs.next()){
         txtPromoID.setText(rs.getString("PROMO_ID"));
         txtPromoName.setText(rs.getString("PROMO_NAME"));
         oldStartDate = rs.getString("START_DATE");
         txtStartDate.setText(oldStartDate);
         txtEndDate.setText(rs.getString("END_DATE"));
         txtDesc.setText(rs.getString("DESCRIPTION"));
         String [] arrTime = (String []) rs.getString("START_TIME").split(":");
         cboStartHour.setSelectedItem(arrTime[0]);
         cboStartMinute.setSelectedItem(arrTime[1]);
         arrTime = (String []) rs.getString("END_TIME").split(":");
         cboEndHour.setSelectedItem(arrTime[0]);
         cboEndMinute.setSelectedItem(arrTime[1]);
         setComboStore(promoID);
         int ToDay =  Integer.parseInt(cvtDate(ut.getSystemDate(1)));
         int EndDay = Integer.parseInt(cvtDate(rs.getString("END_DATE")));
         int StartDay = Integer.parseInt(cvtDate(rs.getString("START_DATE")));
         setObjectEnable(false);

         // if  Start date >= today
         // enable edit Start date, desc, name
         if (!ut.compareDate(ut.getSystemDateStandard(),txtStartDate.getText())) {
           btnModify.setEnabled(true);
           txtDesc.setEnabled(true);
           txtPromoName.setEnabled(true);
           cboStartHour.setEnabled(true);
           cboStartMinute.setEnabled(true);
           cboEndHour.setEnabled(true);
           cboEndMinute.setEnabled(true);
           txtStartDate.setEnabled(true);
           btnStartDate.setEnabled(true);
         }
         // if  End date >= today
         // enable edit End date, desc, name
         if (!ut.compareDate(ut.getSystemDateStandard(),txtEndDate.getText())) {
           btnModify.setEnabled(true);
           txtDesc.setEnabled(true);
           txtPromoName.setEnabled(true);
           cboStartHour.setEnabled(true);
           cboStartMinute.setEnabled(true);
           cboEndHour.setEnabled(true);
           cboEndMinute.setEnabled(true);
           txtEndDate.setEnabled(true);
           btnEndDate.setEnabled(true);
         }

       }
       stmt.close();
       rs.close();
     }
     catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
  }
  void setObjectEnable(boolean flag){
    btnModify.setEnabled(flag);
    txtDesc.setEnabled(flag);
    txtEndDate.setEnabled(flag);
    txtPromoName.setEnabled(flag);
    txtStartDate.setEnabled(flag);
    btnStartDate.setEnabled(flag);
    btnEndDate.setEnabled(flag);
    cboStartHour.setEnabled(flag);
    cboStartMinute.setEnabled(flag);
    cboEndHour.setEnabled(flag);
    cboEndMinute.setEnabled(flag);
    cboStore.setEnabled(flag);
  }
  public void btnCancel_actionPerformed(ActionEvent e) {
    //show Dialog Promo Search
    DialogPromoSearch dlgPromoSearch = new DialogPromoSearch(frmMain,lang.getString("TT0112"),true, frmMain,frmMain.promoBusObj);
    dlgPromoSearch.setLocation(112, 85);
    dlgPromoSearch.updateScreen();
    dlgPromoSearch.setVisible(true);
    setEnabled(true);
    if(dlgPromoSearch.done){
       frmMain.showScreen(Constant.SCRS_PROMOTION_HEAD_MODIFY);
     }else{
       frmMain.showScreen(Constant.SCRS_PROMOTION_HEAD);
     }

  }

  public void btnStartDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnStartDate.getX() + posXFrame + 90;
    posY = this.btnEndDate.getY() + posYFrame + 90;
    TimeDlg startdate = new TimeDlg(null);
    startdate.setTitle(lang.getString("ChooseStartDate"));
    startdate.setResizable(false);
    startdate.setLocation(posX,posY);
    startdate.setVisible(true);
    if (startdate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
      String date = fd.format(startdate.getDate());
      if (!ut.compareDate(ut.getSystemDateStandard(),date)) {
        this.txtStartDate.setText(date);
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1164_StartDateGreaterOrEqualCurrentDate"));
      }


    }

  }

  public void btnEndDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnStartDate.getX() + posXFrame + 90;
    posY = this.btnEndDate.getY() + posYFrame + 90;
    TimeDlg startdate = new TimeDlg(null);
    startdate.setTitle(lang.getString("ChooseEndDate"));
    startdate.setResizable(false);
    startdate.setLocation(posX,posY);
    startdate.setVisible(true);
    if (startdate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(startdate.getDate());

      java.sql.Date tmpDate = java.sql.Date.valueOf(ut.getYYYY_MM_DD(date));
      if (!ut.compareDate(ut.getSystemDateStandard(),ut.getDD_MM_YYYY(ut.addDate(tmpDate,1)))) {
        this.txtEndDate.setText(date);
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       "End date must be greater than or equal yesterday.");
      }
    }

  }

    private boolean checkExist(String promoID, String promoName){
    ResultSet rs = null;
    String sqlStr = "Select Promo_Name From BTM_IM_PROMO_HEAD Where lower(Promo_Name)=lower('" + promoName
        + "') and Promo_Id <> '" + promoID + "'";
    try{
//      System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr,stmt);
      if(rs.next()){
        stmt.close();
        return true;
      }
      stmt.close();
    }
    catch(Exception ex){}
    return false;
  }

  String getExtractDate(String PromoID){
    String extDate="";
    String sql="Select to_char(EXTRACT_DATE,'DD/MM/YYYY') EXTRACT_DATE From BTM_IM_PROMO_HEAD Where PROMO_ID ='" + PromoID + "'";
    ResultSet rs=null;
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if(rs.next()){
        extDate = rs.getString("EXTRACT_DATE");
      }
      stmt.close();
      rs.close();
    }catch(Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return extDate;
  }

  String getEndDate(String PromoID){
    String endDate="";
    String sql="Select to_char(End_Date,'DD/MM/YYYY') End_Date From BTM_IM_PROMO_HEAD Where PROMO_ID ='" + PromoID + "'";
    ResultSet rs=null;
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if(rs.next()){
        endDate = rs.getString("End_Date");
      }
      stmt.close();
      rs.close();
    }catch(Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return endDate;
  }

  String getStartDate(String PromoID){
    String StartDate="";
    String sql="Select to_char(Start_Date,'DD/MM/YYYY') Start_Date From BTM_IM_PROMO_HEAD Where PROMO_ID ='" + PromoID + "'";
    ResultSet rs=null;
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if(rs.next()){
        StartDate = rs.getString("Start_Date");
      }
      stmt.close();
      rs.close();
    }catch(Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return StartDate;
  }

  String getSysTime(){
    String time ="";
    String sql = "Select sysdate From dual";
    ResultSet rs=null;
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if(rs.next()){
        time = rs.getString("sysdate");
        int p0 = time.indexOf(" ")+1;
        int p1 = time.indexOf(".");
        time = time.substring(p0,p1);
      }
      stmt.close();
      rs.close();
    }catch(Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return time;
  }
//  void modifyEndDate(){
//    String promoID = txtPromoID.getText().trim();
//    String endDate = txtEndDate.getText().trim();
//    String endMinu = cboEndMinute.getSelectedItem().toString();
//    String endHour = cboEndHour.getSelectedItem().toString();
//    int nEndTime = Integer.parseInt(endHour)*60 + Integer.parseInt(endMinu);
//    String SysTime = getSysTime();
//    String SysHour = SysTime.substring(0,2);
//    String SysMinu = SysTime.substring(3,5);
//    int nSysTime = Integer.parseInt(SysHour)*60 + Integer.parseInt(SysMinu);
//    System.out.println("sys:" + nSysTime);
//    System.out.println("end:" + nEndTime);
//    int ToDay =  Integer.parseInt(cvtDate(ut.getSystemDate(1)));
//    int EndDate = Integer.parseInt(cvtDate(endDate));
//    if(!ut.checkDate(endDate,"/") || endDate.equals("")){
//      ut.showMessage(frmMain,lang.getString("TT001"), "End date must be a date type. It is not null");
//      txtEndDate.requestFocus();
//      txtEndDate.setSelectionStart(0);
//      txtEndDate.setSelectionEnd(txtEndDate.getText().length());
//      return;
//    }
//    if(EndDate<ToDay){
//      ut.showMessage(frmMain,lang.getString("TT001"), "End date must be greater than or equal today");
//      txtEndDate.requestFocus();
//      txtEndDate.setSelectionStart(0);
//      txtEndDate.setSelectionEnd(txtEndDate.getText().length());
//      return;
//    }
//
//
//    String EndDateTime = endDate + " " + endHour + ":" + endMinu;
//    String sqlPromHead = "Update BTM_IM_PROMO_HEAD Set END_DATE=to_date('" + endDate + "','DD/MM/YYYY')," +
//        "END_TIME=to_date('" + EndDateTime + "','DD/MM/YYYY HH24:MI'), EXTRACT_DATE=to_date('07/07/7777','DD/MM/YYYY') " +
//        " Where PROMO_ID='" + promoID + "'";
//
//    String sqlPromMixMatch = "Update BTM_IM_MIX_MATCH_HEAD Set EXTRACT_DATE=to_date('07/07/7777','DD/MM/YYYY') " +
//        " Where PROMO_ID='" + promoID + "'";
//
//    String sqlPromThresHold = "Update BTM_IM_PROMO_THRESHOLD_HEAD Set EXTRACT_DATE=to_date('07/07/7777','DD/MM/YYYY') " +
//        " Where PROMO_ID='" + promoID + "'";
//
//    String sqlPromStore = "Update BTM_IM_PROMO_STORE Set END_DATE=to_date('" + endDate + "','DD/MM/YYYY')," +
//        "EXTRACT_DATE=to_date('07/07/7777','DD/MM/YYYY') " +
//        " Where PROMO_ID='" + promoID + "'";
//System.out.println(sqlPromHead);
//    System.out.println(sqlPromMixMatch);
//    System.out.println(sqlPromThresHold);
//    System.out.println(sqlPromStore);
//
//
//    updDataModifyEndDate(sqlPromHead);
//    updDataModifyEndDate(sqlPromMixMatch);
//    updDataModifyEndDate(sqlPromThresHold);
//    updDataModifyEndDate(sqlPromStore);
//    returnDialogSearch();
//  }

  void modifyAll(){
    String promoID = txtPromoID.getText().trim();
    String startDate = txtStartDate.getText().trim();
    String endDate = txtEndDate.getText().trim();
    String startMinu = cboStartMinute.getSelectedItem().toString();
    String startHour = cboStartHour.getSelectedItem().toString();
    String endMinu = cboEndMinute.getSelectedItem().toString();
    String endHour = cboEndHour.getSelectedItem().toString();
    String desc = txtDesc.getText().trim();
    String name = txtPromoName.getText().trim();
    int ToDay =  Integer.parseInt(cvtDate(ut.getSystemDate(1)));
    int StartDate = Integer.parseInt(cvtDate(startDate));
    int nStartTime = Integer.parseInt(startHour)*60 + Integer.parseInt(startMinu);
    String SysTime = getSysTime();
    String SysHour = SysTime.substring(0,2);
    String SysMinu = SysTime.substring(3,5);
    int nSysTime = Integer.parseInt(SysHour)*60 + Integer.parseInt(SysMinu);

//    if(!ut.checkDate(startDate,"/") || startDate.equals("")){
//      ut.showMessage(frmMain,lang.getString("TT001"), "Start date must be a date type. It is not null");
//      txtStartDate.requestFocus();
//      txtStartDate.setSelectionStart(0);
//      txtStartDate.setSelectionEnd(txtStartDate.getText().length());
//      return;
//    }
//    if(!ut.checkDate(endDate,"/") || endDate.equals("")){
//      ut.showMessage(frmMain,lang.getString("TT001"), "End date must be a date type. It is not null");
//      txtEndDate.requestFocus();
//      txtEndDate.setSelectionStart(0);
//      txtEndDate.setSelectionEnd(txtEndDate.getText().length());
//      return;
//    }

//    if(StartDate<ToDay){
//      ut.showMessage(frmMain,lang.getString("TT001"), "Start date must be greater than or equal today");
//      txtStartDate.requestFocus();
//      txtStartDate.setSelectionStart(0);
//      txtStartDate.setSelectionEnd(txtStartDate.getText().length());
//      return;
//    }
//    if (ut.compareDate(startDate, endDate)) {
//      ut.showMessage(frmMain, lang.getString("TT001"), "End date must be greater than or equal start date ");
//      txtEndDate.requestFocus();
//      txtEndDate.setSelectionStart(0);
//      txtEndDate.setSelectionEnd(txtEndDate.getText().length());
//      return;
//   }
//   if(StartDate==ToDay && nStartTime<=nSysTime){
//     ut.showMessage(frmMain,lang.getString("TT001"), "Start time must be greater than now");
//     cboStartMinute.requestFocus();
//     return;
//   }
    if (ut.compareDate(txtStartDate.getText(),txtEndDate.getText())){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1158_EndDateGreaterOrEqualStartDate"));
      return;
    }

    if (txtPromoName.getText().trim().length()>40) {
       ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1163_LenghtPromotionNameSoLong"));
       txtPromoName.requestFocus(true);
       return;
     }

   if (txtDesc.getText().trim().length()>120) {
      ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1162_LenghtDescriptionSoLongShouldLess120Characters"));
      txtDesc.requestFocus(true);
      return;
    }

   String EndDateTime = endDate + " " + endHour + ":" + endMinu;
   String StartDateTime = startDate + " " + startHour + ":" + startMinu;

   String sqlPromHead = "Update BTM_IM_PROMO_HEAD Set END_DATE=to_date('" + endDate + "','DD/MM/YYYY')," +
       "END_TIME=to_date('" + EndDateTime + "','DD/MM/YYYY HH24:MI'), START_DATE=to_date('" +
       startDate + "','DD/MM/YYYY'),START_TIME=to_date('" + StartDateTime + "','DD/MM/YYYY HH24:MI')," +
       "DESCRIPTION = '" + desc + "', PROMO_NAME = '"+name+"' , EXTRACT_DATE=to_date('07/07/7777','DD/MM/YYYY') Where PROMO_ID='" + promoID + "'";
   String sqlPromMixMatch = "Update BTM_IM_MIX_MATCH_HEAD Set EXTRACT_DATE=to_date('07/07/7777','DD/MM/YYYY') " +
       " Where PROMO_ID='" + promoID + "'";

   String sqlPromThresHold = "Update BTM_IM_PROMO_THRESHOLD_HEAD Set EXTRACT_DATE=to_date('07/07/7777','DD/MM/YYYY') " +
       " Where PROMO_ID='" + promoID + "'";

   String sqlPromStore = "Update BTM_IM_PROMO_STORE Set END_DATE=to_date('" + endDate + "','DD/MM/YYYY')," +
       "START_DATE=to_date('" + startDate + "','DD/MM/YYYY') " +
       " Where PROMO_ID='" + promoID + "'";
//System.out.println(sqlPromHead);
   updDataModifyEndDate(sqlPromHead);
   updDataModifyEndDate(sqlPromMixMatch);
   updDataModifyEndDate(sqlPromThresHold);
   updDataModifyEndDate(sqlPromStore);
   returnDialogSearch();


  }

  public void btnModify_actionPerformed(ActionEvent e) {
//    String promoID = txtPromoID.getText().trim();
//    int ToDay =  Integer.parseInt(cvtDate(ut.getSystemDate(1)));
//    int EndDate = Integer.parseInt(cvtDate(getEndDate(promoID)));
//    String ExtDate = getExtractDate(promoID);
//    int StartDay = Integer.parseInt(cvtDate(getStartDate(promoID)));

    if (ut.compareDate(txtStartDate.getText(),txtEndDate.getText())){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1158_EndDateGreaterOrEqualStartDate"));
      return;
    }

    modifyAll();

//    //Already export to Pos, StartDay < ToDay < EndDate
//    if((!ExtDate.equalsIgnoreCase("07/07/7777") && EndDate>=ToDay)||
//       (ExtDate.equalsIgnoreCase("07/07/7777") && StartDay<=ToDay)){
//      modifyEndDate();
//    }else{
//      ut.showMessage(frmMain,lang.getString("TT001"), "Start date must be greater or equal today!");
//    }

  }

  void updDataModifyEndDate(String sql){
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement();
      DAL.executeUpdate(sql, stmt);
      stmt.close();
    }catch(Exception ex){}
  }



  private void returnDialogSearch(){
    //show Dialog Promo Search
    DialogPromoSearch dlgPromoSearch = new DialogPromoSearch(frmMain,
        lang.getString("TT0112"), true, frmMain, frmMain.promoBusObj);
    dlgPromoSearch.setLocation(112, 85);
    dlgPromoSearch.updateScreen();
    String startTime = cboStartHour.getSelectedItem().toString() + ":" + cboStartMinute.getSelectedItem().toString();
    String endTime = cboEndHour.getSelectedItem().toString() + ":" + cboEndMinute.getSelectedItem().toString();
    String [] arrInfo = new String [] {txtPromoName.getText(),txtDesc.getText(),txtStartDate.getText(),txtEndDate.getText(),startTime,endTime};
    dlgPromoSearch.updateRowOnTable(arrInfo);
    setEnabled(true);
    dlgPromoSearch.setVisible(true);
    if(dlgPromoSearch.done){
       frmMain.showScreen(Constant.SCRS_PROMOTION_HEAD_MODIFY);
     }else{
       frmMain.showScreen(Constant.SCRS_PROMOTION_HEAD);
     }

 }
  private void updateModify(String sqlStr){
//    System.out.println(sqlStr);
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);
      DAL.executeUpdate(sqlStr, stmt);
      stmt.close();
    }catch(Exception ex){};
  }
  boolean checkExistOnGridModify(String promoName){
    for (int i = 0; i < dmPromoHead.getRowCount(); i++) {
      if (i != tblPromoHead.getSelectedRow() &&
          dmPromoHead.getValueAt(i, PROMO_NAME).toString().equals(promoName.trim()))
        return true;
    }
    return false;
  }
  private void registerKeyboardActions() {
/// set up the maps:
InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
ActionMap actionMap = getActionMap();

// F4
Integer key = new Integer(KeyEvent.VK_F4);
inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), key);
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
class KeyAction extends AbstractAction {

   private Integer identifier = null;

   public KeyAction(Integer identifier) {
     this.identifier = identifier;
   }

   /**
    * Invoked when an action occurs.
    */
   public void actionPerformed(ActionEvent e) {

     if (identifier.intValue() == KeyEvent.VK_F4) {
       btnModify.doClick();
     }else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
       btnCancel.doClick();
     }
   }
 }


}

class PanelPromotionHeadModify_btnModify_actionAdapter
    implements ActionListener {
  private PanelPromotionHeadModify adaptee;
  PanelPromotionHeadModify_btnModify_actionAdapter(PanelPromotionHeadModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelPromotionHeadModify_btnEndDate_actionAdapter
    implements ActionListener {
  private PanelPromotionHeadModify adaptee;
  PanelPromotionHeadModify_btnEndDate_actionAdapter(PanelPromotionHeadModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnEndDate_actionPerformed(e);
  }
}

class PanelPromotionHeadModify_btnStartDate_actionAdapter
    implements ActionListener {
  private PanelPromotionHeadModify adaptee;
  PanelPromotionHeadModify_btnStartDate_actionAdapter(PanelPromotionHeadModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnStartDate_actionPerformed(e);
  }
}


class PanelPromotionHeadModify_btnCancel_actionAdapter
    implements ActionListener {
  private PanelPromotionHeadModify adaptee;
  PanelPromotionHeadModify_btnCancel_actionAdapter(PanelPromotionHeadModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}
