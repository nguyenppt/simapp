package sim;

import java.sql.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import btm.attr.*;
import btm.business.*;
import btm.swing.*;
import common.*;
import java.sql.Statement;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Main->Merch Mgmt->Supplier Setup</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM InterSoft</p>
 * @author Phuoc Nguyen
 * @version 1.0
 */

public class PanelSupplierSetup extends JPanel {

        DataAccessLayer DAL;
        FrameMainSim frmMain;
        SupplierBusObj suppBusObj = new SupplierBusObj();
        Utils ut = new Utils();
        ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
        Vector vSql = new Vector();
        boolean flagSetHotKeyForAdd = true;
        int nSuppID = 0;
        int lenCodeSuppId = 5;
        String hostId = "";
        int rowNum = 5;
        int  vPosition = 0;
        Statement stmt = null;
        Vector vModify = new Vector();
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
        BJButton btnDone = new BJButton();
        BJButton btnAdd = new BJButton();
        BJButton btnDelete = new BJButton();
        BJButton btnSearch = new BJButton();
        BJButton btnClear = new BJButton();
        BJButton btnCancel = new BJButton();
        BJButton btnModify = new BJButton();

//---------------------------------------------------------------------------------//
//-------------------------------declare Panel Infomation--------------------------//


//------------declare Panel Infomation Left--------------------------//
        JPanel pnlInfoLeft = new JPanel();

        //--------declare Panel Label Infomation Left--------------------------//
        JPanel pnlInfoLeftLabel = new JPanel();

        JLabel lblSuppID = new JLabel();
        JLabel lblSuppName = new JLabel();
        JLabel lblContactName = new JLabel();
        JLabel lblAbbreName = new JLabel();
        JLabel lblContactPhone = new JLabel();

    //--------declare Panel TextField Infomation Left--------------------------//
        JPanel pnlInfoLeftTextfield = new JPanel();

        JTextField txtSuppID = new JTextField();
        JTextField txtSuppName = new JTextField();
        JTextField txtContactName = new JTextField();
        JTextField txtAbbreName = new JTextField();
        JTextField txtContactPhone = new JTextField();

        JLabel lblStarSignName = new JLabel();

///bo
        JTextField txtRecdLastUpdDate = new JTextField();
        JTextField txtRecdCurrFlag = new JTextField();

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
        BJComboBox cboPayment = new BJComboBox();


//---------------------------------------------------------------------------------//
//-------------------------------declare Panel Scroll Table------------------------//
        JScrollPane jScrollPane1 = new JScrollPane();
        SortableTableModel dm = new SortableTableModel();

        BJTable table = new BJTable(dm, true) {
            public Class getColumnClass(int col) {
                switch (col) {
                    case 0:
                        return String.class;
                    default:
                        return Object.class;
                }
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray, Color.white, null, null);

////////////////////////////////////////////////////////////////////////////////////////
//Constructor
        public PanelSupplierSetup(FrameMainSim frmMain) {
                try {
                        this.frmMain = frmMain;
                        DAL = frmMain.getDAL();
                        jbInit();
                }
                catch (Exception ex) {
                        ex.printStackTrace();
                }
        }
        JPanel pnlAlignLeft = new JPanel();

  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField txtVAT_No = new JTextField();
////////////////END CONSTRUCTOR///////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////
//Initial all
        void jbInit() throws Exception {
            registerKeyboardActions();
            //----------------------------------------------------------------------------------//
            //---------------This(Border Layout)------------------------------------------------//
            this.setBackground(UIManager.getColor("Label.background"));
            this.setPreferredSize(new Dimension(800, 600));
            this.setLayout(borderLayout1);
            flowLayout3.setAlignment(FlowLayout.LEFT);
            flowLayout4.setAlignment(FlowLayout.LEFT);
            txtSuppID.setEditable(false);
            pnlAlignLeft.setMinimumSize(new Dimension(10, 50));
            pnlAlignLeft.setPreferredSize(new Dimension(20, 50));
            btnModify.addActionListener(new PanelSupplierSetup_btnModify_actionAdapter(this));
            txtAbbreName.addKeyListener(new PanelSupplierSetup_txtAbbreName_keyAdapter(this));
            cboCurrencyCode.addKeyListener(new
            PanelSupplierSetup_cboCurrencyCode_keyAdapter(this));
            cboCountryCode.addKeyListener(new PanelSupplierSetup_cboCountryCode_keyAdapter(this));
            table.addKeyListener(new PanelSupplierSetup_table_keyAdapter(this));
            jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
            jLabel1.setPreferredSize(new Dimension(98, 20));
            jLabel1.setText(lang.getString("Payment") + ":");
            cboPayment.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1,11));
            cboPayment.setPreferredSize(new Dimension(210, 20));
            jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
            jLabel2.setPreferredSize(new Dimension(110, 20));
            jLabel2.setText(lang.getString("VAT") + ":");
            txtVAT_No.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
            txtVAT_No.setPreferredSize(new Dimension(245, 20));
            txtVAT_No.setText("");
            txtVAT_No.setBounds(new Rectangle(25, 107, 230, 20));
            txtVAT_No.addKeyListener(new PanelSupplierSetup_txtVAT_No_keyAdapter(this));
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
            //Done
            btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
            btnDone.setPreferredSize(new Dimension(120, 37));
            btnDone.setToolTipText(lang.getString("Done") + " (F1)");
            btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
            btnDone.addActionListener(new PanelSupplierSetup_btnDone_actionAdapter(this));
            //Add
            btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
            btnAdd.setPreferredSize(new Dimension(120, 37));
            btnAdd.setToolTipText(lang.getString("Add") + " (F2)");
            btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
            btnAdd.addActionListener(new PanelSupplierSetup_btnAdd_actionAdapter(this));
            //Search
            btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
            btnSearch.setPreferredSize(new Dimension(120, 37));
            btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
            btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
            btnSearch.addActionListener(new PanelSupplierSetup_btnSearch_actionAdapter(this));
            //Delete
            btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
            btnDelete.setPreferredSize(new Dimension(120, 37));
            btnDelete.setToolTipText(lang.getString("Delete") + " (F8)");
            btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
            btnDelete.addActionListener(new PanelSupplierSetup_btnDelete_actionAdapter(this));

