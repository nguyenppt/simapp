package sim;


import java.util.*;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import btm.business.SeasonBusObj;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import btm.attr.*;
import java.beans.*;
import java.sql.Statement;
import java.sql.Date;
import java.util.*;
//import javax.swing.JPanel;

/**
 * <p>Title: </p>
 * <p>Description: MAIN -> REASON SETUP </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company:BTM Intersoft </p>
 * @author Loan Vo
 * @version 1.0
 */

public class PanelSeasonSetup extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
     //DataAccessLayer DAL;
     DataAccessLayer DAL = new DataAccessLayer();
     FrameMainSim frmMain;
     CardLayout cardLayout;
     JPanel parent;
     SeasonBusObj seaBusObj = new SeasonBusObj();
     Utils ut = new Utils();
     ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
     Vector vSql = new Vector();
     Vector vModify = new Vector();
     boolean flagSetHotKeyForAdd = true;
     int  vPosition = 0;
     int nSeaID = 10000;
     String hostId = "";
     int lenCodeSeaId = 5;
     String startDate,endDate;
     int rowNum = 5;
     Statement stmt = null;
     BorderLayout borderLayout2 = new BorderLayout();
     BorderLayout borderLayout3 = new BorderLayout();

     FlowLayout flowLayout1 = new FlowLayout();
     FlowLayout flowLayout2 = new FlowLayout();
     FlowLayout flowLayout3 = new FlowLayout();
     FlowLayout flowLayout4 = new FlowLayout();

     BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
     //buttons
     BJButton btnDone = new BJButton();
     BJButton btnAdd = new BJButton();
     BJButton btnDelete = new BJButton();
     BJButton btnSearch = new BJButton();
     BJButton btnClear = new BJButton();
     BJButton btnCancel = new BJButton();
     BJButton btnModify = new BJButton();


