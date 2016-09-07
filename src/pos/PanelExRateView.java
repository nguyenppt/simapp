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

public class PanelExRateView  extends JPanel  {
    DataAccessLayer DAL;
    FrameMain frmMain;
    Utils ut = new Utils();
    ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
    boolean status=false;

    BorderLayout borderLayout1 = new BorderLayout();

    //Panels
    BJPanel pnlHeader = new BJPanel();
    BJPanel pnlCenter = new BJPanel();
    BJPanel pnlBottom = new BJPanel();
    BJPanel pnlLef = new BJPanel();
    BJPanel pnlRight = new BJPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    //buttons
    BJButton btnModify = new BJButton();
    BJButton btnBack = new BJButton();

    //Label
    JLabel lblDate = new JLabel(lang.getString("Date") + ":");
    JLabel lblLocalCurr = new JLabel(lang.getString("LocalCurrency") + ":");
    JLabel lblCurrency = new JLabel(lang.getString("ForeignCurrency") + ":");
    JLabel lblExRate = new JLabel(lang.getString("ExchangeRate") + ":");

    //textfield
    JTextField txtDate = new JTextField();
    JTextField txtLocalCurr = new JTextField();
    JTextField txtForeignCurr = new JTextField();
    JTextField txtExRate = new JTextField();



    //Table
    SortableTableModel dm = new SortableTableModel();
    BJTable table = new BJTable(dm, true) {
      public Class getColumnClass(int col) {
        switch (col) {
          case 2:
            return Long.class;
          default:
            return Object.class;
        }
      }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    StripedTableCellRenderer str = new StripedTableCellRenderer(null,
            Color.lightGray, Color.white, null, null);

    public PanelExRateView(FrameMain frmMain) {
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
        table.addMouseListener(new PanelExRateView_table_mouseAdapter(this));
        txtExRate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        txtExRate.addKeyListener(new PanelExRateView_txtExRate_keyAdapter(this));
        btnBack.addActionListener(new PanelExRateView_btnBack_actionAdapter(this));
        btnModify.addActionListener(new PanelExRateView_btnModify_actionAdapter(this));
        table.addKeyListener(new PanelExRateView_table_keyAdapter(this));
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
        pnlRight.add(txtExRate, null);
        pnlRight.add(txtForeignCurr, null);
        pnlRight.add(txtLocalCurr, null);

        pnlBottom.add(jScrollPane1, null);
        jScrollPane1.getViewport().add(table,null);

        //button done
        btnModify.setText(lang.getString("Modify") + " (F4)");
        btnModify.setToolTipText(lang.getString("Modify") + " (F4)");
        btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));

