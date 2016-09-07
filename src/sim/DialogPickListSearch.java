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
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Font;

/**
 * <p>Title: Search Pick List</p>
 * <p>Description: Search Pick List by Date, Pick List ID, Prod group, UPC, Creator, Type, Status </p>
 * <p>Description: Main -> Inv Mgmt -> Pick List -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM </p>
 * @author PHUONG NGUYEN
 * @version 1.0
 */

public class DialogPickListSearch extends JDialog {
  //My variable
  public int FLAG_SCR=0;
  public boolean done=false;
  public String PLID="";
  public static final String PL_TO_BACKROOM = "BackRoom";
  public static final String PL_TO_FRONTROOM = "FrontRoom";

  //Assign column name to the position of column of showed grid
  private static final int COL_PL_ID        = 0;
  private static final int COL_DESC        = 1;
  private static final int COL_PROD_GROUP   = 2;
  private static final int COL_TYPE         = 3;
  private static final int COL_CREATE_DATE  = 4;
  private static final int COL_EXPECTED_DATE= 5;
  private static final int COL_RECEIPT_DATE = 6;
  private static final int COL_STATUS       = 7;
  private static final int COL_CREATOR      = 8;

  public static final String STATUS_WORKSHEET = "WorkSheet";
  public static final String STATUS_APPROVED = "Approved";
  public static final String STATUS_COMPLETED = "Completed";
  public static final String STATUS_CANCELLED = "Cancelled";
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

  Statement stmt = null;
//-----------------------declare BorderLayout------------------------------//
    BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    BorderLayout borderLayout4 = new BorderLayout();
//-------------------------declare Panel Header--------------------------//
    BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER); //-------------------------declare Panel information--------------------------//
    JPanel pnlInfo = new JPanel();
    JPanel pnlInfoLeft = new JPanel();
    JPanel pnlInfoRight = new JPanel();
    JPanel pnlInfoLeftLabel = new JPanel();
    JPanel pnlInfoLeftText = new JPanel();

    JPanel pnlInfoRightLabel = new JPanel();
    JPanel pnlInfoRightText = new JPanel();

    JLabel lblUPC = new JLabel();
    JLabel lblProGroup = new JLabel();
    JLabel lblCreator = new JLabel();
    JLabel lblToDate = new JLabel();
    JLabel lblRowLimit = new JLabel();

    JTextField txtUPC = new JTextField();
    JTextField txtPLID = new JTextField();
    JTextField txtToDate = new JTextField();
    JTextField txtRowLimit = new JTextField();


//-------------------------declare Panel  search result--------------------------//
  JScrollPane jScrollPane1 = new JScrollPane();

