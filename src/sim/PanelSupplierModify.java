package sim;

import java.util.*;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import btm.business.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import btm.attr.*;
import java.sql.Statement;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Supplier Setup -> Modify</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Nghi Doan
 * @version 1.0
 */

public class PanelSupplierModify extends JPanel {
    DataAccessLayer DAL;
    FrameMainSim frmMain;
    SupplierBusObj suppBusObj = new SupplierBusObj();
    Utils ut = new Utils();
    ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
    Vector vSql = new Vector();
    int nSuppID = 0;
    int lenCodeSuppId = 6;
    int rowNum = 5;
    Statement stmt = null;
//-----------------------declare BorderLayout------------------------------------------//
    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    BorderLayout borderLayout3 = new BorderLayout();

    FlowLayout flowLayout1 = new FlowLayout();
    FlowLayout flowLayout2 = new FlowLayout();
    FlowLayout flowLayout3 = new FlowLayout();
    FlowLayout flowLayout4 = new FlowLayout();

//-------------------------declare Panel Header--------------------------//
    BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
    //buttons
    BJButton btnModify = new BJButton();
    BJButton btnCancel = new BJButton();


//---------------------------------------------------------------------------------//
//-------------------------------declare Panel Infomation--------------------------//


//------------declare Panel Infomation Left--------------------------//
    JPanel pnlInfoLeft = new JPanel();

    //--------declare Panel Label Infomation Left--------------------------//
    JPanel pnlInfoLeftLabel = new JPanel();

    JLabel lblSuppID = new JLabel();
    JLabel lblSuppName = new JLabel();
    JLabel lblAbbreName = new JLabel();
    JLabel lblContactName = new JLabel();
    JLabel lblContactPhone = new JLabel();

    //--------declare Panel TextField Infomation Left--------------------------//
    JPanel pnlInfoLeftTextfield = new JPanel();

    JTextField txtSuppID = new JTextField();
    JTextField txtSuppName = new JTextField();
    JTextField txtAbbreName = new JTextField();
    JTextField txtContactName = new JTextField();
    JTextField txtContactPhone = new JTextField();

    JLabel lblStarSignName = new JLabel();

//--------------------------------------------------------------------------//
//-----------------declare Panel Infomation Right--------------------------//
    JPanel pnlInfoRight = new JPanel();

    //--------declare Panel Label Infomation Right--------------------------//
    JPanel pnlInfoRightLabel = new JPanel();

    JLabel lblContactFax = new JLabel();
    JLabel lblCurrencyCode = new JLabel();
    JLabel lblCountryCode = new JLabel();

    //--------declare Panel TextField Infomation Right--------------------------//
    JPanel pnlInfoRightTextfield = new JPanel();

    JTextField txtContactFax = new JTextField();
    BJComboBox cboCurrencyCode = new BJComboBox();
    BJComboBox cboCountryCode = new BJComboBox();

//---------------------------------------------------------------------------------//
//-------------------------------declare Panel Scroll Table------------------------//
    JScrollPane jScrollPane1 = new JScrollPane();
    SortableTableModel dm = new SortableTableModel();

