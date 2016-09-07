//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//

















































package pos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import common.*;
import javax.swing.*;
import btm.swing.*;
import java.sql.*;
import btm.attr.*;
import java.awt.print.*;
import java.util.*;
//import com.borland.jbcl.layout.*;
/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2005</p>
* <p>Company: BTM International Software</p>
* @author TrungNT
* @version 1.0
*/
//not use now
public class PanelReceiptItem extends JPanel {
    private static final int RECEIPT_ITEM_ADD_BTN = 233;
    private static final int RECEIPT_ITEM_QUANTITY_BTN = 234;
  //Column Order of Receipt Item Table
    private static final int RI_COL_ITEM_ID =0;//
    private static final int RI_COL_ART_NO =1;//
    private static final int RI_COL_ART_NAME =2;//
    private static final int RI_COL_SIZE =3;//
    private static final int RI_COL_QUANTITY =4;//
    private static final int RI_COL_PRICE =5;

  FrameMain frmMain;
  //Vector soh
  Vector vSOH = new Vector();
  Statement stmt = null;
  int flagButton=RECEIPT_ITEM_ADD_BTN;
  Utils ut=new Utils();
  DataAccessLayer DAL;
  String mainInput;
  String transID;

  Object[][] rowdata = new Object[0][6];
  String[] columnNames = new String[]{"Item Code","Art No","Art Name","Size","Quantity","Unit Price"};
  SortableTableModel model = new SortableTableModel(){
    public boolean isCellEditable(int rowIndex, int mColIndex) {
      return false;
    }
    public Class getColumnClass(int col){
      switch(col){
        case 2: return Long.class;
        case 3: return Long.class;
        default: return Object.class;
      }
    }
  };

  BorderLayout borderLayout1 = new BorderLayout();
  BJButton btnSave = new BJButton();
  BJButton btnQuantity = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnAddItem = new BJButton();
  BJButton btnView = new BJButton();
  BJButton btnPrintReceipt = new BJButton();
  JPanel pnlNorth = new BJPanel();
  JPanel pnlCenter = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  BJTable tblReceptItem = new BJTable(model){
    public Class getColumnClass(int col){
      switch(col){
        case 2: return Long.class;
        case 3: return Long.class;
        default: return Object.class;
      }
    }
  };
  BorderLayout borderLayout2 = new BorderLayout();
  JLabel lblheader = new JLabel();
  BJButton btnBack = new BJButton();
  BJPanel jPanelCenterNorth = new BJPanel(Constant.PANEL_TYPE_DEFAULT);
  JLabel lblTransferID = new JLabel();
  JTextField txtTransID = new JTextField();
  JLabel lblFromStore = new JLabel();
//  JTextField txtFromStoreID = new JTextField();
  BJComboBox cboFromStore = new BJComboBox();
  JLabel jLabel1 = new JLabel();
  JTextField txtArtNo = new JTextField();
  JTextField txtSize = new JTextField();
  JTextField txtQuantity = new JTextField();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();



