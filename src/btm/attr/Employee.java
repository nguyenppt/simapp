package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Loan vo
 * @version 1.0
 */

public class Employee {
  public static final int LEN_EMP_ID = 10;
  public static final int LEN_EMP_CDE = 10;
  public static final int LEN_EMP_STORE_ID = 6;
  public static final int LEN_EMP_FIRST_NAME = 120;
  public static final int LEN_EMP_LAST_NAME = 120;
  public static final int LEN_EMP_PASSWORD = 40;
  public static final int LEN_EMP_ADDRESS = 150;
  public static final int LEN_EMP_PHONE = 15;
  public static final int LEN_EMP_EMAIL = 125;
  public static final int LEN_EMP_COUNTRY = 50;

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
  public static String simEmpID = "";

  public Employee() {
  }

  public Employee(String sEmpID, String sEmpCode, String sStoreID,
                  String sFirstName, String sLastName,
                  String sSSN, String sPass,
                  String sAddress1, String sAddress2,
                  String sHomeNo, String sWorkNo, String sMobileNo,
                  String sEmail, String sCity, String sCountry, String sCounty,
                  String sTartDate, String sEndDate,
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

  public String getEmpID() {
    return this.sEmpID = sEmpID;
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

  public String getPassword() {
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
//=======
  public static String getEmpID1() {
    return simEmpID;
  }

  public static void setEmpID1(String empID) {
    simEmpID = empID;
  }

//>>>>>>> 1.3
}