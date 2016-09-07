package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: Main -> Merch Mgmt -> Promotion -> Search</p>
 *
 * <p>Description: Main -> Merch Mgmt -> Mixmath Promo -> Search</p>
 *
 * <p>Description: Main -> Merch Mgmt -> ThresHold Promo -> Search</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author Vo Ha Thanh Huy
 * @version 1.0
 */
public class PanelPromoChildSearch
    extends JPanel {
  //SCREEN FLAG
  // 0 : No SCREEN
  // 1 : PanelPromotionMixMatch
  // 2 : PanelPromoThresholdSetup
  // 3 : PanelMainSim - Search
  // 4 : PanelPromotionMixMatchModify
  // 5 : PanelPromoThresholdModify
  public int SCREEN_FLAG = 0;
  //Constat
  public static int COLUMN_NUMBER = 6;
  public static int COLUMN_CHECK = 0;
  public static int COLUMN_ID = 1;
  public static int COLUMN_DESCRIPTION = 2;
  public static int COLUMN_TYPE = 3;
  public static int COLUMN_START_DATE = 4;
  public static int COLUMN_END_DATE = 5;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  //Variable
  BJCheckBox check = new BJCheckBox();
  Statement stmt = null;
  int rowsNum = 15;
  Utils ut = new Utils();
  MixMatchBusObj mm = new MixMatchBusObj();
  //My Variable
  BorderLayout borderLayout1 = new BorderLayout();
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  DataAccessLayer DAL = new DataAccessLayer();
  JPanel jPanel3 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm, true);

  GridLayout gridLayout1 = new GridLayout();
  Border border1 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border2 = new TitledBorder(border1, "Mix Math Info");
  Border border3 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border4 = new TitledBorder(border3, "Mix Math Info");
  Border border5 = BorderFactory.createCompoundBorder(border4, border1);
  BJButton btnCancel = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnDone = new BJButton();
  BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JTextField txtStartDate = new JTextField();
  JTextField txtEndDate = new JTextField();
  BJPanel pnlInfo = new BJPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  JLabel lblEndDate = new JLabel();//("End Date:");
  BorderLayout borderLayout4 = new BorderLayout();
  JButton btnStartDate = new JButton();
  BJPanel pnlInfoLeftLabel = new BJPanel();
  JLabel lblStartDate = new JLabel();//("StartDate:");
  JButton btnEndDate = new JButton();
  JLabel lblPromoName = new JLabel();//("Promotion Name:");
  BJPanel pnlInfoLeft = new BJPanel();
  JLabel lblPromoID = new JLabel();//("Promotion ID:");
  FlowLayout flowLayout1 = new FlowLayout();
  JTextField txtPromoName = new JTextField();
  BorderLayout borderLayout2 = new BorderLayout();
  BJPanel pnlInfoRightLabel = new BJPanel();
  BJPanel pnlInfoLeftText = new BJPanel();
  BJPanel pnlInfoRight = new BJPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JTextField txtPromoID = new JTextField();
  FlowLayout flowLayout3 = new FlowLayout();
  JLabel lblRowLimit = new JLabel();//("Row Limit:");
  BJPanel pnlInfoRightText = new BJPanel();
  FlowLayout flowLayout4 = new FlowLayout();
  JTextField txtRowLimit = new JTextField();
  JLabel lblDesc = new JLabel();//("Row Limit:");
  JTextField txtDesc = new JTextField();
  JLabel lblStartDate1 = new JLabel();//("Start Date:");
  BJComboBox cboType = new BJComboBox();
  BJButton btnDelete = new BJButton();

  public PanelPromoChildSearch() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public PanelPromoChildSearch(FrameMainSim frmMain, CardLayout crdLayout,
                               JPanel parent) {
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

  private void jbInit() throws Exception {
    border5 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,
        new Color(148, 145, 140)), lang.getString("MixMatchInfo"));
    this.setLayout(borderLayout1);
    jScrollPane1.setPreferredSize(new Dimension(800, 240));
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    jPanel3.setLayout(gridLayout1);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(130, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+"(F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                      "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setPreferredSize(new Dimension(130, 37));
    btnSearch.setToolTipText(lang.getString("Search")+"(F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                      "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F3</html>");
    btnSearch.addActionListener(new
                                PanelPromoChildSearch_btnSearch_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(130, 37));
    btnDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelPromoChildSearch_btnDone_actionAdapter(this));
    pnlHeader.setPreferredSize(new Dimension(800, 47));
    txtStartDate.setEnabled(true);
    txtStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtStartDate.setPreferredSize(new Dimension(140, 20));
    txtStartDate.setEditable(true);
    txtStartDate.addKeyListener(new PanelPromoChildSearch_txtStartDate_keyAdapter(this));
    txtEndDate.setBackground(Color.white);
    txtEndDate.setEnabled(true);
    txtEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtEndDate.setPreferredSize(new Dimension(140, 20));
    txtEndDate.addKeyListener(new PanelPromoChildSearch_txtEndDate_keyAdapter(this));
    pnlInfo.setPreferredSize(new Dimension(800, 100));
    pnlInfo.setLayout(borderLayout3);
    flowLayout2.setAlignment(FlowLayout.LEFT);

    lblEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblEndDate.setText(lang.getString("EndDate")+":");

    lblStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStartDate.setText(lang.getString("StartDate")+":");

    lblPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPromoName.setText(lang.getString("PromoName")+":");

    lblPromoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPromoID.setText(lang.getString("PromoID")+":");

    lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblRowLimit.setText(lang.getString("RowsLimit")+":");

    lblEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblEndDate.setPreferredSize(new Dimension(100, 20));
    btnStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnStartDate.setPreferredSize(new Dimension(30, 20));
    btnStartDate.setText("...");
    btnStartDate.addActionListener(new
        PanelPromoChildSearch_btnStartDate_actionAdapter(this));
    pnlInfoLeftLabel.setPreferredSize(new Dimension(180, 30));
    pnlInfoLeftLabel.setLayout(flowLayout1);
    lblStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStartDate.setPreferredSize(new Dimension(100, 20));
    btnEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnEndDate.setPreferredSize(new Dimension(30, 20));
    btnEndDate.setText("...");
    btnEndDate.addActionListener(new
                                 PanelPromoChildSearch_btnEndDate_actionAdapter(this));
    lblPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPromoName.setPreferredSize(new Dimension(100, 20));
    lblPromoName.setHorizontalAlignment(SwingConstants.LEFT);
    pnlInfoLeft.setPreferredSize(new Dimension(400, 30));
    pnlInfoLeft.setLayout(borderLayout2);
    lblPromoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPromoID.setPreferredSize(new Dimension(100, 20));
    lblPromoID.setHorizontalAlignment(SwingConstants.LEFT);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    txtPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPromoName.setPreferredSize(new Dimension(140, 20));
    txtPromoName.setRequestFocusEnabled(true);
    txtPromoName.setHorizontalAlignment(SwingConstants.LEADING);
    txtPromoName.addKeyListener(new
                                PanelPromoChildSearch_txtPromoName_keyAdapter(this));
    pnlInfoRightLabel.setPreferredSize(new Dimension(130, 30));
    pnlInfoRightLabel.setLayout(flowLayout3);
    pnlInfoLeftText.setPreferredSize(new Dimension(1, 29));
    pnlInfoLeftText.setLayout(flowLayout2);
    pnlInfoRight.setPreferredSize(new Dimension(400, 100));
    pnlInfoRight.setLayout(borderLayout4);
    txtPromoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPromoID.setPreferredSize(new Dimension(140, 20));
    txtPromoID.setHorizontalAlignment(SwingConstants.LEADING);
    txtPromoID.addKeyListener(new PanelPromoChildSearch_txtPromoID_keyAdapter(this));
    txtPromoID.setRequestFocusEnabled(true);
    flowLayout3.setAlignment(FlowLayout.RIGHT);
    lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblRowLimit.setPreferredSize(new Dimension(100, 20));
    pnlInfoRightText.setPreferredSize(new Dimension(200, 100));
    pnlInfoRightText.setLayout(flowLayout4);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtRowLimit.setPreferredSize(new Dimension(80, 20));
    txtRowLimit.setText(DAL.getSearchLimit());
    txtRowLimit.setHorizontalAlignment(SwingConstants.LEADING);
    txtRowLimit.addKeyListener(new PanelPromoChildSearch_txtRowLimit_keyAdapter(this));
    lblDesc.setPreferredSize(new Dimension(100, 20));
    lblDesc.setText(lang.getString("Description")+":");
    lblDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDesc.setHorizontalAlignment(SwingConstants.LEADING);
    txtDesc.addKeyListener(new PanelPromoChildSearch_txtDesc_keyAdapter(this));
    txtDesc.setRequestFocusEnabled(true);
    txtDesc.setPreferredSize(new Dimension(140, 20));
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStartDate1.setPreferredSize(new Dimension(100, 20));
    lblStartDate1.setText(lang.getString("Type")+":");
    lblStartDate1.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboType.setEnabled(true);
    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboType.setMinimumSize(new Dimension(26, 21));
    cboType.setOpaque(true);
    cboType.setPreferredSize(new Dimension(178, 20));
    jPanel3.setPreferredSize(new Dimension(800, 395));
    table.addMouseListener(new PanelPromoChildSearch_table_mouseAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setToolTipText(lang.getString("Delete")+"(F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8<" +
                      "/html>");
    btnDelete.addActionListener(new
                                PanelPromoChildSearch_btnDelete_actionAdapter(this));
    this.add(jPanel3, java.awt.BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1);
    this.add(pnlHeader, BorderLayout.NORTH);
    pnlHeader.add(btnDone, null);
    pnlHeader.add(btnSearch, null);
    pnlHeader.add(btnDelete, null);
    pnlHeader.add(btnCancel, null);
    this.add(pnlInfo, BorderLayout.CENTER);
    pnlInfoLeftLabel.add(lblPromoID, null);
    pnlInfoLeftLabel.add(lblPromoName, null);
    pnlInfoLeftLabel.add(lblDesc, null);
    pnlInfoLeftLabel.add(lblRowLimit, null);
    pnlInfoLeft.add(pnlInfoLeftText, java.awt.BorderLayout.CENTER);
    pnlInfoLeft.add(pnlInfoLeftLabel, java.awt.BorderLayout.WEST);
    pnlInfoLeftText.add(txtPromoID, null);
    pnlInfoLeftText.add(txtPromoName, null);
    pnlInfoLeftText.add(txtDesc, null);
    pnlInfoLeftText.add(txtRowLimit, null);
    pnlInfo.add(pnlInfoRight, BorderLayout.CENTER);
    pnlInfo.add(pnlInfoLeft, java.awt.BorderLayout.WEST);
    pnlInfoRightLabel.add(lblStartDate, null);
    pnlInfoRightLabel.add(lblEndDate, null);
    pnlInfoRight.add(pnlInfoRightText, java.awt.BorderLayout.CENTER);
    pnlInfoRight.add(pnlInfoRightLabel, java.awt.BorderLayout.WEST);
    pnlInfoRightText.add(txtStartDate, null);
    pnlInfoRightText.add(btnStartDate, null);
    pnlInfoRightText.add(txtEndDate, null);
    pnlInfoRightText.add(btnEndDate, null);
    pnlInfoRightText.add(cboType, null);
    jScrollPane1.getViewport().add(table);
    pnlInfoRightLabel.add(lblStartDate1, null); //Init Table

    //Add Listener
    btnCancel.addActionListener(new
                                PanelPromoChildSearch_btnCancel_actionAdapter(this));

    cboType.removeAllItems();
    txtRowLimit.setText(DAL.getSearchLimit());

    //Info
    cboType.addItem("All");
    cboType.addItem("Mix Match");
    cboType.addItem("Threshold");

    //regist Hot Key
    registerKeyboardActions();
  }

  public void setLayout() {
    if (SCREEN_FLAG == 1) {
      cboType.setSelectedItem("Mix Match");
      cboType.setEnabled(false);
    }
    else if (SCREEN_FLAG == 2) {
      cboType.setSelectedItem("Threshold");
      cboType.setEnabled(false);
    }
  }

  public void btnCancel_actionPerformed(ActionEvent e) {
    //Clear Panel Search
    txtDesc.setText("");
    txtPromoID.setText("");
    txtPromoName.setText("");
    txtRowLimit.setText(DAL.getSearchLimit());
    txtEndDate.setText("");
    txtStartDate.setText("");

    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }

    //Update
    if (SCREEN_FLAG == 1) {
      SCREEN_FLAG = 0;
      frmMain.showScreen(Constant.SCRS_PROMOTION_MIX_MATCH);
//      frmMain.pnlPromotionMixMatch.initScreen();
    }
    else if (SCREEN_FLAG == 2) {
      SCREEN_FLAG = 0;
      frmMain.showScreen(Constant.SCRS_PROMOTION_THRES_HOLD);
//      frmMain.pnlPromoThresholdSetup.initScreen();
    }
    else if (SCREEN_FLAG == 3) {
      SCREEN_FLAG = 0;
      frmMain.showScreen(Constant.SCRS_MAIN_SIM);
      frmMain.pnlMainSim.showPromotionButton();
      frmMain.pnlMainSim.frmMain.setTitle(lang.getString("TT0108"));
    }
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    if (!ut.checkDate(txtStartDate.getText().trim(), "/") &&
        !txtStartDate.getText().trim().equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1155_StartdateDateType"));
      txtStartDate.requestFocus();
      txtStartDate.setSelectionStart(0);
      txtStartDate.setSelectionEnd(txtStartDate.getText().length());
      return;
    }
    if (!ut.checkDate(txtEndDate.getText().trim(), "/") &&
        !txtEndDate.getText().trim().equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1156_EnddateDateType"));
      txtEndDate.requestFocus();
      txtEndDate.setSelectionStart(0);
      txtEndDate.setSelectionEnd(txtEndDate.getText().length());
      return;
    }
    if (txtStartDate.getText().trim().length() > 0 &&
        txtEndDate.getText().trim().length() > 0) {
      if (ut.compareDate(txtStartDate.getText(), txtEndDate.getText())) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1153_StartDateBeforeEndDate"));
        return;
      }
    }
    if (txtRowLimit.getText().equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1015_InputLimitedNumberSearchingRecords"));
      return;
    }
    rowsNum = Integer.parseInt(txtRowLimit.getText());
