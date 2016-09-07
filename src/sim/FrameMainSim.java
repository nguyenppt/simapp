package sim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import common.*;
import java.sql.*;
import btm.swing.*;
import btm.business.*;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Date;
import common.Utils;
import java.util.*;
import btm.attr.EmpRight;
//import sim.FrameLogIn;
/**
 * <p>Title: </p>
 * <p>Description: Main</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */
public class FrameMainSim extends JFrame {
    DataAccessLayer DAL = new DataAccessLayer();
    Utils ut=new Utils();
    Timer timer = new Timer();
    public int frmMainCount = 1;
    EmployeeBusObj employeeBusObject = new EmployeeBusObj();
    CustomerBusObj customerBusObject = new CustomerBusObj();
    StoreBusObj storeBusObj = new StoreBusObj();
    ItemSeasonBusObj iSeasonBusObj = new ItemSeasonBusObj();
    ItemBusObject itemBusObject = new ItemBusObject();
    SupplierBusObj suppBusObj = new SupplierBusObj();
    PromotionBusObj promoBusObj = new PromotionBusObj();
    PurchaseOrderBusObj poBusObj = new PurchaseOrderBusObj();
    ReceiptBusObj receiptBusObj = new ReceiptBusObj();
    PickListBusObj plBusObj = new PickListBusObj();
    ProdGroupBusObj pgBusObj = new ProdGroupBusObj();
    SeasonBusObj seaBusObj = new SeasonBusObj();
    AdminBusObj adminBusObj = new AdminBusObj();
    int flagScreen=0;//determine screen is working on : ItemLookup, ItemSetup,...
    String SeqNo = "";
    JPanel pnlMain;
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel pnlContent = new JPanel();
    ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

    CardLayout cardLayout1 = new CardLayout();
    //1. Hierrachy
    PanelPrdHierSetup pnlPrdHierSetup;
    PanelOrgHierSetup pnlOrgHierSetup;
    PanelPrdHierTypeSetup pnlPrdHierTypeSetup;
    PanelOrgHierTypeSetup pnlOrgHierTypeSetup;
//    PanelPrdHierTypeSearch pnlPrdHierTypeSearch;
//    PanelOrgHierTypeSearch pnlOrgHierTypeSearch;
//    PanelOrgHierSetupModify pnlOrgHierSetupModify;
//    PanelPrdHierSetupModify pnlPrdHierSetupModify;

    //2. Mer Main
    PanelItemSetup pnlItemSetup;
    PanelItemModify pnlItemModify;
    PanelSeasonSetup pnlSeasonSetup;
    PanelItemSeasonSetup pnlItemSeasonSetup;
    PanelExchangeRate pnlExchangeRate;
    PanelExRateView pnlExRateView;
    PanelAttributeSetup pnlAttributeSetup;
    PanelAttributeView pnlAttrView;

    //Promotion
    PanelPromotionHead pnlPromotionHead;
    PanelPromotionHeadModify pnlPromotionHeadModify;
    PanelPromotionMixMatch pnlPromotionMixMatch;
    PanelPromotionMixMatchModify pnlPromotionMixMatchModify;
    PanelPromoChildSearch pnlPromoChildSearch;
    PanelPromoThresholdSetup pnlPromoThresholdSetup;
    PanelPromoThresholdModify pnlPromoThresholdModify;
    //JPanel pnlCustomerSetup = new PanelCustomerSetup(this,cardLayout1,pnlContent);
    //JPanel pnlSupplierSetup = new PanelSupplierSetup(this,cardLayout1,pnlContent);

    //3. Inventory Manager
    PanelUnitCostChange pnlUnitCostChange;
    PanelPriceChange pnlPriceChange;
    PanelTransfer pnlTransfer;
    PanelPurchaseOrder pnlPurchaseOrder;
    PanelPurchaseOrderModify pnlPurchaseOrderModify;
    PanelTransferView pnlTransferView;
    PanelReceiptSearch pnlreceiptSearch;
    PanelTransferViewDetail pnlTransferViewDetail;
    PanelReceiptFromStoreViewDetail pnlReceiptFromStoreViewDetail;
    PanelItemStockLocator pnlItemStockLocator;
    PanelItemLookupDetail pnlItemLookupDetail;
    PanelInventoryReceipt pnlInventoryReceipt;
    PanelItemLookup pnlItemLookup;
//    PanelSupplierLookup pnlSupplierLookup;
    PanelCustomerSetup pnlCustomerSetup;
//    PanelCustomerSearch pnlCustomerSearch;
    PanelCustomerModify pnlCustomerModify;
    PanelSupplierSetup pnlSupplierSetup;
//    PanelSupplierSearch pnlSupplierSearch;
    PanelSupplierModify pnlSupplierModify;
    PanelStoreSetup pnlStoreSetup;
    PanelGoodsReceipt pnlGoodsReceipt;
    PanelReceiptFromDmgGood pnlRecDmgGood;
    PanelReceiptFromStore pnlReceiptFromStore;
    PanelTransferDamageGoods pnlTransferDamageGoods;
    PanelTransferViewDamageGoods pnlTransferViewDamageGoods;
    PanelTransferModifyDamageGoods pnlTransferModifyDamageGoods;
    PanelViewDamageGoods pnlViewDamageGoods;
    PanelDetailDamageGoods pnlDetailDamageGoods;
    PanelStockOnHand pnlStockOnHand;
    PanelStockCount pnlStockCountBackRoom;
    PanelStockCountFrontRoom pnlStockCountFrontRoom;
    PanelViewStockOnHand pnlViewStockOnHand;
    PanelViewReceiptDetails pnlViewReceiptDetails;
    PanelPickList pnlPickList;
    PanelPickListModify pnlPickListModify;
    PanelRTV pnlRTV;
    PanelRTVModify pnlRTVModify;
    PanelDMGModify pnlDMGModify;