  public PanelReceiptItem(FrameMain frmMain) {
    try {
      this.frmMain=frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
//    System.out.println("N14");
    registerKeyboardActions();
//    DAL.getConnfigParameter();
    btnBack.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnBack.setToolTipText("");
    btnBack.setText("Add Item");
    btnBack.addActionListener(new PanelReceiptItem_btnBack_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnDelete.setText("Delete(F3)");
    btnDelete.addActionListener(new PanelReceiptItem_btnDelete_actionAdapter(this));
    btnQuantity.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnQuantity.setText("Quantity(F1)");
    btnQuantity.addActionListener(new PanelReceiptItem_btnQuantity_actionAdapter(this));
    btnSave.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnSave.setText("Save(F2)");
    btnSave.addActionListener(new PanelReceiptItem_btnSave_actionAdapter(this));
    this.setLayout(borderLayout1);
    pnlCenter.setLayout(borderLayout2);
    lblheader.setFont(new java.awt.Font("SansSerif", 1, 13));
    lblheader.setDoubleBuffered(false);
    lblheader.setText("Receipt item for store " + DAL.getStoreID() + " on " + ut.getSystemDate());
//  tblReceptItem.setRowHeight(30);
    btnBack.setSelectedIcon(null);
    btnBack.setText("Back(F7)");
    btnAddItem.addActionListener(new PanelReceiptItem_btnAddItem_actionAdapter(this));
    btnAddItem.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnAddItem.setText("Add Item(F4)");
    btnView.setFont(new java.awt.Font("SansSerif",0,12));
    btnView.addActionListener(new PanelReceiptItem_btnView_actionAdapter(this));
    btnView.setText("<html><center><b>View Receipt </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;F6</html>");
    btnPrintReceipt.addActionListener(new PanelReceiptItem_btnPrintReceipt_actionAdapter(this));
    btnPrintReceipt.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnPrintReceipt.setText("Print(F5)");
    jPanelCenterNorth.setBackground(SystemColor.control);
    jPanelCenterNorth.setOpaque(true);
    jPanelCenterNorth.setPreferredSize(new Dimension(100, 58));
    jPanelCenterNorth.setLayout(null);
    lblTransferID.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblTransferID.setPreferredSize(new Dimension(66, 20));
    lblTransferID.setText("Transfer ID:");
    lblTransferID.setBounds(new Rectangle(25, 5, 66, 20));
    txtTransID.setPreferredSize(new Dimension(160, 20));
    txtTransID.setText("");
    txtTransID.setBounds(new Rectangle(96, 5, 108, 20));
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    lblFromStore.setText("From Store:");
    lblFromStore.setBounds(new Rectangle(233, 5, 66, 20));
    lblFromStore.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblFromStore.setPreferredSize(new Dimension(66, 20));
//    txtFromStoreID.setBounds(new Rectangle(341, 12, 110, 26));
//    txtFromStoreID.addKeyListener(new PanelReceiptItem_txtFromStoreID_keyAdapter(this));
//    txtFromStoreID.setPreferredSize(new Dimension(10, 20));
//    txtFromStoreID.setText("");
    tblReceptItem.addKeyListener(new PanelReceiptItem_tblReceptItem_keyAdapter(this));
    cboFromStore.setPreferredSize(new Dimension(160, 20));
    cboFromStore.setBounds(new Rectangle(332, 5, 108, 20));
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel1.setText("ArtNo:");
    jLabel1.setBounds(new Rectangle(25, 30, 35, 16));
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel2.setText("Size:");
    jLabel2.setBounds(new Rectangle(233, 30, 27, 16));
    jLabel3.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel3.setText("Quantity:");
    jLabel3.setBounds(new Rectangle(469, 30, 50, 16));
    txtQuantity.setText("");
    txtQuantity.setBounds(new Rectangle(531, 28, 113, 21));
    txtQuantity.addKeyListener(new PanelReceiptItem_txtQuantity_keyAdapter(this));
//    txtQuantity.setNextFocusableComponent(txtArtNo);

    txtSize.setText("");
    txtSize.setBounds(new Rectangle(332, 28, 108, 21));
    txtSize.addKeyListener(new PanelReceiptItem_txtSize_keyAdapter(this));
//    txtSize.setNextFocusableComponent(txtQuantity);

    txtArtNo.setText("");
    txtArtNo.setBounds(new Rectangle(96, 28, 108, 21));
    txtArtNo.addFocusListener(new PanelReceiptItem_txtArtNo_focusAdapter(this));
    txtArtNo.addKeyListener(new PanelReceiptItem_txtArtNo_keyAdapter(this));
//    txtArtNo.setNextFocusableComponent(txtSize);
    this.add(pnlNorth,  BorderLayout.NORTH);
    pnlNorth.add(lblheader, null);
    this.add(pnlCenter, BorderLayout.CENTER);
    pnlCenter.add(jScrollPane1, BorderLayout.CENTER);
    pnlCenter.add(jPanelCenterNorth, BorderLayout.NORTH);
    jPanelCenterNorth.add(lblTransferID, null);
    jPanelCenterNorth.add(txtTransID, null);
//    jPanelCenterNorth.add(txtFromStoreID, null);
    jPanelCenterNorth.add(cboFromStore, null);
    jPanelCenterNorth.add(jLabel1, null);
    jPanelCenterNorth.add(txtArtNo, null);
    jPanelCenterNorth.add(lblFromStore, null);
    jPanelCenterNorth.add(jLabel2, null);
    jPanelCenterNorth.add(jLabel3, null);
    jPanelCenterNorth.add(txtSize, null);
    jPanelCenterNorth.add(txtQuantity, null);
    jScrollPane1.getViewport().add(tblReceptItem, null);

    //set table model
//  tblReceptItem.getTableHeader().setReorderingAllowed(false);
//  tblReceptItem.getTableHeader().setFont(new java.awt.Font("Dialog",1,12));
//  tblReceptItem.setAutoResizeMode(tblReceptItem.AUTO_RESIZE_ALL_COLUMNS);
    model.setDataVector(rowdata, columnNames);

  }
  //initialize data
  void initScreen(){
    if(!vSOH.isEmpty()){
      vSOH.clear();
    }
    Statement stmt = null;
    ResultSet rs = null;
    try{
      String sql = "select item_id from btm_pos_item_loc_soh";
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      while (rs.next()){
        vSOH.addElement(rs.getString("item_id"));
      }
      rs.close();
      stmt.close();
      setCboStoreWithoutNoneValue(cboFromStore,DAL);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
//remove row
  void removeRow(){
    while (tblReceptItem.getRowCount()>0){
      model.removeRow(0);
    }
  }



  //insert data into BTM_POS_RECEIPT_ITEM, update  BTM_POS_ITEM_LOC_SOH
  void saveData(){
//    DataAccessLayer DAL = frmMain.getDAL();
    String sSQL=new String();
    String sqlSOH = "";
    boolean flagSOH = false;
    Transaction trans=frmMain.getTransaction();
    String itemID="";
    int quantity=0;
    double unitPrice=0;
    ResultSet rs=null;
    int newQuantity=0;
    String today="";//get today (yyMMddHHmmss) as receiptID

//    /********************** HIEU.PHAM *********************/
//    String transID;
//
//    transID = this.txtTransID.getText().trim();
//      if ( (transID.length() <= 0)) {
//        JOptionPane.showMessageDialog(this.frmMain,
//            "The lengh of Transfer ID can not lower than 0 character",
//            "Invalid Input", JOptionPane.ERROR_MESSAGE);
//        this.txtTransID.requestFocus(true) ;
//        return ;
//      }
//      else if (transID.length() > 25) {
//        JOptionPane.showMessageDialog(this.frmMain,
//            "The lengh of Transfer ID can not greater than 25 character",
//            "Invalid Input", JOptionPane.ERROR_MESSAGE);
//        this.txtTransID.requestFocus(true) ;
//        return ;
//      }
//
//
//    /********************** HIEU.PHAM *********************/

    try{
      today = ut.getSystemDateTime();
      today = today.substring(2, 4) + today.substring(5, 7) + today.substring(8,10)
            +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);
//      today = today.substring(8,10) + today.substring(5,7) + today.substring(2,4)
//              +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);

      for (int i = 0; i < tblReceptItem.getRowCount(); i++) {
        itemID=tblReceptItem.getValueAt(i, RI_COL_ITEM_ID).toString();
        quantity=ut.doubleStringToInt(tblReceptItem.getValueAt(i, RI_COL_QUANTITY).toString());
        unitPrice=ut.convertToDouble(tblReceptItem.getValueAt(i, RI_COL_PRICE).toString());
        for (int j = 0; j< vSOH.size(); j++){
          if (itemID.equalsIgnoreCase(vSOH.elementAt(j).toString())){
            flagSOH = true ;
            break;
          }
        }
        if (!flagSOH){
          sqlSOH = "insert into btm_pos_item_loc_soh (item_id, store_id, stock_on_hand, soh_update_datetime, last_update_id) " +
              " values('" + itemID + "'," + trans.getStore_ID() +
              ",0,to_date('" + ut.getSystemDate() + "','" +
              ut.DATE_FORMAT + "'),'" + DAL.getEmpPosID1()/*trans.getEmpl_ID()*/ + "')";
          System.out.println(sqlSOH);
          stmt = DAL.getConnection().createStatement();
          DAL.executeUpdate(sqlSOH,stmt);
          stmt.close();
        }else {
          flagSOH = false;
        }
        sSQL = "INSERT INTO BTM_POS_RECEIPT_ITEM ( TRANSFER_ID, RECEIPT_ID, ITEM_ID, TO_STORE_ID, FROM_STORE_ID, UNIT_PRICE, TOTAL_QTY, "+
            "  RECEIPT_DATE, BATCH_SEQ, EXP_DATE) VALUES( '" + transID + "',"+Long.parseLong(today) + ",'"  +itemID+"',"+
            trans.getStore_ID() +
            ","+Long.parseLong(cboFromStore.getSelectedData())+","+unitPrice+","+ quantity + ",to_Date('" + ut.getSystemDate() + "','yyyy-MM-dd'),0,to_date('7777-7-7','YYYY-MM-DD'))";
//        System.out.println(sSQL);
        stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        DAL.executeUpdate(sSQL,stmt);
        stmt.close();
//        sSQL = "SELECT ITEM_ID,STOCK_ON_HAND FROM BTM_POS_ITEM_LOC_SOH WHERE ITEM_ID='"+itemID+"'";
//        rs=DAL.executeResultSetUpdate(sSQL);
//        rs.next();
//        newQuantity=Integer.parseInt(rs.getBigDecimal("STOCK_ON_HAND").toString())+quantity;
//        rs.updateFloat("STOCK_ON_HAND", newQuantity);
//        rs.updateRow();
      }

    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    //BTM_POS_DETAIL_TRANS(SEQ_NO,TRANS_ID,TENDER_ID,EMP_ID,PROMO_ID,ITEM_ID,STORE_ID,CUST_ID,REGISTER_ID,AMOUNT,UNIT_QTY,TRANS_DATE,DISC_AMT,TRANS_TYPE_ID,MARKDOWN,MKDN_DISC_REASON,ORG_UNIT_PRICE)

  }
//show button in Frame Main
  void showButton() {

    frmMain.showButton(btnQuantity);
    frmMain.showButton(btnSave);
    frmMain.showButton(btnDelete);
    frmMain.showButton(btnAddItem);
    frmMain.showButton(btnPrintReceipt);
    frmMain.showButton(btnView);
    frmMain.showButton(btnBack);
  }
  boolean isExistItem(){
    if(tblReceptItem.getRowCount()>0){
      return true;
    }else{
      return false;
    }
  }

//add row
  void addItemRow(Object[] item){
    String itemId="";
    boolean isExist=false;
    int quantity=0;
    int row=0;

    for (int i = 0; i <  tblReceptItem.getRowCount(); i++) {
      itemId=tblReceptItem.getValueAt(i,RI_COL_ITEM_ID).toString();
      if(itemId.equals(item[0].toString())){
        isExist=true;
        row=i;
        break;
      }
    }
    if(!isExist){
      model.addRow(item);
    //increase quantity by 1
    }else{
      //old value
      quantity=Integer.parseInt((tblReceptItem.getValueAt(row,RI_COL_QUANTITY).toString()));
      //new value
      quantity=quantity+Integer.parseInt(item[4].toString());
      //update table
      tblReceptItem.setValueAt(String.valueOf(quantity), row, RI_COL_QUANTITY);
    }

    //set focus for last row
    if(tblReceptItem.getRowCount()>0){
      tblReceptItem.setRowSelectionInterval(tblReceptItem.getRowCount()-1, tblReceptItem.getRowCount()-1);
    }

    //show current row
    Rectangle bounds = tblReceptItem.getCellRect(tblReceptItem.getRowCount()-1,0,true);
    jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

  }
  //delete select row
  void deleteItemRow(){
    if(tblReceptItem.getSelectedRow()!=-1){
      model.removeRow(tblReceptItem.getSelectedRow());
      if(tblReceptItem.getRowCount()>0){
        tblReceptItem.setRowSelectionInterval(0, 0);
      }
    }
  }
  //delete Receipt Item
  void deleteReceiptItem(){
    while(model.getRowCount() >0){
      model.removeRow(0);
    }
  }

//Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {
    mainInput=frmMain.getInputText();
//    DataAccessLayer DAL =frmMain.getDAL();
    ResultSet rs=null;
    String sqlQuery="";
    Item item=new Item();

    if (e.getKeyCode() == KeyEvent.VK_ENTER) {


        //search item and add that item to table
        if (flagButton == RECEIPT_ITEM_ADD_BTN) {
            //check item code
            if (!ut.isLongIntInput(frmMain, mainInput)) {
              return;
            }
            //only select items in this store
            sqlQuery = "Select * from BTM_POS_ITEM_PRICE where ITEM_ID='" +
                mainInput + "' and STORE_ID=" + DAL.getStoreID();
//            System.out.println(sqlQuery);
            try {
              stmt = DAL.getConnection().createStatement();
              rs = DAL.executeQueries(sqlQuery,stmt);
              if (rs.next()) {
//                item = new Item(rs.getString("ITEM_ID"), rs.getString("ITEM_DESC"),
//                                rs.getDouble("UNIT_PRICE"), 1, 0, "", 0,
//                                rs.getDouble("UNIT_PRICE"),0);
                item = new Item(rs.getString("ITEM_ID"),
                                rs.getString("ART_NO"),
                                rs.getString("ITEM_DESC"),
                                rs.getString("ATTR2_NAME"),1,
                                rs.getDouble("UNIT_PRICE"));
                addItemRow(new Object[] {item.getId(),
                           item.getArtNo(),
                           item.getDescription(),
                           item.getattribute2(),
                           new Double( item.getQuantity()),
                           ut.formatNumber("" + item.getunitPrice())});
                tblReceptItem.setColor(Color.lightGray, Color.white);
              }
              else {
                ut.showMessage(frmMain,"Warning",  "Cannot find item " + mainInput + " in this store.");
              }
              stmt.close();
            }
            catch (Exception ex) {
              ExceptionHandle eh = new ExceptionHandle();
              eh.ouputError(ex);
            }
        }
        else if (flagButton == RECEIPT_ITEM_QUANTITY_BTN) {
          //check quantity number
            if (!ut.isIntInput(frmMain, mainInput)) {
              return;
            }
           tblReceptItem.setValueAt(String.valueOf(mainInput), tblReceptItem.getSelectedRow(), RI_COL_QUANTITY);
           flagButton=RECEIPT_ITEM_ADD_BTN;
           tblReceptItem.requestFocus(true);
        }
        frmMain.setInputText("");
        frmMain.setInputLabel(" Enter or scan item code:");
        frmMain.txtMainInput.requestFocus(true);

    }

  }

  void showData() {

    lblheader.setText("Receipt item for store "+frmMain.getTransaction().getStore_ID()+" on "+ut.getSystemDate());
  }

  boolean checkValidTransID(){
        transID = this.txtTransID.getText().trim();
          if ( (transID.length() <= 0)) {
            ut.showMessage(frmMain,"Warning",
                "The lengh of Transfer ID can not lower than 0 character");
            this.txtTransID.requestFocus(true) ;
            return false;
          }
          else if (transID.length() > 25) {
            ut.showMessage(frmMain,"Warning",
                "The length of Transfer ID can not greater than 25 character");
            this.txtTransID.requestFocus(true) ;
            return false;
          }
          return true;
  }

  //set stores' name into cbo box
  public void setCboStoreWithoutNoneValue(BJComboBox cbo, DataAccessLayer DAL) {
    ResultSet rs = null;
    Statement stmt = null;
    String sql =
        "select store_id, name from btm_pos_store";
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql, stmt);
      cbo.setData(rs);
      rs.close();
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void btnSave_actionPerformed(ActionEvent e) {
    if(!checkValidTransID()){
       return;
    }
//    if (txtFromStoreID.getText().trim().equalsIgnoreCase(DAL.getStoreID())){
//      ut.showMessage(frmMain,"Warning","The from store id can not be local store id");
//      txtTransID.requestFocus(true);
//      return;
//    }
//    if ( (txtFromStoreID.getText().equals(""))) {
//           ut.showMessage(frmMain,"Warning","Input From Store Id");
//           txtFromStoreID.requestFocus(true) ;
//           return;
//         }
    //save to DB
    saveData();
    deleteReceiptItem();
    this.txtTransID.setText("") ;
//    this.txtFromStoreID.setText("");
    transID="";
    vSOH.clear();
    frmMain.setTitle("JPOS - RECEIPT ITEM");
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showReceiptButton();
    //reset
  }

  void btnQuantity_actionPerformed(ActionEvent e) {
    if(isExistItem()){
      frmMain.setInputLabel(" Enter the new quantity for selected item.");
      flagButton = RECEIPT_ITEM_QUANTITY_BTN;
      frmMain.setEditTextInput(true);
    }
  }

  void btnDelete_actionPerformed(ActionEvent e) {
    if(isExistItem()){
      frmMain.setInputLabel("Enter or scan item code:");
      deleteItemRow();
      flagButton = RECEIPT_ITEM_ADD_BTN;
      frmMain.setEditTextInput(true);
    }
  }

  void btnAddItem_actionPerformed(ActionEvent e) {
    frmMain.pnlAddItem.setParentScreen(Constant.SCR_RECEIPT_ITEM);
    frmMain.setTitle("JPOS - ADD ITEM");
    frmMain.showScreen(Constant.SCR_ADD_ITEM);
  }

  void btnBack_actionPerformed(ActionEvent e) {
    this.txtTransID.setText("") ;
//    this.txtFromStoreID.setText("");
    removeRow();
    vSOH.clear();
    frmMain.setTitle("JPOS - RECEIPT ITEM");
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showReceiptButton();
  }
  Vector getSaleItem(){
    Vector vSaleItem=new Vector();
    Item item=  new Item();
    String itemId="";
    String artNo="";
    String desc="";
    String size="";
    int quantity = 0;
    double price = 0;


    for (int i = 0; i < tblReceptItem.getRowCount(); i++) {
          itemId=tblReceptItem.getValueAt(i,RI_COL_ITEM_ID).toString();
          artNo=tblReceptItem.getValueAt(i,RI_COL_ART_NO).toString();
          desc=tblReceptItem.getValueAt(i,RI_COL_ART_NAME).toString();
          size=tblReceptItem.getValueAt(i,RI_COL_SIZE).toString();
          quantity=ut.doubleStringToInt(tblReceptItem.getValueAt(i,RI_COL_QUANTITY).toString());
          price=ut.convertToDouble1(tblReceptItem.getValueAt(i,RI_COL_PRICE).toString());
          item=new Item(itemId,artNo,desc,size,quantity,price);
          vSaleItem.add(item);
    }
    return vSaleItem;
  }

  void btnPrintReceipt_actionPerformed(ActionEvent e) {
    Transaction trans=frmMain.getTransaction();
    trans.setTrans_Date(java.sql.Date.valueOf(ut.getSystemDate())  ) ;

    PanelPrintReciept PanPrint = new PanelPrintReciept(frmMain);
    PanPrint.setTransaction(trans);
    PanPrint.setSaleItem(getSaleItem());
    PanPrint.setTypePrint(Constant.PRINT_RECEIPT_ITEM);
//    PanelPrintPage printpage = new PanelPrintPage();
    if (e.getSource() instanceof JButton) {
         PrinterJob printJob = PrinterJob.getPrinterJob();
         PageFormat pf = new PageFormat();

             Paper paper = new Paper();

             // Set the margins.

             paper.setImageableArea(0, 0, 214, 841);

             pf.setPaper(paper);
       ////
//         printJob.validatePage(pf);
               printJob.setPrintable(PanPrint,pf);


         if (printJob.printDialog()) {
             try {
                 printJob.print();
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
         }
         }

  }

  private void registerKeyboardActions() {
   /// set up the maps:
   InputMap inputMap =getInputMap(WHEN_IN_FOCUSED_WINDOW);
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

   // F6
   key = new Integer(KeyEvent.VK_F6);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), key);
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
       if (identifier.intValue()==KeyEvent.VK_F1){
           btnQuantity.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F2){
           btnSave.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F3){
           btnDelete.doClick();
//         searchExpressItem();//TrungNT
       }else if (identifier.intValue()==KeyEvent.VK_F4){
           btnAddItem.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F5){
           btnPrintReceipt.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F6){
         btnView.doClick();
       }else if (identifier.intValue()==KeyEvent.VK_F7 || identifier.intValue()==KeyEvent.VK_ESCAPE){
           btnBack.doClick();
       }
   }
}

  private void catchHotKey(KeyEvent e) {
              if (e.getKeyCode() == KeyEvent.VK_F1) {
                btnQuantity.doClick();
              }

            }

  void tblReceptItem_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void btnView_actionPerformed(ActionEvent e) {
    frmMain.setInputLabel("Enter the transaction date (DD/MM/YYYY)");
    frmMain.setTitle("JPOS - VIEW RECEIPT ITEM");
     frmMain.showScreen(Constant.SCR_VIEW_RECEIPT);
  }

//  void searchExpressItem(){
//      DialogSearchExpressItem dlgSearchExpressItem = new DialogSearchExpressItem(frmMain,
//          "JPOS - ITEM LOOKUP", true);
//      dlgSearchExpressItem.setVisible(true);
//
//      if (dlgSearchExpressItem.isExistItem()) {
//          Item item = dlgSearchExpressItem.getItem();
//
//          //add found item to Receipt table
//          addItemRow(new Object[] {item.getId(),
//                     item.getArtNo(),
//                     item.getDescription(),
//                     item.getattribute2(),
//                     new Double(item.getQuantity()),
//                     ut.formatNumber("" + item.getunitPrice())});
//
//
//      }
//
//
//  }

  void txtArtNo_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
      txtSize.requestFocus(true);
    }

  }

  void txtSize_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
      txtQuantity.requestFocus(true);
    }

  }

  void txtQuantity_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
      String sqlQuery="";
        ResultSet rs=null;
        boolean noneSize=false;

        String artNo=txtArtNo.getText().trim();
        String size= txtSize.getText().trim();
        String qty= txtQuantity.getText().trim();

        //size="n" : no size for item
        if(size.equals("")){
          noneSize=true;
        }

        if(noneSize){
          sqlQuery = "Select * from BTM_POS_ITEM_PRICE where ART_NO='" + artNo +"' and (Attr2_name is null or Attr2_name like 'null')";
        }else{
          sqlQuery = "Select * from BTM_POS_ITEM_PRICE where ART_NO='" + artNo +"' and UPPER(ATTR2_NAME) =UPPER('" + size + "')";
        }
      try {
        if(!artNo.equals("") && !qty.equals("")){
          rs = DAL.executeQueries(sqlQuery);

          if (rs.next()) {
            //add found item to Receipt table
            addItemRow(new Object[] {rs.getString("ITEM_ID"),
                       artNo,
                       rs.getString("ITEM_DESC"),
                       size,
                       new Integer(qty),
                       ut.formatNumber( String.valueOf(rs.getDouble("UNIT_PRICE")))});
//            txtArtNo.setText("");
            txtSize.setText("");
            txtQuantity.setText("");
            txtArtNo.requestFocus(true);
            txtArtNo.selectAll();

          }else {
            ut.showMessage(frmMain, "Warning",
                           "No items found matching your Art No and Size");

          }
          rs.getStatement().close();
        }else{
          ut.showMessage(frmMain, "Warning",
                         "Art No or quantity can not null");

        }

      }catch(Exception ex){
        ex.printStackTrace();
      }


      txtArtNo.requestFocus(true);
    }

  }

  void txtArtNo_focusLost(FocusEvent e) {

  }




}