//----------------------------------------------------------------------------------------------//
    FrameMainSim frmMain;
    DataAccessLayer DAL;
    PickListBusObj plBusObj = new PickListBusObj();
    Utils ut = new Utils();
    Vector vStatus = new Vector();
    int parentScreen = 0;
    SortableTableModel dm = new SortableTableModel();

    BJTable table = new BJTable(dm, true) {
        public Class getColumnClass(int col) {
            switch (col) {
                case COL_PL_ID:
                    return String.class;
                default:
                    return Object.class;
            }
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

  int rowsNum = 0;
  JButton btnToDate = new JButton();
  JButton btnUPC = new JButton();
  JTextField txtFromDate = new JTextField();
  JButton btnFromDate = new JButton();
  JLabel lblFromDate = new JLabel();
  JLabel lblType = new JLabel();
  JComboBox cboStatus = new JComboBox();
  BJComboBox cboCreator = new BJComboBox();
  JComboBox cboType = new JComboBox();
  JLabel lblPLID = new JLabel();
  BJButton btnDone = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnCancel = new BJButton();
  BJComboBox cboProGroup = new BJComboBox();
  JLabel lblStatus = new JLabel();
  public DialogPickListSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain, PickListBusObj plBusObj) {
        super(frame, title, modal);
        try {
            this.frmMain = frmMain;
            this.plBusObj = plBusObj;
            DAL = frmMain.getDAL();
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public DialogPickListSearch() {
        this(null, "", false,null,null);
    }
    private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(new Dimension(800,600));
    this.addKeyListener(new DialogPickListSearch_this_keyAdapter(this));
    this.addWindowListener(new DialogPickListSearch_this_windowAdapter(this));
    this.getContentPane().setLayout(borderLayout1);
    pnlInfo.setPreferredSize(new Dimension(800, 180));

    pnlInfo.setToolTipText("");
    pnlInfoLeft.setPreferredSize(new Dimension(400, 180));

    pnlInfoLeftLabel.setPreferredSize(new Dimension(180, 180));
    pnlInfoLeftLabel.setLayout(null);
    lblUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblUPC.setPreferredSize(new Dimension(100, 22));
    lblUPC.setHorizontalAlignment(SwingConstants.LEFT);
    lblUPC.setText(lang.getString("UPC")+":");
    lblUPC.setBounds(new Rectangle(36, 95, 100, 22));
    lblProGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblProGroup.setPreferredSize(new Dimension(100, 22));
    lblProGroup.setHorizontalAlignment(SwingConstants.LEFT);
    lblProGroup.setText(lang.getString("ProGroup")+":");
    lblProGroup.setBounds(new Rectangle(36, 66, 100, 22));
    lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblRowLimit.setPreferredSize(new Dimension(100, 22));
    lblRowLimit.setBounds(new Rectangle(36, 126, 100, 22));
    lblRowLimit.setText(lang.getString("RowsLimit")+":");
    pnlInfoLeftText.setPreferredSize(new Dimension(220, 180));
    pnlInfoLeftText.setLayout(null);
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtUPC.setPreferredSize(new Dimension(175, 22));
    txtUPC.setHorizontalAlignment(SwingConstants.LEADING);
    txtUPC.setBounds(new Rectangle(6, 95, 136, 22));
    txtPLID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPLID.setPreferredSize(new Dimension(140, 22));
    txtPLID.setRequestFocusEnabled(true);
    txtPLID.setHorizontalAlignment(SwingConstants.LEADING);
    txtPLID.setBounds(new Rectangle(6, 35, 170, 22));
    txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtRowLimit.setPreferredSize(new Dimension(60, 22));
    txtRowLimit.setText(DAL.getSearchLimit());
    txtRowLimit.setHorizontalAlignment(SwingConstants.LEADING);
    txtRowLimit.setBounds(new Rectangle(6, 126, 60, 22));
    txtRowLimit.addKeyListener(new DialogPickListSearch_txtRowLimit_keyAdapter(this));

    pnlInfoRight.setPreferredSize(new Dimension(400, 180));

    pnlInfoRightLabel.setPreferredSize(new Dimension(180, 180));
    pnlInfoRightLabel.setLayout(null);
    lblCreator.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblCreator.setPreferredSize(new Dimension(100, 22));
    lblCreator.setText(lang.getString("Creator")+":");
    lblCreator.setBounds(new Rectangle(40, 35, 92, 22));
    lblToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblToDate.setPreferredSize(new Dimension(100, 22));
    lblToDate.setText(lang.getString("ToDate")+":");
    lblToDate.setBounds(new Rectangle(40, 5, 101, 22));

    pnlInfoRightText.setPreferredSize(new Dimension(220, 180));
    pnlInfoRightText.setLayout(null);
    txtToDate.setEnabled(true);
    txtToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtToDate.setPreferredSize(new Dimension(140, 22));
    txtToDate.setEditable(true);
    txtToDate.setBounds(new Rectangle(8, 5, 130, 22));
//    txtToDate.addKeyListener(new DialogPickListSearch_txtToDate_keyAdapter(this));
    btnToDate.setBounds(new Rectangle(142, 5, 28, 22));
    btnToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnToDate.setPreferredSize(new Dimension(30, 22));
    btnToDate.setText("...");
    btnToDate.addActionListener(new
                                   DialogPickListSearch_btnToDate_actionAdapter(this));
    pnlInfo.setLayout(borderLayout3);
    pnlInfoLeft.setLayout(borderLayout2);
    pnlInfoRight.setLayout(borderLayout4);
    btnUPC.setBounds(new Rectangle(146, 95, 30, 22));

    btnUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnUPC.setPreferredSize(new Dimension(30, 22));
    btnUPC.setText("...");
//    btnUPC.addActionListener(new DialogPickListSearch_btnStore_actionAdapter(this));
    txtFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtFromDate.setPreferredSize(new Dimension(140, 22));
    txtFromDate.setText("");
    txtFromDate.setBounds(new Rectangle(6, 5, 136, 22));
    btnFromDate.setBounds(new Rectangle(146, 5, 30, 22));
    btnFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnFromDate.setPreferredSize(new Dimension(30, 22));
    btnFromDate.setText("...");
    btnFromDate.addActionListener(new DialogPickListSearch_btnFromDate_actionAdapter(this));
    lblFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblFromDate.setPreferredSize(new Dimension(100, 22));
    lblFromDate.setText(lang.getString("FromDate")+":");
    lblFromDate.setBounds(new Rectangle(36, 5, 91, 22));
    lblType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblType.setPreferredSize(new Dimension(100, 22));
    lblType.setText(lang.getString("Type")+":");
    lblType.setBounds(new Rectangle(40, 66, 73, 22));
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboStatus.setPreferredSize(new Dimension(140, 22));
    cboStatus.setBounds(new Rectangle(8, 95, 162, 22));
    cboCreator.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboCreator.setPreferredSize(new Dimension(140, 22));
    cboCreator.setBounds(new Rectangle(8, 35, 162, 22));
    cboCreator.addKeyListener(new DialogPickListSearch_cboCreator_keyAdapter(this));
    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboType.setPreferredSize(new Dimension(140, 22));
    cboType.setBounds(new Rectangle(8, 66, 162, 22));
    lblPLID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPLID.setPreferredSize(new Dimension(100, 20));
    lblPLID.setText(lang.getString("PickListID")+":");
    lblPLID.setBounds(new Rectangle(36, 35, 110, 22));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText(lang.getString("Done")+"(F1)");
    btnDone.addActionListener(new DialogPickListSearch_btnDone_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText(lang.getString("Search")+" (F3)");
    btnSearch.addActionListener(new
                                DialogPickListSearch_btnSearch_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText(lang.getString("Delete")+" (F8)");
    btnDelete.addActionListener(new
                                DialogPickListSearch_btnDelete_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new
                                DialogPickListSearch_btnCancel_actionAdapter(this));
    cboProGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboProGroup.setBounds(new Rectangle(6, 66, 170, 22));
    lblStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStatus.setText(lang.getString("Status")+":");
    lblStatus.setBounds(new Rectangle(40, 95, 84, 22));
    pnlHeader.add(btnDone);
    pnlHeader.add(btnSearch);
//    pnlHeader.add(btnDelete);
    pnlHeader.add(btnCancel);
    jScrollPane1.getViewport().add(table, null);
    pnlInfo.add(pnlInfoLeft, java.awt.BorderLayout.WEST);
    pnlInfo.add(pnlInfoRight, BorderLayout.CENTER);
    table.addMouseListener(new DialogPickListSearch_table_mouseAdapter(this));
//    table.addKeyListener(new DialogPickListSearch_table_keyAdapter(this));

//-------Hearder
    pnlHeader.setBackground(new Color(121, 152, 198));
    pnlHeader.setPreferredSize(new Dimension(800, 45));
    pnlInfoLeftLabel.add(lblFromDate, null);
    pnlInfoLeftLabel.add(lblPLID, null);
    pnlInfoLeftLabel.add(lblProGroup, null);
    pnlInfoLeftLabel.add(lblUPC, null);
    pnlInfoLeftLabel.add(lblRowLimit);
    pnlInfoLeftText.add(txtFromDate, null);
    pnlInfoLeftText.add(btnFromDate, null);
    pnlInfoLeftText.add(txtPLID, null);
    pnlInfoLeftText.add(cboProGroup);
    pnlInfoLeftText.add(txtUPC);
    pnlInfoLeftText.add(btnUPC, null);
    pnlInfoLeftText.add(txtRowLimit);
    pnlInfoRightLabel.add(lblCreator, null);
    pnlInfoRightLabel.add(lblType, null);
    pnlInfoRightLabel.add(lblToDate, null);
    pnlInfoRightLabel.add(lblStatus);
    pnlInfoRight.add(pnlInfoRightText, java.awt.BorderLayout.CENTER);
    pnlInfoRightText.add(cboCreator, null);
    pnlInfoRightText.add(cboType, null);
    pnlInfoRightText.add(cboStatus, null);
    pnlInfoRightText.add(txtToDate, null);
    pnlInfoRightText.add(btnToDate, null);
    pnlInfoRight.add(pnlInfoRightLabel, java.awt.BorderLayout.WEST);
    jScrollPane1.setPreferredSize(new Dimension(800, 375));
    jScrollPane1.getViewport().add(table, null);
    //jScrollPane1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    this.getContentPane().add(pnlInfo, java.awt.BorderLayout.CENTER);
    this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.SOUTH);
    pnlInfoLeft.add(pnlInfoLeftLabel, java.awt.BorderLayout.WEST);
    pnlInfoLeft.add(pnlInfoLeftText, java.awt.BorderLayout.CENTER);
    this.getContentPane().add(pnlHeader, java.awt.BorderLayout.NORTH);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation( (screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);

    txtUPC.setRequestFocusEnabled(true);
    initScreen();
    }
    void initScreen(){
      txtFromDate.setText(ut.getSystemDate(1));
      txtToDate.setText(ut.getSystemDate(1));
      txtPLID.setText("");
      setCboProdGroup(cboProGroup);
      txtUPC.setText("");
      txtRowLimit.setText(DAL.getSearchLimit());
      setCboType(cboType);
      setCboStatus(cboStatus);
      setCboCreator(cboCreator);
    }
//set data into combobox Status
    public void setCboStatus(JComboBox cbo) {
      cbo.removeAllItems();
      cbo.addItem("All");
      cbo.addItem("WorkSheet");
      cbo.addItem("Approved");
      cbo.addItem("Completed");
      cbo.addItem("Cancelled");
      cbo.setSelectedIndex(0); //default status: All
    }
//set data into combobox Type
    private void setCboType(JComboBox cbo) {
      cbo.removeAllItems();
      cbo.addItem("All");
      cbo.addItem(PL_TO_FRONTROOM);
      cbo.addItem(PL_TO_BACKROOM);

      cbo.setSelectedIndex(0); //default status: All
    }
//set data into combobox product group
    private void setCboProdGroup(BJComboBox cbo){
      ResultSet rs = null;
      String SQL = "select PROD_GROUP_ID,DESCRIPTION from BTM_IM_PROD_GROUP"
          + " where TYPE = 'P'";
      try{
        stmt=DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery(SQL);
        cbo.setData2(rs);
        rs.close();
        stmt.close();
      }
      catch(Exception ex){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      };
    }
//set data into combobox creator
    private void setCboCreator(BJComboBox cbo){
      ResultSet rs = null;
      String SQL = "SELECT DISTINCT EMP_ID, EMP_CDE "
          + "FROM BTM_IM_EMPLOYEE, BTM_IM_PICK_LIST "
          + "WHERE USER_ID = EMP_ID ";
      try{
        stmt=DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery(SQL);
        cbo.setData2(rs);
        rs.close();
        stmt.close();
      }
      catch(Exception ex){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      };
    }

//    public void applyPermission() {
//       EmpRight er = new EmpRight();
//       er.initData(DAL, DAL.getEmpID());
//       er.setData(DAL.getEmpID(), Constant.SCRS_ADMIN_ROLE);
//  }

    void txtRowLimit_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e, txtRowLimit, 3);
        ut.checkNumber(e);
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
    }
    }
// action performing of button Done
    void btnDone_actionPerformed(ActionEvent e) {
//        if (FLAG_SCR==1){
//           done=true;
//          if(table.getSelectedRow()!=-1){
//            PLID=table.getValueAt(table.getSelectedRow(),0).toString();
//          }
          this.dispose();
//        }
      }
// action performing of button Cancel
    void btnCancel_actionPerformed(ActionEvent e) {
      done=false;
      this.dispose();

    }

     void btnSearch_actionPerformed(ActionEvent e) {
       if (!ut.checkDate(txtFromDate.getText().trim(), "/") && ! txtFromDate.getText().trim().equals("")){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1023_FromDateMustInTypedd/mm/yyyy"));
        txtFromDate.setText("");
        txtFromDate.requestFocus();
        return;
      }
      if (!ut.checkDate(txtToDate.getText().trim(), "/") && ! txtToDate.getText().trim().equals("")){
       ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1024_ToDateMustInTypedd/mm/yyyy"));
       txtToDate.setText("");
       txtToDate.requestFocus();
       return;
     }
     if (!txtFromDate.getText().equalsIgnoreCase("") && !txtToDate.getText().equalsIgnoreCase("")){
       if (ut.compareDate(txtFromDate.getText(), txtToDate.getText())) {
         ut.showMessage(frmMain, lang.getString("TT001"),
                        lang.getString("MS1022_FromDateBeforeToDate"));
         return;
       }
     }
     if (txtRowLimit.getText().equals("")) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1015_InputLimitedNumberSearchingRecords"));
          return;
      }
        rowsNum = Integer.parseInt(txtRowLimit.getText());
        searchData(rowsNum);
    }

    // Search Data
    void searchData(int rowsNum) {
      dm.resetIndexes();
      try{
           stmt = DAL.getConnection().createStatement(ResultSet.
                                       TYPE_SCROLL_INSENSITIVE,
                                       ResultSet.CONCUR_READ_ONLY);
           table.setData(getData(rowsNum,stmt));
           stmt.close();
      }catch(Exception ex){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      };
      table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
      table.getTableHeader().setReorderingAllowed(false);
      table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
      table.setHeaderName(lang.getString("PickListID"), COL_PL_ID);
      table.setHeaderName(lang.getString("Description"), COL_DESC);
      table.setHeaderName(lang.getString("ProductGroup"), COL_PROD_GROUP);
      table.setHeaderName(lang.getString("Type"), COL_TYPE);
      table.setHeaderName(lang.getString("CreateDate"), COL_CREATE_DATE);
      table.setHeaderName(lang.getString("ExpectDate"), COL_EXPECTED_DATE);
      table.setHeaderName(lang.getString("ReceiptDate"), COL_RECEIPT_DATE);
      table.setHeaderName(lang.getString("Status"), COL_STATUS);
      table.setHeaderName(lang.getString("Creator"), COL_CREATOR);
      table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
     }
     private ResultSet getData(int rows, Statement stmt) {
       ResultSet rs = null;
       try {
         String SQL = " SELECT * FROM (SELECT DISTINCT PL.PICK_LIST_ID, PL.DECSRIPTION, 	    "
         + "		 CASE  PL.REPLEN_GROUP_ID 	    "
         + "		 	   WHEN '' THEN '' ELSE  	   "
         + "			   (SELECT PRG.DESCRIPTION FROM BTM_IM_PROD_GROUP PRG  "
         + "		 			    WHERE PL.REPLEN_GROUP_ID = PRG.PROD_GROUP_ID)   	     "
         + "		 END AS PROD_GROUP_NAME,  "
         + "	 	 CASE PL.TYPE  "
         + "		 	  WHEN 'F' THEN 'FrontRoom'  	   "
         + "		 	  WHEN 'B' THEN 'BackRoom' END AS TYPE, 	   "
         + "	 to_char(PL.CREATE_DATE,'dd/MM/yyyy') CREATE_DATE,       "
         + "	 to_char(PL.EXPECTED_DATE,'dd/MM/yyyy') EXPECTED_DATE,       "
         + "	 to_char(PL.RECEIPT_DATE,'dd/MM/yyyy') RECEIPT_DATE,  	    "
         + "	 CASE PL.STATUS "
         + "	 	  WHEN 'W' THEN '"+STATUS_WORKSHEET+"'"
         + "		  WHEN 'A' THEN '"+STATUS_APPROVED+"'"
         + "		  WHEN 'C' THEN '"+STATUS_COMPLETED+"' ELSE"
         + "		  '"+STATUS_CANCELLED+"' END AS STATUS,  "
         + "	 EMP_CDE  "
         + "FROM BTM_IM_PICK_LIST PL, 	  "
         + " 	 BTM_IM_EMPLOYEE EMP, "
         + "     BTM_IM_PICK_LIST_ITEM PLIT  "
         + "WHERE PL.USER_ID=EMP.EMP_ID and PLIT.PICK_LIST_ID = PL.PICK_LIST_ID ";
         if(!cboStatus.getSelectedItem().toString().equalsIgnoreCase("All")){
            SQL += " and PL.STATUS = '" + cboStatus.getSelectedItem().toString().substring(0,1) + "'";
         }
         if(!cboType.getSelectedItem().toString().equalsIgnoreCase("All")){
            SQL += " and PL.TYPE = '" + cboType.getSelectedItem().toString().substring(0,1) + "'";
         }
         if(!cboProGroup.getSelectedItem().toString().equalsIgnoreCase("All")){
            SQL += " and PL.REPLEN_GROUP_ID = '" + cboProGroup.getSelectedData().toString() + "'";
         }
         if (txtPLID.getText().toString().trim().length() > 0) {
           SQL += " and PL.PICK_LIST_ID = '" + txtPLID.getText().toString().trim() + "'";
         }
         if(!cboCreator.getSelectedItem().toString().equalsIgnoreCase("All")){
           String Creatr = getUserID(cboCreator.getSelectedItem().toString());
            SQL += " and EMP_ID = '" + Creatr + "'";
         }
       if (!txtFromDate.getText().equals("")){
             SQL += " and CREATE_DATE >= to_date('" + txtFromDate.getText() + "','DD/MM/YYYY')";
           }
       if (!txtToDate.getText().equals("")){
             SQL += " and CREATE_DATE <= to_date('" + txtToDate.getText() + "','DD/MM/YYYY')";
           }
       if (!txtUPC.getText().equals("")){
             SQL += " and PLIT.ITEM_ID = '"+txtUPC.getText()+"'";
       }
       SQL += " and PL.STORE_ID = '"+DAL.getStoreID()+"'";
       SQL += " ) WHERE ROWNUM <= " + rows  ;
//         System.out.println(SQL);
         rs = DAL.executeScrollQueries(SQL,stmt);
       }
       catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }
       return rs;
     }
     public String getUserID(String userName){
       String userID =null;
       ResultSet rs = null;
       String SQL="select EMP_ID from BTM_IM_EMPLOYEE where EMP_CDE='"+userName+"'";
       try{
         stmt=DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         rs = stmt.executeQuery(SQL);
         if(rs.next()){
           userID = rs.getString("EMP_ID");
         }
         rs.getStatement().close();
       }catch(Exception ex){
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(ex);
       }
       return userID;
  }

    void table_mouseClicked(MouseEvent e) {
      if (FLAG_SCR == 1) {
        done = true;
        if (table.getSelectedRow() != -1) {
          PLID = table.getValueAt(table.getSelectedRow(), 0).toString();
        }
        this.dispose();
      }else{
        if (e.getClickCount() == 2) {
          if (this.table.getSelectedRow() >= 0) {
            String PLID = table.getValueAt(table.getSelectedRow(),
                                              COL_PL_ID).toString();
            frmMain.pnlPickListModify.showPickListOnForm(PLID);
            frmMain.pnlPickListModify.initFormValue(frmMain.pnlPickListModify.flagStatus);
            done = true;
            this.dispose();
          }
          else {
//            ut.showMessage(frmMain, "Pick List Search",
//                           "You should choose data !");
//            return;
          }
        }
      }
    }
    //get selected PLID
    public String getPLID(){
      String pLID="";
      if(table.getSelectedRow()!=-1){
        pLID=table.getValueAt(table.getSelectedRow(),COL_PL_ID).toString();
      }
      return pLID;
    }

    private void registerKeyboardActions() {
    /// set up the maps:

      InputMap inputMap = new InputMap();
      inputMap.setParent(pnlInfo.getInputMap(JComponent.
                                                 WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
      ActionMap actionMap = pnlInfo.getActionMap();

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

      pnlInfo.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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

        if (identifier.intValue() == KeyEvent.VK_F3 ||
            identifier.intValue() == KeyEvent.VK_ENTER) {
          btnSearch.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F1) {
          btnDone.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F8) {
          btnDelete.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F12 ||
                 identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
      }
    }

    private void catchHotKey(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        btnDone.doClick();
      } else if (e.getKeyCode() == KeyEvent.VK_F3) {
        btnSearch.doClick();
      } else if (e.getKeyCode() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      } else if (e.getKeyCode() == KeyEvent.VK_F12 ||
               e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }

      }
  void navigationComp(KeyEvent e, JTextField txt){
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      txt.requestFocus(true);
      txt.selectAll();
    }
  }
 void this_windowOpened(WindowEvent e) {
    txtUPC.requestFocus(true);
  }

