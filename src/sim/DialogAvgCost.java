package sim;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.Dimension;
import btm.business.*;
import btm.attr.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> AGV Cost</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DialogAvgCost extends JDialog {
  DataAccessLayer DAL;
  Utils ut = new Utils();
  FrameMainSim frmMain;
  String artNo = "";
  String size = "";
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnModify = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JLabel lblCurrSOH = new JLabel();
  JLabel lblOldUnitCost = new JLabel();
  JLabel lblItemID = new JLabel();
  JTextField txtCurrSOH = new JTextField();
  JTextField txtOldUnitCost = new JTextField();
  JButton btnItemID = new JButton();
  JTextField txtItemID = new JTextField();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JLabel lblNewAvgCost = new JLabel();
  JLabel lblOldAvgCost = new JLabel();
  JLabel lblNewUnitCost = new JLabel();
  JTextField txtNewAvgCost = new JTextField();
  JTextField txtOldAvgCost = new JTextField();
  JTextField txtNewUnitCost = new JTextField();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public boolean isCellEditable(int row, int col){
      return false;
    }
  };
  ResourceBundle lang=DAL.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international
  ItemMasterBusObj itBus = new ItemMasterBusObj();
  BJTable table = new BJTable(dm);
  JLabel lblNewQty = new JLabel();
  JTextField txtNewQty = new JTextField();
  StripedTableCellRenderer stripedTable = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  boolean modifyFlag = false;

  public DialogAvgCost(Frame frame, String title, boolean modal, FrameMainSim frmMain) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogAvgCost() {
    this(null, "", false,null);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(800, 600));
    this.addWindowListener(new DialogAvgCost_this_windowAdapter(this));
    this.addKeyListener(new DialogAvgCost_this_keyAdapter(this));
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.addKeyListener(new DialogAvgCost_jPanel1_keyAdapter(this));
    jPanel2.setPreferredSize(new Dimension(800, 150));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(800, 400));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setText("<html><center><b>" + lang.getString("Cancel") + "</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new DialogAvgCost_btnCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setText(lang.getString("Delete") + "(F8)");
    btnDelete.addActionListener(new DialogAvgCost_btnDelete_actionAdapter(this));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setText(lang.getString("Modify") +"(F4)");
    btnModify.addActionListener(new DialogAvgCost_btnModify_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setText(lang.getString("Add") +"(F2)");
    btnAdd.addActionListener(new DialogAvgCost_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setText(lang.getString("Done") +"(F1)");
    btnDone.addActionListener(new DialogAvgCost_btnDone_actionAdapter(this));
    jPanel4.setPreferredSize(new Dimension(100, 150));
    jPanel5.setPreferredSize(new Dimension(200, 150));
    jPanel6.setPreferredSize(new Dimension(500, 150));
    jPanel6.setLayout(borderLayout3);
    lblCurrSOH.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCurrSOH.setPreferredSize(new Dimension(90, 20));
    lblCurrSOH.setText(lang.getString("CurrentSOH")+":");
    lblOldUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblOldUnitCost.setPreferredSize(new Dimension(90, 20));
    lblOldUnitCost.setText(lang.getString("OldUnitCost")+":");
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setPreferredSize(new Dimension(90, 20));
    lblItemID.setText(lang.getString("UPC")+":");
    txtCurrSOH.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCurrSOH.setPreferredSize(new Dimension(185, 20));
    txtCurrSOH.setEditable(false);
    txtCurrSOH.setText("");
    txtCurrSOH.setHorizontalAlignment(SwingConstants.LEADING);
    txtCurrSOH.addKeyListener(new DialogAvgCost_txtCurrSOH_keyAdapter(this));
    txtOldUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtOldUnitCost.setPreferredSize(new Dimension(185, 20));
    txtOldUnitCost.setEditable(false);
    txtOldUnitCost.setText("");
    txtOldUnitCost.addKeyListener(new DialogAvgCost_txtOldUnitCost_keyAdapter(this));
    btnItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnItemID.setPreferredSize(new Dimension(30, 20));
    btnItemID.setText("...");
    btnItemID.addActionListener(new DialogAvgCost_btnItemID_actionAdapter(this));
    txtItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemID.setPreferredSize(new Dimension(150, 20));
    txtItemID.setText("");
    txtItemID.addKeyListener(new DialogAvgCost_txtItemID_keyAdapter(this));
    txtItemID.addFocusListener(new DialogAvgCost_txtItemID_focusAdapter(this));
    jPanel7.setPreferredSize(new Dimension(130, 150));
    jPanel8.setPreferredSize(new Dimension(200, 150));
    jPanel9.setPreferredSize(new Dimension(170, 150));
    lblNewAvgCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblNewAvgCost.setPreferredSize(new Dimension(90, 20));
    lblNewAvgCost.setText(lang.getString("NewAvgCost")+":");
    lblOldAvgCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblOldAvgCost.setPreferredSize(new Dimension(90, 20));
    lblOldAvgCost.setText(lang.getString("OldAvgCost")+":");
    lblNewUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblNewUnitCost.setPreferredSize(new Dimension(90, 20));
    lblNewUnitCost.setText(lang.getString("NewUnitCost")+":");
    txtNewAvgCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtNewAvgCost.setPreferredSize(new Dimension(185, 20));
    txtNewAvgCost.setVerifyInputWhenFocusTarget(true);
    txtNewAvgCost.setEditable(false);
    txtNewAvgCost.setColumns(0);
    txtNewAvgCost.addKeyListener(new DialogAvgCost_txtNewAvgCost_keyAdapter(this));
    txtOldAvgCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtOldAvgCost.setPreferredSize(new Dimension(185, 20));
    txtOldAvgCost.setEditable(false);
    txtOldAvgCost.setText("");
    txtOldAvgCost.addKeyListener(new DialogAvgCost_txtOldAvgCost_keyAdapter(this));
    txtNewUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtNewUnitCost.setPreferredSize(new Dimension(185, 20));
    txtNewUnitCost.setText("");
    txtNewUnitCost.addKeyListener(new DialogAvgCost_txtNewUnitCost_keyAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(790, 390));
    jScrollPane1.addKeyListener(new DialogAvgCost_jScrollPane1_keyAdapter(this));
    lblNewQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblNewQty.setPreferredSize(new Dimension(90, 20));
    lblNewQty.setText(lang.getString("NewQuantity")+":");
    txtNewQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtNewQty.setPreferredSize(new Dimension(185, 20));
    txtNewQty.setEditable(false);
    txtNewQty.addKeyListener(new DialogAvgCost_txtNewQty_keyAdapter(this));
    table.addMouseListener(new DialogAvgCost_table_mouseAdapter(this));
    table.addKeyListener(new DialogAvgCost_table_keyAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jPanel1,  BorderLayout.NORTH);
    jPanel1.add(btnDone, null);
    jPanel1.add(btnAdd, null);
    jPanel1.add(btnModify, null);
    jPanel1.add(btnDelete, null);
    jPanel1.add(btnCancel, null);
    panel1.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel4,  BorderLayout.WEST);
    jPanel4.add(lblItemID, null);
    jPanel4.add(lblOldUnitCost, null);
    jPanel4.add(lblCurrSOH, null);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel2.add(jPanel6,  BorderLayout.EAST);
    panel1.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    jPanel5.add(txtItemID, null);
    jPanel5.add(btnItemID, null);
    jPanel5.add(txtOldUnitCost, null);
    jPanel5.add(txtCurrSOH, null);
    jPanel6.add(jPanel7,  BorderLayout.WEST);
    jPanel7.add(lblNewUnitCost, null);
    jPanel7.add(lblOldAvgCost, null);
    jPanel7.add(lblNewQty, null);
    jPanel7.add(lblNewAvgCost, null);
    jPanel6.add(jPanel8,  BorderLayout.CENTER);
    jPanel6.add(jPanel9,  BorderLayout.EAST);
    jPanel8.add(txtNewUnitCost, null);
    jPanel8.add(txtOldAvgCost, null);
    jPanel8.add(txtNewQty, null);
    jPanel8.add(txtNewAvgCost, null);
    Object[] columnData = new Object[]{
        lang.getString("ItemID"), lang.getString("UPC"), lang.getString("Size"),lang.getString("NewUnitCost"), lang.getString("NewAvgCost")
    };
    dm.setDataVector(new Object[][]{},columnData);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation( (screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);

   txtItemID.setRequestFocusEnabled(true);


  }

  void txtItemID_focusLost(FocusEvent e) {
    if (!txtItemID.getText().equalsIgnoreCase("")){
      getData();
    }
  }
  //get Item_ID
public String getItemID(String upc){
  ResultSet rs = null;
  Statement stmt = null;
  if(upc.equalsIgnoreCase("")){
    return "";
  }
  String itemID="-1";
  try {
    String sql ="Select ITEM_ID From BTM_IM_ITEM_MASTER Where ART_NO='" + upc + "'";
//        System.out.println(sql);
        stmt = DAL.getConnection().createStatement();
        rs = DAL.executeQueries(sql, stmt);
        if (rs.next()) {
          itemID = rs.getString("ITEM_ID");
        }
  }catch (Exception e) {
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

  //get data
  void getData(){
    ResultSet rs = null;
    Statement stmt = null;
    String sql = "";
    String itemID = getItemID(txtItemID.getText());
    try {
      stmt = DAL.getConnection().createStatement();
      sql = "select distinct im.item_id, art_no, attr2_name, stock_on_hand, avg_cost, ls.unit_cost " +
          " from btm_im_item_master im, btm_im_item_loc_soh ils, btm_im_price_hist ph, btm_im_item_loc_supplier ls " +
          " where im.item_id = ils.item_id and im.item_id = ph.item_id and im.item_id = ls.item_id " +
          " and im.item_id = '" + itemID + "'";
//      System.out.println(sql);
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        this.artNo = rs.getString("art_no");
        this.size = rs.getString("attr2_name");
        txtCurrSOH.setText("" + rs.getLong("stock_on_hand"));
        txtOldUnitCost.setText(ut.formatNumber("" + rs.getDouble("unit_cost")));
        txtOldAvgCost.setText(ut.formatNumber("" + rs.getDouble("avg_cost")));
        ResultSet rs1 = null;
        sql = "select qty from btm_im_receipt r, btm_im_receipt_item ri " +
            " where r.receipt_id = ri.receipt_id and ri.item_id = '" + itemID + "'" +
            " order by receipt_date desc ";
//        System.out.println(sql);
        rs1 = DAL.executeQueries(sql, stmt);
        if (rs1.next()) {
          double currSOH = Long.parseLong(txtCurrSOH.getText().trim()) -
              rs1.getLong("qty");
          txtCurrSOH.setText("" + currSOH);
          txtNewQty.setText("" + rs1.getLong("qty"));
        }
        rs1.close();
      }
      rs.close();
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }


  void btnItemID_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dialogItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0095"),true,frmMain,itemBusObject);
    dialogItemLookup.setVisible(true);
    if (dialogItemLookup.done){
      txtItemID.setText(dialogItemLookup.getUPC());
      txtItemID.requestFocus(true);
//      txtNewUnitCost.requestFocus(true);
    }else{
      txtItemID.requestFocus(true);
    }
  }

  void txtItemID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtItemID, ItemMaster.LEN_ITEM_ARTNO);
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtNewUnitCost.requestFocus(true);
    }
  }
  //get new avg cost
  double getNewAvgCost(){
    double result = 0;
    double oldAvgCost = ut.convertToDouble(txtOldAvgCost.getText());
    double currSOH = Double.parseDouble(txtCurrSOH.getText());
    double newUnitCost = Double.parseDouble(txtNewUnitCost.getText());
    double newQty = Double.parseDouble(txtNewQty.getText());
    double newAvgCost = 0;
    newAvgCost = ((oldAvgCost * currSOH) + (newUnitCost * newQty))/(currSOH + newQty);
    result = newAvgCost;
    return result;
  }
  void btnCancel_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void txtNewUnitCost_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtNewUnitCost,18);