    BJTable table = new BJTable(dm, true) {
        public Class getColumnClass(int col) {
            switch (col) {
                case 0:
                    return Long.class;
                default:
                    return Object.class;
            }
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    StripedTableCellRenderer str = new StripedTableCellRenderer(null,
            Color.lightGray, Color.white, null, null);
    JPanel pnlAlignLeft = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField txtVAT_No = new JTextField();
  JLabel jLabel2 = new JLabel();
  BJComboBox cboPayment = new BJComboBox();
    public PanelSupplierModify(FrameMainSim frmMain) {
        try {
            this.frmMain = frmMain;
            DAL = frmMain.getDAL();
            jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void jbInit() throws Exception {
        registerKeyboardActions();
        this.setBackground(UIManager.getColor("Label.background"));
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(borderLayout1);
        flowLayout3.setAlignment(FlowLayout.LEFT);
        flowLayout4.setAlignment(FlowLayout.LEFT);
        txtSuppID.setEditable(false);
        pnlAlignLeft.setMinimumSize(new Dimension(10, 50));
        pnlAlignLeft.setPreferredSize(new Dimension(20, 50));
        btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
        btnModify.setActionCommand("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                                   "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
        btnModify.addActionListener(new PanelSupplierModify_btnModify_actionAdapter(this));
        btnCancel.addActionListener(new PanelSupplierModify_btnCancel_actionAdapter(this));
        table.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        table.addKeyListener(new PanelSupplierModify_table_keyAdapter(this));
        txtContactFax.addKeyListener(new PanelSupplierModify_txtContactFax_keyAdapter(this));
        txtContactPhone.addKeyListener(new PanelSupplierModify_txtContactPhone_keyAdapter(this));
        txtContactName.addKeyListener(new PanelSupplierModify_txtContactName_keyAdapter(this));
        txtSuppName.addKeyListener(new PanelSupplierModify_txtSuppName_keyAdapter(this));
        txtAbbreName.addKeyListener(new PanelSupplierModify_txtAbbreName_keyAdapter(this));
        cboCurrencyCode.addKeyListener(new PanelSupplierModify_cboCurrencyCode_keyAdapter(this));
        cboCountryCode.addKeyListener(new PanelSupplierModify_cboCountryCode_keyAdapter(this));
        jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        jLabel1.setPreferredSize(new Dimension(110, 20));
        jLabel1.setText(lang.getString("VAT") + ":");
        txtVAT_No.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
        txtVAT_No.setPreferredSize(new Dimension(229, 20));
        txtVAT_No.setText("");
        txtVAT_No.setBounds(new Rectangle(26, 104, 229, 20));
        txtVAT_No.addKeyListener(new PanelSupplierModify_txtVAT_No_keyAdapter(this));
        jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        jLabel2.setForeground(Color.black);
        jLabel2.setPreferredSize(new Dimension(110, 20));
        jLabel2.setText(lang.getString("Payment") + ":");
        cboPayment.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
        cboPayment.setPreferredSize(new Dimension(210, 20));
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlAlignLeft, BorderLayout.WEST);
        this.add(pnlInfoLeft, BorderLayout.CENTER);
        this.add(pnlInfoRight, BorderLayout.EAST);
        this.add(jScrollPane1, BorderLayout.SOUTH);
        flowLayout1.setAlignment(FlowLayout.LEFT);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);

        //------------------------------------------------------------------------------------//
        //----------------Panel Header content all buttons------------------------------------//
        pnlHeader.setPreferredSize(new Dimension(850, 50));

        //Initial Buttons

        //Add
        btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
        btnModify.setPreferredSize(new Dimension(120, 37));
        btnModify.setToolTipText(lang.getString("Add") + " (F1)");
        btnModify.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                          "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
//        btnAdd.addActionListener(new PanelSupplierSetup_btnAdd_actionAdapter(this));

        //Cancel
        btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
        btnCancel.setPreferredSize(new Dimension(120, 37));
        btnCancel.setToolTipText(lang.getString("Cancel") + " (F2)");
        btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                          "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");

        pnlHeader.add(btnModify, null);
        pnlHeader.add(btnCancel, null);

        //----------------------------------------------------------------------------------//
        //--------------Panel Infomation Left-----------------------------------------------//
        pnlInfoLeft.setPreferredSize(new Dimension(450, 150));
        pnlInfoLeft.setLayout(borderLayout2);

        pnlInfoLeft.add(pnlInfoLeftLabel, BorderLayout.WEST);
        pnlInfoLeft.add(pnlInfoLeftTextfield, BorderLayout.CENTER);

        //-----Panel content Lable-------------------------------------------------------//
        pnlInfoLeftLabel.setPreferredSize(new Dimension(110, 10));

        lblSuppID.setText(lang.getString("SupplierID") + ":");
        lblSuppID.setPreferredSize(new Dimension(110, 20));
        lblSuppID.setHorizontalAlignment(SwingConstants.LEFT);
        lblSuppID.setHorizontalTextPosition(SwingConstants.TRAILING);
        lblSuppID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        lblSuppName.setText(lang.getString("SupplierName") + ":");
        lblSuppName.setPreferredSize(new Dimension(110, 20));
        lblSuppName.setHorizontalAlignment(SwingConstants.LEFT);
        lblSuppName.setHorizontalTextPosition(SwingConstants.TRAILING);
        lblSuppName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        lblAbbreName.setText(lang.getString("AbbreviationName") + ":");
        lblAbbreName.setPreferredSize(new Dimension(110, 20));
        lblAbbreName.setHorizontalAlignment(SwingConstants.LEFT);
        lblAbbreName.setHorizontalTextPosition(SwingConstants.TRAILING);
        lblAbbreName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        lblContactName.setText(lang.getString("ContactName") + ":");
        lblContactName.setPreferredSize(new Dimension(110, 20));
        lblContactName.setHorizontalAlignment(SwingConstants.LEFT);
        lblContactName.setHorizontalTextPosition(SwingConstants.TRAILING);
        lblContactName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        lblContactPhone.setText(lang.getString("ContactPhone") + ":");
        lblContactPhone.setPreferredSize(new Dimension(110, 20));
        lblContactPhone.setHorizontalAlignment(SwingConstants.LEFT);
        lblContactPhone.setHorizontalTextPosition(SwingConstants.TRAILING);
        lblContactPhone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        pnlInfoLeftLabel.add(lblSuppID, null);
        pnlInfoLeftLabel.add(lblSuppName, null);
        pnlInfoLeftLabel.add(lblAbbreName, null);
        pnlInfoLeftLabel.add(lblContactName, null);
    pnlInfoLeftLabel.add(jLabel1, null);

        //-----Panel content Text Field-------------------------------------------------------//
        pnlInfoLeftTextfield.setDebugGraphicsOptions(0);
        pnlInfoLeftTextfield.setPreferredSize(new Dimension(240, 10));
        pnlInfoLeftTextfield.setLayout(null);

        txtSuppID.setText("");
        txtSuppID.setSelectionStart(0);
        txtSuppID.setPreferredSize(new Dimension(200, 20));
        txtSuppID.setBounds(new Rectangle(26, 5, 229, 20));
        txtSuppID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));

        txtSuppName.setText("");
        txtSuppName.setSelectionStart(0);
        txtSuppName.setPreferredSize(new Dimension(245, 20));
        txtSuppName.setBounds(new Rectangle(26, 30, 229, 20));
        txtSuppName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));

        txtAbbreName.setText("");
        txtAbbreName.setSelectionStart(0);
        txtAbbreName.setPreferredSize(new Dimension(245, 20));
        txtAbbreName.setBounds(new Rectangle(26, 54, 229, 20));
        txtAbbreName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));

        txtContactName.setText("");
        txtContactName.setSelectionStart(0);
        txtContactName.setPreferredSize(new Dimension(245, 20));
        txtContactName.setBounds(new Rectangle(26, 79, 229, 20));
        txtContactName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));

        txtContactPhone.setText("");
        txtContactPhone.setSelectionStart(0);
        txtContactPhone.setPreferredSize(new Dimension(210, 20));
        txtContactPhone.setBounds(new Rectangle(10, 79, 245, 20));
        txtContactPhone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));

        lblStarSignName.setText("(*)");
        lblStarSignName.setForeground(Color.red);
        lblStarSignName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        pnlInfoLeftTextfield.add(txtSuppID, null);
        pnlInfoLeftTextfield.add(lblStarSignName, null);
        pnlInfoLeftTextfield.add(txtSuppName, null);
        pnlInfoLeftTextfield.add(txtAbbreName, null);
        pnlInfoLeftTextfield.add(txtContactName, null);
        pnlInfoLeftTextfield.add(txtVAT_No, null);

        lblSuppID.setDisplayedMnemonic('0');
        lblStarSignName.setBounds(new Rectangle(261, 32, 13, 16));

        //----------------------------------------------------------------------------------//
        //---------------Panel Information Right--------------------------------------------//
        pnlInfoRight.setPreferredSize(new Dimension(350, 150));
        pnlInfoRight.setLayout(borderLayout3);

        pnlInfoRight.add(pnlInfoRightTextfield, BorderLayout.CENTER);
        pnlInfoRight.add(pnlInfoRightLabel, BorderLayout.WEST);

        //-----Panel content Lable-------------------------------------------------------//
        pnlInfoRightLabel.setDebugGraphicsOptions(0);
        pnlInfoRightLabel.setPreferredSize(new Dimension(110, 10));
        pnlInfoRightLabel.setLayout(flowLayout3);

        lblContactFax.setText(lang.getString("Fax") + ":");
        lblContactFax.setPreferredSize(new Dimension(110, 20));
        lblContactFax.setHorizontalAlignment(SwingConstants.LEFT);
        lblContactFax.setHorizontalTextPosition(SwingConstants.TRAILING);
        lblContactFax.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        lblCurrencyCode.setText(lang.getString("Currency") + ":");
        lblCurrencyCode.setPreferredSize(new Dimension(110, 20));
        lblCurrencyCode.setHorizontalAlignment(SwingConstants.LEFT);
        lblCurrencyCode.setHorizontalTextPosition(SwingConstants.TRAILING);
        lblCurrencyCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

        lblCountryCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
        lblCountryCode.setPreferredSize(new Dimension(110, 20));
        lblCountryCode.setHorizontalAlignment(SwingConstants.LEFT);
        lblCountryCode.setText(lang.getString("Country") + ":");

        pnlInfoRightLabel.add(lblContactPhone, null);
        pnlInfoRightLabel.add(lblContactFax, null);
        pnlInfoRightLabel.add(lblCurrencyCode, null);
        pnlInfoRightLabel.add(lblCountryCode, null);

        //-----Panel content TextField-------------------------------------------------------//
        pnlInfoRightTextfield.setPreferredSize(new Dimension(230, 10));
        pnlInfoRightTextfield.setLayout(flowLayout4);

        txtContactFax.setText("");
        txtContactFax.setSelectionStart(0);
        txtContactFax.setPreferredSize(new Dimension(210, 20));
        txtContactFax.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
        txtContactFax.setBounds(new Rectangle(56, 105, 200, 20));


        cboCurrencyCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
        cboCurrencyCode.setPreferredSize(new Dimension(210, 20));
        cboCurrencyCode.setBounds(new Rectangle(40, 105, 200, 20));

        cboCountryCode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
        cboCountryCode.setPreferredSize(new Dimension(210, 20));
        cboCountryCode.setBounds(new Rectangle(40, 105, 200, 20));

        pnlInfoRightTextfield.add(txtContactPhone, null);
        pnlInfoRightTextfield.add(txtContactFax, null);
        pnlInfoRightTextfield.add(cboCurrencyCode, null);
        pnlInfoRightTextfield.add(cboCountryCode, null);

        //----------------------------------------------------------------------------------//
        //---------------Panel Scroll------------------------------------------------------//
        jScrollPane1.setPreferredSize(new Dimension(800, 370));

        jScrollPane1.getViewport().add(table, null);
    pnlInfoRightLabel.add(jLabel2, null);
    pnlInfoRightTextfield.add(cboPayment, null);

