package sim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ResourceBundle;
import btm.swing.*;
import common.*;
import java.sql.Date;
import java.awt.BorderLayout;

/**
 * <p>Title: </p>
 * <p>Description: Main</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelMainSim extends JPanel {
  // init constant to set hot key each screen in this panel
  public final int FLAG_SET_HOTKEY_SCR_MAIN = 2001 ;
  public final int FLAG_SET_HOTKEY_SCR_ADMIN = 2002 ;
  public final int FLAG_SET_HOTKEY_SCR_HIER_SETUP = 2003 ;
  public final int FLAG_SET_HOTKEY_SCR_MERCH_MAINT = 2004 ;
  public final int FLAG_SET_HOTKEY_SCR_INV_MANAGE = 2005 ;
  public final int FLAG_SET_HOTKEY_SCR_INV_MANAGE_LOOKUP = 2006 ;
  public final int FLAG_SET_HOTKEY_SCR_INV_MANAGE_TRANS = 2007 ;
  public final int FLAG_SET_HOTKEY_SCR_INV_MANAGE_REC = 2008 ;
  public final int FLAG_SET_HOTKEY_SCR_MERCH_MAINT_PROMO = 2009 ;


  public int flagSetHotkey = FLAG_SET_HOTKEY_SCR_MAIN;

  FrameMainSim frmMain;
  Utils ut = new Utils();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel pnlCenter = new JPanel();
  BJPanel pnlNorth = new BJPanel(Constant.PANEL_TYPE_HEADER);
  ImageIcon logoImage;
  JLabel jLabel1 = new JLabel();

  BorderLayout borderLayout2 = new BorderLayout();
  BJButton btnAdminRoleAccess = new BJButton();
  BJButton btnAdminRole = new BJButton();
  BJButton btnAdminGrantRole = new BJButton();
  BJButton btnAdminEmployee = new BJButton();

  BJButton btnTransfer = new BJButton();
  BJButton btnPurcharOrder = new BJButton();
  BJButton btnTranStore = new BJButton();
  BJButton btnRTV = new BJButton();
  BJButton btnTransDmgGood = new BJButton();
  BJButton btnTransBack = new BJButton();

  BJButton btnReceive = new BJButton();
  BJButton btnRecSupplier = new BJButton();
  BJButton btnRecStore = new BJButton();
  BJButton btnRecDmgGood = new BJButton();
  BJButton btnRecBack = new BJButton();

  BJButton btnAvgCost = new BJButton();
  BJButton btnPriceChange = new BJButton();
  BJButton btnUnitCostChange = new BJButton();
  BJButton btnItemLookup = new BJButton();
  BJButton btnBackLookup = new BJButton();
  BJButton btnLookup = new BJButton();
  BJButton btnSupplierLookup = new BJButton();
  BJButton btnItemSetup = new BJButton();
  BJButton btnItemSeasonSetup = new BJButton();
  BJButton btnPromotion = new BJButton();
  BJButton btnPromotionHead = new BJButton();
  BJButton btnPromotionStore = new BJButton();
  BJButton btnPromotionMixMatch = new BJButton();
  BJButton btnPromotionThresHold = new BJButton();
  BJButton btnPromotionBack = new BJButton();
  BJButton btnSupplierSetup = new BJButton();
  BJButton btnStoreSetup = new BJButton();
  BJButton btnAttributSetup = new BJButton();
  BJButton btnProdGroup = new BJButton();
  BJButton btnCustomerSetup = new BJButton();
  BJButton btnOrgHierTypeSetup = new BJButton();
  BJButton btnOrgHierSetup = new BJButton();
  BJButton btnPrdHierTypeSetup = new BJButton();
  BJButton btnPrdHierSetup = new BJButton();
  BJButton btnMainAdmin = new BJButton();
  BJButton btnAdmin = new BJButton();
  BJButton btnExitPro = new BJButton();
  BJButton btnHierSetup = new BJButton();
  BJButton btnMerMain = new BJButton();
  BJButton btnInvMng = new BJButton();
  BJButton btnInvReceipt = new BJButton();
  BJButton btnSOH = new BJButton();
  BJButton btnSeasonSetup = new BJButton();
  BJButton btnExchangeRate = new BJButton();
  BJButton btnPickList = new BJButton();
  BJButton btnRunBatchJobs = new BJButton();
//  JButton jButton1 = new JButton();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelMainSim(FrameMainSim frmMain) {
    try {
      this.frmMain= frmMain;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    logoImage=new ImageIcon("./images/SimMain2.jpg");
    jLabel1.setText("jLabel1");
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setIcon(logoImage);
    pnlCenter.setLayout(borderLayout2);
    btnAdminRoleAccess.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRoleAccess.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdminRoleAccess.setPreferredSize(new Dimension(120, 37));
    btnAdminRoleAccess.setToolTipText(lang.getString("RoleAccess")+"(F4)");
    btnAdminRoleAccess.setText("<html><center><b>"+lang.getString("RoleAccess")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnAdminRoleAccess.addActionListener(new PanelMainSim_btnAdminRoleAccess_actionAdapter(this));
    btnAdminRole.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminRole.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdminRole.setPreferredSize(new Dimension(120, 37));
    btnAdminRole.setToolTipText(lang.getString("Role")+"(F3)");
    btnAdminRole.setText("<html><center><b>"+lang.getString("Role")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnAdminRole.addActionListener(new PanelMainSim_btnAdminRole_actionAdapter(this));
    btnAdminGrantRole.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminGrantRole.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdminGrantRole.setPreferredSize(new Dimension(120, 37));
    btnAdminGrantRole.setToolTipText(lang.getString("EmployeeRole")+"(F2)");
    btnAdminGrantRole.setText("<html><center><b>"+lang.getString("EmployeeRole")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdminGrantRole.addActionListener(new PanelMainSim_btnAdminGrantRole_actionAdapter(this));
    btnAdminEmployee.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdminEmployee.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdminEmployee.setPreferredSize(new Dimension(120, 37));
    btnAdminEmployee.setToolTipText(lang.getString("Employee")+"(F1)");
    btnAdminEmployee.setText("<html><center><b>"+lang.getString("Employee")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnAdminEmployee.addActionListener(new PanelMainSim_btnAdminEmployee_actionAdapter(this));

    pnlNorth.setMinimumSize(new Dimension(2845, 37));

    pnlNorth.setPreferredSize(new Dimension(1423, 85));
//=======
    pnlNorth.setPreferredSize(new Dimension(1423, 45));
    btnTranStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnTranStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnTranStore.setToolTipText(lang.getString("StoreToStore")+"(F1)");
    btnTranStore.setActionCommand("<html><center><b>"+lang.getString("StoreToStore")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "1</html>");
    btnTranStore.setText("<html><center><b>"+lang.getString("StoreToStore")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "1</html>");

    btnTranStore.addActionListener(new PanelMainSim_btnTranStore_actionAdapter(this));
    btnRTV.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnRTV.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRTV.setToolTipText(lang.getString("ReturnToVendor")+"(F1)");
    btnRTV.setText("<html><center><b>"+lang.getString("RTV")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "2</html>");
    btnRTV.addActionListener(new PanelMainSim_btnRTV_actionAdapter(this));
    btnTransfer.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnTransfer.setToolTipText(lang.getString("Transfer")+"(F3)");
    btnTransfer.setActionCommand("<html><center><b>"+lang.getString("Transfer")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "3</html>");
    btnTransfer.setText("<html><center><b>"+lang.getString("Transfer")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "3</html>");
    btnTransfer.addActionListener(new PanelMainSim_btnTransfer_actionAdapter(this));
    btnPurcharOrder.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPurcharOrder.setToolTipText(lang.getString("PurchaseOrder")+" (F8)");
    btnPurcharOrder.setActionCommand("<html><center><b>"+lang.getString("PurchaseOrder")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "8</html>");
    btnPurcharOrder.setText("<html><center><b>"+lang.getString("PurchaseOrder")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "8</html>");
    btnPurcharOrder.addActionListener(new PanelMainSim_btnPurcharOrder_actionAdapter(this));

    btnPriceChange.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPriceChange.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPriceChange.setToolTipText(lang.getString("PriceChange")+"(F2)");
    btnPriceChange.setActionCommand("<html><center><b>"+lang.getString("PriceChange")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;F</html>");
    btnPriceChange.setText("<html><center><b>"+lang.getString("PriceChange")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;F2</html>");
    btnPriceChange.addActionListener(new PanelMainSim_btnPriceChange_actionAdapter(this));

    btnUnitCostChange.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnUnitCostChange.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnUnitCostChange.setToolTipText(lang.getString("UnitCostChange")+"(F10)");
    btnUnitCostChange.setActionCommand("<html><center><b>"+lang.getString("UnitCostChange")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F</html>");
    btnUnitCostChange.setText("<html><center><b>"+lang.getString("UnitCostChange")+"</b></center>&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;F10</html>");
    btnUnitCostChange.addActionListener(new PanelMainSim_btnUnitCostChange_actionAdapter(this));

    btnItemLookup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnItemLookup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnItemLookup.setToolTipText(lang.getString("ItemLookup")+"(F1)");
    btnItemLookup.setText("<html><center><b>"+lang.getString("ItemLookup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;F1</html>");
    btnItemLookup.addActionListener(new PanelMainSim_btnItemLookup_actionAdapter(this));

    btnSupplierLookup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSupplierLookup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSupplierLookup.setToolTipText(lang.getString("SuppLookup")+"(F2)");
    btnSupplierLookup.setText("<html><center><b>"+lang.getString("SuppLookup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;F2</html>");
    btnSupplierLookup.addActionListener(new PanelMainSim_btnSupplierLookup_actionAdapter(this));

    btnItemSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnItemSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnItemSetup.setToolTipText(lang.getString("ItemSetup")+"(F1)");
    btnItemSetup.setText(lang.getString("ItemSetup")+" (F1)");
    btnItemSetup.addActionListener(new PanelMainSim_btnItemSetup_actionAdapter(this));
    btnItemSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnItemSetup.setToolTipText(lang.getString("ItemSetup")+" (F1)");
    btnItemSetup.setText("<html><center><b>"+lang.getString("ItemSetup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";F1</html>");

    btnItemSeasonSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnItemSeasonSetup.setToolTipText(lang.getString("ItemSeaSetup")+"(F3)");
    btnItemSeasonSetup.setText("<html><center><b>"+lang.getString("ItemSeaSetup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;F3</html>");
    btnItemSeasonSetup.addActionListener(new PanelMainSim_btnItemSeasonSetup_actionAdapter(this));

    btnSupplierSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnSupplierSetup.setToolTipText(lang.getString("SupplierSetup")+"(F5)");
    btnSupplierSetup.setText("<html><center><b>"+lang.getString("SupplierSetup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;F5</html>");
    btnSupplierSetup.addActionListener(new PanelMainSim_btnSupplierSetup_actionAdapter(this));

    btnStoreSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnStoreSetup.setToolTipText(lang.getString("StoreSetup")+"(F6)");
    btnStoreSetup.setText("<html><center><b>"+lang.getString("StoreSetup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;F6</html>");
    btnStoreSetup.addActionListener(new PanelMainSim_btnStoreSetup_actionAdapter(this));

    btnRecSupplier.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRecSupplier.setToolTipText(lang.getString("FromSupplier")+"(F1)");
    btnRecSupplier.setActionCommand("<html><center><b>"+lang.getString("FromSupplier")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;F1</html>");
    btnRecSupplier.setText("<html><center><b>"+lang.getString("FromSupplier")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;F1</html>");
    btnRecSupplier.addActionListener(new PanelMainSim_btnRecSupplier_actionAdapter(this));

    btnRecStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRecStore.setToolTipText(lang.getString("FromStore")+"(F2)");
    btnRecStore.setActionCommand("<html><center><b>"+lang.getString("FromStore")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;F2</html>");
    btnRecStore.setText("<html><center><b>"+lang.getString("FromStore")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;F2</html>");
    btnRecStore.addActionListener(new PanelMainSim_btnRecStore_actionAdapter(this));

    btnRecBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnRecBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRecBack.setToolTipText(lang.getString("Back")+"(F4)");
    btnRecBack.setActionCommand("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;F12</html>");
    btnRecBack.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnRecBack.addActionListener(new PanelMainSim_btnRecBack_actionAdapter(this));


    /*set properties for Franchise button*/
    btnProdGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnProdGroup.setToolTipText(lang.getString("ProGroup")+"(F7)");
    btnProdGroup.setActionCommand("<html><center><b>"+lang.getString("ProGroup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "F7</html>");
    btnProdGroup.setText("<html><center><b>"+lang.getString("ProGroup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "F7</html>");
    btnProdGroup.addActionListener(new PanelMainSim_btnProdGroup_actionAdapter(this));
    btnCustomerSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCustomerSetup.setToolTipText(lang.getString("CustomerSetup")+"(F4)");
    btnCustomerSetup.setText("<html><center><b>"+lang.getString("CustomerSetup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;F4</html>");
    btnCustomerSetup.addActionListener(new PanelMainSim_btnCustomerSetup_actionAdapter(this));
    btnOrgHierTypeSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOrgHierTypeSetup.setPreferredSize(new Dimension(150, 37));
    btnOrgHierTypeSetup.setToolTipText(lang.getString("OrgHierarchyType")+"(F3)");
    btnOrgHierTypeSetup.setText("<html><center><b>"+lang.getString("OrgHierarchyType")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnOrgHierTypeSetup.addActionListener(new PanelMainSim_btnOrgHierTypeSetup_actionAdapter(this));
    btnOrgHierSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOrgHierSetup.setPreferredSize(new Dimension(140, 37));
    btnOrgHierSetup.setToolTipText(lang.getString("OrgHierarchy")+"(F4)");
    btnOrgHierSetup.setText("<html><center><b>"+lang.getString("OrgHierarchy")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;F4</html>");
    btnOrgHierSetup.addActionListener(new PanelMainSim_btnOrgHierSetup_actionAdapter(this));
    btnPrdHierTypeSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPrdHierTypeSetup.setPreferredSize(new Dimension(153, 37));
    btnPrdHierTypeSetup.setToolTipText(lang.getString("ProdHierarchyType")+"(F1)");
    btnPrdHierTypeSetup.setActionCommand("hi ");
    btnPrdHierTypeSetup.setText("<html><center><b>"+lang.getString("ProdHierarchyType")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnPrdHierTypeSetup.addActionListener(new PanelMainSim_btnPrdHierTypeSetup_actionAdapter(this));
    btnPrdHierSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnPrdHierSetup.setPreferredSize(new Dimension(140, 37));
    btnPrdHierSetup.setToolTipText(lang.getString("ProdHierarchy")+"(F2)");
    btnPrdHierSetup.setText("<html><center><b>"+lang.getString("ProdHierarchy")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;F2</html>");
    btnPrdHierSetup.addActionListener(new PanelMainSim_btnPrdHierSetup_actionAdapter(this));
    btnMainAdmin.setText("<html><center><b>"+lang.getString("MainMenu")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");

    btnMainAdmin.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnMainAdmin.setToolTipText(lang.getString("MainMenu")+" (F12)");
    btnMainAdmin.setActionCommand("<html><center><b>"+lang.getString("MainMenu")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "F12</html>");
    btnMainAdmin.addActionListener(new PanelMainSim_btnMainAdmin_actionAdapter(this));
    //btnAdmin.setText("Admin");
    btnAdmin.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnAdmin.setToolTipText(lang.getString("Admin")+"(F4)");
    btnAdmin.setText(lang.getString("Admin")+" (F4)");
    btnAdmin.addActionListener(new PanelMainSim_btnAdmin_actionAdapter(this));
    btnExitPro.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnExitPro.setToolTipText(lang.getString("Exit")+"(F10)");
    btnExitPro.setText("<html><center><b>"+lang.getString("Exit")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F10</html>");
    btnExitPro.addActionListener(new PanelMainSim_btnExitPro_actionAdapter(this));
    btnHierSetup.setText(lang.getString("HierachySetup"));
    btnHierSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnHierSetup.setToolTipText(lang.getString("HierachySetup")+"(F1)");
    btnHierSetup.setText("<html><center><b>"+lang.getString("HierachySetup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;F1</html>");
    btnHierSetup.addActionListener(new PanelMainSim_btnHierSetup_actionAdapter(this));
    btnMerMain.setText(lang.getString("MerchMgmt"));
    btnMerMain.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnMerMain.setToolTipText(lang.getString("MerchMgmt")+"(F2)");
    btnMerMain.setText("<html><center><b>"+lang.getString("MerchMgmt")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";F2</html>");
    btnMerMain.addActionListener(new PanelMainSim_btnMerMain_actionAdapter(this));
    btnInvMng.setText(lang.getString("InventoryManagement"));
    btnInvMng.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnInvMng.setToolTipText(lang.getString("InventoryManagement")+"(F3)");
    btnInvMng.setText("<html><center><b>"+lang.getString("InventoryManagement")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3" +
    "</html>");
    btnInvMng.addActionListener(new PanelMainSim_btnInvMng_actionAdapter(this));
    this.setOpaque(true);
    this.setPreferredSize(new Dimension(1423, 80));
    this.addMouseListener(new PanelMainSim_this_mouseAdapter(this));
    btnInvReceipt.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnInvReceipt.setToolTipText(lang.getString("InventoryReceipt")+"(F5)");
    btnInvReceipt.setText(lang.getString("InventoryReceipt")+"(F5)");
    btnInvReceipt.addActionListener(new PanelMainSim_btnInvReceipt_actionAdapter(this));
    btnPickList.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPickList.setToolTipText(lang.getString("PickList")+"(F9)");
    btnPickList.setText("<html><center><b>"+lang.getString("PickList")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;F9</html>");
    btnPickList.addActionListener(new PanelMainSim_btnPickList_actionAdapter(this));
    btnSeasonSetup.addActionListener(new PanelMainSim_btnSeasonSetup_actionAdapter(this));
    btnSeasonSetup.setToolTipText(lang.getString("SeasonSetup")+"(F2)");
    btnSeasonSetup.setText(lang.getString("SeasonSetup")+"(F2)");

    btnAttributSetup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAttributSetup.setToolTipText(lang.getString("AttributeSetup")+"(F7)");
    btnAttributSetup.setText("<html><center><b>"+lang.getString("AttributeSetup")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;F7</html>");
    btnAttributSetup.addActionListener(new PanelMainSim_btnAttributSetup_actionAdapter(this));
    btnRunBatchJobs.setText("<html><center><b>"+lang.getString("InventoryManagement")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3" +
    "</html>");
    btnRunBatchJobs.setToolTipText(lang.getString("RunBatchJobs")+"(F5)");
    btnRunBatchJobs.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnRunBatchJobs.setText("<html><center><b>"+lang.getString("RunBatchJobs")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;F5</html>");
    btnRunBatchJobs.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnRunBatchJobs.addActionListener(new PanelMainSim_btnRunBatchJobs_actionAdapter(this));
    btnAvgCost.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAvgCost.setToolTipText(lang.getString("AvgCost")+"(F6)");
    btnAvgCost.setActionCommand("<html><center><b>"+lang.getString("AvgCost")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "6</html>");
    btnAvgCost.setText("<html><center><b>"+lang.getString("AvgCost")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "6</html>");
    btnAvgCost.addActionListener(new PanelMainSim_btnAvgCost_actionAdapter(this));
    btnRecDmgGood.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnRecDmgGood.setToolTipText(lang.getString("DamageGoods")+" (F3)");
    btnRecDmgGood.setText("<html><center><b>"+lang.getString("DamageGoods")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnRecDmgGood.addActionListener(new PanelMainSim_btnRecDmgGood_actionAdapter(this));
    btnLookup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnLookup.setToolTipText(lang.getString("LookUp")+" (F1)");
    btnLookup.setText(lang.getString("LookUp")+" (F1)");
    btnLookup.addActionListener(new PanelMainSim_btnLookup_actionAdapter(this));
    btnBackLookup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnBackLookup.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnBackLookup.addActionListener(new PanelMainSim_btnBackLookup_actionAdapter(this));
    btnSOH.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSOH.setToolTipText(lang.getString("StockOnHand")+" (F5)");
    btnSOH.setActionCommand("btnSOH");
    btnSOH.setText("<html><center><b>"+lang.getString("StockOnHand")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;F5</html>");
    btnSOH.addActionListener(new PanelMainSim_btnSOH_actionAdapter(this));
//    btnSOH.addActionListener(new PanelMainSim_btnSOH_actionAdapter(this));
//    btnSOH.setEnabled(false);

    btnReceive.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnReceive.setToolTipText(lang.getString("Receive")+" (F4)");
    btnReceive.setActionCommand("<html><center><b>"+lang.getString("Receive")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "4</html>");
    btnReceive.setText("<html><center><b>"+lang.getString("Receive")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
    "4</html>");
    btnReceive.addActionListener(new PanelMainSim_btnReceive_actionAdapter(this));
    btnTransDmgGood.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnTransDmgGood.setToolTipText(lang.getString("DamageGoods")+" (F3)");
    btnTransDmgGood.setActionCommand("<html><center><b>"+lang.getString("DamageGoods")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                                "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
                                "2</html>");
    btnTransDmgGood.setText("<html><center><b>"+lang.getString("DamageGoods")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                       "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
                       "3</html>");
    btnTransDmgGood.addActionListener(new PanelMainSim_btnTransDmgGood_actionAdapter(this));
//    btnTransDmgGood.setEnabled(false);
    btnTransBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

    btnTransBack.setToolTipText(lang.getString("Back")+"(F12)");
    btnTransBack.setActionCommand("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                               "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F" +
                               "12</html>");
    btnTransBack.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnTransBack.addActionListener(new PanelMainSim_btnTransBack_actionAdapter(this));
    btnPromotion.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPromotion.setToolTipText(lang.getString("Promotion")+" (F9)");
    btnPromotion.setText("<html><center><b>"+lang.getString("Promotion")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                      "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                      "nbsp;F9</html>");
    btnPromotion.addActionListener(new PanelMainSim_btnPromotion_actionAdapter(this));
    btnPromotionHead.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPromotionHead.setToolTipText(lang.getString("PromotionHead")+"(F1)");
    btnPromotionHead.setText("<html><center><b>"+lang.getString("PromotionHead")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                      "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                      "nbsp;F1</html>");
    btnPromotionHead.addActionListener(new
    PanelMainSim_btnPromotionHead_actionAdapter(this));
    btnPromotionStore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPromotionStore.setToolTipText(lang.getString("PromotionStore")+"(F2)");
    btnPromotionStore.setText("<html><center><b>"+lang.getString("PromotionStore")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                      "nbsp;F2</html>");
    btnPromotionMixMatch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPromotionMixMatch.setToolTipText(lang.getString("MixMatchPromotion")+"(F3)");
    btnPromotionMixMatch.setPreferredSize(new Dimension(165, 37));
    btnPromotionMixMatch.setText("<html><center><b>"+lang.getString("MixMatchPromotion")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");

    btnPromotionMixMatch.addActionListener(new
        PanelMainSim_btnPromotionMixMatch_actionAdapter(this));
    btnPromotionThresHold.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPromotionThresHold.setToolTipText(lang.getString("ThresholdPromotion")+"(F4)");
    btnPromotionThresHold.setPreferredSize(new Dimension(163, 37));
    btnPromotionThresHold.setText("<html><center><b>"+lang.getString("ThresholdPromotion")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnPromotionThresHold.addActionListener(new
        PanelMainSim_btnPromotionThresHold_actionAdapter(this));
    btnPromotionBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPromotionBack.setToolTipText(lang.getString("Back")+"(F9)");
    btnPromotionBack.setText("<html><center><b>"+lang.getString("Back")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
                      "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                      "nbsp;F9</html>");
    btnPromotionBack.addActionListener(new
        PanelMainSim_btnPromotionBack_actionAdapter(this));
//    jButton1.setText("jButton1");

    this.add(pnlCenter, BorderLayout.CENTER);
    this.add(pnlNorth, BorderLayout.NORTH);


    btnExchangeRate.setText("<html><center><b>"+lang.getString("ExchangRate")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;F8</html>");
    btnExchangeRate.setToolTipText(lang.getString("ExchangRate")+"(F8)");
    btnExchangeRate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnExchangeRate.addActionListener(new PanelMainSim_btnExchangeRate_actionAdapter(this));


    pnlNorth.add(btnHierSetup, null);
    pnlNorth.add(btnMerMain, null);
    pnlNorth.add(btnInvMng, null);
    pnlNorth.add(btnAdmin, null);
    pnlNorth.add(btnExitPro, null);

    pnlNorth.add(btnMainAdmin, null);

    pnlNorth.add(btnPrdHierSetup, null);
    pnlNorth.add(btnPrdHierTypeSetup, null);
    pnlNorth.add(btnOrgHierSetup, null);
    pnlNorth.add(btnOrgHierTypeSetup, null);

    pnlNorth.add(btnItemSetup, null);
    pnlNorth.add(btnSeasonSetup, null);
    pnlNorth.add(btnItemSeasonSetup, null);
    pnlNorth.add(btnCustomerSetup, null);
    pnlNorth.add(btnSupplierSetup, null);
    pnlNorth.add(btnStoreSetup,null);
    pnlNorth.add(btnAttributSetup,null);
    pnlNorth.add(btnExchangeRate,null);
    pnlNorth.add(btnPromotion,null);
    pnlNorth.add(btnRecSupplier,null);
    pnlNorth.add(btnProdGroup,null);

    pnlNorth.add(btnPriceChange, null);
    pnlNorth.add(btnUnitCostChange, null);
    pnlNorth.add(btnTranStore, null);
    pnlNorth.add(btnRTV, null);
    pnlNorth.add(btnAvgCost,null);
    pnlNorth.add(btnLookup,null);
    pnlNorth.add(btnBackLookup,null);
    pnlNorth.add(btnItemLookup, null);
    pnlNorth.add(btnSupplierLookup, null);
    pnlNorth.add(btnInvReceipt, null);
    pnlNorth.add(btnRecDmgGood, null);
//    pnlNorth.add(btnSOH,null);


    pnlNorth.add(btnAdminRole, null);
    pnlNorth.add(btnAdminGrantRole, null);
    pnlNorth.add(btnAdminRoleAccess, null);
    pnlNorth.add(btnRunBatchJobs, null);
    pnlNorth.add(btnAdminEmployee, null); //show Main butons
    pnlCenter.add(jLabel1, BorderLayout.CENTER);
//    pnlCenter.add(jButton1, java.awt.BorderLayout.CENTER);
    showMainButton();

    registerKeyboardActions();
  }
 //show Main buttons
  void showMainButton(){
    this.pnlNorth.setPreferredSize(new Dimension(1423, 45));
    pnlNorth.removeAll();
    pnlNorth.updateUI();
    pnlNorth.add(btnHierSetup, null);
    pnlNorth.add(btnMerMain, null);
    pnlNorth.add(btnInvMng, null);
    pnlNorth.add(btnAdmin, null);
    pnlNorth.add(btnExitPro, null);

    btnHierSetup.requestFocus(true);
    //0000000001,0000000002 : EmpId of Admin
    if(DataAccessLayer.getEmpID().equals("0000000001007")||DataAccessLayer.getEmpID().equals("0000000002004")||DataAccessLayer.getEmpID().equals("0000000003")){
      btnAdmin.setEnabled(true);
    }
    else{
      btnAdmin.setEnabled(false);
    }
  }
  //show Hierachy Setup buttons
  void showHierSetupButton(){
    this.pnlNorth.setPreferredSize(new Dimension(1423, 45));
    pnlNorth.removeAll();
    pnlNorth.updateUI();
    pnlNorth.add(btnPrdHierTypeSetup, null);
    pnlNorth.add(btnPrdHierSetup, null);
    pnlNorth.add(btnOrgHierTypeSetup, null);
    pnlNorth.add(btnOrgHierSetup, null);
    pnlNorth.add(btnMainAdmin, null);

    btnPrdHierTypeSetup.requestFocus(true);
  }
   //show Merchandise System buttons
  void showMerchanSystemButton(){
    this.pnlNorth.setPreferredSize(new Dimension(1423, 90));
    pnlNorth.removeAll();
    pnlNorth.updateUI();
    pnlNorth.add(btnItemSetup, null);
    pnlNorth.add(btnSeasonSetup, null);
    pnlNorth.add(btnItemSeasonSetup, null);
    pnlNorth.add(btnCustomerSetup, null);
    pnlNorth.add(btnSupplierSetup, null);
    pnlNorth.add(btnStoreSetup,null);
    pnlNorth.add(btnAttributSetup,null);
    pnlNorth.add(btnExchangeRate,null);
    pnlNorth.add(btnPromotion,null);
    pnlNorth.add(btnMainAdmin, null);

    btnItemSetup.requestFocus(true);
  }
   //show Promotion buttons
    public void showPromotionButton(){
     this.pnlNorth.setPreferredSize(new Dimension(1423, 45));
     pnlNorth.removeAll();
     pnlNorth.updateUI();
     pnlNorth.add(btnPromotionHead, null);
//     pnlNorth.add(btnPromotionStore);
     pnlNorth.add(btnPromotionMixMatch, null);
     pnlNorth.add(btnPromotionThresHold, null);
     pnlNorth.add(btnPromotionBack, null);

     btnPromotionHead.requestFocus(true);
   }

  //show Inventory Management buttons
  void showInventManageButton(){
    this.pnlNorth.setPreferredSize(new Dimension(1423, 90));
    pnlNorth.removeAll();
    pnlNorth.updateUI();
    pnlNorth.add(btnLookup,null);//1
    pnlNorth.add(btnPriceChange, null);//2
    pnlNorth.add(btnUnitCostChange, null);//2
    pnlNorth.add(btnAvgCost, null);//6
    pnlNorth.add(btnProdGroup, null);//7
    pnlNorth.add(btnTransfer, null);//3
    pnlNorth.add(btnReceive, null);//4
//    pnlNorth.add(btnSOH,null);//5
    pnlNorth.add(btnPickList, null);//7
    pnlNorth.add(btnPurcharOrder, null);//8
    pnlNorth.add(btnMainAdmin, null);//9

    btnLookup.requestFocus(true);
   }
   //show look up information
   void showTransfer(){
     this.pnlNorth.setPreferredSize(new Dimension(1423, 45));
     pnlNorth.removeAll();
     pnlNorth.updateUI();
     pnlNorth.add(btnTranStore, null);
     pnlNorth.add(btnRTV, null);
     pnlNorth.add(btnTransDmgGood, null);
     pnlNorth.add(btnTransBack, null);

     btnTranStore.requestFocus(true);
   }
   //show look up information
   void showReceive(){
     this.pnlNorth.setPreferredSize(new Dimension(1423, 45));
     pnlNorth.removeAll();
     pnlNorth.updateUI();
     pnlNorth.add(btnRecSupplier, null);
     pnlNorth.add(btnRecStore, null);
     pnlNorth.add(btnRecDmgGood,null);
     pnlNorth.add(btnRecBack,null);

     btnRecSupplier.requestFocus(true);
   }

   //show look up information
   void showLookup(){
     this.pnlNorth.setPreferredSize(new Dimension(1423, 45));
     pnlNorth.removeAll();
     pnlNorth.updateUI();
     pnlNorth.add(btnItemLookup, null);
     pnlNorth.add(btnSupplierLookup, null);
     pnlNorth.add(btnBackLookup,null);

//     btnItemLookup.requestFocus(true);
   }
   //show Admin buttons
  void showAdminButton(){
    this.pnlNorth.setPreferredSize(new Dimension(1423, 45));
     pnlNorth.removeAll();
     pnlNorth.updateUI();
     pnlNorth.add(btnAdminEmployee, null);
     pnlNorth.add(btnAdminGrantRole, null);
     pnlNorth.add(btnAdminRole, null);
     pnlNorth.add(btnAdminRoleAccess, null);
     pnlNorth.add(btnRunBatchJobs, null);
     pnlNorth.add(btnMainAdmin, null);

     btnAdminEmployee.requestFocus(true);
  }

  void btnHierSetup_actionPerformed(ActionEvent e) {
    btnMainAdmin.setText("<html><center><b>"+lang.getString("MainMenu")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnMainAdmin.setToolTipText(lang.getString("MainMenu")+"(F12)");
    showHierSetupButton();
    btnPrdHierTypeSetup.requestFocus(true);
    frmMain.setTitle(lang.getString("TT0105"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_HIER_SETUP;
  }

  void btnMerMain_actionPerformed(ActionEvent e) {
    btnMainAdmin.setText("<html><center><b>"+lang.getString("MainMenu")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnMainAdmin.setToolTipText (lang.getString("MainMenu")+"(F12)");
    showMerchanSystemButton();
    btnItemSetup.requestFocus(true);
    btnPrdHierTypeSetup.requestFocus(true);
    frmMain.setTitle(lang.getString("TT0001"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_MERCH_MAINT;
  }

  void btnInvMng_actionPerformed(ActionEvent e) {
    //Run Inventory Management only if ut.SIM_IMP_INV_ITEM_LOC_DCOM is completed
//    Date today = Date.valueOf(ut.getSystemDate());
//    if(!ut.batchjobComplete("ut.SIM_IMP_INV_ITEM_LOC_DCOM", ut.STATUS_COMPLETE,ut.addDate(today,-1),frmMain.getDAL() )){
//      ut.showMessage(frmMain,"Warning", "You must run Sim Batch Job yesterday!");
//      //return;
//    }

    btnMainAdmin.setText("<html><center><b>"+lang.getString("MainMenu")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnMainAdmin.setToolTipText(lang.getString("MainMenu")+"(F12)");
    showInventManageButton();
//without Pick List function for new HOME process
    btnPickList.setEnabled(false);
    //invisiable button AgvCost
    btnAvgCost.setEnabled(false);
    btnLookup.requestFocus(true);
    frmMain.setTitle(lang.getString("TT0002"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGE;
  }

  void btnAdmin_actionPerformed(ActionEvent e) {
    btnMainAdmin.setText("<html><center><b>"+lang.getString("MainMenu")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
"bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");

    btnMainAdmin.setToolTipText(lang.getString("MainMenu")+"(F12)");
    showAdminButton();
    btnAdminEmployee.requestFocus(true);
    frmMain.setTitle(lang.getString("TT0021"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_ADMIN;
  }

  void btnExitPro_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  void btnPrdHierSetup_actionPerformed(ActionEvent e) {
    frmMain.pnlPrdHierSetup.applyPermission();
    frmMain.pnlPrdHierSetup.showData();
    //check Prd Hier Type, if true then run, else not run
    if (!frmMain.pnlPrdHierSetup.checkPrdHierType()) {
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1200_MustInputDataHierType"));
      return;
    }
    frmMain.showScreen(Constant.SCRS_PRD_HIER_SETUP);
  }

  void btnPrdHierTypeSetup_actionPerformed(ActionEvent e) {
    frmMain.pnlPrdHierTypeSetup.applyPermission();
    frmMain.pnlPrdHierTypeSetup.showData();
    if (!frmMain.pnlPrdHierTypeSetup.checkHierTypeSetup()) {
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1128_CanNotInputDataIntoHierType"));
      return;
    }
    frmMain.showScreen(Constant.SCRS_PRD_HIER_TYPE_SETUP);
  }

  void btnOrgHierSetup_actionPerformed(ActionEvent e) {
    frmMain.pnlOrgHierSetup.applyPermission();
    frmMain.pnlOrgHierSetup.showData();
    //check Org Hier Type, if true then run, else not run
    if (!frmMain.pnlOrgHierSetup.checkOrgHierType()) {
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1129_MustInputDataForTheOrgHierType"));
      return;
    }
    frmMain.showScreen(Constant.SCRS_ORG_HIER_SETUP);

  }

  void btnOrgHierTypeSetup_actionPerformed(ActionEvent e) {
    if (!frmMain.pnlOrgHierTypeSetup.checkHierTypeSetup()) {
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1128_CanNotInputDataIntoHierType"));
      return;
    }
    frmMain.pnlOrgHierTypeSetup.applyPermission();
    frmMain.pnlOrgHierTypeSetup.showData();
    frmMain.showScreen(Constant.SCRS_ORG_HIER_TYPE_SETUP);
  }

  void btnItemSetup_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_ITEM_SETUP);
      frmMain.pnlItemSetup.showData();
      frmMain.pnlItemSetup.applyPermission();
  }

  void btnItemSeasonSetup_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_ITEM_SEASON_SETUP);
      frmMain.pnlItemSeasonSetup.applyPermission();
      frmMain.pnlItemSeasonSetup.initScreen();
  }

  void btnCustomerSetup_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_CUSTOMER_SETUP);
      frmMain.pnlCustomerSetup.applyPermission();
      frmMain.pnlCustomerSetup.txtName.requestFocus(true);
  }

  void btnSupplierSetup_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_SUPPLIER_SETUP);
      frmMain.pnlSupplierSetup.applyPermission();
      frmMain.pnlSupplierSetup.initScreen();
  }

  void btnPriceChange_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_PRICE_CHANGE);
//      frmMain.pnlPriceChange.getPriceHist();
      frmMain.pnlPriceChange.applyPermission();
  }

  void btnUnitCostChange_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_UNIT_COST_CHANGE);
//      frmMain.pnlPriceChange.getPriceHist();
      frmMain.pnlUnitCostChange.applyPermission();
  }


  void btnItemLookup_actionPerformed(ActionEvent e) {
      //set panel parent for pnlItemLookup
      frmMain.pnlItemLookup.showData();
      frmMain.pnlItemLookup.setParentScreen(Constant.SCRS_MAIN_SIM);
      frmMain.pnlItemLookup.resetData();
      frmMain.showScreen(Constant.SCRS_ITEM_LOOKUP);
       frmMain.pnlItemLookup.applyPermission();
       frmMain.pnlItemLookup.txtArtNo.requestFocus(true);
  }


  void btnAdminRole_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_ADMIN_ROLE);
      frmMain.pnlAdminRole.applyPermission();
      frmMain.pnlAdminRole.loadNewRoleID();
  }

  void btnAdminGrantRole_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_ADMIN_GRANT_ROLE);
      frmMain.pnlAdminGrantRole.applyPermission();
      frmMain.pnlAdminGrantRole.initScreen();
  }

  void btnAdminRoleAccess_actionPerformed(ActionEvent e) {
    frmMain.pnlAdminRoleAccess.binDataListRole();
    frmMain.pnlAdminRoleAccess.resetCheck();
    frmMain.pnlAdminRoleAccess.showData();
//    frmMain.pnlAdminRoleAccess.jListRole.setSelectedIndex(0);
   frmMain.pnlAdminRoleAccess.tree.setSelectionRow(0);
    frmMain.showScreen(Constant.SCRS_ADMIN_ROLE_ACCESS);
    frmMain.pnlAdminRoleAccess.applyPermission();
     }

  void btnAdminEmployee_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_ADMIN_EMPLOYEE);
      frmMain.pnlAdminEmployee.applyPermission();
      frmMain.pnlAdminEmployee.txtEmployeeCode.requestFocus(true);
      frmMain.pnlAdminEmployee.initScreen();
  }

  void btnMainAdmin_actionPerformed(ActionEvent e) {
      showMainButton();

      frmMain.setTitle(lang.getString("TT0020"));
      flagSetHotkey = FLAG_SET_HOTKEY_SCR_MAIN;
      btnHierSetup.requestFocus(true);
  }

  void btnInvReceipt_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_INVENTORY_RECEIPT);
  }

  void btnStoreSetup_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_STORE_SETUP);
    frmMain.pnlStoreSetup.applyPermission();
    frmMain.pnlStoreSetup.initScreen();
    frmMain.pnlStoreSetup.txtName.requestFocus(true);
  }

  public void registerKeyboardActions() {
    // set up the maps:
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
//      System.out.println("--- " + flagSetHotkey);
      switch (flagSetHotkey) {
        case FLAG_SET_HOTKEY_SCR_MAIN:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnHierSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnMerMain.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnInvMng.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F4) {
            btnAdmin.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F10 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnExitPro.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_ADMIN:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnAdminEmployee.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnAdminGrantRole.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnAdminRole.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F4) {
            btnAdminRoleAccess.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F5) {
            btnRunBatchJobs.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnMainAdmin.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_HIER_SETUP:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnPrdHierTypeSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnPrdHierSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnOrgHierTypeSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F4) {
            btnOrgHierSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnMainAdmin.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_MERCH_MAINT:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnItemSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnSeasonSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnItemSeasonSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F4) {
            btnCustomerSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F5) {
            btnSupplierSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F6) {
            btnStoreSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F7) {
            btnAttributSetup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F8) {
            btnExchangeRate.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F9) {
            btnPromotion.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnMainAdmin.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_INV_MANAGE:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnLookup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnPriceChange.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F10) {
            btnUnitCostChange.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnTransfer.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F4) {
            btnReceive.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F5) {
//            btnSOH.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F6) {
            btnAvgCost.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F7) {
            btnProdGroup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F8) {
            btnPurcharOrder.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F9) {
            btnPickList.doClick();
          }

          else if (identifier.intValue() == KeyEvent.VK_F12 ||
                   identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnMainAdmin.doClick();
          }
          break;

        case FLAG_SET_HOTKEY_SCR_INV_MANAGE_TRANS:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnTranStore.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnRTV.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnTransDmgGood.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12) {
            btnTransBack.doClick();
          }
          break;

        case FLAG_SET_HOTKEY_SCR_INV_MANAGE_REC:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnRecSupplier.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnRecStore.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnRecDmgGood.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12) {
            btnRecBack.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_INV_MANAGE_LOOKUP:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnItemLookup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnSupplierLookup.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12) {
            btnBackLookup.doClick();
          }
          break;
        case FLAG_SET_HOTKEY_SCR_MERCH_MAINT_PROMO:
          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnPromotionHead.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnPromotionStore.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3) {
            btnPromotionMixMatch.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F4) {
            btnPromotionThresHold.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F9) {
            btnPromotionBack.doClick();
          }
          break;


      }

    }
  }

  void btnRecSupplier_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_GOOD_RECEIPT);
    frmMain.pnlGoodsReceipt.applyPermission();
    frmMain.pnlGoodsReceipt.cboSupplier.requestFocus(true);
    frmMain.pnlGoodsReceipt.initScreen();
  }

  void btnPickList_actionPerformed(ActionEvent e) {
    //Run Inventory Management only if ut.SIM_IMP_INV_ITEM_LOC_DCOM is completed
    Date today = Date.valueOf(ut.getSystemDate());
    if(!ut.batchjobSimComplete(ut.SIM_IMP_INV_ITEM_LOC_DCOM, ut.STATUS_COMPLETE,ut.addDate(today,-1),frmMain.getDAL() )){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1000_RunBatchJob"));
      return;
    }

    frmMain.showScreen(Constant.SCRS_PICK_LIST);
    frmMain.pnlPickList.applyPermission();
    frmMain.pnlPickList.initScreen();
  }

  void btnSupplierLookup_actionPerformed(ActionEvent e) {
      DialogSupplierSearch dlgSuppSearch = new DialogSupplierSearch(frmMain,
              lang.getString("TT0013"), true, frmMain, frmMain.suppBusObj);
      dlgSuppSearch.setVisible(true);
      dlgSuppSearch.txtSuppId.requestFocus(true);

//      DialogPromoSearch dlgPromoSearch = new DialogPromoSearch(frmMain,
//              lang.getString("TT014")+" - Promotion Lookup", true, frmMain, frmMain.promoBusObj);
//      dlgPromoSearch.setVisible(true);
//      dlgPromoSearch.txtPromoID.requestFocus(true);
  }

  void btnTranStore_actionPerformed(ActionEvent e) {


    frmMain.showScreen(Constant.SCRS_TRANSFER);
     frmMain.pnlTransfer.applyPermission();
    frmMain.pnlTransfer.setText();
  }

  void btnSeasonSetup_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_SEASON_SETUP);
    frmMain.pnlSeasonSetup.applyPermission();
    frmMain.pnlSeasonSetup.txtSeaName.requestFocus(true);
  }

//phuoc.nguyen
  void btnExchangeRate_actionPerformed(ActionEvent e) {
      frmMain.showScreen(Constant.SCRS_EXCHANGE_RATE);
      frmMain.pnlExchangeRate.applyPermission();
      frmMain.pnlExchangeRate.txtExRate.requestFocus(true);
       }

  void btnAttributSetup_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_ATTRIBUTE_SETUP);
    frmMain.pnlAttributeSetup.applyPermission();
    frmMain.pnlAttributeSetup.showdata();
    frmMain.pnlAttributeSetup.txtColorSize.requestFocus(true);
  }

  void btnProdGroup_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_PROD_GROUP);
    frmMain.pnlProdGroup.initScreen();
