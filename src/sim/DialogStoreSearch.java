package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import btm.business.CustomerBusObj;
import btm.attr.Store;
import java.sql.*;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Store Setup -> Search</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DialogStoreSearch extends JDialog {
  BorderLayout borderLayout1 = new BorderLayout();
  DataAccessLayer DAL;
  Utils ut = new Utils();
  int parentScreen=0;
  Statement stmt = null;

  StoreBusObj storeBusObj ;
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        case 0: return Long.class;
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm,true);
  BorderLayout borderLayout2 = new BorderLayout();
  BJPanel jPanel5 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJButton btnSearch = new BJButton();
  BJButton btnCancel = new BJButton();
  FrameMainSim frmMain;
  JPanel jPanel6 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout();
  int rowsNum = 5;
  public boolean done = false;
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel7 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JLabel lblCity = new JLabel();
  JLabel lblAddress1 = new JLabel();
  JLabel lblName = new JLabel();
  JLabel lblStoreID = new JLabel();
  JTextField txtCity = new JTextField();
  JTextField txtAddress1 = new JTextField();
  JTextField txtName = new JTextField();
  JTextField txtStoreID = new JTextField();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JPanel jPanel12 = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jPanel13 = new JPanel();
  JPanel jPanel14 = new JPanel();
  JLabel lblRowLimit = new JLabel();
  JLabel lblPhoneNumber = new JLabel();
  JLabel lblManagerName = new JLabel();
  JLabel lblCountry = new JLabel();
  JPanel jPanel15 = new JPanel();
  JTextField txtRowLimit = new JTextField();
  JTextField txtPhoneNumber = new JTextField();
  JTextField txtManagerName = new JTextField();
  JTextField txtCountry = new JTextField();
  JTable jTable1 = new JTable();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

  public DialogStoreSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain, StoreBusObj storeBusObj) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      this.storeBusObj = storeBusObj;
      DAL = frmMain.getDAL();
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  public DialogStoreSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain){
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  public DialogStoreSearch() {
    this(null, "", false,null,null);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();
    this.getContentPane().setLayout(borderLayout1);

    this.setSize(new Dimension(800,600));
    this.addWindowListener(new DialogStoreSearch_this_windowAdapter(this));
    this.addKeyListener(new DialogStoreSearch_this_keyAdapter(this));
    jPanel1.setMinimumSize(new Dimension(10, 10));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout2);
    jPanel2.setPreferredSize(new Dimension(800, 400));
    jPanel2.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(800, 390));
//    jPanel3.setPreferredSize(new Dimension(230, 50));
    jPanel5.setMinimumSize(new Dimension(503, 35));
    jPanel5.setPreferredSize(new Dimension(750, 50));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setPreferredSize(new Dimension(130, 37));
//    btnSearch.setPreferredSize(new Dimension(80, 35));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3<" +
    "/html>");
    btnSearch.addActionListener(new DialogStoreSearch_btnSearch_actionAdapter(this));
