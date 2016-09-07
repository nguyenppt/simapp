package btm.business;

import java.sql.*;
import common.*;
import java.util.*;
/**
 * <p>Title: Purchase Order Business  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author : Vinh Le
 * @version 1.0
 */
public class PurchaseOrderBusObj {
   Utils ut = new Utils();
//   Statement stmt = null;
   public static final int LEN_PO_ID = 13;
   public static final int LEN_UPC_ID = 13;
   public String po_ID ;
   public String store_Name ;
   public String supp_Name ;
   public String order_Date;
   public String receipt_Date;
   public String status;
   public String desc;
   public int rowlimit;
   public Vector vData ;
   public Vector vStatus ;
   public int rowSelected;

  public PurchaseOrderBusObj() {
  }
  public void setPOID(String poID){
    this.po_ID = poID;
  }
  public String getPOID(){
    return this.po_ID;
  }
  public void setData(String poID, String storeName, String suppName, String orderDate, String receiptDate,String status,String desc,int rows, Vector vData, Vector vStatus, int rowSelected) {
    this.po_ID = poID;
    this.store_Name = storeName;
    this.supp_Name = suppName;
    this.order_Date = orderDate;
    this.receipt_Date = receiptDate;
    this.status = status;
    this.desc = desc;
    this.rowlimit = rows;
    this.vData = vData;
    this.vStatus = vStatus;
    this.rowSelected = rowSelected;
 }

}
