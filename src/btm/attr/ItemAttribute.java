package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class ItemAttribute {
  //************* GET FROM TABLE <BTM_IM_ATTR> *****************
  private String ATTR_DL_ID;
  private String ATTR_DL_DESC;
  private String PROD_GROUP_ID;
  //************* GET FROM TABLE <BTM_IM_ATTR> *****************

  public ItemAttribute() {
  }

//  public ItemAttribute(String ATTR_DL_ID,String ATTR_DL_DESC) {
//    this.ATTR_DL_ID = ATTR_DL_ID ;
//    this.ATTR_DL_DESC = ATTR_DL_DESC ;
//  }
 public ItemAttribute(String ATTR_DL_ID,String ATTR_DL_DESC, String PROD_GROUP_ID) {
   this.ATTR_DL_ID = ATTR_DL_ID ;
   this.ATTR_DL_DESC = ATTR_DL_DESC ;
   this.PROD_GROUP_ID = PROD_GROUP_ID;
 }

  //set and get
  public String getATTR_DL_ID(){
      return ATTR_DL_ID;
  }
  public void setATTR_DL_ID(String ATTR_DL_ID){
    this.ATTR_DL_ID = ATTR_DL_ID ;
  }

  public String getATTR_DL_DESC(){
      return ATTR_DL_DESC;
  }
  public void setATTR_DL_DESC(String ATTR_DL_DESC){
    this.ATTR_DL_DESC = ATTR_DL_DESC ;
  }

  public String getPROD_GROUP_ID(){
    return PROD_GROUP_ID;
  }
  public void setPROD_GROUP_ID(String PROD_GROUP_ID){
    this.PROD_GROUP_ID = PROD_GROUP_ID;
  }

}