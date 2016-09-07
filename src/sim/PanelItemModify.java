package sim;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import java.sql.*;
import java.sql.Statement;
import common.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.ResourceBundle;
import javax.swing.event.*;
import btm.attr.*;
import javax.swing.border.*;
import btm.business.*;
import java.awt.Dimension;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Item Setup -> Item Modify</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelItemModify
    extends JPanel {
  //Declare variable for this screen
  DataAccessLayer DAL = new DataAccessLayer();
  FrameMainSim frmMain;
  Utils ut = new Utils();
  //store sub category of item
  String sID = "";
  ItemMasterBusObj bItemMaster ;
  //Declare  "vColor" to hold Color infomation
  Vector vColor;
  //Declare  "vSize" to hold Size infomation
  Vector vSize;
  int maxLevelProdHier=0;//max level of product hierachy
  //End Declare

  Statement stmt = null;
  String oldUPC="";
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  BJButton btnCancel = new BJButton();
  BJButton btnModify = new BJButton();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JLabel lblUnitPrice = new JLabel();
  JLabel lblUnitCost = new JLabel();
  JLabel lblSupplier = new JLabel();
  JLabel lblItemName = new JLabel();
  JLabel lblArtNo = new JLabel();
  JLabel lblItemID = new JLabel();
  JTextField txtUnitPrice = new JTextField();
  JTextField txtUnitCost = new JTextField();
  JTextField txtItemName = new JTextField();
  JTextField txtArtNo = new JTextField();
  JTextField txtItemID = new JTextField();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JLabel lblMaterial = new JLabel();
  JLabel lblSize = new JLabel();
  JLabel lblColor = new JLabel();
  JLabel lblItemLevel = new JLabel();
  JLabel lblTransLevel = new JLabel();
  JLabel lblUnit = new JLabel();
  JTextField txtMaterial = new JTextField();
  BJComboBox cboSize = new BJComboBox();
  BJComboBox cboColor = new BJComboBox();
  JComboBox cboItemLevel = new JComboBox();
  JComboBox cboTransLevel = new JComboBox();
//  JTextField txtUnit = new JTextField();
  BJComboBox cmbUnit = new BJComboBox();
  JPanel jPanel12 = new JPanel();
  JPanel jPanel13 = new JPanel();
  JPanel jPanel14 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea txtItemDesc = new JTextArea();
  JTextField txtCategory = new JTextField();
  JLabel lblSubCategory = new JLabel();
  JLabel lblPriceRange = new JLabel();
  JComboBox cboPriceRange = new JComboBox();
  String size = "";
  JLabel lblItemDesc1 = new JLabel();
  BNode rootTree;
  BTreeModel pTreeModel;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  //Keep value current
  String sArtNo="";
  String sSize="";
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  TitledBorder titledBorder1;
  Border border1;
  TitledBorder titledBorder2;
  Border border2;
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel3 = new JPanel();
  GridLayout gridLayout2 = new GridLayout();
  JScrollPane jScrollPane2 = new JScrollPane();
  BJTree treProdHier;
  JLabel lblVAT = new JLabel();
  JButton btnGenerateUPC = new JButton();
  BJComboBox cboSupplier = new BJComboBox();
  JButton btnSupplier = new JButton();
  JComboBox cboVAT = new JComboBox();
  JLabel jLabel1 = new JLabel();
  JTextField txtPackSize = new JTextField();
  String status = "";
  public PanelItemModify(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    bItemMaster = new ItemMasterBusObj();
    bItemMaster.setDataAccessLayer(frmMain.getDAL());
    rootTree = new BNode(new BNodeDetail("1", "1", "1", Constant.root, Constant.root));
    pTreeModel = new BTreeModel(rootTree);
    treProdHier = new BJTree(pTreeModel);
    titledBorder1 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(103, 101, 98),new Color(148, 145, 140)),lang.getString("ProductHierarchy"));
    border1 = BorderFactory.createEmptyBorder();
    titledBorder2 = new TitledBorder(border1,lang.getString("ItemDetail"));
    border2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),lang.getString("ItemDetail"));
    registerKeyboardActions();
    treProdHier.addTreeSelectionListener(new
        PanelItemModify_treProdHier_treeSelectionAdapter(this));
    treProdHier.addMouseListener(new PanelItemModify_treProdHier_mouseAdapter(this));
//    rootTree = new BNode(new BNodeDetail("1", "1", "1", Constant.root,
//                                         Constant.root));
//    pTreeModel = new BTreeModel(rootTree);

    this.setSize(new Dimension(878, 376));
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel2.setPreferredSize(new Dimension(800, 320));
    jPanel2.setLayout(borderLayout2);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F2)");
    btnCancel.setText(lang.getString("Cancel")+" (F2)");
    btnCancel.addActionListener(new PanelItemModify_btnCancel_actionAdapter(this));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setToolTipText(lang.getString("Modify")+" (F1)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                     "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1<" +
                     "/html>");
    btnModify.addActionListener(new PanelItemModify_btnModify_actionAdapter(this));
    jPanel4.setEnabled(true);
    jPanel4.setForeground(Color.black);
    jPanel4.setAlignmentY( (float) 0.5);
    jPanel4.setBorder(titledBorder1);
    jPanel4.setDebugGraphicsOptions(0);
    jPanel4.setMinimumSize(new Dimension(10, 10));
    jPanel4.setOpaque(true);
    jPanel4.setPreferredSize(new Dimension(200, 320));
    jPanel4.setVerifyInputWhenFocusTarget(true);
    jPanel4.setLayout(gridLayout1);
    jPanel5.setBorder(border2);
    jPanel5.setPreferredSize(new Dimension(600, 320));
    jPanel5.setLayout(null);
    jPanel7.setPreferredSize(new Dimension(90, 230));
    jPanel7.setBounds(new Rectangle(8, 24, 100, 213));
    jPanel9.setPreferredSize(new Dimension(285, 230));
    jPanel9.setBounds(new Rectangle(297, 23, 292, 184));
    jPanel9.setLayout(borderLayout4);
    lblUnitPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblUnitPrice.setPreferredSize(new Dimension(90, 20));
    lblUnitPrice.setText(lang.getString("UnitPrice")+":");
    lblUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblUnitCost.setPreferredSize(new Dimension(90, 20));
    lblUnitCost.setText(lang.getString("UnitCost")+":");
    lblSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSupplier.setPreferredSize(new Dimension(90, 20));
    lblSupplier.setText(lang.getString("SupplierName")+":");
    lblItemName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemName.setPreferredSize(new Dimension(90, 20));
    lblItemName.setText(lang.getString("ItemName")+":");
    lblArtNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblArtNo.setPreferredSize(new Dimension(90, 20));
    lblArtNo.setText(lang.getString("UPC")+":");
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setPreferredSize(new Dimension(90, 20));
    lblItemID.setText(lang.getString("ItemID")+":");
    txtUnitPrice.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUnitPrice.setPreferredSize(new Dimension(180, 20));
    txtUnitPrice.setEditable(false);
    txtUnitPrice.setText("");
    txtUnitPrice.addFocusListener(new PanelItemModify_txtUnitPrice_focusAdapter(this));
    txtUnitCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUnitCost.setPreferredSize(new Dimension(180, 20));
    txtUnitCost.setEditable(false);
    txtUnitCost.setText("");
    txtUnitCost.addFocusListener(new PanelItemModify_txtUnitCost_focusAdapter(this));
    txtItemName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemName.setNextFocusableComponent(cboPriceRange);
    txtItemName.setPreferredSize(new Dimension(180, 20));
    txtItemName.setText("");
    txtItemName.addKeyListener(new PanelItemModify_txtItemName_keyAdapter(this));
    txtArtNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtArtNo.setNextFocusableComponent(txtItemName);
    txtArtNo.setPreferredSize(new Dimension(105, 20));
    txtArtNo.setText("");
    txtArtNo.addKeyListener(new PanelItemModify_txtArtNo_keyAdapter(this));
    txtItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemID.setPreferredSize(new Dimension(180, 20));
    txtItemID.setEditable(false);
    txtItemID.setText("");
    jPanel11.setPreferredSize(new Dimension(200, 230));
    jPanel10.setPreferredSize(new Dimension(100, 230));
    lblMaterial.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblMaterial.setPreferredSize(new Dimension(90, 20));
    lblMaterial.setText(lang.getString("Material")+":");
    lblSize.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSize.setPreferredSize(new Dimension(90, 20));
    lblSize.setText(lang.getString("Size")+":");
    lblColor.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblColor.setPreferredSize(new Dimension(90, 20));
    lblColor.setText(lang.getString("Color")+":");
    lblItemLevel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemLevel.setPreferredSize(new Dimension(90, 20));
    lblItemLevel.setText(lang.getString("ItemLevel")+":");
    lblTransLevel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTransLevel.setPreferredSize(new Dimension(90, 20));
    lblTransLevel.setText(lang.getString("TransLevel")+":");
    lblUnit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblUnit.setPreferredSize(new Dimension(90, 20));
    lblUnit.setText(lang.getString("Unit")+":");
    txtMaterial.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtMaterial.setNextFocusableComponent(txtItemDesc);
    txtMaterial.setPreferredSize(new Dimension(180, 20));
    txtMaterial.setText("");
    txtMaterial.addKeyListener(new PanelItemModify_txtMaterial_keyAdapter(this));
