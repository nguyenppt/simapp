package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

import java.sql.*;

public class ItemMaster {
    public static final int LEN_ITEM_ID = 13;
    public static final int LEN_ITEM_NAME = 120;
    public static final int LEN_ITEM_DESC = 120;
    public static final int LEN_ITEM_ARTNO = 13;
    public static final int LEN_ITEM_MATERIAL = 120;

  private String itemId;//ITEM_ID, varchar2(25) , <N> , <PK>
  private String itemName;//ITEM_NAME , varchar2(120) , <Y>
  private String itemDesc;//ITEM_DESC , varchar2(120)
  private String shortDesc;//SHORT_DESC , varchar2(20)
  private String itemAggregateInd;//ITEM_AGGREGATE_IND , varchar2(1)
  private String attr1Id;//ATTR1_ID , varchar2(4)
  private String attr2Id;//ATTR2_ID , varchar2(4)
  private String attr3Id;//ATTR3_ID , varchar2(4)
  private String attr1DtlId;//ATTR1_DTL_ID , varchar2(4)
  private String attr2DtlId;//ATTR2_DTL_ID , varchar2(4)
  private String attr3DtlId;//ATTR3_DTL_ID , varchar2(4)
  private String childId;//CHILD_ID , varchar2(4)
  private String status;//STATUS , varchar2(4)
  private long transLevel;//TRANS_LEVEL , number(4)
  private long itemLevel;//ITEM_LEVEL , number(4)
  private String packInd;//PACK_IND , varchar2(1)
  private String packSellableInd;//PACK_SELLABLE_IND , varchar2(1)
  private String packOrderableInd;//PACK_ORDERABLE_IND , varchar2(1)
  private String packUom;//PACK_UOM , varchar2(6)
  private double dftCaseSize;//DFT_CASE_SIZE , number(12,2)
  private String primaryRef;// PRIMARY_REF , varchar2(1)
  private String packSize;// PACK_SIZE , varchar2(6)
  private String standardUom;// STANDARD_UOM , varchar2(6)
  private double uomConvFactor;//UOM_CONV_FACTOR , number(18,4)
  private String merchandiseInd;//MERCHANDISE_IND , varchar2(1)
  private String storeOrderMult;//STORE_ORDERR_MULT , varchar2(1)
  private String forecastInd;//FORECAST_IND , varchar2(1)
  private double orginalRetail;//ORGINAL_RETAIL , number(18,4)
  private double manufacturerRecRetail;//MANUFACTURER_REC_RETAIL , number(18,4)
  private Date firstReceivedDate;//FIRST_RECEIVED_DATE , DATE
  private Date lastReceivedDate;//LAST_RECEIVED_DATE , DATE
  private double qtyReceived;//QTY_RECEIVED , number(12,4)
  private String simplePackInd;// SIMPLE_PACK_IND , varchar2(1)
  private String packContainInnerInd;// PACK_CONTAIN_INNER_IND , varchar2(1)
  private String orderAsType;// ORDER_AS_TYPE , varchar2(1)
  private String giftWrapInd;// GIFT_WRAP_IND , varchar2(1)
  private String shipAloneInd;// SHIP_ALONE_IND , varchar2(1)
  private Date createdDate;//CREATED_DATE , DATE
  private String lastUpdId;// LAST_UPD_ID , varchar2(10)
  private Date lastUpdDate;//LAST_UPD_DATE , DATE


  public ItemMaster() {

  }

