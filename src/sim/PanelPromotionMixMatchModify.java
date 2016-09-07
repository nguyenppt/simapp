package sim;

import java.awt.*;
import javax.swing.*;
import common.*;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import btm.swing.BJButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import btm.swing.BJTable;
import btm.swing.SortableTableModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import btm.swing.BJComboBox;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 *
 * <p>Description: Main -> Merch Mgmt -> Promotion -> Mixmatch Promo -> Modify</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author Vo Ha Thanh Huy
 * @version 1.0
 */
public class PanelPromotionMixMatchModify
    extends JPanel {
  //Constat
  public static int COLUMN_NUMBER = 6;
  public static int COLUMN_ITEM_ID = 0;
  public static int COLUMN_UPC = 1;
  public static int COLUMN_ITEM_NAME = 2;
  public static int COLUMN_BUY = 3;
  public static int COLUMN_GET = 4;
  public static int COLUMN_AMOUNT = 5;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  BorderLayout borderLayout1 = new BorderLayout();
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  DataAccessLayer DAL = new DataAccessLayer();
  JPanel jPanel1 = new JPanel();
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
  Statement stmt = null;
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel4 = new JPanel();
  Border border1 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  Border border2 = new TitledBorder(border1, lang.getString("MixMatchInfo"));
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
  Border border4 = new TitledBorder(border3, lang.getString("MixMatchInfo"));
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

  public PanelPromotionMixMatchModify() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public PanelPromotionMixMatchModify(FrameMainSim frmMain,
                                      CardLayout crdLayout, JPanel parent) {
    try {
      this.frmMain = frmMain;
      this.cardLayout = crdLayout;
      this.parent = parent;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    border5 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,
        new Color(148, 145, 140)), lang.getString("MixMatchInfo"));
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setMinimumSize(new Dimension(610, 51));
    jPanel1.setPreferredSize(new Dimension(750, 50));
    jPanel1.setBounds(new Rectangle( -9, 0, 816, 50));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setBounds(new Rectangle(660, 5, 120, 37));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Back")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
                      ";&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new
        PanelPromotionMixMatchModify_btnCancel_actionAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(800, 240));
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    jPanel3.setLayout(gridLayout1);
    jPanel2.setLayout(null);
    jPanel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 11));
    jPanel4.setBorder(border5);
    jPanel4.setBounds(new Rectangle(9, 5, 778, 184));
    jPanel4.setLayout(null);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel1.setText(lang.getString("MixMatchID")+":");
    jLabel1.setBounds(new Rectangle(17, 27, 90, 14));
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel2.setText(lang.getString("PromoName")+":");
    jLabel2.setBounds(new Rectangle(17, 57, 93, 14));
    cboPromotion.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboPromotion.setBounds(new Rectangle(112, 53, 169, 22));
    btnSearchPromotion.setBounds(new Rectangle(290, 53, 72, 23));
    btnSearchPromotion.setPreferredSize(new Dimension(25, 22));
    btnSearchPromotion.setText("...");
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel3.setText(lang.getString("BuyType")+":");
    jLabel3.setBounds(new Rectangle(421, 27, 65, 14));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel4.setText(lang.getString("GetType")+":");
    jLabel4.setBounds(new Rectangle(421, 57, 86, 14));
    txtMixMathID.setEnabled(false);
    txtMixMathID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtMixMathID.setBounds(new Rectangle(112, 24, 250, 20));
    cboBuyType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboBuyType.setBounds(new Rectangle(509, 23, 251, 22));
    cboGetType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboGetType.setBounds(new Rectangle(509, 53, 251, 22));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel5.setText(lang.getString("Description")+":");
    jLabel5.setBounds(new Rectangle(17, 80, 74, 14));
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel6.setText(lang.getString("BuyItem")+":");
    jLabel6.setBounds(new Rectangle(19, 201, 63, 14));
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel8.setText(lang.getString("BuyAmount")+":");
    jLabel8.setBounds(new Rectangle(424, 201, 74, 14));
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel10.setText(lang.getString("GetAmount")+":");
    jLabel10.setBounds(new Rectangle(424, 234, 68, 14));
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jLabel11.setText(lang.getString("GetItem")+":");
    jLabel11.setBounds(new Rectangle(20, 234, 63, 14));
    btnSearchBuyItems.setBounds(new Rectangle(295, 197, 72, 23));
    btnSearchBuyItems.setPreferredSize(new Dimension(25, 22));
    btnSearchBuyItems.setText("...");
    cboBuyItems.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboBuyItems.setBounds(new Rectangle(115, 197, 169, 22));
    cboGetItems.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    cboGetItems.setBounds(new Rectangle(115, 230, 169, 22));
    btnSearchGetItems.setBounds(new Rectangle(294, 230, 72, 23));
    btnSearchGetItems.setPreferredSize(new Dimension(25, 22));
    btnSearchGetItems.setText("...");
    txtBuyAmount.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtBuyAmount.setBounds(new Rectangle(511, 198, 249, 20));
    txtGetAmount.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtGetAmount.setBounds(new Rectangle(510, 231, 249, 20));
    jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.
                                              HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.
                                            VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane2.setBounds(new Rectangle(112, 84, 645, 81));
    txtDescription.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    jPanel1.add(btnCancel);
    this.add(jPanel2, java.awt.BorderLayout.CENTER);
    jPanel2.add(jPanel4);
    jPanel4.add(jLabel1);
    jPanel4.add(jLabel2);
    jPanel4.add(cboGetType);
    jPanel4.add(cboBuyType);
    jPanel4.add(txtMixMathID);
    jPanel4.add(cboPromotion);
    jPanel4.add(btnSearchPromotion);
    jPanel4.add(jLabel3);
    jPanel4.add(jLabel4);
    jPanel4.add(jLabel5, null);
    jPanel4.add(jScrollPane2, null);
    jScrollPane2.getViewport().add(txtDescription);
    jPanel2.add(cboBuyItems);
    jPanel2.add(txtBuyAmount);
    jPanel2.add(txtGetAmount);
    jPanel2.add(jLabel10);
    jPanel2.add(jLabel8);
    jPanel2.add(btnSearchBuyItems);
    jPanel2.add(btnSearchGetItems);
    jPanel2.add(cboGetItems);
    jPanel2.add(jLabel6);
    jPanel2.add(jLabel11);
    this.add(jPanel3, java.awt.BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1);
    jScrollPane1.getViewport().add(table);
    this.add(jPanel1, java.awt.BorderLayout.NORTH); //Init Table
    String[] columnNames = new String[] {
        lang.getString("ItemID"), lang.getString("UPC"), lang.getString("ItemName"), lang.getString("Buy"), lang.getString("Get"), lang.getString("Amount")};
    dm.setDataVector(new Object[][] {}
                     , columnNames);
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getColumn(lang.getString("ItemID")).setPreferredWidth(150);
    table.getColumn(lang.getString("UPC")).setPreferredWidth(150);
    table.getColumn(lang.getString("ItemName")).setPreferredWidth(350);
    table.getColumn(lang.getString("Buy")).setPreferredWidth(80);
    table.getColumn(lang.getString("Get")).setPreferredWidth(80);
    table.getColumn(lang.getString("Amount")).setPreferredWidth(200);

    cboPromotion.setEnabled(false);
    cboBuyType.setEnabled(false);
    cboGetType.setEnabled(false);
    txtDescription.setEditable(false);
    cboBuyItems.setEnabled(false);
    cboGetItems.setEnabled(false);
    txtBuyAmount.setEnabled(false);
    txtGetAmount.setEnabled(false);

    //Init combobox Promotion
    cboPromotion.removeAllItems();
    getAllPromotion(DAL, stmt);

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
    cboGetType.addItem(Constant.GET_TYPE_LIST_PERCENTAGE);
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
      cboPromotion.setData(rs);
      stmt.close();

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
      stmt.close();

    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  public void initScreen() {
    //Init combobox Promotion
    cboPromotion.removeAllItems();
    getAllPromotion(DAL, stmt);

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
    cboGetType.addItem(Constant.GET_TYPE_LIST_PERCENTAGE);


    //Init Table
    while (dm.getRowCount() > 0) {
      dm.removeRow(0);
    }
  }

  public void showData(String MMID) {
    String sql = "";
    ResultSet rs = null;
    JTextField txtTemp = new JTextField();
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      sql = " select mm.MIX_MATCH_NO,mm.MIC_MATCH_DESC,mm.BUY_TYPE,mm.GET_TYPE, ph.PROMO_ID " +
          " from BTM_IM_MIX_MATCH_HEAD mm,BTM_IM_PROMO_HEAD ph " +
          " where mm.PROMO_ID = ph.PROMO_ID and " +
          " mm.MIX_MATCH_NO = " + MMID;
//      System.out.println("vit:" + sql);
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        txtMixMathID.setText(rs.getString("MIX_MATCH_NO"));
        cboBuyType.setSelectedItem(rs.getString("BUY_TYPE"));
        cboGetType.setSelectedItem(rs.getString("GET_TYPE"));
        cboPromotion.setSelectedData(rs.getString("PROMO_ID"));

        txtDescription.setText(rs.getString("MIC_MATCH_DESC"));
      }

      sql = " select im.ITEM_ID,im.ART_NO,im.ITEM_NAME, mb.BUY_AMT " +
          " from BTM_IM_MIX_MATCH_BUY mb, BTM_IM_ITEM_MASTER im " +
          " where mb.ITEM_ID = im.ITEM_ID and mb.MIX_MATCH_NO= " + MMID;
      rs = DAL.executeQueries(sql, stmt);

      while (rs.next()) {
        Object obj[] = new Object[COLUMN_NUMBER];

        obj[0] = rs.getString("ITEM_ID");
        obj[1] = rs.getString("ART_NO");
        obj[2] = rs.getString("ITEM_NAME");
        obj[3] = "1";
        obj[4] = "0";
        obj[5] = rs.getString("BUY_AMT");

        dm.addRow(obj);
      }
      sql = " select im.ITEM_ID,im.ART_NO,im.ITEM_NAME, mb.GET_AMT " +
          " from BTM_IM_MIX_MATCH_GET mb, BTM_IM_ITEM_MASTER im " +
          " where mb.ITEM_ID = im.ITEM_ID and mb.MIX_MATCH_NO= " + MMID;
      rs = DAL.executeQueries(sql, stmt);

      while (rs.next()) {
        Object obj[] = new Object[COLUMN_NUMBER];

        obj[0] = rs.getString("ITEM_ID");
        obj[1] = rs.getString("ART_NO");
        obj[2] = rs.getString("ITEM_NAME");
        obj[3] = "0";
        obj[4] = "1";
        obj[5] = rs.getString("GET_AMT");

        dm.addRow(obj);
      }

      stmt.close();
    }
    catch (Exception ex) {}
    ;

  }

  public void btnCancel_actionPerformed(ActionEvent e) {
    //Update
    initScreen();
    frmMain.showScreen(Constant.SCRS_PROMOTION_CHILD_SEARCH);
  }
}

class PanelPromotionMixMatchModify_btnCancel_actionAdapter
    implements ActionListener {
  private PanelPromotionMixMatchModify adaptee;
  PanelPromotionMixMatchModify_btnCancel_actionAdapter(
      PanelPromotionMixMatchModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}