//    txtUnit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    txtUnit.setPreferredSize(new Dimension(180, 20));
//    txtUnit.setEditable(false);
//    txtUnit.setText("");
    cmbUnit.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cmbUnit.setMinimumSize(new Dimension(180, 22));
    cmbUnit.setPreferredSize(new Dimension(180, 20));
    cmbUnit.addItemListener(new PanelItemModify_cmbUnit_itemAdapter(this));

    cboTransLevel.setEnabled(false);
    cboTransLevel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboTransLevel.setNextFocusableComponent(cboItemLevel);
    cboTransLevel.setPreferredSize(new Dimension(180, 20));
    cboTransLevel.setRequestFocusEnabled(true);
    cboTransLevel.setEditable(true);
    cboItemLevel.setEnabled(false);
    cboItemLevel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboItemLevel.setNextFocusableComponent(null);
    cboItemLevel.setPreferredSize(new Dimension(180, 20));
    cboItemLevel.setEditable(true);
    cboItemLevel.addActionListener(new
                                   PanelItemModify_cboItemLevel_actionAdapter(this));
    cboSize.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSize.setOpaque(true);
    cboSize.setPreferredSize(new Dimension(180, 20));
    cboSize.addKeyListener(new PanelItemModify_cboSize_keyAdapter(this));
    cboSize.addFocusListener(new PanelItemModify_cboSize_focusAdapter(this));
    cboColor.setEnabled(true);
    cboColor.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboColor.setPreferredSize(new Dimension(180, 20));
    cboColor.setEditable(false);
    cboColor.addKeyListener(new PanelItemModify_cboColor_keyAdapter(this));
    cboColor.addFocusListener(new PanelItemModify_cboColor_focusAdapter(this));
    jPanel8.setPreferredSize(new Dimension(190, 230));
    jPanel8.setBounds(new Rectangle(102, 26, 200, 217));
    jPanel12.setPreferredSize(new Dimension(600, 125));
    jPanel12.setRequestFocusEnabled(true);
    jPanel12.setBounds(new Rectangle(9, 240, 580, 153));
    jPanel12.setLayout(flowLayout1);
    jPanel13.setPreferredSize(new Dimension(550, 25));
    jPanel14.setPreferredSize(new Dimension(550, 70));
    jScrollPane1.setPreferredSize(new Dimension(550, 100));
    txtItemDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemDesc.setMinimumSize(new Dimension(22, 16));
    txtItemDesc.setPreferredSize(new Dimension(520, 75));
    txtItemDesc.setText("");
    txtItemDesc.setLineWrap(true);
    txtItemDesc.addKeyListener(new PanelItemModify_txtItemDesc_keyAdapter(this));
    txtCategory.setText("");
    txtCategory.setPreferredSize(new Dimension(180, 20));
    txtCategory.setEditable(false);
    txtCategory.setNextFocusableComponent(txtItemDesc);
    txtCategory.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSubCategory.setText(lang.getString("SubCategory")+":");
    lblSubCategory.setPreferredSize(new Dimension(90, 20));
    lblSubCategory.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblPriceRange.setText(lang.getString("PriceRange")+":");
    lblPriceRange.setPreferredSize(new Dimension(90, 20));
    lblPriceRange.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboPriceRange.setRequestFocusEnabled(true);
    cboPriceRange.setPreferredSize(new Dimension(180, 20));
    cboPriceRange.setNextFocusableComponent(cboTransLevel);
    cboPriceRange.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboPriceRange.setMinimumSize(new Dimension(27, 22));
    cboPriceRange.addItem("Low Range");
    cboPriceRange.addItem("Mid Range");
    cboPriceRange.addItem("High Range");
    cboPriceRange.addItem("Top Range");
    lblItemDesc1.setText(lang.getString("ItemDesc")+":");
    lblItemDesc1.setPreferredSize(new Dimension(550, 25));
    lblItemDesc1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel3.setLayout(gridLayout2);
    lblVAT.setText(lang.getString("VAT")+":");
    lblVAT.setPreferredSize(new Dimension(90, 20));
    lblVAT.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnGenerateUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnGenerateUPC.setPreferredSize(new Dimension(70, 22));
    btnGenerateUPC.setToolTipText(lang.getString("GenerateUPC"));
    btnGenerateUPC.setText(lang.getString("Generate"));
    btnGenerateUPC.addActionListener(new PanelItemModify_btnGenerateUPC_actionAdapter(this));
    cboSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboSupplier.setPreferredSize(new Dimension(145, 20));
    btnSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSupplier.setPreferredSize(new Dimension(30, 20));
    btnSupplier.setText("..");
    btnSupplier.addActionListener(new PanelItemModify_btnSupplier_actionAdapter(this));
    cboVAT.setPreferredSize(new Dimension(180, 20));
    cboVAT.setEditable(true);
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setPreferredSize(new Dimension(100, 20));
    jLabel1.setText(lang.getString("PackingSize")+":");
    jLabel1.setBounds(new Rectangle(302, 202, 93, 20));
    txtPackSize.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtPackSize.setPreferredSize(new Dimension(180, 20));
    txtPackSize.setText("");
    txtPackSize.setBounds(new Rectangle(403, 200, 180, 20));
    txtPackSize.addKeyListener(new PanelItemModify_txtPackSize_keyAdapter(this));
    this.add(jPanel1, BorderLayout.NORTH);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel4, BorderLayout.WEST);
    jPanel4.add(jPanel3, null);
    jPanel3.add(jScrollPane2, null);
    jScrollPane2.getViewport().add(treProdHier, null);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel7.add(lblItemID, null);
    jPanel7.add(lblArtNo, null);
    jPanel7.add(lblItemName, null);
    jPanel7.add(lblSupplier, null);
    jPanel7.add(lblUnitCost, null);
    jPanel7.add(lblUnitPrice, null);
    jPanel7.add(lblPriceRange, null);
    jPanel7.add(lblVAT, null);
    jPanel5.add(txtPackSize, null);
    jPanel5.add(jLabel1, null);
    jPanel1.add(btnModify, null);
    jPanel1.add(btnCancel, null);
    jPanel8.add(txtItemID, null);
    jPanel8.add(txtArtNo, null);
    jPanel8.add(btnGenerateUPC, null);
    jPanel8.add(txtItemName, null);
    jPanel8.add(cboSupplier, null);
    jPanel8.add(btnSupplier, null);
    jPanel8.add(txtUnitCost, null);
    jPanel8.add(txtUnitPrice, null);
    jPanel8.add(cboPriceRange, null);
    jPanel8.add(cboVAT, null);
    jPanel5.add(jPanel9, null);
    jPanel5.add(jPanel8, null);
    jPanel10.add(lblTransLevel, null);
    jPanel10.add(lblItemLevel, null);
    jPanel10.add(lblColor, null);
    jPanel10.add(lblSize, null);
    jPanel10.add(lblMaterial, null);
    jPanel10.add(lblSubCategory, null);
    jPanel10.add(lblUnit, null);
    jPanel5.add(jPanel12, null);
    jPanel9.add(jPanel11, BorderLayout.CENTER);
    jPanel11.add(cboTransLevel, null);
    jPanel11.add(cboItemLevel, null);
    jPanel11.add(cboColor, null);
    jPanel11.add(cboSize, null);
    jPanel11.add(txtMaterial, null);
    jPanel11.add(txtCategory, null);
    jPanel11.add(cmbUnit, null);
    jPanel9.add(jPanel10, BorderLayout.WEST);
    jPanel12.add(jPanel13, null);
    jPanel13.add(lblItemDesc1, null);
    jPanel12.add(jPanel14, null);
    jPanel14.add(jScrollPane1, null);
    jPanel5.add(jPanel7, null);
    jScrollPane1.getViewport().add(txtItemDesc, null);
    cboVAT.addItem("0");
    cboVAT.addItem("5");
    cboVAT.addItem("10");
  }
  public void applyPermission() {
   EmpRight er = new EmpRight();
   er.initData(DAL, DAL.getEmpID());
   er.setData(DAL.getEmpID(), Constant.SCRS_ITEM_MODIFY);
   btnModify.setEnabled(er.getAdd());
   if(!er.getAdd()){
     ut.showMessage(frmMain, lang.getString("TT002"),
                    lang.getString("MS1085_CanNotUseScreen"));

   }
  }

  //show data()
  void showData(String itemID) {
    //show tree
    Vector vPrdHierAll = new Vector();
    vPrdHierAll = treProdHier.initHierAll(DAL, "BTM_IM_PROD_HIR");
    Vector vPrdHierActive = new Vector();
    vPrdHierActive = treProdHier.initHierActive(vPrdHierAll);
    treProdHier.showHierTree(vPrdHierActive, rootTree);
    treProdHier.updateUI();
//    treProdHier.setEnabled(false);
    ///--------------------------------------------------------
    txtUnitCost.setEditable(false);
    txtUnitPrice.setEditable(false);
    cboItemLevel.removeAllItems();
    cboTransLevel.removeAllItems();
    for (int i = 0; i < 3; i++) {
      cboItemLevel.addItem(new Integer(i + 1));
      cboTransLevel.addItem(new Integer(i + 1));
    }
    cboItemLevel.setEnabled(false);
    cboTransLevel.setEnabled(false);
    cboColor.setEnabled(true);
    cboSize.setEnabled(true);
    initSupplier();

//    showAttr();
//    Statement stmt = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    ResultSet rs3 = null;
    String sql = "";
    try {
      sql = " select distinct im.ITEM_ID, im.status, im.art_no, im.item_name,  "
      + "	   im.item_desc, s.supp_name, im.standard_uom, im.pack_size,  "
      + "	   un.new_unit_cost unit_cost,ils.VAT, ph.new_price, im.trans_level,  "
      + "	   im.item_level, im.attr1_dtl_id, im.attr2_dtl_id, im.material,  "
      + "	   im.price_range, im.CHILD_ID  "
      + " from btm_im_item_master im, btm_im_item_loc_supplier ils,  "
      + " 	  btm_im_price_hist ph,  btm_im_supplier s, "
      + "	  btm_im_unit_cost_hist un  "
      + " where im.item_id = ils.item_id and im.item_id = ph.item_id  "
      + "  and  un.ITEM_ID=im.ITEM_ID and un.STATUS = '1'  "
      + "  and  ils.supp_id = s.supp_id and ph.status = '1' and im.item_id = '"+itemID+"' and ph.STORE_ID=ils.store_id and ils.store_id=un.store_id and ils.store_id= "+DAL.getStoreID();
//      System.out.println(sql);
//       stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        //set Selection Path
        treProdHier.setSelectionPathByNameNode(rootTree,
                                               rs.getString("CHILD_ID"));
        sID=rs.getString("CHILD_ID");
        treProdHier.scrollRowToVisible(treProdHier.getSelectionRows()[0]);

        status =  rs.getString("status").trim();
        if (status.equalsIgnoreCase("W")) {
          txtUnitCost.setEditable(true);
          txtUnitPrice.setEditable(true);
        }
        cboPriceRange.setSelectedItem(rs.getString("price_range"));
        txtItemID.setText(rs.getString("item_id"));
        txtArtNo.setText(rs.getString("art_no"));

        //save old UPC before modify
        oldUPC = rs.getString("art_no");

        sArtNo=rs.getString("art_no");
        txtItemName.setText(rs.getString("item_name"));
        cboSupplier.setSelectedItem(rs.getString("supp_name"));
        cboSupplier.setToolTipText(rs.getString("supp_name"));
        txtUnitCost.setText(ut.formatNumber(rs.getString("unit_cost")));
        txtUnitPrice.setText(ut.formatNumber(rs.getString("new_price")));
        cboVAT.setSelectedItem(rs.getString("VAT"));
        cmbUnit.setData(getUOM());
        cmbUnit.setSelectedData("Cai");
//        this.cmbUnit.removeAllItems();
//        this.cmbUnit.addItem("Cai");
//        this.cmbUnit.addItem("Kg");
//        this.cmbUnit.addItem("Hu");
//        this.cmbUnit.addItem("Binh");
//        this.cmbUnit.addItem("Cay");
//        this.cmbUnit.addItem("Goi");
//        this.cmbUnit.addItem("Vi");
//        this.cmbUnit.addItem("Loc");
//        this.cmbUnit.addItem("Hop");
//        this.cmbUnit.addItem("Tuyp");
//        this.cmbUnit.addItem("Chai");
//        this.cmbUnit.addItem("Lon");
//        this.cmbUnit.addItem("Cuon");
//        this.cmbUnit.addItem("Thung");
//        this.cmbUnit.addItem("Piece");
        cmbUnit.setSelectedItem(rs.getString("STANDARD_UOM"));
        txtPackSize.setText(rs.getString("PACK_SIZE"));
        cboTransLevel.setSelectedItem(new Integer(rs.getInt("trans_level")));
        cboItemLevel.setSelectedItem(new Integer(rs.getInt("item_level")));

        //show all attributes of department into combobox
        showAttr(treProdHier.getParentId(treProdHier.getParentId()));

        //show attribute which belongs to this item
        if (rs.getString("attr1_dtl_id") != null &&
            !rs.getString("attr1_dtl_id").equalsIgnoreCase("-1")) {
          cboColor.setSelectedData(rs.getString("attr1_dtl_id"));
        }
        else {
          cboColor.setSelectedIndex(0);
        }
        if (rs.getString("attr2_dtl_id") != null &&
            !rs.getString("attr2_dtl_id").equalsIgnoreCase("-1")) {
          cboSize.setSelectedData(rs.getString("attr2_dtl_id"));
          sSize = cboSize.getSelectedData();
        }
        else {
          cboSize.setSelectedIndex(0);
        }
        txtMaterial.setText(rs.getString("material"));
        txtItemDesc.setText(rs.getString("item_desc"));
      }


      if (cboItemLevel.getSelectedIndex() > 0) {
        cboSize.setEnabled(true);
        cboColor.setEnabled(true);
      }
      this.size = cboSize.getSelectedData();
      //
      sql="select count(*) as maxLevel from BTM_IM_PROD_HIR_TYPE";
      rs1=DAL.executeQueries(sql);
      if(rs1.next()){
        maxLevelProdHier=rs1.getInt("maxLevel");
      }
      //show sub category
      sql = "select child_name from btm_im_prod_hir where child_id='" + sID + "'";
      rs3 = DAL.executeQueries(sql);
      if (rs3.next()){
        txtCategory.setText(rs3.getString("child_name"));
      }

      rs.getStatement().close();
      rs1.getStatement().close();
      rs3.getStatement().close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  //get data UOM and assign to rs
    private ResultSet getUOM(){
      String SQL="SELECT UOM_ID, UOM_DESC FROM BTM_IM_UOM_STANDARD order by UOM_ID asc";
      ResultSet rs = null;
      try{
        rs = DAL.executeScrollQueries(SQL);
//      rs.getStatement().close();
      }catch(Exception ex){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      }
      return rs;
  }
  private void initSupplier() {
    cboSupplier.setData(getSupplierData());
    try {
      stmt.close();
    }
    catch (Exception ex) {}
  }

  ResultSet getSupplierData() {
    ResultSet rs = null;
    String sqlStr = "Select Supp_ID, Supp_Name From BTM_IM_SUPPLIER order by supp_name asc";
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sqlStr, stmt);
    }
    catch (Exception ex) {}
    return rs;
  }



  //show attribute base on prodGroup
  void showAttr(String prodGroup) {

    ResultSet rs1 = null;
    ResultSet rs2 = null;
    String sql = "";
    try {
      sql = "select attr_dtl_id, attr_dtl_desc from btm_im_attr_dtl where attr_id = 1 and prod_group_id='" +
          prodGroup + "'";
//      System.out.println(sql);
      rs1 = DAL.executeScrollQueries(sql);
      cboColor.setData1(rs1);

      sql = "";
      sql = "select attr_dtl_id, attr_dtl_desc from btm_im_attr_dtl where attr_id = 2 and prod_group_id='" +
          prodGroup + "'";
//      System.out.println("size :"+sql);
      rs2 = DAL.executeScrollQueries(sql);
      cboSize.setData1(rs2);

      rs1.getStatement().close();
      rs2.getStatement().close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  void cboItemLevel_actionPerformed(ActionEvent e) {
    if (cboItemLevel.getSelectedIndex() > 0) {
      cboColor.setEnabled(true);
      cboSize.setEnabled(true);
    }
    else {
      cboColor.setEnabled(false);
      cboSize.setEnabled(false);
      if (cboColor.getItemCount() > 0 && cboSize.getItemCount() > 0) {
        cboColor.setSelectedIndex(0);
        cboSize.setSelectedIndex(0);
      }
    }
    if (cboItemLevel.getSelectedIndex() == 1) {
      cboColor.setSelectedIndex(0);
      cboSize.setSelectedIndex(0);
    }
  }

  void txtArtNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtArtNo, ItemMaster.LEN_ITEM_ARTNO);
    ut.checkNumber(e);
  }

  void txtItemName_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtItemName, ItemMaster.LEN_ITEM_NAME);
  }

  void txtMaterial_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtMaterial, ItemMaster.LEN_ITEM_MATERIAL);
  }

  void txtItemDesc_keyTyped(KeyEvent e) {
    ut.limitLenjTextArea(e, txtItemDesc, ItemMaster.LEN_ITEM_DESC);
  }
