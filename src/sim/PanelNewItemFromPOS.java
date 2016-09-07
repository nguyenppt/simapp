







//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//

























package sim;

import java.io.*;
import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import btm.swing.*;
import common.*;
import btm.attr.*;
import java.awt.print.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Vinh.Le
 * @version 1.0
 */

public class PanelNewItemFromPOS
    extends JPanel {
  //Column Order of Sale Item Table
  private static final int COL_STORE_ID = 0;
  private static final int COL_ITEM_ID = 1; //
  private static final int COL_ART_NO = 2;
  private static final int COL_ITEM_DESC = 3; //
  private static final int COL_SIZE = 4;
  private static final int COL_UNIT_PRICE = 5; //
  private static final int COL_POS_CREATE_DAY = 6;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);


  //define for panel
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;
  Statement stmt = null;

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm, true) {
    public Class getColumnClass(int col) {
      switch (col) {
        case COL_UNIT_PRICE:
          return Long.class;
        default:
          return Object.class;
      }
    }

    public boolean isCellEditable(int row, int col) {
      return false;
    }
  };

  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BJButton btnCancel = new BJButton();
  BJButton btnPrint = new BJButton();
  JLabel lbTitle = new JLabel();
  DataAccessLayer DAL = new DataAccessLayer();
  Utils ut = new Utils();
  String path = "";
  JPanel jPanel5 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JLabel lbDate = new JLabel();
  JTextField txtDatePosNewItem = new JTextField();
  JButton btnSearchDate = new JButton();

  public PanelNewItemFromPOS() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public PanelNewItemFromPOS(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public PanelNewItemFromPOS(FrameMainSim frmMain, CardLayout crdLayout,
                             JPanel parent) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      this.cardLayout = crdLayout;
      this.parent = parent;
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
//        System.out.println("N11");
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout3);
    jPanel2.setLayout(borderLayout2);
    btnCancel.setToolTipText("Exit (F12 or ESC)");
    btnCancel.setSelectedIcon(null);
    btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelNewItemFromPOS_btnCancel_actionAdapter(this));
    btnPrint.setToolTipText("Print (F9)");
    btnPrint.setText("Print (F9)");
    btnPrint.addActionListener(new PanelNewItemFromPOS_btnPrint_actionAdapter(this));
    lbTitle.setFont(new java.awt.Font("Dialog", 1, 15));
    lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lbTitle.setText("");
    jPanel4.setBackground(new Color(121, 152, 198));
    jPanel3.setBackground(Color.lightGray);
    jPanel3.setLayout(borderLayout4);
    lbDate.setFont(new java.awt.Font("SansSerif", 1, 12));
    lbDate.setText("The new Item list on :");
    txtDatePosNewItem.setFont(new java.awt.Font("SansSerif", 1, 12));
    txtDatePosNewItem.setPreferredSize(new Dimension(120, 21));
    txtDatePosNewItem.setText(ut.getSystemDate(1));
    txtDatePosNewItem.addKeyListener(new
        PanelNewItemFromPOS_txtDatePosNewItem_keyAdapter(this));
    btnSearchDate.setFont(new java.awt.Font("SansSerif", 1, 12));
    btnSearchDate.setPreferredSize(new Dimension(35, 22));
    btnSearchDate.setText("...");
    btnSearchDate.addActionListener(new
        PanelNewItemFromPOS_btnSearchDate_actionAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(454, 380));
    jPanel5.setPreferredSize(new Dimension(294, 40));
    table.addKeyListener(new PanelNewItemFromPOS_table_keyAdapter(this));
    this.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel4, BorderLayout.NORTH);
    jPanel4.add(btnPrint, null);
    jPanel4.add(btnCancel, null);
    jPanel1.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jPanel5, BorderLayout.NORTH);
    jPanel5.add(lbDate, null);
    jPanel5.add(txtDatePosNewItem, null);
    jPanel5.add(btnSearchDate, null);
    jPanel3.add(lbTitle, BorderLayout.CENTER);
    this.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(table, null);

    //define for table
    String[] columnNames = new String[] {
        "Store ID", "Item ID","UPC", "Item Name","Size", "Unit Price",
        "POS Created Date"};
    dm.setDataVector(new Object[][] {}
                     , columnNames);
    table.getColumn("Store ID").setMaxWidth(100);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 13));
    table.getColumn("Store ID").setMinWidth(100);
    table.getColumn("Item ID").setPreferredWidth(160);
    table.getColumn("UPC").setPreferredWidth(80);
    table.getColumn("Item Name").setPreferredWidth(250);
    table.getColumn("Size").setPreferredWidth(100);
    table.getColumn("Unit Price").setPreferredWidth(120);
    table.getColumn("POS Created Date").setPreferredWidth(150);
    table.setRowHeight(30);
