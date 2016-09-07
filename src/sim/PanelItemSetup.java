package sim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import java.sql.*;
import java.sql.Statement;
import common.*;
import btm.attr.*;
import btm.swing.*;
import java.util.*;
import javax.swing.border.*;
import btm.business.*;
import javax.swing.event.*;
import java.beans.*;
import java.util.*;
import java.text.NumberFormat;
//import com.borland.jbcl.layout.*;


/**
 *
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Item Setup</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */
//Modify barcode generate:
public class PanelItemSetup extends JPanel {

  String ItemCode, ItemName, ItemDesc, ColorID, SizeID,  Child_ID,standardUom ,SupplierID,ArtNo;
  long transLevel, itemLevel,transType ;
  String subClass = "";
  String seqID = "";
  double  unitCost,unitPrice, newPrice,sellingUnitRetailnewPrice,VAT;
  java.sql.Date priceEffDate;
  Utils ut = new Utils();
  boolean flagLeaf = false;
  //Declare parameter for Business Layer
  DataAccessLayer DAL ;//= new DataAccessLayer();
  btm.business.ItemMasterBusObj bItemMaster;
  btm.business.PriceHistBusObj bPriceHist = new btm.business.PriceHistBusObj();
  btm.business.UnitCostHistBusObj bUnitCostHist = new btm.business.UnitCostHistBusObj();
  btm.business.ItemLocSupplierBusObj bItemLocSupplier = new btm.business.ItemLocSupplierBusObj();

//  DialogLocation dlgLocation = new DialogLocation(this.frmMain,"", true);
  DialogLocation dlgLocation ;
  //Declare  "vColor" to hold Color infomation
  Vector vColor;
  //Declare  "vSize" to hold Size infomation
  Vector vSize;
  //Declare  "vSupplier" to hold Supplier infomation
  Vector vSupplier = new Vector();
  //Declare  parameter to hold ChildID infomation
  String childIdProduct,childIdLocation;
  //Declare vCountryCDE
  Vector vCountryCDE = new Vector();
  //Declare vDeptVector
  Vector vDeptVector = new Vector();
  //Declare vCategoryVector
  Vector vCategoryVector = new Vector();
  //Declare sub CategoryVector
  Vector vSubCatVector = new Vector();

  //Declare vSeqIDVector
  Vector vSeqIDVector = new Vector();

  BJPanel pnlButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJButton btnSearch = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnCancel = new BJButton();

  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  FlowLayout flowLayout4 = new FlowLayout();


  JPanel pnlSub = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlInfo = new JPanel();
  JPanel pnlProductGroup = new JPanel();
  BJPanel pnlItemDetal = new BJPanel(Constant.PANEL_TYPE_DEFAULT,true);
  DefaultTableModel dm = new DefaultTableModel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JComboBox cmbSupplier = new JComboBox();
  BJComboBox cmbColor = new  BJComboBox();
  BJComboBox cmbSize = new BJComboBox();
  JTextField txtItemcode = new JTextField();
  JTextField txtItemName = new JTextField();
  JTextArea txtItemdesc = new JTextArea();
  JTextField txtUnitPrice = new JTextField();
  JToggleButton btnLocation = new JToggleButton();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  TitledBorder titledBorder2;
  Border border3;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

//  JToggleButton btnSupplier = new JToggleButton();
  JLabel jLabel12 = new JLabel();
  JTextField txtUnitCost = new JTextField();
  JLabel jLabel13 = new JLabel();
  JComboBox cmbTransLevel = new JComboBox();
  JLabel jLabel14 = new JLabel();
  JComboBox cmbItemLevel = new JComboBox();
  JLabel jLabel15 = new JLabel();
  BJComboBox cmbUnit = new BJComboBox();


  String root = "Root";
  String rootID = "-1";
  JScrollPane jScrollTreeView = new JScrollPane();
//  DefaultMutableTreeNode top =  new DefaultMutableTreeNode(root);
//  BJTree jTreePrdDetail = new BJTree("BTM_IM_PROD_HIR",getData(),top);
  BNode rootTree ;
  BTreeModel pTreeModel;
  BJTree treProdHier ;

  JCheckBox jCheckAllLocation = new JCheckBox();
  JLabel jLabelArea = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  JToggleButton btnSupplier = new JToggleButton();
  Border border4;
  Border border5;
  GridLayout gridLayout2 = new GridLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextField txtArtNo = new JTextField();
  JLabel jLabel10 = new JLabel();
  public boolean flagSearchItem = false;
  JTextField txtMaterial = new JTextField();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JComboBox cmbPriceRange = new JComboBox();
  JLabel jLabel18 = new JLabel();
  JComboBox cmbStatus = new JComboBox();
  JButton btnColorAtributeSetup = new JButton();
  JButton btnSizeSetup = new JButton();
  JLabel jLabel19 = new JLabel();
  JButton btnGenerateUPC = new JButton();
  JComboBox cboVAT = new JComboBox();
  JLabel jLabel1 = new JLabel();
  JTextField txtPackSize = new JTextField();


  public PanelItemSetup(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
    try {
      this.frmMain = frmMain;
      this.cardLayout = crdLayout;
      this.parent = parent;
      bItemMaster = new btm.business.ItemMasterBusObj();
      DAL = this.frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    registerKeyboardActions();
    rootTree = new BNode(new BNodeDetail("1", "1", "1", Constant.root, Constant.root));
    pTreeModel = new BTreeModel(rootTree);
    treProdHier = new BJTree(pTreeModel);
    border1 = BorderFactory.createLineBorder(Color.lightGray,2);
    titledBorder1 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151)),lang.getString("ProductHierarchy"));
    border2 = BorderFactory.createEmptyBorder();
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),lang.getString("ItemDetail"));
    border3 = new TitledBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption,2),lang.getString("ItemDetail"));
    border4 = BorderFactory.createEmptyBorder();
    border5 = BorderFactory.createEmptyBorder();
//    pnlButton.setPreferredSize(new Dimension(10, 50));
//    pnlButton.setBackground(new Color(121, 152, 198));
    pnlButton.setLayout(flowLayout4);
    this.setLayout(borderLayout1);
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnSearch.setPreferredSize(new Dimension(80, 35));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3<" +
    "/html>");
    btnSearch.addActionListener(new PanelItemSetup_btnSearch_actionAdapter(this));
//    btnCancel.addActionListener(new PanelItemSetup_btnCancel_actionAdapter(this));
//    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnModify.setPreferredSize(new Dimension(80, 35));
//    btnModify.addActionListener(new PanelItemSetup_btnModify_actionAdapter(this));
//    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnAdd.setPreferredSize(new Dimension(80, 35));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setText(lang.getString("Add")+" (F2)");
    btnAdd.addActionListener(new PanelItemSetup_btnAdd_actionAdapter(this));
    btnAdd.addMouseListener(new PanelItemSetup_btnAdd_mouseAdapter(this));
//    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnDelete.setMinimumSize(new Dimension(57, 25));
//    btnDelete.setPreferredSize(new Dimension(80, 35));
//    btnDelete.addActionListener(new PanelItemSetup_btnDelete_actionAdapter(this));
    pnlSub.setLayout(borderLayout2);
    pnlInfo.setPreferredSize(new Dimension(10, 250));
    pnlInfo.setLayout(borderLayout3);
    this.setPreferredSize(new Dimension(10, 150));
    pnlProductGroup.setBorder(titledBorder1);
    pnlProductGroup.setDebugGraphicsOptions(0);
    pnlProductGroup.setPreferredSize(new Dimension(200, 300));
    pnlProductGroup.setLayout(gridLayout2);
    pnlItemDetal.setBackground(new Color(212, 208, 200));
    pnlItemDetal.setBorder(titledBorder2);
    pnlItemDetal.setPreferredSize(new Dimension(600, 300));
    pnlItemDetal.setLayout(null);
    jLabel4.setBackground(Color.lightGray);
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("ItemID")+":");
    jLabel4.setBounds(new Rectangle(17, 42, 85, 22));
    jLabel5.setText(lang.getString("ItemName")+":");
    jLabel5.setBounds(new Rectangle(17, 103, 85, 22));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setText(lang.getString("ItemDesc")+":");
    jLabel6.setBounds(new Rectangle(17, 314, 85, 28));
    jLabel7.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel7.setText(lang.getString("UnitPrice")+":");
    jLabel7.setBounds(new Rectangle(17, 191, 85, 22));
    jLabel8.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel8.setText(lang.getString("SupplierName")+":");
    jLabel8.setBounds(new Rectangle(17, 132, 85, 22));
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel9.setText(lang.getString("Color")+":");
    jLabel9.setBounds(new Rectangle(313, 161, 85, 22));
    jLabel11.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel11.setText(lang.getString("Size")+":");
    jLabel11.setBounds(new Rectangle(313, 191, 85, 22));
    cmbColor.addFocusListener(new PanelItemSetup_cmbColor_focusAdapter(this));
    cmbColor.addActionListener(new PanelItemSetup_cmbColor_actionAdapter(this));
    cmbSize.addFocusListener(new PanelItemSetup_cmbSize_focusAdapter(this));
    cmbSize.addActionListener(new PanelItemSetup_cmbSize_actionAdapter(this));
    txtItemcode.setPreferredSize(new Dimension(26, 21));
    txtItemcode.setToolTipText("");
    txtItemcode.setEditable(false);
    txtItemcode.setText("");
    txtItemcode.setBounds(new Rectangle(120, 42, 173, 22));
        txtItemcode.addKeyListener(new PanelItemSetup_txtItemcode_keyAdapter(this));
    txtItemcode.addFocusListener(new PanelItemSetup_txtItemcode_focusAdapter(this));
        txtItemName.addKeyListener(new PanelItemSetup_txtItemName_keyAdapter(this));
    txtItemName.setToolTipText("");
//    txtItemName.setNextFocusableComponent(cmbSupplier);
    txtItemName.setPreferredSize(new Dimension(26, 21));
    txtItemName.setText("");
    txtItemName.setBounds(new Rectangle(120, 103, 173, 22));
    txtItemName.addFocusListener(new PanelItemSetup_txtItemName_focusAdapter(this));
        txtItemdesc.addKeyListener(new PanelItemSetup_txtItemdesc_keyAdapter(this));
    txtItemdesc.setToolTipText("");
    txtItemdesc.setEditable(true);
    txtItemdesc.setText("");
    txtItemdesc.setLineWrap(true);
    txtItemdesc.setWrapStyleWord(true);
    txtUnitPrice.addKeyListener(new PanelItemSetup_txtUnitPrice_keyAdapter(this));
    txtUnitPrice.setToolTipText("");
//    txtUnitPrice.setNextFocusableComponent(cmbUnit);
    txtUnitPrice.setPreferredSize(new Dimension(26, 21));
    txtUnitPrice.setText("0");
    txtUnitPrice.setBounds(new Rectangle(120, 191, 173, 22));
    txtUnitPrice.addFocusListener(new PanelItemSetup_txtUnitPrice_focusAdapter(this));
//    btnLocation.setNextFocusableComponent(cmbTransLevel);
    btnLocation.setPreferredSize(new Dimension(41, 23));
    btnLocation.setText("...");
    btnLocation.setBounds(new Rectangle(551, 42, 34, 22));
    btnLocation.addActionListener(new PanelItemSetup_btnLocation_actionAdapter(this));
    btnLocation.setEnabled(false);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelItemSetup_btnCancel_actionAdapter(this));
