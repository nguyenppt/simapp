package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import btm.swing.BJButton;
import btm.swing.BJTable;
import btm.swing.BJCheckBox;
import btm.swing.SortableTableModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import btm.business.*;
import java.sql.*;
import btm.swing.BJComboBox;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.util.*;
import common.DataAccessLayer;
import btm.attr.EmpRight;

/**
 * <p>Title: </p>
 *
 * <p>Description: Main -> Merch Mgmt -> Promotion -> Mixmatch Promo</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author Vo Ha Thanh Huy
 * @version 1.0
 */

public class PanelPromotionMixMatch
    extends JPanel {

  //Constat
  public static int COLUMN_NUMBER = 6;
  public static int COLUMN_ITEM_ID = 0;
  public static int COLUMN_UPC = 1;
  public static int COLUMN_ITEM_NAME = 2;
  public static int COLUMN_BUY = 3;
  public static int COLUMN_GET = 4;
  public static int COLUMN_AMOUNT = 5;

  private boolean FLAG_SEARCH=false;
  private boolean FLAG_INSERT_CBO = false;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  //Variable
  MixMatchBusObj mm=new MixMatchBusObj();
  BJCheckBox check = new BJCheckBox();
  boolean flagSetHotKeyForAdd = true; // true if Add screen, false if modify screen
  JTextField txtTemp = new JTextField();

  Vector vBuyAmount = new Vector();
  Vector vGetAmount = new Vector();

  Constant cos = new Constant();
  PromotionBusObj prBusObj = new PromotionBusObj();
  Utils ut = new Utils();
  Statement stmt = null;

  BorderLayout borderLayout1 = new BorderLayout();
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  DataAccessLayer DAL = new DataAccessLayer();
  JPanel jPanel1 = new JPanel();
  BJButton btnDone = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnClear = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnCancel = new BJButton();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm, true) {
    public boolean isCellEditable(int row, int col) {
      return false;
    }
  };
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel4 = new JPanel();
  Border border1 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border2 = new TitledBorder(border1, "Mix Math Info");
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  BJComboBox cboPromotion = new BJComboBox();
  JButton btnSearchPromotion = new JButton();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField txtMixMathID = new JTextField();
  JComboBox cboBuyType = new JComboBox();
  JComboBox cboGetType = new JComboBox();
  Border border3 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border4 = new TitledBorder(border3, "Mix Math Info");
  Border border5 = BorderFactory.createCompoundBorder(border4, border1);
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JButton btnSearchBuyItems = new JButton();
  JComboBox cboBuyItems = new JComboBox();
  JComboBox cboGetItems = new JComboBox();
  JButton btnSearchGetItems = new JButton();
  JTextField txtBuyAmount = new JTextField();
  JTextField txtGetAmount = new JTextField();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextArea txtDescription = new JTextArea();
  public PanelPromotionMixMatch() {
    try {
      DAL = frmMain.getDAL();
//      stmt = DAL.getConnection().createStatement();
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public PanelPromotionMixMatch(FrameMainSim frmMain, CardLayout crdLayout,
                                JPanel parent) {
    try {
      this.frmMain = frmMain;
      this.cardLayout = crdLayout;
      this.parent = parent;
      DAL = frmMain.getDAL();
//      stmt = DAL.getConnection().createStatement();

      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    registerKeyboardActions();
    border5 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,
        new Color(148, 145, 140)), lang.getString("MixMatchInfo"));
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setMinimumSize(new Dimension(610, 51));
    jPanel1.setPreferredSize(new Dimension(750, 50));
    jPanel1.setBounds(new Rectangle( -9, 0, 816, 50));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelPromotionMixMatch_btnDone_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                   "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelPromotionMixMatch_btnAdd_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
                      ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnSearch.addActionListener(new
                                PanelPromotionMixMatch_btnSearch_actionAdapter(this));
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setBounds(new Rectangle(410, 5, 120, 37));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear")+" (F7)");
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
                     ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnClear.addActionListener(new
                               PanelPromotionMixMatch_btnClear_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnDelete.addActionListener(new
                                PanelPromotionMixMatch_btnDelete_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setBounds(new Rectangle(660, 5, 120, 37));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new
                                PanelPromotionMixMatch_btnCancel_actionAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(800, 240));
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    jPanel3.setLayout(gridLayout1);
    jPanel2.setLayout(null);
    jPanel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 11));
    jPanel4.setBorder(border5);
    jPanel4.setBounds(new Rectangle(9, 5, 778, 183));
    jPanel4.setLayout(null);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel1.setText(lang.getString("MixMatchID")+":");
    jLabel1.setBounds(new Rectangle(17, 27, 90, 14));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel2.setText(lang.getString("PromoName")+":");
    jLabel2.setBounds(new Rectangle(17, 57, 104, 14));
    cboPromotion.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboPromotion.setBounds(new Rectangle(112, 53, 169, 22));
    btnSearchPromotion.setBounds(new Rectangle(290, 53, 72, 23));
    btnSearchPromotion.setPreferredSize(new Dimension(25, 22));
    btnSearchPromotion.setText("...");
    btnSearchPromotion.addActionListener(new PanelPromotionMixMatch_btnSearchPromotion_actionAdapter(this));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel3.setText(lang.getString("BuyType")+":");
    jLabel3.setBounds(new Rectangle(421, 27, 65, 14));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel4.setText(lang.getString("GetType")+":");
    jLabel4.setBounds(new Rectangle(421, 57, 80, 14));
    txtMixMathID.setEnabled(false);
    txtMixMathID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtMixMathID.setBounds(new Rectangle(112, 24, 250, 20));
    cboBuyType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboBuyType.setBounds(new Rectangle(509, 23, 251, 22));
    cboBuyType.addItemListener(new PanelPromotionMixMatch_cboBuyType_itemAdapter(this));
    cboGetType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboGetType.setBounds(new Rectangle(509, 53, 251, 22));
    cboGetType.addItemListener(new PanelPromotionMixMatch_cboGetType_itemAdapter(this));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel5.setText(lang.getString("Description")+":");
    jLabel5.setBounds(new Rectangle(17, 80, 74, 14));
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel6.setText(lang.getString("BuyUPC")+":");
    jLabel6.setBounds(new Rectangle(25, 202, 63, 14));
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel8.setText(lang.getString("BuyAmount")+":");
    jLabel8.setBounds(new Rectangle(430, 202, 74, 14));
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel10.setText(lang.getString("GetAmount")+":");
    jLabel10.setBounds(new Rectangle(430, 235, 81, 14));
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel11.setText(lang.getString("GetUPC")+":");
    jLabel11.setBounds(new Rectangle(26, 235, 63, 14));
    btnSearchBuyItems.setBounds(new Rectangle(301, 198, 72, 23));
    btnSearchBuyItems.setPreferredSize(new Dimension(25, 22));
    btnSearchBuyItems.setText("...");
    btnSearchBuyItems.addActionListener(new PanelPromotionMixMatch_btnSearchBuyItems_actionAdapter(this));
    cboBuyItems.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboBuyItems.setBounds(new Rectangle(121, 198, 169, 22));
    cboBuyItems.addItemListener(new
                                PanelPromotionMixMatch_cboBuyItems_itemAdapter(this));
    cboBuyItems.addInputMethodListener(new
        PanelPromotionMixMatch_cboBuyItems_inputMethodAdapter(this));
    cboBuyItems.addActionListener(new
        PanelPromotionMixMatch_cboBuyItems_actionAdapter(this));
    cboBuyItems.addMouseListener(new
        PanelPromotionMixMatch_cboBuyItems_mouseAdapter(this));
    cboBuyItems.addKeyListener(new
                               PanelPromotionMixMatch_cboBuyItems_keyAdapter(this));
    cboGetItems.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboGetItems.setBounds(new Rectangle(121, 231, 169, 22));
    cboGetItems.addItemListener(new
                                PanelPromotionMixMatch_cboGetItems_itemAdapter(this));
    cboGetItems.addActionListener(new
        PanelPromotionMixMatch_cboGetItems_actionAdapter(this));
    cboGetItems.addKeyListener(new
                               PanelPromotionMixMatch_cboGetItems_keyAdapter(this));
    btnSearchGetItems.setBounds(new Rectangle(300, 231, 72, 23));
    btnSearchGetItems.setPreferredSize(new Dimension(25, 22));
    btnSearchGetItems.setText("...");
    btnSearchGetItems.addActionListener(new PanelPromotionMixMatch_btnSearchGetItems_actionAdapter(this));
    txtBuyAmount.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtBuyAmount.setBounds(new Rectangle(517, 199, 249, 20));
    txtBuyAmount.addKeyListener(new
                                PanelPromotionMixMatch_txtBuyAmount_keyAdapter(this));
    txtGetAmount.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtGetAmount.setBounds(new Rectangle(516, 232, 249, 20));
    txtGetAmount.addKeyListener(new
                                PanelPromotionMixMatch_txtGetAmount_keyAdapter(this));
    jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.
                                              HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.
                                            VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane2.setBounds(new Rectangle(112, 84, 645, 81));
    txtDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jPanel1.add(btnDone);
    jPanel1.add(btnAdd);
    jPanel1.add(btnSearch);
    jPanel1.add(btnDelete);
    jPanel1.add(btnClear);
    jPanel1.add(btnCancel);
    this.add(jPanel2, java.awt.BorderLayout.CENTER);
    jPanel2.add(jPanel4);
    jPanel4.add(jLabel1);
    jPanel4.add(cboGetType);
    jPanel4.add(cboBuyType);
    jPanel4.add(txtMixMathID);
    jPanel4.add(cboPromotion);
    jPanel4.add(btnSearchPromotion);
    jPanel4.add(jLabel3);
    jPanel4.add(jLabel4);
    jPanel4.add(jLabel5, null);
    jPanel4.add(jScrollPane2, null);
    jPanel4.add(jLabel2);
    jScrollPane2.getViewport().add(txtDescription);
    jPanel2.add(jLabel8);
    jPanel2.add(cboBuyItems);
    jPanel2.add(btnSearchBuyItems);
    jPanel2.add(btnSearchGetItems);
    jPanel2.add(cboGetItems);
    jPanel2.add(jLabel11);
    jPanel2.add(jLabel6);
    jPanel2.add(txtBuyAmount);
    jPanel2.add(txtGetAmount);
    jPanel2.add(jLabel10);
    this.add(jPanel3, java.awt.BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1);
    jScrollPane1.getViewport().add(table);
    this.add(jPanel1, java.awt.BorderLayout.NORTH); //Init Table
    String[] columnNames = new String[] {
        lang.getString("ItemID"), lang.getString("UPC"), lang.getString("ItemName"), lang.getString("Buy"), lang.getString("Get"), lang.getString("Amount")};
    dm.setDataVector(new Object[][] {}
                     , columnNames);
    dm.resetIndexes();
//    table.setData(rs,check,1);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn(lang.getString("ItemID")).setPreferredWidth(150);
    table.getColumn(lang.getString("UPC")).setPreferredWidth(150);
    table.getColumn(lang.getString("ItemName")).setPreferredWidth(350);
    table.getColumn(lang.getString("Buy")).setPreferredWidth(80);
    table.getColumn(lang.getString("Get")).setPreferredWidth(80);
    table.getColumn(lang.getString("Amount")).setPreferredWidth(200);

    //Init Screem
    initScreen();
    cboBuyItems.setEditable(true);
    cboGetItems.setEditable(true);
    txtDescription.addKeyListener(new
        PanelPromotionMixMatch_txtDescription_keyAdapter(this));

    if (cboBuyItems.getEditor() == null ||
        cboBuyItems.getEditor()instanceof UIResource) {
      cboBuyItems.setEditor(createEditor());
    }

    try {
      cboBuyItems.getEditor().getEditorComponent().addKeyListener(new
          KeyAdapter() {
        public void keyTyped(KeyEvent e) {
          txtTemp = (JTextField) cboBuyItems.getEditor().getEditorComponent();

          char key = e.getKeyChar();

          String strTemp = txtTemp.getText();
          if ( (ut.isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE ||
                key == KeyEvent.VK_DELETE) && strTemp.length() < 25) {
            return;
          }
          else if (key == KeyEvent.VK_ENTER) {
            if (!checkApprove(strTemp, DAL)) {
              ut.showMessage(frmMain, lang.getString("TT001"),
                  lang.getString("MS1201_ItemMustApprove"));
              txtTemp.setText("");
              txtTemp.requestFocus(true);
              return;
            }
            if (!checkUPC(strTemp, DAL)) {
              ut.showMessage(frmMain, lang.getString("TT001"),
                  lang.getString("MS349_UPCNotExist"));
              txtTemp.setText("");
              txtTemp.requestFocus(true);
              return;
            }
            else {
              if (!checkCombobox(cboBuyItems, txtTemp.getText())) {
                txtTemp.setText("");
                cboBuyItems.addItem(strTemp);
                cboBuyItems.setSelectedItem(strTemp);
                txtBuyAmount.setText("");
                txtBuyAmount.requestFocus();

                //add to vector get amount
                vBuyAmount.insertElementAt("1", cboBuyItems.getSelectedIndex());
              }
              else {
                cboBuyItems.setSelectedItem(strTemp);
              }
            }
            return;
          }
          else {
            e.consume();
          }
        }
      });
      cboGetItems.getEditor().getEditorComponent().addKeyListener(new
          KeyAdapter() {
        public void keyTyped(KeyEvent e) {
          JTextField txtTemp = (JTextField) cboGetItems.getEditor().
              getEditorComponent();
          char key = e.getKeyChar();

          String strTemp = txtTemp.getText();
          if ( (ut.isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE ||
                key == KeyEvent.VK_DELETE) && strTemp.length() < 25) {
            return;
          }
          else if (key == KeyEvent.VK_ENTER) {
            if (!checkApprove(strTemp, DAL)) {
              ut.showMessage(frmMain, lang.getString("TT001"),
                  lang.getString("MS1201_ItemMustApprove"));
              txtTemp.setText("");
              txtTemp.requestFocus(true);
              return;
            }

            if (!checkUPC(strTemp, DAL)) {
              ut.showMessage(frmMain, lang.getString("TT001"),
                  lang.getString("MS349_UPCNotExist"));
              txtTemp.setText("");
              txtTemp.requestFocus(true);
              return;
            }
            else {
              if (!checkCombobox(cboGetItems, txtTemp.getText())) {
                txtTemp.setText("");
                cboGetItems.addItem(strTemp);
                cboGetItems.setSelectedItem(strTemp);
                txtGetAmount.setText("");
                txtGetAmount.requestFocus();

                //add to vector get amount
                vGetAmount.insertElementAt("", cboGetItems.getSelectedIndex());
              }
              else {
                cboGetItems.setSelectedItem(strTemp);
              }
            }
            return;
          }
          else {
            e.consume();
          }
        }
      });
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  public void applyPermission() {
     EmpRight er = new EmpRight();
     er.initData(DAL, DAL.getEmpID());
     er.setData(DAL.getEmpID(), Constant.SCRS_PROMOTION_MIX_MATCH);
     btnAdd.setEnabled(er.getAdd());
     btnSearch.setEnabled(er.getAdd());
     btnDelete.setEnabled(er.getDelete());

     //  btnAdminRoleReport.setEnabled(er.getReport());
     if(!er.getAdd()){
       ut.showMessage(frmMain, lang.getString("TT002"),
                      lang.getString("MS1085_CanNotUseScreen"));

     }

   }

  protected ComboBoxEditor createEditor() {
    return new BasicComboBoxEditor.UIResource();
  }

  public void initScreen() {
    try {
//      stmt = DAL.getConnection().createStatement();
      //Enable screen
      cboPromotion.setEnabled(true);
      cboBuyType.setEnabled(true);
      cboGetType.setEnabled(true);
      txtDescription.setEnabled(true);
      txtDescription.setText("");
      cboGetItems.setEnabled(true);
      btnSearchGetItems.setEnabled(true);
      //Init combobox Buy Type
      cboBuyType.removeAllItems();
      cboBuyType.addItem(Constant.BUY_TYPE_ALL);
      cboBuyType.addItem(Constant.BUY_TYPE_ANY);
      cboBuyType.addItem(Constant.BUY_TYPE_LIST);

      //Init combobox Get Type
      cboGetType.removeAllItems();
      cboGetType.addItem(Constant.GET_TYPE_PERCENT_OFF);
      cboGetType.addItem(Constant.GET_TYPE_FIXED_UNIT_PRICE);
      cboGetType.addItem(Constant.GET_TYPE_FIXED_TOTAL_PRICE);
      cboGetType.addItem(Constant.GET_TYPE_UNIT_PRICE_OFF);
      cboGetType.addItem(Constant.GET_TYPE_TOTAL_PRICE_OFF);
      cboGetType.addItem(Constant.GET_TYPE_GET_FREE);

      //Init combobox Promotion
      cboPromotion.removeAllItems();
      getAllPromotion(DAL, stmt);

      //Init all sceen
      cboBuyItems.removeAllItems();
      cboGetItems.removeAllItems();
      txtBuyAmount.setText("");
      txtGetAmount.setText("");
      txtDescription.setText("");

      //Init MixMathID
      txtMixMathID.setText(ut.getDateTimeID());

      //Init Table
      while (dm.getRowCount() > 0) {
        dm.removeRow(0);
      }

      //Init Variable
      vBuyAmount = new Vector();
      vGetAmount = new Vector();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }


  void getAllBuyType(DataAccessLayer DAL, Statement stmt) {
    String sql =
        "select PROMO_ID,PROMO_NAME from BTM_IM_PROMO_HEAD order by upper(PROMO_NAME)";

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql, stmt);
      cboPromotion.setData(rs);
      rs.getStatement().close();

    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  void getAllPromotion(DataAccessLayer DAL, Statement stmt) {
    String sql =
        "select PROMO_ID,PROMO_NAME from BTM_IM_PROMO_HEAD where status<>'D' order by upper(PROMO_NAME)";

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql, stmt);
      cboPromotion.setData1(rs);
      cboPromotion.setSelectedData("None");
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

  }

  public boolean checkCombobox(JComboBox cboCheck, String strID) {
    boolean bFlag = false;
    int i;

    for (i = 0; i < cboCheck.getItemCount(); i++) {
      if (cboCheck.getItemAt(i).equals( (Object) strID)) {
        return true;
      }
    }

    return bFlag;
  }

  public void btnCancel_actionPerformed(ActionEvent e) {
    initScreen();

    cboPromotion.setEnabled(true);
    cboBuyType.setEnabled(true);
    cboGetType.setEnabled(true);
    txtDescription.setEditable(true);
    txtDescription.setText("");
    //Update
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0108"));
    frmMain.pnlMainSim.showPromotionButton();
  }

  public boolean checkVector(Vector vCheck) {
    for (int i = 0; i < vCheck.toArray().length; i++) {
      if (vCheck.elementAt(i).equals("")) {
        return false;
      }
    }
    return true;
  }

  public boolean checkTable(String strItemID, int iCol) {
    int i = 0;

    for (i = 0; i < table.getRowCount(); i++) {
      if (table.getValueAt(i, 0).toString().equals(strItemID)) {
        if (table.getValueAt(i, iCol).equals("1")) {
          return true;
        }
      }
    }
    return false;
  }
  public int checkPercentage(Vector vAmount){
    int bFlag = -1;

    for (int i=0;i<vAmount.toArray().length;i++){
      if (Double.parseDouble(vAmount.elementAt(i).toString())>100){
        return i;
      }
    }

    return bFlag;
  }
  public void btnAdd_actionPerformed(ActionEvent e) {
    //Check Information
    int i;
    if(cboPromotion.getSelectedItem().equals("None")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1202_PickUpCertainPromotionWhichMixMatch"));
      return;
    }
    if ( (cboBuyItems.getItemCount() == 0) && (cboGetItems.getItemCount() == 0)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1205_MustEnterBuyUpcOrGetUpc"));
      return;
    }

    if (!checkVector(vBuyAmount)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1203_BuyAmountEmptyInformation_InputAmountBuyUPC"));
      return;
    }

    if (!cboGetType.getSelectedItem().equals(Constant.GET_TYPE_LIST_PERCENTAGE)&&!checkVector(vGetAmount)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1204_GetAmountEmptyInformation_InputAmountGetUPC"));
      return;
    }

    if (!cboGetType.getSelectedItem().equals(Constant.GET_TYPE_LIST_PERCENTAGE)&&checkPercentage(vGetAmount)!=-1&&cboGetType.getSelectedItem().equals(Constant.GET_TYPE_PERCENT_OFF)) {
      ut.showMessage(frmMain, lang.getString("TT001"),
                     lang.getString("MS1206_GetTypePercentageOff"));
      vGetAmount.setElementAt("0",checkPercentage(vGetAmount));
      return;
    }
    if (cboGetType.getSelectedItem().equals(Constant.GET_TYPE_LIST_PERCENTAGE)){
      if (txtGetAmount.getText().equals("")){
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1207_GetAmountNotNull"));
        return;
      }
      cboGetItems.removeAllItems();
      vGetAmount.clear();
      Vector vTemp=new Vector();

      vTemp.addElement(txtGetAmount.getText());

      if (checkPercentage(vTemp)!=-1){
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1208_GetTypeListPercentage"));
        return;
      }
      FLAG_INSERT_CBO= true;
      for (i = 0; i < cboBuyItems.getItemCount(); i++) {
        vGetAmount.addElement(txtGetAmount.getText());
        cboGetItems.addItem(cboBuyItems.getItemAt(i));
      }
      FLAG_INSERT_CBO= false;
    }
    //Buy Type is List
    if (cboBuyType.getSelectedItem().equals(Constant.BUY_TYPE_LIST)) {
      if (cboBuyItems.getItemCount()>0){
        if (Integer.parseInt(vBuyAmount.elementAt(0).toString()) == 1) {
          if (txtGetAmount.getText().equals("")) {
            ut.showMessage(frmMain, lang.getString("TT001"),
                           lang.getString("MS1207_GetAmountNotNull"));
            return;
          }

          cboGetItems.removeAllItems();
          vGetAmount.clear();
          Vector vTemp = new Vector();

          vTemp.addElement(txtGetAmount.getText());
          FLAG_INSERT_CBO = true;

          for (i = 0; i < cboBuyItems.getItemCount(); i++) {
            vGetAmount.addElement(txtGetAmount.getText());
            cboGetItems.addItem(cboBuyItems.getItemAt(i));
          }

          FLAG_INSERT_CBO = false;
        }
      }
    }
    //Delete all item is not approve
    //Set enabled
    cboPromotion.setEnabled(false);
    cboBuyType.setEnabled(false);
    cboGetType.setEnabled(false);
    txtDescription.setEnabled(false);

    //Add Info to table
    String strTemp[] = new String[2];
    Object obj[] = new Object[COLUMN_NUMBER];
    for (i = 0; i < cboBuyItems.getItemCount(); i++) {
      strTemp = getItemInfo(cboBuyItems.getItemAt(i).toString(), DAL);
      if (! (checkTable(strTemp[0], COLUMN_BUY))) {
        obj[COLUMN_ITEM_ID] = strTemp[0];
        obj[COLUMN_UPC] = cboBuyItems.getItemAt(i);
        obj[COLUMN_ITEM_NAME] = strTemp[1];
        obj[COLUMN_BUY] = "1";
        obj[COLUMN_GET] = "0";
        obj[COLUMN_AMOUNT] = vBuyAmount.elementAt(i).toString();

        dm.addRow(obj);
      }
    }

    for (i = 0; i < cboGetItems.getItemCount(); i++) {
      strTemp = getItemInfo(cboGetItems.getItemAt(i).toString(), DAL);
      if (! (checkTable(strTemp[0], COLUMN_GET))) {
        obj[COLUMN_ITEM_ID] = strTemp[0];
        obj[COLUMN_UPC] = cboGetItems.getItemAt(i);
        obj[COLUMN_ITEM_NAME] = strTemp[1];
        obj[COLUMN_BUY] = "0";
        obj[COLUMN_GET] = "1";
        obj[COLUMN_AMOUNT] = vGetAmount.elementAt(i).toString();

        dm.addRow(obj);
      }
    }

    //Reset Screen
    cboBuyItems.removeAllItems();
    cboGetItems.removeAllItems();
    txtBuyAmount.setText("");
    txtGetAmount.setText("");
    //Clear Vector
    vBuyAmount = new Vector();
    vGetAmount = new Vector();
  }
  int checkTableBuyGet(){
    int bFlag=-1;
    int dem1,dem2;
    dem1=0;
    dem2=0;
    if (table.getRowCount()>0){
      if (table.getValueAt(0,COLUMN_BUY).equals("1")){
        dem1=1;
      }else{
        dem2=1;
      }
      for (int i = 1; i < table.getRowCount(); i++) {
        if (dem1==1){
          if (table.getValueAt(i,COLUMN_GET).equals("1")){
            return 1;
          }
        }
        if (dem2==1){
          if (table.getValueAt(i, COLUMN_BUY).equals("1")) {
            return 1;
          }
        }
      }
      if (dem1==1){
        return 2;
      }
      if (dem2 == 1) {
        return 3;
      }

    }

    return bFlag;
  }
  public void btnDone_actionPerformed(ActionEvent e) {
    String strDesc = txtDescription.getText();
    Vector vSQL = new Vector();

    if (table.getRowCount() == 0) {
      return;
    }

    if (checkTableBuyGet()==2){
      ut.showMessage(frmMain, lang.getString("TT001"),
                     "You must enter get item.");
      return;

    }

    if (checkTableBuyGet()==3){
      ut.showMessage(frmMain, lang.getString("TT001"),
                     "You must enter put item.");
      return;

    }

    if (strDesc == null) {
      strDesc = "null";
    }
    else {
      strDesc = "'" + txtDescription.getText() + "'";
    }

    //Insert data to BTM_IM_MIX_MATCH_HEAD
    vSQL.addElement("insert into BTM_IM_MIX_MATCH_HEAD values('" +
                    cboPromotion.getSelectedData() + "','" +
                    txtMixMathID.getText() + "'," + strDesc + ",'" +
                    cboBuyType.getSelectedItem() + "','" +
                    cboGetType.getSelectedItem() +
                    "',to_date('7/7/7777','dd/mm/yyyy'),'1')"
                    );
    //Insert data to BTM_IM_MIX_MATCH_BUY
    int i, iBuy, iGet;
    iBuy = 0;
    iGet = 0;
    for (i = 0; i < table.getRowCount(); i++) {
      if (table.getValueAt(i, COLUMN_BUY).equals("1")) {
        vSQL.addElement("insert into BTM_IM_MIX_MATCH_BUY values('" +
                        cboPromotion.getSelectedData() + "','" +
                        txtMixMathID.getText() + "','" +
                        table.getValueAt(i, COLUMN_ITEM_ID) + "'," + iBuy +
                        ",''," + table.getValueAt(i, COLUMN_AMOUNT) + ",'" +
                        getSellingUOM(table.getValueAt(i, COLUMN_UPC).toString(),
                                      DAL) + "')"
                        );
        iBuy++;
      }
      else {
        vSQL.addElement("insert into BTM_IM_MIX_MATCH_GET values('" +
                        cboPromotion.getSelectedData() + "','" +
                        txtMixMathID.getText() + "','" +
                        table.getValueAt(i, COLUMN_ITEM_ID) + "'," + iGet +
                        ",''," + table.getValueAt(i, COLUMN_AMOUNT) + ",'" +
                        getSellingUOM(table.getValueAt(i, COLUMN_UPC).toString(),
                                      DAL) + "')"
                        );
        iGet++;
      }
    }

    //Insert data to BTM_IM_MIX_MATCH_GET

    try {

      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      DAL.setBeginTrans(DAL.getConnection());
      DAL.executeBatchQuery(vSQL, stmt);
      DAL.setEndTrans(DAL.getConnection());
      stmt.close();
    }
    catch (Exception ex) {}
    initScreen();
    //Update
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0108"));
    frmMain.pnlMainSim.showPromotionButton();
  }

  public void btnSearch_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_PROMOTION_CHILD_SEARCH);
    frmMain.pnlPromoChildSearch.SCREEN_FLAG = 1;
    frmMain.pnlPromoChildSearch.mm = getInfo();
    frmMain.pnlPromoChildSearch.setLayout();
  }

  public void btnDelete_actionPerformed(ActionEvent e) {
    if (table.getRowCount() == 0) {
      return;
    }
    if (table.getSelectedRow() == -1) {
      return;
    }
    dm.removeRow(table.getSelectedRow());
  }

  public void btnClear_actionPerformed(ActionEvent e) {
    cboPromotion.setEnabled(true);
    cboBuyType.setEnabled(true);
    cboGetType.setEnabled(true);
    txtDescription.setEditable(true);

    initScreen();
  }

  public String getSellingUOM(String UPC, DataAccessLayer DAL) {
    String strTemp = "";
    String sql = "select STANDARD_UOM from BTM_IM_ITEM_MASTER where ART_NO = '" +
        UPC + "'";

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        strTemp = rs.getString("STANDARD_UOM");
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return strTemp;
  }

  public String getUPC(String ItemID, DataAccessLayer DAL) {
    String strTemp = "";
    String sql =
        "select ART_NO from BTM_IM_ITEM_MASTER where ITEM_ID = '" +
        ItemID + "'";

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        strTemp = rs.getString("ART_NO");
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return strTemp;
  }

  public String[] getItemInfo(String ItemID, DataAccessLayer DAL) {
    String strTemp[] = new String[2];
    String sql =
        "select Item_id,Item_Name from BTM_IM_ITEM_MASTER where ART_NO = '" +
        ItemID + "'";

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        strTemp[0] = rs.getString("Item_id");
        strTemp[1] = rs.getString("Item_Name");
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return strTemp;
  }
  public boolean checkApprove(String ItemID, DataAccessLayer DAL) {
     boolean bLag = false;
     String sql = "select STATUS from BTM_IM_ITEM_MASTER where ART_NO = '" +
         ItemID + "'";

     try {
       ResultSet rs = null;
       stmt = DAL.getConnection().createStatement();
       rs = DAL.executeQueries(sql, stmt);
       if (rs.next()) {
         if (rs.getString("STATUS").equals("A")) {
           bLag = true;
         }
       }
       stmt.close();
     }
     catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }

     return bLag;
   }

  public boolean checkUPC(String ItemID, DataAccessLayer DAL) {
    boolean bLag = false;
    String sql = "select ART_NO from BTM_IM_ITEM_MASTER where ART_NO = '" +
        ItemID + "'";

    try {
      ResultSet rs = null;
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        if (rs.getString("ART_NO").equals(ItemID)) {
          bLag = true;
        }
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return bLag;
  }

  public void cboBuyItems_keyTyped(KeyEvent e) {
  }

  public void txtBuyAmount_keyTyped(KeyEvent e) {
    char key = e.getKeyChar();
    if (!txtBuyAmount.getText().equals("")
        &&cboBuyType.getSelectedItem().equals(Constant.BUY_TYPE_LIST)
        &&cboBuyItems.getItemCount()>0
        &&Integer.parseInt(txtBuyAmount.getText())>1){
      cboGetItems.setEnabled(true);
    }else if (!txtBuyAmount.getText().equals("")
        &&cboBuyType.getSelectedItem().equals(Constant.BUY_TYPE_LIST)
        &&cboBuyItems.getItemCount()>0
        &&Integer.parseInt(txtBuyAmount.getText())==1){
      cboGetItems.setEnabled(false);
    }
    if (key == KeyEvent.VK_ENTER) {
      if (cboBuyItems.getSelectedIndex() == -1) {
        return;
      }
      vBuyAmount.setElementAt(txtBuyAmount.getText(),
                              cboBuyItems.getSelectedIndex());

      if (cboBuyType.getSelectedItem().equals(Constant.BUY_TYPE_LIST)) {
        if (ut.isIntString(txtBuyAmount.getText())) {
          for (int i=0;i<vBuyAmount.toArray().length;i++){
            vBuyAmount.setElementAt(txtBuyAmount.getText(),i);
          }
        }
      }

      txtTemp = (JTextField) cboBuyItems.getEditor().getEditorComponent();
      txtTemp.setText("");
      txtTemp.requestFocus(true);
      txtBuyAmount.setText("");
    }
    ut.checkNumberUpdate(e, txtBuyAmount.getText());
  }

  public void txtGetAmount_keyTyped(KeyEvent e) {
    char key = e.getKeyChar();
    if (key == KeyEvent.VK_ENTER) {
      if (cboGetItems.getSelectedIndex() == -1) {
        return;
      }

      vGetAmount.setElementAt(txtGetAmount.getText(),
                              cboGetItems.getSelectedIndex());
      txtTemp = (JTextField) cboGetItems.getEditor().getEditorComponent();
      txtTemp.setText("");
      txtTemp.requestFocus(true);
      txtGetAmount.setText("");
    }
    ut.checkNumberUpdate(e, txtGetAmount.getText());
  }

  void cboBuyItems_keyReleased(KeyEvent e) {
  }

  void cboGetItems_keyTyped(KeyEvent e) {
  }

  void cboGetItems_keyReleased(KeyEvent e) {

  }

  void cboBuyItems_keyPressed(KeyEvent e) {
  }

  void cboBuyItems_mouseClicked(MouseEvent e) {
  }

  void txtDescription_keyTyped(KeyEvent e) {
    ut.checkCharUpdate(e);
    ut.limitLenjTextArea(e, txtDescription,120);
  }

  void cboBuyItems_actionPerformed(ActionEvent e) {
  }

  void cboGetItems_actionPerformed(ActionEvent e) {
  }

  void cboBuyItems_caretPositionChanged(InputMethodEvent e) {
  }

  MixMatchBusObj getInfo(){
    MixMatchBusObj mmTemp=new MixMatchBusObj();
    Vector vTemp = new Vector();
    //1
    mmTemp.setMixMatchID(txtMixMathID.getText());
    mmTemp.setPromotion(cboPromotion.getSelectedItem().toString());
    mmTemp.setBuyType(cboBuyType.getSelectedItem().toString());
    mmTemp.setGetType(cboGetType.getSelectedItem().toString());
    mmTemp.setDescription(txtDescription.getText());

    //2
    vTemp = new Vector();
    for (int i=0;i<cboBuyItems.getItemCount();i++){
      vTemp.addElement(cboBuyItems.getItemAt(i));
    }
    mmTemp.setBuyUPC(vTemp);

    vTemp = new Vector();
    for (int i = 0; i < cboGetItems.getItemCount(); i++) {
      vTemp.addElement(cboGetItems.getItemAt(i));
    }
    mmTemp.setGetUPC(vTemp);

    mmTemp.setBuyAmount(vBuyAmount);
    mmTemp.setGetAmount(vGetAmount);

    if (cboBuyItems.getSelectedIndex()!=-1){
      mmTemp.setBuyUPCSelect(cboBuyItems.getSelectedItem().toString());
    }else{
      mmTemp.setBuyUPCSelect("");
    }
    if (cboGetItems.getSelectedIndex()!=-1){
      mmTemp.setGetUPCSelect(cboGetItems.getSelectedItem().toString());
    }else{
      mmTemp.setGetUPCSelect("");
    }

    mmTemp.setBuyAmountText(txtBuyAmount.getText());
    mmTemp.setGetAmountText(txtGetAmount.getText());

    mmTemp.setEdit(cboBuyItems.isEnabled());
    //3
    vTemp = new Vector();
    int i,j;
    for (i=0;i<table.getRowCount();i++){
      String strTemp[]=new String[COLUMN_NUMBER];

      for(j=0;j<COLUMN_NUMBER;j++){
        strTemp[j]=table.getValueAt(i,j).toString();
      }
      vTemp.addElement(strTemp);
    }
    mmTemp.setTable(vTemp);

    return mmTemp;
  }
  public void viewInfo(MixMatchBusObj mm){
    //1
   txtMixMathID.setText(mm.getMixMatchID());
   cboPromotion.setSelectedItem(mm.getPromotion());
   cboBuyType.setSelectedItem(mm.getBuyType());
   cboGetType.setSelectedItem(mm.getGetType());
   txtDescription.setText(mm.getDescription());

   //2
   Vector vTemp = new Vector();
   vTemp=mm.getBuyUPC();
   for (int i = 0; i < vTemp.toArray().length; i++) {
     cboBuyItems.addItem(vTemp.elementAt(i).toString());
   }
   if (!mm.getBuyUPCSelect().equals("")){
     cboBuyItems.setSelectedItem(mm.getBuyUPCSelect());
   }else{
     txtTemp = (JTextField) cboBuyItems.getEditor().getEditorComponent();
     txtTemp.setText("");
   }

   vTemp = new Vector();
   vTemp = mm.getGetUPC();
   for (int i = 0; i < vTemp.toArray().length; i++) {
     cboGetItems.addItem(vTemp.elementAt(i).toString());
   }
   if (!mm.getGetUPCSelect().equals("")) {
     cboGetItems.setSelectedItem(mm.getBuyUPCSelect());
   }else{
     txtTemp = (JTextField) cboGetItems.getEditor().getEditorComponent();
     txtTemp.setText("");
   }

   vBuyAmount=mm.getBuyAmount();
   vGetAmount=mm.getGetAmount();
   txtBuyAmount.setText(mm.getBuyAmountText());
   txtGetAmount.setText(mm.getGetAmountText());

   cboPromotion.setEnabled(mm.getEdit());
   cboBuyType.setEnabled(mm.getEdit());
   cboGetType.setEnabled(mm.getEdit());
   txtDescription.setEditable(mm.getEdit());

   //3
   vTemp = new Vector();
   vTemp=mm.getTable();

   for (int i = 0; i < vTemp.toArray().length; i++) {
     String str[]=new String[COLUMN_NUMBER];
     str=(String [])vTemp.elementAt(i);

     dm.addRow(str);
   }
  }
  void cboBuyItems_itemStateChanged(ItemEvent e) {
    String strTemp = "";
    if (FLAG_SEARCH){
      return;
    }
    if (e.getStateChange() == ItemEvent.SELECTED) {
      if (cboBuyItems.getSelectedIndex() != -1) {
        strTemp = vBuyAmount.elementAt(cboBuyItems.getSelectedIndex()).
            toString();
        txtBuyAmount.setText(strTemp);

        if (cboBuyType.getSelectedItem().equals(Constant.BUY_TYPE_LIST)) {
          if (Integer.parseInt(txtBuyAmount.getText())==1){
            cboGetItems.setEnabled(false);
          }
        }
      }
    }
  }

  void cboGetItems_itemStateChanged(ItemEvent e) {
    String strTemp = "";
    if (FLAG_SEARCH) {
      return;
    }
    if (FLAG_INSERT_CBO){
      return;
    }

    if (e.getStateChange() == ItemEvent.SELECTED) {
      if (cboGetItems.getSelectedIndex() != -1) {
        strTemp = vGetAmount.elementAt(cboGetItems.getSelectedIndex()).
            toString();
        txtGetAmount.setText(strTemp);
      }
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
//             btnModify.doClick();
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

  void btnSearchBuyItems_actionPerformed(ActionEvent e) {
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0016"), true, frmMain,frmMain.itemBusObject);
    dlgItemLookup.FLAG_SCR=1;
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.setVisible(true);


    if (dlgItemLookup.FLAG_DONE){
      if (dlgItemLookup.vItemID.toArray().length>0){
        FLAG_SEARCH=true;
        for (int i=0;i<dlgItemLookup.vItemID.toArray().length;i++){
          if (!checkCombobox(cboBuyItems,getUPC(dlgItemLookup.vItemID.elementAt(i).
                                         toString(), DAL))){
            if (checkApprove(getUPC(dlgItemLookup.vItemID.elementAt(i).
                                    toString(), DAL), DAL)) {
              cboBuyItems.addItem(getUPC(dlgItemLookup.vItemID.elementAt(i).
                                         toString(), DAL));
              vBuyAmount.addElement("1");
            }
          }
        }
        txtTemp = (JTextField) cboBuyItems.getEditor().getEditorComponent();
        txtTemp.setText("");
        cboBuyItems.setSelectedIndex(-1);
        FLAG_SEARCH=false;
      }
      dlgItemLookup.FLAG_DONE=false;
    }

  }

  void btnSearchGetItems_actionPerformed(ActionEvent e) {
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0016"), true, frmMain, frmMain.itemBusObject);
    dlgItemLookup.FLAG_SCR = 1;
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.setVisible(true);


    if (dlgItemLookup.FLAG_DONE){
      if (dlgItemLookup.vItemID.toArray().length>0){
        FLAG_SEARCH=true;
        for (int i=0;i<dlgItemLookup.vItemID.toArray().length;i++){
          if (!checkCombobox(cboGetItems,getUPC(dlgItemLookup.vItemID.elementAt(i).
                               toString(), DAL))){

        if (checkApprove(getUPC(dlgItemLookup.vItemID.elementAt(i).
                                toString(), DAL), DAL)) {
          cboGetItems.addItem(getUPC(dlgItemLookup.vItemID.elementAt(i).
                                     toString(), DAL));
          vGetAmount.addElement("");
        }
      }
        }
        txtTemp = (JTextField) cboGetItems.getEditor().getEditorComponent();
        txtTemp.setText("");
        cboGetItems.setSelectedIndex(-1);
        FLAG_SEARCH=false;
      }
      dlgItemLookup.FLAG_DONE=false;
    }
  }

  void btnSearchPromotion_actionPerformed(ActionEvent e) {
    btm.business.PromotionBusObj promoBusObject = new btm.business.
        PromotionBusObj();
    DialogPromoSearch dlgPromoSearch = new DialogPromoSearch(frmMain,
        lang.getString("TT0112"), true, frmMain, promoBusObject);
    dlgPromoSearch.FLAG_SCR=1;
    dlgPromoSearch.setVisible(true);

    if(dlgPromoSearch.bDone){
      cboPromotion.setSelectedData(dlgPromoSearch.PromotionID);
    }

  }

  void cboGetType_itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      if (cboGetType.getSelectedItem().equals(Constant.GET_TYPE_LIST_PERCENTAGE)){
        cboGetItems.setEnabled(false);
        btnSearchGetItems.setEnabled(false);
      }
    }
  }

  void cboBuyType_itemStateChanged(ItemEvent e) {
    if (!cboBuyType.getSelectedItem().equals(Constant.BUY_TYPE_LIST)){
      cboGetItems.setEnabled(true);
    }else{
      if (cboBuyItems.getItemCount()>0){
        if (Integer.parseInt(""+vBuyAmount.elementAt(0))==1){
          cboGetItems.setEnabled(false);
        }
      }
    }
  }

}

