//============================================================================//
//============================ Reuse NghiaLe ===================================//
//Main - Doi hang - Xem GD//
//============================================================================//






































package pos;

import java.awt.*;
import javax.swing.*;
import common.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.*;
import btm.swing.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Main - Doi hang - Xem GD</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */
//not use now
public class PanelViewTrans extends JPanel {
  private static final int VIEW_TRANS_BTN =221;

  FrameMain frmMain;
  Statement stmt = null;
  int flagButton=VIEW_TRANS_BTN;
  Utils ut=new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BJTable jTable1;
  DataAccessLayer DAL;
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 1: return String.class;
        case 2: return String.class;
//        case 3: return Double.class;
        default: return Object.class;
      }
    }
    public boolean isCellEditable(int row, int col){
      return false;
    }
  };
  int rowCount = 0;

  BJButton btnOK = new BJButton();
  BJButton btnCancel = new BJButton();

  public PanelViewTrans(FrameMain frmMain) {
    this.frmMain = frmMain;
    DAL = frmMain.getDAL();
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
//        System.out.println("N25");
    registerKeyboardActions();
    this.setLayout(new FlowLayout());
    dm.addColumn(lang.getString("TXTDate"));
    dm.addColumn(lang.getString("TXTID"));
    dm.addColumn(lang.getString("Description"));
    dm.addColumn(lang.getString("TotalAmount"));
    jTable1 = new BJTable(dm,true){
      public boolean isCellEditable(int row, int col){
        return false;
      }
    };
//    jTable1.setRowHeight(30);
//    jTable1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
//    jTable1.setPreferredSize(new Dimension(0, 0));

//    JTableHeader header = new JTableHeader();
//    header = jTable1.getTableHeader();
//    header.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
//    jTable1.setColor(Color.lightGray, Color.white);
//    jTable1.setTableHeader(header);
    jTable1.addMouseListener(new PanelViewTrans_jTable1_mouseAdapter(this));

    JScrollPane jsp = new JScrollPane(jTable1);
    jsp.getViewport().setBackground(UIManager.getColor("controlHighlight"));
    jsp.setPreferredSize(new Dimension(650, 477));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOK.setToolTipText(lang.getString("OK") + " (F1)");
    btnOK.setText(lang.getString("OK") + " (F1)");
    btnOK.addActionListener(new PanelViewTrans_btnOK_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F12)");
    btnCancel.setText("<html><center><br><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                       "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                       "ml>");
    btnCancel.addActionListener(new PanelViewTrans_btnCancel_actionAdapter(this));
    this.setBackground(SystemColor.control);
    jTable1.addKeyListener(new PanelViewTrans_jTable1_keyAdapter(this));
    this.add(jsp,null);
    jsp.setViewportView(jTable1);
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable1.getTableHeader().setResizingAllowed(false);
  }

  private ResultSet getData(String date,Statement stmt){
    ResultSet rs = null;
    try{
      String sql = " select to_char(trunc(trans_date),'dd/mm/yyyy') trans_date, trans_id, trans_desc, sum(amount) total " +
          " from BTM_POS_DETAIL_TRANS d, BTM_POS_TRANS_TYPE t  " +
          " where d.trans_type_id = t.trans_type_id and trunc(trans_date) = to_date('" + date + "', 'DD/MM/YYYY') " +
          " and (trans_flag = 0 or d.item_id not in (select item_id from BTM_POS_RETURN_ITEM where trans_id = d.TRANS_ID))  "  +
          " group by trunc(trans_date) , trans_id, trans_desc ";
//      System.out.println(sql);

      rs = DAL.executeScrollQueries(sql,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  public void showData(String date){
    ResultSet rs = null;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = getData(date,stmt);
      //reset dm
//      while (dm.getRowCount()>0) {
//        dm.removeRow(0);
//      }
//
//      while (rs.next()){
//        String trans_id = "" + rs.getDouble("trans_id");
//        dm.addRow(new Object[]{
//                  rs.getDate("trans_date"),
//                  "" + rs.getLong("trans_id"),
//                  rs.getString("trans_desc"),
//                  new Double(rs.getDouble("total"))
//        });
//        rowCount += 1;
//      }
      dm.resetIndexes();
      jTable1.setData(rs);
      stmt.close();
      jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
      jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
      jTable1.setColumnWidth(0,50);
      jTable1.setColumnWidth(1,100);
      jTable1.setHeaderName(lang.getString("TXTDate"),0);
      jTable1.setHeaderName(lang.getString("TXTID"),1);
      jTable1.setHeaderName(lang.getString("Description"),2);
      jTable1.setHeaderName(lang.getString("TotalAmount"),3);



      rs = null;
        if (dm.getRowCount()>0){
          jTable1.setRowSelectionInterval(0,0);
        }


    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  void setTable(){
    JTableHeader header = new JTableHeader();
    header = jTable1.getTableHeader();
    header.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
  }
  void jTable1_mousePressed(MouseEvent e) {
    if (e.getClickCount() == 2){
      String trans_id = jTable1.getValueAt(jTable1.getSelectedRow(),1).toString();
      frmMain.setTitle(lang.getString("TT057"));
      frmMain.showScreen(Constant.SCR_DETAIL_TRANS);
      frmMain.goToDetailTrans(trans_id);
    }
  }
  void removeRow(){
    dm.addColumn(lang.getString("TransactionDate"));
    dm.addColumn(lang.getString("TransactionID"));
    dm.addColumn(lang.getString("Type"));
    dm.addColumn(lang.getString("TransactionTotal"));


    String[] columnNames = new String[]{lang.getString("TransactionDate"),lang.getString("TransactionID"),lang.getString("Type"),lang.getString("TransactionTotal")};
    dm.setDataVector(new Object[][]{},columnNames);
    setTable();
  }
  //show button in Frame Main
  void showButton() {
    frmMain.showButton(btnOK);
    frmMain.showButton(btnCancel);
  }

  //reset data on table
  void resetData(){
    while (dm.getRowCount()>0)
      dm.removeRow(0);
    frmMain.txtMainInput.setSelectionStart(0);
    frmMain.txtMainInput.setSelectionEnd(frmMain.txtMainInput.getText().length());
  }

  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput=frmMain.getInputText();
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          if (!mainInput.equals("")){
            if (ut.checkDate(mainInput,"/")){
              showData(mainInput);
            }else{
              ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS140_DateType"));
              resetData();
            }
          }
          else
            resetData();
    }//enter

  }

  void btnOK_actionPerformed(ActionEvent e) {
    if (dm.getRowCount()>0){
      String trans_id = jTable1.getValueAt(jTable1.getSelectedRow(),1).toString();
      frmMain.setTitle(lang.getString("TT057"));
      frmMain.showScreen(Constant.SCR_DETAIL_TRANS);
      frmMain.goToDetailTrans(trans_id);
    }else{
      if (!frmMain.txtMainInput.getText().equals("")){
            if (ut.checkDate(frmMain.txtMainInput.getText(),"/")){
              showData(frmMain.txtMainInput.getText());
            }else{
              ut.showMessage(frmMain, lang.getString("TT001"),  lang.getString("MS140_DateType"));
              resetData();
            }
          }
          else
            resetData();
    }
  }
  void removeAllThing(){
    while (jTable1.getRowCount()>0){
      dm.removeRow(0);
    }
  }
  void btnCancel_actionPerformed(ActionEvent e) {
    while (jTable1.getRowCount()>0){
      dm.removeRow(0);
    }
    frmMain.flagEvenExchangeConstant = Constant.EVEN_EXCHANGE_TRANS;
    frmMain.setTitle(lang.getString("TT045"));
    frmMain.showScreen(Constant.SCR_EVEN_EXCHANGE);
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

      // ESCAPE
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
          else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue()==KeyEvent.VK_ESCAPE) {
            btnCancel.doClick();
          }
        }
   }

  void jTable1_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                btnCancel.doClick();
              }
  }


}

class PanelViewTrans_jTable1_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelViewTrans adaptee;

  PanelViewTrans_jTable1_mouseAdapter(PanelViewTrans adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.jTable1_mousePressed(e);
  }
}

class PanelViewTrans_btnOK_actionAdapter implements java.awt.event.ActionListener {
  PanelViewTrans adaptee;

  PanelViewTrans_btnOK_actionAdapter(PanelViewTrans adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class PanelViewTrans_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelViewTrans adaptee;

  PanelViewTrans_btnCancel_actionAdapter(PanelViewTrans adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelViewTrans_jTable1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewTrans adaptee;

  PanelViewTrans_jTable1_keyAdapter(PanelViewTrans adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTable1_keyPressed(e);
  }
}
