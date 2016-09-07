/**
 *
 */
package sim;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import common.*;
import java.sql.*;
import java.util.Vector;
import java.awt.event.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import btm.attr.*;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt ->Transfer -> Store to Store -> Stock Count</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @mofidy by Nghia Le
 * @version 1.0
 */
//Stock count for Back room
//Update soh and Back room
public class PanelStockCount extends JPanel {

    //Column of Stock count table
    private static final int COL_ITEM_ID = 0; //
    private static final int COL_ART_NO = 1;
    private static final int COL_ITEM_NAME = 2; //
    private static final int COL_SYSTEM_COUNT = 3; //
    private static final int COL_USER_COUNT = 4; //
    private static final int COL_DESCRIPTION = 5; //

    //DataAccessLayer DAL;
    Utils ut = new Utils(); //flag
    InitTableModel dm = new InitTableModel();
    DataSourceVector dsItems;
    int enterCount = 0;
    boolean not_exist = false;
    boolean IS_ENTER = true;
    String strBareCode = "0043000104101";
    String strStoreID="1100";

    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanelButton = new JPanel();
    JPanel jPanelInput = new JPanel();
    JPanel jPanelGirdData = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    BJTable table = new BJTable(dm){
      /*
       * Set cells are edited on grid directly.
       * editable.
       */
      public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col == COL_USER_COUNT) {
          return true;
        }
        return false;
      }
    };
    BJButton btnCancel = new BJButton();
    BJButton btnDone = new BJButton();
    BorderLayout borderLayout2 = new BorderLayout();
    Statement stmt = null;
    BJLabel jLabel4 = new BJLabel();
    BJTextField txtArtNo = new BJTextField();
    BJButton btnAdd = new BJButton();
    JPanel jPanel9 = new JPanel();
    JScrollPane jScrollPane2 = new JScrollPane();
    BJTextArea txtDesc = new BJTextArea();
    BJLabel jLabel5 = new BJLabel();
    JButton btnUPC = new JButton();
    BJLabel jLabel3 = new BJLabel();
    BJTextField txtPrdGroup = new BJTextField();
    JButton btnSearchPrdGroup = new JButton(); //first time press Enter
    CardLayout cardLayout1 = new CardLayout();
    JPanel jPanelContent = new JPanel();
    BorderLayout borderLayout4 = new BorderLayout();
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    BJButton btnDelete = new BJButton();
    BJLabel bJLabel1 = new BJLabel();

  public PanelStockCount(FrameMainSim frmMain) {
        try {
            jbInit();
            /**
             * init header
             */
            initTable();
	    setLanguage();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Generate code for Design on form
     * Programmer should not change content of this function.
     * @throws Exception
     */
    void jbInit() throws Exception {
        registerKeyboardActions();
        borderLayout1.setHgap(0);
        this.setPreferredSize(new Dimension(800, 600));
	this.setLayout(borderLayout1);
        jPanelButton.setBackground(new Color(121, 152, 198));
        jPanelButton.setPreferredSize(new Dimension(800, 50));
        jPanelInput.setLayout(borderLayout2);
        jPanelGirdData.setLayout(cardLayout1);
        btnCancel.setText("<html><center><b>Cancel</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                          "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
        btnCancel.addActionListener(new PanelStockCount_btnCancel_actionAdapter(this));
        btnDone.setText("Done" + " (F1)");
        btnDone.addActionListener(new PanelStockCount_btnDone_actionAdapter(this));
        jLabel4.setPreferredSize(new Dimension(120, 20));
        jLabel4.setText("UPC" + ":");
        txtArtNo.setFont(new java.awt.Font("Arial", 1, 12));
        txtArtNo.setPreferredSize(new Dimension(150, 21));
        txtArtNo.setText("");
        txtArtNo.addKeyListener(new PanelStockCount_txtArtNo_keyAdapter(this));
        btnAdd.setText("Add" + " (F2)");
        btnAdd.addActionListener(new PanelStockCount_btnAdd_actionAdapter(this));
        jPanel9.setBorder(null);
        jPanel9.setPreferredSize(new Dimension(770, 65));
        jPanel9.setLayout(gridBagLayout2);
        txtDesc.setBorder(null);
        txtDesc.setPreferredSize(new Dimension(200, 95));
        txtDesc.setText("");
        txtDesc.setRows(0);
        txtDesc.setWrapStyleWord(false);
        txtDesc.addKeyListener(new PanelStockCount_txtDesc_keyAdapter(this));
        jLabel5.setText("Reason" + ":");
        jLabel5.setPreferredSize(new Dimension(120, 20));
        btnUPC.setFont(new java.awt.Font("Arial", 1, 11));
        btnUPC.setPreferredSize(new Dimension(25, 22));
        btnUPC.setText("...");
        btnUPC.addActionListener(new PanelStockCount_btnUPC_actionAdapter(this));
        jLabel3.setPreferredSize(new Dimension(120, 20));
        jLabel3.setText("AddGroup" + ":");
        btnSearchPrdGroup.setFont(new java.awt.Font("Arial", 1, 11));
        btnSearchPrdGroup.setPreferredSize(new Dimension(25, 22));
        btnSearchPrdGroup.setText("...");
        btnSearchPrdGroup.addActionListener(new
                                            PanelStockCount_btnSearchPrdGroup_actionAdapter(this));
        txtPrdGroup.setEnabled(false);
        txtPrdGroup.setPreferredSize(new Dimension(150, 21));
        txtPrdGroup.setEditable(true);
        txtPrdGroup.setText("");
        jPanelContent.setLayout(borderLayout4);
        jPanel2.setBorder(null);
        jPanel2.setPreferredSize(new Dimension(770, 30));
        jPanel2.setLayout(gridBagLayout1);
        jPanel1.setPreferredSize(new Dimension(800, 110));
        btnDelete.setText("<html><center><b>Delete </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                          "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8<" +
                          "/html>");
        btnDelete.addActionListener(new PanelStockCount_btnDelete_actionAdapter(this));
        table.addMouseListener(new PanelStockCount_table_mouseAdapter(this));
        table.addKeyListener(new PanelStockCount_table_keyAdapter(this));
        bJLabel1.setText("bJLabel1");
        jPanelButton.add(btnDone, null);
        jPanelButton.add(btnAdd, null);
        jPanelButton.add(btnDelete);
        jPanelButton.add(btnCancel, null);
        jPanelInput.add(jPanel1, java.awt.BorderLayout.NORTH);
        jPanel1.add(jPanel2);
        jPanelContent.add(jPanelGirdData, java.awt.BorderLayout.CENTER);
        jPanelGirdData.add(jScrollPane1, "jScrollPane1");
        this.add(jPanelContent, java.awt.BorderLayout.CENTER);
        this.add(jPanelButton, java.awt.BorderLayout.NORTH);
        jPanelContent.add(jPanelInput, java.awt.BorderLayout.NORTH);
        jPanelInput.add(jPanel1, java.awt.BorderLayout.WEST);
        jScrollPane1.getViewport().add(table, null);
        jPanel1.add(jPanel9);

        jPanel9.add(jScrollPane2, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(6, 1, 4, 54), 500, 28));
        jPanel9.add(jLabel5, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                    , GridBagConstraints.WEST,
                                                    GridBagConstraints.NONE,
                                                    new Insets(10, 60, 34, 3), 82,
                                                    -1));
        jScrollPane2.getViewport().add(txtDesc, null);
        jPanel2.add(btnSearchPrdGroup, new GridBagConstraints(3, 1, 1, 3, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));
        jPanel2.add(txtArtNo, new GridBagConstraints(5, 1, 1, 2, 0.0, 0.0
                                                     , GridBagConstraints.CENTER,
                                                     GridBagConstraints.NONE,
                                                     new Insets(0, 0, 0, 0), 0, 0));
        jPanel2.add(btnUPC, new GridBagConstraints(6, 1, 1, 3, 0.0, 0.0
                                                   , GridBagConstraints.CENTER,
                                                   GridBagConstraints.NONE,
                                                   new Insets(0, 0, 0, 0), 0, 0));
    jPanel2.add(jLabel3, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
                                                    , GridBagConstraints.SOUTHEAST,
                                                    GridBagConstraints.NONE,
                                                    new Insets(0, 0, 0, 0), 12, -2));
        jPanel2.add(jLabel4, new GridBagConstraints(4, 1, 1, 3, 0.0, 0.0
                                                    , GridBagConstraints.CENTER,
                                                    GridBagConstraints.NONE,
                                                    new Insets(0, 19, 0, 4), -8, 1));
    jPanel2.add(txtPrdGroup, new GridBagConstraints(2, 1, 1, 2, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 1, 0, 2), 30, 0));
    jScrollPane2.getViewport();
    }

    /**
     * Properties
     */
    public void setStoreID(String storeID){ strStoreID = storeID;}

    public void applyPermission() {
       EmpRight er = new EmpRight();
       er.initData(SystemClass.objDAL, SystemClass.objDAL.getEmpID());
       er.setData(SystemClass.objDAL.getEmpID(), Constant.SCRS_STOCK_COUNT);
       btnAdd.setEnabled(er.getAdd());
       btnDone.setEnabled(er.getAdd());
       if(!er.getAdd()){
	 ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT002") ,  SystemClass.lang.getString("MS317_YouPermission"));

       }
    }

    /**
     * Init data for table
     */
    private void initTable() {

      //define for table
          String[] columnNames = new String[] {
              SystemClass.lang.getString("ItemID"),
              SystemClass.lang.getString("UPC"),
              SystemClass.lang.getString("ItemName"),
              SystemClass.lang.getString("SystemCount"),
              SystemClass.lang.getString("UserCount"),
              SystemClass.lang.getString("Reason")
         };
       dm.setHeader(columnNames);
       setColumnsWith();
    }

    /**
     * add new row to grid
     */
    private void getAndInsertItem(){
      if(checkDupplicateBarcode()== -1){
        String strSQL = "";
        strSQL = "";
        strSQL = "Select im.item_id, im.art_no, im.item_name, ";
        strSQL += " BACK_ROOM_QTY, BACK_ROOM_QTY user_count, ' ' Reason";
        strSQL += " from btm_im_item_loc_soh ils, btm_im_item_master im ";
        strSQL += " where im.status <> 'D' and ils.item_id = im.item_id ";
        strSQL += " and store_id = '" + SystemClass.storeID + "'";
        strSQL += " and im.art_no = '" + strBareCode + "'";
        strSQL += " order by im.art_no, im.item_id ";

        try {
          if(dsItems == null){
            String SQLtemp = "";
            SQLtemp = "Select im.item_id, im.art_no, im.item_name, ";
            SQLtemp += " BACK_ROOM_QTY, BACK_ROOM_QTY user_count, ' ' Reason";
            SQLtemp += " from btm_im_item_loc_soh ils, btm_im_item_master im ";
            SQLtemp += " where ils.item_id = im.item_id and 1=0";

            dsItems = new DataSourceVector(SystemClass.objDAL.getConnection(),
                                           SQLtemp);
	    //aller
	    dsItems.setColumnsAreNum(new int[]{COL_USER_COUNT});
          }
          ResultSet rsItem = null;
          Statement stmt = SystemClass.objDAL.getConnection().createStatement(
              ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
          rsItem = stmt.executeQuery(strSQL);
	  int dem = 0;
          if (rsItem != null) {
            rsItem.beforeFirst();
            while (rsItem.next()) {
              Vector cols = new Vector();
              for (int col = 1; col <= rsItem.getMetaData().getColumnCount();
                   col++) {
                cols.add(rsItem.getObject(col));
              }
              cols.setElementAt(new String(txtDesc.getText().trim()), COL_DESCRIPTION);
              dsItems.addNewRow(cols);
	      dem++;
            }
          }
	  if(dem == 0){
            ut.showMessage(SystemClass.frmMainSim,
                           SystemClass.lang.getString("TT001"),
                           SystemClass.lang.getString("MS319_ItemNotExist"));
          }
          stmt.close();
        dm.setDataSourceVector(dsItems);
        setColumnsWith();

        }
        catch (Exception ex) {
          ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                         ex.getMessage());
        }
      }else{
        ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),  SystemClass.lang.getString("MS319_ItemNotExist"));
      }
      resetTextAll();
    }

    /**
     * Set width of column
     */
    private void setColumnsWith(){
      table.setColumnWidth(COL_ITEM_ID, 90);
      table.setColumnWidth(COL_ART_NO, 90);
      table.setColumnWidth(COL_ITEM_NAME, 150);
      table.setColumnWidth(COL_SYSTEM_COUNT, 70);
      table.setColumnWidth(COL_USER_COUNT, 70);
      table.setColumnWidth(COL_DESCRIPTION, 60);
      table.setColor(Color.lightGray, Color.white);
      table.setCellEditable(COL_USER_COUNT);

    }

    /**
     * update reason to grid
     * @param rowIndex int
     */
    private void updateReasonStockCount(int rowIndex){
      dsItems.setValueAt(new String(txtDesc.getText().trim()), rowIndex, COL_DESCRIPTION);
      dm.setDataSourceVector(dsItems);
      resetTextAll();
      setColumnsWith();
    }

    /**
     * Check duppliacte item
     * @return boolean
     */
    private int checkDupplicateBarcode(){
      int index=-1;
      try{
        if (dsItems != null) {
          int sizeofData = dsItems.getNumberOfRows();
          for (int row = 0; row < sizeofData; row++) {
            if (dsItems.getValueAt(row,
                COL_ART_NO).toString().equals(new String(strBareCode))) {
              return row;
            }
          }
        }
      }catch(Exception ex){
        ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"), ex.getMessage());
      }
      return index;
    }

    /**
     * find item in aray
     * @param barcode String
     * @return int
     */
    private int findIndex(String barcode) {
      int index = -1;
      try {
        if (dsItems != null) {
          int sizeofData = dsItems.getNumberOfRows();
          for (int row = 0; row < sizeofData; row++) {
            if (dsItems.getValueAt(row,
                                   COL_ART_NO).toString().equals(new String(
                                       barcode))) {
              return row;
            }
          }
        }
      }
      catch (Exception ex) {
        ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                       ex.getMessage());
      }
      return index;
    }


    void resetTextAll(){
      txtDesc.setText("");
      txtPrdGroup.setText("");
      txtDesc.setText("");
      txtArtNo.setText("");
    }

    void resetData() {
      txtArtNo.setText("");
      txtPrdGroup.setText("");
      this.enterCount = 0;
      if(dsItems!= null){
        dsItems.getData().clear();
        dm.setDataSourceVector(dsItems);
      }
    }

    /**
     * Set data
     * @param vUPC Vector
     */
    void setData(Vector vUPC) {
      for (int i = 0; i < vUPC.size(); i++) {
          strBareCode = vUPC.get(i).toString().trim();
          getAndInsertItem();
          resetTextAll();
      }
    }

    /**
     * Set language
     */
    void setLanguage(){
      btnCancel.setText("<html><center><b>"+SystemClass.lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                        "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
      btnDone.setText(SystemClass.lang.getString("Done") + " (F1)");
      btnDelete.setText("<html><center><b>"+SystemClass.lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
			  "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
      btnAdd.setText(SystemClass.lang.getString("Add") + " (F2)");
      jLabel4.setText(SystemClass.lang.getString("UPC") + ":");
      jLabel5.setText(SystemClass.lang.getString("Reason") + ":");
      jLabel3.setText(SystemClass.lang.getString("AddGroup") + ":");
    }


  /*Events process*/


  public void btnUPC_actionPerformed(ActionEvent e) {
      btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
      DialogItemLookup dlgItemLookup = new DialogItemLookup(SystemClass.frmMainSim,
          SystemClass.lang.getString("TT0005"), true, SystemClass.frmMainSim, itemBusObject);
      dlgItemLookup.cboStatus.setSelectedItem("Approve");
      dlgItemLookup.cboStatus.setEnabled(false);
      dlgItemLookup.setVisible(true);
      if (dlgItemLookup.done) {
        if (dlgItemLookup.getUPCList().isEmpty()) {
          ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.
                         getString("TT001"), SystemClass.lang.
                         getString("MS321_ItemNotSelect"));
          return;
        }
        setData(dlgItemLookup.getUPCList());
        txtArtNo.setText("");
      }
    }

  public void btnCancel_actionPerformed(ActionEvent e) {
    resetData();
    SystemClass.frmMainSim.showScreen(Constant.SCRS_TRANSFER);
  }

  public void btnDone_actionPerformed(ActionEvent e) {
    if(dsItems!=null && dsItems.getNumberOfRows()>0){
      DataSourceVector vtItemInsert = null;
      vtItemInsert = dsItems.getInsertRows();
      ut.writeToTextFile("file\\error.txt", "Hist STB  " + ut.getSystemDateTime() + "\r\n");

      String today = "";
      today = ut.getSystemDateTime();
      today = today.substring(0, 2) + today.substring(3, 5) +  today.substring(8, 10) + today.substring(11, 13) + today.substring(14, 16) + today.substring(17);
      String stkID = SystemClass.storeID + SystemClass.objDAL.getHostID() + today;
      Vector vSql = new Vector();
      try{
        for (int i = 0; i < vtItemInsert.getNumberOfRows(); i++) {
          if (vtItemInsert.getValueAt(i, COL_USER_COUNT).toString().trim() !=
              "") {
            //double systemCount = ut.convertToDouble(((Vector)vtItemInsert.elementAt(i)).elementAt(COL_SYSTEM_COUNT).toString());
            double systemCount = ut.convertToDouble(vtItemInsert.getValueAt(i,
                COL_SYSTEM_COUNT).toString());
            double userCount = ut.convertToDouble(vtItemInsert.getValueAt(i,
                COL_USER_COUNT).toString());
            double variantQty = userCount - systemCount;
            String sDesc = vtItemInsert.getValueAt(i, COL_DESCRIPTION).toString();
            String sql = "";
            sql += " INSERT INTO BTM_IM_STOCK_COUNT ( STK_ID, STORE_ID, ITEM_ID, STK_COUNTED, STK_COUNTER, STK_DATE, VARIANCE_QTY, DESCRIPTION ) ";
            sql += " VALUES('" + stkID + "', ";
            sql += Long.parseLong(SystemClass.storeID) + ",'";
            sql += vtItemInsert.getValueAt(i,COL_ITEM_ID).
                toString() + "', ";
            sql += userCount + ",' ";
            sql += SystemClass.objDAL.getEmpCode() + "',to_date('" +
                ut.getSystemDateTime1() + "',' ";
            sql += ut.DATETIME_FORMAT2 + "'), ";
            sql += variantQty + ",'" + sDesc + "')";
            vSql.addElement(sql);
            sql = "update btm_im_item_loc_soh set BACK_ROOM_QTY =  " +
                userCount + ", STOCK_ON_HAND = " + userCount +
                " + FRONT_ROOM_QTY,soh_update_datetime = to_date('" +
                ut.getSystemDate() + "','" + ut.DATE_FORMAT + "')" +
                " where item_id = '" +
                vtItemInsert.getValueAt(i,COL_ITEM_ID).
                toString() + "' and store_id = " +
                Long.parseLong(SystemClass.storeID);
            vSql.addElement(sql);
          }
        }
      }catch(Exception ex){
        ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"), ex.getMessage());
      }
      if (!vSql.isEmpty()) {

        SystemClass.objDAL.setBeginTrans(SystemClass.objDAL.getConnection());
        SystemClass.objDAL.executeBatchQuery(vSql);
        SystemClass.objDAL.setEndTrans(SystemClass.objDAL.getConnection());
      }
      resetData();
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

  public void btnAdd_actionPerformed(ActionEvent e) {
    strBareCode = txtArtNo.getText();
    getAndInsertItem();
  }

  public void btnSearchPrdGroup_actionPerformed(ActionEvent e) {
    btm.business.ProdGroupBusObj prdGrpBusObject = new btm.business.
        ProdGroupBusObj();
    DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(
        SystemClass.frmMainSim, SystemClass.lang.getString("TT0006"),
        true, SystemClass.frmMainSim, prdGrpBusObject);
    dlgProdGroupSearch.txtSupplier.setEnabled(false);
    dlgProdGroupSearch.btnSupplier.setEnabled(false);
    dlgProdGroupSearch.cboType.setSelectedItem(SystemClass.lang.getString("StockCount"));
    SystemClass.frmMainSim.pnlProdGroupDetail.SCREEN_FLAG = SystemClass.frmMainSim.pnlProdGroupDetail.
        STOCK_COUNT_BR;
    dlgProdGroupSearch.setVisible(true);
    if (dlgProdGroupSearch.done) {
      if (dlgProdGroupSearch.getPGID().equals("")) {
        ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),
                       SystemClass.lang.getString("MS321_ItemNotSelect"));
        return;
      }
      SystemClass.frmMainSim.pnlProdGroupDetail.setDataDetail(dlgProdGroupSearch.getPGID());
      SystemClass.frmMainSim.showScreen(Constant.SCRS_PROD_GROUP_DETAIL);
    }
    else {
      SystemClass.frmMainSim.showScreen(Constant.SCRS_STOCK_COUNT);
    }
  }

  public void btnDelete_actionPerformed(ActionEvent e) {
    if(table.getSelectedRow()>-1){
      int indexOf = table.getSelectedRow();
      int indexOfData = -1;
      indexOfData = findIndex(table.getValueAt(indexOf, COL_ART_NO).toString());
      if(indexOfData>-1){
        dsItems.removeRowAt(indexOfData);
        dm.setDataSourceVector(dsItems);
      }else{
        ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"), SystemClass.lang.getString("MS2022_SelectItemOnGrid"));
      }
    }else{
      ut.showMessage(SystemClass.frmMainSim, SystemClass.lang.getString("TT001"),  SystemClass.lang.getString("MS2022_SelectItemOnGrid"));
    }

  }

  public void table_mouseClicked(MouseEvent e) {
    if(dsItems != null){
      txtArtNo.setText("" +  table.getValueAt(table.getSelectedRow(), COL_ART_NO).toString());
      txtDesc.setText("" +  table.getValueAt(table.getSelectedRow(), COL_DESCRIPTION).toString());
    }
  }

  public void table_keyReleased(KeyEvent e) {
    if(e.getKeyCode() == e.VK_DOWN || e.getKeyCode() == e.VK_UP){
      table_mouseClicked(null);
    }
  }

  public void txtArtNo_keyReleased(KeyEvent e) {
    strBareCode = txtArtNo.getText();
    if (e.getKeyCode() == e.VK_ENTER) {
      getAndInsertItem();
    }else{
      if (txtArtNo.getText().trim().length() > 0) {
        int indexof = -1;
        indexof = checkDupplicateBarcode();
        if (indexof > -1) {
          txtDesc.setText(((Vector)dsItems.getData().elementAt(indexof)).
                          elementAt(COL_DESCRIPTION).toString());
        }
      }
    }
  }

  public void txtDesc_keyReleased(KeyEvent e) {
    strBareCode = txtArtNo.getText();
    if(e.getKeyCode() == e.VK_ENTER){
      int  indexof = checkDupplicateBarcode();
      if(indexof>-1){
        updateReasonStockCount(indexof);
      }else{
        getAndInsertItem();
      }
      resetTextAll();
    }
  }


  public void txtArtNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtArtNo, ItemMaster.LEN_ITEM_ARTNO);
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
      if (!txtArtNo.getText().equalsIgnoreCase("")) {
        if (txtArtNo.getText().trim().length() < ItemMaster.LEN_ITEM_ARTNO) {
          ut.showMessage(SystemClass.frmMainSim,
                         SystemClass.lang.getString("TT001"),
                         SystemClass.lang.getString("MS318_UPCLengthShould13"));
          txtArtNo.requestFocus(true);
          txtArtNo.selectAll();
          return;
        }
      }
    }
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
      else if (identifier.intValue() == KeyEvent.VK_F8) {
        btnDelete.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }
}

