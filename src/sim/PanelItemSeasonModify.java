package sim;

import java.util.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.util.ResourceBundle;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import btm.attr.*;
import btm.business.*;

/***kjklj
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Season Setup -> Season Modify</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Vinh Le
 * @version 1.0
 */

public class PanelItemSeasonModify
    extends JPanel {
  //Keep value of dialog search
  // 7-4-2006
  public String sArtNo = "";
  public String sItemID = "";
  public String sItemName = "";
  public int iAppSeason = 0;
  public String sSeasonCurrent="";
  //Variable
  DataAccessLayer DAL;
  FrameMainSim frmMain;
  Statement stmt = null;
  public boolean done = false;
  ItemSeasonBusObj iSeasonBusObj = new ItemSeasonBusObj();
  SeasonBusObj bSeasonBusObj = new SeasonBusObj();
  PhasesBusObject bPhasesBusObject = new PhasesBusObject();
  Utils ut = new Utils();
  BorderLayout borderLayout1 = new BorderLayout();
  Vector vSql = new Vector();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  //
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  //
  //
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm, true) {
    public Class getColumnClass(int col) {
      switch (col) {
        case 0:
          return Long.class;
        default:
          return Object.class;
      }
    }

    public boolean isCellEditable(int row, int col) {
      return false;
    }
  };
  BorderLayout borderLayout6 = new BorderLayout();
  BJPanel jPanel15 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJButton btnCancel = new BJButton();
  BJButton btnModify = new BJButton();
  //
  StripedTableCellRenderer stripedTable = new StripedTableCellRenderer(null,
      Color.lightGray, Color.white, null, null);
  DialogLocation dlgLocation;
  int deleteRow = -1;
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel lblSeason = new JLabel();
//  BJComboBox cboCurrCDE = new BJComboBox();
  FlowLayout flowLayout2 = new FlowLayout();
  BJComboBox cboSeason = new BJComboBox();
  JPanel jPanel9 = new JPanel();
  FlowLayout flowLayout3 = new FlowLayout();
  JTextField txtArtNo = new JTextField();
  JLabel lblIArtNo = new JLabel();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelItemSeasonModify(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void createConnection() {
//    DAL.getOracleConnect();
    this.iSeasonBusObj.setDataAccessLayer(DAL);
    this.bSeasonBusObj.setDataAccessLayer(DAL);
    this.bPhasesBusObject.setDataAccessLayer(DAL);
  }

  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setPreferredSize(new Dimension(800, 600));
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout6);
    jPanel2.setBorder(null);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setOpaque(true);
    jPanel2.setPreferredSize(new Dimension(450, 220));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(350, 220));
    jPanel3.setLayout(borderLayout3);
    jPanel4.setPreferredSize(new Dimension(150, 10));
    jPanel4.setLayout(flowLayout3);
    jPanel6.setPreferredSize(new Dimension(120, 10));
    jPanel8.setPreferredSize(new Dimension(80, 10));
    jPanel7.setPreferredSize(new Dimension(230, 10));
    jPanel5.setPreferredSize(new Dimension(250, 10));
    jPanel5.setLayout(flowLayout1);
    jScrollPane1.setPreferredSize(new Dimension(800, 390));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    jPanel15.setPreferredSize(new Dimension(700, 50));
    jPanel15.setLayout(flowLayout2);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F2)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnCancel.addActionListener(new
                                PanelItemSeasonModify_btnCancel_actionAdapter(this));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnModify.setToolTipText(lang.getString("Modify")+" (F1)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnModify.addActionListener(new
                                PanelItemSeasonModify_btnModify_actionAdapter(this));
    lblSeason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSeason.setPreferredSize(new Dimension(110, 20));
    lblSeason.setText(lang.getString("AppliedToSeason")+":");
    cboSeason.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    cboSeason.setPreferredSize(new Dimension(180, 21));
    flowLayout3.setAlignment(FlowLayout.RIGHT);
    table.addKeyListener(new PanelItemSeasonModify_table_keyAdapter(this));
    txtArtNo.setText("");
    txtArtNo.setPreferredSize(new Dimension(180, 21));
    txtArtNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtArtNo.setEnabled(false);
    txtArtNo.setBackground(SystemColor.control);
    lblIArtNo.setText(lang.getString("UPC")+":");
    lblIArtNo.setPreferredSize(new Dimension(110, 20));
    lblIArtNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    this.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel15, BorderLayout.CENTER);
    jPanel15.add(btnModify, null);
    jPanel15.add(btnCancel, null);
    this.add(jPanel2, BorderLayout.WEST);
    jPanel2.add(jPanel4, BorderLayout.WEST);
    jPanel4.add(lblIArtNo, null);
    jPanel4.add(lblSeason, null);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel2.add(jPanel8, BorderLayout.EAST);
    jPanel2.add(jPanel9, BorderLayout.NORTH);
    this.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jPanel6, BorderLayout.WEST);
    jPanel3.add(jPanel7, BorderLayout.CENTER);
    this.add(jScrollPane1, BorderLayout.SOUTH);
    jScrollPane1.getViewport().add(table, null);

    jPanel5.add(txtArtNo, null);
    jPanel5.add(cboSeason, null);

    String[] columnNames = new String[] {
        lang.getString("ItemID"), lang.getString("ItemName"), lang.getString("Season"), lang.getString("Phase")
    };
    dm.setDataVector(new Object[][] {}
                     , columnNames);
    table.setColumnWidth(0, 100);
    table.setColumnWidth(1, 200);
    table.setColumnWidth(2, 200);
    table.setColumnWidth(3, 150);

