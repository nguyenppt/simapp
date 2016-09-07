package btm.business;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.sql.*;
import common.*;

public class CustomerBusObj {
  Utils ut = new Utils();
  String sCustId = "";
  String sCustType = "";
  String sFirstName = "";
  String sLastName = "";
  String sContactName = "";
  String sAddress1 = "";
  String sAddress2 = "";
  String sHomePhone = "";
  String sWorkPhone = "";
  String sCellPhone = "";
  String sFax1 = "";
  String sFax2 = "";
  String sEmail = "";
  String sCountry = "";
  String sCounty = "";
  String sCity = "";
  String sComment = "";
  String sRow = "";
  String sBirthday = "";

  public CustomerBusObj() {
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
  /** getData(String custName, String address1, int rowsLimit, DataAccessLayer DAL) - this method return the data from BTM_IM_CUSTOMER table
   * @author Nghi Doan
   * @param custName
   * @param address1
   * @param rowsLimit
   * @param DAL
   */
  public ResultSet getData(String custName, String address1, int rowsLimit, DataAccessLayer DAL,Statement  stmt){
    ResultSet rs = null;
    try{
//      String sql = "select cust_id, cust_name, cust_abb_name, address1, address2, " +
//          " home_no, work_no, mobile_no, email, " +
//          " city, county, country, fax1, fax2, cust_comment, created_date " +
//          " from BTM_IM_CUSTOMER where lower(cust_name) like lower('%" + custName.trim() +
//          "%') and lower(address1) like lower('%" + address1 + "%') and rownum <=" + rowsLimit;
      String sql = "select cust_id, first_name || ' ' || last_name as cust_name, address1, address2, " +
          " home_no, work_no, mobile_no, email, " +
          " city, county, country, fax1, fax2, cust_comment, created_date " +
          " from BTM_IM_CUSTOMER where lower(first_name) like lower('%" + custName.trim() +
          "%') and lower(address1) like lower('%" + address1 + "%') and rownum <=" + rowsLimit;

//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  public ResultSet getCustomerId(DataAccessLayer DAL,String OrderBy){
    ResultSet rs = null;
    try{
      String sql = "Select CUST_ID from BTM_IM_CUSTOMER Order by CUST_ID "+OrderBy;
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  public ResultSet getDataSearch(String sCustId,int sCustType,String sFirstName,String sLastName,
                                 String sContactName,String sAddress1,String sHomePhone,String sWorkPhone,String sCellPhone,
                                 String sCountry,String sCity,DataAccessLayer DAL,String rownum, String sDate, Statement stmt ){
    ResultSet rs = null;
    String sSQL=new String();
    String sID=new String();
    sSQL="";

    if (!sCustId.equals("")){
//      if (sCustId.length()<10){
//        sID =  ut.getYeroCode(Integer.parseInt(sCustId),10);
//      }else{
//        sID = sCustId;
//      }
      sSQL = " lower(CUST_ID) like lower('%"+ sCustId + "%')";
    }

    if (!sFirstName.equals("")){
      if (sSQL.equals("")){
        sSQL = " lower(FIRST_NAME) like lower('%" + sFirstName + "%')";
      }else{
        sSQL = sSQL + " and lower(FIRST_NAME) like lower('%" + sFirstName + "%')";
      }
    }

    if (!sLastName.equals("")){
      if (sSQL.equals("")){
        sSQL = " lower(LAST_NAME) like lower('%" + sLastName + "%')";
      }else{
        sSQL = sSQL + " and lower(LAST_NAME) like lower('%" + sLastName + "%')";
      }
    }

    if (!sContactName.equals("")){
      if (sSQL.equals("")){
        sSQL = " lower(CONTACT_NAME) like lower('%" + sContactName + "%')";
      }else{
        sSQL = sSQL + " and lower(CONTACT_NAME) like lower('%" + sContactName + "%')";
      }
    }

    if (!sAddress1.equals("")){
      if (sSQL.equals("")){
        sSQL = " lower(ADDRESS1) like lower('%" + sAddress1 + "%')";
      }else{
        sSQL = sSQL + " and lower(ADDRESS1) like lower('%" + sAddress1 + "%')";
      }
    }

    if (!sHomePhone.equals("")){
      if (sSQL.equals("")){
        sSQL = " lower(HOME_NO) like lower('%" + sHomePhone + "%')";
      }else{
        sSQL = sSQL + " and lower(HOME_NO) like lower('%" + sHomePhone + "%')";
      }
    }

    if (!sWorkPhone.equals("")){
      if (sSQL.equals("")){
        sSQL = " lower(WORK_NO) like lower('%" + sWorkPhone + "%')";
      }else{
        sSQL = sSQL + " and lower(WORK_NO) like lower('%" + sWorkPhone + "%')";
      }
    }

    if (!sCellPhone.equals("")){
      if (sSQL.equals("")){
        sSQL = " lower(MOBILE_NO) like lower('%" + sCellPhone + "%')";
      }else{
        sSQL = sSQL + " and lower(MOBILE_NO) like lower('%" + sCellPhone + "%')";
      }
    }

    if (!sCity.equals("")){
      if (sSQL.equals("")){
        sSQL = " lower(CITY) like lower('%" + sCity + "%')";
      }else{
        sSQL = sSQL + " and lower(CITY) like lower('%" + sCity + "%')";
      }
    }

    if (!sCountry.equals("")){
      if (sSQL.equals("")){
        sSQL = " lower(COUNTRY) like lower('%" + sCountry + "%')";
      }else{
        sSQL = sSQL + " and lower(COUNTRY) like lower('%" + sCountry + "%')";
      }
    }

    if (!sDate.equals("")) {
      if (sSQL.equals("")) {
        sSQL = " Birthday = to_date('" + sDate.trim() + "','DD/MM/YY')";
      }
      else {
        sSQL = sSQL + " and Birthday = to_date('" + sDate.trim() + "','DD/MM/YY') ";
      }
    }

    if (sCustType == 0){
      if (sSQL.equals("")){
        sSQL = " CUST_FLAG = '0'";
      }else{
        sSQL = sSQL + " and CUST_FLAG = '0'";
      }
    }else if(sCustType == 1){
      if (sSQL.equals("")){
        sSQL = " CUST_FLAG = '1'";
      }else{
        sSQL = sSQL + " and CUST_FLAG = '1'";
      }
    }else if(sCustType == 2){
      if (sSQL.equals("")){
        sSQL = " CUST_FLAG = '2'";
      }else{
        sSQL = sSQL + " and CUST_FLAG = '2'";
      }
    }else if(sCustType == 3){
      if (sSQL.equals("")){
        sSQL = " CUST_FLAG = '3'";
      }else{
        sSQL = sSQL + " and CUST_FLAG = '3'";
      }
    }

    if (sSQL.equals("")){
      if (sCustType == 0){
        sSQL = "select CUST_ID, concat(FIRST_NAME,concat(' ',LAST_NAME)), ADDRESS1,BIRTHDAY,HOME_NO,MOBILE_NO from BTM_IM_CUSTOMER where rownum <=" + rownum + " order by to_char(BIRTHDAY,'MM/DD') ";
      }else{
        sSQL = "select CUST_ID, FIRST_NAME, ADDRESS1,BIRTHDAY,HOME_NO,MOBILE_NO from BTM_IM_CUSTOMER where rownum <=" + rownum + " order by to_char(BIRTHDAY,'MM/DD') ";
      }
    }else{
      if (sCustType == 0){
        sSQL = "select CUST_ID, concat(FIRST_NAME,concat(' ',LAST_NAME)), ADDRESS1,BIRTHDAY,HOME_NO,MOBILE_NO from BTM_IM_CUSTOMER where" + sSQL + " and rownum <=" + rownum + " order by to_char(BIRTHDAY,'MM/DD') ";
      }else{
        sSQL = "select CUST_ID, FIRST_NAME, ADDRESS1,BIRTHDAY,HOME_NO,MOBILE_NO from BTM_IM_CUSTOMER where" + sSQL + " and rownum <=" + rownum + " order by to_char(BIRTHDAY,'MM/DD') ";
      }
    }

    try{
//      System.out.println("CustomerBusObj_getDataSearch:"+sSQL);
      rs = DAL.executeScrollQueries(sSQL,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return rs;
  }
//get customer Type of a certain customer
  long getCustType(String custID, DataAccessLayer DAL){
    long custType =-1;
    String SQL = "SELECT CUST_FLAG FROM BTM_IM_CUSTOMER WHERE CUST_ID='"+custID+"'";
    ResultSet rs = null;
    try{
      rs = DAL.executeQueries(SQL);
      if(rs.next()){
        custType = Long.parseLong(rs.getString("CUST_FLAG"));
      }
      rs.getStatement().close();
    }catch(Exception ex){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      }
    return custType;
  }

  //get customer FullName of a certain customer
    String getCustName(String custID, DataAccessLayer DAL){
      String custName ="";
      String SQL = "SELECT FIRST_NAME,LAST_NAME FROM BTM_IM_CUSTOMER WHERE CUST_ID='"+custID+"'";
      ResultSet rs = null;
      try{
        rs = DAL.executeQueries(SQL);
        if(rs.next()){
          custName = rs.getString("FIRST_NAME");
          custName+= rs.getString("LAST_NAME");
        }
        rs.getStatement().close();
      }catch(Exception ex){
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(ex);
        }
      return custName;
  }
  public void setValueSearch(String sCustId,String sCustType,String sFirstName,String sLastName,
                             String sContactName,String sAddress1,String sHomePhone,
                             String sWorkPhone,String sCellPhone,String sCountry,String sCity,String sRow, String sBirthday){
    this.sCustId = sCustId;
    this.sCustType = sCustType;
    this.sFirstName = sFirstName;
    this.sLastName = sLastName;
    this.sContactName = sContactName;
    this.sAddress1 = sAddress1;
    this.sHomePhone = sHomePhone;
    this.sWorkPhone = sWorkPhone;
    this.sCellPhone = sCellPhone;
    this.sCity = sCity;
    this.sCountry = sCountry;
    this.sRow = sRow;
    this.sBirthday = sBirthday;
  }
  public String getCustID(){
    return this.sCustId;
  }
  public String getCustType(){
    return this.sCustType;
  }
  public String getFirstName(){
    return this.sFirstName;
  }
  public String getLastName(){
    return this.sLastName;
  }
  public String getContactName(){
    return this.sContactName;
  }
  public String getAddress1(){
    return this.sAddress1;
  }
  public String getAddress2(){
    return this.sAddress2;
  }
  public String getHomePhone(){
    return this.sHomePhone;
  }
  public String getWorkPhone(){
    return this.sWorkPhone;
  }
  public String getCellPhone(){
    return this.sCellPhone;
  }
  public String getEmail(){
    return this.sEmail;
  }
  public String getFax1(){
    return this.sFax1;
  }
  public String getFax2(){
    return this.sFax2 = sFax2;
  }
  public String getCity(){
    return this.sCity;
  }
  public String getComment(){
    return this.sComment;
  }
  public String getCountry(){
    return this.sCountry;
  }
  public String getCounty(){
    return this.sCounty;
  }

  public String getRow() {
    return this.sRow;
  }

  public String getBirthday() {
    return this.sBirthday;
  }
  public String getCustomerIdCanInsertPOS(DataAccessLayer DAL){
     String id = new String();
      try {
        ResultSet rs = DAL.executeScrollQueries("Select CUST_ID from BTM_POS_CUSTOMER where CUST_ID like '" + DAL.getStoreID()+DAL.getHostID() + "%' Order by CUST_ID DESC");
        rs.beforeFirst();
        if (!rs.next()){
          rs.getStatement().close();
          return DAL.getStoreID()+DAL.getHostID()+"0000001";
        }
        String strTemp1 = rs.getString("CUST_ID");
        strTemp1 = strTemp1.substring(1,strTemp1.length());
        long iTemp = Long.parseLong(strTemp1)+1;
        //Add yero
        String st =null;
        int pos1=0;
        int pos2=0;
        st=ut.addYeroCode(7) + iTemp;
        pos1=st.length()- 7;
        pos2=st.length();
        if (pos1 < 0) {
          st = String.valueOf(iTemp).toString();
          return st;
        }
        st=st.substring(pos1, pos2);
        id=DAL.getStoreID()+DAL.getHostID()+st;
        rs.getStatement().close();
      }
      catch(Exception ex) {
        ex.printStackTrace();
      }

     return id;
    }

  public String getCustomerIdCanInsert(DataAccessLayer DAL){
   String id = new String();
    try {
      ResultSet rs = DAL.executeScrollQueries("Select CUST_ID from BTM_IM_CUSTOMER where CUST_ID like '" + DAL.getStoreID()+DAL.getHostID() + "%' Order by CUST_ID DESC");
      rs.beforeFirst();
      if (!rs.next()){
        rs.getStatement().close();
        id = DAL.getStoreID()+DAL.getHostID()+"0000001";
        return  id + String.valueOf(ut.getCheckDigitEAN13(id));
      }
      String strTemp1 = rs.getString("CUST_ID");
      strTemp1 = strTemp1.substring(1,strTemp1.length()-1);
      long iTemp = Long.parseLong(strTemp1)+1;
      //Add yero
      String st =null;
      int pos1=0;
      int pos2=0;
      st=ut.addYeroCode(7) + iTemp;
      pos1=st.length()- 7;
      pos2=st.length();
      if (pos1 < 0) {
        st = String.valueOf(iTemp).toString();
        return st;
      }
      st=st.substring(pos1, pos2);
      id=DAL.getStoreID()+DAL.getHostID()+st;
      id+=String.valueOf(ut.getCheckDigitEAN13(id));
      rs.getStatement().close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }

   return id;
  }
}
