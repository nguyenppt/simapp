package sim;

import java.awt.*;
import javax.swing.JPanel;
import java.util.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;

import btm.swing.*;
import common.*;
import java.awt.event.*;
import btm.attr.*;
import btm.business.*;
import java.sql.Statement;

/**
 * <p>Title: </p>
 * <p>Description: MAIN -> SEASON SETUP -> SEARCH -> Double click on 1 Reason -> Open Season modify</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM Intersoft</p>
 * @author Loan Vo
 * @version 1.0
 */

public class PanelSeasonModify extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  DataAccessLayer DAL;
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;
  SeasonBusObj seaBusObj = new SeasonBusObj();
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague,
      ut.sCountry);
//   Vector vSql = new Vector();

  int nSeaID = 0;
  int lenCodeSeaId = 1;
  String startDate, endDate;
  int rowsNum = 5;
  Statement stmt = null;
  public boolean done = false;
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();

  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();

  BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
  //buttons
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
  SortableTableModel dm = new SortableTableModel() {
    public Class getColumnClass(int col) {
      switch (col) {
        case 0:
          return String.class;
        case 2:
          return java.util.Date.class;
        case 3:
          return java.util.Date.class;

        default:
          return Object.class;
      }
    }

  };

  BJTable table = new BJTable(dm, true) {
//                   public Class getColumnClass(int col) {
//                           switch (col) {
//                                   case 0:
//                                           return Long.class;
//                                   default:
//                                           return Object.class;
//                           }
//                   }

    public boolean isCellEditable(int row, int col) {
      return false;
    }
  };

  StripedTableCellRenderer str = new StripedTableCellRenderer(null,
      Color.lightGray, Color.white, null, null);

  JPanel pnlAlignLeft = new JPanel();
  JButton jbtnStartdate = new JButton();
  JButton jBtnEnddate = new JButton();
  JTextArea txtDesc = new JTextArea();

  public PanelSeasonModify(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public PanelSeasonModify() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setLayout(borderLayout1);

    this.setSize(new Dimension(800, 600));
    txtStartDate.setBackground(Color.white);
    txtStartDate.setEditable(true);
    txtStartDate.addKeyListener(new PanelSeasonModify_txtStartDate_keyAdapter(this));
    txtStartDate.addFocusListener(new
                                  PanelSeasonModify_txtStartDate_focusAdapter(this));
    this.setBackground(UIManager.getColor("Label.background"));
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    pnlAlignLeft.setMinimumSize(new Dimension(10, 50));
    pnlAlignLeft.setPreferredSize(new Dimension(30, 50));

    jbtnStartdate.setBounds(new Rectangle(259, 54, 29, 20));
    jbtnStartdate.setText("...");
    jbtnStartdate.addKeyListener(new PanelSeasonModify_jbtnStartdate_keyAdapter(this));
    jbtnStartdate.addActionListener(new
        PanelSeasonModify_jbtnStartdate_actionAdapter(this));
    jBtnEnddate.setText("...");
    jBtnEnddate.addKeyListener(new PanelSeasonModify_jBtnEnddate_keyAdapter(this));
    jBtnEnddate.addActionListener(new
                                  PanelSeasonModify_jBtnEnddate_actionAdapter(this));
    jBtnEnddate.setBounds(new Rectangle(259, 79, 29, 20));
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDesc.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDesc.setPreferredSize(new Dimension(200, 95));
    txtDesc.setText("");
    txtDesc.setRows(0);
    txtDesc.setWrapStyleWord(false);
    txtDesc.addKeyListener(new PanelSeasonModify_txtDesc_keyAdapter(this));
    txtEndDate.setBackground(Color.white);
    txtEndDate.addKeyListener(new PanelSeasonModify_txtEndDate_keyAdapter(this));
    txtEndDate.addFocusListener(new PanelSeasonModify_txtEndDate_focusAdapter(this));
    txtSeaID.setBackground(SystemColor.control);
    txtSeaID.setEditable(false);
    txtSeaID.addKeyListener(new PanelSeasonModify_txtSeaID_keyAdapter(this));
    btnCancel.addActionListener(new PanelSeasonModify_btnCancel_actionAdapter(this));
    btnModify.addActionListener(new PanelSeasonModify_btnModify_actionAdapter(this));
    txtSeaName.addKeyListener(new PanelSeasonModify_txtSeaName_keyAdapter(this));
    table.addKeyListener(new PanelSeasonModify_table_keyAdapter(this));
    this.add(pnlHeader, BorderLayout.NORTH);
    this.add(pnlAlignLeft, BorderLayout.WEST);
    this.add(pnlInfoLeft, BorderLayout.CENTER);
    this.add(pnlInfoRight, BorderLayout.EAST);
    this.add(jScrollPane1, BorderLayout.SOUTH);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    FlowLayout layout = new FlowLayout(FlowLayout.CENTER);

    //----------------Panel Header content all buttons------------------------------------//
    pnlHeader.setPreferredSize(new Dimension(850, 50));

    //Initial Buttons
    //Modify
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify") + " (F1)");
    btnModify.setText("<html><center><b>" + lang.getString("Modify") +
        " </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    //Cancel
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F2)");
    btnCancel.setText("<html><center><b>" + lang.getString("Cancel") +
        " </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");

    pnlHeader.add(btnModify, null);
    pnlHeader.add(btnCancel, null);

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
    lblSeasonID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,
                                          12));

    lblSeaName.setText(lang.getString("SeasonName") + ":");
    lblSeaName.setPreferredSize(new Dimension(120, 20));
    lblSeaName.setHorizontalAlignment(SwingConstants.LEFT);
    lblSeaName.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblSeaName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,
                                         12));

    lblStartDate.setText(lang.getString("StartDate") + ":");
    lblStartDate.setPreferredSize(new Dimension(120, 20));
    lblStartDate.setHorizontalAlignment(SwingConstants.LEFT);
    lblStartDate.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,
                                           12));

    lblEndDate.setText(lang.getString("EndDate") + ":");
    lblEndDate.setPreferredSize(new Dimension(120, 20));
    lblEndDate.setHorizontalAlignment(SwingConstants.LEFT);
    lblEndDate.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,
                                         12));

    pnlInfoLeftLabel.add(lblSeasonID, null);
    pnlInfoLeftLabel.add(lblSeaName, null);
    pnlInfoLeftLabel.add(lblStartDate, null);
    pnlInfoLeftLabel.add(lblEndDate, null);

    //-----Panel content Text Field-------------------------------------------------------//
    pnlInfoLeftTextfield.setDebugGraphicsOptions(0);
    pnlInfoLeftTextfield.setPreferredSize(new Dimension(240, 10));
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
    txtSeaName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,
                                         12));
    txtStartDate.setText("");
    txtStartDate.setSelectionStart(0);
    txtStartDate.setPreferredSize(new Dimension(245, 20));
    txtStartDate.setBounds(new Rectangle(10, 54, 245, 20));
    txtStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,
                                           12));
    txtEndDate.setText("");
    txtEndDate.setSelectionStart(0);
    txtEndDate.setPreferredSize(new Dimension(245, 20));
    txtEndDate.setBounds(new Rectangle(10, 79, 245, 20));
    txtEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,
                                         12));

    pnlInfoLeftTextfield.add(txtSeaID, null);
    pnlInfoLeftTextfield.add(txtSeaName, null);
    pnlInfoLeftTextfield.add(txtStartDate, null);
    pnlInfoLeftTextfield.add(txtEndDate, null);
    pnlInfoLeftTextfield.add(jBtnEnddate, null);
    pnlInfoLeftTextfield.add(jbtnStartdate, null);

    lblSeasonID.setDisplayedMnemonic('0');

    pnlInfoRight.setPreferredSize(new Dimension(350, 150));
    pnlInfoRight.setLayout(borderLayout3);

    pnlInfoRight.add(pnlInfoRightTextfield, BorderLayout.CENTER);
    pnlInfoRightTextfield.add(txtDesc, null);
    pnlInfoRight.add(pnlInfoRightLabel, BorderLayout.WEST);
    pnlInfoRightLabel.add(lblDesc, null);

    pnlInfoRightLabel.setDebugGraphicsOptions(0);
    pnlInfoRightLabel.setPreferredSize(new Dimension(120, 10));
    pnlInfoRightLabel.setLayout(flowLayout3);

    lblDesc.setText(lang.getString("Description") + ":");
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
    txtSeaName.requestFocus(true);

  }