//    dlgLocation = new DialogLocation(frmMain,"", true,frmMain);
    /************ SCREEN INIT ************************/
    createConnection();
  }

  //Show data
  void showData(String itemID, String seasonID, String phaseID) {
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      this.cboSeason.setData1(bSeasonBusObj.getDataIntoCombo(stmt));
      this.cboSeason.setSelectedIndex(0);
      stmt.close();
    }
    catch (Exception ex) {}
    ;
    dlgLocation = new DialogLocation(frmMain, "", true, frmMain);
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    ResultSet rs = null;
    try {
      rs = iSeasonBusObj.getData(itemID);
      if (rs.next()) {
//        txtItemID.setText(itemID);
//        txtItemName.setText(rs.getString(2));
        txtArtNo.setText(rs.getString("ART_NO"));
        cboSeason.setSelectedItem(seasonID);
        sSeasonCurrent = cboSeason.getSelectedData();
//        cboPhase.setSelectedItem(phaseID);
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void showData(String itemID, String seasonID) {
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      this.cboSeason.setData1(bSeasonBusObj.getDataIntoCombo(stmt));
      this.cboSeason.setSelectedIndex(0);
      stmt.close();
    }
    catch (Exception ex) {}
    ;
    dlgLocation = new DialogLocation(frmMain, "", true, frmMain);
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    ResultSet rs = null;
    try {
      rs = iSeasonBusObj.getData(itemID);
      if (rs.next()) {
        txtArtNo.setText(rs.getString("ART_NO"));
        cboSeason.setSelectedItem(seasonID);
        sSeasonCurrent = cboSeason.getSelectedData();
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void btnModify_actionPerformed(ActionEvent e) {
    /*if(cboSeason.getSelectedItem().equals("None")){
        ut.showMessage(this.frmMain,"Invalid Input","Please select Season. It is required field. ");
        return;
      }
      String seasonID = "-1";
      if (cboSeason.getSelectedItem().toString() != "None") {
        seasonID = cboSeason.getSelectedData();
      }
      if (iSeasonBusObj.checkDataExistModify(txtItemID.getText(), seasonID,
                                           frmMain.SeqNo ,DAL)) {
        ut.showMessage(frmMain, "Warning!", "This Item is existed for season " + cboSeason.getSelectedItem());
        return;
      }
      DialogItemSeasonSearch dlgItemSeasonSearch = new DialogItemSeasonSearch(
        frmMain, lang.getString("TT014")+" - ITEM SEASON SEARCH", true, frmMain,
        frmMain.iSeasonBusObj);
       dlgItemSeasonSearch.setDataVector(frmMain.iSeasonBusObj.vData);
       dlgItemSeasonSearch.updateItemSeason(txtArtNo.getText(),
         cboSeason.getSelectedItem().toString());
      dlgItemSeasonSearch.setLocation(112, 85);
      dlgItemSeasonSearch.setVisible(true);*/
    if (cboSeason.getSelectedItem().equals("None")) {
      ut.showMessage(this.frmMain, lang.getString("TT013"),
                     lang.getString("MS1110_SelectSeason"));
      return;
    }
    String seasonID = "-1";
    if (cboSeason.getSelectedItem().toString() != "None") {
      seasonID = cboSeason.getSelectedData();
    }

    UpdateArtNoSeason(txtArtNo.getText(),sSeasonCurrent,cboSeason.getSelectedData(),DAL);

    //Show Search Dialog
    DialogItemSeasonSearch dlgItemSeasonSearch = new DialogItemSeasonSearch(
        frmMain, lang.getString("TT0104"), true, frmMain,
        frmMain.iSeasonBusObj);
    dlgItemSeasonSearch.txtArtNo.setText(sArtNo);
    dlgItemSeasonSearch.txtItemID.setText(sItemID);
    dlgItemSeasonSearch.txtItemName.setText(sItemName);
    dlgItemSeasonSearch.cboSeason.setSelectedIndex(iAppSeason);

    dlgItemSeasonSearch.searchData(frmMain.pnlItemSeasonSetup.rowNum);
    dlgItemSeasonSearch.txtRowLimit.setText("" +
                                            frmMain.pnlItemSeasonSetup.rowNum);

    dlgItemSeasonSearch.setLocation(112, 85);
    dlgItemSeasonSearch.setVisible(true);
  }
  //Update ArtNo and Season
  public boolean UpdateArtNoSeason(String strArtNo,String strSeasonIDCurrent,String strSeasonIDUpdate,DataAccessLayer DAL){
    Statement stmt = null;
    try{
      String sql =
          "Update BTM_IM_ITEM_SEASON set SEASON_ID = " + strSeasonIDUpdate +
          " where SEASON_ID = " + strSeasonIDCurrent + " and " +
          " ITEM_ID in (Select ITEM_ID " +
          " from BTM_IM_ITEM_MASTER " +
          "where ART_NO='"+strArtNo+"')";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      DAL.executeUpdate(sql,stmt);
      stmt.close();
    }catch(Exception e){
      return false;
    }
    return true;
  }

  /*void txtItemID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtItemID,Store.LEN_STORE_NAME);
     }
     void txtItemName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtItemName,Store.LEN_STORE_SHORT_NAME1);
     }*/

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
    // ENTER
    key = new Integer(KeyEvent.VK_ENTER);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
    actionMap.put(key, new KeyAction(key));

    // ESCAPE
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

      if (identifier.intValue() == KeyEvent.VK_F1 ||
          identifier.intValue() == KeyEvent.VK_ENTER) {
        btnModify.doClick();
      }

      else if (identifier.intValue() == KeyEvent.VK_F2 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void table_mousePressed(MouseEvent e) {
  }

//  void cboSeason_actionPerformed(ActionEvent e) {
//        refreshPhaseCombo();
//  }
//  private void refreshPhaseCombo(){
//   if (cboSeason.getSelectedIndex() > 0){
//     try {
//       stmt = DAL.getConnection().createStatement(ResultSet.
//           TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//       this.cboPhase.setData1(this.bPhasesBusObject.getDataIntoCombo( (String)this.
//           cboSeason.getSelectedData(), stmt));
//     }
//     catch (Exception ex) {}
//     ;
//   }
//   else {
//     cboPhase.removeAllItems();
//     cboPhase.addItem("None");
//   }
// }

  void btnCancel_actionPerformed(ActionEvent e) {
    DialogItemSeasonSearch dlgItemSeasonSearch = new DialogItemSeasonSearch(
        frmMain, lang.getString("TT0104"), true, frmMain,
        frmMain.iSeasonBusObj);
    dlgItemSeasonSearch.txtArtNo.setText(sArtNo);
    dlgItemSeasonSearch.txtItemID.setText(sItemID);
    dlgItemSeasonSearch.txtItemName.setText(sItemName);
    dlgItemSeasonSearch.cboSeason.setSelectedIndex(iAppSeason);

    dlgItemSeasonSearch.searchData(frmMain.pnlItemSeasonSetup.rowNum);
    dlgItemSeasonSearch.txtRowLimit.setText("" +
                                            frmMain.pnlItemSeasonSetup.rowNum);

    dlgItemSeasonSearch.setLocation(112, 85);
    dlgItemSeasonSearch.setVisible(true);
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      btnCancel.doClick();
    }
  }

}

class PanelItemSeasonModify_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelItemSeasonModify adaptee;

  PanelItemSeasonModify_btnCancel_actionAdapter(PanelItemSeasonModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelItemSeasonModify_btnModify_actionAdapter
    implements java.awt.event.ActionListener {
  PanelItemSeasonModify adaptee;

  PanelItemSeasonModify_btnModify_actionAdapter(PanelItemSeasonModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelItemSeasonModify_table_mouseAdapter
    extends java.awt.event.MouseAdapter {
  PanelItemSeasonModify adaptee;

  PanelItemSeasonModify_table_mouseAdapter(PanelItemSeasonModify adaptee) {
    this.adaptee = adaptee;
  }

  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelItemSeasonModify_table_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelItemSeasonModify adaptee;

  PanelItemSeasonModify_table_keyAdapter(PanelItemSeasonModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
