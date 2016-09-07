package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import java.sql.*;
import btm.attr.*;
import java.util.*;

/**
 * <p>Title: Search Promotions</p>
 * <p>Description: Search Promotion by PromoID, Name, Start Date, End Date</p>
 * <p>Description: Main -> Merch Mgmt -> Promotion -> Search </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM INTERSOFT</p>
 * @author Phuong Nguyen
 * @version 1.0
 */

public class DialogPromoSearch extends JDialog {
  //My variable
  public int FLAG_SCR=0;
  public boolean bDone=false;
  public String PromotionID="";
  //Assign column name to the position of column of showed grid
  private static final int COL_PROMO_ID = 0;
  private static final int COL_PROMO_NAME = 1;
  private static final int COL_DESCRIPTION = 2;
  private static final int COL_START_DATE = 3;
  private static final int COL_END_DATE = 4;
  private static final int COL_START_TIME = 5;
  private static final int COL_END_TIME = 6;

  Statement stmt = null;
//    int status = 0;
//-----------------------declare BorderLayout------------------------------//
    BorderLayout borderLayout1 = new BorderLayout();
    GridLayout gridLayout1 = new GridLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    BorderLayout borderLayout4 = new BorderLayout();
//-------------------------declare Panel Header--------------------------//
    BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
    //buttons
    BJButton btnDone = new BJButton();
    BJButton btnSearch = new BJButton();
    BJButton btnDelete = new BJButton();
    BJButton btnCancel = new BJButton();

//-------------------------declare Panel information--------------------------//
    BJPanel pnlInfo = new BJPanel();
    BJPanel pnlInfoLeft = new BJPanel();
    BJPanel pnlInfoRight = new BJPanel();
    BJPanel pnlInfoLeftLabel = new BJPanel();
    BJPanel pnlInfoLeftText = new BJPanel();

    BJPanel pnlInfoRightLabel = new BJPanel();
    BJPanel pnlInfoRightText = new BJPanel();

    JLabel lblPromoID = new JLabel();//("Promotion ID:");
    JLabel lblPromoName = new JLabel();//("Promotion Name:");
    JLabel lblStartDate = new JLabel();//("Start Date:");
    JLabel lblEndDate = new JLabel();//("End Date:");
    JLabel lblRowLimit = new JLabel();//("Row Limit:");

    JTextField txtPromoID = new JTextField();
    JTextField txtPromoName = new JTextField();
    JTextField txtStartDate = new JTextField();
    JTextField txtEndDate = new JTextField();
    JTextField txtRowLimit = new JTextField();

    ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international


//-------------------------declare Panel  search result--------------------------//
    BJPanel pnlResultSearch = new BJPanel();
    JScrollPane jScrollPane1 = new JScrollPane();

//----------------------------------------------------------------------------------------------//
    FrameMainSim frmMain;
    DataAccessLayer DAL;
    PromotionBusObj promotionBusObj = new PromotionBusObj();
    Utils ut = new Utils();
    int parentScreen = 0;
    SortableTableModel dm = new SortableTableModel();

