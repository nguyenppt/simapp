package sim;

import javax.swing.*;
import java.awt.*;
import btm.swing.*;
import btm.attr.*;
import common.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.sql.*;
import java.awt.event.*;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: Main -> Merch Mgmt -> ThresHold Promo</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: BTM INTERSOFT VN</p>
 *
 * @author PHUONG NGUYEN
 * @version 1.0
 */
public class PanelPromoThresholdSetup extends JPanel{
  DataAccessLayer DAL;
  Statement stmt=null;
  Utils ut=new Utils();
  Vector vItemList = new Vector();
  btm.business.PromotionBusObj promoBusObj = new btm.business.PromotionBusObj();
  String itemEffect="";
  int seq_no = -1;

  public static final int THRESHOLD_TYPE_UNIT = 0;
  public static final int THRESHOLD_TYPE_AMOUNT = 1;
  public static final int THRESHOLD_TYPE_UNIT_AND_AMOUNT=2;
  public static final int THRESHOLD_TYPE_AMOUNT_LIST=3;

  public static final int DISCOUNT_PERCENTAGE_OFF = 0;
  public static final int DISCOUNT_FIXED_PRICE = 1;
  public static final int DISCOUNT_PRICE_OFF=2;

//identify the name of column table
  public static final int    COLUMN_NUMBER=3;
  public static final int COLUMN_ITEM_ID=0;
  public static final int COLUMN_UPC = 1;
  public static final int COLUMN_ITEM_NAME = 2;
//  public static final int COLUMN_QUANTITY = 3;

