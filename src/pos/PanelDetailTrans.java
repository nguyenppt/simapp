//============================================================================//
//============================ Reuse by Nghia Le ===================================//
//============================================================================//






































































package pos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import common.DataAccessLayer;
import common.ExceptionHandle;
import common.*;
import btm.swing.*;
import java.util.*;







//Not use now
/**
 * <p>Title: </p>
 * <p>Description: Main - Doi ngang - Xem giao dich - Double click tren 1 hoa don</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelDetailTrans
    extends JPanel {
  private static final int OK_BTN = 223;

  FrameMain frmMain;
  int flagButton = OK_BTN;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  //13_3_2006
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_DESC = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_QUANTITY = 4; //
  private static final int COL_PRICE = 5;
  private static final int COL_MARKDOWN = 6;
  private static final int COL_AMOUNT = 7;

  DataAccessLayer DAL;
  BorderLayout borderLayout1 = new BorderLayout();
  SortableTableModel dm = new SortableTableModel() {
    public Class getColumnClass(int col) {
      switch (col) {
        case COL_QUANTITY:
          return Long.class;
        default:
          return Object.class;
      }
    }
  };
  JLabel lblTransID = new JLabel(lang.getString("TransactionNo") + ":");
  JLabel lblDate = new JLabel(lang.getString("Date") + ":");
  JLabel lblType = new JLabel(lang.getString("Type") + ":");
  JLabel lblStoreTrans = new JLabel(lang.getString("StoreName") + ":");
  JLabel lblOperator = new JLabel(lang.getString("Operator") + ":");
  JTextField txtTransID = new JTextField();
  JTextField txtDate = new JTextField();
  JTextField txtType = new JTextField();
  JTextField txtStoreTrans = new JTextField();
  JTextField txtOperator = new JTextField();
  JPanel jPanel1 = new JPanel();
  JPanel pnlText = new JPanel();
  JPanel pnlTable = new JPanel();
  JPanel pnlItemCode = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout3 = new GridLayout();
  BJTable jTable1;
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BJButton btnCancel = new BJButton();

  public PanelDetailTrans(FrameMain frmMain) {
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
//        System.out.println("N8");
    registerKeyboardActions();
    this.setLayout(new FlowLayout());
//    dm.addColumn("Item code");
//    dm.addColumn("Item Description");
//    dm.addColumn("Quantity");
//    dm.addColumn("Unit Price");
//    dm.addColumn("Markdown");
//    dm.addColumn("Amount Due");
    jTable1 = new BJTable(dm) {
      public Class getColumnClass(int col) {
        switch (col) {
          case COL_QUANTITY:
            return Long.class;
          case COL_PRICE:
            return Long.class;
          case COL_MARKDOWN:
            return Long.class;
          case COL_AMOUNT:
            return Long.class;
          default:
            return Object.class;
        }
      }

      public boolean isCellEditable(int row, int col) {
        return false;
      }
    };

    pnlText.setBackground(Color.lightGray);
    pnlText.setMinimumSize(new Dimension(10, 10));
    pnlText.setPreferredSize(new Dimension(660, 160));
    pnlText.setRequestFocusEnabled(true);
    pnlText.setLayout(new BorderLayout());

    pnlTable.setBackground(SystemColor.control);
    pnlTable.setMinimumSize(new Dimension(10, 10));
    pnlTable.setPreferredSize(new Dimension(660, 357));
    pnlTable.setLayout(new FlowLayout());

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
    txtTransID.setPreferredSize(new Dimension(240, 21));
    txtTransID.setEditable(false);
    txtDate.setBackground(SystemColor.control);
    txtDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtDate.setPreferredSize(new Dimension(240, 21));
    txtDate.setEditable(false);
    txtType.setBackground(SystemColor.control);
    txtType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtType.setDoubleBuffered(false);
    txtType.setPreferredSize(new Dimension(240, 21));
    txtType.setEditable(false);
    txtStoreTrans.setBackground(SystemColor.control);
    txtStoreTrans.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtStoreTrans.setPreferredSize(new Dimension(240, 21));
    txtStoreTrans.setEditable(false);
    txtOperator.setBackground(SystemColor.control);
    txtOperator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtOperator.setPreferredSize(new Dimension(240, 21));
    txtOperator.setEditable(false);
    jPanel6.setBackground(SystemColor.control);
    jPanel6.setPreferredSize(new Dimension(60, 31));
    jPanel2.setBackground(SystemColor.control);
    jPanel5.setBackground(SystemColor.control);
    jTable1.addMouseListener(new PanelDetailTrans_jTable1_mouseAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setText("<html><center><b>" +lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new PanelDetailTrans_btnCancel_actionAdapter(this));
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

//    jTable1.setBorder(null);
//    jTable1.setRequestFocusEnabled(true);

    jPanel2.setPreferredSize(new Dimension(120, 10));
    jPanel5.setPreferredSize(new Dimension(290, 10));

//    jTable1.setPreferredSize(new Dimension(650, 330));
//    jTable1.setRowHeight(30);
//    jTable1.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
//    jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
//    jTable1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));

    JScrollPane jsp = new JScrollPane(jTable1);
    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jsp.setPreferredSize(new Dimension(650, 307));
    jsp.setViewportView(jTable1);
    pnlTable.add(jsp, null);
    pnlText.add(jPanel2, BorderLayout.WEST);
    pnlText.add(jPanel5, BorderLayout.EAST);
    pnlText.add(jPanel6, BorderLayout.CENTER);

    this.add(pnlText, null);
    this.add(pnlTable, null);
//    jTable1.getTableHeader().setReorderingAllowed(false);
//    jTable1.getTableHeader().setResizingAllowed(false);
  }

  /* get new markdown of item to show on table
   * @author Vinh.Le
   * @param : tran_id, item_id, old_markdown and new_Qty
   * @return : double markdown
   */
  double getNewMarkDown(String tran_id, String item_id, double old_Markdown,
                        long new_Qty) {
    double newMarkDown = old_Markdown;
    ResultSet rs = null;
    Statement stmt = null;
    //get quantity returned of item
    String sqlStr =
        "Select RETURN_QTY From BTM_POS_RETURN_ITEM Where TRANS_ID='" + tran_id +
        "' and ITEM_ID='" + item_id + "'";
//    System.out.println(sqlStr);
//    rs = DAL.executeQueries(sqlStr);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sqlStr, stmt);
      if (rs.next()) {
        //calculate new markdown
        newMarkDown = (old_Markdown / (rs.getInt("RETURN_QTY") + new_Qty)) *
            new_Qty;
      }
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return newMarkDown;
  }

  //Get and show data out from database
  public void showData(String trans_id) {
    String[] columnNames = new String[] { lang.getString("ItemID")
        ,lang.getString("ArtNo"),
        lang.getString("ItemName"),lang.getString("Size"), lang.getString("Quantity"), lang.getString("UnitPrice"),
        lang.getString("MarkDown"), lang.getString("AmountDue")
    };
    int i = 0;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      String sql =
          "select trunc(trans_date) trans_date, trans_id, trans_desc, store_id, emp_id " +
          " from BTM_POS_DETAIL_TRANS d, BTM_POS_TRANS_TYPE t " +
          " where d.trans_type_id = t.trans_type_id and trans_id ='" + trans_id +
          "' and trans_flag <> 4 group by trunc(trans_date), trans_id, trans_desc, store_id, emp_id";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        txtTransID.setText("" + rs.getLong("trans_id"));
        txtDate.setText("" + rs.getDate("trans_date"));
        txtType.setText(rs.getString("trans_desc"));
        txtStoreTrans.setText("" + rs.getLong("store_id"));
        txtOperator.setText(rs.getString("emp_id"));
      }
      stmt.close();
