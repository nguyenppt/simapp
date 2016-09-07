package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */

public class AccessRight {
  public static final int LEN_DETAIL_NAME = 120;

  private String parentId;
  private String childId;
  private String name;

  public AccessRight() {
  }

  public AccessRight(String parentId, String childId, String name){
    this.parentId = parentId;
    this.childId = childId;
    this.name = name;
  }//end Hierarchy

  //set and get
  public String getParentId(){
      return parentId;
  }
  public void setParentId(String parentId){
      this.parentId= parentId;
  }

  public String getChildId(){
      return childId;
  }
  public void setChildId(String childId){
      this.childId= childId;
  }

  public String getName(){
      return name;
  }
  public void setName(String name){
      this.name= name;
  }
}
