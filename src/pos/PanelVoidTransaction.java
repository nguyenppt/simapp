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
import btm.attr.*;
import java.util.*;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: Main - Qly GD - Xoa GD</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelVoidTransaction extends JPanel {
  private static final int RI_OK_BTN =224;

  String temp = "";
  Statement stmt = null;
  FrameMain frmMain;
  Vector vSql = new Vector();
  Vector vSql1 = new Vector();
  int flagButton=RI_OK_BTN;//dertermine button on screen.
  Utils ut=new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);

  DataAccessLayer DAL; //= new DataAccessLayer();
  BorderLayout borderLayout1 = new BorderLayout();
  SortableTableModel dm = new SortableTableModel();
  JPanel pnlMain = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlCenter = new JPanel();
  JLabel lblTransID = new JLabel(lang.getString("TransactionNo") +  ":");
  JLabel lblDate = new JLabel(lang.getString("Date") +  ":");
  JLabel lblType = new JLabel(lang.getString("Type") +  ":");
  JLabel lblStoreTrans = new JLabel(lang.getString("StoreName") +  ":");
  JLabel lblOperator = new JLabel(lang.getString("Operator") +  ":");
  JTextField txtTransID = new JTextField();
  JTextField txtDate = new JTextField();
  JTextField txtType = new JTextField();
  JTextField txtStoreTrans = new JTextField();
  JTextField txtOperator = new JTextField();

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
  SortableTableModel dm2 = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 5: return Double.class;
        case 2: return Long.class;
        case 3: return Double.class;
        case 4: return Double.class;
        default: return Object.class;
      }
    }

  };
  BJButton btnOK = new BJButton();
  BJButton btnBack = new BJButton();

//Declare variable in transaction detail table
  float tender_id = 0;
  float promo_id = 0;
  float store_id = 0;
  String cust_id = "";
  float register_id = 0;
  float amount = 0;
  float unit_qty = 0;
  Date trans_date = null;
  String trans_datetime="";
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
  boolean flagSelectAll = true ;
  JLabel jLabel1 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea txtReason = new JTextArea();

  public PanelVoidTransaction(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOK.setToolTipText(lang.getString("OK") +" (F1)");
    btnOK.setText(lang.getString("OK") +" (F1)");
    btnOK.addActionListener(new PanelVoidTransaction_btnOK_actionAdapter(this));
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnBack.setToolTipText(lang.getString("Back") + " (F12)");
    btnBack.setText("<html><center><br><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                       "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                       "ml>");
    btnBack.addActionListener(new PanelVoidTransaction_btnBack_actionAdapter(this));

    pnlMain.setBackground(new Color(55, 194, 255));
    pnlMain.setLayout(borderLayout2);
    pnlCenter.setBackground(SystemColor.control);
    pnlCenter.setMinimumSize(new Dimension(10, 10));
    pnlCenter.setPreferredSize(new Dimension(660, 60));
    pnlCenter.setLayout(flowLayout1);

    pnlText.setBackground(SystemColor.control);
    pnlText.setAlignmentY((float) 0.5);
    pnlText.setMinimumSize(new Dimension(10,10));
    pnlText.setPreferredSize(new Dimension(660, 150));
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
    txtTransID.setBackground(SystemColor.control);
    txtTransID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtTransID.setAlignmentY((float) 0.5);
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
    jPanel6.setBackground(SystemColor.control);
    jPanel6.setPreferredSize(new Dimension(60, 31));
    jPanel2.setBackground(SystemColor.control);
    jPanel5.setBackground(SystemColor.control);
//    jTable2.setPreferredSize(new Dimension(0, 0));
    this.setBackground(SystemColor.control);
    pnlTableData.setBackground(SystemColor.control);
    frmMain.getContentPane().setBackground(Color.lightGray);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(260, 20));
    jLabel1.setText(lang.getString("ReasonToVoidTransaction") +":");
    jScrollPane1.setPreferredSize(new Dimension(260, 100));
    jPanel2.add(lblTransID, null);
    jPanel2.add(lblDate, null);
    jPanel2.add(lblType, null);
    jPanel2.add(lblStoreTrans, null);
    jPanel2.add(lblOperator, null);

    jPanel6.add(txtTransID, null);
    jPanel6.add(txtDate, null);
    jPanel6.add(txtType, null);
    jPanel6.add(txtStoreTrans, null);
    jPanel6.add(txtOperator, null);

    //Create Constructor for pnlData

    /*dm2.addColumn("UPC");
    dm2.addColumn("Item Description");
    dm2.addColumn("Quantity");
    dm2.addColumn("Unit Price");
    dm2.addColumn("Markdown");
    dm2.addColumn("Amount Due");*/

    dm2.addColumn(lang.getString("UPC"));
    dm2.addColumn(lang.getString("Description"));
    dm2.addColumn(lang.getString("Quantity"));
    dm2.addColumn(lang.getString("UnitPrice"));
    dm2.addColumn(lang.getString("MarkDown"));
    dm2.addColumn(lang.getString("AmountDue"));

    jTable2 = new BJTable(dm2,true){
      public void tableChanged(TableModelEvent e){
        super.tableChanged(e);
        repaint();
      }
      public boolean isCellEditable(int row, int col){
        return false;
      }
    };