//      sql = " select count(*) num" +
//          " from " +
//          "(select d.item_id, item_desc, unit_qty, unit_price, markdown, amount " +
//          " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i " +
//          " where d.item_id = i.item_id and trans_id = '" + trans_id +
//          "' and d.item_id not in (select item_id from BTM_POS_RETURN_ITEM where trans_id ='" + trans_id +"')" +
//          " and trans_flag <> 4 " +
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
//      Object[][] data = new Object[i][6];
      sql = " select item_id, art_no,item_desc,attr2_name, unit_qty, unit_price, markdown*unit_qty, unit_qty*(unit_price - markdown) " +
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

//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql, stmt);
      dm.resetIndexes();
      jTable1.setData(rs);
      jTable1.setHeaderName(lang.getString("ItemID"), COL_ITEM_ID);
      jTable1.setHeaderName(lang.getString("ArtNo"), COL_ART_NO);
      jTable1.setHeaderName(lang.getString("Quantity"), COL_QUANTITY);
      jTable1.setHeaderName(lang.getString("Size"), COL_SIZE);
      jTable1.setHeaderName(lang.getString("UnitPrice"), COL_PRICE);
      jTable1.setHeaderName(lang.getString("MarkDown"), COL_MARKDOWN);
      jTable1.setHeaderName(lang.getString("AmountDue"), COL_AMOUNT);
      jTable1.setHeaderName(lang.getString("ItemName"), COL_ITEM_DESC);
