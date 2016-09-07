package sim;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import btm.swing.*;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.CardLayout;
import java.sql.Statement;
import common.Utils;
import common.DataAccessLayer;
import common.DialogMessage;
import common.Constant;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ResourceBundle;
/**
 * <p>Title: </p>
 *
 * <p>Description: Main -> Merch Mgmt -> ThresHold Promo -> Modify</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: BTM INTERSOFT VN</p>
 *
 * @author PHUONG NGUYEN
 * @version 1.0
 */
public class PanelPromoThresholdModify extends JPanel{
  DataAccessLayer DAL;
  Statement stmt=null;
  Utils ut=new Utils();
  btm.business.PromotionBusObj promoBusObj = new btm.business.PromotionBusObj();
  int flag_threshold_type=-1;

  public static final int THRESHOLD_TYPE_UNIT = 0;
  public static final int THRESHOLD_TYPE_AMOUNT = 1;
  public static final int THRESHOLD_TYPE_UNIT_AND_AMOUNT=2;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  //identify the name of column table
  public static final int    COLUMN_NUMBER=3;
  public static final int COLUMN_ITEM_ID=0;
  public static final int COLUMN_UPC = 1;
  public static final int COLUMN_ITEM_NAME = 2;

  public PanelPromoThresholdModify(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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
    border1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,
        new Color(148, 145, 140)),lang.getString("Benefit"));
    this.setLayout(borderLayout2);
    this.setPreferredSize(new Dimension(800, 600));
    pnlButton.setBackground(new Color(121, 152, 198));
    pnlButton.setPreferredSize(new Dimension(800, 47));
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnBack.setPreferredSize(new Dimension(120, 37));
    btnBack.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F9</html>");
    btnBack.addActionListener(new
                              PanelPromoThresholdModify_btnBack_actionAdapter(this));
    pnlMain.setPreferredSize(new Dimension(800, 553));
    pnlMain.setLayout(null);
    pnlInfo.setPreferredSize(new Dimension(800, 223));
    pnlInfo.setBounds(new Rectangle(10, 10, 808, 232));
    pnlInfo.setLayout(null);
    pnlTop.setPreferredSize(new Dimension(800, 93));
    pnlTop.setBounds(new Rectangle(0, 0, 800, 93));
    pnlTop.setLayout(null);
    pnlLeft.setBorder(border2);
    pnlLeft.setPreferredSize(new Dimension(400, 110));
    pnlLeft.setBounds(new Rectangle( -2, 92, 394, 137));
    pnlLeft.setLayout(null);
    pnlRight.setBorder(border1);
    pnlRight.setPreferredSize(new Dimension(400, 110));
    pnlRight.setToolTipText("");
    pnlRight.setBounds(new Rectangle(391, 92, 400, 137));
    pnlRight.setLayout(null);
    cboPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboPromoName.setPreferredSize(new Dimension(59, 25));
    cboPromoName.setBounds(new Rectangle(128, 17, 222, 25));
    txtThresholdNo.setEnabled(false);
    txtThresholdNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtThresholdNo.setPreferredSize(new Dimension(59, 25));
    txtThresholdNo.setBounds(new Rectangle(537, 17, 235, 25));
    lblDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDescription.setPreferredSize(new Dimension(57, 25));
    lblDescription.setText(lang.getString("Description")+":");
    lblDescription.setBounds(new Rectangle(8, 50, 110, 25));
    lblThresholdNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblThresholdNo.setPreferredSize(new Dimension(80, 25));
    lblThresholdNo.setText(lang.getString("ThresholdNo")+":");
    lblThresholdNo.setBounds(new Rectangle(426, 17, 105, 25));
    btnSearchPromo.setBounds(new Rectangle(358, 17, 25, 25));
    btnSearchPromo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearchPromo.setPreferredSize(new Dimension(25, 25));
    btnSearchPromo.setText("...");
    lblPromoName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPromoName.setPreferredSize(new Dimension(80, 25));
    lblPromoName.setText(lang.getString("PromoName")+":");
    lblPromoName.setBounds(new Rectangle(8, 17, 110, 25));
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.
                                              HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane1.setPreferredSize(new Dimension(704, 25));
    jScrollPane1.setBounds(new Rectangle(128, 50, 644, 26));
    txtDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDescription.setPreferredSize(new Dimension(704, 22));
    txtThresholdAmt.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtThresholdAmt.setPreferredSize(new Dimension(59, 25));
    txtThresholdAmt.setBounds(new Rectangle(129, 98, 254, 25));
    lblThresholdAmt.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblThresholdAmt.setPreferredSize(new Dimension(100, 25));
    lblThresholdAmt.setText(lang.getString("ThresholdAmount")+":");
    lblThresholdAmt.setBounds(new Rectangle(12, 98, 119, 25));
    cboThresholdType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboThresholdType.setPreferredSize(new Dimension(29, 25));
    cboThresholdType.setBounds(new Rectangle(130, 24, 253, 25));
    lblThresholdType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblThresholdType.setPreferredSize(new Dimension(90, 25));
    lblThresholdType.setText(lang.getString("ThresholdType")+":");
    lblThresholdType.setBounds(new Rectangle(11, 24, 107, 25));
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtUPC.setPreferredSize(new Dimension(59, 25));
    txtUPC.setBounds(new Rectangle(237, 64, 116, 25));
    lblBuyItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblBuyItem.setPreferredSize(new Dimension(27, 25));
    lblBuyItem.setText(lang.getString("BuyItem")+":");
    lblBuyItem.setBounds(new Rectangle(177, 64, 62, 25));
    txtThresholdQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtThresholdQty.setPreferredSize(new Dimension(59, 25));
    txtThresholdQty.setBounds(new Rectangle(130, 64, 46, 25));
    lblThresholdQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblThresholdQty.setPreferredSize(new Dimension(100, 25));
    lblThresholdQty.setText(lang.getString("ThresholdQuantity")+":");
    lblThresholdQty.setBounds(new Rectangle(12, 64, 118, 25));
    btnSearchItem.setBounds(new Rectangle(358, 64, 25, 25));
    btnSearchItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearchItem.setPreferredSize(new Dimension(25, 25));
    btnSearchItem.setText("...");
    cboDiscountType.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboDiscountType.setPreferredSize(new Dimension(29, 25));
    cboDiscountType.setBounds(new Rectangle(122, 25, 270, 25));
    lblEffectedItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblEffectedItem.setText(lang.getString("EffectedItem")+":");
    lblEffectedItem.setBounds(new Rectangle(9, 98, 111, 25));
    txtEffectedItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtEffectedItem.setBounds(new Rectangle(122, 98, 233, 25));
    btnSearchEffectItem.setBounds(new Rectangle(360, 99, 32, 25));
    btnSearchEffectItem.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnSearchEffectItem.setPreferredSize(new Dimension(25, 25));
    btnSearchEffectItem.setText("jButton1");
    lblDiscountAmt.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDiscountAmt.setPreferredSize(new Dimension(100, 25));
    lblDiscountAmt.setText(lang.getString("DiscountAmount")+":");
    lblDiscountAmt.setBounds(new Rectangle(10, 64, 111, 25));
    txtDiscountAmt.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtDiscountAmt.setBounds(new Rectangle(122, 62, 270, 25));
    lblDiscountType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDiscountType.setPreferredSize(new Dimension(43, 25));
    lblDiscountType.setText(lang.getString("DiscountType")+":");
    lblDiscountType.setBounds(new Rectangle(13, 25, 107, 25));
    jScrollPane2.setPreferredSize(new Dimension(700, 390));
    jScrollPane2.setToolTipText("");
    jScrollPane2.setBounds(new Rectangle(0, 243, 808, 245));
    pnlButton.add(btnBack);
    pnlTop.add(lblDescription);
    pnlTop.add(lblPromoName);
    pnlTop.add(cboPromoName);
    pnlTop.add(btnSearchPromo);
    pnlTop.add(lblThresholdNo);
    pnlTop.add(txtThresholdNo);
    pnlTop.add(jScrollPane1);
    jScrollPane1.getViewport().add(txtDescription);
    pnlInfo.add(pnlLeft, null);
    pnlLeft.add(cboThresholdType);
    pnlLeft.add(lblThresholdType);
    pnlLeft.add(lblThresholdQty);
    pnlLeft.add(lblThresholdAmt);
    pnlLeft.add(txtThresholdQty);
    pnlLeft.add(txtUPC);
    pnlLeft.add(btnSearchItem);
    pnlLeft.add(txtThresholdAmt);
    pnlLeft.add(lblBuyItem);
    pnlInfo.add(pnlRight);
    pnlRight.add(lblDiscountType);
    pnlRight.add(cboDiscountType);
    pnlRight.add(lblEffectedItem);
    pnlRight.add(txtEffectedItem);
    pnlRight.add(btnSearchEffectItem);
    pnlRight.add(lblDiscountAmt);
    pnlRight.add(txtDiscountAmt);
    pnlInfo.add(pnlTop, null);
    jScrollPane2.getViewport().add(tblDtl);
    String[] columnNames = new String[]{lang.getString("ItemID"),lang.getString("UPC"),lang.getString("ItemName")};
    dm.setDataVector(new Object[][] {}, columnNames);
    dm.resetIndexes();
    tblDtl.setAutoResizeMode(tblDtl.AUTO_RESIZE_ALL_COLUMNS);
    tblDtl.getTableHeader().setReorderingAllowed(false);
    this.add(pnlButton, java.awt.BorderLayout.NORTH);
    this.add(pnlMain, java.awt.BorderLayout.CENTER);
    pnlMain.add(jScrollPane2, null);
    pnlMain.add(pnlInfo, null);
    initScreen();
    disableScreen();
  }

  BJPanel pnlButton = new BJPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  BJButton btnBack = new BJButton();
  JPanel pnlMain = new JPanel();
  JPanel pnlInfo = new JPanel();
  JPanel pnlTop = new JPanel();
  JPanel pnlLeft = new JPanel();
  JPanel pnlRight = new JPanel();
  Border border1 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border2 = new TitledBorder(border1, lang.getString("Criterial"));
  BJComboBox cboPromoName = new BJComboBox();
  JTextField txtThresholdNo = new JTextField();
  JLabel lblDescription = new JLabel();
  JLabel lblThresholdNo = new JLabel();
  JButton btnSearchPromo = new JButton();
  JLabel lblPromoName = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea txtDescription = new JTextArea();
  JTextField txtThresholdAmt = new JTextField();
  JLabel lblThresholdAmt = new JLabel();
  JComboBox cboThresholdType = new JComboBox();
  JLabel lblThresholdType = new JLabel();
  JTextField txtUPC = new JTextField();
  JLabel lblBuyItem = new JLabel();
  JTextField txtThresholdQty = new JTextField();
  JLabel lblThresholdQty = new JLabel();
  JButton btnSearchItem = new JButton();
  JComboBox cboDiscountType = new JComboBox();
  JLabel lblEffectedItem = new JLabel();
  JTextField txtEffectedItem = new JTextField();
  JButton btnSearchEffectItem = new JButton();
  JLabel lblDiscountAmt = new JLabel();
  JTextField txtDiscountAmt = new JTextField();
  JLabel lblDiscountType = new JLabel();
  JScrollPane jScrollPane2 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable tblDtl = new BJTable(dm,true){
//    public
  };
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  public void initScreen(){
    showPromotionName();
    txtThresholdNo.setText(ut.getDateTimeID());
    txtDescription.setText("");
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
        btnSearchItem.setEnabled(true);
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
          }
          else if(i==DialogMessage.NO_VALUE) {
            return;
          }
        }
        txtThresholdQty.setEditable(false);
        txtThresholdQty.setText("");
        txtUPC.setEditable(false);
        txtUPC.setText("");
        btnSearchItem.setEnabled(false);
        txtThresholdAmt.setEditable(true);
      }
