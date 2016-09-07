package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import java.awt.event.*;
import btm.business.*;
import java.sql.*;
import btm.attr.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Season Setup -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Vinh.Le
 * @version 1.0
 */

public class DialogItemSeasonSearch extends JDialog {
  DataAccessLayer DAL;
  Utils ut = new Utils();
  Statement stmt = null;
  BJPanel pnlButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BorderLayout borderLayout2 = new BorderLayout();
  BJButton btnCancel = new BJButton();
  FlowLayout flowLayout1 = new FlowLayout();
  BJButton btnSearch = new BJButton();
  JPanel pnlTable = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JScrollPane scrPanelTable = new JScrollPane();
  FrameMainSim frmMain;
  int parentScreen=0;
  String arr_Seq[];
  ItemSeasonBusObj iSeasonBusObj = new ItemSeasonBusObj();
  SeasonBusObj bSeasonBusObj = new SeasonBusObj();
  PhasesBusObject bPhasesBusObject = new PhasesBusObject();
  BJCheckBox check = new BJCheckBox();
  ResourceBundle lang=DataAccessLayer .getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

   int rowsNum = 5;
   public boolean done = false;
  SortableTableModel dm = new SortableTableModel(){
  public Class getColumnClass(int col){
    switch(col){
      default: return Object.class;
    }
  }
};
  BJTable tblItemSeasonSearch = new BJTable(dm,true);
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel pnlLeftInfo = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel pnlLabel = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JLabel lblApplied = new JLabel();
  JPanel pnlControl = new JPanel();
  JLabel lblItemName = new JLabel();
  JLabel lblItemID = new JLabel();
  BorderLayout borderLayout5 = new BorderLayout();
  BJComboBox cboSeason = new BJComboBox();
  JTextField txtItemName = new JTextField();
  JPanel jPanel4 = new JPanel();
  JTextField txtItemID = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField txtRowLimit = new JTextField();
  JTextField txtArtNo = new JTextField();
  JLabel lblItemID1 = new JLabel();
//  BJButton btnDelete = new BJButton();

  public DialogItemSeasonSearch(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  public DialogItemSeasonSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain, ItemSeasonBusObj iSeasonBusObj) {
     super(frame, title, modal);
     try {
       this.frmMain = frmMain;
       DAL = frmMain.getDAL();
       jbInit();
     }
     catch(Exception ex) {
       ex.printStackTrace();
     }
   }


