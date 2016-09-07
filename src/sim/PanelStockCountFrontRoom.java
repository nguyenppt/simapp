//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//














































package sim;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import btm.swing.*;
import common.*;
import java.sql.*;
import java.util.Vector;
import java.util.ResourceBundle;
import java.awt.event.*;
import btm.attr.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//Stock count for Front room
//Update soh and Front room
public class PanelStockCountFrontRoom extends JPanel {
  //Column of Stock count table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_NAME = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_SYSTEM_COUNT = 4; //
  private static final int COL_USER_COUNT = 5; //
  private static final int COL_DESCRIPTION = 6; //

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);
  DataAccessLayer DAL;
  Utils ut = new Utils();
  FrameMainSim frmMain;
  //flag
  int enterCount = 0;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  boolean not_exist = false;
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
          case COL_SYSTEM_COUNT: return Long.class;
          case COL_USER_COUNT: return Long.class;
          default: return Object.class;
      }
    }
    public boolean isCellEditable(int row, int col){
      return false;
    }
  };
  BJTable table = new BJTable(dm,true);
  BJButton btnCancel = new BJButton();
  BJButton btnDone = new BJButton();
  BorderLayout borderLayout2 = new BorderLayout();
  Statement stmt = null;
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField txtUserCount = new JTextField();
  BJComboBox cboStoreID = new BJComboBox();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JLabel jLabel4 = new JLabel();
  JTextField txtArtNo = new JTextField();
  BJButton btnView = new BJButton();
  BJButton btnAdd = new BJButton();
  JPanel jPanel9 = new JPanel();
  JTextArea txtItemdesc = new JTextArea();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextArea txtDesc = new JTextArea();
  JLabel jLabel5 = new JLabel();
  JButton btnUPC = new JButton();

  boolean IS_ENTER = true;
  JLabel jLabel3 = new JLabel();
  JTextField txtPrdGroup = new JTextField();
  JButton btnSearchPrdGroup = new JButton();//first time press Enter

  public PanelStockCountFrontRoom(FrameMainSim frmMain) {
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
//        System.out.println("N17");
    registerKeyboardActions();
    borderLayout1.setHgap(0);
    this.setPreferredSize(new Dimension(800, 600));
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel2.setMinimumSize(new Dimension(10, 10));
    jPanel2.setPreferredSize(new Dimension(800, 150));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(800, 375));
    jScrollPane1.setPreferredSize(new Dimension(790, 375));
    btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelStockCountFrontRoom_btnCancel_actionAdapter(this));
    btnDone.setText("Done (F1)");
    btnDone.addActionListener(new PanelStockCountFrontRoom_btnDone_actionAdapter(this));
    jPanel4.setPreferredSize(new Dimension(50, 50));
    jPanel5.setMinimumSize(new Dimension(10, 10));
    jPanel5.setPreferredSize(new Dimension(320, 50));
    jPanel6.setPreferredSize(new Dimension(400, 50));
    jPanel6.setLayout(borderLayout3);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setText("Store :");
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText("User Count:");
    txtUserCount.setFont(new java.awt.Font("Dialog", 1, 12));
    txtUserCount.setPreferredSize(new Dimension(180, 21));
    txtUserCount.addKeyListener(new PanelStockCountFrontRoom_txtUserCount_keyAdapter(this));
    txtUserCount.addFocusListener(new PanelStockCountFrontRoom_txtStockOnHand_focusAdapter(this));
    cboStoreID.setFont(new java.awt.Font("Dialog", 1, 12));
    cboStoreID.setPreferredSize(new Dimension(180, 21));
    cboStoreID.addActionListener(new PanelStockCountFrontRoom_cboStoreID_actionAdapter(this));
    table.addKeyListener(new PanelStockCountFrontRoom_table_keyAdapter(this));
    jPanel8.setPreferredSize(new Dimension(320, 50));
    jPanel7.setPreferredSize(new Dimension(80, 50));
    jLabel4.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel4.setPreferredSize(new Dimension(120, 20));
    jLabel4.setText("UPC:");
    txtArtNo.setFont(new java.awt.Font("Dialog", 1, 12));
    txtArtNo.setPreferredSize(new Dimension(150, 21));
    txtArtNo.setText("");
    txtArtNo.addKeyListener(new PanelStockCountFrontRoom_txtArtNo_keyAdapter(this));
    btnView.setText("Print (F5)");
    btnView.addActionListener(new PanelStockCountFrontRoom_btnView_actionAdapter(this));
    btnAdd.setText("Add (F2)");
    btnAdd.addActionListener(new PanelStockCountFrontRoom_btnAdd_actionAdapter(this));
    jPanel9.setPreferredSize(new Dimension(10, 65));
    jPanel9.setLayout(null);
    txtItemdesc.setToolTipText("");
    txtItemdesc.setEditable(true);
    txtItemdesc.setText("");
    txtItemdesc.setLineWrap(true);
    txtItemdesc.setWrapStyleWord(true);
    jScrollPane2.setBounds(new Rectangle(193, 10, 515, 53));
    txtDesc.setFont(new java.awt.Font("SansSerif", 1, 12));
    txtDesc.setBorder(null);
    txtDesc.setPreferredSize(new Dimension(200, 95));
    txtDesc.setText("");
    txtDesc.setRows(0);
    txtDesc.setWrapStyleWord(false);
    jLabel5.setText("Reason:");
    jLabel5.setBounds(new Rectangle(71, 11, 120, 20));
    jLabel5.setPreferredSize(new Dimension(120, 20));
    jLabel5.setFont(new java.awt.Font("Dialog", 1, 12));
    table.addMouseListener(new PanelStockCountFrontRoom_table_mouseAdapter(this));
    btnUPC.setFont(new java.awt.Font("Dialog", 1, 11));
    btnUPC.setPreferredSize(new Dimension(25, 22));
    btnUPC.setText("...");
    btnUPC.addActionListener(new PanelStockCountFrontRoom_btnUPC_actionAdapter(this));
    jLabel3.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setText("Add Group:");
    btnSearchPrdGroup.setFont(new java.awt.Font("Dialog", 1, 11));
    btnSearchPrdGroup.setPreferredSize(new Dimension(25, 22));
    btnSearchPrdGroup.setText("...");
    btnSearchPrdGroup.addActionListener(new PanelStockCountFrontRoom_btnSearchPrdGroup_actionAdapter(this));
    txtPrdGroup.setEnabled(false);
    txtPrdGroup.setFont(new java.awt.Font("Dialog", 1, 12));
    txtPrdGroup.setPreferredSize(new Dimension(150, 21));
    txtPrdGroup.setEditable(true);
    txtPrdGroup.setText("");
    this.add(jPanel1,  BorderLayout.NORTH);
    jPanel1.add(btnDone, null);
    jPanel1.add(btnAdd, null);
    jPanel1.add(btnView, null);
    jPanel1.add(btnCancel, null);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel4,  BorderLayout.WEST);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel2.add(jPanel6,  BorderLayout.EAST);
    jPanel6.add(jPanel7,  BorderLayout.EAST);
    jPanel6.add(jPanel8,  BorderLayout.CENTER);
    jPanel8.add(jLabel4, null);
    jPanel8.add(txtArtNo, null);
    jPanel8.add(btnUPC, null);
    jPanel8.add(jLabel2, null);
    jPanel8.add(txtUserCount, null);
    jPanel2.add(jPanel9,  BorderLayout.SOUTH);
    jPanel9.add(jScrollPane2, null);
    jPanel9.add(jLabel5, null);
    jScrollPane2.getViewport().add(txtDesc, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    jPanel5.add(jLabel1, null);
    jPanel5.add(cboStoreID, null);
    jPanel5.add(jLabel3, null);
    jPanel5.add(txtPrdGroup, null);
    jPanel5.add(btnSearchPrdGroup, null);
    jScrollPane2.getViewport();
    initTable();
  }

  private void initTable() {
    //define for table
    String[] columnNames = new String[] {
        "Item ID", "UPC", "Item Name", "Size ", "System Count", "User Count FR", "Reason"};
    dm.setDataVector(new Object[][] {}
                                  , columnNames);
    table.setColumnWidth(COL_ITEM_ID, 90);
    table.setColumnWidth(COL_ART_NO, 90);
    table.setColumnWidth(COL_ITEM_NAME, 150);
    table.setColumnWidth(COL_SIZE, 30);
    table.setColumnWidth(COL_SYSTEM_COUNT, 70);
    table.setColumnWidth(COL_USER_COUNT, 70);
    table.setColumnWidth(COL_DESCRIPTION, 60);
    table.setColor(Color.lightGray, Color.white);
    table.setCellEditable(COL_USER_COUNT);
  }
  public void applyPermission() {
   EmpRight er = new EmpRight();
   er.initData(DAL, DAL.getEmpID());
   er.setData(DAL.getEmpID(), Constant.SCRS_STOCK_COUNT_FRONT_ROOM);
   btnAdd.setEnabled(er.getAdd());
   btnDone.setEnabled(er.getAdd());
   if(!er.getAdd()){
     ut.showMessage(frmMain, lang.getString("TT002"),
                    lang.getString("MS1085_CanNotUseScreen"));

   }

 }

  //init Screen
  void initScreen(){
    this.enterCount = 0;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select store_id, name from btm_im_store order by upper(name)";

    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
      cboStoreID.setData1(rs);
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }

  }


  void cboStoreID_actionPerformed(ActionEvent e) {
    if (cboStoreID.getSelectedIndex() == 0 ){
      //jLabel1.setText("Select a store name to view stock on hand: ");
      while(table.getRowCount()>0){
        dm.removeRow(0);
      }
    }

  }
 void updateQuantity(){
     if (txtUserCount.getText().equalsIgnoreCase("")){
       ut.showMessage(frmMain, "Warning!","User Count is not null");
       txtUserCount.requestFocus(true);
       return;
     }
     if (table.getSelectedRow() != -1){
       this.enterCount ++;
       table.setValueAt(new Double(ut.convertToDouble(txtUserCount.getText())),table.getSelectedRow(),COL_USER_COUNT);
       table.setValueAt(txtDesc.getText(),table.getSelectedRow(),COL_DESCRIPTION);
       txtUserCount.setText("");
       txtDesc.setText("");
       table.requestFocus(true);
     }
     if (this.enterCount >= 1) {
       cboStoreID.setEnabled(false);

     }
     txtArtNo.setText("");
}
  void txtUserCount_keyTyped(KeyEvent e) {
     ut.checkNumberPositive(e,txtUserCount.getText());
    }
  //Yen.Dinh 19-06-2006
  void txtUserCount_focusLost(FocusEvent e) {
    if (txtUserCount.isEditable()) {
          if (!txtUserCount.getText().equalsIgnoreCase("")) {
            txtUserCount.setText("" + ut.formatNumber(txtUserCount.getText()));
          }
        }

  }


  void table_keyTyped(KeyEvent e) {
//    if (e.getKeyChar() == KeyEvent.VK_ENTER){
//      txtUserCount.requestFocus(true);
//    }
  }

  void table_keyPressed(KeyEvent e) {
//    if (e.getKeyChar() == KeyEvent.VK_SPACE){
//      txtUserCount.requestFocus(true);
//      return;
//    }
    char key = e.getKeyChar();
    String sNode = table.getValueAt(table.getSelectedRow(),
                                               COL_USER_COUNT).toString();
    if ( ( (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
          (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) ||
          (e.getKeyCode() == KeyEvent.VK_DELETE) ||
          (key == '-' && sNode.equals("")) ||
          (key == '.' && ut.countDot(sNode) == 0 && !sNode.equals("")))
        && (table.getSelectedColumn() == COL_USER_COUNT)) {
      if (IS_ENTER == true) {
        IS_ENTER = false; // modifying
        table.setValueAt("", table.getSelectedRow(), COL_USER_COUNT);
      }
    }
    else {
      if (e.getKeyCode() != KeyEvent.VK_DOWN &&
          e.getKeyCode() != KeyEvent.VK_UP &&
          e.getKeyCode() != KeyEvent.VK_LEFT &&
          e.getKeyCode() != KeyEvent.VK_RIGHT &&
          e.getKeyCode() != KeyEvent.VK_ENTER)
        e.consume();
    }
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      IS_ENTER = true;
    }

  }

  void resetData() {
    cboStoreID.setSelectedData(DAL.getStoreID());
    txtArtNo.setText("");
    txtUserCount.setText("");
    txtPrdGroup.setText("");
    this.enterCount = 0;
    cboStoreID.setEnabled(true);
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
  }
  void btnCancel_actionPerformed(ActionEvent e) {
    resetData();
    frmMain.showScreen(Constant.SCRS_PICK_LIST);
  }

  void btnDone_actionPerformed(ActionEvent e) {
    ut.writeToTextFile("file\\error.txt","Hist STF  "+ ut.getSystemDateTime()+ "\r\n");

    String today = "";
    today = ut.getSystemDateTime();
    today = today.substring(0,2) + today.substring(3, 5) + today.substring(8,10)
            +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);
    String stkID = cboStoreID.getSelectedData() + DAL.getHostID()+ today;
    Vector vSql = new Vector();
    for (int i=0; i< table.getRowCount(); i++){
      if (table.getValueAt(i,COL_USER_COUNT) !=null && !table.getValueAt(i,COL_USER_COUNT).toString().trim().equalsIgnoreCase("")){
        double systemCount = ut.convertToDouble(table.getValueAt(i,COL_SYSTEM_COUNT).toString());
        double userCount = ut.convertToDouble(table.getValueAt(i,COL_USER_COUNT).toString());
        double variantQty = userCount - systemCount;
        String sDesc = table.getValueAt(i,COL_DESCRIPTION).toString();
        String sql = "insert into BTM_IM_STOCK_COUNT_FR(stk_id, store_id, item_id, stk_counted,STK_COUNTER, stk_date, variance_qty,DESCRIPTION) " +
            " values('" + stkID + "'," + Long.parseLong(cboStoreID.getSelectedData()) +
            ",'" + table.getValueAt(i,COL_ITEM_ID).toString() + "'," + userCount +",'"+DAL.getEmpCode()+ "',to_date('" +
            ut.getSystemDateTime1() + "','" + ut.DATETIME_FORMAT2 + "')," + variantQty + ",'"+sDesc+"')";
//        System.out.println(sql);
        vSql.addElement(sql);
        sql = "update btm_im_item_loc_soh set FRONT_ROOM_QTY =  " + userCount +
           ", STOCK_ON_HAND = "+ userCount +" + BACK_ROOM_QTY, soh_update_datetime = to_date('" + ut.getSystemDate() + "','" + ut.DATE_FORMAT + "')" +
            " where item_id = '" + table.getValueAt(i,COL_ITEM_ID).toString() + "' and store_id = " +
            Long.parseLong(cboStoreID.getSelectedData());
//        System.out.println(sql);
        vSql.addElement(sql);
      }
    }
    if (!vSql.isEmpty()){

      DAL.setBeginTrans(DAL.getConnection());
      DAL.executeBatchQuery(vSql);
      DAL.setEndTrans(DAL.getConnection());
    }
    resetData();
}


  void txtArtNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtArtNo,ItemMaster.LEN_ITEM_ARTNO);
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
     if(!txtArtNo.getText().equalsIgnoreCase("")){
        if (txtArtNo.getText().trim().length() < ItemMaster.LEN_ITEM_ARTNO) {
          ut.showMessage(frmMain, "Warning!", "The length of UPC should be 13.");
          txtArtNo.requestFocus(true);
          txtArtNo.selectAll();
          return;
        }
        if (!checkExistOnGrid(txtArtNo.getText())) {
          insertItemToGrid(txtArtNo.getText());
          if(not_exist == true){
            ut.showMessage(frmMain, "Warning!", "This item does not exist.");
            txtArtNo.selectAll();
            txtArtNo.requestFocus(true);
          }

        }else{
          ut.showMessage(frmMain, "Warning!", "This item is already existed in the grid.");
        }
      }
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
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2) {
        btnAdd.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F5) {
        btnView.doClick();
      }else if (identifier.intValue() == KeyEvent.VK_F12 ||
                identifier.intValue() == KeyEvent.VK_ESCAPE) {


          btnCancel.doClick();
      }
      }
    }


  void btnView_actionPerformed(ActionEvent e) {
    //Change to Print
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    updateQuantity();
  }

  void table_mouseClicked(MouseEvent e) {
      txtArtNo.setText(table.getValueAt(table.getSelectedRow(), COL_ART_NO).toString());
      txtUserCount.requestFocus();
  }
  private boolean checkExistOnGrid(String upc) {
   for (int i = 0; i < dm.getRowCount(); i++) {
     if (dm.getValueAt(i, COL_ART_NO).toString().equalsIgnoreCase(upc.trim()))
       return true;
   }
   return false;
 }

  void setData(Vector vUPC) {
    for (int i = 0; i < vUPC.size(); i++) {
      if (!checkExistOnGrid(vUPC.get(i).toString().trim())) {
        insertItemToGrid(vUPC.get(i).toString().trim());
      }
    }
  }
  void insertItemToGrid(String upc) {
    ResultSet rs = null;
    String SQL = "Select im.item_id, im.art_no, im.item_name, ad.attr_dtl_desc, "
           + " ils.FRONT_ROOM_QTY, ' ' user_count, ' ' reason "
           + " from btm_im_item_loc_soh ils, btm_im_item_master im, btm_im_attr_dtl ad "
           + " where im.status <> 'D' and ils.item_id = im.item_id "
           + " and im.attr2_dtl_id = ad.attr_dtl_id "
           + " and store_id = '" + cboStoreID.getSelectedData() + "'"
           + " and im.art_no = '" + upc + "'"
           + " order by im.art_no, im.item_id ";

    try {
//      System.out.println(SQL);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(SQL, stmt);
      if (rs.next()) {
        dm.addRow(new Object[] {rs.getString("ITEM_ID"),
                          rs.getString("ART_NO"),
                          rs.getString("ITEM_NAME"),
                          rs.getString("ATTR_DTL_DESC"),
                          rs.getString("FRONT_ROOM_QTY"),
                          rs.getString("USER_COUNT"),
                          rs.getString("REASON")});
        not_exist = false;
      } else{
        not_exist = true;
      }

      rs.close();
      stmt.close();
    }
    catch (Exception ex) {}
  }


  void btnUPC_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        "JSIM - ITEM SEARCH", true, frmMain, itemBusObject);
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.cboStatus.setEnabled(false);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      if (dlgItemLookup.getUPCList().isEmpty()) {
        ut.showMessage(frmMain, "Warning",
                       "The item(s) is not selected or not approved.");
        return;
      }
      setData(dlgItemLookup.getUPCList());
      txtArtNo.setText("");
    }

  }

  void txtUserCount_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      updateQuantity();
    }
  }

  void table_keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (table.getSelectedRow() > 0 && (table.getValueAt(table.getSelectedRow()-1, COL_USER_COUNT).equals("")
         || table.getValueAt(table.getSelectedRow()-1, COL_USER_COUNT).toString().equals("0")
         || !ut.isDoubleString(table.getValueAt(table.getSelectedRow()-1, COL_USER_COUNT).toString()))) {
         table.setValueAt("", table.getSelectedRow()-1, COL_USER_COUNT);
      }
      if (table.getRowCount() > 0 && (table.getValueAt(table.getRowCount()-1, COL_USER_COUNT).equals("")
         || table.getValueAt(table.getRowCount()-1, COL_USER_COUNT).toString().equals("0")
         || !ut.isDoubleString(table.getValueAt(table.getRowCount()-1, COL_USER_COUNT).toString()))) {
         table.setValueAt("", table.getRowCount()-1, COL_USER_COUNT);
      }
      txtArtNo.setText(table.getValueAt(table.getSelectedRow(),COL_ART_NO).toString());
   }
   if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
     txtArtNo.setText(table.getValueAt(table.getSelectedRow(),COL_ART_NO).toString());
   }
  }

  void btnSearchPrdGroup_actionPerformed(ActionEvent e) {
     btm.business.ProdGroupBusObj prdGrpBusObject = new btm.business.ProdGroupBusObj();
     DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(frmMain,"JSIM - PROD GROUP SEARCH",true,frmMain,prdGrpBusObject);
     dlgProdGroupSearch.txtSupplier.setEnabled(false);
     dlgProdGroupSearch.btnSupplier.setEnabled(false);
     dlgProdGroupSearch.cboType.setSelectedItem("Stock Count");
     frmMain.pnlProdGroupDetail.SCREEN_FLAG = frmMain.pnlProdGroupDetail.STOCK_COUNT_FR;
     dlgProdGroupSearch.setVisible(true);
     if (dlgProdGroupSearch.done){
        if (dlgProdGroupSearch.getPGID().equals("")){
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1079_ItemNotSelectedOrNotApproved") );
          return;
        }
        frmMain.pnlProdGroupDetail.setDataDetail(dlgProdGroupSearch.getPGID());
        frmMain.showScreen(Constant.SCRS_PROD_GROUP_DETAIL);
     }else{
       frmMain.showScreen(Constant.SCRS_STOCK_COUNT_FRONT_ROOM);
     }
  }
}

