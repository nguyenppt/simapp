package sim;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Nghi Doan
 * @version 1.0
 */
import common.*;
import btm.attr.*;
import btm.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
import java.util.*;

public class PanelStockOnHand extends JPanel {
  //Column Name of Table
  private static final String COL_ITEM_ID ="Item ID";//0
  private static final String COL_ART_NO = "UPC";//1
  private static final String COL_ITEM_NAME = "Item Name";//2
  private static final String COL_SIZE = "Size";//3
  private static final String COL_QUANTITY ="Quantity";//4
  private static final String COL_STORE_NAME ="Store Name";//5

  boolean artNoInput=true;//false if input by item id

  //Declare Data Access Layer
  DataAccessLayer DAL;
  //Declare Utils
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  //Declare Frame Main
  FrameMainSim frmMain;
  //Declare a batch update
  Vector vSql = new Vector();
  Vector vSql2 = new Vector();
  String itemID = "";
  String itemName = "";
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
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
  JLabel lblStoreID = new JLabel();
  JLabel lblItemID = new JLabel();
  JTextField txtArtNo = new JTextField();
  BJComboBox cboStoreID = new BJComboBox();
  JLabel lblSohUpdateDatetime = new JLabel();
  JLabel lblStockOnHand = new JLabel();
  JTextField txtSohUpdateDatetime = new JTextField();
  JTextField txtStockOnHand = new JTextField();
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnModify = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  BJButton btnView = new BJButton();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel10 = new JPanel();
  JLabel lblItemID1 = new JLabel();
  JTextField txtSize = new JTextField();


  public PanelStockOnHand(FrameMainSim frmMain) {
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
    this.setLayout(borderLayout1);
    jPanel1.setPreferredSize(new Dimension(800, 50));
    this.setPreferredSize(new Dimension(800, 600));
    jPanel2.setPreferredSize(new Dimension(800, 150));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(800, 400));
    jPanel3.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(760, 395));
    jPanel4.setPreferredSize(new Dimension(300, 150));
    jPanel4.setLayout(borderLayout3);
    jPanel5.setMinimumSize(new Dimension(300, 150));
    jPanel5.setPreferredSize(new Dimension(500, 150));
    jPanel5.setLayout(borderLayout4);
    jPanel6.setPreferredSize(new Dimension(120, 150));
    jPanel7.setPreferredSize(new Dimension(180, 150));
    lblStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStoreID.setPreferredSize(new Dimension(110, 20));
    lblStoreID.setText(lang.getString("StoreID") + ": ");
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setPreferredSize(new Dimension(110, 20));
    lblItemID.setText(lang.getString("UPC") + ": ");
    txtArtNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtArtNo.setNextFocusableComponent(txtStockOnHand);
    txtArtNo.setPreferredSize(new Dimension(170, 20));
    txtArtNo.setText("");
    txtArtNo.addKeyListener(new PanelStockOnHand_txtArtNo_keyAdapter(this));
    txtArtNo.addFocusListener(new PanelStockOnHand_txtArtNo_focusAdapter(this));
    jPanel8.setPreferredSize(new Dimension(120, 150));
    jPanel9.setPreferredSize(new Dimension(180, 150));
    lblSohUpdateDatetime.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSohUpdateDatetime.setPreferredSize(new Dimension(110, 20));
    lblSohUpdateDatetime.setText(lang.getString("ReceiptDate") + ":");
    lblStockOnHand.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStockOnHand.setPreferredSize(new Dimension(110, 20));
    lblStockOnHand.setText("Quantity: ");
    txtSohUpdateDatetime.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtSohUpdateDatetime.setPreferredSize(new Dimension(170, 20));
    txtSohUpdateDatetime.setEditable(false);
    txtSohUpdateDatetime.setText("");
    txtSohUpdateDatetime.addActionListener(new PanelStockOnHand_txtSohUpdateDatetime_actionAdapter(this));
    txtStockOnHand.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtStockOnHand.setPreferredSize(new Dimension(170, 20));
    txtStockOnHand.setText("");
    txtStockOnHand.addKeyListener(new PanelStockOnHand_txtStockOnHand_keyAdapter(this));
    txtStockOnHand.addFocusListener(new PanelStockOnHand_txtStockOnHand_focusAdapter(this));
    cboStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    cboStoreID.setNextFocusableComponent(txtArtNo);
    cboStoreID.setPreferredSize(new Dimension(170, 20));
    cboStoreID.addKeyListener(new PanelStockOnHand_cboStoreID_keyAdapter(this));
