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
 * <p>Title: Promotion Head Setup </p>
 * <p>Description: Main -> Merch Mgmt -> Promotion Head </p>
 * <p>Copyright: Copyright (c) 2006  </p>
 * <p>Company: BTM </p>
 * @author Vinh Le
 * @version 1.0
 */
public class PanelPromotionHead extends JPanel {
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
  BJButton btnDelete = new BJButton();
  BJButton btnClear = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
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
  BJButton btnSearch = new BJButton();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLblStore = new JLabel();
  JComboBox cboStore = new JComboBox();
  public PanelPromotionHead() {
    try {
      jbInit();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
  public PanelPromotionHead(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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
  private void initTimeCombo(){
    int i;
    String strTemp="";
    for(i = 0;i<24;i++ ){
      if(i<10){
        strTemp="0" + String.valueOf(i);
      }else{
        strTemp = String.valueOf(i);
      }
      cboStartHour.addItem(strTemp);
      cboEndHour.addItem(strTemp);
    }
    for(i=0;i<60;i+=5)  {
      if(i<10){
        strTemp="0" + String.valueOf(i);
      }else{
        strTemp = String.valueOf(i);
      }
      cboStartMinute.addItem(strTemp);
      cboEndMinute.addItem(strTemp);
    }
  }

  public void initComboStore(){
    cboStore.addItem("NONE");
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
    }catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void initScreen(){
    promoID = ut.getDateTimeID();
    txtPromoID.setText(promoID);
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
    btnModify.addActionListener(new PanelPromotionHead_btnModify_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new PanelPromotionHead_btnCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnDelete.addActionListener(new PanelPromotionHead_btnDelete_actionAdapter(this));
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear")+" (F7)");
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnClear.addActionListener(new PanelPromotionHead_btnClear_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                   "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelPromotionHead_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelPromotionHead_btnDone_actionAdapter(this));
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
    txtPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPromoName.setPreferredSize(new Dimension(175, 22));
    btnEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnEndDate.setMinimumSize(new Dimension(30, 23));
    btnEndDate.setPreferredSize(new Dimension(30, 23));
    btnEndDate.setText("jButton1");
    btnEndDate.addActionListener(new
                                 PanelPromotionHead_btnEndDate_actionAdapter(this));
    btnStartDate.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
    btnStartDate.setPreferredSize(new Dimension(30, 23));
    btnStartDate.setText("jButton1");
    btnStartDate.addActionListener(new
        PanelPromotionHead_btnStartDate_actionAdapter(this));
    txtPromoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPromoID.setPreferredSize(new Dimension(175, 22));
    txtPromoID.setDisabledTextColor(SystemColor.info);
    txtPromoID.setEditable(false);
    pnlRightLabel.setPreferredSize(new Dimension(150, 10));
    pnlRightLabel.setLayout(flowLayout2);
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel7.setPreferredSize(new Dimension(80, 22));
    jLabel7.setText(lang.getString("EndTime")+":");

    jLblStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLblStore.setPreferredSize(new Dimension(80, 22));
    jLblStore.setText(lang.getString("Store")+":");

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
    cboStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboStore.setPreferredSize(new Dimension(130, 22));
    cboEndMinute.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboEndMinute.setPreferredSize(new Dimension(52, 22));
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel9.setMinimumSize(new Dimension(34, 16));
    jLabel9.setPreferredSize(new Dimension(34, 16));
    jLabel9.setText(lang.getString("mm"));
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel10.setPreferredSize(new Dimension(24, 16));
    jLabel10.setText(lang.getString("hh"));
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel11.setPreferredSize(new Dimension(34, 16));
    jLabel11.setText(lang.getString("mm"));
    jLabel12.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel12.setPreferredSize(new Dimension(24, 16));
    jLabel12.setText(lang.getString("hh"));
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    showButton(true);
    tblPromoHead.addMouseListener(new
                                  PanelPromotionHead_tblPromoHead_mouseAdapter(this));
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDesc.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDesc.setMinimumSize(new Dimension(635, 15));
    txtDesc.setPreferredSize(new Dimension(594, 38));
    txtDesc.setLineWrap(true);
    txtDesc.setWrapStyleWord(true);
    txtDesc.addKeyListener(new PanelPromotionHead_txtDesc_keyAdapter(this));
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnSearch.addActionListener(new PanelPromotionHead_btnSearch_actionAdapter(this));
    jLabel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel13.setForeground(Color.red);
    jLabel13.setText("(*)");
    jLabel14.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel14.setForeground(Color.red);
    jLabel14.setText("(*)");
    flowLayout1.setAlignment(FlowLayout.LEFT);
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setForeground(Color.red);
    jLabel6.setText("(*)");
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
    pnlRightLabel.add(jLblStore);
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
 public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_PROMOTION_HEAD);
    btnAdd.setEnabled(er.getAdd());
    btnSearch.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnModify.setEnabled(er.getModify());
    //  btnAdminRoleReport.setEnabled(er.getReport());
    if(!er.getAdd()){
      ut.showMessage(frmMain, lang.getString("TT002"),
                     lang.getString("MS1085_CanNotUseScreen"));

    }

  }

  void showButton(boolean flagSetButton) {
    this.pnlHeader.removeAll();
    this.pnlHeader.updateUI();
    pnlHeader.add(btnDone, null);
    if (flagSetButton == true)
      pnlHeader.add(btnAdd, null);
    else
      pnlHeader.add(btnModify, null);
    pnlHeader.add(btnSearch, null);
    pnlHeader.add(btnClear, null);
    pnlHeader.add(btnDelete, null);
    pnlHeader.add(btnCancel, null);
  }

  public void btnCancel_actionPerformed(ActionEvent e) {
    returnPreviousScreen();
  }

  public void txtPromoID_actionPerformed(ActionEvent e) {

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
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
      String date = fd.format(startdate.getDate());
      if (!ut.compareDate(ut.getSystemDateStandard(),date)) {
        this.txtEndDate.setText(date);
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1158_EndDateGreaterOrEqualStartDate"));
      }
    }

  }



  public void btnAdd_actionPerformed(ActionEvent e) {

    String promoName = txtPromoName.getText();
    String startDate = txtStartDate.getText().trim();
    String endDate = txtEndDate.getText().trim();
    String startTime = cboStartHour.getSelectedItem().toString() + ":" + cboStartMinute.getSelectedItem().toString();
    String endTime = cboEndHour.getSelectedItem().toString() + ":" + cboEndMinute.getSelectedItem().toString();

    String strStore = cboStore.getSelectedItem().toString();

    if (promoName.trim().length()==0) {
      ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1159_PromotionNameNotNull"));
      txtPromoName.requestFocus(true);
      return;
    }
    if (promoName.trim().length()>40) {
      ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1163_LenghtPromotionNameSoLong"));
      txtPromoName.requestFocus(true);
      return;
    }
    if(checkExist(promoName)){
      ut.showMessage(frmMain, lang.getString("TT022"),lang.getString("MS1160_PromotionNameExisted"));
      txtPromoName.requestFocus(true);
      return;
    }
    if (checkExistOnGrid(promoName)) {
      ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1160_PromotionNameExisted"));
      txtPromoName.requestFocus(true);
      return;
    }

//    if (!ut.checkDate(startDate, "/") ||  startDate.equals("")){
//      ut.showMessage(frmMain,"Warning", "Start date must be a date type. It is not null");
//      txtStartDate.requestFocus();
//      txtStartDate.setSelectionStart(0);
//      txtStartDate.setSelectionEnd(txtStartDate.getText().length());
//      return;
//    }
//    if (ut.compareDate( ut.getSystemDateStandard(),startDate)) {
//        ut.showMessage(frmMain, "Warning", "Start date must be greater than or equal current date.");
//        txtStartDate.requestFocus();
//        txtStartDate.setSelectionStart(0);
//        txtStartDate.setSelectionEnd(txtStartDate.getText().length());
//        return;
//    }
//    if(!ut.checkDate(endDate,"/") || endDate.equals("")){
//        ut.showMessage(frmMain,"Warning", "End date must be a date type. It is not null");
//        txtEndDate.requestFocus();
//        txtEndDate.setSelectionStart(0);
//        txtEndDate.setSelectionEnd(txtEndDate.getText().length());
//        return;
//     }
     if (ut.compareDate(startDate, endDate)) {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1158_EndDateGreaterOrEqualStartDate"));
        txtEndDate.requestFocus();
        txtEndDate.setSelectionStart(0);
        txtEndDate.setSelectionEnd(txtEndDate.getText().length());
        return;
    }
    if (txtDesc.getText().trim().length()>120) {
      ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1162_LenghtDescriptionSoLongShouldLess120Characters"));
      txtDesc.requestFocus(true);
      return;
    }
    if (!ut.compareDate(startDate, endDate) && !ut.compareDate(endDate, startDate)) {
        int startMins = Integer.parseInt(cboStartHour.getSelectedItem().toString()) * 60
            + Integer.parseInt(cboStartMinute.getSelectedItem().toString());
        int endMins = Integer.parseInt(cboEndHour.getSelectedItem().toString()) * 60
            + Integer.parseInt(cboEndMinute.getSelectedItem().toString());
        if(startMins > endMins){
          ut.showMessage(frmMain, lang.getString("TT001"),
              lang.getString("MS1158_EndDateGreaterOrEqualStartDate"));
          txtEndDate.requestFocus();
          txtEndDate.setSelectionStart(0);
          txtEndDate.setSelectionEnd(txtEndDate.getText().length());
          return;
        }
    }
    if(strStore.equalsIgnoreCase("NONE")){
      ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1161_StoreNameNotNONE"));
      txtPromoName.requestFocus(true);
      return;
    }

    vInfo.addElement(txtDesc.getText().trim());
    dmPromoHead.addRow(new Object[] {txtPromoID.getText(),txtPromoName.getText(),startDate,endDate, startTime,endTime,strStore});
    promoID= ut.getDateTimeID();
    resetData();
  }
  private void resetData(){
    txtPromoID.setText(promoID);
    txtPromoName.setText("");
    txtStartDate.setText("");
    txtEndDate.setText("");
    txtDesc.setText("");
    cboStartHour.setSelectedIndex(0);
    cboStartMinute.setSelectedIndex(0);
    cboEndHour.setSelectedIndex(0);
    cboEndMinute.setSelectedIndex(0);
    cboStore.setSelectedIndex(0);
    txtPromoName.requestFocus(true);
  }
  private boolean checkExist(String promoName){
    ResultSet rs = null;
    String sqlStr = "Select Promo_Name From BTM_IM_PROMO_HEAD Where Promo_Name='" + promoName + "'";
    try{
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
  private boolean checkExistOnGrid(String promoName){
    for(int i=0;i < dmPromoHead.getRowCount();i++){
      if(dmPromoHead.getValueAt(i,1).toString().equalsIgnoreCase(promoName.trim()))
        return true;
    }
    return false;
  }
  private void setSQLsForPromoStore(String store,String promoID, String startDate, String endDate){
    String strSql,sqlStore;
    ResultSet rs = null;
    try{
      if(store.equalsIgnoreCase("ALL")){
        sqlStore = "Select STORE_ID From BTM_IM_STORE";
      }else{
        sqlStore = "Select STORE_ID From BTM_IM_STORE where NAME ='" + store + "'";
      }

      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStore,stmt);
      while(rs.next()){
        strSql = "insert into BTM_IM_PROMO_STORE (PROMO_ID,STORE_ID,START_DATE,END_DATE,EXTRACT_DATE) values ('"
          + promoID + "','" + rs.getString("STORE_ID") + "',to_date('" + startDate
          + "','dd/MM/yyyy'),to_date('" + endDate +
          "','dd/MM/yyyy'),'07/Jul/7777')";
//        System.out.println(strSql);
        vSql.addElement(strSql);
      }
      stmt.close();
    }
    catch(Exception ex){}
  }
  private void setSQLsForVector(){
    String strSql,promoID,promoName,startDate,endDate,startDateTime,endDateTime,extractDate,desc;
    String createDate=ut.getSystemDate(1);
    String createUser=DAL.getEmpID();
    String Store = "";
    for(int i=0;i< tblPromoHead.getRowCount();i++){
      promoID = tblPromoHead.getValueAt(i,PROMO_ID).toString();
      promoName = tblPromoHead.getValueAt(i,PROMO_NAME).toString();
      startDate = tblPromoHead.getValueAt(i,START_DATE).toString();
      endDate = tblPromoHead.getValueAt(i,END_DATE).toString();
      startDateTime = tblPromoHead.getValueAt(i,START_DATE).toString() + " " + tblPromoHead.getValueAt(i,START_TIME).toString();
      endDateTime = tblPromoHead.getValueAt(i,END_DATE).toString() + " " + tblPromoHead.getValueAt(i,END_TIME).toString();
      desc = vInfo.get(i).toString() ;
      Store = tblPromoHead.getValueAt(i,STORE).toString();
      strSql="insert into BTM_IM_PROMO_HEAD (PROMO_ID, PROMO_NAME,START_DATE,END_DATE,START_TIME,END_TIME,CREATE_DATE,CREATE_USER,EXTRACT_DATE,DESCRIPTION,STATUS) values ('"
          + promoID + "','" + promoName + "',to_date('" + startDate +
          "','dd/MM/yyyy'),to_date('" + endDate + "','dd/MM/yyyy'),"
          + "to_date('" + startDateTime + "','dd/MM/yyyy HH24:MI'),to_date('" +
          endDateTime + "','dd/MM/yyyy HH24:MI'),to_date('"
          + createDate + "','dd/MM/yyyy'),'" + createUser + "', '07/Jul/7777','" + desc + "','A')";
//      System.out.println(strSql);
      vSql.addElement(strSql);
      setSQLsForPromoStore(Store,promoID,startDate,endDate);
    }
  }
  public void btnDone_actionPerformed(ActionEvent e) {
    if(tblPromoHead.getRowCount()>0){
      setSQLsForVector();
      if (vSql.isEmpty() == false) {
        try {
          stmt = DAL.getConnection().createStatement(ResultSet.
              TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
          DAL.executeBatchQuery(vSql, stmt);
          stmt.close();
        }
        catch (Exception ex) {}
      }
    }
    returnPreviousScreen();
  }
  private void returnPreviousScreen(){
    resetData();
    while (dmPromoHead.getRowCount() > 0) {
        dmPromoHead.removeRow(0);
      }
    vSql.removeAllElements();
    vInfo.removeAllElements();
    showButton(true);
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0108"));
   // frmMain.pnlMainSim.flagSetHotkey = frmMain.pnlMainSim.FLAG_SET_HOTKEY_SCR_MERCH_MAINT_PROMO;
    frmMain.pnlMainSim.btnPromotionHead.requestFocus(true);


 }
 void updateValueFromSelecledItemInTable(){
   int row=tblPromoHead.getSelectedRow();
   if(row >= 0){
     txtPromoID.setText(tblPromoHead.getValueAt(row, PROMO_ID).toString());
     txtPromoName.setText(tblPromoHead.getValueAt(row, PROMO_NAME).toString());
     txtStartDate.setText(tblPromoHead.getValueAt(row, START_DATE).toString());
     txtEndDate.setText(tblPromoHead.getValueAt(row, END_DATE).toString());
     String [] arrTime = (String []) tblPromoHead.getValueAt(row, START_TIME).toString().split(":");
     cboStartHour.setSelectedItem(arrTime[0]);
     cboStartMinute.setSelectedItem(arrTime[1]);
     arrTime = (String []) tblPromoHead.getValueAt(row, END_TIME).toString().split(":");
     cboEndHour.setSelectedItem(arrTime[0]);
     cboEndMinute.setSelectedItem(arrTime[1]);
     txtDesc.setText(vInfo.get(row).toString());
      showButton(false);//show Modify; hide Add
//     flagSetHotKeyForAdd = false;
     txtPromoName.requestFocus(true);
   }
  }
  public void btnDelete_actionPerformed(ActionEvent e) {
    int row= tblPromoHead.getSelectedRow();
    if(row>=0){
      int choose = ut.showMessage(frmMain, lang.getString("TT022"),
                                  lang.getString("MS1021_DeletePromotion") + tblPromoHead.getValueAt(row,PROMO_NAME).toString() + "' ?", 3, DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.YES_VALUE) {
        vInfo.remove(row);
        dmPromoHead.removeRow(row);
        resetData();
        showButton(true);
      }
    }
    else{
      if(tblPromoHead.getRowCount()>0){
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1019_ChoosePromotionWantDelete"));
        return;
      }
    }
  }

  public void tblPromoHead_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
          updateValueFromSelecledItemInTable();
    }
  }

  public void btnModify_actionPerformed(ActionEvent e) {
      String promoName = txtPromoName.getText();
      int row=tblPromoHead.getSelectedRow();
      if (promoName.trim().length()==0) {
        ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1159_PromotionNameNotNull"));
        txtPromoName.requestFocus(true);
        return;
      }
      if(checkExist(promoName)){
        ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1160_PromotionNameExisted"));
        txtPromoName.requestFocus(true);
        return;
      }
      if (checkExistOnGridModify(promoName)) {
        ut.showMessage(frmMain, lang.getString("TT022"), lang.getString("MS1160_PromotionNameExisted"));
        txtPromoName.requestFocus(true);
        return;
      }
//      if (!ut.checkDate(txtStartDate.getText().trim(), "/") ||  txtStartDate.getText().trim().equals("")){
//        ut.showMessage(frmMain,"Warning", "Input startdate must be a date type");
//        txtStartDate.requestFocus();
//        txtStartDate.setSelectionStart(0);
//        txtStartDate.setSelectionEnd(txtStartDate.getText().length());
//        return;
//      }
//      if(!ut.checkDate(txtEndDate.getText().trim(),"/") || txtEndDate.getText().trim().equals("")){
//        ut.showMessage(frmMain,"Warning", "Input enddate must be a date type");
//        txtEndDate.requestFocus();
//        txtEndDate.setSelectionStart(0);
//        txtEndDate.setSelectionEnd(txtEndDate.getText().length());
//        return;
//      }
      if (ut.compareDate(txtStartDate.getText(),txtEndDate.getText())){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1158_EndDateGreaterOrEqualStartDate"));
        return;
      }
      tblPromoHead.setValueAt(promoName,row,PROMO_NAME);
      tblPromoHead.setValueAt(txtStartDate.getText(),row,START_DATE);
      tblPromoHead.setValueAt(txtEndDate.getText(),row,END_TIME);
      tblPromoHead.setValueAt(cboStartHour.getSelectedItem().toString() + ":" + cboStartMinute.getSelectedItem().toString(),row,START_TIME);
      tblPromoHead.setValueAt(cboEndHour.getSelectedItem().toString() + ":" + cboEndMinute.getSelectedItem().toString(),row,END_TIME);
      vInfo.setElementAt(txtDesc.getText().trim(),row);
      txtPromoName.setText("");
      showButton(true);
      resetData();
  }
  boolean checkExistOnGridModify(String promoName){
    for (int i = 0; i < dmPromoHead.getRowCount(); i++) {
      if (i != tblPromoHead.getSelectedRow() &&
          dmPromoHead.getValueAt(i, PROMO_NAME).toString().equals(promoName.trim()))
        return true;
    }
    return false;
  }

  public void btnSearch_actionPerformed(ActionEvent e) {
     btm.business.PromotionBusObj promoBusObject = new btm.business.PromotionBusObj();
     DialogPromoSearch dlgPromoSearch = new DialogPromoSearch(frmMain,lang.getString("TT0112"),true, frmMain,promoBusObject);
     dlgPromoSearch.setVisible(true);
     if(dlgPromoSearch.done){
       frmMain.showScreen(Constant.SCRS_PROMOTION_HEAD_MODIFY);
     }else{
       frmMain.showScreen(Constant.SCRS_PROMOTION_HEAD);
     }
   }

  public void btnClear_actionPerformed(ActionEvent e) {
    showButton(true);
    resetData();
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
       btnDone.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F2) {
         btnAdd.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F8) {
       btnDelete.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F3) {
       btnSearch.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F4) {
            btnModify.doClick();
          }
     else if (identifier.intValue() == KeyEvent.VK_F7) {
       btnClear.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
       btnCancel.doClick();
     }
   }
 }

  void txtDesc_keyTyped(KeyEvent e) {
    ut.limitLenjTextArea(e, txtDesc,200);
  }

}

class PanelPromotionHead_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_btnSearch_actionAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelPromotionHead_btnClear_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_btnClear_actionAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelPromotionHead_tblPromoHead_mouseAdapter
    extends MouseAdapter {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_tblPromoHead_mouseAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblPromoHead_mouseClicked(e);
  }
}

class PanelPromotionHead_btnModify_actionAdapter
    implements ActionListener {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_btnModify_actionAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelPromotionHead_btnDelete_actionAdapter
    implements ActionListener {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_btnDelete_actionAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelPromotionHead_btnDone_actionAdapter
    implements ActionListener {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_btnDone_actionAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelPromotionHead_btnAdd_actionAdapter
    implements ActionListener {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_btnAdd_actionAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelPromotionHead_btnEndDate_actionAdapter
    implements ActionListener {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_btnEndDate_actionAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnEndDate_actionPerformed(e);
  }
}

class PanelPromotionHead_btnStartDate_actionAdapter
    implements ActionListener {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_btnStartDate_actionAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnStartDate_actionPerformed(e);
  }
}

class PanelPromotionHead_btnCancel_actionAdapter
    implements ActionListener {
  private PanelPromotionHead adaptee;
  PanelPromotionHead_btnCancel_actionAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelPromotionHead_txtDesc_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPromotionHead adaptee;

  PanelPromotionHead_txtDesc_keyAdapter(PanelPromotionHead adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtDesc_keyTyped(e);
  }
}