            //Clear
            btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
            btnClear.setPreferredSize(new Dimension(120, 37));
            btnClear.setToolTipText(lang.getString("Clear") + " (F7)");
            btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
            btnClear.addActionListener(new PanelSupplierSetup_btnClear_actionAdapter(this));
            //Cancel
            btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
            btnCancel.setPreferredSize(new Dimension(120, 37));
            btnCancel.setToolTipText(lang.getString("Cancel") +" (F12)");
            btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
            "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
            btnCancel.addActionListener(new PanelSupplierSetup_btnCancel_actionAdapter(this));

            btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
            btnModify.setToolTipText(lang.getString("Modify") + " (F4)");
            btnModify.setText("<html><center><b>"+lang.getString("Modify")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                              "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
            btnModify.setPreferredSize(new Dimension(120, 37));
            btnModify.setVisible(false);

            pnlHeader.add(btnDone, null);
            pnlHeader.add(btnAdd, null);
            pnlHeader.add(btnSearch, null);
            pnlHeader.add(btnModify, null);

            pnlHeader.add(btnClear, null);
            pnlHeader.add(btnDelete, null);

            pnlHeader.add(btnCancel, null);

            //----------------------------------------------------------------------------------//
            //--------------Panel Infomation Left-----------------------------------------------//
            pnlInfoLeft.setPreferredSize(new Dimension(400, 150));
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

            lblContactName.setText(lang.getString("ContactName") + ":");
            lblContactName.setPreferredSize(new Dimension(110, 20));
            lblContactName.setHorizontalAlignment(SwingConstants.LEFT);
            lblContactName.setHorizontalTextPosition(SwingConstants.TRAILING);
            lblContactName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

            lblAbbreName.setText(lang.getString("AbbreviationName") + ":");
            lblAbbreName.setPreferredSize(new Dimension(110, 20));
            lblAbbreName.setHorizontalAlignment(SwingConstants.LEFT);
            lblAbbreName.setHorizontalTextPosition(SwingConstants.TRAILING);
            lblAbbreName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

            lblContactPhone.setText(lang.getString("ContactPhone") + ":");
            lblContactPhone.setPreferredSize(new Dimension(120, 20));
            lblContactPhone.setHorizontalAlignment(SwingConstants.LEFT);
            lblContactPhone.setHorizontalTextPosition(SwingConstants.TRAILING);
            lblContactPhone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

            pnlInfoLeftLabel.add(lblSuppID, null);
            pnlInfoLeftLabel.add(lblSuppName, null);
            pnlInfoLeftLabel.add(lblAbbreName, null);
            pnlInfoLeftLabel.add(lblContactName, null);

            //-----Panel content Text Field-------------------------------------------------------//
            pnlInfoLeftTextfield.setDebugGraphicsOptions(0);
            pnlInfoLeftTextfield.setPreferredSize(new Dimension(230, 10));
            pnlInfoLeftTextfield.setLayout(null);

            txtSuppID.setText("");
            txtSuppID.setSelectionStart(0);
            txtSuppID.setPreferredSize(new Dimension(200, 20));
            txtSuppID.setBounds(new Rectangle(25, 5, 231, 20));
            txtSuppID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));

            txtSuppName.setText("");
            txtSuppName.setSelectionStart(0);
            txtSuppName.setPreferredSize(new Dimension(240, 20));
            txtSuppName.setBounds(new Rectangle(25, 30, 231, 20));
            txtSuppName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
            txtSuppName.addKeyListener(new
                                       PanelSupplierSetup_txtSuppName_keyAdapter(this));

            txtContactName.setText("");
            txtContactName.setSelectionStart(0);
            txtContactName.setPreferredSize(new Dimension(245, 20));
            txtContactName.setBounds(new Rectangle(25, 81, 231, 20));
            txtContactName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
            txtContactName.addKeyListener(new
                                          PanelSupplierSetup_txtContactName_keyAdapter(this));

            txtAbbreName.setText("");
            txtAbbreName.setSelectionStart(0);
            txtAbbreName.setPreferredSize(new Dimension(245, 20));
            txtAbbreName.setBounds(new Rectangle(25, 56, 231, 20));
            txtAbbreName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));


            txtContactPhone.setText("");
            txtContactPhone.setSelectionStart(0);
            txtContactPhone.setPreferredSize(new Dimension(210, 20));
            txtContactPhone.setBounds(new Rectangle(11, 79, 245, 20));
            txtContactPhone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
            txtContactPhone.addKeyListener(new
                    PanelSupplierSetup_txtContactPhone_keyAdapter(this));

            lblStarSignName.setText("(*)");
            lblStarSignName.setForeground(Color.red);
            lblStarSignName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

            pnlInfoLeftTextfield.add(txtSuppID, null);
            pnlInfoLeftTextfield.add(txtSuppName, null);
            pnlInfoLeftTextfield.add(lblStarSignName, null);
            pnlInfoLeftTextfield.add(txtAbbreName, null);
            pnlInfoLeftTextfield.add(txtContactName, null);


            lblSuppID.setDisplayedMnemonic('0');
            lblStarSignName.setBounds(new Rectangle(261, 32, 13, 16));

            //----------------------------------------------------------------------------------//
            //---------------Panel Information Right--------------------------------------------//
            pnlInfoRight.setPreferredSize(new Dimension(360, 150));
            pnlInfoRight.setLayout(borderLayout3);

            pnlInfoRight.add(pnlInfoRightTextfield, BorderLayout.CENTER);
            pnlInfoRight.add(pnlInfoRightLabel, BorderLayout.WEST);

            //-----Panel content Lable-------------------------------------------------------//
            pnlInfoRightLabel.setDebugGraphicsOptions(0);
            pnlInfoRightLabel.setPreferredSize(new Dimension(120, 10));
            pnlInfoRightLabel.setLayout(flowLayout3);

            lblContactFax.setText(lang.getString("Fax") + ":");
            lblContactFax.setPreferredSize(new Dimension(120, 20));
            lblContactFax.setHorizontalAlignment(SwingConstants.LEFT);
            lblContactFax.setHorizontalTextPosition(SwingConstants.TRAILING);
            lblContactFax.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

            lblCurrencyCode.setText(lang.getString("Currency") + ":");
            lblCurrencyCode.setPreferredSize(new Dimension(120, 20));
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
            pnlInfoRightLabel.add(jLabel1, null);

            //-----Panel content TextField-------------------------------------------------------//
            pnlInfoRightTextfield.setPreferredSize(new Dimension(230, 10));
            pnlInfoRightTextfield.setLayout(flowLayout4);

            txtContactFax.setText("");
            txtContactFax.setSelectionStart(0);
            txtContactFax.setPreferredSize(new Dimension(210, 20));
            txtContactFax.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
            txtContactFax.setBounds(new Rectangle(56, 105, 200, 20));
            txtContactFax.addKeyListener(new
                    PanelSupplierSetup_txtContactFax_keyAdapter(this));

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
            pnlInfoRightTextfield.add(cboPayment, null);

            //----------------------------------------------------------------------------------//
            //---------------Panel Scroll------------------------------------------------------//
            //jScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());
            //				jScrollPane1.setToolTipText("");
            jScrollPane1.setPreferredSize(new Dimension(800, 360));

            jScrollPane1.getViewport().add(table, null);
            pnlInfoLeftLabel.add(jLabel2, null);
            pnlInfoLeftTextfield.add(txtVAT_No, null);

            table.addMouseListener(new PanelSupplierSetup_table_mouseAdapter(this));

            String[] columnNames = new String[] {lang.getString("SupplierID"),
                                                 lang.getString("SupplierName"),
                                                 lang.getString("ContactName"),
                                                 lang.getString("ContactPhone")};
            dm.setDataVector(new Object[][] {}
                             , columnNames);

            table.setColumnWidth(0, 120);
            table.setColumnWidth(1, 260);
            table.setColumnWidth(2, 260);
            table.setColumnWidth(3, 160);
            resetButtonLabel();
        }




 //apply employee's permission for this screen
 public void applyPermission() {
   EmpRight er = new EmpRight();
   er.initData(DAL, DAL.getEmpID());
   er.setData(DAL.getEmpID(), Constant.SCRS_SUPPLIER_SETUP);
   btnAdd.setEnabled(er.getAdd());
   btnDelete.setEnabled(er.getDelete());
   btnModify.setEnabled(er.getModify());
   btnSearch.setEnabled(er.getSearch());

 }