  int flag_threshold_type=-1;
  int flag_discount_type =-1;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelPromoThresholdSetup(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
    try {
        this.frmMain = frmMain;
        this.cardLayout = crdLayout;
        this.parent = parent;
        DAL = frmMain.getDAL();
        stmt = DAL.getConnection().createStatement();
        jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    registerKeyboardActions();
    border1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,
        new Color(148, 145, 140)), lang.getString("Benefit"));
    this.setPreferredSize(new Dimension(800, 600));
    this.setLayout(borderLayout1);
    pnlButton.setPreferredSize(new Dimension(800, 47));
    pnlButton.setBackground(new Color(121, 152, 198));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new
                              PanelPromoThresholdSetup_btnDone_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.PLAIN, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+"(F2)");
    btnAdd.setActionCommand("btnAdd");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelPromoThresholdSetup_btnAdd_actionAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.PLAIN, 12));
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search")+"(F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnSearch.addActionListener(new
        PanelPromoThresholdSetup_btnSearch_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.PLAIN, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnDelete.addActionListener(new
        PanelPromoThresholdSetup_btnDelete_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.PLAIN, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                   "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new
        PanelPromoThresholdSetup_btnCancel_actionAdapter(this));
    pnlMain.setPreferredSize(new Dimension(800, 553));
    pnlMain.setLayout(borderLayout2);
    pnlInfo.setLayout(null);
    pnlLeft.setBorder(border3);
    pnlLeft.setPreferredSize(new Dimension(400, 130));
    pnlLeft.setBounds(new Rectangle(4, 86, 400, 133));
    pnlLeft.setLayout(null);
    lblThresholdNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblThresholdNo.setPreferredSize(new Dimension(80, 25));
    lblThresholdNo.setText(lang.getString("ThresholdNo")+":");
    lblThresholdNo.setBounds(new Rectangle(408, 4, 112, 25));
    txtThresholdNo.setEnabled(false);
    txtThresholdNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtThresholdNo.setPreferredSize(new Dimension(59, 25));
    txtThresholdNo.setBounds(new Rectangle(534, 5, 234, 25));
    lblPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPromoName.setPreferredSize(new Dimension(80, 25));
    lblPromoName.setText(lang.getString("PromoName")+":");
    lblPromoName.setBounds(new Rectangle(11, 4, 111, 25));
    cboPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboPromoName.setPreferredSize(new Dimension(59, 25));
    cboPromoName.setBounds(new Rectangle(133, 5, 222, 25));
    btnSearchPromo.setBounds(new Rectangle(358, 4, 29, 25));
    btnSearchPromo.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnSearchPromo.setPreferredSize(new Dimension(25, 25));
    btnSearchPromo.setText("...");
    btnSearchPromo.addActionListener(new
        PanelPromoThresholdSetup_btnSearchPromo_actionAdapter(this));
    lblDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDescription.setPreferredSize(new Dimension(57, 25));
    lblDescription.setText(lang.getString("Description")+":");
    lblDescription.setBounds(new Rectangle(11, 37, 93, 25));
    pnlRight.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 11));
    pnlRight.setBorder(border1);
    pnlRight.setPreferredSize(new Dimension(400, 130));
    pnlRight.setBounds(new Rectangle(403, 86, 390, 133));
    pnlRight.setLayout(null);
    pnlInfo.setPreferredSize(new Dimension(800, 223));
    lblThresholdType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblThresholdType.setPreferredSize(new Dimension(90, 25));
    lblThresholdType.setText(lang.getString("ThresholdType")+":");
    lblThresholdType.setBounds(new Rectangle(12, 17, 110, 19));
    cboThresholdType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboThresholdType.setPreferredSize(new Dimension(29, 25));
    cboThresholdType.setBounds(new Rectangle(126, 17, 265, 25));
    cboThresholdType.addActionListener(new
        PanelPromoThresholdSetup_cboThresholdType_actionAdapter(this));
    lblDiscountType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDiscountType.setPreferredSize(new Dimension(43, 25));
    lblDiscountType.setText(lang.getString("DiscountType")+":");
    lblDiscountType.setBounds(new Rectangle(15, 17, 109, 25));
    cboDiscountType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboDiscountType.setPreferredSize(new Dimension(29, 25));
    cboDiscountType.setBounds(new Rectangle(138, 17, 234, 25));
    cboDiscountType.addActionListener(new
        PanelPromoThresholdSetup_cboDiscountType_actionAdapter(this));
    lblThresholdQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblThresholdQty.setPreferredSize(new Dimension(100, 25));
    lblThresholdQty.setText(lang.getString("ThresholdQuantity")+":");
    lblThresholdQty.setBounds(new Rectangle(12, 49, 111, 25));
    txtThresholdQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtThresholdQty.setPreferredSize(new Dimension(59, 25));
    txtThresholdQty.setText("");
    txtThresholdQty.setBounds(new Rectangle(126, 49, 40, 25));
    txtThresholdQty.addKeyListener(new
        PanelPromoThresholdSetup_txtThresholdQty_keyAdapter(this));
    lblBuyItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblBuyItem.setPreferredSize(new Dimension(27, 25));
    lblBuyItem.setText(lang.getString("BuyItem")+":");
    lblBuyItem.setBounds(new Rectangle(166, 49, 61, 25));
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtUPC.setPreferredSize(new Dimension(59, 25));
    txtUPC.setText("");
    txtUPC.setBounds(new Rectangle(227, 49, 130, 25));
    txtUPC.addKeyListener(new PanelPromoThresholdSetup_txtUPC_keyAdapter(this));
    lblThresholdAmt.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblThresholdAmt.setPreferredSize(new Dimension(100, 25));
    lblThresholdAmt.setText(lang.getString("ThresholdAmount")+":");
    lblThresholdAmt.setBounds(new Rectangle(12, 84, 111, 25));
    txtThresholdAmt.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtThresholdAmt.setPreferredSize(new Dimension(59, 25));
    txtThresholdAmt.setBounds(new Rectangle(127, 84, 264, 25));
    txtThresholdAmt.addKeyListener(new
                               PanelPromoThresholdSetup_jTextField1_keyAdapter(this));
    btnSearchBuyItem.setBounds(new Rectangle(363, 49, 28, 25));
    btnSearchBuyItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnSearchBuyItem.setPreferredSize(new Dimension(25, 25));
    btnSearchBuyItem.setText("...");
    btnSearchBuyItem.addActionListener(new
        PanelPromoThresholdSetup_btnSearchItem_actionAdapter(this));
    lblDiscountAmt.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDiscountAmt.setPreferredSize(new Dimension(100, 25));
    lblDiscountAmt.setText(lang.getString("DiscountAmount")+":");
    lblDiscountAmt.setBounds(new Rectangle(15, 49, 109, 25));
    txtDiscountAmt.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDiscountAmt.setBounds(new Rectangle(138, 49, 234, 25));
    txtDiscountAmt.addKeyListener(new
                               PanelPromoThresholdSetup_jTextField2_keyAdapter(this));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnClear.addActionListener(new
                               PanelPromoThresholdSetup_btnClear_actionAdapter(this));
    pnlTop.setPreferredSize(new Dimension(800, 93));
    pnlTop.setBounds(new Rectangle(0, 6, 800, 70));
    pnlTop.setLayout(null);
    lblGetItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblGetItem.setText(lang.getString("EffectedItem")+":");
    lblGetItem.setBounds(new Rectangle(15, 84, 121, 25));
    txtEffectedItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtEffectedItem.setBounds(new Rectangle(138, 84, 206, 25));
    txtEffectedItem.addKeyListener(new
        PanelPromoThresholdSetup_txtEffectedItem_keyAdapter(this));
    btnSearchEffectedItem.setBounds(new Rectangle(347, 84, 25, 25));
    btnSearchEffectedItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnSearchEffectedItem.setText("jButton1");
    btnSearchEffectedItem.addActionListener(new
        PanelPromoThresholdSetup_btnSearchEffectedItem_actionAdapter(this));
    txtDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDescription.setBounds(new Rectangle(134, 37, 634, 25));
    txtDescription.addKeyListener(new PanelPromoThresholdSetup_txtDescription_keyAdapter(this));
    lblWarning.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.ITALIC, 11));
    lblWarning.setForeground(Color.red);
    lblWarning.setText(lang.getString("EffectedItemNotBlankOtherwiseDiscountWouldEffectedToTotalAmt"));
    lblWarning.setBounds(new Rectangle(15, 113, 368, 14));
    pnlButton.add(btnDone);
    pnlButton.add(btnAdd);
    pnlButton.add(btnSearch);
    pnlButton.add(btnDelete);
    pnlButton.add(btnClear);
    pnlButton.add(btnCancel);
    this.add(pnlMain, java.awt.BorderLayout.CENTER);
    pnlRight.add(lblGetItem);
    pnlRight.add(lblDiscountType);
    pnlRight.add(cboDiscountType);
    pnlRight.add(txtDiscountAmt);
    pnlRight.add(lblDiscountAmt);
    pnlRight.add(txtEffectedItem);
    pnlRight.add(btnSearchEffectedItem);
    pnlRight.add(lblWarning);
    pnlInfo.add(pnlTop);
    pnlInfo.add(pnlLeft);
    pnlLeft.add(lblThresholdType);
    pnlLeft.add(cboThresholdType);
    pnlLeft.add(txtThresholdQty);
    pnlLeft.add(lblBuyItem);
    pnlLeft.add(txtUPC);
    pnlLeft.add(btnSearchBuyItem);
    pnlLeft.add(txtThresholdAmt);
    pnlLeft.add(lblThresholdAmt);
    pnlLeft.add(lblThresholdQty);
    pnlInfo.add(pnlRight);
    pnlTop.add(cboPromoName);
    pnlTop.add(lblPromoName);
    pnlTop.add(lblThresholdNo);
    pnlTop.add(btnSearchPromo);
    pnlTop.add(lblDescription);
    pnlTop.add(txtThresholdNo);
    pnlTop.add(txtDescription);
    this.add(pnlButton, java.awt.BorderLayout.NORTH);
    pnlMain.add(pnlInfo, java.awt.BorderLayout.NORTH);
    pnlMain.add(jScrollPane1, java.awt.BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tblItemDtl);
    String[] columnNames = new String[]{lang.getString("ItemID"),lang.getString("UPC"),lang.getString("ItemName")};
    dm.setDataVector(new Object[][]{},columnNames);
    dm.resetIndexes();
    tblItemDtl.setAutoResizeMode(tblItemDtl.AUTO_RESIZE_ALL_COLUMNS);
    tblItemDtl.getTableHeader().setReorderingAllowed(false);
    initScreen();
  }

  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  JPanel pnlButton = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  BJButton btnDone = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnCancel = new BJButton();
  JPanel pnlMain = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlInfo = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel pnlLeft = new JPanel();
  JLabel lblThresholdNo = new JLabel();
  JTextField txtThresholdNo = new JTextField();
  JLabel lblPromoName = new JLabel();
  BJComboBox cboPromoName = new BJComboBox();
  JButton btnSearchPromo = new JButton();
  JLabel lblDescription = new JLabel();
  JPanel pnlRight = new JPanel();
  JLabel lblThresholdType = new JLabel();
  JComboBox cboThresholdType = new JComboBox();
  JLabel lblDiscountType = new JLabel();
  JComboBox cboDiscountType = new JComboBox();
  JLabel lblThresholdQty = new JLabel();
  JTextField txtThresholdQty = new JTextField();
  JLabel lblBuyItem = new JLabel();
  JTextField txtUPC = new JTextField();
  JLabel lblThresholdAmt = new JLabel();
  JTextField txtThresholdAmt = new JTextField();
  JButton btnSearchBuyItem = new JButton();
  JLabel lblDiscountAmt = new JLabel();
  JTextField txtDiscountAmt = new JTextField();
  Border border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
      Color.white, Color.white, new Color(124, 124, 124),
      new Color(178, 178, 178));
  BJButton btnClear = new BJButton();
  JPanel pnlTop = new JPanel();
  JLabel lblGetItem = new JLabel();
  JTextField txtEffectedItem = new JTextField();
  Border border2 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border3 = new TitledBorder(border2, lang.getString("Criterial"));
  JButton btnSearchEffectedItem = new JButton();
  SortableTableModel dm = new SortableTableModel();
  BJTable tblItemDtl = new BJTable(dm,true){
    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };
  JTextField txtDescription = new JTextField();
  JLabel lblWarning = new JLabel();
  public void btnCancel_actionPerformed(ActionEvent e) {
    initScreen();

    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0108"));
    frmMain.pnlMainSim.showPromotionButton();
  }
  public void applyPermission() {
     EmpRight er = new EmpRight();
     er.initData(DAL, DAL.getEmpID());
     er.setData(DAL.getEmpID(), Constant.SCRS_PROMOTION_THRES_HOLD);
     btnAdd.setEnabled(er.getAdd());
     btnSearch.setEnabled(er.getAdd());
     btnDelete.setEnabled(er.getDelete());

     //  btnAdminRoleReport.setEnabled(er.getReport());
     if(!er.getAdd()){
       ut.showMessage(frmMain, lang.getString("TT002"),
                      lang.getString("MS1085_CanNotUseScreen"));

     }

   }

  public void initScreen() {
    txtDescription.setText("");
    showPromotionName();
    txtThresholdNo.setText(ut.getDateTimeID());
    showThresholdType();
    txtThresholdQty.setText("");
    txtUPC.setText("");
    txtThresholdAmt.setText("");
    showDiscountType();
    txtDiscountAmt.setText("");
    txtEffectedItem.setText("");
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
    vItemList.clear();
    itemEffect="";
  }
