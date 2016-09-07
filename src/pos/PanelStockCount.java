//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//













































package pos;

import btm.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;
import common.*;
import btm.attr.*;
import java.awt.event.*;
import java.sql.*;
import java.beans.*;
import java.sql.Statement;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//not use now
public class PanelStockCount extends JPanel {

  private static final int NS_NEW_SALE_BTN =200 ;
  private static final int NS_SEARCH_ITEM_BTN =209 ;
  private static final int NUM_CHAR_SEARCH =3 ;
  private static final int STOCK_COUNT = 4;
  //Column Order of Sale Item Table
  private static final int COL_ITEM_ID = 0; //
  private static final int COL_ART_NO = 1;
  private static final int COL_ITEM_DESC = 2; //
  private static final int COL_SIZE = 3;
  private static final int COL_SYSTEM_COUNT = 4; //
  private static final int COL_MANUAL_COUNT = 5;

  int flagButton=NS_NEW_SALE_BTN;//dertermine button on screen.

  Statement stmt = null;
  String itemId,stkCounted;
  long stkId,storeId;
  String stkDated;
  String sql;
  ResultSet rs=null;
  DataAccessLayer DAL ;
  Utils ut = new Utils();
  Vector vSql = new Vector();
  Vector vSelectedRow = new Vector();

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanelMain = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JLabel jLabel2 = new JLabel();
  JTextField txtStockCountDate = new JTextField();
  JButton btnChoiceDate = new JButton();
  JPanel jPanelNorth = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField txtStockCountID = new JTextField();
  BJPanel jPanelCenterTable = new BJPanel(Constant.PANEL_TYPE_DEFAULT);
  BJPanel jPanelCenterNorth = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BorderLayout borderLayout2 = new BorderLayout();


  JLabel lblInform = new JLabel();
  JPanel jPanelCenter = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();

  Object[][] rowdata = new Object[0][0];
  String[] columnNames = new String[]{"Item ID","Art No","Item Name","Size","System count","Manual Count"};
  SortableTableModel model = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch (col){
        case COL_SYSTEM_COUNT: return Long.class;
        case COL_MANUAL_COUNT: return Long.class;
        default: return Object.class;
      }
    }
    public boolean isCellEditable(int rowIndex, int mColIndex) {
      return mColIndex==COL_MANUAL_COUNT ? true:false;
    }
  };
  BJTable jTable = new BJTable(model){
    public Class getColumnClass(int col){
      switch (col){
        case COL_SYSTEM_COUNT: return Long.class;
        case COL_MANUAL_COUNT: return Long.class;
        default: return Object.class;
      }
    }
    public void tableChanged(TableModelEvent e){
      super.tableChanged(e);
      repaint();
    }
  };
