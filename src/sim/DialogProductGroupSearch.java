

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
 * <p>Title: Search Purchase Order</p>
 * <p>Description: Search Purchase Order by Store, Supplier, OrderDate, ReceiptDate, Desc </p>
 * <p>Description: Main -> Inv Mgmt -> Product Group -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM </p>
 * @author Vinh Le
 * @version 1.0
 */

public class DialogProductGroupSearch extends JDialog {
//  public static final String STATUS_ALL   = "All";
  public static final String STATUS_ACTIVE   = "Active";
  public static final String STATUS_CANCEL = "Cancel";
  public static final String TYPE_PICK_LIST = "Pick List";
  public static final String TYPE_PO = "PO";
  public static final String TYPE_STOCK_COUNT = "Stock Count";

  //My variable
  public int FLAG_SCR=0;
  public boolean bDone=false;
  public String POID="";
  //Assign column name to the position of column of showed grid
  private static final int COL_PO_ID        = 0;
  private static final int COL_PO_DESC      = 1;
  private static final int COL_ORDER_DATE   = 2;
  private static final int COL_RECEIPT_DATE = 3;
  private static final int COL_STORE        = 4;
  private static final int COL_SUPPLIER     = 5;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

  Statement stmt = null;
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
    BJButton btnCancel = new BJButton();

//-------------------------declare Panel information--------------------------//
    BJPanel pnlInfo = new BJPanel();
    BJPanel pnlInfoLeft = new BJPanel();
    BJPanel pnlInfoRight = new BJPanel();
    BJPanel pnlInfoLeftLabel = new BJPanel();
    BJPanel pnlInfoLeftText = new BJPanel();

    BJPanel pnlInfoRightLabel = new BJPanel();
    BJPanel pnlInfoRightText = new BJPanel();

    JLabel lblDesc = new JLabel("Promotion ID:");
//    JLabel lblStore = new JLabel("Promotion Name:");
    JLabel lblStartDate = new JLabel("Start Date:");
    JLabel lblType = new JLabel("End Date:");
    JLabel lblRowLimit = new JLabel();//("Row Limit:");

    JTextField txtDesc = new JTextField();
//    JTextField txtStore = new JTextField();
    JTextField txtOrderDate = new JTextField();
//    JTextField txtReceiptDate = new JTextField();
    JComboBox cboStatus = new JComboBox();
    JTextField txtRowLimit = new JTextField();


//-------------------------declare Panel  search result--------------------------//
    BJPanel pnlResultSearch = new BJPanel();
    JScrollPane jScrollPane1 = new JScrollPane();

//----------------------------------------------------------------------------------------------//
    FrameMainSim frmMain;
    DataAccessLayer DAL;
    ProdGroupBusObj pgBusObj = new ProdGroupBusObj();
    Utils ut = new Utils();
    Vector vStatus = new Vector();
    int parentScreen = 0;
    SortableTableModel dm = new SortableTableModel();