//    if (rowsNum > 50) {
//      ut.showMessage(frmMain, lang.getString("TT001"),
//          "The number of rows is too long! It must be less than or equal 50");
//      return;
//    }
    searchData(rowsNum);
  }

  ResultSet getNullRow(Statement stmt) {
    ResultSet rs = null;
    String sql =
        "select to_char(MIX_MATCH_NO) ID,MIC_MATCH_DESC Description,MIC_MATCH_DESC Type,  to_char(ph.Start_Date,'DD/MM/YYYY') Start_Date, to_char(ph.End_Date,'DD/MM/YYYY') End_Date  from BTM_IM_MIX_MATCH_HEAD mm,BTM_IM_PROMO_HEAD ph  where ph.PROMO_ID = mm.PROMO_ID and mm.Status = '2' and ph.Status <> 'D' and rownum <= 15 Order by MIX_MATCH_NO desc";
    try {
      rs = DAL.executeScrollQueries(sql, stmt);

    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  // Search Data
  void searchData(int rowsNum) {
    dm.resetIndexes();
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = getNullRow(stmt);
      table.setData(rs,
                    check, COLUMN_ID);
      if (cboType.getSelectedItem().equals("All")) {

      }
      else if (cboType.getSelectedItem().equals("Mix Match")) {
        rs = getData(txtPromoID.getText(), txtPromoName.getText(),
                     txtStartDate.getText()
                     , txtEndDate.getText(), txtDesc.getText(),
                     cboType.getSelectedItem().toString(), rowsNum, stmt);
        while (rs.next()) {
          Object obj[] = new Object[COLUMN_NUMBER];

          check = new BJCheckBox();
          check.setValue(rs.getString("ID"));
          obj[COLUMN_CHECK] = check;
          obj[COLUMN_ID] = rs.getString("ID");
          obj[COLUMN_DESCRIPTION] = rs.getString("Description");
          obj[COLUMN_TYPE] = rs.getString("MM_Type");
          obj[COLUMN_START_DATE] = rs.getString("Start_Date");
          obj[COLUMN_END_DATE] = rs.getString("End_Date");

          dm.addRow(obj);
        }
      }
      else if (cboType.getSelectedItem().equals("Threshold")) {
        rs = getData(txtPromoID.getText(), txtPromoName.getText(),
                     txtStartDate.getText()
                     , txtEndDate.getText(), txtDesc.getText(),
                     cboType.getSelectedItem().toString(), rowsNum, stmt);
        while (rs.next()) {
          Object obj[] = new Object[COLUMN_NUMBER];

          check = new BJCheckBox();
          check.setValue(rs.getString("ID"));
          obj[COLUMN_CHECK] = check;
          obj[COLUMN_ID] = rs.getString("ID");
          obj[COLUMN_DESCRIPTION] = rs.getString("Description");
          obj[COLUMN_TYPE] = rs.getString("Threshold_Type");
          obj[COLUMN_START_DATE] = rs.getString("Start_Date");
          obj[COLUMN_END_DATE] = rs.getString("End_Date");

          dm.addRow(obj);
        }
      }

      stmt.close();
    }
    catch (Exception ex) {}
    ;
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    table.setHeaderName(lang.getString("ID"), COLUMN_ID);
    table.setHeaderName(lang.getString("Description"), COLUMN_DESCRIPTION);
    table.setHeaderName(lang.getString("Type"), COLUMN_TYPE);
    table.setHeaderName(lang.getString("StartDate"), COLUMN_START_DATE);
    table.setHeaderName(lang.getString("EndDate"), COLUMN_END_DATE);
    table.getColumn(table.getColumnName(0)).setHeaderValue("");

    table.getColumn(lang.getString("ID")).setPreferredWidth(120);
    table.getColumn(lang.getString("Description")).setPreferredWidth(300);
    table.getColumn(lang.getString("Type")).setPreferredWidth(150);
    table.getColumn(lang.getString("StartDate")).setPreferredWidth(100);
    table.getColumn(lang.getString("EndDate")).setPreferredWidth(100);

    table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));

  }

  private ResultSet getData(String promoID, String promoName, String startDate,
                            String endDate, String Desc, String Type, int rows,
                            Statement stmt) {
    ResultSet rs = null;
    try {
      if (Type.equals("All")) {
        //Search all in here
      }
      else if (Type.equals("Mix Match")) {
        String sqlStr =
            "select to_char(MIX_MATCH_NO) ID,MIC_MATCH_DESC Description,'Mix Match' MM_Type, "
            + " to_char(ph.Start_Date,'DD/MM/YYYY') Start_Date, to_char(ph.End_Date,'DD/MM/YYYY') End_Date "
            + " from BTM_IM_MIX_MATCH_HEAD mm,BTM_IM_PROMO_HEAD ph "
            + " where ph.PROMO_ID = mm.PROMO_ID and mm.Status <> '0' and ph.Status <> 'D' and rownum <= " +
            rows;
        String sql = "";
        if (promoID.trim().length() > 0) {
          sql += " and lower(ph.Promo_Id) like lower('%" + promoID + "%')";
        }

        if (promoName.trim().length() > 0) {
          sql += " and lower(ph.Promo_Name) like lower('%" + promoName + "%')";
        }

        if (!startDate.trim().equals("")) {
          sql += " and ph.Start_Date >= to_date('" + startDate +
              "','DD/MM/YYYY')";
        }

        if (!endDate.trim().equals("")) {
          sql += " and ph.End_Date <= to_date('" + endDate + "','DD/MM/YYYY')";
        }

        if (!Desc.trim().equals("")) {
          sql += " and lower(mm.MIC_MATCH_DESC) like lower('%" + Desc + "%')";
        }

        sqlStr += sql + " Order by MIX_MATCH_NO desc";
//        System.out.println(sqlStr);
        rs = DAL.executeScrollQueries(sqlStr, stmt);
      }
      else if (Type.equals("Threshold")) {
        //Search threshold in here
        String sqlStr =
            sqlStr = " select to_char(THRESHOLD_NO) ID,THRESHOLD_DESC Description,'Threshold' Threshold_Type,  "
            + " to_char(ph.Start_Date,'DD/MM/YYYY') Start_Date, to_char(ph.End_Date,'DD/MM/YYYY') End_Date  "
            + " from BTM_IM_PROMO_THRESHOLD_HEAD thrhld,BTM_IM_PROMO_HEAD ph  "
            + " where ph.PROMO_ID = thrhld.PROMO_ID and thrhld.Status <> '0' and ph.Status <> 'D' and rownum <= "
            + rows;
        String sql = "";
        if (promoID.trim().length() > 0) {
          sql += " and lower(ph.Promo_Id) like lower('%" + promoID + "%')";
        }

        if (promoName.trim().length() > 0) {
          sql += " and lower(ph.Promo_Name) like lower('%" + promoName + "%')";
        }

        if (!startDate.trim().equals("")) {
          sql += " and ph.Start_Date >= to_date('" + startDate +
              "','DD/MM/YYYY')";
        }

        if (!endDate.trim().equals("")) {
          sql += " and ph.End_Date <= to_date('" + endDate + "','DD/MM/YYYY')";
        }

        if (!Desc.trim().equals("")) {
          sql += " and lower(thrhld.THRESHOLD_DESC) like lower('%" + Desc +
              "%')";
        }

        sqlStr += sql + " Order by THRESHOLD_NO desc";
//        System.out.println(sqlStr);
        rs = DAL.executeScrollQueries(sqlStr, stmt);
      }
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return rs;
  }

  void btnDone_actionPerformed(ActionEvent e) {
    if (SCREEN_FLAG == 1) {
      if (table.getSelectedRow() == -1) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1154_SelectRowInTable"));
        return;
      }
      else {
        frmMain.showScreen(Constant.SCRS_PROMOTION_MIX_MATCH_MODIFY);
        frmMain.pnlPromotionMixMatchModify.showData(table.getValueAt(table.
            getSelectedRow(), COLUMN_ID).toString());
      }
    }
  }

  void btnStartDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnStartDate.getX() + posXFrame + 150;
    posY = this.btnStartDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtStartDate.setText(date);
    }
    btnEndDate.requestFocus(true);

  }
  private void registerKeyboardActions() {
                /// set up the maps:
                InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
                ActionMap actionMap = getActionMap();

               // F3
               Integer key = new Integer(KeyEvent.VK_F3);
               inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), key);
               actionMap.put(key, new KeyAction(key));
               // F1
               key = new Integer(KeyEvent.VK_F1);
               inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), key);
               actionMap.put(key, new KeyAction(key));

               // F8
               key = new Integer(KeyEvent.VK_F8);
               inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), key);
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