//    btnCancel.setPreferredSize(new Dimension(80, 35));
//    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    btnSupplier.setBounds(new Rectangle(635, 90, 31, 26));
//    btnSupplier.setText("jToggleButton1");
    jLabel12.setText(lang.getString("UnitCost")+":");
    jLabel12.setBounds(new Rectangle(17, 161, 85, 22));
    jLabel12.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUnitCost.setText("0");
    txtUnitCost.setBounds(new Rectangle(120, 161, 173, 22));
    txtUnitCost.addFocusListener(new PanelItemSetup_txtUnitCost_focusAdapter(this));
//    txtUnitCost.setNextFocusableComponent(txtUnitPrice);
    txtUnitCost.setPreferredSize(new Dimension(26, 21));
    txtUnitCost.setToolTipText("");
        txtUnitCost.addKeyListener(new PanelItemSetup_txtUnitCost_keyAdapter(this));
    jLabel13.setText(lang.getString("TransLevel")+":");
    jLabel13.setBounds(new Rectangle(313, 103, 85, 22));
    jLabel13.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    cmbTransLevel.addActionListener(new PanelItemSetup_cmbTransLevel_actionAdapter(this));
    jLabel14.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel14.setText(lang.getString("ItemLevel")+":");
    jLabel14.setBounds(new Rectangle(313, 132, 85, 22));
    jLabel15.setText(lang.getString("Unit")+":");
    jLabel15.setBounds(new Rectangle(16, 280, 85, 22));
    jLabel15.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    treProdHier.addTreeSelectionListener(new PanelItemSetup_jTreePrdDetail_treeSelectionAdapter(this));
    jCheckAllLocation.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    jCheckAllLocation.setNextFocusableComponent(cmbTransLevel);
    jCheckAllLocation.setToolTipText("");
    jCheckAllLocation.setText(lang.getString("AppliedToAllLocation"));
    jCheckAllLocation.setBounds(new Rectangle(313, 73, 208, 22));
    jCheckAllLocation.addMouseListener(new PanelItemSetup_jCheckAllLocation_mouseAdapter(this));
    jCheckAllLocation.setEnabled(false);
    jLabelArea.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabelArea.setPreferredSize(new Dimension(120, 16));
    jLabelArea.setText(lang.getString("AppliedToArea")+":");
    jLabelArea.setBounds(new Rectangle(313, 42, 237, 22));
//    btnLocation1.addMouseListener(new PanelItemSetup_btnLocation1_mouseAdapter(this));
//    btnLocation1.addActionListener(new PanelItemSetup_btnLocation1_actionAdapter(this));
//    btnSupplier.addMouseListener(new PanelItemSetup_btnSupplier_mouseAdapter(this));
//    btnSupplier.addActionListener(new PanelItemSetup_btnSupplier_actionAdapter(this));
    btnSupplier.setOpaque(true);
    btnSupplier.setPreferredSize(new Dimension(10, 2));
    btnSupplier.setText("...");
    btnSupplier.setBounds(new Rectangle(257, 132, 34, 22));
    btnSupplier.addActionListener(new PanelItemSetup_btnSupplier_actionAdapter(this));
    btnSupplier.addMouseListener(new PanelItemSetup_btnSupplier_mouseAdapter(this));
    txtArtNo.setText("");
    txtArtNo.setBounds(new Rectangle(120, 73, 94, 22));
    txtArtNo.addFocusListener(new PanelItemSetup_txtArtNo_focusAdapter(this));
    txtArtNo.addKeyListener(new PanelItemSetup_txtArtNo_keyAdapter(this));
//    txtArtNo.setNextFocusableComponent(txtItemName);
    txtArtNo.setPreferredSize(new Dimension(26, 21));
    txtArtNo.setToolTipText("");
//    txtArtNo.addKeyListener(new PanelItemSetup_txtArtNo_keyAdapter(this));
    jLabel10.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel10.setText(lang.getString("UPC")+":");
    jLabel10.setBounds(new Rectangle(17, 73, 85, 22));
    jScrollPane1.getViewport().setBackground(Color.lightGray);
    jScrollPane1.setBounds(new Rectangle(120, 314, 465, 58));
    jScrollTreeView.getViewport().setBackground(Color.lightGray);
//    cmbSize.setNextFocusableComponent(txtMaterial);
    cmbSize.setBounds(new Rectangle(410, 191, 131, 22));
    cmbSize.addKeyListener(new PanelItemSetup_cmbSize_keyAdapter(this));
    cmbItemLevel.setEnabled(false);
//    cmbItemLevel.setNextFocusableComponent(cmbColor);
    cmbItemLevel.setBounds(new Rectangle(410, 132, 173, 22));
    cmbItemLevel.addActionListener(new PanelItemSetup_cmbItemLevel_actionAdapter(this));
    cmbTransLevel.setEnabled(false);
//    cmbTransLevel.setNextFocusableComponent(cmbItemLevel);
    cmbTransLevel.setBounds(new Rectangle(410, 103, 173, 22));
    cmbTransLevel.addFocusListener(new PanelItemSetup_cmbTransLevel_focusAdapter(this));
//    cmbColor.setNextFocusableComponent(cmbSize);
    cmbColor.setBounds(new Rectangle(410, 161, 131, 22));
    cmbColor.addKeyListener(new PanelItemSetup_cmbColor_keyAdapter(this));
//    cmbUnit.setNextFocusableComponent(cmbStatus);
    cmbUnit.setBounds(new Rectangle(119, 280, 108, 22));
    cmbUnit.addItemListener(new PanelItemSetup_cmbUnit_itemAdapter(this));
    cmbUnit.addKeyListener(new PanelItemSetup_cmbUnit_keyAdapter(this));
    cmbSupplier.setMaximumSize(new Dimension(41, 23));
//    cmbSupplier.setNextFocusableComponent(txtUnitCost);
    cmbSupplier.setOpaque(true);
    cmbSupplier.setPreferredSize(new Dimension(10, 2));
    cmbSupplier.setBounds(new Rectangle(120, 132, 137, 22));
    cmbSupplier.addKeyListener(new PanelItemSetup_cmbSupplier_keyAdapter(this));
    cmbSupplier.addActionListener(new PanelItemSetup_cmbSupplier_actionAdapter(this));

    txtMaterial.setToolTipText("");
//    txtMaterial.setNextFocusableComponent(cmbPriceRange);
    txtMaterial.setPreferredSize(new Dimension(26, 21));
    txtMaterial.setBounds(new Rectangle(410, 220, 173, 22));
    txtMaterial.addKeyListener(new PanelItemSetup_txtMaterial_keyAdapter(this));
    txtMaterial.addFocusListener(new PanelItemSetup_txtMaterial_focusAdapter(this));
    txtMaterial.setText("");
    jLabel16.setBounds(new Rectangle(313, 220, 85, 22));
    jLabel16.setText(lang.getString("Material")+":");
    jLabel16.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel17.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel17.setText(lang.getString("PriceRange")+":");
    jLabel17.setBounds(new Rectangle(313, 249, 85, 22));
    cmbPriceRange.setBounds(new Rectangle(410, 249, 173, 22));
    cmbPriceRange.addKeyListener(new PanelItemSetup_cmbPriceRange_keyAdapter(this));
//    cmbPriceRange.setNextFocusableComponent(txtItemdesc);
    cmbPriceRange.addItem("Low Range");
    cmbPriceRange.addItem("Mid Range");
    cmbPriceRange.addItem("High Range");
    cmbPriceRange.addItem("Top Range");
    jLabel18.setBounds(new Rectangle(15, 249, 85, 22));
    jLabel18.setText(lang.getString("Status")+":");
    jLabel18.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
//    cmbStatus.setNextFocusableComponent(cmbColor);
    cmbStatus.setBounds(new Rectangle(119, 250, 173, 22));
    cmbStatus.addFocusListener(new PanelItemSetup_cmbStatus_focusAdapter(this));
    cmbStatus.addKeyListener(new PanelItemSetup_cmbStatus_keyAdapter(this));
    btnColorAtributeSetup.setBounds(new Rectangle(545, 161, 38, 22));
    btnColorAtributeSetup.setText("jButton1");
    btnColorAtributeSetup.addActionListener(new PanelItemSetup_btnColorAtributeSetup_actionAdapter(this));
    btnSizeSetup.setText("jButton1");
    btnSizeSetup.addActionListener(new PanelItemSetup_btnSizeSetup_actionAdapter(this));
    btnSizeSetup.setBounds(new Rectangle(545, 192, 38, 20));
    treProdHier.addMouseMotionListener(new PanelItemSetup_treProdHier_mouseMotionAdapter(this));
    treProdHier.addKeyListener(new PanelItemSetup_treProdHier_keyAdapter(this));
    jLabel19.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel19.setText(lang.getString("VAT")+":");
    jLabel19.setBounds(new Rectangle(17, 220, 85, 22));
    btnGenerateUPC.setBounds(new Rectangle(220, 73, 72, 23));
    btnGenerateUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnGenerateUPC.setToolTipText(lang.getString("GenerateUPC"));
    btnGenerateUPC.setText(lang.getString("Generate"));
    btnGenerateUPC.addActionListener(new PanelItemSetup_btnGenerateUPC_actionAdapter(this));
    cboVAT.setBackground(SystemColor.inactiveCaptionBorder);
    cboVAT.setEnabled(false);
    cboVAT.setAlignmentX((float) 0.5);
    cboVAT.setEditable(true);
    cboVAT.setBounds(new Rectangle(120, 221, 56, 23));
    cboVAT.addKeyListener(new PanelItemSetup_cboVAT_keyAdapter(this));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setText(lang.getString("PackingSize")+":");
    jLabel1.setBounds(new Rectangle(232, 281, 85, 21));
    txtPackSize.setEditable(true);
    txtPackSize.setText("");
    txtPackSize.setBounds(new Rectangle(323, 280, 260, 21));
    txtPackSize.addKeyListener(new PanelItemSetup_txtPackSize_keyAdapter(this));
    pnlButton.add(btnAdd, null);
    pnlButton.add(btnSearch, null);
    pnlButton.add(btnCancel, null);
    this.add(pnlSub,  BorderLayout.CENTER);
    pnlSub.add(pnlInfo,  BorderLayout.CENTER);
    this.add(pnlButton, BorderLayout.NORTH);