//    frmMain.pnlTransferFranchise.txtItemID.requestFocus(true);
  }

  void btnRunBatchJobs_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_ADMIN_RUN_BATCH_JOB);
    frmMain.pnlRunBatchJobsManual.applyPermission();
    frmMain.pnlRunBatchJobsManual.initScreen();
  }

  void btnAvgCost_actionPerformed(ActionEvent e) {
    DialogAvgCost dialogAvgCost = new DialogAvgCost(frmMain,lang.getString("TT014")+" - ",true,frmMain);
//    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    Dimension frameSize = dialogAvgCost.getSize();
//    if (frameSize.height > screenSize.height) {
//      frameSize.height = screenSize.height;
//    }
//    if (frameSize.width > screenSize.width) {
//      frameSize.width = screenSize.width;
//    }
//    dialogAvgCost.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    dialogAvgCost.setVisible(true);

  }

  void btnRecDmgGood_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_RECEIPT_DAMAGE_GOODS);
    frmMain.pnlRecDmgGood.initData();
    frmMain.pnlRecDmgGood.applyPermission();
  }

  void btnLookup_actionPerformed(ActionEvent e) {
    showLookup();
    frmMain.setTitle(lang.getString("TT0106"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGE_LOOKUP;
  }

  void btnBackLookup_actionPerformed(ActionEvent e) {
    showInventManageButton();
    frmMain.setTitle(lang.getString("TT0002"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGE;

  }

  void btnSOH_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_STOCK_ON_HAND);
    frmMain.pnlStockOnHand.initScreen();
//    frmMain.pnlStockOnHand
  }

  void btnReceive_actionPerformed(ActionEvent e) {
    //Run Inventory Management only if ut.SIM_IMP_INV_ITEM_LOC_DCOM is completed
    Date today = Date.valueOf(ut.getSystemDate());
    if(!ut.batchjobSimComplete(ut.SIM_IMP_INV_ITEM_LOC_DCOM, ut.STATUS_COMPLETE,ut.addDate(today,-1),frmMain.getDAL() )){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1000_RunBatchJob"));
      return;
    }

    showReceive();
    btnRecDmgGood.setEnabled(false);  //090307 reject the receipt DM fuction
    frmMain.setTitle(lang.getString("TT0017"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGE_REC;

  }

  void btnTransfer_actionPerformed(ActionEvent e) {
    //Run Inventory Management only if ut.SIM_IMP_INV_ITEM_LOC_DCOM is completed
    Date today = Date.valueOf(ut.getSystemDate());
    if(!ut.batchjobSimComplete(ut.SIM_IMP_INV_ITEM_LOC_DCOM, ut.STATUS_COMPLETE,ut.addDate(today,-1),frmMain.getDAL() )){
      ut.showMessage(frmMain,lang.getString("TT001"), lang.getString("MS1000_RunBatchJob"));
      return;
    }

    showTransfer();
    frmMain.setTitle(lang.getString("TT0107"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGE_TRANS;
    btnTranStore.requestFocus(true);

  }

  void btnRecStore_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_RECEIPT_FROME_STORE);
    frmMain.pnlReceiptFromStore.initData();
    frmMain.pnlReceiptFromStore.applyPermission();
  }

  void btnTransBack_actionPerformed(ActionEvent e) {
    btnMainAdmin.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnMainAdmin.setText("<html><center><b>"+lang.getString("MainMenu")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnMainAdmin.setToolTipText(lang.getString("MainMenu")+"(F12)");
    showInventManageButton();
//    btnItemLookup.requestFocus(true);
    frmMain.setTitle(lang.getString("TT0002"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGE;

  }

  void btnRecBack_actionPerformed(ActionEvent e) {
    btnMainAdmin.setText("<html><center><b>"+lang.getString("MainMenu")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnMainAdmin.setToolTipText(lang.getString("MainMenu")+"(F12)");
    showInventManageButton();
//    btnItemLookup.requestFocus(true);
    frmMain.setTitle(lang.getString("TT0002"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_INV_MANAGE;

  }

  void btnTransDmgGood_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_TRANSFER_DAMAGE_GOODS);
    frmMain.pnlTransferDamageGoods.applyPermission();
//    frmMain.PanelTransferDamageGoods.setText();
  }

  public void btnPromotion_actionPerformed(ActionEvent e) {
    showPromotionButton();
    frmMain.setTitle(lang.getString("TT0108"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_MERCH_MAINT_PROMO;
  }

  public void btnPromotionBack_actionPerformed(ActionEvent e) {
    btnMainAdmin.setText("<html><center><b>"+lang.getString("MainMenu")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnMainAdmin.setToolTipText (lang.getString("MainMenu")+"(F12)");
    showMerchanSystemButton();
    btnItemSetup.requestFocus(true);
    btnPrdHierTypeSetup.requestFocus(true);
    frmMain.setTitle(lang.getString("TT0001"));
    flagSetHotkey = FLAG_SET_HOTKEY_SCR_MERCH_MAINT;
  }

  public void btnPromotionHead_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_PROMOTION_HEAD);
    //frmMain.pnlPromotionHead.applyPermission();
    frmMain.pnlPromotionHead.initScreen();
    frmMain.pnlPromotionHead.applyPermission();
  }

  public void btnPromotionMixMatch_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_PROMOTION_MIX_MATCH);
    frmMain.pnlPromotionMixMatch.initScreen();
    frmMain.pnlPromotionMixMatch.applyPermission();
  }

  public void btnPromotionThresHold_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_PROMOTION_THRES_HOLD);
    frmMain.pnlPromoThresholdSetup.initScreen();
    frmMain.pnlPromoThresholdSetup.applyPermission();
  }

  void btnPurcharOrder_actionPerformed(ActionEvent e) {
     frmMain.showScreen(Constant.SCRS_PURCHASE_ORDER);
     frmMain.pnlPurchaseOrder.initScreen();
     frmMain.pnlPurchaseOrder.applyPermission();
  }

  public void btnRTV_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_RTV);
     frmMain.pnlRTV.applyPermission();
    frmMain.pnlRTV.initScreen();

  }

  //  void btnSOH_actionPerformed(ActionEvent e) {
//    frmMain.showScreen(Constant.SCRS_STOCK_COUNT);
//    frmMain.pnlStockCount.initScreen();
//    frmMain.pnlStockCount.cboStoreID.requestFocus(true);
//  }
}

class PanelMainSim_btnRTV_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelMainSim adaptee;
  PanelMainSim_btnRTV_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnRTV_actionPerformed(e);
  }
}

