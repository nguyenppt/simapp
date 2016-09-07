package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import java.awt.event.*;
import btm.business.*;
import java.sql.*;
import btm.attr.*;
import java.util.*;
import common.Utils.*;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Admin -> Admin Role -> Role Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Vinh.Le
 * @version 1.0
 */

public class DialogAminRoleSearch extends JDialog {
  DataAccessLayer DAL;
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international
  Utils ut = new Utils();
  BJPanel pnlButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BorderLayout borderLayout2 = new BorderLayout();
  BJButton btnCancel = new BJButton();
  FlowLayout flowLayout1 = new FlowLayout();
  BJButton btnSearch = new BJButton();
  JPanel pnlTable = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JScrollPane scrPanelTable = new JScrollPane();
  FrameMainSim frmMain;
  int parentScreen=0;
  String arr_Seq[];
  ItemSeasonBusObj iSeasonBusObj = new ItemSeasonBusObj();
  SeasonBusObj bSeasonBusObj = new SeasonBusObj();
  PhasesBusObject bPhasesBusObject = new PhasesBusObject();
   int rowsNum = 5;
   public boolean done = false;
  SortableTableModel dm = new SortableTableModel(){
  public Class getColumnClass(int col){
    switch(col){
//      case 0: return Long.class;
      default: return Object.class;
    }
  }
};
  BJTable table = new BJTable(dm,true);
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel pnlLeftInfo = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel pnlLabel = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel pnlControl = new JPanel();
  JLabel lblItemName = new JLabel();
  JLabel lblItemID = new JLabel();
  BorderLayout borderLayout5 = new BorderLayout();
  JTextField txtRoleName = new JTextField();
  JPanel jPanel4 = new JPanel();
  JTextField txtRoleID = new JTextField();
  FlowLayout flowLayout2 = new FlowLayout();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField txtRowLimit = new JTextField();
  FlowLayout flowLayout3 = new FlowLayout();
  JPanel jPanel5 = new JPanel();
  Statement stmt = null;

  public DialogAminRoleSearch(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  public DialogAminRoleSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain, ItemSeasonBusObj iSeasonBusObj) {
     super(frame, title, modal);
     try {
       this.frmMain = frmMain;
       DAL = frmMain.getDAL();
       jbInit();
     }
     catch(Exception ex) {
       ex.printStackTrace();
     }
   }


