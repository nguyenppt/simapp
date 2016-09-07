package pos;

import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import btm.attr.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM InterSoft</p>
 * @author Phuoc Nguyen
 * @version 1.0
 */

public class PanelExchangeRate extends JPanel {

    DataAccessLayer DAL;
    FrameMain frmMain;
    Utils ut = new Utils();
    ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
    Vector vSql = new Vector();
    String strDate = new String();
    Vector vCurrency = new Vector();

    BorderLayout borderLayout1 = new BorderLayout();

    //Panels
    BJPanel pnlHeader = new BJPanel();
    BJPanel pnlCenter = new BJPanel();
    BJPanel pnlBottom = new BJPanel();
    BJPanel pnlLef = new BJPanel();
    BJPanel pnlRight = new BJPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    //buttons
    BJButton btnDone = new BJButton();
    BJButton btnAdd = new BJButton();
    BJButton btnDelete = new BJButton();
    BJButton btnView = new BJButton();
    BJButton btnCancel = new BJButton();

    //Label
    JLabel lblDate = new JLabel(lang.getString("Date") + ":");
    JLabel lblLocalCurr = new JLabel(lang.getString("LocalCurrency") + ":");
    JLabel lblCurrency = new JLabel(lang.getString("ForeignCurrency") + ":");
    JLabel lblExRate = new JLabel(lang.getString("ExchangeRate") + ":");

    //textfield
    JTextField txtDate = new JTextField();
    JTextField txtLocalCurr = new JTextField();
    JTextField txtExRate = new JTextField();

    //Combobox
    BJComboBox cboExRate = new BJComboBox();

    //Table
    SortableTableModel dm = new SortableTableModel();
    BJTable table = new BJTable(dm, true) {
        public Class getColumnClass(int col) {
            switch (col) {
               case 2: return Long.class;
                default:
                    return Object.class;
            }
        }
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray, Color.white, null, null);


//--------Constructor
    public PanelExchangeRate(FrameMain frmMain) {
        try {
            this.frmMain = frmMain;
            DAL = frmMain.getDAL();
            jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//////////

    void jbInit() throws Exception {

        registerKeyboardActions();
        this.setBackground(UIManager.getColor("Label.background"));
        this.setPreferredSize(new Dimension(500, 600));
        this.setLayout(borderLayout1);
        jScrollPane1.setPreferredSize(new Dimension(650, 600));
        cboExRate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        txtExRate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        txtExRate.addKeyListener(new PanelExchangeRate_txtExRate_keyAdapter(this));
        btnDone.addActionListener(new PanelExchangeRate_btnDone_actionAdapter(this));
        btnView.addActionListener(new PanelExchangeRate_btnView_actionAdapter(this));
        table.addKeyListener(new PanelExchangeRate_table_keyAdapter(this));
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlBottom, BorderLayout.SOUTH);
        this.add(pnlLef,BorderLayout.WEST);
        this.add(pnlRight,BorderLayout.EAST);

        pnlCenter.setPreferredSize(new Dimension(110, 26));
        pnlBottom.setPreferredSize(new Dimension(600, 360));
        pnlRight.setLayout(null);
        pnlRight.setPreferredSize(new Dimension(450, 100));
        pnlLef.setPreferredSize(new Dimension(30, 100));
        pnlHeader.setPreferredSize(new Dimension(500, 1));

        pnlCenter.add(lblDate,null);
        pnlCenter.add(lblLocalCurr,null);
        pnlCenter.add(lblCurrency,null);
        pnlCenter.add(lblExRate,null);

        pnlRight.add(txtDate, null);
        pnlRight.add(txtLocalCurr, null);
        pnlRight.add(cboExRate, null);
        pnlRight.add(txtExRate, null);

        pnlBottom.add(jScrollPane1, null);
        jScrollPane1.getViewport().add(table,null);

        //button done
        btnDone.setText(lang.getString("Done") +" (F1)");
        btnDone.setToolTipText(lang.getString("Done") +" (F1)");
        btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
        //btn Add
        btnAdd.setText(lang.getString("Add") + " (F2)");
        btnAdd.setToolTipText(lang.getString("Add") + " (F2)");
        btnAdd.addActionListener(new PanelExchangeRate_btnAdd_actionAdapter(this));
        btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));