    BJTable table = new BJTable(dm, true) {
        public Class getColumnClass(int col) {
            switch (col) {
                case COL_PROMO_ID:
                    return String.class;
                default:
                    return Object.class;
            }
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    public boolean done = false;
    int rowsNum = 15;
  JButton btnStartDate = new JButton();
  JButton btnEndDate = new JButton();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();

  public DialogPromoSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain, PromotionBusObj promoBusObj) {
        super(frame, title, modal);
        try {
            this.frmMain = frmMain;
            this.promotionBusObj = promoBusObj;
            DAL = frmMain.getDAL();
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public DialogPromoSearch() {
        this(null, "", false,null,null);
    }
    private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    this.setSize(new Dimension(800, 600));
    this.addKeyListener(new DialogPromoSearch_this_keyAdapter(this));
    this.addWindowListener(new DialogPromoSearch_this_windowAdapter(this));
    this.getContentPane().setLayout(borderLayout1);

    pnlInfo.setPreferredSize(new Dimension(800, 100));
    pnlInfoLeft.setPreferredSize(new Dimension(400, 30));
    pnlInfoLeftLabel.setPreferredSize(new Dimension(180, 30));
    pnlInfoLeftLabel.setLayout(flowLayout1);
    lblPromoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPromoID.setPreferredSize(new Dimension(100, 20));
    lblPromoID.setHorizontalAlignment(SwingConstants.LEFT);
    lblPromoID.setText(lang.getString("PromoID")+":");
    lblPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPromoName.setPreferredSize(new Dimension(100, 20));
    lblPromoName.setHorizontalAlignment(SwingConstants.LEFT);
    lblPromoName.setText(lang.getString("PromoName")+":");
    lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblRowLimit.setPreferredSize(new Dimension(100, 20));
    lblRowLimit.setText(lang.getString("RowsLimit")+":");
    pnlInfoLeftText.setPreferredSize(new Dimension(1, 29));
    pnlInfoLeftText.setLayout(flowLayout2);
    txtPromoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPromoID.setPreferredSize(new Dimension(140, 20));
    txtPromoID.setHorizontalAlignment(SwingConstants.LEADING);
    txtPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPromoName.setPreferredSize(new Dimension(140, 20));
    txtPromoName.setRequestFocusEnabled(true);
    txtPromoName.setHorizontalAlignment(SwingConstants.LEADING);
    txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtRowLimit.setPreferredSize(new Dimension(80, 20));
    txtRowLimit.setText(DAL.getSearchLimit());
    txtRowLimit.setHorizontalAlignment(SwingConstants.LEADING);
    txtRowLimit.addKeyListener(new DialogPromoSearch_txtRowLimit_keyAdapter(this));

    pnlInfoRight.setPreferredSize(new Dimension(400, 100));

    pnlInfoRightLabel.setPreferredSize(new Dimension(130, 30));
    pnlInfoRightLabel.setLayout(flowLayout3);
    lblStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStartDate.setPreferredSize(new Dimension(100, 20));
    lblStartDate.setText(lang.getString("StartDate")+":");
    lblEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblEndDate.setPreferredSize(new Dimension(100, 20));
    lblEndDate.setText(lang.getString("EndDate")+":");

    pnlInfoRightText.setPreferredSize(new Dimension(200, 100));
    pnlInfoRightText.setLayout(flowLayout4);
    txtStartDate.setEnabled(true);
    txtStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtStartDate.setPreferredSize(new Dimension(140, 20));
    txtStartDate.setEditable(true);
    txtEndDate.setBackground(Color.white);
    txtEndDate.setEnabled(true);
    txtEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtEndDate.setPreferredSize(new Dimension(140, 20));
    btnStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnStartDate.setPreferredSize(new Dimension(30, 20));
    btnStartDate.setText("...");
    btnStartDate.addActionListener(new
                                   DialogPromoSearch_btnStartDate_actionAdapter(this));
    btnEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnEndDate.setPreferredSize(new Dimension(30, 20));
    btnEndDate.setText("...");
    btnEndDate.addActionListener(new DialogPromoSearch_btnEndDate_actionAdapter(this));

    pnlInfo.setLayout(borderLayout3);
    pnlInfoLeft.setLayout(borderLayout2);
    pnlInfoRight.setLayout(borderLayout4);
    pnlResultSearch.setOpaque(true);
    pnlResultSearch.setPreferredSize(new Dimension(800, 430));

    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setPreferredSize(new Dimension(130, 37));
    btnSearch.addActionListener(new DialogPromoSearch_btnSearch_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(130, 37));
    btnDone.addActionListener(new DialogPromoSearch_btnDone_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(130, 37));
    btnDelete.addActionListener(new DialogPromoSearch_btnDelete_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(130, 37));
    btnCancel.addActionListener(new DialogPromoSearch_btnCancel_actionAdapter(this));
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    flowLayout2.setAlignment(FlowLayout.LEFT);

    this.getContentPane().add(pnlHeader, BorderLayout.NORTH);
    this.getContentPane().add(pnlInfo, BorderLayout.CENTER);
    this.getContentPane().add(pnlResultSearch, BorderLayout.SOUTH);
    pnlInfo.add(pnlInfoLeft, java.awt.BorderLayout.WEST);
    pnlInfoLeft.add(pnlInfoLeftLabel, java.awt.BorderLayout.WEST);
    pnlInfoLeft.add(pnlInfoLeftText, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlInfoRight, BorderLayout.CENTER);
    pnlInfoRight.add(pnlInfoRightLabel, java.awt.BorderLayout.WEST);
    pnlInfoRight.add(pnlInfoRightText, java.awt.BorderLayout.CENTER);
    pnlResultSearch.add(jScrollPane1);
    jScrollPane1.getViewport().add(table, null);
    table.addMouseListener(new DialogPromoSearch_table_mouseAdapter(this));
    table.addKeyListener(new DialogPromoSearch_table_keyAdapter(this));

//-------Hearder
    pnlHeader.setPreferredSize(new Dimension(800, 47));

    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F3</html>");
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
   "&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");

    pnlHeader.add(btnDone, null);
    pnlHeader.add(btnSearch, null);
    pnlHeader.add(btnDelete, null);
    pnlHeader.add(btnCancel, null);
    pnlInfoLeftLabel.add(lblPromoID, null);
    pnlInfoLeftLabel.add(lblPromoName, null);
    pnlInfoLeftLabel.add(lblRowLimit, null);
    pnlInfoLeftText.add(txtPromoID, null);
    pnlInfoLeftText.add(txtPromoName, null);
    pnlInfoLeftText.add(txtRowLimit, null);
    pnlInfoRightLabel.add(lblStartDate, null);
    pnlInfoRightLabel.add(lblEndDate, null);
    pnlInfoRightText.add(txtStartDate, null);
    pnlInfoRightText.add(btnStartDate, null);
    pnlInfoRightText.add(txtEndDate, null);
    pnlInfoRightText.add(btnEndDate, null);
    jScrollPane1.setPreferredSize(new Dimension(452, 455));
    pnlResultSearch.setLayout(gridLayout1);


    jScrollPane1.getViewport().add(table, null);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation( (screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);

    txtPromoID.setRequestFocusEnabled(true);
    }
    public void applyPermission() {
       EmpRight er = new EmpRight();
       er.initData(DAL, DAL.getEmpID());
       er.setData(DAL.getEmpID(), Constant.SCRS_ADMIN_ROLE);
       btnSearch.setEnabled(er.getAdd());
       btnDone.setEnabled(er.getAdd());
     }

    void txtRowLimit_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e, txtRowLimit, 2);
        ut.checkNumber(e);
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          btnSearch.doClick();
        }
    }

    void btnCancel_actionPerformed(ActionEvent e) {
       if (FLAG_SCR==1){
         this.dispose();
       }else{
         done = false;
         this.dispose();
       }
    }



     void btnSearch_actionPerformed(ActionEvent e) {
       if (!ut.checkDate(txtStartDate.getText().trim(), "/") && ! txtStartDate.getText().trim().equals("")){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1155_StartdateDateType"));
        txtStartDate.requestFocus();
        txtStartDate.setSelectionStart(0);
        txtStartDate.setSelectionEnd(txtStartDate.getText().length());
        return;
      }
      if(!ut.checkDate(txtEndDate.getText().trim(),"/") && ! txtEndDate.getText().trim().equals("")){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1156_EnddateDateType"));
        txtEndDate.requestFocus();
        txtEndDate.setSelectionStart(0);
        txtEndDate.setSelectionEnd(txtEndDate.getText().length());
        return;
      }
       if (txtStartDate.getText().trim().length()>0 && txtEndDate.getText().trim().length()>0 ){
         if (ut.compareDate(txtStartDate.getText(), txtEndDate.getText())) {
           ut.showMessage(frmMain, lang.getString("TT001"),
                          lang.getString("MS325_BeginEndDate"));
           return;
         }
       }
       if (txtRowLimit.getText().equals("")) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1015_InputLimitedNumberSearchingRecords"));
            return;
        }
        rowsNum = Integer.parseInt(txtRowLimit.getText());
