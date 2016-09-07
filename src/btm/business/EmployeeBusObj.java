package btm.business;
import java.sql.*;
import common.*;
//import btm.sql.Employee.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class EmployeeBusObj {
  Statement stmt = null;
  DataAccessLayer DAL = new DataAccessLayer();
  Utils ut = new Utils();
  String sEmpID = "";
  String sEmpCode = "";
  String sStoreID = "";
  String sFirstName = "";
  String sLastName = "";
  String sSSN = "";
  String sPassword = "";
  String sAddress1 = "";
  String sAddress2 = "";
  String sHomeNo = "";
  String sWorkNo = "";
  String sMobileNo = "";
  String sEmail = "";
  String sCity = "";
  String sCountry = "";
  String sCounty = "";
  String sStartDate = "";
  String sEndDate = "";
  String sBirthday = "";
  String sRow="";
  public EmployeeBusObj(){

  }
  /** insertEmployeeBusObj method - insert into database
   * @author 		Vo Ha Thanh Huy
   * @param 		No
   * @return		No
   */
//  public void insertEmployee(String empId, int storeId, String roleId,
//                             String firstName, String lastName, int ssn,
//                             String passWord,
//                             String address1, String address2, String homeNo,
//                             String workNo, String mobileNo, String email,
//                             String city, String country, String birthDay,Statement stmt) {
//    DAL.getOracleConnect(); //temp
//    ResultSet rs = null;
//    String sql = "insert into BTM_IM_EMPLOYEE (EMP_ID, STORE_ID, ROLE_ID, FIRST_NAME, LAST_NAME, PASSWD, " +
//        "ADDRESS1, ADDRESS2, HOME_NO, WORK_NO, MOBILE_NO, EMAIL, CITY, COUNTRY) " +
//        "values ('" + empId + "'," + storeId + ",'" + roleId + "','" +
//        firstName + "','" + lastName + "','" + passWord + "','" +
//        address1 + "','" + address2 + "','" + homeNo + "','" + workNo + "','" +
//        mobileNo + "','" + email + "','" + city + "','" + country + "')";
//    System.out.println(sql);
//    try {
//      rs = DAL.executeQueries(sql,stmt);
//    }
//    catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//  } //end method

  /** deleteEmployee method - delete into database
   * @author 		Vo Ha Thanh Huy
   * @param 		No
   * @return		No
   */