//    cboStoreID.setSelectedIndex(0);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F12)");
    btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelStockOnHand_btnCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete") + " (F8)");
    btnDelete.setText("<html><center><b>Delete </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnDelete.addActionListener(new PanelStockOnHand_btnDelete_actionAdapter(this));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify") + " (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnModify.addActionListener(new PanelStockOnHand_btnModify_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add") + " (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelStockOnHand_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done") + "(F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelStockOnHand_btnDone_actionAdapter(this));
    table.addMouseListener(new PanelStockOnHand_table_mouseAdapter(this));
    jPanel10.setPreferredSize(new Dimension(200, 150));
    btnView.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnView.setPreferredSize(new Dimension(120, 37));
    btnView.setText("<html><center><b>"+lang.getString("View")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html>");
    btnView.addActionListener(new PanelStockOnHand_btnView_actionAdapter(this));
    table.addKeyListener(new PanelStockOnHand_table_keyAdapter(this));
    lblItemID1.setText(lang.getString("Size")+": ");
    lblItemID1.setPreferredSize(new Dimension(110, 20));
    lblItemID1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSize.setText("");
    txtSize.addKeyListener(new PanelStockOnHand_txtSize_keyAdapter(this));
    txtSize.setPreferredSize(new Dimension(170, 20));
    txtSize.setNextFocusableComponent(txtStockOnHand);
    txtSize.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    this.add(jPanel1,  BorderLayout.NORTH);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel4,  BorderLayout.WEST);
    jPanel4.add(jPanel6,  BorderLayout.WEST);
    jPanel6.add(lblStoreID, null);
    jPanel6.add(lblItemID, null);
    jPanel6.add(lblItemID1, null);
    jPanel4.add(jPanel7,  BorderLayout.CENTER);
    jPanel7.add(cboStoreID, null);
    jPanel7.add(txtArtNo, null);
    jPanel7.add(txtSize, null);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel8,  BorderLayout.WEST);
    jPanel8.add(lblSohUpdateDatetime, null);
    jPanel8.add(lblStockOnHand, null);
    jPanel5.add(jPanel9,  BorderLayout.CENTER);
    jPanel9.add(txtSohUpdateDatetime, null);
    jPanel9.add(txtStockOnHand, null);
    jPanel5.add(jPanel10,  BorderLayout.EAST);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    jPanel1.add(btnDone, null);
    jPanel1.add(btnAdd, null);
//TrungNT: disable modify
//    jPanel1.add(btnModify, null);
    jPanel1.add(btnView, null);
    jPanel1.add(btnDelete, null);
    jPanel1.add(btnCancel, null);
    dm.addColumn(COL_ITEM_ID);
    dm.addColumn(COL_ART_NO);
    dm.addColumn(COL_ITEM_NAME);
    dm.addColumn(COL_SIZE);
    dm.addColumn(COL_QUANTITY);
    dm.addColumn(COL_STORE_NAME);