//    jTable2.setRowHeight(30);
    JScrollPane jsp2 = new JScrollPane(jTable2);
    jsp2.setPreferredSize(new Dimension(650, 307));
    jsp2.setBackground(SystemColor.control);
    jsp2.setViewportView(jTable2);

    jPanel2.setPreferredSize(new Dimension(120, 10));
    jPanel5.setPreferredSize(new Dimension(290, 10));
    this.add(pnlMain, BorderLayout.CENTER);
    pnlMain.add(pnlCenter, BorderLayout.CENTER);

    pnlCenter.add(pnlText,null);
    pnlText.add(jPanel2, BorderLayout.WEST);
    pnlText.add(jPanel5, BorderLayout.EAST);
    jPanel5.add(jLabel1, null);
    jPanel5.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(txtReason, null);
    pnlText.add(jPanel6, BorderLayout.CENTER);

    pnlTableData.setLayout(new FlowLayout());
    pnlTableData.setPreferredSize(new Dimension(660, 367));
    pnlTableData.add(jsp2,null);
    pnlCenter.add(pnlTableData, null);
    jTable2.addKeyListener(new PanelVoidTransaction_jTable2_keyAdapter(this));
//    jTable2.getTableHeader().setReorderingAllowed(false);

//    jTable2.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
//    jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_ALL_COLUMNS);
  }
  void setTable(){
    jTable2.getColumnModel().getColumn(0).setMaxWidth(80);
    jTable2.getColumnModel().getColumn(0).setMinWidth(10);
    jTable2.getColumnModel().getColumn(2).setMaxWidth(80);
    jTable2.getColumnModel().getColumn(2).setMinWidth(10);
    jTable2.getColumnModel().getColumn(3).setMaxWidth(80);
    jTable2.getColumnModel().getColumn(3).setMinWidth(10);
    jTable2.getColumnModel().getColumn(4).setMaxWidth(80);
    jTable2.getColumnModel().getColumn(4).setMinWidth(10);
    jTable2.getColumnModel().getColumn(5).setMaxWidth(150);
    jTable2.getColumnModel().getColumn(5).setMinWidth(90);



  }
  //show data in overview
  void showData(String trans_id){
    ResultSet rs = null;
    try{
      String sql = "select distinct trunc(trans_date) trans_date, trans_id, trans_flag, store_id, emp_id " +
          " from BTM_POS_DETAIL_TRANS d" +
          " where trans_id ='" + trans_id + "' and trans_flag = 0";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()) {
        txtTransID.setText("" + rs.getLong("trans_id"));
        txtDate.setText("" + rs.getDate("trans_date"));
        if (rs.getString("trans_flag").equalsIgnoreCase("0")){
            txtType.setText("Sales");
        }
        txtStoreTrans.setText("" + rs.getLong("store_id"));
        txtOperator.setText(rs.getString("emp_id"));
      }else {
        txtTransID.setText("");
        txtDate.setText("");
        txtType.setText("");
        txtStoreTrans.setText("");
        txtOperator.setText("");
      }
      stmt.close();
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }


  /* get new markdown of item to show on table
   * @author Vinh.Le
   * @param : tran_id, item_id, old_markdown and new_Qty
   * @return : double markdown
  */
  double getNewMarkDown(String tran_id, String item_id, double old_Markdown, long new_Qty){
    double newMarkDown = old_Markdown;
    ResultSet rs=null;

    //get quantity returned of item
    String sqlStr = "Select RETURN_QTY From BTM_POS_RETURN_ITEM Where TRANS_ID='" + tran_id + "' and ITEM_ID='" + item_id + "'";
//    System.out.println(sqlStr);
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr,stmt);
      if (rs.next()) {
        //calculate new markdown
        newMarkDown = (old_Markdown / (rs.getInt("RETURN_QTY") + new_Qty))*new_Qty;
      }
      stmt.close();
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return newMarkDown;
  }


  //Show data in detail
  void showData(){

    String trans_id = txtTransID.getText();
    /*String[] columnNames = new String[]{"UPC",
        "Item Description","Quantity","Unit Price","Markdown","Amount Due"
    };*/

  String[] columnNames = new String[]{lang.getString("UPC"),
        lang.getString("Description"),lang.getString("Quantity"),lang.getString("UnitPrice"),lang.getString("MarkDown"),lang.getString("AmountDue")
    };

    int i=0;
    ResultSet rs = null;
    try{
      while (dm.getRowCount()>0){
        dm.removeRow(0);
      }
      String sql = "";
//          sql = " select count(*) num" +
//          " from " +
//          "(select d.item_id, item_desc, unit_qty, unit_price, markdown, amount " +
//          " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i " +
//          " where d.item_id = i.item_id and trans_id = '" + trans_id +
//          "' and d.item_id not in (select item_id from BTM_POS_RETURN_ITEM where trans_id ='" + trans_id +"')" +
//
//          " union all " +
//
//          " select d1.item_id, item_desc, (d1.unit_qty - r.return_qty) unit_qty, unit_price, markdown, amount " +
//          " from BTM_POS_DETAIL_TRANS d1, BTM_POS_ITEM_PRICE i , " +
//                 " (select trans_id, item_id, sum(return_qty) return_qty " +
//                 " from BTM_POS_RETURN_ITEM " +
//                 " where trans_id ='" + trans_id + "' " +
//                 " group by trans_id , item_id) r " +
//          " where d1.trans_id = r.trans_id and d1.item_id = r.item_id " +
//          " and d1.item_id = i.item_id " +
//          " and d1.unit_qty > r.return_qty and d1.trans_id = '" + trans_id + "')";

//      rs = DAL.executeQueries(sql);
//      if (rs.next()){
//        i = rs.getInt("num");
//      }
//      rs = null;
//      rowCount = i;
//      Object[][] data = new Object[i][7];
//      dataUnitQty = new double[i];
      sql = " select art_no, item_desc, unit_qty, unit_price, markdown*unit_qty, amount " +
          " from " +
          "(select i.art_no, item_desc, unit_qty, unit_price, markdown, amount " +
          " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i " +
          " where d.item_id = i.item_id and trans_id = '" + trans_id +
          "' and d.item_id not in (select item_id from BTM_POS_RETURN_ITEM where trans_id ='" + trans_id +"')" +

          " union all " +

          " select i.art_no, item_desc, (d1.unit_qty - r.return_qty) unit_qty, unit_price, markdown, amount " +
          " from BTM_POS_DETAIL_TRANS d1, BTM_POS_ITEM_PRICE i , " +
                 " (select trans_id, item_id, sum(return_qty) return_qty " +
                 " from BTM_POS_RETURN_ITEM " +
                 " where trans_id ='" + trans_id + "' " +
                 " group by trans_id , item_id) r " +
          " where d1.trans_id = r.trans_id and d1.item_id = r.item_id " +
          " and d1.item_id = i.item_id " +
          " and d1.unit_qty > r.return_qty and d1.trans_id = '" + trans_id + "')";
//System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      jTable2.setData(rs);
      stmt.close();
      jTable2.setHeaderName(lang.getString("UPC"),0);
      jTable2.setHeaderName(lang.getString("Description"),2);
      jTable2.setHeaderName(lang.getString("Quantity"),3);
      jTable2.setHeaderName(lang.getString("UnitPrice"),4);
      jTable2.setHeaderName(lang.getString("MarkDown"),5);
      jTable2.setHeaderName(lang.getString("AmountDue"),1);



//      i = 0;
//      while (rs.next()){
//        data[i][0] = rs.getString("item_id");
//        data[i][1] = rs.getString("item_desc");
//        data[i][2] = new Long(rs.getLong("unit_qty"));
//        data[i][3] = new Double(rs.getDouble("unit_price"));
//
//        double newMakDown = getNewMarkDown(trans_id, rs.getString("item_id"), rs.getDouble("markdown"),rs.getLong("unit_qty"));
//        data[i][4] = new Double(newMakDown);
//
//        double newAmount = rs.getDouble("unit_price")*rs.getLong("unit_qty") - newMakDown;
//        data[i][5] = new Double(newAmount);
//        //data[i][5] = new Double(rs.getDouble("markdown"));
//        //data[i][6] = new Double(rs.getDouble("amount"));
//        dataUnitQty[i] = rs.getDouble("unit_qty");
//        i += 1;
//      }
//      dm2.setDataVector(data,columnNames);

//      setTable();
     //ut.changeDataTypetoLong(2,dm2); //Yen.Dinh 19-06-2006
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  //Add the return item into database
  void addReturn(){
    String oldTransID = txtTransID.getText();
    String newTransID = "";
    String today="";
    Transaction trans;
    today = ut.getSystemDateTime();
    today = today.substring(0,2) + today.substring(3, 5) + today.substring(8,10)
            +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);
//    today = today.substring(8,10) + today.substring(3, 5) + today.substring(0,2)
//            +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);

    trans= frmMain.getTransaction();
    newTransID = ut.doubleToString(trans.getStore_ID()) +ut.doubleToString(trans.getRegister_ID())+today;
    trans.setTrans_ID(Long.parseLong(newTransID));
    int ii = 0;
//    ii = getSeqNo(oldTransID);
    for (int i = 0; i < jTable2.getRowCount(); i++) {
      try {
        String upc =  jTable2.getValueAt(i, 0).toString();
        String item_id =  getItemCode(upc);
        getOldData(oldTransID, item_id);
        String item_desc =  jTable2.getValueAt(i, 1).toString();
        double unit_qty1 = Double.parseDouble("" + jTable2.getValueAt(i, 2));
        ii ++;
//        System.out.println(
//            "Insert into BTM_POS_DETAIL_TRANS values("+ ii + ", " +
//            "'" + newTransID + "'," + tender_id + ",'" +
//            frmMain.getTransaction().getEmpl_ID() +
//            DAL.getEmpPosID1() +
//            "'," + promo_id + ",'" + item_id + "'," + store_id + ",'" +
//            cust_id +
//            "'," + register_id + "," + amount*(-1) + "," + unit_qty1*(-1) +
//            ",to_date('" + trans_datetime + "','" + ut.DATETIME_FORMAT2 + "')," + disc_amt +
//            ",'4'," + markdown + ",'" +
//            mkdn_disc_reason +
//            "'," + org_unit_price + ",'" + account_no + "',to_date('" +
//            exp_date +
//            "','YYYY-MM-DD'),'4',0,'"+ txtReason.getText() + "',to_Date('"+ creditCardExpire +"','yyyy-MM-dd'))"
//            );
        if (DAL.getFranchiseCust().length() == 13) {
          cust_id=DAL.getFranchiseCust();
        }

        vSql.addElement(
            "Insert into BTM_POS_DETAIL_TRANS values("+ ii + ", " +
            "'" + newTransID + "'," + tender_id + ",'" +
//            frmMain.getTransaction().getEmpl_ID() +
            DAL.getEmpPosID1() +
            "'," + promo_id + ",'" + item_id + "'," + store_id + ",'" +
            cust_id +
            "'," + register_id + "," + amount*(-1) + "," + unit_qty1*(-1) +
            ",to_date('" + trans_datetime + "','" + ut.DATETIME_FORMAT2 + "')," + disc_amt +
            ",'4'," + markdown + ",'" +
            mkdn_disc_reason +
            "'," + org_unit_price + ",'" + account_no + "',to_date('" +
            exp_date +
            "','YYYY-MM-DD'),'4',0,'"+ txtReason.getText() + "',to_Date('"+ creditCardExpire +"','yyyy-MM-dd'))"
            );
//        System.out.println(
//            "Insert into BTM_POS_PAID_IN_OUT values(" +
//            "'" + String.valueOf( Long.parseLong(newTransID)+i ) + "',0,0," + amount + ",'" +   DAL.getCurrencyID() +
//            "','" + DAL.getCurrencyID() + "',to_date('" + trans_datetime + "','" + ut.DATETIME_FORMAT2 +
//            "'),1,'',to_date('7777-7-7','yyyy-MM-dd'),'',0,'" +DAL.getEmpPosID1()+ "')"
//);
         //newTransID=newTransID+i : avoid dupplicate when returm more than 1 item
        vSql.addElement(
            "Insert into BTM_POS_PAID_IN_OUT values(" +
            "'" + String.valueOf( Long.parseLong(newTransID)+i ) + "',0,0," + amount + ",'" +   DAL.getCurrencyID() +
            "','" + DAL.getCurrencyID() + "',to_date('" + trans_datetime + "','" + ut.DATETIME_FORMAT2 +
            "'),1,'',to_date('7777-7-7','yyyy-MM-dd'),'',0,'" +DAL.getEmpPosID1()+ "')");

        //TrungNT: let batch job exp transaction that void
//        System.out.println(
//            "update btm_pos_detail_trans set trans_flag = '4' " +
//            " where trans_id ='" + oldTransID + "'"
//            );
        vSql.addElement(
            "update btm_pos_detail_trans set trans_type_id = '4' " +
            " where trans_id ='" + oldTransID + "'"
            );
      }
      catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }

    }
    if (!vSql.isEmpty()){
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        DAL.setBeginTrans(DAL.getConnection());
        DAL.executeBatchQuery(vSql, stmt);
        vSql.clear();
        DAL.setEndTrans(DAL.getConnection());
        stmt.close();
      }
      catch(Exception ex){};
    }
    removeAllThing();
  }
  //get seq_no
