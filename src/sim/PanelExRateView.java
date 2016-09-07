package sim;

import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import btm.business.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import btm.attr.*;
import java.sql.Statement;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Exchange Rate -> View</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Phuoc Nguyen
 * @version 1.0
 */

public class PanelExRateView extends JPanel {
    DataAccessLayer DAL;
    FrameMainSim frmMain;
    ExchangeRateBusObj exRateBusObj = new ExchangeRateBusObj();
    Utils ut = new Utils();
    BorderLayout borderLayout1 = new BorderLayout();
    boolean status =false;
    Statement stmt = null;

    //Panels
    BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
    BJPanel pnlCenter = new BJPanel();
    BJPanel pnlBottom = new BJPanel();
    BJPanel pnlLef = new BJPanel();
    BJPanel pnlRight = new BJPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    //buttons
    BJButton btnModify = new BJButton();
    BJButton btnBack = new BJButton();

    //Label
    JLabel lblDate = new JLabel();//("Date:");
    JLabel lblLocalCurr = new JLabel();//("Local Currency:");
    JLabel lblCurrency = new JLabel();//("Foreign Currency:");
    JLabel lblExRate = new JLabel();//("Exchange Rate:");

    //textfield
    JTextField txtDate = new JTextField();
    JTextField txtLocalCurr = new JTextField();
    JTextField txtExRate = new JTextField();
    JTextField txtForeignCurr = new JTextField();
    //Combobox
//    BJComboBox cboExRate = new BJComboBox();