class PanelPromotionMixMatch_txtGetAmount_keyAdapter
    extends KeyAdapter {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_txtGetAmount_keyAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtGetAmount_keyTyped(e);
  }
}

class PanelPromotionMixMatch_txtBuyAmount_keyAdapter
    extends KeyAdapter {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_txtBuyAmount_keyAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtBuyAmount_keyTyped(e);
  }
}

class PanelPromotionMixMatch_cboGetItems_keyAdapter
    extends KeyAdapter {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_cboGetItems_keyAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.cboGetItems_keyTyped(e);
  }

  public void keyReleased(KeyEvent e) {
    adaptee.cboGetItems_keyReleased(e);
  }
}

class PanelPromotionMixMatch_cboBuyItems_keyAdapter
    extends KeyAdapter {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_cboBuyItems_keyAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.cboBuyItems_keyTyped(e);
  }

  public void keyReleased(KeyEvent e) {
    adaptee.cboBuyItems_keyReleased(e);
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cboBuyItems_keyPressed(e);
  }
}

class PanelPromotionMixMatch_btnClear_actionAdapter
    implements ActionListener {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_btnClear_actionAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_btnDelete_actionAdapter
    implements ActionListener {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_btnDelete_actionAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_btnSearch_actionAdapter
    implements ActionListener {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_btnSearch_actionAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_btnDone_actionAdapter
    implements ActionListener {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_btnDone_actionAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_btnAdd_actionAdapter
    implements ActionListener {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_btnAdd_actionAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_btnCancel_actionAdapter
    implements ActionListener {
  private PanelPromotionMixMatch adaptee;
  PanelPromotionMixMatch_btnCancel_actionAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_cboBuyItems_mouseAdapter
    extends java.awt.event.MouseAdapter {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_cboBuyItems_mouseAdapter(PanelPromotionMixMatch
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.cboBuyItems_mouseClicked(e);
  }
}

class PanelPromotionMixMatch_cboBuyItems_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_cboBuyItems_actionAdapter(PanelPromotionMixMatch
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cboBuyItems_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_txtDescription_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_txtDescription_keyAdapter(PanelPromotionMixMatch
      adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtDescription_keyTyped(e);
  }
}

class PanelPromotionMixMatch_cboGetItems_actionAdapter
    implements java.awt.event.ActionListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_cboGetItems_actionAdapter(PanelPromotionMixMatch
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cboGetItems_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_cboBuyItems_inputMethodAdapter
    implements java.awt.event.InputMethodListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_cboBuyItems_inputMethodAdapter(PanelPromotionMixMatch
      adaptee) {
    this.adaptee = adaptee;
  }

  public void inputMethodTextChanged(InputMethodEvent e) {
  }

  public void caretPositionChanged(InputMethodEvent e) {
    adaptee.cboBuyItems_caretPositionChanged(e);
  }
}

class PanelPromotionMixMatch_cboBuyItems_itemAdapter
    implements java.awt.event.ItemListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_cboBuyItems_itemAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.cboBuyItems_itemStateChanged(e);
  }
}

class PanelPromotionMixMatch_cboGetItems_itemAdapter
    implements java.awt.event.ItemListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_cboGetItems_itemAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.cboGetItems_itemStateChanged(e);
  }
}

class PanelPromotionMixMatch_btnSearchBuyItems_actionAdapter implements java.awt.event.ActionListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_btnSearchBuyItems_actionAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchBuyItems_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_btnSearchGetItems_actionAdapter implements java.awt.event.ActionListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_btnSearchGetItems_actionAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchGetItems_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_btnSearchPromotion_actionAdapter implements java.awt.event.ActionListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_btnSearchPromotion_actionAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchPromotion_actionPerformed(e);
  }
}

class PanelPromotionMixMatch_cboGetType_itemAdapter implements java.awt.event.ItemListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_cboGetType_itemAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.cboGetType_itemStateChanged(e);
  }
}

class PanelPromotionMixMatch_cboBuyType_itemAdapter implements java.awt.event.ItemListener {
  PanelPromotionMixMatch adaptee;

  PanelPromotionMixMatch_cboBuyType_itemAdapter(PanelPromotionMixMatch adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.cboBuyType_itemStateChanged(e);
  }
}