//  int getSeqNo(String transID){
//    int result = 0;
//    setOpenConnection();
//    Statement stmt = null;
//    ResultSet rs = null;
//    String sql = "select seq_no from btm_pos_detail_trans where trans_id = '" + transID + "' order by seq_no desc";
//    try{
//      stmt = DAL.getConnection().createStatement();
//      rs = DAL.executeQueries(sql,stmt);
//      if (rs.next()){
//        result = rs.getInt("seq_no");
//      }
//      rs.close();
//      stmt.close();
//    }catch(Exception e){
//      e.printStackTrace();
//    }
//    closeConnection();
//    return result;
//  }
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

  //set return item
  void setReturnItem(Vector vSql, Vector vSql1){
    this.vSql = vSql;
    this.vSql1 = vSql1;
  }
  //get return Item
  Vector getReturnItem(){
    return this.vSql;
  }
  //get old data of trans_id for returning
  void getOldData(String transID, String itemId){
    ResultSet rs = null;
    try{
//      System.out.println("Select distinct tender_id, promo_id, store_id, cust_id, register_id, " +
//          " amount, unit_qty, trunc(trans_date) trans_date, disc_amt, trans_type_id, markdown, " +
//          " mkdn_disc_reason, org_unit_price, account_no, exp_date, expired_date " +
//          " from BTM_POS_DETAIL_TRANS where trans_id ='" + transID +
//          "' and item_id ='" + itemId + "' and unit_qty > 0");

      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(
          "Select distinct tender_id, promo_id, store_id, cust_id, register_id, " +
          " amount, unit_qty, trunc(trans_date) trans_date, disc_amt, trans_type_id, markdown, " +
          " mkdn_disc_reason, org_unit_price, account_no, exp_date, expired_date " +
          " from BTM_POS_DETAIL_TRANS where trans_id ='" + transID +
          "' and item_id ='" + itemId + "' and unit_qty > 0",stmt);
      if (rs.next()) {
        tender_id = rs.getFloat("tender_id");
        promo_id = rs.getFloat("promo_id");
        store_id = rs.getFloat("store_id");
        cust_id = rs.getString("cust_id");
        register_id = rs.getFloat("register_id");
        amount = rs.getFloat("amount");
        unit_qty = rs.getFloat("unit_qty");
        trans_date = rs.getDate("trans_date");
        trans_datetime=ut.getSystemDateTime1();
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
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  void removeAllThing(){
    /*String[] columnNames = new String[]{"UPC",
        "Item Description","Quantity","Unit Price","Markdown","Amount Due"
    };*/
    String[] columnNames = new String[]{"Ma vach",
        "Mo ta","So luong","Gia","Fia giam","So huong"
    };

    dm2.setDataVector(new Object[][]{},columnNames);
    setTable();
    txtTransID.setText("");
    txtDate.setText("");
    txtType.setText("");
    txtStoreTrans.setText("");
    txtOperator.setText("");
    txtReason.setText("");
    frmMain.txtMainInput.setText("");
  }
//  //show button in Frame Main
  void showButton() {
    frmMain.showButton(btnOK);
    frmMain.showButton(btnBack);
  }
  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput=frmMain.getInputText();
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (flagButton == RI_OK_BTN) {
        if (mainInput.equals("") ){
          ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS040_EnterTransactionID"));
          return;

        }
        if (!ut.isDoubleString(mainInput)){
          ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS041_TranIDIsNotNum"));
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        showData(mainInput);
        showData();
      }
    }//enter

  }

  void btnOK_actionPerformed(ActionEvent e) {
    String mainInput=frmMain.getInputText();
      if (mainInput.equals("") ){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS040_EnterTransactionID"));
        return;

      }
      if (!ut.isDoubleString(mainInput)){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS041_TranIDIsNotNum"));
        frmMain.txtMainInput.requestFocus(true);
        return;
      }
      showData(frmMain.getInputText());
      showData();
      if (!txtTransID.getText().equalsIgnoreCase("") && !txtDate.getText().equalsIgnoreCase("")){
        //Check if enough money (return to customer)
        try{
          String sql = " select sum(amount) total from btm_pos_detail_trans where trans_id='"+txtTransID.getText() + "' " +
              " and trunc(trans_date) = to_date('" + txtDate.getText() +
              "','yyyy-MM-dd')";
          ResultSet rs = null;

         rs = DAL.executeQueries(sql);
         if(rs.next()){
           if( calcDrawFun()< rs.getLong("total") ) {
             ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS079_paidInMoney") +calcDrawFun()+" VND");
             return;
           }

         }

        //Check if void
        sql = "select * from btm_pos_detail_trans where trans_id='" +
            txtTransID.getText() + "' and trunc(trans_date) = to_date('" +
            txtDate.getText()+"','yyyy-MM-dd') and trans_type_id =4";
//        System.out.println(sql);

          stmt = DAL.getConnection().createStatement();
         rs = DAL.executeQueries(sql,stmt);
          if (rs.next()){
            stmt.close();
            ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS142_TransExcute"));
            return;
          }
//          rs.close();
//          rs = null;
          stmt.close();
          if (!txtDate.getText().equalsIgnoreCase(ut.getSystemDate())){
            ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS141_DateType"));
            return;
          }
          int k= ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS143_VoidTras"),DialogMessage.QUESTION_MESSAGE,DialogMessage.YES_NO_OPTION);

          if(k==0)
            addReturn();
//            btnBack.requestFocus();
          return;
        }catch(Exception ex){
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(ex);
        }
      }
//      frmMain.setEditTextInput(false);
  }
  //  caculate current draw fun: the same code in PanelPaidOut, NewSale
  long calcDrawFun(){
   String sql="";
   long currDrawFun=0;
   ResultSet rs=null;

   try{
     // beginning draw fun
      sql = "select draw_fund from btm_pos_store_fund where total_fund = 0 order by workday desc";
      rs = DAL.executeQueries(sql);
      if(rs.next()){
        currDrawFun = rs.getLong("draw_fund");
      }

      //get paid in-out
      sql = "select sum(paid_in) - sum(paid_out) total "+
        " from btm_pos_paid_in_out   "+
        " where trunc(trans_date) = to_date('"+ut.getSystemDate()+"', 'yyyy-MM-dd')  " +
        " order by trunc(trans_date)";

        rs = DAL.executeQueries(sql);
        if(rs.next()){
          currDrawFun = currDrawFun + rs.getLong("total");
        }

   }
   catch (Exception ex) {
     ex.printStackTrace();
   }

   return currDrawFun;

 }


  void btnBack_actionPerformed(ActionEvent e) {
    removeAllThing();
    frmMain.setTitle(lang.getString("TT051"));
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showTransManagementButton();
  }

  void btnReturnOK_actionPerformed(ActionEvent e) {
    if (jTable2.getSelectedRow() != -1 ){
      if (jTable2.getValueAt(jTable2.getSelectedRow(), 3) == null) {
        ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS138_ItemInvalid"));
        return;
      }
      addReturn();
      if (flagDone == true) {
        frmMain.setTitle(lang.getString("TT013"));
        frmMain.showScreen(Constant.SCR_NEW_SALE);
      }
    }
  }

  void btnReturnCancel_actionPerformed(ActionEvent e) {
    removeAllThing();
    frmMain.setTitle(lang.getString("TT013"));
    frmMain.showScreen(Constant.SCR_NEW_SALE);
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

  // F12
  key = new Integer(KeyEvent.VK_F12);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
  actionMap.put(key, new KeyAction(key));

  // Escape
  key = new Integer(KeyEvent.VK_ESCAPE);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
  actionMap.put(key, new KeyAction(key));

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
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnOK.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnBack.doClick();
      }
  }
    }

  void jTable2_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2 || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                btnBack.doClick();
              }
  }

}

class PanelVoidTransaction_btnOK_actionAdapter implements java.awt.event.ActionListener {
  PanelVoidTransaction adaptee;

  PanelVoidTransaction_btnOK_actionAdapter(PanelVoidTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class PanelVoidTransaction_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelVoidTransaction adaptee;

  PanelVoidTransaction_btnBack_actionAdapter(PanelVoidTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}
class PanelVoidTransaction_jTable2_keyAdapter extends java.awt.event.KeyAdapter {
  PanelVoidTransaction adaptee;

  PanelVoidTransaction_jTable2_keyAdapter(PanelVoidTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTable2_keyPressed(e);
  }
}