//show data on combobox Promotion Name
  public void showPromotionName(){
    cboPromoName.setData1(promoBusObj.getPromoName(DAL));
  }
//show types of Threshold in the combobox Threshold Type
  public void showThresholdType(){
    cboThresholdType.removeAllItems();
    cboThresholdType.addItem(Constant.THRESHOLD_TYPE_UNIT);
    cboThresholdType.addItem(Constant.THRESHOLD_TYPE_AMOUNT);
    cboThresholdType.addItem(Constant.THRESHOLD_TYPE_BOTH);
    cboThresholdType.addItem(Constant.THRESHOLD_TYPE_AMOUNT_LIST);
    cboThresholdType.setSelectedIndex(0);
    flag_threshold_type=THRESHOLD_TYPE_UNIT;
    showThresholdTextfield(flag_threshold_type);
    cboThresholdType.setEnabled(true);
  }
//show textfield appropriate to threshold type
  public void showThresholdTextfield(int THRESHOLD_TYPE){
//Threshold type is UNIT -> allow to input data into qty and buy item only
    if(THRESHOLD_TYPE==THRESHOLD_TYPE_UNIT){
      txtThresholdQty.setEditable(true);
      txtUPC.setEditable(true);
      btnSearchBuyItem.setEnabled(true);
      txtThresholdAmt.setEditable(false);
      txtThresholdAmt.setText("");
    }
//Threshold type is AMOUNT -> allow to input data into Threshold Amount only
    else if (THRESHOLD_TYPE==THRESHOLD_TYPE_AMOUNT){
      if(dm.getRowCount()>0){
        int i=ut.showMessage(frmMain,lang.getString("TT021"),lang.getString("MS1157_ListItemsWillLostIfWantChangeThisThresholdType"),DialogMessage.QUESTION_MESSAGE,DialogMessage.YES_NO_OPTION);
        if(i==DialogMessage.YES_VALUE){
          while(dm.getRowCount()>0){
            dm.removeRow(0);
          }
          vItemList.clear();
        }
        else if(i==DialogMessage.NO_VALUE) {
          cboThresholdType.setSelectedIndex(THRESHOLD_TYPE_UNIT_AND_AMOUNT);
          return;
        }
      }
      txtThresholdQty.setEditable(false);
      txtThresholdQty.setText("");
      txtUPC.setEditable(false);
      txtUPC.setText("");
      btnSearchBuyItem.setEnabled(false);
      txtThresholdAmt.setEditable(true);
    }
//Threshold type is UNIT_AND_AMOUNT -> allow to input both threshold Qty and threshold amt
    else if(THRESHOLD_TYPE==THRESHOLD_TYPE_UNIT_AND_AMOUNT){
      txtThresholdQty.setEditable(true);
      txtUPC.setEditable(true);
      btnSearchBuyItem.setEnabled(true);
      txtThresholdAmt.setEditable(true);
    }
//Threshold type is AMOUNT LIST -> allow to input threshold amt and list of item buy to get promo
    else if (THRESHOLD_TYPE==THRESHOLD_TYPE_AMOUNT_LIST){
      txtThresholdQty.setEditable(false);
      txtUPC.setEditable(true);
      btnSearchBuyItem.setEnabled(true);
      txtThresholdAmt.setEditable(true);
    }
  }