class PanelMainSim_btnPromotionThresHold_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelMainSim adaptee;
  PanelMainSim_btnPromotionThresHold_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPromotionThresHold_actionPerformed(e);
  }
}

class PanelMainSim_btnPromotionMixMatch_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelMainSim adaptee;
  PanelMainSim_btnPromotionMixMatch_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPromotionMixMatch_actionPerformed(e);
  }
}

class PanelMainSim_btnPromotionBack_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelMainSim adaptee;
  PanelMainSim_btnPromotionBack_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPromotionBack_actionPerformed(e);
  }
}

class PanelMainSim_btnPromotionHead_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelMainSim adaptee;
  PanelMainSim_btnPromotionHead_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPromotionHead_actionPerformed(e);
  }
}

class PanelMainSim_btnPromotion_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelMainSim adaptee;
  PanelMainSim_btnPromotion_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPromotion_actionPerformed(e);
  }
}

class PanelMainSim_btnHierSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnHierSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnHierSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnMerMain_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnMerMain_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnMerMain_actionPerformed(e);
  }
}

class PanelMainSim_btnInvMng_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnInvMng_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnInvMng_actionPerformed(e);
  }
}

class PanelMainSim_btnAdmin_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnAdmin_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdmin_actionPerformed(e);
  }
}

