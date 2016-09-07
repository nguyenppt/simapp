  package common;

  /**
   * <p>Title: </p>
   * <p>Description: </p>
   * <p>Copyright: Copyright (c) 2005</p>
   * <p>Company: BTM International Software</p>
   * @author
   * @version 1.0
   */

  public class Constant {
//=========================================================================//
//============================== JPOS CONST  ==============================//
//=========================================================================//


    //================= Flag Screen for New Sale
    public static final int SCR_MAIN =100 ;
    public static final int SCR_NEW_SALE =101 ;
    public static final int SCR_EVEN_EXCHANGE =102 ;
    public static final int SCR_ADD_ITEM=103;
    public static final int SCR_RETURN_ITEM=104;
    public static final int SCR_RETURN_NOTFOUND_ITEM=1041;
    public static final int SCR_PAYMENT =105;
    public static final int SCR_VIEW_TRANS = 109;
    public static final int SCR_DETAIL_TRANS = 110;
    public static final int SCR_VIEW_TRANSACTION = 112;
    public static final int SCR_DETAIL_TRANSACTION = 113;
    public static final int SCR_REGISTER_CLOSE = 114;
    public static final int SCR_RECEIPT_ITEM = 115;
    public static final int SCR_INVENTORY_RECEIPT = 116;
    public static final int SCR_NET_SALES_BY_ITEM = 117;
    public static final int SCR_NET_SALES_BY_TRANS = 118;
    public static final int SCR_CUSTOMER_SETUP = 119;
    public static final int SCR_CUSTOMER_MODIFY = 200;
    public static final int SCR_TRANSFER_ITEM = 201;
    public static final int SCR_VIEW_TRANSFER_TRANS = 202;
    public static final int SCR_SCHEDULE_TIMER = 203;
    public static final int SCR_VOID_TRANSACTION = 204;
    public static final int SCR_STOCK_COUNT = 205;

    public static final int SCR_PAID_OUT = 206;
    public static final int SCR_LOG_OFF = 207;
    public static final int SCR_PAID_IN = 208;

    public static final int SCR_EXCHANGE_RATE = 209;
    public static final int SCR_EXCHANGE_RATE_VIEW = 210;
    public static final int SCR_RUN_POS_BATCH_JOB = 211;
    public static final int SCR_RETURN_DAMAGE_GOODS_ITEM = 212;
    public static final int SCR_VIEW_DAMAGE_GOODS_ITEM = 213;
    public static final int SCR_MODIFY_DAMAGE_GOODS_ITEM = 214;

    public static final int SCR_VIEW_RECEIPT = 215;



    //================= Flag Button for Even Exchange
    public static final int EVEN_EXCHANGE_TRANS = 235;

    //decimal in Payment Panel
    public static final int PM_DECIMAL_LENGTH =2;
    //date delimiter
    public static final String DATE_DILIMITER ="/";
    //Transaction type ( GET FROM DB)
    public static final String TRANS_TYPE_SALE ="1";
    public static final String TRANS_TYPE_RETURN ="2";
    //Print type
    public static final int PRINT_NEW_SALE =1 ;
    public static final int PRINT_RECEIPT_ITEM =2 ;

    //Flag Button for TransferItem
    public static final int TI_TRANSFER_ITEM = 1;
    public static final int TI_SEARCH_ITEM = 2;
    public static final int TI_MODIFY_QUANTITY = 3;

    //Flag Button for Return Damage Goods
    public static final int RETURN_DAMAGE_GOODS_ITEM = 1;
    public static final int SEARCH_DAMAGE_GOODS_ITEM = 2;
    public static final int MODIFY_DAMAGE_GOODS_ITEM = 3;
    //Flag Button for ViewTransferTransaction
    public static final int TI_VIEW_TRANSFER_TRANS = 1;


//=========================================================================//
//============================== JSIM CONST  ==============================//
//=========================================================================//

    //================= Flag Screen for SIM
    public static final int SCRS_MAIN_SIM =1000 ;
    //================= Flag Screen for Hierchy
    public static final int SCRS_HIER_SETUP =1100 ;
    public static final int SCRS_PRD_HIER_TYPE_SETUP =1101 ;
    public static final int SCRS_PRD_HIER_SETUP =1102 ;
    public static final int SCRS_ORG_HIER_TYPE_SETUP=1103;
    public static final int SCRS_ORG_HIER_SETUP =1104 ;
    public static final int SCRS_PRD_HIER_TYPE_SEARCH=1105;
    public static final int SCRS_ORG_HIER_TYPE_SEARCH =1106;
    public static final int SCRS_ORG_HIER_SETUP_MODIFY = 1107;
    public static final int SCRS_PRD_HIER_SETUP_MODIFY = 1108;
    //================= Flag Screen for Merchandise
    public static final int SCRS_MERC =1200 ;
    public static final int SCRS_ITEM_SETUP = 1201;
    public static final int SCRS_ITEM_MODIFY = 221;//*
    public static final int SCRS_SEASON_SETUP = 1202;
    public static final int SCRS_SEASON_MODIFY = 217;//*

    public static final int SCRS_ITEM_SEASON_SETUP = 1203;
    public static final int SCRS_ITEM_SEASON_MODIFY = 203;//*
    public static final int SCRS_ITEM_STOCK_LOCATOR = 205;//*
    public static final int SCRS_ITEM_LOOKUP_DETAIL = 206;//*

    public static final int SCRS_CUSTOMER_SETUP=1204;
    public static final int SCRS_CUSTOMER_SEARCH =208;//*
    public static final int SCRS_CUSTOMER_MODIFY = 209;//*

    public static final int SCRS_SUPPLIER_SETUP = 1205;
    public static final int SCRS_SUPPLIER_MODIFY = 212;//*

    public static final int SCRS_STORE_SETUP = 1206;
    public static final int SCRS_STORE_SEARCH = 214;//*
    public static final int SCRS_STORE_MODIFY = 215;//*
    public static final int SCRS_EXCHANGE_RATE = 218;//*
    public static final int SCRS_ATTRIBUTE_SETUP = 219;//*
    public static final int SCRS_EXCHANGE_RATE_VIEW = 220;//*
    public static final int SCRS_ATTRIBUTE_VIEW = 222;//*

    //===================== Flag Screen for Promotion
    public static final int SCRS_PROMOTION_HEAD = 1901;
    public static final int SCRS_PROMOTION_HEAD_MODIFY = 1911;
    public static final int SCRS_PROMOTION_STORE = 1902;
    public static final int SCRS_PROMOTION_MIX_MATCH = 1903;
    public static final int SCRS_PROMOTION_THRES_HOLD = 1904;
    public static final int SCRS_PROMOTION_CHILD_SEARCH = 1905;
    public static final int SCRS_PROMOTION_MIX_MATCH_MODIFY = 1906;
    public static final int SCRS_PROMOTION_THRES_HOLD_MODIFY = 1907;

    //===================== Flag Screen for Inventory
    public static final int SCRS_INV_MAN = 1300;
    public static final int SCRS_ITEM_LOOKUP = 1301;
    public static final int SCRS_SUPPLIER_SEARCH = 1302;
    public static final int SCRS_PRICE_CHANGE = 1303;
    public static final int SCRS_UNIT_COST_CHANGE = 1306;
    public static final int SCRS_INVENTORY_RECEIPT = 1304;
    public static final int SCRS_RECEIPT_DETAIL = 303;//*
    public static final int SCRS_STOCK_ON_HAND = 304;//*
    public static final int SCRS_PROD_GROUP = 3081; /*Flag screen for transfer to franchise*/
    public static final int SCRS_PROD_GROUP_MODIFY = 3082; /*Flag screen for transfer to franchise*/
    public static final int SCRS_PROD_GROUP_DETAIL = 3083; /*Flag screen for Product Group detail*/
    public static final int SCRS_TRANSFER_FRAN_VIEW = 1309; /*flag screen for view transfers*/
    public static final int SCRS_TRANSFER_FROM_FRAN_VIEW = 1311; /*flag screen for view transfers from franchise*/
    public static final int SCRS_VIEW_TRANSFER_FRAN_DTL = 1310; /*flag screen for view detail transfers*/
    public static final int SCRS_STOCK_ON_HAND_VIEW = 310;//*
    public static final int SCRS_GOOD_RECEIPT = 305;//*
    public static final int SCRS_TRANSFER = 1305;
    public static final int SCRS_RTV = 3055;
    public static final int SCRS_RTV_MODIFY = 3056;
    public static final int SCRS_DMG_MODIFY = 3057;
    public static final int SCRS_PICK_LIST = 3061;
    public static final int SCRS_PICK_LIST_MODIFY = 3062;
    public static final int SCRS_VIEW_RECEIPT = 306;//*
    public static final int SCRS_TRANSFER_VIEW = 307;//*
    public static final int SCRS_TRANSFER_VIEW_DETAIL = 308;//*
    public static final int SCRS_VIEW_RECEIPT_DETAILS = 309;//*
    public static final int SCRS_RECEIPT_DAMAGE_GOODS = 311;//*
    public static final int SCRS_RECEIPT_FROM_STORE_SEARCH = 322;//*
    public static final int SCRS_VIEW_DAMAGE_GOODS = 312;//*
    public static final int SCRS_DETAIL_DAMAGE_GOODS = 313;//*
    public static final int SCRS_STOCK_COUNT = 314;//*
    public static final int SCRS_STOCK_COUNT_FRONT_ROOM = 315;//*
    public static final int SCRS_FRAN_STOCK_COUNT = 3151;//*
    public static final int SCRS_RECEIPT_FROME_STORE = 316; //*
    public static final int SCRS_RECEIPT_FROM_STORE_VIEW_DETAIL = 323; //*
    public static final int SCRS_TRANSFER_DAMAGE_GOODS = 317; //*
    public static final int SCRS_TRANSFER_VIEW_DAMAGE_GOODS = 318;//*
    public static final int SCRS_TRANSFER_MODIFY_DAMAGE_GOODS = 319;//*
    public static final int SCRS_PURCHASE_ORDER = 320;
    public static final int SCRS_PURCHASE_ORDER_MODIFY = 321;
    //===================== Flag Screen for Admin
    public static final int SCRS_ADMIN = 1400;
    public static final int SCRS_ADMIN_ROLE = 1401;
    public static final int SCRS_ADMIN_ROLE_ACCESS = 1402;
    public static final int SCRS_ADMIN_ACCESS_LEVEL = 402;//*
    public static final int SCRS_ADMIN_ROLE_SEARCH = 404;//*
    public static final int SCRS_ADMIN_ROLE_MODIFY = 405;//*
    public static final int SCRS_ADMIN_EMPLOYEE = 1403;
    public static final int SCRS_ADMIN_GRANT_ROLE = 1404;
    public static final int SCRS_ADMIN_EMPLOYEE_MODIFY = 407;//*
    public static final int SCRS_ADMIN_RUN_BATCH_JOB = 1405;





    //Tree view
    public static final String root="Root";
    public static final String rootId="-1";
    public static final String startId="001";



//=========================================================================//
//=========================== BOTH POS AND SIM  ===========================//
//=========================================================================//
    //POS_MODULE=true if JPOS, POS_MODULE=false if JSIM
    public static final boolean POS_MODULE =false ;
    //length of limit search text box
    public static final int LEN_LIMIT_SEARCH= 3;
    //==================================CSS==================================//
    //=================CSS FrameMain
    public static final int FRM_POS_HEIGHT =600 ;
    public static final int FRM_POS_WIDTH =800 ;

    //=================CSS Dialog
    public static final int DLG_POS_HEIGHT =400 ;
    public static final int DLG_POS_WIDTH =600 ;

    //=================CSS SCR_

    public static final int PANEL_TYPE_DEFAULT = 0;
    public static final int PANEL_TYPE_HEADER = 1 ;

    public static final int PANEL_RED_DEFAULT =236 ;
    public static final int PANEL_GREEN_DEFAULT =233 ;
    public static final int PANEL_BLUE_DEFAULT =216 ;

    public static final int PANEL_RED_HEADER =121 ;
    public static final int PANEL_GREEN_HEADER =152 ;
    public static final int PANEL_BLUE_HEADER =198 ;
    //Border Color
    public static final int PANEL_HIGHLIGH_RED =172 ;
    public static final int PANEL_HIGHLIGH_GREEN =217 ;
    public static final int PANEL_HIGHLIGH_BLUE =255 ;
    public static final int PANEL_SHADOW_RED =84 ;
    public static final int PANEL_SHADOW_GREEN =106 ;
    public static final int PANEL_SHADOW_BLUE =138 ;

    //=================CSS POS Button
//    public static final int BTN_POS_HEIGHT =114 ;
//    public static final int BTN_POS_WIDTH =133 ;
//=======
    public static final int BTN_POS_HEIGHT =37 ;
    public static final int BTN_POS_WIDTH =130 ;

    public static final int BTN_POS_FONTSIZE =12 ;
    //=================CSS SIM Button
    public static final int BTN_SIM_HEIGHT =37 ;
    public static final int BTN_SIM_WIDTH =130 ;
    public static final int BTN_SIM_FONTSIZE =12 ;
    //=================CSS POS Label
    public static final int LBL_POS_FONTSIZE =12 ;
    //=================CSS POS Label
    public static final int LBL_SIM_FONTSIZE =12 ;
    //================ CSS textbox
    //LENGTH of main input
    public static final int LENGHT_MAIN_INPUT =19;
    public static final int LENGHT_MAIN_INPUT_TRANS =18;
    public static final int LENGHT_MAIN_INPUT_DATE =10;
    public static final int LENGHT_DRAWER_INPUT =8;
    public static final int LENGHT_UPC_INPUT = 13;
    public static final int LENGHT_ITEM_ID_INPUT = 13;
    public static final int TXT_FONTSIZE =133 ;

    public static final int LENGHT_PICK_LIST_ID=13; //length of pick list id

    //Constant for DialogLocation
    public static final int ITEM_SETUP = 1;
    public static final int STORE_SETUP = 2;

    //Constant Return Item
    public static final int RETURN_ITEM = 1;
    public static final int SALES_ITEM = 2;

    //============================= BAT JOB ====================================
    //Batch Job status
    public static final String COMPLETE = "Complete";
    public static final String NOT_COMPLETE = "Not Complete";

    //SIM Batch Job name
    public static final String SIM_EMPLOYEE = "SimEmployee";
    public static final String SIM_EXCHANGE_RATE = "SimExchangeRate";
    public static final String SIM_ITEM_PRICE = "SimItemPrice";
    public static final String SIM_SALES_ITEM_LOC = "SimSalesItemLoc";
    public static final String SIM_SOH = "SimSOH";
    public static final String SIM_INV_ITEM_LOC = "SimInvItemLoc";
    public static final String SIM_FRAN_INV_ITEM_LOC = "SimFranInvItemLoc";
    public static final String SIM_CUSTOMER = "SimCustomer";
    public static final String SIM_SUM_DAILY_TRANS_BACK = "SimSumDailyTransBackup";

    //POS Batch Job name
    public static final String POS_CUSTOMER = "PosCustomer";
    public static final String POS_NEW_ITEM = "PosNewItem";
    public static final String POS_SALES_ITEM_LOC = "PosSalesItemLoc";
    public static final String POS_SUM_DAILY_TRANS = "PosSumDailyTrans";
    public static final String POS_SOH = "PosSOH";
    public static final String POS_SUM_DAILY_TRANS_BACK = "PosSumDailyTransBackup";
    public static final String POS_EMPLOYEE = "PosEmployee";
    public static final String POS_EXCHANGE_RATE = "PosExchangeRate";
    public static final String POS_ITEM_PRICE = "PosItemPrice";

    // Batch Job file name
    public static final String IMPORT_PATH = "./Batch/Import";

    // POS Batch Job file name
    public static final String POS_EMPLOYEE_FILE = "Employee_";
    public static final String POS_EXCHANGE_RATE_FILE = "ExchangeRate_";
    public static final String POS_ITEM_PRICE_FILE = "ItemPrice_";

    // SIM Batch Job file name
    public static final String SIM_SALES_ITEM_LOC_FILE = "SalesItemLoc_";
    public static final String SIM_SOH_FILE = "ExportSOH_";
    public static final String SIM_CUSTOMER_FILE = "Customer_";
    public static final String SIM_SUM_DAILY_TRANS_BACK_FILE = "SumDailyBackup_";

    //Promotion Info
    //Buy Type
    public static final String BUY_TYPE_ALL = "All";
    public static final String BUY_TYPE_ANY = "Any";
    public static final String BUY_TYPE_LIST = "List";

    //Threshold Type discount (for Promotion Threshold)
    public static final String THRESHOLD_TYPE_UNIT = "Unit";
    public static final String THRESHOLD_TYPE_AMOUNT = "Amount";
    public static final String THRESHOLD_TYPE_BOTH = "Unit_And_Amount";
    public static final String THRESHOLD_TYPE_AMOUNT_LIST = "Amount_List";
    //Mix Match Type discount (Get Type)
    public static final String GET_TYPE_PERCENT_OFF = "Percentage Off";
    public static final String GET_TYPE_FIXED_UNIT_PRICE = "Fixed Unit Price";
    public static final String GET_TYPE_FIXED_TOTAL_PRICE = "Fixed Total Price";
    public static final String GET_TYPE_UNIT_PRICE_OFF = "Unit Price Off";
    public static final String GET_TYPE_TOTAL_PRICE_OFF = "Total Price Off";
    public static final String GET_TYPE_GET_FREE = "Get Free";
    public static final String GET_TYPE_LIST_PERCENTAGE = "List Percentage";
    //=================  Total
    public Constant() {
    }

  }