        //btn Delete
        btnDelete.setText(lang.getString("Delete") + " (F8)");
        btnDelete.setToolTipText(lang.getString("Delete") + " (F8)");
        btnDelete.addActionListener(new PanelExchangeRate_btnDelete_actionAdapter(this));
        btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"),0, 12));

        //btn View
        btnView.setText(lang.getString("View") + " (F5)");
        btnView.setToolTipText(lang.getString("View") + " (F5)");
        btnView.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));

        //btn Cancel
        btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
        btnCancel.setText("<html><center><br><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                       "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                       "ml>");
        btnCancel.addActionListener(new PanelExchangeRate_btnCancel_actionAdapter(this));
        btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));

        //label
        lblDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblDate.setPreferredSize(new Dimension(110, 22));

        lblLocalCurr.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblLocalCurr.setPreferredSize(new Dimension(110, 22));

        lblCurrency.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblCurrency.setPreferredSize(new Dimension(110, 22));

        lblExRate.setPreferredSize(new Dimension(110, 22));
        lblExRate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        //TextField
        txtDate.setPreferredSize(new Dimension(220, 21));
        txtDate.setEditable(false);
        txtDate.setBounds(new Rectangle(6, 7, 220, 21));
        txtDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        txtLocalCurr.setPreferredSize(new Dimension(220, 21));
        txtLocalCurr.setEditable(false);
        txtLocalCurr.setBounds(new Rectangle(6, 34, 220, 21));
        txtLocalCurr.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        txtExRate.setPreferredSize(new Dimension(220, 21));
        txtExRate.setBounds(new Rectangle(6, 88, 220, 21));

        //Combobox
        cboExRate.setPreferredSize(new Dimension(220, 21));
        cboExRate.setBounds(new Rectangle(6, 61, 220, 21));

        //Table
        String[] columnNames = new String[] {lang.getString("Date") , lang.getString("Currency"), lang.getString("ExchangeRate")};
        dm.setDataVector(new Object[][] {}
                         , columnNames);

        table.setColumnWidth(0, 150);
        table.setColumnWidth(1, 200);
        table.setColumnWidth(2, 200);

        strDate = ut.getSystemDate(1);

        txtDate.setText(strDate);
        txtLocalCurr.setText(DAL.getCurrencyID());
    }
    //initialize screen
    void initScreen(){
      try{
        Statement stmt = null;
        stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        cboExRate.setData(getCurrency(stmt));
        stmt.close();
      }catch(Exception e){
        e.printStackTrace();
      }
    }

    ResultSet getCurrency(Statement stmt) {
        ResultSet rs = null;
        String localCurr = DAL.getCurrencyID();
        try {
            String sql = "select CURR_ID, CURR_ID from BTM_POS_CURRENCY_CDE where CURR_ID <> '" +localCurr + "'";
//            System.out.println("---------pos:"+sql)
            rs = DAL.executeScrollQueries(sql,stmt);
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return rs;

    }

    void showExRateButton(){
        frmMain.showButton(btnDone);
        frmMain.showButton(btnAdd);
        frmMain.showButton(btnDelete);
        frmMain.showButton(btnView);
        frmMain.showButton(btnCancel);
    }


    void resetData(){
        cboExRate.setSelectedIndex(0);
        txtExRate.setText("");
    }

    public boolean isHasCurrency(String date, String currency){
      Statement stmt = null;
       ResultSet rs = null;
       try {
           String sql = "select CURR_ID from BTM_POS_EXCHANGE_RATE where CURR_ID = '" +
               currency + "' and WORKDAY = to_date('" + date + "','DD/MM/YYYY')";
           stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
           rs = DAL.executeScrollQueries(sql,stmt);
           rs.previous();
           if(rs.next()){
               rs.close();
               stmt.close();
               return true;
           }
           else{
               rs.close();
               stmt.close();
               return false;
           }
       }
       catch (Exception e) {
           ExceptionHandle eh = new ExceptionHandle();
           eh.ouputError(e);
       }
       return true;
   }

   void btnCancel_actionPerformed(ActionEvent e) {
       resetData();
       while (table.getRowCount() > 0) {
           dm.removeRow(0);
       }
       vSql.clear();
       vCurrency.clear();
       Statement stmt = null;
       try{
         stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         getCurrency(stmt);
         stmt.close();
       }catch(Exception ex){
         ex.printStackTrace();
       }
       frmMain.setTitle(lang.getString("TT003"));
       frmMain.showScreen(Constant.SCR_MAIN);
   }

   void btnDelete_actionPerformed(ActionEvent e) {
       if (table.getSelectedRow() == -1) {
           return;
       }
       if (vSql.isEmpty()) {
           return;
       }
       if (table.getRowCount() == 1) {
           dm.removeRow(0);
           vSql.removeAllElements();
           vCurrency.removeAllElements();
           return;
       }
       int[] i = table.getSelectedRows();
       for (int j = 0; j < i.length; j++) {
           vSql.removeElementAt(i[0]);
           vCurrency.removeElementAt(i[0]);
           dm.removeRow(i[0]);
       }
   }


  void btnAdd_actionPerformed(ActionEvent e) {
      if (txtExRate.getText().equals("")) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS053_ExchangeRateNotNull"));
          txtExRate.requestFocus(true);
          return;
      }
      if (txtExRate.getText().equalsIgnoreCase("0")) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS054_ExchangeRateGreater0"));
          txtExRate.setText("");
          txtExRate.requestFocus(true);
          return;
      }
      String temp = txtExRate.getText().trim();
      if(!ut.isExRate(temp)){
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS055_ExchangeRateWrong"));
          txtExRate.requestFocus(true);
          return;
      }
      String date = txtDate.getText();
      String curr = cboExRate.getSelectedData();
      String exRate = ut.formatExchangeRate(temp, 6);

      if (exRate.length() > 21) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS056_ExchangeRateTooLarge"));
          txtExRate.requestFocus(true);
          return;
      }

      if(isHasCurrency(date,curr)){
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS057_CurencyExist"));
          cboExRate.requestFocus(true);
          return;
      }

      int n= table.getRowCount();
      for(int i=0; i<n; i++)
          if(String.valueOf(vCurrency.get(i)).equalsIgnoreCase(curr)){
              ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS057_CurencyExist"));
              cboExRate.requestFocus(true);
              return;
          }

      Object[] rowData = new Object[] {date, curr, exRate};
      dm.addRow(rowData);
      float[] f1 = new float[3];
      Color.RGBtoHSB(255,255,230,f1);
      str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

      resetData();
      vCurrency.addElement(curr);
      vSql.addElement(
              "Insert into BTM_POS_EXCHANGE_RATE(CURR_ID, WORKDAY, EXCHANGE_RATE) " +
              " values ('" + curr + "',to_date('" + ut.getSystemDate(1) +
              "','DD/MM/YYYY')," + exRate + ")");