////////////////////////////////Initial finish ///////////////////////////////////////////////////////////////////////

    //////generate Supplier ID////////////////////////////////////////////////////////////////////////////////////////
       //init: supp_id=host*10000, then increament by 1
        private int initSuppId() {
            int id = 0;
            id = suppBusObj.getMaxSuppID(DAL,hostId);
            if(id == 0){
                id = Integer.parseInt(hostId);
                id  = id*100000;
            }
            id = id + 1;
            return id;
        }

        private String getAddSuppId(int num) {
//            String suppId = null;
//            num = num;
//            suppId = ut.getYeroCode(num,lenCodeSuppId);
//            return suppId;
            return String.valueOf(num);
        }

        public void showSuppID(){
             txtSuppID.setText(getAddSuppId(nSuppID));
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//to standardize all Text Field
        //--------------------------------Repaired----------------------------------------------------------//
        void txtSuppName_keyTyped(KeyEvent e) {
            ut.limitLenjTextField(e, txtSuppName, Supplier.LEN_SUP_NAME);
    //			char keyCode = e.getKeyChar();
    //			if (isNumber(keyCode))
    //				e.consume();
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
            ut.limitLenjTextField(e, txtContactName, Supplier.LEN_SUP_CONTACT_NAME);
    //			char keyCode = e.getKeyChar();
    //			if (isNumber(keyCode))
    //				e.consume();
            navigationComp(e,txtContactPhone);
        }

        void txtContactPhone_keyTyped(KeyEvent e) {
            ut.limitLenjTextField(e, txtContactPhone,
                                  Supplier.LEN_SUP_CONTACT_PHONE);
            char keyCode = e.getKeyChar();
            if (!isCharInPhoneNo(keyCode))
                e.consume();
             navigationComp(e,txtContactFax);
        }

        void txtContactFax_keyTyped(KeyEvent e) {
            ut.limitLenjTextField(e, txtContactFax, Supplier.LEN_SUP_CONTACT_FAX);
            char keyCode = e.getKeyChar();
            if (!isCharInPhoneNo(keyCode))
                e.consume();
            navigationComp(e,cboCurrencyCode);
        }

        void navigationComp(KeyEvent e, JComboBox cbo) {
          if (e.getKeyChar() == KeyEvent.VK_ENTER ||
              e.getKeyChar() == KeyEvent.VK_TAB) {
            cbo.requestFocus(true);
          }
        }


        public boolean isCharInPhoneNo(char c) {
            if (isNumber(c) || c == 8 || c == 32 || c == 40 || c == 41 || c == 45 ||
                c == 95)
                return true;
            return false;
        }

        public boolean isNumber(char c) {
            if (c < 48 || c > 57)
                return false;
            return true;
        }


//--------------------------END --------------------------------------------------//

////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public void initScreen(){
          try {
              stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            }
            catch (Exception ex) {
              ex.printStackTrace();
            }

            hostId = DAL.getHostID();
            nSuppID = initSuppId();
            showSuppID();
            cboCurrencyCode.setData(frmMain.suppBusObj.getCurrency(DAL,stmt));
//            try {
//              stmt.close();
//
//            }
//            catch (Exception ex) {
//              ex.printStackTrace();
//            }

            cboCountryCode.setData(frmMain.suppBusObj.getCountry(DAL,stmt));
//            try {
//              stmt.close();
//
//            }
//            catch (Exception ex) {
//              ex.printStackTrace();
//            }

            cboCountryCode.setSelectedItem("VIET NAM");
            cboCurrencyCode.setSelectedItem("VND");
            cboPayment.setData(getPaymentMethod(DAL,stmt));
            txtSuppName.requestFocus(true);
            try {
              stmt.close();

            }
            catch (Exception ex) {
              ex.printStackTrace();
            }
       }
        //Click button
        public void resetData() {
            txtSuppName.setText("");
            txtAbbreName.setText("");
            txtContactName.setText("");
            txtContactPhone.setText("");
            txtContactFax.setText("");
            txtVAT_No.setText("");
            cboCountryCode.setSelectedItem("VIET NAM");
            cboCurrencyCode.setSelectedItem("USD");
            cboPayment.setSelectedIndex(0);
            showSuppID();
            setButton(true,false);
            resetButtonLabel();
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

        //set button
        void setButton(boolean flag, boolean flag1) {
            btnAdd.setVisible(flag);
            btnDelete.setVisible(flag);
            btnModify.setVisible(flag1);
        }

        //Clear
        void btnClear_actionPerformed(ActionEvent e) {
              resetData();
              flagSetHotKeyForAdd = true;
              txtSuppName.requestFocus(true);
        }

        //Cancel
        void btnCancel_actionPerformed(ActionEvent e) {
            resetData();
            vModify.clear();
            vSql.clear();
            flagSetHotKeyForAdd = true;
            while (table.getRowCount() > 0) {
                dm.removeRow(0);
            }
            nSuppID = initSuppId();
            showSuppID();
            frmMain.showScreen(Constant.SCRS_MAIN_SIM);
            frmMain.pnlMainSim.showMerchanSystemButton();
            frmMain.setTitle(lang.getString("TT017"));
        }

        //Add
        void btnAdd_actionPerformed(ActionEvent e) {
            String suppID = txtSuppID.getText().trim();
            String suppName = txtSuppName.getText().trim();
            String abbreName = txtAbbreName.getText().trim();
            String contactName = txtContactName.getText().trim();
            String contactPhone = txtContactPhone.getText().trim();
            String contactFax = txtContactFax.getText().trim();
            String currCode = cboCurrencyCode.getSelectedData();
            String cntryCode = cboCountryCode.getSelectedData();
            String vat_No = txtVAT_No.getText().trim();
            String paymentCode = cboPayment.getSelectedData();
            //check data text field
            if (suppName.equals("")) {
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

             //insert table
             Object[] rowData = new Object[] {suppID, suppName, contactName, contactPhone};
             dm.addRow(rowData);
//             str.installInTable(table, Color.white, Color.black, Color.lightGray,Color.white);
             float[] f1 = new float[3];
             Color.RGBtoHSB(255,255,230,f1);
             str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

             //set focus for last row
              if(table.getRowCount()>0){
                table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
              }

              //show current row
              Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
              jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

             String contactPager = "null";
             String suppStatus = "null";
             String loadDate = ut.getSystemDate(1);
             String updDate = ut.getSystemDate(1);
             String currFlag = "1";
             String closeDate = "7/7/7777";
             String returnAllowInd = "null";
             String returnAuthReq = "null";
             double returnMinDolAmt = 0;
             String returnCourier = "null";
             String freightChargeInd = "null";
             int vatReason = 0;
             String invcPayLoc = "null";
             String invcReceiveLoc = "null";
             vSql.addElement("insert into BTM_IM_SUPPLIER(SUPP_ID, SUPP_NAME, CONTACT_NAME, CONTACT_PHONE," +
                             "CONTACT_FAX, CONTACT_PAGER, SUPP_STATUS, SUPP_CURR_CDE, RECD_LOAD_DATE," +
                             "RECD_LAST_UPD_DATE, RECD_CURR_FLAG, RECD_CLOSED_DATE, RETURN_ALLOW_IND," +
                             "RETURN_AUTH_REQ, RETURN_MIN_DOL_AMT, RETURN_COURIER, FREIGHT_CHARGE_IND,"+
                             "VAT_REGION, INVC_PAY_LOC, INVC_RECEIVE_LOC, CNTRY_CDE, ABBREVIATION_NAME,"+
                             "PAYMENT_METHOD_ID, VAT_NO)"
                             +"values('" + suppID + "','" + suppName + "','" + contactName + "','"
                             + contactPhone + "','" + contactFax + "'," + contactPager + "," + suppStatus
                             + ",'" + currCode + "',to_date('" + loadDate + "','DD-MM-YYYY'),to_date('"
                             + updDate + "','DD-MM-YYYY'),'" + currFlag + "',to_date('" + closeDate
                             + "','DD-MM-YYYY')," +  returnAllowInd + "," + returnAuthReq + ","
                             + returnMinDolAmt + "," + returnCourier + "," + freightChargeInd + ","
                             + vatReason + "," + invcPayLoc + "," + invcReceiveLoc + ",'" + cntryCode
                             + "','"+ abbreName + "','" + paymentCode + "','" + vat_No + "')");

             nSuppID++;
             resetData();
             txtSuppName.requestFocus(true);
             Vector vData = new Vector();
             vData.addElement(suppID);
             vData.addElement(suppName);
             vData.addElement(abbreName);
             vData.addElement(contactName);
             vData.addElement(contactPhone);
             vData.addElement(contactFax);
             vData.addElement(currCode);
             vData.addElement(cntryCode);
             vData.addElement(paymentCode);
             vData.addElement(vat_No);
             vModify.addElement(vData);

        }
        //Done
        void btnDone_actionPerformed(ActionEvent e) {
            String suppName = txtSuppName.getText().trim();
            String contactPhone = txtContactPhone.getText().trim();
            String contactFax = txtContactFax.getText().trim();
            try {
              stmt = DAL.getConnection().createStatement();
            }
            catch (Exception ex) {
              ex.printStackTrace();
            }


            if (!suppName.equals("") && table.getRowCount()==0) {
                    int i = ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS302_HelpSave"),
                                       DialogMessage.QUESTION_MESSAGE,
                                       DialogMessage.YES_NO_OPTION);
                    if(i==DialogMessage.YES_VALUE){
                      return;
                    }
            }
//            if (ut.checkPhoneNumber(contactPhone) == false) {
//                     ut.showMessage(frmMain, "Warning!", "Wrong number");
//                     txtContactPhone.requestFocus(true);
//                     return;
//             }
//             if (ut.checkPhoneNumber(contactFax) == false) {
//                     ut.showMessage(frmMain, "Warning!", "Wrong number");
//                     txtContactFax.requestFocus(true);
//                     return;
//             }

            if (vSql.isEmpty() == false) {
                DAL.setBeginTrans(DAL.getConnection());
                DAL.executeBatchQuery(vSql,stmt);
                DAL.setEndTrans(DAL.getConnection());
            }
            while (dm.getRowCount() > 0) {
                dm.removeRow(0);
                //////////

            }
            resetData();
            vSql.clear();
            vModify.clear();
            flagSetHotKeyForAdd = true;
            frmMain.showScreen(Constant.SCRS_MAIN_SIM);
            frmMain.pnlMainSim.showMerchanSystemButton();
            frmMain.setTitle(lang.getString("TT0002"));
            try {
              stmt.close();

            }
            catch (Exception ex) {
              ex.printStackTrace();
            }

        }
        //Delete
        void btnDelete_actionPerformed(ActionEvent e) {
            if (table.getSelectedRow() == -1) {
                return;
            }
            if (vSql.isEmpty()) {
                return;
            }
            if (table.getRowCount() == 1) {
                dm.removeRow(0);
                vSql.removeAllElements();
                vModify.removeAllElements();
                flagSetHotKeyForAdd = true;
                return;
            }
            int[] i = table.getSelectedRows();
            for (int j = 0; j < i.length; j++) {
                vSql.removeElementAt(i[0]);
                vModify.removeElementAt(i[0]);
                dm.removeRow(i[0]);

            }
            flagSetHotKeyForAdd = true;

        }

        void btnSearch_actionPerformed(ActionEvent e) {
            DialogSupplierSearch dlgSuppSearch = new DialogSupplierSearch(frmMain,
                    lang.getString("TT017"), true, frmMain, frmMain.suppBusObj);
            dlgSuppSearch.setVisible(true);
            dlgSuppSearch.txtSuppId.requestFocus(true);
            if (dlgSuppSearch.done) {
                String suppId = dlgSuppSearch.getData();
                frmMain.showSupplierModify(suppId);
                frmMain.pnlSupplierModify.txtSuppName.requestFocus(true);
                frmMain.showScreen(Constant.SCRS_SUPPLIER_MODIFY);
            }
            else{
              txtSuppName.requestFocus(true);
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

                  if (identifier.intValue() == KeyEvent.VK_F1) {
                    btnDone.doClick();
                  }
                  else if (identifier.intValue() == KeyEvent.VK_F2) {
                    if (flagSetHotKeyForAdd == true) {
                      btnAdd.doClick();
                    }
                  }
                  else if (identifier.intValue() == KeyEvent.VK_F3) {
                      btnSearch.doClick();
                  }
                  else if (identifier.intValue() == KeyEvent.VK_F4) {
                    if (flagSetHotKeyForAdd == false) {
                      btnModify.doClick();
                    }
                  }
                  else if (identifier.intValue() == KeyEvent.VK_F5) {

                  }
                  else if (identifier.intValue() == KeyEvent.VK_F7) {
                      btnClear.doClick();
                  }
                  else if (identifier.intValue() == KeyEvent.VK_F8) {
                    if (flagSetHotKeyForAdd == true) {
                      btnDelete.doClick();
                    }
                  }

                  else if (identifier.intValue() == KeyEvent.VK_F12 ||
                           identifier.intValue() == KeyEvent.VK_ESCAPE) {
                      btnCancel.doClick();
                  }

                }
        }

        void table_mousePressed(MouseEvent e) {
        }

        void table_mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2 ){
                 Vector vData = null;
                 this.vPosition = table.getSelectedRow();
                 vData = (Vector)vModify.elementAt(this.vPosition);
                 txtSuppID.setText(vData.elementAt(0).toString());
                 txtSuppName.setText(vData.elementAt(1).toString());
                 txtAbbreName.setText(vData.elementAt(2).toString());
                 txtContactName.setText(vData.elementAt(3).toString());
                 txtContactPhone.setText(vData.elementAt(4).toString());
                 txtContactFax.setText(vData.elementAt(5).toString());
                 cboCurrencyCode.setSelectedItem(vData.elementAt(6));
                 cboPayment.setSelectedData(vData.elementAt(8).toString());
                 txtVAT_No.setText(vData.elementAt(9).toString());
                 try{
                   stmt = DAL.getConnection().createStatement();
                      ResultSet rs = null;
                     String sql =
                             "select CNTRY_NAME from BTM_IM_COUNTRY_CDE where CNTRY_CDE='" +
                             vData.elementAt(7).toString() + "'";

                     rs = DAL.executeQueries(sql,stmt);
                     if(rs.next()){
                         cboCountryCode.setSelectedItem(rs.getString("cntry_name"));
                     }
                     rs.close();
                     stmt.close();
                 }catch(Exception ex){

                 }
                 setButton(false, true);
                 flagSetHotKeyForAdd = false;
//                 System.out.println("flarhotkey...." + flagSetHotKeyForAdd);
//                 btnSearch.setText("<html><center><b>Search</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
//                 btnClear.setText("<html><center><b>Clear</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
//                 btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</h" +
//                     "tml>");
                 txtSuppName.requestFocus(true);

               }

        }

        void btnModify_actionPerformed(ActionEvent e) {

            String iD = txtSuppID.getText().trim();
            String name = txtSuppName.getText().trim();
            String abbreName = txtAbbreName.getText().trim();
            String conName = txtContactName.getText().trim();
            String conPhone = txtContactPhone.getText().trim();
            String conFax = txtContactFax.getText().trim();
            String crrCde = cboCurrencyCode.getSelectedData().trim();
            String cntCde = cboCountryCode.getSelectedData().trim();
            String vat_No = txtVAT_No.getText().trim();
            String paymentCode = cboPayment.getSelectedData();

            //check data text field
            if (name.equals("")) {
                ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS301_SuppNameNotNull"));
                txtSuppName.requestFocus(true);
                return;
            }
            if (ut.checkPhoneNumber(conPhone) == false) {
                ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS800_WrongNum"));
                txtContactPhone.requestFocus(true);
                return;
            }
            if (ut.checkPhoneNumber(conFax) == false) {
                ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS800_WrongNum"));
                txtContactFax.requestFocus(true);
                return;
            }

            String contactPager = "null";
            String suppStatus = "null";
            String loadDate = ut.getSystemDate(1);
            String updDate = ut.getSystemDate(1);
            String currFlag = "1";
            String closeDate = "7/7/7777";
            String returnAllowInd = "null";
            String returnAuthReq = "null";
            double returnMinDolAmt = 0;
            String returnCourier = "null";
            String freightChargeInd = "null";
            int vatReason = 0;
            String invcPayLoc = "null";
            String invcReceiveLoc = "null";

            vSql.setElementAt(
                    "insert into BTM_IM_SUPPLIER(SUPP_ID, SUPP_NAME, CONTACT_NAME, CONTACT_PHONE," +
                    "CONTACT_FAX, CONTACT_PAGER, SUPP_STATUS, SUPP_CURR_CDE, RECD_LOAD_DATE," +
                    "RECD_LAST_UPD_DATE, RECD_CURR_FLAG, RECD_CLOSED_DATE, RETURN_ALLOW_IND," +
                    "RETURN_AUTH_REQ, RETURN_MIN_DOL_AMT, RETURN_COURIER, FREIGHT_CHARGE_IND," +
                    "VAT_REGION, INVC_PAY_LOC, INVC_RECEIVE_LOC, CNTRY_CDE, ABBREVIATION_NAME," +
                    "PAYMENT_METHOD_ID,VAT_NO)"
                    + "values('" + iD + "','" + name + "','" + conName + "','" + conPhone + "','" + conFax +
                    "'," + contactPager  + "," + suppStatus + ",'" + crrCde + "',to_date('" +
                    loadDate + "','DD-MM-YYYY'),to_date('" + updDate +  "','DD-MM-YYYY'),'" + currFlag +
                    "',to_date('" + closeDate + "','DD-MM-YYYY')," + returnAllowInd + "," +
                    returnAuthReq + "," + returnMinDolAmt + "," +  returnCourier + "," +
                    freightChargeInd + "," + vatReason + "," + invcPayLoc +  "," + invcReceiveLoc + ",'" +
                    cntCde + "','" + abbreName + "','" + paymentCode + "','" + vat_No + "')", this.vPosition);


            Vector vData = new Vector();
            vData.addElement(iD);
            vData.addElement(name);
            vData.addElement(abbreName);
            vData.addElement(conName);
            vData.addElement(conPhone);
            vData.addElement(conFax);
            vData.addElement(crrCde);
            vData.addElement(cntCde);
            vData.addElement(paymentCode);
            vData.addElement(vat_No);

            vModify.setElementAt(vData, this.vPosition);

            Object[] rowData = new Object[] {iD, name, conName, conPhone};

            table.setValueAt(iD,this.vPosition,0);
            table.setValueAt(name,this.vPosition,1);
            table.setValueAt(conName, this.vPosition, 2);
            table.setValueAt(conPhone, this.vPosition, 3);
            resetData();
            flagSetHotKeyForAdd = true;

        }

        void resetButtonLabel() {
//          btnSearch.setText("<html><center><b>Search</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//              "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
//          btnClear.setText("<html><center><b>Clear</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//              "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html>");
//          btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
//              "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F6</h" +
//              "tml>");
          flagSetHotKeyForAdd = true;
        }


  void txtAbbreName_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e, txtAbbreName, Supplier.LEN_SUP_ABBRE_NAME);
      navigationComp(e,txtContactName);
  }

  void cboCurrencyCode_keyTyped(KeyEvent e) {
    navigationComp(e,cboCountryCode);
  }

  void cboCountryCode_keyTyped(KeyEvent e) {
     if (e.getKeyChar() == KeyEvent.VK_ENTER ){
       btnAdd.doClick();
     }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      if (flagSetHotKeyForAdd == true) {
                    btnAdd.doClick();
                  }
                  else {
                    btnModify.doClick();

                  }

               }
  }

  void txtVAT_No_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtVAT_No,12);
      if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
          (e.getKeyChar() != e.VK_BACK_SPACE))
        e.consume();
  }

}

