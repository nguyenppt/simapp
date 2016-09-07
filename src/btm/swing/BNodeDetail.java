package btm.swing;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class BNodeDetail   {
  private String parentID;
  private String nodeID;
  private String typeID;
  private String name;
  private String parentName;

  public BNodeDetail() {
  }

  public BNodeDetail(String parentID, String nodeID, String typeID, String name, String parentName){
    this.parentID= parentID;
    this.nodeID= nodeID;
    this.typeID= typeID;
    this.name= name;
    this.parentName = parentName;
  }//end Hierarchy

  public String getParentID(){
      return parentID;
  }
  public void setParentID(String parentID){
      this.parentID= parentID;
  }

  public String getNodeID(){
      return nodeID;
  }
  public void setNodeID(String nodeID){
      this.nodeID= nodeID;
  }

  public String getTypeID(){
      return typeID;
  }
  public void setTypeID(String typeID){
      this.typeID= typeID;
  }

  public String getName(){
      return name;
  }
  public void setName(String name){
      this.name= name;
  }

  public String getParentName(){
      return parentName;
  }
  public void setParentName(String parentName){
      this.parentName= parentName;
  }

}