//check if item eixist, base on ArtNo
//  boolean checkItem() {
//    String colorID = "";
//    String sizeID = "";
//    String sql = "";
//    if (!txtArtNo.getText().equalsIgnoreCase("")) {
//
//        sql =
//            "select * from btm_im_item_master where art_no ='" +
//            txtArtNo.getText().trim() +
//            "' ";
//        System.out.println(sql);
//        try {
//          ResultSet rs = null;
//          rs = DAL.executeQueries(sql);
//          if (rs.next()) {
//            // UPC is exist if belongs to other item
//            if(!rs.getString("item_id").equals(txtItemID.getText().trim())){
//              return false;
//            }
//          }
//          rs.getStatement().close();
//        }
//        catch (Exception e) {
//          e.printStackTrace();
//        }
//
//    }
//
//    return true;
//  }

  void btnModify_actionPerformed(ActionEvent e) {
    if (txtArtNo.getText().equalsIgnoreCase("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1007_UPCIsNotNull"));
      txtArtNo.requestFocus(true);
      return;
    }
    if (txtArtNo.getText().trim().length() < ItemMaster.LEN_ITEM_ARTNO) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1099_LengthOfUPCshould13"));
      txtArtNo.requestFocus(true);
      txtArtNo.selectAll();
      return;
    }
    if(!txtArtNo.getText().trim().equalsIgnoreCase(oldUPC)){
      if (!checkBarCodeStandard(txtArtNo.getText().trim())) {
        return;
      }
    }
    if (txtItemName.getText().equalsIgnoreCase("") ) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1100_ItemNameNotNull"));
      txtItemName.requestFocus(true);
      return;
    }
    if ( ut.foundSpecialChar(txtItemName.getText())) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1101_ItemNameHasSpecialChar"));
      txtItemName.requestFocus(true);
      return;
    }

    if (txtUnitCost.getText().equalsIgnoreCase("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1102_UnitCostNotNull"));
      return;
    }
    if (txtUnitPrice.getText().equalsIgnoreCase("")) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1103_UnitPriceNotNull"));
      return;
    }
    //Check VAT
    if (cboVAT.getSelectedItem().toString().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1104_VATnotNull"));
      cboVAT.requestFocus(true);
      return;
    }

    //check VAT
    if (Integer.parseInt(cboVAT.getSelectedItem().toString()) >=100){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1105_VATmustLessThan100"));
      cboVAT.requestFocus(true);
      return;
    }

    if (!txtArtNo.getText().equalsIgnoreCase(sArtNo)||!cboSize.getSelectedData().equalsIgnoreCase(sSize)){
      if (!bItemMaster.checkItem(txtItemID.getText(),txtArtNo.getText())) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1106_UPC_Alreadyexisted"));
        txtArtNo.requestFocus(true);
        txtArtNo.selectAll();
        return;
      }
    }
    if(cmbUnit.getSelectedItem().toString().equalsIgnoreCase("Set")){
      if(txtPackSize.getText().trim().equalsIgnoreCase("")){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1107_SizeNotNull"));
        txtPackSize.requestFocus(true);
        return;
      }
    }


    String artNo = "";
    String itemName = "";
    int transLevel = 0;
    int itemLevel = 0;
    String colorID = "";
    String sizeID = "";
    String colorName = "";
    String sizeName = "";
    String material = "";
    String itemDesc = "";
    String priceRange = "";
    artNo = txtArtNo.getText();
    itemName = ut.putCommaToString(txtItemName.getText());
    transLevel = cboTransLevel.getSelectedIndex() + 1;
    itemLevel = cboItemLevel.getSelectedIndex() + 1;
    itemDesc = ut.putCommaToString(txtItemDesc.getText());
    material = ut.putCommaToString(txtMaterial.getText());
    if (cboColor.getSelectedIndex() == 0) {
      colorName = "";
      colorID = "-1";
    }
    else {
      colorName = cboColor.getSelectedItem().toString();
      colorID = cboColor.getSelectedData();
    }
    if (cboSize.getSelectedIndex() == 0) {
      sizeName = "";
      sizeID = "-1";
    }
    else {
      sizeName = cboSize.getSelectedItem().toString();
      sizeID = cboSize.getSelectedData();
    }
    priceRange = cboPriceRange.getSelectedItem().toString();
    String sql = "";
    if (ut.convertToDouble(txtUnitCost.getText()) > 0 &&
        ut.convertToDouble(txtUnitPrice.getText()) > 0) {
      sql = "update btm_im_item_master set art_no ='" + artNo +
          "',item_name='" +
          itemName + "',trans_level=" + transLevel + ",item_level=" +
          itemLevel +
          ",attr1_dtl_id='" + colorID + "',attr2_dtl_id='" + sizeID +
          "', material='" +
          material + "', item_desc ='" + itemDesc + "', price_range ='" +
          priceRange + "',status = 'A' " + " , CHILD_ID = '" + sID + "' , STANDARD_UOM = '" + cmbUnit.getSelectedItem().toString() + "', PACK_SIZE = '" + ut.putCommaToString(txtPackSize.getText()) +
          "', LAST_UPD_DATE = to_date('" + ut.getSystemDate(1) + "','dd/mm/yyyy')" +
          " where item_id ='" + txtItemID.getText() + "'";
    }
    else {
      sql = "update btm_im_item_master set art_no ='" + artNo +
          "',item_name='" +
          itemName + "',trans_level=" + transLevel + ",item_level=" +
          itemLevel +
          ",attr1_dtl_id='" + colorID + "',attr2_dtl_id='" + sizeID +
          "', material='" +
          material + "', item_desc ='" + itemDesc + "', price_range ='" +
          priceRange + "', CHILD_ID = '" + sID  + "' , STANDARD_UOM = '" +
          cmbUnit.getSelectedItem().toString() + "', PACK_SIZE = '" + ut.putCommaToString(txtPackSize.getText()) +
          "', LAST_UPD_DATE = to_date('" + ut.getSystemDate(1) + "','dd/mm/yyyy')" +
          " where item_id ='" + txtItemID.getText() + "'";
    }
    String sql1 = "";
    sql1 = " UPDATE BTM_IM_PRICE_HIST  "
    + "SET	   PRICE_EFF_DATE = to_date('"+ut.getSystemDate()+"','"+ut.DATE_FORMAT+"') "
    + ",NEW_PRICE = '"+ut.convertToDouble(txtUnitPrice.getText())+ "' "
    + ",UNIT_COST = '"+ut.convertToDouble(txtUnitCost.getText())+"', "
    + "	   LAST_UPD_DATE = to_date('"+ut.getSystemDate(1)+"','dd/mm/yyyy'), BATCH_SEQ = 0, "
    + "	   ATTR1_NAME = '"+colorName+"', ATTR2_NAME = '"+sizeName+"' "
    + "WHERE ITEM_ID = '"+txtItemID.getText()+"' "