    //Table
    SortableTableModel dm = new SortableTableModel();
    BJTable table = new BJTable(dm, true) {
        public Class getColumnClass(int col) {
            return Object.class;
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray, Color.white, null, null);

    ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

    public PanelExRateView(FrameMainSim frmMain) {
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
        this.setBackground(UIManager.getColor("Label.background"));
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(borderLayout1);
        jScrollPane1.setPreferredSize(new Dimension(800, 600));
        btnBack.addActionListener(new PanelExRateView_btnBack_actionAdapter(this));
        table.addMouseListener(new PanelExRateView_table_mouseAdapter(this));
        txtLocalCurr.setBounds(new Rectangle(1, 34, 220, 21));
        btnModify.addActionListener(new PanelExRateView_btnModify_actionAdapter(this));
    txtExRate.addKeyListener(new PanelExRateView_txtExRate_keyAdapter(this));
    table.addKeyListener(new PanelExRateView_table_keyAdapter(this));
    this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlBottom, BorderLayout.SOUTH);
        this.add(pnlLef,BorderLayout.WEST);
        this.add(pnlRight,BorderLayout.EAST);

        pnlCenter.setPreferredSize(new Dimension(110, 26));
        pnlBottom.setPreferredSize(new Dimension(800, 380));
        pnlRight.setLayout(null);
        pnlRight.setPreferredSize(new Dimension(600, 100));
        pnlLef.setPreferredSize(new Dimension(50, 100));
        pnlHeader.setPreferredSize(new Dimension(850, 50));

        pnlHeader.add(btnModify, null);
        pnlHeader.add(btnBack, null);

        pnlCenter.add(lblDate,null);
        pnlCenter.add(lblLocalCurr, null);
        pnlCenter.add(lblCurrency,null);
        pnlCenter.add(lblExRate,null);

        pnlRight.add(txtDate, null);
        pnlRight.add(txtExRate, null);
        pnlRight.add(txtLocalCurr, null);
        pnlRight.add(txtForeignCurr, null);

        pnlBottom.add(jScrollPane1, null);
        jScrollPane1.getViewport().add(table,null);

        //button done
        btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        btnModify.setText(lang.getString("Modify")+"(F4)");
        btnModify.setToolTipText(lang.getString("Modify")+" (F4)");
        //btn Add
        btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        btnBack.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
        "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
        btnBack.setToolTipText(lang.getString("Back")+" (F12)");

        //label

        lblLocalCurr.setText(lang.getString("LocalCurr")+":");
        lblDate.setText(lang.getString("Date")+":");
        lblCurrency.setText(lang.getString("Currency")+":");
        lblExRate.setText(lang.getString("ExchangRate")+":");


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
        txtDate.setBounds(new Rectangle(1, 7, 220, 21));
        txtDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        txtLocalCurr.setPreferredSize(new Dimension(220, 21));
        txtLocalCurr.setEditable(false);
        txtLocalCurr.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        txtExRate.setPreferredSize(new Dimension(220, 21));
        txtExRate.setBounds(new Rectangle(1, 87, 220, 21));
        txtExRate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        //Combobox
        txtForeignCurr.setPreferredSize(new Dimension(220, 21));
        txtForeignCurr.setEditable(false);
        txtForeignCurr.setBounds(new Rectangle(1, 60, 220, 21));
        txtForeignCurr.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));


        //Table
        String[] columnNames = new String[] {lang.getString("Date"), lang.getString("Currency"), lang.getString("ExchangRate")};
        dm.setDataVector(new Object[][] {}, columnNames);

        table.setColumnWidth(0, 220);
        table.setColumnWidth(1, 230);
        table.setColumnWidth(2, 350);

        txtDate.setText("");
        txtLocalCurr.setText(DAL.getCurrencyID());

    }




    void showDataTable(){

      //String date = ut.getSystemDate(1);
        String currId="";
        String exRate="";
        String date="";
        try {
          stmt = DAL.getConnection().createStatement();
            ResultSet rs = null;
            rs = exRateBusObj.getAll(DAL, stmt);

            while (rs.next()) {
                currId = rs.getString("curr_id");
                exRate = rs.getString("exchange_rate");
                date=rs.getString("workday");
                Object[] rowData = new Object[] {
                        date, currId, exRate};
                dm.addRow(rowData);
//                str.installInTable(table, Color.white, Color.black,
//                                   Color.lightGray,
//                                   Color.white);
                float[] f1 = new float[3];
                Color.RGBtoHSB(255,255,230,f1);
                str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

            }
        }catch(Exception e){
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        try {
          stmt.close();

        }
        catch (Exception ex) {
          ex.printStackTrace();
        }

    }

    void btnBack_actionPerformed(ActionEvent e) {
        frmMain.showScreen(Constant.SCRS_EXCHANGE_RATE);
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }
        txtExRate.setText("");
        txtForeignCurr.setText("");
        status = false;
    }

    void table_mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 ){
            status = true;
            int row = table.getSelectedRow();
            String currId =  table.getValueAt(row,1).toString();
            String rate = table.getValueAt(row,2).toString();
            txtForeignCurr.setText(currId);
            txtExRate.setText(rate);
            txtDate.setText(table.getValueAt(row,0).toString());
        }
    }

  void btnModify_actionPerformed(ActionEvent e) {
    try {
        stmt = DAL.getConnection().createStatement();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }

      if(status){
          String temp = txtExRate.getText().trim();
          if(!ut.isExRate(temp)){
              ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1081_ExchangeRateWrong"));
              txtExRate.requestFocus(true);
              return;

          }
          String date = ut.getSystemDate(1);
          String exRate = ut.formatExchangeRate(temp, 6);

          if(exRate.length()>21){
              ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1082_ExchangeRateTooLarge"));
              txtExRate.requestFocus(true);
              return;
          }

          if (txtExRate.getText().equals("") ){
              ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1083_ExchangeRateNotNull"));
              txtExRate.requestFocus(true);
              return;
          }

          exRateBusObj.updateExRate(DAL,exRate,date,txtForeignCurr.getText(),stmt);

          int row = table.getSelectedRow();
          table.setValueAt(ut.formatNumberNoZero(exRate),row,2);

          txtExRate.setText("");
          txtForeignCurr.setText("");
          status=false;
      }
      try {
        stmt.close();

      }
      catch (Exception ex) {
        ex.printStackTrace();
      }

  }

  void txtExRate_keyTyped(KeyEvent e) {
//      ut.checkFloatNumber(e,txtExRate.getText(),6);
      formatExchangeRate(e, txtExRate, 8, 6);
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

    // ENTER
     key = new Integer(KeyEvent.VK_ENTER);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
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
          if (identifier.intValue() == KeyEvent.VK_F4) {
            btnModify.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnBack.doClick();
          }
        }
      }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnBack.doClick();
               }
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

class PanelExRateView_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelExRateView adaptee;

  PanelExRateView_table_mouseAdapter(PanelExRateView adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
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