//  JTable jTable = new JTable();


  FrameMain frmMain;
  BJButton btnPrint = new BJButton();
  BJButton btnSave = new BJButton();
  BJButton btnQuantity = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnCancel = new BJButton();

  public PanelStockCount() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public PanelStockCount(  FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      DAL =frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
//        System.out.println("N16");
    registerKeyboardActions();
    jPanelMain.setPreferredSize(new Dimension(600, 300));
    jPanelMain.setRequestFocusEnabled(true);
    jPanelCenterTable.setBackground(SystemColor.control);
    jPanelCenterTable.setPreferredSize(new Dimension(600, 340));
    jPanelCenterTable.setLayout(flowLayout1);
    jPanelCenter.setBackground(Color.lightGray);
    jPanelCenter.setPreferredSize(new Dimension(600, 100));
    jPanelCenter.setLayout(borderLayout2);
    lblInform.setText("Stock Count Date");
    lblInform.setFont(new java.awt.Font("SansSerif", 1, 15));
    jPanelCenterNorth.setBackground(SystemColor.control);
    jPanelCenterNorth.setPreferredSize(new Dimension(600, 30));
    txtStockCountID.setFont(new java.awt.Font("Dialog",1,12));
    txtStockCountID.setPreferredSize(new Dimension(20,20));
    txtStockCountID.setEditable(false);
    txtStockCountID.setBounds(new Rectangle(152, 8, 183, 27));
    jLabel1.setBounds(new Rectangle(10, 8, 129, 27));
    jLabel1.setText("Stock Count ID");
    jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12));
    jPanelNorth.setBackground(SystemColor.control);
    jPanelNorth.setPreferredSize(new Dimension(600, 80));
    jPanelNorth.setLayout(null);
    btnChoiceDate.setText("...");
    btnChoiceDate.addActionListener(new PanelStockCount_btnChoiceDate_actionAdapter(this));
    btnChoiceDate.setBackground(SystemColor.control);
    btnChoiceDate.setBounds(new Rectangle(260, 41, 33, 25));
    txtStockCountDate.setBounds(new Rectangle(152, 40, 107, 27));
    txtStockCountDate.addActionListener(new PanelStockCount_txtStockCountDate_actionAdapter(this));
    txtStockCountDate.setText("");
    txtStockCountDate.setBackground(SystemColor.control);
    txtStockCountDate.setFont(new java.awt.Font("Dialog", 1, 12));
    txtStockCountDate.setEditable(false);
    jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel2.setText("Stock Count Date");
    jLabel2.setBounds(new Rectangle(10, 40, 129, 27));
    this.setLayout(borderLayout1);
    jPanelMain.setLayout(borderLayout3);
    btnPrint.setBounds(new Rectangle(12, 9, 73, 25));
    btnPrint.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnPrint.setToolTipText("Print(F1)");
    btnPrint.setText("Print (F1)");
    btnSave.setText("Save (F1)");
    btnSave.addActionListener(new PanelStockCount_btnSave_actionAdapter(this));
    btnSave.addMouseListener(new PanelStockCount_btnSave_mouseAdapter(this));
    btnSave.setBounds(new Rectangle(14, 51, 73, 25));
    btnSave.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnSave.setToolTipText("Save (F1)");
    btnCancel.setBounds(new Rectangle(18, 95, 73, 25));
    btnCancel.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnCancel.setToolTipText("Cancel (F4)");
    btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4<" +
    "/html>");
    btnCancel.addActionListener(new PanelStockCount_btnCancel_actionAdapter(this));
    jTable.addKeyListener(new PanelStockCount_jTable_keyAdapter(this));
    jTable.addPropertyChangeListener(new PanelStockCount_jTable_propertyChangeAdapter(this));
    jTable.addInputMethodListener(new PanelStockCount_jTable_inputMethodAdapter(this));
    jTable.addAncestorListener(new PanelStockCount_jTable_ancestorAdapter(this));
    btnQuantity.setToolTipText("Quantity (F2)");
    btnQuantity.setText("<html><center><b>Quantity </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "2</html>");
    btnQuantity.addActionListener(new PanelStockCount_btnQuantity_actionAdapter(this));
    btnDelete.setText("Delete (F3)");
    btnDelete.addActionListener(new PanelStockCount_btnDelete_actionAdapter(this));
    this.add(jPanelMain, BorderLayout.CENTER);
    jPanelMain.add(jPanelNorth,  BorderLayout.NORTH);
    jPanelNorth.add(jLabel2, null);
    jPanelNorth.add(jLabel1, null);
    jPanelNorth.add(txtStockCountID, null);
    jPanelNorth.add(txtStockCountDate, null);
    jPanelNorth.add(btnChoiceDate, null);
    jPanelMain.add(jPanelCenter, BorderLayout.CENTER);
    jPanelCenter.add(jPanelCenterNorth, BorderLayout.NORTH);
    jPanelCenterNorth.add(lblInform, null);
    jPanelCenter.add(jPanelCenterTable, BorderLayout.CENTER);
    JScrollPane jScrollPaneTable = new JScrollPane(jTable);
    jScrollPaneTable.setPreferredSize(new Dimension(650, 350));
    jPanelCenterTable.add(jScrollPaneTable, null);
    jScrollPaneTable.setViewportView(jTable);
    jScrollPaneTable.getViewport().setBackground(SystemColor.control);
//    jScrollPaneTable.add(jTable, null);
//    jScrollPaneTable.add(jTable, null);
//    jPanelCenterTable.add(jScrollPaneTable, null);
    //set table model
    this.jTable.getTableHeader().setReorderingAllowed(false);
    this.jTable.getTableHeader().setFont(new java.awt.Font("Dialog",1,12));
    this.jTable.setAutoResizeMode(jTable.AUTO_RESIZE_ALL_COLUMNS );

    model.setDataVector(rowdata, columnNames);
//    model.addColumn("Item Code");
//    model.addColumn("b");
//    model.addColumn("c");
//    model.addColumn("d");
    /**************************** LOAD DATA INTO SCREEN *************************/
    initPara();
//    loadDataIntoTable();
    /**************************** LOAD DATA INTO SCREEN *************************/
  }


  //show button in Frame Main
