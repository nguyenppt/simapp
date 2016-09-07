package pos;

import java.awt.*;
import javax.swing.*;
import common.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.*;
import btm.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: (Error on grid)</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelViewTransaction extends JPanel {
    private static final int VIEWTRANSACTION_BTN = 230;
    private static final int SEARCHTRANSACTION_BTN = 231;

  FrameMain frmMain;
  Statement stmt = null;
  int flagButton=VIEWTRANSACTION_BTN;
  Utils ut=new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague,ut.sCountry);
  BJTable jTable1;
  DataAccessLayer DAL;
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 1: return String.class;
        case 2: return String.class;
        case 3: return String.class;
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
  JTextField txtPriceTo = new JTextField();
  JLabel jLabel1 = new JLabel();
  JTextField txtPriceFrom = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField txtUPC = new JTextField();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField txtTransID = new JTextField();
  JLabel jLabel5 = new JLabel();

  public PanelViewTransaction(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setLayout(new FlowLayout());
    /*dm.addColumn("TXN Date");
    dm.addColumn("TXN ID");
    dm.addColumn("Customer");
    dm.addColumn("TXN Description");
    dm.addColumn("Total Amount");*/

    dm.addColumn(lang.getString("TXNDate"));
    dm.addColumn(lang.getString("TXNID"));
    dm.addColumn(lang.getString("Customer"));
    dm.addColumn(lang.getString("Description"));
    dm.addColumn(lang.getString("TotalAmount"));

    /*dm.addColumn("Ngay Giao dich");
    dm.addColumn("Ma giao dich");
    dm.addColumn("Khach hang");
    dm.addColumn("Mo ta");
    dm.addColumn("Tong cong");*/


    jTable1 = new BJTable(dm,true){
      public boolean isCellEditable(int row, int col){
        return false;
      }
    };

//    jTable1.setRowHeight(30);
//    jTable1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));

//
//    JTableHeader header = new JTableHeader();
//    header = jTable1.getTableHeader();
//    header.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
//    jTable1.setTableHeader(header);
    jTable1.addMouseListener(new PanelViewTransaction_jTable1_mouseAdapter(this));

    JScrollPane jsp = new JScrollPane(jTable1);
    jsp.getViewport().setBackground(SystemColor.control);
    jsp.setPreferredSize(new Dimension(650, 477));
    jsp.setViewportView(jTable1);
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOK.setToolTipText(lang.getString("OK") + " (F1)");
    btnOK.setText(lang.getString("OK") + " (F1)");
    btnOK.addActionListener(new PanelViewTransaction_btnOK_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F12)");
    btnCancel.setText("<html><center><b>" +lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new PanelViewTransaction_btnCancel_actionAdapter(this));
    this.setBackground(SystemColor.control);
    this.setMaximumSize(new Dimension(32767, 32767));
    this.setToolTipText("");
    jTable1.addKeyListener(new PanelViewTransaction_jTable1_keyAdapter(this));
    txtPriceTo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPriceTo.setPreferredSize(new Dimension(60, 22));
    txtPriceTo.addKeyListener(new PanelViewTransaction_txtPriceTo_keyAdapter(this));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(30, 15));
    jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel1.setText(lang.getString("To") + ":");
    txtPriceFrom.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPriceFrom.setPreferredSize(new Dimension(60, 22));
    txtPriceFrom.setText("");
    txtPriceFrom.addKeyListener(new PanelViewTransaction_txtPriceFrom_keyAdapter(this));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(40, 15));
    jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel2.setText(lang.getString("From") + ":");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(30, 15));
    jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel3.setText(lang.getString("UPC") + ":");
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUPC.setMinimumSize(new Dimension(6, 21));
    txtUPC.setPreferredSize(new Dimension(140, 22));
    txtUPC.addKeyListener(new PanelViewTransaction_txtUPC_keyAdapter(this));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setForeground(Color.blue);
    jLabel4.setMinimumSize(new Dimension(79, 16));
    jLabel4.setPreferredSize(new Dimension(79, 22));
    jLabel4.setRequestFocusEnabled(true);
    jLabel4.setText("( x 1000 VND )");
    txtTransID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtTransID.setMinimumSize(new Dimension(6, 21));
    txtTransID.setPreferredSize(new Dimension(140, 22));
    txtTransID.addKeyListener(new PanelViewTransaction_txtTransID_keyAdapter(this));
    jLabel5.setText(lang.getString("ID") + ":");
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel5.setPreferredSize(new Dimension(30, 15));
    jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
    this.add(jLabel5);
    this.add(txtTransID);
    this.add(jLabel3, null);
    this.add(txtUPC, null);
    this.add(jLabel2, null);
    this.add(txtPriceFrom, null);
    this.add(jLabel1, null);
    this.add(txtPriceTo, null);
    this.add(jLabel4, null);
    this.add(jsp,null);
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

  //view Data
  private ResultSet getData(String date,Statement stmt){
    ResultSet rs = null;
    String itemID = "";
    String transID = "";
    double priceFrom = 0;
    try{
     String sql = " select to_char(trunc(trans_date),'dd/mm/yyyy') trans_date, trans_id,c.FIRST_NAME, trans_desc, sum(amount) total " +
         " from BTM_POS_DETAIL_TRANS d, BTM_POS_TRANS_TYPE t, BTM_POS_CUSTOMER c  " +
         " where d.trans_type_id = t.trans_type_id and trunc(trans_date) = to_date('" + date + "', 'DD/MM/YYYY') and c.CUST_ID = d.CUST_ID ";
     if(!txtTransID.getText().trim().equalsIgnoreCase("")){
       transID = txtTransID.getText().trim();
       sql += " and d.trans_id  = '" + transID + "' ";
     }

     if(!txtUPC.getText().trim().equalsIgnoreCase("") && txtTransID.getText().trim().equalsIgnoreCase("")){
       itemID = getItemCode(txtUPC.getText().trim());
       sql += " and d.trans_id in ( select trans_id from BTM_POS_DETAIL_TRANS  where ITEM_ID = '" + itemID + "') ";
     }
     sql +=  " group by trunc(trans_date), trans_id,c.FIRST_NAME, trans_desc ";
     if(!txtPriceFrom.getText().trim().equalsIgnoreCase("")){
       priceFrom = Double.parseDouble(txtPriceFrom.getText().trim()+"000");
       sql +=" Having sum(amount) >=" + priceFrom;
     }

     if(!txtPriceTo.getText().trim().equalsIgnoreCase("")){
       if(!txtPriceFrom.getText().trim().equalsIgnoreCase("")){
         sql += " and sum(amount) <=" +  Double.parseDouble(txtPriceTo.getText().trim() + "000");
       }else{
         sql += " Having sum(amount) <=" +  Double.parseDouble(txtPriceTo.getText().trim() + "000");
       }
     }
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  private ResultSet getData1(String date, Statement stmt){
    ResultSet rs = null;
    try{
      String sql = " select distinct trans_id from btm_pos_return_item " +
          " where trunc(trans_date) = to_date('" + date + "', 'DD/MM/YYYY')";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  public void showData(String date){
    if (date.equals("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS060_InputDate"));
      frmMain.txtMainInput.requestFocus(true);
      return;
    }
    else {
      if ( !ut.compareDate(ut.getDD_MM_YYYY(ut.getSystemDate()),date)) {
        DialogCheckAccount dlgCheckAccount = new DialogCheckAccount(frmMain,
            lang.getString("TT044"), true, frmMain); //lang.getString("TT044")
        dlgCheckAccount.setUserFlag(ut.VIEWER_ACCOUNT);
        dlgCheckAccount.setLocation(210, 170);
        dlgCheckAccount.setVisible(true);
        if (!dlgCheckAccount.done) {
          return;
        }
      }
      if (ut.checkDate(date, "/")) {
        ResultSet rs = null;
        try{
          stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
          rs = getData(date,stmt);
          dm.resetIndexes();
          jTable1.setData(rs);
          stmt.close();
          jTable1.setColumnWidth(0,50);
          jTable1.setColumnWidth(1,100);

          jTable1.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
          jTable1.setHeaderName(lang.getString("TXNDate"),0);
          jTable1.setHeaderName(lang.getString("TXNID"),1);
          jTable1.setHeaderName(lang.getString("Customer"),2);
          jTable1.setHeaderName(lang.getString("Description"),3);
          jTable1.setHeaderName(lang.getString("TotalAmount"),4);

          if (dm.getRowCount()>0){
            jTable1.setRowSelectionInterval(0,0);
          }
        }catch(Exception e){
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(e);
        }

      }
      else {
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS061_WrongFormatDate"));
        frmMain.txtMainInput.requestFocus(true);
        return;
      }
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
      frmMain.goToDetailTransaction(trans_id);
      frmMain.setTitle(lang.getString("TT057"));
      frmMain.showScreen(Constant.SCR_DETAIL_TRANSACTION);
    }
  }
  void removeRow(){
    //String[] columnNames = new String[]{"Date","Transaction ID","Trans Type","Transaction Total"};
    String[] columnNames = new String[]{lang.getString("TXNDate"),lang.getString("TXNID"),lang.getString("Type"),lang.getString("TotalAmount")};


    dm.setDataVector(new Object[][]{},columnNames);
    setTable();
  }
  //show button in Frame Main
  void showButton() {
    frmMain.showButton(btnOK);
    frmMain.showButton(btnCancel);
  }
  //reset data
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
      if (mainInput.equalsIgnoreCase("s")){
        flagButton = SEARCHTRANSACTION_BTN;
        frmMain.setInputText("");
        frmMain.setInputLabel(lang.getString("MS139_SearchTrans"));
        frmMain.setButtonInVisible();
        return;
      }
      if (flagButton == VIEWTRANSACTION_BTN){
        showData(mainInput);
//        if (!mainInput.equals("")){
//          if (ut.checkDate(mainInput, "/")) {
//            showData(mainInput);
//          }
//          else {
//            ut.showMessage(frmMain,lang.getString("TT001"),  lang.getString("MS140_DateType"));
//            resetData();
//          }
//        }
//        else
//          resetData();
      }
      if (flagButton == SEARCHTRANSACTION_BTN){
        if (mainInput.length() < 3){
          ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS074_RequireThreeLetters"));
          return;
        }
        DialogSearchTransaction search = new DialogSearchTransaction(frmMain,lang.getString("TT058"),true);
        search.setData(mainInput);
        if (search.done){
          String trans_id = search.getData();
          flagButton = VIEWTRANSACTION_BTN;
          frmMain.setInputLabel(" ");
          frmMain.goToDetailTransaction(trans_id);
          frmMain.setTitle(lang.getString("TT057"));
          frmMain.showScreen(Constant.SCR_DETAIL_TRANSACTION);
          frmMain.setButtonVisible();
        }
      }
    }//enter
  }

  void btnOK_actionPerformed(ActionEvent e) {
    if (dm.getRowCount()>0){
      String trans_id = jTable1.getValueAt(jTable1.getSelectedRow(),1).toString();
      frmMain.goToDetailTransaction(trans_id);
      frmMain.setTitle(lang.getString("TT057"));
      frmMain.showScreen(Constant.SCR_DETAIL_TRANSACTION);
    }else{
      if (!frmMain.txtMainInput.getText().equals("")) {
        showData(frmMain.txtMainInput.getText());
      }
    }
  }
  void btnCancel_actionPerformed(ActionEvent e) {
    while (jTable1.getRowCount()>0){
      dm.removeRow(0);
    }
    txtUPC.setText("");
    txtPriceFrom.setText("");
    txtPriceTo.setText("");
    flagButton = VIEWTRANSACTION_BTN;
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
    // F3
    key = new Integer(KeyEvent.VK_F3);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), key);
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

    //  if(flag == true){//4 nut
        if (identifier.intValue() == KeyEvent.VK_F1 ) {
          btnOK.doClick();
        }else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue()==KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
       }
    }

  void jTable1_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F12) {
                btnCancel.doClick();
    }
  }

  void txtUPC_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtUPC,Constant.LENGHT_UPC_INPUT);
    ut.checkNumber(e);
  }

  void txtPriceFrom_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtUPC,13);
    ut.checkNumberUpdate(e,txtPriceFrom.getText());
  }

  void txtPriceTo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtUPC,13);
    ut.checkNumberUpdate(e,txtPriceTo.getText());
  }

  void txtUPC_keyPressed(KeyEvent e) {
    processSearch(e);
  }

  void txtPriceFrom_keyPressed(KeyEvent e) {
    processSearch(e);
  }

  void txtPriceTo_keyPressed(KeyEvent e) {
    processSearch(e);
  }
  void processSearch(KeyEvent e){

    String mainInput=frmMain.getInputText();
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (flagButton == VIEWTRANSACTION_BTN){
        if (!mainInput.equals("")){
          if (ut.checkDate(mainInput, "/")) {
            showData(mainInput);
          }
          else {
            ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS140_DateType"));
            resetData();
          }
        }
        else
          resetData();
      }
    }//enter
  }

  public void txtTransID_keyPressed(KeyEvent e) {
    processSearch(e);
  }

}