//    pnlProductGroup.add(cmbDepartment, null);
//    pnlProductGroup.add(cmbClass, null);
//    pnlProductGroup.add(cmbSubclass, null);
    pnlInfo.add(pnlItemDetal, BorderLayout.CENTER);

    pnlItemDetal.add(jLabel4, null);
    pnlItemDetal.add(txtItemcode, null);
    pnlItemDetal.add(jCheckAllLocation, null);
    pnlItemDetal.add(jLabel10, null);
    pnlItemDetal.add(txtArtNo, null);
    pnlItemDetal.add(jLabel5, null);
    pnlItemDetal.add(txtItemName, null);
    pnlItemDetal.add(jLabel8, null);
    pnlItemDetal.add(cmbSupplier, null);
    pnlItemDetal.add(btnSupplier, null);
    pnlItemDetal.add(btnLocation, null);
    pnlItemDetal.add(jLabelArea, null);
    pnlItemDetal.add(jLabel6, null);
    pnlItemDetal.add(jLabel14, null);
    pnlItemDetal.add(cmbTransLevel, null);
    pnlItemDetal.add(cmbItemLevel, null);
    pnlItemDetal.add(jLabel13, null);
    pnlItemDetal.add(jLabel9, null);
    pnlItemDetal.add(cmbColor, null);
    pnlItemDetal.add(jLabel12, null);
    pnlItemDetal.add(txtUnitCost, null);
    pnlItemDetal.add(txtUnitPrice, null);
    pnlItemDetal.add(jLabel7, null);
    pnlItemDetal.add(cmbSize, null);
    pnlItemDetal.add(jLabel11, null);
    pnlItemDetal.add(txtMaterial, null);
    pnlItemDetal.add(jLabel16, null);
    pnlItemDetal.add(jScrollPane1, null);
    pnlItemDetal.add(jLabel17, null);
    pnlItemDetal.add(cmbPriceRange, null);
    pnlItemDetal.add(btnColorAtributeSetup, null);
    pnlItemDetal.add(btnSizeSetup, null);
    pnlItemDetal.add(jLabel19, null);
    pnlItemDetal.add(btnGenerateUPC, null);
    pnlItemDetal.add(cboVAT, null);
    pnlItemDetal.add(cmbStatus, null);
    pnlItemDetal.add(jLabel18, null);
    pnlItemDetal.add(cmbUnit, null);
    pnlItemDetal.add(jLabel15, null);
    pnlItemDetal.add(jLabel1, null);
    pnlItemDetal.add(txtPackSize, null);
    pnlInfo.add(pnlProductGroup, BorderLayout.WEST);
    pnlProductGroup.add(jScrollTreeView, null);
    jScrollTreeView.getViewport().add(treProdHier, null);
    jScrollPane1.getViewport().add(txtItemdesc, null);
    setEnableControl(false);
    cmbStatus.addItem("Work Sheet");
    cmbStatus.addItem("Approve");
    cboVAT.addItem("0");
    cboVAT.addItem("5");
    cboVAT.addItem("10");
    cboVAT.setSelectedIndex(0);
  }
  public void applyPermission() {
   EmpRight er = new EmpRight();
   er.initData(DAL, DAL.getEmpID());
   er.setData(DAL.getEmpID(), Constant.SCRS_ITEM_SETUP);
   btnAdd.setEnabled(er.getAdd());
   if(!er.getAdd()){
     ut.showMessage(frmMain, lang.getString("TT002"),
                    lang.getString("MS1085_CanNotUseScreen"));

   }
  }
  //show data
  void showData(){
      //built tree prod hier
      bItemMaster.setDataAccessLayer(frmMain.getDAL());
      bPriceHist.setDataAccessLayer(frmMain.getDAL());
      bUnitCostHist.setDataAccessLayer(frmMain.getDAL());
      bItemLocSupplier.setDataAccessLayer(frmMain.getDAL());
      Vector vPrdHierAll = new Vector();
      vPrdHierAll = treProdHier.initHierAll(DAL, "BTM_IM_PROD_HIR");
      Vector vPrdHierActive = new Vector();
      vPrdHierActive = treProdHier.initHierActive(vPrdHierAll);
      treProdHier.showHierTree(vPrdHierActive, rootTree);
      treProdHier.updateUI();
      this.cmbTransLevel.removeAllItems();
      this.cmbItemLevel.removeAllItems();
      for(int i=1;i<=3;i++){
        this.cmbTransLevel.addItem(String.valueOf(i) );
        this.cmbItemLevel.addItem(String.valueOf(i)) ;
      }
      cmbTransLevel.setSelectedIndex(2);
      cmbItemLevel.setSelectedIndex(2);
      cmbTransLevel.setEnabled(false);
      cmbItemLevel.setEnabled(false);
      cmbColor.setEnabled(false);
      btnColorAtributeSetup.setEnabled(false);
      cmbSize.setEnabled(false);
      btnSizeSetup.setEnabled(false);
      cmbUnit.setData(getUOM());
      cmbUnit.setSelectedData("Cai");
//      this.cmbUnit.removeAllItems();
//      this.cmbUnit.addItem("Cai");
//      this.cmbUnit.addItem("Kg");
//      this.cmbUnit.addItem("Hu");
//      this.cmbUnit.addItem("Binh");
//      this.cmbUnit.addItem("Cay");
//      this.cmbUnit.addItem("Goi");
//      this.cmbUnit.addItem("Vi");
//      this.cmbUnit.addItem("Loc");
//      this.cmbUnit.addItem("Hop");
//      this.cmbUnit.addItem("Tuyp");
//      this.cmbUnit.addItem("Chai");
//      this.cmbUnit.addItem("Lon");
//      this.cmbUnit.addItem("Cuon");
//      this.cmbUnit.addItem("Thung");
//      this.cmbUnit.addItem("Piece");
//      this.cmbUnit.setSelectedIndex(0);
      /***************** BIN DATA *****************/
//      createConnection();
      if (!vSupplier.isEmpty()){
        vSupplier.clear();
      }
      vSupplier=fetchDataIntoVectorSupplier();
      fetchDataIntoComboSupplier(this.cmbSupplier   ,vSupplier);
      addDataIntoComboInMyPanel("");
      this.jCheckAllLocation .setSelected(true);
      dlgLocation = new DialogLocation(this.frmMain,"", true,this.frmMain);
      childIdLocation = dlgLocation.getChildIdLocation();
//      System.out.println("aa "+childIdLocation);
//    this.txtItemcode.setText( String.valueOf( this.bItemMaster .getMaxItemMasterID()) )  ;
      getProductRoot();
  //    resetDataInItemsetupScreen();
      /***************** BIN DATA *****************/
      getData();
  }
  void getData(){
    getCountryCode();
    getDeptVector();
    getCategoryVetor();
    getSubCategoryVetor();
    getSeqIDVector();
  }
  /** get rootId from TABLE <BTM_IM_PROD_HIR>
   * @author 		Hieu.Pham
   * @param 		no
   * @return		String
   */
  public String getProductRoot() {
      String sSQL = null;
      ResultSet rs = null;
      Statement stmt = null;
      try {
//        DAL.setBeginTrans(DAL.getConnection() ) ;
        sSQL = "select PARENT_ID, CHILD_ID, TYPE_ID, RECD_LOAD_DATE,RECD_LAST_UPD_DATE, "+
               "RECD_CURR_FLAG, RECD_CLOSED_DATE, CHILD_NAME,PARENT_NAME, PURCHASE_TYPE_ID "+
               "from BTM_IM_PROD_HIR where PARENT_ID = -1";
//        System.out.println(sSQL);
        stmt = DAL.getConnection().createStatement();
        rs = DAL.executeQueries(sSQL,stmt);
        while(rs.next() ){
          this.childIdProduct =rs.getString("CHILD_ID") ;
          //get first record
          break;
        }
//        DAL.setEndTrans(DAL.getConnection() ) ;

        rs.close();
        stmt.close();
      }catch (Exception e) {
//          DAL.setRollback(DAL.getConnection() ) ;
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(e);
      }

      return childIdLocation;
  }//end method

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

   /** assignPrdHierTypeData method - insert data into vector (GET DATA FRORM TABLE <BTM_IM_ATTR_DTL> )
    * @author 		Hieu.Pham
    * @param 		String ATTR_TYPE_ID
    *                          ATTR_TYPE_ID = "1" => Get data for vector Color
    *                          ATTR_TYPE_ID = "2" => Get data from vector Size
    * @return		vector
    */
   private Vector fetchDataIntoVectorArtt(String ATTR_TYPE_ID){
      //assign for Prod Hier Type
      Statement stmt = null;
      ItemAttribute iItemSetup=new ItemAttribute();
      Vector vListPrdHier=new Vector();
      String sql;

      ResultSet rs = null;
      iItemSetup = null;
      vListPrdHier.removeAllElements();
      sql="select ATTR_DTL_ID, ATTR_DTL_DESC, PROD_GROUP_ID from BTM_IM_ATTR_DTL where ATTR_ID='" + ATTR_TYPE_ID  + "' order by ATTR_DTL_DESC";
//      System.out.println(sql);
      int i=0;
       try {
         stmt = DAL.getConnection().createStatement();
         rs = DAL.executeQueries(sql,stmt);
           iItemSetup=new ItemAttribute("","None","");
           vListPrdHier.add(iItemSetup);
           while (rs.next()) {
             i +=1;
             iItemSetup=new ItemAttribute(rs.getString("ATTR_DTL_ID"),rs .getString("ATTR_DTL_DESC"),rs.getString("PROD_GROUP_ID"));
             vListPrdHier.add(iItemSetup);
           }

           rs.close();
           stmt.close();

      return vListPrdHier;
      }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
         return vListPrdHier;
      }
    }

    /** assignPrdHierTypeData method - insert data into vector (GET DATA FRORM TABLE <BTM_IM_SUPPLIER> )
    * @author 		Hieu.Pham
    * @param 		no
    * @return		vector
    */
   private Vector fetchDataIntoVectorSupplier(){
      //assign for Prod Hier Type
      btm.attr.Supplier sSupplier=new btm.attr.Supplier();
      Vector vListPrdHier=new Vector();
      String sql;

      ResultSet rs = null;
      sSupplier = null;
      vListPrdHier.removeAllElements();
      sql="select SUPP_ID, SUPP_NAME from BTM_IM_SUPPLIER order by SUPP_NAME ASC";
      int i=0;
       try {
         Statement stmt = DAL.getConnection().createStatement();
//         System.out.println(sql);
         rs = DAL.executeQueries(sql,stmt);
           while (rs.next()) {
             i +=1;
             sSupplier=new btm.attr.Supplier(rs.getString("SUPP_ID"),rs .getString("SUPP_NAME"));
             vListPrdHier.add(sSupplier);
           }

           rs.close();
           stmt.close();

      return vListPrdHier;
      }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
         return vListPrdHier;
      }
    }

    /** showDataTypeCombo method - bin data into Type Combobox Color & Size
    * @author 		Hieu.Pham
    * @param 		JComboBox cbo             //Reference to combobox which needed to add infomation
    *                   Vector vListPrdHier       //Hold infomation<Season> for fetching to combobox
    * @return		no
    */
   private void fetchDataIntoComboAttribute(BJComboBox cbo,int attrID, String prodGroupID){

     String sql = "select attr_dtl_id, attr_dtl_desc from btm_im_attr_dtl " +
         " where attr_id = " + attrID + " and prod_group_id ='" + prodGroupID +
         "' order by upper(attr_dtl_desc)";
//     System.out.println("--- "+sql);
     try{
       Statement stmt = null;
       stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       cbo.setData1(DAL.executeScrollQueries(sql,stmt));
       stmt.close();
     }catch(Exception e){
       e.printStackTrace();
     }

   }
   private void setSelectIndexForCombobox(JComboBox cbo, Vector v, String id){
     ItemAttribute iItemAttr=new ItemAttribute();
     int i = 0;
     while(i < v.size()){
       iItemAttr = (ItemAttribute) v.get(i);
       if(iItemAttr.getATTR_DL_ID().equals(id)){
         cbo.setSelectedItem(iItemAttr.getATTR_DL_DESC());
         return;
       }
       i++;
     }
   }

   private void setSelectIndexForCboSupplier(JComboBox cbo, Vector v, String id) {
     Supplier iItemAttr = new Supplier();
     int i = 0;
     while (i < v.size()) {
       iItemAttr = (Supplier) v.get(i);
       if (iItemAttr.getSUPP_ID().equals(id)) {
         cbo.setSelectedItem(iItemAttr.getSUPP_NAME());
         return;
       }
       i++;
     }
   }


   /** showDataTypeCombo method - bin data into Type Combobox Supplier
    * @author 		Hieu.Pham
    * @param 		JComboBox cbo             //Reference to combobox which needed to add infomation
    *                   Vector vListPrdHier       //Hold infomation<Season> for fetching to combobox
    * @return		no
    */
   private void fetchDataIntoComboSupplier(JComboBox cbo,Vector vListPrdHier){
     //assign for Prod Hier Type
     btm.attr.Supplier sSupplier=new btm.attr.Supplier();

     cbo.removeAllItems();
     cbo.addItem("None");
     int i=0;
     while (i < vListPrdHier.size()) {
         sSupplier = (btm.attr.Supplier) vListPrdHier.get(i);
         cbo.addItem(sSupplier.getSUPP_NAME());
         i+= 1;
     }
   }

   /** Add data into my combobox in this screen
    * @author 		Hieu.Pham
    * @param 		no
    * @return		no
    */
   private void addDataIntoComboInMyPanel(String prodGroup){

     //fetching data into vector after that fetching data into combobox
     vColor=this.fetchDataIntoVectorArtt("1") ;//get Color
     this.fetchDataIntoComboAttribute(this.cmbColor ,1, prodGroup);

     //fetching data into vector after that fetching data into combobox
      vSize=this.fetchDataIntoVectorArtt("2") ; //get size
      fetchDataIntoComboAttribute(this.cmbSize  ,2, prodGroup);

     }

  /*
   * CREATE CONNECTION  INTO DATABASE
   * @author 			Hieu.Pham
   *@param  :   no
   *@return :   no
   */