    /*declare panels concerned in transfer to franchise*/
    PanelProductGroup pnlProdGroup;
    PanelProductGroupModify pnlProdGroupModify;
    PanelProductGroupDetails pnlProdGroupDetail;

    PanelTransferFranView pnlTransferFranView;
    PanelTransferFromFranView pnlTransferFromFranView;
    PanelTransferFranViewDtl pnlTransferFranViewDtl;
    PanelStockCountFran pnlStockCountFran;

    PanelReceiptDetail pnlReceiptDetail;

//    PanelStoreSearch pnlStoreSearch;
    PanelStoreModify pnlStoreModify;
    PanelSeasonModify pnlSeaModify;
    PanelItemSeasonModify pnlItemSeasonModify;

    PanelMainSim pnlMainSim;
//  JPanel pnlStockLocator = new PanelAdminEmployee(this,cardLayout1,pnlContent);

    //4. ADMIN-Button Admin
    PanelAdminRole pnlAdminRole ;
    PanelAdminAccessLevel pnlAdminAccessLevel ;
    PanelAdminRoleAccess pnlAdminRoleAccess ;
    PanelAdminGrantRole pnlAdminGrantRole;
    PanelRunBatchJobsManual pnlRunBatchJobsManual;
    BJPanel pnlStatus = new BJPanel(Constant.PANEL_TYPE_HEADER);
    JLabel lblEmp = new JLabel();
    JLabel lblStrore = new JLabel();
    JLabel lblMode = new JLabel();
    JLabel lblTimer = new JLabel();
    GridLayout gridLayout1 = new GridLayout();
    PanelAdminEmployee pnlAdminEmployee;
    PanelAdminEmployeeModify pnlAdminEmployeeModify ;
    PanelAdminRoleSearch pnlAdminRoleSearch;
    PanelAdminRoleModify pnlAdminRoleModify;
    //Vinh.Le
    PanelViewReceipt pnlViewReceipt;