//    + "AND STORE_ID = '"+DAL.getStoreID()+"'  "
    + "AND	  PRICE_END_DATE = TO_DATE('7/7/7777','dd/mm/yyyy')  "
    + "AND   STATUS = 1 ";

    String sql2 = "";
//    if (ut.convertToDouble(txtUnitCost.getText()) > 0 &&
//        ut.convertToDouble(txtUnitPrice.getText()) > 0) {
      sql2 = "update btm_im_item_loc_supplier set supp_id = '" + cboSupplier.getSelectedData() +
          "',unit_cost = " + ut.convertToDouble(txtUnitCost.getText()) +
          ", avg_cost = " + ut.convertToDouble(txtUnitCost.getText()) +
          ", VAT = " + ut.convertToDouble(cboVAT.getSelectedItem().toString()) +
          " where item_id = '" + txtItemID.getText() + "'";
//      System.out.println(sql2);
//    }
//modify data in unit_cost_hist
    String sql3 = "";
    sql3 = " UPDATE BTM_IM_UNIT_COST_HIST  "
    + "SET	   UNIT_COST_EFF_DATE = to_date('"+ut.getSystemDate(1)+"','dd/mm/yyyy'), "
    + "	   NEW_UNIT_COST = '" + ut.convertToDouble(txtUnitCost.getText())+ "', "
    + "    PRICE='"+ut.convertToDouble(txtUnitPrice.getText())+"', "
    + "	   LAST_UPD_DATE = to_date('"+ut.getSystemDate(1)+"','dd/mm/yyyy'), BATCH_SEQ = 0, "
    + "	   ATTR1_NAME = '"+colorName+"', ATTR2_NAME = '"+sizeName+"' "
    + "WHERE ITEM_ID = '"+txtItemID.getText()+"'"