//action performing of btnFromDate
  void btnFromDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnFromDate.getX() + posXFrame + 150;
    posY = this.btnFromDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseStartDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtFromDate.setText(date);
    }
  }

  //action performing of btnToDate
  public void btnToDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnToDate.getX() + posXFrame + 150;
    posY = this.btnToDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtToDate.setText(date);
    }
  }

  void txtPLID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtPLID,Constant.LENGHT_PICK_LIST_ID);
    ut.checkNumber(e);
  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
  public void cboCreator_keyPressed(KeyEvent e) {

  }

  public void btnDelete_actionPerformed(ActionEvent e) {
//    Vector vDelete = new Vector();
//    if(table.getSelectedRow()!=-1){
//      if(!table.getValueAt(table.getSelectedRow(),COL_STATUS).toString().equals(STATUS_WORKSHEET)){
//        ut.showMessage(frmMain,lang.getString("FontName_Label"),"You can not delete the pick list in this status.");
//        return;
//      }else{
//        table.removeRowSelectionInterval(table.getSelectedRow(),table.getSelectedRow()+1);
//        vDelete.clear();
//        String SQL = "delete BTM_IM_PICK_LIST_ITEM where PICK_LIST_ID = '"+getPLID()+"'";
//        vDelete.addElement(SQL);
//        SQL = "delete BTM_IM_PICK_LIST where PICK_LIST_ID = '"+getPLID()+"'";
//        vDelete.addElement(SQL);
//        if(!vDelete.isEmpty()){
//          try {
//            stmt = DAL.getConnection().createStatement(ResultSet.
//                TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            DAL.executeBatchQuery(vDelete,stmt);
//            stmt.close();
//          }
//          catch (Exception ex) {
//            ExceptionHandle eh = new ExceptionHandle();
//            eh.ouputError(ex);
//          }
//        }
//      }
//    }
  }
}