//  private void createConnection(){
//    DAL.getOracleConnect();
//    bItemMaster.setDataAccessLayer(frmMain.getDAL());
//    bPriceHist.setDataAccessLayer(frmMain.getDAL());
//    bItemLocSupplier.setDataAccessLayer(frmMain.getDAL());
//  }

  /*
   * CLOSE CONNECTION  INTO DATABASE
   * @author 			Hieu.Pham
   *@param  :   no
   *@return :   no
   */

  /*
   * SUB FUNCTION CHECK DATA
   * @author 			Hieu.Pham
   *@param  :   no
   *@return :   boolean
   *                    true : input is true
   *                    false: input is false
   */
  private boolean checkDataInScreen(){
    try{
      this.ItemCode = this.txtItemcode.getText().trim();
      if ( (ItemCode.length() <= 0)) {
        JOptionPane.showMessageDialog(this.frmMain,
            lang.getString("MS1087_LenghOfItemCodeGreater0"),
            lang.getString("TT013"), JOptionPane.ERROR_MESSAGE);
        this.txtItemcode.requestFocus(true) ;
        return false;
      }
      else if (ItemCode.length() > 25) {
        JOptionPane.showMessageDialog(this.frmMain,
            lang.getString("MS1115_LenghOfItemCodeNotGreater25Character"),
            lang.getString("TT013"), JOptionPane.ERROR_MESSAGE);
        this.txtItemcode.requestFocus(true) ;
        return false;
      }
      if(cmbStatus.getSelectedIndex() == 1){
        if (txtUnitCost.getText().equalsIgnoreCase("") || txtUnitCost.getText().equalsIgnoreCase("0")
            || txtUnitPrice.getText().equalsIgnoreCase("") || txtUnitPrice.getText().equalsIgnoreCase("0")){
          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1116_ChooseAPPROVEstatusSystemRecommendsYouInputPriceAndCost") );
          if (txtUnitCost.getText().equalsIgnoreCase("") || txtUnitCost.getText().equalsIgnoreCase("0")){
            txtUnitCost.requestFocus(true);
            txtUnitCost.selectAll();
            return false;
          }else if(txtUnitPrice.getText().equalsIgnoreCase("") || txtUnitPrice.getText().equalsIgnoreCase("0")){
            txtUnitPrice.requestFocus(true);
            txtUnitPrice.selectAll();
            return false;
          }
        }
      }else if (cmbStatus.getSelectedIndex() == 0){
        if (!txtUnitCost.getText().equalsIgnoreCase("") && !txtUnitCost.getText().equalsIgnoreCase("0")
            && !txtUnitPrice.getText().equalsIgnoreCase("") && !txtUnitPrice.getText().equalsIgnoreCase("0")){
          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1117_ChoosingWORKSHEETstatusSystemRecommendsYouRemoveUnitCostOrUnitPriceOrBoth"));
          txtUnitCost.requestFocus(true);
          txtUnitCost.selectAll();
          return false;
        }
      }

      this.ArtNo  = this.txtArtNo.getText().trim();

      this.ItemName = this.txtItemName.getText().trim();
      if ( (ItemName.length() <= 0)) {
        JOptionPane.showMessageDialog(this.frmMain,
            lang.getString("MS1118_LenghItemNameNotLowerThan0Character"),
            lang.getString("TT013"), JOptionPane.ERROR_MESSAGE);
        this.txtItemName.requestFocus(true) ;
        return false;
      }
      else if (ItemName.length() > 120) {
        JOptionPane.showMessageDialog(this.frmMain,
            lang.getString("MS1119_LenghItemNameNotGreaterThan120Character"),
            lang.getString("TT013"), JOptionPane.ERROR_MESSAGE);
        this.txtItemName.requestFocus(true) ;
        return false;
      }

      this.ItemDesc = this.txtItemdesc.getText().trim();
      if (ItemDesc.length() > 120) {
        JOptionPane.showMessageDialog(this.frmMain,
            lang.getString("MS1120_LenghItemDescNotGreaterThan120Character"),
            lang.getString("TT013"), JOptionPane.ERROR_MESSAGE);
        this.txtItemdesc.requestFocus(true) ;
        return false;
      }

      if (txtUnitCost.getText().equalsIgnoreCase("")){
        txtUnitCost.setText("0");
      }
      if (txtUnitPrice.getText().equalsIgnoreCase("")){
        txtUnitPrice.setText("0");
      }
      if(!ut.isDoubleString(this.txtUnitCost.getText().trim()) ){
        JOptionPane.showMessageDialog(this.frmMain,
            lang.getString("MS1121_ValueOfItemCostBeDoubleTypeNumber"),
            lang.getString("TT013"), JOptionPane.ERROR_MESSAGE);
        this.txtUnitCost.requestFocus(true) ;
        return false;
      }
      else{
        this.unitCost = Double.parseDouble(this.txtUnitCost.getText().trim());
      }

      if(!ut.isDoubleString(this.txtUnitPrice.getText().trim()) ){
        JOptionPane.showMessageDialog(this.frmMain,
            lang.getString("MS1121_ValueOfItemCostBeDoubleTypeNumber"),
            lang.getString("TT013"), JOptionPane.ERROR_MESSAGE);
        this.txtUnitPrice.requestFocus(true) ;
        return false;
      }
      else{
        this.unitPrice  = Double.parseDouble(this.txtUnitPrice.getText().trim());
      }

      return true;
    }catch(Exception e){
        JOptionPane.showMessageDialog(this.frmMain, e.toString() , lang.getString("TT013"), JOptionPane.ERROR_MESSAGE);
        return false;
    }
  }


  /*
   * SUB FUNCTION INSERT DATA INTO DATABSE
   * @author 			Hieu.Pham
   *@param  :   no
   *@return :   no
   */
   private void addDataInItemsetupScreen(){
    ResultSet rs = null;
    Statement stmt = null;
    String sql;
    if (!checkDataInScreen()){
      return;
    }

    if(bItemMaster.checkItemcodeExisting(this.ItemCode,this.ItemName ) ){
      JOptionPane.showMessageDialog(this.frmMain,
            lang.getString("MS1113_ItemCodeNotExistedInDatabaseCanNotAddItemIntoDatabase"),
            lang.getString("MS1114_DatabaseConflict"), JOptionPane.ERROR_MESSAGE);
        return ;
    }
    SupplierID=((btm.attr.Supplier) vSupplier.get(cmbSupplier.getSelectedIndex()-1 )).getSUPP_ID();
    newPrice=unitPrice;
    standardUom=this.cmbUnit .getSelectedItem().toString()  ;
    transLevel= Long.parseLong(this.cmbTransLevel.getSelectedItem().toString()  )   ;
    itemLevel=Long.parseLong(this.cmbItemLevel.getSelectedItem().toString()  )   ;
    transType=1;
    String material = txtMaterial.getText();
    priceEffDate=java.sql.Date.valueOf((new Utils()).getSystemDate()) ;

    if (this.jCheckAllLocation .isSelected() ){
      childIdLocation = "-1";
    }

    if (!childIdLocation.equalsIgnoreCase("-1")){
      sql = " select c.STORE_ID " +
          " from BTM_IM_ORG_HIR a, " +
          " BTM_IM_ORG_HIR b, " +
          " BTM_IM_STORE   c " +
          " where a.PARENT_ID = b.CHILD_ID " +
          " and   a.CHILD_ID  = c.CHILD_ID " +
          " and   (b.CHILD_ID = '" + childIdLocation +
          "' or a.CHILD_ID = '" + childIdLocation +
          "' or a.PARENT_ID = '" + childIdLocation +
          "' or b.PARENT_ID = '" + childIdLocation + "')" +
          " and c.closed_date = to_date('7777-7-7','yyyy-MM-dd') ";
    }else{
      sql = "select store_id from btm_im_store c where c.closed_date = to_date('7777-7-7','yyyy-MM-dd') ";
    }
//    System.out.println(sql);
    try {
      //Nghi
      String size = cmbSize.getSelectedItem().toString();
      String color = cmbColor.getSelectedItem().toString();
      ColorID = cmbColor.getSelectedData();
      SizeID = cmbSize.getSelectedData();
      VAT=Double.parseDouble( cboVAT.getSelectedItem().toString());
      if (size.equalsIgnoreCase("None")){
        size = "";
        SizeID = "-1";
      }
      if (color.equalsIgnoreCase("None")){
        color = "";
        ColorID = "-1";
      }
//      System.out.println("Color: " + ColorID);
//      System.out.println("Size: " + SizeID);
      //End Nghi

      ItemDesc = ut.putCommaToString(ItemDesc);
      String status = "";
      if (unitCost != 0 && unitPrice != 0){
        status = "A";
      }else{
        status = "W";
      }
      DAL.setBeginTrans(DAL.getConnection());
      updateSEQ();

      //update SEQ if Art_No begin '200'
//      if(txtArtNo.getText().substring(0,3).equalsIgnoreCase("200")){
        updateSEQForHomeItem();
//      }
      bItemMaster.insertItemMaster(ItemCode,ItemName,ItemDesc,ColorID,SizeID, childIdProduct,transLevel,itemLevel,standardUom,txtPackSize.getText().trim(),ArtNo,material,status,cmbPriceRange.getSelectedItem().toString(), frmMain) ;
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sql,stmt);
//      DAL.setBeginTrans(DAL.getConnection() ) ;
      while(rs.next() ){
        bItemLocSupplier.insertItemLocSupplier(ItemCode,SupplierID,rs.getLong("STORE_ID"),unitCost,VAT) ;
        bPriceHist.insertPriceHist(ItemCode,rs.getLong("STORE_ID"),transType,priceEffDate,newPrice,unitCost,newPrice,color,size) ;
        bUnitCostHist.insertUnitCostHist(ItemCode,rs.getLong("STORE_ID"),transType,priceEffDate,newPrice,unitCost,newPrice,color,size) ;
        insertItemLocSOH(ItemCode,rs.getLong("STORE_ID"));
      }


      DAL.setEndTrans(DAL.getConnection());
      getSeqIDVector();
      rs.close();
      stmt.close();
      ut.showMessage(frmMain,lang.getString("TT016"),lang.getString("MS1198_Item") +" '"+ txtItemName.getText() +"' "+ lang.getString("MS1199_SetupSuccessfully") );
      txtItemName.setText("");
    }
    catch (Exception e) {
//        DAL.setRollback(DAL.getConnection() ) ;
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }

  }

  void insertItemLocSOH(String itemId, long storeId) {
    Statement stmt = null;
    String sql = "INSERT INTO BTM_IM_ITEM_LOC_SOH (ITEM_ID, STORE_ID, STOCK_ON_HAND, BACK_ROOM_QTY, FRONT_ROOM_QTY, SOH_UPDATE_DATETIME, LAST_HIST_EXPORT_TIME, LAST_UPDATE_ID) VALUES ( '" +
        itemId + "'," + storeId + ",0,0,0,to_date('"+ut.getSystemDate(1)+"','dd/mm/yyyy'),to_date('"+ut.getSystemDate(1)+"','dd/mm/yyyy'),'" + DAL.getEmpID()+ "' ) ";
//    System.out.println(sql);
    try {
      stmt = DAL.getConnection().createStatement();
      DAL.executeUpdate(sql, stmt);
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  //increase seq by 1 , and insert into BTM_IM_BARCODE_SEQ for next item setup
  private void updateSEQ(){
    Statement stmt = null;
    String category = treProdHier.getChildId();
    String temp = "" + Integer.parseInt(seqID);

    int seqNumber = Integer.parseInt(temp);
    if( seqNumber < 9999 )
      seqNumber ++;
    seqID = "000" + String.valueOf(seqNumber);
    //get last 4 characters of BARCODE_SEQ
    seqID = seqID.substring(seqID.length()-4);

    String sqlStr = "Update BTM_IM_BARCODE_SEQ set BARCODE_SEQ = '" + seqID + "' Where SBCLASS_ID='" + category + "'";
    try{
      stmt = DAL.getConnection().createStatement();
      DAL.executeUpdate(sqlStr,stmt);
      stmt.close();
    }
    catch(Exception ex){}
  }

  //increase seq by 1 , and insert into BTM_IM_BARCODE_SEQ for next item setup
  private void updateSEQForHomeItem(){
    Statement stmt = null;
    int seqNumber = Integer.parseInt(getMaxSEQ());
    String seq_ID = String.valueOf(seqNumber);
    if( seqNumber < 99999 )
      seqNumber ++;
    seq_ID = "0000" + String.valueOf(seqNumber);
    //get last 4 characters of BARCODE_SEQ
    seq_ID = seq_ID.substring(seq_ID.length()-5);
    String sqlStr = "Update BTM_IM_BARCODE_SEQ set BARCODE_SEQ = '" + seq_ID + "' Where SBCLASS_ID='0000'";
    try{
      stmt = DAL.getConnection().createStatement();
      DAL.executeUpdate(sqlStr,stmt);
      stmt.close();
    }
    catch(Exception ex){
     ex.printStackTrace();
    }
  }



  /*
   * SUB FUNCTION DELETE DATA INTO DATABSE
   * @author 			Hieu.Pham
   *@param  :   no
   *@return :   no
   */
//  private void deleteDataInItemsetupScreen(){
//
//    if(!bItemMaster.checkItemcodeExisting(this.ItemCode ) ){
//      JOptionPane.showMessageDialog(this.frmMain,
//            "This Item does not exist in Database => Can not delete this Item ",
//            "Database Conflict", JOptionPane.ERROR_MESSAGE);
//        return ;
//    }
//
//
//    try {
//          DAL.setBeginTrans(DAL.getConnection() ) ;
//          bItemMaster.deleteItemMaster(this.txtArtNo .getText() )  ;
//          DAL.setEndTrans(DAL.getConnection() ) ;
//          resetDataInItemsetupScreen();
//    }
//    catch (Exception e) {
//        DAL.setRollback(DAL.getConnection() ) ;
//        ExceptionHandle eh = new ExceptionHandle();
//        eh.ouputError(e);
//    }
//  }

  /*
   * SUB FUNCTION RESET DATA IN SCREEN
   * @author 			Hieu.Pham
   *@param  :   no
   *@return :   no
   */
  void resetDataInItemsetupScreen(){
    this.txtItemcode.setText("") ;
//    this.txtItemcode.setText( this.bItemMaster .getMaxItemMasterID() )  ;
    this.txtArtNo.setText("") ;
    this.txtItemName .setText("") ;
    this.txtItemdesc.setText("") ;
    this.txtUnitCost.setText("0") ;
    this.txtUnitPrice .setText("0") ;
    this.txtPackSize.setText("");
    this.txtMaterial.setText("");
//    this.jLabelArea.setText("Applied to Area : ");
    if (cmbColor.getSelectedIndex() != -1){
      this.cmbColor.setSelectedIndex(0);
    }
    if (cmbSize.getSelectedIndex() != -1){
      this.cmbSize.setSelectedIndex(0);
    }
    this.cmbTransLevel.setSelectedIndex(2) ;
    this.cmbItemLevel.setSelectedIndex(2) ;
    this.cmbUnit.setSelectedIndex(0) ;
  }

  /*
   * SUB FUNCTION UPDATE VALUE OF COMBO ITEMLEVEL
   * @author 			Hieu.Pham
   *@param  :   String valueofTranslevel
   *@return :   no
   */
  void updateValueofItemlevel(String valueofTranslevel ){
    this.cmbItemLevel.removeAllItems();
    for (int i = 1; i <= Integer.parseInt(valueofTranslevel); i++) {
      this.cmbItemLevel.addItem(String.valueOf(i));
    }

  }

  /*
   * SUB FUNCTION UPDATE INFOMATION IN SCREEN BASE
   * @author 			Hieu.Pham
   *@param  :   String itemCode
   *@return :   no
   */
  void updateValueInScreenBaseOnItemcode(String itemCode ){
    ResultSet rs= null;
    Statement stmt = null;
    boolean itemExist = false;
    int i=0;
    try {
      stmt = DAL.getConnection().createStatement();
      rs = (ResultSet)this.bItemMaster.selectItemMaster(itemCode,stmt);
      while (rs.next()) {
        this.txtItemcode.setText(rs.getString("ITEM_ID"));
        this.txtArtNo.setText(rs.getString("ART_NO"));
        this.txtItemName.setText(rs.getString("ITEM_NAME"));
        this.txtItemdesc.setText(rs.getString("ITEM_DESC"));
        this.cmbUnit.setSelectedItem(rs.getString("STANDARD_UOM"));

        this.childIdProduct = rs.getString("CHILD_ID");
        this.cmbTransLevel.setSelectedItem(rs.getString("TRANS_LEVEL"));
        this.cmbItemLevel.setSelectedItem(rs.getString("ITEM_LEVEL"));
        this.txtUnitCost.setText(rs.getString("UNIT_COST"));
        this.txtUnitPrice.setText(rs.getString("NEW_PRICE"));

        //Vinh.Le
        //set selected index for combobox

        setSelectIndexForCboSupplier(cmbSupplier, vSupplier, rs.getString("SUPP_ID"));
        setSelectIndexForCombobox(cmbColor, vColor, rs.getString("ATTR1_ID"));
        setSelectIndexForCombobox(cmbSize, vSize, rs.getString("ATTR1_ID"));

        this.cmbTransLevel.setEnabled(false);
        this.cmbItemLevel.setEnabled(false);
        itemExist = true;
        setEnableControl(false);
        //end Vinh.Le

      }
//      rs = null;
      rs.close();
      stmt.close();
    }catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }
//    if( itemExist == false){
//      this.cmbTransLevel.setEnabled(true);
//      this.cmbItemLevel.setEnabled(true);
//    }
  }
  //get item from DB through ItemLookupPanel
  void setSelectItem(String itemId){
     txtItemcode.setText(itemId);
     updateValueInScreenBaseOnItemcode(this.txtItemcode.getText() );
  }

//  void btnDelete_actionPerformed(ActionEvent e) {
//    deleteDataInItemsetupScreen();
//  }

  void jTreePrdDetail_valueChanged(TreeSelectionEvent e) {
    BNode currentNode;
    // Get paths of all selected nodes
    if (treProdHier.getSelectionPaths() != null){
//      treProdHier.getParentId();
      TreePath[] paths = treProdHier.getSelectionPaths();
      for (int i = 0; i < paths.length; i++) {
//        System.out.println("_______________________");
//        System.out.println(paths[i].getLastPathComponent().toString());
        currentNode = (BNode) paths[i].getLastPathComponent();
//        System.out.println("ID: " + currentNode.getNodeID());
//        System.out.println("Parent ID: " + currentNode.getParentID());
//        System.out.println("Type ID: " + currentNode.getTypeID());
//        System.out.println("Name: " + currentNode.getName());

        //Vinh.Le

        //get child_id
        this.childIdProduct = currentNode.getNodeID();
        resetDataInItemsetupScreen();
        //Nghi
        if (currentNode.isLeaf()){
          setEnableControl(true);
//          //generate Item_ID
          subClass = currentNode.getNodeID();
          txtItemcode.setText(getItem_ID());
          cmbSupplier.setSelectedIndex(0);
          cboVAT.setSelectedIndex(0);
        }else{
          txtItemcode.setText("");
          setEnableControl(false);
        }

      }
      String prodGroup = treProdHier.getParentId(treProdHier.getParentId());
//      System.out.println("Product Group: " + prodGroup);
      addDataIntoComboInMyPanel(prodGroup);
    }
  }
  public void updateNewData(){
     String prodGroup = treProdHier.getParentId(treProdHier.getParentId());
//     System.out.println("Product Group: " + prodGroup);
     addDataIntoComboInMyPanel(prodGroup);
  }
//get last seq number for evry branch
  private void getSeqIDVector(){
//    stmt = null;
    ResultSet rs = null;
    Statement stmt = null;
    String sql = "select barcode_seq, sbclass_id from btm_im_barcode_seq";
    try{
      if (!vSeqIDVector.isEmpty()){
        vSeqIDVector.clear();
      }
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      while(rs.next()){
        Vector vData = new Vector();
//        System.out.println("Barcode_seq :" + rs.getString("barcode_seq") + ", sbclass_id: " + rs.getString("sbclass_id"));
        vData.addElement(rs.getString("barcode_seq"));
        vData.addElement(rs.getString("sbclass_id"));
        vSeqIDVector.addElement(vData);
      }
      rs.close();
      stmt.close();

    }catch(Exception e){

    }
  }
  //get seq num from seq vector, base on category
  private String getSeq(){
    String category = treProdHier.getChildId();
    String result="";
    for(int i = 0; i<vSeqIDVector.size(); i++){
      Vector vData = (Vector)vSeqIDVector.elementAt(i);
      if (vData.elementAt(1).toString().equalsIgnoreCase(category)){
        result = vData.elementAt(0).toString();
        return result;
      }
    }
    return result;
  }

  //get country code
  private void getCountryCode(){
//    stmt = null;
    ResultSet rs = null;
    Statement stmt = null;
    try{
      if (!vCountryCDE.isEmpty()){
        vCountryCDE.clear();
      }
      String sql = "select cntry_cde, supp_id from btm_im_supplier";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      while (rs.next()){
        Vector vData = new Vector();
        vData.addElement(rs.getString("cntry_cde"));
        vData.addElement(rs.getString("supp_id"));
        vCountryCDE.addElement(vData);
      }

      rs.close();
      stmt.close();

    }catch(Exception e){

    }
  }
//Create item id= Dep(2)+Cat(3)+SubCat(4)+Seq (4)
  private String getItem_ID(){
    String itemCode = "";
//    String countryCode="";
//    SupplierID=((btm.attr.Supplier) vSupplier.elementAt(cmbSupplier.getSelectedIndex())).getSUPP_ID();

    seqID = getSeq();
    itemCode =  getDept(treProdHier.getParentId(treProdHier.getParentId())) +  getCategory(treProdHier.getParentId()) +getSubCategory(treProdHier.getChildId())+ DAL.getHostID() +seqID;

    return itemCode;
  }
  //get all department from BTM_IM_MAP_DEPARTMENT into vector
  private void getDeptVector(){
//    stmt = null;
    ResultSet rs = null;
    Statement stmt = null;
    try{
      if (!vDeptVector.isEmpty()){
        vDeptVector.clear();
      }
      String sql = "select mapping_no, DEPT_ID from BTM_IM_MAP_DEPARTMENT ";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      while(rs.next()){
        Vector vData = new Vector();
        vData.addElement(rs.getString("mapping_no"));
        vData.addElement(rs.getString("DEPT_ID"));
        vDeptVector.addElement(vData);
      }
      rs.close();
      stmt.close();

    }catch (Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }


  //get all category from btm_im_map_category into vector
  private void getCategoryVetor(){
//    stmt = null;
    ResultSet rs = null;
    Statement stmt = null;
    try {
      if (!vCategoryVector.isEmpty()){
        vCategoryVector.clear();
      }
      String sql = "select mapping_no, category_id from btm_im_map_category";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      while (rs.next()){
        Vector vData = new Vector();
        vData.addElement(rs.getString("mapping_no"));
        vData.addElement(rs.getString("category_id"));
        vCategoryVector.addElement(vData);
      }
      rs.close();
      stmt.close();

    }catch (Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  //get all sub category from BTM_IM_MAP_SUBCATEGORY into vector
  private void getSubCategoryVetor(){
//    stmt = null;
    ResultSet rs = null;
    Statement stmt = null;
    try {
      if (!vSubCatVector.isEmpty()){
        vSubCatVector.clear();
      }
      String sql = "select mapping_no, SUBCATEGORY_ID from BTM_IM_MAP_SUBCATEGORY";
//      System.out.println(sql);
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql,stmt);
      while (rs.next()){
        Vector vData = new Vector();
        vData.addElement(rs.getString("mapping_no"));
        vData.addElement(rs.getString("SUBCATEGORY_ID"));
        vSubCatVector.addElement(vData);
      }
      rs.close();
      stmt.close();

    }catch (Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  //get dept from mapping table
  private String getDept(String dept){
    String result = "";
    for (int i = 0; i<vDeptVector.size();i++){
      Vector vData = (Vector)vDeptVector.elementAt(i);
      if (vData.elementAt(1).toString().equalsIgnoreCase(dept)){
        result = vData.elementAt(0).toString();
        return result;
      }
    }
    return result;
  }
  //get Category from mapping table
 private String getCategory(String category){

   String result = "";
   for (int i = 0; i<vCategoryVector.size();i++){
     Vector vData = (Vector)vCategoryVector.elementAt(i);
     if (vData.elementAt(1).toString().equalsIgnoreCase(category)){
       result = vData.elementAt(0).toString();
       return result;
     }
   }

   return result;
 }

  //get sub category from mapping table
  private String getSubCategory( String subCat){

    String result = "";
    for (int i = 0; i<vSubCatVector.size();i++){
      Vector vData = (Vector)vSubCatVector.elementAt(i);
      if (vData.elementAt(1).toString().equalsIgnoreCase(subCat)){
        result = vData.elementAt(0).toString();
        return result;
      }
    }
    if(result.equals("")) {
//      System.out.println("Error! The mapping table has no data! Pls complete product hierachy.");
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1122_MappingTableNoDataCompleteProductHierachy"));
      setEnableControl(false);
    }

    return result;
  }


  private void setEnableControl(boolean flag){
    txtArtNo.setEditable(flag);
    btnGenerateUPC.setEnabled(flag);
    txtItemdesc.setEditable(flag);
    if(flag==true)
      txtItemdesc.setBackground(Color.WHITE);
    else
      txtItemdesc.setBackground(new Color(212,208,200));
    txtItemName.setEditable(flag);
    txtUnitCost.setEditable(flag);
    txtUnitPrice.setEditable(flag);
    txtPackSize.setEditable(flag);
    cboVAT.setEditable(flag);
    cboVAT.setEnabled(flag);
    cmbSupplier.setEnabled(flag);
    cmbPriceRange.setEnabled(flag);
    cmbStatus.setEnabled(flag);
//    cmbTransLevel.setEnabled(flag);
//    cmbItemLevel.setEnabled(flag);
    cmbUnit.setEnabled(flag);
    cmbColor.setEnabled(flag);
    btnColorAtributeSetup.setEnabled(flag);
    cmbSize.setEnabled(flag);
    btnSizeSetup.setEnabled(flag);
//    jCheckAllLocation.setEnabled(flag);
    btnSupplier.setEnabled(flag);
//    btnLocation.setEnabled(flag);
    btnAdd.setEnabled(flag);
    //Nghi Doan
    txtMaterial.setEditable(flag);
    //End Nghi
    if(flag == true)
      txtArtNo.requestFocus();
  }

  void btnLocation_actionPerformed(ActionEvent e) {
    //    DialogLocation dlgLocation = new DialogLocation(this.frmMain,"", true);
    dlgLocation.flagDialog = Constant.ITEM_SETUP;
    dlgLocation.setTitle(lang.getString("SelectLocation"));
    dlgLocation.setVisible(true) ;
    if (dlgLocation.isOKButton()) {
      childIdLocation = dlgLocation.getChildIdLocation();
      this.jLabelArea.setText(lang.getString("AppliedToArea")+" : " + dlgLocation.getChildNameLocation()  ) ;
      this.jCheckAllLocation.setSelected(false);
    }
//    dlgLocation.dispose() ;
    dlgLocation.setVisible(false) ;
  }

  void jCheckAllLocation_mouseClicked(MouseEvent e) {
    if (this.jCheckAllLocation .isSelected() ){
      childIdLocation = "-1";//(new DialogLocation(this.frmMain,"", true,this.frmMain)).getChildIdLocation();
//      System.out.println(childIdLocation);
      this.jLabelArea.setText(lang.getString("AppliedToArea")+" : ");
    }
  }



  void btnCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.setTitle(lang.getString("TT0001"));
    subClass="";
    resetDataInItemsetupScreen();
    setEnableControl(false);

  }

  void txtItemcode_focusLost(FocusEvent e) {
    updateValueInScreenBaseOnItemcode(this.txtItemcode .getText() );
  }


  void cmbSize_actionPerformed(ActionEvent e) {
//    try{
//      if(!subClass.equals(""))
//        txtItemcode.setText(getItem_ID());
//    }catch(Exception ex){}
  }

  void cmbColor_focusLost(FocusEvent e) {
    if( this.cmbItemLevel.getSelectedItem().toString().equals("2")  ){
      if (cmbSize.getSelectedIndex() != -1){
        this.cmbSize.setSelectedIndex(0);
      }
    }
  }

  void cmbSize_focusLost(FocusEvent e) {
    if( this.cmbItemLevel.getSelectedItem().toString().equals("2")  ){
      if (cmbColor.getSelectedIndex() != -1){
        this.cmbColor.setSelectedIndex(0);
      }
    }
  }

  void btnSearch_actionPerformed(ActionEvent e) {

    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0005"), true, frmMain,frmMain.itemBusObject);
    dlgItemLookup.setVisible(true);
    //IF SELECT ITEM => goto Modify screen
    if (dlgItemLookup.done){
      frmMain.pnlItemModify.showData(dlgItemLookup.getItemID());
//      frmMain.pnlItemModify.txtCategory.setText(dlgItemLookup.getSubCategoryName());
      frmMain.showScreen(Constant.SCRS_ITEM_MODIFY);
      frmMain.pnlItemModify.applyPermission();
    }

  }

  void btnSupplier_actionPerformed(ActionEvent e) {
    SupplierBusObj suppBusObj = new SupplierBusObj();
    DialogSupplierSearch dlgSupplierSearch = new DialogSupplierSearch(frmMain,lang.getString("TT0013"),true,frmMain,suppBusObj);
    dlgSupplierSearch.setVisible(true);
    if (dlgSupplierSearch.done){
//      System.out.println(dlgSupplierSearch.getData());
      cmbSupplier.setSelectedItem(dlgSupplierSearch.getSuppName());
    }
  }

    void txtItemcode_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e,txtItemcode,ItemMaster.LEN_ITEM_ID);
    }

    void txtItemName_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e,txtItemName,ItemMaster.LEN_ITEM_NAME);
        if (e.getKeyChar() == KeyEvent.VK_ENTER){
          cmbSupplier.requestFocus(true);
          return;
        }
    }

    void txtItemdesc_keyTyped(KeyEvent e) {
        ut.limitLenjTextArea(e,txtItemdesc,ItemMaster.LEN_ITEM_DESC);
        if (e.getKeyChar() == KeyEvent.VK_ENTER){
          btnAdd.doClick();
        }else if(e.getKeyChar() == '&'){
          e.consume();
        }
    }

    void txtUnitCost_keyTyped(KeyEvent e) {
      if (e.getKeyChar() == KeyEvent.VK_ENTER){
        txtUnitPrice.requestFocus(true);
        txtUnitPrice.selectAll();
        return;
      }
      ut.limitLenjTextField(e,txtUnitCost,ItemLocSupplier.LEN_UNIT_COST);
      if(e.getKeyChar()!='.'){
        if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
             (e.getKeyChar() != e.VK_BACK_SPACE))
          e.consume();
      }else if(txtUnitCost.getText().indexOf(".")>=0 || txtUnitCost.getText().length()==0)
        e.consume();

    }


    void txtUnitPrice_keyTyped(KeyEvent e) {
      if(e.getKeyChar() == KeyEvent.VK_ENTER){
        cboVAT.requestFocus(true);
        return;
      }
        ut.limitLenjTextField(e,txtUnitPrice,PriceHist.LEN_UNIT_PRICE);
        if (e.getKeyChar() != '.') {
          if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
              (e.getKeyChar() != e.VK_BACK_SPACE))
            e.consume();
        }
        else if (txtUnitPrice.getText().indexOf(".") >= 0 ||
                 txtUnitPrice.getText().length() == 0)
          e.consume();
    }

  void txtArtNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtArtNo,ItemMaster.LEN_ITEM_ARTNO);
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
      txtItemName.requestFocus(true);
      txtItemName.selectAll();
      return;
    }
    if (( txtArtNo.getText().length() == Constant.LENGHT_UPC_INPUT) && (e.getKeyChar() != e.VK_BACK_SPACE) && (txtArtNo.getSelectionStart() == txtArtNo.getSelectionEnd()) ) {
        e.consume();
    }
    if((e.getKeyChar()<'0' || e.getKeyChar() > '9')&& (e.getKeyChar() != e.VK_BACK_SPACE))
              e.consume();

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
class KeyAction extends AbstractAction {