//    btnDelete.setPreferredSize(new Dimension(80, 35));
//    btnModify.setPreferredSize(new Dimension(80, 35));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(130, 37));
//    btnCancel.setPreferredSize(new Dimension(80, 35));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new DialogStoreSearch_btnCancel_actionAdapter(this));

    jPanel6.setPreferredSize(new Dimension(800, 150));
    jPanel6.setLayout(borderLayout3);
    table.addMouseListener(new DialogStoreSearch_table_mouseAdapter(this));
    jPanel3.setPreferredSize(new Dimension(150, 150));
    jPanel3.setLayout(borderLayout4);
    jPanel4.setPreferredSize(new Dimension(200, 150));
    jPanel7.setPreferredSize(new Dimension(450, 150));
    jPanel7.setLayout(borderLayout5);
    jPanel8.setPreferredSize(new Dimension(40, 150));
    jPanel9.setPreferredSize(new Dimension(110, 150));
    lblCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCity.setPreferredSize(new Dimension(100, 20));
    lblCity.setText(lang.getString("City")+":");
    lblAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAddress1.setPreferredSize(new Dimension(100, 20));
    lblAddress1.setText(lang.getString("Address1")+":");
    lblName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblName.setPreferredSize(new Dimension(100, 20));
    lblName.setText(lang.getString("FirstName")+":");
    lblStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStoreID.setPreferredSize(new Dimension(100, 20));
    lblStoreID.setText(lang.getString("StoreID")+":");
    txtCity.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCity.setPreferredSize(new Dimension(190, 20));
    txtCity.setEditable(true);
    txtCity.setText("");
    txtCity.addFocusListener(new DialogStoreSearch_txtCity_focusAdapter(this));
    txtCity.addKeyListener(new DialogStoreSearch_txtCity_keyAdapter(this));
    txtAddress1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtAddress1.setPreferredSize(new Dimension(190, 20));
    txtAddress1.setText("");
    txtAddress1.addFocusListener(new DialogStoreSearch_txtAddress1_focusAdapter(this));
    txtAddress1.addKeyListener(new DialogStoreSearch_txtAddress1_keyAdapter(this));
    txtName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtName.setPreferredSize(new Dimension(190, 20));
    txtName.setText("");
    txtName.addFocusListener(new DialogStoreSearch_txtName_focusAdapter(this));
    txtName.addKeyListener(new DialogStoreSearch_txtName_keyAdapter(this));
    txtStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtStoreID.setPreferredSize(new Dimension(190, 20));
    txtStoreID.setText("");
    txtStoreID.addFocusListener(new DialogStoreSearch_txtStoreID_focusAdapter(this));
    txtStoreID.addKeyListener(new DialogStoreSearch_txtStoreID_keyAdapter(this));
    jPanel10.setPreferredSize(new Dimension(100, 150));
    jPanel11.setPreferredSize(new Dimension(310, 150));
    jPanel11.setLayout(borderLayout6);
    jPanel12.setPreferredSize(new Dimension(40, 150));
    jPanel13.setPreferredSize(new Dimension(110, 150));
    jPanel14.setPreferredSize(new Dimension(200, 150));
    lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblRowLimit.setPreferredSize(new Dimension(100, 20));
    lblRowLimit.setText(lang.getString("RowsLimit")+":");
    lblPhoneNumber.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPhoneNumber.setOpaque(false);
    lblPhoneNumber.setPreferredSize(new Dimension(100, 20));
    lblPhoneNumber.setText(lang.getString("Phone")+":");
    lblManagerName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblManagerName.setPreferredSize(new Dimension(100, 20));
    lblManagerName.setText(lang.getString("ManagerName")+":");
    lblCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblCountry.setPreferredSize(new Dimension(100, 20));
    lblCountry.setText(lang.getString("Country")+":");
    txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtRowLimit.setPreferredSize(new Dimension(50, 20));
    txtRowLimit.setText("5");
    txtRowLimit.addFocusListener(new DialogStoreSearch_txtRowLimit_focusAdapter(this));
    txtRowLimit.addKeyListener(new DialogStoreSearch_txtRowLimit_keyAdapter(this));
    txtPhoneNumber.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPhoneNumber.setPreferredSize(new Dimension(190, 20));
    txtPhoneNumber.setText("");
    txtPhoneNumber.addFocusListener(new DialogStoreSearch_txtPhoneNumber_focusAdapter(this));
    txtPhoneNumber.addKeyListener(new DialogStoreSearch_txtPhoneNumber_keyAdapter(this));
    txtManagerName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtManagerName.setPreferredSize(new Dimension(190, 20));
    txtManagerName.setText("");
    txtManagerName.addFocusListener(new DialogStoreSearch_txtManagerName_focusAdapter(this));
    txtManagerName.addKeyListener(new DialogStoreSearch_txtManagerName_keyAdapter(this));
    txtCountry.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCountry.setPreferredSize(new Dimension(190, 20));
    txtCountry.setText("");
    txtCountry.addFocusListener(new DialogStoreSearch_txtCountry_focusAdapter(this));
    txtCountry.addKeyListener(new DialogStoreSearch_txtCountry_keyAdapter(this));
    jPanel15.setPreferredSize(new Dimension(130, 20));
    table.addKeyListener(new DialogStoreSearch_table_keyAdapter(this));
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jPanel5,BorderLayout.CENTER);
    jPanel5.add(btnSearch,null);
    jPanel5.add(btnCancel,null);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jScrollPane1, null);
    this.getContentPane().add(jPanel6, BorderLayout.CENTER);
    jPanel6.add(jPanel3,  BorderLayout.WEST);
    jPanel3.add(jPanel8,  BorderLayout.WEST);
    jPanel3.add(jPanel9,  BorderLayout.CENTER);
    jPanel9.add(lblStoreID, null);
    jPanel9.add(lblName, null);
    jPanel9.add(lblAddress1, null);
    jPanel9.add(lblCity, null);
    jPanel6.add(jPanel4,  BorderLayout.CENTER);
    jPanel4.add(txtStoreID, null);
    jPanel4.add(txtName, null);
    jPanel4.add(txtAddress1, null);
    jPanel4.add(txtCity, null);
    jPanel6.add(jPanel7,  BorderLayout.EAST);
    jPanel7.add(jPanel10,  BorderLayout.WEST);
    jPanel7.add(jPanel11,  BorderLayout.CENTER);
    jPanel11.add(jPanel13,  BorderLayout.WEST);
    jPanel13.add(lblCountry, null);
    jPanel13.add(lblManagerName, null);
    jPanel13.add(lblPhoneNumber, null);
    jPanel13.add(lblRowLimit, null);
    jPanel11.add(jPanel14,  BorderLayout.CENTER);
    jPanel14.add(txtCountry, null);
    jPanel14.add(txtManagerName, null);
    jPanel14.add(txtPhoneNumber, null);
        jPanel14.add(txtRowLimit, null);
    jPanel14.add(jPanel15, null);
    jPanel7.add(jPanel12,  BorderLayout.EAST);
    jScrollPane1.getViewport().add(table, null);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);
  }



   void btnSearch_actionPerformed(ActionEvent e) {

    if (txtRowLimit.getText().equals("")){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS031_RowsIsNum"));
      return;
    }

    rowsNum = Integer.parseInt(txtRowLimit.getText());
//    if (rowsNum > 50){
//      ut.showMessage(frmMain,lang.getString("TT001"), "The number of rows is too long! it must be less than or equal 50");
//      return;
//    }
    frmMain.pnlStoreSetup.rowNum = rowsNum;
    searchData(rowsNum);
    txtStoreID.requestFocus(true);
  }
  // Search Data
  void searchData(int rowsNum) {
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_UPDATABLE);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
    dm.resetIndexes();
    table.setData(storeBusObj.getData(txtStoreID.getText(), txtName.getText(),
                                      txtAddress1.getText(), txtCity.getText(),
                                      txtCountry.getText(),txtManagerName.getText(),
                                      txtPhoneNumber.getText(), rowsNum, DAL,stmt));
    table.setColumnWidth(0,80);
    table.setColumnWidth(1,250);
    table.setColumnWidth(2,250);
    table.setColumnWidth(3,100);

    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
    table.setHeaderName(lang.getString("StoreID"), 0);
    table.setHeaderName(lang.getString("Name"), 1);
    table.setHeaderName(lang.getString("ManagerName"), 2);
    table.setHeaderName(lang.getString("Phone"), 3);
    table.setHeader(new java.awt.Font(lang.getString("FontName_Label"), 1, 15));
    ut.changeDataTypetoLong(0,dm);
    try {
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  void table_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2){
      done = true;
      setDataIntoStoreBusObj();
      this.dispose();
    }
  }
  //return data
  String getStoreName(){
    String result = "";
    result = table.getValueAt(table.getSelectedRow(),1).toString();
    return result;
  }
  String getData(){
    String result = "";
    result = "" + table.getValueAt(table.getSelectedRow(),0);
    return result;
  }
  void btnCancel_actionPerformed(ActionEvent e) {
    done = false;
    this.dispose();
  }
  //set data into StoreBusObj
  void setDataIntoStoreBusObj(){
    storeBusObj.setValue(txtStoreID.getText(),txtName.getText(),
                         txtAddress1.getText(), txtCity.getText(),
                         txtCountry.getText(), txtManagerName.getText(),
                         txtPhoneNumber.getText());
  }
  //set text
  void setText(String storeID, String storeName, String address1, String city, String country, String managerName, String phoneNo ){
    txtStoreID.setText(storeID);
    txtName.setText(storeName);
    txtAddress1.setText(address1);
    txtCity.setText(city);
    txtCountry.setText(country);
    txtManagerName.setText(managerName);
    txtPhoneNumber.setText(phoneNo);
  }
  void txtStoreID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtStoreID,Store.LEN_STORE_ID);
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtName.requestFocus(true);
    }
  }

  void txtName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtName,Store.LEN_STORE_NAME);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtAddress1.requestFocus(true);
    }
  }


  void txtAddress1_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtAddress1,Store.LEN_STORE_ADDRESS_1);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtCity.requestFocus(true);
    }
  }

  void txtCity_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCity,Store.LEN_STORE_CITY);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtCountry.requestFocus(true);
    }
  }


  void txtCountry_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtCountry,Store.LEN_STORE_COUNTRY);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtManagerName.requestFocus(true);
    }
  }

  void txtManagerName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtManagerName,Store.LEN_STORE_MANAGER_NAME);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtPhoneNumber.requestFocus(true);
    }
  }

  void txtPhoneNumber_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtPhoneNumber,Store.LEN_STORE_PHONE_NO);
    ut.checkPhone(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtRowLimit.requestFocus(true);
    }
  }


  void txtRowLimit_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtRowLimit,2);
    ut.checkNumber(e);
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      btnSearch.doClick();
      txtStoreID.requestFocus(true);
    }
  }
  private void registerKeyboardActions() {
              /// set up the maps:

              InputMap inputMap = new InputMap();
              inputMap.setParent(jPanel5.getInputMap(JComponent.
                                                     WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
              ActionMap actionMap = jPanel5.getActionMap();


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

               jPanel5.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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
             if (e.getKeyCode() == KeyEvent.VK_F3) {
               btnSearch.doClick();
             }
             else if (e.getKeyCode() == KeyEvent.VK_F12 ||
                      e.getKeyCode() == KeyEvent.VK_ESCAPE) {
               btnCancel.doClick();
             }

           }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtStoreID_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtAddress1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtCity_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtCountry_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtManagerName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtPhoneNumber_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtRowLimit_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtStoreID_focusGained(FocusEvent e) {
    txtStoreID.selectAll();
  }

  void txtName_focusGained(FocusEvent e) {
    txtName.selectAll();
  }

  void txtAddress1_focusGained(FocusEvent e) {
    txtAddress1.selectAll();
  }

  void txtCity_focusGained(FocusEvent e) {
    txtCity.selectAll();
  }

  void txtCountry_focusGained(FocusEvent e) {
    txtCountry.selectAll();
  }

  void txtManagerName_focusGained(FocusEvent e) {
    txtManagerName.selectAll();
  }

  void txtPhoneNumber_focusGained(FocusEvent e) {
    txtPhoneNumber.selectAll();
  }

  void txtRowLimit_focusGained(FocusEvent e) {
    txtRowLimit.selectAll();
  }

  void txtStoreID_focusLost(FocusEvent e) {
    txtStoreID.select(0,0);
  }

  void txtName_focusLost(FocusEvent e) {
    txtName.select(0,0);
  }

  void txtAddress1_focusLost(FocusEvent e) {
    txtAddress1.select(0,0);
  }

  void txtCity_focusLost(FocusEvent e) {
    txtCity.select(0,0);
  }

  void txtCountry_focusLost(FocusEvent e) {
    txtCountry.select(0,0);
  }

  void txtManagerName_focusLost(FocusEvent e) {
    txtManagerName.select(0,0);
  }

  void txtPhoneNumber_focusLost(FocusEvent e) {
    txtPhoneNumber.select(0,0);
  }

  void txtRowLimit_focusLost(FocusEvent e) {
    txtRowLimit.select(0,0);
  }

  void this_windowOpened(WindowEvent e) {
    txtStoreID.requestFocus(true);
  }

}

class DialogStoreSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  DialogStoreSearch adaptee;

  DialogStoreSearch_btnSearch_actionAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class DialogStoreSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_table_mouseAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogStoreSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogStoreSearch adaptee;

  DialogStoreSearch_btnCancel_actionAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogStoreSearch_txtStoreID_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtStoreID_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtStoreID_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtStoreID_keyPressed(e);
  }
}