//               pnlInfoLeft.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
        }

        class KeyAction  extends AbstractAction {

                private Integer identifier = null;

                public KeyAction(Integer identifier) {
                        this.identifier = identifier;
                }

                /**
                 * Invoked when an action occurs.
                 */
                public void actionPerformed(ActionEvent e) {

                  if (identifier.intValue() == KeyEvent.VK_F3 || identifier.intValue() == KeyEvent.VK_ENTER) {
                    btnSearch.doClick();
                  }else if (identifier.intValue() == KeyEvent.VK_F1) {
                    btnDone.doClick();
                  }else if (identifier.intValue() == KeyEvent.VK_F8) {
                    btnDelete.doClick();
                  }else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                    btnCancel.doClick();
                  }
                }
              }
    private void catchHotKey(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }else if (e.getKeyCode() == KeyEvent.VK_F3) {
        btnSearch.doClick();
      }else if (e.getKeyCode() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }else if (e.getKeyCode() == KeyEvent.VK_F12 ||
               e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }

    }


  void btnEndDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnEndDate.getX() + posXFrame + 150;
    posY = this.btnEndDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtEndDate.setText(date);
    }
    btnSearch.requestFocus(true);

  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      if (SCREEN_FLAG == 1) {
        frmMain.showScreen(Constant.SCRS_PROMOTION_MIX_MATCH_MODIFY);
        frmMain.pnlPromotionMixMatchModify.showData(table.getValueAt(table.
            getSelectedRow(), COLUMN_ID).toString());
      }
      if (SCREEN_FLAG == 2) {
        frmMain.showScreen(Constant.SCRS_PROMOTION_THRES_HOLD_MODIFY);
        frmMain.pnlPromoThresholdModify.showData(table.getValueAt(table.
            getSelectedRow(), COLUMN_ID).toString());
      }
    }
  }

  void txtRowLimit_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtRowLimit, 2);
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
      btnSearch.doClick();
    }
  }

  void txtPromoID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtPromoID, PromotionBusObj.LEN_PROMO_ID);
    if (e.getKeyChar() < e.VK_0 || e.getKeyChar() > e.VK_9) {
      e.consume();
    }
    navigationComp(e, txtStartDate);
  }

  void navigationComp(KeyEvent e, JTextField txt) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      txt.requestFocus(true);
      txt.selectAll();
    }
  }

  void txtPromoName_keyTyped(KeyEvent e) {
    ut.checkCharUpdate(e);
  }

  void txtDesc_keyTyped(KeyEvent e) {
    ut.checkCharUpdate(e);
  }

  public boolean checkDateMMID(String MMID, DataAccessLayer DAL) {
    boolean bLag = false;
    String sql =
        "Select to_char(EXTRACT_DATE,'dd/mm/yyyy') EXTRACT_DATE from BTM_IM_MIX_MATCH_HEAD where  MIX_MATCH_NO = " +
        MMID;

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        if (rs.getString("EXTRACT_DATE").equals("07/07/7777")) {
          bLag = true;
        }
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return bLag;
  }

  public boolean checkDateThresholdID(String ThresholdID, DataAccessLayer DAL) {
    boolean bLag = false;
    String sql =
        "Select to_char(EXTRACT_DATE,'dd/mm/yyyy') EXTRACT_DATE from BTM_IM_PROMO_THRESHOLD_HEAD where  THRESHOLD_NO = " +
        ThresholdID;

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        if (rs.getString("EXTRACT_DATE").equals("7/7/7777")) {
          bLag = true;
        }
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return bLag;
  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if (table.getRowCount() > 0) {
      int i = 1;
      i = ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1009_Sure"),
                         DialogMessage.QUESTION_MESSAGE,
                         DialogMessage.YES_NO_OPTION);
      if (i == DialogMessage.YES_VALUE) {
        for (int j = 0; j < table.getRowCount(); j++) {
          BJCheckBox check = (BJCheckBox) table.getValueAt(j, COLUMN_CHECK);
          try {
            stmt = DAL.getConnection().createStatement();
            Vector vSql = new Vector();
            Vector vSqlThrhld = new Vector();
            if (check.isSelected()) {
              if (cboType.getSelectedItem().equals("Mix Match")) {
                if (checkDateMMID(check.getValue(), DAL)) {
                  vSql.addElement(
                      "Delete from BTM_IM_MIX_MATCH_BUY where  MIX_MATCH_NO = " +
                      check.getValue());
                  vSql.addElement(
                      "Delete from BTM_IM_MIX_MATCH_GET where  MIX_MATCH_NO = " +
                      check.getValue());
                  vSql.addElement(
                      "Delete from BTM_IM_MIX_MATCH_HEAD where  MIX_MATCH_NO = " +
                      check.getValue());
                }
                else {
                  vSql.addElement(
                      "Update BTM_IM_MIX_MATCH_HEAD set STATUS = '0' where  MIX_MATCH_NO = " +
                      check.getValue());
                }
                DAL.executeBatchQuery(vSql);
                DAL.executeUpdate("commit");
              }
            }
            if (cboType.getSelectedItem().equals("Threshold")) {
              if (checkDateThresholdID(check.getValue(), DAL)) {
                vSqlThrhld.addElement(
                    "Delete from BTM_IM_PROMO_THRESHOLD_DETAIL where  THRESHOLD_NO = " +
                    check.getValue());
                vSqlThrhld.addElement(
                    "Delete from BTM_IM_PROMO_THRESHOLD_SKU where  THRESHOLD_NO = " +
                    check.getValue());
                vSqlThrhld.addElement(
                    "Delete from BTM_IM_PROMO_THRESHOLD_HEAD where  THRESHOLD_NO = " +
                    check.getValue());
              }
              else {
                vSqlThrhld.addElement(
                    "Update BTM_IM_PROMO_THRESHOLD_HEAD set STATUS = '0' where  THRESHOLD_NO = " +
                    check.getValue());
              }
              DAL.executeBatchQuery(vSqlThrhld);
              DAL.executeUpdate("commit");
              vSqlThrhld.clear();
            }
            stmt.close();
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
      if (!ut.checkDate(txtStartDate.getText().trim(), "/") &&
          !txtStartDate.getText().trim().equals("")) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1093_InputMustDateType"));
        txtStartDate.requestFocus();
        txtStartDate.setSelectionStart(0);
        txtStartDate.setSelectionEnd(txtStartDate.getText().length());
        return;
      }
      if (!ut.checkDate(txtEndDate.getText().trim(), "/") &&
          !txtEndDate.getText().trim().equals("")) {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1093_InputMustDateType"));
        txtEndDate.requestFocus();
        txtEndDate.setSelectionStart(0);
        txtEndDate.setSelectionEnd(txtEndDate.getText().length());
        return;
      }
      if (txtStartDate.getText().trim().length() > 0 &&
          txtEndDate.getText().trim().length() > 0) {
        if (ut.compareDate(txtStartDate.getText(), txtEndDate.getText())) {
          ut.showMessage(frmMain, lang.getString("TT001"),
                         lang.getString("MS1153_StartDateBeforeEndDate"));
          return;
        }
      }
      if (txtRowLimit.getText().equals("")) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1015_InputLimitedNumberSearchingRecords"));
        return;
      }
      rowsNum = Integer.parseInt(txtRowLimit.getText());
//      if (rowsNum > 50) {
//        ut.showMessage(frmMain, lang.getString("TT001"),
//            "The number of rows is too long! It must be less than or equal 50");
//        return;
//      }
      searchData(rowsNum);

    }
  }

  void txtStartDate_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtEndDate_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
}

