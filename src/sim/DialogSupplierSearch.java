package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import btm.swing.*;
import btm.business.*;
import java.awt.event.*;
import btm.business.SupplierBusObj;
//import btm.attr.Store;
import java.sql.*;
import btm.attr.*;
import java.sql.Statement;
import java.util.ResourceBundle;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Supplier Setup -> Search</p>
 * <p>Description: Main -> Inv Mgmt -> Lookup -> Supplier Lookup -> Search </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Phuoc Nguyen
 * @version 1.0
 */

public class DialogSupplierSearch extends JDialog {
  //Column Order of Sale Item Table
  private static final int COL_SUPPLIER_ID = 0; //
  private static final int COL_SUPPLIER_NAME = 1;
  private static final int COL_ABBREVIATION_NAME = 2; //
  private static final int COL_CONTACT_NAME = 3;
  private static final int COL_CONTACT_PHONE = 4; //

  Statement stmt = null;
//    int status = 0;
//-----------------------declare BorderLayout------------------------------//
    BorderLayout borderLayout1 = new BorderLayout();
    GridLayout gridLayout1 = new GridLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    BorderLayout borderLayout4 = new BorderLayout();
//-------------------------declare Panel Header--------------------------//
    BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
    //buttons
    BJButton btnSearch = new BJButton();
    BJButton btnCancel = new BJButton();

//-------------------------declare Panel information--------------------------//
    BJPanel pnlInfo = new BJPanel();
    BJPanel pnlInfoAlign = new BJPanel();
    BJPanel pnlInfoLeft = new BJPanel();
    BJPanel pnlInfoRight = new BJPanel();

    BJPanel pnlInfoLeftAlign = new BJPanel();
    BJPanel pnlInfoLeftLabel = new BJPanel();
    BJPanel pnlInfoLeftText = new BJPanel();

    BJPanel pnlInfoRightLabel = new BJPanel();
    BJPanel pnlInfoRightText = new BJPanel();

    ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

    JLabel lblSuppId = new JLabel();//("Supplier ID:");
    JLabel lblSuppName = new JLabel();//("Supplier Name:");
    JLabel lblAbbreName = new JLabel();//("Abbreviation Name:");
    JLabel lblContactName = new JLabel();//("Contact Name:");
    JLabel lblContactPhone = new JLabel();//("Contact Phone:");
    JLabel lblRowLimit = new JLabel();//("Row Limit:");


    JTextField txtSuppId = new JTextField();
    JTextField txtSuppName = new JTextField();
    JTextField txtAbbreName = new JTextField();
    JTextField txtContactName = new JTextField();
    JTextField txtContactPhone = new JTextField();
    JTextField txtRowLimit = new JTextField();


//-------------------------declare Panel  search result--------------------------//
    BJPanel pnlResultSearch = new BJPanel();
    JScrollPane jScrollPane1 = new JScrollPane();

//----------------------------------------------------------------------------------------------//
    FrameMainSim frmMain;
    DataAccessLayer DAL;
    SupplierBusObj supplierBusObj = new SupplierBusObj();
    Utils ut = new Utils();
    int parentScreen = 0;
//    suppBusObj suppBusObj;
    SortableTableModel dm = new SortableTableModel();