    BJTable table = new BJTable(dm, true) {
        public Class getColumnClass(int col) {
            switch (col) {
                case COL_PO_ID:
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
  JButton btnOrderDate = new JButton();
//  JButton btnReceiptDate = new JButton();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
//  JButton btnStore = new JButton();
  JTextField txtSupplier = new JTextField();
  JButton btnSupplier = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel lblStatus = new JLabel();
  JComboBox cboType = new JComboBox();
  JLabel lblPGID = new JLabel();
  JTextField txtPoID = new JTextField();
//  JLabel jLabel3 = new JLabel();
  public DialogProductGroupSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain, ProdGroupBusObj pgBusObj) {
        super(frame, title, modal);
        try {
            this.frmMain = frmMain;
            this.pgBusObj = pgBusObj;
            DAL = frmMain.getDAL();
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public DialogProductGroupSearch() {
        this(null, "", false,null,null);
    }
    private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    this.setSize(new Dimension(800, 600));
    this.addKeyListener(new DialogProductGroupSearch_this_keyAdapter(this));
    this.addWindowListener(new DialogProductGroupSearch_this_windowAdapter(this));
    this.getContentPane().setLayout(borderLayout1);

    pnlInfo.setPreferredSize(new Dimension(800, 100));
    pnlInfoLeft.setPreferredSize(new Dimension(400, 30));

    lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    lblRowLimit.setText(lang.getString("RowsLimit")+":");

    pnlInfoLeftLabel.setPreferredSize(new Dimension(180, 30));
    pnlInfoLeftLabel.setLayout(flowLayout1);
    lblDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDesc.setPreferredSize(new Dimension(100, 22));
    lblDesc.setHorizontalAlignment(SwingConstants.LEFT);
    lblDesc.setText(lang.getString("Description")+":");
//    lblStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    lblStore.setPreferredSize(new Dimension(100, 22));
//    lblStore.setHorizontalAlignment(SwingConstants.LEFT);
//    lblStore.setText("To Store:");
    lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblRowLimit.setPreferredSize(new Dimension(100, 20));
    pnlInfoLeftText.setPreferredSize(new Dimension(1, 29));
    pnlInfoLeftText.setLayout(flowLayout2);
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDesc.setPreferredSize(new Dimension(175, 22));
    txtDesc.setHorizontalAlignment(SwingConstants.LEADING);
//    txtStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    txtStore.setPreferredSize(new Dimension(140, 20));
//    txtStore.setRequestFocusEnabled(true);
//    txtStore.setHorizontalAlignment(SwingConstants.LEADING);
    txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtRowLimit.setPreferredSize(new Dimension(60, 22));
    txtRowLimit.setText(DAL.getSearchLimit());
    txtRowLimit.setHorizontalAlignment(SwingConstants.LEADING);
    txtRowLimit.addKeyListener(new DialogProductGroupSearch_txtRowLimit_keyAdapter(this));

    pnlInfoRight.setPreferredSize(new Dimension(400, 100));

    pnlInfoRightLabel.setPreferredSize(new Dimension(130, 30));
    pnlInfoRightLabel.setLayout(flowLayout3);
    lblStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStartDate.setPreferredSize(new Dimension(100, 20));
    lblStartDate.setText(lang.getString("CreatedDate")+":");
    lblType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblType.setPreferredSize(new Dimension(100, 20));
    lblType.setText(lang.getString("Type")+":");

    pnlInfoRightText.setPreferredSize(new Dimension(200, 100));
    pnlInfoRightText.setLayout(flowLayout4);
    txtOrderDate.setEnabled(true);
    txtOrderDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtOrderDate.setPreferredSize(new Dimension(140, 20));
    txtOrderDate.setEditable(true);
    txtOrderDate.addKeyListener(new DialogProductGroupSearch_txtOrderDate_keyAdapter(this));
//    txtReceiptDate.setBackground(Color.white);
//    txtReceiptDate.setEnabled(true);
//    txtReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    txtReceiptDate.setPreferredSize(new Dimension(140, 20));
//    txtReceiptDate.addKeyListener(new DialogProductGroupSearch_txtReceiptDate_keyAdapter(this));
    btnOrderDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnOrderDate.setPreferredSize(new Dimension(30, 20));
    btnOrderDate.setText("...");
    btnOrderDate.addActionListener(new
                                   DialogProductGroupSearch_btnOrderDate_actionAdapter(this));
//    btnReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    btnReceiptDate.setPreferredSize(new Dimension(30, 20));
//    btnReceiptDate.setText("...");
//    btnReceiptDate.addActionListener(new DialogProductGroupSearch_btnReceiptDate_actionAdapter(this));

    pnlInfo.setLayout(borderLayout3);
    pnlInfoLeft.setLayout(borderLayout2);
    pnlInfoRight.setLayout(borderLayout4);
    pnlResultSearch.setOpaque(true);
    pnlResultSearch.setPreferredSize(new Dimension(800, 400));

    btnSearch.setPreferredSize(new Dimension(130, 37));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.addActionListener(new DialogProductGroupSearch_btnSearch_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(130, 37));
    btnDone.addActionListener(new DialogProductGroupSearch_btnDone_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(130, 37));
    btnCancel.addActionListener(new DialogProductGroupSearch_btnCancel_actionAdapter(this));
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    flowLayout2.setAlignment(FlowLayout.LEFT);

//    btnStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnStore.setPreferredSize(new Dimension(30, 22));
//    btnStore.setText("...");
//    btnStore.addActionListener(new DialogProductGroupSearch_btnStore_actionAdapter(this));
    txtSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSupplier.setPreferredSize(new Dimension(140, 22));
    txtSupplier.setText("");
    btnSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSupplier.setPreferredSize(new Dimension(30, 22));
    btnSupplier.setText("...");
    btnSupplier.addActionListener(new DialogProductGroupSearch_btnSupplier_actionAdapter(this));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(100, 22));
    jLabel1.setText(lang.getString("SupplierName")+":");
    lblStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStatus.setPreferredSize(new Dimension(100, 20));
    lblStatus.setText(lang.getString("Status")+":");
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setPreferredSize(new Dimension(140, 20));
    cboType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboType.setPreferredSize(new Dimension(140, 20));
    cboType.addItemListener(new DialogProductGroupSearch_cboType_itemAdapter(this));

    lblPGID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPGID.setPreferredSize(new Dimension(100, 20));
    lblPGID.setText(lang.getString("PG_ID")+":");
    txtPoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPoID.setPreferredSize(new Dimension(175, 22));
    txtPoID.setText("");
    txtPoID.addKeyListener(new DialogProductGroupSearch_txtPoID_keyAdapter(this));
//    jLabel3.setPreferredSize(new Dimension(60, 15));
//    jLabel3.setText("");
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
    table.addMouseListener(new DialogProductGroupSearch_table_mouseAdapter(this));
    table.addKeyListener(new DialogProductGroupSearch_table_keyAdapter(this));

//-------Hearder
    pnlHeader.setPreferredSize(new Dimension(800, 47));

    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F3</html>");
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");

    pnlHeader.add(btnDone, null);
    pnlHeader.add(btnSearch, null);
    pnlHeader.add(btnCancel, null);
    pnlInfoLeftLabel.add(lblPGID, null);
    pnlInfoLeftLabel.add(lblDesc, null);
    pnlInfoLeftLabel.add(jLabel1, null);
    pnlInfoLeftLabel.add(lblRowLimit, null);
    pnlInfoLeftText.add(txtPoID, null);
    pnlInfoLeftText.add(txtDesc, null);
    pnlInfoLeftText.add(txtSupplier, null);
    pnlInfoLeftText.add(btnSupplier, null);
    pnlInfoLeftText.add(txtRowLimit, null);
    pnlInfoRightLabel.add(lblStartDate, null);
    pnlInfoRightLabel.add(lblType, null);
    pnlInfoRightLabel.add(lblStatus, null);
//    pnlInfoRightLabel.add(lblRowLimit, null);
    pnlInfoRightText.add(txtOrderDate, null);
    pnlInfoRightText.add(btnOrderDate, null);
    pnlInfoRightText.add(cboType, null);
//    pnlInfoRightText.add(btnReceiptDate, null);
    pnlInfoRightText.add(cboStatus, null);
    jScrollPane1.setPreferredSize(new Dimension(452, 455));
    pnlResultSearch.setLayout(gridLayout1);


    jScrollPane1.getViewport().add(table, null);
//    pnlInfoLeftText.add(btnStore, null);
//    pnlInfoRightText.add(jLabel3, null);
//    pnlInfoRightText.add(txtRowLimit, null);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation( (screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);

    txtDesc.setRequestFocusEnabled(true);
    initScreen();
    cboStatus.addKeyListener(new DialogProductGroupSearch_cboStatus_keyAdapter(this));
    }
    void initScreen(){
//      cboType.addItem(TYPE_PICK_LIST);
      cboType.addItem(TYPE_PO);
      cboType.addItem(TYPE_STOCK_COUNT);
      cboType.setSelectedIndex(0);
      txtSupplier.setEditable(false);
//      cboStatus.addItem(STATUS_ALL);
      cboStatus.addItem(STATUS_ACTIVE);
      cboStatus.addItem(STATUS_CANCEL);
      cboStatus.setSelectedIndex(0);
    }
    public void applyPermission() {
       EmpRight er = new EmpRight();
       er.initData(DAL, DAL.getEmpID());
       er.setData(DAL.getEmpID(), Constant.SCRS_ADMIN_ROLE);
       btnSearch.setEnabled(er.getAdd());
       btnDone.setEnabled(er.getAdd());
     }

    void txtRowLimit_keyTyped(KeyEvent e) {
//        ut.limitLenjTextField(e, txtRowLimit, 2);
        ut.checkNumber(e);
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          btnSearch.doClick();
        }
    }

    void btnCancel_actionPerformed(ActionEvent e) {
      done=false;
      this.dispose();
    }

     void btnSearch_actionPerformed(ActionEvent e) {
       if (!ut.checkDate(txtOrderDate.getText().trim(), "/") && ! txtOrderDate.getText().trim().equals("")){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1012_OrderDateMustBeDateType"));
        txtOrderDate.requestFocus();
        txtOrderDate.setSelectionStart(0);
        txtOrderDate.setSelectionEnd(txtOrderDate.getText().length());
        return;
      }
//      if(!ut.checkDate(txtReceiptDate.getText().trim(),"/") && ! txtReceiptDate.getText().trim().equals("")){
//        ut.showMessage(frmMain,lang.getString("TT001"), "Receipt date must be a date type");
//        txtReceiptDate.requestFocus();
//        txtReceiptDate.setSelectionStart(0);
//        txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
//        return;
//      }
//       if (txtOrderDate.getText().trim().length()>0 && txtReceiptDate.getText().trim().length()>0 ){
//         if (ut.compareDate(txtOrderDate.getText(), txtReceiptDate.getText())) {
//           ut.showMessage(frmMain, lang.getString("TT001"),
//                          "Order date must be before receipt date ");
//           return;
//         }
//       }
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
      ResultSet rs = null;
      dm.resetIndexes();
      try{
           stmt = DAL.getConnection().createStatement(ResultSet.
                                       TYPE_SCROLL_INSENSITIVE,
                                       ResultSet.CONCUR_READ_ONLY);
           rs = getStatus(txtPoID.getText(),txtDesc.getText(), "",txtSupplier.getText() ,
                                 txtOrderDate.getText(), rowsNum,stmt);
           vStatus.clear();
           while(rs.next()){
             vStatus.addElement(rs.getString("STATUS"));
          }
          table.setData(getData(txtPoID.getText(),txtDesc.getText(),txtSupplier.getText() ,
                                 txtOrderDate.getText(), rowsNum,stmt));
           stmt.close();
      }catch(Exception ex){};
      table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
      table.getTableHeader().setReorderingAllowed(false);
      table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
      table.setHeaderName(lang.getString("PG_ID"), COL_PO_ID);
      table.setHeaderName(lang.getString("PG_Desc"), COL_PO_DESC);
      table.setHeaderName(lang.getString("CreateDate"), COL_ORDER_DATE);
//      table.setHeaderName("Receipt Date", COL_RECEIPT_DATE);
//      table.setHeaderName("Store", COL_STORE);
      table.setHeaderName(lang.getString("SupplierID"), COL_SUPPLIER);
      table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
     }
     private ResultSet getData(String poID, String desc,  String suppName, String createDate, int rows, Statement stmt) {
       ResultSet rs = null;
       try {
         String sqlStr ="";

         //only picklist Prod Group has supplier
         if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
           sqlStr = "Select inv.PROD_GROUP_ID PG_Id, inv.DESCRIPTION PG_Desc, to_char(inv.CREATE_DATE,'DD/MM/YYYY') CREATE_DATE,"
             + "   sup.Supp_Name Supp_Name"
             + " From BTM_IM_PROD_GROUP inv, BTM_IM_SUPPLIER sup"
             + " Where  inv.Supp_Id = sup.Supp_Id and rownum <=" +  rows;

         }else{
           sqlStr = "Select inv.PROD_GROUP_ID PG_Id, inv.DESCRIPTION PG_Desc, to_char(inv.CREATE_DATE,'DD/MM/YYYY') CREATE_DATE,"
             + "   '' Supp_Name"
             + " From BTM_IM_PROD_GROUP inv"
             + " Where  rownum <=" +  rows;

         }
         String sql = "";
         String status="", type=  "";
         if(cboType.getSelectedItem().toString().equals(TYPE_PICK_LIST)){
           type = "P";
         }
         else if (cboType.getSelectedItem().toString().equals(TYPE_PO)) {
           type = "O";
         }
         else if (cboType.getSelectedItem().toString().equals(TYPE_STOCK_COUNT)) {
           type = "S";
         }
         if (cboStatus.getSelectedItem().toString().equals(STATUS_ACTIVE)) {
           status = "A";
         }
         else if (cboStatus.getSelectedItem().toString().equals(STATUS_CANCEL)) {
           status = "C";
         }

         sql += " and inv.Type = '" + type + "'";
         sql += " and inv.Status = '" + status + "'";

         if (poID.trim().length() > 0) {
           sql += " and inv.PROD_GROUP_ID like '%" + poID + "%'";
         }
         if (desc.trim().length() > 0) {
           sql += " and lower(inv.DESCRIPTION) like lower('%" + desc + "%')";
         }
//         if (storeName.trim().length() > 0) {
//           sql += " and lower(st.Name) like lower('%" + storeName + "%')";
//         }
         if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
           if (suppName.trim().length() > 0) {
             sql += " and lower(sup.Supp_Name) like lower('%" + suppName + "%')";
           }
         }
         if (!createDate.trim().equals("")){
           sql += " and inv.CREATE_DATE = to_date('" + createDate + "','DD/MM/YYYY')";
         }
//         if (!receiptDate.trim().equals("")){
//               sql += " and inv.Received_Date <= to_date('" + receiptDate + "','DD/MM/YYYY')";
//             }
         sqlStr += sql + " Order By inv.PROD_GROUP_ID";
//         System.out.println(sqlStr);
         rs = DAL.executeScrollQueries(sqlStr,stmt);
       }
       catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }
       return rs;
     }
     //Get Status for all record
     private ResultSet getStatus(String poID,String desc, String storeName, String suppName, String createDate, int rows, Statement stmt) {
       ResultSet rs = null;
       try {
         String sqlStr = "Select inv.Status"
             + " From BTM_IM_PROD_GROUP inv, BTM_IM_SUPPLIER sup"
             + " Where  inv.Supp_Id = sup.Supp_Id and rownum <=" +  rows;

         //only picklist Prod Group has supplier
         if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
           sqlStr = "Select inv.Status"
             + " From BTM_IM_PROD_GROUP inv, BTM_IM_SUPPLIER sup"
             + " Where  inv.Supp_Id = sup.Supp_Id and rownum <=" +  rows;

         }else{
           sqlStr = "Select inv.Status"
              + " From BTM_IM_PROD_GROUP inv"
              + " Where  rownum <=" +  rows;

         }

         String sql = "";
         String status="", type=  "";
         if(cboType.getSelectedItem().toString().equals(TYPE_PICK_LIST)){
           type = "P";
         }
         else if (cboType.getSelectedItem().toString().equals(TYPE_PO)) {
           type = "O";
         }
         else if (cboType.getSelectedItem().toString().equals(TYPE_STOCK_COUNT)) {
           type = "S";
         }
         if (cboStatus.getSelectedItem().toString().equals(STATUS_ACTIVE)) {
           status = "A";
         }
         else if (cboStatus.getSelectedItem().toString().equals(STATUS_CANCEL)) {
           status = "C";
         }

         sql += " and inv.Type = '" + type + "'";
         sql += " and inv.Status = '" + status + "'";

         if (poID.trim().length() > 0) {
           sql += " and inv.PROD_GROUP_ID like '%" + poID + "%'";
         }
         if (desc.trim().length() > 0) {
           sql += " and lower(inv.DESCRIPTION) like lower('%" + desc + "%')";
         }
//         if (storeName.trim().length() > 0) {
//           sql += " and lower(st.Name) like lower('%" + storeName + "%')";
//         }
         if(cboType.getSelectedItem().toString().equals(TYPE_PO)){

           if (suppName.trim().length() > 0) {
             sql += " and lower(sup.Supp_Name) like lower('%" + suppName + "%')";
           }
         }
         if (!createDate.trim().equals("")){
           sql += " and inv.CREATE_DATE = to_date('" + createDate + "','DD/MM/YYYY')";
         }
//         if (!receiptDate.trim().equals("")){
//               sql += " and inv.Received_Date <= to_date('" + receiptDate + "','DD/MM/YYYY')";
//             }
         sqlStr += sql + " Order By inv.PROD_GROUP_ID";
//         System.out.println(sqlStr);
         rs = DAL.executeScrollQueries(sqlStr,stmt);
       }
       catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }
       return rs;
     }
     private ResultSet getStatusUpdate(String poID,Statement stmt) {
       ResultSet rs = null;
       try {
         String sqlStr = "Select inv.Status"
             + " From BTM_IM_INV_ORDER inv"
             + " Where inv.Order_Id = '" + poID + "'";
//         System.out.println(sqlStr);
         rs = DAL.executeScrollQueries(sqlStr,stmt);
       }
       catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }
       return rs;
     }


     void disableObjectModify(boolean flag){
       frmMain.pnlProdGroupModify.btnDone.setEnabled(flag);
       frmMain.pnlProdGroupModify.btnAdd.setEnabled(flag);
       frmMain.pnlProdGroupModify.btnDelete.setEnabled(flag);
       frmMain.pnlProdGroupModify.btnClear.setEnabled(flag);
//       frmMain.pnlProdGroupModify.btnPrint.setEnabled(!flag);
       frmMain.pnlProdGroupModify.tblProdGroup.setEnabled(flag);
//       frmMain.pnlProdGroupModify.cboStore.setEnabled(flag);
//       frmMain.pnlProdGroupModify.btnStore.setEnabled(flag);
//       frmMain.pnlProdGroupModify.txtOrderDate.setEnabled(flag);
//       frmMain.pnlProdGroupModify.btnOrderDate.setEnabled(flag);
//       frmMain.pnlProdGroupModify.txtReceiptDate.setEnabled(flag);
//       frmMain.pnlProdGroupModify.btnReceiptDate.setEnabled(flag);
       frmMain.pnlProdGroupModify.cboStatus.setEnabled(flag);
//       frmMain.pnlProdGroupModify.txtTerm.setEnabled(flag);
       frmMain.pnlProdGroupModify.txtDesc.setEnabled(flag);
//       frmMain.pnlProdGroupModify.txtQty.setEnabled(flag);
       frmMain.pnlProdGroupModify.txtUPC.setEnabled(flag);
       frmMain.pnlProdGroupModify.btnUPC.setEnabled(flag);
     }

    void table_mouseClicked(MouseEvent e) {
      if (FLAG_SCR == 1) {
        bDone = true;
        if (table.getSelectedRow() != -1) {
          POID = table.getValueAt(table.getSelectedRow(), 0).toString();
        }
        this.dispose();
      }else{
        if (e.getClickCount() == 2) {
          if (this.table.getSelectedRow() >= 0) {
            frmMain.pgBusObj.setData(   txtPoID.getText(),
                                        "",
                                        txtSupplier.getText(),
                                        txtOrderDate.getText(),
                                        cboType.getSelectedItem().toString(),
                                        cboStatus.getSelectedItem().toString(),
                                        txtDesc.getText(),
                                        rowsNum, dm.getDataVector(),
                                        vStatus,
                                        table.getSelectedRow());
            String poID = table.getValueAt(table.getSelectedRow(),
                                              COL_PO_ID).toString();
            frmMain.pnlProdGroupModify.setDataToModify(poID);
//            if(vStatus.get(table.getSelectedRow()).toString().equalsIgnoreCase("C") ||
//               vStatus.get(table.getSelectedRow()).toString().equalsIgnoreCase("A")){
//              disableObjectModify(false); // only view Purchase Order, not allow to modify
//            }else{
              disableObjectModify(true); // allow to modify
//            }
            done = true;
            this.dispose();
          }
          else {
            ut.showMessage(frmMain, lang.getString("MS1017_ProductGroupSearch"),
                           lang.getString("MS1002_YouShouldChooseData"));
            return;
          }
        }
      }
    }
    //get selected POID
    public String getPoID(){
      String poID="";
      if(table.getSelectedRow()!=-1){
        poID=table.getValueAt(table.getSelectedRow(),COL_PO_ID).toString();
      }
      return poID;
    }

    //get selected PG IP
    public String getPGID(){
      String pgID="";
      if(table.getSelectedRow()!=-1){
        pgID=table.getValueAt(table.getSelectedRow(),COL_PO_ID).toString();
      }
      return pgID;
    }


    void updateScreen(){
      txtPoID.setText(frmMain.pgBusObj.pg_ID);
      txtDesc.setText(frmMain.pgBusObj.desc);
//      txtStore.setText(frmMain.poBusObj.store_Name);
      txtSupplier.setText(frmMain.pgBusObj.supp_Name);
      txtOrderDate.setText(frmMain.pgBusObj.created_Date);
//      txtReceiptDate.setText(frmMain.poBusObj.receipt_Date);
      cboType.setSelectedItem(frmMain.pgBusObj.type);
      cboStatus.setSelectedItem(frmMain.pgBusObj.status);
      txtRowLimit.setText(String.valueOf(frmMain.pgBusObj.rowlimit));
      vStatus = frmMain.pgBusObj.vStatus;
      setDataVector(frmMain.pgBusObj.vData);
    }
    void updateRowOnTable(String arrInfo[]){
      ResultSet rs = null;
      try{
           stmt = DAL.getConnection().createStatement(ResultSet.
                                       TYPE_SCROLL_INSENSITIVE,
                                       ResultSet.CONCUR_READ_ONLY);
           rs = getStatusUpdate(table.getValueAt(frmMain.pgBusObj.rowSelected,0).toString(),stmt);
           if(rs.next()){
             vStatus.remove(frmMain.pgBusObj.rowSelected);
             vStatus.insertElementAt(rs.getString("STATUS"),frmMain.pgBusObj.rowSelected);
           }
          stmt.close();
      }catch(Exception ex){};

      table.setValueAt(arrInfo[0],frmMain.pgBusObj.rowSelected,COL_PO_DESC);
      table.setValueAt(arrInfo[1],frmMain.pgBusObj.rowSelected,COL_ORDER_DATE);
      table.setValueAt(arrInfo[2],frmMain.pgBusObj.rowSelected,COL_RECEIPT_DATE);
      table.setValueAt(arrInfo[3],frmMain.poBusObj.rowSelected,COL_STORE);
    }

    void setDataVector(Vector vData){
      Vector vColumns = new Vector();
      vColumns.add("PG ID");
      vColumns.add("PG Desc");
      vColumns.add("Created Date");
//      vColumns.add("Receipt Date");
//      vColumns.add("Store");
      vColumns.add("Supplier");
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
                    }else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                      btnCancel.doClick();
                    }
                  }
                }
      private void catchHotKey(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F1) {
          btnDone.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_F3) {
          btnSearch.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_F12 ||
                 e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }

      }
  void txtStore_keyPressed(KeyEvent e) {
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
    txtDesc.requestFocus(true);
  }

  public void btnOrderDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnOrderDate.getX() + posXFrame + 150;
    posY = this.btnOrderDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