    //Construct the frame
    public FrameMainSim() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception  {

    //get Oracle Connection
    Connection conn = DAL.getOracleConnect();
    //Vinh.Le
    iSeasonBusObj.setDataAccessLayer(DAL);
    seaBusObj.setDataAccessLayer(DAL);
    //DAL.getConnfigParameter();
    SystemClass.frmMainSim = this;

    pnlAdminRole = new PanelAdminRole(this,cardLayout1,pnlContent);
    pnlAdminAccessLevel = new PanelAdminAccessLevel(this,cardLayout1,pnlContent);
    pnlAdminRoleAccess = new PanelAdminRoleAccess(this,cardLayout1,pnlContent);
    pnlAdminGrantRole = new PanelAdminGrantRole(this,cardLayout1,pnlContent);
    pnlRunBatchJobsManual = new PanelRunBatchJobsManual(this,cardLayout1,pnlContent);
    pnlAdminEmployee = new PanelAdminEmployee(this,cardLayout1,pnlContent);
    pnlAdminEmployeeModify = new PanelAdminEmployeeModify(this,cardLayout1,pnlContent);
    pnlAdminRoleSearch = new PanelAdminRoleSearch(this);
    pnlAdminRoleModify = new PanelAdminRoleModify(this,cardLayout1,pnlContent);
    //main panel
    pnlMainSim=new PanelMainSim(this);
    //1. Hierrachy
    pnlPrdHierSetup = new PanelPrdHierSetup(this,cardLayout1,pnlContent);
    pnlOrgHierSetup = new PanelOrgHierSetup(this,cardLayout1,pnlContent);
    pnlPrdHierTypeSetup = new PanelPrdHierTypeSetup(this,cardLayout1,pnlContent);
    pnlOrgHierTypeSetup = new PanelOrgHierTypeSetup(this,cardLayout1,pnlContent);
//    pnlPrdHierTypeSearch = new PanelPrdHierTypeSearch(this);
//    pnlOrgHierTypeSearch = new PanelOrgHierTypeSearch(this);
//    pnlOrgHierSetupModify = new PanelOrgHierSetupModify(this,cardLayout1,pnlContent);
//    pnlPrdHierSetupModify = new PanelPrdHierSetupModify(this,cardLayout1,pnlContent);

    //2. Mer Main
    pnlItemSetup = new PanelItemSetup(this,cardLayout1,pnlContent);
    pnlItemModify = new PanelItemModify(this);
    pnlSeasonSetup = new PanelSeasonSetup(this,cardLayout1,pnlContent);
    pnlItemSeasonSetup = new PanelItemSeasonSetup(this,cardLayout1,pnlContent);
    //Promotion
    pnlPromotionHead = new PanelPromotionHead(this,cardLayout1,pnlContent);
    pnlPromotionHeadModify = new PanelPromotionHeadModify(this,cardLayout1,pnlContent);
    pnlPromotionMixMatch = new PanelPromotionMixMatch(this,cardLayout1,pnlContent);
    pnlPromotionMixMatchModify = new PanelPromotionMixMatchModify(this,cardLayout1,pnlContent);
    pnlPromoChildSearch = new PanelPromoChildSearch(this,cardLayout1,pnlContent);
    pnlPromoThresholdSetup = new PanelPromoThresholdSetup(this,cardLayout1,pnlContent);
    pnlPromoThresholdModify = new PanelPromoThresholdModify(this,cardLayout1,pnlContent);
//Nghi
    //3 Inv Manage
    pnlUnitCostChange = new PanelUnitCostChange(this);
    pnlPriceChange = new PanelPriceChange(this);
    pnlTransfer = new PanelTransfer(this);
    pnlPurchaseOrder = new PanelPurchaseOrder(this,cardLayout1,pnlContent);
    pnlPurchaseOrderModify = new PanelPurchaseOrderModify(this,cardLayout1,pnlContent);
    pnlProdGroupModify = new PanelProductGroupModify(this,cardLayout1,pnlContent);
    pnlPickListModify = new PanelPickListModify(this,cardLayout1,pnlContent);
    pnlRTVModify = new PanelRTVModify(this,cardLayout1,pnlContent);
    pnlDMGModify = new PanelDMGModify(this,cardLayout1,pnlContent);

    pnlProdGroupDetail = new PanelProductGroupDetails(this,cardLayout1,pnlContent);

    pnlTransferView = new PanelTransferView(this);
    pnlreceiptSearch = new PanelReceiptSearch(this);
    pnlTransferViewDetail = new PanelTransferViewDetail(this);
    pnlReceiptFromStoreViewDetail= new PanelReceiptFromStoreViewDetail (this);
    pnlItemStockLocator = new PanelItemStockLocator(this);
    pnlItemLookupDetail= new PanelItemLookupDetail(this);
    pnlInventoryReceipt = new PanelInventoryReceipt(this);
    pnlItemLookup= new PanelItemLookup(this) ;
    pnlCustomerSetup = new PanelCustomerSetup(this);
//    pnlCustomerSearch = new PanelCustomerSearch(this);
    pnlCustomerModify = new PanelCustomerModify(this);

    pnlSupplierSetup = new PanelSupplierSetup(this);
//    pnlSupplierSearch = new PanelSupplierSearch(this);
    pnlSupplierModify = new PanelSupplierModify(this);

    pnlStoreSetup = new PanelStoreSetup(this);
    pnlGoodsReceipt = new PanelGoodsReceipt(this);
    pnlRecDmgGood = new PanelReceiptFromDmgGood(this);
    pnlReceiptFromStore = new PanelReceiptFromStore(this);
    pnlTransferDamageGoods = new PanelTransferDamageGoods(this);
    pnlTransferViewDamageGoods = new PanelTransferViewDamageGoods(this);
    pnlTransferModifyDamageGoods = new PanelTransferModifyDamageGoods(this);
    pnlViewDamageGoods = new PanelViewDamageGoods(this);
    pnlDetailDamageGoods = new PanelDetailDamageGoods(this);
    pnlStockOnHand = new PanelStockOnHand(this);
    pnlProdGroup =new PanelProductGroup(this);
    pnlTransferFranView = new PanelTransferFranView(this);
    pnlTransferFromFranView = new PanelTransferFromFranView(this);
    pnlTransferFranViewDtl = new PanelTransferFranViewDtl(this);
    pnlStockCountFran = new PanelStockCountFran(this);
    pnlStockCountBackRoom = new PanelStockCount(this);
    pnlStockCountFrontRoom = new PanelStockCountFrontRoom(this);
    pnlViewStockOnHand = new PanelViewStockOnHand(this);



    pnlReceiptDetail = new PanelReceiptDetail(this);

//    pnlStoreSearch = new PanelStoreSearch(this);
    pnlStoreModify = new PanelStoreModify(this);
    pnlSeaModify = new PanelSeasonModify(this);
    pnlItemSeasonModify = new PanelItemSeasonModify(this);
    pnlViewReceipt = new PanelViewReceipt(this);
    pnlViewReceiptDetails = new PanelViewReceiptDetails(this);
    pnlPickList = new PanelPickList(this);
    pnlRTV = new PanelRTV(this);

//End Nghi

    pnlExchangeRate = new PanelExchangeRate(this);
    pnlExRateView = new PanelExRateView(this);
    pnlAttributeSetup = new PanelAttributeSetup(this);
    pnlAttrView = new PanelAttributeView(this);

    pnlMain = (JPanel) this.getContentPane();
    pnlMain.setLayout(borderLayout1);
    this.setResizable(false);
    this.setSize(new Dimension(800, 600));
    this.setTitle(lang.getString("TT0020"));
    pnlContent.setBackground(Color.lightGray);
    pnlContent.setForeground(Color.black);
    pnlContent.setPreferredSize(new Dimension(10, 50));
    pnlContent.setLayout(cardLayout1);
    lblEmp.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblEmp.setBorder(BorderFactory.createEtchedBorder());
    lblEmp.setDebugGraphicsOptions(0);
    lblEmp.setHorizontalAlignment(SwingConstants.CENTER);
    lblEmp.setText(lang.getString("Employee")+ ": " +  DAL.getEmpCode());
    lblStrore.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblStrore.setBorder(BorderFactory.createEtchedBorder());
    lblStrore.setHorizontalAlignment(SwingConstants.CENTER);
    lblStrore.setText(lang.getString("Store")+ ": " + DAL.getStoreName());
    lblMode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblMode.setBorder(BorderFactory.createEtchedBorder());
    lblMode.setHorizontalAlignment(SwingConstants.CENTER);
    lblMode.setText("<html>" + lang.getString("Status")+ ": <FONT color=\"yellow\">"+lang.getString("Online")+"</FONT></html>");
    lblTimer.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTimer.setBorder(BorderFactory.createEtchedBorder());
    lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
    lblTimer.setText(lang.getString("Timer"));

    pnlStatus.setLayout(gridLayout1);
    pnlContent.add(pnlMainSim,"pnlMainSim"); //TrungNT
    pnlContent.add(pnlPrdHierSetup,   "pnlPrdHierSetup");
    pnlContent.add(pnlOrgHierSetup,   "pnlOrgHierSetup");
    pnlContent.add(pnlPrdHierTypeSetup,   "pnlPrdHierTypeSetup");
    pnlContent.add(pnlOrgHierTypeSetup,   "pnlOrgHierTypeSetup");
//    pnlContent.add(pnlPrdHierTypeSearch,   "pnlPrdHierTypeSearch");
//    pnlContent.add(pnlOrgHierTypeSearch,   "pnlOrgHierTypeSearch");
//    pnlContent.add(pnlOrgHierSetupModify,   "pnlOrgHierSetupModify");
//    pnlContent.add(pnlPrdHierSetupModify,   "pnlPrdHierSetupModify");

    pnlContent.add(pnlItemSetup,   "pnlItemSetup");
    pnlContent.add(pnlItemModify, "pnlItemModify");
    pnlContent.add(pnlSeasonSetup,   "pnlSeasonSetup");
    pnlContent.add(pnlItemSeasonSetup,   "pnlItemSeasonSetup");
    pnlContent.add(pnlPromotionHead, "pnlPromotionHead");
    pnlContent.add(pnlPromotionHeadModify, "pnlPromotionHeadModify");
    pnlContent.add(pnlPromotionMixMatch, "pnlPromotionMixMatch");
    pnlContent.add(pnlPromotionMixMatchModify, "pnlPromotionMixMatchModify");
    pnlContent.add(pnlPromoChildSearch, "pnlPromoChildSearch");
    pnlContent.add(pnlPromoThresholdSetup, "pnlPromoThresholdSetup");
    pnlContent.add(pnlPromoThresholdModify,"pnlPromoThresholdModify");
    pnlContent.add(pnlItemLookup,   "pnlItemLookup");
    pnlContent.add(pnlItemLookupDetail, "pnlItemLookupDetail");
    pnlContent.add(pnlItemStockLocator, "pnlItemStockLocator");
    pnlContent.add(pnlInventoryReceipt, "pnlInventoryReceipt");

    pnlContent.add(pnlCustomerSetup,   "pnlCustomerSetup");
//    pnlContent.add(pnlCustomerSearch, "pnlCustomerSearch");
    pnlContent.add(pnlCustomerModify, "pnlCustomerModify");
    pnlContent.add(pnlSupplierSetup, "pnlSupplierSetup");
//    pnlContent.add(pnlSupplierSearch,"pnlSupplierSearch");
    pnlContent.add(pnlSupplierModify, "pnlSupplierModify");
    pnlContent.add(pnlStoreSetup, "pnlStoreSetup");
    pnlContent.add(pnlGoodsReceipt, "pnlGoodsReceipt");
    pnlContent.add(pnlRecDmgGood, "pnlRecDmgGood");
    pnlContent.add(pnlReceiptFromStore,"pnlReceiptFromStore");
    pnlContent.add(pnlTransferDamageGoods, "pnlTransferDamageGoods");
    pnlContent.add(pnlTransferViewDamageGoods,"pnlTransferViewDamageGoods");
    pnlContent.add(pnlTransferModifyDamageGoods,"pnlTransferModifyDamageGoods");
    pnlContent.add(pnlViewDamageGoods, "pnlViewDamageGoods");
    pnlContent.add(pnlDetailDamageGoods,"pnlDetailDamageGoods");
    pnlContent.add(pnlStockOnHand, "pnlStockOnHand");
    pnlContent.add(pnlStockCountBackRoom,"pnlStockCountBackRoom");
    pnlContent.add(pnlStockCountFrontRoom,"pnlStockCountFrontRoom");
    pnlContent.add(pnlViewStockOnHand,"pnlViewStockOnHand");
    //add panels that concerned in transfer to franchise
    pnlContent.add(pnlProdGroup, "pnlProdGroup");
    pnlContent.add(pnlTransferFranView,"pnlTransferFranView");
    pnlContent.add(pnlTransferFromFranView,"pnlTransferFromFranView");
    pnlContent.add(pnlTransferFranViewDtl,"pnlTransferFranViewDtl");
    pnlContent.add(pnlStockCountFran, "pnlStockCountFran");
//Phuoc.nguyen
    pnlContent.add(pnlExchangeRate, "pnlExangeRate");
    pnlContent.add(pnlExRateView, "pnlExRateView");
    pnlContent.add(pnlAttrView, "pnlAttrView");

    pnlContent.add(pnlAttributeSetup, "pnlAttributeSetup");
    pnlContent.add(pnlReceiptDetail,"pnlReceiptDetail");

//    pnlContent.add(pnlStoreSearch, "pnlStoreSearch");
    pnlContent.add(pnlStoreModify, "pnlStoreModify");
    pnlContent.add(pnlSeaModify, "pnlSeaModify");
    pnlContent.add(pnlItemSeasonModify, "pnlItemSeasonModify");
    pnlContent.add(pnlViewReceipt, "pnlViewReceipt");
    pnlContent.add(pnlViewReceiptDetails, "pnlViewReceiptDetails");

    //Vinh.Le
    pnlContent.add(pnlPickList, "pnlPickList");
    pnlContent.add(pnlRTV, "pnlRTV");
    pnlContent.add(pnlPurchaseOrder, "pnlPurchaseOrder");
    pnlContent.add(pnlPurchaseOrderModify, "pnlPurchaseOrderModify");
    pnlContent.add(pnlProdGroupModify, "pnlProdGroupModify");
    pnlContent.add(pnlPickListModify, "pnlPickListModify");
    pnlContent.add(pnlRTVModify, "pnlRTVModify");
    pnlContent.add(pnlDMGModify, "pnlDMGModify");
    pnlContent.add(pnlProdGroupDetail,"pnlProdGroupDetail");

//  Nghi
//Trung    pnlContent.add(pnlCustomerSetup, "pnlCustomerSetup");

    pnlContent.add(pnlUnitCostChange, "pnlUnitCostChange");
    pnlContent.add(pnlPriceChange,   "pnlPriceChange");
    pnlContent.add(pnlTransfer, "pnlTransfer");
    pnlContent.add(pnlTransferView, "pnlTransferView");
     pnlContent.add(pnlreceiptSearch, "pnlreceiptSearch");
    pnlContent.add(pnlTransferViewDetail, "pnlTransferViewDetail");
    pnlContent.add(pnlReceiptFromStoreViewDetail, "pnlReceiptFromStoreViewDetail");
//    pnlContent.add(pnlTranfer,   "pnlTranfer");
//    pnlContent.add(pnlItemLookup,   "pnlItemLookup");
//    pnlContent.add(pnlSupplierLookup,   "pnlSupplierLookup");

    pnlContent.add(pnlAdminRole,   "pnlAdminRole");
    pnlContent.add(pnlAdminAccessLevel,   "pnlAdminAccessLevel");
    pnlContent.add(pnlAdminRoleAccess,   "pnlAdminRoleAccess");
    pnlContent.add(pnlAdminGrantRole,   "pnlAdminGrantRole");
    pnlContent.add(pnlRunBatchJobsManual,   "pnlRunBatchJobsManual");
    pnlMain.add(pnlStatus,  BorderLayout.SOUTH);
    pnlContent.add(pnlAdminEmployee,   "pnlAdminEmployee");
    pnlContent.add(pnlAdminEmployeeModify,   "pnlAdminEmployeeModify");
    pnlContent.add(pnlAdminRoleSearch,   "pnlAdminRoleSearch");
    pnlContent.add(pnlAdminRoleModify,   "pnlAdminRoleModify");
   //the end admin
    pnlMain.add(pnlContent,  BorderLayout.CENTER);
    pnlStatus.add(lblMode, null);
    pnlStatus.add(lblStrore, null);
    pnlStatus.add(lblEmp, null);
    pnlStatus.add(lblTimer, null);
    this.setIconImage(new ImageIcon("images/logo1.gif").getImage());
    //set Timer
   timer.scheduleAtFixedRate(new TimerTask() {
     public void run() {
       // Task here
       lblTimer.setText(ut.getSystemDateTime0());
     }
   }

   , 10, 1000);

  }//the end init

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