class DialogPickListSearch_btnDone_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogPickListSearch adaptee;
  DialogPickListSearch_btnDone_actionAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class DialogPickListSearch_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogPickListSearch adaptee;
  DialogPickListSearch_btnSearch_actionAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class DialogPickListSearch_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogPickListSearch adaptee;
  DialogPickListSearch_btnDelete_actionAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class DialogPickListSearch_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogPickListSearch adaptee;
  DialogPickListSearch_btnCancel_actionAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogPickListSearch_cboCreator_keyAdapter
    extends KeyAdapter {
  private DialogPickListSearch adaptee;
  DialogPickListSearch_cboCreator_keyAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cboCreator_keyPressed(e);
  }
}

class DialogPickListSearch_btnToDate_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogPickListSearch adaptee;
  DialogPickListSearch_btnToDate_actionAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnToDate_actionPerformed(e);
  }
}

class DialogPickListSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
    DialogPickListSearch adaptee;

    DialogPickListSearch_txtRowLimit_keyAdapter(DialogPickListSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtRowLimit_keyTyped(e);
    }
}

class DialogPickListSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogPickListSearch adaptee;

  DialogPickListSearch_table_mouseAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogPickListSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogPickListSearch adaptee;

  DialogPickListSearch_this_windowAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DialogPickListSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogPickListSearch adaptee;

  DialogPickListSearch_this_keyAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}
class DialogPickListSearch_btnFromDate_actionAdapter implements java.awt.event.ActionListener {
  DialogPickListSearch adaptee;

  DialogPickListSearch_btnFromDate_actionAdapter(DialogPickListSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnFromDate_actionPerformed(e);
  }
}