//show types of discount in the combobox Discount Type
  public void showDiscountType(){
  cboDiscountType.removeAllItems();
  cboDiscountType.addItem(Constant.GET_TYPE_PERCENT_OFF);
  cboDiscountType.addItem(Constant.GET_TYPE_FIXED_UNIT_PRICE);
  cboDiscountType.addItem(Constant.GET_TYPE_UNIT_PRICE_OFF);
//  cboDiscountType.addItem(Constant.GET_TYPE_TOTAL_PRICE_OFF);
//  cboDiscountType.addItem(Constant.GET_TYPE_FIXED_TOTAL_PRICE);
  cboDiscountType.setSelectedIndex(0);
}

  public void txtThresholdQty_keyTyped(KeyEvent e) {
    char key = e.getKeyChar();
    ut.checkNumberUpdate(e,txtThresholdQty.getText());
  }

  public void txtUPC_keyTyped(KeyEvent e) {
    char key = e.getKeyChar();
    ut.limitLenjTextField(e, txtUPC,ItemMaster.LEN_ITEM_ARTNO);
    ut.checkNumber(e);
//    ut.checkNumberUpdate(e,txtUPC.getText());
  }

  public void jTextField1_keyTyped(KeyEvent e) {
    char key=e.getKeyChar();
    ut.checkNumberUpdate(e,txtThresholdAmt.getText());
  }

  public void jTextField2_keyTyped(KeyEvent e) {
    char key=e.getKeyChar();
    ut.checkNumberUpdate(e,txtDiscountAmt.getText());
  }

  public void btnSearchItem_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObj = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0095"),true,frmMain, itemBusObj);
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.setVisible(true);
    if(dlgItemLookup.done){
      txtUPC.setText(dlgItemLookup.getUPC());
    }
  }

  public void btnSearchEffectedItem_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObj = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0095"),true,frmMain,itemBusObj);
    dlgItemLookup.cboStatus.setSelectedItem("Approve");
    dlgItemLookup.setVisible(true);
    if(dlgItemLookup.done){
      txtEffectedItem.setText(dlgItemLookup.getUPC());
    }
  }

  public void btnSearchPromo_actionPerformed(ActionEvent e) {
    btm.business.PromotionBusObj promoBusObj = new btm.business.PromotionBusObj();
    DialogPromoSearch dlgPromoSearch = new DialogPromoSearch(frmMain,lang.getString("TT0112"),true,frmMain,promoBusObj);
    dlgPromoSearch.FLAG_SCR=1;
    dlgPromoSearch.setVisible(true);
    if(dlgPromoSearch.bDone){
      cboPromoName.setSelectedData(dlgPromoSearch.PromotionID);
    }
  }

  public void cboThresholdType_actionPerformed(ActionEvent e) {
    if(cboThresholdType.getSelectedIndex()==THRESHOLD_TYPE_UNIT){
      btnAdd.setEnabled(true);
      flag_threshold_type=THRESHOLD_TYPE_UNIT;
    }
    else if (cboThresholdType.getSelectedIndex()==THRESHOLD_TYPE_AMOUNT){
      btnAdd.setEnabled(false);
      flag_threshold_type=THRESHOLD_TYPE_AMOUNT;
    }
    else if (cboThresholdType.getSelectedIndex()==THRESHOLD_TYPE_UNIT_AND_AMOUNT){
      btnAdd.setEnabled(true);
      flag_threshold_type=THRESHOLD_TYPE_UNIT_AND_AMOUNT;
    }
    else if (cboThresholdType.getSelectedIndex()==THRESHOLD_TYPE_AMOUNT_LIST){
      btnAdd.setEnabled(true);
      flag_threshold_type=THRESHOLD_TYPE_AMOUNT_LIST;
    }
    showThresholdTextfield(flag_threshold_type);
  }

  public void btnClear_actionPerformed(ActionEvent e) {
    initScreen();
  }

  public void txtEffectedItem_keyTyped(KeyEvent e) {
    char key = e.getKeyChar();
    ut.limitLenjTextField(e, txtEffectedItem,ItemMaster.LEN_ITEM_ARTNO);
    ut.checkNumberUpdate(e,txtEffectedItem.getText());
  }

  public void btnAdd_actionPerformed(ActionEvent e) {
    Object obj[] = new Object[COLUMN_NUMBER];
    String itemInfo[] = new String[3];
        itemInfo=getItemInfo(txtUPC.getText(),DAL);
        if(itemInfo[0]!=null){
          if (!checkItemInTbl(itemInfo[0], COLUMN_ITEM_ID)) {
            obj[COLUMN_ITEM_ID] = itemInfo[0];
            obj[COLUMN_UPC] = itemInfo[1];
            obj[COLUMN_ITEM_NAME] = itemInfo[2];
//          obj[COLUMN_QUANTITY] = Double.valueOf(txtThresholdQty.getText());
            dm.addRow(obj);
            vItemList.addElement(obj[COLUMN_ITEM_ID]);
            txtUPC.setText("");
          }
          else{
            txtUPC.setText("");
            txtUPC.requestFocus(true);
          }
        }
        else {
          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1184_InvalidItemInputValuebleUPC_EffectedItem"));
          txtUPC.setText("");
          return;
        }
  }
  public boolean checkItemInTbl(String itemId, int iCol){
    for(int i=0;i<tblItemDtl.getRowCount();i++){
      if(tblItemDtl.getValueAt(i,0).equals(itemId)){
        return true;
      }
    }
    return false;
  }
  public String getItemId(String UPC){
    String SQL = "select item_id from BTM_IM_ITEM_MASTER where art_no = '"+UPC+"'";
    String itemID="";
    try{
      ResultSet rs = null;
      rs = DAL.executeQueries(SQL);
      if(rs.next()){
        itemID = rs.getString("item_id");
      }
      rs.getStatement().close();
    }
    catch(Exception ex){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }
    return itemID;
  }
