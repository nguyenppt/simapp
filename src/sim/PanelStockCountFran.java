package sim;

import javax.swing.*;
import java.awt.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import btm.business.*;
/**
 * <p>Title: View of Franchise's Stock Count </p>
 * <p>Description: show SOH quantity of the selected franchise
 *                 and have a right to modify the SOH qty by inputting new count qty
 *                 update SOH and back_room_qty in BTM_IM_ITEM_FRAN_SOH
 *                 insert new row into BTM_IM_FRAN_STOCK_COUNT with tolerance qty and generated STK_ID</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM Intersoft VN </p>
 * @PHUONG NGUYEN
 * @version 1.0
 */
//Not use
public class PanelStockCountFran extends JPanel{
  FrameMainSim frmMain;
  DataAccessLayer DAL;
  Utils ut=new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  FranBusObj franBusObj = new FranBusObj();
  BorderLayout borderLayout1 = new BorderLayout();

  JPanel btnPanel = new JPanel();

  FlowLayout flowLayout1 = new FlowLayout();
  BJButton btnDone = new BJButton();
  BJButton btnBack = new BJButton();
  int enterCount=0;
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout2 = new FlowLayout(flowLayout1.CENTER,30,5);
  JPanel infoPanel = new JPanel();
  JLabel lblFranName = new JLabel();
  BJComboBox cboFranName = new BJComboBox();
  JLabel lblItemID = new JLabel();
  JTextField txtItemID = new JTextField();
  JLabel lblUserCount = new JLabel();
  JTextField txtUserCount = new JTextField();
  JLabel lblArtNo = new JLabel();
  JTextField txtArtNo = new JTextField();

  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable tbl = new BJTable(dm,true);

