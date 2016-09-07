package sim;

import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import btm.business.SeasonBusObj;
import btm.attr.Season;
import java.sql.*;
import java.sql.Statement;
import java.util.*;

//import java.lang.
/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Season Setup -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTN Intersoft</p>
 * @author Loan Vo
 * @version 1.0
 */

public class DialogSeasonSearch extends JDialog {

DataAccessLayer DAL;
   FrameMainSim frmMain;
   CardLayout cardLayout;
   JPanel parent;
   SeasonBusObj seaBusObj = new SeasonBusObj();
   Utils ut = new Utils();
//   Vector vSql = new Vector();
   BJCheckBox check = new BJCheckBox();

   int nSeaID = 0;
   int lenCodeSeaId = 1;
   String startDate,endDate;
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
   BJButton btnSearch = new BJButton();

   ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international



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
                     case 1: return Long.class;
                     default:
                             return Object.class;
             }
     }
   };

   BJTable table = new BJTable(dm, true) {
//                   public boolean isCellEditable(int row, int col) {
//                           return false;
//                   }
           };

//  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray, Color.white, null, null);



JPanel pnlAlignLeft = new JPanel();
JButton jbtnStartdate = new JButton();
JButton jBtnEnddate = new JButton();
JTextArea txtDesc = new JTextArea();

  JLabel jLabel1 = new JLabel();
  JTextField txtRowLimit = new JTextField();
  BJButton btnDelete = new BJButton();

  public DialogSeasonSearch(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  public DialogSeasonSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain,SeasonBusObj seasonBusObj) {
      super(frame, title, modal);
      try {
        this.frmMain = frmMain;
        DAL = frmMain.getDAL();
        jbInit();
      }
      catch(Exception ex) {
        ex.printStackTrace();
      }
    }

  public DialogSeasonSearch() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setMaximumSize(new Dimension(53, 16));
    jLabel1.setMinimumSize(new Dimension(53, 16));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    this.setSize(new Dimension(800, 600));
    this.addWindowListener(new DialogSeasonSearch_this_windowAdapter(this));
    this.addKeyListener(new DialogSeasonSearch_this_keyAdapter(this));
   jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel1.setText(lang.getString("RowsLimit")+":");
    txtRowLimit.setPreferredSize(new Dimension(6, 20));
    txtRowLimit.setToolTipText(lang.getString("InputMustBeNumber"));
    txtRowLimit.setText(DAL.getSearchLimit());
    txtRowLimit.setBounds(new Rectangle(10, 105, 46, 22));
    txtRowLimit.addKeyListener(new DialogSeasonSearch_txtRowLimit_keyAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.addActionListener(new DialogSeasonSearch_btnCancel_actionAdapter(this));
    txtStartDate.setBackground(Color.white);
    txtStartDate.setEditable(true);
    txtStartDate.addFocusListener(new DialogSeasonSearch_txtStartDate_focusAdapter(this));
    txtStartDate.addKeyListener(new DialogSeasonSearch_txtStartDate_keyAdapter(this));
    this.setBackground(UIManager.getColor("Label.background"));
//    this.setPreferredSize(new Dimension(800, 600));
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    pnlAlignLeft.setMinimumSize(new Dimension(10, 50));
    pnlAlignLeft.setPreferredSize(new Dimension(30, 50));

    jbtnStartdate.setBounds(new Rectangle(263, 54, 29, 20));
    jbtnStartdate.setText("...");
    jbtnStartdate.addKeyListener(new DialogSeasonSearch_jbtnStartdate_keyAdapter(this));
    jbtnStartdate.addActionListener(new DialogSeasonSearch_jbtnStartdate_actionAdapter(this));
    jBtnEnddate.setText("...");
    jBtnEnddate.addKeyListener(new DialogSeasonSearch_jBtnEnddate_keyAdapter(this));
    jBtnEnddate.addActionListener(new DialogSeasonSearch_jBtnEnddate_actionAdapter(this));
    jBtnEnddate.setBounds(new Rectangle(263, 79, 29, 20));
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDesc.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDesc.setPreferredSize(new Dimension(200, 122));
    txtDesc.setText("");
    txtDesc.setRows(0);
    txtDesc.setWrapStyleWord(false);
    txtDesc.addKeyListener(new DialogSeasonSearch_txtDesc_keyAdapter(this));
    txtEndDate.setBackground(Color.white);
    txtEndDate.setEditable(true);
    txtEndDate.addKeyListener(new DialogSeasonSearch_txtEndDate_keyAdapter(this));
    txtEndDate.addFocusListener(new DialogSeasonSearch_txtEndDate_focusAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.addActionListener(new DialogSeasonSearch_btnSearch_actionAdapter(this));
    txtSeaID.setBackground(Color.white);
    txtSeaID.addActionListener(new DialogSeasonSearch_txtSeaID_actionAdapter(this));
    txtSeaID.addKeyListener(new DialogSeasonSearch_txtSeaID_keyAdapter(this));
    txtSeaName.addKeyListener(new DialogSeasonSearch_txtSeaName_keyAdapter(this));
    table.addMouseListener(new DialogSeasonSearch_table_mouseAdapter(this));
    table.addKeyListener(new DialogSeasonSearch_table_keyAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnDelete.addActionListener(new DialogSeasonSearch_btnDelete_actionAdapter(this));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setPreferredSize(new Dimension(120, 37));
    this.getContentPane().add(pnlHeader, BorderLayout.NORTH);
    this.getContentPane().add(pnlAlignLeft, BorderLayout.WEST);
    this.getContentPane().add(pnlInfoLeft, BorderLayout.CENTER);
    this.getContentPane().add(pnlInfoRight, BorderLayout.EAST);
    this.getContentPane().add(jScrollPane1, BorderLayout.SOUTH);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    FlowLayout layout = new FlowLayout(FlowLayout.CENTER);

    //------------------------------------------------------------------------------------//
    //----------------Panel Header content all buttons------------------------------------//
    pnlHeader.setPreferredSize(new Dimension(850, 50));

    //Initial Buttons
     //Cancel
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    //Search
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");

    pnlHeader.add(btnSearch, null);
    pnlHeader.add(btnDelete,null);
    pnlHeader.add(btnCancel, null);

    //--------------Panel Infomation Left-----------------------------------------------//
    pnlInfoLeft.setPreferredSize(new Dimension(450, 150));
    pnlInfoLeft.setLayout(borderLayout2);

    pnlInfoLeft.add(pnlInfoLeftLabel, BorderLayout.WEST);
    pnlInfoLeft.add(pnlInfoLeftTextfield, BorderLayout.CENTER);

    //-----Panel content Lable-------------------------------------------------------//
    pnlInfoLeftLabel.setPreferredSize(new Dimension(120, 10));

    lblSeasonID.setText(lang.getString("SeasonID")+":");
    lblSeasonID.setPreferredSize(new Dimension(120, 20));
    lblSeasonID.setHorizontalAlignment(SwingConstants.LEFT);
    lblSeasonID.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblSeasonID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    lblSeaName.setText(lang.getString("SeasonName")+":");
    lblSeaName.setPreferredSize(new Dimension(120, 20));
    lblSeaName.setHorizontalAlignment(SwingConstants.LEFT);
    lblSeaName.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblSeaName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    lblStartDate.setText(lang.getString("StartDate")+":");
    lblStartDate.setPreferredSize(new Dimension(120, 20));
    lblStartDate.setHorizontalAlignment(SwingConstants.LEFT);
    lblStartDate.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    lblEndDate.setText(lang.getString("EndDate")+":");
    lblEndDate.setPreferredSize(new Dimension(120, 20));
    lblEndDate.setHorizontalAlignment(SwingConstants.LEFT);
    lblEndDate.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    pnlInfoLeftLabel.add(lblSeasonID, null);
    pnlInfoLeftLabel.add(lblSeaName, null);
    pnlInfoLeftLabel.add(lblStartDate, null);
    pnlInfoLeftLabel.add(lblEndDate, null);
    pnlInfoLeftLabel.add(jLabel1, null);

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
    txtSeaName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtStartDate.setText("");
    txtStartDate.setSelectionStart(0);
    txtStartDate.setPreferredSize(new Dimension(245, 20));
    txtStartDate.setBounds(new Rectangle(10, 55, 245, 20));
    txtStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtEndDate.setText("");
    txtEndDate.setSelectionStart(0);
    txtEndDate.setPreferredSize(new Dimension(245, 20));
    txtEndDate.setBounds(new Rectangle(10, 80, 245, 20));
    txtEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    pnlInfoLeftTextfield.add(txtSeaID, null);
    pnlInfoLeftTextfield.add(txtSeaName, null);
    pnlInfoLeftTextfield.add(txtStartDate, null);
    pnlInfoLeftTextfield.add(txtEndDate, null);
    pnlInfoLeftTextfield.add(jBtnEnddate, null);
    pnlInfoLeftTextfield.add(jbtnStartdate, null);
    pnlInfoLeftTextfield.add(txtRowLimit, null);

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

    lblDesc.setText(lang.getString("Description")+":");
    lblDesc.setPreferredSize(new Dimension(110, 20));
    lblDesc.setHorizontalAlignment(SwingConstants.LEFT);
    lblDesc.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    pnlInfoRightTextfield.setPreferredSize(new Dimension(230, 10));
    pnlInfoRightTextfield.setLayout(flowLayout4);

    jScrollPane1.setPreferredSize(new Dimension(800, 380));

    jScrollPane1.getViewport().add(table, null);
//
//    String[] columnNames = new String[] {
//        "Season ID", "Season Name", "Start Date", "End Date", "Description"};
//    dm.setDataVector(new Object[][] {}
//                     , columnNames);
//
//    table.setColumnWidth(0, 120);
//    table.setColumnWidth(1, 120);
//    table.setColumnWidth(2, 120);
//    table.setColumnWidth(3, 120);
//    table.setColumnWidth(4, 350);

    txtSeaName.requestFocus(true);
    DAL.getOracleConnect();
    seaBusObj.setDataAccessLayer(DAL);

  }

  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_SEASON_SETUP);
    this.dispose();
  }



   void btnSearch_actionPerformed(ActionEvent e) {

    if (txtRowLimit.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS031_RowsIsNum"));
      return;
    }

    rowsNum = Integer.parseInt(txtRowLimit.getText());
//    if (rowsNum > 50){
//      ut.showMessage(frmMain,lang.getString("TT001"), "The number of rows is too long! it must be less than or equal 50");
//      return;
//    }
    String Startdate = txtStartDate.getText().trim();
    String Enddate = txtEndDate.getText().trim();
    if (!ut.checkDate(Startdate, "/") && ! Startdate.equals("")){
    ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1023_FromDateMustInTypedd/mm/yyyy"));
    txtStartDate.requestFocus();
    txtStartDate.setSelectionStart(0);
    txtStartDate.setSelectionEnd(txtStartDate.getText().length());

    return;
  }
  if(!ut.checkDate(Enddate,"/") && ! Enddate.equals("")){
    ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1023_ToDateMustInTypedd/mm/yyyy"));
    txtEndDate.requestFocus();
    txtEndDate.setSelectionStart(0);
    txtEndDate.setSelectionEnd(txtEndDate.getText().length());

    return;
  }
     frmMain.pnlSeasonSetup.rowNum = rowsNum;
    searchData(rowsNum);
  }
  //Update
  void updateSeason(String seaID, String seaName, String startdate,String enddate, String desc){
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

     txtSeaID.setText(seaID);
     txtSeaName.setText(seaName);
     txtStartDate.setText(startdate);
     txtEndDate.setText(enddate);
     txtDesc.setText(desc);
     String last_upd_id = DAL.getEmpID();
     if (seaName.equals("")) {
           ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1025_SeasonNameNotNull"));
           txtSeaName.requestFocus(true);
           return;
   }
   DAL.getConnection();
   seaBusObj.setDataAccessLayer(DAL);
   seaBusObj.updateSeason(seaID,seaName,startdate,enddate,desc,stmt);
   try {
     stmt.close();
   }
   catch (Exception ex) {
     ex.printStackTrace();
   }
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

  //Search data
    void searchData(int rowsNum) {
        dm.resetIndexes();
        try {
          stmt = DAL.getConnection().createStatement(ResultSet.
                                                     TYPE_SCROLL_INSENSITIVE,
                                                     ResultSet.CONCUR_READ_ONLY);
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }

        table.setData(seaBusObj.getData(txtSeaID.getText(), txtSeaName.getText(),
                                          txtStartDate.getText(),txtEndDate.getText(),
                                          txtDesc.getText(), rowsNum,DAL,stmt),check,1);


          table.setColumnWidth(0, 10);
          table.setColumnWidth(1, 100);
          table.setColumnWidth(2, 100);
          table.setColumnWidth(3, 100);
          table.setColumnWidth(4, 100);

          table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
          table.getTableHeader().setReorderingAllowed(false);
          table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
          table.setHeaderName(lang.getString("SeasonID"), 1);
          table.setHeaderName(lang.getString("SeasonName"), 2);
          table.setHeaderName(lang.getString("StartDate"), 3);
          table.setHeaderName(lang.getString("EndDate"), 4);
          table.setHeaderName(lang.getString("Description"), 5);
          ut.changeDataTypetoLong(1, dm);
          try {
            stmt.close();
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }

    }
    //set text
  public void setText(String seaID,String seaName,String startday,String enddate,String desc){
   txtSeaID.setText(seaID);
   txtSeaName.setText(seaName);
   txtStartDate.setText(startDate);
   txtEndDate.setText(enddate);
   txtDesc.setText(desc);
 }
    void table_mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
          done = true;
          setDataIntoSeasonBusObj();
          this.dispose();
        }

      }

      //set data into SeasonBusObj
      void setDataIntoSeasonBusObj() {
        DAL.getConnection();
        String desc = ut.putCommaToString(txtDesc.getText());
        String startdate = txtStartDate.getText();
        String enddate = txtEndDate.getText();
        if (desc.equals("") || desc.equals(null))
          desc = "";
        if(startdate.equals("") || startdate.equals(null))
          startdate = ut.getSystemDateTime();
        if(enddate.equals("") || enddate.equals(null))
       //   enddate = "7/7/7777";
        seaBusObj.setDataAccessLayer(DAL);
        frmMain.seaBusObj.setValue(txtSeaID.getText(),
                           ut.putCommaToString(txtSeaName.getText()),
                           startdate, enddate,desc);
      }

      //return data
      String getData() {
        String result = "";
        result = "" + table.getValueAt(table.getSelectedRow(), 1);
        return result;
      }


  void txtRowLimit_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtRowLimit,2);
    ut.checkNumber(e);
    navigationComp(e,txtDesc);
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


  void txtSeaID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtSeaID,5);
    ut.checkNumber(e);
    navigationComp(e,txtSeaName);
  }

  void txtSeaName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtSeaName,120);
    navigationComp(e,txtStartDate);
  }

  void txtDesc_keyTyped(KeyEvent e) {
    ut.limitLenjTextArea(e,txtDesc,120);
    if(e.getKeyChar()==KeyEvent.VK_ENTER){
      btnSearch.doClick();
    }
  }

  void txtSeaID_actionPerformed(ActionEvent e) {

  }

  void txtEndDate_focusLost(FocusEvent e) {
//    String Enddate = txtEndDate.getText().trim();
//    if (!ut.checkDate(Enddate, "/")) {
//      ut.showMessage(frmMain, lang.getString("TT001"), "Input Enddate must be a date type");
//      txtEndDate.setSelectionStart(0);
//      txtEndDate.setSelectionEnd(txtEndDate.getText().length());
//      txtEndDate.requestFocus();
//      return;
//    }

  }

  void txtStartDate_focusLost(FocusEvent e) {
//    String Startdate = txtStartDate.getText().trim();
//    if (!ut.checkDate(Startdate, "/")) {
//      ut.showMessage(frmMain, lang.getString("TT001"), "Input startdate must be a date type");
//      txtStartDate.setSelectionStart(0);
//      txtStartDate.setSelectionEnd(txtStartDate.getText().length());
//      txtStartDate.requestFocus();
//      return;
//    }

  }
  private void registerKeyboardActions() {
    pnlHeader.add(btnSearch, null);
               /// set up the maps:

               InputMap inputMap = new InputMap();
               inputMap.setParent(pnlHeader.getInputMap(JComponent.
                                                      WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
               ActionMap actionMap = pnlHeader.getActionMap();


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

                pnlHeader.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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

                 if (identifier.intValue() == KeyEvent.VK_F3 ) {
                   btnSearch.doClick();
                 }
                 else if (identifier.intValue() == KeyEvent.VK_F8){//|| identifier.intValue() == KeyEvent.VK_ESCAPE) {
                   btnDelete.doClick();
                 }
                 else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                   btnCancel.doClick();
                 }
               }
             }

             private void catchHotKey(KeyEvent e) {
               if (e.getKeyCode() == KeyEvent.VK_F1) {
                 btnSearch.doClick();
               }
               else if (e.getKeyCode() == KeyEvent.VK_F2){// || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                 btnDelete.doClick();
               }
               else if (e.getKeyCode() == KeyEvent.VK_F3 || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                 btnCancel.doClick();
               }

             }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtDesc_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtEndDate_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtStartDate_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtSeaName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtSeaID_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void jbtnStartdate_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void jBtnEnddate_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtStartDate_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      jbtnStartdate.requestFocus(true);
    }

  }

  void jbtnStartdate_keyTyped(KeyEvent e) {
    navigationComp(e,txtEndDate);
  }

  void txtEndDate_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER ||
        e.getKeyChar() == KeyEvent.VK_TAB) {
      jBtnEnddate.requestFocus(true);
    }
  }

  void jBtnEnddate_keyTyped(KeyEvent e) {
    navigationComp(e,txtRowLimit);
  }

  void this_windowOpened(WindowEvent e) {
    txtSeaID.requestFocus(true);
  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if (table.getRowCount()>0){
      int i = 1;
      if (checkTable(table)) {
        i = ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1009_Sure"),
                           DialogMessage.QUESTION_MESSAGE,
                           DialogMessage.YES_NO_OPTION);
      }
      if (i == DialogMessage.YES_VALUE) {
        String mess = "";
        for (int j = 0; j < table.getRowCount(); j++) {
          //Xoa du lieu trong Database
          BJCheckBox check = (BJCheckBox) table.getValueAt(j, 0);
          if (check.isSelected()) {
            if (checkExist(check.getValue())) {
              Statement stmt = null;
//              System.out.println("delete from btm_im_season where season_id ='" +
//                    check.getValue() + "'");
              try {
                stmt = DAL.getConnection().createStatement();

                DAL.executeUpdate(
                    "delete from btm_im_season where season_id ='" +
                    check.getValue() + "'", stmt);
//                DAL.executeUpdate(
//                    "delete from btm_im_item_loc_supplier where item_id ='" +
//                    check.getValue() + "'", stmt);
//                DAL.executeUpdate(
//                    "delete from btm_im_item_season where item_id ='" +
//                    check.getValue() + "'", stmt);
//                DAL.executeUpdate(
//                    "delete from btm_im_item_master where item_id ='" +
//                    check.getValue() + "'", stmt);

                stmt.close();
              }
              catch (Exception ex) {
                ex.printStackTrace();
              }

            }else {
              if (mess.equalsIgnoreCase("")){
                mess = "Items : " + check.getValue();
              }else{
                mess = mess + "; " + check.getValue();
              }
            }
          }
        }
        if (!mess.equalsIgnoreCase("")){
          ut.showMessage(frmMain,lang.getString("TT007"), mess + lang.getString("MS1010_CanNotDelete"));
        }
      }
      searchData(Integer.parseInt(txtRowLimit.getText()));
    }
  }
  boolean checkTable(BJTable table1){
    for (int i = 0; i<table.getRowCount(); i++){
      BJCheckBox check = (BJCheckBox) table1.getValueAt(i,0);
      if (check.isSelected()){
        return true;
      }
    }
    return false;
  }
  boolean checkExist(String seasonID){
    Statement stmt = null;
    ResultSet rs = null;
    try{
      stmt = DAL.getConnection().createStatement();
      String sql = "select * from btm_im_item_season where season_id ='" + seasonID + "'";
//      System.out.println(sql);
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        stmt.close();

        return false;
      }
      stmt.close();

    }catch(Exception e){
      e.printStackTrace();
    }
    return true;
  }

}

class DialogSeasonSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_btnCancel_actionAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogSeasonSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_btnSearch_actionAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class DialogSeasonSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_txtRowLimit_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRowLimit_keyTyped(e);
  }
}

class DialogSeasonSearch_jbtnStartdate_actionAdapter implements java.awt.event.ActionListener {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_jbtnStartdate_actionAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jbtnStartdate_actionPerformed(e);
  }
}

class DialogSeasonSearch_jBtnEnddate_actionAdapter implements java.awt.event.ActionListener {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_jBtnEnddate_actionAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jBtnEnddate_actionPerformed(e);
  }
}

class DialogSeasonSearch_txtStartDate_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_txtStartDate_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtStartDate_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtStartDate_keyPressed(e);
  }
}

class DialogSeasonSearch_txtSeaID_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_txtSeaID_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSeaID_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtSeaID_keyPressed(e);
  }
}

class DialogSeasonSearch_txtSeaName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_txtSeaName_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSeaName_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtSeaName_keyPressed(e);
  }
}

class DialogSeasonSearch_txtDesc_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_txtDesc_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtDesc_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtDesc_keyPressed(e);
  }
}

class DialogSeasonSearch_txtSeaID_actionAdapter implements java.awt.event.ActionListener {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_txtSeaID_actionAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtSeaID_actionPerformed(e);
  }
}

class DialogSeasonSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_table_mouseAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogSeasonSearch_txtEndDate_focusAdapter extends java.awt.event.FocusAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_txtEndDate_focusAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtEndDate_focusLost(e);
  }
}

class DialogSeasonSearch_txtStartDate_focusAdapter extends java.awt.event.FocusAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_txtStartDate_focusAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtStartDate_focusLost(e);
  }
}

class DialogSeasonSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_this_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogSeasonSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_table_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogSeasonSearch_txtEndDate_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_txtEndDate_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtEndDate_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtEndDate_keyTyped(e);
  }
}

class DialogSeasonSearch_jbtnStartdate_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_jbtnStartdate_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jbtnStartdate_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jbtnStartdate_keyTyped(e);
  }
}

class DialogSeasonSearch_jBtnEnddate_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_jBtnEnddate_keyAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jBtnEnddate_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jBtnEnddate_keyTyped(e);
  }
}

class DialogSeasonSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_this_windowAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DialogSeasonSearch_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  DialogSeasonSearch adaptee;

  DialogSeasonSearch_btnDelete_actionAdapter(DialogSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}
