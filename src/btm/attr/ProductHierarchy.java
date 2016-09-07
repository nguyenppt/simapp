package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */

public class ProductHierarchy {
  private String typeID;
  private String higherTypeID;
  private String typeName;
  private String typeDesc;

  public ProductHierarchy() {
  }

  public ProductHierarchy(String typeID, String higherTypeID, String typeName, String typeDesc){
    this.typeID= typeID;
    this.higherTypeID= higherTypeID;
    this.typeName= typeName;
    this.typeDesc= typeDesc;
  }//end Hierarchy

  //set and get
  public String getTypeID(){
      return typeID;
  }
  public void setTypeID(String typeID){
      this.typeID= typeID;
  }

  public String getHigherTypeID(){
      return higherTypeID;
  }
  public void setHigherTypeID(String higherTypeID){
      this.higherTypeID= higherTypeID;
  }

  public String getTypeName(){
      return typeName;
  }
  public void setTypeName(String typeName){
      this.typeName= typeName;
  }

  public String getTypeDesc(){
      return typeDesc;
  }
  public void setTypeDesc(String typeDesc){
      this.typeDesc= typeDesc;
  }

}