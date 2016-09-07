package pos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import common.*;
import btm.swing.*;
import java.util.Vector;
import java.text.*;
import java.util.*;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description:  (Error on Grid)</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelReturnItem
    extends JPanel {
  private static final int RI_OK_BTN = 224;

  String temp = "";
  FrameMain frmMain;
  Vector vSql = new Vector();
  Vector vSql1 = new Vector();
  Statement stmt = null;
  int flagButton = RI_OK_BTN; //dertermine button on screen.
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  DataAccessLayer DAL; //= new DataAccessLayer();
  BorderLayout borderLayout1 = new BorderLayout();
//  SortableTableModel dm = new SortableTableModel();
  JPanel pnlMain = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlCenter = new JPanel();
  JLabel lblTransID = new JLabel(lang.getString("TransactionNo") +":");
  JLabel lblDate = new JLabel(lang.getString("Date") +":");
  JLabel lblType = new JLabel(lang.getString("Type") +":");
  JLabel lblStoreTrans = new JLabel(lang.getString("StoreName") +":");
  JLabel lblOperator = new JLabel(lang.getString("Operator") +":");
  JLabel lblReason = new JLabel(lang.getString("ReasonToReturn"));
  JTextField txtTransID = new JTextField();
  JTextField txtDate = new JTextField();
  JTextField txtType = new JTextField();
  JTextField txtStoreTrans = new JTextField();
  JTextField txtOperator = new JTextField();
  BJComboBox comReason = new BJComboBox();

  GridLayout gridLayout2 = new GridLayout();
  JPanel pnlText = new JPanel();
  JPanel pnlTableData = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
//  int rowCount = 0;
  //declare component for pnlData
  BJTable jTable2;
  SortableTableModel dm2 = new SortableTableModel() {
    public Class getColumnClass(int col) {
      switch (col) {
        case 5:
          return Double.class;
        case 6:
          return Double.class;
        case 7:
          return Double.class;
        case 8:
          return Double.class;
        default:
          return Object.class;
      }
    }
  };
  BJButton btnOK = new BJButton();
  BJButton btnBack = new BJButton();
  BJButton btnSelectAll = new BJButton();
  BJButton btnClearAll = new BJButton();
  BJButton btnReturnOK = new BJButton();
  BJButton btnReturnCancel = new BJButton();
  //Code at 10_3_2005
  //Column Order of Sale Item Table
  private static final int COL_CHECK_BOX = 0; //
  private static final int COL_ITEM_ID = 1; //
  private static final int COL_UPC = 2;
  private static final int COL_ITEM_DESC = 3; //
  private static final int COL_SIZE = 4;
  private static final int COL_QUANTITY = 5; //
  private static final int COL_PRICE = 6;
  private static final int COL_MARKDOWN = 7;
  private static final int COL_AMOUNT = 8;

  //Declare variable in transaction detail table
  float tender_id = 0;
  float promo_id = 0;
  float store_id = 0;
  String cust_id = "";
  float register_id = 0;
  float amount = 0;
  double unit_qty = 0;
  Date trans_date = null;
  String trans_datetime = "";
  float disc_amt = 0;
  String trans_type_id = "";
  float markdown = 0;
  String mkdn_disc_reason = "";
  float org_unit_price = 0;
  String account_no = "";
  Date exp_date = null;
  Date creditCardExpire = null;
  //Set data quantity
  double[] dataUnitQty;
  //flag condition
  boolean flagDone = false;
  boolean flagSetHotkey = true;
  boolean flagSelectAll = true;

  public PanelReturnItem(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOK.setToolTipText(lang.getString("OK") +" (F1)");
    btnOK.setText(lang.getString("OK") +" (F1)");
    btnOK.addActionListener(new PanelReturnItem_btnOK_actionAdapter(this));
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnBack.setToolTipText(lang.getString("Back") +" (F12)");
    btnBack.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnBack.addActionListener(new PanelReturnItem_btnBack_actionAdapter(this));
    btnSelectAll.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSelectAll.setToolTipText(lang.getString("SelectAll") +" (F1)");
    btnSelectAll.setText(lang.getString("SelectAll") +" (F1)");
    btnSelectAll.addActionListener(new
                                   PanelReturnItem_btnSelectAll_actionAdapter(this));
    btnClearAll.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnClearAll.setToolTipText(lang.getString("ClearAll") +"(F1)");
    btnClearAll.setText(lang.getString("ClearAll") +"(F1)");
    btnClearAll.addActionListener(new PanelReturnItem_btnClearAll_actionAdapter(this));
    btnReturnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnReturnOK.setToolTipText(lang.getString("OK") +" (F2)");
    btnReturnOK.setText(lang.getString("OK") +" (F2)");
    btnReturnOK.addActionListener(new PanelReturnItem_btnReturnOK_actionAdapter(this));
    btnReturnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnReturnCancel.setToolTipText(lang.getString("Cancel") +" (F3)");
    btnReturnCancel.setText(lang.getString("Cancel") +" (F3)");
    btnReturnCancel.addActionListener(new
        PanelReturnItem_btnReturnCancel_actionAdapter(this));

    pnlMain.setBackground(new Color(55, 194, 255));
    pnlMain.setLayout(borderLayout2);
    pnlCenter.setBackground(SystemColor.control);
    pnlCenter.setMinimumSize(new Dimension(10, 10));
    pnlCenter.setPreferredSize(new Dimension(660, 60));
    pnlCenter.setLayout(flowLayout1);

    pnlText.setBackground(SystemColor.control);
    pnlText.setAlignmentY( (float) 0.5);
    pnlText.setMinimumSize(new Dimension(10, 10));
    pnlText.setPreferredSize(new Dimension(660, 160));
    pnlText.setRequestFocusEnabled(true);
    pnlText.setLayout(new BorderLayout());

    lblTransID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTransID.setPreferredSize(new Dimension(100, 21));
    lblDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblDate.setPreferredSize(new Dimension(100, 21));
    lblType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblType.setPreferredSize(new Dimension(100, 21));
    lblStoreTrans.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStoreTrans.setPreferredSize(new Dimension(100, 21));
    lblOperator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblOperator.setPreferredSize(new Dimension(100, 21));
    lblReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReason.setPreferredSize(new Dimension(100, 21));
    lblReason.setText(lang.getString("ReasonToReturn") +":");
    txtTransID.setBackground(SystemColor.control);
    txtTransID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtTransID.setAlignmentY( (float) 0.5);
    txtTransID.setPreferredSize(new Dimension(240, 21));
    txtTransID.setCaretPosition(0);
    txtTransID.setDisabledTextColor(Color.black);
    txtTransID.setEditable(false);
    txtDate.setBackground(SystemColor.control);
    txtDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtDate.setPreferredSize(new Dimension(240, 21));
    txtDate.setEditable(false);
    txtType.setBackground(SystemColor.control);
    txtType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtType.setPreferredSize(new Dimension(240, 21));
    txtType.setEditable(false);
    txtStoreTrans.setBackground(SystemColor.control);
    txtStoreTrans.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtStoreTrans.setPreferredSize(new Dimension(240, 21));
    txtStoreTrans.setEditable(false);
    txtOperator.setBackground(SystemColor.control);
    txtOperator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtOperator.setDoubleBuffered(false);
    txtOperator.setPreferredSize(new Dimension(240, 21));
    txtOperator.setEditable(false);
    comReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    comReason.setPreferredSize(new Dimension(240, 21));
    ResultSet rs = null;
    rs = getComboData(stmt);
    comReason.setData(rs);
    rs.getStatement().close();
    comReason.addActionListener(new PanelReturnItem_comReason_actionAdapter(this));
    jPanel6.setBackground(SystemColor.control);
    jPanel6.setPreferredSize(new Dimension(60, 31));
    jPanel2.setBackground(SystemColor.control);
    jPanel5.setBackground(SystemColor.control);
//    jTable2.setPreferredSize(new Dimension(0, 0));
    this.setBackground(SystemColor.control);
    pnlTableData.setBackground(SystemColor.control);
    frmMain.getContentPane().setBackground(Color.lightGray);
    jPanel2.add(lblTransID, null);
    jPanel2.add(lblDate, null);
    jPanel2.add(lblType, null);
    jPanel2.add(lblStoreTrans, null);
    jPanel2.add(lblOperator, null);
    jPanel2.add(lblReason, null);

    jPanel6.add(txtTransID, null);
    jPanel6.add(txtDate, null);
    jPanel6.add(txtType, null);
    jPanel6.add(txtStoreTrans, null);
    jPanel6.add(txtOperator, null);
    jPanel6.add(comReason, null);

    //Create Constructor for pnlData

//    dm2.addColumn("Return");
//    dm2.addColumn("Item ID");
//    dm2.addColumn("Item Description");
//    dm2.addColumn("Quantity");
//    dm2.addColumn("Unit Price");
//    dm2.addColumn("Markdown");
//    dm2.addColumn("Amount Due");

    jTable2 = new BJTable(dm2) {
      public void tableChanged(TableModelEvent e) {
        super.tableChanged(e);
        repaint();
      }

      public boolean isCellEditable(int row, int col) {
        if (col == COL_CHECK_BOX) {
          return true;
        }
        if (col == COL_QUANTITY) {
          if (dataUnitQty[row] <= 0) {
            return false;
          }
          else {
            return true;
          }
        }
        return false;
      }
    };
//    jTable2.setRowHeight(30);
    jTable2.addMouseListener(new PanelReturnItem_jTable2_mouseAdapter(this));
    JScrollPane jsp2 = new JScrollPane(jTable2);
    jsp2.setPreferredSize(new Dimension(650, 307));
    jsp2.setBackground(SystemColor.control);
    jsp2.setViewportView(jTable2);

    jPanel2.setPreferredSize(new Dimension(120, 10));
    jPanel5.setPreferredSize(new Dimension(290, 10));
    this.add(pnlMain, BorderLayout.CENTER);
    pnlMain.add(pnlCenter, BorderLayout.CENTER);

    pnlCenter.add(pnlText, null);
    pnlText.add(jPanel2, BorderLayout.WEST);
    pnlText.add(jPanel5, BorderLayout.EAST);
    pnlText.add(jPanel6, BorderLayout.CENTER);

    pnlTableData.setLayout(new FlowLayout());
    pnlTableData.setPreferredSize(new Dimension(660, 357));
    pnlTableData.add(jsp2, null);
    pnlCenter.add(pnlTableData, null);
//    jTable2.getTableHeader().setReorderingAllowed(false);
//    jTable2.getTableHeader().setResizingAllowed(false);
//    jTable2.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
//    jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_ALL_COLUMNS);
  }

  void setTable() {
    JTableHeader header = new JTableHeader();
    header = jTable2.getTableHeader();
    header.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    jTable2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jTable2.setTableHeader(header);



    TableColumn column = new TableColumn();
    column = jTable2.getColumn(lang.getString("ItemID"));
    column.setMaxWidth(50);
    column.setMinWidth(10);
    TableColumn column1 = new TableColumn();
    column1 = jTable2.getColumn(lang.getString("Quantity"));
    column1.setMaxWidth(80);
    column1.setMinWidth(10);
    TableColumn column2 = new TableColumn();
    column2 = jTable2.getColumn(lang.getString("UnitPrice"));
    column2.setMaxWidth(80);
    column2.setMinWidth(10);
    TableColumn column3 = new TableColumn();
    column3 = jTable2.getColumn(lang.getString("MarkDown"));
    column3.setMaxWidth(80);
    column3.setMinWidth(10);
    TableColumn column4 = new TableColumn();
    column4 = jTable2.getColumn(lang.getString("AmountDue"));
    column4.setMaxWidth(150);
    column4.setMinWidth(90);
    TableColumn column5 = new TableColumn();
    column5 = jTable2.getColumn(lang.getString("Return"));
    column5.setMaxWidth(50);
    column5.setMinWidth(10);


//    String[] columnNames = new String[]{"","Item code",
//        "Item Description","Quantity","Unit Price","Markdown","Amount Due"
//    };
//    dm.setDataVector(new Object[][]{},columnNames);
//    jTable2.getColumn("Item code").setPreferredWidth(80);
//    jTable2.getColumn("Item Description").setPreferredWidth(200);
  }

  //get reason from database
  ResultSet getComboData(Statement stmt) {
    ResultSet rs = null;
    try {
//      System.out.println(
//          "select reason_id, reason_desc from BTM_POS_REASON where reason_type='R'");
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(
          "select reason_id, reason_desc from BTM_POS_REASON where reason_type='R'",
          stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  //show data in overview
  void showData(String trans_id) {
    ResultSet rs = null;
    try {
      String sql = "select trunc(trans_date) trans_date, trans_id, trans_desc, d.store_id, emp_id, trans_flag, " +
// not show first name
//          " Case when cust_flag='0' then first_name " +
//          " else concat(first_name,concat(' ',last_name)) end cust_name, c.work_no, c.cust_id " +
          " concat(first_name,concat(' ',last_name)) cust_name, c.work_no, c.cust_id " +
          " from BTM_POS_DETAIL_TRANS d, BTM_POS_TRANS_TYPE t, BTM_POS_CUSTOMER c " +
          " where d.trans_type_id = t.trans_type_id and trans_id ='" + trans_id +
          "'" +
          " and d.cust_id=c.cust_id";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        if (rs.getString("trans_flag").equalsIgnoreCase("4")) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS046_TransVoid"));
          stmt.close();
          return;
        }
        txtTransID.setText("" + rs.getLong("trans_id"));
        txtDate.setText("" + rs.getDate("trans_date"));
        txtType.setText(rs.getString("trans_desc"));
        txtStoreTrans.setText("" + rs.getLong("store_id"));
        txtOperator.setText(rs.getString("emp_id"));
        frmMain.lblName.setText(lang.getString("Name") +": " + rs.getString("cust_name"));
        frmMain.lblPhone.setText(lang.getString("Phone") +": " + rs.getString("work_no"));
        frmMain.pnlNewSale.custID = rs.getLong("cust_id");
        stmt.close();
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS042_TranNotExist"));
        txtTransID.setText("");
        txtDate.setText("");
        txtType.setText("");
        txtStoreTrans.setText("");
        txtOperator.setText("");
        frmMain.lblName.setText("");
        frmMain.lblPhone.setText("");
        stmt.close();
        return;
      }
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /* get new markdown of item to show on table
   * @author Vinh.Le
   * @param : tran_id, item_id, old_markdown and new_Qty
   * @return : double markdown
   */

  //Show data in detail
  void showData() {

    String trans_id = txtTransID.getText();
    ResultSet rs = null;
    try {
      while (dm2.getRowCount() > 0) {
        dm2.removeRow(0);
      }
      String sql = " select item_id, art_no,item_desc,attr2_name, unit_qty, unit_price, markdown*unit_qty,unit_qty*(unit_price-markdown)  " +
          " from " +
          "(select d.item_id, art_no,item_desc,attr2_name, unit_qty, unit_price, markdown, amount " +
          " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i " +
          " where d.item_id = i.item_id and trans_id = '" + trans_id +
          "' and d.item_id not in (select item_id from BTM_POS_RETURN_ITEM where trans_id ='" +
          trans_id + "')" +
          " and trans_flag <> 4 " +

          " union all " +

          " select d1.item_id, art_no,item_desc,attr2_name, (d1.unit_qty - r.return_qty) unit_qty, unit_price, markdown, amount " +
          " from BTM_POS_DETAIL_TRANS d1, BTM_POS_ITEM_PRICE i , " +
          " (select trans_id, item_id, sum(return_qty) return_qty " +
          " from BTM_POS_RETURN_ITEM " +
          " where trans_id ='" + trans_id + "' " +
          " group by trans_id , item_id) r " +
          " where d1.trans_id = r.trans_id and d1.item_id = r.item_id " +
          " and d1.item_id = i.item_id " +
          " and d1.unit_qty > r.return_qty and d1.trans_id = '" + trans_id +
          "')";
//System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql, stmt);
      BJCheckBox check = new BJCheckBox();
      jTable2.setData(rs, check, 1);
      stmt.close();
      jTable2.setColumnWidth(COL_ITEM_ID, 95);
      jTable2.setColumnWidth(COL_UPC, 60);
      jTable2.setColumnWidth(COL_ITEM_DESC, 140);
      jTable2.setColumnWidth(COL_SIZE, 50);
      jTable2.setColumnWidth(COL_QUANTITY, 60);
      jTable2.setColumnWidth(COL_PRICE, 75);
      jTable2.setColumnWidth(COL_MARKDOWN, 75);
      jTable2.setColumnWidth(COL_AMOUNT, 80);

      jTable2.setHeaderName(lang.getString("ItemID"), COL_ITEM_ID);
      jTable2.setHeaderName(lang.getString("UPC"), COL_UPC);
      jTable2.setHeaderName(lang.getString("ItemName"), COL_ITEM_DESC);
      jTable2.setHeaderName(lang.getString("Size"), COL_SIZE);
      jTable2.setHeaderName(lang.getString("Quantity"), COL_QUANTITY);
      jTable2.setHeaderName(lang.getString("UnitPrice"), COL_PRICE);
      jTable2.setHeaderName(lang.getString("MarkDown"), COL_MARKDOWN);
      jTable2.setHeaderName(lang.getString("AmountDue"), COL_AMOUNT);

      dataUnitQty = new double[jTable2.getRowCount()];
      for (int i = 0; i < jTable2.getRowCount(); i++) {
        dataUnitQty[i] = Double.parseDouble("" + jTable2.getValueAt(i, COL_QUANTITY));
      }
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void setSelectedTable() {
    jTable2.selectAll();
  }

  void deselectedTable() {
    jTable2.clearSelection();
  }

  void selectAll() {
    JCheckBox check;
    for (int i = 0; i < jTable2.getRowCount(); i++) {
      check = (JCheckBox) jTable2.getValueAt(i, COL_CHECK_BOX);
      check.setSelected(true);
    }
    setSelectedTable();
    deselectedTable();
  }

  void clearAll() {
    JCheckBox check;
    for (int i = 0; i < jTable2.getRowCount(); i++) {
      check = (JCheckBox) jTable2.getValueAt(i, COL_CHECK_BOX);
      check.setSelected(false);
    }
    setSelectedTable();
    deselectedTable();
  }

  //Check selected
  boolean checkSelected(int row) {
    JCheckBox jCheckbox;
    jCheckbox = (JCheckBox) jTable2.getValueAt(row, COL_CHECK_BOX);
    if (jCheckbox.isSelected()) {
      return true;
    }
    return false;
  }

  void jTable2_mouseClicked(MouseEvent e) {
//    System.out.print("BBB");
  }

  // get trans_detail_seq
  int getTransDetailSeq(String transID) {
    int transDetailSeq = 0;
    ResultSet rs = null;
    try {
      String sql = "select seq_no from btm_pos_detail_trans where trans_id ='" +
          transID + "' order by seq_no desc";
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        transDetailSeq = rs.getInt("seq_no");
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return transDetailSeq;
  }

  //Add the return item into database: BTM_POS_DETAIL_TRANS, BTM_POS_PAID_IN_OUT,BTM_POS_RETURN_ITEM
  // BTM_POS_DETAIL_TRANS: Trans_id: old Trans_id, BTM_POS_PAID_IN_OUT: update Paid_out.
  void addReturn() {

    String transID = txtTransID.getText();
    boolean flag = false;
    boolean flagCheck = false;

    //clear vector
    vSql.clear();
    vSql1.clear();

    for (int i = 0; i < jTable2.getRowCount(); i++) {
      String item_id = jTable2.getValueAt(i, COL_ITEM_ID).toString();
      String unit_qty1 = "" + jTable2.getValueAt(i, COL_QUANTITY).toString();
      getOldData(transID, item_id);
      if (Double.parseDouble(ut.formatNumber(String.valueOf(unit_qty))) < Double.parseDouble(unit_qty1)) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS134_CanNotReturn") + " " + (i + 1) + " " +
                       lang.getString("MS135_CanNotReturn"));
        flagCheck = true;
        flagDone = false;
        break;
      }
      else if ( Double.parseDouble(unit_qty1) < 0) {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS135_CanNotReturn") + " " + (i + 1) + " " +
                       lang.getString("MS136_CanNotLessThan0"));
        flagCheck = true;
        flagDone = false;
        break;
      }
    }
    if (flagCheck == false) {
      frmMain.pnlNewSale.flagReturn = Constant.RETURN_ITEM;
      frmMain.pnlNewSale.removeRow();
      int transDetailSeq = getTransDetailSeq(transID);
//      System.out.println("TransDetailSeq: " + transDetailSeq);
      for (int i = 0; i < jTable2.getRowCount(); i++) {
        if (checkSelected(i)) {
          try {
            flag = false;
            String item_id = jTable2.getValueAt(i, COL_ITEM_ID).toString();
            getOldData(transID, item_id);
            String item_desc = jTable2.getValueAt(i, COL_ITEM_DESC).toString();
            double unit_qty1 = -Double.parseDouble("" + jTable2.getValueAt(i, COL_QUANTITY));
            //add 10_3_2006
            String sArtNo = jTable2.getValueAt(i, COL_UPC).toString();
            String sSize="";
            if(jTable2.getValueAt(i, COL_SIZE)!=null) {
              sSize = jTable2.getValueAt(i, COL_SIZE).toString();
            }
            double amount_due = (unit_qty1 * org_unit_price) -
                (markdown * unit_qty1);

//            System.out.println("amount due" + amount_due);
            Object[] item = new Object[] {
                item_id, sArtNo,item_desc, sSize,new Double(unit_qty1),
                ut.formatNumber("" + org_unit_price),
                ut.formatNumber("" + markdown*unit_qty1),ut.formatNumber("" + ut.getRound ( (markdown/ org_unit_price) * 100,2 ) ), ut.formatNumber("" + amount_due)
            };
            transDetailSeq = transDetailSeq + 1;
//            System.out.println("Increase the trans detail seq: " +
//                               transDetailSeq);
//            System.out.println(
//                "Insert into BTM_POS_DETAIL_TRANS values(" + transDetailSeq +
//                ", " +
//                "'" + transID + "'," + tender_id + ",'" +
//                    frmMain.getTransaction().getEmpl_ID() +
//                DAL.getEmpPosID1() +
//                "'," + promo_id + ",'" + item_id + "'," + store_id + ",'" +
//                cust_id +
//                "'," + register_id + "," + amount_due + "," + unit_qty1 +
//                ",to_date('" + trans_datetime + "','" + ut.DATETIME_FORMAT2 +
//                "')," + disc_amt +
//                ",'" + trans_type_id + "'," + markdown + ",'" +
//                mkdn_disc_reason +
//                "'," + org_unit_price + ",'" + account_no + "',to_date('7777-7-7','YYYY-MM-DD'),1,0,'',to_Date('" + creditCardExpire +
//                "','yyyy-MM-dd'))"
//                );
            if (DAL.getFranchiseCust().length() == 13) {
              cust_id=DAL.getFranchiseCust();
            }

            vSql.addElement(
                "Insert into BTM_POS_DETAIL_TRANS values(" + transDetailSeq +
                ", " +
                "'" + transID + "'," + tender_id + ",'" +
//                    frmMain.getTransaction().getEmpl_ID() +
                DAL.getEmpPosID1() +
                "'," + promo_id + ",'" + item_id + "'," + store_id + ",'" +
                cust_id +
                "'," + register_id + "," + amount_due + "," + unit_qty1 +
                ",to_date('" + trans_datetime + "','" + ut.DATETIME_FORMAT2 +
                "')," + disc_amt +
                ",'" + trans_type_id + "'," + markdown + ",'" +
                mkdn_disc_reason +
                "'," + org_unit_price + ",'" + account_no + "',to_date('7777-7-7','YYYY-MM-DD'),1,0,'',to_Date('" + creditCardExpire +
                "','yyyy-MM-dd'),'-1')"
                );

            vSql1.addElement(
                "Insert into BTM_POS_RETURN_ITEM values('" + transID +
                "','" + item_id + "'," + comReason.getSelectedData() +
                ",to_date('" + trans_datetime + "','" + ut.DATETIME_FORMAT2 +
                "')," +
                -1 * unit_qty1 + ",'" +
//                frmMain.getTransaction().getEmpl_ID() +
                DAL.getEmpPosID1() +
                "'," + promo_id + "," + tender_id + ", return_seq.nextval,0," +
                "to_date('7777-7-7','YYYY-MM-DD'))"
                );
            setReturnItem(vSql, vSql1);
            frmMain.pnlNewSale.addItemRow(item);
            flag = true;
          }
          catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
            flag = false;
          }
        }
      }
    }
    if (flag == true) {
//      JOptionPane.showMessageDialog(jOptionPane, "Return item is succesfully!");
      removeAllThing();
      flagDone = true;
    }
  }

  //set return item
  void setReturnItem(Vector vSql, Vector vSql1) {
    this.vSql = vSql;
    this.vSql1 = vSql1;
  }

  //get return Item
  Vector getReturnItem() {
    return this.vSql;
  }

  //get old data of trans_id for returning
  void getOldData(String transID, String itemId) {
    ResultSet rs = null;
    try {
//      System.out.println(
//          "Select distinct tender_id, promo_id, store_id, cust_id, register_id, " +
//          " amount, unit_qty, trunc(trans_date) trans_date, disc_amt, trans_type_id, markdown, " +
//          " mkdn_disc_reason, org_unit_price, account_no, exp_date, expired_date" +
//          " from BTM_POS_DETAIL_TRANS where trans_id ='" + transID +
//          "' and item_id ='" + itemId + "' and unit_qty > 0");
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(
          "Select distinct tender_id, promo_id, store_id, cust_id, register_id, " +
          " amount, unit_qty, trunc(trans_date) trans_date, disc_amt, trans_type_id, markdown, " +
          " mkdn_disc_reason, org_unit_price, account_no, exp_date, expired_date" +
          " from BTM_POS_DETAIL_TRANS where trans_id ='" + transID +
          "' and item_id ='" + itemId + "' and unit_qty > 0", stmt);
      if (rs.next()) {
        tender_id = rs.getFloat("tender_id");
        promo_id = rs.getFloat("promo_id");
        store_id = rs.getFloat("store_id");
        cust_id = rs.getString("cust_id");
        register_id = rs.getFloat("register_id");
        amount = rs.getFloat("amount");
        unit_qty = rs.getDouble("unit_qty");
        trans_date = rs.getDate("trans_date");
        trans_datetime = ut.getSystemDateTime1();
        disc_amt = rs.getFloat("disc_amt");
        trans_type_id = rs.getString("trans_type_id");
        markdown = rs.getFloat("markdown");
        mkdn_disc_reason = rs.getString("mkdn_disc_reason");
        org_unit_price = rs.getFloat("org_unit_price");
        account_no = rs.getString("account_no");
        exp_date = rs.getDate("exp_date");
        creditCardExpire = rs.getDate("expired_date");
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }



  void removeAllThing() {
    String[] columnNames = new String[] {
        lang.getString("Return"), lang.getString("ItemCode"), lang.getString("ArtNo"),
        lang.getString("ItemName"), lang.getString("Size"), lang.getString("Quantity"), lang.getString("UnitPrice"), lang.getString("MarkDown"), lang.getString("AmountDue")
    };


    dm2.setDataVector(new Object[][] {}
                      , columnNames);
//    setTable();
    txtTransID.setText("");
    txtDate.setText("");
    txtType.setText("");
    txtStoreTrans.setText("");
    txtOperator.setText("");
  }

//  //show button in Frame Main
  void showButton() {
    frmMain.showButton(btnOK);
    frmMain.showButton(btnBack);
  }
  void setTransId(String transId) {
    txtTransID.setText(transId);
    flagSetHotkey = false; //
    showData(transId);
    showData();
    frmMain.removeButton();
    frmMain.showButton(btnSelectAll);
    frmMain.showButton(btnReturnOK);
    frmMain.showButton(btnReturnCancel);

  }

  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput = frmMain.getInputText();
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (flagButton == RI_OK_BTN) {
        if (mainInput.equals("")) {
          ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS040_EnterTransactionID"));
          return;

        }
        if (!ut.isDoubleString(mainInput)) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS041_TranIDIsNotNum"));
          frmMain.txtMainInput.setText("");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        flagSetHotkey = false; //
        showData(mainInput);
        showData();
        frmMain.removeButton();
        frmMain.showButton(btnSelectAll);
        frmMain.showButton(btnReturnOK);
        frmMain.showButton(btnReturnCancel);
      }
      frmMain.txtMainInput.setText("");
    } //enter

  }

  void btnOK_actionPerformed(ActionEvent e) {
    String mainInput = frmMain.getInputText();
    if (mainInput.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS040_EnterTransactionID"));
      return;

    }
    if (!ut.isDoubleString(mainInput)) {
      ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS041_TranIDIsNotNum"));
      frmMain.txtMainInput.requestFocus(true);
      return;
    }
    showData(frmMain.getInputText());
    showData();
    frmMain.removeButton();
    frmMain.showButton(btnSelectAll);
    frmMain.showButton(btnReturnOK);
    frmMain.showButton(btnReturnCancel);

    flagSetHotkey = false;
//      frmMain.setEditTextInput(false);
  }

  void btnBack_actionPerformed(ActionEvent e) {
    removeAllThing();
//    frmMain.lblName.setText("Name:");
//    frmMain.lblPhone.setText("Phone:");
//    frmMain.pnlNewSale.custID = -1;
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.showScreen(Constant.SCR_NEW_SALE);
//    frmMain.lblPhone.setText("Phone:");
    flagSetHotkey = true;
  }

  void btnSelectAll_actionPerformed(ActionEvent e) {
    selectAll();
    if (jTable2.getRowCount() > 0) {
      jTable2.setRowSelectionInterval(0, 0);
    }
    frmMain.removeButton();
    frmMain.showButton(btnClearAll);
    frmMain.showButton(btnReturnOK);
    frmMain.showButton(btnReturnCancel);
  }

  void btnClearAll_actionPerformed(ActionEvent e) {
    clearAll();
    frmMain.removeButton();
    frmMain.showButton(btnSelectAll);
    frmMain.showButton(btnReturnOK);
    frmMain.showButton(btnReturnCancel);
  }

  void btnReturnOK_actionPerformed(ActionEvent e) {
    boolean isSelect=false;

    //Warning if not select
    for (int i = 0; i < jTable2.getRowCount(); i++) {
      if (checkSelected(i)) {
        isSelect=true;
      }
    }
    if(!isSelect){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS137_SelectItem"));
    }
    //================
    if (jTable2.getSelectedRow() != -1) {
      if (jTable2.getValueAt(jTable2.getSelectedRow(), COL_QUANTITY) == null) {
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS138_ItemInvalid"));
        return;
      }
      addReturn();
      if (flagDone == true) {
        frmMain.setTitle(lang.getString("TT013"));
        frmMain.showScreen(Constant.SCR_NEW_SALE);
        flagSetHotkey = true;
      }
    }
  }

  void btnReturnCancel_actionPerformed(ActionEvent e) {
    removeAllThing();
    frmMain.lblName.setText(lang.getString("Name") +":");
    frmMain.lblPhone.setText(lang.getString("Phone") +":");
    frmMain.pnlNewSale.custID = -1;
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.showScreen(Constant.SCR_NEW_SALE);
    flagSetHotkey = true;
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
    // F12
    key = new Integer(KeyEvent.VK_F12);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
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
      if (flagSetHotkey == true) {
        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnOK.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F12 ||
                 identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnBack.doClick();
        }
      }
      else {
        if (identifier.intValue() == KeyEvent.VK_F1) {
          if (flagSelectAll == true) { //press SelectAll button
            btnSelectAll.doClick();
            flagSelectAll = false;
          }
          else { //press ClearAll button
            btnClearAll.doClick();
            flagSelectAll = true;
          }
        }
        else if (identifier.intValue() == KeyEvent.VK_F2) {
          btnReturnOK.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F3 ||
                 identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnReturnCancel.doClick();
        }
      }
    }
  }

  void comReason_actionPerformed(ActionEvent e) {

  }

}