  public DialogItemSeasonSearch() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    pnlButton.setLayout(flowLayout1);
    this.setSize(new Dimension(800, 600));
    this.addWindowListener(new DialogItemSeasonSearch_this_windowAdapter(this));
    this.addKeyListener(new DialogItemSeasonSearch_this_keyAdapter(this));
    this.getContentPane().setLayout(borderLayout2);
    pnlButton.setPreferredSize(new Dimension(800, 50));
    //scrPanelTable.setFont(new java.awt.Font(lang.getString("FontName_Label"),1,11));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new DialogItemSeasonSearch_btnCancel_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    btnSearch.setToolTipText("");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3<" +
    "/html>");
    btnSearch.addActionListener(new DialogItemSeasonSearch_btnSearch_actionAdapter(this));
    pnlTable.setLayout(gridLayout1);
    jPanel1.setLayout(borderLayout3);
    jPanel1.setMaximumSize(new Dimension(2147483647, 2147483647));
    jPanel1.setPreferredSize(new Dimension(10, 50));
    pnlTable.setPreferredSize(new Dimension(800, 390));
    jPanel2.setPreferredSize(new Dimension(20, 50));
    lblItemName.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 11));
    lblItemName.setOpaque(false);
    pnlLabel.setPreferredSize(new Dimension(150, 50));
    pnlLeftInfo.setLayout(borderLayout4);
    pnlLeftInfo.setPreferredSize(new Dimension(150, 50));
    lblApplied.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblApplied.setPreferredSize(new Dimension(110, 21));
    lblApplied.setText(lang.getString("AppliedToSeason")+":");
    lblItemName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemName.setPreferredSize(new Dimension(110, 21));
    lblItemName.setText(lang.getString("ItemName")+":");
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setPreferredSize(new Dimension(110, 21));
    lblItemID.setText(lang.getString("ItemID")+":");
    pnlControl.setLayout(borderLayout5);
    cboSeason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    cboSeason.setPreferredSize(new Dimension(180, 21));
    cboSeason.addKeyListener(new DialogItemSeasonSearch_cboSeason_keyAdapter(this));
    cboSeason.addActionListener(new DialogItemSeasonSearch_cboSeason_actionAdapter(this));
    txtItemName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtItemName.setPreferredSize(new Dimension(180, 21));
    txtItemName.setText("");
    txtItemName.addKeyListener(new DialogItemSeasonSearch_txtItemName_keyAdapter(this));
    jPanel4.setLayout(flowLayout2);
    jPanel4.setPreferredSize(new Dimension(200, 31));
    txtItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtItemID.setPreferredSize(new Dimension(180, 21));
    txtItemID.addKeyListener(new DialogItemSeasonSearch_txtItemID_keyAdapter(this));
   //  txtItemID.setText("");
    flowLayout2.setAlignment(FlowLayout.LEFT);
    scrPanelTable.setPreferredSize(new Dimension(454, 350));
    scrPanelTable.addMouseListener(new DialogItemSeasonSearch_scrPanelTable_mouseAdapter(this));
    jPanel3.setLayout(borderLayout1);
    jPanel3.setPreferredSize(new Dimension(150, 50));
    jPanel3.setOpaque(true);
    jPanel3.setPreferredSize(new Dimension(100, 10));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(110, 21));
    jLabel1.setText(lang.getString("RowsLimit")+":");
    txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRowLimit.setPreferredSize(new Dimension(40, 21));
    txtRowLimit.setText(DAL.getSearchLimit());
    txtRowLimit.addKeyListener(new DialogItemSeasonSearch_txtRowLimit_keyAdapter(this));
    tblItemSeasonSearch.addMouseListener(new DialogItemSeasonSearch_tblItemSeasonSearch_mouseAdapter(this));
    tblItemSeasonSearch.addKeyListener(new DialogItemSeasonSearch_tblItemSeasonSearch_keyAdapter(this));
    txtArtNo.setPreferredSize(new Dimension(180, 21));
    txtArtNo.addKeyListener(new DialogItemSeasonSearch_txtArtNo_keyAdapter(this));
    txtArtNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    lblItemID1.setText(lang.getString("UPC")+":");
    lblItemID1.setPreferredSize(new Dimension(110, 21));
    lblItemID1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnDelete.setText("<html><center><b>Delete </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
//    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2<" +
//    "/html>");
//    btnDelete.addActionListener(new DialogItemSeasonSearch_btnSearch1_actionAdapter(this));
//    btnDelete.setToolTipText("");
//    btnDelete.addActionListener(new DialogItemSeasonSearch_btnSearch1_actionAdapter(this));
    getContentPane().add(pnlButton,  BorderLayout.NORTH);
    pnlButton.add(btnSearch, null);
    pnlButton.add(btnCancel, null);
    this.getContentPane().add(pnlTable,  BorderLayout.SOUTH);
    pnlTable.add(scrPanelTable, null);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(pnlLeftInfo,  BorderLayout.WEST);
    pnlLeftInfo.add(pnlLabel, BorderLayout.CENTER);
    pnlLabel.add(lblItemID1, null);
    pnlLabel.add(lblItemID, null);
    pnlLabel.add(lblItemName, null);
    pnlLabel.add(lblApplied, null);
    pnlLabel.add(jLabel1, null);
    pnlLeftInfo.add(jPanel2,  BorderLayout.WEST);
    jPanel1.add(pnlControl,  BorderLayout.CENTER);
    pnlControl.add(jPanel4,  BorderLayout.WEST);
    jPanel4.add(txtArtNo, null);
    jPanel4.add(txtItemID, null);
    jPanel4.add(txtItemName, null);
    jPanel4.add(cboSeason, null);
    jPanel4.add(txtRowLimit, null);
    pnlControl.add(jPanel3,  BorderLayout.EAST);
    scrPanelTable.getViewport().add(tblItemSeasonSearch, null);
    /************ SCREEN INIT ************************/
    createConnection();
    this.cboSeason.setData1(bSeasonBusObj.getDataIntoCombo() ) ;
    this.cboSeason.setSelectedIndex(0) ;
  }

