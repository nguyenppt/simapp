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
 * <p>Title: Search Receipt</p>
 * <p>Description: Search Receipt by Date, Receipt ID, UPC, Receiver, Destination </p>
 * <p>Description: Main -> Inv Mgmt -> Receive -> Form Supplier -> View Receipt -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM </p>
 * @author PHUONG NGUYEN
 * @version 1.0
 */

public class DialogReceiptSearch extends JDialog {
  //My variable
  public int FLAG_SCR=0;
  public boolean done=false;
  public String PLID="";
  //Assign column name to the position of column of showed grid
  private static final int COL_RECEIPT_ID        = 0;
  private static final int COL_FROM_SUPPLIER     = 1;
  private static final int COL_TO_STORE          = 2;
  private static final int COL_DESTINATION       = 3;
  private static final int COL_RECEIVER          = 4;
  private static final int COL_RECEIPT_DATE      = 5;
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
//    JLabel lblProGroup = new JLabel();
    JLabel lblReceiver = new JLabel();
    JLabel lblToDate = new JLabel();
    JLabel lblRowLimit = new JLabel();

    JTextField txtUPC = new JTextField();
    JTextField txtReceiptID = new JTextField();
    JTextField txtToDate = new JTextField();
    JTextField txtRowLimit = new JTextField();


//-------------------------declare Panel  search result--------------------------//
  JScrollPane jScrollPane1 = new JScrollPane();

//----------------------------------------------------------------------------------------------//
    FrameMainSim frmMain;
    DataAccessLayer DAL;
    ReceiptBusObj receiptBusObj = new ReceiptBusObj();
    Utils ut = new Utils();
    Vector vStatus = new Vector();
    int parentScreen = 0;
    SortableTableModel dm = new SortableTableModel();