//disable textfields content main information
  public void disableMainInfo(){
    cboThresholdType.setEnabled(false);
    txtThresholdAmt.setEnabled(false);
    txtUPC.setText("");
  }

  public boolean checkInputData(){
   if(cboPromoName.getSelectedIndex()==0){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1193_PickUpCertainPromotionThreshold"));
    return false;
   }
   if (flag_threshold_type == THRESHOLD_TYPE_UNIT) {
     if (txtThresholdQty.getText().equalsIgnoreCase("") ||
         txtUPC.getText().equalsIgnoreCase("")) {
       if(dm.getRowCount()<=0){
         ut.showMessage(frmMain, lang.getString("TT001"),
                        lang.getString("MS1191_InputEnoughCriterialTypeUNIT_Threshold"));
         return false;
       }
     }
   }
   if (flag_threshold_type == THRESHOLD_TYPE_AMOUNT) {
     if (txtThresholdAmt.getText().equalsIgnoreCase("")) {
       ut.showMessage(frmMain, lang.getString("TT001"),
                      lang.getString("MS1192_InputEnoughCriterialtypeAMOUNT_Threshold"));
       txtThresholdAmt.requestFocus(true);
       return false;
     }
   }
   if (flag_threshold_type == THRESHOLD_TYPE_UNIT_AND_AMOUNT) {
       if (txtThresholdQty.getText().equalsIgnoreCase("") ||
           txtThresholdAmt.getText().equalsIgnoreCase("")){
         ut.showMessage(frmMain, lang.getString("TT001"),
                          lang.getString("MS1190_InputEnoughCriterialTypeUNIT_AND_AMOUNT_Threshold"));
         txtThresholdAmt.requestFocus(true);
         return false;
       }
       if (txtUPC.getText().equalsIgnoreCase("")){
         if (dm.getRowCount() <= 0) {
           ut.showMessage(frmMain, lang.getString("TT001"),
                          lang.getString("MS1189_BuyingUPCNotNullInputItem"));
           txtUPC.requestFocus(true);
           return false;
         }
       }
   }
   if(flag_threshold_type==THRESHOLD_TYPE_AMOUNT_LIST){
     if(txtUPC.getText().equalsIgnoreCase("")&&vItemList==null){
       ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1187_NotEnoughCriterialPromotionListItemsBought"));
       txtUPC.requestFocus(true);
       return false;
     }
   }
   if (txtDiscountAmt.getText().equalsIgnoreCase("") ||
       Double.parseDouble(txtDiscountAmt.getText()) < 0) {
     ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1185_InvalidDiscount"));
     txtDiscountAmt.setText("");
     txtDiscountAmt.requestFocus(true);
     return false;
   }
   if (cboDiscountType.getSelectedItem().equals(Constant.GET_TYPE_PERCENT_OFF)) {
     if (Double.parseDouble(txtDiscountAmt.getText()) > 100) {
       ut.showMessage(frmMain, lang.getString("TT001"),
                      lang.getString("MS1186_DiscountTypePercentageOffDiscountValueOver100"));
       txtDiscountAmt.setText("");
       txtDiscountAmt.requestFocus(true);
       return false;
     }
   }
   if(!txtUPC.getText().equalsIgnoreCase("")){
     if (getItemId(txtUPC.getText()).equalsIgnoreCase("")) {
       ut.showMessage(frmMain, lang.getString("TT001"),
                      lang.getString("MS1184_InvalidItemInputValuebleUPC_EffectedItem"));
       return false;
     }
   }
   if(!txtEffectedItem.getText().equalsIgnoreCase("")){
     itemEffect = getItemId(txtEffectedItem.getText());
     if(itemEffect.equalsIgnoreCase("")){
       ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1184_InvalidItemInputValuebleUPC_EffectedItem"));
       txtEffectedItem.setText("");
       return false;
     }
   }
   if(flag_discount_type!=DISCOUNT_PERCENTAGE_OFF){
     if(txtEffectedItem.getText().equalsIgnoreCase("")){
       ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1183_InputUPC_EffectedItem"));
       txtEffectedItem.requestFocus(true);
       return false;
     }
   }
   if(flag_discount_type==DISCOUNT_PERCENTAGE_OFF){
     if(txtEffectedItem.getText().equalsIgnoreCase("")){
       int i = ut.showMessage(frmMain, lang.getString("TT024"), lang.getString("MS1188_EffectedItemIsNullWantApplyDiscountValueTotalAmountBill"),
                              DialogMessage.WARNING_MESSAGE,
                              DialogMessage.YES_NO_OPTION);
       if (i == DialogMessage.NO_VALUE) {
         return false;
       }
     }
   }
   return true;
  }