  public PanelStockCountFran(FrameMainSim frmMain) {
    try {
      this.frmMain=frmMain;
      this.DAL=frmMain.getDAL();
      jbInit();
      franBusObj.setCboFran(cboFranName,DAL);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
//set Accelerators
    registerKeyboardActions();
//set properties for Panel contains buttons
    btnPanel.setBackground(new Color(121, 152, 198));
    btnPanel.setPreferredSize(new Dimension(800, 50));
    btnPanel.setLayout(flowLayout1);
    this.setLayout(borderLayout1);
//set properties for btn Done
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelStockCountFran_btnDone_actionAdapter(this));
//set properties for btn Back
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnBack.setPreferredSize(new Dimension(120, 37));
    btnBack.setToolTipText(lang.getString("Back") + " (F2)");
    btnBack.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnBack.addActionListener(new PanelStockCountFran_btnBack_actionAdapter(this));

    jPanel2.setPreferredSize(new Dimension(800, 550));
    jPanel2.setLayout(borderLayout2);
    infoPanel.setAlignmentX((float) 1.0);
    infoPanel.setAlignmentY((float) 1.0);
    infoPanel.setPreferredSize(new Dimension(800, 60));
    infoPanel.setLayout(flowLayout2);
    jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPane1.setEnabled(true);
    jScrollPane1.setDebugGraphicsOptions(0);
    jScrollPane1.setDoubleBuffered(false);
    jScrollPane1.setMinimumSize(new Dimension(24, 28));
    jScrollPane1.setPreferredSize(new Dimension(800, 470));
    jScrollPane1.setVerifyInputWhenFocusTarget(true);
//set properties for label FranName
    lblFranName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFranName.setAlignmentX((float) 1.0);
    lblFranName.setAlignmentY((float) 1.0);
    lblFranName.setPreferredSize(new Dimension(85, 20));
    lblFranName.setText(lang.getString("Franchise") + ":");
//set properties for combobox FranName
    cboFranName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboFranName.setAlignmentX((float) 1.0);
    cboFranName.setAlignmentY((float) 1.0);
    cboFranName.setPreferredSize(new Dimension(200, 20));
    cboFranName.setSelectedIndex(-1);
    cboFranName.addActionListener(new PanelStockCountFran_cboFranName_actionAdapter(this));
//set properties for label ItemID
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setAlignmentX((float) 4.0);
    lblItemID.setAlignmentY((float) 4.5);
    lblItemID.setPreferredSize(new Dimension(85, 20));
    lblItemID.setText(lang.getString("ItemID")+":");
//set properties for textbox ItemID
    txtItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemID.setAlignmentX((float) 1.0);
    txtItemID.setAlignmentY((float) 1.0);
    txtItemID.setPreferredSize(new Dimension(200, 20));
    txtItemID.setText("");
    txtItemID.addKeyListener(new PanelStockCountFran_txtItemID_keyAdapter(this));
//set properties for label UserCount
    lblUserCount.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblUserCount.setPreferredSize(new Dimension(85, 20));
    lblUserCount.setText(lang.getString("UserCount") + ":");
//set properties for textbox UserCount
    txtUserCount.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUserCount.setPreferredSize(new Dimension(200, 20));
    txtUserCount.setText("");
    txtUserCount.addKeyListener(new PanelStockCountFran_txtUserCount_keyAdapter(this));
//set properties for label ArtNo
    lblArtNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblArtNo.setPreferredSize(new Dimension(85, 20));
    lblArtNo.setText(lang.getString("UPC") + ":");
    txtArtNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtArtNo.setPreferredSize(new Dimension(200, 20));
    txtArtNo.setText("");
    txtArtNo.addKeyListener(new PanelStockCountFran_txtArtNo_keyAdapter(this));
    this.add(btnPanel,  BorderLayout.NORTH);
    btnPanel.add(btnDone, null);
    btnPanel.add(btnBack, null);
    this.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(infoPanel, BorderLayout.NORTH);
//add elements into infoPanel
    infoPanel.add(lblFranName, null);
    infoPanel.add(cboFranName, null);
    infoPanel.add(lblItemID, null);
    infoPanel.add(txtItemID, null);
    infoPanel.add(lblUserCount, null);
    infoPanel.add(txtUserCount, null);
    infoPanel.add(lblArtNo, null);
    infoPanel.add(txtArtNo, null);

    jPanel2.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tbl, null);
  }

  void btnBack_actionPerformed(ActionEvent e) {
    resetData();
    frmMain.showScreen(Constant.SCRS_PROD_GROUP);
  }

  void resetData(){
     cboFranName.setSelectedIndex(0);
     txtUserCount.setText("");
     txtItemID.setText("");
     txtArtNo.setText("");
     this.enterCount = 0;
     cboFranName.setEnabled(true);
     while (tbl.getRowCount()>0){
       dm.removeRow(0);
     }
   }

  private void registerKeyboardActions(){
    //set up the map
    InputMap inputMap= getInputMap(WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap=getActionMap();
    //F1
    Integer key = new Integer(KeyEvent.VK_F1);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0),key);
    actionMap.put(key,new KeyAction(key));
    //F2
    key=new Integer(KeyEvent.VK_F2);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0),key);
    actionMap.put(key,new KeyAction(key));
  }
  class KeyAction extends AbstractAction{
    private Integer identifier = null;

    public KeyAction(Integer identifier) {
      this.identifier = identifier;
    }
    public void actionPerformed(ActionEvent e) {
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnDone.doClick();
     }else if (identifier.intValue() == KeyEvent.VK_F2) {
        btnBack.doClick();
   }
    }
  }

  void txtUserCount_keyTyped(KeyEvent e) {
    char key = e.getKeyChar();
    ut.checkNumber(e);
    if (key == KeyEvent.VK_ENTER){
      if (txtUserCount.getText().equalsIgnoreCase("")){
        ut.showMessage(frmMain, lang.getString("TT001"),lang.getString("MS316_UserCountNotNull"));
        txtUserCount.requestFocus(true);
        return;
      }
      if (tbl.getSelectedRow() != -1){
        this.enterCount ++;
        tbl.setValueAt(new Long(Long.parseLong(txtUserCount.getText())),tbl.getSelectedRow(),6);
        txtUserCount.setText("");
        tbl.requestFocus(true);
      }
      if (this.enterCount >= 1) {
        cboFranName.setEnabled(false);
      }
    }
  }

  void txtItemID_keyTyped(KeyEvent e) {
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      if (!txtItemID.getText().equalsIgnoreCase("") && txtArtNo.getText().equalsIgnoreCase("")){
        for (int i = 0; i < tbl.getRowCount(); i++) {
          if (tbl.getValueAt(i,
              0).toString().equalsIgnoreCase(txtItemID.getText())) {
//            tbl.setRowSelectionInterval(i, i);
//            tbl.requestFocus(true);

            //show current row
            tbl.setRowSelectionInterval(i, i);
            Rectangle bounds = tbl.getCellRect(i,0,true);
            jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

            txtUserCount.requestFocus(true);

            return;
          }
        }
      }else if(txtItemID.getText().equalsIgnoreCase("") && !txtArtNo.getText().equalsIgnoreCase("")){
        for (int i = 0; i < tbl.getRowCount(); i++) {
          if (tbl.getValueAt(i,1).toString().equalsIgnoreCase(txtArtNo.getText())) {
//            tbl.setRowSelectionInterval(i, i);
//            tbl.requestFocus(true);

            //show current row
            tbl.setRowSelectionInterval(i, i);
            Rectangle bounds = tbl.getCellRect(i,0,true);
            jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

            txtUserCount.requestFocus(true);

            return;
          }
        }
      }else if(!txtItemID.getText().equalsIgnoreCase("") && !txtArtNo.getText().equalsIgnoreCase("")){
        for (int i = 0; i < tbl.getRowCount(); i++) {
          if (tbl.getValueAt(i,0).toString().equalsIgnoreCase(txtItemID.getText())
              && tbl.getValueAt(i,1).toString().equalsIgnoreCase(txtArtNo.getText())) {
//            tbl.setRowSelectionInterval(i, i);
//            tbl.requestFocus(true);

            //show current row
            tbl.setRowSelectionInterval(i, i);
            Rectangle bounds = tbl.getCellRect(i,0,true);
            jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

            txtUserCount.requestFocus(true);

            return;
          }
        }
      }
    }
  }

  void txtArtNo_keyTyped(KeyEvent e) {
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      if (!txtItemID.getText().equalsIgnoreCase("") && txtArtNo.getText().equalsIgnoreCase("")){
        for (int i = 0; i < tbl.getRowCount(); i++) {
          if (tbl.getValueAt(i,0).toString().equalsIgnoreCase(txtItemID.getText())) {
//            tbl.setRowSelectionInterval(i, i);
//            tbl.requestFocus(true);

            //show current row
            tbl.setRowSelectionInterval(i, i);
            Rectangle bounds = tbl.getCellRect(i,0,true);
            jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

            txtUserCount.requestFocus(true);

            return;
          }
        }
      }else if(txtItemID.getText().equalsIgnoreCase("") && !txtArtNo.getText().equalsIgnoreCase("")){
        for (int i = 0; i < tbl.getRowCount(); i++) {
          if (tbl.getValueAt(i,1).toString().equalsIgnoreCase(txtArtNo.getText())) {
//            tbl.setRowSelectionInterval(i, i);
//            tbl.requestFocus(true);

            //show current row
            tbl.setRowSelectionInterval(i, i);
            Rectangle bounds = tbl.getCellRect(i,0,true);
            jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

            txtUserCount.requestFocus(true);

            return;
          }
        }
      }else if(!txtItemID.getText().equalsIgnoreCase("") && !txtArtNo.getText().equalsIgnoreCase("")){
        for (int i = 0; i < tbl.getRowCount(); i++) {
          if (tbl.getValueAt(i,0).toString().equalsIgnoreCase(txtItemID.getText())
              && tbl.getValueAt(i,1).toString().equalsIgnoreCase(txtArtNo.getText())) {
//            tbl.setRowSelectionInterval(i, i);
//            tbl.requestFocus(true);

            //show current row
            tbl.setRowSelectionInterval(i, i);
            Rectangle bounds = tbl.getCellRect(i,0,true);
            jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

            txtUserCount.requestFocus(true);

            return;
          }
        }
      }
    }
  }

  void cboFranName_actionPerformed(ActionEvent e) {
    if (cboFranName.getSelectedIndex() != 0){
      while(tbl.getRowCount()>0){
        dm.removeRow(0);
      }
      Statement stmt = null;
      ResultSet rs = null;
      String sql =
          " select im.item_id, art_no, item_name, ad.attr_dtl_desc color,  "
        + " (select attr_dtl_desc from btm_im_attr_dtl a, btm_im_item_master i  "
        + " where i.attr2_dtl_id = a.attr_dtl_id and item_id =im.item_id) ssize, back_room_qty, 0 user_count  "
        + "from btm_im_item_fran_soh ifs, btm_im_item_master im, btm_im_attr_dtl ad "
        + "where ifs.item_id = im.item_id and im.attr1_dtl_id = ad.attr_dtl_id and franchise_id = '"
        + cboFranName.getSelectedData() + "'";

      try {
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = DAL.executeScrollQueries(sql, stmt);
        dm.resetIndexes();
        tbl.setData(rs);
        tbl.setHeaderName(lang.getString("Item ID"), 0);
        tbl.setHeaderName(lang.getString("UPC"), 1);
        tbl.setHeaderName(lang.getString("Name"), 2);
        tbl.setHeaderName(lang.getString("Color"), 3);
        tbl.setHeaderName(lang.getString("Size"), 4);
        tbl.setHeaderName(lang.getString("SystemCount"), 5);
        tbl.setHeaderName(lang.getString("UserCount"), 6);
        ut.changeDataTypetoLong(5, dm);
        ut.changeDataTypetoLong(6, dm);
        if (tbl.getRowCount() > 0) {
          tbl.setRowSelectionInterval(0, 0);
        }
        txtItemID.requestFocus(true);
        rs.close();
        stmt.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  void btnDone_actionPerformed(ActionEvent e) {
    String today = "";
    today = ut.getSystemDateTime();
    today = today.substring(0,2) + today.substring(3, 5) + today.substring(8,10)
            +today.substring(11, 13)+today.substring(14, 16)+today.substring(17);
    String stkID = DAL.getStoreID() + today;
    Vector vSql = new Vector();
    for (int i=0; i< tbl.getRowCount(); i++){
      if (!tbl.getValueAt(i,6).toString().equalsIgnoreCase("0")){
        long systemCount = Long.parseLong(tbl.getValueAt(i,5).toString());
        long userCount = Long.parseLong(tbl.getValueAt(i,6).toString());
        float variantQty = userCount - systemCount;
        String sql = "insert into btm_im_fran_stock_count(stk_id, franchise_id, item_id, stk_counted, stk_date, variance_qty) " +
            " values('" + stkID + "'," + Long.parseLong(cboFranName.getSelectedData()) +
            ",'" + tbl.getValueAt(i,0).toString() + "'," + userCount + ",to_date('" +
            ut.getSystemDate() + "','" + ut.DATE_FORMAT + "')," + variantQty + " )";
//        System.out.println(sql);
        vSql.addElement(sql);
        sql = "update btm_im_item_fran_soh set stock_on_hand = front_room_qty + " + userCount
            + ", back_room_qty = " + userCount
            + " where item_id = '" + tbl.getValueAt(i,0).toString()
            + "' and franchise_id = '" + cboFranName.getSelectedData().toString() + "'";
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
}

class PanelStockCountFran_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFran adaptee;

  PanelStockCountFran_btnBack_actionAdapter(PanelStockCountFran adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelStockCountFran_txtUserCount_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockCountFran adaptee;

  PanelStockCountFran_txtUserCount_keyAdapter(PanelStockCountFran adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtUserCount_keyTyped(e);
  }
}

class PanelStockCountFran_txtItemID_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockCountFran adaptee;

  PanelStockCountFran_txtItemID_keyAdapter(PanelStockCountFran adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemID_keyTyped(e);
  }
}

class PanelStockCountFran_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelStockCountFran adaptee;

  PanelStockCountFran_txtArtNo_keyAdapter(PanelStockCountFran adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelStockCountFran_cboFranName_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFran adaptee;

  PanelStockCountFran_cboFranName_actionAdapter(PanelStockCountFran adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboFranName_actionPerformed(e);
  }
}

class PanelStockCountFran_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelStockCountFran adaptee;

  PanelStockCountFran_btnDone_actionAdapter(PanelStockCountFran adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}