//    catchHotKey(e);
    char key = e.getKeyChar();
    if (key != KeyEvent.VK_ENTER){
      if (ut.isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE ||
          key == KeyEvent.VK_DELETE || key == '.') {
        return;
      }
      else {
        e.consume();
      }
    }else{
      if (txtNewQty.getText().equalsIgnoreCase("")){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1006_ItemNotReceiptedIntoDatabase"));
        txtItemID.requestFocus(true);
        return;
      }
      if(!txtNewAvgCost.getText().equalsIgnoreCase("")){
         txtNewAvgCost.setText(ut.formatNumber("" + getNewAvgCost()));
      }
    }

  }

  void btnAdd_actionPerformed(ActionEvent e) {
    if (modifyFlag){
      return;
    }
    if (txtItemID.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS1007_UPCIsNotNull"));
      txtItemID.requestFocus(true);
      return;
    }
    if (txtNewUnitCost.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1008_NewUnitCostIsNotNull"));
      txtNewUnitCost.requestFocus(true);
      return;
    }
    if (txtNewQty.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1006_ItemNotReceiptedIntoDatabase"));
      txtItemID.requestFocus(true);
      return;
    }
    txtNewAvgCost.setText(ut.formatNumber("" + getNewAvgCost()));
    String itemID = getItemID(txtItemID.getText().trim());
    Object[] rowData = new Object[]{
        itemID,this.artNo,this.size, txtNewUnitCost.getText(), txtNewAvgCost.getText()
    };
    dm.addRow(rowData);
//    stripedTable.installInTable(table, Color.lightGray, Color.white,Color.white, Color.black);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    stripedTable.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

    //set focus for last row
     if(table.getRowCount()>0){
       table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
     }

     //show current row
     Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
     jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

    resetData();
  }
  void resetData(){
    this.artNo = "";
    this.size = "";
    txtItemID.setText("");
    txtOldAvgCost.setText("");
    txtOldUnitCost.setText("");
    txtNewAvgCost.setText("");
    txtNewQty.setText("");
    txtNewUnitCost.setText("");
    txtCurrSOH.setText("");
    txtItemID.requestFocus(true);
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      txtItemID.setText(table.getValueAt(table.getSelectedRow(),1).toString());
      getData();
      txtNewAvgCost.setText(table.getValueAt(table.getSelectedRow(),4).toString());
      txtNewUnitCost.setText(table.getValueAt(table.getSelectedRow(),3).toString());
      modifyFlag = true;
      txtItemID.setEditable(false);
      btnItemID.setEnabled(false);
    }
  }

  void btnModify_actionPerformed(ActionEvent e) {
    if (modifyFlag){
      if (txtNewUnitCost.getText().equalsIgnoreCase("")){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1008_NewUnitCostIsNotNull"));
        txtNewUnitCost.requestFocus(true);
        return;
      }
      for (int i = 0; i < table.getRowCount(); i++){
        String itemID = table.getValueAt(i,0).toString();
        if (itemID.equalsIgnoreCase(txtItemID.getText())){
          table.setValueAt(txtNewUnitCost.getText(),i,3);
          table.setValueAt(txtNewAvgCost.getText(),i,4);
          modifyFlag = false;
          txtItemID.setEditable(true);
          btnItemID.setEnabled(true);
          table.setRowSelectionInterval(i,i);
          resetData();
          return;
        }
      }
    }
  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if (table.getSelectedRow() == -1){
      return;
    }
    if (table.getRowCount()==1){
      dm.removeRow(0);
      return;
    }
    int[] i = table.getSelectedRows();
    for (int j=0; j<i.length; j++){
      dm.removeRow(i[0]);
    }
  }

  void btnDone_actionPerformed(ActionEvent e) {
    if (table.getRowCount() == -1){
      this.dispose();
    }else{
      Vector vSql = new Vector();
      for(int i=0; i < table.getRowCount(); i ++){
        double avgCost = ut.convertToDouble(table.getValueAt(i,4).toString());
        String sql = "update btm_im_item_loc_supplier set avg_cost = " + avgCost;
//        System.out.print(sql);
        vSql.addElement(sql);
      }
      if (!vSql.isEmpty()){

        DAL.setBeginTrans(DAL.getConnection());
        try{
          Statement stmt = DAL.getConnection().createStatement();
          DAL.executeBatchQuery(vSql, stmt);
          stmt.close();
        }catch(Exception ex){
          ex.printStackTrace();
        }
        DAL.setEndTrans(DAL.getConnection());

        vSql.clear();
        this.dispose();
      }
    }
  }
  private void registerKeyboardActions() {
   /// set up the maps:
   InputMap inputMap = new InputMap();
   inputMap.setParent(jPanel1.getInputMap(JComponent.
                                          WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
   ActionMap actionMap = jPanel1.getActionMap();


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
        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnDone.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F2) {
          btnAdd.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F4) {
          btnModify.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F8) {
          btnDelete.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
      }
    }

    private void catchHotKey(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }
      else if (e.getKeyCode() == KeyEvent.VK_F2 ) {
        btnAdd.doClick();
      }
      else if (e.getKeyCode() == KeyEvent.VK_F4) {
        btnModify.doClick();
      }
      else if (e.getKeyCode() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }
      else if (e.getKeyCode() == KeyEvent.VK_F12 ||
               e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }

}

  void this_keyTyped(KeyEvent e) {
//    catchHotKey(e);
  }

  void table_keyTyped(KeyEvent e) {
//    catchHotKey(e);
  }

  void txtOldUnitCost_keyTyped(KeyEvent e) {
//    catchHotKey(e);
  }

  void txtCurrSOH_keyTyped(KeyEvent e) {
//    catchHotKey(e);
  }

  void txtOldAvgCost_keyTyped(KeyEvent e) {
//    catchHotKey(e);

  }

  void txtNewQty_keyTyped(KeyEvent e) {
//    catchHotKey(e);
  }

  void txtNewAvgCost_keyTyped(KeyEvent e) {
//    catchHotKey(e);
  }

  void jPanel1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void jScrollPane1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtItemID_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtNewUnitCost_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtOldUnitCost_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtCurrSOH_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtOldAvgCost_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtNewQty_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtNewAvgCost_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void this_windowOpened(WindowEvent e) {
    txtItemID.requestFocus(true);
  }

}

