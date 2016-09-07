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
import java.util.ResourceBundle;
/**
 * <p>Title: </p>
 * <p>Description: Admin -> Role -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelAdminRoleSearch extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  DataAccessLayer DAL;
  Statement stmt = null;
  Utils ut = new Utils();
  AdminBusObj adminBusObj = new AdminBusObj();
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
  JTextField txtRoleId = new JTextField();
  JTextField txtName = new JTextField();
  JTextField txtRowsLimit = new JTextField();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel12 = new JPanel();
  int rowsNum = 5;
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);
  public PanelAdminRoleSearch(FrameMainSim frmMain) {
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
    jPanel2.setPreferredSize(new Dimension(800, 380));
    jPanel2.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(800, 410));
//    jPanel3.setPreferredSize(new Dimension(230, 50));
    jPanel5.setPreferredSize(new Dimension(320, 50));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText(lang.getString("Search")+" (F3)");
    btnSearch.addActionListener(new PanelAdminRoleSearch_btnSearch_actionAdapter(this));
    txtSearch.setPreferredSize(new Dimension(120, 20));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelAdminRoleSearch_btnCancel_actionAdapter(this));

    jPanel6.setPreferredSize(new Dimension(800, 130));
    jPanel6.setLayout(borderLayout3);
    jPanel9.setPreferredSize(new Dimension(150, 10));
    jPanel7.setPreferredSize(new Dimension(150, 10));
    jPanel8.setLayout(borderLayout4);
    jPanel11.setPreferredSize(new Dimension(120, 10));
    jPanel11.setLayout(null);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setText(lang.getString("RoleID")+":");
    jLabel1.setBounds(new Rectangle(-20, 30, 120, 20));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText(lang.getString("Description")+":");
    jLabel2.setBounds(new Rectangle(-20, 5, 120, 20));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setText(lang.getString("RowsLimit")+":");
    jLabel3.setBounds(new Rectangle(-20, 55, 136, 20));
    txtRoleId.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRoleId.setPreferredSize(new Dimension(180, 20));
    txtRoleId.setText("");
    txtRoleId.setBounds(new Rectangle(0, 5, 180, 20));
        txtRoleId.addKeyListener(new PanelAdminRoleSearch_txtRoleId_keyAdapter(this));
    txtName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtName.setPreferredSize(new Dimension(180, 20));
    txtName.setText("");
    txtName.setBounds(new Rectangle(0, 32, 180, 20));
        txtName.addKeyListener(new PanelAdminRoleSearch_txtName_keyAdapter(this));
    txtRowsLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRowsLimit.setPreferredSize(new Dimension(46, 20));
    txtRowsLimit.setText("5");
    txtRowsLimit.setBounds(new Rectangle(0, 58, 46, 20));
    jPanel10.setPreferredSize(new Dimension(200, 30));
    jPanel10.setLayout(null);
    jPanel12.setPreferredSize(new Dimension(180, 10));
    jPanel8.setPreferredSize(new Dimension(500, 30));
    table.addMouseListener(new PanelAdminRoleSearch_table_mouseAdapter(this));
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.addKeyListener(new PanelAdminRoleSearch_table_keyAdapter(this));
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    this.add(jPanel1, BorderLayout.NORTH);
//    jPanel1.add(jPanel3, BorderLayout.WEST);
//    jPanel3.add(txtSearch, null);
//    jPanel3.add(btnSearch, null);
    jPanel1.add(jPanel5,BorderLayout.CENTER);
    jPanel5.add(btnSearch,null);
    jPanel5.add(btnCancel,null);
    this.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jScrollPane1, null);
    this.add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(jPanel9, BorderLayout.WEST);
    jPanel6.add(jPanel8, BorderLayout.CENTER);
    jPanel8.add(jPanel10, BorderLayout.CENTER);
    jPanel10.add(txtRoleId, null);
    jPanel10.add(txtName, null);
    jPanel10.add(txtRowsLimit, null);
    jPanel8.add(jPanel11,  BorderLayout.WEST);
    jPanel11.add(jLabel2, null);
    jPanel11.add(jLabel1, null);
    jPanel11.add(jLabel3, null);
    jPanel8.add(jPanel12,  BorderLayout.EAST);
    jPanel6.add(jPanel7,  BorderLayout.EAST);
    jScrollPane1.getViewport().add(table, null);
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_ADMIN_ROLE);
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    if (txtRowsLimit.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS031_RowsIsNum"));
      return;
    }
    rowsNum = Integer.parseInt(txtRowsLimit.getText());
    if (rowsNum >= 50){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS032_RowsTooLong"));
      return;
    }
    showData(rowsNum);
  }
  // Search Data
  void showData(int rowsNum){
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);

      table.setData(adminBusObj.getAdminRoleData(this.txtRoleId.getText().
                                                 toString(),
                                                 this.txtName.getText().
                                                 toString(),
                                                 rowsNum, DAL,stmt), check, 1);
      stmt.close();
    }
    catch(Exception ex){};
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.setColor(Color.lightGray, Color.white);
    table.setColumnWidth(0,30);
    table.setColumnWidth(1,120);
    table.setColumnWidth(2,400);
    table.setRowHeight(30);
    table.setHeaderName(lang.getString("RoleID"),1);
    table.setHeaderName(lang.getString("RoleDesc"),2);
    table.setHeaderName("Which App",3);
      }
  void btnDelete_actionPerformed(ActionEvent e) {
    int i = 1;
    for (int j = 0; j < table.getRowCount(); j++) {
      BJCheckBox check = (BJCheckBox) table.getValueAt(j, 0);
      if (check.isSelected()) {
         String roleId = check.getValue();
         if (adminBusObj.checkRole_AllowDel(roleId, DAL)) { //the last then allow to delete
           int choose = ut.showMessage(frmMain,lang.getString("TT008"),lang.getString("MS1066_WantDeleteRole"),3,1);
           if (choose==DialogMessage.YES_VALUE) {
              adminBusObj.deleteRole(roleId, DAL);
              showData(rowsNum);
           }
         }else {
           ut.showMessage(frmMain,lang.getString("TT008"),lang.getString("MS1067_ShouldChooseRole"));
           return;
      }
    }
    } //end for
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
    if (this.table.getSelectedRow()==-1) {
     ut.showMessage(frmMain,lang.getString("TT008"),lang.getString("MS1002_YouShouldChooseData"));
      return;
    }

//    frmMain.showAdminRoleModify((String)table.getValueAt(table.getSelectedRow(),1));
    frmMain.showScreen(Constant.SCRS_ADMIN_ROLE_MODIFY);
  }

    void txtRoleId_keyTyped(KeyEvent e) {
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
class KeyAction extends AbstractAction {

    private Integer identifier = null;

    public KeyAction(Integer identifier) {
      this.identifier = identifier;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {

      if (identifier.intValue() == KeyEvent.VK_F3) {
        btnSearch.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void table_mousePressed(MouseEvent e) {
//    btnSearch.requestFocus();
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnCancel.doClick();
               }
  }


}

class PanelAdminRoleSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRoleSearch adaptee;

  PanelAdminRoleSearch_btnCancel_actionAdapter(PanelAdminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelAdminRoleSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelAdminRoleSearch adaptee;

  PanelAdminRoleSearch_btnSearch_actionAdapter(PanelAdminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelAdminRoleSearch_txtRoleId_keyAdapter extends java.awt.event.KeyAdapter {
    PanelAdminRoleSearch adaptee;

    PanelAdminRoleSearch_txtRoleId_keyAdapter(PanelAdminRoleSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtRoleId_keyTyped(e);
    }
}

class PanelAdminRoleSearch_txtName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelAdminRoleSearch adaptee;

    PanelAdminRoleSearch_txtName_keyAdapter(PanelAdminRoleSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtName_keyTyped(e);
    }
}

class PanelAdminRoleSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAdminRoleSearch adaptee;

  PanelAdminRoleSearch_table_mouseAdapter(PanelAdminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelAdminRoleSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAdminRoleSearch adaptee;

  PanelAdminRoleSearch_table_keyAdapter(PanelAdminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