   public DataAccessLayer getDAL(){
      return DAL;
  }

//  public EmpRight getEmpRight() {
//    EmpRight er = frmLogin

//  }
  //show screen when click button
  //flagScreen: screen u want to goto

  public void showScreen(int flagScreen){

        this.flagScreen = flagScreen;
        //remove old button
        if (flagScreen == Constant.SCRS_MAIN_SIM) {
          cardLayout1.show(pnlContent, "pnlMainSim");
          this.setTitle(lang.getString("TT0021"));
        }else if (flagScreen == Constant.SCRS_ADMIN_ACCESS_LEVEL) {
          cardLayout1.show(pnlContent, "pnlAdminAccessLevel");
          this.setTitle(lang.getString("TT0022"));
        }else if (flagScreen == Constant.SCRS_ADMIN_ROLE) {
          cardLayout1.show(pnlContent,"pnlAdminRole");
          this.setTitle(lang.getString("TT0023"));
        }else if (flagScreen == Constant.SCRS_ADMIN_ROLE_ACCESS) {
          cardLayout1.show(pnlContent,"pnlAdminRoleAccess");//??
          this.setTitle(lang.getString("TT0024"));
        }else if (flagScreen == Constant.SCRS_ADMIN_ROLE_MODIFY) {
          cardLayout1.show(pnlContent,"pnlAdminRoleModify");
          this.setTitle(lang.getString("TT0025"));
        }else if (flagScreen == Constant.SCRS_ADMIN_ROLE_SEARCH) {
          cardLayout1.show(pnlContent,"pnlAdminRoleSearch");
          this.setTitle(lang.getString("TT0026"));
        }else if (flagScreen == Constant.SCRS_ADMIN_EMPLOYEE) {
          cardLayout1.show(pnlContent,"pnlAdminEmployee");
          this.setTitle(lang.getString("TT0027"));
        }else if (flagScreen == Constant.SCRS_ADMIN_EMPLOYEE_MODIFY){
          cardLayout1.show(pnlContent,"pnlAdminEmployeeModify");
          this.setTitle(lang.getString("TT0028"));
        }else if (flagScreen == Constant.SCRS_CUSTOMER_MODIFY) {
          cardLayout1.show(pnlContent,"pnlCustomerModify");
          this.setTitle(lang.getString("TT0029"));
//        }else if (flagScreen == Constant.SCRS_CUSTOMER_SEARCH) {
//          cardLayout1.show(pnlContent,"pnlCustomerSearch");
//          this.setTitle(lang.getString("TT014")+" - Customer - Search");
        }else if (flagScreen == Constant.SCRS_CUSTOMER_SETUP) {
          cardLayout1.show(pnlContent,"pnlCustomerSetup");
          this.setTitle(lang.getString("TT0030"));
        }else if (flagScreen == Constant.SCRS_INVENTORY_RECEIPT) {
          cardLayout1.show(pnlContent,"pnlInventoryReceipt");
          this.setTitle(lang.getString("TT0031"));
        }else if (flagScreen == Constant.SCRS_ITEM_LOOKUP) {
          cardLayout1.show(pnlContent,"pnlItemLookup");
          this.setTitle(lang.getString("TT0032"));
        }else if (flagScreen == Constant.SCRS_ITEM_LOOKUP_DETAIL) {
          cardLayout1.show(pnlContent,"pnlItemLookupDetail");
          this.setTitle(lang.getString("TT0033"));
        }else if (flagScreen == Constant.SCRS_ITEM_SEASON_SETUP) {
          cardLayout1.show(pnlContent,"pnlItemSeasonSetup");
          this.setTitle(lang.getString("TT0034"));
        }else if (flagScreen == Constant.SCRS_ITEM_SETUP) {
          cardLayout1.show(pnlContent,"pnlItemSetup");
          this.setTitle(lang.getString("TT0035"));
        }else if (flagScreen == Constant.SCRS_ITEM_MODIFY){
          cardLayout1.show(pnlContent,"pnlItemModify");
          this.setTitle(lang.getString("TT0036"));
        }else if (flagScreen == Constant.SCRS_SEASON_SETUP) {
          cardLayout1.show(pnlContent,"pnlSeasonSetup");
          this.setTitle(lang.getString("TT0037"));
        } else if (flagScreen == Constant.SCRS_ITEM_STOCK_LOCATOR) {
          cardLayout1.show(pnlContent,"pnlItemStockLocator");
          this.setTitle(lang.getString("TT0038"));
        }else if (flagScreen == Constant.SCRS_ORG_HIER_SETUP) {
          cardLayout1.show(pnlContent,"pnlOrgHierSetup");
          this.setTitle(lang.getString("TT0039"));
//        }else if (flagScreen == Constant.SCRS_ORG_HIER_SETUP_MODIFY) {
//          cardLayout1.show(pnlContent,"pnlOrgHierSetupModify");
//          this.setTitle(lang.getString("TT014")+" - Org Hier Setup Modify");
//        }else if (flagScreen == Constant.SCRS_ORG_HIER_TYPE_SEARCH) {
//          cardLayout1.show(pnlContent,"pnlOrgHierTypeSearch");
//          this.setTitle(lang.getString("TT014")+" - Org Hier Search");
        }else if (flagScreen == Constant.SCRS_ORG_HIER_TYPE_SETUP) {
          cardLayout1.show(pnlContent,"pnlOrgHierTypeSetup");
          this.setTitle(lang.getString("TT0040"));
        }else if (flagScreen == Constant.SCRS_PRD_HIER_SETUP) {
          cardLayout1.show(pnlContent,"pnlPrdHierSetup");
          this.setTitle(lang.getString("TT0041"));
//        }else if (flagScreen == Constant.SCRS_PRD_HIER_SETUP_MODIFY) {
//          cardLayout1.show(pnlContent,"pnlPrdHierSetupModify");
//          this.setTitle(lang.getString("TT014")+" - Hierarchy Setup - Product Hierarchy Setup Modify");
//        }else if (flagScreen == Constant.SCRS_PRD_HIER_TYPE_SEARCH) {
//          cardLayout1.show(pnlContent,"pnlPrdHierTypeSearch");
//          this.setTitle(lang.getString("TT014")+" - Product Hierachy Search");
        }else if (flagScreen == Constant.SCRS_PRD_HIER_TYPE_SETUP) {
          cardLayout1.show(pnlContent,"pnlPrdHierTypeSetup");
          this.setTitle(lang.getString("TT0042"));
        }else if (flagScreen == Constant.SCRS_PRICE_CHANGE) {
          cardLayout1.show(pnlContent,"pnlPriceChange");
          this.setTitle(lang.getString("TT0043"));
        }else if (flagScreen == Constant.SCRS_UNIT_COST_CHANGE) {
          cardLayout1.show(pnlContent,"pnlUnitCostChange");
          this.setTitle(lang.getString("TT0044"));
        }else if(flagScreen == Constant.SCRS_TRANSFER){
          cardLayout1.show(pnlContent,"pnlTransfer");
          this.setTitle(lang.getString("TT0045"));
        }else if (flagScreen == Constant.SCRS_RECEIPT_DETAIL) {
          cardLayout1.show(pnlContent,"pnlReceiptDetail");
          this.setTitle(lang.getString("TT0046"));
        }else if (flagScreen == Constant.SCRS_STORE_MODIFY) {
          cardLayout1.show(pnlContent,"pnlStoreModify");
          this.setTitle(lang.getString("TT0047"));
        }else if (flagScreen == Constant.SCRS_SEASON_MODIFY) {
          cardLayout1.show(pnlContent,"pnlSeaModify");
          this.setTitle(lang.getString("TT0048"));
        }else if (flagScreen == Constant.SCRS_ITEM_SEASON_MODIFY) {
          cardLayout1.show(pnlContent,"pnlItemSeasonModify");
          this.setTitle(lang.getString("TT0049"));
//        }else if (flagScreen == Constant.SCRS_STORE_SEARCH) {
//          cardLayout1.show(pnlContent,"pnlStoreSearch");
//          this.setTitle(lang.getString("TT014")+" - Store Search");
        }else if (flagScreen == Constant.SCRS_STORE_SETUP) {
          cardLayout1.show(pnlContent,"pnlStoreSetup");
          this.setTitle(lang.getString("TT0050"));
        }else if (flagScreen == Constant.SCRS_STOCK_ON_HAND){
          cardLayout1.show(pnlContent,"pnlStockOnHand");
          this.setTitle(lang.getString("TT0094"));
        }else if (flagScreen == Constant.SCRS_PROD_GROUP){ //show Franchise Transfer Screen
          cardLayout1.show(pnlContent,"pnlProdGroup");
          this.setTitle(lang.getString("TT0051"));
        }else if(flagScreen == Constant.SCRS_PROD_GROUP_MODIFY){
          cardLayout1.show(pnlContent,"pnlProdGroupModify");
          this.setTitle(lang.getString("TT0052"));
        }else if(flagScreen == Constant.SCRS_PROD_GROUP_DETAIL){
          cardLayout1.show(pnlContent,"pnlProdGroupDetail");
          this.setTitle(lang.getString("TT0053"));
        }else if (flagScreen == Constant.SCRS_TRANSFER_FRAN_VIEW){ //show View of Franchise Transfer
         cardLayout1.show(pnlContent,"pnlTransferFranView");
         this.setTitle(lang.getString("TT0054"));
        }else if (flagScreen == Constant.SCRS_TRANSFER_FROM_FRAN_VIEW){ //show View of Transfers from Franchise
         cardLayout1.show(pnlContent,"pnlTransferFromFranView");
         this.setTitle(lang.getString("TT0055"));
        }else if (flagScreen == Constant.SCRS_VIEW_TRANSFER_FRAN_DTL){ //show View Detail of Franchise Transfer
          cardLayout1.show(pnlContent,"pnlTransferFranViewDtl");
          this.setTitle(lang.getString("TT0056"));
        }else if (flagScreen==Constant.SCRS_FRAN_STOCK_COUNT){
          cardLayout1.show(pnlContent,"pnlStockCountFran");
          this.setTitle(lang.getString("TT0057"));
        }else if (flagScreen == Constant.SCRS_STOCK_COUNT){
          cardLayout1.show(pnlContent,"pnlStockCountBackRoom");
          this.setTitle(lang.getString("TT0058"));
        }else if (flagScreen == Constant.SCRS_STOCK_COUNT_FRONT_ROOM){
          cardLayout1.show(pnlContent,"pnlStockCountFrontRoom");
          this.setTitle(lang.getString("TT0059"));
        }else if(flagScreen == Constant.SCRS_STOCK_ON_HAND_VIEW){
          cardLayout1.show(pnlContent,"pnlViewStockOnHand");
          this.setTitle(lang.getString("TT0061"));
        }else if (flagScreen == Constant.SCRS_GOOD_RECEIPT){
          cardLayout1.show(pnlContent,"pnlGoodsReceipt");
          this.setTitle(lang.getString("TT0062"));
        }else if (flagScreen == Constant.SCRS_RECEIPT_DAMAGE_GOODS){
          cardLayout1.show(pnlContent,"pnlRecDmgGood");
          this.setTitle(lang.getString("TT0063"));
        }else if (flagScreen == Constant.SCRS_RECEIPT_FROME_STORE){
          cardLayout1.show(pnlContent,"pnlReceiptFromStore");
          this.setTitle(lang.getString("TT0064"));
        }else if (flagScreen == Constant.SCRS_TRANSFER_DAMAGE_GOODS){
          cardLayout1.show(pnlContent,"pnlTransferDamageGoods");
          this.setTitle(lang.getString("TT0065"));
//          this.setTitle(lang.getString("TT014")+" - Receive from Store");
        }else if (flagScreen == Constant.SCRS_TRANSFER_VIEW_DAMAGE_GOODS){
          cardLayout1.show(pnlContent,"pnlTransferViewDamageGoods");
          this.setTitle(lang.getString("TT0066"));
        }else if (flagScreen == Constant.SCRS_TRANSFER_MODIFY_DAMAGE_GOODS){
          cardLayout1.show(pnlContent, "pnlTransferModifyDamageGoods");
          this.setTitle(lang.getString("TT0067"));
        }else if(flagScreen == Constant.SCRS_VIEW_DAMAGE_GOODS){
          cardLayout1.show(pnlContent,"pnlViewDamageGoods");
          this.setTitle(lang.getString("TT0066"));
        }else if(flagScreen == Constant.SCRS_DETAIL_DAMAGE_GOODS){
          cardLayout1.show(pnlContent,"pnlDetailDamageGoods");
          this.setTitle(lang.getString("TT0068"));
        }else if (flagScreen == Constant.SCRS_SUPPLIER_MODIFY) {
          cardLayout1.show(pnlContent,"pnlSupplierModify");
          this.setTitle(lang.getString("TT0069"));
//        }else if (flagScreen == Constant.SCRS_SUPPLIER_SEARCH) {
//          cardLayout1.show(pnlContent,"pnlSupplierSearch");
//          this.setTitle(lang.getString("TT014")+" - Supplier Search");
        }else if (flagScreen == Constant.SCRS_SUPPLIER_SETUP) {
          cardLayout1.show(pnlContent,"pnlSupplierSetup");
          this.setTitle(lang.getString("TT0070"));
        }else if (flagScreen == Constant.SCRS_VIEW_RECEIPT) {
          cardLayout1.show(pnlContent,"pnlViewReceipt");
          this.setTitle(lang.getString("TT0071"));
        }else if (flagScreen == Constant.SCRS_VIEW_RECEIPT_DETAILS) {
          cardLayout1.show(pnlContent,"pnlViewReceiptDetails");
          this.setTitle(lang.getString("TT0072"));
        }
        else if (flagScreen == Constant.SCRS_TRANSFER_VIEW) {
          cardLayout1.show(pnlContent, "pnlTransferView");
          this.setTitle(lang.getString("TT0073"));
        }
        else if (flagScreen == Constant.SCRS_RECEIPT_FROM_STORE_SEARCH) {
          cardLayout1.show(pnlContent, "pnlreceiptSearch");
          this.setTitle(lang.getString("TT0074"));
        }

        else if (flagScreen == Constant.SCRS_TRANSFER_VIEW_DETAIL) {
          cardLayout1.show(pnlContent, "pnlTransferViewDetail");
          this.setTitle(lang.getString("TT0075"));
        }
        else if (flagScreen == Constant.SCRS_RECEIPT_FROM_STORE_VIEW_DETAIL) {
          cardLayout1.show(pnlContent, "pnlReceiptFromStoreViewDetail");
          this.setTitle(lang.getString("TT0076"));
        }

        else if (flagScreen == Constant.SCRS_EXCHANGE_RATE) {
          cardLayout1.show(pnlContent, "pnlExangeRate");
          this.setTitle(lang.getString("TT0077"));
        }
        else if (flagScreen == Constant.SCRS_ADMIN_GRANT_ROLE) {
          cardLayout1.show(pnlContent, "pnlAdminGrantRole");
          this.setTitle(lang.getString("TT0078"));
        }else if( flagScreen == Constant.SCRS_ATTRIBUTE_SETUP){
          cardLayout1.show(pnlContent,"pnlAttributeSetup");
          this.setTitle(lang.getString("TT0079"));
        }else if( flagScreen == Constant.SCRS_PICK_LIST){
          cardLayout1.show(pnlContent,"pnlPickList");
          this.setTitle(lang.getString("TT0080"));
        }else if(flagScreen == Constant.SCRS_PICK_LIST_MODIFY){
          cardLayout1.show(pnlContent,"pnlPickListModify");

        }else if( flagScreen == Constant.SCRS_RTV){
          cardLayout1.show(pnlContent,"pnlRTV");
          this.setTitle(lang.getString("TT0081"));
        }else if(flagScreen == Constant.SCRS_RTV_MODIFY){
          cardLayout1.show(pnlContent,"pnlRTVModify");
        }else if(flagScreen == Constant.SCRS_DMG_MODIFY){
          cardLayout1.show(pnlContent,"pnlDMGModify");
        }else if(flagScreen == Constant.SCRS_EXCHANGE_RATE_VIEW){
            cardLayout1.show(pnlContent,"pnlExRateView");
            this.setTitle(lang.getString("TT0082"));
        }else if(flagScreen == Constant.SCRS_ATTRIBUTE_VIEW){
            cardLayout1.show(pnlContent,"pnlAttrView");
            this.setTitle(lang.getString("TT0083"));
        }else if(flagScreen == Constant.SCRS_ADMIN_RUN_BATCH_JOB){
            cardLayout1.show(pnlContent,"pnlRunBatchJobsManual");
            this.setTitle(lang.getString("TT0084"));
        }else if (flagScreen == Constant.SCRS_PROMOTION_HEAD) {
            cardLayout1.show(pnlContent,"pnlPromotionHead");
            this.setTitle(lang.getString("TT0085"));
          }else if (flagScreen == Constant.SCRS_PROMOTION_HEAD_MODIFY) {
            cardLayout1.show(pnlContent,"pnlPromotionHeadModify");
            this.setTitle(lang.getString("TT0086"));
          }else if (flagScreen == Constant.SCRS_PROMOTION_MIX_MATCH) {
              cardLayout1.show(pnlContent,"pnlPromotionMixMatch");
              this.setTitle(lang.getString("TT0087"));
          }else if (flagScreen == Constant.SCRS_PROMOTION_THRES_HOLD) {
              cardLayout1.show(pnlContent,"pnlPromoThresholdSetup");
              this.setTitle(lang.getString("TT0088"));
          }else if(flagScreen == Constant.SCRS_PROMOTION_THRES_HOLD_MODIFY){
            cardLayout1.show(pnlContent,"pnlPromoThresholdModify");
            this.setTitle(lang.getString("TT0089"));
          }else if(flagScreen == Constant.SCRS_PURCHASE_ORDER){
            cardLayout1.show(pnlContent,"pnlPurchaseOrder");
            this.setTitle(lang.getString("TT0090"));
          }else if(flagScreen == Constant.SCRS_PURCHASE_ORDER_MODIFY){
            cardLayout1.show(pnlContent,"pnlPurchaseOrderModify");
            this.setTitle(lang.getString("TT0091"));
          }else if (flagScreen == Constant.SCRS_PROMOTION_CHILD_SEARCH){
            cardLayout1.show(pnlContent,"pnlPromoChildSearch");
            this.setTitle(lang.getString("TT0092"));
          }else if (flagScreen == Constant.SCRS_PROMOTION_MIX_MATCH_MODIFY){
            cardLayout1.show(pnlContent,"pnlPromotionMixMatchModify");
            this.setTitle(lang.getString("TT0093"));
          }
  }
  //show panel in pnlContent
  void showPanel(String childPanel,String title){
    cardLayout1.show(pnlContent,childPanel);
    this.setTitle(title);
  }

