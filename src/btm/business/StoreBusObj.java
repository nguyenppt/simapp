package btm.business;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Nghi Doan
 * @version 1.0
 */
import java.sql.*;
import common.*;

public class StoreBusObj {
  //Set Data
  String storeID = "";
  String storeName = "";
  String address1 = "";
  String city = "";
  String country = "";
  String managerName = "";
  String phoneNo = "";

  public StoreBusObj() {
  }
  /** checkLength(String string, int length) - this method will check the limit of length
   * @author Nghi Doan
   * @param string
   * @param length
   */
  public boolean checkLength(String string, int length){
    if (string.length() > length){
      return true;
    }
    return false;
  }
  /** getData(String storeID, String storeName, String shortName1, String shortName2, String address1, String address2, String city, String county, String country, String managerName, String phoneNo, String faxNo, String email, int rowsLimit, DataAccessLayer DAL)
   *  - this method return the data from BTM_IM_CUSTOMER table
   *
   * @author Nghi Doan
   * @param storeID
   * @param storeName
   * @param shortName1
   * @param shortName2
   * @param address1
   * @param address2
   * @param city
   * @param county
   * @param country
   * @param managerName
   * @param phoneNo
   * @param faxNo
   * @param email
   * @param rowsLimit
   * @param DAL
   */
  public ResultSet getData(DataAccessLayer DAL){
    ResultSet rs = null;
    String SQL = "SELECT STORE_ID, NAME FROM BTM_IM_STORE ORDER BY NAME ASC";
      try {
        rs = DAL.executeScrollQueries(SQL);
    }catch(Exception ex){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }
    return rs;
  }
//get store address with indicated store id
  public String getStoreAddress1(String storeId, DataAccessLayer DAL){
    String addr = "";
    String SQL ="Select ADDRESS_1 from BTM_IM_STORE where STORE_ID = '"+storeId+"'";
    ResultSet rs = null;
    try{
      rs = DAL.executeScrollQueries(SQL);
      if(rs.next()){
        addr = rs.getString("ADDRESS_1");
      }
    }catch(Exception ex){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }
    return addr;
  }

  public ResultSet getData(String storeID, String storeName, String address1, String city, String country, String managerName, String phoneNo, int rowsLimit, DataAccessLayer DAL,Statement stmt){
    String sql = "select Store_ID, name, manager_name, phone_no " +
        " from BTM_IM_STORE where rownum <=" + rowsLimit;
    if (!storeID.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(store_id) like lower('%" + storeID.trim() + "%') ";
    }
    if (!storeName.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(name) like lower('%" + storeName.trim() + "%') ";
    }
    if (!address1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(address_1) like lower('%" + address1 + "%') ";
    }
    if (!city.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(city) like lower('%" + city + "%') ";
    }
    if (!country.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(country) like lower('%" + country + "%') ";
    }
    if (!managerName.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(manager_name) like lower('%" + managerName + "%') ";
    }
    if (!phoneNo.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(phone_no) like lower('%" + phoneNo + "%') ";
    }
    ResultSet rs = null;
    try{
//      String sql = "select Store_ID, name, manager_name, phone_no, fax_no " +
//          " from BTM_IM_STORE where lower(store_id) like lower('%" + storeID.trim() +
//          "%') and lower(name) like lower('%" + storeName + "%') " +
//          " and lower(short_name1) like lower('%" + shortName1 + "%') " +
//          " and lower(short_name2) like lower('%" + shortName2 + "%') " +
//          " and lower(address_1) like lower('%" + address1 + "%') "  +
//          " and lower(address_2) like lower('%" + address2 + "%') " +
//          " and lower(city) like lower('%" + city + "%') " +
//          " and lower(county) like lower('%" + county + "%') " +
//          " and lower(country) like lower('%" + country + "%') " +
//          " and lower(manager_name) like lower('%" + managerName + "%') "  +
//          " and lower(phone_no) like lower('%" + phoneNo + "%') " +
//          " and lower(fax_no) like lower('%" + faxNo + "%') " +
//          " and lower(email) like lower('%" + email + "%') " +
//          " and rownum <=" + rowsLimit;
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  /** getCurrency(DataAccessLayer DAL) - return data from currency
   * @author Nghi Doan
   * @param DAL
   */
  public ResultSet getCurrency(DataAccessLayer DAL,Statement stmt){
    ResultSet rs = null;
    try{
      String sql = "select curr_id, curr_id from btm_im_currency_cde";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  /** setValue(String storeID, String storeName, String shortName1, String shortName2, String address1, String address2, String city, String county, String country, String managerName, String phoneNo, String faxNo, String email)
   * @author Nghi Doan
   * @param storeID
   * @param storeName
   * @param shortName1
   * @param shortName2
   * @param address1
   * @param address2
   * @param city
   * @param county
   * @param country
   * @param managerName
   * @param phoneNo
   * @param faxNo
   * @param email
   */
  public String getStoreName(DataAccessLayer DAL, String storeId){
    String storeName = "";
    ResultSet rs = null;
    String SQL = "SELECT NAME FROM BTM_IM_STORE WHERE STORE_ID = '"+storeId+"'";
    try{
      rs = DAL.executeScrollQueries(SQL);
      if(rs.next()){
        storeName = rs.getString("NAME");
      }
      rs.getStatement().close();
    }catch(Exception ex){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }
    return storeName;
  }

  public void setValue(String storeID, String storeName, String address1, String city, String country, String managerName, String phoneNo){
    this.storeID = storeID;
    this.storeName = storeName;
    this.address1 = address1;
    this.city = city;
    this.country = country;
    this.managerName = managerName;
    this.phoneNo = phoneNo;
  }
  //Store ID
  public String getStoreID(){
    return this.storeID;
  }
  //Store Name
  public String getStoreName(){
    return this.storeName;
  }
  //Store Name
  public String getAddress1(){
    return this.address1;
  }
  //Store Name
  public String getCity(){
    return this.city;
  }
  //Store Name
  public String getCountry(){
    return this.country;
  }
  //Store Name
  public String getManagerName(){
    return this.managerName;
  }
  //Store Name
  public String getPhoneNo(){
    return this.phoneNo;
  }

}
