package btm.business;

import java.util.Vector;
import java.sql.Statement;
import common.Utils;
import common.DataAccessLayer;

/**
 * <p>Title: Pick List Business Object</p>
 *
 * <p>Description: contains attributes of object Pick List</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: BTM</p>
 *
 * @author: PHUONG NGUYEN
 * @version 1.0
 */
public class PickListBusObj {
  Utils ut = new Utils();
//  Statement stmt = null;
  DataAccessLayer DAL = new DataAccessLayer();
  public String PICK_LIST_ID;
  public String STORE_ID;
  public String STATUS;
  public String TYPE;
  public String USER_ID;
  public String CREATE_DATE;
  public String EXPECTED_DATE;
  public String RECEIPT_DATE;
  public String DECSRIPTION;
  public String REPLEN_GROUP_ID;

  public String ITEM_ID;
  public double QTY_EXPECTED;
  public double QTY_RECEIVED;
  public String UOM;

  public PickListBusObj() {
  }
  public String getPLID(){
    return this.PICK_LIST_ID;
  }

  public void setPLID(String PLID){
    PLID=this.PICK_LIST_ID;
  }

  public void setData(String PLID,String STORE_ID,
                      String TYPE,String STATUS, String Creator,
                      String Create_date, String Expected_Date, String Desc){
    this.PICK_LIST_ID=PLID;
    this.STORE_ID=STORE_ID;
    this.TYPE=TYPE;
    this.STATUS=STATUS;
    this.USER_ID=getUserID(Creator);
    this.CREATE_DATE=Create_date;
    this.EXPECTED_DATE=Expected_Date;
    this.DECSRIPTION=Desc;
  }
  public String getUserID(String userName){
    String userID =null;
    String SQL="select EMP_ID from BTM_IM_EMPLOYEE where EMP_CDE='"+userName+"'";
    try{
//      stmt=DAL.getConnection().createStatement(ResultSet.);
    }catch(Exception eh){

    }
    return userID;
  }

//  public void setData(String PLID, String storeName, String suppName, String orderDate, String receiptDate,String status,String desc,int rows, Vector vData, Vector vStatus, int rowSelected) {
//    this.PICK_LIST_ID = PLID;
//    this.store_Name = storeName;
//    this.supp_Name = suppName;
//    this.order_Date = orderDate;
//    this.receipt_Date = receiptDate;
//    this.status = status;
//    this.desc = desc;
//    this.rowlimit = rows;
//    this.vData = vData;
//    this.vStatus = vStatus;
//    this.rowSelected = rowSelected;
// }
}