  public void resetValue(){
    itemId=null;//ITEM_ID, varchar2(25) , <N> , <PK>
    itemName=null;//ITEM_NAME , varchar2(120) , <Y>
    itemDesc = null; //ITEM_DESC , varchar2(120)
    shortDesc = null; //SHORT_DESC , varchar2(20)
    itemAggregateInd = null; //ITEM_AGGREGATE_IND , varchar2(1)
    attr1Id = null; //ATTR1_ID , varchar2(4)
    attr2Id = null; //ATTR2_ID , varchar2(4)
    attr3Id = null; //ATTR3_ID , varchar2(4)
    attr1DtlId = null; //ATTR1_DTL_ID , varchar2(4)
    attr2DtlId = null; //ATTR2_DTL_ID , varchar2(4)
    attr3DtlId = null; //ATTR3_DTL_ID , varchar2(4)
    childId = null; //CHILD_ID , varchar2(4)
    status = null; //STATUS , varchar2(4)
    transLevel = 0; //TRANS_LEVEL , number(4)
    itemLevel = 0; //ITEM_LEVEL , number(4)
    packInd = null; //PACK_IND , varchar2(1)
    packSellableInd = null; //PACK_SELLABLE_IND , varchar2(1)
    packOrderableInd = null; //PACK_ORDERABLE_IND , varchar2(1)
    packUom = null; //PACK_UOM , varchar2(6)
    dftCaseSize = 0; //DFT_CASE_SIZE , number(12,2)
    primaryRef = null; // PRIMARY_REF , varchar2(1)
    packSize = null; // PACK_SIZE , varchar2(6)
    standardUom = null; // STANDARD_UOM , varchar2(6)
    uomConvFactor = 0; //UOM_CONV_FACTOR , number(18,4)
    merchandiseInd = null; //MERCHANDISE_IND , varchar2(1)
    storeOrderMult = null; //STORE_ORDERR_MULT , varchar2(1)
    forecastInd = null; //FORECAST_IND , varchar2(1)
    orginalRetail = 0; //ORGINAL_RETAIL , number(18,4)
    manufacturerRecRetail = 0; //MANUFACTURER_REC_RETAIL , number(18,4)
    firstReceivedDate = null; //FIRST_RECEIVED_DATE , DATE
    lastReceivedDate = null; //LAST_RECEIVED_DATE , DATE
    qtyReceived = 0; //QTY_RECEIVED , number(12,4)
    simplePackInd = null; // SIMPLE_PACK_IND , varchar2(1)
    packContainInnerInd = null; // PACK_CONTAIN_INNER_IND , varchar2(1)
    orderAsType = null; // ORDER_AS_TYPE , varchar2(1)
    giftWrapInd = null; // GIFT_WRAP_IND , varchar2(1)
    shipAloneInd = null; // SHIP_ALONE_IND , varchar2(1)
    createdDate = null; //CREATED_DATE , DATE
    lastUpdId = null; // LAST_UPD_ID , varchar2(10)
    lastUpdDate = null; //LAST_UPD_DATE , DATE
  }
  //===============================GET
  public String getITEM_ID(){
      return this.itemId ;
  }

  public void setITEM_ID(String itemId ){
    this.itemId=itemId ;
  }


  public String getITEM_NAME(){
    return this.itemName  ;
  }

  public void setITEM_NAME(String itemName){
   this.itemName=itemName  ;
  }


  public String getITEM_DESC(){
      return this.itemDesc ;
  }

  public void setITEM_DESC(String itemDesc){
      this.itemDesc=itemDesc ;
  }


  public String getSHORT_DESC(){
      return this.shortDesc  ;
  }

  public void  setSHORT_DESC(String shortDesc){
      this.shortDesc=shortDesc  ;
  }


  public String getITEM_AGGREGATE_IND(){
      return this.itemAggregateInd  ;
  }

  public void setITEM_AGGREGATE_IND(String  itemAggregateInd){
      this.itemAggregateInd=itemAggregateInd  ;
  }


  public String getATTR1_ID(){
      return this.attr1Id  ;
  }

  public void setATTR1_ID(String attr1Id ){
      this.attr1Id =attr1Id ;
  }


  public String getATTR2_ID(){
     return this.attr2Id  ;
 }

 public void setATTR2_ID(String attr2Id  ){
     this.attr2Id =attr2Id ;
 }

 public String getATTR3_ID(){
    return this.attr3Id  ;
 }
 public void setATTR3_ID(String attr3Id ){
    this.attr3Id =attr3Id ;
 }


  public String getATTR1_DTL_ID(){
      return this.attr1DtlId  ;
  }
  public void  setATTR1_DTL_ID(String attr1DtlId){
      this.attr1DtlId=attr1DtlId  ;
  }


  public String getATTR2_DTL_ID(){
      return this.attr2DtlId  ;
  }
  public void setATTR2_DTL_ID(String attr2DtlId){
      this.attr2DtlId =attr2DtlId ;
  }


  public String getATTR3_DTL_ID(){
        return this.attr3DtlId  ;
  }
  public void  setATTR3_DTL_ID(String attr3DtlId){
        this.attr3DtlId  =attr3DtlId;
  }


  public String getCHILD_ID(){
        return this.childId   ;
  }
  public void setCHILD_ID(String childId ){
        this.childId =childId  ;
  }


  public String getSTATUS(){
         return this.status ;
   }
   public  void setSTATUS(String status){
         this.status=status ;
   }


   public long getTRANS_LEVEL(){
         return this.transLevel   ;
   }
   public  long setTRANS_LEVEL(long transLevel){
         return this.transLevel =transLevel  ;
   }


   public long getITEM_LEVEL(){
         return this.itemLevel   ;
   }
   public void  setITEM_LEVEL(long itemLevel ){
         this.itemLevel =itemLevel  ;
   }


   public String getPACK_IND(){
         return this.packInd ;
   }
   public void setPACK_IND(String packInd){
         this.packInd=packInd ;
   }


   public String getPACK_SELLABLE_IND(){
         return this.packSellableInd   ;
   }
   public void setPACK_SELLABLE_IND(String packSellableInd ){
         this.packSellableInd  =packSellableInd ;
   }


