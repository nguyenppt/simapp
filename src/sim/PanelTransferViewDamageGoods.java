package sim;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
import common.*;
import btm.swing.*;
import btm.attr.*;
import btm.business.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.ResourceBundle;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Transfer -> View Damage Goods</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelTransferViewDamageGoods extends JPanel {
  DataAccessLayer DAL;
      ResourceBundle lang;
  TransferBusObj transBusObj = new TransferBusObj();
  FrameMainSim frmMain;
  Statement stmt = null;
  Vector vSql = new Vector();
  Utils ut = new Utils();
  JPanel jPanel6 = new JPanel();
  BJButton btnCancel = new BJButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
//  SortableTableModel dm = new SortableTableModel();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 1: return Long.class;
        case 2: return Long.class;
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm,true){
    public void tableChanged(TableModelEvent e) {
      super.tableChanged(e);
      repaint();
    }
  };

  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  JLabel lblTransferDate = new JLabel();
  FlowLayout flowLayout1 = new FlowLayout();
  JTextField txtDate = new JTextField();
  JButton btnDate = new JButton();
  BJButton btnView = new BJButton();
  JLabel lblFromStore = new JLabel();
  BJComboBox cboFromStore = new BJComboBox();
  JLabel lblFromStore1 = new JLabel();
  BJComboBox cboToStore = new BJComboBox();

  public PanelTransferViewDamageGoods(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    DAL.getConnfigParameter();
    lang=DAL.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

    registerKeyboardActions();
    jPanel6.setPreferredSize(new Dimension(800, 50));
    jPanel6.setBackground(new Color(121, 152, 198));
    jPanel6.setMinimumSize(new Dimension(126, 51));
    //Button Cancel
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setToolTipText(lang.getString("Back")+" (F12)");
    btnCancel.setActionCommand("btnCancel");
    btnCancel.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelTransferViewDamageGoods_btnCancel_actionAdapter(this));
    this.setLayout(borderLayout1);
    jPanel1.setDebugGraphicsOptions(0);
    jPanel1.setPreferredSize(new Dimension(800, 460));
    jPanel1.setLayout(borderLayout2);
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(780, 395));
    jPanel3.setBackground(SystemColor.control);
    jPanel3.setPreferredSize(new Dimension(800, 400));
    jPanel3.setRequestFocusEnabled(true);
    jPanel3.setLayout(borderLayout3);
    jPanel2.setPreferredSize(new Dimension(800, 50));
    jPanel2.setLayout(flowLayout1);
    lblTransferDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTransferDate.setText(lang.getString("TransferDate") );
    txtDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDate.setPreferredSize(new Dimension(150, 20));
    txtDate.setText("");
    txtDate.addKeyListener(new PanelTransferViewDamageGoods_txtDate_keyAdapter(this));
    btnDate.setText("...");
    btnDate.addActionListener(new PanelTransferViewDamageGoods_btnDate_actionAdapter(this));
    btnView.setText("<html><center><b>"+lang.getString("View")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</h" +
                    "tml>");
    btnView.addActionListener(new PanelTransferViewDamageGoods_btnView_actionAdapter(this));
    btnView.setActionCommand("btnCancel");
    btnView.setToolTipText(lang.getString("View") +" (F5)");
    btnView.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//    btnDate1.addActionListener(new PanelTransferView_btnDate1_actionAdapter(this));
    lblFromStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFromStore.setPreferredSize(new Dimension(70, 20));
    lblFromStore.setText(lang.getString("FromStore") );
    lblFromStore1.setText(lang.getString("ToStore") );
    lblFromStore1.setPreferredSize(new Dimension(70, 20));
    lblFromStore1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboFromStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboFromStore.setPreferredSize(new Dimension(150, 20));
    cboToStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboToStore.setPreferredSize(new Dimension(150, 20));
    table.addMouseListener(new PanelTransferViewDamageGoods_table_mouseAdapter(this));
    table.addKeyListener(new PanelTransferViewDamageGoods_table_keyAdapter(this));
    jPanel6.add(btnView, null);
    jPanel6.add(btnCancel, null);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(lblTransferDate, null);
    jPanel2.add(txtDate, null);
    jPanel2.add(btnDate, null);
    jPanel2.add(lblFromStore, null);
    jPanel2.add(cboFromStore, null);
    jPanel2.add(lblFromStore1, null);
    jPanel2.add(cboToStore, null);
    this.add(jPanel1,  BorderLayout.SOUTH);
    jPanel1.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.add(table, null);
    jScrollPane1.setViewportView(table);
    this.add(jPanel6, BorderLayout.NORTH);
    //View Table
    String[] columnNames = new String[]{lang.getString("TransferId"),lang.getString("FromStoreID"),lang.getString("ToStoreID")};
    dm.setDataVector(new Object[][]{},columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn(lang.getString("TransferId")).setPreferredWidth(150);
    table.getColumn(lang.getString("FromStoreID")).setPreferredWidth(150);
    table.getColumn(lang.getString("ToStoreID")).setPreferredWidth(150);
  }
  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_TRANSFER_VIEW);
    btnView.setEnabled(er.getAdd());
