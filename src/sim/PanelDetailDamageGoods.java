package sim;

import common.*;
import btm.attr.StockOnHand;
import btm.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
import javax.swing.border.*;
import btm.business.*;
import java.awt.print.*;
import java.util.ResourceBundle;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Receive -> Damage Goods -> Details</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelDetailDamageGoods extends JPanel {
  //Declare Data Access Layer
  DataAccessLayer DAL;
  //Declare Utils
  Utils ut = new Utils();
  //Declare Frame Main
  FrameMainSim frmMain;
  //Declare a batch update
  Statement stmt = null;
  Vector vInfo = new Vector();//Vector save array info of Receipt Item
  ItemMasterBusObj  bItemMasterBusObj = new ItemMasterBusObj();
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
//   SortableTableModel dm = new SortableTableModel();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 2: return Long.class;
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm,true){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JLabel lblStoreID = new JLabel();
  JLabel lblReceiptDate = new JLabel();
  BJButton btnCancel = new BJButton();
  BJButton btnDone = new BJButton();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  GridLayout gridLayout1 = new GridLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel lblReceiptID = new JLabel();
  JTextField txtReceiptID = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  JTextField txtReceiptDate = new JTextField();
  JLabel lblDescription = new JLabel();
  JTextArea txtDescription = new JTextArea();
  TitledBorder titledBorder1;
  JLabel lblISupplier = new JLabel();
  JTextField txtToStore = new JTextField();
  boolean flagSetHotKeyForAdd = true;
  JTextField txtFromStore = new JTextField();
  JTextField txtCreaterName = new JTextField();
  JLabel lblReceiptDate1 = new JLabel();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelDetailDamageGoods(FrameMainSim frmMain) {
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
    registerKeyboardActions();
    titledBorder1 = new TitledBorder("");
    this.setLayout(borderLayout1);
    jPanel1.setPreferredSize(new Dimension(800, 50));
    this.setPreferredSize(new Dimension(800, 600));
    jPanel2.setPreferredSize(new Dimension(800, 150));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(800, 380));
    jPanel3.setLayout(gridLayout1);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(760, 395));
    jPanel4.setPreferredSize(new Dimension(400, 150));
    jPanel4.setLayout(borderLayout3);
    jPanel5.setMinimumSize(new Dimension(300, 150));
    jPanel5.setLayout(borderLayout4);
    jPanel6.setPreferredSize(new Dimension(120, 150));
    jPanel6.setLayout(flowLayout1);
    jPanel7.setPreferredSize(new Dimension(180, 150));
    jPanel7.setLayout(flowLayout4);
    jPanel10.setPreferredSize(new Dimension(50, 150));
    jPanel8.setPreferredSize(new Dimension(100, 150));
    jPanel8.setLayout(flowLayout2);
    jPanel9.setPreferredSize(new Dimension(180, 150));
    jPanel9.setLayout(flowLayout3);
    lblStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStoreID.setPreferredSize(new Dimension(110, 20));
    lblStoreID.setText(lang.getString("FromStore")+" :");
    lblReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReceiptDate.setPreferredSize(new Dimension(90, 20));
    lblReceiptDate.setText(lang.getString("ReceiptDate")+":");
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Back")+" (F2)");
    btnCancel.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnCancel.addActionListener(new PanelDetailDamageGoods_btnCancel_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Print")+"(F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Print")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    lblReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReceiptID.setPreferredSize(new Dimension(110, 20));
    lblReceiptID.setText(lang.getString("DamageGoodsID")+":");
    txtReceiptID.setBackground(SystemColor.control);
    txtReceiptID.setEnabled(true);
    txtReceiptID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtReceiptID.setPreferredSize(new Dimension(225, 20));
    txtReceiptID.setEditable(false);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    txtReceiptDate.setBackground(SystemColor.control);
    txtReceiptDate.setEnabled(true);
    txtReceiptDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtReceiptDate.setMinimumSize(new Dimension(6, 21));
    txtReceiptDate.setPreferredSize(new Dimension(225, 20));
    txtReceiptDate.setToolTipText("");
    txtReceiptDate.setEditable(false);
    txtReceiptDate.setText(ut.getSystemDate2());
    lblDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblDescription.setPreferredSize(new Dimension(90, 20));
    lblDescription.setText(lang.getString("Description")+":");
    txtDescription.setBackground(SystemColor.control);
    txtDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDescription.setBorder(BorderFactory.createLoweredBevelBorder());
    txtDescription.setDebugGraphicsOptions(0);
    txtDescription.setPreferredSize(new Dimension(225, 46));
    txtDescription.setEditable(false);
    lblISupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblISupplier.setPreferredSize(new Dimension(110, 20));
    lblISupplier.setText(lang.getString("ToStore")+":");
    txtToStore.setBackground(SystemColor.control);
    txtToStore.setEnabled(true);
    txtToStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtToStore.setPreferredSize(new Dimension(225, 20));
    txtToStore.setEditable(false);
    txtToStore.setText("");
    txtFromStore.setText("");
    txtFromStore.setPreferredSize(new Dimension(225, 20));
    txtFromStore.setEditable(false);
    txtFromStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtFromStore.setBackground(SystemColor.control);
    txtFromStore.setEnabled(true);
    txtCreaterName.setToolTipText("");
    txtCreaterName.setText("");
    txtCreaterName.setPreferredSize(new Dimension(225, 20));
    txtCreaterName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCreaterName.setEditable(false);
    lblReceiptDate1.setText(lang.getString("CreaterName")+":");
    lblReceiptDate1.setPreferredSize(new Dimension(90, 20));
    lblReceiptDate1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel7.add(txtReceiptID, null);
    jPanel7.add(txtFromStore, null);
    jPanel7.add(txtToStore, null);
    jPanel6.add(lblReceiptID, null);
    jPanel6.add(lblStoreID, null);
    jPanel6.add(lblISupplier, null);
    this.add(jPanel1,  BorderLayout.NORTH);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel4,  BorderLayout.WEST);
    jPanel4.add(jPanel6,  BorderLayout.WEST);
    jPanel4.add(jPanel7,  BorderLayout.CENTER);
    jPanel4.add(jPanel10,  BorderLayout.EAST);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel8,  BorderLayout.WEST);
    jPanel8.add(lblReceiptDate, null);
    jPanel8.add(lblReceiptDate1, null);
    jPanel8.add(lblDescription, null);
    jPanel5.add(jPanel9,  BorderLayout.CENTER);
    jPanel9.add(txtReceiptDate, null);
    jPanel9.add(txtCreaterName, null);
    jPanel9.add(txtDescription, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    jPanel1.add(btnDone, null);
    jPanel1.add(btnCancel, null);
    String[] columnNames = new String[]{lang.getString("ItemID"),lang.getString("ItemName"),lang.getString("Quantity")};
    dm.setDataVector(new Object[][]{},columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn(lang.getString("ItemID")).setPreferredWidth(150);
    table.getColumn(lang.getString("ItemName")).setPreferredWidth(300);
    table.getColumn(lang.getString("Quantity")).setPreferredWidth(350);
  }

  //show data
  void showData(String receiptDamageGoodsID){
    Statement stmt = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    String sql = "select RECEIPT_DMG_GOODS_ID, s1.name from_store, s2.name to_store, to_char(r.RECEIPT_DATE,'DD/MM/YYYY') receipt_date, concat(first_name, concat(' ',last_name)) emp_name, r.DESCRIPTION " +
        " from btm_im_receive_dmg_goods r, btm_im_store s1, btm_im_store s2, btm_im_employee e " +
        " where r.FROM_STORE_ID = s1.STORE_ID and r.TO_STORE_ID = s2.STORE_ID and r.CREATER_ID = e.emp_id and r.RECEIPT_DMG_GOODS_ID = '" + receiptDamageGoodsID + "'";
//    System.out.println(sql);
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()){
        txtReceiptID.setText(rs.getString("RECEIPT_DMG_GOODS_ID"));
        txtFromStore.setText(rs.getString("from_store"));
        txtToStore.setText(rs.getString("to_store"));
        txtReceiptDate.setText(rs.getString("receipt_date"));
        txtCreaterName.setText(rs.getString("emp_name"));
        txtDescription.setText(rs.getString("description"));
        sql = "select distinct im.item_id, im.item_name, im.art_no, ph.attr2_name, r.QTY " +
            " from btm_im_receive_dmg_goods_item r, btm_im_item_master im, btm_im_price_hist ph " +
            " where ph.item_id = r.item_id and ph.item_id = im.item_id and r.RECEIPT_DMG_GOODS_ID = '" + receiptDamageGoodsID + "'";
        rs1 = DAL.executeScrollQueries(sql,stmt);
        dm.resetIndexes();
        table.setData(rs1);
        table.setHeaderName("Item id",0);
        table.setHeaderName("Item name",1);
        table.setHeaderName("UPC",2);
        table.setHeaderName("Size",3);
        table.setHeaderName("Quantity",4);
       // ut.changeDataTypetoLong(4,dm);//Yen.Dinh 19-06-2006
      }
      rs1.close();
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

  }



  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_VIEW_DAMAGE_GOODS);
    frmMain.pnlViewDamageGoods.txtReceiptDate.requestFocus(true);
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
      if(identifier.intValue() == KeyEvent.VK_F1){
        btnDone.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

}

class PanelDetailDamageGoods_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelDetailDamageGoods adaptee;

  PanelDetailDamageGoods_btnCancel_actionAdapter(PanelDetailDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}