//    + "AND STORE_ID = '"+DAL.getStoreID()+"'  "
    + "AND	  UNIT_COST_END_DATE = TO_DATE('7/7/7777','dd/mm/yyyy')  "
    + "AND   STATUS = 1 ";

    this.size = "";

    //update SEQ if Art_No begin '200000'
      if(txtArtNo.getText().substring(0,6).equalsIgnoreCase("200000")){
        updateSEQForHomeItem();
      }

    try {
      Statement stmt = DAL.getConnection().createStatement();
//      System.out.println(sql);
//      System.out.println(sql1);
      DAL.setBeginTrans(DAL.getConnection());
      DAL.executeUpdate(sql, stmt);
      DAL.executeUpdate(sql2, stmt);

      //if status is 'WorkSheet' then update in the price_hist and unit_cost_hist table
      if(status.equalsIgnoreCase("W")){
//        System.out.println("SQL3:  "+ sql3);
        DAL.executeUpdate(sql1, stmt);
        DAL.executeUpdate(sql3, stmt);
      }

      updateSupplier(cboSupplier.getSelectedData(),txtItemID.getText(),stmt);
      DAL.setEndTrans(DAL.getConnection());
      stmt.close();

    }
    catch (Exception ex) {
      ex.printStackTrace();

    }

    //show dailogitemlookup
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0016"), true, frmMain, frmMain.itemBusObject);
    dlgItemLookup.getData(frmMain.itemBusObject.getItemID(),
                          frmMain.itemBusObject.getItemName(),
                          frmMain.itemBusObject.getArtNo(),
                          frmMain.itemBusObject.getSupplierID(),
                          frmMain.itemBusObject.getSupplierName(),
                          frmMain.itemBusObject.getCreatedDate(),
                          frmMain.itemBusObject.getStatus(),
                          frmMain.itemBusObject.getSearchLimit());
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      showData(dlgItemLookup.getItemID());
//      txtCategory.setText(dlgItemLookup.getSubCategoryName());
    }
    else {
      frmMain.showScreen(Constant.SCRS_ITEM_SETUP);
      frmMain.pnlItemSetup.showData();
      frmMain.pnlItemSetup.applyPermission();
    }
  }

  //update Supplier for all tables relative with item modified =
  void updateSupplier(String suppID,String itemCode,Statement stmt){
    String strSql1 = "Update BTM_IM_INV_ITEM_DCOM set Supp_ID='" + suppID + "' where Item_ID='" + itemCode + "'";
    String strSql2 = "Update BTM_IM_INV_ITEM_DM set Supp_ID='" + suppID + "' where Item_ID='" + itemCode + "'";
    String strSql3 = "Update BTM_IM_SALES_ITEM_DM set Supp_ID='" + suppID + "' where Item_ID='" + itemCode + "'";
    String strSql4 = "Update BTM_IM_SALES_RETURN_ITEM_DM set Supp_ID='" + suppID + "' where Item_ID='" + itemCode + "'";
    try {
      DAL.executeUpdate(strSql1, stmt);
      DAL.executeUpdate(strSql2, stmt);
      DAL.executeUpdate(strSql3, stmt);
      DAL.executeUpdate(strSql4, stmt);
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  String getMaxSEQ(){
    String strSql = "Select BARCODE_SEQ From BTM_IM_BARCODE_SEQ Where SBCLASS_ID='0000'";
    String maxSEQ = "";
    ResultSet rs = null;
    Statement stmt = null;
    try {
       stmt = DAL.getConnection().createStatement();
       rs = DAL.executeQueries(strSql,stmt);
       if(rs.next()){
         maxSEQ = rs.getString("BARCODE_SEQ");
       }
       rs.close();
       stmt.close();
     }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
     }
    return maxSEQ;
  }

  //increase seq by 1 , and insert into BTM_IM_BARCODE_SEQ for next item setup
  private void updateSEQForHomeItem(){
    Statement stmt = null;
    int seqNumber = Integer.parseInt(getMaxSEQ());
    String seq_ID = String.valueOf(seqNumber);
    if( seqNumber < 9999 )
      seqNumber ++;
    seq_ID = "000" + String.valueOf(seqNumber);
    //get last 4 characters of BARCODE_SEQ
    seq_ID = seq_ID.substring(seq_ID.length()-4);
    String sqlStr = "Update BTM_IM_BARCODE_SEQ set BARCODE_SEQ = '" + seq_ID + "' Where SBCLASS_ID='0000'";
    try{
      stmt = DAL.getConnection().createStatement();
      DAL.executeUpdate(sqlStr,stmt);
      stmt.close();
    }
    catch(Exception ex){}
  }


  void updateTableDM(String sID) {
    try {
      String sql1 = "update BTM_IM_INV_ITEM_DM set " +
          "GENDER_ID = '" + sID + "', " +
          "PROD_TYPE_ID = '" + treProdHier.getParentId(sID) + "'," +
          "CATEGORY_ID = '" + treProdHier.getParentId(treProdHier.getParentId(sID)) +
          "'," +
          "PROD_GRP_ID = '" +
                    treProdHier.getParentId(treProdHier.getParentId(treProdHier.
                    getParentId(sID))) + "' " +
          "where item_id = '" + txtItemID.getText() + "'";
      String sql2 = "update BTM_IM_SALES_ITEM_DM set " +
          "GENDER_ID = '" + sID + "', " +
           "PROD_TYPE_ID = '" + treProdHier.getParentId(sID) + "'," +
           "CATEGORY_ID = '" + treProdHier.getParentId(treProdHier.getParentId(sID)) +
           "'," +
           "PROD_GRP_ID = '" +
                     treProdHier.getParentId(treProdHier.getParentId(treProdHier.
                     getParentId(sID))) + "' " +
          "where item_id = '" + txtItemID.getText() + "'";
//      System.out.println("SQL1:"+sql1);
//      System.out.println("SQL2:"+sql2);
      Statement stmt = DAL.getConnection().createStatement();
      DAL.setBeginTrans(DAL.getConnection());
      DAL.executeUpdate(sql1, stmt);
      DAL.executeUpdate(sql2, stmt);
      DAL.setEndTrans(DAL.getConnection());
      stmt.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();

    }
  }

  void cboColor_focusLost(FocusEvent e) {
    if (cboItemLevel.getSelectedItem().toString().equalsIgnoreCase("2")) {
      if (cboSize.getSelectedIndex() != -1) {
        this.cboSize.setSelectedIndex(0);
      }
    }
  }

  void cboSize_focusLost(FocusEvent e) {
    if (cboItemLevel.getSelectedItem().toString().equalsIgnoreCase("2")) {
      if (cboColor.getSelectedIndex() != -1) {
        this.cboColor.setSelectedIndex(0);
      }
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0016"), true, frmMain, frmMain.itemBusObject);
    dlgItemLookup.getData(frmMain.itemBusObject.getItemID(),
                          frmMain.itemBusObject.getItemName(),
                          frmMain.itemBusObject.getArtNo(),
                          frmMain.itemBusObject.getSupplierID(),
                          frmMain.itemBusObject.getSupplierName(),
                          frmMain.itemBusObject.getCreatedDate(),
                          frmMain.itemBusObject.getStatus(),
                          frmMain.itemBusObject.getSearchLimit());
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done) {
      showData(dlgItemLookup.getItemID());
//      txtCategory.setText(dlgItemLookup.getSubCategoryName());
    }
    else {
      frmMain.showScreen(Constant.SCRS_ITEM_SETUP);
      frmMain.pnlItemSetup.showData();
      frmMain.pnlItemSetup.cmbItemLevel.setEnabled(false);
      frmMain.pnlItemSetup.cmbTransLevel.setEnabled(false);
      frmMain.pnlItemSetup.resetDataInItemsetupScreen();
      frmMain.pnlItemSetup.treProdHier.setSelectionRow(0);
    }

  }

  void cboSize_keyTyped(KeyEvent e) {
    cboSize.showPopup();
  }

  void cboColor_keyTyped(KeyEvent e) {
    cboColor.showPopup();
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

      if (identifier.intValue() == KeyEvent.VK_F1 ||
          identifier.intValue() == KeyEvent.VK_ENTER) {
        btnModify.doClick();
      }

      else if (identifier.intValue() == KeyEvent.VK_F2 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void txtUnitCost_focusGained(FocusEvent e) {
    if (txtUnitCost.isEditable()) {
      if (!txtUnitCost.getText().equalsIgnoreCase("")) {
        txtUnitCost.setText("" + ut.convertToDouble(txtUnitCost.getText()));
      }
    }
  }

  void txtUnitCost_focusLost(FocusEvent e) {
    if (txtUnitCost.isEditable()) {
      if (!txtUnitCost.getText().equalsIgnoreCase("")) {
        txtUnitCost.setText("" + ut.formatNumber(txtUnitCost.getText()));
      }
    }
  }

  void txtUnitPrice_focusGained(FocusEvent e) {
    if (txtUnitPrice.isEditable()) {
      if (!txtUnitPrice.getText().equalsIgnoreCase("")) {
        txtUnitPrice.setText("" + ut.convertToDouble(txtUnitPrice.getText()));
      }
    }

  }

  void txtUnitPrice_focusLost(FocusEvent e) {
    if (txtUnitPrice.isEditable()) {
      if (!txtUnitPrice.getText().equalsIgnoreCase("")) {
        txtUnitPrice.setText("" + ut.formatNumber(txtUnitPrice.getText()));
      }
    }
  }

  private Vector fetchDataIntoVectorArtt(String ATTR_TYPE_ID) {
    //assign for Prod Hier Type
    Statement stmt = null;
    ItemAttribute iItemSetup = new ItemAttribute();
    Vector vListPrdHier = new Vector();
    String sql;

    ResultSet rs = null;
    iItemSetup = null;
    vListPrdHier.removeAllElements();
    sql = "select ATTR_DTL_ID, ATTR_DTL_DESC, PROD_GROUP_ID from BTM_IM_ATTR_DTL where ATTR_ID='" +
        ATTR_TYPE_ID + "' order by ATTR_DTL_DESC";
//    System.out.println(sql);
    int i = 0;
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      iItemSetup = new ItemAttribute("", "None", "");
      vListPrdHier.add(iItemSetup);
      while (rs.next()) {
        i += 1;
        iItemSetup = new ItemAttribute(rs.getString("ATTR_DTL_ID"),
                                       rs.getString("ATTR_DTL_DESC"),
                                       rs.getString("PROD_GROUP_ID"));
        vListPrdHier.add(iItemSetup);
      }

      rs.close();
      stmt.close();

      return vListPrdHier;
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
      return vListPrdHier;
    }
  }

  private void addDataIntoComboInMyPanel(String prodGroup) {
    //fetching data into vector after that fetching data into combobox
    vColor = this.fetchDataIntoVectorArtt("1"); //get Color
    this.fetchDataIntoComboAttribute(this.cboColor, 1, prodGroup);

    //fetching data into vector after that fetching data into combobox
    vSize = this.fetchDataIntoVectorArtt("2"); //get size
    fetchDataIntoComboAttribute(this.cboSize, 2, prodGroup);
  }

  private void fetchDataIntoComboAttribute(BJComboBox cbo, int attrID,
                                           String prodGroupID) {
    //assign for Prod Hier Type
    String sql = "select attr_dtl_id, attr_dtl_desc from btm_im_attr_dtl " +
        " where attr_id = " + attrID + " and prod_group_id ='" + prodGroupID +
        "' order by upper(attr_dtl_desc)";
//    System.out.println(sql);
    try {
      Statement stmt = null;
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      cbo.setData1(DAL.executeScrollQueries(sql, stmt));
      stmt.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  void treProdHier_valueChanged(TreeSelectionEvent e) {
    BNode currentNode;
    // Get paths of all selected nodes
    if (treProdHier.getSelectionPaths() != null) {
      String prodGroup = treProdHier.getParentId(treProdHier.getParentId(
          treProdHier.getParentId()));
      addDataIntoComboInMyPanel(prodGroup);
    }
  }

  void treProdHier_mouseClicked(MouseEvent e) {
    //only enable select node which full hierachy
    if (treProdHier.isLeaf() && (maxLevelProdHier==treProdHier.getPosNode()-1) && !sID.equalsIgnoreCase(treProdHier.getChildId())) {
      int answer = ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1097_ChangeSubCategoryThisItem"),
                   DialogMessage.QUESTION_MESSAGE,
                   DialogMessage.YES_NO_OPTION);
      if(answer==DialogMessage.YES_VALUE){
        txtCategory.setText(treProdHier.getNodeName());
        sID = treProdHier.getChildId();
        //show list of attibute at new department
        showAttr(treProdHier.getParentId(treProdHier.getParentId()));

      }
    }
  }

   void btnGenerateUPC_actionPerformed(ActionEvent e) {
    String upc = txtArtNo.getText().trim();
    if(upc.length() ==0){
      txtArtNo.setText(getNewBarCode());
    }else if(upc.length()==8 || upc.length()==12){
      txtArtNo.setText(getBarCodeStandard(upc));
    }else if(upc.length()==Constant.LENGHT_UPC_INPUT){
      if(!checkBarCodeStandard(upc)){
        return;
      }
    }
  }
  boolean checkBarCodeStandard(String upc){
   int checkDigit = ut.getCheckDigitEAN13(upc.substring(0,12));
   if(checkDigit != Integer.parseInt(upc.substring(12,13))){
     ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1098_UPC_Invalid"));
     txtArtNo.setSelectionStart(12);
     txtArtNo.setSelectionEnd(13);
     txtArtNo.requestFocus(true);
     return false;
   }
   return true;
  }
  String getNewBarCode(){
    String strSql = "Select BARCODE_SEQ From BTM_IM_BARCODE_SEQ Where SBCLASS_ID='0000'";
    String barCode = "";
    ResultSet rs = null;
    Statement stmt = null;
    String hostID =  DAL.getHostID();//2 digits
    if(hostID.length()==1){//if 1 digit, then add "0" previous
      hostID ="0" + hostID;
    }
    try {
       stmt = DAL.getConnection().createStatement();
       rs = DAL.executeQueries(strSql,stmt);
       if(rs.next()){
         barCode = "200000" + hostID + rs.getString("BARCODE_SEQ");
       }
       rs.close();
       stmt.close();
     }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
     }
    barCode += ut.getCheckDigitEAN13(barCode);
    return barCode;
  }
  String getBarCodeStandard(String upc){
    //add Zero digits to fit 12 digits
    String barCode = "00000000000" + upc;
    barCode = barCode.substring(barCode.length()-13);
//    barCode += String.valueOf(ut.getCheckDigitEAN13(barCode));
    return barCode;
  }

  void btnSupplier_actionPerformed(ActionEvent e) {
    SupplierBusObj suppBusObj = new SupplierBusObj();
    DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(frmMain,
        lang.getString("TT0013"), true, frmMain, suppBusObj);
    dlgSupplierSearch.setVisible(true);
    if (dlgSupplierSearch.done) {
      cboSupplier.setSelectedItem(dlgSupplierSearch.getSuppName());
    }
  }

  void cboVAT_keyTyped(KeyEvent e) {
    ut.limitLenjComboBox(e, cboVAT, 3);
    if (e.getKeyChar() != '.') {
      if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
          (e.getKeyChar() != e.VK_BACK_SPACE))
        e.consume();
    }
    else {
      e.consume();
    }

  }

  void cmbUnit_itemStateChanged(ItemEvent e) {
//    if(cmbUnit.getSelectedItem().toString().equalsIgnoreCase("Set")){
//      txtPackSize.setEditable(true);
//      txtPackSize.requestFocus(true);
//    }else{
//      txtPackSize.setEditable(false);
//      txtPackSize.setText("");
//    }
  }

  void txtPackSize_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtPackSize,40);
  }


}