//  private void refreshPhaseCombo() {
//    if (cboSeason.getSelectedIndex() > 0)
//      try {
//        stmt = DAL.getConnection().createStatement(ResultSet.
//            TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        this.cboPhase.setData1(this.bPhasesBusObject.getDataIntoCombo( (String)this.
//            cboSeason.getSelectedData(), stmt));
//        stmt.close();
//      }
//      catch (Exception ex) {}
//    else {
//      cboPhase.removeAllItems();
//      cboPhase.addItem("None");
//    }
//  }

  private void createConnection() {
    this.iSeasonBusObj.setDataAccessLayer(DAL);
    this.bSeasonBusObj.setDataAccessLayer(DAL);
    this.bPhasesBusObject.setDataAccessLayer(DAL);
  }



  void btnSearch_actionPerformed(ActionEvent e) {

    if (txtRowLimit.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS031_RowsIsNum"));
      return;
    }

    rowsNum = Integer.parseInt(txtRowLimit.getText());
//    if (rowsNum > 50){
//      ut.showMessage(frmMain,"Warning!", "The number of rows is too long! it must be less than or equal 50");
//      return;
//    }
    frmMain.pnlItemSeasonSetup.rowNum = rowsNum;
    searchData(rowsNum);

  }
  void searchData(int rowsNum) {
    String phaseID="";
    String seasonID="";
    if (cboSeason.getSelectedItem().toString() != "None") {
      seasonID = cboSeason.getSelectedData();
    }
    dm.resetIndexes();
    try{
       stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    }catch(Exception ex){}
    tblItemSeasonSearch.setData(iSeasonBusObj.getData(txtItemID.getText(),
       ut.putCommaToString(txtItemName.getText()),seasonID,rowsNum,txtArtNo.getText(), DAL,stmt));
    ResultSet rs = iSeasonBusObj.getDataSEQ(txtItemID.getText(),
       ut.putCommaToString(txtItemName.getText()),seasonID,rowsNum,DAL,stmt);
    try{
      rs.last();
      arr_Seq = new String[rs.getRow()];
      int i=0;
      rs.beforeFirst();
      while (rs.next()) {
        arr_Seq[i] = String.valueOf(rs.getLong("Seq_No"));
        i++;
      }
      stmt.close();
      rs.close();
    }
    catch(Exception ex){}

    tblItemSeasonSearch.setAutoResizeMode(tblItemSeasonSearch.AUTO_RESIZE_ALL_COLUMNS);
    tblItemSeasonSearch.getTableHeader().setReorderingAllowed(false);
    tblItemSeasonSearch.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 13));