void showButton(){
    //frmMain.showButton(this.btnPrint);
    frmMain.showButton(this.btnSave);
    frmMain.showButton(this.btnQuantity);
    frmMain.showButton(this.btnDelete);
    frmMain.showButton(this.btnCancel);
}


  //update caculate at currrent row
  void updateCalculate(int row, int quantity, double markdown, double amountDue){

//    this.jTable .setValueAt(String.valueOf(quantity), row, COL_QUANTITY);
//    this.jTable .setValueAt(String.valueOf(markdown), row, COL_MARKDOWN);
//    this.jTable .setValueAt(String.valueOf(amountDue), row, COL_AMOUNT);
  }
  void addItemRow(Object[] item){
    model.addRow(item);
  }


  void loadDataIntoTable(String itemId){

    if(checkExistItemCodeInTable(itemId.trim() )){
      JOptionPane.showMessageDialog(this, "This items has existed ","Message", 1);
      return;
    }

    sql=" Select i.ITEM_ID, i.ART_NO, i.ITEM_DESC, i.ATTR2_NAME, l.STOCK_ON_HAND " +
        " From  BTM_POS_ITEM_PRICE i, BTM_POS_ITEM_LOC_SOH l Where i.ITEM_ID=l.ITEM_ID (+) " +
        " and i.ITEM_ID='" + itemId +"'";
//    System.out.println(sql);
    try {
      stmt = DAL.getConnection().createStatement();
      rs=DAL.executeQueries(sql,stmt);
      while(rs.next()){
        addItemRow(new Object[] {rs.getString("ITEM_ID"),rs.getString("ART_NO"),rs.getString("ITEM_DESC"),rs.getString("ATTR2_NAME"),new Long(rs.getLong("STOCK_ON_HAND"))
                   ,new Long(0)  });
      }
      stmt.close();
    }catch(Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }
  }

  void initPara(){
    String today="";
    today = ut.getSystemDateTime();
//    System.out.println(today);
    storeId= Long.parseLong(DAL.getStoreID()) ;
//    stkDated= java.sql.Date.valueOf(today) ; java.sql.Date.valueOf("2005-08-08 11:50:00")
//    stkDated= java.sql.Date.valueOf(ut.getSystemDateTime()) ;
    this.txtStockCountDate .setText(today.substring(0,10)) ;
    stkDated= ut.getSystemDate(1);
    today = today.substring(0,2) + today.substring(3, 5) + today.substring(8,10)
            +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);