//------------declare Panel Infomation Left--------------------------//
     JPanel pnlInfoLeft = new JPanel();

     JPanel pnlInfoLeftLabel = new JPanel();

     JLabel lblSeasonID = new JLabel();
     JLabel lblSeaName = new JLabel();
     JLabel lblStartDate = new JLabel();
     JLabel lblEndDate = new JLabel();

     JPanel pnlInfoLeftTextfield = new JPanel();

     JTextField txtSeaID = new JTextField();
     JTextField txtSeaName = new JTextField();
     JTextField txtStartDate = new JTextField();
     JTextField txtEndDate = new JTextField();

     JPanel pnlInfoRight = new JPanel();

     JPanel pnlInfoRightLabel = new JPanel();

     JLabel lblDesc = new JLabel();

     JPanel pnlInfoRightTextfield = new JPanel();

     JScrollPane jScrollPane1 = new JScrollPane();
     SortableTableModel dm = new SortableTableModel(){
       public Class getColumnClass(int col) {
               switch (col) {
                       case 0:
                               return String.class;
                       case 2: return java.util.Date.class;
                       case 3: return java.util.Date.class;

                       default:
                               return Object.class;
               }
       }

     };

     BJTable table = new BJTable(dm, true) {
                     public boolean isCellEditable(int row, int col) {
                             return false;
                     }
             };

    StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray, Color.white, null, null);


    public PanelSeasonSetup(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
      try {
        this.frmMain = frmMain;
        this.cardLayout = crdLayout;
        this.parent = parent;
        DAL = this.frmMain.getDAL();
        jbInit();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }

  public PanelSeasonSetup(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  JPanel pnlAlignLeft = new JPanel();
  JButton jbtnStartdate = new JButton();
  JButton jBtnEnddate = new JButton();
  JTextArea txtDesc = new JTextArea();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  void jbInit() throws Exception {
    txtStartDate.setBackground(Color.white);
    txtStartDate.setEditable(true);
    txtStartDate.addKeyListener(new PanelSeasonSetup_txtStartDate_keyAdapter(this));
    this.setLayout(borderLayout1);
    registerKeyboardActions();

    this.setBackground(UIManager.getColor("Label.background"));
    this.setPreferredSize(new Dimension(800, 600));
    this.setLayout(borderLayout1);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    txtSeaID.setEditable(false);
    pnlAlignLeft.setMinimumSize(new Dimension(10, 50));
    pnlAlignLeft.setPreferredSize(new Dimension(30, 50));
    btnDone.addActionListener(new PanelSeasonSetup_btnDone_actionAdapter(this));
    btnDelete.addActionListener(new PanelSeasonSetup_btnDelete_actionAdapter(this));
    btnSearch.addActionListener(new PanelSeasonSetup_btnSearch_actionAdapter(this));
    btnCancel.addActionListener(new PanelSeasonSetup_btnCancel_actionAdapter(this));
    btnAdd.addActionListener(new PanelSeasonSetup_btnAdd_actionAdapter(this));
    btnClear.addActionListener(new PanelSeasonSetup_btnClear_actionAdapter(this));
    jbtnStartdate.setBounds(new Rectangle(258, 54, 29, 20));
    jbtnStartdate.setText("...");
    jbtnStartdate.addKeyListener(new PanelSeasonSetup_jbtnStartdate_keyAdapter(this));
    jbtnStartdate.addActionListener(new PanelSeasonSetup_jbtnStartdate_actionAdapter(this));
    jBtnEnddate.setText("...");
    jBtnEnddate.addKeyListener(new PanelSeasonSetup_jBtnEnddate_keyAdapter(this));
    jBtnEnddate.addActionListener(new PanelSeasonSetup_jBtnEnddate_actionAdapter(this));
    jBtnEnddate.setBounds(new Rectangle(258, 79, 29, 20));
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDesc.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDesc.setPreferredSize(new Dimension(200, 95));
    txtDesc.setText("");
    txtDesc.setRows(0);
    txtDesc.setWrapStyleWord(false);
    txtDesc.addKeyListener(new PanelSeasonSetup_txtDesc_keyAdapter(this));
    txtEndDate.setBackground(Color.white);
    txtEndDate.setEditable(true);
    txtEndDate.addKeyListener(new PanelSeasonSetup_txtEndDate_keyAdapter(this));
    jLabel1.setBackground(SystemColor.control);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setForeground(Color.red);
    jLabel1.setAlignmentY((float) 0.5);
    jLabel1.setText("(*)");
    jLabel1.setBounds(new Rectangle(264, 31, 24, 19));
    table.addMouseListener(new PanelSeasonSetup_table_mouseAdapter(this));
    btnModify.addActionListener(new PanelSeasonSetup_btnModify_actionAdapter(this));
    jLabel2.setBounds(new Rectangle(287, 54, 21, 19));
    jLabel2.setText("(*)");
    jLabel2.setAlignmentY((float) 0.5);
    jLabel2.setForeground(Color.red);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setBackground(SystemColor.control);
    txtSeaName.addKeyListener(new PanelSeasonSetup_txtSeaName_keyAdapter(this));
    table.addKeyListener(new PanelSeasonSetup_table_keyAdapter(this));
    this.add(pnlHeader, BorderLayout.NORTH);
    this.add(pnlAlignLeft, BorderLayout.WEST);
    this.add(pnlInfoLeft, BorderLayout.CENTER);
    this.add(pnlInfoRight, BorderLayout.EAST);
    this.add(jScrollPane1, BorderLayout.SOUTH);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    FlowLayout layout = new FlowLayout(FlowLayout.CENTER);

    //------------------------------------------------------------------------------------//
    //----------------Panel Header content all buttons------------------------------------//
    pnlHeader.setPreferredSize(new Dimension(850, 50));

    //Initial Buttons
    //Done
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done") + " (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    //Add
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add") + " (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    //Modify
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnModify.setToolTipText(lang.getString("Modify") + " (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</h" +
    "tml>");
    btnModify.setPreferredSize(new Dimension(120,37));

    //Search
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search") + " (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    //Delete
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete") + " (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    //Clear
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear") + " (F7)");
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");

    //Cancel
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    showButton(true);
//    pnlHeader.add(btnDone, null);
//    pnlHeader.add(btnAdd, null);
//    pnlHeader.add(btnModify, null);
//    pnlHeader.add(btnDelete, null);
//    pnlHeader.add(btnSearch, null);
//    pnlHeader.add(btnClear, null);
//    pnlHeader.add(btnCancel, null);

    //----------------------------------------------------------------------------------//
    //--------------Panel Infomation Left-----------------------------------------------//
    pnlInfoLeft.setPreferredSize(new Dimension(450, 150));
    pnlInfoLeft.setLayout(borderLayout2);

    pnlInfoLeft.add(pnlInfoLeftLabel, BorderLayout.WEST);
    pnlInfoLeft.add(pnlInfoLeftTextfield, BorderLayout.CENTER);

    //-----Panel content Lable-------------------------------------------------------//
    pnlInfoLeftLabel.setPreferredSize(new Dimension(120, 10));

    lblSeasonID.setText(lang.getString("SeasonID") + ":");
    lblSeasonID.setPreferredSize(new Dimension(120, 20));
    lblSeasonID.setHorizontalAlignment(SwingConstants.LEFT);
    lblSeasonID.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblSeasonID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    lblSeaName.setText(lang.getString("SeasonName") + ":");
    lblSeaName.setPreferredSize(new Dimension(120, 20));
    lblSeaName.setHorizontalAlignment(SwingConstants.LEFT);
    lblSeaName.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblSeaName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    lblStartDate.setText(lang.getString("StartDate") + ":");
    lblStartDate.setPreferredSize(new Dimension(120, 20));
    lblStartDate.setHorizontalAlignment(SwingConstants.LEFT);
    lblStartDate.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    lblEndDate.setText(lang.getString("EndDate") + ":");
    lblEndDate.setPreferredSize(new Dimension(120, 20));
    lblEndDate.setHorizontalAlignment(SwingConstants.LEFT);
    lblEndDate.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    pnlInfoLeftLabel.add(lblSeasonID, null);
    pnlInfoLeftLabel.add(lblSeaName, null);
    pnlInfoLeftLabel.add(lblStartDate, null);
    pnlInfoLeftLabel.add(lblEndDate, null);

    //-----Panel content Text Field-------------------------------------------------------//
    pnlInfoLeftTextfield.setDebugGraphicsOptions(0);
    pnlInfoLeftTextfield.setPreferredSize(new Dimension(260, 10));
    pnlInfoLeftTextfield.setLayout(null);

    txtSeaID.setText("");
    txtSeaID.setSelectionStart(0);
    txtSeaID.setPreferredSize(new Dimension(200, 20));
    txtSeaID.setBounds(new Rectangle(10, 5, 245, 20));
    txtSeaID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    txtSeaName.setText("");
    txtSeaName.setSelectionStart(0);
    txtSeaName.setPreferredSize(new Dimension(245, 20));
    txtSeaName.setBounds(new Rectangle(10, 30, 245, 20));
    txtSeaName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtStartDate.setText("");
    txtStartDate.setSelectionStart(0);
    txtStartDate.setPreferredSize(new Dimension(245, 20));
    txtStartDate.setBounds(new Rectangle(10, 54, 245, 20));
    txtStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEndDate.setText("");
    txtEndDate.setSelectionStart(0);
    txtEndDate.setPreferredSize(new Dimension(245, 20));
    txtEndDate.setBounds(new Rectangle(10, 79, 245, 20));
    txtEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    pnlInfoLeftTextfield.add(txtSeaID, null);
    pnlInfoLeftTextfield.add(txtSeaName, null);
    pnlInfoLeftTextfield.add(txtStartDate, null);
    pnlInfoLeftTextfield.add(txtEndDate, null);
    pnlInfoLeftTextfield.add(jLabel1, null);
    pnlInfoLeftTextfield.add(jBtnEnddate, null);
    pnlInfoLeftTextfield.add(jbtnStartdate, null);
    pnlInfoLeftTextfield.add(jLabel2, null);

    lblSeasonID.setDisplayedMnemonic('0');

    pnlInfoRight.setPreferredSize(new Dimension(330, 150));
    pnlInfoRight.setLayout(borderLayout3);

    pnlInfoRight.add(pnlInfoRightTextfield, BorderLayout.CENTER);
    pnlInfoRightTextfield.add(txtDesc, null);
    pnlInfoRight.add(pnlInfoRightLabel, BorderLayout.WEST);
    pnlInfoRightLabel.add(lblDesc, null);

    pnlInfoRightLabel.setDebugGraphicsOptions(0);
    pnlInfoRightLabel.setPreferredSize(new Dimension(100, 10));
    pnlInfoRightLabel.setLayout(flowLayout3);

    lblDesc.setText(lang.getString("Description") + ": ");
    lblDesc.setPreferredSize(new Dimension(110, 20));
    lblDesc.setHorizontalAlignment(SwingConstants.LEFT);
    lblDesc.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    pnlInfoRightTextfield.setPreferredSize(new Dimension(230, 10));
    pnlInfoRightTextfield.setLayout(flowLayout4);

    jScrollPane1.setPreferredSize(new Dimension(800, 380));

    jScrollPane1.getViewport().add(table, null);

    String[] columnNames = new String[] {

        //ut.ToUpperString(lang.getString("SeasonIDV")), ut.ToUpperString(lang.getString("SeasonNameV")), ut.ToUpperString(lang.getString("StartDateV")), ut.ToUpperString(lang.getString("EndDateV")), ut.ToUpperString(lang.getString("DescriptionV"))};

        lang.getString("SeasonID"),
        lang.getString("SeasonName"),
        lang.getString("StartDate"),
        lang.getString("EndDate"),
        lang.getString("Description")};

    dm.setDataVector(new Object[][] {}
                     , columnNames);

    table.setColumnWidth(0, 120);
    table.setColumnWidth(1, 120);
    table.setColumnWidth(2, 120);
    table.setColumnWidth(3, 120);
    table.setColumnWidth(4, 350);

    hostId = DAL.getHostID();
    nSeaID = initSeaId();
    showSeaID();
//    initDate();
    txtSeaName.requestFocus(true);
    txtStartDate.addFocusListener(new PanelSeasonSetup_txtStartDate_focusAdapter(this));
    txtEndDate.addFocusListener(new PanelSeasonSetup_txtEndDate_focusAdapter(this));
//    btnModify.setVisible(false);
  }


  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_SEASON_SETUP);
    btnAdd.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnModify.setEnabled(er.getModify());
    btnSearch.setEnabled(er.getSearch());

  }

    //initSeason Setup
        private int initSeaId(){
          int id = 10000;
          id = seaBusObj.getMaxSeaID(DAL,hostId);
          if (id == 0){
            id = Integer.parseInt(hostId);
            id = id*10000;
          }
            id = id + 1;
            return id;
        }
        public void showSeaID() {
          String sSeaID = String.valueOf(nSeaID).toString();
//          String sSeaID =  String.valueOf(nSeaID).toString();
          txtSeaID.setText(sSeaID);
        }
      //Set Date
        void initDate(){
            String today="";
            today = ut.getSystemDateTime();
            this.txtStartDate .setText(today.substring(0,10)) ;
            startDate = ut.getSystemDate(1);
            today = today.substring(0,2) + today.substring(3, 5) + today.substring(8,10)
                    +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);
            this.txtEndDate.setText("7/77/7777");
          }
        //reset Data
          public void resetData() {
           txtSeaName.setText("");
           txtDesc.setText("");
           txtStartDate.setText("");
           txtEndDate.setText("");
//           initDate();
//           setButton(true,false);
       }
       public void resetDataInScreen() {
//       txtSeaID.setText("");
        txtSeaName.setText("");
        txtDesc.setText("");
        txtStartDate.setText("");
        txtEndDate.setText("");

    }

       //set button
//       void setButton(boolean flag, boolean flag1) {
//         btnAdd.setVisible(flag);
//         btnDelete.setVisible(flag);
//         btnModify.setVisible(flag1);
//       }


  void btnDone_actionPerformed(ActionEvent e) {
    if (!txtSeaName.getText().equals("") && table.getRowCount()==0) {
            int i = ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS302_HelpSave"),
                               DialogMessage.QUESTION_MESSAGE,
                               DialogMessage.YES_NO_OPTION);
            if(i==DialogMessage.YES_VALUE){
              return;
            }
    }

    try {
      stmt = DAL.getConnection().createStatement();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    if (vSql.isEmpty() == false) {
        DAL.setBeginTrans(DAL.getConnection());
        DAL.executeBatchQuery(vSql,stmt);
        DAL.setEndTrans(DAL.getConnection());
    }
    while (dm.getRowCount() > 0) {
        dm.removeRow(0);
    }
//        vSql.removeAllElements();
        vSql.clear();
        vModify.clear();
        showButton(true);
        flagSetHotKeyForAdd = true;
         showSeaID();
        resetData();
        frmMain.showScreen(Constant.SCRS_MAIN_SIM);
        frmMain.pnlMainSim.showMerchanSystemButton();
        frmMain.setTitle(lang.getString("TT0002"));
        try {
          stmt.close();

        }
        catch (Exception ex) {
          ex.printStackTrace();
        }

  }
  private boolean checkExist(String seaName){
      ResultSet rs = null;
      String sqlStr = "Select SEASON_CDE From BTM_IM_SEASON Where season_cde='" + seaName + "'";
      try{
//        System.out.println(sqlStr);
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
    private boolean  checkExistOnGrid(String seaName){
       for(int i=0;i < dm.getRowCount();i++){
         if(dm.getValueAt(i,1).toString().equals(seaName.trim()))
           return true;
       }
       return false;
     }

  void btnAdd_actionPerformed(ActionEvent e) {
    java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
           "dd/MM/yyyy");
    String SeaID = ut.putCommaToString(txtSeaID.getText().trim());
    String seaName = txtSeaName.getText().trim();
    String Startdate = txtStartDate.getText().trim();
    String Enddate = txtEndDate.getText().trim();
    String Disc = ut.putCommaToString(txtDesc.getText().trim());
    //check data text field
    if (seaName.equals("")) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1025_SeasonNameNotNull"));
            txtSeaName.requestFocus(true);
            return;
    }
    if(checkExist(seaName)){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS322_SeasonNameExisted"));
      txtSeaName.requestFocus(true);
      return;
    }
    if (checkExistOnGrid(seaName)) {
      ut.showMessage(frmMain, lang.getString("TT0007"), lang.getString("MS322_SeasonNameExisted"));
      txtSeaName.requestFocus(true);
      return;
    }
    if (Disc.equals("") || Disc.equals(null))
      Disc = "";
    if (Enddate.equals("") || Enddate.equals(null))
      Enddate = "7/7/7777";

        if (!ut.checkDate(Startdate, "/") ||  Startdate.equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS323_StartDateMustDateType"));
      txtStartDate.requestFocus();
      txtStartDate.setSelectionStart(0);
      txtStartDate.setSelectionEnd(txtStartDate.getText().length());

      return;
    }
    if(!ut.checkDate(Enddate,"/") && ! Enddate.equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS324_EndDateMustDateType"));
      txtEndDate.requestFocus();
      txtEndDate.setSelectionStart(0);
      txtEndDate.setSelectionEnd(txtEndDate.getText().length());

      return;
    }
    if (txtEndDate.getText().equals("")){
      txtEndDate.setText("7/7/7777");
    }
    if (ut.compareDate(txtStartDate.getText(),txtEndDate.getText())){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS325_BeginEndDate"));
       txtStartDate.requestFocus();
       txtStartDate.setSelectionStart(0);
       txtStartDate.setSelectionEnd(txtStartDate.getText().length());
       return;
    }
//

     //insert table
     Object[] rowData = new Object[] {SeaID, seaName, Startdate, Enddate,Disc};

     vSql.addElement("insert into BTM_IM_SEASON(SEASON_ID, SEASON_CDE, START_DATE, END_DATE,SEASON_DESC)"
                     + "values('" + SeaID + "','" + seaName + "',to_date('" +
                     Startdate + "','dd/MM/yyyy'),to_date('" + Enddate +
                     "','dd/MM/yyyy'),'" + Disc + "')");
//     System.out.println(vSql);
     dm.addRow(rowData);
     Vector vData = new Vector();
     vData.addElement(new Long(SeaID));
     vData.addElement(seaName);
     vData.addElement(Startdate);
     vData.addElement(Enddate);
     vData.addElement(Disc);
     vModify.addElement(vData);

//     str.installInTable(table, Color.white, Color.black, Color.lightGray,Color.white);
     float[] f1 = new float[3];
     Color.RGBtoHSB(255,255,230,f1);
     str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

     //set focus for last row
      if(table.getRowCount()>0){
        table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
      }

      //show current row
      Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
      jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

     nSeaID++;
     showSeaID();
     resetData();
     txtSeaName.requestFocus(true);

  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if (table.getSelectedRow() == -1){
      return;
    }
    if (vSql.isEmpty()){
      return;
    }
    if (table.getRowCount()==1){
      dm.removeRow(0);
      vSql.removeAllElements();
      vModify.removeAllElements();
      resetDataInScreen();
      showButton(true);
      return;
    }
    int[] i = table.getSelectedRows();
    for (int j=0; j<i.length; j++){
      vSql.removeElementAt(i[0]);
      vModify.removeElementAt(i[0]);
      dm.removeRow(i[0]);
    }
    resetDataInScreen();
    showButton(true);
    flagSetHotKeyForAdd = true;
    txtSeaName.requestFocus(true);
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    DialogSeasonSearch dlgSeasonSearch = new DialogSeasonSearch(frmMain,lang.getString("TT0008"), true, frmMain,frmMain.seaBusObj);
       dlgSeasonSearch.setLocation(112,85);
       dlgSeasonSearch.setVisible(true);
       if (dlgSeasonSearch.done) {
         String seaID = dlgSeasonSearch.getData();
         frmMain.showSeasonModify(Long.parseLong(seaID));
         frmMain.showScreen(Constant.SCRS_SEASON_MODIFY);
       }
  }
  void btnClear_actionPerformed(ActionEvent e) {
    showButton(true);
    flagSetHotKeyForAdd = true;
     showSeaID();
    resetData();
    txtSeaName.requestFocus(true);
   }

  void btnCancel_actionPerformed(ActionEvent e) {
     showSeaID();
    resetData();
    vSql.removeAllElements();
    while (table.getRowCount() > 0) {
      dm.removeRow(0);
    }
    showButton(true);
    flagSetHotKeyForAdd = true;
    nSeaID = initSeaId();
    showSeaID();
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showMerchanSystemButton();
    frmMain.setTitle(lang.getString("TT0001"));
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
                   if (identifier.intValue() == KeyEvent.VK_F1) {
                     btnDone.doClick();
                   }
                   else if (identifier.intValue() == KeyEvent.VK_F2) {
                     if (flagSetHotKeyForAdd == true) {
                       btnAdd.doClick();
                     }
                   }

                   else if (identifier.intValue() == KeyEvent.VK_F3) {
                     btnSearch.doClick();
                   }
                   else if (identifier.intValue() == KeyEvent.VK_F4) {
                     if (flagSetHotKeyForAdd == false) {
                       btnModify.doClick();
                     }
                   }
                   else if (identifier.intValue() == KeyEvent.VK_F8) {
                     btnDelete.doClick();
                   }
                   else if (identifier.intValue() == KeyEvent.VK_F7) {
                     btnClear.doClick();
                   }

                   else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                                 btnCancel.doClick();
                         }
                 }
         }

  void jbtnStartdate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
      posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.jbtnStartdate.getX() + posXFrame + 90;
    posY = this.jBtnEnddate.getY() + posYFrame + 90;
    TimeDlg startdate = new TimeDlg(null);
    startdate.setTitle(lang.getString("ChooseStartDate"));
    startdate.setResizable(false);
    startdate.setLocation(posX, posY);
    startdate.setVisible(true);
    if (startdate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(startdate.getDate());
      this.txtStartDate.setText(date);
      startDate = this.txtStartDate.getText();
    }

  }

  void jBtnEnddate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.jbtnStartdate.getX() + posXFrame + 90;
    posY = this.jBtnEnddate.getY() + posYFrame + 90;
    TimeDlg enddate = new TimeDlg(null);
    enddate.setTitle(lang.getString("ChooseEndDate"));
