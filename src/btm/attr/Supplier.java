package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class Supplier {
    public static final int LEN_SUP_ID = 6;
    public static final int LEN_SUP_NAME = 120;
    public static final int LEN_SUP_ABBRE_NAME = 20;
    public static final int LEN_SUP_CONTACT_NAME = 120;
    public static final int LEN_SUP_CONTACT_PHONE = 15;
    public static final int LEN_SUP_CONTACT_FAX = 15;
    public static final int LEN_SUP_CONTACT_PAGER = 15;
    public static final int LEN_SUP_STATUS = 1;
    public static final int LEN_SUP_CURR_CDE = 6;
    public static final int LEN_SUP_RETURN_ALLOW_IND = 1;
    public static final int LEN_SUP_RETURN_AUTH_REQ = 1;
    public static final int LEN_SUP_RETURN_MIN_DOL_AMT = 12;
    public static final int LEN_SUP_RETURN_COURIER = 50;
    public static final int LEN_SUP_FREIGHT_CHARGE_IND = 1;
    public static final int LEN_SUP_VAT_REGION = 4;
    public static final int LEN_SUP_INVC_PAY_LOC = 1;
    public static final int LEN_SUP_INVC_RECEIVE_LOC = 1;

  //************* GET FROM TABLE <BTM_IM_ATTR> *****************
  private String SUPP_ID;
  private String SUPP_NAME;
  //************* GET FROM TABLE <BTM_IM_ATTR> *****************

  public Supplier() {
  }

 public Supplier(String SUPP_ID,String SUPP_NAME) {
   this.SUPP_ID = SUPP_ID ;
   this.SUPP_NAME = SUPP_NAME ;
 }

 //set and get
 public String getSUPP_ID(){
     return SUPP_ID;
 }
 public void setSUPP_ID(String SUPP_ID){
   this.SUPP_ID = SUPP_ID ;
 }

 public String getSUPP_NAME(){
     return SUPP_NAME;
 }
 public void setSUPP_NAME(String SUPP_NAME){
   this.SUPP_NAME = SUPP_NAME ;
 }


}