//    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    table.setRowHeight(30);
//    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
//    table.getTableHeader().setReorderingAllowed(false);
    registerKeyboardActions();
  }
  //initialize Screen
  void initScreen(){

    try{
      txtSohUpdateDatetime.setText(ut.getSystemDate(1));
      Statement stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      cboStoreID.setData(getComboData(stmt));
      stmt.close();
      cboStoreID.setEnabled(true);
      cboStoreID.requestFocus(true);
    }catch(Exception e){
      e.printStackTrace();
    }

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
       if (identifier.intValue()==KeyEvent.VK_F1){
         btnDone.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F2){
         btnAdd.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F4){
         btnModify.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F8){
         btnDelete.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F5){
         btnView.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F12 || identifier.intValue()==KeyEvent.VK_ESCAPE){
         btnCancel.doClick();
       }
}
}

  void btnAdd_actionPerformed(ActionEvent e) {
    String itemID="";
      itemID = checkArtNo();
      if (itemID.equalsIgnoreCase("")) {
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS312_UPCOrSizeInvalid"));
        txtArtNo.requestFocus(true);
        return;
      }
      if (txtArtNo.getText().equalsIgnoreCase("")){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1007_UPCIsNotNull"));
        txtArtNo.requestFocus(true);
        return;
      }
      if (txtStockOnHand.getText().trim().equalsIgnoreCase("")){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS313_QuantityNotNull"));
        txtStockOnHand.requestFocus(true);
        return;
      }
    String storeName =  cboStoreID.getSelectedItem().toString();
    String qty = txtStockOnHand.getText().trim();
    if (qty.equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS313_QuantityNotNull"));
      txtStockOnHand.requestFocus(true);
      return;
    }
    for (int i=0; i< dm.getRowCount(); i++){
      String artNoOld =  table.getValueAt(i,table.getColumnInd(COL_ART_NO)).toString();
      String sizeOld =  table.getValueAt(i,table.getColumnInd(COL_SIZE)).toString();
      String storeNameOld =  table.getValueAt(i,table.getColumnInd(COL_STORE_NAME)).toString();
      if (txtArtNo.getText().equalsIgnoreCase(artNoOld) && storeName.equalsIgnoreCase(storeNameOld) && txtSize.getText().equalsIgnoreCase(sizeOld)){
        ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS314_ItemOrStoreHad"));
        txtArtNo.requestFocus(true);
        return;
      }
    }
    if (!checkExist(itemID, Long.parseLong(cboStoreID.getSelectedData()))){
      vSql.addElement(
          "insert into btm_im_item_loc_soh(item_id, store_id, stock_on_hand, soh_update_datetime, last_update_id) " +
          " values('" + itemID + "'," + Long.parseLong(cboStoreID.getSelectedData()) +
          " , " + ut.convertToDouble(txtStockOnHand.getText().trim()) + ", to_date ('" +
          txtSohUpdateDatetime.getText() + "','" + ut.DATE_FORMAT1 + "'), '" + DAL.getEmpID() +
          "')"
          );
      //insert into BTM_IM_HISTORY_INIT_SOH , keep history
      vSql2.addElement(
                "insert into BTM_IM_INIT_SOH_HIST (item_id, store_id, soh, soh_update_datetime) " +
                " values('" + itemID + "'," + Long.parseLong(cboStoreID.getSelectedData()) +
                " , " + ut.convertToDouble(txtStockOnHand.getText().trim()) + ", to_date ('" +
                ut.getSystemDateTime1() + "','" + ut.DATETIME_FORMAT2 + "'))");

    }else{
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS315_ItemExist"));
      return;

//      vSql.addElement(
//          "update btm_im_item_loc_soh set stock_on_hand = " +
//          ut.convertToDouble(txtStockOnHand.getText().trim()) +
//          " , soh_update_datetime = to_date ('" + txtSohUpdateDatetime.getText() +
//          "','" + ut.DATE_FORMAT1 + "'),last_update_id ='" + DAL.getEmpID() +
//          "' " +
//          " where item_id ='" + itemID +
//          "' and store_id =" + Long.parseLong(cboStoreID.getSelectedData())
//      );
    }

    Object[] rowData = new Object[]{itemID, txtArtNo.getText(), this.itemName, txtSize.getText(),
        new Double(ut.convertToDouble(txtStockOnHand.getText().trim())),cboStoreID.getSelectedItem(),
    };
    dm.addRow(rowData);
    txtSize.setText("");
    txtStockOnHand.setText("");
    if (table.getRowCount()>0){
      cboStoreID.setEnabled(false);
    }
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

    //set focus for last row
    if(table.getRowCount()>0){
      table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
    }

    //show current row
    Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
    jScrollPane1.getViewport().setViewPosition(bounds.getLocation());
    txtStockOnHand.setText("");
    txtArtNo.setText("");
    txtArtNo.requestFocus(true);
    txtArtNo.selectAll();

  }
  //get index of column from name
  //
  public int getColumnInd(String columnName){
    TableColumn tc = table.getColumn(columnName);
    return tc.getModelIndex();
  }

  //Get data for combo box
  ResultSet getComboData(Statement stmt){
    ResultSet rs = null;
    try{
      String sql = "Select store_id, name from btm_im_store order by upper(name)";
      rs = DAL.executeScrollQueries(sql,stmt);
    }catch (Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  //Check exist
  boolean checkExist(String itemID, long storeID){

    ResultSet rs = null;
    Statement stmt = null;
    try{
      stmt = DAL.getConnection().createStatement();
      String sql = "select * from btm_im_item_loc_soh where item_id ='" + itemID +
          "' and store_id =" + storeID;
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        stmt.close();

        return true;
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  void txtArtNo_keyTyped(KeyEvent e) {

    ut.limitLenjTextField(e,txtArtNo,ItemMaster.LEN_ITEM_ARTNO);
    char key = e.getKeyChar();

    if (ut.isIntString(String.valueOf( key)) || key == KeyEvent.VK_BACK_SPACE ||
            key == KeyEvent.VK_DELETE) {
         return;
    }else{
      e.consume();
    }

    if (key == KeyEvent.VK_ENTER){
      if (!txtArtNo.getText().equalsIgnoreCase("") && !txtSize.getText().equalsIgnoreCase("")){
        this.itemID = checkArtNo();
        if (this.itemID.equalsIgnoreCase("")){
          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS312_UPCOrSizeInvalid"));
          return;
        }
        txtStockOnHand.requestFocus(true);
      }else{
        txtSize.requestFocus(true);
      }
    }
  }

  void txtStockOnHand_keyTyped(KeyEvent e) {
    /*ut.limitLenjTextField(e,txtStockOnHand,StockOnHand.LEN_QTY);
    char key = e.getKeyChar();
    if (ut.isIntString(String.valueOf( key)) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE){
      return;
    }else{
      e.consume();
    }
    if (key == KeyEvent.VK_ENTER){
      btnAdd.doClick();
//      if(artNoInput){
        txtArtNo.requestFocus(true);
//      }else{
//        txtItemID.requestFocus(true);
//      }
    }*/
    ut.checkNumberPositive(e,txtStockOnHand.getText());//Yen.Dinh 19-06-2006
  }
  void txtStockOnHand_focusLost(FocusEvent e) {
  if (txtStockOnHand.isEditable()) {
        if (!txtStockOnHand.getText().equalsIgnoreCase("")) {
          txtStockOnHand.setText("" + ut.formatNumber(txtStockOnHand.getText()));
        }
      }

}


  void btnDone_actionPerformed(ActionEvent e) {
    if (!vSql.isEmpty()){

      try{
        for(int i = 0; i < vSql.size(); i++){
//          System.out.println(vSql.elementAt(i));
        }
        Statement stmt = DAL.getConnection().createStatement();
        DAL.setBeginTrans(DAL.getConnection());
        DAL.executeBatchQuery(vSql,stmt);
        DAL.executeBatchQuery(vSql2,stmt);
        DAL.setEndTrans(DAL.getConnection());
        stmt.close();
        vSql.clear();
        vSql2.clear();
      }catch(Exception ex){
        DAL.setRollback(DAL.getConnection());
        ex.printStackTrace();
      }

    }
    while(table.getRowCount()>0){
      dm.removeRow(0);
    }
    txtArtNo.setText("");
    cboStoreID.setSelectedIndex(0);
    txtStockOnHand.setText("");
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showInventManageButton();

  }

  void btnCancel_actionPerformed(ActionEvent e) {
    if (!vSql.isEmpty()){
      vSql.clear();
      vSql2.clear();
    }
    while(table.getRowCount()>0){
      dm.removeRow(0);
    }
    txtArtNo.setText("");
    cboStoreID.setSelectedIndex(0);
    txtStockOnHand.setText("");
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0002"));
    frmMain.pnlMainSim.showInventManageButton();
  }

  void btnDelete_actionPerformed(ActionEvent e) {
//    if (table.getSelectedRow() != -1){
//      dm.removeRow(table.getSelectedRow());
//      vSql.removeAllElements();
//      btnAdd.doClick();
//      return;
//    }
    //delete vector and data
    int[] i = table.getSelectedRows();
    for (int j=0; j<i.length; j++){
      vSql.removeElementAt(i[0]);
      vSql2.removeElementAt(i[0]);
      dm.removeRow(i[0]);
    }
  }

  void btnModify_actionPerformed(ActionEvent e) {
    if (table.getSelectedRow() != -1){
      txtArtNo.setText( table.getValueAt(table.getSelectedRow(),table.getColumnInd(COL_ART_NO)).toString());
      txtSize.setText( table.getValueAt(table.getSelectedRow(),table.getColumnInd(COL_SIZE)).toString());
      cboStoreID.setSelectedItem(table.getValueAt(table.getSelectedRow(),table.getColumnInd(COL_STORE_NAME)).toString());
      txtStockOnHand.setText( table.getValueAt(table.getSelectedRow(),table.getColumnInd(COL_QUANTITY)).toString());
      vSql.removeElementAt(table.getSelectedRow());
      dm.removeRow(table.getSelectedRow());
      txtStockOnHand.requestFocus(true);
      txtStockOnHand.selectAll();
    }
  }

  void table_mouseClicked(MouseEvent e) {
//TrungNT: disable modify
//    if (e.getClickCount() == 2){
//      txtArtNo.setText( table.getValueAt(table.getSelectedRow(),table.getColumnInd(COL_ART_NO)).toString());
//      cboStoreID.setSelectedItem(table.getValueAt(table.getSelectedRow(),table.getColumnInd(COL_STORE_NAME)).toString());
//      txtStockOnHand.setText( table.getValueAt(table.getSelectedRow(),table.getColumnInd(COL_QUANTITY)).toString());
//      txtSize.setText( table.getValueAt(table.getSelectedRow(),table.getColumnInd(COL_SIZE)).toString());
//      vSql.removeElementAt(table.getSelectedRow());
//      dm.removeRow(table.getSelectedRow());
//      txtArtNo.requestFocus(true);
//    }
  }

  void txtSohUpdateDatetime_actionPerformed(ActionEvent e) {

  }

  void table_mousePressed(MouseEvent e) {

  }


  void btnView_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_STOCK_ON_HAND_VIEW);
    frmMain.pnlViewStockOnHand.initScreen();
    frmMain.pnlViewStockOnHand.cboStore.requestFocus(true);
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnAdd.doClick();
               }
  }

  void txtArtNo_keyPressed(KeyEvent e) {


  }
  String checkArtNo(){
    String result = "";
    String sql = "";
    if (!txtArtNo.getText().equalsIgnoreCase("")){
      if (!txtSize.getText().equalsIgnoreCase("")){
        sql = "select distinct item_id, item_name from btm_im_item_master im, btm_im_attr_dtl ad where im.art_no = '" +
            txtArtNo.getText().trim() +
            "' and upper(ad.attr_dtl_desc) = upper('" + txtSize.getText() +
            "') and im.attr2_dtl_id = ad.attr_dtl_id and im.status <> 'D'";
      }else{
        sql = "select distinct item_id, item_name from btm_im_item_master im, btm_im_attr_dtl ad where im.art_no = '" +
            txtArtNo.getText().trim() +
            "' and im.attr2_dtl_id = '-1'" +
            " and im.attr2_dtl_id = ad.attr_dtl_id and im.status <> 'D'";
      }
    }
    if (!sql.equalsIgnoreCase("")){
      ResultSet rs = null;
//      System.out.println("aaaa " + sql);
      try {
        rs = DAL.executeQueries(sql);
        if (rs.next()) {
          result = rs.getString("item_id");
          this.itemName = rs.getString("item_name");
          if (txtArtNo.getText().equalsIgnoreCase("") &&
              txtSize.getText().equalsIgnoreCase("")) {
            txtArtNo.setText(rs.getString("art_no"));
            txtSize.setText(rs.getString("attr_dtl_desc"));
          }
        }
        rs.getStatement().close();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  void txtSize_keyPressed(KeyEvent e) {

  }

  void txtSize_keyTyped(KeyEvent e) {

    if (e.getKeyChar() == KeyEvent.VK_ENTER){

      if (!txtArtNo.getText().equalsIgnoreCase("") && !txtSize.getText().equalsIgnoreCase("")){
        this.itemID = checkArtNo();
        if (this.itemID.equalsIgnoreCase("")) {
          ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS312_UPCOrSizeInvalid"));
          return;
        }
      }
      txtStockOnHand.requestFocus(true);
      return;
    }
  }

  void cboStoreID_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtArtNo.requestFocus(true);
    }
  }

  void txtArtNo_focusLost(FocusEvent e) {
    artNoInput=true;
  }


}

class PanelStockOnHand_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelStockOnHand adaptee;

  PanelStockOnHand_btnAdd_actionAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelStockOnHand_txtArtNo_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStockOnHand adaptee;

  PanelStockOnHand_txtArtNo_focusAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtArtNo_focusLost(e);
  }
}