    BJTable table = new BJTable(dm, true) {
        public Class getColumnClass(int col) {
            switch (col) {
                case COL_RECEIPT_ID:
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
  JLabel lblDestination = new JLabel();
//  JComboBox cboStatus = new JComboBox();
  BJComboBox cboReceiver = new BJComboBox();
  JComboBox cboDestination = new JComboBox();
  JLabel lblReceiptID = new JLabel();
  BJButton btnDone = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnCancel = new BJButton();

  public DialogReceiptSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain, ReceiptBusObj receiptBusObj) {
        super(frame, title, modal);
        try {
            this.frmMain = frmMain;
            this.receiptBusObj = receiptBusObj;
            DAL = frmMain.getDAL();
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public DialogReceiptSearch() {
        this(null, "", false,null,null);
    }
    private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(new Dimension(800, 600));
    this.addKeyListener(new DialogReceiptSearch_this_keyAdapter(this));
    this.addWindowListener(new DialogReceiptSearch_this_windowAdapter(this));
    this.getContentPane().setLayout(borderLayout1);
    pnlInfo.setPreferredSize(new Dimension(800, 160));

    pnlInfo.setToolTipText("");
    pnlInfoLeft.setPreferredSize(new Dimension(400, 160));

    pnlInfoLeftLabel.setPreferredSize(new Dimension(180, 160));
    pnlInfoLeftLabel.setLayout(null);
    lblUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblUPC.setPreferredSize(new Dimension(100, 22));
    lblUPC.setHorizontalAlignment(SwingConstants.LEFT);
    lblUPC.setText(lang.getString("UPC")+":");
    lblUPC.setBounds(new Rectangle(19, 67, 106, 24));
    lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblRowLimit.setPreferredSize(new Dimension(100, 22));
    lblRowLimit.setText(lang.getString("RowsLimit")+":");
    lblRowLimit.setBounds(new Rectangle(19, 97, 106, 21));
    pnlInfoLeftText.setPreferredSize(new Dimension(220, 160));
    pnlInfoLeftText.setLayout(null);
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtUPC.setPreferredSize(new Dimension(175, 22));
    txtUPC.setHorizontalAlignment(SwingConstants.LEADING);
    txtUPC.setBounds(new Rectangle(3, 67, 154, 24));
    txtReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtReceiptID.setPreferredSize(new Dimension(140, 22));
    txtReceiptID.setRequestFocusEnabled(true);
    txtReceiptID.setHorizontalAlignment(SwingConstants.LEADING);
    txtReceiptID.setBounds(new Rectangle(3, 38, 188, 23));
    txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtRowLimit.setPreferredSize(new Dimension(60, 22));
    txtRowLimit.setText(DAL.getSearchLimit());
    txtRowLimit.setHorizontalAlignment(SwingConstants.LEADING);
    txtRowLimit.setBounds(new Rectangle(3, 97, 81, 25));
    txtRowLimit.addKeyListener(new DialogReceiptSearch_txtRowLimit_keyAdapter(this));

    pnlInfoRight.setPreferredSize(new Dimension(400, 160));

    pnlInfoRightLabel.setPreferredSize(new Dimension(180, 160));
    pnlInfoRightLabel.setLayout(null);
    lblReceiver.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblReceiver.setPreferredSize(new Dimension(100, 22));
    lblReceiver.setText(lang.getString("Receiver")+":");
    lblReceiver.setBounds(new Rectangle(11, 67, 118, 24));
    lblToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblToDate.setPreferredSize(new Dimension(100, 22));
    lblToDate.setText(lang.getString("ToDate")+":");
    lblToDate.setBounds(new Rectangle(11, 9, 118, 24));

    pnlInfoRightText.setPreferredSize(new Dimension(220, 160));
    pnlInfoRightText.setLayout(null);
    txtToDate.setEnabled(true);
    txtToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtToDate.setPreferredSize(new Dimension(140, 22));
    txtToDate.setEditable(true);
    txtToDate.setBounds(new Rectangle(0, 9, 163, 24));
//    txtToDate.addKeyListener(new DialogReceiptSearch_txtToDate_keyAdapter(this));
    btnToDate.setBounds(new Rectangle(167, 9, 29, 24));
    btnToDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnToDate.setPreferredSize(new Dimension(30, 22));
    btnToDate.setText("...");
    btnToDate.addActionListener(new
                                   DialogReceiptSearch_btnToDate_actionAdapter(this));
    pnlInfo.setLayout(borderLayout3);
    pnlInfoLeft.setLayout(borderLayout2);
    pnlInfoRight.setLayout(borderLayout4);
    btnUPC.setBounds(new Rectangle(160, 67, 31, 24));

    btnUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnUPC.setPreferredSize(new Dimension(30, 22));
    btnUPC.setText("...");
    btnUPC.addActionListener(new DialogReceiptSearch_btnUPC_actionAdapter(this));
    txtFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtFromDate.setPreferredSize(new Dimension(140, 22));
    txtFromDate.setText("");
    txtFromDate.setBounds(new Rectangle(3, 9, 154, 24));
    btnFromDate.setBounds(new Rectangle(160, 9, 31, 24));
    btnFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnFromDate.setPreferredSize(new Dimension(30, 22));
    btnFromDate.setText("...");
    btnFromDate.addActionListener(new DialogReceiptSearch_btnFromDate_actionAdapter(this));
    lblFromDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblFromDate.setPreferredSize(new Dimension(100, 22));
    lblFromDate.setText(lang.getString("FromDate")+":");
    lblFromDate.setBounds(new Rectangle(19, 9, 106, 24));
    lblDestination.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDestination.setPreferredSize(new Dimension(100, 22));
    lblDestination.setText(lang.getString("Type")+":");
    lblDestination.setBounds(new Rectangle(11, 38, 118, 23));
//    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    cboStatus.setPreferredSize(new Dimension(140, 22));
//    cboStatus.setBounds(new Rectangle(8, 95, 162, 22));
    cboReceiver.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboReceiver.setPreferredSize(new Dimension(140, 22));
    cboReceiver.setBounds(new Rectangle(0, 67, 196, 24));
    cboReceiver.addKeyListener(new DialogReceiptSearch_cboReceiver_keyAdapter(this));
    cboDestination.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboDestination.setPreferredSize(new Dimension(140, 22));
    cboDestination.setBounds(new Rectangle(0, 38, 196, 23));
    cboDestination.setEnabled(false);
    lblReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblReceiptID.setPreferredSize(new Dimension(100, 20));
    lblReceiptID.setText(lang.getString("ReceiptID")+":");
    lblReceiptID.setBounds(new Rectangle(19, 38, 106, 23));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText(lang.getString("Done")+" (F1)");
    btnDone.addActionListener(new DialogReceiptSearch_btnDone_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText(lang.getString("Search")+" (F3)");
    btnSearch.addActionListener(new
                                DialogReceiptSearch_btnSearch_actionAdapter(this));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText(lang.getString("Delete")+" (F8)");
    btnDelete.addActionListener(new
                                DialogReceiptSearch_btnDelete_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" F12");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new
                                DialogReceiptSearch_btnCancel_actionAdapter(this));
//    cboProGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    cboProGroup.setBounds(new Rectangle(6, 66, 170, 22));
//    lblStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    lblStatus.setText("Status:");
//    lblStatus.setBounds(new Rectangle(40, 95, 84, 22));
    pnlHeader.add(btnDone);
    pnlHeader.add(btnSearch);
//    pnlHeader.add(btnDelete);
    pnlHeader.add(btnCancel);
    jScrollPane1.getViewport().add(table, null);
    pnlInfo.add(pnlInfoLeft, java.awt.BorderLayout.WEST);
    pnlInfo.add(pnlInfoRight, BorderLayout.CENTER);
    table.addMouseListener(new DialogReceiptSearch_table_mouseAdapter(this));
//    table.addKeyListener(new DialogReceiptSearch_table_keyAdapter(this));

//-------Hearder
    pnlHeader.setBackground(new Color(121, 152, 198));
    pnlHeader.setPreferredSize(new Dimension(800, 45));
    pnlInfoLeftLabel.add(lblFromDate, null);
    pnlInfoLeftLabel.add(lblReceiptID, null);
    pnlInfoLeftLabel.add(lblUPC, null);
    pnlInfoLeftLabel.add(lblRowLimit);
    pnlInfoLeft.add(pnlInfoLeftText, java.awt.BorderLayout.CENTER);
    pnlInfoLeftText.add(txtFromDate, null);
    pnlInfoLeftText.add(btnFromDate, null);
    pnlInfoLeftText.add(txtReceiptID, null);
    pnlInfoLeftText.add(txtUPC, null);
    pnlInfoLeftText.add(btnUPC, null);
    pnlInfoLeftText.add(txtRowLimit, null);
    pnlInfoRightLabel.add(lblReceiver, null);
    pnlInfoRightLabel.add(lblToDate, null);
    pnlInfoRightLabel.add(lblDestination, null);
    //    pnlInfoRightLabel.add(lblStatus);
    pnlInfoRight.add(pnlInfoRightText, java.awt.BorderLayout.CENTER);
    pnlInfoRightText.add(txtToDate, null);
    pnlInfoRightText.add(btnToDate, null);
    pnlInfoRightText.add(cboDestination, null);
    pnlInfoRightText.add(cboReceiver, null);
    pnlInfoRight.add(pnlInfoRightLabel, java.awt.BorderLayout.WEST);
    jScrollPane1.setPreferredSize(new Dimension(800, 395));
    jScrollPane1.getViewport().add(table, null);
    this.getContentPane().add(pnlInfo, java.awt.BorderLayout.CENTER);
    this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.SOUTH);
    this.getContentPane().add(pnlHeader, java.awt.BorderLayout.NORTH);
    pnlInfoLeft.add(pnlInfoLeftLabel, java.awt.BorderLayout.WEST);
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
      txtReceiptID.setText("");
      txtUPC.setText("");
      txtRowLimit.setText(DAL.getSearchLimit());
      setcboDestination(cboDestination);
      cboDestination.setEnabled(false);
//      setCboStatus(cboStatus);
      setcboReceiver(cboReceiver);
    }
//set data into combobox Destination
    private void setcboDestination(JComboBox cbo) {
      cbo.removeAllItems();
//      cbo.addItem("All");
      cbo.addItem("BackRoom");
//      cbo.addItem("FrontRoom");
      cbo.setSelectedIndex(0); //default status: All
    }
//set data into combobox creator
    private void setcboReceiver(BJComboBox cbo){
      ResultSet rs = null;
      String SQL = "SELECT DISTINCT EMP_ID, EMP_CDE "
          + "FROM BTM_IM_EMPLOYEE, BTM_IM_RECEIPT "
          + "WHERE CREATER_ID = EMP_ID ";
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
      table.setHeaderName(lang.getString("ReceiptID"), COL_RECEIPT_ID);
      table.setHeaderName(lang.getString("FromSupplier"), COL_FROM_SUPPLIER);
      table.setHeaderName(lang.getString("ToStore"), COL_TO_STORE);
      table.setHeaderName(lang.getString("Type"), COL_DESTINATION);
      table.setHeaderName(lang.getString("Receiver"), COL_RECEIVER);
      table.setHeaderName(lang.getString("ReceiptDate"), COL_RECEIPT_DATE);
      table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
     }
     private ResultSet getData(int rows, Statement stmt) {
       ResultSet rs = null;
       try {
         String SQL1="";
         String SQL2="";
         String SQL="";
         String cond="";
         SQL1 = "SELECT DISTINCT RC.RECEIPT_ID, SU.SUPP_NAME FROM_SUPPLIER, ST.NAME TO_STORE,  "
         + "	   CASE ORD.FR_BR_FLAG  "
         + "	   		WHEN 'F' THEN 'FrontRoom'  "
         + "			WHEN 'B' THEN 'BackRoom' END AS DESTINATION,  "
         + "	   EM.EMP_CDE RECEIVER, to_char(RECEIPT_DATE,'dd/mm/yyyy')  "
         + "FROM BTM_IM_RECEIPT RC, "
         + "BTM_IM_RECEIPT_ITEM RCI, "
         + "	 BTM_IM_INV_ORDER ORD,  "
         + "	 BTM_IM_SUPPLIER SU, "
         + "	 BTM_IM_EMPLOYEE EM, "
         + "	 BTM_IM_STORE ST "
         + "WHERE RC.RECEIPT_ID = ORD.ORDER_ID "
         + "AND   RC.RECEIPT_ID = RCI.RECEIPT_ID "
         + "AND   RC.STORE_ID = ST.STORE_ID "
         + "AND	  RC.SUPP_ID =  SU.SUPP_ID "
         + "AND RC.CREATER_ID = EM.EMP_ID ";

     SQL2 = "SELECT DISTINCT RC.RECEIPT_ID, SU.SUPP_NAME FROM_SUPPLIER, ST.NAME TO_STORE,  "
     + "			 'BackRoom'  AS DESTINATION,  "
     + "	   EM.EMP_CDE RECEIVER, to_char(RECEIPT_DATE,'dd/mm/yyyy')  "
     + "FROM BTM_IM_RECEIPT RC, "
     + "BTM_IM_RECEIPT_ITEM RCI, "
     + "	 BTM_IM_SUPPLIER SU, "
     + "	 BTM_IM_EMPLOYEE EM, "
     + "	 BTM_IM_STORE ST "
     + "WHERE "
     + "   RC.RECEIPT_ID = RCI.RECEIPT_ID "
     + "AND   RC.STORE_ID = ST.STORE_ID "
     + "AND	  RC.SUPP_ID =  SU.SUPP_ID "
     + "AND RC.CREATER_ID = EM.EMP_ID ";

         if (txtReceiptID.getText().toString().trim().length() > 0) {
           cond += " and RC.RECEIPT_ID = '" + txtReceiptID.getText().toString().trim() + "'";
         }
         if(!cboReceiver.getSelectedItem().toString().equalsIgnoreCase("All")){
           String Receiver = getUserID(cboReceiver.getSelectedItem().toString());
            cond += " and EMP_ID = '" + Receiver + "'";
         }
         if (!txtFromDate.getText().equals("")){
             cond += " and RECEIPT_DATE >= to_date('" + txtFromDate.getText() + "','DD/MM/YYYY')";
           }
         if (!txtToDate.getText().equals("")){
             cond += " and RECEIPT_DATE <= to_date('" + txtToDate.getText() + "','DD/MM/YYYY')";
           }
         if (!txtUPC.getText().equals("")){
             cond += " and RCI.ITEM_ID = '"+getItemId(txtUPC.getText())+"'";
           }
           String storeName = DAL.getStoreID();
            cond += " and ST.STORE_ID = '" + storeName + "'";

       SQL = " SELECT * FROM ("+SQL1+cond+" union all " +SQL2+cond+" ) WHERE ROWNUM <= " + rows  ;

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
  //get Item_Id from BTM_IM_ITEM_MASTER
  //parameter Art_No : String
  String getItemId(String upc){
    String itemId = "";
    ResultSet rs = null;
    Statement stmt = null;
    String sql ="Select ITEM_ID From BTM_IM_ITEM_MASTER Where ART_NO='" + upc + "'";
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        itemId = rs.getString("ITEM_ID");
      }
      rs.close();
      stmt.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return itemId;
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
            String receiptID = table.getValueAt(table.getSelectedRow(),
                                              COL_RECEIPT_ID).toString();
            viewReceiptDetail(receiptID);
            frmMain.pnlViewReceiptDetails.getComboData();
            frmMain.pnlViewReceiptDetails.txtItemID.requestFocus(true);
            frmMain.pnlViewReceiptDetails.initScreen();
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


    void viewReceiptDetail(String receiptID){
      ResultSet rs = null;
      try {
        stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

        rs= receiptBusObj.getDataDetail(receiptID,DAL,stmt);
        rs.next();
        if (!rs.isBeforeFirst() && !rs.isAfterLast()) {
          frmMain.pnlViewReceiptDetails.txtReceiptID.setText(rs.getString("Receipt_ID"));
          frmMain.pnlViewReceiptDetails.txtStore.setText(rs.getString("Store_ID"));
          frmMain.pnlViewReceiptDetails.txtSupplier.setText(rs.getString("SUPP_NAME"));
          frmMain.pnlViewReceiptDetails.txtReceiptDate.setText(rs.getString("RECEIPT_DATE"));
          frmMain.pnlViewReceiptDetails.txtReceiptTo.setText(table.getValueAt(table.getSelectedRow(),3).toString());
          frmMain.pnlViewReceiptDetails.txtDescription.setText(rs.getString("DESCRIPTION"));
        }
        frmMain.pnlViewReceiptDetails.dm.resetIndexes();
        frmMain.pnlViewReceiptDetails.table.removeAll();
        frmMain.pnlViewReceiptDetails.table.setData(receiptBusObj.getData(String.valueOf(table.getValueAt(table.getSelectedRow(),0)),DAL));
        frmMain.pnlViewReceiptDetails.table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
        frmMain.pnlViewReceiptDetails.table.getTableHeader().setReorderingAllowed(false);
        frmMain.pnlViewReceiptDetails.table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
        frmMain.pnlViewReceiptDetails.table.setHeaderName(lang.getString("ItemID"),0);
        frmMain.pnlViewReceiptDetails.table.setHeaderName(lang.getString("UPC"),1);
        frmMain.pnlViewReceiptDetails.table.setHeaderName(lang.getString("ItemName"),2);
        frmMain.pnlViewReceiptDetails.table.setHeaderName(lang.getString("Size"),3);
        frmMain.pnlViewReceiptDetails.table.setHeaderName(lang.getString("Quantity"),4);
        frmMain.pnlViewReceiptDetails.table.setHeaderName(lang.getString("Reason"),5);
        frmMain.pnlViewReceiptDetails.table.setHeaderName(lang.getString("Season"),6);
//       frmMain.pnlViewReceiptDetails.table.setColumnWidth();
        frmMain.pnlViewReceiptDetails.table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
       // ut.changeDataTypetoLong(4,frmMain.pnlViewReceiptDetails.dm);//Yen.Dinh 19-06-2006
        stmt.close();
      }
      catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
     }



    //get selected PLID
    public String getReceiptID(){
      String receiptID="";
      if(table.getSelectedRow()!=-1){
        receiptID=table.getValueAt(table.getSelectedRow(),COL_RECEIPT_ID).toString();
      }
      return receiptID;
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

  void txtReceiptID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtReceiptID,Constant.LENGHT_PICK_LIST_ID);
    ut.checkNumber(e);
  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
  public void cboReceiver_keyPressed(KeyEvent e) {

  }

  public void btnDelete_actionPerformed(ActionEvent e) {
//    Vector vDelete = new Vector();
//    if(table.getSelectedRow()!=-1){
//      if(!table.getValueAt(table.getSelectedRow(),COL_STATUS).toString().equals(STATUS_WORKSHEET)){
//        ut.showMessage(frmMain,lang.getString("TT001"),"You can not delete the pick list in this status.");
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

  public void btnUPC_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0016"), true, frmMain,itemBusObject);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done){
      txtUPC.setText(dlgItemLookup.getUPC());
    }
  }
}

class DialogReceiptSearch_btnUPC_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogReceiptSearch adaptee;
  DialogReceiptSearch_btnUPC_actionAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnUPC_actionPerformed(e);
  }
}

class DialogReceiptSearch_btnDone_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogReceiptSearch adaptee;
  DialogReceiptSearch_btnDone_actionAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class DialogReceiptSearch_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogReceiptSearch adaptee;
  DialogReceiptSearch_btnSearch_actionAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class DialogReceiptSearch_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogReceiptSearch adaptee;
  DialogReceiptSearch_btnDelete_actionAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class DialogReceiptSearch_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogReceiptSearch adaptee;
  DialogReceiptSearch_btnCancel_actionAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogReceiptSearch_cboReceiver_keyAdapter
    extends KeyAdapter {
  private DialogReceiptSearch adaptee;
  DialogReceiptSearch_cboReceiver_keyAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cboReceiver_keyPressed(e);
  }
}

class DialogReceiptSearch_btnToDate_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogReceiptSearch adaptee;
  DialogReceiptSearch_btnToDate_actionAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnToDate_actionPerformed(e);
  }
}

class DialogReceiptSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
    DialogReceiptSearch adaptee;

    DialogReceiptSearch_txtRowLimit_keyAdapter(DialogReceiptSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtRowLimit_keyTyped(e);
    }
}

class DialogReceiptSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogReceiptSearch adaptee;

  DialogReceiptSearch_table_mouseAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogReceiptSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogReceiptSearch adaptee;

  DialogReceiptSearch_this_windowAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DialogReceiptSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogReceiptSearch adaptee;

  DialogReceiptSearch_this_keyAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}
class DialogReceiptSearch_btnFromDate_actionAdapter implements java.awt.event.ActionListener {
  DialogReceiptSearch adaptee;

  DialogReceiptSearch_btnFromDate_actionAdapter(DialogReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnFromDate_actionPerformed(e);
  }
}