class PanelMainSim_btnExitPro_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnExitPro_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnExitPro_actionPerformed(e);
  }
}

class PanelMainSim_btnPrdHierSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnPrdHierSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnPrdHierTypeSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnPrdHierTypeSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierTypeSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnOrgHierSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnOrgHierSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOrgHierSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnOrgHierTypeSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnOrgHierTypeSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOrgHierTypeSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnItemSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnItemSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnItemSetup_actionPerformed(e);
  }
}


class PanelMainSim_btnItemSeasonSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnItemSeasonSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnItemSeasonSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnCustomerSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnCustomerSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCustomerSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnSupplierSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnSupplierSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplierSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnPriceChange_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnPriceChange_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPriceChange_actionPerformed(e);
  }
}

class PanelMainSim_btnUnitCostChange_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnUnitCostChange_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnUnitCostChange_actionPerformed(e);
  }
}
class PanelMainSim_btnTranStore_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnTranStore_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnTranStore_actionPerformed(e);
  }
}

class PanelMainSim_btnItemLookup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnItemLookup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnItemLookup_actionPerformed(e);
  }
}

class PanelMainSim_btnSupplierLookup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnSupplierLookup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSupplierLookup_actionPerformed(e);
  }
}

class PanelMainSim_btnAdminRole_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnAdminRole_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRole_actionPerformed(e);
  }
}

