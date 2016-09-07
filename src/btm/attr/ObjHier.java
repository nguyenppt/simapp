package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */

public class ObjHier {
    public static final int LEN_DETAIL_NAME = 120;
    public static final int LEN_DETAIL_DESC = 120;

  private String parentID;
  private String childID;
  private String typeID;
  private String childName;
  private String parentName;
  private String flagActive;//1:active, 0 : del

  public ObjHier() {
  }

  public ObjHier(String parentID, String childID, String typeID, String parentName, String childName, String flagActive){
    this.parentID= parentID;
    this.childID= childID;
    this.typeID= typeID;
    this.parentName=parentName;
    this.childName=childName;
    this.flagActive=flagActive;
  }//end Hierarchy

  //set and get
  public String getParentID(){
      return parentID;
  }
  public void setParentID(String parentID){
      this.parentID= parentID;
  }

  public String getChildID(){
      return childID;
  }
  public void setChildID(String childID){
      this.childID= childID;
  }

  public String getTypeID(){
      return typeID;
  }
  public void setTypeID(String typeID){
      this.typeID= typeID;
  }

  public String getChildName(){
      return childName;
  }
  public void setChildName(String childName){
      this.childName= childName;
  }

  public String getParentName(){
      return parentName;
  }
  public void setParentName(String parentName){
      this.parentName= parentName;
  }
  public String getFlagActive(){
       return flagActive;
   }
   public void setFlagActive(String flagActive){
       this.flagActive= flagActive;
   }
}