//Threshold type is UNIT_AND_AMOUNT -> allow to input both threshold Qty and threshold amt
      else if(THRESHOLD_TYPE==THRESHOLD_TYPE_UNIT_AND_AMOUNT){
        txtThresholdQty.setEditable(true);
        txtUPC.setEditable(true);
        btnSearchItem.setEnabled(true);
        txtThresholdAmt.setEditable(true);
      }
    }
//show types of discount in the combobox Discount Type
    public void showDiscountType(){
    cboDiscountType.removeAllItems();
    cboDiscountType.addItem(Constant.GET_TYPE_PERCENT_OFF);
    cboDiscountType.addItem(Constant.GET_TYPE_FIXED_UNIT_PRICE);
    cboDiscountType.addItem(Constant.GET_TYPE_UNIT_PRICE_OFF);
    cboDiscountType.addItem(Constant.GET_TYPE_TOTAL_PRICE_OFF);
    cboDiscountType.addItem(Constant.GET_TYPE_FIXED_TOTAL_PRICE);
    cboDiscountType.setSelectedIndex(0);
  }
//disable screen
  public void disableScreen(){
    cboPromoName.setEnabled(false);
    txtThresholdNo.setEnabled(false);
    txtDescription.setEnabled(false);
    cboThresholdType.setEnabled(false);
    cboThresholdType.setEditable(false);
    txtThresholdQty.setEnabled(false);
    txtUPC.setEnabled(false);
    txtThresholdAmt.setEnabled(false);
    cboDiscountType.setEnabled(false);
    txtDiscountAmt.setEnabled(false);
    txtEffectedItem.setEnabled(false);
  }
  public void showData(String ThresholdID) {
    String sql = "";
    ResultSet rs = null;
    JTextField txtTemp = new JTextField();
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      sql = " select thrhld.THRESHOLD_NO,thrhld.THRESHOLD_DESC,thrhld.THRESHOLD_TYPE,thrhld.DISCOUNT_TYPE,ph.PROMO_ID " +
          " from BTM_IM_PROMO_THRESHOLD_HEAD thrhld,BTM_IM_PROMO_HEAD ph " +
          " where thrhld.PROMO_ID = ph.PROMO_ID and " +
          " thrhld.THRESHOLD_NO = " + ThresholdID;

      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        cboPromoName.setSelectedData(rs.getString("PROMO_ID"));
        txtThresholdNo.setText(rs.getString("THRESHOLD_NO"));
        txtDescription.setText(rs.getString("THRESHOLD_DESC"));
        cboThresholdType.setSelectedItem(rs.getString("THRESHOLD_TYPE"));
        cboDiscountType.setSelectedItem(rs.getString("DISCOUNT_TYPE"));
      }

      sql = " select im.ITEM_ID,im.ART_NO,im.ITEM_NAME " +
          " from BTM_IM_PROMO_THRESHOLD_SKU sku, BTM_IM_ITEM_MASTER im " +
          " where sku.ITEM_ID = im.ITEM_ID and BUY_GET_FLAG=0 and sku.THRESHOLD_NO= " + ThresholdID;
      rs = DAL.executeQueries(sql, stmt);
      while (rs.next()) {
        Object obj[] = new Object[COLUMN_NUMBER];

        obj[0] = rs.getString("ITEM_ID");
        obj[1] = rs.getString("ART_NO");
        obj[2] = rs.getString("ITEM_NAME");
        dm.addRow(obj);
      }
      sql = " select im.ART_NO" +
          " from BTM_IM_PROMO_THRESHOLD_SKU sku, BTM_IM_ITEM_MASTER im " +
          " where sku.ITEM_ID = im.ITEM_ID and BUY_GET_FLAG=1 and sku.THRESHOLD_NO= " + ThresholdID;
      rs = DAL.executeQueries(sql, stmt);

      if (rs.next()) {
        txtEffectedItem.setText(rs.getString("ART_NO"));
      }
      sql = " select THRESHOLD_QTY, THRESHOLD_AMT, DISCOUNT_AMT" +
          " from BTM_IM_PROMO_THRESHOLD_DETAIL dtl " +
          " where THRESHOLD_NO= " + ThresholdID;
      rs = DAL.executeQueries(sql, stmt);

      if (rs.next()) {
        txtThresholdAmt.setText(rs.getString("THRESHOLD_AMT"));
        txtDiscountAmt.setText(rs.getString("DISCOUNT_AMT"));
        txtThresholdQty.setText(rs.getString("THRESHOLD_QTY"));
      }

      stmt.close();
    }
    catch (Exception ex) {}
    ;

  }

  public void btnBack_actionPerformed(ActionEvent e) {
    initScreen();
    frmMain.showScreen(Constant.SCRS_PROMOTION_CHILD_SEARCH);
  }
}

class PanelPromoThresholdModify_btnBack_actionAdapter
    implements ActionListener {
  private PanelPromoThresholdModify adaptee;
  PanelPromoThresholdModify_btnBack_actionAdapter(PanelPromoThresholdModify
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}