class PanelViewTransaction_txtTransID_keyAdapter
    extends KeyAdapter {
  private PanelViewTransaction adaptee;
  PanelViewTransaction_txtTransID_keyAdapter(PanelViewTransaction adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.txtTransID_keyPressed(e);
  }
}

class PanelViewTransaction_jTable1_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelViewTransaction adaptee;
  PanelViewTransaction_jTable1_mouseAdapter(PanelViewTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.jTable1_mousePressed(e);
  }
}

class PanelViewTransaction_btnOK_actionAdapter implements java.awt.event.ActionListener {
  PanelViewTransaction adaptee;

  PanelViewTransaction_btnOK_actionAdapter(PanelViewTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}


class PanelViewTransaction_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelViewTransaction adaptee;

  PanelViewTransaction_btnCancel_actionAdapter(PanelViewTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelViewTransaction_jTable1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewTransaction adaptee;

  PanelViewTransaction_jTable1_keyAdapter(PanelViewTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTable1_keyPressed(e);
  }
}

class PanelViewTransaction_txtUPC_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewTransaction adaptee;

  PanelViewTransaction_txtUPC_keyAdapter(PanelViewTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtUPC_keyPressed(e);
  }
}

class PanelViewTransaction_txtPriceFrom_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewTransaction adaptee;

  PanelViewTransaction_txtPriceFrom_keyAdapter(PanelViewTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPriceFrom_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtPriceFrom_keyPressed(e);
  }
}

class PanelViewTransaction_txtPriceTo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewTransaction adaptee;

  PanelViewTransaction_txtPriceTo_keyAdapter(PanelViewTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPriceTo_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtPriceTo_keyPressed(e);
  }
}