        //btn Add
        btnBack.setText("<html><center><br><b>"+lang.getString("Back") + "</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                          "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                          "ml>");
        btnBack.setToolTipText(lang.getString("Back") + " (F12)");
        btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));


        //label
        lblDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblDate.setPreferredSize(new Dimension(110, 23));

        lblLocalCurr.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblLocalCurr.setPreferredSize(new Dimension(110, 23));

        lblCurrency.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblCurrency.setPreferredSize(new Dimension(110, 23));

        lblExRate.setPreferredSize(new Dimension(110, 23));
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

        txtForeignCurr.setPreferredSize(new Dimension(220, 21));
        txtForeignCurr.setEditable(false);
        txtForeignCurr.setBounds(new Rectangle(6, 61, 220, 21));
        txtForeignCurr.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));


        txtExRate.setPreferredSize(new Dimension(220, 21));
        txtExRate.setBounds(new Rectangle(6, 88, 220, 21));


        //Table
        String[] columnNames = new String[] {lang.getString("Date") ,lang.getString("Currency"), lang.getString("ExchangeRate")};
        dm.setDataVector(new Object[][] {}
                         , columnNames);

        table.setColumnWidth(0, 150);
        table.setColumnWidth(1, 200);
        table.setColumnWidth(2, 200);


        txtDate.setText(ut.getSystemDate(1));
        txtLocalCurr.setText(DAL.getCurrencyID());
    }
    // SHOW all buttons
    void showExRateViewButton(){
        frmMain.showButton(btnModify);
        frmMain.showButton(btnBack);
    }

    //show data into table
    void showDataTable(){
        String date = "";
        String currId = "";
        String exRate = "";
        try {
            ResultSet rs = null;
            Statement stmt = null;
            stmt = DAL.getConnection().createStatement();
            rs = getAllExRate(stmt);

            while (rs.next()) {
                currId = rs.getString("CURR_ID");
                exRate = ut.formatExchangeRate(rs.getString("EXCHANGE_RATE"), 6);
                date = rs.getString("WORKDAY");
                Object[] rowData = new Object[] {date, currId, exRate};
                dm.addRow(rowData);
                float[] f1 = new float[3];
                Color.RGBtoHSB(255,255,230,f1);
                str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);
            }
            rs.close();
            stmt.close();
          }
        catch (Exception e) {
          e.printStackTrace();
        }
    }

    //get all data from table BTM_POS_EXCHANGE_RATE
    ResultSet getAllExRate(Statement stmt){
        ResultSet rs = null;
        String date = ut.getSystemDate(1);
        try {
//            String sql ="select * from BTM_POS_EXCHANGE_RATE where WORKDAY = to_date('"
//                + date + "','DD/MM/YYYY')";
          String sql="select CURR_ID,to_char(WORKDAY,'dd/mm/yyyy') WORKDAY,EXCHANGE_RATE from btm_pos_exchange_rate where (CURR_ID = 'USD' and WORKDAY = (select max ( WORKDAY ) from btm_pos_exchange_rate where CURR_ID = 'USD')) or (CURR_ID = 'EUR' and WORKDAY = (select max ( WORKDAY ) from btm_pos_exchange_rate where CURR_ID = 'EUR')) order by WORKDAY ASC";
          rs = DAL.executeQueries(sql,stmt);
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return rs;
    }

    void table_mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            status = true;
            int row = table.getSelectedRow();
            String currId = table.getValueAt(row, 1).toString();
            String rate = table.getValueAt(row, 2).toString();
            txtForeignCurr.setText(currId);
            txtExRate.setText(rate);
            txtDate.setText(table.getValueAt(row, 0).toString());
        }
    }

    void btnBack_actionPerformed(ActionEvent e) {
        frmMain.setTitle(lang.getString("TT015"));
        frmMain.showScreen(Constant.SCR_EXCHANGE_RATE);
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }
        txtExRate.setText("");
        txtForeignCurr.setText("");
        status=false;

    }

    void btnModify_actionPerformed(ActionEvent e) {
        if (status) {
            String temp = txtExRate.getText().trim();
            if (!ut.isExRate(temp)) {
                ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS055_ExchangeRateWrong"));
                txtExRate.requestFocus(true);
                return;

            }
            String date = ut.getSystemDate(1);
            String exRate = ut.formatExchangeRate(temp, 6);

            if (exRate.length() > 21) {
                ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS056_ExchangeRateTooLarge"));
                txtExRate.requestFocus(true);
                return;
            }

            if (txtExRate.getText().equals("")) {
                ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS053_ExchangeRateNotNull"));
                txtExRate.requestFocus(true);
                return;
            }

            updateExRate(exRate,txtForeignCurr.getText(),txtDate.getText());

            int row = table.getSelectedRow();
            table.setValueAt(exRate, row, 2);

            txtExRate.setText("");
            txtForeignCurr.setText("");
            status = false;
        }
    }

    void updateExRate(String exRate, String currId,String date){
        //String date = ut.getSystemDate(1);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String sql ="update BTM_POS_EXCHANGE_RATE set EXCHANGE_RATE =" + exRate +
                "where WORKDAY = to_date('"+ date + "','DD/MM/YYYY') and CURR_ID ='" + currId+ "'";
            stmt = DAL.getConnection().createStatement();
            rs = DAL.executeQueries(sql,stmt);
            rs.close();
            stmt.close();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }

  void txtExRate_keyTyped(KeyEvent e) {
//      ut.checkFloatNumber(e,txtExRate.getText(),6);
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
      if (s.length() - posCaret > lenSuff)
        e.consume();
    }
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
    // F4
    key = new Integer(KeyEvent.VK_F4);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), key);
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
        if (identifier.intValue()==KeyEvent.VK_F4){
            btnModify.doClick();
        }else if (identifier.intValue()==KeyEvent.VK_F12 || identifier.intValue()==KeyEvent.VK_ESCAPE){
            btnBack.doClick();
        }
    }
 }
 private void catchHotKey(KeyEvent e) {
             if (e.getKeyCode() == KeyEvent.VK_F12) {
               btnBack.doClick();
             }
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }


}

class PanelExRateView_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelExRateView adaptee;

  PanelExRateView_table_mouseAdapter(PanelExRateView adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelExRateView_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelExRateView adaptee;

  PanelExRateView_btnBack_actionAdapter(PanelExRateView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelExRateView_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelExRateView adaptee;

  PanelExRateView_btnModify_actionAdapter(PanelExRateView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelExRateView_txtExRate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelExRateView adaptee;

  PanelExRateView_txtExRate_keyAdapter(PanelExRateView adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtExRate_keyTyped(e);
  }
}

class PanelExRateView_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelExRateView adaptee;

  PanelExRateView_table_keyAdapter(PanelExRateView adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
