package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class Season {
  private String Season_ID;
  private String Season_Desc;

  public Season() {
  }

  public Season(String Season_ID,String Season_Desc) {
    this.Season_ID = Season_ID ;
    this.Season_Desc = Season_Desc ;
  }

  //set and get
  public String getSeason_ID(){
      return Season_ID;
  }
  public void setSeason_ID(String Season_ID){
    this.Season_ID = Season_ID ;
  }

  public String getSeason_Desc(){
      return Season_Desc;
  }
  public void setSeason_Desc(String Season_Desc){
    this.Season_Desc = Season_Desc ;
  }




}