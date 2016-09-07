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
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Receive -> From Supplier -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
// View Receipt from Store
public class PanelReceiptSearch extends JPanel{
  DataAccessLayer DAL;
  ReceiptFromStoreBusObj receiptBusObj = new ReceiptFromStoreBusObj();
  FrameMainSim frmMain;
  Statement stmt = null;
  Vector vSql = new Vector();
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  JPanel jPanel6 = new JPanel();
  BJButton btnCancel = new BJButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  SortableTableModel dm = new SortableTableModel();
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

  public PanelReceiptSearch(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    jPanel6.setPreferredSize(new Dimension(800, 50));
    jPanel6.setBackground(new Color(121, 152, 198));
    jPanel6.setMinimumSize(new Dimension(126, 51));
    //Button Cancel
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setToolTipText(lang.getString("Back") + " (F12)");
    btnCancel.setActionCommand("btnCancel");
    btnCancel.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelReceiptSearch_btnCancel_actionAdapter(this));
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
    lblTransferDate.setText(lang.getString("ReceiptDate") + ":");
    txtDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtDate.setPreferredSize(new Dimension(150, 20));
    txtDate.setText("");
    txtDate.addKeyListener(new PanelReceiptSearch_txtDate_keyAdapter(this));
    btnDate.setText("...");
    btnView.addActionListener(new PanelReceiptSearch_btnView_actionAdapter(this));
    btnView.setText("<html><center><b>"+lang.getString("View")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</h" +
                    "tml>");
    btnView.setActionCommand("btnCancel");
    btnView.setToolTipText(lang.getString("View") + " (F)");
    btnView.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    table.addMouseListener(new PanelReceiptSearch_table_mouseAdapter(this));
//    btnDate1.addActionListener(new PanelReceiptSearch_btnDate1_actionAdapter(this));
    table.addKeyListener(new PanelReceiptSearch_table_keyAdapter(this));
    lblFromStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblFromStore.setPreferredSize(new Dimension(70, 20));
    lblFromStore.setText(lang.getString("FromStore") + ":");
    lblFromStore1.setText(lang.getString("ToStore") + ":");
    lblFromStore1.setPreferredSize(new Dimension(70, 20));
    lblFromStore1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboFromStore.setPreferredSize(new Dimension(150, 20));
    cboToStore.setPreferredSize(new Dimension(150, 20));
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
    btnDate.addActionListener(new PanelReceiptSearch_btnDate_actionAdapter(this));
    //View Table