if (endDate.isOKPressed()) {
  java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
      "dd/MM/yyyy");
  String date = fd.format(endDate.getDate());
  this.txtOrderDate.setText(date);
}
// btnReceiptDate.requestFocus(true);

  }

  public void btnReceiptDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
//    posX = this.btnReceiptDate.getX() + posXFrame + 150;
//    posY = this.btnReceiptDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
    endDate.setResizable(false);
//    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

//    if (endDate.isOKPressed()) {
//      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
//      String date = fd.format(endDate.getDate());
//      this.txtReceiptDate.setText(date);
//    }
    btnSearch.requestFocus(true);
  }

  void txtDesc_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtDesc,PromotionBusObj.LEN_PROMO_ID);
    if (e.getKeyChar() < e.VK_0 || e.getKeyChar() > e.VK_9){
      e.consume();
    }
    navigationComp(e,txtOrderDate);
  }

  void btnDone_actionPerformed(ActionEvent e) {
    if (FLAG_SCR==1){
       bDone=true;
      if(table.getSelectedRow()!=-1){
        POID=table.getValueAt(table.getSelectedRow(),0).toString();
      }
      this.dispose();
    }
  }

//  void btnStore_actionPerformed(ActionEvent e) {
//    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,
//           "JSIM - STORE SEARCH", true, frmMain, frmMain.storeBusObj);
//       dlgStoreSearch.setVisible(true);
//       if (dlgStoreSearch.done) {
//         txtStore.setText(dlgStoreSearch.getStoreName());
//       }
//  }

  void btnSupplier_actionPerformed(ActionEvent e) {
      SupplierBusObj suppBusObj = new SupplierBusObj();
      DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(frmMain,lang.getString("TT0013"),true,frmMain,suppBusObj);
      dlgSupplierSearch.setVisible(true);
      if (dlgSupplierSearch.done){
        txtSupplier.setText(dlgSupplierSearch.getSuppName());
      }
  }

  void txtPoID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtPoID,ProdGroupBusObj.LEN_PO_ID);
    if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
       (e.getKeyChar() != e.VK_BACK_SPACE))
     e.consume();
  }

  void txtOrderDate_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtReceiptDate_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void cboStatus_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  public void cboType_itemStateChanged(ItemEvent e) {
    if(cboType.getSelectedItem().toString().equals(TYPE_PO)){
      txtSupplier.setEditable(true);
      btnSupplier.setEnabled(true);
    }else{
      txtSupplier.setText("");
      txtSupplier.setEditable(false);
      btnSupplier.setEnabled(false);
    }

  }
}

