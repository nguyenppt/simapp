//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//
















































package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import java.sql.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//not use now
public class PanelPrdHierTypeSearch extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  DataAccessLayer DAL;
  Utils ut = new Utils();
  HierBusObj hierBusObj = new HierBusObj();
  BJCheckBox check = new BJCheckBox();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true);
  BorderLayout borderLayout2 = new BorderLayout();
  BJPanel jPanel4 = new BJPanel(Constant.PANEL_TYPE_HEADER);
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
  JTextField txtTypeId = new JTextField();
  JTextField txtName = new JTextField();
  JTextField txtRowsLimit = new JTextField();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel12 = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  int rowsNum = 5;
    FlowLayout flowLayout3 = new FlowLayout();
  public PanelPrdHierTypeSearch(FrameMainSim frmMain) {
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
//        System.out.println("N13");
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    jPanel1.setMinimumSize(new Dimension(10, 10));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout2);
    jPanel2.setPreferredSize(new Dimension(800, 420));
    jPanel2.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(800, 410));
//    jPanel3.setPreferredSize(new Dimension(230, 50));
    jPanel4.setPreferredSize(new Dimension(200, 50));
    jPanel5.setPreferredSize(new Dimension(320, 50));
        jPanel5.setLayout(flowLayout3);
    btnSearch.setToolTipText("Search (F1)");
    btnSearch.setText("Search (F1)");
    btnSearch.addActionListener(new PanelPrdHierTypeSearch_btnSearch_actionAdapter(this));
    txtSearch.setPreferredSize(new Dimension(120, 20));
    btnDelete.setToolTipText("Delete (F2)");
    btnDelete.setText("Delete (F2)");
    btnDelete.addActionListener(new PanelPrdHierTypeSearch_btnDelete_actionAdapter(this));
    btnModify.setToolTipText("Modify (F3)");
    btnModify.setText("Modify (F3)");
    btnModify.addActionListener(new PanelPrdHierTypeSearch_btnModify_actionAdapter(this));
    btnCancel.setToolTipText("Cancel (F4)");
    btnCancel.setText("Cancel (F4)");
    btnCancel.addActionListener(new PanelPrdHierTypeSearch_btnCancel_actionAdapter(this));

    jPanel6.setPreferredSize(new Dimension(800, 130));
    jPanel6.setLayout(borderLayout3);
    jPanel9.setPreferredSize(new Dimension(150, 10));
    jPanel7.setPreferredSize(new Dimension(150, 10));
    jPanel8.setLayout(borderLayout4);
    jPanel11.setPreferredSize(new Dimension(120, 10));
    jPanel11.setLayout(flowLayout2);
    jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setText("Type ID:");
    jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText("Name:");
    jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setText("Rows limit:");
    txtTypeId.setFont(new java.awt.Font("Dialog", 1, 11));
    txtTypeId.setPreferredSize(new Dimension(180, 20));
    txtTypeId.setText("");
    txtTypeId.setBounds(new Rectangle(-8, 5, 180, 20));
    txtName.setFont(new java.awt.Font("Dialog", 1, 11));
    txtName.setPreferredSize(new Dimension(180, 20));
    txtName.setText("");
    txtName.setBounds(new Rectangle(-8, 30, 180, 20));
    txtRowsLimit.setFont(new java.awt.Font("Dialog", 1, 11));
    txtRowsLimit.setPreferredSize(new Dimension(46, 20));
    txtRowsLimit.setText("5");
    txtRowsLimit.setBounds(new Rectangle(-8, 55, 46, 20));
    jPanel10.setPreferredSize(new Dimension(200, 30));
    jPanel10.setLayout(null);
    jPanel12.setPreferredSize(new Dimension(180, 10));
    jPanel8.setPreferredSize(new Dimension(500, 30));
    flowLayout3.setAlignment(FlowLayout.LEFT);
    table.addMouseListener(new PanelPrdHierTypeSearch_table_mouseAdapter(this));
    table.addKeyListener(new PanelPrdHierTypeSearch_table_keyAdapter(this));
    this.add(jPanel1, BorderLayout.NORTH);