class DialogAvgCost_txtItemID_focusAdapter extends java.awt.event.FocusAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_txtItemID_focusAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemID_focusLost(e);
  }
}

class DialogAvgCost_btnItemID_actionAdapter implements java.awt.event.ActionListener {
  DialogAvgCost adaptee;

  DialogAvgCost_btnItemID_actionAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnItemID_actionPerformed(e);
  }
}

class DialogAvgCost_txtItemID_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_txtItemID_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemID_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtItemID_keyPressed(e);
  }
}

class DialogAvgCost_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogAvgCost adaptee;

  DialogAvgCost_btnCancel_actionAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogAvgCost_txtNewUnitCost_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_txtNewUnitCost_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtNewUnitCost_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtNewUnitCost_keyPressed(e);
  }
}

class DialogAvgCost_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  DialogAvgCost adaptee;

  DialogAvgCost_btnAdd_actionAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class DialogAvgCost_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_table_mouseAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogAvgCost_btnModify_actionAdapter implements java.awt.event.ActionListener {
  DialogAvgCost adaptee;

  DialogAvgCost_btnModify_actionAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class DialogAvgCost_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  DialogAvgCost adaptee;

  DialogAvgCost_btnDelete_actionAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class DialogAvgCost_btnDone_actionAdapter implements java.awt.event.ActionListener {
  DialogAvgCost adaptee;

  DialogAvgCost_btnDone_actionAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class DialogAvgCost_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_this_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.this_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogAvgCost_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_table_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.table_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogAvgCost_txtOldUnitCost_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_txtOldUnitCost_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtOldUnitCost_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtOldUnitCost_keyPressed(e);
  }
}

class DialogAvgCost_txtCurrSOH_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_txtCurrSOH_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCurrSOH_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtCurrSOH_keyPressed(e);
  }
}

class DialogAvgCost_txtOldAvgCost_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_txtOldAvgCost_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtOldAvgCost_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtOldAvgCost_keyPressed(e);
  }
}

class DialogAvgCost_txtNewQty_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_txtNewQty_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtNewQty_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtNewQty_keyPressed(e);
  }
}

class DialogAvgCost_txtNewAvgCost_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_txtNewAvgCost_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtNewAvgCost_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtNewAvgCost_keyPressed(e);
  }
}

class DialogAvgCost_jPanel1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_jPanel1_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jPanel1_keyPressed(e);
  }
}

class DialogAvgCost_jScrollPane1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_jScrollPane1_keyAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jScrollPane1_keyPressed(e);
  }
}

class DialogAvgCost_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogAvgCost adaptee;

  DialogAvgCost_this_windowAdapter(DialogAvgCost adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