    private Integer identifier = null;

    public KeyAction(Integer identifier) {
      this.identifier = identifier;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {

      //  if(flag == true){//4 nut
      if (identifier.intValue() == KeyEvent.VK_F2) {
        btnAdd.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F3) {
        btnSearch.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }
    }
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    //Check UPC
    if (txtArtNo.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1007_UPCIsNotNull"));
      txtArtNo.requestFocus(true);
      txtArtNo.selectAll();
      return;
    }
    if (!bItemMaster.checkItem(txtItemcode.getText(), txtArtNo.getText())) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1106_UPC_Alreadyexisted"));
      txtArtNo.requestFocus(true);
      txtArtNo.selectAll();
      return;
    }
    if (!bItemMaster.checkItem(txtItemcode.getText(), txtArtNo.getText())) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1106_UPC_Alreadyexisted"));
      txtArtNo.requestFocus(true);
      txtArtNo.selectAll();
      return;
    }
    if ( ut.foundSpecialChar(txtItemName.getText())) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1101_ItemNameHasSpecialChar"));
      txtItemName.requestFocus(true);
      return;
    }

    if (txtArtNo.getText().trim().length() < ItemMaster.LEN_ITEM_ARTNO) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1099_LengthOfUPCshould13"));
      txtArtNo.requestFocus(true);
      txtArtNo.selectAll();
      return;
    }
    if(!checkBarCodeStandard(txtArtNo.getText().trim())){
        return;
      }
    //check item name
    if (txtItemName.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1100_ItemNameNotNull"));
      txtItemName.requestFocus(true);
      return;
    }
    //check supplier not "None"
    if(cmbSupplier.getSelectedItem().toString().equalsIgnoreCase("None")){
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1123_SelectSupplierForItem"));
      cmbSupplier.requestFocus(true);
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

    //check unit cost and unit price
    double unit_price = Double.parseDouble(txtUnitPrice.getText());
    double unit_cost = Double.parseDouble(txtUnitCost.getText());
    if (unit_price <= unit_cost && cmbStatus.getSelectedItem().toString().equals("Approve")){
      int choose = ut.showMessage(frmMain, lang.getString("TT001"),
                                  lang.getString("MS1124_UnitPriceLessThanOrEqualUnitCostWantSetupThisItem"), 3, DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.NO_VALUE) {
        txtUnitPrice.requestFocus(true);
        txtUnitPrice.selectAll();
        return;
      }
    }
    if (unit_price >= 2*unit_cost && cmbStatus.getSelectedItem().toString().equals("Approve")){
      int choose = ut.showMessage(frmMain, lang.getString("TT001"),
                                  lang.getString("MS1125_UnitPriceGreaterThanOrEqual2xUnitCostWantSetupThisItem"), 3, DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.NO_VALUE) {
        txtUnitPrice.requestFocus(true);
        txtUnitPrice.selectAll();
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

    //Insert into DB
    addDataInItemsetupScreen();

    txtItemcode.setText(getItem_ID());
    txtArtNo.setText(getNewBarCode());
    txtItemName.requestFocus(true);

    txtArtNo.selectAll();

  }
//  boolean checkItem(){
//    String colorID = "";
//    String sizeID = "";
//    String sql = "";
//    if (!txtArtNo.getText().equalsIgnoreCase("")){
//      if (cmbColor.getSelectedIndex() != 0) {
//
//        colorID = cmbColor.getSelectedData();
//      }else{
//        colorID = "-1";
//      }
//      if (cmbSize.getSelectedIndex() != 0){
////        sizeID = ( (ItemAttribute) vSize.get( (cmbSize.getSelectedIndex() == -1 ?
////                                               0 : cmbSize.getSelectedIndex()))).
////            getATTR_DL_ID();
//        sizeID = cmbSize.getSelectedData();
//      }else{
//        sizeID = "-1";
//      }
//
//      if (!sizeID.equalsIgnoreCase("")){
//        if (sql.equalsIgnoreCase("")){
//          sql = " and attr2_dtl_id ='" + sizeID + "'" ;
//        }else {
//          sql = sql + " and attr2_dtl_id ='" + sizeID + "'" ;
//        }
//      }
//      if (!sql.equalsIgnoreCase("")){
//        sql = "select * from btm_im_item_master where status <> 'D' and art_no ='" + txtArtNo.getText().trim() +
//            "' " + sql;
//        System.out.println(sql);
//        try{
//          ResultSet rs = null;
//          Statement stmt = null;
//          stmt = DAL.getConnection().createStatement();
//          rs = DAL.executeQueries(sql,stmt);
//          if (rs.next()){
//            stmt.close();
//
//            return false;
//          }
//          stmt.close();
//        }catch(Exception e){
//          e.printStackTrace();
//        }
//      }
//    }
//
//    return true;
//  }

//  void btnModify_actionPerformed(ActionEvent e) {
//     modifyDataInItemsetupScreen();
//  }

  void cmbItemLevel_actionPerformed(ActionEvent e) {
    if( this.cmbItemLevel.getSelectedItem().toString().equals("1")  ){
      this.cmbColor .setEnabled(false) ;
      btnColorAtributeSetup.setEnabled(false);
      this.cmbSize .setEnabled(false) ;
      btnSizeSetup.setEnabled(false);
    }
    else{
      if(flagSearchItem==false){
        this.cmbColor.setEnabled(true);
        btnColorAtributeSetup.setEnabled(true);
        this.cmbSize.setEnabled(true);
        btnSizeSetup.setEnabled(true);
        if (cmbColor.getSelectedIndex() != -1){
          cmbColor.setSelectedIndex(0);
        }
        if (cmbSize.getSelectedIndex() != -1){
          cmbSize.setSelectedIndex(0);
        }
      }else{
        this.cmbColor.setEnabled(false);
        btnColorAtributeSetup.setEnabled(false);
        this.cmbSize.setEnabled(false);
        btnSizeSetup.setEnabled(false);
        flagSearchItem = false;
      }
    }
//    try{
//      if (!subClass.equals(""))
//        txtItemcode.setText(getItem_ID());
//    }
//    catch (Exception ex) {}
  }

  void cmbSupplier_actionPerformed(ActionEvent e) {
    try{
      SupplierID=((btm.attr.Supplier) vSupplier.get(cmbSupplier.getSelectedIndex() )).getSUPP_ID();
      if (!subClass.equals(""))
        txtItemcode.setText(getItem_ID());
    }
    catch(Exception ex){}
  }

  void txtMaterial_focusGained(FocusEvent e) {
    if (!txtMaterial.getText().equalsIgnoreCase("")){
      txtMaterial.selectAll();
    }
  }

  void cmbSupplier_keyTyped(KeyEvent e) {
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
      txtUnitCost.requestFocus(true);
      txtUnitCost.selectAll();
      return;
    }else{
      cmbSupplier.showPopup();
    }
  }

  void cmbUnit_keyTyped(KeyEvent e) {
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
      cmbStatus.requestFocus(true);
      return;
    }else{
      cmbUnit.showPopup();
    }
  }

  void cmbColor_keyTyped(KeyEvent e) {
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
      cmbSize.requestFocus(true);
      return;
    }else{
      cmbColor.showPopup();
    }
  }

  void cmbSize_keyTyped(KeyEvent e) {
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
      txtMaterial.requestFocus(true);
      txtMaterial.selectAll();
      return;
    }else{
      cmbSize.showPopup();
    }
  }

  void txtMaterial_keyTyped(KeyEvent e) {
    if(e.getKeyChar() == KeyEvent.VK_ENTER){
      cmbPriceRange.requestFocus(true);
    }
  }

  void txtArtNo_focusLost(FocusEvent e) {
    txtArtNo.select(0,0);
  }
  void btnSizeSetup_actionPerformed(ActionEvent e) {
    DialogAttributeSetup dlgAttributeSetup = new DialogAttributeSetup(
        frmMain,
        lang.getString("TT0079"), true, frmMain);
    dlgAttributeSetup.showdata();
    dlgAttributeSetup.txtColorSize.requestFocus(true);
    dlgAttributeSetup.cboAttr.setSelectedItem("SIZE");
    dlgAttributeSetup.setLocation(112, 85);
    dlgAttributeSetup.setVisible(true);
  }
  void txtItemName_focusLost(FocusEvent e) {
    txtItemName.select(0,0);
  }

  void txtUnitCost_focusLost(FocusEvent e) {
    txtUnitCost.select(0,0);
    if (!txtUnitCost.getText().equalsIgnoreCase("") && !txtUnitCost.getText().equalsIgnoreCase("0")
        && !txtUnitPrice.getText().equalsIgnoreCase("") && !txtUnitPrice.getText().equalsIgnoreCase("0")){
      cmbStatus.setSelectedIndex(1);
    }else{
      cmbStatus.setSelectedIndex(0);
    }
    if (txtUnitCost.getText().equalsIgnoreCase("")){
      txtUnitCost.setText("0");
    }
  }

  void txtUnitPrice_focusLost(FocusEvent e) {
    txtUnitPrice.select(0,0);
    if (!txtUnitCost.getText().equalsIgnoreCase("") && !txtUnitCost.getText().equalsIgnoreCase("0")
        && !txtUnitPrice.getText().equalsIgnoreCase("") && !txtUnitPrice.getText().equalsIgnoreCase("0")){
      cmbStatus.setSelectedIndex(1);
    }else{
      cmbStatus.setSelectedIndex(0);
    }
    if (txtUnitPrice.getText().equalsIgnoreCase("")){
      txtUnitPrice.setText("0");
    }
  }

  void txtMaterial_focusLost(FocusEvent e) {
    txtMaterial.select(0,0);
  }

  void cmbPriceRange_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      txtItemdesc.requestFocus(true);
      txtItemdesc.selectAll();
      return;
    }else{
      cmbPriceRange.showPopup();
    }
  }

  void cmbStatus_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER){
      cmbColor.requestFocus(true);
      return;
    }else{
      cmbStatus.showPopup();
    }
  }

  void cmbStatus_focusLost(FocusEvent e) {
    if(cmbStatus.getSelectedIndex() == 1){
      if (txtUnitCost.getText().equalsIgnoreCase("") || txtUnitCost.getText().equalsIgnoreCase("0")
          || txtUnitPrice.getText().equalsIgnoreCase("") || txtUnitPrice.getText().equalsIgnoreCase("0")){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1116_ChooseAPPROVEstatusSystemRecommendsYouInputPriceAndCost"));
        if (txtUnitCost.getText().equalsIgnoreCase("") || txtUnitCost.getText().equalsIgnoreCase("0")){
          txtUnitCost.requestFocus(true);
          txtUnitCost.selectAll();
          return;
        }else if(txtUnitPrice.getText().equalsIgnoreCase("") || txtUnitPrice.getText().equalsIgnoreCase("0")){
          txtUnitPrice.requestFocus(true);
          txtUnitPrice.selectAll();
          return;
        }
      }
    }else if (cmbStatus.getSelectedIndex() == 0){
      if (!txtUnitCost.getText().equalsIgnoreCase("") && !txtUnitCost.getText().equalsIgnoreCase("0")
          && !txtUnitPrice.getText().equalsIgnoreCase("") && !txtUnitPrice.getText().equalsIgnoreCase("0")){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1117_ChoosingWORKSHEETstatusSystemRecommendsYouRemoveUnitCostOrUnitPriceOrBoth"));
        txtUnitCost.requestFocus(true);
        txtUnitCost.selectAll();
        return;
      }
    }
  }

  void btnColorAtributeSetup_actionPerformed(ActionEvent e) {
    DialogAttributeSetup dlgAttributeSetup = new DialogAttributeSetup(
        frmMain,
        lang.getString("TT0079"), true, frmMain);
    dlgAttributeSetup.showdata();
    dlgAttributeSetup.txtColorSize.requestFocus(true);
    dlgAttributeSetup.setLocation(112, 85);
    dlgAttributeSetup.setVisible(true);
  }

  void treProdHier_mouseMoved(MouseEvent e) {


  }

  void treProdHier_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnSearch.doClick();
          }
  }

  void cmbColor_actionPerformed(ActionEvent e) {

  }

  void btnGenerateUPC_actionPerformed(ActionEvent e) {
    String upc = txtArtNo.getText().trim();
    if(upc.length() <=7){
      txtArtNo.setText(getNewBarCode());
    }else if(upc.length()==12){
      txtArtNo.setText(upc+ut.getCheckDigitEAN13(upc));
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

  String getNewBarCode(){

//    System.out.println(treProdHier.getNodeName());
    String strSql = "Select BARCODE_SEQ From BTM_IM_BARCODE_SEQ Where SBCLASS_ID='0000'";
    String barCode = "";
    ResultSet rs = null;
    Statement stmt = null;
    String hostID =  DAL.getHostID();//2 digits
    if(hostID.length()>1){//get only 1 digit for host
      hostID =hostID.substring(hostID.length()-1,hostID.length());
    }
    hostID="200"+hostID+getCategory(treProdHier.getParentId());

    //BTMProduct specific UPC:  Men= 20018..., Women=20017..., Unisex=20020..
    if(treProdHier.getNodeName().equalsIgnoreCase("Men")){
      hostID="8"+getDept(treProdHier.getParentId(treProdHier.getParentId()))+getCategory(treProdHier.getParentId())+"0";
    }else if(treProdHier.getNodeName().equalsIgnoreCase("Women")){
      hostID="7"+getDept(treProdHier.getParentId(treProdHier.getParentId()))+getCategory(treProdHier.getParentId())+"0";
    }else if(treProdHier.getNodeName().equalsIgnoreCase("Unisex")){
      hostID="0"+getDept(treProdHier.getParentId(treProdHier.getParentId()))+getCategory(treProdHier.getParentId())+"0";
    }
    try {
       stmt = DAL.getConnection().createStatement();
       rs = DAL.executeQueries(strSql,stmt);
       if(rs.next()){
         barCode = hostID + rs.getString("BARCODE_SEQ");

       }
       rs.close();
       stmt.close();
     }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
     }

     //Bambo specific : input 4-7 digits=> auto generate upc
     String upc=txtArtNo.getText();
     if(upc.length()==4){
       barCode="20000000"+upc;
     }

    barCode += ut.getCheckDigitEAN13(barCode);

    return barCode;
  }
  String getBarCodeStandard(String upc){
    //add Zero digits to fit 8 or 12 digits
    String barCode = "00000000000" + upc;
    barCode = barCode.substring(barCode.length()-13);
//    barCode += String.valueOf(ut.getCheckDigitEAN13(barCode));
    return barCode;
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

  void cboVAT_keyPressed(KeyEvent e) {
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

class PanelItemSetup_btnAdd_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_btnAdd_mouseAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
}

class PanelItemSetup_btnModify_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_btnModify_mouseAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
}

//class PanelItemSetup_btnDelete_actionAdapter implements java.awt.event.ActionListener {
//  PanelItemSetup adaptee;
//
//  PanelItemSetup_btnDelete_actionAdapter(PanelItemSetup adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnDelete_actionPerformed(e);
//  }
//}

class PanelItemSetup_jTreePrdDetail_treeSelectionAdapter implements javax.swing.event.TreeSelectionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_jTreePrdDetail_treeSelectionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(TreeSelectionEvent e) {
    adaptee.jTreePrdDetail_valueChanged(e);
  }
}