   public String getPACK_ORDERABLE_IND(){
         return this.packOrderableInd   ;
   }
   public void  setPACK_ORDERABLE_IND(String packOrderableInd){
         this.packOrderableInd =packOrderableInd  ;
   }


   public String getPACK_UOM(){
         return this.packUom ;
   }
   public void   setPACK_UOM(String packUom){
         this.packUom=packUom ;
   }


   public double getDFT_CASE_SIZE(){
         return this.dftCaseSize  ;
   }
   public  void setDFT_CASE_SIZE(double dftCaseSize ){
         this.dftCaseSize=dftCaseSize  ;
   }


   public String getPRIMARY_REF(){
         return this.primaryRef   ;
   }
   public void   setPRIMARY_REF(String  primaryRef){
         this.primaryRef  =primaryRef ;
   }


   public String getPACK_SIZE(){
       return this.packSize   ;
   }
   public  void setPACK_SIZE(String packSize){
       this.packSize =packSize  ;
   }


   public String getSTANDARD_UOM(){
       return this.standardUom ;
   }
   public void setSTANDARD_UOM(String standardUom){
       this.standardUom =standardUom;
   }


   public double getUOM_CONV_FACTOR(){
       return this.uomConvFactor  ;
   }
   public  void setUOM_CONV_FACTOR(double uomConvFactor ){
       this.uomConvFactor =uomConvFactor ;
   }


   public String getMERCHANDISE_IND(){
       return this.merchandiseInd  ;
   }
   public   void setMERCHANDISE_IND(String merchandiseInd ){
       this.merchandiseInd  =merchandiseInd;
   }


   public String getSTORE_ORDERR_MULT(){
       return this.storeOrderMult   ;
   }
   public void setSTORE_ORDERR_MULT(String storeOrderMult){
       this.storeOrderMult   =storeOrderMult;
   }

   public String getFORECAST_IND(){
       return this.forecastInd ;
   }
   public  void setFORECAST_IND(String  forecastInd){
       this.forecastInd =forecastInd;
   }


   public double getORGINAL_RETAIL(){
       return this.orginalRetail  ;
   }
   public  void setORGINAL_RETAIL(double orginalRetail){
       this.orginalRetail=orginalRetail  ;
   }


   public double getMANUFACTURER_REC_RETAIL(){
       return this.manufacturerRecRetail  ;
   }
   public void   setMANUFACTURER_REC_RETAIL(double manufacturerRecRetail ){
       this.manufacturerRecRetail =manufacturerRecRetail ;
   }


   public Date getFIRST_RECEIVED_DATE(){
       return this.firstReceivedDate    ;
   }
   public  void setFIRST_RECEIVED_DATE(Date firstReceivedDate){
       this.firstReceivedDate =firstReceivedDate   ;
   }


   public Date getLAST_RECEIVED_DATE(){
       return this.lastReceivedDate ;
   }
   public  void setLAST_RECEIVED_DATE(Date lastReceivedDate ){
       this.lastReceivedDate=lastReceivedDate ;
   }


   public double getQTY_RECEIVED(){
       return this.qtyReceived  ;
   }
   public void  setQTY_RECEIVED(double qtyReceived ){
       this.qtyReceived =qtyReceived ;
   }


   public String getSIMPLE_PACK_IND(){
       return this.simplePackInd ;
   }
   public  void setSIMPLE_PACK_IND(String simplePackInd){
       this.simplePackInd =simplePackInd;
   }


   public String getPACK_CONTAIN_INNER_IND(){
       return this.packContainInnerInd   ;
   }
   public  void setPACK_CONTAIN_INNER_IND(String packContainInnerInd){
       this.packContainInnerInd =packContainInnerInd  ;
   }


   public String getORDER_AS_TYPE(){
       return this.orderAsType   ;
   }
   public void   setORDER_AS_TYPE(String orderAsType){
       this.orderAsType  =orderAsType ;
   }


   public String getGIFT_WRAP_IND(){
       return this.giftWrapInd ;
   }
   public void setGIFT_WRAP_IND(String giftWrapInd){
       this.giftWrapInd=giftWrapInd ;
   }


   public String getSHIP_ALONE_IND(){
       return this.shipAloneInd ;
   }
   public void   setSHIP_ALONE_IND(String shipAloneInd ){
       this.shipAloneInd =shipAloneInd;
   }


   public Date getCREATED_DATE(){
       return this.createdDate ;
   }
   public void   setCREATED_DATE(Date createdDate){
       this.createdDate =createdDate;
   }


   public String getLAST_UPD_ID(){
       return this.lastUpdId  ;
   }
   public  void setLAST_UPD_ID(String lastUpdId){
       this.lastUpdId  =lastUpdId;
   }


   public Date getLAST_UPD_DATE(){
       return this.lastUpdDate   ;
   }
   public void   setLAST_UPD_DATE(Date lastUpdDate){
       this.lastUpdDate=lastUpdDate   ;
   }


}