class PanelPromoChildSearch_btnCancel_actionAdapter
    implements ActionListener {
  private PanelPromoChildSearch adaptee;
  PanelPromoChildSearch_btnCancel_actionAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelPromoChildSearch_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_btnSearch_actionAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelPromoChildSearch_btnDone_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_btnDone_actionAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelPromoChildSearch_btnStartDate_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_btnStartDate_actionAdapter(PanelPromoChildSearch
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnStartDate_actionPerformed(e);
  }
}

class PanelPromoChildSearch_btnEndDate_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_btnEndDate_actionAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnEndDate_actionPerformed(e);
  }
}

class PanelPromoChildSearch_table_mouseAdapter
    extends java.awt.event.MouseAdapter {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_table_mouseAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelPromoChildSearch_txtRowLimit_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_txtRowLimit_keyAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtRowLimit_keyTyped(e);
  }
}

class PanelPromoChildSearch_txtPromoID_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_txtPromoID_keyAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtPromoID_keyTyped(e);
  }
}

class PanelPromoChildSearch_txtPromoName_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_txtPromoName_keyAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtPromoName_keyTyped(e);
  }
}

class PanelPromoChildSearch_txtDesc_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_txtDesc_keyAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtDesc_keyTyped(e);
  }
}

class PanelPromoChildSearch_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_btnDelete_actionAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelPromoChildSearch_txtStartDate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_txtStartDate_keyAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtStartDate_keyPressed(e);
  }
}

class PanelPromoChildSearch_txtEndDate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPromoChildSearch adaptee;

  PanelPromoChildSearch_txtEndDate_keyAdapter(PanelPromoChildSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtEndDate_keyPressed(e);
  }
}