//get ITEM_ID, ITEM_NAME with assigned UPC
  public String[] getItemInfo(String UPC,DataAccessLayer DAL){
    String[] itemInfo = new String[3];
    String SQL="";
    SQL = " SELECT ITEM_ID,ART_NO,ITEM_NAME FROM BTM_IM_ITEM_MASTER"
           + " WHERE ART_NO='"+ txtUPC.getText() +"' and STATUS = 'A'";
       try {
         ResultSet rs = null;
         stmt=DAL.getConnection().createStatement();
         rs = DAL.executeQueries(SQL,stmt);
         if(rs.next()){
           itemInfo[0] = rs.getString("ITEM_ID"); //ItemID
           itemInfo[1] = rs.getString("ART_NO"); //UPC
           itemInfo[2] = rs.getString("ITEM_NAME"); //Item Name
         }
         stmt.close();
       }
       catch (Exception ex) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(ex);
       }
       return itemInfo;
  }

  public void btnDone_actionPerformed(ActionEvent e) {
    if(checkInputData()){
      if(txtDescription.getText().equalsIgnoreCase("")){
        int i= ut.showMessage(frmMain,lang.getString("TT024"),lang.getString("MS1182_WantSaveThresholdWithoutDescription"),DialogMessage.WARNING_MESSAGE,DialogMessage.YES_NO_OPTION);
        if(i==DialogMessage.NO_VALUE){
          return;
        }
      }
        saveThreshold(stmt,DAL);
        initScreen();
    }
  }
