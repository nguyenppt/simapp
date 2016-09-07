package pos;

import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import btm.swing.*;
import common.*;
import btm.attr.*;
import java.util.*;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */
public class PanelEvenExchange
    extends JPanel {

  private static final int EVEN_EXCHANGE_ITEM = 236;

  FrameMain frmMain;
//  int flagButton=Constant.EVEN_EXCHANGE_TRANS;//Constant.NS_EVEN_EXCHANGE_BTN;
  Utils ut = new Utils();
  DataAccessLayer DAL;
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague,ut.sCountry);
  Object[][] rowdata1 = new Object[2][2];
  Object[] columnNames1 = new Object[] {
      " ", lang.getString("MS042_SelectReason")};
  BJTable jTable1;
  //13_3_2006
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_UPC = 1;
  private static final int COL_ITEM_DESC = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_QUANTITY = 4; //
  private static final int COL_PRICE = 5;
  private static final int COL_MARKDOWN = 6;
  private static final int COL_AMOUNT = 7;

  int total_row = 0;
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  JTextArea textComments = new JTextArea();
  SortableTableModel dm = new SortableTableModel() {
    public Class getColumnClass(int col) {
      switch (col) {
        case COL_QUANTITY:
          return Double.class;
        case COL_PRICE:
          return Double.class;
        case COL_MARKDOWN:
          return Double.class;
        case COL_AMOUNT:
          return Double.class;
        default:
          return Object.class;
      }
    }

    public boolean isCellEditable(int row, int col) {
      if (col == COL_QUANTITY) {
        return true;
      }
      return false;
    }
  };
  JRadioButton ra1 = new JRadioButton();
  JRadioButton ra2 = new JRadioButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JTextField jTextField1 = new JTextField();

  BJButton btnOK = new BJButton();
  BJButton btnViewTrans = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnBack = new BJButton();
  BJButton btnEvenExchangeOK = new BJButton();
  BJButton btnEvenExchangeCancel = new BJButton();
  //Declare an old transaction id
  String oldTrans_id = "";
  String itemcode = "";
  //Declare variable in transaction detail table
  float tender_id = 0;
  float promo_id = 0;
  float store_id = 0;
  String cust_id = "";
  float register_id = 0;
  float amount = 0;
  float unit_qty = 0;
  Date trans_date = null;
  String trans_datetime = null;
  float disc_amt = 0;
  String trans_type_id = "";
  float markdown = 0;
  String mkdn_disc_reason = "";
  float org_unit_price = 0;
  String account_no = "";
  Date exp_date = null;
  Date creditCardExpire = null;
  String trans_flag = "";
  boolean flag = true; //4 nut
  //////////////
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,
      Color.lightGray, Color.white, null, null);
  BJComboBox cboReason = new BJComboBox();
  JLabel jLabel1 = new JLabel();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();

  public PanelEvenExchange(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {

    registerKeyboardActions();
    this.setLayout(new FlowLayout());

    dm.addColumn(lang.getString("ItemID"));
    dm.addColumn(lang.getString("UPC"));
    dm.addColumn(lang.getString("ItemName"));
    dm.addColumn(lang.getString("Size"));
    dm.addColumn(lang.getString("Quantity"));
    dm.addColumn(lang.getString("ItemPrice"));
    dm.addColumn(lang.getString("MarkDown"));
    dm.addColumn(lang.getString("AmountDue"));

//    DefaultTableModel dm1 = new DefaultTableModel();
//    ra1.setBackground(Color.white);
//    ra1.setHorizontalAlignment(SwingConstants.CENTER);
//    ra2.setBackground(Color.white);
//    ra2.setHorizontalAlignment(SwingConstants.CENTER);
//    dm1.setDataVector(
//        new Object[][]{
//                        {ra1,"Returning to Stock" },
//                        {ra2,"Damaged Merchandise"}},
//                        columnNames1);
    jTable1 = new BJTable(dm, true) {
      public void tableChanged(TableModelEvent e) {
        super.tableChanged(e);
        repaint();
      }
    };
//    ButtonGroup group1 = new ButtonGroup();
    jTabbedPane1.setBackground(Color.lightGray);
    jTabbedPane1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOK.setActionCommand("OK");
    btnOK.setText(lang.getString("OK") + " (F1)");
    btnViewTrans.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnViewTrans.setToolTipText(lang.getString("ViewTXN") + " (F5)");
    btnViewTrans.setText("<html><center><b>"+lang.getString("ViewTXN") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
                         "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5" +
                         "</html>");
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnBack.setText("<html><center><br><b>" + lang.getString("Back") + "</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                          "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                          "ml>");
    btnBack.addActionListener(new PanelEvenExchange_btnBack_actionAdapter(this));
    btnOK.addActionListener(new PanelEvenExchange_btnOK_actionAdapter(this));
    btnViewTrans.addActionListener(new
                                   PanelEvenExchange_btnViewTrans_actionAdapter(this));
    btnEvenExchangeOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnEvenExchangeOK.setText(lang.getString("OK") + " (F1)");
    btnEvenExchangeOK.addActionListener(new
        PanelEvenExchange_btnEvenExchangeOK_actionAdapter(this));
    btnEvenExchangeCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnEvenExchangeCancel.setText("<html><center><br><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                          "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                          "ml>");
    btnEvenExchangeCancel.addActionListener(new
        PanelEvenExchange_btnEvenExchangeCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDelete.setText(lang.getString("Delete") + " (F8)");
    btnDelete.addActionListener(new PanelEvenExchange_btnDelete_actionAdapter(this));
    this.setBackground(SystemColor.control);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setText(lang.getString("ReasonToExchange") + ": ");
    cboReason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    cboReason.setPreferredSize(new Dimension(200, 20));
//    group1.add((JRadioButton)dm1.getValueAt(0,0));
//    group1.add((JRadioButton)dm1.getValueAt(1,0));
    jTable1.setBorder(null);
    jTable1.addKeyListener(new PanelEvenExchange_jTable1_keyAdapter(this));

//    jTable1.setPreferredSize(new Dimension(650, 95));
//    jTable1.setRowHeight(30);
//    jTable1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));

//    setTable();
    jPanel2.setBackground(SystemColor.control);
    jPanel2.setEnabled(true);
    jPanel2.setForeground(Color.black);
    jPanel2.setPreferredSize(new Dimension(650, 20));
    jPanel1.setBackground(SystemColor.control);
    jPanel1.setPreferredSize(new Dimension(650, 30));
    textComments.setLineWrap(true);
    this.add(jPanel2, null);
    this.add(jLabel1, null);
    this.add(cboReason, null);
    this.add(jPanel1, null);

    JScrollPane jsp = new JScrollPane(jTable1);
    jsp.setViewportView(jTable1);
    jsp.getViewport().setBackground(Color.LIGHT_GRAY);
    jsp.setPreferredSize(new Dimension(650, 170));
    this.add(jsp, null);

    jTabbedPane1.setPreferredSize(new Dimension(650, 200));
    textComments.setText("");
    this.add(jTabbedPane1, null);
    jTabbedPane1.add(textComments, lang.getString("Comment"));
    jTabbedPane1.setSelectedComponent(textComments);
    /*jTable1.getColumn(lang.getString("ItemID")).setPreferredWidth(80);
    jTable1.getColumn(lang.getString("ItemName")).setPreferredWidth(200);*/

    jTable1.getColumnModel().getColumn(COL_ITEM_ID).setPreferredWidth(80);
    jTable1.getColumnModel().getColumn(COL_ITEM_DESC).setPreferredWidth(80);

//    jTable1.getTableHeader().setReorderingAllowed(false);
//    jTable1.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
  }

  void initScreen() {
    Statement stmt = null;
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      cboReason.setData(getCboData(stmt));
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //get cbo data
  ResultSet getCboData(Statement stmt) {
    ResultSet rs = null;
    try {
//      System.out.println(
//          "select reason_id, reason_desc from BTM_POS_REASON where reason_type='R'");
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

  void setTable() {


    JTableHeader header = new JTableHeader();
    header = jTable1.getTableHeader();
    header.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    jTable1.setTableHeader(header);

    jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
    jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
    jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
    jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
    jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
    jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);
    jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
    jTable1.getColumnModel().getColumn(7).setPreferredWidth(100);



    /*
    TableColumn column = new TableColumn();
    column = jTable1.getColumn(lang.getString("ItemName") );
    column.setMaxWidth(80);
    column.setMinWidth(80);

    TableColumn column1 = new TableColumn();
    column1 = jTable1.getColumn(lang.getString("Quantity"));
    column1.setMaxWidth(80);
    column1.setMinWidth(80);

    TableColumn column2 = new TableColumn();
    column2 = jTable1.getColumn(lang.getString("ItemPrice"));
    column2.setMaxWidth(80);
    column2.setMinWidth(80);

    TableColumn column3 = new TableColumn();
    column3 = jTable1.getColumn(lang.getString("MarkDown"));
    column3.setMaxWidth(80);
    column3.setMinWidth(80);

    TableColumn column4 = new TableColumn();
    column4 = jTable1.getColumn(lang.getString("AmountDue"));
    column4.setMaxWidth(100);
    column4.setMinWidth(100);

    TableColumn column5 = new TableColumn();
    column5 = jTable1.getColumn(lang.getString("UPC"));
    column5.setMaxWidth(60);
    column5.setMinWidth(60);

    TableColumn column6 = new TableColumn();
    column6 = jTable1.getColumn(lang.getString("Size"));
    column6.setMaxWidth(40);
    column6.setMinWidth(40);*/


  }

  void getCheckBoxValue() {
  }

  //Get data from database through Data Access Layer
  //Show item return information
  public ResultSet getDataIn(String itemcode, String trans_id) {
    ResultSet rs = null;
    String sql = null;
    sql = "select d.trans_id, d.item_id, i.art_no,i.item_desc,i.attr2_name, i.unit_price, d.markdown, d.amount, d.unit_qty " +
        " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i where " +
        " d.item_id = i.item_id and i.item_id ='" + itemcode +
        "' and d.trans_id = '" + trans_id + "'";
//    System.out.println(sql);
    try {
      rs = DAL.executeQueries(sql);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  //Get Data from database throgh Data Access Layer
  //Show item exchange information
  public ResultSet getDataOut(String itemcode) {
    ResultSet rs = null;
    String sql = null;
    sql =
        "select item_id, art_no,item_desc,attr2_name, unit_price from BTM_POS_ITEM_PRICE where item_id='" +
        itemcode + "'";
//    System.out.println(sql);
    try {

      rs = DAL.executeQueries(sql);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  int rowCount() {
    return total_row;
  }

  void addNewRow(String itemcode, int count) {
    ResultSet rs = null;
//    Statement stmt = null;
    rs = getDataOut(itemcode);
    str.installInTable(jTable1, null, Color.red, null, null);
    try {
      if (rs.next()) {
        if (count >= 1) {
          if (dm.getRowCount() > 1) {
            dm.removeRow(1);
            total_row -= 1;
          }
          dm.addRow(new Object[] {rs.getString("item_id"),rs.getString("art_no"),
                    rs.getString("item_desc"),rs.getString("attr2_name"),
                    new Double(1),
                    new Double(rs.getDouble("unit_price")),
                    new Double(ut.getRound(0, 2)),
                    new Double(ut.getRound( (rs.getDouble("unit_price") * 1), 2))
          });
          total_row += 1;
        }
        else if (count < 1) {
          dm.addRow(new Object[] {rs.getString("item_id"),rs.getString("art_no"),
                    rs.getString("item_desc"),rs.getString("attr2_name"),
                    new Double( -1),
                    new Double(rs.getDouble("unit_price")),
                    new Double(0),
                    new Double(0)
          });
          total_row += 1;
        }
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void addNewRow(String itemcode, String trans_id) {
    this.oldTrans_id = trans_id;
    str.installInTable(jTable1, null, Color.red, null, null);
    ResultSet rs = null;
//    Statement stmt = null;
    while (total_row > 0) {
      dm.removeRow(0);
      total_row -= 1;
    }
    rs = getDataIn(itemcode, trans_id);
    try {
      if (rs.next()) {
        dm.addRow(new Object[] {rs.getString("item_id"),rs.getString("art_no"),
                  rs.getString("item_desc"),rs.getString("attr2_name"),
                  new Double("-1"),
                  new Double(rs.getDouble("unit_price")),
                  new Double(ut.getRound(rs.getDouble("markdown") , 2)),
                  new Double(ut.getRound( (-1)*(rs.getDouble("unit_price")-
                                         rs.getDouble("markdown")), 2))
        });
        trans_id = "" + rs.getLong("trans_id");
        total_row += 1;
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  boolean evenExchangeItem() {
    String transID = "";
    String oldItem_id = null;
    String newItem_id = null;
    double oldunitPrice = 0.0;
    double newunitPrice = 0.0;
    double newreason_id = 0.0;
    double newAmount = 0.0;
    double oldAmount = 0.0;
    oldItem_id = jTable1.getValueAt(0, COL_ITEM_ID).toString();
    newItem_id = jTable1.getValueAt(1, COL_ITEM_ID).toString();
    oldunitPrice = Double.parseDouble("" + jTable1.getValueAt(0, COL_PRICE));
    newunitPrice = Double.parseDouble("" + jTable1.getValueAt(1, COL_PRICE));
    oldAmount = Double.parseDouble(jTable1.getValueAt(0, COL_AMOUNT).toString());
    newAmount = Double.parseDouble(jTable1.getValueAt(1, COL_AMOUNT).toString());
    Transaction trans;
    //get info of transaction NGhi
    //Trung
    String today = "";
    today = ut.getSystemDateTime();
    today = today.substring(0, 2) + today.substring(3, 5) +
        today.substring(8, 10)
        + today.substring(11, 13) + today.substring(14, 16) +
        today.substring(17);
//    today = today.substring(8, 10) +  today.substring(3, 5) + today.substring(0, 2)
//        + today.substring(11, 13) + today.substring(14, 16) +
//        today.substring(17);

    trans = frmMain.getTransaction();
    transID = ut.doubleToString(trans.getStore_ID()) +
        ut.doubleToString(trans.getRegister_ID()) + today;
    trans.setTrans_ID(Long.parseLong(transID));
    //end Trung
    newreason_id = Long.parseLong(cboReason.getSelectedData());
    oldAmount = oldAmount * ( -1);
//    System.out.println("Old Amount Due: " + oldAmount);
//    System.out.println("New Amount Due: " + newAmount);
    if (oldAmount == newAmount) {
      try {
        String sql = "";
        getOldData(oldItem_id);
        oldAmount = oldAmount * ( -1);
        int transDetailSeq = getTransDetailSeq(oldTrans_id);
        transDetailSeq = transDetailSeq + 1;

//        sql = "Insert into BTM_POS_DETAIL_TRANS values("+ transDetailSeq + ", " +
//            "'" + oldTrans_id + "'," + tender_id + ",'" + getEmpID() + "'," + promo_id +
//            ",'" + oldItem_id + "'," + store_id + ",'" + cust_id + "'," + register_id +
//            "," + oldAmount + ",-1,to_date('" + trans_date + "','YYYY-MM-DD')," +
//            "" + disc_amt + ",'" + trans_type_id + "'," + markdown + ",'" + mkdn_disc_reason +
//            "'," + org_unit_price + ",'" + account_no + "',to_date('" + exp_date + "','YYYY-MM-DD'),2,0,'',to_Date('"+ creditCardExpire +"','yyyy-MM-dd'))";
        //Nguyen Pham Begin
        if (DAL.getFranchiseCust().length() == 13) {
          cust_id=DAL.getFranchiseCust();
        }
        Statement stmt = DAL.getConnection().createStatement();
        sql = "Insert into BTM_POS_DETAIL_TRANS values(" + transDetailSeq +
            ", " +
            "'" + oldTrans_id + "'," + tender_id + ",'" + getEmpID() + "'," +
            promo_id +
            ",'" + oldItem_id + "'," + store_id + ",'" + cust_id + "'," +
            register_id +
            "," + oldAmount + ",-1,to_date('" + trans_datetime + "','" +
            ut.DATETIME_FORMAT2 + "')," +
            "" + disc_amt + ",'" + trans_type_id + "'," + markdown + ",'" +
            mkdn_disc_reason +
            "'," + org_unit_price + ",'" + account_no + "',to_date('" +
            exp_date + "','YYYY-MM-DD'),2,0,'',to_Date('" + creditCardExpire +
            "','yyyy-MM-dd'),'-1')";
        //Nguyen Pham End
        String sql1 = "";
        sql1 = "Insert into BTM_POS_RETURN_ITEM values('" + oldTrans_id +
            "','" + oldItem_id + "'," + newreason_id + ",to_date('" +
            trans_datetime + "','" + ut.DATETIME_FORMAT2 + "')" +
            ",1,'" + getEmpID() + "'," + promo_id + "," + tender_id +
            ", return_seq.nextval,0,to_date('7777-7-7','YYYY-MM-DD'))";
//        System.out.println(sql);
//        System.out.println(sql1);
        DAL.executeUpdate(sql, stmt);
        DAL.executeUpdate(sql1, stmt);
        sql = "";
        if (DAL.getFranchiseCust().length() == 13) {
          cust_id = DAL.getFranchiseCust();
        }
        //Waiting for more information about the amout due
        sql = "Insert into BTM_POS_DETAIL_TRANS values(1, " +
            "'" + transID + "'," + tender_id + ",'" + getEmpID() + "'," +
            promo_id +
            ",'" + newItem_id + "'," + store_id + "," + cust_id + "," +
            register_id +
            "," + newAmount + ",1,to_date('" + trans_datetime + "','" +
            ut.DATETIME_FORMAT2 + "')," +
            disc_amt + ",'" + trans_type_id + "'," + markdown + ",'" +
            mkdn_disc_reason +
            "'," + org_unit_price + ",'" + account_no +
            "',to_date('7777-7-7','YYYY-MM-DD'),0,0,'',to_Date('1/1/1970','dd/MM/yyyy'),'-1')";
//        System.out.println(sql);
        DAL.executeUpdate(sql, stmt);
        sql = "";
        ut.showMessage(frmMain, lang.getString("TT012") , lang.getString("MS043_ExchangeSucessful"));
        textComments.setText("");
        while (dm.getRowCount() > 0) {
          dm.removeRow(0);
        }
        stmt.close();
      }
      catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
    }
    else {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS044_CanNotExchange"));
      dm.removeRow(1);
      frmMain.removeButton();
      total_row -= 1;
      ut.flagEvenExchange = 1;
      showButton();
      return false;
    }
    flag = true;
    return true;
  }

  //get old data of trans_id for returning
  void getOldData(String itemID) {
    Statement stmt = null;
    ResultSet rs = null;
    try {
//      System.out.println(
//          "Select distinct tender_id, promo_id, store_id, cust_id, register_id, " +
//          " amount, unit_qty, trunc(trans_date) trans_date, disc_amt, trans_type_id, markdown, " +
//          " mkdn_disc_reason, org_unit_price, account_no, exp_date, expired_date" +
//          " from BTM_POS_DETAIL_TRANS where trans_id ='" +
//          this.oldTrans_id + "' and item_id ='" + itemID + "'");
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(
          "Select distinct tender_id, promo_id, store_id, cust_id, register_id, " +
          " amount, unit_qty, trunc(trans_date) trans_date, disc_amt, trans_type_id, markdown, " +
          " mkdn_disc_reason, org_unit_price, account_no, exp_date, expired_date" +
          " from BTM_POS_DETAIL_TRANS where trans_id ='" +
          this.oldTrans_id + "' and item_id ='" + itemID + "'", stmt);
      if (rs.next()) {
        tender_id = rs.getFloat("tender_id");
        promo_id = rs.getFloat("promo_id");
        store_id = rs.getFloat("store_id");
        cust_id = rs.getString("cust_id");
        register_id = rs.getFloat("register_id");
        amount = rs.getFloat("amount");
        unit_qty = rs.getFloat("unit_qty");
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
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    try {
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // get Emp Id
  String getEmpID() {
    String empID;
//    empID = frmMain.getTransaction().getEmpl_ID();
    empID = DAL.getEmpPosID1();
    return empID;
  }

  // get trans_detail_seq
  int getTransDetailSeq(String transID) {
    Statement stmt = null;
    int transDetailSeq = 0;
    ResultSet rs = null;
    try {
//      String sql = "select seq_no from btm_pos_detail_trans where trans_id ='" +
//          transID + "' and rownum <= 1 order by seq_no desc";
//      rs = DAL.executeQueries(sql);
//      if (rs.next()){
//        transDetailSeq = rs.getInt("seq_no");
//      }
      //Nguyen Pham Begin
      String sql = "select seq_no from btm_pos_detail_trans where trans_id ='" +
          transID + "' order by seq_no desc";
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        transDetailSeq = rs.getInt("seq_no");
//        System.out.println("seq no:" + transDetailSeq);
      }
      //Nguyen Pham End
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    try {
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return transDetailSeq;
  }

  //Remove row in table
  void removeRow() {
    String[] columnNames = new String[] {
        lang.getString("ItemID"),lang.getString("UPC"),
       lang.getString("ItemName"), lang.getString("Size"),lang.getString("Quantity"),
       lang.getString("ItemPrice"), lang.getString("MarkDown"), lang.getString("AmountDue")
    };




    dm.setDataVector(new Object[][] {}
                     , columnNames);

//    setTable();
    total_row = 0;
  }

  //show button in Frame Main
  void showButton() {

    if (ut.flagEvenExchange == 1) {
      frmMain.showButton(btnOK);
      frmMain.showButton(btnViewTrans);
      frmMain.showButton(btnDelete);
      frmMain.showButton(btnBack);
    }
    else {
      frmMain.showButton(btnEvenExchangeOK);
      frmMain.showButton(btnEvenExchangeCancel);
    }
  }

  boolean checkTransID(String transID) {
    Statement stmt = null;
    ResultSet rs = null;
    try {
      String sql;
      sql =
          "Select trans_id, trans_flag from BTM_POS_DETAIL_TRANS where trans_id = '" +
          transID + "'";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        oldTrans_id = transID;
        trans_flag = rs.getString("trans_flag");
      }
      else {
        rs.close();
        stmt.close();
        return false;
      }
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    try {
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput = frmMain.getInputText().trim();
    itemcode = getItemCode(mainInput);
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      //Save transaction id
      if (frmMain.flagEvenExchangeConstant == Constant.EVEN_EXCHANGE_TRANS) {
        if (!checkTransID(mainInput)) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS045_WrongTrans"));
          return;
        }
        else if (trans_flag.equalsIgnoreCase("4")) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS046_TransVoid"));
          return;
        }
        frmMain.flagEvenExchangeConstant = EVEN_EXCHANGE_ITEM;
        trans_flag = "0";
        frmMain.setInputLabel(lang.getString("MS047_EnterUPC"));
        frmMain.setInputText("");
        return;
      }
      //code is the same from btnEvenExchangeOK_actionPerformed
      if (frmMain.flagEvenExchangeConstant == EVEN_EXCHANGE_ITEM) {
        if (!mainInput.equals("")) {
          if (!ut.isDoubleString(mainInput)) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS048_InputIsNum"));
            return;
          }
          if (rowCount() < 1) {

            if (!checkItemID(itemcode)) {
              ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS049_NotFoundItem"));
              frmMain.txtMainInput.requestFocus(true);
              return;
            }
            addNewRow(itemcode, oldTrans_id);
            frmMain.setInputLabel(lang.getString("MS050_UPCNew"));
            frmMain.setInputText("");
            ut.flagEvenExchange = 1;
//              JOptionPane.showMessageDialog(this, "First of all You must choose the item to return by clicking on View Trans Button","Message",1);
          }
          else if (rowCount() >= 1) {
            if (!ut.isDoubleString(mainInput)) {
              ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS048_InputIsNum"));
              frmMain.setInputText("");
              return;
            }
            addNewRow(itemcode, rowCount());
            if (dm.getRowCount() <= 1) {
              ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS051_UPCIncorrect"));
              frmMain.setInputText("");
              return;
            }
            flag = false;
            frmMain.setInputText("");
            frmMain.removeButton();
            frmMain.showButton(btnEvenExchangeOK);
            frmMain.showButton(btnEvenExchangeCancel);
            ut.flagEvenExchange = 1;
          }
        }
      }

      frmMain.setInputText("");
    } //end ENTER key

  }

  boolean checkItemID(String itemID) {
    Statement stmt = null;
    ResultSet rs = null;
    try {
      String sql =
          " select item_id, art_no,item_desc,attr2_name, unit_qty, unit_price, markdown, amount " +
          " from " +
          "(select d.item_id, art_no,item_desc,attr2_name, unit_qty, unit_price, markdown, amount " +
          " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i " +
          " where d.item_id = i.item_id and trans_id = '" + oldTrans_id +
          "' and d.item_id ='" + itemID +
          "' and d.item_id not in (select item_id from BTM_POS_RETURN_ITEM where trans_id ='" +
          oldTrans_id + "')" +

          " union all " +

          " select d1.item_id, art_no,item_desc,attr2_name, (d1.unit_qty - r.return_qty) unit_qty, unit_price, markdown, amount " +
          " from BTM_POS_DETAIL_TRANS d1, BTM_POS_ITEM_PRICE i , " +
          " (select trans_id, item_id, sum(return_qty) return_qty " +
          " from BTM_POS_RETURN_ITEM " +
          " where trans_id ='" + oldTrans_id + "' " +
          " group by trans_id , item_id) r " +
          " where d1.trans_id = r.trans_id and d1.item_id = r.item_id " +
          " and d1.item_id ='" + itemID + "' and d1.item_id = i.item_id " +
          " and d1.unit_qty > r.return_qty and d1.trans_id = '" + oldTrans_id +
          "')";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        stmt.close();
        return true;
      }
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    try {
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  String getItemCode(String upc){
    ResultSet rs = null;
    Statement stmt = null;
    if(upc.equalsIgnoreCase("")){
      return "";
    }
    String itemID="-1";
        try {
          String sql ="Select ITEM_ID From BTM_POS_ITEM_PRICE Where ART_NO='" + upc + "'";
//          System.out.println(sql);
          stmt = DAL.getConnection().createStatement();
          rs = DAL.executeQueries(sql, stmt);
          if (rs.next()) {
            itemID = rs.getString("ITEM_ID");
          }
        }
        catch (Exception e) {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(e);
        }
        try {
          stmt.close();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      return itemID;
  }
  void btnOK_actionPerformed(ActionEvent e) {
    String upc = frmMain.getInputText().trim();
    //get Item code from UPC and set into mainInput variable
    String mainInput = getItemCode(upc);
    //Save transaction id
    if (frmMain.flagEvenExchangeConstant == Constant.EVEN_EXCHANGE_TRANS) {
      if (!checkTransID(mainInput)) {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS045_WrongTrans"));
        return;
      }
      frmMain.flagEvenExchangeConstant = EVEN_EXCHANGE_ITEM;
      frmMain.setInputLabel(lang.getString("MS047_EnterUPC"));
      frmMain.setInputText("");
      return;
    }
    //code is the same from btnEvenExchangeOK_actionPerformed
    if (frmMain.flagEvenExchangeConstant == EVEN_EXCHANGE_ITEM) {
      if (!mainInput.equals("")) {
        if (!ut.isDoubleString(mainInput)) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS048_InputIsNum"));
          return;
        }
        if (rowCount() < 1) {
          if (!checkItemID(itemcode)) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS049_NotFoundItem"));
            frmMain.txtMainInput.requestFocus(true);
            return;
          }
          addNewRow(itemcode, oldTrans_id);
          frmMain.setInputText("");
          ut.flagEvenExchange = 1;
        }
        else if (rowCount() >= 1) {
          if (!ut.isDoubleString(mainInput)) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS048_InputIsNum"));
            frmMain.setInputText("");
            return;
          }
          addNewRow(itemcode, rowCount());
          if (dm.getRowCount() <= 1) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS051_UPCIncorrect"));
            frmMain.setInputText("");
            return;
          }
          frmMain.setInputText("");
          frmMain.removeButton();
          frmMain.showButton(btnEvenExchangeOK);
          frmMain.showButton(btnEvenExchangeCancel);
          ut.flagEvenExchange = 1;
        }
      }
    }
    frmMain.setInputText("");
  }

  void btnViewTrans_actionPerformed(ActionEvent e) {
    frmMain.flagEvenExchangeConstant = EVEN_EXCHANGE_ITEM;
    frmMain.setTitle(lang.getString("TT011"));
    frmMain.showScreen(Constant.SCR_VIEW_TRANS);
  }

  void btnBack_actionPerformed(ActionEvent e) {
    while (jTable1.getRowCount() > 0) {
      dm.removeRow(0);
    }
    textComments.setText("");
    total_row = 0;
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.showScreen(Constant.SCR_NEW_SALE);
    ut.flagEvenExchange = 1;
    frmMain.flagEvenExchangeConstant = Constant.EVEN_EXCHANGE_TRANS;
    flag = true;
  }

  void btnEvenExchangeOK_actionPerformed(ActionEvent e) {
    if (evenExchangeItem()) {
      total_row = 0;
      frmMain.setTitle(lang.getString("TT013"));
      frmMain.showScreen(Constant.SCR_NEW_SALE);
      ut.flagEvenExchange = 1;
      frmMain.flagEvenExchangeConstant = Constant.EVEN_EXCHANGE_TRANS;
    }

  }

  void btnEvenExchangeCancel_actionPerformed(ActionEvent e) {
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    total_row = 0;
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.showScreen(Constant.SCR_NEW_SALE);
    ut.flagEvenExchange = 1;
    frmMain.flagEvenExchangeConstant = Constant.EVEN_EXCHANGE_TRANS;
    flag = true;
  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if (dm.getRowCount() == 1) {
      dm.removeRow(0);
      ut.flagEvenExchange = 1;
      frmMain.setInputText("");
      frmMain.flagEvenExchangeConstant = Constant.EVEN_EXCHANGE_TRANS;
      frmMain.lblMainInput.setText(lang.getString("MS052_ScanTrans"));
      total_row -= 1;
    }
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
    // F8
    key = new Integer(KeyEvent.VK_F8);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), key);
    actionMap.put(key, new KeyAction(key));
    // F12
    key = new Integer(KeyEvent.VK_F12);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
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

      if (flag == true) { //4 nut
        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnOK.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F5) {
          btnViewTrans.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F8) {
          btnDelete.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F12 ||
                 identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnBack.doClick();
        }
      }
      else { //2 nut
        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnEvenExchangeOK.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F12 ||
                 identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnEvenExchangeCancel.doClick();
        }
      }
    }
  }

  private void catchHotKey(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      btnViewTrans.doClick();
    }
  }

  void jTable1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

}

class PanelEvenExchange_btnOK_actionAdapter
    implements java.awt.event.ActionListener {
  PanelEvenExchange adaptee;

  PanelEvenExchange_btnOK_actionAdapter(PanelEvenExchange adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class PanelEvenExchange_btnViewTrans_actionAdapter
    implements java.awt.event.ActionListener {
  PanelEvenExchange adaptee;

  PanelEvenExchange_btnViewTrans_actionAdapter(PanelEvenExchange adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnViewTrans_actionPerformed(e);
  }
}

class PanelEvenExchange_btnBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelEvenExchange adaptee;

  PanelEvenExchange_btnBack_actionAdapter(PanelEvenExchange adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelEvenExchange_btnEvenExchangeOK_actionAdapter
    implements java.awt.event.ActionListener {
  PanelEvenExchange adaptee;

  PanelEvenExchange_btnEvenExchangeOK_actionAdapter(PanelEvenExchange adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnEvenExchangeOK_actionPerformed(e);
  }
}

class PanelEvenExchange_btnEvenExchangeCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelEvenExchange adaptee;

  PanelEvenExchange_btnEvenExchangeCancel_actionAdapter(PanelEvenExchange
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnEvenExchangeCancel_actionPerformed(e);
  }
}

class PanelEvenExchange_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
  PanelEvenExchange adaptee;

  PanelEvenExchange_btnDelete_actionAdapter(PanelEvenExchange adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelEvenExchange_jTable1_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelEvenExchange adaptee;

  PanelEvenExchange_jTable1_keyAdapter(PanelEvenExchange adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.jTable1_keyPressed(e);
  }
}