//    tblItemSeasonSearch.setColumnWidth(0,10);
//    tblItemSeasonSearch.setColumnWidth(1,70);
//    tblItemSeasonSearch.setColumnWidth(3,70);
//    tblItemSeasonSearch.setColumnWidth(4,70);
//    tblItemSeasonSearch.setColumnWidth(5,70);
    tblItemSeasonSearch.setHeaderName(lang.getString("ItemID"), 0);
    tblItemSeasonSearch.setHeaderName(lang.getString("ItemName"), 1);
    tblItemSeasonSearch.setHeaderName(lang.getString("Season"), 2);
    tblItemSeasonSearch.setHeaderName(lang.getString("UPC"),3);
    tblItemSeasonSearch.setHeaderName(lang.getString("Size"),4);
    tblItemSeasonSearch.setHeader(new java.awt.Font(lang.getString("FontName_Text"), 1, 15));
  }
  public void setDataVector(Vector vData){
    Vector vColumns = new Vector();
    vColumns.add("Item ID");
    vColumns.add("Item Name");
    vColumns.add("Season");
    dm.setDataVector(vData, vColumns);
    tblItemSeasonSearch.setAutoResizeMode(tblItemSeasonSearch.
                                          AUTO_RESIZE_ALL_COLUMNS);
    tblItemSeasonSearch.getTableHeader().setReorderingAllowed(false);
    tblItemSeasonSearch.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Text"), 1,
        13));
    tblItemSeasonSearch.setHeader(new java.awt.Font(lang.getString("FontName_Text"), 1, 13));
    tblItemSeasonSearch.setColor(Color.lightGray, Color.white);
   }

  void cboSeason_actionPerformed(ActionEvent e) {
//    refreshPhaseCombo();
  }


  void tblItemSeasonSearch_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      String itID = tblItemSeasonSearch.getValueAt(tblItemSeasonSearch.getSelectedRow(),0).toString();
      String seasonID = tblItemSeasonSearch.getValueAt(tblItemSeasonSearch.getSelectedRow(),2).toString();
    //  String phaseID = tblItemSeasonSearch.getValueAt(tblItemSeasonSearch.getSelectedRow(),3).toString();
      int rowSelected = tblItemSeasonSearch.getSelectedRow();
      frmMain.SeqNo =arr_Seq[rowSelected];
      //save info search
      frmMain.iSeasonBusObj.setData(txtItemID.getText(),txtItemName.getText(),cboSeason.getSelectedItem().toString(),Integer.parseInt(txtRowLimit.getText()), dm.getDataVector(),arr_Seq,rowSelected);

      frmMain.showItemSeasonModify(itID,seasonID,txtArtNo.getText(),txtItemID.getText(),txtItemName.getText(),cboSeason.getSelectedIndex());
      frmMain.showScreen(Constant.SCRS_ITEM_SEASON_MODIFY);

      this.dispose();
    }
  }
  void updateItemSeason(String itemID,String seasonID){
    cboSeason.setSelectedItem(seasonID);
//    updateModifyOnTable(frmMain.iSeasonBusObj.rowSelected);
    String last_upd_id = DAL.getEmpID();
    iSeasonBusObj.updateItemSeason(itemID,frmMain.SeqNo,cboSeason.getSelectedData(),last_upd_id);
    txtItemID.setText(frmMain.iSeasonBusObj.item_ID);
    txtItemName.setText(frmMain.iSeasonBusObj.item_Name);
    cboSeason.setSelectedItem(frmMain.iSeasonBusObj.season_Name);
//    cboPhase.setSelectedItem(frmMain.iSeasonBusObj.phase_Name);
    txtRowLimit.setText(String.valueOf(frmMain.iSeasonBusObj.rowlimit));
    arr_Seq = frmMain.iSeasonBusObj.arrSEQ;
    searchData(frmMain.iSeasonBusObj.rowlimit);
  }
  void updateModifyOnTable(int row){
    tblItemSeasonSearch.setValueAt( cboSeason.getSelectedItem(), row, 2);
  }
  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_ITEM_SEASON_SETUP);
    frmMain.pnlItemSeasonSetup.initScreen();
    frmMain.pnlItemSeasonSetup.initTable();
    this.dispose();
  }

  void txtItemID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtItemID,ItemMaster.LEN_ITEM_ID);
          if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
              (e.getKeyChar() != e.VK_BACK_SPACE))
            e.consume();
        }

  void txtItemName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtItemName,ItemMaster.LEN_ITEM_NAME);
  }

  void scrPanelTable_mouseClicked(MouseEvent e) {

  }
  private void registerKeyboardActions() {
//    pnlButton.add(btnDelete, null);
                /// set up the maps:

                InputMap inputMap = new InputMap();
                inputMap.setParent(pnlButton.getInputMap(JComponent.
                                                       WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
                ActionMap actionMap = pnlButton.getActionMap();

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

                pnlButton.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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

                  if (identifier.intValue() == KeyEvent.VK_F3 || identifier.intValue() == KeyEvent.VK_ENTER) {
                    btnSearch.doClick();
                  }else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE ) {
//                    btnDelete.doClick();
                    btnCancel.doClick();
                  }
//                  else if (identifier.intValue() == KeyEvent.VK_F3 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
//                    btnCancel.doClick();
//                  }
                }
        }
       private void catchHotKey(KeyEvent e) {
       if (e.getKeyCode() == KeyEvent.VK_F3) {
         btnSearch.doClick();
       } else if (e.getKeyCode() == KeyEvent.VK_F12) {
         btnCancel.doClick();
       }
       else if (e.getKeyCode() == KeyEvent.VK_F12|
                e.getKeyCode() == KeyEvent.VK_ESCAPE) {
         btnCancel.doClick();
       }

     }
     void txtRowLimit_keyTyped(KeyEvent e) {
          ut.limitLenjTextField(e, txtRowLimit, 2);
          if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
              (e.getKeyChar() != e.VK_BACK_SPACE))
            e.consume();
          }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void tblItemSeasonSearch_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtRowLimit_keyPressed(KeyEvent e) {
    catchHotKey(e);

  }

  void cboSeason_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtItemName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtItemID_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void this_windowOpened(WindowEvent e) {
    txtItemID.requestFocus(true);
  }

