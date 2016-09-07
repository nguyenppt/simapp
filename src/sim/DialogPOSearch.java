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
import javax.swing.table.TableColumn;

/**
 * <p>Title: Search Purchase Order</p>
 * <p>Description: Search Purchase Order by Store, Supplier, OrderDate, ReceiptDate, Desc </p>
 * <p>Description: Main -> Inv Mgmt -> Purchase Order -> Search </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM </p>
 * @author Vinh Le
 * @version 1.0
 */

public class DialogPOSearch extends JDialog {
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
  private static final int COL_RECEIPT_FLAG = 6;

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
    JLabel lblStore = new JLabel("Promotion Name:");
    JLabel lblStartDate = new JLabel("Start Date:");
    JLabel lblEndDate = new JLabel("End Date:");
    JLabel lblRowLimit = new JLabel();//("Row Limit:");

    JTextField txtDesc = new JTextField();
    JTextField txtStore = new JTextField();
    JTextField txtOrderDate = new JTextField();
    JTextField txtReceiptDate = new JTextField();
    JTextField txtRowLimit = new JTextField();
    ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international


//-------------------------declare Panel  search result--------------------------//
    BJPanel pnlResultSearch = new BJPanel();
    JScrollPane jScrollPane1 = new JScrollPane();

//----------------------------------------------------------------------------------------------//
    FrameMainSim frmMain;
    DataAccessLayer DAL;
    PurchaseOrderBusObj poBusObj = new PurchaseOrderBusObj();
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
  JButton btnReceiptDate = new JButton();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  JButton btnStore = new JButton();
  JTextField txtSupplier = new JTextField();
  JButton btnSupplier = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JComboBox cboStatus = new JComboBox();
  JLabel lblPoID = new JLabel();
  JTextField txtPoID = new JTextField();
  JLabel jLabel3 = new JLabel();
  public DialogPOSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain, PurchaseOrderBusObj poBusObj) {
        super(frame, title, modal);
        try {
            this.frmMain = frmMain;
            this.poBusObj = poBusObj;
            DAL = frmMain.getDAL();
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public DialogPOSearch() {
        this(null, "", false,null,null);
    }
    private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    this.setSize(new Dimension(800, 600));
    this.addKeyListener(new DialogPOSearch_this_keyAdapter(this));
    this.addWindowListener(new DialogPOSearch_this_windowAdapter(this));
    this.getContentPane().setLayout(borderLayout1);

    pnlInfo.setPreferredSize(new Dimension(800, 100));
    pnlInfoLeft.setPreferredSize(new Dimension(400, 30));

    lblRowLimit.setText(lang.getString("RowsLimit")+":");

    pnlInfoLeftLabel.setPreferredSize(new Dimension(180, 30));
    pnlInfoLeftLabel.setLayout(flowLayout1);
    lblDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDesc.setPreferredSize(new Dimension(100, 22));
    lblDesc.setHorizontalAlignment(SwingConstants.LEFT);
    lblDesc.setText(lang.getString("Description")+":");
    lblStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStore.setPreferredSize(new Dimension(100, 22));
    lblStore.setHorizontalAlignment(SwingConstants.LEFT);
    lblStore.setText(lang.getString("ToStore")+":");
    lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblRowLimit.setPreferredSize(new Dimension(100, 22));
    pnlInfoLeftText.setPreferredSize(new Dimension(1, 29));
    pnlInfoLeftText.setLayout(flowLayout2);
    txtDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDesc.setPreferredSize(new Dimension(175, 22));
    txtDesc.setHorizontalAlignment(SwingConstants.LEADING);
    txtStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtStore.setPreferredSize(new Dimension(140, 22));
    txtStore.setRequestFocusEnabled(true);
    txtStore.setHorizontalAlignment(SwingConstants.LEADING);
    txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtRowLimit.setPreferredSize(new Dimension(60, 22));
    txtRowLimit.setText(DAL.getSearchLimit());
    txtRowLimit.setHorizontalAlignment(SwingConstants.LEADING);
    txtRowLimit.addKeyListener(new DialogPOSearch_txtRowLimit_keyAdapter(this));

    pnlInfoRight.setPreferredSize(new Dimension(400, 100));

    pnlInfoRightLabel.setPreferredSize(new Dimension(130, 30));
    pnlInfoRightLabel.setLayout(flowLayout3);
    lblStartDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStartDate.setPreferredSize(new Dimension(100, 22));
    lblStartDate.setText(lang.getString("OrderDate")+":");
    lblEndDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblEndDate.setPreferredSize(new Dimension(100, 22));
    lblEndDate.setText(lang.getString("ReceiptDate")+":");

    pnlInfoRightText.setPreferredSize(new Dimension(200, 100));
    pnlInfoRightText.setLayout(flowLayout4);
    txtOrderDate.setEnabled(true);
    txtOrderDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtOrderDate.setPreferredSize(new Dimension(140, 22));
    txtOrderDate.setEditable(true);
    txtOrderDate.addKeyListener(new DialogPOSearch_txtOrderDate_keyAdapter(this));
    txtReceiptDate.setBackground(Color.white);
    txtReceiptDate.setEnabled(true);
    txtReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtReceiptDate.setPreferredSize(new Dimension(140, 22));
    txtReceiptDate.addKeyListener(new DialogPOSearch_txtReceiptDate_keyAdapter(this));
    btnOrderDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnOrderDate.setPreferredSize(new Dimension(30, 22));
    btnOrderDate.setText("...");
    btnOrderDate.addActionListener(new
                                   DialogPOSearch_btnOrderDate_actionAdapter(this));
    btnReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnReceiptDate.setPreferredSize(new Dimension(30, 22));
    btnReceiptDate.setText("...");
    btnReceiptDate.addActionListener(new DialogPOSearch_btnReceiptDate_actionAdapter(this));

    pnlInfo.setLayout(borderLayout3);
    pnlInfoLeft.setLayout(borderLayout2);
    pnlInfoRight.setLayout(borderLayout4);
    pnlResultSearch.setOpaque(true);
    pnlResultSearch.setPreferredSize(new Dimension(800, 400));

    btnSearch.setPreferredSize(new Dimension(130, 37));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnSearch.addActionListener(new DialogPOSearch_btnSearch_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnDone.setPreferredSize(new Dimension(130, 37));
    btnDone.addActionListener(new DialogPOSearch_btnDone_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnCancel.setPreferredSize(new Dimension(130, 37));
    btnCancel.addActionListener(new DialogPOSearch_btnCancel_actionAdapter(this));
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.RIGHT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    flowLayout2.setAlignment(FlowLayout.LEFT);

    btnStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnStore.setPreferredSize(new Dimension(30, 22));
    btnStore.setText("...");
    btnStore.addActionListener(new DialogPOSearch_btnStore_actionAdapter(this));
    txtSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSupplier.setPreferredSize(new Dimension(140, 22));
    txtSupplier.setText("");
    btnSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSupplier.setPreferredSize(new Dimension(30, 22));
    btnSupplier.setText("...");
    btnSupplier.addActionListener(new DialogPOSearch_btnSupplier_actionAdapter(this));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(100, 22));
    jLabel1.setText(lang.getString("SupplierName")+":");
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(100, 22));
    jLabel2.setText(lang.getString("Status")+":");
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setPreferredSize(new Dimension(140, 22));
    lblPoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPoID.setPreferredSize(new Dimension(100, 20));
    lblPoID.setText(lang.getString("PO_ID")+":");
    txtPoID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPoID.setPreferredSize(new Dimension(175, 22));
    txtPoID.setText("");
    txtPoID.addKeyListener(new DialogPOSearch_txtPoID_keyAdapter(this));
    jLabel3.setPreferredSize(new Dimension(60, 15));
    jLabel3.setText("");
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
    table.addMouseListener(new DialogPOSearch_table_mouseAdapter(this));
    table.addKeyListener(new DialogPOSearch_table_keyAdapter(this));

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
    pnlInfoLeftLabel.add(lblPoID, null);
    pnlInfoLeftLabel.add(lblDesc, null);
    pnlInfoLeftLabel.add(lblStore, null);
    pnlInfoLeftLabel.add(jLabel1, null);
    pnlInfoLeftText.add(txtPoID, null);
    pnlInfoLeftText.add(txtDesc, null);
    pnlInfoLeftText.add(txtStore, null);
    pnlInfoRightLabel.add(lblStartDate, null);
    pnlInfoRightLabel.add(lblEndDate, null);
    pnlInfoRightLabel.add(jLabel2, null);
    pnlInfoRightLabel.add(lblRowLimit, null);
    pnlInfoRightText.add(txtOrderDate, null);
    pnlInfoRightText.add(btnOrderDate, null);
    pnlInfoRightText.add(txtReceiptDate, null);
    pnlInfoRightText.add(btnReceiptDate, null);
    pnlInfoRightText.add(cboStatus, null);
    jScrollPane1.setPreferredSize(new Dimension(452, 455));
    pnlResultSearch.setLayout(gridLayout1);


    jScrollPane1.getViewport().add(table, null);
    pnlInfoLeftText.add(btnStore, null);
    pnlInfoLeftText.add(txtSupplier, null);
    pnlInfoLeftText.add(btnSupplier, null);
    pnlInfoRightText.add(jLabel3, null);
    pnlInfoRightText.add(txtRowLimit, null);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation( (screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);

    txtDesc.setRequestFocusEnabled(true);
    initScreen();
    cboStatus.addKeyListener(new DialogPOSearch_cboStatus_keyAdapter(this));
    }
    void initScreen(){
      cboStatus.addItem("All");
      cboStatus.addItem("Approve");
      cboStatus.addItem("Work Sheet");
      cboStatus.addItem("Complete");
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
      if(!ut.checkDate(txtReceiptDate.getText().trim(),"/") && ! txtReceiptDate.getText().trim().equals("")){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1013_ReceiptDateMustBeDateType"));
        txtReceiptDate.requestFocus();
        txtReceiptDate.setSelectionStart(0);
        txtReceiptDate.setSelectionEnd(txtReceiptDate.getText().length());
        return;
      }
       if (txtOrderDate.getText().trim().length()>0 && txtReceiptDate.getText().trim().length()>0 ){
         if (ut.compareDate(txtOrderDate.getText(), txtReceiptDate.getText())) {
           ut.showMessage(frmMain, lang.getString("TT001"),
                          lang.getString("MS1014_OrderDateMustBeBeforeReceiptDate"));
           return;
         }
       }
       if (txtRowLimit.getText().equals("")) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1015_InputLimitedNumberSearchingRecords"));
            return;
        }
        rowsNum = Integer.parseInt(txtRowLimit.getText());
//        if (rowsNum > 50) {
//            ut.showMessage(frmMain, "Warning!",
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
           rs = getStatus(txtPoID.getText(),txtDesc.getText(), txtStore.getText(),txtSupplier.getText() ,
                                 txtOrderDate.getText(),txtReceiptDate.getText(), rowsNum,stmt);
           vStatus.clear();
           while(rs.next()){
             vStatus.addElement(rs.getString("STATUS"));
          }
          table.setData(getData(txtPoID.getText(),txtDesc.getText(), txtStore.getText(),txtSupplier.getText() ,
                                 txtOrderDate.getText(),txtReceiptDate.getText(), rowsNum,stmt));
           stmt.close();
      }catch(Exception ex){};
      table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
      table.getTableHeader().setReorderingAllowed(false);
      table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
      table.setHeaderName(lang.getString("PO_ID"), COL_PO_ID);
      table.setHeaderName(lang.getString("PODesc"), COL_PO_DESC);
      table.setHeaderName(lang.getString("OrderDate"), COL_ORDER_DATE);
      table.setHeaderName(lang.getString("ReceiptDate"), COL_RECEIPT_DATE);
      table.setHeaderName(lang.getString("Store"), COL_STORE);
      table.setHeaderName(lang.getString("SupplierName"), COL_SUPPLIER);
      table.setHeaderName(lang.getString("ReceiptFlag"), COL_RECEIPT_FLAG);
      table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
      //    //hide the FrontR column
        TableColumn column3 = new TableColumn();
        column3 = table.getColumn(lang.getString("ReceiptFlag"));
        column3.setMaxWidth(0);
        column3.setMinWidth(0);
        column3.setPreferredWidth(0);

     }
     private ResultSet getData(String poID, String desc, String storeName, String suppName, String orderDate,String receiptDate, int rows, Statement stmt) {
       ResultSet rs = null;
       try {
         String sqlStr = "Select inv.Order_Id PO_Id, inv.Comment_Desc PO_Desc, to_char(inv.Ordered_Date,'DD/MM/YYYY') Order_Date,"
             + " to_char(inv.Received_Date,'DD/MM/YYYY') Receipt_Date, st.Name Store_Name, sup.Supp_Name Supp_Name, decode(inv.FR_BR_FLAG,'B','Backroom','Frontroom') FR_BR_FLAG"
             + " From BTM_IM_INV_ORDER inv, BTM_IM_STORE st, BTM_IM_SUPPLIER sup"
             + " Where inv.Store_Id = st.Store_Id and inv.Supp_Id = sup.Supp_Id and rownum <=" +  rows;
         String sql = "";
         String status=cboStatus.getSelectedItem().toString();
         if(!status.equalsIgnoreCase("All")){
            sql += " and inv.Status = '" + status.substring(0,1) + "'";
         }
         if (poID.trim().length() > 0) {
           sql += " and inv.Order_Id like '%" + poID + "%'";
         }
         if (desc.trim().length() > 0) {
           sql += " and lower(inv.Comment_Desc) like lower('%" + desc + "%')";
         }
         if (storeName.trim().length() > 0) {
           sql += " and lower(st.Name) like lower('%" + storeName + "%')";
         }
         if (suppName.trim().length() > 0) {
           sql += " and lower(sup.Supp_Name) like lower('%" + suppName + "%')";
         }

         if (!orderDate.trim().equals("")){
           sql += " and inv.Ordered_Date >= to_date('" + orderDate + "','DD/MM/YYYY')";
         }
         if (!receiptDate.trim().equals("")){
               sql += " and inv.Received_Date <= to_date('" + receiptDate + "','DD/MM/YYYY')";
             }
         sqlStr += sql + " Order By inv.Order_Id";
//         System.out.println(sqlStr);
         rs = DAL.executeScrollQueries(sqlStr,stmt);
       }
       catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }
       return rs;
     }
     private ResultSet getStatus(String poID,String desc, String storeName, String suppName, String orderDate,String receiptDate, int rows, Statement stmt) {
       ResultSet rs = null;
       try {
         String sqlStr = "Select inv.Status"
             + " From BTM_IM_INV_ORDER inv, BTM_IM_STORE st, BTM_IM_SUPPLIER sup"
             + " Where inv.Store_Id = st.Store_Id and inv.Supp_Id = sup.Supp_Id and rownum <=" +  rows;
         String sql = "";
         String status=cboStatus.getSelectedItem().toString();
         if(!status.equalsIgnoreCase("All")){
            sql += " and inv.Status = '" + status.substring(0,1) + "'";
         }
         if (poID.trim().length() > 0) {
           sql += " and inv.Order_Id like '%" + poID + "%'";
         }

         if (desc.trim().length() > 0) {
           sql += " and lower(inv.Comment_Desc) like lower('%" + desc + "%')";
         }
         if (storeName.trim().length() > 0) {
           sql += " and lower(st.Name) like lower('%" + storeName + "%')";
         }
         if (suppName.trim().length() > 0) {
           sql += " and lower(sup.Supp_Name) like lower('%" + suppName + "%')";
         }

         if (!orderDate.trim().equals("")){
           sql += " and inv.Ordered_Date >= to_date('" + orderDate + "','DD/MM/YYYY')";
         }
         if (!receiptDate.trim().equals("")){
               sql += " and inv.Received_Date <= to_date('" + receiptDate + "','DD/MM/YYYY')";
             }
         sqlStr += sql + " Order By inv.Order_Id";
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
       frmMain.pnlPurchaseOrderModify.btnDone.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.btnAdd.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.btnDelete.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.btnClear.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.btnPrint.setEnabled(!flag);
       frmMain.pnlPurchaseOrderModify.tblPurchaseOrder.setEnabled(flag);
//       frmMain.pnlPurchaseOrderModify.cboStore.setEnabled(flag);
//       frmMain.pnlPurchaseOrderModify.btnStore.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.txtOrderDate.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.btnOrderDate.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.txtReceiptDate.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.btnReceiptDate.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.cboStatus.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.txtTerm.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.txtDesc.setEnabled(flag);
       frmMain.pnlPurchaseOrderModify.txtQty.setEnabled(flag);
       if(flag==false){
         frmMain.pnlPurchaseOrderModify.txtUPC.setEnabled(flag);
         frmMain.pnlPurchaseOrderModify.btnUPC.setEnabled(flag);
         frmMain.pnlPurchaseOrderModify.txtProdGroup.setEnabled(flag);
         frmMain.pnlPurchaseOrderModify.btnProdGroup.setEnabled(flag);
       }
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
            frmMain.poBusObj.setData(   txtPoID.getText(),
                                        txtStore.getText(),
                                        txtSupplier.getText(),
                                        txtOrderDate.getText(),
                                        txtReceiptDate.getText(),
                                        cboStatus.getSelectedItem().toString(),
                                        txtDesc.getText(),
                                        rowsNum, dm.getDataVector(),
                                        vStatus,
                                        table.getSelectedRow());
            String poID = table.getValueAt(table.getSelectedRow(),
                                              COL_PO_ID).toString();
            frmMain.pnlPurchaseOrderModify.setDataToModify(poID);
            if(vStatus.get(table.getSelectedRow()).toString().equalsIgnoreCase("C") ||
               vStatus.get(table.getSelectedRow()).toString().equalsIgnoreCase("A")){
              disableObjectModify(false); // only view Purchase Order, not allow to modify
            }else{
              disableObjectModify(true); // allow to modify
            }
            done = true;
            this.dispose();
          }
          else {
            ut.showMessage(frmMain, lang.getString("MS1016_PurchaseOrderSearch"),
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
    void updateScreen(){
      txtPoID.setText(frmMain.poBusObj.po_ID);
      txtDesc.setText(frmMain.poBusObj.desc);
      txtStore.setText(frmMain.poBusObj.store_Name);
      txtSupplier.setText(frmMain.poBusObj.supp_Name);
      txtOrderDate.setText(frmMain.poBusObj.order_Date);
      txtReceiptDate.setText(frmMain.poBusObj.receipt_Date);
      cboStatus.setSelectedItem(frmMain.poBusObj.status);
      txtRowLimit.setText(String.valueOf(frmMain.poBusObj.rowlimit));
      vStatus = frmMain.poBusObj.vStatus;
      setDataVector(frmMain.poBusObj.vData);
    }
    void updateRowOnTable(String arrInfo[]){
      ResultSet rs = null;
      try{
           stmt = DAL.getConnection().createStatement(ResultSet.
                                       TYPE_SCROLL_INSENSITIVE,
                                       ResultSet.CONCUR_READ_ONLY);
           rs = getStatusUpdate(table.getValueAt(frmMain.poBusObj.rowSelected,0).toString(),stmt);
           if(rs.next()){
             vStatus.remove(frmMain.poBusObj.rowSelected);
             vStatus.insertElementAt(rs.getString("STATUS"),frmMain.poBusObj.rowSelected);
           }
          stmt.close();
      }catch(Exception ex){};

      table.setValueAt(arrInfo[0],frmMain.poBusObj.rowSelected,COL_PO_DESC);
      table.setValueAt(arrInfo[1],frmMain.poBusObj.rowSelected,COL_ORDER_DATE);
      table.setValueAt(arrInfo[2],frmMain.poBusObj.rowSelected,COL_RECEIPT_DATE);
      table.setValueAt(arrInfo[3],frmMain.poBusObj.rowSelected,COL_STORE);
    }

    void setDataVector(Vector vData){
      Vector vColumns = new Vector();
      vColumns.add("PO ID");
      vColumns.add("PO Desc");
      vColumns.add("Order Date");
      vColumns.add("Receipt Date");
      vColumns.add("Store");
      vColumns.add("Supplier");
//      vColumns.add("Receipt Flag");
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
 btnReceiptDate.requestFocus(true);

  }

  public void btnReceiptDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnReceiptDate.getX() + posXFrame + 150;
    posY = this.btnReceiptDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtReceiptDate.setText(date);
    }
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

  void btnStore_actionPerformed(ActionEvent e) {
    DialogStoreSearch dlgStoreSearch = new DialogStoreSearch(frmMain,
           lang.getString("TT0004"), true, frmMain, frmMain.storeBusObj);
       dlgStoreSearch.setVisible(true);
       if (dlgStoreSearch.done) {
         txtStore.setText(dlgStoreSearch.getStoreName());
       }
  }

  void btnSupplier_actionPerformed(ActionEvent e) {
      SupplierBusObj suppBusObj = new SupplierBusObj();
      DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(frmMain,lang.getString("TT0013"),true,frmMain,suppBusObj);
      dlgSupplierSearch.setVisible(true);
      if (dlgSupplierSearch.done){
        txtSupplier.setText(dlgSupplierSearch.getSuppName());
      }
  }

  void txtPoID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtPoID,PurchaseOrderBusObj.LEN_PO_ID);
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
}