//  execute saving data into tables BTM_IM_PROMO_THRESHOLD_HEAD,
//                                  BTM_IM_PROMO_THRESHOLD_DETAIL,
//                                  BTM_IM_PROMO_THRESHOLD_SKU
  public void saveThreshold(Statement stmt, DataAccessLayer DAL){
    String promoID = "";
    String thresholdNo = "";
    double thresholdAmt = 0;
    if(!txtThresholdAmt.getText().equalsIgnoreCase("")){
      thresholdAmt = Double.parseDouble(txtThresholdAmt.getText());
    }
    double discountAmt = 0;
    if(!txtDiscountAmt.getText().equalsIgnoreCase("")){
     discountAmt = Double.parseDouble(txtDiscountAmt.getText());
    }
    double thresholdQty = 0;
    if(!txtThresholdQty.getText().equalsIgnoreCase("")){
      thresholdQty = Double.parseDouble(txtThresholdQty.getText());
    }
    promoID = cboPromoName.getSelectedData();
    thresholdNo = txtThresholdNo.getText();

//information will be saved in table BTM_IM_PROMO_THRESHOLD_HEAD
// STATUS = 1: Active - STATUS = 0: Delete
    String SQL = " INSERT INTO BTM_IM_PROMO_THRESHOLD_HEAD ( "
        + "   PROMO_ID, THRESHOLD_NO, THRESHOLD_DESC,  "
        + "   APPLY_TO, THRESHOLD_TYPE, DISCOUNT_TYPE,  "
        + "   EXTRACT_DATE, STATUS)  "
        + "VALUES ('" + promoID
        + "','" + thresholdNo + "','" + txtDescription.getText()
        + "','C','" + cboThresholdType.getSelectedItem()
        + "','" + cboDiscountType.getSelectedItem() + "',"
        + "TO_DATE('7-7-7777','DD-MM-YYYY') , 1)";
//    System.out.println(SQL);
    Vector setofThresholdData = new Vector();
    setofThresholdData.addElement(SQL);
  //information will be saved in table BTM_IM_PROMO_THRESHOLD_DETAIL
    SQL = " INSERT INTO BTM_IM_PROMO_THRESHOLD_DETAIL ( "
        + "   PROMO_ID, THRESHOLD_NO, THRESHOLD_AMT,  "
        + "   DISCOUNT_AMT, THRESHOLD_QTY)  "
        + " VALUES ('" + promoID
        + "' ,'" + thresholdNo
        + "' ,'" + thresholdAmt
        + "' ,'" + discountAmt
        + "', '" + thresholdQty + "') ";
//    System.out.println(SQL);
    setofThresholdData.addElement(SQL);
  //information will be saved in table BTM_IM_PROMO_THRESHOLD_SKU
    if (vItemList != null) {
      for (int i = 0; i < vItemList.size(); i++) {
        seq_no = seq_no + 1;
        SQL = " INSERT INTO BTM_IM_PROMO_THRESHOLD_SKU ( "
            + "   PROMO_ID, THRESHOLD_NO, ITEM_ID,  "
            + "   SEQ_NO, ATTR_ID, QTY,  "
            + "   SELLING_UOM, BUY_GET_FLAG)  "
            + "VALUES ('" + promoID + "','" + thresholdNo + "','" +
            vItemList.elementAt(i) + "', "
            + " '"+seq_no+"','','', "
            + " '','0') ";
        setofThresholdData.addElement(SQL);
//        System.out.println(SQL);
      }
      vItemList.clear();
    }
    if (!getItemId(txtUPC.getText()).equalsIgnoreCase("")){
      seq_no = seq_no + 1;
      SQL = " INSERT INTO BTM_IM_PROMO_THRESHOLD_SKU ( "
          + "   PROMO_ID, THRESHOLD_NO, ITEM_ID,  "
          + "   SEQ_NO, ATTR_ID, QTY,  "
          + "   SELLING_UOM, BUY_GET_FLAG)  "
          + "VALUES ('" + promoID + "','" + thresholdNo + "','" +
          getItemId(txtUPC.getText()) + "', "
          + " '"+seq_no+"','','', "
          + " '','0') ";
      setofThresholdData.addElement(SQL);
//      System.out.println(SQL);
    }
  if(!itemEffect.equalsIgnoreCase("")){
    seq_no = seq_no + 1;
    SQL = " INSERT INTO BTM_IM_PROMO_THRESHOLD_SKU ( "
        + "   PROMO_ID, THRESHOLD_NO, ITEM_ID,  "
        + "   SEQ_NO, ATTR_ID, QTY,  "
        + "   SELLING_UOM, BUY_GET_FLAG)  "
        + "VALUES ('" + promoID + "','" + thresholdNo
        + "','" + itemEffect + "', "
        + " '"+seq_no+"','','1', "
        + " '','1') ";
    setofThresholdData.addElement(SQL);
    itemEffect="";
//    System.out.println(SQL);
  }
  try{
    ResultSet rs = null;
    stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    DAL.setBeginTrans(DAL.getConnection());
    DAL.executeBatchQuery(setofThresholdData,stmt);
    DAL.setEndTrans(DAL.getConnection());
    seq_no=-1;
    stmt.close();
  }catch(Exception ex){
    ExceptionHandle eh=new ExceptionHandle();
    eh.ouputError(ex);
  }
  }

  public void btnDelete_actionPerformed(ActionEvent e) {
    if (tblItemDtl.getRowCount() == 0) {
      return;
    }
    if (tblItemDtl.getSelectedRow() == -1) {
      return;
    }
    vItemList.removeElementAt(tblItemDtl.getSelectedRow());
    dm.removeRow(tblItemDtl.getSelectedRow());
  }

  public void btnSearch_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_PROMOTION_CHILD_SEARCH);
    frmMain.pnlPromoChildSearch.SCREEN_FLAG = 2;
    frmMain.pnlPromoChildSearch.setLayout();
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
  // F7
  key = new Integer(KeyEvent.VK_F7);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), key);
  actionMap.put(key, new KeyAction(key));
  // F8
  key = new Integer(KeyEvent.VK_F8);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), key);
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

  public void cboDiscountType_actionPerformed(ActionEvent e) {
    if(cboDiscountType.getSelectedItem().equals(Constant.GET_TYPE_PERCENT_OFF)){
      flag_discount_type=DISCOUNT_PERCENTAGE_OFF;
      lblDiscountAmt.setText(lang.getString("DiscountPercent"));
    }
    if(cboDiscountType.getSelectedItem().equals(Constant.GET_TYPE_FIXED_UNIT_PRICE)){
      flag_discount_type=DISCOUNT_FIXED_PRICE;
      lblDiscountAmt.setText(lang.getString("DiscountPrice"));
    }
    if(cboDiscountType.getSelectedItem().equals(Constant.GET_TYPE_UNIT_PRICE_OFF)){
      flag_discount_type=DISCOUNT_PRICE_OFF;
      lblDiscountAmt.setText(lang.getString("PriceOff"));
    }
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
        btnAdd.doClick();
    }
    else if (identifier.intValue() == KeyEvent.VK_F3) {
      btnSearch.doClick();
    }
    else if (identifier.intValue() == KeyEvent.VK_F5) {

    }
    else if (identifier.intValue() == KeyEvent.VK_F7) {
      btnClear.doClick();
    }
    else if (identifier.intValue() == KeyEvent.VK_F8) {
        btnDelete.doClick();
    }
    else if (identifier.intValue() == KeyEvent.VK_F12 ||
             identifier.intValue() == KeyEvent.VK_ESCAPE) {
      btnCancel.doClick();
    }
  }
  }

  void txtDescription_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtDescription,120);
  }
}