class PanelItemModify_cboItemLevel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelItemModify adaptee;

  PanelItemModify_cboItemLevel_actionAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cboItemLevel_actionPerformed(e);
  }
}

class PanelItemModify_txtArtNo_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelItemModify adaptee;

  PanelItemModify_txtArtNo_keyAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelItemModify_txtItemName_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelItemModify adaptee;

  PanelItemModify_txtItemName_keyAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtItemName_keyTyped(e);
  }
}

class PanelItemModify_txtMaterial_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelItemModify adaptee;

  PanelItemModify_txtMaterial_keyAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtMaterial_keyTyped(e);
  }
}

class PanelItemModify_txtItemDesc_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelItemModify adaptee;

  PanelItemModify_txtItemDesc_keyAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtItemDesc_keyTyped(e);
  }
}

class PanelItemModify_btnModify_actionAdapter
    implements java.awt.event.ActionListener {
  PanelItemModify adaptee;

  PanelItemModify_btnModify_actionAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelItemModify_cboColor_focusAdapter
    extends java.awt.event.FocusAdapter {
  PanelItemModify adaptee;

  PanelItemModify_cboColor_focusAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.cboColor_focusLost(e);
  }
}

class PanelItemModify_cboSize_focusAdapter
    extends java.awt.event.FocusAdapter {
  PanelItemModify adaptee;

  PanelItemModify_cboSize_focusAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.cboSize_focusLost(e);
  }
}