//        if (rowsNum > 50) {
//            ut.showMessage(frmMain, lang.getString("TT001"),
//                    "The number of rows is too long! It must be less than or equal 50");
//            return;
//        }
        searchData(rowsNum);
    }

    // Search Data
    void searchData(int rowsNum) {
      dm.resetIndexes();
      try{
           stmt = DAL.getConnection().createStatement(ResultSet.
                                       TYPE_SCROLL_INSENSITIVE,
                                       ResultSet.CONCUR_READ_ONLY);

           table.setData(getData(txtPromoID.getText(), txtPromoName.getText(),txtStartDate.getText()
                                 ,txtEndDate.getText(), rowsNum,stmt));
          stmt.close();
      }catch(Exception ex){};
      table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
      table.getTableHeader().setReorderingAllowed(false);
      table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
      table.setHeaderName(lang.getString("PromoID"), COL_PROMO_ID);
      table.setHeaderName(lang.getString("PromoName"), COL_PROMO_NAME);
      table.setHeaderName(lang.getString("Description"), COL_DESCRIPTION);
      table.setHeaderName(lang.getString("StartDate"), COL_START_DATE);
      table.setHeaderName(lang.getString("EndDate"), COL_END_DATE);
      table.setHeaderName(lang.getString("StartTime"), COL_START_TIME);
      table.setHeaderName(lang.getString("EndTime"), COL_END_TIME);
      table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
     }
     private ResultSet getData(String promoID, String promoName, String startDate,String endDate, int rows, Statement stmt) {
       ResultSet rs = null;
       try {
         String sqlStr = "Select to_char(Promo_Id) Promo_Id, Promo_Name, Description, "
             + " to_char(Start_Date,'DD/MM/YYYY') Start_Date, to_char(End_Date,'DD/MM/YYYY') End_Date,"
             + " to_char(Start_Time,'HH24:MI') Start_Time, to_char(End_Time,'HH24:MI') End_Time "
             + " From BTM_IM_PROMO_HEAD Where Status <> 'D' and rownum <=" +  rows;
         String sql = "";
         if (promoID.trim().length() > 0) {
           sql += " and lower(Promo_Id) like lower('%" + promoID + "%')";
         }
         if (promoName.trim().length() > 0) {
           sql += " and lower(Promo_Name) like lower('%" + promoName + "%')";
         }
         if (!startDate.trim().equals("")){
           sql += " and Start_Date >= to_date('" + startDate + "','DD/MM/YYYY')";
         }
         if (!endDate.trim().equals("")){
               sql += " and End_Date <= to_date('" + endDate + "','DD/MM/YYYY')";
             }
         sqlStr += sql + " Order By Promo_Id";
//         System.out.println(sqlStr);
         rs = DAL.executeScrollQueries(sqlStr,stmt);
       }
       catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }
       return rs;
     }

    //return data
    String getPromoID() {
        String promoId = "";
        promoId = "" + table.getValueAt(table.getSelectedRow(), COL_PROMO_ID);
        return promoId;
    }
    String getPromoName(){
      String promoName = "";
      promoName = table.getValueAt(table.getSelectedRow(),COL_PROMO_NAME).toString();
      return promoName;
    }

    void table_mouseClicked(MouseEvent e) {
      if (FLAG_SCR == 1) {
        bDone = true;
        if (table.getSelectedRow() != -1) {
          PromotionID = table.getValueAt(table.getSelectedRow(), 0).toString();
        }
        this.dispose();
      }else{
        if (e.getClickCount() == 2) {
          if (this.table.getSelectedRow() >= 0) {
            frmMain.promoBusObj.setData(txtPromoID.getText(),
                                        txtPromoName.getText(),
                                        txtStartDate.getText(),
                                        txtEndDate.getText(),
                                        rowsNum, dm.getDataVector(),
                                        table.getSelectedRow());
            String promoID = table.getValueAt(table.getSelectedRow(),
                                              COL_PROMO_ID).toString();
            frmMain.pnlPromotionHeadModify.setDataToModify(promoID);
            done = true;
            this.dispose();
          }
          else {
            ut.showMessage(frmMain, "Promotion Search",
                           "You should choose data !");
            return;
          }
        }
      }
    }
    void updateScreen(){
      txtPromoID.setText(frmMain.promoBusObj.promo_ID);
      txtPromoName.setText(frmMain.promoBusObj.promo_Name);
      txtStartDate.setText(frmMain.promoBusObj.start_Date);
      txtEndDate.setText(frmMain.promoBusObj.end_Date);
      txtRowLimit.setText(String.valueOf(frmMain.promoBusObj.rowlimit));
      setDataVector(frmMain.promoBusObj.vData);
    }
    void updateRowOnTable(String arrInfo[]){
      table.setValueAt(arrInfo[0],frmMain.promoBusObj.rowSelected,COL_PROMO_NAME);
      table.setValueAt(arrInfo[1],frmMain.promoBusObj.rowSelected,COL_DESCRIPTION);
      table.setValueAt(arrInfo[2],frmMain.promoBusObj.rowSelected,COL_START_DATE);
      table.setValueAt(arrInfo[3],frmMain.promoBusObj.rowSelected,COL_END_DATE);
      table.setValueAt(arrInfo[4],frmMain.promoBusObj.rowSelected,COL_START_TIME);
      table.setValueAt(arrInfo[5],frmMain.promoBusObj.rowSelected,COL_END_TIME);
    }

    void setDataVector(Vector vData){
      Vector vColumns = new Vector();
      vColumns.add("Promo ID");
      vColumns.add("Promo Name");
      vColumns.add("Description");
      vColumns.add("Start Date");
      vColumns.add("End Date");
      vColumns.add("Start Time");
      vColumns.add("End Time");
      dm.setDataVector(vData, vColumns);
      table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
      table.getTableHeader().setReorderingAllowed(false);
      table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,13));
      table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
      table.setColor(Color.lightGray, Color.white);
}

    private void registerKeyboardActions() {
                  /// set up the maps:

                  InputMap inputMap = new InputMap();
                  inputMap.setParent(pnlInfoLeft.getInputMap(JComponent.
                                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
                  ActionMap actionMap = pnlInfoLeft.getActionMap();

                 // F3
                 Integer key = new Integer(KeyEvent.VK_F3);
                 inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), key);
                 actionMap.put(key, new KeyAction(key));
                 // F1
                 key = new Integer(KeyEvent.VK_F1);
                 inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), key);
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

                 pnlInfoLeft.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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
  void txtPromoName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
  void txtPromoId_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
  void txtRowLimit_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void navigationComp(KeyEvent e, JTextField txt){
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      txt.requestFocus(true);
      txt.selectAll();
    }
  }
 void this_windowOpened(WindowEvent e) {
    txtPromoID.requestFocus(true);
  }

  public void btnStartDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnStartDate.getX() + posXFrame + 150;
    posY = this.btnStartDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("MS1018_ChooseEndDate"));
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

  public void btnEndDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnEndDate.getX() + posXFrame + 150;
    posY = this.btnEndDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("MS1018_ChooseEndDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtEndDate.setText(date);
    }
    btnSearch.requestFocus(true);
  }

  void txtPromoID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtPromoID,PromotionBusObj.LEN_PROMO_ID);
    if (e.getKeyChar() < e.VK_0 || e.getKeyChar() > e.VK_9){
      e.consume();
    }
    navigationComp(e,txtStartDate);
  }

  void btnDone_actionPerformed(ActionEvent e) {
    if (FLAG_SCR==1){
       bDone=true;
      if(table.getSelectedRow()!=-1){
        PromotionID=table.getValueAt(table.getSelectedRow(),0).toString();
      }
      this.dispose();
    }
  }

  //change Status of Promo Head to 'D'
  void deletePromoHead(String promoID){
    String sqlStr = "Update BTM_IM_PROMO_HEAD set STATUS='D' Where PROMO_ID='" + promoID + "'";
//    System.out.println(sqlStr);
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);
      DAL.executeUpdate(sqlStr, stmt);
      stmt.close();
    }catch(Exception ex){};
  }
  // delete Promo Store belong to Promo Head deleted
  void deletePromoStore(String promoID){
    String sqlStr = "Update BTM_IM_PROMO_STORE set START_DATE='07/Jul/7777', END_DATE='07/Jul/7777' Where PROMO_ID='" + promoID + "'";
//    System.out.println(sqlStr);
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);
      DAL.executeUpdate(sqlStr, stmt);
      stmt.close();
    }catch(Exception ex){};
  }

  boolean checkDeletePromo(String promoID){
    boolean found=false;
    String sqlStr = "select promo_id from  BTM_IM_PROMO_HEAD Where PROMO_ID='" + promoID + "' and CREATE_DATE =to_date('" + ut.getSystemDateStandard() + "','dd/MM/yyyy')";
//    System.out.println(sqlStr);
    ResultSet rs;
    try{
      rs=DAL.executeQueries(sqlStr);
      if (rs.next()){
        found=true;
      }
      rs.getStatement().close();
    }catch(Exception ex){
     ex.printStackTrace();
    };
    return found;
  }