class PanelItemSetup_btnLocation_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_btnLocation_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnLocation_actionPerformed(e);
  }
}

class PanelItemSetup_jCheckAllLocation_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_jCheckAllLocation_mouseAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckAllLocation_mouseClicked(e);
  }
}


//class PanelItemSetup_cmbTransLevel_actionAdapter implements java.awt.event.ActionListener {
//  PanelItemSetup adaptee;
//
//  PanelItemSetup_cmbTransLevel_actionAdapter(PanelItemSetup adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.cmbTransLevel_actionPerformed(e);
//  }
//}
//
class PanelItemSetup_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_btnCancel_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelItemSetup_txtItemcode_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_txtItemcode_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemcode_focusLost(e);
  }
}

class PanelItemSetup_cmbColor_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbColor_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cmbColor_actionPerformed(e);
  }
}

class PanelItemSetup_cmbSize_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbSize_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cmbSize_actionPerformed(e);
  }
}

class PanelItemSetup_cmbColor_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbColor_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.cmbColor_focusLost(e);
  }
}

class PanelItemSetup_cmbSize_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbSize_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.cmbSize_focusLost(e);
  }
}

class PanelItemSetup_btnSupplier_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_btnSupplier_mouseAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
}

class PanelItemSetup_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_btnSearch_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelItemSetup_btnSupplier_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_btnSupplier_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplier_actionPerformed(e);
  }
}