class PanelItemModify_btnCancel_actionAdapter
    implements java.awt.event.ActionListener {
  PanelItemModify adaptee;

  PanelItemModify_btnCancel_actionAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelItemModify_cboSize_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelItemModify adaptee;

  PanelItemModify_cboSize_keyAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.cboSize_keyTyped(e);
  }
}

class PanelItemModify_cboColor_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelItemModify adaptee;

  PanelItemModify_cboColor_keyAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.cboColor_keyTyped(e);
  }
}

class PanelItemModify_txtUnitCost_focusAdapter
    extends java.awt.event.FocusAdapter {
  PanelItemModify adaptee;

  PanelItemModify_txtUnitCost_focusAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void focusGained(FocusEvent e) {
    adaptee.txtUnitCost_focusGained(e);
  }

  public void focusLost(FocusEvent e) {
    adaptee.txtUnitCost_focusLost(e);
  }
}

class PanelItemModify_txtUnitPrice_focusAdapter
    extends java.awt.event.FocusAdapter {
  PanelItemModify adaptee;

  PanelItemModify_txtUnitPrice_focusAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void focusGained(FocusEvent e) {
    adaptee.txtUnitPrice_focusGained(e);
  }

  public void focusLost(FocusEvent e) {
    adaptee.txtUnitPrice_focusLost(e);
  }
}