//      ut.changeDataTypetoLong(COL_QUANTITY, dm);
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

  void setTable() {
//    JTableHeader header = new JTableHeader();
//    header = jTable1.getTableHeader();
//    header.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
//    jTable1.setTableHeader(header);
    jTable1.getColumnModel().getColumn(COL_ITEM_ID).setMinWidth(10);
    jTable1.getColumnModel().getColumn(COL_ITEM_ID).setMinWidth(80);
    jTable1.getColumnModel().getColumn(COL_QUANTITY).setMinWidth(10);
    jTable1.getColumnModel().getColumn(COL_QUANTITY).setMinWidth(80);
    jTable1.getColumnModel().getColumn(COL_PRICE).setMinWidth(10);
    jTable1.getColumnModel().getColumn(COL_PRICE).setMinWidth(80);
    jTable1.getColumnModel().getColumn(COL_MARKDOWN).setMinWidth(10);
    jTable1.getColumnModel().getColumn(COL_MARKDOWN).setMinWidth(80);
    jTable1.getColumnModel().getColumn(COL_AMOUNT).setMinWidth(90);
    jTable1.getColumnModel().getColumn(COL_AMOUNT).setMinWidth(160);
    jTable1.getColumnModel().getColumn(COL_ART_NO).setMinWidth(60);
    jTable1.getColumnModel().getColumn(COL_ART_NO).setMinWidth(60);
    jTable1.getColumnModel().getColumn(COL_SIZE).setMinWidth(40);
    jTable1.getColumnModel().getColumn(COL_SIZE).setMinWidth(40);


  }

  void jTable1_mousePressed(MouseEvent e) {
    if (e.getClickCount() == 2) {
      String item_id = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
      String trans_id = txtTransID.getText();
      frmMain.setEditTextInput(true);
      frmMain.goBackEvenExchange(item_id, trans_id);
      frmMain.setTitle(lang.getString("TT045"));
      frmMain.showScreen(Constant.SCR_EVEN_EXCHANGE);
    }

  }

  //show button in Frame Main
  void showButton() {
    frmMain.showButton(btnCancel);

  }

  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput = frmMain.getInputText();
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (!ut.isDoubleString(mainInput)) {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS093_PameyNum"));
        return;
      }

    } //enter

  }

  void btnCancel_actionPerformed(ActionEvent e) {
    while (jTable1.getRowCount() > 0) {
      dm.removeRow(0);
    }
    txtTransID.setText("");
    txtDate.setText("");
    txtType.setText("");
    txtStoreTrans.setText("");
    txtOperator.setText("");
    frmMain.setEditTextInput(true);
    frmMain.setTitle(lang.getString("TT011"));
    frmMain.showScreen(Constant.SCR_VIEW_TRANS);
  }

  private void registerKeyboardActions() {
    /// set up the maps:
    InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap = getActionMap();

    // F1
    Integer key = new Integer(KeyEvent.VK_F1);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), key);
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

      if (identifier.intValue() == KeyEvent.VK_F12 ||
          identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }
}

class PanelDetailTrans_jTable1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  PanelDetailTrans adaptee;

  PanelDetailTrans_jTable1_mouseAdapter(PanelDetailTrans adaptee) {
    this.adaptee = adaptee;
  }

  public void mousePressed(MouseEvent e) {
    adaptee.jTable1_mousePressed(e);
  }
}

class PanelDetailTrans_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelDetailTrans adaptee;

  PanelDetailTrans_btnCancel_actionAdapter(PanelDetailTrans adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}