//        table.addMouseListener(new PanelSupplierSetup_table_mouseAdapter(this));

        String[] columnNames = new String[] {
                lang.getString("SupplierID") ,
                lang.getString("SupplierName") ,
                lang.getString("ContactName")  ,
                lang.getString("ContactPhone") };
        dm.setDataVector(new Object[][] {}
                         , columnNames);

        table.setColumnWidth(0, 120);
        table.setColumnWidth(1, 260);
        table.setColumnWidth(2, 260);
        table.setColumnWidth(3, 160);

//        nSuppID = initSuppId();
//        showSuppID();
        try {
          stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }

        cboCurrencyCode.setData(frmMain.suppBusObj.getCurrency(DAL,stmt));
        cboCountryCode.setData(frmMain.suppBusObj.getCountry(DAL,stmt));
        cboPayment.setData(getPaymentMethod(DAL,stmt));
        txtSuppName.requestFocus(true);
        table.setAutoCreateColumnsFromModel(false);
        try {
          stmt.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
    }
    //get data for combobox Payment
       ResultSet getPaymentMethod(DataAccessLayer DAL, Statement stmt){
         ResultSet rs = null;
         try {
           String sql = "Select PAYMENT_METHOD_ID, PAYMENT_METHOD_DESC From BTM_IM_PAYMENT_METHOD";
                       rs = DAL.executeScrollQueries(sql,stmt);
         }catch (Exception e) {
                       ExceptionHandle eh = new ExceptionHandle();
                       eh.ouputError(e);
         }
         return rs;
       }


    //Show data
    void showData(String suppId) {
      try {
        stmt = DAL.getConnection().createStatement();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }

        while (table.getRowCount() > 0) {
            dm.removeRow(0);
        }
        ResultSet rs = null;
        ResultSet rs1 = null;
        try {
            String sql = "select * from BTM_IM_SUPPLIER where supp_id ='" +
                    suppId + "'";
            rs = DAL.executeQueries(sql,stmt);
            if (rs.next()) {
                txtSuppID.setText(rs.getString("supp_id"));
                txtSuppName.setText(rs.getString("supp_name"));
                txtAbbreName.setText(rs.getString("abbreviation_name"));
                txtContactName.setText(rs.getString("contact_name"));
                txtContactPhone.setText(rs.getString("contact_phone"));
                txtContactFax.setText(rs.getString("contact_fax"));
                txtVAT_No.setText(rs.getString("vat_no"));
                cboPayment.setSelectedData(rs.getString("payment_method_id"));
                cboCurrencyCode.setSelectedItem(rs.getString("SUPP_CURR_CDE"));
                String cntryId = rs.getString("CNTRY_CDE");
                sql = "select CNTRY_NAME from BTM_IM_COUNTRY_CDE where CNTRY_CDE ='" +
                        cntryId + "'";
                rs1 = DAL.executeQueries(sql,stmt);
                if(rs1.next()){
                    cboCountryCode.setSelectedItem(rs1.getString("CNTRY_NAME"));
                }
            }
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        try {
          stmt.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }

    }


    void txtSuppName_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e, txtSuppName, Supplier.LEN_SUP_CONTACT_NAME);
        navigationComp(e,txtAbbreName);
    }

    void navigationComp(KeyEvent e, JTextField txt) {
      if (e.getKeyChar() == KeyEvent.VK_ENTER ||
          e.getKeyChar() == KeyEvent.VK_TAB) {
        txt.requestFocus(true);
        txt.selectAll();
      }
    }

    void txtContactName_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e, txtSuppName, Supplier.LEN_SUP_NAME);
        navigationComp(e,txtContactPhone);
    }


    void txtContactPhone_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e, txtContactPhone,Supplier.LEN_SUP_CONTACT_PHONE);
        ut.checkPhone(e);
        navigationComp(e,txtContactFax);
    }


    void txtContactFax_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e, txtContactFax,Supplier.LEN_SUP_CONTACT_FAX);
        ut.checkPhone(e);
        navigationComp(e,cboCurrencyCode);
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
        // ENTER
        key = new Integer(KeyEvent.VK_ENTER);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
        actionMap.put(key, new KeyAction(key));


        // ESCAPE
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


           if (identifier.intValue() == KeyEvent.VK_F1 ) {
                btnModify.doClick();
            }

            else if (identifier.intValue() == KeyEvent.VK_F2 ||
                     identifier.intValue() == KeyEvent.VK_ESCAPE) {
                btnCancel.doClick();
            }
        }
    }

    void table_mousePressed(MouseEvent e) {
//    btnDone.requestFocus();
    }


    void btnCancel_actionPerformed(ActionEvent e) {
        DialogSupplierSearch dlgSuppSearch = new DialogSupplierSearch(frmMain,
                lang.getString("TT0003"), true, frmMain, frmMain.suppBusObj);

        dlgSuppSearch.setText(frmMain.suppBusObj.getSuppId(),
                              frmMain.suppBusObj.getSuppName(),
                              frmMain.suppBusObj.getAbbreName(),
                              frmMain.suppBusObj.getContactName(),
                              frmMain.suppBusObj.getContactPhone());

        dlgSuppSearch.searchData(frmMain.pnlSupplierSetup.rowNum);
        dlgSuppSearch.txtRowLimit.setText("" + frmMain.pnlSupplierSetup.rowNum);
        dlgSuppSearch.setVisible(true);
        if (dlgSuppSearch.done == false) {
            frmMain.showScreen(Constant.SCRS_SUPPLIER_SETUP);
        }
        else {
            showData(dlgSuppSearch.getData());
            txtSuppName.requestFocus(true);
        }

    }

    void btnModify_actionPerformed(ActionEvent e) {
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.
                                                   TYPE_SCROLL_INSENSITIVE,
                                                   ResultSet.CONCUR_UPDATABLE);
      }
      catch(Exception ex){}
      String suppID = txtSuppID.getText().trim();
      String suppName = txtSuppName.getText().trim();
      String abbreName = txtAbbreName.getText().trim();
      String contactName = txtContactName.getText().trim();
      String contactPhone = txtContactPhone.getText().trim();
      String contactFax = txtContactFax.getText().trim();
      String currCode = cboCurrencyCode.getSelectedData();
      String cntryCode = cboCountryCode.getSelectedData();
      String paymentCode = cboPayment.getSelectedData();
      String vat_No = txtVAT_No.getText().trim();

        if(suppName.equals("")){
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS301_SuppNameNotNull"));
            txtSuppName.requestFocus(true);
            return;
        }
        if (ut.checkPhoneNumber(contactPhone) == false) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS800_WrongNum"));
            txtContactPhone.requestFocus(true);
            return;
        }
        if (ut.checkPhoneNumber(contactFax) == false) {
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS800_WrongNum"));
            txtContactFax.requestFocus(true);
            return;
        }



        String sql = "update BTM_IM_SUPPLIER set SUPP_NAME ='" + suppName + "',ABBREVIATION_NAME='" + abbreName +
                "',CONTACT_NAME='" + contactName +
                "',CONTACT_PHONE='" + contactPhone + "',CONTACT_FAX='" + contactFax + "',SUPP_CURR_CDE='" +currCode + "',CNTRY_CDE='" + cntryCode +
                "',RECD_LAST_UPD_DATE=" + "to_date('" + ut.getSystemDate(1) + "','DD-MM-YYYY'),PAYMENT_METHOD_ID='"  + paymentCode + "', VAT_NO='"
                + vat_No + "' where SUPP_ID='" + suppID + "'";
