package sim;

import java.awt.*;
import common.*;
import javax.swing.*;
import java.awt.event.*;
import btm.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.*;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelInventoryReceipt extends JPanel {
    FrameMainSim frmMain;
    Utils ut=new Utils();
//      DataAccessLayer DAL;
      DataAccessLayer DAL = new DataAccessLayer();

      DefaultTableModel dm = new DefaultTableModel(){
         public Class getColumnClass(int col){
           switch(col){
             case 0: return String.class;
             case 1: return String.class;
             case 2: return String.class;
             case 3: return Double.class;
             default: return Object.class;
           }
         }
         public boolean isCellEditable(int row, int col){
           return false;
         }
       };
       int rowCount = 0;

    BorderLayout borderLayout1 = new BorderLayout();
  BJPanel pnlButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  BJButton btnCancel = new BJButton();
  BJButton jButton2 = new BJButton();
  BJButton btnDone = new BJButton();
  FlowLayout flowLayout1 = new FlowLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JPanel pnlsearch = new JPanel();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JLabel lblDate = new JLabel();
  JTextField txtdate = new JTextField();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1 = new JTable();
  BorderLayout borderLayout3 = new BorderLayout();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

    public PanelInventoryReceipt(FrameMainSim frmMain) {
        try {
            this.frmMain =frmMain;
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    void jbInit() throws Exception {
      registerKeyboardActions();
      FrameMainSim framesim;
         DAL.getOracleConnect();
      dm.addColumn("Receipt ID");
      dm.addColumn("Store ID");
      dm.addColumn("Source ID");
      dm.addColumn("Total Quantity");
      jTable1 = new JTable(dm){
     public boolean isCellEditable(int row, int col){
       return false;
     }
   };
   jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
   jTable1.getTableHeader().setReorderingAllowed(false);
   jTable1.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
   jTable1.setPreferredSize(new Dimension(650, 500));
  jTable1.setRowHeight(30);
  jTable1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
  JTableHeader header = new JTableHeader();
    header = jTable1.getTableHeader();
    header.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    jTable1.setTableHeader(header);
    jTable1.addKeyListener(new PanelInventoryReceipt_jTable1_keyAdapter(this));
    jTable1.addMouseListener(new PanelInventoryReceipt_jTable1_mouseAdapter(this));
//     jTable1.addMouseListener(new PanelInventoryReceipt_jTable1_mouseAdapter(this));
//    TableColumn column = new TableColumn();
//    column = jTable1.getColumn("Date");
//    column.setMaxWidth(80);
//    column.setMinWidth(10);
    JScrollPane jsp = new JScrollPane(jTable1);
    jsp.setPreferredSize(new Dimension(650,527));

   titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setLayout(borderLayout1);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    //btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+"(F3)");
    btnCancel.setText(lang.getString("Cancel")+"(F3)");
    btnCancel.addActionListener(new PanelInventoryReceipt_btnCancel_actionAdapter(this));
    jButton2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    //jButton2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    jButton2.setRequestFocusEnabled(true);
    jButton2.setToolTipText(lang.getString("Search")+"(F2)");
    jButton2.setText(lang.getString("Search")+"(F2)");
    jButton2.addActionListener(new PanelInventoryReceipt_jButton2_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    //btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnDone.setText(lang.getString("Done")+"(F1)");
    pnlButton.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    jPanel2.setLayout(borderLayout2);
    pnlsearch.setLayout(null);
    pnlsearch.setDebugGraphicsOptions(0);
    pnlsearch.setMinimumSize(new Dimension(1, 1));
    pnlsearch.setOpaque(true);
    pnlsearch.setPreferredSize(new Dimension(15, 50));
    lblDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblDate.setText(lang.getString("Date"));
    lblDate.setBounds(new Rectangle(14, 14, 56, 20));
    jPanel1.setLayout(borderLayout3);
    jPanel2.setBorder(null);
    txtdate.setText("");
    txtdate.requestFocus(true);
    txtdate.setBounds(new Rectangle(70, 13, 139, 21));
    jScrollPane1.getViewport().setBackground(Color.white);
    pnlButton.setPreferredSize(new Dimension(500, 47));
    this.add(pnlButton,  BorderLayout.NORTH);
    this.add(jPanel2, BorderLayout.CENTER);
    pnlButton.add(btnDone, null);
    pnlButton.add(jButton2, null);
    pnlButton.add(btnCancel, null);
    jPanel2.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jScrollPane1, BorderLayout.EAST);
    jScrollPane1.getViewport().add(jTable1, null);
    jPanel2.add(pnlsearch, BorderLayout.NORTH);
    pnlsearch.add(txtdate,   new GridBagConstraints(1, 0, 1, 2, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 37, 0, 287), 163, 0));
    pnlsearch.add(txtdate, null);
    pnlsearch.add(lblDate, null);
    }
    private ResultSet getData(String date){
        ResultSet rs = null;
        try{
          String sql= "select a.order_id, a.store_id, a.source_id,a.received_date, sum(b.QTY_RECEIVED) TotalQty " +
              "from BTM_IM_INV_RECEIPT a, BTM_IM_INV_RECEIPT_ITEM b "+
                        "  where received_date = to_date('"+date+"', 'dd/MM/yyyy') and a.ORDER_ID=b.ORDER_ID"+
                         " group by a.ORDER_ID,a.store_id,a.source_id,a.received_date";


//                     System.out.println(sql);
                     rs = DAL.executeQueries(sql);
        }catch(Exception e){
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(e);
        }
        return rs;
      }
    //Process mainInput in FrameMain

      public void showData(String date){
         ResultSet rs = null;
         try{
           rs = getData(date);
           //reset dm
           while (dm.getRowCount()>0) {
             dm.removeRow(0);
           }

           while (rs.next()){
//             String itemdesc = ""+rs.getString("item_desc");
             dm.addRow(new Object[]{

                       "" + rs.getString("order_id"),
                       "" + rs.getLong("store_id"),
                       "" + rs.getString("source_id"),
                       new Integer(rs.getInt("TotalQty"))});
             rowCount += 1;
           }
           rs.getStatement().close();
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

     //show button in Frame Main
     void showButton(){
//       frmMain.showButton(jButton1);
//    frmMain.showButton(jButton2);
//       frmMain.showButton(btnBack);
     }

  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
  }

  void jButton2_actionPerformed(ActionEvent e) {
    String date1=txtdate.getText();
     if (!txtdate.getText().equals("")){
       if (ut.checkDate(date1,"/")){
               showData(date1);
      }else{
        JOptionPane.showMessageDialog(this, lang.getString("MS1093_InputMustDateType"),"Message", 1);
             }

//            jLabel1.setText("Inventory Receipt date:\t"+mainInput);
     }
  }

  void jTable1_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
//      System.out.println("system ok");
      String receipt_id = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
      String date = txtdate.getText();
//      PanelReceiptDetail pnlReceiptDetail = new PanelReceiptDetail();
//      frmMain.pnlReceiptDetail.setVisible(true);
      frmMain.goToReceiptDetail(receipt_id,date);
      frmMain.showScreen(Constant.SCRS_RECEIPT_DETAIL);
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
        btnDone.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2) {
        jButton2.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void jTable1_mousePressed(MouseEvent e) {
//    btnDone.requestFocus();
  }

  void jTable1_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 jButton2.doClick();
               }
  }

   }



class PanelInventoryReceipt_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelInventoryReceipt adaptee;

  PanelInventoryReceipt_btnCancel_actionAdapter(PanelInventoryReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelInventoryReceipt_jButton2_actionAdapter implements java.awt.event.ActionListener {
  PanelInventoryReceipt adaptee;

  PanelInventoryReceipt_jButton2_actionAdapter(PanelInventoryReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class PanelInventoryReceipt_jTable1_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelInventoryReceipt adaptee;

  PanelInventoryReceipt_jTable1_mouseAdapter(PanelInventoryReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTable1_mouseClicked(e);
  }
  public void mousePressed(MouseEvent e) {
    adaptee.jTable1_mousePressed(e);
  }
}

class PanelInventoryReceipt_jTable1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelInventoryReceipt adaptee;

  PanelInventoryReceipt_jTable1_keyAdapter(PanelInventoryReceipt adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTable1_keyPressed(e);
  }
}