class PanelSupplierSetup_btnCancel_actionAdapter
          implements java.awt.event.ActionListener {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_btnCancel_actionAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
                adaptee.btnCancel_actionPerformed(e);
        }
}

class PanelSupplierSetup_btnAdd_actionAdapter
          implements java.awt.event.ActionListener {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_btnAdd_actionAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
                adaptee.btnAdd_actionPerformed(e);
        }
}

class PanelSupplierSetup_btnDone_actionAdapter
          implements java.awt.event.ActionListener {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_btnDone_actionAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
                adaptee.btnDone_actionPerformed(e);
        }
}

class PanelSupplierSetup_btnDelete_actionAdapter
          implements java.awt.event.ActionListener {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_btnDelete_actionAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
                adaptee.btnDelete_actionPerformed(e);
        }
}

class PanelSupplierSetup_btnSearch_actionAdapter
          implements java.awt.event.ActionListener {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_btnSearch_actionAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
                adaptee.btnSearch_actionPerformed(e);
        }
}

class PanelSupplierSetup_txtSuppName_keyAdapter
          extends java.awt.event.KeyAdapter {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_txtSuppName_keyAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void keyTyped(KeyEvent e) {
                adaptee.txtSuppName_keyTyped(e);
        }
}

class PanelSupplierSetup_txtContactName_keyAdapter
          extends java.awt.event.KeyAdapter {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_txtContactName_keyAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void keyTyped(KeyEvent e) {
                adaptee.txtContactName_keyTyped(e);
        }
}

