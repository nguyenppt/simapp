package btm.business;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ItemBusObject {
  String itemID = "";
  String itemName = "";
  String artNo = "";
  String supplierID = "";
  String supplierName = "";
  String createdDate = "";
  String searchLimit = "";
  int status = 0;
  public ItemBusObject() {
  }
  public void setData(String itemID, String itemName, String artNo, String supplierID,
               String supplierName, String createdDate, int status, String searchLimit){
    this.itemID = itemID;
    this.itemName = itemName;
    this.artNo = artNo;
    this.supplierID = supplierID;
    this.supplierName = supplierName;
    this.createdDate = createdDate;
    this.searchLimit = searchLimit;
    this.status = status;
  }
  public String getItemID(){
    return this.itemID;
  }
  public String getItemName(){
    return this.itemName;
  }
  public String getArtNo(){
    return this.artNo;
  }
  public String getSupplierID(){
    return this.supplierID;
  }
  public String getSupplierName(){
    return this.supplierName;
  }
  public String getCreatedDate(){
    return this.createdDate;
  }
  public String getSearchLimit(){
    return this.searchLimit;
  }
  public int getStatus(){
    return this.status;
  }
}