package btm.swing;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class BJScreenNodeDetail   {
  private String parentId;
  private String nodeId;
  private String name;

  public BJScreenNodeDetail() {
  }

  public BJScreenNodeDetail(String parentId, String nodeId, String name){
    this.parentId= parentId;
    this.nodeId= nodeId;
    this.name= name;
  }//end Hierarchy

  public String getParentId(){
      return parentId;
  }
  public void setParentId(String parentId){
      this.parentId= parentId;
  }

  public String getNodeId(){
      return nodeId;
  }
  public void setNodeId(String nodeId){
      this.nodeId= nodeId;
  }

  public String getName(){
      return name;
  }
  public void setName(String name){
      this.name= name;
  }
}