//    System.out.println(today + " aa " + String.valueOf(storeId));

    stkId = Long.parseLong( String.valueOf(storeId) + today ) ;
    this.lblInform .setText(" Item in stock for store " + storeId  + " on " + stkDated ) ;
    this.txtStockCountID.setText(String.valueOf(stkId)) ;
  }


  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.setTitle("JPOS - MAIN");
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showInvManageButton();

  }

  void btnChoiceDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnChoiceDate.getX() + posXFrame+90;
    posY = this.btnChoiceDate.getY() + posYFrame+90;
    TimeDlg birthday = new TimeDlg(null);
    birthday.setTitle("Choose Stock Count Date");
    birthday.setResizable(false);
    birthday.setLocation(posX, posY);
    birthday.setVisible(true);
    if (birthday.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd-MM-yyyy");
      String date = fd.format(birthday.getDate());
      this.txtStockCountDate.setText(date);
      stkDated= this.txtStockCountDate.getText() ;
      this.lblInform .setText(" Item in stock for store " + storeId  + " on " + stkDated ) ;
    }
  }

  void addDataIntoDatabase(){
    try {
      DAL.setBeginTrans(DAL.getConnection() ) ;

      DAL.setEndTrans(DAL.getConnection() ) ;

    }
    catch (Exception e) {
        DAL.setRollback(DAL.getConnection() ) ;
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }

  }

  boolean checkExistItemCodeInTable(String itemCode){
      for (int i = 0; i < model.getRowCount(); i++) {
        String itemI = jTable.getValueAt(i,COL_ITEM_ID).toString();
        if (itemCode.compareTo(itemI)==0 ) {
//          model.removeRow(i) ;
          return true;
        }
    }
    return false;
  }

  void addInsertRowIntoVector(long stkId,long storeId,String itemId,long stkCounted,String stkDated){
    vSql.addElement("INSERT INTO BTM_POS_STOCK_COUNT (STK_ID, STORE_ID, ITEM_ID, STK_COUNTED, STK_DATE) VALUES (" +
                    stkId +"," + storeId + ",'" +itemId + "'," + stkCounted + ",to_date('" +
                    stkDated + "','DD-MM-YYYY'))");
    vSql.addElement("update btm_pos_item_loc_soh set stock_on_hand =" + stkCounted +
          " , soh_update_datetime = to_date ('" + ut.getSystemDate(1) +
          "','" + ut.DATE_FORMAT1 + "'),last_update_id ='" + DAL.getEmpPosID1() +
          "' where item_id ='" + itemId +
          "' and store_id =" + storeId);
  }

  void saveStockCount(){
    int selectedRow,i;
    vSelectedRow.trimToSize() ;
    String itemCount,itemId;
    for(i=0;i<vSelectedRow.size()  ;i++){
      selectedRow= Integer.parseInt(  vSelectedRow.elementAt(i).toString() ) ;

      if (!ut.isDoubleString(jTable.getValueAt(selectedRow, COL_MANUAL_COUNT).toString().trim())) {
        ut.showMessage(frmMain,"Warning",
            "Value of Manual Count must be a double type number");
        this.jTable .setValueAt("0",selectedRow,COL_MANUAL_COUNT);
//        return;
      }

//      checkExistItemCodeInVector(jTable.getValueAt(selectedRow, 0).toString());

      itemId=this.jTable.getValueAt(selectedRow, COL_ITEM_ID).toString();
      itemCount=this.jTable.getValueAt(selectedRow,COL_MANUAL_COUNT).toString();
//      this.jTable .setValueAt("0",selectedRow,3);
      addInsertRowIntoVector(this.stkId, this.storeId,itemId,Long.parseLong(itemCount), this.stkDated);
    }
    if (vSql.isEmpty() == false){
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        DAL.executeBatchQuery(vSql, stmt);
        stmt.close();
      }catch(Exception ex){};
      vSql.removeAllElements() ;
      for(i=0;i<vSelectedRow.size()  ; i++){
        selectedRow= Integer.parseInt(  vSelectedRow.elementAt(i).toString() ) ;
        this.jTable .setValueAt("0",selectedRow,COL_MANUAL_COUNT);
      }
      vSelectedRow.removeAllElements() ;
      initPara();
    }
  }

  void saveData(){
    String itemCount,itemId;
    vSql.removeAllElements() ;
    for (int i = 0; i < model.getRowCount(); i++) {
      itemId=model.getValueAt(i,COL_ITEM_ID).toString()  ;
      itemCount=model.getValueAt(i,COL_MANUAL_COUNT).toString().trim() ;
      if (!ut.isDoubleString(itemCount.trim() )) {
        ut.showMessage(frmMain,"Warning",
            "Value of Manual Count at row " + i + " must be a double type number");
        model.setValueAt("0",i,COL_MANUAL_COUNT);
        itemCount="0";
      }
      if (Double.parseDouble(itemCount) < 1){
        ut.showMessage(frmMain,"Warning","You must enter the quantity of these items");
        jTable.setRowSelectionInterval(0,0);
        return;
      }

      addInsertRowIntoVector(this.stkId, this.storeId,itemId,Long.parseLong(itemCount), this.stkDated);

    }
    if (vSql.isEmpty() == false){
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        DAL.executeBatchQuery(vSql);
        stmt.close();
      }
      catch(Exception ex){};
      vSql.removeAllElements() ;
      while(model.getRowCount() >0){
        model.removeRow(0) ;
      }
      initPara();
    }
  }

  void jTable_keyTyped(KeyEvent e) {
//    int selectedRow,selectedCol;s
//    if(e.getKeyCode() == KeyEvent.VK_ENTER ){
//      selectedRow= jTable.getSelectedRow() ;
//      selectedCol=jTable.getSelectedColumn() ;
//      checkExistItemCodeInVector(jTable.getValueAt(selectedRow,0).toString().trim() );
//
//      addInsertRowIntoVector(this.stkId,this.storeId ,this.jTable .getValueAt(selectedRow,0).toString() ,
//                             Long.parseLong( this.jTable .getValueAt(selectedRow,3).toString() ) ,  this.stkDated  );
//
//    }
  }

  void btnSave_mouseClicked(MouseEvent e) {
//    if (vSql.isEmpty() == false){
//      DAL.executeBatchQuery(vSql);
//    }
//    while (model.getRowCount()>0){
//      model.removeRow(0);
//    }
//    for(int i=0;i<model.getRowCount() ;i++){
//
//    }
//    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
  }

  void jTable_propertyChange(PropertyChangeEvent e) {
//    int selectedRow,selectedCol;
  }

  void jTable_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                btnSave.doClick();
              }