class PanelReceiptItem_btnSave_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptItem adaptee;

  PanelReceiptItem_btnSave_actionAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSave_actionPerformed(e);
  }
}

class PanelReceiptItem_btnQuantity_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptItem adaptee;

  PanelReceiptItem_btnQuantity_actionAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnQuantity_actionPerformed(e);
  }
}

class PanelReceiptItem_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptItem adaptee;

  PanelReceiptItem_btnDelete_actionAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelReceiptItem_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptItem adaptee;

  PanelReceiptItem_btnBack_actionAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelReceiptItem_btnAddItem_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptItem adaptee;

  PanelReceiptItem_btnAddItem_actionAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAddItem_actionPerformed(e);
  }
}

class PanelReceiptItem_btnPrintReceipt_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptItem adaptee;

  PanelReceiptItem_btnPrintReceipt_actionAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrintReceipt_actionPerformed(e);
  }
}

class PanelReceiptItem_tblReceptItem_keyAdapter extends java.awt.event.KeyAdapter {
  PanelReceiptItem adaptee;

  PanelReceiptItem_tblReceptItem_keyAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tblReceptItem_keyPressed(e);
  }
}

class PanelReceiptItem_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptItem adaptee;

  PanelReceiptItem_btnView_actionAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelReceiptItem_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelReceiptItem adaptee;

  PanelReceiptItem_txtArtNo_keyAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelReceiptItem_txtQuantity_keyAdapter extends java.awt.event.KeyAdapter {
  PanelReceiptItem adaptee;

  PanelReceiptItem_txtQuantity_keyAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtQuantity_keyTyped(e);
  }
}

class PanelReceiptItem_txtSize_keyAdapter extends java.awt.event.KeyAdapter {
  PanelReceiptItem adaptee;

  PanelReceiptItem_txtSize_keyAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSize_keyTyped(e);
  }
}

class PanelReceiptItem_txtArtNo_focusAdapter extends java.awt.event.FocusAdapter {
  PanelReceiptItem adaptee;

  PanelReceiptItem_txtArtNo_focusAdapter(PanelReceiptItem adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtArtNo_focusLost(e);
  }
}