//Show data
  void showData(long seaID) {
    try {
      stmt = DAL.getConnection().createStatement();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    ResultSet rs = null;
    java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
        "dd/MM/yyyy");
    try {
      String sql = "select season_id, season_cde,start_date, end_date, season_desc from BTM_IM_SEASON where season_id =" +
          seaID;
//      System.out.println(sql);
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        txtSeaID.setText("" + rs.getLong("season_id"));

        txtSeaName.setText(rs.getString("season_cde").trim());
        txtStartDate.setText(fd.format(rs.getDate("Start_date")));
        txtEndDate.setText(fd.format(rs.getDate("End_date")));
        String desc = rs.getString("season_desc");
        if (desc == null)
          desc = "";
        txtDesc.setText(desc);
      }
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    try {
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    txtSeaName.requestFocus(true);
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    DialogSeasonSearch dlgSeasonSearch = new DialogSeasonSearch(
        frmMain, lang.getString("TT0008"), true, frmMain,
        frmMain.seaBusObj);
    dlgSeasonSearch.setText(frmMain.seaBusObj.getseaID(),
                            frmMain.seaBusObj.getseaName(),
                            frmMain.seaBusObj.getstartDate(),
                            frmMain.seaBusObj.getendDate(),
                            frmMain.seaBusObj.getDesc());
    dlgSeasonSearch.searchData(frmMain.pnlSeasonSetup.rowNum);
    dlgSeasonSearch.txtRowLimit.setText("" + frmMain.pnlSeasonSetup.rowNum);
    dlgSeasonSearch.setLocation(112, 85);
    dlgSeasonSearch.setVisible(true);
    if (dlgSeasonSearch.done == false) {
      frmMain.showScreen(Constant.SCRS_SEASON_SETUP);
    }
    else {
      showData(Long.parseLong(dlgSeasonSearch.getData()));
    }

  }

  void navigationComp(KeyEvent e, JTextField txt) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
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

  private boolean checkExist(String seaId, String seaName) {
    ResultSet rs = null;
    String sqlStr = "Select SEASON_CDE From BTM_IM_SEASON Where season_cde='" +
        seaName + "' and season_id <> " + seaId;
    try {
//          System.out.println(sqlStr);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr, stmt);
      if (rs.next()) {
        stmt.close();
        return true;
      }
      stmt.close();
    }
    catch (Exception ex) {}
    return false;
  }

  void btnModify_actionPerformed(ActionEvent e) {
    long seaID = 0;

    java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
    String SeaID = ut.putCommaToString(txtSeaID.getText().trim());
    String seaName = txtSeaName.getText().trim();
    String Startdate = txtStartDate.getText().trim();
    String Enddate = txtEndDate.getText().trim();
    String Disc = ut.putCommaToString(txtDesc.getText().trim());
    //check data text field
    if (seaName.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1025_SeasonNameNotNull"));
      txtSeaName.requestFocus(true);
      return;
    }
    if (checkExist(SeaID, seaName)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS322_SeasonNameExisted"));
      txtSeaName.requestFocus(true);
      return;
    }
    if (Disc.equals("") || Disc.equals(null))
      Disc = "";
    if (Enddate.equals("") || Enddate.equals(null))
      Enddate = "7/7/7777";

    if (!ut.checkDate(Startdate, "/") || Startdate.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS323_StartDateMustDateType"));
      txtStartDate.requestFocus();
      txtStartDate.setSelectionStart(0);
      txtStartDate.setSelectionEnd(txtStartDate.getText().length());

      return;
    }
    if (!ut.checkDate(Enddate, "/") && !Enddate.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS324_EndDateMustDateType"));
      txtEndDate.requestFocus();
      txtEndDate.setSelectionStart(0);
      txtEndDate.setSelectionEnd(txtEndDate.getText().length());

      return;
    }
    if (txtEndDate.getText().equals("")) {
      txtEndDate.setText("7/7/7777");
    }
    if (ut.compareDate(txtStartDate.getText(), txtEndDate.getText())) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS325_BeginEndDate"));
      txtStartDate.requestFocus();
      txtStartDate.setSelectionStart(0);
      txtStartDate.setSelectionEnd(txtStartDate.getText().length());
      return;
    }
    DialogSeasonSearch dlgSeasonSearch = new DialogSeasonSearch(
        frmMain, lang.getString("TT0008"), true, frmMain,
        frmMain.seaBusObj);
    dlgSeasonSearch.updateSeason(txtSeaID.getText(),
                                 ut.putCommaToString(txtSeaName.getText()),
                                 Startdate, Enddate,
                                 ut.putCommaToString(txtDesc.getText()));
//Hien thi dialog search
    dlgSeasonSearch.setText(frmMain.seaBusObj.getseaID().toString(),
                            frmMain.seaBusObj.getseaName().toString(),
                            frmMain.seaBusObj.getstartDate().toString(),
                            frmMain.seaBusObj.getendDate().toString(),
                            frmMain.seaBusObj.getDesc().toString());
    dlgSeasonSearch.searchData(frmMain.pnlSeasonSetup.rowNum);
    dlgSeasonSearch.txtRowLimit.setText("" + frmMain.pnlSeasonSetup.rowNum);
    dlgSeasonSearch.setLocation(112, 85);
    dlgSeasonSearch.setVisible(true);
    if (dlgSeasonSearch.done == false) {
      frmMain.showScreen(Constant.SCRS_SEASON_SETUP);
    }
    else {
      seaID = Long.parseLong(dlgSeasonSearch.getData());
      showData(seaID);
    }
  }

  void txtSeaID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtSeaID, 10);
    ut.checkNumber(e);
  }

  void txtSeaName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtSeaName, 120);
    navigationComp(e, txtStartDate);
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
      txtEndDate.requestFocus(true);
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
    enddate.setResizable(false);
    enddate.setLocation(posX, posY);
    enddate.setVisible(true);
    if (enddate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(enddate.getDate());
      this.txtEndDate.setText(date);
      txtDesc.requestFocus(true);
      endDate = this.txtEndDate.getText();
    }

  }

  void txtDesc_keyTyped(KeyEvent e) {
    ut.limitLenjTextArea(e, txtDesc, 120);
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      btnModify.doClick();
    }
  }

  void txtStartDate_focusLost(FocusEvent e) {
//    String Startdate = txtStartDate.getText().trim();
//    if (!ut.checkDate(Startdate, "/")) {
//      ut.showMessage(frmMain, "Warning", "Input startdate must be a date type");
//      txtStartDate.setSelectionStart(0);
//      txtStartDate.setSelectionEnd(txtStartDate.getText().length());
//      txtStartDate.requestFocus();
//      return;
//    }
  }

  void txtEndDate_focusLost(FocusEvent e) {
//    String Enddate = txtEndDate.getText().trim();
//    if (!ut.checkDate(Enddate, "/")) {
//      ut.showMessage(frmMain, "Warning", "Input Enddate must be a date type");
//      txtEndDate.setSelectionStart(0);
//      txtEndDate.setSelectionEnd(txtEndDate.getText().length());
//      txtEndDate.requestFocus();
//      return;
//    }

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

    // ENTER
    key = new Integer(KeyEvent.VK_ENTER);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
    actionMap.put(key, new KeyAction(key));
    // ESCAPE
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
        btnModify.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void txtStartDate_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      jbtnStartdate.requestFocus(true);
    }
  }

  void jbtnStartdate_keyTyped(KeyEvent e) {

  }

  void txtEndDate_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      jBtnEnddate.requestFocus(true);
    }
  }

  void jBtnEnddate_keyTyped(KeyEvent e) {

  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      btnModify.doClick();
    }
  }

  class PanelSeasonModify_btnCancel_actionAdapter
      implements java.awt.event.ActionListener {
    PanelSeasonModify adaptee;

    PanelSeasonModify_btnCancel_actionAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.btnCancel_actionPerformed(e);
    }
  }

  class PanelSeasonModify_btnModify_actionAdapter
      implements java.awt.event.ActionListener {
    PanelSeasonModify adaptee;

    PanelSeasonModify_btnModify_actionAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.btnModify_actionPerformed(e);
    }
  }

  class PanelSeasonModify_txtSeaID_keyAdapter
      extends java.awt.event.KeyAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_txtSeaID_keyAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent e) {
      adaptee.txtSeaID_keyTyped(e);
    }
  }

  class PanelSeasonModify_txtSeaName_keyAdapter
      extends java.awt.event.KeyAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_txtSeaName_keyAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent e) {
      adaptee.txtSeaName_keyTyped(e);
    }
  }

  class PanelSeasonModify_jbtnStartdate_actionAdapter
      implements java.awt.event.ActionListener {
    PanelSeasonModify adaptee;

    PanelSeasonModify_jbtnStartdate_actionAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.jbtnStartdate_actionPerformed(e);
    }
  }

  class PanelSeasonModify_jBtnEnddate_actionAdapter
      implements java.awt.event.ActionListener {
    PanelSeasonModify adaptee;

    PanelSeasonModify_jBtnEnddate_actionAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.jBtnEnddate_actionPerformed(e);
    }
  }

  class PanelSeasonModify_txtDesc_keyAdapter
      extends java.awt.event.KeyAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_txtDesc_keyAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent e) {
      adaptee.txtDesc_keyTyped(e);
    }
  }

  class PanelSeasonModify_txtStartDate_focusAdapter
      extends java.awt.event.FocusAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_txtStartDate_focusAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void focusLost(FocusEvent e) {
      adaptee.txtStartDate_focusLost(e);
    }
  }

  class PanelSeasonModify_txtEndDate_focusAdapter
      extends java.awt.event.FocusAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_txtEndDate_focusAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void focusLost(FocusEvent e) {
      adaptee.txtEndDate_focusLost(e);
    }
  }

  class PanelSeasonModify_txtStartDate_keyAdapter
      extends java.awt.event.KeyAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_txtStartDate_keyAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent e) {
      adaptee.txtStartDate_keyTyped(e);
    }
  }

  class PanelSeasonModify_jbtnStartdate_keyAdapter
      extends java.awt.event.KeyAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_jbtnStartdate_keyAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent e) {
      adaptee.jbtnStartdate_keyTyped(e);
    }
  }

  class PanelSeasonModify_txtEndDate_keyAdapter
      extends java.awt.event.KeyAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_txtEndDate_keyAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent e) {
      adaptee.txtEndDate_keyTyped(e);
    }
  }

  class PanelSeasonModify_jBtnEnddate_keyAdapter
      extends java.awt.event.KeyAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_jBtnEnddate_keyAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent e) {
      adaptee.jBtnEnddate_keyTyped(e);
    }
  }

  class PanelSeasonModify_table_keyAdapter
      extends java.awt.event.KeyAdapter {
    PanelSeasonModify adaptee;

    PanelSeasonModify_table_keyAdapter(PanelSeasonModify adaptee) {
      this.adaptee = adaptee;
    }

    public void keyPressed(KeyEvent e) {
      adaptee.table_keyPressed(e);
    }
  }
  }