class PanelItemSetup_txtItemcode_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemSetup adaptee;

    PanelItemSetup_txtItemcode_keyAdapter(PanelItemSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtItemcode_keyTyped(e);
    }
}

class PanelItemSetup_txtItemName_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemSetup adaptee;

    PanelItemSetup_txtItemName_keyAdapter(PanelItemSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtItemName_keyTyped(e);
    }
}

class PanelItemSetup_txtItemdesc_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemSetup adaptee;

    PanelItemSetup_txtItemdesc_keyAdapter(PanelItemSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtItemdesc_keyTyped(e);
    }
}

class PanelItemSetup_txtUnitCost_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemSetup adaptee;

    PanelItemSetup_txtUnitCost_keyAdapter(PanelItemSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtUnitCost_keyTyped(e);
    }
}

class PanelItemSetup_txtUnitPrice_keyAdapter extends java.awt.event.KeyAdapter {
    PanelItemSetup adaptee;

    PanelItemSetup_txtUnitPrice_keyAdapter(PanelItemSetup adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtUnitPrice_keyTyped(e);
    }
}

class PanelItemSetup_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_txtArtNo_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelItemSetup_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_btnAdd_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

//class PanelItemSetup_btnModify_actionAdapter implements java.awt.event.ActionListener {
//  PanelItemSetup adaptee;
//
//  PanelItemSetup_btnModify_actionAdapter(PanelItemSetup adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnModify_actionPerformed(e);
//  }
//}