class PanelMainSim_btnAdminGrantRole_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnAdminGrantRole_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminGrantRole_actionPerformed(e);
  }
}

class PanelMainSim_btnAdminRoleAccess_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnAdminRoleAccess_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminRoleAccess_actionPerformed(e);
  }
}

class PanelMainSim_btnAdminEmployee_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnAdminEmployee_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdminEmployee_actionPerformed(e);
  }
}

class PanelMainSim_btnMainAdmin_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnMainAdmin_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnMainAdmin_actionPerformed(e);
  }
}

class PanelMainSim_btnInvReceipt_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnInvReceipt_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnInvReceipt_actionPerformed(e);
  }
}

class PanelMainSim_btnStoreSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnStoreSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnStoreSetup_actionPerformed(e);
  }
}

class PanelMainSim_this_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelMainSim adaptee;

  PanelMainSim_this_mouseAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
}

class PanelMainSim_btnPickList_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnPickList_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPickList_actionPerformed(e);
  }
}

class PanelMainSim_btnRecSupplier_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnRecSupplier_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnRecSupplier_actionPerformed(e);
  }
}

class PanelMainSim_btnSeasonSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnSeasonSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSeasonSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnExchangeRate_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnExchangeRate_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnExchangeRate_actionPerformed(e);
  }
}