//    jPanel1.add(jPanel3, BorderLayout.WEST);
//    jPanel3.add(txtSearch, null);
//    jPanel3.add(btnSearch, null);
    jPanel1.add(jPanel5,BorderLayout.CENTER);
    jPanel5.add(btnSearch,null);
    jPanel5.add(btnDelete,null);
    jPanel5.add(btnModify,null);
    jPanel5.add(btnCancel,null);
    jPanel1.add(jPanel4, BorderLayout.EAST);
    this.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    this.add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(jPanel9, BorderLayout.WEST);
    jPanel6.add(jPanel8, BorderLayout.CENTER);
    jPanel8.add(jPanel10, BorderLayout.CENTER);
    jPanel10.add(txtTypeId, null);
    jPanel10.add(txtName, null);
    jPanel10.add(txtRowsLimit, null);
    jPanel8.add(jPanel11,  BorderLayout.WEST);
    jPanel11.add(jLabel1, null);
    jPanel11.add(jLabel2, null);
    jPanel11.add(jLabel3, null);
    jPanel8.add(jPanel12,  BorderLayout.EAST);
    jPanel6.add(jPanel7,  BorderLayout.EAST);

  }

  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.pnlPrdHierTypeSetup.showData();
    frmMain.showScreen(Constant.SCRS_PRD_HIER_TYPE_SETUP);
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    if (txtRowsLimit.getText().equals("")){
      ut.showMessage(frmMain,"Warning!", "You must enter the number of rows that you want to show");
      return;
    }
    rowsNum = Integer.parseInt(txtRowsLimit.getText());
    if (rowsNum >= 50){
      ut.showMessage(frmMain,"Warning!", "The number of rows is too long! it must be less than 50");
      return;
    }
    searchData(rowsNum);
  }
  // Search Data
  void searchData(int rowsNum){
    table.setData(hierBusObj.getPrdHierTypeData(this.txtTypeId.getText().toString(), this.txtName.getText().toString(),
                                               rowsNum ,DAL),check,1);
//    table.setColor(Color.lightGray, Color.white);
    table.setColumnWidth(0,30);
    table.setColumnWidth(1,120);
    table.setColumnWidth(2,120);
    table.setColumnWidth(3,120);
    table.setColumnWidth(4,400);
    table.setHeaderName("Type Id",1);
    table.setHeaderName("Higher Type Id",2);
    table.setHeaderName("Name",3);
    table.setHeaderName("Description",4);
    table.setHeaderName("",0);

//    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
//    table.setHeaderName("COMMENT", 15);
//    table.setHeaderName("ABB NAME",3);
    table.setRowHeight(30);
  }
  void btnDelete_actionPerformed(ActionEvent e) {
    int i = 1;
    for (int j = 0; j < table.getRowCount(); j++) {
      BJCheckBox check = (BJCheckBox) table.getValueAt(j, 0);
      if (check.isSelected()) {
         String typeId = check.getValue();
         if (hierBusObj.checkEndTypeId(typeId, DAL)) { //the last then allow to delete
           i = JOptionPane.showConfirmDialog(this,
                                          "Do you want to delete this hier type ?", "Warning",
                                          JOptionPane.YES_NO_OPTION);
           if (i == 0) { //yes
              hierBusObj.deletePrdHierTypeData(typeId, DAL);
           }
         }else {
              ut.showMessage(frmMain,"Alert","You should choose last of product hier type !");
              return;
          }
      } //end if of isSelected
    } //end for
    searchData(rowsNum);
  }//end method

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
//    int row = table.getSelectedRow();
//    if( row >=0){
//      frmMain.showPrdHierSetupModify( (String) table.getValueAt(table.
//          getSelectedRow(), 1));
//      frmMain.pnlPrdHierSetupModify.resetData();
//      frmMain.showScreen(Constant.SCRS_PRD_HIER_SETUP_MODIFY);
//    }
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
        btnSearch.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2) {
        btnDelete.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {
        btnModify.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F4 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void table_mousePressed(MouseEvent e) {
//    btnSearch.requestFocus();
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnDelete.doClick();
               }
  }

}

class PanelPrdHierTypeSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierTypeSearch adaptee;

  PanelPrdHierTypeSearch_btnCancel_actionAdapter(PanelPrdHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelPrdHierTypeSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierTypeSearch adaptee;

  PanelPrdHierTypeSearch_btnSearch_actionAdapter(PanelPrdHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelPrdHierTypeSearch_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierTypeSearch adaptee;

  PanelPrdHierTypeSearch_btnDelete_actionAdapter(PanelPrdHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelPrdHierTypeSearch_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierTypeSearch adaptee;

  PanelPrdHierTypeSearch_btnModify_actionAdapter(PanelPrdHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelPrdHierTypeSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelPrdHierTypeSearch adaptee;

  PanelPrdHierTypeSearch_table_mouseAdapter(PanelPrdHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelPrdHierTypeSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPrdHierTypeSearch adaptee;

  PanelPrdHierTypeSearch_table_keyAdapter(PanelPrdHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
