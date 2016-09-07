package pos;

import java.awt.*;
import java.awt.event.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelNetSaleByItem
    extends JPanel {
  FrameMain frmMain;
  DataAccessLayer DAL;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  BJButton btnShow = new BJButton();
  BJButton btnPrint = new BJButton();
  BJButton btnNext = new BJButton();
  BJButton btnBack = new BJButton();
  JScrollPane jScrollPane1 = new JScrollPane();
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_DESC = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_UNIT_SOLD = 4;
  private static final int COL_AMOUNT_SOLD = 5;

  SortableTableModel dm = new SortableTableModel() {
    public Class getColumnClass(int col) {
      switch (col) {
        case COL_UNIT_SOLD:
          return Double.class;
        case COL_AMOUNT_SOLD:
          return Double.class;
        default:
          return Object.class;
      }
    }
  };
  BJTable table;
  JLabel jLabel1 = new JLabel();

  public PanelNetSaleByItem(FrameMain frmMain) {
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
    table = new BJTable(dm, true);
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnBack.setToolTipText(lang.getString("Back") + " (F12)");
    btnBack.setText("<html><center><br><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                       "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                       "ml>");
    btnBack.addActionListener(new PanelNetSaleByItem_btnBack_actionAdapter(this));
    btnPrint.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPrint.setToolTipText(lang.getString("Print") + " (F2)");
    btnPrint.setText(lang.getString("Print") + " (F2)");
    btnNext.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnNext.setToolTipText(lang.getString("Next") + " (F2)");
    btnNext.setText(lang.getString("Next") + " (F2)");
    btnNext.addActionListener(new PanelNetSaleByItem_btnNext_actionAdapter(this));
    btnShow.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnShow.setToolTipText(lang.getString("Show") + " (F1)");
    btnShow.setText(lang.getString("Show") + " (F1)");
    btnShow.addActionListener(new PanelNetSaleByItem_btnShow_actionAdapter(this));
    this.setLayout(borderLayout1);
    table.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 11));
    table.addKeyListener(new PanelNetSaleByItem_table_keyAdapter(this));
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jScrollPane1.setPreferredSize(new Dimension(600, 600));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setMaximumSize(new Dimension(28, 20));
    jLabel1.setMinimumSize(new Dimension(28, 20));
    jLabel1.setPreferredSize(new Dimension(28, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText(lang.getString("NetSalesByItemForStore") + " " +
                    frmMain.getTransaction().getStore_ID() +
                    " "+lang.getString("On")+" " + ut.getSystemDate(1));
    this.setBackground(SystemColor.control);
    this.add(jScrollPane1, BorderLayout.CENTER);
    this.add(jLabel1, BorderLayout.NORTH);
    jScrollPane1.getViewport().add(table, null);
  }

  //show data
  ResultSet getData(Statement stmt) {
    ResultSet rs = null;
    String sqlStr = "select d.item_id , art_no,item_desc,attr2_name, sum(unit_qty), sum(amount) " +
        " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i " +
        "where d.ITEM_ID = i.ITEM_ID and " +
        " trunc(trans_date) = to_date('" + frmMain.txtMainInput.getText().trim() +
        "','DD/MM/YYYY') " +
        " and trans_flag = 0 " +
        "group by d.item_id, art_no,item_desc,attr2_name ";

    try {
//      System.out.println(sqlStr);
      rs = DAL.executeScrollQueries(sqlStr, stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
//  public void setTitleByItem(){
//    frmMain.setTitle("JPOS - ITEM NET SALES");
//  }
  //show button
  void showButton() {
    frmMain.showButton(btnShow);
    //frmMain.showButton(btnPrint);
    frmMain.showButton(btnNext);
    frmMain.showButton(btnBack);
  }

  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput = frmMain.getInputText();
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      showData();
//      if (mainInput.equals("")) {
//        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS060_InputDate") );
//        return;
//      }
//      else {
//        System.out.println("input"+mainInput);
//        System.out.println("ut"+ut.compareDate(ut.getDD_MM_YYYY(ut.getSystemDate()),mainInput));
//        if (ut.checkDate(mainInput, "/")) {
//          Statement stmt=null;
//          try {
//            stmt = DAL.getConnection().createStatement(ResultSet.
//                TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//          }
//          catch (Exception ex) {
//            ex.printStackTrace();
//          }
//          jLabel1.setText(lang.getString("NetSalesByItemForStore") + " " +
//                          frmMain.getTransaction().getStore_ID() + " " +
//                          lang.getString("on") +" " + mainInput);
//          table.setData(getData(stmt));
//          table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//          table.getTableHeader().setReorderingAllowed(false);
//          table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//          table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//          table.setHeaderName(lang.getString("ItemID") , COL_ITEM_ID);
//          table.setHeaderName(lang.getString("ArtNo"), COL_ART_NO);
//          table.setHeaderName(lang.getString("ItemName"), COL_ITEM_DESC);
//          table.setHeaderName(lang.getString("Size"), COL_SIZE);
//          table.setHeaderName(lang.getString("Units"), COL_UNIT_SOLD);
//          table.setHeaderName(lang.getString("Amount"), COL_AMOUNT_SOLD);
//
//          try {
//            stmt.close();
//          }
//          catch (Exception ex) {
//            ex.printStackTrace();
//          }
//        }
//        else {
//          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS061_WrongFormatDate"));
//          frmMain.txtMainInput.requestFocus(true);
//          return;
//        }
//      }
    } //enter
  }

  void btnBack_actionPerformed(ActionEvent e) {
    //reset data
    while (dm.getRowCount() > 0)
      dm.removeRow(0);
    while (frmMain.pnlNetSalesByTrans.dm.getRowCount() > 0)
      frmMain.pnlNetSalesByTrans.dm.removeRow(0);
    frmMain.setTitle(lang.getString("TT019"));
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showReportButton();
  }

  void btnShow_actionPerformed(ActionEvent e) {
    showData();
  }

  //show data
  void showData() {
    String mainInput = frmMain.getInputText();
//           System.out.println("input"+mainInput);
//      System.out.println("ut"+ut.compareDate(ut.getDD_MM_YYYY(ut.getSystemDate()),mainInput));

    if (mainInput.equals("") || !ut.compareDate(ut.getDD_MM_YYYY(ut.getSystemDate()),mainInput)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS060_InputDate"));
      frmMain.txtMainInput.requestFocus(true);
      return;
    }
    else {
      if (ut.checkDate(mainInput, "/")) {
        Statement stmt = null;
        jLabel1.setText(lang.getString("NetSalesByItemForStore") +" " +
                        frmMain.getTransaction().getStore_ID() + " " +
                        lang.getString("On")
                        + " " + mainInput);
        try {
          stmt = DAL.getConnection().createStatement(ResultSet.
              TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
        dm.resetIndexes();
        table.setData(getData(stmt));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        table.setHeaderName(lang.getString("ItemID") , COL_ITEM_ID);
        table.setHeaderName(lang.getString("ArtNo"), COL_ART_NO);
        table.setHeaderName(lang.getString("ItemName"), COL_ITEM_DESC);
        table.setHeaderName(lang.getString("Size"), COL_SIZE);
        table.setHeaderName(lang.getString("Units"), COL_UNIT_SOLD);
        table.setHeaderName(lang.getString("Amount"), COL_AMOUNT_SOLD);




        try {
          stmt.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS061_WrongFormatDate"));
        frmMain.txtMainInput.requestFocus(true);
        return;
      }
    }

  }

  void btnNext_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT022"));
    frmMain.showScreen(Constant.SCR_NET_SALES_BY_TRANS);
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

      //  if(flag == true){//4 nut
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnShow.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2) {
      /*  btnPrint.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {*/
        btnNext.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnBack.doClick();
      }
    }
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  private void catchHotKey(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      btnPrint.doClick();
    }
  }

}

class PanelNetSaleByItem_btnBack_actionAdapter
    implements java.awt.event.ActionListener {
  PanelNetSaleByItem adaptee;

  PanelNetSaleByItem_btnBack_actionAdapter(PanelNetSaleByItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelNetSaleByItem_btnShow_actionAdapter
    implements java.awt.event.ActionListener {
  PanelNetSaleByItem adaptee;

  PanelNetSaleByItem_btnShow_actionAdapter(PanelNetSaleByItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnShow_actionPerformed(e);
  }
}

class PanelNetSaleByItem_btnNext_actionAdapter
    implements java.awt.event.ActionListener {
  PanelNetSaleByItem adaptee;

  PanelNetSaleByItem_btnNext_actionAdapter(PanelNetSaleByItem adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnNext_actionPerformed(e);
  }
}

class PanelNetSaleByItem_table_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelNetSaleByItem adaptee;

  PanelNetSaleByItem_table_keyAdapter(PanelNetSaleByItem adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