class PanelReturnItem_jTable2_mouseAdapter
    extends java.awt.event.MouseAdapter {
  PanelReturnItem adaptee;

  PanelReturnItem_jTable2_mouseAdapter(PanelReturnItem adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTable2_mouseClicked(e);
  }
}

class PanelReturnItem_btnOK_actionAdapter
    implements java.awt.event.ActionListener {
  PanelReturnItem adaptee;

  PanelReturnItem_btnOK_actionAdapter(PanelReturnItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class PanelReturnItem_btnBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelReturnItem adaptee;

  PanelReturnItem_btnBack_actionAdapter(PanelReturnItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelReturnItem_btnSelectAll_actionAdapter
    implements java.awt.event.ActionListener {
  PanelReturnItem adaptee;

  PanelReturnItem_btnSelectAll_actionAdapter(PanelReturnItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSelectAll_actionPerformed(e);
  }
}

class PanelReturnItem_btnClearAll_actionAdapter
    implements java.awt.event.ActionListener {
  PanelReturnItem adaptee;

  PanelReturnItem_btnClearAll_actionAdapter(PanelReturnItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnClearAll_actionPerformed(e);
  }
}

class PanelReturnItem_btnReturnOK_actionAdapter
    implements java.awt.event.ActionListener {
  PanelReturnItem adaptee;

  PanelReturnItem_btnReturnOK_actionAdapter(PanelReturnItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReturnOK_actionPerformed(e);
  }
}

class PanelReturnItem_btnReturnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelReturnItem adaptee;

  PanelReturnItem_btnReturnCancel_actionAdapter(PanelReturnItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReturnCancel_actionPerformed(e);
  }
}

class PanelReturnItem_comReason_actionAdapter
    implements java.awt.event.ActionListener {
  PanelReturnItem adaptee;

  PanelReturnItem_comReason_actionAdapter(PanelReturnItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.comReason_actionPerformed(e);
  }
}