//            System.out.println(sql);
       DAL.executeUpdate(sql,stmt);
       txtSuppName.setText("");
       txtContactName.setText("");
       txtContactPhone.setText("");
       txtContactFax.setText("");
       DialogSupplierSearch dlgSuppSearch = new DialogSupplierSearch(frmMain,
               lang.getString("TT0003"),true,frmMain,frmMain.suppBusObj);
       dlgSuppSearch.setText(frmMain.suppBusObj.getSuppId(), frmMain.suppBusObj.getSuppName(), frmMain.suppBusObj.getContactName(),
                             frmMain.suppBusObj.getContactName(),frmMain.suppBusObj.getContactPhone());
       dlgSuppSearch.searchData(frmMain.pnlSupplierSetup.rowNum);
       dlgSuppSearch.txtRowLimit.setText("" + frmMain.pnlSupplierSetup.rowNum);
       dlgSuppSearch.setVisible(true);
       if (dlgSuppSearch.done == false) {
           frmMain.showScreen(Constant.SCRS_SUPPLIER_SETUP);
       }
       else {
           suppID = dlgSuppSearch.getData();
           showData(suppID);
           txtSuppName.requestFocus(true);
       }
       try {
         stmt.close();
       }
       catch (Exception ex) {
         ex.printStackTrace();
       }

    }

  void txtAbbreName_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e, txtAbbreName, Supplier.LEN_SUP_ABBRE_NAME);
      navigationComp(e,txtContactName);
  }

  void cboCurrencyCode_keyTyped(KeyEvent e) {
    navigationComp(e,cboCountryCode);
  }
  void navigationComp(KeyEvent e, JComboBox cbo){
    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
      cbo.requestFocus(true);
    }
  }

  void cboCountryCode_keyTyped(KeyEvent e) {
    if(e.getKeyChar()==KeyEvent.VK_ENTER){
      btnModify.doClick();
    }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnCancel.doClick();
               }
  }

  void txtVAT_No_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtVAT_No,12);
      if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
          (e.getKeyChar() != e.VK_BACK_SPACE))
        e.consume();
  }

}