class PanelStockCount_table_keyAdapter
    extends KeyAdapter {
  private PanelStockCount adaptee;
  PanelStockCount_table_keyAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void keyReleased(KeyEvent e) {
    adaptee.table_keyReleased(e);
  }

  public void keyPressed(KeyEvent e) {
  }

  public void keyTyped(KeyEvent e) {
  }
}

class PanelStockCount_table_mouseAdapter
    extends MouseAdapter {
  private PanelStockCount adaptee;
  PanelStockCount_table_mouseAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelStockCount_txtDesc_keyAdapter
    extends KeyAdapter {
  private PanelStockCount adaptee;
  PanelStockCount_txtDesc_keyAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void keyReleased(KeyEvent e) {
    adaptee.txtDesc_keyReleased(e);
  }
}

class PanelStockCount_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelStockCount adaptee;
  PanelStockCount_btnDelete_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelStockCount_btnAdd_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelStockCount adaptee;
  PanelStockCount_btnAdd_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelStockCount_btnDone_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelStockCount adaptee;
  PanelStockCount_btnDone_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelStockCount_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelStockCount adaptee;
  PanelStockCount_btnCancel_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelStockCount_txtArtNo_keyAdapter
    extends KeyAdapter {
  private PanelStockCount adaptee;
  PanelStockCount_txtArtNo_keyAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
  }

  public void keyReleased(KeyEvent e) {
    adaptee.txtArtNo_keyReleased(e);
  }

  public void keyTyped(KeyEvent keyEvent) {
    adaptee.txtArtNo_keyTyped(keyEvent);
  }
}

class PanelStockCount_btnUPC_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelStockCount adaptee;
  PanelStockCount_btnUPC_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnUPC_actionPerformed(e);
  }
}

class PanelStockCount_btnSearchPrdGroup_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelStockCount adaptee;
  PanelStockCount_btnSearchPrdGroup_actionAdapter(PanelStockCount adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchPrdGroup_actionPerformed(e);
  }
}
