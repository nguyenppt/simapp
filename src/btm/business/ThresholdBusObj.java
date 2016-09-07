
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

public class ThresholdBusObj {
private String thresholdID = "";
private String transType = "";
private String status = "";
private String thresholdQty = "";
private String thresholdAmt = "";
  private String discountType = "";
  private String discountAmt = "";

private String strPromotion = "";
private String strDesc = "";
private Vector vBuyUPC = new Vector();
private Vector vGetUPC = new Vector();
private Vector vBuyAmount = new Vector();
private Vector vGetAmount = new Vector();
private Vector vTable = new Vector();
private boolean bEditable1 = true;

private String strBuyUPCSelect = new String();
private String strGetUPCSelect = new String();
private String strBuyAmountText = new String();
private String strGetAmountText = new String();

public ThresholdBusObj() {
}

public ThresholdBusObj(boolean Edit, String ThresholdID, String BuyType,
                      String GetType, String Promotion, String Description,
                      Vector BuyUPC, String BuyUPCSelect, Vector BuyAmount,
                      String BuyAmountText, Vector GetUPC,
                      String GetUPCSelect, Vector GetAmount,
                      String GetAmountText, Vector Table) {
  bEditable1 = Edit;
  thresholdID = ThresholdID;
  thresholdQty = BuyType;
  thresholdAmt = GetType;
  strPromotion = Promotion;
  strDesc = Description;
  vBuyUPC = BuyUPC;
  vGetUPC = GetUPC;
  vBuyAmount = BuyAmount;
  vGetAmount = GetAmount;
  vTable = Table;

  //Select
  strBuyUPCSelect = BuyUPCSelect;
  strGetUPCSelect = GetUPCSelect;
  strBuyAmountText = BuyAmountText;
  strGetAmountText = GetAmountText;
}

public String getGetAmountText() {
  return strGetUPCSelect;
}

public void setGetAmountText(String Text) {
  strGetAmountText = Text;
}

public String getBuyAmountText() {
  return strBuyUPCSelect;
}

public void setBuyAmountText(String Text) {
  strBuyAmountText = Text;
}

public String getBuyUPCSelect() {
  return strBuyUPCSelect;
}

public void setBuyUPCSelect(String Select) {
  strBuyUPCSelect = Select;
}

public String getGetUPCSelect() {
  return strGetUPCSelect;
}

public void setGetUPCSelect(String Select) {
  strGetUPCSelect = Select;
}

public boolean getEdit() {
  return bEditable1;
}

public void setEdit(boolean bFlag) {
  bEditable1 = bFlag;
}

public String getThresholdID() {
  return thresholdID;
}

public void setThresholdID(String ID) {
  thresholdID = ID;
}

public String getTransType() {
  return transType;
}
public void setTransType(String transType) {
  this.transType = transType;
}
public String getStatus() {
  return status;
}
public void setStatus(String status) {
  this.status = status;
}

public String getThresholdQty() {
  return thresholdQty;
}

public void setThresholdQty(String thresholdQty) {
  this.thresholdQty = thresholdQty;
}

public String getThresholdAmt() {
  return thresholdAmt;
}

public void setThresholdAmt(String thresholdAmt) {
  this.thresholdAmt = thresholdAmt;
}

public String getDiscountType() {
  return discountType;
}

public void setDiscountType(String discountType) {
  this.discountType = discountType;
}
public String getDiscountAmt() {
  return discountAmt;
}

public void setDiscountAmt(String discountAmt) {
  this.discountAmt = discountAmt;
}

public String getPromotion() {
  return strPromotion;
}

public void setPromotion(String ID) {
  strPromotion = ID;
}

public String getDescription() {
  return strDesc;
}

public void setDescription(String Desc) {
  strDesc = Desc;
}

public Vector getBuyUPC() {
  return vBuyUPC;
}

public void setBuyUPC(Vector BuyUPC) {
  vBuyUPC = BuyUPC;
}

public Vector getGetUPC() {
  return vGetUPC;
}

public void setGetUPC(Vector GetUPC) {
  vGetUPC = GetUPC;
}

public Vector getBuyAmount() {
  return vBuyAmount;
}

public void setBuyAmount(Vector BuyAmount) {
  vBuyAmount = BuyAmount;
}

public Vector getGetAmount() {
  return vGetAmount;
}

public void setGetAmount(Vector GetAmount) {
  vGetAmount = GetAmount;
}

public Vector getTable() {
  return vTable;
}

public void setTable(Vector Table) {
  vTable = Table;
}
}