class PanelMainSim_btnAttributSetup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnAttributSetup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAttributSetup_actionPerformed(e);
  }
}

class PanelMainSim_btnProdGroup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnProdGroup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnProdGroup_actionPerformed(e);
  }
}

class PanelMainSim_btnRunBatchJobs_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnRunBatchJobs_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnRunBatchJobs_actionPerformed(e);
  }
}

class PanelMainSim_btnAvgCost_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnAvgCost_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAvgCost_actionPerformed(e);
  }
}

class PanelMainSim_btnRecDmgGood_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnRecDmgGood_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnRecDmgGood_actionPerformed(e);
  }
}

class PanelMainSim_btnLookup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnLookup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnLookup_actionPerformed(e);
  }
}

class PanelMainSim_btnBackLookup_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnBackLookup_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBackLookup_actionPerformed(e);
  }
}

//class PanelMainSim_btnSOH_actionAdapter implements java.awt.event.ActionListener {
//  PanelMainSim adaptee;
//
//  PanelMainSim_btnSOH_actionAdapter(PanelMainSim adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void actionPerformed(ActionEvent e) {
//    adaptee.btnSOH_actionPerformed(e);
//  }
//}

class PanelMainSim_btnSOH_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnSOH_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSOH_actionPerformed(e);
  }
}

class PanelMainSim_btnReceive_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnReceive_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceive_actionPerformed(e);
  }
}

class PanelMainSim_btnTransfer_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnTransfer_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnTransfer_actionPerformed(e);
  }
}

class PanelMainSim_btnRecStore_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnRecStore_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnRecStore_actionPerformed(e);
  }
}

class PanelMainSim_btnTransBack_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnTransBack_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnTransBack_actionPerformed(e);
  }
}

class PanelMainSim_btnRecBack_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnRecBack_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnRecBack_actionPerformed(e);
  }
}

class PanelMainSim_btnTransDmgGood_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnTransDmgGood_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnTransDmgGood_actionPerformed(e);
  }
}

class PanelMainSim_btnPurcharOrder_actionAdapter implements java.awt.event.ActionListener {
  PanelMainSim adaptee;

  PanelMainSim_btnPurcharOrder_actionAdapter(PanelMainSim adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPurcharOrder_actionPerformed(e);
  }
}