class DialogProductGroupSearch_cboType_itemAdapter
    implements ItemListener {
  private DialogProductGroupSearch adaptee;
  DialogProductGroupSearch_cboType_itemAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.cboType_itemStateChanged(e);
  }
}

class DialogProductGroupSearch_btnReceiptDate_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogProductGroupSearch adaptee;
  DialogProductGroupSearch_btnReceiptDate_actionAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceiptDate_actionPerformed(e);
  }
}

class DialogProductGroupSearch_btnOrderDate_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogProductGroupSearch adaptee;
  DialogProductGroupSearch_btnOrderDate_actionAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnOrderDate_actionPerformed(e);
  }
}

class DialogProductGroupSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
    DialogProductGroupSearch adaptee;

    DialogProductGroupSearch_txtRowLimit_keyAdapter(DialogProductGroupSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtRowLimit_keyTyped(e);
    }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRowLimit_keyPressed(e);
  }
}

class DialogProductGroupSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
    DialogProductGroupSearch adaptee;

    DialogProductGroupSearch_btnCancel_actionAdapter(DialogProductGroupSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnCancel_actionPerformed(e);
    }
}

class DialogProductGroupSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
    DialogProductGroupSearch adaptee;

    DialogProductGroupSearch_btnSearch_actionAdapter(DialogProductGroupSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnSearch_actionPerformed(e);
    }
}

class DialogProductGroupSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_table_mouseAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogProductGroupSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_table_keyAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogProductGroupSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_this_windowAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DialogProductGroupSearch_btnDone_actionAdapter implements java.awt.event.ActionListener {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_btnDone_actionAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

//class DialogProductGroupSearch_btnStore_actionAdapter implements java.awt.event.ActionListener {
//  DialogProductGroupSearch adaptee;
//
//  DialogProductGroupSearch_btnStore_actionAdapter(DialogProductGroupSearch adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnStore_actionPerformed(e);
//  }
//}

class DialogProductGroupSearch_btnSupplier_actionAdapter implements java.awt.event.ActionListener {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_btnSupplier_actionAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplier_actionPerformed(e);
  }
}

class DialogProductGroupSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_this_keyAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogProductGroupSearch_txtPoID_keyAdapter extends java.awt.event.KeyAdapter {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_txtPoID_keyAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPoID_keyTyped(e);
  }
}

class DialogProductGroupSearch_txtOrderDate_keyAdapter extends java.awt.event.KeyAdapter {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_txtOrderDate_keyAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtOrderDate_keyPressed(e);
  }
}

class DialogProductGroupSearch_txtReceiptDate_keyAdapter extends java.awt.event.KeyAdapter {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_txtReceiptDate_keyAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtReceiptDate_keyPressed(e);
  }
}

class DialogProductGroupSearch_cboStatus_keyAdapter extends java.awt.event.KeyAdapter {
  DialogProductGroupSearch adaptee;

  DialogProductGroupSearch_cboStatus_keyAdapter(DialogProductGroupSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboStatus_keyPressed(e);
  }
}