class PanelSupplierSetup_txtContactPhone_keyAdapter
          extends java.awt.event.KeyAdapter {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_txtContactPhone_keyAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void keyTyped(KeyEvent e) {
                adaptee.txtContactPhone_keyTyped(e);
        }
}

class PanelSupplierSetup_txtContactFax_keyAdapter
          extends java.awt.event.KeyAdapter {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_txtContactFax_keyAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void keyTyped(KeyEvent e) {
                adaptee.txtContactFax_keyTyped(e);
        }
}


class PanelSupplierSetup_table_mouseAdapter
          extends java.awt.event.MouseAdapter {
        PanelSupplierSetup adaptee;

        PanelSupplierSetup_table_mouseAdapter(PanelSupplierSetup adaptee) {
                this.adaptee = adaptee;
        }

        public void mousePressed(MouseEvent e) {
                adaptee.table_mousePressed(e);
        }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelSupplierSetup_btnClear_actionAdapter implements java.awt.event.ActionListener {
    PanelSupplierSetup adaptee;

    PanelSupplierSetup_btnClear_actionAdapter(PanelSupplierSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnClear_actionPerformed(e);
    }
}

class PanelSupplierSetup_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelSupplierSetup adaptee;

  PanelSupplierSetup_btnModify_actionAdapter(PanelSupplierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelSupplierSetup_txtAbbreName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierSetup adaptee;

  PanelSupplierSetup_txtAbbreName_keyAdapter(PanelSupplierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtAbbreName_keyTyped(e);
  }
}

class PanelSupplierSetup_cboCurrencyCode_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierSetup adaptee;

  PanelSupplierSetup_cboCurrencyCode_keyAdapter(PanelSupplierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboCurrencyCode_keyTyped(e);
  }
}

class PanelSupplierSetup_cboCountryCode_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierSetup adaptee;

  PanelSupplierSetup_cboCountryCode_keyAdapter(PanelSupplierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboCountryCode_keyTyped(e);
  }
}

class PanelSupplierSetup_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierSetup adaptee;

  PanelSupplierSetup_table_keyAdapter(PanelSupplierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class PanelSupplierSetup_txtVAT_No_keyAdapter extends java.awt.event.KeyAdapter {
  PanelSupplierSetup adaptee;

  PanelSupplierSetup_txtVAT_No_keyAdapter(PanelSupplierSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtVAT_No_keyTyped(e);
  }
}