class PanelItemModify_treProdHier_treeSelectionAdapter
    implements javax.swing.event.TreeSelectionListener {
  PanelItemModify adaptee;

  PanelItemModify_treProdHier_treeSelectionAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void valueChanged(TreeSelectionEvent e) {
    adaptee.treProdHier_valueChanged(e);
  }
}

class PanelItemModify_treProdHier_mouseAdapter
    extends java.awt.event.MouseAdapter {
  PanelItemModify adaptee;

  PanelItemModify_treProdHier_mouseAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.treProdHier_mouseClicked(e);
  }
}

class PanelItemModify_btnGenerateUPC_actionAdapter implements java.awt.event.ActionListener {
  PanelItemModify adaptee;

  PanelItemModify_btnGenerateUPC_actionAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnGenerateUPC_actionPerformed(e);
  }
}

class PanelItemModify_btnSupplier_actionAdapter implements java.awt.event.ActionListener {
  PanelItemModify adaptee;

  PanelItemModify_btnSupplier_actionAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplier_actionPerformed(e);
  }
}

class PanelItemModify_cboVAT_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemModify adaptee;

  PanelItemModify_cboVAT_keyAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboVAT_keyTyped(e);
  }
}

class PanelItemModify_cmbUnit_itemAdapter implements java.awt.event.ItemListener {
  PanelItemModify adaptee;

  PanelItemModify_cmbUnit_itemAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.cmbUnit_itemStateChanged(e);
  }
}

class PanelItemModify_txtPackSize_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemModify adaptee;

  PanelItemModify_txtPackSize_keyAdapter(PanelItemModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPackSize_keyTyped(e);
  }
}