//      System.out.println(exRate);
  }

  void txtExRate_keyTyped(KeyEvent e) {
//      ut.checkFloatNumber(e, txtExRate.getText(),6);
      formatExchangeRate(e,txtExRate,8,6);
  }

  private void formatExchangeRate(KeyEvent e, JTextField txt, int lenPre,
                                  int lenSuff) {
    String s = txt.getText().trim();
    int posCaret = txt.getCaretPosition();
    if (e.getKeyChar() != '.') { //neu khac '.'
      int posDot = s.indexOf(".");
      if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
          (e.getKeyChar() != e.VK_BACK_SPACE)) {
        e.consume();
      }
      else if (posDot != -1 && posDot < s.length() - 1) {
        if (e.getKeyChar() != e.VK_BACK_SPACE && e.getKeyChar() != e.VK_DELETE) {
          String s1 = s.replace('.', ',');
          String arrNum[] = s1.split(",");
          int len1 = arrNum[0].length();
          int len2 = arrNum[1].length();
          if (len2 >= lenSuff && posCaret > posDot &&
              (e.getKeyChar() != e.VK_BACK_SPACE)) {
            e.consume();
          }
          else
          if (len1 >= lenPre && posCaret < posDot &&
              (e.getKeyChar() != e.VK_BACK_SPACE)) {
            e.consume();
          }
        }
        else {
          // is back space
          if (e.getKeyChar() == e.VK_BACK_SPACE && (posCaret - 1) == posDot &&
              s.length() - 1 >= lenPre) {
            e.consume();
          }
          else if (e.getKeyChar() == e.VK_DELETE && (posCaret + 1) == posDot &&
                   s.length() - 1 >= lenPre) {
            e.consume();
          }
        }
      }
      if (posDot == -1 && (e.getKeyChar() != e.VK_BACK_SPACE)) {
        if (s.length() >= lenPre) {
          e.consume();
        }
      }
    } // neu la dau '.'
    else if (txt.getText().indexOf(",") >= 0) { //neu co roi
      e.consume();
    }
    else { //neu chua co
      if (s.length() - posCaret > lenPre)
        e.consume();
    }
  }

  void btnDone_actionPerformed(ActionEvent e) {
      if (vSql.isEmpty() == false) {
        try{
          Statement stmt = DAL.getConnection().createStatement();
          DAL.setBeginTrans(DAL.getConnection());
          DAL.executeBatchQuery(vSql,stmt);
          DAL.setEndTrans(DAL.getConnection());
          stmt.close();
        }catch(Exception ex){
          ex.printStackTrace();
        }
      }

        while (dm.getRowCount()>0){
          dm.removeRow(0);
        }
        vSql.clear();
        vCurrency.clear();
        resetData();
        frmMain.setTitle(lang.getString("TT003"));
        frmMain.showScreen(Constant.SCR_MAIN);
  }

  void btnView_actionPerformed(ActionEvent e) {
      frmMain.setTitle(lang.getString("TT014"));
      frmMain.showScreen(Constant.SCR_EXCHANGE_RATE_VIEW);
      frmMain.pnlExRateView.txtDate.setText("");
      frmMain.pnlExRateView.showDataTable();
  }

   private void registerKeyboardActions() {
    /// set up the maps:
    InputMap inputMap =getInputMap(WHEN_IN_FOCUSED_WINDOW);
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

     // F6
     key = new Integer(KeyEvent.VK_F6);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), key);
     actionMap.put(key, new KeyAction(key));

     // F7
     key = new Integer(KeyEvent.VK_F7);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), key);
     actionMap.put(key, new KeyAction(key));

     // F8
     key = new Integer(KeyEvent.VK_F8);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), key);
     actionMap.put(key, new KeyAction(key));

     // F9
     key = new Integer(KeyEvent.VK_F9);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), key);
     actionMap.put(key, new KeyAction(key));

     // F10
     key = new Integer(KeyEvent.VK_F10);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), key);
     actionMap.put(key, new KeyAction(key));
     // F11
     key = new Integer(KeyEvent.VK_F11);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), key);
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
        if (identifier.intValue()==KeyEvent.VK_F1){
            btnDone.doClick();
        }else if (identifier.intValue()==KeyEvent.VK_F2){
            btnAdd.doClick();
        }else if (identifier.intValue()==KeyEvent.VK_F8){
            btnDelete.doClick();
        }else if (identifier.intValue()==KeyEvent.VK_F5){
            btnView.doClick();
        }else if (identifier.intValue()==KeyEvent.VK_F12 || identifier.intValue()==KeyEvent.VK_ESCAPE){
            btnCancel.doClick();
        }
    }
 }
 private void catchHotKey(KeyEvent e) {
             if (e.getKeyCode() == KeyEvent.VK_F2) {
               btnAdd.doClick();
             }
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }


}

class PanelExchangeRate_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelExchangeRate adaptee;

  PanelExchangeRate_btnCancel_actionAdapter(PanelExchangeRate adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelExchangeRate_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelExchangeRate adaptee;

  PanelExchangeRate_btnDelete_actionAdapter(PanelExchangeRate adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelExchangeRate_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelExchangeRate adaptee;

  PanelExchangeRate_btnAdd_actionAdapter(PanelExchangeRate adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelExchangeRate_txtExRate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelExchangeRate adaptee;

  PanelExchangeRate_txtExRate_keyAdapter(PanelExchangeRate adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtExRate_keyTyped(e);
  }
}

class PanelExchangeRate_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelExchangeRate adaptee;

  PanelExchangeRate_btnDone_actionAdapter(PanelExchangeRate adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelExchangeRate_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelExchangeRate adaptee;

  PanelExchangeRate_btnView_actionAdapter(PanelExchangeRate adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelExchangeRate_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelExchangeRate adaptee;

  PanelExchangeRate_table_keyAdapter(PanelExchangeRate adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
