package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author
 * @version 1.0BTM_POS_ITEM_LOC_SOH
 */

public class Customer {
    public static final int LEN_CUST_ID = 12;
    public static final int LEN_CUST_NAME = 120;
    public static final int LEN_CUST_ABB_NAME=120;
    public static final int LEN_CUST_CONTACT_NAME = 120;
    public static final int LEN_CUST_ADDRESS = 150;
    public static final int LEN_CUST_PHONE = 15;
    public static final int LEN_CUST_EMAIL = 120;
    public static final int LEN_CUST_FAX = 15;
    public static final int LEN_CUST_CITY = 50;
    public static final int LEN_CUST_COUNTY = 50;
    public static final int LEN_CUST_COUNTRY = 50;
    public static final int LEN_CUST_COMMENT = 250;
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
    String sBirthday = "";
    String sStoreID = "";
    public Customer() {
    }
    public Customer(String sCustId,String sCustType,String sFirstName,String sLastName,String sContactName,String sAddress1,String sAddress2,String sHomePhone,String sWorkPhone,String sCellPhone,String sFax1,String sFax2,String sEmail,String sCountry,String sCounty,String sCity,String sComment, String sBirthday,String sStoreID){
      this.sCustId = sCustId;
      this.sCustType = sCustType;
      this.sFirstName = sFirstName;
      this.sLastName = sLastName;
      this.sContactName = sContactName;
      this.sAddress1 = sAddress1;
      this.sAddress2 = sAddress2;
      this.sHomePhone = sHomePhone;
      this.sWorkPhone = sWorkPhone;
      this.sCellPhone = sCellPhone;
      this.sEmail = sEmail;
      this.sFax1 = sFax1;
      this.sFax2 = sFax2;
      this.sCity = sCity;
      this.sComment = sComment;
      this.sCountry = sCountry;
      this.sCounty = sCounty;
      this.sBirthday = sBirthday;
      this.sStoreID=sStoreID;
    }
    //Get Data
    public String getStoreID(){
     return this.sStoreID;
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
    public String getBirthday(){
      return this.sBirthday;
    }
    //Set Data
    public void setCustID(String sID){
      this.sCustId = sID;
    }
    public void setCustType(String sType){
      this.sCustType=sType;
    }
    public void setFirstName(String sFirstName){
      this.sFirstName=sFirstName;
    }
    public void setLastName(String sLastName){
      this.sLastName=sLastName;
    }
    public void setContactName(String sContactName){
      this.sContactName = sContactName;
    }
    public void setAddress1(String sAddress1){
      this.sAddress1=sAddress1;
    }
    public void setAddress2(String sAddress2){
      this.sAddress2=sAddress2;
    }
    public void setHomePhone(String sPhone){
      this.sHomePhone=sPhone;
    }
    public void setWorkPhone(String sPhone){
      this.sWorkPhone=sPhone;
    }
    public void setCellPhone(String sPhone){
      this.sCellPhone=sPhone;
    }
    public void setEmail(String sEmail){
      this.sEmail=sEmail;
    }
    public void setFax1(String sFax1){
      this.sFax1=sFax1;
    }
    public void setFax2(String sFax2){
      this.sFax2 = sFax2;
    }
    public void setCity(String sCity){
      this.sCity=sCity;
    }
    public void setComment(String sComment){
      this.sComment=sComment;
    }
    public void setCountry(String sCountry){
      this.sCountry=sCountry;
    }
    public void setCounty(String sCounty){
      this.sCounty=sCounty;
    }

    public void setBirthday(String sBirthday) {
      this.sBirthday = sBirthday;
    }
    public void setStoreID(String sStoreID) {
     this.sStoreID = sStoreID;
   }

}