  //show Prd Setup Modify
  void showPrdHierSetupModify(String typeId){
//    pnlPrdHierSetupModify.typeId = typeId;
//    pnlPrdHierSetupModify.showData();
  }

  //show Org Setup Modify
  void showOrgHierSetupModify(String typeId){
//    pnlOrgHierSetupModify.typeId = typeId;
//    pnlOrgHierSetupModify.showData();
  }

  //Nghi
  //show customerModify
  void showCustomerModify(String custID){
//    pnlCustomerModify.custID = custID;
//    pnlCustomerModify.showData();
  }
  //show customerSearch
  void showCustomerSearch(){
//    pnlCustomerSearch.searchData(pnlCustomerSearch.rowsNum);
  }
  //show supplierModify
  void showSupplierModify(String suppID){

//    pnlSupplierModify.suppID = suppID;
//    pnlSupplierModify.showData();

//    pnlSupplierModify.suppID = suppID;
    pnlSupplierModify.showData(suppID);

  }
  //show supplierSearch
  void showSupplierSearch(){
//    pnlSupplierSearch.searchData(pnlSupplierSearch.rowsNum);
  }
  // show store modify
  void showStoreModify(long storeID){
    pnlStoreModify.showData(storeID);
  }
  //show store search
  void showStoreSearch(){
//    pnlStoreSearch.searchData(pnlStoreSearch.rowsNum);
  }
  //End Nghi
  //Loan vo
  // show store modify
   void showSeasonModify(long seaID){
     pnlSeaModify.showData(seaID);
   }

  // show item season modify
 void showItemSeasonModify(String itemID, String seasonID,String phaseID){
   pnlItemSeasonModify.showData(itemID,seasonID,phaseID);
 }
 // show item season modify
 void showItemSeasonModify(String itemID, String seasonID,String sArtNo,String sItemID,String sItemName,int iAppSeason){
  pnlItemSeasonModify.sArtNo=sArtNo;
  pnlItemSeasonModify.sItemID=sItemID;
  pnlItemSeasonModify.sItemName=sItemName;
  pnlItemSeasonModify.iAppSeason=iAppSeason;
  pnlItemSeasonModify.showData(itemID,seasonID);
 }


  private void displayPanel(CardLayout cardlayout, JPanel jPanel, String pnl, String wndTitle) {
    pnlMain = (JPanel) this.getContentPane();
    this.setTitle(wndTitle);
    cardlayout.show(jPanel,pnl);
  }
  public void goToReceiptDetail(String receipt_id, String date){
//   this.setInputLabel("");
//   setEditTextInput(false);
//   pnlReceiptDetail.lblDate.setText();
   pnlReceiptDetail.lblDate.setText("Receipt Item Detail on "+date);
   pnlReceiptDetail.showData(receipt_id);
 }
}//end class