//    initData(ut.getSystemDate(1));
    table.setColor(Color.lightGray, Color.white);
  }

  public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_PICK_LIST);
    btnPrint.setEnabled(er.getReport());
  }




  void initScreen() {
    initData(ut.getSystemDate(1));
  }

  void initData(String date) {
    //combine files
    date = ut.changeFormatDate(date);
    String pathTo = "./Batch/Backup/Import/SimNewItem_" + date + ".txt";
    String sqlStr = "Select STORE_ID From BTM_IM_STORE";
//    System.out.println(sqlStr);
    ResultSet rs = null;
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr, stmt);
      while (rs.next()) {
        String pathFrom = "./Batch/Import/NewItem_" +
            rs.getString("STORE_ID") + date + ".txt";
        boolean exists = (new File(pathFrom)).exists();
        if (exists) { // File exists
          ut.combineFile(pathFrom, pathTo);
          deleteFile(pathFrom);
        }
      }
      //show Data
      showData(pathTo);
      rs.close();
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void deleteFile(String filename) {
    boolean success = (new File(filename)).delete();
    if (!success) {
      // Deletion failed
    }
  }

  void showData(String filename) throws Exception {
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    try {
      if(ut.isExistFile(filename)){
        RandomAccessFile in = new RandomAccessFile(filename, "r");
        while (in.getFilePointer() < in.length()) {
          String strLine = in.readLine();
          String[] rowData = strLine.split(";");
          dm.addRow(rowData);
        }
        in.close();
      }else{
         ut.showMessage(frmMain, "Warning!", "The file does not exist.");
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    txtDatePosNewItem.setText(ut.getSystemDate(1));
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0002"));
  }

  void btnPrint_actionPerformed(ActionEvent e) {}

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
      if (identifier.intValue() == KeyEvent.VK_F9) {
        btnPrint.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void btnSearchDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnSearchDate.getX() + posXFrame;
    posY = this.btnSearchDate.getY() + posYFrame + 75;
    TimeDlg posNewItemDate = new TimeDlg(null);
    posNewItemDate.setTitle(lang.getString("ChooseReceiptDate"));
    posNewItemDate.setResizable(false);
    posNewItemDate.setLocation(posX, posY);
    posNewItemDate.setVisible(true);
    if (posNewItemDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          ut.DATE_FORMAT1);
      String date = fd.format(posNewItemDate.getDate());
      this.txtDatePosNewItem.setText(date);
      initData(date);
    }

  }

  private String formatDateStandard(String sDate) {
    String date = "";
    String arrDate[] = sDate.split("/");
    if (arrDate[0].length() < 2) {
      arrDate[0] = "0" + arrDate[0];
    }
    if (arrDate[1].length() < 2) {
      arrDate[1] = "0" + arrDate[1];
    }
    date = arrDate[0] + arrDate[1] + arrDate[2];
    return date;
  }

  void txtDatePosNewItem_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (ut.checkDate(txtDatePosNewItem.getText(), "/")) {
        String date = formatDateStandard(txtDatePosNewItem.getText().trim());
        initData(date);
      }
      else {
        ut.showMessage(frmMain, "Warning!", "Please enter date type.");
        while (dm.getRowCount() > 0) {
          dm.removeRow(0);
        }
      }
    }

  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F12) {
                 btnCancel.doClick();
               }
  }

}

class PanelNewItemFromPOS_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelNewItemFromPOS adaptee;

  PanelNewItemFromPOS_btnCancel_actionAdapter(PanelNewItemFromPOS adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelNewItemFromPOS_btnPrint_actionAdapter
    implements java.awt.event.ActionListener {
  PanelNewItemFromPOS adaptee;

  PanelNewItemFromPOS_btnPrint_actionAdapter(PanelNewItemFromPOS adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrint_actionPerformed(e);
  }
}

class PanelNewItemFromPOS_btnSearchDate_actionAdapter
    implements java.awt.event.ActionListener {
  PanelNewItemFromPOS adaptee;

  PanelNewItemFromPOS_btnSearchDate_actionAdapter(PanelNewItemFromPOS adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchDate_actionPerformed(e);
  }
}

class PanelNewItemFromPOS_txtDatePosNewItem_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelNewItemFromPOS adaptee;

  PanelNewItemFromPOS_txtDatePosNewItem_keyAdapter(PanelNewItemFromPOS adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.txtDatePosNewItem_keyPressed(e);
  }
}

class PanelNewItemFromPOS_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelNewItemFromPOS adaptee;

  PanelNewItemFromPOS_table_keyAdapter(PanelNewItemFromPOS adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