class DialogStoreSearch_txtName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtName_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtName_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtName_keyPressed(e);
  }
}

class DialogStoreSearch_txtAddress1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtAddress1_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAddress1_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtAddress1_keyPressed(e);
  }

}

class DialogStoreSearch_txtCity_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtCity_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCity_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtCity_keyPressed(e);
  }
}

class DialogStoreSearch_txtCountry_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtCountry_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtCountry_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtCountry_keyPressed(e);
  }
}

class DialogStoreSearch_txtManagerName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtManagerName_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtManagerName_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtManagerName_keyPressed(e);
  }
}

class DialogStoreSearch_txtPhoneNumber_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtPhoneNumber_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPhoneNumber_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtPhoneNumber_keyPressed(e);
  }
}

class DialogStoreSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtRowLimit_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtRowLimit_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRowLimit_keyPressed(e);
  }
}

class DialogStoreSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_this_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogStoreSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_table_keyAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogStoreSearch_txtStoreID_focusAdapter extends java.awt.event.FocusAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtStoreID_focusAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtStoreID_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtStoreID_focusLost(e);
  }
}

class DialogStoreSearch_txtName_focusAdapter extends java.awt.event.FocusAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtName_focusAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtName_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtName_focusLost(e);
  }
}

class DialogStoreSearch_txtAddress1_focusAdapter extends java.awt.event.FocusAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtAddress1_focusAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtAddress1_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtAddress1_focusLost(e);
  }
}

class DialogStoreSearch_txtCity_focusAdapter extends java.awt.event.FocusAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtCity_focusAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtCity_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtCity_focusLost(e);
  }
}

class DialogStoreSearch_txtCountry_focusAdapter extends java.awt.event.FocusAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtCountry_focusAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtCountry_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtCountry_focusLost(e);
  }
}

class DialogStoreSearch_txtManagerName_focusAdapter extends java.awt.event.FocusAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtManagerName_focusAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtManagerName_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtManagerName_focusLost(e);
  }
}

class DialogStoreSearch_txtPhoneNumber_focusAdapter extends java.awt.event.FocusAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtPhoneNumber_focusAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtPhoneNumber_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtPhoneNumber_focusLost(e);
  }
}

class DialogStoreSearch_txtRowLimit_focusAdapter extends java.awt.event.FocusAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_txtRowLimit_focusAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtRowLimit_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtRowLimit_focusLost(e);
  }
}

class DialogStoreSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogStoreSearch adaptee;

  DialogStoreSearch_this_windowAdapter(DialogStoreSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}