//  void btnDelete_actionPerformed(ActionEvent e) {
//    if (tblItemSeasonSearch.getRowCount()>0){
//      int i = 1;
//      if (checkTable(tblItemSeasonSearch)) {
//        i = ut.showMessage(frmMain, "Warning", "Are you sure?",
//                           DialogMessage.QUESTION_MESSAGE,
//                           DialogMessage.YES_NO_OPTION);
//      }
//      if (i == DialogMessage.YES_VALUE) {
//        String mess = "";
//        for (int j = 0; j < tblItemSeasonSearch.getRowCount(); j++) {
//          //Xoa du lieu trong Database
//          BJCheckBox check = (BJCheckBox) tblItemSeasonSearch.getValueAt(j, 0);
//          if (check.isSelected()) {
//            if (checkExist(check.getValue())) {
//              setOpenConnection();
//              Statement stmt = null;
//              try {
//                stmt = DAL.getConnection().createStatement();
//                DAL.executeUpdate(
//                    "delete from btm_im_price_hist where item_id ='" +
//                    check.getValue() + "'", stmt);
//                DAL.executeUpdate(
//                    "delete from btm_im_item_loc_supplier where item_id ='" +
//                    check.getValue() + "'", stmt);
//                DAL.executeUpdate(
//                    "delete from btm_im_item_season where item_id ='" +
//                    check.getValue() + "'", stmt);
//                DAL.executeUpdate(
//                    "delete from btm_im_item_master where item_id ='" +
//                    check.getValue() + "'", stmt);
//
//                stmt.close();
//              }
//              catch (Exception ex) {
//                ex.printStackTrace();
//              }
//              closeConnection();
//            }else {
//              if (mess.equalsIgnoreCase("")){
//                mess = "Items : " + check.getValue();
//              }else{
//                mess = mess + "; " + check.getValue();
//              }
//            }
//          }
//        }
//        if (!mess.equalsIgnoreCase("")){
//          ut.showMessage(frmMain,"Info!", mess + " can not delete!");
//        }
//      }
//      searchData(Integer.parseInt(txtRowLimit.getText()));
//    }
//  }
  boolean checkTable(BJTable table1){
    for (int i = 0; i<tblItemSeasonSearch.getRowCount(); i++){
      BJCheckBox check = (BJCheckBox) table1.getValueAt(i,0);
      if (check.isSelected()){
        return true;
      }
    }
    return false;
  }
  boolean checkExist(String itemID){
   Statement stmt = null;
   ResultSet rs = null;
   try{
     stmt = DAL.getConnection().createStatement();
     String sql = "select * from btm_im_receipt_item where item_id ='" + itemID + "'";
//     System.out.println(sql);
     rs = DAL.executeQueries(sql,stmt);
     if (rs.next()){
       stmt.close();

       return false;
     }
     stmt.close();

   }catch(Exception e){
     e.printStackTrace();
   }
   return true;
 }

  void txtArtNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtArtNo,ItemMaster.LEN_ITEM_ARTNO);
      if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
          (e.getKeyChar() != e.VK_BACK_SPACE))
        e.consume();
  }

}

class DialogItemSeasonSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_btnSearch_actionAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class DialogItemSeasonSearch_cboSeason_actionAdapter implements java.awt.event.ActionListener {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_cboSeason_actionAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboSeason_actionPerformed(e);
  }
}

class DialogItemSeasonSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_btnCancel_actionAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogItemSeasonSearch_tblItemSeasonSearch_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_tblItemSeasonSearch_mouseAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.tblItemSeasonSearch_mouseClicked(e);
  }
}

class DialogItemSeasonSearch_txtItemID_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_txtItemID_keyAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemID_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtItemID_keyPressed(e);
  }
}

class DialogItemSeasonSearch_txtItemName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_txtItemName_keyAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemName_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtItemName_keyPressed(e);
  }
}

class DialogItemSeasonSearch_scrPanelTable_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_scrPanelTable_mouseAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.scrPanelTable_mouseClicked(e);
  }
}

class DialogItemSeasonSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_txtRowLimit_keyAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRowLimit_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRowLimit_keyPressed(e);
  }
}

class DialogItemSeasonSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_this_keyAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogItemSeasonSearch_tblItemSeasonSearch_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_tblItemSeasonSearch_keyAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tblItemSeasonSearch_keyPressed(e);
  }
}

class DialogItemSeasonSearch_cboSeason_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_cboSeason_keyAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboSeason_keyPressed(e);
  }
}

class DialogItemSeasonSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_this_windowAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

//class DialogItemSeasonSearch_btnSearch1_actionAdapter implements java.awt.event.ActionListener {
//  DialogItemSeasonSearch adaptee;
//
//  DialogItemSeasonSearch_btnSearch1_actionAdapter(DialogItemSeasonSearch adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnDelete_actionPerformed(e);
//  }
//}
//

class DialogItemSeasonSearch_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  DialogItemSeasonSearch adaptee;

  DialogItemSeasonSearch_txtArtNo_keyAdapter(DialogItemSeasonSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}