//    int selectedRow,selectedCol;
//    System.out.println(jTable.getValueAt(0,3).toString().trim());
//    if(e.getKeyCode() == KeyEvent.VK_ENTER ){
//      selectedRow= jTable.getSelectedRow() ;
//      selectedCol=jTable.getSelectedColumn() ;
//
//      if(!ut.isDoubleString(jTable.getValueAt(selectedRow,3).toString().trim()) ){
//        JOptionPane.showMessageDialog(this.frmMain,
//            "Value of Manual Count must be a double type number",
//            "Invalid Input", JOptionPane.ERROR_MESSAGE);
//        return ;
//      }
//      checkExistItemCodeInVector(jTable.getValueAt(selectedRow,0).toString());
//
//      addInsertRowIntoVector(this.stkId,this.storeId ,this.jTable .getValueAt(selectedRow,0).toString() ,
//                             Long.parseLong( this.jTable .getValueAt(selectedRow,3).toString() ) ,  this.stkDated  );
//
//    }

    int selectedRow;
    selectedRow= jTable.getSelectedRow() ;
    for(int i=0;i<vSelectedRow.size() ;i++){
      if( Integer.parseInt(  vSelectedRow.elementAt(i).toString())==selectedRow  ){
        return;
      }
    }
    vSelectedRow.addElement(String.valueOf( selectedRow)) ;

  }

  void jTable_caretPositionChanged(InputMethodEvent e) {
//    int selectedRow,selectedCol;
//    selectedRow= jTable.getSelectedRow() ;
//    selectedCol=jTable.getSelectedColumn() ;
//    System.out.println("Table at row = " +selectedRow + " ; col = " +selectedCol);

  }

  void jTable_ancestorMoved(AncestorEvent e) {
    if (model.getRowCount()>0){
      int selectedRow, selectedCol;
      selectedRow = jTable.getSelectedRow();
      selectedCol = jTable.getSelectedColumn();
//      System.out.println("Table at row = " + selectedRow + " ; col = " +
//                         selectedCol);
//      System.out.println("Value = " + jTable.getValueAt(selectedRow, selectedCol).toString().trim());
    }
  }

  void btnSave_actionPerformed(ActionEvent e) {
//    saveStockCount();
    saveData();
  }

  void txtMainInput_keyPressed(KeyEvent e) {
    String mainInput = frmMain.getInputText();
    DataAccessLayer DAL = frmMain.getDAL();
    ResultSet rs = null;
    String sqlQuery = "";
    Item item = new Item();

    if (mainInput.equalsIgnoreCase("s") && flagButton != NS_SEARCH_ITEM_BTN) {
      flagButton = NS_SEARCH_ITEM_BTN;
      frmMain.setInputLabel(" Enter key words from the item description.");
      frmMain.setInputText("");
      return;
    }

    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (flagButton == NS_SEARCH_ITEM_BTN) {
        //check input
        if (mainInput.length()<NUM_CHAR_SEARCH){
          ut.showMessage(frmMain, "Warning", "The systems required at least three lettes to search for");
          return;
        }

        DialogSearchResultItem dlgSearchResultItem = new DialogSearchResultItem(frmMain,
            "JPOS - ITEM LOOKUP", true);
        //add seach result item
        sqlQuery="Select * from BTM_POS_ITEM_PRICE where UPPER(ITEM_DESC) LIKE UPPER('%" +mainInput+"%')";
//        System.out.println(sqlQuery);
        try {
          stmt = DAL.getConnection().createStatement();
          rs=DAL.executeQueries(sqlQuery);
          while (rs.next()) {
            item = new Item(rs.getString("ITEM_ID"),rs.getString("ITEM_DESC"),rs.getDouble("UNIT_PRICE"),1,0,"",0,rs.getDouble("UNIT_PRICE"),0);
            dlgSearchResultItem.addItemRow(new Object[] {item.getId(), item.getDescription(),String.valueOf(item.getunitPrice())});
//                    this.loadDataIntoTable(rs.getString("ITEM_ID")) ;
          }
          stmt.close();
        }
        catch (Exception ex) {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(ex);
        }
        if (dlgSearchResultItem.isExistItem()) {
          dlgSearchResultItem.setVisible(true);
          if (dlgSearchResultItem.isOKButton()) {
            item = dlgSearchResultItem.getItem();
//                    addItemRow(new Object[] {item.getId(),
//                               item.getDescription(),
//                               String.valueOf(item.getQuantity()),
//                               String.valueOf(item.getunitPrice()), "0",
//                               String.valueOf(item.getAmountDue())});
            this.loadDataIntoTable(item.getId() ) ;
          }
        }else{
          ut.showMessage(frmMain, "Warning", "No items found matching your description");
          dlgSearchResultItem.dispose();
        }
        frmMain.setInputLabel("Insert or Scan Item to return, Press 's' to search:");
        frmMain.setInputText("");
//              goBackNewSale();
      }
      else if(flagButton == NS_NEW_SALE_BTN){
        if (!ut.isLongIntInput(frmMain, mainInput)) {
          return;
        }
        this.loadDataIntoTable(mainInput) ;
        if (jTable.getRowCount()<1){
          ut.showMessage(frmMain, "Warning", "The item does not exist ");
          return;
        }
//        sqlQuery="Select * from BTM_POS_ITEM_PRICE where ITEM_ID='" +mainInput+"'";
//        System.out.println(sqlQuery);
//        rs=DAL.executeQueries(sqlQuery);
//        try {
//          if (rs.next()) {
//            this.loadDataIntoTable(rs.getString("ITEM_ID") ) ;
//          }else{
//            ut.showMessage(frmMain, "Warning", "Cannot find item ");
//          }
//        }
//        catch (Exception ex) {
//          ExceptionHandle eh = new ExceptionHandle();
//          eh.ouputError(ex);
//        }
      }else if (flagButton == STOCK_COUNT){
        if (jTable.getSelectedRow() != -1){
          if (!ut.isLongIntString(mainInput)) {
            ut.showMessage(frmMain, "Warning", "Wrong number");
            frmMain.txtMainInput.requestFocus(true);
            return;
          }
          if (Long.parseLong(mainInput) < 1) {
            ut.showMessage(frmMain, "Warning", "Quantity can not be less than 1");
            frmMain.txtMainInput.requestFocus(true);
            return;
          }
          jTable.setValueAt(mainInput, jTable.getSelectedRow(), COL_MANUAL_COUNT);
          frmMain.setInputText("");
          flagButton = NS_NEW_SALE_BTN;
          frmMain.setInputLabel(
              " Insert or scan Item to stock count, Press s to search");
        }
      }
      flagButton=NS_NEW_SALE_BTN;
      frmMain.setInputText("");
      if (jTable.getRowCount()>0){
        jTable.setRowSelectionInterval(0,0);
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
 /*         if (identifier.intValue()==KeyEvent.VK_F1){
          btnPrint.doClick();
        }else if (identifier.intValue()==KeyEvent.VK_F1 ){*/
         if (identifier.intValue()==KeyEvent.VK_F1 ){
          btnSave.doClick();
        } else if (identifier.intValue()==KeyEvent.VK_F2 ){
          btnQuantity.doClick();
        }else if (identifier.intValue()==KeyEvent.VK_F3 ){
          btnDelete.doClick();
        }else if( identifier.intValue() == KeyEvent.VK_F4 || identifier.intValue()==KeyEvent.VK_ESCAPE){
          btnCancel.doClick();
        }
    }
}

  void btnQuantity_actionPerformed(ActionEvent e) {
    String mainInput = frmMain.getInputText();
    if (flagButton != STOCK_COUNT) {
      if (jTable.getRowCount()>0){
        flagButton = STOCK_COUNT;
        frmMain.setInputLabel(
            "Insert the quantity to stock count for the specific item");
        frmMain.setInputText("");
        frmMain.txtMainInput.requestFocus(true);
      }
      return;
    }
    if (flagButton == STOCK_COUNT) {
      if (jTable.getSelectedRow() != -1){
        if (!ut.isLongIntString(mainInput)) {
          ut.showMessage(frmMain, "Warning", "Wrong number");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        if (Long.parseLong(mainInput) < 1) {
          ut.showMessage(frmMain, "Warning", "Quantity can not be less than 1");
          frmMain.txtMainInput.requestFocus(true);
          return;
        }
        jTable.setValueAt(mainInput, jTable.getSelectedRow(), COL_MANUAL_COUNT);
        frmMain.setInputText("");
        flagButton = NS_NEW_SALE_BTN;
        frmMain.setInputLabel(
            " Insert or scan Item to stock count, Press s to search");
      }
    }
  }

  void txtStockCountDate_actionPerformed(ActionEvent e) {

  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if (jTable.getRowCount()>0){
      if (jTable.getSelectedRow() != -1){
        model.removeRow(jTable.getSelectedRow());
      }
    }
  }



}