class DialogPOSearch_btnReceiptDate_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogPOSearch adaptee;
  DialogPOSearch_btnReceiptDate_actionAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceiptDate_actionPerformed(e);
  }
}

class DialogPOSearch_btnOrderDate_actionAdapter
    implements java.awt.event.ActionListener {
  private DialogPOSearch adaptee;
  DialogPOSearch_btnOrderDate_actionAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnOrderDate_actionPerformed(e);
  }
}

class DialogPOSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
    DialogPOSearch adaptee;

    DialogPOSearch_txtRowLimit_keyAdapter(DialogPOSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtRowLimit_keyTyped(e);
    }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRowLimit_keyPressed(e);
  }
}

class DialogPOSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
    DialogPOSearch adaptee;

    DialogPOSearch_btnCancel_actionAdapter(DialogPOSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnCancel_actionPerformed(e);
    }
}

class DialogPOSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
    DialogPOSearch adaptee;

    DialogPOSearch_btnSearch_actionAdapter(DialogPOSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnSearch_actionPerformed(e);
    }
}

class DialogPOSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogPOSearch adaptee;

  DialogPOSearch_table_mouseAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogPOSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogPOSearch adaptee;

  DialogPOSearch_table_keyAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogPOSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogPOSearch adaptee;

  DialogPOSearch_this_windowAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DialogPOSearch_btnDone_actionAdapter implements java.awt.event.ActionListener {
  DialogPOSearch adaptee;

  DialogPOSearch_btnDone_actionAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class DialogPOSearch_btnStore_actionAdapter implements java.awt.event.ActionListener {
  DialogPOSearch adaptee;

  DialogPOSearch_btnStore_actionAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnStore_actionPerformed(e);
  }
}