    BJTable table = new BJTable(dm, true) {
        public Class getColumnClass(int col) {
            switch (col) {
                case COL_SUPPLIER_ID:
                    return String.class;
                default:
                    return Object.class;
            }
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };




    public boolean done = false;
    int rowsNum = 5;

    public DialogSupplierSearch(Frame frame, String title, boolean modal,FrameMainSim frmMain, SupplierBusObj suppBusObj) {
        super(frame, title, modal);
        try {
            this.frmMain = frmMain;
            this.supplierBusObj = suppBusObj;
            DAL = frmMain.getDAL();
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public DialogSupplierSearch() {
        this(null, "", false,null,null);
    }
    private void jbInit() throws Exception {
      //DAL.getConnfigParameter();

      registerKeyboardActions();
        this.setSize(new Dimension(800, 600));
    this.addWindowListener(new DialogSupplierSearch_this_windowAdapter(this));
    this.addKeyListener(new DialogSupplierSearch_this_keyAdapter(this));
    lblSuppId.setText(lang.getString("SupplierID")+":");
    lblSuppName.setText(lang.getString("SupplierName")+":");
    lblAbbreName.setText(lang.getString("AbbreName")+":");
    lblContactName.setText(lang.getString("ContactName")+":");
    lblContactPhone.setText(lang.getString("ContactPhone")+":");
    lblRowLimit.setText(lang.getString("RowsLimit")+":");

        this.getContentPane().setLayout(borderLayout1);
        pnlInfoRight.setPreferredSize(new Dimension(360, 31));
        pnlInfoLeftText.setPreferredSize(new Dimension(220, 31));
        txtSuppId.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        txtSuppId.setPreferredSize(new Dimension(200, 22));
    txtSuppId.addKeyListener(new DialogSupplierSearch_txtSuppId_keyAdapter(this));
        txtSuppName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        txtSuppName.setMinimumSize(new Dimension(200, 20));
        txtSuppName.setPreferredSize(new Dimension(200, 22));
        txtSuppName.setRequestFocusEnabled(true);
    txtSuppName.addKeyListener(new DialogSupplierSearch_txtSuppName_keyAdapter(this));

        txtAbbreName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        txtAbbreName.setMinimumSize(new Dimension(200, 20));
        txtAbbreName.setPreferredSize(new Dimension(200, 22));
    txtAbbreName.addKeyListener(new DialogSupplierSearch_txtAbbreName_keyAdapter(this));

        pnlInfoLeft.setPreferredSize(new Dimension(400, 100));
        pnlInfo.setPreferredSize(new Dimension(700, 100));
        pnlResultSearch.setOpaque(true);
        pnlResultSearch.setPreferredSize(new Dimension(800, 410));
        pnlInfoLeftAlign.setPreferredSize(new Dimension(40, 10));
        lblContactName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblContactName.setPreferredSize(new Dimension(130, 22));
        lblContactPhone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblContactPhone.setPreferredSize(new Dimension(130, 22));
        pnlInfoRightLabel.setPreferredSize(new Dimension(130, 26));
        pnlInfoRightText.setPreferredSize(new Dimension(260, 31));
    pnlInfoRightText.addKeyListener(new DialogSupplierSearch_pnlInfoRightText_keyAdapter(this));
        pnlInfoRightText.setLayout(null);
        txtContactName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        txtContactName.setPreferredSize(new Dimension(200, 22));
        txtContactName.setBounds(new Rectangle(30, 5, 200, 22));
    txtContactName.addKeyListener(new DialogSupplierSearch_txtContactName_keyAdapter(this));
        txtContactPhone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        txtContactPhone.setPreferredSize(new Dimension(200, 22));
        txtContactPhone.setBounds(new Rectangle(30, 32, 200, 22));
    txtContactPhone.addKeyListener(new DialogSupplierSearch_txtContactPhone_keyAdapter(this));
    txtContactPhone.addActionListener(new DialogSupplierSearch_txtContactPhone_actionAdapter(this));
        btnSearch.setPreferredSize(new Dimension(130, 37));
        btnSearch.addActionListener(new DialogSupplierSearch_btnSearch_actionAdapter(this));
        btnCancel.setPreferredSize(new Dimension(130, 37));
        btnCancel.addActionListener(new DialogSupplierSearch_btnCancel_actionAdapter(this));
        lblRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblRowLimit.setPreferredSize(new Dimension(130, 22));
        txtRowLimit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        txtRowLimit.setPreferredSize(new Dimension(60, 22));
        txtRowLimit.setBounds(new Rectangle(30, 60, 60, 22));
        txtRowLimit.setText(DAL.getSearchLimit());
        txtRowLimit.addKeyListener(new DialogSupplierSearch_txtRowLimit_keyAdapter(this));
        table.addMouseListener(new DialogSupplierSearch_table_mouseAdapter(this));
        table.addKeyListener(new DialogSupplierSearch_table_keyAdapter(this));
    this.getContentPane().add(pnlHeader, BorderLayout.NORTH);
        this.getContentPane().add(pnlInfo, BorderLayout.CENTER);
        this.getContentPane().add(pnlResultSearch, BorderLayout.SOUTH);
        pnlResultSearch.add(jScrollPane1);
        jScrollPane1.getViewport().add(table, null);
    //-------Hearder
        pnlHeader.setPreferredSize(new Dimension(700, 50));

        //Clear
        btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
        btnSearch.setText(lang.getString("Search")+" (F3)");


//        btnSearch.addActionListener(new PanelSupplierSetup_btnClear_actionAdapter(this));
        //Cancel
        btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
        btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
        "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
//        btnCancel.addActionListener(new PanelSupplierSetup_btnCancel_actionAdapter(this));

        pnlHeader.add(btnSearch, null);
        pnlHeader.add(btnCancel, null);

        //--------Info
        pnlInfo.setLayout(borderLayout3);
        pnlInfo.add(pnlInfoAlign, BorderLayout.WEST);
        pnlInfo.add(pnlInfoLeft, BorderLayout.CENTER);
        pnlInfo.add(pnlInfoRight, BorderLayout.EAST);

        pnlInfoAlign.setPreferredSize(new Dimension(20, 10));

        pnlInfoLeft.setLayout(borderLayout2);
        pnlInfoLeft.add(pnlInfoLeftLabel,BorderLayout.WEST);
        pnlInfoLeft.add(pnlInfoLeftText,  BorderLayout.CENTER);
        pnlInfoLeft.add(pnlInfoLeftAlign,  BorderLayout.EAST);

        pnlInfoRight.setLayout(borderLayout4);
        pnlInfoRight.add(pnlInfoRightLabel,BorderLayout.WEST);
        pnlInfoRight.add(pnlInfoRightText, BorderLayout.EAST);

        pnlInfoLeftLabel.setPreferredSize(new Dimension(110, 100));
        pnlInfoLeftLabel.add(lblSuppId, null);
        pnlInfoLeftLabel.add(lblSuppName, null);
        pnlInfoLeftLabel.add(lblAbbreName, null);

        pnlInfoLeftText.add(txtSuppId, null);
        pnlInfoLeftText.add(txtSuppName, null);
        pnlInfoLeftText.add(txtAbbreName, null);

        pnlInfoRightLabel.add(lblContactName, null);
        pnlInfoRightLabel.add(lblContactPhone, null);
        pnlInfoRightLabel.add(lblRowLimit, null);
        pnlInfoRightText.add(txtContactName, null);
        pnlInfoRightText.add(txtContactPhone, null);
        pnlInfoRightText.add(txtRowLimit, null);

        lblSuppId.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblSuppId.setMaximumSize(new Dimension(100, 100));
        lblSuppId.setMinimumSize(new Dimension(64, 16));
        lblSuppId.setPreferredSize(new Dimension(110, 22));
        lblSuppId.setHorizontalAlignment(SwingConstants.LEFT);
        lblSuppName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblSuppName.setPreferredSize(new Dimension(110, 22));
        lblSuppName.setHorizontalAlignment(SwingConstants.LEFT);
        lblAbbreName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblAbbreName.setPreferredSize(new Dimension(110, 22));
        lblAbbreName.setHorizontalAlignment(SwingConstants.LEFT);



    //---------Result Search
        jScrollPane1.setPreferredSize(new Dimension(800, 380));
        pnlResultSearch.setLayout(gridLayout1);


        jScrollPane1.getViewport().add(table, null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        this.setLocation( (screenSize.width - frameSize.width) / 2,
                         (screenSize.height - frameSize.height) / 2);

       txtSuppId.setRequestFocusEnabled(true);
    }
    public void applyPermission() {
       EmpRight er = new EmpRight();
       er.initData(DAL, DAL.getEmpID());
       er.setData(DAL.getEmpID(), Constant.SCRS_ADMIN_ROLE);
       btnSearch.setEnabled(er.getAdd());
     }

    void txtRowLimit_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e, txtRowLimit, 2);
        ut.checkNumber(e);
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          btnSearch.doClick();
        }
    }

    void btnCancel_actionPerformed(ActionEvent e) {
        done = false;
        this.dispose();
    }



     void btnSearch_actionPerformed(ActionEvent e) {

        if (txtRowLimit.getText().equals("")) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS031_RowsIsNum"));
            return;
        }
        rowsNum = Integer.parseInt(txtRowLimit.getText());
//        if (rowsNum > 50) {
//            ut.showMessage(frmMain, lang.getString("TT001"),
//                    "The number of rows is too long! it must be less than or equal 50");
//            return;
//        }
        frmMain.pnlSupplierSetup.rowNum = rowsNum;
        searchData(rowsNum);
    }

