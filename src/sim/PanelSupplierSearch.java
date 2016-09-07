package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import java.sql.*;
import java.util.*;
import java.sql.Date;

/**
 * <p>Title: </p>
 * <p>Description: Main->Merch Mgmt->Supp Setup</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelSupplierSearch extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  DataAccessLayer DAL;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  int parentScreen=0;

  SupplierBusObj suppBusObj = new SupplierBusObj();
  BJCheckBox check = new BJCheckBox();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 6: return Double.class;
        case 8: return Double.class;
        case 9: return Date.class;
        case 10: return Date.class;
        case 12: return Date.class;
        case 15: return Double.class;
        case 18: return Double.class;
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm,true);
  BorderLayout borderLayout2 = new BorderLayout();
  BJPanel jPanel5 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJButton btnSearch = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnModify = new BJButton();
  BJButton btnCancel = new BJButton();
  JTextField txtSearch = new JTextField();
  FrameMainSim frmMain;
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField txtCustName = new JTextField();
  JTextField txtAddress1 = new JTextField();
  JTextField txtRowsLimit = new JTextField();
  GridLayout gridLayout1 = new GridLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  int rowsNum = 5;
  BJButton btnDone = new BJButton();

  public PanelSupplierSearch(FrameMainSim frmMain) {
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
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    jPanel1.setMinimumSize(new Dimension(10, 10));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout2);
    jPanel2.setPreferredSize(new Dimension(800, 400));
    jPanel2.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(800, 380));
//    jPanel3.setPreferredSize(new Dimension(230, 50));
    jPanel5.setMinimumSize(new Dimension(750, 67));
    jPanel5.setPreferredSize(new Dimension(320, 50));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//    btnSearch.setPreferredSize(new Dimension(80, 35));
    btnSearch.setToolTipText(lang.getString("Search")+" (F2)");
    btnSearch.setText(lang.getString("Search")+" (F2)");
    btnSearch.addActionListener(new PanelSupplierSearch_btnSearch_actionAdapter(this));
//    txtSearch.setPreferredSize(new Dimension(120, 20));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//    btnDelete.setPreferredSize(new Dimension(80, 35));
    btnDelete.setToolTipText(lang.getString("Delete") + " (F3)");
    btnDelete.setText(lang.getString("Delete")+" (F3)");
    btnDelete.addActionListener(new PanelSupplierSearch_btnDelete_actionAdapter(this));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//    btnModify.setPreferredSize(new Dimension(80, 35));
    btnModify.setToolTipText(lang.getString("Modify") + " (F4)");
    btnModify.setText(lang.getString("Modify") + " (F4)");
    btnModify.addActionListener(new PanelSupplierSearch_btnModify_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
//    btnCancel.setPreferredSize(new Dimension(80, 35));
    btnCancel.setToolTipText(lang.getString("Cancel") + " (F5)");
    btnCancel.setText(lang.getString("Cancel") + " (F5)");
    btnCancel.addActionListener(new PanelSupplierSearch_btnCancel_actionAdapter(this));

    jPanel6.setPreferredSize(new Dimension(800, 130));
    jPanel6.setLayout(borderLayout3);
    jPanel9.setPreferredSize(new Dimension(150, 10));
    jPanel7.setPreferredSize(new Dimension(150, 10));
    jPanel8.setLayout(borderLayout4);
    jPanel11.setPreferredSize(new Dimension(120, 10));
    jPanel11.setLayout(flowLayout2);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setText(lang.getString("CustomerName")+":");
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText(lang.getString("Address") + ":");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setText(lang.getString("RowsLimit") + ":");
    txtCustName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCustName.setPreferredSize(new Dimension(180, 20));
    txtCustName.setText("");
    txtCustName.setBounds(new Rectangle(1, 6, 180, 20));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAddress1.setPreferredSize(new Dimension(180, 20));
    txtAddress1.setText("");
    txtAddress1.setBounds(new Rectangle(1, 31, 180, 20));
    txtRowsLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRowsLimit.setPreferredSize(new Dimension(46, 20));
    txtRowsLimit.setText(DAL.getSearchLimit());
    txtRowsLimit.setBounds(new Rectangle(1, 56, 46, 20));
    jPanel10.setPreferredSize(new Dimension(200, 30));
    jPanel10.setLayout(null);
    jPanel8.setPreferredSize(new Dimension(500, 30));
    table.addMouseListener(new PanelSupplierSearch_table_mouseAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnDone.setToolTipText(lang.getString("Done") + " (F1)");
    btnDone.setText(lang.getString("Done") + " (F1)");
    btnDone.addActionListener(new PanelSupplierSearch_btnDone_actionAdapter(this));
    table.addKeyListener(new PanelSupplierSearch_table_keyAdapter(this));
    this.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel5,BorderLayout.CENTER);
    jPanel5.add(btnDone, null);
    jPanel5.add(btnSearch,null);
    jPanel5.add(btnDelete,null);
    jPanel5.add(btnModify,null);
    jPanel5.add(btnCancel,null);
    this.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jScrollPane1, null);
    this.add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(jPanel9, BorderLayout.WEST);
    jPanel6.add(jPanel8, BorderLayout.CENTER);
    jPanel8.add(jPanel10, BorderLayout.CENTER);
    jPanel10.add(txtCustName, null);
    jPanel10.add(txtAddress1, null);
    jPanel10.add(txtRowsLimit, null);
    jPanel8.add(jPanel11,  BorderLayout.WEST);
    jPanel11.add(jLabel1, null);
    jPanel11.add(jLabel2, null);
    jPanel11.add(jLabel3, null);
    jPanel6.add(jPanel7,  BorderLayout.EAST);
    jScrollPane1.getViewport().add(table, null);
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_SUPPLIER_SETUP);
    resetDat();
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    if (txtRowsLimit.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1001_EnterNumberOfRow"));
      return;
    }

    rowsNum = Integer.parseInt(txtRowsLimit.getText());
    if (rowsNum >= 50){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS032_RowsTooLong"));
      return;
    }
    searchData(rowsNum);
  }
  // Search Data
  void searchData(int rowsNum){
//    table.setData(suppBusObj.getData(txtCustName.getText(), txtAddress1.getText(), rowsNum ,DAL),check,1);
    table.setColor(Color.lightGray, Color.white);
    table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
    //table.setAutoscrolls(true);
//    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    table.setColumnWidth(0,30);
    table.setColumnWidth(1,80);
    table.setColumnWidth(2,200);
    table.setColumnWidth(3,200);
    table.setColumnWidth(4,150);
    table.setColumnWidth(5,150);
    table.setColumnWidth(6,150);
    table.setColumnWidth(7,150);
    table.setColumnWidth(8,150);
    table.setColumnWidth(9,150);
    table.setColumnWidth(10,150);
    table.setColumnWidth(11,150);
    table.setColumnWidth(12,150);
    table.setColumnWidth(13,150);
    table.setColumnWidth(14,150);
    table.setColumnWidth(15,150);
    table.setColumnWidth(16,150);
    table.setColumnWidth(17,150);
    table.setColumnWidth(18,150);
    table.setColumnWidth(19,150);
    table.setColumnWidth(20,150);
    table.setHeaderName(lang.getString("SupplierID") ,1);
    table.setHeaderName(lang.getString("SupplierName") ,2);
    table.setHeaderName(lang.getString("ContactName") ,3);
    table.setHeaderName(lang.getString("ContactPhone") ,4);
    table.setHeaderName(lang.getString("Fax") ,5);
    table.setHeaderName(lang.getString("Pager") ,6);
    table.setHeaderName(lang.getString("Status") ,7);
    table.setHeaderName(lang.getString("SuppCurrCde") ,8);
    table.setHeaderName(lang.getString("RecdLoadDate"),9);
    table.setHeaderName(lang.getString("RecdLastUpdateDate"),10);
    table.setHeaderName(lang.getString("RecdCurrFlag"),11);
    table.setHeaderName(lang.getString("RecdClosedDate"),12);
    table.setHeaderName(lang.getString("ReturnAllowInd"),13);
    table.setHeaderName(lang.getString("ReturnAuthReq"),14);
    table.setHeaderName(lang.getString("ReturnMinDolAmt"),15);
    table.setHeaderName(lang.getString("ReturnCourier"),16);
    table.setHeaderName(lang.getString("FreightChargeInd"),17);
    table.setHeaderName(lang.getString("VatReason"),18);
    table.setHeaderName(lang.getString("InvcPayLoc"),19);
    table.setHeaderName(lang.getString("InvcReceiveLoc"),20);
    table.setRowHeight(30);

        }

  // set parent panel for come back
  void setParentScreen(int parentScreen){
    this.parentScreen=parentScreen;
  }
  void resetDat(){
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
  }
  void btnDelete_actionPerformed(ActionEvent e) {
    int i = 1;
    if (checkTable(table)){
      i = JOptionPane.showConfirmDialog(this, lang.getString("MS1009_Sure")+"?", lang.getString("TT001"),
                                            JOptionPane.YES_NO_OPTION);
    }
    if (i==0){
      for (int j = 0; j < table.getRowCount(); j++) {
        //Xoa du lieu trong Database
        BJCheckBox check = (BJCheckBox) table.getValueAt(j, 0);
        if (check.isSelected()) {
          try{
//            System.out.println("Delete from btm_im_supplier where supp_id ='" +
//                              check.getValue() + "'");
            DAL.executeUpdate("Delete from btm_im_supplier where supp_id ='" +
                              check.getValue() + "'", 1);
          }catch(Exception ex){
            ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS303_CannotDelete") + " " + check.getValue() +
                                          lang.getString("MS304_CannotDeleteReason"));
            check.setSelected(false);
            table.selectAll();
            table.clearSelection();
            return;
          }
        }
      }
      searchData(rowsNum);
    }
  }
  //check table
  boolean checkTable(BJTable table1){
    for (int i = 0; i<table.getRowCount(); i++){
      BJCheckBox check = (BJCheckBox) table1.getValueAt(i,0);
      if (check.isSelected()){
        return true;
      }
    }
    return false;
  }

  void btnModify_actionPerformed(ActionEvent e) {
    if (table.getSelectedRow() != -1){
      frmMain.showSupplierModify(  table.getValueAt(table.getSelectedRow(),
          1).toString());
      frmMain.showScreen(Constant.SCRS_SUPPLIER_MODIFY);
    }
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      frmMain.showSupplierModify(  table.getValueAt(table.getSelectedRow(),
          1).toString());
      frmMain.showScreen(Constant.SCRS_SUPPLIER_MODIFY);
    }
  }

  void btnDone_actionPerformed(ActionEvent e) {
      frmMain.showScreen(parentScreen);
      if(parentScreen==Constant.SCRS_ITEM_SETUP){
          if (table.getRowCount() > 0) {
              frmMain.pnlItemSetup.setSelectItem(table.getValueAt(
                      table.getSelectedRow(), 0).toString());
          }
//      }else if(pnlParent.equals("pnlItemSetup")){
//          if (table.getRowCount() > 0) {
//              frmMain.pnlItemSetup.setSelectItem(table.getValueAt(
//                      table.getSelectedRow(), 0).toString());
//          }

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

   // ESCAPE
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

        if (identifier.intValue() == KeyEvent.VK_F1) {
          btnDone.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F2) {
          btnSearch.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F3) {
          btnDelete.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F4) {
          btnModify.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F5 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
      }
    }

  void table_mousePressed(MouseEvent e) {
//    btnDone.requestFocus();
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnSearch.doClick();
               }
  }
}

class PanelSupplierSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelSupplierSearch adaptee;

  PanelSupplierSearch_btnCancel_actionAdapter(PanelSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelSupplierSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelSupplierSearch adaptee;

  PanelSupplierSearch_btnSearch_actionAdapter(PanelSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelSupplierSearch_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelSupplierSearch adaptee;

  PanelSupplierSearch_btnDelete_actionAdapter(PanelSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelSupplierSearch_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelSupplierSearch adaptee;

  PanelSupplierSearch_btnModify_actionAdapter(PanelSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelSupplierSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelSupplierSearch adaptee;

  PanelSupplierSearch_table_mouseAdapter(PanelSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelSupplierSearch_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelSupplierSearch adaptee;

  PanelSupplierSearch_btnDone_actionAdapter(PanelSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelSupplierSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierSearch adaptee;

  PanelSupplierSearch_table_keyAdapter(PanelSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