class PanelSupplierModify_txtSuppId_keyAdapter
        extends java.awt.event.KeyAdapter {
    PanelSupplierModify adaptee;

    PanelSupplierModify_txtSuppId_keyAdapter(PanelSupplierModify adaptee) {
        this.adaptee = adaptee;
    }
}



class PanelSupplierModify_table_mouseAdapter
        extends java.awt.event.MouseAdapter {
    PanelSupplierModify adaptee;

    PanelSupplierModify_table_mouseAdapter(PanelSupplierModify adaptee) {
        this.adaptee = adaptee;
    }

    public void mousePressed(MouseEvent e) {
        adaptee.table_mousePressed(e);
    }
}

class PanelSupplierModify_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelSupplierModify adaptee;

  PanelSupplierModify_btnCancel_actionAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelSupplierModify_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelSupplierModify adaptee;

  PanelSupplierModify_btnModify_actionAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelSupplierModify_txtContactFax_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierModify adaptee;

  PanelSupplierModify_txtContactFax_keyAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtContactFax_keyTyped(e);
  }
}

class PanelSupplierModify_txtContactPhone_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierModify adaptee;

  PanelSupplierModify_txtContactPhone_keyAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtContactPhone_keyTyped(e);
  }
}

class PanelSupplierModify_txtContactName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierModify adaptee;

  PanelSupplierModify_txtContactName_keyAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtContactName_keyTyped(e);
  }
}

class PanelSupplierModify_txtSuppName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierModify adaptee;

  PanelSupplierModify_txtSuppName_keyAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSuppName_keyTyped(e);
  }
}

class PanelSupplierModify_txtAbbreName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierModify adaptee;

  PanelSupplierModify_txtAbbreName_keyAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAbbreName_keyTyped(e);
  }
}

class PanelSupplierModify_cboCurrencyCode_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierModify adaptee;

  PanelSupplierModify_cboCurrencyCode_keyAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboCurrencyCode_keyTyped(e);
  }
}

class PanelSupplierModify_cboCountryCode_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierModify adaptee;

  PanelSupplierModify_cboCountryCode_keyAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboCountryCode_keyTyped(e);
  }
}

class PanelSupplierModify_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierModify adaptee;

  PanelSupplierModify_table_keyAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }

}




class PanelSupplierModify_txtVAT_No_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierModify adaptee;

  PanelSupplierModify_txtVAT_No_keyAdapter(PanelSupplierModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtVAT_No_keyTyped(e);
  }
}