class PanelStockCountFrontRoom_cboStoreID_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_cboStoreID_actionAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboStoreID_actionPerformed(e);
  }
}

class PanelStockCountFrontRoom_txtUserCount_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_txtUserCount_keyAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtUserCount_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtUserCount_keyPressed(e);
  }
}

class PanelStockCountFrontRoom_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_table_keyAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.table_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
  public void keyReleased(KeyEvent e) {
    adaptee.table_keyReleased(e);
  }
}

class PanelStockCountFrontRoom_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_btnCancel_actionAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelStockCountFrontRoom_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_btnDone_actionAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelStockCountFrontRoom_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_txtArtNo_keyAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelStockCountFrontRoom_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_btnView_actionAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelStockCountFrontRoom_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_btnAdd_actionAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}
//Yen.Dinh 19-06-2006
class PanelStockCountFrontRoom_txtStockOnHand_focusAdapter extends java.awt.event.FocusAdapter {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_txtStockOnHand_focusAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtUserCount_focusLost(e);
  }

}

class PanelStockCountFrontRoom_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_table_mouseAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelStockCountFrontRoom_btnUPC_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_btnUPC_actionAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnUPC_actionPerformed(e);
  }
}

class PanelStockCountFrontRoom_btnSearchPrdGroup_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFrontRoom adaptee;

  PanelStockCountFrontRoom_btnSearchPrdGroup_actionAdapter(PanelStockCountFrontRoom adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchPrdGroup_actionPerformed(e);
  }
}