class PanelPromoThresholdSetup_cboDiscountType_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_cboDiscountType_actionAdapter(
      PanelPromoThresholdSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cboDiscountType_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_btnSearch_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_btnSearch_actionAdapter(PanelPromoThresholdSetup
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_btnDelete_actionAdapter(PanelPromoThresholdSetup
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_btnDone_actionAdapter
    implements ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_btnDone_actionAdapter(PanelPromoThresholdSetup
                                                 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_btnAdd_actionAdapter
    implements ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_btnAdd_actionAdapter(PanelPromoThresholdSetup
                                                adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_cboThresholdType_actionAdapter
    implements ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_cboThresholdType_actionAdapter(
      PanelPromoThresholdSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cboThresholdType_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_btnClear_actionAdapter
    implements ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_btnClear_actionAdapter(PanelPromoThresholdSetup
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_txtEffectedItem_keyAdapter
    extends KeyAdapter {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_txtEffectedItem_keyAdapter(PanelPromoThresholdSetup
      adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtEffectedItem_keyTyped(e);
  }
}

class PanelPromoThresholdSetup_btnSearchPromo_actionAdapter
    implements ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_btnSearchPromo_actionAdapter(
      PanelPromoThresholdSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchPromo_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_btnSearchEffectedItem_actionAdapter
    implements ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_btnSearchEffectedItem_actionAdapter(
      PanelPromoThresholdSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchEffectedItem_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_btnSearchItem_actionAdapter
    implements ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_btnSearchItem_actionAdapter(PanelPromoThresholdSetup
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchItem_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_jTextField2_keyAdapter
    extends KeyAdapter {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_jTextField2_keyAdapter(PanelPromoThresholdSetup
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.jTextField2_keyTyped(e);
  }
}

class PanelPromoThresholdSetup_jTextField1_keyAdapter
    extends KeyAdapter {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_jTextField1_keyAdapter(PanelPromoThresholdSetup
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.jTextField1_keyTyped(e);
  }
}

class PanelPromoThresholdSetup_txtUPC_keyAdapter
    extends KeyAdapter {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_txtUPC_keyAdapter(PanelPromoThresholdSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }
}

class PanelPromoThresholdSetup_txtThresholdQty_keyAdapter
    extends KeyAdapter {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_txtThresholdQty_keyAdapter(PanelPromoThresholdSetup
      adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtThresholdQty_keyTyped(e);
  }
}

class PanelPromoThresholdSetup_btnCancel_actionAdapter
    implements ActionListener {
  private PanelPromoThresholdSetup adaptee;
  PanelPromoThresholdSetup_btnCancel_actionAdapter(PanelPromoThresholdSetup
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelPromoThresholdSetup_txtDescription_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPromoThresholdSetup adaptee;

  PanelPromoThresholdSetup_txtDescription_keyAdapter(PanelPromoThresholdSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtDescription_keyTyped(e);
  }
}
