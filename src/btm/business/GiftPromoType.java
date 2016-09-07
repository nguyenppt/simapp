
package btm.business;

import java.sql.*;
import common.*;
import java.util.*;

/**
 * <p>Title:  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//======================== Gift Promo Code ===================//
public class GiftPromoType {
  private String giftPromoID = ""; // id
  private String minRevenue = ""; //min revenue for trans
  private String maxRevenue = ""; //max revenue for trans
  private String totalTrans = ""; //total trans on Promo campagne
  private String totalGift = ""; //total gift on Promo campagne
  private Vector vGiftList = new Vector(); // list of gift
  private Vector vTransPos = new Vector(); // trans that win
  private String status = "";

//  private Vector vGetUPC = new Vector();
//  private Vector vBuyAmount = new Vector();
//  private Vector vGetAmount = new Vector();
//  private Vector vTable = new Vector();
//  private boolean bEditable1 = true;
//
//  private String strBuyUPCSelect = new String();
//  private String strGetUPCSelect = new String();
  private String startDate = new String();
  private String endDate = new String();

  public GiftPromoType() {
  }

  public GiftPromoType( String giftPromoID,
                        String minRevenue, String maxRevenue, String totalTrans,
                         String totalGift,Vector  vGiftList, Vector vTransPos,String startDate,String endDate,String status) {
    this.giftPromoID = giftPromoID;

    this.minRevenue = minRevenue;
    this.maxRevenue = maxRevenue;
    this.totalTrans = totalTrans;
    this.totalGift = totalGift;
    this.vGiftList = vGiftList;
    this.vTransPos = vTransPos;
    this.startDate=startDate;
    this.endDate=        endDate;
    this.status = status;
  }

//  public String getGetAmountText() {
//    return strGetUPCSelect;
//  }
//
//  public void setGetAmountText(String Text) {
//    strGetAmountText = Text;
//  }
//
  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
//
//  public String getBuyUPCSelect() {
//    return strBuyUPCSelect;
//  }
//
//  public void setBuyUPCSelect(String Select) {
//    strBuyUPCSelect = Select;
//  }
//
//  public String getGetUPCSelect() {
//    return strGetUPCSelect;
//  }
//
//  public void setGetUPCSelect(String Select) {
//    strGetUPCSelect = Select;
//  }
//
//  public boolean getEdit() {
//    return bEditable1;
//  }
//
//  public void setEdit(boolean bFlag) {
//    bEditable1 = bFlag;
//  }

  public String getGiftPromoID() {
    return giftPromoID;
  }

  public void setGiftPromoID(String ID) {
    giftPromoID = ID;
  }

  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  public String getMinRevenue() {
    return minRevenue;
  }

  public void setMinRevenue(String minRevenue) {
    this.minRevenue = minRevenue;
  }

  public String getMaxRevenue() {
    return maxRevenue;
  }

  public void setMaxRevenue(String maxRevenue) {
    this.maxRevenue = maxRevenue;
  }

  public String getTotalTrans() {
    return totalTrans;
  }

  public void setTotalTrans(String totalTrans) {
    this.totalTrans = totalTrans;
  }

  public String getTotalGift() {
    return totalGift;
  }

  public void setTotalGift(String totalGift) {
    this.totalGift = totalGift;
  }

  public Vector getVTransPos() {
    return vTransPos;
  }

  public void setVTransPos(Vector vTransPos) {
    this.vTransPos = vTransPos;
  }

  public Vector getVGiftList() {
    return vGiftList;
  }

  public void setVGiftList(Vector vGiftList) {
    this.vGiftList = vGiftList;
  }

//  public Vector getGetUPC() {
//    return vGetUPC;
//  }

//  public void setGetUPC(Vector GetUPC) {
//    vGetUPC = GetUPC;
//  }
//
//  public Vector getBuyAmount() {
//    return vBuyAmount;
//  }
//
//  public void setBuyAmount(Vector BuyAmount) {
//    vBuyAmount = BuyAmount;
//  }
//
//  public Vector getGetAmount() {
//    return vGetAmount;
//  }
//
//  public void setGetAmount(Vector GetAmount) {
//    vGetAmount = GetAmount;
//  }
//
//  public Vector getTable() {
//    return vTable;
//  }
//
//  public void setTable(Vector Table) {
//    vTable = Table;
//  }
}
