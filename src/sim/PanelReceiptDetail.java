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
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Receive -> From Supplier -> View Recipt</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelReceiptDetail extends JPanel {
    FrameMainSim frmMain;
    Utils ut=new Utils();
    ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
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
  BJButton btnDone = new BJButton();
  FlowLayout flowLayout1 = new FlowLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JPanel pnlsearch = new JPanel();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JLabel lblDate = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1 = new JTable();
  BorderLayout borderLayout3 = new BorderLayout();

    public PanelReceiptDetail(FrameMainSim frmMain) {
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
      jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
      jTable1.getTableHeader().setReorderingAllowed(false);
      jTable1.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
      dm.addColumn(lang.getString("ItemID"));
      dm.addColumn(lang.getString("StoreID"));
      dm.addColumn(lang.getString("SourceID"));
//      dm.addColumn("Total Quantity");
      jTable1 = new JTable(dm){
     public boolean isCellEditable(int row, int col){
       return false;
     }
   };
   jTable1.setPreferredSize(new Dimension(650, 500));
  jTable1.setRowHeight(30);
  jTable1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
  JTableHeader header = new JTableHeader();
    header = jTable1.getTableHeader();
    header.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    jTable1.setTableHeader(header);
    jTable1.addKeyListener(new PanelReceiptDetail_jTable1_keyAdapter(this));
    jTable1.addMouseListener(new PanelReceiptDetail_jTable1_mouseAdapter(this));
//     jTable1.addMouseListener(new PanelReceiptDetail_jTable1_mouseAdapter(this));

//    TableColumn column = new TableColumn();
//    column = jTable1.getColumn("Date");
//    column.setMaxWidth(80);
//    column.setMinWidth(10);
    JScrollPane jsp = new JScrollPane(jTable1);
    jsp.setPreferredSize(new Dimension(650,527));

   titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setLayout(borderLayout1);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setPreferredSize(new Dimension(130, 37));
    btnCancel.setText(lang.getString("Cancel") + " (F2)");
    btnCancel.addActionListener(new PanelReceiptDetail_btnCancel_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setPreferredSize(new Dimension(130, 37));
    btnDone.setText(lang.getString("Done") + " (F1)");
    pnlButton.setLayout(flowLayout1);
    jPanel2.setLayout(borderLayout2);
    pnlsearch.setLayout(null);
    pnlsearch.setDebugGraphicsOptions(0);
    pnlsearch.setMinimumSize(new Dimension(1, 1));
    pnlsearch.setOpaque(true);
    pnlsearch.setPreferredSize(new Dimension(15, 50));
    lblDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblDate.setPreferredSize(new Dimension(70, 15));
    lblDate.setText(lang.getString("Date") + "");
    lblDate.setBounds(new Rectangle(43, 4, 350, 20));
    jPanel1.setLayout(borderLayout3);
    jPanel2.setBorder(null);
    jScrollPane1.getViewport().setBackground(Color.white);
    flowLayout1.setAlignment(FlowLayout.CENTER);
    this.add(pnlButton,  BorderLayout.NORTH);
    this.add(jPanel2, BorderLayout.CENTER);
    pnlButton.add(btnDone, null);
    pnlButton.add(btnCancel, null);
    jPanel2.add(jPanel1, BorderLayout.CENTER);
    jPanel2.add(pnlsearch, BorderLayout.NORTH);
    pnlsearch.add(lblDate, null);
    jPanel1.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTable1, null);
    }
    private ResultSet getData(String date){
        ResultSet rs = null;
        try{
          String sql = "select b.order_id,a.source_id, b.item_id " +
        " from BTM_IM_INV_RECEIPT a, BTM_IM_INV_RECEIPT_ITEM b " +
        " where b.order_id = '"+date+"' group by b.order_id,a.source_id,b.item_id";
//    System.out.println(sql);
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
//             System.out.println("receipt varialble is ok");
             dm.addRow(new Object[]{

                       "" + rs.getString("item_id"),
                       "" + rs.getString("order_id"),
                       "" + rs.getString("source_id")});
//                       "" + rs.getString("received_date")});

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

        else if (identifier.intValue() == KeyEvent.VK_F2 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
      }
    }

  void jTable1_mousePressed(MouseEvent e) {
//    btnDone.requestFocus();
  }

  void jTable1_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnCancel.doClick();
               }
  }


   }



class PanelReceiptDetail_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptDetail adaptee;

  PanelReceiptDetail_btnCancel_actionAdapter(PanelReceiptDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

//class PanelReceiptDetail_jButton2_actionAdapter implements java.awt.event.ActionListener {
//  PanelReceiptDetail adaptee;
//
//  PanelReceiptDetail_jButton2_actionAdapter(PanelReceiptDetail adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.jButton2_actionPerformed(e);
//  }
//}

class PanelReceiptDetail_jTable1_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelReceiptDetail adaptee;

  PanelReceiptDetail_jTable1_mouseAdapter(PanelReceiptDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.jTable1_mousePressed(e);
  }
//  public void mouseClicked(MouseEvent e) {
//    adaptee.jTable1_mouseClicked(e);
//  }
}

class PanelReceiptDetail_jTable1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelReceiptDetail adaptee;

  PanelReceiptDetail_jTable1_keyAdapter(PanelReceiptDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTable1_keyPressed(e);
  }
}