//    btnAdminRoleDel.setEnabled(er.getDelete());
//    btnAdminRoleModify.setEnabled(er.getModify());
//    btnAdminRoleSearch.setEnabled(er.getSearch());
  }
  public void showData(){
    ResultSet rs = null;
    String sql = "select store_id, name from btm_im_store order by upper(name)";
    try{
      rs = DAL.executeScrollQueries(sql);
      cboFromStore.setData(rs);
      rs.beforeFirst();
      cboToStore.setData(rs);
      rs.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
    frmMain.showScreen(Constant.SCRS_TRANSFER_DAMAGE_GOODS);
    frmMain.pnlTransferDamageGoods.applyPermission();
//    frmMain.pnlTransferDamageGoods.setText();
  }

  void btnDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;;
    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnDate.getX() + posXFrame+90;
    posY = this.btnDate.getY() + posYFrame+90;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtDate.setText(date);
      this.txtDate.requestFocus(true);
    }
    outInterface();

  }
  void outInterface(){
    if (txtDate.getText().equals("")){
      return;
    }
    if (ut.checkDate(txtDate.getText(),"/") == false){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1018_InputDate"));
      btnDate.requestFocus(true);
      return;
    }
    dm.resetIndexes();
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
     table.setData(transBusObj.getDataWithDateDamageGoods(txtDate.getText(),DAL,stmt,cboFromStore.getSelectedData(),cboToStore.getSelectedData()));
         stmt.close();
    }
    catch(Exception ex){};

    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    table.setHeaderName(lang.getString("TransferId"), 0);
    table.setHeaderName(lang.getString("FromStoreID"), 1);
    table.setHeaderName(lang.getString("ToStoreID"), 2);
    table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
  }

  void btnView_actionPerformed(ActionEvent e) {
    outInterface();
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      viewTransferViewDetail();
      frmMain.showScreen(Constant.SCRS_TRANSFER_MODIFY_DAMAGE_GOODS);
      frmMain.pnlTransferModifyDamageGoods.getComboData();
//      frmMain.pnlTransferViewDetail.getComboData();
    }

  }
  void viewTransferViewDetail(){
    ResultSet rs1=null;
    ResultSet rs2=null;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

      rs1=transBusObj.getDataTransferWithIDDmgGoods(String.valueOf(table.getValueAt(table.getSelectedRow(),0)),DAL,stmt);
      rs1.next();
      if (!rs1.isBeforeFirst() && !rs1.isAfterLast()) {
        frmMain.pnlTransferModifyDamageGoods.showData();
        frmMain.pnlTransferModifyDamageGoods.txtTransferID.setText(rs1.getString("DMG_GOODS_ID"));
//        frmMain.pnlTransferModifyDamageGoods.txtFromStoreID.setText(rs1.getString("FROM_STORE_ID"));
//        frmMain.pnlTransferModifyDamageGoods.txtToStoreID.setText(rs1.getString("TO_STORE_ID"));
        frmMain.pnlTransferModifyDamageGoods.cboDesStoreID.setSelectedItem(rs1.getString("TO_STORE_ID"));
        frmMain.pnlTransferModifyDamageGoods.cboSourceStoreID.setSelectedItem(rs1.getString("FROM_STORE_ID"));
        frmMain.pnlTransferModifyDamageGoods.txtReceiverName.setText(rs1.getString("RECEIVER_NAME"));
        frmMain.pnlTransferModifyDamageGoods.txtTransferDate.setText(rs1.getString("TRANSFER_DATE"));
//        frmMain.pnlTransferViewDetail.txtItemID.setText(rs.getString(""));
//        frmMain.pnlTransferViewDetail.txtQty.setText(rs.getString(""));
        rs2=transBusObj.getUserNameWithUserID(rs1.getString("USER_ID"),DAL);
        rs2.next();
        if (!rs2.isBeforeFirst() && !rs2.isAfterLast()) {
          frmMain.pnlTransferModifyDamageGoods.txtCreaterName.setText(rs2.getString("NAME"));
        }
      }
      frmMain.pnlTransferModifyDamageGoods.dm.resetIndexes();
      frmMain.pnlTransferModifyDamageGoods.table.removeAll();
      frmMain.pnlTransferModifyDamageGoods.table.setData(transBusObj.getDataTransferItemWithIDDmgGoods(String.valueOf(table.getValueAt(table.getSelectedRow(),0)),DAL));
      transBusObj.getDataTransferItemWithIDDmgGoods(String.valueOf(table.getValueAt(table.getSelectedRow(),0)),DAL).getStatement().close();
      frmMain.pnlTransferModifyDamageGoods.table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
      frmMain.pnlTransferModifyDamageGoods.table.getTableHeader().setReorderingAllowed(false);
      frmMain.pnlTransferModifyDamageGoods.table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
      frmMain.pnlTransferModifyDamageGoods.table.setHeaderName(lang.getString("ItemID"), 0);
      frmMain.pnlTransferModifyDamageGoods.table.setHeaderName(lang.getString("UPC"), 1);
      frmMain.pnlTransferModifyDamageGoods.table.setHeaderName(lang.getString("ItemName"), 2);
      frmMain.pnlTransferModifyDamageGoods.table.setHeaderName(lang.getString("Size"), 3);
      frmMain.pnlTransferModifyDamageGoods.table.setHeaderName(lang.getString("Quantity"), 4);
      frmMain.pnlTransferModifyDamageGoods.table.setHeaderName(lang.getString("Status"), 5);
      frmMain.pnlTransferModifyDamageGoods.table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
//      ut.changeDataTypetoLong(1,frmMain.pnlTransferViewDetail.dm);
     // ut.changeDataTypetoLong(4,frmMain.pnlTransferModifyDamageGoods.dm);// Yen.Dinh 19-06-2006
      stmt.close();
      rs2.getStatement().close();
//      rs1.close();
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

  }

  void txtDate_keyTyped(KeyEvent e) {
    if (!txtDate.getText().equalsIgnoreCase("")){
      if (e.getKeyChar() == KeyEvent.VK_ENTER){
        if (ut.checkDate(txtDate.getText(), "/") || ut.checkDate(txtDate.getText(), "-")) {
          outInterface();
        }else{
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1018_InputDate"));
          txtDate.requestFocus(true);
          while(dm.getRowCount()>0){
            dm.removeRow(0);
          }
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

                    if (identifier.intValue() == KeyEvent.VK_F5 || identifier.intValue() == KeyEvent.VK_ENTER) {
                      btnView.doClick();
                    }
                    else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                      btnCancel.doClick();
                    }
                  }
          }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F12) {
                 btnCancel.doClick();
               }

  }

}

class PanelTransferViewDamageGoods_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferViewDamageGoods adaptee;

  PanelTransferViewDamageGoods_btnCancel_actionAdapter(PanelTransferViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelTransferViewDamageGoods_btnDate_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferViewDamageGoods adaptee;

  PanelTransferViewDamageGoods_btnDate_actionAdapter(PanelTransferViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class PanelTransferViewDamageGoods_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferViewDamageGoods adaptee;

  PanelTransferViewDamageGoods_btnView_actionAdapter(PanelTransferViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelTransferViewDamageGoods_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelTransferViewDamageGoods adaptee;

  PanelTransferViewDamageGoods_table_mouseAdapter(PanelTransferViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelTransferViewDamageGoods_txtDate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferViewDamageGoods adaptee;

  PanelTransferViewDamageGoods_txtDate_keyAdapter(PanelTransferViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtDate_keyTyped(e);
  }
}

class PanelTransferViewDamageGoods_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferViewDamageGoods adaptee;

  PanelTransferViewDamageGoods_table_keyAdapter(PanelTransferViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