class PanelStockOnHand_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockOnHand adaptee;

  PanelStockOnHand_txtArtNo_keyAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtArtNo_keyPressed(e);
  }
}

class PanelStockOnHand_txtStockOnHand_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockOnHand adaptee;

  PanelStockOnHand_txtStockOnHand_keyAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtStockOnHand_keyTyped(e);
  }
}

class PanelStockOnHand_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelStockOnHand adaptee;

  PanelStockOnHand_btnDone_actionAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelStockOnHand_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelStockOnHand adaptee;

  PanelStockOnHand_btnCancel_actionAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelStockOnHand_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelStockOnHand adaptee;

  PanelStockOnHand_btnDelete_actionAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelStockOnHand_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelStockOnHand adaptee;

  PanelStockOnHand_btnModify_actionAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelStockOnHand_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelStockOnHand adaptee;

  PanelStockOnHand_table_mouseAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelStockOnHand_txtSohUpdateDatetime_actionAdapter implements java.awt.event.ActionListener {
  PanelStockOnHand adaptee;

  PanelStockOnHand_txtSohUpdateDatetime_actionAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtSohUpdateDatetime_actionPerformed(e);
  }
}

class PanelStockOnHand_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelStockOnHand adaptee;

  PanelStockOnHand_btnView_actionAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelStockOnHand_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockOnHand adaptee;

  PanelStockOnHand_table_keyAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class PanelStockOnHand_txtSize_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockOnHand adaptee;

  PanelStockOnHand_txtSize_keyAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtSize_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSize_keyTyped(e);
  }
}

class PanelStockOnHand_cboStoreID_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockOnHand adaptee;

  PanelStockOnHand_cboStoreID_keyAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboStoreID_keyTyped(e);
  }
}

class PanelStockOnHand_txtStockOnHand_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStockOnHand adaptee;

  PanelStockOnHand_txtStockOnHand_focusAdapter(PanelStockOnHand adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtStockOnHand_focusLost(e);
  }

}

