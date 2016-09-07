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
import btm.attr.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//not use now
public class PanelOrgHierTypeSearch extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  DataAccessLayer DAL;
  Utils ut = new Utils();
  HierBusObj hierBusObj = new HierBusObj();
  BJCheckBox check = new BJCheckBox();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      if (col == 16){
        return Date.class;
      }
      return Object.class;
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
  JTextField txtTypeId = new JTextField();
  JTextField txtName = new JTextField();
  JTextField txtRowsLimit = new JTextField();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel12 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  JPanel jPanel13 = new JPanel();
  int rowsNum = 5;
    FlowLayout flowLayout3 = new FlowLayout();
  public PanelOrgHierTypeSearch(FrameMainSim frmMain) {
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
//    System.out.println("N12");
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    jPanel1.setMinimumSize(new Dimension(10, 10));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout2);
    jPanel2.setPreferredSize(new Dimension(800, 420));
    jPanel2.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(800, 410));
//    jPanel3.setPreferredSize(new Dimension(230, 50));
    jPanel5.setPreferredSize(new Dimension(7503, 50));
        jPanel5.setLayout(flowLayout3);
    btnSearch.setToolTipText("Cancel (F5)");
    btnSearch.setText("Search (F1)");
    btnSearch.addActionListener(new PanelOrgHierTypeSearch_btnSearch_actionAdapter(this));
    txtSearch.setPreferredSize(new Dimension(120, 20));
    btnDelete.setToolTipText("Delete (F2)");
    btnDelete.setText("Delete (F2)");
    btnDelete.addActionListener(new PanelOrgHierTypeSearch_btnDelete_actionAdapter(this));
    btnModify.setToolTipText("Modify (F3)");
    btnModify.setText("Modify (F3)");
    btnModify.addActionListener(new PanelOrgHierTypeSearch_btnModify_actionAdapter(this));
    btnCancel.setToolTipText("Cancel (F4)");
    btnCancel.setText("Cancel (F4)");
    btnCancel.addActionListener(new PanelOrgHierTypeSearch_btnCancel_actionAdapter(this));

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
        txtTypeId.addKeyListener(new PanelOrgHierTypeSearch_txtTypeId_keyAdapter(this));
    txtName.setFont(new java.awt.Font("Dialog", 1, 11));
    txtName.setPreferredSize(new Dimension(180, 20));
    txtName.setText("");
        txtName.addKeyListener(new PanelOrgHierTypeSearch_txtName_keyAdapter(this));
    txtRowsLimit.setFont(new java.awt.Font("Dialog", 1, 11));
    txtRowsLimit.setPreferredSize(new Dimension(46, 20));
    txtRowsLimit.setText("5");
    jPanel10.setPreferredSize(new Dimension(200, 30));
    jPanel10.setLayout(flowLayout1);
    jPanel12.setPreferredSize(new Dimension(180, 10));
    jPanel8.setPreferredSize(new Dimension(500, 30));
    jPanel13.setPreferredSize(new Dimension(130, 20));
    flowLayout3.setAlignment(FlowLayout.LEFT);
        table.addMouseListener(new PanelOrgHierTypeSearch_table_mouseAdapter(this));
    table.addKeyListener(new PanelOrgHierTypeSearch_table_keyAdapter(this));
    this.add(jPanel1, BorderLayout.NORTH);
//    jPanel1.add(jPanel3, BorderLayout.WEST);
//    jPanel3.add(txtSearch, null);
//    jPanel3.add(btnSearch, null);
    jPanel1.add(jPanel5,BorderLayout.CENTER);
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
    jPanel10.add(txtTypeId, null);
    jPanel10.add(txtName, null);
    jPanel10.add(txtRowsLimit, null);
    jPanel10.add(jPanel13, null);
    jPanel8.add(jPanel11,  BorderLayout.WEST);
    jPanel11.add(jLabel1, null);
    jPanel11.add(jLabel2, null);
    jPanel11.add(jLabel3, null);
    jPanel8.add(jPanel12,  BorderLayout.EAST);
    jPanel6.add(jPanel7,  BorderLayout.EAST);
    jScrollPane1.getViewport().add(table, null);
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_ORG_HIER_TYPE_SETUP);
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
    showData(rowsNum);
  }
  // Search Data
  void showData(int rowsNum){
    table.setData(hierBusObj.getOrgHierTypeData(this.txtTypeId.getText().toString(), this.txtName.getText().toString(),
                                               rowsNum ,DAL),check,1);
//    table.setColor(Color.lightGray, Color.white);
//    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
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
         if (hierBusObj.checkEndOrgTypeId(typeId, DAL)) { //the last then allow to delete
           i = JOptionPane.showConfirmDialog(this,
                                          "Do you want to delete this hier type ?", "Warning",
                                          JOptionPane.YES_NO_OPTION);
           if (i == 0) { //yes
              hierBusObj.deleteOrgHierTypeData(typeId, DAL);
           }
         }else {
              ut.showMessage(frmMain,"Alert","You should choose the last of org hier type !");
              return;
          }
      } //end if of isSelected
    } //end for
    showData(rowsNum);
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
    if (this.table.getSelectedRow()==-1) return;
    frmMain.showOrgHierSetupModify(table.getValueAt(table.getSelectedRow(),1).toString());
    frmMain.showScreen(Constant.SCRS_ORG_HIER_SETUP_MODIFY);
  }

    void txtTypeId_keyTyped(KeyEvent e) {
//        ut.limitLenjTextField(e,txtName,OrgHierType.LEN_ORG_TYPE_NAME);
    }

    void txtName_keyTyped(KeyEvent e) {
//        ut.limitLenjTextField(e,txtName,OrgHierType.LEN_ORG_TYPE_NAME);
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

class PanelOrgHierTypeSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelOrgHierTypeSearch adaptee;

  PanelOrgHierTypeSearch_btnCancel_actionAdapter(PanelOrgHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelOrgHierTypeSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelOrgHierTypeSearch adaptee;

  PanelOrgHierTypeSearch_btnSearch_actionAdapter(PanelOrgHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelOrgHierTypeSearch_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelOrgHierTypeSearch adaptee;

  PanelOrgHierTypeSearch_btnDelete_actionAdapter(PanelOrgHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelOrgHierTypeSearch_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelOrgHierTypeSearch adaptee;

  PanelOrgHierTypeSearch_btnModify_actionAdapter(PanelOrgHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelOrgHierTypeSearch_txtTypeId_keyAdapter extends java.awt.event.KeyAdapter {
    PanelOrgHierTypeSearch adaptee;

    PanelOrgHierTypeSearch_txtTypeId_keyAdapter(PanelOrgHierTypeSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtTypeId_keyTyped(e);
    }
}

class PanelOrgHierTypeSearch_txtName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelOrgHierTypeSearch adaptee;

    PanelOrgHierTypeSearch_txtName_keyAdapter(PanelOrgHierTypeSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtName_keyTyped(e);
    }
}

class PanelOrgHierTypeSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelOrgHierTypeSearch adaptee;

  PanelOrgHierTypeSearch_table_mouseAdapter(PanelOrgHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelOrgHierTypeSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelOrgHierTypeSearch adaptee;

  PanelOrgHierTypeSearch_table_keyAdapter(PanelOrgHierTypeSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