    String[] columnNames = new String[]{ut.ToUpperString(lang.getString("ReceiptID")) ,
                                        ut.ToUpperString(lang.getString("FromStore")),
                                        ut.ToUpperString(lang.getString("ToStore")),
                                        ut.ToUpperString(lang.getString("Receiver")),
                                        ut.ToUpperString(lang.getString("ReceiptDate"))};
    dm.setDataVector(new Object[][]{},columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
//    table.getColumn("Transfer ID").setPreferredWidth(150);
//    table.getColumn("From Store ID").setPreferredWidth(150);
//    table.getColumn("To Store ID").setPreferredWidth(150);
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
    ResultSet rs1 = null;
    String sql = "select store_id, name from btm_im_store order by name";
    try{
      rs = DAL.executeScrollQueries(sql);
      cboFromStore.setData2(rs);
      rs1 = DAL.executeScrollQueries(sql);
      cboToStore.setData2(rs1);
      rs.getStatement().close();
      rs1.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  void btnCancel_actionPerformed(ActionEvent e) {
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
    frmMain.showScreen(Constant.SCRS_RECEIPT_FROME_STORE);
    frmMain.pnlMainSim.showInventManageButton();
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

  void btnView_actionPerformed(ActionEvent e) {
    outInterface();
  }
  void outInterface(){
    if (txtDate.getText().equals("")){
      return;
    }
    if (ut.checkDate(txtDate.getText(),"/") == false){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS346_WrongDate"));
      btnDate.requestFocus(true);
      return;
    }
    dm.resetIndexes();
    String sql =
        "Select r.RECEIPT_ID, sf.NAME FROM_STORE, st.NAME TO_STORE, e.FIRST_NAME RECEIVER,r.RECEIPT_DATE " +
        "From BTM_IM_RECEIPT_F_TSF r, BTM_IM_STORE sf, " +
        "BTM_IM_STORE st, BTM_IM_EMPLOYEE e Where r.FROM_STORE_ID = sf.STORE_ID and r.TO_STORE_ID= " +
        "st.STORE_ID and r.USER_ID=e.EMP_ID and RECEIPT_DATE=to_date('" +
        txtDate.getText() + "','DD-MM-YYYY')";
    if (!cboFromStore.getSelectedData().equalsIgnoreCase("")) {
      sql = sql + " and sf.STORE_ID = " + Long.parseLong(cboFromStore.getSelectedData());
   }
   if (!cboToStore.getSelectedData().equalsIgnoreCase("")) {
     sql = sql + " and st.STORE_ID = " + Long.parseLong(cboToStore.getSelectedData());
   }
   ResultSet rs =null;
   try{
     stmt = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
     rs = DAL.executeScrollQueries(sql,stmt);
     table.setData(rs);
     stmt.close();
    }
    catch(Exception ex){};

    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));

    table.setHeaderName(lang.getString("ReceiptID"), 0);
    table.setHeaderName(lang.getString("FromStore"), 1);
    table.setHeaderName(lang.getString("ToStore"), 2);
    table.setHeaderName(lang.getString("Receiver"), 3);
    table.setHeaderName(lang.getString("ReceiptDate"), 4);

    table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
  }
  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      viewReceiptFromStoreViewDetail();
      frmMain.showScreen(Constant.SCRS_RECEIPT_FROM_STORE_VIEW_DETAIL);
      frmMain.pnlReceiptFromStoreViewDetail.getComboData();
//      frmMain.pnlMainSim.showInventManageButton();
    }
  }
  void viewReceiptFromStoreViewDetail(){
    ResultSet rs1=null;
    ResultSet rs2=null;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

      rs1=receiptBusObj.getDataReceiptWithID(String.valueOf(table.getValueAt(table.getSelectedRow(),0)),DAL,stmt);
      rs1.next();
      if (!rs1.isBeforeFirst() && !rs1.isAfterLast()) {
        frmMain.pnlReceiptFromStoreViewDetail.showData();
        frmMain.pnlReceiptFromStoreViewDetail.txtTransferID.setText(rs1.getString("RECEIPT_ID"));
//        frmMain.pnlReceiptFromStoreViewDetail.txtFromStoreID.setText(rs1.getString("FROM_STORE_ID"));
//        frmMain.pnlReceiptFromStoreViewDetail.txtToStoreID.setText(rs1.getString("TO_STORE_ID"));
        frmMain.pnlReceiptFromStoreViewDetail.cboDesStoreID.setSelectedItem(rs1.getString("TO_STORE_ID"));
        frmMain.pnlReceiptFromStoreViewDetail.cboSourceStoreID.setSelectedItem(rs1.getString("FROM_STORE_ID"));
//        frmMain.pnlReceiptFromStoreViewDetail.txtReceiverName.setText(rs1.getString("RECEIVER_NAME"));
        frmMain.pnlReceiptFromStoreViewDetail.txtTransferDate.setText(rs1.getString("RECEIPT_DATE"));
//        frmMain.pnlReceiptFromStoreViewDetail.txtItemID.setText(rs.getString(""));
//        frmMain.pnlReceiptFromStoreViewDetail.txtQty.setText(rs.getString(""));
        rs2=receiptBusObj.getUserNameWithUserID(rs1.getString("USER_ID"),DAL);
        rs2.next();
        if (!rs2.isBeforeFirst() && !rs2.isAfterLast()) {
          frmMain.pnlReceiptFromStoreViewDetail.txtCreaterName.setText(rs2.getString("NAME"));
        }
      }
      frmMain.pnlReceiptFromStoreViewDetail.dm.resetIndexes();
      frmMain.pnlReceiptFromStoreViewDetail.table.removeAll();
      frmMain.pnlReceiptFromStoreViewDetail.table.setData(receiptBusObj.getDataTransferItemWithID(String.valueOf(table.getValueAt(table.getSelectedRow(),0)),DAL));
      receiptBusObj.getDataTransferItemWithID(String.valueOf(table.getValueAt(table.getSelectedRow(),0)),DAL).getStatement().close();
      frmMain.pnlReceiptFromStoreViewDetail.table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
      frmMain.pnlReceiptFromStoreViewDetail.table.getTableHeader().setReorderingAllowed(false);
      frmMain.pnlReceiptFromStoreViewDetail.table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
      frmMain.pnlReceiptFromStoreViewDetail.table.setHeaderName(lang.getString("ItemID"), 0);
      frmMain.pnlReceiptFromStoreViewDetail.table.setHeaderName(lang.getString("UPC"), 1);
      frmMain.pnlReceiptFromStoreViewDetail.table.setHeaderName(lang.getString("ItemName"), 2);
      frmMain.pnlReceiptFromStoreViewDetail.table.setHeaderName(lang.getString("Size"), 3);
      frmMain.pnlReceiptFromStoreViewDetail.table.setHeaderName(lang.getString("Quantity"), 4);
//      frmMain.pnlReceiptFromStoreViewDetail.table.setHeaderName("Status", 5);
      frmMain.pnlReceiptFromStoreViewDetail.table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
      int nrow=table.getSelectedRow();
//      frmMain.pnlReceiptFromStoreViewDetail.setcboStatus(table.getValueAt(nrow,0).toString());
//      ut.changeDataTypetoLong(1,frmMain.pnlReceiptFromStoreViewDetail.dm);
     // ut.changeDataTypetoLong(4,frmMain.pnlReceiptFromStoreViewDetail.dm);//Yen.Dinh 19-06-2006
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
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS347_DateType"));
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
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnCancel.doClick();
               }

  }


}

class PanelReceiptSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptSearch adaptee;

  PanelReceiptSearch_btnCancel_actionAdapter(PanelReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelReceiptSearch_btnDate_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptSearch adaptee;

  PanelReceiptSearch_btnDate_actionAdapter(PanelReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class PanelReceiptSearch_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelReceiptSearch adaptee;

  PanelReceiptSearch_btnView_actionAdapter(PanelReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelReceiptSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelReceiptSearch adaptee;

  PanelReceiptSearch_table_mouseAdapter(PanelReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelReceiptSearch_txtDate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelReceiptSearch adaptee;

  PanelReceiptSearch_txtDate_keyAdapter(PanelReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtDate_keyTyped(e);
  }
}

class PanelReceiptSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelReceiptSearch adaptee;

  PanelReceiptSearch_table_keyAdapter(PanelReceiptSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}