class DialogPOSearch_btnSupplier_actionAdapter implements java.awt.event.ActionListener {
  DialogPOSearch adaptee;

  DialogPOSearch_btnSupplier_actionAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplier_actionPerformed(e);
  }
}

class DialogPOSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogPOSearch adaptee;

  DialogPOSearch_this_keyAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogPOSearch_txtPoID_keyAdapter extends java.awt.event.KeyAdapter {
  DialogPOSearch adaptee;

  DialogPOSearch_txtPoID_keyAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPoID_keyTyped(e);
  }
}

class DialogPOSearch_txtOrderDate_keyAdapter extends java.awt.event.KeyAdapter {
  DialogPOSearch adaptee;

  DialogPOSearch_txtOrderDate_keyAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtOrderDate_keyPressed(e);
  }
}

class DialogPOSearch_txtReceiptDate_keyAdapter extends java.awt.event.KeyAdapter {
  DialogPOSearch adaptee;

  DialogPOSearch_txtReceiptDate_keyAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtReceiptDate_keyPressed(e);
  }
}

class DialogPOSearch_cboStatus_keyAdapter extends java.awt.event.KeyAdapter {
  DialogPOSearch adaptee;

  DialogPOSearch_cboStatus_keyAdapter(DialogPOSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboStatus_keyPressed(e);
  }
}