//    enddate.setSize(300, 250);
    enddate.setResizable(false);
    enddate.setLocation(posX, posY);
    enddate.setVisible(true);
    if (enddate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
        "dd/MM/yyyy");
    String date = fd.format(enddate.getDate());
    this.txtEndDate.setText(date);
    endDate = this.txtEndDate.getText();
  }

  }

  void txtStartDate_focusLost(FocusEvent e) {
//    String Startdate = txtStartDate.getText().trim();
//    if (!ut.checkDate(Startdate, "/")){
//    ut.showMessage(frmMain,"Warning", "Input startdate must be a date type");
//    txtStartDate.setSelectionStart(0);
//    txtStartDate.setSelectionEnd(txtStartDate.getText().length());
//    txtStartDate.requestFocus();
//    return;
//  }

  }

  void txtEndDate_focusLost(FocusEvent e) {
//    String Enddate = txtEndDate.getText().trim();
//  if (!ut.checkDate(Enddate, "/")){
//  ut.showMessage(frmMain,"Warning", "Input Enddate must be a date type");
//  txtEndDate.setSelectionStart(0);
//  txtEndDate.setSelectionEnd(txtEndDate.getText().length());
//  txtEndDate.requestFocus();
//  return;
//  }
  }

  void table_mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2 ){
      Vector vData = null;
      this.vPosition = table.getSelectedRow();
      vData = (Vector)vModify.elementAt(this.vPosition);

      txtSeaID.setText(vData.elementAt(0).toString());
//      System.out.println(vData.elementAt(0).toString());
//      System.out.println(vData.elementAt(1).toString());
//      System.out.println(vData.elementAt(2).toString());
//      System.out.println(vData.elementAt(3).toString());
//      System.out.println(vData.elementAt(4).toString());
      txtSeaName.setText(vData.elementAt(1).toString());
      txtStartDate.setText(vData.elementAt(2).toString());
      txtEndDate.setText(vData.elementAt(3).toString());
      txtDesc.setText(vData.elementAt(4).toString());
//      setButton(false,true);
      showButton(false);//show Modify; hide Add
      flagSetHotKeyForAdd = false;
      txtSeaName.requestFocus(true);
    }

  }

  void btnModify_actionPerformed(ActionEvent e) {
    long seaID = Long.parseLong(txtSeaID.getText());

    String seaName = txtSeaName.getText().trim();
    String startDate = txtStartDate.getText().trim();
    String endDate = txtEndDate.getText().trim();
    String desc = txtDesc.getText().trim();
    if(txtSeaName.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1025_SeasonNameNotNull"));
      txtSeaName.requestFocus(true);
      return;
    }
    if (!ut.checkDate(startDate, "/")){
     ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS323_StartDateMustDateType"));
     txtStartDate.requestFocus();
     txtStartDate.setSelectionStart(0);
     txtStartDate.setSelectionEnd(txtStartDate.getText().length());

     return;
   }
   if(!ut.checkDate(endDate,"/")){
     ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS324_EndDateMustDateType"));
     txtEndDate.requestFocus();
     txtEndDate.setSelectionStart(0);
     txtEndDate.setSelectionEnd(txtEndDate.getText().length());

     return;
   }
   //check data text field
 if (seaName.equals("")) {
         ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1025_SeasonNameNotNull"));
         txtSeaName.requestFocus(true);
         return;
 }
 if(checkExist(seaName)){
   ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS322_SeasonNameExisted"));
   txtSeaName.requestFocus(true);
   return;
 }
 if (checkExistOnGrid(seaName)) {
   ut.showMessage(frmMain, lang.getString("TT0007"), lang.getString("MS322_SeasonNameExisted"));
   txtSeaName.requestFocus(true);
   return;
 }
 if (desc.equals("") || desc.equals(null))
   desc = "";
 if (endDate.equals("") || endDate.equals(null))
   endDate = "7/7/7777";

     if (!ut.checkDate(startDate, "/") ||  startDate.equals("")){
   ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS323_StartDateMustDateType"));
   txtStartDate.requestFocus();
   txtStartDate.setSelectionStart(0);
   txtStartDate.setSelectionEnd(txtStartDate.getText().length());

   return;
 }
 if(!ut.checkDate(endDate,"/") && ! endDate.equals("")){
   ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS324_EndDateMustDateType"));
   txtEndDate.requestFocus();
   txtEndDate.setSelectionStart(0);
   txtEndDate.setSelectionEnd(txtEndDate.getText().length());

   return;
 }
 if (txtEndDate.getText().equals("")){
   txtEndDate.setText("7/7/7777");
 }
 if (ut.compareDate(txtStartDate.getText(),txtEndDate.getText())){
   ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS325_BeginEndDate"));
    txtStartDate.requestFocus();
    txtStartDate.setSelectionStart(0);
    txtStartDate.setSelectionEnd(txtStartDate.getText().length());
    return;
 }

    vSql.setElementAt("insert into BTM_IM_SEASON(SEASON_ID, SEASON_CDE, START_DATE, END_DATE,SEASON_DESC)"
                      + "values('" + seaID + "','" + seaName + "',to_date('" +
                      startDate + "','dd/MM/yyyy'),to_date('" + endDate +
                      "','dd/MM/yyyy'),'" + desc + "')", this.vPosition);

//    System.out.println(vSql);
    Vector vData = new Vector();
    vData.addElement(new Long(seaID));
    vData.addElement(seaName);
    vData.addElement(startDate);
    vData.addElement(endDate);
    vData.addElement(desc);
    vModify.setElementAt(vData, this.vPosition);

    Object[] rowData = new Object[] { seaName, startDate, endDate,desc};
    table.setValueAt(new Long(seaID),this.vPosition,0);
    table.setValueAt(seaName,this.vPosition,1);
    table.setValueAt(startDate, this.vPosition, 2);
    table.setValueAt(endDate, this.vPosition, 3);
    table.setValueAt(desc, this.vPosition, 4);
    showButton(true); //show Add, hide Modify
    flagSetHotKeyForAdd = true;
     showSeaID();
    resetData();
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
    void navigationComp(KeyEvent e, JTextField txt){
      if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
        txt.requestFocus(true);
        txt.selectAll();
      }
    }

    void navigationComp(KeyEvent e, JTextArea txt) {
      if (e.getKeyChar() == KeyEvent.VK_ENTER ||
          e.getKeyChar() == KeyEvent.VK_TAB) {
        txt.requestFocus(true);
        txt.selectAll();
      }
    }


  void txtSeaName_keyTyped(KeyEvent e) {
    navigationComp(e,txtStartDate);
  }

  void txtEndDate_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      jBtnEnddate.requestFocus(true);
    }
  }

  void txtDesc_keyTyped(KeyEvent e) {
    if(e.getKeyChar()==KeyEvent.VK_ENTER){
      btnAdd.doClick();
    }
  }

  void jbtnStartdate_keyTyped(KeyEvent e) {
    navigationComp(e,txtEndDate);
  }

  void jBtnEnddate_keyTyped(KeyEvent e) {
    navigationComp(e,txtDesc);
  }

  void txtStartDate_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
      e.getKeyChar() == KeyEvent.VK_TAB) {
       jbtnStartdate.requestFocus(true);
  }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      if (flagSetHotKeyForAdd == true) {
             btnAdd.doClick();
           }
           else {
             btnModify.doClick();
           }

       }
  }
}