    // Search Data
    void searchData(int rowsNum) {
        String[] columnNames = new String[] {
                           lang.getString("SupplierID"), lang.getString("SupplierName"),lang.getString("AbbreviationName"), lang.getString("ContactName"), lang.getString("ContactPhone")};
                   dm.setDataVector(new Object[][] {}
                                    , columnNames);

        table.setColumnWidth(COL_SUPPLIER_ID, 120);
        table.setColumnWidth(COL_SUPPLIER_NAME, 230);
        table.setColumnWidth(COL_ABBREVIATION_NAME, 230);
        table.setColumnWidth(COL_CONTACT_NAME, 230);
        table.setColumnWidth(COL_CONTACT_PHONE, 160);
        dm.resetIndexes();
        try {
          stmt = DAL.getConnection().createStatement(ResultSet.
                                                     TYPE_SCROLL_INSENSITIVE,
                                                     ResultSet.CONCUR_READ_ONLY);
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }

        table.setData(supplierBusObj.getData(txtSuppId.getText(),
                                             txtSuppName.getText(),txtAbbreName.getText(),
                                             txtContactName.getText(),
                                             txtContactPhone.getText(),
                                             rowsNum, DAL,stmt));

        table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 13));
        table.setHeaderName(lang.getString("SupplierID"), COL_SUPPLIER_ID);
        table.setHeaderName(lang.getString("SupplierName"), COL_SUPPLIER_NAME);
        table.setHeaderName(lang.getString("AbbreviationName"), COL_ABBREVIATION_NAME);
        table.setHeaderName(lang.getString("ContactName"), COL_CONTACT_NAME);
        table.setHeaderName(lang.getString("ContactPhone"), COL_CONTACT_PHONE);
        try {
          stmt.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }

      }


    //return data
    String getData() {
        String result = "";
        result = "" + table.getValueAt(table.getSelectedRow(), COL_SUPPLIER_ID);
        return result;
    }
    String getSuppName(){
      String result = "";
      result = table.getValueAt(table.getSelectedRow(),COL_SUPPLIER_NAME).toString();
      return result;
    }

    void table_mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
                done = true;
                setDataIntoSuppBusObj();
                this.dispose();
        }
    }

    //set data into StoreBusObj
    void setDataIntoSuppBusObj() {
        supplierBusObj.setValue(txtSuppId.getText(),txtSuppName.getText(), txtAbbreName.getText(),
                                txtContactName.getText(),txtContactPhone.getText());
    }

    //set text
    public void setText(String suppID, String suppName, String abbreName, String contactName,String contactPhone) {
        txtSuppId.setText(suppID);
        txtSuppName.setText(suppName);
        txtAbbreName.setText(abbreName);
        txtContactName.setText(contactName);
        txtContactPhone.setText(contactPhone);
    }
    private void registerKeyboardActions() {
                  /// set up the maps:

                  InputMap inputMap = new InputMap();
                  inputMap.setParent(pnlHeader.getInputMap(JComponent.
                                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
                  ActionMap actionMap = pnlHeader.getActionMap();


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
  void txtSuppName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtSuppId_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtAbbreName_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void pnlInfoRightText_keyPressed(KeyEvent e) {

  }

  void txtContactName_keyPressed(KeyEvent e) {
 catchHotKey(e);
  }

  void txtContactPhone_actionPerformed(ActionEvent e) {
//    catchHotKey(e);
  }

  void txtRowLimit_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtContactPhone_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtSuppId_keyTyped(KeyEvent e) {
    navigationComp(e,txtSuppName);
  }
  void navigationComp(KeyEvent e, JTextField txt){
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      txt.requestFocus(true);
      txt.selectAll();
    }
  }

  void txtSuppName_keyTyped(KeyEvent e) {
    navigationComp(e,txtAbbreName);
  }

  void txtAbbreName_keyTyped(KeyEvent e) {
    navigationComp(e,txtContactName);
  }

  void txtContactName_keyTyped(KeyEvent e) {
    navigationComp(e,txtContactPhone);
  }

  void txtContactPhone_keyTyped(KeyEvent e) {
    navigationComp(e,txtRowLimit);
  }

  void this_windowOpened(WindowEvent e) {
    txtSuppId.requestFocus(true);
  }
}

class DialogSupplierSearch_txtRowLimit_keyAdapter extends java.awt.event.KeyAdapter {
    DialogSupplierSearch adaptee;

    DialogSupplierSearch_txtRowLimit_keyAdapter(DialogSupplierSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtRowLimit_keyTyped(e);
    }
  public void keyPressed(KeyEvent e) {
    adaptee.txtRowLimit_keyPressed(e);
  }
}

class DialogSupplierSearch_btnCancel_actionAdapter implements java.awt.event.ActionListener {
    DialogSupplierSearch adaptee;

    DialogSupplierSearch_btnCancel_actionAdapter(DialogSupplierSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnCancel_actionPerformed(e);
    }
}

class DialogSupplierSearch_btnSearch_actionAdapter implements java.awt.event.ActionListener {
    DialogSupplierSearch adaptee;

    DialogSupplierSearch_btnSearch_actionAdapter(DialogSupplierSearch adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnSearch_actionPerformed(e);
    }
}

class DialogSupplierSearch_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_table_mouseAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class DialogSupplierSearch_txtSuppName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_txtSuppName_keyAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtSuppName_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSuppName_keyTyped(e);
  }
}

class DialogSupplierSearch_txtSuppId_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_txtSuppId_keyAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtSuppId_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSuppId_keyTyped(e);
  }
}

class DialogSupplierSearch_txtAbbreName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_txtAbbreName_keyAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtAbbreName_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAbbreName_keyTyped(e);
  }
}

class DialogSupplierSearch_pnlInfoRightText_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_pnlInfoRightText_keyAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.pnlInfoRightText_keyPressed(e);
  }
}

class DialogSupplierSearch_txtContactName_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_txtContactName_keyAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtContactName_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtContactName_keyTyped(e);
  }
}

class DialogSupplierSearch_txtContactPhone_actionAdapter implements java.awt.event.ActionListener {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_txtContactPhone_actionAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtContactPhone_actionPerformed(e);
  }
}

class DialogSupplierSearch_txtContactPhone_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_txtContactPhone_keyAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtContactPhone_keyPressed(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtContactPhone_keyTyped(e);
  }
}

class DialogSupplierSearch_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_this_keyAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogSupplierSearch_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_table_keyAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogSupplierSearch_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogSupplierSearch adaptee;

  DialogSupplierSearch_this_windowAdapter(DialogSupplierSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
