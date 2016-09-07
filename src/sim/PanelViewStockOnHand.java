//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//































package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelViewStockOnHand extends JPanel {
  DataAccessLayer DAL;
  FrameMainSim frmMain;
  Utils ut = new Utils();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BJButton btnView = new BJButton();
  BJButton btnBack = new BJButton();
  BJComboBox cboStore = new BJComboBox();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 4: return Long.class;
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm,true){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  JLabel jLabel2 = new JLabel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField txtArtNo = new JTextField();
  JTextField txtSize = new JTextField();

  public PanelViewStockOnHand(FrameMainSim frmMain) {
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
    this.setSize(new Dimension(800,600));
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel2.setPreferredSize(new Dimension(800, 50));
    jPanel2.setLayout(null);
    jPanel3.setPreferredSize(new Dimension(800, 450));
    btnView.setText("View (F5)");
    btnView.addActionListener(new PanelViewStockOnHand_btnView_actionAdapter(this));
    btnBack.setText("<html><center><b>Back </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnBack.addActionListener(new PanelViewStockOnHand_btnBack_actionAdapter(this));
    cboStore.addActionListener(new PanelViewStockOnHand_cboStore_actionAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(790, 450));
    cboStore.setFont(new java.awt.Font("Dialog", 1, 12));
    cboStore.setPreferredSize(new Dimension(300, 20));
    cboStore.setBounds(new Rectangle(90, 4, 180, 20));
    table.addKeyListener(new PanelViewStockOnHand_table_keyAdapter(this));
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText("Size:");
    jLabel2.setBounds(new Rectangle(468, 5, 64, 20));
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setText("Store :");
    jLabel1.setBounds(new Rectangle(9, 4, 59, 20));
    jLabel4.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel4.setPreferredSize(new Dimension(120, 20));
    jLabel4.setText("UPC : ");
    jLabel4.setBounds(new Rectangle(9, 29, 59, 20));
    txtArtNo.setFont(new java.awt.Font("Dialog", 1, 12));
    txtArtNo.setPreferredSize(new Dimension(180, 20));
    txtArtNo.setText("");
    txtArtNo.setBounds(new Rectangle(90, 29, 180, 20));
    txtArtNo.addKeyListener(new PanelViewStockOnHand_txtArtNo_keyAdapter(this));
    txtSize.setBounds(new Rectangle(554, 5, 180, 20));
    txtSize.addKeyListener(new PanelViewStockOnHand_txtSize_keyAdapter(this));
    txtSize.setText("");
    txtSize.setPreferredSize(new Dimension(180, 20));
    txtSize.setFont(new java.awt.Font("Dialog", 1, 12));
    this.add(jPanel1,  BorderLayout.NORTH);
    jPanel1.add(btnView, null);
    jPanel1.add(btnBack, null);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(cboStore, null);
    jPanel2.add(txtArtNo, null);
    jPanel2.add(jLabel1, null);
    jPanel2.add(jLabel4, null);
    jPanel2.add(txtSize, null);
    jPanel2.add(jLabel2, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    registerKeyboardActions();
  }

  private void registerKeyboardActions() {
// set up the maps:
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
      if (identifier.intValue() == KeyEvent.VK_F5 || identifier.intValue() == KeyEvent.VK_ENTER) {
        btnView.doClick();
      }else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnBack.doClick();
      }
    }
  }

  void initScreen(){
    //jLabel1.setText("Select a store name to view stock on hand: ");

    Statement stmt = null;
    ResultSet rs = null;
    try{
      String sql = "select store_id, name from btm_im_store order by name";
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      cboStore.setData1(rs);
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

  }


  void cboStore_actionPerformed(ActionEvent e) {
    if (cboStore.getSelectedIndex() == 0 ){
      //jLabel1.setText("Select a store name to view stock on hand: ");
      while(table.getRowCount()>0){
        dm.removeRow(0);
      }
    }else{
      //jLabel1.setText("View stock on hand of " + cboStore.getSelectedItem() + " store: ");
//TrungNT      viewSOH(Long.parseLong(cboStore.getSelectedData().toString()));
    }
  }
  //view stock on hand
  void viewSOH(long storeID){
    String artNo = txtArtNo.getText().trim();
    String size = txtSize.getText().trim();
    String where="";
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }

    Statement stmt = null;
    ResultSet rs = null;
    try{
      if (!artNo.equals("") && !size.equals("")){
        where = " and im.art_no= '"+artNo+  "' and UPPER(ad.attr_dtl_desc) LIKE UPPER('" +size+"')";
      }else if (!artNo.equals("")){
        where = " and im.art_no= '"+artNo+ "'";
      }

      String sql = "select ils.item_id, im.art_no, item_name, ad.attr_dtl_desc, stock_on_hand " +
          " from btm_im_item_loc_soh ils, btm_im_item_master im,  btm_im_attr_dtl ad " +
          " where ils.item_id = im.item_id and im.status <> 'D' " +
          " and im.attr2_dtl_id = ad.attr_dtl_id and ils.store_id = " + storeID + " "+where+" order by im.art_no asc ,im.item_id asc";

//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      dm.resetIndexes();
      table.setData(rs);
      table.setHeaderName("Item ID", 0);
      table.setHeaderName("UPC", 1);
      table.setHeaderName("Item Name", 2);
      table.setHeaderName("Size",3);
      table.setHeaderName("Quantity",4);
     // ut.changeDataTypetoLong(4,dm);//Yen.Dinh 19-06-2006
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

  }

  void btnView_actionPerformed(ActionEvent e) {
    if (cboStore.getSelectedIndex() != 0 ){
      //jLabel1.setText("View stock on hand of " + cboStore.getSelectedItem() + " store: ");
      viewSOH(Long.parseLong(cboStore.getSelectedData().toString()));
    }
  }

  void btnBack_actionPerformed(ActionEvent e) {
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
    frmMain.showScreen(Constant.SCRS_STOCK_ON_HAND);
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnBack.doClick();
               }
  }

  void txtArtNo_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
      if (!txtArtNo.getText().equalsIgnoreCase("")&&!txtSize.getText().equalsIgnoreCase("")) {
        for (int i = 0; i < table.getRowCount(); i++) {
          if (table.getValueAt(i,
                               1).toString().equalsIgnoreCase(txtArtNo.getText())&&table.getValueAt(i,
              3).toString().equalsIgnoreCase(txtSize.getText())) {
            //show current row
            table.setRowSelectionInterval(i, i);
            Rectangle bounds = table.getCellRect(i, 0, true);
            jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

            return;
          }
        }
      }else{
        txtSize.requestFocus(true);
      }
    }
  }

  void txtSize_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
      if (!txtArtNo.getText().equalsIgnoreCase("")&&!txtSize.getText().equalsIgnoreCase("")) {
        for (int i = 0; i < table.getRowCount(); i++) {
          if (table.getValueAt(i,
                               1).toString().equalsIgnoreCase(txtArtNo.getText()) &&
              table.getValueAt(i,
                               3).toString().equalsIgnoreCase(txtSize.getText())) {
            //show current row
            table.setRowSelectionInterval(i, i);
            Rectangle bounds = table.getCellRect(i, 0, true);
            jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

            return;
          }
        }
      }
      else {
        txtArtNo.requestFocus(true);
      }
    }

  }
}

class PanelViewStockOnHand_cboStore_actionAdapter implements java.awt.event.ActionListener {
  PanelViewStockOnHand adaptee;

  PanelViewStockOnHand_cboStore_actionAdapter(PanelViewStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboStore_actionPerformed(e);
  }
}

class PanelViewStockOnHand_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelViewStockOnHand adaptee;

  PanelViewStockOnHand_btnView_actionAdapter(PanelViewStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelViewStockOnHand_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelViewStockOnHand adaptee;

  PanelViewStockOnHand_btnBack_actionAdapter(PanelViewStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelViewStockOnHand_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewStockOnHand adaptee;

  PanelViewStockOnHand_table_keyAdapter(PanelViewStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class PanelViewStockOnHand_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewStockOnHand adaptee;

  PanelViewStockOnHand_txtArtNo_keyAdapter(PanelViewStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelViewStockOnHand_txtSize_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewStockOnHand adaptee;

  PanelViewStockOnHand_txtSize_keyAdapter(PanelViewStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSize_keyTyped(e);
  }
}