class PanelItemSetup_cmbTransLevel_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbTransLevel_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
}

class PanelItemSetup_cmbItemLevel_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbItemLevel_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cmbItemLevel_actionPerformed(e);
  }
}

class PanelItemSetup_cmbSupplier_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbSupplier_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cmbSupplier_actionPerformed(e);
  }
}

class PanelItemSetup_txtMaterial_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_txtMaterial_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtMaterial_focusGained(e);
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtMaterial_focusLost(e);
  }
}

class PanelItemSetup_cmbSupplier_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbSupplier_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cmbSupplier_keyTyped(e);
  }
}

class PanelItemSetup_cmbUnit_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbUnit_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cmbUnit_keyTyped(e);
  }
}

class PanelItemSetup_cmbColor_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbColor_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cmbColor_keyTyped(e);
  }
}

class PanelItemSetup_cmbSize_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbSize_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cmbSize_keyTyped(e);
  }
}

class PanelItemSetup_txtMaterial_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_txtMaterial_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtMaterial_keyTyped(e);
  }
}

class PanelItemSetup_txtArtNo_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_txtArtNo_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtArtNo_focusLost(e);
  }
}

class PanelItemSetup_txtItemName_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_txtItemName_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemName_focusLost(e);
  }
}

class PanelItemSetup_txtUnitCost_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_txtUnitCost_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtUnitCost_focusLost(e);
  }
}

class PanelItemSetup_txtUnitPrice_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_txtUnitPrice_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtUnitPrice_focusLost(e);
  }
}

class PanelItemSetup_cmbPriceRange_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbPriceRange_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cmbPriceRange_keyTyped(e);
  }
}

class PanelItemSetup_cmbStatus_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbStatus_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cmbStatus_keyTyped(e);
  }
}

class PanelItemSetup_cmbStatus_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbStatus_focusAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.cmbStatus_focusLost(e);
  }
}

class PanelItemSetup_btnColorAtributeSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_btnColorAtributeSetup_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnColorAtributeSetup_actionPerformed(e);
  }
}

class PanelItemSetup_btnSizeSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_btnSizeSetup_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSizeSetup_actionPerformed(e);
  }
}

class PanelItemSetup_treProdHier_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_treProdHier_mouseMotionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseMoved(MouseEvent e) {
    adaptee.treProdHier_mouseMoved(e);
  }
}

class PanelItemSetup_treProdHier_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_treProdHier_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.treProdHier_keyPressed(e);
  }
}

class PanelItemSetup_btnGenerateUPC_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSetup adaptee;

  PanelItemSetup_btnGenerateUPC_actionAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnGenerateUPC_actionPerformed(e);
  }
}

class PanelItemSetup_cboVAT_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_cboVAT_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.cboVAT_keyTyped(e);
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboVAT_keyPressed(e);
  }
}

class PanelItemSetup_cmbUnit_itemAdapter implements java.awt.event.ItemListener {
  PanelItemSetup adaptee;

  PanelItemSetup_cmbUnit_itemAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void itemStateChanged(ItemEvent e) {
    adaptee.cmbUnit_itemStateChanged(e);
  }
}

class PanelItemSetup_txtPackSize_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSetup adaptee;

  PanelItemSetup_txtPackSize_keyAdapter(PanelItemSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtPackSize_keyTyped(e);
  }
}