class PanelSeasonSetup_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_btnDone_actionAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}


class PanelSeasonSetup_btnClear_actionAdapter implements java.awt.event.ActionListener {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_btnClear_actionAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}


class PanelSeasonSetup_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_btnDelete_actionAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelSeasonSetup_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_btnSearch_actionAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelSeasonSetup_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_btnCancel_actionAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelSeasonSetup_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_btnAdd_actionAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelSeasonSetup_jbtnStartdate_actionAdapter implements java.awt.event.ActionListener {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_jbtnStartdate_actionAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jbtnStartdate_actionPerformed(e);
  }
}

class PanelSeasonSetup_jBtnEnddate_actionAdapter implements java.awt.event.ActionListener {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_jBtnEnddate_actionAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jBtnEnddate_actionPerformed(e);
  }
}

class PanelSeasonSetup_txtStartDate_focusAdapter extends java.awt.event.FocusAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_txtStartDate_focusAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtStartDate_focusLost(e);
  }
}

class PanelSeasonSetup_txtEndDate_focusAdapter extends java.awt.event.FocusAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_txtEndDate_focusAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtEndDate_focusLost(e);
  }
}

class PanelSeasonSetup_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_table_mouseAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelSeasonSetup_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_btnModify_actionAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelSeasonSetup_txtSeaName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_txtSeaName_keyAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSeaName_keyTyped(e);
  }
}

class PanelSeasonSetup_txtEndDate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_txtEndDate_keyAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEndDate_keyTyped(e);
  }
}

class PanelSeasonSetup_txtDesc_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_txtDesc_keyAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtDesc_keyTyped(e);
  }
}

class PanelSeasonSetup_jbtnStartdate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_jbtnStartdate_keyAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jbtnStartdate_keyTyped(e);
  }
}

class PanelSeasonSetup_jBtnEnddate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_jBtnEnddate_keyAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jBtnEnddate_keyTyped(e);
  }
}

class PanelSeasonSetup_txtStartDate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_txtStartDate_keyAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtStartDate_keyTyped(e);
  }
}

class PanelSeasonSetup_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSeasonSetup adaptee;

  PanelSeasonSetup_table_keyAdapter(PanelSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