class PanelStockCount_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCount adaptee;

  PanelStockCount_btnCancel_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelStockCount_btnChoiceDate_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCount adaptee;

  PanelStockCount_btnChoiceDate_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnChoiceDate_actionPerformed(e);
  }
}

class PanelStockCount_jTable_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockCount adaptee;

  PanelStockCount_jTable_keyAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jTable_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTable_keyPressed(e);
  }
}

class PanelStockCount_btnSave_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelStockCount adaptee;

  PanelStockCount_btnSave_mouseAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.btnSave_mouseClicked(e);
  }
}

class PanelStockCount_jTable_propertyChangeAdapter implements java.beans.PropertyChangeListener {
  PanelStockCount adaptee;

  PanelStockCount_jTable_propertyChangeAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void propertyChange(PropertyChangeEvent e) {
    adaptee.jTable_propertyChange(e);
  }
}

class PanelStockCount_jTable_inputMethodAdapter implements java.awt.event.InputMethodListener {
  PanelStockCount adaptee;

  PanelStockCount_jTable_inputMethodAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void inputMethodTextChanged(InputMethodEvent e) {
  }
  public void caretPositionChanged(InputMethodEvent e) {
    adaptee.jTable_caretPositionChanged(e);
  }
}

class PanelStockCount_jTable_ancestorAdapter implements javax.swing.event.AncestorListener {
  PanelStockCount adaptee;

  PanelStockCount_jTable_ancestorAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void ancestorAdded(AncestorEvent e) {
  }
  public void ancestorRemoved(AncestorEvent e) {
  }
  public void ancestorMoved(AncestorEvent e) {
    adaptee.jTable_ancestorMoved(e);
  }
}

class PanelStockCount_btnSave_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCount adaptee;

  PanelStockCount_btnSave_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSave_actionPerformed(e);
  }
}

class PanelStockCount_btnQuantity_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCount adaptee;

  PanelStockCount_btnQuantity_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnQuantity_actionPerformed(e);
  }
}

class PanelStockCount_txtStockCountDate_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCount adaptee;

  PanelStockCount_txtStockCountDate_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtStockCountDate_actionPerformed(e);
  }
}

class PanelStockCount_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCount adaptee;

  PanelStockCount_btnDelete_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}