//  public void deleteEmployee(String empId,Statement stmt) {
//    DAL.getOracleConnect(); //temp
//    ResultSet rs = null;
//    String sql = "delete from BTM_IM_EMPLOYEE where EMP_ID = '" + empId + "'";
//    System.out.println(sql);
//    try {
//      rs = DAL.executeQueries(sql,stmt);
//    }
//    catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//  } //end method

  /** getDataCboPosition method - get ROLE_ID and ROLE_DESC
   * @author 		Vo Ha Thanh Huy
   * @param 		no
   * @return		ResultSet
   */
  public ResultSet getDataCboPosition(Statement stmt) {
    DAL.getOracleConnect(); //temp
    ResultSet rs = null;
    String sSQL = "select ROLE_ID, ROLE_NAME from BTM_IM_ROLE";
    try {
//      System.out.println(sSQL);
      rs = DAL.executeScrollQueries(sSQL,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method

  /** getDataCboStore method - get ROLE_ID and ROLE_DESC
   * @author 		Vo Ha Thanh Huy
   * @param 		no
   * @return		ResultSet
   */
  public ResultSet getDataCboStore(Statement stmt) {
    DAL.getOracleConnect();
    ResultSet rs = null;
    String sSQL = null;
    try {
      sSQL = "select STORE_ID, NAME from BTM_IM_STORE";
//      System.out.println(sSQL);
      rs = DAL.executeScrollQueries(sSQL,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method

  /** getDataListRole method - get ROLE_ID and ROLE_DESC
   * @author 		Vo Ha Thanh Huy
   * @param 		no
   * @return		ResultSet
   */
  public ResultSet getDataAdminEmployee(DataAccessLayer DAL,Statement stmt) {
    ResultSet rs = null;
    String sSQL = null;
    try {
//    System.out.println("select EMP_ID, STORE_ID, ROLE_ID, LAST_NAME, " +
//        "ADDRESS1, HOME_NO  from BTM_IM_EMPLOYEE");
      sSQL =
          "select EMP_ID, STORE_ID, LAST_NAME, ADDRESS1 from BTM_IM_EMPLOYEE";
      rs = DAL.executeScrollQueries(sSQL,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method

  public boolean checkLength(String string, int length) {
    if (string.length() > length) {
      return true;
    }
    return false;
  }

  //Check Employee Code
  public boolean checkEmployeeCode(String sEmp_ID,String sEmployeeCode,DataAccessLayer DAL) {
    boolean bLag = false;
    ResultSet rs = null;
    String sSQL = null;
    try {
      sSQL = "Select EMP_ID from BTM_IM_EMPLOYEE where EMP_CDE = '" + sEmployeeCode + "' and EMP_ID != '" + sEmp_ID + "'";
//      sSQL = "Select * from BTM_IM_EMPLOYEE where EMP_ID = '" + sEmployeeCode + "'";
//      System.out.println(sSQL);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                               TYPE_SCROLL_INSENSITIVE,
                                               ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sSQL,stmt);
      if (rs.isBeforeFirst() || rs.isAfterLast()) {
        bLag = true;
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return bLag;
  }
  //Get data Search
  public ResultSet getDataSearch(String sEmpID1, String sEmpCode1, String sStoreID1,
                          String sFirstName1, String sLastName1,
                          String sSSN1, String sPass1,
                          String sAddress11, String sAddress21,
                          String sHomeNo1, String sWorkNo1, String sMobileNo1,
                          String sEmail1, String sCity1, String sCountry1,
                          String sCounty1, String sTartDate1, String sEndDate1,
                          String sBirthday1,String rowsLimit,DataAccessLayer DAL,Statement stmt){
    ResultSet rs = null;
    String sSQL=new String();
    String sID=new String();
    sSQL="";
    String sql = "Select EMP_ID, EMP_CDE, concat(FIRST_NAME, concat(' ',LAST_NAME)), STORE_ID, HOME_NO, WORK_NO " +
        "from BTM_IM_EMPLOYEE where rownum <=" + rowsLimit + " ";

    if (!sEmpID1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(EMP_ID) like lower('%" + sEmpID1.trim() + "%') ";
    }
    if (!sEmpCode1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(EMP_CDE) like lower('%" + sEmpCode1.trim() + "%') ";
    }
    if (!sStoreID1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(STORE_ID) like lower('%" + sStoreID1.trim() + "%') ";
    }
    if (!sFirstName1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(FIRST_NAME) like lower('%" + sFirstName1.trim() + "%') ";
    }
    if (!sLastName1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(LAST_NAME) like lower('%" + sLastName1.trim() + "%') ";
    }
    if (!sSSN1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(SSN) like lower('%" + sSSN1.trim() + "%') ";
    }
    if (!sPass1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(PASSWD) like lower('%" + sPass1.trim() + "%') ";
    }
    if (!sAddress11.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(ADDRESS1) like lower('%" + sAddress11.trim() + "%') ";
    }
    if (!sAddress21.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(ADDRESS2) like lower('%" + sAddress21.trim() + "%') ";
    }
    if (!sHomeNo1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(HOME_NO) like lower('%" + sHomeNo1.trim() + "%') ";
    }
    if (!sWorkNo1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(WORK_NO) like lower('%" + sWorkNo1.trim() + "%') ";
    }
    if (!sMobileNo1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(MOBILE_NO) like lower('%" + sMobileNo1.trim() + "%') ";
    }
    if (!sEmail1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(EMAIL) like lower('%" + sEmail1.trim() + "%') ";
    }
    if (!sCity1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(CITY) like lower('%" + sCity1.trim() + "%') ";
    }
    if (!sCountry1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(COUNTRY) like lower('%" + sCountry1.trim() + "%') ";
    }
    if (!sCounty1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(COUNTY) like lower('%" + sCounty1.trim() + "%') ";
    }
    if (!sTartDate1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(START_DATE) like lower('%" + sTartDate1.trim() + "%') ";
    }
    if (!sEndDate1.trim().equalsIgnoreCase("")){
      sql = sql + " and lower(END_DATE) like lower('%" + sEndDate1.trim() + "%') ";
    }
    if (!sBirthday1.trim().equalsIgnoreCase("")){
      sql = sql + " and Birthday = to_date('" + sBirthday1.trim() + "','DD/MM/YY') ";
    }

    try {
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }

    return rs;
  }

  //Get datda with Employee ID
  public ResultSet getDataEmployee(String sID, DataAccessLayer DAL,Statement stmt) {
    ResultSet rs = null;
    String sSQL = null;
    try {
      sSQL =
          "Select EMP_ID, EMP_CDE, STORE_ID, FIRST_NAME, LAST_NAME, PASSWD, " +
          "ADDRESS1, ADDRESS2, HOME_NO, WORK_NO, MOBILE_NO, EMAIL, CITY, "+
          "COUNTY, COUNTRY, BIRTHDAY " +
          "from BTM_IM_EMPLOYEE where EMP_ID = '"+ sID + "'";
//      System.out.println(sSQL);
      rs = DAL.executeScrollQueries(sSQL,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method

  //Check Store Code
  public boolean checkStore(String sStoreID1,DataAccessLayer DAL) {
    boolean bLag = false;
    ResultSet rs = null;
    String sSQL = null;
    try {
      sSQL = "select STORE_ID from BTM_IM_STORE where STORE_ID = '" + sStoreID1 + "'";
//      System.out.println(sSQL);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                               TYPE_SCROLL_INSENSITIVE,
                                               ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sSQL,stmt);
      if (rs.isBeforeFirst() || rs.isAfterLast()) {
        bLag = true;
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return bLag;
  }

  public String getEmployeeIdCanInsert(DataAccessLayer DAL) {
      String id = new String();
       try {
         ResultSet rs = DAL.executeScrollQueries("Select EMP_ID from BTM_IM_EMPLOYEE where length(EMP_ID)=13 and  EMP_ID like '" + DAL.getStoreID()+DAL.getHostID() + "%' Order by EMP_ID DESC");
         rs.beforeFirst();
         if (!rs.next()){
           rs.getStatement().close();
           id = DAL.getStoreID()+DAL.getHostID()+"0000001";
           return  id + String.valueOf(ut.getCheckDigitEAN13(id));
         }
         String strTemp1 = rs.getString("EMP_ID");
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

  public void setEmployee(String sEmpID, String sEmpCode, String sStoreID,
                          String sFirstName, String sLastName,
                          String sSSN, String sPass,
                          String sAddress1, String sAddress2,
                          String sHomeNo, String sWorkNo, String sMobileNo,
                          String sEmail, String sCity, String sCountry,
                          String sCounty, String sTartDate, String sEndDate,
                          String sBirthday) {
    this.sEmpID = sEmpID;
    this.sEmpCode = sEmpCode;
    this.sStoreID = sStoreID;
    this.sFirstName = sFirstName;
    this.sLastName = sLastName;
    this.sSSN = sSSN;
    this.sPassword = sPass;
    this.sAddress1 = sAddress1;
    this.sAddress2 = sAddress2;
    this.sHomeNo = sHomeNo;
    this.sWorkNo = sWorkNo;
    this.sMobileNo = sMobileNo;
    this.sEmail = sEmail;
    this.sCity = sCity;
    this.sCountry = sCountry;
    this.sCounty = sCounty;
    this.sStartDate = sStartDate;
    this.sEndDate = sEndDate;
    this.sBirthday = sBirthday;
  }

  public void setEmpID(String sEmpID) {
    this.sEmpID = sEmpID;
  }

  public void setEmpCode(String sEmpCode) {
    this.sEmpCode = sEmpCode;
  }

  public void setStoreID(String sStoreID) {
    this.sStoreID = sStoreID;
  }

  public void setFirstName(String sFirstName) {
    this.sFirstName = sFirstName;
  }

  public void setLastName(String sLastName) {
    this.sLastName = sLastName;
  }

  public void setSSN(String sSSN) {
    this.sSSN = sSSN;
  }

  public void setPassword(String sPass) {
    this.sPassword = sPass;
  }

  public void setAddress1(String sAddress1) {
    this.sAddress1 = sAddress1;
  }

  public void setAddress2(String sAddress2) {
    this.sAddress2 = sAddress2;
  }

  public void setHomeNo(String sHomeNo) {
    this.sHomeNo = sHomeNo;
  }

  public void setWorkNo(String sWorkNo) {
    this.sWorkNo = sWorkNo;
  }

  public void setMobileNo(String sMobileNo) {
    this.sMobileNo = sMobileNo;
  }

  public void setEmail(String sEmail) {
    this.sEmail = sEmail;
  }

  public void setCity(String sCity) {
    this.sCity = sCity;
  }

  public void setCountry(String sCountry) {
    this.sCountry = sCountry;
  }

  public void setCounty(String sCounty) {
    this.sCounty = sCounty;
  }

  public void setStartDate(String sStartDate) {
    this.sStartDate = sStartDate;
  }

  public void setEndDate(String sEndDate) {
    this.sEndDate = sEndDate;
  }

  public void setBirthday(String sBirthday) {
    this.sBirthday = sBirthday;
  }

  public void setRow(String sRow) {
      this.sRow = sRow;
  }

  public String getEmpID() {
    return this.sEmpID;
  }

  public String getEmpCode() {
    return this.sEmpCode;
  }

  public String getStoreID() {
    return this.sStoreID = sStoreID;
  }

  public String getFirstName() {
    return this.sFirstName;
  }

  public String getLastName() {
    return this.sLastName;
  }

  public String getSSN() {
    return this.sSSN = sSSN;
  }

  public String setPassword() {
    return this.sPassword;
  }

  public String getAddress1() {
    return this.sAddress1;
  }

  public String getAddress2() {
    return this.sAddress2;
  }

  public String getHomeNo() {
    return this.sHomeNo;
  }

  public String getWorkNo() {
    return this.sWorkNo;
  }

  public String getMobileNo() {
    return this.sMobileNo;
  }

  public String getEmail() {
    return this.sEmail;
  }

  public String getCity() {
    return this.sCity;
  }

  public String getCountry() {
    return this.sCountry;
  }

  public String getCounty() {
    return this.sCounty;
  }

  public String getStartDate() {
    return this.sStartDate;
  }

  public String getEndDate() {
    return this.sEndDate;
  }

  public String getBirthday() {
    return this.sBirthday;
  }

  public String getRow() {
    return this.sRow;
  }
}