// delete promo which created today
  void btnDelete_actionPerformed(ActionEvent e) {
    int row = table.getSelectedRow();



    if (row >= 0) {
      String promoId=table.getValueAt(row, COL_PROMO_ID).toString();
      if (checkDeletePromo(promoId)) {
        int choose = ut.showMessage(frmMain, lang.getString("TT001"),
                                    lang.getString("MS1021_DeletePromotion") +
                                    table.getValueAt(row, COL_PROMO_NAME).
                                    toString() + " ?", 3,
                                    DialogMessage.YES_NO_OPTION);
        if (choose == DialogMessage.YES_VALUE) {
          deletePromoHead(promoId);
          deletePromoStore(promoId);
          dm.removeRow(row);
        }
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1020_CanNotDeletePromoWhichCreatedYesterday"));

      }
    }
    else {
      if (table.getRowCount() > 0) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1019_ChoosePromotionWantDelete"));
        return;
      }
    }
  }
}

class DialogPromoSearch_btnEndDate_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogPromoSearch adaptee;
  DialogPromoSearch_btnEndDate_actionAdapter(DialogPromoSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnEndDate_actionPerformed(e);
  }
}

class DialogPromoSearch_btnStartDate_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogPromoSearch adaptee;
  DialogPromoSearch_btnStartDate_actionAdapter(DialogPromoSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnStartDate_actionPerformed(e);
  }
}

class DialogPromoSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
    DialogPromoSearch adaptee;

    DialogPromoSearch_txtRowLimit_keyAdapter(DialogPromoSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtRowLimit_keyTyped(e);
    }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRowLimit_keyPressed(e);
  }
}

class DialogPromoSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
    DialogPromoSearch adaptee;

    DialogPromoSearch_btnCancel_actionAdapter(DialogPromoSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnCancel_actionPerformed(e);
    }
}

class DialogPromoSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
    DialogPromoSearch adaptee;

    DialogPromoSearch_btnSearch_actionAdapter(DialogPromoSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnSearch_actionPerformed(e);
    }
}

class DialogPromoSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogPromoSearch adaptee;

  DialogPromoSearch_table_mouseAdapter(DialogPromoSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogPromoSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogPromoSearch adaptee;

  DialogPromoSearch_table_keyAdapter(DialogPromoSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogPromoSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogPromoSearch adaptee;

  DialogPromoSearch_this_windowAdapter(DialogPromoSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DialogPromoSearch_btnDone_actionAdapter implements java.awt.event.ActionListener {
  DialogPromoSearch adaptee;

  DialogPromoSearch_btnDone_actionAdapter(DialogPromoSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class DialogPromoSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogPromoSearch adaptee;

  DialogPromoSearch_this_keyAdapter(DialogPromoSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogPromoSearch_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  DialogPromoSearch adaptee;

  DialogPromoSearch_btnDelete_actionAdapter(DialogPromoSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}
