//============================================================================//
//============================ NOT USE NOW ===================================//
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
import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelModifyDmgGoods extends JPanel {
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_DESC = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_CURRENT_QUANTITY = 4; //
  private static final int COL_NEW_QUANTITY = 5; //

  FrameMain frmMain;
  Utils ut=new Utils();
  DataAccessLayer DAL;
  BorderLayout borderLayout1 = new BorderLayout();
//  SortableTableModel dm = new SortableTableModel();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch (col) {
        case COL_CURRENT_QUANTITY:
          return Long.class;
        default:
          return Object.class;
      }
    }
  };
  JLabel lblTransID = new JLabel("Transaction No:");
  JLabel lblDate = new JLabel("Date:");
  JLabel lblType = new JLabel("Type:");
  JLabel lblStoreTrans = new JLabel("Store:");
  JLabel lblOperator = new JLabel("Operator:");
  JTextField txtDmgGoodsID = new JTextField();
  JTextField txtFromStore = new JTextField();
  JTextField txtToStore = new JTextField();
  JTextField txtCreaterName = new JTextField();
  JTextField txtReceiverName = new JTextField();
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
  JLabel lblDate1 = new JLabel("Date:");
  JTextField txtDate = new JTextField();
  BJButton btnModify = new BJButton();

  public PanelModifyDmgGoods(FrameMain frmMain) {
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
//    System.out.println("N10");
    registerKeyboardActions();
    this.setLayout(new FlowLayout());
    jTable1 = new BJTable(dm){
      public Class getColumnClass(int col){
        switch (col) {
          case COL_CURRENT_QUANTITY:
            return Double.class;
          case COL_NEW_QUANTITY:
            return Double.class;
          /*case 4:
            return Long.class;
          case 5:
            return Long.class;*/
          default:
            return Object.class;
        }
      }
      public boolean isCellEditable(int row, int col){
        return false;
      }
    };

    pnlText.setBackground(Color.lightGray);
    pnlText.setMinimumSize(new Dimension(10,10));
    pnlText.setPreferredSize(new Dimension(660, 160));
    pnlText.setRequestFocusEnabled(true);
    pnlText.setLayout(new BorderLayout());

    pnlTable.setBackground(SystemColor.control);
    pnlTable.setMinimumSize(new Dimension(10,10));
    pnlTable.setPreferredSize(new Dimension(660, 357));
    pnlTable.setLayout(new FlowLayout());

    lblTransID.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblTransID.setPreferredSize(new Dimension(100, 21));
    lblTransID.setText("Dmg Goods ID:");
    lblDate.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblDate.setPreferredSize(new Dimension(100, 21));
    lblDate.setText("From Store:");
    lblType.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblType.setPreferredSize(new Dimension(100, 21));
    lblType.setText("To Store:");
    lblStoreTrans.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblStoreTrans.setPreferredSize(new Dimension(100, 21));
    lblStoreTrans.setText("Creater Name:");
    lblOperator.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblOperator.setPreferredSize(new Dimension(100, 21));
    lblOperator.setText("Receiver Name:");
    txtDmgGoodsID.setBackground(SystemColor.control);
    txtDmgGoodsID.setFont(new java.awt.Font("Dialog", 1, 11));
    txtDmgGoodsID.setPreferredSize(new Dimension(240, 21));
    txtDmgGoodsID.setEditable(false);
    txtFromStore.setBackground(SystemColor.control);
    txtFromStore.setFont(new java.awt.Font("Dialog", 1, 11));
    txtFromStore.setPreferredSize(new Dimension(240, 21));
    txtFromStore.setEditable(false);
    txtToStore.setBackground(SystemColor.control);
    txtToStore.setFont(new java.awt.Font("Dialog", 1, 11));
    txtToStore.setDoubleBuffered(false);
    txtToStore.setPreferredSize(new Dimension(240, 21));
    txtToStore.setEditable(false);
    txtCreaterName.setBackground(SystemColor.control);
    txtCreaterName.setFont(new java.awt.Font("Dialog", 1, 11));
    txtCreaterName.setPreferredSize(new Dimension(240, 21));
    txtCreaterName.setEditable(false);
    txtReceiverName.setBackground(SystemColor.control);
    txtReceiverName.setFont(new java.awt.Font("Dialog", 1, 11));
    txtReceiverName.setPreferredSize(new Dimension(240, 21));
    txtReceiverName.setEditable(false);
    jPanel6.setBackground(SystemColor.control);
    jPanel6.setPreferredSize(new Dimension(60, 31));
    jPanel2.setBackground(SystemColor.control);
    jPanel5.setBackground(SystemColor.control);
    btnCancel.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnCancel.setText("Cancel (F2)");
    btnCancel.addActionListener(new PanelModifyDmgGoods_btnCancel_actionAdapter(this));
    lblDate1.setPreferredSize(new Dimension(100, 21));
    lblDate1.setFont(new java.awt.Font("SansSerif", 1, 12));
    txtDate.setEditable(false);
    txtDate.setPreferredSize(new Dimension(240, 21));
    txtDate.setFont(new java.awt.Font("Dialog", 1, 11));
    txtDate.setBackground(SystemColor.control);
    btnModify.setText("Modify (F1)");
    btnModify.addActionListener(new PanelModifyDmgGoods_btnModify_actionAdapter(this));
    jTable1.addMouseListener(new PanelModifyDmgGoods_jTable1_mouseAdapter(this));
    jTable1.addKeyListener(new PanelModifyDmgGoods_jTable1_keyAdapter(this));
    jPanel2.add(lblTransID, null);
    jPanel2.add(lblDate, null);
    jPanel2.add(lblType, null);
    jPanel2.add(lblStoreTrans, null);
    jPanel2.add(lblOperator, null);
    jPanel2.add(lblDate1, null);

    jPanel6.add(txtDmgGoodsID, null);
    jPanel6.add(txtFromStore, null);
    jPanel6.add(txtToStore, null);
    jPanel6.add(txtCreaterName, null);
    jPanel6.add(txtReceiverName, null);
    jPanel6.add(txtDate, null);

    jPanel2.setPreferredSize(new Dimension(120, 10));
    jPanel5.setPreferredSize(new Dimension(290, 10));

    JScrollPane jsp = new JScrollPane(jTable1);
    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jsp.setPreferredSize(new Dimension(650, 307));
    jsp.setViewportView(jTable1);
    pnlTable.add(jsp,null);
    pnlText.add(jPanel2, BorderLayout.WEST);
    pnlText.add(jPanel5, BorderLayout.EAST);
    pnlText.add(jPanel6, BorderLayout.CENTER);

    this.add(pnlText,null);
    this.add(pnlTable,null);
  }
  //Show data
  void showData(String rtnDmgGoodsID){
    resetData();
    Statement stmt = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    String sql = "select rtn_dmg_goods_id, s1.name from_store, s2.name to_store, " +
        " concat(first_name, concat(' ',last_name)) emp_name, receiver_name, to_char(transfer_date,'DD/MM/YYYY') transfer_date " +
        " from btm_pos_rtn_damage_goods d, btm_pos_store s1, btm_pos_store s2, btm_pos_employee e " +
        " where d.TO_STORE_ID = s2.store_id and d.FROM_STORE_ID = s1.store_id and d.USER_ID = e.EMP_ID and rtn_dmg_goods_id ='" + rtnDmgGoodsID + "'";
//    System.out.println(sql);
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        txtDmgGoodsID.setText(rs.getString("rtn_dmg_goods_id"));
        txtFromStore.setText(rs.getString("from_store"));
        txtToStore.setText(rs.getString("to_store"));
        txtCreaterName.setText(rs.getString("emp_name"));
        txtReceiverName.setText(rs.getString("receiver_name"));
        txtDate.setText(rs.getString("transfer_date"));
      }
      rs.close();
      if (!txtDmgGoodsID.getText().equalsIgnoreCase("")){
        //sql = "select item_id, item_name, qty, '' new_qty from btm_pos_rtn_damage_goods_itm where rtn_dmg_goods_id ='" + txtDmgGoodsID.getText() + "'";
        sql = "select i.item_id, j.ART_NO,i.item_name, j.ATTR2_NAME,i.qty, '' new_qty from btm_pos_rtn_damage_goods_itm i,BTM_POS_ITEM_PRICE j where i.ITEM_ID=j.ITEM_ID and rtn_dmg_goods_id ='" + txtDmgGoodsID.getText() + "'";
//        System.out.println(sql);
        rs1 = DAL.executeScrollQueries(sql,stmt);
        dm.resetIndexes();
        jTable1.setData(rs1);
        jTable1.setHeaderName("Item ID",COL_ITEM_ID);
        jTable1.setHeaderName("Art No",COL_ART_NO);
        jTable1.setHeaderName("Item Name",COL_ITEM_DESC);
        jTable1.setHeaderName("Size",COL_SIZE);
        jTable1.setHeaderName("Current Qty",COL_CURRENT_QUANTITY);
        jTable1.setHeaderName("New Qty",COL_NEW_QUANTITY);
        //ut.changeDataTypetoLong(COL_CURRENT_QUANTITY,dm);//Yen.Dinh 19-06-2006
        rs1.close();
      }
      if (jTable1.getRowCount()>0){
        jTable1.setRowSelectionInterval(0,COL_ITEM_ID);
      }
      stmt.close();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  void showButton(){
    frmMain.showButton(btnModify);
    frmMain.showButton(btnCancel);
  }
  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput=frmMain.getInputText();
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (!ut.isLongIntString(mainInput)){
        ut.showMessage(frmMain,"Warning!","New quatity must be numeric");
        frmMain.txtMainInput.selectAll();
        frmMain.txtMainInput.requestFocus(true);
        return;
      }
      if (!mainInput.equals("")){
        if (jTable1.getSelectedRow() != -1){
          long soh = getSOH(jTable1.getValueAt(jTable1.getSelectedRow(),COL_ITEM_ID).toString());
          long newQty = 0;
          if (Long.parseLong(mainInput) > Long.parseLong(jTable1.getValueAt(jTable1.getSelectedRow(),COL_CURRENT_QUANTITY).toString())){
            newQty = Long.parseLong(mainInput) -
                Long.parseLong(jTable1.getValueAt(jTable1.getSelectedRow(),COL_CURRENT_QUANTITY).toString());
          }
          if (newQty > soh){
            ut.showMessage(frmMain,"Warning!", "There is not enough in you stock on hand");
            frmMain.txtMainInput.selectAll();
            frmMain.txtMainInput.requestFocus(true);
            return;
          }
          jTable1.setValueAt(mainInput,jTable1.getSelectedRow(),COL_NEW_QUANTITY);
          frmMain.txtMainInput.selectAll();
          frmMain.txtMainInput.requestFocus(true);
        }
      }
    }//enter
  }
  //get stock on hand
  long getSOH(String itemID){
    long result = 0;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select stock_on_hand from btm_pos_item_loc_soh where item_id='" + itemID + "'";
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        result = rs.getLong("stock_on_hand");
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return result;
  }
  //reset data
  void resetData(){
    while (jTable1.getRowCount()>0){
      dm.removeRow(0);
    }
    txtDmgGoodsID.setText("");
    txtFromStore.setText("");
    txtToStore.setText("");
    txtCreaterName.setText("");
    txtReceiverName.setText("");
    txtDate.setText("");
  }

  void btnModify_actionPerformed(ActionEvent e) {
    Vector vSql = new Vector();
    if (jTable1.getRowCount()>0){
      for(int i=0; i<jTable1.getRowCount(); i++){
        double currentQty = Double.parseDouble(jTable1.getValueAt(i,COL_CURRENT_QUANTITY).toString());
        double newQty = 0;
        if (jTable1.getValueAt(i,COL_NEW_QUANTITY) != null){
          newQty = Double.parseDouble(jTable1.getValueAt(i,COL_NEW_QUANTITY).toString());
        }
        double updateSOH = 0;
        if (newQty > 0){
          updateSOH = currentQty - newQty;
          String sql = "update btm_pos_item_loc_soh set stock_on_hand = stock_on_hand + " +
              updateSOH + " where item_id = '" + jTable1.getValueAt(i,COL_ITEM_ID) + "'";
//          System.out.println(sql);
          vSql.addElement(sql);
          sql = "update btm_pos_rtn_damage_goods_itm set qty = " +
              newQty + " where rtn_dmg_goods_id ='" + txtDmgGoodsID.getText() +
              "' and item_id = '" + jTable1.getValueAt(i,COL_ITEM_ID) + "'";
//          System.out.println(sql);
          vSql.addElement(sql);
        }
      }
      if (!vSql.isEmpty()){
        DAL.setBeginTrans(DAL.getConnection());
        DAL.executeBatchQuery(vSql);
        DAL.setEndTrans(DAL.getConnection());
      }
      resetData();
      frmMain.removeButton();
      frmMain.showScreen(Constant.SCR_VIEW_DAMAGE_GOODS_ITEM);
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    resetData();
    frmMain.removeButton();
    frmMain.showScreen(Constant.SCR_VIEW_DAMAGE_GOODS_ITEM);
  }

  void jTable1_mouseClicked(MouseEvent e) {
    frmMain.txtMainInput.requestFocus(true);
    frmMain.txtMainInput.selectAll();
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

 //  if(flag == true){//4 nut
     if (identifier.intValue() == KeyEvent.VK_F1 ) {
       btnModify.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F2 || identifier.intValue()==KeyEvent.VK_ESCAPE) {
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

class PanelModifyDmgGoods_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelModifyDmgGoods adaptee;

  PanelModifyDmgGoods_btnModify_actionAdapter(PanelModifyDmgGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelModifyDmgGoods_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelModifyDmgGoods adaptee;

  PanelModifyDmgGoods_btnCancel_actionAdapter(PanelModifyDmgGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelModifyDmgGoods_jTable1_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelModifyDmgGoods adaptee;

  PanelModifyDmgGoods_jTable1_mouseAdapter(PanelModifyDmgGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTable1_mouseClicked(e);
  }
}

class PanelModifyDmgGoods_jTable1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelModifyDmgGoods adaptee;

  PanelModifyDmgGoods_jTable1_keyAdapter(PanelModifyDmgGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTable1_keyPressed(e);
  }
}