  public DialogAminRoleSearch() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    pnlButton.setLayout(flowLayout1);
    this.setSize(new Dimension(800, 600));
    this.addKeyListener(new DialogAminRoleSearch_this_keyAdapter(this));
    this.getContentPane().setLayout(borderLayout2);
    pnlButton.setPreferredSize(new Dimension(800, 50));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    //btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Text"), 1, 12))
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new DialogAminRoleSearch_btnCancel_actionAdapter(this));
    btnSearch.setToolTipText(lang.getString("Search") +" (F3)");
    btnSearch.setText("<html><center><b>"+ lang.getString("Search") +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3<" +
    "/html>");
    btnSearch.addActionListener(new DialogAminRoleSearch_btnSearch_actionAdapter(this));
    pnlTable.setLayout(gridLayout1);
    jPanel1.setLayout(borderLayout3);
    jPanel1.setMaximumSize(new Dimension(2147483647, 2147483647));
    jPanel1.setPreferredSize(new Dimension(10, 50));
    pnlTable.setPreferredSize(new Dimension(800, 420));
    jPanel2.setPreferredSize(new Dimension(20, 50));
    lblItemName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    lblItemName.setOpaque(false);
    pnlLabel.setPreferredSize(new Dimension(150, 50));
    pnlLabel.setLayout(flowLayout3);
    pnlLeftInfo.setLayout(borderLayout4);
    pnlLeftInfo.setPreferredSize(new Dimension(150, 50));
    lblItemName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemName.setPreferredSize(new Dimension(110, 21));
    lblItemName.setText(lang.getString("RoleName")+":");
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setPreferredSize(new Dimension(110, 21));
    lblItemID.setText(lang.getString("RoleID")+":");
    pnlControl.setLayout(borderLayout5);
    txtRoleName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRoleName.setPreferredSize(new Dimension(180, 21));
    txtRoleName.setText("");
    txtRoleName.addKeyListener(new DialogAminRoleSearch_txtRoleName_keyAdapter(this));
    jPanel4.setLayout(flowLayout2);
    jPanel4.setPreferredSize(new Dimension(200, 31));
    txtRoleID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRoleID.setPreferredSize(new Dimension(180, 21));
    txtRoleID.addKeyListener(new DialogAminRoleSearch_txtRoleID_keyAdapter(this));
    flowLayout2.setAlignment(FlowLayout.LEFT);
    scrPanelTable.setPreferredSize(new Dimension(454, 350));
    scrPanelTable.addMouseListener(new DialogAminRoleSearch_scrPanelTable_mouseAdapter(this));
    jPanel3.setLayout(borderLayout1);
    jPanel3.setPreferredSize(new Dimension(110, 50));
    jPanel3.setOpaque(true);
    jPanel3.setPreferredSize(new Dimension(110, 10));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(110, 21));
    jLabel1.setText(lang.getString("RowsLimit")+":");
    txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtRowLimit.setPreferredSize(new Dimension(60, 21));
    txtRowLimit.setText("5");
    txtRowLimit.addKeyListener(new DialogAminRoleSearch_txtRowLimit_keyAdapter(this));
    flowLayout3.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setHgap(5);
    table.addMouseListener(new DialogAminRoleSearch_table_mouseAdapter(this));
    table.addKeyListener(new DialogAminRoleSearch_table_keyAdapter(this));
    getContentPane().add(pnlButton,  BorderLayout.NORTH);
    pnlButton.add(btnSearch, null);
    pnlButton.add(btnCancel, null);
    this.getContentPane().add(pnlTable,  BorderLayout.SOUTH);
    pnlTable.add(scrPanelTable, null);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(pnlLeftInfo,  BorderLayout.WEST);
    pnlLeftInfo.add(pnlLabel, BorderLayout.CENTER);
    pnlLabel.add(lblItemID, null);
    pnlLabel.add(lblItemName, null);
    pnlLabel.add(jLabel1, null);
    pnlLeftInfo.add(jPanel2,  BorderLayout.WEST);
    jPanel1.add(pnlControl,  BorderLayout.CENTER);
    pnlControl.add(jPanel4,  BorderLayout.WEST);
    jPanel4.add(txtRoleID, null);
    jPanel4.add(txtRoleName, null);
    jPanel4.add(txtRowLimit, null);
    pnlControl.add(jPanel3,  BorderLayout.EAST);
    scrPanelTable.getViewport().add(table, null);
    /************ SCREEN INIT ************************/
    createConnection();
  }

  private void createConnection() {
    DAL.getOracleConnect();
    this.iSeasonBusObj.setDataAccessLayer(DAL);
    this.bSeasonBusObj.setDataAccessLayer(DAL);
    this.bPhasesBusObject.setDataAccessLayer(DAL);
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    if (txtRowLimit.getText().trim().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1001_EnterNumberOfRow"));
      txtRowLimit.requestFocus(true);
      return;
    }
    rowsNum = Integer.parseInt(txtRowLimit.getText());
//    if (rowsNum > 50){
//      ut.showMessage(frmMain,lang.getString("TT001"), "The number of rows is too long! it must be less than or equal 50");
//      return;
//    }
    searchData(rowsNum);

  }
  private void searchData(int rowsNum) {
    dm.resetIndexes();
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);

      table.setData(getData(txtRoleID.getText(), txtRoleName.getText(), rowsNum,
                            stmt));
      stmt.close();
    }catch(Exception ex){};
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    table.setHeaderName(lang.getString("RoleID"), 0);
    table.setHeaderName(lang.getString("RoleName"), 1);
    table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
  }

  private ResultSet getData(String roleID, String roleName, int rows, Statement stmt) {
    ResultSet rs = null;
    try {
      String sqlStr = "select role_id, role_name from btm_im_role where rownum <=" +  rows;
      String sql = "";
      if (roleID.trim().length() > 0) {
        sql += " and lower(role_id) like lower('%" + roleID + "%')";
      }
      if (roleName.trim().length() > 0) {
        sql += " and lower(role_name) like lower('%" + roleName + "%')";
      }

      sqlStr += sql + " order by role_id";
//      System.out.println(sqlStr);
       rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;

  }

  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_ADMIN_ROLE);
    this.dispose();
  }
  void scrPanelTable_mouseClicked(MouseEvent e) {

  }
  private void registerKeyboardActions() {
              /// set up the maps:

              InputMap inputMap = new InputMap();
              inputMap.setParent(pnlButton.getInputMap(JComponent.
                                                     WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
              ActionMap actionMap = pnlButton.getActionMap();


              // F3
              Integer key = new Integer(KeyEvent.VK_F3);
              inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), key);
              actionMap.put(key, new KeyAction(key));

              // F12
              key = new Integer(KeyEvent.VK_F12);
              inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
              actionMap.put(key, new KeyAction(key));

             // ENTER
              key = new Integer(KeyEvent.VK_ENTER);
              inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
              actionMap.put(key, new KeyAction(key));
              // ESCAPE
              key = new Integer(KeyEvent.VK_ESCAPE);
              inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
              actionMap.put(key, new KeyAction(key));
               pnlButton.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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

                if (identifier.intValue() == KeyEvent.VK_F3 || identifier.intValue() == KeyEvent.VK_ENTER) {
                  btnSearch.doClick();
                }
                else if (identifier.intValue() == KeyEvent.VK_F12 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                  btnCancel.doClick();
                }
              }
      }
      private void catchHotKey(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F1) {
          btnSearch.doClick();
        }
        else if (e.getKeyCode() == KeyEvent.VK_F2 ||
                 e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }

      }
  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      if (this.table.getSelectedRow() >= 0) {
        frmMain.adminBusObj.setData(txtRoleID.getText(), txtRoleName.getText(),
                                    rowsNum, dm.getDataVector(),
                                    table.getSelectedRow());
        String roleID = table.getValueAt(table.getSelectedRow(), 0).toString();
        String roleName = table.getValueAt(table.getSelectedRow(), 1).toString();
        frmMain.pnlAdminRoleModify.txtRoleID.setText(roleID);
        frmMain.pnlAdminRoleModify.txtRoleDesc.setText(roleName);
        if(roleID.trim().equals("001") || roleID.trim().equals("002")){
          frmMain.pnlAdminRoleModify.txtRoleDesc.setEditable(false);
        }
        else{
          frmMain.pnlAdminRoleModify.txtRoleDesc.setEditable(true);
          frmMain.pnlAdminRoleModify.txtRoleDesc.requestFocus(true);
        }
         frmMain.showScreen(Constant.SCRS_ADMIN_ROLE_MODIFY);
        this.dispose();
      }
      else {
        ut.showMessage(frmMain, lang.getString("TT008"),lang.getString("MS1002_YouShouldChooseData"));
        return;
      }
    }
  }
  void setDataVector(Vector vData){
    Vector vColumns = new Vector();
    vColumns.add("Role ID");
    vColumns.add("Role Name");
    dm.setDataVector(vData, vColumns);
    table.setAutoResizeMode(table.
                                          AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,
        13));
    table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    table.setColor(Color.lightGray, Color.white);
  }
  void updateRowOnTable(String roleName){
    table.setValueAt(roleName,frmMain.adminBusObj.rowSelected,1);
  }
  void updateScreen(){
    txtRoleID.setText(frmMain.adminBusObj.role_ID);
    txtRoleName.setText(frmMain.adminBusObj.role_Name);
    txtRowLimit.setText(String.valueOf(frmMain.adminBusObj.rowlimit));
    setDataVector(frmMain.adminBusObj.vData);
  }

  void txtRoleID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtRoleID, AdminBusObj.LEN_ROLE_ID);
    if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
        (e.getKeyChar() != e.VK_BACK_SPACE))
      e.consume();
  }

  void txtRowLimit_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtRowLimit, AdminBusObj.LEN_ROW_LIMIT);
    if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
        (e.getKeyChar() != e.VK_BACK_SPACE))
      e.consume();
  }

  void txtRoleID_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtRoleName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtRowLimit_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
}

class DialogAminRoleSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  DialogAminRoleSearch adaptee;

  DialogAminRoleSearch_btnSearch_actionAdapter(DialogAminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}



class DialogAminRoleSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogAminRoleSearch adaptee;

  DialogAminRoleSearch_btnCancel_actionAdapter(DialogAminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogAminRoleSearch_scrPanelTable_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogAminRoleSearch adaptee;

  DialogAminRoleSearch_scrPanelTable_mouseAdapter(DialogAminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.scrPanelTable_mouseClicked(e);
  }
}

class DialogAminRoleSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogAminRoleSearch adaptee;

  DialogAminRoleSearch_table_mouseAdapter(DialogAminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogAminRoleSearch_txtRoleID_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAminRoleSearch adaptee;

  DialogAminRoleSearch_txtRoleID_keyAdapter(DialogAminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRoleID_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRoleID_keyPressed(e);
  }
}

class DialogAminRoleSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAminRoleSearch adaptee;

  DialogAminRoleSearch_txtRowLimit_keyAdapter(DialogAminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRowLimit_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRowLimit_keyPressed(e);
  }
}

class DialogAminRoleSearch_txtRoleName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAminRoleSearch adaptee;

  DialogAminRoleSearch_txtRoleName_keyAdapter(DialogAminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRoleName_keyPressed(e);
  }
}

class DialogAminRoleSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAminRoleSearch adaptee;

  DialogAminRoleSearch_this_keyAdapter(DialogAminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogAminRoleSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAminRoleSearch adaptee;

  DialogAminRoleSearch_table_keyAdapter(DialogAminRoleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

