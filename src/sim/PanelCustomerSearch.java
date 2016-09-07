package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Customer Setup -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelCustomerSearch extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  DataAccessLayer DAL;
  Statement stmt = null;
  Utils ut = new Utils();
  CustomerBusObj cusBusObj = new CustomerBusObj();
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
  JTextField txtCustName = new JTextField();
  JTextField txtAddress1 = new JTextField();
  JTextField txtRowsLimit = new JTextField();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel12 = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  int rowsNum = 5;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelCustomerSearch(FrameMainSim frmMain) {
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
    jPanel2.setPreferredSize(new Dimension(800, 420));
    jPanel2.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(800, 410));
//    jPanel3.setPreferredSize(new Dimension(230, 50));
    jPanel4.setPreferredSize(new Dimension(200, 50));
    jPanel5.setPreferredSize(new Dimension(320, 50));
//    btnSearch.setPreferredSize(new Dimension(80, 35));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setToolTipText(lang.getString("Search")+" (F1)");
    btnSearch.setText(lang.getString("Search")+" (F1)");
    btnSearch.addActionListener(new PanelCustomerSearch_btnSearch_actionAdapter(this));
    txtSearch.setPreferredSize(new Dimension(120, 20));
//    btnDelete.setPreferredSize(new Dimension(80, 35));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F2)");
    btnDelete.setText(lang.getString("Delete")+" (F2)");
    btnDelete.addActionListener(new PanelCustomerSearch_btnDelete_actionAdapter(this));
//    btnModify.setPreferredSize(new Dimension(80, 35));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setToolTipText(lang.getString("Modify")+" (F3)");
    btnModify.setText(lang.getString("Modify")+" (F3)");
    btnModify.addActionListener(new PanelCustomerSearch_btnModify_actionAdapter(this));
//    btnCancel.setPreferredSize(new Dimension(80, 35));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F4)");
    btnCancel.setText(lang.getString("Cancel")+" (F4)");
    btnCancel.addActionListener(new PanelCustomerSearch_btnCancel_actionAdapter(this));

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
    jLabel2.setText(lang.getString("Address")+":");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setText(lang.getString("RowsLimit")+":");
    txtCustName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtCustName.setPreferredSize(new Dimension(180, 20));
    txtCustName.setText("");
    txtCustName.setBounds(new Rectangle(31, 5, 180, 20));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtAddress1.setPreferredSize(new Dimension(180, 20));
    txtAddress1.setText("");
    txtAddress1.setBounds(new Rectangle(31, 30, 180, 20));
    txtRowsLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRowsLimit.setPreferredSize(new Dimension(40, 20));
    txtRowsLimit.setText(DAL.getSearchLimit());
    txtRowsLimit.setBounds(new Rectangle(31, 56, 50, 21));
    jPanel10.setPreferredSize(new Dimension(200, 30));
    jPanel10.setLayout(null);
    jPanel12.setPreferredSize(new Dimension(180, 10));
    jPanel8.setPreferredSize(new Dimension(500, 30));
    table.addMouseListener(new PanelCustomerSearch_table_mouseAdapter(this));
    table.addKeyListener(new PanelCustomerSearch_table_keyAdapter(this));
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
    jPanel8.add(jPanel12,  BorderLayout.EAST);
    jPanel6.add(jPanel7,  BorderLayout.EAST);
    jScrollPane1.getViewport().add(table, null);
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_CUSTOMER_SETUP);
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
    searchData(rowsNum);
  }
  // Search Data
  void searchData(int rowsNum){
    try{
       stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       table.setData(cusBusObj.getData(txtCustName.getText(), txtAddress1.getText(), rowsNum ,DAL,stmt),check,1);
       stmt.close();
    }
    catch(Exception ex){};

    table.setColor(Color.lightGray, Color.white);
    table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
    table.setColumnWidth(0,30);
    table.setColumnWidth(1,80);
    table.setColumnWidth(2,200);
    table.setColumnWidth(3,100);
    table.setColumnWidth(4,200);
    table.setColumnWidth(5,200);
    table.setColumnWidth(6,100);
    table.setColumnWidth(7,100);
    table.setColumnWidth(8,100);
    table.setColumnWidth(9,150);
    table.setColumnWidth(10,100);
    table.setColumnWidth(11,100);
    table.setColumnWidth(12,100);
    table.setColumnWidth(13,100);
    table.setColumnWidth(14,100);
    table.setColumnWidth(15,300);
    table.setColumnWidth(16,150);
    table.setHeaderName("COMMENT", 15);
    table.setHeaderName("ABB NAME",3);
    table.setRowHeight(30);
  }
  void btnDelete_actionPerformed(ActionEvent e) {
    int i = 1;
    if (checkTable(table)){
      i = JOptionPane.showConfirmDialog(this, lang.getString("MS1009_Sure"), lang.getString("TT001"),
                                            JOptionPane.YES_NO_OPTION);
    }
    if (i==0){
      for (int j = 0; j < table.getRowCount(); j++) {
        //Xoa du lieu trong Database
        BJCheckBox check = (BJCheckBox) table.getValueAt(j, 0);
        if (check.isSelected()) {
//          System.out.println("Delete from btm_im_customer where cust_id ='" + check.getValue() + "'");
          DAL.executeUpdate("Delete from btm_im_customer where cust_id ='" + check.getValue() + "'");
        }
      }
    }
    searchData(rowsNum);
  }
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
    if (table.getSelectedRow() != -1 ){
      frmMain.showCustomerModify(  table.getValueAt(table.getSelectedRow(),
          1).toString());
      frmMain.showScreen(Constant.SCRS_CUSTOMER_MODIFY);
    }
  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      frmMain.showCustomerModify(table.getValueAt(table.getSelectedRow(),1).toString());
      frmMain.showScreen(Constant.SCRS_CUSTOMER_MODIFY);
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

class PanelCustomerSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSearch adaptee;

  PanelCustomerSearch_btnCancel_actionAdapter(PanelCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelCustomerSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSearch adaptee;

  PanelCustomerSearch_btnSearch_actionAdapter(PanelCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelCustomerSearch_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSearch adaptee;

  PanelCustomerSearch_btnDelete_actionAdapter(PanelCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelCustomerSearch_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelCustomerSearch adaptee;

  PanelCustomerSearch_btnModify_actionAdapter(PanelCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelCustomerSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelCustomerSearch adaptee;

  PanelCustomerSearch_table_mouseAdapter(PanelCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelCustomerSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelCustomerSearch adaptee;

  PanelCustomerSearch_table_keyAdapter(PanelCustomerSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
